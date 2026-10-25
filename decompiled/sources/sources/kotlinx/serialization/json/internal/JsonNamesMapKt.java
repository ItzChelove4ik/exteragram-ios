package kotlinx.serialization.json.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.internal.JsonInternalDependenciesKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonIgnoreUnknownKeys;
import kotlinx.serialization.json.JsonNames;
import kotlinx.serialization.json.JsonNamingStrategy;
import kotlinx.serialization.json.JsonSchemaCacheKt;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\u001a'\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a'\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003*\u00020\u00012\u0006\u0010\b\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\t\u0010\n\u001a#\u0010\f\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\f\u0010\r\u001a!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001d\u0010\u0012\u001a\u0004\u0018\u00010\u0011*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a#\u0010\u0015\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u001b\u0010\u0018\u001a\u00020\u0017*\u00020\u00012\u0006\u0010\b\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0018\u0010\u0019\u001a#\u0010\u001a\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u001a\u0010\u0016\u001a\u001b\u0010\u001b\u001a\u00020\u0017*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u001b\u0010\u001c\",\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u001d8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"&\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\"0\u001d8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b#\u0010\u001f\u001a\u0004\b$\u0010!¨\u0006%"}, d2 = {"Lkotlinx/serialization/descriptors/SerialDescriptor;", "Lkotlinx/serialization/json/Json;", "json", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "buildDeserializationNamesMap", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/Json;)Ljava/util/Map;", "descriptor", "deserializationNamesMap", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/descriptors/SerialDescriptor;)Ljava/util/Map;", "index", "getJsonElementName", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/Json;I)Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "getJsonEncodedNames", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/Json;)Ljava/util/Set;", "Lkotlinx/serialization/json/JsonNamingStrategy;", "namingStrategy", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/Json;)Lkotlinx/serialization/json/JsonNamingStrategy;", "name", "getJsonNameIndexSlowPath", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/Json;Ljava/lang/String;)I", _UrlKt.FRAGMENT_ENCODE_SET, "decodeCaseInsensitive", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/descriptors/SerialDescriptor;)Z", "getJsonNameIndex", "ignoreUnknownKeys", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/Json;)Z", "Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", "JsonDeserializationNamesKey", "Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", "getJsonDeserializationNamesKey", "()Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "JsonSerializationNamesKey", "getJsonSerializationNamesKey", "kotlinx-serialization-json"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJsonNamesMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonNamesMap.kt\nkotlinx/serialization/json/internal/JsonNamesMapKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,167:1\n812#2,12:168\n1807#2,3:183\n14048#3,2:180\n1#4:182\n*S KotlinDebug\n*F\n+ 1 JsonNamesMap.kt\nkotlinx/serialization/json/internal/JsonNamesMapKt\n*L\n35#1:168,12\n166#1:183,3\n35#1:180,2\n*E\n"})
/* loaded from: classes.dex */
public abstract class JsonNamesMapKt {
    private static final DescriptorSchemaCache.Key<Map<String, Integer>> JsonDeserializationNamesKey = null;
    private static final DescriptorSchemaCache.Key<String[]> JsonSerializationNamesKey = null;

    public static Map $r8$lambda$0Wrxba8wq6selDNjX8p_NiECb3A(SerialDescriptor r0, Json r1) {
        return buildDeserializationNamesMap(r0, r1);
    }

    static {
        JsonDeserializationNamesKey = new DescriptorSchemaCache.Key();
        JsonSerializationNamesKey = new DescriptorSchemaCache.Key();
    }

    private static final Map<String, Integer> buildDeserializationNamesMap(SerialDescriptor r9, Json r10) {
        LinkedHashMap r0 = new LinkedHashMap();
        boolean r1 = decodeCaseInsensitive(r10, r9);
        namingStrategy(r9, r10);
        int r102 = r9.getElementsCount();
        int r3 = 0;
    L3:
        if (r3 >= r102) goto L27;
        List<Annotation> r4 = r9.getElementAnnotations(r3);
        ArrayList r5 = new ArrayList();
        Iterator<T> r42 = r4.iterator();
    L6:
        if (r42.hasNext() == false) goto L10;
        Object r6 = r42.next();
        if ((r6 instanceof JsonNames) == false) goto L6;
        r5.add(r6);
        goto L6
    L10:
        JsonNames r43 = (JsonNames) CollectionsKt.singleOrNull(r5);
        if (r43 == null) goto L20;
        String[] r44 = r43.names();
        if (r44 == null) goto L20;
        int r52 = r44.length;
        int r62 = 0;
    L15:
        if (r62 >= r52) goto L20;
        String r7 = r44[r62];
        if (r1 == false) goto L19;
        r7 = r7.toLowerCase(Locale.ROOT);
    L19:
        buildDeserializationNamesMap$putOrThrow(r0, r9, r7, r3);
        r62 = r62 + 1;
    L20:
        if (r1 == false) goto L22;
        String r45 = r9.getElementName(r3).toLowerCase(Locale.ROOT);
    L23:
        if (r45 == null) goto L25;
        buildDeserializationNamesMap$putOrThrow(r0, r9, r45, r3);
    L25:
        r3 = r3 + 1;
        goto L3
    L22:
        r45 = null;
        goto L23
    L27:
        if (r0.isEmpty() == true) goto L29;
        return r0;
    L29:
        return MapsKt.emptyMap();
    }

