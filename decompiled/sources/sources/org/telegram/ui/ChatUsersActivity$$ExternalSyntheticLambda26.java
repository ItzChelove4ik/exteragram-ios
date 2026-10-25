package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatUsersActivity$$ExternalSyntheticLambda26 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ChatUsersActivity f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.TL_chatAdminRights f$3;
    public final /* synthetic */ TLRPC.TL_chatBannedRights f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ boolean f$6;

    public /* synthetic */ ChatUsersActivity$$ExternalSyntheticLambda26(ChatUsersActivity r1, TLRPC.User r2, TLObject r3, TLRPC.TL_chatAdminRights r4, TLRPC.TL_chatBannedRights r5, String r6, boolean r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r10, int r11) {
        ChatUsersActivity.$r8$lambda$SztNNm0_MHqNJ9iTurYjguj0Q3U(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r10, r11);
    }
}
