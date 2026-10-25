package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Stars.StarGiftSheet;

/* loaded from: classes6.dex */
public final /* synthetic */ class PeerColorActivity$$ExternalSyntheticLambda15 implements Utilities.Callback2 {
    public final /* synthetic */ PeerColorActivity f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ PeerColorActivity$$ExternalSyntheticLambda15(PeerColorActivity r1, boolean[] r2, TL_stars.TL_starGiftUnique r3, long r4, Utilities.Callback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r9, Object r10) {
        PeerColorActivity.$r8$lambda$Bo19yxq4L4YPYzQxQi5bQTJeQdc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (StarGiftSheet.PaymentFormState) r9, (Browser.Progress) r10);
    }
}
