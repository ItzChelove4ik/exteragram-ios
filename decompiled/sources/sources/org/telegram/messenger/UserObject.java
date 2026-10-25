package org.telegram.messenger;

import android.text.TextUtils;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes.dex */
public class UserObject {
    public static final long ANONYMOUS = 2666000;
    public static final long OAUTH = 489001;
    public static final long REPLY_BOT = 1271266957;
    public static final long VERIFY = 489000;

    public UserObject() {
    }

    public static boolean applyRequirementToContact(TLRPC.User r7, TL_account.RequirementToContact r8) {
        if (r7 != null) goto L6;
        return false;
    L6:
        if ((r8 instanceof TL_account.requirementToContactEmpty) == false) goto L14;
        if (r7.contact_require_premium == false) goto L10;
    L12:
        r7.contact_require_premium = false;
        r7.flags2 &= -16385;
        r7.send_paid_messages_stars = 0;
    L29:
        return true;
    L10:
        if (r7.send_paid_messages_stars != 0) goto L12;
        return false;
    L14:
        if ((r8 instanceof TL_account.requirementToContactPremium) == false) goto L22;
        if (r7.contact_require_premium == true) goto L18;
    L20:
        r7.contact_require_premium = true;
        r7.flags2 &= -16385;
        r7.send_paid_messages_stars = 0;
        goto L29
    L18:
        if (r7.send_paid_messages_stars != 0) goto L20;
        return false;
    L22:
        if ((r8 instanceof TL_account.requirementToContactPaidMessages) == false) goto L30;
        long r3 = ((TL_account.requirementToContactPaidMessages) r8).stars_amount;
        if (r7.contact_require_premium == false) goto L26;
    L28:
        r7.contact_require_premium = false;
        r7.flags2 |= 16384;
        r7.send_paid_messages_stars = r3;
        goto L29
    L26:
        if (r7.send_paid_messages_stars != r3) goto L28;
        return false;
    L30:
        return false;
    }

    public static boolean applyRequirementToContact(TLRPC.UserFull r7, TL_account.RequirementToContact r8) {
        if (r7 != null) goto L6;
        return false;
    L6:
        if ((r8 instanceof TL_account.requirementToContactEmpty) == false) goto L14;
        if (r7.contact_require_premium == false) goto L10;
    L12:
        r7.contact_require_premium = false;
        r7.flags2 &= -16385;
        r7.send_paid_messages_stars = 0;
    L29:
        return true;
    L10:
        if (r7.send_paid_messages_stars != 0) goto L12;
        return false;
    L14:
        if ((r8 instanceof TL_account.requirementToContactPremium) == false) goto L22;
        if (r7.contact_require_premium == true) goto L18;
    L20:
        r7.contact_require_premium = true;
        r7.flags2 &= -16385;
        r7.send_paid_messages_stars = 0;
        goto L29
    L18:
        if (r7.send_paid_messages_stars != 0) goto L20;
        return false;
    L22:
        if ((r8 instanceof TL_account.requirementToContactPaidMessages) == false) goto L30;
        long r3 = ((TL_account.requirementToContactPaidMessages) r8).stars_amount;
        if (r7.contact_require_premium == false) goto L26;
    L28:
        r7.contact_require_premium = false;
        r7.flags2 |= 16384;
        r7.send_paid_messages_stars = r3;
        goto L29
    L26:
        if (r7.send_paid_messages_stars != r3) goto L28;
        return false;
    L30:
        return false;
    }

    public static boolean areGiftsDisabled(long r1) {
        return areGiftsDisabled(MessagesController.getInstance(UserConfig.selectedAccount).getUserFull(r1));
    }

    public static boolean areGiftsDisabled(TLRPC.UserFull r5) {
        if (r5 != null) goto L5;
    L7:
        if (r5 == null) goto L20;
        TLRPC.DisallowedGiftsSettings r52 = r5.disallowed_stargifts;
        if (r52 == null) goto L20;
        if (r52.disallow_limited_stargifts == false) goto L20;
        if (r52.disallow_unlimited_stargifts == false) goto L20;
        if (r52.disallow_unique_stargifts == false) goto L20;
        if (r52.disallow_premium_gifts == false) goto L20;
        return true;
    L20:
        return false;
    L5:
        if (r5.id != UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId()) goto L7;
        return false;
    }

