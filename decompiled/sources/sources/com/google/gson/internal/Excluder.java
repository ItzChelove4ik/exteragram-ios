package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes.dex */
public final class Excluder implements TypeAdapterFactory, Cloneable {
    public static final Excluder DEFAULT = null;
    private List<ExclusionStrategy> deserializationStrategies;
    private int modifiers;
    private boolean requireExpose;
    private List<ExclusionStrategy> serializationStrategies;
    private boolean serializeInnerClasses;
    private double version;

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: com.google.gson.internal.Excluder$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1<T> extends TypeAdapter<T> {
        private volatile TypeAdapter<T> delegate;
        final /* synthetic */ Excluder this$0;
        final /* synthetic */ Gson val$gson;
        final /* synthetic */ boolean val$skipDeserialize;
        final /* synthetic */ boolean val$skipSerialize;
        final /* synthetic */ TypeToken val$type;

        public AnonymousClass1(Excluder r1, boolean r2, boolean r3, Gson r4, TypeToken r5) {
            this.this$0 = r1;
            this.val$skipDeserialize = r2;
            this.val$skipSerialize = r3;
            this.val$gson = r4;
            this.val$type = r5;
        }

        private TypeAdapter<T> delegate() {
            TypeAdapter<T> r0 = this.delegate;
            if (r0 != null) goto L6;
            TypeAdapter<T> r02 = this.val$gson.getDelegateAdapter(this.this$0, this.val$type);
            this.delegate = r02;
            return r02;
        L6:
            return r0;
        }

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader r2) {
            if (this.val$skipDeserialize == false) goto L7;
            r2.skipValue();
            return null;
        L7:
            return delegate().read(r2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter r2, T r3) {
            if (this.val$skipSerialize == false) goto L6;
            r2.nullValue();
            return;
        L6:
            delegate().write(r2, r3);
        }
    }

    static {
        DEFAULT = new Excluder();
    }

    public Excluder() {
        this.version = -1.0d;
        this.modifiers = 136;
        this.serializeInnerClasses = true;
        List<ExclusionStrategy> r0 = Collections.EMPTY_LIST;
        this.serializationStrategies = r0;
        this.deserializationStrategies = r0;
    }

    private static boolean isInnerClass(Class<?> r1) {
        if (r1.isMemberClass() == true) goto L5;
        return false;
    L5:
        if (ReflectionHelper.isStatic(r1) == true) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isValidSince(Since r4) {
        if (r4 == null) goto L9;
        double r1 = r4.value();
        if (this.version < r1) goto L7;
        return true;
    L7:
        return false;
    L9:
        return true;
    }

    private boolean isValidUntil(Until r4) {
        if (r4 == null) goto L9;
        double r1 = r4.value();
        if (this.version >= r1) goto L7;
        return true;
    L7:
        return false;
    L9:
        return true;
    }

    private boolean isValidVersion(Since r1, Until r2) {
        if (isValidSince(r1) == true) goto L5;
        return false;
    L5:
        if (isValidUntil(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public Excluder clone() {
        return (Excluder) super.clone();
    L4:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    }

    /* renamed from: clone, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m1990clone() {
        return clone();
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson r9, TypeToken<T> r10) {
        Class<? super T> r0 = r10.getRawType();
        boolean r5 = excludeClass(r0, true);
        boolean r4 = excludeClass(r0, false);
        if (r5 == true) goto L8;
        if (r4 == true) goto L8;
        return null;
    L8:
        return new AnonymousClass1(this, r4, r5, r9, r10);
    }

    public boolean excludeClass(Class<?> r5, boolean r6) {
        if (this.version == (-1.0d)) goto L8;
        if (isValidVersion((Since) r5.getAnnotation(Since.class), (Until) r5.getAnnotation(Until.class)) == true) goto L8;
        return true;
    L8:
        if (this.serializeInnerClasses == false) goto L10;
    L12:
        if (r6 == false) goto L14;
    L18:
        if (r6 == false) goto L20;
        List<ExclusionStrategy> r4 = this.serializationStrategies;
    L21:
        Iterator<ExclusionStrategy> r42 = r4.iterator();
    L23:
        if (r42.hasNext() == false) goto L27;
        if (r42.next().shouldSkipClass(r5) == false) goto L23;
        return true;
    L27:
        return false;
    L20:
        r4 = this.deserializationStrategies;
        goto L21
    L14:
        if (Enum.class.isAssignableFrom(r5) == true) goto L18;
        if (ReflectionHelper.isAnonymousOrNonStaticLocal(r5) == false) goto L18;
        return true;
    L10:
        if (isInnerClass(r5) == false) goto L12;
        return true;
    }

    public boolean excludeField(Field r7, boolean r8) {
        if ((this.modifiers & r7.getModifiers()) == 0) goto L6;
        return true;
    L6:
        if (this.version == (-1.0d)) goto L11;
        if (isValidVersion((Since) r7.getAnnotation(Since.class), (Until) r7.getAnnotation(Until.class)) == true) goto L11;
        return true;
    L11:
        if (r7.isSynthetic() == false) goto L14;
        return true;
    L14:
        if (this.requireExpose == false) goto L25;
        Expose r0 = (Expose) r7.getAnnotation(Expose.class);
        if (r0 == null) goto L23;
        if (r8 == false) goto L22;
        if (r0.serialize() == true) goto L25;
    L22:
        if (r0.deserialize() == true) goto L25;
    L23:
        return true;
    L25:
        if (excludeClass(r7.getType(), r8) == false) goto L27;
        return true;
    L27:
        if (r8 == false) goto L29;
        List<ExclusionStrategy> r6 = this.serializationStrategies;
    L31:
        if (r6.isEmpty() == true) goto L38;
        FieldAttributes r82 = new FieldAttributes(r7);
        Iterator<ExclusionStrategy> r62 = r6.iterator();
    L34:
        if (r62.hasNext() == false) goto L43;
        if (r62.next().shouldSkipField(r82) == false) goto L34;
        return true;
    L43:
        return false;
    L38:
        return false;
    L29:
        r6 = this.deserializationStrategies;
        goto L31
    }

    public Excluder withExclusionStrategy(ExclusionStrategy r3, boolean r4, boolean r5) {
        Excluder r0 = clone();
        if (r4 == false) goto L5;
        ArrayList r42 = new ArrayList(this.serializationStrategies);
        r0.serializationStrategies = r42;
        r42.add(r3);
    L5:
        if (r5 == false) goto L7;
        ArrayList r43 = new ArrayList(this.deserializationStrategies);
        r0.deserializationStrategies = r43;
        r43.add(r3);
    L7:
        return r0;
    }
}
