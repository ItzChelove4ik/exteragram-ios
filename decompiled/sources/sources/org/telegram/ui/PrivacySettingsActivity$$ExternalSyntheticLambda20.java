package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.TextCheckCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class PrivacySettingsActivity$$ExternalSyntheticLambda20 implements RequestDelegate {
    public final /* synthetic */ PrivacySettingsActivity f$0;
    public final /* synthetic */ TextCheckCell f$1;

    public /* synthetic */ PrivacySettingsActivity$$ExternalSyntheticLambda20(PrivacySettingsActivity r1, TextCheckCell r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        PrivacySettingsActivity.$r8$lambda$_ROrzV_QIHwj6awTi9NLspbxPwI(this.f$0, this.f$1, r2, r3);
    }
}
