package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.ast.Instance$$ExternalSyntheticBUOutline0;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class VariableAccessor implements AccessorNode {
    private AccessorNode nextNode;
    private String property;

    public VariableAccessor(String r1) {
        this.property = r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return Object.class;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode getNextNode() {
        return this.nextNode;
    }

    public Object getProperty() {
        return this.property;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r1, Object r2, VariableResolverFactory r3) {
        if (r3 == null) goto L9;
        AccessorNode r12 = this.nextNode;
        String r0 = this.property;
        if (r12 == null) goto L8;
        return r12.getValue(r3.getVariableResolver(r0).getValue(), r2, r3);
    L8:
        return r3.getVariableResolver(r0).getValue();
    L9:
        Instance$$ExternalSyntheticBUOutline0.m("cannot access property in optimized accessor: ", this.property);
        return null;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    public void setProperty(String r1) {
        this.property = r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        AccessorNode r12 = this.nextNode;
        String r0 = this.property;
        if (r12 != null) goto L5;
        r3.getVariableResolver(r0).setValue(r4);
        return r4;
    L5:
        return r12.setValue(r3.getVariableResolver(r0).getValue(), r2, r3, r4);
    }
}
