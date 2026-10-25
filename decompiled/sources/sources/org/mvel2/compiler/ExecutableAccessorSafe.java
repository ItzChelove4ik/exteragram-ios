package org.mvel2.compiler;

import org.mvel2.ast.ASTNode;
import org.mvel2.ast.Safe;
import org.mvel2.ast.TypeCast;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class ExecutableAccessorSafe implements ExecutableStatement, Safe {
    private boolean convertable;
    private Class egress;
    private Class ingress;
    private ASTNode node;

    public ExecutableAccessorSafe(ASTNode r1) {
        this.node = r1;
    }

    public ExecutableAccessorSafe(ASTNode r1, Class r2) {
        this.node = r1;
        this.egress = r2;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public void computeTypeConversionRule() {
        Class r0 = this.ingress;
        if (r0 == null) goto L8;
        Class<?> r1 = this.egress;
        if (r1 == null) goto L9;
        this.convertable = r0.isAssignableFrom(r1);
        return;
    L9:
        return;
    }

    @Override // org.mvel2.compiler.ExecutableStatement, org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this.egress;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public Class getKnownIngressType() {
        return this.ingress;
    }

    public ASTNode getNode() {
        return this.node;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r1, Object r2, VariableResolverFactory r3) {
        return this.node.getReducedValueAccelerated(r1, r2, r3);
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public Object getValue(Object r1, VariableResolverFactory r2) {
        return this.node.getReducedValueAccelerated(r1, r1, r2);
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean intOptimized() {
        return false;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isConvertableIngressEgress() {
        return this.convertable;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isEmptyStatement() {
        if (this.node != null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isExplicitCast() {
        ASTNode r0 = this.node;
        if (r0 != null) goto L5;
        return false;
    L5:
        if ((r0 instanceof TypeCast) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isLiteralOnly() {
        return false;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public void setKnownEgressType(Class r1) {
        this.egress = r1;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public void setKnownIngressType(Class r1) {
        this.ingress = r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        return null;
    }
}
