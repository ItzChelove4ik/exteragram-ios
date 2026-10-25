package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.JvmName;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0087\u0080\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001b\u0010\u0000\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00050\u0002H\u0087\u0080\u0004¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\b0\u0002H\u0087\u0080\u0004¢\u0006\u0004\b\t\u0010\u0004\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0087\u0080\u0004¢\u0006\u0004\b\u000b\u0010\u0004¨\u0006\f"}, d2 = {"sum", "Lkotlin/UInt;", "Lkotlin/sequences/Sequence;", "sumOfUInt", "(Lkotlin/sequences/Sequence;)I", "Lkotlin/ULong;", "sumOfULong", "(Lkotlin/sequences/Sequence;)J", "Lkotlin/UByte;", "sumOfUByte", "Lkotlin/UShort;", "sumOfUShort", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/sequences/USequencesKt")
/* loaded from: classes5.dex */
class USequencesKt___USequencesKt {
    public USequencesKt___USequencesKt() {
    }

    @SinceKotlin(version = "1.5")
    @JvmName(name = "sumOfUByte")
    public static final int sumOfUByte(Sequence<UByte> r2) {
        Iterator<UByte> r22 = r2.iterator();
        int r0 = 0;
    L4:
        if (r22.hasNext() == false) goto L6;
        r0 = UInt.m2113constructorimpl(r0 + UInt.m2113constructorimpl(r22.next().m2086unboximpl() & UByte.MAX_VALUE));
        goto L4
    L6:
        return r0;
    }

    @SinceKotlin(version = "1.5")
    @JvmName(name = "sumOfUInt")
    public static final int sumOfUInt(Sequence<UInt> r2) {
        Iterator<UInt> r22 = r2.iterator();
        int r0 = 0;
    L4:
        if (r22.hasNext() == false) goto L6;
        r0 = UInt.m2113constructorimpl(r0 + r22.next().m2165unboximpl());
        goto L4
    L6:
        return r0;
    }

    @SinceKotlin(version = "1.5")
    @JvmName(name = "sumOfULong")
    public static final long sumOfULong(Sequence<ULong> r4) {
        Iterator<ULong> r42 = r4.iterator();
        long r0 = 0;
    L4:
        if (r42.hasNext() == false) goto L6;
        r0 = ULong.m2192constructorimpl(r0 + r42.next().m2244unboximpl());
        goto L4
    L6:
        return r0;
    }

    @SinceKotlin(version = "1.5")
    @JvmName(name = "sumOfUShort")
    public static final int sumOfUShort(Sequence<UShort> r3) {
        Iterator<UShort> r32 = r3.iterator();
        int r0 = 0;
    L4:
        if (r32.hasNext() == false) goto L6;
        r0 = UInt.m2113constructorimpl(r0 + UInt.m2113constructorimpl(r32.next().m2349unboximpl() & UShort.MAX_VALUE));
        goto L4
    L6:
        return r0;
    }
}
