package com.exteragram.messenger.export.api;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class ApiWrap$ExportPersonalInfo {
    public String bio;
    public ApiWrap$User user;

    public ApiWrap$ExportPersonalInfo() {
        this.bio = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public ApiWrap$ExportPersonalInfo(ApiWrap$ExportPersonalInfo r2) {
        this.bio = _UrlKt.FRAGMENT_ENCODE_SET;
        this.user = r2.user;
        this.bio = r2.bio;
    }
}
