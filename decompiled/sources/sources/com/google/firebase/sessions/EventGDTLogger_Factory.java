package com.google.firebase.sessions;

import com.google.android.datatransport.TransportFactory;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Provider;

/* loaded from: classes.dex */
public final class EventGDTLogger_Factory implements Factory<EventGDTLogger> {
    private final Provider<com.google.firebase.inject.Provider<TransportFactory>> transportFactoryProvider;

    private EventGDTLogger_Factory(Provider<com.google.firebase.inject.Provider<TransportFactory>> r1) {
        this.transportFactoryProvider = r1;
    }

    public static EventGDTLogger_Factory create(Provider<com.google.firebase.inject.Provider<TransportFactory>> r1) {
        return new EventGDTLogger_Factory(r1);
    }

    public static EventGDTLogger newInstance(com.google.firebase.inject.Provider<TransportFactory> r1) {
        return new EventGDTLogger(r1);
    }

    @Override // javax.inject.Provider
    public EventGDTLogger get() {
        return newInstance(this.transportFactoryProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
