package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ TL_stars.StarGift f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$6;
    public final /* synthetic */ Utilities.Callback2 f$7;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda13(StarsController r1, boolean[] r2, TL_stars.StarGift r3, boolean r4, boolean r5, long r6, TLRPC.TL_textWithEntities r8, Utilities.Callback2 r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.$r8$lambda$gFpgIzIuyrk3mmdsthuyvBmQnk0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