    private static final void buildDeserializationNamesMap$putOrThrow(Map<String, Integer> r3, SerialDescriptor r4, String r5, int r6) {
        if (Intrinsics.areEqual(r4.getKind(), SerialKind.ENUM.INSTANCE) == false) goto L5;
        String r0 = "enum value";
    L7:
        if (r3.containsKey(r5) == true) goto L11;
        r3.put(r5, Integer.valueOf(r6));
        return;
    L11:
        throw JsonExceptionsKt.decodingExceptionOf("The suggested name '" + r5 + "' for " + r0 + ' ' + r4.getElementName(r6) + " is already one of the names for " + r0 + ' ' + r4.getElementName(((Number) MapsKt.getValue(r3, r5)).intValue()) + " in " + r4);
    L5:
        r0 = "property";
        goto L7
    }

    private static final boolean decodeCaseInsensitive(Json r0, SerialDescriptor r1) {
        if (r0.getConfiguration().getDecodeEnumsCaseInsensitive() == true) goto L5;
        return false;
    L5:
        if (Intrinsics.areEqual(r1.getKind(), SerialKind.ENUM.INSTANCE) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static final Map<String, Integer> deserializationNamesMap(Json r3, SerialDescriptor r4) {
        return (Map) JsonSchemaCacheKt.getSchemaCache(r3).getOrPut(r4, JsonDeserializationNamesKey, new JsonNamesMapKt$$ExternalSyntheticLambda0(r4, r3));
    }

    public static final String getJsonElementName(SerialDescriptor r0, Json r1, int r2) {
        namingStrategy(r0, r1);
        return r0.getElementName(r2);
    }

    public static final Set<String> getJsonEncodedNames(SerialDescriptor r0, Json r1) {
        namingStrategy(r0, r1);
        return JsonInternalDependenciesKt.jsonCachedSerialNames(r0);
    }

    public static final int getJsonNameIndex(SerialDescriptor r2, Json r3, String r4) {
        if (decodeCaseInsensitive(r3, r2) == true) goto L5;
        namingStrategy(r2, r3);
        int r0 = r2.getElementIndex(r4);
        if (r0 == (-3)) goto L10;
    L11:
        return r0;
    L10:
        if (r3.getConfiguration().getUseAlternativeNames() == false) goto L11;
        return getJsonNameIndexSlowPath(r2, r3, r4);
    L5:
        return getJsonNameIndexSlowPath(r2, r3, r4.toLowerCase(Locale.ROOT));
    }

    private static final int getJsonNameIndexSlowPath(SerialDescriptor r0, Json r1, String r2) {
        Integer r02 = deserializationNamesMap(r1, r0).get(r2);
        if (r02 != null) goto L5;
        return -3;
    L5:
        return r02.intValue();
    }

    public static final boolean ignoreUnknownKeys(SerialDescriptor r0, Json r1) {
        if (r1.getConfiguration().getIgnoreUnknownKeys() == true) goto L17;
        List<Annotation> r02 = r0.getAnnotations();
        if ((r02 instanceof Collection) == true) goto L7;
    L9:
        Iterator<T> r03 = r02.iterator();
    L11:
        if (r03.hasNext() == false) goto L22;
        if ((((Annotation) r03.next()) instanceof JsonIgnoreUnknownKeys) == false) goto L11;
        return true;
    L22:
        return false;
    L7:
        if (r02.isEmpty() == false) goto L9;
        return false;
    L17:
        return true;
    }

    public static final JsonNamingStrategy namingStrategy(SerialDescriptor r1, Json r2) {
        if (Intrinsics.areEqual(r1.getKind(), StructureKind.CLASS.INSTANCE) == false) goto L5;
        r2.getConfiguration().getNamingStrategy();
    L5:
        return null;
    }
}
