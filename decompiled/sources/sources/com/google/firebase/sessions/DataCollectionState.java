package com.google.firebase.sessions;

import com.google.firebase.encoders.json.NumberedEnum;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0080\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/google/firebase/sessions/DataCollectionState;", "Lcom/google/firebase/encoders/json/NumberedEnum;", _UrlKt.FRAGMENT_ENCODE_SET, "number", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;II)V", "getNumber", "()I", "COLLECTION_UNKNOWN", "COLLECTION_SDK_NOT_INSTALLED", "COLLECTION_ENABLED", "COLLECTION_DISABLED", "COLLECTION_DISABLED_REMOTE", "COLLECTION_SAMPLED", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes5.dex */
public enum DataCollectionState extends Enum<DataCollectionState> implements NumberedEnum {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ DataCollectionState[] $VALUES = null;
    public static final DataCollectionState COLLECTION_DISABLED = null;
    public static final DataCollectionState COLLECTION_DISABLED_REMOTE = null;
    public static final DataCollectionState COLLECTION_ENABLED = null;
    public static final DataCollectionState COLLECTION_SAMPLED = null;
    public static final DataCollectionState COLLECTION_SDK_NOT_INSTALLED = null;
    public static final DataCollectionState COLLECTION_UNKNOWN = null;
    private final int number;

    private static final /* synthetic */ DataCollectionState[] $values() {
        return new DataCollectionState[]{COLLECTION_UNKNOWN, COLLECTION_SDK_NOT_INSTALLED, COLLECTION_ENABLED, COLLECTION_DISABLED, COLLECTION_DISABLED_REMOTE, COLLECTION_SAMPLED};
    }

    static {
        COLLECTION_UNKNOWN = new DataCollectionState("COLLECTION_UNKNOWN", 0, 0);
        COLLECTION_SDK_NOT_INSTALLED = new DataCollectionState("COLLECTION_SDK_NOT_INSTALLED", 1, 1);
        COLLECTION_ENABLED = new DataCollectionState("COLLECTION_ENABLED", 2, 2);
        COLLECTION_DISABLED = new DataCollectionState("COLLECTION_DISABLED", 3, 3);
        COLLECTION_DISABLED_REMOTE = new DataCollectionState("COLLECTION_DISABLED_REMOTE", 4, 4);
        COLLECTION_SAMPLED = new DataCollectionState("COLLECTION_SAMPLED", 5, 5);
        DataCollectionState[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    DataCollectionState(String r1, int r2, int r3) {
        this.number = r3;
    }

    public static DataCollectionState valueOf(String r1) {
        return (DataCollectionState) Enum.valueOf(DataCollectionState.class, r1);
    }

    public static DataCollectionState[] values() {
        return (DataCollectionState[]) $VALUES.clone();
    }

    @Override // com.google.firebase.encoders.json.NumberedEnum
    public int getNumber() {
        return this.number;
    }
}
