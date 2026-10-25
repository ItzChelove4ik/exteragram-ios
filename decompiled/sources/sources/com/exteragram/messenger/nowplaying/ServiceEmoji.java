package com.exteragram.messenger.nowplaying;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\t\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/exteragram/messenger/nowplaying/ServiceEmoji;", _UrlKt.FRAGMENT_ENCODE_SET, "documentId", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;IJ)V", "getDocumentId", "()J", "MUSIC", "SPOTIFY", "TELEGRAM", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public enum ServiceEmoji extends Enum<ServiceEmoji> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ ServiceEmoji[] $VALUES = null;
    public static final Companion Companion = null;
    public static final ServiceEmoji MUSIC = null;
    public static final ServiceEmoji SPOTIFY = null;
    public static final ServiceEmoji TELEGRAM = null;
    private final long documentId;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/exteragram/messenger/nowplaying/ServiceEmoji$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "fromString", "Lcom/exteragram/messenger/nowplaying/ServiceEmoji;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nServiceEmoji.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ServiceEmoji.kt\ncom/exteragram/messenger/nowplaying/ServiceEmoji$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,14:1\n1#2:15\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final ServiceEmoji fromString(String r4) {
            Iterator<ServiceEmoji> r3 = ServiceEmoji.getEntries().iterator();
        L4:
            if (r3.hasNext() == false) goto L8;
            ServiceEmoji r0 = r3.next();
            if (StringsKt.equals(r0.name(), r4, true) == false) goto L4;
        L9:
            ServiceEmoji r02 = r0;
            if (r02 == null) goto L12;
            return r02;
        L12:
            return ServiceEmoji.MUSIC;
        L8:
            r0 = null;
            goto L9
        }
    }

    private static final /* synthetic */ ServiceEmoji[] $values() {
        return new ServiceEmoji[]{MUSIC, SPOTIFY, TELEGRAM};
    }

    static {
        MUSIC = new ServiceEmoji("MUSIC", 0, 5271627010681108586L);
        SPOTIFY = new ServiceEmoji("SPOTIFY", 1, 5271857023359681001L);
        TELEGRAM = new ServiceEmoji("TELEGRAM", 2, 5325674462522144646L);
        ServiceEmoji[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
        Companion = new Companion(null);
    }

    ServiceEmoji(String r1, int r2, long r3) {
        this.documentId = r3;
    }

    public static EnumEntries<ServiceEmoji> getEntries() {
        return $ENTRIES;
    }

    public static ServiceEmoji valueOf(String r1) {
        return (ServiceEmoji) Enum.valueOf(ServiceEmoji.class, r1);
    }

    public static ServiceEmoji[] values() {
        return (ServiceEmoji[]) $VALUES.clone();
    }

    public final long getDocumentId() {
        return this.documentId;
    }
}
