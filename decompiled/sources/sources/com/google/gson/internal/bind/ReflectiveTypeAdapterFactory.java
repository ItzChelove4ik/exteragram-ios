package com.google.gson.internal.bind;

import androidx.work.WorkerFactory$$ExternalSyntheticBUOutline0;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.ReflectionAccessFilter$FilterResult;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ConstructorConstructor$$ExternalSyntheticBUOutline0;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.GsonTypes;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.ReflectionAccessFilterHelper;
import com.google.gson.internal.TroubleshootingGuide;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    private final List<Object> reflectionFilters;

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1<T> extends TypeAdapter<T> {
        final /* synthetic */ ReflectiveTypeAdapterFactory this$0;

        public AnonymousClass1(ReflectiveTypeAdapterFactory r1) {
            this.this$0 = r1;
        }

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader r1) {
            r1.skipValue();
            return null;
        }

        public String toString() {
            return "AnonymousOrNonStaticLocalClassAdapter";
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter r1, T r2) {
            r1.nullValue();
        }
    }

    /* renamed from: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$2, reason: invalid class name */
    public class AnonymousClass2 extends BoundField {
        final /* synthetic */ ReflectiveTypeAdapterFactory this$0;
        final /* synthetic */ Method val$accessor;
        final /* synthetic */ boolean val$blockInaccessible;
        final /* synthetic */ boolean val$isPrimitive;
        final /* synthetic */ boolean val$isStaticFinalField;
        final /* synthetic */ TypeAdapter val$typeAdapter;
        final /* synthetic */ TypeAdapter val$writeTypeAdapter;

        public AnonymousClass2(ReflectiveTypeAdapterFactory r1, String r2, Field r3, boolean r4, Method r5, TypeAdapter r6, TypeAdapter r7, boolean r8, boolean r9) {
            this.this$0 = r1;
            this.val$blockInaccessible = r4;
            this.val$accessor = r5;
            this.val$writeTypeAdapter = r6;
            this.val$typeAdapter = r7;
            this.val$isPrimitive = r8;
            this.val$isStaticFinalField = r9;
            super(r2, r3);
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField
        public void readIntoArray(JsonReader r3, int r4, Object[] r5) {
            Object r0 = this.val$typeAdapter.read(r3);
            if (r0 == null) goto L5;
        L9:
            r5[r4] = r0;
            return;
        L5:
            if (this.val$isPrimitive == false) goto L9;
            throw new JsonParseException("null is not allowed as value for record component '" + this.fieldName + "' of primitive type; at path " + r3.getPath());
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField
        public void readIntoField(JsonReader r2, Object r3) {
            Object r22 = this.val$typeAdapter.read(r2);
            if (r22 != null) goto L9;
            if (this.val$isPrimitive == false) goto L9;
            return;
        L9:
            if (this.val$blockInaccessible == false) goto L12;
            ReflectiveTypeAdapterFactory.access$000(r3, this.field);
        L13:
            this.field.set(r3, r22);
            return;
        L12:
            if (this.val$isStaticFinalField == false) goto L13;
            ConstructorConstructor$$ExternalSyntheticBUOutline0.m("Cannot set value of 'static final' ", ReflectionHelper.getAccessibleObjectDescription(this.field, false));
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField
        public void write(JsonWriter r3, Object r4) {
            if (this.val$blockInaccessible == false) goto L8;
            Method r0 = this.val$accessor;
            if (r0 != null) goto L7;
            ReflectiveTypeAdapterFactory.access$000(r4, this.field);
            goto L8
        L7:
            ReflectiveTypeAdapterFactory.access$000(r4, r0);
        L8:
            Method r02 = this.val$accessor;
            if (r02 != null) goto L21;
            Object r03 = this.field.get(r4);
        L17:
            if (r03 != r4) goto L19;
            return;
        L19:
            r3.name(this.serializedName);
            this.val$writeTypeAdapter.write(r3, r03);
            return;
        L21:
            r03 = r02.invoke(r4, null);     // Catch: InvocationTargetException -> L13
        L13:
            e = move-exception;
            throw new JsonIOException("Accessor " + ReflectionHelper.getAccessibleObjectDescription(this.val$accessor, false) + " threw exception", e.getCause());
        }
    }

    public static abstract class Adapter<T, A> extends TypeAdapter<T> {
        private final FieldsData fieldsData;

        public Adapter(FieldsData r1) {
            this.fieldsData = r1;
        }

        public abstract A createAccumulator();

        public abstract T finalize(A r1);

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader r4) {
            if (r4.peek() != JsonToken.NULL) goto L6;
            r4.nextNull();
            return null;
        L6:
            A r0 = createAccumulator();
            Map<String, BoundField> r1 = this.fieldsData.deserializedFields;
            r4.beginObject();     // Catch: IllegalAccessException -> L17 IllegalStateException -> L20
        L8:
            if (r4.hasNext() == false) goto L15;
            BoundField r2 = r1.get(r4.nextName());     // Catch: IllegalAccessException -> L17 IllegalStateException -> L20
            if (r2 == null) goto L12;
            readField(r0, r4, r2);     // Catch: IllegalAccessException -> L17 IllegalStateException -> L20
            goto L8
        L12:
            r4.skipValue();     // Catch: IllegalAccessException -> L17 IllegalStateException -> L20
            goto L8
        L15:
            r4.endObject();
            return finalize(r0);
        L17:
            e = move-exception;
            throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
        L20:
            e = move-exception;
            throw new JsonSyntaxException(e);
        }

        public abstract void readField(A r1, JsonReader r2, BoundField r3);

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter r2, T r3) {
            if (r3 != null) goto L5;
            r2.nullValue();
            return;
        L5:
            r2.beginObject();
            Iterator<BoundField> r1 = this.fieldsData.serializedFields.iterator();     // Catch: IllegalAccessException -> L13
        L7:
            if (r1.hasNext() == false) goto L11;
            r1.next().write(r2, r3);     // Catch: IllegalAccessException -> L13
            goto L7
        L11:
            r2.endObject();
            return;
        L13:
            e = move-exception;
            throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
        }
    }

    public static abstract class BoundField {
        final Field field;
        final String fieldName;
        final String serializedName;

        public BoundField(String r1, Field r2) {
            this.serializedName = r1;
            this.field = r2;
            this.fieldName = r2.getName();
        }

        public abstract void readIntoArray(JsonReader r1, int r2, Object[] r3);

        public abstract void readIntoField(JsonReader r1, Object r2);

        public abstract void write(JsonWriter r1, Object r2);
    }

    public static final class FieldReflectionAdapter<T> extends Adapter<T, T> {
        private final ObjectConstructor<T> constructor;

        public FieldReflectionAdapter(ObjectConstructor<T> r1, FieldsData r2) {
            super(r2);
            this.constructor = r1;
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter
        public T createAccumulator() {
            return this.constructor.construct();
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter
        public T finalize(T r1) {
            return r1;
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter
        public void readField(T r1, JsonReader r2, BoundField r3) {
            r3.readIntoField(r2, r1);
        }
    }

    public static class FieldsData {
        static final FieldsData EMPTY = null;
        final Map<String, BoundField> deserializedFields;
        final List<BoundField> serializedFields;

        static {
            EMPTY = new FieldsData(Collections.EMPTY_MAP, Collections.EMPTY_LIST);
        }

        public FieldsData(Map<String, BoundField> r1, List<BoundField> r2) {
            this.deserializedFields = r1;
            this.serializedFields = r2;
        }
    }

    /* loaded from: classes5.dex */
    public static final class RecordAdapter<T> extends Adapter<T, Object[]> {
        static final Map<Class<?>, Object> PRIMITIVE_DEFAULTS = null;
        private final Map<String, Integer> componentIndices;
        private final Constructor<T> constructor;
        private final Object[] constructorArgsDefaults;

        static {
            PRIMITIVE_DEFAULTS = primitiveDefaults();
        }

        public RecordAdapter(Class<T> r4, FieldsData r5, boolean r6) {
            super(r5);
            this.componentIndices = new HashMap();
            Constructor<T> r52 = ReflectionHelper.getCanonicalRecordConstructor(r4);
            this.constructor = r52;
            if (r6 == false) goto L5;
            ReflectiveTypeAdapterFactory.access$000(null, r52);
        L6:
            String[] r42 = ReflectionHelper.getRecordComponentNames(r4);
            int r53 = 0;
            int r62 = 0;
        L8:
            if (r62 >= r42.length) goto L10;
            this.componentIndices.put(r42[r62], Integer.valueOf(r62));
            r62 = r62 + 1;
            goto L8
        L10:
            Class<?>[] r43 = this.constructor.getParameterTypes();
            this.constructorArgsDefaults = new Object[r43.length];
        L12:
            if (r53 >= r43.length) goto L14;
            this.constructorArgsDefaults[r53] = PRIMITIVE_DEFAULTS.get(r43[r53]);
            r53 = r53 + 1;
            goto L12
        L14:
            return;
        L5:
            ReflectionHelper.makeAccessible(r52);
            goto L6
        }

        private static Map<Class<?>, Object> primitiveDefaults() {
            HashMap r0 = new HashMap();
            r0.put(Byte.TYPE, (byte) 0);
            r0.put(Short.TYPE, (short) 0);
            r0.put(Integer.TYPE, 0);
            r0.put(Long.TYPE, 0L);
            Float r2 = Float.valueOf(0.0f);
            r0.put(Float.TYPE, r2);
            Double r22 = Double.valueOf(0.0d);
            r0.put(Double.TYPE, r22);
            r0.put(Character.TYPE, (char) 0);
            r0.put(Boolean.TYPE, Boolean.FALSE);
            return r0;
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter
        public /* bridge */ /* synthetic */ Object[] createAccumulator() {
            return createAccumulator2();
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter
        /* renamed from: createAccumulator, reason: avoid collision after fix types in other method */
        public Object[] createAccumulator2() {
            return (Object[]) this.constructorArgsDefaults.clone();
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter
        public /* bridge */ /* synthetic */ Object finalize(Object[] r1) {
            return finalize2(r1);
        }

        /* renamed from: finalize, reason: avoid collision after fix types in other method */
        public T finalize2(Object[] r6) {
            return this.constructor.newInstance(r6);
        L15:
            e = move-exception;
            throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
        L7:
            e = e;
        L14:
            throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(this.constructor) + "' with args " + Arrays.toString(r6), e);
        L9:
            e = e;
        L5:
            e = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m("Failed to invoke constructor '" + ReflectionHelper.constructorToString(this.constructor) + "' with args " + Arrays.toString(r6), e.getCause());
            return null;
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter
        public /* bridge */ /* synthetic */ void readField(Object[] r1, JsonReader r2, BoundField r3) {
            readField2(r1, r2, r3);
        }

        /* renamed from: readField, reason: avoid collision after fix types in other method */
        public void readField2(Object[] r3, JsonReader r4, BoundField r5) {
            Integer r0 = this.componentIndices.get(r5.fieldName);
            if (r0 == null) goto L6;
            r5.readIntoArray(r4, r0.intValue(), r3);
            return;
        L6:
            WorkerFactory$$ExternalSyntheticBUOutline0.m("Could not find the index in the constructor '", ReflectionHelper.constructorToString(this.constructor), "' for field with name '", r5.fieldName, "', unable to determine which argument in the constructor the field corresponds to. This is unexpected behavior, as we expect the RecordComponents to have the same names as the fields in the Java class, and that the order of the RecordComponents is the same as the order of the canonical constructor parameters.");
        }
    }

    public ReflectiveTypeAdapterFactory(ConstructorConstructor r1, FieldNamingStrategy r2, Excluder r3, JsonAdapterAnnotationTypeAdapterFactory r4, List<Object> r5) {
        this.constructorConstructor = r1;
        this.fieldNamingPolicy = r2;
        this.excluder = r3;
        this.jsonAdapterFactory = r4;
        this.reflectionFilters = r5;
    }

    public static /* synthetic */ void access$000(Object r0, AccessibleObject r1) {
        checkAccessible(r0, r1);
    }

    private static <M extends AccessibleObject & Member> void checkAccessible(Object r1, M r2) {
        if (Modifier.isStatic(r2.getModifiers()) == false) goto L6;
        r1 = null;
    L6:
        if (ReflectionAccessFilterHelper.canAccess(r2, r1) == false) goto L9;
        return;
    L9:
        throw new JsonIOException(ReflectionHelper.getAccessibleObjectDescription(r2, true) + " is not accessible and ReflectionAccessFilter does not permit making it accessible. Register a TypeAdapter for the declaring type, adjust the access filter or increase the visibility of the element and its declaring type.");
    }

    private BoundField createBoundField(Gson r12, Field r13, Method r14, String r15, TypeToken<?> r16, boolean r17, boolean r18) {
        boolean r8 = Primitives.isPrimitive(r16.getRawType());
        int r0 = r13.getModifiers();
        boolean r9 = false;
        boolean r10 = true;
        if (Modifier.isStatic(r0) == true) goto L5;
    L7:
        boolean r02 = false;
    L8:
        JsonAdapter r6 = (JsonAdapter) r13.getAnnotation(JsonAdapter.class);
        if (r6 == null) goto L11;
        TypeAdapter<?> r2 = this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, r12, r16, r6, false);
    L12:
        if (r2 != null) goto L16;
        r10 = r02;
    L16:
        if (r2 != null) goto L18;
        r2 = r12.getAdapter(r16);
    L18:
        TypeAdapter<?> r7 = r2;
        if (r17 == false) goto L24;
        if (r10 == false) goto L22;
        TypeAdapter<?> r03 = r7;
    L23:
        TypeAdapter<?> r62 = r03;
    L26:
        return new AnonymousClass2(this, r15, r13, r18, r14, r62, r7, r8, r9);
    L22:
        r03 = new TypeAdapterRuntimeTypeWrapper(r12, r7, r16.getType());
        goto L23
    L24:
        r62 = r7;
        goto L26
    L11:
        r2 = null;
        goto L12
    L5:
        if (Modifier.isFinal(r0) == false) goto L7;
        r02 = false;
        r9 = true;
        goto L8
    }

    private static IllegalArgumentException createDuplicateFieldException(Class<?> r3, String r4, Field r5, Field r6) {
        throw new IllegalArgumentException("Class " + r3.getName() + " declares multiple JSON fields named '" + r4 + "'; conflict is caused by fields " + ReflectionHelper.fieldToString(r5) + " and " + ReflectionHelper.fieldToString(r6) + "\nSee " + TroubleshootingGuide.createUrl("duplicate-fields"));
    }

    private FieldsData getBoundFields(Gson r20, TypeToken<?> r21, Class<?> r22, boolean r23, boolean r24) {
        ReflectiveTypeAdapterFactory r0 = this;
        if (r22.isInterface() == true) goto L5;
        LinkedHashMap r9 = new LinkedHashMap();
        LinkedHashMap r10 = new LinkedHashMap();
        TypeToken<?> r11 = r21;
        boolean r1 = r23;
        Class<?> r12 = r22;
    L8:
        if (r12 == Object.class) goto L66;
        Field[] r13 = r12.getDeclaredFields();
        boolean r14 = true;
        boolean r15 = false;
        if (r12 != r22) goto L12;
    L19:
        boolean r7 = r1;
        int r16 = r13.length;
        int r2 = 0;
    L23:
        if (r2 >= r16) goto L64;
        int r3 = r2;
        Field r25 = r13[r3];
        boolean r6 = r0.includeField(r25, r14);
        boolean r4 = r0.includeField(r25, r15);
        if (r6 == true) goto L28;
        if (r4 == true) goto L28;
        int r17 = r16;
        int r18 = r3;
        TypeToken<?> r162 = r11;
        boolean r112 = r15;
    L63:
        r2 = r18 + 1;
        r0 = this;
        r15 = r112;
        r11 = r162;
        r16 = r17;
        r14 = true;
    L28:
        Method r5 = null;
        if (r24 == true) goto L31;
    L43:
        boolean r142 = r4;
    L44:
        if (r7 == true) goto L47;
        if (r5 != null) goto L47;
        ReflectionHelper.makeAccessible(r25);
    L47:
        Type r42 = GsonTypes.resolve(r11.getType(), r12, r25.getGenericType());
        List<String> r152 = r0.getFieldNames(r25);
        r162 = r11;
        r112 = false;
        String r172 = r152.get(0);
        r18 = r3;
        r17 = r16;
        BoundField r32 = r0.createBoundField(r20, r25, r5, r172, TypeToken.get(r42), r6, r7);
        if (r142 == false) goto L57;
        Iterator<String> r02 = r152.iterator();
    L51:
        if (r02.hasNext() == false) goto L57;
        String r19 = r02.next();
        BoundField r52 = (BoundField) r9.put(r19, r32);
        if (r52 == null) goto L51;
        throw createDuplicateFieldException(r22, r19, r52.field, r25);
    L57:
        if (r6 == false) goto L63;
        BoundField r03 = (BoundField) r10.put(r172, r32);
        if (r03 == null) goto L63;
        throw createDuplicateFieldException(r22, r172, r03.field, r25);
    L31:
        if (Modifier.isStatic(r25.getModifiers()) == false) goto L33;
        r142 = r15;
        goto L44
    L33:
        r5 = ReflectionHelper.getAccessor(r12, r25);
        if (r7 == true) goto L37;
        ReflectionHelper.makeAccessible(r5);
    L37:
        if (r5.getAnnotation(SerializedName.class) == null) goto L43;
        if (r25.getAnnotation(SerializedName.class) != null) goto L43;
        throw new JsonIOException("@SerializedName on " + ReflectionHelper.getAccessibleObjectDescription(r5, r15) + " is not supported");
    L64:
        r11 = TypeToken.get(GsonTypes.resolve(r11.getType(), r12, r12.getGenericSuperclass()));
        r12 = r11.getRawType();
        r0 = this;
        r1 = r7;
        goto L8
    L12:
        if (r13.length <= 0) goto L19;
        ReflectionAccessFilter$FilterResult r110 = ReflectionAccessFilterHelper.getFilterResult(r0.reflectionFilters, r12);
        if (r110 == ReflectionAccessFilter$FilterResult.BLOCK_ALL) goto L21;
        if (r110 != ReflectionAccessFilter$FilterResult.BLOCK_INACCESSIBLE) goto L18;
        r1 = true;
        goto L19
    L18:
        r1 = false;
        goto L19
    L21:
        throw new JsonIOException("ReflectionAccessFilter does not permit using reflection for " + r12 + " (supertype of " + r22 + "). Register a TypeAdapter for this type or adjust the access filter.");
    L66:
        return new FieldsData(r9, new ArrayList(r10.values()));
    L5:
        return FieldsData.EMPTY;
    }

    private List<String> getFieldNames(Field r3) {
        SerializedName r0 = (SerializedName) r3.getAnnotation(SerializedName.class);
        if (r0 != null) goto L5;
        String r02 = this.fieldNamingPolicy.translateName(r3);
        List<String> r2 = this.fieldNamingPolicy.alternateNames(r3);
    L7:
        if (r2.isEmpty() == true) goto L9;
        ArrayList r32 = new ArrayList(r2.size() + 1);
        r32.add(r02);
        r32.addAll(r2);
        return r32;
    L9:
        return Collections.singletonList(r02);
    L5:
        String r22 = r0.value();
        List<String> r33 = Arrays.asList(r0.alternate());
        r02 = r22;
        r2 = r33;
        goto L7
    }

    private boolean includeField(Field r1, boolean r2) {
        return !this.excluder.excludeField(r1, r2);
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson r8, TypeToken<T> r9) {
        Class<? super T> r3 = r9.getRawType();
        if (Object.class.isAssignableFrom(r3) == true) goto L7;
        return null;
    L7:
        if (ReflectionHelper.isAnonymousOrNonStaticLocal(r3) == true) goto L9;
        ReflectionAccessFilter$FilterResult r0 = ReflectionAccessFilterHelper.getFilterResult(this.reflectionFilters, r3);
        if (r0 == ReflectionAccessFilter$FilterResult.BLOCK_ALL) goto L23;
        if (r0 != ReflectionAccessFilter$FilterResult.BLOCK_INACCESSIBLE) goto L15;
        boolean r4 = true;
    L17:
        if (ReflectionHelper.isRecord(r3) == false) goto L21;
        return new RecordAdapter(r3, getBoundFields(r8, r9, r3, r4, true), r4);
    L21:
        return new FieldReflectionAdapter(this.constructorConstructor.get(r9, true), getBoundFields(r8, r9, r3, r4, false));
    L15:
        r4 = false;
        goto L17
    L23:
        throw new JsonIOException("ReflectionAccessFilter does not permit using reflection for " + r3 + ". Register a TypeAdapter for this type or adjust the access filter.");
    L9:
        return new AnonymousClass1(this);
    }
}
