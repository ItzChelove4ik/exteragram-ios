package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

/* loaded from: classes5.dex */
public class ParcelableSparseIntArray extends SparseIntArray implements Parcelable {
    public static final Parcelable.Creator<ParcelableSparseIntArray> CREATOR = null;

    /* renamed from: com.google.android.material.internal.ParcelableSparseIntArray$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<ParcelableSparseIntArray> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableSparseIntArray createFromParcel(Parcel r6) {
            int r5 = r6.readInt();
            ParcelableSparseIntArray r0 = new ParcelableSparseIntArray(r5);
            int[] r1 = new int[r5];
            int[] r2 = new int[r5];
            r6.readIntArray(r1);
            r6.readIntArray(r2);
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
        public /* bridge */ /* synthetic */ ParcelableSparseIntArray createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableSparseIntArray[] newArray(int r1) {
            return new ParcelableSparseIntArray[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ ParcelableSparseIntArray[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public ParcelableSparseIntArray() {
    }

    public ParcelableSparseIntArray(int r1) {
        super(r1);
    }

    public ParcelableSparseIntArray(SparseIntArray r4) {
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
        int[] r0 = new int[size()];
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
        r4.writeIntArray(r0);
    }
}
