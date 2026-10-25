package org.telegram.ui.community;

/* loaded from: classes7.dex */
public enum CommunityChatType extends Enum<CommunityChatType> {
    private static final /* synthetic */ CommunityChatType[] $VALUES = null;
    public static final CommunityChatType HiddenUnavailable = null;
    public static final CommunityChatType YouAreIn = null;
    public static final CommunityChatType YouCanSendJoinRequest = null;
    public static final CommunityChatType YouCanView = null;

    private static /* synthetic */ CommunityChatType[] $values() {
        return new CommunityChatType[]{YouAreIn, YouCanView, YouCanSendJoinRequest, HiddenUnavailable};
    }

    static {
        YouAreIn = new CommunityChatType("YouAreIn", 0);
        YouCanView = new CommunityChatType("YouCanView", 1);
        YouCanSendJoinRequest = new CommunityChatType("YouCanSendJoinRequest", 2);
        HiddenUnavailable = new CommunityChatType("HiddenUnavailable", 3);
        $VALUES = $values();
    }

    CommunityChatType(String r1, int r2) {
    }

    public static CommunityChatType valueOf(String r1) {
        return (CommunityChatType) Enum.valueOf(CommunityChatType.class, r1);
    }

    public static CommunityChatType[] values() {
        return (CommunityChatType[]) $VALUES.clone();
    }
}
