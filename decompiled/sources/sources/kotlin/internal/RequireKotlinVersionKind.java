package kotlin.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.2")
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0081\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/internal/RequireKotlinVersionKind;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "LANGUAGE_VERSION", "COMPILER_VERSION", "API_VERSION", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public enum RequireKotlinVersionKind extends Enum<RequireKotlinVersionKind> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ RequireKotlinVersionKind[] $VALUES = null;
    public static final RequireKotlinVersionKind API_VERSION = null;
    public static final RequireKotlinVersionKind COMPILER_VERSION = null;
    public static final RequireKotlinVersionKind LANGUAGE_VERSION = null;

    private static final /* synthetic */ RequireKotlinVersionKind[] $values() {
        return new RequireKotlinVersionKind[]{LANGUAGE_VERSION, COMPILER_VERSION, API_VERSION};
    }

    static {
        LANGUAGE_VERSION = new RequireKotlinVersionKind("LANGUAGE_VERSION", 0);
        COMPILER_VERSION = new RequireKotlinVersionKind("COMPILER_VERSION", 1);
        API_VERSION = new RequireKotlinVersionKind("API_VERSION", 2);
        RequireKotlinVersionKind[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    RequireKotlinVersionKind(String r1, int r2) {
    }

    public static EnumEntries<RequireKotlinVersionKind> getEntries() {
        return $ENTRIES;
    }

    public static RequireKotlinVersionKind valueOf(String r1) {
        return (RequireKotlinVersionKind) Enum.valueOf(RequireKotlinVersionKind.class, r1);
    }

    public static RequireKotlinVersionKind[] values() {
        return (RequireKotlinVersionKind[]) $VALUES.clone();
    }
}
