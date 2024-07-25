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
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
        antlrLexer.setInputStream(new ANTLRInputStream(buffer.toString()));
        antlrLexer.reset();
    }

    @Override
    public int getState() {
        return 0;
    }

    @Override
    public @Nullable IElementType getTokenType() {
        Token token = antlrLexer.nextToken();


        token.getType();

        // TODO: map from antlr to enum
        //PantsBuildTokenType.getTokenType(token.getType());

        return null;
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
              return 0;
          }

          @Override
          public int getState() {
              return 0;
          }
      };
    }

    @Override
    public void restore(@NotNull LexerPosition position) {
        // TODO
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
