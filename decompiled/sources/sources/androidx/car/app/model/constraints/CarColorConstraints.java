package androidx.car.app.model.constraints;

import androidx.car.app.model.CarColor;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.HashSet;

/* loaded from: classes3.dex */
public final class CarColorConstraints {
    public static final CarColorConstraints STANDARD_ONLY = null;
    public static final CarColorConstraints UNCONSTRAINED = null;
    private final HashSet<Integer> mAllowedTypes;

    static {
        UNCONSTRAINED = create(new int[]{0, 1, 2, 3, 4, 5, 6, 7});
        STANDARD_ONLY = create(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    private CarColorConstraints(int[] r5) {
        this.mAllowedTypes = new HashSet();
        int r0 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        int r2 = r5[r1];
        this.mAllowedTypes.add(Integer.valueOf(r2));
        r1 = r1 + 1;
        goto L3
    }

    private static CarColorConstraints create(int[] r1) {
        return new CarColorConstraints(r1);
    }

    public void validateOrThrow(CarColor r2) {
        int r0 = r2.getType();
        if (this.mAllowedTypes.contains(Integer.valueOf(r0)) == false) goto L5;
        return;
    L5:
        Native$$ExternalSyntheticBUOutline5.m("Car color type is not allowed: ", r2);
    }
}
