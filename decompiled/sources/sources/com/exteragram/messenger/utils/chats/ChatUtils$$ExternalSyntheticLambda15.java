package com.exteragram.messenger.utils.chats;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ChatUtils$$ExternalSyntheticLambda15 implements RequestDelegate {
    public final /* synthetic */ ChatUtils f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ Utilities.Callback f$4;
    public final /* synthetic */ Utilities.Callback f$5;
    public final /* synthetic */ String f$6;

    public /* synthetic */ ChatUtils$$ExternalSyntheticLambda15(ChatUtils r1, boolean r2, TLRPC.User r3, String r4, Utilities.Callback r5, Utilities.Callback r6, String r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        ChatUtils.$r8$lambda$F8nvp2kwnJ9ygHkukdrkYcoFxXE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r10, r11);
    }
}
