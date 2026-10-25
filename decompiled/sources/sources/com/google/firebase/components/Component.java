package com.google.firebase.components;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public final class Component<T> {
    private final Set<Dependency> dependencies;
    private final ComponentFactory<T> factory;
    private final int instantiation;
    private final String name;
    private final Set<Qualified<? super T>> providedInterfaces;
    private final Set<Class<?>> publishedEvents;
    private final int type;

    /* renamed from: com.google.firebase.components.Component$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Builder<T> {
        private final Set<Dependency> dependencies;
        private ComponentFactory<T> factory;
        private int instantiation;
        private String name;
        private final Set<Qualified<? super T>> providedInterfaces;
        private final Set<Class<?>> publishedEvents;
        private int type;

        @SafeVarargs
        private Builder(Qualified<T> r4, Qualified<? super T>... r5) {
            this.name = null;
            HashSet r0 = new HashSet();
            this.providedInterfaces = r0;
            this.dependencies = new HashSet();
            int r1 = 0;
            this.instantiation = 0;
            this.type = 0;
            this.publishedEvents = new HashSet();
            Preconditions.checkNotNull(r4, "Null interface");
            r0.add(r4);
            int r42 = r5.length;
        L3:
            if (r1 >= r42) goto L5;
            Preconditions.checkNotNull(r5[r1], "Null interface");
            r1 = r1 + 1;
            goto L3
        L5:
            Collections.addAll(this.providedInterfaces, r5);
        }

        public /* synthetic */ Builder(Qualified r1, Qualified[] r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        @SafeVarargs
        private Builder(Class<T> r5, Class<? super T>... r6) {
            this.name = null;
            HashSet r0 = new HashSet();
            this.providedInterfaces = r0;
            this.dependencies = new HashSet();
            int r1 = 0;
            this.instantiation = 0;
            this.type = 0;
            this.publishedEvents = new HashSet();
            Preconditions.checkNotNull(r5, "Null interface");
            r0.add(Qualified.unqualified(r5));
            int r52 = r6.length;
        L3:
            if (r1 >= r52) goto L5;
            Class<? super T> r02 = r6[r1];
            Preconditions.checkNotNull(r02, "Null interface");
            this.providedInterfaces.add(Qualified.unqualified(r02));
            r1 = r1 + 1;
            goto L3
        }

        public /* synthetic */ Builder(Class r1, Class[] r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        public static /* synthetic */ Builder access$200(Builder r0) {
            return r0.intoSet();
        }

        private Builder<T> intoSet() {
            this.type = 1;
            return this;
        }

        private Builder<T> setInstantiation(int r3) {
            if (this.instantiation != 0) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkState(r0, "Instantiation type has already been set.");
            this.instantiation = r3;
            return this;
        L5:
            r0 = false;
            goto L6
        }

        private void validateInterface(Qualified<?> r1) {
            Preconditions.checkArgument(!this.providedInterfaces.contains(r1), "Components are not allowed to depend on interfaces they themselves provide.");
        }

        public Builder<T> add(Dependency r2) {
            Preconditions.checkNotNull(r2, "Null dependency");
            validateInterface(r2.getInterface());
            this.dependencies.add(r2);
            return this;
        }

        public Builder<T> alwaysEager() {
            return setInstantiation(1);
        }

        public Component<T> build() {
            if (this.factory == null) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkState(r0, "Missing required property: factory.");
            return new Component(this.name, new HashSet(this.providedInterfaces), new HashSet(this.dependencies), this.instantiation, this.type, this.factory, this.publishedEvents, null);
        L5:
            r0 = false;
            goto L6
        }

        public Builder<T> eagerInDefaultApp() {
            return setInstantiation(2);
        }

        public Builder<T> factory(ComponentFactory<T> r2) {
            this.factory = (ComponentFactory) Preconditions.checkNotNull(r2, "Null factory");
            return this;
        }

        public Builder<T> name(String r1) {
            this.name = r1;
            return this;
        }
    }

    public static /* synthetic */ Object $r8$lambda$1XnijlikLlzJWfIqLOXy2V5iuHw(Object r0, ComponentContainer r1) {
        return r0;
    }

    public static /* synthetic */ Object $r8$lambda$OZ0j4a0bDeW_qgAe4WJTrUMuxVw(Object r0, ComponentContainer r1) {
        return r0;
    }

    private Component(String r1, Set<Qualified<? super T>> r2, Set<Dependency> r3, int r4, int r5, ComponentFactory<T> r6, Set<Class<?>> r7) {
        this.name = r1;
        this.providedInterfaces = Collections.unmodifiableSet(r2);
        this.dependencies = Collections.unmodifiableSet(r3);
        this.instantiation = r4;
        this.type = r5;
        this.factory = r6;
        this.publishedEvents = Collections.unmodifiableSet(r7);
    }

    public /* synthetic */ Component(String r1, Set r2, Set r3, int r4, int r5, ComponentFactory r6, Set r7, AnonymousClass1 r8) {
        this(r1, r2, r3, r4, r5, r6, r7);
    }

    public static <T> Builder<T> builder(Qualified<T> r3) {
        return new Builder(r3, new Qualified[0], null);
    }

    @SafeVarargs
    public static <T> Builder<T> builder(Qualified<T> r2, Qualified<? super T>... r3) {
        return new Builder(r2, r3, null);
    }

    public static <T> Builder<T> builder(Class<T> r3) {
        return new Builder(r3, new Class[0], null);
    }

    @SafeVarargs
    public static <T> Builder<T> builder(Class<T> r2, Class<? super T>... r3) {
        return new Builder(r2, r3, null);
    }

    public static <T> Component<T> intoSet(T r1, Class<T> r2) {
        return intoSetBuilder(r2).factory(new Component$$ExternalSyntheticLambda0(r1)).build();
    }

    public static <T> Builder<T> intoSetBuilder(Class<T> r0) {
        return Builder.access$200(builder(r0));
    }

    @SafeVarargs
    public static <T> Component<T> of(T r0, Class<T> r1, Class<? super T>... r2) {
        return builder(r1, r2).factory(new Component$$ExternalSyntheticLambda1(r0)).build();
    }

    public Set<Dependency> getDependencies() {
        return this.dependencies;
    }

    public ComponentFactory<T> getFactory() {
        return this.factory;
    }

    public String getName() {
        return this.name;
    }

    public Set<Qualified<? super T>> getProvidedInterfaces() {
        return this.providedInterfaces;
    }

    public Set<Class<?>> getPublishedEvents() {
        return this.publishedEvents;
    }

    public boolean isAlwaysEager() {
        if (this.instantiation != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean isEagerInDefaultApp() {
        if (this.instantiation != 2) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isValue() {
        if (this.type != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public String toString() {
        return "Component<" + Arrays.toString(this.providedInterfaces.toArray()) + ">{" + this.instantiation + ", type=" + this.type + ", deps=" + Arrays.toString(this.dependencies.toArray()) + "}";
    }

    public Component<T> withFactory(ComponentFactory<T> r9) {
        return new Component(this.name, this.providedInterfaces, this.dependencies, this.instantiation, this.type, r9, this.publishedEvents);
    }
}
