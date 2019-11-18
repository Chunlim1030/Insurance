package com.example.insurance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, "Position " + position, Toast.LENGTH_LONG).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerAge.onItemSelectedListener = this

        buttonCalculate.setOnClickListener {
            calculatePremium()
        }

        buttonReset.setOnClickListener {
            reset()
        }
    }

    private fun calculatePremium(){
        var basic_premium: Int = 0
        val position = spinnerAge.selectedItemPosition
        val gender = radioGroupGender.checkedRadioButtonId
        val symbol = Currency.getInstance(Locale.getDefault()).symbol

        basic_premium = when(position){
            0 -> 60 //less than 17
            1 -> 70 //17 to 25
            2 -> 90 //26 to30
            3 -> 120 //31 to 40
            4 -> 150 // 41 to 55
            else -> 150 //more than 55
        }

        if(gender == R.id.radioButtonMale){
            //TODO calculate premium for male
            basic_premium += when(position) {
                0 -> 0 //less than 17
                1 -> 50 //17 to 25
                2 -> 100 //26 to30
                3 -> 150 //31 to 40
                4 -> 200 // 41 to 55
                else -> 200 //more than 55
            }
        }

        if(checkBoxSmoker.isChecked){
            //TODO calculate premium for smoker
            basic_premium += when(position){
                0 -> 0 //less than 17
                1 -> 100 //17 to 25
                2 -> 150 //26 to30
                3 -> 200 //31 to 40
                4 -> 250 // 41 to 55
                else -> 300 //more than 55
            }
        }

        textViewPremium.text = String.format("%s %s %d", getString(R.string.insurance_premium), symbol, basic_premium)
    }

    private fun reset(){
        textViewPremium.text = ""

    }
}
