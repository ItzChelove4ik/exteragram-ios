package org.telegram.messenger;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.android.dx.AppDataDirGuesser;
import com.google.android.gms.cast.MediaError;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public class AppGlobalConfig {
    public final ConfigInt aicomposeToneExamplesNum;
    public final ConfigInt aicomposeTonePromptLengthMax;
    public final ConfigInt aicomposeToneSavedLimitDefault;
    public final ConfigInt aicomposeToneSavedLimitPremium;
    public final ConfigInt aicomposeToneTitleLengthMax;
    public final ConfigInt botsCreateLimitDefault;
    public final ConfigInt botsCreateLimitPremium;
    public final ConfigInt communityBotPeersLimit;
    public final ConfigInt communityPeersLimit;
    public final ConfigInt contactNoteLengthLimit;
    public final ConfigBoolean disableBlurInDarkTheme;
    public final ConfigBoolean disableBlurInLightTheme;
    public final ConfigInt ephemeralWelcomeMessagesMax;
    public final ConfigInt groupCallMessageLengthLimit;
    public final ConfigTime groupCallMessageTtl;
    private final HashMap<String, ConfigInternal> map;
    public final ConfigInt messageLengthLimitDefault;
    public final ConfigInt messageLengthLimitPremium;
    public final ConfigBoolean messagePrimaryEditedDate;
    public final ConfigTime messageTypingDraftTtl;
    public final ConfigString musicSearchUsername;
    public final ConfigBoolean needAgeVideoVerification;
    public final ConfigTime noForwardsRequestExpirePeriod;
    public final ConfigInt passkeysAccountPasskeysMax;
    public final ConfigString phoneCountryIso2;
    public final ConfigTime pollAnswerDeletePeriod;
    public final ConfigInt pollAnswerLengthMax;
    public final ConfigInt pollAnswersMax;
    public final ConfigInt pollCaptionLengthMax;
    public final ConfigTime pollClosePeriodMax;
    public final ConfigInt pollCountriesMax;
    public final ConfigInt pollQuestionLengthMax;
    public final ConfigInt pollSolutionLengthMax;
    public final ConfigInt quickReplyMessagesLimit;
    public final ConfigInt richMessageLengthLimit;
    public final ConfigInt richMessageMaxBlocks;
    public final ConfigInt richMessageMaxDepth;
    public final ConfigInt richMessageMaxMedia;
    public final ConfigInt richMessageMaxTableCols;
    public final ConfigString richMessagePosting;
    public final ConfigBoolean settingsDisplayPasskeys;
    public final ConfigInt stargiftsCollectionGiftsLimit;
    public final ConfigInt stargiftsCollectionsLimit;
    public final ConfigInt starsPaidMessagesChannelAmountDefault;
    public final ConfigString starsRatingLearnMoreUrl;
    public final ConfigBoolean starsSpendTopUpInvoiceDisabled;
    public final ConfigInt starsStarGiftResaleAmountMax;
    public final ConfigInt starsStarGiftResaleAmountMin;
    public final ConfigInt starsStarGiftResaleCommissionPermille;
    public final ConfigTime starsSuggestedPostAgeMin;
    public final ConfigInt starsSuggestedPostAmountMax;
    public final ConfigInt starsSuggestedPostAmountMin;
    public final ConfigInt starsSuggestedPostCommissionPermille;
    public final ConfigTime starsSuggestedPostFutureMax;
    public final ConfigTime starsSuggestedPostFutureMin;
    public final ConfigInt storiesAlbumStoriesLimit;
    public final ConfigInt storiesAlbumsLimit;
    public final ConfigLong tonStarGiftResaleAmountMax;
    public final ConfigLong tonStarGiftResaleAmountMin;
    public final ConfigInt tonStarGiftResaleCommissionPermille;
    public final ConfigLong tonSuggestedPostAmountMax;
    public final ConfigLong tonSuggestedPostAmountMin;
    public final ConfigInt tonSuggestedPostCommissionPermille;
    public final ConfigDouble tonUsdRate;

    public static class ConfigBoolean {
        private final Internal handler;

        public static class Internal implements ConfigInternal {
            private final boolean defaultValue;
            private final String name;
            private boolean value;

            /* renamed from: -$$Nest$fgetvalue, reason: not valid java name */
            public static /* bridge */ /* synthetic */ boolean m3877$$Nest$fgetvalue(Internal r0) {
                return r0.value;
            }

            private Internal(String r1, boolean r2) {
                this.name = r1;
                this.defaultValue = r2;
            }

            public /* synthetic */ Internal(String r1, boolean r2, AppGlobalConfigIA r3) {
                this(r1, r2);
            }

            @Override // org.telegram.messenger.AppGlobalConfig.ConfigInternal
            public boolean apply(SharedPreferences.Editor r2, TLRPC.JSONValue r3) {
                if ((r3 instanceof TLRPC.TL_jsonBool) == false) goto L8;
                boolean r32 = ((TLRPC.TL_jsonBool) r3).value;
                if (r32 == this.value) goto L10;
                this.value = r32;
                r2.putBoolean(this.name, r32);
                return true;
            L10:
                return false;
            L8:
                return false;
            }

            @Override // org.telegram.messenger.AppGlobalConfig.ConfigInternal
            public void load(SharedPreferences r3) {
                this.value = r3.getBoolean(this.name, this.defaultValue);
            }
        }

        /* renamed from: -$$Nest$fgethandler, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Internal m3876$$Nest$fgethandler(ConfigBoolean r0) {
            return r0.handler;
        }

        private ConfigBoolean(String r3, boolean r4) {
            this.handler = new Internal(r3, r4, null);
        }

        public /* synthetic */ ConfigBoolean(String r1, boolean r2, AppGlobalConfigIA r3) {
            this(r1, r2);
        }

        public boolean get() {
            return Internal.m3877$$Nest$fgetvalue(this.handler);
        }
    }

    public static class ConfigDouble {
        private final Internal handler;

        public static class Internal implements ConfigInternal {
            private final double defaultValue;
            private final String name;
            private double value;

            /* renamed from: -$$Nest$fgetvalue, reason: not valid java name */
            public static /* bridge */ /* synthetic */ double m3879$$Nest$fgetvalue(Internal r2) {
                return r2.value;
            }

            private Internal(String r1, double r2) {
                this.name = r1;
                this.defaultValue = r2;
            }

            public /* synthetic */ Internal(String r1, double r2, AppGlobalConfigIA r4) {
                this(r1, r2);
            }

            @Override // org.telegram.messenger.AppGlobalConfig.ConfigInternal
            public boolean apply(SharedPreferences.Editor r5, TLRPC.JSONValue r6) {
                if ((r6 instanceof TLRPC.TL_jsonNumber) == false) goto L8;
                double r0 = ((TLRPC.TL_jsonNumber) r6).value;
                if (r0 == this.value) goto L10;
                this.value = r0;
                r5.putFloat(this.name, (float) r0);
                return true;
            L10:
                return false;
            L8:
                return false;
            }

            @Override // org.telegram.messenger.AppGlobalConfig.ConfigInternal
            public void load(SharedPreferences r4) {
                this.value = r4.getFloat(this.name, (float) this.defaultValue);
            }
        }

        /* renamed from: -$$Nest$fgethandler, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Internal m3878$$Nest$fgethandler(ConfigDouble r0) {
            return r0.handler;
        }

        private ConfigDouble(String r3, double r4) {
            this.handler = new Internal(r3, r4, null);
        }

        public /* synthetic */ ConfigDouble(String r1, double r2, AppGlobalConfigIA r4) {
            this(r1, r2);
        }

        public double get() {
            return Internal.m3879$$Nest$fgetvalue(this.handler);
        }
    }

    public static class ConfigInt {
        private final Internal handler;

        public static class Internal implements ConfigInternal {
            private final int defaultValue;
            private final String name;
            private int value;

            /* renamed from: -$$Nest$fgetvalue, reason: not valid java name */
            public static /* bridge */ /* synthetic */ int m3881$$Nest$fgetvalue(Internal r0) {
                return r0.value;
            }

            private Internal(String r1, int r2) {
                this.name = r1;
                this.defaultValue = r2;
            }

            public /* synthetic */ Internal(String r1, int r2, AppGlobalConfigIA r3) {
                this(r1, r2);
            }

            @Override // org.telegram.messenger.AppGlobalConfig.ConfigInternal
            public boolean apply(SharedPreferences.Editor r5, TLRPC.JSONValue r6) {
                if ((r6 instanceof TLRPC.TL_jsonNumber) == false) goto L8;
                double r0 = ((TLRPC.TL_jsonNumber) r6).value;
                if (r0 == this.value) goto L10;
                int r62 = (int) r0;
                this.value = r62;
                r5.putInt(this.name, r62);
                return true;
            L10:
                return false;
            L8:
                return false;
            }

            @Override // org.telegram.messenger.AppGlobalConfig.ConfigInternal
            public void load(SharedPreferences r3) {
                this.value = r3.getInt(this.name, this.defaultValue);
            }
        }

        /* renamed from: -$$Nest$fgethandler, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Internal m3880$$Nest$fgethandler(ConfigInt r0) {
            return r0.handler;
        }

        private ConfigInt(String r3, int r4) {
            this.handler = new Internal(r3, r4, null);
        }

        public /* synthetic */ ConfigInt(String r1, int r2, AppGlobalConfigIA r3) {
            this(r1, r2);
        }

        public int get() {
            return Internal.m3881$$Nest$fgetvalue(this.handler);
        }
    }

    public interface ConfigInternal {
        boolean apply(SharedPreferences.Editor r1, TLRPC.JSONValue r2);

        void load(SharedPreferences r1);
    }

    public static class ConfigLong {
        private final Internal handler;

        public static class Internal implements ConfigInternal {
            private final long defaultValue;
            private final String name;
            private long value;

            /* renamed from: -$$Nest$fgetvalue, reason: not valid java name */
            public static /* bridge */ /* synthetic */ long m3883$$Nest$fgetvalue(Internal r2) {
                return r2.value;
            }

            private Internal(String r1, long r2) {
                this.name = r1;
                this.defaultValue = r2;
            }

            public /* synthetic */ Internal(String r1, long r2, AppGlobalConfigIA r4) {
                this(r1, r2);
            }

            @Override // org.telegram.messenger.AppGlobalConfig.ConfigInternal
            public boolean apply(SharedPreferences.Editor r5, TLRPC.JSONValue r6) {
                if ((r6 instanceof TLRPC.TL_jsonNumber) == false) goto L8;
                double r0 = ((TLRPC.TL_jsonNumber) r6).value;
                if (r0 == this.value) goto L10;
                long r02 = (long) r0;
                this.value = r02;
                r5.putLong(this.name, r02);
                return true;
            L10:
                return false;
            L8:
                return false;
            }

            @Override // org.telegram.messenger.AppGlobalConfig.ConfigInternal
            public void load(SharedPreferences r4) {
                this.value = r4.getLong(this.name, this.defaultValue);
            }
        }

        /* renamed from: -$$Nest$fgethandler, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Internal m3882$$Nest$fgethandler(ConfigLong r0) {
            return r0.handler;
        }

        private ConfigLong(String r3, long r4) {
            this.handler = new Internal(r3, r4, null);
        }

        public /* synthetic */ ConfigLong(String r1, long r2, AppGlobalConfigIA r4) {
            this(r1, r2);
        }

        public long get() {
            return Internal.m3883$$Nest$fgetvalue(this.handler);
        }
    }

    public static class ConfigString {
        private final Internal handler;

        public static class Internal implements ConfigInternal {
            private final String defaultValue;
            private final String name;
            private String value;

            /* renamed from: -$$Nest$fgetvalue, reason: not valid java name */
            public static /* bridge */ /* synthetic */ String m3885$$Nest$fgetvalue(Internal r0) {
                return r0.value;
            }

            private Internal(String r1, String r2) {
                this.name = r1;
                this.defaultValue = r2;
            }

            public /* synthetic */ Internal(String r1, String r2, AppGlobalConfigIA r3) {
                this(r1, r2);
            }

            @Override // org.telegram.messenger.AppGlobalConfig.ConfigInternal
            public boolean apply(SharedPreferences.Editor r3, TLRPC.JSONValue r4) {
                if ((r4 instanceof TLRPC.TL_jsonString) == false) goto L8;
                TLRPC.TL_jsonString r42 = (TLRPC.TL_jsonString) r4;
                if (TextUtils.equals(r42.value, this.value) == true) goto L10;
                String r43 = r42.value;
                this.value = r43;
                r3.putString(this.name, r43);
                return true;
            L10:
                return false;
            L8:
                return false;
            }

            @Override // org.telegram.messenger.AppGlobalConfig.ConfigInternal
            public void load(SharedPreferences r3) {
                this.value = r3.getString(this.name, this.defaultValue);
            }
        }

        /* renamed from: -$$Nest$fgethandler, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Internal m3884$$Nest$fgethandler(ConfigString r0) {
            return r0.handler;
        }

        private ConfigString(String r3, String r4) {
            this.handler = new Internal(r3, r4, null);
        }

        public /* synthetic */ ConfigString(String r1, String r2, AppGlobalConfigIA r3) {
            this(r1, r2);
        }

        public String get() {
            return Internal.m3885$$Nest$fgetvalue(this.handler);
        }

        public boolean is(String r1) {
            return TextUtils.equals(get(), r1);
        }
    }

    public static class ConfigTime {
        private final ConfigLong.Internal handler;
        private final TimeUnit timeUnit;

        /* renamed from: -$$Nest$fgethandler, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ConfigLong.Internal m3886$$Nest$fgethandler(ConfigTime r0) {
            return r0.handler;
        }

        private ConfigTime(String r3, TimeUnit r4, long r5) {
            this.handler = new ConfigLong.Internal(r3, r5, null);
            this.timeUnit = r4;
        }

        public /* synthetic */ ConfigTime(String r1, TimeUnit r2, long r3, AppGlobalConfigIA r5) {
            this(r1, r2, r3);
        }

        public long get(TimeUnit r3) {
            return r3.convert(ConfigLong.Internal.m3883$$Nest$fgetvalue(this.handler), this.timeUnit);
        }
    }

    public AppGlobalConfig() {
        this.map = new HashMap();
        this.starsPaidMessagesChannelAmountDefault = ofInt("stars_paid_messages_channel_amount_default", 10);
        this.starsSuggestedPostCommissionPermille = ofInt("stars_suggested_post_commission_permille", 850);
        this.starsSuggestedPostAmountMin = ofInt("stars_suggested_post_amount_min", 5);
        this.starsSuggestedPostAmountMax = ofInt("stars_suggested_post_amount_max", AppDataDirGuesser.PER_USER_RANGE);
        this.tonSuggestedPostCommissionPermille = ofInt("ton_suggested_post_commission_permille", 850);
        this.tonSuggestedPostAmountMin = ofLong("ton_suggested_post_amount_min", 10000000);
        this.tonSuggestedPostAmountMax = ofLong("ton_suggested_post_amount_max", 10000000000000L);
        TimeUnit r1 = TimeUnit.SECONDS;
        this.starsSuggestedPostAgeMin = ofTime("stars_suggested_post_age_min", 86400, r1);
        this.starsSuggestedPostFutureMin = ofTime("stars_suggested_post_future_min", 300, r1);
        this.starsSuggestedPostFutureMax = ofTime("stars_suggested_post_future_max", 2678400, r1);
        this.tonUsdRate = ofDouble("ton_usd_rate", 3.0d);
        this.starsRatingLearnMoreUrl = ofString("stars_rating_learnmore_url", "https://telegram.org/blog/telegram-stars");
        this.needAgeVideoVerification = ofBoolean("need_age_video_verification", false);
        this.starsStarGiftResaleCommissionPermille = ofInt("stars_stargift_resale_commission_permille", 800);
        this.tonStarGiftResaleCommissionPermille = ofInt("ton_stargift_resale_commission_permille", 800);
        this.starsStarGiftResaleAmountMin = ofInt("stars_stargift_resale_amount_min", 125);
        this.starsStarGiftResaleAmountMax = ofInt("stars_stargift_resale_amount_max", 35000);
        this.tonStarGiftResaleAmountMin = ofLong("ton_stargift_resale_amount_min", 10000000);
        this.tonStarGiftResaleAmountMax = ofLong("ton_stargift_resale_amount_max", 10000000000000L);
        this.stargiftsCollectionsLimit = ofInt("stargifts_collections_limit", 100);
        this.stargiftsCollectionGiftsLimit = ofInt("stargifts_collection_gifts_limit", 100);
        this.storiesAlbumsLimit = ofInt("stories_albums_limit", 100);
        this.storiesAlbumStoriesLimit = ofInt("stories_album_stories_limit", 100);
        this.messageTypingDraftTtl = ofTime("message_typing_draft_ttl", 30, r1);
        this.groupCallMessageTtl = ofTime("group_call_message_ttl", 10, r1);
        this.groupCallMessageLengthLimit = ofInt("group_call_message_length_limit", 128);
        this.contactNoteLengthLimit = ofInt("contact_note_length_limit", 128);
        this.passkeysAccountPasskeysMax = ofInt("passkeys_account_passkeys_max", 5);
        this.settingsDisplayPasskeys = ofBoolean("settings_display_passkeys", BuildVars.DEBUG_VERSION);
        this.disableBlurInLightTheme = ofBoolean("android_disable_blur_in_light_theme", false);
        this.disableBlurInDarkTheme = ofBoolean("android_disable_blur_in_dark_theme", false);
        this.noForwardsRequestExpirePeriod = ofTime("no_forwards_request_expire_period", 86400, r1);
        this.pollClosePeriodMax = ofTime("poll_close_period_max", 2592000, r1);
        this.musicSearchUsername = ofString("music_search_username", null);
        this.pollAnswersMax = ofInt("poll_answers_max", 12);
        this.pollCountriesMax = ofInt("poll_countries_max", 12);
        this.pollAnswerLengthMax = ofInt("poll_answer_length_max", 100);
        this.pollQuestionLengthMax = ofInt("poll_question_length_max", 255);
        this.pollSolutionLengthMax = ofInt("poll_solution_length_max", 200);
        this.pollCaptionLengthMax = ofInt("poll_caption_length_max", 300);
        this.pollAnswerDeletePeriod = ofTime("poll_answer_delete_period", 300, r1);
        this.botsCreateLimitDefault = ofInt("bots_create_limit_default", 20);
        this.botsCreateLimitPremium = ofInt("bots_create_limit_premium", 40);
        this.phoneCountryIso2 = ofString("phone_country_iso2", "en");
        this.aicomposeToneExamplesNum = ofInt("aicompose_tone_examples_num", 3);
        this.aicomposeToneTitleLengthMax = ofInt("aicompose_tone_title_length_max", 12);
        this.aicomposeTonePromptLengthMax = ofInt("aicompose_tone_prompt_length_max", 1024);
        this.aicomposeToneSavedLimitDefault = ofInt("aicompose_tone_saved_limit_default", 5);
        this.aicomposeToneSavedLimitPremium = ofInt("aicompose_tone_saved_limit_premium", 20);
        this.messagePrimaryEditedDate = ofBoolean("message_primary_edited_date", false);
        this.richMessageLengthLimit = ofInt("rich_message_length_limit", 32768);
        this.richMessageMaxBlocks = ofInt("rich_message_max_blocks", MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
        this.richMessageMaxDepth = ofInt("rich_message_max_depth", 16);
        this.richMessageMaxMedia = ofInt("rich_message_max_media", 50);
        this.richMessageMaxTableCols = ofInt("rich_message_max_table_cols", 20);
        this.richMessagePosting = ofString("rich_message_posting", "premium");
        this.communityPeersLimit = ofInt("community_peers_limit", 100);
        this.communityBotPeersLimit = ofInt("community_bot_peers_limit", 100);
        this.messageLengthLimitDefault = ofInt("message_length_limit_default", 4096);
        this.messageLengthLimitPremium = ofInt("message_length_limit_premium", 8192);
        this.quickReplyMessagesLimit = ofInt("quick_reply_messages_limit", 20);
        this.ephemeralWelcomeMessagesMax = ofInt("ephemeral_welcome_messages_max", 5);
        this.starsSpendTopUpInvoiceDisabled = ofBoolean("stars_spend_topup_invoice_disabled", false);
    }

    public static AppGlobalConfig getInstance(int r0) {
        return MessagesController.getInstance(r0).config;
    }

    private ConfigBoolean ofBoolean(String r3, boolean r4) {
        ConfigBoolean r0 = new ConfigBoolean(r3, r4, null);
        this.map.put(r3, ConfigBoolean.m3876$$Nest$fgethandler(r0));
        return r0;
    }

    private ConfigDouble ofDouble(String r3, double r4) {
        ConfigDouble r0 = new ConfigDouble(r3, r4, null);
        this.map.put(r3, ConfigDouble.m3878$$Nest$fgethandler(r0));
        return r0;
    }

    private ConfigInt ofInt(String r3, int r4) {
        ConfigInt r0 = new ConfigInt(r3, r4, null);
        this.map.put(r3, ConfigInt.m3880$$Nest$fgethandler(r0));
        return r0;
    }

    private ConfigLong ofLong(String r3, long r4) {
        ConfigLong r0 = new ConfigLong(r3, r4, null);
        this.map.put(r3, ConfigLong.m3882$$Nest$fgethandler(r0));
        return r0;
    }

    private ConfigString ofString(String r3, String r4) {
        ConfigString r0 = new ConfigString(r3, r4, null);
        this.map.put(r3, ConfigString.m3884$$Nest$fgethandler(r0));
        return r0;
    }

    private ConfigTime ofTime(String r7, long r8, TimeUnit r10) {
        ConfigTime r0 = new ConfigTime(r7, r10, r8, null);
        this.map.put(r7, ConfigTime.m3886$$Nest$fgethandler(r0));
        return r0;
    }

    public boolean apply(SharedPreferences.Editor r7, TLRPC.TL_jsonObject r8) {
        int r0 = r8.value.size();
        int r1 = 0;
        boolean r2 = false;
    L3:
        if (r1 >= r0) goto L9;
        TLRPC.TL_jsonObjectValue r3 = r8.value.get(r1);
        ConfigInternal r4 = this.map.get(r3.key);
        if (r4 == null) goto L8;
        r2 = r2 | r4.apply(r7, r3.value);
    L8:
        r1 = r1 + 1;
        goto L3
    L9:
        return r2;
    }

    public void load(SharedPreferences r2) {
        Iterator<ConfigInternal> r1 = this.map.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L10;
        r1.next().load(r2);     // Catch: ClassCastException -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L4
    }
}