    public static boolean eq(TL_account.RequirementToContact r4, TL_account.RequirementToContact r5) {
        if ((r4 instanceof TL_account.requirementToContactEmpty) == false) goto L6;
        r4 = null;
    L6:
        if ((r5 instanceof TL_account.requirementToContactEmpty) == false) goto L9;
        r5 = null;
    L9:
        if (r4 != null) goto L13;
        if (r5 != null) goto L13;
        return true;
    L13:
        if (r4 == null) goto L28;
        if (r5 == null) goto L28;
        if ((r4 instanceof TL_account.requirementToContactPremium) == false) goto L22;
        if ((r5 instanceof TL_account.requirementToContactPremium) == false) goto L22;
        return true;
    L22:
        if ((r4 instanceof TL_account.requirementToContactPaidMessages) == false) goto L28;
        if ((r5 instanceof TL_account.requirementToContactPaidMessages) == false) goto L28;
        if (((TL_account.requirementToContactPaidMessages) r4).stars_amount != ((TL_account.requirementToContactPaidMessages) r5).stars_amount) goto L28;
        return true;
    L28:
        return false;
    }

    public static int getColorId(TLRPC.User r4) {
        if (r4 != null) goto L5;
        return 0;
    L5:
        TLRPC.PeerColor r0 = r4.color;
        if ((r0 instanceof TLRPC.TL_peerColor) == false) goto L12;
        if ((r0.flags & 1) == 0) goto L12;
        return r0.color;
    L12:
        return (int) (r4.id % 7);
    }

    public static long getEmojiId(TLRPC.User r2) {
        if (r2 == null) goto L9;
        TLRPC.PeerColor r22 = r2.color;
        if ((r22 instanceof TLRPC.TL_peerColor) == true) goto L6;
        return 0;
    L6:
        if ((r22.flags & 2) != 0) goto L8;
        return 0;
    L8:
        return r22.background_emoji_id;
    L9:
        return 0;
    }

