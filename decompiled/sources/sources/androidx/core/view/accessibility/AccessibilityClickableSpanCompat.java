package androidx.core.view.accessibility;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* loaded from: classes3.dex */
public final class AccessibilityClickableSpanCompat extends ClickableSpan {
    private final int mClickableSpanActionId;
    private final AccessibilityNodeInfoCompat mNodeInfoCompat;
    private final int mOriginalClickableSpanId;

    public AccessibilityClickableSpanCompat(int r1, AccessibilityNodeInfoCompat r2, int r3) {
        this.mOriginalClickableSpanId = r1;
        this.mNodeInfoCompat = r2;
        this.mClickableSpanActionId = r3;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View r3) {
        Bundle r32 = new Bundle();
        r32.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.mOriginalClickableSpanId);
        this.mNodeInfoCompat.performAction(this.mClickableSpanActionId, r32);
    }
}
