package com.exteragram.messenger.export.api;

import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class ApiWrap$MessagesSlice {
    public ArrayList<ApiWrap$Message> list;
    public HashMap<Long, ApiWrap$Peer> peers;

    public ApiWrap$MessagesSlice() {
        this.list = new ArrayList();
    }
}
