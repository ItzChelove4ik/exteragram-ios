package org.mvel2.util;

import java.lang.reflect.Field;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class StaticFieldStub implements StaticStub {
    private final Object cachedValue;
    private final Field field;

    public StaticFieldStub(Field r4) {
        this.field = r4;
        if (r4.isAccessible() == true) goto L5;
    L11:
        StaticFieldStub$$ExternalSyntheticBUOutline0.m("not an accessible static field: ", r4.getDeclaringClass().getName(), ".", r4.getName());
        throw null;
    L5:
        if ((r4.getModifiers() & 8) == 0) goto L11;
        this.cachedValue = r4.get(null);     // Catch: IllegalAccessException -> L8
        return;
    L8:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("error accessing static field", e);
        throw null;
    }

    @Override // org.mvel2.util.StaticStub
    public Object call(Object r1, Object r2, VariableResolverFactory r3, Object[] r4) {
        return this.cachedValue;
    }
}
