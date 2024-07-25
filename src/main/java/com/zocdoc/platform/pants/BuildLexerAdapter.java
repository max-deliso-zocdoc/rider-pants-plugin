package com.zocdoc.platform.pants;

import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerPosition;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;
import com.zocdoc.grammar.PantsBuildLexer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BuildLexerAdapter extends Lexer {
    private final PantsBuildLexer antlrLexer;

    private static final Logger LOG = Logger.getInstance(BuildLexerAdapter.class);
    private CharSequence buffer;
    private int startOffset;
    private int endOffset;
    private Token currentToken;

    public BuildLexerAdapter() {
        antlrLexer = new com.zocdoc.grammar.PantsBuildLexer(null);
    }

    @Override
    public void start(@NotNull CharSequence buffer,
                      int startOffset,
                      int endOffset,
                      int initialState) {
        this.buffer = buffer;
        this.startOffset = startOffset;
        this.endOffset = endOffset;

        LOG.info("lexer started with buffer: " + this.buffer);

        antlrLexer.setInputStream(CharStreams.fromString(buffer.toString()));
        antlrLexer.reset();
        advance();
    }

    @Override
    public int getState() {
        LOG.info("get state called on lexer adapter");
        return antlrLexer.getState();
    }

    @Override
    public @Nullable IElementType getTokenType() {
        LOG.info("get token type called on lexer adapter...");

        if (this.currentToken == null || this.currentToken.getType() == Token.EOF) {
            LOG.info("no token to return");
            return null;
        }

        var inputTokenType = this.currentToken.getType();
        LOG.info("get token type: " + inputTokenType);
        var mappedTokenType = BuildTokenType.getTokenType(inputTokenType);
        LOG.info("mapped token type: " + mappedTokenType);
        return mappedTokenType;
    }

    @Override
    public int getTokenStart() {
        return currentToken != null ? this.currentToken.getStartIndex() : -1;
    }

    @Override
    public int getTokenEnd() {
        return currentToken != null ? this.currentToken.getStopIndex() + 1 : -1;
    }

    @Override
    public void advance() {
        currentToken = antlrLexer.nextToken();
        LOG.info("advanced token to: " + this.currentToken);
    }

    @Override
    public @NotNull LexerPosition getCurrentPosition() {
        return new LexerPosition() {
            @Override
            public int getOffset() {
                return getTokenStart();
            }

            @Override
            public int getState() {
                return antlrLexer.getState();
            }
        };
    }

    @Override
    public void restore(@NotNull LexerPosition position) {
        antlrLexer.reset();
        antlrLexer.setInputStream(CharStreams.fromString(buffer.toString()));
        for (int i = 0; i < position.getOffset(); i++) {
            antlrLexer.nextToken();
        }
        advance();
    }

    @Override
    public @NotNull CharSequence getBufferSequence() {
        return buffer;
    }

    @Override
    public int getBufferEnd() {
        return endOffset;
    }
}
