package org.telegram.ui.bots;

import android.content.DialogInterface;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class SetupEmojiStatusSheet$$ExternalSyntheticLambda4 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ SetupEmojiStatusSheet$$ExternalSyntheticLambda4(boolean[] r1, boolean[] r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r3) {
        SetupEmojiStatusSheet.$r8$lambda$V1U6ZnSV1fFU4tBdC9dbSPRxIho(this.f$0, this.f$1, this.f$2, r3);
    }
}
