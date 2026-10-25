package com.google.android.material.datepicker;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.timepicker.TimeModel;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes5.dex */
class YearGridAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final MaterialCalendar<?> materialCalendar;

    /* renamed from: com.google.android.material.datepicker.YearGridAdapter$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ YearGridAdapter this$0;
        final /* synthetic */ int val$year;

        public AnonymousClass1(YearGridAdapter r1, int r2) {
            this.this$0 = r1;
            this.val$year = r2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r2) {
            Month r22 = Month.create(this.val$year, YearGridAdapter.access$000(this.this$0).getCurrentMonth().month);
            Month r23 = YearGridAdapter.access$000(this.this$0).getCalendarConstraints().clamp(r22);
            YearGridAdapter.access$000(this.this$0).setCurrentMonth(r23);
            YearGridAdapter.access$000(this.this$0).setSelector(MaterialCalendar.CalendarSelector.DAY);
            YearGridAdapter.access$000(this.this$0).sendAccessibilityFocusEventToMonthDropdown();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        public ViewHolder(TextView r1) {
            super(r1);
            this.textView = r1;
        }
    }

    public YearGridAdapter(MaterialCalendar<?> r1) {
        this.materialCalendar = r1;
    }

    public static /* synthetic */ MaterialCalendar access$000(YearGridAdapter r0) {
        return r0.materialCalendar;
    }

    private View.OnClickListener createYearClickListener(int r2) {
        return new AnonymousClass1(this, r2);
    }

    private void ensureFocusRingForeground(TextView r2, CalendarItemStyle r3) {
        if ((r2.getForeground() instanceof FocusRingDrawable) == true) goto L10;
        Drawable r1 = FocusRingDrawable.wrap(r2.getContext(), r2.getForeground());
        if ((r1 instanceof FocusRingDrawable) == false) goto L9;
        FocusRingDrawable r12 = (FocusRingDrawable) r1;
        r12.setFocusRingShapeAppearance(r3.getItemShapeAppearanceModel());
        r12.setFocusRingInset(r3.getTopInset());
        r2.setForeground(r12);
        return;
    L9:
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.materialCalendar.getCalendarConstraints().getYearSpan();
    }

    public int getPositionForYear(int r1) {
        return r1 - this.materialCalendar.getCalendarConstraints().getStart().year;
    }

    public int getYearForPosition(int r1) {
        return this.materialCalendar.getCalendarConstraints().getStart().year + r1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
        onBindViewHolder((ViewHolder) r1, r2);
    }

    public void onBindViewHolder(ViewHolder r8, int r9) {
        int r92 = getYearForPosition(r9);
        r8.textView.setText(String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r92)}));
        TextView r0 = r8.textView;
        r0.setContentDescription(DateStrings.getYearContentDescription(r0.getContext(), r92));
        CalendarStyle r02 = this.materialCalendar.getCalendarStyle();
        Calendar r1 = UtcDates.getTodayCalendar();
        boolean r2 = true;
        if (r1.get(1) != r92) goto L5;
        CalendarItemStyle r3 = r02.todayYear;
    L6:
        Iterator<Long> r4 = this.materialCalendar.getDateSelector().getSelectedDays().iterator();
    L8:
        if (r4.hasNext() == false) goto L12;
        r1.setTimeInMillis(r4.next().longValue());
        if (r1.get(1) != r92) goto L8;
        r3 = r02.selectedYear;
        goto L8
    L12:
        r3.styleItem(r8.textView);
        ensureFocusRingForeground(r8.textView, r3);
        TextView r12 = r8.textView;
        if (r3 == r02.selectedYear) goto L16;
        r2 = false;
    L16:
        r12.setSelected(r2);
        r8.textView.setOnClickListener(createYearClickListener(r92));
        return;
    L5:
        r3 = r02.year;
        goto L6
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
        return onCreateViewHolder(r1, r2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
        return new ViewHolder((TextView) LayoutInflater.from(r2.getContext()).inflate(R.layout.mtrl_calendar_year, r2, false));
    }
}
