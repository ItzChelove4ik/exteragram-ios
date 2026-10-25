package org.mvel2.optimizers.impl.refl.nodes;

import java.lang.reflect.Field;
import org.mvel2.DataConversion;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class DynamicFieldAccessor implements AccessorNode {
    private Field field;
    private AccessorNode nextNode;
    private Class targetType;

    public DynamicFieldAccessor() {
    }

    public DynamicFieldAccessor(Field r1) {
        setField(r1);
    }

    public Field getField() {
        return this.field;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this.targetType;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode getNextNode() {
        return this.nextNode;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r2, Object r3, VariableResolverFactory r4) {
        AccessorNode r0 = this.nextNode;     // Catch: Exception -> L8
        if (r0 == null) goto L7;
        return r0.getValue(this.field.get(r2), r3, r4);
    L7:
        return this.field.get(r2);
    L8:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to access field", e);
        return null;
    }

    public void setField(Field r1) {
        this.field = r1;
        this.targetType = r1.getType();
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r2, Object r3, VariableResolverFactory r4, Object r5) {
        AccessorNode r0 = this.nextNode;     // Catch: Exception -> L8
        if (r0 != null) goto L5;
        this.field.set(r2, DataConversion.convert(r5, this.targetType));     // Catch: Exception -> L8
        return r5;
    L5:
        return r0.setValue(this.field.get(r2), r3, r4, r5);
    L8:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to access field", e);
        return null;
    }
}
