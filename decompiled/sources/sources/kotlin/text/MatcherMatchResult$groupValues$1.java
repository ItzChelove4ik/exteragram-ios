package kotlin.text;

import kotlin.Metadata;
import kotlin.collections.AbstractList;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0004H\u0096\u0082\u0004R\u0015\u0010\u0003\u001a\u00020\u00048VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"kotlin/text/MatcherMatchResult$groupValues$1", "Lkotlin/collections/AbstractList;", _UrlKt.FRAGMENT_ENCODE_SET, "size", _UrlKt.FRAGMENT_ENCODE_SET, "getSize", "()I", "get", "index", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MatcherMatchResult$groupValues$1 extends AbstractList<String> {
    final /* synthetic */ MatcherMatchResult this$0;

    public MatcherMatchResult$groupValues$1(MatcherMatchResult r1) {
        this.this$0 = r1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object r2) {
        if ((r2 instanceof String) == true) goto L7;
        return false;
    L7:
        return contains((String) r2);
    }

    public /* bridge */ boolean contains(String r1) {
        return super.contains(r1);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int r1) {
        return get(r1);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public String get(int r1) {
        String r0 = MatcherMatchResult.access$getMatchResult(this.this$0).group(r1);
        if (r0 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return r0;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return MatcherMatchResult.access$getMatchResult(this.this$0).groupCount() + 1;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object r2) {
        if ((r2 instanceof String) == true) goto L7;
        return -1;
    L7:
        return indexOf((String) r2);
    }

    public /* bridge */ int indexOf(String r1) {
        return super.indexOf(r1);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object r2) {
        if ((r2 instanceof String) == true) goto L7;
        return -1;
    L7:
        return lastIndexOf((String) r2);
    }

    public /* bridge */ int lastIndexOf(String r1) {
        return super.lastIndexOf(r1);
    }
}
