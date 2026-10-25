package com.exteragram.messenger.export.api;

import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class ApiWrap$DownloadProgress extends RecordTag {
    private final int itemIndex;
    private final String path;
    private final long randomId;
    private final long ready;
    private final long total;

    private /* synthetic */ boolean $record$equals(Object r5) {
        if ((r5 instanceof ApiWrap$DownloadProgress) == false) goto L16;
        ApiWrap$DownloadProgress r52 = (ApiWrap$DownloadProgress) r5;
        if (this.itemIndex == r52.itemIndex) goto L7;
        return false;
    L7:
        if (this.randomId == r52.randomId) goto L9;
        return false;
    L9:
        if (this.ready == r52.ready) goto L11;
        return false;
    L11:
        if (this.total == r52.total) goto L13;
        return false;
    L13:
        if (Objects.equals(this.path, r52.path) == false) goto L22;
        return true;
    L22:
        return false;
    L16:
        return false;
    }

    private /* synthetic */ Object[] $record$getFieldsAsObjects() {
        return new Object[]{Long.valueOf(this.randomId), this.path, Integer.valueOf(this.itemIndex), Long.valueOf(this.ready), Long.valueOf(this.total)};
    }

    public ApiWrap$DownloadProgress() {
        this(0, _UrlKt.FRAGMENT_ENCODE_SET, 0, 0, 0);
    }

    public ApiWrap$DownloadProgress(long r1, String r3, int r4, long r5, long r7) {
        this.randomId = r1;
        this.path = r3;
        this.itemIndex = r4;
        this.ready = r5;
        this.total = r7;
    }

    public final boolean equals(Object r1) {
        return $record$equals(r1);
    }

    public final int hashCode() {
        return ApiWrap$DownloadProgress$$ExternalSyntheticRecord0.m(this.itemIndex, this.randomId, this.ready, this.total, this.path);
    }

    public int itemIndex() {
        return this.itemIndex;
    }

    public String path() {
        return this.path;
    }

    public long randomId() {
        return this.randomId;
    }

    public long ready() {
        return this.ready;
    }

    public final String toString() {
        return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), ApiWrap$DownloadProgress.class, "randomId;path;itemIndex;ready;total");
    }

    public long total() {
        return this.total;
    }
}
