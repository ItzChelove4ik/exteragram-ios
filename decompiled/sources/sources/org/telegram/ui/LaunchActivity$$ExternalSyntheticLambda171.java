package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda171 implements Utilities.Callback {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ DialogsActivity f$3;
    public final /* synthetic */ BaseFragment f$4;
    public final /* synthetic */ TLRPC.User f$5;
    public final /* synthetic */ String f$6;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda171(LaunchActivity r1, int r2, long r3, DialogsActivity r5, BaseFragment r6, TLRPC.User r7, String r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r10) {
        LaunchActivity.m15620$r8$lambda$_tMGBX22U6AVTqRBsB77oU87ug(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, (Boolean) r10);
    }
}
