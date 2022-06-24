package com.example.recipie_app_for_final

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.recipie_app_for_final.database.RecipieDatabase
import com.example.recipie_app_for_final.entities.Category
import com.example.recipie_app_for_final.interfaces.Getdataservice
import com.example.recipie_app_for_final.retrofit.retrofitclientinstance
import kotlinx.android.synthetic.main.splash_screen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity(), EasyPermissions.RationaleCallbacks, EasyPermissions.PermissionCallbacks {
    private var READ_STORAGE_PERM = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)


        readStorageTask()

        btnStart.setOnClickListener {
            var intent = Intent(this@SplashActivity,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun getCategories(){
        val service = retrofitclientinstance.retrofitInstace?.create(Getdataservice::class.java)
        val call = service?.getCatList()
        call?.enqueue(object : Callback<Category>{
            override fun onResponse(
                call: Call<Category>,
                response: Response<Category>
            ) {
                insertDataIntoRoomDb(response.body())
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                loader.visibility = View.INVISIBLE
                Toast.makeText(this@SplashActivity,"racxa ar wevida sworad",Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun insertDataIntoRoomDb(category : Category?) {

        launch {
            this.let {
                RecipieDatabase.getDatabase(this@SplashActivity).recipieDao().clearDb()
                for (arr in category!!.categoryItems!!){
                    RecipieDatabase.getDatabase(this@SplashActivity)
                        .recipieDao().insertCategory(arr)
                }

                btnStart.visibility = View.VISIBLE
            }
        }
    }
    private fun hasReadStoragePermission():Boolean{
        return EasyPermissions.hasPermissions(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }
    private fun readStorageTask(){
        if (hasReadStoragePermission()){
            getCategories()

        } else {
            EasyPermissions.requestPermissions(
                this,
                "gvhirdeba wvdoma mexsierebastan",
                READ_STORAGE_PERM,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }
    override fun onRationaleAccepted(requestCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onRationaleDenied(requestCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            AppSettingsDialog.Builder(this).build().show()
        }
    }

}