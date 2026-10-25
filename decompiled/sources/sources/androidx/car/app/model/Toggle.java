package androidx.car.app.model;

import java.util.Objects;

/* loaded from: classes3.dex */
public final class Toggle {
    private final boolean mIsChecked;
    private final boolean mIsEnabled;
    private final OnCheckedChangeDelegate mOnCheckedChangeDelegate;

    public static final class Builder {
    }

    public interface OnCheckedChangeListener {
    }

    private Toggle() {
        this.mOnCheckedChangeDelegate = null;
        this.mIsChecked = false;
        this.mIsEnabled = true;
    }

    public Toggle(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Toggle) == true) goto L8;
        return false;
    L8:
        Toggle r52 = (Toggle) r5;
        if (this.mIsChecked == r52.mIsChecked) goto L11;
    L13:
        return false;
    L11:
        if (this.mIsEnabled != r52.mIsEnabled) goto L13;
        return true;
    }

    public OnCheckedChangeDelegate getOnCheckedChangeDelegate() {
        OnCheckedChangeDelegate r0 = this.mOnCheckedChangeDelegate;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Boolean.valueOf(this.mIsChecked), Boolean.valueOf(this.mIsEnabled)});
    }

    public boolean isChecked() {
        return this.mIsChecked;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public String toString() {
        return "[ isChecked: " + this.mIsChecked + ", isEnabled: " + this.mIsEnabled + "]";
    }
}
