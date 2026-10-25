package org.mvel2.optimizers.impl.refl.nodes;

import java.lang.reflect.Constructor;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class ConstructorAccessor extends InvokableAccessor {
    private Constructor constructor;

    public ConstructorAccessor(Constructor r1, ExecutableStatement[] r2) {
        this.constructor = r1;
        Class<?>[] r12 = r1.getParameterTypes();
        this.parameterTypes = r12;
        this.length = r12.length;
        this.parms = r2;
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

    public Constructor getConstructor() {
        return this.constructor;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this.constructor.getClass();
    }

    public ExecutableStatement[] getParameters() {
        return this.parms;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r4, Object r5, VariableResolverFactory r6) {
        boolean r0 = this.coercionNeeded;     // Catch: Exception -> L18
        AccessorNode r1 = this.nextNode;
        if (r0 == false) goto L21;
        if (r1 == null) goto L16;
        Constructor r42 = this.constructor;     // Catch: Exception -> L18
        return r1.getValue(r42.newInstance(executeAndCoerce(this.parameterTypes, r5, r6, r42.isVarArgs())), r5, r6);
    L16:
        Constructor r43 = this.constructor;     // Catch: Exception -> L18
        return r43.newInstance(executeAndCoerce(this.parameterTypes, r5, r6, r43.isVarArgs()));
    L21:
        if (r1 == null) goto L9;
        return r1.getValue(this.constructor.newInstance(executeAll(r5, r6)), r5, r6);
    L9:
        return this.constructor.newInstance(executeAll(r5, r6));
    L11:
        this.coercionNeeded = true;     // Catch: Exception -> L18
        return getValue(r4, r5, r6);
    L18:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("cannot construct object", e);
        return null;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        return null;
    }
}
