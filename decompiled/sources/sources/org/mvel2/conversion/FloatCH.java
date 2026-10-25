package org.mvel2.conversion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import org.mvel2.ConversionHandler;

/* loaded from: classes5.dex */
public class FloatCH implements ConversionHandler {
    private static final Map<Class, Converter> CNV = null;
    private static Converter stringConverter;

    /* renamed from: org.mvel2.conversion.FloatCH$1, reason: invalid class name */
    public class AnonymousClass1 implements Converter {
        public AnonymousClass1() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            String r12 = (String) r1;
            if (r12.length() != 0) goto L7;
            return Float.valueOf(0.0f);
        L7:
            return Float.valueOf(Float.parseFloat(r12));
        }
    }

    /* renamed from: org.mvel2.conversion.FloatCH$10, reason: invalid class name */
    public class AnonymousClass10 implements Converter {
        public AnonymousClass10() {
        }

        @Override // org.mvel2.conversion.Converter
        public Float convert(Object r1) {
            if (((Boolean) r1).booleanValue() == false) goto L7;
            return Float.valueOf(1.0f);
        L7:
            return Float.valueOf(0.0f);
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.FloatCH$2, reason: invalid class name */
    public class AnonymousClass2 implements Converter {
        public AnonymousClass2() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return FloatCH.access$000().convert(String.valueOf(r1));
        }
    }

    /* renamed from: org.mvel2.conversion.FloatCH$3, reason: invalid class name */
    public class AnonymousClass3 implements Converter {
        public AnonymousClass3() {
        }

        @Override // org.mvel2.conversion.Converter
        public Float convert(Object r1) {
            return Float.valueOf(((BigDecimal) r1).floatValue());
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.FloatCH$4, reason: invalid class name */
    public class AnonymousClass4 implements Converter {
        public AnonymousClass4() {
        }

        @Override // org.mvel2.conversion.Converter
        public Float convert(Object r1) {
            return Float.valueOf(((BigInteger) r1).floatValue());
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.FloatCH$5, reason: invalid class name */
    public class AnonymousClass5 implements Converter {
        public AnonymousClass5() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return r1;
        }
    }

    /* renamed from: org.mvel2.conversion.FloatCH$6, reason: invalid class name */
    public class AnonymousClass6 implements Converter {
        public AnonymousClass6() {
        }

        @Override // org.mvel2.conversion.Converter
        public Float convert(Object r1) {
            return Float.valueOf(((Integer) r1).floatValue());
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.FloatCH$7, reason: invalid class name */
    public class AnonymousClass7 implements Converter {
        public AnonymousClass7() {
        }

        @Override // org.mvel2.conversion.Converter
        public Float convert(Object r1) {
            return Float.valueOf(((Double) r1).floatValue());
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.FloatCH$8, reason: invalid class name */
    public class AnonymousClass8 implements Converter {
        public AnonymousClass8() {
        }

        @Override // org.mvel2.conversion.Converter
        public Float convert(Object r1) {
            return Float.valueOf(((Long) r1).floatValue());
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.FloatCH$9, reason: invalid class name */
    public class AnonymousClass9 implements Converter {
        public AnonymousClass9() {
        }

        @Override // org.mvel2.conversion.Converter
        public Float convert(Object r1) {
            return Float.valueOf(((Short) r1).floatValue());
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    static {
        HashMap r0 = new HashMap();
        CNV = r0;
        AnonymousClass1 r1 = new AnonymousClass1();
        stringConverter = r1;
        r0.put(String.class, r1);
        r0.put(Object.class, new AnonymousClass2());
        r0.put(BigDecimal.class, new AnonymousClass3());
        r0.put(BigInteger.class, new AnonymousClass4());
        r0.put(Float.class, new AnonymousClass5());
        r0.put(Integer.class, new AnonymousClass6());
        r0.put(Double.class, new AnonymousClass7());
        r0.put(Long.class, new AnonymousClass8());
        r0.put(Short.class, new AnonymousClass9());
        r0.put(Boolean.class, new AnonymousClass10());
    }

    public FloatCH() {
    }

    public static /* synthetic */ Converter access$000() {
        return stringConverter;
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
