package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.mlkit.genai.common.DownloadCallback;
import com.google.mlkit.genai.common.DownloadStatus;
import com.google.mlkit.genai.common.GenAiException;
import kotlinx.coroutines.channels.ProducerScope;

/* loaded from: classes4.dex */
public final class zzym implements DownloadCallback {
    final /* synthetic */ ProducerScope zza;

    public zzym(ProducerScope r1) {
        this.zza = r1;
    }

    @Override // com.google.mlkit.genai.common.DownloadCallback
    public final void onDownloadCompleted() {
        ProducerScope r1 = this.zza;
        r1.mo3532trySendJP2dKIU(DownloadStatus.DownloadCompleted.INSTANCE);
        r1.close(null);
    }

    @Override // com.google.mlkit.genai.common.DownloadCallback
    public final void onDownloadFailed(GenAiException r2) {
        DownloadStatus.DownloadFailed r0 = new DownloadStatus.DownloadFailed(r2);
        ProducerScope r1 = this.zza;
        r1.mo3532trySendJP2dKIU(r0);
        r1.close(r2);
    }

    @Override // com.google.mlkit.genai.common.DownloadCallback
    public final void onDownloadProgress(long r2) {
        DownloadStatus.DownloadProgress r0 = new DownloadStatus.DownloadProgress(r2);
        this.zza.mo3532trySendJP2dKIU(r0);
    }

    @Override // com.google.mlkit.genai.common.DownloadCallback
    public final void onDownloadStarted(long r2) {
        DownloadStatus.DownloadStarted r0 = new DownloadStatus.DownloadStarted(r2);
        this.zza.mo3532trySendJP2dKIU(r0);
    }
}
