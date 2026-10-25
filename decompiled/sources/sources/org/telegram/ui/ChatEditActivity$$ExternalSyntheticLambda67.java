package org.telegram.ui;

import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatEditActivity$$ExternalSyntheticLambda67 implements Utilities.Callback {
    public final /* synthetic */ ChatEditActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TL_stories.TL_premium_boostsStatus f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ ChatEditActivity$$ExternalSyntheticLambda67(ChatEditActivity r1, AlertDialog r2, TL_stories.TL_premium_boostsStatus r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r7) {
        ChatEditActivity.$r8$lambda$1lAouOJzvWSWLDTzWlrXNVQWerI(this.f$0, this.f$1, this.f$2, this.f$3, (ChannelBoostsController.CanApplyBoost) r7);
    }
}
