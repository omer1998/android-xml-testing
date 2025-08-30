package com.example.xml_project_testing

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {
    lateinit var equalButton: Button
    lateinit var result: TextView
    lateinit var addButton: Button
    lateinit var subtractButton: Button
    lateinit var multiplyButton: Button
    lateinit var divideButton: Button
    lateinit var clearButton: Button
    lateinit var deleteButton: Button
    lateinit var equation: TextView
    var operation: Operation? = null
    var firstNumber: Float = 0f
    var equationText: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        initViews()
        addButton.setOnClickListener {
            manageOperation(Operation.ADD)
        }
        subtractButton.setOnClickListener {
            manageOperation(Operation.SUBTRACT)
        }
        multiplyButton.setOnClickListener {
            manageOperation(Operation.MULTIPLY)
        }
        divideButton.setOnClickListener {
            manageOperation(Operation.DIVIDE)
        }
        clearButton.setOnClickListener {
            result.text = ""
            equation.text = ""
            equationText = ""
            firstNumber = 0f
            operation = null
        }



        equalButton.setOnClickListener {
            if(result.text.isEmpty().not()){
                var secondNumber = result.text.toString().toFloat()
                performOperation(firstNumber, secondNumber)
            }
        }
    }
    private fun manageOperation(op: Operation) {
        if (result.text.isEmpty()) return
        operation = op
        firstNumber = result.text.toString().toFloat()
        result.text = ""
        equationText = equationText + firstNumber.toString() + op.toSymbol()
        equation.text = equationText
    }

    private fun performOperation(firstNumber: Float, secondNumber: Float) {
        val total = when (operation) {
            Operation.ADD -> firstNumber + secondNumber
            Operation.SUBTRACT -> firstNumber - secondNumber
            Operation.MULTIPLY -> firstNumber * secondNumber
            Operation.DIVIDE -> firstNumber / secondNumber
            null -> 0f
        }
        result.text = total.toString()
        equationText = equationText + secondNumber.toString() + " = "
        equation.text = equationText
    }


    private fun initViews() {
        equalButton = findViewById(R.id.equalButton)
        result = findViewById(R.id.result)
        addButton = findViewById(R.id.addButton)
        equation = findViewById(R.id.equation)
        subtractButton = findViewById(R.id.subtractButton)
        multiplyButton = findViewById(R.id.multiplyButton)
        divideButton = findViewById(R.id.divideButton)
        clearButton = findViewById(R.id.clearButton)
    }


    fun onNumberClick(v: View) {
        val currentResult = result.text.toString()
        val buttonNumber = (v as Button).text.toString()
        val newNumber = currentResult + buttonNumber
        result.text = newNumber
    }
}

enum class Operation {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE
}

fun Operation.toSymbol(): String {
    return when (this) {
        Operation.ADD -> " + "
        Operation.SUBTRACT -> " - "
        Operation.MULTIPLY -> " x "
        Operation.DIVIDE -> " / "
    }
}