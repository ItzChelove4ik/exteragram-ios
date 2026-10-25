package org.telegram.ui;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_payments;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda91 implements Utilities.Callback {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda91(ProfileActivity r1, Context r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r5) {
        ProfileActivity.$r8$lambda$UWluQAtu5O8iJDjRqKL3dkJ0HNI(this.f$0, this.f$1, this.f$2, (TL_payments.connectedBotStarRef) r5);
    }
}
