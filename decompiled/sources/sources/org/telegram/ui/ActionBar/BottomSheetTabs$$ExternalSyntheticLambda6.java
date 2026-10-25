package org.telegram.ui.ActionBar;

import android.content.DialogInterface;
import org.telegram.messenger.Utilities;

/* loaded from: classes6.dex */
public final /* synthetic */ class BottomSheetTabs$$ExternalSyntheticLambda6 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ BottomSheetTabs$$ExternalSyntheticLambda6(boolean[] r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r2) {
        BottomSheetTabs.m5726$r8$lambda$wYmzJHTOpTZmii2flzYplpdWs(this.f$0, this.f$1, r2);
    }
}
