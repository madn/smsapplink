package de.mittelabfluss.alarm

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import de.mittelabfluss.alarm.ui.theme.AlarmTheme
import android.telephony.SmsManager


class MainActivity : ComponentActivity() {
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val telephonyManager = LocalContext.current.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                    val myNumber = telephonyManager.line1Number ?: "+4917000000"
                    MyScreenContent(this, myNumber) // Pass the context to MyScreenContent
                }
            }
        }
        // ATTENTION: This was auto-generated to handle app links.
        val appLinkIntent: Intent = intent
        val appLinkAction: String? = appLinkIntent.action
        val appLinkData: Uri? = appLinkIntent.data
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreenContent(context: Context, myNumber: String) { // Add a context parameter to MyScreenContent
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "SMS APP LINK",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        var smsNumber by remember { mutableStateOf(myNumber) }
        TextField(
            value = smsNumber,
            onValueChange = { smsNumber = it },
            label = { Text("Telefonnummer") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val message = "link: http://mittelabfluss.de" // Set the message to send

                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(smsNumber, null, message, null, null)

                //     Use an intent to send an SMS
            //    val sendIntent = Intent(Intent.ACTION_VIEW).apply {
            //        data = Uri.parse("smsto:$smsNumber")
            //        putExtra("sms_body", message)
            //    }
            //
            //    context.startActivity(sendIntent) // Use the context to call startActivity
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "sendSMS")
        }

    }
}
