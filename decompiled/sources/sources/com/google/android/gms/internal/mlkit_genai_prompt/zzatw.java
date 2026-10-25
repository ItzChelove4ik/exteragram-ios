package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.SystemClock;
import com.google.mlkit.genai.common.StreamingCallback;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes4.dex */
final class zzatw implements StreamingCallback {
    final /* synthetic */ AtomicLong zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ StreamingCallback zzc;

    public zzatw(zzaun r1, AtomicLong r2, long r3, StreamingCallback r5) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r5;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.mlkit.genai.common.StreamingCallback
    public final void onNewText(String r6) {
        long r0 = SystemClock.elapsedRealtime() - this.zzb;
        this.zza.compareAndSet(0, r0);
        this.zzc.onNewText(r6);
    }

    @Override // com.google.mlkit.genai.common.StreamingCallback
    public final void onNewThought(String r1) {
        this.zzc.onNewThought(r1);
    }
}
