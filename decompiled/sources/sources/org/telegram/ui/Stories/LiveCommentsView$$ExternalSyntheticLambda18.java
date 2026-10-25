package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;

/* loaded from: classes7.dex */
public final /* synthetic */ class LiveCommentsView$$ExternalSyntheticLambda18 implements RequestDelegate {
    public final /* synthetic */ LiveCommentsView f$0;
    public final /* synthetic */ TL_phone.sendGroupCallMessage f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$5;

    public /* synthetic */ LiveCommentsView$$ExternalSyntheticLambda18(LiveCommentsView r1, TL_phone.sendGroupCallMessage r2, int r3, long r4, long r6, TLRPC.TL_textWithEntities r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        LiveCommentsView.$r8$lambda$gTpcrXKXW3G4czLK6ZDPFAbMT8w(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r11, r12);
    }
}
