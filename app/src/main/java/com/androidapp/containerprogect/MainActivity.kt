package com.androidapp.containerprogect

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidapp.containerprogect.adapter.FingerprintAdapter
import com.androidapp.containerprogect.adapter.fingerprint.PostFingerprint
import com.androidapp.containerprogect.adapter.fingerprint.TitleFingerprint
import com.androidapp.containerprogect.databinding.ActivityMainBinding
import com.androidapp.containerprogect.utils.getRandomFeed

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FingerprintAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        adapter = FingerprintAdapter(getFingerprints())

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        adapter.setItem(getRandomFeed())
    }

    private fun getFingerprints() = listOf(
        TitleFingerprint(),
        PostFingerprint()
    )
}