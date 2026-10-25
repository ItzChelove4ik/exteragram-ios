package org.mvel2.conversion;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.mvel2.ConversionHandler;
import org.mvel2.MVEL;

/* loaded from: classes5.dex */
public class BooleanCH implements ConversionHandler {
    private static final Map<Class, Converter> CNV = null;
    private static Converter stringConverter;

    /* renamed from: org.mvel2.conversion.BooleanCH$1, reason: invalid class name */
    public class AnonymousClass1 implements Converter {
        public AnonymousClass1() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r2) {
            String r1 = (String) r2;
            if (r1.equalsIgnoreCase("false") == false) goto L5;
        L13:
            boolean r12 = false;
        L15:
            return Boolean.valueOf(r12);
        L5:
            if (r1.equalsIgnoreCase("no") == true) goto L13;
            if (r1.equalsIgnoreCase("off") == true) goto L13;
            if (MVEL.VERSION_SUB.equals(r2) == true) goto L13;
            if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r2) == true) goto L13;
            r12 = true;
            goto L15
        }
    }

    /* renamed from: org.mvel2.conversion.BooleanCH$10, reason: invalid class name */
    public class AnonymousClass10 implements Converter {
        public AnonymousClass10() {
        }

        @Override // org.mvel2.conversion.Converter
        public Boolean convert(Object r3) {
            if (((BigDecimal) r3).doubleValue() <= 0.0d) goto L5;
            boolean r2 = true;
        L7:
            return Boolean.valueOf(r2);
        L5:
            r2 = false;
            goto L7
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.BooleanCH$2, reason: invalid class name */
    public class AnonymousClass2 implements Converter {
        public AnonymousClass2() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return BooleanCH.access$000().convert(String.valueOf(r1));
        }
    }

    /* renamed from: org.mvel2.conversion.BooleanCH$3, reason: invalid class name */
    public class AnonymousClass3 implements Converter {
        public AnonymousClass3() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return r1;
        }
    }

    /* renamed from: org.mvel2.conversion.BooleanCH$4, reason: invalid class name */
    public class AnonymousClass4 implements Converter {
        public AnonymousClass4() {
        }

        @Override // org.mvel2.conversion.Converter
        public Boolean convert(Object r1) {
            if (((Integer) r1).intValue() <= 0) goto L5;
            boolean r0 = true;
        L7:
            return Boolean.valueOf(r0);
        L5:
            r0 = false;
            goto L7
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.BooleanCH$5, reason: invalid class name */
    public class AnonymousClass5 implements Converter {
        public AnonymousClass5() {
        }

        @Override // org.mvel2.conversion.Converter
        public Boolean convert(Object r1) {
            if (((Float) r1).floatValue() <= 0.0f) goto L5;
            boolean r0 = true;
        L7:
            return Boolean.valueOf(r0);
        L5:
            r0 = false;
            goto L7
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.BooleanCH$6, reason: invalid class name */
    public class AnonymousClass6 implements Converter {
        public AnonymousClass6() {
        }

        @Override // org.mvel2.conversion.Converter
        public Boolean convert(Object r3) {
            if (((Double) r3).doubleValue() <= 0.0d) goto L5;
            boolean r2 = true;
        L7:
            return Boolean.valueOf(r2);
        L5:
            r2 = false;
            goto L7
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.BooleanCH$7, reason: invalid class name */
    public class AnonymousClass7 implements Converter {
        public AnonymousClass7() {
        }

        @Override // org.mvel2.conversion.Converter
        public Boolean convert(Object r1) {
            if (((Short) r1).shortValue() <= 0) goto L5;
            boolean r0 = true;
        L7:
            return Boolean.valueOf(r0);
        L5:
            r0 = false;
            goto L7
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.BooleanCH$8, reason: invalid class name */
    public class AnonymousClass8 implements Converter {
        public AnonymousClass8() {
        }

        @Override // org.mvel2.conversion.Converter
        public Boolean convert(Object r3) {
            if (((Long) r3).longValue() <= 0) goto L5;
            boolean r2 = true;
        L7:
            return Boolean.valueOf(r2);
        L5:
            r2 = false;
            goto L7
        }

        @Override // org.mvel2.conversion.Converter
        public /* bridge */ /* synthetic */ Object convert(Object r1) {
            return convert(r1);
        }
    }

    /* renamed from: org.mvel2.conversion.BooleanCH$9, reason: invalid class name */
    public class AnonymousClass9 implements Converter {
        public AnonymousClass9() {
        }

        @Override // org.mvel2.conversion.Converter
        public Boolean convert(Object r1) {
            Boolean r12 = (Boolean) r1;
            r12.booleanValue();
            return r12;
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
        r0.put(Boolean.class, new AnonymousClass3());
        r0.put(Integer.class, new AnonymousClass4());
        r0.put(Float.class, new AnonymousClass5());
        r0.put(Double.class, new AnonymousClass6());
        r0.put(Short.class, new AnonymousClass7());
        r0.put(Long.class, new AnonymousClass8());
        AnonymousClass9 r12 = new AnonymousClass9();
        r0.put(Boolean.TYPE, r12);
        r0.put(BigDecimal.class, new AnonymousClass10());
    }

    public BooleanCH() {
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
        ByteCH$$ExternalSyntheticBUOutline0.m(r2.getClass().getName(), Boolean.class.getName());
        return null;
    L5:
        return r1.get(r2.getClass()).convert(r2);
    }
}
