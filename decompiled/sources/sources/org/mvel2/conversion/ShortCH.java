package org.mvel2.conversion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import org.mvel2.ConversionHandler;

/* loaded from: classes5.dex */
public class ShortCH implements ConversionHandler {
    private static final Map<Class, Converter> CNV = null;
    private static final Short FALSE = null;
    private static final Short TRUE = null;
    private static Converter stringConverter;

    /* renamed from: org.mvel2.conversion.ShortCH$1, reason: invalid class name */
    public class AnonymousClass1 implements Converter {
        public AnonymousClass1() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public Short convert(Object r1) {
            return Short.valueOf(Short.parseShort((String) r1));
        }
    }

    /* renamed from: org.mvel2.conversion.ShortCH$10, reason: invalid class name */
    public class AnonymousClass10 implements Converter {
        public AnonymousClass10() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public Short convert(Object r1) {
            if (((Boolean) r1).booleanValue() == false) goto L7;
            return ShortCH.access$100();
        L7:
            return ShortCH.access$200();
        }
    }

    /* renamed from: org.mvel2.conversion.ShortCH$2, reason: invalid class name */
    public class AnonymousClass2 implements Converter {
        public AnonymousClass2() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return ShortCH.access$000().convert(String.valueOf(r1));
        }
    }

    /* renamed from: org.mvel2.conversion.ShortCH$3, reason: invalid class name */
    public class AnonymousClass3 implements Converter {
        public AnonymousClass3() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public Short convert(Object r1) {
            return Short.valueOf(((BigDecimal) r1).shortValue());
        }
    }

    /* renamed from: org.mvel2.conversion.ShortCH$4, reason: invalid class name */
    public class AnonymousClass4 implements Converter {
        public AnonymousClass4() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public Short convert(Object r1) {
            return Short.valueOf(((BigInteger) r1).shortValue());
        }
    }

    /* renamed from: org.mvel2.conversion.ShortCH$5, reason: invalid class name */
    public class AnonymousClass5 implements Converter {
        public AnonymousClass5() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return r1;
        }
    }

    /* renamed from: org.mvel2.conversion.ShortCH$6, reason: invalid class name */
    public class AnonymousClass6 implements Converter {
        public AnonymousClass6() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public Short convert(Object r3) {
            Integer r2 = (Integer) r3;
            if (r2.intValue() <= 32767) goto L5;
            ShortCH$6$$ExternalSyntheticBUOutline0.m("cannot coerce Integer to Short since the value (", r3, ") exceeds that maximum precision of Integer.");
            return null;
        L5:
            return Short.valueOf(r2.shortValue());
        }
    }

    /* renamed from: org.mvel2.conversion.ShortCH$7, reason: invalid class name */
    public class AnonymousClass7 implements Converter {
        public AnonymousClass7() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public Short convert(Object r3) {
            Float r2 = (Float) r3;
            if (r2.floatValue() <= 32767.0f) goto L5;
            ShortCH$6$$ExternalSyntheticBUOutline0.m("cannot coerce Float to Short since the value (", r3, ") exceeds that maximum precision of Integer.");
            return null;
        L5:
            return Short.valueOf(r2.shortValue());
        }
    }

    /* renamed from: org.mvel2.conversion.ShortCH$8, reason: invalid class name */
    public class AnonymousClass8 implements Converter {
        public AnonymousClass8() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public Short convert(Object r5) {
            Double r4 = (Double) r5;
            if (r4.doubleValue() <= 32767.0d) goto L5;
            ShortCH$6$$ExternalSyntheticBUOutline0.m("cannot coerce Double to Short since the value (", r5, ") exceeds that maximum precision of Integer.");
            return null;
        L5:
            return Short.valueOf(r4.shortValue());
        }
    }

    /* renamed from: org.mvel2.conversion.ShortCH$9, reason: invalid class name */
    public class AnonymousClass9 implements Converter {
        public AnonymousClass9() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public Short convert(Object r5) {
            Long r4 = (Long) r5;
            if (r4.longValue() <= 32767) goto L5;
            ShortCH$6$$ExternalSyntheticBUOutline0.m("cannot coerce Integer to Short since the value (", r5, ") exceeds that maximum precision of Integer.");
            return null;
        L5:
            return Short.valueOf(r4.shortValue());
        }
    }

    static {
        TRUE = 1;
        FALSE = 0;
        stringConverter = new AnonymousClass1();
        HashMap r0 = new HashMap();
        CNV = r0;
        r0.put(String.class, stringConverter);
        r0.put(Object.class, new AnonymousClass2());
        r0.put(BigDecimal.class, new AnonymousClass3());
        r0.put(BigInteger.class, new AnonymousClass4());
        r0.put(Short.class, new AnonymousClass5());
        r0.put(Integer.class, new AnonymousClass6());
        r0.put(Float.class, new AnonymousClass7());
        r0.put(Double.class, new AnonymousClass8());
        r0.put(Long.class, new AnonymousClass9());
        r0.put(Boolean.class, new AnonymousClass10());
    }

    public ShortCH() {
    }

    public static /* synthetic */ Converter access$000() {
        return stringConverter;
    }

    public static /* synthetic */ Short access$100() {
        return TRUE;
    }

    public static /* synthetic */ Short access$200() {
        return FALSE;
    }

    @Override // org.mvel2.ConversionHandler
    public boolean canConvertFrom(Class r1) {
        return CNV.containsKey(r1);
    }

    @Override // org.mvel2.ConversionHandler
    public Object convertFrom(Object r2) {
        Map<Class, Converter> r1 = CNV;
        if (r1.containsKey(r2.getClass()) == true) goto L5;
        ByteCH$$ExternalSyntheticBUOutline0.m(r2.getClass().getName(), Short.class.getName());
        return null;
    L5:
        return r1.get(r2.getClass()).convert(r2);
    }
}
