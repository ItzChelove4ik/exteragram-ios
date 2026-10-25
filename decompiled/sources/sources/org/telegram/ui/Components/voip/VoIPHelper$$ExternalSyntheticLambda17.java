package org.telegram.ui.Components.voip;

import android.app.Activity;
import org.telegram.messenger.AccountInstance;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.JoinCallAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class VoIPHelper$$ExternalSyntheticLambda17 implements JoinCallAlert.JoinCallAlertDelegate {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ Activity f$1;
    public final /* synthetic */ AccountInstance f$2;
    public final /* synthetic */ TLRPC.Chat f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ TLRPC.User f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ boolean f$7;
    public final /* synthetic */ BaseFragment f$8;

    public /* synthetic */ VoIPHelper$$ExternalSyntheticLambda17(boolean r1, Activity r2, AccountInstance r3, TLRPC.Chat r4, String r5, TLRPC.User r6, boolean r7, boolean r8, BaseFragment r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // org.telegram.ui.Components.JoinCallAlert.JoinCallAlertDelegate
    public final void didSelectChat(TLRPC.InputPeer r14, boolean r15, boolean r16, boolean r17) {
        VoIPHelper.$r8$lambda$ughoGj68usXSB2IOxLqz6uhBjN8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, r14, r15, r16, r17);
    }
}
