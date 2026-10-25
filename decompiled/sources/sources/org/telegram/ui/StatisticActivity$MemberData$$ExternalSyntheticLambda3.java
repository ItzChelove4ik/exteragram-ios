package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.StatisticActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class StatisticActivity$MemberData$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ StatisticActivity.MemberData f$0;
    public final /* synthetic */ StatisticActivity f$1;
    public final /* synthetic */ AlertDialog[] f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ TLObject f$4;
    public final /* synthetic */ TLRPC.ChatFull f$5;

    public /* synthetic */ StatisticActivity$MemberData$$ExternalSyntheticLambda3(StatisticActivity.MemberData r1, StatisticActivity r2, AlertDialog[] r3, TLRPC.TL_error r4, TLObject r5, TLRPC.ChatFull r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StatisticActivity.MemberData.$r8$lambda$5zhsaTZLAzn4wVFResRXdYRND20(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
