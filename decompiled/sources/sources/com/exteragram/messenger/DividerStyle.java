package com.exteragram.messenger;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/DividerStyle;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "HIDDEN", "LINE", "SEGMENTS", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum DividerStyle extends Enum<DividerStyle> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ DividerStyle[] $VALUES = null;
    public static final DividerStyle HIDDEN = null;
    public static final DividerStyle LINE = null;
    public static final DividerStyle SEGMENTS = null;

    private static final /* synthetic */ DividerStyle[] $values() {
        return new DividerStyle[]{HIDDEN, LINE, SEGMENTS};
    }

    static {
        HIDDEN = new DividerStyle("HIDDEN", 0);
        LINE = new DividerStyle("LINE", 1);
        SEGMENTS = new DividerStyle("SEGMENTS", 2);
        DividerStyle[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    DividerStyle(String r1, int r2) {
    }

    public static EnumEntries<DividerStyle> getEntries() {
        return $ENTRIES;
    }

    public static DividerStyle valueOf(String r1) {
        return (DividerStyle) Enum.valueOf(DividerStyle.class, r1);
    }

    public static DividerStyle[] values() {
        return (DividerStyle[]) $VALUES.clone();
    }
}
