package org.telegram.ui;

import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$16$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ PhotoViewer.AnonymousClass16 f$0;
    public final /* synthetic */ UserConfig f$1;
    public final /* synthetic */ TLRPC.Photo f$2;

    public /* synthetic */ PhotoViewer$16$$ExternalSyntheticLambda3(PhotoViewer.AnonymousClass16 r1, UserConfig r2, TLRPC.Photo r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        PhotoViewer.AnonymousClass16.$r8$lambda$kpTBmQKnXV3ygMf01CSrWLTQ4Qc(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
