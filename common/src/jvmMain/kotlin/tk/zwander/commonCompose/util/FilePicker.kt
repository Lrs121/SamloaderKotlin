package tk.zwander.commonCompose.util

import dev.zwander.kotlin.file.PlatformFile
import io.github.vinceglb.filekit.core.FileKit
import io.github.vinceglb.filekit.core.pickFile

object FilePicker {
    suspend fun createFile(name: String): PlatformFile? {
        val dotIndex = name.lastIndexOf('.')
        val baseName = name.slice(0 until dotIndex)
        val extension = name.slice(dotIndex + 1 until name.length)

        return FileKit.saveFile(
            baseName = baseName,
            extension = extension,
        )?.let { PlatformFile(it.file) }
    }

    suspend fun pickFile(): PlatformFile? {
        return FileKit.pickFile()?.let { PlatformFile(it.file) }
    }
}
