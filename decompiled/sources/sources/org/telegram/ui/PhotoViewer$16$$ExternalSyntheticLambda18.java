package org.telegram.ui;

import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$16$$ExternalSyntheticLambda18 implements Runnable {
    public final /* synthetic */ PhotoViewer.AnonymousClass16 f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ UserConfig f$2;
    public final /* synthetic */ TLRPC.Photo f$3;

    public /* synthetic */ PhotoViewer$16$$ExternalSyntheticLambda18(PhotoViewer.AnonymousClass16 r1, TLObject r2, UserConfig r3, TLRPC.Photo r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PhotoViewer.AnonymousClass16.$r8$lambda$fCVGz8jg1cCncP3XqgiVIUhQQtM(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
