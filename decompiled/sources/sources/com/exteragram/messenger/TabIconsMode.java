package com.exteragram.messenger;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/TabIconsMode;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "ICONS_AND_TITLES", "TITLES_ONLY", "ICONS_ONLY", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum TabIconsMode extends Enum<TabIconsMode> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ TabIconsMode[] $VALUES = null;
    public static final TabIconsMode ICONS_AND_TITLES = null;
    public static final TabIconsMode ICONS_ONLY = null;
    public static final TabIconsMode TITLES_ONLY = null;

    private static final /* synthetic */ TabIconsMode[] $values() {
        return new TabIconsMode[]{ICONS_AND_TITLES, TITLES_ONLY, ICONS_ONLY};
    }

    static {
        ICONS_AND_TITLES = new TabIconsMode("ICONS_AND_TITLES", 0);
        TITLES_ONLY = new TabIconsMode("TITLES_ONLY", 1);
        ICONS_ONLY = new TabIconsMode("ICONS_ONLY", 2);
        TabIconsMode[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    TabIconsMode(String r1, int r2) {
    }

    public static EnumEntries<TabIconsMode> getEntries() {
        return $ENTRIES;
    }

    public static TabIconsMode valueOf(String r1) {
        return (TabIconsMode) Enum.valueOf(TabIconsMode.class, r1);
    }

    public static TabIconsMode[] values() {
        return (TabIconsMode[]) $VALUES.clone();
    }
}
