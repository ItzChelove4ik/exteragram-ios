package com.exteragram.messenger.export.api;

import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;

/* loaded from: classes4.dex */
public final class ApiWrap$FileProgress extends RecordTag {
    private final long ready;
    private final long total;

    private /* synthetic */ boolean $record$equals(Object r5) {
        if ((r5 instanceof ApiWrap$FileProgress) == false) goto L10;
        ApiWrap$FileProgress r52 = (ApiWrap$FileProgress) r5;
        if (this.ready == r52.ready) goto L7;
        return false;
    L7:
        if (this.total != r52.total) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    private /* synthetic */ Object[] $record$getFieldsAsObjects() {
        return new Object[]{Long.valueOf(this.ready), Long.valueOf(this.total)};
    }

    public ApiWrap$FileProgress(long r1, long r3) {
        this.ready = r1;
        this.total = r3;
    }

    public final boolean equals(Object r1) {
        return $record$equals(r1);
    }

    public final int hashCode() {
        return ApiWrap$FileProgress$$ExternalSyntheticRecord0.m(this.ready, this.total);
    }

    public long ready() {
        return this.ready;
    }

    public final String toString() {
        return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), ApiWrap$FileProgress.class, "ready;total");
    }

    public long total() {
        return this.total;
    }
}
