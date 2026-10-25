package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;

/* loaded from: classes5.dex */
final class CalendarStyle {
    final CalendarItemStyle day;
    final CalendarItemStyle invalidDay;
    final Paint rangeFill;
    final CalendarItemStyle selectedDay;
    final CalendarItemStyle selectedYear;
    final CalendarItemStyle todayDay;
    final CalendarItemStyle todayYear;
    final CalendarItemStyle year;

    public CalendarStyle(Context r5) {
        TypedArray r0 = r5.obtainStyledAttributes(MaterialAttributes.resolveOrThrow(r5, R.attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), R.styleable.MaterialCalendar);
        this.day = CalendarItemStyle.create(r5, r0.getResourceId(R.styleable.MaterialCalendar_dayStyle, 0));
        this.invalidDay = CalendarItemStyle.create(r5, r0.getResourceId(R.styleable.MaterialCalendar_dayInvalidStyle, 0));
        this.selectedDay = CalendarItemStyle.create(r5, r0.getResourceId(R.styleable.MaterialCalendar_daySelectedStyle, 0));
        this.todayDay = CalendarItemStyle.create(r5, r0.getResourceId(R.styleable.MaterialCalendar_dayTodayStyle, 0));
        ColorStateList r1 = MaterialResources.getColorStateList(r5, r0, R.styleable.MaterialCalendar_rangeFillColor);
        this.year = CalendarItemStyle.create(r5, r0.getResourceId(R.styleable.MaterialCalendar_yearStyle, 0));
        this.selectedYear = CalendarItemStyle.create(r5, r0.getResourceId(R.styleable.MaterialCalendar_yearSelectedStyle, 0));
        this.todayYear = CalendarItemStyle.create(r5, r0.getResourceId(R.styleable.MaterialCalendar_yearTodayStyle, 0));
        Paint r52 = new Paint();
        this.rangeFill = r52;
        r52.setColor(r1.getDefaultColor());
        r0.recycle();
    }
}
