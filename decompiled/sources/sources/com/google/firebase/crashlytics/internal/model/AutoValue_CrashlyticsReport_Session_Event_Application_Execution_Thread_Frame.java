package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame extends CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame {
    private final String file;
    private final int importance;
    private final long offset;
    private final long pc;
    private final String symbol;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder {
        private String file;
        private int importance;
        private long offset;
        private long pc;
        private byte set$0;
        private String symbol;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame build() {
            if (this.set$0 != 7) goto L9;
            String r5 = this.symbol;
            if (r5 == null) goto L9;
            return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame(this.pc, r5, this.file, this.offset, this.importance, null);
        L9:
            StringBuilder r0 = new StringBuilder();
            if ((this.set$0 & 1) != 0) goto L13;
            r0.append(" pc");
        L13:
            if (this.symbol != null) goto L16;
            r0.append(" symbol");
        L16:
            if ((this.set$0 & 2) != 0) goto L19;
            r0.append(" offset");
        L19:
            if ((this.set$0 & 4) != 0) goto L21;
            r0.append(" importance");
        L21:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder setFile(String r1) {
            this.file = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder setImportance(int r1) {
            this.importance = r1;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder setOffset(long r1) {
            this.offset = r1;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder setPc(long r1) {
            this.pc = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder setSymbol(String r1) {
            if (r1 == null) goto L5;
            this.symbol = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null symbol");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame(long r1, String r3, String r4, long r5, int r7) {
        this.pc = r1;
        this.symbol = r3;
        this.file = r4;
        this.offset = r5;
        this.importance = r7;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame(long r1, String r3, String r4, long r5, int r7, AnonymousClass1 r8) {
        this(r1, r3, r4, r5, r7);
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame) == false) goto L23;
        CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame r82 = (CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame) r8;
        if (this.pc != r82.getPc()) goto L23;
        if (this.symbol.equals(r82.getSymbol()) == false) goto L23;
        String r1 = this.file;
        if (r1 != null) goto L17;
        if (r82.getFile() != null) goto L23;
    L19:
        if (this.offset != r82.getOffset()) goto L23;
        if (this.importance != r82.getImportance()) goto L23;
        return true;
    L17:
        if (r1.equals(r82.getFile()) == true) goto L19;
    L23:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame
    public String getFile() {
        return this.file;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame
    public int getImportance() {
        return this.importance;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame
    public long getOffset() {
        return this.offset;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame
    public long getPc() {
        return this.pc;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame
    public String getSymbol() {
        return this.symbol;
    }

    public int hashCode() {
        long r0 = this.pc;
        int r02 = (((((int) (r0 ^ (r0 >>> 32))) ^ 1000003) * 1000003) ^ this.symbol.hashCode()) * 1000003;
        String r3 = this.file;
        if (r3 != null) goto L5;
        int r32 = 0;
    L6:
        int r03 = (r02 ^ r32) * 1000003;
        long r33 = this.offset;
        int r7 = this.importance;
        return r7 ^ ((r03 ^ ((int) ((r33 >>> 32) ^ r33))) * 1000003);
    L5:
        r32 = r3.hashCode();
        goto L6
    }

    public String toString() {
        return "Frame{pc=" + this.pc + ", symbol=" + this.symbol + ", file=" + this.file + ", offset=" + this.offset + ", importance=" + this.importance + "}";
    }
}
