package com.avenger.timesaver.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.avenger.timesaver.Adapter.TipsAdapter
import com.avenger.timesaver.R
import com.avenger.timesaver.models.TipsModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tips.*

@AndroidEntryPoint

class TipsFragment : Fragment() {
    private var list = mutableListOf<TipsModel>()
    private lateinit var adapter: TipsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tips, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildTipsModelList()
        setRecyclerAdapter()


    }

    private fun setRecyclerAdapter() {
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter = TipsAdapter(list)
        recyclerView.adapter = adapter

    }

    private fun buildTipsModelList() {
        list.add(
            TipsModel(
                1,
                "9 Easy Male Grooming Tips | Men's Grooming",
                "https://i.ytimg.com/an_webp/mVJKf-Zxe5Q/mqdefault_6s.webp?du=3000&sqp=CJTm2oYG&rs=AOn4CLDPYsYhWIabKGG-FLh-pM8pkGv6TQ",
                "https://www.youtube.com/watch?v=AWkClbrkvEs"
            )
        )
        list.add(
            TipsModel(
                1,
                "9 Easy Male Grooming Tips | Men's Grooming",
                "https://i.ytimg.com/an_webp/mVJKf-Zxe5Q/mqdefault_6s.webp?du=3000&sqp=CJTm2oYG&rs=AOn4CLDPYsYhWIabKGG-FLh-pM8pkGv6TQ",
                "https://www.youtube.com/watch?v=AWkClbrkvEs"
            )
        )
        list.add(
            TipsModel(
                1,
                "9 Easy Male Grooming Tips | Men's Grooming",
                "https://i.ytimg.com/an_webp/mVJKf-Zxe5Q/mqdefault_6s.webp?du=3000&sqp=CJTm2oYG&rs=AOn4CLDPYsYhWIabKGG-FLh-pM8pkGv6TQ",
                "https://www.youtube.com/watch?v=AWkClbrkvEs"
            )
        )
        list.add(
            TipsModel(
                1,
                "9 Easy Male Grooming Tips | Men's Grooming",
                "https://i.ytimg.com/an_webp/mVJKf-Zxe5Q/mqdefault_6s.webp?du=3000&sqp=CJTm2oYG&rs=AOn4CLDPYsYhWIabKGG-FLh-pM8pkGv6TQ",
                "https://www.youtube.com/watch?v=AWkClbrkvEs"
            )
        )
        list.add(
            TipsModel(
                1,
                "9 Easy Male Grooming Tips | Men's Grooming",
                "https://i.ytimg.com/an_webp/mVJKf-Zxe5Q/mqdefault_6s.webp?du=3000&sqp=CJTm2oYG&rs=AOn4CLDPYsYhWIabKGG-FLh-pM8pkGv6TQ",
                "https://www.youtube.com/watch?v=AWkClbrkvEs"
            )
        )
        list.add(
            TipsModel(
                1,
                "9 Easy Male Grooming Tips | Men's Grooming",
                "https://i.ytimg.com/an_webp/mVJKf-Zxe5Q/mqdefault_6s.webp?du=3000&sqp=CJTm2oYG&rs=AOn4CLDPYsYhWIabKGG-FLh-pM8pkGv6TQ",
                "https://www.youtube.com/watch?v=AWkClbrkvEs"
            )
        )
        list.add(
            TipsModel(
                1,
                "9 Easy Male Grooming Tips | Men's Grooming",
                "https://i.ytimg.com/an_webp/mVJKf-Zxe5Q/mqdefault_6s.webp?du=3000&sqp=CJTm2oYG&rs=AOn4CLDPYsYhWIabKGG-FLh-pM8pkGv6TQ",
                "https://www.youtube.com/watch?v=AWkClbrkvEs"
            )
        )
        list.add(
            TipsModel(
                1,
                "9 Easy Male Grooming Tips | Men's Grooming",
                "https://i.ytimg.com/an_webp/mVJKf-Zxe5Q/mqdefault_6s.webp?du=3000&sqp=CJTm2oYG&rs=AOn4CLDPYsYhWIabKGG-FLh-pM8pkGv6TQ",
                "https://www.youtube.com/watch?v=AWkClbrkvEs"
            )
        )
        list.add(
            TipsModel(
                1,
                "9 Easy Male Grooming Tips | Men's Grooming",
                "https://i.ytimg.com/an_webp/mVJKf-Zxe5Q/mqdefault_6s.webp?du=3000&sqp=CJTm2oYG&rs=AOn4CLDPYsYhWIabKGG-FLh-pM8pkGv6TQ",
                "https://www.youtube.com/watch?v=AWkClbrkvEs"
            )
        )
        list.add(
            TipsModel(
                1,
                "9 Easy Male Grooming Tips | Men's Grooming",
                "https://i.ytimg.com/an_webp/mVJKf-Zxe5Q/mqdefault_6s.webp?du=3000&sqp=CJTm2oYG&rs=AOn4CLDPYsYhWIabKGG-FLh-pM8pkGv6TQ",
                "https://www.youtube.com/watch?v=AWkClbrkvEs"
            )
        )
        list.add(
            TipsModel(
                1,
                "9 Easy Male Grooming Tips | Men's Grooming",
                "https://i.ytimg.com/an_webp/mVJKf-Zxe5Q/mqdefault_6s.webp?du=3000&sqp=CJTm2oYG&rs=AOn4CLDPYsYhWIabKGG-FLh-pM8pkGv6TQ",
                "https://www.youtube.com/watch?v=AWkClbrkvEs"
            )
        )

    }


}