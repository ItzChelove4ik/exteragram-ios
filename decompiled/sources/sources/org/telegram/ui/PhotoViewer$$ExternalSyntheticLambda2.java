package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedFileDrawable;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ PhotoViewer f$0;
    public final /* synthetic */ AnimatedFileDrawable f$1;
    public final /* synthetic */ TLRPC.Document f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ PhotoViewer.PlaceProviderObject f$4;

    public /* synthetic */ PhotoViewer$$ExternalSyntheticLambda2(PhotoViewer r1, AnimatedFileDrawable r2, TLRPC.Document r3, long r4, PhotoViewer.PlaceProviderObject r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PhotoViewer.$r8$lambda$vmXIRWWLeojUOJr4IMelucQsxRI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
