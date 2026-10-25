package com.exteragram.messenger.api.model;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/api/model/ProfileStatus;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", PluginsConstants.Strategy.DEFAULT, "DEVELOPER", "SUPPORTER", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum ProfileStatus extends Enum<ProfileStatus> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ ProfileStatus[] $VALUES = null;
    public static final ProfileStatus DEFAULT = null;
    public static final ProfileStatus DEVELOPER = null;
    public static final ProfileStatus SUPPORTER = null;

    private static final /* synthetic */ ProfileStatus[] $values() {
        return new ProfileStatus[]{DEFAULT, DEVELOPER, SUPPORTER};
    }

    static {
        DEFAULT = new ProfileStatus(PluginsConstants.Strategy.DEFAULT, 0);
        DEVELOPER = new ProfileStatus("DEVELOPER", 1);
        SUPPORTER = new ProfileStatus("SUPPORTER", 2);
        ProfileStatus[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    ProfileStatus(String r1, int r2) {
    }

    public static EnumEntries<ProfileStatus> getEntries() {
        return $ENTRIES;
    }

    public static ProfileStatus valueOf(String r1) {
        return (ProfileStatus) Enum.valueOf(ProfileStatus.class, r1);
    }

    public static ProfileStatus[] values() {
        return (ProfileStatus[]) $VALUES.clone();
    }
}
