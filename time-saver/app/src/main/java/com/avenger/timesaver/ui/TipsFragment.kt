package com.avenger.timesaver.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.avenger.timesaver.adapter.TipsAdapter
import com.avenger.timesaver.R
import com.avenger.timesaver.interfaces.tipsItemClickedListener
import com.avenger.timesaver.models.TipsModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tips.*

@AndroidEntryPoint

class TipsFragment : Fragment(), tipsItemClickedListener {
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
        adapter = TipsAdapter(list, this)
        recyclerView.adapter = adapter

    }

    private fun buildTipsModelList() {
        list.add(
            TipsModel(
                1,
                "9 Easy Male Grooming Tips | Men's Grooming",
                "https://i.ytimg.com/vi/AWkClbrkvEs/maxresdefault.jpg",
                "https://www.youtube.com/watch?v=AWkClbrkvEs"
            )
        )
        list.add(
            TipsModel(
                2,
                "IMPORTANT Grooming Tips For GIRLS To Look ATTRACTIVE!",
                "https://i.ytimg.com/vi/Kte162x2q4g/maxresdefault.jpg",
                "https://www.youtube.com/watch?v=Kte162x2q4g"
            )
        )
        list.add(
            TipsModel(
                3,
                "30 TIME-SAVING BEAUTY TIPS",
                "https://i.ytimg.com/vi/GQseQO7LgpY/maxresdefault.jpg",
                "https://www.youtube.com/watch?v=GQseQO7LgpY"
            )
        )
        list.add(
            TipsModel(
                4,
                "Hair Care Tips For Men | Men's Grooming ",
                "https://i.ytimg.com/vi/mVJKf-Zxe5Q/maxresdefault.jpg",
                "https://www.youtube.com/watch?v=mVJKf-Zxe5Q"
            )
        )
        list.add(
            TipsModel(
                5,
                "7 Tips To Look Handsome Instantly | Indian Men's Grooming",
                "https://i.ytimg.com/vi/8gPc-p1scVc/maxresdefault.jpg",
                "https://www.youtube.com/watch?v=8gPc-p1scVc"
            )
        )
        list.add(
            TipsModel(
                6,
                "How to Tie a Windsor Knot | Men's Fashion",
                "https://i.ytimg.com/vi/T0NPYZyI7V8/maxresdefault.jpg",
                "https://www.youtube.com/watch?v=T0NPYZyI7V8"
            )
        )
        list.add(
            TipsModel(
                7,
                "Easy Ways to Style Jeans with T-Shirts | Men’s Fashion ",
                "https://i.ytimg.com/vi/-jGSNFt-6Ic/maxresdefault.jpg",
                "https://www.youtube.com/watch?v=-jGSNFt-6Ic"
            )
        )
        list.add(
            TipsModel(
                8,
                "A Guide to French Girl Style ",
                "https://i.ytimg.com/vi/mc0aj9ERr0E/maxresdefault.jpg",
                "https://www.youtube.com/watch?v=mc0aj9ERr0E"
            )
        )
        list.add(
            TipsModel(
                9,
                "What HR Managers Learn About You By Looking at Your Clothes",
                "https://i.ytimg.com/vi/t0fM8sCZZes/maxresdefault.jpg",
                "https://www.youtube.com/watch?v=t0fM8sCZZes"
            )
        )
        list.add(
            TipsModel(
                10,
                "7 body language tips to impress at your next job interview",
                "data:image/jpeg;base64,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",
                "https://www.youtube.com/watch?v=PCWVi5pAa30"
            )
        )




    }

    override fun onItemClicked(position: Int, content: String) {
        Toast.makeText(context, "Response : $position", Toast.LENGTH_SHORT).show()

    }


}