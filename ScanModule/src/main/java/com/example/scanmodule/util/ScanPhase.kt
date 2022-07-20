package com.example.scanmodule.util

enum class ScanPhase (val description : String  ,val code : String) {
    RECEPTION ("RECEPTION","R"),
    CHARGEMENT ("CHARGEMENT","C"),
    DEGROUPEMENT ("DEGROUPEMENT","DR"),
    MISE_EN_RAYON ("MISE_EN_RAYON","MR"),
    PREPARATION ("PREPARATION","P"),
    LIVRAISON ("LIVRAISON","D")
}