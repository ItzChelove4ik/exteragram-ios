package androidx.camera.core;

/* loaded from: classes3.dex */
public final /* synthetic */ class Preview$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Preview f$0;

    public /* synthetic */ Preview$$ExternalSyntheticLambda0(Preview r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.notifyReset();
    }
}
