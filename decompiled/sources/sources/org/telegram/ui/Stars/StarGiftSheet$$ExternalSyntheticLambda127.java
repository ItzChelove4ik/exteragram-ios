package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda127 implements RequestDelegate {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ UserSelectorBottomSheet[] f$1;
    public final /* synthetic */ Long f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda127(StarGiftSheet r1, UserSelectorBottomSheet[] r2, Long r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        StarGiftSheet.$r8$lambda$hnzglEYXovU6joJdWvrqghw6lZg(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
