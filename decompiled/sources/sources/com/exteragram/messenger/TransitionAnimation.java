package com.exteragram.messenger;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/TransitionAnimation;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", PluginsConstants.Strategy.DEFAULT, "AOSP", "SPRING", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public enum TransitionAnimation extends Enum<TransitionAnimation> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ TransitionAnimation[] $VALUES = null;
    public static final TransitionAnimation AOSP = null;
    public static final TransitionAnimation DEFAULT = null;
    public static final TransitionAnimation SPRING = null;

    private static final /* synthetic */ TransitionAnimation[] $values() {
        return new TransitionAnimation[]{DEFAULT, AOSP, SPRING};
    }

    static {
        DEFAULT = new TransitionAnimation(PluginsConstants.Strategy.DEFAULT, 0);
        AOSP = new TransitionAnimation("AOSP", 1);
        SPRING = new TransitionAnimation("SPRING", 2);
        TransitionAnimation[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    TransitionAnimation(String r1, int r2) {
    }

    public static EnumEntries<TransitionAnimation> getEntries() {
        return $ENTRIES;
    }

    public static TransitionAnimation valueOf(String r1) {
        return (TransitionAnimation) Enum.valueOf(TransitionAnimation.class, r1);
    }

    public static TransitionAnimation[] values() {
        return (TransitionAnimation[]) $VALUES.clone();
    }
}
