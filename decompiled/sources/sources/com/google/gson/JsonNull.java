package com.google.gson;

/* loaded from: classes5.dex */
public final class JsonNull extends JsonElement {
    public static final JsonNull INSTANCE = null;

    static {
        INSTANCE = new JsonNull();
    }

    @Deprecated
    public JsonNull() {
    }

    public boolean equals(Object r1) {
        return r1 instanceof JsonNull;
    }

    public int hashCode() {
        return JsonNull.class.hashCode();
    }
}
