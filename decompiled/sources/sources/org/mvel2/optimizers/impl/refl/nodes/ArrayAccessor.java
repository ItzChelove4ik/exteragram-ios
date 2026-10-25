package org.mvel2.optimizers.impl.refl.nodes;

import java.lang.reflect.Array;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class ArrayAccessor implements AccessorNode {
    private int index;
    private AccessorNode nextNode;

    public ArrayAccessor() {
    }

    public ArrayAccessor(int r1) {
        this.index = r1;
    }

    public int getIndex() {
        return this.index;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return Object[].class;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode getNextNode() {
        return this.nextNode;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r2, Object r3, VariableResolverFactory r4) {
        AccessorNode r0 = this.nextNode;
        int r1 = this.index;
        if (r0 != null) goto L5;
        return Array.get(r2, r1);
    L9:
        throw new ClassCastException("Argument of type '" + r2.getClass() + "' is not an Array");
    L5:
        return r0.getValue(Array.get(r2, r1), r3, r4);
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
        int r1 = this.index;
        if (r0 != null) goto L5;
        Array.set(r2, r1, r5);
        return r5;
    L5:
        return r0.setValue(Array.get(r2, r1), r3, r4, r5);
    }

    public String toString() {
        return "Array Accessor -> [" + this.index + "]";
    }
}
