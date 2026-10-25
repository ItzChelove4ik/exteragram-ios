package kotlin;

import com.google.android.gms.cast.MediaError;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/DeprecationLevel;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "WARNING", MediaError.ERROR_TYPE_ERROR, "HIDDEN", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public enum DeprecationLevel extends Enum<DeprecationLevel> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ DeprecationLevel[] $VALUES = null;
    public static final DeprecationLevel ERROR = null;
    public static final DeprecationLevel HIDDEN = null;
    public static final DeprecationLevel WARNING = null;

    private static final /* synthetic */ DeprecationLevel[] $values() {
        return new DeprecationLevel[]{WARNING, ERROR, HIDDEN};
    }

    static {
        WARNING = new DeprecationLevel("WARNING", 0);
        ERROR = new DeprecationLevel(MediaError.ERROR_TYPE_ERROR, 1);
        HIDDEN = new DeprecationLevel("HIDDEN", 2);
        DeprecationLevel[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    DeprecationLevel(String r1, int r2) {
    }

    public static EnumEntries<DeprecationLevel> getEntries() {
        return $ENTRIES;
    }

    public static DeprecationLevel valueOf(String r1) {
        return (DeprecationLevel) Enum.valueOf(DeprecationLevel.class, r1);
    }

    public static DeprecationLevel[] values() {
        return (DeprecationLevel[]) $VALUES.clone();
    }
}
