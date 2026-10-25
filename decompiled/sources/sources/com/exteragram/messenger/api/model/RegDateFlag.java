package com.exteragram.messenger.api.model;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/exteragram/messenger/api/model/RegDateFlag;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "EXACT", "INTERPOLATED", "LT", "ET", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public enum RegDateFlag extends Enum<RegDateFlag> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ RegDateFlag[] $VALUES = null;
    public static final RegDateFlag ET = null;
    public static final RegDateFlag EXACT = null;
    public static final RegDateFlag INTERPOLATED = null;
    public static final RegDateFlag LT = null;

    private static final /* synthetic */ RegDateFlag[] $values() {
        return new RegDateFlag[]{EXACT, INTERPOLATED, LT, ET};
    }

    static {
        EXACT = new RegDateFlag("EXACT", 0);
        INTERPOLATED = new RegDateFlag("INTERPOLATED", 1);
        LT = new RegDateFlag("LT", 2);
        ET = new RegDateFlag("ET", 3);
        RegDateFlag[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    RegDateFlag(String r1, int r2) {
    }

    public static EnumEntries<RegDateFlag> getEntries() {
        return $ENTRIES;
    }

    public static RegDateFlag valueOf(String r1) {
        return (RegDateFlag) Enum.valueOf(RegDateFlag.class, r1);
    }

    public static RegDateFlag[] values() {
        return (RegDateFlag[]) $VALUES.clone();
    }
}
