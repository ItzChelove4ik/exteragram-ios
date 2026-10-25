package org.simplifiles.archive.security;

import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/simplifiles/archive/security/DuplicatePolicy;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", MediaError.ERROR_TYPE_ERROR, "KEEP_FIRST", "KEEP_LAST", "RENAME", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public enum DuplicatePolicy extends Enum<DuplicatePolicy> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ DuplicatePolicy[] $VALUES = null;
    public static final DuplicatePolicy ERROR = null;
    public static final DuplicatePolicy KEEP_FIRST = null;
    public static final DuplicatePolicy KEEP_LAST = null;
    public static final DuplicatePolicy RENAME = null;

    private static final /* synthetic */ DuplicatePolicy[] $values() {
        return new DuplicatePolicy[]{ERROR, KEEP_FIRST, KEEP_LAST, RENAME};
    }

    static {
        ERROR = new DuplicatePolicy(MediaError.ERROR_TYPE_ERROR, 0);
        KEEP_FIRST = new DuplicatePolicy("KEEP_FIRST", 1);
        KEEP_LAST = new DuplicatePolicy("KEEP_LAST", 2);
        RENAME = new DuplicatePolicy("RENAME", 3);
        DuplicatePolicy[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    DuplicatePolicy(String r1, int r2) {
    }

    public static DuplicatePolicy valueOf(String r1) {
        return (DuplicatePolicy) Enum.valueOf(DuplicatePolicy.class, r1);
    }

    public static DuplicatePolicy[] values() {
        return (DuplicatePolicy[]) $VALUES.clone();
    }
}
