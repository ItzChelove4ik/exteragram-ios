package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
public abstract class zziv {
    public static /* synthetic */ String zza(int r1) {
        if (r1 != 1) goto L5;
        return "STRINGVALUE";
    L5:
        if (r1 != 2) goto L7;
        return "BOOLVALUE";
    L7:
        if (r1 != 3) goto L9;
        return "INTVALUE";
    L9:
        if (r1 != 4) goto L11;
        return "DOUBLEVALUE";
    L11:
        if (r1 == 5) goto L14;
        return "LONGVALUE";
    L14:
        return "LISTVALUE";
    }
}
