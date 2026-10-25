package kotlin.text;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B]\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012:\u0010\b\u001a6\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r0\t¢\u0006\u0002\b\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012H\u0096\u0082\u0004R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0005\u001a\u00020\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000RC\u0010\b\u001a6\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r0\t¢\u0006\u0002\b\u000eX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlin/text/DelimitedRangesSequence;", "Lkotlin/sequences/Sequence;", "Lkotlin/ranges/IntRange;", PluginsConstants.Settings.TYPE_INPUT, _UrlKt.FRAGMENT_ENCODE_SET, "startIndex", _UrlKt.FRAGMENT_ENCODE_SET, "limit", "getNextMatch", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "currentIndex", "Lkotlin/Pair;", "Lkotlin/ExtensionFunctionType;", "<init>", "(Ljava/lang/CharSequence;IILkotlin/jvm/functions/Function2;)V", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
final class DelimitedRangesSequence implements Sequence<IntRange> {
    private final Function2<CharSequence, Integer, Pair<Integer, Integer>> getNextMatch;
    private final CharSequence input;
    private final int limit;
    private final int startIndex;

    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\n\u0010\u0017\u001a\u00020\u0018H\u0082\u0080\u0004J\n\u0010\u0019\u001a\u00020\u0002H\u0096\u0082\u0004J\n\u0010\u001a\u001a\u00020\u001bH\u0096\u0082\u0004R\u001b\u0010\u0003\u001a\u00020\u0004X\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\u0004X\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001b\u0010\f\u001a\u00020\u0004X\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001d\u0010\u000f\u001a\u0004\u0018\u00010\u0002X\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u0004X\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\b¨\u0006\u001c"}, d2 = {"kotlin/text/DelimitedRangesSequence$iterator$1", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ranges/IntRange;", "nextState", _UrlKt.FRAGMENT_ENCODE_SET, "getNextState", "()I", "setNextState", "(I)V", "currentStartIndex", "getCurrentStartIndex", "setCurrentStartIndex", "nextSearchIndex", "getNextSearchIndex", "setNextSearchIndex", "nextItem", "getNextItem", "()Lkotlin/ranges/IntRange;", "setNextItem", "(Lkotlin/ranges/IntRange;)V", "counter", "getCounter", "setCounter", "calcNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* renamed from: kotlin.text.DelimitedRangesSequence$iterator$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Iterator<IntRange>, KMappedMarker {
        private int counter;
        private int currentStartIndex;
        private IntRange nextItem;
        private int nextSearchIndex;
        private int nextState;
        final /* synthetic */ DelimitedRangesSequence this$0;

        public AnonymousClass1(DelimitedRangesSequence r3) {
            this.this$0 = r3;
            this.nextState = -1;
            int r32 = RangesKt.coerceIn(DelimitedRangesSequence.access$getStartIndex$p(r3), 0, DelimitedRangesSequence.access$getInput$p(r3).length());
            this.currentStartIndex = r32;
            this.nextSearchIndex = r32;
        }

        private final void calcNext() {
            int r1 = 0;
            if (this.nextSearchIndex >= 0) goto L7;
            this.nextState = 0;
            this.nextItem = null;
            return;
        L7:
            if (DelimitedRangesSequence.access$getLimit$p(this.this$0) <= 0) goto L11;
            int r0 = this.counter + 1;
            this.counter = r0;
            if (r0 < DelimitedRangesSequence.access$getLimit$p(this.this$0)) goto L11;
        L12:
            this.nextItem = new IntRange(this.currentStartIndex, StringsKt__StringsKt.getLastIndex(DelimitedRangesSequence.access$getInput$p(this.this$0)));
            this.nextSearchIndex = -1;
        L20:
            this.nextState = 1;
            return;
        L11:
            if (this.nextSearchIndex > DelimitedRangesSequence.access$getInput$p(this.this$0).length()) goto L12;
            Pair r02 = (Pair) DelimitedRangesSequence.access$getGetNextMatch$p(this.this$0).invoke(DelimitedRangesSequence.access$getInput$p(this.this$0), Integer.valueOf(this.nextSearchIndex));
            if (r02 != null) goto L16;
            this.nextItem = new IntRange(this.currentStartIndex, StringsKt__StringsKt.getLastIndex(DelimitedRangesSequence.access$getInput$p(this.this$0)));
            this.nextSearchIndex = -1;
            goto L20
        L16:
            int r2 = ((Number) r02.component1()).intValue();
            int r03 = ((Number) r02.component2()).intValue();
            this.nextItem = RangesKt.until(this.currentStartIndex, r2);
            int r22 = r2 + r03;
            this.currentStartIndex = r22;
            if (r03 != 0) goto L19;
            r1 = 1;
        L19:
            this.nextSearchIndex = r22 + r1;
            goto L20
        }

        public final int getCounter() {
            return this.counter;
        }

        public final int getCurrentStartIndex() {
            return this.currentStartIndex;
        }

        public final IntRange getNextItem() {
            return this.nextItem;
        }

        public final int getNextSearchIndex() {
            return this.nextSearchIndex;
        }

        public final int getNextState() {
            return this.nextState;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.nextState != (-1)) goto L6;
            calcNext();
        L6:
            if (this.nextState != 1) goto L8;
            return true;
        L8:
            return false;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ IntRange next() {
            return next2();
        }

        @Override // java.util.Iterator
        /* renamed from: next, reason: avoid collision after fix types in other method */
        public IntRange next2() {
            if (this.nextState != (-1)) goto L6;
            calcNext();
        L6:
            if (this.nextState == 0) goto L9;
            IntRange r0 = this.nextItem;
            this.nextItem = null;
            this.nextState = -1;
            return r0;
        L9:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setCounter(int r1) {
            this.counter = r1;
        }

        public final void setCurrentStartIndex(int r1) {
            this.currentStartIndex = r1;
        }

        public final void setNextItem(IntRange r1) {
            this.nextItem = r1;
        }

        public final void setNextSearchIndex(int r1) {
            this.nextSearchIndex = r1;
        }

        public final void setNextState(int r1) {
            this.nextState = r1;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DelimitedRangesSequence(CharSequence r1, int r2, int r3, Function2<? super CharSequence, ? super Integer, Pair<Integer, Integer>> r4) {
        this.input = r1;
        this.startIndex = r2;
        this.limit = r3;
        this.getNextMatch = r4;
    }

    public static final /* synthetic */ Function2 access$getGetNextMatch$p(DelimitedRangesSequence r0) {
        return r0.getNextMatch;
    }

    public static final /* synthetic */ CharSequence access$getInput$p(DelimitedRangesSequence r0) {
        return r0.input;
    }

    public static final /* synthetic */ int access$getLimit$p(DelimitedRangesSequence r0) {
        return r0.limit;
    }

    public static final /* synthetic */ int access$getStartIndex$p(DelimitedRangesSequence r0) {
        return r0.startIndex;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<IntRange> iterator() {
        return new AnonymousClass1(this);
    }
}
