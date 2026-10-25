package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.firebase.crashlytics.internal.model.StaticSessionData;

/* loaded from: classes.dex */
final class AutoValue_StaticSessionData_DeviceData extends StaticSessionData.DeviceData {
    private final int arch;
    private final int availableProcessors;
    private final long diskSpace;
    private final boolean isEmulator;
    private final String manufacturer;
    private final String model;
    private final String modelClass;
    private final int state;
    private final long totalRam;

    public AutoValue_StaticSessionData_DeviceData(int r1, String r2, int r3, long r4, long r6, boolean r8, int r9, String r10, String r11) {
        this.arch = r1;
        if (r2 == null) goto L14;
        this.model = r2;
        this.availableProcessors = r3;
        this.totalRam = r4;
        this.diskSpace = r6;
        this.isEmulator = r8;
        this.state = r9;
        if (r10 == null) goto L12;
        this.manufacturer = r10;
        if (r11 == null) goto L10;
        this.modelClass = r11;
        return;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null modelClass");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("Null manufacturer");
        throw null;
    L14:
        f$$ExternalSyntheticBUOutline2.m("Null model");
        throw null;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public int arch() {
        return this.arch;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public int availableProcessors() {
        return this.availableProcessors;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public long diskSpace() {
        return this.diskSpace;
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof StaticSessionData.DeviceData) == false) goto L26;
        StaticSessionData.DeviceData r82 = (StaticSessionData.DeviceData) r8;
        if (this.arch != r82.arch()) goto L26;
        if (this.model.equals(r82.model()) == false) goto L26;
        if (this.availableProcessors != r82.availableProcessors()) goto L26;
        if (this.totalRam != r82.totalRam()) goto L26;
        if (this.diskSpace != r82.diskSpace()) goto L26;
        if (this.isEmulator != r82.isEmulator()) goto L26;
        if (this.state != r82.state()) goto L26;
        if (this.manufacturer.equals(r82.manufacturer()) == false) goto L26;
        if (this.modelClass.equals(r82.modelClass()) == false) goto L26;
        return true;
    L26:
        return false;
    }

    public int hashCode() {
        int r0 = (((((this.arch ^ 1000003) * 1000003) ^ this.model.hashCode()) * 1000003) ^ this.availableProcessors) * 1000003;
        long r2 = this.totalRam;
        int r02 = (r0 ^ ((int) (r2 ^ (r2 >>> 32)))) * 1000003;
        long r22 = this.diskSpace;
        int r03 = (r02 ^ ((int) (r22 ^ (r22 >>> 32)))) * 1000003;
        if (this.isEmulator == false) goto L5;
        int r23 = 1231;
    L6:
        int r04 = (((((r03 ^ r23) * 1000003) ^ this.state) * 1000003) ^ this.manufacturer.hashCode()) * 1000003;
        return this.modelClass.hashCode() ^ r04;
    L5:
        r23 = 1237;
        goto L6
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public boolean isEmulator() {
        return this.isEmulator;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public String manufacturer() {
        return this.manufacturer;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public String model() {
        return this.model;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public String modelClass() {
        return this.modelClass;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public int state() {
        return this.state;
    }

    public String toString() {
        return "DeviceData{arch=" + this.arch + ", model=" + this.model + ", availableProcessors=" + this.availableProcessors + ", totalRam=" + this.totalRam + ", diskSpace=" + this.diskSpace + ", isEmulator=" + this.isEmulator + ", state=" + this.state + ", manufacturer=" + this.manufacturer + ", modelClass=" + this.modelClass + "}";
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.DeviceData
    public long totalRam() {
        return this.totalRam;
    }
}
