package androidx.car.app.hardware.common;

import java.util.HashMap;
import java.util.Objects;

/* loaded from: classes3.dex */
public class CarResultStubMap<T, U> {
    private final CarHardwareHostDispatcher mHostDispatcher;
    private final int mResultType;
    private final HashMap<U, CarResultStub<T>> mStubMap;
    private final T mUnsupportedValue;

    public CarResultStubMap(int r2, T r3, CarHardwareHostDispatcher r4) {
        this.mStubMap = new HashMap();
        this.mResultType = r2;
        Objects.requireNonNull(r3);
        this.mUnsupportedValue = r3;
        Objects.requireNonNull(r4);
        this.mHostDispatcher = r4;
    }
}
