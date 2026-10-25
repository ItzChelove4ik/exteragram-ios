package com.exteragram.messenger.badges.source;

import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.api.model.ProfileStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000f\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ0\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0013\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lcom/exteragram/messenger/badges/source/BadgeInfo;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/api/dto/BadgeDTO;", "badge", "Lcom/exteragram/messenger/api/model/ProfileStatus;", "status", _UrlKt.FRAGMENT_ENCODE_SET, "canChangeBadge", "<init>", "(Lcom/exteragram/messenger/api/dto/BadgeDTO;Lcom/exteragram/messenger/api/model/ProfileStatus;Z)V", "copy", "(Lcom/exteragram/messenger/api/dto/BadgeDTO;Lcom/exteragram/messenger/api/model/ProfileStatus;Z)Lcom/exteragram/messenger/badges/source/BadgeInfo;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Lcom/exteragram/messenger/api/dto/BadgeDTO;", "getBadge", "()Lcom/exteragram/messenger/api/dto/BadgeDTO;", "Lcom/exteragram/messenger/api/model/ProfileStatus;", "getStatus", "()Lcom/exteragram/messenger/api/model/ProfileStatus;", "Z", "getCanChangeBadge", "()Z", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
final class BadgeInfo {
    private final BadgeDTO badge;
    private final boolean canChangeBadge;
    private final ProfileStatus status;

    public BadgeInfo(BadgeDTO r1, ProfileStatus r2, boolean r3) {
        this.badge = r1;
        this.status = r2;
        this.canChangeBadge = r3;
    }

    public static /* synthetic */ BadgeInfo copy$default(BadgeInfo r0, BadgeDTO r1, ProfileStatus r2, boolean r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = r0.badge;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = r0.status;
    L9:
        if ((r4 & 4) == 0) goto L12;
        r3 = r0.canChangeBadge;
    L12:
        return r0.copy(r1, r2, r3);
    }

    public final BadgeInfo copy(BadgeDTO r1, ProfileStatus r2, boolean r3) {
        return new BadgeInfo(r1, r2, r3);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof BadgeInfo) == true) goto L8;
        return false;
    L8:
        BadgeInfo r52 = (BadgeInfo) r5;
        if (Intrinsics.areEqual(this.badge, r52.badge) == true) goto L12;
        return false;
    L12:
        if (this.status == r52.status) goto L15;
        return false;
    L15:
        if (this.canChangeBadge == r52.canChangeBadge) goto L17;
        return false;
    L17:
        return true;
    }

    public final BadgeDTO getBadge() {
        return this.badge;
    }

    public final boolean getCanChangeBadge() {
        return this.canChangeBadge;
    }

    public final ProfileStatus getStatus() {
        return this.status;
    }

    public int hashCode() {
        BadgeDTO r0 = this.badge;
        if (r0 != null) goto L5;
        int r02 = 0;
    L7:
        return (((r02 * 31) + this.status.hashCode()) * 31) + Boolean.hashCode(this.canChangeBadge);
    L5:
        r02 = r0.hashCode();
        goto L7
    }

    public String toString() {
        return "BadgeInfo(badge=" + this.badge + ", status=" + this.status + ", canChangeBadge=" + this.canChangeBadge + ")";
    }
}
