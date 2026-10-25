package org.mvel2.conversion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import org.mvel2.ConversionHandler;

/* loaded from: classes5.dex */
public class IntegerCH implements ConversionHandler {
    private static final Map<Class, Converter> CNV = null;

    /* renamed from: org.mvel2.conversion.IntegerCH$1, reason: invalid class name */
    public class AnonymousClass1 implements Converter {
        public AnonymousClass1() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            if (((String) r1).length() != 0) goto L7;
            return 0;
        L7:
            return Integer.valueOf(Integer.parseInt(String.valueOf(r1)));
        }
    }

    /* renamed from: org.mvel2.conversion.IntegerCH$10, reason: invalid class name */
    public class AnonymousClass10 implements Converter {
        public AnonymousClass10() {
        }

        @Override // org.mvel2.conversion.Converter
        public Integer convert(Object r1) {
            if (((Boolean) r1).booleanValue() == false) goto L7;
            return 1;
        L7:
            return 0;
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.IntegerCH$11, reason: invalid class name */
    public class AnonymousClass11 implements Converter {
        public AnonymousClass11() {
        }

        @Override // org.mvel2.conversion.Converter
        public Integer convert(Object r1) {
            return Integer.valueOf(((Character) r1).charValue());
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.IntegerCH$2, reason: invalid class name */
    public class AnonymousClass2 implements Converter {
        public AnonymousClass2() {
        }

        @Override // org.mvel2.conversion.Converter
        public Integer convert(Object r1) {
            return Integer.valueOf(((BigDecimal) r1).intValue());
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.IntegerCH$3, reason: invalid class name */
    public class AnonymousClass3 implements Converter {
        public AnonymousClass3() {
        }

        @Override // org.mvel2.conversion.Converter
        public Integer convert(Object r1) {
            return Integer.valueOf(((BigInteger) r1).intValue());
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.IntegerCH$4, reason: invalid class name */
    public class AnonymousClass4 implements Converter {
        public AnonymousClass4() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return Integer.valueOf(Integer.parseInt((String) r1));
        }
    }

    /* renamed from: org.mvel2.conversion.IntegerCH$5, reason: invalid class name */
    public class AnonymousClass5 implements Converter {
        public AnonymousClass5() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return Integer.valueOf(((Short) r1).intValue());
        }
    }

    /* renamed from: org.mvel2.conversion.IntegerCH$6, reason: invalid class name */
    public class AnonymousClass6 implements Converter {
        public AnonymousClass6() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r5) {
            Long r4 = (Long) r5;
            if (r4.longValue() <= 2147483647L) goto L5;
            ShortCH$6$$ExternalSyntheticBUOutline0.m("cannot coerce Long to Integer since the value (", r5, ") exceeds that maximum precision of Integer.");
            return null;
        L5:
            return Integer.valueOf(r4.intValue());
        }
    }

    /* renamed from: org.mvel2.conversion.IntegerCH$7, reason: invalid class name */
    public class AnonymousClass7 implements Converter {
        public AnonymousClass7() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r3) {
            Float r2 = (Float) r3;
            if (r2.floatValue() <= 2.1474836E9f) goto L5;
            ShortCH$6$$ExternalSyntheticBUOutline0.m("cannot coerce Float to Integer since the value (", r3, ") exceeds that maximum precision of Integer.");
            return null;
        L5:
            return Integer.valueOf(r2.intValue());
        }
    }

    /* renamed from: org.mvel2.conversion.IntegerCH$8, reason: invalid class name */
    public class AnonymousClass8 implements Converter {
        public AnonymousClass8() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r5) {
            Double r4 = (Double) r5;
            if (r4.doubleValue() <= 2.147483647E9d) goto L5;
            ShortCH$6$$ExternalSyntheticBUOutline0.m("cannot coerce Long to Integer since the value (", r5, ") exceeds that maximum precision of Integer.");
            return null;
        L5:
            return Integer.valueOf(r4.intValue());
        }
    }

    /* renamed from: org.mvel2.conversion.IntegerCH$9, reason: invalid class name */
    public class AnonymousClass9 implements Converter {
        public AnonymousClass9() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return r1;
        }
    }

    static {
        HashMap r0 = new HashMap(10);
        CNV = r0;
        r0.put(Object.class, new AnonymousClass1());
        r0.put(BigDecimal.class, new AnonymousClass2());
        r0.put(BigInteger.class, new AnonymousClass3());
        r0.put(String.class, new AnonymousClass4());
        r0.put(Short.class, new AnonymousClass5());
        r0.put(Long.class, new AnonymousClass6());
        r0.put(Float.class, new AnonymousClass7());
        r0.put(Double.class, new AnonymousClass8());
        r0.put(Integer.class, new AnonymousClass9());
        r0.put(Boolean.class, new AnonymousClass10());
        r0.put(Character.class, new AnonymousClass11());
    }

    public IntegerCH() {
    }

    @Override // org.mvel2.ConversionHandler
    public boolean canConvertFrom(Class r1) {
        return CNV.containsKey(r1);
    }

    @Override // org.mvel2.ConversionHandler
    public Object convertFrom(Object r2) {
        Map<Class, Converter> r1 = CNV;
        if (r1.containsKey(r2.getClass()) == true) goto L5;
        ByteCH$$ExternalSyntheticBUOutline0.m(r2.getClass().getName(), Integer.class.getName());
        return null;
    L5:
        return r1.get(r2.getClass()).convert(r2);
    }
}
