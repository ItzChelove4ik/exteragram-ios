package org.mvel2.util;

/* loaded from: classes5.dex */
public class ReflectionUtil {
    public ReflectionUtil() {
    }

    private static boolean areBoxingCompatible(Class<?> r1, Class<?> r2) {
        if (r1.isPrimitive() == false) goto L7;
        return isPrimitiveOf(r2, r1);
    L7:
        if (r2.isPrimitive() == true) goto L9;
        return false;
    L9:
        if (isPrimitiveOf(r1, r2) == false) goto L14;
        return true;
    L14:
        return false;
    }

    public static String getGetter(String r6) {
        char[] r62 = r6.toCharArray();
        char[] r0 = new char[r62.length + 3];
        r0[0] = 'g';
        r0[1] = 'e';
        r0[2] = 't';
        r0[3] = Character.toUpperCase(r62[0]);
        System.arraycopy(r62, 1, r0, 4, r62.length - 1);
        return new String(r0);
    }

    public static String getIsGetter(String r5) {
        char[] r52 = r5.toCharArray();
        char[] r0 = new char[r52.length + 2];
        r0[0] = 'i';
        r0[1] = 's';
        r0[2] = Character.toUpperCase(r52[0]);
        System.arraycopy(r52, 1, r0, 3, r52.length - 1);
        return new String(r0);
    }

    public static String getPropertyFromAccessor(String r9) {
        char[] r0 = r9.toCharArray();
        int r5 = 1;
        if (r0.length <= 3) goto L17;
        if (r0[1] != 'e') goto L17;
        if (r0[2] != 't') goto L17;
        int r1 = r0.length - 3;
        char[] r4 = new char[r1];
        char r7 = r0[0];
        if (r7 == 'g') goto L11;
        if (r7 == 's') goto L11;
        return r9;
    L11:
        r4[0] = Character.toLowerCase(r0[3]);
    L12:
        if (r5 >= r1) goto L15;
        r4[r5] = r0[r5 + 3];
        r5 = r5 + 1;
        goto L12
    L15:
        return new String(r4);
    L17:
        if (r0.length > 2) goto L19;
        return r9;
    L19:
        if (r0[0] == 'i') goto L21;
        return r9;
    L21:
        if (r0[1] != 's') goto L32;
        int r92 = r0.length - 2;
        char[] r12 = new char[r92];
        r12[0] = Character.toLowerCase(r0[2]);
    L23:
        if (r5 >= r92) goto L26;
        r12[r5] = r0[r5 + 2];
        r5 = r5 + 1;
        goto L23
    L26:
        return new String(r12);
    L32:
        return r9;
    }

    public static String getSetter(String r6) {
        char[] r0 = new char[r6.length() + 3];
        r0[0] = 's';
        r0[1] = 'e';
        r0[2] = 't';
        r0[3] = Character.toUpperCase(r6.charAt(0));
        int r1 = r6.length() - 1;
    L3:
        if (r1 == 0) goto L6;
        r0[r1 + 3] = r6.charAt(r1);
        r1 = r1 - 1;
        goto L3
    L6:
        return new String(r0);
    }

    public static boolean isAssignableFrom(Class<?> r1, Class<?> r2) {
        if (r1.isAssignableFrom(r2) == false) goto L5;
        return true;
    L5:
        if (areBoxingCompatible(r1, r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    private static boolean isPrimitiveOf(Class<?> r3, Class<?> r4) {
        if (r4 != Integer.TYPE) goto L9;
        if (r3 != Integer.class) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (r4 != Long.TYPE) goto L15;
        if (r3 != Long.class) goto L13;
        return true;
    L13:
        return false;
    L15:
        if (r4 != Double.TYPE) goto L21;
        if (r3 != Double.class) goto L19;
        return true;
    L19:
        return false;
    L21:
        if (r4 != Float.TYPE) goto L27;
        if (r3 != Float.class) goto L25;
        return true;
    L25:
        return false;
    L27:
        if (r4 != Short.TYPE) goto L33;
        if (r3 != Short.class) goto L31;
        return true;
    L31:
        return false;
    L33:
        if (r4 != Byte.TYPE) goto L39;
        if (r3 != Byte.class) goto L37;
        return true;
    L37:
        return false;
    L39:
        if (r4 != Character.TYPE) goto L45;
        if (r3 != Character.class) goto L43;
        return true;
    L43:
        return false;
    L45:
        if (r4 == Boolean.TYPE) goto L47;
    L49:
        return false;
    L47:
        if (r3 != Boolean.class) goto L49;
        return true;
    }

    public static Class<?> toNonPrimitiveArray(Class<?> r1) {
        if (r1.isArray() == true) goto L5;
        return r1;
    L5:
        if (r1.getComponentType().isPrimitive() == true) goto L8;
        return r1;
    L8:
        if (r1 != int[].class) goto L12;
        return Integer[].class;
    L12:
        if (r1 != long[].class) goto L16;
        return Long[].class;
    L16:
        if (r1 != double[].class) goto L20;
        return Double[].class;
    L20:
        if (r1 != float[].class) goto L24;
        return Float[].class;
    L24:
        if (r1 != short[].class) goto L28;
        return Short[].class;
    L28:
        if (r1 != byte[].class) goto L32;
        return Byte[].class;
    L32:
        if (r1 != char[].class) goto L35;
        return Character[].class;
    L35:
        return Boolean[].class;
    }

    public static Class<?> toNonPrimitiveType(Class<?> r1) {
        if (r1.isPrimitive() == true) goto L6;
        return r1;
    L6:
        if (r1 != Integer.TYPE) goto L10;
        return Integer.class;
    L10:
        if (r1 != Long.TYPE) goto L14;
        return Long.class;
    L14:
        if (r1 != Double.TYPE) goto L18;
        return Double.class;
    L18:
        if (r1 != Float.TYPE) goto L22;
        return Float.class;
    L22:
        if (r1 != Short.TYPE) goto L26;
        return Short.class;
    L26:
        if (r1 != Byte.TYPE) goto L30;
        return Byte.class;
    L30:
        if (r1 != Character.TYPE) goto L33;
        return Character.class;
    L33:
        return Boolean.class;
    }

    public static Class<?> toPrimitiveArrayType(Class<?> r1) {
        if (r1.isPrimitive() == true) goto L5;
        ReflectionUtil$$ExternalSyntheticBUOutline0.m(r1, " is not a primitive type");
        return null;
    L5:
        if (r1 != Integer.TYPE) goto L9;
        return int[].class;
    L9:
        if (r1 != Long.TYPE) goto L13;
        return long[].class;
    L13:
        if (r1 != Double.TYPE) goto L17;
        return double[].class;
    L17:
        if (r1 != Float.TYPE) goto L21;
        return float[].class;
    L21:
        if (r1 != Short.TYPE) goto L25;
        return short[].class;
    L25:
        if (r1 != Byte.TYPE) goto L29;
        return byte[].class;
    L29:
        if (r1 != Character.TYPE) goto L32;
        return char[].class;
    L32:
        return boolean[].class;
    }
}
