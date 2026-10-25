package org.mvel2.util;

import java.util.ArrayList;
import java.util.HashMap;
import org.mvel2.DataConversion;
import org.mvel2.ParserContext;
import org.mvel2.ast.Sign$$ExternalSyntheticBUOutline0;
import org.mvel2.compiler.ExecutableStatement;

/* loaded from: classes5.dex */
public class CollectionParser {
    public static final int ARRAY = 1;
    private static final Object[] EMPTY_ARRAY = null;
    public static final int LIST = 0;
    public static final int MAP = 2;
    private Class colType;
    private int cursor;
    private int end;
    private ParserContext pCtx;
    private char[] property;
    private int start;
    private int type;

    static {
        EMPTY_ARRAY = new Object[0];
    }

    public CollectionParser() {
    }

    public CollectionParser(int r1) {
        this.type = r1;
    }

    private boolean isStrongType() {
        ParserContext r0 = this.pCtx;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isStrongTyping() == false) goto L10;
        return true;
    L10:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object parseCollection(boolean r22) {
        int r1 = this.end;
        int r2 = this.start;
        int r12 = r1 - r2;
        int r3 = this.type;
        if (r12 != 0) goto L9;
        if (r3 != 0) goto L8;
        return new ArrayList();
    L8:
        return EMPTY_ARRAY;
    L9:
        String r13 = null;
        if (r3 == (-1)) goto L17;
        if (r3 == 0) goto L16;
        if (r3 == 1) goto L16;
        if (r3 != 2) goto L17;
        HashMap r32 = new HashMap();
        ArrayList r7 = null;
    L18:
        int r8 = -1;
    L19:
        int r9 = this.cursor;
        int r10 = this.end;
        if (r9 >= r10) goto L84;
        char[] r11 = this.property;
        char r122 = r11[r9];
        if (r122 != '\"') goto L24;
    L82:
        this.cursor = ParseTools.balancedCapture(r11, r9, r10, r122);
    L83:
        this.cursor++;
        goto L19
    L24:
        if (r122 == ',') goto L76;
        if (r122 != '.') goto L28;
        int r92 = r9 + 1;
        this.cursor = r92;
        int r93 = ParseTools.skipWhitespace(r11, r92);
        this.cursor = r93;
        if (r93 == this.end) goto L83;
        char[] r102 = this.property;
        if (r102[r93] != '{') goto L83;
        this.cursor = ParseTools.balancedCapture(r102, r93, '{');
        goto L83
    L28:
        if (r122 == ':') goto L63;
        if (r122 == '[') goto L41;
        if (r122 != '{') goto L33;
        if (r8 != (-1)) goto L41;
        r8 = 1;
        goto L41
    L33:
        if (r122 == '\'') goto L82;
        if (r122 != '(') goto L83;
        this.cursor = ParseTools.balancedCapture(r11, r9, r10, '(');
    L41:
        if (r9 > this.start) goto L43;
    L45:
        if (r8 != (-1)) goto L47;
        r8 = 0;
    L47:
        CollectionParser r14 = new CollectionParser(r8);
        char[] r15 = this.property;
        int r23 = this.cursor;
        this.cursor = ParseTools.balancedCapture(r15, r23, this.end, r15[r23]);
        Object r24 = r14.parseCollection(r15, r23 + 1, (r9 - r23) - 1, r22, this.colType, this.pCtx);
        if (this.type != 2) goto L50;
        r32.put(r13, r24);
    L51:
        char[] r25 = this.property;
        int r94 = this.cursor + 1;
        this.cursor = r94;
        r2 = ParseTools.skipWhitespace(r25, r94);
        this.cursor = r2;
        int r95 = this.end;
        if (r2 < r95) goto L54;
    L56:
        if (r2 >= r95) goto L83;
        if (ParseTools.opLookup(this.property[r2]) != (-1)) goto L83;
        Sign$$ExternalSyntheticBUOutline0.m("unterminated collection element", this.property, this.cursor);
        return null;
    L54:
        if (this.property[r2] != ',') goto L56;
        r2 = r2 + 1;
        goto L83
    L50:
        r7.add(r24);
        r13 = r24;
        goto L51
    L43:
        if (ParseTools.isIdentifierPart(r11[r9 - 1]) == false) goto L45;
    L63:
        if (this.type == 2) goto L65;
        r32 = new HashMap();
        this.type = 2;
    L65:
        r13 = ParseTools.createStringTrimmed(this.property, r2, this.cursor - r2);
        if (r22 == false) goto L68;
        subCompile(r2, this.cursor - r2);
    L68:
        int r26 = this.cursor;
    L69:
        r2 = r26 + 1;
        goto L83
    L76:
        if (this.type == 2) goto L78;
        r7.add(new String(r11, r2, r9 - r2).trim());
    L79:
        if (r22 == false) goto L81;
        subCompile(r2, this.cursor - r2);
    L81:
        r26 = this.cursor;
        goto L69
    L78:
        r32.put(r13, ParseTools.createStringTrimmed(r11, r2, r9 - r2));
        goto L79
    L84:
        if (r2 < r10) goto L86;
    L88:
        int r4 = this.end;
        if (r2 >= r4) goto L102;
        int r82 = this.cursor;
        if (r82 >= (r4 - 1)) goto L94;
        this.cursor = r82 + 1;
    L94:
        if (this.type != 2) goto L96;
        r32.put(r13, ParseTools.createStringTrimmed(this.property, r2, this.cursor - r2));
    L100:
        if (r22 == false) goto L102;
        subCompile(r2, this.cursor - r2);
        goto L102
    L96:
        int r16 = this.cursor;
        if (r16 >= r4) goto L99;
        this.cursor = r16 + 1;
    L99:
        r7.add(ParseTools.createStringTrimmed(this.property, r2, this.cursor - r2));
    L102:
        int r0 = this.type;
        if (r0 == 1) goto L108;
        if (r0 == 2) goto L106;
        return r7;
    L106:
        return r32;
    L108:
        return r7.toArray();
    L86:
        if (ParseTools.isWhitespace(this.property[r2]) == false) goto L88;
        r2 = ParseTools.skipWhitespace(this.property, r2);
    L16:
        r7 = new ArrayList();
        r32 = null;
    L17:
        r32 = null;
        r7 = null;
        goto L18
    }

