package org.telegram.ui.Stars;

import android.content.Context;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda26 implements Utilities.Callback {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.TL_payments_paymentFormStars f$10;
    public final /* synthetic */ int f$11;
    public final /* synthetic */ long f$12;
    public final /* synthetic */ boolean[] f$2;
    public final /* synthetic */ Utilities.Callback f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ Theme.ResourcesProvider f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ String f$7;
    public final /* synthetic */ MessageObject f$8;
    public final /* synthetic */ TLRPC.InputInvoice f$9;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda26(StarsController r1, long r2, boolean[] r4, Utilities.Callback r5, Context r6, Theme.ResourcesProvider r7, boolean r8, String r9, MessageObject r10, TLRPC.InputInvoice r11, TLRPC.TL_payments_paymentFormStars r12, int r13, long r14) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
        this.f$9 = r11;
        this.f$10 = r12;
        this.f$11 = r13;
        this.f$12 = r14;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r19) {
        StarsController.$r8$lambda$UykCEEPChVwGE3i07RyGQcf0aII(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, (Utilities.Callback) r19);
    }
}
