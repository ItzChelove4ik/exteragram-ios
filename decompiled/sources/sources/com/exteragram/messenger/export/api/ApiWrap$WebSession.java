package com.exteragram.messenger.export.api;

import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class ApiWrap$WebSession extends RecordTag {
    private final String botUsername;
    private final String browser;
    private final int created;
    private final String domain;
    private final String ip;
    private final int lastActive;
    private final String platform;
    private final String region;

    private /* synthetic */ boolean $record$equals(Object r3) {
        if ((r3 instanceof ApiWrap$WebSession) == false) goto L22;
        ApiWrap$WebSession r32 = (ApiWrap$WebSession) r3;
        if (this.created == r32.created) goto L7;
        return false;
    L7:
        if (this.lastActive == r32.lastActive) goto L9;
        return false;
    L9:
        if (Objects.equals(this.botUsername, r32.botUsername) == true) goto L11;
        return false;
    L11:
        if (Objects.equals(this.domain, r32.domain) == true) goto L13;
        return false;
    L13:
        if (Objects.equals(this.browser, r32.browser) == true) goto L15;
        return false;
    L15:
        if (Objects.equals(this.platform, r32.platform) == true) goto L17;
        return false;
    L17:
        if (Objects.equals(this.ip, r32.ip) == true) goto L19;
        return false;
    L19:
        if (Objects.equals(this.region, r32.region) == false) goto L31;
        return true;
    L31:
        return false;
    L22:
        return false;
    }

    private /* synthetic */ Object[] $record$getFieldsAsObjects() {
        return new Object[]{this.botUsername, this.domain, this.browser, this.platform, Integer.valueOf(this.created), Integer.valueOf(this.lastActive), this.ip, this.region};
    }

    public ApiWrap$WebSession(String r1, String r2, String r3, String r4, int r5, int r6, String r7, String r8) {
        this.botUsername = r1;
        this.domain = r2;
        this.browser = r3;
        this.platform = r4;
        this.created = r5;
        this.lastActive = r6;
        this.ip = r7;
        this.region = r8;
    }

    public String botUsername() {
        return this.botUsername;
    }

    public String browser() {
        return this.browser;
    }

    public int created() {
        return this.created;
    }

    public String domain() {
        return this.domain;
    }

    public final boolean equals(Object r1) {
        return $record$equals(r1);
    }

    public final int hashCode() {
        return ApiWrap$WebSession$$ExternalSyntheticRecord0.m(this.created, this.lastActive, this.botUsername, this.domain, this.browser, this.platform, this.ip, this.region);
    }

    public String ip() {
        return this.ip;
    }

    public int lastActive() {
        return this.lastActive;
    }

    public String platform() {
        return this.platform;
    }

    public String region() {
        return this.region;
    }

    public final String toString() {
        return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), ApiWrap$WebSession.class, "botUsername;domain;browser;platform;created;lastActive;ip;region");
    }
}
