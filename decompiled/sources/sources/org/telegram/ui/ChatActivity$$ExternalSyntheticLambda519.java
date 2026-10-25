package org.telegram.ui;

import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda519 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ AtomicBoolean f$2;
    public final /* synthetic */ TLRPC.TL_attachMenuBot f$3;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda519(ChatActivity r1, TLRPC.User r2, AtomicBoolean r3, TLRPC.TL_attachMenuBot r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r7, int r8) {
        ChatActivity.m7525$r8$lambda$mdYAfuMmmpP9WSsMGPcUs_zJQs(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
