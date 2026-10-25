package org.telegram.messenger;

import android.view.ViewTreeObserver;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidUtilities$$ExternalSyntheticLambda5 implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ ViewTreeObserver f$0;
    public final /* synthetic */ ViewTreeObserver.OnPreDrawListener[] f$1;
    public final /* synthetic */ boolean[] f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ AndroidUtilities$$ExternalSyntheticLambda5(ViewTreeObserver r1, ViewTreeObserver.OnPreDrawListener[] r2, boolean[] r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        return AndroidUtilities.$r8$lambda$nDdac_H_x5E6XpTEYUCiwboDsTU(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
