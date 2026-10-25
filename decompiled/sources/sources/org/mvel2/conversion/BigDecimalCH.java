package org.mvel2.conversion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;
import org.mvel2.ConversionHandler;

/* loaded from: classes5.dex */
public class BigDecimalCH implements ConversionHandler {
    private static final Map<Class, Converter> CNV = null;

    /* renamed from: org.mvel2.conversion.BigDecimalCH$1, reason: invalid class name */
    public class AnonymousClass1 implements Converter {
        public AnonymousClass1() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public BigDecimal convert(Object r2) {
            return new BigDecimal(String.valueOf(r2), MathContext.DECIMAL128);
        }
    }

    /* renamed from: org.mvel2.conversion.BigDecimalCH$10, reason: invalid class name */
    public class AnonymousClass10 implements Converter {
        public AnonymousClass10() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public BigDecimal convert(Object r2) {
            return new BigDecimal((String) r2, MathContext.DECIMAL128);
        }
    }

    /* renamed from: org.mvel2.conversion.BigDecimalCH$11, reason: invalid class name */
    public class AnonymousClass11 implements Converter {
        public AnonymousClass11() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public BigDecimal convert(Object r2) {
            return new BigDecimal((char[]) r2, MathContext.DECIMAL128);
        }
    }

    /* renamed from: org.mvel2.conversion.BigDecimalCH$2, reason: invalid class name */
    public class AnonymousClass2 implements Converter {
        public AnonymousClass2() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public BigDecimal convert(Object r1) {
            return (BigDecimal) r1;
        }
    }

    /* renamed from: org.mvel2.conversion.BigDecimalCH$3, reason: invalid class name */
    public class AnonymousClass3 implements Converter {
        public AnonymousClass3() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public BigDecimal convert(Object r3) {
            return new BigDecimal(((BigInteger) r3).doubleValue(), MathContext.DECIMAL128);
        }
    }

    /* renamed from: org.mvel2.conversion.BigDecimalCH$4, reason: invalid class name */
    public class AnonymousClass4 implements Converter {
        public AnonymousClass4() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public BigDecimal convert(Object r2) {
            return new BigDecimal((String) r2, MathContext.DECIMAL128);
        }
    }

    /* renamed from: org.mvel2.conversion.BigDecimalCH$5, reason: invalid class name */
    public class AnonymousClass5 implements Converter {
        public AnonymousClass5() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public BigDecimal convert(Object r3) {
            return new BigDecimal(((Double) r3).doubleValue(), MathContext.DECIMAL128);
        }
    }

    /* renamed from: org.mvel2.conversion.BigDecimalCH$6, reason: invalid class name */
    public class AnonymousClass6 implements Converter {
        public AnonymousClass6() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public BigDecimal convert(Object r3) {
            return new BigDecimal(((Float) r3).doubleValue(), MathContext.DECIMAL128);
        }
    }

    /* renamed from: org.mvel2.conversion.BigDecimalCH$7, reason: invalid class name */
    public class AnonymousClass7 implements Converter {
        public AnonymousClass7() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public BigDecimal convert(Object r3) {
            return new BigDecimal(((Short) r3).doubleValue(), MathContext.DECIMAL128);
        }
    }

    /* renamed from: org.mvel2.conversion.BigDecimalCH$8, reason: invalid class name */
    public class AnonymousClass8 implements Converter {
        public AnonymousClass8() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public BigDecimal convert(Object r3) {
            return new BigDecimal(((Long) r3).doubleValue(), MathContext.DECIMAL128);
        }
    }

    /* renamed from: org.mvel2.conversion.BigDecimalCH$9, reason: invalid class name */
    public class AnonymousClass9 implements Converter {
        public AnonymousClass9() {
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }

        @Override // org.mvel2.conversion.Converter
        public BigDecimal convert(Object r3) {
            return new BigDecimal(((Integer) r3).doubleValue(), MathContext.DECIMAL128);
        }
    }

    static {
        HashMap r0 = new HashMap();
        CNV = r0;
        r0.put(Object.class, new AnonymousClass1());
        r0.put(BigDecimal.class, new AnonymousClass2());
        r0.put(BigInteger.class, new AnonymousClass3());
        r0.put(String.class, new AnonymousClass4());
        r0.put(Double.class, new AnonymousClass5());
        r0.put(Float.class, new AnonymousClass6());
        r0.put(Short.class, new AnonymousClass7());
        r0.put(Long.class, new AnonymousClass8());
        r0.put(Integer.class, new AnonymousClass9());
        r0.put(String.class, new AnonymousClass10());
        r0.put(char[].class, new AnonymousClass11());
    }

    public BigDecimalCH() {
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
