package com.zocdoc.platform.pants;

import com.intellij.psi.tree.IElementType;
import com.zocdoc.grammar.PantsBuildLexer;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class BuildTokenType extends IElementType {

    private static final Map<Integer, IElementType> tokenTypeMap = new HashMap<>();

    public BuildTokenType(@NonNls @NotNull String debugName) {
        super(debugName, BuildLanguage.INSTANCE);
    }

    public static IElementType getTokenType(int antlrTokenType) {
        return tokenTypeMap.get(antlrTokenType);
    }

    private static void addTokenType(int antlrTokenType, IElementType tokenType) {
        tokenTypeMap.put(antlrTokenType, tokenType);
    }

    public static final IElementType IDENTIFIER = new BuildTokenType("IDENTIFIER");
    public static final IElementType STRING = new BuildTokenType("STRING");
    public static final IElementType NUMBER = new BuildTokenType("NUMBER");
    public static final IElementType LBRACKET = new BuildTokenType("LBRACKET");
    public static final IElementType RBRACKET = new BuildTokenType("RBRACKET");
    public static final IElementType LBRACE = new BuildTokenType("LBRACE");
    public static final IElementType RBRACE = new BuildTokenType("RBRACE");
    public static final IElementType LPAREN = new BuildTokenType("LPAREN");
    public static final IElementType RPAREN = new BuildTokenType("RPAREN");
    public static final IElementType COMMA = new BuildTokenType("COMMA");
    public static final IElementType COLON = new BuildTokenType("COLON");
    public static final IElementType ASSIGN = new BuildTokenType("ASSIGN");
    public static final IElementType WS = new BuildTokenType("WS");
    public static final IElementType NEWLINE = new BuildTokenType("NEWLINE");
    public static final IElementType COMMENT = new BuildTokenType("COMMENT");

    static {
        addTokenType(PantsBuildLexer.IDENTIFIER, IDENTIFIER);
        addTokenType(PantsBuildLexer.STRING, STRING);
        addTokenType(PantsBuildLexer.NUMBER, NUMBER);
        addTokenType(PantsBuildLexer.LBRACKET, LBRACKET);
        addTokenType(PantsBuildLexer.RBRACKET, RBRACKET);
        addTokenType(PantsBuildLexer.LBRACE, LBRACE);
        addTokenType(PantsBuildLexer.RBRACE, RBRACE);
        addTokenType(PantsBuildLexer.LPAREN, LPAREN);
        addTokenType(PantsBuildLexer.RPAREN, RPAREN);
        addTokenType(PantsBuildLexer.COMMA, COMMA);
        addTokenType(PantsBuildLexer.COLON, COLON);
        addTokenType(PantsBuildLexer.ASSIGN, ASSIGN);
        addTokenType(PantsBuildLexer.WS, WS);
        addTokenType(PantsBuildLexer.NEWLINE, NEWLINE);
        addTokenType(PantsBuildLexer.COMMENT, COMMENT);
    }
}

