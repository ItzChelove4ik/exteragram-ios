package org.telegram.ui.Components;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda160 implements MessagesStorage.IntCallback {
    public final /* synthetic */ BaseFragment f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TLRPC.Chat f$2;
    public final /* synthetic */ TLRPC.User f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ boolean[] f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ MessagesStorage.BooleanCallback f$7;
    public final /* synthetic */ Theme.ResourcesProvider f$8;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda160(BaseFragment r1, boolean r2, TLRPC.Chat r3, TLRPC.User r4, boolean r5, boolean[] r6, boolean r7, MessagesStorage.BooleanCallback r8, Theme.ResourcesProvider r9) {
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

    @Override // org.telegram.messenger.MessagesStorage.IntCallback
    public final void run(int r11) {
        AlertsCreator.$r8$lambda$s8IFEOUSMBH81wsbyedoGFRSDi4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, r11);
    }
}
