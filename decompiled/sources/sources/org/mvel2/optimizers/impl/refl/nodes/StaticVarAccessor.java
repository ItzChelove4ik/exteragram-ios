package org.mvel2.optimizers.impl.refl.nodes;

import java.lang.reflect.Field;
import org.mvel2.OptimizationFailure;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class StaticVarAccessor implements AccessorNode {
    Field field;
    private AccessorNode nextNode;

    public StaticVarAccessor(Field r1) {
        this.field = r1;
    }

    public Field getField() {
        return this.field;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this.field.getClass();
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode getNextNode() {
        return this.nextNode;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r2, Object r3, VariableResolverFactory r4) {
        AccessorNode r22 = this.nextNode;     // Catch: Exception -> L8
        if (r22 == null) goto L7;
        return r22.getValue(this.field.get(null), r3, r4);
    L7:
        return this.field.get(null);
    L8:
        e = move-exception;
        throw new OptimizationFailure("unable to access static field", e);
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r2, Object r3, VariableResolverFactory r4, Object r5) {
        AccessorNode r22 = this.nextNode;     // Catch: Exception -> L8
        if (r22 != null) goto L7;
        this.field.set(null, r5);     // Catch: Exception -> L8
        return r5;
    L7:
        return r22.setValue(this.field.get(null), r3, r4, r5);
    L8:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("error accessing static variable", e);
        return null;
    }
}
