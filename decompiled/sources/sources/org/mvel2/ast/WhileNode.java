package org.mvel2.ast;

import java.util.HashMap;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.mvel2.util.CompilerTools;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class WhileNode extends BlockNode {
    protected ExecutableStatement condition;
    protected String item;

    public WhileNode(char[] r1, int r2, int r3, int r4, int r5, int r6, ParserContext r7) {
        super(r7);
        ExecutableStatement r22 = (ExecutableStatement) ParseTools.subCompileExpression(r1, r2, r3, r7);
        this.condition = r22;
        if ((r6 & 16) == 0) goto L5;
        boolean r32 = true;
    L6:
        CompilerTools.expectType(r7, r22, Boolean.class, r32);
        if (r7 == null) goto L9;
        r7.pushVariableScope();
    L9:
        this.compiledBlock = (ExecutableStatement) ParseTools.subCompileExpression(r1, r4, r5, r7);
        if (r7 == null) goto L13;
        r7.popVariableScope();
        return;
    L13:
        return;
    L5:
        r32 = false;
        goto L6
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r3, Object r4, VariableResolverFactory r5) {
        MapVariableResolverFactory r0 = new MapVariableResolverFactory(new HashMap(), r5);
    L4:
        if (((Boolean) this.condition.getValue(r3, r4, r5)).booleanValue() == false) goto L6;
        this.compiledBlock.getValue(r3, r4, r0);
        goto L4
    L6:
        return null;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r3, Object r4, VariableResolverFactory r5) {
        MapVariableResolverFactory r0 = new MapVariableResolverFactory(new HashMap(), r5);
    L4:
        if (((Boolean) this.condition.getValue(r3, r4, r5)).booleanValue() == false) goto L6;
        this.compiledBlock.getValue(r3, r4, r0);
        goto L4
    L6:
        return null;
    }
}
