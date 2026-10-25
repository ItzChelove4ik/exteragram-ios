package android.support.v4.app;

import androidx.core.app.RemoteActionCompat;
import androidx.versionedparcelable.VersionedParcel;

/* loaded from: classes3.dex */
public final class RemoteActionCompatParcelizer extends androidx.core.app.RemoteActionCompatParcelizer {
    public RemoteActionCompatParcelizer() {
    }

    public static RemoteActionCompat read(VersionedParcel r0) {
        return androidx.core.app.RemoteActionCompatParcelizer.read(r0);
    }

    public static void write(RemoteActionCompat r0, VersionedParcel r1) {
        androidx.core.app.RemoteActionCompatParcelizer.write(r0, r1);
    }
}
