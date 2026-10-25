package org.telegram.ui;

import android.content.DialogInterface;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.StatisticActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class StatisticActivity$MemberData$$ExternalSyntheticLambda2 implements DialogInterface.OnClickListener {
    public final /* synthetic */ StatisticActivity.MemberData f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ TLRPC.ChatFull f$2;
    public final /* synthetic */ TLRPC.TL_chatChannelParticipant f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ StatisticActivity f$5;

    public /* synthetic */ StatisticActivity$MemberData$$ExternalSyntheticLambda2(StatisticActivity.MemberData r1, ArrayList r2, TLRPC.ChatFull r3, TLRPC.TL_chatChannelParticipant r4, boolean r5, StatisticActivity r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface r9, int r10) {
        StatisticActivity.MemberData.m19286$r8$lambda$_QnG5I1j0WPrYc4E1CjeJHnQw8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
