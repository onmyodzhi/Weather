package com.example.weatherapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import com.example.weatherapp.adapters.WeatherAdapter
import com.example.weatherapp.adapters.WeatherModel
import com.example.weatherapp.databinding.FragmentHoursBinding
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.roundToInt


class HoursFragment : Fragment() {
    private lateinit var binnding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter
    private val model: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binnding = FragmentHoursBinding.inflate(inflater, container, false)
        return binnding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        model.liveDataCurrent.observe(viewLifecycleOwner) {
            adapter.submitList(getHoursList(it))
        }
    }

    private fun initRcView() = with(binnding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter(null)
        rcView.adapter = adapter

    }

    private fun getHoursList(weatherItemitem: WeatherModel): List<WeatherModel> {
        val hoursArray = JSONArray(weatherItemitem.hours)
        val hoursList = ArrayList<WeatherModel>()

        for (i in 0 until hoursArray.length()) {
            val item = WeatherModel(
                city = weatherItemitem.city,
                time = (hoursArray[i] as JSONObject).getString("time"),
                condition = (hoursArray[i] as JSONObject)
                    .getJSONObject("condition")
                    .getString("text"),
                currentTemp = (hoursArray[i] as JSONObject).getString("temp_c")
                    .toDouble().roundToInt().toString().plus("°C"),
                minTemp = "",
                maxTemp = "",
                imageUrl = (hoursArray[i] as JSONObject)
                    .getJSONObject("condition")
                    .getString("icon"),

                hours = ""
            )
            hoursList.add(item)
        }
        return hoursList
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}
