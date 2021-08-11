package com.jason.common.widgets

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.airbnb.lottie.LottieAnimationView
import com.jason.common.R

class LoadingDialog : DialogFragment() {

    lateinit var lottieView:LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = inflater.inflate(R.layout.fragment_loading, container, false)
        lottieView = view.findViewById(R.id.lottieView)
        return view
    }

    fun show(manager: FragmentManager){
        val transaction = manager.beginTransaction()
        transaction.add(this,"loadingView")
        transaction.commitAllowingStateLoss()
    }


    override fun dismiss() {
        dismissAllowingStateLoss();
    }

    override fun onDestroy() {
        lottieView.cancelAnimation()
        lottieView.clearAnimation()
        super.onDestroy()
    }
}