package kotlin.contracts;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.internal.ContractsDsl;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.3")
@ContractsDsl
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lkotlin/contracts/InvocationKind;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "AT_MOST_ONCE", "AT_LEAST_ONCE", "EXACTLY_ONCE", "UNKNOWN", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ExperimentalContracts
/* loaded from: classes5.dex */
public enum InvocationKind extends Enum<InvocationKind> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ InvocationKind[] $VALUES = null;

    @ContractsDsl
    public static final InvocationKind AT_LEAST_ONCE = null;

    @ContractsDsl
    public static final InvocationKind AT_MOST_ONCE = null;

    @ContractsDsl
    public static final InvocationKind EXACTLY_ONCE = null;

    @ContractsDsl
    public static final InvocationKind UNKNOWN = null;

    private static final /* synthetic */ InvocationKind[] $values() {
        return new InvocationKind[]{AT_MOST_ONCE, AT_LEAST_ONCE, EXACTLY_ONCE, UNKNOWN};
    }

    static {
        AT_MOST_ONCE = new InvocationKind("AT_MOST_ONCE", 0);
        AT_LEAST_ONCE = new InvocationKind("AT_LEAST_ONCE", 1);
        EXACTLY_ONCE = new InvocationKind("EXACTLY_ONCE", 2);
        UNKNOWN = new InvocationKind("UNKNOWN", 3);
        InvocationKind[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    InvocationKind(String r1, int r2) {
    }

    public static EnumEntries<InvocationKind> getEntries() {
        return $ENTRIES;
    }

    public static InvocationKind valueOf(String r1) {
        return (InvocationKind) Enum.valueOf(InvocationKind.class, r1);
    }

    public static InvocationKind[] values() {
        return (InvocationKind[]) $VALUES.clone();
    }
}
