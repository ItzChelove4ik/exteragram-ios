package org.telegram.ui;

import android.graphics.Bitmap;
import org.telegram.messenger.MediaController;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$$ExternalSyntheticLambda199 implements Runnable {
    public final /* synthetic */ PhotoViewer f$0;
    public final /* synthetic */ MediaController.PhotoEntry f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ Bitmap f$4;

    public /* synthetic */ PhotoViewer$$ExternalSyntheticLambda199(PhotoViewer r1, MediaController.PhotoEntry r2, long r3, String r5, Bitmap r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PhotoViewer.m16931$r8$lambda$kceASj_wQGY7EEwWweO0jY7nb8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
