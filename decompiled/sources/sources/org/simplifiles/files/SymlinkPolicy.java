package org.simplifiles.files;

import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/simplifiles/files/SymlinkPolicy;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "SKIP", MediaError.ERROR_TYPE_ERROR, "FOLLOW", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public enum SymlinkPolicy extends Enum<SymlinkPolicy> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ SymlinkPolicy[] $VALUES = null;
    public static final SymlinkPolicy ERROR = null;
    public static final SymlinkPolicy FOLLOW = null;
    public static final SymlinkPolicy SKIP = null;

    private static final /* synthetic */ SymlinkPolicy[] $values() {
        return new SymlinkPolicy[]{SKIP, ERROR, FOLLOW};
    }

    static {
        SKIP = new SymlinkPolicy("SKIP", 0);
        ERROR = new SymlinkPolicy(MediaError.ERROR_TYPE_ERROR, 1);
        FOLLOW = new SymlinkPolicy("FOLLOW", 2);
        SymlinkPolicy[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    SymlinkPolicy(String r1, int r2) {
    }

    public static SymlinkPolicy valueOf(String r1) {
        return (SymlinkPolicy) Enum.valueOf(SymlinkPolicy.class, r1);
    }

    public static SymlinkPolicy[] values() {
        return (SymlinkPolicy[]) $VALUES.clone();
    }
}
