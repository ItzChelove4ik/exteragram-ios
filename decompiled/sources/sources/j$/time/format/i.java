package j$.time.format;

import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class i implements e {
    public static final String[] d = null;
    public static final i e = null;
    public static final i f = null;

    /* renamed from: a, reason: collision with root package name */
    public final String f386a;

    /* renamed from: b, reason: collision with root package name */
    public final int f387b;

    /* renamed from: c, reason: collision with root package name */
    public final int f388c;

    static {
        d = new String[]{"+HH", "+HHmm", "+HH:mm", "+HHMM", "+HH:MM", "+HHMMss", "+HH:MM:ss", "+HHMMSS", "+HH:MM:SS", "+HHmmss", "+HH:mm:ss", "+H", "+Hmm", "+H:mm", "+HMM", "+H:MM", "+HMMss", "+H:MM:ss", "+HMMSS", "+H:MM:SS", "+Hmmss", "+H:mm:ss"};
        e = new i("+HH:MM:ss", "Z");
        f = new i("+HH:MM:ss", MVEL.VERSION_SUB);
    }

    public i(String r3, String r4) {
        int r0 = 0;
    L4:
        if (r0 >= 22) goto L10;
        if (d[r0].equals(r3) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        this.f387b = r0;
        this.f388c = r0 % 11;
        this.f386a = r4;
        return;
    L10:
        j$.nio.file.b.a("Invalid zone offset pattern: ".concat(r3));
        throw null;
    }

    public static void a(boolean r0, int r1, StringBuilder r2) {
        if (r0 == false) goto L4;
        String r02 = ":";
    L5:
        r2.append(r02);
        r2.append((char) ((r1 / 10) + 48));
        r2.append((char) ((r1 % 10) + 48));
        return;
    L4:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L5
    }

    public static boolean b(CharSequence r5, boolean r6, int r7, int[] r8) {
        int r1 = r8[0];
        if (r1 >= 0) goto L5;
        return true;
    L5:
        if (r6 == false) goto L13;
        if (r7 == 1) goto L13;
        int r62 = r1 + 1;
        if (r62 <= r5.length()) goto L10;
    L30:
        return false;
    L10:
        if (r5.charAt(r1) != ':') goto L30;
        r1 = r62;
    L13:
        int r63 = r1 + 2;
        if (r63 > r5.length()) goto L30;
        int r3 = r1 + 1;
        char r12 = r5.charAt(r1);
        char r52 = r5.charAt(r3);
        if (r12 < '0') goto L30;
        if (r12 > '9') goto L30;
        if (r52 < '0') goto L30;
        if (r52 > '9') goto L30;
        int r53 = r52 - '0';
        int r54 = r53 + ((r12 - '0') * 10);
        if (r54 < 0) goto L30;
        if (r54 > 59) goto L30;
        r8[r7] = r54;
        r8[0] = r63;
        return true;
    }

    public static void c(CharSequence r1, boolean r2, int[] r3) {
        if (r2 == true) goto L5;
        e(r1, 1, 2, r3);
        return;
    L5:
        if (b(r1, false, 1, r3) == true) goto L10;
        r3[0] = ~r3[0];
        return;
    }

    public static void d(CharSequence r1, boolean r2, boolean r3, int[] r4) {
        if (b(r1, r2, 2, r4) == true) goto L7;
        if (r3 == false) goto L8;
        r4[0] = ~r4[0];
        return;
    L8:
        return;
    }

    public static void e(CharSequence r9, int r10, int r11, int[] r12) {
        int r1 = r12[0];
        char[] r2 = new char[r11];
        int r3 = 0;
        int r4 = 0;
    L4:
        if (r3 >= r11) goto L14;
        int r6 = r1 + 1;
        if (r6 > r9.length()) goto L14;
        char r7 = r9.charAt(r1);
        if (r7 < '0') goto L14;
        if (r7 > '9') goto L14;
        r2[r3] = r7;
        r4 = r4 + 1;
        r3 = r3 + 1;
        r1 = r6;
    L14:
        if (r4 >= r10) goto L18;
        r12[0] = ~r12[0];
        return;
    L18:
        switch(r4) {
            case 1: goto L25;
            case 2: goto L24;
            case 3: goto L23;
            case 4: goto L22;
            case 5: goto L21;
            case 6: goto L20;
            default: goto L26;
        };
    L20:
        r12[1] = (r2[1] - '0') + ((r2[0] - '0') * 10);
        r12[2] = (r2[3] - '0') + ((r2[2] - '0') * 10);
        r12[3] = (r2[5] - '0') + ((r2[4] - '0') * 10);
        goto L26
    L21:
        r12[1] = r2[0] - '0';
        r12[2] = (r2[2] - '0') + ((r2[1] - '0') * 10);
        r12[3] = (r2[4] - '0') + ((r2[3] - '0') * 10);
        goto L26
    L22:
        r12[1] = (r2[1] - '0') + ((r2[0] - '0') * 10);
        r12[2] = (r2[3] - '0') + ((r2[2] - '0') * 10);
        goto L26
    L23:
        r12[1] = r2[0] - '0';
        r12[2] = (r2[2] - '0') + ((r2[1] - '0') * 10);
        goto L26
    L24:
        r12[1] = (r2[1] - '0') + ((r2[0] - '0') * 10);
        goto L26
    L25:
        r12[1] = r2[0] - '0';
    L26:
        r12[0] = r1;
    }

    @Override // j$.time.format.e
    public final int D(r r13, CharSequence r14, int r15) {
        int r0 = r14.length();
        int r7 = this.f386a.length();
        if (r7 != 0) goto L8;
        if (r15 == r0) goto L6;
        r r1 = r13;
        CharSequence r3 = r14;
        int r4 = r15;
    L16:
        char r132 = r3.charAt(r4);
        if (r132 == '+') goto L22;
        if (r132 == '-') goto L22;
    L20:
        r r02 = r1;
        if (r7 != 0) goto L94;
        return r02.f(j$.time.temporal.a.OFFSET_SECONDS, 0, r4, r4);
    L94:
        return ~r4;
    L22:
        if (r132 != '-') goto L24;
        int r133 = -1;
    L25:
        int r5 = this.f388c;
        if (r5 > 0) goto L28;
    L30:
        boolean r52 = false;
    L31:
        int r12 = this.f387b;
        if (r12 >= 11) goto L34;
        boolean r8 = true;
    L35:
        int[] r10 = new int[4];
        r10[0] = r4 + 1;
        if (r1.f405c == true) goto L61;
        if (r8 == false) goto L49;
        if (r52 == true) goto L47;
        if (r12 != 0) goto L46;
        int r122 = r4 + 3;
        if (r0 <= r122) goto L46;
        if (r3.charAt(r122) == ':') goto L47;
    L46:
        r12 = 9;
    L47:
        r12 = 10;
    L48:
        r52 = true;
        goto L61
    L49:
        if (r52 == true) goto L59;
        if (r12 != 11) goto L58;
        int r123 = r4 + 3;
        if (r0 <= r123) goto L58;
        if (r3.charAt(r4 + 2) == ':') goto L59;
        if (r3.charAt(r123) == ':') goto L59;
    L58:
        r12 = 20;
    L59:
        r12 = 21;
    L61:
        switch(r12) {
            case 0: goto L77;
            case 1: goto L76;
            case 2: goto L76;
            case 3: goto L75;
            case 4: goto L75;
            case 5: goto L74;
            case 6: goto L74;
            case 7: goto L71;
            case 8: goto L71;
            case 9: goto L68;
            case 10: goto L68;
            case 11: goto L77;
            case 12: goto L67;
            case 13: goto L76;
            case 14: goto L66;
            case 15: goto L75;
            case 16: goto L65;
            case 17: goto L74;
            case 18: goto L64;
            case 19: goto L71;
            case 20: goto L63;
            case 21: goto L68;
            default: goto L78;
        };
    L63:
        e(r3, 1, 6, r10);
        goto L78
    L64:
        e(r3, 5, 6, r10);
        goto L78
    L65:
        e(r3, 3, 6, r10);
        goto L78
    L66:
        e(r3, 3, 4, r10);
        goto L78
    L67:
        e(r3, 1, 4, r10);
        goto L78
    L68:
        c(r3, r8, r10);
        if (b(r3, r52, 2, r10) == false) goto L78;
        b(r3, r52, 3, r10);
        goto L78
    L71:
        c(r3, r8, r10);
        d(r3, r52, true, r10);
        if (b(r3, r52, 3, r10) == true) goto L78;
        r10[0] = ~r10[0];
        goto L78
    L74:
        c(r3, r8, r10);
        d(r3, r52, true, r10);
        b(r3, r52, 3, r10);
        goto L78
    L75:
        c(r3, r8, r10);
        d(r3, r52, true, r10);
        goto L78
    L76:
        c(r3, r8, r10);
        d(r3, r52, false, r10);
        goto L78
    L77:
        c(r3, r8, r10);
    L78:
        int r53 = r10[0];
        if (r53 <= 0) goto L20;
        int r124 = r10[1];
        if (r124 > 23) goto L88;
        int r142 = r10[2];
        if (r142 > 59) goto L88;
        int r03 = r10[3];
        if (r03 > 59) goto L88;
        return r1.f(j$.time.temporal.a.OFFSET_SECONDS, r133 * (((r142 * 60) + (r124 * 3600)) + r03), r4, r53);
    L88:
        j$.nio.file.b.h("Value out of range: Hour[0-23], Minute[0-59], Second[0-59]");
        return 0;
    L34:
        r8 = false;
        goto L35
    L28:
        if ((r5 % 2) != 0) goto L30;
        r52 = true;
        goto L31
    L24:
        r133 = 1;
        goto L25
    L6:
        return r13.f(j$.time.temporal.a.OFFSET_SECONDS, 0, r15, r15);
    L8:
        r1 = r13;
        r4 = r15;
        if (r4 == r0) goto L11;
        r3 = r14;
        if (r1.g(r3, r4, this.f386a, 0, r7) == false) goto L16;
        return r1.f(j$.time.temporal.a.OFFSET_SECONDS, 0, r4, r4 + r7);
    L11:
        return ~r4;
    }

    @Override // j$.time.format.e
    public final boolean s(t r9, StringBuilder r10) {
        Long r92 = r9.a(j$.time.temporal.a.OFFSET_SECONDS);
        boolean r0 = false;
        if (r92 != null) goto L5;
        return false;
    L5:
        int r93 = Math.toIntExact(r92.longValue());
        String r2 = this.f386a;
        if (r93 != 0) goto L9;
        r10.append(r2);
        return true;
    L9:
        int r3 = Math.abs((r93 / 3600) % 100);
        int r4 = Math.abs((r93 / 60) % 60);
        int r5 = Math.abs(r93 % 60);
        int r6 = r10.length();
        if (r93 >= 0) goto L12;
        String r94 = "-";
    L13:
        r10.append(r94);
        if (this.f387b >= 11) goto L17;
    L18:
        a(false, r3, r10);
    L20:
        int r8 = this.f388c;
        if (r8 < 3) goto L24;
        if (r8 > 8) goto L24;
    L28:
        if (r8 > 0) goto L30;
    L32:
        boolean r95 = false;
    L33:
        a(r95, r4, r10);
        r3 = r3 + r4;
        if (r8 == 7) goto L39;
        if (r8 == 8) goto L39;
        if (r8 < 5) goto L44;
        if (r5 > 0) goto L39;
    L44:
        if (r3 != 0) goto L46;
        r10.setLength(r6);
        r10.append(r2);
    L46:
        return true;
    L39:
        if (r8 > 0) goto L41;
    L43:
        a(r0, r5, r10);
        r3 = r3 + r5;
        goto L44
    L41:
        if ((r8 % 2) != 0) goto L43;
        r0 = true;
        goto L43
    L30:
        if ((r8 % 2) != 0) goto L32;
        r95 = true;
    L24:
        if (r8 < 9) goto L26;
        if (r5 > 0) goto L28;
    L26:
        if (r8 < 1) goto L44;
        if (r4 <= 0) goto L44;
    L17:
        if (r3 >= 10) goto L18;
        r10.append((char) (r3 + 48));
        goto L20
    L12:
        r94 = "+";
        goto L13
    }

    public final String toString() {
        String r0 = this.f386a.replace("'", "''");
        return "Offset(" + d[this.f387b] + ",'" + r0 + "')";
    }
}
