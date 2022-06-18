package org.intellij.sdk.language

import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.vfs.VirtualFile
import java.nio.charset.StandardCharsets

object SimpleLanguage : Language("Simple")


object SimpleFileType1 : LanguageFileType(SimpleLanguage) {
    override fun getDisplayName() = "Simple"
    override fun getName() = "Simple"
    override fun getDescription() = "Simple"
    override fun getDefaultExtension() = "simple"
    override fun getIcon() = SimpleIcons.FILE
    override fun getCharset(file: VirtualFile, content: ByteArray): String = StandardCharsets.UTF_8.name()
}
