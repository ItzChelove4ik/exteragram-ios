package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda160 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ Runnable f$2;
    public final /* synthetic */ BaseFragment f$3;
    public final /* synthetic */ TLRPC.TL_channels_editBanned f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda160(MessagesController r1, long r2, Runnable r4, BaseFragment r5, TLRPC.TL_channels_editBanned r6, boolean r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        MessagesController.m4504$r8$lambda$6xlzuYmGLYQ436MZItoAjEBY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r10, r11);
    }
}
