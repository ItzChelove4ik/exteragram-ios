package org.telegram.ui.bots;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotShareSheet$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ Theme.ResourcesProvider f$4;
    public final /* synthetic */ Runnable f$5;
    public final /* synthetic */ Utilities.Callback2 f$6;

    public /* synthetic */ BotShareSheet$$ExternalSyntheticLambda0(int r1, AlertDialog r2, Context r3, long r4, Theme.ResourcesProvider r6, Runnable r7, Utilities.Callback2 r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        BotShareSheet.$r8$lambda$UU5hNZVorMM4DDGJxEneGJn0QBs(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r11, r12);
    }
}
