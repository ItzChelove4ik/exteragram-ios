package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.common.StreamingCallback;

/* loaded from: classes4.dex */
final /* synthetic */ class zzyk implements zzik {
    private final /* synthetic */ StreamingCallback zza;

    public /* synthetic */ zzyk(StreamingCallback r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzik
    public final /* synthetic */ void zza(int r2, String r3) {
        StreamingCallback r1 = this.zza;
        if (r2 != 1) goto L6;
        r1.onNewThought(r3);
        return;
    L6:
        r1.onNewText(r3);
    }
}
