package dk.itu.garbage

import android.content.Context
import dk.itu.garbage.ItemsDB
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.HashMap

class ItemsDB<r> {
    private val itemsDB = HashMap<String, String?>()

    private constructor(context: Context) {
        fillItemsDB(context, "items.txt")
    }

    constructor() {}

    fun listItems(): String {
        var r = ""
        for ((key, value) in itemsDB) r = """$r
 Buy $key in: $value"""
        return r
    }

    fun size(): Int {
        return itemsDB.size
    }

    fun addItem(what: String, where: String?) {
        itemsDB[what] = where
    }

    fun removeItem(what: String) {
        if (itemsDB[what] != null) itemsDB.remove(what)
    }

    private fun fillItemsDB(context: Context, filename: String?) {
        try {
            val reader = BufferedReader(
                InputStreamReader(context.assets.open(filename!!))
            )
            var line = reader.readLine()
            while (line != null) {
                val gItem = line.split(",").toTypedArray()
                itemsDB[gItem[0]] = gItem[1]
                line = reader.readLine()
            }
        } catch (e: IOException) {  // Error occurred when opening raw file for reading.
        }
    }

    fun garbageLookup(what: String): String {
        return what + " should be placed in: " + itemsDB[what]
    }

    companion object {
        private var sItemsDB: ItemsDB<*>? = null
        fun initialize(context: Context) {
            if (sItemsDB == null) sItemsDB = ItemsDB<Any?>(context)
        }

        fun get(): ItemsDB<*>? {
            checkNotNull(sItemsDB) { "ItemsDB must be initialized" }
            return sItemsDB
        }
    }
}