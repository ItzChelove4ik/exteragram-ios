package androidx.recyclerview.widget;

import android.R;
import android.animation.LayoutTransition;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.core.os.TraceCompat;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.EdgeEffectCompat;
import androidx.customview.view.AbsSavedState;
import androidx.recyclerview.widget.AdapterHelper;
import androidx.recyclerview.widget.ChildHelper;
import androidx.recyclerview.widget.GapWorker;
import androidx.recyclerview.widget.ViewBoundsCheck;
import androidx.recyclerview.widget.ViewInfoStore;
import androidx.webkit.WebMessageCompat$$ExternalSyntheticBUOutline0;
import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.dex.code.CstInsn$$ExternalSyntheticBUOutline0;
import com.sun.jna.Structure$$ExternalSyntheticBUOutline2;
import dagger.internal.Preconditions$$ExternalSyntheticBUOutline0;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline4;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.tgnet.TLObject;
import retrofit2.Utils$$ExternalSyntheticBUOutline1;
import retrofit2.Utils$$ExternalSyntheticBUOutline2;

/* loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements NestedScrollingChild {
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC = false;
    static final boolean ALLOW_THREAD_GAP_WORK = true;
    private static final int[] CLIP_TO_PADDING_ATTR = null;
    static final boolean DEBUG = false;
    static final int DEFAULT_ORIENTATION = 1;
    static final boolean DISPATCH_TEMP_DETACH = false;
    private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION = false;
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST = false;
    static final long FOREVER_NS = Long.MAX_VALUE;
    public static final int HORIZONTAL = 0;
    private static final boolean IGNORE_DETACHED_FOCUSED_CHILD = false;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = null;
    static final int MAX_SCROLL_DURATION = 2000;
    private static final int[] NESTED_SCROLLING_ATTRS = null;
    public static final long NO_ID = -1;
    public static final int NO_POSITION = -1;
    static final boolean POST_UPDATES_ON_ANIMATION = true;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    static final String TAG = "RecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    static final String TRACE_PREFETCH_TAG = "RV Prefetch";
    static final String TRACE_SCROLL_TAG = "RV Scroll";
    static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
    static final boolean VERBOSE_TRACING = false;
    public static final int VERTICAL = 1;
    static final Interpolator sQuinticInterpolator = null;
    private int bottomGlowOffset;
    public boolean canStopFlinger;
    private Integer glowColor;
    private boolean isFlingerWorking;
    RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    Adapter mAdapter;
    AdapterHelper mAdapterHelper;
    boolean mAdapterUpdateDuringMeasure;
    private EdgeEffect mBottomGlow;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    public ChildHelper mChildHelper;
    boolean mClipToPadding;
    boolean mDataSetHasChangedAfterLayout;
    boolean mDispatchItemsChangedEvent;
    private int mDispatchScrollCounter;
    private int mEatenAccessibilityChangeFlags;
    private EdgeEffectFactory mEdgeEffectFactory;
    boolean mEnableFastScroller;
    boolean mFirstLayoutComplete;
    GapWorker mGapWorker;
    boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mInterceptRequestLayoutDepth;
    private OnItemTouchListener mInterceptingOnItemTouchListener;
    boolean mIsAttached;
    ItemAnimator mItemAnimator;
    private ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    final ArrayList<ItemDecoration> mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;
    LayoutManager mLayout;
    private int mLayoutOrScrollCounter;
    boolean mLayoutSuppressed;
    boolean mLayoutWasDefered;
    private EdgeEffect mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int[] mNestedOffsets;
    private final RecyclerViewDataObserver mObserver;
    private List<OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
    private OnFlingListener mOnFlingListener;
    private final ArrayList<OnItemTouchListener> mOnItemTouchListeners;
    final List<ViewHolder> mPendingAccessibilityImportanceChange;
    private SavedState mPendingSavedState;
    boolean mPostedAnimatorRunner;
    GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
    private boolean mPreserveFocusAfterLayout;
    public final Recycler mRecycler;
    RecyclerListener mRecyclerListener;
    final int[] mReusableIntPair;
    private EdgeEffect mRightGlow;
    private float mScaledHorizontalScrollFactor;
    private float mScaledVerticalScrollFactor;
    private OnScrollListener mScrollListener;
    private List<OnScrollListener> mScrollListeners;
    private final int[] mScrollOffset;
    private int mScrollPointerId;
    private int mScrollState;
    private NestedScrollingChildHelper mScrollingChildHelper;
    public final State mState;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    private EdgeEffect mTopGlow;
    private int mTouchSlop;
    final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    final ViewFlinger mViewFlinger;
    private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
    final ViewInfoStore mViewInfoStore;
    private String moreInfo;
    private int topGlowOffset;

    /* renamed from: androidx.recyclerview.widget.RecyclerView$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ RecyclerView this$0;

        public AnonymousClass1(RecyclerView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView r0 = this.this$0;
            if (r0.mFirstLayoutComplete == true) goto L5;
            return;
        L5:
            if (r0.isLayoutRequested() == true) goto L18;
            RecyclerView r2 = this.this$0;
            if (r2.mIsAttached == true) goto L12;
            r2.requestLayout();
            return;
        L12:
            if (r2.mLayoutSuppressed == false) goto L15;
            r2.mLayoutWasDefered = true;
            return;
        L15:
            r2.consumePendingUpdateOperations();
            return;
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ RecyclerView this$0;

        public AnonymousClass2(RecyclerView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            ItemAnimator r0 = this.this$0.mItemAnimator;
            if (r0 == null) goto L5;
            r0.runPendingAnimations();
        L5:
            this.this$0.mPostedAnimatorRunner = false;
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$3, reason: invalid class name */
    public class AnonymousClass3 implements Interpolator {
        public AnonymousClass3() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float r2) {
            float r22 = r2 - 1.0f;
            return ((((r22 * r22) * r22) * r22) * r22) + 1.0f;
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$4, reason: invalid class name */
    public class AnonymousClass4 implements ViewInfoStore.ProcessCallback {
        final /* synthetic */ RecyclerView this$0;

        public AnonymousClass4(RecyclerView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.ViewInfoStore.ProcessCallback
        public void processAppeared(ViewHolder r1, ItemAnimator.ItemHolderInfo r2, ItemAnimator.ItemHolderInfo r3) {
            this.this$0.animateAppearance(r1, r2, r3);
        }

        @Override // androidx.recyclerview.widget.ViewInfoStore.ProcessCallback
        public void processDisappeared(ViewHolder r2, ItemAnimator.ItemHolderInfo r3, ItemAnimator.ItemHolderInfo r4) {
            this.this$0.mRecycler.unscrapView(r2);
            this.this$0.animateDisappearance(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.ViewInfoStore.ProcessCallback
        public void processPersistent(ViewHolder r3, ItemAnimator.ItemHolderInfo r4, ItemAnimator.ItemHolderInfo r5) {
            r3.setIsRecyclable(false);
            RecyclerView r0 = this.this$0;
            if (r0.mDataSetHasChangedAfterLayout == false) goto L9;
            if (r0.mItemAnimator.animateChange(r3, r3, r4, r5) == false) goto L12;
            this.this$0.postAnimationRunner();
            return;
        L12:
            return;
        L9:
            if (r0.mItemAnimator.animatePersistence(r3, r4, r5) == false) goto L13;
            this.this$0.postAnimationRunner();
            return;
        }

        @Override // androidx.recyclerview.widget.ViewInfoStore.ProcessCallback
        public void unused(ViewHolder r2) {
            RecyclerView r1 = this.this$0;
            r1.mLayout.removeAndRecycleView(r2.itemView, r1.mRecycler);
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$5, reason: invalid class name */
    public class AnonymousClass5 implements ChildHelper.Callback {
        final /* synthetic */ RecyclerView this$0;

        public AnonymousClass5(RecyclerView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.ChildHelper.Callback
        public void addView(View r2, int r3) {
            this.this$0.addView(r2, r3);
            this.this$0.dispatchChildAttached(r2);
        }

        @Override // androidx.recyclerview.widget.ChildHelper.Callback
        public void attachViewToParent(View r3, int r4, ViewGroup.LayoutParams r5) {
            ViewHolder r0 = RecyclerView.getChildViewHolderInt(r3);
            if (r0 != null) goto L5;
        L12:
            RecyclerView.access$000(this.this$0, r3, r4, r5);
            return;
        L5:
            if (r0.isTmpDetached() == false) goto L7;
        L11:
            r0.clearTmpDetachFlag();
            goto L12
        L7:
            if (r0.shouldIgnore() == true) goto L11;
            throw new IllegalArgumentException("Called attach on a child which is not detached: " + r0 + this.this$0.exceptionLabel());
        }

        @Override // androidx.recyclerview.widget.ChildHelper.Callback
        public void detachViewFromParent(int r4) {
            View r0 = getChildAt(r4);
            if (r0 == null) goto L14;
            ViewHolder r02 = RecyclerView.getChildViewHolderInt(r0);
            if (r02 == null) goto L14;
            if (r02.isTmpDetached() == true) goto L9;
        L13:
            r02.addFlags(256);
            goto L14
        L9:
            if (r02.shouldIgnore() == true) goto L13;
            throw new IllegalArgumentException("called detach on an already detached child " + r02 + this.this$0.exceptionLabel());
        L14:
            RecyclerView.access$100(this.this$0, r4);
        }

        @Override // androidx.recyclerview.widget.ChildHelper.Callback
        public View getChildAt(int r1) {
            return this.this$0.getChildAt(r1);
        }

        @Override // androidx.recyclerview.widget.ChildHelper.Callback
        public int getChildCount() {
            return this.this$0.getChildCount();
        }

        @Override // androidx.recyclerview.widget.ChildHelper.Callback
        public ViewHolder getChildViewHolder(View r1) {
            return RecyclerView.getChildViewHolderInt(r1);
        }

        @Override // androidx.recyclerview.widget.ChildHelper.Callback
        public int indexOfChild(View r1) {
            return this.this$0.indexOfChild(r1);
        }

        @Override // androidx.recyclerview.widget.ChildHelper.Callback
        public void onEnteredHiddenState(View r1) {
            ViewHolder r12 = RecyclerView.getChildViewHolderInt(r1);
            if (r12 == null) goto L6;
            r12.onEnteredHiddenState(this.this$0);
            return;
        }

        @Override // androidx.recyclerview.widget.ChildHelper.Callback
        public void onLeftHiddenState(View r1) {
            ViewHolder r12 = RecyclerView.getChildViewHolderInt(r1);
            if (r12 == null) goto L6;
            r12.onLeftHiddenState(this.this$0);
            return;
        }

        @Override // androidx.recyclerview.widget.ChildHelper.Callback
        public void removeAllViews() {
            int r0 = getChildCount();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            View r2 = getChildAt(r1);
            this.this$0.dispatchChildDetached(r2);
            r2.clearAnimation();
            r1 = r1 + 1;
            goto L3
        L5:
            this.this$0.removeAllViews();
        }

        @Override // androidx.recyclerview.widget.ChildHelper.Callback
        public void removeViewAt(int r3) {
            View r0 = this.this$0.getChildAt(r3);
            if (r0 == null) goto L5;
            this.this$0.dispatchChildDetached(r0);
            r0.clearAnimation();
        L5:
            this.this$0.removeViewAt(r3);
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$6, reason: invalid class name */
    public class AnonymousClass6 implements AdapterHelper.Callback {
        final /* synthetic */ RecyclerView this$0;

        public AnonymousClass6(RecyclerView r1) {
            this.this$0 = r1;
        }

        public void dispatchUpdate(AdapterHelper.UpdateOp r4) {
            int r0 = r4.cmd;
            if (r0 != 1) goto L5;
            RecyclerView r3 = this.this$0;
            r3.mLayout.onItemsAdded(r3, r4.positionStart, r4.itemCount);
            return;
        L5:
            if (r0 != 2) goto L7;
            RecyclerView r32 = this.this$0;
            r32.mLayout.onItemsRemoved(r32, r4.positionStart, r4.itemCount);
            return;
        L7:
            if (r0 != 4) goto L9;
            RecyclerView r33 = this.this$0;
            r33.mLayout.onItemsUpdated(r33, r4.positionStart, r4.itemCount, r4.payload);
            return;
        L9:
            if (r0 == 8) goto L11;
            return;
        L11:
            RecyclerView r34 = this.this$0;
            r34.mLayout.onItemsMoved(r34, r4.positionStart, r4.itemCount, 1);
        }

        @Override // androidx.recyclerview.widget.AdapterHelper.Callback
        public ViewHolder findViewHolder(int r3) {
            ViewHolder r32 = this.this$0.findViewHolderForPosition(r3, true);
            if (r32 != null) goto L6;
            return null;
        L6:
            if (this.this$0.mChildHelper.isHidden(r32.itemView) == false) goto L8;
            return null;
        L8:
            return r32;
        }

        @Override // androidx.recyclerview.widget.AdapterHelper.Callback
        public void markViewHoldersUpdated(int r2, int r3, Object r4) {
            this.this$0.viewRangeUpdate(r2, r3, r4);
            this.this$0.mItemsChanged = true;
        }

        @Override // androidx.recyclerview.widget.AdapterHelper.Callback
        public void offsetPositionsForAdd(int r2, int r3) {
            this.this$0.offsetPositionRecordsForInsert(r2, r3);
            this.this$0.mItemsAddedOrRemoved = true;
        }

        @Override // androidx.recyclerview.widget.AdapterHelper.Callback
        public void offsetPositionsForMove(int r2, int r3) {
            this.this$0.offsetPositionRecordsForMove(r2, r3);
            this.this$0.mItemsAddedOrRemoved = true;
        }

        @Override // androidx.recyclerview.widget.AdapterHelper.Callback
        public void offsetPositionsForRemovingInvisible(int r3, int r4) {
            this.this$0.offsetPositionRecordsForRemove(r3, r4, true);
            RecyclerView r2 = this.this$0;
            r2.mItemsAddedOrRemoved = true;
            r2.mState.mDeletedInvisibleItemCountSincePreviousLayout += r4;
        }

        @Override // androidx.recyclerview.widget.AdapterHelper.Callback
        public void offsetPositionsForRemovingLaidOutOrNewView(int r3, int r4) {
            this.this$0.offsetPositionRecordsForRemove(r3, r4, false);
            this.this$0.mItemsAddedOrRemoved = true;
        }

        @Override // androidx.recyclerview.widget.AdapterHelper.Callback
        public void onDispatchFirstPass(AdapterHelper.UpdateOp r1) {
            dispatchUpdate(r1);
        }

        @Override // androidx.recyclerview.widget.AdapterHelper.Callback
        public void onDispatchSecondPass(AdapterHelper.UpdateOp r1) {
            dispatchUpdate(r1);
        }
    }

    public static abstract class Adapter<VH extends ViewHolder> {
        private boolean mHasStableIds;
        private final AdapterDataObservable mObservable;

        public Adapter() {
            this.mObservable = new AdapterDataObservable();
            this.mHasStableIds = false;
        }

        public final void bindViewHolder(VH r3, int r4) {
            r3.mPosition = r4;
            if (hasStableIds() == false) goto L5;
            r3.mItemId = getItemId(r4);
        L5:
            r3.setFlags(1, 519);
            TraceCompat.beginSection(RecyclerView.TRACE_BIND_VIEW_TAG);
            onBindViewHolder(r3, r4, r3.getUnmodifiedPayloads());
            r3.clearPayload();
            ViewGroup.LayoutParams r2 = r3.itemView.getLayoutParams();
            if ((r2 instanceof LayoutParams) == false) goto L8;
            ((LayoutParams) r2).mInsetsDirty = true;
        L8:
            TraceCompat.endSection();
        }

        public final VH createViewHolder(ViewGroup r2, int r3) {
            TraceCompat.beginSection(RecyclerView.TRACE_CREATE_VIEW_TAG);     // Catch: Throwable -> L9
            VH r1 = (VH) onCreateViewHolder(r2, r3);
            if (r1.itemView.getParent() != null) goto L8;
            r1.mItemViewType = r3;     // Catch: Throwable -> L9
            TraceCompat.endSection();
            return r1;
        L8:
            throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");     // Catch: Throwable -> L9
        L9:
            th = move-exception;
            TraceCompat.endSection();
            throw th;
        }

        public abstract int getItemCount();

        public long getItemId(int r1) {
            return -1;
        }

        public int getItemViewType(int r1) {
            return 0;
        }

        public final boolean hasObservers() {
            return this.mObservable.hasObservers();
        }

        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }

        public void notifyDataSetChanged() {
            this.mObservable.notifyChanged();
        }

        public void notifyItemChanged(int r2) {
            this.mObservable.notifyItemRangeChanged(r2, 1);
        }

        public void notifyItemChanged(int r2, Object r3) {
            this.mObservable.notifyItemRangeChanged(r2, 1, r3);
        }

        public void notifyItemInserted(int r2) {
            this.mObservable.notifyItemRangeInserted(r2, 1);
        }

        public void notifyItemMoved(int r1, int r2) {
            this.mObservable.notifyItemMoved(r1, r2);
        }

        public void notifyItemRangeChanged(int r1, int r2) {
            this.mObservable.notifyItemRangeChanged(r1, r2);
        }

        public void notifyItemRangeChanged(int r1, int r2, Object r3) {
            this.mObservable.notifyItemRangeChanged(r1, r2, r3);
        }

        public void notifyItemRangeInserted(int r1, int r2) {
            this.mObservable.notifyItemRangeInserted(r1, r2);
        }

        public void notifyItemRangeRemoved(int r1, int r2) {
            this.mObservable.notifyItemRangeRemoved(r1, r2);
        }

        public void notifyItemRemoved(int r2) {
            this.mObservable.notifyItemRangeRemoved(r2, 1);
        }

        public void onAttachedToRecyclerView(RecyclerView r1) {
        }

        public abstract void onBindViewHolder(VH r1, int r2);

        public void onBindViewHolder(VH r1, int r2, List<Object> r3) {
            onBindViewHolder(r1, r2);
        }

        public abstract VH onCreateViewHolder(ViewGroup r1, int r2);

        public void onDetachedFromRecyclerView(RecyclerView r1) {
        }

        public boolean onFailedToRecycleView(VH r1) {
            return false;
        }

        public void onViewAttachedToWindow(VH r1) {
        }

        public void onViewDetachedFromWindow(VH r1) {
        }

        public void onViewRecycled(VH r1) {
        }

        public void registerAdapterDataObserver(AdapterDataObserver r1) {
            this.mObservable.registerObserver(r1);
        }

        public void setHasStableIds(boolean r2) {
            if (hasObservers() == true) goto L6;
            this.mHasStableIds = r2;
            return;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }

        public void unregisterAdapterDataObserver(AdapterDataObserver r1) {
            this.mObservable.unregisterObserver(r1);
        }
    }

    public static class AdapterDataObservable extends Observable<AdapterDataObserver> {
        public AdapterDataObservable() {
        }

        public boolean hasObservers() {
            return !((Observable) this).mObservers.isEmpty();
        }

        public void notifyChanged() {
            int r0 = ((Observable) this).mObservers.size() - 1;
        L3:
            if (r0 < 0) goto L5;
            ((AdapterDataObserver) ((Observable) this).mObservers.get(r0)).onChanged();
            r0 = r0 - 1;
            goto L3
        }

        public void notifyItemMoved(int r4, int r5) {
            int r0 = ((Observable) this).mObservers.size() - 1;
        L3:
            if (r0 < 0) goto L5;
            ((AdapterDataObserver) ((Observable) this).mObservers.get(r0)).onItemRangeMoved(r4, r5, 1);
            r0 = r0 - 1;
            goto L3
        }

        public void notifyItemRangeChanged(int r2, int r3) {
            notifyItemRangeChanged(r2, r3, null);
        }

        public void notifyItemRangeChanged(int r3, int r4, Object r5) {
            int r0 = ((Observable) this).mObservers.size() - 1;
        L3:
            if (r0 < 0) goto L5;
            ((AdapterDataObserver) ((Observable) this).mObservers.get(r0)).onItemRangeChanged(r3, r4, r5);
            r0 = r0 - 1;
            goto L3
        }

        public void notifyItemRangeInserted(int r3, int r4) {
            int r0 = ((Observable) this).mObservers.size() - 1;
        L3:
            if (r0 < 0) goto L5;
            ((AdapterDataObserver) ((Observable) this).mObservers.get(r0)).onItemRangeInserted(r3, r4);
            r0 = r0 - 1;
            goto L3
        }

        public void notifyItemRangeRemoved(int r3, int r4) {
            int r0 = ((Observable) this).mObservers.size() - 1;
        L3:
            if (r0 < 0) goto L5;
            ((AdapterDataObserver) ((Observable) this).mObservers.get(r0)).onItemRangeRemoved(r3, r4);
            r0 = r0 - 1;
            goto L3
        }
    }

    public static abstract class AdapterDataObserver {
        public AdapterDataObserver() {
        }

        public void onChanged() {
        }

        public void onItemRangeChanged(int r1, int r2) {
        }

        public void onItemRangeChanged(int r1, int r2, Object r3) {
            onItemRangeChanged(r1, r2);
        }

        public void onItemRangeInserted(int r1, int r2) {
        }

        public void onItemRangeMoved(int r1, int r2, int r3) {
        }

        public void onItemRangeRemoved(int r1, int r2) {
        }
    }

    public interface ChildDrawingOrderCallback {
        int onGetChildDrawingOrder(int r1, int r2);
    }

    public static class EdgeEffectFactory {
        public EdgeEffectFactory() {
        }

        public EdgeEffect createEdgeEffect(RecyclerView r1, int r2) {
            return new EdgeEffect(r1.getContext());
        }
    }

    public static abstract class ItemAnimator {
        public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        public static final int FLAG_CHANGED = 2;
        public static final int FLAG_INVALIDATED = 4;
        public static final int FLAG_MOVED = 2048;
        public static final int FLAG_REMOVED = 8;
        private long mAddDelay;
        private long mAddDuration;
        private TimeInterpolator mAddInterpolator;
        private long mChangeAddDuration;
        private long mChangeDelay;
        private TimeInterpolator mChangeInterpolator;
        private long mChangeRemoveDuration;
        private long mDelay;
        private ArrayList<ItemAnimatorFinishedListener> mFinishedListeners;
        private ItemAnimatorListener mListener;
        private long mMoveDelay;
        private long mMoveDuration;
        private TimeInterpolator mMoveInterpolator;
        private long mRemoveDelay;
        private long mRemoveDuration;
        private TimeInterpolator mRemoveInterpolator;

        /* loaded from: classes4.dex */
        public interface ItemAnimatorFinishedListener {
            void onAnimationsFinished();
        }

        public interface ItemAnimatorListener {
            void onAnimationFinished(ViewHolder r1);
        }

        public static class ItemHolderInfo {
            public int bottom;
            public int left;
            public int right;
            public int top;

            public ItemHolderInfo() {
            }

            public ItemHolderInfo setFrom(ViewHolder r2) {
                return setFrom(r2, 0);
            }

            public ItemHolderInfo setFrom(ViewHolder r1, int r2) {
                View r12 = r1.itemView;
                this.left = r12.getLeft();
                this.top = r12.getTop();
                this.right = r12.getRight();
                this.bottom = r12.getBottom();
                return this;
            }
        }

        public ItemAnimator() {
            this.mListener = null;
            this.mFinishedListeners = new ArrayList();
            this.mAddDuration = 120;
            this.mRemoveDuration = 120;
            this.mMoveDuration = 250;
            this.mChangeAddDuration = 250;
            this.mChangeRemoveDuration = 250;
            this.mAddDelay = 0;
            this.mRemoveDelay = 0;
            this.mMoveDelay = 0;
            this.mDelay = 0;
            this.mChangeDelay = 0;
        }

        public static int buildAdapterChangeFlagsForAnimations(ViewHolder r4) {
            int r0 = r4.mFlags;
            int r1 = r0 & 14;
            if (r4.isInvalid() == false) goto L6;
            return 4;
        L6:
            if ((r0 & 4) != 0) goto L13;
            int r02 = r4.getOldPosition();
            int r42 = r4.getAdapterPosition();
            if (r02 == (-1)) goto L13;
            if (r42 == (-1)) goto L13;
            if (r02 == r42) goto L13;
            return r1 | 2048;
        L13:
            return r1;
        }

        public abstract boolean animateAppearance(ViewHolder r1, ItemHolderInfo r2, ItemHolderInfo r3);

        public abstract boolean animateChange(ViewHolder r1, ViewHolder r2, ItemHolderInfo r3, ItemHolderInfo r4);

        public abstract boolean animateDisappearance(RecyclerView r1, ViewHolder r2, ItemHolderInfo r3, ItemHolderInfo r4);

        public abstract boolean animatePersistence(ViewHolder r1, ItemHolderInfo r2, ItemHolderInfo r3);

        public abstract boolean canReuseUpdatedViewHolder(ViewHolder r1);

        public boolean canReuseUpdatedViewHolder(ViewHolder r1, List<Object> r2) {
            return canReuseUpdatedViewHolder(r1);
        }

        public final void dispatchAnimationFinished(ViewHolder r1) {
            onAnimationFinished(r1);
            ItemAnimatorListener r0 = this.mListener;
            if (r0 == null) goto L6;
            r0.onAnimationFinished(r1);
            return;
        }

        public final void dispatchAnimationStarted(ViewHolder r1) {
            onAnimationStarted(r1);
        }

        public final void dispatchAnimationsFinished() {
            int r0 = this.mFinishedListeners.size();
            int r1 = 0;
        L3:
            ArrayList<ItemAnimatorFinishedListener> r2 = this.mFinishedListeners;
            if (r1 >= r0) goto L6;
            r2.get(r1).onAnimationsFinished();
            r1 = r1 + 1;
            goto L3
        L6:
            r2.clear();
        }

        public abstract void endAnimation(ViewHolder r1);

        public abstract void endAnimations();

        public long getAddDelay() {
            return this.mAddDelay;
        }

        public long getAddDuration() {
            return this.mAddDuration;
        }

        public TimeInterpolator getAddInterpolator() {
            return this.mAddInterpolator;
        }

        public long getChangeAddDuration() {
            return this.mChangeAddDuration;
        }

        public long getChangeDelay() {
            return this.mChangeDelay;
        }

        public long getChangeDuration() {
            return Math.max(this.mChangeAddDuration, this.mChangeRemoveDuration);
        }

        public TimeInterpolator getChangeInterpolator() {
            return this.mChangeInterpolator;
        }

        public long getChangeRemoveDuration() {
            return this.mChangeRemoveDuration;
        }

        public long getMoveDelay() {
            return this.mMoveDelay;
        }

        public long getMoveDuration() {
            return this.mMoveDuration;
        }

        public TimeInterpolator getMoveInterpolator() {
            return this.mMoveInterpolator;
        }

        public long getRemoveDelay() {
            return this.mRemoveDelay;
        }

        public long getRemoveDuration() {
            return this.mRemoveDuration;
        }

        public TimeInterpolator getRemoveInterpolator() {
            return this.mRemoveInterpolator;
        }

        public abstract boolean isRunning();

        public final boolean isRunning(ItemAnimatorFinishedListener r2) {
            boolean r0 = isRunning();
            if (r2 == null) goto L8;
            if (r0 == true) goto L7;
            r2.onAnimationsFinished();
            return r0;
        L7:
            this.mFinishedListeners.add(r2);
        L8:
            return r0;
        }

        public ItemHolderInfo obtainHolderInfo() {
            return new ItemHolderInfo();
        }

        public void onAnimationFinished(ViewHolder r1) {
        }

        public void onAnimationStarted(ViewHolder r1) {
        }

        public ItemHolderInfo recordPostLayoutInformation(State r1, ViewHolder r2) {
            return obtainHolderInfo().setFrom(r2);
        }

        public ItemHolderInfo recordPreLayoutInformation(State r1, ViewHolder r2, int r3, List<Object> r4) {
            return obtainHolderInfo().setFrom(r2);
        }

        public abstract void runPendingAnimations();

        public void setAddDelay(long r1) {
            this.mAddDelay = r1;
        }

        public void setAddDuration(long r1) {
            this.mAddDuration = r1;
        }

        public void setChangeDelay(long r1) {
            this.mChangeDelay = r1;
        }

        public void setChangeDuration(long r1) {
            this.mChangeAddDuration = r1;
            this.mChangeRemoveDuration = r1;
        }

        public void setChangeDuration(long r1, long r3) {
            this.mChangeAddDuration = r1;
            this.mChangeRemoveDuration = r3;
        }

        public void setDurations(long r1) {
            this.mAddDuration = r1;
            this.mMoveDuration = r1;
            this.mRemoveDuration = r1;
            this.mChangeAddDuration = r1;
            this.mChangeRemoveDuration = r1;
        }

        public void setInterpolator(TimeInterpolator r1) {
            this.mAddInterpolator = r1;
            this.mMoveInterpolator = r1;
            this.mRemoveInterpolator = r1;
            this.mChangeInterpolator = r1;
        }

        public void setListener(ItemAnimatorListener r1) {
            this.mListener = r1;
        }

        public void setMoveDelay(long r1) {
            this.mMoveDelay = r1;
        }

        public void setMoveDuration(long r1) {
            this.mMoveDuration = r1;
        }

        public void setMoveInterpolator(TimeInterpolator r1) {
            this.mMoveInterpolator = r1;
        }

        public void setRemoveDelay(long r1) {
            this.mRemoveDelay = r1;
        }

        public void setRemoveDuration(long r1) {
            this.mRemoveDuration = r1;
        }
    }

    public class ItemAnimatorRestoreListener implements ItemAnimator.ItemAnimatorListener {
        final /* synthetic */ RecyclerView this$0;

        public ItemAnimatorRestoreListener(RecyclerView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator.ItemAnimatorListener
        public void onAnimationFinished(ViewHolder r3) {
            r3.setIsRecyclable(true);
            if (r3.mShadowedHolder != null) goto L5;
        L7:
            r3.mShadowingHolder = null;
            if (r3.shouldBeKeptAsChild() == false) goto L10;
            return;
        L10:
            if (this.this$0.removeAnimatingView(r3.itemView) == false) goto L12;
            return;
        L12:
            if (r3.isTmpDetached() == false) goto L17;
            this.this$0.removeDetachedView(r3.itemView, false);
            return;
        L17:
            return;
        L5:
            if (r3.mShadowingHolder != null) goto L7;
            r3.mShadowedHolder = null;
            goto L7
        }
    }

    public static abstract class ItemDecoration {
        public ItemDecoration() {
        }

        @Deprecated
        public void getItemOffsets(Rect r1, int r2, RecyclerView r3) {
            r1.set(0, 0, 0, 0);
        }

        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, State r4) {
            getItemOffsets(r1, ((LayoutParams) r2.getLayoutParams()).getViewLayoutPosition(), r3);
        }

        @Deprecated
        public void onDraw(Canvas r1, RecyclerView r2) {
        }

        public void onDraw(Canvas r1, RecyclerView r2, State r3) {
            onDraw(r1, r2);
        }

        @Deprecated
        public void onDrawOver(Canvas r1, RecyclerView r2) {
        }

        public void onDrawOver(Canvas r1, RecyclerView r2, State r3) {
            onDrawOver(r1, r2);
        }
    }

    public static abstract class LayoutManager {
        boolean mAutoMeasure;
        ChildHelper mChildHelper;
        private int mHeight;
        private int mHeightMode;
        ViewBoundsCheck mHorizontalBoundCheck;
        private final ViewBoundsCheck.Callback mHorizontalBoundCheckCallback;
        boolean mIsAttachedToWindow;
        private boolean mItemPrefetchEnabled;
        private boolean mMeasurementCacheEnabled;
        int mPrefetchMaxCountObserved;
        boolean mPrefetchMaxObservedInInitialPrefetch;
        RecyclerView mRecyclerView;
        boolean mRequestedSimpleAnimations;
        SmoothScroller mSmoothScroller;
        ViewBoundsCheck mVerticalBoundCheck;
        private final ViewBoundsCheck.Callback mVerticalBoundCheckCallback;
        private int mWidth;
        private int mWidthMode;

        /* renamed from: androidx.recyclerview.widget.RecyclerView$LayoutManager$1, reason: invalid class name */
        public class AnonymousClass1 implements ViewBoundsCheck.Callback {
            final /* synthetic */ LayoutManager this$0;

            public AnonymousClass1(LayoutManager r1) {
                this.this$0 = r1;
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
            public View getChildAt(int r1) {
                return this.this$0.getChildAt(r1);
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
            public int getChildEnd(View r2) {
                LayoutParams r0 = (LayoutParams) r2.getLayoutParams();
                return this.this$0.getDecoratedRight(r2) + ((ViewGroup.MarginLayoutParams) r0).rightMargin;
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
            public int getChildStart(View r2) {
                LayoutParams r0 = (LayoutParams) r2.getLayoutParams();
                return this.this$0.getDecoratedLeft(r2) - ((ViewGroup.MarginLayoutParams) r0).leftMargin;
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
            public int getParentEnd() {
                return this.this$0.getWidth() - this.this$0.getPaddingRight();
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
            public int getParentStart() {
                return this.this$0.getPaddingLeft();
            }
        }

        /* renamed from: androidx.recyclerview.widget.RecyclerView$LayoutManager$2, reason: invalid class name */
        public class AnonymousClass2 implements ViewBoundsCheck.Callback {
            final /* synthetic */ LayoutManager this$0;

            public AnonymousClass2(LayoutManager r1) {
                this.this$0 = r1;
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
            public View getChildAt(int r1) {
                return this.this$0.getChildAt(r1);
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
            public int getChildEnd(View r2) {
                LayoutParams r0 = (LayoutParams) r2.getLayoutParams();
                return this.this$0.getDecoratedBottom(r2) + ((ViewGroup.MarginLayoutParams) r0).bottomMargin;
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
            public int getChildStart(View r2) {
                LayoutParams r0 = (LayoutParams) r2.getLayoutParams();
                return this.this$0.getDecoratedTop(r2) - ((ViewGroup.MarginLayoutParams) r0).topMargin;
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
            public int getParentEnd() {
                return this.this$0.getHeight() - this.this$0.getPaddingBottom();
            }

            @Override // androidx.recyclerview.widget.ViewBoundsCheck.Callback
            public int getParentStart() {
                return this.this$0.getParentStart();
            }
        }

        public interface LayoutPrefetchRegistry {
            void addPosition(int r1, int r2);
        }

        public LayoutManager() {
            AnonymousClass1 r0 = new AnonymousClass1(this);
            this.mHorizontalBoundCheckCallback = r0;
            AnonymousClass2 r1 = new AnonymousClass2(this);
            this.mVerticalBoundCheckCallback = r1;
            this.mHorizontalBoundCheck = new ViewBoundsCheck(r0);
            this.mVerticalBoundCheck = new ViewBoundsCheck(r1);
            this.mRequestedSimpleAnimations = false;
            this.mIsAttachedToWindow = false;
            this.mAutoMeasure = false;
            this.mMeasurementCacheEnabled = true;
            this.mItemPrefetchEnabled = true;
        }

        private void addViewInt(View r6, int r7, boolean r8) {
            ViewHolder r0 = RecyclerView.getChildViewHolderInt(r6);
            if (r8 == false) goto L5;
        L8:
            this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(r0);
        L9:
            LayoutParams r82 = (LayoutParams) r6.getLayoutParams();
            if (r0.wasReturnedFromScrap() == true) goto L30;
            if (r0.isScrap() == true) goto L30;
            ViewParent r1 = r6.getParent();
            RecyclerView r3 = this.mRecyclerView;
            ChildHelper r4 = this.mChildHelper;
            if (r1 != r3) goto L24;
            int r12 = r4.indexOfChild(r6);
            if (r7 != (-1)) goto L19;
            r7 = this.mChildHelper.getChildCount();
        L19:
            if (r12 == (-1)) goto L23;
            if (r12 == r7) goto L35;
            this.mRecyclerView.mLayout.moveView(r12, r7);
        L35:
            if (r82.mPendingInvalidate == false) goto L38;
            r0.itemView.invalidate();
            r82.mPendingInvalidate = false;
            return;
        L38:
            return;
        L23:
            throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.mRecyclerView.indexOfChild(r6) + this.mRecyclerView.exceptionLabel());
        L24:
            r4.addView(r6, r7, false);
            r82.mInsetsDirty = true;
            SmoothScroller r72 = this.mSmoothScroller;
            if (r72 == null) goto L35;
            if (r72.isRunning() == false) goto L35;
            this.mSmoothScroller.onChildAttachedToWindow(r6);
        L30:
            if (r0.isScrap() == false) goto L32;
            r0.unScrap();
        L33:
            this.mChildHelper.attachViewToParent(r6, r7, r6.getLayoutParams(), false);
            goto L35
        L32:
            r0.clearReturnedFromScrapFlag();
            goto L33
        L5:
            if (r0.isRemoved() == true) goto L8;
            this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(r0);
            goto L9
        }

        public static int chooseSize(int r2, int r3, int r4) {
            int r0 = View.MeasureSpec.getMode(r2);
            int r22 = View.MeasureSpec.getSize(r2);
            if (r0 == Integer.MIN_VALUE) goto L9;
            if (r0 != 1073741824) goto L7;
            return r22;
        L7:
            return Math.max(r3, r4);
        L9:
            return Math.min(r22, Math.max(r3, r4));
        }

        private void detachViewInternal(int r1, View r2) {
            this.mChildHelper.detachViewFromParent(r1);
        }

        public static int getChildMeasureSpec(int r4, int r5, int r6, int r7, boolean r8) {
            int r42 = Math.max(0, r4 - r6);
            if (r8 == false) goto L11;
            if (r7 < 0) goto L6;
        L5:
            r5 = 1073741824;
        L22:
            return View.MeasureSpec.makeMeasureSpec(r7, r5);
        L6:
            if (r7 != (-1)) goto L10;
            if (r5 == Integer.MIN_VALUE) goto L14;
            if (r5 == 0) goto L10;
            if (r5 != 1073741824) goto L10;
        L14:
            r7 = r42;
        L10:
            r5 = 0;
            r7 = 0;
            goto L22
        L11:
            if (r7 >= 0) goto L5;
            if (r7 == (-1)) goto L14;
            if (r7 != (-2)) goto L10;
            if (r5 == Integer.MIN_VALUE) goto L20;
            if (r5 == 1073741824) goto L20;
            r7 = r42;
            r5 = 0;
        L20:
            r7 = r42;
            r5 = Integer.MIN_VALUE;
            goto L22
        }

        @Deprecated
        public static int getChildMeasureSpec(int r1, int r2, int r3, boolean r4) {
            int r12 = r1 - r2;
            int r22 = 0;
            int r13 = Math.max(0, r12);
            if (r4 == false) goto L7;
            if (r3 < 0) goto L6;
        L5:
            r22 = 1073741824;
        L16:
            return View.MeasureSpec.makeMeasureSpec(r3, r22);
        L6:
            r3 = 0;
            goto L16
        L7:
            if (r3 >= 0) goto L5;
            if (r3 != (-1)) goto L13;
            r3 = r13;
            goto L5
        L13:
            if (r3 != (-2)) goto L6;
            r22 = Integer.MIN_VALUE;
            r3 = r13;
            goto L16
        }

        private boolean isFocusedChildVisibleAfterScrolling(RecyclerView r7, int r8, int r9) {
            View r72 = r7.getFocusedChild();
            if (r72 != null) goto L5;
            return false;
        L5:
            int r1 = getPaddingLeft();
            int r2 = getPaddingTop();
            int r3 = getWidth() - getPaddingRight();
            int r4 = getHeight() - getPaddingBottom();
            Rect r5 = this.mRecyclerView.mTempRect;
            getDecoratedBoundsWithMargins(r72, r5);
            if ((r5.left - r8) < r3) goto L8;
        L16:
            return false;
        L8:
            if ((r5.right - r8) <= r1) goto L16;
            if ((r5.top - r9) >= r4) goto L16;
            if ((r5.bottom - r9) <= r2) goto L16;
            return true;
        }

        private static boolean isMeasurementUpToDate(int r3, int r4, int r5) {
            int r0 = View.MeasureSpec.getMode(r4);
            int r42 = View.MeasureSpec.getSize(r4);
            if (r5 <= 0) goto L7;
            if (r3 == r5) goto L7;
            return false;
        L7:
            if (r0 == Integer.MIN_VALUE) goto L16;
            if (r0 != 0) goto L10;
            return true;
        L10:
            if (r0 == 1073741824) goto L12;
            return false;
        L12:
            if (r42 != r3) goto L14;
            return true;
        L14:
            return false;
        L16:
            if (r42 < r3) goto L18;
            return true;
        L18:
            return false;
        }

        private void scrapOrRecycleView(Recycler r3, int r4, View r5) {
            ViewHolder r0 = RecyclerView.getChildViewHolderInt(r5);
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.shouldIgnore() == false) goto L8;
            return;
        L8:
            if (r0.isInvalid() == true) goto L10;
        L15:
            detachViewAt(r4);
            r3.scrapView(r5);
            this.mRecyclerView.mViewInfoStore.onViewDetached(r0);
            return;
        L10:
            if (r0.isRemoved() == true) goto L15;
            if (this.mRecyclerView.mAdapter.hasStableIds() == true) goto L15;
            removeViewAt(r4);
            r3.recycleViewHolderInternal(r0);
        }

        public void addDisappearingView(View r2) {
            addDisappearingView(r2, -1);
        }

        public void addDisappearingView(View r2, int r3) {
            addViewInt(r2, r3, true);
        }

        public void addView(View r2) {
            addView(r2, -1);
        }

        public void addView(View r2, int r3) {
            addViewInt(r2, r3, false);
        }

        public void assertInLayoutOrScroll(String r1) {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 == null) goto L6;
            r0.assertInLayoutOrScroll(r1);
            return;
        }

        public void assertNotInLayoutOrScroll(String r1) {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 == null) goto L6;
            r0.assertNotInLayoutOrScroll(r1);
            return;
        }

        public void attachView(View r2) {
            attachView(r2, -1);
        }

        public void attachView(View r2, int r3) {
            attachView(r2, r3, (LayoutParams) r2.getLayoutParams());
        }

        public void attachView(View r4, int r5, LayoutParams r6) {
            ViewHolder r0 = RecyclerView.getChildViewHolderInt(r4);
            boolean r1 = r0.isRemoved();
            RecyclerView r2 = this.mRecyclerView;
            if (r1 == false) goto L5;
            r2.mViewInfoStore.addToDisappearedInLayout(r0);
        L6:
            this.mChildHelper.attachViewToParent(r4, r5, r6, r0.isRemoved());
            return;
        L5:
            r2.mViewInfoStore.removeFromDisappearedInLayout(r0);
            goto L6
        }

        public void calculateItemDecorationsForChild(View r1, Rect r2) {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 != null) goto L6;
            r2.set(0, 0, 0, 0);
            return;
        L6:
            r2.set(r0.getItemDecorInsetsForChild(r1));
        }

        public abstract boolean canScrollHorizontally();

        public abstract boolean canScrollVertically();

        public boolean checkLayoutParams(LayoutParams r1) {
            if (r1 == null) goto L5;
            return true;
        L5:
            return false;
        }

        public void collectAdjacentPrefetchPositions(int r1, int r2, State r3, LayoutPrefetchRegistry r4) {
        }

        public void collectInitialPrefetchPositions(int r1, LayoutPrefetchRegistry r2) {
        }

        public abstract int computeHorizontalScrollExtent(State r1);

        public abstract int computeHorizontalScrollOffset(State r1);

        public abstract int computeHorizontalScrollRange(State r1);

        public abstract int computeVerticalScrollExtent(State r1);

        public abstract int computeVerticalScrollOffset(State r1);

        public abstract int computeVerticalScrollRange(State r1);

        public void detachAndScrapAttachedViews(Recycler r3) {
            int r0 = getChildCount() - 1;
        L3:
            if (r0 < 0) goto L5;
            scrapOrRecycleView(r3, r0, getChildAt(r0));
            r0 = r0 - 1;
            goto L3
        }

        public void detachAndScrapView(View r2, Recycler r3) {
            scrapOrRecycleView(r3, this.mChildHelper.indexOfChild(r2), r2);
        }

        public void detachAndScrapViewAt(int r2, Recycler r3) {
            scrapOrRecycleView(r3, r2, getChildAt(r2));
        }

        public void detachView(View r2) {
            int r0 = this.mChildHelper.indexOfChild(r2);
            if (r0 < 0) goto L6;
            detachViewInternal(r0, r2);
            return;
        }

        public void detachViewAt(int r2) {
            detachViewInternal(r2, getChildAt(r2));
        }

        public void dispatchAttachedToWindow(RecyclerView r2) {
            this.mIsAttachedToWindow = true;
            onAttachedToWindow(r2);
        }

        public void dispatchDetachedFromWindow(RecyclerView r2, Recycler r3) {
            this.mIsAttachedToWindow = false;
            onDetachedFromWindow(r2, r3);
        }

        public void endAnimation(View r1) {
            ItemAnimator r0 = this.mRecyclerView.mItemAnimator;
            if (r0 == null) goto L6;
            r0.endAnimation(RecyclerView.getChildViewHolderInt(r1));
            return;
        }

        public View findContainingItemView(View r3) {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 != null) goto L5;
            return null;
        L5:
            View r32 = r0.findContainingItemView(r3);
            if (r32 != null) goto L9;
            return null;
        L9:
            if (this.mChildHelper.isHidden(r32) == false) goto L11;
            return null;
        L11:
            return r32;
        }

        public View findViewByPosition(int r6) {
            int r0 = getChildCount();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L17;
            View r2 = getChildAt(r1);
            ViewHolder r3 = RecyclerView.getChildViewHolderInt(r2);
            if (r3 == null) goto L16;
            if (r3.getLayoutPosition() != r6) goto L16;
            if (r3.shouldIgnore() == true) goto L16;
            if (this.mRecyclerView.mState.isPreLayout() == true) goto L15;
            if (r3.isRemoved() == true) goto L16;
        L15:
            return r2;
        L16:
            r1 = r1 + 1;
            goto L3
        L17:
            return null;
        }

        public abstract LayoutParams generateDefaultLayoutParams();

        public LayoutParams generateLayoutParams(Context r1, AttributeSet r2) {
            return new LayoutParams(r1, r2);
        }

        public LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
            if ((r1 instanceof LayoutParams) == false) goto L7;
            return new LayoutParams((LayoutParams) r1);
        L7:
            if ((r1 instanceof ViewGroup.MarginLayoutParams) == false) goto L11;
            return new LayoutParams((ViewGroup.MarginLayoutParams) r1);
        L11:
            return new LayoutParams(r1);
        }

        public int getBaseline() {
            return -1;
        }

        public int getBottomDecorationHeight(View r1) {
            return ((LayoutParams) r1.getLayoutParams()).mDecorInsets.bottom;
        }

        public View getChildAt(int r1) {
            ChildHelper r0 = this.mChildHelper;
            if (r0 != null) goto L5;
            return null;
        L5:
            return r0.getChildAt(r1);
        }

        public int getChildCount() {
            ChildHelper r0 = this.mChildHelper;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return r0.getChildCount();
        }

        public int[] getChildRectangleOnScreenScrollAmount(View r8, Rect r9) {
            int r0 = getPaddingLeft();
            int r1 = getPaddingTop();
            int r2 = getWidth() - getPaddingRight();
            int r3 = getHeight() - getPaddingBottom();
            int r4 = (r8.getLeft() + r9.left) - r8.getScrollX();
            int r5 = (r8.getTop() + r9.top) - r8.getScrollY();
            int r82 = r9.width() + r4;
            int r92 = r9.height() + r5;
            int r42 = r4 - r0;
            int r6 = Math.min(0, r42);
            int r52 = r5 - r1;
            int r12 = Math.min(0, r52);
            int r83 = r82 - r2;
            int r22 = Math.max(0, r83);
            int r93 = Math.max(0, r92 - r3);
            if (getLayoutDirection() != 1) goto L7;
            if (r22 != 0) goto L11;
            r22 = Math.max(r6, r83);
        L11:
            if (r12 != 0) goto L15;
            r12 = Math.min(r52, r93);
        L15:
            return new int[]{r22, r12};
        L7:
            if (r6 != 0) goto L10;
            r6 = Math.min(r42, r22);
        L10:
            r22 = r6;
            goto L11
        }

        public boolean getClipToPadding() {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.mClipToPadding == false) goto L10;
            return true;
        L10:
            return false;
        }

        public int getColumnCountForAccessibility(Recycler r1, State r2) {
            RecyclerView r12 = this.mRecyclerView;
            if (r12 != null) goto L5;
        L11:
            return 1;
        L5:
            if (r12.mAdapter == null) goto L11;
            if (canScrollHorizontally() == false) goto L11;
            return this.mRecyclerView.mAdapter.getItemCount();
        }

        public int getDecoratedBottom(View r2) {
            return r2.getBottom() + getBottomDecorationHeight(r2);
        }

        public void getDecoratedBoundsWithMargins(View r1, Rect r2) {
            RecyclerView.getDecoratedBoundsWithMarginsInt(r1, r2);
        }

        public int getDecoratedLeft(View r2) {
            return r2.getLeft() - getLeftDecorationWidth(r2);
        }

        public int getDecoratedMeasuredHeight(View r2) {
            Rect r1 = ((LayoutParams) r2.getLayoutParams()).mDecorInsets;
            return (r2.getMeasuredHeight() + r1.top) + r1.bottom;
        }

        public int getDecoratedMeasuredWidth(View r2) {
            Rect r1 = ((LayoutParams) r2.getLayoutParams()).mDecorInsets;
            return (r2.getMeasuredWidth() + r1.left) + r1.right;
        }

        public int getDecoratedRight(View r2) {
            return r2.getRight() + getRightDecorationWidth(r2);
        }

        public int getDecoratedTop(View r2) {
            return r2.getTop() - getTopDecorationHeight(r2);
        }

        public View getFocusedChild() {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 != null) goto L5;
            return null;
        L5:
            View r02 = r0.getFocusedChild();
            if (r02 != null) goto L8;
        L11:
            return null;
        L8:
            if (this.mChildHelper.isHidden(r02) == true) goto L11;
            return r02;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public int getHeightMode() {
            return this.mHeightMode;
        }

        public int getItemCount() {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 == null) goto L5;
            Adapter r02 = r0.getAdapter();
        L6:
            if (r02 != null) goto L8;
            return 0;
        L8:
            return r02.getItemCount();
        L5:
            r02 = null;
            goto L6
        }

        public int getItemViewType(View r1) {
            return RecyclerView.getChildViewHolderInt(r1).getItemViewType();
        }

        public int getLayoutDirection() {
            return ViewCompat.getLayoutDirection(this.mRecyclerView);
        }

        public int getLeftDecorationWidth(View r1) {
            return ((LayoutParams) r1.getLayoutParams()).mDecorInsets.left;
        }

        public int getMinimumHeight() {
            return ViewCompat.getMinimumHeight(this.mRecyclerView);
        }

        public int getMinimumWidth() {
            return ViewCompat.getMinimumWidth(this.mRecyclerView);
        }

        public int getPaddingBottom() {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return r0.getPaddingBottom();
        }

        public int getPaddingEnd() {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return ViewCompat.getPaddingEnd(r0);
        }

        public int getPaddingLeft() {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return r0.getPaddingLeft();
        }

        public int getPaddingRight() {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return r0.getPaddingRight();
        }

        public int getPaddingStart() {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return ViewCompat.getPaddingStart(r0);
        }

        public int getPaddingTop() {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return r0.getPaddingTop();
        }

        public int getParentStart() {
            return getPaddingTop();
        }

        public int getPosition(View r1) {
            return ((LayoutParams) r1.getLayoutParams()).getViewLayoutPosition();
        }

        public int getRightDecorationWidth(View r1) {
            return ((LayoutParams) r1.getLayoutParams()).mDecorInsets.right;
        }

        public int getRowCountForAccessibility(Recycler r1, State r2) {
            RecyclerView r12 = this.mRecyclerView;
            if (r12 != null) goto L5;
        L11:
            return 1;
        L5:
            if (r12.mAdapter == null) goto L11;
            if (canScrollVertically() == false) goto L11;
            return this.mRecyclerView.mAdapter.getItemCount();
        }

        public int getSelectionModeForAccessibility(Recycler r1, State r2) {
            return 0;
        }

        public int getStartAfterPadding() {
            return getPaddingTop();
        }

        public int getTopDecorationHeight(View r1) {
            return ((LayoutParams) r1.getLayoutParams()).mDecorInsets.top;
        }

        public int getTotalSpace() {
            return (getHeight() - getPaddingTop()) - getPaddingBottom();
        }

        public void getTransformedBoundingBox(View r5, boolean r6, Rect r7) {
            if (r6 == false) goto L4;
            Rect r62 = ((LayoutParams) r5.getLayoutParams()).mDecorInsets;
            r7.set(-r62.left, -r62.top, r5.getWidth() + r62.right, r5.getHeight() + r62.bottom);
        L6:
            if (this.mRecyclerView == null) goto L12;
            Matrix r63 = r5.getMatrix();
            if (r63 == null) goto L12;
            if (r63.isIdentity() == true) goto L12;
            RectF r4 = this.mRecyclerView.mTempRectF;
            r4.set(r7);
            r63.mapRect(r4);
            r7.set((int) Math.floor(r4.left), (int) Math.floor(r4.top), (int) Math.ceil(r4.right), (int) Math.ceil(r4.bottom));
        L12:
            r7.offset(r5.getLeft(), r5.getTop());
            return;
        L4:
            r7.set(0, 0, r5.getWidth(), r5.getHeight());
            goto L6
        }

        public int getWidth() {
            return this.mWidth;
        }

        public int getWidthMode() {
            return this.mWidthMode;
        }

        public boolean hasFlexibleChildInBothOrientations() {
            int r0 = getChildCount();
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L11;
            ViewGroup.LayoutParams r3 = getChildAt(r2).getLayoutParams();
            if (r3.width >= 0) goto L10;
            if (r3.height >= 0) goto L10;
            return true;
        L10:
            r2 = r2 + 1;
            goto L3
        L11:
            return false;
        }

        public boolean hasFocus() {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.hasFocus() == false) goto L10;
            return true;
        L10:
            return false;
        }

        public void ignoreView(View r3) {
            ViewParent r0 = r3.getParent();
            RecyclerView r1 = this.mRecyclerView;
            if (r0 == r1) goto L5;
        L8:
            Buffer$$ExternalSyntheticBUOutline4.m("View should be fully attached to be ignored", this.mRecyclerView.exceptionLabel());
            return;
        L5:
            if (r1.indexOfChild(r3) == (-1)) goto L8;
            ViewHolder r32 = RecyclerView.getChildViewHolderInt(r3);
            r32.addFlags(128);
            this.mRecyclerView.mViewInfoStore.removeViewHolder(r32);
        }

        public boolean isAttachedToWindow() {
            return this.mIsAttachedToWindow;
        }

        public abstract boolean isAutoMeasureEnabled();

        public boolean isFocused() {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.isFocused() == false) goto L10;
            return true;
        L10:
            return false;
        }

        public final boolean isItemPrefetchEnabled() {
            return this.mItemPrefetchEnabled;
        }

        public boolean isLayoutHierarchical(Recycler r1, State r2) {
            return false;
        }

        public boolean isMeasurementCacheEnabled() {
            return this.mMeasurementCacheEnabled;
        }

        public boolean isSmoothScrolling() {
            SmoothScroller r0 = this.mSmoothScroller;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.isRunning() == false) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isViewPartiallyVisible(View r3, boolean r4, boolean r5) {
            if (this.mHorizontalBoundCheck.isViewWithinBoundFlags(r3, 24579) == true) goto L5;
        L7:
            boolean r2 = false;
        L8:
            if (r4 == false) goto L11;
            return r2;
        L11:
            return !r2;
        L5:
            if (this.mVerticalBoundCheck.isViewWithinBoundFlags(r3, 24579) == false) goto L7;
            r2 = true;
            goto L8
        }

        public void layoutDecorated(View r2, int r3, int r4, int r5, int r6) {
            Rect r1 = ((LayoutParams) r2.getLayoutParams()).mDecorInsets;
            r2.layout(r3 + r1.left, r4 + r1.top, r5 - r1.right, r6 - r1.bottom);
        }

        public void layoutDecoratedWithMargins(View r3, int r4, int r5, int r6, int r7) {
            LayoutParams r2 = (LayoutParams) r3.getLayoutParams();
            Rect r0 = r2.mDecorInsets;
            r3.layout((r4 + r0.left) + ((ViewGroup.MarginLayoutParams) r2).leftMargin, (r5 + r0.top) + ((ViewGroup.MarginLayoutParams) r2).topMargin, (r6 - r0.right) - ((ViewGroup.MarginLayoutParams) r2).rightMargin, (r7 - r0.bottom) - ((ViewGroup.MarginLayoutParams) r2).bottomMargin);
        }

        public void measureChild(View r6, int r7, int r8) {
            LayoutParams r0 = (LayoutParams) r6.getLayoutParams();
            Rect r1 = this.mRecyclerView.getItemDecorInsetsForChild(r6);
            int r72 = r7 + (r1.left + r1.right);
            int r82 = r8 + (r1.top + r1.bottom);
            int r73 = getChildMeasureSpec(getWidth(), getWidthMode(), (getPaddingLeft() + getPaddingRight()) + r72, ((ViewGroup.MarginLayoutParams) r0).width, canScrollHorizontally());
            int r83 = getChildMeasureSpec(getHeight(), getHeightMode(), (getPaddingTop() + getPaddingBottom()) + r82, ((ViewGroup.MarginLayoutParams) r0).height, canScrollVertically());
            if (shouldMeasureChild(r6, r73, r83, r0) == false) goto L6;
            r6.measure(r73, r83);
            return;
        }

        public void measureChildWithMargins(View r6, int r7, int r8) {
            LayoutParams r0 = (LayoutParams) r6.getLayoutParams();
            Rect r1 = this.mRecyclerView.getItemDecorInsetsForChild(r6);
            int r72 = r7 + (r1.left + r1.right);
            int r82 = r8 + (r1.top + r1.bottom);
            int r73 = getChildMeasureSpec(getWidth(), getWidthMode(), (((getPaddingLeft() + getPaddingRight()) + ((ViewGroup.MarginLayoutParams) r0).leftMargin) + ((ViewGroup.MarginLayoutParams) r0).rightMargin) + r72, ((ViewGroup.MarginLayoutParams) r0).width, canScrollHorizontally());
            int r83 = getChildMeasureSpec(getHeight(), getHeightMode(), (((getPaddingTop() + getPaddingBottom()) + ((ViewGroup.MarginLayoutParams) r0).topMargin) + ((ViewGroup.MarginLayoutParams) r0).bottomMargin) + r82, ((ViewGroup.MarginLayoutParams) r0).height, canScrollVertically());
            if (shouldMeasureChild(r6, r73, r83, r0) == false) goto L6;
            r6.measure(r73, r83);
            return;
        }

        public void moveView(int r3, int r4) {
            View r0 = getChildAt(r3);
            if (r0 == null) goto L7;
            detachViewAt(r3);
            attachView(r0, r4);
            return;
        L7:
            throw new IllegalArgumentException("Cannot move a child from non-existing index:" + r3 + this.mRecyclerView.toString());
        }

        public void offsetChildrenHorizontal(int r1) {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 == null) goto L6;
            r0.offsetChildrenHorizontal(r1);
            return;
        }

        public void offsetChildrenVertical(int r1) {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 == null) goto L6;
            r0.offsetChildrenVertical(r1);
            return;
        }

        public void onAdapterChanged(Adapter r1, Adapter r2) {
        }

        public boolean onAddFocusables(RecyclerView r1, ArrayList<View> r2, int r3, int r4) {
            return false;
        }

        public void onAttachedToWindow(RecyclerView r1) {
        }

        @Deprecated
        public void onDetachedFromWindow(RecyclerView r1) {
        }

        public void onDetachedFromWindow(RecyclerView r1, Recycler r2) {
            onDetachedFromWindow(r1);
        }

        public abstract View onFocusSearchFailed(View r1, int r2, Recycler r3, State r4);

        public void onInitializeAccessibilityEvent(AccessibilityEvent r3) {
            RecyclerView r0 = this.mRecyclerView;
            onInitializeAccessibilityEvent(r0.mRecycler, r0.mState, r3);
        }

        public void onInitializeAccessibilityEvent(Recycler r2, State r3, AccessibilityEvent r4) {
            RecyclerView r22 = this.mRecyclerView;
            if (r22 == null) goto L20;
            if (r4 == null) goto L22;
            boolean r32 = true;
            if (r22.canScrollVertically(1) == false) goto L9;
        L16:
            r4.setScrollable(r32);
            Adapter r1 = this.mRecyclerView.mAdapter;
            if (r1 == null) goto L21;
            r4.setItemCount(r1.getItemCount());
            return;
        L21:
            return;
        L9:
            if (this.mRecyclerView.canScrollVertically(-1) == true) goto L16;
            if (this.mRecyclerView.canScrollHorizontally(-1) == true) goto L16;
            if (this.mRecyclerView.canScrollHorizontally(1) == true) goto L16;
            r32 = false;
            goto L16
        L22:
            return;
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat r3) {
            RecyclerView r0 = this.mRecyclerView;
            onInitializeAccessibilityNodeInfo(r0.mRecycler, r0.mState, r3);
        }

        public void onInitializeAccessibilityNodeInfo(Recycler r4, State r5, AccessibilityNodeInfoCompat r6) {
            if (this.mRecyclerView.canScrollVertically(-1) == false) goto L5;
        L6:
            r6.addAction(8192);
            r6.setScrollable(true);
        L8:
            if (this.mRecyclerView.canScrollVertically(1) == false) goto L10;
        L11:
            r6.addAction(4096);
            r6.setScrollable(true);
        L12:
            r6.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(r4, r5), getColumnCountForAccessibility(r4, r5), isLayoutHierarchical(r4, r5), getSelectionModeForAccessibility(r4, r5)));
            return;
        L10:
            if (this.mRecyclerView.canScrollHorizontally(1) == false) goto L12;
        L5:
            if (this.mRecyclerView.canScrollHorizontally(-1) == false) goto L8;
            goto L6
        }

        public void onInitializeAccessibilityNodeInfoForItem(View r3, AccessibilityNodeInfoCompat r4) {
            ViewHolder r0 = RecyclerView.getChildViewHolderInt(r3);
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isRemoved() == false) goto L7;
            return;
        L7:
            if (this.mChildHelper.isHidden(r0.itemView) == true) goto L12;
            RecyclerView r02 = this.mRecyclerView;
            onInitializeAccessibilityNodeInfoForItem(r02.mRecycler, r02.mState, r3, r4);
            return;
        }

        public void onInitializeAccessibilityNodeInfoForItem(Recycler r7, State r8, View r9, AccessibilityNodeInfoCompat r10) {
            int r82 = 0;
            if (canScrollVertically() == false) goto L5;
            int r0 = getPosition(r9);
        L7:
            if (canScrollHorizontally() == false) goto L9;
            r82 = getPosition(r9);
        L9:
            r10.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(r0, 1, r82, 1, false, false));
            return;
        L5:
            r0 = 0;
            goto L7
        }

        public View onInterceptFocusSearch(View r1, int r2) {
            return null;
        }

        public void onItemsAdded(RecyclerView r1, int r2, int r3) {
        }

        public void onItemsChanged(RecyclerView r1) {
        }

        public void onItemsMoved(RecyclerView r1, int r2, int r3, int r4) {
        }

        public void onItemsRemoved(RecyclerView r1, int r2, int r3) {
        }

        public void onItemsUpdated(RecyclerView r1, int r2, int r3) {
        }

        public void onItemsUpdated(RecyclerView r1, int r2, int r3, Object r4) {
            onItemsUpdated(r1, r2, r3);
        }

        public abstract void onLayoutChildren(Recycler r1, State r2);

        public void onLayoutCompleted(State r1) {
        }

        public void onMeasure(Recycler r1, State r2, int r3, int r4) {
            this.mRecyclerView.defaultOnMeasure(r3, r4);
        }

        @Deprecated
        public boolean onRequestChildFocus(RecyclerView r1, View r2, View r3) {
            if (isSmoothScrolling() == false) goto L5;
            return true;
        L5:
            if (r1.isComputingLayout() == true) goto L11;
            return false;
        L11:
            return true;
        }

        public boolean onRequestChildFocus(RecyclerView r1, State r2, View r3, View r4) {
            return onRequestChildFocus(r1, r3, r4);
        }

        public void onRestoreInstanceState(Parcelable r1) {
        }

        public Parcelable onSaveInstanceState() {
            return null;
        }

        public void onScrollStateChanged(int r1) {
        }

        public void onSmoothScrollerStopped(SmoothScroller r2) {
            if (this.mSmoothScroller != r2) goto L6;
            this.mSmoothScroller = null;
            return;
        }

        public boolean performAccessibilityAction(int r3, Bundle r4) {
            RecyclerView r0 = this.mRecyclerView;
            return performAccessibilityAction(r0.mRecycler, r0.mState, r3, r4);
        }

        public boolean performAccessibilityAction(Recycler r2, State r3, int r4, Bundle r5) {
            RecyclerView r22 = this.mRecyclerView;
            if (r22 != null) goto L6;
            return false;
        L6:
            if (r4 == 4096) goto L19;
            if (r4 == 8192) goto L11;
            int r23 = 0;
            int r42 = 0;
        L25:
            if (r23 != 0) goto L28;
            if (r42 != 0) goto L28;
            return false;
        L28:
            this.mRecyclerView.smoothScrollBy(r42, r23);
            return true;
        L11:
            if (r22.canScrollVertically(-1) == false) goto L13;
            r23 = -((getHeight() - getPaddingTop()) - getPaddingBottom());
        L15:
            if (this.mRecyclerView.canScrollHorizontally(-1) == false) goto L17;
            r42 = -((getWidth() - getPaddingLeft()) - getPaddingRight());
        L17:
            r42 = 0;
            goto L25
        L13:
            r23 = 0;
            goto L15
        L19:
            if (r22.canScrollVertically(1) == false) goto L21;
            r23 = (getHeight() - getPaddingTop()) - getPaddingBottom();
        L23:
            if (this.mRecyclerView.canScrollHorizontally(1) == false) goto L17;
            r42 = (getWidth() - getPaddingLeft()) - getPaddingRight();
            goto L25
        L21:
            r23 = 0;
            goto L23
        }

        public boolean performAccessibilityActionForItem(View r8, int r9, Bundle r10) {
            RecyclerView r0 = this.mRecyclerView;
            return performAccessibilityActionForItem(r0.mRecycler, r0.mState, r8, r9, r10);
        }

        public boolean performAccessibilityActionForItem(Recycler r1, State r2, View r3, int r4, Bundle r5) {
            return false;
        }

        public void postOnAnimation(Runnable r1) {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 == null) goto L6;
            ViewCompat.postOnAnimation(r0, r1);
            return;
        }

        public void removeAllViews() {
            int r0 = getChildCount() - 1;
        L3:
            if (r0 < 0) goto L5;
            this.mChildHelper.removeViewAt(r0);
            r0 = r0 - 1;
            goto L3
        }

        public void removeAndRecycleAllViews(Recycler r3) {
            int r0 = getChildCount() - 1;
        L3:
            if (r0 < 0) goto L8;
            if (RecyclerView.getChildViewHolderInt(getChildAt(r0)).shouldIgnore() == true) goto L7;
            removeAndRecycleViewAt(r0, r3);
        L7:
            r0 = r0 - 1;
            goto L3
        }

        public void removeAndRecycleScrapInt(Recycler r7) {
            int r0 = r7.getScrapCount();
            int r1 = r0 - 1;
        L3:
            if (r1 < 0) goto L15;
            View r2 = r7.getScrapViewAt(r1);
            ViewHolder r3 = RecyclerView.getChildViewHolderInt(r2);
            if (r3.shouldIgnore() == true) goto L14;
            r3.setIsRecyclable(false);
            if (r3.isTmpDetached() == false) goto L10;
            this.mRecyclerView.removeDetachedView(r2, false);
        L10:
            ItemAnimator r4 = this.mRecyclerView.mItemAnimator;
            if (r4 == null) goto L13;
            r4.endAnimation(r3);
        L13:
            r3.setIsRecyclable(true);
            r7.quickRecycleScrapView(r2);
        L14:
            r1 = r1 - 1;
            goto L3
        L15:
            r7.clearScrap();
            if (r0 <= 0) goto L22;
            this.mRecyclerView.invalidate();
            return;
        }

        public void removeAndRecycleView(View r1, Recycler r2) {
            removeView(r1);
            r2.recycleView(r1);
        }

        public void removeAndRecycleViewAt(int r3, Recycler r4) {
            View r0 = getChildAt(r3);
            if (RecyclerView.getChildViewHolderInt(r0).shouldIgnore() == false) goto L5;
            return;
        L5:
            removeViewAt(r3);
            r4.recycleView(r0);
        }

        public boolean removeCallbacks(Runnable r1) {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 != null) goto L5;
            return false;
        L5:
            return r0.removeCallbacks(r1);
        }

        public void removeDetachedView(View r2) {
            this.mRecyclerView.removeDetachedView(r2, false);
        }

        public void removeView(View r1) {
            this.mChildHelper.removeView(r1);
        }

        public void removeViewAt(int r2) {
            if (getChildAt(r2) == null) goto L6;
            this.mChildHelper.removeViewAt(r2);
            return;
        }

        public boolean requestChildRectangleOnScreen(RecyclerView r7, View r8, Rect r9, boolean r10) {
            return requestChildRectangleOnScreen(r7, r8, r9, r10, false);
        }

        public boolean requestChildRectangleOnScreen(RecyclerView r3, View r4, Rect r5, boolean r6, boolean r7) {
            int[] r42 = getChildRectangleOnScreenScrollAmount(r4, r5);
            int r0 = r42[0];
            int r43 = r42[1];
            if (r7 == true) goto L5;
        L6:
            if (r0 != 0) goto L10;
            if (r43 != 0) goto L10;
        L9:
            return false;
        L10:
            if (r6 == false) goto L12;
            r3.scrollBy(r0, r43);
        L13:
            return true;
        L12:
            r3.smoothScrollBy(r0, r43);
            goto L13
        L5:
            if (isFocusedChildVisibleAfterScrolling(r3, r0, r43) == false) goto L9;
            goto L6
        }

        public void requestLayout() {
            RecyclerView r0 = this.mRecyclerView;
            if (r0 == null) goto L6;
            r0.requestLayout();
            return;
        }

        public void requestSimpleAnimationsInNextLayout() {
            this.mRequestedSimpleAnimations = true;
        }

        public abstract int scrollHorizontallyBy(int r1, Recycler r2, State r3);

        public abstract void scrollToPosition(int r1);

        public abstract int scrollVerticallyBy(int r1, Recycler r2, State r3);

        @Deprecated
        public void setAutoMeasureEnabled(boolean r1) {
            this.mAutoMeasure = r1;
        }

        public void setExactMeasureSpecsFrom(RecyclerView r3) {
            setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(r3.getWidth(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r3.getHeight(), TLObject.FLAG_30));
        }

        public final void setItemPrefetchEnabled(boolean r2) {
            if (r2 == this.mItemPrefetchEnabled) goto L8;
            this.mItemPrefetchEnabled = r2;
            this.mPrefetchMaxCountObserved = 0;
            RecyclerView r1 = this.mRecyclerView;
            if (r1 == null) goto L9;
            r1.mRecycler.updateViewCacheSize();
            return;
        L9:
            return;
        }

        public void setMeasureSpecs(int r2, int r3) {
            this.mWidth = View.MeasureSpec.getSize(r2);
            int r22 = View.MeasureSpec.getMode(r2);
            this.mWidthMode = r22;
            if (r22 == 0) goto L5;
        L7:
            this.mHeight = View.MeasureSpec.getSize(r3);
            int r23 = View.MeasureSpec.getMode(r3);
            this.mHeightMode = r23;
            if (r23 == 0) goto L10;
            return;
        L10:
            if (RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC == true) goto L14;
            this.mHeight = 0;
            return;
        L14:
            return;
        L5:
            if (RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC == true) goto L7;
            this.mWidth = 0;
            goto L7
        }

        public void setMeasuredDimension(int r1, int r2) {
            RecyclerView.access$300(this.mRecyclerView, r1, r2);
        }

        public void setMeasuredDimension(Rect r3, int r4, int r5) {
            int r0 = (r3.width() + getPaddingLeft()) + getPaddingRight();
            int r32 = (r3.height() + getPaddingTop()) + getPaddingBottom();
            setMeasuredDimension(chooseSize(r4, r0, getMinimumWidth()), chooseSize(r5, r32, getMinimumHeight()));
        }

        public void setMeasuredDimensionFromChildren(int r9, int r10) {
            int r0 = getChildCount();
            if (r0 != 0) goto L6;
            this.mRecyclerView.defaultOnMeasure(r9, r10);
            return;
        L6:
            int r1 = Integer.MIN_VALUE;
            int r4 = Integer.MAX_VALUE;
            int r5 = 0;
            int r2 = Integer.MIN_VALUE;
            int r3 = Integer.MAX_VALUE;
        L7:
            if (r5 >= r0) goto L21;
            View r6 = getChildAt(r5);
            Rect r7 = this.mRecyclerView.mTempRect;
            getDecoratedBoundsWithMargins(r6, r7);
            int r62 = r7.left;
            if (r62 >= r3) goto L11;
            r3 = r62;
        L11:
            int r63 = r7.right;
            if (r63 <= r1) goto L14;
            r1 = r63;
        L14:
            int r64 = r7.top;
            if (r64 >= r4) goto L17;
            r4 = r64;
        L17:
            int r65 = r7.bottom;
            if (r65 <= r2) goto L20;
            r2 = r65;
        L20:
            r5 = r5 + 1;
            goto L7
        L21:
            this.mRecyclerView.mTempRect.set(r3, r4, r1, r2);
            setMeasuredDimension(this.mRecyclerView.mTempRect, r9, r10);
        }

        public void setMeasurementCacheEnabled(boolean r1) {
            this.mMeasurementCacheEnabled = r1;
        }

        public void setRecyclerView(RecyclerView r2) {
            if (r2 != null) goto L4;
            this.mRecyclerView = null;
            this.mChildHelper = null;
            this.mWidth = 0;
            this.mHeight = 0;
        L5:
            this.mWidthMode = TLObject.FLAG_30;
            this.mHeightMode = TLObject.FLAG_30;
            return;
        L4:
            this.mRecyclerView = r2;
            this.mChildHelper = r2.mChildHelper;
            this.mWidth = r2.getWidth();
            this.mHeight = r2.getHeight();
            goto L5
        }

        public boolean shouldMeasureChild(View r2, int r3, int r4, LayoutParams r5) {
            if (r2.isLayoutRequested() == false) goto L5;
            return true;
        L5:
            if (this.mMeasurementCacheEnabled == true) goto L7;
            return true;
        L7:
            if (isMeasurementUpToDate(r2.getWidth(), r3, ((ViewGroup.MarginLayoutParams) r5).width) == true) goto L9;
            return true;
        L9:
            if (isMeasurementUpToDate(r2.getHeight(), r4, ((ViewGroup.MarginLayoutParams) r5).height) == false) goto L17;
            return false;
        L17:
            return true;
        }

        public boolean shouldMeasureTwice() {
            return false;
        }

        public boolean shouldReMeasureChild(View r2, int r3, int r4, LayoutParams r5) {
            if (this.mMeasurementCacheEnabled == true) goto L5;
            return true;
        L5:
            if (isMeasurementUpToDate(r2.getMeasuredWidth(), r3, ((ViewGroup.MarginLayoutParams) r5).width) == true) goto L7;
            return true;
        L7:
            if (isMeasurementUpToDate(r2.getMeasuredHeight(), r4, ((ViewGroup.MarginLayoutParams) r5).height) == false) goto L14;
            return false;
        L14:
            return true;
        }

        public abstract void smoothScrollToPosition(RecyclerView r1, State r2, int r3);

        public void startSmoothScroll(SmoothScroller r2) {
            SmoothScroller r0 = this.mSmoothScroller;
            if (r0 == null) goto L8;
            if (r2 == r0) goto L8;
            if (r0.isRunning() == false) goto L8;
            this.mSmoothScroller.stop();
        L8:
            this.mSmoothScroller = r2;
            r2.start(this.mRecyclerView, this);
        }

        public void stopIgnoringView(View r1) {
            ViewHolder r0 = RecyclerView.getChildViewHolderInt(r1);
            r0.stopIgnoring();
            r0.resetInternal();
            r0.addFlags(4);
        }

        public void stopSmoothScroller() {
            SmoothScroller r0 = this.mSmoothScroller;
            if (r0 == null) goto L6;
            r0.stop();
            return;
        }

        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public final Rect mDecorInsets;
        boolean mInsetsDirty;
        boolean mPendingInvalidate;
        public ViewHolder mViewHolder;

        public LayoutParams(int r1, int r2) {
            super(r1, r2);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(Context r1, AttributeSet r2) {
            super(r1, r2);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(ViewGroup.LayoutParams r1) {
            super(r1);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams r1) {
            super(r1);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(LayoutParams r1) {
            super(r1);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public int getViewAdapterPosition() {
            ViewHolder r0 = this.mViewHolder;
            if (r0 != null) goto L7;
            return -1;
        L7:
            return r0.getAdapterPosition();
        }

        public int getViewLayoutPosition() {
            ViewHolder r0 = this.mViewHolder;
            if (r0 != null) goto L7;
            return -1;
        L7:
            return r0.getLayoutPosition();
        }

        public boolean isItemChanged() {
            return this.mViewHolder.isUpdated();
        }

        public boolean isItemRemoved() {
            return this.mViewHolder.isRemoved();
        }

        public boolean isViewInvalid() {
            return this.mViewHolder.isInvalid();
        }
    }

    public interface OnChildAttachStateChangeListener {
        void onChildViewAttachedToWindow(View r1);

        void onChildViewDetachedFromWindow(View r1);
    }

    /* loaded from: classes4.dex */
    public static abstract class OnFlingListener {
        public OnFlingListener() {
        }

        public abstract boolean onFling(int r1, int r2);
    }

    public interface OnItemTouchListener {
        boolean onInterceptTouchEvent(RecyclerView r1, MotionEvent r2);

        void onRequestDisallowInterceptTouchEvent(boolean r1);

        void onTouchEvent(RecyclerView r1, MotionEvent r2);
    }

    public static abstract class OnScrollListener {
        public OnScrollListener() {
        }

        public void onScrollStateChanged(RecyclerView r1, int r2) {
        }

        public void onScrolled(RecyclerView r1, int r2, int r3) {
        }
    }

    public static class RecycledViewPool {
        private int mAttachCount;
        SparseArray<ScrapData> mScrap;

        public static class ScrapData {
            long mBindRunningAverageNs;
            long mCreateRunningAverageNs;
            int mMaxScrap;
            final ArrayList<ViewHolder> mScrapHeap;

            public ScrapData() {
                this.mScrapHeap = new ArrayList();
                this.mMaxScrap = 20;
                this.mCreateRunningAverageNs = 0;
                this.mBindRunningAverageNs = 0;
            }
        }

        public RecycledViewPool() {
            this.mScrap = new SparseArray();
            this.mAttachCount = 0;
        }

        private ScrapData getScrapDataForType(int r2) {
            ScrapData r0 = this.mScrap.get(r2);
            if (r0 != null) goto L6;
            ScrapData r02 = new ScrapData();
            this.mScrap.put(r2, r02);
            return r02;
        L6:
            return r0;
        }

        public void attach() {
            this.mAttachCount++;
        }

        public void clear() {
            int r0 = 0;
        L4:
            if (r0 >= this.mScrap.size()) goto L6;
            this.mScrap.valueAt(r0).mScrapHeap.clear();
            r0 = r0 + 1;
            goto L4
        }

        public void detach() {
            this.mAttachCount--;
        }

        public void factorInBindTime(int r3, long r4) {
            ScrapData r32 = getScrapDataForType(r3);
            r32.mBindRunningAverageNs = runningAverage(r32.mBindRunningAverageNs, r4);
        }

        public void factorInCreateTime(int r3, long r4) {
            ScrapData r32 = getScrapDataForType(r3);
            r32.mCreateRunningAverageNs = runningAverage(r32.mCreateRunningAverageNs, r4);
        }

        public ViewHolder getRecycledView(int r2) {
            ScrapData r1 = this.mScrap.get(r2);
            if (r1 != null) goto L5;
            return null;
        L5:
            if (r1.mScrapHeap.isEmpty() == true) goto L17;
            ArrayList<ViewHolder> r12 = r1.mScrapHeap;
            int r22 = r12.size() - 1;
        L7:
            if (r22 < 0) goto L18;
            if (r12.get(r22).isAttachedToTransitionOverlay() == false) goto L11;
            r22 = r22 - 1;
            goto L7
        L11:
            return r12.remove(r22);
        L18:
            return null;
        L17:
            return null;
        }

        public void onAdapterChanged(Adapter r1, Adapter r2, boolean r3) {
            if (r1 == null) goto L4;
            detach();
        L4:
            if (r3 == false) goto L6;
        L8:
            if (r2 == null) goto L11;
            attach();
            return;
        L11:
            return;
        L6:
            if (this.mAttachCount != 0) goto L8;
            clear();
            goto L8
        }

        public void putRecycledView(ViewHolder r3) {
            int r0 = r3.getItemViewType();
            ArrayList<ViewHolder> r1 = getScrapDataForType(r0).mScrapHeap;
            if (this.mScrap.get(r0).mMaxScrap > r1.size()) goto L5;
            return;
        L5:
            r3.resetInternal();
            r1.add(r3);
        }

        public long runningAverage(long r5, long r7) {
            if (r5 != 0) goto L6;
            return r7;
        L6:
            return ((r5 / 4) * 3) + (r7 / 4);
        }

        public boolean willBindInTime(int r3, long r4, long r6) {
            long r2 = getScrapDataForType(r3).mBindRunningAverageNs;
            if (r2 != 0) goto L5;
            return true;
        L5:
            if ((r4 + r2) < r6) goto L11;
            return false;
        L11:
            return true;
        }

        public boolean willCreateInTime(int r3, long r4, long r6) {
            long r2 = getScrapDataForType(r3).mCreateRunningAverageNs;
            if (r2 != 0) goto L5;
            return true;
        L5:
            if ((r4 + r2) < r6) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public final class Recycler {
        final ArrayList<ViewHolder> mAttachedScrap;
        final ArrayList<ViewHolder> mCachedViews;
        ArrayList<ViewHolder> mChangedScrap;
        RecycledViewPool mRecyclerPool;
        private int mRequestedCacheMax;
        private final List<ViewHolder> mUnmodifiableAttachedScrap;
        int mViewCacheMax;
        final /* synthetic */ RecyclerView this$0;

        public Recycler(RecyclerView r2) {
            this.this$0 = r2;
            ArrayList<ViewHolder> r22 = new ArrayList();
            this.mAttachedScrap = r22;
            this.mChangedScrap = null;
            this.mCachedViews = new ArrayList();
            this.mUnmodifiableAttachedScrap = Collections.unmodifiableList(r22);
            this.mRequestedCacheMax = 2;
            this.mViewCacheMax = 2;
        }

        private void attachAccessibilityDelegateOnBind(ViewHolder r4) {
            if (this.this$0.isAccessibilityEnabled() == false) goto L13;
            View r0 = r4.itemView;
            if (ViewCompat.getImportantForAccessibility(r0) != 0) goto L7;
            ViewCompat.setImportantForAccessibility(r0, 1);
        L7:
            AccessibilityDelegateCompat r1 = ViewCompat.getAccessibilityDelegate(r0);
            if (r1 != null) goto L10;
        L11:
            r4.addFlags(16384);
            ViewCompat.setAccessibilityDelegate(r0, this.this$0.mAccessibilityDelegate.getItemDelegate());
            return;
        L10:
            if (r1.getClass().equals(AccessibilityDelegateCompat.class) == true) goto L11;
            return;
        }

        private boolean tryBindViewHolderByDeadline(ViewHolder r8, int r9, int r10, long r11) {
            r8.mOwnerRecyclerView = this.this$0;
            int r2 = r8.getItemViewType();
            long r3 = this.this$0.getNanoTime();
            if (r11 != Long.MAX_VALUE) goto L5;
        L8:
            this.this$0.mAdapter.bindViewHolder(r8, r9);
            long r112 = this.this$0.getNanoTime();
            this.mRecyclerPool.factorInBindTime(r8.getItemViewType(), r112 - r3);
            attachAccessibilityDelegateOnBind(r8);
            if (this.this$0.mState.isPreLayout() == false) goto L13;
            r8.mPreLayoutPosition = r10;
            return true;
        L13:
            return true;
        L5:
            if (this.mRecyclerPool.willBindInTime(r2, r3, r11) == true) goto L8;
            return false;
        }

        public void addViewHolderToRecycledViewPool(ViewHolder r4, boolean r5) {
            RecyclerView.clearNestedRecyclerViewIfNotNested(r4);
            if (r4.hasAnyOfTheFlags(16384) == false) goto L5;
            r4.setFlags(0, 16384);
            ViewCompat.setAccessibilityDelegate(r4.itemView, null);
        L5:
            if (r5 == false) goto L7;
            dispatchViewRecycled(r4);
        L7:
            r4.mOwnerRecyclerView = null;
            getRecycledViewPool().putRecycledView(r4);
        }

        public void clear() {
            this.mAttachedScrap.clear();
            recycleAndClearCachedViews();
        }

        public void clearOldPositions() {
            int r0 = this.mCachedViews.size();
            int r1 = 0;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L5;
            this.mCachedViews.get(r2).clearOldPosition();
            r2 = r2 + 1;
            goto L3
        L5:
            int r02 = this.mAttachedScrap.size();
            int r22 = 0;
        L6:
            if (r22 >= r02) goto L8;
            this.mAttachedScrap.get(r22).clearOldPosition();
            r22 = r22 + 1;
            goto L6
        L8:
            ArrayList<ViewHolder> r03 = this.mChangedScrap;
            if (r03 == null) goto L13;
            int r04 = r03.size();
        L11:
            if (r1 >= r04) goto L17;
            this.mChangedScrap.get(r1).clearOldPosition();
            r1 = r1 + 1;
            goto L11
        L17:
            return;
        }

        public void clearScrap() {
            this.mAttachedScrap.clear();
            ArrayList<ViewHolder> r1 = this.mChangedScrap;
            if (r1 == null) goto L6;
            r1.clear();
            return;
        }

        public int convertPreLayoutPositionToPostLayout(int r5) {
            if (r5 < 0) goto L11;
            if (r5 >= this.this$0.mState.getItemCount()) goto L11;
            if (this.this$0.mState.isPreLayout() == true) goto L9;
            return r5;
        L9:
            return this.this$0.mAdapterHelper.findPositionOffset(r5);
        L11:
            throw new IndexOutOfBoundsException("invalid position " + r5 + ". State item count is " + this.this$0.mState.getItemCount() + this.this$0.exceptionLabel());
        }

        public void dispatchViewRecycled(ViewHolder r2) {
            this.this$0.getClass();
            Adapter r0 = this.this$0.mAdapter;
            if (r0 == null) goto L5;
            r0.onViewRecycled(r2);
        L5:
            RecyclerView r1 = this.this$0;
            if (r1.mState == null) goto L9;
            r1.mViewInfoStore.removeViewHolder(r2);
            return;
        }

        public ViewHolder getChangedScrapViewForPosition(int r10) {
            ArrayList<ViewHolder> r0 = this.mChangedScrap;
            if (r0 == null) goto L32;
            int r02 = r0.size();
            if (r02 == 0) goto L32;
            int r2 = 0;
            int r3 = 0;
        L9:
            if (r3 >= r02) goto L18;
            ViewHolder r5 = this.mChangedScrap.get(r3);
            if (r5.wasReturnedFromScrap() == true) goto L16;
            if (r5.getLayoutPosition() != r10) goto L16;
            r5.addFlags(32);
            return r5;
        L16:
            r3 = r3 + 1;
            goto L9
        L18:
            if (this.this$0.mAdapter.hasStableIds() == false) goto L32;
            int r102 = this.this$0.mAdapterHelper.findPositionOffset(r10);
            if (r102 <= 0) goto L32;
            if (r102 >= this.this$0.mAdapter.getItemCount()) goto L32;
            long r52 = this.this$0.mAdapter.getItemId(r102);
        L24:
            if (r2 >= r02) goto L32;
            ViewHolder r103 = this.mChangedScrap.get(r2);
            if (r103.wasReturnedFromScrap() == true) goto L31;
            if (r103.getItemId() != r52) goto L31;
            r103.addFlags(32);
            return r103;
        L31:
            r2 = r2 + 1;
        L32:
            return null;
        }

        public RecycledViewPool getRecycledViewPool() {
            if (this.mRecyclerPool != null) goto L6;
            this.mRecyclerPool = new RecycledViewPool();
        L6:
            return this.mRecyclerPool;
        }

        public int getScrapCount() {
            return this.mAttachedScrap.size();
        }

        public List<ViewHolder> getScrapList() {
            return this.mUnmodifiableAttachedScrap;
        }

        public ViewHolder getScrapOrCachedViewForId(long r6, int r8, boolean r9) {
            int r0 = this.mAttachedScrap.size() - 1;
        L3:
            if (r0 < 0) goto L19;
            ViewHolder r1 = this.mAttachedScrap.get(r0);
            if (r1.getItemId() != r6) goto L18;
            if (r1.wasReturnedFromScrap() == true) goto L18;
            if (r8 == r1.getItemViewType()) goto L10;
            if (r9 == true) goto L18;
            this.mAttachedScrap.remove(r0);
            this.this$0.removeDetachedView(r1.itemView, false);
            quickRecycleScrapView(r1.itemView);
            goto L18
        L10:
            r1.addFlags(32);
            if (r1.isRemoved() == true) goto L13;
        L15:
            return r1;
        L13:
            if (this.this$0.mState.isPreLayout() == true) goto L15;
            r1.setFlags(2, 14);
        L18:
            r0 = r0 - 1;
            goto L3
        L19:
            int r02 = this.mCachedViews.size();
        L20:
            r02 = r02 - 1;
            if (r02 < 0) goto L35;
            ViewHolder r2 = this.mCachedViews.get(r02);
            if (r2.getItemId() != r6) goto L20;
            if (r2.isAttachedToTransitionOverlay() == true) goto L20;
            if (r8 == r2.getItemViewType()) goto L28;
            if (r9 == true) goto L20;
            recycleCachedViewAt(r02);
            return null;
        L28:
            if (r9 == true) goto L30;
            this.mCachedViews.remove(r02);
        L30:
            return r2;
        L35:
            return null;
        }

        public ViewHolder getScrapOrHiddenOrCachedHolderForPosition(int r6, boolean r7) {
            int r0 = this.mAttachedScrap.size();
            int r1 = 0;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L18;
            ViewHolder r3 = this.mAttachedScrap.get(r2);
            if (r3.wasReturnedFromScrap() == true) goto L16;
            if (r3.getLayoutPosition() != r6) goto L16;
            if (r3.isInvalid() == true) goto L16;
            if (this.this$0.mState.mInPreLayout == true) goto L14;
            if (r3.isRemoved() == true) goto L16;
        L14:
            r3.addFlags(32);
            return r3;
        L16:
            r2 = r2 + 1;
            goto L3
        L18:
            if (r7 == true) goto L27;
            View r22 = this.this$0.mChildHelper.findHiddenNonRemovedView(r6);
            if (r22 == null) goto L27;
            ViewHolder r62 = RecyclerView.getChildViewHolderInt(r22);
            this.this$0.mChildHelper.unhide(r22);
            int r72 = this.this$0.mChildHelper.indexOfChild(r22);
            if (r72 == (-1)) goto L25;
            this.this$0.mChildHelper.detachViewFromParent(r72);
            scrapView(r22);
            r62.addFlags(8224);
            return r62;
        L25:
            StringBuilder r73 = new StringBuilder("layout index should not be -1 after unhiding a view:");
            r73.append(r62);
            RecyclerView$$ExternalSyntheticBUOutline0.m(r73, this.this$0.exceptionLabel());
            return null;
        L27:
            int r23 = this.mCachedViews.size();
        L28:
            if (r1 >= r23) goto L39;
            ViewHolder r32 = this.mCachedViews.get(r1);
            if (r32.isInvalid() == true) goto L38;
            if (r32.getLayoutPosition() != r6) goto L38;
            if (r32.isAttachedToTransitionOverlay() == true) goto L38;
            if (r7 == true) goto L37;
            this.mCachedViews.remove(r1);
        L37:
            return r32;
        L38:
            r1 = r1 + 1;
            goto L28
        L39:
            return null;
        }

        public View getScrapViewAt(int r1) {
            return this.mAttachedScrap.get(r1).itemView;
        }

        public View getViewForPosition(int r2) {
            return getViewForPosition(r2, false);
        }

        public View getViewForPosition(int r3, boolean r4) {
            return tryGetViewHolderForPositionByDeadline(r3, r4, Long.MAX_VALUE).itemView;
        }

        public void markItemDecorInsetsDirty() {
            int r0 = this.mCachedViews.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L8;
            LayoutParams r2 = (LayoutParams) this.mCachedViews.get(r1).itemView.getLayoutParams();
            if (r2 == null) goto L7;
            r2.mInsetsDirty = true;
        L7:
            r1 = r1 + 1;
            goto L3
        }

        public void markKnownViewsInvalid() {
            int r0 = this.mCachedViews.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L8;
            ViewHolder r2 = this.mCachedViews.get(r1);
            if (r2 == null) goto L7;
            r2.addFlags(6);
            r2.addChangePayload(null);
        L7:
            r1 = r1 + 1;
            goto L3
        L8:
            Adapter r02 = this.this$0.mAdapter;
            if (r02 != null) goto L11;
        L14:
            recycleAndClearCachedViews();
            return;
        L11:
            if (r02.hasStableIds() == false) goto L14;
        }

        public void offsetPositionRecordsForInsert(int r5, int r6) {
            int r0 = this.mCachedViews.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L10;
            ViewHolder r2 = this.mCachedViews.get(r1);
            if (r2 == null) goto L9;
            if (r2.mPosition < r5) goto L9;
            r2.offsetPosition(r6, true);
        L9:
            r1 = r1 + 1;
            goto L3
        }

        public void offsetPositionRecordsForMove(int r9, int r10) {
            if (r9 >= r10) goto L4;
            int r0 = -1;
            int r1 = r9;
            int r2 = r10;
        L5:
            int r3 = this.mCachedViews.size();
            int r5 = 0;
        L6:
            if (r5 >= r3) goto L17;
            ViewHolder r6 = this.mCachedViews.get(r5);
            if (r6 == null) goto L16;
            int r7 = r6.mPosition;
            if (r7 < r1) goto L16;
            if (r7 > r2) goto L16;
            if (r7 != r9) goto L15;
            r6.offsetPosition(r10 - r9, false);
            goto L16
        L15:
            r6.offsetPosition(r0, false);
        L16:
            r5 = r5 + 1;
            goto L6
        L17:
            return;
        L4:
            r0 = 1;
            r2 = r9;
            r1 = r10;
            goto L5
        }

        public void offsetPositionRecordsForRemove(int r5, int r6, boolean r7) {
            int r0 = r5 + r6;
            int r1 = this.mCachedViews.size() - 1;
        L3:
            if (r1 < 0) goto L12;
            ViewHolder r2 = this.mCachedViews.get(r1);
            if (r2 == null) goto L11;
            int r3 = r2.mPosition;
            if (r3 < r0) goto L9;
            r2.offsetPosition(-r6, r7);
            goto L11
        L9:
            if (r3 < r5) goto L11;
            r2.addFlags(8);
            recycleCachedViewAt(r1);
        L11:
            r1 = r1 - 1;
            goto L3
        }

        public void onAdapterChanged(Adapter r1, Adapter r2, boolean r3) {
            clear();
            getRecycledViewPool().onAdapterChanged(r1, r2, r3);
        }

        public void quickRecycleScrapView(View r2) {
            ViewHolder r22 = RecyclerView.getChildViewHolderInt(r2);
            r22.mScrapContainer = null;
            r22.mInChangeScrap = false;
            r22.clearReturnedFromScrapFlag();
            recycleViewHolderInternal(r22);
        }

        public void recycleAndClearCachedViews() {
            int r0 = this.mCachedViews.size() - 1;
        L3:
            if (r0 < 0) goto L5;
            recycleCachedViewAt(r0);
            r0 = r0 - 1;
            goto L3
        L5:
            this.mCachedViews.clear();
            this.this$0.mPrefetchRegistry.clearPrefetchPositions();
        }

        public void recycleCachedViewAt(int r3) {
            addViewHolderToRecycledViewPool(this.mCachedViews.get(r3), true);
            this.mCachedViews.remove(r3);
        }

        public void recycleView(View r4) {
            ViewHolder r0 = RecyclerView.getChildViewHolderInt(r4);
            if (r0.isTmpDetached() == false) goto L6;
            this.this$0.removeDetachedView(r4, false);
        L6:
            if (r0.isScrap() == false) goto L9;
            r0.unScrap();
        L11:
            recycleViewHolderInternal(r0);
            if (this.this$0.mItemAnimator != null) goto L14;
            return;
        L14:
            if (r0.isRecyclable() == true) goto L18;
            this.this$0.mItemAnimator.endAnimation(r0);
            return;
        L18:
            return;
        L9:
            if (r0.wasReturnedFromScrap() == false) goto L11;
            r0.clearReturnedFromScrapFlag();
            goto L11
        }

        public void recycleViewHolderInternal(ViewHolder r7) {
            boolean r1 = false;
            boolean r2 = true;
            if (r7.isScrap() == false) goto L5;
        L57:
            StringBuilder r3 = new StringBuilder("Scrapped or attached views may not be recycled. isScrap:");
            r3.append(r7.isScrap());
            r3.append(" isAttached:");
            if (r7.itemView.getParent() == null) goto L60;
            r1 = true;
        L60:
            r3.append(r1);
            r3.append(this.this$0.exceptionLabel());
            throw new IllegalArgumentException(r3.toString());
        L5:
            if (r7.itemView.getParent() != null) goto L57;
            if (r7.isTmpDetached() == true) goto L56;
            if (r7.shouldIgnore() == true) goto L53;
            boolean r0 = r7.doesTransientStatePreventRecycling();
            Adapter r32 = this.this$0.mAdapter;
            if (r32 == null) goto L17;
            if (r0 == false) goto L17;
            if (r32.onFailedToRecycleView(r7) == false) goto L17;
            boolean r33 = true;
        L18:
            if (r33 == true) goto L24;
            if (r7.isRecyclable() == true) goto L24;
            r2 = false;
        L47:
            this.this$0.mViewInfoStore.removeViewHolder(r7);
            if (r1 == true) goto L64;
            if (r2 == true) goto L65;
            if (r0 == false) goto L66;
            r7.mOwnerRecyclerView = null;
            return;
        L66:
            return;
        L65:
            return;
        L64:
            return;
        L24:
            if (this.mViewCacheMax > 0) goto L26;
        L42:
            boolean r34 = false;
        L43:
            if (r34 == true) goto L46;
            addViewHolderToRecycledViewPool(r7, true);
        L45:
            r1 = r34;
            goto L47
        L46:
            r2 = false;
            goto L45
        L26:
            if (r7.hasAnyOfTheFlags(526) == true) goto L42;
            int r35 = this.mCachedViews.size();
            if (r35 < this.mViewCacheMax) goto L31;
            if (r35 <= 0) goto L31;
            recycleCachedViewAt(0);
            r35 = r35 - 1;
        L31:
            if (r35 > 0) goto L33;
        L41:
            this.mCachedViews.add(r35, r7);
            r34 = true;
            goto L43
        L33:
            if (this.this$0.mPrefetchRegistry.lastPrefetchIncludedPosition(r7.mPosition) == true) goto L41;
            int r36 = r35 - 1;
        L35:
            if (r36 < 0) goto L40;
            if (this.this$0.mPrefetchRegistry.lastPrefetchIncludedPosition(this.mCachedViews.get(r36).mPosition) == false) goto L40;
            r36 = r36 - 1;
        L40:
            r35 = r36 + 1;
        L17:
            r33 = false;
            goto L18
        L53:
            Buffer$$ExternalSyntheticBUOutline4.m("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.", this.this$0.exceptionLabel());
            return;
        L56:
            throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + r7 + this.this$0.exceptionLabel());
        }

        public void scrapView(View r2) {
            ViewHolder r22 = RecyclerView.getChildViewHolderInt(r2);
            if (r22.hasAnyOfTheFlags(12) == true) goto L15;
            if (r22.isUpdated() == false) goto L15;
            if (this.this$0.canReuseUpdatedViewHolder(r22) == true) goto L15;
            if (this.mChangedScrap != null) goto L12;
            this.mChangedScrap = new ArrayList();
        L12:
            r22.setScrapContainer(this, true);
            this.mChangedScrap.add(r22);
            return;
        L15:
            if (r22.isInvalid() == true) goto L17;
        L23:
            r22.setScrapContainer(this, false);
            this.mAttachedScrap.add(r22);
            return;
        L17:
            if (r22.isRemoved() == true) goto L23;
            if (this.this$0.mAdapter.hasStableIds() == true) goto L23;
            Buffer$$ExternalSyntheticBUOutline4.m("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.", this.this$0.exceptionLabel());
        }

        public void setRecycledViewPool(RecycledViewPool r2) {
            RecycledViewPool r0 = this.mRecyclerPool;
            if (r0 == null) goto L5;
            r0.detach();
        L5:
            this.mRecyclerPool = r2;
            if (r2 != null) goto L8;
            return;
        L8:
            if (this.this$0.getAdapter() == null) goto L12;
            this.mRecyclerPool.attach();
            return;
        }

        public void setViewCacheExtension(ViewCacheExtension r1) {
        }

        public void setViewCacheSize(int r1) {
            this.mRequestedCacheMax = r1;
            updateViewCacheSize();
        }

        public ViewHolder tryGetViewHolderForPositionByDeadline(int r17, boolean r18, long r19) {
            if (r17 < 0) goto L93;
            if (r17 >= this.this$0.mState.getItemCount()) goto L93;
            boolean r6 = true;
            if (this.this$0.mState.isPreLayout() == false) goto L11;
            ViewHolder r2 = getChangedScrapViewForPosition(r17);
            if (r2 == null) goto L12;
            boolean r5 = true;
        L13:
            if (r2 != null) goto L28;
            r2 = getScrapOrHiddenOrCachedHolderForPosition(r17, r18);
            if (r2 == null) goto L28;
            if (validateViewHolderForOffsetPosition(r2) == true) goto L27;
            if (r18 == true) goto L26;
            r2.addFlags(4);
            if (r2.isScrap() == false) goto L23;
            this.this$0.removeDetachedView(r2.itemView, false);
            r2.unScrap();
        L25:
            recycleViewHolderInternal(r2);
            goto L26
        L23:
            if (r2.wasReturnedFromScrap() == false) goto L25;
            r2.clearReturnedFromScrapFlag();
        L26:
            r2 = null;
            goto L28
        L27:
            r5 = true;
        L28:
            if (r2 != null) goto L53;
            int r8 = this.this$0.mAdapterHelper.findPositionOffset(r17);
            if (r8 < 0) goto L55;
            if (r8 >= this.this$0.mAdapter.getItemCount()) goto L55;
            int r11 = this.this$0.mAdapter.getItemViewType(r8);
            if (this.this$0.mAdapter.hasStableIds() == false) goto L38;
            r2 = getScrapOrCachedViewForId(this.this$0.mAdapter.getItemId(r8), r11, r18);
            if (r2 == null) goto L38;
            r2.mPosition = r8;
            r5 = true;
        L38:
            if (r2 != null) goto L43;
            ViewHolder r1 = getRecycledViewPool().getRecycledView(r11);
            if (r1 == null) goto L42;
            r1.resetInternal();
        L42:
            r2 = r1;
        L43:
            if (r2 != null) goto L53;
            long r12 = this.this$0.getNanoTime();
            if (r19 != Long.MAX_VALUE) goto L47;
        L49:
            RecyclerView r13 = this.this$0;
            r2 = r13.mAdapter.createViewHolder(r13, r11);
            RecyclerView r14 = RecyclerView.findNestedRecyclerView(r2.itemView);
            if (r14 == null) goto L52;
            r2.mNestedRecyclerView = new WeakReference(r14);
        L52:
            this.mRecyclerPool.factorInCreateTime(r11, this.this$0.getNanoTime() - r12);
            goto L53
        L47:
            if (this.mRecyclerPool.willCreateInTime(r11, r12, r19) == true) goto L49;
            return null;
        L55:
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + r17 + "(offset:" + r8 + ").state:" + this.this$0.mState.getItemCount() + this.this$0.exceptionLabel());
        L53:
            ViewHolder r15 = r2;
            boolean r82 = r5;
            if (r82 == false) goto L65;
            if (this.this$0.mState.isPreLayout() == true) goto L65;
            if (r15.hasAnyOfTheFlags(8192) == false) goto L65;
            r15.setFlags(0, 8192);
            if (this.this$0.mState.mRunSimpleAnimations == false) goto L65;
            int r22 = ItemAnimator.buildAdapterChangeFlagsForAnimations(r15) | 4096;
            RecyclerView r4 = this.this$0;
            this.this$0.recordAnimationInfoIfBouncedHiddenView(r15, r4.mItemAnimator.recordPreLayoutInformation(r4.mState, r15, r22, r15.getUnmodifiedPayloads()));
        L65:
            if (this.this$0.mState.isPreLayout() == false) goto L70;
            if (r15.isBound() == false) goto L70;
            r15.mPreLayoutPosition = r17;
        L76:
            boolean r23 = false;
        L78:
            ViewGroup.LayoutParams r3 = r15.itemView.getLayoutParams();
            RecyclerView r42 = this.this$0;
            if (r3 != null) goto L82;
            LayoutParams r0 = (LayoutParams) r42.generateDefaultLayoutParams();
            r15.itemView.setLayoutParams(r0);
        L85:
            r0.mViewHolder = r15;
            if (r82 == false) goto L89;
            if (r23 == false) goto L89;
        L90:
            r0.mPendingInvalidate = r6;
            return r15;
        L89:
            r6 = false;
            goto L90
        L82:
            if (r42.checkLayoutParams(r3) == true) goto L84;
            r0 = (LayoutParams) this.this$0.generateLayoutParams(r3);
            r15.itemView.setLayoutParams(r0);
            goto L85
        L84:
            r0 = (LayoutParams) r3;
        L70:
            if (r15.isBound() == true) goto L72;
        L77:
            r23 = tryBindViewHolderByDeadline(r15, this.this$0.mAdapterHelper.findPositionOffset(r17), r17, r19);
            goto L78
        L72:
            if (r15.needsUpdate() == true) goto L77;
            if (r15.isInvalid() == false) goto L76;
        L12:
            r5 = false;
            goto L13
        L11:
            r2 = null;
        L93:
            throw new IndexOutOfBoundsException("Invalid item position " + r17 + "(" + r17 + "). Item count:" + this.this$0.mState.getItemCount() + this.this$0.exceptionLabel());
        }

        public void unscrapView(ViewHolder r2) {
            if (r2.mInChangeScrap == false) goto L5;
            this.mChangedScrap.remove(r2);
        L6:
            r2.mScrapContainer = null;
            r2.mInChangeScrap = false;
            r2.clearReturnedFromScrapFlag();
            return;
        L5:
            this.mAttachedScrap.remove(r2);
            goto L6
        }

        public void updateViewCacheSize() {
            LayoutManager r0 = this.this$0.mLayout;
            if (r0 == null) goto L5;
            int r02 = r0.mPrefetchMaxCountObserved;
        L6:
            this.mViewCacheMax = this.mRequestedCacheMax + r02;
            int r03 = this.mCachedViews.size() - 1;
        L7:
            if (r03 < 0) goto L11;
            if (this.mCachedViews.size() <= this.mViewCacheMax) goto L14;
            recycleCachedViewAt(r03);
            r03 = r03 - 1;
            goto L7
        L14:
            return;
        L11:
            return;
        L5:
            r02 = 0;
            goto L6
        }

        public boolean validateViewHolderForOffsetPosition(ViewHolder r6) {
            if (r6.isRemoved() == true) goto L5;
            int r0 = r6.mPosition;
            if (r0 < 0) goto L23;
            if (r0 >= this.this$0.mAdapter.getItemCount()) goto L23;
            if (this.this$0.mState.isPreLayout() == true) goto L16;
            if (this.this$0.mAdapter.getItemViewType(r6.mPosition) == r6.getItemViewType()) goto L16;
            return false;
        L16:
            if (this.this$0.mAdapter.hasStableIds() == true) goto L18;
            return true;
        L18:
            if (r6.getItemId() != this.this$0.mAdapter.getItemId(r6.mPosition)) goto L20;
            return true;
        L20:
            return false;
        L23:
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + r6 + this.this$0.exceptionLabel());
        L5:
            return this.this$0.mState.isPreLayout();
        }

        public void viewRangeUpdate(int r4, int r5) {
            int r52 = r5 + r4;
            int r0 = this.mCachedViews.size() - 1;
        L3:
            if (r0 < 0) goto L12;
            ViewHolder r1 = this.mCachedViews.get(r0);
            if (r1 == null) goto L11;
            int r2 = r1.mPosition;
            if (r2 < r4) goto L11;
            if (r2 >= r52) goto L11;
            r1.addFlags(2);
            recycleCachedViewAt(r0);
        L11:
            r0 = r0 - 1;
            goto L3
        }
    }

    /* loaded from: classes4.dex */
    public interface RecyclerListener {
    }

    public class RecyclerViewDataObserver extends AdapterDataObserver {
        final /* synthetic */ RecyclerView this$0;

        public RecyclerViewDataObserver(RecyclerView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            this.this$0.assertNotInLayoutOrScroll(null);
            RecyclerView r0 = this.this$0;
            r0.mState.mStructureChanged = true;
            if (BuildVars.DEBUG_VERSION == false) goto L5;
            r0.mAdapterHelper.logNotify("notifyDataSetChanged()");
        L5:
            this.this$0.processDataSetCompletelyChanged(true);
            if (this.this$0.mAdapterHelper.hasPendingUpdates() == true) goto L9;
            this.this$0.requestLayout();
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int r3, int r4, Object r5) {
            this.this$0.assertNotInLayoutOrScroll(null);
            if (this.this$0.mAdapterHelper.onItemRangeChanged(r3, r4, r5) == false) goto L6;
            triggerUpdateProcessor();
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int r3, int r4) {
            this.this$0.assertNotInLayoutOrScroll(null);
            if (this.this$0.mAdapterHelper.onItemRangeInserted(r3, r4) == false) goto L6;
            triggerUpdateProcessor();
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int r3, int r4, int r5) {
            this.this$0.assertNotInLayoutOrScroll(null);
            if (this.this$0.mAdapterHelper.onItemRangeMoved(r3, r4, r5) == false) goto L6;
            triggerUpdateProcessor();
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int r3, int r4) {
            this.this$0.assertNotInLayoutOrScroll(null);
            if (this.this$0.mAdapterHelper.onItemRangeRemoved(r3, r4) == false) goto L6;
            triggerUpdateProcessor();
            return;
        }

        public void triggerUpdateProcessor() {
            RecyclerView r1 = this.this$0;
            if (r1.mHasFixedSize == true) goto L5;
        L8:
            r1.mAdapterUpdateDuringMeasure = true;
            r1.requestLayout();
            return;
        L5:
            if (r1.mIsAttached == false) goto L8;
            ViewCompat.postOnAnimation(r1, r1.mUpdateChildViewsRunnable);
        }
    }

    /* loaded from: classes4.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        Parcelable mLayoutState;

        /* renamed from: androidx.recyclerview.widget.RecyclerView$SavedState$1, reason: invalid class name */
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

        public SavedState(Parcel r1, ClassLoader r2) {
            super(r1, r2);
            if (r2 != null) goto L6;
            r2 = LayoutManager.class.getClassLoader();
        L6:
            this.mLayoutState = r1.readParcelable(r2);
        }

        public SavedState(Parcelable r1) {
            super(r1);
        }

        public void copyFrom(SavedState r1) {
            this.mLayoutState = r1.mLayoutState;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            super.writeToParcel(r1, r2);
            r1.writeParcelable(this.mLayoutState, 0);
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class SmoothScroller {
        private LayoutManager mLayoutManager;
        private boolean mPendingInitialRun;
        private RecyclerView mRecyclerView;
        private final Action mRecyclingAction;
        private boolean mRunning;
        private boolean mStarted;
        private int mTargetPosition;
        private View mTargetView;

        public static class Action {
            private boolean mChanged;
            private int mConsecutiveUpdates;
            private int mDuration;
            private int mDx;
            private int mDy;
            private Interpolator mInterpolator;
            private int mJumpToPosition;

            public Action(int r3, int r4) {
                this(r3, r4, Integer.MIN_VALUE, null);
            }

            public Action(int r2, int r3, int r4, Interpolator r5) {
                this.mJumpToPosition = -1;
                this.mChanged = false;
                this.mConsecutiveUpdates = 0;
                this.mDx = r2;
                this.mDy = r3;
                this.mDuration = r4;
                this.mInterpolator = r5;
            }

            private void validate() {
                if (this.mInterpolator == null) goto L10;
                if (this.mDuration >= 1) goto L10;
                Segment$$ExternalSyntheticBUOutline1.m("If you provide an interpolator, you must set a positive duration");
                return;
            L10:
                if (this.mDuration < 1) goto L12;
                return;
            L12:
                Segment$$ExternalSyntheticBUOutline1.m("Scroll duration must be a positive number");
            }

            public boolean hasJumpTarget() {
                if (this.mJumpToPosition < 0) goto L6;
                return true;
            L6:
                return false;
            }

            public void jumpTo(int r1) {
                this.mJumpToPosition = r1;
            }

            public void runIfNecessary(RecyclerView r6) {
                int r0 = this.mJumpToPosition;
                if (r0 < 0) goto L7;
                this.mJumpToPosition = -1;
                r6.jumpToPositionForSmoothScroller(r0);
                this.mChanged = false;
                return;
            L7:
                if (this.mChanged == false) goto L13;
                validate();
                r6.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration, this.mInterpolator);
                int r62 = this.mConsecutiveUpdates + 1;
                this.mConsecutiveUpdates = r62;
                if (r62 <= 10) goto L11;
                Log.e(RecyclerView.TAG, "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
            L11:
                this.mChanged = false;
                return;
            L13:
                this.mConsecutiveUpdates = 0;
            }

            public void update(int r1, int r2, int r3, Interpolator r4) {
                this.mDx = r1;
                this.mDy = r2;
                this.mDuration = r3;
                this.mInterpolator = r4;
                this.mChanged = true;
            }
        }

        /* loaded from: classes.dex */
        public interface ScrollVectorProvider {
            PointF computeScrollVectorForPosition(int r1);
        }

        public SmoothScroller() {
            this.mTargetPosition = -1;
            this.mRecyclingAction = new Action(0, 0);
        }

        public PointF computeScrollVectorForPosition(int r2) {
            Object r1 = getLayoutManager();
            if ((r1 instanceof ScrollVectorProvider) == true) goto L5;
            Log.w(RecyclerView.TAG, "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + ScrollVectorProvider.class.getCanonicalName());
            return null;
        L5:
            return ((ScrollVectorProvider) r1).computeScrollVectorForPosition(r2);
        }

        public View findViewByPosition(int r1) {
            return this.mRecyclerView.mLayout.findViewByPosition(r1);
        }

        public int getChildCount() {
            return this.mRecyclerView.mLayout.getChildCount();
        }

        public int getChildPosition(View r1) {
            return this.mRecyclerView.getChildLayoutPosition(r1);
        }

        public LayoutManager getLayoutManager() {
            return this.mLayoutManager;
        }

        public int getTargetPosition() {
            return this.mTargetPosition;
        }

        @Deprecated
        public void instantScrollToPosition(int r1) {
            this.mRecyclerView.scrollToPosition(r1);
        }

        public boolean isPendingInitialRun() {
            return this.mPendingInitialRun;
        }

        public boolean isRunning() {
            return this.mRunning;
        }

        public void normalize(PointF r3) {
            float r2 = r3.x;
            float r0 = r3.y;
            float r22 = (float) Math.sqrt((r2 * r2) + (r0 * r0));
            r3.x /= r22;
            r3.y /= r22;
        }

        public void onAnimation(int r7, int r8) {
            RecyclerView r0 = this.mRecyclerView;
            if (this.mTargetPosition == (-1)) goto L5;
            if (r0 == null) goto L5;
        L7:
            if (this.mPendingInitialRun == true) goto L9;
        L19:
            this.mPendingInitialRun = false;
            View r1 = this.mTargetView;
            if (r1 == null) goto L26;
            if (getChildPosition(r1) != this.mTargetPosition) goto L24;
            onTargetFound(this.mTargetView, r0.mState, this.mRecyclingAction);
            this.mRecyclingAction.runIfNecessary(r0);
            stop();
            goto L26
        L24:
            Log.e(RecyclerView.TAG, "Passed over target position while smooth scrolling.");
            this.mTargetView = null;
        L26:
            if (this.mRunning == false) goto L33;
            onSeekTargetStep(r7, r8, r0.mState, this.mRecyclingAction);
            boolean r72 = this.mRecyclingAction.hasJumpTarget();
            this.mRecyclingAction.runIfNecessary(r0);
            if (r72 == true) goto L30;
            return;
        L30:
            if (this.mRunning == false) goto L35;
            this.mPendingInitialRun = true;
            r0.mViewFlinger.postOnAnimation();
            return;
        L35:
            return;
        L33:
            return;
        L9:
            if (this.mTargetView != null) goto L19;
            if (this.mLayoutManager == null) goto L19;
            PointF r12 = computeScrollVectorForPosition(this.mTargetPosition);
            if (r12 == null) goto L19;
            float r3 = r12.x;
            if (r3 == 0.0f) goto L17;
        L18:
            r0.scrollStep((int) Math.signum(r3), (int) Math.signum(r12.y), null);
            goto L19
        L17:
            if (r12.y == 0.0f) goto L19;
        L5:
            stop();
            goto L7
        }

        public void onChildAttachedToWindow(View r3) {
            if (getChildPosition(r3) != getTargetPosition()) goto L6;
            this.mTargetView = r3;
            return;
        }

        public abstract void onSeekTargetStep(int r1, int r2, State r3, Action r4);

        public abstract void onStart();

        public abstract void onStop();

        public abstract void onTargetFound(View r1, State r2, Action r3);

        public void setTargetPosition(int r1) {
            this.mTargetPosition = r1;
        }

        public void start(RecyclerView r4, LayoutManager r5) {
            r4.canStopFlinger = true;
            r4.mViewFlinger.stop();
            if (this.mStarted == false) goto L5;
            Log.w(RecyclerView.TAG, "An instance of " + getClass().getSimpleName() + " was started more than once. Each instance of" + getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
        L5:
            this.mRecyclerView = r4;
            this.mLayoutManager = r5;
            int r52 = this.mTargetPosition;
            if (r52 == (-1)) goto L9;
            r4.mState.mTargetPosition = r52;
            this.mRunning = true;
            this.mPendingInitialRun = true;
            this.mTargetView = findViewByPosition(getTargetPosition());
            onStart();
            this.mRecyclerView.mViewFlinger.postOnAnimation();
            this.mStarted = true;
            return;
        L9:
            f$$ExternalSyntheticBUOutline1.m("Invalid target position");
        }

        public final void stop() {
            if (this.mRunning == true) goto L5;
            return;
        L5:
            this.mRunning = false;
            onStop();
            this.mRecyclerView.mState.mTargetPosition = -1;
            this.mTargetView = null;
            this.mTargetPosition = -1;
            this.mPendingInitialRun = false;
            this.mLayoutManager.onSmoothScrollerStopped(this);
            this.mLayoutManager = null;
            this.mRecyclerView = null;
        }
    }

    public static class State {
        private SparseArray<Object> mData;
        int mDeletedInvisibleItemCountSincePreviousLayout;
        long mFocusedItemId;
        int mFocusedItemPosition;
        int mFocusedSubChildId;
        boolean mInPreLayout;
        boolean mIsMeasuring;
        int mItemCount;
        int mLayoutStep;
        int mPreviousLayoutItemCount;
        int mRemainingScrollHorizontal;
        int mRemainingScrollVertical;
        boolean mRunPredictiveAnimations;
        boolean mRunSimpleAnimations;
        boolean mStructureChanged;
        int mTargetPosition;
        boolean mTrackOldChangeHolders;

        public State() {
            this.mTargetPosition = -1;
            this.mPreviousLayoutItemCount = 0;
            this.mDeletedInvisibleItemCountSincePreviousLayout = 0;
            this.mLayoutStep = 1;
            this.mItemCount = 0;
            this.mStructureChanged = false;
            this.mInPreLayout = false;
            this.mTrackOldChangeHolders = false;
            this.mIsMeasuring = false;
            this.mRunSimpleAnimations = false;
            this.mRunPredictiveAnimations = false;
        }

        public void assertLayoutStep(int r3) {
            if ((this.mLayoutStep & r3) == 0) goto L5;
            return;
        L5:
            WebMessageCompat$$ExternalSyntheticBUOutline0.m("Layout state should be one of ", Integer.toBinaryString(r3), " but it is ", Integer.toBinaryString(this.mLayoutStep));
        }

        public int getItemCount() {
            if (this.mInPreLayout == false) goto L7;
            return this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout;
        L7:
            return this.mItemCount;
        }

        public boolean hasTargetScrollPosition() {
            if (this.mTargetPosition == (-1)) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isPreLayout() {
            return this.mInPreLayout;
        }

        public void prepareForNestedPrefetch(Adapter r2) {
            this.mLayoutStep = 1;
            this.mItemCount = r2.getItemCount();
            this.mInPreLayout = false;
            this.mTrackOldChangeHolders = false;
            this.mIsMeasuring = false;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.mTargetPosition + ", mData=" + this.mData + ", mItemCount=" + this.mItemCount + ", mIsMeasuring=" + this.mIsMeasuring + ", mPreviousLayoutItemCount=" + this.mPreviousLayoutItemCount + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.mDeletedInvisibleItemCountSincePreviousLayout + ", mStructureChanged=" + this.mStructureChanged + ", mInPreLayout=" + this.mInPreLayout + ", mRunSimpleAnimations=" + this.mRunSimpleAnimations + ", mRunPredictiveAnimations=" + this.mRunPredictiveAnimations + '}';
        }

        public boolean willRunPredictiveAnimations() {
            return this.mRunPredictiveAnimations;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class ViewCacheExtension {
    }

    public class ViewFlinger implements Runnable {
        private boolean mEatRunOnAnimationRequest;
        Interpolator mInterpolator;
        private int mLastFlingX;
        private int mLastFlingY;
        OverScroller mOverScroller;
        private boolean mReSchedulePostAnimationCallback;
        final /* synthetic */ RecyclerView this$0;

        public ViewFlinger(RecyclerView r3) {
            this.this$0 = r3;
            Interpolator r0 = RecyclerView.sQuinticInterpolator;
            this.mInterpolator = r0;
            this.mEatRunOnAnimationRequest = false;
            this.mReSchedulePostAnimationCallback = false;
            this.mOverScroller = new OverScroller(r3.getContext(), r0);
        }

        private int computeScrollDuration(int r5, int r6, int r7, int r8) {
            int r0 = Math.abs(r5);
            int r1 = Math.abs(r6);
            if (r0 <= r1) goto L5;
            boolean r2 = true;
        L6:
            int r72 = (int) Math.sqrt((r7 * r7) + (r8 * r8));
            int r52 = (int) Math.sqrt((r5 * r5) + (r6 * r6));
            RecyclerView r62 = this.this$0;
            if (r2 == false) goto L9;
            int r63 = r62.getWidth();
        L10:
            int r82 = r63 / 2;
            float r64 = r63;
            float r83 = r82;
            float r84 = r83 + (distanceInfluenceForSnapDuration(Math.min(1.0f, (r52 * 1.0f) / r64)) * r83);
            if (r72 <= 0) goto L13;
            int r4 = Math.round(Math.abs(r84 / r72) * 1000.0f) * 4;
        L18:
            return Math.min(r4, 2000);
        L13:
            if (r2 == true) goto L16;
            r0 = r1;
        L16:
            r4 = (int) (((r0 / r64) + 1.0f) * 300.0f);
            goto L18
        L9:
            r63 = r62.getHeight();
            goto L10
        L5:
            r2 = false;
            goto L6
        }

        private float distanceInfluenceForSnapDuration(float r1) {
            return (float) Math.sin((r1 - 0.5f) * 0.47123894f);
        }

        private void internalPostOnAnimation() {
            this.this$0.removeCallbacks(this);
            ViewCompat.postOnAnimation(this.this$0, this);
        }

        public void fling(int r13, int r14) {
            this.this$0.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            Interpolator r0 = this.mInterpolator;
            Interpolator r1 = RecyclerView.sQuinticInterpolator;
            if (r0 == r1) goto L5;
            this.mInterpolator = r1;
            this.mOverScroller = new OverScroller(this.this$0.getContext(), r1);
        L5:
            this.mOverScroller.fling(0, 0, r13, r14, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            postOnAnimation();
        }

        public void postOnAnimation() {
            if (this.mEatRunOnAnimationRequest == false) goto L6;
            this.mReSchedulePostAnimationCallback = true;
            return;
        L6:
            internalPostOnAnimation();
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView r1 = this.this$0;
            if (r1.mLayout != null) goto L6;
            r1.canStopFlinger = true;
            stop();
            return;
        L6:
            this.mReSchedulePostAnimationCallback = false;
            this.mEatRunOnAnimationRequest = true;
            r1.consumePendingUpdateOperations();
            OverScroller r12 = this.mOverScroller;
            RecyclerView.m940$$Nest$fputisFlingerWorking(this.this$0, true);
            if (r12.computeScrollOffset() == false) goto L83;
            int r4 = r12.getCurrX();
            int r5 = r12.getCurrY();
            int r8 = r4 - this.mLastFlingX;
            int r9 = r5 - this.mLastFlingY;
            this.mLastFlingX = r4;
            this.mLastFlingY = r5;
            RecyclerView r7 = this.this$0;
            int[] r10 = r7.mReusableIntPair;
            r10[0] = 0;
            r10[1] = 0;
            if (r7.dispatchNestedPreScroll(r8, r9, r10, null, 1) == false) goto L12;
            int[] r42 = this.this$0.mReusableIntPair;
            r8 = r8 - r42[0];
            r9 = r9 - r42[1];
        L12:
            if (this.this$0.getOverScrollMode() == 2) goto L14;
            this.this$0.considerReleasingGlowsOnScroll(r8, r9);
        L14:
            RecyclerView r43 = this.this$0;
            if (r43.mAdapter == null) goto L31;
            int[] r6 = r43.mReusableIntPair;
            r6[0] = 0;
            r6[1] = 0;
            r43.scrollStep(r8, r9, r6);
            RecyclerView r44 = this.this$0;
            int[] r62 = r44.mReusableIntPair;
            int r72 = r62[0];
            int r63 = r62[1];
            r8 = r8 - r72;
            r9 = r9 - r63;
            SmoothScroller r45 = r44.mLayout.mSmoothScroller;
            if (r45 != null) goto L19;
        L29:
            int r13 = r63;
            int r122 = r72;
        L30:
            int r14 = r8;
            int r15 = r9;
            if (this.this$0.mItemDecorations.isEmpty() == true) goto L35;
            this.this$0.invalidate();
        L35:
            RecyclerView r11 = this.this$0;
            int[] r46 = r11.mReusableIntPair;
            r46[0] = 0;
            r46[1] = 0;
            r11.dispatchNestedScroll(r122, r13, r14, r15, null, 1, r46);
            RecyclerView r47 = this.this$0;
            int[] r64 = r47.mReusableIntPair;
            int r142 = r14 - r64[0];
            int r152 = r15 - r64[1];
            if (r122 != 0) goto L38;
            if (r13 != 0) goto L38;
        L40:
            if (RecyclerView.access$200(this.this$0) == true) goto L43;
            this.this$0.invalidate();
        L43:
            if (r12.getCurrX() != r12.getFinalX()) goto L45;
            boolean r48 = true;
        L47:
            if (r12.getCurrY() != r12.getFinalY()) goto L49;
            boolean r65 = true;
        L51:
            if (r12.isFinished() == true) goto L58;
            if (r48 == true) goto L54;
            if (r142 != 0) goto L54;
        L57:
            boolean r49 = false;
        L59:
            SmoothScroller r66 = this.this$0.mLayout.mSmoothScroller;
            if (r66 != null) goto L62;
        L64:
            if (r49 == true) goto L66;
        L80:
            postOnAnimation();
            RecyclerView r16 = this.this$0;
            GapWorker r410 = r16.mGapWorker;
            if (r410 == null) goto L83;
            r410.postFromTraversal(r16, r122, r13);
            goto L83
        L66:
            if (this.this$0.getOverScrollMode() == 2) goto L79;
            int r17 = (int) r12.getCurrVelocity();
            if (r142 >= 0) goto L70;
            int r411 = -r17;
        L73:
            if (r152 >= 0) goto L75;
            r17 = -r17;
        L78:
            this.this$0.absorbGlows(r411, r17);
            goto L79
        L75:
            if (r152 > 0) goto L78;
            r17 = 0;
            goto L78
        L70:
            if (r142 <= 0) goto L72;
            r411 = r17;
            goto L73
        L72:
            r411 = 0;
        L79:
            this.this$0.mPrefetchRegistry.clearPrefetchPositions();
            goto L83
        L62:
            if (r66.isPendingInitialRun() == false) goto L64;
        L54:
            if (r65 == true) goto L58;
            if (r152 == 0) goto L57;
        L58:
            r49 = true;
            goto L59
        L49:
            r65 = false;
            goto L51
        L45:
            r48 = false;
        L38:
            r47.dispatchOnScrolled(r122, r13);
            goto L40
        L19:
            if (r45.isPendingInitialRun() == true) goto L29;
            if (r45.isRunning() == false) goto L29;
            int r102 = this.this$0.mState.getItemCount();
            if (r102 != 0) goto L26;
            r45.stop();
            goto L29
        L26:
            if (r45.getTargetPosition() < r102) goto L28;
            r45.setTargetPosition(r102 - 1);
            r45.onAnimation(r72, r63);
            goto L29
        L28:
            r45.onAnimation(r72, r63);
            goto L29
        L31:
            r122 = 0;
            r13 = 0;
        L83:
            RecyclerView.m940$$Nest$fputisFlingerWorking(this.this$0, false);
            SmoothScroller r18 = this.this$0.mLayout.mSmoothScroller;
            if (r18 != null) goto L86;
        L88:
            this.mEatRunOnAnimationRequest = false;
            if (this.mReSchedulePostAnimationCallback == false) goto L92;
            internalPostOnAnimation();
            return;
        L92:
            this.this$0.setScrollState(0);
            this.this$0.stopNestedScroll(1);
            return;
        L86:
            if (r18.isPendingInitialRun() == false) goto L88;
            r18.onAnimation(0, 0);
            goto L88
        }

        public void smoothScrollBy(int r9, int r10, int r11, Interpolator r12) {
            if (r11 != Integer.MIN_VALUE) goto L5;
            r11 = computeScrollDuration(r9, r10, 0, 0);
        L5:
            int r7 = r11;
            if (r12 != null) goto L9;
            r12 = RecyclerView.sQuinticInterpolator;
        L9:
            if (this.mInterpolator == r12) goto L11;
            this.mInterpolator = r12;
            this.mOverScroller = new OverScroller(this.this$0.getContext(), r12);
        L11:
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            this.this$0.setScrollState(2);
            this.mOverScroller.startScroll(0, 0, r9, r10, r7);
            postOnAnimation();
        }

        public void stop() {
            RecyclerView r0 = this.this$0;
            if (r0.canStopFlinger == true) goto L5;
            return;
        L5:
            r0.removeCallbacks(this);
            this.mOverScroller.abortAnimation();
        }
    }

    public static abstract class ViewHolder {
        static final int FLAG_ADAPTER_FULLUPDATE = 1024;
        static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
        static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
        static final int FLAG_BOUND = 1;
        static final int FLAG_IGNORE = 128;
        static final int FLAG_INVALID = 4;
        static final int FLAG_MOVED = 2048;
        static final int FLAG_NOT_RECYCLABLE = 16;
        static final int FLAG_REMOVED = 8;
        static final int FLAG_RETURNED_FROM_SCRAP = 32;
        static final int FLAG_SET_A11Y_ITEM_DELEGATE = 16384;
        static final int FLAG_TMP_DETACHED = 256;
        static final int FLAG_UPDATE = 2;
        private static final List<Object> FULLUPDATE_PAYLOADS = null;
        static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
        public final View itemView;
        int mFlags;
        public boolean mInChangeScrap;
        private int mIsRecyclableCount;
        long mItemId;
        int mItemViewType;
        WeakReference<RecyclerView> mNestedRecyclerView;
        public int mOldCompoundPosition;
        public int mOldOldPosition;
        int mOldPosition;
        RecyclerView mOwnerRecyclerView;
        List<Object> mPayloads;
        int mPendingAccessibilityState;
        int mPosition;
        int mPreLayoutPosition;
        Recycler mScrapContainer;
        ViewHolder mShadowedHolder;
        ViewHolder mShadowingHolder;
        List<Object> mUnmodifiedPayloads;
        private int mWasImportantForAccessibilityBeforeHidden;

        static {
            FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
        }

        public ViewHolder(View r4) {
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1;
            this.mItemViewType = -1;
            this.mPreLayoutPosition = -1;
            this.mOldOldPosition = -1;
            this.mOldCompoundPosition = -1;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            this.mPayloads = null;
            this.mUnmodifiedPayloads = null;
            this.mIsRecyclableCount = 0;
            this.mScrapContainer = null;
            this.mInChangeScrap = false;
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            if (r4 == null) goto L6;
            this.itemView = r4;
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("itemView may not be null");
            throw null;
        }

        private void createPayloadsIfNeeded() {
            if (this.mPayloads != null) goto L6;
            ArrayList r0 = new ArrayList();
            this.mPayloads = r0;
            this.mUnmodifiedPayloads = Collections.unmodifiableList(r0);
            return;
        }

        public void addChangePayload(Object r3) {
            if (r3 != null) goto L7;
            addFlags(1024);
            return;
        L7:
            if ((1024 & this.mFlags) != 0) goto L10;
            createPayloadsIfNeeded();
            this.mPayloads.add(r3);
            return;
        }

        public void addFlags(int r2) {
            this.mFlags = r2 | this.mFlags;
        }

        public void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        public void clearPayload() {
            List<Object> r0 = this.mPayloads;
            if (r0 == null) goto L5;
            r0.clear();
        L5:
            this.mFlags &= -1025;
        }

        public void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        public void clearTmpDetachFlag() {
            this.mFlags &= -257;
        }

        public boolean doesTransientStatePreventRecycling() {
            if ((this.mFlags & 16) == 0) goto L5;
            return false;
        L5:
            if (ViewCompat.hasTransientState(this.itemView) == false) goto L10;
            return true;
        L10:
            return false;
        }

        public void flagRemovedAndOffsetPosition(int r2, int r3, boolean r4) {
            addFlags(8);
            offsetPosition(r3, r4);
            this.mPosition = r2;
        }

        public final int getAdapterPosition() {
            RecyclerView r0 = this.mOwnerRecyclerView;
            if (r0 != null) goto L7;
            return -1;
        L7:
            return r0.getAdapterPositionFor(this);
        }

        public final long getItemId() {
            return this.mItemId;
        }

        public final int getItemViewType() {
            return this.mItemViewType;
        }

        public final int getLayoutPosition() {
            int r0 = this.mPreLayoutPosition;
            if (r0 == (-1)) goto L5;
            return r0;
        L5:
            return this.mPosition;
        }

        public final int getOldPosition() {
            return this.mOldPosition;
        }

        public Object getPayload() {
            if (getUnmodifiedPayloads().isEmpty() == false) goto L7;
            return null;
        L7:
            return getUnmodifiedPayloads().get(0);
        }

        @Deprecated
        public final int getPosition() {
            int r0 = this.mPreLayoutPosition;
            if (r0 == (-1)) goto L5;
            return r0;
        L5:
            return this.mPosition;
        }

        public List<Object> getUnmodifiedPayloads() {
            if ((this.mFlags & 1024) != 0) goto L14;
            List<Object> r0 = this.mPayloads;
            if (r0 == null) goto L12;
            if (r0.size() == 0) goto L12;
            return this.mUnmodifiedPayloads;
        L12:
            return FULLUPDATE_PAYLOADS;
        L14:
            return FULLUPDATE_PAYLOADS;
        }

        public boolean hasAnyOfTheFlags(int r1) {
            if ((this.mFlags & r1) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isAdapterPositionUnknown() {
            if ((this.mFlags & 512) == 0) goto L5;
            return true;
        L5:
            if (isInvalid() == true) goto L11;
            return false;
        L11:
            return true;
        }

        public boolean isAttachedToTransitionOverlay() {
            if (this.itemView.getParent() != null) goto L5;
            return false;
        L5:
            if (this.itemView.getParent() == this.mOwnerRecyclerView) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isBound() {
            if ((this.mFlags & 1) == 0) goto L5;
            return true;
        L5:
            return false;
        }

        public boolean isInvalid() {
            if ((this.mFlags & 4) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        public final boolean isRecyclable() {
            if ((this.mFlags & 16) == 0) goto L5;
            return false;
        L5:
            if (ViewCompat.hasTransientState(this.itemView) == true) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isRemoved() {
            if ((this.mFlags & 8) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isScrap() {
            if (this.mScrapContainer == null) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isTmpDetached() {
            if ((this.mFlags & 256) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isUpdated() {
            if ((this.mFlags & 2) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean needsUpdate() {
            if ((this.mFlags & 2) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        public void offsetPosition(int r3, boolean r4) {
            if (this.mOldPosition != (-1)) goto L6;
            this.mOldPosition = this.mPosition;
        L6:
            if (this.mPreLayoutPosition != (-1)) goto L8;
            this.mPreLayoutPosition = this.mPosition;
        L8:
            if (r4 == false) goto L10;
            this.mPreLayoutPosition += r3;
        L10:
            this.mPosition += r3;
            if (this.itemView.getLayoutParams() == null) goto L14;
            ((LayoutParams) this.itemView.getLayoutParams()).mInsetsDirty = true;
            return;
        }

        public void onEnteredHiddenState(RecyclerView r3) {
            int r0 = this.mPendingAccessibilityState;
            if (r0 == (-1)) goto L5;
            this.mWasImportantForAccessibilityBeforeHidden = r0;
        L6:
            r3.setChildImportantForAccessibilityInternal(this, 4);
            return;
        L5:
            this.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(this.itemView);
            goto L6
        }

        public void onLeftHiddenState(RecyclerView r2) {
            r2.setChildImportantForAccessibilityInternal(this, this.mWasImportantForAccessibilityBeforeHidden);
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }

        public void resetInternal() {
            this.mFlags = 0;
            int r1 = this.mPosition;
            if (r1 == (-1)) goto L5;
            this.mOldOldPosition = r1;
        L5:
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.clearNestedRecyclerViewIfNotNested(this);
        }

        public void saveOldPosition() {
            if (this.mOldPosition != (-1)) goto L5;
            this.mOldPosition = this.mPosition;
        L5:
            this.mOldOldPosition = this.mPosition;
        }

        public void setFlags(int r3, int r4) {
            int r32 = r3 & r4;
            this.mFlags = r32 | (this.mFlags & (~r4));
        }

        public final void setIsRecyclable(boolean r3) {
            int r0 = this.mIsRecyclableCount;
            if (r3 == false) goto L5;
            int r02 = r0 - 1;
        L6:
            this.mIsRecyclableCount = r02;
            if (r02 >= 0) goto L14;
            this.mIsRecyclableCount = 0;
            if (BuildVars.DEBUG_VERSION == true) goto L12;
            Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            return;
        L12:
            MVEL$$ExternalSyntheticBUOutline0.m("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ", this);
            return;
        L14:
            if (r3 == true) goto L18;
            if (r02 != 1) goto L18;
            this.mFlags |= 16;
            return;
        L18:
            if (r3 == false) goto L22;
            if (r02 != 0) goto L23;
            this.mFlags &= -17;
            return;
        L23:
            return;
        L22:
            return;
        L5:
            r02 = r0 + 1;
            goto L6
        }

        public void setScrapContainer(Recycler r1, boolean r2) {
            this.mScrapContainer = r1;
            this.mInChangeScrap = r2;
        }

        public boolean shouldBeKeptAsChild() {
            if ((this.mFlags & 16) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean shouldIgnore() {
            if ((this.mFlags & 128) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        public void stopIgnoring() {
            this.mFlags &= -129;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("ViewHolder{" + Integer.toHexString(hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
            if (isScrap() == false) goto L10;
            r0.append(" scrap ");
            if (this.mInChangeScrap == false) goto L7;
            String r1 = "[changeScrap]";
        L8:
            r0.append(r1);
            goto L10
        L7:
            r1 = "[attachedScrap]";
        L10:
            if (isInvalid() == false) goto L13;
            r0.append(" invalid");
        L13:
            if (isBound() == true) goto L16;
            r0.append(" unbound");
        L16:
            if (needsUpdate() == false) goto L19;
            r0.append(" update");
        L19:
            if (isRemoved() == false) goto L22;
            r0.append(" removed");
        L22:
            if (shouldIgnore() == false) goto L25;
            r0.append(" ignored");
        L25:
            if (isTmpDetached() == false) goto L28;
            r0.append(" tmpDetached");
        L28:
            if (isRecyclable() == true) goto L31;
            r0.append(" not recyclable(" + this.mIsRecyclableCount + ")");
        L31:
            if (isAdapterPositionUnknown() == false) goto L34;
            r0.append(" undefined adapter position");
        L34:
            if (this.itemView.getParent() != null) goto L36;
            r0.append(" no parent");
        L36:
            r0.append("}");
            return r0.toString();
        }

        public void unScrap() {
            this.mScrapContainer.unscrapView(this);
        }

        public boolean wasReturnedFromScrap() {
            if ((this.mFlags & 32) == 0) goto L6;
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: -$$Nest$fputisFlingerWorking, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m940$$Nest$fputisFlingerWorking(RecyclerView r0, boolean r1) {
        r0.isFlingerWorking = r1;
    }

    static {
        NESTED_SCROLLING_ATTRS = new int[]{R.attr.nestedScrollingEnabled};
        CLIP_TO_PADDING_ATTR = new int[]{R.attr.clipToPadding};
        ALLOW_SIZE_IN_UNSPECIFIED_SPEC = true;
        Class r1 = Integer.TYPE;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class, r1, r1};
        sQuinticInterpolator = new AnonymousClass3();
    }

    public RecyclerView(Context r2) {
        this(r2, null);
    }

    public RecyclerView(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public RecyclerView(Context r5, AttributeSet r6, int r7) {
        super(r5, r6, r7);
        this.mObserver = new RecyclerViewDataObserver(this);
        this.mRecycler = new Recycler(this);
        this.mViewInfoStore = new ViewInfoStore();
        this.mUpdateChildViewsRunnable = new AnonymousClass1(this);
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mItemDecorations = new ArrayList();
        this.mOnItemTouchListeners = new ArrayList();
        boolean r0 = false;
        this.mInterceptRequestLayoutDepth = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory = new EdgeEffectFactory();
        this.mItemAnimator = new DefaultItemAnimator();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
        this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new ViewFlinger(this);
        this.mPrefetchRegistry = new GapWorker.LayoutPrefetchRegistryImpl();
        this.mState = new State();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new ItemAnimatorRestoreListener(this);
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mNestedOffsets = new int[2];
        this.topGlowOffset = 0;
        this.bottomGlowOffset = 0;
        this.glowColor = null;
        this.mReusableIntPair = new int[2];
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.mItemAnimatorRunner = new AnonymousClass2(this);
        this.mViewInfoProcessCallback = new AnonymousClass4(this);
        this.canStopFlinger = true;
        this.isFlingerWorking = false;
        if (r6 == null) goto L5;
        TypedArray r62 = r5.obtainStyledAttributes(r6, CLIP_TO_PADDING_ATTR, r7, 0);
        this.mClipToPadding = r62.getBoolean(0, true);
        r62.recycle();
    L6:
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration r63 = ViewConfiguration.get(r5);
        this.mTouchSlop = r63.getScaledTouchSlop();
        this.mScaledHorizontalScrollFactor = ViewConfigurationCompat.getScaledHorizontalScrollFactor(r63, r5);
        this.mScaledVerticalScrollFactor = ViewConfigurationCompat.getScaledVerticalScrollFactor(r63, r5);
        this.mMinFlingVelocity = r63.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = r63.getScaledMaximumFlingVelocity();
        if (getOverScrollMode() != 2) goto L9;
        r0 = true;
    L9:
        setWillNotDraw(r0);
        this.mItemAnimator.setListener(this.mItemAnimatorListener);
        initAdapterManager();
        initChildrenHelper();
        initAutofill();
        if (ViewCompat.getImportantForAccessibility(this) != 0) goto L12;
        ViewCompat.setImportantForAccessibility(this, 1);
    L12:
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        setDescendantFocusability(262144);
        setNestedScrollingEnabled(true);
        return;
    L5:
        this.mClipToPadding = true;
        goto L6
    }

    public static /* synthetic */ void access$000(RecyclerView r0, View r1, int r2, ViewGroup.LayoutParams r3) {
        r0.attachViewToParent(r1, r2, r3);
    }

    public static /* synthetic */ void access$100(RecyclerView r0, int r1) {
        r0.detachViewFromParent(r1);
    }

    public static /* synthetic */ boolean access$200(RecyclerView r0) {
        return r0.awakenScrollBars();
    }

    public static /* synthetic */ void access$300(RecyclerView r0, int r1, int r2) {
        r0.setMeasuredDimension(r1, r2);
    }

    private void addAnimatingView(ViewHolder r6) {
        View r0 = r6.itemView;
        if (r0.getParent() != this) goto L5;
        boolean r1 = true;
    L6:
        this.mRecycler.unscrapView(getChildViewHolder(r0));
        if (r6.isTmpDetached() == false) goto L10;
        this.mChildHelper.attachViewToParent(r0, -1, r0.getLayoutParams(), true);
        return;
    L10:
        ChildHelper r5 = this.mChildHelper;
        if (r1 == true) goto L14;
        r5.addView(r0, true);
        return;
    L14:
        r5.hide(r0);
        return;
    L5:
        r1 = false;
        goto L6
    }

    private void animateChange(ViewHolder r2, ViewHolder r3, ItemAnimator.ItemHolderInfo r4, ItemAnimator.ItemHolderInfo r5, boolean r6, boolean r7) {
        r2.setIsRecyclable(false);
        if (r6 == false) goto L5;
        addAnimatingView(r2);
    L5:
        if (r2 == r3) goto L10;
        if (r7 == false) goto L8;
        addAnimatingView(r3);
    L8:
        r2.mShadowedHolder = r3;
        addAnimatingView(r2);
        this.mRecycler.unscrapView(r2);
        r3.setIsRecyclable(false);
        r3.mShadowingHolder = r2;
    L10:
        if (this.mItemAnimator.animateChange(r2, r3, r4, r5) == false) goto L13;
        postAnimationRunner();
        return;
    }

    private void cancelScroll() {
        resetScroll();
        setScrollState(0);
    }

    public static void clearNestedRecyclerViewIfNotNested(ViewHolder r3) {
        WeakReference<RecyclerView> r0 = r3.mNestedRecyclerView;
        if (r0 == null) goto L23;
        RecyclerView r02 = r0.get();
    L6:
        if (r02 == null) goto L14;
        if (r02 == r3.itemView) goto L24;
        Object r03 = r02.getParent();
        if ((r03 instanceof View) == true) goto L12;
        r02 = null;
        goto L6
    L12:
        r02 = (View) r03;
        goto L6
    L24:
        return;
    L14:
        r3.mNestedRecyclerView = null;
        return;
    }

    private boolean didChildRangeChange(int r4, int r5) {
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        int[] r3 = this.mMinMaxLayoutPositions;
        if (r3[0] == r4) goto L5;
    L8:
        return true;
    L5:
        if (r3[1] != r5) goto L8;
        return false;
    }

    private void dispatchContentChangedIfNecessary() {
        int r0 = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if (r0 != 0) goto L5;
        return;
    L5:
        if (isAccessibilityEnabled() == false) goto L9;
        AccessibilityEvent r1 = AccessibilityEvent.obtain();
        r1.setEventType(2048);
        AccessibilityEventCompat.setContentChangeTypes(r1, r0);
        sendAccessibilityEventUnchecked(r1);
        return;
    }

    private void dispatchLayoutStep1() {
        boolean r1 = true;
        this.mState.assertLayoutStep(1);
        fillRemainingScrollValues(this.mState);
        this.mState.mIsMeasuring = false;
        startInterceptRequestLayout();
        this.mViewInfoStore.clear();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        saveFocusInfo();
        State r0 = this.mState;
        if (r0.mRunSimpleAnimations == true) goto L5;
    L7:
        r1 = false;
    L8:
        r0.mTrackOldChangeHolders = r1;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        r0.mInPreLayout = r0.mRunPredictiveAnimations;
        r0.mItemCount = this.mAdapter.getItemCount();
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mState.mRunSimpleAnimations == false) goto L32;
        int r02 = this.mChildHelper.getChildCount();
        int r12 = 0;
    L11:
        if (r12 >= r02) goto L32;
        ViewHolder r3 = getChildViewHolderInt(this.mChildHelper.getChildAt(r12));
        if (r3.shouldIgnore() == true) goto L30;
        if (r3.isInvalid() == true) goto L17;
    L19:
        this.mViewInfoStore.addToPreLayout(r3, this.mItemAnimator.recordPreLayoutInformation(this.mState, r3, ItemAnimator.buildAdapterChangeFlagsForAnimations(r3), r3.getUnmodifiedPayloads()));
        if (this.mState.mTrackOldChangeHolders == false) goto L30;
        if (r3.isUpdated() == false) goto L30;
        if (r3.isRemoved() == true) goto L30;
        if (r3.shouldIgnore() == true) goto L30;
        if (r3.isInvalid() == true) goto L30;
        this.mViewInfoStore.addToOldChangeHolders(getChangedHolderKey(r3), r3);
        goto L30
    L17:
        if (this.mAdapter.hasStableIds() == true) goto L19;
    L30:
        r12 = r12 + 1;
    L32:
        if (this.mState.mRunPredictiveAnimations == false) goto L50;
        saveOldPositions();
        State r03 = this.mState;
        boolean r13 = r03.mStructureChanged;
        r03.mStructureChanged = false;
        this.mLayout.onLayoutChildren(this.mRecycler, r03);
        this.mState.mStructureChanged = r13;
        int r04 = 0;
    L35:
        if (r04 >= this.mChildHelper.getChildCount()) goto L49;
        ViewHolder r14 = getChildViewHolderInt(this.mChildHelper.getChildAt(r04));
        if (r14.shouldIgnore() == true) goto L48;
        if (this.mViewInfoStore.isInPreLayout(r14) == true) goto L48;
        int r32 = ItemAnimator.buildAdapterChangeFlagsForAnimations(r14);
        boolean r4 = r14.hasAnyOfTheFlags(8192);
        if (r4 == true) goto L44;
        r32 = r32 | 4096;
    L44:
        ItemAnimator.ItemHolderInfo r33 = this.mItemAnimator.recordPreLayoutInformation(this.mState, r14, r32, r14.getUnmodifiedPayloads());
        if (r4 == false) goto L47;
        recordAnimationInfoIfBouncedHiddenView(r14, r33);
        goto L48
    L47:
        this.mViewInfoStore.addToAppearedInPreLayoutHolders(r14, r33);
    L48:
        r04 = r04 + 1;
        goto L35
    L49:
        clearOldPositions();
    L51:
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        this.mState.mLayoutStep = 2;
        return;
    L50:
        clearOldPositions();
        goto L51
    L5:
        if (this.mItemsChanged == false) goto L7;
        goto L7
    }

    private void dispatchLayoutStep2() {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        this.mState.assertLayoutStep(6);
        this.mAdapterHelper.consumeUpdatesInOnePass();
        this.mState.mItemCount = this.mAdapter.getItemCount();
        State r0 = this.mState;
        r0.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        r0.mInPreLayout = false;
        this.mLayout.onLayoutChildren(this.mRecycler, r0);
        State r02 = this.mState;
        r02.mStructureChanged = false;
        this.mPendingSavedState = null;
        if (r02.mRunSimpleAnimations == true) goto L5;
    L7:
        boolean r2 = false;
    L8:
        r02.mRunSimpleAnimations = r2;
        r02.mLayoutStep = 4;
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        return;
    L5:
        if (this.mItemAnimator == null) goto L7;
        r2 = true;
        goto L8
    }

    private void dispatchLayoutStep3() {
        this.mState.assertLayoutStep(4);
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        State r0 = this.mState;
        r0.mLayoutStep = 1;
        if (r0.mRunSimpleAnimations == true) goto L57;
        RecyclerView r3 = this;
    L44:
        r3.mLayout.removeAndRecycleScrapInt(r3.mRecycler);
        State r11 = r3.mState;
        r11.mPreviousLayoutItemCount = r11.mItemCount;
        r3.mDataSetHasChangedAfterLayout = false;
        r3.mDispatchItemsChangedEvent = false;
        r11.mRunSimpleAnimations = false;
        r11.mRunPredictiveAnimations = false;
        r3.mLayout.mRequestedSimpleAnimations = false;
        ArrayList<ViewHolder> r112 = r3.mRecycler.mChangedScrap;
        if (r112 == null) goto L47;
        r112.clear();
    L47:
        LayoutManager r113 = r3.mLayout;
        if (r113.mPrefetchMaxObservedInInitialPrefetch == false) goto L50;
        r113.mPrefetchMaxCountObserved = 0;
        r113.mPrefetchMaxObservedInInitialPrefetch = false;
        r3.mRecycler.updateViewCacheSize();
    L50:
        r3.mLayout.onLayoutCompleted(r3.mState);
        r3.onExitLayoutOrScroll();
        r3.stopInterceptRequestLayout(false);
        r3.mViewInfoStore.clear();
        int[] r114 = r3.mMinMaxLayoutPositions;
        if (r3.didChildRangeChange(r114[0], r114[1]) == false) goto L53;
        r3.dispatchOnScrolled(0, 0);
    L53:
        r3.recoverFocusFromState();
        r3.resetFocusInfo();
        return;
    L57:
        int r02 = this.mChildHelper.getChildCount() - 1;     // Catch: Exception -> L19
    L5:
        if (r02 < 0) goto L31;
        ViewHolder r5 = getChildViewHolderInt(this.mChildHelper.getChildAt(r02));     // Catch: Exception -> L19
        if (r5 != null) goto L9;
    L10:
        RecyclerView r32 = this;
    L30:
        r02 = r02 - 1;
        RecyclerView this = r32;
        goto L5
    L9:
        if (r5.shouldIgnore() == true) goto L10;
        long r2 = this.getChangedHolderKey(r5);     // Catch: Exception -> L19
        ItemAnimator.ItemHolderInfo r4 = this.mItemAnimator.recordPostLayoutInformation(this.mState, r5);     // Catch: Exception -> L19
        ViewHolder r6 = this.mViewInfoStore.getFromOldChangeHolders(r2);     // Catch: Exception -> L19
        if (r6 != null) goto L14;
    L29:
        r32 = this;
        r32.mViewInfoStore.addToPostLayout(r5, r4);     // Catch: Exception -> L27
        goto L30
    L14:
        if (r6.shouldIgnore() == true) goto L29;
        boolean r8 = this.mViewInfoStore.isDisappearing(r6);     // Catch: Exception -> L19
        boolean r9 = this.mViewInfoStore.isDisappearing(r5);     // Catch: Exception -> L19
        if (r8 == false) goto L21;
        if (r6 != r5) goto L21;
        this.mViewInfoStore.addToPostLayout(r5, r4);     // Catch: Exception -> L19
    L21:
        ItemAnimator.ItemHolderInfo r7 = this.mViewInfoStore.popFromPreLayout(r6);     // Catch: Exception -> L19
        this.mViewInfoStore.addToPostLayout(r5, r4);     // Catch: Exception -> L19
        ItemAnimator.ItemHolderInfo r42 = this.mViewInfoStore.popFromPostLayout(r5);     // Catch: Exception -> L19
        if (r7 != null) goto L25;
        this.handleMissingPreInfoForChangeError(r2, r5, r6);     // Catch: Exception -> L19
        goto L10
    L25:
        r32 = this;
        r32.animateChange(r6, r5, r7, r42, r8, r9);     // Catch: Exception -> L27
    L27:
        e = e;
    L32:
        StringBuilder r115 = new StringBuilder();
        int r22 = r32.mChildHelper.getChildCount() - 1;
    L33:
        if (r22 < 0) goto L42;
        ViewHolder r1 = getChildViewHolderInt(r32.mChildHelper.getChildAt(r22));
        if (r1 == null) goto L40;
        if (r1.shouldIgnore() == true) goto L40;
        r115.append("Holder at" + r22 + " " + r1 + "\n");
    L40:
        r22 = r22 - 1;
        goto L33
    L42:
        throw new RuntimeException(r115.toString(), e);
    L31:
        r3 = this;
        r3.mViewInfoStore.process(r3.mViewInfoProcessCallback);
    L19:
        e = e;
        r32 = this;
        goto L32
    }

    private boolean dispatchToOnItemTouchListeners(MotionEvent r3) {
        OnItemTouchListener r0 = this.mInterceptingOnItemTouchListener;
        if (r0 == null) goto L5;
        r0.onTouchEvent(this, r3);
        int r32 = r3.getAction();
        if (r32 == 3) goto L13;
        if (r32 == 1) goto L13;
    L14:
        return true;
    L13:
        this.mInterceptingOnItemTouchListener = null;
        goto L14
    L5:
        if (r3.getAction() != 0) goto L9;
        return false;
    L9:
        return findInterceptingOnItemTouchListener(r3);
    }

    private boolean findInterceptingOnItemTouchListener(MotionEvent r7) {
        int r0 = r7.getAction();
        int r1 = this.mOnItemTouchListeners.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L11;
        OnItemTouchListener r4 = this.mOnItemTouchListeners.get(r3);
        if (r4.onInterceptTouchEvent(this, r7) == false) goto L10;
        if (r0 == 3) goto L10;
        this.mInterceptingOnItemTouchListener = r4;
        return true;
    L10:
        r3 = r3 + 1;
        goto L3
    L11:
        return false;
    }

    private void findMinMaxChildLayoutPositions(int[] r9) {
        int r0 = this.mChildHelper.getChildCount();
        if (r0 != 0) goto L6;
        r9[0] = -1;
        r9[1] = -1;
        return;
    L6:
        int r3 = Integer.MAX_VALUE;
        int r4 = Integer.MIN_VALUE;
        int r5 = 0;
    L7:
        if (r5 >= r0) goto L19;
        ViewHolder r6 = getChildViewHolderInt(this.mChildHelper.getChildAt(r5));
        if (r6 == null) goto L18;
        if (r6.shouldIgnore() == true) goto L18;
        int r62 = r6.getLayoutPosition();
        if (r62 >= r3) goto L16;
        r3 = r62;
    L16:
        if (r62 <= r4) goto L18;
        r4 = r62;
    L18:
        r5 = r5 + 1;
        goto L7
    L19:
        r9[0] = r3;
        r9[1] = r4;
    }

    public static RecyclerView findNestedRecyclerView(View r4) {
        if ((r4 instanceof ViewGroup) == true) goto L6;
        return null;
    L6:
        if ((r4 instanceof RecyclerView) == true) goto L8;
        ViewGroup r42 = (ViewGroup) r4;
        int r0 = r42.getChildCount();
        int r2 = 0;
    L10:
        if (r2 >= r0) goto L15;
        RecyclerView r3 = findNestedRecyclerView(r42.getChildAt(r2));
        if (r3 != null) goto L13;
        r2 = r2 + 1;
        goto L10
    L13:
        return r3;
    L15:
        return null;
    L8:
        return (RecyclerView) r4;
    }

    private View findNextViewToFocus() {
        State r0 = this.mState;
        int r1 = r0.mFocusedItemPosition;
        if (r1 != (-1)) goto L6;
        r1 = 0;
    L6:
        int r02 = r0.getItemCount();
        int r2 = r1;
    L7:
        if (r2 >= r02) goto L16;
        ViewHolder r3 = findViewHolderForAdapterPosition(r2);
        if (r3 == null) goto L16;
        if (r3.itemView.hasFocusable() == true) goto L14;
        r2 = r2 + 1;
        goto L7
    L14:
        return r3.itemView;
    L16:
        int r03 = Math.min(r02, r1);
    L17:
        r03 = r03 - 1;
        if (r03 < 0) goto L27;
        ViewHolder r22 = findViewHolderForAdapterPosition(r03);
        if (r22 == null) goto L21;
        if (r22.itemView.hasFocusable() == false) goto L17;
        return r22.itemView;
    L21:
        return null;
    L27:
        return null;
    }

    public static ViewHolder getChildViewHolderInt(View r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        ViewGroup.LayoutParams r22 = r2.getLayoutParams();
        if ((r22 instanceof LayoutParams) == true) goto L9;
        return null;
    L9:
        return ((LayoutParams) r22).mViewHolder;
    }

    public static void getDecoratedBoundsWithMarginsInt(View r6, Rect r7) {
        LayoutParams r0 = (LayoutParams) r6.getLayoutParams();
        Rect r1 = r0.mDecorInsets;
        r7.set((r6.getLeft() - r1.left) - ((ViewGroup.MarginLayoutParams) r0).leftMargin, (r6.getTop() - r1.top) - ((ViewGroup.MarginLayoutParams) r0).topMargin, (r6.getRight() + r1.right) + ((ViewGroup.MarginLayoutParams) r0).rightMargin, (r6.getBottom() + r1.bottom) + ((ViewGroup.MarginLayoutParams) r0).bottomMargin);
    }

    private int getDeepestFocusedViewWithId(View r3) {
        int r2 = r3.getId();
    L4:
        if (r3.isFocused() == true) goto L12;
        if ((r3 instanceof ViewGroup) == false) goto L12;
        if (r3.hasFocus() == false) goto L12;
        r3 = ((ViewGroup) r3).getFocusedChild();
        if (r3.getId() == (-1)) goto L4;
        r2 = r3.getId();
    L12:
        return r2;
    }

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.mScrollingChildHelper != null) goto L6;
        this.mScrollingChildHelper = new NestedScrollingChildHelper(this);
    L6:
        return this.mScrollingChildHelper;
    }

    private void handleMissingPreInfoForChangeError(long r6, ViewHolder r8, ViewHolder r9) {
        int r0 = this.mChildHelper.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L18;
        ViewHolder r2 = getChildViewHolderInt(this.mChildHelper.getChildAt(r1));
        if (r2 == r8) goto L17;
        if (getChangedHolderKey(r2) != r6) goto L17;
        Adapter r62 = this.mAdapter;
        if (r62 != null) goto L12;
    L15:
        StringBuilder r63 = new StringBuilder("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:");
        r63.append(r2);
        r63.append(" \n View Holder 2:");
        r63.append(r8);
        RecyclerView$$ExternalSyntheticBUOutline0.m(r63, exceptionLabel());
        return;
    L12:
        if (r62.hasStableIds() == false) goto L15;
        StringBuilder r64 = new StringBuilder("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:");
        r64.append(r2);
        r64.append(" \n View Holder 2:");
        r64.append(r8);
        RecyclerView$$ExternalSyntheticBUOutline0.m(r64, exceptionLabel());
        return;
    L17:
        r1 = r1 + 1;
        goto L3
    L18:
        Log.e(TAG, "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + r9 + " cannot be found but it is necessary for " + r8 + exceptionLabel());
    }

    private boolean hasUpdatedView() {
        int r0 = this.mChildHelper.getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L14;
        ViewHolder r3 = getChildViewHolderInt(this.mChildHelper.getChildAt(r2));
        if (r3 == null) goto L13;
        if (r3.shouldIgnore() == true) goto L13;
        if (r3.isUpdated() == false) goto L13;
        return true;
    L13:
        r2 = r2 + 1;
        goto L3
    L14:
        return false;
    }

    @SuppressLint({"InlinedApi"})
    private void initAutofill() {
        if (ViewCompat.getImportantForAutofill(this) != 0) goto L6;
        ViewCompat.setImportantForAutofill(this, 8);
        return;
    }

    private void initChildrenHelper() {
        this.mChildHelper = new ChildHelper(new AnonymousClass5(this));
    }

    private boolean isPreferredNextFocus(View r10, View r11, int r12) {
        if (r11 == null) goto L85;
        if (r11 == this) goto L85;
        if (r11 == r10) goto L85;
        if (findContainingItemView(r11) != null) goto L11;
        return false;
    L11:
        if (r10 != null) goto L14;
        return true;
    L14:
        if (findContainingItemView(r10) != null) goto L16;
        return true;
    L16:
        this.mTempRect.set(0, 0, r10.getWidth(), r10.getHeight());
        this.mTempRect2.set(0, 0, r11.getWidth(), r11.getHeight());
        offsetDescendantRectToMyCoords(r10, this.mTempRect);
        offsetDescendantRectToMyCoords(r11, this.mTempRect2);
        char r112 = 65535;
        if (this.mLayout.getLayoutDirection() != 1) goto L19;
        int r102 = -1;
    L20:
        Rect r2 = this.mTempRect;
        int r3 = r2.left;
        Rect r4 = this.mTempRect2;
        int r5 = r4.left;
        if (r3 < r5) goto L25;
        if (r2.right <= r5) goto L25;
    L27:
        int r6 = r2.right;
        int r7 = r4.right;
        if (r6 > r7) goto L30;
        if (r3 >= r7) goto L30;
    L32:
        int r32 = 0;
    L33:
        int r52 = r2.top;
        int r62 = r4.top;
        if (r52 < r62) goto L38;
        if (r2.bottom <= r62) goto L38;
    L40:
        int r22 = r2.bottom;
        int r42 = r4.bottom;
        if (r22 > r42) goto L43;
        if (r52 >= r42) goto L43;
    L45:
        r112 = 0;
    L46:
        if (r12 != 1) goto L48;
        if (r112 < 0) goto L84;
        if (r112 == 0) goto L81;
    L83:
        return false;
    L81:
        if ((r32 * r102) >= 0) goto L83;
    L84:
        return true;
    L48:
        if (r12 != 2) goto L50;
        if (r112 > 0) goto L77;
        if (r112 == 0) goto L74;
    L76:
        return false;
    L74:
        if ((r32 * r102) <= 0) goto L76;
    L77:
        return true;
    L50:
        if (r12 != 17) goto L52;
        if (r32 >= 0) goto L70;
        return true;
    L70:
        return false;
    L52:
        if (r12 != 33) goto L54;
        if (r112 >= 0) goto L67;
        return true;
    L67:
        return false;
    L54:
        if (r12 != 66) goto L56;
        if (r32 <= 0) goto L64;
        return true;
    L64:
        return false;
    L56:
        if (r12 != 130) goto L60;
        if (r112 <= 0) goto L59;
        return true;
    L59:
        return false;
    L60:
        Structure$$ExternalSyntheticBUOutline2.m("Invalid direction: ", r12, exceptionLabel());
        return false;
    L43:
        if (r52 <= r62) goto L45;
    L38:
        if (r2.bottom >= r4.bottom) goto L40;
        r112 = 1;
    L30:
        if (r3 <= r5) goto L32;
        r32 = -1;
    L25:
        if (r2.right >= r4.right) goto L27;
        r32 = 1;
        goto L33
    L19:
        r102 = 1;
    L85:
        return false;
    }

    private void onPointerUp(MotionEvent r4) {
        int r0 = r4.getActionIndex();
        if (r4.getPointerId(r0) != this.mScrollPointerId) goto L9;
        if (r0 != 0) goto L6;
        int r02 = 1;
    L7:
        this.mScrollPointerId = r4.getPointerId(r02);
        int r1 = (int) (r4.getX(r02) + 0.5f);
        this.mLastTouchX = r1;
        this.mInitialTouchX = r1;
        int r42 = (int) (r4.getY(r02) + 0.5f);
        this.mLastTouchY = r42;
        this.mInitialTouchY = r42;
        return;
    L6:
        r02 = 0;
        goto L7
    }

    private boolean predictiveItemAnimationsEnabled() {
        if (this.mItemAnimator != null) goto L5;
        return false;
    L5:
        if (this.mLayout.supportsPredictiveItemAnimations() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private void processAdapterUpdatesAndSetAnimationFlags() {
        if (this.mDataSetHasChangedAfterLayout == false) goto L7;
        this.mAdapterHelper.reset();
        if (this.mDispatchItemsChangedEvent == false) goto L7;
        this.mLayout.onItemsChanged(this);
    L7:
        boolean r0 = predictiveItemAnimationsEnabled();
        AdapterHelper r1 = this.mAdapterHelper;
        if (r0 == false) goto L10;
        r1.preProcess();
    L11:
        boolean r12 = true;
        if (this.mItemsAddedOrRemoved == false) goto L14;
    L17:
        boolean r02 = true;
    L18:
        State r3 = this.mState;
        if (this.mFirstLayoutComplete == true) goto L21;
    L31:
        boolean r4 = false;
    L32:
        r3.mRunSimpleAnimations = r4;
        State r32 = this.mState;
        if (r32.mRunSimpleAnimations == false) goto L40;
        if (r02 == false) goto L40;
        if (this.mDataSetHasChangedAfterLayout == true) goto L40;
        if (predictiveItemAnimationsEnabled() == false) goto L40;
    L41:
        r32.mRunPredictiveAnimations = r12;
        return;
    L40:
        r12 = false;
        goto L41
    L21:
        if (this.mItemAnimator == null) goto L31;
        boolean r42 = this.mDataSetHasChangedAfterLayout;
        if (r42 == true) goto L27;
        if (r02 == true) goto L27;
        if (this.mLayout.mRequestedSimpleAnimations == false) goto L31;
    L27:
        if (r42 == true) goto L29;
    L30:
        r4 = true;
        goto L32
    L29:
        if (this.mAdapter.hasStableIds() == false) goto L31;
    L14:
        if (this.mItemsChanged == true) goto L17;
        r02 = false;
        goto L18
    L10:
        r1.consumeUpdatesInOnePass();
        goto L11
    }

    private void pullGlows(float r7, float r8, float r9, float r10) {
        boolean r3 = true;
        if (r8 >= 0.0f) goto L7;
        ensureLeftGlow();
        EdgeEffectCompat.onPull(this.mLeftGlow, (-r8) / getWidth(), 1.0f - (r9 / getHeight()));
    L5:
        boolean r92 = true;
    L11:
        if (r10 >= 0.0f) goto L14;
        ensureTopGlow();
        EdgeEffectCompat.onPull(this.mTopGlow, (-r10) / getHeight(), r7 / getWidth());
    L17:
        if (r3 == false) goto L19;
    L24:
        ViewCompat.postInvalidateOnAnimation(this);
        return;
    L19:
        if (r8 != 0.0f) goto L24;
        if (r10 != 0.0f) goto L24;
        return;
    L14:
        if (r10 <= 0.0f) goto L16;
        ensureBottomGlow();
        EdgeEffectCompat.onPull(this.mBottomGlow, r10 / getHeight(), 1.0f - (r7 / getWidth()));
        goto L17
    L16:
        r3 = r92;
        goto L17
    L7:
        if (r8 <= 0.0f) goto L9;
        ensureRightGlow();
        EdgeEffectCompat.onPull(this.mRightGlow, r8 / getWidth(), r9 / getHeight());
        goto L5
    L9:
        r92 = false;
        goto L11
    }

    private void recoverFocusFromState() {
        if (this.mPreserveFocusAfterLayout == true) goto L5;
        return;
    L5:
        if (this.mAdapter != null) goto L7;
        return;
    L7:
        if (hasFocus() == true) goto L9;
        return;
    L9:
        if (getDescendantFocusability() != 393216) goto L11;
        return;
    L11:
        if (getDescendantFocusability() != 131072) goto L16;
        if (isFocused() == false) goto L16;
        return;
    L16:
        if (isFocused() == false) goto L18;
    L20:
        View r1 = null;
        if (this.mState.mFocusedItemId != (-1)) goto L23;
    L25:
        ViewHolder r0 = null;
    L26:
        if (r0 == null) goto L34;
        if (this.mChildHelper.isHidden(r0.itemView) == true) goto L34;
        if (r0.itemView.hasFocusable() == false) goto L34;
        r1 = r0.itemView;
    L36:
        if (r1 == null) goto L50;
        int r6 = this.mState.mFocusedSubChildId;
        if (r6 == (-1)) goto L44;
        View r62 = r1.findViewById(r6);
        if (r62 == null) goto L44;
        if (r62.isFocusable() == false) goto L44;
        r1 = r62;
    L44:
        r1.requestFocus();
        return;
    L50:
        return;
    L34:
        if (this.mChildHelper.getChildCount() <= 0) goto L36;
        r1 = findNextViewToFocus();
        goto L36
    L23:
        if (this.mAdapter.hasStableIds() == false) goto L25;
        r0 = findViewHolderForItemId(this.mState.mFocusedItemId);
        goto L26
    L18:
        if (this.mChildHelper.isHidden(getFocusedChild()) == true) goto L20;
    }

    private void releaseGlows() {
        EdgeEffect r0 = this.mLeftGlow;
        if (r0 == null) goto L5;
        r0.onRelease();
        boolean r02 = this.mLeftGlow.isFinished();
    L6:
        EdgeEffect r1 = this.mTopGlow;
        if (r1 == null) goto L9;
        r1.onRelease();
        r02 = r02 | this.mTopGlow.isFinished();
    L9:
        EdgeEffect r12 = this.mRightGlow;
        if (r12 == null) goto L12;
        r12.onRelease();
        r02 = r02 | this.mRightGlow.isFinished();
    L12:
        EdgeEffect r13 = this.mBottomGlow;
        if (r13 == null) goto L15;
        r13.onRelease();
        r02 = r02 | this.mBottomGlow.isFinished();
    L15:
        if (r02 == false) goto L18;
        ViewCompat.postInvalidateOnAnimation(this);
        return;
    L18:
        return;
    L5:
        r02 = false;
        goto L6
    }

    private void resetFocusInfo() {
        State r2 = this.mState;
        r2.mFocusedItemId = -1;
        r2.mFocusedItemPosition = -1;
        r2.mFocusedSubChildId = -1;
    }

    private void resetScroll() {
        VelocityTracker r0 = this.mVelocityTracker;
        if (r0 == null) goto L5;
        r0.clear();
    L5:
        stopNestedScroll(0);
        releaseGlows();
    }

    private void saveFocusInfo() {
        ViewHolder r1 = null;
        if (this.mPreserveFocusAfterLayout == true) goto L5;
    L9:
        View r0 = null;
    L10:
        if (r0 == null) goto L13;
        r1 = findContainingViewHolder(r0);
    L13:
        if (r1 != null) goto L16;
        resetFocusInfo();
        return;
    L16:
        State r02 = this.mState;
        if (this.mAdapter.hasStableIds() == false) goto L19;
        long r2 = r1.getItemId();
    L20:
        r02.mFocusedItemId = r2;
        State r03 = this.mState;
        if (this.mDataSetHasChangedAfterLayout == false) goto L24;
        int r22 = -1;
    L27:
        r03.mFocusedItemPosition = r22;
        this.mState.mFocusedSubChildId = getDeepestFocusedViewWithId(r1.itemView);
        return;
    L24:
        if (r1.isRemoved() == false) goto L26;
        r22 = r1.mOldPosition;
        goto L27
    L26:
        r22 = r1.getAdapterPosition();
        goto L27
    L19:
        r2 = -1;
        goto L20
    L5:
        if (hasFocus() == false) goto L9;
        if (this.mAdapter == null) goto L9;
        r0 = getFocusedChild();
        goto L10
    }

    private void setAdapterInternal(Adapter r3, boolean r4, boolean r5) {
        Adapter r0 = this.mAdapter;
        if (r0 == null) goto L5;
        r0.unregisterAdapterDataObserver(this.mObserver);
        this.mAdapter.onDetachedFromRecyclerView(this);
    L5:
        if (r4 == false) goto L7;
        if (r5 == true) goto L7;
    L8:
        this.mAdapterHelper.reset();
        Adapter r52 = this.mAdapter;
        this.mAdapter = r3;
        if (r3 == null) goto L11;
        r3.registerAdapterDataObserver(this.mObserver);
        r3.onAttachedToRecyclerView(this);
    L11:
        LayoutManager r32 = this.mLayout;
        if (r32 == null) goto L14;
        r32.onAdapterChanged(r52, this.mAdapter);
    L14:
        this.mRecycler.onAdapterChanged(r52, this.mAdapter, r4);
        this.mState.mStructureChanged = true;
        return;
    L7:
        removeAndRecycleViews();
        goto L8
    }

    private void stopScrollersInternal() {
        this.mViewFlinger.stop();
        LayoutManager r1 = this.mLayout;
        if (r1 == null) goto L6;
        r1.stopSmoothScroller();
        return;
    }

    public void absorbGlows(int r3, int r4) {
        if (r3 >= 0) goto L6;
        ensureLeftGlow();
        if (this.mLeftGlow.isFinished() == false) goto L10;
        this.mLeftGlow.onAbsorb(-r3);
    L10:
        if (r4 >= 0) goto L14;
        ensureTopGlow();
        if (this.mTopGlow.isFinished() == false) goto L18;
        this.mTopGlow.onAbsorb(-r4);
    L18:
        if (r3 != 0) goto L22;
        if (r4 != 0) goto L22;
        return;
    L22:
        ViewCompat.postInvalidateOnAnimation(this);
        return;
    L14:
        if (r4 <= 0) goto L18;
        ensureBottomGlow();
        if (this.mBottomGlow.isFinished() == false) goto L18;
        this.mBottomGlow.onAbsorb(r4);
        goto L18
    L6:
        if (r3 <= 0) goto L10;
        ensureRightGlow();
        if (this.mRightGlow.isFinished() == false) goto L10;
        this.mRightGlow.onAbsorb(r3);
        goto L10
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> r2, int r3, int r4) {
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L5;
    L8:
        super.addFocusables(r2, r3, r4);
        return;
    L5:
        if (r0.onAddFocusables(this, r2, r3, r4) == false) goto L8;
    }

    public void addItemDecoration(ItemDecoration r2) {
        addItemDecoration(r2, -1);
    }

    public void addItemDecoration(ItemDecoration r3, int r4) {
        LayoutManager r0 = this.mLayout;
        if (r0 == null) goto L6;
        r0.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
    L6:
        if (this.mItemDecorations.isEmpty() == false) goto L8;
        setWillNotDraw(false);
    L8:
        ArrayList<ItemDecoration> r02 = this.mItemDecorations;
        if (r4 >= 0) goto L11;
        r02.add(r3);
    L12:
        markItemDecorInsetsDirty();
        requestLayout();
        return;
    L11:
        r02.add(r4, r3);
        goto L12
    }

    public void addOnChildAttachStateChangeListener(OnChildAttachStateChangeListener r2) {
        if (this.mOnChildAttachStateListeners != null) goto L5;
        this.mOnChildAttachStateListeners = new ArrayList();
    L5:
        this.mOnChildAttachStateListeners.add(r2);
    }

    public void addOnItemTouchListener(OnItemTouchListener r1) {
        this.mOnItemTouchListeners.add(r1);
    }

    public void addOnScrollListener(OnScrollListener r2) {
        if (this.mScrollListeners != null) goto L5;
        this.mScrollListeners = new ArrayList();
    L5:
        this.mScrollListeners.add(r2);
    }

    public void animateAppearance(ViewHolder r2, ItemAnimator.ItemHolderInfo r3, ItemAnimator.ItemHolderInfo r4) {
        r2.setIsRecyclable(false);
        if (this.mItemAnimator.animateAppearance(r2, r3, r4) == false) goto L6;
        postAnimationRunner();
        return;
    }

    public void animateDisappearance(ViewHolder r2, ItemAnimator.ItemHolderInfo r3, ItemAnimator.ItemHolderInfo r4) {
        addAnimatingView(r2);
        r2.setIsRecyclable(false);
        if (this.mItemAnimator.animateDisappearance(this, r2, r3, r4) == false) goto L6;
        postAnimationRunner();
        return;
    }

    public void applyEdgeEffectColor(EdgeEffect r1) {
        if (r1 == null) goto L7;
        Integer r0 = this.glowColor;
        if (r0 == null) goto L8;
        r1.setColor(r0.intValue());
        return;
    L8:
        return;
    }

    public void assertInLayoutOrScroll(String r2) {
        if (isComputingLayout() == true) goto L9;
        if (r2 != null) goto L7;
        CstInsn$$ExternalSyntheticBUOutline0.m("Cannot call this method unless RecyclerView is computing a layout or scrolling", exceptionLabel());
        return;
    L7:
        Preconditions$$ExternalSyntheticBUOutline0.m(r2, exceptionLabel());
        return;
    }

    public void assertNotInLayoutOrScroll(String r3) {
        if (isComputingLayout() == false) goto L10;
        if (r3 != null) goto L7;
        CstInsn$$ExternalSyntheticBUOutline0.m("Cannot call this method while RecyclerView is computing a layout or scrolling", exceptionLabel());
        return;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m(r3);
        return;
    L10:
        if (this.mDispatchScrollCounter <= 0) goto L13;
        Log.w(TAG, "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(_UrlKt.FRAGMENT_ENCODE_SET + exceptionLabel()));
        return;
    }

    public boolean canReuseUpdatedViewHolder(ViewHolder r2) {
        ItemAnimator r1 = this.mItemAnimator;
        if (r1 != null) goto L5;
        return true;
    L5:
        if (r1.canReuseUpdatedViewHolder(r2, r2.getUnmodifiedPayloads()) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams r2) {
        if ((r2 instanceof LayoutParams) == true) goto L5;
        return false;
    L5:
        if (this.mLayout.checkLayoutParams((LayoutParams) r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void clearOldPositions() {
        int r0 = this.mChildHelper.getUnfilteredChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L10;
        ViewHolder r2 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(r1));
        if (r2 == null) goto L9;
        if (r2.shouldIgnore() == true) goto L9;
        r2.clearOldPosition();
    L9:
        r1 = r1 + 1;
        goto L3
    L10:
        this.mRecycler.clearOldPositions();
    }

    public void clearOnChildAttachStateChangeListeners() {
        List<OnChildAttachStateChangeListener> r0 = this.mOnChildAttachStateListeners;
        if (r0 == null) goto L6;
        r0.clear();
        return;
    }

    public void clearOnScrollListeners() {
        List<OnScrollListener> r0 = this.mScrollListeners;
        if (r0 == null) goto L6;
        r0.clear();
        return;
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L6;
        return 0;
    L6:
        if (r0.canScrollHorizontally() == true) goto L8;
        return 0;
    L8:
        return this.mLayout.computeHorizontalScrollExtent(this.mState);
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L6;
        return 0;
    L6:
        if (r0.canScrollHorizontally() == true) goto L8;
        return 0;
    L8:
        return this.mLayout.computeHorizontalScrollOffset(this.mState);
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L6;
        return 0;
    L6:
        if (r0.canScrollHorizontally() == true) goto L8;
        return 0;
    L8:
        return this.mLayout.computeHorizontalScrollRange(this.mState);
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L6;
        return 0;
    L6:
        if (r0.canScrollVertically() == true) goto L8;
        return 0;
    L8:
        return this.mLayout.computeVerticalScrollExtent(this.mState);
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L6;
        return 0;
    L6:
        if (r0.canScrollVertically() == true) goto L8;
        return 0;
    L8:
        return this.mLayout.computeVerticalScrollOffset(this.mState);
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L6;
        return 0;
    L6:
        if (r0.canScrollVertically() == true) goto L8;
        return 0;
    L8:
        return this.mLayout.computeVerticalScrollRange(this.mState);
    }

    public void considerReleasingGlowsOnScroll(int r3, int r4) {
        EdgeEffect r0 = this.mLeftGlow;
        if (r0 != null) goto L5;
    L8:
        boolean r02 = false;
    L9:
        EdgeEffect r1 = this.mRightGlow;
        if (r1 != null) goto L12;
    L15:
        EdgeEffect r32 = this.mTopGlow;
        if (r32 != null) goto L18;
    L21:
        EdgeEffect r33 = this.mBottomGlow;
        if (r33 != null) goto L24;
    L27:
        if (r02 == false) goto L30;
        ViewCompat.postInvalidateOnAnimation(this);
        return;
    L30:
        return;
    L24:
        if (r33.isFinished() == true) goto L27;
        if (r4 >= 0) goto L27;
        this.mBottomGlow.onRelease();
        r02 = r02 | this.mBottomGlow.isFinished();
        goto L27
    L18:
        if (r32.isFinished() == true) goto L21;
        if (r4 <= 0) goto L21;
        this.mTopGlow.onRelease();
        r02 = r02 | this.mTopGlow.isFinished();
        goto L21
    L12:
        if (r1.isFinished() == true) goto L15;
        if (r3 >= 0) goto L15;
        this.mRightGlow.onRelease();
        r02 = r02 | this.mRightGlow.isFinished();
        goto L15
    L5:
        if (r0.isFinished() == true) goto L8;
        if (r3 <= 0) goto L8;
        this.mLeftGlow.onRelease();
        r02 = this.mLeftGlow.isFinished();
        goto L9
    }

    public void consumePendingUpdateOperations() {
        if (this.mFirstLayoutComplete == true) goto L5;
    L26:
        TraceCompat.beginSection(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
        dispatchLayout();
        TraceCompat.endSection();
        return;
    L5:
        if (this.mDataSetHasChangedAfterLayout == true) goto L26;
        if (this.mAdapterHelper.hasPendingUpdates() == true) goto L11;
        return;
    L11:
        if (this.mAdapterHelper.hasAnyUpdateTypes(4) == false) goto L23;
        if (this.mAdapterHelper.hasAnyUpdateTypes(11) == true) goto L23;
        TraceCompat.beginSection(TRACE_HANDLE_ADAPTER_UPDATES_TAG);
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        this.mAdapterHelper.preProcess();
        if (this.mLayoutWasDefered == false) goto L17;
    L20:
        stopInterceptRequestLayout(true);
        onExitLayoutOrScroll();
        TraceCompat.endSection();
        return;
    L17:
        if (hasUpdatedView() == false) goto L19;
        dispatchLayout();
        goto L20
    L19:
        this.mAdapterHelper.consumePostponedUpdates();
    L23:
        if (this.mAdapterHelper.hasPendingUpdates() == false) goto L28;
        TraceCompat.beginSection(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
        dispatchLayout();
        TraceCompat.endSection();
        return;
    }

    public void defaultOnMeasure(int r3, int r4) {
        setMeasuredDimension(LayoutManager.chooseSize(r3, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth(this)), LayoutManager.chooseSize(r4, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight(this)));
    }

    public void dispatchChildAttached(View r3) {
        ViewHolder r0 = getChildViewHolderInt(r3);
        onChildAttachedToWindow(r3);
        Adapter r1 = this.mAdapter;
        if (r1 == null) goto L6;
        if (r0 == null) goto L6;
        r1.onViewAttachedToWindow(r0);
    L6:
        List<OnChildAttachStateChangeListener> r02 = this.mOnChildAttachStateListeners;
        if (r02 == null) goto L11;
        int r03 = r02.size() - 1;
    L9:
        if (r03 < 0) goto L13;
        this.mOnChildAttachStateListeners.get(r03).onChildViewAttachedToWindow(r3);
        r03 = r03 - 1;
        goto L9
    L13:
        return;
    }

    public void dispatchChildDetached(View r3) {
        ViewHolder r0 = getChildViewHolderInt(r3);
        onChildDetachedFromWindow(r3);
        Adapter r1 = this.mAdapter;
        if (r1 == null) goto L6;
        if (r0 == null) goto L6;
        r1.onViewDetachedFromWindow(r0);
    L6:
        List<OnChildAttachStateChangeListener> r02 = this.mOnChildAttachStateListeners;
        if (r02 == null) goto L11;
        int r03 = r02.size() - 1;
    L9:
        if (r03 < 0) goto L13;
        this.mOnChildAttachStateListeners.get(r03).onChildViewDetachedFromWindow(r3);
        r03 = r03 - 1;
        goto L9
    L13:
        return;
    }

    public void dispatchLayout() {
        if (this.mAdapter != null) goto L7;
        Log.e(TAG, "No adapter attached; skipping layout");
        return;
    L7:
        if (this.mLayout != null) goto L10;
        Log.e(TAG, "No layout manager attached; skipping layout");
        return;
    L10:
        State r0 = this.mState;
        r0.mIsMeasuring = false;
        if (r0.mLayoutStep != 1) goto L14;
        dispatchLayoutStep1();
        this.mLayout.setExactMeasureSpecsFrom(this);
        dispatchLayoutStep2();
    L22:
        dispatchLayoutStep3();
        return;
    L14:
        if (this.mAdapterHelper.hasUpdates() == false) goto L16;
    L21:
        this.mLayout.setExactMeasureSpecsFrom(this);
        dispatchLayoutStep2();
        goto L22
    L16:
        if (this.mLayout.getWidth() != getWidth()) goto L21;
        if (this.mLayout.getHeight() != getHeight()) goto L21;
        this.mLayout.setExactMeasureSpecsFrom(this);
        goto L22
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float r1, float r2, boolean r3) {
        return getScrollingChildHelper().dispatchNestedFling(r1, r2, r3);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float r1, float r2) {
        return getScrollingChildHelper().dispatchNestedPreFling(r1, r2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int r1, int r2, int[] r3, int[] r4) {
        return getScrollingChildHelper().dispatchNestedPreScroll(r1, r2, r3, r4);
    }

    public boolean dispatchNestedPreScroll(int r1, int r2, int[] r3, int[] r4, int r5) {
        return getScrollingChildHelper().dispatchNestedPreScroll(r1, r2, r3, r4, r5);
    }

    public final void dispatchNestedScroll(int r1, int r2, int r3, int r4, int[] r5, int r6, int[] r7) {
        getScrollingChildHelper().dispatchNestedScroll(r1, r2, r3, r4, r5, r6, r7);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int r1, int r2, int r3, int r4, int[] r5) {
        return getScrollingChildHelper().dispatchNestedScroll(r1, r2, r3, r4, r5);
    }

    public boolean dispatchNestedScroll(int r1, int r2, int r3, int r4, int[] r5, int r6) {
        return getScrollingChildHelper().dispatchNestedScroll(r1, r2, r3, r4, r5, r6);
    }

    public void dispatchOnScrollStateChanged(int r3) {
        LayoutManager r0 = this.mLayout;
        if (r0 == null) goto L5;
        r0.onScrollStateChanged(r3);
    L5:
        onScrollStateChanged(r3);
        OnScrollListener r02 = this.mScrollListener;
        if (r02 == null) goto L8;
        r02.onScrollStateChanged(this, r3);
    L8:
        List<OnScrollListener> r03 = this.mScrollListeners;
        if (r03 == null) goto L13;
        int r04 = r03.size() - 1;
    L11:
        if (r04 < 0) goto L15;
        this.mScrollListeners.get(r04).onScrollStateChanged(this, r3);
        r04 = r04 - 1;
        goto L11
    L15:
        return;
    }

    public void dispatchOnScrolled(int r5, int r6) {
        this.mDispatchScrollCounter++;
        int r0 = getScrollX();
        int r1 = getScrollY();
        onScrollChanged(r0, r1, r0 - r5, r1 - r6);
        onScrolled(r5, r6);
        OnScrollListener r02 = this.mScrollListener;
        if (r02 == null) goto L5;
        r02.onScrolled(this, r5, r6);
    L5:
        List<OnScrollListener> r03 = this.mScrollListeners;
        if (r03 == null) goto L10;
        int r04 = r03.size() - 1;
    L8:
        if (r04 < 0) goto L10;
        this.mScrollListeners.get(r04).onScrolled(this, r5, r6);
        r04 = r04 - 1;
    L10:
        this.mDispatchScrollCounter--;
    }

    public void dispatchPendingImportantForAccessibilityChanges() {
        int r0 = this.mPendingAccessibilityImportanceChange.size();
    L3:
        r0 = r0 - 1;
        List<ViewHolder> r1 = this.mPendingAccessibilityImportanceChange;
        if (r0 < 0) goto L14;
        ViewHolder r12 = r1.get(r0);
        if (r12.itemView.getParent() != this) goto L3;
        if (r12.shouldIgnore() == true) goto L3;
        int r2 = r12.mPendingAccessibilityState;
        if (r2 == (-1)) goto L3;
        ViewCompat.setImportantForAccessibility(r12.itemView, r2);
        r12.mPendingAccessibilityState = -1;
        goto L3
    L14:
        r1.clear();
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent r1) {
        onPopulateAccessibilityEvent(r1);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> r1) {
        dispatchThawSelfOnly(r1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSaveInstanceState(SparseArray<Parcelable> r1) {
        dispatchFreezeSelfOnly(r1);
    }

    @Override // android.view.View
    public void draw(Canvas r8) {
        super.draw(r8);
        int r0 = this.mItemDecorations.size();
        boolean r1 = false;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        this.mItemDecorations.get(r2).onDrawOver(r8, this, this.mState);
        r2 = r2 + 1;
        goto L3
    L5:
        Integer r02 = this.glowColor;
        boolean r22 = true;
        if (r02 != null) goto L8;
    L9:
        EdgeEffect r03 = this.mLeftGlow;
        if (r03 != null) goto L12;
    L24:
        boolean r4 = false;
    L25:
        EdgeEffect r04 = this.mTopGlow;
        if (r04 != null) goto L28;
    L39:
        EdgeEffect r05 = this.mRightGlow;
        if (r05 != null) goto L42;
    L54:
        EdgeEffect r06 = this.mBottomGlow;
        if (r06 != null) goto L57;
    L68:
        r1 = r4;
    L69:
        if (r1 == false) goto L71;
    L77:
        r22 = r1;
    L78:
        if (r22 == false) goto L82;
        ViewCompat.postInvalidateOnAnimation(this);
        return;
    L82:
        return;
    L71:
        if (this.mItemAnimator == null) goto L77;
        if (this.mItemDecorations.size() <= 0) goto L77;
        if (this.mItemAnimator.isRunning() == false) goto L77;
    L57:
        if (r06.isFinished() == true) goto L68;
        int r07 = r8.save();
        r8.rotate(180.0f);
        if (this.mClipToPadding == false) goto L61;
        r8.translate((-getWidth()) + getPaddingRight(), (-getHeight()) + getPaddingBottom());
    L62:
        EdgeEffect r3 = this.mBottomGlow;
        if (r3 != null) goto L65;
    L67:
        r1 = r1 | r4;
        r8.restoreToCount(r07);
        goto L69
    L65:
        if (r3.draw(r8) == false) goto L67;
        r1 = true;
        goto L67
    L61:
        r8.translate(-getWidth(), (-getHeight()) + this.bottomGlowOffset);
        goto L62
    L42:
        if (r05.isFinished() == true) goto L54;
        int r08 = r8.save();
        int r32 = getWidth();
        if (this.mClipToPadding == false) goto L46;
        int r5 = getPaddingTop();
    L47:
        r8.rotate(90.0f);
        r8.translate(-r5, -r32);
        EdgeEffect r33 = this.mRightGlow;
        if (r33 != null) goto L50;
    L52:
        boolean r34 = false;
    L53:
        r4 = r4 | r34;
        r8.restoreToCount(r08);
        goto L54
    L50:
        if (r33.draw(r8) == false) goto L52;
        r34 = true;
        goto L53
    L46:
        r5 = 0;
        goto L47
    L28:
        if (r04.isFinished() == true) goto L39;
        int r09 = r8.save();
        if (this.mClipToPadding == false) goto L32;
        r8.translate(getPaddingLeft(), getPaddingTop());
    L32:
        r8.translate(0.0f, this.topGlowOffset);
        EdgeEffect r35 = this.mTopGlow;
        if (r35 != null) goto L35;
    L37:
        boolean r36 = false;
    L38:
        r4 = r4 | r36;
        r8.restoreToCount(r09);
        goto L39
    L35:
        if (r35.draw(r8) == false) goto L37;
        r36 = true;
        goto L38
    L12:
        if (r03.isFinished() == true) goto L24;
        int r010 = r8.save();
        if (this.mClipToPadding == false) goto L16;
        int r42 = getPaddingBottom();
    L17:
        r8.rotate(270.0f);
        r8.translate((-getHeight()) + r42, 0.0f);
        EdgeEffect r43 = this.mLeftGlow;
        if (r43 != null) goto L20;
    L22:
        r4 = false;
    L23:
        r8.restoreToCount(r010);
        goto L25
    L20:
        if (r43.draw(r8) == false) goto L22;
        r4 = true;
        goto L23
    L16:
        r42 = 0;
        goto L17
    L8:
        if (r02.intValue() == 0) goto L69;
        goto L9
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r1, View r2, long r3) {
        return super.drawChild(r1, r2, r3);
    }

    public void ensureBottomGlow() {
        if (this.mBottomGlow == null) goto L5;
        return;
    L5:
        EdgeEffect r0 = this.mEdgeEffectFactory.createEdgeEffect(this, 3);
        this.mBottomGlow = r0;
        if (this.mClipToPadding == false) goto L8;
        r0.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
    L9:
        applyEdgeEffectColor(this.mBottomGlow);
        return;
    L8:
        r0.setSize(getMeasuredWidth(), getMeasuredHeight());
        goto L9
    }

    public void ensureLeftGlow() {
        if (this.mLeftGlow == null) goto L5;
        return;
    L5:
        EdgeEffect r0 = this.mEdgeEffectFactory.createEdgeEffect(this, 0);
        this.mLeftGlow = r0;
        if (this.mClipToPadding == false) goto L8;
        r0.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
    L9:
        applyEdgeEffectColor(this.mLeftGlow);
        return;
    L8:
        r0.setSize(getMeasuredHeight(), getMeasuredWidth());
        goto L9
    }

    public void ensureRightGlow() {
        if (this.mRightGlow == null) goto L5;
        return;
    L5:
        EdgeEffect r0 = this.mEdgeEffectFactory.createEdgeEffect(this, 2);
        this.mRightGlow = r0;
        if (this.mClipToPadding == false) goto L8;
        r0.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
    L9:
        applyEdgeEffectColor(this.mRightGlow);
        return;
    L8:
        r0.setSize(getMeasuredHeight(), getMeasuredWidth());
        goto L9
    }

    public void ensureTopGlow() {
        if (this.mTopGlow == null) goto L5;
        return;
    L5:
        EdgeEffect r0 = this.mEdgeEffectFactory.createEdgeEffect(this, 1);
        this.mTopGlow = r0;
        if (this.mClipToPadding == false) goto L8;
        r0.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
    L9:
        applyEdgeEffectColor(this.mTopGlow);
        return;
    L8:
        r0.setSize(getMeasuredWidth(), getMeasuredHeight());
        goto L9
    }

    public String exceptionLabel() {
        StringBuilder r0 = new StringBuilder(" ");
        r0.append(super.toString());
        r0.append(", adapter:");
        r0.append(this.mAdapter);
        r0.append(", layout:");
        r0.append(this.mLayout);
        r0.append(", context:");
        r0.append(getContext());
        r0.append(", ainfo:");
        r0.append(this.moreInfo);
        String r2 = this.mAdapterHelper.getLastNotifies();
        if (r2 == null) goto L6;
        r0.append(", last notifies:\n");
        r0.append(r2);
    L6:
        return r0.toString();
    }

    public final void fillRemainingScrollValues(State r3) {
        if (getScrollState() != 2) goto L6;
        OverScroller r2 = this.mViewFlinger.mOverScroller;
        r3.mRemainingScrollHorizontal = r2.getFinalX() - r2.getCurrX();
        r3.mRemainingScrollVertical = r2.getFinalY() - r2.getCurrY();
        return;
    L6:
        r3.mRemainingScrollHorizontal = 0;
        r3.mRemainingScrollVertical = 0;
    }

    public View findChildViewUnder(float r6, float r7) {
        int r0 = this.mChildHelper.getChildCount() - 1;
    L3:
        if (r0 < 0) goto L14;
        View r1 = this.mChildHelper.getChildAt(r0);
        float r2 = r1.getTranslationX();
        float r3 = r1.getTranslationY();
        if (r6 < (r1.getLeft() + r2)) goto L13;
        if (r6 > (r1.getRight() + r2)) goto L13;
        if (r7 < (r1.getTop() + r3)) goto L13;
        if (r7 > (r1.getBottom() + r3)) goto L13;
        return r1;
    L13:
        r0 = r0 - 1;
        goto L3
    L14:
        return null;
    }

    public View findContainingItemView(View r3) {
        ViewParent r0 = r3.getParent();
    L3:
        if (r0 == null) goto L8;
        if (r0 == this) goto L8;
        if ((r0 instanceof View) == false) goto L8;
        r3 = r0;
        r0 = r3.getParent();
    L8:
        if (r0 != this) goto L10;
        return r3;
    L10:
        return null;
    }

    public ViewHolder findContainingViewHolder(View r1) {
        View r12 = findContainingItemView(r1);
        if (r12 != null) goto L7;
        return null;
    L7:
        return getChildViewHolder(r12);
    }

    public ViewHolder findViewHolderForAdapterPosition(int r6) {
        ViewHolder r1 = null;
        if (this.mDataSetHasChangedAfterLayout == false) goto L5;
        return null;
    L5:
        int r0 = this.mChildHelper.getUnfilteredChildCount();
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L18;
        ViewHolder r3 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(r2));
        if (r3 == null) goto L17;
        if (r3.isRemoved() == true) goto L17;
        if (getAdapterPositionFor(r3) != r6) goto L17;
        if (this.mChildHelper.isHidden(r3.itemView) == false) goto L16;
        r1 = r3;
        goto L17
    L16:
        return r3;
    L17:
        r2 = r2 + 1;
        goto L6
    L18:
        return r1;
    }

    public ViewHolder findViewHolderForItemId(long r7) {
        Adapter r0 = this.mAdapter;
        ViewHolder r1 = null;
        if (r0 != null) goto L5;
    L20:
        return r1;
    L5:
        if (r0.hasStableIds() == false) goto L20;
        int r02 = this.mChildHelper.getUnfilteredChildCount();
        int r2 = 0;
    L8:
        if (r2 >= r02) goto L20;
        ViewHolder r3 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(r2));
        if (r3 == null) goto L19;
        if (r3.isRemoved() == true) goto L19;
        if (r3.getItemId() != r7) goto L19;
        if (this.mChildHelper.isHidden(r3.itemView) == false) goto L18;
        r1 = r3;
        goto L19
    L18:
        return r3;
    L19:
        r2 = r2 + 1;
        goto L8
    }

    public ViewHolder findViewHolderForLayoutPosition(int r2) {
        return findViewHolderForPosition(r2, false);
    }

    @Deprecated
    public ViewHolder findViewHolderForPosition(int r2) {
        return findViewHolderForPosition(r2, false);
    }

    public ViewHolder findViewHolderForPosition(int r6, boolean r7) {
        int r0 = this.mChildHelper.getUnfilteredChildCount();
        ViewHolder r1 = null;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L20;
        ViewHolder r3 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(r2));
        if (r3 == null) goto L19;
        if (r3.isRemoved() == true) goto L19;
        if (r7 == false) goto L13;
        if (r3.mPosition != r6) goto L19;
    L16:
        if (this.mChildHelper.isHidden(r3.itemView) == false) goto L18;
        r1 = r3;
        goto L19
    L18:
        return r3;
    L13:
        if (r3.getLayoutPosition() == r6) goto L16;
    L19:
        r2 = r2 + 1;
        goto L3
    L20:
        return r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public boolean fling(int r8, int r9) {
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L7;
        Log.e(TAG, "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        return false;
    L7:
        if (this.mLayoutSuppressed == false) goto L9;
        return false;
    L9:
        int r02 = r0.canScrollHorizontally();
        boolean r2 = this.mLayout.canScrollVertically();
        if (r02 != 0) goto L12;
    L13:
        r8 = 0;
    L14:
        if (r2 == true) goto L16;
    L17:
        r9 = 0;
    L18:
        if (r8 != 0) goto L21;
        if (r9 != 0) goto L21;
        return false;
    L21:
        float r3 = r8;
        float r4 = r9;
        if (dispatchNestedPreFling(r3, r4) == false) goto L24;
    L39:
        return false;
    L24:
        if (r02 != 0) goto L28;
        if (r2 == true) goto L28;
        boolean r6 = false;
    L29:
        dispatchNestedFling(r3, r4, r6);
        OnFlingListener r32 = this.mOnFlingListener;
        if (r32 != null) goto L32;
    L34:
        if (r6 == false) goto L39;
        if (r2 == false) goto L37;
        r02 = (r02 == true ? 1 : 0) | 2;
    L37:
        startNestedScroll(r02, 1);
        int r03 = this.mMaxFlingVelocity;
        int r82 = Math.max(-r03, Math.min(r8, r03));
        int r04 = this.mMaxFlingVelocity;
        this.mViewFlinger.fling(r82, Math.max(-r04, Math.min(r9, r04)));
        return true;
    L32:
        if (r32.onFling(r8, r9) == false) goto L34;
        return true;
    L28:
        r6 = true;
        goto L29
    L16:
        if (Math.abs(r9) >= this.mMinFlingVelocity) goto L18;
    L12:
        if (Math.abs(r8) >= this.mMinFlingVelocity) goto L14;
        goto L13
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View r8, int r9) {
        View r0 = this.mLayout.onInterceptFocusSearch(r8, r9);
        if (r0 == null) goto L5;
        return r0;
    L5:
        boolean r1 = true;
        if (this.mAdapter != null) goto L8;
    L14:
        boolean r02 = false;
    L15:
        FocusFinder r3 = FocusFinder.getInstance();
        if (r02 == true) goto L18;
    L54:
        View r12 = r3.findNextFocus(this, r8, r9);
        if (r12 != null) goto L61;
        if (r02 == false) goto L61;
        consumePendingUpdateOperations();
        if (findContainingItemView(r8) != null) goto L60;
        return null;
    L60:
        startInterceptRequestLayout();
        View r03 = this.mLayout.onFocusSearchFailed(r8, r9, this.mRecycler, this.mState);
        stopInterceptRequestLayout(false);
    L62:
        if (r03 == null) goto L72;
        if (r03.hasFocusable() == true) goto L72;
        if (getFocusedChild() == null) goto L68;
        requestChildOnScreen(r03, null);
        return r8;
    L68:
        return super.focusSearch(r8, r9);
    L72:
        if (isPreferredNextFocus(r8, r03, r9) == false) goto L75;
        return r03;
    L75:
        return super.focusSearch(r8, r9);
    L61:
        r03 = r12;
        goto L62
    L18:
        if (r9 == 2) goto L21;
        if (r9 != 1) goto L54;
    L21:
        if (this.mLayout.canScrollVertically() == false) goto L28;
        if (r9 != 2) goto L24;
        int r04 = 130;
    L26:
        if (r3.findNextFocus(this, r8, r04) != null) goto L28;
        boolean r05 = true;
    L29:
        if (r05 == false) goto L31;
    L48:
        if (r05 == false) goto L53;
        consumePendingUpdateOperations();
        if (findContainingItemView(r8) != null) goto L52;
        return null;
    L52:
        startInterceptRequestLayout();
        this.mLayout.onFocusSearchFailed(r8, r9, this.mRecycler, this.mState);
        stopInterceptRequestLayout(false);
    L53:
        r03 = r3.findNextFocus(this, r8, r9);
        goto L62
    L31:
        if (this.mLayout.canScrollHorizontally() == false) goto L48;
        if (this.mLayout.getLayoutDirection() != 1) goto L35;
        boolean r06 = true;
    L36:
        if (r9 != 2) goto L38;
        boolean r5 = true;
    L40:
        if ((r06 ^ r5) == false) goto L42;
        int r07 = 66;
    L44:
        if (r3.findNextFocus(this, r8, r07) == null) goto L47;
        r1 = false;
    L47:
        r05 = r1;
        goto L48
    L42:
        r07 = 17;
        goto L44
    L38:
        r5 = false;
        goto L40
    L35:
        r06 = false;
        goto L36
    L24:
        r04 = 33;
    L28:
        r05 = false;
        goto L29
    L8:
        if (this.mLayout == null) goto L14;
        if (isComputingLayout() == true) goto L14;
        if (this.mLayoutSuppressed == true) goto L14;
        r02 = true;
        goto L15
    }

    public void forAllChild(Consumer<View> r4) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L6;
        r4.accept(getChildAt(r1));
        r1 = r1 + 1;
        goto L4
    L6:
        int r12 = 0;
    L8:
        if (r12 >= getHiddenChildCount()) goto L11;
        r4.accept(getHiddenChildAt(r12));
        r12 = r12 + 1;
    L11:
        if (r0 >= getAttachedScrapChildCount()) goto L13;
        r4.accept(getAttachedScrapChildAt(r0));
        r0 = r0 + 1;
        goto L11
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L5;
        CstInsn$$ExternalSyntheticBUOutline0.m("RecyclerView has no LayoutManager", exceptionLabel());
        return null;
    L5:
        return r0.generateDefaultLayoutParams();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet r2) {
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L5;
        CstInsn$$ExternalSyntheticBUOutline0.m("RecyclerView has no LayoutManager", exceptionLabel());
        return null;
    L5:
        return r0.generateLayoutParams(getContext(), r2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r2) {
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L5;
        CstInsn$$ExternalSyntheticBUOutline0.m("RecyclerView has no LayoutManager", exceptionLabel());
        return null;
    L5:
        return r0.generateLayoutParams(r2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public Adapter getAdapter() {
        return this.mAdapter;
    }

    public int getAdapterPositionFor(ViewHolder r2) {
        if (r2.hasAnyOfTheFlags(524) == false) goto L5;
        return -1;
    L5:
        if (r2.isBound() == true) goto L8;
        return -1;
    L8:
        return this.mAdapterHelper.applyPendingUpdatesToPosition(r2.mPosition);
    }

    public View getAttachedScrapChildAt(int r2) {
        if (r2 >= 0) goto L4;
        return null;
    L4:
        if (r2 < this.mRecycler.mAttachedScrap.size()) goto L7;
        return null;
    L7:
        return this.mRecycler.getScrapViewAt(r2);
    }

    public int getAttachedScrapChildCount() {
        return this.mRecycler.getScrapCount();
    }

    @Override // android.view.View
    public int getBaseline() {
        LayoutManager r0 = this.mLayout;
        if (r0 == null) goto L7;
        return r0.getBaseline();
    L7:
        return super.getBaseline();
    }

    public int getBottomGlowOffset() {
        return this.bottomGlowOffset;
    }

    public View getCachedChildAt(int r2) {
        if (r2 >= 0) goto L4;
        return null;
    L4:
        if (r2 < this.mRecycler.mCachedViews.size()) goto L7;
        return null;
    L7:
        return this.mRecycler.mCachedViews.get(r2).itemView;
    }

    public int getCachedChildCount() {
        return this.mRecycler.mCachedViews.size();
    }

    public long getChangedHolderKey(ViewHolder r1) {
        if (this.mAdapter.hasStableIds() == false) goto L7;
        return r1.getItemId();
    L7:
        return r1.mPosition;
    }

    public int getChildAdapterPosition(View r1) {
        ViewHolder r0 = getChildViewHolderInt(r1);
        if (r0 != null) goto L5;
        return -1;
    L5:
        return r0.getAdapterPosition();
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int r2, int r3) {
        ChildDrawingOrderCallback r0 = this.mChildDrawingOrderCallback;
        if (r0 != null) goto L7;
        return super.getChildDrawingOrder(r2, r3);
    L7:
        return r0.onGetChildDrawingOrder(r2, r3);
    }

    public long getChildItemId(View r3) {
        Adapter r2 = this.mAdapter;
        if (r2 != null) goto L5;
    L11:
        return -1;
    L5:
        if (r2.hasStableIds() == false) goto L11;
        ViewHolder r22 = getChildViewHolderInt(r3);
        if (r22 == null) goto L11;
        return r22.getItemId();
    }

    public int getChildLayoutPosition(View r1) {
        ViewHolder r0 = getChildViewHolderInt(r1);
        if (r0 != null) goto L5;
        return -1;
    L5:
        return r0.getLayoutPosition();
    }

    @Deprecated
    public int getChildPosition(View r1) {
        return getChildAdapterPosition(r1);
    }

    public ViewHolder getChildViewHolder(View r3) {
        ViewParent r0 = r3.getParent();
        if (r0 == null) goto L9;
        if (r0 == this) goto L9;
        Utils$$ExternalSyntheticBUOutline2.m("View ", r3, " is not a direct child of ", this);
        return null;
    L9:
        return getChildViewHolderInt(r3);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    public float getCurrentVelocity() {
        VelocityTracker r0 = this.mVelocityTracker;
        if (r0 == null) goto L6;
        r0.computeCurrentVelocity(1000, this.mMaxFlingVelocity);
        return this.mVelocityTracker.getYVelocity();
    L6:
        return 0.0f;
    }

    public void getDecoratedBoundsWithMargins(View r1, Rect r2) {
        getDecoratedBoundsWithMarginsInt(r1, r2);
    }

    public EdgeEffectFactory getEdgeEffectFactory() {
        return this.mEdgeEffectFactory;
    }

    public View getHiddenChildAt(int r1) {
        return this.mChildHelper.getHiddenChildAt(r1);
    }

    public int getHiddenChildCount() {
        return this.mChildHelper.getHiddenChildCount();
    }

    public ItemAnimator getItemAnimator() {
        return this.mItemAnimator;
    }

    public Rect getItemDecorInsetsForChild(View r9) {
        LayoutParams r0 = (LayoutParams) r9.getLayoutParams();
        if (r0.mInsetsDirty == true) goto L7;
        return r0.mDecorInsets;
    L7:
        if (this.mState.isPreLayout() == true) goto L9;
    L14:
        Rect r1 = r0.mDecorInsets;
        r1.set(0, 0, 0, 0);
        int r3 = this.mItemDecorations.size();
        int r4 = 0;
    L15:
        if (r4 >= r3) goto L17;
        this.mTempRect.set(0, 0, 0, 0);
        this.mItemDecorations.get(r4).getItemOffsets(this.mTempRect, r9, this, this.mState);
        int r5 = r1.left;
        Rect r6 = this.mTempRect;
        r1.left = r5 + r6.left;
        r1.top += r6.top;
        r1.right += r6.right;
        r1.bottom += r6.bottom;
        r4 = r4 + 1;
        goto L15
    L17:
        r0.mInsetsDirty = false;
        return r1;
    L9:
        if (r0.isItemChanged() == true) goto L13;
        if (r0.isViewInvalid() == false) goto L14;
    L13:
        return r0.mDecorInsets;
    }

    public ItemDecoration getItemDecorationAt(int r3) {
        int r0 = getItemDecorationCount();
        if (r3 < 0) goto L8;
        if (r3 >= r0) goto L8;
        return this.mItemDecorations.get(r3);
    L8:
        throw new IndexOutOfBoundsException(r3 + " is an invalid index for size " + r0);
    }

    public int getItemDecorationCount() {
        return this.mItemDecorations.size();
    }

    public LayoutManager getLayoutManager() {
        return this.mLayout;
    }

    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public OnFlingListener getOnFlingListener() {
        return this.mOnFlingListener;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
    }

    public RecycledViewPool getRecycledViewPool() {
        return this.mRecycler.getRecycledViewPool();
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    public int getTopGlowOffset() {
        return this.topGlowOffset;
    }

    public boolean hasFixedSize() {
        return this.mHasFixedSize;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    public boolean hasNestedScrollingParent(int r1) {
        return getScrollingChildHelper().hasNestedScrollingParent(r1);
    }

    public boolean hasPendingAdapterUpdates() {
        if (this.mFirstLayoutComplete == true) goto L5;
        return true;
    L5:
        if (this.mDataSetHasChangedAfterLayout == false) goto L7;
        return true;
    L7:
        if (this.mAdapterHelper.hasPendingUpdates() == true) goto L14;
        return false;
    L14:
        return true;
    }

    public void initAdapterManager() {
        this.mAdapterHelper = new AdapterHelper(new AnonymousClass6(this));
    }

    public void initFastScroller(StateListDrawable r11, Drawable r12, StateListDrawable r13, Drawable r14) {
        if (r11 == null) goto L8;
        if (r12 == null) goto L8;
        if (r13 == null) goto L8;
        if (r14 == null) goto L8;
        getContext().getResources();
        new FastScroller(this, r11, r12, r13, r14, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(50.0f), 0);
        return;
    L8:
        Buffer$$ExternalSyntheticBUOutline4.m("Trying to set fast scroller without both required drawables.", exceptionLabel());
    }

    public void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    public void invalidateItemDecorations() {
        if (this.mItemDecorations.size() != 0) goto L5;
        return;
    L5:
        LayoutManager r0 = this.mLayout;
        if (r0 == null) goto L8;
        r0.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
    L8:
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public boolean isAccessibilityEnabled() {
        AccessibilityManager r0 = this.mAccessibilityManager;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isEnabled() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isAnimating() {
        ItemAnimator r0 = this.mItemAnimator;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isRunning() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    public boolean isComputingLayout() {
        if (this.mLayoutOrScrollCounter <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isFlingerWorking() {
        return this.isFlingerWorking;
    }

    @Deprecated
    public boolean isLayoutFrozen() {
        return isLayoutSuppressed();
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.mLayoutSuppressed;
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    public void jumpToPositionForSmoothScroller(int r2) {
        if (this.mLayout != null) goto L5;
        return;
    L5:
        setScrollState(2);
        this.mLayout.scrollToPosition(r2);
        awakenScrollBars();
    }

    public void markItemDecorInsetsDirty() {
        int r0 = this.mChildHelper.getUnfilteredChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        ((LayoutParams) this.mChildHelper.getUnfilteredChildAt(r1).getLayoutParams()).mInsetsDirty = true;
        r1 = r1 + 1;
        goto L3
    L5:
        this.mRecycler.markItemDecorInsetsDirty();
    }

    public void markKnownViewsInvalid() {
        int r0 = this.mChildHelper.getUnfilteredChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L10;
        ViewHolder r2 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(r1));
        if (r2 == null) goto L9;
        if (r2.shouldIgnore() == true) goto L9;
        r2.addFlags(6);
    L9:
        r1 = r1 + 1;
        goto L3
    L10:
        markItemDecorInsetsDirty();
        this.mRecycler.markKnownViewsInvalid();
    }

    public void offsetChildrenHorizontal(int r4) {
        int r0 = this.mChildHelper.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mChildHelper.getChildAt(r1).offsetLeftAndRight(r4);
        r1 = r1 + 1;
        goto L3
    }

    public void offsetChildrenVertical(int r4) {
        int r0 = this.mChildHelper.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mChildHelper.getChildAt(r1).offsetTopAndBottom(r4);
        r1 = r1 + 1;
        goto L3
    }

    public void offsetPositionRecordsForInsert(int r6, int r7) {
        int r0 = this.mChildHelper.getUnfilteredChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L12;
        ViewHolder r3 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(r2));
        if (r3 == null) goto L11;
        if (r3.shouldIgnore() == true) goto L11;
        if (r3.mPosition < r6) goto L11;
        r3.offsetPosition(r7, false);
        this.mState.mStructureChanged = true;
    L11:
        r2 = r2 + 1;
        goto L3
    L12:
        this.mRecycler.offsetPositionRecordsForInsert(r6, r7);
        requestLayout();
    }

    public void offsetPositionRecordsForMove(int r10, int r11) {
        int r0 = this.mChildHelper.getUnfilteredChildCount();
        if (r10 >= r11) goto L5;
        int r2 = -1;
        int r3 = r10;
        int r4 = r11;
    L6:
        int r6 = 0;
    L7:
        if (r6 >= r0) goto L19;
        ViewHolder r7 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(r6));
        if (r7 == null) goto L18;
        int r8 = r7.mPosition;
        if (r8 < r3) goto L18;
        if (r8 > r4) goto L18;
        if (r8 != r10) goto L16;
        r7.offsetPosition(r11 - r10, false);
    L17:
        this.mState.mStructureChanged = true;
        goto L18
    L16:
        r7.offsetPosition(r2, false);
    L18:
        r6 = r6 + 1;
        goto L7
    L19:
        this.mRecycler.offsetPositionRecordsForMove(r10, r11);
        requestLayout();
        return;
    L5:
        r4 = r10;
        r3 = r11;
        r2 = 1;
        goto L6
    }

    public void offsetPositionRecordsForRemove(int r8, int r9, boolean r10) {
        int r0 = r8 + r9;
        int r1 = this.mChildHelper.getUnfilteredChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L14;
        ViewHolder r3 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(r2));
        if (r3 == null) goto L13;
        if (r3.shouldIgnore() == true) goto L13;
        int r4 = r3.mPosition;
        if (r4 < r0) goto L11;
        r3.offsetPosition(-r9, r10);
        this.mState.mStructureChanged = true;
        goto L13
    L11:
        if (r4 < r8) goto L13;
        r3.flagRemovedAndOffsetPosition(r8 - 1, -r9, r10);
        this.mState.mStructureChanged = true;
    L13:
        r2 = r2 + 1;
        goto L3
    L14:
        this.mRecycler.offsetPositionRecordsForRemove(r8, r9, r10);
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        boolean r1 = true;
        this.mIsAttached = true;
        if (this.mFirstLayoutComplete == true) goto L5;
    L7:
        r1 = false;
    L8:
        this.mFirstLayoutComplete = r1;
        LayoutManager r12 = this.mLayout;
        if (r12 == null) goto L11;
        r12.dispatchAttachedToWindow(this);
    L11:
        this.mPostedAnimatorRunner = false;
        ThreadLocal<GapWorker> r0 = GapWorker.sGapWorker;
        GapWorker r13 = r0.get();
        this.mGapWorker = r13;
        if (r13 != null) goto L21;
        this.mGapWorker = new GapWorker();
        Display r14 = ViewCompat.getDisplay(this);
        if (isInEditMode() == true) goto L19;
        if (r14 == null) goto L19;
        float r15 = r14.getRefreshRate();
        if (r15 < 30.0f) goto L19;
    L20:
        GapWorker r2 = this.mGapWorker;
        r2.mFrameIntervalNs = (long) (1.0E9f / r15);
        r0.set(r2);
    L19:
        r15 = 60.0f;
    L21:
        this.mGapWorker.add(this);
        return;
    L5:
        if (isLayoutRequested() == true) goto L7;
        goto L7
    }

    public void onChildAttachedToWindow(View r1) {
    }

    public void onChildDetachedFromWindow(View r1) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ItemAnimator r0 = this.mItemAnimator;
        if (r0 == null) goto L5;
        r0.endAnimations();
    L5:
        stopScroll();
        this.mIsAttached = false;
        LayoutManager r02 = this.mLayout;
        if (r02 == null) goto L8;
        r02.dispatchDetachedFromWindow(this, this.mRecycler);
    L8:
        this.mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(this.mItemAnimatorRunner);
        this.mViewInfoStore.onDetach();
        GapWorker r03 = this.mGapWorker;
        if (r03 == null) goto L12;
        r03.remove(this);
        this.mGapWorker = null;
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r5) {
        super.onDraw(r5);
        int r0 = this.mItemDecorations.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mItemDecorations.get(r1).onDraw(r5, this, this.mState);
        r1 = r1 + 1;
        goto L3
    }

    public void onEnterLayoutOrScroll() {
        this.mLayoutOrScrollCounter++;
    }

    public void onExitLayoutOrScroll() {
        onExitLayoutOrScroll(true);
    }

    public void onExitLayoutOrScroll(boolean r3) {
        int r0 = this.mLayoutOrScrollCounter - 1;
        this.mLayoutOrScrollCounter = r0;
        if (r0 >= 1) goto L8;
        this.mLayoutOrScrollCounter = 0;
        if (r3 == false) goto L9;
        dispatchContentChangedIfNecessary();
        dispatchPendingImportantForAccessibilityChanges();
        return;
    L9:
        return;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent r6) {
        if (this.mLayout != null) goto L6;
        return false;
    L6:
        if (this.mLayoutSuppressed == false) goto L9;
        return false;
    L9:
        if (r6.getAction() == 8) goto L11;
    L34:
        return false;
    L11:
        if ((r6.getSource() & 2) == 0) goto L21;
        if (this.mLayout.canScrollVertically() == false) goto L15;
        float r0 = -r6.getAxisValue(9);
    L17:
        if (this.mLayout.canScrollHorizontally() == false) goto L19;
        float r3 = r6.getAxisValue(10);
    L30:
        if (r0 == 0.0f) goto L32;
    L33:
        scrollByInternal((int) (r3 * this.mScaledHorizontalScrollFactor), (int) (r0 * this.mScaledVerticalScrollFactor), r6);
        goto L34
    L32:
        if (r3 == 0.0f) goto L34;
    L19:
        r3 = 0.0f;
        goto L30
    L15:
        r0 = 0.0f;
        goto L17
    L21:
        if ((r6.getSource() & 4194304) == 0) goto L28;
        float r02 = r6.getAxisValue(26);
        if (this.mLayout.canScrollVertically() == false) goto L26;
        r0 = -r02;
        goto L19
    L26:
        if (this.mLayout.canScrollHorizontally() == false) goto L28;
        r3 = r02;
        r0 = 0.0f;
    L28:
        r0 = 0.0f;
        r3 = 0.0f;
        goto L30
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r9) {
        if (this.mLayoutSuppressed == false) goto L5;
        return false;
    L5:
        this.mInterceptingOnItemTouchListener = null;
        if (findInterceptingOnItemTouchListener(r9) == false) goto L9;
        cancelScroll();
        return true;
    L9:
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L12;
        return false;
    L12:
        boolean r02 = r0.canScrollHorizontally();
        boolean r3 = this.mLayout.canScrollVertically();
        if (this.mVelocityTracker != null) goto L15;
        this.mVelocityTracker = VelocityTracker.obtain();
    L15:
        this.mVelocityTracker.addMovement(r9);
        int r4 = r9.getActionMasked();
        int r5 = r9.getActionIndex();
        if (r4 == 0) goto L49;
        if (r4 == 1) goto L47;
        if (r4 != 2) goto L20;
        int r42 = r9.findPointerIndex(this.mScrollPointerId);
        if (r42 >= 0) goto L33;
        Log.e(TAG, "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
        return false;
    L33:
        int r52 = (int) (r9.getX(r42) + 0.5f);
        int r92 = (int) (r9.getY(r42) + 0.5f);
        if (this.mScrollState == 1) goto L59;
        int r43 = r52 - this.mInitialTouchX;
        int r6 = r92 - this.mInitialTouchY;
        if (r02 != 0) goto L38;
    L40:
        boolean r03 = false;
    L41:
        if (r3 == true) goto L43;
    L45:
        if (r03 == false) goto L59;
        setScrollState(1);
        goto L59
    L43:
        if (Math.abs(r6) <= this.mTouchSlop) goto L45;
        this.mLastTouchY = r92;
        r03 = true;
        goto L45
    L38:
        if (Math.abs(r43) <= this.mTouchSlop) goto L40;
        this.mLastTouchX = r52;
        r03 = true;
    L59:
        if (this.mScrollState != 1) goto L61;
        return true;
    L61:
        return false;
    L20:
        if (r4 != 3) goto L22;
        cancelScroll();
        goto L59
    L22:
        if (r4 != 5) goto L24;
        this.mScrollPointerId = r9.getPointerId(r5);
        int r04 = (int) (r9.getX(r5) + 0.5f);
        this.mLastTouchX = r04;
        this.mInitialTouchX = r04;
        int r93 = (int) (r9.getY(r5) + 0.5f);
        this.mLastTouchY = r93;
        this.mInitialTouchY = r93;
        goto L59
    L24:
        if (r4 != 6) goto L59;
        onPointerUp(r9);
        goto L59
    L47:
        this.mVelocityTracker.clear();
        stopNestedScroll(0);
        goto L59
    L49:
        if (this.mIgnoreMotionEventTillDown == false) goto L51;
        this.mIgnoreMotionEventTillDown = false;
    L51:
        this.mScrollPointerId = r9.getPointerId(0);
        int r44 = (int) (r9.getX() + 0.5f);
        this.mLastTouchX = r44;
        this.mInitialTouchX = r44;
        int r94 = (int) (r9.getY() + 0.5f);
        this.mLastTouchY = r94;
        this.mInitialTouchY = r94;
        if (this.mScrollState != 2) goto L54;
        getParent().requestDisallowInterceptTouchEvent(true);
        setScrollState(1);
        stopNestedScroll(1);
    L54:
        int[] r95 = this.mNestedOffsets;
        r95[1] = 0;
        r95[0] = 0;
        int r05 = r02;
        if (r3 == false) goto L57;
        r05 = (r02 ? 1 : 0) | 2;
    L57:
        startNestedScroll(r05, 0);
        goto L59
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        TraceCompat.beginSection(TRACE_ON_LAYOUT_TAG);
        dispatchLayout();
        TraceCompat.endSection();
        this.mFirstLayoutComplete = true;
    }

    @Override // android.view.View
    public void onMeasure(int r7, int r8) {
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L7;
        defaultOnMeasure(r7, r8);
        return;
    L7:
        if (r0.isAutoMeasureEnabled() == false) goto L23;
        int r02 = View.MeasureSpec.getMode(r7);
        int r2 = View.MeasureSpec.getMode(r8);
        this.mLayout.onMeasure(this.mRecycler, this.mState, r7, r8);
        if (r02 != 1073741824) goto L13;
        if (r2 != 1073741824) goto L13;
        return;
    L13:
        if (this.mAdapter != null) goto L16;
        return;
    L16:
        if (this.mState.mLayoutStep != 1) goto L18;
        dispatchLayoutStep1();
    L18:
        this.mLayout.setMeasureSpecs(r7, r8);
        this.mState.mIsMeasuring = true;
        dispatchLayoutStep2();
        this.mLayout.setMeasuredDimensionFromChildren(r7, r8);
        if (this.mLayout.shouldMeasureTwice() == false) goto L43;
        this.mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
        this.mState.mIsMeasuring = true;
        dispatchLayoutStep2();
        this.mLayout.setMeasuredDimensionFromChildren(r7, r8);
        return;
    L43:
        return;
    L23:
        if (this.mHasFixedSize == false) goto L27;
        this.mLayout.onMeasure(this.mRecycler, this.mState, r7, r8);
        return;
    L27:
        if (this.mAdapterUpdateDuringMeasure == false) goto L34;
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        onExitLayoutOrScroll();
        State r03 = this.mState;
        if (r03.mRunPredictiveAnimations == false) goto L31;
        r03.mInPreLayout = true;
    L32:
        this.mAdapterUpdateDuringMeasure = false;
        stopInterceptRequestLayout(false);
    L37:
        Adapter r04 = this.mAdapter;
        State r1 = this.mState;
        if (r04 == null) goto L40;
        r1.mItemCount = r04.getItemCount();
    L41:
        startInterceptRequestLayout();
        this.mLayout.onMeasure(this.mRecycler, this.mState, r7, r8);
        stopInterceptRequestLayout(false);
        this.mState.mInPreLayout = false;
        return;
    L40:
        r1.mItemCount = 0;
        goto L41
    L31:
        this.mAdapterHelper.consumeUpdatesInOnePass();
        this.mState.mInPreLayout = false;
        goto L32
    L34:
        if (this.mState.mRunPredictiveAnimations == false) goto L37;
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int r2, Rect r3) {
        if (isComputingLayout() == false) goto L7;
        return false;
    L7:
        return super.onRequestFocusInDescendants(r2, r3);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable r2) {
        if ((r2 instanceof SavedState) == true) goto L6;
        super.onRestoreInstanceState(r2);
        return;
    L6:
        SavedState r22 = (SavedState) r2;
        this.mPendingSavedState = r22;
        super.onRestoreInstanceState(r22.getSuperState());
        LayoutManager r23 = this.mLayout;
        if (r23 == null) goto L12;
        Parcelable r1 = this.mPendingSavedState.mLayoutState;
        if (r1 == null) goto L13;
        r23.onRestoreInstanceState(r1);
        return;
    L13:
        return;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState r0 = new SavedState(super.onSaveInstanceState());
        SavedState r1 = this.mPendingSavedState;
        if (r1 == null) goto L6;
        r0.copyFrom(r1);
        return r0;
    L6:
        LayoutManager r2 = this.mLayout;
        if (r2 == null) goto L10;
        r0.mLayoutState = r2.onSaveInstanceState();
        return r0;
    L10:
        r0.mLayoutState = null;
        return r0;
    }

    public void onScrollStateChanged(int r1) {
    }

    public void onScrolled(int r1, int r2) {
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        if (r1 != r3) goto L7;
        if (r2 != r4) goto L7;
        return;
    L7:
        invalidateGlows();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r14) {
        int r1 = 0;
        if (this.mLayoutSuppressed == false) goto L5;
    L99:
        return false;
    L5:
        if (this.mIgnoreMotionEventTillDown == true) goto L99;
        if (dispatchToOnItemTouchListeners(r14) == false) goto L11;
        cancelScroll();
        return true;
    L11:
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L14;
        return false;
    L14:
        boolean r02 = r0.canScrollHorizontally();
        boolean r3 = this.mLayout.canScrollVertically();
        if (this.mVelocityTracker != null) goto L17;
        this.mVelocityTracker = VelocityTracker.obtain();
    L17:
        MotionEvent r4 = MotionEvent.obtain(r14);
        int r5 = r14.getActionMasked();
        int r6 = r14.getActionIndex();
        if (r5 != 0) goto L20;
        int[] r7 = this.mNestedOffsets;
        r7[1] = 0;
        r7[0] = 0;
    L20:
        int[] r72 = this.mNestedOffsets;
        r4.offsetLocation(r72[0], r72[1]);
        if (r5 == 0) goto L92;
        if (r5 != 1) goto L24;
        this.mVelocityTracker.addMovement(r4);
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxFlingVelocity);
        if (r02 == 0) goto L80;
        float r142 = -this.mVelocityTracker.getXVelocity(this.mScrollPointerId);
    L81:
        if (r3 == false) goto L83;
        float r03 = -this.mVelocityTracker.getYVelocity(this.mScrollPointerId);
    L85:
        if (r142 != 0.0f) goto L89;
        if (r03 != 0.0f) goto L89;
    L90:
        setScrollState(0);
    L91:
        resetScroll();
    L97:
        r4.recycle();
        return true;
    L89:
        if (fling((int) r142, (int) r03) == true) goto L91;
    L83:
        r03 = 0.0f;
        goto L85
    L80:
        r142 = 0.0f;
        goto L81
    L24:
        if (r5 != 2) goto L26;
        int r52 = r14.findPointerIndex(this.mScrollPointerId);
        if (r52 >= 0) goto L39;
        Log.e(TAG, "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
        return false;
    L39:
        int r62 = (int) (r14.getX(r52) + 0.5f);
        int r143 = (int) (r14.getY(r52) + 0.5f);
        int r8 = this.mLastTouchX - r62;
        int r9 = this.mLastTouchY - r143;
        int[] r10 = this.mReusableIntPair;
        r10[0] = 0;
        r10[1] = 0;
        RecyclerView r53 = this;
        if (dispatchNestedPreScroll(r8, r9, r10, this.mScrollOffset, 0) == false) goto L43;
        int[] r13 = r53.mReusableIntPair;
        r8 = r8 - r13[0];
        r9 = r9 - r13[1];
        int[] r132 = r53.mScrollOffset;
        r4.offsetLocation(r132[0], r132[1]);
        int[] r133 = r53.mNestedOffsets;
        int r73 = r133[0];
        int[] r102 = r53.mScrollOffset;
        r133[0] = r73 + r102[0];
        r133[1] = r133[1] + r102[1];
    L43:
        if (r53.mScrollState == 1) goto L61;
        if (r02 == 0) goto L51;
        int r134 = Math.abs(r8);
        int r74 = r53.mTouchSlop;
        if (r134 <= r74) goto L51;
        if (r8 <= 0) goto L49;
        r8 = r8 - r74;
    L50:
        boolean r135 = true;
    L52:
        if (r3 == false) goto L59;
        int r75 = Math.abs(r9);
        int r103 = r53.mTouchSlop;
        if (r75 <= r103) goto L59;
        if (r9 <= 0) goto L57;
        r9 = r9 - r103;
    L58:
        r135 = true;
        goto L59
    L57:
        r9 = r9 + r103;
    L59:
        if (r135 == false) goto L61;
        r53.setScrollState(1);
        goto L61
    L49:
        r8 = r8 + r74;
    L51:
        r135 = false;
    L61:
        int r136 = r53.mScrollState;
        RecyclerView r54 = r53;
        if (r136 != 1) goto L96;
        int[] r137 = r53.mScrollOffset;
        r53.mLastTouchX = r62 - r137[0];
        r53.mLastTouchY = r143 - r137[1];
        if (r02 == 0) goto L66;
        int r138 = r8;
    L67:
        if (r3 == false) goto L70;
        r1 = r9;
    L70:
        if (r53.scrollByInternal(r138, r1, r4) == false) goto L72;
        r53.getParent().requestDisallowInterceptTouchEvent(true);
    L72:
        GapWorker r139 = r53.mGapWorker;
        r54 = r53;
        r54 = r53;
        if (r139 == null) goto L96;
        if (r8 != 0) goto L76;
        if (r9 == 0) goto L96;
    L76:
        r139.postFromTraversal(r53, r8, r9);
        r54 = r53;
        goto L96
    L66:
        r138 = 0;
    L96:
        r54.mVelocityTracker.addMovement(r4);
        goto L97
    L26:
        if (r5 != 3) goto L28;
        cancelScroll();
    L31:
        r54 = this;
        goto L96
    L28:
        if (r5 != 5) goto L30;
        this.mScrollPointerId = r14.getPointerId(r6);
        int r04 = (int) (r14.getX(r6) + 0.5f);
        this.mLastTouchX = r04;
        this.mInitialTouchX = r04;
        int r144 = (int) (r14.getY(r6) + 0.5f);
        this.mLastTouchY = r144;
        this.mInitialTouchY = r144;
        goto L31
    L30:
        if (r5 != 6) goto L31;
        onPointerUp(r14);
        goto L31
    L92:
        RecyclerView r55 = this;
        r55.mScrollPointerId = r14.getPointerId(0);
        int r1310 = (int) (r14.getX() + 0.5f);
        r55.mLastTouchX = r1310;
        r55.mInitialTouchX = r1310;
        int r1311 = (int) (r14.getY() + 0.5f);
        r55.mLastTouchY = r1311;
        r55.mInitialTouchY = r1311;
        int r05 = r02;
        if (r3 == false) goto L95;
        r05 = (r02 ? 1 : 0) | 2;
    L95:
        r55.startNestedScroll(r05, 0);
        r54 = r55;
        goto L96
    }

    public void postAnimationRunner() {
        if (this.mPostedAnimatorRunner == false) goto L5;
        return;
    L5:
        if (this.mIsAttached == false) goto L9;
        ViewCompat.postOnAnimation(this, this.mItemAnimatorRunner);
        this.mPostedAnimatorRunner = true;
        return;
    }

    public void prepareForFastScroll() {
        stopScroll();
        removeAndRecycleViews();
        this.mAdapterHelper.reset();
        Recycler r0 = this.mRecycler;
        Adapter r1 = this.mAdapter;
        r0.onAdapterChanged(r1, r1, false);
        this.mState.mStructureChanged = true;
        this.mChildHelper.removeAllViewsUnfiltered();
        this.mRecycler.updateViewCacheSize();
    }

    public void processDataSetCompletelyChanged(boolean r2) {
        this.mDispatchItemsChangedEvent = r2 | this.mDispatchItemsChangedEvent;
        this.mDataSetHasChangedAfterLayout = true;
        markKnownViewsInvalid();
    }

    public void recordAnimationInfoIfBouncedHiddenView(ViewHolder r4, ItemAnimator.ItemHolderInfo r5) {
        r4.setFlags(0, 8192);
        if (this.mState.mTrackOldChangeHolders == true) goto L5;
    L11:
        this.mViewInfoStore.addToPreLayout(r4, r5);
        return;
    L5:
        if (r4.isUpdated() == false) goto L11;
        if (r4.isRemoved() == true) goto L11;
        if (r4.shouldIgnore() == true) goto L11;
        long r0 = getChangedHolderKey(r4);
        this.mViewInfoStore.addToOldChangeHolders(r0, r4);
        goto L11
    }

    public void removeAndRecycleViews() {
        ItemAnimator r0 = this.mItemAnimator;
        if (r0 == null) goto L5;
        r0.endAnimations();
    L5:
        LayoutManager r02 = this.mLayout;
        if (r02 == null) goto L8;
        r02.removeAndRecycleAllViews(this.mRecycler);
        this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
    L8:
        this.mRecycler.clear();
    }

    public boolean removeAnimatingView(View r3) {
        startInterceptRequestLayout();
        boolean r0 = this.mChildHelper.removeViewIfHidden(r3);
        if (r0 == false) goto L5;
        ViewHolder r32 = getChildViewHolderInt(r3);
        this.mRecycler.unscrapView(r32);
        this.mRecycler.recycleViewHolderInternal(r32);
    L5:
        stopInterceptRequestLayout(!r0);
        return r0;
    }

    @Override // android.view.ViewGroup
    public void removeDetachedView(View r3, boolean r4) {
        ViewHolder r0 = getChildViewHolderInt(r3);
        if (r0 != null) goto L5;
    L12:
        r3.clearAnimation();
        dispatchChildDetached(r3);
        super.removeDetachedView(r3, r4);
        return;
    L5:
        if (r0.isTmpDetached() == false) goto L8;
        r0.clearTmpDetachFlag();
        goto L12
    L8:
        if (r0.shouldIgnore() == true) goto L12;
        throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + r0 + exceptionLabel());
    }

    public void removeItemDecoration(ItemDecoration r3) {
        LayoutManager r0 = this.mLayout;
        if (r0 == null) goto L5;
        r0.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
    L5:
        this.mItemDecorations.remove(r3);
        if (this.mItemDecorations.isEmpty() == true) goto L8;
    L12:
        markItemDecorInsetsDirty();
        requestLayout();
        return;
    L8:
        if (getOverScrollMode() != 2) goto L10;
        boolean r32 = true;
    L11:
        setWillNotDraw(r32);
        goto L12
    L10:
        r32 = false;
        goto L11
    }

    public void removeItemDecorationAt(int r3) {
        int r0 = getItemDecorationCount();
        if (r3 < 0) goto L8;
        if (r3 >= r0) goto L8;
        removeItemDecoration(getItemDecorationAt(r3));
        return;
    L8:
        throw new IndexOutOfBoundsException(r3 + " is an invalid index for size " + r0);
    }

    public void removeOnChildAttachStateChangeListener(OnChildAttachStateChangeListener r1) {
        List<OnChildAttachStateChangeListener> r0 = this.mOnChildAttachStateListeners;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.remove(r1);
    }

    public void removeOnItemTouchListener(OnItemTouchListener r2) {
        this.mOnItemTouchListeners.remove(r2);
        if (this.mInterceptingOnItemTouchListener != r2) goto L6;
        this.mInterceptingOnItemTouchListener = null;
        return;
    }

    public void removeOnScrollListener(OnScrollListener r1) {
        List<OnScrollListener> r0 = this.mScrollListeners;
        if (r0 == null) goto L6;
        r0.remove(r1);
        return;
    }

    public void repositionShadowingViews() {
        int r0 = this.mChildHelper.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L14;
        View r2 = this.mChildHelper.getChildAt(r1);
        ViewHolder r3 = getChildViewHolder(r2);
        if (r3 == null) goto L13;
        ViewHolder r32 = r3.mShadowingHolder;
        if (r32 == null) goto L13;
        View r33 = r32.itemView;
        int r4 = r2.getLeft();
        int r22 = r2.getTop();
        if (r4 == r33.getLeft()) goto L11;
    L12:
        r33.layout(r4, r22, r33.getWidth() + r4, r33.getHeight() + r22);
        goto L13
    L11:
        if (r22 != r33.getTop()) goto L12;
    L13:
        r1 = r1 + 1;
        goto L3
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View r3, View r4) {
        if (this.mLayout.onRequestChildFocus(this, this.mState, r3, r4) == true) goto L6;
        if (r4 == null) goto L6;
        requestChildOnScreen(r3, r4);
    L6:
        super.requestChildFocus(r3, r4);
    }

    public void requestChildOnScreen(View r12, View r13) {
        if (r13 == null) goto L4;
        View r0 = r13;
    L5:
        this.mTempRect.set(0, 0, r0.getWidth(), r0.getHeight());
        ViewGroup.LayoutParams r02 = r0.getLayoutParams();
        if ((r02 instanceof LayoutParams) == false) goto L10;
        LayoutParams r03 = (LayoutParams) r02;
        if (r03.mInsetsDirty == true) goto L10;
        Rect r04 = r03.mDecorInsets;
        Rect r1 = this.mTempRect;
        r1.left -= r04.left;
        r1.right += r04.right;
        r1.top -= r04.top;
        r1.bottom += r04.bottom;
    L10:
        if (r13 == null) goto L12;
        offsetDescendantRectToMyCoords(r13, this.mTempRect);
        offsetRectIntoDescendantCoords(r12, this.mTempRect);
    L12:
        LayoutManager r5 = this.mLayout;
        Rect r8 = this.mTempRect;
        boolean r9 = !this.mFirstLayoutComplete;
        if (r13 != null) goto L16;
        boolean r10 = true;
    L17:
        r5.requestChildRectangleOnScreen(this, r12, r8, r9, r10);
        return;
    L16:
        r10 = false;
        goto L17
    L4:
        r0 = r12;
        goto L5
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View r2, Rect r3, boolean r4) {
        return this.mLayout.requestChildRectangleOnScreen(this, r2, r3, r4);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean r4) {
        int r0 = this.mOnItemTouchListeners.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.mOnItemTouchListeners.get(r1).onRequestDisallowInterceptTouchEvent(r4);
        r1 = r1 + 1;
        goto L3
    L5:
        super.requestDisallowInterceptTouchEvent(r4);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.mInterceptRequestLayoutDepth == 0) goto L5;
    L8:
        this.mLayoutWasDefered = true;
        return;
    L5:
        if (this.mLayoutSuppressed == true) goto L8;
        super.requestLayout();
    }

    public void saveOldPositions() {
        int r0 = this.mChildHelper.getUnfilteredChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        ViewHolder r2 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(r1));
        if (r2.shouldIgnore() == true) goto L7;
        r2.saveOldPosition();
    L7:
        r1 = r1 + 1;
        goto L3
    }

    @Override // android.view.View
    public void scrollBy(int r4, int r5) {
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L7;
        Log.e(TAG, "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        return;
    L7:
        if (this.mLayoutSuppressed == true) goto L23;
        boolean r02 = r0.canScrollHorizontally();
        boolean r1 = this.mLayout.canScrollVertically();
        if (r02 == true) goto L15;
        if (r1 == true) goto L15;
        return;
    L15:
        if (r02 == true) goto L18;
        r4 = 0;
    L18:
        if (r1 == true) goto L21;
        r5 = 0;
    L21:
        scrollByInternal(r4, r5, null);
        return;
    }

    public boolean scrollByInternal(int r16, int r17, MotionEvent r18) {
        consumePendingUpdateOperations();
        if (this.mAdapter == null) goto L5;
        int[] r1 = this.mReusableIntPair;
        r1[0] = 0;
        r1[1] = 0;
        scrollStep(r16, r17, r1);
        int[] r12 = this.mReusableIntPair;
        int r2 = r12[0];
        int r13 = r12[1];
        int r3 = r16 - r2;
        int r4 = r17 - r13;
        int r22 = r13;
        int r14 = r2;
    L7:
        if (this.mItemDecorations.isEmpty() == true) goto L9;
        invalidate();
    L9:
        int[] r7 = this.mReusableIntPair;
        r7[0] = 0;
        r7[1] = 0;
        dispatchNestedScroll(r14, r22, r3, r4, this.mScrollOffset, 0, r7);
        int[] r5 = this.mReusableIntPair;
        int r32 = r3 - r5[0];
        int r42 = r4 - r5[1];
        int r52 = this.mLastTouchX;
        int[] r6 = this.mScrollOffset;
        int r72 = r6[0];
        this.mLastTouchX = r52 - r72;
        int r53 = this.mLastTouchY;
        int r62 = r6[1];
        this.mLastTouchY = r53 - r62;
        if (r18 == null) goto L12;
        r18.offsetLocation(r72, r62);
    L12:
        int[] r54 = this.mNestedOffsets;
        int r63 = r54[0];
        int[] r73 = this.mScrollOffset;
        r54[0] = r63 + r73[0];
        r54[1] = r54[1] + r73[1];
        if (getOverScrollMode() == 2) goto L19;
        if (r18 != null) goto L16;
    L18:
        considerReleasingGlowsOnScroll(r16, r17);
        goto L19
    L16:
        if (MotionEventCompat.isFromSource(r18, 8194) == true) goto L18;
        pullGlows(r18.getX(), r32, r18.getY(), r42);
    L19:
        if (r14 != 0) goto L21;
        if (r22 != 0) goto L21;
    L23:
        if (awakenScrollBars() == true) goto L25;
        invalidate();
    L25:
        if (r14 != 0) goto L29;
        if (r22 != 0) goto L29;
        return false;
    L29:
        return true;
    L21:
        dispatchOnScrolled(r14, r22);
        goto L23
    L5:
        r14 = 0;
        r22 = 0;
        r3 = 0;
        r4 = 0;
        goto L7
    }

    public void scrollStep(int r5, int r6, int[] r7) {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        TraceCompat.beginSection(TRACE_SCROLL_TAG);
        fillRemainingScrollValues(this.mState);
        if (r5 == 0) goto L5;
        int r52 = this.mLayout.scrollHorizontallyBy(r5, this.mRecycler, this.mState);
    L6:
        if (r6 == 0) goto L8;
        int r62 = this.mLayout.scrollVerticallyBy(r6, this.mRecycler, this.mState);
    L9:
        TraceCompat.endSection();
        repositionShadowingViews();
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        if (r7 == null) goto L13;
        r7[0] = r52;
        r7[1] = r62;
        return;
    L13:
        return;
    L8:
        r62 = 0;
        goto L9
    L5:
        r52 = 0;
        goto L6
    }

    @Override // android.view.View
    public void scrollTo(int r1, int r2) {
        Log.w(TAG, "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollToPosition(int r2) {
        if (this.mLayoutSuppressed == false) goto L5;
        return;
    L5:
        stopScroll();
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L9;
        Log.e(TAG, "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        return;
    L9:
        r0.scrollToPosition(r2);
        awakenScrollBars();
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent r2) {
        if (shouldDeferAccessibilityEvent(r2) == false) goto L5;
        return;
    L5:
        super.sendAccessibilityEventUnchecked(r2);
    }

    public void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate r1) {
        this.mAccessibilityDelegate = r1;
        ViewCompat.setAccessibilityDelegate(this, r1);
    }

    public void setAdapter(Adapter r3) {
        setLayoutFrozen(false);
        setAdapterInternal(r3, false, true);
        processDataSetCompletelyChanged(false);
        requestLayout();
    }

    public void setAdditionalDebugInfo(String r1) {
        this.moreInfo = r1;
    }

    public void setBottomGlowOffset(int r1) {
        this.bottomGlowOffset = r1;
    }

    public void setChildDrawingOrderCallback(ChildDrawingOrderCallback r2) {
        if (r2 != this.mChildDrawingOrderCallback) goto L5;
        return;
    L5:
        this.mChildDrawingOrderCallback = r2;
        if (r2 == null) goto L8;
        boolean r22 = true;
    L9:
        setChildrenDrawingOrderEnabled(r22);
        return;
    L8:
        r22 = false;
        goto L9
    }

    public boolean setChildImportantForAccessibilityInternal(ViewHolder r2, int r3) {
        if (isComputingLayout() == false) goto L6;
        r2.mPendingAccessibilityState = r3;
        this.mPendingAccessibilityImportanceChange.add(r2);
        return false;
    L6:
        ViewCompat.setImportantForAccessibility(r2.itemView, r3);
        return true;
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean r2) {
        if (r2 == this.mClipToPadding) goto L5;
        invalidateGlows();
    L5:
        this.mClipToPadding = r2;
        super.setClipToPadding(r2);
        if (this.mFirstLayoutComplete == false) goto L9;
        requestLayout();
        return;
    }

    public void setEdgeEffectFactory(EdgeEffectFactory r1) {
        Preconditions.checkNotNull(r1);
        this.mEdgeEffectFactory = r1;
        invalidateGlows();
    }

    public void setGlowColor(int r1) {
        this.glowColor = Integer.valueOf(r1);
    }

    public void setHasFixedSize(boolean r1) {
        this.mHasFixedSize = r1;
    }

    public void setItemAnimator(ItemAnimator r3) {
        ItemAnimator r0 = this.mItemAnimator;
        if (r0 == null) goto L5;
        r0.endAnimations();
        this.mItemAnimator.setListener(null);
    L5:
        this.mItemAnimator = r3;
        if (r3 == null) goto L9;
        r3.setListener(this.mItemAnimatorListener);
        return;
    }

    public void setItemViewCacheSize(int r1) {
        this.mRecycler.setViewCacheSize(r1);
    }

    @Deprecated
    public void setLayoutFrozen(boolean r1) {
        suppressLayout(r1);
    }

    public void setLayoutManager(LayoutManager r3) {
        if (r3 != this.mLayout) goto L5;
        return;
    L5:
        stopScroll();
        if (this.mLayout == null) goto L14;
        ItemAnimator r0 = this.mItemAnimator;
        if (r0 == null) goto L10;
        r0.endAnimations();
    L10:
        this.mLayout.removeAndRecycleAllViews(this.mRecycler);
        this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        this.mRecycler.clear();
        if (this.mIsAttached == false) goto L13;
        this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
    L13:
        this.mLayout.setRecyclerView(null);
        this.mLayout = null;
    L15:
        this.mChildHelper.removeAllViewsUnfiltered();
        this.mLayout = r3;
        if (r3 != null) goto L18;
    L24:
        this.mRecycler.updateViewCacheSize();
        requestLayout();
        return;
    L18:
        if (r3.mRecyclerView != null) goto L22;
        r3.setRecyclerView(this);
        if (this.mIsAttached == false) goto L24;
        this.mLayout.dispatchAttachedToWindow(this);
        goto L24
    L22:
        StringBuilder r2 = new StringBuilder("LayoutManager ");
        r2.append(r3);
        Utils$$ExternalSyntheticBUOutline1.m(r2, " is already attached to a RecyclerView:", r3.mRecyclerView.exceptionLabel());
        return;
    L14:
        this.mRecycler.clear();
        goto L15
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition r1) {
        if (r1 != null) goto L5;
        super.setLayoutTransition(null);
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean r1) {
        getScrollingChildHelper().setNestedScrollingEnabled(r1);
    }

    public void setOnFlingListener(OnFlingListener r1) {
        this.mOnFlingListener = r1;
    }

    @Deprecated
    public void setOnScrollListener(OnScrollListener r1) {
        this.mScrollListener = r1;
    }

    public void setPreserveFocusAfterLayout(boolean r1) {
        this.mPreserveFocusAfterLayout = r1;
    }

    public void setRecycledViewPool(RecycledViewPool r1) {
        this.mRecycler.setRecycledViewPool(r1);
    }

    public void setRecyclerListener(RecyclerListener r1) {
    }

    public void setScrollState(int r2) {
        if (r2 != this.mScrollState) goto L5;
        return;
    L5:
        this.mScrollState = r2;
        if (r2 == 2) goto L8;
        stopScrollersInternal();
    L8:
        dispatchOnScrollStateChanged(r2);
    }

    public void setScrollingTouchSlop(int r4) {
        ViewConfiguration r0 = ViewConfiguration.get(getContext());
        if (r4 != 0) goto L5;
    L9:
        this.mTouchSlop = r0.getScaledTouchSlop();
        return;
    L5:
        if (r4 == 1) goto L7;
        Log.w(TAG, "setScrollingTouchSlop(): bad argument constant " + r4 + "; using default value");
        goto L9
    L7:
        this.mTouchSlop = r0.getScaledPagingTouchSlop();
    }

    public void setTopGlowOffset(int r1) {
        this.topGlowOffset = r1;
    }

    public void setViewCacheExtension(ViewCacheExtension r1) {
        this.mRecycler.setViewCacheExtension(r1);
    }

    public boolean shouldDeferAccessibilityEvent(AccessibilityEvent r3) {
        int r1 = 0;
        if (isComputingLayout() == false) goto L12;
        if (r3 == null) goto L6;
        int r32 = AccessibilityEventCompat.getContentChangeTypes(r3);
    L7:
        if (r32 == 0) goto L10;
        r1 = r32;
    L10:
        this.mEatenAccessibilityChangeFlags |= r1;
        return true;
    L6:
        r32 = 0;
        goto L7
    L12:
        return false;
    }

    public void smoothScrollBy(int r2, int r3) {
        smoothScrollBy(r2, r3, null);
    }

    public void smoothScrollBy(int r3, int r4, int r5, Interpolator r6) {
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L7;
        Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        return;
    L7:
        if (this.mLayoutSuppressed == false) goto L10;
        return;
    L10:
        if (r0.canScrollHorizontally() == true) goto L13;
        r3 = 0;
    L13:
        if (this.mLayout.canScrollVertically() == true) goto L15;
        r4 = 0;
    L15:
        if (r3 != 0) goto L19;
        if (r4 != 0) goto L19;
        return;
    L19:
        this.mViewFlinger.smoothScrollBy(r3, r4, r5, r6);
    }

    public void smoothScrollBy(int r3, int r4, Interpolator r5) {
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L7;
        Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        return;
    L7:
        if (this.mLayoutSuppressed == false) goto L10;
        return;
    L10:
        if (r0.canScrollHorizontally() == true) goto L13;
        r3 = 0;
    L13:
        if (this.mLayout.canScrollVertically() == true) goto L15;
        r4 = 0;
    L15:
        if (r3 != 0) goto L19;
        if (r4 != 0) goto L19;
        return;
    L19:
        this.mViewFlinger.smoothScrollBy(r3, r4, Integer.MIN_VALUE, r5);
    }

    public void smoothScrollToPosition(int r3) {
        if (this.mLayoutSuppressed == false) goto L5;
        return;
    L5:
        LayoutManager r0 = this.mLayout;
        if (r0 != null) goto L9;
        Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        return;
    L9:
        r0.smoothScrollToPosition(this, this.mState, r3);
    }

    public void startInterceptRequestLayout() {
        int r0 = this.mInterceptRequestLayoutDepth + 1;
        this.mInterceptRequestLayoutDepth = r0;
        if (r0 == 1) goto L5;
        return;
    L5:
        if (this.mLayoutSuppressed == true) goto L9;
        this.mLayoutWasDefered = false;
        return;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int r1) {
        return getScrollingChildHelper().startNestedScroll(r1);
    }

    public boolean startNestedScroll(int r1, int r2) {
        return getScrollingChildHelper().startNestedScroll(r1, r2);
    }

    public void stopInterceptRequestLayout(boolean r4) {
        if (this.mInterceptRequestLayoutDepth >= 1) goto L6;
        this.mInterceptRequestLayoutDepth = 1;
    L6:
        if (r4 == true) goto L11;
        if (this.mLayoutSuppressed == true) goto L11;
        this.mLayoutWasDefered = false;
    L11:
        if (this.mInterceptRequestLayoutDepth != 1) goto L25;
        if (r4 == false) goto L23;
        if (this.mLayoutWasDefered == false) goto L23;
        if (this.mLayoutSuppressed == true) goto L23;
        if (this.mLayout == null) goto L23;
        if (this.mAdapter == null) goto L23;
        dispatchLayout();
    L23:
        if (this.mLayoutSuppressed == true) goto L25;
        this.mLayoutWasDefered = false;
    L25:
        this.mInterceptRequestLayoutDepth--;
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll();
    }

    public void stopNestedScroll(int r1) {
        getScrollingChildHelper().stopNestedScroll(r1);
    }

    public void stopScroll() {
        setScrollState(0);
        stopScrollersInternal();
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean r10) {
        if (r10 == this.mLayoutSuppressed) goto L17;
        assertNotInLayoutOrScroll("Do not suppressLayout in layout or scroll");
        if (r10 == true) goto L15;
        this.mLayoutSuppressed = false;
        if (this.mLayoutWasDefered == true) goto L9;
    L13:
        this.mLayoutWasDefered = false;
        return;
    L9:
        if (this.mLayout == null) goto L13;
        if (this.mAdapter == null) goto L13;
        requestLayout();
        goto L13
    L15:
        long r1 = SystemClock.uptimeMillis();
        onTouchEvent(MotionEvent.obtain(r1, r1, 3, 0.0f, 0.0f, 0));
        this.mLayoutSuppressed = true;
        this.mIgnoreMotionEventTillDown = true;
        stopScroll();
        return;
    }

    public void swapAdapter(Adapter r2, boolean r3) {
        setLayoutFrozen(false);
        setAdapterInternal(r2, true, r3);
        processDataSetCompletelyChanged(true);
        requestLayout();
    }

    public void viewRangeUpdate(int r7, int r8, Object r9) {
        int r0 = this.mChildHelper.getUnfilteredChildCount();
        int r1 = r7 + r8;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L14;
        View r3 = this.mChildHelper.getUnfilteredChildAt(r2);
        ViewHolder r4 = getChildViewHolderInt(r3);
        if (r4 == null) goto L13;
        if (r4.shouldIgnore() == true) goto L13;
        int r5 = r4.mPosition;
        if (r5 < r7) goto L13;
        if (r5 >= r1) goto L13;
        r4.addFlags(2);
        r4.addChangePayload(r9);
        ((LayoutParams) r3.getLayoutParams()).mInsetsDirty = true;
    L13:
        r2 = r2 + 1;
        goto L3
    L14:
        this.mRecycler.viewRangeUpdate(r7, r8);
    }
}
