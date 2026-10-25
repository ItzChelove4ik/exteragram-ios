package org.telegram.ui.Stories.recorder;

import android.graphics.Bitmap;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Stories.recorder.StoryRecorder;

/* loaded from: classes7.dex */
public final /* synthetic */ class PaintView$$ExternalSyntheticLambda5 implements Utilities.Callback2 {
    public final /* synthetic */ StoryRecorder.WindowView f$0;

    public /* synthetic */ PaintView$$ExternalSyntheticLambda5(StoryRecorder.WindowView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.drawBlurBitmap((Bitmap) r1, ((Float) r2).floatValue());
    }
}
