package androidx.viewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.gms.cast.MediaError;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import okhttp3.OkHttpClient$Builder$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.tgnet.TLObject;

/* loaded from: classes.dex */
public class ViewPager extends ViewGroup {
    private static final Comparator<ItemInfo> COMPARATOR = null;
    static final int[] LAYOUT_ATTRS = null;
    private static final Interpolator sInterpolator = null;
    private static final ViewPositionComparator sPositionComparator = null;
    private int mActivePointerId;
    PagerAdapter mAdapter;
    private List<OnAdapterChangeListener> mAdapterChangeListeners;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsScrollStarted;
    private boolean mIsUnableToDrag;
    private final ArrayList<ItemInfo> mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    private EdgeEffect mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit;
    private OnPageChangeListener mOnPageChangeListener;
    private List<OnPageChangeListener> mOnPageChangeListeners;
    private int mPageMargin;
    private PageTransformer mPageTransformer;
    private int mPageTransformerLayerType;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private EdgeEffect mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private final ItemInfo mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    /* renamed from: androidx.viewpager.widget.ViewPager$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static class AnonymousClass1 implements Comparator<ItemInfo> {
        public AnonymousClass1() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(ItemInfo r1, ItemInfo r2) {
            return r1.position - r2.position;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(ItemInfo r1, ItemInfo r2) {
            return compare2(r1, r2);
        }
    }

    /* renamed from: androidx.viewpager.widget.ViewPager$2, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static class AnonymousClass2 implements Interpolator {
        public AnonymousClass2() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float r2) {
            float r22 = r2 - 1.0f;
            return ((((r22 * r22) * r22) * r22) * r22) + 1.0f;
        }
    }

    /* renamed from: androidx.viewpager.widget.ViewPager$3, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ ViewPager this$0;

        public AnonymousClass3(ViewPager r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.setScrollState(0);
            this.this$0.populate();
        }
    }

    /* renamed from: androidx.viewpager.widget.ViewPager$4, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass4 implements OnApplyWindowInsetsListener {
        private final Rect mTempRect;
        final /* synthetic */ ViewPager this$0;

