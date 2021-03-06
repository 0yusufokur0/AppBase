package com.resurrection.base.utils

import com.resurrection.base.extensions.tryCatch
import java.io.File
import java.io.FileWriter

fun createFile(path: String, sFileName: String?, sBody: MutableList<String>) {
    tryCatch {
        val gpxfile = File(path, "$sFileName.txt")
        val writer = FileWriter(gpxfile)
        sBody.forEach {
            writer.append(it + "\n")
        }
        writer.flush()
        writer.close()
    }
}

fun createFolder(rootPath: String, folderName: String): String {
    val tempFile = File(rootPath, folderName)
    return if (!tempFile.exists()) {
        tempFile.mkdir()
        "$rootPath/$folderName"
    } else rootPath
}