package org.telegram.messenger;

import android.view.PixelCopy;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidUtilities$$ExternalSyntheticLambda33 implements PixelCopy.OnPixelCopyFinishedListener {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ CountDownLatch f$1;

    public /* synthetic */ AndroidUtilities$$ExternalSyntheticLambda33(boolean[] r1, CountDownLatch r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
    public final void onPixelCopyFinished(int r2) {
        AndroidUtilities.m3874$r8$lambda$U1mjz0c6pNXEGrZtp2rsOmuR0(this.f$0, this.f$1, r2);
    }
}
