package org.telegram.ui;

import android.graphics.Canvas;
import android.view.View;
import org.telegram.ui.Components.blur3.ViewGroupPartRenderer;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class TopicsFragment$$ExternalSyntheticLambda6 implements ViewGroupPartRenderer.DrawChildMethod {
    public final /* synthetic */ TopicsFragment.TopicsRecyclerView f$0;

    public /* synthetic */ TopicsFragment$$ExternalSyntheticLambda6(TopicsFragment.TopicsRecyclerView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.blur3.ViewGroupPartRenderer.DrawChildMethod
    public final boolean drawChild(Canvas r1, View r2, long r3) {
        return this.f$0.drawChild(r1, r2, r3);
    }
}
