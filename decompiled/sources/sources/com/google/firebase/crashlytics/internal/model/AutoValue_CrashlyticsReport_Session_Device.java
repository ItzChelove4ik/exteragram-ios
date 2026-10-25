package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Device extends CrashlyticsReport.Session.Device {
    private final int arch;
    private final int cores;
    private final long diskSpace;
    private final String manufacturer;
    private final String model;
    private final String modelClass;
    private final long ram;
    private final boolean simulator;
    private final int state;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Device$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends CrashlyticsReport.Session.Device.Builder {
        private int arch;
        private int cores;
        private long diskSpace;
        private String manufacturer;
        private String model;
        private String modelClass;
        private long ram;
        private byte set$0;
        private boolean simulator;
        private int state;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder
        public CrashlyticsReport.Session.Device build() {
            if (this.set$0 != 63) goto L13;
            String r4 = this.model;
            if (r4 == null) goto L13;
            String r12 = this.manufacturer;
            if (r12 == null) goto L13;
            String r13 = this.modelClass;
            if (r13 == null) goto L13;
            return new AutoValue_CrashlyticsReport_Session_Device(this.arch, r4, this.cores, this.ram, this.diskSpace, this.simulator, this.state, r12, r13, null);
        L13:
            StringBuilder r0 = new StringBuilder();
            if ((this.set$0 & 1) != 0) goto L17;
            r0.append(" arch");
        L17:
            if (this.model != null) goto L20;
            r0.append(" model");
        L20:
            if ((this.set$0 & 2) != 0) goto L23;
            r0.append(" cores");
        L23:
            if ((this.set$0 & 4) != 0) goto L26;
            r0.append(" ram");
        L26:
            if ((this.set$0 & 8) != 0) goto L29;
            r0.append(" diskSpace");
        L29:
            if ((this.set$0 & 16) != 0) goto L32;
            r0.append(" simulator");
        L32:
            if ((this.set$0 & 32) != 0) goto L35;
            r0.append(" state");
        L35:
            if (this.manufacturer != null) goto L38;
            r0.append(" manufacturer");
        L38:
            if (this.modelClass != null) goto L40;
            r0.append(" modelClass");
        L40:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder
        public CrashlyticsReport.Session.Device.Builder setArch(int r1) {
            this.arch = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder
        public CrashlyticsReport.Session.Device.Builder setCores(int r1) {
            this.cores = r1;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder
        public CrashlyticsReport.Session.Device.Builder setDiskSpace(long r1) {
            this.diskSpace = r1;
            this.set$0 = (byte) (this.set$0 | 8);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder
        public CrashlyticsReport.Session.Device.Builder setManufacturer(String r1) {
            if (r1 == null) goto L5;
            this.manufacturer = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null manufacturer");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder
        public CrashlyticsReport.Session.Device.Builder setModel(String r1) {
            if (r1 == null) goto L5;
            this.model = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null model");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder
        public CrashlyticsReport.Session.Device.Builder setModelClass(String r1) {
            if (r1 == null) goto L5;
            this.modelClass = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null modelClass");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder
        public CrashlyticsReport.Session.Device.Builder setRam(long r1) {
            this.ram = r1;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder
        public CrashlyticsReport.Session.Device.Builder setSimulator(boolean r1) {
            this.simulator = r1;
            this.set$0 = (byte) (this.set$0 | 16);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device.Builder
        public CrashlyticsReport.Session.Device.Builder setState(int r1) {
            this.state = r1;
            this.set$0 = (byte) (this.set$0 | 32);
            return this;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Device(int r1, String r2, int r3, long r4, long r6, boolean r8, int r9, String r10, String r11) {
        this.arch = r1;
        this.model = r2;
        this.cores = r3;
        this.ram = r4;
        this.diskSpace = r6;
        this.simulator = r8;
        this.state = r9;
        this.manufacturer = r10;
        this.modelClass = r11;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Device(int r1, String r2, int r3, long r4, long r6, boolean r8, int r9, String r10, String r11, AnonymousClass1 r12) {
        this(r1, r2, r3, r4, r6, r8, r9, r10, r11);
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof CrashlyticsReport.Session.Device) == false) goto L26;
        CrashlyticsReport.Session.Device r82 = (CrashlyticsReport.Session.Device) r8;
        if (this.arch != r82.getArch()) goto L26;
        if (this.model.equals(r82.getModel()) == false) goto L26;
        if (this.cores != r82.getCores()) goto L26;
        if (this.ram != r82.getRam()) goto L26;
        if (this.diskSpace != r82.getDiskSpace()) goto L26;
        if (this.simulator != r82.isSimulator()) goto L26;
        if (this.state != r82.getState()) goto L26;
        if (this.manufacturer.equals(r82.getManufacturer()) == false) goto L26;
        if (this.modelClass.equals(r82.getModelClass()) == false) goto L26;
        return true;
    L26:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device
    public int getArch() {
        return this.arch;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device
    public int getCores() {
        return this.cores;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device
    public long getDiskSpace() {
        return this.diskSpace;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device
    public String getManufacturer() {
        return this.manufacturer;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device
    public String getModel() {
        return this.model;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device
    public String getModelClass() {
        return this.modelClass;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device
    public long getRam() {
        return this.ram;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device
    public int getState() {
        return this.state;
    }

    public int hashCode() {
        int r0 = (((((this.arch ^ 1000003) * 1000003) ^ this.model.hashCode()) * 1000003) ^ this.cores) * 1000003;
        long r2 = this.ram;
        int r02 = (r0 ^ ((int) (r2 ^ (r2 >>> 32)))) * 1000003;
        long r22 = this.diskSpace;
        int r03 = (r02 ^ ((int) (r22 ^ (r22 >>> 32)))) * 1000003;
        if (this.simulator == false) goto L5;
        int r23 = 1231;
    L6:
        int r04 = (((((r03 ^ r23) * 1000003) ^ this.state) * 1000003) ^ this.manufacturer.hashCode()) * 1000003;
        return this.modelClass.hashCode() ^ r04;
    L5:
        r23 = 1237;
        goto L6
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Device
    public boolean isSimulator() {
        return this.simulator;
    }

    public String toString() {
        return "Device{arch=" + this.arch + ", model=" + this.model + ", cores=" + this.cores + ", ram=" + this.ram + ", diskSpace=" + this.diskSpace + ", simulator=" + this.simulator + ", state=" + this.state + ", manufacturer=" + this.manufacturer + ", modelClass=" + this.modelClass + "}";
    }
}
