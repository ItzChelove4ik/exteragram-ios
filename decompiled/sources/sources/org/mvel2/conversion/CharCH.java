package org.mvel2.conversion;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.mvel2.ConversionException;
import org.mvel2.ConversionHandler;

/* loaded from: classes5.dex */
public class CharCH implements ConversionHandler {
    private static final Map<Class, Converter> CNV = null;
    private static final Converter stringConverter = null;

    /* renamed from: org.mvel2.conversion.CharCH$1, reason: invalid class name */
    public class AnonymousClass1 implements Converter {
        public AnonymousClass1() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r2) {
            String r22 = (String) r2;
            if (r22.length() > 1) goto L7;
            return Character.valueOf(r22.charAt(0));
        L7:
            throw new ConversionException("cannot convert a string with a length greater than 1 to java.lang.Character");
        }
    }

    /* renamed from: org.mvel2.conversion.CharCH$2, reason: invalid class name */
    public class AnonymousClass2 implements Converter {
        public AnonymousClass2() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return CharCH.access$000().convert(String.valueOf(r1));
        }
    }

    /* renamed from: org.mvel2.conversion.CharCH$3, reason: invalid class name */
    public class AnonymousClass3 implements Converter {
        public AnonymousClass3() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return new Character(((Character) r1).charValue());
        }
    }

    /* renamed from: org.mvel2.conversion.CharCH$4, reason: invalid class name */
    public class AnonymousClass4 implements Converter {
        public AnonymousClass4() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return Character.valueOf((char) ((BigDecimal) r1).intValue());
        }
    }

    /* renamed from: org.mvel2.conversion.CharCH$5, reason: invalid class name */
    public class AnonymousClass5 implements Converter {
        public AnonymousClass5() {
        }

        @Override // org.mvel2.conversion.Converter
        public Object convert(Object r1) {
            return Character.valueOf((char) ((Integer) r1).intValue());
        }
    }

    static {
        HashMap r0 = new HashMap();
        CNV = r0;
        AnonymousClass1 r1 = new AnonymousClass1();
        stringConverter = r1;
        r0.put(String.class, r1);
        r0.put(Object.class, new AnonymousClass2());
        r0.put(Character.class, new AnonymousClass3());
        r0.put(BigDecimal.class, new AnonymousClass4());
        r0.put(Integer.class, new AnonymousClass5());
    }

    public CharCH() {
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
