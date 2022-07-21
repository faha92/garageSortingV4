package dk.itu.garbage

class Item(what: String?, where: String?) {
    var what: String? = null
    var where: String? = null
    override fun toString(): String {
        return oneLine("", " in: ")
    }

    fun oneLine(pre: String, post: String): String {
        return pre + what + post + where
    }

    init {
        this.what = what
        this.where = where
    }
}