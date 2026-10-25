package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.GlobalListenerFactory;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class Notify implements AccessorNode {
    private String name;
    private AccessorNode nextNode;

    public Notify(String r1) {
        this.name = r1;
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
    public Object getValue(Object r2, Object r3, VariableResolverFactory r4) {
        GlobalListenerFactory.notifyGetListeners(r2, this.name, r4);
        return this.nextNode.getValue(r2, r3, r4);
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r2, Object r3, VariableResolverFactory r4, Object r5) {
        GlobalListenerFactory.notifySetListeners(r2, this.name, r4, r5);
        return this.nextNode.setValue(r2, r3, r4, r5);
    }
}
