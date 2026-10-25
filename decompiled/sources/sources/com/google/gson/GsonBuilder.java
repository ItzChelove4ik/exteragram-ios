package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DefaultDateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.NumberTypeAdapter;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.internal.sql.SqlTypesSupport;
import com.google.gson.reflect.TypeToken;
import com.sun.jna.Native$$ExternalSyntheticBUOutline2;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* loaded from: classes.dex */
public final class GsonBuilder {
    static final GsonBuilder DEFAULT = null;
    static final ConstructorConstructor DEFAULT_CONSTRUCTOR_CONSTRUCTOR = null;
    private static final String DEFAULT_DATE_PATTERN = null;
    private static final FieldNamingStrategy DEFAULT_FIELD_NAMING_STRATEGY = null;
    private static final FormattingStyle DEFAULT_FORMATTING_STYLE = null;
    static final JsonAdapterAnnotationTypeAdapterFactory DEFAULT_JSON_ADAPTER_ANNOTATION_TYPE_ADAPTER_FACTORY = null;
    private static final ToNumberStrategy DEFAULT_NUMBER_TO_NUMBER_STRATEGY = null;
    private static final ToNumberStrategy DEFAULT_OBJECT_TO_NUMBER_STRATEGY = null;
    private static final Strictness DEFAULT_STRICTNESS = null;
    static final List<TypeAdapterFactory> DEFAULT_TYPE_ADAPTER_FACTORIES = null;
    boolean complexMapKeySerialization;
    String datePattern;
    int dateStyle;
    boolean escapeHtmlChars;
    Excluder excluder;
    final List<TypeAdapterFactory> factories;
    FieldNamingStrategy fieldNamingPolicy;
    FormattingStyle formattingStyle;
    boolean generateNonExecutableJson;
    final List<TypeAdapterFactory> hierarchyFactories;
    final Map<Type, Object> instanceCreators;
    LongSerializationPolicy longSerializationPolicy;
    ToNumberStrategy numberToNumberStrategy;
    ToNumberStrategy objectToNumberStrategy;
    final ArrayDeque<Object> reflectionFilters;
    boolean serializeNulls;
    boolean serializeSpecialFloatingPointValues;
    Strictness strictness;
    int timeStyle;
    boolean useJdkUnsafe;

    static {
        DEFAULT_FORMATTING_STYLE = FormattingStyle.COMPACT;
        DEFAULT_DATE_PATTERN = null;
        DEFAULT_FIELD_NAMING_STRATEGY = FieldNamingPolicy.IDENTITY;
        DEFAULT_OBJECT_TO_NUMBER_STRATEGY = ToNumberPolicy.DOUBLE;
        DEFAULT_NUMBER_TO_NUMBER_STRATEGY = ToNumberPolicy.LAZILY_PARSED_NUMBER;
        ConstructorConstructor r0 = new ConstructorConstructor(Collections.EMPTY_MAP, true, Collections.EMPTY_LIST);
        DEFAULT_CONSTRUCTOR_CONSTRUCTOR = r0;
        JsonAdapterAnnotationTypeAdapterFactory r1 = new JsonAdapterAnnotationTypeAdapterFactory(r0);
        DEFAULT_JSON_ADAPTER_ANNOTATION_TYPE_ADAPTER_FACTORY = r1;
        GsonBuilder r2 = new GsonBuilder();
        DEFAULT = r2;
        DEFAULT_TYPE_ADAPTER_FACTORIES = r2.createFactories(r0, r1);
    }

    public GsonBuilder() {
        this.excluder = Excluder.DEFAULT;
        this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
        this.fieldNamingPolicy = DEFAULT_FIELD_NAMING_STRATEGY;
        this.instanceCreators = new HashMap();
        this.factories = new ArrayList();
        this.hierarchyFactories = new ArrayList();
        this.serializeNulls = false;
        this.datePattern = DEFAULT_DATE_PATTERN;
        this.dateStyle = 2;
        this.timeStyle = 2;
        this.complexMapKeySerialization = false;
        this.serializeSpecialFloatingPointValues = false;
        this.escapeHtmlChars = true;
        this.formattingStyle = DEFAULT_FORMATTING_STYLE;
        this.generateNonExecutableJson = false;
        this.strictness = DEFAULT_STRICTNESS;
        this.useJdkUnsafe = true;
        this.objectToNumberStrategy = DEFAULT_OBJECT_TO_NUMBER_STRATEGY;
        this.numberToNumberStrategy = DEFAULT_NUMBER_TO_NUMBER_STRATEGY;
        this.reflectionFilters = new ArrayDeque();
    }

