package com.exteragram.messenger.export.api;

import java.util.Deque;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/* loaded from: classes4.dex */
public class ApiWrap$LoadedFileCache {
    private final int _limit;
    private final Deque<String> _list;
    private final HashMap<String, String> _map;

    public ApiWrap$LoadedFileCache(int r1) {
        this._limit = r1;
        this._map = new HashMap();
        this._list = new ConcurrentLinkedDeque();
    }

    public String find(ApiWrap$FileLocation r1) {
        if (r1 != null) goto L5;
        return null;
    L5:
        String r12 = DataTypesUtils.ComputeLocationKey(r1);
        return this._map.get(r12);
    }

    public void save(ApiWrap$FileLocation r2, String r3) {
        if (r2 == null) goto L9;
        String r22 = DataTypesUtils.ComputeLocationKey(r2);
        this._map.put(r22, r3);
        this._list.add(r22);
        if (this._list.size() <= this._limit) goto L8;
        String r23 = this._list.getFirst();
        this._list.removeFirst();
        this._map.remove(r23);
        return;
    L8:
        return;
    }
}
