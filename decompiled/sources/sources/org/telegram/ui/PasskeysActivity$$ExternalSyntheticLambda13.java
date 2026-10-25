package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class PasskeysActivity$$ExternalSyntheticLambda13 implements Utilities.Callback2 {
    public final /* synthetic */ PasskeysActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TL_account.Passkey f$2;

    public /* synthetic */ PasskeysActivity$$ExternalSyntheticLambda13(PasskeysActivity r1, int r2, TL_account.Passkey r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        PasskeysActivity.$r8$lambda$5j64GBgcmm__wMiJbWcuNZTZz1k(this.f$0, this.f$1, this.f$2, (TLRPC.Bool) r3, (TLRPC.TL_error) r4);
    }
}