    private void addDateTypeAdapters(List<TypeAdapterFactory> r6) {
        boolean r0 = SqlTypesSupport.SUPPORTS_SQL_TYPES;
        String r1 = this.datePattern;
        TypeAdapterFactory r2 = null;
        if (r1 != null) goto L5;
    L10:
        int r12 = this.dateStyle;
        if (r12 == 2) goto L13;
    L14:
        TypeAdapterFactory r13 = DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory(r12, this.timeStyle);
        if (r0 == true) goto L16;
    L9:
        TypeAdapterFactory r5 = null;
    L17:
        r6.add(r13);
        if (r0 == false) goto L22;
        r6.add(r2);
        r6.add(r5);
        return;
    L22:
        return;
    L16:
        r2 = SqlTypesSupport.TIMESTAMP_DATE_TYPE.createAdapterFactory(this.dateStyle, this.timeStyle);
        r5 = SqlTypesSupport.DATE_DATE_TYPE.createAdapterFactory(this.dateStyle, this.timeStyle);
        goto L17
    L13:
        if (this.timeStyle != 2) goto L14;
        return;
    L5:
        if (r1.trim().isEmpty() == true) goto L10;
        r13 = DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory(this.datePattern);
        if (r0 == false) goto L9;
        r2 = SqlTypesSupport.TIMESTAMP_DATE_TYPE.createAdapterFactory(this.datePattern);
        r5 = SqlTypesSupport.DATE_DATE_TYPE.createAdapterFactory(this.datePattern);
        goto L17
    }

    private void addUserDefinedAdapters(List<TypeAdapterFactory> r3) {
        if (this.factories.isEmpty() == true) goto L6;
        ArrayList r0 = new ArrayList(this.factories);
        Collections.reverse(r0);
        r3.addAll(r0);
    L6:
        if (this.hierarchyFactories.isEmpty() == true) goto L9;
        ArrayList r02 = new ArrayList(this.hierarchyFactories);
        Collections.reverse(r02);
        r3.addAll(r02);
        return;
    }

    private TypeAdapter<Number> doubleAdapter() {
        if (this.serializeSpecialFloatingPointValues == false) goto L7;
        return TypeAdapters.DOUBLE;
    L7:
        return TypeAdapters.DOUBLE_STRICT;
    }

    private TypeAdapter<Number> floatAdapter() {
        if (this.serializeSpecialFloatingPointValues == false) goto L7;
        return TypeAdapters.FLOAT;
    L7:
        return TypeAdapters.FLOAT_STRICT;
    }

    private static boolean hasNonOverridableAdapter(Type r1) {
        if (r1 != Object.class) goto L6;
        return true;
    L6:
        return false;
    }

    public static <E> List<E> newImmutableList(Collection<E> r2) {
        if (r2.isEmpty() == false) goto L7;
        return Collections.EMPTY_LIST;
    L7:
        if (r2.size() != 1) goto L15;
        if ((r2 instanceof List) == false) goto L11;
        Object r22 = ((List) r2).get(0);
    L13:
        return Collections.singletonList(r22);
    L11:
        r22 = r2.iterator().next();
        goto L13
    L15:
        return Collections.unmodifiableList(Arrays.asList(r2.toArray()));
    }

    public GsonBuilder addSerializationExclusionStrategy(ExclusionStrategy r4) {
        Objects.requireNonNull(r4);
        this.excluder = this.excluder.withExclusionStrategy(r4, true, false);
        return this;
    }

    public Gson create() {
        return new Gson(this);
    }

