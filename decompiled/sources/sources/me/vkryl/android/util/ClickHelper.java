package me.vkryl.android.util;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import me.vkryl.android.ViewUtils;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public class ClickHelper {
    private int bottom;
    private final Delegate delegate;
    private int flags;
    private int left;
    private Runnable longPressCallback;
    private float longPressX;
    private float longPressY;
    private boolean regionSet;
    private int right;
    private float startX;
    private float startY;
    private int top;

    public interface Delegate {
        default boolean forceEnableVibration() {
            return false;
        }

        default long getLongPressDuration() {
            return ViewConfiguration.getLongPressTimeout();
        }

        default boolean ignoreHapticFeedbackSettings(float r1, float r2) {
            return false;
        }

        default boolean needCancelTouchBySlopMove() {
            return true;
        }

        boolean needClickAt(View r1, float r2, float r3);

        default boolean needLongPress(float r1, float r2) {
            return false;
        }

        void onClickAt(View r1, float r2, float r3);

        default void onClickTouchDown(View r1, float r2, float r3) {
        }

        default void onClickTouchMove(View r1, float r2, float r3) {
        }

        default void onClickTouchUp(View r1, float r2, float r3) {
        }

        default void onLongPressCancelled(View r1, float r2, float r3) {
        }

        default void onLongPressFinish(View r1, float r2, float r3) {
        }

        default void onLongPressMove(View r1, MotionEvent r2, float r3, float r4, float r5, float r6) {
        }

        default boolean onLongPressRequestedAt(View r1, float r2, float r3) {
            return false;
        }
    }

    public static /* synthetic */ void $r8$lambda$4nvdFSrNpI0KdeT7QNcUBYuCYok(ClickHelper r0, View r1) {
        r0.lambda$scheduleLongPress$0(r1);
    }

    public ClickHelper(Delegate r1) {
        this.delegate = r1;
    }

    private /* synthetic */ void lambda$scheduleLongPress$0(View r4) {
        if ((this.flags & 2) == 0) goto L10;
        boolean r0 = this.delegate.onLongPressRequestedAt(r4, this.startX, this.startY);
        int r1 = this.flags;
        if (r0 == false) goto L8;
        this.flags = r1 & (-3);
        this.longPressCallback = null;
        onLongPress(r4, this.startX, this.startY);
        return;
    L8:
        this.flags = r1 | 8;
        return;
    }

    private void resetTouch(View r3, float r4, float r5) {
        int r0 = this.flags;
        if ((r0 & 2) == 0) goto L9;
        this.flags = r0 & (-3);
        Runnable r02 = this.longPressCallback;
        if (r02 == null) goto L7;
        r3.removeCallbacks(r02);
        this.longPressCallback = null;
        goto L9
    L7:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return;
    L9:
        int r03 = this.flags;
        if ((r03 & 8) == 0) goto L13;
        this.flags = r03 & (-9);
        this.delegate.onLongPressCancelled(r3, r4, r5);
    L13:
        if ((this.flags & 4) == 0) goto L16;
        this.delegate.onLongPressFinish(r3, r4, r5);
        this.flags &= -5;
    L16:
        if ((this.flags & 1) == 0) goto L19;
        this.delegate.onClickTouchUp(r3, r4, r5);
        this.flags &= -2;
        return;
    }

    private void scheduleLongPress(View r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (this.longPressCallback != null) goto L7;
        this.flags |= 2;
        ClickHelper$$ExternalSyntheticLambda0 r0 = new ClickHelper$$ExternalSyntheticLambda0(this, r4);
        this.longPressCallback = r0;
        r4.postDelayed(r0, this.delegate.getLongPressDuration());
        return;
    L7:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
    }

    public void cancel(View r1, float r2, float r3) {
        resetTouch(r1, r2, r3);
    }

    public final void onLongPress(View r2, float r3, float r4) {
        this.longPressX = r3;
        this.longPressY = r4;
        if (this.delegate.ignoreHapticFeedbackSettings(r3, r4) == false) goto L5;
        ViewUtils.hapticVibrate(r2, true, this.delegate.forceEnableVibration());
    L6:
        this.flags = (this.flags | 4) & (-11);
        this.longPressCallback = null;
        return;
    L5:
        r2.performHapticFeedback(0);
        goto L6
    }

    public boolean onTouchEvent(View r9, MotionEvent r10) {
        float r3 = r10.getX();
        float r4 = r10.getY();
        int r0 = r10.getAction();
        if (r0 == 0) goto L39;
        if (r0 != 1) goto L6;
        int r102 = this.flags;
        if ((r102 & 1) == 0) goto L36;
        int r103 = r102 & 4;
        Delegate r02 = this.delegate;
        if (r103 == 0) goto L30;
        r02.onLongPressFinish(r9, r3, r4);
        this.flags &= -5;
    L33:
        resetTouch(r9, r3, r4);
        return true;
    L30:
        r02.onClickAt(r9, r3, r4);
        if ((this.flags & 256) != 0) goto L33;
        ViewUtils.onClick(r9);
    L36:
        if ((this.flags & 1) == 0) goto L38;
        return true;
    L38:
        return false;
    L6:
        if (r0 == 2) goto L15;
        if (r0 != 3) goto L36;
        if ((this.flags & 1) == 0) goto L36;
        resetTouch(r9, r3, r4);
        return true;
    L15:
        if ((this.flags & 1) == 0) goto L36;
        this.delegate.onClickTouchMove(r9, r3, r4);
        int r03 = this.flags & 4;
        Delegate r04 = this.delegate;
        if (r03 == 0) goto L20;
        r04.onLongPressMove(r9, r10, r3, r4, this.longPressX, this.longPressY);
    L24:
        return true;
    L20:
        if (r04.needCancelTouchBySlopMove() == false) goto L24;
        if (Math.max(Math.abs(this.startX - r3), Math.abs(this.startY - r4)) <= (ViewConfiguration.get(r9.getContext()).getScaledTouchSlop() * 1.89f)) goto L24;
        resetTouch(r9, r3, r4);
        goto L24
    L39:
        resetTouch(r9, r3, r4);
        if (this.regionSet == false) goto L51;
        if (r3 >= this.left) goto L44;
    L49:
        return false;
    L44:
        if (r3 > this.right) goto L49;
        if (r4 < this.top) goto L49;
        if (r4 > this.bottom) goto L49;
    L51:
        if (this.delegate.needClickAt(r9, r3, r4) == true) goto L53;
        return false;
    L53:
        this.flags |= 1;
        this.startX = r3;
        this.startY = r4;
        this.delegate.onClickTouchDown(r9, r3, r4);
        if (this.delegate.needLongPress(r3, r4) == false) goto L56;
        scheduleLongPress(r9);
    L56:
        return true;
    }
}
