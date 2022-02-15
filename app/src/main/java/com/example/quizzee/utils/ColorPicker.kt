package com.example.quizzee.utils

object ColorPicker {
    val colors=arrayOf("#FF03DAC5","#FFBB86FC","#FFFFFFFF","#FF018786","#FF69B4","#FF1493","#008000","#FFA500")
    var currentColorIndex=0
    fun getColor():String{
        currentColorIndex=(currentColorIndex+1)% colors.size
        return colors[currentColorIndex]
    }
}