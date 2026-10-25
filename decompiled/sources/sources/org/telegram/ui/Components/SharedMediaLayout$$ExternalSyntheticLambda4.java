package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.view.View;
import org.telegram.ui.Components.SharedMediaLayout;
import org.telegram.ui.Components.blur3.ViewGroupPartRenderer;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$$ExternalSyntheticLambda4 implements ViewGroupPartRenderer.DrawChildMethod {
    public final /* synthetic */ SharedMediaLayout.InternalListView f$0;

    public /* synthetic */ SharedMediaLayout$$ExternalSyntheticLambda4(SharedMediaLayout.InternalListView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.blur3.ViewGroupPartRenderer.DrawChildMethod
    public final boolean drawChild(Canvas r1, View r2, long r3) {
        return this.f$0.drawChild(r1, r2, r3);
    }
}
