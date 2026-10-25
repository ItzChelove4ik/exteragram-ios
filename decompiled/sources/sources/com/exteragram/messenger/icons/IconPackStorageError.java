package com.exteragram.messenger.icons;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/exteragram/messenger/icons/IconPackStorageError;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "INVALID_ARCHIVE", "MISSING_METADATA", "METADATA_TOO_LARGE", "INVALID_METADATA", "TOO_MANY_FILES", "ARCHIVE_TOO_LARGE", "FILE_TOO_LARGE", "COMPRESSION_RATIO_TOO_HIGH", "STORAGE_ERROR", "UNKNOWN", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public enum IconPackStorageError extends Enum<IconPackStorageError> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ IconPackStorageError[] $VALUES = null;
    public static final IconPackStorageError ARCHIVE_TOO_LARGE = null;
    public static final IconPackStorageError COMPRESSION_RATIO_TOO_HIGH = null;
    public static final IconPackStorageError FILE_TOO_LARGE = null;
    public static final IconPackStorageError INVALID_ARCHIVE = null;
    public static final IconPackStorageError INVALID_METADATA = null;
    public static final IconPackStorageError METADATA_TOO_LARGE = null;
    public static final IconPackStorageError MISSING_METADATA = null;
    public static final IconPackStorageError STORAGE_ERROR = null;
    public static final IconPackStorageError TOO_MANY_FILES = null;
    public static final IconPackStorageError UNKNOWN = null;

    private static final /* synthetic */ IconPackStorageError[] $values() {
        return new IconPackStorageError[]{INVALID_ARCHIVE, MISSING_METADATA, METADATA_TOO_LARGE, INVALID_METADATA, TOO_MANY_FILES, ARCHIVE_TOO_LARGE, FILE_TOO_LARGE, COMPRESSION_RATIO_TOO_HIGH, STORAGE_ERROR, UNKNOWN};
    }

    static {
        INVALID_ARCHIVE = new IconPackStorageError("INVALID_ARCHIVE", 0);
        MISSING_METADATA = new IconPackStorageError("MISSING_METADATA", 1);
        METADATA_TOO_LARGE = new IconPackStorageError("METADATA_TOO_LARGE", 2);
        INVALID_METADATA = new IconPackStorageError("INVALID_METADATA", 3);
        TOO_MANY_FILES = new IconPackStorageError("TOO_MANY_FILES", 4);
        ARCHIVE_TOO_LARGE = new IconPackStorageError("ARCHIVE_TOO_LARGE", 5);
        FILE_TOO_LARGE = new IconPackStorageError("FILE_TOO_LARGE", 6);
        COMPRESSION_RATIO_TOO_HIGH = new IconPackStorageError("COMPRESSION_RATIO_TOO_HIGH", 7);
        STORAGE_ERROR = new IconPackStorageError("STORAGE_ERROR", 8);
        UNKNOWN = new IconPackStorageError("UNKNOWN", 9);
        IconPackStorageError[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    IconPackStorageError(String r1, int r2) {
    }

    public static IconPackStorageError valueOf(String r1) {
        return (IconPackStorageError) Enum.valueOf(IconPackStorageError.class, r1);
    }

    public static IconPackStorageError[] values() {
        return (IconPackStorageError[]) $VALUES.clone();
    }
}
