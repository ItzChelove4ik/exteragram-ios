package org.telegram.messenger;

import android.view.PixelCopy;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidUtilities$$ExternalSyntheticLambda32 implements PixelCopy.OnPixelCopyFinishedListener {
    public final /* synthetic */ CountDownLatch f$0;

    public /* synthetic */ AndroidUtilities$$ExternalSyntheticLambda32(CountDownLatch r1) {
        this.f$0 = r1;
    }

    @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
    public final void onPixelCopyFinished(int r1) {
        AndroidUtilities.$r8$lambda$dbJ_kwONwNyQCcwH5Wpjk9vjteY(this.f$0, r1);
    }
}
