package org.mvel2.optimizers.impl.refl.nodes;

import java.lang.reflect.Array;
import org.mvel2.DataConversion;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public abstract class InvokableAccessor extends BaseAccessor {
    protected boolean coercionNeeded;
    protected int length;
    protected Class[] parameterTypes;
    protected ExecutableStatement[] parms;

    public InvokableAccessor() {
        this.coercionNeeded = false;
    }

    public Object[] executeAndCoerce(Class[] r6, Object r7, VariableResolverFactory r8, boolean r9) {
        Object[] r0 = new Object[this.length];
        int r2 = 0;
    L3:
        int r3 = this.length;
        if (r2 >= r3) goto L9;
        if (r9 == false) goto L8;
        if (r2 >= (r3 - 1)) goto L9;
    L8:
        r0[r2] = DataConversion.convert(this.parms[r2].getValue(r7, r8), r6[r2]);
        r2 = r2 + 1;
    L9:
        if (r9 == false) goto L19;
        Class<?> r62 = r6[r3 - 1].getComponentType();
        ExecutableStatement[] r92 = this.parms;
        if (r92 != null) goto L13;
        Object r63 = Array.newInstance(r62, 0);
    L18:
        r0[this.length - 1] = r63;
        goto L19
    L13:
        Object r93 = Array.newInstance(r62, (r92.length - this.length) + 1);
        int r1 = this.length - 1;
    L14:
        ExecutableStatement[] r22 = this.parms;
        if (r1 >= r22.length) goto L17;
        Array.set(r93, (r1 - this.length) + 1, DataConversion.convert(r22[r1].getValue(r7, r8), r62));
        r1 = r1 + 1;
        goto L14
    L17:
        r63 = r93;
    L19:
        return r0;
    }

    public Class[] getParameterTypes() {
        return this.parameterTypes;
    }
}
