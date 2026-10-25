package org.telegram.ui.Components;

import android.graphics.Canvas;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.quickforward.BlurVisibilityDrawable;

/* loaded from: classes7.dex */
public final /* synthetic */ class Bulletin$Layout$$ExternalSyntheticLambda0 implements BlurVisibilityDrawable.DrawRunnable {
    public final /* synthetic */ Bulletin.Layout f$0;

    public /* synthetic */ Bulletin$Layout$$ExternalSyntheticLambda0(Bulletin.Layout r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.quickforward.BlurVisibilityDrawable.DrawRunnable
    public final void draw(Canvas r1, int r2) {
        this.f$0.dispatchDrawImplBlur(r1, r2);
    }
}
