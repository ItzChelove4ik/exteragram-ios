package org.mvel2.optimizers.impl.refl.nodes;

import androidx.room.util.KClassUtil$$ExternalSyntheticBUOutline0;
import java.lang.reflect.Method;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class MethodAccessor extends InvokableAccessor {
    private Method method;

    public MethodAccessor() {
    }

    public MethodAccessor(Method r1, ExecutableStatement[] r2) {
        setMethod(r1);
        this.parms = r2;
    }

    private Object[] executeAll(Object r7, VariableResolverFactory r8, Method r9) {
        int r0 = this.length;
        if (r0 == 0) goto L5;
        Object[] r02 = new Object[r0];
        int r1 = 0;
        int r2 = 0;
    L8:
        if (r2 >= (this.length - (r9.isVarArgs() ? 1 : 0))) goto L11;
        r02[r2] = this.parms[r2].getValue(r7, r8);
        r2 = r2 + 1;
        goto L8
    L11:
        if (r9.isVarArgs() == false) goto L30;
        ExecutableStatement[] r92 = this.parms;
        if (r92 != null) goto L16;
        r02[this.length - 1] = new Object[0];
        return r02;
    L16:
        int r3 = r92.length;
        int r4 = this.length;
        if (r3 != r4) goto L26;
        Object r72 = r92[r4 - 1].getValue(r7, r8);
        int r6 = this.length - 1;
        if (r72 != null) goto L21;
    L24:
        r02[r6] = r72;
        return r02;
    L21:
        if (r72.getClass().isArray() == true) goto L24;
        r72 = new Object[]{r72};
        goto L24
    L26:
        int r93 = (r92.length - r4) + 1;
        Object[] r32 = new Object[r93];
    L27:
        if (r1 >= r93) goto L29;
        r32[r1] = this.parms[(this.length - 1) + r1].getValue(r7, r8);
        r1 = r1 + 1;
        goto L27
    L29:
        r02[this.length - 1] = r32;
    L30:
        return r02;
    L5:
        return GetterAccessor.EMPTY;
    }

    private Object executeOverrideTarget(Method r11, Object r12, Object r13, VariableResolverFactory r14) {
        boolean r0 = this.coercionNeeded;
        AccessorNode r1 = this.nextNode;
        if (r0 == false) goto L35;
        String r2 = "no";
        if (r1 == null) goto L24;
        return r1.getValue(r11.invoke(r12, executeAndCoerce(r11.getParameterTypes(), r13, r14, r11.isVarArgs())), r13, r14);
    L24:
        return r11.invoke(r12, executeAndCoerce(r11.getParameterTypes(), r13, r14, r11.isVarArgs()));
    L30:
        StringBuilder r132 = new StringBuilder("unable to invoke method (expected target: ");
        r132.append(this.method.getDeclaringClass().getName());
        r132.append("::");
        r132.append(this.method.getName());
        r132.append("; actual target: ");
        r132.append(r12.getClass().getName());
        r132.append("::");
        r132.append(this.method.getName());
        r132.append("; coercionNeeded=");
        if (this.coercionNeeded == false) goto L33;
        r2 = "yes";
    L33:
        r132.append(r2);
        r132.append(")");
        throw new RuntimeException(r132.toString());
    L25:
        StringBuilder r133 = new StringBuilder("unable to invoke method (expected target: ");
        r133.append(this.method.getDeclaringClass().getName());
        r133.append("::");
        r133.append(this.method.getName());
        r133.append("; actual target: ");
        r133.append(r12.getClass().getName());
        r133.append("::");
        r133.append(this.method.getName());
        r133.append("; coercionNeeded=");
        if (this.coercionNeeded == false) goto L28;
        r2 = "yes";
    L28:
        r133.append(r2);
        r133.append(")");
        throw new RuntimeException(r133.toString());
    L35:
        if (r1 == null) goto L10;
        return r1.getValue(r11.invoke(r12, executeAll(r13, r14, r11)), r13, r14);
    L10:
        return r11.invoke(r12, executeAll(r13, r14, r11));
    L7:
        e = move-exception;
        if (r0 == false) goto L13;
        throw e;     // Catch: Exception -> L16
    L13:
        this.coercionNeeded = true;     // Catch: Exception -> L16
        return executeOverrideTarget(r11, r12, r13, r14);
    L16:
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

    public ExecutableStatement[] getParms() {
        return this.parms;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r7, Object r8, VariableResolverFactory r9) {
        boolean r0 = this.coercionNeeded;
        AccessorNode r1 = this.nextNode;
        if (r0 == false) goto L40;
        if (r1 == null) goto L30;
        Method r02 = this.method;     // Catch: Exception -> L26 IllegalArgumentException -> L28
        return r1.getValue(r02.invoke(r7, executeAndCoerce(this.parameterTypes, r8, r9, r02.isVarArgs())), r8, r9);
    L30:
        Method r03 = this.method;     // Catch: Exception -> L26 IllegalArgumentException -> L28
        return r03.invoke(r7, executeAndCoerce(this.parameterTypes, r8, r9, r03.isVarArgs()));
    L28:
        e = move-exception;
        Method r12 = ParseTools.getWidenedTarget(ParseTools.getBestCandidate(executeAndCoerce(this.parameterTypes, r8, r9, false), this.method.getName(), r7.getClass(), r7.getClass().getMethods(), false));
        if (r12 != null) goto L37;
        throw e;
    L37:
        return executeOverrideTarget(r12, r7, r8, r9);
    L26:
        e = move-exception;
        KClassUtil$$ExternalSyntheticBUOutline0.m("cannot invoke method: ", this.method.getName(), e);
        return null;
    L40:
        if (r1 == null) goto L9;
        Method r04 = this.method;     // Catch: Exception -> L7 IllegalArgumentException -> L13
        return r1.getValue(r04.invoke(r7, executeAll(r8, r9, r04)), r8, r9);
    L9:
        Method r05 = this.method;     // Catch: Exception -> L7 IllegalArgumentException -> L13
        return r05.invoke(r7, executeAll(r8, r9, r05));
    L7:
        e = move-exception;
        KClassUtil$$ExternalSyntheticBUOutline0.m("cannot invoke method: ", this.method.getName(), e);
        return null;
    L14:
        if (r7 != null) goto L16;
    L21:
        this.coercionNeeded = true;
        return getValue(r7, r8, r9);
    L16:
        if (this.method.getDeclaringClass() == r7.getClass()) goto L21;
        Method r13 = ParseTools.getBestCandidate(this.parameterTypes, this.method.getName(), r7.getClass(), r7.getClass().getMethods(), true);
        if (r13 == null) goto L21;
        return executeOverrideTarget(ParseTools.getWidenedTarget(r13), r7, r8, r9);
    }

    public void setMethod(Method r1) {
        this.method = r1;
        Class<?>[] r12 = r1.getParameterTypes();
        this.parameterTypes = r12;
        this.length = r12.length;
    }

    public void setParms(ExecutableStatement[] r1) {
        this.parms = r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r6, Object r7, VariableResolverFactory r8, Object r9) {
        AccessorNode r0 = this.nextNode;     // Catch: Exception -> L4 IllegalArgumentException -> L7
        Method r1 = this.method;     // Catch: Exception -> L4 IllegalArgumentException -> L7
        return r0.setValue(r1.invoke(r6, executeAll(r7, r8, r1)), r7, r8, r9);
    L4:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("cannot invoke method", e);
        return null;
    L8:
        if (r6 != null) goto L10;
    L15:
        this.coercionNeeded = true;
        return this.setValue(r6, r7, r8, r9);
    L10:
        if (this.method.getDeclaringClass() == r6.getClass()) goto L15;
        Method r12 = ParseTools.getBestCandidate(this.parameterTypes, this.method.getName(), r6.getClass(), r6.getClass().getMethods(), true);
        if (r12 == null) goto L15;
        return this.nextNode.setValue(this.executeOverrideTarget(r12, r6, r7, r8), r7, r8, r9);
    }
}
