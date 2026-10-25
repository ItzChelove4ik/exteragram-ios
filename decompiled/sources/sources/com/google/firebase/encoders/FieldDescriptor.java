package com.google.firebase.encoders;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class FieldDescriptor {
    private final String name;
    private final Map<Class<?>, Object> properties;

    /* renamed from: com.google.firebase.encoders.FieldDescriptor$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder {
        private final String name;
        private Map<Class<?>, Object> properties;

        public Builder(String r2) {
            this.properties = null;
            this.name = r2;
        }

        public FieldDescriptor build() {
            String r1 = this.name;
            if (this.properties != null) goto L5;
            Map r3 = Collections.EMPTY_MAP;
        L7:
            return new FieldDescriptor(r1, r3, null);
        L5:
            r3 = Collections.unmodifiableMap(new HashMap(this.properties));
            goto L7
        }

        public <T extends Annotation> Builder withProperty(T r3) {
            if (this.properties != null) goto L5;
            this.properties = new HashMap();
        L5:
            this.properties.put(r3.annotationType(), r3);
            return this;
        }
    }

    private FieldDescriptor(String r1, Map<Class<?>, Object> r2) {
        this.name = r1;
        this.properties = r2;
    }

    public /* synthetic */ FieldDescriptor(String r1, Map r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public static Builder builder(String r1) {
        return new Builder(r1);
    }

    public static FieldDescriptor of(String r2) {
        return new FieldDescriptor(r2, Collections.EMPTY_MAP);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof FieldDescriptor) == true) goto L8;
        return false;
    L8:
        FieldDescriptor r52 = (FieldDescriptor) r5;
        if (this.name.equals(r52.name) == true) goto L11;
    L13:
        return false;
    L11:
        if (this.properties.equals(r52.properties) == false) goto L13;
        return true;
    }

    public String getName() {
        return this.name;
    }

    public <T extends Annotation> T getProperty(Class<T> r1) {
        return (T) this.properties.get(r1);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.properties.hashCode();
    }

    public String toString() {
        return "FieldDescriptor{name=" + this.name + ", properties=" + this.properties.values() + "}";
    }
}
