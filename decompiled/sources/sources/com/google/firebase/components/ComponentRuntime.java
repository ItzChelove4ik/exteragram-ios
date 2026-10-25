package com.google.firebase.components;

import android.util.Log;
import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0;
import com.google.firebase.dynamicloading.ComponentLoader;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class ComponentRuntime implements ComponentContainer, ComponentLoader {
    private static final Provider<Set<Object>> EMPTY_PROVIDER = null;
    private final ComponentRegistrarProcessor componentRegistrarProcessor;
    private final Map<Component<?>, Provider<?>> components;
    private final AtomicReference<Boolean> eagerComponentsInitializedWith;
    private final EventBus eventBus;
    private final Map<Qualified<?>, Provider<?>> lazyInstanceMap;
    private final Map<Qualified<?>, LazySet<?>> lazySetMap;
    private Set<String> processedCoroutineDispatcherInterfaces;
    private final List<Provider<ComponentRegistrar>> unprocessedRegistrarProviders;

    /* renamed from: com.google.firebase.components.ComponentRuntime$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private final List<Component<?>> additionalComponents;
        private ComponentRegistrarProcessor componentRegistrarProcessor;
        private final Executor defaultExecutor;
        private final List<Provider<ComponentRegistrar>> lazyRegistrars;

        public static /* synthetic */ ComponentRegistrar $r8$lambda$rq5ahKBnCKJMyE365M3jifpISoo(ComponentRegistrar r0) {
            return r0;
        }

        public Builder(Executor r2) {
            this.lazyRegistrars = new ArrayList();
            this.additionalComponents = new ArrayList();
            this.componentRegistrarProcessor = ComponentRegistrarProcessor.NOOP;
            this.defaultExecutor = r2;
        }

        public Builder addComponent(Component<?> r2) {
            this.additionalComponents.add(r2);
            return this;
        }

        public Builder addComponentRegistrar(ComponentRegistrar r3) {
            this.lazyRegistrars.add(new ComponentRuntime$Builder$$ExternalSyntheticLambda0(r3));
            return this;
        }

        public Builder addLazyComponentRegistrars(Collection<Provider<ComponentRegistrar>> r2) {
            this.lazyRegistrars.addAll(r2);
            return this;
        }

        public ComponentRuntime build() {
            return new ComponentRuntime(this.defaultExecutor, this.lazyRegistrars, this.additionalComponents, this.componentRegistrarProcessor, null);
        }

        public Builder setProcessor(ComponentRegistrarProcessor r1) {
            this.componentRegistrarProcessor = r1;
            return this;
        }
    }

    public static /* synthetic */ Object $r8$lambda$KkGLnATw7zawFxzXHI2bmfEwUaI(ComponentRuntime r2, Component r3) {
        r2.getClass();
        return r3.getFactory().create(new RestrictedComponentContainer(r3, r2));
    }

    /* renamed from: $r8$lambda$Y1HkYIMfr-LJ7mSb7JmI1eSZxOk, reason: not valid java name */
    public static /* synthetic */ void m1920$r8$lambda$Y1HkYIMfrLJ7mSb7JmI1eSZxOk(OptionalProvider r0, Provider r1) {
        r0.set(r1);
    }

    /* renamed from: $r8$lambda$r0miIGl-pzlXuy5qyb8bsIMw2Gk, reason: not valid java name */
    public static /* synthetic */ void m1921$r8$lambda$r0miIGlpzlXuy5qyb8bsIMw2Gk(LazySet r0, Provider r1) {
        r0.add(r1);
    }

    static {
        EMPTY_PROVIDER = new ComponentRuntime$$ExternalSyntheticLambda0();
    }

    private ComponentRuntime(Executor r3, Iterable<Provider<ComponentRegistrar>> r4, Collection<Component<?>> r5, ComponentRegistrarProcessor r6) {
        this.components = new HashMap();
        this.lazyInstanceMap = new HashMap();
        this.lazySetMap = new HashMap();
        this.processedCoroutineDispatcherInterfaces = new HashSet();
        this.eagerComponentsInitializedWith = new AtomicReference();
        EventBus r0 = new EventBus(r3);
        this.eventBus = r0;
        this.componentRegistrarProcessor = r6;
        ArrayList r32 = new ArrayList();
        r32.add(Component.of(r0, EventBus.class, new Class[]{Subscriber.class, Publisher.class}));
        r32.add(Component.of(this, ComponentLoader.class, new Class[0]));
        Iterator<Component<?>> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L8;
        Component<?> r62 = r52.next();
        if (r62 == null) goto L4;
        r32.add(r62);
        goto L4
    L8:
        this.unprocessedRegistrarProviders = iterableToList(r4);
        discoverComponents(r32);
    }

    public /* synthetic */ ComponentRuntime(Executor r1, Iterable r2, Collection r3, ComponentRegistrarProcessor r4, AnonymousClass1 r5) {
        this(r1, r2, r3, r4);
    }

    public static Builder builder(Executor r1) {
        return new Builder(r1);
    }

    private void discoverComponents(List<Component<?>> r9) {
        ArrayList r0 = new ArrayList();
        monitor-enter(this);
        Iterator<Provider<ComponentRegistrar>> r1 = this.unprocessedRegistrarProviders.iterator();     // Catch: Throwable -> L12
    L6:
        if (r1.hasNext() == false) goto L16;
        ComponentRegistrar r2 = r1.next().get();     // Catch: Throwable -> L12 InvalidRegistrarException -> L14
    L9:
        if (r2 == null) goto L6;
        r9.addAll(this.componentRegistrarProcessor.processRegistrar(r2));     // Catch: Throwable -> L12 InvalidRegistrarException -> L14
        r1.remove();     // Catch: Throwable -> L12 InvalidRegistrarException -> L14
    L14:
        e = move-exception;
        r1.remove();     // Catch: Throwable -> L12
        Log.w("ComponentDiscovery", "Invalid component registrar.", e);     // Catch: Throwable -> L12
        goto L6
    L16:
        Iterator<Component<?>> r12 = r9.iterator();     // Catch: Throwable -> L12
    L17:
        int r3 = 0;
        if (r12.hasNext() == false) goto L29;
        Object[] r22 = r12.next().getProvidedInterfaces().toArray();     // Catch: Throwable -> L12
        int r4 = r22.length;     // Catch: Throwable -> L12
    L20:
        if (r3 >= r4) goto L17;
        Object r5 = r22[r3];     // Catch: Throwable -> L12
        if (r5.toString().contains("kotlinx.coroutines.CoroutineDispatcher") == false) goto L27;
        if (this.processedCoroutineDispatcherInterfaces.contains(r5.toString()) == true) goto L25;
        this.processedCoroutineDispatcherInterfaces.add(r5.toString());     // Catch: Throwable -> L12
        goto L27
    L25:
        r12.remove();     // Catch: Throwable -> L12
    L27:
        r3 = r3 + 1;     // Catch: Throwable -> L12
        goto L20
    L29:
        if (this.components.isEmpty() == false) goto L31;
        CycleDetector.detect(r9);     // Catch: Throwable -> L12
    L32:
        Iterator<Component<?>> r13 = r9.iterator();     // Catch: Throwable -> L12
    L34:
        if (r13.hasNext() == false) goto L36;
        Component<?> r23 = r13.next();     // Catch: Throwable -> L12
        Lazy r42 = new Lazy(new ComponentRuntime$$ExternalSyntheticLambda1(this, r23));     // Catch: Throwable -> L12
        this.components.put(r23, r42);     // Catch: Throwable -> L12
        goto L34
    L36:
        r0.addAll(processInstanceComponents(r9));     // Catch: Throwable -> L12
        r0.addAll(processSetComponents());     // Catch: Throwable -> L12
        processDependencies();     // Catch: Throwable -> L12
        monitor-exit(this);     // Catch: Throwable -> L12
        int r92 = r0.size();
    L39:
        if (r3 >= r92) goto L41;
        Object r14 = r0.get(r3);
        r3 = r3 + 1;
        ((Runnable) r14).run();
        goto L39
    L41:
        maybeInitializeEagerComponents();
        return;
    L31:
        ArrayList r15 = new ArrayList(this.components.keySet());     // Catch: Throwable -> L12
        r15.addAll(r9);     // Catch: Throwable -> L12
        CycleDetector.detect(r15);     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        throw th;
    }

    private void doInitializeEagerComponents(Map<Component<?>, Provider<?>> r4, boolean r5) {
        Iterator<Map.Entry<Component<?>, Provider<?>>> r42 = r4.entrySet().iterator();
    L4:
        if (r42.hasNext() == false) goto L11;
        Map.Entry<Component<?>, Provider<?>> r0 = r42.next();
        Component<?> r1 = r0.getKey();
        Provider<?> r02 = r0.getValue();
        if (r1.isAlwaysEager() == true) goto L10;
        if (r1.isEagerInDefaultApp() == false) goto L4;
        if (r5 == false) goto L4;
    L10:
        r02.get();
        goto L4
    L11:
        this.eventBus.enablePublishingAndFlushPending();
    }

    private static <T> List<T> iterableToList(Iterable<T> r2) {
        ArrayList r0 = new ArrayList();
        Iterator<T> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r0.add(r22.next());
        goto L4
    L6:
        return r0;
    }

    private void maybeInitializeEagerComponents() {
        Boolean r0 = this.eagerComponentsInitializedWith.get();
        if (r0 == null) goto L6;
        doInitializeEagerComponents(this.components, r0.booleanValue());
        return;
    }

    private void processDependencies() {
        Iterator<Component<?>> r0 = this.components.keySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L22;
        Component<?> r1 = r0.next();
        Iterator<Dependency> r2 = r1.getDependencies().iterator();
    L7:
        if (r2.hasNext() == false) goto L4;
        Dependency r3 = r2.next();
        if (r3.isSet() == false) goto L14;
        if (this.lazySetMap.containsKey(r3.getInterface()) == true) goto L14;
        this.lazySetMap.put(r3.getInterface(), LazySet.fromCollection(Collections.EMPTY_SET));
    L14:
        if (this.lazyInstanceMap.containsKey(r3.getInterface()) == true) goto L7;
        if (r3.isRequired() == true) goto L21;
        if (r3.isSet() == true) goto L7;
        this.lazyInstanceMap.put(r3.getInterface(), OptionalProvider.empty());
        goto L7
    L21:
        throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", new Object[]{r1, r3.getInterface()}));
    }

    private List<Runnable> processInstanceComponents(List<Component<?>> r7) {
        ArrayList r0 = new ArrayList();
        Iterator<Component<?>> r72 = r7.iterator();
    L4:
        if (r72.hasNext() == false) goto L15;
        Component<?> r1 = r72.next();
        if (r1.isValue() == false) goto L4;
        Provider<?> r2 = this.components.get(r1);
        Iterator<Qualified<? super Object>> r12 = r1.getProvidedInterfaces().iterator();
    L10:
        if (r12.hasNext() == false) goto L4;
        Qualified<? super Object> r3 = r12.next();
        boolean r4 = this.lazyInstanceMap.containsKey(r3);
        Map<Qualified<?>, Provider<?>> r5 = this.lazyInstanceMap;
        if (r4 == false) goto L13;
        r0.add(new ComponentRuntime$$ExternalSyntheticLambda2((OptionalProvider) r5.get(r3), r2));
        goto L10
    L13:
        r5.put(r3, r2);
        goto L10
    L15:
        return r0;
    }

    private List<Runnable> processSetComponents() {
        ArrayList r0 = new ArrayList();
        HashMap r1 = new HashMap();
        Iterator<Map.Entry<Component<?>, Provider<?>>> r2 = this.components.entrySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L15;
        Map.Entry<Component<?>, Provider<?>> r3 = r2.next();
        Component<?> r4 = r3.getKey();
        if (r4.isValue() == true) goto L4;
        Provider<?> r32 = r3.getValue();
        Iterator<Qualified<? super Object>> r42 = r4.getProvidedInterfaces().iterator();
    L10:
        if (r42.hasNext() == false) goto L4;
        Qualified<? super Object> r5 = r42.next();
        if (r1.containsKey(r5) == true) goto L14;
        r1.put(r5, new HashSet());
    L14:
        ((Set) r1.get(r5)).add(r32);
        goto L10
    L15:
        Iterator r12 = r1.entrySet().iterator();
    L17:
        if (r12.hasNext() == false) goto L25;
        Map.Entry r22 = (Map.Entry) r12.next();
        boolean r33 = this.lazySetMap.containsKey(r22.getKey());
        Map<Qualified<?>, LazySet<?>> r43 = this.lazySetMap;
        if (r33 == false) goto L20;
        LazySet<?> r34 = r43.get(r22.getKey());
        Iterator r23 = ((Set) r22.getValue()).iterator();
    L23:
        if (r23.hasNext() == false) goto L17;
        r0.add(new ComponentRuntime$$ExternalSyntheticLambda3(r34, (Provider) r23.next()));
        goto L23
    L20:
        r43.put((Qualified) r22.getKey(), LazySet.fromCollection((Collection) r22.getValue()));
        goto L17
    L25:
        return r0;
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Deferred<T> getDeferred(Qualified<T> r1) {
        Provider<T> r0 = getProvider(r1);
        if (r0 != null) goto L7;
        return OptionalProvider.empty();
    L7:
        if ((r0 instanceof OptionalProvider) == false) goto L11;
        return (OptionalProvider) r0;
    L11:
        return OptionalProvider.of(r0);
    }

    @Override // com.google.firebase.components.ComponentContainer
    public synchronized <T> Provider<T> getProvider(Qualified<T> r2) {
        monitor-enter(this);
        Preconditions.checkNotNull(r2, "Null interface requested.");     // Catch: Throwable -> L6
        Provider<T> r22 = (Provider) this.lazyInstanceMap.get(r2);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r22;
    L6:
        th = move-exception;
        throw th;
    }

    public void initializeEagerComponents(boolean r4) {
        if (AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(this.eagerComponentsInitializedWith, null, Boolean.valueOf(r4)) == true) goto L5;
        return;
    L5:
        monitor-enter(this);
        HashMap r0 = new HashMap(this.components);     // Catch: Throwable -> L10
        monitor-exit(this);     // Catch: Throwable -> L10
        doInitializeEagerComponents(r0, r4);
        return;
    L10:
        th = move-exception;
        throw th;
    }

    @Override // com.google.firebase.components.ComponentContainer
    public synchronized <T> Provider<Set<T>> setOfProvider(Qualified<T> r2) {
        monitor-enter(this);
        LazySet<?> r22 = this.lazySetMap.get(r2);     // Catch: Throwable -> L10
        if (r22 == null) goto L7;
        monitor-exit(this);
        return r22;
    L7:
        Provider<Set<T>> r23 = (Provider<Set<T>>) EMPTY_PROVIDER;
        monitor-exit(this);
        return r23;
    L10:
        th = move-exception;
        throw th;
    }
}
