package androidx.camera.camera2.config;

import android.content.res.TypedArray;
import android.media.MediaDrm;
import android.media.MediaMetadataRetriever;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public abstract /* synthetic */ class UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0 {
    public static /* synthetic */ void m(Object r1) {
        if ((r1 instanceof AutoCloseable) == false) goto L7;
        ((AutoCloseable) r1).close();
        return;
    L7:
        if ((r1 instanceof ExecutorService) == false) goto L11;
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableForwarder1.m((ExecutorService) r1);
        return;
    L11:
        if ((r1 instanceof TypedArray) == false) goto L15;
        ((TypedArray) r1).recycle();
        return;
    L15:
        if ((r1 instanceof MediaMetadataRetriever) == false) goto L19;
        ((MediaMetadataRetriever) r1).release();
        return;
    L19:
        if ((r1 instanceof MediaDrm) == false) goto L22;
        ((MediaDrm) r1).release();
        return;
    L22:
        UseCaseGraphContext$$ExternalSyntheticThrowIAE2.m(r1);
    }
}
