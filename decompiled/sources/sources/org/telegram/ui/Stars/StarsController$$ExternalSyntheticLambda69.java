package org.telegram.ui.Stars;

import android.content.Context;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda69 implements RequestDelegate {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$10;
    public final /* synthetic */ TLRPC.TL_payments_paymentFormStars f$11;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ Context f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ String f$7;
    public final /* synthetic */ TLRPC.InputInvoice f$8;
    public final /* synthetic */ long f$9;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda69(StarsController r1, Utilities.Callback r2, MessageObject r3, Context r4, long r5, String r7, int r8, String r9, TLRPC.InputInvoice r10, long r11, Theme.ResourcesProvider r13, TLRPC.TL_payments_paymentFormStars r14) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
        this.f$9 = r11;
        this.f$10 = r13;
        this.f$11 = r14;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r17, TLRPC.TL_error r18) {
        StarsController.$r8$lambda$ELZfW1FgLHetd9NKqKhsEWRaRmI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, r17, r18);
    }
}
