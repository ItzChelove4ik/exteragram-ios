package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.common.DownloadCallback;
import com.google.mlkit.genai.common.DownloadStatus;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes4.dex */
final class zzzk implements FlowCollector {
    final /* synthetic */ DownloadCallback zza;

    public zzzk(DownloadCallback r1) {
        this.zza = r1;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final /* bridge */ /* synthetic */ Object emit(Object r1, Continuation r2) {
        DownloadStatus r12 = (DownloadStatus) r1;
        if ((r12 instanceof DownloadStatus.DownloadStarted) == false) goto L6;
        this.zza.onDownloadStarted(((DownloadStatus.DownloadStarted) r12).getBytesToDownload());
    L15:
        return Unit.INSTANCE;
    L6:
        if ((r12 instanceof DownloadStatus.DownloadProgress) == false) goto L9;
        this.zza.onDownloadProgress(((DownloadStatus.DownloadProgress) r12).getTotalBytesDownloaded());
        goto L15
    L9:
        if ((r12 instanceof DownloadStatus.DownloadFailed) == false) goto L12;
        this.zza.onDownloadFailed(((DownloadStatus.DownloadFailed) r12).getE());
        goto L15
    L12:
        if ((r12 instanceof DownloadStatus.DownloadCompleted) == false) goto L16;
        this.zza.onDownloadCompleted();
        goto L15
    L16:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    }
}
