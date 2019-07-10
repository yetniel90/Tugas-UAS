package com.example.myapplication.view.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myapplication.BuildConfig

import com.example.myapplication.R
import com.example.myapplication.model.DataModel
import com.example.myapplication.model.ItemModel
import com.example.myapplication.rest.ApiConfig
import com.example.myapplication.rest.ApiService
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profil.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ProfilFragment : Fragment() {
    private var items: ArrayList<ItemModel> = arrayListOf()
    private lateinit var npmTexView: TextView
    private lateinit var nik: TextView
    private lateinit var nisn: TextView
    private lateinit var nama: TextView
    private lateinit var kelamin: TextView
    private lateinit var dosen_wali: TextView
    private lateinit var almt: TextView
    private lateinit var ayah: TextView
    private lateinit var ibu: TextView
    private lateinit var email: TextView
    private lateinit var npm: TextView
    private lateinit var ivImageProfile: CircleImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profil, container, false)
        ivImageProfile = view.ivImageProfile
        npmTexView = view.tvNpm
        nama = view.fnama
        kelamin = view.fkelamin
        dosen_wali = view.fdosen
        almt = view.falamat
        email = view.femail
        ayah = view.fayah
        ibu = view.fibu
        npm=view.tvNpm
        nisn = view.tvNISN
        npmTexView.text = "16670016"

        getDatas()

        return view;
    }

    private fun getDatas() {
        val apiService: ApiService = ApiConfig.provideApi()
        apiService.getProfil()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                items.clear()
                items = it.data as ArrayList<ItemModel>
                for (i: Int in items.indices) {
                    nik.text = items.get(i).kalmt
                    nisn.text = items.get(i).nisn
                    activity?.let { it1 ->
                        Glide.with(it1).load("http://informatika.upgris.ac.id/mobile/image/16670016" + items.get(i).foto)
                            .override(512, 512).error(R.drawable.error).into(ivImageProfile)
                    }
                    if (BuildConfig.NPM.equals("16670016")) {
                        npm.text = items.get(i).npm
                        nisn.text = items.get(i).nisn
                        nama.text = items.get(i).nama
                        kelamin.text = items.get(i).kelamin
                        dosen_wali.text= items.get(i).dosenWali
                        almt.text=items.get(i).almt
                        email.text=items.get(i).email
                        ayah.text=items.get(i).ayah
                        ibu.text=items.get(i).ibu

                        activity?.let { it1 ->
                            Glide.with(it1).load("https://avatars0.githubusercontent.com/u/28645602?s=460&v=4")
                                .override(512, 512).error(R.drawable.error).into(ivImageProfile)
                        }
                    }
                }


            }, {
                error("Error" + it.message)
            })

    }


}
