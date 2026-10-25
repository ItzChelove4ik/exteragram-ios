package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda175 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ DialogsActivity f$2;
    public final /* synthetic */ BaseFragment f$3;
    public final /* synthetic */ TLRPC.User f$4;
    public final /* synthetic */ String f$5;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda175(LaunchActivity r1, int r2, DialogsActivity r3, BaseFragment r4, TLRPC.User r5, String r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        LaunchActivity.$r8$lambda$AO4XFBhJMV92sWVv3M3W1R3UIIo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
