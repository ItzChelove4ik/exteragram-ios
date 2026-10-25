package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda4 implements Utilities.Callback2 {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$6;
    public final /* synthetic */ Object f$7;
    public final /* synthetic */ HashMap f$8;
    public final /* synthetic */ boolean f$9;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda4(SendMessagesHelper r1, TLObject r2, MessageObject r3, String r4, SendMessagesHelper.DelayedMessage r5, boolean r6, SendMessagesHelper.DelayedMessage r7, Object r8, HashMap r9, boolean r10) {
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
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r13, Object r14) {
        SendMessagesHelper.m4940$r8$lambda$pr8Op0zLd7orctSM_UeZIVAuiQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, (TLRPC.EmojiGameInfo) r13, (TLRPC.TL_error) r14);
    }
}
