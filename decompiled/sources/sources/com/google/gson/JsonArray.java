package com.google.gson;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public final class JsonArray extends JsonElement implements Iterable<JsonElement> {
    private final ArrayList<JsonElement> elements;

    public JsonArray() {
        this.elements = new ArrayList();
    }

    private JsonElement getAsSingleElement() {
        int r0 = this.elements.size();
        if (r0 == 1) goto L5;
        JsonArray$$ExternalSyntheticBUOutline0.m("Array must have size 1, but has size ", r0);
        return null;
    L5:
        return this.elements.get(0);
    }

    public void add(JsonElement r1) {
        if (r1 != null) goto L4;
        r1 = JsonNull.INSTANCE;
    L4:
        this.elements.add(r1);
    }

    public boolean equals(Object r2) {
        if (r2 != this) goto L4;
        return true;
    L4:
        if ((r2 instanceof JsonArray) == true) goto L6;
        return false;
    L6:
        if (((JsonArray) r2).elements.equals(this.elements) == true) goto L13;
        return false;
    L13:
        return true;
    }

    @Override // com.google.gson.JsonElement
    public boolean getAsBoolean() {
        return getAsSingleElement().getAsBoolean();
    }

    @Override // com.google.gson.JsonElement
    public float getAsFloat() {
        return getAsSingleElement().getAsFloat();
    }

    @Override // com.google.gson.JsonElement
    public int getAsInt() {
        return getAsSingleElement().getAsInt();
    }

    @Override // com.google.gson.JsonElement
    public long getAsLong() {
        return getAsSingleElement().getAsLong();
    }

    @Override // com.google.gson.JsonElement
    public String getAsString() {
        return getAsSingleElement().getAsString();
    }

    public int hashCode() {
        return this.elements.hashCode();
    }

    @Override // java.lang.Iterable
    public Iterator<JsonElement> iterator() {
        return this.elements.iterator();
    }

    public int size() {
        return this.elements.size();
    }
}
