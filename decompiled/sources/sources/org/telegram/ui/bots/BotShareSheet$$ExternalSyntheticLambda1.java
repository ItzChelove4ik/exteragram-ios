package org.telegram.ui.bots;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotShareSheet$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ TLObject f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ AlertDialog f$2;
    public final /* synthetic */ Context f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ Theme.ResourcesProvider f$5;
    public final /* synthetic */ Runnable f$6;
    public final /* synthetic */ Utilities.Callback2 f$7;

    public /* synthetic */ BotShareSheet$$ExternalSyntheticLambda1(TLObject r1, int r2, AlertDialog r3, Context r4, long r5, Theme.ResourcesProvider r7, Runnable r8, Utilities.Callback2 r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BotShareSheet.$r8$lambda$F_fmB5oaljGgrU5BGz3SGrAIyRY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
