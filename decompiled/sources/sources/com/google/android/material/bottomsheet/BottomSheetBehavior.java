package com.google.android.material.bottomsheet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.RoundedCorner;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import androidx.activity.BackEventCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.core.view.insets.GradientProtection;
import androidx.core.view.insets.Protection;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.MediaError;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialBackHandler;
import com.google.android.material.motion.MaterialBottomContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> implements MaterialBackHandler {
    private static final int CORNER_ANIMATION_DURATION = 500;
    static final int DEFAULT_SIGNIFICANT_VEL_THRESHOLD = 500;
    private static final int DEF_STYLE_RES = 0;
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    private static final int INVALID_POSITION = -1;
    private static final int NO_MAX_SIZE = -1;
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int SAVE_ALL = -1;
    public static final int SAVE_FIT_TO_CONTENTS = 2;
    public static final int SAVE_HIDEABLE = 4;
    public static final int SAVE_NONE = 0;
    public static final int SAVE_PEEK_HEIGHT = 1;
    public static final int SAVE_SKIP_COLLAPSED = 8;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HALF_EXPANDED = 6;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "BottomSheetBehavior";
    static final int VIEW_INDEX_ACCESSIBILITY_DELEGATE_VIEW = 1;
    static final int VIEW_INDEX_BOTTOM_SHEET = 0;
    WeakReference<View> accessibilityDelegateViewRef;
    int activePointerId;
    private boolean autoExpandOnRequestChildRectangleOffscreen;
    private ColorStateList backgroundTint;
    MaterialBottomContainerBackHelper bottomContainerBackHelper;
    private final ArrayList<BottomSheetCallback> callbacks;
    private int childHeight;
    final SparseIntArray collapseActionIds;
    int collapsedOffset;
    private WeakReference<View> currentTouchedScrollChildRef;
    private final ViewDragHelper.Callback dragCallback;
    WeakReference<View> dragHandleViewRef;
    private boolean draggable;
    private boolean draggableOnNestedScroll;
    private boolean draggableOnNestedScrollLastDragIgnored;
    float elevation;
    final SparseIntArray expandActionIds;
    final SparseIntArray expandHalfwayActionIds;
    private boolean expandedCornersRemoved;
    int expandedOffset;
    private boolean fitToContents;
    int fitToContentsOffset;
    private int gestureInsetBottom;
    private boolean gestureInsetBottomIgnored;
    int halfExpandedOffset;
    float halfExpandedRatio;
    private float hideFriction;
    boolean hideable;
    private boolean ignoreEvents;
    private Map<View, Integer> importantForAccessibilityMap;
    private int initialY;
    private int insetBottom;
    private int insetTop;
    private ValueAnimator interpolatorAnimator;
    private int lastNestedScrollDy;
    int lastStableState;
    private boolean marginLeftSystemWindowInsets;
    private boolean marginRightSystemWindowInsets;
    private boolean marginTopSystemWindowInsets;
    private MaterialShapeDrawable materialShapeDrawable;
    private int maxHeight;
    private int maxWidth;
    private float maximumVelocity;
    private boolean multipleScrollingChildrenSupported;
    private boolean nestedScrolled;
    final List<WeakReference<View>> nestedScrollingChildrenRef;
    private boolean paddingBottomSystemWindowInsets;
    private boolean paddingLeftSystemWindowInsets;
    private boolean paddingRightSystemWindowInsets;
    private boolean paddingTopSystemWindowInsets;
    int parentHeight;
    int parentWidth;
    private int peekHeight;
    private boolean peekHeightAuto;
    private int peekHeightGestureInsetBuffer;
    private int peekHeightMin;
    private int saveFlags;
    private ShapeAppearanceModel shapeAppearanceModelDefault;
    private final Rect sheetRect;
    private boolean shouldRemoveExpandedCorners;
    private int significantVelocityThreshold;
    private boolean skipCollapsed;
    int state;
    private final BottomSheetBehavior<V>.StateSettlingTracker stateSettlingTracker;
    boolean touchingScrollingChild;
    private boolean updateImportantForAccessibilityOnSiblings;
    private VelocityTracker velocityTracker;
    ViewDragHelper viewDragHelper;
    WeakReference<V> viewRef;

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetBehavior$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ BottomSheetBehavior this$0;
        final /* synthetic */ View val$child;
        final /* synthetic */ int val$finalState;

        public AnonymousClass1(BottomSheetBehavior r1, View r2, int r3) {
            this.this$0 = r1;
            this.val$child = r2;
            this.val$finalState = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            BottomSheetBehavior.access$100(this.this$0, this.val$child, this.val$finalState, false);
        }
    }

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetBehavior$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ BottomSheetBehavior this$0;

        public AnonymousClass2(BottomSheetBehavior r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.setStateInternal(5);
            WeakReference<V> r22 = this.this$0.viewRef;
            if (r22 != null) goto L5;
            return;
        L5:
            if (r22.get() == null) goto L9;
            this.this$0.viewRef.get().requestLayout();
            return;
        }
    }

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetBehavior$3, reason: invalid class name */
    public class AnonymousClass3 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ BottomSheetBehavior this$0;

        public AnonymousClass3(BottomSheetBehavior r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            float r22 = ((Float) r2.getAnimatedValue()).floatValue();
            if (BottomSheetBehavior.access$200(this.this$0) == null) goto L6;
            BottomSheetBehavior.access$200(this.this$0).setInterpolation(r22);
            return;
        }
    }

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetBehavior$4, reason: invalid class name */
    public class AnonymousClass4 implements ViewUtils.OnApplyWindowInsetsListener {
        final /* synthetic */ BottomSheetBehavior this$0;
        final /* synthetic */ boolean val$shouldHandleGestureInsets;

        public AnonymousClass4(BottomSheetBehavior r1, boolean r2) {
            this.this$0 = r1;
            this.val$shouldHandleGestureInsets = r2;
        }

        @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r11, WindowInsetsCompat r12, ViewUtils.RelativePadding r13) {
            Insets r0 = r12.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets r1 = r12.getInsets(WindowInsetsCompat.Type.mandatorySystemGestures());
            BottomSheetBehavior.access$302(this.this$0, r0.top);
            boolean r2 = ViewUtils.isLayoutRtl(r11);
            int r3 = r11.getPaddingBottom();
            int r4 = r11.getPaddingLeft();
            int r5 = r11.getPaddingRight();
            if (BottomSheetBehavior.access$400(this.this$0) == false) goto L6;
            BottomSheetBehavior.access$502(this.this$0, r12.getSystemWindowInsetBottom());
            r3 = r13.bottom + BottomSheetBehavior.access$500(this.this$0);
        L6:
            if (BottomSheetBehavior.access$600(this.this$0) == false) goto L12;
            if (r2 == false) goto L9;
            int r42 = r13.end;
        L10:
            r4 = r42 + r0.left;
            goto L12
        L9:
            r42 = r13.start;
        L12:
            if (BottomSheetBehavior.access$700(this.this$0) == false) goto L17;
            if (r2 == false) goto L15;
            int r132 = r13.start;
        L16:
            r5 = r132 + r0.right;
            goto L17
        L15:
            r132 = r13.end;
        L17:
            ViewGroup.MarginLayoutParams r133 = (ViewGroup.MarginLayoutParams) r11.getLayoutParams();
            boolean r7 = true;
            if (BottomSheetBehavior.access$800(this.this$0) == false) goto L22;
            int r22 = r133.leftMargin;
            int r8 = r0.left;
            if (r22 == r8) goto L22;
            r133.leftMargin = r8;
            boolean r23 = true;
        L24:
            if (BottomSheetBehavior.access$900(this.this$0) == false) goto L29;
            int r82 = r133.rightMargin;
            int r9 = r0.right;
            if (r82 == r9) goto L29;
            r133.rightMargin = r9;
            r23 = true;
        L29:
            if (BottomSheetBehavior.access$1000(this.this$0) == false) goto L33;
            int r83 = r133.topMargin;
            int r02 = r0.top;
            if (r83 == r02) goto L33;
            r133.topMargin = r02;
        L34:
            if (r7 == false) goto L36;
            r11.setLayoutParams(r133);
        L36:
            r11.setPadding(r4, r11.getPaddingTop(), r5, r3);
            if (this.val$shouldHandleGestureInsets == false) goto L40;
            BottomSheetBehavior.access$1102(this.this$0, r1.bottom);
        L40:
            if (BottomSheetBehavior.access$400(this.this$0) == false) goto L42;
        L45:
            BottomSheetBehavior.access$1200(this.this$0, false);
            return r12;
        L42:
            if (this.val$shouldHandleGestureInsets == true) goto L45;
            return r12;
        L33:
            r7 = r23;
        L22:
            r23 = false;
            goto L24
        }
    }

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetBehavior$5, reason: invalid class name */
    public class AnonymousClass5 extends ViewDragHelper.Callback {
        final /* synthetic */ BottomSheetBehavior this$0;
        private long viewCapturedMillis;

        public AnonymousClass5(BottomSheetBehavior r1) {
            this.this$0 = r1;
        }

        private boolean releasedLow(View r2) {
            int r22 = r2.getTop();
            BottomSheetBehavior r1 = this.this$0;
            if (r22 <= ((r1.parentHeight + r1.getExpandedOffset()) / 2)) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View r1, int r2, int r3) {
            return r1.getLeft();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View r1, int r2, int r3) {
            return MathUtils.clamp(r2, this.this$0.getExpandedOffset(), getViewVerticalDragRange(r1));
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewVerticalDragRange(View r1) {
            boolean r12 = BottomSheetBehavior.access$1900(this.this$0);
            BottomSheetBehavior r0 = this.this$0;
            if (r12 == false) goto L7;
            return r0.parentHeight;
        L7:
            return r0.collapsedOffset;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int r2) {
            if (r2 == 1) goto L5;
            return;
        L5:
            if (BottomSheetBehavior.access$1600(this.this$0) == false) goto L9;
            this.this$0.setStateInternal(1);
            return;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View r1, int r2, int r3, int r4, int r5) {
            this.this$0.dispatchOnSlide(r3);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View r9, float r10, float r11) {
            BottomSheetBehavior r2 = this.this$0;
            int r3 = 6;
            if (r11 >= 0.0f) goto L16;
            if (BottomSheetBehavior.access$1700(r2) == false) goto L7;
        L6:
            r3 = 3;
        L63:
            BottomSheetBehavior r8 = this.this$0;
            BottomSheetBehavior.access$100(r8, r9, r3, r8.shouldSkipSmoothAnimation());
            return;
        L7:
            int r102 = r9.getTop();
            long r0 = SystemClock.uptimeMillis() - this.viewCapturedMillis;
            boolean r112 = this.this$0.shouldSkipHalfExpandedStateWhenDragging();
            BottomSheetBehavior r22 = this.this$0;
            if (r112 == false) goto L13;
            if (r22.shouldExpandOnUpwardDrag(r0, (r102 * 100.0f) / r22.parentHeight) == true) goto L6;
        L39:
            r3 = 4;
            goto L63
        L13:
            if (r102 <= r22.halfExpandedOffset) goto L6;
        L16:
            if (r2.hideable == false) goto L33;
            if (r2.shouldHide(r9, r11) == false) goto L33;
            if (Math.abs(r10) >= Math.abs(r11)) goto L24;
            if (r11 <= BottomSheetBehavior.access$1800(this.this$0)) goto L24;
        L25:
            r3 = 5;
        L24:
            if (releasedLow(r9) == true) goto L25;
            if (BottomSheetBehavior.access$1700(this.this$0) == true) goto L6;
            if (Math.abs(r9.getTop() - this.this$0.getExpandedOffset()) >= Math.abs(r9.getTop() - this.this$0.halfExpandedOffset)) goto L63;
        L33:
            if (r11 != 0.0f) goto L35;
        L45:
            int r103 = r9.getTop();
            boolean r113 = BottomSheetBehavior.access$1700(this.this$0);
            BottomSheetBehavior r02 = this.this$0;
            if (r113 == true) goto L48;
            int r114 = r02.halfExpandedOffset;
            if (r103 >= r114) goto L59;
            if (r103 < Math.abs(r103 - r02.collapsedOffset)) goto L6;
            if (this.this$0.shouldSkipHalfExpandedStateWhenDragging() == false) goto L63;
        L59:
            if (Math.abs(r103 - r114) >= Math.abs(r103 - this.this$0.collapsedOffset)) goto L39;
            if (this.this$0.shouldSkipHalfExpandedStateWhenDragging() == false) goto L63;
        L48:
            if (Math.abs(r103 - r02.fitToContentsOffset) >= Math.abs(r103 - this.this$0.collapsedOffset)) goto L39;
        L35:
            if (Math.abs(r10) > Math.abs(r11)) goto L45;
            if (BottomSheetBehavior.access$1700(this.this$0) == true) goto L39;
            int r104 = r9.getTop();
            if (Math.abs(r104 - this.this$0.halfExpandedOffset) >= Math.abs(r104 - this.this$0.collapsedOffset)) goto L39;
            if (this.this$0.shouldSkipHalfExpandedStateWhenDragging() == false) goto L63;
            goto L63
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View r6, int r7) {
            BottomSheetBehavior r0 = this.this$0;
            int r1 = r0.state;
            if (r1 != 1) goto L6;
            return false;
        L6:
            if (r0.touchingScrollingChild == false) goto L9;
            return false;
        L9:
            if (r1 == 3) goto L11;
        L23:
            this.viewCapturedMillis = SystemClock.uptimeMillis();
            WeakReference<V> r5 = this.this$0.viewRef;
            if (r5 != null) goto L26;
        L28:
            return false;
        L26:
            if (r5.get() != r6) goto L28;
            return true;
        L11:
            if (r0.activePointerId != r7) goto L23;
            boolean r72 = BottomSheetBehavior.access$1300(r0);
            BottomSheetBehavior r02 = this.this$0;
            if (r72 == true) goto L15;
            View r73 = BottomSheetBehavior.access$1500(r02);
        L19:
            if (r73 == null) goto L23;
            if (r73.canScrollVertically(-1) == false) goto L23;
            return false;
        L15:
            if (BottomSheetBehavior.access$1400(r02) == null) goto L17;
            r73 = (View) BottomSheetBehavior.access$1400(this.this$0).get();
            goto L19
        L17:
            r73 = null;
            goto L19
        }
    }

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetBehavior$6, reason: invalid class name */
    public class AnonymousClass6 implements AccessibilityViewCommand {
        final /* synthetic */ BottomSheetBehavior this$0;
        final /* synthetic */ int val$state;

        public AnonymousClass6(BottomSheetBehavior r1, int r2) {
            this.this$0 = r1;
            this.val$state = r2;
        }

        @Override // androidx.core.view.accessibility.AccessibilityViewCommand
        public boolean perform(View r1, AccessibilityViewCommand.CommandArguments r2) {
            this.this$0.setState(this.val$state);
            return true;
        }
    }

    public static abstract class BottomSheetCallback {
        public BottomSheetCallback() {
        }

        public void onLayout(View r1) {
        }

        public abstract void onSlide(View r1, float r2);

        public abstract void onStateChanged(View r1, int r2);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SaveFlags {
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        boolean fitToContents;
        boolean hideable;
        int peekHeight;
        boolean skipCollapsed;
        final int state;

        /* renamed from: com.google.android.material.bottomsheet.BottomSheetBehavior$SavedState$1, reason: invalid class name */
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

        public SavedState(Parcel r2) {
            this(r2, null);
        }

        public SavedState(Parcel r3, ClassLoader r4) {
            super(r3, r4);
            this.state = r3.readInt();
            this.peekHeight = r3.readInt();
            boolean r0 = false;
            if (r3.readInt() != 1) goto L5;
            boolean r42 = true;
        L6:
            this.fitToContents = r42;
            if (r3.readInt() != 1) goto L9;
            boolean r43 = true;
        L10:
            this.hideable = r43;
            if (r3.readInt() != 1) goto L13;
            r0 = true;
        L13:
            this.skipCollapsed = r0;
            return;
        L9:
            r43 = false;
            goto L10
        L5:
            r42 = false;
            goto L6
        }

        @Deprecated
        public SavedState(Parcelable r1, int r2) {
            super(r1);
            this.state = r2;
        }

        public SavedState(Parcelable r1, BottomSheetBehavior<?> r2) {
            super(r1);
            this.state = r2.state;
            this.peekHeight = BottomSheetBehavior.access$2200(r2);
            this.fitToContents = BottomSheetBehavior.access$1700(r2);
            this.hideable = r2.hideable;
            this.skipCollapsed = BottomSheetBehavior.access$2300(r2);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            super.writeToParcel(r1, r2);
            r1.writeInt(this.state);
            r1.writeInt(this.peekHeight);
            r1.writeInt(this.fitToContents ? 1 : 0);
            r1.writeInt(this.hideable ? 1 : 0);
            r1.writeInt(this.skipCollapsed ? 1 : 0);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StableState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    public class StateSettlingTracker {
        private final Runnable continueSettlingRunnable;
        private boolean isContinueSettlingRunnablePosted;
        private int targetState;
        final /* synthetic */ BottomSheetBehavior this$0;

        /* renamed from: com.google.android.material.bottomsheet.BottomSheetBehavior$StateSettlingTracker$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ StateSettlingTracker this$1;

            public AnonymousClass1(StateSettlingTracker r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                StateSettlingTracker.access$2002(this.this$1, false);
                ViewDragHelper r0 = this.this$1.this$0.viewDragHelper;
                if (r0 != null) goto L5;
            L8:
                StateSettlingTracker r3 = this.this$1;
                BottomSheetBehavior r02 = r3.this$0;
                if (r02.state != 2) goto L12;
                r02.setStateInternal(StateSettlingTracker.access$2100(r3));
                return;
            L12:
                return;
            L5:
                if (r0.continueSettling(true) == false) goto L8;
                StateSettlingTracker r32 = this.this$1;
                r32.continueSettlingToState(StateSettlingTracker.access$2100(r32));
            }
        }

        private StateSettlingTracker(BottomSheetBehavior r1) {
            this.this$0 = r1;
            this.continueSettlingRunnable = new AnonymousClass1(this);
        }

        public /* synthetic */ StateSettlingTracker(BottomSheetBehavior r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static /* synthetic */ boolean access$2002(StateSettlingTracker r0, boolean r1) {
            r0.isContinueSettlingRunnablePosted = r1;
            return r1;
        }

        public static /* synthetic */ int access$2100(StateSettlingTracker r0) {
            return r0.targetState;
        }

        public void continueSettlingToState(int r2) {
            WeakReference<V> r0 = this.this$0.viewRef;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.get() == null) goto L13;
            this.targetState = r2;
            if (this.isContinueSettlingRunnablePosted == true) goto L12;
            this.this$0.viewRef.get().postOnAnimation(this.continueSettlingRunnable);
            this.isContinueSettlingRunnablePosted = true;
            return;
        L12:
            return;
        }
    }

    static {
        DEF_STYLE_RES = R.style.Widget_Design_BottomSheet_Modal;
    }

    public BottomSheetBehavior() {
        this.saveFlags = 0;
        this.fitToContents = true;
        this.updateImportantForAccessibilityOnSiblings = false;
        this.maxWidth = -1;
        this.maxHeight = -1;
        this.stateSettlingTracker = new StateSettlingTracker(this, null);
        this.halfExpandedRatio = HIDE_THRESHOLD;
        this.elevation = -1.0f;
        this.draggable = true;
        this.draggableOnNestedScroll = true;
        this.autoExpandOnRequestChildRectangleOffscreen = true;
        this.state = 4;
        this.lastStableState = 4;
        this.hideFriction = HIDE_FRICTION;
        this.nestedScrollingChildrenRef = new ArrayList();
        this.callbacks = new ArrayList();
        this.initialY = -1;
        this.expandHalfwayActionIds = new SparseIntArray();
        this.expandActionIds = new SparseIntArray();
        this.collapseActionIds = new SparseIntArray();
        this.sheetRect = new Rect();
        this.dragCallback = new AnonymousClass5(this);
    }

    public BottomSheetBehavior(Context r9, AttributeSet r10) {
        super(r9, r10);
        this.saveFlags = 0;
        this.fitToContents = true;
        this.updateImportantForAccessibilityOnSiblings = false;
        this.maxWidth = -1;
        this.maxHeight = -1;
        this.stateSettlingTracker = new StateSettlingTracker(this, null);
        this.halfExpandedRatio = HIDE_THRESHOLD;
        this.elevation = -1.0f;
        this.draggable = true;
        this.draggableOnNestedScroll = true;
        this.autoExpandOnRequestChildRectangleOffscreen = true;
        this.state = 4;
        this.lastStableState = 4;
        this.hideFriction = HIDE_FRICTION;
        this.nestedScrollingChildrenRef = new ArrayList();
        this.callbacks = new ArrayList();
        this.initialY = -1;
        this.expandHalfwayActionIds = new SparseIntArray();
        this.expandActionIds = new SparseIntArray();
        this.collapseActionIds = new SparseIntArray();
        this.sheetRect = new Rect();
        this.dragCallback = new AnonymousClass5(this);
        this.peekHeightGestureInsetBuffer = r9.getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size);
        TypedArray r5 = r9.obtainStyledAttributes(r10, R.styleable.BottomSheetBehavior_Layout);
        if (r5.hasValue(R.styleable.BottomSheetBehavior_Layout_backgroundTint) == false) goto L6;
        this.backgroundTint = MaterialResources.getColorStateList(r9, r5, R.styleable.BottomSheetBehavior_Layout_backgroundTint);
    L6:
        if (r5.hasValue(R.styleable.BottomSheetBehavior_Layout_shapeAppearance) == false) goto L8;
        this.shapeAppearanceModelDefault = ShapeAppearanceModel.builder(r9, r10, R.attr.bottomSheetStyle, DEF_STYLE_RES).build();
    L8:
        createMaterialShapeDrawableIfNeeded(r9);
        createShapeValueAnimator();
        this.elevation = r5.getDimension(R.styleable.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        if (r5.hasValue(R.styleable.BottomSheetBehavior_Layout_android_maxWidth) == false) goto L12;
        setMaxWidth(r5.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_android_maxWidth, -1));
    L12:
        if (r5.hasValue(R.styleable.BottomSheetBehavior_Layout_android_maxHeight) == false) goto L14;
        setMaxHeight(r5.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_android_maxHeight, -1));
    L14:
        TypedValue r102 = r5.peekValue(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
        if (r102 == null) goto L19;
        int r103 = r102.data;
        if (r103 != (-1)) goto L19;
        setPeekHeight(r103);
    L20:
        setHideable(r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        setGestureInsetBottomIgnored(r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false));
        setFitToContents(r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        setSkipCollapsed(r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        setDraggable(r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_draggable, true));
        setDraggableOnNestedScroll(r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_draggableOnNestedScroll, true));
        setSaveFlags(r5.getInt(R.styleable.BottomSheetBehavior_Layout_behavior_saveFlags, 0));
        setHalfExpandedRatio(r5.getFloat(R.styleable.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, HIDE_THRESHOLD));
        TypedValue r104 = r5.peekValue(R.styleable.BottomSheetBehavior_Layout_behavior_expandedOffset);
        if (r104 != null) goto L23;
    L25:
        setExpandedOffset(r5.getDimensionPixelOffset(R.styleable.BottomSheetBehavior_Layout_behavior_expandedOffset, 0));
    L26:
        setSignificantVelocityThreshold(r5.getInt(R.styleable.BottomSheetBehavior_Layout_behavior_significantVelocityThreshold, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN));
        this.multipleScrollingChildrenSupported = r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_multipleScrollingChildrenSupported, false);
        this.autoExpandOnRequestChildRectangleOffscreen = r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_autoExpandOnRequestChildRectangleOffscreen, true);
        this.paddingBottomSystemWindowInsets = r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingBottomSystemWindowInsets, false);
        this.paddingLeftSystemWindowInsets = r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingLeftSystemWindowInsets, false);
        this.paddingRightSystemWindowInsets = r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingRightSystemWindowInsets, false);
        this.paddingTopSystemWindowInsets = r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingTopSystemWindowInsets, true);
        this.marginLeftSystemWindowInsets = r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_marginLeftSystemWindowInsets, false);
        this.marginRightSystemWindowInsets = r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_marginRightSystemWindowInsets, false);
        this.marginTopSystemWindowInsets = r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_marginTopSystemWindowInsets, false);
        this.shouldRemoveExpandedCorners = r5.getBoolean(R.styleable.BottomSheetBehavior_Layout_shouldRemoveExpandedCorners, true);
        r5.recycle();
        this.maximumVelocity = ViewConfiguration.get(r9).getScaledMaximumFlingVelocity();
        return;
    L23:
        if (r104.type != 16) goto L25;
        setExpandedOffset(r104.data);
    L19:
        setPeekHeight(r5.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        goto L20
    }

    public static /* synthetic */ void access$100(BottomSheetBehavior r0, View r1, int r2, boolean r3) {
        r0.startSettling(r1, r2, r3);
    }

    public static /* synthetic */ boolean access$1000(BottomSheetBehavior r0) {
        return r0.marginTopSystemWindowInsets;
    }

    public static /* synthetic */ int access$1102(BottomSheetBehavior r0, int r1) {
        r0.gestureInsetBottom = r1;
        return r1;
    }

    public static /* synthetic */ void access$1200(BottomSheetBehavior r0, boolean r1) {
        r0.updatePeekHeight(r1);
    }

    public static /* synthetic */ boolean access$1300(BottomSheetBehavior r0) {
        return r0.multipleScrollingChildrenSupported;
    }

    public static /* synthetic */ WeakReference access$1400(BottomSheetBehavior r0) {
        return r0.currentTouchedScrollChildRef;
    }

    public static /* synthetic */ View access$1500(BottomSheetBehavior r0) {
        return r0.getSingleScrollingChildOrNull();
    }

    public static /* synthetic */ boolean access$1600(BottomSheetBehavior r0) {
        return r0.draggable;
    }

    public static /* synthetic */ boolean access$1700(BottomSheetBehavior r0) {
        return r0.fitToContents;
    }

    public static /* synthetic */ int access$1800(BottomSheetBehavior r0) {
        return r0.significantVelocityThreshold;
    }

    public static /* synthetic */ boolean access$1900(BottomSheetBehavior r0) {
        return r0.canBeHiddenByDragging();
    }

    public static /* synthetic */ MaterialShapeDrawable access$200(BottomSheetBehavior r0) {
        return r0.materialShapeDrawable;
    }

    public static /* synthetic */ int access$2200(BottomSheetBehavior r0) {
        return r0.peekHeight;
    }

    public static /* synthetic */ boolean access$2300(BottomSheetBehavior r0) {
        return r0.skipCollapsed;
    }

    public static /* synthetic */ int access$302(BottomSheetBehavior r0, int r1) {
        r0.insetTop = r1;
        return r1;
    }

    public static /* synthetic */ boolean access$400(BottomSheetBehavior r0) {
        return r0.paddingBottomSystemWindowInsets;
    }

    public static /* synthetic */ int access$500(BottomSheetBehavior r0) {
        return r0.insetBottom;
    }

    public static /* synthetic */ int access$502(BottomSheetBehavior r0, int r1) {
        r0.insetBottom = r1;
        return r1;
    }

    public static /* synthetic */ boolean access$600(BottomSheetBehavior r0) {
        return r0.paddingLeftSystemWindowInsets;
    }

    public static /* synthetic */ boolean access$700(BottomSheetBehavior r0) {
        return r0.paddingRightSystemWindowInsets;
    }

    public static /* synthetic */ boolean access$800(BottomSheetBehavior r0) {
        return r0.marginLeftSystemWindowInsets;
    }

    public static /* synthetic */ boolean access$900(BottomSheetBehavior r0) {
        return r0.marginRightSystemWindowInsets;
    }

    private int addAccessibilityActionForState(View r2, int r3, int r4) {
        return ViewCompat.addAccessibilityAction(r2, r2.getResources().getString(r3), createAccessibilityViewCommandForState(r4));
    }

    private void calculateCollapsedOffset() {
        int r0 = calculatePeekHeight();
        boolean r1 = this.fitToContents;
        int r2 = this.parentHeight;
        if (r1 == false) goto L6;
        this.collapsedOffset = Math.max(r2 - r0, this.fitToContentsOffset);
        return;
    L6:
        this.collapsedOffset = r2 - r0;
    }

    private float calculateCornerInterpolation(float r2, RoundedCorner r3) {
        if (r3 == null) goto L10;
        float r32 = r3.getRadius();
        if (r32 <= 0.0f) goto L10;
        if (r2 <= 0.0f) goto L10;
        return r32 / r2;
    L10:
        return 0.0f;
    }

    private void calculateHalfExpandedOffset() {
        this.halfExpandedOffset = (int) (this.parentHeight * (1.0f - this.halfExpandedRatio));
    }

    private float calculateInterpolationWithCornersRemoved() {
        if (this.materialShapeDrawable == null) goto L16;
        WeakReference<V> r0 = this.viewRef;
        if (r0 != null) goto L7;
        return 0.0f;
    L7:
        if (r0.get() != null) goto L9;
        return 0.0f;
    L9:
        if (Build.VERSION.SDK_INT < 31) goto L20;
        V r02 = this.viewRef.get();
        if (isAtTopOfScreen() == false) goto L21;
        WindowInsets r03 = r02.getRootWindowInsets();
        if (r03 != null) goto L15;
        return 0.0f;
    L15:
        return Math.max(calculateCornerInterpolation(this.materialShapeDrawable.getTopLeftCornerResolvedSize(), r03.getRoundedCorner(0)), calculateCornerInterpolation(this.materialShapeDrawable.getTopRightCornerResolvedSize(), r03.getRoundedCorner(1)));
    L21:
        return 0.0f;
    L20:
        return 0.0f;
    L16:
        return 0.0f;
    }

    private int calculatePeekHeight() {
        if (this.peekHeightAuto == false) goto L8;
        int r0 = Math.min(Math.max(this.peekHeightMin, this.parentHeight - ((this.parentWidth * 9) / 16)), this.childHeight);
        int r3 = this.insetBottom;
    L6:
        return r0 + r3;
    L8:
        if (this.gestureInsetBottomIgnored == false) goto L10;
    L15:
        r0 = this.peekHeight;
        r3 = this.insetBottom;
        goto L6
    L10:
        if (this.paddingBottomSystemWindowInsets == true) goto L15;
        int r02 = this.gestureInsetBottom;
        if (r02 <= 0) goto L15;
        return Math.max(this.peekHeight, r02 + this.peekHeightGestureInsetBuffer);
    }

    private float calculateSlideOffsetWithTop(int r3) {
        int r0 = this.collapsedOffset;
        if (r3 <= r0) goto L5;
    L10:
        int r02 = this.collapsedOffset;
        float r32 = r02 - r3;
        float r2 = this.parentHeight - r02;
    L9:
        return r32 / r2;
    L5:
        if (r0 == getExpandedOffset()) goto L10;
        int r03 = this.collapsedOffset;
        r32 = r03 - r3;
        r2 = r03 - getExpandedOffset();
        goto L9
    }

    private boolean canBeHiddenByDragging() {
        if (isHideable() == true) goto L5;
        return false;
    L5:
        if (isHideableWhenDragging() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private void clearAccessibilityAction(View r3, int r4) {
        if (r3 == null) goto L15;
        ViewCompat.removeAccessibilityAction(r3, 1048576);
        ViewCompat.removeAccessibilityAction(r3, 524288);
        ViewCompat.removeAccessibilityAction(r3, 262144);
        int r0 = this.expandActionIds.get(r4, -1);
        if (r0 == (-1)) goto L7;
        ViewCompat.removeAccessibilityAction(r3, r0);
        this.expandActionIds.delete(r4);
    L7:
        int r02 = this.expandHalfwayActionIds.get(r4, -1);
        if (r02 == (-1)) goto L10;
        ViewCompat.removeAccessibilityAction(r3, r02);
        this.expandHalfwayActionIds.delete(r4);
    L10:
        int r03 = this.collapseActionIds.get(r4, -1);
        if (r03 == (-1)) goto L14;
        ViewCompat.removeAccessibilityAction(r3, r03);
        this.collapseActionIds.delete(r4);
        return;
    L14:
        return;
    }

    private AccessibilityViewCommand createAccessibilityViewCommandForState(int r2) {
        return new AnonymousClass6(this, r2);
    }

    private void createMaterialShapeDrawableIfNeeded(Context r4) {
        if (this.shapeAppearanceModelDefault != null) goto L5;
        return;
    L5:
        MaterialShapeDrawable r0 = new MaterialShapeDrawable(this.shapeAppearanceModelDefault);
        this.materialShapeDrawable = r0;
        r0.initializeElevationOverlay(r4);
        ColorStateList r02 = this.backgroundTint;
        if (r02 == null) goto L9;
        this.materialShapeDrawable.setFillColor(r02);
        return;
    L9:
        TypedValue r03 = new TypedValue();
        r4.getTheme().resolveAttribute(android.R.attr.colorBackground, r03, true);
        this.materialShapeDrawable.setTint(r03.data);
    }

    private void createShapeValueAnimator() {
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{calculateInterpolationWithCornersRemoved(), 1.0f});
        this.interpolatorAnimator = r0;
        r0.setDuration(500);
        this.interpolatorAnimator.addUpdateListener(new AnonymousClass3(this));
    }

    private View findScrollingChildContainingCoordinates(CoordinatorLayout r4, int r5, int r6) {
        if (this.nestedScrollingChildrenRef.isEmpty() == false) goto L5;
        return null;
    L5:
        Iterator<WeakReference<View>> r3 = this.nestedScrollingChildrenRef.iterator();
    L7:
        if (r3.hasNext() == false) goto L13;
        View r0 = r3.next().get();
        if (r0 == null) goto L7;
        if (r4.isPointInChildBounds(r0, r5, r6) == false) goto L7;
        return r0;
    L13:
        return null;
    }

    public static <V extends View> BottomSheetBehavior<V> from(V r2) {
        ViewGroup.LayoutParams r22 = r2.getLayoutParams();
        if ((r22 instanceof CoordinatorLayout.LayoutParams) == false) goto L10;
        CoordinatorLayout.Behavior r23 = ((CoordinatorLayout.LayoutParams) r22).getBehavior();
        if ((r23 instanceof BottomSheetBehavior) == true) goto L7;
        f$$ExternalSyntheticBUOutline1.m("The view is not associated with BottomSheetBehavior");
        return null;
    L7:
        return (BottomSheetBehavior) r23;
    L10:
        f$$ExternalSyntheticBUOutline1.m("The view is not a child of CoordinatorLayout");
        return null;
    }

    private int getChildMeasureSpec(int r1, int r2, int r3, int r4) {
        int r0 = ViewGroup.getChildMeasureSpec(r1, r2, r4);
        if (r3 != (-1)) goto L5;
        return r0;
    L5:
        int r12 = View.MeasureSpec.getMode(r0);
        int r02 = View.MeasureSpec.getSize(r0);
        if (r12 == 1073741824) goto L13;
        if (r02 == 0) goto L11;
        r3 = Math.min(r02, r3);
    L11:
        return View.MeasureSpec.makeMeasureSpec(r3, Integer.MIN_VALUE);
    L13:
        return View.MeasureSpec.makeMeasureSpec(Math.min(r02, r3), TLObject.FLAG_30);
    }

    public static Protection getDefaultBottomGradientProtection(Context r2) {
        Integer r0 = MaterialColors.getColorOrNull(r2, R.attr.colorSurfaceContainerLow);
        if (r0 != null) goto L6;
        r0 = Integer.valueOf(MaterialColors.getColor(r2, R.attr.colorSurface, BottomSheetBehavior.class.getSimpleName()));
    L6:
        return new GradientProtection(8, r0.intValue());
    }

    private View getSingleScrollingChildOrNull() {
        if (this.nestedScrollingChildrenRef.isEmpty() == false) goto L5;
        return null;
    L5:
        return this.nestedScrollingChildrenRef.get(0).get();
    }

    private int getTopOffsetForState(int r2) {
        if (r2 == 3) goto L19;
        if (r2 == 4) goto L17;
        if (r2 == 5) goto L15;
        if (r2 == 6) goto L11;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Invalid state to get top offset: ", r2);
        return 0;
    L11:
        return this.halfExpandedOffset;
    L15:
        return this.parentHeight;
    L17:
        return this.collapsedOffset;
    L19:
        return getExpandedOffset();
    }

    private float getYVelocity() {
        VelocityTracker r0 = this.velocityTracker;
        if (r0 != null) goto L6;
        return 0.0f;
    L6:
        r0.computeCurrentVelocity(1000, this.maximumVelocity);
        return this.velocityTracker.getYVelocity(this.activePointerId);
    }

    private boolean hasScrollingChild() {
        Iterator<WeakReference<View>> r1 = this.nestedScrollingChildrenRef.iterator();
    L4:
        if (r1.hasNext() == false) goto L9;
        if (r1.next().get() == null) goto L4;
        return true;
    L9:
        return false;
    }

    private boolean isAtTopOfScreen() {
        WeakReference<V> r0 = this.viewRef;
        if (r0 != null) goto L5;
    L10:
        return false;
    L5:
        if (r0.get() == null) goto L10;
        int[] r02 = new int[2];
        this.viewRef.get().getLocationOnScreen(r02);
        if (r02[1] != 0) goto L10;
        return true;
    }

    private boolean isExpandedAndShouldRemoveCorners() {
        if (this.state == 3) goto L5;
        return false;
    L5:
        if (this.shouldRemoveExpandedCorners == false) goto L7;
        return true;
    L7:
        if (isAtTopOfScreen() == false) goto L13;
        return true;
    L13:
        return false;
    }

    private boolean isLayouting(V r1) {
        ViewParent r0 = r1.getParent();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isLayoutRequested() == true) goto L7;
        return false;
    L7:
        if (r1.isAttachedToWindow() == false) goto L13;
        return true;
    L13:
        return false;
    }

    private boolean isTouchingDragHandle(CoordinatorLayout r1, int r2, int r3) {
        WeakReference<View> r0 = this.dragHandleViewRef;
        if (r0 == null) goto L5;
        View r02 = r0.get();
    L6:
        if (r02 != null) goto L8;
        return false;
    L8:
        if (r1.isPointInChildBounds(r02, r2, r3) == false) goto L13;
        return true;
    L13:
        return false;
    L5:
        r02 = null;
        goto L6
    }

    private boolean isTouchingScrollingChild(CoordinatorLayout r4, MotionEvent r5) {
        if (this.multipleScrollingChildrenSupported == false) goto L10;
        WeakReference<View> r3 = this.currentTouchedScrollChildRef;
        if (r3 != null) goto L7;
    L9:
        return false;
    L7:
        if (r3.get() == null) goto L9;
        return true;
    L10:
        View r32 = getSingleScrollingChildOrNull();
        if (r32 != null) goto L13;
    L15:
        return false;
    L13:
        if (r4.isPointInChildBounds(r32, (int) r5.getX(), (int) r5.getY()) == false) goto L15;
        return true;
    }

    private boolean isViewScrollingChild(View r2) {
        Iterator<WeakReference<View>> r1 = this.nestedScrollingChildrenRef.iterator();
    L4:
        if (r1.hasNext() == false) goto L9;
        if (r1.next().get() != r2) goto L4;
        return true;
    L9:
        return false;
    }

    private void replaceAccessibilityActionForState(View r2, AccessibilityNodeInfoCompat.AccessibilityActionCompat r3, int r4) {
        ViewCompat.replaceAccessibilityAction(r2, r3, null, createAccessibilityViewCommandForState(r4));
    }

    private void reset() {
        this.activePointerId = -1;
        this.initialY = -1;
        this.currentTouchedScrollChildRef = null;
        VelocityTracker r1 = this.velocityTracker;
        if (r1 == null) goto L6;
        r1.recycle();
        this.velocityTracker = null;
        return;
    }

    private void restoreOptionalState(SavedState r5) {
        int r0 = this.saveFlags;
        if (r0 != 0) goto L6;
        return;
    L6:
        if (r0 != (-1)) goto L8;
    L9:
        this.peekHeight = r5.peekHeight;
    L10:
        if (r0 != (-1)) goto L12;
    L13:
        this.fitToContents = r5.fitToContents;
    L14:
        if (r0 != (-1)) goto L16;
    L17:
        this.hideable = r5.hideable;
    L18:
        if (r0 != (-1)) goto L20;
    L23:
        this.skipCollapsed = r5.skipCollapsed;
        return;
    L20:
        if ((r0 & 8) == 8) goto L23;
        return;
    L16:
        if ((r0 & 4) != 4) goto L18;
    L12:
        if ((r0 & 2) != 2) goto L14;
    L8:
        if ((r0 & 1) != 1) goto L10;
        goto L9
    }

    private void runAfterLayout(V r1, Runnable r2) {
        if (isLayouting(r1) == false) goto L6;
        r1.post(r2);
        return;
    L6:
        r2.run();
    }

    private void setWindowInsetsListener(View r3) {
        if (Build.VERSION.SDK_INT >= 29) goto L5;
    L9:
        boolean r0 = false;
    L11:
        if (this.paddingBottomSystemWindowInsets == false) goto L13;
    L24:
        ViewUtils.doOnApplyWindowInsets(r3, new AnonymousClass4(this, r0));
        return;
    L13:
        if (this.paddingLeftSystemWindowInsets == true) goto L24;
        if (this.paddingRightSystemWindowInsets == true) goto L24;
        if (this.marginLeftSystemWindowInsets == true) goto L24;
        if (this.marginRightSystemWindowInsets == true) goto L24;
        if (this.marginTopSystemWindowInsets == true) goto L24;
        if (r0 == true) goto L24;
        return;
    L5:
        if (isGestureInsetBottomIgnored() == true) goto L9;
        if (this.peekHeightAuto == true) goto L9;
        r0 = true;
        goto L11
    }

    private boolean shouldHandleDraggingWithHelper() {
        if (this.viewDragHelper != null) goto L5;
        return false;
    L5:
        if (this.draggable == false) goto L7;
    L8:
        return true;
    L7:
        if (this.state == 1) goto L8;
        return false;
    }

    private void startSettling(View r3, int r4, boolean r5) {
        int r0 = getTopOffsetForState(r4);
        ViewDragHelper r1 = this.viewDragHelper;
        if (r1 == null) goto L12;
        if (r5 == false) goto L9;
        if (r1.settleCapturedViewAt(r3.getLeft(), r0) == false) goto L12;
    L10:
        setStateInternal(2);
        updateDrawableForTargetState(r4, true);
        this.stateSettlingTracker.continueSettlingToState(r4);
        return;
    L9:
        if (r1.smoothSlideViewTo(r3, r3.getLeft(), r0) == true) goto L10;
    L12:
        setStateInternal(r4);
    }

    private void updateAccessibilityActions() {
        WeakReference<V> r0 = this.viewRef;
        if (r0 == null) goto L5;
        updateAccessibilityActions(r0.get(), 0);
    L5:
        WeakReference<View> r02 = this.accessibilityDelegateViewRef;
        if (r02 == null) goto L9;
        updateAccessibilityActions(r02.get(), 1);
        return;
    }

    private void updateAccessibilityActions(View r5, int r6) {
        if (r5 == null) goto L33;
        clearAccessibilityAction(r5, r6);
        if (this.fitToContents == true) goto L10;
        if (this.state == 6) goto L10;
        this.expandHalfwayActionIds.put(r6, addAccessibilityActionForState(r5, R.string.bottomsheet_action_expand_halfway, 6));
    L10:
        if (this.hideable == true) goto L12;
    L16:
        int r0 = this.state;
        if (r0 == 3) goto L29;
        if (r0 == 4) goto L26;
        if (r0 == 6) goto L22;
        return;
    L22:
        if (canCollapse() == false) goto L24;
        this.collapseActionIds.put(r6, addAccessibilityActionForState(r5, R.string.bottomsheet_action_collapse, 4));
    L24:
        this.expandActionIds.put(r6, addAccessibilityActionForState(r5, R.string.bottomsheet_action_expand, 3));
        return;
    L26:
        this.expandActionIds.put(r6, addAccessibilityActionForState(r5, R.string.bottomsheet_action_expand, 3));
        return;
    L29:
        if (canCollapse() == false) goto L32;
        this.collapseActionIds.put(r6, addAccessibilityActionForState(r5, R.string.bottomsheet_action_collapse, 4));
        return;
    L32:
        return;
    L12:
        if (isHideableWhenDragging() == false) goto L16;
        if (this.state == 5) goto L16;
        replaceAccessibilityActionForState(r5, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, 5);
        goto L16
    }

    private void updateDrawableForTargetState(int r4, boolean r5) {
        if (r4 == 2) goto L34;
        boolean r42 = isExpandedAndShouldRemoveCorners();
        if (this.expandedCornersRemoved != r42) goto L8;
        return;
    L8:
        if (this.materialShapeDrawable == null) goto L35;
        this.expandedCornersRemoved = r42;
        float r1 = 1.0f;
        if (r5 == false) goto L23;
        ValueAnimator r52 = this.interpolatorAnimator;
        if (r52 == null) goto L23;
        if (r52.isRunning() == false) goto L18;
        this.interpolatorAnimator.reverse();
        return;
    L18:
        float r53 = this.materialShapeDrawable.getInterpolation();
        if (r42 == false) goto L21;
        r1 = calculateInterpolationWithCornersRemoved();
    L21:
        this.interpolatorAnimator.setFloatValues(new float[]{r53, r1});
        this.interpolatorAnimator.start();
        return;
    L23:
        ValueAnimator r43 = this.interpolatorAnimator;
        if (r43 != null) goto L26;
    L28:
        MaterialShapeDrawable r44 = this.materialShapeDrawable;
        if (this.expandedCornersRemoved == false) goto L31;
        r1 = calculateInterpolationWithCornersRemoved();
    L31:
        r44.setInterpolation(r1);
        return;
    L26:
        if (r43.isRunning() == false) goto L28;
        this.interpolatorAnimator.cancel();
        goto L28
    L35:
        return;
    }

    private void updateImportantForAccessibility(boolean r7) {
        WeakReference<V> r0 = this.viewRef;
        if (r0 == null) goto L47;
        ViewParent r02 = r0.get().getParent();
        if ((r02 instanceof CoordinatorLayout) == false) goto L48;
        CoordinatorLayout r03 = (CoordinatorLayout) r02;
        int r1 = r03.getChildCount();
        if (r7 == true) goto L11;
    L13:
        int r2 = 0;
    L14:
        if (r2 >= r1) goto L30;
        View r3 = r03.getChildAt(r2);
        if (r3 == this.viewRef.get()) goto L29;
        if (r7 == false) goto L23;
        this.importantForAccessibilityMap.put(r3, Integer.valueOf(r3.getImportantForAccessibility()));
        if (this.updateImportantForAccessibilityOnSiblings == false) goto L29;
        r3.setImportantForAccessibility(4);
        goto L29
    L23:
        if (this.updateImportantForAccessibilityOnSiblings == false) goto L29;
        Map<View, Integer> r4 = this.importantForAccessibilityMap;
        if (r4 == null) goto L29;
        if (r4.containsKey(r3) == false) goto L29;
        r3.setImportantForAccessibility(this.importantForAccessibilityMap.get(r3).intValue());
    L29:
        r2 = r2 + 1;
        goto L14
    L30:
        if (r7 == true) goto L34;
        this.importantForAccessibilityMap = null;
        return;
    L34:
        if (this.updateImportantForAccessibilityOnSiblings == false) goto L46;
        this.viewRef.get().sendAccessibilityEvent(8);
        return;
    L46:
        return;
    L11:
        if (this.importantForAccessibilityMap != null) goto L45;
        this.importantForAccessibilityMap = new HashMap(r1);
        goto L13
    L45:
        return;
    L48:
        return;
    }

    private void updatePeekHeight(boolean r3) {
        if (this.viewRef == null) goto L13;
        calculateCollapsedOffset();
        if (this.state != 4) goto L14;
        V r0 = this.viewRef.get();
        if (r0 == null) goto L15;
        if (r3 == false) goto L11;
        setState(4);
        return;
    L11:
        r0.requestLayout();
        return;
    L15:
        return;
    L14:
        return;
    }

    public void addBottomSheetCallback(BottomSheetCallback r2) {
        if (this.callbacks.contains(r2) == true) goto L6;
        this.callbacks.add(r2);
        return;
    }

    public float calculateSlideOffset() {
        WeakReference<V> r0 = this.viewRef;
        if (r0 != null) goto L5;
        return -1.0f;
    L5:
        if (r0.get() != null) goto L8;
        return -1.0f;
    L8:
        return calculateSlideOffsetWithTop(this.viewRef.get().getTop());
    }

    public boolean canCollapse() {
        if (this.skipCollapsed == true) goto L5;
        return true;
    L5:
        if (this.hideable == false) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void cancelBackProgress() {
        MaterialBottomContainerBackHelper r0 = this.bottomContainerBackHelper;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.cancelBackProgress();
    }

    public void disableShapeAnimations() {
        this.interpolatorAnimator = null;
    }

    public void dispatchOnSlide(int r4) {
        V r0 = this.viewRef.get();
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.callbacks.isEmpty() == true) goto L12;
        float r42 = calculateSlideOffsetWithTop(r4);
        int r1 = 0;
    L8:
        if (r1 >= this.callbacks.size()) goto L13;
        this.callbacks.get(r1).onSlide(r0, r42);
        r1 = r1 + 1;
        goto L8
    L13:
        return;
    }

    public View findScrollingChild(View r5) {
        if (r5.getVisibility() == 0) goto L6;
        return null;
    L6:
        if (r5.isNestedScrollingEnabled() == false) goto L9;
        return r5;
    L9:
        if ((r5 instanceof ViewGroup) == false) goto L16;
        ViewGroup r52 = (ViewGroup) r5;
        int r0 = r52.getChildCount();
        int r2 = 0;
    L11:
        if (r2 >= r0) goto L16;
        View r3 = findScrollingChild(r52.getChildAt(r2));
        if (r3 != null) goto L14;
        r2 = r2 + 1;
        goto L11
    L14:
        return r3;
    L16:
        return null;
    }

    public MaterialBottomContainerBackHelper getBackHelper() {
        return this.bottomContainerBackHelper;
    }

    public int getExpandedOffset() {
        if (this.fitToContents == true) goto L5;
        int r0 = this.expandedOffset;
        if (this.paddingTopSystemWindowInsets == false) goto L9;
        int r2 = 0;
    L11:
        return Math.max(r0, r2);
    L9:
        r2 = this.insetTop;
        goto L11
    L5:
        return this.fitToContentsOffset;
    }

    public float getHalfExpandedRatio() {
        return this.halfExpandedRatio;
    }

    public float getHideFriction() {
        return this.hideFriction;
    }

    public int getLastStableState() {
        return this.lastStableState;
    }

    public MaterialShapeDrawable getMaterialShapeDrawable() {
        return this.materialShapeDrawable;
    }

    public int getMaxHeight() {
        return this.maxHeight;
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    public int getPeekHeight() {
        if (this.peekHeightAuto == false) goto L7;
        return -1;
    L7:
        return this.peekHeight;
    }

    public int getPeekHeightMin() {
        return this.peekHeightMin;
    }

    public int getSaveFlags() {
        return this.saveFlags;
    }

    public int getSignificantVelocityThreshold() {
        return this.significantVelocityThreshold;
    }

    public boolean getSkipCollapsed() {
        return this.skipCollapsed;
    }

    public int getState() {
        return this.state;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void handleBackInvoked() {
        MaterialBottomContainerBackHelper r0 = this.bottomContainerBackHelper;
        if (r0 != null) goto L5;
        return;
    L5:
        BackEventCompat r02 = r0.onHandleBackInvoked();
        int r1 = 4;
        if (r02 == null) goto L17;
        if (Build.VERSION.SDK_INT < 34) goto L17;
        boolean r2 = this.hideable;
        MaterialBottomContainerBackHelper r3 = this.bottomContainerBackHelper;
        if (r2 == false) goto L14;
        r3.finishBackProgressNotPersistent(r02, new AnonymousClass2(this));
        return;
    L14:
        r3.finishBackProgressPersistent(r02, null);
        setState(4);
        return;
    L17:
        if (this.hideable == false) goto L19;
        r1 = 5;
    L19:
        setState(r1);
    }

    public boolean isAutoExpandOnRequestChildRectangleOffscreen() {
        return this.autoExpandOnRequestChildRectangleOffscreen;
    }

    public boolean isDraggable() {
        return this.draggable;
    }

    public boolean isDraggableOnNestedScroll() {
        return this.draggableOnNestedScroll;
    }

    public boolean isFitToContents() {
        return this.fitToContents;
    }

    public boolean isGestureInsetBottomIgnored() {
        return this.gestureInsetBottomIgnored;
    }

    public boolean isHideable() {
        return this.hideable;
    }

    public boolean isHideableWhenDragging() {
        return true;
    }

    public boolean isNestedScrollingCheckEnabled() {
        return true;
    }

    public boolean isShouldRemoveExpandedCorners() {
        return this.shouldRemoveExpandedCorners;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams r1) {
        super.onAttachedToLayoutParams(r1);
        this.viewRef = null;
        this.viewDragHelper = null;
        this.bottomContainerBackHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.viewRef = null;
        this.viewDragHelper = null;
        this.bottomContainerBackHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout r10, V r11, MotionEvent r12) {
        if (r11.isShown() == true) goto L5;
    L61:
        this.ignoreEvents = true;
        return false;
    L5:
        if (this.draggable == false) goto L61;
        int r0 = r12.getActionMasked();
        if (r0 != 0) goto L11;
        reset();
    L11:
        if (this.velocityTracker != null) goto L13;
        this.velocityTracker = VelocityTracker.obtain();
    L13:
        this.velocityTracker.addMovement(r12);
        if (r0 == 0) goto L23;
        if (r0 != 1) goto L17;
    L19:
        this.touchingScrollingChild = false;
        this.currentTouchedScrollChildRef = null;
        this.activePointerId = -1;
        if (this.ignoreEvents == false) goto L38;
        this.ignoreEvents = false;
        return false;
    L38:
        if (this.ignoreEvents == true) goto L44;
        ViewDragHelper r112 = this.viewDragHelper;
        if (r112 == null) goto L44;
        if (r112.shouldInterceptTouchEvent(r12) == false) goto L44;
        return true;
    L44:
        if (r0 == 2) goto L46;
    L60:
        return false;
    L46:
        if (hasScrollingChild() == false) goto L60;
        if (this.ignoreEvents == true) goto L60;
        if (this.state == 1) goto L60;
        if (isTouchingScrollingChild(r10, r12) == true) goto L60;
        if (this.viewDragHelper == null) goto L60;
        int r102 = this.initialY;
        if (r102 == (-1)) goto L60;
        if (Math.abs(r102 - r12.getY()) <= this.viewDragHelper.getTouchSlop()) goto L60;
        return true;
    L17:
        if (r0 == 3) goto L19;
    L23:
        int r5 = (int) r12.getX();
        this.initialY = (int) r12.getY();
        WeakReference<View> r6 = new WeakReference(findScrollingChildContainingCoordinates(r10, (int) r12.getX(), (int) r12.getY()));
        this.currentTouchedScrollChildRef = r6;
        if (this.state == 2) goto L31;
        if (r6.get() == null) goto L31;
        this.activePointerId = r12.getPointerId(r12.getActionIndex());
        if (isTouchingDragHandle(r10, r5, this.initialY) == true) goto L31;
        this.touchingScrollingChild = true;
    L31:
        if (this.activePointerId == (-1)) goto L33;
    L35:
        boolean r113 = false;
    L36:
        this.ignoreEvents = r113;
        goto L38
    L33:
        if (r10.isPointInChildBounds(r11, r5, this.initialY) == true) goto L35;
        r113 = true;
        goto L36
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout r6, V r7, int r8) {
        if (r6.getFitsSystemWindows() == false) goto L8;
        if (r7.getFitsSystemWindows() == true) goto L8;
        r7.setFitsSystemWindows(true);
    L8:
        if (this.viewRef != null) goto L22;
        this.peekHeightMin = r6.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
        setWindowInsetsListener(r7);
        ViewCompat.setWindowInsetsAnimationCallback(r7, new InsetsAnimationCallback(r7));
        this.viewRef = new WeakReference(r7);
        this.bottomContainerBackHelper = new MaterialBottomContainerBackHelper(r7);
        MaterialShapeDrawable r0 = this.materialShapeDrawable;
        if (r0 == null) goto L15;
        r7.setBackground(r0);
        MaterialShapeDrawable r02 = this.materialShapeDrawable;
        float r2 = this.elevation;
        if (r2 != (-1.0f)) goto L14;
        r2 = r7.getElevation();
    L14:
        r02.setElevation(r2);
    L18:
        updateAccessibilityActions();
        if (r7.getImportantForAccessibility() != 0) goto L22;
        r7.setImportantForAccessibility(1);
        goto L22
    L15:
        ColorStateList r03 = this.backgroundTint;
        if (r03 == null) goto L18;
        ViewCompat.setBackgroundTintList(r7, r03);
    L22:
        if (this.viewDragHelper != null) goto L24;
        this.viewDragHelper = ViewDragHelper.create(r6, this.dragCallback);
    L24:
        int r04 = r7.getTop();
        r6.onLayoutChild(r7, r8);
        this.parentWidth = r6.getWidth();
        this.parentHeight = r6.getHeight();
        int r62 = r7.getHeight();
        this.childHeight = r62;
        int r82 = this.parentHeight;
        int r63 = r82 - r62;
        int r22 = this.insetTop;
        if (r63 >= r22) goto L37;
        boolean r64 = this.paddingTopSystemWindowInsets;
        int r3 = this.maxHeight;
        if (r64 == false) goto L32;
        if (r3 == (-1)) goto L31;
        r82 = Math.min(r82, r3);
    L31:
        this.childHeight = r82;
        goto L37
    L32:
        int r83 = r82 - r22;
        if (r3 == (-1)) goto L36;
        r83 = Math.min(r83, r3);
    L36:
        this.childHeight = r83;
    L37:
        int r65 = this.parentHeight - this.childHeight;
        int r84 = 0;
        this.fitToContentsOffset = Math.max(0, r65);
        calculateHalfExpandedOffset();
        calculateCollapsedOffset();
        int r66 = this.state;
        if (r66 != 3) goto L41;
        ViewCompat.offsetTopAndBottom(r7, getExpandedOffset());
    L55:
        updateDrawableForTargetState(this.state, false);
        this.nestedScrollingChildrenRef.clear();
        if (this.multipleScrollingChildrenSupported == false) goto L58;
        populateScrollingChildren(r7);
    L60:
        if (r84 >= this.callbacks.size()) goto L62;
        this.callbacks.get(r84).onLayout(r7);
        r84 = r84 + 1;
        goto L60
    L62:
        return true;
    L58:
        this.nestedScrollingChildrenRef.add(new WeakReference(findScrollingChild(r7)));
        goto L60
    L41:
        if (r66 != 6) goto L44;
        ViewCompat.offsetTopAndBottom(r7, this.halfExpandedOffset);
        goto L55
    L44:
        if (this.hideable == false) goto L49;
        if (r66 != 5) goto L49;
        ViewCompat.offsetTopAndBottom(r7, this.parentHeight);
    L49:
        if (r66 != 4) goto L51;
        ViewCompat.offsetTopAndBottom(r7, this.collapsedOffset);
        goto L55
    L51:
        if (r66 != 1) goto L53;
    L54:
        ViewCompat.offsetTopAndBottom(r7, r04 - r7.getTop());
        goto L55
    L53:
        if (r66 != 2) goto L55;
        goto L54
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onMeasureChild(CoordinatorLayout r4, V r5, int r6, int r7, int r8, int r9) {
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) r5.getLayoutParams();
        r5.measure(getChildMeasureSpec(r6, (((r4.getPaddingLeft() + r4.getPaddingRight()) + r0.leftMargin) + r0.rightMargin) + r7, this.maxWidth, r0.width), getChildMeasureSpec(r8, (((r4.getPaddingTop() + r4.getPaddingBottom()) + r0.topMargin) + r0.bottomMargin) + r9, this.maxHeight, r0.height));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onNestedPreFling(CoordinatorLayout r4, V r5, View r6, float r7, float r8) {
        if (isNestedScrollingCheckEnabled() == true) goto L5;
    L16:
        return false;
    L5:
        if (hasScrollingChild() == false) goto L16;
        if (isViewScrollingChild(r6) == false) goto L16;
        if (this.state == 3) goto L13;
        if (this.draggableOnNestedScrollLastDragIgnored == true) goto L13;
        return true;
    L13:
        if (super.onNestedPreFling(r4, r5, r6, r7, r8) == false) goto L16;
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(CoordinatorLayout r3, V r4, View r5, int r6, int r7, int[] r8, int r9) {
        if (r9 == 1) goto L49;
        boolean r62 = isViewScrollingChild(r5);
        if (isNestedScrollingCheckEnabled() == false) goto L9;
        if (r62 == true) goto L9;
        return;
    L9:
        int r92 = r4.getTop();
        int r0 = r92 - r7;
        if (r7 > 0) goto L12;
        if (r7 >= 0) goto L47;
        boolean r52 = r5.canScrollVertically(-1);
        if (this.nestedScrolled == false) goto L31;
    L36:
        if (r52 == true) goto L47;
        if (r0 <= this.collapsedOffset) goto L44;
        if (canBeHiddenByDragging() == true) goto L44;
        int r93 = r92 - this.collapsedOffset;
        r8[1] = r93;
        ViewCompat.offsetTopAndBottom(r4, -r93);
        setStateInternal(4);
    L44:
        if (this.draggable == true) goto L46;
        return;
    L46:
        r8[1] = r7;
        ViewCompat.offsetTopAndBottom(r4, -r7);
        setStateInternal(1);
        goto L47
    L31:
        if (this.draggableOnNestedScroll == true) goto L36;
        if (r62 == false) goto L36;
        if (r52 == false) goto L36;
        this.draggableOnNestedScrollLastDragIgnored = true;
        return;
    L47:
        dispatchOnSlide(r4.getTop());
        this.lastNestedScrollDy = r7;
        this.nestedScrolled = true;
        this.draggableOnNestedScrollLastDragIgnored = false;
        return;
    L12:
        if (this.nestedScrolled == true) goto L21;
        if (this.draggableOnNestedScroll == true) goto L21;
        if (r62 == false) goto L21;
        if (r5.canScrollVertically(1) == false) goto L21;
        this.draggableOnNestedScrollLastDragIgnored = true;
        return;
    L21:
        if (r0 >= getExpandedOffset()) goto L24;
        int r94 = r92 - getExpandedOffset();
        r8[1] = r94;
        ViewCompat.offsetTopAndBottom(r4, -r94);
        setStateInternal(3);
        goto L47
    L24:
        if (this.draggable == false) goto L51;
        r8[1] = r7;
        ViewCompat.offsetTopAndBottom(r4, -r7);
        setStateInternal(1);
        goto L47
    L51:
        return;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(CoordinatorLayout r1, V r2, View r3, int r4, int r5, int r6, int r7, int r8, int[] r9) {
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onRequestChildRectangleOnScreen(CoordinatorLayout r3, V r4, Rect r5, boolean r6) {
        if (this.autoExpandOnRequestChildRectangleOffscreen == true) goto L5;
    L24:
        return false;
    L5:
        if (r4.isInTouchMode() == true) goto L24;
        int r32 = this.state;
        if (r32 == 4) goto L13;
        if (r32 == 6) goto L13;
        return false;
    L13:
        if (r4.getLocalVisibleRect(this.sheetRect) == false) goto L22;
        WindowInsetsCompat r33 = ViewCompat.getRootWindowInsets(r4);
        if (r33 == null) goto L17;
        this.sheetRect.bottom -= r33.getInsets(WindowInsetsCompat.Type.systemBars()).bottom;
    L17:
        int r34 = r5.top;
        Rect r42 = this.sheetRect;
        if (r34 < r42.top) goto L22;
        if (r5.bottom > r42.bottom) goto L22;
        return false;
    L22:
        setState(3);
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onRestoreInstanceState(CoordinatorLayout r2, V r3, Parcelable r4) {
        SavedState r42 = (SavedState) r4;
        super.onRestoreInstanceState(r2, r3, r42.getSuperState());
        restoreOptionalState(r42);
        int r22 = r42.state;
        if (r22 != 1) goto L5;
    L9:
        this.state = 4;
        this.lastStableState = 4;
        return;
    L5:
        if (r22 == 2) goto L9;
        this.state = r22;
        this.lastStableState = r22;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public Parcelable onSaveInstanceState(CoordinatorLayout r2, V r3) {
        return new SavedState(super.onSaveInstanceState(r2, r3), this);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout r1, V r2, View r3, View r4, int r5, int r6) {
        this.lastNestedScrollDy = 0;
        this.nestedScrolled = false;
        if ((r5 & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onStopNestedScroll(CoordinatorLayout r3, V r4, View r5, int r6) {
        int r0 = 3;
        if (r4.getTop() != getExpandedOffset()) goto L7;
        setStateInternal(3);
        return;
    L7:
        if (isNestedScrollingCheckEnabled() == false) goto L14;
        if (isViewScrollingChild(r5) == true) goto L11;
        return;
    L11:
        if (this.nestedScrolled == true) goto L14;
        return;
    L14:
        if (this.lastNestedScrollDy <= 0) goto L22;
        if (this.fitToContents == false) goto L19;
    L50:
        startSettling(r4, r0, false);
        this.nestedScrolled = false;
        return;
    L19:
        if (r4.getTop() <= this.halfExpandedOffset) goto L50;
    L49:
        r0 = 6;
        goto L50
    L22:
        if (this.hideable == false) goto L27;
        if (shouldHide(r4, getYVelocity()) == false) goto L27;
        r0 = 5;
    L27:
        if (this.lastNestedScrollDy != 0) goto L45;
        int r32 = r4.getTop();
        if (this.fitToContents == true) goto L31;
        int r1 = this.halfExpandedOffset;
        if (r32 >= r1) goto L42;
        if (r32 < Math.abs(r32 - this.collapsedOffset)) goto L50;
        if (shouldSkipHalfExpandedStateWhenDragging() == false) goto L49;
    L46:
        r0 = 4;
        goto L50
    L42:
        if (Math.abs(r32 - r1) >= Math.abs(r32 - this.collapsedOffset)) goto L46;
    L31:
        if (Math.abs(r32 - this.fitToContentsOffset) >= Math.abs(r32 - this.collapsedOffset)) goto L46;
    L45:
        if (this.fitToContents == true) goto L46;
        int r33 = r4.getTop();
        if (Math.abs(r33 - this.halfExpandedOffset) >= Math.abs(r33 - this.collapsedOffset)) goto L46;
        goto L46
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout r3, V r4, MotionEvent r5) {
        if (r4.isShown() == true) goto L6;
        return false;
    L6:
        int r32 = r5.getActionMasked();
        if (this.state != 1) goto L11;
        if (r32 != 0) goto L11;
        return true;
    L11:
        if (shouldHandleDraggingWithHelper() == false) goto L13;
        this.viewDragHelper.processTouchEvent(r5);
    L13:
        if (r32 != 0) goto L16;
        reset();
    L16:
        if (this.velocityTracker != null) goto L18;
        this.velocityTracker = VelocityTracker.obtain();
    L18:
        this.velocityTracker.addMovement(r5);
        if (shouldHandleDraggingWithHelper() == false) goto L28;
        if (r32 != 2) goto L28;
        if (this.ignoreEvents == true) goto L28;
        if (Math.abs(this.initialY - r5.getY()) <= this.viewDragHelper.getTouchSlop()) goto L28;
        this.viewDragHelper.captureChildView(r4, r5.getPointerId(r5.getActionIndex()));
    L28:
        return !this.ignoreEvents;
    }

    public void populateScrollingChildren(View r3) {
        if (r3.getVisibility() == 0) goto L6;
        return;
    L6:
        if (r3.isNestedScrollingEnabled() == false) goto L10;
        this.nestedScrollingChildrenRef.add(new WeakReference(r3));
        return;
    L10:
        if ((r3 instanceof ViewGroup) == false) goto L15;
        ViewGroup r32 = (ViewGroup) r3;
        int r0 = 0;
    L13:
        if (r0 >= r32.getChildCount()) goto L18;
        populateScrollingChildren(r32.getChildAt(r0));
        r0 = r0 + 1;
        goto L13
    L18:
        return;
    }

    public void removeBottomSheetCallback(BottomSheetCallback r1) {
        this.callbacks.remove(r1);
    }

    public void setAccessibilityDelegateView(View r3) {
        if (r3 != null) goto L8;
        WeakReference<View> r1 = this.accessibilityDelegateViewRef;
        if (r1 == null) goto L8;
        clearAccessibilityAction(r1.get(), 1);
        this.accessibilityDelegateViewRef = null;
        return;
    L8:
        this.accessibilityDelegateViewRef = new WeakReference(r3);
        updateAccessibilityActions(r3, 1);
    }

    public void setAutoExpandOnRequestChildRectangleOffscreen(boolean r1) {
        this.autoExpandOnRequestChildRectangleOffscreen = r1;
    }

    @Deprecated
    public void setBottomSheetCallback(BottomSheetCallback r3) {
        Log.w(TAG, "BottomSheetBehavior now supports multiple callbacks. `setBottomSheetCallback()` removes all existing callbacks, including ones set internally by library authors, which may result in unintended behavior. This may change in the future. Please use `addBottomSheetCallback()` and `removeBottomSheetCallback()` instead to set your own callbacks.");
        this.callbacks.clear();
        if (r3 == null) goto L6;
        this.callbacks.add(r3);
        return;
    }

    public void setDragHandleView(BottomSheetDragHandleView r2) {
        if (r2 == null) goto L4;
        WeakReference<View> r0 = new WeakReference(r2);
    L5:
        this.dragHandleViewRef = r0;
        return;
    L4:
        r0 = null;
        goto L5
    }

    public void setDraggable(boolean r1) {
        this.draggable = r1;
    }

    public void setDraggableOnNestedScroll(boolean r1) {
        this.draggableOnNestedScroll = r1;
    }

    public void setExpandedOffset(int r2) {
        if (r2 < 0) goto L5;
        this.expandedOffset = r2;
        updateDrawableForTargetState(this.state, true);
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("offset must be greater than or equal to 0");
    }

    public void setFitToContents(boolean r2) {
        if (this.fitToContents != r2) goto L5;
        return;
    L5:
        this.fitToContents = r2;
        if (this.viewRef == null) goto L9;
        calculateCollapsedOffset();
    L9:
        if (this.fitToContents == true) goto L11;
    L13:
        int r22 = this.state;
    L14:
        setStateInternal(r22);
        updateDrawableForTargetState(this.state, true);
        updateAccessibilityActions();
        return;
    L11:
        if (this.state != 6) goto L13;
        r22 = 3;
        goto L14
    }

    public void setGestureInsetBottomIgnored(boolean r1) {
        this.gestureInsetBottomIgnored = r1;
    }

    public void setHalfExpandedRatio(float r2) {
        if (r2 > 0.0f) goto L5;
    L10:
        f$$ExternalSyntheticBUOutline1.m("ratio must be a float value between 0 and 1");
        return;
    L5:
        if (r2 >= 1.0f) goto L10;
        this.halfExpandedRatio = r2;
        if (this.viewRef == null) goto L12;
        calculateHalfExpandedOffset();
        return;
    }

    public void setHideFriction(float r1) {
        this.hideFriction = r1;
    }

    public void setHideable(boolean r2) {
        if (this.hideable == r2) goto L11;
        this.hideable = r2;
        if (r2 == false) goto L7;
    L9:
        updateAccessibilityActions();
        return;
    L7:
        if (this.state != 5) goto L9;
        setState(4);
        goto L9
    }

    public void setHideableInternal(boolean r1) {
        this.hideable = r1;
    }

    public void setMaxHeight(int r1) {
        this.maxHeight = r1;
    }

    public void setMaxWidth(int r1) {
        this.maxWidth = r1;
    }

    public void setPeekHeight(int r2) {
        setPeekHeight(r2, false);
    }

    public final void setPeekHeight(int r3, boolean r4) {
        boolean r0 = this.peekHeightAuto;
        if (r3 != (-1)) goto L6;
        if (r0 == true) goto L10;
        this.peekHeightAuto = true;
    L12:
        updatePeekHeight(r4);
        return;
    L10:
        return;
    L6:
        if (r0 == false) goto L8;
    L11:
        this.peekHeightAuto = false;
        this.peekHeight = Math.max(0, r3);
        goto L12
    L8:
        if (this.peekHeight != r3) goto L11;
    }

    public void setSaveFlags(int r1) {
        this.saveFlags = r1;
    }

    public void setShouldRemoveExpandedCorners(boolean r2) {
        if (this.shouldRemoveExpandedCorners == r2) goto L6;
        this.shouldRemoveExpandedCorners = r2;
        updateDrawableForTargetState(getState(), true);
        return;
    }

    public void setSignificantVelocityThreshold(int r1) {
        this.significantVelocityThreshold = r1;
    }

    public void setSkipCollapsed(boolean r1) {
        this.skipCollapsed = r1;
    }

    public void setState(int r4) {
        if (r4 != 1) goto L5;
    L30:
        StringBuilder r1 = new StringBuilder("STATE_");
        if (r4 != 1) goto L33;
        String r42 = "DRAGGING";
    L34:
        r1.append(r42);
        r1.append(" should not be set externally.");
        throw new IllegalArgumentException(r1.toString());
    L33:
        r42 = "SETTLING";
        goto L34
    L5:
        if (r4 == 2) goto L30;
        if (this.hideable == true) goto L14;
        if (r4 != 5) goto L14;
        Log.w(TAG, "Cannot set state: " + r4);
        return;
    L14:
        if (r4 == 6) goto L16;
    L20:
        int r0 = r4;
    L21:
        WeakReference<V> r12 = this.viewRef;
        if (r12 != null) goto L24;
    L28:
        setStateInternal(r4);
        return;
    L24:
        if (r12.get() == null) goto L28;
        V r43 = this.viewRef.get();
        runAfterLayout(r43, new AnonymousClass1(this, r43, r0));
        return;
    L16:
        if (this.fitToContents == false) goto L20;
        if (getTopOffsetForState(r4) > this.fitToContentsOffset) goto L20;
        r0 = 3;
        goto L21
    }

    public void setStateInternal(int r8) {
        if (this.state == r8) goto L33;
        this.state = r8;
        if (r8 == 4) goto L12;
        if (r8 == 3) goto L12;
        if (r8 == 6) goto L12;
        if (this.hideable == false) goto L13;
        if (r8 == 5) goto L12;
    L13:
        WeakReference<V> r4 = this.viewRef;
        if (r4 == null) goto L34;
        V r42 = r4.get();
        if (r42 != null) goto L19;
        return;
    L19:
        int r5 = 0;
        if (r8 != 3) goto L22;
        updateImportantForAccessibility(true);
    L26:
        updateDrawableForTargetState(r8, true);
    L28:
        if (r5 >= this.callbacks.size()) goto L30;
        this.callbacks.get(r5).onStateChanged(r42, r8);
        r5 = r5 + 1;
        goto L28
    L30:
        updateAccessibilityActions();
        return;
    L22:
        if (r8 == 6) goto L25;
        if (r8 == 5) goto L25;
        if (r8 != 4) goto L26;
    L25:
        updateImportantForAccessibility(false);
        goto L26
    L34:
        return;
    L12:
        this.lastStableState = r8;
        goto L13
    }

    public void setUpdateImportantForAccessibilityOnSiblings(boolean r1) {
        this.updateImportantForAccessibilityOnSiblings = r1;
    }

    public boolean shouldExpandOnUpwardDrag(long r1, float r3) {
        return false;
    }

    public boolean shouldHide(View r5, float r6) {
        if (this.skipCollapsed == false) goto L6;
        return true;
    L6:
        if (isHideableWhenDragging() == true) goto L9;
        return false;
    L9:
        if (r5.getTop() >= this.collapsedOffset) goto L12;
        return false;
    L12:
        if ((Math.abs((r5.getTop() + (r6 * this.hideFriction)) - this.collapsedOffset) / calculatePeekHeight()) <= HIDE_THRESHOLD) goto L14;
        return true;
    L14:
        return false;
    }

    public boolean shouldSkipHalfExpandedStateWhenDragging() {
        return false;
    }

    public boolean shouldSkipSmoothAnimation() {
        return true;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void startBackProgress(BackEventCompat r1) {
        MaterialBottomContainerBackHelper r0 = this.bottomContainerBackHelper;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.startBackProgress(r1);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void updateBackProgress(BackEventCompat r1) {
        MaterialBottomContainerBackHelper r0 = this.bottomContainerBackHelper;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.updateBackProgress(r1);
    }
}
