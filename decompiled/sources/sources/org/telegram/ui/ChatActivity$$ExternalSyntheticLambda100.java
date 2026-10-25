package org.telegram.ui;

import android.content.DialogInterface;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.StickersAlert;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda100 implements DialogInterface.OnShowListener {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ Object[] f$1;
    public final /* synthetic */ StickersAlert f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TLRPC.StickerSet f$4;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda100(ChatActivity r1, Object[] r2, StickersAlert r3, boolean r4, TLRPC.StickerSet r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface r7) {
        ChatActivity.m7504$r8$lambda$eoMQ315j8Ws2QimmpvysO_h0ro(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
