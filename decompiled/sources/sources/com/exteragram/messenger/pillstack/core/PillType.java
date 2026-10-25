package com.exteragram.messenger.pillstack.core;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, d2 = {"Lcom/exteragram/messenger/pillstack/core/PillType;", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;II)V", "getId", "()I", "WEATHER", "GRAM", "BTC", "USD", "CACHE", "PROXY", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum PillType extends Enum<PillType> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ PillType[] $VALUES = null;
    public static final PillType BTC = null;
    public static final PillType CACHE = null;
    public static final Companion Companion = null;
    public static final PillType GRAM = null;
    public static final PillType PROXY = null;
    public static final PillType USD = null;
    public static final PillType WEATHER = null;
    private final int id;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/exteragram/messenger/pillstack/core/PillType$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPillStackConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PillStackConfig.kt\ncom/exteragram/messenger/pillstack/core/PillType$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,177:1\n1#2:178\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    private static final /* synthetic */ PillType[] $values() {
        return new PillType[]{WEATHER, GRAM, BTC, USD, CACHE, PROXY};
    }

    static {
        WEATHER = new PillType("WEATHER", 0, 1);
        GRAM = new PillType("GRAM", 1, 2);
        BTC = new PillType("BTC", 2, 3);
        USD = new PillType("USD", 3, 4);
        CACHE = new PillType("CACHE", 4, 5);
        PROXY = new PillType("PROXY", 5, 6);
        PillType[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
        Companion = new Companion(null);
    }

    PillType(String r1, int r2, int r3) {
        this.id = r3;
    }

    public static PillType valueOf(String r1) {
        return (PillType) Enum.valueOf(PillType.class, r1);
    }

    public static PillType[] values() {
        return (PillType[]) $VALUES.clone();
    }

    public final int getId() {
        return this.id;
    }
}
