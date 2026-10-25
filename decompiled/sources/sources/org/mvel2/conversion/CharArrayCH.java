package org.mvel2.conversion;

import java.util.HashMap;
import java.util.Map;
import org.mvel2.ConversionHandler;

/* loaded from: classes5.dex */
public class CharArrayCH implements ConversionHandler {
    private static final Map<Class, Converter> CNV = null;

    /* renamed from: org.mvel2.conversion.CharArrayCH$1, reason: invalid class name */
    public class AnonymousClass1 implements Converter {
        public AnonymousClass1() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return ((String) r1).toCharArray();
        }
    }

    static {
        HashMap r0 = new HashMap();
        CNV = r0;
        r0.put(String.class, new AnonymousClass1());
    }

    public CharArrayCH() {
    }

    @Override // org.mvel2.ConversionHandler
    public boolean canConvertFrom(Class r1) {
        return CNV.containsKey(r1);
    }

    @Override // org.mvel2.ConversionHandler
    public Object convertFrom(Object r2) {
        Map<Class, Converter> r1 = CNV;
        if (r1.containsKey(r2.getClass()) == true) goto L5;
        ByteCH$$ExternalSyntheticBUOutline0.m(r2.getClass().getName(), Boolean.class.getName());
        return null;
    L5:
        return r1.get(r2.getClass()).convert(r2);
    }
}
