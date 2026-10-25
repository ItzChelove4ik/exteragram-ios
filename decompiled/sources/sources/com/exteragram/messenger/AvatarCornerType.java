package com.exteragram.messenger;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/AvatarCornerType;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", PluginsConstants.Strategy.DEFAULT, "FORUM", "COMMUNITY", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum AvatarCornerType extends Enum<AvatarCornerType> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ AvatarCornerType[] $VALUES = null;
    public static final AvatarCornerType COMMUNITY = null;
    public static final AvatarCornerType DEFAULT = null;
    public static final AvatarCornerType FORUM = null;

    private static final /* synthetic */ AvatarCornerType[] $values() {
        return new AvatarCornerType[]{DEFAULT, FORUM, COMMUNITY};
    }

    static {
        DEFAULT = new AvatarCornerType(PluginsConstants.Strategy.DEFAULT, 0);
        FORUM = new AvatarCornerType("FORUM", 1);
        COMMUNITY = new AvatarCornerType("COMMUNITY", 2);
        AvatarCornerType[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    AvatarCornerType(String r1, int r2) {
    }

    public static AvatarCornerType valueOf(String r1) {
        return (AvatarCornerType) Enum.valueOf(AvatarCornerType.class, r1);
    }

    public static AvatarCornerType[] values() {
        return (AvatarCornerType[]) $VALUES.clone();
    }
}
