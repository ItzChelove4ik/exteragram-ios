package com.exteragram.messenger.export.api;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class ApiWrap$MediaData {
    public String classes;
    public String description;
    public String link;
    public String status;
    public String thumb;
    public String title;

    public ApiWrap$MediaData() {
        this.title = _UrlKt.FRAGMENT_ENCODE_SET;
        this.description = _UrlKt.FRAGMENT_ENCODE_SET;
        this.status = _UrlKt.FRAGMENT_ENCODE_SET;
        this.classes = _UrlKt.FRAGMENT_ENCODE_SET;
        this.thumb = _UrlKt.FRAGMENT_ENCODE_SET;
        this.link = _UrlKt.FRAGMENT_ENCODE_SET;
    }
}
