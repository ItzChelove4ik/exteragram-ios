package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmField;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\f\n\u0002\b\b\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\bB\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lkotlinx/serialization/json/internal/WriteMode;", _UrlKt.FRAGMENT_ENCODE_SET, "begin", _UrlKt.FRAGMENT_ENCODE_SET, "end", "<init>", "(Ljava/lang/String;ICC)V", "OBJ", "LIST", "MAP", "POLY_OBJ", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public enum WriteMode extends Enum<WriteMode> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ WriteMode[] $VALUES = null;
    public static final WriteMode LIST = null;
    public static final WriteMode MAP = null;
    public static final WriteMode OBJ = null;
    public static final WriteMode POLY_OBJ = null;

    @JvmField
    public final char begin;

    @JvmField
    public final char end;

    private static final /* synthetic */ WriteMode[] $values() {
        return new WriteMode[]{OBJ, LIST, MAP, POLY_OBJ};
    }

    static {
        OBJ = new WriteMode("OBJ", 0, '{', '}');
        LIST = new WriteMode("LIST", 1, '[', ']');
        MAP = new WriteMode("MAP", 2, '{', '}');
        POLY_OBJ = new WriteMode("POLY_OBJ", 3, '[', ']');
        WriteMode[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    WriteMode(String r1, int r2, char r3, char r4) {
        this.begin = r3;
        this.end = r4;
    }

    public static EnumEntries<WriteMode> getEntries() {
        return $ENTRIES;
    }

    public static WriteMode valueOf(String r1) {
        return (WriteMode) Enum.valueOf(WriteMode.class, r1);
    }

    public static WriteMode[] values() {
        return (WriteMode[]) $VALUES.clone();
    }
}
