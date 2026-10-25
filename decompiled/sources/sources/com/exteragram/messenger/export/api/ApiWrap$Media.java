package com.exteragram.messenger.export.api;

import com.exteragram.messenger.export.output.html.HtmlWriter;

/* loaded from: classes4.dex */
public class ApiWrap$Media {
    public Object content;
    public int ttl;

    public ApiWrap$Media() {
        this.ttl = 0;
    }

    public ApiWrap$File getFile() {
        Object r1 = this.content;
        if ((r1 instanceof HtmlWriter.Photo) == false) goto L7;
        return ((HtmlWriter.Photo) r1).image.file;
    L7:
        if ((r1 instanceof ApiWrap$Document) == false) goto L11;
        return ((ApiWrap$Document) r1).file;
    L11:
        if ((r1 instanceof ApiWrap$SharedContact) == false) goto L15;
        return ((ApiWrap$SharedContact) r1).vcard;
    L15:
        return new ApiWrap$File();
    }

    public ApiWrap$Image getThumb() {
        Object r1 = this.content;
        if ((r1 instanceof ApiWrap$Document) == false) goto L7;
        return ((ApiWrap$Document) r1).thumb;
    L7:
        return new ApiWrap$Image();
    }
}
