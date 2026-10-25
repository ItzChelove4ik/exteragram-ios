package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\b\u0012\u0004\u0012\u0002H\u00030\u0004B?\bF\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00020\fH\u0096\u0082\u0004R\u0015\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R!\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\bX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/sequences/MergingSequence;", "T1", "T2", "V", "Lkotlin/sequences/Sequence;", "sequence1", "sequence2", "transform", "Lkotlin/Function2;", "<init>", "(Lkotlin/sequences/Sequence;Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function2;)V", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class MergingSequence<T1, T2, V> implements Sequence<V> {
    private final Sequence<T1> sequence1;
    private final Sequence<T2> sequence2;
    private final Function2<T1, T2, V> transform;

    @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0010(\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0007\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\bJ\n\u0010\t\u001a\u00020\nH\u0096\u0082\u0004R\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u001b\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0004¨\u0006\u000b"}, d2 = {"kotlin/sequences/MergingSequence$iterator$1", _UrlKt.FRAGMENT_ENCODE_SET, "iterator1", "getIterator1", "()Ljava/util/Iterator;", "iterator2", "getIterator2", "next", "()Ljava/lang/Object;", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* renamed from: kotlin.sequences.MergingSequence$iterator$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Iterator<V>, KMappedMarker {
        private final Iterator<T1> iterator1;
        private final Iterator<T2> iterator2;
        final /* synthetic */ MergingSequence<T1, T2, V> this$0;

        public AnonymousClass1(MergingSequence<T1, T2, V> r2) {
            this.this$0 = r2;
            this.iterator1 = MergingSequence.access$getSequence1$p(r2).iterator();
            this.iterator2 = MergingSequence.access$getSequence2$p(r2).iterator();
        }

        public final Iterator<T1> getIterator1() {
            return this.iterator1;
        }

        public final Iterator<T2> getIterator2() {
            return this.iterator2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.iterator1.hasNext() == true) goto L5;
            return false;
        L5:
            if (this.iterator2.hasNext() == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // java.util.Iterator
        public V next() {
            return (V) MergingSequence.access$getTransform$p(this.this$0).invoke(this.iterator1.next(), this.iterator2.next());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MergingSequence(Sequence<? extends T1> r1, Sequence<? extends T2> r2, Function2<? super T1, ? super T2, ? extends V> r3) {
        this.sequence1 = r1;
        this.sequence2 = r2;
        this.transform = r3;
    }

    public static final /* synthetic */ Sequence access$getSequence1$p(MergingSequence r0) {
        return r0.sequence1;
    }

    public static final /* synthetic */ Sequence access$getSequence2$p(MergingSequence r0) {
        return r0.sequence2;
    }

    public static final /* synthetic */ Function2 access$getTransform$p(MergingSequence r0) {
        return r0.transform;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<V> iterator() {
        return new AnonymousClass1(this);
    }
}
