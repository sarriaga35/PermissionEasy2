package com.charfaoiu.permissioneasy2

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.charfaoiu.permissioneasy2.ui.theme.PermissionEasy2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PermissionEasy2Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Content()
            }
        }
    }
}
    @Composable
    public fun Content() {
        val cameraPermission = android.Manifest.permission.CAMERA

        var isPermissionGranted by remember {mutableStateOf(checkPermissionFor(cameraPermission))}
        val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(),
            onResult = {isGranted -> if(isGranted) isPermissionGranted = true}
        )


        Column {
            Button(onClick = {
                if (!isPermissionGranted)
                {launcher.launch(cameraPermission)}
            }) {
                Text(text = "Camera permission $isPermissionGranted")
            }

        }
    }
        private fun checkPermissionFor(permission: String): Boolean {
            return  ContextCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_GRANTED

        }



}

