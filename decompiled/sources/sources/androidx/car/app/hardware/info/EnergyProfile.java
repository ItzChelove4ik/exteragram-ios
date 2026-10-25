package androidx.car.app.hardware.info;

import androidx.car.app.hardware.common.CarValue;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class EnergyProfile {
    public static final int EVCONNECTOR_TYPE_CHADEMO = 3;
    public static final int EVCONNECTOR_TYPE_COMBO_1 = 4;
    public static final int EVCONNECTOR_TYPE_COMBO_2 = 5;
    public static final int EVCONNECTOR_TYPE_GBT = 9;
    public static final int EVCONNECTOR_TYPE_GBT_DC = 10;
    public static final int EVCONNECTOR_TYPE_J1772 = 1;
    public static final int EVCONNECTOR_TYPE_MENNEKES = 2;
    public static final int EVCONNECTOR_TYPE_OTHER = 101;
    public static final int EVCONNECTOR_TYPE_SCAME = 11;
    public static final int EVCONNECTOR_TYPE_TESLA_HPWC = 7;
    public static final int EVCONNECTOR_TYPE_TESLA_ROADSTER = 6;
    public static final int EVCONNECTOR_TYPE_TESLA_SUPERCHARGER = 8;
    public static final int EVCONNECTOR_TYPE_UNKNOWN = 0;
    public static final int FUEL_TYPE_BIODIESEL = 5;
    public static final int FUEL_TYPE_CNG = 8;
    public static final int FUEL_TYPE_DIESEL_1 = 3;
    public static final int FUEL_TYPE_DIESEL_2 = 4;
    public static final int FUEL_TYPE_E85 = 6;
    public static final int FUEL_TYPE_ELECTRIC = 10;
    public static final int FUEL_TYPE_HYDROGEN = 11;
    public static final int FUEL_TYPE_LEADED = 2;
    public static final int FUEL_TYPE_LNG = 9;
    public static final int FUEL_TYPE_LPG = 7;
    public static final int FUEL_TYPE_OTHER = 12;
    public static final int FUEL_TYPE_UNKNOWN = 0;
    public static final int FUEL_TYPE_UNLEADED = 1;
    private final CarValue<List<Integer>> mEvConnectorTypes;
    private final CarValue<List<Integer>> mFuelTypes;

    public static final class Builder {
        CarValue<List<Integer>> mEvConnectorTypes;
        CarValue<List<Integer>> mFuelTypes;

        public Builder() {
            CarValue<List<Integer>> r0 = CarValue.UNKNOWN_INTEGER_LIST;
            this.mEvConnectorTypes = r0;
            this.mFuelTypes = r0;
        }

        public EnergyProfile build() {
            return new EnergyProfile(this);
        }
    }

    private EnergyProfile() {
        CarValue<List<Integer>> r0 = CarValue.UNKNOWN_INTEGER_LIST;
        this.mEvConnectorTypes = r0;
        this.mFuelTypes = r0;
    }

    public EnergyProfile(Builder r2) {
        CarValue<List<Integer>> r0 = r2.mEvConnectorTypes;
        Objects.requireNonNull(r0);
        this.mEvConnectorTypes = r0;
        CarValue<List<Integer>> r22 = r2.mFuelTypes;
        Objects.requireNonNull(r22);
        this.mFuelTypes = r22;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof EnergyProfile) == true) goto L8;
        return false;
    L8:
        EnergyProfile r52 = (EnergyProfile) r5;
        if (Objects.equals(this.mEvConnectorTypes, r52.mEvConnectorTypes) == true) goto L11;
    L13:
        return false;
    L11:
        if (Objects.equals(this.mFuelTypes, r52.mFuelTypes) == false) goto L13;
        return true;
    }

    public CarValue<List<Integer>> getEvConnectorTypes() {
        CarValue<List<Integer>> r0 = this.mEvConnectorTypes;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarValue<List<Integer>> getFuelTypes() {
        CarValue<List<Integer>> r0 = this.mFuelTypes;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mEvConnectorTypes, this.mFuelTypes});
    }

    public String toString() {
        return "[ evConnectorTypes: " + this.mEvConnectorTypes + ", fuelTypes: " + this.mFuelTypes + "]";
    }
}
