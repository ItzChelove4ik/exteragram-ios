package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatEditTypeActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatEditTypeActivity$UsernamesListView$1$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ ChatEditTypeActivity.UsernamesListView.AnonymousClass1 f$0;
    public final /* synthetic */ TLRPC.TL_channels_toggleUsername f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.TL_username f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ TLRPC.TL_error f$5;

    public /* synthetic */ ChatEditTypeActivity$UsernamesListView$1$$ExternalSyntheticLambda4(ChatEditTypeActivity.UsernamesListView.AnonymousClass1 r1, TLRPC.TL_channels_toggleUsername r2, TLObject r3, TLRPC.TL_username r4, boolean r5, TLRPC.TL_error r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatEditTypeActivity.UsernamesListView.AnonymousClass1.$r8$lambda$dcx8AEkpyEzchsHzFE92ptyN7MU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
