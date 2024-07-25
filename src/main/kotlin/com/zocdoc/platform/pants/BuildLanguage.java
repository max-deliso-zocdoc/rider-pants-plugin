package com.zocdoc.platform.pants;

import com.intellij.lang.Language;

public class BuildLanguage extends Language {
    public static final BuildLanguage INSTANCE = new BuildLanguage();

    private BuildLanguage() {
        super("Build");
    }
}
