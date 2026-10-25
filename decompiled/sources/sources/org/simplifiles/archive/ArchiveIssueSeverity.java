package org.simplifiles.archive;

import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/simplifiles/archive/ArchiveIssueSeverity;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "INFO", "WARNING", MediaError.ERROR_TYPE_ERROR, "BLOCKER", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public enum ArchiveIssueSeverity extends Enum<ArchiveIssueSeverity> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ ArchiveIssueSeverity[] $VALUES = null;
    public static final ArchiveIssueSeverity BLOCKER = null;
    public static final ArchiveIssueSeverity ERROR = null;
    public static final ArchiveIssueSeverity INFO = null;
    public static final ArchiveIssueSeverity WARNING = null;

    private static final /* synthetic */ ArchiveIssueSeverity[] $values() {
        return new ArchiveIssueSeverity[]{INFO, WARNING, ERROR, BLOCKER};
    }

    static {
        INFO = new ArchiveIssueSeverity("INFO", 0);
        WARNING = new ArchiveIssueSeverity("WARNING", 1);
        ERROR = new ArchiveIssueSeverity(MediaError.ERROR_TYPE_ERROR, 2);
        BLOCKER = new ArchiveIssueSeverity("BLOCKER", 3);
        ArchiveIssueSeverity[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    ArchiveIssueSeverity(String r1, int r2) {
    }

    public static ArchiveIssueSeverity valueOf(String r1) {
        return (ArchiveIssueSeverity) Enum.valueOf(ArchiveIssueSeverity.class, r1);
    }

    public static ArchiveIssueSeverity[] values() {
        return (ArchiveIssueSeverity[]) $VALUES.clone();
    }
}
