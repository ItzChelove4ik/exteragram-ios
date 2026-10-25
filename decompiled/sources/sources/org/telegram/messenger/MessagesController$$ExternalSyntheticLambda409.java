package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda409 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TLObject f$10;
    public final /* synthetic */ boolean f$11;
    public final /* synthetic */ TLRPC.Chat f$12;
    public final /* synthetic */ TLRPC.InputUser f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ Utilities.Callback f$4;
    public final /* synthetic */ Runnable f$5;
    public final /* synthetic */ TLRPC.User f$6;
    public final /* synthetic */ boolean f$7;
    public final /* synthetic */ MessagesController.ErrorDelegate f$8;
    public final /* synthetic */ BaseFragment f$9;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda409(MessagesController r1, boolean r2, TLRPC.InputUser r3, long r4, Utilities.Callback r6, Runnable r7, TLRPC.User r8, boolean r9, MessagesController.ErrorDelegate r10, BaseFragment r11, TLObject r12, boolean r13, TLRPC.Chat r14) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
        this.f$9 = r11;
        this.f$10 = r12;
        this.f$11 = r13;
        this.f$12 = r14;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r17, TLRPC.TL_error r18) {
        MessagesController.$r8$lambda$YS9XInBSUtSM_y_cKUgrYfm1g9A(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, r17, r18);
    }
}
