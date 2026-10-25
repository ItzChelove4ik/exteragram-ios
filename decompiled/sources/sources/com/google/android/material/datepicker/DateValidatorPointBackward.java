package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class DateValidatorPointBackward implements CalendarConstraints.DateValidator {
    public static final Parcelable.Creator<DateValidatorPointBackward> CREATOR = null;
    private final long point;

    /* renamed from: com.google.android.material.datepicker.DateValidatorPointBackward$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<DateValidatorPointBackward> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DateValidatorPointBackward createFromParcel(Parcel r3) {
            return new DateValidatorPointBackward(r3.readLong(), null);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ DateValidatorPointBackward createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DateValidatorPointBackward[] newArray(int r1) {
            return new DateValidatorPointBackward[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ DateValidatorPointBackward[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    private DateValidatorPointBackward(long r1) {
        this.point = r1;
    }

    public /* synthetic */ DateValidatorPointBackward(long r1, AnonymousClass1 r3) {
        this(r1);
    }

    public static DateValidatorPointBackward before(long r1) {
        return new DateValidatorPointBackward(r1);
    }

    public static DateValidatorPointBackward now() {
        return before(UtcDates.getTodayCalendar().getTimeInMillis());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object r6) {
        if (this != r6) goto L6;
        return true;
    L6:
        if ((r6 instanceof DateValidatorPointBackward) == true) goto L9;
        return false;
    L9:
        if (this.point != ((DateValidatorPointBackward) r6).point) goto L11;
        return true;
    L11:
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.point)});
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public boolean isValid(long r3) {
        if (r3 > this.point) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        r3.writeLong(this.point);
    }
}
