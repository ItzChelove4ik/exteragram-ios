package kotlin.reflect;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.1")
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/reflect/KVariance;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "INVARIANT", "IN", "OUT", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public enum KVariance extends Enum<KVariance> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ KVariance[] $VALUES = null;
    public static final KVariance IN = null;
    public static final KVariance INVARIANT = null;
    public static final KVariance OUT = null;

    private static final /* synthetic */ KVariance[] $values() {
        return new KVariance[]{INVARIANT, IN, OUT};
    }

    static {
        INVARIANT = new KVariance("INVARIANT", 0);
        IN = new KVariance("IN", 1);
        OUT = new KVariance("OUT", 2);
        KVariance[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    KVariance(String r1, int r2) {
    }

    public static EnumEntries<KVariance> getEntries() {
        return $ENTRIES;
    }

    public static KVariance valueOf(String r1) {
        return (KVariance) Enum.valueOf(KVariance.class, r1);
    }

    public static KVariance[] values() {
        return (KVariance[]) $VALUES.clone();
    }
}
