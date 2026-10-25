package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda107 implements Utilities.Callback {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$5;
    public final /* synthetic */ Object f$6;
    public final /* synthetic */ HashMap f$7;
    public final /* synthetic */ boolean f$8;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda107(SendMessagesHelper r1, MessageObject r2, String r3, SendMessagesHelper.DelayedMessage r4, boolean r5, SendMessagesHelper.DelayedMessage r6, Object r7, HashMap r8, boolean r9) {
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

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r11) {
        SendMessagesHelper.m4933$r8$lambda$hq_P9QudbRtY9mkkjGFy502nHY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, (TLObject) r11);
    }
}
