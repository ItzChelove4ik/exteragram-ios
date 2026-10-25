package androidx.car.app.hardware;

import android.os.Bundle;
import androidx.car.app.CarAppMetadataHolderService;
import androidx.car.app.CarContext;
import androidx.car.app.HostDispatcher;
import androidx.car.app.HostException;
import androidx.car.app.hardware.climate.CarClimate;
import androidx.car.app.managers.Manager;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public interface CarHardwareManager extends Manager {
    static CarHardwareManager create(CarContext r4, HostDispatcher r5) {
        if (r4.getCarAppApiLevel() < 3) goto L17;
        Bundle r1 = CarAppMetadataHolderService.getServiceInfo(r4).metaData;     // Catch: Throwable -> L14
        if (r1 == null) goto L8;
        String r12 = r1.getString("androidx.car.app.CarAppMetadataHolderService.CAR_HARDWARE_MANAGER");     // Catch: Throwable -> L14
    L9:
        if (r12 == null) goto L13;
        return (CarHardwareManager) Class.forName(r12).getConstructor(new Class[]{CarContext.class, HostDispatcher.class}).newInstance(new Object[]{r4, r5});
    L13:
        throw new ClassNotFoundException("CarHardwareManager metadata could not be found");     // Catch: Throwable -> L14
    L8:
        r12 = null;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("CarHardwareManager not configured. Did you forget to add a dependency on app-automotive or app-projected artifacts?");
        return null;
    L17:
        throw new HostException("Create CarHardwareManager failed", new IllegalArgumentException("Attempted to retrieve CarHardwareManager service, but the host is less than 3"));
    }

    default CarClimate getCarClimate() {
        throw new UnsupportedOperationException();
    }
}
