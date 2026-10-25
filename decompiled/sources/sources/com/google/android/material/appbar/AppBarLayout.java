package com.google.android.material.appbar;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* loaded from: classes5.dex */
public class AppBarLayout extends LinearLayout implements CoordinatorLayout.AttachedBehavior {
    private static final int DEF_STYLE_RES = 0;
    private static final int INVALID_SCROLL_RANGE = -1;
    static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
    static final int PENDING_ACTION_COLLAPSED = 2;
    static final int PENDING_ACTION_EXPANDED = 1;
    static final int PENDING_ACTION_FORCE = 8;
    static final int PENDING_ACTION_NONE = 0;
    private final float appBarElevation;
    private int backgroundOriginalColor;
    private Behavior behavior;
    private int currentOffset;
    private int downPreScrollRange;
    private int downScrollRange;
    private boolean haveChildWithInterpolator;
    private WindowInsetsCompat lastInsets;
    private boolean liftOnScroll;
    private ColorStateList liftOnScrollColor;
    private ValueAnimator liftOnScrollColorAnimator;
    private final long liftOnScrollColorDuration;
    private final TimeInterpolator liftOnScrollColorInterpolator;
    private ValueAnimator.AnimatorUpdateListener liftOnScrollColorUpdateListener;
    private final List<LiftOnScrollListener> liftOnScrollListeners;
    private WeakReference<View> liftOnScrollTargetView;
    private int liftOnScrollTargetViewId;
    private final LinkedHashSet<LiftOnScrollProgressListener> liftProgressListeners;
    private boolean liftable;
    private boolean liftableOverride;
    private boolean lifted;
    private List<BaseOnOffsetChangedListener> listeners;
    private int pendingAction;
    private Drawable statusBarForeground;
    private Integer statusBarForegroundOriginalColor;
    private int[] tmpStatesArray;
    private int totalScrollRange;

    /* renamed from: com.google.android.material.appbar.AppBarLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements OnApplyWindowInsetsListener {
        final /* synthetic */ AppBarLayout this$0;

