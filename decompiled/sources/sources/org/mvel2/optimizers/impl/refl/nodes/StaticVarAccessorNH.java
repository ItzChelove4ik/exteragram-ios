package org.mvel2.optimizers.impl.refl.nodes;

import java.lang.reflect.Field;
import org.mvel2.OptimizationFailure;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.PropertyHandler;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class StaticVarAccessorNH implements AccessorNode {
    Field field;
    private AccessorNode nextNode;
    private PropertyHandler nullHandler;

    public StaticVarAccessorNH(Field r1, PropertyHandler r2) {
        this.field = r1;
        this.nullHandler = r2;
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
        Object r22 = this.field.get(r2);     // Catch: Exception -> L10
        if (r22 != null) goto L5;
        r22 = this.nullHandler.getProperty(this.field.getName(), r3, r4);     // Catch: Exception -> L10
    L5:
        AccessorNode r1 = this.nextNode;     // Catch: Exception -> L10
        if (r1 == null) goto L9;
        return r1.getValue(r22, r3, r4);
    L9:
        return r22;
    L10:
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
