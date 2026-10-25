package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
public final class zzayp extends RuntimeException {
    public zzayp(zzaxw r1) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzaxm zza() {
        return new zzaxm(getMessage());
    }
}
