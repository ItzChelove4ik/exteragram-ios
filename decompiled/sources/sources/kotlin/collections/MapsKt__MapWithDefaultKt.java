package kotlin.collections;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt___RangesKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\u001a5\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u0081\u0080\u0004¢\u0006\u0004\b\u0005\u0010\u0006\u001aU\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032!\u0010\b\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u0002H\u00010\tH\u0086\u0080\u0004\u001aZ\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\f2!\u0010\b\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u0002H\u00010\tH\u0087\u0080\u0004¢\u0006\u0002\b\r¨\u0006\u000e"}, d2 = {"getOrImplicitDefault", "V", "K", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "getOrImplicitDefaultNullable", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "withDefault", "defaultValue", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "withDefaultMutable", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/collections/MapsKt")
@SourceDebugExtension({"SMAP\nMapWithDefault.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MapWithDefault.kt\nkotlin/collections/MapsKt__MapWithDefaultKt\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,111:1\n376#2,6:112\n*S KotlinDebug\n*F\n+ 1 MapWithDefault.kt\nkotlin/collections/MapsKt__MapWithDefaultKt\n*L\n24#1:112,6\n*E\n"})
/* loaded from: classes.dex */
class MapsKt__MapWithDefaultKt {
    public MapsKt__MapWithDefaultKt() {
    }

    @PublishedApi
    @JvmName(name = "getOrImplicitDefaultNullable")
    public static final <K, V> V getOrImplicitDefaultNullable(Map<K, ? extends V> r1, K r2) {
        if ((r1 instanceof MapWithDefault) == true) goto L5;
        V r0 = r1.get(r2);
        if (r0 == null) goto L9;
    L13:
        return r0;
    L9:
        if (r1.containsKey(r2) == true) goto L13;
        RangesKt___RangesKt$$ExternalSyntheticBUOutline0.m("Key ", r2, " is missing in the map.");
        return null;
    L5:
        return (V) ((MapWithDefault) r1).getOrImplicitDefault(r2);
    }

    public static final <K, V> Map<K, V> withDefault(Map<K, ? extends V> r1, Function1<? super K, ? extends V> r2) {
        if ((r1 instanceof MapWithDefault) == false) goto L7;
        return withDefault(((MapWithDefault) r1).getMap(), r2);
    L7:
        return new MapWithDefaultImpl(r1, r2);
    }

    @JvmName(name = "withDefaultMutable")
    public static final <K, V> Map<K, V> withDefaultMutable(Map<K, V> r1, Function1<? super K, ? extends V> r2) {
        if ((r1 instanceof MutableMapWithDefault) == false) goto L7;
        return withDefaultMutable(((MutableMapWithDefault) r1).getMap(), r2);
    L7:
        return new MutableMapWithDefaultImpl(r1, r2);
    }
}
