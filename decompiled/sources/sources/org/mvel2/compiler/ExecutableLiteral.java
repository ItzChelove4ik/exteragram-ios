package org.mvel2.compiler;

import org.mvel2.ast.Safe;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class ExecutableLiteral implements ExecutableStatement, Safe {
    private boolean intOptimized;
    private int integer32;
    private Object literal;

    public ExecutableLiteral(int r1) {
        this.integer32 = r1;
        this.literal = Integer.valueOf(r1);
        this.intOptimized = true;
    }

    public ExecutableLiteral(Object r2) {
        this.literal = r2;
        if ((r2 instanceof Integer) == false) goto L6;
        this.integer32 = ((Integer) r2).intValue();
        return;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public void computeTypeConversionRule() {
    }

    public int getInteger32() {
        return this.integer32;
    }

    @Override // org.mvel2.compiler.ExecutableStatement, org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        Object r0 = this.literal;
        if (r0 != null) goto L7;
        return Object.class;
    L7:
        return r0.getClass();
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public Class getKnownIngressType() {
        return null;
    }

    public Object getLiteral() {
        return this.literal;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r1, Object r2, VariableResolverFactory r3) {
        return this.literal;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public Object getValue(Object r1, VariableResolverFactory r2) {
        return this.literal;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean intOptimized() {
        return this.intOptimized;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isConvertableIngressEgress() {
        return false;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isEmptyStatement() {
        return false;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isExplicitCast() {
        return false;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isLiteralOnly() {
        return true;
    }

    public void setInteger32(int r1) {
        this.integer32 = r1;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public void setKnownEgressType(Class r1) {
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public void setKnownIngressType(Class r1) {
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        return null;
    }
}
