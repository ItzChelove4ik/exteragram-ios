package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.GsonTypes;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class CollectionTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;

    public static final class Adapter<E> extends TypeAdapter<Collection<E>> {
        private final ObjectConstructor<? extends Collection<E>> constructor;
        private final TypeAdapter<E> elementTypeAdapter;

        public Adapter(TypeAdapter<E> r1, ObjectConstructor<? extends Collection<E>> r2) {
            this.elementTypeAdapter = r1;
            this.constructor = r2;
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ Object read(JsonReader r1) {
            return read(r1);
        }

        @Override // com.google.gson.TypeAdapter
        public Collection<E> read(JsonReader r3) {
            if (r3.peek() != JsonToken.NULL) goto L6;
            r3.nextNull();
            return null;
        L6:
            Collection<E> r0 = this.constructor.construct();
            r3.beginArray();
        L8:
            if (r3.hasNext() == false) goto L10;
            r0.add(this.elementTypeAdapter.read(r3));
            goto L8
        L10:
            r3.endArray();
            return r0;
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, Object r2) {
            write(r1, (Collection) r2);
        }

        public void write(JsonWriter r3, Collection<E> r4) {
            if (r4 != null) goto L5;
            r3.nullValue();
            return;
        L5:
            r3.beginArray();
            Iterator<E> r42 = r4.iterator();
        L7:
            if (r42.hasNext() == false) goto L9;
            E r0 = r42.next();
            this.elementTypeAdapter.write(r3, r0);
            goto L7
        L9:
            r3.endArray();
        }
    }

    public CollectionTypeAdapterFactory(ConstructorConstructor r1) {
        this.constructorConstructor = r1;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson r4, TypeToken<T> r5) {
        Type r0 = r5.getType();
        Class<? super T> r1 = r5.getRawType();
        if (Collection.class.isAssignableFrom(r1) == true) goto L6;
        return null;
    L6:
        Type r02 = GsonTypes.getCollectionElementType(r0, r1);
        return new Adapter(new TypeAdapterRuntimeTypeWrapper(r4, r4.getAdapter(TypeToken.get(r02)), r02), this.constructorConstructor.get(r5, false));
    }
}
