package org.simplifiles.files;

import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/simplifiles/files/OverwritePolicy;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", MediaError.ERROR_TYPE_ERROR, "REPLACE", "SKIP", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public enum OverwritePolicy extends Enum<OverwritePolicy> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ OverwritePolicy[] $VALUES = null;
    public static final OverwritePolicy ERROR = null;
    public static final OverwritePolicy REPLACE = null;
    public static final OverwritePolicy SKIP = null;

    private static final /* synthetic */ OverwritePolicy[] $values() {
        return new OverwritePolicy[]{ERROR, REPLACE, SKIP};
    }

    static {
        ERROR = new OverwritePolicy(MediaError.ERROR_TYPE_ERROR, 0);
        REPLACE = new OverwritePolicy("REPLACE", 1);
        SKIP = new OverwritePolicy("SKIP", 2);
        OverwritePolicy[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    OverwritePolicy(String r1, int r2) {
    }

    public static OverwritePolicy valueOf(String r1) {
        return (OverwritePolicy) Enum.valueOf(OverwritePolicy.class, r1);
    }

    public static OverwritePolicy[] values() {
        return (OverwritePolicy[]) $VALUES.clone();
    }
}
