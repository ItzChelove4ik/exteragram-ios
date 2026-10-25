package org.mvel2.optimizers;

import java.lang.reflect.Method;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.ast.Sign$$ExternalSyntheticBUOutline0;
import org.mvel2.compiler.AbstractParser;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class AbstractOptimizer extends AbstractParser {
    protected static final int BEAN = 0;
    protected static final int COL = 2;
    protected static final int METH = 1;
    protected static final int WITH = 3;
    protected boolean collection;
    protected Class currType;
    protected boolean nullSafe;
    protected boolean staticAccess;
    protected int tkStart;

    public AbstractOptimizer() {
        this.collection = false;
        this.nullSafe = false;
        this.currType = null;
        this.staticAccess = false;
    }

    public AbstractOptimizer(ParserContext r2) {
        super(r2);
        this.collection = false;
        this.nullSafe = false;
        this.currType = null;
        this.staticAccess = false;
    }

    public String capture() {
        char[] r1 = this.expr;
        int r2 = trimRight(this.tkStart);
        this.tkStart = r2;
        return new String(r1, r2, trimLeft(this.cursor) - this.tkStart);
    }

    public int findLastUnion() {
        int r0 = (this.start + this.length) - 1;
        int r3 = 0;
        int r4 = -1;
    L4:
        if (r0 == this.start) goto L26;
        char r5 = this.expr[r0];
        if (r5 != '.') goto L8;
        if (r3 == 0) goto L20;
    L23:
        if (r4 != (-1)) goto L24;
        r0 = r0 - 1;
        goto L4
    L24:
        return r4;
    L20:
        r4 = r0;
        goto L23
    L8:
        if (r5 != '[') goto L10;
    L17:
        r3 = r3 - 1;
        if (r3 != 0) goto L23;
        this.collection = true;
        goto L20
    L10:
        if (r5 != ']') goto L12;
    L16:
        r3 = r3 + 1;
        goto L23
    L12:
        if (r5 == '{') goto L17;
        if (r5 == '}') goto L16;
    L26:
        return r4;
    }

    public int nextSubToken() {
        skipWhitespace();
        this.nullSafe = false;
        char[] r1 = this.expr;
        int r2 = this.cursor;
        this.tkStart = r2;
        char r3 = r1[r2];
        if (r3 == '.') goto L15;
        if (r3 == '?') goto L13;
        if (r3 == '[') goto L11;
        if (r3 != '{') goto L27;
        if (r1[r2 - 1] != '.') goto L27;
        return 3;
    L27:
        int r12 = this.cursor + 1;
        this.cursor = r12;
        if (r12 >= this.end) goto L32;
        if (ParseTools.isIdentifierPart(this.expr[r12]) == true) goto L27;
    L32:
        skipWhitespace();
        int r13 = this.cursor;
        if (r13 >= this.end) goto L40;
        char r132 = this.expr[r13];
        if (r132 == '(') goto L39;
        if (r132 == '[') goto L38;
        return 0;
    L38:
        return 2;
    L39:
        return 1;
    L40:
        return 0;
    L11:
        return 2;
    L13:
        if (this.start != r2) goto L27;
        this.tkStart = r2 + 1;
        this.cursor = r2 + 1;
        this.nullSafe = true;
        goto L27
    L15:
        int r32 = this.start;
        if ((r32 + 1) == this.end) goto L44;
        int r22 = r2 + 1;
        this.tkStart = r22;
        this.cursor = r22;
        char r14 = r1[r22];
        if (r14 == '?') goto L24;
        if (r14 != '{') goto L21;
        return 3;
    L21:
        if (ParseTools.isWhitespace(r14) == false) goto L27;
        skipWhitespace();
        this.tkStart = this.cursor;
        goto L27
    L24:
        skipWhitespace();
        int r15 = this.tkStart + 1;
        this.tkStart = r15;
        this.cursor = r15;
        if (r15 == this.end) goto L41;
        this.nullSafe = true;
        this.fields = -1;
        goto L27
    L41:
        Sign$$ExternalSyntheticBUOutline0.m("unexpected end of statement", this.expr, this.start);
        return 0;
    L44:
        Sign$$ExternalSyntheticBUOutline0.m("unexpected end of statement", r1, r32);
        return 0;
    }

    public boolean scanTo(char r6) {
    L2:
        int r0 = this.cursor;
        int r1 = this.end;
        if (r0 >= r1) goto L15;
        char[] r2 = this.expr;
        char r3 = r2[r0];
        if (r3 != '\"') goto L7;
    L9:
        this.cursor = ParseTools.captureStringLiteral(r3, r2, r0, r1);
    L10:
        char[] r02 = this.expr;
        int r12 = this.cursor;
        if (r02[r12] == r6) goto L12;
        this.cursor = r12 + 1;
        goto L2
    L12:
        return false;
    L7:
        if (r3 == '\'') goto L9;
    L15:
        return true;
    }

    public Object tryStaticAccess() {
        int r0 = this.cursor;
        int r2 = this.end;     // Catch: Exception -> L101
        int r3 = r2 - 1;
        int r4 = 0;
        boolean r5 = false;
    L5:
        if (r3 <= this.start) goto L102;
        char r6 = this.expr[r3];     // Catch: Exception -> L101
        if (r6 != '\"') goto L9;
    L92:
        int r62 = r3 - 1;
        if (r62 <= this.start) goto L91;
        char[] r9 = this.expr;     // Catch: Exception -> L101
        if (r9[r62] != '\"') goto L99;
        if (r9[r3 - 2] != '\\') goto L91;
    L99:
        r3 = r62;
    L91:
        r3 = r62;
    L100:
        r3 = r3 - 1;
        goto L5
    L9:
        if (r6 == '\'') goto L83;
        int r11 = 1;
        if (r6 != ')') goto L13;
        int r32 = r3 - 1;
        int r22 = 1;
    L66:
        if (r32 <= this.start) goto L82;
        if (r22 == 0) goto L82;
        char r52 = this.expr[r32];     // Catch: Exception -> L101
        if (r52 == '\"') goto L75;
        switch(r52) {
            case 39: goto L75;
            case 40: goto L73;
            case 41: goto L72;
            default: goto L81;
        };     // Catch: Exception -> L101
    L72:
        r22 = r22 + 1;     // Catch: Exception -> L101
        goto L81
    L73:
        r22 = r22 - 1;
    L81:
        r32 = r32 - 1;
    L75:
        if (r32 <= this.start) goto L81;
        char[] r63 = this.expr;     // Catch: Exception -> L101
        if (r63[r32] == r52) goto L81;
        if (r63[r32 - 1] == '\\') goto L81;
        r32 = r32 - 1;
    L82:
        int r53 = r32;
        r3 = r32 + 1;     // Catch: Exception -> L101
        r2 = r53;
        r5 = true;
        goto L100
    L13:
        if (r6 != '.') goto L15;
        if (r5 == false) goto L37;
        r2 = r3;
        r5 = false;
        goto L100
    L37:
        ParserContext r54 = this.pCtx;     // Catch: Exception -> L101
        if (r54 == null) goto L40;
        ClassLoader r55 = r54.getClassLoader();     // Catch: Exception -> L101
    L41:
        char[] r7 = this.expr;     // Catch: Exception -> L101
        int r8 = this.start;     // Catch: Exception -> L101
        this.cursor = r2;     // Catch: Exception -> L101
        String r64 = new String(r7, r8, r2 - r8);     // Catch: Exception -> L101
    L47:
        e = move-exception;
        return ParseTools.findInnerClass(r64, r55, e);
    L53:
        Class r23 = ParseTools.forNameWithInner(new String(this.expr, this.start, r3 - this.start), r55);     // Catch: Exception -> L101
        String r56 = new String(this.expr, r3 + 1, (this.end - r3) - 1);     // Catch: Exception -> L101
        return r23.getField(r56);
    L56:
        Method[] r24 = r23.getMethods();     // Catch: Exception -> L101
        int r33 = r24.length;     // Catch: Exception -> L101
    L57:
        if (r4 >= r33) goto L62;
        Method r65 = r24[r4];     // Catch: Exception -> L101
        if (r56.equals(r65.getName()) == true) goto L60;
        r4 = r4 + 1;     // Catch: Exception -> L101
        goto L57
    L60:
        return r65;
    L62:
        return null;
    L43:
        if (MVEL.COMPILER_OPT_SUPPORT_JAVA_STYLE_CLASS_LITERALS == true) goto L45;
    L49:
        return Class.forName(r64, true, r55);
    L45:
        if (r64.endsWith(".class") == false) goto L49;
        r64 = r64.substring(0, r64.length() - 6);     // Catch: ClassNotFoundException -> L47 Exception -> L101
        goto L49
    L40:
        r55 = Thread.currentThread().getContextClassLoader();     // Catch: Exception -> L101
        goto L41
    L15:
        if (r6 != '}') goto L100;
    L17:
        r3 = r3 - 1;
        if (r3 <= this.start) goto L100;
        if (r11 == 0) goto L100;
        char r66 = this.expr[r3];     // Catch: Exception -> L101
        if (r66 == '\"') goto L30;
        if (r66 == '\'') goto L30;
        if (r66 != '{') goto L25;
        r11 = r11 - 1;
        goto L17
    L25:
        if (r66 != '}') goto L17;
        r11 = r11 + 1;     // Catch: Exception -> L101
    L30:
        if (r3 <= this.start) goto L17;
        char[] r12 = this.expr;     // Catch: Exception -> L101
        if (r12[r3] == r66) goto L17;
        if (r12[r3 - 1] == '\\') goto L17;
        r3 = r3 - 1;
    L83:
        r62 = r3 - 1;
        if (r62 <= this.start) goto L91;
        char[] r82 = this.expr;     // Catch: Exception -> L101
        if (r82[r62] != '\'') goto L90;
        if (r82[r3 - 2] != '\\') goto L91;
    L90:
        r3 = r62;
    L102:
        return null;
    L101:
        this.cursor = r0;
        goto L102
    }

    public void whiteSpaceSkip() {
        if (this.cursor < this.length) goto L5;
        return;
    L5:
        if (ParseTools.isWhitespace(this.expr[this.cursor]) == false) goto L13;
        int r0 = this.cursor + 1;
        this.cursor = r0;
        if (r0 != this.length) goto L5;
        return;
    }
}
