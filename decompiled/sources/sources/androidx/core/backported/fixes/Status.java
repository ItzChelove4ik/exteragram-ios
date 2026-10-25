package androidx.core.backported.fixes;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/core/backported/fixes/Status;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "Unknown", "Fixed", "NotApplicable", "NotFixed", "core-backported-fixes"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
public enum Status extends Enum<Status> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ Status[] $VALUES = null;
    public static final Status Fixed = null;
    public static final Status NotApplicable = null;
    public static final Status NotFixed = null;
    public static final Status Unknown = null;

    private static final /* synthetic */ Status[] $values() {
        return new Status[]{Unknown, Fixed, NotApplicable, NotFixed};
    }

    static {
        Unknown = new Status("Unknown", 0);
        Fixed = new Status("Fixed", 1);
        NotApplicable = new Status("NotApplicable", 2);
        NotFixed = new Status("NotFixed", 3);
        Status[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    Status(String r1, int r2) {
    }

    public static Status valueOf(String r1) {
        return (Status) Enum.valueOf(Status.class, r1);
    }

    public static Status[] values() {
        return (Status[]) $VALUES.clone();
    }
}
