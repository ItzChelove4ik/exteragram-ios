package androidx.car.app.constraints;

import androidx.car.app.CarContext;
import androidx.car.app.HostDispatcher;
import androidx.car.app.managers.Manager;
import java.util.Objects;

/* loaded from: classes3.dex */
public class ConstraintManager implements Manager {
    private final CarContext mCarContext;
    private final HostDispatcher mHostDispatcher;

    private ConstraintManager(CarContext r1, HostDispatcher r2) {
        this.mCarContext = r1;
        this.mHostDispatcher = r2;
    }

    public static ConstraintManager create(CarContext r1, HostDispatcher r2) {
        Objects.requireNonNull(r1);
        Objects.requireNonNull(r2);
        return new ConstraintManager(r1, r2);
    }
}
