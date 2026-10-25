package kotlin.streams.jdk8;

import java.util.Iterator;
import java.util.stream.IntStream;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0010\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0082\u0004¨\u0006\u0004¸\u0006\u0000"}, d2 = {"kotlin/sequences/SequencesKt__SequencesKt$Sequence$1", "Lkotlin/sequences/Sequence;", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSequences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sequences.kt\nkotlin/sequences/SequencesKt__SequencesKt$Sequence$1\n+ 2 Streams.kt\nkotlin/streams/jdk8/StreamsKt\n*L\n1#1,730:1\n39#2:731\n*E\n"})
/* loaded from: classes5.dex */
public final class StreamsKt$asSequence$$inlined$Sequence$2 implements Sequence<Integer> {
    final /* synthetic */ IntStream $this_asSequence$inlined;

    public StreamsKt$asSequence$$inlined$Sequence$2(IntStream r1) {
        this.$this_asSequence$inlined = r1;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<Integer> iterator() {
        return this.$this_asSequence$inlined.iterator();
    }
}
