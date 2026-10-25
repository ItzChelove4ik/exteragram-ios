package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatEditTypeActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatEditTypeActivity$UsernamesListView$1$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ ChatEditTypeActivity.UsernamesListView.AnonymousClass1 f$0;
    public final /* synthetic */ TLRPC.TL_channels_toggleUsername f$1;
    public final /* synthetic */ TLRPC.TL_username f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ ChatEditTypeActivity$UsernamesListView$1$$ExternalSyntheticLambda3(ChatEditTypeActivity.UsernamesListView.AnonymousClass1 r1, TLRPC.TL_channels_toggleUsername r2, TLRPC.TL_username r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        ChatEditTypeActivity.UsernamesListView.AnonymousClass1.$r8$lambda$DyGqaaWTTUPGJk2Qm2ICDyaNMEk(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
