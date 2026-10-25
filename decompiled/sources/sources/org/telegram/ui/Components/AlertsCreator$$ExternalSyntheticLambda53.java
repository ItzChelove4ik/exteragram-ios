package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda53 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ MessagesStorage.BooleanCallback f$10;
    public final /* synthetic */ Theme.ResourcesProvider f$11;
    public final /* synthetic */ AlertDialog.Builder f$12;
    public final /* synthetic */ int f$13;
    public final /* synthetic */ Context f$14;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLRPC.User f$3;
    public final /* synthetic */ BaseFragment f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ TLRPC.Chat f$6;
    public final /* synthetic */ boolean f$7;
    public final /* synthetic */ boolean[] f$8;
    public final /* synthetic */ boolean f$9;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda53(boolean r1, boolean r2, boolean r3, TLRPC.User r4, BaseFragment r5, boolean r6, TLRPC.Chat r7, boolean r8, boolean[] r9, boolean r10, MessagesStorage.BooleanCallback r11, Theme.ResourcesProvider r12, AlertDialog.Builder r13, int r14, Context r15) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
        this.f$9 = r10;
        this.f$10 = r11;
        this.f$11 = r12;
        this.f$12 = r13;
        this.f$13 = r14;
        this.f$14 = r15;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r18, int r19) {
        AlertsCreator.$r8$lambda$F13ytT5slXERaTEg_lytSyQvct4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13, this.f$14, r18, r19);
    }
}
