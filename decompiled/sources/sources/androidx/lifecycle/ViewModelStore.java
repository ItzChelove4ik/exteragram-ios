package androidx.lifecycle;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.CharsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\b\u001a\u00020\u00072\b\u0010\u0004\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\b\u0010\tJ\u001c\u0010\n\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001H\u0087\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH\u0007¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\u0007¢\u0006\u0004\b\u000f\u0010\u0003J\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\"\u0010\u0014\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u00050\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Landroidx/lifecycle/ViewModelStore;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", PluginsConstants.Settings.KEY, "Landroidx/lifecycle/ViewModel;", "viewModel", _UrlKt.FRAGMENT_ENCODE_SET, "put", "(Ljava/lang/Object;Landroidx/lifecycle/ViewModel;)V", "get", "(Ljava/lang/Object;)Landroidx/lifecycle/ViewModel;", _UrlKt.FRAGMENT_ENCODE_SET, "keys", "()Ljava/util/Set;", "clear", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "map", "Ljava/util/Map;", "lifecycle-viewmodel"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nViewModelStore.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ViewModelStore.kt\nandroidx/lifecycle/ViewModelStore\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,105:1\n384#2,7:106\n*S KotlinDebug\n*F\n+ 1 ViewModelStore.kt\nandroidx/lifecycle/ViewModelStore\n*L\n69#1:106,7\n*E\n"})
/* loaded from: classes.dex */
public class ViewModelStore {
    private final Map<Object, ViewModel> map;

    public ViewModelStore() {
        this.map = new LinkedHashMap();
    }

    public final void clear() {
        Map r0 = MapsKt.toMap(this.map);
        this.map.clear();
        Iterator r1 = r0.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((ViewModel) r1.next()).clear$lifecycle_viewmodel();
        goto L4
    }

    public final ViewModel get(Object r1) {
        return this.map.get(r1);
    }

    public final Set<Object> keys() {
        return CollectionsKt.toSet(this.map.keySet());
    }

    public final void put(Object r1, ViewModel r2) {
        ViewModel r0 = this.map.put(r1, r2);
        if (r0 == null) goto L6;
        r0.clear$lifecycle_viewmodel();
        return;
    }

    public String toString() {
        String r0 = Reflection.getOrCreateKotlinClass(getClass()).getSimpleName();
        if (r0 != null) goto L6;
        r0 = "ViewModelStore";
    L6:
        return r0 + "@" + Integer.toString(hashCode(), CharsKt.checkRadix(16)) + "(keys=" + keys() + ")";
    }
}
