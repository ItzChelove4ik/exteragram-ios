package org.telegram.ui;

import android.graphics.Canvas;
import android.graphics.RectF;
import org.telegram.ui.ChannelAdminLogActivity;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelAdminLogActivity$$ExternalSyntheticLambda11 implements IBlur3Capture {
    public final /* synthetic */ ChannelAdminLogActivity.ChatActivityFragmentView f$0;

    public /* synthetic */ ChannelAdminLogActivity$$ExternalSyntheticLambda11(ChannelAdminLogActivity.ChatActivityFragmentView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.blur3.capture.IBlur3Capture
    public final void capture(Canvas r1, RectF r2) {
        this.f$0.drawList(r1, r2);
    }
}
