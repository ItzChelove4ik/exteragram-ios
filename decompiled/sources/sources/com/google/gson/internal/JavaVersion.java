package com.google.gson.internal;

/* loaded from: classes5.dex */
public abstract class JavaVersion {
    private static final int majorJavaVersion = 0;

    static {
        majorJavaVersion = determineMajorJavaVersion();
    }

    private static int determineMajorJavaVersion() {
        return parseMajorJavaVersion(System.getProperty("java.version"));
    }

    private static int extractBeginningInt(String r4) {
        StringBuilder r0 = new StringBuilder();     // Catch: NumberFormatException -> L10
        int r1 = 0;
    L4:
        if (r1 >= r4.length()) goto L8;
        char r2 = r4.charAt(r1);     // Catch: NumberFormatException -> L10
        if (Character.isDigit(r2) == false) goto L8;
        r0.append(r2);     // Catch: NumberFormatException -> L10
        r1 = r1 + 1;     // Catch: NumberFormatException -> L10
    L8:
        return Integer.parseInt(r0.toString());
    L10:
        return -1;
    }

    public static boolean isJava9OrLater() {
        if (majorJavaVersion < 9) goto L6;
        return true;
    L6:
        return false;
    }

    private static int parseDotted(String r3) {
        String[] r32 = r3.split("[._]", 3);     // Catch: NumberFormatException -> L9
        int r0 = Integer.parseInt(r32[0]);     // Catch: NumberFormatException -> L9
        if (r0 == 1) goto L5;
    L8:
        return r0;
    L5:
        if (r32.length <= 1) goto L8;
        return Integer.parseInt(r32[1]);
    L9:
        return -1;
    }

    public static int parseMajorJavaVersion(String r2) {
        int r0 = parseDotted(r2);
        if (r0 != (-1)) goto L5;
        r0 = extractBeginningInt(r2);
    L5:
        if (r0 != (-1)) goto L8;
        return 6;
    L8:
        return r0;
    }
}
