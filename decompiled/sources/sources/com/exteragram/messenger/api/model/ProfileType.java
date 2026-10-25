package com.exteragram.messenger.api.model;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/exteragram/messenger/api/model/ProfileType;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "USER", "CHAT", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum ProfileType extends Enum<ProfileType> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ ProfileType[] $VALUES = null;
    public static final ProfileType CHAT = null;
    public static final ProfileType USER = null;

    private static final /* synthetic */ ProfileType[] $values() {
        return new ProfileType[]{USER, CHAT};
    }

    static {
        USER = new ProfileType("USER", 0);
        CHAT = new ProfileType("CHAT", 1);
        ProfileType[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    ProfileType(String r1, int r2) {
    }

    public static EnumEntries<ProfileType> getEntries() {
        return $ENTRIES;
    }

    public static ProfileType valueOf(String r1) {
        return (ProfileType) Enum.valueOf(ProfileType.class, r1);
    }

    public static ProfileType[] values() {
        return (ProfileType[]) $VALUES.clone();
    }
}
