package org.mvel2.jsr223;

import c.f$$ExternalSyntheticBUOutline2;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.script.Bindings;

/* loaded from: classes5.dex */
public class MvelBindings implements Bindings {
    private Map<String, Object> map;

    public MvelBindings() {
        this(new HashMap());
    }

    public MvelBindings(Map<String, Object> r1) {
        r1.getClass();
        this.map = r1;
    }

    public void clear() {
        this.map.clear();
    }

    public boolean containsKey(Object r1) {
        return this.map.containsKey(r1);
    }

    public boolean containsValue(Object r1) {
        return this.map.containsValue(r1);
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    public Object get(Object r1) {
        return this.map.get(r1);
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    public /* bridge */ /* synthetic */ Object put(Object r1, Object r2) {
        return put((String) r1, r2);
    }

    public Object put(String r1, Object r2) {
        return this.map.put(r1, r2);
    }

    public void putAll(Map<? extends String, ? extends Object> r3) {
        if (r3 == null) goto L8;
        Iterator<Map.Entry<? extends String, ? extends Object>> r32 = r3.entrySet().iterator();
    L5:
        if (r32.hasNext() == false) goto L7;
        Map.Entry<? extends String, ? extends Object> r0 = r32.next();
        put(r0.getKey(), r0.getValue());
        goto L5
    L7:
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("toMerge map is null");
    }

    public Object remove(Object r1) {
        return this.map.remove(r1);
    }

    public int size() {
        return this.map.size();
    }

    public Collection<Object> values() {
        return this.map.values();
    }
}
