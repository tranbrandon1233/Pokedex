package com.example.codepath_project4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.codepath_project4.ui.theme.Codepath_Project4Theme
import com.example.codepath_project4.databinding.ActivityMainBinding
import okhttp3.Headers


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




                    val client = AsyncHttpClient()
                    var pokemon = "ditto"


                    binding.buttonId.setOnClickListener {
                        pokemon = binding.editTextId.text.toString();

                        client["https://pokeapi.co/api/v2/pokemon/${pokemon}", object :
                            JsonHttpResponseHandler() {
                            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                                // called when response HTTP status is "200 OK"

                                val abilities = json.jsonObject.getJSONArray("abilities");
                                val types = json.jsonObject.getJSONArray("types");

                                var movesTxt = "Moves: "
                                var typeTxt = "Type(s): "
                                for (i in 0 until abilities.length()) {
                                    movesTxt +=
                                        abilities.getJSONObject(i).getJSONObject("ability")
                                            .getString("name");
                                    if (i != abilities.length() - 1) {
                                        movesTxt += ", "
                                    }


                                }
                                for (i in 0 until types.length()) {
                                    typeTxt +=
                                        types.getJSONObject(i).getJSONObject("type")
                                            .getString("name");
                                    if (i != types.length() - 1) {
                                        movesTxt += ", "
                                    }


                                }
                                val name = "Name: ${json.jsonObject.getString("name")}"
                                binding.textView2.text = movesTxt
                                binding.textView1.text = name
                                binding.textView3.text = typeTxt


                            }


                            override fun onFailure(
                                statusCode: Int,
                                headers: Headers?,
                                errorResponse: String,
                                t: Throwable?
                            ) {

                                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                            }
                        }]
                    }
                }
            }
