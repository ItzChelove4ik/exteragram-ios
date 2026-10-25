package com.exteragram.messenger.proxy;

import com.exteragram.messenger.proxy.IpAddressInfoController;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010'\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u001e\u0010\u0004\u001a\u00020\u00052\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007H\u0014¨\u0006\b"}, d2 = {"com/exteragram/messenger/proxy/IpAddressInfoController$cache$1", "Ljava/util/LinkedHashMap;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/proxy/IpAddressInfoController$CacheEntry;", "removeEldestEntry", _UrlKt.FRAGMENT_ENCODE_SET, "eldest", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class IpAddressInfoController$cache$1 extends LinkedHashMap<String, IpAddressInfoController.CacheEntry> {
    public IpAddressInfoController$cache$1() {
        super(100, 0.75f, true);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsKey(Object r2) {
        if ((r2 instanceof String) == true) goto L7;
        return false;
    L7:
        return containsKey((String) r2);
    }

    public /* bridge */ boolean containsKey(String r1) {
        return super.containsKey(r1);
    }

    public /* bridge */ boolean containsValue(IpAddressInfoController.CacheEntry r1) {
        return super.containsValue(r1);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsValue(Object r2) {
        if ((r2 instanceof IpAddressInfoController.CacheEntry) == true) goto L7;
        return false;
    L7:
        return containsValue((IpAddressInfoController.CacheEntry) r2);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<Map.Entry<String, IpAddressInfoController.CacheEntry>> entrySet() {
        return getEntries();
    }

    public /* bridge */ IpAddressInfoController.CacheEntry get(String r1) {
        return (IpAddressInfoController.CacheEntry) super.get(r1);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object get(Object r2) {
        if ((r2 instanceof String) == true) goto L7;
        return null;
    L7:
        return get((String) r2);
    }

    public /* bridge */ Set<Map.Entry<String, IpAddressInfoController.CacheEntry>> getEntries() {
        return super.entrySet();
    }

    public /* bridge */ Set<String> getKeys() {
        return super.keySet();
    }

    public /* bridge */ IpAddressInfoController.CacheEntry getOrDefault(String r1, IpAddressInfoController.CacheEntry r2) {
        return (IpAddressInfoController.CacheEntry) super.getOrDefault(r1, r2);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object getOrDefault(Object r2, Object r3) {
        if ((r2 instanceof String) == true) goto L6;
        return r3;
    L6:
        return getOrDefault((String) r2, (IpAddressInfoController.CacheEntry) r3);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ Collection<IpAddressInfoController.CacheEntry> getValues() {
        return super.values();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<String> keySet() {
        return getKeys();
    }

    public /* bridge */ IpAddressInfoController.CacheEntry remove(String r1) {
        return (IpAddressInfoController.CacheEntry) super.remove(r1);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object remove(Object r2) {
        if ((r2 instanceof String) == true) goto L7;
        return null;
    L7:
        return remove((String) r2);
    }

    @Override // java.util.HashMap, java.util.Map
    public final /* bridge */ boolean remove(Object r3, Object r4) {
        if ((r3 instanceof String) == true) goto L6;
        return false;
    L6:
        if ((r4 instanceof IpAddressInfoController.CacheEntry) == true) goto L9;
        return false;
    L9:
        return remove((String) r3, (IpAddressInfoController.CacheEntry) r4);
    }

    public /* bridge */ boolean remove(String r1, IpAddressInfoController.CacheEntry r2) {
        return super.remove(r1, r2);
    }

    @Override // java.util.LinkedHashMap
    public boolean removeEldestEntry(Map.Entry<String, IpAddressInfoController.CacheEntry> r1) {
        if (size() <= 100) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Collection<IpAddressInfoController.CacheEntry> values() {
        return getValues();
    }
}
