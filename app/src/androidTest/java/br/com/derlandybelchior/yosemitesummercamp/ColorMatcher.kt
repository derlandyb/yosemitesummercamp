package br.com.derlandybelchior.yosemitesummercamp

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

fun withBackground(expectedReourceId: Int) : BoundedMatcher<View, View> {

    return object : BoundedMatcher<View, View>(View::class.java) {

        var actualColor: Int = 0
        var message: String = ""
        var expectedColor: Int = 0

        override fun describeTo(description: Description?) {
            if (actualColor != 0) {
                message = ("Background color did not match: Expected "
                        + java.lang.String.format("#%06X", 0xFFFFFF and expectedColor)
                        + " was " + String.format("#%06X", 0xFFFFFF and actualColor))
            }
            description?.appendText(message)
        }

        override fun matchesSafely(item: View?): Boolean {

            if (item?.background == null) {
                return false
            }
            val resources = item.context.resources
            expectedColor = ResourcesCompat.getColor(resources, expectedReourceId, null)

            actualColor = try {
                (item.background as ColorDrawable).color
            } catch (e: Throwable) {
                try {
                    (item.background as GradientDrawable).color!!.defaultColor
                } catch (e: Throwable) {
                    try {
                        val rippleDrawable = item.background as RippleDrawable
                        (rippleDrawable.findDrawableByLayerId(R.id.bt_continue_background) as GradientDrawable).color?.defaultColor ?: 0

                    } catch (e: NoSuchFieldException) {
                        e.printStackTrace()
                        0
                    } catch (e: IllegalAccessException) {
                        e.printStackTrace()
                        0
                    }

                } finally {
                    if (actualColor == expectedColor) {
                        Log.i(
                            "Success...:",
                            "Expected Color ${String.format("#%06X", (0xFFFFFF and expectedColor))}"
                                    + " Actual Color " + java.lang.String.format(
                                "#%06X",
                                0xFFFFFF and actualColor
                            )
                        )
                    }
                }
            }
            return actualColor == expectedColor
        }
    }
}