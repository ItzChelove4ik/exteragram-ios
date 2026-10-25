package androidx.media3.session.legacy;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public abstract class LegacyParcelableUtil {
    public static <T extends Parcelable, U extends Parcelable> T convert(U r2, Parcelable.Creator<T> r3) {
        if (r2 != null) goto L5;
        return null;
    L5:
        Parcelable r22 = (Parcelable) maybeApplyMediaDescriptionParcelableBugWorkaround(r2);
        Parcel r0 = Parcel.obtain();
        r22.writeToParcel(r0, 0);     // Catch: Throwable -> L9
        r0.setDataPosition(0);     // Catch: Throwable -> L9
        T r23 = (T) maybeApplyMediaDescriptionParcelableBugWorkaround(r3.createFromParcel(r0));     // Catch: Throwable -> L9
        r0.recycle();
        return r23;
    L9:
        th = move-exception;
        r0.recycle();
        throw th;
    }

    private static <T> T maybeApplyMediaDescriptionParcelableBugWorkaround(T r0) {
        return r0;
    }
}
