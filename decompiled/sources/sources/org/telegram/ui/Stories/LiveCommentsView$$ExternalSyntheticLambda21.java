package org.telegram.ui.Stories;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class LiveCommentsView$$ExternalSyntheticLambda21 implements Runnable {
    public final /* synthetic */ LiveCommentsView f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$5;

    public /* synthetic */ LiveCommentsView$$ExternalSyntheticLambda21(LiveCommentsView r1, int r2, TLRPC.TL_error r3, long r4, long r6, TLRPC.TL_textWithEntities r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LiveCommentsView.m19341$r8$lambda$HIx6XSSS3Ak7pwgVPzYa2aamrI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
