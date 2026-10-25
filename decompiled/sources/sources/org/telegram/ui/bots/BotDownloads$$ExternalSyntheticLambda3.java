package org.telegram.ui.bots;

import android.content.DialogInterface;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotDownloads$$ExternalSyntheticLambda3 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ BotDownloads$$ExternalSyntheticLambda3(boolean[] r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r2) {
        BotDownloads.$r8$lambda$OQh7ybzxESYhklG32SuXfFcyUBY(this.f$0, this.f$1, r2);
    }
}
