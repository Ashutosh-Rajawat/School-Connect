package com.atr.schoolconnect.presentation.fragment

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.atr.schoolconnect.R
import com.atr.schoolconnect.databinding.FragmentAcademicsBinding
import com.atr.schoolconnect.presentation.adapter.AcademicsSubjectAdapter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class AcademicsFragment : Fragment() {

    private lateinit var binding: FragmentAcademicsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAcademicsBinding.inflate(layoutInflater, container, false)

        val classList = listOf(
            "Select Class",
            "Current Year (2024-25)",
            "10th Class (2023-24)",
            "09th Class (2022-23)"
        )

        val adapterSpinner = object : ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, classList) {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = layoutInflater.inflate(R.layout.item_spinner_selected, parent, false)
                view.findViewById<TextView>(R.id.tvText).text = classList[position]
                return view
            }
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = layoutInflater.inflate(R.layout.item_spinner_dropdown, parent, false)
                view.findViewById<TextView>(R.id.tvDropDown).text = classList[position]
                return view
            }
        }

        binding.spinnerClass.adapter = adapterSpinner
        binding.spinnerClass.setSelection(0)

        binding.spinnerClass.post {
            binding.spinnerClass.dropDownVerticalOffset = binding.spinnerClass.height + 6.dp
        }

        binding.rec.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            adapter = AcademicsSubjectAdapter(requireContext())


        }
        setupPieChart(binding.pieChart)


        return binding.root
    }
    private fun setupPieChart(pieChart: PieChart) {

        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(94f, "Present"))
        entries.add(PieEntry(6f, "Leave"))
        entries.add(PieEntry(12f, "Holiday"))

        val dataSet = PieDataSet(entries, "")

        dataSet.colors = listOf(
            Color.parseColor("#5A54FF"), // Purple (Present)
            Color.parseColor("#E74C3C"), // Red (Leave)
            Color.parseColor("#1E8E8E")  // Teal (Holiday)
        )

        dataSet.sliceSpace = 6f
        dataSet.selectionShift = 8f

        val data = PieData(dataSet)
        data.setDrawValues(false)

        pieChart.apply {
            this.data = data
            description.isEnabled = false
            legend.isEnabled = false

            isDrawHoleEnabled = true
            holeRadius = 70f
            transparentCircleRadius = 0f
            setHoleColor(Color.TRANSPARENT)

            setUsePercentValues(false)
            setDrawEntryLabels(false)

            animateY(1000, Easing.EaseInOutQuad)
            invalidate()
        }
    }

    val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()


}