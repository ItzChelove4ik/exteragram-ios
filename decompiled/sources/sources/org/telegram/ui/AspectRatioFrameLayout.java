package org.telegram.ui;

import android.content.Context;
import android.graphics.Matrix;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.tgnet.TLObject;

/* loaded from: classes6.dex */
public class AspectRatioFrameLayout extends FrameLayout {
    private final AspectRatioUpdateDispatcher aspectRatioUpdateDispatcher;
    private boolean drawingReady;
    private Matrix matrix;
    private int resizeMode;
    private int rotation;
    private float videoAspectRatio;

    public interface AspectRatioListener {
    }

    public final class AspectRatioUpdateDispatcher implements Runnable {
        private boolean aspectRatioMismatch;
        private boolean isScheduled;
        private float naturalAspectRatio;
        private float targetAspectRatio;
        final /* synthetic */ AspectRatioFrameLayout this$0;

        private AspectRatioUpdateDispatcher(AspectRatioFrameLayout r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ AspectRatioUpdateDispatcher(AspectRatioFrameLayout r1, AspectRatioFrameLayoutIA r2) {
            this(r1);
        }

        @Override // java.lang.Runnable
        public void run() {
            this.isScheduled = false;
            AspectRatioFrameLayout.m6239$$Nest$fgetaspectRatioListener(this.this$0);
        }

        public void scheduleUpdate(float r1, float r2, boolean r3) {
            this.targetAspectRatio = r1;
            this.naturalAspectRatio = r2;
            this.aspectRatioMismatch = r3;
            if (this.isScheduled == true) goto L6;
            this.isScheduled = true;
            this.this$0.post(this);
            return;
        }
    }

    /* renamed from: -$$Nest$fgetaspectRatioListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AspectRatioListener m6239$$Nest$fgetaspectRatioListener(AspectRatioFrameLayout r0) {
        r0.getClass();
        return null;
    }

    public AspectRatioFrameLayout(Context r2) {
        super(r2);
        this.matrix = new Matrix();
        this.resizeMode = 0;
        this.aspectRatioUpdateDispatcher = new AspectRatioUpdateDispatcher(this, null);
    }

    public float getAspectRatio() {
        return this.videoAspectRatio;
    }

    public int getResizeMode() {
        return this.resizeMode;
    }

    public int getVideoRotation() {
        return this.rotation;
    }

    public boolean isDrawingReady() {
        return this.drawingReady;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r12, int r13) {
        super.onMeasure(r12, r13);
        if (this.videoAspectRatio <= 0.0f) goto L44;
        int r122 = getMeasuredWidth();
        int r0 = getMeasuredHeight();
        float r1 = r122;
        float r2 = r0;
        float r3 = r1 / r2;
        float r4 = (this.videoAspectRatio / r3) - 1.0f;
        int r7 = 0;
        if (Math.abs(r4) > 0.01f) goto L9;
        this.aspectRatioUpdateDispatcher.scheduleUpdate(this.videoAspectRatio, r3, false);
        return;
    L9:
        int r6 = this.resizeMode;
        if (r6 == 0) goto L29;
        if (r6 == 1) goto L28;
        if (r6 != 2) goto L14;
        float r22 = r2 * this.videoAspectRatio;
    L21:
        r122 = (int) r22;
    L32:
        this.aspectRatioUpdateDispatcher.scheduleUpdate(this.videoAspectRatio, r3, true);
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30));
        int r123 = getChildCount();
    L33:
        if (r7 >= r123) goto L47;
        View r132 = getChildAt(r7);
        if ((r132 instanceof TextureView) == true) goto L36;
        r7 = r7 + 1;
        goto L33
    L36:
        this.matrix.reset();
        int r124 = getWidth() / 2;
        float r125 = r124;
        float r02 = getHeight() / 2;
        this.matrix.postRotate(this.rotation, r125, r02);
        int r14 = this.rotation;
        if (r14 != 90) goto L39;
    L40:
        float r15 = getHeight() / getWidth();
        this.matrix.postScale(1.0f / r15, r15, r125, r02);
    L41:
        ((TextureView) r132).setTransform(this.matrix);
        return;
    L39:
        if (r14 != 270) goto L41;
    L47:
        return;
    L14:
        if (r6 != 3) goto L16;
        float r42 = this.videoAspectRatio;
        if (r4 <= 0.0f) goto L22;
    L20:
        r22 = r2 * r42;
    L22:
        float r16 = r1 / r42;
    L23:
        r0 = (int) r16;
        goto L32
    L16:
        if (r6 != 4) goto L32;
        r42 = this.videoAspectRatio;
        if (r4 <= 0.0f) goto L22;
    L28:
        r16 = r1 / this.videoAspectRatio;
        goto L23
    L29:
        r42 = this.videoAspectRatio;
        if (r4 <= 0.0f) goto L20;
    }

    public void setAspectRatio(float r2, int r3) {
        if (this.videoAspectRatio == r2) goto L6;
        this.videoAspectRatio = r2;
        this.rotation = r3;
        requestLayout();
        return;
    }

    public void setAspectRatioListener(AspectRatioListener r1) {
    }

    public void setDrawingReady(boolean r2) {
        if (this.drawingReady != r2) goto L5;
        return;
    L5:
        this.drawingReady = r2;
    }

    public void setResizeMode(int r2) {
        if (this.resizeMode == r2) goto L6;
        this.resizeMode = r2;
        requestLayout();
        return;
    }
}
