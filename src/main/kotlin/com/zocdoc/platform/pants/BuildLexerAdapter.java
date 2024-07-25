package com.zocdoc.platform.pants;

import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerPosition;
import com.intellij.psi.tree.IElementType;
import com.zocdoc.grammar.PantsBuildLexer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BuildLexerAdapter extends Lexer {
    private final PantsBuildLexer antlrLexer;

    public BuildLexerAdapter() {
        antlrLexer = new com.zocdoc.grammar.PantsBuildLexer(null);
    }

    @Override
    public void start(@NotNull CharSequence buffer,
                      int startOffset,
                      int endOffset,
                      int initialState) {
        antlrLexer.setInputStream(new ANTLRInputStream(buffer.toString()));
        antlrLexer.reset();
        antlrLexer.setState(initialState);
    }

    @Override
    public int getState() {
        return antlrLexer.getState();
    }

    @Override
    public @Nullable IElementType getTokenType() {
        final Token token = antlrLexer.nextToken();
        return BuildTokenType.getTokenType(token.getType());
    }

    @Override
    public int getTokenStart() {
        return antlrLexer.getToken().getStartIndex();
    }

    @Override
    public int getTokenEnd() {
        return antlrLexer.getToken().getStopIndex();
    }

    @Override
    public void advance() {
        antlrLexer.nextToken();
    }

    @Override
    public @NotNull LexerPosition getCurrentPosition() {
        return new LexerPosition() {
            @Override
            public int getOffset() {
                return antlrLexer.getToken().getStartIndex();
            }

            @Override
            public int getState() {
                return antlrLexer.getState();
            }
        };
    }

    @Override
    public void restore(@NotNull LexerPosition position) {
        antlrLexer.getInputStream().seek(position.getOffset());
        antlrLexer.mode(position.getState());
    }

    @Override
    public @NotNull CharSequence getBufferSequence() {
        return antlrLexer.getInputStream().toString();
    }

    @Override
    public int getBufferEnd() {
        return antlrLexer.getInputStream().size();
    }
}
