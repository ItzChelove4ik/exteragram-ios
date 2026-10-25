package androidx.car.app.model.signin;

import androidx.car.app.model.CarText;
import androidx.car.app.model.signin.SignInTemplate;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class PinSignInMethod implements SignInTemplate.SignInMethod {
    private static final int MAX_PIN_LENGTH = 12;
    private final CarText mPinCode;

    private PinSignInMethod() {
        this.mPinCode = null;
    }

    public PinSignInMethod(CharSequence r4) {
        Objects.requireNonNull(r4);
        int r0 = r4.length();
        if (r0 != 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("PIN must not be empty");
        throw null;
    L5:
        if (r0 > 12) goto L8;
        this.mPinCode = CarText.create(r4);
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("PIN must not be longer than 12 characters");
        throw null;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof PinSignInMethod) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mPinCode, ((PinSignInMethod) r2).mPinCode);
    }

    public CarText getPinCode() {
        CarText r0 = this.mPinCode;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mPinCode});
    }
}
