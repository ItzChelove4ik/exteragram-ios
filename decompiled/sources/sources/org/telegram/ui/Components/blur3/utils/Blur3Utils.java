package org.telegram.ui.Components.blur3.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceBitmap;
import org.telegram.ui.Components.chat.ViewPositionWatcher;

/* loaded from: classes3.dex */
public abstract class Blur3Utils {
    private static final RectF captureTmpChildPos = null;
    private static final RectF captureTmpRectF = null;
    private static final Matrix matrixTmp = null;

    /* renamed from: org.telegram.ui.Components.blur3.utils.Blur3Utils$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends Drawable {
        final /* synthetic */ Drawable val$drawable;
        final /* synthetic */ int val$h;
        final /* synthetic */ int val$w;

        public AnonymousClass1(int r1, int r2, Drawable r3) {
            this.val$w = r1;
            this.val$h = r2;
            this.val$drawable = r3;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r1) {
            this.val$drawable.draw(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public int getAlpha() {
            return this.val$drawable.getAlpha();
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return this.val$drawable.getOpacity();
        }

        @Override // android.graphics.drawable.Drawable
        public void onBoundsChange(Rect r4) {
            super.onBoundsChange(r4);
            int r0 = (r4.width() - this.val$w) / 2;
            int r42 = r4.height();
            int r1 = this.val$h;
            int r43 = (r42 - r1) / 2;
            this.val$drawable.setBounds(r0, r43, this.val$w + r0, r1 + r43);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.val$drawable.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    static {
        matrixTmp = new Matrix();
        captureTmpRectF = new RectF();
        captureTmpChildPos = new RectF();
    }

    public static void captureRelativeParent(IBlur3Capture r6, Canvas r7, RectF r8, View r9, ViewGroup r10) {
        captureRelativeParent(r6, r7, r8, r9, r10, 255);
    }

    public static void captureRelativeParent(IBlur3Capture r4, Canvas r5, RectF r6, View r7, ViewGroup r8, int r9) {
        if (r9 <= 0) goto L28;
        if (r4 == null) goto L31;
        RectF r0 = captureTmpChildPos;
        if (ViewPositionWatcher.computeRectInParent(r7, r8, r0) == false) goto L29;
        float r72 = r0.left;
        float r82 = r0.top;
        RectF r02 = captureTmpRectF;
        r02.set(r6);
        r02.offset(-r72, -r82);
        boolean r2 = true;
        if (r72 == 0.0f) goto L10;
    L13:
        boolean r62 = true;
    L15:
        if (r9 != 255) goto L18;
        r2 = false;
    L18:
        if (r62 == false) goto L20;
        r5.save();
        r5.translate(r72, r82);
    L20:
        if (r2 == false) goto L22;
        r5.saveLayerAlpha(r02, r9);
    L22:
        r4.capture(r5, r02);
        if (r2 == false) goto L25;
        r5.restore();
    L25:
        if (r62 == false) goto L30;
        r5.restore();
        return;
    L30:
        return;
    L10:
        if (r82 != 0.0f) goto L13;
        r62 = false;
        goto L15
    L29:
        return;
    L31:
        return;
    }

    public static boolean checkBitmapSourceMatrixScale(BlurredBackgroundSourceBitmap r4, View r5) {
        if (r4 == null) goto L21;
        if (r5 == null) goto L21;
        if (r5.getWidth() == 0) goto L21;
        if (r5.getHeight() == 0) goto L21;
        Bitmap r1 = r4.getBitmap();
        if (r1 == null) goto L21;
        if (r1.isRecycled() == true) goto L21;
        if (r1.getWidth() == 0) goto L21;
        if (r1.getHeight() == 0) goto L21;
        Matrix r0 = matrixTmp;
        r0.reset();
        r0.setScale(r5.getWidth() / r1.getWidth(), r5.getHeight() / r1.getHeight());
        r4.setMatrix(r0);
        return true;
    L21:
        return false;
    }

    public static Drawable wrapCenteredDrawable(Drawable r1, int r2, int r3) {
        return new AnonymousClass1(r2, r3, r1);
    }
}
