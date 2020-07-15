package br.com.derlandybelchior.yosemitesummercamp

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatcher(private val recyclerViewId: Int) {

    fun atPosition(position: Int) = atPositionOnView(position, -1)

    fun atPositionOnView(position: Int, targetViewId: Int) : Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            private var resources: Resources? = null
            private var childView: View? = null

            override fun describeTo(description: Description?) {
                var idDescription: String = recyclerViewId.toString()

                resources?.apply {
                    idDescription = try {
                        getResourceName(recyclerViewId)
                    } catch (e: Resources.NotFoundException) {
                        String.format("%s (resource name not found", arrayOf(Integer.valueOf(recyclerViewId)))
                    }
                }
                description?.appendText("with id: $idDescription")
            }

            override fun matchesSafely(item: View?): Boolean {
                resources = item?.resources

                if(childView == null) {
                    val recyclerView = item?.rootView?.findViewById<RecyclerView>(recyclerViewId)
                    if(recyclerView != null && recyclerView.id == recyclerViewId) {
                        childView = recyclerView.findViewHolderForAdapterPosition(position)?.itemView
                    }else{
                        return false
                    }
                }

                return if(targetViewId == -1) {
                    item == childView
                } else {
                    val targetView = childView?.findViewById<View>(targetViewId)
                    item == targetView
                }
            }

        }
    }
}