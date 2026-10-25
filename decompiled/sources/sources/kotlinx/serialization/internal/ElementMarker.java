package kotlinx.serialization.internal;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.descriptors.SerialDescriptor;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B+\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0086\u0080\u0004J\n\u0010\u0011\u001a\u00020\u0006H\u0086\u0080\u0004J\u0012\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0006H\u0082\u0080\u0004J\u0012\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0082\u0080\u0004J\n\u0010\u0015\u001a\u00020\u0006H\u0082\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R!\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\u000bX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\f\u001a\u00020\rX\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lkotlinx/serialization/internal/ElementMarker;", _UrlKt.FRAGMENT_ENCODE_SET, "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "readIfAbsent", "Lkotlin/Function2;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlin/jvm/functions/Function2;)V", "lowerMarks", _UrlKt.FRAGMENT_ENCODE_SET, "highMarksArray", _UrlKt.FRAGMENT_ENCODE_SET, "mark", _UrlKt.FRAGMENT_ENCODE_SET, "index", "nextUnmarkedIndex", "prepareHighMarksArray", "elementsCount", "markHigh", "nextUnmarkedHighIndex", "Companion", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ElementMarker {
    private static final Companion Companion = null;
    private static final long[] EMPTY_HIGH_MARKS = null;
    private final SerialDescriptor descriptor;
    private final long[] highMarksArray;
    private long lowerMarks;
    private final Function2<SerialDescriptor, Integer, Boolean> readIfAbsent;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlinx/serialization/internal/ElementMarker$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "EMPTY_HIGH_MARKS", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        EMPTY_HIGH_MARKS = new long[0];
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ElementMarker(SerialDescriptor r3, Function2<? super SerialDescriptor, ? super Integer, Boolean> r4) {
        this.descriptor = r3;
        this.readIfAbsent = r4;
        int r32 = r3.getElementsCount();
        long r0 = 0;
        if (r32 > 64) goto L9;
        if (r32 == 64) goto L7;
        r0 = (-1) << r32;
    L7:
        this.lowerMarks = r0;
        this.highMarksArray = EMPTY_HIGH_MARKS;
        return;
    L9:
        this.lowerMarks = 0;
        this.highMarksArray = prepareHighMarksArray(r32);
    }

    private final void markHigh(int r6) {
        int r0 = (r6 >>> 6) - 1;
        long[] r5 = this.highMarksArray;
        r5[r0] = r5[r0] | (1 << (r6 & 63));
    }

    private final int nextUnmarkedHighIndex() {
        int r0 = this.highMarksArray.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L12;
        int r2 = r1 + 1;
        int r3 = r2 * 64;
        long r5 = this.highMarksArray[r1];
    L6:
        if (r5 == (-1)) goto L11;
        int r4 = Long.numberOfTrailingZeros(~r5);
        r5 = r5 | (1 << r4);
        int r42 = r4 + r3;
        if (this.readIfAbsent.invoke(this.descriptor, Integer.valueOf(r42)).booleanValue() == false) goto L6;
        this.highMarksArray[r1] = r5;
        return r42;
    L11:
        this.highMarksArray[r1] = r5;
        r1 = r2;
        goto L3
    L12:
        return -1;
    }

    private final long[] prepareHighMarksArray(int r4) {
        long[] r3 = new long[(r4 - 1) >>> 6];
        if ((r4 & 63) == 0) goto L5;
        r3[ArraysKt.getLastIndex(r3)] = (-1) << r4;
    L5:
        return r3;
    }

    public final void mark(int r5) {
        if (r5 >= 64) goto L6;
        this.lowerMarks |= 1 << r5;
        return;
    L6:
        markHigh(r5);
    }

    public final int nextUnmarkedIndex() {
        int r0 = this.descriptor.getElementsCount();
    L3:
        long r1 = this.lowerMarks;
        if (r1 == (-1)) goto L9;
        int r12 = Long.numberOfTrailingZeros(~r1);
        this.lowerMarks |= 1 << r12;
        if (this.readIfAbsent.invoke(this.descriptor, Integer.valueOf(r12)).booleanValue() == false) goto L3;
        return r12;
    L9:
        if (r0 > 64) goto L11;
        return -1;
    L11:
        return nextUnmarkedHighIndex();
    }
}
