package com.google.firebase.heartbeatinfo;

import c.f$$ExternalSyntheticBUOutline2;
import java.util.List;

/* loaded from: classes5.dex */
final class AutoValue_HeartBeatResult extends HeartBeatResult {
    private final List<String> usedDates;
    private final String userAgent;

    public AutoValue_HeartBeatResult(String r2, List<String> r3) {
        if (r2 == null) goto L10;
        this.userAgent = r2;
        if (r3 == null) goto L8;
        this.usedDates = r3;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null usedDates");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null userAgent");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof HeartBeatResult) == false) goto L12;
        HeartBeatResult r52 = (HeartBeatResult) r5;
        if (this.userAgent.equals(r52.getUserAgent()) == false) goto L12;
        if (this.usedDates.equals(r52.getUsedDates()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatResult
    public List<String> getUsedDates() {
        return this.usedDates;
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatResult
    public String getUserAgent() {
        return this.userAgent;
    }

    public int hashCode() {
        int r0 = (this.userAgent.hashCode() ^ 1000003) * 1000003;
        return this.usedDates.hashCode() ^ r0;
    }

    public String toString() {
        return "HeartBeatResult{userAgent=" + this.userAgent + ", usedDates=" + this.usedDates + "}";
    }
}
