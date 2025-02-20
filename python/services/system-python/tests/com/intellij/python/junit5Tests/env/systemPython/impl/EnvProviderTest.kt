// Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.python.junit5Tests.env.systemPython.impl

import com.intellij.python.community.services.systemPython.SystemPythonService
import com.intellij.python.junit5Tests.framework.env.PyEnvTestCase
import com.intellij.python.junit5Tests.framework.env.PythonBinaryPath
import com.intellij.testFramework.common.timeoutRunBlocking
import com.jetbrains.python.PythonBinary
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@PyEnvTestCase
class EnvProviderTest {

  @Test
  fun testPythonProvider(@PythonBinaryPath python: PythonBinary): Unit = timeoutRunBlocking {
    val systemPythons = SystemPythonService().findSystemPythons()
    val systemPythonBinaries = systemPythons.map { it.pythonBinary }
    assertThat("No env python registered", systemPythonBinaries, Matchers.hasItem(python))

    if (systemPythons.size > 1) {
      val best = systemPythons.first()
      for (python in systemPythons.subList(1, systemPythonBinaries.size)) {
        Assertions.assertTrue(python.languageLevel <= best.languageLevel, "$best is the first, bust worse than $python")
      }
    }
  }
}