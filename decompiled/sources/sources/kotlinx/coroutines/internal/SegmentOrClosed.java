package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlinx.coroutines.internal.Segment;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0081@\u0018\u0000*\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00000\u00012\u00020\u0003B\u0011\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\n\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0017\u0010\u000f\u001a\u00028\u00008F¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f\u0088\u0001\u0004\u0092\u0001\u0004\u0018\u00010\u0003¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/internal/SegmentOrClosed;", "Lkotlinx/coroutines/internal/Segment;", "S", _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "isClosed-impl", "(Ljava/lang/Object;)Z", "isClosed", "getSegment-impl", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/Segment;", "getSegment$annotations", "()V", "segment", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
/* loaded from: classes.dex */
public abstract class SegmentOrClosed<S extends Segment<S>> {
    /* renamed from: constructor-impl, reason: not valid java name */
    public static <S extends Segment<S>> Object m3557constructorimpl(Object r0) {
        return r0;
    }

    /* renamed from: getSegment-impl, reason: not valid java name */
    public static final S m3558getSegmentimpl(Object r1) {
        if (r1 != ConcurrentLinkedListKt.access$getCLOSED$p()) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Does not contain segment");
        return null;
    L5:
        return (S) r1;
    }

    /* renamed from: isClosed-impl, reason: not valid java name */
    public static final boolean m3559isClosedimpl(Object r1) {
        if (r1 != ConcurrentLinkedListKt.access$getCLOSED$p()) goto L6;
        return true;
    L6:
        return false;
    }
}
