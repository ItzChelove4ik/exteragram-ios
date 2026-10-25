package androidx.appcompat.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;

/* loaded from: classes3.dex */
class TooltipCompatHandler implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    private static TooltipCompatHandler sActiveHandler;
    private static TooltipCompatHandler sPendingHandler;
    private final View mAnchor;
    private int mAnchorX;
    private int mAnchorY;
    private boolean mForceNextChangeSignificant;
    private boolean mFromTouch;
    private final Runnable mHideRunnable;
    private final int mHoverSlop;
    private TooltipPopup mPopup;
    private final Runnable mShowRunnable;
    private final CharSequence mTooltipText;

    /* renamed from: $r8$lambda$g05a00WJJiYSbj4G6GVn6Bh-ySE, reason: not valid java name */
    public static /* synthetic */ void m2$r8$lambda$g05a00WJJiYSbj4G6GVn6BhySE(TooltipCompatHandler r1) {
        r1.show(false);
    }

    private TooltipCompatHandler(View r2, CharSequence r3) {
        this.mShowRunnable = new TooltipCompatHandler$$ExternalSyntheticLambda0(this);
        this.mHideRunnable = new TooltipCompatHandler$$ExternalSyntheticLambda1(this);
        this.mAnchor = r2;
        this.mTooltipText = r3;
        this.mHoverSlop = ViewConfigurationCompat.getScaledHoverSlop(ViewConfiguration.get(r2.getContext()));
        forceNextChangeSignificant();
        r2.setOnLongClickListener(this);
        r2.setOnHoverListener(this);
    }

    private void cancelPendingShow() {
        this.mAnchor.removeCallbacks(this.mShowRunnable);
    }

    private void forceNextChangeSignificant() {
        this.mForceNextChangeSignificant = true;
    }

    private void scheduleShow() {
        this.mAnchor.postDelayed(this.mShowRunnable, ViewConfiguration.getLongPressTimeout());
    }

    private static void setPendingHandler(TooltipCompatHandler r1) {
        TooltipCompatHandler r0 = sPendingHandler;
        if (r0 == null) goto L5;
        r0.cancelPendingShow();
    L5:
        sPendingHandler = r1;
        if (r1 == null) goto L9;
        r1.scheduleShow();
        return;
    }

    public static void setTooltipText(View r2, CharSequence r3) {
        TooltipCompatHandler r0 = sPendingHandler;
        if (r0 == null) goto L8;
        if (r0.mAnchor != r2) goto L8;
        setPendingHandler(null);
    L8:
        if (TextUtils.isEmpty(r3) == false) goto L16;
        TooltipCompatHandler r32 = sActiveHandler;
        if (r32 != null) goto L12;
    L14:
        r2.setOnLongClickListener(null);
        r2.setLongClickable(false);
        r2.setOnHoverListener(null);
        return;
    L12:
        if (r32.mAnchor != r2) goto L14;
        r32.hide();
        goto L14
    L16:
        new TooltipCompatHandler(r2, r3);
    }

    private boolean updateAnchorPos(MotionEvent r5) {
        int r0 = (int) r5.getX();
        int r52 = (int) r5.getY();
        if (this.mForceNextChangeSignificant == false) goto L5;
    L10:
        this.mAnchorX = r0;
        this.mAnchorY = r52;
        this.mForceNextChangeSignificant = false;
        return true;
    L5:
        if (Math.abs(r0 - this.mAnchorX) > this.mHoverSlop) goto L10;
        if (Math.abs(r52 - this.mAnchorY) > this.mHoverSlop) goto L10;
        return false;
    }

    public void hide() {
        if (sActiveHandler != this) goto L9;
        sActiveHandler = null;
        TooltipPopup r0 = this.mPopup;
        if (r0 == null) goto L7;
        r0.hide();
        this.mPopup = null;
        forceNextChangeSignificant();
        this.mAnchor.removeOnAttachStateChangeListener(this);
        goto L9
    L7:
        Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
    L9:
        if (sPendingHandler != this) goto L11;
        setPendingHandler(null);
    L11:
        this.mAnchor.removeCallbacks(this.mHideRunnable);
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View r3, MotionEvent r4) {
        if (this.mPopup != null) goto L5;
    L7:
        AccessibilityManager r32 = (AccessibilityManager) this.mAnchor.getContext().getSystemService("accessibility");
        if (r32.isEnabled() == true) goto L10;
    L12:
        int r33 = r4.getAction();
        if (r33 == 7) goto L19;
        if (r33 != 10) goto L25;
        forceNextChangeSignificant();
        hide();
    L25:
        return false;
    L19:
        if (this.mAnchor.isEnabled() == false) goto L25;
        if (this.mPopup != null) goto L25;
        if (updateAnchorPos(r4) == false) goto L25;
        setPendingHandler(this);
        goto L25
    L10:
        if (r32.isTouchExplorationEnabled() == false) goto L12;
        return false;
    L5:
        if (this.mFromTouch == false) goto L7;
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View r2) {
        this.mAnchorX = r2.getWidth() / 2;
        this.mAnchorY = r2.getHeight() / 2;
        show(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View r1) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View r1) {
        hide();
    }

    public void show(boolean r8) {
        if (this.mAnchor.isAttachedToWindow() == true) goto L5;
        return;
    L5:
        setPendingHandler(null);
        TooltipCompatHandler r0 = sActiveHandler;
        if (r0 == null) goto L8;
        r0.hide();
    L8:
        sActiveHandler = this;
        this.mFromTouch = r8;
        TooltipPopup r1 = new TooltipPopup(this.mAnchor.getContext());
        this.mPopup = r1;
        r1.show(this.mAnchor, this.mAnchorX, this.mAnchorY, this.mFromTouch, this.mTooltipText);
        this.mAnchor.addOnAttachStateChangeListener(this);
        if (this.mFromTouch == false) goto L12;
        long r02 = 2500;
    L16:
        this.mAnchor.removeCallbacks(this.mHideRunnable);
        this.mAnchor.postDelayed(this.mHideRunnable, r02);
        return;
    L12:
        if ((ViewCompat.getWindowSystemUiVisibility(this.mAnchor) & 1) != 1) goto L15;
        long r03 = ViewConfiguration.getLongPressTimeout();
        long r2 = 3000;
    L14:
        r02 = r2 - r03;
        goto L16
    L15:
        r03 = ViewConfiguration.getLongPressTimeout();
        r2 = 15000;
        goto L14
    }
}
