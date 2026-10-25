package org.telegram.ui.Components;

import android.widget.TextView;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersAlert$$ExternalSyntheticLambda58 implements Runnable {
    public final /* synthetic */ StickersAlert f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ TextView f$4;

    public /* synthetic */ StickersAlert$$ExternalSyntheticLambda58(StickersAlert r1, String r2, TLRPC.TL_error r3, TLObject r4, TextView r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StickersAlert.$r8$lambda$6pOusNRCLslaUf6Z3_3hkId72v0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
