package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;

/* loaded from: classes5.dex */
public class ParcelableSparseBooleanArray extends SparseBooleanArray implements Parcelable {
    public static final Parcelable.Creator<ParcelableSparseBooleanArray> CREATOR = null;

    /* renamed from: com.google.android.material.internal.ParcelableSparseBooleanArray$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<ParcelableSparseBooleanArray> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableSparseBooleanArray createFromParcel(Parcel r6) {
            int r5 = r6.readInt();
            ParcelableSparseBooleanArray r0 = new ParcelableSparseBooleanArray(r5);
            int[] r1 = new int[r5];
            boolean[] r2 = new boolean[r5];
            r6.readIntArray(r1);
            r6.readBooleanArray(r2);
            int r62 = 0;
        L3:
            if (r62 >= r5) goto L5;
            r0.put(r1[r62], r2[r62]);
            r62 = r62 + 1;
            goto L3
        L5:
            return r0;
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ ParcelableSparseBooleanArray createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableSparseBooleanArray[] newArray(int r1) {
            return new ParcelableSparseBooleanArray[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ ParcelableSparseBooleanArray[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public ParcelableSparseBooleanArray() {
    }

    public ParcelableSparseBooleanArray(int r1) {
        super(r1);
    }

    public ParcelableSparseBooleanArray(SparseBooleanArray r4) {
        super(r4.size());
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L6;
        put(r4.keyAt(r0), r4.valueAt(r0));
        r0 = r0 + 1;
        goto L4
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int[] r52 = new int[size()];
        boolean[] r0 = new boolean[size()];
        int r1 = 0;
    L4:
        if (r1 >= size()) goto L6;
        r52[r1] = keyAt(r1);
        r0[r1] = valueAt(r1);
        r1 = r1 + 1;
        goto L4
    L6:
        r4.writeInt(size());
        r4.writeIntArray(r52);
        r4.writeBooleanArray(r0);
    }
}
