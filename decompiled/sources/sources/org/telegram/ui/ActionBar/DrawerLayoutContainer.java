package org.telegram.ui.ActionBar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.exteragram.messenger.drawer.DrawerContainer;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;

/* loaded from: classes3.dex */
public class DrawerLayoutContainer extends FrameLayout {
    private ActionBarLayout actionBarLayout;
    private boolean drawCurrentPreviewFragmentAbove;
    private DrawerContainer drawerContainer;
    private boolean inLayout;
    private final Paint internalNavbarPaint;
    private WindowInsetsCompat lastWindowInsetsCompat;
    private INavigationLayout parentActionBarLayout;
    private BitmapDrawable previewBlurDrawable;
    private float previewStartY;
    private Insets systemAndCutoutAndImeInsets;
    private Insets systemAndCutoutInsets;

    /* renamed from: $r8$lambda$-jdFrN6-r0YpYN-Cl9WMyEJWCUk, reason: not valid java name */
    public static /* synthetic */ WindowInsetsCompat m5748$r8$lambda$jdFrN6r0YpYNCl9WMyEJWCUk(DrawerLayoutContainer r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    public DrawerLayoutContainer(Context r2) {
        super(r2);
        this.internalNavbarPaint = new Paint(1);
        Insets r22 = Insets.NONE;
        this.systemAndCutoutInsets = r22;
        this.systemAndCutoutAndImeInsets = r22;
        ViewCompat.setOnApplyWindowInsetsListener(this, new DrawerLayoutContainer$$ExternalSyntheticLambda0(this));
        setSystemUiVisibility(1280);
    }

    private void createBlurDrawable() {
        int r0 = getMeasuredWidth();
        int r1 = getMeasuredHeight();
        int r2 = (int) (r0 / 6.0f);
        int r3 = (int) (r1 / 6.0f);
        Bitmap r4 = Bitmap.createBitmap(r2, r3, Bitmap.Config.ARGB_8888);
        Canvas r5 = new Canvas(r4);
        r5.scale(0.16666667f, 0.16666667f);
        super.dispatchDraw(r5);
        Utilities.stackBlurBitmap(r4, Math.max(7, Math.max(r2, r3) / 180));
        BitmapDrawable r22 = new BitmapDrawable(r4);
        this.previewBlurDrawable = r22;
        r22.setBounds(0, 0, r0, r1);
    }

    private void dispatchApplyWindowInsetsInternal(View r1, WindowInsetsCompat r2) {
        if ((r1 instanceof ActionBarLayout) == false) goto L5;
    L8:
        ViewCompat.dispatchApplyWindowInsets(r1, r2);
        return;
    L5:
        if (r1.getTag() == null) goto L8;
    }

    private WindowInsetsCompat onApplyWindowInsets(View r4, WindowInsetsCompat r5) {
        this.lastWindowInsetsCompat = r5;
        int r42 = 0;
        Insets r0 = AndroidUtilities.getDefaultWindowInsets(r5, false);
        Insets r1 = AndroidUtilities.getDefaultWindowInsets(r5, true);
        if (this.systemAndCutoutInsets.equals(r0) == true) goto L5;
    L6:
        AndroidUtilities.statusBarHeight = r0.top;
        AndroidUtilities.navigationBarHeight = r0.bottom;
        this.systemAndCutoutInsets = r0;
        this.systemAndCutoutAndImeInsets = r1;
        requestLayout();
    L7:
        int r02 = getChildCount();
    L8:
        if (r42 >= r02) goto L10;
        dispatchApplyWindowInsetsInternal(getChildAt(r42), r5);
        r42 = r42 + 1;
        goto L8
    L10:
        invalidate();
        return WindowInsetsCompat.CONSUMED;
    L5:
        if (this.systemAndCutoutAndImeInsets.equals(r1) == true) goto L7;
        goto L6
    }

    @Override // android.view.ViewGroup
    public void addView(View r1, int r2, ViewGroup.LayoutParams r3) {
        super.addView(r1, r2, r3);
        WindowInsetsCompat r22 = this.lastWindowInsetsCompat;
        if (r22 == null) goto L6;
        dispatchApplyWindowInsetsInternal(r1, r22);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r4) {
        ActionBarLayout r0 = this.actionBarLayout;
        if (r0 != null) goto L5;
    L7:
        super.dispatchDraw(r4);
        if (this.drawCurrentPreviewFragmentAbove == false) goto L16;
        INavigationLayout r02 = this.parentActionBarLayout;
        if (r02 == null) goto L17;
        BitmapDrawable r1 = this.previewBlurDrawable;
        if (r1 == null) goto L14;
        r1.setAlpha((int) (r02.getCurrentPreviewFragmentAlpha() * 255.0f));
        this.previewBlurDrawable.draw(r4);
    L14:
        this.parentActionBarLayout.drawCurrentPreviewFragment(r4, null);
        return;
    L17:
        return;
    L16:
        return;
    L5:
        if (r0.getParent() != this) goto L7;
        this.actionBarLayout.parentDraw(this, r4);
        goto L7
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r12) {
        if (this.drawCurrentPreviewFragmentAbove == false) goto L20;
        if (this.parentActionBarLayout == null) goto L20;
        int r0 = r12.getActionMasked();
        if (r0 != 2) goto L12;
        float r02 = this.previewStartY;
        if (r02 != 0.0f) goto L11;
        this.previewStartY = r12.getY();
        MotionEvent r122 = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0);
        super.dispatchTouchEvent(r122);
        r122.recycle();
    L18:
        return true;
    L11:
        this.parentActionBarLayout.movePreviewFragment(r02 - r12.getY());
        goto L18
    L12:
        if (r0 != 1) goto L14;
    L17:
        this.parentActionBarLayout.finishPreviewFragment();
        goto L18
    L14:
        if (r0 == 6) goto L17;
        if (r0 != 3) goto L18;
    L20:
        return super.dispatchTouchEvent(r12);
    }

