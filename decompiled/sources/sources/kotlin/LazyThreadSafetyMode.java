package kotlin;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/LazyThreadSafetyMode;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "SYNCHRONIZED", "PUBLICATION", "NONE", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public enum LazyThreadSafetyMode extends Enum<LazyThreadSafetyMode> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ LazyThreadSafetyMode[] $VALUES = null;
    public static final LazyThreadSafetyMode NONE = null;
    public static final LazyThreadSafetyMode PUBLICATION = null;
    public static final LazyThreadSafetyMode SYNCHRONIZED = null;

    private static final /* synthetic */ LazyThreadSafetyMode[] $values() {
        return new LazyThreadSafetyMode[]{SYNCHRONIZED, PUBLICATION, NONE};
    }

    static {
        SYNCHRONIZED = new LazyThreadSafetyMode("SYNCHRONIZED", 0);
        PUBLICATION = new LazyThreadSafetyMode("PUBLICATION", 1);
        NONE = new LazyThreadSafetyMode("NONE", 2);
        LazyThreadSafetyMode[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    LazyThreadSafetyMode(String r1, int r2) {
    }

    public static EnumEntries<LazyThreadSafetyMode> getEntries() {
        return $ENTRIES;
    }

    public static LazyThreadSafetyMode valueOf(String r1) {
        return (LazyThreadSafetyMode) Enum.valueOf(LazyThreadSafetyMode.class, r1);
    }

    public static LazyThreadSafetyMode[] values() {
        return (LazyThreadSafetyMode[]) $VALUES.clone();
    }
}
