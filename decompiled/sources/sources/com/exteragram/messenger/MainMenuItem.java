package com.exteragram.messenger;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\b\u0086\u0081\u0002\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0016B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0017"}, d2 = {"Lcom/exteragram/messenger/MainMenuItem;", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;II)V", "getId", "()I", "DIVIDER", "PROFILE", "ARCHIVE", "BOTS", "NEW_GROUP", "CONTACTS", "NEW_CHANNEL", "CALLS", "SAVED", "SETTINGS", "PLUGINS", "BROWSER", "QR", "FEED", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum MainMenuItem extends Enum<MainMenuItem> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ MainMenuItem[] $VALUES = null;
    public static final MainMenuItem ARCHIVE = null;
    public static final MainMenuItem BOTS = null;
    public static final MainMenuItem BROWSER = null;
    public static final MainMenuItem CALLS = null;
    public static final MainMenuItem CONTACTS = null;
    public static final Companion Companion = null;
    public static final MainMenuItem DIVIDER = null;
    public static final MainMenuItem FEED = null;
    public static final MainMenuItem NEW_CHANNEL = null;
    public static final MainMenuItem NEW_GROUP = null;
    public static final MainMenuItem PLUGINS = null;
    public static final MainMenuItem PROFILE = null;
    public static final MainMenuItem QR = null;
    public static final MainMenuItem SAVED = null;
    public static final MainMenuItem SETTINGS = null;
    private final int id;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/exteragram/messenger/MainMenuItem$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getById", "Lcom/exteragram/messenger/MainMenuItem;", "id", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nExteraConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExteraConfig.kt\ncom/exteragram/messenger/MainMenuItem$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,704:1\n1#2:705\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final MainMenuItem getById(int r3) {
            Iterator<MainMenuItem> r2 = MainMenuItem.getEntries().iterator();
        L4:
            if (r2.hasNext() == false) goto L8;
            MainMenuItem r0 = r2.next();
            if (r0.getId() != r3) goto L4;
        L10:
            return r0;
        L8:
            r0 = null;
            goto L10
        }
    }

    private static final /* synthetic */ MainMenuItem[] $values() {
        return new MainMenuItem[]{DIVIDER, PROFILE, ARCHIVE, BOTS, NEW_GROUP, CONTACTS, NEW_CHANNEL, CALLS, SAVED, SETTINGS, PLUGINS, BROWSER, QR, FEED};
    }

    static {
        DIVIDER = new MainMenuItem("DIVIDER", 0, -1);
        PROFILE = new MainMenuItem("PROFILE", 1, 18);
        ARCHIVE = new MainMenuItem("ARCHIVE", 2, 14);
        BOTS = new MainMenuItem("BOTS", 3, 105);
        NEW_GROUP = new MainMenuItem("NEW_GROUP", 4, 2);
        CONTACTS = new MainMenuItem("CONTACTS", 5, 6);
        NEW_CHANNEL = new MainMenuItem("NEW_CHANNEL", 6, 3);
        CALLS = new MainMenuItem("CALLS", 7, 10);
        SAVED = new MainMenuItem("SAVED", 8, 11);
        SETTINGS = new MainMenuItem("SETTINGS", 9, 8);
        PLUGINS = new MainMenuItem("PLUGINS", 10, 102);
        BROWSER = new MainMenuItem("BROWSER", 11, 101);
        QR = new MainMenuItem("QR", 12, 17);
        FEED = new MainMenuItem("FEED", 13, 106);
        MainMenuItem[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
        Companion = new Companion(null);
    }

    MainMenuItem(String r1, int r2, int r3) {
        this.id = r3;
    }

    @JvmStatic
    public static final MainMenuItem getById(int r1) {
        return Companion.getById(r1);
    }

    public static EnumEntries<MainMenuItem> getEntries() {
        return $ENTRIES;
    }

    public static MainMenuItem valueOf(String r1) {
        return (MainMenuItem) Enum.valueOf(MainMenuItem.class, r1);
    }

    public static MainMenuItem[] values() {
        return (MainMenuItem[]) $VALUES.clone();
    }

    public final int getId() {
        return this.id;
    }
}
