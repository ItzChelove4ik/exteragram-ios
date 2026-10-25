package com.exteragram.messenger.export.api;

import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import org.telegram.messenger.NotificationBadge;

/* loaded from: classes4.dex */
public final class ApiWrap$UserpicsInfo extends RecordTag {
    private final int count;

    private /* synthetic */ boolean $record$equals(Object r2) {
        if ((r2 instanceof ApiWrap$UserpicsInfo) == true) goto L5;
        return false;
    L5:
        if (this.count != ((ApiWrap$UserpicsInfo) r2).count) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ Object[] $record$getFieldsAsObjects() {
        return new Object[]{Integer.valueOf(this.count)};
    }

    public ApiWrap$UserpicsInfo(int r1) {
        this.count = r1;
    }

    public int count() {
        return this.count;
    }

    public final boolean equals(Object r1) {
        return $record$equals(r1);
    }

    public final int hashCode() {
        return ApiWrap$UserpicsInfo$$ExternalSyntheticRecord0.m(this.count);
    }

    public final String toString() {
        return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), ApiWrap$UserpicsInfo.class, NotificationBadge.NewHtcHomeBadger.COUNT);
    }
}
