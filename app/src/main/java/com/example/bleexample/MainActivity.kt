package com.example.bleexample

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var pairedDevices: Set<BluetoothDevice>? = null
    lateinit var BA: BluetoothAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BA = BluetoothAdapter.getDefaultAdapter()

        button.setOnClickListener {
            on()
        }
        button2.setOnClickListener {
            visible()
        }
        button3.setOnClickListener {
            list()
        }
        button4.setOnClickListener {
            off()
        }

    }

    override fun onResume() {
        super.onResume()

    }

    fun on() {
        if (!BA.isEnabled) {
            val turnOn = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(turnOn, 0)
            Toast.makeText(applicationContext, "Turned on", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Already on", Toast.LENGTH_SHORT).show()
        }
    }

    fun off() {
        BA.disable()
        Toast.makeText(applicationContext, "Turned off", Toast.LENGTH_SHORT).show()
    }

    fun visible() {
        val getVisible = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
        startActivityForResult(getVisible, 0)
    }

    fun list() {
        pairedDevices = BA.bondedDevices
        val list = ArrayList<Any>()
        for (bt in (pairedDevices as MutableSet<BluetoothDevice>?)!!) list.add(bt.name)
        Toast.makeText(applicationContext, "Showing Paired Devices", Toast.LENGTH_SHORT).show()
        val adapter: ArrayAdapter<*> = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter
    }
}