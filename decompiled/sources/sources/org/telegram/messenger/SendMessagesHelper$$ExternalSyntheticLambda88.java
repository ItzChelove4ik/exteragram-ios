package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda88 implements Utilities.Callback {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ ArrayList f$3;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda88(SendMessagesHelper r1, ArrayList r2, ArrayList r3, ArrayList r4, SendMessagesHelper.DelayedMessage r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r8) {
        SendMessagesHelper.$r8$lambda$dIMXoHoGQzxQ8PQGP6SRtUHqWJI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (TLObject) r8);
    }
}
