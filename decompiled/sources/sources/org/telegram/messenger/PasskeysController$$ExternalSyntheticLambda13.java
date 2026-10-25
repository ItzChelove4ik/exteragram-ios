package org.telegram.messenger;

import android.os.CancellationSignal;

/* loaded from: classes5.dex */
public final /* synthetic */ class PasskeysController$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ CancellationSignal f$0;

    public /* synthetic */ PasskeysController$$ExternalSyntheticLambda13(CancellationSignal r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.cancel();
    }
}
