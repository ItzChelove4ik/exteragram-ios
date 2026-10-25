package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;
import okio.Utf8$$ExternalSyntheticBUOutline0;
import okio.Utf8$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.NotificationBadge;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B'\bF\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u000e\u001a\u00020\u0006H\u0096\u0080\u0004J\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u000e\u001a\u00020\u0006H\u0096\u0080\u0004J\u0010\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0096\u0082\u0004R\u0015\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0005\u001a\u00020\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\n\u001a\u00020\u00068BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0012"}, d2 = {"Lkotlin/sequences/SubSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "startIndex", _UrlKt.FRAGMENT_ENCODE_SET, "endIndex", "<init>", "(Lkotlin/sequences/Sequence;II)V", NotificationBadge.NewHtcHomeBadger.COUNT, "getCount", "()I", "drop", "n", "take", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSequences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sequences.kt\nkotlin/sequences/SubSequence\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,730:1\n1#2:731\n*E\n"})
/* loaded from: classes5.dex */
public final class SubSequence<T> implements Sequence<T>, DropTakeSequence<T> {
    private final int endIndex;
    private final Sequence<T> sequence;
    private final int startIndex;

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\n\u0010\u000b\u001a\u00020\fH\u0082\u0080\u0004J\n\u0010\r\u001a\u00020\u000eH\u0096\u0082\u0004J\u000f\u0010\u000f\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0010R\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u0006X\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"kotlin/sequences/SubSequence$iterator$1", _UrlKt.FRAGMENT_ENCODE_SET, "iterator", "getIterator", "()Ljava/util/Iterator;", "position", _UrlKt.FRAGMENT_ENCODE_SET, "getPosition", "()I", "setPosition", "(I)V", "drop", _UrlKt.FRAGMENT_ENCODE_SET, "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* renamed from: kotlin.sequences.SubSequence$iterator$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Iterator<T>, KMappedMarker {
        private final Iterator<T> iterator;
        private int position;
        final /* synthetic */ SubSequence<T> this$0;

        public AnonymousClass1(SubSequence<T> r1) {
            this.this$0 = r1;
            this.iterator = SubSequence.access$getSequence$p(r1).iterator();
        }

        private final void drop() {
        L3:
            if (this.position >= SubSequence.access$getStartIndex$p(this.this$0)) goto L7;
            if (this.iterator.hasNext() == false) goto L10;
            this.iterator.next();
            this.position++;
            goto L3
        L10:
            return;
        }

        public final Iterator<T> getIterator() {
            return this.iterator;
        }

        public final int getPosition() {
            return this.position;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            drop();
            if (this.position < SubSequence.access$getEndIndex$p(this.this$0)) goto L5;
            return false;
        L5:
            if (this.iterator.hasNext() == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // java.util.Iterator
        public T next() {
            drop();
            if (this.position >= SubSequence.access$getEndIndex$p(this.this$0)) goto L6;
            this.position++;
            return this.iterator.next();
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setPosition(int r1) {
            this.position = r1;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SubSequence(Sequence<? extends T> r1, int r2, int r3) {
        this.sequence = r1;
        this.startIndex = r2;
        this.endIndex = r3;
        if (r2 < 0) goto L11;
        if (r3 < 0) goto L9;
        if (r3 < r2) goto L7;
        return;
    L7:
        Utf8$$ExternalSyntheticBUOutline0.m("endIndex should be not less than startIndex, but was ", r3, " < ", r2);
        throw null;
    L9:
        Utf8$$ExternalSyntheticBUOutline1.m("endIndex should be non-negative, but is ", r3);
        throw null;
    L11:
        Utf8$$ExternalSyntheticBUOutline1.m("startIndex should be non-negative, but is ", r2);
        throw null;
    }

    public static final /* synthetic */ int access$getEndIndex$p(SubSequence r0) {
        return r0.endIndex;
    }

    public static final /* synthetic */ Sequence access$getSequence$p(SubSequence r0) {
        return r0.sequence;
    }

    public static final /* synthetic */ int access$getStartIndex$p(SubSequence r0) {
        return r0.startIndex;
    }

    private final int getCount() {
        return this.endIndex - this.startIndex;
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence<T> drop(int r4) {
        if (r4 < getCount()) goto L7;
        return SequencesKt__SequencesKt.emptySequence();
    L7:
        return new SubSequence(this.sequence, this.startIndex + r4, this.endIndex);
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<T> iterator() {
        return new AnonymousClass1(this);
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence<T> take(int r3) {
        if (r3 < getCount()) goto L5;
        return this;
    L5:
        Sequence<T> r1 = this.sequence;
        int r2 = this.startIndex;
        return new SubSequence(r1, r2, r3 + r2);
    }
}
