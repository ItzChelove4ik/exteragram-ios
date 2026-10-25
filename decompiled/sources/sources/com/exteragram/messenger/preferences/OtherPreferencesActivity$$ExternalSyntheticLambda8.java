package com.exteragram.messenger.preferences;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes4.dex */
public final /* synthetic */ class OtherPreferencesActivity$$ExternalSyntheticLambda8 implements RequestDelegate {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ OtherPreferencesActivity f$1;

    public /* synthetic */ OtherPreferencesActivity$$ExternalSyntheticLambda8(AlertDialog r1, OtherPreferencesActivity r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        OtherPreferencesActivity.$r8$lambda$yWCQpFPXkqfATo6phc_BXBXCqMQ(this.f$0, this.f$1, r2, r3);
    }
}
