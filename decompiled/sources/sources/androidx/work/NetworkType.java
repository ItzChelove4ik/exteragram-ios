package androidx.work;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/work/NetworkType;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "NOT_REQUIRED", "CONNECTED", "UNMETERED", "NOT_ROAMING", "METERED", "TEMPORARILY_UNMETERED", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public enum NetworkType extends Enum<NetworkType> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ NetworkType[] $VALUES = null;
    public static final NetworkType CONNECTED = null;
    public static final NetworkType METERED = null;
    public static final NetworkType NOT_REQUIRED = null;
    public static final NetworkType NOT_ROAMING = null;
    public static final NetworkType TEMPORARILY_UNMETERED = null;
    public static final NetworkType UNMETERED = null;

    private static final /* synthetic */ NetworkType[] $values() {
        return new NetworkType[]{NOT_REQUIRED, CONNECTED, UNMETERED, NOT_ROAMING, METERED, TEMPORARILY_UNMETERED};
    }

    static {
        NOT_REQUIRED = new NetworkType("NOT_REQUIRED", 0);
        CONNECTED = new NetworkType("CONNECTED", 1);
        UNMETERED = new NetworkType("UNMETERED", 2);
        NOT_ROAMING = new NetworkType("NOT_ROAMING", 3);
        METERED = new NetworkType("METERED", 4);
        TEMPORARILY_UNMETERED = new NetworkType("TEMPORARILY_UNMETERED", 5);
        NetworkType[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    NetworkType(String r1, int r2) {
    }

    public static NetworkType valueOf(String r1) {
        return (NetworkType) Enum.valueOf(NetworkType.class, r1);
    }

    public static NetworkType[] values() {
        return (NetworkType[]) $VALUES.clone();
    }
}
