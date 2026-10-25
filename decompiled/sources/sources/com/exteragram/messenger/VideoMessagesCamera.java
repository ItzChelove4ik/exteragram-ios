package com.exteragram.messenger;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/VideoMessagesCamera;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "FRONT", "REAR", "ASK", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum VideoMessagesCamera extends Enum<VideoMessagesCamera> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ VideoMessagesCamera[] $VALUES = null;
    public static final VideoMessagesCamera ASK = null;
    public static final VideoMessagesCamera FRONT = null;
    public static final VideoMessagesCamera REAR = null;

    private static final /* synthetic */ VideoMessagesCamera[] $values() {
        return new VideoMessagesCamera[]{FRONT, REAR, ASK};
    }

    static {
        FRONT = new VideoMessagesCamera("FRONT", 0);
        REAR = new VideoMessagesCamera("REAR", 1);
        ASK = new VideoMessagesCamera("ASK", 2);
        VideoMessagesCamera[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    VideoMessagesCamera(String r1, int r2) {
    }

    public static EnumEntries<VideoMessagesCamera> getEntries() {
        return $ENTRIES;
    }

    public static VideoMessagesCamera valueOf(String r1) {
        return (VideoMessagesCamera) Enum.valueOf(VideoMessagesCamera.class, r1);
    }

    public static VideoMessagesCamera[] values() {
        return (VideoMessagesCamera[]) $VALUES.clone();
    }
}
