package org.telegram.ui.Components;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiPacksAlert$$ExternalSyntheticLambda9 implements RequestDelegate {
    public final /* synthetic */ TLRPC.StickerSet f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ View f$2;
    public final /* synthetic */ BaseFragment f$3;
    public final /* synthetic */ TLRPC.TL_messages_stickerSet f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ Utilities.Callback f$6;
    public final /* synthetic */ Runnable f$7;

    public /* synthetic */ EmojiPacksAlert$$ExternalSyntheticLambda9(TLRPC.StickerSet r1, boolean r2, View r3, BaseFragment r4, TLRPC.TL_messages_stickerSet r5, int r6, Utilities.Callback r7, Runnable r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        EmojiPacksAlert.m10210$r8$lambda$Mh3tb7shUOlc7hESdFEs2EK3EM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r11, r12);
    }
}
