package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda94 implements RequestDelegate {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ TLRPC.TodoItem f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ Runnable f$6;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda94(SendMessagesHelper r1, MessageObject r2, TLRPC.TodoItem r3, boolean r4, long r5, int r7, Runnable r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        SendMessagesHelper.m4926$r8$lambda$ZuUj6zl1T_9kK7Nb5zDvm2aN9U(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r11, r12);
    }
}
