package org.mvel2.optimizers.impl.refl.collection;

import java.util.HashMap;
import java.util.Map;
import org.mvel2.compiler.Accessor;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class MapCreator implements Accessor {
    private Accessor[] keys;
    private int size;
    private Accessor[] vals;

    public MapCreator(Accessor[] r1, Accessor[] r2) {
        this.keys = r1;
        this.size = r1.length;
        this.vals = r2;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return Map.class;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r5, Object r6, VariableResolverFactory r7) {
        HashMap r0 = new HashMap(this.size * 2);
        int r1 = this.size;
    L3:
        r1 = r1 - 1;
        if (r1 == (-1)) goto L6;
        r0.put(this.keys[r1].getValue(r5, r6, r7), this.vals[r1].getValue(r5, r6, r7));
        goto L3
    L6:
        return r0;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        return null;
    }
}
