package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class PrivacySettingsActivity$$ExternalSyntheticLambda23 implements Runnable {
    public final /* synthetic */ PrivacySettingsActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TL_account.setAccountTTL f$3;

    public /* synthetic */ PrivacySettingsActivity$$ExternalSyntheticLambda23(PrivacySettingsActivity r1, AlertDialog r2, TLObject r3, TL_account.setAccountTTL r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PrivacySettingsActivity.$r8$lambda$TbCpifjfFg58q91BkxQAW__hsb8(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
