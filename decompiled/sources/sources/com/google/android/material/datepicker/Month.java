package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* loaded from: classes5.dex */
final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = null;
    final int daysInMonth;
    final int daysInWeek;
    private final Calendar firstOfMonth;
    private String longName;
    final int month;
    final long timeInMillis;
    final int year;

    /* renamed from: com.google.android.material.datepicker.Month$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<Month> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Month createFromParcel(Parcel r1) {
            return Month.create(r1.readInt(), r1.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ Month createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Month[] newArray(int r1) {
            return new Month[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ Month[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    private Month(Calendar r4) {
        r4.set(5, 1);
        Calendar r42 = UtcDates.getDayCopy(r4);
        this.firstOfMonth = r42;
        this.month = r42.get(2);
        this.year = r42.get(1);
        this.daysInWeek = r42.getMaximum(7);
        this.daysInMonth = r42.getActualMaximum(5);
        this.timeInMillis = r42.getTimeInMillis();
    }

    public static Month create(int r2, int r3) {
        Calendar r0 = UtcDates.getUtcCalendar();
        r0.set(1, r2);
        r0.set(2, r3);
        return new Month(r0);
    }

    public static Month create(long r1) {
        Calendar r0 = UtcDates.getUtcCalendar();
        r0.setTimeInMillis(r1);
        return new Month(r0);
    }

    public static Month current() {
        return new Month(UtcDates.getTodayCalendar());
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(Month r1) {
        return this.firstOfMonth.compareTo(r1.firstOfMonth);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Month r1) {
        return compareTo2(r1);
    }

    public int daysFromStartOfWeekToFirstOfMonth(int r3) {
        int r0 = this.firstOfMonth.get(7);
        if (r3 > 0) goto L6;
        r3 = this.firstOfMonth.getFirstDayOfWeek();
    L6:
        int r02 = r0 - r3;
        if (r02 < 0) goto L9;
        return r02;
    L9:
        return r02 + this.daysInWeek;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Month) == true) goto L8;
        return false;
    L8:
        Month r52 = (Month) r5;
        if (this.month == r52.month) goto L11;
    L13:
        return false;
    L11:
        if (this.year != r52.year) goto L13;
        return true;
    }

    public long getDay(int r2) {
        Calendar r1 = UtcDates.getDayCopy(this.firstOfMonth);
        r1.set(5, r2);
        return r1.getTimeInMillis();
    }

    public int getDayOfMonth(long r1) {
        Calendar r0 = UtcDates.getDayCopy(this.firstOfMonth);
        r0.setTimeInMillis(r1);
        return r0.get(5);
    }

    public String getLongName() {
        if (this.longName != null) goto L6;
        this.longName = DateStrings.getYearMonth(this.firstOfMonth.getTimeInMillis());
    L6:
        return this.longName;
    }

    public long getStableId() {
        return this.firstOfMonth.getTimeInMillis();
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.month), Integer.valueOf(this.year)});
    }

    public Month monthsLater(int r2) {
        Calendar r1 = UtcDates.getDayCopy(this.firstOfMonth);
        r1.add(2, r2);
        return new Month(r1);
    }

    public int monthsUntil(Month r3) {
        if ((this.firstOfMonth instanceof GregorianCalendar) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Only Gregorian calendars are supported.");
        return 0;
    L5:
        return ((r3.year - this.year) * 12) + (r3.month - this.month);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeInt(this.year);
        r1.writeInt(this.month);
    }
}
