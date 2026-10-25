package com.exteragram.messenger.export.ui;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.google.android.gms.cast.MediaTrack;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes4.dex */
public class ExportMapper {
    private final ChatInfo chatInfo;
    private final int currentAccount;
    private final String path;

    public static class Action {

        @SerializedName("action")
        public String actionType;

        @SerializedName("actor")
        public String actor;

        @SerializedName("actor_id")
        public String actor_id;

        @SerializedName("amount")
        public long amount;

        @SerializedName("boost_peer_id")
        public long boost_peer_id;

        @SerializedName("boosts")
        public int boosts;

        @SerializedName("button_id")
        public int button_id;

        @SerializedName("charge_id")
        public String charge_id;

        @SerializedName("currency")
        public String currency;

        @SerializedName("information_text")
        public String customAction;

        @SerializedName("discard_reason")
        public String discard_reason;

        @SerializedName("distance")
        public int distance;

        @SerializedName("duration_seconds")
        public int duration;

        @SerializedName("emoticon")
        public String emotion;

        @SerializedName("from_id")
        public long from_id;

        @SerializedName("game_message_id")
        public int game_message_id;

        @SerializedName("gift_text")
        public List<Entity> giftText;

        @SerializedName("gift_code")
        public String gift_code;

        @SerializedName("gift_id")
        public long gift_id;

        @SerializedName("giveaway_msg_id")
        public int giveaway_msg_id;

        @SerializedName("inviter_id")
        public long inviterId;

        @SerializedName("is_unclaimed")
        public boolean is_unclaimed;

        @SerializedName("media_spoiler")
        public boolean media_spoiler;

        @SerializedName("members")
        public List<Long> members;

        @SerializedName("message_id")
        public int messageId;

        @SerializedName("months")
        public int months;

        @SerializedName("new_icon_emoji_id")
        public int new_icon_emoji_id;

        @SerializedName("peer")
        public long peer;

        @SerializedName("peers")
        public List<String> peers;

        @SerializedName("period")
        public int period;

        @SerializedName("reason_app_id")
        public long reason_app_id;

        @SerializedName("reason_app_name")
        public String reason_app_name;

        @SerializedName("reason_domain")
        public String reason_domain;

        @SerializedName("recurring")
        public String recurring;

        @SerializedName("schedule_date")
        public int schedule_date;

        @SerializedName("score")
        public int score;

        @SerializedName("stars")
        public long stars;

        @SerializedName("stars_boolean")
        public boolean stars_boolean;

        @SerializedName("text")
        public String text;

        @SerializedName("title")
        public String title;

        @SerializedName("to_id")
        public long to_id;

        @SerializedName("transaction_id")
        public String transaction_id;

        @SerializedName("unclaimed")
        public int unclaimed;

        @SerializedName("unclaimed_count")
        public int unclaimed_count;

        @SerializedName("values")
        public List<String> values;

        @SerializedName("via_giveaway")
        public boolean via_giveaway;

        @SerializedName("winners_count")
        public int winners_count;

        public Action() {
        }
    }

    public static class Answer {

        @SerializedName("chosen")
        public boolean chosen;

        @SerializedName("text")
        public String text;

        @SerializedName("voters")
        public String votersCount;

        private Answer() {
        }
    }

    public static class ChatInfo {

        @SerializedName("id")
        public long id;

        @SerializedName("msgsCount")
        public int msgsCount;

        @SerializedName("name")
        public String name;

        @SerializedName("type")
        public String type;

        public ChatInfo() {
        }
    }

    public static class ContactInformation {

        @SerializedName("first_name")
        public String firstName;

        @SerializedName("last_name")
        public String lastName;

        @SerializedName("phone_number")
        public String phoneNumber;

        @SerializedName("contact_vcard")
        public String vcardRelativePath;

        private ContactInformation() {
        }
    }

    public static class Entity {

        @SerializedName("additional")
        public String additional;

        @SerializedName("text")
        public String text;

        @SerializedName("type")
        public String type;

        public Entity() {
        }
    }

    public static class ExportFileLocation extends TLRPC.FileLocation {
        public String path;

        public ExportFileLocation(String r1) {
            this.path = r1;
        }
    }

    public static class GiveawayInformation {

        @SerializedName("additional_prize")
        public String additionalPrize;

        @SerializedName("channels")
        public List<Long> channels;

        @SerializedName("countries")
        public List<String> countries;

        @SerializedName("months")
        public String months;

        @SerializedName("is_only_new_subscribers")
        public boolean onlyNew;

        @SerializedName("quantity")
        public String quantity;

        @SerializedName("stars")
        public String stars;

        @SerializedName("until_date")
        public String until_date;

        private GiveawayInformation() {
        }
    }

    public static class GiveawayResults {

        @SerializedName("additional_peers_count")
        public String additionalPeersCount;

        @SerializedName("additional_prize")
        public String additionalPrize;

        @SerializedName("channel")
        public String channel;

        @SerializedName("is_refunded")
        public boolean isRefunded;

        @SerializedName("launch_message_id")
        public String launchMessageId;

        @SerializedName("months")
        public String months;

        @SerializedName("is_only_new_subscribers")
        public boolean onlyNewSubscribers;

        @SerializedName("stars")
        public String stars;

        @SerializedName("unclaimed_count")
        public String unclaimedCount;

        @SerializedName("until_date")
        public String untilDate;

        @SerializedName("winners")
        public List<String> winners;

        @SerializedName("winners_count")
        public String winnersCount;

        private GiveawayResults() {
        }
    }

    public static class InvoiceInformation {

        @SerializedName("amount")
        public String amount;

        @SerializedName("currency")
        public String currency;

        @SerializedName(MediaTrack.ROLE_DESCRIPTION)
        public String description;

        @SerializedName("receipt_message_id")
        public String receiptMsgId;

        @SerializedName("title")
        public String title;

        private InvoiceInformation() {
        }
    }

    public static class JsonMessage {

        @SerializedName("action")
        public Action action;

        @SerializedName("date")
        public int date;

        @SerializedName("from")
        public String from;

        @SerializedName("from_id")
        public String from_id;

        @SerializedName("id")
        public int id;

        @SerializedName("media")
        public Media media;

        @SerializedName("text_entities")
        public List<Entity> text_entities;

        @SerializedName("type")
        public String type;

        public JsonMessage() {
        }
    }

    public static class LocationInformation {

        @SerializedName("latitude")
        public String latitude;

        @SerializedName("longitude")
        public String longitude;

        private LocationInformation() {
        }
    }

    public static class Media {

        @SerializedName("contact_information")
        public ContactInformation contact;

        @SerializedName("duration")
        public int duration;

        @SerializedName("file_name")
        public String fileName;

        @SerializedName("file")
        public String filePathRelative;

        @SerializedName("game_description")
        public String gameDescription;

        @SerializedName("game_short_name")
        public String gameShortName;

        @SerializedName("game_title")
        public String gameTitle;

        @SerializedName("giveaway_information")
        public GiveawayInformation giveawayInformation;

        @SerializedName("giveaway_results")
        public GiveawayResults giveawayResults;

        @SerializedName("height")
        public int height;

