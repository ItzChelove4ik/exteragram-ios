package com.exteragram.messenger.export.api;

import java.util.ArrayList;

/* loaded from: classes4.dex */
public class ApiWrap$StoriesSlice {
    public int lastId;
    public ArrayList<ApiWrap$Story> list;
    public int skipped;

    public ApiWrap$StoriesSlice() {
        this.list = new ArrayList();
        this.lastId = 0;
        this.skipped = 0;
    }
}
