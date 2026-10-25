package com.google.android.datatransport.cct.internal;

import c.f$$ExternalSyntheticBUOutline2;
import java.util.List;

/* loaded from: classes4.dex */
final class AutoValue_BatchedLogRequest extends BatchedLogRequest {
    private final List<LogRequest> logRequests;

    public AutoValue_BatchedLogRequest(List<LogRequest> r1) {
        if (r1 == null) goto L6;
        this.logRequests = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null logRequests");
        throw null;
    }

    public boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof BatchedLogRequest) == true) goto L8;
        return false;
    L8:
        return this.logRequests.equals(((BatchedLogRequest) r2).getLogRequests());
    }

    @Override // com.google.android.datatransport.cct.internal.BatchedLogRequest
    public List<LogRequest> getLogRequests() {
        return this.logRequests;
    }

    public int hashCode() {
        return this.logRequests.hashCode() ^ 1000003;
    }

    public String toString() {
        return "BatchedLogRequest{logRequests=" + this.logRequests + "}";
    }
}
