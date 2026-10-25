package org.mvel2.conversion;

import java.util.HashMap;
import java.util.Map;
import org.mvel2.ConversionHandler;

/* loaded from: classes5.dex */
public class PrimIntArrayCH implements ConversionHandler {
    private static final Map<Class, Converter> CNV = null;

    /* renamed from: org.mvel2.conversion.PrimIntArrayCH$1, reason: invalid class name */
    public class AnonymousClass1 implements Converter {
        public AnonymousClass1() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r3) {
            String[] r32 = (String[]) r3;
            int[] r2 = new int[r32.length];
            int r0 = 0;
        L4:
            if (r0 >= r32.length) goto L6;
            r2[r0] = Integer.parseInt(r32[r0]);
            r0 = r0 + 1;
            goto L4
        L6:
            return r2;
        }
    }

    /* renamed from: org.mvel2.conversion.PrimIntArrayCH$2, reason: invalid class name */
    public class AnonymousClass2 implements Converter {
        public AnonymousClass2() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r3) {
            Object[] r32 = (Object[]) r3;
            int[] r2 = new int[r32.length];
            int r0 = 0;
        L4:
            if (r0 >= r32.length) goto L6;
            r2[r0] = Integer.parseInt(String.valueOf(r32[r0]));
            r0 = r0 + 1;
            goto L4
        L6:
            return r2;
        }
    }

    /* renamed from: org.mvel2.conversion.PrimIntArrayCH$3, reason: invalid class name */
    public class AnonymousClass3 implements Converter {
        public AnonymousClass3() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r3) {
            Integer[] r32 = (Integer[]) r3;
            int[] r2 = new int[r32.length];
            int r0 = 0;
        L4:
            if (r0 >= r32.length) goto L6;
            r2[r0] = r32[r0].intValue();
            r0 = r0 + 1;
            goto L4
        L6:
            return r2;
        }
    }

    static {
        HashMap r0 = new HashMap();
        CNV = r0;
        r0.put(String[].class, new AnonymousClass1());
        r0.put(Object[].class, new AnonymousClass2());
        r0.put(Integer[].class, new AnonymousClass3());
    }

    public PrimIntArrayCH() {
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
