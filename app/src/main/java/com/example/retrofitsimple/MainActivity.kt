package com.example.retrofitsimple

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitsimple.models.WeatherResponse
import com.example.retrofitsimple.network.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var tvLatitude:TextView
    private lateinit var tvLongitude:TextView
    private lateinit var tvWeather:TextView
    private lateinit var tvWindSpeed:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvLatitude = findViewById(R.id.tvLatitude)
        tvLongitude = findViewById(R.id.tvLongitude)
        tvWeather = findViewById(R.id.tvWeather)
        tvWindSpeed = findViewById(R.id.tvWindSpeed)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: WeatherService = retrofit
            .create(WeatherService::class.java)

        val listCall: Call<WeatherResponse> = service.getWeather(
            Constants.LATITUDE,Constants.LONGITUDE,Constants.METRIC_UNIT,Constants.APP_ID
        )

        listCall.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if(response.isSuccessful){
                    val weatherList : WeatherResponse? = response.body()
                    Log.i("Response Result", "$weatherList ")

                    tvLatitude.text = "經度： ${weatherList!!.coord.lat}"
                    tvLongitude.text = "緯度： ${weatherList.coord.lon}"
                    tvWeather.text = "天氣： ${weatherList.weather[0].main}"
                    tvWindSpeed.text = "風速： ${weatherList.wind.speed}"
                }else{
                    when(response.code()){
                        400->{
                            Log.e("Error 400", "Bad Connection" )
                        }
                        404->{
                            Log.e("Error 400", "Not Found" )
                        }
                        else->{
                            Log.e("Error", "Generic Error" )
                        }
                    }
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("Errorrrrrr", t.message.toString())
            }

        })
    }
}

