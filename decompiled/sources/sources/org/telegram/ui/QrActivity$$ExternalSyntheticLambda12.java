package org.telegram.ui;

import android.util.Pair;
import org.telegram.tgnet.ResultCallback;

/* loaded from: classes6.dex */
public final /* synthetic */ class QrActivity$$ExternalSyntheticLambda12 implements ResultCallback {
    public final /* synthetic */ QrActivity f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ QrActivity$$ExternalSyntheticLambda12(QrActivity r1, boolean r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.ResultCallback
    public final void onComplete(Object r5) {
        QrActivity.$r8$lambda$E7rBsS4H1foN5vAZS5f1iJ3Fb8M(this.f$0, this.f$1, this.f$2, (Pair) r5);
    }
}
