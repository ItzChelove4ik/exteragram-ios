package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ HashMap f$10;
    public final /* synthetic */ boolean f$11;
    public final /* synthetic */ TLRPC.TL_inputMediaStakeDice f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ MessageObject f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$6;
    public final /* synthetic */ boolean f$7;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$8;
    public final /* synthetic */ Object f$9;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda5(SendMessagesHelper r1, BaseFragment r2, TLRPC.TL_inputMediaStakeDice r3, TLObject r4, MessageObject r5, String r6, SendMessagesHelper.DelayedMessage r7, boolean r8, SendMessagesHelper.DelayedMessage r9, Object r10, HashMap r11, boolean r12) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
        this.f$9 = r10;
        this.f$10 = r11;
        this.f$11 = r12;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4921$r8$lambda$T9StOUuA_8Q_YTzpHIC6WeS6C0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11);
    }
}
