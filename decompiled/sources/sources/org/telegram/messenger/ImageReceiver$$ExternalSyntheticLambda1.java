package org.telegram.messenger;

/* loaded from: classes.dex */
public final /* synthetic */ class ImageReceiver$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ImageReceiver f$0;

    public /* synthetic */ ImageReceiver$$ExternalSyntheticLambda1(ImageReceiver r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidate();
    }
}
