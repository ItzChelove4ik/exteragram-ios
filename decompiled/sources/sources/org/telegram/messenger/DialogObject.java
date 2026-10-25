package org.telegram.messenger;

import android.text.TextUtils;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;

/* loaded from: classes.dex */
public class DialogObject {
    public DialogObject() {
    }

    public static int editDistance(String r9, String r10) {
        String r92 = r9.toLowerCase();
        String r102 = r10.toLowerCase();
        int[] r0 = new int[r102.length() + 1];
        int r2 = 0;
    L4:
        if (r2 > r92.length()) goto L20;
        int r3 = 0;
        int r4 = r2;
    L7:
        if (r3 > r102.length()) goto L16;
        if (r2 != 0) goto L10;
        r0[r3] = r3;
    L15:
        r3 = r3 + 1;
        goto L7
    L10:
        if (r3 <= 0) goto L15;
        int r5 = r3 - 1;
        int r6 = r0[r5];
        if (r92.charAt(r2 - 1) == r102.charAt(r5)) goto L14;
        r6 = Math.min(Math.min(r6, r4), r0[r3]) + 1;
    L14:
        r0[r5] = r4;
        r4 = r6;
        goto L15
    L16:
        if (r2 <= 0) goto L18;
        r0[r102.length()] = r4;
    L18:
        r2 = r2 + 1;
        goto L4
    L20:
        return r0[r102.length()];
    }

    public static boolean emojiStatusesEqual(TLRPC.EmojiStatus r4, TLRPC.EmojiStatus r5) {
        if (getEmojiStatusDocumentId(r4) == getEmojiStatusDocumentId(r5)) goto L5;
        return false;
    L5:
        if (getEmojiStatusCollectibleId(r4) == getEmojiStatusCollectibleId(r5)) goto L7;
        return false;
    L7:
        if (getEmojiStatusUntil(r4) != getEmojiStatusUntil(r5)) goto L13;
        return true;
    L13:
        return false;
    }

    public static TLRPC.EmojiStatus filterEmojiStatus(TLRPC.EmojiStatus r5) {
        int r0 = getEmojiStatusUntil(r5);
        if (r0 != 0) goto L5;
        return r5;
    L5:
        if (r0 > ((int) (System.currentTimeMillis() / 1000))) goto L9;
        return null;
    L9:
        return r5;
    }

    public static TLRPC.TL_username findUsername(String r5, ArrayList<TLRPC.TL_username> r6) {
        if (r6 != null) goto L5;
        return null;
    L5:
        int r1 = r6.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L12;
        TLRPC.TL_username r3 = r6.get(r2);
        r2 = r2 + 1;
        TLRPC.TL_username r32 = r3;
        if (r32 == null) goto L6;
        if (TextUtils.equals(r32.username, r5) == false) goto L6;
        return r32;
    L12:
        return null;
    }

    public static TLRPC.TL_username findUsername(String r0, TLRPC.Chat r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return findUsername(r0, r1.usernames);
    }

    public static TLRPC.TL_username findUsername(String r0, TLRPC.User r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return findUsername(r0, r1.usernames);
    }

    public static TL_bots.botVerification getBotVerification(TLObject r1) {
        if ((r1 instanceof TLRPC.UserFull) == false) goto L7;
        return ((TLRPC.UserFull) r1).bot_verification;
    L7:
        if ((r1 instanceof TLRPC.ChatFull) == true) goto L9;
        return null;
    L9:
        return ((TLRPC.ChatFull) r1).bot_verification;
    }

    public static long getBotVerificationIcon(TLObject r2) {
        if ((r2 instanceof TLRPC.User) == false) goto L7;
        return ((TLRPC.User) r2).bot_verification_icon;
    L7:
        if ((r2 instanceof TLRPC.Chat) == true) goto L9;
        return 0;
    L9:
        return ((TLRPC.Chat) r2).bot_verification_icon;
    }

    public static long getDialogId(TLObject r2) {
        if ((r2 instanceof TLRPC.User) == false) goto L7;
        return ((TLRPC.User) r2).id;
    L7:
        if ((r2 instanceof TLRPC.Chat) == true) goto L9;
        return 0;
    L9:
        return -((TLRPC.Chat) r2).id;
    }

    public static String getDialogTitle(TLObject r1) {
        return setDialogPhotoTitle(null, null, r1);
    }

