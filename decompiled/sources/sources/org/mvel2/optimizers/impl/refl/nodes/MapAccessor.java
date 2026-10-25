package org.mvel2.optimizers.impl.refl.nodes;

import java.util.Map;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class MapAccessor implements AccessorNode {
    private AccessorNode nextNode;
    private Object property;

    public MapAccessor() {
    }

    public MapAccessor(Object r1) {
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
    public Object getValue(Object r2, Object r3, VariableResolverFactory r4) {
        AccessorNode r0 = this.nextNode;
        if (r0 == null) goto L7;
        return r0.getValue(((Map) r2).get(this.property), r3, r4);
    L7:
        return ((Map) r2).get(this.property);
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    public void setProperty(Object r1) {
        this.property = r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r2, Object r3, VariableResolverFactory r4, Object r5) {
        AccessorNode r0 = this.nextNode;
        if (r0 != null) goto L5;
        ((Map) r2).put(this.property, r5);
        return r5;
    L5:
        return r0.setValue(((Map) r2).get(this.property), r3, r4, r5);
    }

    public String toString() {
        return "Map Accessor -> [" + this.property + "]";
    }
}