        @SerializedName("invoice_information")
        public InvoiceInformation invoice;

        @SerializedName("location_information")
        public LocationInformation location;

        @SerializedName("media_type")
        public String mediaType;

        @SerializedName("mimeType")
        public String mimeType;

        @SerializedName("paid_stars_amount")
        public long paidStarsAmount;

        @SerializedName("performer")
        public String performer;

        @SerializedName("photo")
        public String photoPathRelative;

        @SerializedName("poll")
        public Poll poll;

        @SerializedName("serializedSticker")
        public String serializedSticker;

        @SerializedName("size")
        public int size;

        @SerializedName("skipReason")
        public String skipReason;

        @SerializedName("media_spoiler")
        public boolean spoiler;

        @SerializedName("title")
        public String title;

        @SerializedName("ttl")
        public int ttl;

        @SerializedName("address")
        public String venueAddress;

        @SerializedName("place_name")
        public String venueTitle;

        @SerializedName("width")
        public int width;

        public Media() {
        }
    }

    public static class Poll {

        @SerializedName("answers")
        public List<Answer> answers;

        @SerializedName("closed")
        public boolean closed;

        @SerializedName("question")
        public String question;

        @SerializedName("total_voters")
        public String totalVotes;

        private Poll() {
        }
    }

    /* renamed from: $r8$lambda$dj-9OgfoWaoQECUiMhajz5vP374, reason: not valid java name */
    public static /* synthetic */ TLRPC.TL_messageMediaDocument m1407$r8$lambda$dj9OgfoWaoQECUiMhajz5vP374(ExportMapper r0, JsonMessage r1) {
        return r0.lambda$mapMedia$0(r1);
    }

