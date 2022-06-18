// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.intellij.sdk.language

import com.intellij.testFramework.ParsingTestCase
import org.intellij.sdk.language.SimpleParserDefinition

class SimpleParsingTest : ParsingTestCase("", "simple", SimpleParserDefinition()) {
    fun testParsingTestData() {
        doTest(true)
    }

    /**
     * @return path to test data file directory relative to root of this module.
     */
    override fun getTestDataPath(): String {
        return "src/test/kotlin/org/intellij/sdk/language/testData/"
    }

    override fun skipSpaces(): Boolean {
        return true
    }

    override fun includeRanges(): Boolean {
        return true
    }
}




