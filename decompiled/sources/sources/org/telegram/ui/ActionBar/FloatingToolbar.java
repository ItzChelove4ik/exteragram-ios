package org.telegram.ui.ActionBar;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.Size;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.GradientProtectionDrawable;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;

/* loaded from: classes6.dex */
public final class FloatingToolbar {
    private static final MenuItem.OnMenuItemClickListener NO_OP_MENUITEM_CLICK_LISTENER = null;
    public static final List<Integer> STYLE_BUTTONS = null;
    public static final List<Integer> premiumOptions = null;
    BlurredBackgroundDrawableViewFactory blurredBackgroundDrawableViewFactory;
    private int currentStyle;
    private final Rect mContentRect;
    private Menu mMenu;
    private MenuItem.OnMenuItemClickListener mMenuItemClickListener;
    private final Comparator<MenuItem> mMenuItemComparator;
    private final View.OnLayoutChangeListener mOrientationChangeHandler;
    private final FloatingToolbarPopup mPopup;
    private final Rect mPreviousContentRect;
    private List<MenuItem> mShowingMenuItems;
    private int mSuggestedWidth;
    private boolean mWidthChanged;
    private final View mWindowView;
    private Runnable premiumLockClickListener;
    private Utilities.Callback0Return<Boolean> quoteShowCallback;
    private final Theme.ResourcesProvider resourcesProvider;

