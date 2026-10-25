package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class NewContactBottomSheet$$ExternalSyntheticLambda17 implements Utilities.Callback {
    public final /* synthetic */ NewContactBottomSheet f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ NewContactBottomSheet$$ExternalSyntheticLambda17(NewContactBottomSheet r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        NewContactBottomSheet.m16237$r8$lambda$DL8fv78VwHKyP3gJaC3QhX7vMw(this.f$0, this.f$1, (TLRPC.User) r2);
    }
}
