package com.exteragram.messenger.components;

import android.content.Context;
import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import androidx.core.content.ContextCompat;
import androidx.media3.common.MimeTypes;
import com.exteragram.messenger.backup.PreferencesUtils;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.google.android.gms.cast.MediaTrack;
import java.io.File;
import java.util.ArrayList;
import java.util.EnumMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00172\u00020\u0001:\u0002\u0016\u0017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J \u0010\u0011\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0013\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/exteragram/messenger/components/BlendedReplyFileText;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "icons", "Ljava/util/EnumMap;", "Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;", "Lcom/exteragram/messenger/components/BlendedReplyColorIconSpan;", "build", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "reply", "Lorg/telegram/messenger/MessageObject;", "type", "fontMetrics", "Landroid/graphics/Paint$FontMetricsInt;", "formatCaption", MediaTrack.ROLE_CAPTION, "createIcon", "res", _UrlKt.FRAGMENT_ENCODE_SET, "AttachType", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBlendedReplySpans.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BlendedReplySpans.kt\ncom/exteragram/messenger/components/BlendedReplyFileText\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,172:1\n410#2,7:173\n1#3:180\n*S KotlinDebug\n*F\n+ 1 BlendedReplySpans.kt\ncom/exteragram/messenger/components/BlendedReplyFileText\n*L\n143#1:173,7\n*E\n"})
/* loaded from: classes4.dex */
public final class BlendedReplyFileText {
    public static final Companion Companion = null;
    private final EnumMap<AttachType, BlendedReplyColorIconSpan> icons;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001d\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0017"}, d2 = {"Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;", _UrlKt.FRAGMENT_ENCODE_SET, "iconRes", _UrlKt.FRAGMENT_ENCODE_SET, "stringRes", "<init>", "(Ljava/lang/String;IILjava/lang/Integer;)V", "getIconRes", "()I", "getStringRes", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "MUSIC", "GIFT", "VOICE", "ROUND_VIDEO", "PHOTO", "VIDEO", "PLUGIN", "BACKUP", "ICON_PACK", "FILE", "UNKNOWN", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum AttachType extends Enum<AttachType> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ AttachType[] $VALUES = null;
        public static final AttachType BACKUP = null;
        public static final AttachType FILE = null;
        public static final AttachType GIFT = null;
        public static final AttachType ICON_PACK = null;
        public static final AttachType MUSIC = null;
        public static final AttachType PHOTO = null;
        public static final AttachType PLUGIN = null;
        public static final AttachType ROUND_VIDEO = null;
        public static final AttachType UNKNOWN = null;
        public static final AttachType VIDEO = null;
        public static final AttachType VOICE = null;
        private final int iconRes;
        private final Integer stringRes;

        private static final /* synthetic */ AttachType[] $values() {
            return new AttachType[]{MUSIC, GIFT, VOICE, ROUND_VIDEO, PHOTO, VIDEO, PLUGIN, BACKUP, ICON_PACK, FILE, UNKNOWN};
        }

