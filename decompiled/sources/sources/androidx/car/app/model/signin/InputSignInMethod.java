package androidx.car.app.model.signin;

import androidx.car.app.model.CarText;
import androidx.car.app.model.InputCallbackDelegate;
import androidx.car.app.model.signin.SignInTemplate;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class InputSignInMethod implements SignInTemplate.SignInMethod {
    public static final int INPUT_TYPE_DEFAULT = 1;
    public static final int INPUT_TYPE_PASSWORD = 2;
    public static final int KEYBOARD_DEFAULT = 1;
    public static final int KEYBOARD_EMAIL = 2;
    public static final int KEYBOARD_NUMBER = 4;
    public static final int KEYBOARD_PHONE = 3;
    private final CarText mDefaultValue;
    private final CarText mErrorMessage;
    private final CarText mHint;
    private final InputCallbackDelegate mInputCallbackDelegate;
    private final int mInputType;
    private final int mKeyboardType;
    private final boolean mShowKeyboardByDefault;

    public static final class Builder {
    }

    private InputSignInMethod() {
        this.mHint = null;
        this.mDefaultValue = null;
        this.mInputType = 1;
        this.mErrorMessage = null;
        this.mKeyboardType = 1;
        this.mInputCallbackDelegate = null;
        this.mShowKeyboardByDefault = false;
    }

    public InputSignInMethod(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof InputSignInMethod) == true) goto L8;
        return false;
    L8:
        InputSignInMethod r52 = (InputSignInMethod) r5;
        if (this.mInputType == r52.mInputType) goto L11;
    L21:
        return false;
    L11:
        if (this.mKeyboardType != r52.mKeyboardType) goto L21;
        if (this.mShowKeyboardByDefault != r52.mShowKeyboardByDefault) goto L21;
        if (Objects.equals(this.mHint, r52.mHint) == false) goto L21;
        if (Objects.equals(this.mDefaultValue, r52.mDefaultValue) == false) goto L21;
        if (Objects.equals(this.mErrorMessage, r52.mErrorMessage) == false) goto L21;
        return true;
    }

    public CarText getDefaultValue() {
        return this.mDefaultValue;
    }

    public CarText getErrorMessage() {
        return this.mErrorMessage;
    }

    public CarText getHint() {
        return this.mHint;
    }

    public InputCallbackDelegate getInputCallbackDelegate() {
        InputCallbackDelegate r0 = this.mInputCallbackDelegate;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int getInputType() {
        return this.mInputType;
    }

    public int getKeyboardType() {
        return this.mKeyboardType;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mHint, this.mDefaultValue, Integer.valueOf(this.mInputType), this.mErrorMessage, Integer.valueOf(this.mKeyboardType), Boolean.valueOf(this.mShowKeyboardByDefault)});
    }

    public boolean isShowKeyboardByDefault() {
        return this.mShowKeyboardByDefault;
    }

    public String toString() {
        return "[inputType:" + this.mInputType + ", keyboardType: " + this.mKeyboardType + "]";
    }
}
