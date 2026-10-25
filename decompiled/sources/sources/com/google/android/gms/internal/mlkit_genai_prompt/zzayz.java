package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
abstract class zzayz {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        int r0 = zzavq.$r8$clinit;
    }

    public static boolean zza(byte[] r8, int r9, int r10) {
    L2:
        if (r9 >= r10) goto L7;
        if (r8[r9] < 0) goto L7;
        r9 = r9 + 1;
    L7:
        if (r9 < r10) goto L9;
        return true;
    L9:
        if (r9 >= r10) goto L10;
        int r1 = r9 + 1;
        byte r2 = r8[r9];
        if (r2 < 0) goto L14;
        r9 = r1;
        goto L9
    L14:
        if (r2 < (-32)) goto L15;
        if (r2 < (-16)) goto L25;
        if (r1 >= (r10 - 2)) goto L44;
        int r3 = r9 + 2;
        byte r12 = r8[r1];
        if (r12 > (-65)) goto L53;
        if ((((r2 << 28) + (r12 + 112)) >> 30) != 0) goto L53;
        int r13 = r9 + 3;
        if (r8[r3] > (-65)) goto L53;
        r9 = r9 + 4;
        if (r8[r13] <= (-65)) goto L9;
    L53:
        return false;
    L44:
        return false;
    L25:
        if (r1 >= (r10 - 1)) goto L26;
        int r6 = r9 + 2;
        byte r14 = r8[r1];
        if (r14 > (-65)) goto L41;
        if (r2 != (-32)) goto L35;
        if (r14 >= (-96)) goto L35;
        return false;
    L35:
        if (r2 != (-19)) goto L39;
        if (r14 < (-96)) goto L39;
        return false;
    L39:
        r9 = r9 + 3;
        if (r8[r6] <= (-65)) goto L9;
    L41:
        return false;
    L26:
        return false;
    L15:
        if (r1 >= r10) goto L16;
        if (r2 < (-62)) goto L21;
        r9 = r9 + 2;
        if (r8[r1] <= (-65)) goto L9;
    L21:
        return false;
    L16:
        return false;
    L10:
        return true;
    }
}
