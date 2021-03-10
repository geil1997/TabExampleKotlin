package com.example.tabexamplekotlin

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.Toast
import java.util.*

open class SearchFragment : Fragment() {

    private val REQUEST_ENABLE_BT: Int = 17

    private val bluetooth: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    //recyclerView.layoutManager = layoutManager
    //private var adapter = RecyclerAdapter()
    //recyclerView.adapter = adapter
    private var mBlDevices = ArrayList<BluetoothDevice?>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.hasFixedSize()
        layoutManager = LinearLayoutManager(activity)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        Log.d(TAG, "New Devices is opened")
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu);

        super.onCreateOptionsMenu(menu, inflater)
        Log.d(TAG, "Menu is created")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.find_new -> {
                Log.d(TAG, "Find new devices button us clicked")
                findNewDevices()
                true
            }
            R.id.refresh_list -> {
                Log.d(TAG, "Refresh list button us clicked")
                updateList()
                true
            }
            R.id.settings -> {
                Log.d(TAG, "Settings button is clicked")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun findNewDevices(){
        turnBtOn()
        findDevices()
        //updateList()
    }

    private fun updateList(){
        //adapter = RecyclerAdapter(mBlDevices)
        recyclerView.layoutManager = layoutManager
        //adapter.RecyclerAdapter(mBlDevices)
        //recyclerView?.adapter = adapter
        val adapter: RecyclerAdapter = RecyclerAdapter(mBlDevices)
        recyclerView.adapter = adapter
    }

    private fun findDevices(){
        bluetooth.cancelDiscovery()
        mBlDevices.clear()
        val MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION: Int = 1
        ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION)
        ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.WAKE_LOCK),
                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION)
        bluetooth.startDiscovery()
        val filter = IntentFilter()
        filter.addAction(BluetoothDevice.ACTION_FOUND)
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        activity!!.registerReceiver(receiver, filter)
    }

    // Create a BroadcastReceiver for ACTION_FOUND.
    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            when(intent.action) {
                BluetoothDevice.ACTION_FOUND -> {
                    Log.d(TAG, "Found a device")
                    // Discovery has found a device. Get the BluetoothDevice
                    // object and its info from the Intent.
                    val device: BluetoothDevice? =
                            intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    if (device?.name != "" && device?.name != null) {mBlDevices.add(device)}
                    Log.d(TAG, "Device added to an array")
                }
                BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {
                    Toast.makeText(context, "Bluetooth discovery has started", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Started discovery")
                }
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                    Toast.makeText(context, "Bluetooth discovery has finished", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Finished discovery")
                }
            }
        }
    }

    //включаем бт, если выключен
    private fun turnBtOn(){
        if (bluetooth.isEnabled) {
            //Toast.makeText(activity, bluetooth.address, Toast.LENGTH_SHORT).show()
            //Toast.makeText(activity, bluetooth.name, Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Bluetooth was already turned on")
        } else {
            //status="Bluetooth выключен";
            //Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG).show();
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            Log.d(TAG, "Bluetooth was turned on")
        }
    }

    //IMPORTANT FUN!!!
    //Needed to notify studio about our intent to create a menu
    //without this one wouldn't work
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        activity!!.unregisterReceiver(receiver)
    }

}