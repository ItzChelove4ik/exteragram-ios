package org.telegram.ui.bots;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotShareSheet$$ExternalSyntheticLambda10 implements View.OnClickListener {
    public final /* synthetic */ BotShareSheet f$0;
    public final /* synthetic */ TLRPC.TL_messages_preparedInlineMessage f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ Runnable f$5;

    public /* synthetic */ BotShareSheet$$ExternalSyntheticLambda10(BotShareSheet r1, TLRPC.TL_messages_preparedInlineMessage r2, Utilities.Callback2 r3, int r4, long r5, Runnable r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r9) {
        BotShareSheet.$r8$lambda$yPCwFuduZpR4sP_s2aNVh6B3hYE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9);
    }
}
