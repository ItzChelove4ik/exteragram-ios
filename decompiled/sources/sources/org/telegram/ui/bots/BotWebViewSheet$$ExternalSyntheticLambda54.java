package org.telegram.ui.bots;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotWebViewSheet$$ExternalSyntheticLambda54 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.TL_attachMenuBot f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ BotWebViewSheet$$ExternalSyntheticLambda54(int r1, long r2, TLRPC.TL_attachMenuBot r4, Runnable r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r8, int r9) {
        BotWebViewSheet.$r8$lambda$QNglP01KvBGd4hpmDml78Oc2a0k(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