        public AnonymousClass1(AppBarLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r1, WindowInsetsCompat r2) {
            return this.this$0.onWindowInsetChanged(r2);
        }
    }

    public static class BaseBehavior<T extends AppBarLayout> extends HeaderBehavior<T> {
        private static final int MAX_OFFSET_ANIMATION_DURATION = 600;
        private WeakReference<View> lastNestedScrollingChildRef;
        private int lastStartedType;
        private ValueAnimator offsetAnimator;
        private int offsetDelta;
        private BaseDragCallback onDragCallback;
        private SavedState savedState;

        /* renamed from: com.google.android.material.appbar.AppBarLayout$BaseBehavior$1, reason: invalid class name */
        public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
            final /* synthetic */ BaseBehavior this$0;
            final /* synthetic */ AppBarLayout val$child;
            final /* synthetic */ CoordinatorLayout val$coordinatorLayout;

            public AnonymousClass1(BaseBehavior r1, CoordinatorLayout r2, AppBarLayout r3) {
                this.this$0 = r1;
                this.val$coordinatorLayout = r2;
                this.val$child = r3;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator r3) {
                this.this$0.setHeaderTopBottomOffset(this.val$coordinatorLayout, this.val$child, ((Integer) r3.getAnimatedValue()).intValue());
            }
        }

        /* renamed from: com.google.android.material.appbar.AppBarLayout$BaseBehavior$2, reason: invalid class name */
        public class AnonymousClass2 extends AccessibilityDelegateCompat {
            final /* synthetic */ BaseBehavior this$0;
            final /* synthetic */ AppBarLayout val$appBarLayout;
            final /* synthetic */ CoordinatorLayout val$coordinatorLayout;

            public AnonymousClass2(BaseBehavior r1, AppBarLayout r2, CoordinatorLayout r3) {
                this.this$0 = r1;
                this.val$appBarLayout = r2;
                this.val$coordinatorLayout = r3;
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View r4, AccessibilityNodeInfoCompat r5) {
                super.onInitializeAccessibilityNodeInfo(r4, r5);
                r5.setClassName(ScrollView.class.getName());
                if (this.val$appBarLayout.getTotalScrollRange() == 0) goto L26;
                View r42 = BaseBehavior.access$000(this.this$0, this.val$coordinatorLayout);
                if (r42 != null) goto L9;
                return;
            L9:
                if (BaseBehavior.access$100(this.this$0, this.val$appBarLayout) == true) goto L12;
                return;
            L12:
                if (this.this$0.getTopBottomOffsetForScrollingSibling() == (-this.val$appBarLayout.getTotalScrollRange())) goto L15;
                r5.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                r5.setScrollable(true);
            L15:
                if (this.this$0.getTopBottomOffsetForScrollingSibling() != 0) goto L17;
                return;
            L17:
                if (r42.canScrollVertically(-1) == true) goto L19;
                r5.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                r5.setScrollable(true);
                return;
            L19:
                if ((-this.val$appBarLayout.getDownNestedPreScrollRange()) == 0) goto L25;
                r5.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                r5.setScrollable(true);
                return;
            L25:
                return;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View r12, int r13, Bundle r14) {
                if (r13 != 4096) goto L7;
                this.val$appBarLayout.setExpanded(false);
                return true;
            L7:
                if (r13 != 8192) goto L20;
                if (this.this$0.getTopBottomOffsetForScrollingSibling() == 0) goto L18;
                View r6 = BaseBehavior.access$000(this.this$0, this.val$coordinatorLayout);
                boolean r122 = r6.canScrollVertically(-1);
                AppBarLayout r132 = this.val$appBarLayout;
                if (r122 == false) goto L16;
                int r8 = -r132.getDownNestedPreScrollRange();
                if (r8 == 0) goto L18;
                this.this$0.onNestedPreScroll(this.val$coordinatorLayout, this.val$appBarLayout, r6, 0, r8, new int[]{0, 0}, 1);
                return true;
            L16:
                r132.setExpanded(true);
                return true;
            L18:
                return false;
            L20:
                return super.performAccessibilityAction(r12, r13, r14);
            }
        }

        public static abstract class BaseDragCallback<T extends AppBarLayout> {
            public BaseDragCallback() {
            }

            public abstract boolean canDrag(T r1);
        }

        public static class SavedState extends AbsSavedState {
            public static final Parcelable.Creator<SavedState> CREATOR = null;
            boolean firstVisibleChildAtMinimumHeight;
            int firstVisibleChildIndex;
            float firstVisibleChildPercentageShown;
            boolean fullyExpanded;
            boolean fullyScrolled;

            /* renamed from: com.google.android.material.appbar.AppBarLayout$BaseBehavior$SavedState$1, reason: invalid class name */
            public class AnonymousClass1 implements Parcelable.ClassLoaderCreator<SavedState> {
                public AnonymousClass1() {
                }

                @Override // android.os.Parcelable.Creator
                public SavedState createFromParcel(Parcel r2) {
                    return new SavedState(r2, null);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.ClassLoaderCreator
                public SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                    return new SavedState(r1, r2);
                }

                @Override // android.os.Parcelable.Creator
                public /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
                    return createFromParcel(r1);
                }

                @Override // android.os.Parcelable.ClassLoaderCreator
                public /* bridge */ /* synthetic */ SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                    return createFromParcel(r1, r2);
                }

                @Override // android.os.Parcelable.Creator
                public SavedState[] newArray(int r1) {
                    return new SavedState[r1];
                }

                @Override // android.os.Parcelable.Creator
                public /* bridge */ /* synthetic */ Object[] newArray(int r1) {
                    return newArray(r1);
                }
            }

            static {
                CREATOR = new AnonymousClass1();
            }

            public SavedState(Parcel r3, ClassLoader r4) {
                super(r3, r4);
                boolean r0 = false;
                if (r3.readByte() == 0) goto L5;
                boolean r42 = true;
            L6:
                this.fullyScrolled = r42;
                if (r3.readByte() == 0) goto L9;
                boolean r43 = true;
            L10:
                this.fullyExpanded = r43;
                this.firstVisibleChildIndex = r3.readInt();
                this.firstVisibleChildPercentageShown = r3.readFloat();
                if (r3.readByte() == 0) goto L13;
                r0 = true;
            L13:
                this.firstVisibleChildAtMinimumHeight = r0;
                return;
            L9:
                r43 = false;
                goto L10
            L5:
                r42 = false;
                goto L6
            }

            public SavedState(Parcelable r1) {
                super(r1);
            }

            @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
            public void writeToParcel(Parcel r1, int r2) {
                super.writeToParcel(r1, r2);
                r1.writeByte(this.fullyScrolled ? 1 : 0);
                r1.writeByte(this.fullyExpanded ? 1 : 0);
                r1.writeInt(this.firstVisibleChildIndex);
                r1.writeFloat(this.firstVisibleChildPercentageShown);
                r1.writeByte(this.firstVisibleChildAtMinimumHeight ? 1 : 0);
            }
        }

        public BaseBehavior() {
        }

        public BaseBehavior(Context r1, AttributeSet r2) {
            super(r1, r2);
        }

        public static /* synthetic */ View access$000(BaseBehavior r0, CoordinatorLayout r1) {
            return r0.getChildWithScrollingBehavior(r1);
        }

        public static /* synthetic */ boolean access$100(BaseBehavior r0, AppBarLayout r1) {
            return r0.childrenHaveScrollFlags(r1);
        }

        public static /* synthetic */ int access$200(BaseBehavior r0) {
            return r0.offsetDelta;
        }

        private void addAccessibilityDelegateIfNeeded(CoordinatorLayout r2, T r3) {
            if (ViewCompat.hasAccessibilityDelegate(r2) == true) goto L6;
            ViewCompat.setAccessibilityDelegate(r2, new AnonymousClass2(this, r3, r2));
            return;
        }

        private void animateOffsetTo(CoordinatorLayout r3, T r4, int r5, float r6) {
            int r0 = Math.abs(getTopBottomOffsetForScrollingSibling() - r5);
            float r62 = Math.abs(r6);
            if (r62 <= 0.0f) goto L5;
            int r63 = Math.round((r0 / r62) * 1000.0f) * 3;
        L6:
            animateOffsetWithDuration(r3, r4, r5, r63);
            return;
        L5:
            r63 = (int) (((r0 / r4.getHeight()) + 1.0f) * 150.0f);
            goto L6
        }

        private void animateOffsetWithDuration(CoordinatorLayout r4, T r5, int r6, int r7) {
            int r0 = getTopBottomOffsetForScrollingSibling();
            ValueAnimator r1 = this.offsetAnimator;
            if (r0 != r6) goto L9;
            if (r1 != null) goto L6;
            return;
        L6:
            if (r1.isRunning() == false) goto L15;
            this.offsetAnimator.cancel();
            return;
        L15:
            return;
        L9:
            if (r1 != null) goto L11;
            ValueAnimator r12 = new ValueAnimator();
            this.offsetAnimator = r12;
            r12.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
            this.offsetAnimator.addUpdateListener(new AnonymousClass1(this, r4, r5));
        L12:
            this.offsetAnimator.setDuration(Math.min(r7, 600));
            this.offsetAnimator.setIntValues(new int[]{r0, r6});
            this.offsetAnimator.start();
            return;
        L11:
            r1.cancel();
            goto L12
        }

        private int calculateSnapOffset(int r1, int r2, int r3) {
            if (r1 >= ((r2 + r3) / 2)) goto L5;
            return r2;
        L5:
            return r3;
        }

        private boolean canScrollChildren(CoordinatorLayout r1, T r2, View r3) {
            if (r2.hasScrollableChildren() == true) goto L5;
            return false;
        L5:
            if ((r1.getHeight() - r3.getHeight()) > r2.getHeight()) goto L10;
            return true;
        L10:
            return false;
        }

        private static boolean checkFlag(int r0, int r1) {
            if ((r0 & r1) != r1) goto L6;
            return true;
        L6:
            return false;
        }

        private boolean childrenHaveScrollFlags(AppBarLayout r4) {
            int r3 = r4.getChildCount();
            int r1 = 0;
        L3:
            if (r1 >= r3) goto L9;
            if (((LayoutParams) r4.getChildAt(r1).getLayoutParams()).scrollFlags != 0) goto L6;
            r1 = r1 + 1;
            goto L3
        L6:
            return true;
        L9:
            return false;
        }

        private View findFirstScrollingChild(CoordinatorLayout r4) {
            int r3 = r4.getChildCount();
            int r0 = 0;
        L3:
            if (r0 >= r3) goto L13;
            View r1 = r4.getChildAt(r0);
            if ((r1 instanceof NestedScrollingChild) == true) goto L12;
            if ((r1 instanceof AbsListView) == true) goto L12;
            if ((r1 instanceof ScrollView) == true) goto L12;
            r0 = r0 + 1;
        L12:
            return r1;
        L13:
            return null;
        }

        private static View getAppBarChildOnOffset(AppBarLayout r4, int r5) {
            int r52 = Math.abs(r5);
            int r0 = r4.getChildCount();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L10;
            View r2 = r4.getChildAt(r1);
            if (r52 < r2.getTop()) goto L9;
            if (r52 > r2.getBottom()) goto L9;
            return r2;
        L9:
            r1 = r1 + 1;
            goto L3
        L10:
            return null;
        }

        private int getChildIndexOnOffset(T r7, int r8) {
            int r6 = r7.getChildCount();
            int r0 = 0;
        L3:
            if (r0 >= r6) goto L12;
            View r1 = r7.getChildAt(r0);
            int r2 = r1.getTop();
            int r3 = r1.getBottom();
            LayoutParams r12 = (LayoutParams) r1.getLayoutParams();
            if (checkFlag(r12.getScrollFlags(), 32) == false) goto L7;
            r2 = r2 - ((LinearLayout.LayoutParams) r12).topMargin;
            r3 = r3 + ((LinearLayout.LayoutParams) r12).bottomMargin;
        L7:
            int r13 = -r8;
            if (r2 > r13) goto L11;
            if (r3 < r13) goto L11;
            return r0;
        L11:
            r0 = r0 + 1;
            goto L3
        L12:
            return -1;
        }

        private View getChildWithScrollingBehavior(CoordinatorLayout r4) {
            int r3 = r4.getChildCount();
            int r0 = 0;
        L3:
            if (r0 >= r3) goto L8;
            View r1 = r4.getChildAt(r0);
            if ((((CoordinatorLayout.LayoutParams) r1.getLayoutParams()).getBehavior() instanceof ScrollingViewBehavior) == true) goto L6;
            r0 = r0 + 1;
            goto L3
        L6:
            return r1;
        L8:
            return null;
        }

        private int interpolateOffset(T r8, int r9) {
            int r7 = Math.abs(r9);
            int r0 = r8.getChildCount();
            int r1 = 0;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L21;
            View r3 = r8.getChildAt(r2);
            LayoutParams r4 = (LayoutParams) r3.getLayoutParams();
            Interpolator r5 = r4.getScrollInterpolator();
            if (r7 < r3.getTop()) goto L20;
            if (r7 > r3.getBottom()) goto L20;
            if (r5 == null) goto L21;
            int r02 = r4.getScrollFlags();
            if ((r02 & 1) == 0) goto L15;
            r1 = (r3.getHeight() + ((LinearLayout.LayoutParams) r4).topMargin) + ((LinearLayout.LayoutParams) r4).bottomMargin;
            if ((r02 & 2) == 0) goto L15;
            r1 = r1 - r3.getMinimumHeight();
        L15:
            if (r3.getFitsSystemWindows() == false) goto L17;
            r1 = r1 - r8.getTopInset();
        L17:
            if (r1 <= 0) goto L21;
            float r82 = r1;
            return Integer.signum(r9) * (r3.getTop() + Math.round(r82 * r5.getInterpolation((r7 - r3.getTop()) / r82)));
        L20:
            r2 = r2 + 1;
        L21:
            return r9;
        }

        private boolean shouldJumpElevationState(CoordinatorLayout r4, T r5) {
            List<View> r3 = r4.getDependents(r5);
            int r42 = r3.size();
            int r0 = 0;
        L3:
            if (r0 >= r42) goto L12;
            CoordinatorLayout.Behavior r1 = ((CoordinatorLayout.LayoutParams) r3.get(r0).getLayoutParams()).getBehavior();
            if ((r1 instanceof ScrollingViewBehavior) == true) goto L7;
            r0 = r0 + 1;
            goto L3
        L7:
            if (((ScrollingViewBehavior) r1).getOverlayTop() == 0) goto L10;
            return true;
        L10:
            return false;
        L12:
            return false;
        }

        private void snapToChildIfNeeded(CoordinatorLayout r9, T r10) {
            int r0 = r10.getTopInset() + r10.getPaddingTop();
            int r1 = getTopBottomOffsetForScrollingSibling() - r0;
            int r2 = getChildIndexOnOffset(r10, r1);
            if (r2 < 0) goto L27;
            View r3 = r10.getChildAt(r2);
            LayoutParams r4 = (LayoutParams) r3.getLayoutParams();
            int r5 = r4.getScrollFlags();
            if ((r5 & 17) != 17) goto L28;
            int r6 = -r3.getTop();
            int r7 = -r3.getBottom();
            if (r2 != 0) goto L14;
            if (r10.getFitsSystemWindows() == false) goto L14;
            if (r3.getFitsSystemWindows() == false) goto L14;
            r6 = r6 - r10.getTopInset();
        L14:
            if (checkFlag(r5, 2) == false) goto L17;
            r7 = r7 + r3.getMinimumHeight();
        L23:
            if (checkFlag(r5, 32) == false) goto L25;
            r6 = r6 + ((LinearLayout.LayoutParams) r4).topMargin;
            r7 = r7 - ((LinearLayout.LayoutParams) r4).bottomMargin;
        L25:
            animateOffsetTo(r9, r10, MathUtils.clamp(calculateSnapOffset(r1, r7, r6) + r0, -r10.getTotalScrollRange(), 0), 0.0f);
            return;
        L17:
            if (checkFlag(r5, 5) == false) goto L23;
            int r22 = r3.getMinimumHeight() + r7;
            if (r1 >= r22) goto L21;
            r6 = r22;
            goto L23
        L21:
            r7 = r22;
            goto L23
        L28:
            return;
        }

        private void updateAppBarLayoutDrawableState(CoordinatorLayout r6, T r7, int r8, int r9, boolean r10) {
            View r0 = getAppBarChildOnOffset(r7, r8);
            boolean r1 = false;
            if (r0 == null) goto L19;
            int r2 = ((LayoutParams) r0.getLayoutParams()).getScrollFlags();
            if ((r2 & 1) == 0) goto L19;
            int r3 = r0.getMinimumHeight();
            if (r9 <= 0) goto L14;
            if ((r2 & 12) == 0) goto L14;
            if ((-r8) < ((r0.getBottom() - r3) - r7.getTopInset())) goto L19;
        L12:
            r1 = true;
        L14:
            if ((r2 & 2) == 0) goto L19;
            if ((-r8) >= ((r0.getBottom() - r3) - r7.getTopInset())) goto L12;
        L19:
            if (r7.isLiftOnScroll() == false) goto L21;
            r1 = r7.shouldLift(findFirstScrollingChild(r6));
        L21:
            boolean r82 = r7.setLiftedState(r1);
            if (r10 == true) goto L27;
            if (r82 == true) goto L25;
            return;
        L25:
            if (shouldJumpElevationState(r6, r7) == true) goto L27;
            return;
        L27:
            if (r7.getBackground() == null) goto L30;
            r7.getBackground().jumpToCurrentState();
        L30:
            if (r7.getForeground() == null) goto L33;
            r7.getForeground().jumpToCurrentState();
        L33:
            if (r7.getStateListAnimator() == null) goto L38;
            r7.getStateListAnimator().jumpToCurrentState();
            return;
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public /* bridge */ /* synthetic */ boolean canDragView(View r1) {
            return canDragView((AppBarLayout) r1);
        }

        public boolean canDragView(T r2) {
            BaseDragCallback r0 = this.onDragCallback;
            if (r0 != null) goto L5;
            WeakReference<View> r1 = this.lastNestedScrollingChildRef;
            if (r1 == null) goto L17;
            View r12 = r1.get();
            if (r12 != null) goto L11;
            return false;
        L11:
            if (r12.isShown() == true) goto L13;
            return false;
        L13:
            if (r12.canScrollVertically(-1) == true) goto L19;
            return true;
        L19:
            return false;
        L17:
            return true;
        L5:
            return r0.canDrag(r2);
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public /* bridge */ /* synthetic */ int getMaxDragOffset(View r1) {
            return getMaxDragOffset((AppBarLayout) r1);
        }

        public int getMaxDragOffset(T r1) {
            return (-r1.getDownNestedScrollRange()) + r1.getTopInset();
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public /* bridge */ /* synthetic */ int getScrollRangeForDragFling(View r1) {
            return getScrollRangeForDragFling((AppBarLayout) r1);
        }

        public int getScrollRangeForDragFling(T r1) {
            return r1.getTotalScrollRange();
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public int getTopBottomOffsetForScrollingSibling() {
            return getTopAndBottomOffset() + this.offsetDelta;
        }

        public boolean isOffsetAnimatorRunning() {
            ValueAnimator r0 = this.offsetAnimator;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.isRunning() == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public /* bridge */ /* synthetic */ void onFlingFinished(CoordinatorLayout r1, View r2) {
            onFlingFinished(r1, (AppBarLayout) r2);
        }

        public void onFlingFinished(CoordinatorLayout r2, T r3) {
            snapToChildIfNeeded(r2, r3);
            if (r3.isLiftOnScroll() == false) goto L6;
            r3.setLiftedState(r3.shouldLift(findFirstScrollingChild(r2)));
            return;
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout r1, View r2, int r3) {
            return onLayoutChild(r1, (AppBarLayout) r2, r3);
        }

        public boolean onLayoutChild(CoordinatorLayout r8, T r9, int r10) {
            boolean r102 = super.onLayoutChild(r8, r9, r10);
            int r0 = r9.getPendingAction();
            SavedState r1 = this.savedState;
            if (r1 != null) goto L5;
        L18:
            if (r0 != 0) goto L20;
        L34:
            r9.resetPendingAction();
            this.savedState = null;
            setTopAndBottomOffset(MathUtils.clamp(getTopAndBottomOffset(), -r9.getTotalScrollRange(), 0));
            updateAppBarLayoutDrawableState(r8, r9, getTopAndBottomOffset(), 0, true);
            r9.onOffsetChanged(getTopAndBottomOffset());
            addAccessibilityDelegateIfNeeded(r8, r9);
            return r102;
        L20:
            if ((r0 & 4) == 0) goto L22;
            boolean r12 = true;
        L24:
            if ((r0 & 2) == 0) goto L30;
            int r02 = -r9.getUpNestedPreScrollRange();
            if (r12 == false) goto L28;
            animateOffsetTo(r8, r9, r02, 0.0f);
            goto L34
        L28:
            setHeaderTopBottomOffset(r8, r9, r02);
            goto L34
        L30:
            if ((r0 & 1) == 0) goto L34;
            if (r12 == false) goto L33;
            animateOffsetTo(r8, r9, 0, 0.0f);
            goto L34
        L33:
            setHeaderTopBottomOffset(r8, r9, 0);
            goto L34
        L22:
            r12 = false;
            goto L24
        L5:
            if ((r0 & 8) != 0) goto L18;
            if (r1.fullyScrolled == false) goto L10;
            setHeaderTopBottomOffset(r8, r9, -r9.getTotalScrollRange());
            goto L34
        L10:
            if (r1.fullyExpanded == false) goto L12;
            setHeaderTopBottomOffset(r8, r9, 0);
            goto L34
        L12:
            View r03 = r9.getChildAt(r1.firstVisibleChildIndex);
            int r13 = -r03.getBottom();
            if (this.savedState.firstVisibleChildAtMinimumHeight == false) goto L16;
            int r04 = r03.getMinimumHeight() + r9.getTopInset();
        L17:
            setHeaderTopBottomOffset(r8, r9, r13 + r04);
            goto L34
        L16:
            r04 = Math.round(r03.getHeight() * this.savedState.firstVisibleChildPercentageShown);
            goto L17
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onMeasureChild(CoordinatorLayout r1, View r2, int r3, int r4, int r5, int r6) {
            return onMeasureChild(r1, (AppBarLayout) r2, r3, r4, r5, r6);
        }

        public boolean onMeasureChild(CoordinatorLayout r8, T r9, int r10, int r11, int r12, int r13) {
            if (((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.LayoutParams) r9.getLayoutParams())).height != (-2)) goto L7;
            r8.onMeasureChild(r9, r10, r11, View.MeasureSpec.makeMeasureSpec(0, 0), r13);
            return true;
        L7:
            return super.onMeasureChild(r8, r9, r10, r11, r12, r13);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ void onNestedPreScroll(CoordinatorLayout r1, View r2, View r3, int r4, int r5, int[] r6, int r7) {
            onNestedPreScroll(r1, (AppBarLayout) r2, r3, r4, r5, r6, r7);
        }

        public void onNestedPreScroll(CoordinatorLayout r7, T r8, View r9, int r10, int r11, int[] r12, int r13) {
            if (r11 == 0) goto L9;
            if (r11 >= 0) goto L6;
            int r102 = -r8.getTotalScrollRange();
            int r132 = r8.getDownNestedPreScrollRange() + r102;
        L5:
            int r4 = r102;
            int r5 = r132;
            if (r4 == r5) goto L9;
            T r2 = r8;
            r12[1] = scroll(r7, r2, r11, r4, r5);
        L11:
            if (r2.isLiftOnScroll() == false) goto L14;
            r2.setLiftedState(r2.shouldLift(r9));
            return;
        L14:
            return;
        L6:
            r102 = -r8.getUpNestedPreScrollRange();
            r132 = 0;
        L9:
            r2 = r8;
            goto L11
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ void onNestedScroll(CoordinatorLayout r1, View r2, View r3, int r4, int r5, int r6, int r7, int r8, int[] r9) {
            onNestedScroll(r1, (AppBarLayout) r2, r3, r4, r5, r6, r7, r8, r9);
        }

        public void onNestedScroll(CoordinatorLayout r7, T r8, View r9, int r10, int r11, int r12, int r13, int r14, int[] r15) {
            if (r13 >= 0) goto L4;
            BaseBehavior<T> r0 = this;
            CoordinatorLayout r1 = r7;
            T r2 = r8;
            int r3 = r13;
            r15[1] = r0.scroll(r1, r2, r3, -r8.getDownNestedScrollRange(), 0);
        L5:
            if (r3 != 0) goto L8;
            r0.addAccessibilityDelegateIfNeeded(r1, r2);
            return;
        L8:
            return;
        L4:
            r0 = this;
            r1 = r7;
            r2 = r8;
            r3 = r13;
            goto L5
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ void onRestoreInstanceState(CoordinatorLayout r1, View r2, Parcelable r3) {
            onRestoreInstanceState(r1, (AppBarLayout) r2, r3);
        }

        public void onRestoreInstanceState(CoordinatorLayout r2, T r3, Parcelable r4) {
            if ((r4 instanceof SavedState) == false) goto L6;
            restoreScrollState((SavedState) r4, true);
            super.onRestoreInstanceState(r2, r3, this.savedState.getSuperState());
            return;
        L6:
            super.onRestoreInstanceState(r2, r3, r4);
            this.savedState = null;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ Parcelable onSaveInstanceState(CoordinatorLayout r1, View r2) {
            return onSaveInstanceState(r1, (AppBarLayout) r2);
        }

        public Parcelable onSaveInstanceState(CoordinatorLayout r1, T r2) {
            Parcelable r12 = super.onSaveInstanceState(r1, r2);
            SavedState r0 = saveScrollState(r12, r2);
            if (r0 != null) goto L5;
            return r12;
        L5:
            return r0;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onStartNestedScroll(CoordinatorLayout r1, View r2, View r3, View r4, int r5, int r6) {
            return onStartNestedScroll(r1, (AppBarLayout) r2, r3, r4, r5, r6);
        }

        public boolean onStartNestedScroll(CoordinatorLayout r1, T r2, View r3, View r4, int r5, int r6) {
            if ((r5 & 2) != 0) goto L5;
        L11:
            boolean r12 = false;
        L12:
            if (r12 == false) goto L16;
            ValueAnimator r22 = this.offsetAnimator;
            if (r22 == null) goto L16;
            r22.cancel();
        L16:
            this.lastNestedScrollingChildRef = null;
            this.lastStartedType = r6;
            return r12;
        L5:
            if (r2.isLiftOnScroll() == false) goto L7;
        L10:
            r12 = true;
            goto L12
        L7:
            if (r2.isLifted() == true) goto L10;
            if (canScrollChildren(r1, r2, r3) == false) goto L11;
            goto L10
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ void onStopNestedScroll(CoordinatorLayout r1, View r2, View r3, int r4) {
            onStopNestedScroll(r1, (AppBarLayout) r2, r3, r4);
        }

        public void onStopNestedScroll(CoordinatorLayout r2, T r3, View r4, int r5) {
            if (this.lastStartedType != 0) goto L5;
        L6:
            snapToChildIfNeeded(r2, r3);
            if (r3.isLiftOnScroll() == false) goto L9;
            r3.setLiftedState(r3.shouldLift(r4));
        L9:
            this.lastNestedScrollingChildRef = new WeakReference(r4);
            return;
        L5:
            if (r5 != 1) goto L9;
            goto L6
        }

        public void restoreScrollState(SavedState r2, boolean r3) {
            if (this.savedState == null) goto L7;
            if (r3 == true) goto L7;
            return;
        L7:
            this.savedState = r2;
        }

        public SavedState saveScrollState(Parcelable r7, T r8) {
            int r6 = getTopAndBottomOffset();
            int r0 = r8.getChildCount();
            boolean r1 = false;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L26;
            View r3 = r8.getChildAt(r2);
            int r4 = r3.getBottom() + r6;
            if ((r3.getTop() + r6) > 0) goto L25;
            if (r4 < 0) goto L25;
            if (r7 != null) goto L10;
            r7 = AbsSavedState.EMPTY_STATE;
        L10:
            SavedState r02 = new SavedState(r7);
            if (r6 != 0) goto L13;
            boolean r5 = true;
        L14:
            r02.fullyExpanded = r5;
            if (r5 == false) goto L17;
        L19:
            boolean r62 = false;
        L20:
            r02.fullyScrolled = r62;
            r02.firstVisibleChildIndex = r2;
            if (r4 != (r3.getMinimumHeight() + r8.getTopInset())) goto L23;
            r1 = true;
        L23:
            r02.firstVisibleChildAtMinimumHeight = r1;
            r02.firstVisibleChildPercentageShown = r4 / r3.getHeight();
            return r02;
        L17:
            if ((-r6) < r8.getTotalScrollRange()) goto L19;
            r62 = true;
            goto L20
        L13:
            r5 = false;
        L25:
            r2 = r2 + 1;
            goto L3
        L26:
            return null;
        }

        public void setDragCallback(BaseDragCallback r1) {
            this.onDragCallback = r1;
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public /* bridge */ /* synthetic */ int setHeaderTopBottomOffset(CoordinatorLayout r1, View r2, int r3, int r4, int r5) {
            return setHeaderTopBottomOffset(r1, (AppBarLayout) r2, r3, r4, r5);
        }

        public int setHeaderTopBottomOffset(CoordinatorLayout r7, T r8, int r9, int r10, int r11) {
            int r0 = getTopBottomOffsetForScrollingSibling();
            int r1 = 0;
            if (r10 == 0) goto L30;
            if (r0 < r10) goto L30;
            if (r0 > r11) goto L30;
            int r92 = MathUtils.clamp(r9, r10, r11);
            if (r0 != r92) goto L9;
        L31:
            addAccessibilityDelegateIfNeeded(r7, r8);
            return r1;
        L9:
            if (r8.hasChildWithInterpolator() == false) goto L11;
            int r102 = interpolateOffset(r8, r92);
        L12:
            boolean r112 = setTopAndBottomOffset(r102);
            int r2 = r0 - r92;
            this.offsetDelta = r92 - r102;
            int r103 = 1;
            if (r112 == true) goto L15;
        L22:
            if (r112 == false) goto L24;
        L26:
            r8.onOffsetChanged(getTopAndBottomOffset());
            if (r92 >= r0) goto L29;
            r103 = -1;
        L29:
            updateAppBarLayoutDrawableState(r7, r8, r92, r103, false);
            r1 = r2;
            goto L31
        L24:
            if (r8.hasChildWithInterpolator() == false) goto L26;
            r7.dispatchDependentViewsChanged(r8);
        L15:
            if (r1 >= r8.getChildCount()) goto L22;
            LayoutParams r3 = (LayoutParams) r8.getChildAt(r1).getLayoutParams();
            ChildScrollEffect r4 = r3.getScrollEffect();
            if (r4 == null) goto L21;
            if ((r3.getScrollFlags() & 1) == 0) goto L21;
            r4.onOffsetChanged(r8, r8.getChildAt(r1), getTopAndBottomOffset());
        L21:
            r1 = r1 + 1;
            goto L15
        L11:
            r102 = r92;
        L30:
            this.offsetDelta = 0;
            goto L31
        }
    }

    public interface BaseOnOffsetChangedListener<T extends AppBarLayout> {
        void onOffsetChanged(T r1, int r2);
    }

    public static class Behavior extends BaseBehavior<AppBarLayout> {

        public static abstract class DragCallback extends BaseBehavior.BaseDragCallback<AppBarLayout> {
            public DragCallback() {
            }
        }

        public Behavior() {
        }

        public Behavior(Context r1, AttributeSet r2) {
            super(r1, r2);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ int getLeftAndRightOffset() {
            return super.getLeftAndRightOffset();
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ int getTopAndBottomOffset() {
            return super.getTopAndBottomOffset();
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean isHorizontalOffsetEnabled() {
            return super.isHorizontalOffsetEnabled();
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean isVerticalOffsetEnabled() {
            return super.isVerticalOffsetEnabled();
        }

        @Override // com.google.android.material.appbar.HeaderBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onInterceptTouchEvent(CoordinatorLayout r1, View r2, MotionEvent r3) {
            return super.onInterceptTouchEvent(r1, r2, r3);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout r1, AppBarLayout r2, int r3) {
            return super.onLayoutChild(r1, r2, r3);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ boolean onMeasureChild(CoordinatorLayout r1, AppBarLayout r2, int r3, int r4, int r5, int r6) {
            return super.onMeasureChild(r1, r2, r3, r4, r5, r6);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void onNestedPreScroll(CoordinatorLayout r1, AppBarLayout r2, View r3, int r4, int r5, int[] r6, int r7) {
            super.onNestedPreScroll(r1, r2, r3, r4, r5, r6, r7);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void onNestedScroll(CoordinatorLayout r1, AppBarLayout r2, View r3, int r4, int r5, int r6, int r7, int r8, int[] r9) {
            super.onNestedScroll(r1, r2, r3, r4, r5, r6, r7, r8, r9);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void onRestoreInstanceState(CoordinatorLayout r1, AppBarLayout r2, Parcelable r3) {
            super.onRestoreInstanceState(r1, r2, r3);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ Parcelable onSaveInstanceState(CoordinatorLayout r1, AppBarLayout r2) {
            return super.onSaveInstanceState(r1, r2);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ boolean onStartNestedScroll(CoordinatorLayout r1, AppBarLayout r2, View r3, View r4, int r5, int r6) {
            return super.onStartNestedScroll(r1, r2, r3, r4, r5, r6);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void onStopNestedScroll(CoordinatorLayout r1, AppBarLayout r2, View r3, int r4) {
            super.onStopNestedScroll(r1, r2, r3, r4);
        }

        @Override // com.google.android.material.appbar.HeaderBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onTouchEvent(CoordinatorLayout r1, View r2, MotionEvent r3) {
            return super.onTouchEvent(r1, r2, r3);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void setDragCallback(BaseBehavior.BaseDragCallback r1) {
            super.setDragCallback(r1);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ void setHorizontalOffsetEnabled(boolean r1) {
            super.setHorizontalOffsetEnabled(r1);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean setLeftAndRightOffset(int r1) {
            return super.setLeftAndRightOffset(r1);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean setTopAndBottomOffset(int r1) {
            return super.setTopAndBottomOffset(r1);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ void setVerticalOffsetEnabled(boolean r1) {
            super.setVerticalOffsetEnabled(r1);
        }
    }

    public static abstract class ChildScrollEffect {
        public ChildScrollEffect() {
        }

        public abstract void onOffsetChanged(AppBarLayout r1, View r2, float r3);
    }

    public static class CompressChildScrollEffect extends ChildScrollEffect {
        private static final float COMPRESS_DISTANCE_FACTOR = 0.3f;
        private final Rect ghostRect;
        private final Rect relativeRect;

        public CompressChildScrollEffect() {
            this.relativeRect = new Rect();
            this.ghostRect = new Rect();
        }

        private static void updateRelativeRect(Rect r0, AppBarLayout r1, View r2) {
            r2.getDrawingRect(r0);
            r1.offsetDescendantRectToMyCoords(r2, r0);
            r0.offset(0, -r1.getTopInset());
        }

        @Override // com.google.android.material.appbar.AppBarLayout.ChildScrollEffect
        public void onOffsetChanged(AppBarLayout r5, View r6, float r7) {
            updateRelativeRect(this.relativeRect, r5, r6);
            float r52 = this.relativeRect.top - Math.abs(r7);
            if (r52 > 0.0f) goto L10;
            float r0 = 1.0f - MathUtils.clamp(Math.abs(r52 / this.relativeRect.height()), 0.0f, 1.0f);
            float r53 = (-r52) - ((this.relativeRect.height() * COMPRESS_DISTANCE_FACTOR) * (1.0f - (r0 * r0)));
            r6.setTranslationY(r53);
            r6.getDrawingRect(this.ghostRect);
            this.ghostRect.offset(0, (int) (-r53));
            if (r53 < this.ghostRect.height()) goto L7;
            r6.setAlpha(0.0f);
        L8:
            r6.setClipBounds(this.ghostRect);
            return;
        L7:
            r6.setAlpha(1.0f);
            goto L8
        L10:
            r6.setClipBounds(null);
            r6.setTranslationY(0.0f);
            r6.setAlpha(1.0f);
        }
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        static final int COLLAPSIBLE_FLAGS = 10;
        static final int FLAG_QUICK_RETURN = 5;
        static final int FLAG_SNAP = 17;
        public static final int SCROLL_EFFECT_COMPRESS = 1;
        public static final int SCROLL_EFFECT_NONE = 0;
        public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
        public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
        public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
        public static final int SCROLL_FLAG_NO_SCROLL = 0;
        public static final int SCROLL_FLAG_SCROLL = 1;
        public static final int SCROLL_FLAG_SNAP = 16;
        public static final int SCROLL_FLAG_SNAP_MARGINS = 32;
        private ChildScrollEffect scrollEffect;
        int scrollFlags;
        Interpolator scrollInterpolator;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ScrollEffect {
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface ScrollFlags {
        }

        public LayoutParams(int r1, int r2) {
            super(r1, r2);
            this.scrollFlags = 1;
        }

        public LayoutParams(int r1, int r2, float r3) {
            super(r1, r2, r3);
            this.scrollFlags = 1;
        }

        public LayoutParams(Context r3, AttributeSet r4) {
            super(r3, r4);
            this.scrollFlags = 1;
            TypedArray r42 = r3.obtainStyledAttributes(r4, R.styleable.AppBarLayout_Layout);
            this.scrollFlags = r42.getInt(R.styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
            setScrollEffect(r42.getInt(R.styleable.AppBarLayout_Layout_layout_scrollEffect, 0));
            if (r42.hasValue(R.styleable.AppBarLayout_Layout_layout_scrollInterpolator) == false) goto L5;
            this.scrollInterpolator = android.view.animation.AnimationUtils.loadInterpolator(r3, r42.getResourceId(R.styleable.AppBarLayout_Layout_layout_scrollInterpolator, 0));
        L5:
            r42.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams r1) {
            super(r1);
            this.scrollFlags = 1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams r1) {
            super(r1);
            this.scrollFlags = 1;
        }

        public LayoutParams(LinearLayout.LayoutParams r1) {
            super(r1);
            this.scrollFlags = 1;
        }

        public LayoutParams(LayoutParams r2) {
            super(r2);
            this.scrollFlags = 1;
            this.scrollFlags = r2.scrollFlags;
            this.scrollEffect = r2.scrollEffect;
            this.scrollInterpolator = r2.scrollInterpolator;
        }

        private ChildScrollEffect createScrollEffectFromInt(int r1) {
            if (r1 == 1) goto L7;
            return null;
        L7:
            return new CompressChildScrollEffect();
        }

        public ChildScrollEffect getScrollEffect() {
            return this.scrollEffect;
        }

        public int getScrollFlags() {
            return this.scrollFlags;
        }

        public Interpolator getScrollInterpolator() {
            return this.scrollInterpolator;
        }

        public boolean isCollapsible() {
            int r2 = this.scrollFlags;
            if ((r2 & 1) == 1) goto L5;
            return false;
        L5:
            if ((r2 & 10) == 0) goto L9;
            return true;
        L9:
            return false;
        }

        public void setScrollEffect(int r1) {
            this.scrollEffect = createScrollEffectFromInt(r1);
        }

        public void setScrollEffect(ChildScrollEffect r1) {
            this.scrollEffect = r1;
        }

        public void setScrollFlags(int r1) {
            this.scrollFlags = r1;
        }

        public void setScrollInterpolator(Interpolator r1) {
            this.scrollInterpolator = r1;
        }
    }

    @Deprecated
    public interface LiftOnScrollListener {
        void onUpdate(float r1, int r2);
    }

    public static abstract class LiftOnScrollProgressListener {
        public LiftOnScrollProgressListener() {
        }

        public abstract void onUpdate(float r1, int r2, float r3);
    }

    public interface OnOffsetChangedListener extends BaseOnOffsetChangedListener<AppBarLayout> {
        @Override // com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        void onOffsetChanged(AppBarLayout r1, int r2);
    }

    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
        public ScrollingViewBehavior() {
        }

        public ScrollingViewBehavior(Context r2, AttributeSet r3) {
            super(r2, r3);
            TypedArray r22 = r2.obtainStyledAttributes(r3, R.styleable.ScrollingViewBehavior_Layout);
            setOverlayTop(r22.getDimensionPixelSize(R.styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
            r22.recycle();
        }

        private static int getAppBarLayoutOffset(AppBarLayout r1) {
            CoordinatorLayout.Behavior r12 = ((CoordinatorLayout.LayoutParams) r1.getLayoutParams()).getBehavior();
            if ((r12 instanceof BaseBehavior) == true) goto L5;
            return 0;
        L5:
            return ((BaseBehavior) r12).getTopBottomOffsetForScrollingSibling();
        }

        private void offsetChildAsNeeded(View r4, View r5) {
            CoordinatorLayout.Behavior r0 = ((CoordinatorLayout.LayoutParams) r5.getLayoutParams()).getBehavior();
            if ((r0 instanceof BaseBehavior) == false) goto L6;
            ViewCompat.offsetTopAndBottom(r4, (((r5.getBottom() - r4.getTop()) + BaseBehavior.access$200((BaseBehavior) r0)) + getVerticalLayoutGap()) - getOverlapPixelsForOffset(r5));
            return;
        }

        private void updateLiftedStateIfNeeded(View r1, View r2) {
            if ((r2 instanceof AppBarLayout) == false) goto L8;
            AppBarLayout r22 = (AppBarLayout) r2;
            if (r22.isLiftOnScroll() == false) goto L9;
            r22.setLiftedState(r22.shouldLift(r1));
            return;
        L9:
            return;
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public /* bridge */ /* synthetic */ View findFirstDependency(List r1) {
            return findFirstDependency(r1);
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public AppBarLayout findFirstDependency(List<View> r4) {
            int r3 = r4.size();
            int r0 = 0;
        L3:
            if (r0 >= r3) goto L9;
            View r1 = r4.get(r0);
            if ((r1 instanceof AppBarLayout) == true) goto L7;
            r0 = r0 + 1;
            goto L3
        L7:
            return (AppBarLayout) r1;
        L9:
            return null;
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ int getLeftAndRightOffset() {
            return super.getLeftAndRightOffset();
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public float getOverlapRatioForOffset(View r4) {
            if ((r4 instanceof AppBarLayout) == false) goto L13;
            AppBarLayout r42 = (AppBarLayout) r4;
            int r3 = r42.getTotalScrollRange();
            int r1 = r42.getDownNestedPreScrollRange();
            int r43 = getAppBarLayoutOffset(r42);
            if (r1 != 0) goto L7;
        L9:
            int r32 = r3 - r1;
            if (r32 == 0) goto L13;
            return (r43 / r32) + 1.0f;
        L7:
            if ((r3 + r43) > r1) goto L9;
            return 0.0f;
        L13:
            return 0.0f;
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public int getScrollRange(View r2) {
            if ((r2 instanceof AppBarLayout) == false) goto L7;
            return ((AppBarLayout) r2).getTotalScrollRange();
        L7:
            return super.getScrollRange(r2);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ int getTopAndBottomOffset() {
            return super.getTopAndBottomOffset();
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean isHorizontalOffsetEnabled() {
            return super.isHorizontalOffsetEnabled();
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean isVerticalOffsetEnabled() {
            return super.isVerticalOffsetEnabled();
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean layoutDependsOn(CoordinatorLayout r1, View r2, View r3) {
            return r3 instanceof AppBarLayout;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onDependentViewChanged(CoordinatorLayout r1, View r2, View r3) {
            offsetChildAsNeeded(r2, r3);
            updateLiftedStateIfNeeded(r2, r3);
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onDependentViewRemoved(CoordinatorLayout r1, View r2, View r3) {
            if ((r3 instanceof AppBarLayout) == false) goto L6;
            ViewCompat.setAccessibilityDelegate(r1, null);
            return;
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout r1, View r2, int r3) {
            return super.onLayoutChild(r1, r2, r3);
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onMeasureChild(CoordinatorLayout r1, View r2, int r3, int r4, int r5, int r6) {
            return super.onMeasureChild(r1, r2, r3, r4, r5, r6);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout r4, View r5, Rect r6, boolean r7) {
            AppBarLayout r0 = findFirstDependency(r4.getDependencies(r5));
            if (r0 == null) goto L8;
            Rect r2 = new Rect(r6);
            r2.offset(r5.getLeft(), r5.getTop());
            Rect r3 = this.tempRect1;
            r3.set(0, 0, r4.getWidth(), r4.getHeight());
            if (r3.contains(r2) == true) goto L8;
            r0.setExpanded(false, !r7);
            return true;
        L8:
            return false;
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ void setHorizontalOffsetEnabled(boolean r1) {
            super.setHorizontalOffsetEnabled(r1);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean setLeftAndRightOffset(int r1) {
            return super.setLeftAndRightOffset(r1);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean setTopAndBottomOffset(int r1) {
            return super.setTopAndBottomOffset(r1);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ void setVerticalOffsetEnabled(boolean r1) {
            super.setVerticalOffsetEnabled(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$PpQ1I3993Gy02bYDF0fSWxB_DpQ(AppBarLayout r4, MaterialShapeDrawable r5, ValueAnimator r6) {
        r4.getClass();
        float r62 = ((Float) r6.getAnimatedValue()).floatValue();
        r5.setElevation(r62);
        Drawable r0 = r4.statusBarForeground;
        if ((r0 instanceof MaterialShapeDrawable) == false) goto L5;
        ((MaterialShapeDrawable) r0).setElevation(r62);
    L5:
        Iterator<LiftOnScrollListener> r02 = r4.liftOnScrollListeners.iterator();
    L7:
        if (r02.hasNext() == false) goto L9;
        r02.next().onUpdate(r62, r5.getResolvedTintColor());
        goto L7
    L9:
        Iterator<LiftOnScrollProgressListener> r03 = r4.liftProgressListeners.iterator();
    L11:
        if (r03.hasNext() == false) goto L13;
        r03.next().onUpdate(r62, r5.getResolvedTintColor(), r62 / r4.appBarElevation);
        goto L11
    }

    public static /* synthetic */ void $r8$lambda$pWyguk4NB6CmY3Ase6MwHO_zXKU(AppBarLayout r3, ColorStateList r4, MaterialShapeDrawable r5, Integer r6, ValueAnimator r7) {
        r3.getClass();
        float r72 = ((Float) r7.getAnimatedValue()).floatValue();
        int r42 = MaterialColors.layer(r3.backgroundOriginalColor, r4.getDefaultColor(), r72);
        r5.setFillColor(ColorStateList.valueOf(r42));
        if (r3.statusBarForeground == null) goto L10;
        Integer r0 = r3.statusBarForegroundOriginalColor;
        if (r0 == null) goto L10;
        if (r0.equals(r6) == false) goto L10;
        r3.statusBarForeground.setTint(r42);
    L10:
        if (r3.liftOnScrollListeners.isEmpty() == true) goto L18;
        Iterator<LiftOnScrollListener> r62 = r3.liftOnScrollListeners.iterator();
    L13:
        if (r62.hasNext() == false) goto L18;
        LiftOnScrollListener r1 = r62.next();
        if (r5.getFillColor() == null) goto L13;
        r1.onUpdate(0.0f, r42);
    L18:
        if (r3.liftProgressListeners.isEmpty() == true) goto L23;
        Iterator<LiftOnScrollProgressListener> r32 = r3.liftProgressListeners.iterator();
    L21:
        if (r32.hasNext() == false) goto L30;
        r32.next().onUpdate(0.0f, r42, r72);
        goto L21
    L30:
        return;
    }

    static {
        DEF_STYLE_RES = R.style.Widget_Design_AppBarLayout;
    }

    public AppBarLayout(Context r2) {
        this(r2, null);
    }

    public AppBarLayout(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.appBarLayoutStyle);
    }

    public AppBarLayout(Context r8, AttributeSet r9, int r10) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r8, r9, r10, r4), r9, r10);
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
        this.pendingAction = 0;
        this.liftOnScrollListeners = new ArrayList();
        this.liftProgressListeners = new LinkedHashSet();
        Context r0 = getContext();
        setOrientation(1);
        if (getOutlineProvider() != ViewOutlineProvider.BACKGROUND) goto L5;
        ViewUtilsLollipop.setBoundsViewOutlineProvider(this);
    L5:
        ViewUtilsLollipop.setStateListAnimatorFromAttrs(this, r9, r10, r4);
        TypedArray r92 = ThemeEnforcement.obtainStyledAttributes(r0, r9, R.styleable.AppBarLayout, r10, r4, new int[0]);
        this.liftOnScrollColor = MaterialResources.getColorStateList(r0, r92, R.styleable.AppBarLayout_liftOnScrollColor);
        this.liftOnScrollColorDuration = MotionUtils.resolveThemeDuration(r0, R.attr.motionDurationMedium2, getResources().getInteger(R.integer.app_bar_elevation_anim_duration));
        this.liftOnScrollColorInterpolator = MotionUtils.resolveThemeInterpolator(r0, R.attr.motionEasingStandardInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
        if (r92.hasValue(R.styleable.AppBarLayout_expanded) == false) goto L9;
        setExpanded(r92.getBoolean(R.styleable.AppBarLayout_expanded, false), false, false);
    L9:
        if (r92.hasValue(R.styleable.AppBarLayout_elevation) == false) goto L11;
        ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, r92.getDimensionPixelSize(R.styleable.AppBarLayout_elevation, 0));
    L11:
        setBackground(r92.getDrawable(R.styleable.AppBarLayout_android_background));
        if (Build.VERSION.SDK_INT >= 26) goto L14;
    L19:
        this.appBarElevation = getResources().getDimension(R.dimen.design_appbar_elevation);
        this.liftOnScroll = r92.getBoolean(R.styleable.AppBarLayout_liftOnScroll, false);
        this.liftOnScrollTargetViewId = r92.getResourceId(R.styleable.AppBarLayout_liftOnScrollTargetViewId, -1);
        setStatusBarForeground(r92.getDrawable(R.styleable.AppBarLayout_statusBarForeground));
        r92.recycle();
        ViewCompat.setOnApplyWindowInsetsListener(this, new AnonymousClass1(this));
        return;
    L14:
        if (r92.hasValue(R.styleable.AppBarLayout_android_keyboardNavigationCluster) == false) goto L17;
        setKeyboardNavigationCluster(r92.getBoolean(R.styleable.AppBarLayout_android_keyboardNavigationCluster, false));
    L17:
        if (r92.hasValue(R.styleable.AppBarLayout_android_touchscreenBlocksFocus) == false) goto L19;
        setTouchscreenBlocksFocus(r92.getBoolean(R.styleable.AppBarLayout_android_touchscreenBlocksFocus, false));
        goto L19
    }

    private void clearLiftOnScrollTargetView() {
        WeakReference<View> r0 = this.liftOnScrollTargetView;
        if (r0 == null) goto L5;
        r0.clear();
    L5:
        this.liftOnScrollTargetView = null;
    }

    private Integer extractStatusBarForegroundColor() {
        Drawable r1 = this.statusBarForeground;
        if ((r1 instanceof MaterialShapeDrawable) == true) goto L5;
        ColorStateList r12 = DrawableUtils.getColorStateListOrNull(r1);
        if (r12 != null) goto L9;
        return null;
    L9:
        return Integer.valueOf(r12.getDefaultColor());
    L5:
        return Integer.valueOf(((MaterialShapeDrawable) r1).getResolvedTintColor());
    }

    private View findLiftOnScrollTargetView(View r4) {
        if (this.liftOnScrollTargetView != null) goto L15;
        int r0 = this.liftOnScrollTargetViewId;
        if (r0 == (-1)) goto L15;
        if (r4 == null) goto L8;
        View r42 = r4.findViewById(r0);
    L9:
        if (r42 == null) goto L11;
    L13:
        if (r42 == null) goto L15;
        this.liftOnScrollTargetView = new WeakReference(r42);
        goto L15
    L11:
        if ((getParent() instanceof ViewGroup) == false) goto L13;
        r42 = ((ViewGroup) getParent()).findViewById(this.liftOnScrollTargetViewId);
        goto L13
    L8:
        r42 = null;
    L15:
        WeakReference<View> r3 = this.liftOnScrollTargetView;
        if (r3 != null) goto L18;
        return null;
    L18:
        return r3.get();
    }

    private boolean hasCollapsibleChild() {
        int r0 = getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (((LayoutParams) getChildAt(r2).getLayoutParams()).isCollapsible() == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private void initializeLiftOnScrollWithColor(MaterialShapeDrawable r3, ColorStateList r4) {
        this.liftOnScrollColorUpdateListener = new AppBarLayout$$ExternalSyntheticLambda0(this, r4, r3, MaterialColors.getColorOrNull(getContext(), R.attr.colorSurface));
    }

    private void initializeLiftOnScrollWithElevation(Context r1, MaterialShapeDrawable r2) {
        r2.initializeElevationOverlay(r1);
        this.liftOnScrollColorUpdateListener = new AppBarLayout$$ExternalSyntheticLambda1(this, r2);
    }

    private void invalidateScrollRanges() {
        Behavior r0 = this.behavior;
        if (r0 != null) goto L5;
    L10:
        BaseBehavior.SavedState r02 = null;
    L11:
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
        if (r02 == null) goto L15;
        this.behavior.restoreScrollState(r02, false);
        return;
    L15:
        return;
    L5:
        if (this.totalScrollRange == (-1)) goto L10;
        if (this.pendingAction != 0) goto L10;
        r02 = r0.saveScrollState(AbsSavedState.EMPTY_STATE, this);
        goto L11
    }

    private boolean isLiftOnScrollCompatibleBackground() {
        return getBackground() instanceof MaterialShapeDrawable;
    }

    private MaterialShapeDrawable maybeConvertToMaterialShapeDrawable(Drawable r1) {
        if ((r1 instanceof MaterialShapeDrawable) == true) goto L5;
        ColorStateList r0 = DrawableUtils.getColorStateListOrNull(r1);
        if (r0 != null) goto L10;
        return null;
    L10:
        MaterialShapeDrawable r12 = new MaterialShapeDrawable();
        r12.setFillColor(r0);
        return r12;
    L5:
        return (MaterialShapeDrawable) r1;
    }

    private Drawable maybeCreateLiftOnScrollBackground(Context r3, Drawable r4) {
        MaterialShapeDrawable r0 = maybeConvertToMaterialShapeDrawable(r4);
        if (r0 != null) goto L5;
    L13:
        return r4;
    L5:
        if (r0.getFillColor() == null) goto L13;
        this.backgroundOriginalColor = r0.getFillColor().getDefaultColor();
        ColorStateList r42 = this.liftOnScrollColor;
        if (r42 == null) goto L11;
        initializeLiftOnScrollWithColor(r0, r42);
        return r0;
    L11:
        initializeLiftOnScrollWithElevation(r3, r0);
        return r0;
    }

    private void setExpanded(boolean r2, boolean r3, boolean r4) {
        if (r2 == false) goto L4;
        int r22 = 1;
    L5:
        int r0 = 0;
        if (r3 == false) goto L8;
        int r32 = 4;
    L9:
        int r23 = r22 | r32;
        if (r4 == false) goto L12;
        r0 = 8;
    L12:
        this.pendingAction = r23 | r0;
        requestLayout();
        return;
    L8:
        r32 = 0;
        goto L9
    L4:
        r22 = 2;
        goto L5
    }

    private boolean setLiftableState(boolean r2) {
        if (this.liftable == r2) goto L6;
        this.liftable = r2;
        refreshDrawableState();
        return true;
    L6:
        return false;
    }

    private boolean shouldDrawStatusBarForeground() {
        if (this.statusBarForeground != null) goto L5;
        return false;
    L5:
        if (getTopInset() <= 0) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean shouldOffsetFirstChild() {
        if (getChildCount() <= 0) goto L10;
        View r3 = getChildAt(0);
        if (r3.getVisibility() == 8) goto L10;
        if (r3.getFitsSystemWindows() == true) goto L10;
        return true;
    L10:
        return false;
    }

    private void startLiftOnScrollColorAnimation(float r3, float r4) {
        ValueAnimator r0 = this.liftOnScrollColorAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{r3, r4});
        this.liftOnScrollColorAnimator = r32;
        r32.setDuration(this.liftOnScrollColorDuration);
        this.liftOnScrollColorAnimator.setInterpolator(this.liftOnScrollColorInterpolator);
        ValueAnimator.AnimatorUpdateListener r33 = this.liftOnScrollColorUpdateListener;
        if (r33 == null) goto L8;
        this.liftOnScrollColorAnimator.addUpdateListener(r33);
    L8:
        this.liftOnScrollColorAnimator.start();
    }

    private void updateWillNotDraw() {
        setWillNotDraw(!shouldDrawStatusBarForeground());
    }

    @Deprecated
    public void addLiftOnScrollListener(LiftOnScrollListener r1) {
        this.liftOnScrollListeners.add(r1);
    }

    public void addLiftOnScrollProgressListener(LiftOnScrollProgressListener r1) {
        this.liftProgressListeners.add(r1);
    }

    public void addOnOffsetChangedListener(BaseOnOffsetChangedListener r2) {
        if (this.listeners != null) goto L5;
        this.listeners = new ArrayList();
    L5:
        if (r2 != null) goto L7;
        return;
    L7:
        if (this.listeners.contains(r2) == true) goto L11;
        this.listeners.add(r2);
        return;
    }

    public void addOnOffsetChangedListener(OnOffsetChangedListener r1) {
        addOnOffsetChangedListener(r1);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams r1) {
        return r1 instanceof LayoutParams;
    }

    @Deprecated
    public void clearLiftOnScrollListener() {
        this.liftOnScrollListeners.clear();
    }

    public void clearLiftOnScrollProgressListener() {
        this.liftProgressListeners.clear();
    }

    @Override // android.view.View
    public void draw(Canvas r4) {
        super.draw(r4);
        if (shouldDrawStatusBarForeground() == false) goto L6;
        int r0 = r4.save();
        r4.translate(0.0f, -this.currentOffset);
        this.statusBarForeground.draw(r4);
        r4.restoreToCount(r0);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] r0 = getDrawableState();
        Drawable r1 = this.statusBarForeground;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.isStateful() == true) goto L7;
        return;
    L7:
        if (r1.setState(r0) == false) goto L12;
        invalidateDrawable(r1);
        return;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ LinearLayout.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ LinearLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet r2) {
        return new LayoutParams(getContext(), r2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        if ((r1 instanceof LinearLayout.LayoutParams) == false) goto L7;
        return new LayoutParams((LinearLayout.LayoutParams) r1);
    L7:
        if ((r1 instanceof ViewGroup.MarginLayoutParams) == false) goto L11;
        return new LayoutParams((ViewGroup.MarginLayoutParams) r1);
    L11:
        return new LayoutParams(r1);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public CoordinatorLayout.Behavior<AppBarLayout> getBehavior() {
        Behavior r0 = new Behavior();
        this.behavior = r0;
        return r0;
    }

    public int getDownNestedPreScrollRange() {
        int r0 = this.downPreScrollRange;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        int r02 = getChildCount() - 1;
        int r2 = 0;
    L6:
        if (r02 < 0) goto L28;
        View r3 = getChildAt(r02);
        if (r3.getVisibility() == 8) goto L27;
        LayoutParams r4 = (LayoutParams) r3.getLayoutParams();
        int r5 = r3.getMeasuredHeight();
        int r6 = r4.scrollFlags;
        if ((r6 & 5) != 5) goto L25;
        int r7 = ((LinearLayout.LayoutParams) r4).topMargin + ((LinearLayout.LayoutParams) r4).bottomMargin;
        if ((r6 & 8) == 0) goto L17;
        int r42 = r3.getMinimumHeight();
    L15:
        int r72 = r7 + r42;
    L20:
        if (r02 == 0) goto L22;
    L24:
        r2 = r2 + r72;
        goto L27
    L22:
        if (r3.getFitsSystemWindows() == false) goto L24;
        r72 = Math.min(r72, r5 - getTopInset());
        goto L24
    L17:
        if ((r6 & 2) == 0) goto L19;
        r42 = r5 - r3.getMinimumHeight();
        goto L15
    L19:
        r72 = r7 + r5;
        goto L20
    L25:
        if (r2 > 0) goto L28;
    L27:
        r02 = r02 - 1;
    L28:
        int r03 = Math.max(0, r2);
        this.downPreScrollRange = r03;
        return r03;
    }

    public int getDownNestedScrollRange() {
        int r0 = this.downScrollRange;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        int r02 = getChildCount();
        int r2 = 0;
        int r3 = 0;
    L6:
        if (r2 >= r02) goto L16;
        View r4 = getChildAt(r2);
        if (r4.getVisibility() == 8) goto L15;
        LayoutParams r5 = (LayoutParams) r4.getLayoutParams();
        int r6 = r4.getMeasuredHeight() + (((LinearLayout.LayoutParams) r5).topMargin + ((LinearLayout.LayoutParams) r5).bottomMargin);
        int r52 = r5.scrollFlags;
        if ((r52 & 1) == 0) goto L16;
        r3 = r3 + r6;
        if ((r52 & 2) == 0) goto L15;
        r3 = r3 - r4.getMinimumHeight();
    L15:
        r2 = r2 + 1;
    L16:
        int r03 = Math.max(0, r3);
        this.downScrollRange = r03;
        return r03;
    }

    public int getLiftOnScrollTargetViewId() {
        return this.liftOnScrollTargetViewId;
    }

    public MaterialShapeDrawable getMaterialShapeBackground() {
        Drawable r1 = getBackground();
        if ((r1 instanceof MaterialShapeDrawable) == true) goto L5;
        return null;
    L5:
        return (MaterialShapeDrawable) r1;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int r0 = getTopInset();
        int r1 = getMinimumHeight();
        if (r1 == 0) goto L9;
        int r2 = (r1 * 2) + r0;
        if (r2 >= getHeight()) goto L8;
        return r2;
    L8:
        return r1 + r0;
    L9:
        int r12 = getChildCount();
        if (r12 < 1) goto L12;
        int r13 = getChildAt(r12 - 1).getMinimumHeight();
    L13:
        if (r13 == 0) goto L20;
        int r22 = (r13 * 2) + r0;
        if (r22 >= getHeight()) goto L18;
        return r22;
    L18:
        return r13 + r0;
    L20:
        return getHeight() / 3;
    L12:
        r13 = 0;
        goto L13
    }

    public int getPendingAction() {
        return this.pendingAction;
    }

    public Drawable getStatusBarForeground() {
        return this.statusBarForeground;
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    public final int getTopInset() {
        WindowInsetsCompat r0 = this.lastInsets;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getSystemWindowInsetTop();
    }

    public final int getTotalScrollRange() {
        int r0 = this.totalScrollRange;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        int r02 = getChildCount();
        int r2 = 0;
        int r3 = 0;
    L6:
        if (r2 >= r02) goto L21;
        View r4 = getChildAt(r2);
        if (r4.getVisibility() == 8) goto L20;
        LayoutParams r5 = (LayoutParams) r4.getLayoutParams();
        int r6 = r4.getMeasuredHeight();
        int r7 = r5.scrollFlags;
        if ((r7 & 1) == 0) goto L21;
        r3 = r3 + ((r6 + ((LinearLayout.LayoutParams) r5).topMargin) + ((LinearLayout.LayoutParams) r5).bottomMargin);
        if (r2 != 0) goto L18;
        if (r4.getFitsSystemWindows() == false) goto L18;
        r3 = r3 - getTopInset();
    L18:
        if ((r7 & 2) == 0) goto L20;
        r3 = r3 - r4.getMinimumHeight();
    L20:
        r2 = r2 + 1;
    L21:
        int r03 = Math.max(0, r3);
        this.totalScrollRange = r03;
        return r03;
    }

    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    public boolean hasChildWithInterpolator() {
        return this.haveChildWithInterpolator;
    }

    public boolean hasScrollableChildren() {
        if (getTotalScrollRange() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isLiftOnScroll() {
        return this.liftOnScroll;
    }

    public boolean isLifted() {
        return this.lifted;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int r5) {
        if (this.tmpStatesArray != null) goto L5;
        this.tmpStatesArray = new int[4];
    L5:
        int[] r0 = this.tmpStatesArray;
        int[] r52 = super.onCreateDrawableState(r5 + r0.length);
        boolean r1 = this.liftable;
        int r2 = R.attr.state_liftable;
        if (r1 == true) goto L9;
        r2 = -r2;
    L9:
        r0[0] = r2;
        if (r1 == true) goto L12;
    L14:
        int r22 = -R.attr.state_lifted;
    L15:
        r0[1] = r22;
        int r23 = R.attr.state_collapsible;
        if (r1 == true) goto L19;
        r23 = -r23;
    L19:
        r0[2] = r23;
        if (r1 == true) goto L22;
    L24:
        int r4 = -R.attr.state_collapsed;
    L25:
        r0[3] = r4;
        return View.mergeDrawableStates(r52, r0);
    L22:
        if (this.lifted == false) goto L24;
        r4 = R.attr.state_collapsed;
        goto L25
    L12:
        if (this.lifted == false) goto L14;
        r22 = R.attr.state_lifted;
        goto L15
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearLiftOnScrollTargetView();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        boolean r22 = true;
        if (getFitsSystemWindows() == true) goto L5;
    L9:
        invalidateScrollRanges();
        this.haveChildWithInterpolator = false;
        int r32 = getChildCount();
        int r42 = 0;
    L10:
        if (r42 >= r32) goto L15;
        if (((LayoutParams) getChildAt(r42).getLayoutParams()).getScrollInterpolator() != null) goto L13;
        r42 = r42 + 1;
        goto L10
    L13:
        this.haveChildWithInterpolator = true;
    L15:
        Drawable r33 = this.statusBarForeground;
        if (r33 == null) goto L19;
        r33.setBounds(0, 0, getWidth(), getTopInset());
    L19:
        if (this.liftableOverride == false) goto L21;
        return;
    L21:
        if (this.liftOnScroll == false) goto L23;
    L26:
        setLiftableState(r22);
        return;
    L23:
        if (hasCollapsibleChild() == true) goto L26;
        r22 = false;
        goto L26
    L5:
        if (shouldOffsetFirstChild() == false) goto L9;
        int r12 = getTopInset();
        int r34 = getChildCount() - 1;
    L7:
        if (r34 < 0) goto L9;
        ViewCompat.offsetTopAndBottom(getChildAt(r34), r12);
        r34 = r34 - 1;
        goto L7
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(r3, r4);
        int r32 = View.MeasureSpec.getMode(r4);
        if (r32 != 1073741824) goto L5;
    L15:
        invalidateScrollRanges();
        return;
    L5:
        if (getFitsSystemWindows() == false) goto L15;
        if (shouldOffsetFirstChild() == false) goto L15;
        int r0 = getMeasuredHeight();
        if (r32 == Integer.MIN_VALUE) goto L13;
        if (r32 != 0) goto L14;
        r0 = r0 + getTopInset();
    L14:
        setMeasuredDimension(getMeasuredWidth(), r0);
        goto L15
    L13:
        r0 = MathUtils.clamp(getMeasuredHeight() + getTopInset(), 0, View.MeasureSpec.getSize(r4));
        goto L14
    }

    public void onOffsetChanged(int r4) {
        this.currentOffset = r4;
        if (willNotDraw() == true) goto L5;
        postInvalidateOnAnimation();
    L5:
        List<BaseOnOffsetChangedListener> r0 = this.listeners;
        if (r0 == null) goto L13;
        int r02 = r0.size();
        int r1 = 0;
    L8:
        if (r1 >= r02) goto L17;
        BaseOnOffsetChangedListener r2 = this.listeners.get(r1);
        if (r2 == null) goto L12;
        r2.onOffsetChanged(this, r4);
    L12:
        r1 = r1 + 1;
        goto L8
    L17:
        return;
    }

    public WindowInsetsCompat onWindowInsetChanged(WindowInsetsCompat r3) {
        if (getFitsSystemWindows() == false) goto L5;
        WindowInsetsCompat r0 = r3;
    L7:
        if (ObjectsCompat.equals(this.lastInsets, r0) == true) goto L9;
        this.lastInsets = r0;
        updateWillNotDraw();
        requestLayout();
    L9:
        return r3;
    L5:
        r0 = null;
        goto L7
    }

    @Deprecated
    public boolean removeLiftOnScrollListener(LiftOnScrollListener r1) {
        return this.liftOnScrollListeners.remove(r1);
    }

    public boolean removeLiftOnScrollProgressListener(LiftOnScrollProgressListener r1) {
        return this.liftProgressListeners.remove(r1);
    }

    public void removeOnOffsetChangedListener(BaseOnOffsetChangedListener r1) {
        List<BaseOnOffsetChangedListener> r0 = this.listeners;
        if (r0 == null) goto L7;
        if (r1 == null) goto L8;
        r0.remove(r1);
        return;
    L8:
        return;
    }

    public void removeOnOffsetChangedListener(OnOffsetChangedListener r1) {
        removeOnOffsetChangedListener(r1);
    }

    public void resetPendingAction() {
        this.pendingAction = 0;
    }

    @Override // android.view.View
    public void setBackground(Drawable r2) {
        super.setBackground(maybeCreateLiftOnScrollBackground(getContext(), r2));
    }

    @Override // android.view.View
    public void setElevation(float r1) {
        super.setElevation(r1);
        MaterialShapeUtils.setElevation(this, r1);
    }

    public void setExpanded(boolean r2) {
        setExpanded(r2, isLaidOut());
    }

    public void setExpanded(boolean r2, boolean r3) {
        setExpanded(r2, r3, true);
    }

    public void setLiftOnScroll(boolean r1) {
        this.liftOnScroll = r1;
    }

    public void setLiftOnScrollColor(ColorStateList r2) {
        if (this.liftOnScrollColor == r2) goto L6;
        this.liftOnScrollColor = r2;
        setBackground(getBackground());
        return;
    }

    public void setLiftOnScrollTargetView(View r2) {
        this.liftOnScrollTargetViewId = -1;
        if (r2 != null) goto L6;
        clearLiftOnScrollTargetView();
        return;
    L6:
        this.liftOnScrollTargetView = new WeakReference(r2);
    }

    public void setLiftOnScrollTargetViewId(int r1) {
        this.liftOnScrollTargetViewId = r1;
        clearLiftOnScrollTargetView();
    }

    public boolean setLiftable(boolean r2) {
        this.liftableOverride = true;
        return setLiftableState(r2);
    }

    public void setLiftableOverrideEnabled(boolean r1) {
        this.liftableOverride = r1;
    }

    public boolean setLifted(boolean r2) {
        return setLiftedState(r2, true);
    }

    public boolean setLiftedState(boolean r2) {
        return setLiftedState(r2, !this.liftableOverride);
    }

    public boolean setLiftedState(boolean r3, boolean r4) {
        if (r4 == true) goto L4;
        return false;
    L4:
        if (this.lifted == r3) goto L31;
        this.lifted = r3;
        refreshDrawableState();
        if (isLiftOnScrollCompatibleBackground() == false) goto L28;
        float r0 = 0.0f;
        if (this.liftOnScrollColor == null) goto L17;
        if (r3 == false) goto L12;
        float r1 = 0.0f;
    L13:
        if (r3 == false) goto L15;
        r0 = 1.0f;
    L15:
        startLiftOnScrollColorAnimation(r1, r0);
        return true;
    L12:
        r1 = 1.0f;
        goto L13
    L17:
        if (this.liftOnScroll == false) goto L30;
        if (r3 == false) goto L20;
        float r42 = 0.0f;
    L21:
        if (r3 == false) goto L23;
        r0 = this.appBarElevation;
    L23:
        startLiftOnScrollColorAnimation(r42, r0);
        return true;
    L20:
        r42 = this.appBarElevation;
        goto L21
    L30:
        return true;
    L28:
        return true;
    L31:
        return false;
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int r2) {
        if (r2 != 1) goto L6;
        super.setOrientation(r2);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("AppBarLayout is always vertical and does not support horizontal orientation");
    }

    public void setPendingAction(int r1) {
        this.pendingAction = r1;
    }

    public void setStatusBarForeground(Drawable r3) {
        Drawable r0 = this.statusBarForeground;
        if (r0 == r3) goto L21;
        Drawable r1 = null;
        if (r0 == null) goto L7;
        r0.setCallback(null);
    L7:
        if (r3 == null) goto L9;
        r1 = r3.mutate();
    L9:
        this.statusBarForeground = r1;
        this.statusBarForegroundOriginalColor = extractStatusBarForegroundColor();
        Drawable r32 = this.statusBarForeground;
        if (r32 != null) goto L12;
    L19:
        updateWillNotDraw();
        postInvalidateOnAnimation();
        return;
    L12:
        if (r32.isStateful() == false) goto L14;
        this.statusBarForeground.setState(getDrawableState());
    L14:
        DrawableCompat.setLayoutDirection(this.statusBarForeground, getLayoutDirection());
        Drawable r33 = this.statusBarForeground;
        if (getVisibility() != 0) goto L17;
        boolean r02 = true;
    L18:
        r33.setVisible(r02, false);
        this.statusBarForeground.setCallback(this);
        goto L19
    L17:
        r02 = false;
        goto L18
    }

    public void setStatusBarForegroundColor(int r2) {
        setStatusBarForeground(new ColorDrawable(r2));
    }

    public void setStatusBarForegroundResource(int r2) {
        setStatusBarForeground(AppCompatResources.getDrawable(getContext(), r2));
    }

    @Deprecated
    public void setTargetElevation(float r1) {
        ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, r1);
    }

    @Override // android.view.View
    public void setVisibility(int r2) {
        super.setVisibility(r2);
        if (r2 != 0) goto L5;
        boolean r22 = true;
    L6:
        Drawable r1 = this.statusBarForeground;
        if (r1 == null) goto L10;
        r1.setVisible(r22, false);
        return;
    L10:
        return;
    L5:
        r22 = false;
        goto L6
    }

    public boolean shouldLift(View r1) {
        View r0 = findLiftOnScrollTargetView(r1);
        if (r0 == null) goto L6;
        r1 = r0;
    L6:
        if (r1 != null) goto L8;
        return false;
    L8:
        if (r1.canScrollVertically(-1) == false) goto L10;
        return true;
    L10:
        if (r1.getScrollY() <= 0) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == false) goto L5;
        return true;
    L5:
        if (r2 == this.statusBarForeground) goto L11;
        return false;
    L11:
        return true;
    }
}
