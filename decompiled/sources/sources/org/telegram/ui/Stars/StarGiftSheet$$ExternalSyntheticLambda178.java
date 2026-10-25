package org.telegram.ui.Stars;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda178 implements Runnable {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TL_stars.InputSavedStarGift f$3;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda178(StarGiftSheet r1, TLRPC.TL_error r2, TLObject r3, TL_stars.InputSavedStarGift r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarGiftSheet.$r8$lambda$4uTUiVjyWOqRbsZLkpp09rtKDmY(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
