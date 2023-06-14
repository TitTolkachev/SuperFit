package com.example.superfit.presentation.view.screens.main.body

import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.superfit.R
import java.io.ByteArrayOutputStream

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun NewImageDialog(
    state: BodyScreenState,
    sendEvent: (BodyScreenIntent) -> Unit
) {

    // ---------------------------------------------------------
    // Philipp Lackner
    // https://github.com/philipplackner/NewPhotoPickerAndroid13
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            sendEvent(BodyScreenIntent.ImageSelected(uri))
        }
    )

    val context = LocalContext.current
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            it?.let {
                val bytes = ByteArrayOutputStream()
                it.compress(Bitmap.CompressFormat.PNG, 100, bytes)
                val path: String = MediaStore.Images.Media.insertImage(
                    context.contentResolver, it, "image_title", null
                )
                sendEvent(BodyScreenIntent.ImageSelected(Uri.parse(path)))
            }
        }

    Dialog(onDismissRequest = { sendEvent(BodyScreenIntent.CloseDialog) }) {
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                AsyncImage(
                    error = painterResource(id = R.drawable.image_dialog_placeholder),
                    model = state.imageUri,
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Button(
                        modifier = Modifier.width(120.dp),
                        onClick = {
                            galleryLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                    ) {
                        Text(text = context.getString(R.string.gallery_button_text))
                    }
                    Button(
                        modifier = Modifier.width(120.dp),
                        onClick = { cameraLauncher.launch() }
                    ) {
                        Text(text = context.getString(R.string.camera_button_text))
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier.width(120.dp),
                        onClick = { sendEvent(BodyScreenIntent.CloseDialog) }
                    ) {
                        Text(text = context.getString(R.string.cancel_button_text))
                    }
                    Button(
                        modifier = Modifier.width(120.dp),
                        onClick = { sendEvent(BodyScreenIntent.SaveImage(context.contentResolver)) }
                    ) {
                        Text(text = context.getString(R.string.save_button_text))
                    }
                }
            }

        }
    }
}