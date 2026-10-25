package com.google.firebase.components;

import com.google.firebase.events.Publisher;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
final class RestrictedComponentContainer implements ComponentContainer {
    private final Set<Qualified<?>> allowedDeferredInterfaces;
    private final Set<Qualified<?>> allowedDirectInterfaces;
    private final Set<Qualified<?>> allowedProviderInterfaces;
    private final Set<Class<?>> allowedPublishedEvents;
    private final Set<Qualified<?>> allowedSetDirectInterfaces;
    private final Set<Qualified<?>> allowedSetProviderInterfaces;
    private final ComponentContainer delegateContainer;

    /* loaded from: classes5.dex */
    public static class RestrictedPublisher implements Publisher {
        private final Set<Class<?>> allowedPublishedEvents;
        private final Publisher delegate;

        public RestrictedPublisher(Set<Class<?>> r1, Publisher r2) {
            this.allowedPublishedEvents = r1;
            this.delegate = r2;
        }
    }

    public RestrictedComponentContainer(Component<?> r9, ComponentContainer r10) {
        HashSet r0 = new HashSet();
        HashSet r1 = new HashSet();
        HashSet r2 = new HashSet();
        HashSet r3 = new HashSet();
        HashSet r4 = new HashSet();
        Iterator<Dependency> r5 = r9.getDependencies().iterator();
    L4:
        if (r5.hasNext() == false) goto L19;
        Dependency r6 = r5.next();
        if (r6.isDirectInjection() == true) goto L8;
        if (r6.isDeferred() == true) goto L13;
        if (r6.isSet() == true) goto L16;
        r1.add(r6.getInterface());
        goto L4
    L16:
        r4.add(r6.getInterface());
        goto L4
    L13:
        r2.add(r6.getInterface());
        goto L4
    L8:
        if (r6.isSet() == true) goto L9;
        r0.add(r6.getInterface());
        goto L4
    L9:
        r3.add(r6.getInterface());
        goto L4
    L19:
        if (r9.getPublishedEvents().isEmpty() == true) goto L21;
        r0.add(Qualified.unqualified(Publisher.class));
    L21:
        this.allowedDirectInterfaces = Collections.unmodifiableSet(r0);
        this.allowedProviderInterfaces = Collections.unmodifiableSet(r1);
        this.allowedDeferredInterfaces = Collections.unmodifiableSet(r2);
        this.allowedSetDirectInterfaces = Collections.unmodifiableSet(r3);
        this.allowedSetProviderInterfaces = Collections.unmodifiableSet(r4);
        this.allowedPublishedEvents = r9.getPublishedEvents();
        this.delegateContainer = r10;
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> T get(Qualified<T> r2) {
        if (this.allowedDirectInterfaces.contains(r2) == false) goto L7;
        return (T) this.delegateContainer.get(r2);
    L7:
        throw new DependencyException(String.format("Attempting to request an undeclared dependency %s.", new Object[]{r2}));
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> T get(Class<T> r3) {
        if (this.allowedDirectInterfaces.contains(Qualified.unqualified(r3)) == false) goto L10;
        T r0 = (T) this.delegateContainer.get(r3);
        if (r3.equals(Publisher.class) == true) goto L8;
        return r0;
    L8:
        return (T) new RestrictedPublisher(this.allowedPublishedEvents, (Publisher) r0);
    L10:
        throw new DependencyException(String.format("Attempting to request an undeclared dependency %s.", new Object[]{r3}));
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Deferred<T> getDeferred(Qualified<T> r2) {
        if (this.allowedDeferredInterfaces.contains(r2) == false) goto L7;
        return this.delegateContainer.getDeferred(r2);
    L7:
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Deferred<%s>.", new Object[]{r2}));
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Deferred<T> getDeferred(Class<T> r1) {
        return getDeferred(Qualified.unqualified(r1));
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Provider<T> getProvider(Qualified<T> r2) {
        if (this.allowedProviderInterfaces.contains(r2) == false) goto L7;
        return this.delegateContainer.getProvider(r2);
    L7:
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Provider<%s>.", new Object[]{r2}));
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Provider<T> getProvider(Class<T> r1) {
        return getProvider(Qualified.unqualified(r1));
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Set<T> setOf(Qualified<T> r2) {
        if (this.allowedSetDirectInterfaces.contains(r2) == false) goto L7;
        return this.delegateContainer.setOf(r2);
    L7:
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Set<%s>.", new Object[]{r2}));
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Provider<Set<T>> setOfProvider(Qualified<T> r2) {
        if (this.allowedSetProviderInterfaces.contains(r2) == false) goto L7;
        return this.delegateContainer.setOfProvider(r2);
    L7:
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Provider<Set<%s>>.", new Object[]{r2}));
    }
}
