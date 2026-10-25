package androidx.car.app.serialization;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Bundleable implements Parcelable {
    public static final Parcelable.Creator<Bundleable> CREATOR = null;
    private final Bundle mBundle;

    /* renamed from: androidx.car.app.serialization.Bundleable$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<Bundleable> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Bundleable createFromParcel(Parcel r2) {
            Bundle r1 = r2.readBundle(getClass().getClassLoader());
            Objects.requireNonNull(r1);
            return new Bundleable(r1);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ Bundleable createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Bundleable[] newArray(int r1) {
            return new Bundleable[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ Bundleable[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public Bundleable(Bundle r1) {
        this.mBundle = r1;
    }

    private Bundleable(Object r1) {
        this.mBundle = Bundler.toBundle(r1);
    }

    public static Bundleable create(Object r1) {
        return new Bundleable(r1);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Object get() {
        return Bundler.fromBundle(this.mBundle);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeBundle(this.mBundle);
    }
}
