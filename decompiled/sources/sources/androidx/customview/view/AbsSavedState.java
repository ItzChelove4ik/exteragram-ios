package androidx.customview.view;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import c.f$$ExternalSyntheticBUOutline1;
import okio.Segment$$ExternalSyntheticBUOutline1;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes3.dex */
public abstract class AbsSavedState implements Parcelable {
    public static final Parcelable.Creator<AbsSavedState> CREATOR = null;
    public static final AbsSavedState EMPTY_STATE = null;
    private final Parcelable mSuperState;

    /* renamed from: androidx.customview.view.AbsSavedState$1, reason: invalid class name */
    public class AnonymousClass1 extends AbsSavedState {
        public AnonymousClass1() {
            super(null);
        }
    }

    /* renamed from: androidx.customview.view.AbsSavedState$2, reason: invalid class name */
    public class AnonymousClass2 implements Parcelable.ClassLoaderCreator<AbsSavedState> {
        public AnonymousClass2() {
        }

        @Override // android.os.Parcelable.Creator
        public AbsSavedState createFromParcel(Parcel r2) {
            return createFromParcel(r2, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        public AbsSavedState createFromParcel(Parcel r1, ClassLoader r2) {
            if (r1.readParcelable(r2) == null) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("superState must be null");
            return null;
        L5:
            return AbsSavedState.EMPTY_STATE;
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        @Override // android.os.Parcelable.ClassLoaderCreator
        public /* bridge */ /* synthetic */ AbsSavedState createFromParcel(Parcel r1, ClassLoader r2) {
            return createFromParcel(r1, r2);
        }

        @Override // android.os.Parcelable.Creator
        public AbsSavedState[] newArray(int r1) {
            return new AbsSavedState[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ Object[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        EMPTY_STATE = new AnonymousClass1();
        CREATOR = new AnonymousClass2();
    }

    private AbsSavedState() {
        this.mSuperState = null;
    }

    public AbsSavedState(Parcel r1, ClassLoader r2) {
        Parcelable r12 = r1.readParcelable(r2);
        if (r12 != null) goto L6;
        r12 = EMPTY_STATE;
    L6:
        this.mSuperState = r12;
    }

    public AbsSavedState(Parcelable r3) {
        if (r3 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("superState must not be null");
        throw null;
    L5:
        if (r3 != EMPTY_STATE) goto L8;
        r3 = null;
    L8:
        this.mSuperState = r3;
    }

    public /* synthetic */ AbsSavedState(AnonymousClass1 r1) {
        this();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final Parcelable getSuperState() {
        return this.mSuperState;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeParcelable(this.mSuperState, r2);
    }
}
