package com.google.gson;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public final class JsonObject extends JsonElement {
    private final LinkedTreeMap<String, JsonElement> members;

    public JsonObject() {
        this.members = new LinkedTreeMap(false);
    }

    public void add(String r1, JsonElement r2) {
        LinkedTreeMap<String, JsonElement> r0 = this.members;
        if (r2 != null) goto L5;
        r2 = JsonNull.INSTANCE;
    L5:
        r0.put(r1, r2);
    }

    public void addProperty(String r2, String r3) {
        if (r3 != null) goto L4;
        JsonElement r32 = JsonNull.INSTANCE;
    L5:
        add(r2, r32);
        return;
    L4:
        r32 = new JsonPrimitive(r3);
        goto L5
    }

    public Set<Map.Entry<String, JsonElement>> entrySet() {
        return this.members.entrySet();
    }

    public boolean equals(Object r2) {
        if (r2 != this) goto L4;
        return true;
    L4:
        if ((r2 instanceof JsonObject) == true) goto L6;
        return false;
    L6:
        if (((JsonObject) r2).members.equals(this.members) == true) goto L13;
        return false;
    L13:
        return true;
    }

    public JsonElement get(String r1) {
        return this.members.get(r1);
    }

    public JsonArray getAsJsonArray(String r1) {
        return (JsonArray) this.members.get(r1);
    }

    public JsonObject getAsJsonObject(String r1) {
        return (JsonObject) this.members.get(r1);
    }

    public boolean has(String r1) {
        return this.members.containsKey(r1);
    }

    public int hashCode() {
        return this.members.hashCode();
    }

    public boolean isEmpty() {
        return this.members.isEmpty();
    }

    public Set<String> keySet() {
        return this.members.keySet();
    }

    public JsonElement remove(String r1) {
        return this.members.remove(r1);
    }
}