    public static Long getEmojiStatusDocumentId(TLRPC.EmojiStatus r6) {
        if (r6 != null) goto L6;
        return null;
    L6:
        if ((r6 instanceof TLRPC.TL_emojiStatus) == false) goto L15;
        TLRPC.TL_emojiStatus r62 = (TLRPC.TL_emojiStatus) r6;
        if ((r62.flags & 1) == 0) goto L13;
        if (r62.until > ((int) (System.currentTimeMillis() / 1000))) goto L13;
        return null;
    L13:
        return Long.valueOf(r62.document_id);
    L15:
        if ((r6 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L23;
        TLRPC.TL_emojiStatusCollectible r63 = (TLRPC.TL_emojiStatusCollectible) r6;
        if ((r63.flags & 1) == 0) goto L22;
        if (r63.until > ((int) (System.currentTimeMillis() / 1000))) goto L22;
        return null;
    L22:
        return Long.valueOf(r63.document_id);
    L23:
        return null;
    }

    public static Long getEmojiStatusDocumentId(TLRPC.User r0) {
        if (r0 != null) goto L6;
        return null;
    L6:
        return getEmojiStatusDocumentId(r0.emoji_status);
    }

    public static String getFirstName(TLRPC.User r1) {
        return getFirstName(r1, true);
    }

    public static String getFirstName(TLRPC.User r2, boolean r3) {
        if (r2 != null) goto L4;
        return "DELETED";
    L4:
        if (isDeleted(r2) == true) goto L21;
        String r0 = r2.first_name;
        if (TextUtils.isEmpty(r0) == false) goto L9;
        r0 = r2.last_name;
    L15:
        if (TextUtils.isEmpty(r0) == true) goto L18;
        return r0;
    L18:
        return LocaleController.getString(R.string.HiddenName);
    L9:
        if (r3 == true) goto L15;
        if (r0.length() > 2) goto L15;
        return ContactsController.formatName(r2.first_name, r2.last_name);
    L21:
        return "DELETED";
    }

    public static String getForcedFirstName(TLRPC.User r2) {
        if (r2 == null) goto L18;
        if (isDeleted(r2) == true) goto L18;
        String r0 = r2.first_name;
        if (TextUtils.isEmpty(r0) == false) goto L9;
        r0 = r2.last_name;
    L9:
        if (r0 == null) goto L11;
        int r22 = r0.indexOf(" ", 2);
        if (r22 >= 0) goto L15;
        return r0;
    L15:
        return r0.substring(0, r22);
    L11:
        return LocaleController.getString(R.string.HiddenName);
    L18:
        return LocaleController.getString(R.string.HiddenName);
    }

    public static long getOnlyProfileEmojiId(TLRPC.User r2) {
        if (r2 == null) goto L9;
        TLRPC.PeerColor r22 = r2.profile_color;
        if ((r22 instanceof TLRPC.TL_peerColor) == true) goto L6;
        return 0;
    L6:
        if ((r22.flags & 2) != 0) goto L8;
        return 0;
    L8:
        return r22.background_emoji_id;
    L9:
        return 0;
    }

    public static MessagesController.PeerColor getPeerColorForAvatar(int r0, TLRPC.User r1) {
        return null;
    }

    public static TLRPC.UserProfilePhoto getPhoto(TLRPC.User r1) {
        if (hasPhoto(r1) == true) goto L5;
        return null;
    L5:
        return r1.photo;
    }

    public static long getProfileCollectibleId(TLRPC.User r2) {
        if (r2 == null) goto L7;
        TLRPC.EmojiStatus r22 = r2.emoji_status;
        if ((r22 instanceof TLRPC.TL_emojiStatusCollectible) == true) goto L6;
        return 0;
    L6:
        return ((TLRPC.TL_emojiStatusCollectible) r22).collectible_id;
    L7:
        return 0;
    }

    public static int getProfileColorId(TLRPC.User r1) {
        if (r1 != null) goto L5;
        return 0;
    L5:
        TLRPC.PeerColor r12 = r1.profile_color;
        if ((r12 instanceof TLRPC.TL_peerColor) == true) goto L8;
        return -1;
    L8:
        if ((r12.flags & 1) != 0) goto L10;
        return -1;
    L10:
        return r12.color;
    }

    public static long getProfileEmojiId(TLRPC.User r2) {
        if (r2 == null) goto L7;
        TLRPC.EmojiStatus r0 = r2.emoji_status;
        if ((r0 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L7;
        return ((TLRPC.TL_emojiStatusCollectible) r0).pattern_document_id;
    L7:
        if (r2 == null) goto L14;
        TLRPC.PeerColor r22 = r2.profile_color;
        if (r22 != null) goto L11;
        return 0;
    L11:
        if ((r22.flags & 2) != 0) goto L13;
        return 0;
    L13:
        return r22.background_emoji_id;
    L14:
        return 0;
    }

    public static String getPublicUsername(TLRPC.User r1) {
        return getPublicUsername(r1, false);
    }

    public static String getPublicUsername(TLRPC.User r4, boolean r5) {
        if (r4 != null) goto L6;
        return null;
    L6:
        if (TextUtils.isEmpty(r4.username) == true) goto L10;
        return r4.username;
    L10:
        if (r4.usernames == null) goto L26;
        int r1 = 0;
    L13:
        if (r1 >= r4.usernames.size()) goto L26;
        TLRPC.TL_username r2 = r4.usernames.get(r1);
        if (r2 == null) goto L25;
        if (r2.active == false) goto L20;
        if (r5 == true) goto L20;
    L22:
        if (TextUtils.isEmpty(r2.username) == true) goto L25;
        return r2.username;
    L20:
        if (r2.editable == true) goto L22;
    L25:
        r1 = r1 + 1;
    L26:
        return null;
    }

    public static TL_account.RequirementToContact getRequirementToContact(TLRPC.User r5) {
        if (r5 != null) goto L6;
        return null;
    L6:
        if (r5.send_paid_messages_stars == 0) goto L10;
        TL_account.requirementToContactPaidMessages r0 = new TL_account.requirementToContactPaidMessages();
        r0.stars_amount = r5.send_paid_messages_stars;
        return r0;
    L10:
        if (r5.contact_require_premium == true) goto L12;
        return null;
    L12:
        return new TL_account.requirementToContactPremium();
    }

    public static TL_account.RequirementToContact getRequirementToContact(TLRPC.UserFull r5) {
        if (r5 != null) goto L6;
        return null;
    L6:
        if (r5.send_paid_messages_stars == 0) goto L10;
        TL_account.requirementToContactPaidMessages r0 = new TL_account.requirementToContactPaidMessages();
        r0.stars_amount = r5.send_paid_messages_stars;
        return r0;
    L10:
        if (r5.contact_require_premium == true) goto L12;
        return null;
    L12:
        return new TL_account.requirementToContactPremium();
    }

    public static String getUserName(TLRPC.User r3) {
        if (r3 == null) goto L15;
        if (isDeleted(r3) == true) goto L15;
        String r0 = AndroidUtilities.removeRTL(AndroidUtilities.removeDiacritics(ContactsController.formatName(r3.first_name, r3.last_name)));
        if (r0.length() == 0) goto L9;
    L13:
        return r0;
    L9:
        if (TextUtils.isEmpty(r3.phone) == true) goto L13;
        return PhoneFormat.getInstance().format("+" + r3.phone);
    L15:
        return LocaleController.getString(R.string.HiddenName);
    }

    public static boolean hasFallbackPhoto(TLRPC.UserFull r0) {
        if (r0 == null) goto L9;
        TLRPC.Photo r02 = r0.fallback_photo;
        if (r02 != null) goto L6;
        return false;
    L6:
        if ((r02 instanceof TLRPC.TL_photoEmpty) == true) goto L12;
        return true;
    L12:
        return false;
    L9:
        return false;
    }

    public static boolean hasPhoto(TLRPC.User r0) {
        if (r0 == null) goto L9;
        TLRPC.UserProfilePhoto r02 = r0.photo;
        if (r02 != null) goto L6;
        return false;
    L6:
        if ((r02 instanceof TLRPC.TL_userProfilePhotoEmpty) == true) goto L12;
        return true;
    L12:
        return false;
    L9:
        return false;
    }

    public static boolean hasPublicUsername(TLRPC.User r5, String r6) {
        if (r5 == null) goto L22;
        if (r6 == null) goto L22;
        if (r6.equalsIgnoreCase(r5.username) == false) goto L10;
        return true;
    L10:
        if (r5.usernames == null) goto L22;
        int r1 = 0;
    L13:
        if (r1 >= r5.usernames.size()) goto L22;
        TLRPC.TL_username r3 = r5.usernames.get(r1);
        if (r3 == null) goto L21;
        if (r3.active == false) goto L21;
        if (r6.equalsIgnoreCase(r3.username) == false) goto L21;
        return true;
    L21:
        r1 = r1 + 1;
    L22:
        return false;
    }

    public static boolean isAnonymous(TLRPC.User r4) {
        if (r4 != null) goto L4;
        return false;
    L4:
        if (r4.id != ANONYMOUS) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isBot(TLRPC.User r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.bot == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isBotForum(int r0, long r1) {
        return isBotForum(MessagesController.getInstance(r0).getUser(Long.valueOf(r1)));
    }

    public static boolean isBotForum(TLRPC.User r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.bot_forum_view == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isBotForumWithEditableTopics(int r0, long r1) {
        return isBotForumWithEditableTopics(MessagesController.getInstance(r0).getUser(Long.valueOf(r1)));
    }

    public static boolean isBotForumWithEditableTopics(TLRPC.User r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.bot_forum_view == true) goto L6;
        return false;
    L6:
        if (r1.bot_forum_can_manage_topics == false) goto L12;
        return true;
    L12:
        return false;
    }

    public static boolean isContact(TLRPC.User r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if ((r1 instanceof TLRPC.TL_userContact_old2) == false) goto L6;
        return true;
    L6:
        if (r1.contact == false) goto L8;
        return true;
    L8:
        if (r1.mutual_contact == false) goto L15;
        return true;
    L15:
        return false;
    }

    public static boolean isDeleted(TLRPC.User r1) {
        if (r1 != null) goto L4;
        return true;
    L4:
        if ((r1 instanceof TLRPC.TL_userDeleted_old2) == false) goto L6;
        return true;
    L6:
        if ((r1 instanceof TLRPC.TL_userEmpty) == false) goto L8;
        return true;
    L8:
        if (r1.deleted == true) goto L16;
        return false;
    L16:
        return true;
    }

    public static boolean isReplyUser(long r2) {
        if (r2 != 708513) goto L5;
        return true;
    L5:
        if (r2 == REPLY_BOT) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isReplyUser(TLRPC.User r4) {
        if (r4 == null) goto L9;
        long r0 = r4.id;
        if (r0 != 708513) goto L6;
        return true;
    L6:
        if (r0 != REPLY_BOT) goto L12;
        return true;
    L12:
        return false;
    L9:
        return false;
    }

    public static boolean isService(long r2) {
        if (r2 != 333000) goto L5;
        return true;
    L5:
        if (r2 != 777000) goto L7;
        return true;
    L7:
        if (r2 == 42777) goto L14;
        return false;
    L14:
        return true;
    }

    public static boolean isUserSelf(TLRPC.User r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if ((r1 instanceof TLRPC.TL_userSelf_old3) == false) goto L6;
        return true;
    L6:
        if (r1.self == false) goto L12;
        return true;
    L12:
        return false;
    }
}
