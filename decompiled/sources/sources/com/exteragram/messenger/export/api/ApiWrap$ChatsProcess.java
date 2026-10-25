package com.exteragram.messenger.export.api;

import java.util.HashMap;
import java.util.Map;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public abstract class ApiWrap$ChatsProcess {
    public Utilities.Callback<ApiWrap$DialogsInfo> done;
    public Map<Long, Integer> indexByPeer;
    public ApiWrap$DialogsInfo info;
    public int processedCount;
    public Utilities.CallbackReturn<Integer, Boolean> progress;

    public ApiWrap$ChatsProcess() {
        this.info = new ApiWrap$DialogsInfo();
        this.processedCount = 0;
        this.indexByPeer = new HashMap();
    }
}
