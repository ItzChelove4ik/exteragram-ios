package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ProximitySheet;

/* loaded from: classes6.dex */
public final /* synthetic */ class LocationActivity$$ExternalSyntheticLambda40 implements ProximitySheet.onRadiusPickerChange {
    public final /* synthetic */ LocationActivity f$0;
    public final /* synthetic */ TLRPC.User f$1;

    public /* synthetic */ LocationActivity$$ExternalSyntheticLambda40(LocationActivity r1, TLRPC.User r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.ProximitySheet.onRadiusPickerChange
    public final boolean run(boolean r2, int r3) {
        return LocationActivity.$r8$lambda$KdoVnXnWdTLwT7IWRxQ4Zpg_o2Q(this.f$0, this.f$1, r2, r3);
    }
}
