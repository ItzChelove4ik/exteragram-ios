package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda127 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Runnable f$3;
    public final /* synthetic */ BaseFragment f$4;
    public final /* synthetic */ TLRPC.TL_channels_updateUsername f$5;
    public final /* synthetic */ Runnable f$6;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda127(MessagesController r1, long r2, String r4, Runnable r5, BaseFragment r6, TLRPC.TL_channels_updateUsername r7, Runnable r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        MessagesController.$r8$lambda$VgMPx4J36mnUgnTpwUf0qu_1rJw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r11, r12);
    }
}
