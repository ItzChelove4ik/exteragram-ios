package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.RectF;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class RecyclerListView$$ExternalSyntheticLambda1 implements Utilities.Callback5 {
    public final /* synthetic */ RecyclerListView f$0;

    public /* synthetic */ RecyclerListView$$ExternalSyntheticLambda1(RecyclerListView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback5
    public final void run(Object r1, Object r2, Object r3, Object r4, Object r5) {
        this.f$0.drawBackgroundRect((Canvas) r1, (RectF) r2, ((Float) r3).floatValue(), ((Float) r4).floatValue(), ((Float) r5).floatValue());
    }
}
