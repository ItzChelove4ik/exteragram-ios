package org.mvel2;

import java.util.Map;
import org.mvel2.compiler.AbstractParser;
import org.mvel2.util.ParseTools;
import org.mvel2.util.StringAppender;

/* loaded from: classes5.dex */
public class MacroProcessor extends AbstractParser implements PreProcessor {
    private Map<String, Macro> macros;

    public MacroProcessor() {
    }

    public MacroProcessor(Map<String, Macro> r1) {
        this.macros = r1;
    }

    public void captureToWhitespace() {
    L2:
        int r0 = this.cursor;
        if (r0 >= this.length) goto L7;
        if (ParseTools.isWhitespace(this.expr[r0]) == true) goto L10;
        this.cursor++;
        goto L2
    L10:
        return;
    }

    public Map<String, Macro> getMacros() {
        return this.macros;
    }

    @Override // org.mvel2.PreProcessor
    public String parse(String r2) {
        return new String(parse(r2.toCharArray()));
    }

    @Override // org.mvel2.PreProcessor
    public char[] parse(char[] r10) {
        setExpression(r10);
        StringAppender r102 = new StringAppender();
        boolean r1 = true;
    L3:
        int r2 = this.cursor;
        if (r2 >= this.length) goto L75;
    L5:
        int r3 = this.cursor;
        if (r3 >= this.length) goto L10;
        if (ParseTools.isIdentifierPart(this.expr[r3]) == false) goto L10;
        this.cursor++;
    L10:
        int r32 = this.cursor;
        if (r32 <= r2) goto L17;
        Map<String, Macro> r4 = this.macros;
        String r5 = new String(this.expr, r2, r32 - r2);
        if (r4.containsKey(r5) == false) goto L16;
        if (r1 == false) goto L16;
        r102.append(this.macros.get(r5).doMacro());
    L16:
        r102.append(r5);
    L17:
        int r22 = this.cursor;
        int r33 = this.length;
        if (r22 >= r33) goto L73;
        char[] r42 = this.expr;
        char r52 = r42[r22];
        if (r52 != '\"') goto L22;
    L55:
        int r34 = ParseTools.captureStringLiteral(r52, r42, r22, r33);
        this.cursor = r34;
        r102.append(new String(r42, r22, r34 - r22));
        int r23 = this.cursor;
        if (r23 >= this.length) goto L73;
        if (ParseTools.isIdentifierPart(this.expr[r23]) == false) goto L61;
        this.cursor--;
    L61:
        char r24 = this.expr[this.cursor];
        if (r24 != '(') goto L64;
    L71:
        r1 = true;
    L72:
        r102.append(r24);
        goto L73
    L64:
        if (r24 != '.') goto L66;
        r1 = false;
        goto L72
    L66:
        if (r24 == ';') goto L71;
        if (r24 == '{') goto L71;
    L22:
        if (r52 == '\'') goto L55;
        if (r52 == '/') goto L30;
        if (r52 != '\\') goto L61;
        this.cursor = r22 + 1;
        goto L73
    L30:
        if ((r22 + 1) == r33) goto L49;
        char r43 = r42[r22 + 1];
        if (r43 == '*') goto L40;
        if (r43 != '/') goto L49;
    L35:
        int r35 = this.cursor;
        if (r35 == this.length) goto L49;
        if (this.expr[r35] == '\n') goto L49;
        this.cursor = r35 + 1;
        goto L35
    L40:
        int r36 = r33 - 1;
    L41:
        int r44 = this.cursor;
        if (r44 == r36) goto L48;
        char[] r7 = this.expr;
        if (r7[r44] != '*') goto L47;
        if (r7[r44 + 1] == '/') goto L48;
    L47:
        this.cursor = r44 + 1;
    L48:
        this.cursor = r44 + 2;
    L49:
        int r37 = this.cursor;
        if (r37 >= this.length) goto L52;
        this.cursor = r37 + 1;
    L52:
        r102.append(new String(this.expr, r22, this.cursor - r22));
        int r25 = this.cursor;
        if (r25 >= this.length) goto L73;
        this.cursor = r25 - 1;
    L73:
        this.cursor++;
        goto L3
    L75:
        return r102.toChars();
    }

    public void setMacros(Map<String, Macro> r1) {
        this.macros = r1;
    }
}
