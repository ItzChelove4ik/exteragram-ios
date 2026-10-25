package androidx.savedstate;

import android.os.Bundle;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0004\b\u0087@\u0018\u00002\u00020\u0001B\u0015\b\u0001\u0012\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\u000f\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0011\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00032\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0010\u0010\u000eJ\r\u0010\u0015\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0018\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u0007H\u0086\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0019¢\u0006\u0004\b\u001a\u0010\u001b\u0088\u0001\u0004\u0092\u0001\u00060\u0002j\u0002`\u0003¨\u0006\u001d"}, d2 = {"Landroidx/savedstate/SavedStateReader;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "source", "constructor-impl", "(Landroid/os/Bundle;)Landroid/os/Bundle;", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "getStringListOrNull-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/util/List;", "getStringListOrNull", "getSavedState-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Landroid/os/Bundle;", "getSavedState", "getSavedStateOrNull-impl", "getSavedStateOrNull", _UrlKt.FRAGMENT_ENCODE_SET, "isEmpty-impl", "(Landroid/os/Bundle;)Z", "isEmpty", "contains-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Z", "contains", _UrlKt.FRAGMENT_ENCODE_SET, "toMap-impl", "(Landroid/os/Bundle;)Ljava/util/Map;", "toMap", "savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JvmInline
@SourceDebugExtension({"SMAP\nSavedStateReader.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SavedStateReader.android.kt\nandroidx/savedstate/SavedStateReader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,914:1\n651#1:915\n687#1:916\n508#1:917\n543#1:918\n1#2:919\n*S KotlinDebug\n*F\n+ 1 SavedStateReader.android.kt\nandroidx/savedstate/SavedStateReader\n*L\n419#1:915\n424#1:916\n454#1:917\n459#1:918\n*E\n"})
/* loaded from: classes4.dex */
public abstract class SavedStateReader {
    @PublishedApi
    /* renamed from: constructor-impl, reason: not valid java name */
    public static Bundle m949constructorimpl(Bundle r0) {
        return r0;
    }

    /* renamed from: contains-impl, reason: not valid java name */
    public static final boolean m950containsimpl(Bundle r0, String r1) {
        return r0.containsKey(r1);
    }

    /* renamed from: getSavedState-impl, reason: not valid java name */
    public static final Bundle m951getSavedStateimpl(Bundle r0, String r1) {
        Bundle r02 = r0.getBundle(r1);
        if (r02 == null) goto L5;
        return r02;
    L5:
        SavedStateReaderKt.keyOrValueNotFoundError(r1);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    /* renamed from: getSavedStateOrNull-impl, reason: not valid java name */
    public static final Bundle m952getSavedStateOrNullimpl(Bundle r0, String r1) {
        return r0.getBundle(r1);
    }

    /* renamed from: getStringListOrNull-impl, reason: not valid java name */
    public static final List<String> m953getStringListOrNullimpl(Bundle r0, String r1) {
        return r0.getStringArrayList(r1);
    }

    /* renamed from: isEmpty-impl, reason: not valid java name */
    public static final boolean m954isEmptyimpl(Bundle r0) {
        return r0.isEmpty();
    }

    /* renamed from: toMap-impl, reason: not valid java name */
    public static final Map<String, Object> m955toMapimpl(Bundle r4) {
        Map r0 = MapsKt.createMapBuilder(r4.size());
        Iterator<String> r1 = r4.keySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L7;
        String r2 = r1.next();
        r0.put(r2, r4.get(r2));
        goto L4
    L7:
        return MapsKt.build(r0);
    }
}
