package org.mvel2.conversion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import org.mvel2.ConversionHandler;

/* loaded from: classes5.dex */
public class ListCH implements ConversionHandler {
    public ListCH() {
    }

    @Override // org.mvel2.ConversionHandler
    public boolean canConvertFrom(Class r1) {
        if (r1.isArray() == false) goto L5;
        return true;
    L5:
        if (Collection.class.isAssignableFrom(r1) == false) goto L7;
        return true;
    L7:
        if (Iterable.class.isAssignableFrom(r1) == true) goto L14;
        return false;
    L14:
        return true;
    }

    @Override // org.mvel2.ConversionHandler
    public Object convertFrom(Object r3) {
        Class<?> r2 = r3.getClass();
        ArrayList r0 = new ArrayList();
        if (r2.isArray() == false) goto L7;
        r0.addAll(Arrays.asList((Object[]) r3));
        return r0;
    L7:
        if (Collection.class.isAssignableFrom(r2) == false) goto L11;
        r0.addAll((Collection) r3);
        return r0;
    L11:
        if (Iterable.class.isAssignableFrom(r2) == false) goto L16;
        Iterator r22 = ((Iterable) r3).iterator();
    L14:
        if (r22.hasNext() == false) goto L16;
        r0.add(r22.next());
    L16:
        return r0;
    }
}
