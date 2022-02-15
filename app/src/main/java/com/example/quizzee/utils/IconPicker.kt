package com.example.quizzee.utils

import com.example.quizzee.R


object IconPicker {

    val Icons =arrayOf(
        R.drawable.ic_iconfinder_bag,
        R.drawable.ic_iconfinder_bell,
        R.drawable.ic_iconfinder_board,
        R.drawable.ic_iconfinder_book,
        R.drawable.ic_iconfinder_building_school,
        R.drawable.ic_iconfinder_calculator,
        R.drawable.ic_iconfinder_pen,
        R.drawable.ic_iconfinder_ruler
    )
    var currentIcon=0
    fun getIcon():Int{
        currentIcon=(currentIcon+1)% Icons.size
        return Icons[currentIcon]
    }
}