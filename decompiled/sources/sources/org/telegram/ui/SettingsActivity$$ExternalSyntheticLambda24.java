package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class SettingsActivity$$ExternalSyntheticLambda24 implements RequestDelegate {
    public final /* synthetic */ SettingsActivity f$0;
    public final /* synthetic */ TLRPC.TL_attachMenuBot f$1;

    public /* synthetic */ SettingsActivity$$ExternalSyntheticLambda24(SettingsActivity r1, TLRPC.TL_attachMenuBot r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        SettingsActivity.$r8$lambda$bY74nDloMU376k4kE8vPWAn4ygE(this.f$0, this.f$1, r2, r3);
    }
}
