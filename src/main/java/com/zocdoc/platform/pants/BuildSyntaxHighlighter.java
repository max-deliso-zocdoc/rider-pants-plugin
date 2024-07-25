package com.zocdoc.platform.pants;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class BuildSyntaxHighlighter extends SyntaxHighlighterBase {
    private static final Logger LOG = Logger.getInstance(BuildSyntaxHighlighter.class);

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        LOG.info("constructing highlighting lexer");

        return new BuildLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        LOG.info("constructing highlighting for token type: " + tokenType);

        if (tokenType.equals(BuildTokenType.IDENTIFIER)) {
            return new TextAttributesKey[]{DefaultLanguageHighlighterColors.IDENTIFIER};
        } else if (tokenType.equals(BuildTokenType.NUMBER)) {
            return new TextAttributesKey[]{DefaultLanguageHighlighterColors.NUMBER};
        } else if (tokenType.equals(BuildTokenType.STRING)) {
            return new TextAttributesKey[]{DefaultLanguageHighlighterColors.STRING};
        } else if (tokenType.equals(BuildTokenType.LBRACKET) || tokenType.equals(BuildTokenType.RBRACKET)) {
            return new TextAttributesKey[]{DefaultLanguageHighlighterColors.BRACKETS};
        } else if (tokenType.equals(BuildTokenType.LPAREN) || tokenType.equals(BuildTokenType.RPAREN)) {
            return new TextAttributesKey[]{DefaultLanguageHighlighterColors.PARENTHESES};
        } else if (tokenType.equals(BuildTokenType.COMMA)) {
            return new TextAttributesKey[]{DefaultLanguageHighlighterColors.COMMA};
        }

        return new TextAttributesKey[0];
    }
}
