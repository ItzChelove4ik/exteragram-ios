package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.core.util.Pair;
import com.google.android.material.R;
import com.google.android.material.timepicker.TimeModel;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes5.dex */
class MonthAdapter extends BaseAdapter {
    private static final int MAXIMUM_GRID_CELLS = 0;
    static final int MAXIMUM_WEEKS = 0;
    private static final int NO_DAY_NUMBER = -1;
    final CalendarConstraints calendarConstraints;
    CalendarStyle calendarStyle;
    final DateSelector<?> dateSelector;
    final DayViewDecorator dayViewDecorator;
    final Month month;
    private Collection<Long> previouslySelectedDates;

    static {
        MAXIMUM_WEEKS = UtcDates.getUtcCalendar().getMaximum(4);
        MAXIMUM_GRID_CELLS = (UtcDates.getUtcCalendar().getMaximum(5) + UtcDates.getUtcCalendar().getMaximum(7)) - 1;
    }

    public MonthAdapter(Month r1, DateSelector<?> r2, CalendarConstraints r3, DayViewDecorator r4) {
        this.month = r1;
        this.dateSelector = r2;
        this.calendarConstraints = r3;
        this.dayViewDecorator = r4;
        this.previouslySelectedDates = r2.getSelectedDays();
    }

    private String getDayContentDescription(Context r7, long r8) {
        return DateStrings.getDayContentDescription(r7, r8, isToday(r8), isStartOfRange(r8), isEndOfRange(r8));
    }

    private void initializeStyles(Context r2) {
        if (this.calendarStyle != null) goto L6;
        this.calendarStyle = new CalendarStyle(r2);
        return;
    }

    private boolean isSelected(long r5) {
        Iterator<Long> r4 = this.dateSelector.getSelectedDays().iterator();
    L4:
        if (r4.hasNext() == false) goto L9;
        long r0 = r4.next().longValue();
        if (UtcDates.canonicalYearMonthDay(r5) != UtcDates.canonicalYearMonthDay(r0)) goto L4;
        return true;
    L9:
        return false;
    }

    private boolean isToday(long r3) {
        if (UtcDates.getTodayCalendar().getTimeInMillis() != r3) goto L6;
        return true;
    L6:
        return false;
    }

    private void updateSelectedState(TextView r9, long r10, int r12) {
        if (r9 != null) goto L4;
        return;
    L4:
        Context r1 = r9.getContext();
        String r7 = getDayContentDescription(r1, r10);
        r9.setContentDescription(r7);
        boolean r5 = this.calendarConstraints.getDateValidator().isValid(r10);
        if (r5 == false) goto L14;
        r9.setEnabled(true);
        boolean r0 = isSelected(r10);
        r9.setSelected(r0);
        if (r0 == false) goto L10;
        CalendarItemStyle r102 = this.calendarStyle.selectedDay;
    L9:
        boolean r6 = r0;
        DayViewDecorator r02 = this.dayViewDecorator;
        if (r02 != null) goto L18;
    L21:
        r102.styleItem(r9);
        return;
    L18:
        if (r12 == (-1)) goto L21;
        Month r11 = this.month;
        int r2 = r11.year;
        int r3 = r11.month;
        r102.styleItem(r9, r02.getBackgroundColor(r1, r2, r3, r12, r5, r6), this.dayViewDecorator.getTextColor(r1, r2, r3, r12, r5, r6));
        r9.setCompoundDrawables(this.dayViewDecorator.getCompoundDrawableLeft(r1, r2, r3, r12, r5, r6), this.dayViewDecorator.getCompoundDrawableTop(r1, r2, r3, r12, r5, r6), this.dayViewDecorator.getCompoundDrawableRight(r1, r2, r3, r12, r5, r6), this.dayViewDecorator.getCompoundDrawableBottom(r1, r2, r3, r12, r5, r6));
        r9.setContentDescription(this.dayViewDecorator.getContentDescription(r1, r2, r3, r12, r5, r6, r7));
        return;
    L10:
        boolean r103 = isToday(r10);
        CalendarStyle r112 = this.calendarStyle;
        if (r103 == false) goto L13;
        r102 = r112.todayDay;
        goto L9
    L13:
        r102 = r112.day;
        goto L9
    L14:
        r0 = false;
        r9.setEnabled(false);
        r102 = this.calendarStyle.invalidDay;
        goto L9
    }

    private void updateSelectedStateForDate(MaterialCalendarGridView r4, long r5) {
        if (Month.create(r5).equals(this.month) == false) goto L6;
        int r0 = this.month.getDayOfMonth(r5);
        updateSelectedState((TextView) r4.getChildAt(r4.getAdapter().dayToPosition(r0) - r4.getFirstVisiblePosition()), r5, r0);
        return;
    }

    public int dayToPosition(int r1) {
        return firstPositionInMonth() + (r1 - 1);
    }

    public int findFirstValidDayPosition() {
        return findNextValidDayPosition(firstPositionInMonth() - 1);
    }

    public int findLastValidDayPosition() {
        return findPreviousValidDayPosition(lastPositionInMonth() + 1);
    }

