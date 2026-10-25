package org.telegram.ui;

import android.content.Context;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public final /* synthetic */ class ReportBottomSheet$$ExternalSyntheticLambda8 implements RequestDelegate {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ Theme.ResourcesProvider f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ byte[] f$3;
    public final /* synthetic */ ChatActivity f$4;
    public final /* synthetic */ MessageObject f$5;
    public final /* synthetic */ int f$6;

    public /* synthetic */ ReportBottomSheet$$ExternalSyntheticLambda8(Context r1, Theme.ResourcesProvider r2, long r3, byte[] r5, ChatActivity r6, MessageObject r7, int r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        ReportBottomSheet.$r8$lambda$cVCd8JcyjbXBr7LbGe5qY3Z8oEU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r11, r12);
    }
}
