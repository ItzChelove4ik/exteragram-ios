package org.webrtc.audio;

import android.media.AudioRecord;
import java.util.concurrent.Callable;

/* loaded from: classes6.dex */
public final /* synthetic */ class WebRtcAudioRecord$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ WebRtcAudioRecord f$0;
    public final /* synthetic */ AudioRecord f$1;

    public /* synthetic */ WebRtcAudioRecord$$ExternalSyntheticLambda0(WebRtcAudioRecord r1, AudioRecord r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return WebRtcAudioRecord.$r8$lambda$Zpf0ZnAH8bhRwXfqZHjvFidZLgw(this.f$0, this.f$1);
    }
}
