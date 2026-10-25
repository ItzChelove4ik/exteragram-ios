package com.google.gson;

import java.lang.reflect.Field;
import java.util.Locale;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes.dex */
public enum FieldNamingPolicy extends Enum<FieldNamingPolicy> implements FieldNamingStrategy {
    private static final /* synthetic */ FieldNamingPolicy[] $VALUES = null;
    public static final FieldNamingPolicy IDENTITY = null;
    public static final FieldNamingPolicy LOWER_CASE_WITH_DASHES = null;
    public static final FieldNamingPolicy LOWER_CASE_WITH_DOTS = null;
    public static final FieldNamingPolicy LOWER_CASE_WITH_UNDERSCORES = null;
    public static final FieldNamingPolicy UPPER_CAMEL_CASE = null;
    public static final FieldNamingPolicy UPPER_CAMEL_CASE_WITH_SPACES = null;
    public static final FieldNamingPolicy UPPER_CASE_WITH_UNDERSCORES = null;

    /* renamed from: com.google.gson.FieldNamingPolicy$1, reason: invalid class name */
    public enum AnonymousClass1 extends FieldNamingPolicy {
        public AnonymousClass1(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.gson.FieldNamingStrategy
        public String translateName(Field r1) {
            return r1.getName();
        }
    }

    /* renamed from: com.google.gson.FieldNamingPolicy$2, reason: invalid class name */
    public enum AnonymousClass2 extends FieldNamingPolicy {
        public AnonymousClass2(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.gson.FieldNamingStrategy
        public String translateName(Field r1) {
            return FieldNamingPolicy.upperCaseFirstLetter(r1.getName());
        }
    }

    /* renamed from: com.google.gson.FieldNamingPolicy$3, reason: invalid class name */
    public enum AnonymousClass3 extends FieldNamingPolicy {
        public AnonymousClass3(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.gson.FieldNamingStrategy
        public String translateName(Field r1) {
            return FieldNamingPolicy.upperCaseFirstLetter(FieldNamingPolicy.separateCamelCase(r1.getName(), ' '));
        }
    }

    /* renamed from: com.google.gson.FieldNamingPolicy$4, reason: invalid class name */
    public enum AnonymousClass4 extends FieldNamingPolicy {
        public AnonymousClass4(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.gson.FieldNamingStrategy
        public String translateName(Field r1) {
            return FieldNamingPolicy.separateCamelCase(r1.getName(), '_').toUpperCase(Locale.ENGLISH);
        }
    }

    /* renamed from: com.google.gson.FieldNamingPolicy$5, reason: invalid class name */
    public enum AnonymousClass5 extends FieldNamingPolicy {
        public AnonymousClass5(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.gson.FieldNamingStrategy
        public String translateName(Field r1) {
            return FieldNamingPolicy.separateCamelCase(r1.getName(), '_').toLowerCase(Locale.ENGLISH);
        }
    }

    /* renamed from: com.google.gson.FieldNamingPolicy$6, reason: invalid class name */
    public enum AnonymousClass6 extends FieldNamingPolicy {
        public AnonymousClass6(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.gson.FieldNamingStrategy
        public String translateName(Field r1) {
            return FieldNamingPolicy.separateCamelCase(r1.getName(), SignatureVisitor.SUPER).toLowerCase(Locale.ENGLISH);
        }
    }

    /* renamed from: com.google.gson.FieldNamingPolicy$7, reason: invalid class name */
    public enum AnonymousClass7 extends FieldNamingPolicy {
        public AnonymousClass7(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.gson.FieldNamingStrategy
        public String translateName(Field r1) {
            return FieldNamingPolicy.separateCamelCase(r1.getName(), '.').toLowerCase(Locale.ENGLISH);
        }
    }

    private static /* synthetic */ FieldNamingPolicy[] $values() {
        return new FieldNamingPolicy[]{IDENTITY, UPPER_CAMEL_CASE, UPPER_CAMEL_CASE_WITH_SPACES, UPPER_CASE_WITH_UNDERSCORES, LOWER_CASE_WITH_UNDERSCORES, LOWER_CASE_WITH_DASHES, LOWER_CASE_WITH_DOTS};
    }

    static {
        IDENTITY = new AnonymousClass1("IDENTITY", 0);
        UPPER_CAMEL_CASE = new AnonymousClass2("UPPER_CAMEL_CASE", 1);
        UPPER_CAMEL_CASE_WITH_SPACES = new AnonymousClass3("UPPER_CAMEL_CASE_WITH_SPACES", 2);
        UPPER_CASE_WITH_UNDERSCORES = new AnonymousClass4("UPPER_CASE_WITH_UNDERSCORES", 3);
        LOWER_CASE_WITH_UNDERSCORES = new AnonymousClass5("LOWER_CASE_WITH_UNDERSCORES", 4);
        LOWER_CASE_WITH_DASHES = new AnonymousClass6("LOWER_CASE_WITH_DASHES", 5);
        LOWER_CASE_WITH_DOTS = new AnonymousClass7("LOWER_CASE_WITH_DOTS", 6);
        $VALUES = $values();
    }

    FieldNamingPolicy(String r1, int r2) {
    }

    /* synthetic */ FieldNamingPolicy(String r1, int r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public static String separateCamelCase(String r5, char r6) {
        StringBuilder r0 = new StringBuilder();
        int r1 = r5.length();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L11;
        char r3 = r5.charAt(r2);
        if (Character.isUpperCase(r3) == false) goto L9;
        if (r0.length() == 0) goto L9;
        r0.append(r6);
    L9:
        r0.append(r3);
        r2 = r2 + 1;
        goto L3
    L11:
        return r0.toString();
    }

    public static String upperCaseFirstLetter(String r5) {
        int r0 = r5.length();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L16;
        char r3 = r5.charAt(r2);
        if (Character.isLetter(r3) == true) goto L7;
        r2 = r2 + 1;
        goto L3
    L7:
        if (Character.isUpperCase(r3) == true) goto L16;
        char r02 = Character.toUpperCase(r3);
        if (r2 != 0) goto L14;
        return r02 + r5.substring(1);
    L14:
        return r5.substring(0, r2) + r02 + r5.substring(r2 + 1);
    L16:
        return r5;
    }

    public static FieldNamingPolicy valueOf(String r1) {
        return (FieldNamingPolicy) Enum.valueOf(FieldNamingPolicy.class, r1);
    }

    public static FieldNamingPolicy[] values() {
        return (FieldNamingPolicy[]) $VALUES.clone();
    }
}