    public DrawerContainer getDrawerContainer() {
        return this.drawerContainer;
    }

    public Paint getInternalNavbarPaint() {
        return this.internalNavbarPaint;
    }

    public INavigationLayout getParentActionBarLayout() {
        return this.parentActionBarLayout;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return this.drawCurrentPreviewFragmentAbove;
    }

    public boolean isDrawCurrentPreviewFragmentAbove() {
        return this.drawCurrentPreviewFragmentAbove;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r4) {
        DrawerContainer r0 = this.drawerContainer;
        if (r0 != null) goto L5;
    L7:
        DrawerContainer r02 = this.drawerContainer;
        if (r02 != null) goto L10;
    L12:
        INavigationLayout r42 = this.parentActionBarLayout;
        if (r42 != null) goto L15;
    L19:
        return false;
    L15:
        if (r42.checkTransitionAnimation() == false) goto L19;
        if (this.parentActionBarLayout.isIncomingFragmentTouchable() == true) goto L19;
        return true;
    L10:
        if (r02.handleEdgeSwipeIntercept(r4) == false) goto L12;
        return true;
    L5:
        if (r0.getVisibility() != 0) goto L7;
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        this.inLayout = true;
        int r52 = getChildCount();
        int r72 = 0;
    L3:
        if (r72 >= r52) goto L15;
        View r82 = getChildAt(r72);
        if (r82.getVisibility() == 8) goto L13;
        FrameLayout.LayoutParams r92 = (FrameLayout.LayoutParams) r82.getLayoutParams();
        r82.layout(r92.leftMargin, r92.topMargin + getPaddingTop(), r92.leftMargin + r82.getMeasuredWidth(), (r92.topMargin + r82.getMeasuredHeight()) + getPaddingTop());     // Catch: Exception -> L10
    L10:
        e = move-exception;
        FileLog.e(e);
        if (BuildVars.DEBUG_VERSION == false) goto L13;
        throw e;
    L13:
        r72 = r72 + 1;
        goto L3
    L15:
        this.inLayout = false;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r8, int r9) {
        int r82 = View.MeasureSpec.getSize(r8);
        int r92 = View.MeasureSpec.getSize(r9);
        setMeasuredDimension(r82, r92);
        Insets r0 = this.systemAndCutoutInsets;
        int r1 = (r82 - r0.left) - r0.right;
        int r2 = (r92 - r0.top) - r0.bottom;
        Point r02 = AndroidUtilities.displaySize;
        r02.x = r1;
        r02.y = r2;
        int r03 = getChildCount();
        int r12 = 0;
    L3:
        if (r12 >= r03) goto L18;
        View r22 = getChildAt(r12);
        if (r22.getVisibility() == 8) goto L17;
        FrameLayout.LayoutParams r3 = (FrameLayout.LayoutParams) r22.getLayoutParams();
        int r4 = View.MeasureSpec.makeMeasureSpec((r82 - r3.leftMargin) - r3.rightMargin, TLObject.FLAG_30);
        int r6 = r3.height;
        if (r6 <= 0) goto L10;
        int r32 = View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30);
    L12:
        if ((r22 instanceof ActionBarLayout) == true) goto L14;
    L16:
        r22.measure(r4, r32);
        goto L17
    L14:
        if (((ActionBarLayout) r22).storyViewerAttached() == false) goto L16;
        r22.forceLayout();
        goto L16
    L10:
        r32 = View.MeasureSpec.makeMeasureSpec((r92 - r3.topMargin) - r3.bottomMargin, TLObject.FLAG_30);
    L17:
        r12 = r12 + 1;
        goto L3
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r1) {
        DrawerContainer r0 = this.drawerContainer;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.handleEdgeSwipeTouch(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.inLayout == true) goto L6;
        super.requestLayout();
        return;
    }

    public void setActionBarLayout(ActionBarLayout r1) {
        this.actionBarLayout = r1;
    }

    public void setDrawCurrentPreviewFragmentAbove(boolean r2) {
        if (this.drawCurrentPreviewFragmentAbove == r2) goto L10;
        this.drawCurrentPreviewFragmentAbove = r2;
        if (r2 == false) goto L7;
        createBlurDrawable();
    L8:
        invalidate();
        return;
    L7:
        this.previewStartY = 0.0f;
        this.previewBlurDrawable = null;
        goto L8
    }

    public void setDrawerContainer(DrawerContainer r3) {
        DrawerContainer r0 = this.drawerContainer;
        if (r0 == r3) goto L12;
        if (r0 == null) goto L7;
        r0.dispose();
        removeView(this.drawerContainer);
    L7:
        this.drawerContainer = r3;
        if (r3 == null) goto L11;
        addView(r3, new FrameLayout.LayoutParams(-1, -1));
        return;
    L11:
        return;
    }

    public void setInternalNavigationBarColor(int r3) {
        if (this.internalNavbarPaint.getColor() == r3) goto L7;
        this.internalNavbarPaint.setColor(r3);
        invalidate();
        int r32 = getChildCount();
        int r0 = 0;
    L5:
        if (r0 >= r32) goto L9;
        getChildAt(r0).invalidate();
        r0 = r0 + 1;
        goto L5
    L9:
        return;
    }

    public void setParentActionBarLayout(INavigationLayout r1) {
        this.parentActionBarLayout = r1;
    }
}
