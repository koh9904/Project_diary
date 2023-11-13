package com.example.object_orientedprogramming.diary

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.object_orientedprogramming.R
import com.example.object_orientedprogramming.diary.etc.AppConstants
import com.example.object_orientedprogramming.diary.etc.DiaryItem
import com.example.object_orientedprogramming.diary.etc.OnTabItemSelectedListener


class DiaryFragment : Fragment(R.layout.fragment_diary) {
    private var mMode = AppConstants.MODE_INSERT
    private lateinit var item : DiaryItem
    private var listener : OnTabItemSelectedListener? = null
    private var date : TextView? = null
    private var content : TextView? = null
    private var image : ImageView? = null
    private var contentInput : TextView? = null
    private var resultPhotoBitmap : Bitmap? = null

    private lateinit var rootView: View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnTabItemSelectedListener) {
            listener = context
        }

    }

    override fun onDetach() {
        super.onDetach()
        if (context != null) {
            listener = null
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_diary, container, false) as ViewGroup

        initUI()
        applyItem()
        return rootView
    }

    private fun initUI() {
        date = rootView.findViewById(R.id.date)
        content = rootView.findViewById(R.id.content)
        image = rootView.findViewById(R.id.image)
    }

    private fun applyItem() {
        mMode = AppConstants.MODE_MODIFY
        assignContent(item.content)
        val imagePath = item.image
        if (imagePath.isEmpty()) {
            image!!.setImageResource(R.drawable.female)
        }
        else {
            1.assignImage(item.image)
        }
    }

    private fun assignContent(data: String?) {
        contentInput!!.text = data
    }

    private fun Int.assignImage(imagePath: String?) {
        val options = BitmapFactory.Options()
        options.inSampleSize = this
        resultPhotoBitmap = BitmapFactory.decodeFile(imagePath, options)
        image!!.setImageBitmap(resultPhotoBitmap)
    }

}