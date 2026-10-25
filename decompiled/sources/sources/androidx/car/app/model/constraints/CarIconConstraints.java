package androidx.car.app.model.constraints;

import androidx.car.app.model.CarIcon;
import androidx.core.graphics.drawable.IconCompat;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public final class CarIconConstraints {
    public static final CarIconConstraints DEFAULT = null;
    public static final CarIconConstraints UNCONSTRAINED = null;
    private final int[] mAllowedTypes;

    static {
        UNCONSTRAINED = create(new int[]{1, 2, 4});
        DEFAULT = create(new int[]{1, 2});
    }

    private CarIconConstraints(int[] r1) {
        this.mAllowedTypes = r1;
    }

    private static CarIconConstraints create(int[] r1) {
        return new CarIconConstraints(r1);
    }

    public IconCompat checkSupportedIcon(IconCompat r6) {
        int r0 = r6.getType();
        int[] r5 = this.mAllowedTypes;
        int r1 = r5.length;
        int r2 = 0;
    L4:
        if (r2 >= r1) goto L16;
        if (r0 == r5[r2]) goto L8;
        r2 = r2 + 1;
        goto L4
    L8:
        if (r0 == 4) goto L10;
    L14:
        return r6;
    L10:
        if ("content".equalsIgnoreCase(r6.getUri().getScheme()) == true) goto L14;
        Native$$ExternalSyntheticBUOutline5.m("Unsupported URI scheme for: ", r6);
        return null;
    L16:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Custom icon type is not allowed: ", r0);
        return null;
    }

    public void validateOrThrow(CarIcon r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.getType() != 1) goto L13;
        IconCompat r32 = r3.getIcon();
        if (r32 == null) goto L10;
        checkSupportedIcon(r32);
        return;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("Custom icon does not have a backing IconCompat");
        return;
    }
}
