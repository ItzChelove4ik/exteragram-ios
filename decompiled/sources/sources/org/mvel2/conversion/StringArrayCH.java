package org.mvel2.conversion;

import java.util.HashMap;
import java.util.Map;
import org.mvel2.ConversionHandler;

/* loaded from: classes5.dex */
public class StringArrayCH implements ConversionHandler {
    private static final Map<Class, Converter> CNV = null;

    /* renamed from: org.mvel2.conversion.StringArrayCH$1, reason: invalid class name */
    public class AnonymousClass1 implements Converter {
        public AnonymousClass1() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r3) {
            Object[] r32 = (Object[]) r3;
            String[] r2 = new String[r32.length];
            int r0 = 0;
        L4:
            if (r0 >= r32.length) goto L6;
            r2[r0] = String.valueOf(r32[r0]);
            r0 = r0 + 1;
            goto L4
        L6:
            return r2;
        }
    }

    static {
        HashMap r0 = new HashMap();
        CNV = r0;
        r0.put(Object[].class, new AnonymousClass1());
    }

    public StringArrayCH() {
    }

    @Override // org.mvel2.ConversionHandler
    public boolean canConvertFrom(Class r1) {
        return CNV.containsKey(r1);
    }

    @Override // org.mvel2.ConversionHandler
    public Object convertFrom(Object r3) {
        if (r3.getClass().isArray() == false) goto L10;
        Object[] r32 = (Object[]) r3;
        String[] r2 = new String[r32.length];
        int r0 = 0;
    L6:
        if (r0 >= r32.length) goto L8;
        r2[r0] = String.valueOf(r32[r0]);
        r0 = r0 + 1;
        goto L6
    L8:
        return r2;
    L10:
        return new String[]{String.valueOf(r3)};
    }
}
