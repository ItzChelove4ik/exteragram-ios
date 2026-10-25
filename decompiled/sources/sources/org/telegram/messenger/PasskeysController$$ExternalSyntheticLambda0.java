package org.telegram.messenger;

import android.content.Context;
import androidx.credentials.CredentialManager;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes5.dex */
public final /* synthetic */ class PasskeysController$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ Utilities.Callback3 f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ CredentialManager f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ Runnable[] f$6;

    public /* synthetic */ PasskeysController$$ExternalSyntheticLambda0(boolean[] r1, Utilities.Callback3 r2, boolean r3, CredentialManager r4, Context r5, int r6, Runnable[] r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r10, Object r11) {
        PasskeysController.$r8$lambda$3L4sUw_47wNxWVaSQ6GIWRaiOEQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, (TL_account.passkeyLoginOptions) r10, (TLRPC.TL_error) r11);
    }
}
