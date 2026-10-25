package com.google.android.datatransport;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
final class AutoValue_Event<T> extends Event<T> {
    private final Integer code;
    private final T payload;
    private final Priority priority;
    private final ProductData productData;

    public AutoValue_Event(Integer r1, T r2, Priority r3, ProductData r4, EventContext r5) {
        this.code = r1;
        if (r2 == null) goto L10;
        this.payload = r2;
        if (r3 == null) goto L8;
        this.priority = r3;
        this.productData = r4;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null priority");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null payload");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof Event) == false) goto L27;
        Event r52 = (Event) r5;
        Integer r1 = this.code;
        if (r1 != null) goto L13;
        if (r52.getCode() != null) goto L27;
    L15:
        if (this.payload.equals(r52.getPayload()) == false) goto L27;
        if (this.priority.equals(r52.getPriority()) == false) goto L27;
        ProductData r4 = this.productData;
        if (r4 != null) goto L24;
        if (r52.getProductData() != null) goto L27;
    L25:
        r52.getEventContext();
        return true;
    L24:
        if (r4.equals(r52.getProductData()) == false) goto L27;
    L13:
        if (r1.equals(r52.getCode()) == true) goto L15;
    L27:
        return false;
    }

    @Override // com.google.android.datatransport.Event
    public Integer getCode() {
        return this.code;
    }

    @Override // com.google.android.datatransport.Event
    public EventContext getEventContext() {
        return null;
    }

    @Override // com.google.android.datatransport.Event
    public T getPayload() {
        return this.payload;
    }

    @Override // com.google.android.datatransport.Event
    public Priority getPriority() {
        return this.priority;
    }

    @Override // com.google.android.datatransport.Event
    public ProductData getProductData() {
        return this.productData;
    }

    public int hashCode() {
        Integer r0 = this.code;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = (((((r02 ^ 1000003) * 1000003) ^ this.payload.hashCode()) * 1000003) ^ this.priority.hashCode()) * 1000003;
        ProductData r4 = this.productData;
        if (r4 == null) goto L11;
        r1 = r4.hashCode();
    L11:
        return (r03 ^ r1) * 1000003;
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public String toString() {
        return "Event{code=" + this.code + ", payload=" + this.payload + ", priority=" + this.priority + ", productData=" + this.productData + ", eventContext=null}";
    }
}
