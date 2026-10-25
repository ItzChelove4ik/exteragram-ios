package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Cells.SharedPhotoVideoCell2;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$$ExternalSyntheticLambda39 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ SharedPhotoVideoCell2 f$0;
    public final /* synthetic */ float f$1;
    public final /* synthetic */ float f$2;
    public final /* synthetic */ boolean[] f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ TL_account.contentSettings f$5;
    public final /* synthetic */ Context f$6;
    public final /* synthetic */ int f$7;
    public final /* synthetic */ MessagesController f$8;

    public /* synthetic */ SharedMediaLayout$$ExternalSyntheticLambda39(SharedPhotoVideoCell2 r1, float r2, float r3, boolean[] r4, boolean r5, TL_account.contentSettings r6, Context r7, int r8, MessagesController r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r12, int r13) {
        SharedMediaLayout.$r8$lambda$eBnPQ__zxwyUKXtky2SPmcbv5OM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, r12, r13);
    }
}
