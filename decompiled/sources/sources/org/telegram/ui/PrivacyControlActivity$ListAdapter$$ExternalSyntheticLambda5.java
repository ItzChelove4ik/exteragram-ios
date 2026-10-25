package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.PrivacyControlActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PrivacyControlActivity$ListAdapter$$ExternalSyntheticLambda5 implements Utilities.Callback {
    public final /* synthetic */ PrivacyControlActivity.ListAdapter f$0;

    public /* synthetic */ PrivacyControlActivity$ListAdapter$$ExternalSyntheticLambda5(PrivacyControlActivity.ListAdapter r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        PrivacyControlActivity.ListAdapter.$r8$lambda$uh11n3pfsvX82ztjE0pV8zVPxyU(this.f$0, (TL_account.TL_birthday) r1);
    }
}
