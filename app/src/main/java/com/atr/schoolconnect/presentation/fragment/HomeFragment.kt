package com.atr.schoolconnect.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atr.schoolconnect.data.viewModels.AuthViewModel
import com.atr.schoolconnect.databinding.FragmentHomeBinding
import com.atr.schoolconnect.domain.BannerModel
import com.atr.schoolconnect.domain.BannerModelData
import com.atr.schoolconnect.domain.PostModel
import com.atr.schoolconnect.domain.PostModelData
import com.atr.schoolconnect.domain.rest.ApiResponseListener
import com.atr.schoolconnect.presentation.adapter.BannerAdapter
import com.atr.schoolconnect.presentation.adapter.HomeSocialAdapter
import com.atr.schoolconnect.presentation.utilities.PreferenceConnector
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment(), ApiResponseListener {
    private lateinit var binding: FragmentHomeBinding
    var authViewModel: AuthViewModel? = null
    private var apiResponseListener: ApiResponseListener? = null
    lateinit var sharedPreferences: PreferenceConnector

   /* Post rec */
    private val postList = mutableListOf<PostModelData>()
    private var adapter: HomeSocialAdapter? = null

    private var page = 1
    private var isLoading = false
    private var isLastPage = false

    /* banner */
    private var bannerAdapter: BannerAdapter? = null
    private val bannerList = mutableListOf<BannerModelData>()
    private var bannerJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        sharedPreferences = PreferenceConnector(requireActivity())
        apiResponseListener = this@HomeFragment

        // Collect StateFlow
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                authViewModel?.responseState?.collect { apiResponse ->
                    apiResponse?.let { putResponse(it) }
                }
            }
        }
        adapter = HomeSocialAdapter(requireContext(), postList)

        binding.rec.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HomeFragment.adapter
        }
        setupBanner()

        authViewModel?.loadBanners()


        binding.rec.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy <= 0) return

                if (!recyclerView.canScrollVertically(1)
                    && !isLoading
                    && !isLastPage
                ) {
                    isLoading = true
                    page += 1
                    authViewModel?.loadPosts(page)
                }
            }
        })


        return binding.root
    }

    private fun setupBanner() {
        bannerAdapter = BannerAdapter(bannerList)
        binding.bannerPager.adapter = bannerAdapter
    }

    private fun startAutoScroll() {
        bannerJob?.cancel()

        bannerJob = viewLifecycleOwner.lifecycleScope.launch {
            while (true) {
                delay(3000) // 3 seconds

                val count = bannerAdapter?.itemCount ?: 0
                if (count <= 1) continue

                val nextItem =
                    (binding.bannerPager.currentItem + 1) % count

                binding.bannerPager.setCurrentItem(nextItem, true)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        bannerJob?.cancel()
    }

    override fun onResume() {
        super.onResume()
        startAutoScroll()
    }

    override fun onLoading() {
    }

    override fun onDataRender(jsonObject: JsonObject?) {
    }

    override fun onResponseRender(jsonObject: JsonObject?) {

        val apiType = jsonObject?.get("apiType")?.asString ?: return
        Log.i("bannerModel", "apiType: "+apiType)

        when (apiType) {

            "posts" -> {
                jsonObject.getAsJsonObject("response")?.let { responseObj ->
                    val realResponseObj = responseObj.getAsJsonObject("data")
                    val postModel = Gson().fromJson(realResponseObj, PostModel::class.java)

                    isLoading = false
                    val newItems = postModel.data
                    if (newItems.isEmpty()) {
                        isLastPage = true
                        return
                    }
                    postList.addAll(newItems)
                    adapter?.submitList(postList.toList())
                }
            }

            "banners" -> {
                Log.i("bannerModel", "banners: ")

                jsonObject.getAsJsonObject("response")?.let { responseObj ->
                    Log.i("bannerModel", "bannerModel.data: "+responseObj)

                    val bannerModel = Gson().fromJson(responseObj.getAsJsonObject("data"), BannerModel::class.java)
                    Log.i("bannerModel", "bannerModel.data: "+bannerModel.data)

                    bannerList.clear()
                    bannerList.addAll(bannerModel.data)
                    Log.i("bannerModel", "bannerModel.data: "+bannerModel.data)
                    bannerAdapter?.notifyDataSetChanged()
                    startAutoScroll()
                    authViewModel?.loadPosts(page)
                }

            }
        }
    }


    override fun onAuthFailure(message: String?) {
    }

    override fun onServerFailure(message: String?) {
    }

    override fun onOtherFailure(message: String?) {
    }

    override fun onTokenExpire(message: String?, shouldLogout: Boolean) {
    }

    override fun onMembershipExpired(message: String?) {
    }


}

