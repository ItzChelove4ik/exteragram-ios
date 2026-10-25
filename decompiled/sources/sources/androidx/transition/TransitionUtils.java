package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
abstract class TransitionUtils {
    private static final boolean HAS_PICTURE_BITMAP = false;

    public static class Api28Impl {
        public static Bitmap createBitmap(Picture r0) {
            return Bitmap.createBitmap(r0);
        }
    }

    static {
        if (Build.VERSION.SDK_INT < 28) goto L5;
        boolean r0 = true;
    L6:
        HAS_PICTURE_BITMAP = r0;
        return;
    L5:
        r0 = false;
        goto L6
    }

    public static View copyViewImage(ViewGroup r7, View r8, View r9) {
        Matrix r0 = new Matrix();
        r0.setTranslate(-r9.getScrollX(), -r9.getScrollY());
        ViewUtils.transformMatrixToGlobal(r8, r0);
        ViewUtils.transformMatrixToLocal(r7, r0);
        RectF r92 = new RectF(0.0f, 0.0f, r8.getWidth(), r8.getHeight());
        r0.mapRect(r92);
        int r1 = Math.round(r92.left);
        int r2 = Math.round(r92.top);
        int r3 = Math.round(r92.right);
        int r4 = Math.round(r92.bottom);
        ImageView r5 = new ImageView(r8.getContext());
        r5.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Bitmap r72 = createViewBitmap(r8, r0, r92, r7);
        if (r72 == null) goto L5;
        r5.setImageBitmap(r72);
    L5:
        r5.measure(View.MeasureSpec.makeMeasureSpec(r3 - r1, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r4 - r2, TLObject.FLAG_30));
        r5.layout(r1, r2, r3, r4);
        return r5;
    }

    private static Bitmap createViewBitmap(View r7, Matrix r8, RectF r9, ViewGroup r10) {
        boolean r0 = r7.isAttachedToWindow();
        if (r10 != null) goto L5;
    L7:
        boolean r2 = false;
    L8:
        Bitmap r3 = null;
        if (r0 == true) goto L13;
        if (r2 == true) goto L12;
        return null;
    L12:
        ViewGroup r1 = (ViewGroup) r7.getParent();
        int r22 = r1.indexOfChild(r7);
        r10.getOverlay().add(r7);
    L14:
        int r4 = Math.round(r9.width());
        int r5 = Math.round(r9.height());
        if (r4 <= 0) goto L21;
        if (r5 <= 0) goto L21;
        float r32 = Math.min(1.0f, 1048576.0f / (r4 * r5));
        int r42 = Math.round(r4 * r32);
        int r52 = Math.round(r5 * r32);
        r8.postTranslate(-r9.left, -r9.top);
        r8.postScale(r32, r32);
        if (HAS_PICTURE_BITMAP == false) goto L20;
        Picture r92 = new Picture();
        Canvas r33 = r92.beginRecording(r42, r52);
        r33.concat(r8);
        r7.draw(r33);
        r92.endRecording();
        r3 = Api28Impl.createBitmap(r92);
        goto L21
    L20:
        r3 = Bitmap.createBitmap(r42, r52, Bitmap.Config.ARGB_8888);
        Canvas r93 = new Canvas(r3);
        r93.concat(r8);
        r7.draw(r93);
    L21:
        if (r0 == true) goto L23;
        r10.getOverlay().remove(r7);
        r1.addView(r7, r22);
    L23:
        return r3;
    L13:
        r22 = 0;
        r1 = null;
        goto L14
    L5:
        if (r10.isAttachedToWindow() == false) goto L7;
        r2 = true;
        goto L8
    }

    public static Animator mergeAnimators(Animator r1, Animator r2) {
        if (r1 != null) goto L4;
        return r2;
    L4:
        if (r2 != null) goto L6;
        return r1;
    L6:
        AnimatorSet r0 = new AnimatorSet();
        r0.playTogether(new Animator[]{r1, r2});
        return r0;
    }
}
