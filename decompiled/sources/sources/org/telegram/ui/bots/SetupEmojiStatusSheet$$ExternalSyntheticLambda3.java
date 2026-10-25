package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class SetupEmojiStatusSheet$$ExternalSyntheticLambda3 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ TLRPC.Document f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ boolean[] f$4;
    public final /* synthetic */ Utilities.Callback f$5;

    public /* synthetic */ SetupEmojiStatusSheet$$ExternalSyntheticLambda3(int r1, boolean[] r2, TLRPC.Document r3, int r4, boolean[] r5, Utilities.Callback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r9, int r10) {
        SetupEmojiStatusSheet.$r8$lambda$jGmmYIy0VFIyzAMrrL6Tm3RNVBg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
