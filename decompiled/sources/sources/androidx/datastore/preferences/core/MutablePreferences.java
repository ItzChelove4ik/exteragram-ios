package androidx.datastore.preferences.core;

import androidx.datastore.preferences.core.Preferences;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B-\b\u0000\u0012\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\r\u001a\u00020\nH\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000f\u001a\u00020\nH\u0000¢\u0006\u0004\b\u000e\u0010\fJ&\u0010\u0012\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0015\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00040\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J,\u0010\u0018\u001a\u00020\n\"\u0004\b\u0000\u0010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0017\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\u0018\u0010\u0019J%\u0010\u001b\u001a\u00020\n2\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0004\b\u001a\u0010\u0019J)\u0010\u001f\u001a\u00020\n2\u001a\u0010\u001e\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u001d0\u001c\"\u0006\u0012\u0002\b\u00030\u001d¢\u0006\u0004\b\u001f\u0010 J!\u0010!\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b!\u0010\u0013J\u001a\u0010#\u001a\u00020\u00062\b\u0010\"\u001a\u0004\u0018\u00010\u0004H\u0096\u0002¢\u0006\u0004\b#\u0010$J\u000f\u0010&\u001a\u00020%H\u0016¢\u0006\u0004\b&\u0010'J\u000f\u0010)\u001a\u00020(H\u0016¢\u0006\u0004\b)\u0010*R*\u0010\u0005\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u0010+\u001a\u0004\b,\u0010\u0016R\u0014\u0010.\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/¨\u00060"}, d2 = {"Landroidx/datastore/preferences/core/MutablePreferences;", "Landroidx/datastore/preferences/core/Preferences;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/datastore/preferences/core/Preferences$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "preferencesMap", _UrlKt.FRAGMENT_ENCODE_SET, "startFrozen", "<init>", "(Ljava/util/Map;Z)V", _UrlKt.FRAGMENT_ENCODE_SET, "checkNotFrozen$datastore_preferences_core_release", "()V", "checkNotFrozen", "freeze$datastore_preferences_core_release", "freeze", "T", PluginsConstants.Settings.KEY, "get", "(Landroidx/datastore/preferences/core/Preferences$Key;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "asMap", "()Ljava/util/Map;", "value", "set", "(Landroidx/datastore/preferences/core/Preferences$Key;Ljava/lang/Object;)V", "setUnchecked$datastore_preferences_core_release", "setUnchecked", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/datastore/preferences/core/Preferences$Pair;", "pairs", "putAll", "([Landroidx/datastore/preferences/core/Preferences$Pair;)V", "remove", "other", "equals", "(Ljava/lang/Object;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Ljava/util/Map;", "getPreferencesMap$datastore_preferences_core_release", "Landroidx/datastore/preferences/core/AtomicBoolean;", "frozen", "Landroidx/datastore/preferences/core/AtomicBoolean;", "datastore-preferences-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPreferences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Preferences.kt\nandroidx/datastore/preferences/core/MutablePreferences\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 5 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,361:1\n1#2:362\n1179#3,2:363\n1253#3,4:365\n13579#4,2:369\n167#5,3:371\n*S KotlinDebug\n*F\n+ 1 Preferences.kt\nandroidx/datastore/preferences/core/MutablePreferences\n*L\n158#1:363,2\n158#1:365,4\n250#1:369,2\n283#1:371,3\n*E\n"})
/* loaded from: classes.dex */
public final class MutablePreferences extends Preferences {
    private final AtomicBoolean frozen;
    private final Map<Preferences.Key<?>, Object> preferencesMap;

    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010'\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u00012\u0016\u0010\u0002\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "entry", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/datastore/preferences/core/Preferences$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.datastore.preferences.core.MutablePreferences$toString$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<Map.Entry<Preferences.Key<?>, Object>, CharSequence> {
        public static final AnonymousClass1 INSTANCE = null;

        static {
            INSTANCE = new AnonymousClass1();
        }

        public AnonymousClass1() {
            super(1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final CharSequence invoke2(Map.Entry<Preferences.Key<?>, Object> r11) {
            Object r10 = r11.getValue();
            if ((r10 instanceof byte[]) == false) goto L5;
            String r102 = ArraysKt.joinToString$default((byte[]) r10, ", ", "[", "]", 0, null, null, 56, null);
        L7:
            return "  " + r11.getKey().getName() + " = " + r102;
        L5:
            r102 = String.valueOf(r11.getValue());
            goto L7
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ CharSequence invoke(Map.Entry<Preferences.Key<?>, Object> r1) {
            return invoke2(r1);
        }
    }

    public MutablePreferences(Map<Preferences.Key<?>, Object> r1, boolean r2) {
        this.preferencesMap = r1;
        this.frozen = new AtomicBoolean(r2);
    }

    public /* synthetic */ MutablePreferences(Map r1, boolean r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = new LinkedHashMap();
    L6:
        if ((r3 & 2) == 0) goto L8;
        r2 = true;
    L8:
        this(r1, r2);
    }

    @Override // androidx.datastore.preferences.core.Preferences
    public Map<Preferences.Key<?>, Object> asMap() {
        Set<Map.Entry<Preferences.Key<?>, Object>> r5 = this.preferencesMap.entrySet();
        LinkedHashMap r1 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(r5, 10)), 16));
        Iterator<T> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L11;
        Map.Entry r0 = (Map.Entry) r52.next();
        Object r2 = r0.getValue();
        if ((r2 instanceof byte[]) == false) goto L8;
        byte[] r22 = (byte[]) r2;
        Pair r3 = new Pair(r0.getKey(), Arrays.copyOf(r22, r22.length));
    L9:
        r1.put(r3.getFirst(), r3.getSecond());
        goto L4
    L8:
        r3 = new Pair(r0.getKey(), r0.getValue());
        goto L9
    L11:
        return Actual_jvmAndroidKt.immutableMap(r1);
    }

    public final void checkNotFrozen$datastore_preferences_core_release() {
        if (this.frozen.get() == true) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Do mutate preferences once returned to DataStore.");
    }

    public boolean equals(Object r6) {
        if ((r6 instanceof MutablePreferences) == true) goto L5;
        return false;
    L5:
        MutablePreferences r62 = (MutablePreferences) r6;
        Map<Preferences.Key<?>, Object> r0 = r62.preferencesMap;
        if (r0 != this.preferencesMap) goto L9;
        return true;
    L9:
        if (r0.size() == this.preferencesMap.size()) goto L11;
        return false;
    L11:
        Map<Preferences.Key<?>, Object> r63 = r62.preferencesMap;
        if (r63.isEmpty() == false) goto L14;
        return true;
    L14:
        Iterator<Map.Entry<Preferences.Key<?>, Object>> r64 = r63.entrySet().iterator();
    L16:
        if (r64.hasNext() == false) goto L30;
        Map.Entry<Preferences.Key<?>, Object> r02 = r64.next();
        Object r2 = this.preferencesMap.get(r02.getKey());
        if (r2 == null) goto L27;
        Object r03 = r02.getValue();
        if ((r03 instanceof byte[]) == true) goto L22;
        boolean r04 = Intrinsics.areEqual(r03, r2);
    L28:
        if (r04 == true) goto L16;
        return false;
    L22:
        if ((r2 instanceof byte[]) == false) goto L27;
        if (Arrays.equals((byte[]) r03, (byte[]) r2) == false) goto L27;
        r04 = true;
    L27:
        r04 = false;
        goto L28
    L30:
        return true;
    }

    public final void freeze$datastore_preferences_core_release() {
        this.frozen.set(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.datastore.preferences.core.Preferences
    public <T> T get(Preferences.Key<T> r1) {
        T r0 = (T) this.preferencesMap.get(r1);
        if ((r0 instanceof byte[]) == false) goto L6;
        byte[] r02 = (byte[]) r0;
        return (T) Arrays.copyOf(r02, r02.length);
    L6:
        return r0;
    }

    public int hashCode() {
        Iterator<T> r3 = this.preferencesMap.entrySet().iterator();
        int r0 = 0;
    L4:
        if (r3.hasNext() == false) goto L10;
        Object r1 = ((Map.Entry) r3.next()).getValue();
        if ((r1 instanceof byte[]) == false) goto L8;
        int r12 = Arrays.hashCode((byte[]) r1);
    L9:
        r0 = r0 + r12;
        goto L4
    L8:
        r12 = r1.hashCode();
        goto L9
    L10:
        return r0;
    }

    public final void putAll(Preferences.Pair<?>... r1) {
        checkNotFrozen$datastore_preferences_core_release();
        if (r1.length > 0) goto L5;
        return;
    L5:
        Preferences.Pair<?> r0 = r1[0];
        throw null;
    }

    public final <T> T remove(Preferences.Key<T> r1) {
        checkNotFrozen$datastore_preferences_core_release();
        return (T) this.preferencesMap.remove(r1);
    }

    public final <T> void set(Preferences.Key<T> r1, T r2) {
        setUnchecked$datastore_preferences_core_release(r1, r2);
    }

    public final void setUnchecked$datastore_preferences_core_release(Preferences.Key<?> r2, Object r3) {
        checkNotFrozen$datastore_preferences_core_release();
        if (r3 != null) goto L7;
        remove(r2);
        return;
    L7:
        if ((r3 instanceof Set) == false) goto L10;
        this.preferencesMap.put(r2, Actual_jvmAndroidKt.immutableCopyOfSet((Set) r3));
        return;
    L10:
        boolean r0 = r3 instanceof byte[];
        Map<Preferences.Key<?>, Object> r1 = this.preferencesMap;
        if (r0 == false) goto L14;
        byte[] r32 = (byte[]) r3;
        r1.put(r2, Arrays.copyOf(r32, r32.length));
        return;
    L14:
        r1.put(r2, r3);
    }

    public String toString() {
        return CollectionsKt.joinToString$default(this.preferencesMap.entrySet(), ",\n", "{\n", "\n}", 0, null, AnonymousClass1.INSTANCE, 24, null);
    }
}
