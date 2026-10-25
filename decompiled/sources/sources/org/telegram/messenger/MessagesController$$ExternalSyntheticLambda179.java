package org.telegram.messenger;

import android.content.Context;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda179 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ boolean[] f$2;
    public final /* synthetic */ TL_account.contentSettings f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ boolean[] f$6;
    public final /* synthetic */ Runnable f$7;
    public final /* synthetic */ Theme.ResourcesProvider f$8;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda179(MessagesController r1, boolean r2, boolean[] r3, TL_account.contentSettings r4, Context r5, long r6, boolean[] r8, Runnable r9, Theme.ResourcesProvider r10) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r13, int r14) {
        MessagesController.$r8$lambda$eCYp82kwhMS5peJgLtgFrsuBwVQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, r13, r14);
    }
}
