package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class ThisValueAccessor implements AccessorNode {
    private AccessorNode nextNode;

    public ThisValueAccessor() {
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
        AccessorNode r0 = this.nextNode;
        if (r0 != null) goto L5;
        return r2;
    L5:
        return r0.getValue(r2, r2, r3);
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        AccessorNode r0 = this.nextNode;
        if (r0 != null) goto L5;
        GlShader$$ExternalSyntheticBUOutline1.m("assignment to reserved variable 'this' not permitted");
        return null;
    L5:
        return r0.setValue(r2, r2, r3, r4);
    }
}
