package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatUsersActivity$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ ChatUsersActivity f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.TL_chatBannedRights f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ TLObject f$4;

    public /* synthetic */ ChatUsersActivity$$ExternalSyntheticLambda13(ChatUsersActivity r1, long r2, TLRPC.TL_chatBannedRights r4, String r5, TLObject r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatUsersActivity.m8515$r8$lambda$rldLa_8u3yKlIY9liExR1FtMno(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
