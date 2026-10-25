package org.telegram.ui;

import android.graphics.Bitmap;
import org.telegram.messenger.MediaController;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$$ExternalSyntheticLambda183 implements Runnable {
    public final /* synthetic */ PhotoViewer f$0;
    public final /* synthetic */ MediaController.PhotoEntry f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Bitmap f$3;

    public /* synthetic */ PhotoViewer$$ExternalSyntheticLambda183(PhotoViewer r1, MediaController.PhotoEntry r2, String r3, Bitmap r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PhotoViewer.$r8$lambda$exsEcrOaZGkYItFnQIbe25jVKyE(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
