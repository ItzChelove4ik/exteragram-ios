package org.telegram.ui.Components.Premium.boosts.cells;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class TableCell$$ExternalSyntheticLambda1 implements View.OnClickListener {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ TLRPC.TL_payments_checkedGiftCode f$1;

    public /* synthetic */ TableCell$$ExternalSyntheticLambda1(Utilities.Callback r1, TLRPC.TL_payments_checkedGiftCode r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r2) {
        TableCell.$r8$lambda$N4Arapk_g1jMSVOvHWUFGhMhj3U(this.f$0, this.f$1, r2);
    }
}
