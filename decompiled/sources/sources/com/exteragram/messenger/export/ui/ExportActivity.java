package com.exteragram.messenger.export.ui;

import android.content.Intent;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.view.View;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.export.ExportSettings;
import com.exteragram.messenger.export.controllers.ExportController;
import com.exteragram.messenger.export.output.AbstractWriter;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.exteragram.messenger.preferences.SwitchGroup;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.BooleanSupplier;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 72\u00020\u00012\u00020\u0002:\u000267B\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J5\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0016\u0010\u0015\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00170\u0016\"\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u001e\u0010\u001b\u001a\u00020\u00102\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 H\u0014J0\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'H\u0014J\"\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u00132\u0006\u0010+\u001a\u00020\u00132\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0010\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u0013H\u0002J\u0010\u00101\u001a\u0002022\u0006\u00100\u001a\u00020\u0013H\u0002J\u0010\u00103\u001a\u00020/2\u0006\u00100\u001a\u00020\u0013H\u0002J\u0010\u00104\u001a\u0002022\u0006\u00100\u001a\u00020\u0013H\u0002J\b\u00105\u001a\u00020\u0013H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/exteragram/messenger/export/ui/ExportActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;", "peer", "Lorg/telegram/tgnet/TLRPC$InputPeer;", "<init>", "(Lorg/telegram/tgnet/TLRPC$InputPeer;)V", "settings", "Lcom/exteragram/messenger/export/ExportSettings;", "exportSettings", "Lcom/exteragram/messenger/preferences/SwitchGroup;", "chatsSettings", "mediaSettings", "onFragmentCreate", _UrlKt.FRAGMENT_ENCODE_SET, "onFragmentDestroy", _UrlKt.FRAGMENT_ENCODE_SET, "didReceivedNotification", "id", _UrlKt.FRAGMENT_ENCODE_SET, "account", "args", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(II[Ljava/lang/Object;)V", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "fillItems", PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "Landroid/view/View;", "position", "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "onActivityResultFragment", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "type", "Ljava/util/function/BooleanSupplier;", "flag", "setType", "Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;", "media", "setMedia", "getIndexOfFormat", "ExportItem", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nExportActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExportActivity.kt\ncom/exteragram/messenger/export/ui/ExportActivity\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,235:1\n1#2:236\n*E\n"})
/* loaded from: classes4.dex */
public final class ExportActivity extends BasePreferencesActivity implements NotificationCenter.NotificationCenterDelegate {
    public static final Companion Companion = null;
    private static final CharSequence[] formats = null;
    private final SwitchGroup chatsSettings;
    private final SwitchGroup exportSettings;
    private final SwitchGroup mediaSettings;
    private final TLRPC.InputPeer peer;
    private final ExportSettings settings;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/exteragram/messenger/export/ui/ExportActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "JSON_PICKER", _UrlKt.FRAGMENT_ENCODE_SET, "formats", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/CharSequence;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001d\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b ¨\u0006!"}, d2 = {"Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", _UrlKt.FRAGMENT_ENCODE_SET, "id", "I", "getId", "()I", "HEADER", "EXPORT_SETTINGS", "ACCOUNT_INFO", "CONTACTS_LIST", "STORY_ARCHIVE", "ACTIVE_SESSIONS", "CHATS_SETTINGS", "PERSONAL_CHATS", "BOT_CHATS", "PRIVATE_GROUPS", "PRIVATE_CHANNELS", "PUBLIC_GROUPS", "PUBLIC_CHANNELS", "MEDIA_SETTINGS", "PHOTOS", "VIDEOS", "VOICE_MESSAGES", "VIDEO_MESSAGES", "STICKERS", "GIFS", "FILES", "FORMAT", "START_EXPORT", "VIEW_JSON_EXPORT", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum ExportItem extends Enum<ExportItem> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ ExportItem[] $VALUES = null;
        public static final ExportItem ACCOUNT_INFO = null;
        public static final ExportItem ACTIVE_SESSIONS = null;
        public static final ExportItem BOT_CHATS = null;
        public static final ExportItem CHATS_SETTINGS = null;
        public static final ExportItem CONTACTS_LIST = null;
        public static final ExportItem EXPORT_SETTINGS = null;
        public static final ExportItem FILES = null;
        public static final ExportItem FORMAT = null;
        public static final ExportItem GIFS = null;
        public static final ExportItem HEADER = null;
        public static final ExportItem MEDIA_SETTINGS = null;
        public static final ExportItem PERSONAL_CHATS = null;
        public static final ExportItem PHOTOS = null;
        public static final ExportItem PRIVATE_CHANNELS = null;
        public static final ExportItem PRIVATE_GROUPS = null;
        public static final ExportItem PUBLIC_CHANNELS = null;
        public static final ExportItem PUBLIC_GROUPS = null;
        public static final ExportItem START_EXPORT = null;
        public static final ExportItem STICKERS = null;
        public static final ExportItem STORY_ARCHIVE = null;
        public static final ExportItem VIDEOS = null;
        public static final ExportItem VIDEO_MESSAGES = null;
        public static final ExportItem VIEW_JSON_EXPORT = null;
        public static final ExportItem VOICE_MESSAGES = null;
        private final int id;

        private static final /* synthetic */ ExportItem[] $values() {
            return new ExportItem[]{HEADER, EXPORT_SETTINGS, ACCOUNT_INFO, CONTACTS_LIST, STORY_ARCHIVE, ACTIVE_SESSIONS, CHATS_SETTINGS, PERSONAL_CHATS, BOT_CHATS, PRIVATE_GROUPS, PRIVATE_CHANNELS, PUBLIC_GROUPS, PUBLIC_CHANNELS, MEDIA_SETTINGS, PHOTOS, VIDEOS, VOICE_MESSAGES, VIDEO_MESSAGES, STICKERS, GIFS, FILES, FORMAT, START_EXPORT, VIEW_JSON_EXPORT};
        }

        static {
            HEADER = new ExportItem("HEADER", 0);
            EXPORT_SETTINGS = new ExportItem("EXPORT_SETTINGS", 1);
            ACCOUNT_INFO = new ExportItem("ACCOUNT_INFO", 2);
            CONTACTS_LIST = new ExportItem("CONTACTS_LIST", 3);
            STORY_ARCHIVE = new ExportItem("STORY_ARCHIVE", 4);
            ACTIVE_SESSIONS = new ExportItem("ACTIVE_SESSIONS", 5);
            CHATS_SETTINGS = new ExportItem("CHATS_SETTINGS", 6);
            PERSONAL_CHATS = new ExportItem("PERSONAL_CHATS", 7);
            BOT_CHATS = new ExportItem("BOT_CHATS", 8);
            PRIVATE_GROUPS = new ExportItem("PRIVATE_GROUPS", 9);
            PRIVATE_CHANNELS = new ExportItem("PRIVATE_CHANNELS", 10);
            PUBLIC_GROUPS = new ExportItem("PUBLIC_GROUPS", 11);
            PUBLIC_CHANNELS = new ExportItem("PUBLIC_CHANNELS", 12);
            MEDIA_SETTINGS = new ExportItem("MEDIA_SETTINGS", 13);
            PHOTOS = new ExportItem("PHOTOS", 14);
            VIDEOS = new ExportItem("VIDEOS", 15);
            VOICE_MESSAGES = new ExportItem("VOICE_MESSAGES", 16);
            VIDEO_MESSAGES = new ExportItem("VIDEO_MESSAGES", 17);
            STICKERS = new ExportItem("STICKERS", 18);
            GIFS = new ExportItem("GIFS", 19);
            FILES = new ExportItem("FILES", 20);
            FORMAT = new ExportItem("FORMAT", 21);
            START_EXPORT = new ExportItem("START_EXPORT", 22);
            VIEW_JSON_EXPORT = new ExportItem("VIEW_JSON_EXPORT", 23);
            ExportItem[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        ExportItem(String r1, int r2) {
            this.id = ordinal() + 1;
        }

        public static EnumEntries<ExportItem> getEntries() {
            return $ENTRIES;
        }

        public static ExportItem valueOf(String r1) {
            return (ExportItem) Enum.valueOf(ExportItem.class, r1);
        }

        public static ExportItem[] values() {
            return (ExportItem[]) $VALUES.clone();
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = null;

        static {
            int[] r0 = new int[ExportItem.values().length];
            r0[ExportItem.EXPORT_SETTINGS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L32
        L91:
            r0[ExportItem.ACCOUNT_INFO.ordinal()] = 2;     // Catch: NoSuchFieldError -> L33
        L105:
            r0[ExportItem.CONTACTS_LIST.ordinal()] = 3;     // Catch: NoSuchFieldError -> L34
        L65:
            r0[ExportItem.STORY_ARCHIVE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L35
        L69:
            r0[ExportItem.ACTIVE_SESSIONS.ordinal()] = 5;     // Catch: NoSuchFieldError -> L36
        L83:
            r0[ExportItem.CHATS_SETTINGS.ordinal()] = 6;     // Catch: NoSuchFieldError -> L37
        L99:
            r0[ExportItem.PERSONAL_CHATS.ordinal()] = 7;     // Catch: NoSuchFieldError -> L38
        L75:
            r0[ExportItem.BOT_CHATS.ordinal()] = 8;     // Catch: NoSuchFieldError -> L39
        L93:
            r0[ExportItem.PRIVATE_GROUPS.ordinal()] = 9;     // Catch: NoSuchFieldError -> L40
        L95:
            r0[ExportItem.PRIVATE_CHANNELS.ordinal()] = 10;     // Catch: NoSuchFieldError -> L41
        L59:
            r0[ExportItem.PUBLIC_GROUPS.ordinal()] = 11;     // Catch: NoSuchFieldError -> L42
        L89:
            r0[ExportItem.PUBLIC_CHANNELS.ordinal()] = 12;     // Catch: NoSuchFieldError -> L43
        L103:
            r0[ExportItem.MEDIA_SETTINGS.ordinal()] = 13;     // Catch: NoSuchFieldError -> L44
        L57:
            r0[ExportItem.PHOTOS.ordinal()] = 14;     // Catch: NoSuchFieldError -> L45
        L71:
            r0[ExportItem.VIDEOS.ordinal()] = 15;     // Catch: NoSuchFieldError -> L46
        L101:
            r0[ExportItem.VOICE_MESSAGES.ordinal()] = 16;     // Catch: NoSuchFieldError -> L47
        L63:
            r0[ExportItem.VIDEO_MESSAGES.ordinal()] = 17;     // Catch: NoSuchFieldError -> L48
        L67:
            r0[ExportItem.STICKERS.ordinal()] = 18;     // Catch: NoSuchFieldError -> L49
        L85:
            r0[ExportItem.GIFS.ordinal()] = 19;     // Catch: NoSuchFieldError -> L50
        L61:
            r0[ExportItem.FILES.ordinal()] = 20;     // Catch: NoSuchFieldError -> L51
        L79:
            r0[ExportItem.FORMAT.ordinal()] = 21;     // Catch: NoSuchFieldError -> L52
        L81:
            r0[ExportItem.START_EXPORT.ordinal()] = 22;     // Catch: NoSuchFieldError -> L53
        L97:
            r0[ExportItem.VIEW_JSON_EXPORT.ordinal()] = 23;     // Catch: NoSuchFieldError -> L54
        L27:
            $EnumSwitchMapping$0 = r0;
            int[] r02 = new int[AbstractWriter.Format.values().length];
            r02[AbstractWriter.Format.Json.ordinal()] = 1;     // Catch: NoSuchFieldError -> L55
        L87:
            r02[AbstractWriter.Format.HtmlAndJson.ordinal()] = 2;     // Catch: NoSuchFieldError -> L56
        L30:
            $EnumSwitchMapping$1 = r02;
        }
    }

    public static void $r8$lambda$CSL7zr0qaOxhjff6yGhcKdv3rX4(ExportActivity r0, int r1, boolean r2) {
        ExportSettings r02 = r0.settings;
        if (r2 == false) goto L5;
        int r12 = r1 | r02.types;
    L6:
        r02.types = r12;
        return;
    L5:
        int r22 = r02.types;
        r12 = (~r1) & r22;
        goto L6
    }

    public static void $r8$lambda$anjpbMsWdHeNsPc8nKOvMM4OfoA(ExportActivity r1, int r2) {
        ExportSettings r12 = r1.settings;
        if (r2 != 0) goto L5;
        AbstractWriter.Format r22 = AbstractWriter.Format.Html;
    L9:
        r12.format = r22;
        return;
    L5:
        if (r2 == 1) goto L7;
        r22 = AbstractWriter.Format.HtmlAndJson;
        goto L9
    L7:
        r22 = AbstractWriter.Format.Json;
        goto L9
    }

    /* renamed from: $r8$lambda$lelC_Sfy9tZdhla-7ngXsAJQz2w, reason: not valid java name */
    public static void m1405$r8$lambda$lelC_Sfy9tZdhla7ngXsAJQz2w(ExportActivity r0, int r1, boolean r2) {
        ExportSettings.MediaSettings r02 = r0.settings.media;
        if (r2 == false) goto L5;
        int r12 = r1 | r02.type;
    L6:
        r02.type = r12;
        return;
    L5:
        int r22 = r02.type;
        r12 = (~r1) & r22;
        goto L6
    }

    public static boolean $r8$lambda$lr3s_ljEfZ5R2DSqgwPmtSpoz_I(ExportActivity r0, int r1) {
        if ((r0.settings.media.type & r1) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    /* renamed from: $r8$lambda$vbrcMRtE7h-rED62mVswu3a4yfo, reason: not valid java name */
    public static boolean m1406$r8$lambda$vbrcMRtE7hrED62mVswu3a4yfo(ExportActivity r0, int r1) {
        if ((r0.settings.types & r1) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    static {
        Companion = new Companion(null);
        formats = new CharSequence[]{"HTML", "JSON", "HTML and JSON"};
    }

    public ExportActivity() {
        this(null, 1, null);
    }

    public ExportActivity(TLRPC.InputPeer r15) {
        this.peer = r15;
        this.settings = new ExportSettings();
        SwitchGroup r0 = new SwitchGroup(this, ExportItem.EXPORT_SETTINGS, 0, "Main settings", null, false, 16, null);
        r0.add(ExportItem.ACCOUNT_INFO, 0, type(1), setType(1), "Account Info");
        r0.add(ExportItem.CONTACTS_LIST, 0, type(4), setType(4), "Contacts");
        r0.add(ExportItem.STORY_ARCHIVE, 0, type(2048), setType(2048), "Stories");
        r0.add(ExportItem.ACTIVE_SESSIONS, 0, type(8), setType(8), "Sessions");
        this.exportSettings = r0;
        SwitchGroup r2 = new SwitchGroup(this, ExportItem.CHATS_SETTINGS, 0, "Chats settings", null, false, 16, null);
        r2.add(ExportItem.PERSONAL_CHATS, 0, type(32), setType(32), "Personal chats");
        r2.add(ExportItem.BOT_CHATS, 0, type(64), setType(64), "Bots");
        r2.add(ExportItem.PRIVATE_GROUPS, 0, type(128), setType(128), "Private groups");
        r2.add(ExportItem.PRIVATE_CHANNELS, 0, type(512), setType(512), "Private channels");
        r2.add(ExportItem.PUBLIC_GROUPS, 0, type(256), setType(256), "Public groups");
        r2.add(ExportItem.PUBLIC_CHANNELS, 0, type(1024), setType(1024), "Public channels");
        this.chatsSettings = r2;
        SwitchGroup r3 = new SwitchGroup(this, ExportItem.MEDIA_SETTINGS, 0, "Media settings", null, false, 16, null);
        r3.add(ExportItem.PHOTOS, 0, media(1), setMedia(1), "Photos");
        r3.add(ExportItem.VIDEOS, 0, media(2), setMedia(2), "Videos");
        r3.add(ExportItem.VOICE_MESSAGES, 0, media(4), setMedia(4), "Voice messages");
        r3.add(ExportItem.VIDEO_MESSAGES, 0, media(8), setMedia(8), "Video messages");
        r3.add(ExportItem.STICKERS, 0, media(16), setMedia(16), "Stickers");
        r3.add(ExportItem.GIFS, 0, media(32), setMedia(32), "GIFs");
        r3.add(ExportItem.FILES, 0, media(64), setMedia(64), "Files");
        this.mediaSettings = r3;
    }

    public /* synthetic */ ExportActivity(TLRPC.InputPeer r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = null;
    L5:
        this(r1);
    }

    private final int getIndexOfFormat() {
        AbstractWriter.Format r1 = this.settings.format;
        if (r1 != null) goto L5;
        int r12 = -1;
    L6:
        int r0 = 1;
        if (r12 == 1) goto L12;
        r0 = 2;
        if (r12 == 2) goto L12;
        return 0;
    L12:
        return r0;
    L5:
        r12 = WhenMappings.$EnumSwitchMapping$1[r1.ordinal()];
        goto L6
    }

    private final BooleanSupplier media(int r2) {
        return new ExportActivity$$ExternalSyntheticLambda2(this, r2);
    }

    private final SwitchGroup.Setter setMedia(int r2) {
        return new ExportActivity$$ExternalSyntheticLambda3(this, r2);
    }

    private final SwitchGroup.Setter setType(int r2) {
        return new ExportActivity$$ExternalSyntheticLambda0(this, r2);
    }

    private final BooleanSupplier type(int r2) {
        return new ExportActivity$$ExternalSyntheticLambda1(this, r2);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r4, int r5, Object... r6) {
        if (r4 != ExportController.FINISH_NOTIFICATION) goto L6;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.contact_check, "Export complete!").show();
        return;
    L6:
        ExportController.ProcessingState r3 = (ExportController.ProcessingState) r6[0];
        float r42 = r3.substepsPassed / r3.substepsTotal;
        if (r3.bytesCount <= 0) goto L10;
        StringCompanionObject r52 = StringCompanionObject.INSTANCE;
        String r32 = String.format(Locale.US, "Downloading %s\n%s / %s", Arrays.copyOf(new Object[]{r3.bytesName, AndroidUtilities.formatFileSize(r3.bytesLoaded), AndroidUtilities.formatFileSize(r3.bytesCount)}, 3));
    L13:
        FileLog.e("[EXPORT] " + r32 + ", " + r42);
        return;
    L10:
        if (r3.entityCount <= 0) goto L12;
        StringCompanionObject r53 = StringCompanionObject.INSTANCE;
        r32 = String.format(Locale.US, "Exporting %s (%d / %d)\nMessage %d / %d", Arrays.copyOf(new Object[]{r3.entityName, Integer.valueOf(r3.entityIndex + 1), Integer.valueOf(r3.entityCount), Integer.valueOf(r3.itemIndex), Integer.valueOf(r3.itemCount)}, 5));
        goto L13
    L12:
        r32 = "Exporting " + r3.step.name();
        goto L13
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r16, UniversalAdapter r17) {
        r16.add(UItem.asHeader("Export settings"));
        this.exportSettings.fill(r16);
        this.chatsSettings.fill(r16);
        this.mediaSettings.fill(r16);
        r16.add(UItem.asShadow());
        r16.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, ExportItem.FORMAT, "Select export result type", 0, formats[getIndexOfFormat()], null, null, false, null, false, false, null, 1972, null));
        r16.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, ExportItem.START_EXPORT, "Start Export", 0, null, null, null, false, null, false, false, null, 1980, null));
        r16.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, ExportItem.VIEW_JSON_EXPORT, "Open Json Export", 0, null, null, null, false, null, false, false, null, 1980, null));
        r16.add(UItem.asShadow("Here you can export your chats."));
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return "Export Chats";
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onActivityResultFragment(int r2, int r3, Intent r4) {
        super.onActivityResultFragment(r2, r3, r4);
        if (r2 == 1337) goto L5;
        return;
    L5:
        if (r3 != (-1)) goto L20;
        if (r4 == null) goto L21;
        Uri r22 = r4.getData();     // Catch: Exception -> L12
        if (r22 == null) goto L22;
        Uri r23 = DocumentsContract.buildDocumentUriUsingTree(r22, DocumentsContract.getTreeDocumentId(r22));     // Catch: Exception -> L12
        presentFragment(new DialogsView(AndroidPickerUtils.getPath(getParentActivity(), r23)));     // Catch: Exception -> L12
        return;
    L22:
        return;
    L12:
        e = move-exception;
        if (ExteraConfig.getUseGoogleCrashlytics() == false) goto L23;
        FirebaseCrashlytics.getInstance().recordException(e);
        return;
    L23:
        return;
    L21:
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r7, View r8, int r9, float r10, float r11) {
        int r82 = r7.id;
        if (r82 > 0) goto L5;
        return;
    L5:
        if (r82 > ExportItem.getEntries().size()) goto L25;
        ExportItem r83 = ExportItem.getEntries().get(r7.id - 1);
        switch(WhenMappings.$EnumSwitchMapping$0[r83.ordinal()]) {
            case 1: goto L18;
            case 2: goto L18;
            case 3: goto L18;
            case 4: goto L18;
            case 5: goto L18;
            case 6: goto L17;
            case 7: goto L17;
            case 8: goto L17;
            case 9: goto L17;
            case 10: goto L17;
            case 11: goto L17;
            case 12: goto L17;
            case 13: goto L16;
            case 14: goto L16;
            case 15: goto L16;
            case 16: goto L16;
            case 17: goto L16;
            case 18: goto L16;
            case 19: goto L16;
            case 20: goto L16;
            case 21: goto L15;
            case 22: goto L11;
            case 23: goto L10;
            default: goto L9;
        };
    L9:
        ExportActivity r0 = this;
    L19:
        UniversalAdapter r6 = r0.getListView$TMessagesProj().adapter;
        if (r6 == null) goto L24;
        r6.update(true);
        return;
    L24:
        return;
    L10:
        Intent r72 = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
        r72.addCategory("android.intent.category.DEFAULT");
        startActivityForResult(Intent.createChooser(r72, "Choose a directory"), 1337);
        goto L9
    L11:
        TLRPC.InputPeer r73 = this.peer;
        if (r73 == null) goto L14;
        this.settings.singlePeer = r73;
    L14:
        BulletinFactory.of(this).createErrorBulletin("Starting export...").show();
        this.settings.media.sizeLimit = 2097152000;
        ExportController.getInstance(UserConfig.selectedAccount).startExport(this.settings);
        goto L9
    L15:
        r0 = this;
        r0.showListDialog(r7, formats, "Select export result type", getIndexOfFormat(), new ExportActivity$$ExternalSyntheticLambda4(this));
        goto L19
    L16:
        r0 = this;
        r0.mediaSettings.onClick(r7);
        goto L19
    L17:
        r0 = this;
        r0.chatsSettings.onClick(r7);
        goto L19
    L18:
        r0 = this;
        r0.exportSettings.onClick(r7);
        goto L19
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, ExportController.INITIALIZATING_NOTIFICATION);
        getNotificationCenter().addObserver(this, ExportController.DIALOGS_LIST_NOTIFICATION);
        getNotificationCenter().addObserver(this, ExportController.PERSONAL_INFO_NOTIFICATION);
        getNotificationCenter().addObserver(this, ExportController.USERPICS_NOTIFICATION);
        getNotificationCenter().addObserver(this, ExportController.STORIES_NOTIFICATION);
        getNotificationCenter().addObserver(this, ExportController.CONTACTS_NOTIFICATION);
        getNotificationCenter().addObserver(this, ExportController.SESSIONS_NOTIFICATION);
        getNotificationCenter().addObserver(this, ExportController.OTHER_DATA_NOTIFICATION);
        getNotificationCenter().addObserver(this, ExportController.DIALOGS_NOTIFICATION);
        getNotificationCenter().addObserver(this, ExportController.FINISH_NOTIFICATION);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, ExportController.INITIALIZATING_NOTIFICATION);
        getNotificationCenter().removeObserver(this, ExportController.DIALOGS_LIST_NOTIFICATION);
        getNotificationCenter().removeObserver(this, ExportController.PERSONAL_INFO_NOTIFICATION);
        getNotificationCenter().removeObserver(this, ExportController.USERPICS_NOTIFICATION);
        getNotificationCenter().removeObserver(this, ExportController.STORIES_NOTIFICATION);
        getNotificationCenter().removeObserver(this, ExportController.CONTACTS_NOTIFICATION);
        getNotificationCenter().removeObserver(this, ExportController.SESSIONS_NOTIFICATION);
        getNotificationCenter().removeObserver(this, ExportController.OTHER_DATA_NOTIFICATION);
        getNotificationCenter().removeObserver(this, ExportController.DIALOGS_NOTIFICATION);
        getNotificationCenter().removeObserver(this, ExportController.FINISH_NOTIFICATION);
        super.onFragmentDestroy();
    }
}
