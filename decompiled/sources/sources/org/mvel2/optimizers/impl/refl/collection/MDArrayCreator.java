package org.mvel2.optimizers.impl.refl.collection;

import java.lang.reflect.Array;
import org.mvel2.compiler.Accessor;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class MDArrayCreator implements Accessor {
    private Class arrayType;
    private int dimension;
    public Accessor[] template;

    public MDArrayCreator(Accessor[] r1, Class r2, int r3) {
        this.template = r1;
        this.arrayType = r2;
        this.dimension = r3;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this.arrayType;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r5, Object r6, VariableResolverFactory r7) {
        int r1 = 0;
        if (Object.class.equals(this.arrayType) == false) goto L8;
        int r0 = this.template.length;
        Object[] r2 = new Object[r0];
    L5:
        if (r1 >= r0) goto L7;
        r2[r1] = this.template[r1].getValue(r5, r6, r7);
        r1 = r1 + 1;
        goto L5
    L7:
        return r2;
    L8:
        Object r02 = Array.newInstance(this.arrayType, this.template.length);
    L9:
        Accessor[] r22 = this.template;
        if (r1 >= r22.length) goto L12;
        Array.set(r02, r1, r22[r1].getValue(r5, r6, r7));
        r1 = r1 + 1;
        goto L9
    L12:
        return r02;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        return null;
    }
}
