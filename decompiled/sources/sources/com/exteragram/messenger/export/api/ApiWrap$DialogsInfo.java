package com.exteragram.messenger.export.api;

import java.util.ArrayList;

/* loaded from: classes4.dex */
public class ApiWrap$DialogsInfo {
    public ArrayList<ApiWrap$DialogInfo> chats;
    public ArrayList<ApiWrap$DialogInfo> left;

    public ApiWrap$DialogsInfo() {
        this.chats = new ArrayList();
        this.left = new ArrayList();
    }

    public ApiWrap$DialogInfo getItemAt(int r3) {
        if (r3 >= 0) goto L5;
        return null;
    L5:
        int r1 = this.chats.size();
        if (r3 < r1) goto L8;
        int r32 = r3 - r1;
        if (r32 < this.left.size()) goto L12;
        return null;
    L12:
        return this.left.get(r32);
    L8:
        return this.chats.get(r3);
    }
}
