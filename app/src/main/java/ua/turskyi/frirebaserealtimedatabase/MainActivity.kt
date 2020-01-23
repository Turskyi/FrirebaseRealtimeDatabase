package ua.turskyi.frirebaserealtimedatabase

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.database.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private var mEditText: EditText? = null
    private var mTextView: TextView? = null
    var mDatabase: FirebaseDatabase? = null
    var mMessageRef: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)
        mEditText = findViewById<View>(R.id.editText) as EditText
        mTextView = findViewById<View>(R.id.textView) as TextView
    }

    override fun onStart() {
        super.onStart()
//         Создаем блок message в базе
        mDatabase = FirebaseDatabase.getInstance()
        mMessageRef = mDatabase!!.getReference("message")
//        addNewCat("0" , "cat", 3)
        mMessageRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()){
                    val value = dataSnapshot.getValue(String::class.java)!!
                    mTextView!!.text = value
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                mTextView!!.text = getString(R.string.error, databaseError.toException())
            }
        })

//        mMessageRef!!.child("cats").child("1").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val cat = dataSnapshot.getValue(Cat::class.java)!!
//                mTextView!!.text = cat.catName
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                mTextView!!.text = getString(R.string.error, databaseError.toException())
//            }
//        })

//        mMessageRef!!.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val nameValue =
//                    dataSnapshot.child("cats").child("0").child("catName").getValue(
//                        String::class.java
//                    )!!
//                val ageValue = dataSnapshot.child("cats").child("0").child("age")
//                    .getValue(Int::class.java)!!
//                mTextView!!.text = getString(R.string.name_and_age, nameValue, ageValue.toString())
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                mTextView!!.text = getString(R.string.error, databaseError.toException())
//            }
//        })
    }

    fun onClick(view: View?) { // Создаем блок message в базе

//        mDatabase = FirebaseDatabase.getInstance()
//        mMessageRef = mDatabase!!.getReference("message")

//        val catName = mEditText!!.text.toString()
//        val age = (Math.random() * 20).toInt()
//        val catsRef = mMessageRef!!.child("cats")
//        val uniqRef = catsRef.push()
//        val cat = Cat(catName, age)
//        uniqRef.setValue(cat)

//        mMessageRef?.child("cats")?.child("1")?.removeValue()

        if (mEditText!!.text.isNotEmpty()) {
            mMessageRef?.setValue(mEditText!!.text.toString())
        } else {
            mMessageRef?.setValue("Hello Kitty!")
        }
    }

    private fun addNewCat(catId: String, name: String, age: Int) {
        val cat = Cat(name, age)
        mMessageRef!!.child("cats").child(catId).setValue(cat)
    }
}

