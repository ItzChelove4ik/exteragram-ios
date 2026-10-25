package org.simplifiles.files;

import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/simplifiles/files/DirectoryOverwritePolicy;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", MediaError.ERROR_TYPE_ERROR, "REPLACE", "SKIP", "MERGE", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public enum DirectoryOverwritePolicy extends Enum<DirectoryOverwritePolicy> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ DirectoryOverwritePolicy[] $VALUES = null;
    public static final DirectoryOverwritePolicy ERROR = null;
    public static final DirectoryOverwritePolicy MERGE = null;
    public static final DirectoryOverwritePolicy REPLACE = null;
    public static final DirectoryOverwritePolicy SKIP = null;

    private static final /* synthetic */ DirectoryOverwritePolicy[] $values() {
        return new DirectoryOverwritePolicy[]{ERROR, REPLACE, SKIP, MERGE};
    }

    static {
        ERROR = new DirectoryOverwritePolicy(MediaError.ERROR_TYPE_ERROR, 0);
        REPLACE = new DirectoryOverwritePolicy("REPLACE", 1);
        SKIP = new DirectoryOverwritePolicy("SKIP", 2);
        MERGE = new DirectoryOverwritePolicy("MERGE", 3);
        DirectoryOverwritePolicy[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    DirectoryOverwritePolicy(String r1, int r2) {
    }

    public static DirectoryOverwritePolicy valueOf(String r1) {
        return (DirectoryOverwritePolicy) Enum.valueOf(DirectoryOverwritePolicy.class, r1);
    }

    public static DirectoryOverwritePolicy[] values() {
        return (DirectoryOverwritePolicy[]) $VALUES.clone();
    }
}
