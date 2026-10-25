package org.telegram.ui.Stories.recorder;

import androidx.annotation.Keep;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;

@Keep
/* loaded from: classes7.dex */
public class FfmpegAudioWaveformLoader {
    private Utilities.Callback2<short[], Integer> onChunkReceived;

    @Keep
    private volatile boolean running;

    public static /* synthetic */ void $r8$lambda$TjnKgYSrZJ0mHs8hhi9MizfX7ek(FfmpegAudioWaveformLoader r0) {
        r0.lambda$destroy$2();
    }

    public static /* synthetic */ void $r8$lambda$pGHDjfFcQcEf3gA1fgE6tbgn8vI(FfmpegAudioWaveformLoader r0, String r1, int r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: $r8$lambda$sCtZaBYr4688cHt6Pfc7gp-lDG4, reason: not valid java name */
    public static /* synthetic */ void m19994$r8$lambda$sCtZaBYr4688cHt6Pfc7gplDG4(FfmpegAudioWaveformLoader r0, short[] r1, int r2) {
        r0.lambda$receiveChunk$1(r1, r2);
    }

    public FfmpegAudioWaveformLoader(String r2, int r3, Utilities.Callback2<short[], Integer> r4) {
        this.running = true;
        this.onChunkReceived = r4;
        Utilities.phoneBookQueue.postRunnable(new FfmpegAudioWaveformLoader$$ExternalSyntheticLambda0(this, r2, r3));
    }

    private native void init(String r1, int r2);

    private /* synthetic */ void lambda$destroy$2() {
        this.running = false;
    }

    private /* synthetic */ void lambda$new$0(String r1, int r2) {
        init(r1, r2);
    }

    private /* synthetic */ void lambda$receiveChunk$1(short[] r1, int r2) {
        this.onChunkReceived.run(r1, Integer.valueOf(r2));
    }

    @Keep
    private void receiveChunk(short[] r2, int r3) {
        AndroidUtilities.runOnUIThread(new FfmpegAudioWaveformLoader$$ExternalSyntheticLambda2(this, r2, r3));
    }

    public void destroy() {
        Utilities.phoneBookQueue.postRunnable(new FfmpegAudioWaveformLoader$$ExternalSyntheticLambda1(this));
    }
}
