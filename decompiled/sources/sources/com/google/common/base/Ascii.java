package com.google.common.base;

/* loaded from: classes.dex */
public abstract class Ascii {
    public static boolean equalsIgnoreCase(CharSequence r7, CharSequence r8) {
        int r0 = r7.length();
        if (r7 != r8) goto L6;
        return true;
    L6:
        if (r0 == r8.length()) goto L8;
        return false;
    L8:
        int r2 = 0;
    L9:
        if (r2 >= r0) goto L19;
        char r4 = r7.charAt(r2);
        char r5 = r8.charAt(r2);
        if (r4 == r5) goto L17;
        int r42 = getAlphaIndex(r4);
        if (r42 >= 26) goto L18;
        if (r42 == getAlphaIndex(r5)) goto L17;
    L18:
        return false;
    L17:
        r2 = r2 + 1;
        goto L9
    L19:
        return true;
    }

    private static int getAlphaIndex(char r0) {
        return (char) ((r0 | ' ') - 97);
    }

    public static boolean isLowerCase(char r1) {
        if (r1 >= 'a') goto L5;
        return false;
    L5:
        if (r1 > 'z') goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isUpperCase(char r1) {
        if (r1 >= 'A') goto L5;
        return false;
    L5:
        if (r1 > 'Z') goto L10;
        return true;
    L10:
        return false;
    }

    public static String toLowerCase(String r4) {
        int r0 = r4.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L15;
        if (isUpperCase(r4.charAt(r1)) == true) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        char[] r42 = r4.toCharArray();
    L7:
        if (r1 >= r0) goto L13;
        char r2 = r42[r1];
        if (isUpperCase(r2) == false) goto L11;
        r42[r1] = (char) (r2 ^ ' ');
    L11:
        r1 = r1 + 1;
        goto L7
    L13:
        return String.valueOf(r42);
    L15:
        return r4;
    }

    public static String toUpperCase(String r4) {
        int r0 = r4.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L15;
        if (isLowerCase(r4.charAt(r1)) == true) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        char[] r42 = r4.toCharArray();
    L7:
        if (r1 >= r0) goto L13;
        char r2 = r42[r1];
        if (isLowerCase(r2) == false) goto L11;
        r42[r1] = (char) (r2 ^ ' ');
    L11:
        r1 = r1 + 1;
        goto L7
    L13:
        return String.valueOf(r42);
    L15:
        return r4;
    }
}
