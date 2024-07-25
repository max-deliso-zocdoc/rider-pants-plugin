package com.zocdoc.platform.pants;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class BuildSyntaxHighlighter extends SyntaxHighlighterBase {
    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new BuildLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {

        // todo: more mapping from i element type to other stuff

        return new TextAttributesKey[0];
    }
}
