package org.simplifiles.internal.io;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/simplifiles/internal/io/SymlinkDecision;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "INCLUDE", "SKIP", "FAIL", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public enum SymlinkDecision extends Enum<SymlinkDecision> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ SymlinkDecision[] $VALUES = null;
    public static final SymlinkDecision FAIL = null;
    public static final SymlinkDecision INCLUDE = null;
    public static final SymlinkDecision SKIP = null;

    private static final /* synthetic */ SymlinkDecision[] $values() {
        return new SymlinkDecision[]{INCLUDE, SKIP, FAIL};
    }

    static {
        INCLUDE = new SymlinkDecision("INCLUDE", 0);
        SKIP = new SymlinkDecision("SKIP", 1);
        FAIL = new SymlinkDecision("FAIL", 2);
        SymlinkDecision[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    SymlinkDecision(String r1, int r2) {
    }

    public static SymlinkDecision valueOf(String r1) {
        return (SymlinkDecision) Enum.valueOf(SymlinkDecision.class, r1);
    }

    public static SymlinkDecision[] values() {
        return (SymlinkDecision[]) $VALUES.clone();
    }
}