    private void subCompile(int r3, int r4) {
        Class r0 = this.colType;
        char[] r1 = this.property;
        if (r0 != null) goto L6;
        ParseTools.subCompileExpression(r1, r3, r4, this.pCtx);
        return;
    L6:
        Class r32 = ((ExecutableStatement) ParseTools.subCompileExpression(r1, r3, r4, this.pCtx)).getKnownEgressType();
        if (r32 != null) goto L9;
        return;
    L9:
        if (ReflectionUtil.isAssignableFrom(this.colType, r32) == false) goto L11;
        return;
    L11:
        if (isStrongType() == false) goto L13;
    L15:
        Sign$$ExternalSyntheticBUOutline0.m("expected type: " + this.colType.getName() + "; but found: " + r32.getName(), this.property, this.cursor);
        return;
    L13:
        if (DataConversion.canConvert(r32, this.colType) == false) goto L15;
    }

    public int getCursor() {
        return this.cursor;
    }

    public Object parseCollection(char[] r1, int r2, int r3, boolean r4, Class r5, ParserContext r6) {
        if (r5 == null) goto L4;
        this.colType = ParseTools.getBaseComponentType(r5);
    L4:
        this.property = r1;
        this.end = r3 + r2;
    L6:
        if (r2 >= this.end) goto L10;
        if (ParseTools.isWhitespace(r1[r2]) == false) goto L10;
        r2 = r2 + 1;
    L10:
        this.cursor = r2;
        this.start = r2;
        this.pCtx = r6;
        return parseCollection(r4);
    }

    public Object parseCollection(char[] r1, int r2, int r3, boolean r4, ParserContext r5) {
        this.property = r1;
        this.pCtx = r5;
        this.end = r3 + r2;
    L4:
        if (r2 >= this.end) goto L8;
        if (ParseTools.isWhitespace(r1[r2]) == false) goto L8;
        r2 = r2 + 1;
    L8:
        this.cursor = r2;
        this.start = r2;
        return parseCollection(r4);
    }
}
