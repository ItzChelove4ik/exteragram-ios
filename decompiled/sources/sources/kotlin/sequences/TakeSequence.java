package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.NotificationBadge;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001f\bF\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\n\u001a\u00020\u0006H\u0096\u0080\u0004J\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\n\u001a\u00020\u0006H\u0096\u0080\u0004J\u0010\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0096\u0082\u0004R\u0015\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0005\u001a\u00020\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlin/sequences/TakeSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", NotificationBadge.NewHtcHomeBadger.COUNT, _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlin/sequences/Sequence;I)V", "drop", "n", "take", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSequences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sequences.kt\nkotlin/sequences/TakeSequence\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,730:1\n1#2:731\n*E\n"})
/* loaded from: classes5.dex */
public final class TakeSequence<T> implements Sequence<T>, DropTakeSequence<T> {
    private final int count;
    private final Sequence<T> sequence;

    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u000b\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\fJ\n\u0010\r\u001a\u00020\u000eH\u0096\u0082\u0004R\u001b\u0010\u0002\u001a\u00020\u0003X\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"kotlin/sequences/TakeSequence$iterator$1", _UrlKt.FRAGMENT_ENCODE_SET, "left", _UrlKt.FRAGMENT_ENCODE_SET, "getLeft", "()I", "setLeft", "(I)V", "iterator", "getIterator", "()Ljava/util/Iterator;", "next", "()Ljava/lang/Object;", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* renamed from: kotlin.sequences.TakeSequence$iterator$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Iterator<T>, KMappedMarker {
        private final Iterator<T> iterator;
        private int left;

        public AnonymousClass1(TakeSequence<T> r2) {
            this.left = TakeSequence.access$getCount$p(r2);
            this.iterator = TakeSequence.access$getSequence$p(r2).iterator();
        }

        public final Iterator<T> getIterator() {
            return this.iterator;
        }

        public final int getLeft() {
            return this.left;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.left > 0) goto L5;
            return false;
        L5:
            if (this.iterator.hasNext() == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // java.util.Iterator
        public T next() {
            int r0 = this.left;
            if (r0 == 0) goto L6;
            this.left = r0 - 1;
            return this.iterator.next();
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setLeft(int r1) {
            this.left = r1;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TakeSequence(Sequence<? extends T> r1, int r2) {
        this.sequence = r1;
        this.count = r2;
        if (r2 < 0) goto L5;
        return;
    L5:
        ByteString$$ExternalSyntheticBUOutline1.m("count must be non-negative, but was ", r2, 46);
        throw null;
    }

    public static final /* synthetic */ int access$getCount$p(TakeSequence r0) {
        return r0.count;
    }

    public static final /* synthetic */ Sequence access$getSequence$p(TakeSequence r0) {
        return r0.sequence;
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence<T> drop(int r3) {
        int r0 = this.count;
        if (r3 < r0) goto L7;
        return SequencesKt__SequencesKt.emptySequence();
    L7:
        return new SubSequence(this.sequence, r3, r0);
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<T> iterator() {
        return new AnonymousClass1(this);
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence<T> take(int r2) {
        if (r2 < this.count) goto L6;
        return this;
    L6:
        return new TakeSequence(this.sequence, r2);
    }
}
