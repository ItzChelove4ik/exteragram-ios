package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.compiler.AccessorNode;

/* loaded from: classes5.dex */
public abstract class BaseAccessor implements AccessorNode {
    protected AccessorNode nextNode;

    public BaseAccessor() {
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode getNextNode() {
        return this.nextNode;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }
}
