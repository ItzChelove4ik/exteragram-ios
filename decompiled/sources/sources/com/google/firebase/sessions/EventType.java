package com.google.firebase.sessions;

import com.google.firebase.encoders.json.NumberedEnum;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0080\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/google/firebase/sessions/EventType;", "Lcom/google/firebase/encoders/json/NumberedEnum;", _UrlKt.FRAGMENT_ENCODE_SET, "number", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;II)V", "getNumber", "()I", "EVENT_TYPE_UNKNOWN", "SESSION_START", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes5.dex */
public enum EventType extends Enum<EventType> implements NumberedEnum {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ EventType[] $VALUES = null;
    public static final EventType EVENT_TYPE_UNKNOWN = null;
    public static final EventType SESSION_START = null;
    private final int number;

    private static final /* synthetic */ EventType[] $values() {
        return new EventType[]{EVENT_TYPE_UNKNOWN, SESSION_START};
    }

    static {
        EVENT_TYPE_UNKNOWN = new EventType("EVENT_TYPE_UNKNOWN", 0, 0);
        SESSION_START = new EventType("SESSION_START", 1, 1);
        EventType[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    EventType(String r1, int r2, int r3) {
        this.number = r3;
    }

    public static EventType valueOf(String r1) {
        return (EventType) Enum.valueOf(EventType.class, r1);
    }

    public static EventType[] values() {
        return (EventType[]) $VALUES.clone();
    }

    @Override // com.google.firebase.encoders.json.NumberedEnum
    public int getNumber() {
        return this.number;
    }
}
