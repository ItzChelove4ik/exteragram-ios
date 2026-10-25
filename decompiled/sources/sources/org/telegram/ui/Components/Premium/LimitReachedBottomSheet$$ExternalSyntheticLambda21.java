package org.telegram.ui.Components.Premium;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class LimitReachedBottomSheet$$ExternalSyntheticLambda21 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ LimitReachedBottomSheet f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ TLRPC.User f$2;

    public /* synthetic */ LimitReachedBottomSheet$$ExternalSyntheticLambda21(LimitReachedBottomSheet r1, ArrayList r2, TLRPC.User r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        LimitReachedBottomSheet.m11985$r8$lambda$YflJU7X9DJoURJQZx7pDbsPNXM(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