    /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnLayoutChangeListener {
        private final Rect mNewRect;
        private final Rect mOldRect;
        final /* synthetic */ FloatingToolbar this$0;

        public AnonymousClass1(FloatingToolbar r1) {
            this.this$0 = r1;
            this.mNewRect = new Rect();
            this.mOldRect = new Rect();
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
            this.mNewRect.set(r2, r3, r4, r5);
            this.mOldRect.set(r6, r7, r8, r9);
            if (FloatingToolbar.m5757$$Nest$fgetmPopup(this.this$0).isShowing() == true) goto L5;
            return;
        L5:
            if (this.mNewRect.equals(this.mOldRect) == true) goto L9;
            FloatingToolbar.m5759$$Nest$fputmWidthChanged(this.this$0, true);
            this.this$0.updateLayout();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayout {
        private View downRootView;
        private final int[] p;

        public AnonymousClass2(Context r1) {
            super(r1);
            this.p = new int[2];
            this.downRootView = null;
        }

        private boolean isParent(View r4, View r5) {
            if (r4 != r5) goto L6;
            return true;
        L6:
            if (r4.getParent() != null) goto L9;
            return false;
        L9:
            if ((r4.getParent() instanceof View) == false) goto L13;
            return isParent((View) r4.getParent(), r5);
        L13:
            if (r4.getParent() != r5) goto L16;
            return true;
        L16:
            if (r4.getRootView() != r5) goto L18;
            return true;
        L18:
            return false;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r8) {
            boolean r0 = super.dispatchTouchEvent(r8);
            if (r0 == true) goto L25;
            getLocationOnScreen(this.p);
            int[] r2 = this.p;
            r8.offsetLocation(r2[0], r2[1]);
            if (r8.getAction() != 0) goto L21;
            List<View> r22 = AndroidUtilities.allGlobalViews();
            if (r22 == null) goto L25;
            if (r22.size() <= 1) goto L25;
            int r4 = r22.size() - 2;
        L11:
            if (r4 < 0) goto L25;
            View r5 = r22.get(r4);
            if (isParent(this, r5) == true) goto L20;
            r5.getLocationOnScreen(this.p);
            int[] r02 = this.p;
            r8.offsetLocation(-r02[0], -r02[1]);
            r0 = r5.dispatchTouchEvent(r8);
            if (r0 == true) goto L17;
            int[] r52 = this.p;
            r8.offsetLocation(r52[0], r52[1]);
            goto L20
        L17:
            this.downRootView = r5;
            return true;
        L20:
            r4 = r4 - 1;
            goto L11
        L21:
            View r23 = this.downRootView;
            if (r23 == null) goto L25;
            r23.getLocationOnScreen(this.p);
            int[] r03 = this.p;
            r8.offsetLocation(-r03[0], -r03[1]);
            r0 = r23.dispatchTouchEvent(r8);
        L25:
            if (r8.getAction() != 1) goto L27;
        L30:
            this.downRootView = null;
            return r0;
        L27:
            if (r8.getAction() == 3) goto L30;
            return r0;
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r1) {
            return super.onInterceptTouchEvent(r1);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r1) {
            return super.onTouchEvent(r1);
        }
    }

    public final class FloatingToolbarPopup {
        private final Drawable mArrow;
        private final AnimationSet mCloseOverflowAnimation;
        private final ViewGroup mContentContainer;
        private final Context mContext;
        private final Point mCoordsOnWindow;
        private final AnimatorSet mDismissAnimation;
        private boolean mDismissed;
        private final Interpolator mFastOutLinearInInterpolator;
        private final Interpolator mFastOutSlowInInterpolator;
        private boolean mHidden;
        private final AnimatorSet mHideAnimation;
        private final int mIconTextSpacing;
        private boolean mIsOverflowOpen;
        private int mLineHeight;
        private final Interpolator mLinearOutSlowInInterpolator;
        private final Interpolator mLogAccelerateInterpolator;
        private final LinearLayout mMainPanel;
        private LinearLayout mMainPanelButtons;
        private Size mMainPanelSize;
        private final int mMarginHorizontal;
        private final int mMarginVertical;
        private final View.OnClickListener mMenuItemButtonOnClickListener;
        private MenuItem.OnMenuItemClickListener mOnMenuItemClickListener;
        private final AnimationSet mOpenOverflowAnimation;
        private boolean mOpenOverflowUpwards;
        private final Drawable mOverflow;
        private final FrameLayout mOverflowButton;
        private final ImageView mOverflowButtonIcon;
        private final View mOverflowButtonShadow;
        private final Size mOverflowButtonSize;
        private final TextView mOverflowButtonText;
        private final OverflowPanel mOverflowPanel;
        private Size mOverflowPanelSize;
        private final OverflowPanelViewHelper mOverflowPanelViewHelper;
        private final View mParent;
        private final PopupWindow mPopupWindow;
        private final Runnable mPreparePopupContentRTLHelper;
        private final AnimatorSet mShowAnimation;
        private final int[] mTmpCoords;
        private final AnimatedVectorDrawable mToArrow;
        private final AnimatedVectorDrawable mToOverflow;
        private final Region mTouchableRegion;
        private int mTransitionDurationScale;
        private final Rect mViewPortOnScreen;
        private int shiftDp;
        final /* synthetic */ FloatingToolbar this$0;

        /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$FloatingToolbarPopup$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ FloatingToolbarPopup this$1;

            public AnonymousClass1(FloatingToolbarPopup r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                FloatingToolbarPopup.m5787$$Nest$msetPanelsStatesAtRestingPosition(this.this$1);
                FloatingToolbarPopup.m5785$$Nest$msetContentAreaAsTouchableSurface(this.this$1);
                FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(this.this$1).setAlpha(1.0f);
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$FloatingToolbarPopup$10, reason: invalid class name */
        public class AnonymousClass10 extends Animation {
            final /* synthetic */ FloatingToolbarPopup this$1;
            final /* synthetic */ float val$bottom;
            final /* synthetic */ int val$startHeight;
            final /* synthetic */ int val$targetHeight;

            public AnonymousClass10(FloatingToolbarPopup r1, int r2, int r3, float r4) {
                this.this$1 = r1;
                this.val$targetHeight = r2;
                this.val$startHeight = r3;
                this.val$bottom = r4;
            }

            @Override // android.view.animation.Animation
            public void applyTransformation(float r3, Transformation r4) {
                FloatingToolbarPopup r42 = this.this$1;
                FloatingToolbarPopup.m5786$$Nest$msetHeight(r42, FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(r42), this.val$startHeight + ((int) (r3 * (this.val$targetHeight - this.val$startHeight))));
                if (FloatingToolbarPopup.m5773$$Nest$fgetmOpenOverflowUpwards(this.this$1) == false) goto L6;
                FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(this.this$1).setY(this.val$bottom - FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(this.this$1).getHeight());
                FloatingToolbarPopup.m5784$$Nest$mpositionContentYCoordinatesIfOpeningOverflowUpwards(this.this$1);
                return;
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$FloatingToolbarPopup$11, reason: invalid class name */
        public class AnonymousClass11 extends Animation {
            final /* synthetic */ FloatingToolbarPopup this$1;
            final /* synthetic */ float val$overflowButtonStartX;
            final /* synthetic */ float val$overflowButtonTargetX;
            final /* synthetic */ int val$startWidth;

            public AnonymousClass11(FloatingToolbarPopup r1, float r2, float r3, int r4) {
                this.this$1 = r1;
                this.val$overflowButtonStartX = r2;
                this.val$overflowButtonTargetX = r3;
                this.val$startWidth = r4;
            }

            @Override // android.view.animation.Animation
            public void applyTransformation(float r3, Transformation r4) {
                float r42 = this.val$overflowButtonStartX;
                float r43 = r42 + ((this.val$overflowButtonTargetX - r42) * r3);
                if (FloatingToolbarPopup.m5782$$Nest$misInRTLMode(this.this$1) == false) goto L5;
                float r0 = 0.0f;
            L6:
                FloatingToolbarPopup.m5774$$Nest$fgetmOverflowButton(this.this$1).setX(r43 + r0);
                float r02 = 1.0f - r3;
                FloatingToolbarPopup.m5777$$Nest$fgetmOverflowButtonText(this.this$1).setAlpha(r02);
                FloatingToolbarPopup.m5775$$Nest$fgetmOverflowButtonShadow(this.this$1).setAlpha(r02);
                return;
            L5:
                r0 = FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(this.this$1).getWidth() - this.val$startWidth;
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$FloatingToolbarPopup$12, reason: invalid class name */
        public class AnonymousClass12 extends LinearLayout {
            final /* synthetic */ FloatingToolbarPopup this$1;

            public AnonymousClass12(FloatingToolbarPopup r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.ViewGroup
            public boolean onInterceptTouchEvent(MotionEvent r1) {
                return FloatingToolbarPopup.m5783$$Nest$misOverflowAnimating(this.this$1);
            }

            @Override // android.widget.LinearLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                if (FloatingToolbarPopup.m5783$$Nest$misOverflowAnimating(this.this$1) == true) goto L5;
            L7:
                super.onMeasure(r2, r3);
                return;
            L5:
                if (FloatingToolbarPopup.m5771$$Nest$fgetmMainPanelSize(this.this$1) == null) goto L7;
                r2 = View.MeasureSpec.makeMeasureSpec(FloatingToolbarPopup.m5771$$Nest$fgetmMainPanelSize(this.this$1).getWidth(), TLObject.FLAG_30);
                goto L7
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$FloatingToolbarPopup$13, reason: invalid class name */
        public class AnonymousClass13 extends ArrayAdapter<MenuItem> {
            final /* synthetic */ FloatingToolbarPopup this$1;

            public AnonymousClass13(FloatingToolbarPopup r1, Context r2, int r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int r1, View r2, ViewGroup r3) {
                return FloatingToolbarPopup.m5780$$Nest$fgetmOverflowPanelViewHelper(this.this$1).getView((MenuItem) getItem(r1), FloatingToolbarPopup.m5779$$Nest$fgetmOverflowPanelSize(this.this$1).getWidth(), r2);
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$FloatingToolbarPopup$14, reason: invalid class name */
        public class AnonymousClass14 implements Animation.AnimationListener {
            final /* synthetic */ FloatingToolbarPopup this$1;

            /* renamed from: $r8$lambda$DHXi-Wc8mG6YQgHaSgOBroUJDxw, reason: not valid java name */
            public static /* synthetic */ void m5789$r8$lambda$DHXiWc8mG6YQgHaSgOBroUJDxw(AnonymousClass14 r0) {
                r0.lambda$onAnimationEnd$0();
            }

            public AnonymousClass14(FloatingToolbarPopup r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$onAnimationEnd$0() {
                FloatingToolbarPopup.m5787$$Nest$msetPanelsStatesAtRestingPosition(this.this$1);
                FloatingToolbarPopup.m5785$$Nest$msetContentAreaAsTouchableSurface(this.this$1);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation r2) {
                FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(this.this$1).post(new FloatingToolbar$FloatingToolbarPopup$14$$ExternalSyntheticLambda0(this));
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation r1) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation r2) {
                FloatingToolbarPopup.m5774$$Nest$fgetmOverflowButton(this.this$1).setEnabled(false);
                FloatingToolbarPopup.m5770$$Nest$fgetmMainPanel(this.this$1).setVisibility(0);
                FloatingToolbarPopup.m5778$$Nest$fgetmOverflowPanel(this.this$1).setVisibility(0);
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$FloatingToolbarPopup$2, reason: invalid class name */
        public class AnonymousClass2 implements View.OnClickListener {
            final /* synthetic */ FloatingToolbarPopup this$1;

            public AnonymousClass2(FloatingToolbarPopup r1) {
                this.this$1 = r1;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View r2) {
                if ((r2.getTag() instanceof MenuItem) == true) goto L5;
                return;
            L5:
                if (FloatingToolbarPopup.m5772$$Nest$fgetmOnMenuItemClickListener(this.this$1) == null) goto L9;
                FloatingToolbarPopup.m5772$$Nest$fgetmOnMenuItemClickListener(this.this$1).onMenuItemClick((MenuItem) r2.getTag());
                return;
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$FloatingToolbarPopup$3, reason: invalid class name */
        public class AnonymousClass3 extends ImageButton {
            final /* synthetic */ FloatingToolbarPopup this$1;
            final /* synthetic */ FloatingToolbar val$this$0;

            public AnonymousClass3(FloatingToolbarPopup r1, Context r2, FloatingToolbar r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.view.View
            public boolean dispatchTouchEvent(MotionEvent r2) {
                if (FloatingToolbarPopup.m5769$$Nest$fgetmIsOverflowOpen(this.this$1) == false) goto L7;
                return false;
            L7:
                return super.dispatchTouchEvent(r2);
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$FloatingToolbarPopup$4, reason: invalid class name */
        public class AnonymousClass4 extends AnimatorListenerAdapter {
            final /* synthetic */ FloatingToolbarPopup this$1;
            final /* synthetic */ FloatingToolbar val$this$0;

            public static /* synthetic */ void $r8$lambda$_6qDMusUJOMwo43XZaWrPjgsMsI(AnonymousClass4 r0) {
                r0.lambda$onAnimationEnd$0();
            }

            public AnonymousClass4(FloatingToolbarPopup r1, FloatingToolbar r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            private /* synthetic */ void lambda$onAnimationEnd$0() {
                FloatingToolbarPopup.m5781$$Nest$fgetmPopupWindow(this.this$1).dismiss();
                FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(this.this$1).removeAllViews();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                NotificationCenter.getInstance(UserConfig.selectedAccount).doOnIdle(new FloatingToolbar$FloatingToolbarPopup$4$$ExternalSyntheticLambda0(this));
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$FloatingToolbarPopup$5, reason: invalid class name */
        public class AnonymousClass5 extends AnimatorListenerAdapter {
            final /* synthetic */ FloatingToolbarPopup this$1;
            final /* synthetic */ FloatingToolbar val$this$0;

            /* renamed from: $r8$lambda$tH2IASon3CCTAE-TSqL2J9MpWcg, reason: not valid java name */
            public static /* synthetic */ void m5790$r8$lambda$tH2IASon3CCTAETSqL2J9MpWcg(AnonymousClass5 r0) {
                r0.lambda$onAnimationEnd$0();
            }

            public AnonymousClass5(FloatingToolbarPopup r1, FloatingToolbar r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            private /* synthetic */ void lambda$onAnimationEnd$0() {
                FloatingToolbarPopup.m5781$$Nest$fgetmPopupWindow(this.this$1).dismiss();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                NotificationCenter.getInstance(UserConfig.selectedAccount).doOnIdle(new FloatingToolbar$FloatingToolbarPopup$5$$ExternalSyntheticLambda0(this));
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$FloatingToolbarPopup$6, reason: invalid class name */
        public class AnonymousClass6 extends Animation {
            final /* synthetic */ FloatingToolbarPopup this$1;
            final /* synthetic */ float val$left;
            final /* synthetic */ float val$right;
            final /* synthetic */ int val$startWidth;
            final /* synthetic */ int val$targetWidth;

            public AnonymousClass6(FloatingToolbarPopup r1, int r2, int r3, float r4, float r5) {
                this.this$1 = r1;
                this.val$targetWidth = r2;
                this.val$startWidth = r3;
                this.val$left = r4;
                this.val$right = r5;
            }

            @Override // android.view.animation.Animation
            public void applyTransformation(float r3, Transformation r4) {
                FloatingToolbarPopup r42 = this.this$1;
                FloatingToolbarPopup.m5788$$Nest$msetWidth(r42, FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(r42), this.val$startWidth + ((int) (r3 * (this.val$targetWidth - this.val$startWidth))));
                boolean r32 = FloatingToolbarPopup.m5782$$Nest$misInRTLMode(this.this$1);
                FloatingToolbarPopup r43 = this.this$1;
                if (r32 == false) goto L6;
                FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(r43).setX(this.val$left);
                FloatingToolbarPopup.m5770$$Nest$fgetmMainPanel(this.this$1).setX(0.0f);
                FloatingToolbarPopup.m5778$$Nest$fgetmOverflowPanel(this.this$1).setX(0.0f);
                return;
            L6:
                FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(r43).setX(this.val$right - FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(this.this$1).getWidth());
                FloatingToolbarPopup.m5770$$Nest$fgetmMainPanel(this.this$1).setX(FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(this.this$1).getWidth() - this.val$startWidth);
                FloatingToolbarPopup.m5778$$Nest$fgetmOverflowPanel(this.this$1).setX(FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(this.this$1).getWidth() - this.val$targetWidth);
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$FloatingToolbarPopup$7, reason: invalid class name */
        public class AnonymousClass7 extends Animation {
            final /* synthetic */ FloatingToolbarPopup this$1;
            final /* synthetic */ int val$startHeight;
            final /* synthetic */ float val$startY;
            final /* synthetic */ int val$targetHeight;

            public AnonymousClass7(FloatingToolbarPopup r1, int r2, int r3, float r4) {
                this.this$1 = r1;
                this.val$targetHeight = r2;
                this.val$startHeight = r3;
                this.val$startY = r4;
            }

            @Override // android.view.animation.Animation
            public void applyTransformation(float r3, Transformation r4) {
                FloatingToolbarPopup r42 = this.this$1;
                FloatingToolbarPopup.m5786$$Nest$msetHeight(r42, FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(r42), this.val$startHeight + ((int) (r3 * (this.val$targetHeight - this.val$startHeight))));
                if (FloatingToolbarPopup.m5773$$Nest$fgetmOpenOverflowUpwards(this.this$1) == false) goto L6;
                FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(this.this$1).setY(this.val$startY - (FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(this.this$1).getHeight() - this.val$startHeight));
                FloatingToolbarPopup.m5784$$Nest$mpositionContentYCoordinatesIfOpeningOverflowUpwards(this.this$1);
                return;
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$FloatingToolbarPopup$8, reason: invalid class name */
        public class AnonymousClass8 extends Animation {
            final /* synthetic */ FloatingToolbarPopup this$1;
            final /* synthetic */ float val$overflowButtonStartX;
            final /* synthetic */ float val$overflowButtonTargetX;
            final /* synthetic */ int val$startWidth;

            public AnonymousClass8(FloatingToolbarPopup r1, float r2, float r3, int r4) {
                this.this$1 = r1;
                this.val$overflowButtonStartX = r2;
                this.val$overflowButtonTargetX = r3;
                this.val$startWidth = r4;
            }

            @Override // android.view.animation.Animation
            public void applyTransformation(float r3, Transformation r4) {
                float r42 = this.val$overflowButtonStartX;
                float r43 = r42 + ((this.val$overflowButtonTargetX - r42) * r3);
                if (FloatingToolbarPopup.m5782$$Nest$misInRTLMode(this.this$1) == false) goto L5;
                float r0 = 0.0f;
            L6:
                FloatingToolbarPopup.m5774$$Nest$fgetmOverflowButton(this.this$1).setX(r43 + r0);
                FloatingToolbarPopup.m5777$$Nest$fgetmOverflowButtonText(this.this$1).setAlpha(r3);
                FloatingToolbarPopup.m5775$$Nest$fgetmOverflowButtonShadow(this.this$1).setAlpha(r3);
                return;
            L5:
                r0 = FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(this.this$1).getWidth() - this.val$startWidth;
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.FloatingToolbar$FloatingToolbarPopup$9, reason: invalid class name */
        public class AnonymousClass9 extends Animation {
            final /* synthetic */ FloatingToolbarPopup this$1;
            final /* synthetic */ float val$left;
            final /* synthetic */ float val$right;
            final /* synthetic */ int val$startWidth;
            final /* synthetic */ int val$targetWidth;

            public AnonymousClass9(FloatingToolbarPopup r1, int r2, int r3, float r4, float r5) {
                this.this$1 = r1;
                this.val$targetWidth = r2;
                this.val$startWidth = r3;
                this.val$left = r4;
                this.val$right = r5;
            }

            @Override // android.view.animation.Animation
            public void applyTransformation(float r3, Transformation r4) {
                FloatingToolbarPopup r42 = this.this$1;
                FloatingToolbarPopup.m5788$$Nest$msetWidth(r42, FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(r42), this.val$startWidth + ((int) (r3 * (this.val$targetWidth - this.val$startWidth))));
                boolean r32 = FloatingToolbarPopup.m5782$$Nest$misInRTLMode(this.this$1);
                FloatingToolbarPopup r43 = this.this$1;
                if (r32 == false) goto L6;
                FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(r43).setX(this.val$left);
                FloatingToolbarPopup.m5770$$Nest$fgetmMainPanel(this.this$1).setX(0.0f);
                FloatingToolbarPopup.m5778$$Nest$fgetmOverflowPanel(this.this$1).setX(0.0f);
                return;
            L6:
                FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(r43).setX(this.val$right - FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(this.this$1).getWidth());
                FloatingToolbarPopup.m5770$$Nest$fgetmMainPanel(this.this$1).setX(FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(this.this$1).getWidth() - this.val$targetWidth);
                FloatingToolbarPopup.m5778$$Nest$fgetmOverflowPanel(this.this$1).setX(FloatingToolbarPopup.m5767$$Nest$fgetmContentContainer(this.this$1).getWidth() - this.val$startWidth);
            }
        }

        public final class LogAccelerateInterpolator implements Interpolator {
            private final int BASE;
            private final float LOGS_SCALE;
            final /* synthetic */ FloatingToolbarPopup this$1;

            private LogAccelerateInterpolator(FloatingToolbarPopup r2) {
                this.this$1 = r2;
                this.BASE = 100;
                this.LOGS_SCALE = 1.0f / computeLog(1.0f, 100);
            }

            public /* synthetic */ LogAccelerateInterpolator(FloatingToolbarPopup r1, FloatingToolbarIA r2) {
                this(r1);
            }

            private float computeLog(float r3, int r4) {
                return (float) (1.0d - Math.pow(r4, -r3));
            }

            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float r3) {
                return 1.0f - (computeLog(1.0f - r3, 100) * this.LOGS_SCALE);
            }
        }

        public final class OverflowPanel extends ListView {
            private final int fadeH;
            private final FloatingToolbarPopup mPopup;
            private final Matrix matrix;
            private final Paint paintBottom;
            private final Paint paintTop;
            private final Shader shaderBottom;
            private final Shader shaderTop;
            final /* synthetic */ FloatingToolbarPopup this$1;

            public OverflowPanel(FloatingToolbarPopup r20, FloatingToolbarPopup r21) {
                this.this$1 = r20;
                super(FloatingToolbarPopup.m5768$$Nest$fgetmContext(r21));
                this.fadeH = 16;
                float r4 = AndroidUtilities.dp(16.0f);
                int[] r7 = generateColors(-16777216);
                Shader.TileMode r18 = Shader.TileMode.CLAMP;
                LinearGradient r2 = new LinearGradient(0.0f, r4, 0.0f, 0.0f, r7, null, r18);
                this.shaderTop = r2;
                LinearGradient r11 = new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(16.0f), generateColors(-16777216), null, r18);
                this.shaderBottom = r11;
                Paint r1 = new Paint(1);
                this.paintTop = r1;
                Paint r42 = new Paint(1);
                this.paintBottom = r42;
                this.matrix = new Matrix();
                r1.setShader(r2);
                PorterDuff.Mode r3 = PorterDuff.Mode.DST_IN;
                r1.setXfermode(new PorterDuffXfermode(r3));
                r42.setShader(r11);
                r42.setXfermode(new PorterDuffXfermode(r3));
                this.mPopup = r21;
                setVerticalScrollBarEnabled(false);
            }

            private int[] generateColors(int r2) {
                int[] r1 = new int[8];
                GradientProtectionDrawable.fillColors(GradientProtectionDrawable.DEFAULT_INTERPOLATOR, r2, r1);
                return r1;
            }

            @Override // android.view.View
            public boolean awakenScrollBars() {
                return super.awakenScrollBars();
            }

            @Override // android.view.ViewGroup, android.view.View
            public boolean dispatchTouchEvent(MotionEvent r2) {
                if (FloatingToolbarPopup.m5783$$Nest$misOverflowAnimating(this.mPopup) == false) goto L7;
                return true;
            L7:
                return super.dispatchTouchEvent(r2);
            }

            @Override // android.widget.ListView, android.view.ViewGroup
            public boolean drawChild(Canvas r17, View r18, long r19) {
                float r3 = r18.getY();
                float r5 = r3 + r18.getHeight();
                if (r3 >= AndroidUtilities.dp(16.0f)) goto L5;
                boolean r8 = true;
            L7:
                if (r5 <= (getHeight() - AndroidUtilities.dp(16.0f))) goto L9;
                boolean r9 = true;
            L10:
                if (r8 == true) goto L15;
                if (r9 == true) goto L15;
                return super.drawChild(r17, r18, r19);
            L15:
                r17.saveLayer(0.0f, r3, getWidth(), r5, null);
                boolean r1 = super.drawChild(r17, r18, r19);
                if (r8 == false) goto L18;
                r17.drawRect(0.0f, 0.0f, getWidth(), AndroidUtilities.dp(16.0f), this.paintTop);
            L18:
                if (r9 == false) goto L20;
                r17.drawRect(0.0f, getHeight() - AndroidUtilities.dp(16.0f), getWidth(), getHeight(), this.paintBottom);
            L20:
                r17.restore();
                return r1;
            L9:
                r9 = false;
                goto L10
            L5:
                r8 = false;
                goto L7
            }

            @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(FloatingToolbarPopup.m5779$$Nest$fgetmOverflowPanelSize(this.mPopup).getHeight() - FloatingToolbarPopup.m5776$$Nest$fgetmOverflowButtonSize(this.mPopup).getHeight(), TLObject.FLAG_30));
            }

            @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
            public void onSizeChanged(int r1, int r2, int r3, int r4) {
                this.matrix.reset();
                this.matrix.postTranslate(0.0f, r2 - AndroidUtilities.dp(16.0f));
                this.shaderBottom.setLocalMatrix(this.matrix);
            }
        }

        public final class OverflowPanelViewHelper {
            private final View mCalculator;
            private final Context mContext;
            private final int mIconTextSpacing;
            private final int mSidePadding;
            final /* synthetic */ FloatingToolbarPopup this$1;

            public OverflowPanelViewHelper(FloatingToolbarPopup r1, Context r2, int r3) {
                this.this$1 = r1;
                this.mContext = r2;
                this.mIconTextSpacing = r3;
                this.mSidePadding = AndroidUtilities.dp(18.0f);
                this.mCalculator = createMenuButton(null);
            }

            private View createMenuButton(MenuItem r9) {
                View r92 = FloatingToolbar.m5761$$Nest$mcreateMenuItemButton(this.this$1.this$0, this.mContext, r9, this.mIconTextSpacing, true, false, false);
                int r8 = this.mSidePadding;
                r92.setPadding(r8, 0, r8, 0);
                return r92;
            }

            public int calculateWidth(MenuItem r5) {
                View r0 = this.mCalculator;
                int r1 = this.mIconTextSpacing;
                if (FloatingToolbar.m5758$$Nest$fgetpremiumLockClickListener(this.this$1.this$0) == null) goto L5;
                boolean r2 = true;
            L6:
                FloatingToolbar.m5766$$Nest$smupdateMenuItemButton(r0, r5, r1, r2);
                this.mCalculator.measure(0, 0);
                return this.mCalculator.getMeasuredWidth();
            L5:
                r2 = false;
                goto L6
            }

            public View getView(MenuItem r2, int r3, View r4) {
                if (r4 == null) goto L8;
                int r0 = this.mIconTextSpacing;
                if (FloatingToolbar.m5758$$Nest$fgetpremiumLockClickListener(this.this$1.this$0) == null) goto L6;
                boolean r1 = true;
            L7:
                FloatingToolbar.m5766$$Nest$smupdateMenuItemButton(r4, r2, r0, r1);
            L9:
                r4.setMinimumWidth(r3);
                return r4;
            L6:
                r1 = false;
                goto L7
            L8:
                r4 = createMenuButton(r2);
                goto L9
            }
        }

        public static /* synthetic */ void $r8$lambda$OKvL1GcUdreP_Ra9deBuXHtqhdc(FloatingToolbarPopup r0, View r1) {
            r0.lambda$updateOverflowButtonClickListener$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$T2htIlTrNKn_V3oloJcOB7aEfXQ(FloatingToolbarPopup r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ int $r8$lambda$XSUKhezwtykLqi5URcVn9bL2ahs(MenuItem r1, MenuItem r2) {
            List<Integer> r0 = FloatingToolbar.premiumOptions;
            boolean r12 = r0.contains(Integer.valueOf(r1.getItemId()));
            boolean r22 = r0.contains(Integer.valueOf(r2.getItemId()));
            return (r12 ? 1 : 0) - (r22 ? 1 : 0);
        }

        public static /* synthetic */ void $r8$lambda$hfSUL1gg9ZAfwPrM5lejZhOKjHg(FloatingToolbarPopup r0, OverflowPanel r1, AdapterView r2, View r3, int r4, long r5) {
            r0.lambda$createOverflowPanel$4(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$nja5h9yT9r3ozrrkfynVRtNdQh0(FloatingToolbarPopup r0, View r1) {
            r0.lambda$updateOverflowButtonClickListener$2(r1);
        }

        /* renamed from: -$$Nest$fgetmContentContainer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ViewGroup m5767$$Nest$fgetmContentContainer(FloatingToolbarPopup r0) {
            return r0.mContentContainer;
        }

        /* renamed from: -$$Nest$fgetmContext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Context m5768$$Nest$fgetmContext(FloatingToolbarPopup r0) {
            return r0.mContext;
        }

        /* renamed from: -$$Nest$fgetmIsOverflowOpen, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m5769$$Nest$fgetmIsOverflowOpen(FloatingToolbarPopup r0) {
            return r0.mIsOverflowOpen;
        }

        /* renamed from: -$$Nest$fgetmMainPanel, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LinearLayout m5770$$Nest$fgetmMainPanel(FloatingToolbarPopup r0) {
            return r0.mMainPanel;
        }

        /* renamed from: -$$Nest$fgetmMainPanelSize, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Size m5771$$Nest$fgetmMainPanelSize(FloatingToolbarPopup r0) {
            return r0.mMainPanelSize;
        }

        /* renamed from: -$$Nest$fgetmOnMenuItemClickListener, reason: not valid java name */
        public static /* bridge */ /* synthetic */ MenuItem.OnMenuItemClickListener m5772$$Nest$fgetmOnMenuItemClickListener(FloatingToolbarPopup r0) {
            return r0.mOnMenuItemClickListener;
        }

        /* renamed from: -$$Nest$fgetmOpenOverflowUpwards, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m5773$$Nest$fgetmOpenOverflowUpwards(FloatingToolbarPopup r0) {
            return r0.mOpenOverflowUpwards;
        }

        /* renamed from: -$$Nest$fgetmOverflowButton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m5774$$Nest$fgetmOverflowButton(FloatingToolbarPopup r0) {
            return r0.mOverflowButton;
        }

        /* renamed from: -$$Nest$fgetmOverflowButtonShadow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m5775$$Nest$fgetmOverflowButtonShadow(FloatingToolbarPopup r0) {
            return r0.mOverflowButtonShadow;
        }

        /* renamed from: -$$Nest$fgetmOverflowButtonSize, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Size m5776$$Nest$fgetmOverflowButtonSize(FloatingToolbarPopup r0) {
            return r0.mOverflowButtonSize;
        }

        /* renamed from: -$$Nest$fgetmOverflowButtonText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m5777$$Nest$fgetmOverflowButtonText(FloatingToolbarPopup r0) {
            return r0.mOverflowButtonText;
        }

        /* renamed from: -$$Nest$fgetmOverflowPanel, reason: not valid java name */
        public static /* bridge */ /* synthetic */ OverflowPanel m5778$$Nest$fgetmOverflowPanel(FloatingToolbarPopup r0) {
            return r0.mOverflowPanel;
        }

        /* renamed from: -$$Nest$fgetmOverflowPanelSize, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Size m5779$$Nest$fgetmOverflowPanelSize(FloatingToolbarPopup r0) {
            return r0.mOverflowPanelSize;
        }

        /* renamed from: -$$Nest$fgetmOverflowPanelViewHelper, reason: not valid java name */
        public static /* bridge */ /* synthetic */ OverflowPanelViewHelper m5780$$Nest$fgetmOverflowPanelViewHelper(FloatingToolbarPopup r0) {
            return r0.mOverflowPanelViewHelper;
        }

        /* renamed from: -$$Nest$fgetmPopupWindow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ PopupWindow m5781$$Nest$fgetmPopupWindow(FloatingToolbarPopup r0) {
            return r0.mPopupWindow;
        }

        /* renamed from: -$$Nest$misInRTLMode, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m5782$$Nest$misInRTLMode(FloatingToolbarPopup r0) {
            return r0.isInRTLMode();
        }

        /* renamed from: -$$Nest$misOverflowAnimating, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m5783$$Nest$misOverflowAnimating(FloatingToolbarPopup r0) {
            return r0.isOverflowAnimating();
        }

        /* renamed from: -$$Nest$mpositionContentYCoordinatesIfOpeningOverflowUpwards, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5784$$Nest$mpositionContentYCoordinatesIfOpeningOverflowUpwards(FloatingToolbarPopup r0) {
            r0.positionContentYCoordinatesIfOpeningOverflowUpwards();
        }

        /* renamed from: -$$Nest$msetContentAreaAsTouchableSurface, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5785$$Nest$msetContentAreaAsTouchableSurface(FloatingToolbarPopup r0) {
            r0.setContentAreaAsTouchableSurface();
        }

        /* renamed from: -$$Nest$msetHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5786$$Nest$msetHeight(FloatingToolbarPopup r0, View r1, int r2) {
            r0.setHeight(r1, r2);
        }

        /* renamed from: -$$Nest$msetPanelsStatesAtRestingPosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5787$$Nest$msetPanelsStatesAtRestingPosition(FloatingToolbarPopup r0) {
            r0.setPanelsStatesAtRestingPosition();
        }

        /* renamed from: -$$Nest$msetWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5788$$Nest$msetWidth(FloatingToolbarPopup r0, View r1, int r2) {
            r0.setWidth(r1, r2);
        }

        public FloatingToolbarPopup(FloatingToolbar r26, Context r27, View r28) {
            this.this$0 = r26;
            this.mViewPortOnScreen = new Rect();
            this.mCoordsOnWindow = new Point();
            this.mTmpCoords = new int[2];
            this.mTouchableRegion = new Region();
            this.mPreparePopupContentRTLHelper = new AnonymousClass1(this);
            this.mDismissed = true;
            this.mMenuItemButtonOnClickListener = new AnonymousClass2(this);
            this.shiftDp = -4;
            this.mParent = r28;
            this.mContext = r27;
            ViewGroup r5 = FloatingToolbar.m5760$$Nest$mcreateContentContainer(r26, r27);
            this.mContentContainer = r5;
            this.mPopupWindow = FloatingToolbar.m5765$$Nest$smcreatePopupWindow(r5);
            this.mMarginHorizontal = AndroidUtilities.dp(16.0f);
            this.mMarginVertical = AndroidUtilities.dp(8.0f);
            this.mLineHeight = AndroidUtilities.dp(48.0f);
            int r7 = AndroidUtilities.dp(8.0f);
            this.mIconTextSpacing = r7;
            this.mLogAccelerateInterpolator = new LogAccelerateInterpolator(this, null);
            this.mFastOutSlowInInterpolator = AnimationUtils.loadInterpolator(r27, R.interpolator.fast_out_slow_in);
            this.mLinearOutSlowInInterpolator = AnimationUtils.loadInterpolator(r27, R.interpolator.linear_out_slow_in);
            this.mFastOutLinearInInterpolator = AnimationUtils.loadInterpolator(r27, R.interpolator.fast_out_linear_in);
            Drawable r9 = r27.getDrawable(org.telegram.messenger.R.drawable.ft_avd_tooverflow).mutate();
            this.mArrow = r9;
            r9.setAutoMirrored(true);
            Drawable r10 = r27.getDrawable(org.telegram.messenger.R.drawable.ft_avd_toarrow).mutate();
            this.mOverflow = r10;
            r10.setAutoMirrored(true);
            AnimatedVectorDrawable r11 = (AnimatedVectorDrawable) r27.getDrawable(org.telegram.messenger.R.drawable.ft_avd_toarrow_animation).mutate();
            this.mToArrow = r11;
            r11.setAutoMirrored(true);
            AnimatedVectorDrawable r12 = (AnimatedVectorDrawable) r27.getDrawable(org.telegram.messenger.R.drawable.ft_avd_tooverflow_animation).mutate();
            this.mToOverflow = r12;
            r12.setAutoMirrored(true);
            FrameLayout r13 = new FrameLayout(r27);
            this.mOverflowButton = r13;
            AnonymousClass3 r14 = new AnonymousClass3(this, r27, r26);
            this.mOverflowButtonIcon = r14;
            r14.setLayoutParams(new ViewGroup.LayoutParams(AndroidUtilities.dp(56.0f), AndroidUtilities.dp(48.0f)));
            r14.setPaddingRelative(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(12.0f));
            r14.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            r14.setImageDrawable(r10);
            TextView r3 = new TextView(r27);
            this.mOverflowButtonText = r3;
            r3.setText(LocaleController.getString(org.telegram.messenger.R.string.Back));
            r3.setTextSize(1, 16.0f);
            r3.setAlpha(0.0f);
            View r4 = new View(r27);
            this.mOverflowButtonShadow = r4;
            if (FloatingToolbar.m5756$$Nest$fgetcurrentStyle(r26) != 0) goto L5;
            int r6 = Theme.key_dialogTextBlack;
            int r8 = FloatingToolbar.m5762$$Nest$mgetThemedColor(r26, r6);
            int r15 = Theme.key_listSelector;
            ViewGroup r17 = r5;
            r14.setBackground(Theme.createSelectorDrawable(FloatingToolbar.m5762$$Nest$mgetThemedColor(r26, r15), 1));
            r13.setBackground(Theme.createSelectorDrawable(FloatingToolbar.m5762$$Nest$mgetThemedColor(r26, r15), 2));
            r4.setBackgroundColor(Theme.multAlpha(FloatingToolbar.m5762$$Nest$mgetThemedColor(r26, r6), 0.4f));
            int r82 = r8;
        L9:
            r10.setTint(r82);
            r9.setTint(r82);
            r11.setTint(r82);
            r12.setTint(r82);
            r3.setTextColor(r82);
            r14.setOnClickListener(new FloatingToolbar$FloatingToolbarPopup$$ExternalSyntheticLambda3(this));
            r13.addView(r14, LayoutHelper.createFrame(-2, -2, 19));
            r13.addView(r3, LayoutHelper.createFrame(-1, -2.0f, 19, 56.0f, 0.0f, 0.0f, 0.0f));
            r13.addView(r4, LayoutHelper.createFrame(-1.0f, 1.0f / AndroidUtilities.density, 55));
            this.mOverflowButtonSize = measure(r14);
            this.mMainPanel = createMainPanel();
            this.mOverflowPanelViewHelper = new OverflowPanelViewHelper(this, r27, r7);
            this.mOverflowPanel = createOverflowPanel();
            Animation.AnimationListener r2 = createOverflowAnimationListener();
            AnimationSet r32 = new AnimationSet(true);
            this.mOpenOverflowAnimation = r32;
            r32.setAnimationListener(r2);
            AnimationSet r33 = new AnimationSet(true);
            this.mCloseOverflowAnimation = r33;
            r33.setAnimationListener(r2);
            this.mShowAnimation = FloatingToolbar.m5763$$Nest$smcreateEnterAnimation(r17);
            ViewGroup r42 = r17;
            this.mDismissAnimation = FloatingToolbar.m5764$$Nest$smcreateExitAnimation(r42, 150, new AnonymousClass4(this, r26));
            this.mHideAnimation = FloatingToolbar.m5764$$Nest$smcreateExitAnimation(r42, 0, new AnonymousClass5(this, r26));
            return;
        L5:
            r17 = r5;
            if (FloatingToolbar.m5756$$Nest$fgetcurrentStyle(r26) != 2) goto L8;
            r14.setBackground(Theme.createSelectorDrawable(553648127, 1));
            r13.setBackground(Theme.createSelectorDrawable(553648127, 2));
            r4.setBackgroundColor(553648127);
            r82 = -328966;
            goto L9
        L8:
            r82 = FloatingToolbar.m5762$$Nest$mgetThemedColor(r26, Theme.key_windowBackgroundWhiteBlackText);
            int r52 = Theme.key_listSelector;
            r14.setBackground(Theme.createSelectorDrawable(FloatingToolbar.m5762$$Nest$mgetThemedColor(r26, r52), 1));
            r13.setBackground(Theme.createSelectorDrawable(FloatingToolbar.m5762$$Nest$mgetThemedColor(r26, r52), 2));
            r4.setBackgroundColor(FloatingToolbar.m5762$$Nest$mgetThemedColor(r26, Theme.key_divider));
            goto L9
        }

        private int calculateOverflowHeight(int r3) {
            int r32 = Math.min(4, Math.min(Math.max(2, r3), this.mOverflowPanel.getCount()));
            if (r32 >= this.mOverflowPanel.getCount()) goto L5;
            int r0 = (int) (this.mLineHeight * 0.5f);
        L7:
            return ((r32 * this.mLineHeight) + this.mOverflowButtonSize.getHeight()) + r0;
        L5:
            r0 = 0;
            goto L7
        }

        private void cancelDismissAndHideAnimations() {
            this.mDismissAnimation.cancel();
            this.mHideAnimation.cancel();
        }

        private void cancelOverflowAnimations() {
            this.mContentContainer.clearAnimation();
            this.mMainPanel.animate().cancel();
            this.mOverflowPanel.animate().cancel();
            this.mToArrow.stop();
            this.mToOverflow.stop();
        }

        private void clearPanels() {
            this.mOverflowPanelSize = null;
            this.mMainPanelSize = null;
            this.mIsOverflowOpen = false;
            updateOverflowButtonClickListener();
            this.mMainPanel.removeAllViews();
            this.mMainPanel.setPaddingRelative(0, 0, 0, 0);
            ArrayAdapter r0 = (ArrayAdapter) this.mOverflowPanel.getAdapter();
            r0.clear();
            this.mOverflowPanel.setAdapter(r0);
            this.mContentContainer.removeAllViews();
        }

        private void closeOverflow() {
            int r3 = this.mMainPanelSize.getWidth();
            int r4 = this.mContentContainer.getWidth();
            float r5 = this.mContentContainer.getX();
            AnonymousClass9 r1 = new AnonymousClass9(this, r3, r4, r5, r5 + this.mContentContainer.getWidth());
            AnonymousClass10 r52 = new AnonymousClass10(this, this.mMainPanelSize.getHeight(), this.mContentContainer.getHeight(), this.mContentContainer.getY() + this.mContentContainer.getHeight());
            float r8 = this.mOverflowButton.getX();
            boolean r0 = isInRTLMode();
            ImageView r32 = this.mOverflowButtonIcon;
            if (r0 == false) goto L5;
            float r02 = (r8 - r4) + r32.getWidth();
        L6:
            AnonymousClass11 r33 = new AnonymousClass11(this, r8, r02, r4);
            r1.setInterpolator(this.mFastOutSlowInInterpolator);
            r1.setDuration(getAdjustedDuration(250));
            r52.setInterpolator(this.mLogAccelerateInterpolator);
            r52.setDuration(getAdjustedDuration(250));
            r33.setInterpolator(this.mFastOutSlowInInterpolator);
            r33.setDuration(getAdjustedDuration(250));
            this.mCloseOverflowAnimation.getAnimations().clear();
            this.mCloseOverflowAnimation.addAnimation(r1);
            this.mCloseOverflowAnimation.addAnimation(r52);
            this.mCloseOverflowAnimation.addAnimation(r33);
            this.mContentContainer.startAnimation(this.mCloseOverflowAnimation);
            this.mIsOverflowOpen = false;
            updateOverflowButtonClickListener();
            this.mMainPanel.animate().alpha(1.0f).withLayer().setInterpolator(this.mFastOutLinearInInterpolator).setDuration(100).start();
            this.mOverflowPanel.animate().alpha(0.0f).withLayer().setInterpolator(this.mLinearOutSlowInInterpolator).setDuration(150).start();
            return;
        L5:
            r02 = (r4 + r8) - r32.getWidth();
            goto L6
        }

        private LinearLayout createMainPanel() {
            return new AnonymousClass12(this, this.mContext);
        }

        private Animation.AnimationListener createOverflowAnimationListener() {
            return new AnonymousClass14(this);
        }

        private OverflowPanel createOverflowPanel() {
            OverflowPanel r0 = new OverflowPanel(this, this);
            r0.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            r0.setDivider(null);
            r0.setDividerHeight(0);
            AnonymousClass13 r2 = new AnonymousClass13(this, this.mContext, 0);
            r0.setPadding(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(2.0f));
            r0.setClipToPadding(false);
            r0.setAdapter(r2);
            r0.setOnItemClickListener(new FloatingToolbar$FloatingToolbarPopup$$ExternalSyntheticLambda4(this, r0));
            return r0;
        }

        private int getAdjustedDuration(int r2) {
            int r1 = this.mTransitionDurationScale;
            if (r1 >= 150) goto L7;
            return Math.max(r2 - 50, 0);
        L7:
            if (r1 > 300) goto L9;
            return r2;
        L9:
            return r2 + 50;
        }

        private int getAdjustedToolbarWidth(int r2) {
            refreshViewPort();
            int r1 = this.mViewPortOnScreen.width() - (AndroidUtilities.dp(16.0f) * 2);
            if (r2 > 0) goto L6;
            r2 = AndroidUtilities.dp(400.0f);
        L6:
            return Math.min(r2, r1);
        }

        private int getOverflowWidth() {
            int r0 = this.mOverflowPanel.getAdapter().getCount();
            int r1 = 0;
            int r2 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r2 = Math.max(this.mOverflowPanelViewHelper.calculateWidth((MenuItem) this.mOverflowPanel.getAdapter().getItem(r1)), r2);
            r1 = r1 + 1;
            goto L3
        L5:
            return r2;
        }

        private boolean hasOverflow() {
            if (this.mOverflowPanelSize == null) goto L6;
            return true;
        L6:
            return false;
        }

        private boolean isInRTLMode() {
            return false;
        }

        private boolean isOverflowAnimating() {
            if (this.mOpenOverflowAnimation.hasStarted() == true) goto L5;
        L7:
            boolean r0 = false;
        L9:
            if (this.mCloseOverflowAnimation.hasStarted() == true) goto L11;
        L13:
            boolean r4 = false;
        L14:
            if (r0 == true) goto L18;
            if (r4 == true) goto L18;
            return false;
        L18:
            return true;
        L11:
            if (this.mCloseOverflowAnimation.hasEnded() == true) goto L13;
            r4 = true;
            goto L14
        L5:
            if (this.mOpenOverflowAnimation.hasEnded() == true) goto L7;
            r0 = true;
            goto L9
        }

        private /* synthetic */ void lambda$createOverflowPanel$4(OverflowPanel r1, AdapterView r2, View r3, int r4, long r5) {
            MenuItem r12 = (MenuItem) r1.getAdapter().getItem(r4);
            if (FloatingToolbar.m5758$$Nest$fgetpremiumLockClickListener(this.this$0) != null) goto L5;
        L8:
            MenuItem.OnMenuItemClickListener r0 = this.mOnMenuItemClickListener;
            if (r0 == null) goto L12;
            r0.onMenuItemClick(r12);
            return;
        L12:
            return;
        L5:
            if (FloatingToolbar.premiumOptions.contains(Integer.valueOf(r12.getItemId())) == false) goto L8;
            int r13 = -this.shiftDp;
            this.shiftDp = r13;
            AndroidUtilities.shakeViewSpring(r3, r13);
            BotWebViewVibrationEffect.APP_ERROR.vibrate();
            FloatingToolbar.m5758$$Nest$fgetpremiumLockClickListener(this.this$0).run();
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            onBackPressed();
        }

        private /* synthetic */ void lambda$updateOverflowButtonClickListener$1(View r1) {
            onBackPressed();
        }

        private /* synthetic */ void lambda$updateOverflowButtonClickListener$2(View r1) {
            onBackPressed();
        }

        private void layoutOverflowPanelItems(List<MenuItem> r9) {
            ArrayAdapter r0 = (ArrayAdapter) this.mOverflowPanel.getAdapter();
            r0.clear();
            if (FloatingToolbar.m5758$$Nest$fgetpremiumLockClickListener(this.this$0) == null) goto L5;
            Collections.sort(r9, new FloatingToolbar$FloatingToolbarPopup$$ExternalSyntheticLambda0());
        L5:
            int r1 = r9.size();
            boolean r2 = MessagesController.getInstance(UserConfig.selectedAccount).premiumFeaturesBlocked();
            int r3 = 0;
        L6:
            if (r3 >= r1) goto L16;
            MenuItem r4 = r9.get(r3);
            boolean r6 = true;
            if (FloatingToolbar.m5758$$Nest$fgetpremiumLockClickListener(this.this$0) != null) goto L11;
        L13:
            if (r6 == false) goto L15;
            r0.add(r4);
        L15:
            r3 = r3 + 1;
            goto L6
        L11:
            if (FloatingToolbar.premiumOptions.contains(Integer.valueOf(r4.getItemId())) == false) goto L13;
            r6 = true ^ r2;
            goto L13
        L16:
            this.mOverflowPanel.setAdapter(r0);
            boolean r92 = this.mOpenOverflowUpwards;
            OverflowPanel r02 = this.mOverflowPanel;
            if (r92 == false) goto L19;
            r02.setY(0.0f);
        L20:
            Size r12 = new Size(Math.max(getOverflowWidth(), this.mOverflowButtonSize.getWidth()), calculateOverflowHeight(4));
            this.mOverflowPanelSize = r12;
            setSize(this.mOverflowPanel, r12);
            return;
        L19:
            r02.setY(this.mOverflowButtonSize.getHeight());
            goto L20
        }

        private void maybeComputeTransitionDurationScale() {
            Size r0 = this.mMainPanelSize;
            if (r0 != null) goto L5;
            return;
        L5:
            if (this.mOverflowPanelSize == null) goto L9;
            int r02 = r0.getWidth() - this.mOverflowPanelSize.getWidth();
            int r1 = this.mOverflowPanelSize.getHeight() - this.mMainPanelSize.getHeight();
            this.mTransitionDurationScale = (int) (Math.sqrt((r02 * r02) + (r1 * r1)) / this.mContentContainer.getContext().getResources().getDisplayMetrics().density);
            return;
        }

        private Size measure(View r2) {
            r2.measure(0, 0);
            return new Size(r2.getMeasuredWidth(), r2.getMeasuredHeight());
        }

        private void onBackPressed() {
            boolean r0 = this.mIsOverflowOpen;
            ImageView r1 = this.mOverflowButtonIcon;
            if (r0 == false) goto L6;
            r1.setImageDrawable(this.mToOverflow);
            this.mToOverflow.start();
            closeOverflow();
            return;
        L6:
            r1.setImageDrawable(this.mToArrow);
            this.mToArrow.start();
            openOverflow();
        }

        private void openOverflow() {
            int r3 = this.mOverflowPanelSize.getWidth();
            int r0 = this.mOverflowPanelSize.getHeight();
            int r4 = this.mContentContainer.getWidth();
            int r7 = this.mContentContainer.getHeight();
            float r8 = this.mContentContainer.getY();
            float r5 = this.mContentContainer.getX();
            AnonymousClass6 r1 = new AnonymousClass6(this, r3, r4, r5, r5 + this.mContentContainer.getWidth());
            AnonymousClass7 r9 = new AnonymousClass7(this, r0, r7, r8);
            float r02 = this.mOverflowButton.getX();
            boolean r52 = isInRTLMode();
            ImageView r6 = this.mOverflowButtonIcon;
            float r32 = r3;
            if (r52 == false) goto L5;
            float r33 = (r32 + r02) - r6.getWidth();
        L6:
            AnonymousClass8 r53 = new AnonymousClass8(this, r02, r33, r4);
            r1.setInterpolator(this.mLogAccelerateInterpolator);
            r1.setDuration(getAdjustedDuration(250));
            r9.setInterpolator(this.mFastOutSlowInInterpolator);
            r9.setDuration(getAdjustedDuration(250));
            r53.setInterpolator(this.mFastOutSlowInInterpolator);
            r53.setDuration(getAdjustedDuration(250));
            this.mOpenOverflowAnimation.getAnimations().clear();
            this.mOpenOverflowAnimation.addAnimation(r1);
            this.mOpenOverflowAnimation.addAnimation(r9);
            this.mOpenOverflowAnimation.addAnimation(r53);
            this.mContentContainer.startAnimation(this.mOpenOverflowAnimation);
            this.mIsOverflowOpen = true;
            updateOverflowButtonClickListener();
            this.mMainPanel.animate().alpha(0.0f).withLayer().setInterpolator(this.mLinearOutSlowInInterpolator).setDuration(250).start();
            RelativeLayout.LayoutParams r92 = (RelativeLayout.LayoutParams) this.mOverflowButton.getLayoutParams();
            r92.width = this.mOverflowPanel.getWidth();
            this.mOverflowButton.setLayoutParams(r92);
            this.mOverflowPanel.setAlpha(1.0f);
            return;
        L5:
            r33 = (r02 - r32) + r6.getWidth();
            goto L6
        }

        private void positionContentYCoordinatesIfOpeningOverflowUpwards() {
            if (this.mOpenOverflowUpwards == false) goto L6;
            this.mMainPanel.setY(this.mContentContainer.getHeight() - this.mMainPanelSize.getHeight());
            this.mOverflowButton.setY(this.mContentContainer.getHeight() - this.mOverflowButton.getHeight());
            this.mOverflowPanel.setY(this.mContentContainer.getHeight() - this.mOverflowPanelSize.getHeight());
            return;
        }

        private void preparePopupContent() {
            this.mContentContainer.removeAllViews();
            if (hasOverflow() == false) goto L5;
            this.mContentContainer.addView(this.mOverflowPanel);
        L5:
            this.mContentContainer.addView(this.mMainPanel);
            if (hasOverflow() == false) goto L8;
            this.mContentContainer.addView(this.mOverflowButton);
        L8:
            setPanelsStatesAtRestingPosition();
            setContentAreaAsTouchableSurface();
            if (isInRTLMode() == false) goto L12;
            this.mContentContainer.setAlpha(0.0f);
            this.mContentContainer.post(this.mPreparePopupContentRTLHelper);
            return;
        }

        private void refreshCoordinatesAndOverflowDirection(Rect r13) {
            refreshViewPort();
            int r0 = Math.min(r13.centerX() - (this.mPopupWindow.getWidth() / 2), this.mViewPortOnScreen.right - this.mPopupWindow.getWidth());
            int r1 = r13.top;
            Rect r3 = this.mViewPortOnScreen;
            int r12 = r1 - r3.top;
            int r32 = r3.bottom - r13.bottom;
            int r4 = this.mMarginVertical * 2;
            int r5 = this.mLineHeight + r4;
            if (hasOverflow() == true) goto L12;
            if (r12 < r5) goto L6;
            int r132 = r13.top - r5;
        L25:
            this.mParent.getRootView().getLocationOnScreen(this.mTmpCoords);
            int[] r14 = this.mTmpCoords;
            int r2 = r14[0];
            int r15 = r14[1];
            this.mParent.getRootView().getLocationInWindow(this.mTmpCoords);
            int[] r33 = this.mTmpCoords;
            int r42 = r33[0];
            int r16 = r15 - r33[1];
            this.mCoordsOnWindow.set(Math.max(0, r0 - (r2 - r42)), Math.max(0, r132 - r16));
            return;
        L6:
            if (r32 < r5) goto L9;
            r132 = r13.bottom;
            goto L25
        L9:
            if (r32 < this.mLineHeight) goto L11;
            r132 = r13.bottom - this.mMarginVertical;
            goto L25
        L11:
            r132 = Math.max(this.mViewPortOnScreen.top, r13.top - r5);
            goto L25
        L12:
            int r22 = calculateOverflowHeight(2) + r4;
            Rect r6 = this.mViewPortOnScreen;
            int r9 = (r6.bottom - r13.top) + r5;
            int r10 = (r13.bottom - r6.top) + r5;
            if (r12 < r22) goto L15;
            updateOverflowHeight(r12 - r4);
            r132 = r13.top - this.mPopupWindow.getHeight();
            this.mOpenOverflowUpwards = true;
            goto L25
        L15:
            if (r12 < r5) goto L18;
            if (r9 < r22) goto L18;
            updateOverflowHeight(r9 - r4);
            r132 = r13.top - r5;
            this.mOpenOverflowUpwards = false;
        L18:
            if (r32 < r22) goto L20;
            updateOverflowHeight(r32 - r4);
            r132 = r13.bottom;
            this.mOpenOverflowUpwards = false;
            goto L25
        L20:
            if (r32 >= r5) goto L22;
        L24:
            updateOverflowHeight(this.mViewPortOnScreen.height() - r4);
            r132 = this.mViewPortOnScreen.top;
            this.mOpenOverflowUpwards = false;
            goto L25
        L22:
            if (r6.height() < r22) goto L24;
            updateOverflowHeight(r10 - r4);
            r132 = (r13.bottom + r5) - this.mPopupWindow.getHeight();
            this.mOpenOverflowUpwards = true;
            goto L25
        }

        private void refreshViewPort() {
            this.mParent.getWindowVisibleDisplayFrame(this.mViewPortOnScreen);
        }

        private void runDismissAnimation() {
            this.mDismissAnimation.start();
        }

        private void runHideAnimation() {
            this.mHideAnimation.start();
        }

        private void runShowAnimation() {
            this.mShowAnimation.start();
        }

        private void setButtonTagAndClickListener(View r1, MenuItem r2) {
            r1.setTag(r2);
            r1.setOnClickListener(this.mMenuItemButtonOnClickListener);
        }

        private void setContentAreaAsTouchableSurface() {
            if (this.mIsOverflowOpen == false) goto L5;
            int r0 = this.mOverflowPanelSize.getWidth();
            int r1 = this.mOverflowPanelSize.getHeight();
        L6:
            this.mTouchableRegion.set((int) this.mContentContainer.getX(), (int) this.mContentContainer.getY(), ((int) this.mContentContainer.getX()) + r0, ((int) this.mContentContainer.getY()) + r1);
            return;
        L5:
            r0 = this.mMainPanelSize.getWidth();
            r1 = this.mMainPanelSize.getHeight();
            goto L6
        }

        private void setHeight(View r2, int r3) {
            setSize(r2, r2.getLayoutParams().width, r3);
        }

        private void setPanelsStatesAtRestingPosition() {
            this.mOverflowButton.setEnabled(true);
            this.mOverflowPanel.awakenScrollBars();
            if (this.mIsOverflowOpen == false) goto L14;
            setSize(this.mContentContainer, this.mOverflowPanelSize);
            this.mMainPanel.setAlpha(0.0f);
            this.mMainPanel.setVisibility(4);
            this.mOverflowPanel.setAlpha(1.0f);
            this.mOverflowPanel.setVisibility(0);
            this.mOverflowButtonIcon.setImageDrawable(this.mArrow);
            this.mOverflowButton.setContentDescription(LocaleController.getString(org.telegram.messenger.R.string.AccDescrMoreOptions));
            boolean r1 = isInRTLMode();
            ViewGroup r2 = this.mContentContainer;
            if (r1 == false) goto L7;
            r2.setX(this.mMarginHorizontal);
            this.mMainPanel.setX(0.0f);
            this.mOverflowButton.setX(r0.getWidth() - this.mOverflowButtonSize.getWidth());
            this.mOverflowPanel.setX(0.0f);
        L8:
            boolean r12 = this.mOpenOverflowUpwards;
            ViewGroup r22 = this.mContentContainer;
            if (r12 == false) goto L12;
            r22.setY(this.mMarginVertical);
            this.mMainPanel.setY(r0.getHeight() - this.mContentContainer.getHeight());
            this.mOverflowButton.setY(r0.getHeight() - this.mOverflowButtonSize.getHeight());
            this.mOverflowPanel.setY(0.0f);
            return;
        L12:
            r22.setY(this.mMarginVertical);
            this.mMainPanel.setY(0.0f);
            this.mOverflowButton.setY(0.0f);
            this.mOverflowPanel.setY(this.mOverflowButtonSize.getHeight());
            return;
        L7:
            r2.setX((this.mPopupWindow.getWidth() - r0.getWidth()) - this.mMarginHorizontal);
            this.mMainPanel.setX(-this.mContentContainer.getX());
            this.mOverflowButton.setX(0.0f);
            this.mOverflowPanel.setX(0.0f);
            goto L8
        L14:
            setSize(this.mContentContainer, this.mMainPanelSize);
            this.mMainPanel.setAlpha(1.0f);
            this.mMainPanel.setVisibility(0);
            this.mOverflowPanel.setAlpha(0.0f);
            this.mOverflowPanel.setVisibility(4);
            this.mOverflowButtonIcon.setImageDrawable(this.mOverflow);
            this.mOverflowButton.setContentDescription(LocaleController.getString(org.telegram.messenger.R.string.AccDescrMoreOptions));
            if (hasOverflow() == false) goto L26;
            boolean r13 = isInRTLMode();
            ViewGroup r23 = this.mContentContainer;
            if (r13 == false) goto L19;
            r23.setX(this.mMarginHorizontal);
            this.mMainPanel.setX(0.0f);
            this.mOverflowButton.setX(0.0f);
            this.mOverflowPanel.setX(0.0f);
        L20:
            boolean r14 = this.mOpenOverflowUpwards;
            ViewGroup r24 = this.mContentContainer;
            if (r14 == false) goto L24;
            r24.setY((this.mMarginVertical + this.mOverflowPanelSize.getHeight()) - r0.getHeight());
            this.mMainPanel.setY(0.0f);
            this.mOverflowButton.setY(0.0f);
            this.mOverflowPanel.setY(r0.getHeight() - this.mOverflowPanelSize.getHeight());
            return;
        L24:
            r24.setY(this.mMarginVertical);
            this.mMainPanel.setY(0.0f);
            this.mOverflowButton.setY(0.0f);
            this.mOverflowPanel.setY(this.mOverflowButtonSize.getHeight());
            return;
        L19:
            r23.setX((this.mPopupWindow.getWidth() - r0.getWidth()) - this.mMarginHorizontal);
            this.mMainPanel.setX(0.0f);
            this.mOverflowButton.setX(r0.getWidth() - this.mOverflowButtonSize.getWidth());
            this.mOverflowPanel.setX(r0.getWidth() - this.mOverflowPanelSize.getWidth());
            goto L20
        L26:
            this.mContentContainer.setX(this.mMarginHorizontal);
            this.mContentContainer.setY(this.mMarginVertical);
            this.mMainPanel.setX(0.0f);
            this.mMainPanel.setY(0.0f);
        }

        private void setSize(View r2, int r3, int r4) {
            r2.setMinimumWidth(r3);
            r2.setMinimumHeight(r4);
            ViewGroup.LayoutParams r1 = r2.getLayoutParams();
            if (r1 != null) goto L5;
            r1 = new ViewGroup.LayoutParams(0, 0);
        L5:
            r1.width = r3;
            r1.height = r4;
            r2.setLayoutParams(r1);
        }

        private void setSize(View r2, Size r3) {
            setSize(r2, r3.getWidth(), r3.getHeight());
        }

        private void setTouchableSurfaceInsetsComputer() {
        }

        private void setWidth(View r2, int r3) {
            setSize(r2, r3, r2.getLayoutParams().height);
        }

        private void setZeroTouchableSurface() {
            this.mTouchableRegion.setEmpty();
        }

        private void updateOverflowButtonClickListener() {
            boolean r0 = this.mIsOverflowOpen;
            FrameLayout r1 = this.mOverflowButton;
            if (r0 == false) goto L6;
            r1.setClickable(true);
            this.mOverflowButton.setOnClickListener(new FloatingToolbar$FloatingToolbarPopup$$ExternalSyntheticLambda1(this));
            this.mOverflowButtonIcon.setClickable(false);
            this.mOverflowButtonIcon.setOnClickListener(null);
            return;
        L6:
            r1.setClickable(false);
            this.mOverflowButton.setOnClickListener(null);
            this.mOverflowButtonIcon.setClickable(true);
            this.mOverflowButtonIcon.setOnClickListener(new FloatingToolbar$FloatingToolbarPopup$$ExternalSyntheticLambda2(this));
        }

        private void updateOverflowHeight(int r3) {
            if (hasOverflow() == false) goto L15;
            int r32 = calculateOverflowHeight((r3 - this.mOverflowButtonSize.getHeight()) / this.mLineHeight);
            if (this.mOverflowPanelSize.getHeight() == r32) goto L7;
            this.mOverflowPanelSize = new Size(this.mOverflowPanelSize.getWidth(), r32);
        L7:
            setSize(this.mOverflowPanel, this.mOverflowPanelSize);
            boolean r0 = this.mIsOverflowOpen;
            ViewGroup r1 = this.mContentContainer;
            if (r0 == false) goto L12;
            setSize(r1, this.mOverflowPanelSize);
            if (this.mOpenOverflowUpwards == false) goto L13;
            int r02 = this.mOverflowPanelSize.getHeight() - r32;
            ViewGroup r33 = this.mContentContainer;
            float r03 = r02;
            r33.setY(r33.getY() + r03);
            FrameLayout r34 = this.mOverflowButton;
            r34.setY(r34.getY() - r03);
        L13:
            updatePopupSize();
            return;
        L12:
            setSize(r1, this.mMainPanelSize);
            goto L13
        }

        private void updatePopupSize() {
            Size r0 = this.mMainPanelSize;
            int r1 = 0;
            if (r0 == null) goto L5;
            r1 = Math.max(0, r0.getWidth());
            int r02 = Math.max(0, this.mMainPanelSize.getHeight());
        L6:
            Size r2 = this.mOverflowPanelSize;
            if (r2 == null) goto L9;
            r1 = Math.max(r1, r2.getWidth());
            r02 = Math.max(r02, this.mOverflowPanelSize.getHeight());
        L9:
            this.mPopupWindow.setWidth(r1 + (this.mMarginHorizontal * 2));
            this.mPopupWindow.setHeight(r02 + (this.mMarginVertical * 2));
            maybeComputeTransitionDurationScale();
            return;
        L5:
            r02 = 0;
            goto L6
        }

        public void dismiss() {
            if (this.mDismissed == false) goto L5;
            return;
        L5:
            this.mHidden = false;
            this.mDismissed = true;
            this.mHideAnimation.cancel();
            runDismissAnimation();
            setZeroTouchableSurface();
        }

        public void hide() {
            if (isShowing() == true) goto L5;
            return;
        L5:
            this.mHidden = true;
            runHideAnimation();
            setZeroTouchableSurface();
        }

        public boolean isShowing() {
            if (this.mDismissed == false) goto L5;
            return false;
        L5:
            if (this.mHidden == true) goto L10;
            return true;
        L10:
            return false;
        }

        public List<MenuItem> layoutMainPanelItems(List<MenuItem> r18, int r19) {
            LinearLayout r1 = this.mMainPanelButtons;
            if (r1 != null) goto L6;
            r1 = this.mMainPanel;
        L6:
            LinkedList r2 = new LinkedList(r18);
            Iterator r3 = r2.iterator();
            int r12 = r19;
            boolean r10 = true;
        L8:
            if (r3.hasNext() == false) goto L39;
            MenuItem r7 = (MenuItem) r3.next();
            boolean r14 = r3.hasNext();
            boolean r11 = !r14;
            if (r7 == null) goto L16;
            if (FloatingToolbar.m5758$$Nest$fgetpremiumLockClickListener(this.this$0) == null) goto L16;
            if (FloatingToolbar.premiumOptions.contains(Integer.valueOf(r7.getItemId())) == true) goto L8;
        L16:
            View r5 = FloatingToolbar.m5761$$Nest$mcreateMenuItemButton(this.this$0, this.mContext, r7, this.mIconTextSpacing, false, r10, r11);
            if ((r5 instanceof LinearLayout) == false) goto L19;
            ((LinearLayout) r5).setGravity(17);
        L19:
            double r15 = 1.5d;
            if (r10 == false) goto L22;
            double r102 = 1.5d;
        L23:
            int r6 = (int) (r102 * r5.getPaddingStart());
            int r8 = r5.getPaddingTop();
            if (r14 == false) goto L27;
            r15 = 1.0d;
        L27:
            r5.setPaddingRelative(r6, r8, (int) (r5.getPaddingEnd() * r15), r5.getPaddingBottom());
            r5.measure(0, 0);
            int r62 = Math.min(r5.getMeasuredWidth(), r19);
            if (r62 > (r12 - this.mOverflowButtonSize.getWidth())) goto L30;
            boolean r9 = true;
        L31:
            if (r14 == true) goto L34;
            if (r62 > r12) goto L34;
            boolean r103 = true;
        L35:
            if (r9 == true) goto L37;
            if (r103 == false) goto L39;
        L37:
            setButtonTagAndClickListener(r5, r7);
            r1.addView(r5);
            ViewGroup.LayoutParams r72 = r5.getLayoutParams();
            r72.width = r62;
            r5.setLayoutParams(r72);
            r12 = r12 - r62;
            r3.remove();
            r10 = false;
        L34:
            r103 = false;
            goto L35
        L30:
            r9 = false;
            goto L31
        L22:
            r102 = 1.0d;
        L39:
            if (r2.isEmpty() == true) goto L41;
            r1.setPaddingRelative(0, 0, this.mOverflowButtonSize.getWidth(), 0);
        L41:
            this.mMainPanelSize = measure(this.mMainPanel);
            return r2;
        }

        public void layoutMenuItems(List<MenuItem> r1, MenuItem.OnMenuItemClickListener r2, int r3) {
            this.mOnMenuItemClickListener = r2;
            cancelOverflowAnimations();
            clearPanels();
            List<MenuItem> r12 = layoutMainPanelItems(r1, getAdjustedToolbarWidth(r3));
            if (r12.isEmpty() == true) goto L5;
            layoutOverflowPanelItems(r12);
        L5:
            updatePopupSize();
        }

        public void show(Rect r5) {
            if (isShowing() == false) goto L5;
            return;
        L5:
            this.mHidden = false;
            this.mDismissed = false;
            cancelDismissAndHideAnimations();
            cancelOverflowAnimations();
            refreshCoordinatesAndOverflowDirection(r5);
            preparePopupContent();
            PopupWindow r52 = this.mPopupWindow;
            View r1 = this.mParent;
            Point r2 = this.mCoordsOnWindow;
            r52.showAtLocation(r1, 0, r2.x, r2.y);
            setTouchableSurfaceInsetsComputer();
            runShowAnimation();
        }

        public void updateCoordinates(Rect r4) {
            if (isShowing() == true) goto L5;
            return;
        L5:
            if (this.mPopupWindow.isShowing() == false) goto L10;
            cancelOverflowAnimations();
            refreshCoordinatesAndOverflowDirection(r4);
            preparePopupContent();
            PopupWindow r42 = this.mPopupWindow;
            Point r0 = this.mCoordsOnWindow;
            r42.update(r0.x, r0.y, r42.getWidth(), this.mPopupWindow.getHeight());
            return;
        }
    }

    public interface StyleDelegate {
        void addStyle(int r1, int r2, int r3);

        int getCurrentStyle(int r1, int r2);

        void removeStyle(int r1, int r2, int r3);
    }

    public static /* synthetic */ boolean $r8$lambda$UV4UHvSG7impHUqxEN2IE95BRm0(MenuItem r0) {
        return false;
    }

    /* renamed from: $r8$lambda$oeoem8jsMsNfzaGiZfcK-WwEphE, reason: not valid java name */
    public static /* synthetic */ int m5755$r8$lambda$oeoem8jsMsNfzaGiZfcKWwEphE(MenuItem r0, MenuItem r1) {
        return r0.getOrder() - r1.getOrder();
    }

    /* renamed from: -$$Nest$fgetcurrentStyle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5756$$Nest$fgetcurrentStyle(FloatingToolbar r0) {
        return r0.currentStyle;
    }

    /* renamed from: -$$Nest$fgetmPopup, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FloatingToolbarPopup m5757$$Nest$fgetmPopup(FloatingToolbar r0) {
        return r0.mPopup;
    }

    /* renamed from: -$$Nest$fgetpremiumLockClickListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5758$$Nest$fgetpremiumLockClickListener(FloatingToolbar r0) {
        return r0.premiumLockClickListener;
    }

    /* renamed from: -$$Nest$fputmWidthChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5759$$Nest$fputmWidthChanged(FloatingToolbar r0, boolean r1) {
        r0.mWidthChanged = r1;
    }

    /* renamed from: -$$Nest$mcreateContentContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m5760$$Nest$mcreateContentContainer(FloatingToolbar r0, Context r1) {
        return r0.createContentContainer(r1);
    }

    /* renamed from: -$$Nest$mcreateMenuItemButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m5761$$Nest$mcreateMenuItemButton(FloatingToolbar r0, Context r1, MenuItem r2, int r3, boolean r4, boolean r5, boolean r6) {
        return r0.createMenuItemButton(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5762$$Nest$mgetThemedColor(FloatingToolbar r0, int r1) {
        return r0.getThemedColor(r1);
    }

    /* renamed from: -$$Nest$smcreateEnterAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m5763$$Nest$smcreateEnterAnimation(View r0) {
        return createEnterAnimation(r0);
    }

    /* renamed from: -$$Nest$smcreateExitAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m5764$$Nest$smcreateExitAnimation(View r0, int r1, Animator.AnimatorListener r2) {
        return createExitAnimation(r0, r1, r2);
    }

    /* renamed from: -$$Nest$smcreatePopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PopupWindow m5765$$Nest$smcreatePopupWindow(ViewGroup r0) {
        return createPopupWindow(r0);
    }

    /* renamed from: -$$Nest$smupdateMenuItemButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5766$$Nest$smupdateMenuItemButton(View r0, MenuItem r1, int r2, boolean r3) {
        updateMenuItemButton(r0, r1, r2, r3);
    }

    static {
        NO_OP_MENUITEM_CLICK_LISTENER = new FloatingToolbar$$ExternalSyntheticLambda1();
        STYLE_BUTTONS = Arrays.asList(new Integer[]{Integer.valueOf(org.telegram.messenger.R.id.menu_regular), Integer.valueOf(org.telegram.messenger.R.id.menu_bold), Integer.valueOf(org.telegram.messenger.R.id.menu_italic), Integer.valueOf(org.telegram.messenger.R.id.menu_strike), Integer.valueOf(org.telegram.messenger.R.id.menu_mono), Integer.valueOf(org.telegram.messenger.R.id.menu_underline), Integer.valueOf(org.telegram.messenger.R.id.menu_spoiler), Integer.valueOf(org.telegram.messenger.R.id.menu_link), Integer.valueOf(org.telegram.messenger.R.id.menu_quote), Integer.valueOf(org.telegram.messenger.R.id.menu_date)});
        premiumOptions = Arrays.asList(new Integer[]{Integer.valueOf(org.telegram.messenger.R.id.menu_bold), Integer.valueOf(org.telegram.messenger.R.id.menu_italic), Integer.valueOf(org.telegram.messenger.R.id.menu_strike), Integer.valueOf(org.telegram.messenger.R.id.menu_link), Integer.valueOf(org.telegram.messenger.R.id.menu_mono), Integer.valueOf(org.telegram.messenger.R.id.menu_code), Integer.valueOf(org.telegram.messenger.R.id.menu_underline), Integer.valueOf(org.telegram.messenger.R.id.menu_spoiler), Integer.valueOf(org.telegram.messenger.R.id.menu_quote)});
    }

    public FloatingToolbar(Context r7, View r8, int r9, Theme.ResourcesProvider r10) {
        this(r7, r8, r9, r10, null);
    }

    public FloatingToolbar(Context r2, View r3, int r4, Theme.ResourcesProvider r5, BlurredBackgroundDrawableViewFactory r6) {
        this.mContentRect = new Rect();
        this.mPreviousContentRect = new Rect();
        this.mShowingMenuItems = new ArrayList();
        this.mMenuItemClickListener = NO_OP_MENUITEM_CLICK_LISTENER;
        this.mWidthChanged = true;
        this.mOrientationChangeHandler = new AnonymousClass1(this);
        this.mMenuItemComparator = new FloatingToolbar$$ExternalSyntheticLambda0();
        this.mWindowView = r3;
        this.currentStyle = r4;
        this.blurredBackgroundDrawableViewFactory = r6;
        this.resourcesProvider = r5;
        this.mPopup = new FloatingToolbarPopup(this, r2, r3);
    }

    private ViewGroup createContentContainer(Context r8) {
        RelativeLayout r0 = new RelativeLayout(r8);
        ViewGroup.MarginLayoutParams r82 = new ViewGroup.MarginLayoutParams(-2, -2);
        int r2 = AndroidUtilities.dp(20.0f);
        r82.rightMargin = r2;
        r82.topMargin = r2;
        r82.leftMargin = r2;
        r82.bottomMargin = r2;
        r0.setLayoutParams(r82);
        r0.setElevation(AndroidUtilities.dp(1.0f));
        r0.setFocusable(true);
        r0.setFocusableInTouchMode(true);
        BlurredBackgroundDrawableViewFactory r22 = this.blurredBackgroundDrawableViewFactory;
        if (r22 == null) goto L5;
        r0.setBackground(r22.create(r0, true).setColorProvider(BlurredBackgroundProviderImpl.photoViewerMenu(this.resourcesProvider)).setRadius(AndroidUtilities.dp(12.0f)));
    L13:
        r0.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        r0.setClipToOutline(true);
        return r0;
    L5:
        GradientDrawable r23 = new GradientDrawable();
        r23.setShape(0);
        float r3 = AndroidUtilities.dp(12.0f);
        r23.setCornerRadii(new float[]{r3, r3, r3, r3, r3, r3, r3, r3});
        int r32 = this.currentStyle;
        if (r32 != 0) goto L8;
        r23.setColor(getThemedColor(Theme.key_dialogBackground));
    L12:
        r0.setBackground(r23);
        goto L13
    L8:
        if (r32 != 2) goto L10;
        r23.setColor(-115203550);
        goto L12
    L10:
        if (r32 != 1) goto L12;
        r23.setColor(getThemedColor(Theme.key_windowBackgroundWhite));
        goto L12
    }

    private static AnimatorSet createEnterAnimation(View r3) {
        AnimatorSet r0 = new AnimatorSet();
        r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(r3, View.ALPHA, new float[]{0.0f, 1.0f}).setDuration(150)});
        return r0;
    }

    private static AnimatorSet createExitAnimation(View r3, int r4, Animator.AnimatorListener r5) {
        AnimatorSet r0 = new AnimatorSet();
        r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(r3, View.ALPHA, new float[]{1.0f, 0.0f}).setDuration(100)});
        r0.setStartDelay(r4);
        r0.addListener(r5);
        return r0;
    }

    private View createMenuItemButton(Context r19, MenuItem r20, int r21, boolean r22, boolean r23, boolean r24) {
        LinearLayout r3 = new LinearLayout(r19);
        r3.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        boolean r4 = false;
        r3.setOrientation(0);
        float r6 = 48.0f;
        r3.setMinimumWidth(AndroidUtilities.dp(48.0f));
        if (r22 == false) goto L5;
        float r8 = 42.0f;
    L6:
        r3.setMinimumHeight(AndroidUtilities.dp(r8));
        r3.setPaddingRelative(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        TextView r82 = new TextView(r19);
        r82.setGravity(17);
        r82.setSingleLine(true);
        r82.setEllipsize(TextUtils.TruncateAt.END);
        r82.setTypeface(AndroidUtilities.bold());
        r82.setTextSize(1, 14.0f);
        r82.setFocusable(false);
        r82.setImportantForAccessibility(2);
        r82.setFocusableInTouchMode(false);
        int r11 = getThemedColor(Theme.key_listSelector);
        int r12 = this.currentStyle;
        if (r12 != 0) goto L9;
        int r10 = getThemedColor(Theme.key_dialogTextBlack);
        r82.setTextColor(r10);
    L14:
        if (r23 == true) goto L18;
        if (r24 == true) goto L18;
        r3.setBackground(Theme.getSelectorDrawable(r11, false));
    L32:
        r82.setPaddingRelative(AndroidUtilities.dp(11.0f), 0, 0, 0);
        if (r22 == false) goto L35;
        r6 = 42.0f;
    L35:
        r3.addView(r82, new LinearLayout.LayoutParams(-2, AndroidUtilities.dp(r6)));
        r3.addView(new Space(r19), new LinearLayout.LayoutParams(-1, 1, 1.0f));
        ImageView r5 = new ImageView(r19);
        r5.setImageResource(org.telegram.messenger.R.drawable.msg_mini_lock3);
        r5.setScaleType(ImageView.ScaleType.CENTER);
        r5.setColorFilter(new PorterDuffColorFilter(Theme.multAlpha(r10, 0.4f), PorterDuff.Mode.SRC_IN));
        r5.setVisibility(8);
        r3.addView(r5, LayoutHelper.createLinear(-2, -1, 0.0f, 0, 12, 0, 0, 0));
        if (r20 != null) goto L38;
    L41:
        return r3;
    L38:
        if (this.premiumLockClickListener == null) goto L40;
        r4 = true;
    L40:
        updateMenuItemButton(r3, r20, r21, r4);
    L18:
        int r122 = 12;
        if (r23 == false) goto L21;
        int r13 = 12;
    L22:
        if (r24 == false) goto L24;
        int r14 = 12;
    L25:
        if (r24 == false) goto L27;
        int r15 = 12;
    L28:
        if (r23 == true) goto L31;
        r122 = 0;
    L31:
        r3.setBackground(Theme.createRadSelectorDrawable(r11, r13, r14, r15, r122));
        goto L32
    L27:
        r15 = 0;
        goto L28
    L24:
        r14 = 0;
        goto L25
    L21:
        r13 = 0;
        goto L22
    L9:
        if (r12 != 2) goto L11;
        r10 = -328966;
        r82.setTextColor(-328966);
        r11 = 553648127;
        goto L14
    L11:
        if (r12 != 1) goto L13;
        r10 = getThemedColor(Theme.key_windowBackgroundWhiteBlackText);
        r82.setTextColor(r10);
        goto L14
    L13:
        r10 = getThemedColor(Theme.key_windowBackgroundWhiteBlackText);
        goto L14
    L5:
        r8 = 48.0f;
        goto L6
    }

    private static PopupWindow createPopupWindow(ViewGroup r4) {
        AnonymousClass2 r0 = new AnonymousClass2(r4.getContext());
        PopupWindow r1 = new PopupWindow(r0);
        r1.setClippingEnabled(false);
        r1.setAnimationStyle(0);
        r1.setBackgroundDrawable(new ColorDrawable(0));
        r1.setSplitTouchEnabled(true);
        r4.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        r0.addView(r4);
        return r1;
    }

    private void doShow() {
        List<MenuItem> r0 = getVisibleAndEnabledMenuItems(this.mMenu);
        Collections.sort(r0, this.mMenuItemComparator);
        if (isCurrentlyShowing(r0) == true) goto L5;
    L6:
        this.mPopup.dismiss();
        this.mPopup.layoutMenuItems(r0, this.mMenuItemClickListener, this.mSuggestedWidth);
        this.mShowingMenuItems = r0;
    L8:
        if (this.mPopup.isShowing() == true) goto L11;
        this.mPopup.show(this.mContentRect);
    L13:
        this.mWidthChanged = false;
        this.mPreviousContentRect.set(this.mContentRect);
        return;
    L11:
        if (this.mPreviousContentRect.equals(this.mContentRect) == true) goto L13;
        this.mPopup.updateCoordinates(this.mContentRect);
        goto L13
    L5:
        if (this.mWidthChanged == false) goto L8;
        goto L6
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private List<MenuItem> getVisibleAndEnabledMenuItems(Menu r6) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L3:
        if (r6 == null) goto L30;
        if (r1 >= r6.size()) goto L30;
        MenuItem r2 = r6.getItem(r1);
        if (r2.isVisible() == false) goto L29;
        if (r2.isEnabled() == false) goto L29;
        SubMenu r3 = r2.getSubMenu();
        if (r3 == null) goto L14;
        r0.addAll(getVisibleAndEnabledMenuItems(r3));
        goto L29
    L14:
        if (r2.getItemId() != org.telegram.messenger.R.id.menu_quote) goto L21;
        Utilities.Callback0Return<Boolean> r32 = this.quoteShowCallback;
        if (r32 == null) goto L21;
        if (r32.run().booleanValue() == false) goto L29;
    L21:
        if (r2.getItemId() == 16908353) goto L29;
        if (r2.getItemId() == 16909808) goto L29;
        if (r2.getItemId() == org.telegram.messenger.R.id.menu_regular) goto L27;
    L28:
        r0.add(r2);
        goto L29
    L27:
        if (this.premiumLockClickListener == null) goto L28;
    L29:
        r1 = r1 + 1;
    L30:
        return r0;
    }

    private boolean isCurrentlyShowing(List<MenuItem> r8) {
        if (this.mShowingMenuItems != null) goto L5;
    L22:
        return false;
    L5:
        if (r8.size() != this.mShowingMenuItems.size()) goto L22;
        int r0 = r8.size();
        int r2 = 0;
    L8:
        if (r2 >= r0) goto L20;
        MenuItem r3 = r8.get(r2);
        MenuItem r4 = this.mShowingMenuItems.get(r2);
        if (r3.getItemId() != r4.getItemId()) goto L19;
        if (TextUtils.equals(r3.getTitle(), r4.getTitle()) == false) goto L19;
        if (Objects.equals(r3.getIcon(), r4.getIcon()) == false) goto L19;
        if (r3.getGroupId() != r4.getGroupId()) goto L19;
        r2 = r2 + 1;
    L19:
        return false;
    L20:
        return true;
    }

    private void registerOrientationHandler() {
        unregisterOrientationHandler();
        this.mWindowView.addOnLayoutChangeListener(this.mOrientationChangeHandler);
    }

    private void unregisterOrientationHandler() {
        this.mWindowView.removeOnLayoutChangeListener(this.mOrientationChangeHandler);
    }

    private static void updateMenuItemButton(View r3, MenuItem r4, int r5, boolean r6) {
        ViewGroup r32 = (ViewGroup) r3;
        int r52 = 0;
        TextView r0 = (TextView) r32.getChildAt(0);
        r0.setEllipsize(null);
        if (TextUtils.isEmpty(r4.getTitle()) == false) goto L5;
        r0.setVisibility(8);
    L6:
        r0.setPaddingRelative(0, 0, 0, 0);
        if (r6 == true) goto L9;
    L11:
        boolean r42 = false;
    L12:
        View r33 = r32.getChildAt(2);
        if (r42 == true) goto L16;
        r52 = 8;
    L16:
        r33.setVisibility(r52);
        return;
    L9:
        if (premiumOptions.contains(Integer.valueOf(r4.getItemId())) == false) goto L11;
        r42 = true;
        goto L12
    L5:
        r0.setVisibility(0);
        r0.setText(r4.getTitle());
        goto L6
    }

    public void dismiss() {
        unregisterOrientationHandler();
        this.mPopup.dismiss();
    }

    public void hide() {
        this.mPopup.hide();
    }

    public FloatingToolbar setContentRect(Rect r2) {
        this.mContentRect.set(r2);
        return this;
    }

    public FloatingToolbar setMenu(Menu r1) {
        this.mMenu = r1;
        return this;
    }

    public FloatingToolbar setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener r1) {
        if (r1 == null) goto L5;
        this.mMenuItemClickListener = r1;
        return this;
    L5:
        this.mMenuItemClickListener = NO_OP_MENUITEM_CLICK_LISTENER;
        return this;
    }

    public void setOnPremiumLockClick(Runnable r1) {
        this.premiumLockClickListener = r1;
    }

    public void setQuoteShowVisible(Utilities.Callback0Return<Boolean> r1) {
        this.quoteShowCallback = r1;
    }

    public FloatingToolbar show() {
        registerOrientationHandler();
        doShow();
        return this;
    }

    public FloatingToolbar updateLayout() {
        if (this.mPopup.isShowing() == false) goto L5;
        doShow();
    L5:
        return this;
    }
}
