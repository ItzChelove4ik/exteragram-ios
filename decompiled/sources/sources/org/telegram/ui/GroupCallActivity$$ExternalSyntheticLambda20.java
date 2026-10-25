package org.telegram.ui;

import android.view.View;
import org.telegram.messenger.AccountInstance;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.NumberPicker;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$$ExternalSyntheticLambda20 implements View.OnClickListener {
    public final /* synthetic */ GroupCallActivity f$0;
    public final /* synthetic */ NumberPicker f$1;
    public final /* synthetic */ NumberPicker f$2;
    public final /* synthetic */ NumberPicker f$3;
    public final /* synthetic */ TLRPC.Chat f$4;
    public final /* synthetic */ AccountInstance f$5;
    public final /* synthetic */ TLRPC.InputPeer f$6;

    public /* synthetic */ GroupCallActivity$$ExternalSyntheticLambda20(GroupCallActivity r1, NumberPicker r2, NumberPicker r3, NumberPicker r4, TLRPC.Chat r5, AccountInstance r6, TLRPC.InputPeer r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r9) {
        GroupCallActivity.$r8$lambda$veJMWJ9m_DCSnrt48Db6epvQ0Pw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r9);
    }
}
