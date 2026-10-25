package org.telegram.ui.Components.voip;

import android.app.Activity;
import org.telegram.messenger.AccountInstance;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class VoIPHelper$$ExternalSyntheticLambda5 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ TLRPC.User f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ Activity f$6;
    public final /* synthetic */ BaseFragment f$7;
    public final /* synthetic */ AccountInstance f$8;

    public /* synthetic */ VoIPHelper$$ExternalSyntheticLambda5(TLRPC.User r1, TLRPC.Chat r2, String r3, boolean r4, boolean r5, boolean r6, Activity r7, BaseFragment r8, AccountInstance r9) {
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
        VoIPHelper.m13957$r8$lambda$3GMa3hKZd7eKIbVcOcnpSYULo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, r12, r13);
    }
}
