package org.telegram.ui.bots;

import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotShareSheet$$ExternalSyntheticLambda7 implements Utilities.Callback2 {
    public final /* synthetic */ int[] f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ NotificationCenter.NotificationCenterDelegate[] f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ BotShareSheet$$ExternalSyntheticLambda7(int[] r1, Utilities.Callback r2, NotificationCenter.NotificationCenterDelegate[] r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        BotShareSheet.$r8$lambda$H6rY4tHTKZdG56Ilq3PeqEHvCN4(this.f$0, this.f$1, this.f$2, this.f$3, (TL_account.webPagePreview) r7, (TLRPC.TL_error) r8);
    }
}
