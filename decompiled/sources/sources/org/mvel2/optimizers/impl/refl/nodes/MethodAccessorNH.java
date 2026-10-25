package org.mvel2.optimizers.impl.refl.nodes;

import java.lang.reflect.Method;
import org.mvel2.DataConversion;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.PropertyHandler;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class MethodAccessorNH implements AccessorNode {
    private boolean coercionNeeded;
    private int length;
    private Method method;
    private AccessorNode nextNode;
    private PropertyHandler nullHandler;
    private Class[] parameterTypes;
    private ExecutableStatement[] parms;

    public MethodAccessorNH() {
        this.coercionNeeded = false;
    }

    public MethodAccessorNH(Method r2, ExecutableStatement[] r3, PropertyHandler r4) {
        this.coercionNeeded = false;
        this.method = r2;
        Class<?>[] r22 = r2.getParameterTypes();
        this.parameterTypes = r22;
        this.length = r22.length;
        this.parms = r3;
        this.nullHandler = r4;
    }

    private Object[] executeAll(Object r4, VariableResolverFactory r5) {
        int r0 = this.length;
        if (r0 == 0) goto L5;
        Object[] r02 = new Object[r0];
        int r1 = 0;
    L8:
        if (r1 >= this.length) goto L10;
        r02[r1] = this.parms[r1].getValue(r4, r5);
        r1 = r1 + 1;
        goto L8
    L10:
        return r02;
    L5:
        return GetterAccessor.EMPTY;
    }

    private Object[] executeAndCoerce(Class[] r5, Object r6, VariableResolverFactory r7) {
        Object[] r0 = new Object[this.length];
        int r1 = 0;
    L4:
        if (r1 >= this.length) goto L6;
        r0[r1] = DataConversion.convert(this.parms[r1].getValue(r6, r7), r5[r1]);
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    private Object executeOverrideTarget(Method r2, Object r3, Object r4, VariableResolverFactory r5) {
        Object r0 = r2.invoke(r3, executeAll(r4, r5));     // Catch: Exception -> L10
        if (r0 != null) goto L5;
        r0 = this.nullHandler.getProperty(r2.getName(), r3, r5);     // Catch: Exception -> L10
    L5:
        AccessorNode r1 = this.nextNode;     // Catch: Exception -> L10
        if (r1 == null) goto L9;
        return r1.getValue(r0, r4, r5);
    L9:
        return r0;
    L10:
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

    public ExecutableStatement[] getParms() {
        return this.parms;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r6, Object r7, VariableResolverFactory r8) {
        if (this.coercionNeeded == false) goto L37;
        AccessorNode r0 = this.nextNode;     // Catch: Exception -> L30
        if (r0 == null) goto L33;
        return r0.getValue(this.method.invoke(r6, executeAndCoerce(this.parameterTypes, r7, r8)), r7, r8);
    L33:
        return this.method.invoke(r6, executeAndCoerce(this.parameterTypes, r7, r8));
    L30:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("cannot invoke method", e);
        return null;
    L37:
        Object r02 = this.method.invoke(r6, executeAll(r7, r8));     // Catch: Exception -> L7 IllegalArgumentException -> L16
        if (r02 != null) goto L9;
        this.nullHandler.getProperty(this.method.getName(), r6, r8);     // Catch: Exception -> L7 IllegalArgumentException -> L16
    L9:
        AccessorNode r3 = this.nextNode;     // Catch: Exception -> L7 IllegalArgumentException -> L16
        if (r3 == null) goto L13;
        return r3.getValue(r02, r7, r8);
        goto L17
    L13:
        return r02;
    L7:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("cannot invoke method", e);
        return null;
    L17:
        if (r6 != null) goto L19;
    L24:
        this.coercionNeeded = true;
        return this.getValue(r6, r7, r8);
    L19:
        if (this.method.getDeclaringClass() == r6.getClass()) goto L24;
        Method r1 = ParseTools.getBestCandidate(this.parameterTypes, this.method.getName(), r6.getClass(), r6.getClass().getMethods(), true);
        if (r1 == null) goto L24;
        return this.executeOverrideTarget(r1, r6, r7, r8);
    }

    public void setMethod(Method r1) {
        this.method = r1;
        Class<?>[] r12 = r1.getParameterTypes();
        this.parameterTypes = r12;
        this.length = r12.length;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    public void setParms(ExecutableStatement[] r1) {
        this.parms = r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        return this.nextNode.setValue(r1, r2, r3, r4);
    }
}
