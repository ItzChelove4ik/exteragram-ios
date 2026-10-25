package com.caverock.androidsvg;

/* loaded from: classes4.dex */
class NumberParser {
    private static final float[] negativePowersOf10 = null;
    private static final float[] positivePowersOf10 = null;
    private int pos;

    static {
        positivePowersOf10 = new float[]{1.0f, 10.0f, 100.0f, 1000.0f, 10000.0f, 100000.0f, 1000000.0f, 1.0E7f, 1.0E8f, 1.0E9f, 1.0E10f, 1.0E11f, 1.0E12f, 1.0E13f, 1.0E14f, 1.0E15f, 1.0E16f, 1.0E17f, 1.0E18f, 1.0E19f, 1.0E20f, 1.0E21f, 1.0E22f, 1.0E23f, 1.0E24f, 1.0E25f, 1.0E26f, 1.0E27f, 1.0E28f, 1.0E29f, 1.0E30f, 1.0E31f, 1.0E32f, 1.0E33f, 1.0E34f, 1.0E35f, 1.0E36f, 1.0E37f, 1.0E38f};
        negativePowersOf10 = new float[]{1.0f, 0.1f, 0.01f, 0.001f, 1.0E-4f, 1.0E-5f, 1.0E-6f, 1.0E-7f, 1.0E-8f, 1.0E-9f, 1.0E-10f, 1.0E-11f, 1.0E-12f, 1.0E-13f, 1.0E-14f, 1.0E-15f, 1.0E-16f, 1.0E-17f, 1.0E-18f, 1.0E-19f, 1.0E-20f, 1.0E-21f, 1.0E-22f, 1.0E-23f, 1.0E-24f, 1.0E-25f, 1.0E-26f, 1.0E-27f, 1.0E-28f, 1.0E-29f, 1.0E-30f, 1.0E-31f, 1.0E-32f, 1.0E-33f, 1.0E-34f, 1.0E-35f, 1.0E-36f, 1.0E-37f, 1.0E-38f};
    }

    public NumberParser() {
    }

    public int getEndPos() {
        return this.pos;
    }

    public float parseNumber(String r24, int r25, int r26) {
        this.pos = r25;
        if (r25 < r26) goto L5;
        return Float.NaN;
    L5:
        char r2 = r24.charAt(r25);
        if (r2 == '+') goto L10;
        if (r2 == '-') goto L9;
        boolean r22 = false;
    L12:
        int r9 = this.pos;
        long r7 = 0;
        int r12 = 0;
        int r13 = 0;
        int r14 = 0;
        boolean r15 = false;
        int r16 = 0;
    L13:
        int r4 = this.pos;
        if (r4 >= r26) goto L42;
        char r42 = r24.charAt(r4);
        if (r42 != '0') goto L21;
        if (r12 != 0) goto L19;
        r14 = r14 + 1;
    L41:
        this.pos++;
        goto L13
    L19:
        r13 = r13 + 1;
        goto L41
    L21:
        if (r42 < '1') goto L37;
        if (r42 > '9') goto L37;
        int r122 = r12 + r13;
    L25:
        if (r13 <= 0) goto L31;
        if (r7 > 922337203685477580L) goto L28;
        r7 = r7 * 10;
        r13 = r13 - 1;
        goto L25
    L28:
        return Float.NaN;
    L31:
        if (r7 > 922337203685477580L) goto L32;
        r7 = (r7 * 10) + (r42 - '0');
        r12 = r122 + 1;
        if (r7 >= 0) goto L41;
        return Float.NaN;
    L32:
        return Float.NaN;
    L37:
        if (r42 != '.') goto L42;
        if (r15 == true) goto L42;
        r16 = this.pos - r9;
        r15 = true;
    L42:
        if (r15 == true) goto L44;
    L46:
        if (r12 != 0) goto L50;
        if (r14 != 0) goto L49;
        return Float.NaN;
    L49:
        r12 = 1;
    L50:
        if (r15 == false) goto L52;
        r13 = (r16 - r14) - r12;
    L52:
        int r43 = this.pos;
        if (r43 >= r26) goto L88;
        char r44 = r24.charAt(r43);
        if (r44 != 'E') goto L57;
    L58:
        int r45 = this.pos + 1;
        this.pos = r45;
        if (r45 != r26) goto L61;
        return Float.NaN;
    L61:
        char r46 = r24.charAt(r45);
        if (r46 == '+') goto L69;
        if (r46 == '-') goto L68;
        switch(r46) {
            case 48: goto L66;
            case 49: goto L66;
            case 50: goto L66;
            case 51: goto L66;
            case 52: goto L66;
            case 53: goto L66;
            case 54: goto L66;
            case 55: goto L66;
            case 56: goto L66;
            case 57: goto L66;
            default: goto L65;
        };
    L65:
        this.pos--;
        boolean r5 = true;
        boolean r47 = false;
    L71:
        if (r5 == true) goto L88;
        int r52 = this.pos;
        int r6 = 0;
    L73:
        int r92 = this.pos;
        if (r92 >= r26) goto L83;
        char r93 = r24.charAt(r92);
        if (r93 < '0') goto L83;
        if (r93 > '9') goto L83;
        if (r6 > 922337203685477580L) goto L80;
        r6 = (r6 * 10) + (r93 - '0');
        this.pos++;
        goto L73
    L80:
        return Float.NaN;
    L83:
        if (this.pos != r52) goto L85;
        return Float.NaN;
    L85:
        if (r47 == false) goto L87;
        r13 = r13 - r6;
        goto L88
    L87:
        r13 = r13 + r6;
        goto L88
    L66:
        r47 = false;
    L67:
        r5 = false;
        goto L71
    L68:
        r47 = true;
    L70:
        this.pos++;
        goto L67
    L69:
        r47 = false;
        goto L70
    L57:
        if (r44 == 'e') goto L58;
    L88:
        int r123 = r12 + r13;
        if (r123 <= 39) goto L91;
    L106:
        return Float.NaN;
    L91:
        if (r123 < (-44)) goto L106;
        float r0 = r7;
        if (r7 == 0) goto L103;
        if (r13 <= 0) goto L98;
        float r1 = positivePowersOf10[r13];
    L97:
        r0 = r0 * r1;
        goto L103
    L98:
        if (r13 >= 0) goto L103;
        if (r13 >= (-38)) goto L102;
        r0 = (float) (r0 * 1.0E-20d);
        r13 = r13 + 20;
    L102:
        r1 = negativePowersOf10[-r13];
    L103:
        if (r22 == true) goto L105;
        return r0;
    L105:
        return -r0;
    L44:
        if (this.pos != (r16 + 1)) goto L46;
        return Float.NaN;
    L9:
        r22 = true;
    L11:
        this.pos++;
        goto L12
    L10:
        r22 = false;
        goto L11
    }
}
