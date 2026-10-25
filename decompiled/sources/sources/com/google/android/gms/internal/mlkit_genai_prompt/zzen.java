package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
public abstract class zzen {
    public static /* synthetic */ String zza(int r1) {
        if (r1 != 1) goto L5;
        return "BLOCK_ATTENTION";
    L5:
        if (r1 != 2) goto L7;
        return "PREFIX_CACHING";
    L7:
        if (r1 != 3) goto L9;
        return "INFERENCE_WITH_SESSION_STATE_CACHING";
    L9:
        if (r1 != 4) goto L11;
        return "INFERENCE_WITH_PREFIX_CACHING";
    L11:
        if (r1 == 5) goto L14;
        return "UNSPECIFIED";
    L14:
        return "INFERENCE_WITH_BLOCK_ATTENTION";
    }
}
