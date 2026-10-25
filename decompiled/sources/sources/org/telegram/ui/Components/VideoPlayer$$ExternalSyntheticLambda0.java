package org.telegram.ui.Components;

import androidx.media3.exoplayer.ExoPlayer;

/* loaded from: classes7.dex */
public final /* synthetic */ class VideoPlayer$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ExoPlayer f$0;

    public /* synthetic */ VideoPlayer$$ExternalSyntheticLambda0(ExoPlayer r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.release();
    }
}
