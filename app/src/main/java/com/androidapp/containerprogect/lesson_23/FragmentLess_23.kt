package com.androidapp.containerprogect.lesson_23

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class FragmentLess_23 : Fragment(R.layout.fragment_less_9) {

    private val viewModelLess by viewModels<ViewModelLess23>()
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelLess

        view.findViewById<Button>(R.id.btn_run).setOnClickListener {
            viewModelLess.liveData.observe(viewLifecycleOwner) {
                log(it)
            }
        }

/*        lifecycleScope.launch {
            while (true) {
                delay(1_000)
                log("Work")
            }
        }*/

/*        log("Dispatchers.Main - before")
        scope.launch {
            log("Dispatchers.Main - launch")
        }
        log("Dispatchers.Main - after")*/
    }
}