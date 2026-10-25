package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda162 implements RequestDelegate {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TL_stars.InputSavedStarGift f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda162(StarGiftSheet r1, String r2, TL_stars.InputSavedStarGift r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        StarGiftSheet.m18972$r8$lambda$URuUHGoHClRYnY6zHyYPCkTqDo(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