        static {
            MUSIC = new AttachType("MUSIC", 0, R.drawable.filled_widget_music, null, 2, null);
            GIFT = new AttachType("GIFT", 1, R.drawable.gift, Integer.valueOf(R.string.ActionStarGift));
            VOICE = new AttachType("VOICE", 2, R.drawable.msg_filled_data_voice, Integer.valueOf(R.string.Voice));
            ROUND_VIDEO = new AttachType("ROUND_VIDEO", 3, R.drawable.filled_profile_video_24, Integer.valueOf(R.string.AttachRound));
            PHOTO = new AttachType("PHOTO", 4, R.drawable.msg_filled_reply_image, Integer.valueOf(R.string.StoryPhoto));
            VIDEO = new AttachType("VIDEO", 5, R.drawable.filled_profile_video_24, Integer.valueOf(R.string.StoryVideo));
            PLUGIN = new AttachType("PLUGIN", 6, R.drawable.plugins_filled, null, 2, null);
            BACKUP = new AttachType("BACKUP", 7, R.drawable.filled_profile_settings, null, 2, null);
            ICON_PACK = new AttachType("ICON_PACK", 8, R.drawable.stickers_filled, null, 2, null);
            FILE = new AttachType("FILE", 9, R.drawable.msg_filled_reply_files, null, 2, null);
            UNKNOWN = new AttachType("UNKNOWN", 10, R.drawable.msg_filled_reply_files, null, 2, null);
            AttachType[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        AttachType(String r1, int r2, int r3, Integer r4) {
            this.iconRes = r3;
            this.stringRes = r4;
        }

        /* synthetic */ AttachType(String r1, int r2, int r3, Integer r4, int r5, DefaultConstructorMarker r6) {
            if ((r5 & 2) == 0) goto L5;
            r4 = null;
        L5:
            this(r1, r2, r3, r4);
        }

        public static AttachType valueOf(String r1) {
            return (AttachType) Enum.valueOf(AttachType.class, r1);
        }

        public static AttachType[] values() {
            return (AttachType[]) $VALUES.clone();
        }

        public final int getIconRes() {
            return this.iconRes;
        }

        public final Integer getStringRes() {
            return this.stringRes;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¨\u0006\b²\u0006\f\u0010\t\u001a\u0004\u0018\u00010\nX\u008a\u0084\u0002"}, d2 = {"Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getType", "Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;", "reply", "Lorg/telegram/messenger/MessageObject;", "TMessagesProj", "file", "Ljava/io/File;"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nBlendedReplySpans.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BlendedReplySpans.kt\ncom/exteragram/messenger/components/BlendedReplyFileText$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,172:1\n1#2:173\n*E\n"})
    public static final class Companion {
        public static File $r8$lambda$q50XyqiFF0WlGV82xdVe1wcVvn4(MessageObject r3) {
            if (r3.type != 9) goto L14;
            String r32 = ChatUtils.getInstance().getPathToMessage(r3);
            if (r32 == null) goto L14;
            if (r32.length() > 0) goto L11;
            r32 = null;
        L11:
            if (r32 == null) goto L14;
            return new File(r32);
        L14:
            return null;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private static final boolean getType$isBackup(Lazy<? extends File> r2) {
            File r22 = getType$lambda$2(r2);
            if (r22 != null) goto L5;
        L7:
            return false;
        L5:
            if (PreferencesUtils.getInstance().isBackup(r22) != true) goto L7;
            return true;
        }

        private static final boolean getType$isFile(MessageObject r1) {
            if (r1.type != 9) goto L6;
            return true;
        L6:
            return false;
        }

        private static final boolean getType$isGif(MessageObject r1, String r2) {
            TLRPC.Document r12 = r1.getDocument();
            if (r12 == null) goto L5;
            String r13 = r12.mime_type;
        L7:
            if (Intrinsics.areEqual(r13, "image/gif") == false) goto L9;
            return true;
        L9:
            if (MimeTypes.isVideo(r2) == true) goto L15;
            return false;
        L15:
            return true;
        L5:
            r13 = null;
            goto L7
        }

        private static final boolean getType$isGift(MessageObject r0) {
            return r0.isAnyGift();
        }

        private static final boolean getType$isIconPack(Lazy<? extends File> r2, MessageObject r3) {
            File r22 = getType$lambda$2(r2);
            if (r22 != null) goto L5;
        L7:
            return false;
        L5:
            if (IconManager.INSTANCE.isIconPack(r22.getPath(), r3) != true) goto L7;
            return true;
        }

        private static final boolean getType$isMusic(MessageObject r1) {
            if (r1.type != 14) goto L6;
            return true;
        L6:
            return false;
        }

        private static final boolean getType$isPhoto(MessageObject r2, String r3) {
            if (r2.type != 1) goto L5;
        L11:
            return true;
        L5:
            if (getType$isFile(r2) == true) goto L7;
            return false;
        L7:
            if (MimeTypes.isImage(r3) == true) goto L11;
            return false;
        }

        private static final boolean getType$isPlugin(Lazy<? extends File> r2, MessageObject r3) {
            File r22 = getType$lambda$2(r2);
            if (r22 != null) goto L5;
        L7:
            return false;
        L5:
            if (PluginsController.Companion.isPlugin(r22, r3) != true) goto L7;
            return true;
        }

        private static final boolean getType$isRound(MessageObject r1) {
            if (r1.type != 5) goto L6;
            return true;
        L6:
            return false;
        }

        private static final boolean getType$isVideo(MessageObject r2, String r3) {
            if (r2.type != 3) goto L5;
            return true;
        L5:
            if (getType$isFile(r2) == true) goto L7;
            return false;
        L7:
            if (MimeTypes.isVideo(r3) == true) goto L14;
            return false;
        L14:
            return true;
        }

        private static final boolean getType$isVoice(MessageObject r1) {
            if (r1.type != 2) goto L6;
            return true;
        L6:
            return false;
        }

        private static final File getType$lambda$2(Lazy<? extends File> r0) {
            return r0.getValue();
        }

        @JvmStatic
        public final AttachType getType(MessageObject r3) {
            if (r3 == null) goto L4;
            TLRPC.Document r2 = r3.getDocument();
            if (r2 == null) goto L8;
            String r22 = r2.mime_type;
        L9:
            Lazy r0 = LazyKt.lazy(LazyThreadSafetyMode.NONE, new BlendedReplyFileText$Companion$$ExternalSyntheticLambda0(r3));
            if (getType$isMusic(r3) == false) goto L14;
            return AttachType.MUSIC;
        L14:
            if (getType$isGift(r3) == false) goto L18;
            return AttachType.GIFT;
        L18:
            if (getType$isVoice(r3) == false) goto L22;
            return AttachType.VOICE;
        L22:
            if (getType$isRound(r3) == false) goto L26;
            return AttachType.ROUND_VIDEO;
        L26:
            if (getType$isPhoto(r3, r22) == false) goto L30;
            return AttachType.PHOTO;
        L30:
            if (getType$isVideo(r3, r22) == false) goto L34;
            return AttachType.VIDEO;
        L34:
            if (getType$isGif(r3, r22) == false) goto L38;
            return AttachType.UNKNOWN;
        L38:
            if (getType$isPlugin(r0, r3) == false) goto L42;
            return AttachType.PLUGIN;
        L42:
            if (getType$isBackup(r0) == false) goto L46;
            return AttachType.BACKUP;
        L46:
            if (getType$isIconPack(r0, r3) == false) goto L50;
            return AttachType.ICON_PACK;
        L50:
            if (getType$isFile(r3) == false) goto L54;
            return AttachType.FILE;
        L54:
            return AttachType.UNKNOWN;
        L8:
            r22 = null;
            goto L9
        L4:
            return AttachType.UNKNOWN;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public BlendedReplyFileText() {
        this.icons = new EnumMap(AttachType.class);
    }

    private final BlendedReplyColorIconSpan createIcon(Context r1, int r2) {
        BlendedReplyColorIconSpan r0 = new BlendedReplyColorIconSpan(ContextCompat.getDrawable(r1, r2).mutate());
        r0.setScale(0.7f, 0.7f);
        return r0;
    }

    private final CharSequence formatCaption(MessageObject r8, CharSequence r9, Paint.FontMetricsInt r10) {
        CharSequence r7 = Emoji.replaceEmoji(StringsKt.replace$default(StringsKt.take(r9.toString(), 150), '\n', ' ', false, 4, null), r10, true);
        TLRPC.Message r82 = r8.messageOwner;
        if (r82 == null) goto L9;
        ArrayList<TLRPC.MessageEntity> r83 = r82.entities;
        if (r83 == null) goto L10;
        ArrayList r1 = new ArrayList(r83);
        LocaleUtils.parseCustomEmojis(r7, r1);
        Spannable r72 = MessageObject.replaceAnimatedEmoji(r7, r1, r10, true);
        MediaDataController.addTextStyleRuns(r83, r9, r72);
        return r72;
    L10:
        return r7;
    L9:
        return r7;
    }

    @JvmStatic
    public static final AttachType getType(MessageObject r1) {
        return Companion.getType(r1);
    }

    public final CharSequence build(Context r4, MessageObject r5, AttachType r6, Paint.FontMetricsInt r7) {
        EnumMap<AttachType, BlendedReplyColorIconSpan> r0 = this.icons;
        BlendedReplyColorIconSpan r1 = r0.get(r6);
        if (r1 != null) goto L5;
        r1 = createIcon(r4, r6.getIconRes());
        r0.put(r6, r1);
    L5:
        BlendedReplyColorIconSpan r12 = r1;
        if (r6 != AttachType.MUSIC) goto L9;
        String r42 = r5.getMusicAuthor() + " – " + r5.getMusicTitle();
    L19:
        SpannableStringBuilder r43 = new SpannableStringBuilder("d ").append(Emoji.replaceEmoji(r42, r7, false));
        r43.setSpan(r12, 0, 1, 33);
        r43.setSpan(new BlendedReplyColorSpan(), 0, r43.length(), 33);
        CharSequence r62 = r5.caption;
        if (r62 != null) goto L22;
    L26:
        return r43;
    L22:
        if (r62.length() == 0) goto L26;
        return r43.append(", ").append(formatCaption(r5, r5.caption, r7));
    L9:
        if (r6.getStringRes() == null) goto L11;
        r42 = LocaleController.getString(r6.getStringRes().intValue());
        goto L19
    L11:
        r42 = r5.getDocumentName();
        if (r42 != null) goto L14;
    L18:
        r42 = LocaleController.getString(R.string.AttachDocument);
        goto L19
    L14:
        if (r42.length() > 0) goto L17;
        r42 = null;
    L17:
        if (r42 != null) goto L19;
        goto L18
    }
}