    public ExportMapper(int r1, String r2, ChatInfo r3) {
        this.currentAccount = r1;
        this.path = r2;
        this.chatInfo = r3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v10, types: [org.telegram.tgnet.TLRPC$TL_messageEntityBold] */
    /* JADX WARN: Type inference failed for: r4v11, types: [org.telegram.tgnet.TLRPC$TL_messageEntityCode] */
    /* JADX WARN: Type inference failed for: r4v12, types: [org.telegram.tgnet.TLRPC$TL_messageEntityUrl] */
    /* JADX WARN: Type inference failed for: r4v13, types: [org.telegram.tgnet.TLRPC$TL_messageEntityEmail] */
    /* JADX WARN: Type inference failed for: r4v14, types: [org.telegram.tgnet.TLRPC$TL_messageEntityPhone] */
    /* JADX WARN: Type inference failed for: r4v15, types: [org.telegram.tgnet.TLRPC$TL_messageEntityCashtag] */
    /* JADX WARN: Type inference failed for: r4v16, types: [org.telegram.tgnet.TLRPC$TL_messageEntityHashtag] */
    /* JADX WARN: Type inference failed for: r4v17, types: [org.telegram.tgnet.TLRPC$TL_messageEntityCustomEmoji] */
    /* JADX WARN: Type inference failed for: r4v18, types: [org.telegram.tgnet.TLRPC$TL_messageEntityMention] */
    /* JADX WARN: Type inference failed for: r4v19, types: [org.telegram.tgnet.TLRPC$MessageEntity, org.telegram.tgnet.TLRPC$TL_messageEntityBlockquote] */
    /* JADX WARN: Type inference failed for: r4v20, types: [org.telegram.tgnet.TLRPC$TL_messageEntityMentionName] */
    /* JADX WARN: Type inference failed for: r4v22, types: [org.telegram.tgnet.TLRPC$TL_messageEntityUnknown] */
    /* JADX WARN: Type inference failed for: r4v3, types: [org.telegram.tgnet.TLRPC$TL_messageEntityBankCard] */
    /* JADX WARN: Type inference failed for: r4v4, types: [org.telegram.tgnet.TLRPC$TL_messageEntityItalic] */
    /* JADX WARN: Type inference failed for: r4v5, types: [org.telegram.tgnet.TLRPC$MessageEntity, org.telegram.tgnet.TLRPC$TL_messageEntityTextUrl] */
    /* JADX WARN: Type inference failed for: r4v6, types: [org.telegram.tgnet.TLRPC$TL_messageEntityUnderline] */
    /* JADX WARN: Type inference failed for: r4v7, types: [org.telegram.tgnet.TLRPC$TL_messageEntityStrike] */
    /* JADX WARN: Type inference failed for: r4v8, types: [org.telegram.tgnet.TLRPC$TL_messageEntityBotCommand] */
    /* JADX WARN: Type inference failed for: r4v9, types: [org.telegram.tgnet.TLRPC$MessageEntity, org.telegram.tgnet.TLRPC$TL_messageEntityPre] */
    private Pair<String, ArrayList<TLRPC.MessageEntity>> getMessageFromEntities(List<Entity> r8) {
        StringBuilder r7 = new StringBuilder();
        ArrayList r0 = new ArrayList();
        if (r8 == null) goto L5;
        Iterator<Entity> r82 = r8.iterator();
        int r2 = 0;
    L8:
        if (r82.hasNext() == false) goto L114;
        Entity r3 = r82.next();
        r7.append(r3.text);
        String r4 = r3.type;
        if (r4 == null) goto L8;
        r4.getClass();
        char r6 = 65535;
        switch(r4.hashCode()) {
            case -1999048254: goto L88;
            case -1787710669: goto L84;
            case -1178781136: goto L80;
            case -1037805620: goto L76;
            case -1026963764: goto L72;
            case -972521773: goto L68;
            case -543404845: goto L64;
            case 111267: goto L60;
            case 3029637: goto L56;
            case 3059181: goto L52;
            case 3321850: goto L48;
            case 96619420: goto L44;
            case 106642798: goto L40;
            case 554996615: goto L36;
            case 697547724: goto L32;
            case 717313528: goto L28;
            case 950345194: goto L24;
            case 1303202319: goto L20;
            case 2009151424: goto L16;
            default: goto L91;
        };
    L91:
        switch(r6) {
            case 0: goto L111;
            case 1: goto L110;
            case 2: goto L109;
            case 3: goto L108;
            case 4: goto L107;
            case 5: goto L106;
            case 6: goto L105;
            case 7: goto L104;
            case 8: goto L103;
            case 9: goto L102;
            case 10: goto L101;
            case 11: goto L100;
            case 12: goto L99;
            case 13: goto L98;
            case 14: goto L97;
            case 15: goto L96;
            case 16: goto L95;
            case 17: goto L94;
            case 18: goto L93;
            default: goto L92;
        };
    L92:
        TLRPC.TL_messageEntitySpoiler r42 = new TLRPC.TL_messageEntityUnknown();
    L112:
        r42.offset = r2;
        r42.length = r3.text.length();
        r0.add(r42);
        r2 = r2 + r3.text.length();
        goto L8
    L93:
        ?? r43 = new TLRPC.TL_messageEntityMentionName();
        r43.user_id = Utilities.parseLong(r3.additional).longValue();
        r42 = r43;
        goto L112
    L94:
        ?? r44 = new TLRPC.TL_messageEntityBlockquote();
        r44.collapsed = "true".equals(r3.additional);
        r42 = r44;
        goto L112
    L95:
        r42 = new TLRPC.TL_messageEntityMention();
        goto L112
    L96:
        ?? r45 = new TLRPC.TL_messageEntityCustomEmoji();
        r45.document_id = Utilities.parseLong(r3.additional).longValue();
        r42 = r45;
        goto L112
    L97:
        r42 = new TLRPC.TL_messageEntityHashtag();
        goto L112
    L98:
        r42 = new TLRPC.TL_messageEntityCashtag();
        goto L112
    L99:
        r42 = new TLRPC.TL_messageEntityPhone();
        goto L112
    L100:
        r42 = new TLRPC.TL_messageEntityEmail();
        goto L112
    L101:
        r42 = new TLRPC.TL_messageEntityUrl();
        goto L112
    L102:
        r42 = new TLRPC.TL_messageEntityCode();
        goto L112
    L103:
        r42 = new TLRPC.TL_messageEntityBold();
        goto L112
    L104:
        ?? r46 = new TLRPC.TL_messageEntityPre();
        r46.language = r3.additional;
        r42 = r46;
        goto L112
    L105:
        r42 = new TLRPC.TL_messageEntityBotCommand();
        goto L112
    L106:
        r42 = new TLRPC.TL_messageEntityStrike();
        goto L112
    L107:
        r42 = new TLRPC.TL_messageEntityUnderline();
        goto L112
    L108:
        ?? r47 = new TLRPC.TL_messageEntityTextUrl();
        r47.url = r3.additional;
        r42 = r47;
        goto L112
    L109:
        r42 = new TLRPC.TL_messageEntityItalic();
        goto L112
    L110:
        r42 = new TLRPC.TL_messageEntityBankCard();
        goto L112
    L111:
        r42 = new TLRPC.TL_messageEntitySpoiler();
        goto L112
    L16:
        if (r4.equals("mention_name") == false) goto L91;
        r6 = 18;
        goto L91
    L20:
        if (r4.equals("blockquote") == false) goto L91;
        r6 = 17;
        goto L91
    L24:
        if (r4.equals("mention") == false) goto L91;
        r6 = 16;
        goto L91
    L28:
        if (r4.equals("custom_emoji") == false) goto L91;
        r6 = 15;
        goto L91
    L32:
        if (r4.equals("hashtag") == false) goto L91;
        r6 = 14;
        goto L91
    L36:
        if (r4.equals("cashtag") == false) goto L91;
        r6 = '\r';
        goto L91
    L40:
        if (r4.equals("phone") == false) goto L91;
        r6 = '\f';
        goto L91
    L44:
        if (r4.equals("email") == false) goto L91;
        r6 = 11;
        goto L91
    L48:
        if (r4.equals("link") == false) goto L91;
        r6 = '\n';
        goto L91
    L52:
        if (r4.equals("code") == false) goto L91;
        r6 = '\t';
        goto L91
    L56:
        if (r4.equals("bold") == false) goto L91;
        r6 = '\b';
        goto L91
    L60:
        if (r4.equals("pre") == false) goto L91;
        r6 = 7;
        goto L91
    L64:
        if (r4.equals("bot_command") == false) goto L91;
        r6 = 6;
        goto L91
    L68:
        if (r4.equals("strikethrough") == false) goto L91;
        r6 = 5;
        goto L91
    L72:
        if (r4.equals("underline") == false) goto L91;
        r6 = 4;
        goto L91
    L76:
        if (r4.equals("text_link") == false) goto L91;
        r6 = 3;
        goto L91
    L80:
        if (r4.equals("italic") == false) goto L91;
        r6 = 2;
        goto L91
    L84:
        if (r4.equals("bank_card") == false) goto L91;
        r6 = 1;
        goto L91
    L88:
        if (r4.equals("spoiler") == false) goto L91;
        r6 = 0;
        goto L91
    L114:
        return new Pair(r7.toString(), r0);
    L5:
        return new Pair(_UrlKt.FRAGMENT_ENCODE_SET, new ArrayList());
    }

    private /* synthetic */ TLRPC.TL_messageMediaDocument lambda$mapMedia$0(JsonMessage r11) {
        TLRPC.TL_messageMediaDocument r0 = new TLRPC.TL_messageMediaDocument();
        r0.flags = 1;
        Media r2 = r11.media;
        r0.spoiler = r2.spoiler;
        r0.ttl_seconds = r2.ttl;
        TLRPC.TL_document r22 = new TLRPC.TL_document();
        r0.document = r22;
        r22.date = r11.date;
        Media r3 = r11.media;
        r22.size = r3.size;
        r22.mime_type = r3.mimeType;
        String r4 = r3.fileName;
        r22.file_name = r4;
        r22.file_name_fixed = r4;
        String r23 = r3.filePathRelative;
        StringBuilder r32 = new StringBuilder();
        String r10 = this.path;
        char r5 = 0;
        r32.append(r10.substring(0, r10.indexOf("/chats/")));
        r32.append("/");
        r32.append(r23);
        String r102 = r32.toString();
        r0.document.localPath = r102;
        String r33 = r11.media.mediaType;
        if (r33 == null) goto L40;
        r33.getClass();
        switch(r33.hashCode()) {
            case -1618268480: goto L25;
            case -1313337277: goto L21;
            case 354039290: goto L17;
            case 1118509956: goto L13;
            case 1548945477: goto L9;
            default: goto L7;
        };
    L7:
        r5 = 65535;
    L27:
        TLRPC.DocumentAttribute r34 = null;
        switch(r5) {
            case 0: goto L34;
            case 1: goto L34;
            case 2: goto L31;
            case 3: goto L30;
            case 4: goto L31;
            default: goto L38;
        };
    L30:
        r34 = new TLRPC.TL_documentAttributeAnimated();
        goto L38
    L31:
        r34 = new TLRPC.TL_documentAttributeAudio();
        Media r103 = r11.media;
        r34.duration = r103.duration;
        r34.voice = r103.mediaType.equals("voice_message");
        if (r11.media.mediaType.equals("audio_file") == false) goto L38;
        Media r104 = r11.media;
        r34.performer = r104.performer;
        r34.title = r104.title;
        goto L38
    L34:
        TLRPC.TL_documentAttributeVideo r42 = new TLRPC.TL_documentAttributeVideo();
        r42.round_message = r11.media.mediaType.equals("video_message");
        Media r112 = r11.media;
        r42.w = r112.width;
        r42.h = r112.height;
        r42.duration = r112.duration;
        if (r23 == null) goto L37;
        SendMessagesHelper.fillVideoAttribute(r102, r42, null);
        r0.document.thumbs.add(ImageLoader.scaleAndSaveImage(SendMessagesHelper.createVideoThumbnail(r102, 1), 320.0f, 320.0f, 90, true));
    L37:
        r34 = r42;
    L38:
        if (r34 == null) goto L40;
        r0.document.attributes.add(r34);
        goto L40
    L9:
        if (r33.equals("audio_file") == false) goto L7;
        r5 = 4;
        goto L27
    L13:
        if (r33.equals("animation") == false) goto L7;
        r5 = 3;
        goto L27
    L17:
        if (r33.equals("voice_message") == false) goto L7;
        r5 = 2;
        goto L27
    L21:
        if (r33.equals("video_message") == false) goto L7;
        r5 = 1;
        goto L27
    L25:
        if (r33.equals("video_file") == true) goto L27;
    L40:
        return r0;
    }

    private TLRPC.MessageMedia mapMedia(JsonMessage r8) {
        ExportMapper$$ExternalSyntheticLambda0 r0 = new ExportMapper$$ExternalSyntheticLambda0(this, r8);
        String r1 = r8.media.mediaType;
        if (r1 == null) goto L5;
        r1.getClass();
        char r5 = 65535;
        switch(r1.hashCode()) {
            case -1890252483: goto L70;
            case -1681785736: goto L66;
            case -1618268480: goto L62;
            case -1450829449: goto L58;
            case -1313337277: goto L54;
            case 3165170: goto L50;
            case 3446719: goto L46;
            case 106642994: goto L42;
            case 112093807: goto L38;
            case 354039290: goto L34;
            case 951526432: goto L30;
            case 1118509956: goto L26;
            case 1548945477: goto L22;
            case 1849077983: goto L18;
            case 1960198957: goto L14;
            case 1997606435: goto L10;
            default: goto L74;
        };
    L10:
        if (r1.equals("giveawayStart") == false) goto L74;
        r5 = 15;
        goto L74
    L14:
        if (r1.equals("invoice") == false) goto L74;
        r5 = 14;
        goto L74
    L18:
        if (r1.equals("geopoint") == false) goto L74;
        r5 = '\r';
        goto L74
    L22:
        if (r1.equals("audio_file") == false) goto L74;
        r5 = '\f';
        goto L74
    L26:
        if (r1.equals("animation") == false) goto L74;
        r5 = 11;
        goto L74
    L30:
        if (r1.equals("contact") == false) goto L74;
        r5 = '\n';
        goto L74
    L34:
        if (r1.equals("voice_message") == false) goto L74;
        r5 = '\t';
        goto L74
    L38:
        if (r1.equals("venue") == false) goto L74;
        r5 = '\b';
        goto L74
    L42:
        if (r1.equals("photo") == false) goto L74;
        r5 = 7;
        goto L74
    L46:
        if (r1.equals("poll") == false) goto L74;
        r5 = 6;
        goto L74
    L50:
        if (r1.equals("game") == false) goto L74;
        r5 = 5;
        goto L74
    L54:
        if (r1.equals("video_message") == false) goto L74;
        r5 = 4;
        goto L74
    L58:
        if (r1.equals("giveawayResults") == false) goto L74;
        r5 = 3;
        goto L74
    L62:
        if (r1.equals("video_file") == false) goto L74;
        r5 = 2;
        goto L74
    L66:
        if (r1.equals("paidMedia") == false) goto L74;
        r5 = 1;
        goto L74
    L70:
        if (r1.equals("sticker") == false) goto L74;
        r5 = 0;
    L74:
        switch(r5) {
            case 0: goto L107;
            case 1: goto L104;
            case 2: goto L107;
            case 3: goto L102;
            case 4: goto L107;
            case 5: goto L100;
            case 6: goto L94;
            case 7: goto L89;
            case 8: goto L87;
            case 9: goto L107;
            case 10: goto L83;
            case 11: goto L107;
            case 12: goto L107;
            case 13: goto L81;
            case 14: goto L79;
            case 15: goto L77;
            default: goto L76;
        };
    L77:
        TLRPC.TL_messageMediaGiveaway r7 = new TLRPC.TL_messageMediaGiveaway();
        GiveawayInformation r82 = r8.media.giveawayInformation;
        r7.quantity = Utilities.parseInt(r82.quantity);
        r7.months = Utilities.parseInt(r82.months);
        r7.until_date = Utilities.parseInt(r82.until_date);
        r7.channels = new ArrayList(r82.channels);
        r7.countries_iso2 = new ArrayList(r82.countries);
        r7.prize_description = r82.additionalPrize;
        r7.stars = Utilities.parseInt(r82.stars);
        r7.only_new_subscribers = r82.onlyNew;
        return r7;
    L79:
        TLRPC.TL_messageMediaInvoice r72 = new TLRPC.TL_messageMediaInvoice();
        InvoiceInformation r02 = r8.media.invoice;
        r72.title = r02.title;
        r72.description = r02.description;
        r72.total_amount = Utilities.parseLong(r02.amount).longValue();
        InvoiceInformation r83 = r8.media.invoice;
        r72.currency = r83.currency;
        r72.receipt_msg_id = Utilities.parseInt(r83.receiptMsgId);
        return r72;
    L81:
        TLRPC.TL_messageMediaGeo r73 = new TLRPC.TL_messageMediaGeo();
        TLRPC.TL_geoPoint r03 = new TLRPC.TL_geoPoint();
        r73.geo = r03;
        r03._long = Utilities.parseLong(r8.media.location.longitude).longValue();
        r73.geo.lat = Utilities.parseLong(r8.media.location.latitude).longValue();
        r73.ttl_seconds = r8.media.ttl;
        return r73;
    L83:
        TLRPC.TL_messageMediaContact r04 = new TLRPC.TL_messageMediaContact();
        ContactInformation r84 = r8.media.contact;
        r04.phone_number = r84.phoneNumber;
        r04.first_name = r84.firstName;
        r04.last_name = r84.lastName;
        String r85 = r84.vcardRelativePath;
        if (r85 == null) goto L86;
        StringBuilder r3 = new StringBuilder();
        String r74 = this.path;
        r3.append(r74.substring(0, r74.indexOf("/chats/")));
        r3.append("/");
        r3.append(r85);
        r04.vcard = r3.toString();
    L86:
        return r04;
    L87:
        TLRPC.TL_messageMediaVenue r75 = new TLRPC.TL_messageMediaVenue();
        Media r05 = r8.media;
        r75.title = r05.venueTitle;
        r75.address = r05.venueAddress;
        TLRPC.TL_geoPoint r06 = new TLRPC.TL_geoPoint();
        r75.geo = r06;
        r06._long = Utilities.parseLong(r8.media.location.longitude).longValue();
        r75.geo.lat = Utilities.parseLong(r8.media.location.latitude).longValue();
        return r75;
    L89:
        TLRPC.TL_messageMediaPhoto r07 = new TLRPC.TL_messageMediaPhoto();
        Media r32 = r8.media;
        r07.spoiler = r32.spoiler;
        r07.ttl_seconds = r32.ttl;
        String r33 = r32.photoPathRelative;
        if (r33 == null) goto L92;
        StringBuilder r52 = new StringBuilder();
        String r6 = this.path;
        r52.append(r6.substring(0, r6.indexOf("/chats/")));
        r52.append("/");
        r52.append(r33);
        r07.attachPath = r52.toString();
    L92:
        r07.photo = new TLRPC.TL_photo();
        TLRPC.TL_photoSize r12 = new TLRPC.TL_photoSize();
        Media r2 = r8.media;
        r12.w = r2.width;
        r12.h = r2.height;
        r12.type = "y";
        r12.location = new ExportFileLocation(this.path);
        r12.size = Utilities.parseInt((r8.media.size / 1024) + _UrlKt.FRAGMENT_ENCODE_SET);
        r07.photo.sizes.add(r12);
        return r07;
    L94:
        TLRPC.TL_messageMediaPoll r76 = new TLRPC.TL_messageMediaPoll();
        TLRPC.TL_poll r08 = new TLRPC.TL_poll();
        TLRPC.TL_textWithEntities r13 = r08.question;
        Poll r22 = r8.media.poll;
        r13.text = r22.question;
        r08.closed = r22.closed;
        Iterator<Answer> r14 = r22.answers.iterator();
    L96:
        if (r14.hasNext() == false) goto L98;
        Answer r23 = r14.next();
        TLRPC.TL_pollAnswer r34 = new TLRPC.TL_pollAnswer();
        r34.text.text = r23.text;
        r08.answers.add(r34);
        goto L96
    L98:
        TLRPC.TL_pollResults r15 = new TLRPC.TL_pollResults();
        r15.total_voters = Utilities.parseInt(r8.media.poll.totalVotes);
        r76.poll = r08;
        r76.results = r15;
        return r76;
    L100:
        TLRPC.TL_messageMediaGame r77 = new TLRPC.TL_messageMediaGame();
        TLRPC.TL_game r09 = new TLRPC.TL_game();
        r77.game = r09;
        Media r86 = r8.media;
        r09.title = r86.gameTitle;
        r09.description = r86.gameDescription;
        r09.short_name = r86.gameShortName;
        return r77;
    L102:
        TLRPC.TL_messageMediaGiveawayResults r78 = new TLRPC.TL_messageMediaGiveawayResults();
        GiveawayResults r87 = r8.media.giveawayResults;
        r78.channel_id = Utilities.parseLong(r87.channel).longValue();
        r78.winners = (ArrayList) r87.winners.stream().map(new ExportMapper$$ExternalSyntheticLambda1()).collect(Collectors.toCollection(new ExportMapper$$ExternalSyntheticLambda2()));
        r78.prize_description = r87.additionalPrize;
        r78.until_date = Utilities.parseInt(r87.untilDate);
        r78.launch_msg_id = Utilities.parseInt(r87.launchMessageId);
        r78.additional_peers_count = Utilities.parseInt(r87.additionalPeersCount);
        r78.winners_count = Utilities.parseInt(r87.winnersCount);
        r78.unclaimed_count = Utilities.parseInt(r87.unclaimedCount);
        r78.months = Utilities.parseInt(r87.months);
        r78.stars = Utilities.parseInt(r87.stars);
        r78.refunded = r87.isRefunded;
        r78.only_new_subscribers = r87.onlyNewSubscribers;
        return r78;
    L104:
        TLRPC.TL_messageMediaPaidMedia r79 = new TLRPC.TL_messageMediaPaidMedia();
        r79.stars_amount = r8.media.paidStarsAmount;
        return r79;
    L76:
        return new TLRPC.TL_messageMediaUnsupported();
    L107:
        if (r8.media.mediaType.equals("sticker") == false) goto L129;
        byte[] r710 = Base64.decode(r8.media.serializedSticker, 0);
        NativeByteBuffer r88 = null;
        NativeByteBuffer r010 = new NativeByteBuffer(r710.length);     // Catch: Throwable -> L117 Exception -> L119
        r010.buffer.put(r710);     // Catch: Throwable -> L113 Exception -> L115
        r010.rewind();     // Catch: Throwable -> L113 Exception -> L115
        TLRPC.MessageMedia r711 = TLRPC.MessageMedia.TLdeserialize(r010, r010.readInt32(false), false);     // Catch: Throwable -> L113 Exception -> L115
        r010.reuse();
        return r711;
    L115:
        e = e;
    L121:
        FileLog.e("Export: failed to deserialize sticker: ", e);     // Catch: Throwable -> L113
        if (r010 == null) goto L124;
        r010.reuse();
    L124:
        return null;
    L113:
        th = th;
        r88 = r010;
    L125:
        if (r88 == null) goto L127;
        r88.reuse();
    L127:
        throw th;
    L119:
        e = e;
        r010 = null;
    L117:
        th = th;
        goto L125
    L129:
        return (TLRPC.MessageMedia) r0.run();
    L5:
        return (TLRPC.MessageMedia) r0.run();
    }

    private TLRPC.TL_messageService mapService(JsonMessage r11) {
        TLRPC.TL_messageService r0 = new TLRPC.TL_messageService();
        r0.id = r11.id;
        r0.date = r11.date;
        String r1 = r11.action.actor_id;
        int r2 = 4;
        if (r1 != null) goto L5;
    L7:
        long r3 = 0;
    L9:
        if ("create_group".equals(r11.action.actionType) == false) goto L12;
        TLRPC.TL_messageActionChatCreate r10 = new TLRPC.TL_messageActionChatCreate();
        r0.action = r10;
        r10.title = r11.action.title;
        r10.users = new ArrayList(r11.action.members);
    L254:
        r0.from_id = MessagesController.getInstance(UserConfig.selectedAccount).getPeer(r3);
        r0.peer_id = MessagesController.getInstance(UserConfig.selectedAccount).getPeer(r3);
        return r0;
    L12:
        if ("edit_group_title".equals(r11.action.actionType) == false) goto L15;
        TLRPC.TL_messageActionChatEditTitle r102 = new TLRPC.TL_messageActionChatEditTitle();
        r0.action = r102;
        r102.title = r11.action.title;
        goto L254
    L15:
        if ("edit_group_photo".equals(r11.action.actionType) == false) goto L18;
        TLRPC.TL_messageActionChatEditPhoto r103 = new TLRPC.TL_messageActionChatEditPhoto();
        r0.action = r103;
        r103.photo = new TLRPC.TL_photo();
        goto L254
    L18:
        if ("delete_group_photo".equals(r11.action.actionType) == false) goto L21;
        TLRPC.TL_messageActionChatDeletePhoto r104 = new TLRPC.TL_messageActionChatDeletePhoto();
        r0.action = r104;
        r104.user_id = Utilities.parseLong(r11.action.actor).longValue();
        goto L254
    L21:
        if ("invite_members".equals(r11.action.actionType) == false) goto L24;
        TLRPC.TL_messageActionChatAddUser r105 = new TLRPC.TL_messageActionChatAddUser();
        r0.action = r105;
        r105.users = new ArrayList(r11.action.members);
        goto L254
    L24:
        if ("remove_members".equals(r11.action.actionType) == false) goto L27;
        TLRPC.TL_messageActionChatDeleteUser r106 = new TLRPC.TL_messageActionChatDeleteUser();
        r0.action = r106;
        r106.user_id = r3;
        goto L254
    L27:
        if ("join_group_by_link".equals(r11.action.actionType) == false) goto L30;
        TLRPC.TL_messageActionChatJoinedByLink r107 = new TLRPC.TL_messageActionChatJoinedByLink();
        r0.action = r107;
        r107.user_id = r3;
        r107.inviter_id = r11.action.inviterId;
        goto L254
    L30:
        if ("create_channel".equals(r11.action.actionType) == false) goto L33;
        TLRPC.TL_messageActionChannelCreate r108 = new TLRPC.TL_messageActionChannelCreate();
        r0.action = r108;
        r108.user_id = r3;
        r108.title = r11.action.title;
        goto L254
    L33:
        if ("migrate_to_supergroup".equals(r11.action.actionType) == false) goto L36;
        r0.action = new TLRPC.TL_messageActionChatMigrateTo();
        goto L254
    L36:
        if ("migrate_from_group".equals(r11.action.actionType) == false) goto L39;
        TLRPC.TL_messageActionChannelMigrateFrom r109 = new TLRPC.TL_messageActionChannelMigrateFrom();
        r0.action = r109;
        r109.title = r11.action.title;
        goto L254
    L39:
        if ("pin_message".equals(r11.action.actionType) == false) goto L42;
        r0.action = new TLRPC.TL_messageActionPinMessage();
        goto L254
    L42:
        if ("clear_history".equals(r11.action.actionType) == false) goto L45;
        r0.action = new TLRPC.TL_messageActionHistoryClear();
        goto L254
    L45:
        if ("score_in_game".equals(r11.action.actionType) == false) goto L48;
        TLRPC.TL_messageActionGameScore r1010 = new TLRPC.TL_messageActionGameScore();
        r0.action = r1010;
        r1010.score = r11.action.score;
        r1010.game_id = r11.game_message_id;
        goto L254
    L48:
        if ("send_payment".equals(r11.action.actionType) == false) goto L55;
        TLRPC.TL_messageActionPaymentSent r1011 = new TLRPC.TL_messageActionPaymentSent();
        r0.action = r1011;
        if ("used".equals(r11.action.recurring) == false) goto L52;
        r2 = 8;
    L52:
        r1011.flags = r2;
        TLRPC.MessageAction r1012 = r0.action;
        Action r112 = r11.action;
        r1012.total_amount = r112.amount;
        r1012.currency = r112.currency;
        return r0;
    L55:
        if ("phone_call".equals(r11.action.actionType) == false) goto L69;
        TLRPC.TL_messageActionPhoneCall r1013 = new TLRPC.TL_messageActionPhoneCall();
        r0.action = r1013;
        Action r12 = r11.action;
        r1013.duration = r12.duration;
        if ("hangup".equals(r12.discard_reason) == false) goto L60;
        r0.action.reason = new TLRPC.TL_phoneCallDiscardReasonHangup();
        goto L254
    L60:
        if ("busy".equals(r11.action.discard_reason) == false) goto L63;
        r0.action.reason = new TLRPC.TL_phoneCallDiscardReasonBusy();
        goto L254
    L63:
        if ("missed".equals(r11.action.discard_reason) == false) goto L66;
        r0.action.reason = new TLRPC.TL_phoneCallDiscardReasonMissed();
        goto L254
    L66:
        if ("disconnect".equals(r11.action.discard_reason) == false) goto L254;
        r0.action.reason = new TLRPC.TL_phoneCallDiscardReasonDisconnect();
        goto L254
    L69:
        if ("take_screenshot".equals(r11.action.actionType) == false) goto L71;
        r0.action = new TLRPC.TL_messageActionScreenshotTaken();
        goto L254
    L71:
        String r13 = r11.action.customAction;
        if (r13 == null) goto L77;
        if (r13.isEmpty() == true) goto L77;
        TLRPC.TL_messageActionCustomAction r1014 = new TLRPC.TL_messageActionCustomAction();
        r0.action = r1014;
        r1014.message = r11.action.customAction;
    L77:
        if ("attach_menu_bot_allowed".equals(r11.action.actionType) == false) goto L79;
    L256:
        TLRPC.TL_messageActionBotAllowed r1015 = new TLRPC.TL_messageActionBotAllowed();
        r0.action = r1015;
        if ("attach_menu_bot_allowed".equals(r11.action.actionType) == false) goto L261;
        r1015.attach_menu = true;
        return r0;
    L261:
        if ("web_app_bot_allowed".equals(r11.action.actionType) == false) goto L265;
        r1015.from_request = true;
        return r0;
    L265:
        if ("allow_sending_messages".equals(r11.action.actionType) == false) goto L268;
        TLRPC.TL_botApp r14 = new TLRPC.TL_botApp();
        r1015.app = r14;
        Action r1016 = r11.action;
        r14.id = r1016.reason_app_id;
        r14.title = r1016.reason_app_name;
        return r0;
    L268:
        r1015.domain = r11.action.reason_domain;
        return r0;
    L79:
        if ("web_app_bot_allowed".equals(r11.action.actionType) == true) goto L256;
        if ("allow_sending_messages".equals(r11.action.actionType) == true) goto L256;
        if ("send_passport_values".equals(r11.action.actionType) == false) goto L165;
        r0.action = new TLRPC.TL_messageActionSecureValuesSent();
        if (r11.action.values == null) goto L254;
        ArrayList<TLRPC.SecureValueType> r1017 = new ArrayList();
        Iterator<String> r113 = r11.action.values.iterator();
    L89:
        if (r113.hasNext() == false) goto L163;
        String r15 = r113.next();
        if (r15 == null) goto L89;
        char r8 = 65535;
        switch(r15.hashCode()) {
            case -1955557588: goto L145;
            case -1623690298: goto L141;
            case -1553552209: goto L137;
            case -612351174: goto L133;
            case 96619420: goto L129;
            case 187441505: goto L125;
            case 507273191: goto L121;
            case 562006513: goto L117;
            case 576802554: goto L113;
            case 579025748: goto L109;
            case 862486339: goto L105;
            case 943069994: goto L101;
            case 1216777234: goto L97;
            default: goto L148;
        };
    L148:
        switch(r8) {
            case 0: goto L162;
            case 1: goto L161;
            case 2: goto L160;
            case 3: goto L159;
            case 4: goto L158;
            case 5: goto L157;
            case 6: goto L156;
            case 7: goto L155;
            case 8: goto L154;
            case 9: goto L153;
            case 10: goto L152;
            case 11: goto L151;
            case 12: goto L150;
            default: goto L89;
        };
    L150:
        r1017.add(new TLRPC.TL_secureValueTypePassport());
        goto L89
    L151:
        r1017.add(new TLRPC.TL_secureValueTypeDriverLicense());
        goto L89
    L152:
        r1017.add(new TLRPC.TL_secureValueTypePersonalDetails());
        goto L89
    L153:
        r1017.add(new TLRPC.TL_secureValueTypeInternalPassport());
        goto L89
    L154:
        r1017.add(new TLRPC.TL_secureValueTypeUtilityBill());
        goto L89
    L155:
        r1017.add(new TLRPC.TL_secureValueTypeIdentityCard());
        goto L89
    L156:
        r1017.add(new TLRPC.TL_secureValueTypeTemporaryRegistration());
        goto L89
    L157:
        r1017.add(new TLRPC.TL_secureValueTypeAddress());
        goto L89
    L158:
        r1017.add(new TLRPC.TL_secureValueTypeEmail());
        goto L89
    L159:
        r1017.add(new TLRPC.TL_secureValueTypePhone());
        goto L89
    L160:
        r1017.add(new TLRPC.TL_secureValueTypeRentalAgreement());
        goto L89
    L161:
        r1017.add(new TLRPC.TL_secureValueTypePassportRegistration());
        goto L89
    L162:
        r1017.add(new TLRPC.TL_secureValueTypeBankStatement());
        goto L89
    L97:
        if (r15.equals("passport") == false) goto L148;
        r8 = '\f';
        goto L148
    L101:
        if (r15.equals("driver_license") == false) goto L148;
        r8 = 11;
        goto L148
    L105:
        if (r15.equals("personal_details") == false) goto L148;
        r8 = '\n';
        goto L148
    L109:
        if (r15.equals("internal_passport") == false) goto L148;
        r8 = '\t';
        goto L148
    L113:
        if (r15.equals("utility_bill") == false) goto L148;
        r8 = '\b';
        goto L148
    L117:
        if (r15.equals("identity_card") == false) goto L148;
        r8 = 7;
        goto L148
    L121:
        if (r15.equals("temporary_registration") == false) goto L148;
        r8 = 6;
        goto L148
    L125:
        if (r15.equals("address_information") == false) goto L148;
        r8 = 5;
        goto L148
    L129:
        if (r15.equals("email") == false) goto L148;
        r8 = 4;
        goto L148
    L133:
        if (r15.equals("phone_number") == false) goto L148;
        r8 = 3;
        goto L148
    L137:
        if (r15.equals("rental_agreement") == false) goto L148;
        r8 = 2;
        goto L148
    L141:
        if (r15.equals("passport_registration") == false) goto L148;
        r8 = 1;
        goto L148
    L145:
        if (r15.equals("bank_statement") == false) goto L148;
        r8 = 0;
        goto L148
    L163:
        ((TLRPC.TL_messageActionSecureValuesSent) r0.action).types = r1017;
        goto L254
    L165:
        if ("joined_telegram".equals(r11.action.actionType) == false) goto L168;
        r0.action = new TLRPC.TL_messageActionContactSignUp();
        goto L254
    L168:
        if ("proximity_reached".equals(r11.action.actionType) == false) goto L171;
        TLRPC.TL_messageActionGeoProximityReached r16 = new TLRPC.TL_messageActionGeoProximityReached();
        r0.action = r16;
        r16.from_id = MessagesController.getInstance(this.currentAccount).getPeer(r11.action.from_id);
        r16.to_id = MessagesController.getInstance(this.currentAccount).getPeer(r11.action.to_id);
        r16.distance = r11.action.distance;
        goto L254
    L171:
        if ("requested_phone_number".equals(r11.action.actionType) == false) goto L174;
        r0.action = new TLRPC.TL_messageActionPhoneNumberRequest();
        goto L254
    L174:
        if ("group_call".equals(r11.action.actionType) == false) goto L177;
        TLRPC.TL_messageActionGroupCall r1018 = new TLRPC.TL_messageActionGroupCall();
        r0.action = r1018;
        r1018.duration = r11.action.duration;
        goto L254
    L177:
        if ("invite_to_group_call".equals(r11.action.actionType) == false) goto L184;
        TLRPC.TL_messageActionInviteToGroupCall r1019 = new TLRPC.TL_messageActionInviteToGroupCall();
        r0.action = r1019;
        r1019.duration = r11.action.duration;
        ArrayList<Long> r1020 = new ArrayList();
        Iterator<String> r114 = r11.action.values.iterator();
    L180:
        if (r114.hasNext() == false) goto L182;
        r1020.add(Utilities.parseLong(r114.next()));
        goto L180
    L182:
        r0.action.users = r1020;
        goto L254
    L184:
        if ("set_messages_ttl".equals(r11.action.actionType) == false) goto L187;
        TLRPC.TL_messageActionSetMessagesTTL r1021 = new TLRPC.TL_messageActionSetMessagesTTL();
        r0.action = r1021;
        r1021.ttl = r11.action.period;
        goto L254
    L187:
        if ("group_call_scheduled".equals(r11.action.actionType) == false) goto L190;
        TLRPC.TL_messageActionGroupCallScheduled r1022 = new TLRPC.TL_messageActionGroupCallScheduled();
        r0.action = r1022;
        r1022.schedule_date = r11.action.schedule_date;
        goto L254
    L190:
        if ("edit_chat_theme".equals(r11.action.actionType) == false) goto L195;
        r0.action = new TLRPC.TL_messageActionSetChatTheme();
        if (TextUtils.isEmpty(r11.action.emotion) == true) goto L254;
        ((TLRPC.TL_chatTheme) ((TLRPC.TL_messageActionSetChatTheme) r0.action).theme).emoticon = r11.action.emotion;
        goto L254
    L195:
        if ("join_group_by_request".equals(r11.action.actionType) == false) goto L198;
        r0.action = new TLRPC.TL_messageActionChatJoinedByRequest();
        goto L254
    L198:
        if ("send_webview_data".equals(r11.action.actionType) == false) goto L202;
        TLRPC.TL_messageActionWebViewDataSent r1023 = new TLRPC.TL_messageActionWebViewDataSent();
        r0.action = r1023;
        r1023.text = r11.action.text;
        return r0;
    L202:
        if ("send_premium_gift".equals(r11.action.actionType) == false) goto L205;
        TLRPC.TL_messageActionGiftPremium r1024 = new TLRPC.TL_messageActionGiftPremium();
        r0.action = r1024;
        Action r115 = r11.action;
        r1024.amount = r115.amount;
        r1024.currency = r115.currency;
        r1024.months = r115.months;
        goto L254
    L205:
        if ("topic_created".equals(r11.action.actionType) == false) goto L208;
        TLRPC.TL_messageActionTopicCreate r1025 = new TLRPC.TL_messageActionTopicCreate();
        r0.action = r1025;
        r1025.title = r11.action.title;
        goto L254
    L208:
        if ("topic_edit".equals(r11.action.actionType) == false) goto L211;
        TLRPC.TL_messageActionTopicEdit r1026 = new TLRPC.TL_messageActionTopicEdit();
        r0.action = r1026;
        r1026.title = r11.action.title;
        r1026.icon_emoji_id = r11.new_icon_emoji_id;
        goto L254
    L211:
        if ("suggest_profile_photo".equals(r11.action.actionType) == false) goto L214;
        TLRPC.TL_messageActionSuggestProfilePhoto r1027 = new TLRPC.TL_messageActionSuggestProfilePhoto();
        r0.action = r1027;
        r1027.title = r11.action.title;
        r1027.photo = new TLRPC.TL_photo();
        goto L254
    L214:
        if ("requested_peer".equals(r11.action.actionType) == false) goto L221;
        TLRPC.TL_messageActionRequestedPeer r17 = new TLRPC.TL_messageActionRequestedPeer();
        r0.action = r17;
        r17.button_id = r11.action.button_id;
        ArrayList<TLRPC.Peer> r18 = new ArrayList();
        Iterator<String> r116 = r11.action.peers.iterator();
    L217:
        if (r116.hasNext() == false) goto L219;
        r18.add(MessagesController.getInstance(this.currentAccount).getPeer(Utilities.parseLong(r116.next()).longValue()));
        goto L217
    L219:
        ((TLRPC.TL_messageActionRequestedPeer) r0.action).peers = r18;
        goto L254
    L221:
        if ("gift_code_prize".equals(r11.action.actionType) == false) goto L225;
        TLRPC.TL_messageActionGiftCode r19 = new TLRPC.TL_messageActionGiftCode();
        r0.action = r19;
        r19.slug = r11.action.gift_code;
        r19.boost_peer = MessagesController.getInstance(this.currentAccount).getPeer(r11.action.boost_peer_id);
        Action r1028 = r11.action;
        r19.unclaimed = r1028.is_unclaimed;
        r19.via_giveaway = r1028.via_giveaway;
        r0.action.months = r1028.months;
        return r0;
    L225:
        if ("giveaway_launch".equals(r11.action.actionType) == false) goto L229;
        r0.action = new TLRPC.TL_messageActionGiveawayLaunch();
        return r0;
    L229:
        if ("giveaway_results".equals(r11.action.actionType) == false) goto L233;
        TLRPC.TL_messageActionGiveawayResults r1029 = new TLRPC.TL_messageActionGiveawayResults();
        r0.action = r1029;
        Action r117 = r11.action;
        r1029.winners_count = r117.winners_count;
        r1029.unclaimed_count = r117.unclaimed_count;
        r1029.stars = r117.stars_boolean;
        return r0;
    L233:
        if ("set_same_chat_wallpaper".equals(r11.action.actionType) == false) goto L235;
    L253:
        TLRPC.TL_messageActionSetChatWallPaper r1030 = new TLRPC.TL_messageActionSetChatWallPaper();
        r0.action = r1030;
        r1030.same = "set_same_chat_wallpaper".equals(r11.action.actionType);
        goto L254
    L235:
        if ("set_chat_wallpaper".equals(r11.action.actionType) == true) goto L253;
        if ("boost_apply".equals(r11.action.actionType) == false) goto L241;
        TLRPC.TL_messageActionBoostApply r1031 = new TLRPC.TL_messageActionBoostApply();
        r0.action = r1031;
        r1031.boosts = r11.action.boosts;
        goto L254
    L241:
        if ("refunded_payment".equals(r11.action.actionType) == false) goto L245;
        TLRPC.TL_messageActionPaymentRefunded r110 = new TLRPC.TL_messageActionPaymentRefunded();
        r0.action = r110;
        Action r22 = r11.action;
        r110.amount = r22.amount;
        r110.currency = r22.currency;
        r110.peer = MessagesController.getInstance(this.currentAccount).getPeer(r11.action.peer);
        TLRPC.TL_messageActionPaymentRefunded r1032 = (TLRPC.TL_messageActionPaymentRefunded) r0.action;
        TLRPC.TL_paymentCharge r111 = new TLRPC.TL_paymentCharge();
        r1032.charge = r111;
        r111.id = r11.action.charge_id;
        return r0;
    L245:
        if ("send_stars_gift".equals(r11.action.actionType) == false) goto L248;
        TLRPC.TL_messageActionGiftStars r1033 = new TLRPC.TL_messageActionGiftStars();
        r0.action = r1033;
        Action r118 = r11.action;
        r1033.stars = r118.stars;
        r1033.amount = r118.amount;
        r1033.currency = r118.currency;
        goto L254
    L248:
        if ("stars_prize".equals(r11.action.actionType) == false) goto L251;
        TLRPC.TL_messageActionPrizeStars r119 = new TLRPC.TL_messageActionPrizeStars();
        r0.action = r119;
        r119.amount = r11.action.stars;
        r119.boost_peer = MessagesController.getInstance(this.currentAccount).getPeer(r11.action.boost_peer_id);
        Action r1034 = r11.action;
        r119.unclaimed = r1034.is_unclaimed;
        r119.giveaway_msg_id = r1034.giveaway_msg_id;
        r119.transaction_id = r1034.transaction_id;
        goto L254
    L251:
        if ("send_star_gift".equals(r11.action.actionType) == false) goto L254;
        r0.action = new TLRPC.TL_messageActionStarGift();
        Pair<String, ArrayList<TLRPC.MessageEntity>> r1035 = getMessageFromEntities(r11.action.giftText);
        TLRPC.TL_messageActionStarGift r120 = (TLRPC.TL_messageActionStarGift) r0.action;
        TLRPC.TL_textWithEntities r23 = new TLRPC.TL_textWithEntities();
        r120.message = r23;
        r23.text = (String) r1035.first;
        r23.entities = (ArrayList) r1035.second;
        TL_stars.TL_starGift r1036 = new TL_stars.TL_starGift();
        r120.gift = r1036;
        r1036.id = r11.action.gift_id;
        r1036.sticker = new TLRPC.TL_document();
        r120.convert_stars = r11.action.stars;
        goto L254
    L5:
        if (r1.isEmpty() == true) goto L7;
        r3 = Utilities.parseLong(r11.action.actor_id.substring(4)).longValue();
        goto L9
    }

    public ArrayList<MessageObject> mapMessages(JsonMessage[] r11) {
        ArrayList<MessageObject> r0 = new ArrayList();
        int r1 = r11.length - 1;
    L3:
        if (r1 < 0) goto L29;
        JsonMessage r3 = r11[r1];
        if (r3 == null) goto L28;
        if ("service".equals(r3.type) == false) goto L10;
        r0.add(new MessageObject(this.currentAccount, mapService(r3), true, true));
        goto L28
    L10:
        TLRPC.TL_message r4 = new TLRPC.TL_message();
        int r5 = r3.id;
        r4.id = r5;
        r4.realId = r5;
        r4.date = r3.date;
        TLRPC.TL_peerUser r52 = new TLRPC.TL_peerUser();
        String r6 = this.chatInfo.type;
        if (r6 != null) goto L13;
    L15:
        String r62 = r3.from_id;
        if (r62 == null) goto L18;
        r52.user_id = Utilities.parseLong(r62.substring(4)).longValue();
    L18:
        r4.from_id = r52;
        r4.peer_id = MessagesController.getInstance(UserConfig.selectedAccount).getPeer(r52.user_id);
        if (r52.user_id != UserConfig.getInstance(UserConfig.selectedAccount).clientUserId) goto L21;
        boolean r63 = true;
    L22:
        r4.out = r63;
        r4.dialog_id = r52.user_id;
        Pair<String, ArrayList<TLRPC.MessageEntity>> r53 = getMessageFromEntities(r3.text_entities);
        r4.message = (String) r53.first;
        r4.entities = (ArrayList) r53.second;
        if (r3.media == null) goto L27;
        r4.media = mapMedia(r3);
        if (r3.media.skipReason == null) goto L27;
        r4.message += "\n\n" + r3.media.skipReason;
    L27:
        r0.add(new MessageObject(this.currentAccount, r4, true, false));
        goto L28
    L21:
        r63 = false;
        goto L22
    L13:
        if (r6.contains("bot_chat") == false) goto L15;
        r52.user_id = this.chatInfo.id;
    L28:
        r1 = r1 - 1;
        goto L3
    L29:
        return r0;
    }
}
