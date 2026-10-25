package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class SettingsActivity$$ExternalSyntheticLambda23 implements RequestDelegate {
    public final /* synthetic */ SettingsActivity f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ SettingsActivity$$ExternalSyntheticLambda23(SettingsActivity r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        SettingsActivity.$r8$lambda$ZE47s89U9nSH3nmxmUTUScYNxN8(this.f$0, this.f$1, r2, r3);
    }
}
