package androidx.versionedparcelable;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes4.dex */
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = null;
    private final VersionedParcelable mParcel;

    /* renamed from: androidx.versionedparcelable.ParcelImpl$1, reason: invalid class name */
    public static class AnonymousClass1 implements Parcelable.Creator<ParcelImpl> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelImpl createFromParcel(Parcel r1) {
            return new ParcelImpl(r1);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ ParcelImpl createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelImpl[] newArray(int r1) {
            return new ParcelImpl[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ ParcelImpl[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public ParcelImpl(Parcel r2) {
        this.mParcel = new VersionedParcelParcel(r2).readVersionedParcelable();
    }

    public ParcelImpl(VersionedParcelable r1) {
        this.mParcel = r1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public <T extends VersionedParcelable> T getVersionedParcel() {
        return (T) this.mParcel;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        new VersionedParcelParcel(r1).writeVersionedParcelable(this.mParcel);
    }
}
