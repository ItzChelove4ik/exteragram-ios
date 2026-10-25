package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage extends CrashlyticsReport.Session.Event.Application.Execution.BinaryImage {
    private final long baseAddress;
    private final String name;
    private final long size;
    private final String uuid;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder {
        private long baseAddress;
        private String name;
        private byte set$0;
        private long size;
        private String uuid;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.BinaryImage build() {
            if (this.set$0 != 3) goto L9;
            String r7 = this.name;
            if (r7 == null) goto L9;
            return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage(this.baseAddress, this.size, r7, this.uuid, null);
        L9:
            StringBuilder r0 = new StringBuilder();
            if ((this.set$0 & 1) != 0) goto L13;
            r0.append(" baseAddress");
        L13:
            if ((this.set$0 & 2) != 0) goto L16;
            r0.append(" size");
        L16:
            if (this.name != null) goto L18;
            r0.append(" name");
        L18:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setBaseAddress(long r1) {
            this.baseAddress = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setName(String r1) {
            if (r1 == null) goto L5;
            this.name = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null name");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setSize(long r1) {
            this.size = r1;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder setUuid(String r1) {
            this.uuid = r1;
            return this;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage(long r1, long r3, String r5, String r6) {
        this.baseAddress = r1;
        this.size = r3;
        this.name = r5;
        this.uuid = r6;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage(long r1, long r3, String r5, String r6, AnonymousClass1 r7) {
        this(r1, r3, r5, r6);
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof CrashlyticsReport.Session.Event.Application.Execution.BinaryImage) == false) goto L21;
        CrashlyticsReport.Session.Event.Application.Execution.BinaryImage r82 = (CrashlyticsReport.Session.Event.Application.Execution.BinaryImage) r8;
        if (this.baseAddress != r82.getBaseAddress()) goto L21;
        if (this.size != r82.getSize()) goto L21;
        if (this.name.equals(r82.getName()) == false) goto L21;
        String r7 = this.uuid;
        if (r7 != null) goto L19;
        if (r82.getUuid() != null) goto L21;
    L20:
        return true;
    L19:
        if (r7.equals(r82.getUuid()) == true) goto L20;
    L21:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage
    public long getBaseAddress() {
        return this.baseAddress;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage
    public String getName() {
        return this.name;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage
    public long getSize() {
        return this.size;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.BinaryImage
    public String getUuid() {
        return this.uuid;
    }

    public int hashCode() {
        long r0 = this.baseAddress;
        int r02 = (((int) (r0 ^ (r0 >>> 32))) ^ 1000003) * 1000003;
        long r3 = this.size;
        int r03 = (((r02 ^ ((int) ((r3 >>> 32) ^ r3))) * 1000003) ^ this.name.hashCode()) * 1000003;
        String r7 = this.uuid;
        if (r7 != null) goto L5;
        int r72 = 0;
    L7:
        return r72 ^ r03;
    L5:
        r72 = r7.hashCode();
        goto L7
    }

    public String toString() {
        return "BinaryImage{baseAddress=" + this.baseAddress + ", size=" + this.size + ", name=" + this.name + ", uuid=" + this.uuid + "}";
    }
}
