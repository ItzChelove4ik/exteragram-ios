package com.google.android.material.sidesheet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.activity.BackEventCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MaterialSideContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import kotlin.text.CharsKt__CharKt$$ExternalSyntheticBUOutline0;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.WearAuthSheet$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class SideSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> implements Sheet<SideSheetCallback> {
    private static final int DEFAULT_ACCESSIBILITY_PANE_TITLE = 0;
    private static final int DEF_STYLE_RES = 0;
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    private static final int NO_MAX_SIZE = -1;
    static final int SIGNIFICANT_VEL_THRESHOLD = 500;
    private ColorStateList backgroundTint;
    private final Set<SideSheetCallback> callbacks;
    private int childWidth;
    private int coplanarSiblingViewId;
    private WeakReference<View> coplanarSiblingViewRef;
    private final ViewDragHelper.Callback dragCallback;
    private boolean draggable;
    private float elevation;
    private float hideFriction;
    private boolean ignoreEvents;
    private int initialX;
    private int innerMargin;
    private int lastStableState;
    private MaterialShapeDrawable materialShapeDrawable;
    private float maximumVelocity;
    private int parentInnerEdge;
    private int parentWidth;
    private ShapeAppearanceModel shapeAppearanceModel;
    private SheetDelegate sheetDelegate;
    private MaterialSideContainerBackHelper sideContainerBackHelper;
    private int state;
    private final SideSheetBehavior<V>.StateSettlingTracker stateSettlingTracker;
    private VelocityTracker velocityTracker;
    private ViewDragHelper viewDragHelper;
    private WeakReference<V> viewRef;

    /* renamed from: com.google.android.material.sidesheet.SideSheetBehavior$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewDragHelper.Callback {
        final /* synthetic */ SideSheetBehavior this$0;

        public AnonymousClass1(SideSheetBehavior r1) {
            this.this$0 = r1;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View r1, int r2, int r3) {
            return MathUtils.clamp(r2, SideSheetBehavior.access$200(this.this$0).getMinViewPositionHorizontal(), SideSheetBehavior.access$200(this.this$0).getMaxViewPositionHorizontal());
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View r1, int r2, int r3) {
            return r1.getTop();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View r1) {
            return SideSheetBehavior.access$700(this.this$0) + this.this$0.getInnerMargin();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int r2) {
            if (r2 == 1) goto L5;
            return;
        L5:
            if (SideSheetBehavior.access$400(this.this$0) == false) goto L9;
            this.this$0.setStateInternal(1);
            return;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View r3, int r4, int r5, int r6, int r7) {
            View r52 = this.this$0.getCoplanarSiblingView();
            if (r52 == null) goto L7;
            ViewGroup.MarginLayoutParams r62 = (ViewGroup.MarginLayoutParams) r52.getLayoutParams();
            if (r62 == null) goto L7;
            SideSheetBehavior.access$200(this.this$0).updateCoplanarSiblingLayoutParams(r62, r3.getLeft(), r3.getRight());
            r52.setLayoutParams(r62);
        L7:
            SideSheetBehavior.access$300(this.this$0, r3, r4);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View r2, float r3, float r4) {
            int r32 = SideSheetBehavior.access$500(this.this$0, r2, r3, r4);
            SideSheetBehavior r1 = this.this$0;
            SideSheetBehavior.access$600(r1, r2, r32, r1.shouldSkipSmoothAnimation());
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View r3, int r4) {
            if (SideSheetBehavior.access$000(this.this$0) != 1) goto L6;
            return false;
        L6:
            if (SideSheetBehavior.access$100(this.this$0) != null) goto L8;
        L10:
            return false;
        L8:
            if (SideSheetBehavior.access$100(this.this$0).get() != r3) goto L10;
            return true;
        }
    }

    /* renamed from: com.google.android.material.sidesheet.SideSheetBehavior$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ SideSheetBehavior this$0;

        public AnonymousClass2(SideSheetBehavior r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.setStateInternal(5);
            if (SideSheetBehavior.access$100(this.this$0) != null) goto L5;
            return;
        L5:
            if (SideSheetBehavior.access$100(this.this$0).get() == null) goto L9;
            ((View) SideSheetBehavior.access$100(this.this$0).get()).requestLayout();
            return;
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        final int state;

        /* renamed from: com.google.android.material.sidesheet.SideSheetBehavior$SavedState$1, reason: invalid class name */
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

        public SavedState(Parcel r1, ClassLoader r2) {
            super(r1, r2);
            this.state = r1.readInt();
        }

        public SavedState(Parcelable r1, SideSheetBehavior<?> r2) {
            super(r1);
            this.state = SideSheetBehavior.access$000(r2);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            super.writeToParcel(r1, r2);
            r1.writeInt(this.state);
        }
    }

    public class StateSettlingTracker {
        private final Runnable continueSettlingRunnable;
        private boolean isContinueSettlingRunnablePosted;
        private int targetState;
        final /* synthetic */ SideSheetBehavior this$0;

        /* renamed from: $r8$lambda$moQjjbcBo6A-pC5WH96319nIACo, reason: not valid java name */
        public static /* synthetic */ void m1900$r8$lambda$moQjjbcBo6ApC5WH96319nIACo(StateSettlingTracker r2) {
            r2.isContinueSettlingRunnablePosted = false;
            if (SideSheetBehavior.access$800(r2.this$0) == null) goto L9;
            if (SideSheetBehavior.access$800(r2.this$0).continueSettling(true) == false) goto L9;
            r2.continueSettlingToState(r2.targetState);
            return;
        L9:
            if (SideSheetBehavior.access$000(r2.this$0) != 2) goto L12;
            r2.this$0.setStateInternal(r2.targetState);
            return;
        }

        public StateSettlingTracker(SideSheetBehavior r1) {
            this.this$0 = r1;
            this.continueSettlingRunnable = new SideSheetBehavior$StateSettlingTracker$$ExternalSyntheticLambda0(this);
        }

        public void continueSettlingToState(int r2) {
            if (SideSheetBehavior.access$100(this.this$0) != null) goto L5;
            return;
        L5:
            if (SideSheetBehavior.access$100(this.this$0).get() == null) goto L13;
            this.targetState = r2;
            if (this.isContinueSettlingRunnablePosted == true) goto L12;
            ((View) SideSheetBehavior.access$100(this.this$0).get()).postOnAnimation(this.continueSettlingRunnable);
            this.isContinueSettlingRunnablePosted = true;
            return;
        L12:
            return;
        }
    }

    public static /* synthetic */ boolean $r8$lambda$eRzu7MgSiEjwRPskR15qKusJnfM(SideSheetBehavior r0, int r1, View r2, AccessibilityViewCommand.CommandArguments r3) {
        r0.setState(r1);
        return true;
    }

    public static /* synthetic */ void $r8$lambda$o7Vv2e9fpJtQ0OuFkAQda6gk1mg(SideSheetBehavior r2, int r3) {
        WeakReference<V> r0 = r2.viewRef;
        if (r0 == null) goto L5;
        V r02 = r0.get();
    L6:
        if (r02 == null) goto L9;
        r2.startSettling(r02, r3, false);
        return;
    L9:
        return;
    L5:
        r02 = null;
        goto L6
    }

    /* renamed from: $r8$lambda$v-g37JzyumMkESCn5K-JfiWrmBw, reason: not valid java name */
    public static /* synthetic */ void m1899$r8$lambda$vg37JzyumMkESCn5KJfiWrmBw(SideSheetBehavior r1, ViewGroup.MarginLayoutParams r2, int r3, View r4, ValueAnimator r5) {
        r1.sheetDelegate.updateCoplanarSiblingAdjacentMargin(r2, AnimationUtils.lerp(r3, 0, r5.getAnimatedFraction()));
        r4.requestLayout();
    }

    static {
        DEFAULT_ACCESSIBILITY_PANE_TITLE = R.string.side_sheet_accessibility_pane_title;
        DEF_STYLE_RES = R.style.Widget_Material3_SideSheet;
    }

    public SideSheetBehavior() {
        this.stateSettlingTracker = new StateSettlingTracker(this);
        this.draggable = true;
        this.state = 5;
        this.lastStableState = 5;
        this.hideFriction = HIDE_FRICTION;
        this.coplanarSiblingViewId = -1;
        this.callbacks = new LinkedHashSet();
        this.dragCallback = new AnonymousClass1(this);
    }

    public SideSheetBehavior(Context r6, AttributeSet r7) {
        super(r6, r7);
        this.stateSettlingTracker = new StateSettlingTracker(this);
        this.draggable = true;
        this.state = 5;
        this.lastStableState = 5;
        this.hideFriction = HIDE_FRICTION;
        this.coplanarSiblingViewId = -1;
        this.callbacks = new LinkedHashSet();
        this.dragCallback = new AnonymousClass1(this);
        TypedArray r2 = r6.obtainStyledAttributes(r7, R.styleable.SideSheetBehavior_Layout);
        if (r2.hasValue(R.styleable.SideSheetBehavior_Layout_backgroundTint) == false) goto L6;
        this.backgroundTint = MaterialResources.getColorStateList(r6, r2, R.styleable.SideSheetBehavior_Layout_backgroundTint);
    L6:
        if (r2.hasValue(R.styleable.SideSheetBehavior_Layout_shapeAppearance) == false) goto L9;
        this.shapeAppearanceModel = ShapeAppearanceModel.builder(r6, r7, 0, DEF_STYLE_RES).build();
    L9:
        if (r2.hasValue(R.styleable.SideSheetBehavior_Layout_coplanarSiblingViewId) == false) goto L11;
        setCoplanarSiblingViewId(r2.getResourceId(R.styleable.SideSheetBehavior_Layout_coplanarSiblingViewId, -1));
    L11:
        createMaterialShapeDrawableIfNeeded(r6);
        this.elevation = r2.getDimension(R.styleable.SideSheetBehavior_Layout_android_elevation, -1.0f);
        setDraggable(r2.getBoolean(R.styleable.SideSheetBehavior_Layout_behavior_draggable, true));
        r2.recycle();
        this.maximumVelocity = ViewConfiguration.get(r6).getScaledMaximumFlingVelocity();
    }

    public static /* synthetic */ int access$000(SideSheetBehavior r0) {
        return r0.state;
    }

    public static /* synthetic */ WeakReference access$100(SideSheetBehavior r0) {
        return r0.viewRef;
    }

    public static /* synthetic */ SheetDelegate access$200(SideSheetBehavior r0) {
        return r0.sheetDelegate;
    }

    public static /* synthetic */ void access$300(SideSheetBehavior r0, View r1, int r2) {
        r0.dispatchOnSlide(r1, r2);
    }

    public static /* synthetic */ boolean access$400(SideSheetBehavior r0) {
        return r0.draggable;
    }

    public static /* synthetic */ int access$500(SideSheetBehavior r0, View r1, float r2, float r3) {
        return r0.calculateTargetStateOnViewReleased(r1, r2, r3);
    }

    public static /* synthetic */ void access$600(SideSheetBehavior r0, View r1, int r2, boolean r3) {
        r0.startSettling(r1, r2, r3);
    }

    public static /* synthetic */ int access$700(SideSheetBehavior r0) {
        return r0.childWidth;
    }

    public static /* synthetic */ ViewDragHelper access$800(SideSheetBehavior r0) {
        return r0.viewDragHelper;
    }

    private int calculateCurrentOffset(int r3, V r4) {
        int r0 = this.state;
        if (r0 == 1) goto L17;
        if (r0 == 2) goto L17;
        if (r0 != 3) goto L9;
        return 0;
    L9:
        if (r0 == 5) goto L11;
        WearAuthSheet$$ExternalSyntheticBUOutline0.m("Unexpected value: ", this.state);
        return 0;
    L11:
        return this.sheetDelegate.getHiddenOffset();
    L17:
        return r3 - this.sheetDelegate.getOuterEdge(r4);
    }

    private float calculateDragDistance(float r1, float r2) {
        return Math.abs(r1 - r2);
    }

    private int calculateTargetStateOnViewReleased(View r4, float r5, float r6) {
        if (isExpandingOutwards(r5) == false) goto L6;
        return 3;
    L6:
        if (shouldHide(r4, r5) == false) goto L15;
        if (this.sheetDelegate.isSwipeSignificant(r5, r6) == false) goto L10;
    L13:
        return 5;
    L10:
        if (this.sheetDelegate.isReleasedCloseToInnerEdge(r4) == true) goto L13;
        return 3;
    L15:
        if (r5 != 0.0f) goto L17;
    L20:
        int r42 = r4.getLeft();
        if (Math.abs(r42 - getExpandedOffset()) >= Math.abs(r42 - this.sheetDelegate.getHiddenOffset())) goto L23;
        return 3;
    L23:
        return 5;
    L17:
        if (SheetUtils.isSwipeMostlyHorizontal(r5, r6) == false) goto L20;
        return 5;
    }

    private void clearCoplanarSiblingView() {
        WeakReference<View> r0 = this.coplanarSiblingViewRef;
        if (r0 == null) goto L5;
        r0.clear();
    L5:
        this.coplanarSiblingViewRef = null;
    }

    private AccessibilityViewCommand createAccessibilityViewCommandForState(int r2) {
        return new SideSheetBehavior$$ExternalSyntheticLambda1(this, r2);
    }

    private void createMaterialShapeDrawableIfNeeded(Context r4) {
        if (this.shapeAppearanceModel != null) goto L5;
        return;
    L5:
        MaterialShapeDrawable r0 = new MaterialShapeDrawable(this.shapeAppearanceModel);
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

    private void dispatchOnSlide(View r2, int r3) {
        if (this.callbacks.isEmpty() == true) goto L8;
        float r32 = this.sheetDelegate.calculateSlideOffset(r3);
        Iterator<SideSheetCallback> r1 = this.callbacks.iterator();
    L6:
        if (r1.hasNext() == false) goto L10;
        r1.next().onSlide(r2, r32);
        goto L6
    L10:
        return;
    }

    private void ensureAccessibilityPaneTitleIsSet(View r2) {
        if (ViewCompat.getAccessibilityPaneTitle(r2) != null) goto L6;
        ViewCompat.setAccessibilityPaneTitle(r2, r2.getResources().getString(DEFAULT_ACCESSIBILITY_PANE_TITLE));
        return;
    }

    public static <V extends View> SideSheetBehavior<V> from(V r2) {
        ViewGroup.LayoutParams r22 = r2.getLayoutParams();
        if ((r22 instanceof CoordinatorLayout.LayoutParams) == false) goto L10;
        CoordinatorLayout.Behavior r23 = ((CoordinatorLayout.LayoutParams) r22).getBehavior();
        if ((r23 instanceof SideSheetBehavior) == true) goto L7;
        f$$ExternalSyntheticBUOutline1.m("The view is not associated with SideSheetBehavior");
        return null;
    L7:
        return (SideSheetBehavior) r23;
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

    private ValueAnimator.AnimatorUpdateListener getCoplanarFinishAnimatorUpdateListener() {
        View r0 = getCoplanarSiblingView();
        if (r0 != null) goto L5;
        return null;
    L5:
        ViewGroup.MarginLayoutParams r2 = (ViewGroup.MarginLayoutParams) r0.getLayoutParams();
        if (r2 != null) goto L9;
        return null;
    L9:
        return new SideSheetBehavior$$ExternalSyntheticLambda2(this, r2, this.sheetDelegate.getCoplanarSiblingAdjacentMargin(r2), r0);
    }

    private int getGravityFromSheetEdge() {
        SheetDelegate r1 = this.sheetDelegate;
        if (r1 != null) goto L5;
        return 5;
    L5:
        if (r1.getSheetEdge() != 0) goto L7;
        return 5;
    L7:
        return 3;
    }

    private CoordinatorLayout.LayoutParams getViewLayoutParams() {
        WeakReference<V> r1 = this.viewRef;
        if (r1 == null) goto L10;
        V r12 = r1.get();
        if (r12 != null) goto L7;
        return null;
    L7:
        if ((r12.getLayoutParams() instanceof CoordinatorLayout.LayoutParams) == true) goto L9;
        return null;
    L9:
        return (CoordinatorLayout.LayoutParams) r12.getLayoutParams();
    L10:
        return null;
    }

    private boolean hasLeftMargin() {
        CoordinatorLayout.LayoutParams r0 = getViewLayoutParams();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (((ViewGroup.MarginLayoutParams) r0).leftMargin <= 0) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean hasRightMargin() {
        CoordinatorLayout.LayoutParams r0 = getViewLayoutParams();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (((ViewGroup.MarginLayoutParams) r0).rightMargin <= 0) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isDraggedFarEnough(MotionEvent r3) {
        if (shouldHandleDraggingWithHelper() == true) goto L6;
        return false;
    L6:
        if (calculateDragDistance(this.initialX, r3.getX()) <= this.viewDragHelper.getTouchSlop()) goto L9;
        return true;
    L9:
        return false;
    }

    private boolean isExpandingOutwards(float r1) {
        return this.sheetDelegate.isExpandingOutwards(r1);
    }

    private boolean isLayingOut(V r1) {
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

    private boolean isSettling(View r1, int r2, boolean r3) {
        int r22 = getOuterEdgeOffsetForState(r2);
        ViewDragHelper r0 = getViewDragHelper();
        if (r0 == null) goto L12;
        if (r3 == false) goto L9;
        if (r0.settleCapturedViewAt(r22, r1.getTop()) == false) goto L15;
        return true;
    L15:
        return false;
    L9:
        if (r0.smoothSlideViewTo(r1, r22, r1.getTop()) == false) goto L16;
        return true;
    L16:
        return false;
    L12:
        return false;
    }

    private void maybeAssignCoplanarSiblingViewBasedId(CoordinatorLayout r3) {
        if (this.coplanarSiblingViewRef != null) goto L10;
        int r0 = this.coplanarSiblingViewId;
        if (r0 == (-1)) goto L11;
        View r32 = r3.findViewById(r0);
        if (r32 == null) goto L12;
        this.coplanarSiblingViewRef = new WeakReference(r32);
        return;
    L12:
        return;
    L11:
        return;
    }

    private void replaceAccessibilityActionForState(V r2, AccessibilityNodeInfoCompat.AccessibilityActionCompat r3, int r4) {
        ViewCompat.replaceAccessibilityAction(r2, r3, null, createAccessibilityViewCommandForState(r4));
    }

    private void resetVelocity() {
        VelocityTracker r0 = this.velocityTracker;
        if (r0 == null) goto L6;
        r0.recycle();
        this.velocityTracker = null;
        return;
    }

    private void runAfterLayout(V r1, Runnable r2) {
        if (isLayingOut(r1) == false) goto L6;
        r1.post(r2);
        return;
    L6:
        r2.run();
    }

    private void setSheetEdge(int r3) {
        SheetDelegate r0 = this.sheetDelegate;
        if (r0 == null) goto L7;
        if (r0.getSheetEdge() != r3) goto L7;
        return;
    L7:
        if (r3 != 0) goto L15;
        this.sheetDelegate = new RightSheetDelegate(this);
        if (this.shapeAppearanceModel != null) goto L11;
        return;
    L11:
        if (hasRightMargin() == true) goto L26;
        ShapeAppearanceModel.Builder r32 = this.shapeAppearanceModel.toBuilder();
        r32.setTopRightCornerSize(0.0f).setBottomRightCornerSize(0.0f);
        updateMaterialShapeDrawable(r32.build());
        return;
    L26:
        return;
    L15:
        if (r3 != 1) goto L22;
        this.sheetDelegate = new LeftSheetDelegate(this);
        if (this.shapeAppearanceModel != null) goto L19;
        return;
    L19:
        if (hasLeftMargin() == true) goto L28;
        ShapeAppearanceModel.Builder r33 = this.shapeAppearanceModel.toBuilder();
        r33.setTopLeftCornerSize(0.0f).setBottomLeftCornerSize(0.0f);
        updateMaterialShapeDrawable(r33.build());
        return;
    L28:
        return;
    L22:
        CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("Invalid sheet edge position value: ", r3, ". Must be 0 or 1.");
    }

    private void setSheetEdge(V r1, int r2) {
        if (Gravity.getAbsoluteGravity(((CoordinatorLayout.LayoutParams) r1.getLayoutParams()).gravity, r2) != 3) goto L5;
        int r12 = 1;
    L6:
        setSheetEdge(r12);
        return;
    L5:
        r12 = 0;
        goto L6
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

    private boolean shouldInterceptTouchEvent(V r2) {
        if (r2.isShown() == true) goto L7;
        if (ViewCompat.getAccessibilityPaneTitle(r2) != null) goto L7;
        return false;
    L7:
        if (this.draggable == false) goto L12;
        return true;
    L12:
        return false;
    }

    private void startSettling(View r1, int r2, boolean r3) {
        if (isSettling(r1, r2, r3) == false) goto L6;
        setStateInternal(2);
        this.stateSettlingTracker.continueSettlingToState(r2);
        return;
    L6:
        setStateInternal(r2);
    }

    private void updateAccessibilityActions() {
        WeakReference<V> r0 = this.viewRef;
        if (r0 == null) goto L16;
        V r02 = r0.get();
        if (r02 == null) goto L17;
        ViewCompat.removeAccessibilityAction(r02, 262144);
        ViewCompat.removeAccessibilityAction(r02, 1048576);
        if (this.state == 5) goto L12;
        replaceAccessibilityActionForState(r02, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, 5);
    L12:
        if (this.state == 3) goto L15;
        replaceAccessibilityActionForState(r02, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, 3);
        return;
    L15:
        return;
    L17:
        return;
    }

    private void updateCoplanarSiblingBackProgress() {
        WeakReference<V> r0 = this.viewRef;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.get() == null) goto L16;
        V r02 = this.viewRef.get();
        View r1 = getCoplanarSiblingView();
        if (r1 == null) goto L17;
        ViewGroup.MarginLayoutParams r2 = (ViewGroup.MarginLayoutParams) r1.getLayoutParams();
        if (r2 == null) goto L18;
        this.sheetDelegate.updateCoplanarSiblingAdjacentMargin(r2, (int) ((this.childWidth * r02.getScaleX()) + this.innerMargin));
        r1.requestLayout();
        return;
    L18:
        return;
    L17:
        return;
    }

    private void updateMaterialShapeDrawable(ShapeAppearanceModel r1) {
        MaterialShapeDrawable r0 = this.materialShapeDrawable;
        if (r0 == null) goto L6;
        r0.setShapeAppearanceModel(r1);
        return;
    }

    private void updateSheetVisibility(View r2) {
        if (this.state != 5) goto L5;
        int r1 = 4;
    L7:
        if (r2.getVisibility() == r1) goto L10;
        r2.setVisibility(r1);
        return;
    L10:
        return;
    L5:
        r1 = 0;
        goto L7
    }

    @Override // com.google.android.material.sidesheet.Sheet
    public /* bridge */ /* synthetic */ void addCallback(SheetCallback r1) {
        addCallback((SideSheetCallback) r1);
    }

    public void addCallback(SideSheetCallback r1) {
        this.callbacks.add(r1);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void cancelBackProgress() {
        MaterialSideContainerBackHelper r0 = this.sideContainerBackHelper;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.cancelBackProgress();
    }

    public void expand() {
        setState(3);
    }

    public MaterialSideContainerBackHelper getBackHelper() {
        return this.sideContainerBackHelper;
    }

    public int getChildWidth() {
        return this.childWidth;
    }

    public View getCoplanarSiblingView() {
        WeakReference<View> r0 = this.coplanarSiblingViewRef;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.get();
    }

    public int getExpandedOffset() {
        return this.sheetDelegate.getExpandedOffset();
    }

    public float getHideFriction() {
        return this.hideFriction;
    }

    public float getHideThreshold() {
        return HIDE_THRESHOLD;
    }

    public int getInnerMargin() {
        return this.innerMargin;
    }

    public int getLastStableState() {
        return this.lastStableState;
    }

    public int getOuterEdgeOffsetForState(int r2) {
        if (r2 == 3) goto L11;
        if (r2 == 5) goto L7;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Invalid state to get outer edge offset: ", r2);
        return 0;
    L7:
        return this.sheetDelegate.getHiddenOffset();
    L11:
        return getExpandedOffset();
    }

    public int getParentInnerEdge() {
        return this.parentInnerEdge;
    }

    public int getParentWidth() {
        return this.parentWidth;
    }

    public int getSignificantVelocityThreshold() {
        return 500;
    }

    @Override // com.google.android.material.sidesheet.Sheet
    public int getState() {
        return this.state;
    }

    public ViewDragHelper getViewDragHelper() {
        return this.viewDragHelper;
    }

    public float getXVelocity() {
        VelocityTracker r0 = this.velocityTracker;
        if (r0 != null) goto L6;
        return 0.0f;
    L6:
        r0.computeCurrentVelocity(1000, this.maximumVelocity);
        return this.velocityTracker.getXVelocity();
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void handleBackInvoked() {
        MaterialSideContainerBackHelper r0 = this.sideContainerBackHelper;
        if (r0 != null) goto L5;
        return;
    L5:
        BackEventCompat r02 = r0.onHandleBackInvoked();
        if (r02 != null) goto L8;
    L12:
        setState(5);
        return;
    L8:
        if (Build.VERSION.SDK_INT < 34) goto L12;
        this.sideContainerBackHelper.finishBackProgress(r02, getGravityFromSheetEdge(), new AnonymousClass2(this), getCoplanarFinishAnimatorUpdateListener());
    }

    public void hide() {
        setState(5);
    }

    public boolean isDraggable() {
        return this.draggable;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams r1) {
        super.onAttachedToLayoutParams(r1);
        this.viewRef = null;
        this.viewDragHelper = null;
        this.sideContainerBackHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.viewRef = null;
        this.viewDragHelper = null;
        this.sideContainerBackHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout r3, V r4, MotionEvent r5) {
        if (shouldInterceptTouchEvent(r4) == true) goto L6;
        this.ignoreEvents = true;
        return false;
    L6:
        int r32 = r5.getActionMasked();
        if (r32 != 0) goto L10;
        resetVelocity();
    L10:
        if (this.velocityTracker != null) goto L12;
        this.velocityTracker = VelocityTracker.obtain();
    L12:
        this.velocityTracker.addMovement(r5);
        if (r32 == 0) goto L22;
        if (r32 == 1) goto L19;
        if (r32 == 3) goto L19;
    L24:
        if (this.ignoreEvents == true) goto L30;
        ViewDragHelper r2 = this.viewDragHelper;
        if (r2 == null) goto L30;
        if (r2.shouldInterceptTouchEvent(r5) == false) goto L30;
        return true;
    L30:
        return false;
    L19:
        if (this.ignoreEvents == false) goto L24;
        this.ignoreEvents = false;
        return false;
    L22:
        this.initialX = (int) r5.getX();
        goto L24
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout r5, V r6, int r7) {
        if (r5.getFitsSystemWindows() == false) goto L8;
        if (r6.getFitsSystemWindows() == true) goto L8;
        r6.setFitsSystemWindows(true);
    L8:
        if (this.viewRef != null) goto L22;
        this.viewRef = new WeakReference(r6);
        this.sideContainerBackHelper = new MaterialSideContainerBackHelper(r6);
        MaterialShapeDrawable r0 = this.materialShapeDrawable;
        if (r0 == null) goto L15;
        r6.setBackground(r0);
        MaterialShapeDrawable r02 = this.materialShapeDrawable;
        float r2 = this.elevation;
        if (r2 != (-1.0f)) goto L14;
        r2 = r6.getElevation();
    L14:
        r02.setElevation(r2);
    L18:
        updateSheetVisibility(r6);
        updateAccessibilityActions();
        if (r6.getImportantForAccessibility() != 0) goto L21;
        r6.setImportantForAccessibility(1);
    L21:
        ensureAccessibilityPaneTitleIsSet(r6);
        goto L22
    L15:
        ColorStateList r03 = this.backgroundTint;
        if (r03 == null) goto L18;
        ViewCompat.setBackgroundTintList(r6, r03);
    L22:
        setSheetEdge(r6, r7);
        if (this.viewDragHelper != null) goto L25;
        this.viewDragHelper = ViewDragHelper.create(r5, this.dragCallback);
    L25:
        int r04 = this.sheetDelegate.getOuterEdge(r6);
        r5.onLayoutChild(r6, r7);
        this.parentWidth = r5.getWidth();
        this.parentInnerEdge = this.sheetDelegate.getParentInnerEdge(r5);
        this.childWidth = r6.getWidth();
        ViewGroup.MarginLayoutParams r72 = (ViewGroup.MarginLayoutParams) r6.getLayoutParams();
        if (r72 == null) goto L28;
        int r73 = this.sheetDelegate.calculateInnerMargin(r72);
    L29:
        this.innerMargin = r73;
        ViewCompat.offsetLeftAndRight(r6, calculateCurrentOffset(r04, r6));
        maybeAssignCoplanarSiblingViewBasedId(r5);
        Iterator<SideSheetCallback> r4 = this.callbacks.iterator();
    L31:
        if (r4.hasNext() == false) goto L35;
        SideSheetCallback r52 = r4.next();
        if ((r52 instanceof SideSheetCallback) == false) goto L31;
        r52.onLayout(r6);
        goto L31
    L35:
        return true;
    L28:
        r73 = 0;
        goto L29
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onMeasureChild(CoordinatorLayout r4, V r5, int r6, int r7, int r8, int r9) {
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) r5.getLayoutParams();
        r5.measure(getChildMeasureSpec(r6, (((r4.getPaddingLeft() + r4.getPaddingRight()) + r0.leftMargin) + r0.rightMargin) + r7, -1, r0.width), getChildMeasureSpec(r8, (((r4.getPaddingTop() + r4.getPaddingBottom()) + r0.topMargin) + r0.bottomMargin) + r9, -1, r0.height));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onRestoreInstanceState(CoordinatorLayout r2, V r3, Parcelable r4) {
        SavedState r42 = (SavedState) r4;
        if (r42.getSuperState() == null) goto L5;
        super.onRestoreInstanceState(r2, r3, r42.getSuperState());
    L5:
        int r22 = r42.state;
        if (r22 != 1) goto L8;
    L9:
        r22 = 5;
    L10:
        this.state = r22;
        this.lastStableState = r22;
        return;
    L8:
        if (r22 != 2) goto L10;
        goto L9
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public Parcelable onSaveInstanceState(CoordinatorLayout r2, V r3) {
        return new SavedState(super.onSaveInstanceState(r2, r3), this);
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
        resetVelocity();
    L16:
        if (this.velocityTracker != null) goto L18;
        this.velocityTracker = VelocityTracker.obtain();
    L18:
        this.velocityTracker.addMovement(r5);
        if (shouldHandleDraggingWithHelper() == false) goto L28;
        if (r32 != 2) goto L28;
        if (this.ignoreEvents == true) goto L28;
        if (isDraggedFarEnough(r5) == false) goto L28;
        this.viewDragHelper.captureChildView(r4, r5.getPointerId(r5.getActionIndex()));
    L28:
        return !this.ignoreEvents;
    }

    @Override // com.google.android.material.sidesheet.Sheet
    public /* bridge */ /* synthetic */ void removeCallback(SheetCallback r1) {
        removeCallback((SideSheetCallback) r1);
    }

    public void removeCallback(SideSheetCallback r1) {
        this.callbacks.remove(r1);
    }

    public void setCoplanarSiblingView(View r2) {
        this.coplanarSiblingViewId = -1;
        if (r2 != null) goto L6;
        clearCoplanarSiblingView();
        return;
    L6:
        this.coplanarSiblingViewRef = new WeakReference(r2);
        WeakReference<V> r1 = this.viewRef;
        if (r1 == null) goto L12;
        V r12 = r1.get();
        if (r12.isLaidOut() == false) goto L13;
        r12.requestLayout();
        return;
    L13:
        return;
    }

    public void setCoplanarSiblingViewId(int r2) {
        this.coplanarSiblingViewId = r2;
        clearCoplanarSiblingView();
        WeakReference<V> r1 = this.viewRef;
        if (r1 == null) goto L10;
        V r12 = r1.get();
        if (r2 != (-1)) goto L7;
        return;
    L7:
        if (r12.isLaidOut() == false) goto L12;
        r12.requestLayout();
        return;
    L12:
        return;
    }

    public void setDraggable(boolean r1) {
        this.draggable = r1;
    }

    public void setHideFriction(float r1) {
        this.hideFriction = r1;
    }

    @Override // com.google.android.material.sidesheet.Sheet
    public void setState(int r4) {
        if (r4 != 1) goto L5;
    L16:
        StringBuilder r1 = new StringBuilder("STATE_");
        if (r4 != 1) goto L19;
        String r42 = "DRAGGING";
    L20:
        r1.append(r42);
        r1.append(" should not be set externally.");
        throw new IllegalArgumentException(r1.toString());
    L19:
        r42 = "SETTLING";
        goto L20
    L5:
        if (r4 == 2) goto L16;
        WeakReference<V> r0 = this.viewRef;
        if (r0 != null) goto L10;
    L14:
        setStateInternal(r4);
        return;
    L10:
        if (r0.get() == null) goto L14;
        runAfterLayout(this.viewRef.get(), new SideSheetBehavior$$ExternalSyntheticLambda0(this, r4));
    }

    public void setStateInternal(int r4) {
        if (this.state == r4) goto L23;
        this.state = r4;
        if (r4 != 3) goto L8;
    L9:
        this.lastStableState = r4;
    L10:
        WeakReference<V> r0 = this.viewRef;
        if (r0 == null) goto L24;
        V r02 = r0.get();
        if (r02 != null) goto L16;
        return;
    L16:
        updateSheetVisibility(r02);
        Iterator<SideSheetCallback> r1 = this.callbacks.iterator();
    L18:
        if (r1.hasNext() == false) goto L20;
        r1.next().onStateChanged(r02, r4);
        goto L18
    L20:
        updateAccessibilityActions();
        return;
    L24:
        return;
    L8:
        if (r4 != 5) goto L10;
    }

    public boolean shouldHide(View r1, float r2) {
        return this.sheetDelegate.shouldHide(r1, r2);
    }

    public boolean shouldSkipSmoothAnimation() {
        return true;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void startBackProgress(BackEventCompat r1) {
        MaterialSideContainerBackHelper r0 = this.sideContainerBackHelper;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.startBackProgress(r1);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void updateBackProgress(BackEventCompat r3) {
        MaterialSideContainerBackHelper r0 = this.sideContainerBackHelper;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.updateBackProgress(r3, getGravityFromSheetEdge());
        updateCoplanarSiblingBackProgress();
    }
}
