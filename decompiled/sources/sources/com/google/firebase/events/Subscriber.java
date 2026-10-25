package com.google.firebase.events;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public interface Subscriber {
    <T> void subscribe(Class<T> r1, EventHandler<? super T> r2);

    <T> void subscribe(Class<T> r1, Executor r2, EventHandler<? super T> r3);
}
