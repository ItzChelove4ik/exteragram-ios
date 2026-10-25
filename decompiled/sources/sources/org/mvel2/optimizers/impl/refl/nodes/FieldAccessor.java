package org.mvel2.optimizers.impl.refl.nodes;

import androidx.room.util.KClassUtil$$ExternalSyntheticBUOutline0;
import java.lang.reflect.Field;
import org.mvel2.DataConversion;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.mvel2.util.PropertyTools;

/* loaded from: classes5.dex */
public class FieldAccessor implements AccessorNode {
    private boolean coercionRequired;
    private Field field;
    private AccessorNode nextNode;
    private boolean primitive;

    public FieldAccessor() {
        this.coercionRequired = false;
    }

    public FieldAccessor(Field r2) {
        this.coercionRequired = false;
        this.field = r2;
        this.primitive = r2.getType().isPrimitive();
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
    public Object getValue(Object r3, Object r4, VariableResolverFactory r5) {
        AccessorNode r0 = this.nextNode;     // Catch: Exception -> L6
        if (r0 == null) goto L9;
        return r0.getValue(this.field.get(r3), r4, r5);
    L9:
        return this.field.get(r3);
    L6:
        e = move-exception;
        KClassUtil$$ExternalSyntheticBUOutline0.m("unable to access field: ", this.field.getName(), e);
        return null;
    }

    public void setField(Field r1) {
        this.field = r1;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r6, Object r7, VariableResolverFactory r8, Object r9) {
        AccessorNode r0 = this.nextNode;
        if (r0 != null) goto L32;
        boolean r02 = this.coercionRequired;
        Field r3 = this.field;
        if (r02 == false) goto L23;
        Object r92 = DataConversion.convert(r6, r3.getClass());     // Catch: Exception -> L19 IllegalArgumentException -> L21
        r3.set(r6, r92);     // Catch: Exception -> L19 IllegalArgumentException -> L21
        return r92;
    L23:
        r3.set(r6, r9);     // Catch: Exception -> L19 IllegalArgumentException -> L21
        return r9;
    L21:
        e = move-exception;
        if (r02 == true) goto L30;
        this.coercionRequired = true;
        return setValue(r6, r7, r8, r9);
    L30:
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to bind property", e);
        return null;
    L19:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to access field", e);
        return null;
    L32:
        Object r62 = this.field.get(r6);     // Catch: Exception -> L9
        if (r9 == null) goto L7;
    L11:
        return r0.setValue(r62, r7, r8, r9);
    L7:
        if (this.primitive == false) goto L11;
        r9 = PropertyTools.getPrimitiveInitialValue(this.field.getType());     // Catch: Exception -> L9
    L9:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to access field", e);
        return null;
    }
}
