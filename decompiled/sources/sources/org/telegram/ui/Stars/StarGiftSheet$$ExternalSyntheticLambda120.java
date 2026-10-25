package org.telegram.ui.Stars;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda120 implements Utilities.Callback {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ MessagesController f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda120(StarGiftSheet r1, MessagesController r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r5) {
        StarGiftSheet.$r8$lambda$ZwigORoSg0NJZoDl3ydZm_vVGZE(this.f$0, this.f$1, this.f$2, (TL_stories.TL_premium_boostsStatus) r5);
    }
}
