package com.zocdoc.platform.pants;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class BuildFileType extends LanguageFileType {
    public static final BuildFileType INSTANCE = new BuildFileType();

    private BuildFileType() {
        super(BuildLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "Pants Build File";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "Pants build file";
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return "";
    }

    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/META-INF/pluginIcon.svg", getClass());
    }
}
