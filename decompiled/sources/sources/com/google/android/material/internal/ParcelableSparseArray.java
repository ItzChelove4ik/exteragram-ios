package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

/* loaded from: classes5.dex */
public class ParcelableSparseArray extends SparseArray<Parcelable> implements Parcelable {
    public static final Parcelable.Creator<ParcelableSparseArray> CREATOR = null;

    /* renamed from: com.google.android.material.internal.ParcelableSparseArray$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.ClassLoaderCreator<ParcelableSparseArray> {
        public AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public ParcelableSparseArray createFromParcel(Parcel r2) {
            return new ParcelableSparseArray(r2, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        public ParcelableSparseArray createFromParcel(Parcel r1, ClassLoader r2) {
            return new ParcelableSparseArray(r1, r2);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        @Override // android.os.Parcelable.ClassLoaderCreator
        public /* bridge */ /* synthetic */ ParcelableSparseArray createFromParcel(Parcel r1, ClassLoader r2) {
            return createFromParcel(r1, r2);
        }

        @Override // android.os.Parcelable.Creator
        public ParcelableSparseArray[] newArray(int r1) {
            return new ParcelableSparseArray[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ Object[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public ParcelableSparseArray() {
    }

    public ParcelableSparseArray(Parcel r5, ClassLoader r6) {
        int r0 = r5.readInt();
        int[] r1 = new int[r0];
        r5.readIntArray(r1);
        Parcelable[] r52 = r5.readParcelableArray(r6);
        int r62 = 0;
    L3:
        if (r62 >= r0) goto L5;
        put(r1[r62], r52[r62]);
        r62 = r62 + 1;
        goto L3
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r6, int r7) {
        int r0 = size();
        int[] r1 = new int[r0];
        Parcelable[] r2 = new Parcelable[r0];
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L5;
        r1[r3] = keyAt(r3);
        r2[r3] = valueAt(r3);
        r3 = r3 + 1;
        goto L3
    L5:
        r6.writeInt(r0);
        r6.writeIntArray(r1);
        r6.writeParcelableArray(r2, r7);
    }
}
