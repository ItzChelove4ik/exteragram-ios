package kotlinx.serialization.json.internal;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.descriptors.SerialDescriptor;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0016B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J7\u0010\t\u001a\u00020\n\"\b\b\u0000\u0010\u000b*\u00020\u00012\u0006\u0010\f\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u00072\u0006\u0010\u000e\u001a\u0002H\u000bH\u0086\u0082\u0004¢\u0006\u0002\u0010\u000fJ=\u0010\u0010\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\u00012\u0006\u0010\f\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u00072\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0012H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0013J1\u0010\u0014\u001a\u0004\u0018\u0001H\u000b\"\b\b\u0000\u0010\u000b*\u00020\u00012\u0006\u0010\f\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0007H\u0086\u0082\u0004¢\u0006\u0002\u0010\u0015R7\u0010\u0004\u001a*\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0007\u0012\u0004\u0012\u00020\u00010\u0005j\b\u0012\u0004\u0012\u00020\u0001`\b0\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lkotlinx/serialization/json/internal/DescriptorSchemaCache;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "map", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/descriptors/SerialDescriptor;", "Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", "Lkotlinx/serialization/json/internal/DescriptorData;", "set", _UrlKt.FRAGMENT_ENCODE_SET, "T", "descriptor", PluginsConstants.Settings.KEY, "value", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;Ljava/lang/Object;)V", "getOrPut", "defaultValue", "Lkotlin/Function0;", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "get", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;)Ljava/lang/Object;", "Key", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSchemaCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SchemaCache.kt\nkotlinx/serialization/json/internal/DescriptorSchemaCache\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,53:1\n410#2,7:54\n1#3:61\n*S KotlinDebug\n*F\n+ 1 SchemaCache.kt\nkotlinx/serialization/json/internal/DescriptorSchemaCache\n*L\n25#1:54,7\n*E\n"})
/* loaded from: classes.dex */
public final class DescriptorSchemaCache {
    private final Map<SerialDescriptor, Map<Key<Object>, Object>> map;

    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\t\bF¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Key<T> {
        public Key() {
        }
    }

    public DescriptorSchemaCache() {
        this.map = CreateMapForCacheKt.createMapForCache(16);
    }

    public final <T> T get(SerialDescriptor r1, Key<T> r2) {
        Map<Key<Object>, Object> r0 = this.map.get(r1);
        if (r0 == null) goto L5;
        T r02 = (T) r0.get(r2);
    L6:
        if (r02 != null) goto L8;
        return null;
    L8:
        return r02;
    L5:
        r02 = null;
        goto L6
    }

    public final <T> T getOrPut(SerialDescriptor r2, Key<T> r3, Function0<? extends T> r4) {
        T r0 = (T) get(r2, r3);
        if (r0 == null) goto L5;
        return r0;
    L5:
        T r42 = r4.invoke();
        set(r2, r3, r42);
        return r42;
    }

    public final <T> void set(SerialDescriptor r2, Key<T> r3, T r4) {
        Map<SerialDescriptor, Map<Key<Object>, Object>> r1 = this.map;
        Map<Key<Object>, Object> r0 = r1.get(r2);
        if (r0 != null) goto L5;
        r0 = CreateMapForCacheKt.createMapForCache(2);
        r1.put(r2, r0);
    L5:
        r0.put(r3, r4);
    }
}
