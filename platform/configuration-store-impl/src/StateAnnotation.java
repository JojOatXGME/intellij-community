// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.configurationStore;

import com.intellij.openapi.components.SettingsCategory;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;

@SuppressWarnings("ClassExplicitlyAnnotation")
class StateAnnotation implements State {
  private final String name;
  private final Storage @NotNull [] storages;

  public StateAnnotation(@NotNull String name, @NotNull Storage storage) {
    this.name = name;
    storages = new Storage[]{storage};
  }

  @Override
  public @NotNull String name() {
    return name;
  }

  @Override
  public Storage @NotNull [] storages() {
    return storages;
  }

  @Override
  public boolean reloadable() {
    return false;
  }

  @Override
  public boolean defaultStateAsResource() {
    return false;
  }

  @Override
  public @NotNull String additionalExportDirectory() {
    return "";
  }

  @Deprecated
  @Override
  public String additionalExportFile() {
    return "";
  }

  @Override
  public Class<? extends NameGetter> presentableName() {
    return null;
  }

  @Override
  public boolean externalStorageOnly() {
    return false;
  }

  @Override
  public boolean reportStatistic() {
    return false;
  }

  @Override
  public boolean allowLoadInTests() {
    return false;
  }

  @Override
  public boolean useLoadedStateAsExisting() {
    return true;
  }

  @Override
  public boolean getStateRequiresEdt() {
    return false;
  }

  @Override
  public SettingsCategory category() {
    return SettingsCategory.OTHER;
  }

  @Override
  public boolean exportable() {
    return false;
  }

  @Override
  public boolean perClient() {
    return false;
  }

  @Override
  public Class<? extends Annotation> annotationType() {
    throw new UnsupportedOperationException("Method annotationType not implemented in " + getClass());
  }
}
