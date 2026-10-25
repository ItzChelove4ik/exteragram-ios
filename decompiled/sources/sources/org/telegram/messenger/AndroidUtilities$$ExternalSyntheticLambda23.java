package org.telegram.messenger;

import android.view.PixelCopy;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidUtilities$$ExternalSyntheticLambda23 implements PixelCopy.OnPixelCopyFinishedListener {
    public final /* synthetic */ CountDownLatch f$0;

    public /* synthetic */ AndroidUtilities$$ExternalSyntheticLambda23(CountDownLatch r1) {
        this.f$0 = r1;
    }

    @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
    public final void onPixelCopyFinished(int r1) {
        AndroidUtilities.$r8$lambda$rLoPqu4Dd4z4V6K51HBx8QtDBvM(this.f$0, r1);
    }
}
