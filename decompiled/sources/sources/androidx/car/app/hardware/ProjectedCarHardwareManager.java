package androidx.car.app.hardware;

import androidx.car.app.CarContext;
import androidx.car.app.HostDispatcher;
import androidx.car.app.hardware.climate.CarClimate;
import androidx.car.app.hardware.common.CarHardwareHostDispatcher;
import androidx.car.app.hardware.info.CarInfo;
import androidx.car.app.hardware.info.CarSensors;
import androidx.car.app.hardware.info.ProjectedCarInfo;
import androidx.car.app.hardware.info.ProjectedCarSensors;

/* loaded from: classes3.dex */
public class ProjectedCarHardwareManager implements CarHardwareManager {
    private final ProjectedCarInfo mVehicleInfo;
    private final ProjectedCarSensors mVehicleSensors;

    public ProjectedCarHardwareManager(CarContext r1, HostDispatcher r2) {
        CarHardwareHostDispatcher r12 = new CarHardwareHostDispatcher(r2);
        this.mVehicleInfo = new ProjectedCarInfo(r12);
        this.mVehicleSensors = new ProjectedCarSensors(r12);
    }

    @Override // androidx.car.app.hardware.CarHardwareManager
    public /* bridge */ /* synthetic */ CarClimate getCarClimate() {
        super.getCarClimate();
        return null;
    }

    public CarInfo getCarInfo() {
        return this.mVehicleInfo;
    }

    public CarSensors getCarSensors() {
        return this.mVehicleSensors;
    }
}
