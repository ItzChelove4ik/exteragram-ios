package org.telegram.ui.Cells;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatMessageCell$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ ChatMessageCell f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.Chat f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ ChatMessageCell$$ExternalSyntheticLambda3(ChatMessageCell r1, TLRPC.User r2, int r3, TLRPC.Chat r4, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatMessageCell.$r8$lambda$cMGEakjFLAHsy_E5WeK18s9Lnk8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
