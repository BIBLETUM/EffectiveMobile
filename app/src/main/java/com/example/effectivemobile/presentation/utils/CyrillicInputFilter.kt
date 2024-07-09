package com.example.effectivemobile.presentation.utils

import android.text.InputFilter
import android.text.Spanned


class CyrillicInputFilter : InputFilter {
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence {
        for (i in start until end) {
            if (!source[i].toString().matches("[\\u0400-\\u04FF]+".toRegex())) {
                return ""
            }
        }
        return ""
    }
}