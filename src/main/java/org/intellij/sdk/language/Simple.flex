// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.intellij.sdk.language;

import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import org.intellij.sdk.language.SimpleTokenType;
import static com.intellij.psi.TokenType.WHITE_SPACE;

%%

%class SimpleLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

DEC_DIGIT = [0-9]
DEC_INTEGER = "-"? {DEC_DIGIT}+

LINE_WS = [\ \t\f]+
EOL = "\r"|"\n"|"\r\n"
WHITE_SPACE=({LINE_WS}|{EOL})+

ALPHA_NUM = [a-zA-Z0-9]
ID = [a-zA-Z] {ALPHA_NUM}*
STRING=(\"([^\"\r\n\\]|\\.)*\")

%%

{WHITE_SPACE}    { return WHITE_SPACE; }
{STRING}         { return SimpleTokenType.Companion.getSTRING(); }
{DEC_INTEGER}    { return SimpleTokenType.Companion.getINT(); }
"funk"           { return SimpleTokenType.Companion.getFUNK(); }
"var"            { return SimpleTokenType.Companion.getVAR(); }
"if"             { return SimpleTokenType.Companion.getIF(); }
"then"           { return SimpleTokenType.Companion.getTHEN(); }
"else"           { return SimpleTokenType.Companion.getELSSE(); }
"return"         { return SimpleTokenType.Companion.getRETURN(); }
"while"          { return SimpleTokenType.Companion.getWHILE(); }

{ID}             { return SimpleTokenType.Companion.getIDEN(); }

";"              { return SimpleTokenType.Companion.getSEMICOLON(); }
"("              { return SimpleTokenType.Companion.getLPAREN(); }
")"              { return SimpleTokenType.Companion.getRPAREN(); }
"{"              { return SimpleTokenType.Companion.getLBRACE(); }
"}"              { return SimpleTokenType.Companion.getRBRACE(); }
","              { return SimpleTokenType.Companion.getCOMMA(); }
"="              { return SimpleTokenType.Companion.getEQ(); }


[^]           { return TokenType.BAD_CHARACTER; }
