package com.exteragram.messenger.api.dto;

import com.exteragram.messenger.api.model.NowPlayingServiceType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/exteragram/messenger/api/dto/NowPlayingInfoDTO;", _UrlKt.FRAGMENT_ENCODE_SET, "serviceType", "Lcom/exteragram/messenger/api/model/NowPlayingServiceType;", "username", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lcom/exteragram/messenger/api/model/NowPlayingServiceType;Ljava/lang/String;)V", "getServiceType", "()Lcom/exteragram/messenger/api/model/NowPlayingServiceType;", "getUsername", "()Ljava/lang/String;", "setUsername", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NowPlayingInfoDTO {
    private final NowPlayingServiceType serviceType;
    private String username;

    public NowPlayingInfoDTO(NowPlayingServiceType r1, String r2) {
        this.serviceType = r1;
        this.username = r2;
    }

    public static /* synthetic */ NowPlayingInfoDTO copy$default(NowPlayingInfoDTO r0, NowPlayingServiceType r1, String r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = r0.serviceType;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = r0.username;
    L9:
        return r0.copy(r1, r2);
    }

    public final NowPlayingServiceType component1() {
        return this.serviceType;
    }

    public final String component2() {
        return this.username;
    }

    public final NowPlayingInfoDTO copy(NowPlayingServiceType r1, String r2) {
        return new NowPlayingInfoDTO(r1, r2);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof NowPlayingInfoDTO) == true) goto L8;
        return false;
    L8:
        NowPlayingInfoDTO r52 = (NowPlayingInfoDTO) r5;
        if (this.serviceType == r52.serviceType) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.username, r52.username) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public final NowPlayingServiceType getServiceType() {
        return this.serviceType;
    }

    public final String getUsername() {
        return this.username;
    }

    public int hashCode() {
        int r0 = this.serviceType.hashCode() * 31;
        String r1 = this.username;
        if (r1 != null) goto L5;
        int r12 = 0;
    L7:
        return r0 + r12;
    L5:
        r12 = r1.hashCode();
        goto L7
    }

    public final void setUsername(String r1) {
        this.username = r1;
    }

    public String toString() {
        return "NowPlayingInfoDTO(serviceType=" + this.serviceType + ", username=" + this.username + ")";
    }
}
