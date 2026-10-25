package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.compiler.AccessorNode;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class IndexedCharSeqAccessorNest implements AccessorNode {
    private ExecutableStatement index;
    private AccessorNode nextNode;

    public IndexedCharSeqAccessorNest() {
    }

    public IndexedCharSeqAccessorNest(ExecutableStatement r1) {
        this.index = r1;
    }

    public ExecutableStatement getIndex() {
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
    public Object getValue(Object r3, Object r4, VariableResolverFactory r5) {
        AccessorNode r0 = this.nextNode;
        if (r0 == null) goto L7;
        return r0.getValue(Character.valueOf(((String) r3).charAt(((Integer) this.index.getValue(r3, r4, r5)).intValue())), r4, r5);
    L7:
        return Character.valueOf(((String) r3).charAt(((Integer) this.index.getValue(r3, r4, r5)).intValue()));
    }

    public void setIndex(ExecutableStatement r1) {
        this.index = r1;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r3, Object r4, VariableResolverFactory r5, Object r6) {
        return this.nextNode.setValue(Character.valueOf(((String) r3).charAt(((Integer) this.index.getValue(r3, r4, r5)).intValue())), r4, r5, r6);
    }

    public String toString() {
        return "Array Accessor -> [" + this.index + "]";
    }
}
