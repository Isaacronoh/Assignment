package com.ronohisaac.tipcalc


import android.icu.text.NumberFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions


import androidx.compose.ui.Alignment
import androidx.compose.material.TextField

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ronohisaac.tipcalc.ui.theme.TipCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
TipTimeScreen()
                }
            }
        }
    }
}

@Composable
fun TipTimeScreen() {
    var amountInput by remember { mutableStateOf("0") }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    Column(modifier = Modifier.padding(32.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = stringResource(R.string.Calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        EditNumberField(value = amountInput, onValueChange = { amountInput = it })
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.tip_amount, ""),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
private infix fun Any.align(centerHorizontally: Alignment.Horizontal): Any {
    TODO("Not yet implemented")
}
@Composable
fun EditNumberField(value: String,
                    onValueChange: (String) -> Unit) {

    var amountInput by remember { mutableStateOf("0") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount,tipPercentage=0.19)
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.cost_of_service)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

}
private fun calculateTip(
    amount :Double,
    tipPercentage : Double = 19.0
): String? {
    val tip = tipPercentage /100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)

}





