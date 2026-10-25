package android.support.v4.graphics.drawable;

import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcel;

/* loaded from: classes3.dex */
public final class IconCompatParcelizer extends androidx.core.graphics.drawable.IconCompatParcelizer {
    public IconCompatParcelizer() {
    }

    public static IconCompat read(VersionedParcel r0) {
        return androidx.core.graphics.drawable.IconCompatParcelizer.read(r0);
    }

    public static void write(IconCompat r0, VersionedParcel r1) {
        androidx.core.graphics.drawable.IconCompatParcelizer.write(r0, r1);
    }
}
