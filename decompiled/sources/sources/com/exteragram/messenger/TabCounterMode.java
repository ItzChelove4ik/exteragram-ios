package com.exteragram.messenger;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/TabCounterMode;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "ALL", "UNMUTED", "HIDDEN", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public enum TabCounterMode extends Enum<TabCounterMode> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ TabCounterMode[] $VALUES = null;
    public static final TabCounterMode ALL = null;
    public static final TabCounterMode HIDDEN = null;
    public static final TabCounterMode UNMUTED = null;

    private static final /* synthetic */ TabCounterMode[] $values() {
        return new TabCounterMode[]{ALL, UNMUTED, HIDDEN};
    }

    static {
        ALL = new TabCounterMode("ALL", 0);
        UNMUTED = new TabCounterMode("UNMUTED", 1);
        HIDDEN = new TabCounterMode("HIDDEN", 2);
        TabCounterMode[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    TabCounterMode(String r1, int r2) {
    }

    public static EnumEntries<TabCounterMode> getEntries() {
        return $ENTRIES;
    }

    public static TabCounterMode valueOf(String r1) {
        return (TabCounterMode) Enum.valueOf(TabCounterMode.class, r1);
    }

    public static TabCounterMode[] values() {
        return (TabCounterMode[]) $VALUES.clone();
    }
}
