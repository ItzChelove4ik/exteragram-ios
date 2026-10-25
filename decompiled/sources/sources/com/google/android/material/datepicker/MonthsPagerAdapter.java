package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.datepicker.MaterialCalendar;
import org.mvel2.asm.Opcodes;

/* loaded from: classes5.dex */
class MonthsPagerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int POSITION_UNSPECIFIED = 0;
    private final CalendarConstraints calendarConstraints;
    private final DateSelector<?> dateSelector;
    private final DayViewDecorator dayViewDecorator;
    private final int itemHeight;
    private int keyboardFocusDirection;
    private final MaterialCalendar.OnDayClickListener onDayClickListener;
    private final MaterialCalendar.OnMonthNavigationListener onMonthNavigationListener;
    private Month visibleMonth;

    /* renamed from: com.google.android.material.datepicker.MonthsPagerAdapter$1, reason: invalid class name */
    public class AnonymousClass1 implements AdapterView.OnItemClickListener {
        final /* synthetic */ MonthsPagerAdapter this$0;
        final /* synthetic */ MaterialCalendarGridView val$monthGrid;

        public AnonymousClass1(MonthsPagerAdapter r1, MaterialCalendarGridView r2) {
            this.this$0 = r1;
            this.val$monthGrid = r2;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> r1, View r2, int r3, long r4) {
            if (this.val$monthGrid.getAdapter().withinMonth(r3) == false) goto L6;
            MonthsPagerAdapter.access$000(this.this$0).onDayClick(this.val$monthGrid.getAdapter().getItem(r3).longValue());
            return;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final MaterialCalendarGridView monthGrid;
        final TextView monthTitle;

        public ViewHolder(LinearLayout r3, boolean r4) {
            super(r3);
            TextView r0 = (TextView) r3.findViewById(R.id.month_title);
            this.monthTitle = r0;
            ViewCompat.setAccessibilityHeading(r0, true);
            this.monthGrid = (MaterialCalendarGridView) r3.findViewById(R.id.month_grid);
            if (r4 == true) goto L6;
            r0.setVisibility(8);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$E9camo9RH2aGrepzFLssIv_21eo(MonthsPagerAdapter r1, MaterialCalendarGridView r2, int r3) {
        r1.getClass();
        if (r2.hasFocus() == false) goto L7;
        if (r3 == 0) goto L8;
        r2.setSelection(r1.getInitialDayPositionForDirection(r2.getAdapter(), r3));
        return;
    L8:
        return;
    }

    public MonthsPagerAdapter(Context r6, DateSelector<?> r7, CalendarConstraints r8, DayViewDecorator r9, MaterialCalendar.OnDayClickListener r10, MaterialCalendar.OnMonthNavigationListener r11) {
        int r0 = 0;
        this.keyboardFocusDirection = 0;
        Month r1 = r8.getStart();
        Month r2 = r8.getEnd();
        Month r3 = r8.getOpenAt();
        if (r1.compareTo2(r3) <= 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("firstPage cannot be after currentPage");
        throw null;
    L5:
        if (r3.compareTo2(r2) > 0) goto L11;
        int r12 = MonthAdapter.MAXIMUM_WEEKS * MaterialCalendar.getDayHeight(r6);
        if (MaterialDatePicker.isFullscreen(r6) == false) goto L9;
        r0 = MaterialCalendar.getDayHeight(r6);
    L9:
        this.itemHeight = r12 + r0;
        this.calendarConstraints = r8;
        this.dateSelector = r7;
        this.dayViewDecorator = r9;
        this.onDayClickListener = r10;
        this.onMonthNavigationListener = r11;
        this.visibleMonth = r3;
        setHasStableIds(true);
        return;
    L11:
        f$$ExternalSyntheticBUOutline1.m("currentPage cannot be after lastPage");
        throw null;
    }

    public static /* synthetic */ MaterialCalendar.OnDayClickListener access$000(MonthsPagerAdapter r0) {
        return r0.onDayClickListener;
    }

    private int getInitialDayPositionForDirection(MonthAdapter r2, int r3) {
        if (r3 != 1) goto L8;
        int r1 = r2.findLastValidDayPosition();
        if (r1 == (-1)) goto L7;
        return r1;
    L7:
        return r2.lastPositionInMonth();
    L8:
        int r12 = r2.findFirstValidDayPosition();
        if (r12 == (-1)) goto L11;
        return r12;
    L11:
        return r2.firstPositionInMonth();
    }

    private void setInitialKeyboardFocus(MaterialCalendarGridView r3) {
        int r0 = this.keyboardFocusDirection;
        this.keyboardFocusDirection = 0;
        r3.post(new MonthsPagerAdapter$$ExternalSyntheticLambda0(this, r3, r0));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.calendarConstraints.getMonthSpan();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int r1) {
        return this.calendarConstraints.getStart().monthsLater(r1).getStableId();
    }

    public Month getPageMonth(int r1) {
        return this.calendarConstraints.getStart().monthsLater(r1);
    }

    public CharSequence getPageTitle(int r1) {
        return getPageMonth(r1).getLongName();
    }

    public int getPosition(Month r1) {
        return this.calendarConstraints.getStart().monthsUntil(r1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
        onBindViewHolder((ViewHolder) r1, r2);
    }

    public void onBindViewHolder(ViewHolder r6, int r7) {
        Month r72 = this.calendarConstraints.getStart().monthsLater(r7);
        r6.monthTitle.setText(r72.getLongName());
        MaterialCalendarGridView r0 = (MaterialCalendarGridView) r6.monthGrid.findViewById(R.id.month_grid);
        if (r0.getAdapter() != null) goto L5;
    L7:
        MonthAdapter r1 = new MonthAdapter(r72, this.dateSelector, this.calendarConstraints, this.dayViewDecorator);
        r0.setNumColumns(r72.daysInWeek);
        r0.setAdapter(r1);
    L8:
        r0.setOnItemClickListener(new AnonymousClass1(this, r0));
        r0.setOnMonthNavigationListener(this.onMonthNavigationListener);
        boolean r62 = MaterialDatePicker.isFullscreen(r6.itemView.getContext());
        if (r62 == false) goto L11;
    L14:
        r0.setFocusable(true);
        r0.setDescendantFocusability(131072);
    L15:
        if (r62 == false) goto L17;
        return;
    L17:
        if (r72.equals(this.visibleMonth) == false) goto L21;
        setInitialKeyboardFocus(r0);
        return;
    L21:
        return;
    L11:
        if (r72.equals(this.visibleMonth) == true) goto L14;
        r0.setFocusable(false);
        r0.setDescendantFocusability(Opcodes.ASM6);
        goto L15
    L5:
        if (r72.equals(r0.getAdapter().month) == false) goto L7;
        r0.invalidate();
        r0.getAdapter().updateSelectedStates(r0);
        goto L8
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
        return onCreateViewHolder(r1, r2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
        LinearLayout r42 = (LinearLayout) LayoutInflater.from(r3.getContext()).inflate(R.layout.mtrl_calendar_month_labeled, r3, false);
        if (MaterialDatePicker.isFullscreen(r3.getContext()) == false) goto L7;
        r42.setLayoutParams(new RecyclerView.LayoutParams(-1, this.itemHeight));
        return new ViewHolder(r42, true);
    L7:
        return new ViewHolder(r42, false);
    }

    public void setKeyboardFocusDirection(int r1) {
        this.keyboardFocusDirection = r1;
    }

    public void setVisibleMonth(Month r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.equals(this.visibleMonth) == true) goto L8;
        int r0 = getPosition(this.visibleMonth);
        this.visibleMonth = r2;
        int r22 = getPosition(r2);
        notifyItemChanged(r0);
        notifyItemChanged(r22);
        return;
    }
}
