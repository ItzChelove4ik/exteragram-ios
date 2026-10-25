package org.mvel2.optimizers.impl.refl.nodes;

import java.lang.reflect.Method;
import org.mvel2.DataConversion;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class DynamicSetterAccessor implements AccessorNode {
    public static final Object[] EMPTY = null;
    private final Method method;
    private Class targetType;

    static {
        EMPTY = new Object[0];
    }

    public DynamicSetterAccessor(Method r2) {
        this.method = r2;
        this.targetType = r2.getParameterTypes()[0];
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this.targetType;
    }

    public Method getMethod() {
        return this.method;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode getNextNode() {
        return null;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r1, Object r2, VariableResolverFactory r3) {
        return null;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        return null;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        return this.method.invoke(r1, new Object[]{DataConversion.convert(r4, this.targetType)});
    L4:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("error binding property", e);
        return null;
    }

    public String toString() {
        return this.method.getDeclaringClass().getName() + "." + this.method.getName();
    }
}
