package com.exteragram.messenger;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/StickerTimeMode;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", PluginsConstants.Strategy.DEFAULT, "SIDE", "HIDDEN", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public enum StickerTimeMode extends Enum<StickerTimeMode> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ StickerTimeMode[] $VALUES = null;
    public static final StickerTimeMode DEFAULT = null;
    public static final StickerTimeMode HIDDEN = null;
    public static final StickerTimeMode SIDE = null;

    private static final /* synthetic */ StickerTimeMode[] $values() {
        return new StickerTimeMode[]{DEFAULT, SIDE, HIDDEN};
    }

    static {
        DEFAULT = new StickerTimeMode(PluginsConstants.Strategy.DEFAULT, 0);
        SIDE = new StickerTimeMode("SIDE", 1);
        HIDDEN = new StickerTimeMode("HIDDEN", 2);
        StickerTimeMode[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    StickerTimeMode(String r1, int r2) {
    }

    public static EnumEntries<StickerTimeMode> getEntries() {
        return $ENTRIES;
    }

    public static StickerTimeMode valueOf(String r1) {
        return (StickerTimeMode) Enum.valueOf(StickerTimeMode.class, r1);
    }

    public static StickerTimeMode[] values() {
        return (StickerTimeMode[]) $VALUES.clone();
    }
}
