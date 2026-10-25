package org.mvel2.conversion;

import java.util.HashMap;
import java.util.Map;
import org.mvel2.ConversionHandler;

/* loaded from: classes5.dex */
public class ByteCH implements ConversionHandler {
    private static final Map<Class, Converter> CNV = null;
    private static Converter stringConverter;

    /* renamed from: org.mvel2.conversion.ByteCH$1, reason: invalid class name */
    public class AnonymousClass1 implements Converter {
        public AnonymousClass1() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return Byte.valueOf(Byte.parseByte((String) r1));
        }
    }

    /* renamed from: org.mvel2.conversion.ByteCH$2, reason: invalid class name */
    public class AnonymousClass2 implements Converter {
        public AnonymousClass2() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return ByteCH.access$000().convert(String.valueOf(r1));
        }
    }

    /* renamed from: org.mvel2.conversion.ByteCH$3, reason: invalid class name */
    public class AnonymousClass3 implements Converter {
        public AnonymousClass3() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return new Byte(((Byte) r1).byteValue());
        }
    }

    /* renamed from: org.mvel2.conversion.ByteCH$4, reason: invalid class name */
    public class AnonymousClass4 implements Converter {
        public AnonymousClass4() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return Byte.valueOf(((Integer) r1).byteValue());
        }
    }

    /* renamed from: org.mvel2.conversion.ByteCH$5, reason: invalid class name */
    public class AnonymousClass5 implements Converter {
        public AnonymousClass5() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return Byte.valueOf(((Long) r1).byteValue());
        }
    }

    /* renamed from: org.mvel2.conversion.ByteCH$6, reason: invalid class name */
    public class AnonymousClass6 implements Converter {
        public AnonymousClass6() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return Byte.valueOf(((Double) r1).byteValue());
        }
    }

    /* renamed from: org.mvel2.conversion.ByteCH$7, reason: invalid class name */
    public class AnonymousClass7 implements Converter {
        public AnonymousClass7() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return Byte.valueOf(((Float) r1).byteValue());
        }
    }

    /* renamed from: org.mvel2.conversion.ByteCH$8, reason: invalid class name */
    public class AnonymousClass8 implements Converter {
        public AnonymousClass8() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return Byte.valueOf(((Short) r1).byteValue());
        }
    }

    static {
        HashMap r0 = new HashMap();
        CNV = r0;
        AnonymousClass1 r1 = new AnonymousClass1();
        stringConverter = r1;
        r0.put(String.class, r1);
        r0.put(Object.class, new AnonymousClass2());
        r0.put(Byte.class, new AnonymousClass3());
        r0.put(Integer.class, new AnonymousClass4());
        r0.put(Long.class, new AnonymousClass5());
        r0.put(Double.class, new AnonymousClass6());
        r0.put(Float.class, new AnonymousClass7());
        r0.put(Short.class, new AnonymousClass8());
    }

    public ByteCH() {
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
