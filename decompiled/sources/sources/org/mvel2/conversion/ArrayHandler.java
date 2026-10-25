package org.mvel2.conversion;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import org.mvel2.ConversionHandler;
import org.mvel2.DataConversion;

/* loaded from: classes5.dex */
public class ArrayHandler implements ConversionHandler {
    private final Class type;

    public ArrayHandler(Class r1) {
        this.type = r1;
    }

    private static Object handleLooseTypeConversion(Class r3, Object r4, Class r5) {
        Class<?> r52 = r5.getComponentType();
        int r0 = 0;
        if (Collection.class.isAssignableFrom(r3) == false) goto L10;
        Collection r42 = (Collection) r4;
        Object r32 = Array.newInstance(r52, r42.size());
        Iterator r43 = r42.iterator();
    L6:
        if (r43.hasNext() == false) goto L8;
        Array.set(r32, r0, DataConversion.convert(r43.next(), r52));
        r0 = r0 + 1;
        goto L6
    L8:
        return r32;
    L10:
        if (r4.getClass().isArray() == true) goto L13;
        Object r33 = Array.newInstance(r52, 1);
        Array.set(r33, 0, DataConversion.convert(r4, r52));
        return r33;
    L13:
        int r34 = Array.getLength(r4);
        Object r1 = Array.newInstance(r52, r34);
    L14:
        if (r0 >= r34) goto L16;
        Array.set(r1, r0, DataConversion.convert(Array.get(r4, r0), r52));
        r0 = r0 + 1;
        goto L14
    L16:
        return r1;
    }

    @Override // org.mvel2.ConversionHandler
    public boolean canConvertFrom(Class r1) {
        if (r1.isArray() == false) goto L5;
        return true;
    L5:
        if (Collection.class.isAssignableFrom(r1) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // org.mvel2.ConversionHandler
    public Object convertFrom(Object r2) {
        return handleLooseTypeConversion(r2.getClass(), r2, this.type);
    }
}
