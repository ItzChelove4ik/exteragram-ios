package org.mvel2.optimizers.impl.refl.nodes;

import com.google.android.gms.internal.play_billing.zzgo$$ExternalSyntheticBUOutline0;
import java.lang.reflect.Method;
import org.mvel2.CompileException;
import org.mvel2.MVEL;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.PropertyHandler;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class GetterAccessorNH implements AccessorNode {
    public static final Object[] EMPTY = null;
    private final Method method;
    private AccessorNode nextNode;
    private PropertyHandler nullHandler;

    static {
        EMPTY = new Object[0];
    }

    public GetterAccessorNH(Method r1, PropertyHandler r2) {
        this.method = r1;
        this.nullHandler = r2;
    }

    private Object executeOverrideTarget(Method r7, Object r8, Object r9, VariableResolverFactory r10) {
        return nullHandle(r7.getName(), r7.invoke(r8, EMPTY), r8, r9, r10);
    L4:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to invoke method", e);
        return null;
    }

    private Object nullHandle(String r2, Object r3, Object r4, Object r5, VariableResolverFactory r6) {
        AccessorNode r0 = this.nextNode;
        if (r3 == null) goto L8;
        if (r0 != null) goto L6;
        return r3;
    L6:
        return r0.getValue(r3, r5, r6);
    L8:
        PropertyHandler r1 = this.nullHandler;
        if (r0 == null) goto L13;
        return r0.getValue(r1.getProperty(r2, r4, r6), r5, r6);
    L13:
        return r1.getProperty(r2, r4, r6);
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
    public Object getValue(Object r8, Object r9, VariableResolverFactory r10) {
        GetterAccessorNH r1 = this;
        Object r4 = r8;
        Object r5 = r9;
        VariableResolverFactory r6 = r10;
        return r1.nullHandle(this.method.getName(), this.method.invoke(r8, EMPTY), r4, r5, r6);
    L6:
        e = e;
    L7:
        Exception r7 = e;
        StringBuilder r92 = new StringBuilder("cannot invoke getter: ");
        r92.append(r1.method.getName());
        r92.append(" [declr.class: ");
        r92.append(r1.method.getDeclaringClass().getName());
        r92.append("; act.class: ");
        if (r4 == null) goto L14;
        String r102 = r4.getClass().getName();
    L15:
        r92.append(r102);
        r92.append("]");
        throw new RuntimeException(r92.toString(), r7);
    L14:
        r102 = "null";
    L17:
        if (r4 == null) goto L25;
        if (r1.method.getDeclaringClass() == r4.getClass()) goto L25;
        Method r72 = ParseTools.getBestCandidate(EMPTY, r1.method.getName(), r4.getClass(), r4.getClass().getMethods(), true);
        if (r72 == null) goto L25;
        return r1.executeOverrideTarget(r72, r4, r5, r6);
    L25:
        return r1.nullHandle(r1.method.getName(), MVEL.getProperty(r1.method.getName() + "()", r4), r4, r5, r6);
    L10:
        r1 = this;
        r4 = r8;
        r5 = r9;
        r6 = r10;
    L8:
        e = e;
        r1 = this;
        r4 = r8;
        goto L7
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r8, Object r9, VariableResolverFactory r10, Object r11) {
        Object r0 = this.method.invoke(r8, EMPTY);     // Catch: Exception -> L5 CompileException -> L11 IllegalArgumentException -> L14
        if (r0 != null) goto L7;
        r0 = this.nullHandler.getProperty(this.method.getName(), r8, r10);     // Catch: Exception -> L5 CompileException -> L11 IllegalArgumentException -> L14
    L7:
        return this.nextNode.setValue(r0, r9, r10, r11);
    L14:
        Object r02 = MVEL.getProperty(this.method.getName() + "()", r8);
        if (r02 != null) goto L18;
        r02 = this.nullHandler.getProperty(this.method.getName(), r8, r10);
    L18:
        return this.nextNode.setValue(r02, r9, r10, r11);
    L11:
        e = move-exception;
        throw e;
    L5:
        e = move-exception;
        zzgo$$ExternalSyntheticBUOutline0.m("error ", this.method.getName(), ": ", e.getClass().getName(), ":", e.getMessage(), e);
        return null;
    }

    public String toString() {
        return this.method.getDeclaringClass().getName() + "." + this.method.getName();
    }
}
