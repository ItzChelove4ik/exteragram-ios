package com.google.android.material.carousel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.carousel.CarouselStrategy;
import com.google.android.material.carousel.KeylineState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class CarouselLayoutManager extends RecyclerView.LayoutManager implements Carousel, RecyclerView.SmoothScroller.ScrollVectorProvider {
    public static final int ALIGNMENT_CENTER = 1;
    public static final int ALIGNMENT_START = 0;
    public static final int HORIZONTAL = 0;
    private static final String TAG = "CarouselLayoutManager";
    public static final int VERTICAL = 1;
    private int carouselAlignment;
    private CarouselStrategy carouselStrategy;
    private int currentEstimatedPosition;
    private int currentFillStartPosition;
    private KeylineState currentKeylineState;
    private final DebugItemDecoration debugItemDecoration;
    private boolean isDebuggingEnabled;
    private KeylineStateList keylineStateList;
    private Map<Integer, KeylineState> keylineStatePositionMap;
    private int lastItemCount;
    int maxScroll;
    int minScroll;
    private CarouselOrientationHelper orientationHelper;
    private final View.OnLayoutChangeListener recyclerViewSizeChangeListener;
    int scrollOffset;

    /* renamed from: com.google.android.material.carousel.CarouselLayoutManager$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearSmoothScroller {
        final /* synthetic */ CarouselLayoutManager this$0;

        public AnonymousClass1(CarouselLayoutManager r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateDxToMakeVisible(View r1, int r2) {
            if (CarouselLayoutManager.access$000(this.this$0) != null) goto L5;
            return 0;
        L5:
            if (this.this$0.isHorizontal() == false) goto L11;
            CarouselLayoutManager r0 = this.this$0;
            return r0.calculateScrollDeltaToMakePositionVisible(r0.getPosition(r1));
        L11:
            return 0;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateDyToMakeVisible(View r1, int r2) {
            if (CarouselLayoutManager.access$000(this.this$0) != null) goto L5;
            return 0;
        L5:
            if (this.this$0.isHorizontal() == true) goto L11;
            CarouselLayoutManager r0 = this.this$0;
            return r0.calculateScrollDeltaToMakePositionVisible(r0.getPosition(r1));
        L11:
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
        public PointF computeScrollVectorForPosition(int r1) {
            return this.this$0.computeScrollVectorForPosition(r1);
        }
    }

    public static final class ChildCalculations {
        final float center;
        final View child;
        final float offsetCenter;
        final KeylineRange range;

        public ChildCalculations(View r1, float r2, float r3, KeylineRange r4) {
            this.child = r1;
            this.center = r2;
            this.offsetCenter = r3;
            this.range = r4;
        }
    }

    public static class DebugItemDecoration extends RecyclerView.ItemDecoration {
        private List<KeylineState.Keyline> keylines;
        private final Paint linePaint;

        public DebugItemDecoration() {
            Paint r0 = new Paint();
            this.linePaint = r0;
            this.keylines = Collections.unmodifiableList(new ArrayList());
            r0.setStrokeWidth(5.0f);
            r0.setColor(-65281);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDrawOver(Canvas r17, RecyclerView r18, RecyclerView.State r19) {
            super.onDrawOver(r17, r18, r19);
            this.linePaint.setStrokeWidth(r18.getResources().getDimension(R.dimen.m3_carousel_debug_keyline_width));
            Iterator<KeylineState.Keyline> r1 = this.keylines.iterator();
        L4:
            if (r1.hasNext() == false) goto L9;
            KeylineState.Keyline r2 = r1.next();
            this.linePaint.setColor(ColorUtils.blendARGB(-65281, -16776961, r2.mask));
            if (((CarouselLayoutManager) r18.getLayoutManager()).isHorizontal() == true) goto L7;
            r17.drawLine(CarouselLayoutManager.access$300((CarouselLayoutManager) r18.getLayoutManager()), r2.locOffset, CarouselLayoutManager.access$400((CarouselLayoutManager) r18.getLayoutManager()), r2.locOffset, this.linePaint);
            goto L4
        L7:
            r17.drawLine(r2.locOffset, CarouselLayoutManager.access$100((CarouselLayoutManager) r18.getLayoutManager()), r2.locOffset, CarouselLayoutManager.access$200((CarouselLayoutManager) r18.getLayoutManager()), this.linePaint);
            goto L4
        }

        public void setKeylines(List<KeylineState.Keyline> r1) {
            this.keylines = Collections.unmodifiableList(r1);
        }
    }

    public static class KeylineRange {
        final KeylineState.Keyline leftOrTop;
        final KeylineState.Keyline rightOrBottom;

        public KeylineRange(KeylineState.Keyline r3, KeylineState.Keyline r4) {
            if (r3.loc > r4.loc) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkArgument(r0);
            this.leftOrTop = r3;
            this.rightOrBottom = r4;
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    public static class LayoutDirection {
        private static final int INVALID_LAYOUT = Integer.MIN_VALUE;
        private static final int LAYOUT_END = 1;
        private static final int LAYOUT_START = -1;

        private LayoutDirection() {
        }
    }

    public static /* synthetic */ void $r8$lambda$EVyYoVT11DpL60suE9bxns8qbCM(CarouselLayoutManager r0) {
        r0.refreshKeylineState();
    }

    public static /* synthetic */ void $r8$lambda$l_qCTHOyjlXYirl7hEzmLbCJVAg(CarouselLayoutManager r0, View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        r0.getClass();
        if ((r4 - r2) == (r8 - r6)) goto L5;
    L8:
        r1.post(new CarouselLayoutManager$$ExternalSyntheticLambda0(r0));
        return;
    L5:
        if ((r5 - r3) != (r9 - r7)) goto L8;
    }

    public CarouselLayoutManager() {
        this(new MultiBrowseCarouselStrategy());
    }

    @SuppressLint({"UnknownNullness"})
    public CarouselLayoutManager(Context r1, AttributeSet r2, int r3, int r4) {
        this.isDebuggingEnabled = false;
        this.debugItemDecoration = new DebugItemDecoration();
        this.currentFillStartPosition = 0;
        this.recyclerViewSizeChangeListener = new CarouselLayoutManager$$ExternalSyntheticLambda1(this);
        this.currentEstimatedPosition = -1;
        this.carouselAlignment = 0;
        setCarouselStrategy(new MultiBrowseCarouselStrategy());
        setCarouselAttributes(r1, r2);
    }

    public CarouselLayoutManager(CarouselStrategy r2) {
        this(r2, 0);
    }

    public CarouselLayoutManager(CarouselStrategy r3, int r4) {
        this.isDebuggingEnabled = false;
        this.debugItemDecoration = new DebugItemDecoration();
        this.currentFillStartPosition = 0;
        this.recyclerViewSizeChangeListener = new CarouselLayoutManager$$ExternalSyntheticLambda1(this);
        this.currentEstimatedPosition = -1;
        this.carouselAlignment = 0;
        setCarouselStrategy(r3);
        setOrientation(r4);
    }

    public static /* synthetic */ KeylineStateList access$000(CarouselLayoutManager r0) {
        return r0.keylineStateList;
    }

    public static /* synthetic */ int access$100(CarouselLayoutManager r0) {
        return r0.getParentTop();
    }

    public static /* synthetic */ int access$200(CarouselLayoutManager r0) {
        return r0.getParentBottom();
    }

    public static /* synthetic */ int access$300(CarouselLayoutManager r0) {
        return r0.getParentLeft();
    }

    public static /* synthetic */ int access$400(CarouselLayoutManager r0) {
        return r0.getParentRight();
    }

    private void addAndLayoutView(View r3, int r4, ChildCalculations r5) {
        float r0 = this.currentKeylineState.getItemSize() / 2.0f;
        addView(r3, r4);
        measureChildWithMargins(r3, 0, 0);
        float r42 = r5.offsetCenter;
        int r1 = (int) (r42 - r0);
        int r43 = (int) (r42 + r0);
        this.orientationHelper.layoutDecoratedWithMargins(r3, r1, r43);
        updateChildMaskForLocation(r3, r5.center, r5.range);
    }

    private float addEnd(float r1, float r2) {
        if (isLayoutRtl() == false) goto L7;
        return r1 - r2;
    L7:
        return r1 + r2;
    }

    private float addStart(float r1, float r2) {
        if (isLayoutRtl() == false) goto L7;
        return r1 + r2;
    L7:
        return r1 - r2;
    }

    private void addViewAtPosition(RecyclerView.Recycler r2, int r3, int r4) {
        if (r3 >= 0) goto L4;
        return;
    L4:
        if (r3 >= getItemCount()) goto L9;
        ChildCalculations r22 = makeChildCalculations(r2, calculateChildStartForFill(r3), r3);
        addAndLayoutView(r22.child, r4, r22);
        return;
    }

    private void addViewsEnd(RecyclerView.Recycler r7, RecyclerView.State r8, int r9) {
        float r0 = calculateChildStartForFill(r9);
    L4:
        if (r9 >= r8.getItemCount()) goto L13;
        float r1 = addEnd(r0, this.currentKeylineState.getItemSize() / 2.0f);
        KeylineRange r2 = getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), r1, false);
        float r3 = calculateChildOffsetCenterForLocation(r1, r2);
        if (isLocOffsetOutOfFillBoundsEnd(r3, r2) == true) goto L18;
        r0 = addEnd(r0, this.currentKeylineState.getItemSize());
        if (isLocOffsetOutOfFillBoundsStart(r3, r2) == true) goto L12;
        View r4 = r7.getViewForPosition(r9);
        addAndLayoutView(r4, -1, new ChildCalculations(r4, r1, r3, r2));
    L12:
        r9 = r9 + 1;
        goto L4
    L18:
        return;
    }

    private void addViewsStart(RecyclerView.Recycler r8, int r9) {
        float r0 = calculateChildStartForFill(r9);
    L3:
        if (r9 < 0) goto L12;
        float r1 = addEnd(r0, this.currentKeylineState.getItemSize() / 2.0f);
        KeylineRange r2 = getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), r1, false);
        float r4 = calculateChildOffsetCenterForLocation(r1, r2);
        if (isLocOffsetOutOfFillBoundsStart(r4, r2) == true) goto L17;
        r0 = addStart(r0, this.currentKeylineState.getItemSize());
        if (isLocOffsetOutOfFillBoundsEnd(r4, r2) == true) goto L11;
        View r5 = r8.getViewForPosition(r9);
        addAndLayoutView(r5, 0, new ChildCalculations(r5, r1, r4, r2));
    L11:
        r9 = r9 - 1;
        goto L3
    L17:
        return;
    }

    private float calculateChildOffsetCenterForLocation(float r5, KeylineRange r6) {
        KeylineState.Keyline r0 = r6.leftOrTop;
        float r1 = r0.locOffset;
        KeylineState.Keyline r2 = r6.rightOrBottom;
        float r02 = AnimationUtils.lerp(r1, r2.locOffset, r0.loc, r2.loc, r5);
        if (r6.rightOrBottom != this.currentKeylineState.getFirstKeyline()) goto L5;
    L8:
        KeylineState.Keyline r4 = r6.rightOrBottom;
        return r02 + ((r5 - r4.loc) * (1.0f - r4.mask));
    L5:
        if (r6.leftOrTop == this.currentKeylineState.getLastKeyline()) goto L8;
        return r02;
    }

    private float calculateChildStartForFill(int r3) {
        return addEnd(getParentStart() - this.scrollOffset, this.currentKeylineState.getItemSize() * r3);
    }

    private int calculateEndScroll(RecyclerView.State r4, KeylineStateList r5) {
        boolean r0 = isLayoutRtl();
        if (r0 == false) goto L5;
        KeylineState r52 = r5.getStartState();
    L6:
        if (r0 == false) goto L8;
        KeylineState.Keyline r1 = r52.getFirstFocalKeyline();
    L9:
        int r2 = 1;
        float r42 = (r4.getItemCount() - 1) * r52.getItemSize();
        if (r0 == false) goto L12;
        float r53 = -1.0f;
    L13:
        float r43 = (r42 * r53) - (r1.loc - getParentStart());
        if (r0 == false) goto L16;
        r2 = -1;
    L16:
        int r3 = (int) (r43 + ((r2 * r1.maskedItemSize) / 2.0f));
        if (r0 == false) goto L21;
        return Math.min(0, r3);
    L21:
        return Math.max(0, r3);
    L12:
        r53 = 1.0f;
        goto L13
    L8:
        r1 = r52.getLastFocalKeyline();
        goto L9
    L5:
        r52 = r5.getEndState();
        goto L6
    }

    private static int calculateShouldScrollBy(int r1, int r2, int r3, int r4) {
        int r0 = r2 + r1;
        if (r0 < r3) goto L5;
        if (r0 > r4) goto L8;
        return r1;
    L8:
        return r4 - r2;
    L5:
        return r3 - r2;
    }

    private int calculateStartScroll(KeylineStateList r3) {
        boolean r0 = isLayoutRtl();
        if (r0 == false) goto L5;
        KeylineState r32 = r3.getEndState();
    L6:
        if (r0 == false) goto L8;
        KeylineState.Keyline r02 = r32.getLastFocalKeyline();
    L10:
        return (int) (getParentStart() - addStart(r02.loc, r32.getItemSize() / 2.0f));
    L8:
        r02 = r32.getFirstFocalKeyline();
        goto L10
    L5:
        r32 = r3.getStartState();
        goto L6
    }

    private int convertFocusDirectionToLayoutDirection(int r6) {
        int r0 = getOrientation();
        if (r6 != 1) goto L5;
        return -1;
    L5:
        if (r6 != 2) goto L7;
        return 1;
    L7:
        if (r6 != 17) goto L9;
        if (r0 == 0) goto L30;
        return Integer.MIN_VALUE;
    L30:
        if (isLayoutRtl() == false) goto L32;
        return 1;
    L32:
        return -1;
    L9:
        if (r6 != 33) goto L11;
        if (r0 != 1) goto L27;
        return -1;
    L27:
        return Integer.MIN_VALUE;
    L11:
        if (r6 != 66) goto L13;
        if (r0 == 0) goto L21;
        return Integer.MIN_VALUE;
    L21:
        if (isLayoutRtl() == false) goto L23;
        return -1;
    L23:
        return 1;
    L13:
        if (r6 == 130) goto L16;
        Log.d(TAG, "Unknown focus request:" + r6);
        return Integer.MIN_VALUE;
    L16:
        if (r0 != 1) goto L18;
        return 1;
    L18:
        return Integer.MIN_VALUE;
    }

    private void fill(RecyclerView.Recycler r3, RecyclerView.State r4) {
        removeAndRecycleOutOfBoundsViews(r3);
        if (getChildCount() != 0) goto L5;
        addViewsStart(r3, this.currentFillStartPosition - 1);
        addViewsEnd(r3, r4, this.currentFillStartPosition);
    L6:
        validateChildOrderIfDebugging();
        return;
    L5:
        int r0 = getPosition(getChildAt(0));
        int r1 = getPosition(getChildAt(getChildCount() - 1));
        addViewsStart(r3, r0 - 1);
        addViewsEnd(r3, r4, r1 + 1);
        goto L6
    }

    private View getChildClosestToEnd() {
        if (isLayoutRtl() == false) goto L5;
        int r0 = 0;
    L7:
        return getChildAt(r0);
    L5:
        r0 = getChildCount() - 1;
        goto L7
    }

    private View getChildClosestToStart() {
        if (isLayoutRtl() == false) goto L5;
        int r0 = getChildCount() - 1;
    L7:
        return getChildAt(r0);
    L5:
        r0 = 0;
        goto L7
    }

    private int getContainerSize() {
        if (isHorizontal() == false) goto L7;
        return getContainerWidth();
    L7:
        return getContainerHeight();
    }

    private float getDecoratedCenterWithMargins(View r2) {
        Rect r0 = new Rect();
        super.getDecoratedBoundsWithMargins(r2, r0);
        if (isHorizontal() == false) goto L7;
        int r1 = r0.centerX();
    L6:
        return r1;
    L7:
        r1 = r0.centerY();
        goto L6
    }

    private int getItemMargins() {
        if (getChildCount() <= 0) goto L10;
        RecyclerView.LayoutParams r0 = (RecyclerView.LayoutParams) getChildAt(0).getLayoutParams();
        if (this.orientationHelper.orientation != 0) goto L9;
        int r2 = ((ViewGroup.MarginLayoutParams) r0).leftMargin;
        int r02 = ((ViewGroup.MarginLayoutParams) r0).rightMargin;
    L8:
        return r2 + r02;
    L9:
        r2 = ((ViewGroup.MarginLayoutParams) r0).topMargin;
        r02 = ((ViewGroup.MarginLayoutParams) r0).bottomMargin;
        goto L8
    L10:
        return 0;
    }

    private KeylineState getKeylineStartingState(KeylineStateList r1) {
        if (isLayoutRtl() == false) goto L7;
        return r1.getEndState();
    L7:
        return r1.getStartState();
    }

    private KeylineState getKeylineStateForPosition(int r4) {
        Map<Integer, KeylineState> r0 = this.keylineStatePositionMap;
        if (r0 == null) goto L8;
        KeylineState r42 = r0.get(Integer.valueOf(MathUtils.clamp(r4, 0, Math.max(0, getItemCount() - 1))));
        if (r42 == null) goto L8;
        return r42;
    L8:
        return this.keylineStateList.getDefaultState();
    }

    private int getLeftOrTopPaddingForKeylineShift() {
        if (getClipToPadding() == false) goto L7;
        return 0;
    L7:
        if (getOrientation() != 1) goto L11;
        return getPaddingTop();
    L11:
        return getPaddingLeft();
    }

    private float getMaskedItemSizeForLocOffset(float r3, KeylineRange r4) {
        KeylineState.Keyline r2 = r4.leftOrTop;
        float r0 = r2.maskedItemSize;
        KeylineState.Keyline r42 = r4.rightOrBottom;
        return AnimationUtils.lerp(r0, r42.maskedItemSize, r2.locOffset, r42.locOffset, r3);
    }

    private int getParentBottom() {
        return this.orientationHelper.getParentBottom();
    }

    private int getParentLeft() {
        return this.orientationHelper.getParentLeft();
    }

    private int getParentRight() {
        return this.orientationHelper.getParentRight();
    }

    private int getParentStart() {
        return this.orientationHelper.getParentStart();
    }

    private int getParentTop() {
        return this.orientationHelper.getParentTop();
    }

    private int getRightOrBottomPaddingForKeylineShift() {
        if (getClipToPadding() == false) goto L7;
        return 0;
    L7:
        if (getOrientation() != 1) goto L11;
        return getPaddingBottom();
    L11:
        return getPaddingRight();
    }

    private int getScrollOffsetForPosition(int r3, KeylineState r4) {
        if (isLayoutRtl() == false) goto L7;
        float r2 = ((getContainerSize() - r4.getLastFocalKeyline().loc) - (r3 * r4.getItemSize())) - (r4.getItemSize() / 2.0f);
    L6:
        return (int) r2;
    L7:
        r2 = ((r3 * r4.getItemSize()) - r4.getFirstFocalKeyline().loc) + (r4.getItemSize() / 2.0f);
        goto L6
    }

    private int getSmallestScrollOffsetToFocalKeyline(int r7, KeylineState r8) {
        Iterator<KeylineState.Keyline> r0 = r8.getFocalKeylines().iterator();
        int r1 = Integer.MAX_VALUE;
    L4:
        if (r0.hasNext() == false) goto L12;
        KeylineState.Keyline r2 = r0.next();
        float r3 = (r7 * r8.getItemSize()) + (r8.getItemSize() / 2.0f);
        if (isLayoutRtl() == false) goto L8;
        int r22 = (int) ((getContainerSize() - r2.loc) - r3);
    L9:
        int r23 = r22 - this.scrollOffset;
        if (Math.abs(r1) <= Math.abs(r23)) goto L4;
        r1 = r23;
        goto L4
    L8:
        r22 = (int) (r3 - r2.loc);
        goto L9
    L12:
        return r1;
    }

    private static KeylineRange getSurroundingKeylineRange(List<KeylineState.Keyline> r13, float r14, boolean r15) {
        float r1 = Float.MAX_VALUE;
        int r6 = -1;
        int r7 = -1;
        int r8 = -1;
        int r9 = -1;
        float r4 = -3.4028235E38f;
        int r5 = 0;
        float r2 = Float.MAX_VALUE;
        float r3 = Float.MAX_VALUE;
    L4:
        if (r5 >= r13.size()) goto L26;
        KeylineState.Keyline r10 = r13.get(r5);
        if (r15 == false) goto L8;
        float r102 = r10.locOffset;
    L9:
        float r11 = Math.abs(r102 - r14);
        if (r102 > r14) goto L15;
        if (r11 > r1) goto L15;
        r6 = r5;
        r1 = r11;
    L15:
        if (r102 <= r14) goto L20;
        if (r11 > r2) goto L20;
        r8 = r5;
        r2 = r11;
    L20:
        if (r102 > r3) goto L23;
        r7 = r5;
        r3 = r102;
    L23:
        if (r102 <= r4) goto L25;
        r9 = r5;
        r4 = r102;
    L25:
        r5 = r5 + 1;
        goto L4
    L8:
        r102 = r10.loc;
        goto L9
    L26:
        if (r6 != (-1)) goto L28;
        r6 = r7;
    L28:
        if (r8 != (-1)) goto L31;
        r8 = r9;
    L31:
        return new KeylineRange(r13.get(r6), r13.get(r8));
    }

    private boolean isLocOffsetOutOfFillBoundsEnd(float r3, KeylineRange r4) {
        float r32 = addStart(r3, getMaskedItemSizeForLocOffset(r3, r4) / 2.0f);
        if (isLayoutRtl() == false) goto L9;
        if (r32 >= 0.0f) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (r32 <= getContainerSize()) goto L11;
        return true;
    L11:
        return false;
    }

    private boolean isLocOffsetOutOfFillBoundsStart(float r3, KeylineRange r4) {
        float r32 = addEnd(r3, getMaskedItemSizeForLocOffset(r3, r4) / 2.0f);
        if (isLayoutRtl() == false) goto L9;
        if (r32 <= getContainerSize()) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (r32 >= 0.0f) goto L11;
        return true;
    L11:
        return false;
    }

    private void logChildrenIfDebugging() {
        if (this.isDebuggingEnabled == true) goto L6;
        return;
    L6:
        if (Log.isLoggable(TAG, 3) == false) goto L14;
        Log.d(TAG, "internal representation of views on the screen");
        int r0 = 0;
    L9:
        if (r0 >= getChildCount()) goto L11;
        View r2 = getChildAt(r0);
        Log.d(TAG, "item position " + getPosition(r2) + ", center:" + getDecoratedCenterWithMargins(r2) + ", child index:" + r0);
        r0 = r0 + 1;
        goto L9
    L11:
        Log.d(TAG, "==============");
        return;
    }

    private ChildCalculations makeChildCalculations(RecyclerView.Recycler r3, float r4, int r5) {
        View r32 = r3.getViewForPosition(r5);
        measureChildWithMargins(r32, 0, 0);
        float r42 = addEnd(r4, this.currentKeylineState.getItemSize() / 2.0f);
        KeylineRange r52 = getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), r42, false);
        return new ChildCalculations(r32, r42, calculateChildOffsetCenterForLocation(r42, r52), r52);
    }

    private float offsetChild(View r3, float r4, float r5, Rect r6) {
        float r42 = addEnd(r4, r5);
        KeylineRange r0 = getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), r42, false);
        float r1 = calculateChildOffsetCenterForLocation(r42, r0);
        super.getDecoratedBoundsWithMargins(r3, r6);
        updateChildMaskForLocation(r3, r42, r0);
        this.orientationHelper.offsetChild(r3, r6, r5, r1);
        return r1;
    }

    private void recalculateKeylineStateList(RecyclerView.Recycler r7) {
        View r72 = r7.getViewForPosition(0);
        measureChildWithMargins(r72, 0, 0);
        KeylineState r73 = this.carouselStrategy.onFirstChildMeasuredWithMargins(this, r72);
        if (isLayoutRtl() == false) goto L5;
        r73 = KeylineState.reverse(r73, getContainerSize());
    L5:
        this.keylineStateList = KeylineStateList.from(this, r73, getItemMargins(), getLeftOrTopPaddingForKeylineShift(), getRightOrBottomPaddingForKeylineShift(), this.carouselStrategy.getStrategyType());
    }

    private void refreshKeylineState() {
        this.keylineStateList = null;
        requestLayout();
    }

    private void removeAndRecycleOutOfBoundsViews(RecyclerView.Recycler r5) {
    L3:
        if (getChildCount() <= 0) goto L8;
        View r0 = getChildAt(0);
        float r2 = getDecoratedCenterWithMargins(r0);
        if (isLocOffsetOutOfFillBoundsStart(r2, getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), r2, true)) == false) goto L8;
        removeAndRecycleView(r0, r5);
    L8:
        if ((getChildCount() - 1) < 0) goto L12;
        View r02 = getChildAt(getChildCount() - 1);
        float r22 = getDecoratedCenterWithMargins(r02);
        if (isLocOffsetOutOfFillBoundsEnd(r22, getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), r22, true)) == false) goto L18;
        removeAndRecycleView(r02, r5);
        goto L8
    L18:
        return;
    }

    private int scrollBy(int r10, RecyclerView.Recycler r11, RecyclerView.State r12) {
        int r1 = 0;
        if (getChildCount() == 0) goto L27;
        if (r10 == 0) goto L27;
        if (this.keylineStateList != null) goto L10;
        recalculateKeylineStateList(r11);
    L10:
        if (getItemCount() > getKeylineStartingState(this.keylineStateList).getTotalVisibleFocalItems()) goto L12;
        return 0;
    L12:
        int r102 = calculateShouldScrollBy(r10, this.scrollOffset, this.minScroll, this.maxScroll);
        this.scrollOffset += r102;
        updateCurrentKeylineStateForScrollOffset(this.keylineStateList);
        float r0 = this.currentKeylineState.getItemSize() / 2.0f;
        float r2 = calculateChildStartForFill(getPosition(getChildAt(0)));
        Rect r3 = new Rect();
        boolean r4 = isLayoutRtl();
        KeylineState r5 = this.currentKeylineState;
        if (r4 == false) goto L15;
        float r42 = r5.getLastFocalKeyline().locOffset;
    L16:
        float r52 = Float.MAX_VALUE;
    L18:
        if (r1 >= getChildCount()) goto L25;
        View r6 = getChildAt(r1);
        float r7 = Math.abs(r42 - offsetChild(r6, r2, r0, r3));
        if (r6 == null) goto L24;
        if (r7 >= r52) goto L24;
        this.currentEstimatedPosition = getPosition(r6);
        r52 = r7;
    L24:
        r2 = addEnd(r2, this.currentKeylineState.getItemSize());
        r1 = r1 + 1;
        goto L18
    L25:
        fill(r11, r12);
        return r102;
    L15:
        r42 = r5.getFirstFocalKeyline().locOffset;
    L27:
        return 0;
    }

    private void scrollBy(RecyclerView r2, int r3) {
        if (isHorizontal() == false) goto L6;
        r2.scrollBy(r3, 0);
        return;
    L6:
        r2.scrollBy(0, r3);
    }

    private void setCarouselAttributes(Context r2, AttributeSet r3) {
        if (r3 == null) goto L5;
        TypedArray r22 = r2.obtainStyledAttributes(r3, R.styleable.Carousel);
        setCarouselAlignment(r22.getInt(R.styleable.Carousel_carousel_alignment, 0));
        setOrientation(r22.getInt(androidx.recyclerview.R.styleable.RecyclerView_android_orientation, 0));
        r22.recycle();
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void updateChildMaskForLocation(View r9, float r10, KeylineRange r11) {
        if ((r9 instanceof Maskable) == true) goto L5;
        return;
    L5:
        KeylineState.Keyline r0 = r11.leftOrTop;
        float r1 = r0.mask;
        KeylineState.Keyline r2 = r11.rightOrBottom;
        float r02 = AnimationUtils.lerp(r1, r2.mask, r0.loc, r2.loc, r10);
        float r12 = r9.getHeight();
        float r22 = r9.getWidth();
        float r4 = AnimationUtils.lerp(0.0f, r22 / 2.0f, 0.0f, 1.0f, r02);
        RectF r03 = this.orientationHelper.getMaskRect(r12, r22, AnimationUtils.lerp(0.0f, r12 / 2.0f, 0.0f, 1.0f, r02), r4);
        float r102 = calculateChildOffsetCenterForLocation(r10, r11);
        RectF r3 = new RectF(r102 - (r03.width() / 2.0f), r102 - (r03.height() / 2.0f), r102 + (r03.width() / 2.0f), (r03.height() / 2.0f) + r102);
        RectF r103 = new RectF(getParentLeft(), getParentTop(), getParentRight(), getParentBottom());
        if (this.carouselStrategy.getStrategyType() != CarouselStrategy.StrategyType.CONTAINED) goto L8;
        this.orientationHelper.containMaskWithinBounds(r03, r3, r103);
    L8:
        this.orientationHelper.moveMaskOnEdgeOutsideBounds(r03, r3, r103);
        ((Maskable) r9).setMaskRectF(r03);
    }

    private void updateCurrentKeylineStateForScrollOffset(KeylineStateList r4) {
        int r0 = this.maxScroll;
        int r1 = this.minScroll;
        if (r0 > r1) goto L5;
        this.currentKeylineState = getKeylineStartingState(r4);
    L6:
        this.debugItemDecoration.setKeylines(this.currentKeylineState.getKeylines());
        return;
    L5:
        this.currentKeylineState = r4.getShiftedState(this.scrollOffset, r1, r0);
        goto L6
    }

    private void updateItemCount() {
        int r0 = getItemCount();
        int r1 = this.lastItemCount;
        if (r0 != r1) goto L5;
        return;
    L5:
        if (this.keylineStateList != null) goto L8;
        return;
    L8:
        if (this.carouselStrategy.shouldRefreshKeylineState(this, r1) == false) goto L10;
        refreshKeylineState();
    L10:
        this.lastItemCount = r0;
    }

    private void validateChildOrderIfDebugging() {
        if (this.isDebuggingEnabled == true) goto L5;
        return;
    L5:
        if (getChildCount() < 1) goto L18;
        int r0 = 0;
    L9:
        if (r0 >= (getChildCount() - 1)) goto L19;
        int r2 = getPosition(getChildAt(r0));
        int r3 = r0 + 1;
        int r4 = getPosition(getChildAt(r3));
        if (r2 > r4) goto L13;
        r0 = r3;
        goto L9
    L13:
        logChildrenIfDebugging();
        throw new IllegalStateException("Detected invalid child order. Child at index [" + r0 + "] had adapter position [" + r2 + "] and child at index [" + r3 + "] had adapter position [" + r4 + "].");
    L19:
        return;
    }

    public int calculateScrollDeltaToMakePositionVisible(int r2) {
        return (int) (this.scrollOffset - getScrollOffsetForPosition(r2, getKeylineStateForPosition(r2)));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return isHorizontal();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return !isHorizontal();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State r3) {
        if (getChildCount() != 0) goto L5;
        return 0;
    L5:
        if (this.keylineStateList != null) goto L7;
        return 0;
    L7:
        if (getItemCount() > 1) goto L10;
        return 0;
    L10:
        return (int) (getWidth() * (this.keylineStateList.getDefaultState().getItemSize() / computeHorizontalScrollRange(r3)));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State r1) {
        return this.scrollOffset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State r1) {
        return this.maxScroll - this.minScroll;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int r2) {
        if (this.keylineStateList != null) goto L6;
        return null;
    L6:
        int r22 = getOffsetToScrollToPosition(r2, getKeylineStateForPosition(r2));
        if (isHorizontal() == false) goto L11;
        return new PointF(r22, 0.0f);
    L11:
        return new PointF(0.0f, r22);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State r3) {
        if (getChildCount() != 0) goto L5;
        return 0;
    L5:
        if (this.keylineStateList != null) goto L7;
        return 0;
    L7:
        if (getItemCount() > 1) goto L10;
        return 0;
    L10:
        return (int) (getHeight() * (this.keylineStateList.getDefaultState().getItemSize() / computeVerticalScrollRange(r3)));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State r1) {
        return this.scrollOffset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State r1) {
        return this.maxScroll - this.minScroll;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // com.google.android.material.carousel.Carousel
    public int getCarouselAlignment() {
        return this.carouselAlignment;
    }

    @Override // com.google.android.material.carousel.Carousel
    public int getContainerHeight() {
        return getHeight();
    }

    @Override // com.google.android.material.carousel.Carousel
    public int getContainerWidth() {
        return getWidth();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void getDecoratedBoundsWithMargins(View r4, Rect r5) {
        super.getDecoratedBoundsWithMargins(r4, r5);
        float r42 = r5.centerY();
        if (isHorizontal() == false) goto L5;
        r42 = r5.centerX();
    L5:
        float r43 = getMaskedItemSizeForLocOffset(r42, getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), r42, true));
        float r1 = 0.0f;
        if (isHorizontal() == false) goto L8;
        float r0 = (r5.width() - r43) / 2.0f;
    L10:
        if (isHorizontal() == true) goto L13;
        r1 = (r5.height() - r43) / 2.0f;
    L13:
        r5.set((int) (r5.left + r0), (int) (r5.top + r1), (int) (r5.right - r0), (int) (r5.bottom - r1));
        return;
    L8:
        r0 = 0.0f;
        goto L10
    }

    public int getOffsetToScrollToPosition(int r1, KeylineState r2) {
        return getScrollOffsetForPosition(r1, r2) - this.scrollOffset;
    }

    public int getOffsetToScrollToPositionForSnap(int r6, boolean r7) {
        int r0 = getOffsetToScrollToPosition(r6, this.keylineStateList.getShiftedState(this.scrollOffset, this.minScroll, this.maxScroll, true));
        if (this.keylineStatePositionMap == null) goto L5;
        int r5 = getOffsetToScrollToPosition(r6, getKeylineStateForPosition(r6));
    L6:
        if (r7 == true) goto L8;
    L10:
        return r0;
    L8:
        if (Math.abs(r5) >= Math.abs(r0)) goto L10;
        return r5;
    L5:
        r5 = r0;
        goto L6
    }

    public int getOrientation() {
        return this.orientationHelper.orientation;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    @Override // com.google.android.material.carousel.Carousel
    public boolean isHorizontal() {
        if (this.orientationHelper.orientation != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isLayoutRtl() {
        if (isHorizontal() == true) goto L5;
        return false;
    L5:
        if (getLayoutDirection() != 1) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void measureChildWithMargins(View r8, int r9, int r10) {
        if ((r8 instanceof Maskable) == false) goto L18;
        RecyclerView.LayoutParams r0 = (RecyclerView.LayoutParams) r8.getLayoutParams();
        Rect r1 = new Rect();
        calculateItemDecorationsForChild(r8, r1);
        int r92 = r9 + (r1.left + r1.right);
        int r102 = r10 + (r1.top + r1.bottom);
        KeylineStateList r12 = this.keylineStateList;
        if (r12 != null) goto L7;
    L9:
        float r13 = ((ViewGroup.MarginLayoutParams) r0).width;
    L10:
        KeylineStateList r2 = this.keylineStateList;
        if (r2 != null) goto L13;
    L15:
        float r22 = ((ViewGroup.MarginLayoutParams) r0).height;
    L16:
        r8.measure(RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), (((getPaddingLeft() + getPaddingRight()) + ((ViewGroup.MarginLayoutParams) r0).leftMargin) + ((ViewGroup.MarginLayoutParams) r0).rightMargin) + r92, (int) r13, canScrollHorizontally()), RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), (((getPaddingTop() + getPaddingBottom()) + ((ViewGroup.MarginLayoutParams) r0).topMargin) + ((ViewGroup.MarginLayoutParams) r0).bottomMargin) + r102, (int) r22, canScrollVertically()));
        return;
    L13:
        if (this.orientationHelper.orientation != 1) goto L15;
        r22 = r2.getDefaultState().getItemSize();
        goto L16
    L7:
        if (this.orientationHelper.orientation != 0) goto L9;
        r13 = r12.getDefaultState().getItemSize();
        goto L10
    L18:
        Segment$$ExternalSyntheticBUOutline1.m("All children of a RecyclerView using CarouselLayoutManager must use MaskableFrameLayout as their root ViewGroup.");
    }

    public void notifyItemSizeChanged() {
        refreshKeylineState();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(RecyclerView r3) {
        super.onAttachedToWindow(r3);
        this.carouselStrategy.initialize(r3.getContext());
        refreshKeylineState();
        r3.addOnLayoutChangeListener(this.recyclerViewSizeChangeListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView r1, RecyclerView.Recycler r2) {
        super.onDetachedFromWindow(r1, r2);
        r1.removeOnLayoutChangeListener(this.recyclerViewSizeChangeListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onFocusSearchFailed(View r2, int r3, RecyclerView.Recycler r4, RecyclerView.State r5) {
        if (getChildCount() != 0) goto L5;
        return null;
    L5:
        int r32 = convertFocusDirectionToLayoutDirection(r3);
        if (r32 != Integer.MIN_VALUE) goto L9;
        return null;
    L9:
        if (r32 != (-1)) goto L16;
        if (getPosition(r2) != 0) goto L13;
        return null;
    L13:
        addViewAtPosition(r4, getPosition(getChildAt(0)) - 1, 0);
        return getChildClosestToStart();
    L16:
        if (getPosition(r2) != (getItemCount() - 1)) goto L18;
        return null;
    L18:
        addViewAtPosition(r4, getPosition(getChildAt(getChildCount() - 1)) + 1, -1);
        return getChildClosestToEnd();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent r2) {
        super.onInitializeAccessibilityEvent(r2);
        if (getChildCount() <= 0) goto L6;
        r2.setFromIndex(getPosition(getChildAt(0)));
        r2.setToIndex(getPosition(getChildAt(getChildCount() - 1)));
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView r1, int r2, int r3) {
        super.onItemsAdded(r1, r2, r3);
        updateItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView r1) {
        super.onItemsChanged(r1);
        updateItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView r1, int r2, int r3) {
        super.onItemsRemoved(r1, r2, r3);
        updateItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler r7, RecyclerView.State r8) {
        if (r8.getItemCount() > 0) goto L5;
    L29:
        removeAndRecycleAllViews(r7);
        this.currentFillStartPosition = 0;
        return;
    L5:
        if (getContainerSize() <= 0.0f) goto L29;
        boolean r0 = isLayoutRtl();
        KeylineStateList r2 = this.keylineStateList;
        if (r2 != null) goto L10;
        boolean r3 = true;
    L11:
        if (r3 == false) goto L13;
    L14:
        recalculateKeylineStateList(r7);
    L15:
        int r22 = calculateStartScroll(this.keylineStateList);
        int r4 = calculateEndScroll(r8, this.keylineStateList);
        if (r0 == false) goto L18;
        int r5 = r4;
    L19:
        this.minScroll = r5;
        if (r0 == false) goto L22;
        r4 = r22;
    L22:
        this.maxScroll = r4;
        if (r3 == false) goto L27;
        this.scrollOffset = r22;
        this.keylineStatePositionMap = this.keylineStateList.getKeylineStateForPositionMap(getItemCount(), this.minScroll, this.maxScroll, isLayoutRtl());
        int r02 = this.currentEstimatedPosition;
        if (r02 == (-1)) goto L27;
        this.scrollOffset = getScrollOffsetForPosition(r02, getKeylineStateForPosition(r02));
    L27:
        int r03 = this.scrollOffset;
        this.scrollOffset = r03 + calculateShouldScrollBy(0, r03, this.minScroll, this.maxScroll);
        this.currentFillStartPosition = MathUtils.clamp(this.currentFillStartPosition, 0, r8.getItemCount());
        updateCurrentKeylineStateForScrollOffset(this.keylineStateList);
        detachAndScrapAttachedViews(r7);
        fill(r7, r8);
        this.lastItemCount = getItemCount();
        return;
    L18:
        r5 = r22;
        goto L19
    L13:
        if (r2.getDefaultState().getCarouselSize() == getContainerSize()) goto L15;
    L10:
        r3 = false;
        goto L11
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State r2) {
        super.onLayoutCompleted(r2);
        if (getChildCount() != 0) goto L5;
        this.currentFillStartPosition = 0;
    L6:
        validateChildOrderIfDebugging();
        return;
    L5:
        this.currentFillStartPosition = getPosition(getChildAt(0));
        goto L6
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean requestChildRectangleOnScreen(RecyclerView r2, View r3, Rect r4, boolean r5, boolean r6) {
        if (this.keylineStateList != null) goto L5;
        return false;
    L5:
        int r42 = getSmallestScrollOffsetToFocalKeyline(getPosition(r3), getKeylineStateForPosition(getPosition(r3)));
        if (r42 != 0) goto L8;
        return false;
    L8:
        int r43 = calculateShouldScrollBy(r42, this.scrollOffset, this.minScroll, this.maxScroll);
        scrollBy(r2, getSmallestScrollOffsetToFocalKeyline(getPosition(r3), this.keylineStateList.getShiftedState(this.scrollOffset + r43, this.minScroll, this.maxScroll)));
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int r2, RecyclerView.Recycler r3, RecyclerView.State r4) {
        if (canScrollHorizontally() == true) goto L5;
        return 0;
    L5:
        return scrollBy(r2, r3, r4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int r3) {
        this.currentEstimatedPosition = r3;
        if (this.keylineStateList != null) goto L5;
        return;
    L5:
        this.scrollOffset = getScrollOffsetForPosition(r3, getKeylineStateForPosition(r3));
        this.currentFillStartPosition = MathUtils.clamp(r3, 0, Math.max(0, getItemCount() - 1));
        updateCurrentKeylineStateForScrollOffset(this.keylineStateList);
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int r2, RecyclerView.Recycler r3, RecyclerView.State r4) {
        if (canScrollVertically() == true) goto L5;
        return 0;
    L5:
        return scrollBy(r2, r3, r4);
    }

    public void setCarouselAlignment(int r1) {
        this.carouselAlignment = r1;
        refreshKeylineState();
    }

    public void setCarouselStrategy(CarouselStrategy r1) {
        this.carouselStrategy = r1;
        refreshKeylineState();
    }

    public void setDebuggingEnabled(RecyclerView r2, boolean r3) {
        this.isDebuggingEnabled = r3;
        r2.removeItemDecoration(this.debugItemDecoration);
        if (r3 == false) goto L5;
        r2.addItemDecoration(this.debugItemDecoration);
    L5:
        r2.invalidateItemDecorations();
    }

    public void setOrientation(int r2) {
        if (r2 != 0) goto L4;
    L8:
        assertNotInLayoutOrScroll(null);
        CarouselOrientationHelper r0 = this.orientationHelper;
        if (r0 != null) goto L11;
    L14:
        this.orientationHelper = CarouselOrientationHelper.createOrientationHelper(this, r2);
        refreshKeylineState();
        return;
    L11:
        if (r2 != r0.orientation) goto L14;
        return;
    L4:
        if (r2 == 1) goto L8;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("invalid orientation:", r2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView r1, RecyclerView.State r2, int r3) {
        AnonymousClass1 r22 = new AnonymousClass1(this, r1.getContext());
        r22.setTargetPosition(r3);
        startSmoothScroll(r22);
    }
}
