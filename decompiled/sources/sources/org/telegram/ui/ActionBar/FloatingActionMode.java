package org.telegram.ui.ActionBar;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.PopupMenu;
import java.util.Arrays;
import org.telegram.messenger.AndroidUtilities;

@TargetApi(23)
/* loaded from: classes6.dex */
public final class FloatingActionMode extends ActionMode {
    private final int mBottomAllowance;
    private final ActionMode.Callback2 mCallback;
    private final Rect mContentRect;
    private final Rect mContentRectOnScreen;
    private final Context mContext;
    private final Point mDisplaySize;
    private FloatingToolbar mFloatingToolbar;
    private FloatingToolbarVisibilityHelper mFloatingToolbarVisibilityHelper;
    private final Runnable mHideOff;
    private final Menu mMenu;
    private final Runnable mMovingOff;
    private final View mOriginatingView;
    private final Rect mPreviousContentRectOnScreen;
    private final int[] mPreviousViewPositionOnScreen;
    private final Rect mPreviousViewRectOnScreen;
    private final int[] mRootViewPositionOnScreen;
    private final Rect mScreenRect;
    private final int[] mViewPositionOnScreen;
    private final Rect mViewRectOnScreen;

    /* renamed from: org.telegram.ui.ActionBar.FloatingActionMode$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ FloatingActionMode this$0;

        public AnonymousClass1(FloatingActionMode r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (FloatingActionMode.m5754$$Nest$misViewStillActive(this.this$0) == false) goto L6;
            FloatingActionMode.m5753$$Nest$fgetmFloatingToolbarVisibilityHelper(this.this$0).setMoving(false);
            FloatingActionMode.m5753$$Nest$fgetmFloatingToolbarVisibilityHelper(this.this$0).updateToolbarVisibility();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.FloatingActionMode$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ FloatingActionMode this$0;

        public AnonymousClass2(FloatingActionMode r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (FloatingActionMode.m5754$$Nest$misViewStillActive(this.this$0) == false) goto L6;
            FloatingActionMode.m5753$$Nest$fgetmFloatingToolbarVisibilityHelper(this.this$0).setHideRequested(false);
            FloatingActionMode.m5753$$Nest$fgetmFloatingToolbarVisibilityHelper(this.this$0).updateToolbarVisibility();
            return;
        }
    }

    public static final class FloatingToolbarVisibilityHelper {
        private boolean mActive;
        private boolean mHideRequested;
        private long mLastShowTime;
        private boolean mMoving;
        private boolean mOutOfBounds;
        private final FloatingToolbar mToolbar;
        private boolean mWindowFocused;

        public FloatingToolbarVisibilityHelper(FloatingToolbar r2) {
            this.mWindowFocused = true;
            this.mToolbar = r2;
        }

        public void activate() {
            this.mHideRequested = false;
            this.mMoving = false;
            this.mOutOfBounds = false;
            this.mWindowFocused = true;
            this.mActive = true;
        }

        public void deactivate() {
            this.mActive = false;
            this.mToolbar.dismiss();
        }

        public void setHideRequested(boolean r1) {
            this.mHideRequested = r1;
        }

        public void setMoving(boolean r5) {
            if ((System.currentTimeMillis() - this.mLastShowTime) <= 500) goto L5;
            boolean r0 = true;
        L6:
            if (r5 == false) goto L10;
            if (r0 == true) goto L10;
            return;
        L10:
            this.mMoving = r5;
            return;
        L5:
            r0 = false;
            goto L6
        }

        public void setOutOfBounds(boolean r1) {
            this.mOutOfBounds = r1;
        }

        public void setWindowFocused(boolean r1) {
            this.mWindowFocused = r1;
        }

        public void updateToolbarVisibility() {
            if (this.mActive == true) goto L6;
            return;
        L6:
            if (this.mHideRequested == false) goto L8;
        L16:
            this.mToolbar.hide();
            return;
        L8:
            if (this.mMoving == true) goto L16;
            if (this.mOutOfBounds == true) goto L16;
            if (this.mWindowFocused == false) goto L16;
            this.mToolbar.show();
            this.mLastShowTime = System.currentTimeMillis();
        }
    }

    public static /* synthetic */ boolean $r8$lambda$5DVWDWxAL4rkyuNKtiHtgbgd3tw(FloatingActionMode r0, MenuItem r1) {
        return r0.lambda$setFloatingToolbar$1(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$ahBV0HnYeoXw1_Xx2AKtTT4wI_g(FloatingActionMode r0, MenuItem r1) {
        return r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetmFloatingToolbarVisibilityHelper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FloatingToolbarVisibilityHelper m5753$$Nest$fgetmFloatingToolbarVisibilityHelper(FloatingActionMode r0) {
        return r0.mFloatingToolbarVisibilityHelper;
    }

    /* renamed from: -$$Nest$misViewStillActive, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5754$$Nest$misViewStillActive(FloatingActionMode r0) {
        return r0.isViewStillActive();
    }

    public FloatingActionMode(Context r2, ActionMode.Callback2 r3, View r4, FloatingToolbar r5) {
        this.mMovingOff = new AnonymousClass1(this);
        this.mHideOff = new AnonymousClass2(this);
        this.mContext = r2;
        this.mCallback = r3;
        PopupMenu r32 = new PopupMenu(r2, null);
        this.mMenu = r32.getMenu();
        setType(1);
        r32.setOnMenuItemClickListener(new FloatingActionMode$$ExternalSyntheticLambda0(this));
        this.mContentRect = new Rect();
        this.mContentRectOnScreen = new Rect();
        this.mPreviousContentRectOnScreen = new Rect();
        int[] r33 = new int[2];
        this.mViewPositionOnScreen = r33;
        this.mPreviousViewPositionOnScreen = new int[2];
        this.mRootViewPositionOnScreen = new int[2];
        this.mViewRectOnScreen = new Rect();
        this.mPreviousViewRectOnScreen = new Rect();
        this.mScreenRect = new Rect();
        this.mOriginatingView = r4;
        r4.getLocationOnScreen(r33);
        this.mBottomAllowance = AndroidUtilities.dp(20.0f);
        this.mDisplaySize = new Point();
        setFloatingToolbar(r5);
    }

    private static boolean intersectsClosed(Rect r2, Rect r3) {
        if (r2.left <= r3.right) goto L5;
        return false;
    L5:
        if (r3.left <= r2.right) goto L7;
        return false;
    L7:
        if (r2.top <= r3.bottom) goto L9;
        return false;
    L9:
        if (r3.top > r2.bottom) goto L16;
        return true;
    L16:
        return false;
    }

    private boolean isContentRectWithinBounds() {
        ((WindowManager) this.mContext.getSystemService(WindowManager.class)).getDefaultDisplay().getRealSize(this.mDisplaySize);
        Rect r0 = this.mScreenRect;
        Point r1 = this.mDisplaySize;
        r0.set(0, 0, r1.x, r1.y);
        if (intersectsClosed(this.mContentRectOnScreen, this.mScreenRect) == true) goto L5;
    L8:
        return false;
    L5:
        if (intersectsClosed(this.mContentRectOnScreen, this.mViewRectOnScreen) == false) goto L8;
        return true;
    }

    private boolean isViewStillActive() {
        if (this.mOriginatingView.getWindowVisibility() == 0) goto L5;
        return false;
    L5:
        if (this.mOriginatingView.isShown() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ boolean lambda$new$0(MenuItem r2) {
        return this.mCallback.onActionItemClicked(this, r2);
    }

    private /* synthetic */ boolean lambda$setFloatingToolbar$1(MenuItem r2) {
        return this.mCallback.onActionItemClicked(this, r2);
    }

    private void repositionToolbar() {
        this.mContentRectOnScreen.set(this.mContentRect);
        ViewParent r0 = this.mOriginatingView.getParent();
        if ((r0 instanceof ViewGroup) == false) goto L5;
        r0.getChildVisibleRect(this.mOriginatingView, this.mContentRectOnScreen, null);
        Rect r02 = this.mContentRectOnScreen;
        int[] r1 = this.mRootViewPositionOnScreen;
        r02.offset(r1[0], r1[1]);
    L6:
        boolean r03 = isContentRectWithinBounds();
        FloatingToolbarVisibilityHelper r12 = this.mFloatingToolbarVisibilityHelper;
        if (r03 == false) goto L11;
        r12.setOutOfBounds(false);
        Rect r04 = this.mContentRectOnScreen;
        r04.set(Math.max(r04.left, this.mViewRectOnScreen.left), Math.max(this.mContentRectOnScreen.top, this.mViewRectOnScreen.top), Math.min(this.mContentRectOnScreen.right, this.mViewRectOnScreen.right), Math.min(this.mContentRectOnScreen.bottom, this.mViewRectOnScreen.bottom + this.mBottomAllowance));
        if (this.mContentRectOnScreen.equals(this.mPreviousContentRectOnScreen) == true) goto L12;
        this.mOriginatingView.removeCallbacks(this.mMovingOff);
        this.mFloatingToolbarVisibilityHelper.setMoving(true);
        this.mOriginatingView.postDelayed(this.mMovingOff, 50);
        this.mFloatingToolbar.setContentRect(this.mContentRectOnScreen);
        this.mFloatingToolbar.updateLayout();
    L12:
        this.mFloatingToolbarVisibilityHelper.updateToolbarVisibility();
        this.mPreviousContentRectOnScreen.set(this.mContentRectOnScreen);
        return;
    L11:
        r12.setOutOfBounds(true);
        this.mContentRectOnScreen.setEmpty();
        goto L12
    L5:
        Rect r05 = this.mContentRectOnScreen;
        int[] r13 = this.mViewPositionOnScreen;
        r05.offset(r13[0], r13[1]);
        goto L6
    }

    private void reset() {
        this.mFloatingToolbar.dismiss();
        this.mFloatingToolbarVisibilityHelper.deactivate();
        this.mOriginatingView.removeCallbacks(this.mMovingOff);
        this.mOriginatingView.removeCallbacks(this.mHideOff);
    }

    private void setFloatingToolbar(FloatingToolbar r2) {
        FloatingToolbar r22 = r2.setMenu(this.mMenu).setOnMenuItemClickListener(new FloatingActionMode$$ExternalSyntheticLambda1(this));
        this.mFloatingToolbar = r22;
        FloatingToolbarVisibilityHelper r0 = new FloatingToolbarVisibilityHelper(r22);
        this.mFloatingToolbarVisibilityHelper = r0;
        r0.activate();
    }

    @Override // android.view.ActionMode
    public void finish() {
        reset();
        this.mCallback.onDestroyActionMode(this);
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return null;
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return this.mMenu;
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return new MenuInflater(this.mContext);
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return null;
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return null;
    }

    @Override // android.view.ActionMode
    public void hide(long r3) {
        if (r3 != (-1)) goto L5;
        r3 = ViewConfiguration.getDefaultActionModeHideDuration();
    L5:
        long r32 = Math.min(3000, r3);
        this.mOriginatingView.removeCallbacks(this.mHideOff);
        if (r32 > 0) goto L9;
        this.mHideOff.run();
        return;
    L9:
        this.mFloatingToolbarVisibilityHelper.setHideRequested(true);
        this.mFloatingToolbarVisibilityHelper.updateToolbarVisibility();
        this.mOriginatingView.postDelayed(this.mHideOff, r32);
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.mCallback.onPrepareActionMode(this, this.mMenu);
        invalidateContentRect();
    }

    @Override // android.view.ActionMode
    public void invalidateContentRect() {
        this.mCallback.onGetContentRect(this, this.mOriginatingView, this.mContentRect);
        Rect r0 = this.mContentRect;
        if (r0.left == 0) goto L5;
    L7:
        repositionToolbar();
        return;
    L5:
        if (r0.right != 0) goto L7;
        r0.left = 1;
        r0.right = 1;
        goto L7
    }

    @Override // android.view.ActionMode
    public void onWindowFocusChanged(boolean r2) {
        this.mFloatingToolbarVisibilityHelper.setWindowFocused(r2);
        this.mFloatingToolbarVisibilityHelper.updateToolbarVisibility();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View r1) {
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int r1) {
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence r1) {
    }

    @Override // android.view.ActionMode
    public void setTitle(int r1) {
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence r1) {
    }

    public void updateViewLocationInWindow() {
        this.mOriginatingView.getLocationOnScreen(this.mViewPositionOnScreen);
        this.mOriginatingView.getRootView().getLocationOnScreen(this.mRootViewPositionOnScreen);
        this.mOriginatingView.getGlobalVisibleRect(this.mViewRectOnScreen);
        Rect r0 = this.mViewRectOnScreen;
        int[] r1 = this.mRootViewPositionOnScreen;
        r0.offset(r1[0], r1[1]);
        if (Arrays.equals(this.mViewPositionOnScreen, this.mPreviousViewPositionOnScreen) == true) goto L5;
    L8:
        repositionToolbar();
        int[] r02 = this.mPreviousViewPositionOnScreen;
        int[] r12 = this.mViewPositionOnScreen;
        r02[0] = r12[0];
        r02[1] = r12[1];
        this.mPreviousViewRectOnScreen.set(this.mViewRectOnScreen);
        return;
    L5:
        if (this.mViewRectOnScreen.equals(this.mPreviousViewRectOnScreen) == false) goto L8;
    }
}
