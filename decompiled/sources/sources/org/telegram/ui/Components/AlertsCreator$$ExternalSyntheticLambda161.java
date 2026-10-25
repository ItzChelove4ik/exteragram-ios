package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda161 implements Utilities.Callback2 {
    public final /* synthetic */ Browser.Progress f$0;
    public final /* synthetic */ MessagesStorage.BooleanCallback f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ boolean[] f$3;
    public final /* synthetic */ BaseFragment f$4;
    public final /* synthetic */ TLRPC.Chat f$5;
    public final /* synthetic */ Context f$6;
    public final /* synthetic */ int f$7;
    public final /* synthetic */ Theme.ResourcesProvider f$8;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda161(Browser.Progress r1, MessagesStorage.BooleanCallback r2, boolean r3, boolean[] r4, BaseFragment r5, TLRPC.Chat r6, Context r7, int r8, Theme.ResourcesProvider r9) {
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

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r12, Object r13) {
        AlertsCreator.m8726$r8$lambda$QO_sCcaXxHlbSGh500edC5GVR0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, (TLRPC.User) r12, (TLRPC.TL_error) r13);
    }
}
