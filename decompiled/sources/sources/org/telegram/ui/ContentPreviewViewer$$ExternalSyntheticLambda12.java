package org.telegram.ui;

import java.util.List;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ContentPreviewViewer$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ ContentPreviewViewer f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ List f$3;
    public final /* synthetic */ TLRPC.TL_messages_getMyStickers f$4;

    public /* synthetic */ ContentPreviewViewer$$ExternalSyntheticLambda12(ContentPreviewViewer r1, TLRPC.TL_error r2, TLObject r3, List r4, TLRPC.TL_messages_getMyStickers r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ContentPreviewViewer.m14097$r8$lambda$R3rlDOEDAEg6xgm4Akp3KTdNaI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
