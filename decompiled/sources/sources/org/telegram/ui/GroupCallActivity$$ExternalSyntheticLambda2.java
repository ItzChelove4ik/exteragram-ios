package org.telegram.ui;

import org.telegram.messenger.ChatObject;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Cells.CheckBoxCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$$ExternalSyntheticLambda2 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ChatObject.Call f$0;
    public final /* synthetic */ CheckBoxCell[] f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ GroupCallActivity$$ExternalSyntheticLambda2(ChatObject.Call r1, CheckBoxCell[] r2, long r3, Runnable r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r8, int r9) {
        GroupCallActivity.$r8$lambda$5w6tLTwxz_75uKnWsnpVUxhxPJE(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
