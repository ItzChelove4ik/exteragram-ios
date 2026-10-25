package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class StaticReferenceAccessor implements AccessorNode {
    Object literal;
    private AccessorNode nextNode;

    public StaticReferenceAccessor() {
    }

    public StaticReferenceAccessor(Object r1) {
        this.literal = r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this.literal.getClass();
    }

    public Object getLiteral() {
        return this.literal;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode getNextNode() {
        return this.nextNode;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r1, Object r2, VariableResolverFactory r3) {
        AccessorNode r12 = this.nextNode;
        Object r0 = this.literal;
        if (r12 != null) goto L5;
        return r0;
    L5:
        return r12.getValue(r0, r2, r3);
    }

    public void setLiteral(Object r1) {
        this.literal = r1;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        return this.nextNode.setValue(this.literal, r2, r3, r4);
    }
}
