package org.mvel2.ast;

import java.util.HashMap;
import org.mvel2.CompileException;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.mvel2.util.CompilerTools;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class ForNode extends BlockNode {
    protected ExecutableStatement after;
    protected ExecutableStatement condition;
    protected boolean indexAlloc;
    protected ExecutableStatement initializer;
    protected String item;

    public ForNode(char[] r2, int r3, int r4, int r5, int r6, int r7, ParserContext r8) {
        super(r8);
        boolean r0 = false;
        this.indexAlloc = false;
        this.expr = r2;
        this.start = r3;
        this.offset = r4;
        this.blockStart = r5;
        this.blockOffset = r6;
        boolean r22 = buildForEach(r2, r3, r4, r5, r6, r7, r8);
        if (r8 != null) goto L5;
    L7:
        this.indexAlloc = r0;
        if ((r7 & 16) != 0) goto L10;
    L15:
        if (r8 == null) goto L18;
        r8.popVariableScope();
        return;
    L18:
        return;
    L10:
        if (this.compiledBlock.isEmptyStatement() == false) goto L15;
        if (r22 == true) goto L15;
        throw new RedundantCodeException();
    L5:
        if (r8.isIndexAllocation() == false) goto L7;
        r0 = true;
        goto L7
    }

    private boolean buildForEach(char[] r8, int r9, int r10, int r11, int r12, int r13, ParserContext r14) {
        int r102 = r10 + r9;
        int r1 = nextCondPart(r8, r9, r102, false);
        if (r14 == null) goto L5;
        ParserContext r2 = r14.createSubcontext().createColoringSubcontext();     // Catch: NegativeArraySizeException -> L45
    L6:
        boolean r4 = true;
        this.initializer = (ExecutableStatement) ParseTools.subCompileExpression(r8, r9, (r1 - r9) - 1, r2);     // Catch: NegativeArraySizeException -> L45
        if (r14 == null) goto L47;
        r14.pushVariableScope();     // Catch: NegativeArraySizeException -> L45
    L47:
        r9 = nextCondPart(r8, r1, r102, false);     // Catch: NegativeArraySizeException -> L29 CompileException -> L30
        ExecutableStatement r3 = (ExecutableStatement) ParseTools.subCompileExpression(r8, r1, (r9 - r1) - 1, r2);     // Catch: NegativeArraySizeException -> L29 CompileException -> L30
        this.condition = r3;     // Catch: NegativeArraySizeException -> L29 CompileException -> L30
        int r132 = r13 & 16;     // Catch: NegativeArraySizeException -> L29 CompileException -> L30
        if (r132 == 0) goto L12;
        boolean r6 = true;
    L13:
        CompilerTools.expectType(r14, r3, Boolean.class, r6);     // Catch: NegativeArraySizeException -> L29 CompileException -> L30
        this.after = (ExecutableStatement) ParseTools.subCompileExpression(r8, r9, nextCondPart(r8, r9, r102, true) - r9, r2);     // Catch: NegativeArraySizeException -> L45
        if (r2 == null) goto L21;
        if (r132 == 0) goto L21;
        if (r2.isVariablesEscape() == false) goto L21;
        if (r14 == r2) goto L25;
        r14.addVariables(r2.getVariables());     // Catch: NegativeArraySizeException -> L45
    L25:
        this.compiledBlock = (ExecutableStatement) ParseTools.subCompileExpression(this.expr, r11, r12, r2);     // Catch: NegativeArraySizeException -> L45
        if (r14 == null) goto L28;
        r14.setInputs(r2.getInputs());     // Catch: NegativeArraySizeException -> L45
    L28:
        return r4;
    L21:
        if (r2 == null) goto L24;
        if (r14 == null) goto L24;
        r14.addVariables(r2.getVariables());     // Catch: NegativeArraySizeException -> L45
    L24:
        r4 = false;
        goto L25
        goto L43
    L12:
        r6 = false;
    L30:
        e = move-exception;
        if (e.getExpr().length != 0) goto L42;
        e.setExpr(this.expr);     // Catch: NegativeArraySizeException -> L29
        int r92 = r1;
    L35:
        char[] r103 = this.expr;     // Catch: NegativeArraySizeException -> L45
        if (r92 >= r103.length) goto L40;
        if (ParseTools.isWhitespace(r103[r92]) == false) goto L40;
        r92 = r92 + 1;     // Catch: NegativeArraySizeException -> L45
    L40:
        e.setCursor(r92);     // Catch: NegativeArraySizeException -> L45
    L42:
        throw e;     // Catch: NegativeArraySizeException -> L45
    L29:
        r9 = r1;
        goto L43
    L5:
        r2 = new ParserContext();     // Catch: NegativeArraySizeException -> L45
    L43:
        Sign$$ExternalSyntheticBUOutline0.m("wrong syntax; did you mean to use 'foreach'?", this.expr, r9);
        return false;
    }

    private static int nextCondPart(char[] r2, int r3, int r4, boolean r5) {
    L2:
        if (r3 >= r4) goto L8;
        if (r2[r3] == ';') goto L6;
        r3 = r3 + 1;
        goto L2
    L6:
        return r3 + 1;
    L8:
        if (r5 == false) goto L10;
        return r3;
    L10:
        Sign$$ExternalSyntheticBUOutline0.m("expected ;", r2, r3);
        return 0;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r5, Object r6, VariableResolverFactory r7) {
        ExecutableStatement r0 = this.initializer;
        MapVariableResolverFactory r1 = new MapVariableResolverFactory(new HashMap(1), r7);
        r0.getValue(r5, r6, r1);
    L4:
        if (((Boolean) this.condition.getValue(r5, r6, r1)).booleanValue() == false) goto L9;
        Object r72 = this.compiledBlock.getValue(r5, r6, r1);
        if (r1.tiltFlag() == true) goto L7;
        this.after.getValue(r5, r6, r1);
        goto L4
    L7:
        return r72;
    L9:
        return null;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r4, Object r5, VariableResolverFactory r6) {
        if (this.indexAlloc == true) goto L6;
        r6 = new MapVariableResolverFactory(new HashMap(1), r6);
    L6:
        ExecutableStatement r0 = this.initializer;
    L7:
        r0.getValue(r4, r5, r6);
        if (((Boolean) this.condition.getValue(r4, r5, r6)).booleanValue() == false) goto L13;
        Object r02 = this.compiledBlock.getValue(r4, r5, r6);
        if (r6.tiltFlag() == true) goto L11;
        r0 = this.after;
        goto L7
    L11:
        return r02;
    L13:
        return null;
    }
}
