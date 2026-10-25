package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/channels/BufferOverflow;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "SUSPEND", "DROP_OLDEST", "DROP_LATEST", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum BufferOverflow extends Enum<BufferOverflow> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ BufferOverflow[] $VALUES = null;
    public static final BufferOverflow DROP_LATEST = null;
    public static final BufferOverflow DROP_OLDEST = null;
    public static final BufferOverflow SUSPEND = null;

    private static final /* synthetic */ BufferOverflow[] $values() {
        return new BufferOverflow[]{SUSPEND, DROP_OLDEST, DROP_LATEST};
    }

    static {
        SUSPEND = new BufferOverflow("SUSPEND", 0);
        DROP_OLDEST = new BufferOverflow("DROP_OLDEST", 1);
        DROP_LATEST = new BufferOverflow("DROP_LATEST", 2);
        BufferOverflow[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    BufferOverflow(String r1, int r2) {
    }

    public static BufferOverflow valueOf(String r1) {
        return (BufferOverflow) Enum.valueOf(BufferOverflow.class, r1);
    }

    public static BufferOverflow[] values() {
        return (BufferOverflow[]) $VALUES.clone();
    }
}
