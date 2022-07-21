package dk.itu.garbage

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.EditText
import android.os.Bundle
import dk.itu.garbage.R
import dk.itu.garbage.ItemsDB
import dk.itu.garbage.MainActivity
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.Button
import dk.itu.garbage.AddActivity

class MainActivity : AppCompatActivity() {
    //Shopping V1
    // GUI variables
    private lateinit var listItems: Button
    private lateinit var addNew: Button
    private lateinit var items: TextView
    private lateinit var itemsEdit: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.garbage)
        items = findViewById(R.id.items)
        itemsEdit = findViewById(R.id.plain_text_input)
        listItems = findViewById(R.id.list_button)
        addNew = findViewById(R.id.add_new)
        ItemsDB.initialize(this@MainActivity)
        itemsDB = ItemsDB.get()
        items.visibility = View.GONE
        listItems.setOnClickListener(View.OnClickListener {
            println(itemsDB?.listItems())
            itemsEdit.visibility = View.GONE
            items.visibility = View.VISIBLE
            items.setBackgroundColor(Color.parseColor("#FFFFFF"))
            val msg = itemsDB?.garbageLookup(itemsEdit.text.toString())?.trim { it <= ' ' }
            items.text = msg
        }
        )
        addNew.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)
        })
    }

    companion object {
        // Model: Database of items
        private var itemsDB: ItemsDB<*>? = null
    }
}