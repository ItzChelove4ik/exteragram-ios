package org.telegram.messenger;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes5.dex */
public final /* synthetic */ class FactCheckController$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ FactCheckController f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ AlertDialog f$4;

    public /* synthetic */ FactCheckController$$ExternalSyntheticLambda12(FactCheckController r1, TLObject r2, TLRPC.TL_textWithEntities r3, boolean r4, AlertDialog r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FactCheckController.$r8$lambda$YGSlWWCz3vO1AbKtahcZijYmhZ0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
