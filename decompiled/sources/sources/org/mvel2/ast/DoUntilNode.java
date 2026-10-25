package org.mvel2.ast;

import java.util.HashMap;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.mvel2.util.CompilerTools;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class DoUntilNode extends BlockNode {
    protected ExecutableStatement condition;
    protected String item;

    public DoUntilNode(char[] r2, int r3, int r4, int r5, int r6, ParserContext r7) {
        super(r7);
        this.expr = r2;
        this.start = r3;
        this.offset = r4;
        ExecutableStatement r32 = (ExecutableStatement) ParseTools.subCompileExpression(r2, r3, r4, r7);
        this.condition = r32;
        if ((this.fields & 16) == 0) goto L5;
        boolean r42 = true;
    L6:
        CompilerTools.expectType(r7, r32, Boolean.class, r42);
        if (r7 == null) goto L9;
        r7.pushVariableScope();
    L9:
        this.compiledBlock = (ExecutableStatement) ParseTools.subCompileExpression(r2, r5, r6, r7);
        if (r7 == null) goto L13;
        r7.popVariableScope();
        return;
    L13:
        return;
    L5:
        r42 = false;
        goto L6
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r4, Object r5, VariableResolverFactory r6) {
        MapVariableResolverFactory r0 = new MapVariableResolverFactory(new HashMap(0), r6);
    L3:
        this.compiledBlock.getValue(r4, r5, r0);
        if (((Boolean) this.condition.getValue(r4, r5, r0)).booleanValue() == false) goto L3;
        return null;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r4, Object r5, VariableResolverFactory r6) {
        MapVariableResolverFactory r0 = new MapVariableResolverFactory(new HashMap(0), r6);
    L3:
        this.compiledBlock.getValue(r4, r5, r0);
        if (((Boolean) this.condition.getValue(r4, r5, r0)).booleanValue() == false) goto L3;
        return null;
    }
}
