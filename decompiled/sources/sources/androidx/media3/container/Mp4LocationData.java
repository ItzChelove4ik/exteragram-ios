package androidx.media3.container;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import com.google.common.primitives.Floats;

/* loaded from: classes3.dex */
public final class Mp4LocationData implements Metadata.Entry {
    public final float latitude;
    public final float longitude;

    public Mp4LocationData(float r3, float r4) {
        if (r3 >= (-90.0f)) goto L5;
    L11:
        boolean r0 = false;
    L12:
        Assertions.checkArgument(r0, "Invalid latitude or longitude");
        this.latitude = r3;
        this.longitude = r4;
        return;
    L5:
        if (r3 > 90.0f) goto L11;
        if (r4 < (-180.0f)) goto L11;
        if (r4 > 180.0f) goto L11;
        r0 = true;
        goto L12
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (Mp4LocationData.class != r5.getClass()) goto L15;
        Mp4LocationData r52 = (Mp4LocationData) r5;
        if (this.latitude != r52.latitude) goto L15;
        if (this.longitude != r52.longitude) goto L15;
        return true;
    }

    public int hashCode() {
        return ((527 + Floats.hashCode(this.latitude)) * 31) + Floats.hashCode(this.longitude);
    }

    public String toString() {
        return "xyz: latitude=" + this.latitude + ", longitude=" + this.longitude;
    }
}
