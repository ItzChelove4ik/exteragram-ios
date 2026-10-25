package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stats;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda343 implements Utilities.Callback {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ AlertDialog[] f$1;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda343(ChatActivity r1, AlertDialog[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ChatActivity.$r8$lambda$s9I58V2zd307J4suUc3ibyzHiZM(this.f$0, this.f$1, (TL_stats.TL_statsPollStats) r2);
    }
}
