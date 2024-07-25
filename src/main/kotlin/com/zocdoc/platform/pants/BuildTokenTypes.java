package com.zocdoc.platform.pants;

import com.intellij.psi.tree.IElementType;

public interface BuildTokenTypes {
    IElementType KEYWORD = new IElementType("KEYWORD", BuildLanguage.INSTANCE);
    IElementType IDENTIFIER = new IElementType("IDENTIFIER", BuildLanguage.INSTANCE);
    IElementType COMMENT = new IElementType("COMMENT", BuildLanguage.INSTANCE);
    // ..
}
