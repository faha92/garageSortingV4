package dk.itu.garbage

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.EditText
import android.os.Bundle
import dk.itu.garbage.R
import dk.itu.garbage.AddActivity
import dk.itu.garbage.ItemsDB
import android.content.Intent
import android.view.View
import android.widget.Button
import dk.itu.garbage.MainActivity

class AddActivity : AppCompatActivity() {
    //Garbage V3
    // GUI variables
    private lateinit var message: TextView
    private lateinit var addNew: Button
    private var what: EditText? = null
    private var where: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add)
        message = findViewById(R.id.message)
        message.text = "Insert new item:"
        what = findViewById(R.id.what)
        where = findViewById(R.id.where)
        addNew = findViewById(R.id.add_new)
        addNew.setOnClickListener(View.OnClickListener {
            itemsDB = ItemsDB.get()
            itemsDB?.addItem(
                what.toString().trim { it <= ' ' },
                where.toString().trim { it <= ' ' })
            message = findViewById(R.id.message)
            message.text = "1 new item was added"
            val intent = Intent(this@AddActivity, MainActivity::class.java)
            startActivity(intent)
        }
        )
    }

    companion object {
        // Model: Database of items
        private var itemsDB: ItemsDB<*>? = null
    }
}