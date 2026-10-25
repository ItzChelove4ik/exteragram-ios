package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sun.jna.Native$$ExternalSyntheticBUOutline2;
import java.util.Objects;

/* loaded from: classes5.dex */
public final class TreeTypeAdapter<T> extends SerializationDelegatingTypeAdapter<T> {
    private final TreeTypeAdapter<T>.GsonContextImpl context;
    private volatile TypeAdapter<T> delegate;
    final Gson gson;
    private final boolean nullSafe;
    private final JsonSerializer<T> serializer;
    private final TypeAdapterFactory skipPastForGetDelegateAdapter;
    private final TypeToken<T> typeToken;

    /* renamed from: com.google.gson.internal.bind.TreeTypeAdapter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public final class GsonContextImpl implements JsonSerializationContext {
        final /* synthetic */ TreeTypeAdapter this$0;

        private GsonContextImpl(TreeTypeAdapter r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ GsonContextImpl(TreeTypeAdapter r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.google.gson.JsonSerializationContext
        public JsonElement serialize(Object r1) {
            return this.this$0.gson.toJsonTree(r1);
        }
    }

    public static final class SingleTypeFactory implements TypeAdapterFactory {
        private final TypeToken<?> exactType;
        private final Class<?> hierarchyType;
        private final boolean matchRawType;
        private final JsonSerializer<?> serializer;

        public SingleTypeFactory(Object r3, TypeToken<?> r4, boolean r5, Class<?> r6) {
            if ((r3 instanceof JsonSerializer) == false) goto L5;
            JsonSerializer<?> r0 = (JsonSerializer) r3;
        L6:
            this.serializer = r0;
            if (r0 == null) goto L10;
            this.exactType = r4;
            this.matchRawType = r5;
            this.hierarchyType = r6;
            return;
        L10:
            Objects.requireNonNull(r3);
            Native$$ExternalSyntheticBUOutline2.m("Type adapter ", r3.getClass().getName(), " must implement JsonSerializer or JsonDeserializer");
            throw null;
        L5:
            r0 = null;
            goto L6
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson r8, TypeToken<T> r9) {
            TypeToken<?> r0 = this.exactType;
            if (r0 != null) goto L5;
            boolean r02 = this.hierarchyType.isAssignableFrom(r9.getRawType());
        L14:
            if (r02 == true) goto L16;
            return null;
        L16:
            return new TreeTypeAdapter(this.serializer, null, r8, r9, this);
        L5:
            if (r0.equals(r9) == false) goto L7;
        L12:
            r02 = true;
            goto L14
        L7:
            if (this.matchRawType == true) goto L9;
        L11:
            r02 = false;
            goto L14
        L9:
            if (this.exactType.getType() != r9.getRawType()) goto L11;
            goto L11
        }
    }

    public TreeTypeAdapter(JsonSerializer<T> r8, JsonDeserializer<T> r9, Gson r10, TypeToken<T> r11, TypeAdapterFactory r12) {
        this(r8, r9, r10, r11, r12, true);
    }

    public TreeTypeAdapter(JsonSerializer<T> r2, JsonDeserializer<T> r3, Gson r4, TypeToken<T> r5, TypeAdapterFactory r6, boolean r7) {
        this.context = new GsonContextImpl(this, null);
        this.serializer = r2;
        this.gson = r4;
        this.typeToken = r5;
        this.skipPastForGetDelegateAdapter = r6;
        this.nullSafe = r7;
    }

    private TypeAdapter<T> delegate() {
        TypeAdapter<T> r0 = this.delegate;
        if (r0 != null) goto L6;
        TypeAdapter<T> r02 = this.gson.getDelegateAdapter(this.skipPastForGetDelegateAdapter, this.typeToken);
        this.delegate = r02;
        return r02;
    L6:
        return r0;
    }

    public static TypeAdapterFactory newFactoryWithMatchRawType(TypeToken<?> r3, Object r4) {
        if (r3.getType() != r3.getRawType()) goto L5;
        boolean r0 = true;
    L7:
        return new SingleTypeFactory(r4, r3, r0, null);
    L5:
        r0 = false;
        goto L7
    }

    public static TypeAdapterFactory newTypeHierarchyFactory(Class<?> r3, Object r4) {
        return new SingleTypeFactory(r4, null, false, r3);
    }

    @Override // com.google.gson.internal.bind.SerializationDelegatingTypeAdapter
    public TypeAdapter<T> getSerializationDelegate() {
        if (this.serializer == null) goto L6;
        return this;
    L6:
        return delegate();
    }

    @Override // com.google.gson.TypeAdapter
    public T read(JsonReader r1) {
        return delegate().read(r1);
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter r3, T r4) {
        JsonSerializer<T> r0 = this.serializer;
        if (r0 != null) goto L7;
        delegate().write(r3, r4);
        return;
    L7:
        if (this.nullSafe == false) goto L11;
        if (r4 != null) goto L11;
        r3.nullValue();
        return;
    L11:
        Streams.write(r0.serialize(r4, this.typeToken.getType(), this.context), r3);
    }
}
