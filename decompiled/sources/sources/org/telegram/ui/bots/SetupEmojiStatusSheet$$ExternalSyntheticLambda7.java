package org.telegram.ui.bots;

import android.content.Context;
import android.content.DialogInterface;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class SetupEmojiStatusSheet$$ExternalSyntheticLambda7 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ TLRPC.User f$4;
    public final /* synthetic */ Utilities.Callback2 f$5;

    public /* synthetic */ SetupEmojiStatusSheet$$ExternalSyntheticLambda7(boolean[] r1, boolean[] r2, Context r3, int r4, TLRPC.User r5, Utilities.Callback2 r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r8) {
        SetupEmojiStatusSheet.m21489$r8$lambda$xBcQe_QLK_Xp3dKHzBejZphvM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r8);
    }
}
