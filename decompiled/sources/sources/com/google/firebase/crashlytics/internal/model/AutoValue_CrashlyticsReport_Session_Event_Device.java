package com.google.firebase.crashlytics.internal.model;

import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_Device extends CrashlyticsReport.Session.Event.Device {
    private final Double batteryLevel;
    private final int batteryVelocity;
    private final long diskUsed;
    private final int orientation;
    private final boolean proximityOn;
    private final long ramUsed;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Device$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.Device.Builder {
        private Double batteryLevel;
        private int batteryVelocity;
        private long diskUsed;
        private int orientation;
        private boolean proximityOn;
        private long ramUsed;
        private byte set$0;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder
        public CrashlyticsReport.Session.Event.Device build() {
            if (this.set$0 == 31) goto L22;
            StringBuilder r0 = new StringBuilder();
            if ((this.set$0 & 1) != 0) goto L8;
            r0.append(" batteryVelocity");
        L8:
            if ((this.set$0 & 2) != 0) goto L11;
            r0.append(" proximityOn");
        L11:
            if ((this.set$0 & 4) != 0) goto L14;
            r0.append(" orientation");
        L14:
            if ((this.set$0 & 8) != 0) goto L17;
            r0.append(" ramUsed");
        L17:
            if ((this.set$0 & 16) != 0) goto L19;
            r0.append(" diskUsed");
        L19:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        L22:
            return new AutoValue_CrashlyticsReport_Session_Event_Device(this.batteryLevel, this.batteryVelocity, this.proximityOn, this.orientation, this.ramUsed, this.diskUsed, null);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder
        public CrashlyticsReport.Session.Event.Device.Builder setBatteryLevel(Double r1) {
            this.batteryLevel = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder
        public CrashlyticsReport.Session.Event.Device.Builder setBatteryVelocity(int r1) {
            this.batteryVelocity = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder
        public CrashlyticsReport.Session.Event.Device.Builder setDiskUsed(long r1) {
            this.diskUsed = r1;
            this.set$0 = (byte) (this.set$0 | 16);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder
        public CrashlyticsReport.Session.Event.Device.Builder setOrientation(int r1) {
            this.orientation = r1;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder
        public CrashlyticsReport.Session.Event.Device.Builder setProximityOn(boolean r1) {
            this.proximityOn = r1;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder
        public CrashlyticsReport.Session.Event.Device.Builder setRamUsed(long r1) {
            this.ramUsed = r1;
            this.set$0 = (byte) (this.set$0 | 8);
            return this;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_Device(Double r1, int r2, boolean r3, int r4, long r5, long r7) {
        this.batteryLevel = r1;
        this.batteryVelocity = r2;
        this.proximityOn = r3;
        this.orientation = r4;
        this.ramUsed = r5;
        this.diskUsed = r7;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Event_Device(Double r1, int r2, boolean r3, int r4, long r5, long r7, AnonymousClass1 r9) {
        this(r1, r2, r3, r4, r5, r7);
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof CrashlyticsReport.Session.Event.Device) == false) goto L25;
        CrashlyticsReport.Session.Event.Device r82 = (CrashlyticsReport.Session.Event.Device) r8;
        Double r1 = this.batteryLevel;
        if (r1 != null) goto L13;
        if (r82.getBatteryLevel() != null) goto L25;
    L15:
        if (this.batteryVelocity != r82.getBatteryVelocity()) goto L25;
        if (this.proximityOn != r82.isProximityOn()) goto L25;
        if (this.orientation != r82.getOrientation()) goto L25;
        if (this.ramUsed != r82.getRamUsed()) goto L25;
        if (this.diskUsed != r82.getDiskUsed()) goto L25;
        return true;
    L13:
        if (r1.equals(r82.getBatteryLevel()) == true) goto L15;
    L25:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device
    public Double getBatteryLevel() {
        return this.batteryLevel;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device
    public int getBatteryVelocity() {
        return this.batteryVelocity;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device
    public long getDiskUsed() {
        return this.diskUsed;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device
    public int getOrientation() {
        return this.orientation;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device
    public long getRamUsed() {
        return this.ramUsed;
    }

    public int hashCode() {
        Double r0 = this.batteryLevel;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = (((r02 ^ 1000003) * 1000003) ^ this.batteryVelocity) * 1000003;
        if (this.proximityOn == false) goto L9;
        int r2 = 1231;
    L10:
        int r04 = (((r03 ^ r2) * 1000003) ^ this.orientation) * 1000003;
        long r22 = this.ramUsed;
        long r1 = this.diskUsed;
        int r7 = (int) (r1 ^ (r1 >>> 32));
        return r7 ^ ((r04 ^ ((int) (r22 ^ (r22 >>> 32)))) * 1000003);
    L9:
        r2 = 1237;
        goto L10
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device
    public boolean isProximityOn() {
        return this.proximityOn;
    }

    public String toString() {
        return "Device{batteryLevel=" + this.batteryLevel + ", batteryVelocity=" + this.batteryVelocity + ", proximityOn=" + this.proximityOn + ", orientation=" + this.orientation + ", ramUsed=" + this.ramUsed + ", diskUsed=" + this.diskUsed + "}";
    }
}
