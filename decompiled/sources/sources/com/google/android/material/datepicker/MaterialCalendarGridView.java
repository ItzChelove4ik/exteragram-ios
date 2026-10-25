package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.core.util.Pair;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.internal.ViewUtils;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.Calendar;
import java.util.Iterator;

/* loaded from: classes5.dex */
final class MaterialCalendarGridView extends GridView {
    private final Calendar dayCompute;
    private final boolean nestedScrollable;
    private MaterialCalendar.OnMonthNavigationListener onMonthNavigationListener;

    /* renamed from: com.google.android.material.datepicker.MaterialCalendarGridView$1, reason: invalid class name */
    public class AnonymousClass1 extends AccessibilityDelegateCompat {
        final /* synthetic */ MaterialCalendarGridView this$0;

        public AnonymousClass1(MaterialCalendarGridView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            r2.setCollectionInfo(null);
        }
    }

    public static /* synthetic */ void $r8$lambda$PqZsnkFZF7YntKmtXySyj_K1Prk(MaterialCalendarGridView r1) {
        r1.ensureFocusRingSelector(r1.getAdapter());
    }

    public MaterialCalendarGridView(Context r2) {
        this(r2, null);
    }

    public MaterialCalendarGridView(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public MaterialCalendarGridView(Context r1, AttributeSet r2, int r3) {
        super(r1, r2, r3);
        this.dayCompute = UtcDates.getUtcCalendar();
        if (MaterialDatePicker.isFullscreen(getContext()) == false) goto L5;
        setNextFocusLeftId(R.id.cancel_button);
        setNextFocusRightId(R.id.confirm_button);
    L5:
        this.nestedScrollable = MaterialDatePicker.isNestedScrollable(getContext());
        ViewCompat.setAccessibilityDelegate(this, new AnonymousClass1(this));
    }

    private void ensureFocusRingSelector(MonthAdapter r3) {
        Drawable r0 = getSelector();
        if ((r0 instanceof FocusRingDrawable) == true) goto L13;
        Drawable r02 = FocusRingDrawable.wrap(getContext(), r0);
        if ((r02 instanceof FocusRingDrawable) == false) goto L12;
        FocusRingDrawable r03 = (FocusRingDrawable) r02;
        CalendarStyle r32 = r3.calendarStyle;
        if (r32 == null) goto L10;
        r03.setFocusRingShapeAppearance(r32.day.getItemShapeAppearanceModel());
    L10:
        setDrawSelectorOnTop(true);
        setSelector(r03);
        return;
    L12:
        return;
    }

    private void gainFocus(int r4, Rect r5) {
        if (r4 == 33) goto L13;
        if (r4 == 1) goto L13;
        if (r4 != 130) goto L9;
    L12:
        int r0 = getAdapter().findFirstValidDayPosition();
    L14:
        if (r0 == (-1)) goto L17;
        setSelection(r0);
        return;
    L17:
        super.onFocusChanged(true, r4, r5);
        return;
    L9:
        if (r4 == 2) goto L12;
        r0 = -1;
    L13:
        r0 = getAdapter().findLastValidDayPosition();
        goto L14
    }

    private View getChildAtPosition(int r2) {
        return getChildAt(r2 - getFirstVisiblePosition());
    }

    private boolean handleHorizontalNavigation(int r3, boolean r4) {
        if (r4 == false) goto L4;
        int r32 = getAdapter().findNextValidDayPosition(r3);
    L6:
        if (r32 == (-1)) goto L9;
        setSelection(r32);
        return true;
    L9:
        if (r4 == true) goto L14;
        MaterialCalendar.OnMonthNavigationListener r33 = this.onMonthNavigationListener;
        if (r33 == null) goto L14;
        return r33.onMonthNavigationPrevious();
    L14:
        if (r4 == false) goto L19;
        MaterialCalendar.OnMonthNavigationListener r2 = this.onMonthNavigationListener;
        if (r2 == null) goto L19;
        return r2.onMonthNavigationNext();
    L19:
        return true;
    L4:
        r32 = getAdapter().findPreviousValidDayPosition(r3);
        goto L6
    }

    private boolean handleTabNavigation(int r1, KeyEvent r2) {
        if (r2.isShiftPressed() == false) goto L5;
        int r12 = getAdapter().findPreviousValidDayPosition(r1);
    L7:
        if (r12 != (-1)) goto L10;
        return false;
    L10:
        setSelection(r12);
        return true;
    L5:
        r12 = getAdapter().findNextValidDayPosition(r1);
        goto L7
    }

    private static int horizontalMidPoint(View r1) {
        return r1.getLeft() + (r1.getWidth() / 2);
    }

    private static boolean skipMonth(Long r3, Long r4, Long r5, Long r6) {
        if (r3 == null) goto L15;
        if (r4 == null) goto L15;
        if (r5 == null) goto L15;
        if (r6 == null) goto L15;
        if (r5.longValue() > r4.longValue()) goto L15;
        if (r6.longValue() < r3.longValue()) goto L15;
        return false;
    L15:
        return true;
    }

    private boolean trySelectNearestValidDayPosition(int r2) {
        int r22 = getAdapter().findNearestValidDayPositionInRow(r2);
        if (r22 == (-1)) goto L6;
        setSelection(r22);
        return true;
    L6:
        return false;
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public /* bridge */ /* synthetic */ Adapter getAdapter() {
        return getAdapter();
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public /* bridge */ /* synthetic */ ListAdapter getAdapter() {
        return getAdapter();
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public MonthAdapter getAdapter() {
        return (MonthAdapter) super.getAdapter();
    }

    public boolean handleVerticalNavigationOnDisabledDay(int r4, int r5) {
        MonthAdapter r0 = getAdapter();
        if (trySelectNearestValidDayPosition(r5) == false) goto L6;
        return true;
    L6:
        if (19 != r4) goto L15;
        int r42 = getNumColumns();
    L8:
        r5 = r5 - r42;
        if (r5 < r0.firstPositionInMonth()) goto L29;
        if (trySelectNearestValidDayPosition(r5) == true) goto L12;
        r42 = getNumColumns();
        goto L8
    L12:
        return true;
    L29:
        return false;
    L15:
        if (r4 != 20) goto L23;
        int r43 = getNumColumns();
    L17:
        r5 = r5 + r43;
        if (r5 > r0.lastPositionInMonth()) goto L30;
        if (trySelectNearestValidDayPosition(r5) == true) goto L21;
        r43 = getNumColumns();
        goto L17
    L21:
        return true;
    L30:
        return false;
    L23:
        return false;
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAdapter().notifyDataSetChanged();
        post(new MaterialCalendarGridView$$ExternalSyntheticLambda0(this));
    }

    @Override // android.view.View
    public final void onDraw(Canvas r27) {
        MaterialCalendarGridView r0 = this;
        super.onDraw(r27);
        MonthAdapter r1 = r0.getAdapter();
        DateSelector<?> r2 = r1.dateSelector;
        CalendarStyle r3 = r1.calendarStyle;
        int r4 = Math.max(r1.firstPositionInMonth(), r0.getFirstVisiblePosition());
        int r5 = Math.min(r1.lastPositionInMonth(), r0.getLastVisiblePosition());
        Long r6 = r1.getItem(r4);
        Long r7 = r1.getItem(r5);
        Iterator<Pair<Long, Long>> r22 = r2.getSelectedRanges().iterator();
    L4:
        if (r22.hasNext() == false) goto L53;
        Pair<Long, Long> r8 = r22.next();
        Long r9 = r8.first;
        if (r9 != null) goto L8;
        r0 = this;
        goto L4
    L8:
        if (r8.second == null) goto L4;
        Long r92 = r9;
        long r10 = r92.longValue();
        Long r82 = r8.second;
        long r12 = r82.longValue();
        if (skipMonth(r6, r7, r92, r82) == true) goto L4;
        boolean r83 = ViewUtils.isLayoutRtl(r0);
        if (r10 < r6.longValue()) goto L16;
        r0.dayCompute.setTimeInMillis(r10);
        int r93 = r1.dayToPosition(r0.dayCompute.get(5));
        int r102 = horizontalMidPoint(r0.getChildAtPosition(r93));
    L24:
        if (r12 > r7.longValue()) goto L26;
        r0.dayCompute.setTimeInMillis(r12);
        int r11 = r1.dayToPosition(r0.dayCompute.get(5));
        int r122 = horizontalMidPoint(r0.getChildAtPosition(r11));
    L33:
        int r13 = (int) r1.getItemId(r93);
        int r14 = r4;
        int r16 = r5;
        int r42 = (int) r1.getItemId(r11);
    L34:
        if (r13 > r42) goto L51;
        int r52 = r0.getNumColumns() * r13;
        int r15 = (r52 + r0.getNumColumns()) - 1;
        View r17 = r0.getChildAtPosition(r52);
        int r02 = r17.getTop() + r3.day.getTopInset();
        MonthAdapter r18 = r1;
        int r19 = r17.getBottom() - r3.day.getBottomInset();
        if (r83 == true) goto L43;
        if (r52 <= r93) goto L39;
        int r53 = 0;
    L40:
        if (r11 <= r15) goto L42;
        int r152 = getWidth();
    L50:
        r27.drawRect(r53, r02, r152, r19, r3.rangeFill);
        r13 = r13 + 1;
        r0 = this;
        r1 = r18;
        goto L34
    L42:
        r152 = r122;
        goto L50
    L39:
        r53 = r102;
        goto L40
    L43:
        if (r11 <= r15) goto L45;
        int r153 = 0;
    L46:
        if (r52 <= r93) goto L48;
        int r54 = getWidth();
    L49:
        int r25 = r153;
        r152 = r54;
        r53 = r25;
        goto L50
    L48:
        r54 = r102;
        goto L49
    L45:
        r153 = r122;
        goto L46
    L51:
        r0 = this;
        r4 = r14;
        r5 = r16;
        goto L4
    L26:
        if (r1.isLastInRow(r5) == false) goto L28;
        int r112 = r0.getWidth();
    L31:
        r122 = r112;
        r11 = r5;
        goto L33
    L28:
        if (r83 == true) goto L30;
        r112 = r0.getChildAtPosition(r5).getRight();
        goto L31
    L30:
        r112 = r0.getChildAtPosition(r5).getLeft();
        goto L31
    L16:
        if (r1.isFirstInRow(r4) == false) goto L18;
        int r94 = 0;
    L21:
        r102 = r94;
        r93 = r4;
        goto L24
    L18:
        if (r83 == true) goto L20;
        r94 = r0.getChildAtPosition(r4 - 1).getRight();
        goto L21
    L20:
        r94 = r0.getChildAtPosition(r4 - 1).getLeft();
        goto L21
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onFocusChanged(boolean r1, int r2, Rect r3) {
        if (r1 == false) goto L5;
        gainFocus(r2, r3);
        return;
    L5:
        super.onFocusChanged(false, r2, r3);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int r6, KeyEvent r7) {
        int r0 = getSelectedItemPosition();
        if (r0 == (-1)) goto L5;
        boolean r2 = ViewUtils.isLayoutRtl(this);
        if (r6 == 21) goto L28;
        if (r6 == 22) goto L26;
        if (r6 == 61) goto L24;
        if (super.onKeyDown(r6, r7) == true) goto L16;
        return false;
    L16:
        MonthAdapter r72 = getAdapter();
        int r02 = getSelectedItemPosition();
        if (r02 != (-1)) goto L19;
    L22:
        return true;
    L19:
        if (r72.isDayPositionValid(r02) == true) goto L22;
        return handleVerticalNavigationOnDisabledDay(r6, r02);
    L24:
        return handleTabNavigation(r0, r7);
    L26:
        return handleHorizontalNavigation(r0, !r2);
    L28:
        return handleHorizontalNavigation(r0, r2);
    L5:
        return super.onKeyDown(r6, r7);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int r2, int r3) {
        if (this.nestedScrollable == false) goto L6;
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(16777215, Integer.MIN_VALUE));
        ViewGroup.LayoutParams r22 = getLayoutParams();
        r22.height = getMeasuredHeight();
        return;
    L6:
        super.onMeasure(r2, r3);
    }

    @Override // android.widget.AdapterView
    public /* bridge */ /* synthetic */ void setAdapter(Adapter r1) {
        setAdapter((ListAdapter) r1);
    }

    @Override // android.widget.GridView, android.widget.AbsListView
    public final void setAdapter(ListAdapter r2) {
        if ((r2 instanceof MonthAdapter) == false) goto L6;
        super.setAdapter(r2);
        return;
    L6:
        FieldSet$$ExternalSyntheticBUOutline0.m("%1$s must have its Adapter set to a %2$s", new Object[]{MaterialCalendarGridView.class.getCanonicalName(), MonthAdapter.class.getCanonicalName()});
    }

    public void setOnMonthNavigationListener(MaterialCalendar.OnMonthNavigationListener r1) {
        this.onMonthNavigationListener = r1;
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public void setSelection(int r2) {
        super.setSelection(Math.max(r2, getAdapter().findFirstValidDayPosition()));
    }
}
