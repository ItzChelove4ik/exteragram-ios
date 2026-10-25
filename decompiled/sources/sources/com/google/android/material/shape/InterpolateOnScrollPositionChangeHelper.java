package com.google.android.material.shape;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class InterpolateOnScrollPositionChangeHelper {
    private final int[] containerLocation;
    private ScrollView containingScrollView;
    private MaterialShapeDrawable materialShapeDrawable;
    private final ViewTreeObserver.OnScrollChangedListener scrollChangedListener;
    private final int[] scrollLocation;
    private View shapedView;

    /* renamed from: com.google.android.material.shape.InterpolateOnScrollPositionChangeHelper$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewTreeObserver.OnScrollChangedListener {
        final /* synthetic */ InterpolateOnScrollPositionChangeHelper this$0;

        public AnonymousClass1(InterpolateOnScrollPositionChangeHelper r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            this.this$0.updateInterpolationForScreenPosition();
        }
    }

    public InterpolateOnScrollPositionChangeHelper(View r3, MaterialShapeDrawable r4, ScrollView r5) {
        this.scrollLocation = new int[2];
        this.containerLocation = new int[2];
        this.scrollChangedListener = new AnonymousClass1(this);
        this.shapedView = r3;
        this.materialShapeDrawable = r4;
        this.containingScrollView = r5;
    }

    public void setContainingScrollView(ScrollView r1) {
        this.containingScrollView = r1;
    }

    public void setMaterialShapeDrawable(MaterialShapeDrawable r1) {
        this.materialShapeDrawable = r1;
    }

    public void startListeningForScrollChanges(ViewTreeObserver r1) {
        r1.addOnScrollChangedListener(this.scrollChangedListener);
    }

    public void stopListeningForScrollChanges(ViewTreeObserver r1) {
        r1.removeOnScrollChangedListener(this.scrollChangedListener);
    }

    public void updateInterpolationForScreenPosition() {
        ScrollView r0 = this.containingScrollView;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.getChildCount() == 0) goto L19;
        this.containingScrollView.getLocationInWindow(this.scrollLocation);
        this.containingScrollView.getChildAt(0).getLocationInWindow(this.containerLocation);
        int r02 = (this.shapedView.getTop() - this.scrollLocation[1]) + this.containerLocation[1];
        int r1 = this.shapedView.getHeight();
        int r2 = this.containingScrollView.getHeight();
        if (r02 >= 0) goto L11;
        this.materialShapeDrawable.setInterpolation(Math.max(0.0f, Math.min(1.0f, (r02 / r1) + 1.0f)));
        this.shapedView.invalidate();
        return;
    L11:
        int r03 = r02 + r1;
        MaterialShapeDrawable r5 = this.materialShapeDrawable;
        if (r03 <= r2) goto L16;
        r5.setInterpolation(Math.max(0.0f, Math.min(1.0f, 1.0f - ((r03 - r2) / r1))));
        this.shapedView.invalidate();
        return;
    L16:
        if (r5.getInterpolation() == 1.0f) goto L21;
        this.materialShapeDrawable.setInterpolation(1.0f);
        this.shapedView.invalidate();
        return;
    L21:
        return;
    L19:
        Segment$$ExternalSyntheticBUOutline1.m("Scroll bar must contain a child to calculate interpolation.");
    }
}