    public List<TypeAdapterFactory> createFactories(ConstructorConstructor r9, JsonAdapterAnnotationTypeAdapterFactory r10) {
        ArrayList r0 = new ArrayList();
        r0.add(TypeAdapters.JSON_ELEMENT_FACTORY);
        r0.add(ObjectTypeAdapter.getFactory(this.objectToNumberStrategy));
        r0.add(this.excluder);
        addUserDefinedAdapters(r0);
        addDateTypeAdapters(r0);
        r0.add(TypeAdapters.STRING_FACTORY);
        r0.add(TypeAdapters.INTEGER_FACTORY);
        r0.add(TypeAdapters.BOOLEAN_FACTORY);
        r0.add(TypeAdapters.BYTE_FACTORY);
        r0.add(TypeAdapters.SHORT_FACTORY);
        TypeAdapter<Number> r1 = this.longSerializationPolicy.typeAdapter();
        r0.add(TypeAdapters.newFactory(Long.TYPE, Long.class, r1));
        TypeAdapter<Number> r3 = doubleAdapter();
        r0.add(TypeAdapters.newFactory(Double.TYPE, Double.class, r3));
        TypeAdapter<Number> r32 = floatAdapter();
        r0.add(TypeAdapters.newFactory(Float.TYPE, Float.class, r32));
        r0.add(NumberTypeAdapter.getFactory(this.numberToNumberStrategy));
        r0.add(TypeAdapters.ATOMIC_INTEGER_FACTORY);
        r0.add(TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
        r0.add(TypeAdapters.newFactory(AtomicLong.class, TypeAdapters.atomicLongAdapter(r1)));
        r0.add(TypeAdapters.newFactory(AtomicLongArray.class, TypeAdapters.atomicLongArrayAdapter(r1)));
        r0.add(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY);
        r0.add(TypeAdapters.CHARACTER_FACTORY);
        r0.add(TypeAdapters.STRING_BUILDER_FACTORY);
        r0.add(TypeAdapters.STRING_BUFFER_FACTORY);
        r0.add(TypeAdapters.BIG_DECIMAL_FACTORY);
        r0.add(TypeAdapters.BIG_INTEGER_FACTORY);
        r0.add(TypeAdapters.LAZILY_PARSED_NUMBER_FACTORY);
        r0.add(TypeAdapters.URL_FACTORY);
        r0.add(TypeAdapters.URI_FACTORY);
        r0.add(TypeAdapters.UUID_FACTORY);
        r0.add(TypeAdapters.CURRENCY_FACTORY);
        r0.add(TypeAdapters.LOCALE_FACTORY);
        r0.add(TypeAdapters.INET_ADDRESS_FACTORY);
        r0.add(TypeAdapters.BIT_SET_FACTORY);
        r0.add(DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY);
        r0.add(TypeAdapters.CALENDAR_FACTORY);
        TypeAdapterFactory r12 = TypeAdapters.javaTimeTypeAdapterFactory();
        if (r12 == null) goto L5;
        r0.add(r12);
    L5:
        r0.addAll(SqlTypesSupport.SQL_TYPE_FACTORIES);
        r0.add(ArrayTypeAdapter.FACTORY);
        r0.add(TypeAdapters.CLASS_FACTORY);
        r0.add(new CollectionTypeAdapterFactory(r9));
        r0.add(new MapTypeAdapterFactory(r9, this.complexMapKeySerialization));
        r0.add(r10);
        r0.add(TypeAdapters.ENUM_FACTORY);
        r0.add(new ReflectiveTypeAdapterFactory(r9, this.fieldNamingPolicy, this.excluder, r10, newImmutableList(this.reflectionFilters)));
        r0.trimToSize();
        return Collections.unmodifiableList(r0);
    }

    public GsonBuilder registerTypeAdapter(Type r4, Object r5) {
        Objects.requireNonNull(r4);
        Objects.requireNonNull(r5);
        boolean r0 = r5 instanceof JsonSerializer;
        if (r0 == true) goto L10;
        if ((r5 instanceof TypeAdapter) == true) goto L10;
        Native$$ExternalSyntheticBUOutline2.m("Class ", r5.getClass().getName(), " does not implement any supported type adapter class or interface");
        return null;
    L10:
        if (hasNonOverridableAdapter(r4) == true) goto L18;
        if (r0 == false) goto L15;
        TypeToken<?> r02 = TypeToken.get(r4);
        this.factories.add(TreeTypeAdapter.newFactoryWithMatchRawType(r02, r5));
    L15:
        if ((r5 instanceof TypeAdapter) == false) goto L17;
        TypeAdapterFactory r42 = TypeAdapters.newFactory(TypeToken.get(r4), (TypeAdapter) r5);
        this.factories.add(r42);
    L17:
        return this;
    L18:
        Native$$ExternalSyntheticBUOutline5.m("Cannot override built-in adapter for ", r4);
        return null;
    }

    public GsonBuilder registerTypeAdapterFactory(TypeAdapterFactory r2) {
        Objects.requireNonNull(r2);
        this.factories.add(r2);
        return this;
    }

    public GsonBuilder registerTypeHierarchyAdapter(Class<?> r3, Object r4) {
        Objects.requireNonNull(r3);
        Objects.requireNonNull(r4);
        boolean r0 = r4 instanceof JsonSerializer;
        if (r0 == false) goto L5;
    L9:
        if (r0 == false) goto L12;
        this.hierarchyFactories.add(TreeTypeAdapter.newTypeHierarchyFactory(r3, r4));
    L12:
        if ((r4 instanceof TypeAdapter) == false) goto L14;
        TypeAdapterFactory r32 = TypeAdapters.newTypeHierarchyFactory(r3, (TypeAdapter) r4);
        this.factories.add(r32);
    L14:
        return this;
    L5:
        if ((r4 instanceof TypeAdapter) == true) goto L9;
        Native$$ExternalSyntheticBUOutline2.m("Class ", r4.getClass().getName(), " does not implement any supported type adapter class or interface");
        return null;
    }

    public GsonBuilder serializeSpecialFloatingPointValues() {
        this.serializeSpecialFloatingPointValues = true;
        return this;
    }

    public GsonBuilder setFormattingStyle(FormattingStyle r1) {
        Objects.requireNonNull(r1);
        this.formattingStyle = r1;
        return this;
    }

    public GsonBuilder setPrettyPrinting() {
        return setFormattingStyle(FormattingStyle.PRETTY);
    }
}
