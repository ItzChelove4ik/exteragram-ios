package org.telegram.ui.Components;

import android.content.DialogInterface;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class CreateBotAlert$$ExternalSyntheticLambda6 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ int[] f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ CreateBotAlert$$ExternalSyntheticLambda6(boolean[] r1, Utilities.Callback r2, int[] r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r4) {
        CreateBotAlert.$r8$lambda$O4LfDmOpx_9Y5LMHst2_FDOR_54(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
