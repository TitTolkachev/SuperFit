package com.example.superfit.data.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import com.example.superfit.domain.model.Exercises
import com.example.superfit.domain.repository.sensors.SensorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SensorsRepositoryImpl(context: Context) : SensorsRepository {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val _progressState = MutableStateFlow(0)
    override val progressState: StateFlow<Int> = _progressState

    // Values for squats and push-ups
    private var isLowestPointDetected = false
    private var isHighestPointDetected = false
    private var isMovingUp = false

    private val squatsAccelerationModule = 3f
    private val pushUpsAccelerationModule = 1f

    private var progress = 0

    // Steps for running
    private var stepsSum = 0f

    private var exercise: Exercises? = null

    override fun subscribe(exercise: Exercises) {

        this.exercise = exercise
        progress = 0
        stepsSum = 0f
        MainScope().launch(Dispatchers.IO) {
            _progressState.emit(0)
        }

        if (exercise == Exercises.SQUATS || exercise == Exercises.PUSH_UP) {
            val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
            sensor?.let {
                sensorManager.registerListener(
                    movementListener, sensor, SensorManager.SENSOR_DELAY_NORMAL
                )
            }
        }
        if (exercise == Exercises.RUNNING) {
            val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
            sensor?.let {
                sensorManager.registerListener(
                    stepsListener, it, SensorManager.SENSOR_DELAY_NORMAL
                )
            }
        }
    }

    override fun unsubscribe() {
        sensorManager.unregisterListener(movementListener)
        sensorManager.unregisterListener(stepsListener)
    }

    private val movementListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val y = event.values[1]
            val z = event.values[2]

            if (exercise == Exercises.SQUATS) {
                if (y < -squatsAccelerationModule)
                    isLowestPointDetected = true

                if (y > squatsAccelerationModule)
                    isHighestPointDetected = true
            }

            if (exercise == Exercises.PUSH_UP) {
                if (z < -pushUpsAccelerationModule)
                    isLowestPointDetected = true

                if (z > pushUpsAccelerationModule)
                    isHighestPointDetected = true
            }

            if (isLowestPointDetected && isHighestPointDetected) {
                if (isMovingUp) {
                    progress++
                    MainScope().launch(Dispatchers.IO) {
                        _progressState.emit(progress)
                    }
                    Log.e("SENSOR REPEATS", progress.toString())
                }
                isMovingUp = !isMovingUp
                isLowestPointDetected = false
                isHighestPointDetected = false
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }

    private val stepsListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val totalSteps = event.values[0]
            if (stepsSum == 0f) stepsSum = totalSteps
            val currentSteps = totalSteps.toInt() - stepsSum
            stepsSum = totalSteps
            MainScope().launch(Dispatchers.IO) {
                _progressState.emit(stepsSum.toInt())
            }
            Log.e("SENSOR STEPS", currentSteps.toString())
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }
}