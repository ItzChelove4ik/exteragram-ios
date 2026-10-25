package com.google.android.material.stateful;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.SimpleArrayMap;
import androidx.customview.view.AbsSavedState;

/* loaded from: classes5.dex */
public class ExtendableSavedState extends AbsSavedState {
    public static final Parcelable.Creator<ExtendableSavedState> CREATOR = null;
    public final SimpleArrayMap<String, Bundle> extendableStates;

    /* renamed from: com.google.android.material.stateful.ExtendableSavedState$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.ClassLoaderCreator<ExtendableSavedState> {
        public AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public ExtendableSavedState createFromParcel(Parcel r2) {
            return new ExtendableSavedState(r2, null, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        public ExtendableSavedState createFromParcel(Parcel r2, ClassLoader r3) {
            return new ExtendableSavedState(r2, r3, null);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        @Override // android.os.Parcelable.ClassLoaderCreator
        public /* bridge */ /* synthetic */ ExtendableSavedState createFromParcel(Parcel r1, ClassLoader r2) {
            return createFromParcel(r1, r2);
        }

        @Override // android.os.Parcelable.Creator
        public ExtendableSavedState[] newArray(int r1) {
            return new ExtendableSavedState[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ Object[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    private ExtendableSavedState(Parcel r6, ClassLoader r7) {
        super(r6, r7);
        int r72 = r6.readInt();
        String[] r0 = new String[r72];
        r6.readStringArray(r0);
        Bundle[] r1 = new Bundle[r72];
        r6.readTypedArray(r1, Bundle.CREATOR);
        this.extendableStates = new SimpleArrayMap(r72);
        int r62 = 0;
    L3:
        if (r62 >= r72) goto L5;
        this.extendableStates.put(r0[r62], r1[r62]);
        r62 = r62 + 1;
        goto L3
    }

    public /* synthetic */ ExtendableSavedState(Parcel r1, ClassLoader r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public ExtendableSavedState(Parcelable r1) {
        super(r1);
        this.extendableStates = new SimpleArrayMap();
    }

    public String toString() {
        return "ExtendableSavedState{" + Integer.toHexString(System.identityHashCode(this)) + " states=" + this.extendableStates + "}";
    }

    @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel r6, int r7) {
        super.writeToParcel(r6, r7);
        int r72 = this.extendableStates.size();
        r6.writeInt(r72);
        String[] r0 = new String[r72];
        Bundle[] r1 = new Bundle[r72];
        int r3 = 0;
    L3:
        if (r3 >= r72) goto L5;
        r0[r3] = this.extendableStates.keyAt(r3);
        r1[r3] = this.extendableStates.valueAt(r3);
        r3 = r3 + 1;
        goto L3
    L5:
        r6.writeStringArray(r0);
        r6.writeTypedArray(r1, 0);
    }
}
