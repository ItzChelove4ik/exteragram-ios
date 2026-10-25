package androidx.media3.exoplayer.audio;

import android.os.Handler;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAudioSink$StreamEventCallbackV29$$ExternalSyntheticLambda0 implements Executor {
    public final /* synthetic */ Handler f$0;

    public /* synthetic */ DefaultAudioSink$StreamEventCallbackV29$$ExternalSyntheticLambda0(Handler r1) {
        this.f$0 = r1;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r1) {
        this.f$0.post(r1);
    }
}
