package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.timepicker.TimeModel;
import java.util.Locale;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class NumberPicker extends LinearLayout {
    private static final CubicBezierInterpolator interpolator = null;
    private int SELECTOR_MIDDLE_ITEM_INDEX;
    private int SELECTOR_WHEEL_ITEM_COUNT;
    private SeekBarAccessibilityDelegate accessibilityDelegate;
    private Integer allItemsCount;
    private Utilities.CallbackReturn<Integer, CharSequence> contentDescriptionCallback;
    private boolean drawDividers;
    private Scroller mAdjustScroller;
    private int mBottomSelectionDividerBottom;
    private ChangeCurrentByOneFromLongPressCommand mChangeCurrentByOneFromLongPressCommand;
    private boolean mComputeMaxWidth;
    private int mCurrentScrollOffset;
    private boolean mDecrementVirtualButtonPressed;
    private String[] mDisplayedValues;
    private int mFantomValue;
    private Scroller mFlingScroller;
    private Formatter mFormatter;
    private boolean mIncrementVirtualButtonPressed;
    private boolean mIngonreMoveEvents;
    private int mInitialScrollOffset;
    private TextView mInputText;
    private long mLastDownEventTime;
    private float mLastDownEventY;
    private float mLastDownOrMoveEventY;
    private int mLastHandledDownDpadKeyCode;
    private long mLongPressUpdateInterval;
    private int mMaxHeight;
    private int mMaxValue;
    private boolean mMaxValueSet;
    private int mMaxWidth;
    private int mMaximumFlingVelocity;
    private int mMinHeight;
    private int mMinValue;
    private boolean mMinValueSet;
    private int mMinWidth;
    private int mMinimumFlingVelocity;
    private OnScrollListener mOnScrollListener;
    private OnValueChangeListener mOnValueChangeListener;
    private PressedStateHelper mPressedStateHelper;
    private int mPreviousScrollerY;
    private int mScrollState;
    private Paint mSelectionDivider;
    private int mSelectionDividerHeight;
    private int mSelectionDividersDistance;
    private int mSelectorElementHeight;
    private final SparseArray<String> mSelectorIndexToStringCache;
    private int[] mSelectorIndices;
    private int mSelectorTextGapHeight;
    private Paint mSelectorWheelPaint;
    private int mSolidColor;
    private int mTextSize;
    private int mTopSelectionDividerTop;
    private int mTouchSlop;
    private int mValue;
    private VelocityTracker mVelocityTracker;
    private boolean mWrapSelectorWheel;
    private boolean mWrapSelectorWheelSetting;
    private final Theme.ResourcesProvider resourcesProvider;
    private int textOffset;
    private int thisGravity;

    /* renamed from: org.telegram.ui.Components.NumberPicker$1, reason: invalid class name */
    public class AnonymousClass1 extends SeekBarAccessibilityDelegate {
        final /* synthetic */ NumberPicker this$0;

        public AnonymousClass1(NumberPicker r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.SeekBarAccessibilityDelegate
        public boolean canScrollBackward(View r1) {
            return true;
        }

        @Override // org.telegram.ui.Components.SeekBarAccessibilityDelegate
        public boolean canScrollForward(View r1) {
            return true;
        }

        @Override // org.telegram.ui.Components.SeekBarAccessibilityDelegate
        public void doScroll(View r1, boolean r2) {
            this.this$0.changeValueByOne(!r2);
        }

        @Override // org.telegram.ui.Components.SeekBarAccessibilityDelegate
        public CharSequence getContentDescription(View r2) {
            Utilities.CallbackReturn r22 = NumberPicker.m11303$$Nest$fgetcontentDescriptionCallback(this.this$0);
            NumberPicker r0 = this.this$0;
            if (r22 == null) goto L7;
            return (CharSequence) NumberPicker.m11303$$Nest$fgetcontentDescriptionCallback(r0).run(Integer.valueOf(NumberPicker.m11309$$Nest$fgetmValue(this.this$0)));
        L7:
            return r0.getContentDescription(NumberPicker.m11309$$Nest$fgetmValue(r0));
        }
    }

    public class ChangeCurrentByOneFromLongPressCommand implements Runnable {
        private boolean mIncrement;
        final /* synthetic */ NumberPicker this$0;

        /* renamed from: -$$Nest$msetStep, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11312$$Nest$msetStep(ChangeCurrentByOneFromLongPressCommand r0, boolean r1) {
            r0.setStep(r1);
        }

        public ChangeCurrentByOneFromLongPressCommand(NumberPicker r1) {
            this.this$0 = r1;
        }

        private void setStep(boolean r1) {
            this.mIncrement = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.changeValueByOne(this.mIncrement);
            NumberPicker r0 = this.this$0;
            r0.postDelayed(this, NumberPicker.m11307$$Nest$fgetmLongPressUpdateInterval(r0));
        }
    }

    public interface Formatter {
        String format(int r1);
    }

    public interface OnScrollListener {
        void onScrollStateChange(NumberPicker r1, int r2);
    }

    public interface OnValueChangeListener {
        void onValueChange(NumberPicker r1, int r2, int r3);
    }

    public class PressedStateHelper implements Runnable {
        private final int MODE_PRESS;
        private final int MODE_TAPPED;
        private int mManagedButton;
        private int mMode;
        final /* synthetic */ NumberPicker this$0;

        public PressedStateHelper(NumberPicker r1) {
            this.this$0 = r1;
            this.MODE_PRESS = 1;
            this.MODE_TAPPED = 2;
        }

        public void buttonPressDelayed(int r3) {
            cancel();
            this.mMode = 1;
            this.mManagedButton = r3;
            this.this$0.postDelayed(this, ViewConfiguration.getTapTimeout());
        }

        public void buttonTapped(int r2) {
            cancel();
            this.mMode = 2;
            this.mManagedButton = r2;
            this.this$0.post(this);
        }

        public void cancel() {
            this.mMode = 0;
            this.mManagedButton = 0;
            this.this$0.removeCallbacks(this);
            if (NumberPicker.m11306$$Nest$fgetmIncrementVirtualButtonPressed(this.this$0) == false) goto L5;
            NumberPicker.m11311$$Nest$fputmIncrementVirtualButtonPressed(this.this$0, false);
            NumberPicker r1 = this.this$0;
            r1.invalidate(0, NumberPicker.m11304$$Nest$fgetmBottomSelectionDividerBottom(r1), this.this$0.getRight(), this.this$0.getBottom());
        L5:
            NumberPicker.m11310$$Nest$fputmDecrementVirtualButtonPressed(this.this$0, false);
            if (NumberPicker.m11305$$Nest$fgetmDecrementVirtualButtonPressed(this.this$0) == false) goto L9;
            NumberPicker r12 = this.this$0;
            r12.invalidate(0, 0, r12.getRight(), NumberPicker.m11308$$Nest$fgetmTopSelectionDividerTop(this.this$0));
            return;
        }

        @Override // java.lang.Runnable
        public void run() {
            int r0 = this.mMode;
            if (r0 == 1) goto L20;
            if (r0 != 2) goto L28;
            int r02 = this.mManagedButton;
            if (r02 == 1) goto L16;
            if (r02 == 2) goto L11;
            return;
        L11:
            if (NumberPicker.m11305$$Nest$fgetmDecrementVirtualButtonPressed(this.this$0) == true) goto L13;
            this.this$0.postDelayed(this, ViewConfiguration.getPressedStateDuration());
        L13:
            NumberPicker.m11310$$Nest$fputmDecrementVirtualButtonPressed(this.this$0, !NumberPicker.m11305$$Nest$fgetmDecrementVirtualButtonPressed(r0));
            NumberPicker r03 = this.this$0;
            r03.invalidate(0, 0, r03.getRight(), NumberPicker.m11308$$Nest$fgetmTopSelectionDividerTop(this.this$0));
            return;
        L16:
            if (NumberPicker.m11306$$Nest$fgetmIncrementVirtualButtonPressed(this.this$0) == true) goto L18;
            this.this$0.postDelayed(this, ViewConfiguration.getPressedStateDuration());
        L18:
            NumberPicker.m11311$$Nest$fputmIncrementVirtualButtonPressed(this.this$0, !NumberPicker.m11306$$Nest$fgetmIncrementVirtualButtonPressed(r0));
            NumberPicker r04 = this.this$0;
            r04.invalidate(0, NumberPicker.m11304$$Nest$fgetmBottomSelectionDividerBottom(r04), this.this$0.getRight(), this.this$0.getBottom());
            return;
        L28:
            return;
        L20:
            int r05 = this.mManagedButton;
            if (r05 == 1) goto L26;
            if (r05 == 2) goto L24;
            return;
        L24:
            NumberPicker.m11310$$Nest$fputmDecrementVirtualButtonPressed(this.this$0, true);
            NumberPicker r06 = this.this$0;
            r06.invalidate(0, 0, r06.getRight(), NumberPicker.m11308$$Nest$fgetmTopSelectionDividerTop(this.this$0));
            return;
        L26:
            NumberPicker.m11311$$Nest$fputmIncrementVirtualButtonPressed(this.this$0, true);
            NumberPicker r07 = this.this$0;
            r07.invalidate(0, NumberPicker.m11304$$Nest$fgetmBottomSelectionDividerBottom(r07), this.this$0.getRight(), this.this$0.getBottom());
        }
    }

    /* renamed from: -$$Nest$fgetcontentDescriptionCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.CallbackReturn m11303$$Nest$fgetcontentDescriptionCallback(NumberPicker r0) {
        return r0.contentDescriptionCallback;
    }

    /* renamed from: -$$Nest$fgetmBottomSelectionDividerBottom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11304$$Nest$fgetmBottomSelectionDividerBottom(NumberPicker r0) {
        return r0.mBottomSelectionDividerBottom;
    }

    /* renamed from: -$$Nest$fgetmDecrementVirtualButtonPressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11305$$Nest$fgetmDecrementVirtualButtonPressed(NumberPicker r0) {
        return r0.mDecrementVirtualButtonPressed;
    }

    /* renamed from: -$$Nest$fgetmIncrementVirtualButtonPressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11306$$Nest$fgetmIncrementVirtualButtonPressed(NumberPicker r0) {
        return r0.mIncrementVirtualButtonPressed;
    }

    /* renamed from: -$$Nest$fgetmLongPressUpdateInterval, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m11307$$Nest$fgetmLongPressUpdateInterval(NumberPicker r2) {
        return r2.mLongPressUpdateInterval;
    }

    /* renamed from: -$$Nest$fgetmTopSelectionDividerTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11308$$Nest$fgetmTopSelectionDividerTop(NumberPicker r0) {
        return r0.mTopSelectionDividerTop;
    }

    /* renamed from: -$$Nest$fgetmValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11309$$Nest$fgetmValue(NumberPicker r0) {
        return r0.mValue;
    }

    /* renamed from: -$$Nest$fputmDecrementVirtualButtonPressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11310$$Nest$fputmDecrementVirtualButtonPressed(NumberPicker r0, boolean r1) {
        r0.mDecrementVirtualButtonPressed = r1;
    }

    /* renamed from: -$$Nest$fputmIncrementVirtualButtonPressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11311$$Nest$fputmIncrementVirtualButtonPressed(NumberPicker r0, boolean r1) {
        r0.mIncrementVirtualButtonPressed = r1;
    }

    static {
        interpolator = new CubicBezierInterpolator(0.0f, 0.5f, 0.5f, 1.0f);
    }

    public NumberPicker(Context r2) {
        this(r2, null);
    }

    public NumberPicker(Context r2, int r3) {
        this(r2, r3, null);
    }

    public NumberPicker(Context r3, int r4, Theme.ResourcesProvider r5) {
        super(r3);
        this.SELECTOR_WHEEL_ITEM_COUNT = 3;
        this.SELECTOR_MIDDLE_ITEM_INDEX = 3 / 2;
        this.mLongPressUpdateInterval = 300;
        this.mSelectorIndexToStringCache = new SparseArray();
        this.mSelectorIndices = new int[this.SELECTOR_WHEEL_ITEM_COUNT];
        this.mInitialScrollOffset = Integer.MIN_VALUE;
        this.mScrollState = 0;
        this.mLastHandledDownDpadKeyCode = -1;
        this.drawDividers = true;
        this.resourcesProvider = r5;
        this.mTextSize = AndroidUtilities.dp(r4);
        init();
    }

    public NumberPicker(Context r2, Theme.ResourcesProvider r3) {
        this(r2, 18, r3);
    }

    private void decrementSelectorIndices(int[] r4) {
        System.arraycopy(r4, 0, r4, 1, r4.length - 1);
        int r0 = r4[1] - 1;
        if (this.mWrapSelectorWheel == true) goto L5;
    L7:
        r4[0] = r0;
        ensureCachedScrollSelectorValue(r0);
        return;
    L5:
        if (r0 >= this.mMinValue) goto L7;
        r0 = this.mMaxValue;
        goto L7
    }

    private void ensureCachedScrollSelectorValue(int r4) {
        SparseArray<String> r0 = this.mSelectorIndexToStringCache;
        if (r0.get(r4) == null) goto L5;
        return;
    L5:
        int r1 = this.mMinValue;
        if (r4 >= r1) goto L8;
    L14:
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L15:
        r0.put(r4, r3);
        return;
    L8:
        if (r4 > this.mMaxValue) goto L14;
        String[] r2 = this.mDisplayedValues;
        if (r2 == null) goto L13;
        r3 = r2[r4 - r1];
        goto L15
    L13:
        r3 = formatNumber(r4);
        goto L15
    }

    private boolean ensureScrollWheelAdjusted() {
        int r0 = this.mInitialScrollOffset - this.mCurrentScrollOffset;
        if (r0 == 0) goto L11;
        this.mPreviousScrollerY = 0;
        int r1 = Math.abs(r0);
        int r2 = this.mSelectorElementHeight;
        if (r1 <= (r2 / 2)) goto L9;
        if (r0 <= 0) goto L8;
        r2 = -r2;
    L8:
        r0 = r0 + r2;
    L9:
        Scroller r12 = this.mAdjustScroller;
        r12.startScroll(0, 0, 0, r0, 800);
        invalidate();
        return true;
    L11:
        return false;
    }

    private void fling(int r11) {
        this.mPreviousScrollerY = 0;
        Scroller r1 = this.mFlingScroller;
        if (r11 <= 0) goto L5;
        r1.fling(0, 0, 0, r11, 0, 0, 0, Integer.MAX_VALUE);
    L6:
        invalidate();
        return;
    L5:
        r1.fling(0, Integer.MAX_VALUE, 0, r11, 0, 0, 0, Integer.MAX_VALUE);
        goto L6
    }

    private String formatNumber(int r1) {
        Formatter r0 = this.mFormatter;
        if (r0 == null) goto L7;
        return r0.format(r1);
    L7:
        return formatNumberWithLocale(r1);
    }

    private static String formatNumberWithLocale(int r2) {
        return String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r2)});
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private int getWrappedSelectorIndex(int r4) {
        if (this.mMaxValueSet == false) goto L11;
        int r0 = this.mMaxValue;
        if (r4 <= r0) goto L11;
        if ((r0 - this.mMinValue) == 0) goto L11;
        return (r1 + ((r4 - r0) % (r0 - r1))) - 1;
    L11:
        if (this.mMinValueSet == false) goto L18;
        int r02 = this.mMinValue;
        if (r4 >= r02) goto L18;
        int r3 = this.mMaxValue;
        if ((r3 - r02) == 0) goto L18;
        return (r3 - ((r02 - r4) % (r3 - r02))) + 1;
    L18:
        return r4;
    }

    private void incrementSelectorIndices(int[] r4) {
        System.arraycopy(r4, 1, r4, 0, r4.length - 1);
        int r0 = r4[r4.length - 2] + 1;
        if (this.mWrapSelectorWheel == true) goto L5;
    L7:
        r4[r4.length - 1] = r0;
        ensureCachedScrollSelectorValue(r0);
        return;
    L5:
        if (r0 <= this.mMaxValue) goto L7;
        r0 = this.mMinValue;
        goto L7
    }

    private void init() {
        this.mSolidColor = 0;
        Paint r1 = new Paint();
        this.mSelectionDivider = r1;
        r1.setColor(getThemedColor(Theme.key_featuredStickers_addButton));
        this.mSelectionDividerHeight = (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics());
        this.mSelectionDividersDistance = (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics());
        this.mMinHeight = -1;
        int r3 = (int) TypedValue.applyDimension(1, 180.0f, getResources().getDisplayMetrics());
        this.mMaxHeight = r3;
        int r4 = this.mMinHeight;
        if (r4 == (-1)) goto L9;
        if (r3 == (-1)) goto L9;
        if (r4 <= r3) goto L9;
        f$$ExternalSyntheticBUOutline1.m("minHeight > maxHeight");
        return;
    L9:
        this.mMinWidth = (int) TypedValue.applyDimension(1, 64.0f, getResources().getDisplayMetrics());
        this.mMaxWidth = -1;
        this.mComputeMaxWidth = true;
        this.mPressedStateHelper = new PressedStateHelper(this);
        setWillNotDraw(false);
        TextView r32 = new TextView(getContext());
        this.mInputText = r32;
        r32.setGravity(17);
        this.mInputText.setSingleLine(true);
        this.mInputText.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        this.mInputText.setBackgroundResource(0);
        this.mInputText.setTextSize(0, this.mTextSize);
        this.mInputText.setVisibility(4);
        addView(this.mInputText, new LinearLayout.LayoutParams(-1, -2));
        ViewConfiguration r0 = ViewConfiguration.get(getContext());
        this.mTouchSlop = r0.getScaledTouchSlop();
        this.mMinimumFlingVelocity = r0.getScaledMinimumFlingVelocity();
        this.mMaximumFlingVelocity = r0.getScaledMaximumFlingVelocity() / 8;
        Paint r02 = new Paint();
        r02.setAntiAlias(true);
        r02.setTextAlign(Paint.Align.CENTER);
        r02.setTextSize(this.mTextSize);
        r02.setTypeface(this.mInputText.getTypeface());
        r02.setColor(this.mInputText.getTextColors().getColorForState(LinearLayout.ENABLED_STATE_SET, -1));
        this.mSelectorWheelPaint = r02;
        this.mFlingScroller = new Scroller(getContext(), null, true);
        this.mAdjustScroller = new Scroller(getContext(), new DecelerateInterpolator(2.5f));
        updateInputTextView();
        setImportantForAccessibility(1);
        AnonymousClass1 r03 = new AnonymousClass1(this);
        this.accessibilityDelegate = r03;
        setAccessibilityDelegate(r03);
    }

    private void initializeFadingEdges() {
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(((getBottom() - getTop()) - this.mTextSize) / 2);
    }

    private void initializeSelectorWheel() {
        initializeSelectorWheelIndices();
        int r1 = this.mSelectorIndices.length * this.mTextSize;
        int r2 = getBottom() - getTop();
        int r3 = this.mTextSize;
        int r0 = (int) ((((r2 + r3) - r1) / r0.length) + 0.5f);
        this.mSelectorTextGapHeight = r0;
        this.mSelectorElementHeight = r3 + r0;
        int r02 = (this.mInputText.getBaseline() + this.mInputText.getTop()) - (this.mSelectorElementHeight * this.SELECTOR_MIDDLE_ITEM_INDEX);
        this.mInitialScrollOffset = r02;
        this.mCurrentScrollOffset = r02;
        updateInputTextView();
    }

    private void initializeSelectorWheelIndices() {
        this.mSelectorIndexToStringCache.clear();
        int[] r0 = this.mSelectorIndices;
        int r1 = getValue();
        int r2 = 0;
    L4:
        if (r2 >= this.mSelectorIndices.length) goto L9;
        int r3 = (r2 - this.SELECTOR_MIDDLE_ITEM_INDEX) + r1;
        if (this.mWrapSelectorWheel == false) goto L8;
        r3 = getWrappedSelectorIndex(r3);
    L8:
        r0[r2] = r3;
        ensureCachedScrollSelectorValue(r3);
        r2 = r2 + 1;
        goto L4
    }

    private int makeMeasureSpec(int r4, int r5) {
        if (r5 == (-1)) goto L9;
        int r3 = View.MeasureSpec.getSize(r4);
        int r0 = View.MeasureSpec.getMode(r4);
        if (r0 == Integer.MIN_VALUE) goto L15;
        if (r0 == 0) goto L13;
        if (r0 == 1073741824) goto L9;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unknown measure mode: ", r0);
        return 0;
    L13:
        return View.MeasureSpec.makeMeasureSpec(r5, TLObject.FLAG_30);
    L15:
        return View.MeasureSpec.makeMeasureSpec(Math.min(r3, r5), TLObject.FLAG_30);
    L9:
        return r4;
    }

    private boolean moveToFinalScrollerPosition(Scroller r7) {
        r7.forceFinished(true);
        int r1 = r7.getFinalY() - r7.getCurrY();
        int r72 = (this.mCurrentScrollOffset + r1) % this.mSelectorElementHeight;
        int r2 = this.mInitialScrollOffset - r72;
        if (r2 == 0) goto L11;
        int r3 = Math.abs(r2);
        int r4 = this.mSelectorElementHeight;
        if (r3 <= (r4 / 2)) goto L9;
        if (r2 <= 0) goto L8;
        r2 = r2 - r4;
        goto L9
    L8:
        r2 = r2 + r4;
    L9:
        scrollBy(0, r1 + r2);
        return true;
    L11:
        return false;
    }

    private void notifyChange(int r2, int r3) {
        OnValueChangeListener r32 = this.mOnValueChangeListener;
        if (r32 == null) goto L6;
        r32.onValueChange(this, r2, this.mValue);
        return;
    }

    private void onScrollStateChange(int r3) {
        if (this.mScrollState == r3) goto L19;
        this.mScrollState = r3;
        OnScrollListener r0 = this.mOnScrollListener;
        if (r0 == null) goto L8;
        r0.onScrollStateChange(this, r3);
    L8:
        if (r3 != 0) goto L17;
        AccessibilityManager r32 = (AccessibilityManager) getContext().getSystemService("accessibility");
        if (r32.isTouchExplorationEnabled() == false) goto L18;
        String[] r02 = this.mDisplayedValues;
        int r1 = this.mValue;
        if (r02 != null) goto L14;
        String r2 = formatNumber(r1);
    L15:
        AccessibilityEvent r03 = AccessibilityEvent.obtain();
        r03.setEventType(16384);
        r03.getText().add(r2);
        r32.sendAccessibilityEvent(r03);
        return;
    L14:
        r2 = r02[r1 - this.mMinValue];
        goto L15
    L18:
        return;
    L17:
        return;
    }

    private void onScrollerFinished(Scroller r2) {
        if (r2 != this.mFlingScroller) goto L10;
        if (ensureScrollWheelAdjusted() == true) goto L7;
        updateInputTextView();
    L7:
        onScrollStateChange(0);
        return;
    L10:
        if (this.mScrollState == 1) goto L13;
        updateInputTextView();
        return;
    }

    private void postChangeCurrentByOneFromLongPress(boolean r2, long r3) {
        ChangeCurrentByOneFromLongPressCommand r0 = this.mChangeCurrentByOneFromLongPressCommand;
        if (r0 != null) goto L5;
        this.mChangeCurrentByOneFromLongPressCommand = new ChangeCurrentByOneFromLongPressCommand(this);
    L6:
        ChangeCurrentByOneFromLongPressCommand.m11312$$Nest$msetStep(this.mChangeCurrentByOneFromLongPressCommand, r2);
        postDelayed(this.mChangeCurrentByOneFromLongPressCommand, r3);
        return;
    L5:
        removeCallbacks(r0);
        goto L6
    }

    private void removeAllCallbacks() {
        ChangeCurrentByOneFromLongPressCommand r0 = this.mChangeCurrentByOneFromLongPressCommand;
        if (r0 == null) goto L5;
        removeCallbacks(r0);
    L5:
        this.mPressedStateHelper.cancel();
    }

    private void removeChangeCurrentByOneFromLongPress() {
        ChangeCurrentByOneFromLongPressCommand r0 = this.mChangeCurrentByOneFromLongPressCommand;
        if (r0 == null) goto L6;
        removeCallbacks(r0);
        return;
    }

    public static int resolveSizeAndState(int r2, int r3, int r4) {
        int r0 = View.MeasureSpec.getMode(r3);
        int r32 = View.MeasureSpec.getSize(r3);
        if (r0 != Integer.MIN_VALUE) goto L5;
        if (r32 >= r2) goto L11;
        r2 = 16777216 | r32;
    L11:
        return r2 | ((-16777216) & r4);
    L5:
        if (r0 != 1073741824) goto L11;
        r2 = r32;
        goto L11
    }

    private int resolveSizeAndStateRespectingMinSize(int r1, int r2, int r3) {
        if (r1 != (-1)) goto L5;
        return r2;
    L5:
        return resolveSizeAndState(Math.max(r1, r2), r3, 0);
    }

    private void setValueInternal(int r4, boolean r5) {
        if (this.mValue != r4) goto L6;
        return;
    L6:
        if (this.mWrapSelectorWheel == false) goto L8;
        int r42 = getWrappedSelectorIndex(r4);
    L9:
        int r0 = this.mValue;
        this.mFantomValue = r42;
        this.mValue = r42;
        updateInputTextView();
        if (Math.abs(r0 - r42) <= 0.9f) goto L12;
        AndroidUtilities.vibrateCursor(this);
    L12:
        if (r5 == false) goto L14;
        notifyChange(r0, r42);
    L14:
        initializeSelectorWheelIndices();
        invalidate();
        if (this.mScrollState != 0) goto L20;
        OnScrollListener r43 = this.mOnScrollListener;
        if (r43 == null) goto L21;
        r43.onScrollStateChange(this, 0);
        return;
    L21:
        return;
    L20:
        return;
    L8:
        r42 = Math.min(Math.max(r4, this.mMinValue), this.mMaxValue);
        goto L9
    }

    private void tryComputeMaxWidth() {
        if (this.mComputeMaxWidth == false) goto L41;
        String[] r0 = this.mDisplayedValues;
        int r1 = 0;
        if (r0 != null) goto L18;
        float r02 = 0.0f;
        int r2 = 0;
    L9:
        if (r2 > 9) goto L14;
        float r3 = this.mSelectorWheelPaint.measureText(formatNumberWithLocale(r2));
        if (r3 <= r02) goto L13;
        r02 = r3;
    L13:
        r2 = r2 + 1;
        goto L9
    L14:
        int r22 = this.mMaxValue;
    L15:
        if (r22 <= 0) goto L17;
        r1 = r1 + 1;
        r22 = r22 / 10;
        goto L15
    L17:
        int r03 = (int) (r1 * r02);
    L25:
        int r04 = r03 + (this.mInputText.getPaddingLeft() + this.mInputText.getPaddingRight());
        if (this.mMaxWidth == r04) goto L40;
        int r12 = this.mMinWidth;
        if (r04 <= r12) goto L30;
        this.mMaxWidth = r04;
    L31:
        invalidate();
        return;
    L30:
        this.mMaxWidth = r12;
        goto L31
    L40:
        return;
    L18:
        int r23 = r0.length;
        int r32 = 0;
    L19:
        if (r1 >= r23) goto L24;
        float r4 = this.mSelectorWheelPaint.measureText(r0[r1]);
        if (r4 <= r32) goto L23;
        r32 = (int) r4;
    L23:
        r1 = r1 + 1;
        goto L19
    L24:
        r03 = r32;
        goto L25
    }

    private boolean updateInputTextView() {
        String[] r0 = this.mDisplayedValues;
        int r1 = this.mValue;
        if (r0 != null) goto L5;
        String r02 = formatNumber(r1);
    L7:
        if (TextUtils.isEmpty(r02) == false) goto L9;
        return false;
    L9:
        if (r02.equals(this.mInputText.getText().toString()) == true) goto L14;
        this.mInputText.setText(r02);
        return true;
    L14:
        return false;
    L5:
        r02 = r0[r1 - this.mMinValue];
        goto L7
    }

    public void changeValueByOne(boolean r8) {
        this.mInputText.setVisibility(4);
        if (moveToFinalScrollerPosition(this.mFlingScroller) == true) goto L5;
        moveToFinalScrollerPosition(this.mAdjustScroller);
    L5:
        this.mPreviousScrollerY = 0;
        Scroller r1 = this.mFlingScroller;
        if (r8 == false) goto L8;
        r1.startScroll(0, 0, 0, -this.mSelectorElementHeight, 300);
    L9:
        invalidate();
        return;
    L8:
        r1.startScroll(0, 0, 0, this.mSelectorElementHeight, 300);
        goto L9
    }

    @Override // android.view.View
    public void computeScroll() {
        Scroller r0 = this.mFlingScroller;
        if (r0.isFinished() == false) goto L7;
        r0 = this.mAdjustScroller;
        if (r0.isFinished() == false) goto L7;
        return;
    L7:
        r0.computeScrollOffset();
        int r1 = r0.getCurrY();
        if (this.mPreviousScrollerY != 0) goto L10;
        this.mPreviousScrollerY = r0.getStartY();
    L10:
        scrollBy(0, r1 - this.mPreviousScrollerY);
        this.mPreviousScrollerY = r1;
        if (r0.isFinished() == false) goto L14;
        onScrollerFinished(r0);
        return;
    L14:
        invalidate();
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        return getHeight();
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        return this.mCurrentScrollOffset;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        return ((this.mMaxValue - this.mMinValue) + 1) * this.mSelectorElementHeight;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent r6) {
        int r0 = r6.getKeyCode();
        if (r0 == 19) goto L11;
        if (r0 == 20) goto L11;
        if (r0 != 23) goto L8;
    L10:
        removeAllCallbacks();
    L36:
        return super.dispatchKeyEvent(r6);
    L8:
        if (r0 == 66) goto L10;
    L11:
        int r1 = r6.getAction();
        if (r1 == 0) goto L20;
        if (r1 != 1) goto L36;
        if (this.mLastHandledDownDpadKeyCode != r0) goto L36;
        this.mLastHandledDownDpadKeyCode = -1;
        return true;
    L20:
        if (this.mWrapSelectorWheel == true) goto L27;
        if (r0 == 20) goto L27;
        if (getValue() <= getMinValue()) goto L36;
    L28:
        requestFocus();
        this.mLastHandledDownDpadKeyCode = r0;
        removeAllCallbacks();
        if (this.mFlingScroller.isFinished() == false) goto L34;
        if (r0 != 20) goto L32;
        boolean r62 = true;
    L33:
        changeValueByOne(r62);
        goto L34
    L32:
        r62 = false;
    L34:
        return true;
    L27:
        if (getValue() >= getMaxValue()) goto L36;
        goto L28
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r3) {
        int r0 = r3.getActionMasked();
        if (r0 != 1) goto L5;
    L7:
        removeAllCallbacks();
    L9:
        return super.dispatchTouchEvent(r3);
    L5:
        if (r0 == 3) goto L7;
        goto L7
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTrackballEvent(MotionEvent r3) {
        int r0 = r3.getActionMasked();
        if (r0 != 1) goto L5;
    L7:
        removeAllCallbacks();
    L9:
        return super.dispatchTrackballEvent(r3);
    L5:
        if (r0 == 3) goto L7;
        goto L7
    }

    @Override // android.view.View
    public float getBottomFadingEdgeStrength() {
        return 0.9f;
    }

    public CharSequence getContentDescription(int r1) {
        return this.mInputText.getText();
    }

    public String[] getDisplayedValues() {
        return this.mDisplayedValues;
    }

    public int getItemsCount() {
        return this.SELECTOR_WHEEL_ITEM_COUNT;
    }

    public int getMaxValue() {
        return this.mMaxValue;
    }

    public int getMinValue() {
        return this.mMinValue;
    }

    @Override // android.view.View
    public int getSolidColor() {
        return this.mSolidColor;
    }

    @Override // android.view.View
    public float getTopFadingEdgeStrength() {
        return 0.9f;
    }

    public int getValue() {
        return this.mValue;
    }

    public boolean getWrapSelectorWheel() {
        return this.mWrapSelectorWheel;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeAllCallbacks();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas r17) {
        int r2 = this.thisGravity;
        int r4 = 3;
        if (r2 != 5) goto L5;
        this.mSelectorWheelPaint.setTextAlign(Paint.Align.RIGHT);
        float r22 = getWidth();
    L9:
        float r23 = r22 + this.textOffset;
        float r3 = this.mCurrentScrollOffset;
        int[] r7 = this.mSelectorIndices;
        int r9 = 0;
    L11:
        if (r9 >= r7.length) goto L37;
        String r10 = this.mSelectorIndexToStringCache.get(r7[r9]);
        if (r10 == null) goto L35;
        if (r9 != this.SELECTOR_MIDDLE_ITEM_INDEX) goto L19;
        if (this.mInputText.getVisibility() == 0) goto L35;
    L19:
        if (this.SELECTOR_WHEEL_ITEM_COUNT <= r4) goto L34;
        float r11 = getMeasuredHeight() / 2.0f;
        float r12 = getMeasuredHeight() * 0.5f;
        float r13 = r3 - (this.mSelectorWheelPaint.getTextSize() / 2.0f);
        if (r13 >= r11) goto L23;
        float r112 = r13 / r12;
        boolean r122 = true;
    L24:
        float r113 = interpolator.getInterpolation(Utilities.clamp(r112, 1.0f, 0.0f));
        float r15 = (1.0f - r113) * this.mSelectorWheelPaint.getTextSize();
        if (r122 == true) goto L27;
        r15 = -r15;
    L27:
        r17.save();
        r17.translate(0.0f, r15);
        r17.scale((0.2f * r113) + 0.8f, r113, r23, r13);
        if (r113 >= 0.1f) goto L30;
        int r132 = this.mSelectorWheelPaint.getAlpha();
        this.mSelectorWheelPaint.setAlpha((int) ((r132 * r113) / 0.1f));
    L31:
        r17.drawText(r10, r23, r3, this.mSelectorWheelPaint);
        r17.restore();
        if (r132 == (-1)) goto L35;
        this.mSelectorWheelPaint.setAlpha(r132);
        goto L35
    L30:
        r132 = -1;
        goto L31
    L23:
        r112 = (getMeasuredHeight() - r13) / r12;
        r122 = false;
        goto L24
    L34:
        r17.drawText(r10, r23, r3, this.mSelectorWheelPaint);
    L35:
        r3 = r3 + this.mSelectorElementHeight;
        r9 = r9 + 1;
        r4 = 3;
        goto L11
    L37:
        if (this.drawDividers == false) goto L46;
        int r24 = this.mTopSelectionDividerTop;
        r17.drawRect(0.0f, r24, getRight(), this.mSelectionDividerHeight + r24, this.mSelectionDivider);
        r17.drawRect(0.0f, r1 - this.mSelectionDividerHeight, getRight(), this.mBottomSelectionDividerBottom, this.mSelectionDivider);
        return;
    L46:
        return;
    L5:
        Paint r32 = this.mSelectorWheelPaint;
        if (r2 != 3) goto L8;
        r32.setTextAlign(Paint.Align.LEFT);
        r22 = 0.0f;
        goto L9
    L8:
        r32.setTextAlign(Paint.Align.CENTER);
        r22 = getWidth() / 2.0f;
        goto L9
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r6) {
        if (isEnabled() == true) goto L6;
        return false;
    L6:
        if (r6.getActionMasked() != 0) goto L30;
        removeAllCallbacks();
        this.mInputText.setVisibility(4);
        float r0 = r6.getY();
        this.mLastDownEventY = r0;
        this.mLastDownOrMoveEventY = r0;
        this.mLastDownEventTime = r6.getEventTime();
        this.mIngonreMoveEvents = false;
        float r62 = this.mLastDownEventY;
        if (r62 >= this.mTopSelectionDividerTop) goto L13;
        if (this.mScrollState != 0) goto L17;
        this.mPressedStateHelper.buttonPressDelayed(2);
    L17:
        getParent().requestDisallowInterceptTouchEvent(true);
        if (this.mFlingScroller.isFinished() == true) goto L21;
        this.mFlingScroller.forceFinished(true);
        this.mAdjustScroller.forceFinished(true);
        onScrollStateChange(0);
    L29:
        return true;
    L21:
        if (this.mAdjustScroller.isFinished() == true) goto L23;
        this.mFlingScroller.forceFinished(true);
        this.mAdjustScroller.forceFinished(true);
        goto L29
    L23:
        float r63 = this.mLastDownEventY;
        if (r63 >= this.mTopSelectionDividerTop) goto L27;
        postChangeCurrentByOneFromLongPress(false, ViewConfiguration.getLongPressTimeout());
        goto L29
    L27:
        if (r63 <= this.mBottomSelectionDividerBottom) goto L29;
        postChangeCurrentByOneFromLongPress(true, ViewConfiguration.getLongPressTimeout());
        goto L29
    L13:
        if (r62 <= this.mBottomSelectionDividerBottom) goto L17;
        if (this.mScrollState != 0) goto L17;
        this.mPressedStateHelper.buttonPressDelayed(1);
        goto L17
    L30:
        return false;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        int r32 = getMeasuredWidth();
        int r42 = getMeasuredHeight();
        int r52 = this.mInputText.getMeasuredWidth();
        int r62 = this.mInputText.getMeasuredHeight();
        int r33 = (r32 - r52) / 2;
        int r43 = (r42 - r62) / 2;
        TextView r0 = this.mInputText;
        r0.layout(r33, r43, r52 + r33, r62 + r43);
        if (r2 == false) goto L6;
        initializeSelectorWheel();
        initializeFadingEdges();
        this.mTopSelectionDividerTop = ((getHeight() - this.mTextSize) - this.mSelectorTextGapHeight) / 2;
        this.mBottomSelectionDividerBottom = ((getHeight() + this.mTextSize) + this.mSelectorTextGapHeight) / 2;
        return;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(makeMeasureSpec(r3, this.mMaxWidth), makeMeasureSpec(r4, this.mMaxHeight));
        setMeasuredDimension(resolveSizeAndStateRespectingMinSize(this.mMinWidth, getMeasuredWidth(), r3), resolveSizeAndStateRespectingMinSize(this.mMinHeight, getMeasuredHeight(), r4));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r10) {
        if (isEnabled() == true) goto L6;
        return false;
    L6:
        if (this.mVelocityTracker != null) goto L8;
        this.mVelocityTracker = VelocityTracker.obtain();
    L8:
        this.mVelocityTracker.addMovement(r10);
        int r0 = r10.getActionMasked();
        if (r0 == 1) goto L22;
        if (r0 == 2) goto L13;
    L37:
        return true;
    L13:
        if (this.mIngonreMoveEvents == true) goto L37;
        float r102 = r10.getY();
        if (this.mScrollState != 1) goto L18;
        scrollBy(0, (int) (r102 - this.mLastDownOrMoveEventY));
        invalidate();
    L21:
        this.mLastDownOrMoveEventY = r102;
        goto L37
    L18:
        if (((int) Math.abs(r102 - this.mLastDownEventY)) <= this.mTouchSlop) goto L21;
        removeAllCallbacks();
        onScrollStateChange(1);
        goto L21
    L22:
        removeChangeCurrentByOneFromLongPress();
        this.mPressedStateHelper.cancel();
        VelocityTracker r02 = this.mVelocityTracker;
        r02.computeCurrentVelocity(1000, this.mMaximumFlingVelocity);
        int r03 = (int) r02.getYVelocity();
        if (Math.abs(r03) <= this.mMinimumFlingVelocity) goto L25;
        fling(r03);
        onScrollStateChange(2);
    L36:
        this.mVelocityTracker.recycle();
        this.mVelocityTracker = null;
        goto L37
    L25:
        int r04 = (int) r10.getY();
        int r4 = (int) Math.abs(r04 - this.mLastDownEventY);
        long r5 = r10.getEventTime() - this.mLastDownEventTime;
        if (r4 <= this.mTouchSlop) goto L28;
    L34:
        ensureScrollWheelAdjusted();
    L35:
        onScrollStateChange(0);
        goto L36
    L28:
        if (r5 >= ViewConfiguration.getTapTimeout()) goto L34;
        int r05 = (r04 / this.mSelectorElementHeight) - this.SELECTOR_MIDDLE_ITEM_INDEX;
        if (r05 <= 0) goto L32;
        changeValueByOne(true);
        this.mPressedStateHelper.buttonTapped(1);
        goto L35
    L32:
        if (r05 >= 0) goto L35;
        changeValueByOne(false);
        this.mPressedStateHelper.buttonTapped(2);
        goto L35
    }

    @Override // android.view.View
    public void scrollBy(int r4, int r5) {
        int[] r42 = this.mSelectorIndices;
        boolean r0 = this.mWrapSelectorWheel;
        if (r0 == true) goto L11;
        if (r5 <= 0) goto L11;
        if (r42[this.SELECTOR_MIDDLE_ITEM_INDEX] > this.mMinValue) goto L11;
        int r1 = this.mCurrentScrollOffset + r5;
        int r2 = this.mInitialScrollOffset;
        if (r1 <= r2) goto L11;
        this.mCurrentScrollOffset = r2;
        return;
    L11:
        if (r0 == true) goto L19;
        if (r5 >= 0) goto L19;
        if (r42[this.SELECTOR_MIDDLE_ITEM_INDEX] < this.mMaxValue) goto L19;
        int r02 = this.mCurrentScrollOffset + r5;
        int r12 = this.mInitialScrollOffset;
        if (r02 >= r12) goto L19;
        this.mCurrentScrollOffset = r12;
        return;
    L19:
        this.mCurrentScrollOffset += r5;
    L20:
        int r52 = this.mCurrentScrollOffset;
        if ((r52 - this.mInitialScrollOffset) <= this.mSelectorTextGapHeight) goto L29;
        this.mCurrentScrollOffset = r52 - this.mSelectorElementHeight;
        decrementSelectorIndices(r42);
        if (this.mWrapSelectorWheel == true) goto L20;
        if (r42[this.SELECTOR_MIDDLE_ITEM_INDEX] > this.mMinValue) goto L20;
        int r53 = this.mCurrentScrollOffset;
        int r03 = this.mInitialScrollOffset;
        if (r53 <= r03) goto L20;
        this.mCurrentScrollOffset = r03;
    L29:
        int r54 = this.mCurrentScrollOffset;
        if ((r54 - this.mInitialScrollOffset) >= (-this.mSelectorTextGapHeight)) goto L38;
        this.mCurrentScrollOffset = r54 + this.mSelectorElementHeight;
        incrementSelectorIndices(r42);
        if (this.mWrapSelectorWheel == true) goto L29;
        if (r42[this.SELECTOR_MIDDLE_ITEM_INDEX] < this.mMaxValue) goto L29;
        int r55 = this.mCurrentScrollOffset;
        int r04 = this.mInitialScrollOffset;
        if (r55 >= r04) goto L29;
        this.mCurrentScrollOffset = r04;
        goto L29
    L38:
        setValueInternal(r42[this.SELECTOR_MIDDLE_ITEM_INDEX], true);
    }

    public void setAllItemsCount(int r1) {
        this.allItemsCount = Integer.valueOf(r1);
        setWrapSelectorWheel(this.mWrapSelectorWheelSetting);
    }

    public void setContentDescriptionCallback(Utilities.CallbackReturn<Integer, CharSequence> r1) {
        this.contentDescriptionCallback = r1;
    }

    public void setDisplayedValues(String[] r2) {
        if (this.mDisplayedValues != r2) goto L5;
        return;
    L5:
        this.mDisplayedValues = r2;
        updateInputTextView();
        initializeSelectorWheelIndices();
        tryComputeMaxWidth();
    }

    public void setDrawDividers(boolean r1) {
        this.drawDividers = r1;
        invalidate();
    }

    @Override // android.view.View
    public void setEnabled(boolean r1) {
        super.setEnabled(r1);
        this.mInputText.setEnabled(r1);
    }

    public void setFormatter(Formatter r2) {
        if (r2 != this.mFormatter) goto L5;
        return;
    L5:
        this.mFormatter = r2;
        initializeSelectorWheelIndices();
        updateInputTextView();
    }

    @Override // android.widget.LinearLayout
    public void setGravity(int r1) {
        this.thisGravity = r1;
        super.setGravity(r1);
    }

    public void setItemCount(int r2) {
        if (this.SELECTOR_WHEEL_ITEM_COUNT != r2) goto L5;
        return;
    L5:
        this.SELECTOR_WHEEL_ITEM_COUNT = r2;
        this.SELECTOR_MIDDLE_ITEM_INDEX = r2 / 2;
        this.mSelectorIndices = new int[r2];
        initializeSelectorWheelIndices();
    }

    public void setMaxValue(int r2) {
        this.mMaxValueSet = true;
        if (this.mMaxValue == r2) goto L22;
        if (r2 < 0) goto L18;
        this.mMaxValue = r2;
        if (r2 >= this.mValue) goto L12;
        int r0 = this.mFantomValue;
        if (r2 < r0) goto L11;
        this.mValue = r0;
        goto L12
    L11:
        this.mValue = r2;
    L12:
        setWrapSelectorWheel(this.mWrapSelectorWheelSetting);
        initializeSelectorWheelIndices();
        updateInputTextView();
        tryComputeMaxWidth();
        invalidate();
        if (this.mScrollState != 0) goto L20;
        OnScrollListener r22 = this.mOnScrollListener;
        if (r22 == null) goto L21;
        r22.onScrollStateChange(this, 0);
        return;
    L21:
        return;
    L20:
        return;
    L18:
        f$$ExternalSyntheticBUOutline1.m("maxValue must be >= 0");
        return;
    }

    public void setMinValue(int r2) {
        this.mMinValueSet = true;
        if (this.mMinValue == r2) goto L22;
        if (r2 < 0) goto L18;
        this.mMinValue = r2;
        if (r2 <= this.mValue) goto L12;
        int r0 = this.mFantomValue;
        if (r2 > r0) goto L11;
        this.mValue = r0;
        goto L12
    L11:
        this.mValue = r2;
    L12:
        setWrapSelectorWheel(this.mWrapSelectorWheelSetting);
        initializeSelectorWheelIndices();
        updateInputTextView();
        tryComputeMaxWidth();
        invalidate();
        if (this.mScrollState != 0) goto L20;
        OnScrollListener r22 = this.mOnScrollListener;
        if (r22 == null) goto L21;
        r22.onScrollStateChange(this, 0);
        return;
    L21:
        return;
    L20:
        return;
    L18:
        f$$ExternalSyntheticBUOutline1.m("minValue must be >= 0");
        return;
    }

    public void setOnLongPressUpdateInterval(long r1) {
        this.mLongPressUpdateInterval = r1;
    }

    public void setOnScrollListener(OnScrollListener r1) {
        this.mOnScrollListener = r1;
    }

    public void setOnValueChangedListener(OnValueChangeListener r1) {
        this.mOnValueChangeListener = r1;
    }

    public void setSelectorColor(int r1) {
        this.mSelectionDivider.setColor(r1);
    }

    public void setTextColor(int r2) {
        this.mInputText.setTextColor(r2);
        this.mSelectorWheelPaint.setColor(r2);
    }

    public void setTextOffset(int r1) {
        this.textOffset = r1;
        invalidate();
    }

    public void setValue(int r2) {
        setValueInternal(r2, false);
    }

    public void setWrapSelectorWheel(boolean r5) {
        boolean r1 = true;
        if (this.mMaxValueSet == true) goto L5;
    L10:
        this.mWrapSelectorWheelSetting = r5;
        if (r5 == true) goto L14;
    L13:
        r1 = false;
    L14:
        this.mWrapSelectorWheel = r1;
        return;
    L5:
        if (this.mMinValueSet == false) goto L10;
        Integer r0 = this.allItemsCount;
        if (r0 == null) goto L13;
        if (((this.mMaxValue - this.mMinValue) + 1) < r0.intValue()) goto L13;
        goto L10
    }
}
