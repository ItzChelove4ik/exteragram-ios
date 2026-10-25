package org.telegram.ui.bots;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class SetupEmojiStatusSheet$$ExternalSyntheticLambda6 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ boolean[] f$2;
    public final /* synthetic */ Utilities.Callback2 f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ TLRPC.User f$5;
    public final /* synthetic */ TLRPC.UserFull f$6;

    public /* synthetic */ SetupEmojiStatusSheet$$ExternalSyntheticLambda6(int r1, boolean[] r2, boolean[] r3, Utilities.Callback2 r4, Context r5, TLRPC.User r6, TLRPC.UserFull r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r10, int r11) {
        SetupEmojiStatusSheet.$r8$lambda$e9F1zgMeFykHyXu2bGe3uCAdOus(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r10, r11);
    }
}
