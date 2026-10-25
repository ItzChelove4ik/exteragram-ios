package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Components.ButtonSpan;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda168 implements Runnable {
    public final /* synthetic */ Utilities.Callback2 f$0;
    public final /* synthetic */ ButtonSpan.TextViewButtons[] f$1;
    public final /* synthetic */ TL_stars.UniqueStarGiftValueInfo f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda168(Utilities.Callback2 r1, ButtonSpan.TextViewButtons[] r2, TL_stars.UniqueStarGiftValueInfo r3, String r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarGiftSheet.m18960$r8$lambda$HaST1O5xF2EKQJq4DS7nSu51DE(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
