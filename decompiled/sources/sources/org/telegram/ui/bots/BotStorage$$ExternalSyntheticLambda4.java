package org.telegram.ui.bots;

import android.content.DialogInterface;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotStorage$$ExternalSyntheticLambda4 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ BotStorage$$ExternalSyntheticLambda4(boolean[] r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r2) {
        BotStorage.m21346$r8$lambda$_XiV6ygf3hnFTc2t7ybF1GjLJQ(this.f$0, this.f$1, r2);
    }
}
