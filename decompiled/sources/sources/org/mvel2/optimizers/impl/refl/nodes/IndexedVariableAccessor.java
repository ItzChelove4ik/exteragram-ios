package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class IndexedVariableAccessor implements AccessorNode {
    private AccessorNode nextNode;
    private int register;

    public IndexedVariableAccessor(int r1) {
        this.register = r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return Object.class;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode getNextNode() {
        return this.nextNode;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r1, Object r2, VariableResolverFactory r3) {
        AccessorNode r12 = this.nextNode;
        int r0 = this.register;
        if (r12 == null) goto L7;
        return r12.getValue(r3.getIndexedVariableResolver(r0).getValue(), r2, r3);
    L7:
        return r3.getIndexedVariableResolver(r0).getValue();
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        AccessorNode r12 = this.nextNode;
        int r0 = this.register;
        if (r12 != null) goto L5;
        r3.getIndexedVariableResolver(r0).setValue(r4);
        return r4;
    L5:
        return r12.setValue(r3.getIndexedVariableResolver(r0).getValue(), r2, r3, r4);
    }
}
