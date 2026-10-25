package kotlin.text;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.AbstractCollection;
import kotlin.collections.CollectionsKt;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.ranges.IntRange;
import kotlin.sequences.SequencesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004J\u0012\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000bH\u0096\u0082\u0004J\u0014\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u0005H\u0096\u0082\u0004J\u0014\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0096\u0082\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"kotlin/text/MatcherMatchResult$groups$1", "Lkotlin/text/MatchNamedGroupCollection;", "Lkotlin/collections/AbstractCollection;", "Lkotlin/text/MatchGroup;", "size", _UrlKt.FRAGMENT_ENCODE_SET, "getSize", "()I", "isEmpty", _UrlKt.FRAGMENT_ENCODE_SET, "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "get", "index", "name", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MatcherMatchResult$groups$1 extends AbstractCollection<MatchGroup> implements MatchNamedGroupCollection {
    final /* synthetic */ MatcherMatchResult this$0;

    public static MatchGroup $r8$lambda$6qTiT2TOre74mkZ4SBVT0fE9ajA(MatcherMatchResult$groups$1 r0, int r1) {
        return r0.get(r1);
    }

    public MatcherMatchResult$groups$1(MatcherMatchResult r1) {
        this.this$0 = r1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object r2) {
        if (r2 != null) goto L4;
        boolean r0 = true;
    L5:
        if (r0 == true) goto L9;
        return false;
    L9:
        return contains((MatchGroup) r2);
    L4:
        r0 = r2 instanceof MatchGroup;
        goto L5
    }

    public /* bridge */ boolean contains(MatchGroup r1) {
        return super.contains(r1);
    }

    @Override // kotlin.text.MatchGroupCollection
    public MatchGroup get(int r3) {
        IntRange r0 = RegexKt.access$range(MatcherMatchResult.access$getMatchResult(this.this$0), r3);
        if (r0.getStart().intValue() >= 0) goto L5;
        return null;
    L5:
        return new MatchGroup(MatcherMatchResult.access$getMatchResult(this.this$0).group(r3), r0);
    }

    @Override // kotlin.text.MatchNamedGroupCollection
    public MatchGroup get(String r2) {
        return PlatformImplementationsKt.IMPLEMENTATIONS.getMatchResultNamedGroup(MatcherMatchResult.access$getMatchResult(this.this$0), r2);
    }

    @Override // kotlin.collections.AbstractCollection
    public int getSize() {
        return MatcherMatchResult.access$getMatchResult(this.this$0).groupCount() + 1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return false;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<MatchGroup> iterator() {
        return SequencesKt.map(CollectionsKt.asSequence(CollectionsKt.getIndices(this)), new MatcherMatchResult$groups$1$$ExternalSyntheticLambda0(this)).iterator();
    }
}
