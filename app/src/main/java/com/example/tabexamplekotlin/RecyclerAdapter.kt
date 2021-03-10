package com.example.tabexamplekotlin

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import java.io.IOException
import java.util.*


class RecyclerAdapter(private val devices: ArrayList<BluetoothDevice?>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    //private var mBlDevices = ArrayList<BluetoothDevice?>()

   /* fun RecyclerAdapter(devices: ArrayList<BluetoothDevice?>) {
        mBlDevices = devices
        Log.d(TAG, "Bluetooth Devices array was delivered to recycler view adapter")
        Log.d(TAG, "Number of devices is ${mBlDevices.size}")
    }*/

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView
        lateinit var itemDetail: TextView

        init {
            //itemDetail = itemView.findViewById(R.id.item_detail)
            itemTitle = itemView.findViewById(R.id.item_title)
            Log.d(TAG,"View Holder is initialized")
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        Log.d(TAG,"Rec View OnCreateViewHolder")
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rec_item, viewGroup, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        Log.d(TAG,"RecView OnBindViewHolder")
        viewHolder.itemTitle.text = devices[i]!!.name
        //viewHolder.itemDetail.text = devices[i]!!.address
        viewHolder.itemView.setOnClickListener { Log.d(TAG,"Item ${i + 1} - ${devices[i]!!.name} was clicked") }
        //  viewHolder.itemTitle.setText(mDevice.getTitle());
        //  viewHolder.itemDetail.setText(mDevice.getAddress());
    }

    override fun getItemCount(): Int {
        Log.d(TAG,"Bluetooth Devices num: ${devices.size}")
        return devices.size
    }

}

/*private inner class ConnectThread(device: BluetoothDevice) : Thread() {

    private val bluetooth: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
        device.createRfcommSocketToServiceRecord(MY_UUID)
    }

    public override fun run() {
        // Cancel discovery because it otherwise slows down the connection.
        bluetooth?.cancelDiscovery()

        mmSocket?.use { socket ->
            // Connect to the remote device through the socket. This call blocks
            // until it succeeds or throws an exception.
            socket.connect()

            // The connection attempt succeeded. Perform work associated with
            // the connection in a separate thread.
            manageMyConnectedSocket(socket)
        }
    }

    // Closes the client socket and causes the thread to finish.
    fun cancel() {
        try {
            mmSocket?.close()
        } catch (e: IOException) {
            Log.e(TAG, "Could not close the client socket", e)
        }
    }
}*/