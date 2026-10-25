package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda52 implements Utilities.Callback2 {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$1;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda52(StarGiftSheet r1, TL_stars.TL_starGiftUnique r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        StarGiftSheet.$r8$lambda$1Sq5Usp2MelfAqjLC_ZiLsQo3FM(this.f$0, this.f$1, (AmountUtils$Amount) r2, (Runnable) r3);
    }
}