    public int findNearestValidDayPositionInRow(int r7) {
        if (isDayPositionValid(r7) == false) goto L5;
        return r7;
    L5:
        long r0 = getItemId(r7);
        int r2 = 1;
    L7:
        if (r2 >= this.month.daysInWeek) goto L23;
        int r3 = r7 + r2;
        if (r3 < getCount()) goto L11;
    L15:
        int r32 = r7 - r2;
        if (r32 < 0) goto L22;
        if (getItemId(r32) != r0) goto L22;
        if (isDayPositionValid(r32) == false) goto L22;
        return r32;
    L22:
        r2 = r2 + 1;
        goto L7
    L11:
        if (getItemId(r3) != r0) goto L15;
        if (isDayPositionValid(r3) == false) goto L15;
        return r3;
    L23:
        return -1;
    }

    public int findNextValidDayPosition(int r2) {
    L2:
        r2 = r2 + 1;
        if (r2 > lastPositionInMonth()) goto L7;
        if (isDayPositionValid(r2) == false) goto L2;
        return r2;
    L7:
        return -1;
    }

    public int findPreviousValidDayPosition(int r2) {
    L3:
        r2 = r2 - 1;
        if (r2 < firstPositionInMonth()) goto L9;
        if (isDayPositionValid(r2) == false) goto L3;
        return r2;
    L9:
        return -1;
    }

    public int firstPositionInMonth() {
        return this.month.daysFromStartOfWeekToFirstOfMonth(this.calendarConstraints.getFirstDayOfWeek());
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return MAXIMUM_GRID_CELLS;
    }

    @Override // android.widget.Adapter
    public Long getItem(int r2) {
        if (r2 >= firstPositionInMonth()) goto L5;
        return null;
    L5:
        if (r2 <= lastPositionInMonth()) goto L8;
        return null;
    L8:
        return Long.valueOf(this.month.getDay(positionToDay(r2)));
    }

    @Override // android.widget.Adapter
    public /* bridge */ /* synthetic */ Object getItem(int r1) {
        return getItem(r1);
    }

    @Override // android.widget.Adapter
    public long getItemId(int r1) {
        return r1 / this.month.daysInWeek;
    }

    @Override // android.widget.Adapter
    public /* bridge */ /* synthetic */ View getView(int r1, View r2, ViewGroup r3) {
        return getView(r1, r2, r3);
    }

    @Override // android.widget.Adapter
    public TextView getView(int r6, View r7, ViewGroup r8) {
        initializeStyles(r8.getContext());
        TextView r0 = (TextView) r7;
        if (r7 != null) goto L5;
        r0 = (TextView) LayoutInflater.from(r8.getContext()).inflate(R.layout.mtrl_calendar_day, r8, false);
    L5:
        int r72 = r6 - firstPositionInMonth();
        if (r72 < 0) goto L11;
        Month r82 = this.month;
        if (r72 >= r82.daysInMonth) goto L11;
        int r73 = r72 + 1;
        r0.setTag(r82);
        r0.setText(String.format(r0.getResources().getConfiguration().locale, TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r73)}));
        r0.setVisibility(0);
        r0.setEnabled(true);
    L12:
        Long r62 = getItem(r6);
        if (r62 != null) goto L15;
        return r0;
    L15:
        updateSelectedState(r0, r62.longValue(), r73);
        return r0;
    L11:
        r0.setVisibility(8);
        r0.setEnabled(false);
        r73 = -1;
        goto L12
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public boolean isDayPositionValid(int r3) {
        Long r32 = getItem(r3);
        if (r32 != null) goto L5;
        return false;
    L5:
        if (this.calendarConstraints.getDateValidator().isValid(r32.longValue()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isEndOfRange(long r3) {
        Iterator<Pair<Long, Long>> r2 = this.dateSelector.getSelectedRanges().iterator();
    L4:
        if (r2.hasNext() == false) goto L11;
        Long r0 = r2.next().second;
        if (r0 == null) goto L4;
        if (r0.longValue() != r3) goto L4;
        return true;
    L11:
        return false;
    }

    public boolean isFirstInRow(int r1) {
        if ((r1 % this.month.daysInWeek) != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isLastInRow(int r2) {
        if (((r2 + 1) % this.month.daysInWeek) != 0) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean isStartOfRange(long r3) {
        Iterator<Pair<Long, Long>> r2 = this.dateSelector.getSelectedRanges().iterator();
    L4:
        if (r2.hasNext() == false) goto L11;
        Long r0 = r2.next().first;
        if (r0 == null) goto L4;
        if (r0.longValue() != r3) goto L4;
        return true;
    L11:
        return false;
    }

    public int lastPositionInMonth() {
        return (firstPositionInMonth() + this.month.daysInMonth) - 1;
    }

    public int positionToDay(int r1) {
        return (r1 - firstPositionInMonth()) + 1;
    }

    public void updateSelectedStates(MaterialCalendarGridView r4) {
        Iterator<Long> r0 = this.previouslySelectedDates.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        updateSelectedStateForDate(r4, r0.next().longValue());
        goto L4
    L6:
        DateSelector<?> r02 = this.dateSelector;
        if (r02 == null) goto L16;
        Iterator<Long> r03 = r02.getSelectedDays().iterator();
    L10:
        if (r03.hasNext() == false) goto L12;
        updateSelectedStateForDate(r4, r03.next().longValue());
        goto L10
    L12:
        this.previouslySelectedDates = this.dateSelector.getSelectedDays();
        return;
    }

    public boolean withinMonth(int r2) {
        if (r2 >= firstPositionInMonth()) goto L5;
        return false;
    L5:
        if (r2 > lastPositionInMonth()) goto L10;
        return true;
    L10:
        return false;
    }
}
