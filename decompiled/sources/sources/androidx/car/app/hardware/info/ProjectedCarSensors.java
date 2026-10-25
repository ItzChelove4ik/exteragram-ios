package androidx.car.app.hardware.info;

import androidx.car.app.hardware.common.CarHardwareHostDispatcher;
import androidx.car.app.hardware.common.CarResultStubMap;
import androidx.car.app.hardware.common.CarValue;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class ProjectedCarSensors implements CarSensors {
    private static final CarValue<List<Float>> UNIMPLEMENTED_FLOAT_LIST = null;
    private final CarResultStubMap<Accelerometer, Integer> mAccelerometerCarResultStubMap;
    final CarHardwareHostDispatcher mCarHardwareHostDispatcher;
    private final CarResultStubMap<CarHardwareLocation, Integer> mCarHardwareLocationCarResultStubMap;
    private final CarResultStubMap<Compass, Integer> mCompassCarResultStubMap;
    private final CarResultStubMap<Gyroscope, Integer> mGyroscopeCarResultStubMap;

    static {
        UNIMPLEMENTED_FLOAT_LIST = new CarValue(null, 0, 2);
    }

    public ProjectedCarSensors(CarHardwareHostDispatcher r8) {
        Objects.requireNonNull(r8);
        this.mCarHardwareHostDispatcher = r8;
        CarValue<List<Float>> r2 = UNIMPLEMENTED_FLOAT_LIST;
        this.mAccelerometerCarResultStubMap = new CarResultStubMap(20, new Accelerometer(r2), r8);
        this.mGyroscopeCarResultStubMap = new CarResultStubMap(22, new Gyroscope(r2), r8);
        this.mCompassCarResultStubMap = new CarResultStubMap(21, new Compass(r2), r8);
        this.mCarHardwareLocationCarResultStubMap = new CarResultStubMap(23, new CarHardwareLocation(new CarValue(null, 0, 2)), r8);
    }
}
