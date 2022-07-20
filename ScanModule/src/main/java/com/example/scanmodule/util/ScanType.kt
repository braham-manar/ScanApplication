package com.example.scanmodule.util

enum class ScanType (val description : String ,val color : String ,val code : String) {
    CONFORM ("CONFORME","#00ff26","AR"),
    RESERVE ("RESERVE","#ff9500","LA"),
    REFUS ("REFUS","#ff0000","LR")
}