        public AnonymousClass4(ViewPager r1) {
            this.this$0 = r1;
            this.mTempRect = new Rect();
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r6, WindowInsetsCompat r7) {
            WindowInsetsCompat r62 = ViewCompat.onApplyWindowInsets(r6, r7);
            if (r62.isConsumed() == false) goto L5;
            return r62;
        L5:
            Rect r72 = this.mTempRect;
            r72.left = r62.getSystemWindowInsetLeft();
            r72.top = r62.getSystemWindowInsetTop();
            r72.right = r62.getSystemWindowInsetRight();
            r72.bottom = r62.getSystemWindowInsetBottom();
            int r0 = this.this$0.getChildCount();
            int r1 = 0;
        L6:
            if (r1 >= r0) goto L9;
            WindowInsetsCompat r2 = ViewCompat.dispatchApplyWindowInsets(this.this$0.getChildAt(r1), r62);
            r72.left = Math.min(r2.getSystemWindowInsetLeft(), r72.left);
            r72.top = Math.min(r2.getSystemWindowInsetTop(), r72.top);
            r72.right = Math.min(r2.getSystemWindowInsetRight(), r72.right);
            r72.bottom = Math.min(r2.getSystemWindowInsetBottom(), r72.bottom);
            r1 = r1 + 1;
            goto L6
        L9:
            return r62.replaceSystemWindowInsets(r72.left, r72.top, r72.right, r72.bottom);
        }
    }

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes4.dex */
    public @interface DecorView {
    }

    /* loaded from: classes4.dex */
    public static class ItemInfo {
        Object object;
        float offset;
        int position;
        boolean scrolling;
        float widthFactor;

        public ItemInfo() {
        }
    }

    /* loaded from: classes4.dex */
    public static class LayoutParams extends ViewGroup.LayoutParams {
        int childIndex;
        public int gravity;
        public boolean isDecor;
        boolean needsMeasure;
        int position;
        float widthFactor;

        public LayoutParams() {
            super(-1, -1);
            this.widthFactor = 0.0f;
        }

        public LayoutParams(Context r2, AttributeSet r3) {
            super(r2, r3);
            this.widthFactor = 0.0f;
            TypedArray r22 = r2.obtainStyledAttributes(r3, ViewPager.LAYOUT_ATTRS);
            this.gravity = r22.getInteger(0, 48);
            r22.recycle();
        }
    }

    /* loaded from: classes4.dex */
    public class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
        final /* synthetic */ ViewPager this$0;

        public MyAccessibilityDelegate(ViewPager r1) {
            this.this$0 = r1;
        }

        private boolean canScroll() {
            PagerAdapter r1 = this.this$0.mAdapter;
            if (r1 != null) goto L5;
            return false;
        L5:
            if (r1.getCount() <= 1) goto L9;
            return true;
        L9:
            return false;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View r2, AccessibilityEvent r3) {
            super.onInitializeAccessibilityEvent(r2, r3);
            r3.setClassName(ViewPager.class.getName());
            r3.setScrollable(canScroll());
            if (r3.getEventType() != 4096) goto L8;
            PagerAdapter r22 = this.this$0.mAdapter;
            if (r22 == null) goto L9;
            r3.setItemCount(r22.getCount());
            r3.setFromIndex(this.this$0.mCurItem);
            r3.setToIndex(this.this$0.mCurItem);
            return;
        L9:
            return;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r2, AccessibilityNodeInfoCompat r3) {
            super.onInitializeAccessibilityNodeInfo(r2, r3);
            r3.setClassName(ViewPager.class.getName());
            r3.setScrollable(canScroll());
            if (this.this$0.canScrollHorizontally(1) == false) goto L6;
            r3.addAction(4096);
        L6:
            if (this.this$0.canScrollHorizontally(-1) == false) goto L9;
            r3.addAction(8192);
            return;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View r2, int r3, Bundle r4) {
            if (super.performAccessibilityAction(r2, r3, r4) == false) goto L6;
            return true;
        L6:
            if (r3 == 4096) goto L16;
            if (r3 == 8192) goto L11;
            return false;
        L11:
            if (this.this$0.canScrollHorizontally(-1) == false) goto L14;
            ViewPager r1 = this.this$0;
            r1.setCurrentItem(r1.mCurItem - 1);
            return true;
        L14:
            return false;
        L16:
            if (this.this$0.canScrollHorizontally(1) == false) goto L19;
            ViewPager r12 = this.this$0;
            r12.setCurrentItem(r12.mCurItem + 1);
            return true;
        L19:
            return false;
        }
    }

    /* loaded from: classes4.dex */
    public interface OnAdapterChangeListener {
        void onAdapterChanged(ViewPager r1, PagerAdapter r2, PagerAdapter r3);
    }

    /* loaded from: classes4.dex */
    public interface OnPageChangeListener {
        void onPageScrollStateChanged(int r1);

        void onPageScrolled(int r1, float r2, int r3);

        void onPageSelected(int r1);
    }

    /* loaded from: classes4.dex */
    public interface PageTransformer {
        void transformPage(View r1, float r2);
    }

    /* loaded from: classes4.dex */
    public class PagerObserver extends DataSetObserver {
        final /* synthetic */ ViewPager this$0;

        public PagerObserver(ViewPager r1) {
            this.this$0 = r1;
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            this.this$0.dataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            this.this$0.dataSetChanged();
        }
    }

    /* loaded from: classes4.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        Parcelable adapterState;
        ClassLoader loader;
        int position;

        /* renamed from: androidx.viewpager.widget.ViewPager$SavedState$1, reason: invalid class name */
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

        public SavedState(Parcel r2, ClassLoader r3) {
            super(r2, r3);
            if (r3 != null) goto L5;
            r3 = getClass().getClassLoader();
        L5:
            this.position = r2.readInt();
            this.adapterState = r2.readParcelable(r3);
            this.loader = r3;
        }

        public SavedState(Parcelable r1) {
            super(r1);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.position + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r2, int r3) {
            super.writeToParcel(r2, r3);
            r2.writeInt(this.position);
            r2.writeParcelable(this.adapterState, r3);
        }
    }

    /* loaded from: classes4.dex */
    public static class SimpleOnPageChangeListener implements OnPageChangeListener {
        public SimpleOnPageChangeListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r1) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r1) {
        }
    }

    /* loaded from: classes4.dex */
    public static class ViewPositionComparator implements Comparator<View> {
        public ViewPositionComparator() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(View r2, View r3) {
            LayoutParams r1 = (LayoutParams) r2.getLayoutParams();
            LayoutParams r22 = (LayoutParams) r3.getLayoutParams();
            boolean r32 = r1.isDecor;
            if (r32 == r22.isDecor) goto L10;
            if (r32 == false) goto L7;
            return 1;
        L7:
            return -1;
        L10:
            return r1.position - r22.position;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(View r1, View r2) {
            return compare2(r1, r2);
        }
    }

    static {
        LAYOUT_ATTRS = new int[]{R.attr.layout_gravity};
        COMPARATOR = new AnonymousClass1();
        sInterpolator = new AnonymousClass2();
        sPositionComparator = new ViewPositionComparator();
    }

    public ViewPager(Context r2) {
        super(r2);
        this.mItems = new ArrayList();
        this.mTempItem = new ItemInfo();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = false;
        this.mEndScrollRunnable = new AnonymousClass3(this);
        this.mScrollState = 0;
        initViewPager();
    }

    private void calculatePageOffsets(ItemInfo r11, int r12, ItemInfo r13) {
        int r0 = this.mAdapter.getCount();
        int r1 = getClientWidth();
        if (r1 <= 0) goto L5;
        float r2 = this.mPageMargin / r1;
    L7:
        if (r13 == null) goto L39;
        int r3 = r13.position;
        int r4 = r11.position;
        if (r3 >= r4) goto L25;
        float r42 = (r13.offset + r13.widthFactor) + r2;
        int r32 = r3 + 1;
        int r132 = 0;
    L12:
        if (r32 > r11.position) goto L39;
        if (r132 >= this.mItems.size()) goto L39;
        ItemInfo r5 = this.mItems.get(r132);
    L16:
        ItemInfo r52 = r5;
        if (r32 <= r52.position) goto L22;
        if (r132 >= (this.mItems.size() - 1)) goto L22;
        r132 = r132 + 1;
        r5 = this.mItems.get(r132);
    L22:
        if (r32 >= r52.position) goto L24;
        r42 = r42 + (this.mAdapter.getPageWidth(r32) + r2);
        r32 = r32 + 1;
        goto L22
    L24:
        r52.offset = r42;
        r42 = r42 + (r52.widthFactor + r2);
        r32 = r32 + 1;
        goto L12
    L25:
        if (r3 <= r4) goto L39;
        int r43 = this.mItems.size() - 1;
        float r133 = r13.offset;
    L27:
        r3 = r3 - 1;
        if (r3 < r11.position) goto L39;
        if (r43 < 0) goto L39;
        ItemInfo r53 = this.mItems.get(r43);
    L31:
        ItemInfo r54 = r53;
        if (r3 >= r54.position) goto L36;
        if (r43 <= 0) goto L36;
        r43 = r43 - 1;
        r53 = this.mItems.get(r43);
    L36:
        if (r3 <= r54.position) goto L38;
        r133 = r133 - (this.mAdapter.getPageWidth(r3) + r2);
        r3 = r3 - 1;
        goto L36
    L38:
        r133 = r133 - (r54.widthFactor + r2);
        r54.offset = r133;
    L39:
        int r134 = this.mItems.size();
        float r33 = r11.offset;
        int r44 = r11.position;
        int r55 = r44 - 1;
        if (r44 != 0) goto L42;
        float r6 = r33;
    L43:
        this.mFirstOffset = r6;
        int r02 = r0 - 1;
        if (r44 != r02) goto L46;
        float r45 = (r11.widthFactor + r33) - 1.0f;
    L47:
        this.mLastOffset = r45;
        int r46 = r12 - 1;
    L48:
        if (r46 < 0) goto L57;
        ItemInfo r7 = this.mItems.get(r46);
    L50:
        int r8 = r7.position;
        if (r55 <= r8) goto L53;
        r33 = r33 - (this.mAdapter.getPageWidth(r55) + r2);
        r55 = r55 - 1;
        goto L50
    L53:
        r33 = r33 - (r7.widthFactor + r2);
        r7.offset = r33;
        if (r8 != 0) goto L56;
        this.mFirstOffset = r33;
    L56:
        r46 = r46 - 1;
        r55 = r55 - 1;
        goto L48
    L57:
        float r34 = (r11.offset + r11.widthFactor) + r2;
        int r112 = r11.position + 1;
        int r122 = r12 + 1;
    L58:
        if (r122 >= r134) goto L66;
        ItemInfo r47 = this.mItems.get(r122);
    L60:
        int r56 = r47.position;
        if (r112 >= r56) goto L63;
        r34 = r34 + (this.mAdapter.getPageWidth(r112) + r2);
        r112 = r112 + 1;
        goto L60
    L63:
        if (r56 != r02) goto L65;
        this.mLastOffset = (r47.widthFactor + r34) - 1.0f;
    L65:
        r47.offset = r34;
        r34 = r34 + (r47.widthFactor + r2);
        r122 = r122 + 1;
        r112 = r112 + 1;
        goto L58
    L66:
        this.mNeedCalculatePageOffsets = false;
        return;
    L46:
        r45 = Float.MAX_VALUE;
        goto L47
    L42:
        r6 = -3.4028235E38f;
        goto L43
    L5:
        r2 = 0.0f;
        goto L7
    }

    private void completeScroll(boolean r8) {
        if (this.mScrollState != 2) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == false) goto L15;
        setScrollingCacheEnabled(false);
        if (this.mScroller.isFinished() == true) goto L15;
        this.mScroller.abortAnimation();
        int r1 = getScrollX();
        int r4 = getScrollY();
        int r5 = this.mScroller.getCurrX();
        int r6 = this.mScroller.getCurrY();
        if (r1 != r5) goto L12;
        if (r4 == r6) goto L15;
    L12:
        scrollTo(r5, r6);
        if (r5 == r1) goto L15;
        pageScrolled(r5);
    L15:
        this.mPopulatePending = false;
        int r12 = 0;
    L17:
        if (r12 >= this.mItems.size()) goto L22;
        ItemInfo r42 = this.mItems.get(r12);
        if (r42.scrolling == false) goto L21;
        r42.scrolling = false;
        r0 = true;
    L21:
        r12 = r12 + 1;
        goto L17
    L22:
        if (r0 == false) goto L32;
        Runnable r02 = this.mEndScrollRunnable;
        if (r8 == false) goto L27;
        ViewCompat.postOnAnimation(this, r02);
        return;
    L27:
        r02.run();
        return;
    L32:
        return;
    L5:
        r0 = false;
        goto L6
    }

    private int determineTargetPage(int r2, float r3, int r4, int r5) {
        if (Math.abs(r5) <= this.mFlingDistance) goto L10;
        if (Math.abs(r4) <= this.mMinimumVelocity) goto L10;
        if (r4 > 0) goto L15;
        r2 = r2 + 1;
    L15:
        if (this.mItems.size() > 0) goto L17;
        return r2;
    L17:
        return Math.max(this.mItems.get(0).position, Math.min(r2, this.mItems.get(r1.size() - 1).position));
    L10:
        if (r2 < this.mCurItem) goto L12;
        float r42 = 0.4f;
    L13:
        r2 = r2 + ((int) (r3 + r42));
        goto L15
    L12:
        r42 = 0.6f;
        goto L13
    }

    private void dispatchOnPageScrolled(int r4, float r5, int r6) {
        OnPageChangeListener r0 = this.mOnPageChangeListener;
        if (r0 == null) goto L5;
        r0.onPageScrolled(r4, r5, r6);
    L5:
        List<OnPageChangeListener> r02 = this.mOnPageChangeListeners;
        if (r02 == null) goto L13;
        int r03 = r02.size();
        int r1 = 0;
    L8:
        if (r1 >= r03) goto L13;
        OnPageChangeListener r2 = this.mOnPageChangeListeners.get(r1);
        if (r2 == null) goto L12;
        r2.onPageScrolled(r4, r5, r6);
    L12:
        r1 = r1 + 1;
    L13:
        OnPageChangeListener r3 = this.mInternalPageChangeListener;
        if (r3 == null) goto L20;
        r3.onPageScrolled(r4, r5, r6);
        return;
    }

    private void dispatchOnPageSelected(int r4) {
        OnPageChangeListener r0 = this.mOnPageChangeListener;
        if (r0 == null) goto L5;
        r0.onPageSelected(r4);
    L5:
        List<OnPageChangeListener> r02 = this.mOnPageChangeListeners;
        if (r02 == null) goto L13;
        int r03 = r02.size();
        int r1 = 0;
    L8:
        if (r1 >= r03) goto L13;
        OnPageChangeListener r2 = this.mOnPageChangeListeners.get(r1);
        if (r2 == null) goto L12;
        r2.onPageSelected(r4);
    L12:
        r1 = r1 + 1;
    L13:
        OnPageChangeListener r3 = this.mInternalPageChangeListener;
        if (r3 == null) goto L20;
        r3.onPageSelected(r4);
        return;
    }

    private void dispatchOnScrollStateChanged(int r4) {
        OnPageChangeListener r0 = this.mOnPageChangeListener;
        if (r0 == null) goto L5;
        r0.onPageScrollStateChanged(r4);
    L5:
        List<OnPageChangeListener> r02 = this.mOnPageChangeListeners;
        if (r02 == null) goto L13;
        int r03 = r02.size();
        int r1 = 0;
    L8:
        if (r1 >= r03) goto L13;
        OnPageChangeListener r2 = this.mOnPageChangeListeners.get(r1);
        if (r2 == null) goto L12;
        r2.onPageScrollStateChanged(r4);
    L12:
        r1 = r1 + 1;
    L13:
        OnPageChangeListener r3 = this.mInternalPageChangeListener;
        if (r3 == null) goto L20;
        r3.onPageScrollStateChanged(r4);
        return;
    }

    private void enableLayers(boolean r7) {
        int r0 = getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        if (r7 == false) goto L6;
        int r3 = this.mPageTransformerLayerType;
    L7:
        getChildAt(r2).setLayerType(r3, null);
        r2 = r2 + 1;
        goto L3
    L6:
        r3 = 0;
        goto L7
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        VelocityTracker r0 = this.mVelocityTracker;
        if (r0 == null) goto L6;
        r0.recycle();
        this.mVelocityTracker = null;
        return;
    }

    private Rect getChildRectInPagerCoordinates(Rect r3, View r4) {
        if (r3 != null) goto L4;
        r3 = new Rect();
    L4:
        if (r4 != null) goto L7;
        r3.set(0, 0, 0, 0);
        return r3;
    L7:
        r3.left = r4.getLeft();
        r3.right = r4.getRight();
        r3.top = r4.getTop();
        r3.bottom = r4.getBottom();
        ViewParent r42 = r4.getParent();
    L9:
        if ((r42 instanceof ViewGroup) == false) goto L12;
        if (r42 == this) goto L12;
        ViewGroup r43 = (ViewGroup) r42;
        r3.left += r43.getLeft();
        r3.right += r43.getRight();
        r3.top += r43.getTop();
        r3.bottom += r43.getBottom();
        r42 = r43.getParent();
    L12:
        return r3;
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private ItemInfo infoForCurrentScrollPosition() {
        int r0 = getClientWidth();
        float r1 = 0.0f;
        if (r0 <= 0) goto L5;
        float r2 = getScrollX() / r0;
    L6:
        if (r0 <= 0) goto L8;
        float r3 = this.mPageMargin / r0;
    L9:
        int r8 = 0;
        boolean r9 = true;
        ItemInfo r7 = null;
        int r6 = -1;
        float r4 = 0.0f;
    L11:
        if (r8 >= this.mItems.size()) goto L28;
        ItemInfo r10 = this.mItems.get(r8);
        if (r9 == true) goto L17;
        int r62 = r6 + 1;
        if (r10.position == r62) goto L17;
        r10 = this.mTempItem;
        r10.offset = (r1 + r4) + r3;
        r10.position = r62;
        r10.widthFactor = this.mAdapter.getPageWidth(r62);
        r8 = r8 - 1;
    L17:
        ItemInfo r63 = r10;
        r1 = r63.offset;
        float r42 = (r63.widthFactor + r1) + r3;
        if (r9 == true) goto L22;
        if (r2 < r1) goto L28;
    L22:
        if (r2 < r42) goto L27;
        if (r8 == (this.mItems.size() - 1)) goto L27;
        int r43 = r63.position;
        float r72 = r63.widthFactor;
        r8 = r8 + 1;
        r6 = r43;
        r4 = r72;
        r7 = r63;
        r9 = false;
    L27:
        return r63;
    L28:
        return r7;
    L8:
        r3 = 0.0f;
        goto L9
    L5:
        r2 = 0.0f;
        goto L6
    }

    private static boolean isDecorView(View r1) {
        if (r1.getClass().getAnnotation(DecorView.class) == null) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isGutterDrag(float r3, float r4) {
        if (r3 >= this.mGutterSize) goto L7;
        if (r4 <= 0.0f) goto L7;
        return true;
    L7:
        if (r3 > (getWidth() - this.mGutterSize)) goto L9;
        return false;
    L9:
        if (r4 >= 0.0f) goto L15;
        return true;
    L15:
        return false;
    }

    private void onSecondaryPointerUp(MotionEvent r4) {
        int r0 = r4.getActionIndex();
        if (r4.getPointerId(r0) != this.mActivePointerId) goto L11;
        if (r0 != 0) goto L6;
        int r02 = 1;
    L7:
        this.mLastMotionX = r4.getX(r02);
        this.mActivePointerId = r4.getPointerId(r02);
        VelocityTracker r3 = this.mVelocityTracker;
        if (r3 == null) goto L12;
        r3.clear();
        return;
    L12:
        return;
    L6:
        r02 = 0;
        goto L7
    }

    private boolean pageScrolled(int r8) {
        if (this.mItems.size() == 0) goto L5;
        ItemInfo r0 = infoForCurrentScrollPosition();
        int r3 = getClientWidth();
        int r4 = this.mPageMargin;
        int r5 = r3 + r4;
        float r32 = r3;
        int r6 = r0.position;
        float r82 = ((r8 / r32) - r0.offset) / (r0.widthFactor + (r4 / r32));
        this.mCalledSuper = false;
        onPageScrolled(r6, r82, (int) (r5 * r82));
        if (this.mCalledSuper == false) goto L16;
        return true;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("onPageScrolled did not call superclass implementation");
        return false;
    L5:
        if (this.mFirstLayout == false) goto L7;
        return false;
    L7:
        this.mCalledSuper = false;
        onPageScrolled(0, 0.0f, 0);
        if (this.mCalledSuper == false) goto L10;
        return false;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("onPageScrolled did not call superclass implementation");
        return false;
    }

    private boolean performDrag(float r10) {
        float r0 = this.mLastMotionX - r10;
        this.mLastMotionX = r10;
        float r102 = getScrollX() + r0;
        float r02 = getClientWidth();
        float r1 = this.mFirstOffset * r02;
        float r2 = this.mLastOffset * r02;
        boolean r4 = false;
        ItemInfo r3 = this.mItems.get(0);
        ArrayList<ItemInfo> r5 = this.mItems;
        ItemInfo r52 = r5.get(r5.size() - 1);
        if (r3.position == 0) goto L5;
        r1 = r3.offset * r02;
        boolean r32 = false;
    L7:
        if (r52.position == (this.mAdapter.getCount() - 1)) goto L9;
        r2 = r52.offset * r02;
        boolean r53 = false;
    L11:
        if (r102 >= r1) goto L16;
        if (r32 == false) goto L14;
        this.mLeftEdge.onPull(Math.abs(r1 - r102) / r02);
        r4 = true;
    L14:
        r102 = r1;
    L20:
        int r12 = (int) r102;
        this.mLastMotionX += r102 - r12;
        scrollTo(r12, getScrollY());
        pageScrolled(r12);
        return r4;
    L16:
        if (r102 <= r2) goto L20;
        if (r53 == false) goto L19;
        this.mRightEdge.onPull(Math.abs(r102 - r2) / r02);
        r4 = true;
    L19:
        r102 = r2;
        goto L20
    L9:
        r53 = true;
        goto L11
    L5:
        r32 = true;
        goto L7
    }

    private void recomputeScrollPosition(int r2, int r3, int r4, int r5) {
        if (r3 > 0) goto L4;
    L11:
        ItemInfo r32 = infoForPosition(this.mCurItem);
        if (r32 == null) goto L14;
        float r33 = Math.min(r32.offset, this.mLastOffset);
    L15:
        int r22 = (int) (r33 * ((r2 - getPaddingLeft()) - getPaddingRight()));
        if (r22 == getScrollX()) goto L19;
        completeScroll(false);
        scrollTo(r22, getScrollY());
        return;
    L19:
        return;
    L14:
        r33 = 0.0f;
        goto L15
    L4:
        if (this.mItems.isEmpty() == true) goto L11;
        if (this.mScroller.isFinished() == true) goto L9;
        this.mScroller.setFinalX(getCurrentItem() * getClientWidth());
        return;
    L9:
        scrollTo((int) ((getScrollX() / (((r3 - getPaddingLeft()) - getPaddingRight()) + r5)) * (((r2 - getPaddingLeft()) - getPaddingRight()) + r4)), getScrollY());
    }

    private void removeNonDecorViews() {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L9;
        if (((LayoutParams) getChildAt(r0).getLayoutParams()).isDecor == true) goto L8;
        removeViewAt(r0);
        r0 = r0 - 1;
    L8:
        r0 = r0 + 1;
        goto L4
    }

    private void requestParentDisallowInterceptTouchEvent(boolean r1) {
        ViewParent r0 = getParent();
        if (r0 == null) goto L6;
        r0.requestDisallowInterceptTouchEvent(r1);
        return;
    }

    private boolean resetTouch() {
        this.mActivePointerId = -1;
        endDrag();
        this.mLeftEdge.onRelease();
        this.mRightEdge.onRelease();
        if (this.mLeftEdge.isFinished() == false) goto L5;
        return true;
    L5:
        if (this.mRightEdge.isFinished() == true) goto L11;
        return false;
    L11:
        return true;
    }

    private void scrollToItem(int r6, boolean r7, int r8, boolean r9) {
        ItemInfo r0 = infoForPosition(r6);
        if (r0 == null) goto L5;
        int r02 = (int) (getClientWidth() * Math.max(this.mFirstOffset, Math.min(r0.offset, this.mLastOffset)));
    L6:
        if (r7 == false) goto L11;
        smoothScrollTo(r02, 0, r8);
        if (r9 == false) goto L15;
        dispatchOnPageSelected(r6);
        return;
    L15:
        return;
    L11:
        if (r9 == false) goto L13;
        dispatchOnPageSelected(r6);
    L13:
        completeScroll(false);
        scrollTo(r02, 0);
        pageScrolled(r02);
        return;
    L5:
        r02 = 0;
        goto L6
    }

    private void setScrollingCacheEnabled(boolean r2) {
        if (this.mScrollingCacheEnabled == r2) goto L6;
        this.mScrollingCacheEnabled = r2;
        return;
    }

    private void sortChildDrawingOrder() {
        if (this.mDrawingOrder == 0) goto L14;
        ArrayList<View> r0 = this.mDrawingOrderedChildren;
        if (r0 != null) goto L7;
        this.mDrawingOrderedChildren = new ArrayList();
    L8:
        int r02 = getChildCount();
        int r1 = 0;
    L9:
        if (r1 >= r02) goto L11;
        View r2 = getChildAt(r1);
        this.mDrawingOrderedChildren.add(r2);
        r1 = r1 + 1;
        goto L9
    L11:
        Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
        return;
    L7:
        r0.clear();
        goto L8
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> r7, int r8, int r9) {
        int r0 = r7.size();
        int r1 = getDescendantFocusability();
        if (r1 == 393216) goto L16;
        int r2 = 0;
    L6:
        if (r2 >= getChildCount()) goto L16;
        View r3 = getChildAt(r2);
        if (r3.getVisibility() != 0) goto L14;
        ItemInfo r4 = infoForChild(r3);
        if (r4 == null) goto L14;
        if (r4.position != this.mCurItem) goto L14;
        r3.addFocusables(r7, r8, r9);
    L14:
        r2 = r2 + 1;
    L16:
        if (r1 != 262144) goto L20;
        if (r0 == r7.size()) goto L20;
        return;
    L20:
        if (isFocusable() == true) goto L23;
        return;
    L23:
        if ((r9 & 1) == 1) goto L25;
    L29:
        r7.add(this);
        return;
    L25:
        if (isInTouchMode() == false) goto L29;
        if (isFocusableInTouchMode() == true) goto L29;
    }

    public ItemInfo addNewItem(int r3, int r4) {
        ItemInfo r0 = new ItemInfo();
        r0.position = r3;
        r0.object = this.mAdapter.instantiateItem(this, r3);
        r0.widthFactor = this.mAdapter.getPageWidth(r3);
        if (r4 >= 0) goto L5;
    L9:
        this.mItems.add(r0);
        return r0;
    L5:
        if (r4 >= this.mItems.size()) goto L9;
        this.mItems.add(r4, r0);
        return r0;
    }

    public void addOnAdapterChangeListener(OnAdapterChangeListener r2) {
        if (this.mAdapterChangeListeners != null) goto L5;
        this.mAdapterChangeListeners = new ArrayList();
    L5:
        this.mAdapterChangeListeners.add(r2);
    }

    public void addOnPageChangeListener(OnPageChangeListener r2) {
        if (this.mOnPageChangeListeners != null) goto L5;
        this.mOnPageChangeListeners = new ArrayList();
    L5:
        this.mOnPageChangeListeners.add(r2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> r5) {
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L13;
        View r1 = getChildAt(r0);
        if (r1.getVisibility() != 0) goto L12;
        ItemInfo r2 = infoForChild(r1);
        if (r2 == null) goto L12;
        if (r2.position != this.mCurItem) goto L12;
        r1.addTouchables(r5);
    L12:
        r0 = r0 + 1;
        goto L4
    }

    @Override // android.view.ViewGroup
    public void addView(View r4, int r5, ViewGroup.LayoutParams r6) {
        if (checkLayoutParams(r6) == true) goto L5;
        r6 = generateLayoutParams(r6);
    L5:
        LayoutParams r0 = (LayoutParams) r6;
        boolean r1 = r0.isDecor | isDecorView(r4);
        r0.isDecor = r1;
        if (this.mInLayout == false) goto L12;
        if (r1 == true) goto L10;
        r0.needsMeasure = true;
        addViewInLayout(r4, r5, r6);
        return;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("Cannot add pager decor view during layout");
        return;
    L12:
        super.addView(r4, r5, r6);
    }

    public boolean arrowScroll(int r5) {
        View r0 = findFocus();
        if (r0 != this) goto L5;
    L4:
        r0 = null;
    L17:
        View r1 = FocusFinder.getInstance().findNextFocus(this, r0, r5);
        if (r1 == null) goto L32;
        if (r1 == r0) goto L32;
        if (r5 != 17) goto L26;
        int r2 = getChildRectInPagerCoordinates(this.mTempRect, r1).left;
        int r3 = getChildRectInPagerCoordinates(this.mTempRect, r0).left;
        if (r0 == null) goto L25;
        if (r2 < r3) goto L25;
        boolean r02 = pageLeft();
    L43:
        if (r02 == false) goto L45;
        playSoundEffect(SoundEffectConstants.getContantForFocusDirection(r5));
    L45:
        return r02;
    L25:
        r02 = r1.requestFocus();
        goto L43
    L26:
        if (r5 != 66) goto L40;
        int r22 = getChildRectInPagerCoordinates(this.mTempRect, r1).left;
        int r32 = getChildRectInPagerCoordinates(this.mTempRect, r0).left;
        if (r0 == null) goto L31;
        if (r22 > r32) goto L31;
        r02 = pageRight();
    L31:
        r02 = r1.requestFocus();
    L40:
        r02 = false;
    L32:
        if (r5 != 17) goto L34;
    L42:
        r02 = pageLeft();
        goto L43
    L34:
        if (r5 == 1) goto L42;
        if (r5 != 66) goto L38;
    L41:
        r02 = pageRight();
        goto L43
    L38:
        if (r5 != 2) goto L40;
    L5:
        if (r0 == null) goto L17;
        ViewParent r23 = r0.getParent();
    L8:
        if ((r23 instanceof ViewGroup) == false) goto L12;
        if (r23 == this) goto L17;
        r23 = r23.getParent();
        goto L8
    L12:
        StringBuilder r24 = new StringBuilder();
        r24.append(r0.getClass().getSimpleName());
        ViewParent r03 = r0.getParent();
    L14:
        if ((r03 instanceof ViewGroup) == false) goto L16;
        r24.append(" => ");
        r24.append(r03.getClass().getSimpleName());
        r03 = r03.getParent();
        goto L14
    L16:
        Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view ".concat(r24.toString()));
        goto L4
    }

    public boolean beginFakeDrag() {
        if (this.mIsBeingDragged == false) goto L6;
        return false;
    L6:
        this.mFakeDragging = true;
        setScrollState(1);
        this.mLastMotionX = 0.0f;
        this.mInitialMotionX = 0.0f;
        VelocityTracker r1 = this.mVelocityTracker;
        if (r1 != null) goto L9;
        this.mVelocityTracker = VelocityTracker.obtain();
    L10:
        long r2 = SystemClock.uptimeMillis();
        MotionEvent r12 = MotionEvent.obtain(r2, r2, 0, 0.0f, 0.0f, 0);
        this.mVelocityTracker.addMovement(r12);
        r12.recycle();
        this.mFakeDragBeginTime = r2;
        return true;
    L9:
        r1.clear();
        goto L10
    }

    public boolean canScroll(View r12, boolean r13, int r14, int r15, int r16) {
        if ((r12 instanceof ViewGroup) == false) goto L18;
        ViewGroup r0 = (ViewGroup) r12;
        int r2 = r12.getScrollX();
        int r3 = r12.getScrollY();
        int r4 = r0.getChildCount() - 1;
    L5:
        if (r4 < 0) goto L18;
        View r6 = r0.getChildAt(r4);
        int r5 = r15 + r2;
        if (r5 < r6.getLeft()) goto L17;
        if (r5 >= r6.getRight()) goto L17;
        int r7 = r16 + r3;
        if (r7 < r6.getTop()) goto L17;
        if (r7 >= r6.getBottom()) goto L17;
        if (canScroll(r6, true, r14, r5 - r6.getLeft(), r7 - r6.getTop()) == false) goto L17;
        return true;
    L17:
        r4 = r4 - 1;
    L18:
        if (r13 == true) goto L20;
        return false;
    L20:
        if (r12.canScrollHorizontally(-r14) == false) goto L31;
        return true;
    L31:
        return false;
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int r5) {
        if (this.mAdapter != null) goto L5;
        return false;
    L5:
        int r0 = getClientWidth();
        int r2 = getScrollX();
        if (r5 < 0) goto L8;
        if (r5 > 0) goto L13;
    L15:
        return false;
    L13:
        if (r2 >= ((int) (r0 * this.mLastOffset))) goto L15;
        return true;
    L8:
        if (r2 <= ((int) (r0 * this.mFirstOffset))) goto L10;
        return true;
    L10:
        return false;
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

    @Override // android.view.View
    public void computeScroll() {
        this.mIsScrollStarted = true;
        if (this.mScroller.isFinished() == false) goto L5;
    L14:
        completeScroll(true);
        return;
    L5:
        if (this.mScroller.computeScrollOffset() == false) goto L14;
        int r0 = getScrollX();
        int r1 = getScrollY();
        int r2 = this.mScroller.getCurrX();
        int r3 = this.mScroller.getCurrY();
        if (r0 != r2) goto L9;
        if (r1 != r3) goto L9;
    L12:
        ViewCompat.postInvalidateOnAnimation(this);
        return;
    L9:
        scrollTo(r2, r3);
        if (pageScrolled(r2) == true) goto L12;
        this.mScroller.abortAnimation();
        scrollTo(0, r3);
        goto L12
    }

    public void dataSetChanged() {
        int r0 = this.mAdapter.getCount();
        this.mExpectedAdapterCount = r0;
        if (this.mItems.size() < ((this.mOffscreenPageLimit * 2) + 1)) goto L5;
    L7:
        boolean r1 = false;
    L8:
        int r2 = this.mCurItem;
        int r5 = 0;
        boolean r6 = false;
    L10:
        if (r5 >= this.mItems.size()) goto L30;
        ItemInfo r7 = this.mItems.get(r5);
        int r8 = this.mAdapter.getItemPosition(r7.object);
        if (r8 == (-1)) goto L29;
        if (r8 != (-2)) goto L23;
        this.mItems.remove(r5);
        r5 = r5 - 1;
        if (r6 == true) goto L19;
        this.mAdapter.startUpdate(this);
        r6 = true;
    L19:
        this.mAdapter.destroyItem(this, r7.position, r7.object);
        int r12 = this.mCurItem;
        if (r12 != r7.position) goto L22;
        r2 = Math.max(0, Math.min(r12, r0 - 1));
    L22:
        r1 = true;
        goto L29
    L23:
        int r9 = r7.position;
        if (r9 == r8) goto L29;
        if (r9 != this.mCurItem) goto L28;
        r2 = r8;
    L28:
        r7.position = r8;
    L29:
        r5 = r5 + 1;
        goto L10
    L30:
        if (r6 == false) goto L32;
        this.mAdapter.finishUpdate(this);
    L32:
        Collections.sort(this.mItems, COMPARATOR);
        if (r1 == false) goto L49;
        int r02 = getChildCount();
        int r13 = 0;
    L35:
        if (r13 >= r02) goto L40;
        LayoutParams r52 = (LayoutParams) getChildAt(r13).getLayoutParams();
        if (r52.isDecor == true) goto L39;
        r52.widthFactor = 0.0f;
    L39:
        r13 = r13 + 1;
        goto L35
    L40:
        setCurrentItemInternal(r2, false, true);
        requestLayout();
        return;
    L49:
        return;
    L5:
        if (this.mItems.size() >= r0) goto L7;
        r1 = true;
        goto L8
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent r2) {
        if (super.dispatchKeyEvent(r2) == false) goto L5;
        return true;
    L5:
        if (executeKeyEvent(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent r7) {
        if (r7.getEventType() == 4096) goto L5;
        int r0 = getChildCount();
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L19;
        View r3 = getChildAt(r2);
        if (r3.getVisibility() != 0) goto L18;
        ItemInfo r4 = infoForChild(r3);
        if (r4 == null) goto L18;
        if (r4.position != this.mCurItem) goto L18;
        if (r3.dispatchPopulateAccessibilityEvent(r7) == false) goto L18;
        return true;
    L18:
        r2 = r2 + 1;
        goto L7
    L19:
        return false;
    L5:
        return super.dispatchPopulateAccessibilityEvent(r7);
    }

    public float distanceInfluenceForSnapDuration(float r1) {
        return (float) Math.sin((r1 - 0.5f) * 0.47123894f);
    }

    @Override // android.view.View
    public void draw(Canvas r8) {
        super.draw(r8);
        int r0 = getOverScrollMode();
        boolean r1 = false;
        if (r0 == 0) goto L13;
        if (r0 != 1) goto L11;
        PagerAdapter r02 = this.mAdapter;
        if (r02 == null) goto L11;
        if (r02.getCount() > 1) goto L13;
    L11:
        this.mLeftEdge.finish();
        this.mRightEdge.finish();
    L18:
        if (r1 == false) goto L21;
        ViewCompat.postInvalidateOnAnimation(this);
        return;
    L21:
        return;
    L13:
        if (this.mLeftEdge.isFinished() == true) goto L16;
        int r03 = r8.save();
        int r12 = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int r2 = getWidth();
        r8.rotate(270.0f);
        r8.translate((-r12) + getPaddingTop(), this.mFirstOffset * r2);
        this.mLeftEdge.setSize(r12, r2);
        r1 = this.mLeftEdge.draw(r8);
        r8.restoreToCount(r03);
    L16:
        if (this.mRightEdge.isFinished() == true) goto L18;
        int r04 = r8.save();
        int r22 = getWidth();
        int r3 = (getHeight() - getPaddingTop()) - getPaddingBottom();
        r8.rotate(90.0f);
        r8.translate(-getPaddingTop(), (-(this.mLastOffset + 1.0f)) * r22);
        this.mRightEdge.setSize(r3, r22);
        r1 = r1 | this.mRightEdge.draw(r8);
        r8.restoreToCount(r04);
        goto L18
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable r0 = this.mMarginDrawable;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isStateful() == false) goto L9;
        r0.setState(getDrawableState());
        return;
    }

    public void endFakeDrag() {
        if (this.mFakeDragging == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("No fake drag in progress. Call beginFakeDrag first.");
        return;
    L5:
        if (this.mAdapter == null) goto L7;
        VelocityTracker r0 = this.mVelocityTracker;
        r0.computeCurrentVelocity(1000, this.mMaximumVelocity);
        int r02 = (int) r0.getXVelocity(this.mActivePointerId);
        this.mPopulatePending = true;
        int r2 = getClientWidth();
        int r3 = getScrollX();
        ItemInfo r4 = infoForCurrentScrollPosition();
        setCurrentItemInternal(determineTargetPage(r4.position, ((r3 / r2) - r4.offset) / r4.widthFactor, r02, (int) (this.mLastMotionX - this.mInitialMotionX)), true, true, r02);
    L7:
        endDrag();
        this.mFakeDragging = false;
    }

    public boolean executeKeyEvent(KeyEvent r4) {
        if (r4.getAction() != 0) goto L31;
        int r0 = r4.getKeyCode();
        if (r0 == 21) goto L26;
        if (r0 == 22) goto L20;
        if (r0 == 61) goto L12;
        return false;
    L12:
        if (r4.hasNoModifiers() == false) goto L16;
        return arrowScroll(2);
    L16:
        if (r4.hasModifiers(1) == true) goto L18;
        return false;
    L18:
        return arrowScroll(1);
    L20:
        if (r4.hasModifiers(2) == false) goto L24;
        return pageRight();
    L24:
        return arrowScroll(66);
    L26:
        if (r4.hasModifiers(2) == false) goto L30;
        return pageLeft();
    L30:
        return arrowScroll(17);
    L31:
        return false;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet r2) {
        return new LayoutParams(getContext(), r2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return generateDefaultLayoutParams();
    }

    public PagerAdapter getAdapter() {
        return this.mAdapter;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int r3, int r4) {
        if (this.mDrawingOrder != 2) goto L6;
        r4 = (r3 - 1) - r4;
    L6:
        return ((LayoutParams) this.mDrawingOrderedChildren.get(r4).getLayoutParams()).childIndex;
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    public ItemInfo infoForAnyChild(View r2) {
    L2:
        Object r0 = r2.getParent();
        if (r0 == this) goto L12;
        if (r0 == null) goto L9;
        if ((r0 instanceof View) == false) goto L16;
        r2 = (View) r0;
        goto L2
    L16:
        return null;
    L9:
        return null;
    L12:
        return infoForChild(r2);
    }

    public ItemInfo infoForChild(View r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.mItems.size()) goto L9;
        ItemInfo r1 = this.mItems.get(r0);
        if (this.mAdapter.isViewFromObject(r5, r1.object) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return null;
    }

    public ItemInfo infoForPosition(int r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.mItems.size()) goto L9;
        ItemInfo r1 = this.mItems.get(r0);
        if (r1.position == r4) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return null;
    }

    public void initViewPager() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context r1 = getContext();
        this.mScroller = new Scroller(r1, sInterpolator);
        ViewConfiguration r2 = ViewConfiguration.get(r1);
        float r3 = r1.getResources().getDisplayMetrics().density;
        this.mTouchSlop = r2.getScaledPagingTouchSlop();
        this.mMinimumVelocity = (int) (400.0f * r3);
        this.mMaximumVelocity = r2.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffect(r1);
        this.mRightEdge = new EdgeEffect(r1);
        this.mFlingDistance = (int) (25.0f * r3);
        this.mCloseEnough = (int) (2.0f * r3);
        this.mDefaultGutterSize = (int) (r3 * 16.0f);
        ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate(this));
        if (ViewCompat.getImportantForAccessibility(this) != 0) goto L5;
        ViewCompat.setImportantForAccessibility(this, 1);
    L5:
        ViewCompat.setOnApplyWindowInsetsListener(this, new AnonymousClass4(this));
    }

    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.mEndScrollRunnable);
        Scroller r0 = this.mScroller;
        if (r0 != null) goto L5;
    L7:
        super.onDetachedFromWindow();
        return;
    L5:
        if (r0.isFinished() == true) goto L7;
        this.mScroller.abortAnimation();
        goto L7
    }

    @Override // android.view.View
    public void onDraw(Canvas r18) {
        super.onDraw(r18);
        if (this.mPageMargin > 0) goto L5;
        return;
    L5:
        if (this.mMarginDrawable != null) goto L7;
        return;
    L7:
        if (this.mItems.size() > 0) goto L9;
        return;
    L9:
        if (this.mAdapter == null) goto L34;
        int r1 = getScrollX();
        float r4 = getWidth();
        float r3 = this.mPageMargin / r4;
        int r6 = 0;
        ItemInfo r5 = this.mItems.get(0);
        float r7 = r5.offset;
        int r8 = this.mItems.size();
        int r9 = r5.position;
        int r10 = this.mItems.get(r8 - 1).position;
    L11:
        if (r9 >= r10) goto L35;
    L12:
        int r11 = r5.position;
        if (r9 <= r11) goto L16;
        if (r6 >= r8) goto L16;
        r6 = r6 + 1;
        r5 = this.mItems.get(r6);
    L16:
        if (r9 != r11) goto L18;
        float r72 = r5.offset;
        float r112 = r5.widthFactor;
        float r12 = (r72 + r112) * r4;
        r7 = (r72 + r112) + r3;
    L20:
        if ((this.mPageMargin + r12) <= r1) goto L22;
        int r16 = r1;
        this.mMarginDrawable.setBounds(Math.round(r12), this.mTopPageBounds, Math.round(this.mPageMargin + r12), this.mBottomPageBounds);
        this.mMarginDrawable.draw(r18);
    L24:
        if (r12 > (r16 + r2)) goto L36;
        r9 = r9 + 1;
        r1 = r16;
        goto L11
    L36:
        return;
    L22:
        r16 = r1;
        goto L24
    L18:
        float r113 = this.mAdapter.getPageWidth(r9);
        r12 = (r7 + r113) * r4;
        r7 = r7 + (r113 + r3);
        goto L20
    L35:
        return;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r14) {
        int r1 = r14.getAction() & 255;
        if (r1 != 3) goto L5;
    L60:
        resetTouch();
        return false;
    L5:
        if (r1 == 1) goto L60;
        if (r1 == 0) goto L15;
        if (this.mIsBeingDragged == false) goto L12;
        return true;
    L12:
        if (this.mIsUnableToDrag == false) goto L15;
        return false;
    L15:
        if (r1 == 0) goto L49;
        if (r1 != 2) goto L18;
        int r12 = this.mActivePointerId;
        if (r12 == (-1)) goto L56;
        int r13 = r14.findPointerIndex(r12);
        float r8 = r14.getX(r13);
        float r2 = r8 - this.mLastMotionX;
        float r9 = Math.abs(r2);
        float r10 = r14.getY(r13);
        float r11 = Math.abs(r10 - this.mInitialMotionY);
        if (r2 != 0.0f) goto L27;
    L32:
        int r15 = this.mTouchSlop;
        if (r9 <= r15) goto L42;
        if ((r9 * 0.5f) <= r11) goto L42;
        this.mIsBeingDragged = true;
        requestParentDisallowInterceptTouchEvent(true);
        setScrollState(1);
        float r16 = this.mInitialMotionX;
        float r22 = this.mTouchSlop;
        if (r2 <= 0.0f) goto L39;
        float r17 = r16 + r22;
    L40:
        this.mLastMotionX = r17;
        this.mLastMotionY = r10;
        setScrollingCacheEnabled(true);
    L45:
        if (this.mIsBeingDragged == false) goto L56;
        if (performDrag(r8) == false) goto L56;
        ViewCompat.postInvalidateOnAnimation(this);
        goto L56
    L39:
        r17 = r16 - r22;
    L42:
        if (r11 <= r15) goto L45;
        this.mIsUnableToDrag = true;
        goto L45
    L27:
        if (isGutterDrag(this.mLastMotionX, r2) == true) goto L32;
        if (canScroll(this, false, (int) r2, (int) r8, (int) r10) == false) goto L32;
        this.mLastMotionX = r8;
        this.mLastMotionY = r10;
        this.mIsUnableToDrag = true;
        return false;
    L56:
        if (this.mVelocityTracker != null) goto L58;
        this.mVelocityTracker = VelocityTracker.obtain();
    L58:
        this.mVelocityTracker.addMovement(r14);
        return this.mIsBeingDragged;
    L18:
        if (r1 != 6) goto L56;
        onSecondaryPointerUp(r14);
        goto L56
    L49:
        float r18 = r14.getX();
        this.mInitialMotionX = r18;
        this.mLastMotionX = r18;
        float r19 = r14.getY();
        this.mInitialMotionY = r19;
        this.mLastMotionY = r19;
        this.mActivePointerId = r14.getPointerId(0);
        this.mIsUnableToDrag = false;
        this.mIsScrollStarted = true;
        this.mScroller.computeScrollOffset();
        if (this.mScrollState == 2) goto L52;
    L54:
        completeScroll(false);
        this.mIsBeingDragged = false;
        goto L56
    L52:
        if (Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) <= this.mCloseEnough) goto L54;
        this.mScroller.abortAnimation();
        this.mPopulatePending = false;
        populate();
        this.mIsBeingDragged = true;
        requestParentDisallowInterceptTouchEvent(true);
        setScrollState(1);
        goto L56
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        int r1 = getChildCount();
        int r2 = r22 - r20;
        int r3 = r23 - r21;
        int r4 = getPaddingLeft();
        int r5 = getPaddingTop();
        int r6 = getPaddingRight();
        int r7 = getPaddingBottom();
        int r8 = getScrollX();
        int r10 = 0;
        int r11 = 0;
    L4:
        if (r10 >= r1) goto L33;
        View r13 = getChildAt(r10);
        if (r13.getVisibility() == 8) goto L32;
        LayoutParams r12 = (LayoutParams) r13.getLayoutParams();
        if (r12.isDecor == false) goto L32;
        int r122 = r12.gravity;
        int r14 = r122 & 7;
        int r123 = r122 & 112;
        if (r14 != 1) goto L12;
        int r142 = Math.max((r2 - r13.getMeasuredWidth()) / 2, r4);
    L17:
        int r17 = r142;
        int r143 = r4;
        r4 = r17;
    L21:
        if (r123 != 16) goto L23;
        int r124 = Math.max((r3 - r13.getMeasuredHeight()) / 2, r5);
    L28:
        int r172 = r124;
        int r125 = r5;
        r5 = r172;
    L31:
        int r42 = r4 + r8;
        r13.layout(r42, r5, r13.getMeasuredWidth() + r42, r5 + r13.getMeasuredHeight());
        r11 = r11 + 1;
        r5 = r125;
        r4 = r143;
        goto L32
    L23:
        if (r123 != 48) goto L25;
        r125 = r13.getMeasuredHeight() + r5;
        goto L31
    L25:
        if (r123 == 80) goto L27;
        r125 = r5;
        goto L31
    L27:
        r124 = (r3 - r7) - r13.getMeasuredHeight();
        r7 = r7 + r13.getMeasuredHeight();
        goto L28
    L12:
        if (r14 != 3) goto L14;
        r143 = r13.getMeasuredWidth() + r4;
        goto L21
    L14:
        if (r14 == 5) goto L16;
        r143 = r4;
        goto L21
    L16:
        r142 = (r2 - r6) - r13.getMeasuredWidth();
        r6 = r6 + r13.getMeasuredWidth();
    L32:
        r10 = r10 + 1;
        goto L4
    L33:
        int r24 = (r2 - r4) - r6;
        int r62 = 0;
    L34:
        if (r62 >= r1) goto L46;
        View r82 = getChildAt(r62);
        if (r82.getVisibility() == 8) goto L45;
        LayoutParams r9 = (LayoutParams) r82.getLayoutParams();
        if (r9.isDecor == true) goto L45;
        ItemInfo r102 = infoForChild(r82);
        if (r102 == null) goto L45;
        float r132 = r24;
        int r103 = ((int) (r102.offset * r132)) + r4;
        if (r9.needsMeasure == false) goto L44;
        r9.needsMeasure = false;
        r82.measure(View.MeasureSpec.makeMeasureSpec((int) (r132 * r9.widthFactor), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((r3 - r5) - r7, TLObject.FLAG_30));
    L44:
        r82.layout(r103, r5, r82.getMeasuredWidth() + r103, r82.getMeasuredHeight() + r5);
    L45:
        r62 = r62 + 1;
        goto L34
    L46:
        this.mTopPageBounds = r5;
        this.mBottomPageBounds = r3 - r7;
        this.mDecorChildCount = r11;
        if (this.mFirstLayout == false) goto L49;
        boolean r144 = false;
        scrollToItem(this.mCurItem, false, 0, false);
    L50:
        this.mFirstLayout = r144;
        return;
    L49:
        r144 = false;
        goto L50
    }

    @Override // android.view.View
    public void onMeasure(int r14, int r15) {
        int r0 = 0;
        setMeasuredDimension(View.getDefaultSize(0, r14), View.getDefaultSize(0, r15));
        int r142 = getMeasuredWidth();
        this.mGutterSize = Math.min(r142 / 10, this.mDefaultGutterSize);
        int r143 = (r142 - getPaddingLeft()) - getPaddingRight();
        int r152 = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int r1 = getChildCount();
        int r2 = 0;
    L3:
        boolean r4 = true;
        int r5 = TLObject.FLAG_30;
        if (r2 >= r1) goto L48;
        View r6 = getChildAt(r2);
        if (r6.getVisibility() == 8) goto L47;
        LayoutParams r3 = (LayoutParams) r6.getLayoutParams();
        if (r3 == null) goto L47;
        if (r3.isDecor == false) goto L47;
        int r7 = r3.gravity;
        int r8 = r7 & 7;
        int r72 = r7 & 112;
        if (r72 != 48) goto L14;
    L17:
        boolean r73 = true;
    L19:
        if (r8 != 3) goto L21;
    L24:
        int r82 = Integer.MIN_VALUE;
        if (r73 == false) goto L27;
        int r9 = Integer.MIN_VALUE;
        r82 = 1073741824;
    L30:
        int r10 = ((ViewGroup.LayoutParams) r3).width;
        if (r10 == (-2)) goto L35;
        if (r10 == (-1)) goto L34;
    L33:
        r82 = 1073741824;
    L36:
        int r32 = ((ViewGroup.LayoutParams) r3).height;
        if (r32 == (-2)) goto L41;
        if (r32 != (-1)) goto L42;
        r32 = r152;
    L42:
        r6.measure(View.MeasureSpec.makeMeasureSpec(r10, r82), View.MeasureSpec.makeMeasureSpec(r32, r5));
        if (r73 == false) goto L45;
        r152 = r152 - r6.getMeasuredHeight();
        goto L47
    L45:
        if (r4 == false) goto L47;
        r143 = r143 - r6.getMeasuredWidth();
        goto L47
    L41:
        r32 = r152;
        r5 = r9;
        goto L42
    L34:
        r10 = r143;
        goto L33
    L35:
        r10 = r143;
        goto L36
    L27:
        if (r4 == false) goto L29;
        r9 = 1073741824;
        goto L30
    L29:
        r9 = Integer.MIN_VALUE;
        goto L30
    L21:
        if (r8 == 5) goto L24;
        r4 = false;
        goto L24
    L14:
        if (r72 == 80) goto L17;
        r73 = false;
    L47:
        r2 = r2 + 1;
        goto L3
    L48:
        this.mChildWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(r143, TLObject.FLAG_30);
        this.mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(r152, TLObject.FLAG_30);
        this.mInLayout = true;
        populate();
        this.mInLayout = false;
        int r153 = getChildCount();
    L49:
        if (r0 >= r153) goto L58;
        View r12 = getChildAt(r0);
        if (r12.getVisibility() == 8) goto L57;
        LayoutParams r22 = (LayoutParams) r12.getLayoutParams();
        if (r22 != null) goto L55;
    L56:
        r12.measure(View.MeasureSpec.makeMeasureSpec((int) (r143 * r22.widthFactor), TLObject.FLAG_30), this.mChildHeightMeasureSpec);
        goto L57
    L55:
        if (r22.isDecor == false) goto L56;
    L57:
        r0 = r0 + 1;
        goto L49
    }

    public void onPageScrolled(int r13, float r14, int r15) {
        int r1 = 0;
        if (this.mDecorChildCount <= 0) goto L25;
        int r0 = getScrollX();
        int r3 = getPaddingLeft();
        int r4 = getPaddingRight();
        int r5 = getWidth();
        int r6 = getChildCount();
        int r7 = 0;
    L5:
        if (r7 >= r6) goto L25;
        View r8 = getChildAt(r7);
        LayoutParams r9 = (LayoutParams) r8.getLayoutParams();
        if (r9.isDecor == false) goto L24;
        int r92 = r9.gravity & 7;
        if (r92 != 1) goto L12;
        int r93 = Math.max((r5 - r8.getMeasuredWidth()) / 2, r3);
    L17:
        int r11 = r93;
        int r94 = r3;
        r3 = r11;
    L20:
        int r32 = (r3 + r0) - r8.getLeft();
        if (r32 == 0) goto L23;
        r8.offsetLeftAndRight(r32);
    L23:
        r3 = r94;
        goto L24
    L12:
        if (r92 != 3) goto L14;
        r94 = r8.getWidth() + r3;
        goto L20
    L14:
        if (r92 == 5) goto L16;
        r94 = r3;
        goto L20
    L16:
        r93 = (r5 - r4) - r8.getMeasuredWidth();
        r4 = r4 + r8.getMeasuredWidth();
    L24:
        r7 = r7 + 1;
    L25:
        dispatchOnPageScrolled(r13, r14, r15);
        if (this.mPageTransformer == null) goto L34;
        int r132 = getScrollX();
        int r142 = getChildCount();
    L28:
        if (r1 >= r142) goto L34;
        View r152 = getChildAt(r1);
        if (((LayoutParams) r152.getLayoutParams()).isDecor == true) goto L33;
        this.mPageTransformer.transformPage(r152, (r152.getLeft() - r132) / getClientWidth());
    L33:
        r1 = r1 + 1;
    L34:
        this.mCalledSuper = true;
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int r9, Rect r10) {
        int r0 = getChildCount();
        if ((r9 & 2) == 0) goto L5;
        int r1 = r0;
        int r02 = 0;
        int r4 = 1;
    L6:
        if (r02 == r1) goto L17;
        View r5 = getChildAt(r02);
        if (r5.getVisibility() != 0) goto L16;
        ItemInfo r6 = infoForChild(r5);
        if (r6 == null) goto L16;
        if (r6.position != this.mCurItem) goto L16;
        if (r5.requestFocus(r9, r10) == false) goto L16;
        return true;
    L16:
        r02 = r02 + r4;
        goto L6
    L17:
        return false;
    L5:
        r02 = r0 - 1;
        r1 = -1;
        r4 = -1;
        goto L6
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable r4) {
        if ((r4 instanceof SavedState) == true) goto L6;
        super.onRestoreInstanceState(r4);
        return;
    L6:
        SavedState r42 = (SavedState) r4;
        super.onRestoreInstanceState(r42.getSuperState());
        PagerAdapter r0 = this.mAdapter;
        if (r0 == null) goto L10;
        r0.restoreState(r42.adapterState, r42.loader);
        setCurrentItemInternal(r42.position, false, true);
        return;
    L10:
        this.mRestoredCurItem = r42.position;
        this.mRestoredAdapterState = r42.adapterState;
        this.mRestoredClassLoader = r42.loader;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState r1 = new SavedState(super.onSaveInstanceState());
        r1.position = this.mCurItem;
        PagerAdapter r2 = this.mAdapter;
        if (r2 == null) goto L5;
        r1.adapterState = r2.saveState();
    L5:
        return r1;
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        if (r1 == r3) goto L6;
        int r22 = this.mPageMargin;
        recomputeScrollPosition(r1, r3, r22, r22);
        return;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        if (this.mFakeDragging == false) goto L5;
        return true;
    L5:
        boolean r2 = false;
        if (r8.getAction() == 0) goto L8;
    L10:
        PagerAdapter r0 = this.mAdapter;
        if (r0 != null) goto L13;
    L61:
        return false;
    L13:
        if (r0.getCount() == 0) goto L61;
        if (this.mVelocityTracker != null) goto L18;
        this.mVelocityTracker = VelocityTracker.obtain();
    L18:
        this.mVelocityTracker.addMovement(r8);
        int r02 = r8.getAction() & 255;
        if (r02 == 0) goto L57;
        if (r02 == 1) goto L55;
        if (r02 == 2) goto L36;
        if (r02 == 3) goto L33;
        if (r02 != 5) goto L28;
        int r03 = r8.getActionIndex();
        this.mLastMotionX = r8.getX(r03);
        this.mActivePointerId = r8.getPointerId(r03);
    L58:
        if (r2 == false) goto L60;
        ViewCompat.postInvalidateOnAnimation(this);
    L60:
        return true;
    L28:
        if (r02 != 6) goto L58;
        onSecondaryPointerUp(r8);
        this.mLastMotionX = r8.getX(r8.findPointerIndex(this.mActivePointerId));
        goto L58
    L33:
        if (this.mIsBeingDragged == false) goto L58;
        scrollToItem(this.mCurItem, true, 0, false);
        r2 = resetTouch();
        goto L58
    L36:
        if (this.mIsBeingDragged == true) goto L52;
        int r04 = r8.findPointerIndex(this.mActivePointerId);
        if (r04 != (-1)) goto L40;
        r2 = resetTouch();
        goto L58
    L40:
        float r3 = r8.getX(r04);
        float r4 = Math.abs(r3 - this.mLastMotionX);
        float r05 = r8.getY(r04);
        float r5 = Math.abs(r05 - this.mLastMotionY);
        if (r4 <= this.mTouchSlop) goto L52;
        if (r4 <= r5) goto L52;
        this.mIsBeingDragged = true;
        requestParentDisallowInterceptTouchEvent(true);
        float r42 = this.mInitialMotionX;
        float r32 = r3 - r42;
        int r52 = this.mTouchSlop;
        if (r32 <= 0.0f) goto L47;
        float r43 = r42 + r52;
    L48:
        this.mLastMotionX = r43;
        this.mLastMotionY = r05;
        setScrollState(1);
        setScrollingCacheEnabled(true);
        ViewParent r06 = getParent();
        if (r06 == null) goto L52;
        r06.requestDisallowInterceptTouchEvent(true);
        goto L52
    L47:
        r43 = r42 - r52;
    L52:
        if (this.mIsBeingDragged == false) goto L58;
        r2 = performDrag(r8.getX(r8.findPointerIndex(this.mActivePointerId)));
        goto L58
    L55:
        if (this.mIsBeingDragged == false) goto L58;
        VelocityTracker r07 = this.mVelocityTracker;
        r07.computeCurrentVelocity(1000, this.mMaximumVelocity);
        int r08 = (int) r07.getXVelocity(this.mActivePointerId);
        this.mPopulatePending = true;
        int r22 = getClientWidth();
        int r33 = getScrollX();
        ItemInfo r44 = infoForCurrentScrollPosition();
        float r23 = r22;
        setCurrentItemInternal(determineTargetPage(r44.position, ((r33 / r23) - r44.offset) / (r44.widthFactor + (this.mPageMargin / r23)), r08, (int) (r8.getX(r8.findPointerIndex(this.mActivePointerId)) - this.mInitialMotionX)), true, true, r08);
        r2 = resetTouch();
        goto L58
    L57:
        this.mScroller.abortAnimation();
        this.mPopulatePending = false;
        populate();
        float r09 = r8.getX();
        this.mInitialMotionX = r09;
        this.mLastMotionX = r09;
        float r010 = r8.getY();
        this.mInitialMotionY = r010;
        this.mLastMotionY = r010;
        this.mActivePointerId = r8.getPointerId(0);
        goto L58
    L8:
        if (r8.getEdgeFlags() == 0) goto L10;
        return false;
    }

    public boolean pageLeft() {
        int r0 = this.mCurItem;
        if (r0 <= 0) goto L6;
        setCurrentItem(r0 - 1, true);
        return true;
    L6:
        return false;
    }

    public boolean pageRight() {
        PagerAdapter r0 = this.mAdapter;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (this.mCurItem >= (r0.getCount() - 1)) goto L10;
        setCurrentItem(this.mCurItem + 1, true);
        return true;
    L10:
        return false;
    }

    public void populate() {
        populate(this.mCurItem);
    }

    public void populate(int r18) {
        int r2 = this.mCurItem;
        if (r2 == r18) goto L5;
        ItemInfo r22 = infoForPosition(r2);
        this.mCurItem = r18;
    L7:
        if (this.mAdapter != null) goto L11;
        sortChildDrawingOrder();
        return;
    L11:
        if (this.mPopulatePending == false) goto L15;
        sortChildDrawingOrder();
        return;
    L15:
        if (getWindowToken() == null) goto L182;
        this.mAdapter.startUpdate(this);
        int r1 = this.mOffscreenPageLimit;
        int r4 = Math.max(0, this.mCurItem - r1);
        int r6 = this.mAdapter.getCount();
        int r12 = Math.min(r6 - 1, this.mCurItem + r1);
        if (r6 != this.mExpectedAdapterCount) goto L139;
        int r7 = 0;
    L21:
        if (r7 >= this.mItems.size()) goto L27;
        ItemInfo r8 = this.mItems.get(r7);
        int r9 = r8.position;
        int r10 = this.mCurItem;
        if (r9 >= r10) goto L24;
        r7 = r7 + 1;
        goto L21
    L24:
        if (r9 != r10) goto L27;
    L28:
        if (r8 != null) goto L32;
        if (r6 <= 0) goto L32;
        r8 = addNewItem(this.mCurItem, r7);
    L32:
        if (r8 == null) goto L103;
        int r102 = r7 - 1;
        if (r102 < 0) goto L36;
        ItemInfo r11 = this.mItems.get(r102);
    L37:
        int r122 = getClientWidth();
        if (r122 > 0) goto L40;
        float r14 = 0.0f;
    L41:
        int r3 = this.mCurItem - 1;
        float r15 = 0.0f;
    L42:
        if (r3 < 0) goto L67;
        if (r15 < r14) goto L57;
        if (r3 >= r4) goto L57;
        if (r11 == null) goto L67;
        if (r3 != r11.position) goto L66;
        if (r11.scrolling == true) goto L66;
        this.mItems.remove(r102);
        this.mAdapter.destroyItem(this, r3, r11.object);
        r102 = r102 - 1;
        r7 = r7 - 1;
        if (r102 < 0) goto L55;
        ItemInfo r5 = this.mItems.get(r102);
    L56:
        r11 = r5;
    L55:
        r5 = null;
    L66:
        r3 = r3 - 1;
    L57:
        if (r11 != null) goto L59;
    L63:
        r15 = r15 + addNewItem(r3, r102 + 1).widthFactor;
        r7 = r7 + 1;
        if (r102 < 0) goto L55;
        r5 = this.mItems.get(r102);
        goto L56
    L59:
        if (r3 != r11.position) goto L63;
        r15 = r15 + r11.widthFactor;
        r102 = r102 - 1;
        if (r102 < 0) goto L55;
        r5 = this.mItems.get(r102);
    L67:
        float r32 = r8.widthFactor;
        int r42 = r7 + 1;
        if (r32 < 2.0f) goto L70;
    L102:
        calculatePageOffsets(r8, r7, r22);
        this.mAdapter.setPrimaryItem(this, this.mCurItem, r8.object);
        goto L103
    L70:
        if (r42 >= this.mItems.size()) goto L72;
        ItemInfo r52 = this.mItems.get(r42);
    L73:
        if (r122 > 0) goto L75;
        float r103 = 0.0f;
    L76:
        int r112 = this.mCurItem;
    L77:
        r112 = r112 + 1;
        if (r112 >= r6) goto L102;
        if (r32 < r103) goto L92;
        if (r112 <= r12) goto L92;
        if (r52 == null) goto L102;
        if (r112 != r52.position) goto L77;
        if (r52.scrolling == true) goto L77;
        this.mItems.remove(r42);
        this.mAdapter.destroyItem(this, r112, r52.object);
        if (r42 < this.mItems.size()) goto L90;
    L91:
        r52 = null;
        goto L77
    L90:
        r52 = this.mItems.get(r42);
    L92:
        if (r52 == null) goto L98;
        if (r112 != r52.position) goto L98;
        r32 = r32 + r52.widthFactor;
        r42 = r42 + 1;
        if (r42 >= this.mItems.size()) goto L91;
        r52 = this.mItems.get(r42);
    L98:
        ItemInfo r53 = addNewItem(r112, r42);
        r42 = r42 + 1;
        r32 = r32 + r53.widthFactor;
        if (r42 >= this.mItems.size()) goto L91;
        r52 = this.mItems.get(r42);
        goto L77
    L75:
        r103 = (getPaddingRight() / r122) + 2.0f;
        goto L76
    L72:
        r52 = null;
        goto L73
    L40:
        r14 = (2.0f - r8.widthFactor) + (getPaddingLeft() / r122);
        goto L41
    L36:
        r11 = null;
    L103:
        this.mAdapter.finishUpdate(this);
        int r13 = getChildCount();
        int r23 = 0;
    L104:
        if (r23 >= r13) goto L113;
        View r33 = getChildAt(r23);
        LayoutParams r43 = (LayoutParams) r33.getLayoutParams();
        r43.childIndex = r23;
        if (r43.isDecor == true) goto L112;
        if (r43.widthFactor != 0.0f) goto L112;
        ItemInfo r34 = infoForChild(r33);
        if (r34 == null) goto L112;
        r43.widthFactor = r34.widthFactor;
        r43.position = r34.position;
    L112:
        r23 = r23 + 1;
        goto L104
    L113:
        sortChildDrawingOrder();
        if (hasFocus() == false) goto L133;
        View r16 = findFocus();
        if (r16 == null) goto L118;
        ItemInfo r35 = infoForAnyChild(r16);
    L119:
        if (r35 != null) goto L121;
    L122:
        int r54 = 0;
    L124:
        if (r54 >= getChildCount()) goto L184;
        View r17 = getChildAt(r54);
        ItemInfo r24 = infoForChild(r17);
        if (r24 == null) goto L132;
        if (r24.position != this.mCurItem) goto L132;
        if (r17.requestFocus(2) == false) goto L132;
        return;
    L132:
        r54 = r54 + 1;
        goto L124
    L184:
        return;
    L121:
        if (r35.position != this.mCurItem) goto L122;
        return;
    L118:
        r35 = null;
        goto L119
    L133:
        return;
    L27:
        r8 = null;
        goto L28
    L139:
        String r19 = getResources().getResourceName(getId());     // Catch: Resources.NotFoundException -> L136
    L137:
        StringBuilder r25 = new StringBuilder("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ");
        r25.append(this.mExpectedAdapterCount);
        r25.append(", found: ");
        r25.append(r6);
        r25.append(" Pager id: ");
        r25.append(r19);
        r25.append(" Pager class: ");
        r25.append(getClass());
        OkHttpClient$Builder$$ExternalSyntheticBUOutline0.m(r25, " Problematic adapter: ", this.mAdapter.getClass());
        return;
    L136:
        r19 = Integer.toHexString(getId());
        goto L137
    L182:
        return;
    L5:
        r22 = null;
        goto L7
    }

    public void removeOnAdapterChangeListener(OnAdapterChangeListener r1) {
        List<OnAdapterChangeListener> r0 = this.mAdapterChangeListeners;
        if (r0 == null) goto L6;
        r0.remove(r1);
        return;
    }

    public void removeOnPageChangeListener(OnPageChangeListener r1) {
        List<OnPageChangeListener> r0 = this.mOnPageChangeListeners;
        if (r0 == null) goto L6;
        r0.remove(r1);
        return;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View r2) {
        if (this.mInLayout == false) goto L6;
        removeViewInLayout(r2);
        return;
    L6:
        super.removeView(r2);
    }

    public void setAdapter(PagerAdapter r8) {
        PagerAdapter r0 = this.mAdapter;
        int r2 = 0;
        if (r0 == null) goto L9;
        r0.setViewPagerObserver(null);
        this.mAdapter.startUpdate(this);
        int r02 = 0;
    L6:
        if (r02 >= this.mItems.size()) goto L8;
        ItemInfo r3 = this.mItems.get(r02);
        this.mAdapter.destroyItem(this, r3.position, r3.object);
        r02 = r02 + 1;
        goto L6
    L8:
        this.mAdapter.finishUpdate(this);
        this.mItems.clear();
        removeNonDecorViews();
        this.mCurItem = 0;
        scrollTo(0, 0);
    L9:
        PagerAdapter r03 = this.mAdapter;
        this.mAdapter = r8;
        this.mExpectedAdapterCount = 0;
        if (r8 != null) goto L12;
    L20:
        List<OnAdapterChangeListener> r1 = this.mAdapterChangeListeners;
        if (r1 != null) goto L23;
        return;
    L23:
        if (r1.isEmpty() == true) goto L30;
        int r12 = this.mAdapterChangeListeners.size();
    L25:
        if (r2 >= r12) goto L31;
        this.mAdapterChangeListeners.get(r2).onAdapterChanged(this, r03, r8);
        r2 = r2 + 1;
        goto L25
    L31:
        return;
    L30:
        return;
    L12:
        if (this.mObserver != null) goto L14;
        this.mObserver = new PagerObserver(this);
    L14:
        this.mAdapter.setViewPagerObserver(this.mObserver);
        this.mPopulatePending = false;
        boolean r32 = this.mFirstLayout;
        this.mFirstLayout = true;
        this.mExpectedAdapterCount = this.mAdapter.getCount();
        if (this.mRestoredCurItem < 0) goto L17;
        this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
        setCurrentItemInternal(this.mRestoredCurItem, false, true);
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        goto L20
    L17:
        if (r32 == true) goto L19;
        populate();
        goto L20
    L19:
        requestLayout();
        goto L20
    }

    public void setCurrentItem(int r3) {
        this.mPopulatePending = false;
        setCurrentItemInternal(r3, !this.mFirstLayout, false);
    }

    public void setCurrentItem(int r2, boolean r3) {
        this.mPopulatePending = false;
        setCurrentItemInternal(r2, r3, false);
    }

    public void setCurrentItemInternal(int r2, boolean r3, boolean r4) {
        setCurrentItemInternal(r2, r3, r4, 0);
    }

    public void setCurrentItemInternal(int r5, boolean r6, boolean r7, int r8) {
        PagerAdapter r0 = this.mAdapter;
        boolean r1 = false;
        if (r0 != null) goto L5;
    L40:
        setScrollingCacheEnabled(false);
        return;
    L5:
        if (r0.getCount() <= 0) goto L40;
        if (r7 == true) goto L15;
        if (this.mCurItem != r5) goto L15;
        if (this.mItems.size() == 0) goto L15;
        setScrollingCacheEnabled(false);
        return;
    L15:
        if (r5 >= 0) goto L18;
        r5 = 0;
    L20:
        int r02 = this.mOffscreenPageLimit;
        int r2 = this.mCurItem;
        if (r5 <= (r2 + r02)) goto L23;
    L24:
        int r03 = 0;
    L26:
        if (r03 >= this.mItems.size()) goto L29;
        this.mItems.get(r03).scrolling = true;
        r03 = r03 + 1;
    L29:
        if (this.mCurItem == r5) goto L32;
        r1 = true;
    L32:
        if (this.mFirstLayout == false) goto L38;
        this.mCurItem = r5;
        if (r1 == false) goto L36;
        dispatchOnPageSelected(r5);
    L36:
        requestLayout();
        return;
    L38:
        populate(r5);
        scrollToItem(r5, r6, r8, r1);
        return;
    L23:
        if (r5 >= (r2 - r02)) goto L29;
    L18:
        if (r5 < this.mAdapter.getCount()) goto L20;
        r5 = this.mAdapter.getCount() - 1;
        goto L20
    }

    public void setOffscreenPageLimit(int r4) {
        if (r4 >= 1) goto L6;
        Log.w("ViewPager", "Requested offscreen page limit " + r4 + " too small; defaulting to 1");
        r4 = 1;
    L6:
        if (r4 == this.mOffscreenPageLimit) goto L9;
        this.mOffscreenPageLimit = r4;
        populate();
        return;
    }

    @Deprecated
    public void setOnPageChangeListener(OnPageChangeListener r1) {
        this.mOnPageChangeListener = r1;
    }

    public void setPageMargin(int r3) {
        int r0 = this.mPageMargin;
        this.mPageMargin = r3;
        int r1 = getWidth();
        recomputeScrollPosition(r1, r1, r3, r0);
        requestLayout();
    }

    public void setPageMarginDrawable(int r2) {
        setPageMarginDrawable(ContextCompat.getDrawable(getContext(), r2));
    }

    public void setPageMarginDrawable(Drawable r1) {
        this.mMarginDrawable = r1;
        if (r1 == null) goto L5;
        refreshDrawableState();
    L5:
        if (r1 != null) goto L7;
        boolean r12 = true;
    L8:
        setWillNotDraw(r12);
        invalidate();
        return;
    L7:
        r12 = false;
        goto L8
    }

    public void setPageTransformer(boolean r2, PageTransformer r3) {
        setPageTransformer(r2, r3, 2);
    }

    public void setPageTransformer(boolean r5, PageTransformer r6, int r7) {
        int r1 = 1;
        if (r6 == null) goto L5;
        boolean r2 = true;
    L7:
        if (this.mPageTransformer == null) goto L9;
        boolean r3 = true;
    L10:
        if (r2 == r3) goto L12;
        boolean r32 = true;
    L13:
        this.mPageTransformer = r6;
        setChildrenDrawingOrderEnabled(r2);
        if (r2 == false) goto L18;
        if (r5 == false) goto L17;
        r1 = 2;
    L17:
        this.mDrawingOrder = r1;
        this.mPageTransformerLayerType = r7;
    L19:
        if (r32 == false) goto L22;
        populate();
        return;
    L22:
        return;
    L18:
        this.mDrawingOrder = 0;
        goto L19
    L12:
        r32 = false;
        goto L13
    L9:
        r3 = false;
        goto L10
    L5:
        r2 = false;
        goto L7
    }

    public void setScrollState(int r2) {
        if (this.mScrollState != r2) goto L5;
        return;
    L5:
        this.mScrollState = r2;
        if (this.mPageTransformer == null) goto L11;
        if (r2 == 0) goto L9;
        boolean r0 = true;
    L10:
        enableLayers(r0);
        goto L11
    L9:
        r0 = false;
    L11:
        dispatchOnScrollStateChanged(r2);
    }

    public void smoothScrollTo(int r9, int r10, int r11) {
        if (getChildCount() != 0) goto L6;
        setScrollingCacheEnabled(false);
        return;
    L6:
        Scroller r0 = this.mScroller;
        if (r0 != null) goto L9;
    L16:
        int r02 = getScrollX();
    L15:
        int r3 = r02;
        int r4 = getScrollY();
        int r5 = r9 - r3;
        int r6 = r10 - r4;
        if (r5 != 0) goto L22;
        if (r6 != 0) goto L22;
        completeScroll(false);
        populate();
        setScrollState(0);
        return;
    L22:
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int r92 = getClientWidth();
        int r102 = r92 / 2;
        float r93 = r92;
        float r103 = r102;
        float r104 = r103 + (distanceInfluenceForSnapDuration(Math.min(1.0f, (Math.abs(r5) * 1.0f) / r93)) * r103);
        int r112 = Math.abs(r11);
        if (r112 <= 0) goto L25;
        int r94 = Math.round(Math.abs(r104 / r112) * 1000.0f) * 4;
    L26:
        int r7 = Math.min(r94, MediaError.DetailedErrorCode.TEXT_UNKNOWN);
        this.mIsScrollStarted = false;
        this.mScroller.startScroll(r3, r4, r5, r6, r7);
        ViewCompat.postInvalidateOnAnimation(this);
        return;
    L25:
        r94 = (int) (((Math.abs(r5) / ((r93 * this.mAdapter.getPageWidth(this.mCurItem)) + this.mPageMargin)) + 1.0f) * 100.0f);
        goto L26
    L9:
        if (r0.isFinished() == true) goto L16;
        boolean r03 = this.mIsScrollStarted;
        Scroller r2 = this.mScroller;
        if (r03 == false) goto L13;
        r02 = r2.getCurrX();
    L14:
        this.mScroller.abortAnimation();
        setScrollingCacheEnabled(false);
        goto L15
    L13:
        r02 = r2.getStartX();
        goto L14
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == false) goto L5;
        return true;
    L5:
        if (r2 == this.mMarginDrawable) goto L11;
        return false;
    L11:
        return true;
    }
}
