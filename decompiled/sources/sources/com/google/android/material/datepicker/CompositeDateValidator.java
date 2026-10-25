package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.util.Preconditions;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public final class CompositeDateValidator implements CalendarConstraints.DateValidator {
    private static final Operator ALL_OPERATOR = null;
    private static final Operator ANY_OPERATOR = null;
    private static final int COMPARATOR_ALL_ID = 2;
    private static final int COMPARATOR_ANY_ID = 1;
    public static final Parcelable.Creator<CompositeDateValidator> CREATOR = null;
    private final Operator operator;
    private final List<CalendarConstraints.DateValidator> validators;

    /* renamed from: com.google.android.material.datepicker.CompositeDateValidator$1, reason: invalid class name */
    public class AnonymousClass1 implements Operator {
        public AnonymousClass1() {
        }

        @Override // com.google.android.material.datepicker.CompositeDateValidator.Operator
        public int getId() {
            return 1;
        }

        @Override // com.google.android.material.datepicker.CompositeDateValidator.Operator
        public boolean isValid(List<CalendarConstraints.DateValidator> r1, long r2) {
            Iterator<CalendarConstraints.DateValidator> r0 = r1.iterator();
        L4:
            if (r0.hasNext() == false) goto L12;
            CalendarConstraints.DateValidator r12 = r0.next();
            if (r12 == null) goto L4;
            if (r12.isValid(r2) == false) goto L4;
            return true;
        L12:
            return false;
        }
    }

    /* renamed from: com.google.android.material.datepicker.CompositeDateValidator$2, reason: invalid class name */
    public class AnonymousClass2 implements Operator {
        public AnonymousClass2() {
        }

        @Override // com.google.android.material.datepicker.CompositeDateValidator.Operator
        public int getId() {
            return 2;
        }

        @Override // com.google.android.material.datepicker.CompositeDateValidator.Operator
        public boolean isValid(List<CalendarConstraints.DateValidator> r1, long r2) {
            Iterator<CalendarConstraints.DateValidator> r0 = r1.iterator();
        L4:
            if (r0.hasNext() == false) goto L12;
            CalendarConstraints.DateValidator r12 = r0.next();
            if (r12 == null) goto L4;
            if (r12.isValid(r2) == true) goto L4;
            return false;
        L12:
            return true;
        }
    }

    /* renamed from: com.google.android.material.datepicker.CompositeDateValidator$3, reason: invalid class name */
    public class AnonymousClass3 implements Parcelable.Creator<CompositeDateValidator> {
        public AnonymousClass3() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CompositeDateValidator createFromParcel(Parcel r3) {
            ArrayList r2 = r3.readArrayList(CalendarConstraints.DateValidator.class.getClassLoader());
            int r32 = r3.readInt();
            if (r32 != 2) goto L6;
            Operator r33 = CompositeDateValidator.access$000();
        L10:
            return new CompositeDateValidator((List) Preconditions.checkNotNull(r2), r33, null);
        L6:
            if (r32 != 1) goto L8;
            r33 = CompositeDateValidator.access$100();
            goto L10
        L8:
            r33 = CompositeDateValidator.access$000();
            goto L10
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ CompositeDateValidator createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CompositeDateValidator[] newArray(int r1) {
            return new CompositeDateValidator[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ CompositeDateValidator[] newArray(int r1) {
            return newArray(r1);
        }
    }

    public interface Operator {
        int getId();

        boolean isValid(List<CalendarConstraints.DateValidator> r1, long r2);
    }

    static {
        ANY_OPERATOR = new AnonymousClass1();
        ALL_OPERATOR = new AnonymousClass2();
        CREATOR = new AnonymousClass3();
    }

    private CompositeDateValidator(List<CalendarConstraints.DateValidator> r1, Operator r2) {
        this.validators = r1;
        this.operator = r2;
    }

    public /* synthetic */ CompositeDateValidator(List r1, Operator r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public static /* synthetic */ Operator access$000() {
        return ALL_OPERATOR;
    }

    public static /* synthetic */ Operator access$100() {
        return ANY_OPERATOR;
    }

    public static CalendarConstraints.DateValidator allOf(List<CalendarConstraints.DateValidator> r2) {
        return new CompositeDateValidator(r2, ALL_OPERATOR);
    }

    public static CalendarConstraints.DateValidator anyOf(List<CalendarConstraints.DateValidator> r2) {
        return new CompositeDateValidator(r2, ANY_OPERATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof CompositeDateValidator) == true) goto L8;
        return false;
    L8:
        CompositeDateValidator r52 = (CompositeDateValidator) r5;
        if (this.validators.equals(r52.validators) == true) goto L11;
    L13:
        return false;
    L11:
        if (this.operator.getId() != r52.operator.getId()) goto L13;
        return true;
    }

    public int hashCode() {
        return this.validators.hashCode();
    }

    @Override // com.google.android.material.datepicker.CalendarConstraints.DateValidator
    public boolean isValid(long r2) {
        return this.operator.isValid(this.validators, r2);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeList(this.validators);
        r1.writeInt(this.operator.getId());
    }
}
