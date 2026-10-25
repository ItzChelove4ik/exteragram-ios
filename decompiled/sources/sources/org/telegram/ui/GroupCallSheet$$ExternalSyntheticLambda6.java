package org.telegram.ui;

import android.content.Context;
import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Components.CheckBox2;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallSheet$$ExternalSyntheticLambda6 implements View.OnClickListener {
    public final /* synthetic */ BottomSheet f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ CheckBox2 f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ TLRPC.InputGroupCall f$4;

    public /* synthetic */ GroupCallSheet$$ExternalSyntheticLambda6(BottomSheet r1, Context r2, CheckBox2 r3, int r4, TLRPC.InputGroupCall r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r7) {
        GroupCallSheet.m15336$r8$lambda$plmRm_ZCfuxi0WM15hUydZwDTE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
