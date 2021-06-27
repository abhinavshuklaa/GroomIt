package com.avenger.timesaver.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.avenger.timesaver.R
import com.avenger.timesaver.adapter.ShoppingItemAdapter
import com.avenger.timesaver.interfaces.tipsItemClickedListener
import com.avenger.timesaver.models.ShoppingItemModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_shop.*

@AndroidEntryPoint

class ShopFragment : Fragment(), tipsItemClickedListener {
    private var list = mutableListOf<ShoppingItemModel>()
    private lateinit var adapter: ShoppingItemAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildRecyclerDataForShop()
        setRecyclerAdapter()

    }

    private fun setRecyclerAdapter() {

        val layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        recyclerViewShoppingList.layoutManager = layoutManager
        adapter = ShoppingItemAdapter(list, this)
        recyclerViewShoppingList.adapter = adapter
    }

    private fun buildRecyclerDataForShop() {
        list = mutableListOf(
            ShoppingItemModel(
                1,
                "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                735,
                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                "men clothing",
                "https://www.amazon.in/dp/B08YZ5YM9Y/ref=cm_sw_r_wa_apa_glt_fabc_91EA6JXQCZ6W85GW70GS?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/71Zd2kYYZ2L._UL1500_.jpg"
            ),
            ShoppingItemModel(
                2,
                "Mens Casual Premium Slim Fit T-Shirts ",
                1499,
                "lim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket",
                "men clothing",
                "https://www.amazon.in/dp/B0963PVH66/ref=cm_sw_r_wa_apa_glt_fabc_F79TJP721DRG1YW8FP4J?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/61jQ8aC776S._UL1500_.jpg"
            ),
            ShoppingItemModel(
                3, "Mens Cotton Jacket",
                11489,
                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                "men clothing",
                "https://www.amazon.in/dp/B08YZ5YM9Y/ref=cm_sw_r_wa_apa_glt_fabc_91EA6JXQCZ6W85GW70GS?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/71nGzaDWhPL._SX425_.jpg"
            ),
            ShoppingItemModel(
                4,
                "Amazonite Bracelet",
                8016,
                "From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean's pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection.",
                "jewellery",
                "https://www.amazon.in/dp/B0846XM29Y/ref=cm_sw_r_wa_apa_glt_fabc_6N8KHM0Q3HBNXQHA7MMY?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/51IGM4ir8sL._UX395_.jpg"
            ),
            ShoppingItemModel(
                5,
                "Gold Stud Earrings for Women",
                18521,
                "Rose Gold Plated Double Flared Tunnel Plug Earrings. Made of 316L Stainless Steel",
                "jewellery",
                "https://www.amazon.in/dp/B08FD2HJP8/ref=cm_sw_r_wa_apa_glt_fabc_MQG3AJDTT2WTQ0462878?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/5137sXwE8IL._UL1000_.jpg"
            ),
            ShoppingItemModel(
                6, "Fastrack Watch",
                2095,
                "Casual Analog White Dial Men's", "men clothing",

                "https://www.amazon.in/dp/B00DQQN51Q/ref=cm_sw_r_wa_apa_glt_fabc_V9987FMFH520HPGNKPSE?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/714SXyEy-DL._UL1500_.jpg"
            ),
            ShoppingItemModel(
                7,
                "Veet Sensitive Touch Expert Trimmer for Face",
                1499,
                "Gentle hair removal and precise shaping for your delicate body parts, ideal for upper lip, side burns and eyebrows",
                "women clothing",
                "https://www.amazon.in/dp/B078H1XKBY/ref=cm_sw_r_wa_apa_glt_fabc_7Y3HJ0A9VT7ZEPRBTW5C?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/61ipGedk7IL._SL1000_.jpg"
            ),
            ShoppingItemModel(
                8,
                "MI Cordless Beard Trimmer ",
                1099,
                "Ultra powerful Battery with upto 60min of cordless usage with 2hour charge time",
                "men clothing",
                "https://www.amazon.in/dp/B08PS2KXJP/ref=cm_sw_r_wa_apa_glt_fabc_K3Y8GNE3QKB58FAG30YT?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/61TmtuN7yAL._SL1500_.jpg"
            ),
            ShoppingItemModel(
                9,
                "Womens T Shirt Casual",
                6664,
                "95%Cotton,5%Spandex, Features: Casual, Short Sleeve, Letter Print,V-Neck,Fashion Tees, The fabric is soft and has some stretch., Occasion: Casual/Office/Beach/School/Home/Street. Season: Spring,Summer,Autumn,Winter.",
                "women clothing",
                "https://www.amazon.in/dp/B07MKJQRX7/ref=cm_sw_r_wa_apa_glt_fabc_M5XP91GYM2TA46BAM9DD?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/71LYZBVjixL._UL1500_.jpg"
            ),
            ShoppingItemModel(
                10,
                "Reebok Shoes",
                941,
                "95%Cotton,5%Spandex, Features: Casual, Short Sleeve, Letter Print,V-Neck,Fashion Tees, The fabric is soft and has some stretch., Occasion: Casual/Office/Beach/School/Home/Street. Season: Spring,Summer,Autumn,Winter.",
                "men clothing",
                "https://www.amazon.in/dp/B082QJDZ51/ref=cm_sw_r_wa_apa_glt_fabc_G71MBY7K13RYEW7QYZM2?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/71Y3KlD3sqL._UL1500_.jpg"
            ),
            ShoppingItemModel(
                1,
                "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                735,
                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                "men clothing",
                "https://www.amazon.in/dp/B08YZ5YM9Y/ref=cm_sw_r_wa_apa_glt_fabc_91EA6JXQCZ6W85GW70GS?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/71Zd2kYYZ2L._UL1500_.jpg"
            ),

            ShoppingItemModel(
                1,
                "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                735,
                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                "men clothing",
                "https://www.amazon.in/dp/B08YZ5YM9Y/ref=cm_sw_r_wa_apa_glt_fabc_91EA6JXQCZ6W85GW70GS?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/71Zd2kYYZ2L._UL1500_.jpg"
            ),
            ShoppingItemModel(
                1,
                "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                735,
                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                "men clothing",
                "https://www.amazon.in/dp/B08YZ5YM9Y/ref=cm_sw_r_wa_apa_glt_fabc_91EA6JXQCZ6W85GW70GS?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/71Zd2kYYZ2L._UL1500_.jpg"
            ),
            ShoppingItemModel(
                1,
                "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                735,
                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                "men clothing",
                "https://www.amazon.in/dp/B08YZ5YM9Y/ref=cm_sw_r_wa_apa_glt_fabc_91EA6JXQCZ6W85GW70GS?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/71Zd2kYYZ2L._UL1500_.jpg"
            ),
            ShoppingItemModel(
                1,
                "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                735,
                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                "men clothing",
                "https://www.amazon.in/dp/B08YZ5YM9Y/ref=cm_sw_r_wa_apa_glt_fabc_91EA6JXQCZ6W85GW70GS?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/71Zd2kYYZ2L._UL1500_.jpg"
            ),

            ShoppingItemModel(
                1,
                "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                735,
                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                "men clothing",
                "https://www.amazon.in/dp/B08YZ5YM9Y/ref=cm_sw_r_wa_apa_glt_fabc_91EA6JXQCZ6W85GW70GS?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/71Zd2kYYZ2L._UL1500_.jpg"
            ),

            ShoppingItemModel(
                1,
                "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                735,
                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                "men clothing",
                "https://www.amazon.in/dp/B08YZ5YM9Y/ref=cm_sw_r_wa_apa_glt_fabc_91EA6JXQCZ6W85GW70GS?_encoding=UTF8&psc=1",
                "https://images-na.ssl-images-amazon.com/images/I/71Zd2kYYZ2L._UL1500_.jpg"
            )
        )


    }

    override fun onItemClicked(position: Int, content: String) {


    }


}