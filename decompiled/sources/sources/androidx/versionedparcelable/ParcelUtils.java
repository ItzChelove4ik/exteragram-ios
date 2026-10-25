package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.Parcelable;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class ParcelUtils {
    public static <T extends VersionedParcelable> T fromParcelable(Parcelable r1) {
        if ((r1 instanceof ParcelImpl) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Invalid parcel");
        return null;
    L5:
        return (T) ((ParcelImpl) r1).getVersionedParcel();
    }

    public static <T extends VersionedParcelable> T getVersionedParcelable(Bundle r1, String r2) {
        Bundle r12 = (Bundle) r1.getParcelable(r2);     // Catch: RuntimeException -> L8
        if (r12 != null) goto L6;
        return null;
    L6:
        r12.setClassLoader(ParcelUtils.class.getClassLoader());     // Catch: RuntimeException -> L8
        return (T) fromParcelable(r12.getParcelable("a"));
    L8:
        return null;
    }

    public static void putVersionedParcelable(Bundle r2, String r3, VersionedParcelable r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        Bundle r0 = new Bundle();
        r0.putParcelable("a", toParcelable(r4));
        r2.putParcelable(r3, r0);
    }

    public static Parcelable toParcelable(VersionedParcelable r1) {
        return new ParcelImpl(r1);
    }
}
