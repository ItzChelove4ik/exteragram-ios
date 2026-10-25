package org.mvel2.optimizers.impl.refl.nodes;

import java.lang.reflect.Method;
import org.mvel2.DataConversion;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.mvel2.util.ParseTools;
import org.mvel2.util.PropertyTools;

/* loaded from: classes5.dex */
public class SetterAccessor implements AccessorNode {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Object[] EMPTY = null;
    private boolean coercionRequired;
    private final Method method;
    private AccessorNode nextNode;
    private boolean primitive;
    private Class<?> targetType;

    static {
        EMPTY = new Object[0];
    }

    public SetterAccessor(Method r2) {
        this.coercionRequired = false;
        this.method = r2;
        Class<?> r22 = r2.getParameterTypes()[0];
        this.targetType = r22;
        this.primitive = r22.isPrimitive();
    }

    private Object executeOverrideTarget(Method r1, Object r2, Object r3) {
        return r1.invoke(r2, new Object[]{DataConversion.convert(r3, this.targetType)});
    L4:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to invoke method", e);
        return null;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this.method.getReturnType();
    }

    public Method getMethod() {
        return this.method;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode getNextNode() {
        return this.nextNode;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r1, Object r2, VariableResolverFactory r3) {
        return null;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r8, Object r9, VariableResolverFactory r10, Object r11) {
        boolean r0 = this.coercionRequired;
        Method r1 = this.method;
        if (r0 == true) goto L5;
        if (r11 == null) goto L12;
    L14:
        Object r2 = r11;
    L16:
        return r1.invoke(r8, new Object[]{r2});
    L12:
        if (this.primitive == false) goto L14;
        r2 = PropertyTools.getPrimitiveInitialValue(this.targetType);     // Catch: Exception -> L6 IllegalArgumentException -> L8
        goto L16
    L5:
        return r1.invoke(r8, new Object[]{DataConversion.convert(r11, this.targetType)});
    L8:
        e = move-exception;
        if (r8 != null) goto L22;
    L27:
        if (r0 == true) goto L30;
        this.coercionRequired = true;
        return setValue(r8, r9, r10, r11);
    L30:
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to bind property", e);
        return null;
    L22:
        if (this.method.getDeclaringClass() == r8.getClass()) goto L27;
        Method r3 = ParseTools.getBestCandidate(EMPTY, this.method.getName(), r8.getClass(), r8.getClass().getMethods(), true);
        if (r3 == null) goto L27;
        return executeOverrideTarget(r3, r8, r11);
    L6:
        e = move-exception;
        throw new RuntimeException("error calling method: " + this.method.getDeclaringClass().getName() + "." + this.method.getName(), e);
    }

    public String toString() {
        return this.method.getDeclaringClass().getName() + "." + this.method.getName();
    }
}
