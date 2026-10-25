package org.telegram.messenger.pip.source;

/* loaded from: classes5.dex */
public final /* synthetic */ class PipSourceHandlerState2$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ PipSourcePlaceholder f$0;

    public /* synthetic */ PipSourceHandlerState2$$ExternalSyntheticLambda9(PipSourcePlaceholder r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.stopPlaceholderForSource();
    }
}
