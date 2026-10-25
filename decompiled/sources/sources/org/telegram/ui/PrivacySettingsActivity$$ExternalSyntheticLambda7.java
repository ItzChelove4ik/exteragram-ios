package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class PrivacySettingsActivity$$ExternalSyntheticLambda7 implements Utilities.Callback2 {
    public final /* synthetic */ PrivacySettingsActivity f$0;

    public /* synthetic */ PrivacySettingsActivity$$ExternalSyntheticLambda7(PrivacySettingsActivity r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        PrivacySettingsActivity.$r8$lambda$6tM66M8fYAphlC4o33E5HWxc930(this.f$0, (TL_account.Passkeys) r1, (TLRPC.TL_error) r2);
    }
}
