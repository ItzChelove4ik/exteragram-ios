package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class SettingsActivity$$ExternalSyntheticLambda12 implements Utilities.Callback {
    public final /* synthetic */ SettingsActivity f$0;
    public final /* synthetic */ TLRPC.TL_attachMenuBot f$1;

    public /* synthetic */ SettingsActivity$$ExternalSyntheticLambda12(SettingsActivity r1, TLRPC.TL_attachMenuBot r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        SettingsActivity.$r8$lambda$cWsNBIIPSOi1BtMHvlu_TBSOEOI(this.f$0, this.f$1, (Boolean) r2);
    }
}
