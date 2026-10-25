package com.android.dx.rop.code;

import com.android.dx.util.Hex;

/* loaded from: classes4.dex */
public final class AccessFlags {
    public static final int ACC_ABSTRACT = 1024;
    public static final int ACC_ANNOTATION = 8192;
    public static final int ACC_BRIDGE = 64;
    public static final int ACC_CONSTRUCTOR = 65536;
    public static final int ACC_DECLARED_SYNCHRONIZED = 131072;
    public static final int ACC_ENUM = 16384;
    public static final int ACC_FINAL = 16;
    public static final int ACC_INTERFACE = 512;
    public static final int ACC_NATIVE = 256;
    public static final int ACC_PRIVATE = 2;
    public static final int ACC_PROTECTED = 4;
    public static final int ACC_PUBLIC = 1;
    public static final int ACC_STATIC = 8;
    public static final int ACC_STRICT = 2048;
    public static final int ACC_SUPER = 32;
    public static final int ACC_SYNCHRONIZED = 32;
    public static final int ACC_SYNTHETIC = 4096;
    public static final int ACC_TRANSIENT = 128;
    public static final int ACC_VARARGS = 128;
    public static final int ACC_VOLATILE = 64;
    public static final int CLASS_FLAGS = 30257;
    private static final int CONV_CLASS = 1;
    private static final int CONV_FIELD = 2;
    private static final int CONV_METHOD = 3;
    public static final int FIELD_FLAGS = 20703;
    public static final int INNER_CLASS_FLAGS = 30239;
    public static final int METHOD_FLAGS = 204287;

    private AccessFlags() {
    }

    public static String classString(int r2) {
        return humanHelper(r2, CLASS_FLAGS, 1);
    }

    public static String fieldString(int r2) {
        return humanHelper(r2, FIELD_FLAGS, 2);
    }

    private static String humanHelper(int r4, int r5, int r6) {
        StringBuilder r0 = new StringBuilder(80);
        int r1 = (~r5) & r4;
        int r42 = r4 & r5;
        if ((r42 & 1) == 0) goto L6;
        r0.append("|public");
    L6:
        if ((r42 & 2) == 0) goto L9;
        r0.append("|private");
    L9:
        if ((r42 & 4) == 0) goto L12;
        r0.append("|protected");
    L12:
        if ((r42 & 8) == 0) goto L15;
        r0.append("|static");
    L15:
        if ((r42 & 16) == 0) goto L18;
        r0.append("|final");
    L18:
        if ((r42 & 32) == 0) goto L23;
        if (r6 != 1) goto L21;
        r0.append("|super");
        goto L23
    L21:
        r0.append("|synchronized");
    L23:
        if ((r42 & 64) == 0) goto L28;
        if (r6 != 3) goto L26;
        r0.append("|bridge");
        goto L28
    L26:
        r0.append("|volatile");
    L28:
        if ((r42 & 128) == 0) goto L33;
        if (r6 != 3) goto L31;
        r0.append("|varargs");
        goto L33
    L31:
        r0.append("|transient");
    L33:
        if ((r42 & 256) == 0) goto L36;
        r0.append("|native");
    L36:
        if ((r42 & 512) == 0) goto L39;
        r0.append("|interface");
    L39:
        if ((r42 & 1024) == 0) goto L42;
        r0.append("|abstract");
    L42:
        if ((r42 & 2048) == 0) goto L45;
        r0.append("|strictfp");
    L45:
        if ((r42 & 4096) == 0) goto L48;
        r0.append("|synthetic");
    L48:
        if ((r42 & 8192) == 0) goto L51;
        r0.append("|annotation");
    L51:
        if ((r42 & 16384) == 0) goto L54;
        r0.append("|enum");
    L54:
        if ((65536 & r42) == 0) goto L57;
        r0.append("|constructor");
    L57:
        if ((r42 & 131072) == 0) goto L59;
        r0.append("|declared_synchronized");
    L59:
        if (r1 == 0) goto L61;
    L62:
        r0.append('|');
        r0.append(Hex.u2(r1));
    L64:
        return r0.substring(1);
    L61:
        if (r0.length() != 0) goto L64;
        goto L62
    }

    public static String innerClassString(int r2) {
        return humanHelper(r2, INNER_CLASS_FLAGS, 1);
    }

    public static boolean isAbstract(int r0) {
        if ((r0 & 1024) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isAnnotation(int r0) {
        if ((r0 & 8192) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isConstructor(int r1) {
        if ((r1 & 65536) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isDeclaredSynchronized(int r1) {
        if ((r1 & 131072) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isEnum(int r0) {
        if ((r0 & 16384) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isInterface(int r0) {
        if ((r0 & 512) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isNative(int r0) {
        if ((r0 & 256) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isPrivate(int r0) {
        if ((r0 & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isProtected(int r0) {
        if ((r0 & 4) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isPublic(int r1) {
        if ((r1 & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public static boolean isStatic(int r0) {
        if ((r0 & 8) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isSynchronized(int r0) {
        if ((r0 & 32) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static String methodString(int r2) {
        return humanHelper(r2, METHOD_FLAGS, 3);
    }
}
