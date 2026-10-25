package org.telegram.ui.bots;

import android.content.DialogInterface;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotLocation$$ExternalSyntheticLambda7 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ BotLocation f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;

    public /* synthetic */ BotLocation$$ExternalSyntheticLambda7(BotLocation r1, boolean[] r2, Utilities.Callback2 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r3) {
        BotLocation.$r8$lambda$lgcxrN8MzSUAmiPmj8ePr94QpqA(this.f$0, this.f$1, this.f$2, r3);
    }
}
