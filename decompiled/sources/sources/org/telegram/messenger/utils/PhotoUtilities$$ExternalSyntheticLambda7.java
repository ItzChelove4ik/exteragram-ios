package org.telegram.messenger.utils;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.INavigationLayout;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoUtilities$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ TLObject f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.PhotoSize f$2;
    public final /* synthetic */ TLRPC.PhotoSize f$3;
    public final /* synthetic */ Runnable f$4;
    public final /* synthetic */ INavigationLayout f$5;

    public /* synthetic */ PhotoUtilities$$ExternalSyntheticLambda7(TLObject r1, int r2, TLRPC.PhotoSize r3, TLRPC.PhotoSize r4, Runnable r5, INavigationLayout r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PhotoUtilities.m5202$r8$lambda$qx2kormntPEvjsmL_mb7R_n0Dg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
