package org.telegram.tgnet.tl;

import android.text.TextUtils;
import com.exteragram.messenger.export.api.ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda2;
import java.util.ArrayList;
import org.telegram.messenger.MessagesStorage$$ExternalSyntheticLambda117;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLMethod;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1;
import org.telegram.tgnet.TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1;
import org.telegram.tgnet.TLRPC$TL_inputPrivacyValueAllowUsers$$ExternalSyntheticLambda0;
import org.telegram.tgnet.TLRPC$TL_secureRequiredTypeOneOf$$ExternalSyntheticLambda0;
import org.telegram.tgnet.Vector;

/* loaded from: classes.dex */
public class TL_account {

    /* loaded from: classes6.dex */
    public static class BusinessAwayMessageSchedule extends TLObject {
        public BusinessAwayMessageSchedule() {
        }

        public static BusinessAwayMessageSchedule TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1007487743)) goto L5;
            TLObject r0 = new TL_businessAwayMessageScheduleOutsideWorkHours();
        L13:
            return (BusinessAwayMessageSchedule) TLObject.TLdeserialize(BusinessAwayMessageSchedule.class, r0, r2, r3, r4);
        L5:
            if (r3 != (-910564679)) goto L7;
            r0 = new TL_businessAwayMessageScheduleAlways();
            goto L13
        L7:
            if (r3 == (-867328308)) goto L9;
            r0 = null;
            goto L13
        L9:
            r0 = new TL_businessAwayMessageScheduleCustom();
            goto L13
        }
    }

    /* loaded from: classes6.dex */
    public static abstract class ChatThemes extends TLObject {
        public ChatThemes() {
        }

        public static ChatThemes TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (ChatThemes) TLObject.TLdeserialize(ChatThemes.class, fromConstructor(r3), r2, r3, r4);
        }

        private static ChatThemes fromConstructor(int r1) {
            if (r1 == (-1106673293)) goto L11;
            if (r1 == (-535699004)) goto L9;
            return null;
        L9:
            return new TL_chatThemesNotModified();
        L11:
            return new Tl_chatThemes();
        }
    }

    /* loaded from: classes6.dex */
    public static class EmailVerified extends TLObject {
        public EmailVerified() {
        }

        public static EmailVerified TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-507835039)) goto L5;
            TLObject r0 = new TL_emailVerifiedLogin();
        L10:
            return (EmailVerified) TLObject.TLdeserialize(EmailVerified.class, r0, r2, r3, r4);
        L5:
            if (r3 == 731303195) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_emailVerified();
            goto L10
        }
    }

    public static class EmojiStatuses extends TLObject {
        public long hash;
        public ArrayList<TLRPC.EmojiStatus> statuses;

        public EmojiStatuses() {
            this.statuses = new ArrayList();
        }

        public static EmojiStatuses TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1866176559)) goto L5;
            TLObject r0 = new TL_emojiStatuses();
        L10:
            return (EmojiStatuses) TLObject.TLdeserialize(EmojiStatuses.class, r0, r2, r3, r4);
        L5:
            if (r3 == (-796072379)) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_emojiStatusesNotModified();
            goto L10
        }
    }

    /* loaded from: classes6.dex */
    public static class InputPasskeyResponse extends TLObject {
        public InputPasskeyResponse() {
        }

        public static InputPasskeyResponse TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1021329078)) goto L5;
            TLObject r0 = new inputPasskeyResponseLogin();
        L10:
            return (InputPasskeyResponse) TLObject.TLdeserialize(InputPasskeyResponse.class, r0, r2, r3, r4);
        L5:
            if (r3 == 1046713180) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new inputPasskeyResponseRegister();
            goto L10
        }
    }

    /* loaded from: classes6.dex */
    public static class Passkey extends TLObject {
        public static final int constructor = -1738457409;
        public int date;
        public int flags;
        public String id;
        public int last_usage_date;
        public String name;
        public long software_emoji_id;

        public Passkey() {
        }

        public static Passkey TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-1738457409)) goto L5;
            Passkey r0 = null;
        L7:
            return (Passkey) TLObject.TLdeserialize(Passkey.class, r0, r2, r3, r4);
        L5:
            r0 = new Passkey();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.id = r3.readString(r4);
            this.name = r3.readString(r4);
            this.date = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.software_emoji_id = r3.readInt64(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.last_usage_date = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeString(this.id);
            r3.writeString(this.name);
            r3.writeInt32(this.date);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt64(this.software_emoji_id);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeInt32(this.last_usage_date);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class Passkeys extends TLObject {
        public static final int constructor = -119494116;
        public ArrayList<Passkey> passkeys;

        public Passkeys() {
            this.passkeys = new ArrayList();
        }

        public static Passkeys TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-119494116)) goto L5;
            Passkeys r0 = null;
        L7:
            return (Passkeys) TLObject.TLdeserialize(Passkeys.class, r0, r2, r3, r4);
        L5:
            r0 = new Passkeys();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.passkeys = Vector.deserialize(r2, new TL_account$Passkeys$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.passkeys);
        }
    }

    /* loaded from: classes6.dex */
    public static class Password extends TLObject {
        public TLRPC.PasswordKdfAlgo current_algo;
        public String email_unconfirmed_pattern;
        public int flags;
        public boolean has_password;
        public boolean has_recovery;
        public boolean has_secure_values;
        public String hint;
        public String login_email_pattern;
        public TLRPC.PasswordKdfAlgo new_algo;
        public TLRPC.SecurePasswordKdfAlgo new_secure_algo;
        public int pending_reset_date;
        public byte[] secure_random;
        public byte[] srp_B;
        public long srp_id;

        public Password() {
        }

        public static Password TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1787080453)) goto L5;
            TLObject r0 = new TL_password();
        L10:
            return (Password) TLObject.TLdeserialize(Password.class, r0, r2, r3, r4);
        L5:
            if (r3 == 408623183) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_password_layer144();
            goto L10
        }
    }

    /* loaded from: classes6.dex */
    public static class ReactionNotificationsFrom extends TLObject {
        public ReactionNotificationsFrom() {
        }

        public static ReactionNotificationsFrom TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1161583078)) goto L5;
            TLObject r0 = new TL_reactionNotificationsFromContacts();
        L10:
            return (ReactionNotificationsFrom) TLObject.TLdeserialize(ReactionNotificationsFrom.class, r0, r2, r3, r4);
        L5:
            if (r3 == 1268654752) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_reactionNotificationsFromAll();
            goto L10
        }
    }

    public static class RequirementToContact extends TLObject {
        public RequirementToContact() {
        }

        public static RequirementToContact TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1258914157)) goto L5;
            TLObject r0 = new requirementToContactPaidMessages();
        L13:
            return (RequirementToContact) TLObject.TLdeserialize(RequirementToContact.class, r0, r2, r3, r4);
        L5:
            if (r3 != (-444472087)) goto L7;
            r0 = new requirementToContactPremium();
            goto L13
        L7:
            if (r3 == 84580409) goto L9;
            r0 = null;
            goto L13
        L9:
            r0 = new requirementToContactEmpty();
            goto L13
        }
    }

    /* loaded from: classes6.dex */
    public static class ResetPasswordResult extends TLObject {
        public ResetPasswordResult() {
        }

        public static ResetPasswordResult TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-478701471)) goto L5;
            TLObject r0 = new resetPasswordFailedWait();
        L13:
            return (ResetPasswordResult) TLObject.TLdeserialize(ResetPasswordResult.class, r0, r2, r3, r4);
        L5:
            if (r3 != (-383330754)) goto L7;
            r0 = new resetPasswordOk();
            goto L13
        L7:
            if (r3 == (-370148227)) goto L9;
            r0 = null;
            goto L13
        L9:
            r0 = new resetPasswordRequestedWait();
            goto L13
        }
    }

    /* loaded from: classes6.dex */
    public static class SavedMusicIds extends TLObject {
        public ArrayList<Long> ids;

        public SavedMusicIds() {
            this.ids = new ArrayList();
        }

        public static SavedMusicIds TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1718786506)) goto L5;
            TLObject r0 = new TL_savedMusicIds();
        L10:
            return (SavedMusicIds) TLObject.TLdeserialize(SavedMusicIds.class, r0, r2, r3, r4);
        L5:
            if (r3 == 1338514798) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_savedMusicIdsNotModified();
            goto L10
        }
    }

    /* loaded from: classes6.dex */
    public static class SavedRingtone extends TLObject {
        public SavedRingtone() {
        }

        public static SavedRingtone TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1222230163)) goto L5;
            TLObject r0 = new TL_savedRingtone();
        L10:
            return (SavedRingtone) TLObject.TLdeserialize(SavedRingtone.class, r0, r2, r3, r4);
        L5:
            if (r3 == 523271863) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_savedRingtoneConverted();
            goto L10
        }
    }

    /* loaded from: classes6.dex */
    public static class SavedRingtones extends TLObject {
        public SavedRingtones() {
        }

        public static SavedRingtones TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1041683259)) goto L5;
            TLObject r0 = new TL_savedRingtones();
        L10:
            return (SavedRingtones) TLObject.TLdeserialize(SavedRingtones.class, r0, r2, r3, r4);
        L5:
            if (r3 == (-67704655)) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_savedRingtonesNotModified();
            goto L10
        }
    }

    public static class TL_birthday extends TLObject {
        public static final int constructor = 1821253126;
        public int day;
        public int flags;
        public int month;
        public int year;

        public TL_birthday() {
        }

        public static TL_birthday TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 1821253126) goto L5;
            TL_birthday r0 = null;
        L7:
            return (TL_birthday) TLObject.TLdeserialize(TL_birthday.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_birthday();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.day = r3.readInt32(r4);
            this.month = r3.readInt32(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.year = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt32(this.day);
            r3.writeInt32(this.month);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt32(this.year);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_businessAwayMessage extends TLObject {
        public static final int constructor = -283809188;
        public int flags;
        public boolean offline_only;
        public TL_businessRecipients recipients;
        public BusinessAwayMessageSchedule schedule;
        public int shortcut_id;

        public TL_businessAwayMessage() {
        }

        public static TL_businessAwayMessage TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-283809188)) goto L5;
            TL_businessAwayMessage r0 = null;
        L7:
            return (TL_businessAwayMessage) TLObject.TLdeserialize(TL_businessAwayMessage.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_businessAwayMessage();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.offline_only = TLObject.hasFlag(r0, 1);
            this.shortcut_id = r3.readInt32(r4);
            this.schedule = BusinessAwayMessageSchedule.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.recipients = TL_businessRecipients.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.offline_only);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt32(this.shortcut_id);
            this.schedule.serializeToStream(r4);
            this.recipients.serializeToStream(r4);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_businessAwayMessageScheduleAlways extends BusinessAwayMessageSchedule {
        public static final int constructor = -910564679;

        public TL_businessAwayMessageScheduleAlways() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_businessAwayMessageScheduleCustom extends BusinessAwayMessageSchedule {
        public static final int constructor = -867328308;
        public int end_date;
        public int start_date;

        public TL_businessAwayMessageScheduleCustom() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.start_date = r2.readInt32(r3);
            this.end_date = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.start_date);
            r2.writeInt32(this.end_date);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_businessAwayMessageScheduleOutsideWorkHours extends BusinessAwayMessageSchedule {
        public static final int constructor = -1007487743;

        public TL_businessAwayMessageScheduleOutsideWorkHours() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_businessBotRecipients extends TLObject {
        public static final int constructor = -1198722189;
        public boolean contacts;
        public boolean exclude_selected;
        public ArrayList<Long> exclude_users;
        public boolean existing_chats;
        public int flags;
        public boolean new_chats;
        public boolean non_contacts;
        public ArrayList<Long> users;

        public TL_businessBotRecipients() {
            this.users = new ArrayList();
            this.exclude_users = new ArrayList();
        }

        public static TL_businessBotRecipients TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-1198722189)) goto L5;
            TL_businessBotRecipients r0 = null;
        L7:
            return (TL_businessBotRecipients) TLObject.TLdeserialize(TL_businessBotRecipients.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_businessBotRecipients();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.existing_chats = TLObject.hasFlag(r0, 1);
            this.new_chats = TLObject.hasFlag(this.flags, 2);
            this.contacts = TLObject.hasFlag(this.flags, 4);
            this.non_contacts = TLObject.hasFlag(this.flags, 8);
            this.exclude_selected = TLObject.hasFlag(this.flags, 32);
            if (TLObject.hasFlag(this.flags, 16) == false) goto L6;
            this.users = Vector.deserializeLong(r3, r4);
        L6:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L9;
            this.exclude_users = Vector.deserializeLong(r3, r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.existing_chats);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.new_chats);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.contacts);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8, this.non_contacts);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 32, this.exclude_selected);
            this.flags = r05;
            r4.writeInt32(r05);
            if (TLObject.hasFlag(this.flags, 16) == false) goto L6;
            Vector.serializeLong(r4, this.users);
        L6:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L9;
            Vector.serializeLong(r4, this.exclude_users);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_businessBotRights extends TLObject {
        public static final int constructor = -1604170505;
        public boolean change_gift_settings;
        public boolean delete_received_messages;
        public boolean delete_sent_messages;
        public boolean edit_bio;
        public boolean edit_name;
        public boolean edit_profile_photo;
        public boolean edit_username;
        public int flags;
        public boolean manage_stories;
        public boolean read_messages;
        public boolean reply;
        public boolean sell_gifts;
        public boolean transfer_and_upgrade_gifts;
        public boolean transfer_stars;
        public boolean view_gifts;

        public TL_businessBotRights() {
        }

        public static TL_businessBotRights TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-1604170505)) goto L5;
            TL_businessBotRights r0 = null;
        L7:
            return (TL_businessBotRights) TLObject.TLdeserialize(TL_businessBotRights.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_businessBotRights();
            goto L7
        }

        public static TL_businessBotRights all() {
            TL_businessBotRights r0 = new TL_businessBotRights();
            r0.reply = true;
            r0.read_messages = true;
            r0.delete_sent_messages = true;
            r0.delete_received_messages = true;
            r0.edit_name = true;
            r0.edit_bio = true;
            r0.edit_profile_photo = true;
            r0.edit_username = true;
            r0.view_gifts = true;
            r0.sell_gifts = true;
            r0.change_gift_settings = true;
            r0.transfer_and_upgrade_gifts = true;
            r0.transfer_stars = true;
            r0.manage_stories = true;
            return r0;
        }

        public static TL_businessBotRights clone(TL_businessBotRights r2) {
            TL_businessBotRights r0 = new TL_businessBotRights();
            r0.reply = r2.reply;
            r0.read_messages = r2.read_messages;
            r0.delete_sent_messages = r2.delete_sent_messages;
            r0.delete_received_messages = r2.delete_received_messages;
            r0.edit_name = r2.edit_name;
            r0.edit_bio = r2.edit_bio;
            r0.edit_profile_photo = r2.edit_profile_photo;
            r0.edit_username = r2.edit_username;
            r0.view_gifts = r2.view_gifts;
            r0.sell_gifts = r2.sell_gifts;
            r0.change_gift_settings = r2.change_gift_settings;
            r0.transfer_and_upgrade_gifts = r2.transfer_and_upgrade_gifts;
            r0.transfer_stars = r2.transfer_stars;
            r0.manage_stories = r2.manage_stories;
            return r0;
        }

        public static TL_businessBotRights makeDefault() {
            TL_businessBotRights r0 = new TL_businessBotRights();
            r0.reply = true;
            r0.read_messages = true;
            r0.delete_sent_messages = true;
            r0.delete_received_messages = true;
            r0.edit_name = false;
            r0.edit_bio = false;
            r0.edit_profile_photo = false;
            r0.edit_username = false;
            r0.view_gifts = false;
            r0.sell_gifts = false;
            r0.change_gift_settings = false;
            r0.transfer_and_upgrade_gifts = false;
            r0.transfer_stars = false;
            r0.manage_stories = false;
            return r0;
        }

        public boolean equals(Object r4) {
            if ((r4 instanceof TL_businessBotRights) == true) goto L5;
            return false;
        L5:
            TL_businessBotRights r42 = (TL_businessBotRights) r4;
            if (this.reply == r42.reply) goto L8;
        L35:
            return false;
        L8:
            if (this.read_messages != r42.read_messages) goto L35;
            if (this.delete_sent_messages != r42.delete_sent_messages) goto L35;
            if (this.delete_received_messages != r42.delete_received_messages) goto L35;
            if (this.edit_name != r42.edit_name) goto L35;
            if (this.edit_bio != r42.edit_bio) goto L35;
            if (this.edit_profile_photo != r42.edit_profile_photo) goto L35;
            if (this.edit_username != r42.edit_username) goto L35;
            if (this.view_gifts != r42.view_gifts) goto L35;
            if (this.sell_gifts != r42.sell_gifts) goto L35;
            if (this.change_gift_settings != r42.change_gift_settings) goto L35;
            if (this.transfer_and_upgrade_gifts != r42.transfer_and_upgrade_gifts) goto L35;
            if (this.transfer_stars != r42.transfer_stars) goto L35;
            if (this.manage_stories != r42.manage_stories) goto L35;
            return true;
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            int r12 = r1.readInt32(r2);
            this.flags = r12;
            this.reply = TLObject.hasFlag(r12, 1);
            this.read_messages = TLObject.hasFlag(this.flags, 2);
            this.delete_sent_messages = TLObject.hasFlag(this.flags, 4);
            this.delete_received_messages = TLObject.hasFlag(this.flags, 8);
            this.edit_name = TLObject.hasFlag(this.flags, 16);
            this.edit_bio = TLObject.hasFlag(this.flags, 32);
            this.edit_profile_photo = TLObject.hasFlag(this.flags, 64);
            this.edit_username = TLObject.hasFlag(this.flags, 128);
            this.view_gifts = TLObject.hasFlag(this.flags, 256);
            this.sell_gifts = TLObject.hasFlag(this.flags, 512);
            this.change_gift_settings = TLObject.hasFlag(this.flags, 1024);
            this.transfer_and_upgrade_gifts = TLObject.hasFlag(this.flags, 2048);
            this.transfer_stars = TLObject.hasFlag(this.flags, 4096);
            this.manage_stories = TLObject.hasFlag(this.flags, 8192);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.reply);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.read_messages);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.delete_sent_messages);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8, this.delete_received_messages);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 16, this.edit_name);
            this.flags = r05;
            int r06 = TLObject.setFlag(r05, 32, this.edit_bio);
            this.flags = r06;
            int r07 = TLObject.setFlag(r06, 64, this.edit_profile_photo);
            this.flags = r07;
            int r08 = TLObject.setFlag(r07, 128, this.edit_username);
            this.flags = r08;
            int r09 = TLObject.setFlag(r08, 256, this.view_gifts);
            this.flags = r09;
            int r010 = TLObject.setFlag(r09, 512, this.sell_gifts);
            this.flags = r010;
            int r011 = TLObject.setFlag(r010, 1024, this.change_gift_settings);
            this.flags = r011;
            int r012 = TLObject.setFlag(r011, 2048, this.transfer_and_upgrade_gifts);
            this.flags = r012;
            int r013 = TLObject.setFlag(r012, 4096, this.transfer_stars);
            this.flags = r013;
            int r014 = TLObject.setFlag(r013, 8192, this.manage_stories);
            this.flags = r014;
            r4.writeInt32(r014);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_businessChatLink extends TLObject {
        public static final int constructor = -1263638929;
        public ArrayList<TLRPC.MessageEntity> entities;
        public int flags;
        public String link;
        public String message;
        public String title;
        public int views;

        public TL_businessChatLink() {
            this.entities = new ArrayList();
        }

        public static TL_businessChatLink TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1263638929) == r3) goto L5;
            TL_businessChatLink r0 = null;
        L7:
            return (TL_businessChatLink) TLObject.TLdeserialize(TL_businessChatLink.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_businessChatLink();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.link = r3.readString(r4);
            this.message = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            this.title = r3.readString(r4);
        L8:
            this.views = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeString(this.link);
            r3.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            Vector.serialize(r3, this.entities);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            r3.writeString(this.title);
        L8:
            r3.writeInt32(this.views);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_businessGreetingMessage extends TLObject {
        public static final int constructor = -451302485;
        public int no_activity_days;
        public TL_businessRecipients recipients;
        public int shortcut_id;

        public TL_businessGreetingMessage() {
        }

        public static TL_businessGreetingMessage TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-451302485)) goto L5;
            TL_businessGreetingMessage r0 = null;
        L7:
            return (TL_businessGreetingMessage) TLObject.TLdeserialize(TL_businessGreetingMessage.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_businessGreetingMessage();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.shortcut_id = r2.readInt32(r3);
            this.recipients = TL_businessRecipients.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.no_activity_days = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.shortcut_id);
            this.recipients.serializeToStream(r2);
            r2.writeInt32(this.no_activity_days);
        }
    }

    public static class TL_businessIntro extends TLObject {
        public static final int constructor = 1510606445;
        public String description;
        public int flags;
        public TLRPC.Document sticker;
        public String title;

        public TL_businessIntro() {
        }

        public static TL_businessIntro TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 1510606445) goto L5;
            TL_businessIntro r0 = null;
        L7:
            return (TL_businessIntro) TLObject.TLdeserialize(TL_businessIntro.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_businessIntro();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.title = r3.readString(r4);
            this.description = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.sticker = TLRPC.Document.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeString(this.title);
            r3.writeString(this.description);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.sticker.serializeToStream(r3);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_businessRecipients extends TLObject {
        public static final int constructor = 554733559;
        public boolean contacts;
        public boolean exclude_selected;
        public boolean existing_chats;
        public int flags;
        public boolean new_chats;
        public boolean non_contacts;
        public ArrayList<Long> users;

        public TL_businessRecipients() {
            this.users = new ArrayList();
        }

        public static TL_businessRecipients TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 554733559) goto L5;
            TL_businessRecipients r0 = null;
        L7:
            return (TL_businessRecipients) TLObject.TLdeserialize(TL_businessRecipients.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_businessRecipients();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.existing_chats = TLObject.hasFlag(r0, 1);
            this.new_chats = TLObject.hasFlag(this.flags, 2);
            this.contacts = TLObject.hasFlag(this.flags, 4);
            this.non_contacts = TLObject.hasFlag(this.flags, 8);
            this.exclude_selected = TLObject.hasFlag(this.flags, 32);
            if (TLObject.hasFlag(this.flags, 16) == false) goto L6;
            this.users = Vector.deserializeLong(r3, r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.existing_chats);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.new_chats);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.contacts);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8, this.non_contacts);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 32, this.exclude_selected);
            this.flags = r05;
            r4.writeInt32(r05);
            if (TLObject.hasFlag(this.flags, 16) == false) goto L6;
            Vector.serializeLong(r4, this.users);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_businessWeeklyOpen extends TLObject {
        public static final int constructor = 302717625;
        public int end_minute;
        public int start_minute;

        public TL_businessWeeklyOpen() {
        }

        public static TL_businessWeeklyOpen TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 302717625) goto L5;
            TL_businessWeeklyOpen r0 = null;
        L7:
            return (TL_businessWeeklyOpen) TLObject.TLdeserialize(TL_businessWeeklyOpen.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_businessWeeklyOpen();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.start_minute = r2.readInt32(r3);
            this.end_minute = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.start_minute);
            r2.writeInt32(this.end_minute);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_businessWorkHours extends TLObject {
        public static final int constructor = -1936543592;
        public int flags;
        public boolean open_now;
        public String timezone_id;
        public ArrayList<TL_businessWeeklyOpen> weekly_open;

        public TL_businessWorkHours() {
            this.weekly_open = new ArrayList();
        }

        public static TL_businessWorkHours TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-1936543592)) goto L5;
            TL_businessWorkHours r0 = null;
        L7:
            return (TL_businessWorkHours) TLObject.TLdeserialize(TL_businessWorkHours.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_businessWorkHours();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.open_now = TLObject.hasFlag(r0, 1);
            this.timezone_id = r3.readString(r4);
            this.weekly_open = Vector.deserialize(r3, new TL_account$TL_businessWorkHours$$ExternalSyntheticLambda0(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.open_now);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeString(this.timezone_id);
            Vector.serialize(r4, this.weekly_open);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_chatThemesNotModified extends ChatThemes {
        public static final int constructor = -535699004;

        public TL_chatThemesNotModified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_connectedBot extends TLObject {
        public static final int constructor = 54448129;
        public long bot_id;
        public int date;
        public String device;
        public int flags;
        public String location;
        public TL_businessBotRecipients recipients;
        public TL_businessBotRights rights;

        public TL_connectedBot() {
        }

        public static TL_connectedBot TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 54448129) goto L5;
            TL_connectedBot r0 = null;
        L7:
            return (TL_connectedBot) TLObject.TLdeserialize(TL_connectedBot.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_connectedBot();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.bot_id = r3.readInt64(r4);
            this.recipients = TL_businessBotRecipients.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.rights = TL_businessBotRights.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.device = r3.readString(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.date = r3.readInt32(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.location = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt64(this.bot_id);
            this.recipients.serializeToStream(r3);
            this.rights.serializeToStream(r3);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.device);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeInt32(this.date);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            r3.writeString(this.location);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_contactBirthday extends TLObject {
        public static final int constructor = 496600883;
        public TL_birthday birthday;
        public long contact_id;

        public TL_contactBirthday() {
        }

        public static TL_contactBirthday TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 496600883) goto L5;
            TL_contactBirthday r0 = null;
        L7:
            return (TL_contactBirthday) TLObject.TLdeserialize(TL_contactBirthday.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_contactBirthday();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.contact_id = r3.readInt64(r4);
            this.birthday = TL_birthday.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.contact_id);
            this.birthday.serializeToStream(r3);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_emailVerified extends EmailVerified {
        public static final int constructor = 731303195;
        public String email;

        public TL_emailVerified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.email = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.email);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_emailVerifiedLogin extends EmailVerified {
        public static final int constructor = -507835039;
        public String email;
        public TLRPC.auth_SentCode sent_code;

        public TL_emailVerifiedLogin() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.email = r2.readString(r3);
            this.sent_code = TLRPC.auth_SentCode.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.email);
            this.sent_code.serializeToStream(r2);
        }
    }

    public static class TL_emojiStatuses extends EmojiStatuses {
        public static final int constructor = -1866176559;

        public TL_emojiStatuses() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.hash = r3.readInt64(r4);
            this.statuses = Vector.deserialize(r3, new TL_account$TL_emojiStatuses$$ExternalSyntheticLambda0(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
            Vector.serialize(r3, this.statuses);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_emojiStatusesNotModified extends EmojiStatuses {
        public static final int constructor = -796072379;

        public TL_emojiStatusesNotModified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_inputBusinessAwayMessage extends TLObject {
        public static final int constructor = -2094959136;
        public int flags;
        public boolean offline_only;
        public TL_inputBusinessRecipients recipients;
        public BusinessAwayMessageSchedule schedule;
        public int shortcut_id;

        public TL_inputBusinessAwayMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.offline_only = TLObject.hasFlag(r0, 1);
            this.shortcut_id = r3.readInt32(r4);
            this.schedule = BusinessAwayMessageSchedule.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.recipients = TL_inputBusinessRecipients.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            boolean r0 = this.offline_only;
            int r1 = this.flags;
            if (r0 == false) goto L5;
            int r02 = r1 | 1;
        L6:
            this.flags = r02;
            r3.writeInt32(r02);
            r3.writeInt32(this.shortcut_id);
            this.schedule.serializeToStream(r3);
            this.recipients.serializeToStream(r3);
            return;
        L5:
            r02 = r1 & 1;
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_inputBusinessBotRecipients extends TLObject {
        public static final int constructor = -991587810;
        public boolean contacts;
        public boolean exclude_selected;
        public ArrayList<TLRPC.InputUser> exclude_users;
        public boolean existing_chats;
        public int flags;
        public boolean new_chats;
        public boolean non_contacts;
        public ArrayList<TLRPC.InputUser> users;

        public TL_inputBusinessBotRecipients() {
            this.users = new ArrayList();
            this.exclude_users = new ArrayList();
        }

        public static TL_inputBusinessBotRecipients TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-991587810)) goto L5;
            TL_inputBusinessBotRecipients r0 = null;
        L7:
            return (TL_inputBusinessBotRecipients) TLObject.TLdeserialize(TL_inputBusinessBotRecipients.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_inputBusinessBotRecipients();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.existing_chats = TLObject.hasFlag(r0, 1);
            this.new_chats = TLObject.hasFlag(this.flags, 2);
            this.contacts = TLObject.hasFlag(this.flags, 4);
            this.non_contacts = TLObject.hasFlag(this.flags, 8);
            this.exclude_selected = TLObject.hasFlag(this.flags, 32);
            if (TLObject.hasFlag(this.flags, 16) == false) goto L6;
            this.users = Vector.deserialize(r3, new TLRPC$TL_inputPrivacyValueAllowUsers$$ExternalSyntheticLambda0(), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L9;
            this.exclude_users = Vector.deserialize(r3, new TLRPC$TL_inputPrivacyValueAllowUsers$$ExternalSyntheticLambda0(), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.existing_chats);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.new_chats);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.contacts);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8, this.non_contacts);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 32, this.exclude_selected);
            this.flags = r05;
            r4.writeInt32(r05);
            if (TLObject.hasFlag(this.flags, 16) == false) goto L6;
            Vector.serialize(r4, this.users);
        L6:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L9;
            Vector.serialize(r4, this.exclude_users);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_inputBusinessChatLink extends TLObject {
        public static final int constructor = 292003751;
        public ArrayList<TLRPC.MessageEntity> entities;
        public int flags;
        public String message;
        public String title;

        public TL_inputBusinessChatLink() {
            this.entities = new ArrayList();
        }

        public static TL_inputBusinessChatLink TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (292003751 == r3) goto L5;
            TL_inputBusinessChatLink r0 = null;
        L7:
            return (TL_inputBusinessChatLink) TLObject.TLdeserialize(TL_inputBusinessChatLink.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_inputBusinessChatLink();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.message = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.title = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            Vector.serialize(r3, this.entities);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeString(this.title);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_inputBusinessGreetingMessage extends TLObject {
        public static final int constructor = 26528571;
        public int no_activity_days;
        public TL_inputBusinessRecipients recipients;
        public int shortcut_id;

        public TL_inputBusinessGreetingMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.shortcut_id = r2.readInt32(r3);
            this.recipients = TL_inputBusinessRecipients.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.no_activity_days = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.shortcut_id);
            this.recipients.serializeToStream(r2);
            r2.writeInt32(this.no_activity_days);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_inputBusinessIntro extends TLObject {
        public static final int constructor = 163867085;
        public String description;
        public int flags;
        public TLRPC.InputDocument sticker;
        public String title;

        public TL_inputBusinessIntro() {
        }

        public static TL_inputBusinessIntro TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 163867085) goto L5;
            TL_inputBusinessIntro r0 = null;
        L7:
            return (TL_inputBusinessIntro) TLObject.TLdeserialize(TL_inputBusinessIntro.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_inputBusinessIntro();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.title = r3.readString(r4);
            this.description = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.sticker = TLRPC.InputDocument.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeString(this.title);
            r3.writeString(this.description);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.sticker.serializeToStream(r3);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_inputBusinessRecipients extends TLObject {
        public static final int constructor = 1871393450;
        public boolean contacts;
        public boolean exclude_selected;
        public boolean existing_chats;
        public int flags;
        public boolean new_chats;
        public boolean non_contacts;
        public ArrayList<TLRPC.InputUser> users;

        public TL_inputBusinessRecipients() {
            this.users = new ArrayList();
        }

        public static TL_inputBusinessRecipients TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 1871393450) goto L5;
            TL_inputBusinessRecipients r0 = null;
        L7:
            return (TL_inputBusinessRecipients) TLObject.TLdeserialize(TL_inputBusinessRecipients.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_inputBusinessRecipients();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.existing_chats = TLObject.hasFlag(r0, 1);
            this.new_chats = TLObject.hasFlag(this.flags, 2);
            this.contacts = TLObject.hasFlag(this.flags, 4);
            this.non_contacts = TLObject.hasFlag(this.flags, 8);
            this.exclude_selected = TLObject.hasFlag(this.flags, 32);
            if (TLObject.hasFlag(this.flags, 16) == false) goto L6;
            this.users = Vector.deserialize(r3, new TLRPC$TL_inputPrivacyValueAllowUsers$$ExternalSyntheticLambda0(), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.existing_chats);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.new_chats);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.contacts);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8, this.non_contacts);
            this.flags = r04;
            int r05 = TLObject.setFlag(r04, 32, this.exclude_selected);
            this.flags = r05;
            r4.writeInt32(r05);
            if (TLObject.hasFlag(this.flags, 16) == false) goto L6;
            Vector.serialize(r4, this.users);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_password extends Password {
        public static final int constructor = -1787080453;

        public TL_password() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.has_recovery = TLObject.hasFlag(r0, 1);
            this.has_secure_values = TLObject.hasFlag(this.flags, 2);
            this.has_password = TLObject.hasFlag(this.flags, 4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L6;
            this.current_algo = TLRPC.PasswordKdfAlgo.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.srp_B = r3.readByteArray(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.srp_id = r3.readInt64(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.hint = r3.readString(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L17;
            this.email_unconfirmed_pattern = r3.readString(r4);
        L17:
            this.new_algo = TLRPC.PasswordKdfAlgo.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.new_secure_algo = TLRPC.SecurePasswordKdfAlgo.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.secure_random = r3.readByteArray(r4);
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.pending_reset_date = r3.readInt32(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L24;
            this.login_email_pattern = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.has_recovery);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.has_secure_values);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.has_password);
            this.flags = r03;
            r4.writeInt32(r03);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L6;
            this.current_algo.serializeToStream(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            r4.writeByteArray(this.srp_B);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            r4.writeInt64(this.srp_id);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            r4.writeString(this.hint);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L17;
            r4.writeString(this.email_unconfirmed_pattern);
        L17:
            this.new_algo.serializeToStream(r4);
            this.new_secure_algo.serializeToStream(r4);
            r4.writeByteArray(this.secure_random);
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            r4.writeInt32(this.pending_reset_date);
        L21:
            if (TLObject.hasFlag(this.flags, 64) == false) goto L24;
            r4.writeString(this.login_email_pattern);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_password_layer144 extends Password {
        public static final int constructor = 408623183;

        public TL_password_layer144() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.has_recovery = TLObject.hasFlag(r0, 1);
            this.has_secure_values = TLObject.hasFlag(this.flags, 2);
            this.has_password = TLObject.hasFlag(this.flags, 4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L6;
            this.current_algo = TLRPC.PasswordKdfAlgo.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.srp_B = r3.readByteArray(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.srp_id = r3.readInt64(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.hint = r3.readString(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L17;
            this.email_unconfirmed_pattern = r3.readString(r4);
        L17:
            this.new_algo = TLRPC.PasswordKdfAlgo.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.new_secure_algo = TLRPC.SecurePasswordKdfAlgo.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.secure_random = r3.readByteArray(r4);
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            this.pending_reset_date = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.has_recovery);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.has_secure_values);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.has_password);
            this.flags = r03;
            r4.writeInt32(r03);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L6;
            this.current_algo.serializeToStream(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            r4.writeByteArray(this.srp_B);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            r4.writeInt64(this.srp_id);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            r4.writeString(this.hint);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L17;
            r4.writeString(this.email_unconfirmed_pattern);
        L17:
            this.new_algo.serializeToStream(r4);
            this.new_secure_algo.serializeToStream(r4);
            r4.writeByteArray(this.secure_random);
            if (TLObject.hasFlag(this.flags, 32) == false) goto L21;
            r4.writeInt32(this.pending_reset_date);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_reactionNotificationsFromAll extends ReactionNotificationsFrom {
        public static final int constructor = 1268654752;

        public TL_reactionNotificationsFromAll() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_reactionNotificationsFromContacts extends ReactionNotificationsFrom {
        public static final int constructor = -1161583078;

        public TL_reactionNotificationsFromContacts() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_reactionsNotifySettings extends TLObject {
        public static final int constructor = 1910827608;
        public int flags;
        public ReactionNotificationsFrom messages_notify_from;
        public ReactionNotificationsFrom poll_votes_notify_from;
        public boolean show_previews;
        public TLRPC.NotificationSound sound;
        public ReactionNotificationsFrom stories_notify_from;

        public TL_reactionsNotifySettings() {
        }

        public static TL_reactionsNotifySettings TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1910827608 == r3) goto L5;
            TL_reactionsNotifySettings r0 = null;
        L7:
            return (TL_reactionsNotifySettings) TLObject.TLdeserialize(TL_reactionsNotifySettings.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_reactionsNotifySettings();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1) == false) goto L6;
            this.messages_notify_from = ReactionNotificationsFrom.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.stories_notify_from = ReactionNotificationsFrom.TLdeserialize(r3, r3.readInt32(r4), r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.poll_votes_notify_from = ReactionNotificationsFrom.TLdeserialize(r3, r3.readInt32(r4), r4);
        L11:
            this.sound = TLRPC.NotificationSound.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.show_previews = r3.readBool(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.messages_notify_from.serializeToStream(r3);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.stories_notify_from.serializeToStream(r3);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L11;
            this.poll_votes_notify_from.serializeToStream(r3);
        L11:
            this.sound.serializeToStream(r3);
            r3.writeBool(this.show_previews);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_savedMusicIds extends SavedMusicIds {
        public static final int constructor = -1718786506;

        public TL_savedMusicIds() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.ids = Vector.deserializeLong(r1, r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serializeLong(r2, this.ids);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_savedMusicIdsNotModified extends SavedMusicIds {
        public static final int constructor = 1338514798;

        public TL_savedMusicIdsNotModified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_savedRingtone extends SavedRingtone {
        public static final int constructor = -1222230163;

        public TL_savedRingtone() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_savedRingtoneConverted extends SavedRingtone {
        public static final int constructor = 523271863;
        public TLRPC.Document document;

        public TL_savedRingtoneConverted() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.document = TLRPC.Document.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.document.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_savedRingtones extends SavedRingtones {
        public static final int constructor = -1041683259;
        public long hash;
        public ArrayList<TLRPC.Document> ringtones;

        public TL_savedRingtones() {
            this.ringtones = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.hash = r3.readInt64(r4);
            this.ringtones = Vector.deserialize(r3, new ExportRequests$InvokeWithTakeoutWrapper$$ExternalSyntheticLambda2(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
            Vector.serialize(r3, this.ringtones);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_savedRingtonesNotModified extends SavedRingtones {
        public static final int constructor = -67704655;

        public TL_savedRingtonesNotModified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_themes extends Themes {
        public static final int constructor = -1707242387;
        public long hash;
        public ArrayList<TLRPC.TL_theme> themes;

        public TL_themes() {
            this.themes = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.hash = r3.readInt64(r4);
            this.themes = Vector.deserialize(r3, new TL_account$TL_themes$$ExternalSyntheticLambda0(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
            Vector.serialize(r3, this.themes);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_themesNotModified extends Themes {
        public static final int constructor = -199313886;

        public TL_themesNotModified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_wallPapers extends WallPapers {
        public static final int constructor = -842824308;
        public long hash;
        public ArrayList<TLRPC.WallPaper> wallpapers;

        public TL_wallPapers() {
            this.wallpapers = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.hash = r3.readInt64(r4);
            this.wallpapers = Vector.deserialize(r3, new TL_account$TL_wallPapers$$ExternalSyntheticLambda0(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
            Vector.serialize(r3, this.wallpapers);
        }
    }

    /* loaded from: classes6.dex */
    public static class TL_wallPapersNotModified extends WallPapers {
        public static final int constructor = 471437699;

        public TL_wallPapersNotModified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_webBrowserSettings extends WebBrowserSettings {
        public static final int constructor = 2045480115;
        public boolean display_close_button;
        public ArrayList<WebDomainException> external_exceptions;
        public int flags;
        public long hash;
        public ArrayList<WebDomainException> inapp_exceptions;
        public boolean open_external_browser;

        public TL_webBrowserSettings() {
            this.external_exceptions = new ArrayList();
            this.inapp_exceptions = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.open_external_browser = TLObject.hasFlag(r0, 1);
            this.display_close_button = TLObject.hasFlag(this.flags, 2);
            this.external_exceptions = Vector.deserialize(r3, new TL_account$TL_webBrowserSettings$$ExternalSyntheticLambda0(), r4);
            this.inapp_exceptions = Vector.deserialize(r3, new TL_account$TL_webBrowserSettings$$ExternalSyntheticLambda0(), r4);
            this.hash = r3.readInt64(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.open_external_browser);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.display_close_button);
            this.flags = r02;
            r4.writeInt32(r02);
            Vector.serialize(r4, this.external_exceptions);
            Vector.serialize(r4, this.inapp_exceptions);
            r4.writeInt64(this.hash);
        }
    }

    public static class TL_webBrowserSettingsNotModified extends WebBrowserSettings {
        public static final int constructor = -1021538482;

        public TL_webBrowserSettingsNotModified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class Themes extends TLObject {
        public Themes() {
        }

        public static Themes TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1707242387)) goto L5;
            TLObject r0 = new TL_themes();
        L10:
            return (Themes) TLObject.TLdeserialize(Themes.class, r0, r2, r3, r4);
        L5:
            if (r3 == (-199313886)) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_themesNotModified();
            goto L10
        }
    }

    /* loaded from: classes6.dex */
    public static class Tl_chatThemes extends ChatThemes {
        public static final int constructor = -1106673293;
        public ArrayList<TLRPC.Chat> chats;
        public int flags;
        public long hash;
        public String next_offset;
        public ArrayList<TLRPC.ChatTheme> themes;
        public ArrayList<TLRPC.User> users;

        public Tl_chatThemes() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.hash = r3.readInt64(r4);
            this.themes = Vector.deserialize(r3, new TL_account$Tl_chatThemes$$ExternalSyntheticLambda0(), r4);
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.next_offset = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeInt64(this.hash);
            Vector.serialize(r3, this.themes);
            Vector.serialize(r3, this.chats);
            Vector.serialize(r3, this.users);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.next_offset);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class Tl_getUniqueGiftChatThemes extends TLMethod<ChatThemes> {
        public static final int constructor = -466818615;
        public long hash;
        public int limit;
        public String offset;

        public Tl_getUniqueGiftChatThemes() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public ChatThemes deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return ChatThemes.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeString(this.offset);
            r3.writeInt32(this.limit);
            r3.writeInt64(this.hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class WallPapers extends TLObject {
        public WallPapers() {
        }

        public static WallPapers TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-842824308)) goto L5;
            TLObject r0 = new TL_wallPapers();
        L10:
            return (WallPapers) TLObject.TLdeserialize(WallPapers.class, r0, r2, r3, r4);
        L5:
            if (r3 == 471437699) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_wallPapersNotModified();
            goto L10
        }
    }

    public static abstract class WebBrowserSettings extends TLObject {
        public WebBrowserSettings() {
        }

        public static WebBrowserSettings TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (WebBrowserSettings) TLObject.TLdeserialize(WebBrowserSettings.class, fromConstructor(r3), r2, r3, r4);
        }

        private static WebBrowserSettings fromConstructor(int r1) {
            if (r1 == (-1021538482)) goto L11;
            if (r1 == 2045480115) goto L9;
            return null;
        L9:
            return new TL_webBrowserSettings();
        L11:
            return new TL_webBrowserSettingsNotModified();
        }
    }

    public static class WebDomainException extends TLObject {
        public static final int constructor = -1824741993;
        public String domain;
        public long favicon;
        public int flags;
        public String title;
        public String url;

        public WebDomainException() {
        }

        public static WebDomainException TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-1824741993)) goto L5;
            WebDomainException r0 = null;
        L7:
            return (WebDomainException) TLObject.TLdeserialize(WebDomainException.class, r0, r2, r3, r4);
        L5:
            r0 = new WebDomainException();
            goto L7
        }

        public static boolean equalsByDomain(WebDomainException r0, WebDomainException r1) {
            if (r0 != r1) goto L5;
            return true;
        L5:
            if (r0 == null) goto L10;
            if (r1 != null) goto L9;
            return false;
        L9:
            return TextUtils.equals(r0.domain.toLowerCase(), r1.domain.toLowerCase());
        L10:
            return false;
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.domain = r3.readString(r4);
            this.url = r3.readString(r4);
            this.title = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.favicon = r3.readInt64(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeString(this.domain);
            r3.writeString(this.url);
            r3.writeString(this.title);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt64(this.favicon);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class acceptAuthorization extends TLObject {
        public static final int constructor = -202552205;
        public long bot_id;
        public TLRPC.TL_secureCredentialsEncrypted credentials;
        public String public_key;
        public String scope;
        public ArrayList<TLRPC.TL_secureValueHash> value_hashes;

        public acceptAuthorization() {
            this.value_hashes = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.bot_id);
            r3.writeString(this.scope);
            r3.writeString(this.public_key);
            Vector.serialize(r3, this.value_hashes);
            this.credentials.serializeToStream(r3);
        }
    }

    /* loaded from: classes6.dex */
    public static class authorizationForm extends TLObject {
        public static final int constructor = -1389486888;
        public ArrayList<TLRPC.SecureValueError> errors;
        public int flags;
        public String privacy_policy_url;
        public ArrayList<TLRPC.SecureRequiredType> required_types;
        public ArrayList<TLRPC.User> users;
        public ArrayList<TLRPC.TL_secureValue> values;

        public authorizationForm() {
            this.required_types = new ArrayList();
            this.values = new ArrayList();
            this.errors = new ArrayList();
            this.users = new ArrayList();
        }

        public static authorizationForm TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1389486888) == r3) goto L5;
            authorizationForm r0 = null;
        L7:
            return (authorizationForm) TLObject.TLdeserialize(authorizationForm.class, r0, r2, r3, r4);
        L5:
            r0 = new authorizationForm();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.required_types = Vector.deserialize(r3, new TLRPC$TL_secureRequiredTypeOneOf$$ExternalSyntheticLambda0(), r4);
            this.values = Vector.deserialize(r3, new TL_account$authorizationForm$$ExternalSyntheticLambda0(), r4);
            this.errors = Vector.deserialize(r3, new TL_account$authorizationForm$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.privacy_policy_url = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            Vector.serialize(r3, this.required_types);
            Vector.serialize(r3, this.values);
            Vector.serialize(r3, this.errors);
            Vector.serialize(r3, this.users);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.privacy_policy_url);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class authorizations extends TLObject {
        public static final int constructor = 1275039392;
        public int authorization_ttl_days;
        public ArrayList<TLRPC.TL_authorization> authorizations;

        public authorizations() {
            this.authorizations = new ArrayList();
        }

        public static authorizations TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1275039392 == r3) goto L5;
            authorizations r0 = null;
        L7:
            return (authorizations) TLObject.TLdeserialize(authorizations.class, r0, r2, r3, r4);
        L5:
            r0 = new authorizations();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.authorization_ttl_days = r2.readInt32(r3);
            this.authorizations = Vector.deserialize(r2, new TL_account$authorizations$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.authorization_ttl_days);
            Vector.serialize(r2, this.authorizations);
        }
    }

    /* loaded from: classes6.dex */
    public static class autoDownloadSettings extends TLObject {
        public static final int constructor = 1674235686;
        public TLRPC.TL_autoDownloadSettings high;
        public TLRPC.TL_autoDownloadSettings low;
        public TLRPC.TL_autoDownloadSettings medium;

        public autoDownloadSettings() {
        }

        public static autoDownloadSettings TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1674235686 == r3) goto L5;
            autoDownloadSettings r0 = null;
        L7:
            return (autoDownloadSettings) TLObject.TLdeserialize(autoDownloadSettings.class, r0, r2, r3, r4);
        L5:
            r0 = new autoDownloadSettings();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.low = TLRPC.TL_autoDownloadSettings.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.medium = TLRPC.TL_autoDownloadSettings.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.high = TLRPC.TL_autoDownloadSettings.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.low.serializeToStream(r2);
            this.medium.serializeToStream(r2);
            this.high.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class businessChatLinks extends TLObject {
        public static final int constructor = -331111727;
        public ArrayList<TLRPC.Chat> chats;
        public ArrayList<TL_businessChatLink> links;
        public ArrayList<TLRPC.User> users;

        public businessChatLinks() {
            this.links = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static businessChatLinks TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-331111727) == r3) goto L5;
            businessChatLinks r0 = null;
        L7:
            return (businessChatLinks) TLObject.TLdeserialize(businessChatLinks.class, r0, r2, r3, r4);
        L5:
            r0 = new businessChatLinks();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.links = Vector.deserialize(r2, new TL_account$businessChatLinks$$ExternalSyntheticLambda0(), r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.links);
            Vector.serialize(r2, this.chats);
            Vector.serialize(r2, this.users);
        }
    }

    /* loaded from: classes6.dex */
    public static class cancelPasswordEmail extends TLObject {
        public static final int constructor = -1043606090;

        public cancelPasswordEmail() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class changeAuthorizationSettings extends TLObject {
        public static final int constructor = 1089766498;
        public boolean call_requests_disabled;
        public boolean confirmed;
        public boolean encrypted_requests_disabled;
        public int flags;
        public long hash;

        public changeAuthorizationSettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 8, this.confirmed);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.hash);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeBool(this.encrypted_requests_disabled);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeBool(this.call_requests_disabled);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class changePhone extends TLObject {
        public static final int constructor = 1891839707;
        public String phone_code;
        public String phone_code_hash;
        public String phone_number;

        public changePhone() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.User.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.phone_number);
            r2.writeString(this.phone_code_hash);
            r2.writeString(this.phone_code);
        }
    }

    /* loaded from: classes6.dex */
    public static class checkUsername extends TLObject {
        public static final int constructor = 655677548;
        public String username;

        public checkUsername() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.username);
        }
    }

    /* loaded from: classes6.dex */
    public static class clearRecentEmojiStatuses extends TLObject {
        public static final int constructor = 404757166;

        public clearRecentEmojiStatuses() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class confirmBotConnection extends TLMethod<TLRPC.Bool> {
        public static final int constructor = 1743593320;
        public TLRPC.InputUser bot_id;

        public confirmBotConnection() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.Bool deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            this.bot_id.serializeToStream(r1);
        }
    }

    /* loaded from: classes6.dex */
    public static class confirmPasswordEmail extends TLObject {
        public static final int constructor = -1881204448;
        public String code;

        public confirmPasswordEmail() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.code);
        }
    }

    /* loaded from: classes6.dex */
    public static class confirmPhone extends TLObject {
        public static final int constructor = 1596029123;
        public String phone_code;
        public String phone_code_hash;

        public confirmPhone() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.phone_code_hash);
            r2.writeString(this.phone_code);
        }
    }

    /* loaded from: classes6.dex */
    public static class connectedBots extends TLObject {
        public static final int constructor = 400029819;
        public ArrayList<TL_connectedBot> connected_bots;
        public ArrayList<TLRPC.User> users;

        public connectedBots() {
            this.connected_bots = new ArrayList();
            this.users = new ArrayList();
        }

        public static connectedBots TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 400029819) goto L5;
            connectedBots r0 = null;
        L7:
            return (connectedBots) TLObject.TLdeserialize(connectedBots.class, r0, r2, r3, r4);
        L5:
            r0 = new connectedBots();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.connected_bots = Vector.deserialize(r2, new TL_account$connectedBots$$ExternalSyntheticLambda0(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.connected_bots);
            Vector.serialize(r2, this.users);
        }
    }

    /* loaded from: classes6.dex */
    public static class contactBirthdays extends TLObject {
        public static final int constructor = 290452237;
        public ArrayList<TL_contactBirthday> contacts;
        public ArrayList<TLRPC.User> users;

        public contactBirthdays() {
            this.contacts = new ArrayList();
            this.users = new ArrayList();
        }

        public static contactBirthdays TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 290452237) goto L5;
            contactBirthdays r0 = null;
        L7:
            return (contactBirthdays) TLObject.TLdeserialize(contactBirthdays.class, r0, r2, r3, r4);
        L5:
            r0 = new contactBirthdays();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.contacts = Vector.deserialize(r2, new TL_account$contactBirthdays$$ExternalSyntheticLambda0(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(290452237);
            Vector.serialize(r2, this.contacts);
            Vector.serialize(r2, this.users);
        }
    }

    public static class contentSettings extends TLObject {
        public static final int constructor = 1474462241;
        public int flags;
        public boolean sensitive_can_change;
        public boolean sensitive_enabled;

        public contentSettings() {
        }

        public static contentSettings TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1474462241 == r3) goto L5;
            contentSettings r0 = null;
        L7:
            return (contentSettings) TLObject.TLdeserialize(contentSettings.class, r0, r2, r3, r4);
        L5:
            r0 = new contentSettings();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            int r12 = r1.readInt32(r2);
            this.flags = r12;
            this.sensitive_enabled = TLObject.hasFlag(r12, 1);
            this.sensitive_can_change = TLObject.hasFlag(this.flags, 2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.sensitive_enabled);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.sensitive_can_change);
            this.flags = r02;
            r4.writeInt32(r02);
        }
    }

    /* loaded from: classes6.dex */
    public static class createBusinessChatLink extends TLObject {
        public static final int constructor = -2007898482;
        public TL_inputBusinessChatLink link;

        public createBusinessChatLink() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_businessChatLink.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.link.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class createTheme extends TLObject {
        public static final int constructor = -2077048289;
        public TLRPC.InputDocument document;
        public int flags;
        public TLRPC.TL_inputThemeSettings settings;
        public String slug;
        public String title;

        public createTheme() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Theme.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeString(this.slug);
            r3.writeString(this.title);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L6;
            this.document.serializeToStream(r3);
        L6:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L9;
            this.settings.serializeToStream(r3);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class declinePasswordReset extends TLObject {
        public static final int constructor = 1284770294;

        public declinePasswordReset() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class deleteAccount extends TLObject {
        public static final int constructor = 1099779595;
        public String reason;

        public deleteAccount() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.reason);
        }
    }

    /* loaded from: classes6.dex */
    public static class deleteBusinessChatLink extends TLObject {
        public static final int constructor = 1611085428;
        public String slug;

        public deleteBusinessChatLink() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.slug);
        }
    }

    /* loaded from: classes6.dex */
    public static class deletePasskey extends TLMethod<TLRPC.Bool> {
        public static final int constructor = -172665281;
        public String id;

        public deletePasskey() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.Bool deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.id);
        }
    }

    /* loaded from: classes6.dex */
    public static class deleteSecureValue extends TLObject {
        public static final int constructor = -1199522741;
        public ArrayList<TLRPC.SecureValueType> types;

        public deleteSecureValue() {
            this.types = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.types);
        }
    }

    /* loaded from: classes6.dex */
    public static class deleteWebBrowserSettingsExceptions extends TLMethod<WebBrowserSettings> {
        public static final int constructor = -2036304291;

        public deleteWebBrowserSettingsExceptions() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public WebBrowserSettings deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return WebBrowserSettings.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class disablePeerConnectedBot extends TLObject {
        public static final int constructor = 1581481689;
        public TLRPC.InputPeer peer;

        public disablePeerConnectedBot() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class editBusinessChatLink extends TLObject {
        public static final int constructor = -1942744913;
        public TL_inputBusinessChatLink link;
        public String slug;

        public editBusinessChatLink() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_businessChatLink.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.slug);
            this.link.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class finishPasskeyLogin extends TLMethod<TLRPC.auth_Authorization> {
        public static final int constructor = -1739084537;
        public inputPasskeyCredentialPublicKey credential;
        public int flags;
        public long from_auth_key_id;
        public int from_dc_id;

        public finishPasskeyLogin() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.auth_Authorization deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.auth_Authorization.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.credential.serializeToStream(r3);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt32(this.from_dc_id);
        L6:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            r3.writeInt64(this.from_auth_key_id);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class getAccountTTL extends TLObject {
        public static final int constructor = 150761757;

        public getAccountTTL() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.TL_accountDaysTTL.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class getAllSecureValues extends TLObject {
        public static final int constructor = -1299661699;

        public getAllSecureValues() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Vector.TLDeserialize(r1, r2, r3, new TL_account$authorizationForm$$ExternalSyntheticLambda0());
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class getAuthorizationForm extends TLObject {
        public static final int constructor = -1456907910;
        public long bot_id;
        public String public_key;
        public String scope;

        public getAuthorizationForm() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return authorizationForm.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.bot_id);
            r3.writeString(this.scope);
            r3.writeString(this.public_key);
        }
    }

    /* loaded from: classes6.dex */
    public static class getAuthorizations extends TLObject {
        public static final int constructor = -484392616;

        public getAuthorizations() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return authorizations.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class getAutoDownloadSettings extends TLObject {
        public static final int constructor = 1457130303;

        public getAutoDownloadSettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return autoDownloadSettings.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class getBirthdays extends TLObject {
        public static final int constructor = -621959068;
        public TL_birthday birthday;
        public int flags;

        public getBirthdays() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return contactBirthdays.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class getBusinessChatLinks extends TLObject {
        public static final int constructor = 1869667809;

        public getBusinessChatLinks() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return businessChatLinks.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class getChannelDefaultEmojiStatuses extends TLObject {
        public static final int constructor = 1999087573;
        public long hash;

        public getChannelDefaultEmojiStatuses() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return EmojiStatuses.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class getChannelRestrictedStatusEmojis extends TLObject {
        public static final int constructor = 900325589;
        public long hash;

        public getChannelRestrictedStatusEmojis() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.EmojiList.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class getChatThemes extends TLMethod<Themes> {
        public static final int constructor = -700916087;
        public long hash;

        public getChatThemes() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public Themes deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return Themes.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class getConnectedBots extends TLObject {
        public static final int constructor = 1319421967;

        public getConnectedBots() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return connectedBots.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class getContactSignUpNotification extends TLObject {
        public static final int constructor = -1626880216;

        public getContactSignUpNotification() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class getContentSettings extends TLObject {
        public static final int constructor = -1952756306;

        public getContentSettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return contentSettings.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class getDefaultBackgroundEmojis extends TLObject {
        public static final int constructor = -1509246514;
        public long hash;

        public getDefaultBackgroundEmojis() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.EmojiList.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class getDefaultEmojiStatuses extends TLObject {
        public static final int constructor = -696962170;
        public long hash;

        public getDefaultEmojiStatuses() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return EmojiStatuses.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class getDefaultGroupPhotoEmojis extends TLObject {
        public static final int constructor = -1856479058;
        public long hash;

        public getDefaultGroupPhotoEmojis() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.EmojiList.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class getDefaultProfilePhotoEmojis extends TLObject {
        public static final int constructor = -495647960;
        public long hash;

        public getDefaultProfilePhotoEmojis() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.EmojiList.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    public static class getGlobalPrivacySettings extends TLObject {
        public static final int constructor = -349483786;

        public getGlobalPrivacySettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.GlobalPrivacySettings.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class getMultiWallPapers extends TLObject {
        public static final int constructor = 1705865692;
        public ArrayList<TLRPC.InputWallPaper> wallpapers;

        public getMultiWallPapers() {
            this.wallpapers = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Vector.TLDeserialize(r1, r2, r3, new TL_account$TL_wallPapers$$ExternalSyntheticLambda0());
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.wallpapers);
        }
    }

    /* loaded from: classes6.dex */
    public static class getNotifyExceptions extends TLObject {
        public static final int constructor = 1398240377;
        public boolean compare_sound;
        public int flags;
        public TLRPC.InputNotifyPeer peer;

        public getNotifyExceptions() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.compare_sound);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.peer.serializeToStream(r4);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class getNotifySettings extends TLObject {
        public static final int constructor = 313765169;
        public TLRPC.InputNotifyPeer peer;

        public getNotifySettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.PeerNotifySettings.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class getPaidMessagesRevenue extends TLObject {
        public static final int constructor = 431639143;
        public int flags;
        public TLRPC.InputPeer parent_peer;
        public TLRPC.InputUser user_id;

        public getPaidMessagesRevenue() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return paidMessagesRevenue.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.parent_peer == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            this.parent_peer.serializeToStream(r4);
        L9:
            this.user_id.serializeToStream(r4);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class getPasskeys extends TLMethod<Passkeys> {
        public static final int constructor = -367063982;

        public getPasskeys() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public Passkeys deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return Passkeys.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class getPassword extends TLObject {
        public static final int constructor = 1418342645;

        public getPassword() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Password.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class getPasswordSettings extends TLObject {
        public static final int constructor = -1663767815;
        public TLRPC.InputCheckPasswordSRP password;

        public getPasswordSettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return passwordSettings.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.password.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class getPrivacy extends TLObject {
        public static final int constructor = -623130288;
        public TLRPC.InputPrivacyKey key;

        public getPrivacy() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return privacyRules.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.key.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class getReactionsNotifySettings extends TLObject {
        public static final int constructor = 115172684;

        public getReactionsNotifySettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_reactionsNotifySettings.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class getRecentEmojiStatuses extends TLObject {
        public static final int constructor = 257392901;
        public long hash;

        public getRecentEmojiStatuses() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return EmojiStatuses.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class getRequirementsToContact extends TLObject {
        public static final int constructor = -660962397;
        public ArrayList<TLRPC.InputUser> id;

        public getRequirementsToContact() {
            this.id = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Vector.TLDeserialize(r1, r2, r3, new TL_account$getRequirementsToContact$$ExternalSyntheticLambda0());
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.id = Vector.deserialize(r2, new TLRPC$TL_inputPrivacyValueAllowUsers$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.id);
        }
    }

    /* loaded from: classes6.dex */
    public static class getSavedMusicByID extends TLObject {
        public static final int constructor = 1970513129;
        public ArrayList<TLRPC.InputDocument> documents;
        public TLRPC.InputUser id;

        public getSavedMusicByID() {
            this.documents = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.SavedMusic.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.id.serializeToStream(r2);
            Vector.serialize(r2, this.documents);
        }
    }

    /* loaded from: classes6.dex */
    public static class getSavedMusicIds extends TLObject {
        public static final int constructor = -526557265;
        public long hash;

        public getSavedMusicIds() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return SavedMusicIds.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    public static class getSavedRingtones extends TLObject {
        public static final int constructor = -510647672;
        public long hash;

        public getSavedRingtones() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return SavedRingtones.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class getSecureValue extends TLObject {
        public static final int constructor = 1936088002;
        public ArrayList<TLRPC.SecureValueType> types;

        public getSecureValue() {
            this.types = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Vector.TLDeserialize(r1, r2, r3, new TL_account$authorizationForm$$ExternalSyntheticLambda0());
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.types);
        }
    }

    /* loaded from: classes6.dex */
    public static class getTheme extends TLObject {
        public static final int constructor = -1919060949;
        public long document_id;
        public String format;
        public TLRPC.InputTheme theme;

        public getTheme() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Theme.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeString(this.format);
            this.theme.serializeToStream(r3);
            r3.writeInt64(this.document_id);
        }
    }

    /* loaded from: classes6.dex */
    public static class getThemes extends TLObject {
        public static final int constructor = 1913054296;
        public String format;
        public long hash;

        public getThemes() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Themes.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeString(this.format);
            r3.writeInt64(this.hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class getTmpPassword extends TLObject {
        public static final int constructor = 1151208273;
        public TLRPC.InputCheckPasswordSRP password;
        public int period;

        public getTmpPassword() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return tmpPassword.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.password.serializeToStream(r2);
            r2.writeInt32(this.period);
        }
    }

    /* loaded from: classes6.dex */
    public static class getWallPaper extends TLObject {
        public static final int constructor = -57811990;
        public TLRPC.InputWallPaper wallpaper;

        public getWallPaper() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.WallPaper.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.wallpaper.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class getWallPapers extends TLObject {
        public static final int constructor = 127302966;
        public long hash;

        public getWallPapers() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return WallPapers.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class getWebAuthorizations extends TLObject {
        public static final int constructor = 405695855;

        public getWebAuthorizations() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return webAuthorizations.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class getWebBrowserSettings extends TLMethod<WebBrowserSettings> {
        public static final int constructor = 1449482088;
        public long hash;

        public getWebBrowserSettings() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public WebBrowserSettings deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return WebBrowserSettings.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class getWebPagePreview extends TLMethod<webPagePreview> {
        public static final int constructor = 1460498287;
        public ArrayList<TLRPC.MessageEntity> entities;
        public int flags;
        public String message;

        public getWebPagePreview() {
            this.entities = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public webPagePreview deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return webPagePreview.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L6;
            Vector.serialize(r3, this.entities);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class initPasskeyLogin extends TLMethod<passkeyLoginOptions> {
        public static final int constructor = 1368051895;
        public String api_hash;
        public int api_id;

        public initPasskeyLogin() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public passkeyLoginOptions deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return passkeyLoginOptions.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.api_id);
            r2.writeString(this.api_hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class initPasskeyRegistration extends TLMethod<passkeyRegistrationOptions> {
        public static final int constructor = 1117079528;

        public initPasskeyRegistration() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public passkeyRegistrationOptions deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return passkeyRegistrationOptions.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class inputPasskeyCredentialPublicKey extends TLObject {
        public static final int constructor = 1009235855;
        public String id;
        public String raw_id;
        public InputPasskeyResponse response;

        public inputPasskeyCredentialPublicKey() {
        }

        public static inputPasskeyCredentialPublicKey TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 1009235855) goto L5;
            inputPasskeyCredentialPublicKey r0 = null;
        L7:
            return (inputPasskeyCredentialPublicKey) TLObject.TLdeserialize(inputPasskeyCredentialPublicKey.class, r0, r2, r3, r4);
        L5:
            r0 = new inputPasskeyCredentialPublicKey();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.id = r2.readString(r3);
            this.raw_id = r2.readString(r3);
            this.response = InputPasskeyResponse.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.id);
            r2.writeString(this.raw_id);
            this.response.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class inputPasskeyResponseLogin extends InputPasskeyResponse {
        public static final int constructor = -1021329078;
        public byte[] authenticator_data;
        public TLRPC.TL_dataJSON client_data;
        public byte[] signature;
        public String user_handle;

        public inputPasskeyResponseLogin() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.client_data = TLRPC.TL_dataJSON.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.authenticator_data = r2.readByteArray(r3);
            this.signature = r2.readByteArray(r3);
            this.user_handle = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.client_data.serializeToStream(r2);
            r2.writeByteArray(this.authenticator_data);
            r2.writeByteArray(this.signature);
            r2.writeString(this.user_handle);
        }
    }

    /* loaded from: classes6.dex */
    public static class inputPasskeyResponseRegister extends InputPasskeyResponse {
        public static final int constructor = 1046713180;
        public byte[] attestation_object;
        public TLRPC.TL_dataJSON client_data;

        public inputPasskeyResponseRegister() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.client_data = TLRPC.TL_dataJSON.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.attestation_object = r2.readByteArray(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.client_data.serializeToStream(r2);
            r2.writeByteArray(this.attestation_object);
        }
    }

    /* loaded from: classes6.dex */
    public static class installTheme extends TLObject {
        public static final int constructor = 2061776695;
        public boolean dark;
        public int flags;
        public String format;
        public TLRPC.InputTheme theme;

        public installTheme() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.dark);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            r4.writeString(this.format);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.theme.serializeToStream(r4);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class installWallPaper extends TLObject {
        public static final int constructor = -18000023;
        public TLRPC.TL_wallPaperSettings settings;
        public TLRPC.InputWallPaper wallpaper;

        public installWallPaper() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.wallpaper.serializeToStream(r2);
            this.settings.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class paidMessagesRevenue extends TLObject {
        public static final int constructor = 504403720;
        public long stars_amount;

        public paidMessagesRevenue() {
        }

        public static paidMessagesRevenue TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 504403720) goto L5;
            paidMessagesRevenue r0 = null;
        L7:
            return (paidMessagesRevenue) TLObject.TLdeserialize(paidMessagesRevenue.class, r0, r2, r3, r4);
        L5:
            r0 = new paidMessagesRevenue();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.stars_amount = r1.readInt64(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.stars_amount);
        }
    }

    /* loaded from: classes6.dex */
    public static class passkeyLoginOptions extends TLObject {
        public static final int constructor = -503089271;
        public TLRPC.TL_dataJSON options;

        public passkeyLoginOptions() {
        }

        public static passkeyLoginOptions TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-503089271)) goto L5;
            passkeyLoginOptions r0 = null;
        L7:
            return (passkeyLoginOptions) TLObject.TLdeserialize(passkeyLoginOptions.class, r0, r2, r3, r4);
        L5:
            r0 = new passkeyLoginOptions();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.options = TLRPC.TL_dataJSON.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.options.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class passkeyRegistrationOptions extends TLObject {
        public static final int constructor = -513057567;
        public TLRPC.TL_dataJSON options;

        public passkeyRegistrationOptions() {
        }

        public static passkeyRegistrationOptions TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == (-513057567)) goto L5;
            passkeyRegistrationOptions r0 = null;
        L7:
            return (passkeyRegistrationOptions) TLObject.TLdeserialize(passkeyRegistrationOptions.class, r0, r2, r3, r4);
        L5:
            r0 = new passkeyRegistrationOptions();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.options = TLRPC.TL_dataJSON.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.options.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class passwordInputSettings extends TLObject {
        public static final int constructor = -1036572727;
        public String email;
        public int flags;
        public String hint;
        public TLRPC.PasswordKdfAlgo new_algo;
        public byte[] new_password_hash;
        public TLRPC.TL_secureSecretSettings new_secure_settings;

        public passwordInputSettings() {
        }

        public static passwordInputSettings TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1036572727) == r3) goto L5;
            passwordInputSettings r0 = null;
        L7:
            return (passwordInputSettings) TLObject.TLdeserialize(passwordInputSettings.class, r0, r2, r3, r4);
        L5:
            r0 = new passwordInputSettings();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1) == false) goto L6;
            this.new_algo = TLRPC.PasswordKdfAlgo.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            this.new_password_hash = r3.readByteArray(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            this.hint = r3.readString(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L15;
            this.email = r3.readString(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            this.new_secure_settings = TLRPC.TL_secureSecretSettings.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.new_algo.serializeToStream(r3);
        L6:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            r3.writeByteArray(this.new_password_hash);
        L9:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            r3.writeString(this.hint);
        L12:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L15;
            r3.writeString(this.email);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            this.new_secure_settings.serializeToStream(r3);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class passwordSettings extends TLObject {
        public static final int constructor = -1705233435;
        public String email;
        public int flags;
        public TLRPC.TL_secureSecretSettings secure_settings;

        public passwordSettings() {
        }

        public static passwordSettings TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1705233435) == r3) goto L5;
            passwordSettings r0 = null;
        L7:
            return (passwordSettings) TLObject.TLdeserialize(passwordSettings.class, r0, r2, r3, r4);
        L5:
            r0 = new passwordSettings();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1) == false) goto L6;
            this.email = r3.readString(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.secure_settings = TLRPC.TL_secureSecretSettings.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.email);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.secure_settings.serializeToStream(r3);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class privacyRules extends TLObject {
        public static final int constructor = 1352683077;
        public ArrayList<TLRPC.Chat> chats;
        public ArrayList<TLRPC.PrivacyRule> rules;
        public ArrayList<TLRPC.User> users;

        public privacyRules() {
            this.rules = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static privacyRules TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1352683077 == r3) goto L5;
            privacyRules r0 = null;
        L7:
            return (privacyRules) TLObject.TLdeserialize(privacyRules.class, r0, r2, r3, r4);
        L5:
            r0 = new privacyRules();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.rules = Vector.deserialize(r2, new TL_account$privacyRules$$ExternalSyntheticLambda0(), r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.rules);
            Vector.serialize(r2, this.chats);
            Vector.serialize(r2, this.users);
        }
    }

    public static class registerDevice extends TLObject {
        public static final int constructor = -326762118;
        public boolean app_sandbox;
        public int flags;
        public boolean no_muted;
        public ArrayList<Long> other_uids;
        public byte[] secret;
        public String token;
        public int token_type;

        public registerDevice() {
            this.other_uids = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.no_muted);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt32(this.token_type);
            r4.writeString(this.token);
            r4.writeBool(this.app_sandbox);
            r4.writeByteArray(this.secret);
            Vector.serializeLong(r4, this.other_uids);
        }
    }

    /* loaded from: classes6.dex */
    public static class registerPasskey extends TLMethod<Passkey> {
        public static final int constructor = 1437867990;
        public inputPasskeyCredentialPublicKey credential;

        public registerPasskey() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public Passkey deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return Passkey.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.credential.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class reorderUsernames extends TLObject {
        public static final int constructor = -279966037;
        public ArrayList<String> order;

        public reorderUsernames() {
            this.order = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serializeString(r2, this.order);
        }
    }

    /* loaded from: classes6.dex */
    public static class reportPeer extends TLObject {
        public static final int constructor = -977650298;
        public String message;
        public TLRPC.InputPeer peer;
        public TLRPC.ReportReason reason;

        public reportPeer() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            this.reason.serializeToStream(r2);
            r2.writeString(this.message);
        }
    }

    /* loaded from: classes6.dex */
    public static class reportProfilePhoto extends TLObject {
        public static final int constructor = -91437323;
        public String message;
        public TLRPC.InputPeer peer;
        public TLRPC.InputPhoto photo_id;
        public TLRPC.ReportReason reason;

        public reportProfilePhoto() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            this.photo_id.serializeToStream(r2);
            this.reason.serializeToStream(r2);
            r2.writeString(this.message);
        }
    }

    /* loaded from: classes6.dex */
    public static class requirementToContactEmpty extends RequirementToContact {
        public static final int constructor = 84580409;

        public requirementToContactEmpty() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class requirementToContactPaidMessages extends RequirementToContact {
        public static final int constructor = -1258914157;
        public long stars_amount;

        public requirementToContactPaidMessages() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.stars_amount = r1.readInt64(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.stars_amount);
        }
    }

    public static class requirementToContactPremium extends RequirementToContact {
        public static final int constructor = -444472087;

        public requirementToContactPremium() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class resendPasswordEmail extends TLObject {
        public static final int constructor = 2055154197;

        public resendPasswordEmail() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class resetAuthorization extends TLObject {
        public static final int constructor = -545786948;
        public long hash;

        public resetAuthorization() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class resetNotifySettings extends TLObject {
        public static final int constructor = -612493497;

        public resetNotifySettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class resetPassword extends TLObject {
        public static final int constructor = -1828139493;

        public resetPassword() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return ResetPasswordResult.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class resetPasswordFailedWait extends ResetPasswordResult {
        public static final int constructor = -478701471;
        public int retry_date;

        public resetPasswordFailedWait() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.retry_date = r1.readInt32(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.retry_date);
        }
    }

    /* loaded from: classes6.dex */
    public static class resetPasswordOk extends ResetPasswordResult {
        public static final int constructor = -383330754;

        public resetPasswordOk() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class resetPasswordRequestedWait extends ResetPasswordResult {
        public static final int constructor = -370148227;
        public int until_date;

        public resetPasswordRequestedWait() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.until_date = r1.readInt32(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.until_date);
        }
    }

    /* loaded from: classes6.dex */
    public static class resetWallPapers extends TLObject {
        public static final int constructor = -1153722364;

        public resetWallPapers() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class resetWebAuthorization extends TLObject {
        public static final int constructor = 755087855;
        public long hash;

        public resetWebAuthorization() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    /* loaded from: classes6.dex */
    public static class resetWebAuthorizations extends TLObject {
        public static final int constructor = 1747789204;

        public resetWebAuthorizations() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    /* loaded from: classes6.dex */
    public static class resolveBusinessChatLink extends TLObject {
        public static final int constructor = 1418913262;
        public String slug;

        public resolveBusinessChatLink() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return resolvedBusinessChatLinks.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.slug);
        }
    }

    /* loaded from: classes6.dex */
    public static class resolvedBusinessChatLinks extends TLObject {
        public static final int constructor = -1708937439;
        public ArrayList<TLRPC.Chat> chats;
        public ArrayList<TLRPC.MessageEntity> entities;
        public int flags;
        public String message;
        public TLRPC.Peer peer;
        public ArrayList<TLRPC.User> users;

        public resolvedBusinessChatLinks() {
            this.entities = new ArrayList();
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static resolvedBusinessChatLinks TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1708937439) == r3) goto L5;
            resolvedBusinessChatLinks r0 = null;
        L7:
            return (resolvedBusinessChatLinks) TLObject.TLdeserialize(resolvedBusinessChatLinks.class, r0, r2, r3, r4);
        L5:
            r0 = new resolvedBusinessChatLinks();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.peer = TLRPC.Peer.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.message = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.entities = Vector.deserialize(r3, new MessagesStorage$$ExternalSyntheticLambda117(), r4);
        L5:
            this.chats = Vector.deserialize(r3, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.peer.serializeToStream(r3);
            r3.writeString(this.message);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            Vector.serialize(r3, this.entities);
        L5:
            Vector.serialize(r3, this.chats);
            Vector.serialize(r3, this.users);
        }
    }

    /* loaded from: classes6.dex */
    public static class saveAutoDownloadSettings extends TLObject {
        public static final int constructor = 1995661875;
        public int flags;
        public boolean high;
        public boolean low;
        public TLRPC.TL_autoDownloadSettings settings;

        public saveAutoDownloadSettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.low);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.high);
            this.flags = r02;
            r4.writeInt32(r02);
            this.settings.serializeToStream(r4);
        }
    }

    /* loaded from: classes6.dex */
    public static class saveRingtone extends TLObject {
        public static final int constructor = 1038768899;
        public TLRPC.InputDocument id;
        public boolean unsave;

        public saveRingtone() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return SavedRingtone.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.id.serializeToStream(r2);
            r2.writeBool(this.unsave);
        }
    }

    /* loaded from: classes6.dex */
    public static class saveSecureValue extends TLObject {
        public static final int constructor = -1986010339;
        public long secure_secret_id;
        public TLRPC.TL_inputSecureValue value;

        public saveSecureValue() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.TL_secureValue.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            this.value.serializeToStream(r3);
            r3.writeInt64(this.secure_secret_id);
        }
    }

    /* loaded from: classes6.dex */
    public static class saveTheme extends TLObject {
        public static final int constructor = -229175188;
        public TLRPC.InputTheme theme;
        public boolean unsave;

        public saveTheme() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.theme.serializeToStream(r2);
            r2.writeBool(this.unsave);
        }
    }

    /* loaded from: classes6.dex */
    public static class saveWallPaper extends TLObject {
        public static final int constructor = 1817860919;
        public TLRPC.TL_wallPaperSettings settings;
        public boolean unsave;
        public TLRPC.InputWallPaper wallpaper;

        public saveWallPaper() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.wallpaper.serializeToStream(r2);
            r2.writeBool(this.unsave);
            this.settings.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class sendChangePhoneCode extends TLObject {
        public static final int constructor = -2108208411;
        public String phone_number;
        public TLRPC.TL_codeSettings settings;

        public sendChangePhoneCode() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.auth_SentCode.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.phone_number);
            this.settings.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class sendConfirmPhoneCode extends TLObject {
        public static final int constructor = 457157256;
        public String hash;
        public TLRPC.TL_codeSettings settings;

        public sendConfirmPhoneCode() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.auth_SentCode.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.hash);
            this.settings.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class sendVerifyEmailCode extends TLObject {
        public static final int constructor = -1730136133;
        public String email;
        public TLRPC.EmailVerifyPurpose purpose;

        public sendVerifyEmailCode() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return sentEmailCode.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.purpose.serializeToStream(r2);
            r2.writeString(this.email);
        }
    }

    /* loaded from: classes6.dex */
    public static class sendVerifyPhoneCode extends TLObject {
        public static final int constructor = -1516022023;
        public String phone_number;
        public TLRPC.TL_codeSettings settings;

        public sendVerifyPhoneCode() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.auth_SentCode.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.phone_number);
            this.settings.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class sentEmailCode extends TLObject {
        public static final int constructor = -2128640689;
        public String email_pattern;
        public int length;

        public sentEmailCode() {
        }

        public static sentEmailCode TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-2128640689) == r3) goto L5;
            sentEmailCode r0 = null;
        L7:
            return (sentEmailCode) TLObject.TLdeserialize(sentEmailCode.class, r0, r2, r3, r4);
        L5:
            r0 = new sentEmailCode();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.email_pattern = r2.readString(r3);
            this.length = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.email_pattern);
            r2.writeInt32(this.length);
        }
    }

    /* loaded from: classes6.dex */
    public static class setAccountTTL extends TLObject {
        public static final int constructor = 608323678;
        public TLRPC.TL_accountDaysTTL ttl;

        public setAccountTTL() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.ttl.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class setAuthorizationTTL extends TLObject {
        public static final int constructor = -1081501024;
        public int authorization_ttl_days;

        public setAuthorizationTTL() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.authorization_ttl_days);
        }
    }

    /* loaded from: classes6.dex */
    public static class setContactSignUpNotification extends TLObject {
        public static final int constructor = -806076575;
        public boolean silent;

        public setContactSignUpNotification() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeBool(this.silent);
        }
    }

    /* loaded from: classes6.dex */
    public static class setContentSettings extends TLObject {
        public static final int constructor = -1250643605;
        public int flags;
        public boolean sensitive_enabled;

        public setContentSettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.sensitive_enabled);
            this.flags = r0;
            r4.writeInt32(r0);
        }
    }

    /* loaded from: classes6.dex */
    public static class setGlobalPrivacySettings extends TLObject {
        public static final int constructor = 517647042;
        public TLRPC.GlobalPrivacySettings settings;

        public setGlobalPrivacySettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.GlobalPrivacySettings.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.settings.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class setPrivacy extends TLObject {
        public static final int constructor = -906486552;
        public TLRPC.InputPrivacyKey key;
        public ArrayList<TLRPC.InputPrivacyRule> rules;

        public setPrivacy() {
            this.rules = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return privacyRules.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.key.serializeToStream(r2);
            Vector.serialize(r2, this.rules);
        }
    }

    /* loaded from: classes6.dex */
    public static class setReactionsNotifySettings extends TLObject {
        public static final int constructor = 829220168;
        public TL_reactionsNotifySettings settings;

        public setReactionsNotifySettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_reactionsNotifySettings.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.settings.serializeToStream(r2);
        }
    }

    public static class tmpPassword extends TLObject {
        public static final int constructor = -614138572;
        public byte[] tmp_password;
        public int valid_until;

        public tmpPassword() {
        }

        public static tmpPassword TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-614138572) == r3) goto L5;
            tmpPassword r0 = null;
        L7:
            return (tmpPassword) TLObject.TLdeserialize(tmpPassword.class, r0, r2, r3, r4);
        L5:
            r0 = new tmpPassword();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.tmp_password = r2.readByteArray(r3);
            this.valid_until = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeByteArray(this.tmp_password);
            r2.writeInt32(this.valid_until);
        }
    }

    /* loaded from: classes6.dex */
    public static class toggleConnectedBotPaused extends TLObject {
        public static final int constructor = 1684934807;
        public boolean paused;
        public TLRPC.InputPeer peer;

        public toggleConnectedBotPaused() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            r2.writeBool(this.paused);
        }
    }

    /* loaded from: classes6.dex */
    public static class toggleNoPaidMessagesException extends TLObject {
        public static final int constructor = -30483850;
        public int flags;
        public TLRPC.InputPeer parent_peer;
        public boolean refund_charged;
        public boolean require_payment;
        public TLRPC.InputUser user_id;

        public toggleNoPaidMessagesException() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            boolean r2 = true;
            int r0 = TLObject.setFlag(this.flags, 1, this.refund_charged);
            this.flags = r0;
            if (this.parent_peer != null) goto L6;
            r2 = false;
        L6:
            int r02 = TLObject.setFlag(r0, 2, r2);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.require_payment);
            this.flags = r03;
            r5.writeInt32(r03);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.parent_peer.serializeToStream(r5);
        L9:
            this.user_id.serializeToStream(r5);
        }
    }

    /* loaded from: classes6.dex */
    public static class toggleSponsoredMessages extends TLObject {
        public static final int constructor = -1176919155;
        public boolean enabled;

        public toggleSponsoredMessages() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeBool(this.enabled);
        }
    }

    /* loaded from: classes6.dex */
    public static class toggleUsername extends TLObject {
        public static final int constructor = 1490465654;
        public boolean active;
        public String username;

        public toggleUsername() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.username);
            r2.writeBool(this.active);
        }
    }

    /* loaded from: classes6.dex */
    public static class toggleWebBrowserSettingsException extends TLMethod<TLRPC.Updates> {
        public static final int constructor = 1626161705;
        public boolean delete;
        public int flags;
        public boolean open_external_browser;
        public String url;

        public toggleWebBrowserSettingsException() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.Updates deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.delete);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r4.writeBool(this.open_external_browser);
        L5:
            r4.writeString(this.url);
        }
    }

    /* loaded from: classes6.dex */
    public static class unregisterDevice extends TLObject {
        public static final int constructor = 1779249670;
        public ArrayList<Long> other_uids;
        public String token;
        public int token_type;

        public unregisterDevice() {
            this.other_uids = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.token_type);
            r2.writeString(this.token);
            Vector.serializeLong(r2, this.other_uids);
        }
    }

    /* loaded from: classes6.dex */
    public static class updateBirthday extends TLObject {
        public static final int constructor = -865203183;
        public TL_birthday birthday;
        public int flags;

        public updateBirthday() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.birthday.serializeToStream(r3);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class updateBusinessAwayMessage extends TLObject {
        public static final int constructor = -1570078811;
        public int flags;
        public TL_inputBusinessAwayMessage message;

        public updateBusinessAwayMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.message.serializeToStream(r3);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class updateBusinessGreetingMessage extends TLObject {
        public static final int constructor = 1724755908;
        public int flags;
        public TL_inputBusinessGreetingMessage message;

        public updateBusinessGreetingMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.message.serializeToStream(r3);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class updateBusinessIntro extends TLObject {
        public static final int constructor = -1508585420;
        public int flags;
        public TL_inputBusinessIntro intro;

        public updateBusinessIntro() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.intro.serializeToStream(r3);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class updateBusinessLocation extends TLObject {
        public static final int constructor = -1637149926;
        public String address;
        public int flags;
        public TLRPC.InputGeoPoint geo_point;

        public updateBusinessLocation() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            this.geo_point.serializeToStream(r3);
        L6:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            r3.writeString(this.address);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class updateBusinessWorkHours extends TLObject {
        public static final int constructor = 1258348646;
        public TL_businessWorkHours business_work_hours;
        public int flags;

        public updateBusinessWorkHours() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.business_work_hours.serializeToStream(r3);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class updateColor extends TLObject {
        public static final int constructor = 1749885262;
        public TLRPC.PeerColor color;
        public int flags;
        public boolean for_profile;

        public updateColor() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.for_profile);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L6;
            this.color.serializeToStream(r4);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class updateConnectedBot extends TLObject {
        public static final int constructor = 1721797758;
        public TLRPC.InputUser bot;
        public boolean deleted;
        public int flags;
        public TL_inputBusinessBotRecipients recipients;
        public TL_businessBotRights rights;

        public updateConnectedBot() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.deleted);
            this.flags = r0;
            if (this.rights == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            this.rights.serializeToStream(r4);
        L9:
            this.bot.serializeToStream(r4);
            this.recipients.serializeToStream(r4);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class updateDeviceLocked extends TLObject {
        public static final int constructor = 954152242;
        public int period;

        public updateDeviceLocked() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.period);
        }
    }

    /* loaded from: classes6.dex */
    public static class updateEmojiStatus extends TLObject {
        public static final int constructor = -70001045;
        public TLRPC.EmojiStatus emoji_status;

        public updateEmojiStatus() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.emoji_status.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class updateNotifySettings extends TLObject {
        public static final int constructor = -2067899501;
        public TLRPC.InputNotifyPeer peer;
        public TLRPC.TL_inputPeerNotifySettings settings;

        public updateNotifySettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.peer.serializeToStream(r2);
            this.settings.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class updatePasswordSettings extends TLObject {
        public static final int constructor = -1516564433;
        public passwordInputSettings new_settings;
        public TLRPC.InputCheckPasswordSRP password;

        public updatePasswordSettings() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.password.serializeToStream(r2);
            this.new_settings.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class updatePersonalChannel extends TLObject {
        public static final int constructor = -649919008;
        public TLRPC.InputChannel channel;

        public updatePersonalChannel() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.channel.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class updateProfile extends TLObject {
        public static final int constructor = 2018596725;
        public String about;
        public String first_name;
        public int flags;
        public String last_name;

        public updateProfile() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.User.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.first_name);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeString(this.last_name);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            r3.writeString(this.about);
            return;
        }
    }

    public static class updateStatus extends TLObject {
        public static final int constructor = 1713919532;
        public boolean offline;

        public updateStatus() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeBool(this.offline);
        }
    }

    /* loaded from: classes6.dex */
    public static class updateTheme extends TLObject {
        public static final int constructor = 1555261397;
        public TLRPC.InputDocument document;
        public int flags;
        public String format;
        public TLRPC.TL_inputThemeSettings settings;
        public String slug;
        public TLRPC.InputTheme theme;
        public String title;

        public updateTheme() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Theme.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeString(this.format);
            this.theme.serializeToStream(r3);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeString(this.slug);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeString(this.title);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.document.serializeToStream(r3);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.settings.serializeToStream(r3);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class updateUsername extends TLObject {
        public static final int constructor = 1040964988;
        public String username;

        public updateUsername() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.User.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.username);
        }
    }

    /* loaded from: classes6.dex */
    public static class updateWebBrowserSettings extends TLMethod<WebBrowserSettings> {
        public static final int constructor = -1696627970;
        public boolean display_close_button;
        public int flags;
        public boolean open_external_browser;

        public updateWebBrowserSettings() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public WebBrowserSettings deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return WebBrowserSettings.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            int r12 = r1.readInt32(r2);
            this.flags = r12;
            this.open_external_browser = TLObject.hasFlag(r12, 1);
            this.display_close_button = TLObject.hasFlag(this.flags, 2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.open_external_browser);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.display_close_button);
            this.flags = r02;
            r4.writeInt32(r02);
        }
    }

    /* loaded from: classes6.dex */
    public static class uploadRingtone extends TLObject {
        public static final int constructor = -2095414366;
        public TLRPC.InputFile file;
        public String file_name;
        public String mime_type;

        public uploadRingtone() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Document.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.file.serializeToStream(r2);
            r2.writeString(this.file_name);
            r2.writeString(this.mime_type);
        }
    }

    /* loaded from: classes6.dex */
    public static class uploadTheme extends TLObject {
        public static final int constructor = 473805619;
        public TLRPC.InputFile file;
        public String file_name;
        public int flags;
        public String mime_type;
        public TLRPC.InputFile thumb;

        public uploadTheme() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Document.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.file.serializeToStream(r3);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.thumb.serializeToStream(r3);
        L5:
            r3.writeString(this.file_name);
            r3.writeString(this.mime_type);
        }
    }

    /* loaded from: classes6.dex */
    public static class uploadWallPaper extends TLObject {
        public static final int constructor = -578472351;
        public TLRPC.InputFile file;
        public String mime_type;
        public TLRPC.TL_wallPaperSettings settings;

        public uploadWallPaper() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.WallPaper.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.file.serializeToStream(r2);
            r2.writeString(this.mime_type);
            this.settings.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class verifyEmail extends TLObject {
        public static final int constructor = 53322959;
        public TLRPC.EmailVerifyPurpose purpose;
        public TLRPC.EmailVerification verification;

        public verifyEmail() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return EmailVerified.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.purpose.serializeToStream(r2);
            this.verification.serializeToStream(r2);
        }
    }

    /* loaded from: classes6.dex */
    public static class verifyPhone extends TLObject {
        public static final int constructor = 1305716726;
        public String phone_code;
        public String phone_code_hash;
        public String phone_number;

        public verifyPhone() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.phone_number);
            r2.writeString(this.phone_code_hash);
            r2.writeString(this.phone_code);
        }
    }

    /* loaded from: classes6.dex */
    public static class webAuthorizations extends TLObject {
        public static final int constructor = -313079300;
        public ArrayList<TLRPC.TL_webAuthorization> authorizations;
        public ArrayList<TLRPC.User> users;

        public webAuthorizations() {
            this.authorizations = new ArrayList();
            this.users = new ArrayList();
        }

        public static webAuthorizations TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-313079300) == r3) goto L5;
            webAuthorizations r0 = null;
        L7:
            return (webAuthorizations) TLObject.TLdeserialize(webAuthorizations.class, r0, r2, r3, r4);
        L5:
            r0 = new webAuthorizations();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.authorizations = Vector.deserialize(r2, new TL_account$webAuthorizations$$ExternalSyntheticLambda0(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.authorizations);
            Vector.serialize(r2, this.users);
        }
    }

    /* loaded from: classes6.dex */
    public static class webPagePreview extends TLObject {
        public static final int constructor = -1936029524;
        public ArrayList<TLRPC.Chat> chats;
        public TLRPC.MessageMedia media;
        public ArrayList<TLRPC.User> users;

        public webPagePreview() {
            this.chats = new ArrayList();
            this.users = new ArrayList();
        }

        public static webPagePreview TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1936029524) == r3) goto L5;
            webPagePreview r0 = null;
        L7:
            return (webPagePreview) TLObject.TLdeserialize(webPagePreview.class, r0, r2, r3, r4);
        L5:
            r0 = new webPagePreview();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.media = TLRPC.MessageMedia.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.chats = Vector.deserialize(r2, new TLRPC$TL_channels_adminLogResults$$ExternalSyntheticLambda1(), r3);
            this.users = Vector.deserialize(r2, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.media.serializeToStream(r2);
            Vector.serialize(r2, this.chats);
            Vector.serialize(r2, this.users);
        }
    }

    public TL_account() {
    }
}
