package androidx.camera.core.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public class QuirkSettings {
    private final boolean mEnabledWhenDeviceHasQuirk;
    private final Set<Class<? extends Quirk>> mForceDisabledQuirks;
    private final Set<Class<? extends Quirk>> mForceEnabledQuirks;

    /* renamed from: androidx.camera.core.impl.QuirkSettings$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Builder {
        private boolean mEnabledWhenDeviceHasQuirk;
        private Set<Class<? extends Quirk>> mForceDisabledQuirks;
        private Set<Class<? extends Quirk>> mForceEnabledQuirks;

        public Builder() {
            this.mEnabledWhenDeviceHasQuirk = true;
        }

        public QuirkSettings build() {
            return new QuirkSettings(this.mEnabledWhenDeviceHasQuirk, this.mForceEnabledQuirks, this.mForceDisabledQuirks, null);
        }

        public Builder forceDisableQuirks(Set<Class<? extends Quirk>> r2) {
            this.mForceDisabledQuirks = new HashSet(r2);
            return this;
        }

        public Builder forceEnableQuirks(Set<Class<? extends Quirk>> r2) {
            this.mForceEnabledQuirks = new HashSet(r2);
            return this;
        }

        public Builder setEnabledWhenDeviceHasQuirk(boolean r1) {
            this.mEnabledWhenDeviceHasQuirk = r1;
            return this;
        }
    }

    private QuirkSettings(boolean r1, Set<Class<? extends Quirk>> r2, Set<Class<? extends Quirk>> r3) {
        this.mEnabledWhenDeviceHasQuirk = r1;
        if (r2 != null) goto L5;
        Set<Class<? extends Quirk>> r12 = Collections.EMPTY_SET;
    L6:
        this.mForceEnabledQuirks = r12;
        if (r3 != null) goto L9;
        Set<Class<? extends Quirk>> r13 = Collections.EMPTY_SET;
    L10:
        this.mForceDisabledQuirks = r13;
        return;
    L9:
        r13 = new HashSet(r3);
        goto L10
    L5:
        r12 = new HashSet(r2);
        goto L6
    }

    public /* synthetic */ QuirkSettings(boolean r1, Set r2, Set r3, AnonymousClass1 r4) {
        this(r1, r2, r3);
    }

    public static QuirkSettings withDefaultBehavior() {
        return new Builder().setEnabledWhenDeviceHasQuirk(true).build();
    }

    public boolean equals(Object r5) {
        if ((r5 instanceof QuirkSettings) == true) goto L6;
        return false;
    L6:
        if (this != r5) goto L8;
        return true;
    L8:
        QuirkSettings r52 = (QuirkSettings) r5;
        if (this.mEnabledWhenDeviceHasQuirk == r52.mEnabledWhenDeviceHasQuirk) goto L11;
    L15:
        return false;
    L11:
        if (Objects.equals(this.mForceEnabledQuirks, r52.mForceEnabledQuirks) == false) goto L15;
        if (Objects.equals(this.mForceDisabledQuirks, r52.mForceDisabledQuirks) == false) goto L15;
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Boolean.valueOf(this.mEnabledWhenDeviceHasQuirk), this.mForceEnabledQuirks, this.mForceDisabledQuirks});
    }

    public boolean shouldEnableQuirk(Class<? extends Quirk> r3, boolean r4) {
        if (this.mForceEnabledQuirks.contains(r3) == false) goto L6;
        return true;
    L6:
        if (this.mForceDisabledQuirks.contains(r3) == false) goto L9;
        return false;
    L9:
        if (this.mEnabledWhenDeviceHasQuirk == false) goto L12;
        if (r4 == false) goto L12;
        return true;
    L12:
        return false;
    }

    public String toString() {
        return "QuirkSettings{enabledWhenDeviceHasQuirk=" + this.mEnabledWhenDeviceHasQuirk + ", forceEnabledQuirks=" + this.mForceEnabledQuirks + ", forceDisabledQuirks=" + this.mForceDisabledQuirks + '}';
    }
}
