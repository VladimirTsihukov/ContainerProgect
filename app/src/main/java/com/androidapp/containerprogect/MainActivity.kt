package com.androidapp.containerprogect

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var stringJSON: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        processionArrayJSON()
    }

    private fun processionArrayJSON() {
        val rawJSON = ("[{\"MemberID\":\"1\",\"Name\":\"Мурзик\",\"Tel\":\"4954876107\"}"
                + ",{\"MemberID\":\"2\",\"Name\":\"Барсик\",\"Tel\":\"4954780121\"}"
                + ",{\"MemberID\":\"3\",\"Name\":\"Рыжик\",\"Tel\":\"4954543211\"}]")

        val data = JSONArray(rawJSON)
        val arrayList = arrayListOf<HashMap<String, String>>()
        val map = hashMapOf<String, String>()
        for (i in 0 until data.length()) {
            val jsonObject = data.getJSONObject(i)
            map["MemberID"] = jsonObject.getString("MemberID")
            map["Name"] = jsonObject.getString("Name")
            map["Tel"] = jsonObject.getString("Tel")
            arrayList.add(map)
        }

        val test = arrayOf(R.id.item_textViewMemberID,
            R.id.item_textViewName, R.id.item_textViewNumber)

     /*   val simpleAdapter = SimpleAdapter(this, arrayList,
            R.layout.list_item,  arrayOf("MemberID",
                "Name", "Tel"),  test)*/
    }

    @SuppressLint("SetTextI18n")
    private fun createJSONSimple1() {
        stringJSON = getString(R.string.simple_json)
        val jsonObject = JSONObject(stringJSON)
        val resultJSON = jsonObject.getJSONObject("results")
        val mySiteName = resultJSON.getString("sitename")
        val myURL = resultJSON.getString("url")

        src_textView.text = "jsonObject:\n$jsonObject"
        object_textView.text = "results:\n$resultJSON"
        result_textView.text = "Имя сайта:\n$mySiteName"
        url_textView.text = "Адрес сайта:\n$myURL"
    }

    @SuppressLint("SetTextI18n")
    private fun createJSONSimple2() {
        stringJSON = getString(R.string.simple_json2)
        val jsonObject = JSONObject(stringJSON)
        val resultJSON = jsonObject.getJSONObject("results")
        val mySiteName = resultJSON.getString("sitename")
        val myURL = resultJSON.getString("url")

        result_textView.text = "Имя сайта:\n$mySiteName"
        url_textView.text = "Адрес сайта:\n$myURL"

        var stringArrayElement = "\n"
        val jsonArray = resultJSON.getJSONArray("array")

        for (i in 0 until jsonArray.length()) {
            val arrayElement = jsonArray.getJSONObject(i)
            stringArrayElement += arrayElement.getString("element") + "\n"
        }

        json_array.text = stringArrayElement
    }


    private fun createJSONObject() {
        val jsonObject = JSONObject()
        jsonObject.put("id", 10)
        jsonObject.put("name", "Tom")

        name.text = jsonObject.getString("name")
        age.text = jsonObject.getInt("id").toString()
    }

    private fun createJSONArray() {
        val jsonArray = JSONArray()
        val jsonObject = JSONObject()

        jsonObject.put("id", 1)
        jsonObject.put("name", "Tom")
        jsonObject.put("value", 325)

        jsonArray.put(jsonObject)

        name.text = "${jsonArray.getJSONObject(0).get("name")}"
    }
}