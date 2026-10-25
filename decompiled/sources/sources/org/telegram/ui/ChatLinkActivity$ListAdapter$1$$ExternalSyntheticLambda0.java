package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatLinkActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatLinkActivity$ListAdapter$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ChatLinkActivity.ListAdapter.AnonymousClass1 f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ ChatLinkActivity$ListAdapter$1$$ExternalSyntheticLambda0(ChatLinkActivity.ListAdapter.AnonymousClass1 r1, TLRPC.Chat r2, boolean r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatLinkActivity.ListAdapter.AnonymousClass1.$r8$lambda$frkbtJDmJMTuX193cZAU33FatT4(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
