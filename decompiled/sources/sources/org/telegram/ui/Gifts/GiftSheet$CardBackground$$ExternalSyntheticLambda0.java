package org.telegram.ui.Gifts;

import org.telegram.ui.Gifts.GiftSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class GiftSheet$CardBackground$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ GiftSheet.CardBackground f$0;

    public /* synthetic */ GiftSheet$CardBackground$$ExternalSyntheticLambda0(GiftSheet.CardBackground r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidate();
    }
}
