package com.example.recipe.ui.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.daimajia.numberprogressbar.NumberProgressBar
import com.example.recipe.R
import com.example.recipe.utils.Constants
import com.example.recipe.utils.Status
import com.timqi.sectorprogressview.ColorfulRingProgressView
import com.wang.avi.AVLoadingIndicatorView
import kotlin.math.roundToInt

// Recycler
@BindingAdapter("rv_visibility")
fun setProgressVisibility(recyclerView: RecyclerView?, status : Status?) {
    status?.let {
        recyclerView?.let {
            it.isVisible = when (status) {
                Status.RUNNING -> false
                Status.SUCCESS -> true
                Status.FAILED -> false
                Status.NOT_FOUND -> false
            }
        }
    }
}

@BindingAdapter("recycler_data")
fun <T> setRecyclerView(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is IBindableAdapter<*>) {
        (recyclerView.adapter as IBindableAdapter<T>).update(data)
    }
}

// Loading
@BindingAdapter("progress_visibility")
fun setProgressVisibility(indicatorView: AVLoadingIndicatorView?, status : Status?) {
    status?.let {
        indicatorView?.let {
            it.isVisible = when (status) {
                Status.RUNNING -> true
                Status.SUCCESS -> false
                Status.FAILED -> false
                Status.NOT_FOUND -> false
            }
        }
    }
}

// Progress
@BindingAdapter("progress", "overalCals")
fun setRingProgress(progress : ColorfulRingProgressView, d : Double, overalCals : Double) {
    progress.percent = ((d * 100) / overalCals).toFloat()
}

@BindingAdapter("progress", "overal")
fun setFatNumberProgress(progress : NumberProgressBar, d : Double, overal : Double) {
    progress.progress = ((d * 100) / overal).roundToInt()
}

//Image
@BindingAdapter("source")
fun loadImage(view: ImageView, url: String?) {
    if (url == null)
        Glide.with(view)
            .load(R.drawable.ic_food)
            .into(view)
    else
        Glide.with(view)
            .load(url)
            .apply(RequestOptions.centerCropTransform())
            .into(view)
}

//Text
@BindingAdapter("text")
fun setDoubleText(textView : TextView, d : Double) {
    textView.text = d.roundToInt().toString()
}

@BindingAdapter("text")
fun setLongText(textView : TextView, s : String) {
    if (s.length > 22)
        textView.text = s.take(21) + "..."
    else
        textView.text = s
}

@BindingAdapter("value", "overal")
fun setTextWithSlash(textView: TextView, d : Double, overal : Double) {
    textView.text = d.roundToInt().toString() + "/" + overal.roundToInt().toString()
}

@BindingAdapter("text")
fun setIntText(textView : TextView, i : Int) {
    textView.text = i.toString()
}

@BindingAdapter("valueCals")
fun setCalsText(textView: TextView, d : Double) {
    textView.text = "Calories: " + d.roundToInt().toString()
}

@BindingAdapter("valueProtein")
fun setProteinText(textView: TextView, d : Double) {
    textView.text = "Protein: " + d.roundToInt().toString()
}

@BindingAdapter("valueFat")
fun setFatText(textView: TextView, d : Double) {
    textView.text = "Fat: " + d.roundToInt().toString()
}

@BindingAdapter("valueCarbs")
fun setCarbsText(textView: TextView, d : Double) {
    textView.text = "Carbs: " + d.roundToInt().toString()
}

@BindingAdapter("valueGrams")
fun setGramsText(textView: TextView, d : Double) {
    textView.text = "Grams: " + d.roundToInt().toString()
}
