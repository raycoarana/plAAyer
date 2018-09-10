package com.raycoarana.plaayer.core.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import javax.inject.Inject

class Database @Inject constructor() {

    private var sql: SQLiteDatabase? = null

    fun use(path: String, command: (db: Database) -> Unit) {
        sql = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE) ?: throw Exception("Can not open SQLite database at $path")
        command.invoke(this)
        sql?.close()
    }

    fun executeSelect(query: String, args: Array<String>): List<Map<String, Any?>> =
            sql?.rawQuery(query, args)?.use { cursor ->
                val results = ArrayList<Map<String, Any?>>()
                while (cursor.moveToNext()) {
                    val item = cursor.columnNames.mapIndexed { index, name ->
                        val value: Any? = when (cursor.getType(index)) {
                            Cursor.FIELD_TYPE_INTEGER -> cursor.getInt(index)
                            Cursor.FIELD_TYPE_FLOAT -> cursor.getDouble(index)
                            Cursor.FIELD_TYPE_BLOB -> cursor.getBlob(index)
                            Cursor.FIELD_TYPE_STRING -> cursor.getString(index)
                            else -> null
                        }
                        Pair(name ?: index.toString(), value)
                    }.associate { it }
                    results.add(item)
                }
                return results
            } ?: emptyList()

    fun execute(query: String, vararg args: Any) =
            if (args.isEmpty()) sql?.execSQL(query) else sql?.execSQL(query, args)
}
