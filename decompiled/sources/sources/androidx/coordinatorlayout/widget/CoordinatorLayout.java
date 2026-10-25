package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.R$attr;
import androidx.coordinatorlayout.R$style;
import androidx.coordinatorlayout.R$styleable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SynchronizedPool;
import androidx.core.view.GravityCompat;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import androidx.webkit.WebMessageCompat$$ExternalSyntheticBUOutline0;
import com.sun.jna.Union$$ExternalSyntheticBUOutline0;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class CoordinatorLayout extends ViewGroup implements NestedScrollingParent2, NestedScrollingParent3 {
    static final Class<?>[] CONSTRUCTOR_PARAMS = null;
    static final int EVENT_NESTED_SCROLL = 1;
    static final int EVENT_PRE_DRAW = 0;
    static final int EVENT_VIEW_REMOVED = 2;
    static final String TAG = "CoordinatorLayout";
    static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR = null;
    private static final int TYPE_ON_INTERCEPT = 0;
    private static final int TYPE_ON_TOUCH = 1;
    static final String WIDGET_PACKAGE_NAME = null;
    static final ThreadLocal<Map<String, Constructor<Behavior>>> sConstructors = null;
    private static final Pools$Pool<Rect> sRectPool = null;
    private OnApplyWindowInsetsListener mApplyWindowInsetsListener;
    private final int[] mBehaviorConsumed;
    private View mBehaviorTouchView;
    private final DirectedAcyclicGraph<View> mChildDag;
    private final List<View> mDependencySortedChildren;
    private boolean mDisallowInterceptReset;
    private boolean mDrawStatusBarBackground;
    private boolean mIsAttachedToWindow;
    private int[] mKeylines;
    private WindowInsetsCompat mLastInsets;
    private boolean mNeedsPreDrawListener;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private View mNestedScrollingTarget;
    private final int[] mNestedScrollingV2ConsumedCompat;
    ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
    private OnPreDrawListener mOnPreDrawListener;
    private Paint mScrimPaint;
    private Drawable mStatusBarBackground;
    private final List<View> mTempDependenciesList;
    private final List<View> mTempList1;

    /* renamed from: androidx.coordinatorlayout.widget.CoordinatorLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements OnApplyWindowInsetsListener {
        final /* synthetic */ CoordinatorLayout this$0;

        public AnonymousClass1(CoordinatorLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r1, WindowInsetsCompat r2) {
            return this.this$0.setWindowInsets(r2);
        }
    }

    public interface AttachedBehavior {
        Behavior getBehavior();
    }

    public static abstract class Behavior<V extends View> {
        public Behavior() {
        }

        public Behavior(Context r1, AttributeSet r2) {
        }

        public static Object getTag(View r0) {
            return ((LayoutParams) r0.getLayoutParams()).mBehaviorTag;
        }

        public static void setTag(View r0, Object r1) {
            ((LayoutParams) r0.getLayoutParams()).mBehaviorTag = r1;
        }

        public boolean blocksInteractionBelow(CoordinatorLayout r1, V r2) {
            if (getScrimOpacity(r1, r2) <= 0.0f) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean getInsetDodgeRect(CoordinatorLayout r1, V r2, Rect r3) {
            return false;
        }

        public int getScrimColor(CoordinatorLayout r1, V r2) {
            return -16777216;
        }

        public float getScrimOpacity(CoordinatorLayout r1, V r2) {
            return 0.0f;
        }

        public boolean layoutDependsOn(CoordinatorLayout r1, V r2, View r3) {
            return false;
        }

        public WindowInsetsCompat onApplyWindowInsets(CoordinatorLayout r1, V r2, WindowInsetsCompat r3) {
            return r3;
        }

        public void onAttachedToLayoutParams(LayoutParams r1) {
        }

        public boolean onDependentViewChanged(CoordinatorLayout r1, V r2, View r3) {
            return false;
        }

        public void onDependentViewRemoved(CoordinatorLayout r1, V r2, View r3) {
        }

        public void onDetachedFromLayoutParams() {
        }

        public boolean onInterceptTouchEvent(CoordinatorLayout r1, V r2, MotionEvent r3) {
            return false;
        }

        public boolean onLayoutChild(CoordinatorLayout r1, V r2, int r3) {
            return false;
        }

        public boolean onMeasureChild(CoordinatorLayout r1, V r2, int r3, int r4, int r5, int r6) {
            return false;
        }

        public boolean onNestedFling(CoordinatorLayout r1, V r2, View r3, float r4, float r5, boolean r6) {
            return false;
        }

        public boolean onNestedPreFling(CoordinatorLayout r1, V r2, View r3, float r4, float r5) {
            return false;
        }

        @Deprecated
        public void onNestedPreScroll(CoordinatorLayout r1, V r2, View r3, int r4, int r5, int[] r6) {
        }

        public void onNestedPreScroll(CoordinatorLayout r1, V r2, View r3, int r4, int r5, int[] r6, int r7) {
            if (r7 != 0) goto L5;
            onNestedPreScroll(r1, r2, r3, r4, r5, r6);
            return;
        }

        @Deprecated
        public void onNestedScroll(CoordinatorLayout r1, V r2, View r3, int r4, int r5, int r6, int r7) {
        }

        @Deprecated
        public void onNestedScroll(CoordinatorLayout r1, V r2, View r3, int r4, int r5, int r6, int r7, int r8) {
            if (r8 != 0) goto L5;
            onNestedScroll(r1, r2, r3, r4, r5, r6, r7);
            return;
        }

        public void onNestedScroll(CoordinatorLayout r3, V r4, View r5, int r6, int r7, int r8, int r9, int r10, int[] r11) {
            r11[0] = r11[0] + r8;
            r11[1] = r11[1] + r9;
            onNestedScroll(r3, r4, r5, r6, r7, r8, r9, r10);
        }

        @Deprecated
        public void onNestedScrollAccepted(CoordinatorLayout r1, V r2, View r3, View r4, int r5) {
        }

        public void onNestedScrollAccepted(CoordinatorLayout r1, V r2, View r3, View r4, int r5, int r6) {
            if (r6 != 0) goto L5;
            onNestedScrollAccepted(r1, r2, r3, r4, r5);
            return;
        }

        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout r1, V r2, Rect r3, boolean r4) {
            return false;
        }

        public void onRestoreInstanceState(CoordinatorLayout r1, V r2, Parcelable r3) {
        }

        public Parcelable onSaveInstanceState(CoordinatorLayout r1, V r2) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        @Deprecated
        public boolean onStartNestedScroll(CoordinatorLayout r1, V r2, View r3, View r4, int r5) {
            return false;
        }

        public boolean onStartNestedScroll(CoordinatorLayout r1, V r2, View r3, View r4, int r5, int r6) {
            if (r6 == 0) goto L4;
            return false;
        L4:
            return onStartNestedScroll(r1, r2, r3, r4, r5);
        }

        @Deprecated
        public void onStopNestedScroll(CoordinatorLayout r1, V r2, View r3) {
        }

        public void onStopNestedScroll(CoordinatorLayout r1, V r2, View r3, int r4) {
            if (r4 != 0) goto L5;
            onStopNestedScroll(r1, r2, r3);
            return;
        }

        public boolean onTouchEvent(CoordinatorLayout r1, V r2, MotionEvent r3) {
            return false;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Deprecated
    public @interface DefaultBehavior {
        Class<? extends Behavior> value();
    }

    public class HierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        final /* synthetic */ CoordinatorLayout this$0;

        public HierarchyChangeListener(CoordinatorLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View r1, View r2) {
            ViewGroup.OnHierarchyChangeListener r0 = this.this$0.mOnHierarchyChangeListener;
            if (r0 == null) goto L6;
            r0.onChildViewAdded(r1, r2);
            return;
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View r3, View r4) {
            this.this$0.onChildViewsChanged(2);
            ViewGroup.OnHierarchyChangeListener r2 = this.this$0.mOnHierarchyChangeListener;
            if (r2 == null) goto L6;
            r2.onChildViewRemoved(r3, r4);
            return;
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int anchorGravity;
        public int dodgeInsetEdges;
        public int gravity;
        public int insetEdge;
        public int keyline;
        View mAnchorDirectChild;
        int mAnchorId;
        View mAnchorView;
        Behavior mBehavior;
        boolean mBehaviorResolved;
        Object mBehaviorTag;
        private boolean mDidAcceptNestedScrollNonTouch;
        private boolean mDidAcceptNestedScrollTouch;
        private boolean mDidBlockInteraction;
        private boolean mDidChangeAfterNestedScroll;
        int mInsetOffsetX;
        int mInsetOffsetY;
        final Rect mLastChildRect;

        public LayoutParams(int r1, int r2) {
            super(r1, r2);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.insetEdge = 0;
            this.dodgeInsetEdges = 0;
            this.mLastChildRect = new Rect();
        }

        public LayoutParams(Context r5, AttributeSet r6) {
            super(r5, r6);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.insetEdge = 0;
            this.dodgeInsetEdges = 0;
            this.mLastChildRect = new Rect();
            TypedArray r2 = r5.obtainStyledAttributes(r6, R$styleable.CoordinatorLayout_Layout);
            this.gravity = r2.getInteger(R$styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.mAnchorId = r2.getResourceId(R$styleable.CoordinatorLayout_Layout_layout_anchor, -1);
            this.anchorGravity = r2.getInteger(R$styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.keyline = r2.getInteger(R$styleable.CoordinatorLayout_Layout_layout_keyline, -1);
            this.insetEdge = r2.getInt(R$styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.dodgeInsetEdges = r2.getInt(R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            boolean r0 = r2.hasValue(R$styleable.CoordinatorLayout_Layout_layout_behavior);
            this.mBehaviorResolved = r0;
            if (r0 == false) goto L5;
            this.mBehavior = CoordinatorLayout.parseBehavior(r5, r6, r2.getString(R$styleable.CoordinatorLayout_Layout_layout_behavior));
        L5:
            r2.recycle();
            Behavior r52 = this.mBehavior;
            if (r52 == null) goto L9;
            r52.onAttachedToLayoutParams(this);
            return;
        }

        public LayoutParams(ViewGroup.LayoutParams r2) {
            super(r2);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.insetEdge = 0;
            this.dodgeInsetEdges = 0;
            this.mLastChildRect = new Rect();
        }

        public LayoutParams(ViewGroup.MarginLayoutParams r2) {
            super(r2);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.insetEdge = 0;
            this.dodgeInsetEdges = 0;
            this.mLastChildRect = new Rect();
        }

        public LayoutParams(LayoutParams r2) {
            super(r2);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.insetEdge = 0;
            this.dodgeInsetEdges = 0;
            this.mLastChildRect = new Rect();
        }

        private void resolveAnchorView(View r5, CoordinatorLayout r6) {
            View r0 = r6.findViewById(this.mAnchorId);
            this.mAnchorView = r0;
            if (r0 == null) goto L28;
            if (r0 == r6) goto L6;
            ViewParent r2 = r0.getParent();
        L12:
            if (r2 == r6) goto L25;
            if (r2 == null) goto L25;
            if (r2 == r5) goto L16;
            if ((r2 instanceof View) == false) goto L24;
            r0 = r2;
        L24:
            r2 = r2.getParent();
            goto L12
        L16:
            if (r6.isInEditMode() == false) goto L19;
            this.mAnchorDirectChild = null;
            this.mAnchorView = null;
            return;
        L19:
            Segment$$ExternalSyntheticBUOutline1.m("Anchor must not be a descendant of the anchored view");
            return;
        L25:
            this.mAnchorDirectChild = r0;
            return;
        L6:
            if (r6.isInEditMode() == false) goto L9;
            this.mAnchorDirectChild = null;
            this.mAnchorView = null;
            return;
        L9:
            Segment$$ExternalSyntheticBUOutline1.m("View can not be anchored to the the parent CoordinatorLayout");
            return;
        L28:
            if (r6.isInEditMode() == false) goto L31;
            this.mAnchorDirectChild = null;
            this.mAnchorView = null;
            return;
        L31:
            WebMessageCompat$$ExternalSyntheticBUOutline0.m("Could not find CoordinatorLayout descendant view with id ", r6.getResources().getResourceName(this.mAnchorId), " to anchor view ", r5);
        }

        private boolean shouldDodge(View r1, int r2) {
            int r12 = GravityCompat.getAbsoluteGravity(((LayoutParams) r1.getLayoutParams()).insetEdge, r2);
            if (r12 != 0) goto L5;
            return false;
        L5:
            if ((GravityCompat.getAbsoluteGravity(this.dodgeInsetEdges, r2) & r12) != r12) goto L10;
            return true;
        L10:
            return false;
        }

        private boolean verifyAnchorView(View r5, CoordinatorLayout r6) {
            if (this.mAnchorView.getId() == this.mAnchorId) goto L5;
            return false;
        L5:
            View r0 = this.mAnchorView;
            ViewParent r1 = r0.getParent();
        L6:
            if (r1 == r6) goto L16;
            if (r1 == null) goto L14;
            if (r1 == r5) goto L14;
            if ((r1 instanceof View) == false) goto L13;
            r0 = r1;
        L13:
            r1 = r1.getParent();
        L14:
            this.mAnchorDirectChild = null;
            this.mAnchorView = null;
            return false;
        L16:
            this.mAnchorDirectChild = r0;
            return true;
        }

        public boolean checkAnchorChanged() {
            if (this.mAnchorView == null) goto L5;
            return false;
        L5:
            if (this.mAnchorId == (-1)) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean dependsOn(CoordinatorLayout r2, View r3, View r4) {
            if (r4 != this.mAnchorDirectChild) goto L5;
            return true;
        L5:
            if (shouldDodge(r4, ViewCompat.getLayoutDirection(r2)) == true) goto L16;
            Behavior r1 = this.mBehavior;
            if (r1 != null) goto L9;
            return false;
        L9:
            if (r1.layoutDependsOn(r2, r3, r4) == true) goto L17;
            return false;
        L17:
            return true;
        L16:
            return true;
        }

        public boolean didBlockInteraction() {
            if (this.mBehavior != null) goto L6;
            this.mDidBlockInteraction = false;
        L6:
            return this.mDidBlockInteraction;
        }

        public View findAnchorView(CoordinatorLayout r3, View r4) {
            if (this.mAnchorId != (-1)) goto L7;
            this.mAnchorDirectChild = null;
            this.mAnchorView = null;
            return null;
        L7:
            if (this.mAnchorView != null) goto L9;
        L10:
            resolveAnchorView(r4, r3);
        L12:
            return this.mAnchorView;
        L9:
            if (verifyAnchorView(r4, r3) == true) goto L12;
            goto L10
        }

        public int getAnchorId() {
            return this.mAnchorId;
        }

        public Behavior getBehavior() {
            return this.mBehavior;
        }

        public boolean getChangedAfterNestedScroll() {
            return this.mDidChangeAfterNestedScroll;
        }

        public Rect getLastChildRect() {
            return this.mLastChildRect;
        }

        public boolean isBlockingInteractionBelow(CoordinatorLayout r3, View r4) {
            boolean r0 = this.mDidBlockInteraction;
            if (r0 == false) goto L6;
            return true;
        L6:
            Behavior r1 = this.mBehavior;
            if (r1 == null) goto L9;
            boolean r32 = r1.blocksInteractionBelow(r3, r4);
        L10:
            boolean r33 = r32 | r0;
            this.mDidBlockInteraction = r33;
            return r33;
        L9:
            r32 = false;
            goto L10
        }

        public boolean isNestedScrollAccepted(int r2) {
            if (r2 == 0) goto L10;
            if (r2 == 1) goto L8;
            return false;
        L8:
            return this.mDidAcceptNestedScrollNonTouch;
        L10:
            return this.mDidAcceptNestedScrollTouch;
        }

        public void resetChangedAfterNestedScroll() {
            this.mDidChangeAfterNestedScroll = false;
        }

        public void resetNestedScroll(int r2) {
            setNestedScrollAccepted(r2, false);
        }

        public void resetTouchBehaviorTracking() {
            this.mDidBlockInteraction = false;
        }

        public void setBehavior(Behavior r2) {
            Behavior r0 = this.mBehavior;
            if (r0 == r2) goto L10;
            if (r0 == null) goto L6;
            r0.onDetachedFromLayoutParams();
        L6:
            this.mBehavior = r2;
            this.mBehaviorTag = null;
            this.mBehaviorResolved = true;
            if (r2 == null) goto L11;
            r2.onAttachedToLayoutParams(this);
            return;
        L11:
            return;
        }

        public void setChangedAfterNestedScroll(boolean r1) {
            this.mDidChangeAfterNestedScroll = r1;
        }

        public void setLastChildRect(Rect r1) {
            this.mLastChildRect.set(r1);
        }

        public void setNestedScrollAccepted(int r2, boolean r3) {
            if (r2 != 0) goto L4;
            this.mDidAcceptNestedScrollTouch = r3;
            return;
        L4:
            if (r2 == 1) goto L6;
            return;
        L6:
            this.mDidAcceptNestedScrollNonTouch = r3;
        }
    }

    public class OnPreDrawListener implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ CoordinatorLayout this$0;

        public OnPreDrawListener(CoordinatorLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.this$0.onChildViewsChanged(0);
            return true;
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        SparseArray<Parcelable> behaviorStates;

        /* renamed from: androidx.coordinatorlayout.widget.CoordinatorLayout$SavedState$1, reason: invalid class name */
        public static class AnonymousClass1 implements Parcelable.ClassLoaderCreator<SavedState> {
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

        public SavedState(Parcel r6, ClassLoader r7) {
            super(r6, r7);
            int r0 = r6.readInt();
            int[] r1 = new int[r0];
            r6.readIntArray(r1);
            Parcelable[] r62 = r6.readParcelableArray(r7);
            this.behaviorStates = new SparseArray(r0);
            int r72 = 0;
        L3:
            if (r72 >= r0) goto L5;
            this.behaviorStates.append(r1[r72], r62[r72]);
            r72 = r72 + 1;
            goto L3
        }

        public SavedState(Parcelable r1) {
            super(r1);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r6, int r7) {
            super.writeToParcel(r6, r7);
            SparseArray<Parcelable> r0 = this.behaviorStates;
            int r1 = 0;
            if (r0 == null) goto L5;
            int r02 = r0.size();
        L6:
            r6.writeInt(r02);
            int[] r2 = new int[r02];
            Parcelable[] r3 = new Parcelable[r02];
        L7:
            if (r1 >= r02) goto L9;
            r2[r1] = this.behaviorStates.keyAt(r1);
            r3[r1] = this.behaviorStates.valueAt(r1);
            r1 = r1 + 1;
            goto L7
        L9:
            r6.writeIntArray(r2);
            r6.writeParcelableArray(r3, r7);
            return;
        L5:
            r02 = 0;
            goto L6
        }
    }

    public static class ViewElevationComparator implements Comparator<View> {
        public ViewElevationComparator() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(View r1, View r2) {
            float r0 = ViewCompat.getZ(r1);
            float r12 = ViewCompat.getZ(r2);
            if (r0 <= r12) goto L7;
            return -1;
        L7:
            if (r0 >= r12) goto L10;
            return 1;
        L10:
            return 0;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(View r1, View r2) {
            return compare2(r1, r2);
        }
    }

    static {
        Package r0 = CoordinatorLayout.class.getPackage();
        if (r0 == null) goto L5;
        String r02 = r0.getName();
    L6:
        WIDGET_PACKAGE_NAME = r02;
        TOP_SORTED_CHILDREN_COMPARATOR = new ViewElevationComparator();
        CONSTRUCTOR_PARAMS = new Class[]{Context.class, AttributeSet.class};
        sConstructors = new ThreadLocal();
        sRectPool = new Pools$SynchronizedPool(12);
        return;
    L5:
        r02 = null;
        goto L6
    }

    public CoordinatorLayout(Context r2) {
        this(r2, null);
    }

    public CoordinatorLayout(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.coordinatorLayoutStyle);
    }

    public CoordinatorLayout(Context r10, AttributeSet r11, int r12) {
        super(r10, r11, r12);
        this.mDependencySortedChildren = new ArrayList();
        this.mChildDag = new DirectedAcyclicGraph();
        this.mTempList1 = new ArrayList();
        this.mTempDependenciesList = new ArrayList();
        this.mBehaviorConsumed = new int[2];
        this.mNestedScrollingV2ConsumedCompat = new int[2];
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        int r0 = 0;
        if (r12 != 0) goto L6;
        TypedArray r1 = r10.obtainStyledAttributes(r11, R$styleable.CoordinatorLayout, 0, R$style.Widget_Support_CoordinatorLayout);
    L5:
        TypedArray r6 = r1;
        if (Build.VERSION.SDK_INT < 29) goto L12;
        if (r12 != 0) goto L11;
        CoordinatorLayout r2 = this;
        Context r3 = r10;
        r2.saveAttributeDataForStyleable(r3, R$styleable.CoordinatorLayout, r11, r6, 0, R$style.Widget_Support_CoordinatorLayout);
    L13:
        int r9 = r6.getResourceId(R$styleable.CoordinatorLayout_keylines, 0);
        if (r9 == 0) goto L18;
        Resources r102 = r3.getResources();
        r2.mKeylines = r102.getIntArray(r9);
        float r92 = r102.getDisplayMetrics().density;
        int r103 = r2.mKeylines.length;
    L16:
        if (r0 >= r103) goto L18;
        r2.mKeylines[r0] = (int) (r11[r0] * r92);
        r0 = r0 + 1;
    L18:
        r2.mStatusBarBackground = r6.getDrawable(R$styleable.CoordinatorLayout_statusBarBackground);
        r6.recycle();
        r2.setupForInsets();
        super.setOnHierarchyChangeListener(new HierarchyChangeListener(r2));
        if (ViewCompat.getImportantForAccessibility(r2) != 0) goto L23;
        ViewCompat.setImportantForAccessibility(r2, 1);
        return;
    L23:
        return;
    L11:
        r2 = this;
        r3 = r10;
        r2.saveAttributeDataForStyleable(r3, R$styleable.CoordinatorLayout, r11, r6, r12, 0);
        goto L13
    L12:
        r2 = this;
        r3 = r10;
        goto L13
    L6:
        r1 = r10.obtainStyledAttributes(r11, R$styleable.CoordinatorLayout, r12, 0);
        goto L5
    }

    private static Rect acquireTempRect() {
        Rect r0 = sRectPool.acquire();
        if (r0 == null) goto L5;
        return r0;
    L5:
        return new Rect();
    }

    private static int clamp(int r0, int r1, int r2) {
        if (r0 >= r1) goto L4;
        return r1;
    L4:
        if (r0 <= r2) goto L6;
        return r2;
    L6:
        return r0;
    }

    private void constrainChildRect(LayoutParams r6, Rect r7, int r8, int r9) {
        int r0 = getWidth();
        int r1 = getHeight();
        int r02 = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) r6).leftMargin, Math.min(r7.left, ((r0 - getPaddingRight()) - r8) - ((ViewGroup.MarginLayoutParams) r6).rightMargin));
        int r5 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) r6).topMargin, Math.min(r7.top, ((r1 - getPaddingBottom()) - r9) - ((ViewGroup.MarginLayoutParams) r6).bottomMargin));
        r7.set(r02, r5, r8 + r02, r9 + r5);
    }

    private WindowInsetsCompat dispatchApplyWindowInsetsToBehaviors(WindowInsetsCompat r5) {
        if (r5.isConsumed() == false) goto L5;
        return r5;
    L5:
        int r0 = getChildCount();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L15;
        View r2 = getChildAt(r1);
        if (ViewCompat.getFitsSystemWindows(r2) == false) goto L14;
        Behavior r3 = ((LayoutParams) r2.getLayoutParams()).getBehavior();
        if (r3 == null) goto L14;
        r5 = r3.onApplyWindowInsets(this, r2, r5);
        if (r5.isConsumed() == false) goto L14;
        return r5;
    L14:
        r1 = r1 + 1;
        goto L6
    L15:
        return r5;
    }

    private void getDesiredAnchoredChildRectWithoutConstraints(View r5, int r6, Rect r7, Rect r8, LayoutParams r9, int r10, int r11) {
        int r4 = GravityCompat.getAbsoluteGravity(resolveAnchoredChildGravity(r9.gravity), r6);
        int r52 = GravityCompat.getAbsoluteGravity(resolveGravity(r9.anchorGravity), r6);
        int r62 = r4 & 7;
        int r42 = r4 & 112;
        int r92 = r52 & 7;
        int r53 = r52 & 112;
        if (r92 == 1) goto L7;
        if (r92 == 5) goto L6;
        int r93 = r7.left;
    L9:
        if (r53 == 16) goto L13;
        if (r53 == 80) goto L12;
        int r54 = r7.top;
    L14:
        if (r62 == 1) goto L17;
        if (r62 == 5) goto L18;
        r93 = r93 - r10;
    L18:
        if (r42 == 16) goto L21;
        if (r42 == 80) goto L22;
        r54 = r54 - r11;
    L22:
        r8.set(r93, r54, r10 + r93, r11 + r54);
        return;
    L21:
        r54 = r54 - (r11 / 2);
        goto L22
    L17:
        r93 = r93 - (r10 / 2);
        goto L18
    L12:
        r54 = r7.bottom;
        goto L14
    L13:
        r54 = r7.top + (r7.height() / 2);
        goto L14
    L6:
        r93 = r7.right;
        goto L9
    L7:
        r93 = r7.left + (r7.width() / 2);
        goto L9
    }

    private int getKeyline(int r5) {
        int[] r0 = this.mKeylines;
        if (r0 != null) goto L6;
        Log.e(TAG, "No keylines defined for " + this + " - attempted index lookup " + r5);
        return 0;
    L6:
        if (r5 >= 0) goto L8;
    L12:
        Log.e(TAG, "Keyline index " + r5 + " out of range for " + this);
        return 0;
    L8:
        if (r5 >= r0.length) goto L12;
        return r0[r5];
    }

    private void getTopSortedChildren(List<View> r5) {
        r5.clear();
        boolean r0 = isChildrenDrawingOrderEnabled();
        int r1 = getChildCount();
        int r2 = r1 - 1;
    L3:
        if (r2 < 0) goto L8;
        if (r0 == false) goto L6;
        int r3 = getChildDrawingOrder(r1, r2);
    L7:
        r5.add(getChildAt(r3));
        r2 = r2 - 1;
        goto L3
    L6:
        r3 = r2;
        goto L7
    L8:
        Comparator<View> r4 = TOP_SORTED_CHILDREN_COMPARATOR;
        if (r4 == null) goto L15;
        Collections.sort(r5, r4);
        return;
    }

    private boolean hasDependencies(View r1) {
        return this.mChildDag.hasOutgoingEdges(r1);
    }

    private void layoutChild(View r8, int r9) {
        LayoutParams r0 = (LayoutParams) r8.getLayoutParams();
        Rect r4 = acquireTempRect();
        r4.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) r0).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) r0).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) r0).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) r0).bottomMargin);
        if (this.mLastInsets != null) goto L5;
    L9:
        Rect r5 = acquireTempRect();
        GravityCompat.apply(resolveGravity(r0.gravity), r8.getMeasuredWidth(), r8.getMeasuredHeight(), r4, r5, r9);
        r8.layout(r5.left, r5.top, r5.right, r5.bottom);
        releaseTempRect(r4);
        releaseTempRect(r5);
        return;
    L5:
        if (ViewCompat.getFitsSystemWindows(this) == false) goto L9;
        if (ViewCompat.getFitsSystemWindows(r8) == true) goto L9;
        r4.left += this.mLastInsets.getSystemWindowInsetLeft();
        r4.top += this.mLastInsets.getSystemWindowInsetTop();
        r4.right -= this.mLastInsets.getSystemWindowInsetRight();
        r4.bottom -= this.mLastInsets.getSystemWindowInsetBottom();
        goto L9
    }

    private void layoutChildWithAnchor(View r4, View r5, int r6) {
        Rect r0 = acquireTempRect();
        Rect r1 = acquireTempRect();
        getDescendantRect(r5, r0);     // Catch: Throwable -> L6
        getDesiredAnchoredChildRect(r4, r6, r0, r1);     // Catch: Throwable -> L6
        r4.layout(r1.left, r1.top, r1.right, r1.bottom);     // Catch: Throwable -> L6
        releaseTempRect(r0);
        releaseTempRect(r1);
        return;
    L6:
        th = move-exception;
        releaseTempRect(r0);
        releaseTempRect(r1);
        throw th;
    }

    private void layoutChildWithKeyline(View r9, int r10, int r11) {
        LayoutParams r0 = (LayoutParams) r9.getLayoutParams();
        int r1 = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(r0.gravity), r11);
        int r2 = r1 & 7;
        int r12 = r1 & 112;
        int r3 = getWidth();
        int r4 = getHeight();
        int r5 = r9.getMeasuredWidth();
        int r6 = r9.getMeasuredHeight();
        if (r11 != 1) goto L5;
        r10 = r3 - r10;
    L5:
        int r102 = getKeyline(r10) - r5;
        if (r2 != 1) goto L8;
        r102 = r102 + (r5 / 2);
    L13:
        if (r12 != 16) goto L15;
        int r112 = r6 / 2;
    L19:
        int r103 = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) r0).leftMargin, Math.min(r102, ((r3 - getPaddingRight()) - r5) - ((ViewGroup.MarginLayoutParams) r0).rightMargin));
        int r8 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) r0).topMargin, Math.min(r112, ((r4 - getPaddingBottom()) - r6) - ((ViewGroup.MarginLayoutParams) r0).bottomMargin));
        r9.layout(r103, r8, r5 + r103, r6 + r8);
        return;
    L15:
        if (r12 == 80) goto L17;
        r112 = 0;
        goto L19
    L17:
        r112 = r6;
        goto L19
    L8:
        if (r2 != 5) goto L13;
        r102 = r102 + r5;
        goto L13
    }

    private void offsetChildByInset(View r9, Rect r10, int r11) {
        if (ViewCompat.isLaidOut(r9) == true) goto L6;
        return;
    L6:
        if (r9.getWidth() > 0) goto L8;
        return;
    L8:
        if (r9.getHeight() <= 0) goto L55;
        LayoutParams r0 = (LayoutParams) r9.getLayoutParams();
        Behavior r1 = r0.getBehavior();
        Rect r2 = acquireTempRect();
        Rect r3 = acquireTempRect();
        r3.set(r9.getLeft(), r9.getTop(), r9.getRight(), r9.getBottom());
        if (r1 != null) goto L13;
    L19:
        r2.set(r3);
    L20:
        releaseTempRect(r3);
        if (r2.isEmpty() == false) goto L24;
        releaseTempRect(r2);
        return;
    L24:
        int r112 = GravityCompat.getAbsoluteGravity(r0.dodgeInsetEdges, r11);
        boolean r4 = true;
        if ((r112 & 48) != 48) goto L29;
        int r12 = (r2.top - ((ViewGroup.MarginLayoutParams) r0).topMargin) - r0.mInsetOffsetY;
        int r32 = r10.top;
        if (r12 >= r32) goto L29;
        setInsetOffsetY(r9, r32 - r12);
        boolean r13 = true;
    L31:
        if ((r112 & 80) != 80) goto L35;
        int r33 = ((getHeight() - r2.bottom) - ((ViewGroup.MarginLayoutParams) r0).bottomMargin) + r0.mInsetOffsetY;
        int r6 = r10.bottom;
        if (r33 >= r6) goto L35;
        setInsetOffsetY(r9, r33 - r6);
        r13 = true;
    L35:
        if (r13 == true) goto L38;
        setInsetOffsetY(r9, 0);
    L38:
        if ((r112 & 3) != 3) goto L42;
        int r14 = (r2.left - ((ViewGroup.MarginLayoutParams) r0).leftMargin) - r0.mInsetOffsetX;
        int r34 = r10.left;
        if (r14 >= r34) goto L42;
        setInsetOffsetX(r9, r34 - r14);
        boolean r15 = true;
    L44:
        if ((r112 & 5) != 5) goto L48;
        int r113 = ((getWidth() - r2.right) - ((ViewGroup.MarginLayoutParams) r0).rightMargin) + r0.mInsetOffsetX;
        int r102 = r10.right;
        if (r113 >= r102) goto L48;
        setInsetOffsetX(r9, r113 - r102);
    L49:
        if (r4 == true) goto L51;
        setInsetOffsetX(r9, 0);
    L51:
        releaseTempRect(r2);
        return;
    L48:
        r4 = r15;
    L42:
        r15 = false;
    L29:
        r13 = false;
        goto L31
    L13:
        if (r1.getInsetDodgeRect(this, r9, r2) == false) goto L19;
        if (r3.contains(r2) == true) goto L20;
        Union$$ExternalSyntheticBUOutline0.m("Rect should be within the child's bounds. Rect:", r2.toShortString(), " | Bounds:", r3.toShortString());
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Behavior parseBehavior(Context r4, AttributeSet r5, String r6) {
        if (TextUtils.isEmpty(r6) == false) goto L6;
        return null;
    L6:
        if (r6.startsWith(".") == false) goto L9;
        r6 = r4.getPackageName() + r6;
    L26:
        ThreadLocal<Map<String, Constructor<Behavior>>> r0 = sConstructors;     // Catch: Exception -> L17
        Map<String, Constructor<Behavior>> r2 = r0.get();     // Catch: Exception -> L17
        if (r2 != null) goto L19;
        r2 = new HashMap();     // Catch: Exception -> L17
        r0.set(r2);     // Catch: Exception -> L17
    L19:
        Constructor<Behavior> r02 = r2.get(r6);     // Catch: Exception -> L17
        if (r02 != null) goto L22;
        r02 = Class.forName(r6, false, r4.getClassLoader()).getConstructor(CONSTRUCTOR_PARAMS);     // Catch: Exception -> L17
        r02.setAccessible(true);     // Catch: Exception -> L17
        r2.put(r6, r02);     // Catch: Exception -> L17
    L22:
        return r02.newInstance(new Object[]{r4, r5});
    L17:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Could not inflate Behavior subclass ".concat(r6), e);
        return null;
    L9:
        if (r6.indexOf(46) >= 0) goto L26;
        String r22 = WIDGET_PACKAGE_NAME;
        if (TextUtils.isEmpty(r22) == true) goto L26;
        r6 = r22 + '.' + r6;
        goto L26
    }

    private boolean performIntercept(MotionEvent r24, int r25) {
        int r3 = r24.getActionMasked();
        List<View> r4 = this.mTempList1;
        getTopSortedChildren(r4);
        int r5 = r4.size();
        MotionEvent r7 = null;
        int r8 = 0;
        boolean r9 = false;
        boolean r10 = false;
    L3:
        if (r8 >= r5) goto L34;
        View r11 = r4.get(r8);
        LayoutParams r12 = (LayoutParams) r11.getLayoutParams();
        Behavior r13 = r12.getBehavior();
        if (r9 == true) goto L7;
        if (r10 == true) goto L7;
    L16:
        if (r9 == true) goto L25;
        if (r13 == null) goto L25;
        if (r25 == 0) goto L22;
        if (r25 != 1) goto L23;
        r9 = r13.onTouchEvent(this, r11, r24);
    L23:
        if (r9 == false) goto L25;
        this.mBehaviorTouchView = r11;
        goto L25
    L22:
        r9 = r13.onInterceptTouchEvent(this, r11, r24);
    L25:
        boolean r102 = r12.didBlockInteraction();
        boolean r112 = r12.isBlockingInteractionBelow(this, r11);
        if (r112 == false) goto L29;
        if (r102 == true) goto L29;
        r10 = true;
    L30:
        if (r112 == false) goto L33;
        if (r10 == false) goto L34;
    L33:
        r8 = r8 + 1;
    L29:
        r10 = false;
    L7:
        if (r3 == 0) goto L16;
        if (r13 == null) goto L33;
        if (r7 != null) goto L11;
        long r15 = SystemClock.uptimeMillis();
        r7 = MotionEvent.obtain(r15, r15, 3, 0.0f, 0.0f, 0);
    L11:
        if (r25 == 0) goto L15;
        if (r25 != 1) goto L33;
        r13.onTouchEvent(this, r11, r7);
        goto L33
    L15:
        r13.onInterceptTouchEvent(this, r11, r7);
    L34:
        r4.clear();
        return r9;
    }

    private void prepareChildren() {
        this.mDependencySortedChildren.clear();
        this.mChildDag.clear();
        int r0 = getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L16;
        View r3 = getChildAt(r2);
        LayoutParams r4 = getResolvedLayoutParams(r3);
        r4.findAnchorView(this, r3);
        this.mChildDag.addNode(r3);
        int r5 = 0;
    L5:
        if (r5 >= r0) goto L15;
        if (r5 == r2) goto L14;
        View r6 = getChildAt(r5);
        if (r4.dependsOn(this, r3, r6) == false) goto L14;
        if (this.mChildDag.contains(r6) == true) goto L13;
        this.mChildDag.addNode(r6);
    L13:
        this.mChildDag.addEdge(r6, r3);
    L14:
        r5 = r5 + 1;
        goto L5
    L15:
        r2 = r2 + 1;
        goto L3
    L16:
        this.mDependencySortedChildren.addAll(this.mChildDag.getSortedList());
        Collections.reverse(this.mDependencySortedChildren);
    }

    private static void releaseTempRect(Rect r1) {
        r1.setEmpty();
        sRectPool.release(r1);
    }

    private void resetTouchBehaviors(boolean r14) {
        int r0 = getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L12;
        View r3 = getChildAt(r2);
        Behavior r4 = ((LayoutParams) r3.getLayoutParams()).getBehavior();
        if (r4 == null) goto L11;
        long r5 = SystemClock.uptimeMillis();
        MotionEvent r52 = MotionEvent.obtain(r5, r5, 3, 0.0f, 0.0f, 0);
        if (r14 == false) goto L9;
        r4.onInterceptTouchEvent(this, r3, r52);
    L10:
        r52.recycle();
        goto L11
    L9:
        r4.onTouchEvent(this, r3, r52);
    L11:
        r2 = r2 + 1;
        goto L3
    L12:
        int r142 = 0;
    L13:
        if (r142 >= r0) goto L15;
        ((LayoutParams) getChildAt(r142).getLayoutParams()).resetTouchBehaviorTracking();
        r142 = r142 + 1;
        goto L13
    L15:
        this.mBehaviorTouchView = null;
        this.mDisallowInterceptReset = false;
    }

    private static int resolveAnchoredChildGravity(int r0) {
        if (r0 != 0) goto L5;
        return 17;
    L5:
        return r0;
    }

    private static int resolveGravity(int r1) {
        if ((r1 & 7) != 0) goto L6;
        r1 = r1 | PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES;
    L6:
        if ((r1 & 112) == 0) goto L8;
        return r1;
    L8:
        return r1 | 48;
    }

    private static int resolveKeylineGravity(int r0) {
        if (r0 != 0) goto L5;
        return 8388661;
    L5:
        return r0;
    }

    private void setInsetOffsetX(View r2, int r3) {
        LayoutParams r1 = (LayoutParams) r2.getLayoutParams();
        int r0 = r1.mInsetOffsetX;
        if (r0 == r3) goto L6;
        ViewCompat.offsetLeftAndRight(r2, r3 - r0);
        r1.mInsetOffsetX = r3;
        return;
    }

    private void setInsetOffsetY(View r2, int r3) {
        LayoutParams r1 = (LayoutParams) r2.getLayoutParams();
        int r0 = r1.mInsetOffsetY;
        if (r0 == r3) goto L6;
        ViewCompat.offsetTopAndBottom(r2, r3 - r0);
        r1.mInsetOffsetY = r3;
        return;
    }

    private void setupForInsets() {
        if (ViewCompat.getFitsSystemWindows(this) == true) goto L5;
        ViewCompat.setOnApplyWindowInsetsListener(this, null);
        return;
    L5:
        if (this.mApplyWindowInsetsListener != null) goto L7;
        this.mApplyWindowInsetsListener = new AnonymousClass1(this);
    L7:
        ViewCompat.setOnApplyWindowInsetsListener(this, this.mApplyWindowInsetsListener);
        setSystemUiVisibility(1280);
    }

    public void addPreDrawListener() {
        if (this.mIsAttachedToWindow == true) goto L5;
    L8:
        this.mNeedsPreDrawListener = true;
        return;
    L5:
        if (this.mOnPreDrawListener != null) goto L7;
        this.mOnPreDrawListener = new OnPreDrawListener(this);
    L7:
        getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        goto L8
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams r2) {
        if ((r2 instanceof LayoutParams) == true) goto L5;
        return false;
    L5:
        if (super.checkLayoutParams(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void dispatchDependentViewsChanged(View r5) {
        List r0 = this.mChildDag.getIncomingEdges(r5);
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isEmpty() == true) goto L17;
        int r1 = 0;
    L8:
        if (r1 >= r0.size()) goto L18;
        View r2 = (View) r0.get(r1);
        Behavior r3 = ((LayoutParams) r2.getLayoutParams()).getBehavior();
        if (r3 == null) goto L12;
        r3.onDependentViewChanged(this, r2, r5);
    L12:
        r1 = r1 + 1;
        goto L8
    L18:
        return;
    }

    public boolean doViewsOverlap(View r5, View r6) {
        boolean r1 = false;
        if (r5.getVisibility() == 0) goto L5;
    L30:
        return false;
    L5:
        if (r6.getVisibility() != 0) goto L30;
        Rect r0 = acquireTempRect();
        if (r5.getParent() == this) goto L9;
        boolean r2 = true;
    L10:
        getChildRect(r5, r2, r0);
        Rect r52 = acquireTempRect();
        if (r6.getParent() == this) goto L13;
        boolean r22 = true;
    L14:
        getChildRect(r6, r22, r52);
    L24:
        th = move-exception;
        releaseTempRect(r0);
        releaseTempRect(r52);
        throw th;
    L16:
        if (r0.left <= r52.right) goto L18;
    L26:
        releaseTempRect(r0);
        releaseTempRect(r52);
        return r1;
    L18:
        if (r0.top > r52.bottom) goto L26;
        if (r0.right < r52.left) goto L26;
        if (r0.bottom < r52.top) goto L26;
        r1 = true;
        goto L26
    L13:
        r22 = false;
        goto L14
    L9:
        r2 = false;
        goto L10
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r17, View r18, long r19) {
        LayoutParams r2 = (LayoutParams) r18.getLayoutParams();
        Behavior r3 = r2.mBehavior;
        if (r3 == null) goto L15;
        float r32 = r3.getScrimOpacity(this, r18);
        if (r32 <= 0.0f) goto L15;
        if (this.mScrimPaint != null) goto L9;
        this.mScrimPaint = new Paint();
    L9:
        this.mScrimPaint.setColor(r2.mBehavior.getScrimColor(this, r18));
        this.mScrimPaint.setAlpha(clamp(Math.round(r32 * 255.0f), 0, 255));
        int r22 = r17.save();
        if (r18.isOpaque() == false) goto L12;
        r17.clipRect(r18.getLeft(), r18.getTop(), r18.getRight(), r18.getBottom(), Region.Op.DIFFERENCE);
    L12:
        r17.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), this.mScrimPaint);
        r17.restoreToCount(r22);
    L15:
        return super.drawChild(r17, r18, r19);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] r0 = getDrawableState();
        Drawable r1 = this.mStatusBarBackground;
        if (r1 != null) goto L5;
    L7:
        boolean r02 = false;
    L8:
        if (r02 == false) goto L11;
        invalidate();
        return;
    L11:
        return;
    L5:
        if (r1.isStateful() == false) goto L7;
        r02 = r1.setState(r0);
        goto L8
    }

    public void ensurePreDrawListener() {
        int r0 = getChildCount();
        boolean r1 = false;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (hasDependencies(getChildAt(r2)) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        r1 = true;
    L9:
        if (r1 == this.mNeedsPreDrawListener) goto L17;
        if (r1 == false) goto L13;
        addPreDrawListener();
        return;
    L13:
        removePreDrawListener();
        return;
    }

    @Override // android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return generateLayoutParams(r1);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet r2) {
        return new LayoutParams(getContext(), r2);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        if ((r1 instanceof LayoutParams) == false) goto L7;
        return new LayoutParams((LayoutParams) r1);
    L7:
        if ((r1 instanceof ViewGroup.MarginLayoutParams) == false) goto L11;
        return new LayoutParams((ViewGroup.MarginLayoutParams) r1);
    L11:
        return new LayoutParams(r1);
    }

    public void getChildRect(View r3, boolean r4, Rect r5) {
        if (r3.isLayoutRequested() == false) goto L5;
    L12:
        r5.setEmpty();
        return;
    L5:
        if (r3.getVisibility() == 8) goto L12;
        if (r4 == false) goto L10;
        getDescendantRect(r3, r5);
        return;
    L10:
        r5.set(r3.getLeft(), r3.getTop(), r3.getRight(), r3.getBottom());
    }

    public List<View> getDependencies(View r2) {
        List<View> r22 = this.mChildDag.getOutgoingEdges(r2);
        this.mTempDependenciesList.clear();
        if (r22 == null) goto L6;
        this.mTempDependenciesList.addAll(r22);
    L6:
        return this.mTempDependenciesList;
    }

    public final List<View> getDependencySortedChildren() {
        prepareChildren();
        return Collections.unmodifiableList(this.mDependencySortedChildren);
    }

    public List<View> getDependents(View r2) {
        List r22 = this.mChildDag.getIncomingEdges(r2);
        this.mTempDependenciesList.clear();
        if (r22 == null) goto L6;
        this.mTempDependenciesList.addAll(r22);
    L6:
        return this.mTempDependenciesList;
    }

    public void getDescendantRect(View r1, Rect r2) {
        ViewGroupUtils.getDescendantRect(this, r1, r2);
    }

    public void getDesiredAnchoredChildRect(View r10, int r11, Rect r12, Rect r13) {
        LayoutParams r6 = (LayoutParams) r10.getLayoutParams();
        int r7 = r10.getMeasuredWidth();
        int r8 = r10.getMeasuredHeight();
        getDesiredAnchoredChildRectWithoutConstraints(r10, r11, r12, r13, r6, r7, r8);
        constrainChildRect(r6, r13, r7, r8);
    }

    public void getLastChildRect(View r1, Rect r2) {
        r2.set(((LayoutParams) r1.getLayoutParams()).getLastChildRect());
    }

    public final WindowInsetsCompat getLastWindowInsets() {
        return this.mLastInsets;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LayoutParams getResolvedLayoutParams(View r6) {
        LayoutParams r5 = (LayoutParams) r6.getLayoutParams();
        if (r5.mBehaviorResolved == false) goto L5;
    L22:
        return r5;
    L5:
        if ((r6 instanceof AttachedBehavior) == false) goto L11;
        Behavior r62 = ((AttachedBehavior) r6).getBehavior();
        if (r62 != null) goto L9;
        Log.e(TAG, "Attached behavior class is null");
    L9:
        r5.setBehavior(r62);
        r5.mBehaviorResolved = true;
        return r5;
    L11:
        Class<?> r63 = r6.getClass();
        DefaultBehavior r3 = null;
    L12:
        if (r63 == null) goto L16;
        r3 = (DefaultBehavior) r63.getAnnotation(DefaultBehavior.class);
        if (r3 != null) goto L16;
        r63 = r63.getSuperclass();
    L16:
        if (r3 != null) goto L23;
    L21:
        r5.mBehaviorResolved = true;
        goto L22
    L23:
        r5.setBehavior(r3.value().getDeclaredConstructor(null).newInstance(null));     // Catch: Exception -> L19
    L19:
        e = move-exception;
        Log.e(TAG, "Default behavior class " + r3.value().getName() + " could not be instantiated. Did you forget a default constructor?", e);
        goto L21
    }

    public Drawable getStatusBarBackground() {
        return this.mStatusBarBackground;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    public boolean isPointInChildBounds(View r2, int r3, int r4) {
        Rect r0 = acquireTempRect();
        getDescendantRect(r2, r0);
        boolean r1 = r0.contains(r3, r4);     // Catch: Throwable -> L6
        releaseTempRect(r0);
        return r1;
    L6:
        th = move-exception;
        releaseTempRect(r0);
        throw th;
    }

    public void offsetChildToAnchor(View r11, int r12) {
        LayoutParams r6 = (LayoutParams) r11.getLayoutParams();
        if (r6.mAnchorView == null) goto L20;
        Rect r4 = acquireTempRect();
        Rect r0 = acquireTempRect();
        Rect r5 = acquireTempRect();
        getDescendantRect(r6.mAnchorView, r4);
        boolean r9 = false;
        getChildRect(r11, false, r0);
        int r7 = r11.getMeasuredWidth();
        int r8 = r11.getMeasuredHeight();
        getDesiredAnchoredChildRectWithoutConstraints(r11, r12, r4, r5, r6, r7, r8);
        if (r5.left == r0.left) goto L7;
    L8:
        r9 = true;
    L9:
        constrainChildRect(r6, r5, r7, r8);
        int r10 = r5.left - r0.left;
        int r112 = r5.top - r0.top;
        if (r10 == 0) goto L12;
        ViewCompat.offsetLeftAndRight(r11, r10);
    L12:
        if (r112 == 0) goto L14;
        ViewCompat.offsetTopAndBottom(r11, r112);
    L14:
        if (r9 == false) goto L18;
        Behavior r102 = r6.getBehavior();
        if (r102 == null) goto L18;
        r102.onDependentViewChanged(this, r11, r6.mAnchorView);
    L18:
        releaseTempRect(r4);
        releaseTempRect(r0);
        releaseTempRect(r5);
        return;
    L7:
        if (r5.top == r0.top) goto L9;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        resetTouchBehaviors(false);
        if (this.mNeedsPreDrawListener == false) goto L9;
        if (this.mOnPreDrawListener != null) goto L7;
        this.mOnPreDrawListener = new OnPreDrawListener(this);
    L7:
        getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
    L9:
        if (this.mLastInsets == null) goto L11;
    L13:
        this.mIsAttachedToWindow = true;
        return;
    L11:
        if (ViewCompat.getFitsSystemWindows(this) == false) goto L13;
        ViewCompat.requestApplyInsets(this);
        goto L13
    }

    public final void onChildViewsChanged(int r18) {
        int r2 = ViewCompat.getLayoutDirection(this);
        int r3 = this.mDependencySortedChildren.size();
        Rect r4 = acquireTempRect();
        Rect r5 = acquireTempRect();
        Rect r6 = acquireTempRect();
        int r8 = 0;
    L3:
        if (r8 >= r3) goto L61;
        View r9 = this.mDependencySortedChildren.get(r8);
        LayoutParams r10 = (LayoutParams) r9.getLayoutParams();
        if (r18 == 0) goto L7;
    L9:
        int r11 = 0;
    L10:
        if (r11 >= r8) goto L15;
        if (r10.mAnchorDirectChild != this.mDependencySortedChildren.get(r11)) goto L14;
        offsetChildToAnchor(r9, r2);
    L14:
        r11 = r11 + 1;
        goto L10
    L15:
        getChildRect(r9, true, r5);
        if (r10.insetEdge == 0) goto L34;
        if (r5.isEmpty() == true) goto L34;
        int r12 = GravityCompat.getAbsoluteGravity(r10.insetEdge, r2);
        int r13 = r12 & 112;
        if (r13 != 48) goto L22;
        r4.top = Math.max(r4.top, r5.bottom);
    L26:
        int r122 = r12 & 7;
        if (r122 != 3) goto L29;
        r4.left = Math.max(r4.left, r5.right);
        goto L34
    L29:
        if (r122 != 5) goto L34;
        r4.right = Math.max(r4.right, getWidth() - r5.left);
        goto L34
    L22:
        if (r13 != 80) goto L26;
        r4.bottom = Math.max(r4.bottom, getHeight() - r5.top);
    L34:
        if (r10.dodgeInsetEdges == 0) goto L39;
        if (r9.getVisibility() != 0) goto L39;
        offsetChildByInset(r9, r4, r2);
    L39:
        if (r18 == 2) goto L44;
        getLastChildRect(r9, r6);
        if (r6.equals(r5) == true) goto L60;
        recordLastChildRect(r9, r5);
    L60:
        r8 = r8 + 1;
    L44:
        int r123 = r8 + 1;
    L45:
        if (r123 >= r3) goto L60;
        View r132 = this.mDependencySortedChildren.get(r123);
        LayoutParams r14 = (LayoutParams) r132.getLayoutParams();
        Behavior r15 = r14.getBehavior();
        if (r15 == null) goto L59;
        if (r15.layoutDependsOn(this, r132, r9) == false) goto L59;
        if (r18 == 0) goto L52;
    L54:
        if (r18 == 2) goto L56;
        boolean r133 = r15.onDependentViewChanged(this, r132, r9);
    L57:
        if (r18 != 1) goto L59;
        r14.setChangedAfterNestedScroll(r133);
        goto L59
    L56:
        r15.onDependentViewRemoved(this, r132, r9);
        r133 = true;
        goto L57
    L52:
        if (r14.getChangedAfterNestedScroll() == false) goto L54;
        r14.resetChangedAfterNestedScroll();
    L59:
        r123 = r123 + 1;
        goto L45
    L7:
        if (r9.getVisibility() != 8) goto L9;
    L61:
        releaseTempRect(r4);
        releaseTempRect(r5);
        releaseTempRect(r6);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        resetTouchBehaviors(false);
        if (this.mNeedsPreDrawListener == true) goto L5;
    L7:
        View r1 = this.mNestedScrollingTarget;
        if (r1 == null) goto L10;
        onStopNestedScroll(r1);
    L10:
        this.mIsAttachedToWindow = false;
        return;
    L5:
        if (this.mOnPreDrawListener == null) goto L7;
        getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        goto L7
    }

    @Override // android.view.View
    public void onDraw(Canvas r5) {
        super.onDraw(r5);
        if (this.mDrawStatusBarBackground == true) goto L5;
        return;
    L5:
        if (this.mStatusBarBackground == null) goto L14;
        WindowInsetsCompat r0 = this.mLastInsets;
        if (r0 == null) goto L9;
        int r02 = r0.getSystemWindowInsetTop();
    L10:
        if (r02 <= 0) goto L15;
        this.mStatusBarBackground.setBounds(0, 0, getWidth(), r02);
        this.mStatusBarBackground.draw(r5);
        return;
    L15:
        return;
    L9:
        r02 = 0;
        goto L10
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r4) {
        int r0 = r4.getActionMasked();
        if (r0 != 0) goto L5;
        resetTouchBehaviors(true);
    L5:
        boolean r42 = performIntercept(r4, 0);
        if (r0 != 1) goto L8;
    L11:
        resetTouchBehaviors(true);
        return r42;
    L8:
        if (r0 == 3) goto L11;
        return r42;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        int r22 = ViewCompat.getLayoutDirection(this);
        int r32 = this.mDependencySortedChildren.size();
        int r42 = 0;
    L3:
        if (r42 >= r32) goto L13;
        View r52 = this.mDependencySortedChildren.get(r42);
        if (r52.getVisibility() == 8) goto L12;
        Behavior r62 = ((LayoutParams) r52.getLayoutParams()).getBehavior();
        if (r62 != null) goto L10;
    L11:
        onLayoutChild(r52, r22);
        goto L12
    L10:
        if (r62.onLayoutChild(this, r52, r22) == false) goto L11;
    L12:
        r42 = r42 + 1;
        goto L3
    }

    public void onLayoutChild(View r3, int r4) {
        LayoutParams r0 = (LayoutParams) r3.getLayoutParams();
        if (r0.checkAnchorChanged() == true) goto L14;
        View r1 = r0.mAnchorView;
        if (r1 == null) goto L8;
        layoutChildWithAnchor(r3, r1, r4);
        return;
    L8:
        int r02 = r0.keyline;
        if (r02 < 0) goto L12;
        layoutChildWithKeyline(r3, r02, r4);
        return;
    L12:
        layoutChild(r3, r4);
        return;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
    }

    @Override // android.view.View
    public void onMeasure(int r27, int r28) {
        CoordinatorLayout r0 = this;
        r0.prepareChildren();
        r0.ensurePreDrawListener();
        int r7 = r0.getPaddingLeft();
        int r1 = r0.getPaddingTop();
        int r8 = r0.getPaddingRight();
        int r2 = r0.getPaddingBottom();
        int r9 = ViewCompat.getLayoutDirection(r0);
        if (r9 != 1) goto L5;
        boolean r11 = true;
    L6:
        int r12 = View.MeasureSpec.getMode(r27);
        int r13 = View.MeasureSpec.getSize(r27);
        int r14 = View.MeasureSpec.getMode(r28);
        int r15 = View.MeasureSpec.getSize(r28);
        int r16 = r7 + r8;
        int r17 = r1 + r2;
        int r18 = r0.getSuggestedMinimumWidth();
        int r22 = r0.getSuggestedMinimumHeight();
        if (r0.mLastInsets != null) goto L9;
    L11:
        boolean r182 = false;
    L12:
        int r3 = r0.mDependencySortedChildren.size();
        int r4 = 0;
        int r5 = 0;
    L13:
        if (r4 >= r3) goto L52;
        View r6 = r0.mDependencySortedChildren.get(r4);
        int r20 = r18;
        if (r6.getVisibility() != 8) goto L17;
        int r23 = r3;
        int r21 = r4;
        int r19 = r7;
        int r222 = r9;
        r18 = r20;
        int r202 = r8;
    L51:
        r4 = r21 + 1;
        r7 = r19;
        r8 = r202;
        r9 = r222;
        r3 = r23;
        goto L13
    L17:
        LayoutParams r10 = (LayoutParams) r6.getLayoutParams();
        int r110 = r10.keyline;
        if (r110 < 0) goto L35;
        if (r12 == 0) goto L35;
        int r111 = r0.getKeyline(r110);
        int r112 = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(r10.gravity), r9) & 7;
        int r223 = r22;
        if (r112 != 3) goto L24;
        if (r11 == true) goto L24;
    L26:
        int r113 = Math.max(0, (r13 - r8) - r111);
    L27:
        int r25 = r4;
        int r42 = r113;
        int r114 = r25;
    L37:
        if (r182 == true) goto L39;
    L41:
        r21 = r114;
        int r24 = r27;
        int r115 = r28;
    L42:
        Behavior r02 = r10.getBehavior();
        if (r02 == null) goto L48;
        r23 = r3;
        int r32 = r24;
        View r26 = r6;
        int r192 = r223;
        r222 = r9;
        int r92 = r192;
        r19 = r7;
        int r72 = r20;
        r202 = r8;
        int r82 = r5;
        int r52 = r115;
        boolean r03 = r02.onMeasureChild(this, r26, r32, r42, r52, 0);
        int r116 = r32;
        int r33 = r42;
        int r43 = r52;
        if (r03 == false) goto L49;
        r0 = this;
    L50:
        r18 = Math.max(r72, ((r16 + r26.getMeasuredWidth()) + ((ViewGroup.MarginLayoutParams) r10).leftMargin) + ((ViewGroup.MarginLayoutParams) r10).rightMargin);
        int r34 = Math.max(r92, ((r17 + r26.getMeasuredHeight()) + ((ViewGroup.MarginLayoutParams) r10).topMargin) + ((ViewGroup.MarginLayoutParams) r10).bottomMargin);
        r5 = View.combineMeasuredStates(r82, r26.getMeasuredState());
        r22 = r34;
    L49:
        View r04 = r26;
        r0 = this;
        r0.onMeasureChild(r04, r116, r33, r43, 0);
        r26 = r04;
        goto L50
    L48:
        int r193 = r223;
        r222 = r9;
        r92 = r193;
        r23 = r3;
        r33 = r42;
        r19 = r7;
        r72 = r20;
        r43 = r115;
        r116 = r24;
        r26 = r6;
        r202 = r8;
        r82 = r5;
        goto L49
    L39:
        if (ViewCompat.getFitsSystemWindows(r6) == true) goto L41;
        r21 = r114;
        int r29 = r0.mLastInsets.getSystemWindowInsetLeft() + r0.mLastInsets.getSystemWindowInsetRight();
        int r117 = r0.mLastInsets.getSystemWindowInsetTop() + r0.mLastInsets.getSystemWindowInsetBottom();
        r24 = View.MeasureSpec.makeMeasureSpec(r13 - r29, r12);
        r115 = View.MeasureSpec.makeMeasureSpec(r15 - r117, r14);
    L24:
        if (r112 != 5) goto L28;
        if (r11 == true) goto L26;
    L28:
        if (r112 != 5) goto L31;
        if (r11 == true) goto L31;
    L33:
        r113 = Math.max(0, r111 - r7);
    L31:
        if (r112 != 3) goto L36;
        if (r11 == true) goto L33;
    L36:
        r114 = r4;
        r42 = 0;
    L35:
        r223 = r22;
        goto L36
    L52:
        int r83 = r5;
        r0.setMeasuredDimension(View.resolveSizeAndState(r18, r27, (-16777216) & r83), View.resolveSizeAndState(r22, r28, r83 << 16));
        return;
    L9:
        if (ViewCompat.getFitsSystemWindows(r0) == false) goto L11;
        r182 = true;
        goto L12
    L5:
        r11 = false;
        goto L6
    }

    public void onMeasureChild(View r1, int r2, int r3, int r4, int r5) {
        measureChildWithMargins(r1, r2, r3, r4, r5);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View r12, float r13, float r14, boolean r15) {
        int r0 = getChildCount();
        int r2 = 0;
        boolean r3 = false;
    L3:
        if (r2 >= r0) goto L14;
        View r6 = this.getChildAt(r2);
        if (r6.getVisibility() != 8) goto L7;
    L6:
        CoordinatorLayout r5 = this;
        View r7 = r12;
        float r8 = r13;
        float r9 = r14;
        boolean r10 = r15;
    L13:
        r2 = r2 + 1;
        CoordinatorLayout this = r5;
        r12 = r7;
        r13 = r8;
        r14 = r9;
        r15 = r10;
        goto L3
    L7:
        LayoutParams r4 = (LayoutParams) r6.getLayoutParams();
        if (r4.isNestedScrollAccepted(0) == false) goto L6;
        Behavior r42 = r4.getBehavior();
        if (r42 == null) goto L6;
        r5 = this;
        r7 = r12;
        r8 = r13;
        r9 = r14;
        r10 = r15;
        r3 = r3 | r42.onNestedFling(r5, r6, r7, r8, r9, r10);
        goto L13
    L14:
        CoordinatorLayout r52 = this;
        if (r3 == false) goto L17;
        r52.onChildViewsChanged(1);
    L17:
        return r3;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View r11, float r12, float r13) {
        int r0 = getChildCount();
        int r2 = 0;
        boolean r3 = false;
    L3:
        if (r2 >= r0) goto L14;
        View r6 = this.getChildAt(r2);
        if (r6.getVisibility() != 8) goto L7;
    L6:
        CoordinatorLayout r5 = this;
        View r7 = r11;
        float r8 = r12;
        float r9 = r13;
    L13:
        r2 = r2 + 1;
        CoordinatorLayout this = r5;
        r11 = r7;
        r12 = r8;
        r13 = r9;
        goto L3
    L7:
        LayoutParams r4 = (LayoutParams) r6.getLayoutParams();
        if (r4.isNestedScrollAccepted(0) == false) goto L6;
        Behavior r42 = r4.getBehavior();
        if (r42 == null) goto L6;
        r5 = this;
        r7 = r11;
        r8 = r12;
        r9 = r13;
        r3 = r3 | r42.onNestedPreFling(r5, r6, r7, r8, r9);
        goto L13
    L14:
        return r3;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View r7, int r8, int r9, int[] r10) {
        onNestedPreScroll(r7, r8, r9, r10, 0);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View r15, int r16, int r17, int[] r18, int r19) {
        int r8 = getChildCount();
        boolean r0 = false;
        int r10 = 0;
        int r11 = 0;
        int r12 = 0;
    L4:
        if (r10 >= r8) goto L25;
        View r2 = getChildAt(r10);
        if (r2.getVisibility() == 8) goto L24;
        LayoutParams r3 = (LayoutParams) r2.getLayoutParams();
        if (r3.isNestedScrollAccepted(r19) == false) goto L24;
        Behavior r32 = r3.getBehavior();
        if (r32 == null) goto L24;
        int[] r6 = this.mBehaviorConsumed;
        r6[0] = 0;
        r6[1] = 0;
        r32.onNestedPreScroll(this, r2, r15, r16, r17, r6, r19);
        int[] r02 = this.mBehaviorConsumed;
        if (r16 <= 0) goto L17;
        int r03 = Math.max(r11, r02[0]);
    L16:
        r11 = r03;
        int[] r04 = this.mBehaviorConsumed;
        if (r17 <= 0) goto L22;
        int r05 = Math.max(r12, r04[1]);
    L21:
        r12 = r05;
        r0 = true;
        goto L24
    L22:
        r05 = Math.min(r12, r04[1]);
        goto L21
    L17:
        r03 = Math.min(r11, r02[0]);
    L24:
        r10 = r10 + 1;
        goto L4
    L25:
        r18[0] = r11;
        r18[1] = r12;
        if (r0 == false) goto L34;
        onChildViewsChanged(1);
        return;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View r8, int r9, int r10, int r11, int r12) {
        onNestedScroll(r8, r9, r10, r11, r12, 0);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View r9, int r10, int r11, int r12, int r13, int r14) {
        onNestedScroll(r9, r10, r11, r12, r13, 0, this.mNestedScrollingV2ConsumedCompat);
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(View r17, int r18, int r19, int r20, int r21, int r22, int[] r23) {
        int r10 = getChildCount();
        boolean r0 = false;
        int r12 = 0;
        int r13 = 0;
        int r14 = 0;
    L4:
        if (r12 >= r10) goto L25;
        View r2 = getChildAt(r12);
        if (r2.getVisibility() == 8) goto L24;
        LayoutParams r3 = (LayoutParams) r2.getLayoutParams();
        if (r3.isNestedScrollAccepted(r22) == false) goto L24;
        Behavior r32 = r3.getBehavior();
        if (r32 == null) goto L24;
        int[] r9 = this.mBehaviorConsumed;
        r9[0] = 0;
        r9[1] = 0;
        r32.onNestedScroll(this, r2, r17, r18, r19, r20, r21, r22, r9);
        int[] r02 = this.mBehaviorConsumed;
        if (r20 <= 0) goto L17;
        int r03 = Math.max(r13, r02[0]);
    L16:
        r13 = r03;
        int[] r04 = this.mBehaviorConsumed;
        if (r21 <= 0) goto L22;
        int r05 = Math.max(r14, r04[1]);
    L21:
        r14 = r05;
        r0 = true;
        goto L24
    L22:
        r05 = Math.min(r14, r04[1]);
        goto L21
    L17:
        r03 = Math.min(r13, r02[0]);
    L24:
        r12 = r12 + 1;
        goto L4
    L25:
        r23[0] = r23[0] + r13;
        r23[1] = r23[1] + r14;
        if (r0 == false) goto L34;
        onChildViewsChanged(1);
        return;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View r2, View r3, int r4) {
        onNestedScrollAccepted(r2, r3, r4, 0);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View r10, View r11, int r12, int r13) {
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(r10, r11, r12, r13);
        this.mNestedScrollingTarget = r11;
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L11;
        View r4 = this.getChildAt(r1);
        LayoutParams r2 = (LayoutParams) r4.getLayoutParams();
        if (r2.isNestedScrollAccepted(r13) == true) goto L7;
    L6:
        CoordinatorLayout r3 = this;
        View r5 = r10;
        View r6 = r11;
        int r7 = r12;
        int r8 = r13;
    L10:
        r1 = r1 + 1;
        CoordinatorLayout this = r3;
        r10 = r5;
        r11 = r6;
        r12 = r7;
        r13 = r8;
        goto L3
    L7:
        Behavior r22 = r2.getBehavior();
        if (r22 == null) goto L6;
        r3 = this;
        r5 = r10;
        r6 = r11;
        r7 = r12;
        r8 = r13;
        r22.onNestedScrollAccepted(r3, r4, r5, r6, r7, r8);
        goto L10
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable r7) {
        if ((r7 instanceof SavedState) == true) goto L6;
        super.onRestoreInstanceState(r7);
        return;
    L6:
        SavedState r72 = (SavedState) r7;
        super.onRestoreInstanceState(r72.getSuperState());
        SparseArray<Parcelable> r73 = r72.behaviorStates;
        int r0 = getChildCount();
        int r1 = 0;
    L7:
        if (r1 >= r0) goto L15;
        View r2 = getChildAt(r1);
        int r3 = r2.getId();
        Behavior r4 = getResolvedLayoutParams(r2).getBehavior();
        if (r3 == (-1)) goto L14;
        if (r4 == null) goto L14;
        Parcelable r32 = r73.get(r3);
        if (r32 == null) goto L14;
        r4.onRestoreInstanceState(this, r2, r32);
    L14:
        r1 = r1 + 1;
        goto L7
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState r0 = new SavedState(super.onSaveInstanceState());
        SparseArray<Parcelable> r1 = new SparseArray();
        int r2 = getChildCount();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L11;
        View r4 = getChildAt(r3);
        int r5 = r4.getId();
        Behavior r6 = ((LayoutParams) r4.getLayoutParams()).getBehavior();
        if (r5 == (-1)) goto L10;
        if (r6 == null) goto L10;
        Parcelable r42 = r6.onSaveInstanceState(this, r4);
        if (r42 == null) goto L10;
        r1.append(r5, r42);
    L10:
        r3 = r3 + 1;
        goto L3
    L11:
        r0.behaviorStates = r1;
        return r0;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View r2, View r3, int r4) {
        return onStartNestedScroll(r2, r3, r4, 0);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View r13, View r14, int r15, int r16) {
        int r7 = getChildCount();
        int r9 = 0;
        boolean r10 = false;
    L3:
        if (r9 >= r7) goto L12;
        View r2 = getChildAt(r9);
        if (r2.getVisibility() == 8) goto L11;
        LayoutParams r11 = (LayoutParams) r2.getLayoutParams();
        Behavior r0 = r11.getBehavior();
        if (r0 == null) goto L10;
        boolean r02 = r0.onStartNestedScroll(this, r2, r13, r14, r15, r16);
        r10 = r10 | r02;
        r11.setNestedScrollAccepted(r16, r02);
        goto L11
    L10:
        r11.setNestedScrollAccepted(r16, false);
    L11:
        r9 = r9 + 1;
        goto L3
    L12:
        return r10;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View r2) {
        onStopNestedScroll(r2, 0);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View r6, int r7) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(r6, r7);
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L12;
        View r2 = getChildAt(r1);
        LayoutParams r3 = (LayoutParams) r2.getLayoutParams();
        if (r3.isNestedScrollAccepted(r7) == false) goto L11;
        Behavior r4 = r3.getBehavior();
        if (r4 == null) goto L10;
        r4.onStopNestedScroll(this, r2, r6, r7);
    L10:
        r3.resetNestedScroll(r7);
        r3.resetChangedAfterNestedScroll();
    L11:
        r1 = r1 + 1;
        goto L3
    L12:
        this.mNestedScrollingTarget = null;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r18) {
        int r2 = r18.getActionMasked();
        if (this.mBehaviorTouchView != null) goto L8;
        boolean r3 = performIntercept(r18, 1);
        if (r3 == true) goto L9;
    L7:
        boolean r6 = false;
    L12:
        MotionEvent r8 = null;
        if (this.mBehaviorTouchView != null) goto L15;
        r6 = r6 | super.onTouchEvent(r18);
    L17:
        if (r8 == null) goto L19;
        r8.recycle();
    L19:
        if (r2 != 1) goto L21;
    L24:
        resetTouchBehaviors(false);
        return r6;
    L21:
        if (r2 == 3) goto L24;
        return r6;
    L15:
        if (r3 == false) goto L17;
        long r9 = SystemClock.uptimeMillis();
        r8 = MotionEvent.obtain(r9, r9, 3, 0.0f, 0.0f, 0);
        super.onTouchEvent(r8);
    L9:
        Behavior r62 = ((LayoutParams) this.mBehaviorTouchView.getLayoutParams()).getBehavior();
        if (r62 == null) goto L7;
        r6 = r62.onTouchEvent(this, this.mBehaviorTouchView, r18);
        goto L12
    L8:
        r3 = false;
        goto L9
    }

    public void recordLastChildRect(View r1, Rect r2) {
        ((LayoutParams) r1.getLayoutParams()).setLastChildRect(r2);
    }

    public void removePreDrawListener() {
        if (this.mIsAttachedToWindow == true) goto L5;
    L7:
        this.mNeedsPreDrawListener = false;
        return;
    L5:
        if (this.mOnPreDrawListener == null) goto L7;
        getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        goto L7
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View r2, Rect r3, boolean r4) {
        Behavior r0 = ((LayoutParams) r2.getLayoutParams()).getBehavior();
        if (r0 == null) goto L9;
        if (r0.onRequestChildRectangleOnScreen(this, r2, r3, r4) == false) goto L9;
        return true;
    L9:
        return super.requestChildRectangleOnScreen(r2, r3, r4);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean r1) {
        super.requestDisallowInterceptTouchEvent(r1);
        if (r1 == true) goto L5;
        return;
    L5:
        if (this.mDisallowInterceptReset == true) goto L9;
        resetTouchBehaviors(false);
        this.mDisallowInterceptReset = true;
        return;
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean r1) {
        super.setFitsSystemWindows(r1);
        setupForInsets();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener r1) {
        this.mOnHierarchyChangeListener = r1;
    }

    public void setStatusBarBackground(Drawable r3) {
        Drawable r0 = this.mStatusBarBackground;
        if (r0 == r3) goto L21;
        Drawable r1 = null;
        if (r0 == null) goto L7;
        r0.setCallback(null);
    L7:
        if (r3 == null) goto L9;
        r1 = r3.mutate();
    L9:
        this.mStatusBarBackground = r1;
        if (r1 != null) goto L12;
    L19:
        ViewCompat.postInvalidateOnAnimation(this);
        return;
    L12:
        if (r1.isStateful() == false) goto L14;
        this.mStatusBarBackground.setState(getDrawableState());
    L14:
        DrawableCompat.setLayoutDirection(this.mStatusBarBackground, ViewCompat.getLayoutDirection(this));
        Drawable r32 = this.mStatusBarBackground;
        if (getVisibility() != 0) goto L17;
        boolean r02 = true;
    L18:
        r32.setVisible(r02, false);
        this.mStatusBarBackground.setCallback(this);
        goto L19
    L17:
        r02 = false;
        goto L18
    }

    public void setStatusBarBackgroundColor(int r2) {
        setStatusBarBackground(new ColorDrawable(r2));
    }

    public void setStatusBarBackgroundResource(int r2) {
        if (r2 == 0) goto L4;
        Drawable r22 = ContextCompat.getDrawable(getContext(), r2);
    L5:
        setStatusBarBackground(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    @Override // android.view.View
    public void setVisibility(int r3) {
        super.setVisibility(r3);
        if (r3 != 0) goto L5;
        boolean r32 = true;
    L6:
        Drawable r1 = this.mStatusBarBackground;
        if (r1 != null) goto L9;
        return;
    L9:
        if (r1.isVisible() == r32) goto L13;
        this.mStatusBarBackground.setVisible(r32, false);
        return;
    L13:
        return;
    L5:
        r32 = false;
        goto L6
    }

    public final WindowInsetsCompat setWindowInsets(WindowInsetsCompat r4) {
        if (ObjectsCompat.equals(this.mLastInsets, r4) == true) goto L17;
        this.mLastInsets = r4;
        boolean r0 = false;
        if (r4 != null) goto L7;
    L9:
        boolean r2 = false;
    L10:
        this.mDrawStatusBarBackground = r2;
        if (r2 == false) goto L13;
    L15:
        setWillNotDraw(r0);
        WindowInsetsCompat r42 = dispatchApplyWindowInsetsToBehaviors(r4);
        requestLayout();
        return r42;
    L13:
        if (getBackground() != null) goto L15;
        r0 = true;
        goto L15
    L7:
        if (r4.getSystemWindowInsetTop() <= 0) goto L9;
        r2 = true;
        goto L10
    L17:
        return r4;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == false) goto L5;
        return true;
    L5:
        if (r2 == this.mStatusBarBackground) goto L11;
        return false;
    L11:
        return true;
    }
}
