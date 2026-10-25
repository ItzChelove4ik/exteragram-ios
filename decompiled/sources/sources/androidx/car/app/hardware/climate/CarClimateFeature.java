package androidx.car.app.hardware.climate;

import androidx.car.app.hardware.common.CarZone;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class CarClimateFeature {
    private final List<CarZone> mCarZones;
    private final int mFeature;

    public static final class Builder {
    }

    public CarClimateFeature(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (CarClimateFeature.class != r5.getClass()) goto L15;
        CarClimateFeature r52 = (CarClimateFeature) r5;
        if (Integer.valueOf(this.mFeature).equals(Integer.valueOf(r52.mFeature)) == false) goto L15;
        if (Objects.equals(this.mCarZones, r52.mCarZones) == false) goto L15;
        return true;
    }

    public List<CarZone> getCarZones() {
        return this.mCarZones;
    }

    public int getFeature() {
        return this.mFeature;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.mFeature), this.mCarZones});
    }

    public String toString() {
        return "ClimateProfileFeature{mFeature='" + this.mFeature + "', mCarZones=" + this.mCarZones + '}';
    }
}
