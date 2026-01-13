package com.atr.schoolconnect.presentation.adapter

import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atr.schoolconnect.R
import com.atr.schoolconnect.databinding.SingleAcademicsSubjectLayoutBinding

class AcademicsSubjectAdapter(
    private val context: Context
) : RecyclerView.Adapter<AcademicsSubjectAdapter.ViewHolder>() {

    private var expandedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleAcademicsSubjectLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isExpanded = position == expandedPosition

        // Nested RecyclerView (init once)
        holder.binding.rec.apply {
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(context)
                adapter = AcademicsMarksAdapter(context)
                isNestedScrollingEnabled = false
            }
        }

        // Reset recycled state
        holder.binding.marksContainer.apply {
            clearAnimation()
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            visibility = if (isExpanded) View.VISIBLE else View.GONE
            alpha = if (isExpanded) 1f else 0f
        }

        // Smooth expand / collapse
        if (isExpanded) {
            holder.binding.marksContainer.smoothExpand()
        } else {
            holder.binding.marksContainer.smoothCollapse()
        }

        // Icon change (+ / -) + rotation
        holder.binding.image.apply {
            setImageResource(
                if (isExpanded) R.drawable.minus else R.drawable.plus
            )
            animate()
                .rotation(if (isExpanded) 180f else 0f)
                .setDuration(250)
                .setInterpolator(android.view.animation.DecelerateInterpolator())
                .start()
        }

        // Click
        holder.binding.image.setOnClickListener {
            val oldExpanded = expandedPosition
            expandedPosition = if (isExpanded) -1 else position

            if (oldExpanded != -1) notifyItemChanged(oldExpanded)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = 6

    class ViewHolder(val binding: SingleAcademicsSubjectLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}


fun View.smoothExpand() {
    visibility = View.VISIBLE
    alpha = 0f
    scaleY = 0.95f
    translationY = -10f

    animate()
        .alpha(1f)
        .scaleY(1f)
        .translationY(0f)
        .setDuration(280)
        .setInterpolator(android.view.animation.DecelerateInterpolator())
        .start()
}
fun View.smoothCollapse() {
    animate()
        .alpha(0f)
        .scaleY(0.95f)
        .translationY(-10f)
        .setDuration(220)
        .setInterpolator(android.view.animation.AccelerateInterpolator())
        .withEndAction {
            visibility = View.GONE
            alpha = 1f
            scaleY = 1f
            translationY = 0f
        }
        .start()
}
