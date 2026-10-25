package j$.desugar.sun.nio.fs;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public abstract class p {

    /* renamed from: a, reason: collision with root package name */
    public static final long f170a = 0;

    /* renamed from: b, reason: collision with root package name */
    public static final long f171b = 0;

    /* renamed from: c, reason: collision with root package name */
    public static final char[] f172c = null;

    static {
        int r0 = Math.max(Math.min(48, 63), 0);
        long r2 = 0;
    L3:
        if (r0 > Math.max(Math.min(57, 63), 0)) goto L5;
        r2 = r2 | (1 << r0);
        r0 = r0 + 1;
        goto L3
    L5:
        long r02 = b('A', 'Z') | b('a', 'z');
        long r5 = d("-_.!~*'()");
        long r03 = r02 | c("-_.!~*'()");
        long r22 = (r2 | r5) | d(":@&=+$,");
        long r04 = r03 | c(":@&=+$,");
        f170a = r22 | d(";/");
        f171b = r04 | c(";/");
        f172c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    public static int a(char r2) {
        if (r2 < '0') goto L9;
        if (r2 > '9') goto L9;
        return r2 - '0';
    L9:
        if (r2 < 'a') goto L15;
        if (r2 > 'f') goto L15;
        return r2 - 'W';
    L15:
        if (r2 < 'A') goto L21;
        if (r2 > 'F') goto L21;
        return r2 - '7';
    L21:
        throw new AssertionError();
    }

    public static long b(char r4, char r5) {
        int r42 = Math.max(Math.min(r4, 127), 64) - 64;
        long r0 = 0;
    L3:
        if (r42 > (Math.max(Math.min(r5, 127), 64) - 64)) goto L5;
        r0 = r0 | (1 << r42);
        r42 = r42 + 1;
        goto L3
    L5:
        return r0;
    }

    public static long c(String r7) {
        int r0 = r7.length();
        long r1 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L10;
        char r4 = r7.charAt(r3);
        if (r4 < '@') goto L9;
        if (r4 >= 128) goto L9;
        r1 = r1 | (1 << (r4 - '@'));
    L9:
        r3 = r3 + 1;
        goto L3
    L10:
        return r1;
    }

    public static long d(String r7) {
        int r0 = r7.length();
        long r1 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L8;
        char r4 = r7.charAt(r3);
        if (r4 >= '@') goto L7;
        r1 = r1 | (1 << r4);
    L7:
        r3 = r3 + 1;
        goto L3
    L8:
        return r1;
    }
}
