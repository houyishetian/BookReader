package lin.com.bookreader.adapter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class BookDividerItemDecoration : RecyclerView.ItemDecoration() {
    private var outRectTop = 40
    private val paintStrokeWidth = 3f
    private val paint: Paint

    init {
        paint = Paint()
        paint.strokeWidth = paintStrokeWidth
        paint.isAntiAlias = true
        paint.color = Color.GRAY
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = outRectTop
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        parent.children.withIndex().forEach { (index, view) ->
            if (index == 0) {
                return@forEach
            }
            val left = view.paddingLeft.toFloat()
            val right = view.width.toFloat() - view.paddingRight
            val top = view.top - (outRectTop + paintStrokeWidth) / 2
            val bottom = top + paintStrokeWidth
            c.drawRect(left, top, right, bottom, paint)
        }
    }
}