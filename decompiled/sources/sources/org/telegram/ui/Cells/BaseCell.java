package org.telegram.ui.Cells;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import org.telegram.messenger.FileLog;
import org.telegram.ui.Components.SizeNotifierFrameLayout;

/* loaded from: classes3.dex */
public abstract class BaseCell extends ViewGroup implements SizeNotifierFrameLayout.IViewWithInvalidateCallback {
    private boolean checkingForLongPress;
    protected Runnable invalidateCallback;
    private CheckForLongPress pendingCheckForLongPress;
    private CheckForTap pendingCheckForTap;
    private int pressCount;

    /* loaded from: classes6.dex */
    public class CheckForLongPress implements Runnable {
        public int currentPressCount;
        final /* synthetic */ BaseCell this$0;

        public CheckForLongPress(BaseCell r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BaseCell.m6619$$Nest$fgetcheckingForLongPress(this.this$0) == true) goto L5;
            return;
        L5:
            if (this.this$0.getParent() != null) goto L7;
            return;
        L7:
            if (this.currentPressCount != BaseCell.m6621$$Nest$fgetpressCount(this.this$0)) goto L18;
            BaseCell.m6622$$Nest$fputcheckingForLongPress(this.this$0, false);
            if (this.this$0.onLongPress() == false) goto L19;
            this.this$0.performHapticFeedback(0, 2);     // Catch: Exception -> L13
        L11:
            MotionEvent r0 = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0);
            this.this$0.onTouchEvent(r0);
            r0.recycle();
            return;
        L19:
            return;
        }
    }

    /* loaded from: classes6.dex */
    public final class CheckForTap implements Runnable {
        final /* synthetic */ BaseCell this$0;

        private CheckForTap(BaseCell r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ CheckForTap(BaseCell r1, BaseCellIA r2) {
            this(r1);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BaseCell.m6620$$Nest$fgetpendingCheckForLongPress(this.this$0) != null) goto L5;
            BaseCell r0 = this.this$0;
            BaseCell.m6623$$Nest$fputpendingCheckForLongPress(r0, new CheckForLongPress(r0));
        L5:
            CheckForLongPress r02 = BaseCell.m6620$$Nest$fgetpendingCheckForLongPress(this.this$0);
            BaseCell r1 = this.this$0;
            int r2 = BaseCell.m6621$$Nest$fgetpressCount(r1) + 1;
            BaseCell.m6624$$Nest$fputpressCount(r1, r2);
            r02.currentPressCount = r2;
            BaseCell r3 = this.this$0;
            r3.postDelayed(BaseCell.m6620$$Nest$fgetpendingCheckForLongPress(r3), ViewConfiguration.getLongPressTimeout() - ViewConfiguration.getTapTimeout());
        }
    }

    public static class RippleDrawableSafe extends RippleDrawable {
        public Drawable mask;

        public RippleDrawableSafe(ColorStateList r1, Drawable r2, Drawable r3) {
            super(r1, r2, r3);
            this.mask = r3;
        }

        @Override // android.graphics.drawable.RippleDrawable, android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas r3) {
            int r0 = r3.save();
            super.draw(r3);     // Catch: Throwable -> L6 Exception -> L8
            r3.restoreToCount(r0);
            return;
        L8:
            e = move-exception;
            FileLog.e("probably forgot to put setCallback", e);     // Catch: Throwable -> L6
            r3.restoreToCount(r0);
            return;
        L6:
            th = move-exception;
            r3.restoreToCount(r0);
            throw th;
        }
    }

    /* renamed from: -$$Nest$fgetcheckingForLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6619$$Nest$fgetcheckingForLongPress(BaseCell r0) {
        return r0.checkingForLongPress;
    }

    /* renamed from: -$$Nest$fgetpendingCheckForLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CheckForLongPress m6620$$Nest$fgetpendingCheckForLongPress(BaseCell r0) {
        return r0.pendingCheckForLongPress;
    }

    /* renamed from: -$$Nest$fgetpressCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6621$$Nest$fgetpressCount(BaseCell r0) {
        return r0.pressCount;
    }

    /* renamed from: -$$Nest$fputcheckingForLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6622$$Nest$fputcheckingForLongPress(BaseCell r0, boolean r1) {
        r0.checkingForLongPress = r1;
    }

    /* renamed from: -$$Nest$fputpendingCheckForLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6623$$Nest$fputpendingCheckForLongPress(BaseCell r0, CheckForLongPress r1) {
        r0.pendingCheckForLongPress = r1;
    }

    /* renamed from: -$$Nest$fputpressCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6624$$Nest$fputpressCount(BaseCell r0, int r1) {
        r0.pressCount = r1;
    }

    public BaseCell(Context r2) {
        super(r2);
        this.checkingForLongPress = false;
        this.pendingCheckForLongPress = null;
        this.pressCount = 0;
        this.pendingCheckForTap = null;
        setWillNotDraw(false);
        setFocusable(true);
        setHapticFeedbackEnabled(true);
    }

    public static float setDrawableBounds(Drawable r2, float r3, float r4, float r5) {
        float r0 = (r2.getIntrinsicWidth() * r5) / r2.getIntrinsicHeight();
        setDrawableBounds(r2, (int) r3, (int) r4, (int) r0, (int) r5);
        return r0;
    }

    public static void setDrawableBounds(Drawable r2, float r3, float r4) {
        setDrawableBounds(r2, (int) r3, (int) r4, r2.getIntrinsicWidth(), r2.getIntrinsicHeight());
    }

    public static void setDrawableBounds(Drawable r0, float r1, float r2, int r3, int r4) {
        if (r0 == null) goto L5;
        int r12 = (int) r1;
        int r22 = (int) r2;
        r0.setBounds(r12, r22, r3 + r12, r4 + r22);
        return;
    }

    public static void setDrawableBounds(Drawable r2, int r3, int r4) {
        setDrawableBounds(r2, r3, r4, r2.getIntrinsicWidth(), r2.getIntrinsicHeight());
    }

    public static void setDrawableBounds(Drawable r0, int r1, int r2, int r3, int r4) {
        if (r0 == null) goto L5;
        r0.setBounds(r1, r2, r3 + r1, r4 + r2);
        return;
    }

    public void cancelCheckLongPress() {
        this.checkingForLongPress = false;
        CheckForLongPress r0 = this.pendingCheckForLongPress;
        if (r0 == null) goto L5;
        removeCallbacks(r0);
    L5:
        CheckForTap r02 = this.pendingCheckForTap;
        if (r02 == null) goto L9;
        removeCallbacks(r02);
        return;
    }

    public int getBoundsLeft() {
        return 0;
    }

    public int getBoundsRight() {
        return getWidth();
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public void invalidate() {
        Runnable r0 = this.invalidateCallback;
        if (r0 == null) goto L5;
        r0.run();
    L5:
        super.invalidate();
    }

    public void invalidateLite() {
        super.invalidate();
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout.IViewWithInvalidateCallback
    public void listenInvalidate(Runnable r1) {
        this.invalidateCallback = r1;
    }

    public boolean onLongPress() {
        return true;
    }

    public void startCheckLongPress() {
        if (this.checkingForLongPress == false) goto L5;
        return;
    L5:
        this.checkingForLongPress = true;
        if (this.pendingCheckForTap != null) goto L8;
        this.pendingCheckForTap = new CheckForTap(this, null);
    L8:
        postDelayed(this.pendingCheckForTap, ViewConfiguration.getTapTimeout());
    }
}
