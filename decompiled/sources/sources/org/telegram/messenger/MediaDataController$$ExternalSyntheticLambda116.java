package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.messenger.MediaDataController;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda116 implements Runnable {
    public final /* synthetic */ MediaDataController.KeywordResultCallback f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda116(MediaDataController.KeywordResultCallback r1, ArrayList r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaDataController.$r8$lambda$MNd5ZyLzS0Y886dnaMojZUYZEyY(this.f$0, this.f$1, this.f$2);
    }
}
