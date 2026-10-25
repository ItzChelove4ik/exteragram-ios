package org.mvel2.ast;

import java.util.HashMap;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.mvel2.util.CompilerTools;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class IfNode extends BlockNode implements NestedStatement {
    protected ExecutableStatement condition;
    protected ExecutableStatement elseBlock;
    protected IfNode elseIf;
    protected boolean idxAlloc;
    protected ExecutableStatement nestedStatement;

    public IfNode(char[] r4, int r5, int r6, int r7, int r8, int r9, ParserContext r10) {
        super(r10);
        boolean r0 = false;
        this.idxAlloc = false;
        this.expr = r4;
        if (r4 == null) goto L19;
        if (r6 == 0) goto L19;
        this.start = r5;
        this.offset = r6;
        this.blockStart = r7;
        this.blockOffset = r8;
        if (r10 != null) goto L8;
    L10:
        this.idxAlloc = r0;
        if ((r9 & 16) == 0) goto L21;
        ExecutableStatement r52 = (ExecutableStatement) ParseTools.subCompileExpression(r4, r5, r6, r10);
        this.condition = r52;
        CompilerTools.expectType(r10, r52, Boolean.class, true);
        if (r10 == null) goto L15;
        r10.pushVariableScope();
    L15:
        this.nestedStatement = (ExecutableStatement) ParseTools.subCompileExpression(r4, r7, r8, r10);
        if (r10 == null) goto L22;
        r10.popVariableScope();
        return;
    L22:
        return;
    L21:
        return;
    L8:
        if (r10.isIndexAllocation() == false) goto L10;
        r0 = true;
    L19:
        Sign$$ExternalSyntheticBUOutline0.m("statement expected", r4, r5);
        throw null;
    }

    public ExecutableStatement getElseBlock() {
        return this.elseBlock;
    }

    @Override // org.mvel2.ast.NestedStatement
    public ExecutableStatement getNestedStatement() {
        return this.nestedStatement;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r5, Object r6, VariableResolverFactory r7) {
        if (((Boolean) MVEL.eval(this.expr, this.start, this.offset, r5, r7)).booleanValue() == true) goto L5;
        IfNode r0 = this.elseIf;
        if (r0 != null) goto L9;
        ExecutableStatement r4 = this.elseBlock;
        if (r4 != null) goto L13;
        return null;
    L13:
        return r4.getValue(r5, r6, new MapVariableResolverFactory(new HashMap(0), r7));
    L9:
        return r0.getReducedValue(r5, r6, new MapVariableResolverFactory(new HashMap(0), r7));
    L5:
        return MVEL.eval(this.expr, this.blockStart, this.blockOffset, r5, new MapVariableResolverFactory(new HashMap(0), r7));
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r4, Object r5, VariableResolverFactory r6) {
        if (((Boolean) this.condition.getValue(r4, r5, r6)).booleanValue() == false) goto L10;
        ExecutableStatement r0 = this.nestedStatement;
        if (this.idxAlloc == true) goto L9;
        r6 = new MapVariableResolverFactory(new HashMap(0), r6);
    L9:
        return r0.getValue(r4, r5, r6);
    L10:
        IfNode r02 = this.elseIf;
        if (r02 != null) goto L13;
        ExecutableStatement r03 = this.elseBlock;
        if (r03 != null) goto L21;
        return null;
    L21:
        if (this.idxAlloc == true) goto L25;
        r6 = new MapVariableResolverFactory(new HashMap(0), r6);
    L25:
        return r03.getValue(r4, r5, r6);
    L13:
        if (this.idxAlloc == true) goto L17;
        r6 = new MapVariableResolverFactory(new HashMap(0), r6);
    L17:
        return r02.getReducedValueAccelerated(r4, r5, r6);
    }

    public IfNode setElseBlock(char[] r1, int r2, int r3, ParserContext r4) {
        this.elseBlock = (ExecutableStatement) ParseTools.subCompileExpression(r1, r2, r3, r4);
        return this;
    }

    public IfNode setElseIf(IfNode r1) {
        this.elseIf = r1;
        return r1;
    }

    @Override // org.mvel2.ast.ASTNode
    public String toString() {
        return new String(this.expr, this.start, this.offset);
    }
}
