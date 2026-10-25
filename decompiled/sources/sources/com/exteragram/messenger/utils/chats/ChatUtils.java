package com.exteragram.messenger.utils.chats;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.preferences.utils.SettingsRegistry$$ExternalSyntheticBackport2;
import com.exteragram.messenger.utils.network.RemoteUtils;
import com.exteragram.messenger.utils.system.SystemUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.WebFile;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChannelAdminLogActivity;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.TranscribeButton;
import org.telegram.ui.Components.VideoPlayer;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes.dex */
public class ChatUtils {
    private static final ChatUtils[] Instance = null;
    private static SpannableStringBuilder channelIcon;
    private static SpannableStringBuilder editedIcon;
    private static final Object[] lockObjects = null;
    private static final CharsetDecoder textDecoder = null;
    public static final DispatchQueue utilsQueue = null;
    private final int selectedAccount;

    public static /* synthetic */ void $r8$lambda$0FiI7QD8hr21i5zbWvUFGDW_h4s(ChatUtils r0, Utilities.Callback r1, TLRPC.messages_BotResults r2) {
        r0.lambda$sendBotRequest$2(r1, r2);
    }

    /* renamed from: $r8$lambda$0U0Jpn0Pb-lteyAzZO6ueMuHI9o, reason: not valid java name */
    public static /* synthetic */ void m1770$r8$lambda$0U0Jpn0PblteyAzZO6ueMuHI9o(ChatUtils r0, Utilities.Callback r1, TLRPC.messages_BotResults r2) {
        r0.lambda$sendBotRequest$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$2F046n2VCFYnTQRjQ8eAQwAaIS0(ChatUtils r0, long r1, Utilities.Callback r3, TLRPC.User r4) {
        r0.lambda$searchChat$11(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$F8nvp2kwnJ9ygHkukdrkYcoFxXE(ChatUtils r0, boolean r1, TLRPC.User r2, String r3, Utilities.Callback r4, Utilities.Callback r5, String r6, TLObject r7, TLRPC.TL_error r8) {
        r0.lambda$sendInlineBotRequest$5(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$G4RFT_7T0gaz1PlaSv9WnyvEjYE(ChatUtils r0, Utilities.Callback r1, TLRPC.messages_BotResults r2) {
        r0.lambda$searchChat$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$IHPqvvWO8wXH5WHQMQapqKdSicE(Utilities.Callback r0, TLRPC.TL_user r1, TLRPC.User r2) {
        if (r2 == null) goto L5;
        r0.run(r2);
        return;
    L5:
        r1.username = null;
        r0.run(r1);
    }

    /* renamed from: $r8$lambda$LKAEB95s0mjTBZ_UTwedLjRg-9s, reason: not valid java name */
    public static /* synthetic */ void m1771$r8$lambda$LKAEB95s0mjTBZ_UTwedLjRg9s(ChatUtils r0, Utilities.Callback r1, Long r2) {
        r0.lambda$resolveChannel$19(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$LULA4D7H6gvsk3sg1BiBQTFC5hI(ChatUtils r0, long r1, Utilities.Callback r3, Long r4) {
        r0.lambda$resolveUser$10(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$OvtIOADdagOnLq4RqhSNiy2_UcI(String r6, Activity r7, Utilities.Callback r8) {
        MediaController.saveFile(r6, r7, 1, null, null, r8);
    }

    public static /* synthetic */ void $r8$lambda$SnURkTGvnoWYMEQczLy0p2l__h0(Utilities.Callback r4, TLRPC.User r5) {
        if (r5 != null) goto L4;
    L7:
        r4.run(null);
        return;
    L4:
        if (r5.access_hash == 0) goto L7;
        r4.run(r5);
    }

    public static /* synthetic */ void $r8$lambda$XWh0dARHfqR_UImIDO2O3JaMIgk(String r1, Activity r2, Utilities.Callback r3) {
        AndroidUtilities.runOnUIThread(new ChatUtils$$ExternalSyntheticLambda4(r1, r2, r3));
    }

    public static /* synthetic */ void $r8$lambda$YpYo_S9uo9F5tb15vPZSRnOcYKg(Utilities.Callback r0, TLRPC.TL_channel r1, TLRPC.Chat r2) {
        r0.run((TLRPC.Chat) SettingsRegistry$$ExternalSyntheticBackport2.m(r2, r1));
    }

    public static /* synthetic */ void $r8$lambda$Zp5ooL2dJDg4AYMUHeKagZrDoMA(ChatUtils r0, int r1) {
        r0.lambda$sendInlineBotRequest$6(r1);
    }

    /* renamed from: $r8$lambda$_mrT6MZLhXrk8326k5kImfX-_3Q, reason: not valid java name */
    public static /* synthetic */ void m1772$r8$lambda$_mrT6MZLhXrk8326k5kImfX_3Q(String r4, boolean r5, Activity r6, Utilities.Callback r7) {
        if (TextUtils.isEmpty(r4) == true) goto L21;
        FileLog.e(r4);     // Catch: Exception -> L17
        if (r5 == false) goto L8;
        AndroidUtilities.runOnUIThread(new ChatUtils$$ExternalSyntheticLambda6(r4, r6, r7));     // Catch: Exception -> L17
        return;
    L8:
        Bitmap r52 = BitmapFactory.decodeFile(r4);     // Catch: Exception -> L17
        if (r52 != null) goto L12;
        return;
    L12:
        if (r4.endsWith(".webp") == false) goto L14;
        String r42 = r4.replace(".webp", ".png");     // Catch: Exception -> L17
    L15:
        File r1 = new File(r42);     // Catch: Exception -> L17
        FileOutputStream r43 = new FileOutputStream(r1);     // Catch: Exception -> L17
        r52.compress(Bitmap.CompressFormat.PNG, 100, r43);     // Catch: Exception -> L17
        r43.close();     // Catch: Exception -> L17
        AndroidUtilities.runOnUIThread(new ChatUtils$$ExternalSyntheticLambda7(r1, r6, r7));     // Catch: Exception -> L17
        return;
    L14:
        r42 = r4.concat(".png");     // Catch: Exception -> L17
    L17:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    /* renamed from: $r8$lambda$aqqFCMjUQrriQ-J2sDuy_DRQmVg, reason: not valid java name */
    public static /* synthetic */ void m1773$r8$lambda$aqqFCMjUQrriQJ2sDuy_DRQmVg(String r6, Activity r7, Utilities.Callback r8) {
        MediaController.saveFile(r6, r7, 1, null, null, r8);
    }

    /* renamed from: $r8$lambda$f350cj5ydHjeLkr5SGQV-uvB-Lo, reason: not valid java name */
    public static /* synthetic */ void m1774$r8$lambda$f350cj5ydHjeLkr5SGQVuvBLo(ChatUtils r0, long r1, Utilities.Callback r3, TLRPC.User r4) {
        r0.lambda$searchUser$7(r1, r3, r4);
    }

    /* renamed from: $r8$lambda$k-oWZqXzXmvcRgcXx1SeTxcoDak, reason: not valid java name */
    public static /* synthetic */ void m1775$r8$lambda$koWZqXzXmvcRgcXx1SeTxcoDak(ChatUtils r0, Utilities.Callback r1, TLRPC.messages_BotResults r2) {
        r0.lambda$searchUser$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ktlp8ykPWn1HJ6mcC0_I9lN2KlM(ChatUtils r0, String r1, boolean r2, Utilities.Callback r3, TLRPC.User r4) {
        r0.lambda$sendBotRequest$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$phdmeN4EqF1uV42OxVKzAbJMHvU(ChatUtils r0, boolean r1, TLObject r2, TLRPC.User r3, String r4, Utilities.Callback r5, Utilities.Callback r6, String r7) {
        r0.lambda$sendInlineBotRequest$4(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$w9Ub66jK03brNLIwP7qvBY2boE8(File r6, Activity r7, Utilities.Callback r8) {
        MediaController.saveFile(r6.toString(), r7, 0, null, null, r8);
    }

    static {
        utilsQueue = new DispatchQueue("utilsQueue");
        Instance = new ChatUtils[16];
        lockObjects = new Object[16];
        textDecoder = StandardCharsets.UTF_8.newDecoder();
        int r1 = 0;
    L3:
        if (r1 >= 16) goto L5;
        lockObjects[r1] = new Object();
        r1 = r1 + 1;
        goto L3
    }

    public ChatUtils(int r1) {
        this.selectedAccount = r1;
    }

    public static boolean applyChannelPostContent(TLRPC.Message r5, TLRPC.Message r6) {
        int r0 = 0;
        if (r5 == null) goto L46;
        if (r6 == null) goto L46;
        TLRPC.MessageFwdHeader r1 = r5.fwd_from;
        if (r1 == null) goto L46;
        if (r1.saved_from_msg_id != r6.id) goto L46;
        if (MessageObject.peersEqual(r1.saved_from_peer, r6.peer_id) == false) goto L46;
        if (reactionsChanged(r5.reactions, r6.reactions) == false) goto L15;
    L26:
        boolean r12 = true;
    L27:
        r5.reactions = r6.reactions;
        r5.message = r6.message;
        TLRPC.MessageMedia r2 = r6.media;
        r5.media = r2;
        ArrayList<TLRPC.MessageEntity> r3 = r6.entities;
        r5.entities = r3;
        r5.edit_date = r6.edit_date;
        r5.edit_hide = r6.edit_hide;
        int r62 = r5.flags & (-1081985);
        if (r2 == null) goto L30;
        int r22 = 512;
    L31:
        int r63 = r62 | r22;
        if (r3 != null) goto L34;
    L36:
        int r23 = 0;
    L37:
        int r64 = r63 | r23;
        if (r5.edit_date == 0) goto L40;
        int r24 = 32768;
    L41:
        int r65 = r64 | r24;
        if (r5.reactions == null) goto L44;
        r0 = 1048576;
    L44:
        r5.flags = r65 | r0;
        return r12;
    L40:
        r24 = 0;
        goto L41
    L34:
        if (r3.isEmpty() == true) goto L36;
        r23 = 128;
        goto L37
    L30:
        r22 = 0;
        goto L31
    L15:
        if (TextUtils.equals(r5.message, r6.message) == false) goto L26;
        if (r5.edit_date != r6.edit_date) goto L26;
        if (r5.edit_hide != r6.edit_hide) goto L26;
        if (mediaChanged(r5.media, r6.media) == true) goto L26;
        if (entitiesChanged(r5.entities, r6.entities) == true) goto L26;
        r12 = false;
    L46:
        return false;
    }

    private static boolean entitiesChanged(ArrayList<TLRPC.MessageEntity> r8, ArrayList<TLRPC.MessageEntity> r9) {
        if (r8 != r9) goto L5;
        return false;
    L5:
        if (r8 != null) goto L7;
        int r1 = 0;
    L8:
        if (r9 != null) goto L10;
        int r2 = 0;
    L12:
        if (r1 == r2) goto L14;
        return true;
    L14:
        int r22 = 0;
    L15:
        if (r22 >= r1) goto L32;
        TLRPC.MessageEntity r4 = r8.get(r22);
        TLRPC.MessageEntity r5 = r9.get(r22);
        if (r4 == null) goto L29;
        if (r5 == null) goto L29;
        if (r4.getClass() != r5.getClass()) goto L28;
        if (r4.offset != r5.offset) goto L28;
        if (r4.length != r5.length) goto L28;
        if (TextUtils.equals(r4.url, r5.url) == false) goto L28;
    L31:
        r22 = r22 + 1;
    L28:
        return true;
    L29:
        if (r4 == r5) goto L31;
        return true;
    L32:
        return false;
    L10:
        r2 = r9.size();
        goto L12
    L7:
        r1 = r8.size();
        goto L8
    }

    public static long extractOwnerId(long r8) {
        long r0 = r8 >> 32;
        if (((r8 >> 16) & 255) != 63) goto L6;
        r0 = r0 | 2147483648L;
    L6:
        if (((r8 >> 24) & 255) != 0) goto L8;
        return r0;
    L8:
        return r0 + 4294967296L;
    }

    private static boolean fileExists(String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L5;
        return false;
    L5:
        File r0 = new File(r2);
        if (r0.exists() == true) goto L8;
    L11:
        return false;
    L8:
        if (r0.isFile() == false) goto L11;
        return true;
    }

    private static Pair<Long, String> getBotInfo() {
        String r0 = RemoteUtils.getStringConfigValue("search_bot", "7424190611:tgdb_search_bot");
        int r1 = r0.indexOf(":");
        if (r1 == (-1)) goto L10;
        long r2 = Long.parseLong(r0.substring(0, r1));     // Catch: NumberFormatException -> L7
        String r02 = r0.substring(r1 + 1);     // Catch: NumberFormatException -> L7
        return new Pair(Long.valueOf(r2), r02);
    L7:
        e = move-exception;
        FileLog.e(e);
    L10:
        return new Pair(7424190611L, "tgdb_search_bot");
    }

    public static CharSequence getChannelIcon() {
        if (channelIcon != null) goto L6;
        SpannableStringBuilder r0 = new SpannableStringBuilder("\u200d");
        channelIcon = r0;
        r0.setSpan(new ColoredImageSpan(Theme.chat_channelIconDrawable), 0, 1, 33);
    L6:
        return channelIcon;
    }

    public static String getDCName(int r1) {
        if (r1 != 1) goto L5;
        return "Miami FL, USA";
    L5:
        if (r1 != 2) goto L7;
        return "Amsterdam, NL";
    L7:
        if (r1 != 3) goto L9;
        return "Miami FL, USA";
    L9:
        if (r1 != 4) goto L11;
        return "Amsterdam, NL";
    L11:
        if (r1 == 5) goto L14;
        return null;
    L14:
        return "Singapore, SG";
    }

    public static CharSequence getEditedIcon() {
        if (editedIcon != null) goto L6;
        editedIcon = new SpannableStringBuilder("\u200d");
        ColoredImageSpan r0 = new ColoredImageSpan(Theme.chat_pencilIconDrawable);
        r0.setTranslateX(-AndroidUtilities.dp(1.0f));
        editedIcon.setSpan(r0, 0, 1, 33);
    L6:
        return editedIcon;
    }

    private String getExistingPath(File r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        if (r1.exists() == true) goto L6;
        return null;
    L6:
        return r1.toString();
    }

    private String getGifPath(TLRPC.Document r3, TLRPC.BotInlineResult r4) {
        if (r3 == null) goto L9;
        String r42 = getExistingPath(getFileLoader().getPathToAttach(r3, false));
        if (r42 == null) goto L8;
        return r42;
    L8:
        return getExistingPath(getFileLoader().getPathToAttach(r3, true));
    L9:
        if (r4 == null) goto L18;
        TLRPC.WebDocument r32 = r4.content;
        if (r32 == null) goto L20;
        WebFile r33 = WebFile.createWithWebDocument(r32);
        String r43 = getExistingPath(getFileLoader().getPathToAttach(r33, false));
        if (r43 == null) goto L17;
        return r43;
    L17:
        return getExistingPath(getFileLoader().getPathToAttach(r33, true));
    L20:
        return null;
    L18:
        return null;
    }

    public static ChatUtils getInstance() {
        return getInstance(UserConfig.selectedAccount);
    }

    public static ChatUtils getInstance(int r3) {
        ChatUtils[] r0 = Instance;
        ChatUtils r1 = r0[r3];
        if (r1 != null) goto L15;
        Object[] r2 = lockObjects;
        monitor-enter(r2);
        ChatUtils r12 = r0[r3];     // Catch: Throwable -> L9
        if (r12 != null) goto L11;
        r12 = new ChatUtils(r3);     // Catch: Throwable -> L9
        r0[r3] = r12;     // Catch: Throwable -> L9
    L11:
        monitor-exit(r2);     // Catch: Throwable -> L9
        return r12;
    L9:
        th = move-exception;
        throw th;
    L15:
        return r1;
    }

    private CharSequence getMessageCaption(MessageObject r4, MessageObject.GroupedMessages r5) {
        String r3 = MessagesController.getInstance(this.selectedAccount).getRestrictionReason(r4.messageOwner.restriction_reason);
        if (TextUtils.isEmpty(r3) == true) goto L6;
        return r3;
    L6:
        if (r4.isVoiceTranscriptionOpen() == true) goto L8;
    L11:
        CharSequence r32 = r4.caption;
        if (r32 == null) goto L15;
        return r32;
    L15:
        if (r5 != null) goto L17;
        return null;
    L17:
        int r42 = r5.messages.size();
        int r0 = 0;
        CharSequence r1 = null;
    L18:
        if (r0 >= r42) goto L25;
        CharSequence r2 = r5.messages.get(r0).caption;
        if (r2 == null) goto L24;
        if (r1 != null) goto L22;
        r1 = r2;
        goto L24
    L22:
        return null;
    L24:
        r0 = r0 + 1;
        goto L18
    L25:
        return r1;
    L8:
        if (TranscribeButton.isTranscribing(r4) == true) goto L11;
        return r4.getVoiceTranscription();
    }

    private CharSequence getMessageContent(MessageObject r3) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        String r2 = MessagesController.getInstance(this.selectedAccount).getRestrictionReason(r3.messageOwner.restriction_reason);
        if (TextUtils.isEmpty(r2) == true) goto L5;
        r0.append(r2);
    L10:
        return r0.toString();
    L5:
        CharSequence r22 = r3.caption;
        if (r22 == null) goto L8;
        r0.append(r22);
        goto L10
    L8:
        r0.append(r3.messageText);
        goto L10
    }

    private MessageObject getTargetMessageObjectFromGroup(MessageObject.GroupedMessages r6) {
        ArrayList<MessageObject> r5 = r6.messages;
        int r62 = r5.size();
        int r1 = 0;
        MessageObject r2 = null;
    L3:
        if (r1 >= r62) goto L9;
        MessageObject r3 = r5.get(r1);
        r1 = r1 + 1;
        MessageObject r32 = r3;
        if (TextUtils.isEmpty(r32.messageOwner.message) == true) goto L3;
        if (r2 != null) goto L7;
        r2 = r32;
        goto L3
    L7:
        return null;
    L9:
        return r2;
    }

    private int getUserProfileDC(TLRPC.User r3) {
        TLRPC.UserFull r2 = getMessagesController().getUserFull(r3.id);
        if (r2 == null) goto L11;
        TLRPC.Photo r22 = r2.profile_photo;
        if (r22 == null) goto L11;
        if ((r22 instanceof TLRPC.TL_photoEmpty) == true) goto L11;
        int r23 = r22.dc_id;
        if (r23 <= 0) goto L11;
        return r23;
    L11:
        TLRPC.UserProfilePhoto r24 = r3.photo;
        if (r24 != null) goto L14;
    L16:
        if (r24 != null) goto L18;
        return -1;
    L18:
        return r24.dc_id;
    L14:
        if (r24.personal == false) goto L16;
        return -1;
    }

    public static boolean hasRestrictionReason(ArrayList<TLRPC.RestrictionReason> r3, String r4) {
        if (r3 != null) goto L5;
    L17:
        return false;
    L5:
        if (TextUtils.isEmpty(r4) == true) goto L17;
        int r1 = 0;
    L9:
        if (r1 >= r3.size()) goto L17;
        TLRPC.RestrictionReason r2 = r3.get(r1);
        if (r2 == null) goto L16;
        if (r4.equals(r2.reason) == false) goto L16;
        return true;
    L16:
        r1 = r1 + 1;
        goto L9
    }

    public static boolean isTermsRestrictedMessage(MessageObject r1) {
        if (r1 == null) goto L9;
        TLRPC.Message r12 = r1.messageOwner;
        if (r12 != null) goto L6;
        return false;
    L6:
        if (hasRestrictionReason(r12.restriction_reason, "terms") == false) goto L12;
        return true;
    L12:
        return false;
    L9:
        return false;
    }

    private /* synthetic */ void lambda$resolveChannel$19(Utilities.Callback r5, Long r6) {
        if (r6 != null) goto L4;
    L7:
        r5.run(null);
        return;
    L4:
        if (r6.longValue() >= 0) goto L7;
        r5.run(getMessagesController().getChat(Long.valueOf(-r6.longValue())));
    }

    private /* synthetic */ void lambda$resolveUser$10(long r5, Utilities.Callback r7, Long r8) {
        if (r8 != null) goto L4;
    L9:
        r7.run(null);
        return;
    L4:
        if (r8.longValue() <= 0) goto L9;
        if (r8.longValue() != r5) goto L9;
        r7.run(getMessagesController().getUser(Long.valueOf(r5)));
    }

    private /* synthetic */ void lambda$searchChat$11(long r7, Utilities.Callback r9, TLRPC.User r10) {
        searchChat(r7, false, false, r9);
    }

    private /* synthetic */ void lambda$searchChat$13(Utilities.Callback r10, TLRPC.messages_BotResults r11) {
        if (r11 != null) goto L5;
    L40:
        r10.run(null);
        return;
    L5:
        if (r11.results.isEmpty() == true) goto L40;
        int r1 = 0;
        TLRPC.BotInlineResult r112 = r11.results.get(0);
        TLRPC.BotInlineMessage r2 = r112.send_message;
        if (r2 != null) goto L10;
    L38:
        r10.run(null);
        return;
    L10:
        if (TextUtils.isEmpty(r2.message) == true) goto L38;
        String[] r113 = r112.send_message.message.split("\n");
        if (r113.length >= 1) goto L16;
        r10.run(null);
        return;
    L16:
        TLRPC.TL_channel r22 = new TLRPC.TL_channel();
        int r4 = r113.length;
    L17:
        if (r1 >= r4) goto L30;
        String r5 = r113[r1].replaceAll("\\p{C}", _UrlKt.FRAGMENT_ENCODE_SET).trim();
        if (r5.startsWith("🆔") == false) goto L26;
        long r52 = Utilities.parseLong(r5.replaceAll("[^\\d-]", _UrlKt.FRAGMENT_ENCODE_SET)).longValue();     // Catch: Exception -> L42
        if (String.valueOf(r52).startsWith("-100") == false) goto L23;
        r22.id = Long.parseLong(String.valueOf(r52).substring(4));     // Catch: Exception -> L42
        goto L28
    L23:
        r22.id = r52;     // Catch: Exception -> L42
    L28:
        r1 = r1 + 1;
        goto L17
    L26:
        if (r5.startsWith("📧") == false) goto L28;
        r22.username = r5.substring(r5.indexOf(64) + 1).trim();
        goto L28
    L30:
        if (r22.id != 0) goto L33;
        r10.run(null);
        return;
    L33:
        String r114 = r22.username;
        if (r114 == null) goto L36;
        resolveChannel(r114, new ChatUtils$$ExternalSyntheticLambda9(r10, r22));
        return;
    L36:
        r10.run(r22);
    }

    private /* synthetic */ void lambda$searchUser$7(long r7, Utilities.Callback r9, TLRPC.User r10) {
        searchUser(r7, false, false, r9);
    }

    private /* synthetic */ void lambda$searchUser$9(Utilities.Callback r8, TLRPC.messages_BotResults r9) {
        if (r9 != null) goto L5;
    L37:
        r8.run(null);
        return;
    L5:
        if (r9.results.isEmpty() == true) goto L37;
        int r1 = 0;
        TLRPC.BotInlineResult r92 = r9.results.get(0);
        TLRPC.BotInlineMessage r2 = r92.send_message;
        if (r2 != null) goto L10;
    L35:
        r8.run(null);
        return;
    L10:
        if (TextUtils.isEmpty(r2.message) == true) goto L35;
        String[] r93 = r92.send_message.message.split("\n");
        if (r93.length >= 3) goto L16;
        r8.run(null);
        return;
    L16:
        TLRPC.TL_user r22 = new TLRPC.TL_user();
        int r3 = r93.length;
    L17:
        if (r1 >= r3) goto L25;
        String r4 = r93[r1].replaceAll("\\p{C}", _UrlKt.FRAGMENT_ENCODE_SET).trim();
        if (r4.startsWith("🆔") == false) goto L22;
        r22.id = Utilities.parseLong(r4.replaceAll("\\D+", _UrlKt.FRAGMENT_ENCODE_SET).trim()).longValue();
    L24:
        r1 = r1 + 1;
        goto L17
    L22:
        if (r4.startsWith("📧") == false) goto L24;
        r22.username = r4.substring(r4.indexOf(64) + 1).trim();
        goto L24
    L25:
        long r32 = r22.id;
        if (r32 != 0) goto L29;
        r8.run(null);
        return;
    L29:
        String r94 = r22.username;
        if (r94 == null) goto L33;
        resolveUser(r94, r32, new ChatUtils$$ExternalSyntheticLambda19(r8, r22));
        return;
    L33:
        r8.run(r22);
    }

    private /* synthetic */ void lambda$sendBotRequest$1(Utilities.Callback r1, TLRPC.messages_BotResults r2) {
        processBotResponse(r2, r1);
    }

    private /* synthetic */ void lambda$sendBotRequest$2(Utilities.Callback r1, TLRPC.messages_BotResults r2) {
        processBotResponse(r2, r1);
    }

    private /* synthetic */ void lambda$sendBotRequest$3(String r2, boolean r3, Utilities.Callback r4, TLRPC.User r5) {
        if (r5 == null) goto L5;
        sendInlineBotRequest(r5, r2, r3, new ChatUtils$$ExternalSyntheticLambda18(this, r4));
        return;
    L5:
        r4.run(null);
    }

    private /* synthetic */ void lambda$sendInlineBotRequest$4(boolean r2, TLObject r3, TLRPC.User r4, String r5, Utilities.Callback r6, Utilities.Callback r7, String r8) {
        if (r2 == false) goto L12;
        if ((r3 instanceof TLRPC.messages_BotResults) == true) goto L6;
    L7:
        Runnable r1 = sendInlineBotRequest(r4, r5, false, r6);
        if (r7 == null) goto L22;
        r7.run(r1);
        return;
    L22:
        return;
    L6:
        if (((TLRPC.messages_BotResults) r3).results.isEmpty() == true) goto L7;
    L12:
        if ((r3 instanceof TLRPC.messages_BotResults) == false) goto L20;
        TLRPC.messages_BotResults r32 = (TLRPC.messages_BotResults) r3;
        if (r2 == false) goto L16;
    L18:
        r6.run(r32);
        return;
    L16:
        if (r32.cache_time == 0) goto L18;
        getMessageStorage().saveBotCache(r8, r32);
        goto L18
    L20:
        r6.run(null);
    }

    private /* synthetic */ void lambda$sendInlineBotRequest$5(boolean r2, TLRPC.User r3, String r4, Utilities.Callback r5, Utilities.Callback r6, String r7, TLObject r8, TLRPC.TL_error r9) {
        AndroidUtilities.runOnUIThread(new ChatUtils$$ExternalSyntheticLambda17(this, r2, r8, r3, r4, r5, r6, r7));
    }

    private /* synthetic */ void lambda$sendInlineBotRequest$6(int r2) {
        getConnectionsManager().cancelRequest(r2, false);
    }

    private static boolean mediaChanged(TLRPC.MessageMedia r4, TLRPC.MessageMedia r5) {
        if (r4 != r5) goto L6;
        return false;
    L6:
        if (r4 == null) goto L15;
        if (r5 == null) goto L15;
        if (r4.getClass() != r5.getClass()) goto L15;
        if (mediaIdentity(r4) == mediaIdentity(r5)) goto L14;
        return true;
    L14:
        return false;
    L15:
        return true;
    }

    private static long mediaIdentity(TLRPC.MessageMedia r2) {
        TLRPC.Photo r0 = r2.photo;
        if (r0 != null) goto L5;
        TLRPC.Document r02 = r2.document;
        if (r02 != null) goto L9;
        TLRPC.WebPage r03 = r2.webpage;
        if (r03 != null) goto L13;
        TLRPC.TL_game r04 = r2.game;
        if (r04 == null) goto L19;
        return r04.id;
    L19:
        return r2.id;
    L13:
        return r03.id;
    L9:
        return r02.id;
    L5:
        return r0.id;
    }

    private void processBotResponse(TLRPC.messages_BotResults r1, Utilities.Callback<String> r2) {
        if (r1 != null) goto L4;
    L11:
        r2.run(null);
        return;
    L4:
        if (r1.results.isEmpty() == true) goto L11;
        TLRPC.BotInlineMessage r0 = r1.results.get(0).send_message;
        if (r0 == null) goto L11;
        String r02 = r0.message;
        if (r02 == null) goto L11;
        r2.run(r02);
    }

    private static boolean reactionsChanged(TLRPC.MessageReactions r8, TLRPC.MessageReactions r9) {
        if (r8 != r9) goto L6;
        return false;
    L6:
        if (r8 == null) goto L36;
        if (r9 == null) goto L36;
        ArrayList<TLRPC.ReactionCount> r2 = r8.results;
        if (r2 != null) goto L12;
        int r22 = 0;
    L13:
        ArrayList<TLRPC.ReactionCount> r3 = r9.results;
        if (r3 != null) goto L16;
        int r32 = 0;
    L17:
        if (r22 == r32) goto L19;
        return true;
    L19:
        int r33 = 0;
    L20:
        if (r33 >= r22) goto L35;
        TLRPC.ReactionCount r4 = r8.results.get(r33);
        TLRPC.ReactionCount r5 = r9.results.get(r33);
        if (r4 == null) goto L32;
        if (r5 == null) goto L32;
        if (r4.count != r5.count) goto L31;
        if (r4.chosen_order != r5.chosen_order) goto L31;
        if (ReactionsLayoutInBubble.equalsTLReaction(r4.reaction, r5.reaction) == false) goto L31;
    L34:
        r33 = r33 + 1;
    L31:
        return true;
    L32:
        if (r4 == r5) goto L34;
        return true;
    L35:
        return false;
    L16:
        r32 = r3.size();
        goto L17
    L12:
        r22 = r2.size();
    L36:
        return true;
    }

    private void searchChat(long r6, boolean r8, boolean r9, Utilities.Callback<TLRPC.Chat> r10) {
        Pair<Long, String> r0 = getBotInfo();
        Long r1 = (Long) r0.first;
        long r2 = r1.longValue();
        TLRPC.User r12 = getMessagesController().getUser(r1);
        if (r12 != null) goto L9;
        if (r8 == false) goto L7;
        resolveUser((String) r0.second, r2, new ChatUtils$$ExternalSyntheticLambda2(this, r6, r10));
        return;
    L7:
        r10.run(null);
        return;
    L9:
        sendInlineBotRequest(r12, String.valueOf(r6), r9, new ChatUtils$$ExternalSyntheticLambda3(this, r10));
    }

    private Runnable searchUser(long r8, boolean r10, boolean r11, Utilities.Callback<TLRPC.User> r12) {
        return searchUser(r8, r10, r11, r12, null);
    }

    private Runnable searchUser(long r6, boolean r8, boolean r9, Utilities.Callback<TLRPC.User> r10, Utilities.Callback<Runnable> r11) {
        Pair<Long, String> r0 = getBotInfo();
        Long r1 = (Long) r0.first;
        long r2 = r1.longValue();
        TLRPC.User r12 = getMessagesController().getUser(r1);
        if (r12 != null) goto L10;
        if (r8 == true) goto L6;
        r10.run(null);
        return null;
    L6:
        return resolveUser((String) r0.second, r2, new ChatUtils$$ExternalSyntheticLambda10(this, r6, r10));
    L10:
        return sendInlineBotRequest(r12, String.valueOf(r6), r9, new ChatUtils$$ExternalSyntheticLambda11(this, r10), r11);
    }

    public void addMessageToClipboard(MessageObject r1, Runnable r2) {
        String r0 = getPathToMessage(r1);
        if (TextUtils.isEmpty(r0) == true) goto L6;
        SystemUtils.addFileToClipboard(new File(r0), r2);
        return;
    }

    public CharSequence addTimestamp(CharSequence r3, long r4, Theme.ResourcesProvider r6) {
        String r2 = LocaleController.getInstance().getFormatterDay().format(r4 * 1000);
        SpannableStringBuilder r42 = new SpannableStringBuilder(r3);
        ProfileActivity.ShowDrawable r32 = ChannelAdminLogActivity.findDrawable(r3);
        if (r32 != null) goto L5;
        r32 = new ProfileActivity.ShowDrawable(r2);
        r32.textDrawable.setTypeface(AndroidUtilities.bold());
        r32.textDrawable.setTextSize(AndroidUtilities.dp(10.0f));
        r32.setTextColor(Theme.getThemePaint("paintChatActionText", r6).getColor());
        r32.setBackgroundColor(503316480);
    L6:
        r32.setBounds(0, 0, r32.getIntrinsicWidth(), r32.getIntrinsicHeight());
        r42.append(" S");
        r42.setSpan(new ColoredImageSpan(r32), r42.length() - 1, r42.length(), 33);
        return r42;
    L5:
        r32.textDrawable.setText(r2, false);
        goto L6
    }

    public boolean canSaveSticker(MessageObject r2) {
        if (MessageObject.isStickerDocument(r2.getDocument()) == false) goto L5;
        return true;
    L5:
        if (isEmoji(r2.getDocument()) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public ArrayList<TLRPC.MessageEntity> copyMessageEntities(ArrayList<TLRPC.MessageEntity> r9) {
        if (r9 != null) goto L4;
        return null;
    L4:
        if (r9.isEmpty() == true) goto L21;
        ArrayList<TLRPC.MessageEntity> r0 = new ArrayList();
        int r1 = r9.size();
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L12;
        TLRPC.MessageEntity r3 = r9.get(r2);
        r2 = r2 + 1;
        TLRPC.MessageEntity r32 = r3;
        if ((r32 instanceof TLRPC.TL_messageEntityMentionName) == true) goto L10;
        r0.add(r32);
        goto L7
    L10:
        TLRPC.TL_inputMessageEntityMentionName r4 = new TLRPC.TL_inputMessageEntityMentionName();
        r4.length = r32.length;
        r4.offset = r32.offset;
        r4.user_id = getMessagesController().getInputUser(((TLRPC.TL_messageEntityMentionName) r32).user_id);
        r0.add(r4);
        goto L7
    L12:
        return r0;
    L21:
        return null;
    }

    public ConnectionsManager getConnectionsManager() {
        return ConnectionsManager.getInstance(this.selectedAccount);
    }

    public String getDC(TLRPC.Chat r2) {
        return getDC(null, r2);
    }

    public String getDC(TLRPC.User r2) {
        return getDC(r2, null);
    }

    public String getDC(TLRPC.User r4, TLRPC.Chat r5) {
        int r0 = getConnectionsManager().getCurrentDatacenterId();
        if (r4 != null) goto L5;
        if (r5 == null) goto L14;
        TLRPC.ChatPhoto r3 = r5.photo;
        if (r3 == null) goto L13;
        r0 = r3.dc_id;
    L15:
        if (r0 == (-1)) goto L21;
        if (r0 == 0) goto L21;
        return String.format(Locale.ROOT, "DC%d, %s", new Object[]{Integer.valueOf(r0), getDCName(r0)});
    L21:
        return getDCName(0);
    L13:
        r0 = -1;
        goto L15
    L14:
        r0 = 0;
        goto L15
    L5:
        if (UserObject.isUserSelf(r4) == false) goto L8;
        if (r0 != (-1)) goto L15;
    L8:
        r0 = getUserProfileDC(r4);
        goto L15
    }

    public long getEmojiIdFrom(MessageObject r3, TLRPC.User r4) {
        if (r3 != null) goto L4;
        return 0;
    L4:
        if (r3.messageOwner == null) goto L33;
        MessageObject r0 = r3.replyMessageObject;
        if (r0 == null) goto L34;
        TLRPC.Message r1 = r0.messageOwner;
        if (r1 != null) goto L10;
        return 0;
    L10:
        if (r1.from_id == null) goto L36;
        boolean r02 = DialogObject.isEncryptedDialog(r0.getDialogId());
        MessageObject r12 = r3.replyMessageObject;
        if (r02 == false) goto L20;
        if (r12.isOutOwner() == false) goto L16;
        r4 = getUserConfig().getCurrentUser();
    L16:
        if (r4 != null) goto L18;
        return 0;
    L18:
        return UserObject.getEmojiId(r4);
    L20:
        if (r12.isFromUser() == false) goto L26;
        TLRPC.User r2 = getMessagesController().getUser(Long.valueOf(r3.replyMessageObject.messageOwner.from_id.user_id));
        if (r2 != null) goto L24;
        return 0;
    L24:
        return UserObject.getEmojiId(r2);
    L26:
        if (r3.replyMessageObject.isFromChannel() == false) goto L39;
        TLRPC.Chat r22 = getMessagesController().getChat(Long.valueOf(r3.replyMessageObject.messageOwner.from_id.channel_id));
        if (r22 != null) goto L30;
        return 0;
    L30:
        return ChatObject.getEmojiId(r22);
    L39:
        return 0;
    L36:
        return 0;
    L34:
        return 0;
    L33:
        return 0;
    }

    public FileLoader getFileLoader() {
        return FileLoader.getInstance(this.selectedAccount);
    }

    public long getLikeDialog() {
        return ExteraConfig.getPreferences().getLong("channelToSave" + this.selectedAccount, getUserConfig().getClientUserId());
    }

    public MessageObject getMessageForRepeat(MessageObject r2, MessageObject.GroupedMessages r3) {
        if (r3 == null) goto L8;
        if (r3.isDocuments == true) goto L8;
        return getTargetMessageObjectFromGroup(r3);
    L8:
        if (TextUtils.isEmpty(r2.messageOwner.message) == true) goto L10;
    L20:
        return r2;
    L10:
        if (r2.isAnyKindOfSticker() == true) goto L20;
        TLRPC.MessageMedia r1 = r2.messageOwner.media;
        if (r1 != null) goto L14;
        return null;
    L14:
        if ((r1 instanceof TLRPC.TL_messageMediaEmpty) == false) goto L16;
        return null;
    L16:
        if ((r1 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L20;
        return null;
    }

    public MessagesStorage getMessageStorage() {
        return MessagesStorage.getInstance(this.selectedAccount);
    }

    public CharSequence getMessageText(MessageObject r8, MessageObject.GroupedMessages r9) {
        int r0 = r8.type;
        if (r0 != 19) goto L5;
    L27:
        CharSequence r92 = null;
    L29:
        if (r8.translated == false) goto L31;
        return null;
    L31:
        if (r8.isRestrictedMessage == false) goto L34;
        return null;
    L34:
        return r92;
    L5:
        if (r0 == 15) goto L27;
        if (r0 == 13) goto L27;
        r92 = getMessageCaption(r8, r9);
        if (r92 == null) goto L11;
    L20:
        if (r92 == null) goto L22;
    L24:
        if (r92 == null) goto L29;
        if (Emoji.fullyConsistsOfEmojis(r92) == false) goto L29;
    L22:
        if (MessageObject.isMediaEmpty(r8.messageOwner) == false) goto L24;
        r92 = getMessageContent(r8);
        goto L24
    L11:
        if (r8.isPoll() == false) goto L20;
        TLRPC.Poll r02 = ((TLRPC.TL_messageMediaPoll) r8.messageOwner.media).poll;     // Catch: Exception -> L35
        StringBuilder r1 = new StringBuilder(r02.question.text);     // Catch: Exception -> L35
        r1.append("\n");     // Catch: Exception -> L35
        ArrayList<TLRPC.PollAnswer> r03 = r02.answers;     // Catch: Exception -> L35
        int r3 = r03.size();     // Catch: Exception -> L35
        int r4 = 0;
    L13:
        if (r4 >= r3) goto L19;
        TLRPC.PollAnswer r5 = r03.get(r4);     // Catch: Exception -> L35
        r4 = r4 + 1;     // Catch: Exception -> L35
        r1.append("\n🔘 ");     // Catch: Exception -> L35
        TLRPC.TL_textWithEntities r52 = r5.text;     // Catch: Exception -> L35
        if (r52 != null) goto L17;
        String r53 = _UrlKt.FRAGMENT_ENCODE_SET;
    L18:
        r1.append(r53);     // Catch: Exception -> L35
        goto L13
    L17:
        r53 = r52.text;     // Catch: Exception -> L35
        goto L18
    L19:
        r92 = r1.toString();     // Catch: Exception -> L35
        goto L20
    }

    public MessagesController getMessagesController() {
        return MessagesController.getInstance(this.selectedAccount);
    }

    public String getName(long r6) {
        String r1 = null;
        if (DialogObject.isEncryptedDialog(r6) == false) goto L10;
        TLRPC.EncryptedChat r0 = getMessagesController().getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r6)));
        if (r0 == null) goto L18;
        TLRPC.User r02 = getMessagesController().getUser(Long.valueOf(r0.user_id));
        if (r02 == null) goto L18;
        r1 = ContactsController.formatName(r02.first_name, r02.last_name);
    L18:
        if (r6 == getUserConfig().getClientUserId()) goto L20;
        return r1;
    L20:
        return LocaleController.getString(R.string.SavedMessages);
    L10:
        if (DialogObject.isUserDialog(r6) == false) goto L14;
        TLRPC.User r03 = getMessagesController().getUser(Long.valueOf(r6));
        if (r03 == null) goto L18;
        r1 = ContactsController.formatName(r03);
        goto L18
    L14:
        TLRPC.Chat r04 = getMessagesController().getChat(Long.valueOf(-r6));
        if (r04 == null) goto L18;
        r1 = r04.title;
        goto L18
    }

    public String getPathToMessage(MessageObject r6) {
        if (r6 != null) goto L5;
        return null;
    L5:
        FileLoader r0 = FileLoader.getInstance(r6.currentAccount);
        TLRPC.Message r1 = r6.messageOwner;
        if (r1 != null) goto L8;
    L12:
        TLRPC.Message r12 = r6.messageOwner;
        if (r12 == null) goto L18;
        String r13 = r0.getPathToMessage(r12).toString();
        if (fileExists(r13) == false) goto L18;
        return r13;
    L18:
        if (r6.getDocument() == null) goto L25;
        String r14 = r0.getPathToAttach(r6.getDocument(), false).toString();
        if (fileExists(r14) == false) goto L22;
        return r14;
    L22:
        String r15 = r0.getPathToAttach(r6.getDocument(), true).toString();
        if (fileExists(r15) == false) goto L25;
        return r15;
    L25:
        VideoPlayer.VideoUri r16 = r6.cachedQuality;
        if (r16 != null) goto L28;
    L34:
        TLRPC.Document r62 = r6.qualityToSave;
        if (r62 == null) goto L39;
        String r63 = r0.getPathToAttach(r62, null, false, true).toString();
        if (fileExists(r63) == false) goto L39;
        return r63;
    L39:
        return null;
    L28:
        if (r16.isCached() == false) goto L34;
        Uri r17 = r6.cachedQuality.uri;
        if (r17 == null) goto L34;
        String r18 = r17.getPath();
        if (fileExists(r18) == false) goto L34;
        return r18;
    L8:
        if (TextUtils.isEmpty(r1.attachPath) == true) goto L12;
        String r19 = r6.messageOwner.attachPath;
        if (fileExists(r19) == false) goto L12;
        return r19;
    }

    public TLRPC.InputStickerSet getSetFrom(MessageObject r2, TLRPC.User r3) {
        return AnimatedEmojiDrawable.findStickerSet(this.selectedAccount, getEmojiIdFrom(r2, r3));
    }

    public TLRPC.InputStickerSet getSetFrom(TLRPC.Chat r3) {
        return AnimatedEmojiDrawable.findStickerSet(this.selectedAccount, ChatObject.getProfileEmojiId(r3));
    }

    public TLRPC.InputStickerSet getSetFrom(TLRPC.User r3) {
        return AnimatedEmojiDrawable.findStickerSet(this.selectedAccount, UserObject.getProfileEmojiId(r3));
    }

    public String getTextFromCallback(byte[] r2) {
        return textDecoder.decode(ByteBuffer.wrap(r2)).toString();
    L5:
        return Base64.encodeToString(r2, 3);
    }

    public UserConfig getUserConfig() {
        return UserConfig.getInstance(this.selectedAccount);
    }

    public boolean hasArchivedChats() {
        return getMessagesController().hasArchivedChatsActual();
    }

    public boolean hasMediaForRepeat(MessageObject r1) {
        if (r1.isAnyKindOfSticker() == true) goto L12;
        TLRPC.MessageMedia r0 = r1.messageOwner.media;
        if (r0 != null) goto L7;
        return false;
    L7:
        if ((r0 instanceof TLRPC.TL_messageMediaEmpty) == false) goto L9;
        return false;
    L9:
        if ((r0 instanceof TLRPC.TL_messageMediaWebPage) == true) goto L16;
        return true;
    L16:
        return false;
    L12:
        return false;
    }

    public boolean isEmoji(TLRPC.Document r2) {
        if (MessageObject.isAnimatedEmoji(r2) == true) goto L5;
    L8:
        return false;
    L5:
        if (MessageObject.isAnimatedStickerDocument(r2, false) == true) goto L8;
        return true;
    }

    public void resolveChannel(String r3, Utilities.Callback<TLRPC.Chat> r4) {
        getMessagesController().getUserNameResolver().resolve(r3, new ChatUtils$$ExternalSyntheticLambda8(this, r4));
    }

    public Runnable resolveUser(String r3, long r4, Utilities.Callback<TLRPC.User> r6) {
        return getMessagesController().getUserNameResolver().resolve(r3, new ChatUtils$$ExternalSyntheticLambda12(this, r4, r6));
    }

    public void saveGifToGallery(Activity r1, TLRPC.Document r2, TLRPC.BotInlineResult r3, Utilities.Callback<Uri> r4) {
        String r0 = getGifPath(r2, r3);
        if (TextUtils.isEmpty(r0) == false) goto L5;
        return;
    L5:
        utilsQueue.postRunnable(new ChatUtils$$ExternalSyntheticLambda0(r0, r1, r4));
    }

    public void saveStickerToGallery(Activity r2, String r3, boolean r4, Utilities.Callback<Uri> r5) {
        utilsQueue.postRunnable(new ChatUtils$$ExternalSyntheticLambda1(r3, r4, r2, r5));
    }

    public void saveStickerToGallery(Activity r2, MessageObject r3, Utilities.Callback<Uri> r4) {
        saveStickerToGallery(r2, getPathToMessage(r3), r3.isVideoSticker(), r4);
    }

    public void saveStickerToGallery(Activity r3, TLRPC.Document r4, Utilities.Callback<Uri> r5) {
        String r0 = getFileLoader().getPathToAttach(r4, true).toString();
        if (new File(r0).exists() == true) goto L5;
        return;
    L5:
        saveStickerToGallery(r3, r0, MessageObject.isVideoSticker(r4), r5);
    }

    public void searchChatById(long r10, Utilities.Callback<TLRPC.Chat> r12) {
        if (r10 != 0) goto L6;
        r12.run(null);
        return;
    L6:
        TLRPC.Chat r1 = getMessagesController().getChat(Long.valueOf(r10));
        if (r1 == null) goto L15;
        r12.run(r1);
        return;
    L15:
        searchChat(Long.parseLong("-100" + r10), true, true, r12);
        return;
    L13:
        r12.run(null);
    }

    public Runnable searchUserById(Long r2, Utilities.Callback<TLRPC.User> r3) {
        return searchUserById(r2, r3, null);
    }

    public Runnable searchUserById(Long r10, Utilities.Callback<TLRPC.User> r11, Utilities.Callback<Runnable> r12) {
        if (r10.longValue() != 0) goto L5;
        return null;
    L5:
        TLRPC.User r0 = getMessagesController().getUser(r10);
        if (r0 == null) goto L10;
        r11.run(r0);
        return null;
    L10:
        return searchUser(r10.longValue(), true, true, new ChatUtils$$ExternalSyntheticLambda5(r11), r12);
    }

    public void sendBotRequest(String r6, boolean r7, Utilities.Callback<String> r8) {
        Pair<Long, String> r0 = ExteraConfig.getApiBotInfo();
        Long r1 = (Long) r0.first;
        long r2 = r1.longValue();
        String r02 = (String) r0.second;
        TLRPC.User r12 = getMessagesController().getUser(r1);
        if (r12 == null) goto L6;
        sendInlineBotRequest(r12, r6, r7, new ChatUtils$$ExternalSyntheticLambda13(this, r8));
        return;
    L6:
        resolveUser(r02, r2, new ChatUtils$$ExternalSyntheticLambda14(this, r6, r7, r8));
    }

    public Runnable sendInlineBotRequest(TLRPC.User r7, String r8, boolean r9, Utilities.Callback<TLRPC.messages_BotResults> r10) {
        return sendInlineBotRequest(r7, r8, r9, r10, null);
    }

    public Runnable sendInlineBotRequest(TLRPC.User r10, String r11, boolean r12, Utilities.Callback<TLRPC.messages_BotResults> r13, Utilities.Callback<Runnable> r14) {
        if (r10 != null) goto L6;
        r13.run(null);
        return null;
    L6:
        String r7 = "bot_inline_query_" + r10.id + "_" + r11;
        ChatUtils$$ExternalSyntheticLambda15 r0 = new ChatUtils$$ExternalSyntheticLambda15(this, r12, r10, r11, r13, r14, r7);
        if (r12 == false) goto L10;
        getMessageStorage().getBotCache(r7, r0);
        return null;
    L10:
        TLRPC.TL_messages_getInlineBotResults r1 = new TLRPC.TL_messages_getInlineBotResults();
        r1.query = r11;
        r1.bot = getMessagesController().getInputUser(r10);
        r1.offset = _UrlKt.FRAGMENT_ENCODE_SET;
        r1.peer = new TLRPC.TL_inputPeerEmpty();
        return new ChatUtils$$ExternalSyntheticLambda16(this, getConnectionsManager().sendRequest(r1, r0, 2));
    }

    public void setLikeDialog(long r4) {
        ExteraConfig.getEditor().putLong("channelToSave" + this.selectedAccount, r4).apply();
    }

    public boolean shouldAddTimestamp(MessageObject r2, CharSequence r3) {
        TLRPC.Message r1 = r2.messageOwner;
        if (r1 != null) goto L6;
        return false;
    L6:
        if (r2.currentEvent != null) goto L10;
        if (r1.action != null) goto L10;
    L13:
        return false;
    L10:
        if (TextUtils.isEmpty(r3) == true) goto L13;
        return true;
    }
}
