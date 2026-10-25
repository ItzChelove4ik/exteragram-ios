package com.exteragram.messenger.api.model;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/exteragram/messenger/api/model/NowPlayingServiceType;", _UrlKt.FRAGMENT_ENCODE_SET, "displayName", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getDisplayName", "()Ljava/lang/String;", "NONE", "LAST_FM", "STATS_FM", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum NowPlayingServiceType extends Enum<NowPlayingServiceType> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ NowPlayingServiceType[] $VALUES = null;
    public static final NowPlayingServiceType LAST_FM = null;
    public static final NowPlayingServiceType NONE = null;
    public static final NowPlayingServiceType STATS_FM = null;
    private final String displayName;

    private static final /* synthetic */ NowPlayingServiceType[] $values() {
        return new NowPlayingServiceType[]{NONE, LAST_FM, STATS_FM};
    }

    static {
        NONE = new NowPlayingServiceType("NONE", 0, LocaleController.getString(R.string.None));
        LAST_FM = new NowPlayingServiceType("LAST_FM", 1, "Last.fm");
        STATS_FM = new NowPlayingServiceType("STATS_FM", 2, "Stats.fm");
        NowPlayingServiceType[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    NowPlayingServiceType(String r1, int r2, String r3) {
        this.displayName = r3;
    }

    public static EnumEntries<NowPlayingServiceType> getEntries() {
        return $ENTRIES;
    }

    public static NowPlayingServiceType valueOf(String r1) {
        return (NowPlayingServiceType) Enum.valueOf(NowPlayingServiceType.class, r1);
    }

    public static NowPlayingServiceType[] values() {
        return (NowPlayingServiceType[]) $VALUES.clone();
    }

    public final String getDisplayName() {
        return this.displayName;
    }
}
