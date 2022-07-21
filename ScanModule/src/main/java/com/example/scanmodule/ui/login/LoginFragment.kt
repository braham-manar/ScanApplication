package com.example.scanmodule.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.scanmodule.data.Api.ApiUserLogin
import com.example.scanmodule.R
import com.example.scanmodule.data.Api.model.Info
import com.example.scanmodule.data.dataBase.model.LoginUserEntity
import com.example.scanmodule.data.remote.model.LoginResponse
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder


const val Base_URL_Of_Email="https://dev-api.box2home.xyz/api/"
class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMail()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



}
    private fun getMail(){
        val retrofitBuilder= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Base_URL_Of_Email)
            .build()
            .create(ApiUserLogin ::class.java)
        val retrofitData = retrofitBuilder.getMail()
        retrofitData.enqueue(object:Callback<List<LoginResponse>?> {
            override fun onResponse(call: Call<List<LoginResponse>?>, response: Response<List<LoginResponse>?>) {
                val responseBody=response.body()!!
                //val MystringBuilder= StringBuilder()
               for (MyData in responseBody[0].collaborateur){
                   if(email.text.toString()==MyData.login){

                           btn_scan.setOnClickListener(){
                               Log.i("test_click", "recycle_btn: clicked ")
                               findNavController().navigate(R.id.scanFragment)

                       }
                   // MystringBuilder.append(MyData)
                   // MystringBuilder.append("\n")
                   }


                }

            }
            override fun onFailure(call: Call<List<LoginResponse>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })}
}



