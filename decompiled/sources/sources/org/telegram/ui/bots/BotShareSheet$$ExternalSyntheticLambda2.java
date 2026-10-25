package org.telegram.ui.bots;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotShareSheet$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ TLRPC.TL_messages_preparedInlineMessage f$4;
    public final /* synthetic */ Theme.ResourcesProvider f$5;
    public final /* synthetic */ Runnable f$6;
    public final /* synthetic */ Utilities.Callback2 f$7;

    public /* synthetic */ BotShareSheet$$ExternalSyntheticLambda2(AlertDialog r1, Context r2, int r3, long r4, TLRPC.TL_messages_preparedInlineMessage r6, Theme.ResourcesProvider r7, Runnable r8, Utilities.Callback2 r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r11) {
        BotShareSheet.m21339$r8$lambda$17G8wWARGR8C2AS8tVfVpEZg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, (TLRPC.WebPage) r11);
    }
}
