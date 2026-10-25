package org.telegram.ui;

import android.graphics.Canvas;
import android.graphics.RectF;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda235 implements IBlur3Capture {
    public final /* synthetic */ ChatActivity.ChatActivityFragmentView f$0;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda235(ChatActivity.ChatActivityFragmentView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.blur3.capture.IBlur3Capture
    public final void capture(Canvas r1, RectF r2) {
        this.f$0.drawList(r1, r2);
    }
}
