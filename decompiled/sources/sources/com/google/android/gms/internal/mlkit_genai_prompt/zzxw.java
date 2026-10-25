package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.common.DownloadCallback;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* loaded from: classes4.dex */
final /* synthetic */ class zzxw implements Function0 {
    private final /* synthetic */ AtomicInteger zza;
    private final /* synthetic */ int zzb;
    private final /* synthetic */ DownloadCallback zzc;

    public /* synthetic */ zzxw(AtomicInteger r1, int r2, DownloadCallback r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* synthetic */ Object invoke() {
        if (this.zza.incrementAndGet() != this.zzb) goto L6;
        this.zzc.onDownloadCompleted();
    L6:
        return Unit.INSTANCE;
    }
}
