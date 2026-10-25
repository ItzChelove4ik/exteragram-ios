package org.telegram.ui;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.WearAuthSheet;

/* loaded from: classes6.dex */
public final /* synthetic */ class WearAuthSheet$$ExternalSyntheticLambda8 implements Utilities.Callback2 {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ BottomSheet f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ View f$3;
    public final /* synthetic */ WearAuthSheet.AuthSession f$4;

    public /* synthetic */ WearAuthSheet$$ExternalSyntheticLambda8(ButtonWithCounterView r1, BottomSheet r2, int r3, View r4, WearAuthSheet.AuthSession r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r8, Object r9) {
        WearAuthSheet.$r8$lambda$qyXj0JZQmSIB8o9BZQgtRMdhUnk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (TLRPC.UrlAuthResult) r8, (TLRPC.TL_error) r9);
    }
}
