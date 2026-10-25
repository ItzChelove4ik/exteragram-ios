package org.mvel2.optimizers.impl.refl.nodes;

import androidx.room.util.KClassUtil$$ExternalSyntheticBUOutline1;
import com.google.android.gms.internal.play_billing.zzgo$$ExternalSyntheticBUOutline0;
import java.lang.reflect.Method;
import org.mvel2.CompileException;
import org.mvel2.MVEL;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.mvel2.util.ParseTools;
import org.mvel2.util.ReflectionUtil;

/* loaded from: classes5.dex */
public class GetterAccessor implements AccessorNode {
    public static final Object[] EMPTY = null;
    private final Method method;
    private AccessorNode nextNode;

    static {
        EMPTY = new Object[0];
    }

    public GetterAccessor(Method r1) {
        this.method = r1;
    }

    private Object executeOverrideTarget(Method r2, Object r3, Object r4, VariableResolverFactory r5) {
        AccessorNode r1 = this.nextNode;     // Catch: Exception -> L8
        if (r1 == null) goto L7;
        return r1.getValue(r2.invoke(r3, EMPTY), r4, r5);
    L7:
        return r2.invoke(r3, EMPTY);
    L8:
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
    public Object getValue(Object r9, Object r10, VariableResolverFactory r11) {
        AccessorNode r0 = this.nextNode;     // Catch: Exception -> L7 NullPointerException -> L9 IllegalArgumentException -> L25
        if (r0 == null) goto L12;
        return r0.getValue(this.method.invoke(r9, EMPTY), r10, r11);
    L12:
        return this.method.invoke(r9, EMPTY);
    L25:
        if (r9 != null) goto L27;
    L32:
        AccessorNode r02 = this.nextNode;
        Method r8 = this.method;
        if (r02 == null) goto L37;
        return r02.getValue(MVEL.getProperty(ReflectionUtil.getPropertyFromAccessor(r8.getName()), r9), r10, r11);
    L37:
        return MVEL.getProperty(ReflectionUtil.getPropertyFromAccessor(r8.getName()), r9);
    L27:
        if (this.method.getDeclaringClass() == r9.getClass()) goto L32;
        Method r03 = ParseTools.getBestCandidate(EMPTY, this.method.getName(), r9.getClass(), r9.getClass().getMethods(), true);
        if (r03 == null) goto L32;
        return executeOverrideTarget(r03, r9, r10, r11);
    L9:
        e = move-exception;
        Method r102 = this.method;
        if (r9 != null) goto L24;
        KClassUtil$$ExternalSyntheticBUOutline1.m("unable to invoke method: ", r102.getDeclaringClass().getName(), ".", this.method.getName(), ": target of method is null", e);
        return null;
    L24:
        throw new RuntimeException("cannot invoke getter: " + r102.getName() + " (see trace)", e);
    L7:
        e = move-exception;
        StringBuilder r04 = new StringBuilder("cannot invoke getter: ");
        r04.append(this.method.getName());
        r04.append(" [declr.class: ");
        r04.append(this.method.getDeclaringClass().getName());
        r04.append("; act.class: ");
        if (r9 == null) goto L16;
        String r82 = r9.getClass().getName();
    L17:
        r04.append(r82);
        r04.append("] (see trace)");
        throw new RuntimeException(r04.toString(), e);
    L16:
        r82 = "null";
        goto L17
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r8, Object r9, VariableResolverFactory r10, Object r11) {
        AccessorNode r0 = this.nextNode;     // Catch: Exception -> L6 CompileException -> L12 IllegalArgumentException -> L15
        if (r0 == null) goto L9;
        return r0.setValue(this.method.invoke(r8, EMPTY), r9, r10, r11);
    L9:
        throw new RuntimeException("bad payload");     // Catch: Exception -> L6 CompileException -> L12 IllegalArgumentException -> L15
    L15:
        AccessorNode r02 = this.nextNode;
        Method r7 = this.method;
        if (r02 == null) goto L20;
        return r02.setValue(MVEL.getProperty(ReflectionUtil.getPropertyFromAccessor(r7.getName()), r8), r9, r10, r11);
    L20:
        return MVEL.getProperty(ReflectionUtil.getPropertyFromAccessor(r7.getName()), r8);
    L12:
        e = move-exception;
        throw e;
    L6:
        e = move-exception;
        zzgo$$ExternalSyntheticBUOutline0.m("error ", this.method.getName(), ": ", e.getClass().getName(), ":", e.getMessage(), e);
        return null;
    }

    public String toString() {
        return this.method.getDeclaringClass().getName() + "." + this.method.getName();
    }
}
