package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;

/* loaded from: classes6.dex */
public final /* synthetic */ class OAuthSheet$$ExternalSyntheticLambda19 implements Utilities.Callback2 {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ BottomSheet f$1;

    public /* synthetic */ OAuthSheet$$ExternalSyntheticLambda19(boolean[] r1, BottomSheet r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        OAuthSheet.$r8$lambda$LEF4U4aGV6a65A5Hsz9Z0_E37nc(this.f$0, this.f$1, (TLRPC.Bool) r2, (TLRPC.TL_error) r3);
    }
}
