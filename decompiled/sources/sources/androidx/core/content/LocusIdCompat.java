package androidx.core.content;

import android.content.LocusId;
import android.os.Build;
import androidx.core.util.Preconditions;

/* loaded from: classes.dex */
public final class LocusIdCompat {
    private final String mId;
    private final LocusId mWrapped;

    public static class Api29Impl {
        public static LocusId create(String r1) {
            return new LocusId(r1);
        }

        public static String getId(LocusId r0) {
            return r0.getId();
        }
    }

    public LocusIdCompat(String r3) {
        this.mId = (String) Preconditions.checkStringNotEmpty(r3, "id cannot be empty");
        if (Build.VERSION.SDK_INT < 29) goto L6;
        this.mWrapped = Api29Impl.create(r3);
        return;
    L6:
        this.mWrapped = null;
    }

    private String getSanitizedId() {
        return this.mId.length() + "_chars";
    }

    public static LocusIdCompat toLocusIdCompat(LocusId r2) {
        Preconditions.checkNotNull(r2, "locusId cannot be null");
        return new LocusIdCompat((String) Preconditions.checkStringNotEmpty(Api29Impl.getId(r2), "id cannot be empty"));
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L9;
        return false;
    L9:
        if (LocusIdCompat.class == r5.getClass()) goto L11;
        return false;
    L11:
        String r4 = this.mId;
        String r52 = ((LocusIdCompat) r5).mId;
        if (r4 != null) goto L17;
        if (r52 != null) goto L15;
        return true;
    L15:
        return false;
    L17:
        return r4.equals(r52);
    }

    public String getId() {
        return this.mId;
    }

    public int hashCode() {
        String r1 = this.mId;
        if (r1 != null) goto L5;
        int r12 = 0;
    L7:
        return 31 + r12;
    L5:
        r12 = r1.hashCode();
        goto L7
    }

    public LocusId toLocusId() {
        return this.mWrapped;
    }

    public String toString() {
        return "LocusIdCompat[" + getSanitizedId() + "]";
    }
}
