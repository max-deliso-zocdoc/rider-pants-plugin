package com.zocdoc.platform.pants;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
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
        if (tokenType.equals(BuildTokenType.IDENTIFIER)) {
            return new TextAttributesKey[]{DefaultLanguageHighlighterColors.IDENTIFIER};
        } else if (tokenType.equals(BuildTokenType.NUMBER)) {
            return new TextAttributesKey[]{DefaultLanguageHighlighterColors.NUMBER};
        } else if (tokenType.equals(BuildTokenType.STRING)) {
            return new TextAttributesKey[]{DefaultLanguageHighlighterColors.STRING};
        }

        return new TextAttributesKey[0];
    }
}
