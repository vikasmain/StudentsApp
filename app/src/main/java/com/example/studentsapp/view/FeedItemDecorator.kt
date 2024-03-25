package com.example.studentsapp.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FeedItemDecorator(
    private val horizontalSpace: Int,
    private val verticalSpace: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.right = horizontalSpace
        outRect.left = horizontalSpace
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = verticalSpace
        }
        outRect.bottom = verticalSpace
    }
}