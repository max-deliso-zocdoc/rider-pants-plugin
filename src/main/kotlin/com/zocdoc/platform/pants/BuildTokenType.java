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

    public static final IElementType NUMBER = new BuildTokenType("NUMBER");

    public static final IElementType STRING = new BuildTokenType("STRING");

    static {
        addTokenType(PantsBuildLexer.IDENTIFIER, IDENTIFIER);
        addTokenType(PantsBuildLexer.NUMBER, NUMBER);
        addTokenType(PantsBuildLexer.STRING, STRING);
    }
}

