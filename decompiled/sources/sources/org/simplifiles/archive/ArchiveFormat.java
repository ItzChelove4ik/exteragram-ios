package org.simplifiles.archive;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lorg/simplifiles/archive/ArchiveFormat;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "ZIP", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public enum ArchiveFormat extends Enum<ArchiveFormat> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ ArchiveFormat[] $VALUES = null;
    public static final ArchiveFormat ZIP = null;

    private static final /* synthetic */ ArchiveFormat[] $values() {
        return new ArchiveFormat[]{ZIP};
    }

    static {
        ZIP = new ArchiveFormat("ZIP", 0);
        ArchiveFormat[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    ArchiveFormat(String r1, int r2) {
    }

    public static ArchiveFormat valueOf(String r1) {
        return (ArchiveFormat) Enum.valueOf(ArchiveFormat.class, r1);
    }

    public static ArchiveFormat[] values() {
        return (ArchiveFormat[]) $VALUES.clone();
    }
}
