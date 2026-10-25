package androidx.datastore.migrations;

import android.content.SharedPreferences;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\fR\u001c\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/datastore/migrations/SharedPreferencesView;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/content/SharedPreferences;", "prefs", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "keySet", "<init>", "(Landroid/content/SharedPreferences;Ljava/util/Set;)V", _UrlKt.FRAGMENT_ENCODE_SET, "getAll", "()Ljava/util/Map;", "Landroid/content/SharedPreferences;", "Ljava/util/Set;", "datastore_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSharedPreferencesMigration.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SharedPreferencesMigration.android.kt\nandroidx/datastore/migrations/SharedPreferencesView\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,323:1\n515#2:324\n500#2,6:325\n442#2:331\n392#2:332\n1238#3,4:333\n1#4:337\n*S KotlinDebug\n*F\n+ 1 SharedPreferencesMigration.android.kt\nandroidx/datastore/migrations/SharedPreferencesView\n*L\n303#1:324\n303#1:325,6\n305#1:331\n305#1:332\n305#1:333,4\n*E\n"})
/* loaded from: classes3.dex */
public final class SharedPreferencesView {
    private final Set<String> keySet;
    private final SharedPreferences prefs;

    public SharedPreferencesView(SharedPreferences r1, Set<String> r2) {
        this.prefs = r1;
        this.keySet = r2;
    }

    public final Map<String, Object> getAll() {
        Map<String, ?> r0 = this.prefs.getAll();
        LinkedHashMap r1 = new LinkedHashMap();
        Iterator<Map.Entry<String, ?>> r02 = r0.entrySet().iterator();
    L4:
        if (r02.hasNext() == false) goto L11;
        Map.Entry<String, ?> r2 = r02.next();
        String r3 = r2.getKey();
        Set<String> r4 = this.keySet;
        if (r4 == null) goto L8;
        boolean r32 = r4.contains(r3);
    L9:
        if (r32 == false) goto L4;
        r1.put(r2.getKey(), r2.getValue());
        goto L4
    L8:
        r32 = true;
        goto L9
    L11:
        LinkedHashMap r5 = new LinkedHashMap(MapsKt.mapCapacity(r1.size()));
        Iterator r03 = r1.entrySet().iterator();
    L13:
        if (r03.hasNext() == false) goto L18;
        Map.Entry r12 = (Map.Entry) r03.next();
        Object r22 = r12.getKey();
        Object r13 = r12.getValue();
        if ((r13 instanceof Set) == false) goto L17;
        r13 = CollectionsKt.toSet((Iterable) r13);
    L17:
        r5.put(r22, r13);
        goto L13
    L18:
        return r5;
    }
}
