package com.exteragram.messenger;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/IconPackType;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", PluginsConstants.Strategy.DEFAULT, "SOLAR", "REMIX", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum IconPackType extends Enum<IconPackType> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ IconPackType[] $VALUES = null;
    public static final IconPackType DEFAULT = null;
    public static final IconPackType REMIX = null;
    public static final IconPackType SOLAR = null;

    private static final /* synthetic */ IconPackType[] $values() {
        return new IconPackType[]{DEFAULT, SOLAR, REMIX};
    }

    static {
        DEFAULT = new IconPackType(PluginsConstants.Strategy.DEFAULT, 0);
        SOLAR = new IconPackType("SOLAR", 1);
        REMIX = new IconPackType("REMIX", 2);
        IconPackType[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    IconPackType(String r1, int r2) {
    }

    public static IconPackType valueOf(String r1) {
        return (IconPackType) Enum.valueOf(IconPackType.class, r1);
    }

    public static IconPackType[] values() {
        return (IconPackType[]) $VALUES.clone();
    }
}
