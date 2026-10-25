package com.exteragram.messenger.export.api;

import java.util.ArrayList;

/* loaded from: classes4.dex */
public class ApiWrap$Story {
    public ArrayList<ApiWrap$TextPart> caption;
    public int date;
    public int expires;
    public int id;
    public ApiWrap$Media media;
    public boolean pinned;

    public ApiWrap$Story() {
        this.id = 0;
        this.date = 0;
        this.expires = 0;
        this.pinned = false;
    }

    public ApiWrap$File file() {
        return this.media.getFile();
    }

    public ApiWrap$Image thumb() {
        return this.media.getThumb();
    }
}
