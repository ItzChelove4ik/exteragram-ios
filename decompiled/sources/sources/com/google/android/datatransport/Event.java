package com.google.android.datatransport;

/* loaded from: classes4.dex */
public abstract class Event<T> {
    public Event() {
    }

    public static <T> Event<T> ofData(int r6, T r7) {
        return new AutoValue_Event(Integer.valueOf(r6), r7, Priority.DEFAULT, null, null);
    }

    public static <T> Event<T> ofData(T r6) {
        return new AutoValue_Event(null, r6, Priority.DEFAULT, null, null);
    }

    public static <T> Event<T> ofData(T r6, ProductData r7) {
        return new AutoValue_Event(null, r6, Priority.DEFAULT, r7, null);
    }

    public static <T> Event<T> ofTelemetry(int r6, T r7) {
        return new AutoValue_Event(Integer.valueOf(r6), r7, Priority.VERY_LOW, null, null);
    }

    public static <T> Event<T> ofTelemetry(T r6) {
        return new AutoValue_Event(null, r6, Priority.VERY_LOW, null, null);
    }

    public static <T> Event<T> ofUrgent(T r6) {
        return new AutoValue_Event(null, r6, Priority.HIGHEST, null, null);
    }

    public abstract Integer getCode();

    public abstract EventContext getEventContext();

    public abstract T getPayload();

    public abstract Priority getPriority();

    public abstract ProductData getProductData();
}
