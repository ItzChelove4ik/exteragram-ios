package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.StatisticActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class StatisticActivity$MemberData$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ StatisticActivity.MemberData f$0;
    public final /* synthetic */ StatisticActivity f$1;
    public final /* synthetic */ AlertDialog[] f$2;
    public final /* synthetic */ TLRPC.ChatFull f$3;

    public /* synthetic */ StatisticActivity$MemberData$$ExternalSyntheticLambda1(StatisticActivity.MemberData r1, StatisticActivity r2, AlertDialog[] r3, TLRPC.ChatFull r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        StatisticActivity.MemberData.$r8$lambda$x3zz0qvH9VUeDY3KVti0hIsd26Y(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
