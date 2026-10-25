package org.telegram.messenger;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import org.telegram.messenger.MediaDataController;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda115 implements Runnable {
    public final /* synthetic */ CountDownLatch f$0;
    public final /* synthetic */ MediaDataController.KeywordResultCallback f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda115(CountDownLatch r1, MediaDataController.KeywordResultCallback r2, ArrayList r3, String r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaDataController.$r8$lambda$OkUErbks0KzldNJS7Ha6sNhiqBI(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
