package com.example.object_orientedprogramming.diary

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


class DiaryFragment(private val item: DiaryItem) : Fragment(R.layout.fragment_diary) {
    private var mMode = AppConstants.MODE_INSERT
    private var resultPhotoBitmap : Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_diary, container, false) as ViewGroup

        val date = rootView.findViewById<TextView>(R.id.tDdate)
        val content = rootView.findViewById<TextView>(R.id.tDcontent)
        val image = rootView.findViewById<ImageView>(R.id.tDimage)

        mMode = AppConstants.MODE_MODIFY

        content.text = item.content

        val imagePath = item.image

        if (imagePath.isEmpty()) {
            image.setImageResource(R.drawable.female)
        } else {
            val options = BitmapFactory.Options()

            options.inSampleSize = 1

            resultPhotoBitmap = BitmapFactory.decodeFile(imagePath, options)

            image.setImageBitmap(resultPhotoBitmap)
        }

        return rootView
    }
}