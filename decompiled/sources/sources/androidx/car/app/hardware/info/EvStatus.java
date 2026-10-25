package androidx.car.app.hardware.info;

import androidx.car.app.hardware.common.CarValue;
import java.util.Objects;

/* loaded from: classes3.dex */
public class EvStatus {
    private final CarValue<Boolean> mEvChargePortConnected;
    private final CarValue<Boolean> mEvChargePortOpen;

    public static final class Builder {
        CarValue<Boolean> mEvChargePortConnected;
        CarValue<Boolean> mEvChargePortOpen;

        public Builder() {
            CarValue<Boolean> r0 = CarValue.UNKNOWN_BOOLEAN;
            this.mEvChargePortOpen = r0;
            this.mEvChargePortConnected = r0;
        }

        public EvStatus build() {
            return new EvStatus(this);
        }
    }

    private EvStatus() {
        CarValue<Boolean> r0 = CarValue.UNKNOWN_BOOLEAN;
        this.mEvChargePortOpen = r0;
        this.mEvChargePortConnected = r0;
    }

    public EvStatus(Builder r2) {
        this.mEvChargePortConnected = r2.mEvChargePortConnected;
        this.mEvChargePortOpen = r2.mEvChargePortOpen;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof EvStatus) == true) goto L8;
        return false;
    L8:
        EvStatus r52 = (EvStatus) r5;
        if (Objects.equals(this.mEvChargePortConnected, r52.mEvChargePortConnected) == true) goto L11;
    L13:
        return false;
    L11:
        if (Objects.equals(this.mEvChargePortOpen, r52.mEvChargePortOpen) == false) goto L13;
        return true;
    }

    public CarValue<Boolean> getEvChargePortConnected() {
        CarValue<Boolean> r0 = this.mEvChargePortConnected;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarValue<Boolean> getEvChargePortOpen() {
        CarValue<Boolean> r0 = this.mEvChargePortOpen;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mEvChargePortOpen, this.mEvChargePortConnected});
    }

    public String toString() {
        return "[ EV charge port open: " + this.mEvChargePortOpen + ", EV charge port connected: " + this.mEvChargePortConnected + "]";
    }
}
