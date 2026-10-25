package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes3.dex */
class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = null;
    final List<String> mFragments;
    final List<BackStackRecordState> mTransactions;

    /* renamed from: androidx.fragment.app.BackStackState$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<BackStackState> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackStackState createFromParcel(Parcel r1) {
            return new BackStackState(r1);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ BackStackState createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackStackState[] newArray(int r1) {
            return new BackStackState[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ BackStackState[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public BackStackState(Parcel r2) {
        this.mFragments = r2.createStringArrayList();
        this.mTransactions = r2.createTypedArrayList(BackStackRecordState.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeStringList(this.mFragments);
        r1.writeTypedList(this.mTransactions);
    }
}
