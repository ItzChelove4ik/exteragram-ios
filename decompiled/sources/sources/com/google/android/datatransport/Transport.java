package com.google.android.datatransport;

/* loaded from: classes.dex */
public interface Transport<T> {
    void schedule(Event<T> r1, TransportScheduleCallback r2);

    void send(Event<T> r1);
}
