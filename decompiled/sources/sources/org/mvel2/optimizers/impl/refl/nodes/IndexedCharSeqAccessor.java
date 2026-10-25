package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class IndexedCharSeqAccessor implements AccessorNode {
    private int index;
    private AccessorNode nextNode;

    public IndexedCharSeqAccessor() {
    }

    public IndexedCharSeqAccessor(int r1) {
        this.index = r1;
    }

    public int getIndex() {
        return this.index;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return Character.class;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode getNextNode() {
        return this.nextNode;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r2, Object r3, VariableResolverFactory r4) {
        AccessorNode r0 = this.nextNode;
        if (r0 == null) goto L7;
        return r0.getValue(Character.valueOf(((String) r2).charAt(this.index)), r3, r4);
    L7:
        return Character.valueOf(((String) r2).charAt(this.index));
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
        return this.nextNode.setValue(Character.valueOf(((String) r2).charAt(this.index)), r3, r4, r5);
    }

    public String toString() {
        return "Array Accessor -> [" + this.index + "]";
    }
}
