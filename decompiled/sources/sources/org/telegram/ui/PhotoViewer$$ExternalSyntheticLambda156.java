package org.telegram.ui;

import android.graphics.Bitmap;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.Utilities;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$$ExternalSyntheticLambda156 implements Utilities.Callback {
    public final /* synthetic */ PhotoViewer f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ MediaController.PhotoEntry f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ PhotoViewer$$ExternalSyntheticLambda156(PhotoViewer r1, String r2, MediaController.PhotoEntry r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r7) {
        PhotoViewer.$r8$lambda$h14NSuAXNlgiJ31hZ7Psi0bhrV0(this.f$0, this.f$1, this.f$2, this.f$3, (Bitmap) r7);
    }
}
