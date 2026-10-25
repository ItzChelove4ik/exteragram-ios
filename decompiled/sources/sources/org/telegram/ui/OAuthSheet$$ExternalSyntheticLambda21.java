package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public final /* synthetic */ class OAuthSheet$$ExternalSyntheticLambda21 implements Utilities.Callback2 {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ BottomSheet f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;

    public /* synthetic */ OAuthSheet$$ExternalSyntheticLambda21(AlertDialog r1, BottomSheet r2, String r3, Theme.ResourcesProvider r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        OAuthSheet.$r8$lambda$4DvKHYHrJDvFx1GneRe3QEE2swg(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.Bool) r7, (TLRPC.TL_error) r8);
    }
}