    public static long getEmojiStatusCollectibleId(TLRPC.EmojiStatus r7) {
        if ((r7 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L11;
        TLRPC.TL_emojiStatusCollectible r72 = (TLRPC.TL_emojiStatusCollectible) r7;
        if ((r72.flags & 1) == 0) goto L10;
        if (r72.until > ((int) (System.currentTimeMillis() / 1000))) goto L10;
        return 0;
    L10:
        return r72.collectible_id;
    L11:
        return 0;
    }

    public static long getEmojiStatusDocumentId(long r3) {
        if (r3 < 0) goto L9;
        TLRPC.User r32 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r3));
        if (r32 != null) goto L8;
        return 0;
    L8:
        return getEmojiStatusDocumentId(r32.emoji_status);
    L9:
        TLRPC.Chat r33 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-r3));
        if (r33 != null) goto L13;
        return 0;
    L13:
        return getEmojiStatusDocumentId(r33.emoji_status);
    }

    public static long getEmojiStatusDocumentId(TLRPC.EmojiStatus r7) {
        if ((r7 instanceof TLRPC.TL_emojiStatus) == false) goto L12;
        TLRPC.TL_emojiStatus r72 = (TLRPC.TL_emojiStatus) r7;
        if ((r72.flags & 1) == 0) goto L10;
        if (r72.until > ((int) (System.currentTimeMillis() / 1000))) goto L10;
        return 0;
    L10:
        return r72.document_id;
    L12:
        if ((r7 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L20;
        TLRPC.TL_emojiStatusCollectible r73 = (TLRPC.TL_emojiStatusCollectible) r7;
        if ((r73.flags & 1) == 0) goto L19;
        if (r73.until > ((int) (System.currentTimeMillis() / 1000))) goto L19;
        return 0;
    L19:
        return r73.document_id;
    L20:
        return 0;
    }

    public static int getEmojiStatusUntil(TLRPC.EmojiStatus r1) {
        if ((r1 instanceof TLRPC.TL_emojiStatus) == false) goto L9;
        TLRPC.TL_emojiStatus r12 = (TLRPC.TL_emojiStatus) r1;
        if ((r12.flags & 1) != 0) goto L7;
        return 0;
    L7:
        return r12.until;
    L9:
        if ((r1 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L16;
        TLRPC.TL_emojiStatusCollectible r13 = (TLRPC.TL_emojiStatusCollectible) r1;
        if ((r13.flags & 1) != 0) goto L13;
        return 0;
    L13:
        return r13.until;
    L16:
        return 0;
    }

    public static int getEncryptedChatId(long r2) {
        return (int) (r2 & 4294967295L);
    }

    public static int getFolderId(long r0) {
        return (int) r0;
    }

    public static long getLastMessageOrDraftDate(TLRPC.Dialog r1, TLRPC.DraftMessage r2) {
        if (r2 == null) goto L8;
        int r22 = r2.date;
        if (r22 < r1.last_message_date) goto L8;
        return r22;
    L8:
        return r1.last_message_date;
    }

    public static long getMessagesStarsPrice(TL_account.RequirementToContact r2) {
        if ((r2 instanceof TL_account.requirementToContactPaidMessages) == true) goto L5;
        return 0;
    L5:
        return ((TL_account.requirementToContactPaidMessages) r2).stars_amount;
    }

    public static String getName(int r0, long r1) {
        return getName(MessagesController.getInstance(r0).getUserOrChat(r1));
    }

    public static String getName(long r1) {
        return getName(UserConfig.selectedAccount, r1);
    }

    public static String getName(TLObject r1) {
        if ((r1 instanceof TLRPC.User) == false) goto L7;
        return AndroidUtilities.removeRTL(AndroidUtilities.removeDiacritics(UserObject.getUserName((TLRPC.User) r1)));
    L7:
        if ((r1 instanceof TLRPC.Chat) == true) goto L9;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        return ((TLRPC.Chat) r1).title;
    }

    public static long getPeerDialogId(TLRPC.InputPeer r5) {
        if (r5 != null) goto L5;
        return 0;
    L5:
        long r2 = r5.user_id;
        if (r2 == 0) goto L8;
        return r2;
    L8:
        long r22 = r5.chat_id;
        if (r22 == 0) goto L13;
        return -r22;
    L13:
        return -r5.channel_id;
    }

    public static long getPeerDialogId(TLRPC.Peer r5) {
        if (r5 != null) goto L5;
        return 0;
    L5:
        long r2 = r5.user_id;
        if (r2 == 0) goto L8;
        return r2;
    L8:
        long r22 = r5.chat_id;
        if (r22 == 0) goto L13;
        return -r22;
    L13:
        return -r5.channel_id;
    }

    public static String getPublicUsername(String r3, ArrayList<TLRPC.TL_username> r4, boolean r5) {
        if (TextUtils.isEmpty(r3) == true) goto L6;
        if (r5 == true) goto L6;
    L28:
        return r3;
    L6:
        if (r4 == null) goto L23;
        int r0 = 0;
    L9:
        if (r0 >= r4.size()) goto L23;
        TLRPC.TL_username r1 = r4.get(r0);
        if (r1 == null) goto L21;
        if (r1.active == false) goto L16;
        if (r5 == true) goto L16;
    L18:
        if (TextUtils.isEmpty(r1.username) == true) goto L21;
        return r1.username;
    L16:
        if (r1.editable == true) goto L18;
    L21:
        r0 = r0 + 1;
    L23:
        if (TextUtils.isEmpty(r3) == true) goto L29;
        if (r5 == false) goto L36;
        if (r4 == null) goto L28;
        if (r4.size() <= 0) goto L28;
        return null;
    L36:
        return null;
    L29:
        return null;
    }

    public static String getPublicUsername(TLObject r2) {
        if ((r2 instanceof TLRPC.Chat) == false) goto L7;
        TLRPC.Chat r22 = (TLRPC.Chat) r2;
        return getPublicUsername(r22.username, r22.usernames, false);
    L7:
        if ((r2 instanceof TLRPC.User) == false) goto L10;
        TLRPC.User r23 = (TLRPC.User) r2;
        return getPublicUsername(r23.username, r23.usernames, false);
    L10:
        return null;
    }

    public static String getPublicUsername(TLObject r2, String r3) {
        if ((r2 instanceof TLRPC.Chat) == false) goto L11;
        TLRPC.Chat r22 = (TLRPC.Chat) r2;
        String r0 = r22.username;
        ArrayList<TLRPC.TL_username> r23 = r22.usernames;
        if (r3 != null) goto L9;
        return getPublicUsername(r0, r23, false);
    L9:
        return getSimilarPublicUsername(r0, r23, r3);
    L11:
        if ((r2 instanceof TLRPC.User) == false) goto L18;
        TLRPC.User r24 = (TLRPC.User) r2;
        String r02 = r24.username;
        ArrayList<TLRPC.TL_username> r25 = r24.usernames;
        if (r3 != null) goto L17;
        return getPublicUsername(r02, r25, false);
    L17:
        return getSimilarPublicUsername(r02, r25, r3);
    L18:
        return null;
    }

    public static String getShortName(int r0, long r1) {
        return getShortName(MessagesController.getInstance(r0).getUserOrChat(r1));
    }

    public static String getShortName(long r1) {
        return getShortName(MessagesController.getInstance(UserConfig.selectedAccount).getUserOrChat(r1));
    }

    public static String getShortName(TLObject r1) {
        if ((r1 instanceof TLRPC.User) == false) goto L7;
        return AndroidUtilities.removeRTL(AndroidUtilities.removeDiacritics(UserObject.getForcedFirstName((TLRPC.User) r1)));
    L7:
        if ((r1 instanceof TLRPC.Chat) == true) goto L9;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        return AndroidUtilities.removeRTL(AndroidUtilities.removeDiacritics(((TLRPC.Chat) r1).title));
    }

    public static String getShortTitle(TLObject r1) {
        if ((r1 instanceof TLRPC.User) == false) goto L7;
        return UserObject.getForcedFirstName((TLRPC.User) r1);
    L7:
        if ((r1 instanceof TLRPC.Chat) == true) goto L9;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        return ((TLRPC.Chat) r1).title;
    }

    public static String getSimilarPublicUsername(String r10, ArrayList<TLRPC.TL_username> r11, String r12) {
        double r0 = 0.0d;
        double r2 = -1.0d;
        String r4 = null;
        if (r11 == null) goto L22;
        int r5 = 0;
    L6:
        if (r5 >= r11.size()) goto L22;
        TLRPC.TL_username r6 = r11.get(r5);
        if (r6 == null) goto L20;
        if (r6.active == false) goto L20;
        if (TextUtils.isEmpty(r6.username) == true) goto L20;
        if (r2 >= 0.0d) goto L16;
        double r7 = 0.0d;
    L18:
        if (r7 <= r2) goto L20;
        r4 = r6.username;
        r2 = r7;
        goto L20
    L16:
        r7 = similarity(r6.username, r12);
    L20:
        r5 = r5 + 1;
    L22:
        if (TextUtils.isEmpty(r10) == false) goto L24;
    L30:
        return r4;
    L24:
        if (r2 < 0.0d) goto L28;
        r0 = similarity(r10, r12);
    L28:
        if (r0 <= r2) goto L30;
        return r10;
    }

    public static String getStatus(int r1, long r2) {
        return getStatus(r1, MessagesController.getInstance(r1).getUserOrChat(r2));
    }

    public static String getStatus(int r1, TLObject r2) {
        if ((r2 instanceof TLRPC.User) == false) goto L7;
        return LocaleController.formatUserStatus(r1, (TLRPC.User) r2, null, null);
    L7:
        if ((r2 instanceof TLRPC.Chat) == false) goto L22;
        TLRPC.Chat r22 = (TLRPC.Chat) r2;
        if (r22.participants_count <= 1) goto L17;
        boolean r12 = ChatObject.isChannelAndNotMegaGroup(r22);
        int r23 = r22.participants_count;
        if (r12 == false) goto L15;
        return LocaleController.formatPluralStringComma("Subscribers", r23);
    L15:
        return LocaleController.formatPluralStringComma("Members", r23);
    L17:
        if (ChatObject.isChannelAndNotMegaGroup(r22) == false) goto L21;
        return LocaleController.getString(R.string.DiscussChannel);
    L21:
        return LocaleController.getString(R.string.AccDescrGroup);
    L22:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static String getStatus(long r1) {
        return getStatus(UserConfig.selectedAccount, r1);
    }

    public static boolean hasPhoto(TLObject r3) {
        if ((r3 instanceof TLRPC.User) == false) goto L9;
        if (((TLRPC.User) r3).photo == null) goto L7;
        return true;
    L7:
        return false;
    L9:
        if ((r3 instanceof TLRPC.Chat) == true) goto L11;
    L13:
        return false;
    L11:
        if (((TLRPC.Chat) r3).photo == null) goto L13;
        return true;
    }

    public static void initDialog(TLRPC.Dialog r6) {
        if (r6 != null) goto L4;
        return;
    L4:
        if (r6.id == 0) goto L7;
        return;
    L7:
        if ((r6 instanceof TLRPC.TL_dialog) == false) goto L22;
        TLRPC.Peer r0 = r6.peer;
        if (r0 == null) goto L32;
        long r4 = r0.user_id;
        if (r4 == 0) goto L15;
        r6.id = r4;
        return;
    L15:
        long r42 = r0.chat_id;
        if (r42 == 0) goto L19;
        r6.id = -r42;
        return;
    L19:
        r6.id = -r0.channel_id;
        return;
    L32:
        return;
    L22:
        if ((r6 instanceof TLRPC.TL_dialogFolder) == false) goto L26;
        r6.id = makeFolderDialogId(((TLRPC.TL_dialogFolder) r6).folder.id);
        return;
    L26:
        if ((r6 instanceof TLRPC.TL_dialogCommunity) == false) goto L30;
        r6.id = -r6.community_id;
        return;
    }

    public static boolean isChannel(TLRPC.Dialog r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if ((r1.flags & 1) == 0) goto L8;
        return true;
    L8:
        return false;
    }

    public static boolean isChatDialog(long r2) {
        if (isEncryptedDialog(r2) == false) goto L5;
        return false;
    L5:
        if (isFolderDialogId(r2) == false) goto L7;
        return false;
    L7:
        if (r2 >= 0) goto L13;
        return true;
    L13:
        return false;
    }

    public static boolean isEmojiStatusCollectible(long r2) {
        if (r2 < 0) goto L9;
        TLRPC.User r22 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r2));
        if (r22 != null) goto L8;
        return false;
    L8:
        return isEmojiStatusCollectible(r22.emoji_status);
    L9:
        TLRPC.Chat r23 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-r2));
        if (r23 != null) goto L13;
        return false;
    L13:
        return isEmojiStatusCollectible(r23.emoji_status);
    }

    public static boolean isEmojiStatusCollectible(TLRPC.EmojiStatus r7) {
        if ((r7 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L10;
        TLRPC.TL_emojiStatusCollectible r72 = (TLRPC.TL_emojiStatusCollectible) r7;
        if ((r72.flags & 1) != 0) goto L7;
    L9:
        return true;
    L7:
        if (r72.until > ((int) (System.currentTimeMillis() / 1000))) goto L9;
        return false;
    L10:
        return false;
    }

    public static boolean isEmpty(TL_account.RequirementToContact r0) {
        if (r0 != null) goto L4;
        return true;
    L4:
        if ((r0 instanceof TL_account.requirementToContactEmpty) == true) goto L10;
        return false;
    L10:
        return true;
    }

    public static boolean isEncryptedDialog(long r4) {
        if ((4611686018427387904L & r4) != 0) goto L5;
        return false;
    L5:
        if ((r4 & Long.MIN_VALUE) != 0) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isFolderDialogId(long r4) {
        if ((2305843009213693952L & r4) != 0) goto L5;
        return false;
    L5:
        if ((r4 & Long.MIN_VALUE) != 0) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isPremiumBlocked(TL_account.RequirementToContact r0) {
        return r0 instanceof TL_account.requirementToContactPremium;
    }

    public static boolean isUserDialog(long r2) {
        if (isEncryptedDialog(r2) == false) goto L5;
        return false;
    L5:
        if (isFolderDialogId(r2) == false) goto L7;
        return false;
    L7:
        if (r2 <= 0) goto L13;
        return true;
    L13:
        return false;
    }

    public static long makeEncryptedDialogId(long r2) {
        return (r2 & 4294967295L) | 4611686018427387904L;
    }

    public static long makeFolderDialogId(int r4) {
        return 2305843009213693952L | r4;
    }

    public static String setDialogPhotoTitle(ImageReceiver r3, AvatarDrawable r4, TLObject r5) {
        if ((r5 instanceof TLRPC.User) == false) goto L27;
        TLRPC.User r0 = (TLRPC.User) r5;
        if (UserObject.isReplyUser(r0) == false) goto L13;
        String r52 = LocaleController.getString(R.string.RepliesTitle);
        if (r4 == null) goto L9;
        r4.setAvatarType(12);
    L9:
        if (r3 == null) goto L11;
        r3.setForUserOrChat(null, r4);
    L11:
        return r52;
    L13:
        if (UserObject.isUserSelf(r0) == false) goto L20;
        String r53 = LocaleController.getString(R.string.SavedMessages);
        if (r4 == null) goto L17;
        r4.setAvatarType(1);
    L17:
        if (r3 == null) goto L19;
        r3.setForUserOrChat(null, r4);
    L19:
        return r53;
    L20:
        String r1 = UserObject.getUserName(r0);
        if (r4 == null) goto L23;
        r4.setInfo(r0);
    L23:
        if (r3 == null) goto L25;
        r3.setForUserOrChat(r5, r4);
    L25:
        return r1;
    L27:
        if ((r5 instanceof TLRPC.Chat) == false) goto L34;
        TLRPC.Chat r02 = (TLRPC.Chat) r5;
        String r12 = r02.title;
        if (r4 == null) goto L31;
        r4.setInfo(r02);
    L31:
        if (r3 == null) goto L33;
        r3.setForUserOrChat(r5, r4);
    L33:
        return r12;
    L34:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static String setDialogPhotoTitle(BackupImageView r1, TLObject r2) {
        if (r1 == null) goto L6;
        return setDialogPhotoTitle(r1.getImageReceiver(), r1.getAvatarDrawable(), r2);
    L6:
        return setDialogPhotoTitle(null, null, r2);
    }

    public static double similarity(String r3, String r4) {
        if (r3.length() >= r4.length()) goto L5;
        r4 = r3;
        r3 = r4;
    L5:
        int r0 = r3.length();
        if (r0 != 0) goto L10;
        return 1.0d;
    L10:
        return (r0 - editDistance(r3, r4)) / r0;
    }
}
