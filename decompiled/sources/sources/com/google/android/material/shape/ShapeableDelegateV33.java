package com.google.android.material.shape;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

/* loaded from: classes5.dex */
class ShapeableDelegateV33 extends ShapeableDelegate {

    /* renamed from: com.google.android.material.shape.ShapeableDelegateV33$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewOutlineProvider {
        final /* synthetic */ ShapeableDelegateV33 this$0;

        public AnonymousClass1(ShapeableDelegateV33 r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r1, Outline r2) {
            if (this.this$0.shapePath.isEmpty() == true) goto L6;
            r2.setPath(this.this$0.shapePath);
            return;
        }
    }

    public ShapeableDelegateV33(View r1) {
        initMaskOutlineProvider(r1);
    }

    private void initMaskOutlineProvider(View r2) {
        r2.setOutlineProvider(new AnonymousClass1(this));
    }

    @Override // com.google.android.material.shape.ShapeableDelegate
    public void invalidateClippingMethod(View r2) {
        r2.setClipToOutline(!shouldUseCompatClipping());
        if (shouldUseCompatClipping() == false) goto L6;
        r2.invalidate();
        return;
    L6:
        r2.invalidateOutline();
    }

    @Override // com.google.android.material.shape.ShapeableDelegate
    public boolean shouldUseCompatClipping() {
        return this.forceCompatClippingEnabled;
    }
}
