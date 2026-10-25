package org.mvel2.ast;

import org.mvel2.CompileException;
import org.mvel2.DataConversion;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ParseTools;
import org.mvel2.util.ReflectionUtil;

/* loaded from: classes5.dex */
public class TypeCast extends ASTNode {
    private ExecutableStatement statement;
    private boolean widen;

    public TypeCast(char[] r1, int r2, int r3, Class r4, int r5, ParserContext r6) {
        super(r6);
        this.egressType = r4;
        this.expr = r1;
        this.start = r2;
        this.offset = r3;
        if ((r5 & 16) == 0) goto L14;
        ExecutableStatement r32 = (ExecutableStatement) ParseTools.subCompileExpression(r1, r2, r3, r6);
        this.statement = r32;
        if (r32.getKnownEgressType() != Object.class) goto L7;
        return;
    L7:
        if (DataConversion.canConvert(r4, this.statement.getKnownEgressType()) == false) goto L9;
        return;
    L9:
        if (canCast(this.statement.getKnownEgressType(), r4) == false) goto L13;
        this.widen = true;
        return;
    L13:
        throw new CompileException("unable to cast type: " + this.statement.getKnownEgressType() + "; to: " + r4, r1, r2);
    }

    private boolean canCast(Class r2, Class r3) {
        if (ReflectionUtil.isAssignableFrom(r2, r3) == false) goto L5;
        return true;
    L5:
        if (r2.isInterface() == true) goto L7;
        return false;
    L7:
        if (interfaceAssignable(r2, r3) == true) goto L14;
        return false;
    L14:
        return true;
    }

    private boolean interfaceAssignable(Class r4, Class r5) {
        Class<?>[] r3 = r4.getInterfaces();
        int r42 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r42) goto L9;
        if (r3[r1].isAssignableFrom(r5) == true) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private static Object typeCheck(Object r2, Class r3) {
        if (r2 != null) goto L6;
        return null;
    L6:
        if (r3.isInstance(r2) == false) goto L9;
        return r2;
    L9:
        throw new ClassCastException(r2.getClass().getName() + " cannot be cast to: " + r3.getName());
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r3, Object r4, VariableResolverFactory r5) {
        boolean r42 = this.widen;
        char[] r0 = this.expr;
        if (r42 == false) goto L7;
        return typeCheck(MVEL.eval(r0, this.start, this.offset, r3, r5), this.egressType);
    L7:
        return DataConversion.convert(MVEL.eval(r0, this.start, this.offset, r3, r5), this.egressType);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r3, Object r4, VariableResolverFactory r5) {
        boolean r0 = this.widen;
        Object r32 = this.statement.getValue(r3, r4, r5);
        Class r2 = this.egressType;
        if (r0 == false) goto L7;
        return typeCheck(r32, r2);
    L7:
        return DataConversion.convert(r32, r2);
    }

    public ExecutableStatement getStatement() {
        return this.statement;
    }
}
