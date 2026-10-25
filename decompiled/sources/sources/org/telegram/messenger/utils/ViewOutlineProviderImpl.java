package org.telegram.messenger.utils;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.View;
import android.view.ViewOutlineProvider;
import org.telegram.messenger.Utilities;

/* loaded from: classes.dex */
public abstract class ViewOutlineProviderImpl {
    public static final ViewOutlineProvider BOUNDS_OVAL = null;
    public static final ViewOutlineProvider BOUNDS_ROUND_RECT = null;
    private static Outline drawClipOutline;
    private static Path drawClipPath;
    private static Rect drawClipRect;

    /* renamed from: org.telegram.messenger.utils.ViewOutlineProviderImpl$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewOutlineProvider {
        public AnonymousClass1() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r2, Outline r3) {
            r3.setOval(0, 0, r2.getWidth(), r2.getHeight());
        }
    }

    /* renamed from: org.telegram.messenger.utils.ViewOutlineProviderImpl$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewOutlineProvider {
        public AnonymousClass2() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getWidth(), r7.getHeight(), Math.min(r7.getWidth(), r7.getHeight()) / 2.0f);
        }
    }

    /* renamed from: org.telegram.messenger.utils.ViewOutlineProviderImpl$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends ViewOutlineProvider {
        final /* synthetic */ Drawable val$drawable;

        public AnonymousClass3(Drawable r1) {
            this.val$drawable = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r1, Outline r2) {
            this.val$drawable.getOutline(r2);
        }
    }

    /* renamed from: org.telegram.messenger.utils.ViewOutlineProviderImpl$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends ViewOutlineProvider {
        final /* synthetic */ float val$radius;

        public AnonymousClass4(float r1) {
            this.val$radius = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(r7.getPaddingLeft(), r7.getPaddingTop(), r7.getMeasuredWidth() - r7.getPaddingRight(), r7.getMeasuredHeight() - r7.getPaddingBottom(), this.val$radius);
        }
    }

    /* renamed from: org.telegram.messenger.utils.ViewOutlineProviderImpl$5, reason: invalid class name */
    public class AnonymousClass5 extends ViewOutlineProvider {
        final /* synthetic */ int val$padding;
        final /* synthetic */ float val$radius;

        public AnonymousClass5(int r1, float r2) {
            this.val$padding = r1;
            this.val$radius = r2;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            int r1 = this.val$padding;
            r8.setRoundRect(r1, r1, r7.getWidth() - this.val$padding, r7.getHeight() - this.val$padding, this.val$radius);
        }
    }

    static {
        BOUNDS_OVAL = new AnonymousClass1();
        BOUNDS_ROUND_RECT = new AnonymousClass2();
    }

    public static ViewOutlineProvider boundsWithPaddingFromViewAndRoundRect(float r1) {
        return new AnonymousClass4(r1);
    }

    public static ViewOutlineProvider boundsWithPaddingRoundRect(int r1, float r2) {
        return new AnonymousClass5(r1, r2);
    }

    public static ViewOutlineProvider boundsWithRoundRect(float r1) {
        return boundsWithPaddingRoundRect(0, r1);
    }

    public static void drawViewWithOutline(Canvas r4, View r5, Utilities.Callback<Canvas> r6) {
        if (r4 == null) goto L26;
        if (r5 == null) goto L27;
        ViewOutlineProvider r0 = r5.getOutlineProvider();
        if (r4.isHardwareAccelerated() == false) goto L8;
    L24:
        r6.run(r4);
        return;
    L8:
        if (r5.getClipToOutline() == false) goto L24;
        if (r0 == null) goto L24;
        if (Looper.myLooper() == Looper.getMainLooper()) goto L14;
        Path r1 = new Path();
        Outline r2 = new Outline();
        Rect r3 = new Rect();
    L18:
        r0.getOutline(r5, r2);
        if (outlineToPath(r2, r3, r1) == true) goto L22;
        r6.run(r4);
        return;
    L22:
        int r52 = r4.save();
        r4.clipPath(r1);
        r6.run(r4);
        r4.restoreToCount(r52);
        return;
    L14:
        if (drawClipPath != null) goto L16;
        drawClipPath = new Path();
        drawClipOutline = new Outline();
        drawClipRect = new Rect();
    L16:
        r1 = drawClipPath;
        r2 = drawClipOutline;
        r3 = drawClipRect;
        r2.setEmpty();
        r3.setEmpty();
        goto L18
    L27:
        return;
    }

    public static ViewOutlineProvider fromDrawable(Drawable r1) {
        return new AnonymousClass3(r1);
    }

    private static boolean outlineToPath(Outline r16, Rect r17, Path r18) {
        r18.rewind();
        if (r16.isEmpty() == false) goto L5;
        return false;
    L5:
        if (r16.getRect(r17) == false) goto L16;
        float r7 = r16.getRadius();
        if (r7 <= 0.0f) goto L10;
        r18.addRoundRect(r17.left, r17.top, r17.right, r17.bottom, r7, r7, Path.Direction.CW);
        return true;
    L10:
        r18.addRect(r17.left, r17.top, r17.right, r17.bottom, Path.Direction.CW);
        return true;
    L16:
        return false;
    }
}
