package com.example.tabexamplekotlin

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import java.util.jar.Manifest

class MyBluetoothAdapter: BaseAdapter() {

    private val PERMISSION_CODE: Int = 6
    val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    lateinit var bluetoothArray: Array<BluetoothDevice>

    //количество элементов
    override fun getCount(): Int =
        bluetoothArray.size

    //элемент по позиции
    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    //id по позиции
    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    //пункт списка
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    fun newDevices(){

    }

    fun checkBluetoothSettings(){
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(baseContext,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    Activity(),
                    arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                    PERMISSION_CODE)
            }
        }*/
        /*if (bluetoothAdapter?.isEnabled == false) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }*/
    }

    fun startDeviceSearch(){

    }

}