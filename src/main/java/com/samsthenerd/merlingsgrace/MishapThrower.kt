package com.samsthenerd.merlingsgrace

import at.petrak.hexcasting.api.casting.mishaps.Mishap

class MishapThrower {
    companion object {
        @JvmStatic
        fun throwMishap(mishap: Mishap) {
            throw mishap
        }
    }
}