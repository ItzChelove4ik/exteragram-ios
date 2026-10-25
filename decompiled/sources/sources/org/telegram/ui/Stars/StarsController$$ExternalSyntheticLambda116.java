package org.telegram.ui.Stars;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda116 implements RequestDelegate {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ Theme.ResourcesProvider f$5;
    public final /* synthetic */ TLRPC.ChatInvite f$6;
    public final /* synthetic */ String f$7;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda116(StarsController r1, Utilities.Callback2 r2, long r3, String r5, Context r6, Theme.ResourcesProvider r7, TLRPC.ChatInvite r8, String r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r12, TLRPC.TL_error r13) {
        StarsController.m19107$r8$lambda$7GzBUcZ6Cvp4lUjewfeMTqwdxE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r12, r13);
    }
}
