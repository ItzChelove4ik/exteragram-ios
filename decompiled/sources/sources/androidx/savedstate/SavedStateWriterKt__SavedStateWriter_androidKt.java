package androidx.savedstate;

import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.PublishedApi;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u001e\n\u0000\u001a*\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\b\b\u0000\u0010\u0002*\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0005H\u0001¨\u0006\u0006"}, d2 = {"toArrayListUnsafe", "Ljava/util/ArrayList;", "T", "Lkotlin/collections/ArrayList;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "savedstate"}, k = 5, mv = {2, 0, 0}, xi = 48, xs = "androidx/savedstate/SavedStateWriterKt")
/* loaded from: classes4.dex */
abstract /* synthetic */ class SavedStateWriterKt__SavedStateWriter_androidKt {
    @PublishedApi
    public static final <T> ArrayList<T> toArrayListUnsafe(Collection<?> r1) {
        if ((r1 instanceof ArrayList) == false) goto L7;
        return (ArrayList) r1;
    L7:
        return new ArrayList(r1);
    }
}
