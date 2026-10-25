package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda238 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ Runnable f$2;
    public final /* synthetic */ TLRPC.Chat f$3;
    public final /* synthetic */ TLRPC.User f$4;
    public final /* synthetic */ MessagesController.ErrorDelegate f$5;
    public final /* synthetic */ BaseFragment f$6;
    public final /* synthetic */ TLRPC.TL_channels_editAdmin f$7;
    public final /* synthetic */ boolean f$8;
    public final /* synthetic */ boolean f$9;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda238(MessagesController r1, long r2, Runnable r4, TLRPC.Chat r5, TLRPC.User r6, MessagesController.ErrorDelegate r7, BaseFragment r8, TLRPC.TL_channels_editAdmin r9, boolean r10, boolean r11) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
        this.f$9 = r11;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r14, TLRPC.TL_error r15) {
        MessagesController.m4596$r8$lambda$gV4rslhg14fA62yaA6HIWy2d2s(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, r14, r15);
    }
}
