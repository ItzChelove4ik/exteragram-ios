package org.telegram.ui;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.DialogsActivity;

/* loaded from: classes3.dex */
public final /* synthetic */ class DialogsActivity$$ExternalSyntheticLambda1 implements IBlur3Capture {
    public final /* synthetic */ DialogsActivity f$0;
    public final /* synthetic */ DialogsActivity.ContentView f$1;
    public final /* synthetic */ PointF f$2;

    public /* synthetic */ DialogsActivity$$ExternalSyntheticLambda1(DialogsActivity r1, DialogsActivity.ContentView r2, PointF r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.Components.blur3.capture.IBlur3Capture
    public final void capture(Canvas r3, RectF r4) {
        DialogsActivity.$r8$lambda$A8BaVAEWlnqdeEhHWCHwZw2YWf4(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
