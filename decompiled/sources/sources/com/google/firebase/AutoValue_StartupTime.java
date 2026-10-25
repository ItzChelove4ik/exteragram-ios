package com.google.firebase;

/* loaded from: classes.dex */
final class AutoValue_StartupTime extends StartupTime {
    private final long elapsedRealtime;
    private final long epochMillis;
    private final long uptimeMillis;

    public AutoValue_StartupTime(long r1, long r3, long r5) {
        this.epochMillis = r1;
        this.elapsedRealtime = r3;
        this.uptimeMillis = r5;
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof StartupTime) == false) goto L14;
        StartupTime r82 = (StartupTime) r8;
        if (this.epochMillis != r82.getEpochMillis()) goto L14;
        if (this.elapsedRealtime != r82.getElapsedRealtime()) goto L14;
        if (this.uptimeMillis != r82.getUptimeMillis()) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // com.google.firebase.StartupTime
    public long getElapsedRealtime() {
        return this.elapsedRealtime;
    }

    @Override // com.google.firebase.StartupTime
    public long getEpochMillis() {
        return this.epochMillis;
    }

    @Override // com.google.firebase.StartupTime
    public long getUptimeMillis() {
        return this.uptimeMillis;
    }

    public int hashCode() {
        long r0 = this.epochMillis;
        int r02 = (((int) (r0 ^ (r0 >>> 32))) ^ 1000003) * 1000003;
        long r3 = this.elapsedRealtime;
        int r03 = (r02 ^ ((int) (r3 ^ (r3 >>> 32)))) * 1000003;
        long r32 = this.uptimeMillis;
        return ((int) ((r32 >>> 32) ^ r32)) ^ r03;
    }

    public String toString() {
        return "StartupTime{epochMillis=" + this.epochMillis + ", elapsedRealtime=" + this.elapsedRealtime + ", uptimeMillis=" + this.uptimeMillis + "}";
    }
}
