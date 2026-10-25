package kotlin.io;

import java.io.BufferedReader;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.Sequence;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\bH\u0096\u0082\u0004R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlin/io/LinesSequence;", "Lkotlin/sequences/Sequence;", _UrlKt.FRAGMENT_ENCODE_SET, "reader", "Ljava/io/BufferedReader;", "<init>", "(Ljava/io/BufferedReader;)V", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class LinesSequence implements Sequence<String> {
    private final BufferedReader reader;

    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0010(\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\n\u0010\u0006\u001a\u00020\u0005H\u0096\u0082\u0004J\n\u0010\u0007\u001a\u00020\u0002H\u0096\u0082\u0004R\u0011\u0010\u0003\u001a\u0004\u0018\u00010\u0002X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"kotlin/io/LinesSequence$iterator$1", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "nextValue", "done", _UrlKt.FRAGMENT_ENCODE_SET, "hasNext", "next", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* renamed from: kotlin.io.LinesSequence$iterator$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Iterator<String>, KMappedMarker {
        private boolean done;
        private String nextValue;
        final /* synthetic */ LinesSequence this$0;

        public AnonymousClass1(LinesSequence r1) {
            this.this$0 = r1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.nextValue != null) goto L10;
            if (this.done == true) goto L10;
            String r0 = LinesSequence.access$getReader$p(this.this$0).readLine();
            this.nextValue = r0;
            if (r0 != null) goto L10;
            this.done = true;
        L10:
            if (this.nextValue == null) goto L12;
            return true;
        L12:
            return false;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ String next() {
            return next2();
        }

        @Override // java.util.Iterator
        /* renamed from: next, reason: avoid collision after fix types in other method */
        public String next2() {
            if (hasNext() == false) goto L6;
            String r0 = this.nextValue;
            this.nextValue = null;
            return r0;
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public LinesSequence(BufferedReader r1) {
        this.reader = r1;
    }

    public static final /* synthetic */ BufferedReader access$getReader$p(LinesSequence r0) {
        return r0.reader;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<String> iterator() {
        return new AnonymousClass1(this);
    }
}
