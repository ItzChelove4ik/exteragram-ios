package kotlin.jvm.optionals;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a%\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0004\u001a.\u0010\u0005\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\f\u0012\b\b\u0001\u0012\u0004\b\u0002H\u00010\u00032\u0006\u0010\u0006\u001a\u0002H\u0001H\u0087\u0080\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001aD\u0010\b\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\f\u0012\b\b\u0001\u0012\u0004\b\u0002H\u00010\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00010\tH\u0087\u0088\u0004ø\u0001\u0001ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\n\u001a=\u0010\u000b\u001a\u0002H\f\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\u0010\b\u0001\u0010\f*\n\u0012\u0006\b\u0000\u0012\u0002H\u00010\r*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u000e\u001a\u0002H\fH\u0087\u0080\b¢\u0006\u0002\u0010\u000f\u001a&\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0011\"\b\b\u0000\u0010\u0001*\u00020\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\u0003H\u0087\u0080\u0004\u001a&\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0013\"\b\b\u0000\u0010\u0001*\u00020\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\u0003H\u0087\u0080\u0004\u001a&\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0015\"\b\b\u0000\u0010\u0001*\u00020\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\u0003H\u0087\u0080\u0004\u0082\u0002\u000b\n\u0002\b9\n\u0005\b\u009920\u0001¨\u0006\u0016"}, d2 = {"getOrNull", "T", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/Optional;", "(Ljava/util/Optional;)Ljava/lang/Object;", "getOrDefault", "defaultValue", "(Ljava/util/Optional;Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "Lkotlin/Function0;", "(Ljava/util/Optional;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "toCollection", "C", _UrlKt.FRAGMENT_ENCODE_SET, "destination", "(Ljava/util/Optional;Ljava/util/Collection;)Ljava/util/Collection;", "toList", _UrlKt.FRAGMENT_ENCODE_SET, "toSet", _UrlKt.FRAGMENT_ENCODE_SET, "asSequence", "Lkotlin/sequences/Sequence;", "kotlin-stdlib-jdk8"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class OptionalsKt {
    @SinceKotlin(version = "1.8")
    public static final <T> Sequence<T> asSequence(Optional<? extends T> r1) {
        if (r1.isPresent() == false) goto L7;
        return SequencesKt.sequenceOf(r1.get());
    L7:
        return SequencesKt.emptySequence();
    }

    @SinceKotlin(version = "1.8")
    public static final <T> T getOrDefault(Optional<? extends T> r1, T r2) {
        if (r1.isPresent() == true) goto L5;
        return r2;
    L5:
        return r1.get();
    }

    @SinceKotlin(version = "1.8")
    public static final <T> T getOrElse(Optional<? extends T> r1, Function0<? extends T> r2) {
        if (r1.isPresent() == false) goto L7;
        return r1.get();
    L7:
        return r2.invoke();
    }

    @SinceKotlin(version = "1.8")
    public static final <T> T getOrNull(Optional<T> r1) {
        return r1.orElse(null);
    }

    @SinceKotlin(version = "1.8")
    @IgnorableReturnValue
    public static final <T, C extends Collection<? super T>> C toCollection(Optional<T> r1, C r2) {
        if (r1.isPresent() == false) goto L5;
        r2.add(r1.get());
    L5:
        return r2;
    }

    @SinceKotlin(version = "1.8")
    public static final <T> List<T> toList(Optional<? extends T> r1) {
        if (r1.isPresent() == false) goto L7;
        return CollectionsKt.listOf(r1.get());
    L7:
        return CollectionsKt.emptyList();
    }

    @SinceKotlin(version = "1.8")
    public static final <T> Set<T> toSet(Optional<? extends T> r1) {
        if (r1.isPresent() == false) goto L7;
        return SetsKt.setOf(r1.get());
    L7:
        return SetsKt.emptySet();
    }
}
