package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda114 implements RequestDelegate {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLRPC.Document f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ long f$6;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda114(StarGiftSheet r1, AlertDialog r2, TLRPC.Document r3, String r4, String r5, String r6, long r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        StarGiftSheet.m18950$r8$lambda$5pFkyhXQRHitZEyZCcgU4pOLak(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r11, r12);
    }
}
