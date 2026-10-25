package org.mvel2.optimizers.impl.refl.nodes;

import java.util.List;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class ListAccessor implements AccessorNode {
    private int index;
    private AccessorNode nextNode;

    public ListAccessor() {
    }

    public ListAccessor(int r1) {
        this.index = r1;
    }

    public int getIndex() {
        return this.index;
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
        AccessorNode r0 = this.nextNode;
        if (r0 == null) goto L7;
        return r0.getValue(((List) r2).get(this.index), r3, r4);
    L7:
        return ((List) r2).get(this.index);
    }

    public void setIndex(int r1) {
        this.index = r1;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r2, Object r3, VariableResolverFactory r4, Object r5) {
        AccessorNode r0 = this.nextNode;
        if (r0 != null) goto L5;
        ((List) r2).set(this.index, r5);
        return r5;
    L5:
        return r0.setValue(((List) r2).get(this.index), r3, r4, r5);
    }

    public String toString() {
        return "Array Accessor -> [" + this.index + "]";
    }
}
