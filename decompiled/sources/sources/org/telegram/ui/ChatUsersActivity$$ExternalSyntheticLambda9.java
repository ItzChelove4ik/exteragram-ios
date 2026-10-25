package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatUsersActivity$$ExternalSyntheticLambda9 implements Utilities.Callback {
    public final /* synthetic */ ChatUsersActivity f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ TLRPC.TL_chatAdminRights f$4;
    public final /* synthetic */ TLRPC.TL_chatBannedRights f$5;
    public final /* synthetic */ String f$6;
    public final /* synthetic */ boolean f$7;

    public /* synthetic */ ChatUsersActivity$$ExternalSyntheticLambda9(ChatUsersActivity r1, long r2, int r4, TLObject r5, TLRPC.TL_chatAdminRights r6, TLRPC.TL_chatBannedRights r7, String r8, boolean r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r11) {
        ChatUsersActivity.m8505$r8$lambda$L32o19w8OD_eZrZU0EVOyMTc0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, (Integer) r11);
    }
}
