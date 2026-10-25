package org.telegram.ui.Stars;

import android.content.Context;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda79 implements Runnable {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ long f$10;
    public final /* synthetic */ TLRPC.TL_error f$11;
    public final /* synthetic */ Theme.ResourcesProvider f$12;
    public final /* synthetic */ TLRPC.TL_payments_paymentFormStars f$13;
    public final /* synthetic */ Utilities.Callback f$2;
    public final /* synthetic */ MessageObject f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ String f$6;
    public final /* synthetic */ int f$7;
    public final /* synthetic */ String f$8;
    public final /* synthetic */ TLRPC.InputInvoice f$9;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda79(StarsController r1, TLObject r2, Utilities.Callback r3, MessageObject r4, Context r5, long r6, String r8, int r9, String r10, TLRPC.InputInvoice r11, long r12, TLRPC.TL_error r14, Theme.ResourcesProvider r15, TLRPC.TL_payments_paymentFormStars r16) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
        this.f$9 = r11;
        this.f$10 = r12;
        this.f$11 = r14;
        this.f$12 = r15;
        this.f$13 = r16;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.$r8$lambda$hF5jE61w44C3qcPHxhBDaeGitf8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13);
    }
}
