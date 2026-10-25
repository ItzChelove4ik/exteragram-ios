package org.telegram.ui;

import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.PassportActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$8$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ PassportActivity.AnonymousClass8 f$0;
    public final /* synthetic */ TL_account.passwordSettings f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ byte[] f$3;

    public /* synthetic */ PassportActivity$8$$ExternalSyntheticLambda9(PassportActivity.AnonymousClass8 r1, TL_account.passwordSettings r2, boolean r3, byte[] r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PassportActivity.AnonymousClass8.$r8$lambda$cj08VjAn1Zr62h5vnpME7j18mvc(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
