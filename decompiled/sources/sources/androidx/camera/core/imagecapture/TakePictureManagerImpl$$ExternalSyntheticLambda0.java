package androidx.camera.core.imagecapture;

/* loaded from: classes3.dex */
public final /* synthetic */ class TakePictureManagerImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ TakePictureManagerImpl f$0;

    public /* synthetic */ TakePictureManagerImpl$$ExternalSyntheticLambda0(TakePictureManagerImpl r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.issueNextRequest();
    }
}
