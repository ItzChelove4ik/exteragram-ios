package org.mvel2.util;

import org.mvel2.ParserContext;
import org.mvel2.ast.EndOfStatement;
import org.mvel2.ast.Function;
import org.mvel2.ast.Sign$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class FunctionParser {
    private int cursor;
    private char[] expr;
    private int fields;
    private int length;
    private String name;
    private ParserContext pCtx;
    private ExecutionStack splitAccumulator;

    public FunctionParser(String r1, int r2, int r3, char[] r4, int r5, ParserContext r6, ExecutionStack r7) {
        this.name = r1;
        this.cursor = r2;
        this.length = r3;
        this.expr = r4;
        this.fields = r5;
        this.pCtx = r6;
        this.splitAccumulator = r7;
    }

    public int getCursor() {
        return this.cursor;
    }

    public String getName() {
        return this.name;
    }

    public Function parse() {
        int r0 = this.cursor;
        int r1 = this.length + r0;
        int r2 = ParseTools.captureToNextTokenJunction(this.expr, r0, r1, this.pCtx);
        this.cursor = r2;
        char[] r3 = this.expr;
        int r22 = ParseTools.nextNonBlank(r3, r2);
        this.cursor = r22;
        char r32 = r3[r22];
        char[] r4 = this.expr;
        if (r32 != '(') goto L12;
        int r33 = ParseTools.balancedCaptureWithLineAccounting(r4, r22, r1, '(', this.pCtx);
        int r23 = r22 + 1;
        int r42 = r33 + 1;
        this.cursor = r42;
        int r43 = ParseTools.skipWhitespace(this.expr, r42);
        this.cursor = r43;
        char[] r6 = this.expr;
        if (r43 >= r1) goto L10;
        char r7 = r6[r43];
        ParserContext r8 = this.pCtx;
        if (r7 != '{') goto L9;
        int r12 = ParseTools.balancedCaptureWithLineAccounting(r6, r43, r1, '{', r8);
        this.cursor = r12;
        int r62 = r23;
        r22 = r43;
    L17:
        int r82 = ParseTools.trimRight(this.expr, r22 + 1);
        int r02 = ParseTools.trimLeft(this.expr, r0, r12);
        int r13 = this.cursor + 1;
        this.cursor = r13;
        if (this.splitAccumulator == null) goto L23;
        if (ParseTools.isStatementNotManuallyTerminated(this.expr, r13) == false) goto L23;
        this.splitAccumulator.add(new EndOfStatement(this.pCtx));
    L23:
        return new Function(this.name, this.expr, r62, r33 - r62, r82, r02 - r82, this.fields, this.pCtx);
    L9:
        r12 = ParseTools.captureToEOS(r6, r43, r1, r8);
        this.cursor = r12;
        r62 = r23;
        r22 = r43 - 1;
        goto L17
    L10:
        Sign$$ExternalSyntheticBUOutline0.m("incomplete statement", r6, r43);
        return null;
    L12:
        char r34 = r4[r22];
        ParserContext r63 = this.pCtx;
        if (r34 != '{') goto L16;
        r12 = ParseTools.balancedCaptureWithLineAccounting(r4, r22, r1, '{', r63);
        this.cursor = r12;
    L15:
        r33 = 0;
        r62 = 0;
        goto L17
    L16:
        r12 = ParseTools.captureToEOS(r4, r22, r1, r63);
        this.cursor = r12;
        r22 = r22 - 1;
        goto L15
    }
}
