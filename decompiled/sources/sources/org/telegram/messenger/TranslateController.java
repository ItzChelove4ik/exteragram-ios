package org.telegram.messenger;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.icu.text.Collator;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.Pair;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.ai.AiController;
import com.exteragram.messenger.ai.TelegramAiReplacement;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.translator.ChatTranslationBridge;
import com.exteragram.messenger.translator.ChatTranslator;
import com.exteragram.messenger.translator.TranslationProviders;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.exteragram.messenger.translator.providers.TelegramTranslator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.TranslateAlert2;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.RestrictedLanguagesSelectActivity;

/* loaded from: classes.dex */
public class TranslateController extends BaseController {
    private static final int GROUPING_TRANSLATIONS_TIMEOUT = 80;
    private static final int MAX_MESSAGES_PER_REQUEST = 20;
    private static final int MAX_SYMBOLS_PER_REQUEST = 25000;
    private static final float REQUIRED_MIN_MESSAGES_TRANSLATABLE_AUTOTRANSLATE = 2.0f;
    private static final float REQUIRED_MIN_PERCENTAGE_MESSAGES_UNKNOWN = 0.65f;
    private static final float REQUIRED_MIN_PERCENTAGE_MESSAGES_UNKNOWN_AUTOTRANSLATE = 0.8f;
    private static final float REQUIRED_PERCENTAGE_MESSAGES_TRANSLATABLE = 0.6f;
    private static final int REQUIRED_TOTAL_MESSAGES_CHECKED = 6;
    private static final int REQUIRED_TOTAL_MESSAGES_CHECKED_AUTOTRANSLATE = 2;
    private static final long SUMMARY_ERROR_BULLETIN_INTERVAL = 5000;
    public static final String UNKNOWN_LANGUAGE = "und";
    private static final List<String> allLanguages = null;
    private static final List<String> languagesOrder = null;
    private static LinkedHashSet<String> suggestedLanguageCodes;
    private Boolean chatTranslateEnabled;
    private Boolean contextTranslateEnabled;
    private final HashMap<Long, String> detectedDialogLanguage;
    private final HashSet<MessageKey> detectingPhotos;
    private final HashSet<StoryKey> detectingStories;
    private final Set<Long> hideTranslateDialogs;
    private final HashMap<Long, HashMap<Integer, MessageObject>> keptReplyMessageObjects;
    private long lastSummaryErrorBulletinTime;
    private final HashSet<Integer> loadingSummarizations;
    private final Set<Integer> loadingTranscriptionTranslations;
    private final Set<Integer> loadingTranslations;
    private MessagesController messagesController;
    private ArrayList<Integer> pendingLanguageChecks;
    private final HashMap<Long, ArrayList<PendingPollTranslation>> pendingPollTranslations;
    private final HashMap<Long, ArrayList<PendingRichTranslation>> pendingRichTranslations;
    private final HashMap<Long, ArrayList<PendingTranslation>> pendingTranscriptionsTranslations;
    private final HashMap<Long, ArrayList<PendingTranslation>> pendingTranslations;
    private final HashMap<Long, TranslatableDecision> translatableDialogMessages;
    private final Set<Long> translatableDialogs;
    private final HashMap<Long, String> translateDialogLanguage;
    private final LongSparseArray<Boolean> translatingDialogs;
    private final HashSet<MessageKey> translatingPhotos;
    private final HashSet<StoryKey> translatingStories;

    /* loaded from: classes5.dex */
    public static class Language {
        public String code;
        public String displayName;
        public String ownDisplayName;
        public String q;

        public Language() {
        }
    }

    /* loaded from: classes5.dex */
    public static class MessageKey {
        public long dialogId;
        public int id;

        public MessageKey(MessageObject r3) {
            this.dialogId = r3.getDialogId();
            this.id = r3.getId();
        }
    }

    /* loaded from: classes5.dex */
    public static class PendingPollTranslation {
        ArrayList<Utilities.Callback3<Integer, PollText, String>> callbacks;
        int delay;
        String language;
        ArrayList<Integer> messageIds;
        ArrayList<Pair<PollText, PollText>> messageTexts;
        int reqId;
        Runnable runnable;
        int symbolsCount;

        private PendingPollTranslation() {
            this.messageIds = new ArrayList();
            this.messageTexts = new ArrayList();
            this.callbacks = new ArrayList();
            this.delay = 80;
            this.reqId = -1;
        }

        public /* synthetic */ PendingPollTranslation(TranslateControllerIA r1) {
            this();
        }
    }

    /* loaded from: classes5.dex */
    public static class PendingRichTranslation {
        ArrayList<Utilities.Callback3<Integer, TL_iv.RichMessage, String>> callbacks;
        int delay;
        String language;
        ArrayList<Integer> messageIds;
        int reqId;
        Runnable runnable;

        private PendingRichTranslation() {
            this.messageIds = new ArrayList();
            this.callbacks = new ArrayList();
            this.delay = 80;
            this.reqId = -1;
        }

        public /* synthetic */ PendingRichTranslation(TranslateControllerIA r1) {
            this();
        }
    }

    /* loaded from: classes5.dex */
    public static class PendingTranslation {
        ArrayList<Utilities.Callback4<Boolean, Integer, TLRPC.TL_textWithEntities, String>> callbacks;
        int delay;
        String language;
        ArrayList<Integer> messageIds;
        ArrayList<TLRPC.TL_textWithEntities> messageTexts;
        int reqId;
        Runnable runnable;
        int symbolsCount;

        private PendingTranslation() {
            this.messageIds = new ArrayList();
            this.messageTexts = new ArrayList();
            this.callbacks = new ArrayList();
            this.delay = 80;
            this.reqId = -1;
        }

        public /* synthetic */ PendingTranslation(TranslateControllerIA r1) {
            this();
        }
    }

    public static class PollText extends TLObject {
        public static final int constructor = 613759672;
        public ArrayList<TLRPC.PollAnswer> answers;
        public TLRPC.TL_textWithEntities question;
        public TLRPC.TL_textWithEntities solution;

        public PollText() {
            this.answers = new ArrayList();
        }

        public static PollText TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (613759672 == r3) goto L5;
            PollText r0 = null;
        L7:
            return (PollText) TLObject.TLdeserialize(PollText.class, r0, r2, r3, r4);
        L5:
            r0 = new PollText();
            goto L7
        }

        public static PollText fromMessage(MessageObject r1) {
            TLRPC.MessageMedia r12 = MessageObject.getMedia(r1);
            if ((r12 instanceof TLRPC.TL_messageMediaPoll) == true) goto L5;
            return null;
        L5:
            return fromPoll((TLRPC.TL_messageMediaPoll) r12);
        }

        public static PollText fromPoll(TLRPC.TL_messageMediaPoll r6) {
            TLRPC.Poll r0 = r6.poll;
            PollText r1 = new PollText();
            r1.question = r0.question;
            int r2 = 0;
        L4:
            if (r2 >= r0.answers.size()) goto L6;
            TLRPC.PollAnswer r3 = r0.answers.get(r2);
            TLRPC.TL_pollAnswer r4 = new TLRPC.TL_pollAnswer();
            r4.text = r3.text;
            r4.option = r3.option;
            r1.answers.add(r4);
            r2 = r2 + 1;
            goto L4
        L6:
            TLRPC.PollResults r02 = r6.results;
            if (r02 != null) goto L9;
        L11:
            return r1;
        L9:
            if (TextUtils.isEmpty(r02.solution) == true) goto L11;
            TLRPC.TL_textWithEntities r03 = new TLRPC.TL_textWithEntities();
            r1.solution = r03;
            TLRPC.PollResults r62 = r6.results;
            r03.text = r62.solution;
            r03.entities = r62.solution_entities;
            goto L11
        }

        public static boolean isFullyTranslated(MessageObject r5, PollText r6) {
            TLRPC.MessageMedia r52 = MessageObject.getMedia(r5);
            if ((r52 instanceof TLRPC.TL_messageMediaPoll) == false) goto L32;
            TLRPC.TL_messageMediaPoll r53 = (TLRPC.TL_messageMediaPoll) r52;
            TLRPC.Poll r0 = r53.poll;
            if (r0 != null) goto L8;
            return true;
        L8:
            if (r0.question == null) goto L10;
            boolean r2 = true;
        L12:
            if (r6.question == null) goto L14;
            boolean r4 = true;
        L15:
            if (r2 == r4) goto L17;
            return false;
        L17:
            TLRPC.PollResults r54 = r53.results;
            if (r54 != null) goto L20;
        L22:
            boolean r55 = false;
        L24:
            if (r6.solution == null) goto L26;
            boolean r22 = true;
        L27:
            if (r55 == r22) goto L30;
            return false;
        L30:
            if (r0.answers.size() == r6.answers.size()) goto L32;
            return false;
        L26:
            r22 = false;
            goto L27
        L20:
            if (r54.solution == null) goto L22;
            r55 = true;
            goto L24
        L14:
            r4 = false;
            goto L15
        L10:
            r2 = false;
        L32:
            return true;
        }

        public int length() {
            TLRPC.TL_textWithEntities r0 = this.question;
            int r1 = 0;
            if (r0 == null) goto L5;
            int r02 = r0.text.length();
        L7:
            if (r1 >= this.answers.size()) goto L9;
            r02 = r02 + this.answers.get(r1).text.text.length();
            r1 = r1 + 1;
            goto L7
        L9:
            TLRPC.TL_textWithEntities r3 = this.solution;
            if (r3 != null) goto L12;
            return r02;
        L12:
            return r02 + r3.text.length();
        L5:
            r02 = 0;
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            if ((r0 & 1) == 0) goto L6;
            this.question = TLRPC.TL_textWithEntities.TLdeserialize(r3, r3.readInt32(r4), r4);
        L6:
            if ((r0 & 2) == 0) goto L9;
            this.answers = Vector.deserialize(r3, new TranslateController$PollText$$ExternalSyntheticLambda0(), r4);
        L9:
            if ((r0 & 4) == 0) goto L12;
            this.solution = TLRPC.TL_textWithEntities.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            if (this.question == null) goto L5;
            int r0 = 1;
        L6:
            ArrayList<TLRPC.PollAnswer> r1 = this.answers;
            if (r1 == null) goto L12;
            if (r1.isEmpty() == true) goto L12;
            r0 = r0 | 2;
        L12:
            if (this.solution == null) goto L14;
            r0 = r0 | 4;
        L14:
            r3.writeInt32(r0);
            if ((r0 & 1) == 0) goto L18;
            this.question.serializeToStream(r3);
        L18:
            if ((r0 & 2) == 0) goto L21;
            Vector.serialize(r3, this.answers);
        L21:
            if ((r0 & 4) == 0) goto L24;
            this.solution.serializeToStream(r3);
            return;
        L24:
            return;
        L5:
            r0 = 0;
            goto L6
        }
    }

    /* loaded from: classes5.dex */
    public static class StoryKey {
        public long dialogId;
        public int storyId;

        public StoryKey(TL_stories.StoryItem r3) {
            this.dialogId = r3.dialogId;
            this.storyId = r3.id;
        }
    }

    /* loaded from: classes5.dex */
    public static class TranslatableDecision {
        Set<Integer> certainlyNotTranslatable;
        Set<Integer> certainlyTranslatable;
        Set<Integer> unknown;

        public TranslatableDecision() {
            this.certainlyTranslatable = new HashSet();
            this.unknown = new HashSet();
            this.certainlyNotTranslatable = new HashSet();
        }
    }

    public static /* synthetic */ void $r8$lambda$1M2Tlh8xzM5vpXml4yptXSURxhc(TranslateController r0, PendingPollTranslation r1, TLObject r2, TLRPC.TL_error r3, long r4) {
        r0.lambda$pushPollToTranslate$31(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$1TqvnokLtbRgETYlac-WVFPpZ8c, reason: not valid java name */
    public static /* synthetic */ void m5019$r8$lambda$1TqvnokLtbRgETYlacWVFPpZ8c(TranslateController r0, TL_stories.StoryItem r1, String r2, StoryKey r3, Runnable r4, TLRPC.TL_textWithEntities r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$translateStory$44(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$1zcgcFiS5FyukDzea4Sqrm-SofE, reason: not valid java name */
    public static /* synthetic */ void m5020$r8$lambda$1zcgcFiS5FyukDzea4SqrmSofE(TranslateController r0, TL_stories.StoryItem r1, StoryKey r2, String r3) {
        r0.lambda$detectStoryLanguage$38(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$255eRpYjLcvY5PjDpJk662VU99U(TranslateController r0, long r1) {
        r0.lambda$checkDialogTranslatable$19(r1);
    }

    /* renamed from: $r8$lambda$2h-bmSTZhQnm8tQnlz2VHWIis0A, reason: not valid java name */
    public static /* synthetic */ void m5021$r8$lambda$2hbmSTZhQnm8tQnlz2VHWIis0A(TranslateController r0) {
        r0.loadTranslatingDialogsCached();
    }

    public static /* synthetic */ void $r8$lambda$2jzQnDIobH1i8R700ywDkcFYYzY(TranslateController r0, PendingRichTranslation r1, TLObject r2, TLRPC.TL_error r3, long r4) {
        r0.lambda$pushRichMessageToTranslate$34(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$35PEH62qLSc09oEau0v-ONskL6k, reason: not valid java name */
    public static /* synthetic */ int m5022$r8$lambda$35PEH62qLSc09oEau0vONskL6k(Collator r0, Language r1, Language r2) {
        return r0.compare(r1.displayName, r2.displayName);
    }

    /* renamed from: $r8$lambda$5AcYUmp7hJXLP-qj8Upb3H1YdQ4, reason: not valid java name */
    public static /* synthetic */ void m5023$r8$lambda$5AcYUmp7hJXLPqj8Upb3H1YdQ4(TranslateController r0, PendingTranslation r1, TLObject r2, boolean r3, TLRPC.TL_error r4, long r5, Set r7) {
        r0.lambda$pushToTranslate$27(r1, r2, r3, r4, r5, r7);
    }

    public static /* synthetic */ void $r8$lambda$64Lep8ul4ZigUxFPbYRXvppzRYc(TranslateController r0, MessageObject r1, boolean r2, long r3, Boolean r5, Integer r6, TLRPC.TL_textWithEntities r7, String r8) {
        r0.lambda$checkTranslation$10(r1, r2, r3, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$6XNavpUALQ_eWE7bkTIhDLoj8Nw(TranslateController r0, PendingPollTranslation r1, List r2) {
        r0.lambda$pushPollToTranslate$30(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$8m7wbnSFEMOLOOek_nF3manlOtU(TranslateController r0, Utilities.Callback4 r1, boolean r2, int r3, String r4, long r5, String r7, Boolean r8) {
        r0.lambda$pushToTranslate$25(r1, r2, r3, r4, r5, r7, r8);
    }

    /* renamed from: $r8$lambda$9bcPUz0RukW-8R7hKokc5eBFuM0, reason: not valid java name */
    public static /* synthetic */ void m5024$r8$lambda$9bcPUz0RukW8R7hKokc5eBFuM0(TranslateController r0, MessageObject r1, MessageKey r2, Utilities.Callback r3) {
        r0.lambda$detectPhotoLanguage$47(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$ADcmI5GJloTF_8gOssBk1iJ91hw(TranslateController r0, MessageObject r1, String r2, TLRPC.TL_textWithEntities r3, TLRPC.TL_textWithEntities r4, MessageKey r5, Runnable r6, long r7) {
        r0.lambda$translatePhoto$50(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$C7j1Lxl5Sz4FfIBkuCcL3K9k39g(TranslateController r0, MessageObject r1, boolean r2, long r3, Integer r5, PollText r6, String r7) {
        r0.lambda$checkTranslation$9(r1, r2, r3, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$EALn3tuwiABxmTep0uLxFJCU2_o(TranslateController r0, MessageObject r1, long r2, int r4) {
        r0.lambda$checkLanguage$16(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$FFIfEst5UM7taygBa1sCpqO5MO4(TranslateController r0, MessageObject r1, String r2, MessageKey r3, Runnable r4, long r5) {
        r0.lambda$translatePhoto$51(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$GtgXTmEihxvR0cbS8qy0J8eR_K4(TranslateController r0, int r1, Utilities.Callback r2, Integer r3, String r4) {
        r0.lambda$pushToSummarize$21(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$HgskmEXLV0erpaZnKyH2rMxhJa4(TranslateController r0, String r1, MessageObject r2, long r3, int r5) {
        r0.lambda$checkLanguage$18(r1, r2, r3, r5);
    }

    /* renamed from: $r8$lambda$I1ZYF6DcBAYUD-_g0b0SkyUIzBo, reason: not valid java name */
    public static /* synthetic */ int m5025$r8$lambda$I1ZYF6DcBAYUD_g0b0SkyUIzBo(LocaleController.LocaleInfo r2, LocaleController.LocaleInfo r3, LocaleController.LocaleInfo r4) {
        if (r3 == r2) goto L29;
        if (r4 == r2) goto L27;
        List<String> r22 = languagesOrder;
        int r0 = r22.indexOf(r3.pluralLangCode);
        int r23 = r22.indexOf(r4.pluralLangCode);
        if (r0 < 0) goto L11;
        if (r23 < 0) goto L11;
        return r0 - r23;
    L11:
        if (r0 >= 0) goto L30;
        if (r23 >= 0) goto L28;
        int r24 = r3.serverIndex;
        int r02 = r4.serverIndex;
        if (r24 == r02) goto L18;
        if (r24 <= r02) goto L22;
        return 1;
    L22:
        if (r24 >= r02) goto L25;
        return -1;
    L25:
        return 0;
    L18:
        return r3.name.compareTo(r4.name);
    L28:
        return 1;
    L30:
        return -1;
    L27:
        return 1;
    L29:
        return -1;
    }

    /* renamed from: $r8$lambda$Ii0Eo4MEvUycC5z-sKiV1SAagDw, reason: not valid java name */
    public static /* synthetic */ void m5026$r8$lambda$Ii0Eo4MEvUycC5zsKiV1SAagDw(TranslateController r0, MessageObject r1, String r2, long r3, TLRPC.TL_textWithEntities r5) {
        r0.lambda$checkTranslation$8(r1, r2, r3, r5);
    }

    /* renamed from: $r8$lambda$JL8PE7z-lysxxxy0sglNr4tbyE4, reason: not valid java name */
    public static /* synthetic */ void m5027$r8$lambda$JL8PE7zlysxxxy0sglNr4tbyE4(TranslateController r0, Utilities.Callback4 r1, boolean r2, int r3, String r4, long r5, String r7, Boolean r8) {
        r0.lambda$pushToTranslate$26(r1, r2, r3, r4, r5, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$JWovDrahylXCPwz23gbfjHYYj5E(BaseFragment r2) {
        r2.presentFragment(new PremiumPreviewFragment("summarize_limit"));
    }

    public static /* synthetic */ void $r8$lambda$Lfx32I48EX5ViNjg9cArdINRv18(TranslateController r0, long r1, PendingPollTranslation r3) {
        r0.lambda$pushPollToTranslate$33(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$M5M9EgLyOwp87rRJR4Cbd58ID74(TranslateController r0, int r1, Utilities.Callback r2, TLRPC.TL_textWithEntities r3, TLRPC.TL_error r4) {
        r0.lambda$pushToSummarize$23(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$PoGYF4nwN9A4UedUBg1YfN5r2Jw(TranslateController r0, PendingPollTranslation r1, long r2, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$pushPollToTranslate$32(r1, r2, r4, r5);
    }

    /* renamed from: $r8$lambda$QwfRixmzOArC6-NScDsAiwzbhLw, reason: not valid java name */
    public static /* synthetic */ void m5028$r8$lambda$QwfRixmzOArC6NScDsAiwzbhLw(TranslateController r0, PendingTranslation r1, boolean r2, long r3, Set r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$pushToTranslate$28(r1, r2, r3, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$S3cL9bR_b7uPPeRp5vVlAeiDTXE(TranslateController r0, MessageObject r1, long r2, int r4, Exception r5) {
        r0.lambda$checkLanguage$17(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$SIYPXIIkU0PJI8cf5AFELn7Ot00(TranslateController r0, MessageObject r1, MessageKey r2, Utilities.Callback r3, Exception r4) {
        r0.lambda$detectPhotoLanguage$48(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$ULagIQ3i_rm21AuPxB7nMG8gdiI(TranslateController r0, PendingTranslation r1, Set r2) {
        r0.lambda$pushToTranslate$24(r1, r2);
    }

    /* renamed from: $r8$lambda$WpD-4QFGsZuxoBNzJMdzKRqfHSc, reason: not valid java name */
    public static /* synthetic */ void m5029$r8$lambda$WpD4QFGsZuxoBNzJMdzKRqfHSc(TranslateController r0, ArrayList r1, ArrayList r2) {
        r0.lambda$checkDialogMessageSure$12(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Wxxm1InjDhLYJ2eNpXdZvQZYkmE(TranslateController r0, MessageObject r1, long r2, TLRPC.TL_textWithEntities r4) {
        r0.lambda$checkTranslation$6(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$YAuTAODGqjjt86_doVzdA_AyU7s(TranslateController r0, int r1, Utilities.Callback r2, TLRPC.TL_textWithEntities r3) {
        r0.lambda$pushToSummarize$20(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Yjh0KokHqzeQef2utzbGy_eCdBw(TranslateController r0, MessageObject r1, boolean r2, long r3, Integer r5, TL_iv.RichMessage r6, String r7) {
        r0.lambda$checkTranslation$7(r1, r2, r3, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$Yy86gJf7LdaNWnhaTWgKK8ObsLA(TranslateController r0, TL_stories.StoryItem r1, StoryKey r2, Exception r3) {
        r0.lambda$detectStoryLanguage$40(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$bsRPmzh1SQnC6RQ-6SVqakOVNi4, reason: not valid java name */
    public static /* synthetic */ void m5030$r8$lambda$bsRPmzh1SQnC6RQ6SVqakOVNi4(TranslateController r0, MessageObject r1, String r2, MessageKey r3, Utilities.Callback r4) {
        r0.lambda$detectPhotoLanguage$45(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$eh7OkDmPplCNgzNP6FqH1c3PZE0(TranslateController r0, MessageObject r1, String r2, MessageKey r3, Runnable r4, long r5, TLRPC.TL_textWithEntities r7, TLObject r8, TLRPC.TL_error r9) {
        r0.lambda$translatePhoto$52(r1, r2, r3, r4, r5, r7, r8, r9);
    }

    /* renamed from: $r8$lambda$gfgGz_a5-jS4y1CkQ4m6nyfyBfg, reason: not valid java name */
    public static /* synthetic */ void m5031$r8$lambda$gfgGz_a5jS4y1CkQ4m6nyfyBfg(TranslateController r0, MessageObject r1, String r2, MessageKey r3, Runnable r4, long r5) {
        r0.lambda$translatePhoto$49(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$ip36tMv0gaJmlkOfLmQhMdK6H3c(TranslateController r0, TL_stories.StoryItem r1, String r2, StoryKey r3, Runnable r4) {
        r0.lambda$translateStory$41(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$m2mtz5NmUIh8xSfwfY9sxiQzk7U(TranslateController r0, long r1, String r3) {
        r0.lambda$setDialogTranslateTo$0(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$m37mxhNDqAaywqdKos7kE5QDJOo(TranslateController r0, String r1, MessageObject r2, long r3, int r5) {
        r0.lambda$checkLanguage$14(r1, r2, r3, r5);
    }

    /* renamed from: $r8$lambda$mt8lp8N-e9J-qxw84cz6o7z9__Q, reason: not valid java name */
    public static /* synthetic */ void m5032$r8$lambda$mt8lp8Ne9Jqxw84cz6o7z9__Q(TranslateController r0, TL_stories.StoryItem r1, String r2, StoryKey r3, Runnable r4) {
        r0.lambda$translateStory$43(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$oUqzsxXadN-ZdVKeFo0HCv1pOf8, reason: not valid java name */
    public static /* synthetic */ void m5033$r8$lambda$oUqzsxXadNZdVKeFo0HCv1pOf8(TranslateController r0, TL_stories.StoryItem r1, String r2, TLRPC.TL_textWithEntities r3, TLRPC.TL_textWithEntities r4, StoryKey r5, Runnable r6) {
        r0.lambda$translateStory$42(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ int $r8$lambda$ogpEaxb9JmIC6s_561tRHjzJzY8(Collator r0, Language r1, Language r2) {
        return r0.compare(r1.displayName, r2.displayName);
    }

    public static /* synthetic */ void $r8$lambda$pDChRzwvja9avVx87Gmr5RjqeJI(TranslateController r0, HashMap r1, long r2, PendingTranslation r4, boolean r5, Set r6) {
        r0.lambda$pushToTranslate$29(r1, r2, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$qHnxYWteY93QUFtKwAHa4CrS05E(TranslateController r0, MessageObject r1, MessageKey r2, Utilities.Callback r3, String r4) {
        r0.lambda$detectPhotoLanguage$46(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$t95dYleYBEB6muwkxZnfbAuIpNE(TranslateController r0, long r1, PendingRichTranslation r3) {
        r0.lambda$pushRichMessageToTranslate$36(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$taVUIa4n2e8JauCG4lbQv5JoWAc(TranslateController r0, TL_stories.StoryItem r1, StoryKey r2) {
        r0.lambda$detectStoryLanguage$39(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$utA3LRbp79PTo8t8ezVpLuPRl04(TranslateController r0, TL_stories.StoryItem r1, String r2, StoryKey r3) {
        r0.lambda$detectStoryLanguage$37(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$vdpB49_7qfIZu18blFx9vcCrKoU(TranslateController r0, MessageObject r1, long r2, int r4, String r5) {
        r0.lambda$checkLanguage$15(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$vt1W8joEr6_0t3BZrSB5fhazSaQ(TranslateController r0, MessageObject r1, long r2) {
        r0.lambda$invalidateTranslation$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$x38ZYuGila2wv5O4XvK2J1JO1Go(TranslateController r0, long r1) {
        r0.lambda$checkDialogMessageSure$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$y84zliWZvf8IFrHzSzdzKjrqfeA(TranslateController r0, PendingRichTranslation r1, long r2, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$pushRichMessageToTranslate$35(r1, r2, r4, r5);
    }

    static {
        languagesOrder = Arrays.asList(new String[]{"en", "ar", "zh", "fr", "de", "it", "ja", "ko", "pt", "ru", "es", "uk"});
        allLanguages = Arrays.asList(new String[]{"af", "sq", "am", "ar", "hy", "az", "eu", "be", "bn", "bs", "bg", "ca", "ceb", "zh-cn", "zh", "zh-tw", "co", "hr", "cs", "da", "nl", "en", "eo", "et", "fi", "fr", "fy", ImageLoader.AUTOPLAY_FILTER_NONLOOP, "ka", "de", "el", "gu", "ht", "ha", "haw", "he", "hi", "hmn", "hu", "is", "ig", "id", "ga", "it", "ja", "jv", "kn", "kk", "km", "rw", "ko", "ku", "ky", "lo", "la", "lv", "lt", "lb", "mk", "mg", "ms", "ml", "mt", "mi", "mr", "mn", "my", "ne", "no", "ny", PluginsConstants.HookFilterTypes.OR, "ps", "fa", "pl", "pt", "pt-br", "pa", "ro", "ru", "sm", "gd", "sr", "st", "sn", "sd", "si", "sk", "sl", "so", "es", "su", "sw", "sv", "tl", "tg", "ta", "tt", "te", "th", "tr", "tk", "uk", "ur", "ug", "uz", "vi", "cy", "xh", "yi", "yo", "zu"});
        suggestedLanguageCodes = null;
    }

    public TranslateController(MessagesController r3) {
        super(r3.currentAccount);
        this.translatingDialogs = new LongSparseArray();
        this.translatableDialogs = new HashSet();
        this.translatableDialogMessages = new HashMap();
        this.translateDialogLanguage = new HashMap();
        this.detectedDialogLanguage = new HashMap();
        this.keptReplyMessageObjects = new HashMap();
        this.hideTranslateDialogs = new HashSet();
        this.pendingLanguageChecks = new ArrayList();
        this.loadingTranslations = new HashSet();
        this.loadingTranscriptionTranslations = new HashSet();
        this.pendingTranslations = new HashMap();
        this.pendingTranscriptionsTranslations = new HashMap();
        this.loadingSummarizations = new HashSet();
        this.pendingPollTranslations = new HashMap();
        this.pendingRichTranslations = new HashMap();
        this.detectingStories = new HashSet();
        this.translatingStories = new HashSet();
        this.detectingPhotos = new HashSet();
        this.translatingPhotos = new HashSet();
        this.messagesController = r3;
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda34(this), 150);
    }

    public static void analyzeSuggestedLanguageCodes() {
        LinkedHashSet<String> r0 = new LinkedHashSet();
        String r1 = TranslatorUtils.normalizeLanguageCode(LocaleController.getInstance().getCurrentLocaleInfo().pluralLangCode);     // Catch: Exception -> L7
        if (TextUtils.isEmpty(r1) == true) goto L37;
        r0.add(r1);     // Catch: Exception -> L7
    L37:
        String r12 = TranslatorUtils.normalizeLanguageCode(Resources.getSystem().getConfiguration().locale.getLanguage());     // Catch: Exception -> L13
        if (TextUtils.isEmpty(r12) == true) goto L41;
        r0.add(r12);     // Catch: Exception -> L13
    L41:
        r0.addAll(RestrictedLanguagesSelectActivity.getRestrictedLanguages());     // Catch: Exception -> L17
    L43:
        InputMethodManager r13 = (InputMethodManager) ApplicationLoader.applicationContext.getSystemService("input_method");     // Catch: Exception -> L33
        Iterator<InputMethodInfo> r2 = r13.getEnabledInputMethodList().iterator();     // Catch: Exception -> L33
    L20:
        if (r2.hasNext() == false) goto L35;
        Iterator<InputMethodSubtype> r3 = r13.getEnabledInputMethodSubtypeList(r2.next(), true).iterator();     // Catch: Exception -> L33
    L24:
        if (r3.hasNext() == false) goto L20;
        InputMethodSubtype r4 = r3.next();     // Catch: Exception -> L33
        if ("keyboard".equals(r4.getMode()) == false) goto L24;
        String r42 = TranslatorUtils.normalizeLanguageCode(r4.getLocale());     // Catch: Exception -> L33
        if (TextUtils.isEmpty(r42) == true) goto L24;
        if (TranslateAlert2.languageName(r42) == null) goto L24;
        r0.add(r42);     // Catch: Exception -> L33
    L35:
        suggestedLanguageCodes = r0;
        return;
    L33:
        e = move-exception;
        FileLog.e(e);
    L17:
        e = move-exception;
        FileLog.e(e);
    L13:
        e = move-exception;
        FileLog.e(e);
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L37
    }

    private void applyPollTranslations(PendingPollTranslation r20, ArrayList<TLRPC.TL_textWithEntities> r21) {
        monitor-enter(this);
        ArrayList<Integer> r2 = r20.messageIds;     // Catch: Throwable -> L53
        ArrayList<Utilities.Callback3<Integer, PollText, String>> r3 = r20.callbacks;     // Catch: Throwable -> L53
        ArrayList<Pair<PollText, PollText>> r4 = r20.messageTexts;     // Catch: Throwable -> L53
        monitor-exit(this);     // Catch: Throwable -> L53
        ArrayList r5 = new ArrayList();
        int r6 = r4.size();
        int r8 = 0;
        int r9 = 0;
    L7:
        if (r9 >= r6) goto L49;
        Pair<PollText, PollText> r10 = r4.get(r9);
        r9 = r9 + 1;
        Pair<PollText, PollText> r102 = r10;
        PollText r11 = (PollText) r102.first;
        PollText r103 = (PollText) r102.second;
        PollText r12 = new PollText();
        if (r103 == null) goto L14;
        TLRPC.TL_textWithEntities r13 = r103.question;
        if (r13 == null) goto L14;
        r12.question = r13;
    L20:
        int r132 = r11.answers.size();
        if (r103 != null) goto L23;
        int r14 = 0;
    L24:
        if (r132 == r14) goto L34;
        ArrayList<TLRPC.PollAnswer> r133 = r11.answers;
        int r142 = r133.size();
        int r15 = 0;
    L26:
        if (r15 >= r142) goto L33;
        TLRPC.PollAnswer r16 = r133.get(r15);
        r15 = r15 + 1;
        TLRPC.PollAnswer r7 = r16;
        ArrayList<Pair<PollText, PollText>> r162 = r4;
        if (r8 < r21.size()) goto L31;
        TLRPC.TL_textWithEntities r42 = new TLRPC.TL_textWithEntities();
    L30:
        int r17 = r6;
        TLRPC.TL_pollAnswer r62 = new TLRPC.TL_pollAnswer();
        r62.text = r42;
        r62.option = r7.option;
        r12.answers.add(r62);
        r4 = r162;
        r6 = r17;
        goto L26
    L31:
        int r43 = r8 + 1;
        TLRPC.TL_textWithEntities r82 = r21.get(r8);
        r8 = r43;
        r42 = r82;
        goto L30
    L33:
        ArrayList<Pair<PollText, PollText>> r163 = r4;
        int r172 = r6;
    L37:
        if (r103 == null) goto L42;
        TLRPC.TL_textWithEntities r44 = r103.solution;
        if (r44 == null) goto L42;
        r12.solution = r44;
    L48:
        r5.add(r12);
        r4 = r163;
        r6 = r172;
    L42:
        if (r11.solution == null) goto L48;
        if (r8 < r21.size()) goto L46;
        TLRPC.TL_textWithEntities r45 = new TLRPC.TL_textWithEntities();
    L47:
        r12.solution = TranslateAlert2.preprocess(r11.solution, r45);
        goto L48
    L46:
        TLRPC.TL_textWithEntities r63 = r21.get(r8);
        r8 = r8 + 1;
        r45 = r63;
        goto L47
    L34:
        r163 = r4;
        r172 = r6;
        if (r103 == null) goto L37;
        r12.answers = r103.answers;
        goto L37
    L23:
        r14 = r103.answers.size();
    L14:
        if (r11.question == null) goto L20;
        if (r8 < r21.size()) goto L18;
        TLRPC.TL_textWithEntities r134 = new TLRPC.TL_textWithEntities();
    L19:
        r12.question = TranslateAlert2.preprocess(r11.question, r134);
        goto L20
    L18:
        int r135 = r8 + 1;
        r134 = r21.get(r8);
        r8 = r135;
        goto L19
    L49:
        int r1 = Math.min(r3.size(), r5.size());
        int r72 = 0;
    L50:
        if (r72 >= r1) goto L52;
        r3.get(r72).run(r2.get(r72), (PollText) r5.get(r72), r20.language);
        r72 = r72 + 1;
        goto L50
    L52:
        return;
    L53:
        th = move-exception;
        throw th;
    }

    private void checkDialogTranslatable(MessageObject r9) {
        if (r9 != null) goto L4;
        return;
    L4:
        if (r9.messageOwner == null) goto L58;
        long r0 = r9.getDialogId();
        TranslatableDecision r2 = this.translatableDialogMessages.get(Long.valueOf(r0));
        if (r2 != null) goto L9;
        HashMap<Long, TranslatableDecision> r22 = this.translatableDialogMessages;
        Long r3 = Long.valueOf(r0);
        TranslatableDecision r4 = new TranslatableDecision();
        r22.put(r3, r4);
        r2 = r4;
    L9:
        boolean r42 = false;
        if (isTranslatable(r9) == false) goto L16;
        String r32 = r9.messageOwner.originalLanguage;
        if (r32 != null) goto L14;
    L15:
        boolean r33 = true;
    L18:
        if (isTranslatable(r9) == false) goto L26;
        String r7 = r9.messageOwner.originalLanguage;
        if (r7 == null) goto L26;
        if (UNKNOWN_LANGUAGE.equals(r7) == true) goto L26;
        if (isLanguageRestricted(r9.messageOwner.originalLanguage) == true) goto L26;
        r42 = true;
    L26:
        if (r33 == false) goto L28;
        r2.unknown.add(Integer.valueOf(r9.getId()));
    L32:
        if (r33 == true) goto L34;
        this.detectedDialogLanguage.put(Long.valueOf(r0), r9.messageOwner.originalLanguage);
    L34:
        int r92 = r2.certainlyTranslatable.size();
        int r34 = r2.unknown.size();
        int r43 = (r92 + r34) + r2.certainlyNotTranslatable.size();
        boolean r5 = isChatAutoTranslated(r0);
        if (r5 == false) goto L37;
        int r6 = 2;
    L38:
        if (r43 < r6) goto L54;
        if (r5 == false) goto L44;
        if (r92 < REQUIRED_MIN_MESSAGES_TRANSLATABLE_AUTOTRANSLATE) goto L55;
    L45:
        float r93 = r34 / r43;
        if (r5 == false) goto L48;
        float r23 = REQUIRED_MIN_PERCENTAGE_MESSAGES_UNKNOWN_AUTOTRANSLATE;
    L50:
        if (r93 >= r23) goto L57;
        this.translatableDialogs.add(Long.valueOf(r0));
        this.translatableDialogMessages.remove(Long.valueOf(r0));
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda22(this, r0), 450);
        return;
    L57:
        return;
    L48:
        r23 = REQUIRED_MIN_PERCENTAGE_MESSAGES_UNKNOWN;
        goto L50
    L55:
        return;
    L44:
        if ((r92 / (r92 + r2)) >= REQUIRED_PERCENTAGE_MESSAGES_TRANSLATABLE) goto L45;
        return;
    L54:
        return;
    L37:
        r6 = 6;
        goto L38
    L28:
        if (r42 == false) goto L30;
        Set<Integer> r44 = r2.certainlyTranslatable;
    L31:
        r44.add(Integer.valueOf(r9.getId()));
        goto L32
    L30:
        r44 = r2.certainlyNotTranslatable;
        goto L31
    L14:
        if (UNKNOWN_LANGUAGE.equals(r32) == true) goto L15;
    L16:
        r33 = false;
        goto L18
    }

    private void checkLanguage(MessageObject r9) {
        if (LanguageDetector.hasSupport() == false) goto L26;
        String r3 = getDetectLanguageText(r9);
        if (isTranslatable(r9) == true) goto L8;
        return;
    L8:
        if (r9.messageOwner != null) goto L10;
        return;
    L10:
        if (TextUtils.isEmpty(r3) == false) goto L13;
        return;
    L13:
        if (r9.messageOwner.originalLanguage == null) goto L16;
        checkDialogTranslatable(r9);
        return;
    L16:
        long r5 = r9.getDialogId();
        int r7 = hash(r9);
        if (isDialogTranslatable(r5) == false) goto L20;
        return;
    L20:
        if (this.pendingLanguageChecks.contains(Integer.valueOf(r7)) == true) goto L29;
        this.pendingLanguageChecks.add(Integer.valueOf(r7));
        Utilities.stageQueue.postRunnable(new TranslateController$$ExternalSyntheticLambda17(this, r3, r9, r5, r7));
        return;
    L29:
        return;
    }

    private void checkTranslation(MessageObject r8, boolean r9, boolean r10) {
        if (r8 != null) goto L4;
        return;
    L4:
        if (r8.messageOwner == null) goto L104;
        long r5 = r8.getDialogId();
        if (r9 == false) goto L16;
        TLRPC.Message r0 = r8.messageOwner;
        if (r0.summarizedOpen == false) goto L16;
        if (r0.summaryText != null) goto L16;
        if (isTranslatingDialog(r8.getDialogId()) == true) goto L16;
        pushToSummarize(r8, null, new TranslateController$$ExternalSyntheticLambda36(this, r8, r5));
    L16:
        if (isFeatureAvailable(r5) == false) goto L105;
        if (r10 == true) goto L23;
        MessageObject r02 = r8.replyMessageObject;
        if (r02 == null) goto L23;
        checkTranslation(r02, r9, true);
    L23:
        if (isTranslatable(r8) == true) goto L26;
        return;
    L26:
        if (isTranslatingDialog(r5) == true) goto L30;
        checkLanguage(r8);
        return;
    L30:
        if (isTranslateDialogHidden(r5) == true) goto L107;
        String r03 = TranslatorUtils.normalizeLanguageCode(getDialogTranslateTo(r5));
        if (r10 == false) goto L35;
    L54:
        MessageObject r3 = r8;
    L55:
        if (r9 == true) goto L57;
        return;
    L57:
        if (isTranslatingDialog(r5) == false) goto L100;
        int r82 = r3.type;
        TLRPC.Message r92 = r3.messageOwner;
        if (r82 != 36) goto L67;
        if (r92.translatedRichMessage != null) goto L63;
    L64:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.messageTranslating, new Object[]{r3});
        pushRichMessageToTranslate(r3, r03, new TranslateController$$ExternalSyntheticLambda37(this, r3, r10, r5));
        return;
    L63:
        if (r03.equals(r92.translatedToLanguage) == false) goto L64;
        return;
    L67:
        if (r92.summarizedOpen == false) goto L75;
        if (r92.translatedSummaryText != null) goto L71;
    L72:
        pushToSummarize(r3, r03, new TranslateController$$ExternalSyntheticLambda38(this, r3, r03, r5));
        return;
    L71:
        if (TextUtils.equals(r03, r92.translatedSummaryLanguage) == false) goto L72;
        return;
    L75:
        if (r92.voiceTranscriptionOpen == true) goto L77;
    L79:
        TLRPC.TL_textWithEntities r83 = r92.translatedText;
    L80:
        if (r83 == null) goto L82;
    L83:
        PollText r84 = r92.translatedPoll;
        if (r84 == null) goto L88;
        if (PollText.isFullyTranslated(r3, r84) == true) goto L88;
    L89:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.messageTranslating, new Object[]{r3});
        if ((MessageObject.getMedia(r3) instanceof TLRPC.TL_messageMediaPoll) == false) goto L93;
        pushPollToTranslate(r3, r03, new TranslateController$$ExternalSyntheticLambda39(this, r3, r10, r5));
        return;
    L93:
        pushToTranslate(r3, r03, new TranslateController$$ExternalSyntheticLambda40(this, r3, r10, r5));
        return;
    L88:
        if (TextUtils.equals(r03, r3.messageOwner.translatedToLanguage) == false) goto L89;
        if (r10 == false) goto L103;
        keepReplyMessage(r3);
        return;
    L103:
        return;
    L82:
        if (r92.translatedPoll == null) goto L89;
    L77:
        if (r92.voiceTranscriptionFinal == false) goto L79;
        r83 = r92.translatedVoiceTranscription;
        goto L80
    L100:
        return;
    L35:
        if (r8.type == 36) goto L54;
        TLRPC.Message r2 = r8.messageOwner;
        if (r2.voiceTranscriptionOpen == true) goto L39;
    L41:
        TLRPC.TL_textWithEntities r32 = r2.translatedText;
    L42:
        if (r32 == null) goto L44;
    L45:
        PollText r22 = r2.translatedPoll;
        if (r22 == null) goto L50;
        if (PollText.isFullyTranslated(r8, r22) == true) goto L50;
    L51:
        MessageObject r23 = findReplyMessageObject(r5, r8.getId());
        if (r23 == null) goto L54;
        TLRPC.Message r85 = r8.messageOwner;
        TLRPC.Message r33 = r23.messageOwner;
        r85.translatedToLanguage = r33.translatedToLanguage;
        r85.translatedText = r33.translatedText;
        r85.translatedPoll = r33.translatedPoll;
        r3 = r23;
    L50:
        if (TextUtils.equals(r03, r8.messageOwner.translatedToLanguage) == true) goto L54;
    L44:
        if (r2.translatedPoll == null) goto L51;
    L39:
        if (r2.voiceTranscriptionFinal == false) goto L41;
        r32 = r2.translatedVoiceTranscription;
        goto L42
    L107:
        return;
    L105:
        return;
    }

    private void clearAllKeptReplyMessages(long r1) {
        this.keptReplyMessageObjects.remove(Long.valueOf(r1));
    }

    private ArrayList<TLRPC.TL_textWithEntities> collectPollSources(PendingPollTranslation r10) {
        ArrayList<TLRPC.TL_textWithEntities> r9 = new ArrayList();
        ArrayList<Pair<PollText, PollText>> r102 = r10.messageTexts;
        int r0 = r102.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L24;
        Pair<PollText, PollText> r3 = r102.get(r2);
        r2 = r2 + 1;
        Pair<PollText, PollText> r32 = r3;
        PollText r4 = (PollText) r32.first;
        PollText r33 = (PollText) r32.second;
        TLRPC.TL_textWithEntities r5 = r4.question;
        if (r5 == null) goto L10;
        if (r33 != null) goto L8;
    L9:
        r9.add(r5);
        goto L10
    L8:
        if (r33.question == null) goto L9;
    L10:
        int r52 = r4.answers.size();
        if (r33 != null) goto L13;
        int r6 = 0;
    L14:
        if (r52 == r6) goto L18;
        ArrayList<TLRPC.PollAnswer> r53 = r4.answers;
        int r62 = r53.size();
        int r7 = 0;
    L16:
        if (r7 >= r62) goto L18;
        TLRPC.PollAnswer r8 = r53.get(r7);
        r7 = r7 + 1;
        r9.add(r8.text);
    L18:
        TLRPC.TL_textWithEntities r42 = r4.solution;
        if (r42 == null) goto L3;
        if (r33 == null) goto L23;
        if (r33.solution != null) goto L3;
    L23:
        r9.add(r42);
        goto L3
    L13:
        r6 = r33.answers.size();
        goto L14
    L24:
        return r9;
    }

    public static String currentLanguage() {
        String r0 = LocaleController.getInstance().getCurrentLocaleInfo().pluralLangCode;
        if (r0 == null) goto L6;
        r0 = r0.split("_")[0];
    L6:
        return TranslatorUtils.normalizeLanguageCode(r0);
    }

    private static String getDetectLanguageText(MessageObject r3) {
        if (r3 == null) goto L17;
        TLRPC.Message r1 = r3.messageOwner;
        if (r1 == null) goto L17;
        if (TextUtils.isEmpty(r1.message) == true) goto L12;
        return r3.messageOwner.message;
    L12:
        if (r3.type != 36) goto L17;
        if (TextUtils.isEmpty(r3.messageText) == true) goto L17;
        return r3.messageText.toString();
    L17:
        return null;
    }

    public static ArrayList<Language> getLanguages() {
        Set<String> r0 = TranslatorUtils.getCurrentTranslator().getSupportedLanguages();
        if (r0 != null) goto L5;
    L27:
        ArrayList<Language> r02 = new ArrayList();
        int r6 = 0;
    L28:
        List<String> r7 = allLanguages;
        if (r6 >= r7.size()) goto L48;
        Language r8 = new Language();
        r8.code = r7.get(r6);
        if (TranslatorUtils.getCurrentTranslator() != TelegramTranslator.getInstance()) goto L38;
        if (r8.code.contains("-") == true) goto L47;
        if (r8.code.contains("_") == false) goto L38;
    L47:
        r6 = r6 + 1;
    L38:
        if ("no".equals(r8.code) == false) goto L40;
        r8.code = "nb";
    L40:
        r8.displayName = TranslateAlert2.capitalFirst(TranslateAlert2.languageName(r8.code));
        r8.ownDisplayName = TranslateAlert2.capitalFirst(TranslateAlert2.systemLanguageName(r8.code, true));
        if (r8.displayName == null) goto L47;
        StringBuilder r72 = new StringBuilder();
        r72.append(r8.displayName);
        r72.append(" ");
        String r9 = r8.ownDisplayName;
        if (r9 != null) goto L46;
        r9 = _UrlKt.FRAGMENT_ENCODE_SET;
    L46:
        r72.append(r9);
        r8.q = r72.toString().toLowerCase();
        r02.add(r8);
        goto L47
    L48:
        Collections.sort(r02, new TranslateController$$ExternalSyntheticLambda29(Collator.getInstance(Locale.getDefault())));
        return r02;
    L5:
        if (r0.isEmpty() == true) goto L27;
        HashSet r62 = new HashSet();
        Iterator<String> r03 = r0.iterator();
    L8:
        if (r03.hasNext() == false) goto L12;
        String r73 = TranslatorUtils.normalizeLanguageCode(r03.next());
        if (TextUtils.isEmpty(r73) == true) goto L8;
        r62.add(r73);
        goto L8
    L12:
        ArrayList<Language> r04 = new ArrayList();
        Iterator r63 = r62.iterator();
    L14:
        if (r63.hasNext() == false) goto L25;
        String r74 = (String) r63.next();
        Language r82 = new Language();
        if ("no".equals(r74) == false) goto L18;
        r74 = "nb";
    L18:
        r82.code = r74;
        r82.displayName = TranslateAlert2.capitalFirst(TranslateAlert2.languageName(r74));
        r82.ownDisplayName = TranslateAlert2.capitalFirst(TranslateAlert2.systemLanguageName(r82.code, true));
        if (r82.displayName == null) goto L14;
        StringBuilder r75 = new StringBuilder();
        r75.append(r82.displayName);
        r75.append(" ");
        String r92 = r82.ownDisplayName;
        if (r92 != null) goto L24;
        r92 = _UrlKt.FRAGMENT_ENCODE_SET;
    L24:
        r75.append(r92);
        r82.q = r75.toString().toLowerCase();
        r04.add(r82);
        goto L14
    L25:
        Collections.sort(r04, new TranslateController$$ExternalSyntheticLambda28(Collator.getInstance(Locale.getDefault())));
        return r04;
    }

    public static ArrayList<LocaleController.LocaleInfo> getLocales() {
        ArrayList<LocaleController.LocaleInfo> r1 = new ArrayList(LocaleController.getInstance().languagesDict.values());
        int r0 = 0;
    L4:
        if (r0 >= r1.size()) goto L15;
        LocaleController.LocaleInfo r2 = r1.get(r0);
        if (r2 == null) goto L13;
        String r3 = r2.shortName;
        if (r3 == null) goto L12;
        if (r3.endsWith("_raw") == true) goto L13;
    L12:
        if ("remote".equals(r2.pathToFile) == false) goto L13;
    L14:
        r0 = r0 + 1;
    L13:
        r1.remove(r0);
        r0 = r0 - 1;
        goto L14
    L15:
        Collections.sort(r1, new TranslateController$$ExternalSyntheticLambda12(LocaleController.getInstance().getCurrentLocaleInfo()));
        return r1;
    }

    public static ArrayList<Language> getSuggestedLanguages(String r7) {
        ArrayList<Language> r0 = new ArrayList();
        if (suggestedLanguageCodes != null) goto L7;
        analyzeSuggestedLanguageCodes();
        if (suggestedLanguageCodes != null) goto L7;
    L33:
        return r0;
    L7:
        HashSet r1 = new HashSet();
        ArrayList<Language> r2 = getLanguages();
        int r3 = r2.size();
        int r4 = 0;
    L8:
        if (r4 >= r3) goto L14;
        Language r5 = r2.get(r4);
        r4 = r4 + 1;
        Language r52 = r5;
        if (r52 == null) goto L8;
        if (TextUtils.isEmpty(r52.code) == true) goto L8;
        r1.add(TranslatorUtils.normalizeLanguageCode(r52.code));
        goto L8
    L14:
        Iterator<String> r22 = suggestedLanguageCodes.iterator();
    L16:
        if (r22.hasNext() == false) goto L33;
        String r32 = r22.next();
        if (TextUtils.equals(r32, r7) == true) goto L16;
        Language r42 = new Language();
        String r33 = TranslatorUtils.normalizeLanguageCode(r32);
        r42.code = r33;
        if (TextUtils.isEmpty(r33) == true) goto L16;
        if (r1.contains(r42.code) == false) goto L16;
        if ("no".equals(r42.code) == false) goto L28;
        String r34 = "nb";
    L29:
        r42.displayName = TranslateAlert2.capitalFirst(TranslateAlert2.languageName(r34));
        r42.ownDisplayName = TranslateAlert2.capitalFirst(TranslateAlert2.systemLanguageName(r34, true));
        if (r42.displayName == null) goto L16;
        r42.q = (r42.displayName + " " + r42.ownDisplayName).toLowerCase();
        r0.add(r42);
        goto L16
    L28:
        r34 = r42.code;
        goto L29
    }

    private int hash(MessageObject r3) {
        if (r3 != null) goto L6;
        return 0;
    L6:
        return Objects.hash(new Object[]{Long.valueOf(r3.getDialogId()), Integer.valueOf(r3.getId())});
    }

    public static void invalidateSuggestedLanguageCodes() {
        suggestedLanguageCodes = null;
    }

    private boolean isChatAutoTranslated(long r3) {
        if (isDialogTranslatable(r3) == true) goto L5;
        return false;
    L5:
        TLRPC.Chat r2 = getMessagesController().getChat(Long.valueOf(-r3));
        if (r2 != null) goto L8;
    L11:
        return false;
    L8:
        if (r2.autotranslation == false) goto L11;
        return true;
    }

    private boolean isLanguageRestricted(String r1) {
        return TranslatorUtils.isRestrictedLanguage(r1);
    }

    public static boolean isSummarizable(MessageObject r3) {
        if (ExteraConfig.getTelegramAiSummaries() == false) goto L31;
        if (r3 == null) goto L33;
        TLRPC.Message r0 = r3.messageOwner;
        if (r0 != null) goto L8;
        return false;
    L8:
        if (r0.summary_from_language != null) goto L10;
        return false;
    L10:
        if (r3.isOutOwner() == false) goto L12;
        return false;
    L12:
        if (r3.isRestrictedMessage == false) goto L14;
        return false;
    L14:
        if (r3.isSponsored() == true) goto L38;
        int r02 = r3.type;
        if (r02 == 0) goto L27;
        if (r02 == 3) goto L27;
        if (r02 == 1) goto L27;
        if (r02 == 9) goto L27;
        if (r02 == 14) goto L27;
        if (r02 == 17) goto L27;
        return false;
    L27:
        if (TextUtils.isEmpty(r3.messageOwner.message) == false) goto L29;
        return false;
    L29:
        if (r3.messageOwner.message.length() <= 100) goto L41;
        return true;
    L41:
        return false;
    L38:
        return false;
    L33:
        return false;
    L31:
        return false;
    }

    public static boolean isTranslatable(MessageObject r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (r3.messageOwner != null) goto L6;
        return false;
    L6:
        if (r3.isOutOwner() == false) goto L8;
        return false;
    L8:
        if (r3.isRestrictedMessage == false) goto L10;
        return false;
    L10:
        if (r3.isSponsored() == true) goto L46;
        int r0 = r3.type;
        if (r0 == 0) goto L29;
        if (r0 == 3) goto L29;
        if (r0 == 1) goto L29;
        if (r0 == 2) goto L29;
        if (r0 == 5) goto L29;
        if (r0 == 9) goto L29;
        if (r0 == 14) goto L29;
        if (r0 == 17) goto L29;
        if (r0 == 36) goto L29;
        return false;
    L29:
        if (TextUtils.isEmpty(r3.messageOwner.message) == true) goto L31;
    L40:
        return true;
    L31:
        if ((MessageObject.getMedia(r3) instanceof TLRPC.TL_messageMediaPoll) == true) goto L40;
        TLRPC.Message r02 = r3.messageOwner;
        if (r02.rich_message != null) goto L40;
        if (r02.voiceTranscriptionOpen == true) goto L37;
        return false;
    L37:
        if (TextUtils.isEmpty(r02.voiceTranscription) == false) goto L39;
        return false;
    L39:
        if (r3.messageOwner.voiceTranscriptionFinal == true) goto L40;
        return false;
    L46:
        return false;
    }

    private void keepReplyMessage(MessageObject r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        HashMap<Integer, MessageObject> r0 = this.keptReplyMessageObjects.get(Long.valueOf(r4.getDialogId()));
        if (r0 != null) goto L7;
        HashMap<Long, HashMap<Integer, MessageObject>> r3 = this.keptReplyMessageObjects;
        Long r02 = Long.valueOf(r4.getDialogId());
        HashMap<Integer, MessageObject> r1 = new HashMap();
        r3.put(r02, r1);
        r0 = r1;
    L7:
        r0.put(Integer.valueOf(r4.getId()), r4);
    }

    private /* synthetic */ void lambda$checkDialogMessageSure$12(ArrayList r8, ArrayList r9) {
        int r1 = 0;
        boolean r2 = false;
    L4:
        if (r1 >= Math.min(r8.size(), r9.size())) goto L15;
        MessageObject r3 = (MessageObject) r9.get(r1);
        TLRPC.Message r4 = (TLRPC.Message) r8.get(r1);
        if (r3 == null) goto L14;
        TLRPC.Message r5 = r3.messageOwner;
        if (r5 == null) goto L14;
        if (r4 == null) goto L14;
        r5.translatedText = r4.translatedText;
        r5.translatedPoll = r4.translatedPoll;
        r5.translatedToLanguage = r4.translatedToLanguage;
        if (r3.updateTranslation(false) == false) goto L14;
        r2 = true;
    L14:
        r1 = r1 + 1;
        goto L4
    L15:
        if (r2 == false) goto L24;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{0});
        return;
    }

    private /* synthetic */ void lambda$checkDialogMessageSure$13(long r7) {
        ArrayList<MessageObject> r72 = this.messagesController.dialogMessage.get(r7);
        if (r72 != null) goto L5;
        return;
    L5:
        ArrayList r8 = new ArrayList();
        int r0 = 0;
    L7:
        if (r0 >= r72.size()) goto L16;
        MessageObject r1 = r72.get(r0);
        if (r1 != null) goto L11;
    L14:
        r8.add(null);
    L15:
        r0 = r0 + 1;
        goto L7
    L11:
        if (r1.messageOwner == null) goto L14;
        r8.add(getMessagesStorage().getMessageWithCustomParamsOnlyInternal(r1.getId(), r1.getDialogId()));
        goto L15
    L16:
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda30(this, r8, r72));
    }

    private /* synthetic */ void lambda$checkDialogTranslatable$19(long r2) {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogIsTranslatable, new Object[]{Long.valueOf(r2)});
    }

    private /* synthetic */ void lambda$checkLanguage$14(String r2, MessageObject r3, long r4, int r6) {
        if (r2 != null) goto L4;
        r2 = UNKNOWN_LANGUAGE;
    L4:
        r3.messageOwner.originalLanguage = r2;
        updateMessageCustomParams(r4, r3);
        this.pendingLanguageChecks.remove(Integer.valueOf(r6));
        checkDialogTranslatable(r3);
    }

    private /* synthetic */ void lambda$checkLanguage$15(MessageObject r8, long r9, int r11, String r12) {
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda32(this, r12, r8, r9, r11));
    }

    private /* synthetic */ void lambda$checkLanguage$16(MessageObject r3, long r4, int r6) {
        r3.messageOwner.originalLanguage = UNKNOWN_LANGUAGE;
        updateMessageCustomParams(r4, r3);
        this.pendingLanguageChecks.remove(Integer.valueOf(r6));
    }

    private /* synthetic */ void lambda$checkLanguage$17(MessageObject r1, long r2, int r4, Exception r5) {
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda33(this, r1, r2, r4));
    }

    private /* synthetic */ void lambda$checkLanguage$18(String r8, MessageObject r9, long r10, int r12) {
        LanguageDetector.detectLanguage(r8, new TranslateController$$ExternalSyntheticLambda8(this, r9, r10, r12), new TranslateController$$ExternalSyntheticLambda9(this, r9, r10, r12));
    }

    private /* synthetic */ void lambda$checkTranslation$10(MessageObject r4, boolean r5, long r6, Boolean r8, Integer r9, TLRPC.TL_textWithEntities r10, String r11) {
        if (r4.getId() == r9.intValue()) goto L5;
        FileLog.e("wtf, asked to translate " + r4.getId() + " but got " + r9 + "!");
    L5:
        String r92 = TranslatorUtils.normalizeLanguageCode(r11);
        r4.messageOwner.translatedToLanguage = r92;
        boolean r112 = r8.booleanValue();
        TLRPC.Message r0 = r4.messageOwner;
        if (r112 == false) goto L8;
        r0.translatedVoiceTranscription = r10;
    L9:
        r4.messageOwner.translatedPoll = null;
        if (r5 == false) goto L12;
        keepReplyMessage(r4);
    L12:
        updateMessageCustomParams(r6, r4);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.messageTranslated, new Object[]{r4});
        ArrayList<MessageObject> r52 = this.messagesController.dialogMessage.get(r6);
        if (r52 == null) goto L30;
        int r7 = 0;
    L16:
        if (r7 >= r52.size()) goto L36;
        MessageObject r113 = r52.get(r7);
        if (r113 == null) goto L29;
        if (r113.getId() != r4.getId()) goto L29;
        r113.messageOwner.translatedToLanguage = r92;
        boolean r42 = r8.booleanValue();
        TLRPC.Message r53 = r113.messageOwner;
        if (r42 == false) goto L24;
        r53.translatedVoiceTranscription = r10;
    L25:
        r113.messageOwner.translatedPoll = null;
        if (r113.updateTranslation() == false) goto L35;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{0});
        return;
    L35:
        return;
    L24:
        r53.translatedText = r10;
    L29:
        r7 = r7 + 1;
        goto L16
    L36:
        return;
    L30:
        return;
    L8:
        r0.translatedText = r10;
        goto L9
    }

    private /* synthetic */ void lambda$checkTranslation$6(MessageObject r2, long r3, TLRPC.TL_textWithEntities r5) {
        TLRPC.Message r0 = r2.messageOwner;
        r0.summaryText = r5;
        if (r5 != null) goto L5;
        r0.summarizedOpen = false;
    L5:
        updateMessageCustomParams(r3, r2);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.messageTranslated, new Object[]{r2, Boolean.TRUE});
    }

    private /* synthetic */ void lambda$checkTranslation$7(MessageObject r3, boolean r4, long r5, Integer r7, TL_iv.RichMessage r8, String r9) {
        if (r3.getId() == r7.intValue()) goto L5;
        FileLog.e("wtf, asked to translate " + r3.getId() + " rich message but got " + r7 + "!");
    L5:
        TLRPC.Message r72 = r3.messageOwner;
        r72.translatedToLanguage = r9;
        r72.translatedText = null;
        r72.translatedVoiceTranscription = null;
        r72.translatedPoll = null;
        r72.translatedRichMessage = r8;
        if (r4 == false) goto L8;
        keepReplyMessage(r3);
    L8:
        getMessagesStorage().updateMessageCustomParams(r5, r3.messageOwner);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.messageTranslated, new Object[]{r3});
        ArrayList<MessageObject> r42 = this.messagesController.dialogMessage.get(r5);
        if (r42 == null) goto L22;
        int r6 = 0;
    L12:
        if (r6 >= r42.size()) goto L28;
        MessageObject r73 = r42.get(r6);
        if (r73 == null) goto L21;
        if (r73.getId() != r3.getId()) goto L21;
        TLRPC.Message r32 = r73.messageOwner;
        r32.translatedToLanguage = r9;
        r32.translatedRichMessage = r8;
        if (r73.updateTranslation() == false) goto L27;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{0});
        return;
    L27:
        return;
    L21:
        r6 = r6 + 1;
        goto L12
    L28:
        return;
    }

    private /* synthetic */ void lambda$checkTranslation$8(MessageObject r2, String r3, long r4, TLRPC.TL_textWithEntities r6) {
        TLRPC.Message r0 = r2.messageOwner;
        if (r6 == null) goto L5;
        String r32 = TranslatorUtils.normalizeLanguageCode(r3);
    L6:
        r0.translatedSummaryLanguage = r32;
        TLRPC.Message r33 = r2.messageOwner;
        r33.translatedSummaryText = r6;
        if (r6 != null) goto L9;
        r33.summarizedOpen = false;
    L9:
        updateMessageCustomParams(r4, r2);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.messageTranslated, new Object[]{r2, Boolean.TRUE});
        return;
    L5:
        r32 = null;
        goto L6
    }

    private /* synthetic */ void lambda$checkTranslation$9(MessageObject r4, boolean r5, long r6, Integer r8, PollText r9, String r10) {
        if (r4.getId() == r8.intValue()) goto L5;
        FileLog.e("wtf, asked to translate " + r4.getId() + " poll but got " + r8 + "!");
    L5:
        String r82 = TranslatorUtils.normalizeLanguageCode(r10);
        TLRPC.Message r102 = r4.messageOwner;
        r102.translatedToLanguage = r82;
        r102.translatedText = null;
        r102.translatedVoiceTranscription = null;
        r102.translatedPoll = r9;
        if (r5 == false) goto L8;
        keepReplyMessage(r4);
    L8:
        updateMessageCustomParams(r6, r4);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.messageTranslated, new Object[]{r4});
        ArrayList<MessageObject> r52 = this.messagesController.dialogMessage.get(r6);
        if (r52 == null) goto L22;
        int r7 = 0;
    L12:
        if (r7 >= r52.size()) goto L28;
        MessageObject r103 = r52.get(r7);
        if (r103 == null) goto L21;
        if (r103.getId() != r4.getId()) goto L21;
        TLRPC.Message r42 = r103.messageOwner;
        r42.translatedToLanguage = r82;
        r42.translatedText = null;
        r42.translatedVoiceTranscription = null;
        r42.translatedPoll = r9;
        if (r103.updateTranslation() == false) goto L27;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{0});
        return;
    L27:
        return;
    L21:
        r7 = r7 + 1;
        goto L12
    L28:
        return;
    }

    private /* synthetic */ void lambda$detectPhotoLanguage$45(MessageObject r3, String r4, MessageKey r5, Utilities.Callback r6) {
        r3.messageOwner.originalLanguage = r4;
        updateMessageCustomParams(r5.dialogId, r3);
        this.detectingPhotos.remove(r5);
        if (r6 == null) goto L6;
        r6.run(r4);
        return;
    }

    private /* synthetic */ void lambda$detectPhotoLanguage$46(MessageObject r7, MessageKey r8, Utilities.Callback r9, String r10) {
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda13(this, r7, r10, r8, r9));
    }

    private /* synthetic */ void lambda$detectPhotoLanguage$47(MessageObject r5, MessageKey r6, Utilities.Callback r7) {
        r5.messageOwner.originalLanguage = UNKNOWN_LANGUAGE;
        updateMessageCustomParams(r6.dialogId, r5);
        this.detectingPhotos.remove(r6);
        if (r7 == null) goto L6;
        r7.run(UNKNOWN_LANGUAGE);
        return;
    }

    private /* synthetic */ void lambda$detectPhotoLanguage$48(MessageObject r1, MessageKey r2, Utilities.Callback r3, Exception r4) {
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda6(this, r1, r2, r3));
    }

    private /* synthetic */ void lambda$detectStoryLanguage$37(TL_stories.StoryItem r3, String r4, StoryKey r5) {
        r3.detectedLng = r4;
        getMessagesController().getStoriesController().getStoriesStorage().putStoryInternal(r3.dialogId, r3);
        this.detectingStories.remove(r5);
    }

    private /* synthetic */ void lambda$detectStoryLanguage$38(TL_stories.StoryItem r2, StoryKey r3, String r4) {
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda5(this, r2, r4, r3));
    }

    private /* synthetic */ void lambda$detectStoryLanguage$39(TL_stories.StoryItem r4, StoryKey r5) {
        r4.detectedLng = UNKNOWN_LANGUAGE;
        getMessagesController().getStoriesController().getStoriesStorage().putStoryInternal(r4.dialogId, r4);
        this.detectingStories.remove(r5);
    }

    private /* synthetic */ void lambda$detectStoryLanguage$40(TL_stories.StoryItem r1, StoryKey r2, Exception r3) {
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda3(this, r1, r2));
    }

    private static /* synthetic */ String lambda$getLanguages$2(Language r0) {
        return r0.displayName;
    }

    private static /* synthetic */ String lambda$getLanguages$4(Language r0) {
        return r0.displayName;
    }

    private /* synthetic */ void lambda$invalidateTranslation$11(MessageObject r4, long r5) {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.messageTranslated, new Object[]{r4, Boolean.FALSE, Boolean.valueOf(isTranslatingDialog(r5))});
    }

    private /* synthetic */ void lambda$pushPollToTranslate$30(PendingPollTranslation r5, List r6) {
        if (r6 == null) goto L4;
        applyPollTranslations(r5, new ArrayList(r6));
    L8:
        releaseLoadingTranslations(r5.messageIds, this.loadingTranslations);
        return;
    L4:
        int r62 = 0;
    L6:
        if (r62 >= r5.callbacks.size()) goto L8;
        r5.callbacks.get(r62).run(r5.messageIds.get(r62), null, r5.language);
        r62 = r62 + 1;
        goto L6
    }

    private /* synthetic */ void lambda$pushPollToTranslate$31(PendingPollTranslation r6, TLObject r7, TLRPC.TL_error r8, long r9) {
        monitor-enter(this);
        ArrayList<Integer> r1 = r6.messageIds;     // Catch: Throwable -> L31
        ArrayList<Utilities.Callback3<Integer, PollText, String>> r2 = r6.callbacks;     // Catch: Throwable -> L31
        monitor-exit(this);     // Catch: Throwable -> L31
        int r4 = 0;
        if ((r7 instanceof TLRPC.TL_messages_translateResult) == false) goto L9;
        applyPollTranslations(r6, ((TLRPC.TL_messages_translateResult) r7).result);
    L21:
        monitor-enter(this);
    L34:
    L25:
        th = move-exception;
        throw th;
    L23:
        if (r4 >= r1.size()) goto L27;
        this.loadingTranslations.remove(r1.get(r4));     // Catch: Throwable -> L25
        r4 = r4 + 1;     // Catch: Throwable -> L25
        goto L34
    L27:
        monitor-exit(this);     // Catch: Throwable -> L25
        return;
    L9:
        if (r8 != null) goto L11;
    L13:
        if (r8 != null) goto L15;
    L17:
        int r72 = 0;
    L19:
        if (r72 >= r2.size()) goto L21;
        r2.get(r72).run(r1.get(r72), null, r6.language);
        r72 = r72 + 1;
        goto L19
    L15:
        if ("QUOTA_EXCEEDED".equals(r8.text) == false) goto L17;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, LocaleController.getString(R.string.TranslationFailedAlert1)});
        goto L17
    L11:
        if ("TO_LANG_INVALID".equals(r8.text) == false) goto L13;
        toggleTranslatingDialog(r9, false);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, LocaleController.getString(R.string.TranslationFailedAlert2)});
    L31:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$pushPollToTranslate$32(PendingPollTranslation r8, long r9, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda4(this, r8, r11, r12, r9));
    }

    private /* synthetic */ void lambda$pushPollToTranslate$33(long r4, PendingPollTranslation r6) {
        monitor-enter(this);
        ArrayList<PendingTranslation> r0 = this.pendingTranslations.get(Long.valueOf(r4));     // Catch: Throwable -> L8
        if (r0 == null) goto L10;
        r0.remove(r6);     // Catch: Throwable -> L8
        if (r0.isEmpty() == false) goto L10;
        this.pendingTranslations.remove(Long.valueOf(r4));     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);     // Catch: Throwable -> L8
        ArrayList<TLRPC.TL_textWithEntities> r02 = collectPollSources(r6);
        if (TranslatorUtils.isAlternativeProvider() == false) goto L15;
        ChatTranslationBridge.translateTexts(r4, r02, normalizeLanguage(r6.language), new TranslateController$$ExternalSyntheticLambda49(this, r6));
        return;
    L15:
        TLRPC.TL_messages_translateText r1 = new TLRPC.TL_messages_translateText();
        r1.flags |= 2;
        r1.text = r02;
        r1.to_lang = normalizeLanguage(r6.language);
        int r42 = getConnectionsManager().sendRequest(r1, new TranslateController$$ExternalSyntheticLambda50(this, r6, r4));
        monitor-enter(this);
        r6.reqId = r42;     // Catch: Throwable -> L20
        monitor-exit(this);     // Catch: Throwable -> L20
        return;
    L20:
        th = move-exception;
        throw th;
    L8:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$pushRichMessageToTranslate$34(PendingRichTranslation r7, TLObject r8, TLRPC.TL_error r9, long r10) {
        monitor-enter(this);
        ArrayList<Integer> r1 = r7.messageIds;     // Catch: Throwable -> L33
        ArrayList<Utilities.Callback3<Integer, TL_iv.RichMessage, String>> r2 = r7.callbacks;     // Catch: Throwable -> L33
        monitor-exit(this);     // Catch: Throwable -> L33
        int r4 = 0;
        if ((r8 instanceof TLRPC.TL_messages_translatedRichMessage) == false) goto L11;
        ArrayList<TL_iv.RichMessage> r82 = ((TLRPC.TL_messages_translatedRichMessage) r8).result;
        int r92 = Math.min(r2.size(), r82.size());
        int r102 = 0;
    L9:
        if (r102 >= r92) goto L23;
        r2.get(r102).run(r1.get(r102), r82.get(r102), r7.language);
        r102 = r102 + 1;
    L23:
        monitor-enter(this);
    L38:
    L27:
        th = move-exception;
        throw th;
    L25:
        if (r4 >= r1.size()) goto L29;
        this.loadingTranslations.remove(r1.get(r4));     // Catch: Throwable -> L27
        r4 = r4 + 1;     // Catch: Throwable -> L27
        goto L38
    L29:
        monitor-exit(this);     // Catch: Throwable -> L27
        return;
    L11:
        if (r9 != null) goto L13;
    L15:
        if (r9 != null) goto L17;
    L19:
        int r83 = 0;
    L21:
        if (r83 >= r2.size()) goto L23;
        r2.get(r83).run(r1.get(r83), null, r7.language);
        r83 = r83 + 1;
        goto L21
    L17:
        if ("QUOTA_EXCEEDED".equals(r9.text) == false) goto L19;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, LocaleController.getString(R.string.TranslationFailedAlert1)});
        goto L19
    L13:
        if ("TO_LANG_INVALID".equals(r9.text) == false) goto L15;
        toggleTranslatingDialog(r10, false);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, LocaleController.getString(R.string.TranslationFailedAlert2)});
    L33:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$pushRichMessageToTranslate$35(PendingRichTranslation r8, long r9, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda0(this, r8, r11, r12, r9));
    }

    private /* synthetic */ void lambda$pushRichMessageToTranslate$36(long r4, PendingRichTranslation r6) {
        monitor-enter(this);
        ArrayList<PendingRichTranslation> r0 = this.pendingRichTranslations.get(Long.valueOf(r4));     // Catch: Throwable -> L8
        if (r0 == null) goto L10;
        r0.remove(r6);     // Catch: Throwable -> L8
        if (r0.isEmpty() == false) goto L10;
        this.pendingRichTranslations.remove(Long.valueOf(r4));     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);     // Catch: Throwable -> L8
        TLRPC.TL_messages_translateRichMessage r02 = new TLRPC.TL_messages_translateRichMessage();
        r02.flags |= 1;
        r02.peer = getMessagesController().getInputPeer(r4);
        r02.id = r6.messageIds;
        r02.to_lang = normalizeLanguage(r6.language);
        int r42 = getConnectionsManager().sendRequest(r02, new TranslateController$$ExternalSyntheticLambda21(this, r6, r4));
        monitor-enter(this);
        r6.reqId = r42;     // Catch: Throwable -> L16
        monitor-exit(this);     // Catch: Throwable -> L16
        return;
    L16:
        th = move-exception;
        throw th;
    L8:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$pushToSummarize$20(int r1, Utilities.Callback r2, TLRPC.TL_textWithEntities r3) {
        this.loadingSummarizations.remove(Integer.valueOf(r1));
        r2.run(r3);
    }

    private /* synthetic */ void lambda$pushToSummarize$21(int r8, Utilities.Callback r9, Integer r10, String r11) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        long r1 = System.currentTimeMillis();
        if (r0 != null) goto L5;
    L7:
        this.loadingSummarizations.remove(Integer.valueOf(r8));
        r9.run(null);
        return;
    L5:
        if ((r1 - this.lastSummaryErrorBulletinTime) <= SUMMARY_ERROR_BULLETIN_INTERVAL) goto L7;
        this.lastSummaryErrorBulletinTime = r1;
        AiController.showErrorBulletin(r0, r10.intValue(), r11);
        goto L7
    }

    private /* synthetic */ void lambda$pushToSummarize$23(int r5, Utilities.Callback r6, TLRPC.TL_textWithEntities r7, TLRPC.TL_error r8) {
        if (r7 == null) goto L5;
        this.loadingSummarizations.remove(Integer.valueOf(r5));
        r6.run(r7);
        return;
    L5:
        if (r8 != null) goto L7;
        return;
    L7:
        if ("SUMMARY_FLOOD_PREMIUM".equalsIgnoreCase(r8.text) == false) goto L11;
        BaseFragment r72 = LaunchActivity.getSafeLastFragment();
        if (r72 == null) goto L11;
        BulletinFactory.of(r72).createSimpleBulletin(R.raw.star_premium_2, LocaleController.getString(R.string.SummaryLimit), LocaleController.getString(R.string.SummaryLimitUpgrade), new TranslateController$$ExternalSyntheticLambda31(r72)).setDuration(5000).show(true);
    L11:
        this.loadingSummarizations.remove(Integer.valueOf(r5));
        r6.run(null);
    }

    private /* synthetic */ void lambda$pushToTranslate$24(PendingTranslation r1, Set r2) {
        releaseLoadingTranslations(r1.messageIds, r2);
    }

    private /* synthetic */ void lambda$pushToTranslate$25(Utilities.Callback4 r1, boolean r2, int r3, String r4, long r5, String r7, Boolean r8) {
        if (r7 == null) goto L5;
        TLRPC.TL_textWithEntities r0 = new TLRPC.TL_textWithEntities();
        r0.text = r7;
        r1.run(Boolean.valueOf(r2), Integer.valueOf(r3), r0, r4);
        return;
    L5:
        toggleTranslatingDialog(r5, false);
        NotificationCenter r02 = NotificationCenter.getGlobalInstance();
        int r12 = NotificationCenter.showBulletin;
        if (r8.booleanValue() == false) goto L8;
        int r32 = R.string.TranslationFailedAlert1;
    L9:
        r02.postNotificationName(r12, new Object[]{1, LocaleController.getString(r32)});
        return;
    L8:
        r32 = R.string.TranslationFailedAlert2;
        goto L9
    }

    private /* synthetic */ void lambda$pushToTranslate$26(Utilities.Callback4 r1, boolean r2, int r3, String r4, long r5, String r7, Boolean r8) {
        if (r7 == null) goto L5;
        TLRPC.TL_textWithEntities r0 = new TLRPC.TL_textWithEntities();
        r0.text = r7;
        r1.run(Boolean.valueOf(r2), Integer.valueOf(r3), r0, r4);
        return;
    L5:
        toggleTranslatingDialog(r5, false);
        NotificationCenter r02 = NotificationCenter.getGlobalInstance();
        int r12 = NotificationCenter.showBulletin;
        if (r8.booleanValue() == false) goto L8;
        int r32 = R.string.TranslationFailedAlert1;
    L9:
        r02.postNotificationName(r12, new Object[]{1, LocaleController.getString(r32)});
        return;
    L8:
        r32 = R.string.TranslationFailedAlert2;
        goto L9
    }

    private /* synthetic */ void lambda$pushToTranslate$27(PendingTranslation r16, TLObject r17, boolean r18, TLRPC.TL_error r19, long r20, Set r22) {
        monitor-enter(this);
        ArrayList<Integer> r8 = r16.messageIds;     // Catch: Throwable -> L41
        ArrayList<Utilities.Callback4<Boolean, Integer, TLRPC.TL_textWithEntities, String>> r9 = r16.callbacks;     // Catch: Throwable -> L41
        ArrayList<TLRPC.TL_textWithEntities> r10 = r16.messageTexts;     // Catch: Throwable -> L41
        String r5 = r16.language;     // Catch: Throwable -> L41
        monitor-exit(this);     // Catch: Throwable -> L41
        int r11 = 0;
        if ((r17 instanceof TLRPC.TL_messages_translateResult) == false) goto L12;
        ArrayList<TLRPC.TL_textWithEntities> r0 = ((TLRPC.TL_messages_translateResult) r17).result;
        int r1 = Math.min(r9.size(), r0.size());
        int r2 = 0;
    L9:
        if (r2 >= r1) goto L31;
        r9.get(r2).run(Boolean.valueOf(r18), r8.get(r2), TranslateAlert2.preprocess(r10.get(r2), r0.get(r2)), r5);
        r2 = r2 + 1;
    L31:
        monitor-enter(this);
    L44:
    L35:
        th = move-exception;
        throw th;
    L33:
        if (r11 >= r8.size()) goto L37;
        r22.remove(r8.get(r11));     // Catch: Throwable -> L35
        r11 = r11 + 1;     // Catch: Throwable -> L35
        goto L44
    L37:
        monitor-exit(this);     // Catch: Throwable -> L35
        return;
    L12:
        if (r19 != null) goto L14;
    L19:
        if (r19 != null) goto L21;
    L23:
        if (r19 != null) goto L25;
    L27:
        int r23 = 0;
    L29:
        if (r23 >= r9.size()) goto L31;
        r9.get(r23).run(Boolean.valueOf(r18), r8.get(r23), null, r16.language);
        r23 = r23 + 1;
        goto L29
    L25:
        if ("QUOTA_EXCEEDED".equals(r19.text) == false) goto L27;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, LocaleController.getString(R.string.TranslationFailedAlert1)});
        goto L27
    L21:
        if ("TO_LANG_INVALID".equals(r19.text) == false) goto L23;
        toggleTranslatingDialog(r20, false);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, LocaleController.getString(R.string.TranslationFailedAlert2)});
        goto L31
    L14:
        if ("TRANSLATIONS_DISABLED_ALT".equalsIgnoreCase(r19.text) == false) goto L19;
        int r13 = 0;
    L17:
        if (r13 >= r8.size()) goto L31;
        int r4 = r8.get(r13).intValue();
        TranslateAlert2.alternativeTranslate(r10.get(r13).text, null, r5, new TranslateController$$ExternalSyntheticLambda51(this, r9.get(r13), r18, r4, r5, r20));
        r13 = r13 + 1;
    L41:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$pushToTranslate$28(PendingTranslation r10, boolean r11, long r12, Set r14, TLObject r15, TLRPC.TL_error r16) {
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda23(this, r10, r15, r11, r16, r12, r14));
    }

    private /* synthetic */ void lambda$pushToTranslate$29(HashMap r11, long r12, PendingTranslation r14, boolean r15, Set r16) {
        monitor-enter(this);
        ArrayList r3 = (ArrayList) r11.get(Long.valueOf(r12));     // Catch: Throwable -> L8
        if (r3 == null) goto L10;
        r3.remove(r14);     // Catch: Throwable -> L8
        if (r3.isEmpty() == false) goto L10;
        r11.remove(Long.valueOf(r12));     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);     // Catch: Throwable -> L8
        if (TranslatorUtils.isAlternativeProvider() == false) goto L15;
        ChatTranslationBridge.translateMessages(r12, r15, r14.messageIds, r14.messageTexts, r14.callbacks, normalizeLanguage(r14.language), new TranslateController$$ExternalSyntheticLambda14(this, r14, r16));
        return;
    L15:
        String r32 = getMessagesController().translationsAutoEnabled;
        if ("alternative".equals(r32) == false) goto L18;
    L32:
        String r5 = r14.language;
        int r8 = 0;
    L34:
        if (r8 >= r14.messageIds.size()) goto L36;
        int r4 = r14.messageIds.get(r8).intValue();
        TranslateAlert2.alternativeTranslate(r14.messageTexts.get(r8).text, null, r5, new TranslateController$$ExternalSyntheticLambda15(this, r14.callbacks.get(r8), r15, r4, r5, r12));
        r8 = r8 + 1;
        goto L34
    L36:
        return;
    L18:
        if ("system".equals(r32) == true) goto L32;
        TLRPC.TL_messages_translateText r7 = new TLRPC.TL_messages_translateText();
        int r33 = r7.flags;
        if (r15 == false) goto L23;
        r7.flags = r33 | 2;
        r7.text.addAll(r14.messageTexts);
    L24:
        r7.to_lang = normalizeLanguage(r14.language);
        int r0 = getConnectionsManager().sendRequest(r7, new TranslateController$$ExternalSyntheticLambda16(this, r14, r15, r12, r16));
        monitor-enter(this);
        r14.reqId = r0;     // Catch: Throwable -> L29
        monitor-exit(this);     // Catch: Throwable -> L29
        return;
    L29:
        th = move-exception;
        throw th;
    L23:
        r7.flags = r33 | 1;
        r7.peer = getMessagesController().getInputPeer(r12);
        r7.id = r14.messageIds;
    L8:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$setDialogTranslateTo$0(long r3, String r5) {
        monitor-enter(this);
        this.translateDialogLanguage.put(Long.valueOf(r3), r5);     // Catch: Throwable -> L7
        LongSparseArray<Boolean> r52 = this.translatingDialogs;     // Catch: Throwable -> L7
        Boolean r0 = Boolean.TRUE;     // Catch: Throwable -> L7
        r52.put(r3, r0);     // Catch: Throwable -> L7
        saveTranslatingDialogsCache();     // Catch: Throwable -> L7
        monitor-exit(this);     // Catch: Throwable -> L7
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogTranslate, new Object[]{Long.valueOf(r3), r0});
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$translatePhoto$49(MessageObject r3, String r4, MessageKey r5, Runnable r6, long r7) {
        TLRPC.Message r0 = r3.messageOwner;
        r0.translatedToLanguage = TranslatorUtils.normalizeLanguageCode(r4);
        r3.messageOwner.translatedText = null;
        updateMessageCustomParams(r5.dialogId, r3);
        this.translatingPhotos.remove(r5);
        if (r6 == null) goto L6;
        AndroidUtilities.runOnUIThread(r6, Math.max(0, 400 - (System.currentTimeMillis() - r7)));
        return;
    }

    private /* synthetic */ void lambda$translatePhoto$50(MessageObject r2, String r3, TLRPC.TL_textWithEntities r4, TLRPC.TL_textWithEntities r5, MessageKey r6, Runnable r7, long r8) {
        TLRPC.Message r0 = r2.messageOwner;
        r0.translatedToLanguage = TranslatorUtils.normalizeLanguageCode(r3);
        TLRPC.Message r32 = r2.messageOwner;
        r32.translatedText = TranslateAlert2.preprocess(r4, r5);
        updateMessageCustomParams(r6.dialogId, r2);
        this.translatingPhotos.remove(r6);
        if (r7 == null) goto L6;
        AndroidUtilities.runOnUIThread(r7, Math.max(0, 400 - (System.currentTimeMillis() - r8)));
        return;
    }

    private /* synthetic */ void lambda$translatePhoto$51(MessageObject r3, String r4, MessageKey r5, Runnable r6, long r7) {
        TLRPC.Message r0 = r3.messageOwner;
        r0.translatedToLanguage = TranslatorUtils.normalizeLanguageCode(r4);
        r3.messageOwner.translatedText = null;
        updateMessageCustomParams(r5.dialogId, r3);
        this.translatingPhotos.remove(r5);
        if (r6 == null) goto L6;
        AndroidUtilities.runOnUIThread(r6, Math.max(0, 400 - (System.currentTimeMillis() - r7)));
        return;
    }

    private /* synthetic */ void lambda$translatePhoto$52(MessageObject r14, String r15, MessageKey r16, Runnable r17, long r18, TLRPC.TL_textWithEntities r20, TLObject r21, TLRPC.TL_error r22) {
        if ((r21 instanceof TLRPC.TL_messages_translateResult) == false) goto L10;
        ArrayList<TLRPC.TL_textWithEntities> r0 = ((TLRPC.TL_messages_translateResult) r21).result;
        if (r0.size() > 0) goto L8;
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda18(this, r14, r15, r16, r17, r18));
        return;
    L8:
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda19(this, r14, r15, r20, r0.get(0), r16, r17, r18));
        return;
    L10:
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda20(this, r14, r15, r16, r17, r18));
    }

    private /* synthetic */ void lambda$translateStory$41(TL_stories.StoryItem r3, String r4, StoryKey r5, Runnable r6) {
        r3.translatedLng = r4;
        r3.translatedText = null;
        getMessagesController().getStoriesController().getStoriesStorage().putStoryInternal(r3.dialogId, r3);
        this.translatingStories.remove(r5);
        if (r6 == null) goto L6;
        r6.run();
        return;
    }

    private /* synthetic */ void lambda$translateStory$42(TL_stories.StoryItem r1, String r2, TLRPC.TL_textWithEntities r3, TLRPC.TL_textWithEntities r4, StoryKey r5, Runnable r6) {
        r1.translatedLng = r2;
        r1.translatedText = TranslateAlert2.preprocess(r3, r4);
        getMessagesController().getStoriesController().getStoriesStorage().putStoryInternal(r1.dialogId, r1);
        this.translatingStories.remove(r5);
        if (r6 == null) goto L6;
        r6.run();
        return;
    }

    private /* synthetic */ void lambda$translateStory$43(TL_stories.StoryItem r3, String r4, StoryKey r5, Runnable r6) {
        r3.translatedLng = r4;
        r3.translatedText = null;
        getMessagesController().getStoriesController().getStoriesStorage().putStoryInternal(r3.dialogId, r3);
        this.translatingStories.remove(r5);
        if (r6 == null) goto L6;
        r6.run();
        return;
    }

    private /* synthetic */ void lambda$translateStory$44(TL_stories.StoryItem r9, String r10, StoryKey r11, Runnable r12, TLRPC.TL_textWithEntities r13, TLObject r14, TLRPC.TL_error r15) {
        if ((r14 instanceof TLRPC.TL_messages_translateResult) == false) goto L10;
        ArrayList<TLRPC.TL_textWithEntities> r142 = ((TLRPC.TL_messages_translateResult) r14).result;
        if (r142.size() > 0) goto L8;
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda41(this, r9, r10, r11, r12));
        return;
    L8:
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda42(this, r9, r10, r13, r142.get(0), r11, r12));
        return;
    L10:
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda43(this, r9, r10, r11, r12));
    }

    private void loadTranslatingDialogsCached() {
        String r0 = this.messagesController.getMainSettings().getString("translating_dialog_languages2", null);
        if (r0 == null) goto L55;
        String[] r02 = r0.split(";");
        int r3 = 0;
    L7:
        if (r3 >= r02.length) goto L33;
        String[] r4 = r02[r3].split("=");
        if (r4.length < 2) goto L32;
        long r7 = Long.parseLong(r4[0]);
        String[] r42 = r4[1].split(">");
        if (r42.length != 2) goto L32;
        String r6 = r42[0];
        String r43 = r42[1];
        if (r43.length() > 0) goto L17;
    L19:
        boolean r9 = false;
    L21:
        if ("null".equals(r6) == false) goto L24;
        r6 = null;
    L24:
        if ("null".equals(r43) == false) goto L26;
        r43 = null;
    L26:
        if (r6 == null) goto L32;
        this.detectedDialogLanguage.put(Long.valueOf(r7), r6);
        if (isLanguageRestricted(r6) == true) goto L30;
        this.translatingDialogs.put(r7, Boolean.valueOf(true ^ r9));
        this.translatableDialogs.add(Long.valueOf(r7));
    L30:
        if (r43 == null) goto L32;
        this.translateDialogLanguage.put(Long.valueOf(r7), r43);
        goto L32
    L17:
        if (r43.charAt(r43.length() - 1) != '!') goto L19;
        r43 = r43.substring(0, r43.length() - 1);
        r9 = true;
    L32:
        r3 = r3 + 1;
        goto L7
    L33:
        Set<String> r03 = this.messagesController.getMainSettings().getStringSet("hidden_translation_at", null);
        if (r03 == null) goto L42;
        Iterator<String> r04 = r03.iterator();
    L37:
        if (r04.hasNext() == false) goto L56;
        this.hideTranslateDialogs.add(Long.valueOf(Long.parseLong(r04.next())));     // Catch: Exception -> L40
    L40:
        e = move-exception;
        FileLog.e(e);
        goto L37
    L56:
        return;
    L42:
        return;
    }

    public static String normalizeLanguage(String r6) {
        if (r6 != null) goto L6;
        return null;
    L6:
        if (r6.contains("_") == false) goto L10;
        String[] r62 = r6.split("_", 2);
        return r62[0].toLowerCase() + "-" + r62[1].toUpperCase();
    L10:
        if (r6.contains("-") == false) goto L13;
        String[] r63 = r6.split("-", 2);
        return r63[0].toLowerCase() + "-" + r63[1].toUpperCase();
    L13:
        return r6;
    }

    private void pushPollToTranslate(MessageObject r12, String r13, Utilities.Callback3<Integer, PollText, String> r14) {
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.getId() < 0) goto L42;
        if (r14 == null) goto L43;
        long r1 = r12.getDialogId();
        monitor-enter(this);
        ArrayList<PendingPollTranslation> r3 = this.pendingPollTranslations.get(Long.valueOf(r1));     // Catch: Throwable -> L13
        if (r3 != null) goto L16;
        HashMap<Long, ArrayList<PendingPollTranslation>> r32 = this.pendingPollTranslations;     // Catch: Throwable -> L13
        Long r4 = Long.valueOf(r1);     // Catch: Throwable -> L13
        ArrayList<PendingPollTranslation> r5 = new ArrayList();     // Catch: Throwable -> L13
        r32.put(r4, r5);     // Catch: Throwable -> L13
        r3 = r5;
    L16:
        if (r3.isEmpty() == false) goto L18;
        PendingPollTranslation r42 = new PendingPollTranslation(null);     // Catch: Throwable -> L13
        r3.add(r42);     // Catch: Throwable -> L13
    L20:
        if (r42.messageIds.contains(Integer.valueOf(r12.getId())) == false) goto L23;
        monitor-exit(this);     // Catch: Throwable -> L13
        return;
    L23:
        TLRPC.MessageMedia r6 = MessageObject.getMedia(r12);     // Catch: Throwable -> L13
        if ((r6 instanceof TLRPC.TL_messageMediaPoll) == true) goto L27;
        monitor-exit(this);     // Catch: Throwable -> L13
        return;
    L27:
        PollText r62 = PollText.fromPoll((TLRPC.TL_messageMediaPoll) r6);     // Catch: Throwable -> L13
        PollText r7 = r12.messageOwner.translatedPoll;     // Catch: Throwable -> L13
        int r8 = r62.length();     // Catch: Throwable -> L13
        if ((r42.symbolsCount + r8) < MAX_SYMBOLS_PER_REQUEST) goto L30;
    L31:
        AndroidUtilities.cancelRunOnUIThread(r42.runnable);     // Catch: Throwable -> L13
        AndroidUtilities.runOnUIThread(r42.runnable);     // Catch: Throwable -> L13
        r42 = new PendingPollTranslation(null);     // Catch: Throwable -> L13
        r3.add(r42);     // Catch: Throwable -> L13
    L32:
        Runnable r33 = r42.runnable;     // Catch: Throwable -> L13
        if (r33 == null) goto L35;
        AndroidUtilities.cancelRunOnUIThread(r33);     // Catch: Throwable -> L13
    L35:
        this.loadingTranslations.add(Integer.valueOf(r12.getId()));     // Catch: Throwable -> L13
        r42.messageIds.add(Integer.valueOf(r12.getId()));     // Catch: Throwable -> L13
        FileLog.d("pending translation +" + r12.getId() + " poll message");     // Catch: Throwable -> L13
        r42.messageTexts.add(new Pair(r62, r7));     // Catch: Throwable -> L13
        r42.callbacks.add(r14);     // Catch: Throwable -> L13
        r42.language = r13;     // Catch: Throwable -> L13
        r42.symbolsCount += r8;
        TranslateController$$ExternalSyntheticLambda24 r122 = new TranslateController$$ExternalSyntheticLambda24(this, r1, r42);     // Catch: Throwable -> L13
        r42.runnable = r122;     // Catch: Throwable -> L13
        AndroidUtilities.runOnUIThread(r122, r42.delay);     // Catch: Throwable -> L13
        r42.delay /= 2;
        monitor-exit(this);     // Catch: Throwable -> L13
        return;
    L30:
        if ((r42.messageIds.size() + 1) < 20) goto L32;
    L18:
        r42 = r3.get(r3.size() - 1);     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        throw th;
    L43:
        return;
    }

    private void pushRichMessageToTranslate(MessageObject r9, String r10, Utilities.Callback3<Integer, TL_iv.RichMessage, String> r11) {
        if (r9 != null) goto L5;
        return;
    L5:
        if (r9.messageOwner != null) goto L7;
        return;
    L7:
        if (r9.getId() < 0) goto L45;
        if (r11 != null) goto L11;
        return;
    L11:
        if (r9.messageOwner.rich_message != null) goto L14;
        return;
    L14:
        if (TranslatorUtils.isAlternativeProvider() == true) goto L48;
        long r1 = r9.getDialogId();
        monitor-enter(this);
        ArrayList<PendingRichTranslation> r3 = this.pendingRichTranslations.get(Long.valueOf(r1));     // Catch: Throwable -> L21
        if (r3 != null) goto L24;
        HashMap<Long, ArrayList<PendingRichTranslation>> r32 = this.pendingRichTranslations;     // Catch: Throwable -> L21
        Long r4 = Long.valueOf(r1);     // Catch: Throwable -> L21
        ArrayList<PendingRichTranslation> r5 = new ArrayList();     // Catch: Throwable -> L21
        r32.put(r4, r5);     // Catch: Throwable -> L21
        r3 = r5;
    L24:
        if (r3.isEmpty() == false) goto L26;
        PendingRichTranslation r42 = new PendingRichTranslation(null);     // Catch: Throwable -> L21
        r3.add(r42);     // Catch: Throwable -> L21
    L28:
        if (r42.messageIds.contains(Integer.valueOf(r9.getId())) == false) goto L32;
        monitor-exit(this);     // Catch: Throwable -> L21
        return;
    L32:
        if ((r42.messageIds.size() + 1) < 20) goto L34;
        AndroidUtilities.cancelRunOnUIThread(r42.runnable);     // Catch: Throwable -> L21
        AndroidUtilities.runOnUIThread(r42.runnable);     // Catch: Throwable -> L21
        r42 = new PendingRichTranslation(null);     // Catch: Throwable -> L21
        r3.add(r42);     // Catch: Throwable -> L21
    L34:
        Runnable r33 = r42.runnable;     // Catch: Throwable -> L21
        if (r33 == null) goto L37;
        AndroidUtilities.cancelRunOnUIThread(r33);     // Catch: Throwable -> L21
    L37:
        this.loadingTranslations.add(Integer.valueOf(r9.getId()));     // Catch: Throwable -> L21
        r42.messageIds.add(Integer.valueOf(r9.getId()));     // Catch: Throwable -> L21
        FileLog.d("pending translation +" + r9.getId() + " rich message");     // Catch: Throwable -> L21
        r42.callbacks.add(r11);     // Catch: Throwable -> L21
        r42.language = r10;     // Catch: Throwable -> L21
        TranslateController$$ExternalSyntheticLambda7 r92 = new TranslateController$$ExternalSyntheticLambda7(this, r1, r42);     // Catch: Throwable -> L21
        r42.runnable = r92;     // Catch: Throwable -> L21
        AndroidUtilities.runOnUIThread(r92, r42.delay);     // Catch: Throwable -> L21
        r42.delay /= 2;
        monitor-exit(this);     // Catch: Throwable -> L21
        return;
    L26:
        r42 = r3.get(r3.size() - 1);     // Catch: Throwable -> L21
    L21:
        th = move-exception;
        throw th;
    L48:
        return;
    }

    private void pushToSummarize(MessageObject r8, String r9, Utilities.Callback<TLRPC.TL_textWithEntities> r10) {
        int r0 = r8.getId();
        Long r1 = Long.valueOf(r8.getDialogId());
        Integer r2 = Integer.valueOf(r0);
        if (r9 == null) goto L5;
        int r4 = 1;
    L6:
        int r12 = Objects.hash(new Object[]{r1, r2, Integer.valueOf(r4)});
        if (this.loadingSummarizations.contains(Integer.valueOf(r12)) == false) goto L9;
        return;
    L9:
        this.loadingSummarizations.add(Integer.valueOf(r12));
        if (TelegramAiReplacement.replacesSummaries(this.currentAccount) == false) goto L17;
        TLRPC.Message r82 = r8.messageOwner;
        String r02 = r82.message;
        if (r9 != null) goto L15;
        r9 = r82.summary_from_language;
    L15:
        TelegramAiReplacement.summarize(r02, r9, new TranslateController$$ExternalSyntheticLambda25(this, r12, r10), new TranslateController$$ExternalSyntheticLambda26(this, r12, r10));
        return;
    L17:
        TLRPC.TL_messages_summarizeText r22 = new TLRPC.TL_messages_summarizeText();
        r22.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r8.getDialogId());
        r22.id = r0;
        if (r9 == null) goto L20;
        r22.flags |= 1;
        r22.to_lang = normalizeLanguage(r9);
    L20:
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r22, new AiTonesController$$ExternalSyntheticLambda0(), new TranslateController$$ExternalSyntheticLambda27(this, r12, r10));
        return;
    L5:
        r4 = 0;
        goto L6
    }

    private void pushToTranslate(MessageObject r19, String r20, Utilities.Callback4<Boolean, Integer, TLRPC.TL_textWithEntities, String> r21) {
        if (r19 != null) goto L5;
        return;
    L5:
        if (r19.messageOwner != null) goto L7;
        return;
    L7:
        if (r19.getId() < 0) goto L79;
        if (r21 == null) goto L80;
        TLRPC.Message r4 = r19.messageOwner;
        boolean r6 = false;
        if (r4.voiceTranscription == null) goto L18;
        if (r4.voiceTranscriptionFinal == false) goto L18;
        if (r4.voiceTranscriptionOpen == false) goto L18;
        r6 = true;
    L18:
        long r8 = r19.getDialogId();
        if (r6 == false) goto L21;
        HashMap<Long, ArrayList<PendingTranslation>> r5 = this.pendingTranscriptionsTranslations;
    L22:
        if (r6 == false) goto L24;
        Set<Integer> r10 = this.loadingTranscriptionTranslations;
    L25:
        TLRPC.TL_textWithEntities r11 = new TLRPC.TL_textWithEntities();
        TLRPC.Message r12 = r19.messageOwner;
        if (r6 == false) goto L30;
        String r122 = r12.voiceTranscription;
        r11.text = r122;
        if (TextUtils.isEmpty(r122) == true) goto L81;
    L31:
        monitor-enter(this);
        ArrayList<PendingTranslation> r123 = r5.get(Long.valueOf(r8));     // Catch: Throwable -> L35
        if (r123 != null) goto L38;
        Long r124 = Long.valueOf(r8);     // Catch: Throwable -> L35
        ArrayList<PendingTranslation> r13 = new ArrayList();     // Catch: Throwable -> L35
        r5.put(r124, r13);     // Catch: Throwable -> L35
        r123 = r13;
    L38:
        if (r123.isEmpty() == false) goto L40;
        PendingTranslation r132 = new PendingTranslation(null);     // Catch: Throwable -> L35
        r123.add(r132);     // Catch: Throwable -> L35
    L42:
        if (r132.messageIds.contains(Integer.valueOf(r19.getId())) == false) goto L45;
        monitor-exit(this);     // Catch: Throwable -> L35
        return;
    L45:
        TLRPC.Message r42 = r19.messageOwner;
        if (r6 == false) goto L52;
        String r43 = r42.voiceTranscription;     // Catch: Throwable -> L35
        if (r43 != null) goto L50;
        int r17 = 0;
    L51:
        int r44 = r17;
    L64:
        if ((r132.symbolsCount + r44) < MAX_SYMBOLS_PER_REQUEST) goto L66;
    L67:
        AndroidUtilities.cancelRunOnUIThread(r132.runnable);     // Catch: Throwable -> L35
        AndroidUtilities.runOnUIThread(r132.runnable);     // Catch: Throwable -> L35
        r132 = new PendingTranslation(null);     // Catch: Throwable -> L35
        r123.add(r132);     // Catch: Throwable -> L35
    L68:
        Runnable r7 = r132.runnable;     // Catch: Throwable -> L35
        if (r7 == null) goto L71;
        AndroidUtilities.cancelRunOnUIThread(r7);     // Catch: Throwable -> L35
    L71:
        r10.add(Integer.valueOf(r19.getId()));     // Catch: Throwable -> L35
        r132.messageIds.add(Integer.valueOf(r19.getId()));     // Catch: Throwable -> L35
        FileLog.d("pending translation +" + r19.getId() + " message");     // Catch: Throwable -> L35
        r132.messageTexts.add(r11);     // Catch: Throwable -> L35
        r132.callbacks.add(r21);     // Catch: Throwable -> L35
        r132.language = r20;     // Catch: Throwable -> L35
        r132.symbolsCount += r44;
        PendingTranslation r52 = r132;
        TranslateController$$ExternalSyntheticLambda1 r0 = new TranslateController$$ExternalSyntheticLambda1(this, r5, r8, r52, r6, r10);     // Catch: Throwable -> L35
        r52.runnable = r0;     // Catch: Throwable -> L35
        AndroidUtilities.runOnUIThread(r0, r52.delay);     // Catch: Throwable -> L35
        r52.delay /= 2;
        monitor-exit(this);     // Catch: Throwable -> L35
        return;
    L66:
        if ((r132.messageIds.size() + 1) < 20) goto L68;
    L50:
        r17 = r43.length();     // Catch: Throwable -> L35
        goto L51
    L52:
        if (r42 == null) goto L56;
        String r45 = r42.message;     // Catch: Throwable -> L35
        if (r45 == null) goto L56;
        r44 = r45.length();     // Catch: Throwable -> L35
    L56:
        CharSequence r46 = r19.caption;     // Catch: Throwable -> L35
        if (r46 == null) goto L59;
        r44 = r46.length();     // Catch: Throwable -> L35
        goto L64
    L59:
        CharSequence r47 = r19.messageText;     // Catch: Throwable -> L35
        if (r47 == null) goto L62;
        r44 = r47.length();     // Catch: Throwable -> L35
        goto L64
    L62:
        r44 = 0;
        goto L64
    L40:
        r132 = r123.get(r123.size() - 1);     // Catch: Throwable -> L35
    L35:
        th = move-exception;
        throw th;
    L81:
        return;
    L30:
        r11.text = r12.message;
        r11.entities = r12.entities;
        goto L31
    L24:
        r10 = this.loadingTranslations;
        goto L25
    L21:
        r5 = this.pendingTranslations;
        goto L22
    L80:
        return;
    }

    private void releaseLoadingTranslations(ArrayList<Integer> r3, Set<Integer> r4) {
        monitor-enter(this);
        int r0 = 0;
    L13:
    L7:
        th = move-exception;
        throw th;
    L5:
        if (r0 >= r3.size()) goto L9;
        r4.remove(r3.get(r0));     // Catch: Throwable -> L7
        r0 = r0 + 1;     // Catch: Throwable -> L7
        goto L13
    L9:
        monitor-exit(this);     // Catch: Throwable -> L7
    }

    private void resetTranslatingDialogsCache() {
        MessagesController.getMainSettings(this.currentAccount).edit().remove("translating_dialog_languages2").remove("hidden_translation_at").apply();
    }

    private void saveTranslatingDialogsCache() {
        StringBuilder r0 = new StringBuilder();
        boolean r1 = true;
        int r3 = 0;
    L4:
        if (r3 >= this.translatingDialogs.size()) goto L22;
        long r4 = this.translatingDialogs.keyAt(r3);     // Catch: Exception -> L31
        if (r1 == true) goto L8;
        r0.append(";");     // Catch: Exception -> L31
    L8:
        if (r1 == false) goto L10;
        r1 = false;
    L10:
        String r6 = this.detectedDialogLanguage.get(Long.valueOf(r4));     // Catch: Exception -> L31
        String r7 = "null";
        if (r6 != null) goto L14;
        r6 = "null";
    L14:
        String r8 = getDialogTranslateTo(r4);     // Catch: Exception -> L31
        if (r8 == null) goto L18;
        r7 = r8;
    L18:
        r0.append(r4);     // Catch: Exception -> L31
        r0.append("=");     // Catch: Exception -> L31
        r0.append(r6);     // Catch: Exception -> L31
        r0.append(">");     // Catch: Exception -> L31
        r0.append(r7);     // Catch: Exception -> L31
        if (this.translatingDialogs.valueAt(r3).booleanValue() == true) goto L21;
        r0.append("!");     // Catch: Exception -> L31
    L21:
        r3 = r3 + 1;
        goto L4
    L22:
        HashSet r12 = new HashSet();
        Iterator<Long> r2 = this.hideTranslateDialogs.iterator();
    L24:
        if (r2.hasNext() == false) goto L29;
        r12.add(_UrlKt.FRAGMENT_ENCODE_SET + r2.next());     // Catch: Exception -> L27
    L27:
        e = move-exception;
        FileLog.e(e);
        goto L24
    L29:
        MessagesController.getMainSettings(this.currentAccount).edit().putString("translating_dialog_languages2", r0.toString()).putStringSet("hidden_translation_at", r12).apply();
    }

    private void updateMessageCustomParams(long r2, MessageObject r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (r4.messageOwner == null) goto L9;
        getMessagesStorage().updateMessageCustomParams(r2, r4.messageOwner, r4.getId());
        return;
    }

    public boolean canTranslatePhoto(MessageObject r3, String r4) {
        if (r3 == null) goto L8;
        TLRPC.Message r0 = r3.messageOwner;
        if (r0 == null) goto L8;
        String r02 = r0.originalLanguage;
        if (r02 == null) goto L8;
        r4 = r02;
    L8:
        if (r3 == null) goto L25;
        TLRPC.Message r03 = r3.messageOwner;
        if (r03 != null) goto L12;
        return false;
    L12:
        if (TextUtils.isEmpty(r03.message) == true) goto L28;
        if (r4 != null) goto L18;
        TLRPC.Message r04 = r3.messageOwner;
        if (r04.translatedText == null) goto L18;
        if (TextUtils.equals(r04.translatedToLanguage, TranslateAlert2.getToLanguage()) == false) goto L18;
    L22:
        if (r3.translated == true) goto L31;
        return true;
    L31:
        return false;
    L18:
        if (r4 != null) goto L20;
        return false;
    L20:
        if (isLanguageRestricted(r3.messageOwner.originalLanguage) == false) goto L22;
        return false;
    L28:
        return false;
    L25:
        return false;
    }

    public boolean canTranslateStory(TL_stories.StoryItem r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (TextUtils.isEmpty(r3.caption) == false) goto L6;
        return false;
    L6:
        if (Emoji.fullyConsistsOfEmojis(r3.caption) == false) goto L8;
        return false;
    L8:
        if (r3.detectedLng == null) goto L10;
    L13:
        String r32 = r3.detectedLng;
        if (r32 != null) goto L16;
        return false;
    L16:
        if (isLanguageRestricted(r32) == true) goto L25;
        return true;
    L25:
        return false;
    L10:
        if (r3.translatedText == null) goto L13;
        if (TextUtils.equals(r3.translatedLng, TranslateAlert2.getToLanguage()) == false) goto L13;
        return true;
    }

    public void cancelAllTranslations() {
        ChatTranslator.cancelAll();
        monitor-enter(this);
        Iterator<ArrayList<PendingTranslation>> r0 = this.pendingTranslations.values().iterator();     // Catch: Throwable -> L16
    L6:
        if (r0.hasNext() == false) goto L18;
        ArrayList<PendingTranslation> r1 = r0.next();     // Catch: Throwable -> L16
        if (r1 == null) goto L6;
        int r5 = r1.size();     // Catch: Throwable -> L16
        int r6 = 0;
    L10:
        if (r6 >= r5) goto L6;
        PendingTranslation r7 = r1.get(r6);     // Catch: Throwable -> L16
        r6 = r6 + 1;     // Catch: Throwable -> L16
        PendingTranslation r72 = r7;     // Catch: Throwable -> L16
        AndroidUtilities.cancelRunOnUIThread(r72.runnable);     // Catch: Throwable -> L16
        if (r72.reqId == (-1)) goto L10;
        getConnectionsManager().cancelRequest(r72.reqId, true);     // Catch: Throwable -> L16
        ArrayList<Integer> r73 = r72.messageIds;     // Catch: Throwable -> L16
        int r8 = r73.size();     // Catch: Throwable -> L16
        int r9 = 0;
    L14:
        if (r9 >= r8) goto L10;
        Integer r10 = r73.get(r9);     // Catch: Throwable -> L16
        r9 = r9 + 1;     // Catch: Throwable -> L16
        this.loadingTranslations.remove(r10);     // Catch: Throwable -> L16
        goto L14
    L18:
        Iterator<ArrayList<PendingTranslation>> r02 = this.pendingTranscriptionsTranslations.values().iterator();     // Catch: Throwable -> L16
    L20:
        if (r02.hasNext() == false) goto L30;
        ArrayList<PendingTranslation> r12 = r02.next();     // Catch: Throwable -> L16
        if (r12 == null) goto L20;
        int r52 = r12.size();     // Catch: Throwable -> L16
        int r62 = 0;
    L24:
        if (r62 >= r52) goto L20;
        PendingTranslation r74 = r12.get(r62);     // Catch: Throwable -> L16
        r62 = r62 + 1;     // Catch: Throwable -> L16
        PendingTranslation r75 = r74;     // Catch: Throwable -> L16
        AndroidUtilities.cancelRunOnUIThread(r75.runnable);     // Catch: Throwable -> L16
        if (r75.reqId == (-1)) goto L24;
        getConnectionsManager().cancelRequest(r75.reqId, true);     // Catch: Throwable -> L16
        ArrayList<Integer> r76 = r75.messageIds;     // Catch: Throwable -> L16
        int r82 = r76.size();     // Catch: Throwable -> L16
        int r92 = 0;
    L28:
        if (r92 >= r82) goto L24;
        Integer r102 = r76.get(r92);     // Catch: Throwable -> L16
        r92 = r92 + 1;     // Catch: Throwable -> L16
        this.loadingTranscriptionTranslations.remove(r102);     // Catch: Throwable -> L16
        goto L28
    L30:
        Iterator<ArrayList<PendingPollTranslation>> r03 = this.pendingPollTranslations.values().iterator();     // Catch: Throwable -> L16
    L32:
        if (r03.hasNext() == false) goto L42;
        ArrayList<PendingPollTranslation> r13 = r03.next();     // Catch: Throwable -> L16
        if (r13 == null) goto L32;
        int r53 = r13.size();     // Catch: Throwable -> L16
        int r63 = 0;
    L36:
        if (r63 >= r53) goto L32;
        PendingPollTranslation r77 = r13.get(r63);     // Catch: Throwable -> L16
        r63 = r63 + 1;     // Catch: Throwable -> L16
        PendingPollTranslation r78 = r77;     // Catch: Throwable -> L16
        AndroidUtilities.cancelRunOnUIThread(r78.runnable);     // Catch: Throwable -> L16
        if (r78.reqId == (-1)) goto L36;
        getConnectionsManager().cancelRequest(r78.reqId, true);     // Catch: Throwable -> L16
        ArrayList<Integer> r79 = r78.messageIds;     // Catch: Throwable -> L16
        int r83 = r79.size();     // Catch: Throwable -> L16
        int r93 = 0;
    L40:
        if (r93 >= r83) goto L36;
        Integer r103 = r79.get(r93);     // Catch: Throwable -> L16
        r93 = r93 + 1;     // Catch: Throwable -> L16
        this.loadingTranslations.remove(r103);     // Catch: Throwable -> L16
        goto L40
    L42:
        Iterator<ArrayList<PendingRichTranslation>> r04 = this.pendingRichTranslations.values().iterator();     // Catch: Throwable -> L16
    L44:
        if (r04.hasNext() == false) goto L54;
        ArrayList<PendingRichTranslation> r14 = r04.next();     // Catch: Throwable -> L16
        if (r14 == null) goto L44;
        int r54 = r14.size();     // Catch: Throwable -> L16
        int r64 = 0;
    L48:
        if (r64 >= r54) goto L44;
        PendingRichTranslation r710 = r14.get(r64);     // Catch: Throwable -> L16
        r64 = r64 + 1;     // Catch: Throwable -> L16
        PendingRichTranslation r711 = r710;     // Catch: Throwable -> L16
        AndroidUtilities.cancelRunOnUIThread(r711.runnable);     // Catch: Throwable -> L16
        if (r711.reqId == (-1)) goto L48;
        getConnectionsManager().cancelRequest(r711.reqId, true);     // Catch: Throwable -> L16
        ArrayList<Integer> r712 = r711.messageIds;     // Catch: Throwable -> L16
        int r84 = r712.size();     // Catch: Throwable -> L16
        int r94 = 0;
    L52:
        if (r94 >= r84) goto L48;
        Integer r104 = r712.get(r94);     // Catch: Throwable -> L16
        r94 = r94 + 1;     // Catch: Throwable -> L16
        this.loadingTranslations.remove(r104);     // Catch: Throwable -> L16
        goto L52
    L54:
        monitor-exit(this);     // Catch: Throwable -> L16
        return;
    L16:
        th = move-exception;
        throw th;
    }

    public void cancelTranslations(long r12) {
        ChatTranslator.cancel(r12);
        monitor-enter(this);
        ArrayList<PendingTranslation> r0 = this.pendingTranslations.get(Long.valueOf(r12));     // Catch: Throwable -> L13
        if (r0 == null) goto L16;
        int r4 = r0.size();     // Catch: Throwable -> L13
        int r5 = 0;
    L7:
        if (r5 >= r4) goto L15;
        PendingTranslation r6 = r0.get(r5);     // Catch: Throwable -> L13
        r5 = r5 + 1;     // Catch: Throwable -> L13
        PendingTranslation r62 = r6;     // Catch: Throwable -> L13
        AndroidUtilities.cancelRunOnUIThread(r62.runnable);     // Catch: Throwable -> L13
        if (r62.reqId == (-1)) goto L7;
        getConnectionsManager().cancelRequest(r62.reqId, true);     // Catch: Throwable -> L13
        ArrayList<Integer> r63 = r62.messageIds;     // Catch: Throwable -> L13
        int r7 = r63.size();     // Catch: Throwable -> L13
        int r8 = 0;
    L11:
        if (r8 >= r7) goto L7;
        Integer r9 = r63.get(r8);     // Catch: Throwable -> L13
        r8 = r8 + 1;     // Catch: Throwable -> L13
        this.loadingTranslations.remove(r9);     // Catch: Throwable -> L13
        goto L11
    L15:
        this.pendingTranslations.remove(Long.valueOf(r12));     // Catch: Throwable -> L13
    L16:
        ArrayList<PendingTranslation> r02 = this.pendingTranscriptionsTranslations.get(Long.valueOf(r12));     // Catch: Throwable -> L13
        if (r02 == null) goto L26;
        int r42 = r02.size();     // Catch: Throwable -> L13
        int r52 = 0;
    L19:
        if (r52 >= r42) goto L25;
        PendingTranslation r64 = r02.get(r52);     // Catch: Throwable -> L13
        r52 = r52 + 1;     // Catch: Throwable -> L13
        PendingTranslation r65 = r64;     // Catch: Throwable -> L13
        AndroidUtilities.cancelRunOnUIThread(r65.runnable);     // Catch: Throwable -> L13
        if (r65.reqId == (-1)) goto L19;
        getConnectionsManager().cancelRequest(r65.reqId, true);     // Catch: Throwable -> L13
        ArrayList<Integer> r66 = r65.messageIds;     // Catch: Throwable -> L13
        int r72 = r66.size();     // Catch: Throwable -> L13
        int r82 = 0;
    L23:
        if (r82 >= r72) goto L19;
        Integer r92 = r66.get(r82);     // Catch: Throwable -> L13
        r82 = r82 + 1;     // Catch: Throwable -> L13
        this.loadingTranscriptionTranslations.remove(r92);     // Catch: Throwable -> L13
        goto L23
    L25:
        this.pendingTranscriptionsTranslations.remove(Long.valueOf(r12));     // Catch: Throwable -> L13
    L26:
        ArrayList<PendingPollTranslation> r03 = this.pendingPollTranslations.get(Long.valueOf(r12));     // Catch: Throwable -> L13
        if (r03 == null) goto L36;
        int r43 = r03.size();     // Catch: Throwable -> L13
        int r53 = 0;
    L29:
        if (r53 >= r43) goto L35;
        PendingPollTranslation r67 = r03.get(r53);     // Catch: Throwable -> L13
        r53 = r53 + 1;     // Catch: Throwable -> L13
        PendingPollTranslation r68 = r67;     // Catch: Throwable -> L13
        AndroidUtilities.cancelRunOnUIThread(r68.runnable);     // Catch: Throwable -> L13
        if (r68.reqId == (-1)) goto L29;
        getConnectionsManager().cancelRequest(r68.reqId, true);     // Catch: Throwable -> L13
        ArrayList<Integer> r69 = r68.messageIds;     // Catch: Throwable -> L13
        int r73 = r69.size();     // Catch: Throwable -> L13
        int r83 = 0;
    L33:
        if (r83 >= r73) goto L29;
        Integer r93 = r69.get(r83);     // Catch: Throwable -> L13
        r83 = r83 + 1;     // Catch: Throwable -> L13
        this.loadingTranslations.remove(r93);     // Catch: Throwable -> L13
        goto L33
    L35:
        this.pendingPollTranslations.remove(Long.valueOf(r12));     // Catch: Throwable -> L13
    L36:
        ArrayList<PendingRichTranslation> r04 = this.pendingRichTranslations.get(Long.valueOf(r12));     // Catch: Throwable -> L13
        if (r04 == null) goto L46;
        int r44 = r04.size();     // Catch: Throwable -> L13
        int r54 = 0;
    L39:
        if (r54 >= r44) goto L45;
        PendingRichTranslation r610 = r04.get(r54);     // Catch: Throwable -> L13
        r54 = r54 + 1;     // Catch: Throwable -> L13
        PendingRichTranslation r611 = r610;     // Catch: Throwable -> L13
        AndroidUtilities.cancelRunOnUIThread(r611.runnable);     // Catch: Throwable -> L13
        if (r611.reqId == (-1)) goto L39;
        getConnectionsManager().cancelRequest(r611.reqId, true);     // Catch: Throwable -> L13
        ArrayList<Integer> r612 = r611.messageIds;     // Catch: Throwable -> L13
        int r74 = r612.size();     // Catch: Throwable -> L13
        int r84 = 0;
    L43:
        if (r84 >= r74) goto L39;
        Integer r94 = r612.get(r84);     // Catch: Throwable -> L13
        r84 = r84 + 1;     // Catch: Throwable -> L13
        this.loadingTranslations.remove(r94);     // Catch: Throwable -> L13
        goto L43
    L45:
        this.pendingRichTranslations.remove(Long.valueOf(r12));     // Catch: Throwable -> L13
    L46:
        monitor-exit(this);     // Catch: Throwable -> L13
        return;
    L13:
        th = move-exception;
        throw th;
    }

    public void checkDialogMessage(long r2) {
        if (isFeatureAvailable(r2) == false) goto L6;
        checkDialogMessageSure(r2);
        return;
    }

    public void checkDialogMessageSure(long r3) {
        if (this.translatingDialogs.get(r3, Boolean.valueOf(isChatAutoTranslated(r3))).booleanValue() == true) goto L5;
        return;
    L5:
        getMessagesStorage().getStorageQueue().postRunnable(new TranslateController$$ExternalSyntheticLambda2(this, r3));
    }

    public void checkRestrictedLanguagesUpdate() {
        monitor-enter(this);
        this.translatableDialogMessages.clear();     // Catch: Throwable -> L11
        ArrayList r0 = new ArrayList();     // Catch: Throwable -> L11
        Iterator<Long> r1 = this.translatableDialogs.iterator();     // Catch: Throwable -> L11
    L5:
        if (r1.hasNext() == false) goto L13;
        Long r2 = r1.next();     // Catch: Throwable -> L11
        long r3 = r2.longValue();     // Catch: Throwable -> L11
        String r5 = this.detectedDialogLanguage.get(r2);     // Catch: Throwable -> L11
        if (r5 == null) goto L5;
        if (isLanguageRestricted(r5) == false) goto L5;
        cancelTranslations(r3);     // Catch: Throwable -> L11
        this.translatingDialogs.remove(r3);     // Catch: Throwable -> L11
        r0.add(r2);     // Catch: Throwable -> L11
        goto L5
    L13:
        this.translatableDialogs.clear();     // Catch: Throwable -> L11
        saveTranslatingDialogsCache();     // Catch: Throwable -> L11
        int r12 = r0.size();     // Catch: Throwable -> L11
        int r22 = 0;
    L14:
        if (r22 >= r12) goto L16;
        Object r32 = r0.get(r22);     // Catch: Throwable -> L11
        r22 = r22 + 1;     // Catch: Throwable -> L11
        Long r33 = (Long) r32;     // Catch: Throwable -> L11
        r33.longValue();     // Catch: Throwable -> L11
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogTranslate, new Object[]{r33, Boolean.FALSE});     // Catch: Throwable -> L11
        goto L14
    L16:
        monitor-exit(this);     // Catch: Throwable -> L11
        return;
    L11:
        th = move-exception;
        throw th;
    }

    public void checkTranslation(MessageObject r2, boolean r3) {
        checkTranslation(r2, r3, false);
    }

    public void cleanup() {
        cancelAllTranslations();
        resetTranslatingDialogsCache();
        this.translatingDialogs.clear();
        this.translatableDialogs.clear();
        this.translatableDialogMessages.clear();
        this.translateDialogLanguage.clear();
        this.detectedDialogLanguage.clear();
        this.keptReplyMessageObjects.clear();
        this.hideTranslateDialogs.clear();
        this.loadingTranslations.clear();
        this.loadingTranscriptionTranslations.clear();
    }

    public void clearMessageTranslationState(MessageObject r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.messageOwner == null) goto L13;
        MessageObject r0 = r2.replyMessageObject;
        if (r0 == null) goto L10;
        if (r0 == r2) goto L10;
        clearMessageTranslationState(r0);
    L10:
        TLRPC.Message r1 = r2.messageOwner;
        r1.originalLanguage = null;
        r1.translatedText = null;
        r1.translatedVoiceTranscription = null;
        r1.translatedPoll = null;
        r1.translatedToLanguage = null;
        r1.summaryText = null;
        r1.translatedSummaryText = null;
        r1.translatedSummaryLanguage = null;
        r2.updateTranslation(false);
        return;
    }

    public void clearTranslationCache() {
        cleanup();
        ChatTranslator.clearCache();
        getMessagesStorage().clearAllMessageCustomParams();
    }

    public void detectPhotoLanguage(MessageObject r5, Utilities.Callback<String> r6) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (r5.messageOwner != null) goto L6;
        return;
    L6:
        if (LanguageDetector.hasSupport() == true) goto L8;
        return;
    L8:
        if (TextUtils.isEmpty(r5.messageOwner.message) == false) goto L11;
        return;
    L11:
        if (TextUtils.isEmpty(r5.messageOwner.originalLanguage) == true) goto L15;
        if (r6 == null) goto L23;
        r6.run(r5.messageOwner.originalLanguage);
        return;
    L23:
        return;
    L15:
        MessageKey r0 = new MessageKey(r5);
        if (this.detectingPhotos.contains(r0) == true) goto L25;
        this.detectingPhotos.add(r0);
        LanguageDetector.detectLanguage(r5.messageOwner.message, new TranslateController$$ExternalSyntheticLambda10(this, r5, r0, r6), new TranslateController$$ExternalSyntheticLambda11(this, r5, r0, r6));
        return;
    }

    public void detectStoryLanguage(TL_stories.StoryItem r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (r5.detectedLng != null) goto L18;
        String r0 = r5.caption;
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.length() != 0) goto L10;
        return;
    L10:
        if (LanguageDetector.hasSupport() == false) goto L21;
        StoryKey r02 = new StoryKey(r5);
        if (this.detectingStories.contains(r02) == true) goto L22;
        this.detectingStories.add(r02);
        LanguageDetector.detectLanguage(r5.caption, new TranslateController$$ExternalSyntheticLambda46(this, r5, r02), new TranslateController$$ExternalSyntheticLambda47(this, r5, r02));
        return;
    L22:
        return;
    L21:
        return;
    }

    public MessageObject findReplyMessageObject(long r1, int r3) {
        HashMap<Integer, MessageObject> r0 = this.keptReplyMessageObjects.get(Long.valueOf(r1));
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.get(Integer.valueOf(r3));
    }

    public String getDialogDetectedLanguage(long r1) {
        return this.detectedDialogLanguage.get(Long.valueOf(r1));
    }

    public String getDialogTranslateTo(long r3) {
        String r0 = this.translateDialogLanguage.get(Long.valueOf(r3));
        if (r0 != null) goto L12;
        r0 = TranslateAlert2.getToLanguage();
        if (TextUtils.isEmpty(r0) == false) goto L7;
    L10:
        r0 = currentLanguage();
        goto L12
    L7:
        if (TranslatorUtils.isRestrictedLanguage(getDialogDetectedLanguage(r3)) == true) goto L10;
        if (TextUtils.equals(TranslatorUtils.primaryLanguageOf(r0), TranslatorUtils.primaryLanguageOf(getDialogDetectedLanguage(r3))) == true) goto L10;
    L12:
        if ("nb".equals(r0) == false) goto L15;
        return "no";
    L15:
        return r0;
    }

    public void invalidateTranslation(MessageObject r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (r5.messageOwner == null) goto L12;
        long r0 = r5.getDialogId();
        if (isFeatureAvailable(r0) == false) goto L13;
        TLRPC.Message r2 = r5.messageOwner;
        r2.translatedToLanguage = null;
        r2.translatedText = null;
        r2.translatedVoiceTranscription = null;
        r2.translatedPoll = null;
        r2.summaryText = null;
        r2.translatedSummaryText = null;
        r2.translatedSummaryLanguage = null;
        updateMessageCustomParams(r0, r5);
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda44(this, r5, r0));
        return;
    L13:
        return;
    }

    public boolean isChatTranslateEnabled() {
        if (getMessagesController().isTranslationsAutoEnabled() == true) goto L8;
        if (TranslatorUtils.isAlternativeProvider() == true) goto L8;
        return false;
    L8:
        if (this.chatTranslateEnabled != null) goto L11;
        this.chatTranslateEnabled = Boolean.valueOf(this.messagesController.getMainSettings().getBoolean("translate_chat_button", false));
    L11:
        return this.chatTranslateEnabled.booleanValue();
    }

    public boolean isContextTranslateEnabled() {
        if (getMessagesController().isTranslationsManualEnabled() == true) goto L7;
        return false;
    L7:
        if (this.contextTranslateEnabled != null) goto L10;
        this.contextTranslateEnabled = Boolean.valueOf(this.messagesController.getMainSettings().getBoolean("translate_button", MessagesController.getGlobalMainSettings().getBoolean("translate_button", true)));
    L10:
        return this.contextTranslateEnabled.booleanValue();
    }

    public boolean isDialogTranslatable(long r3) {
        if (this.translatableDialogs.contains(Long.valueOf(r3)) == true) goto L5;
        return false;
    L5:
        if (isFeatureAvailable(r3) == true) goto L7;
        return false;
    L7:
        if (DialogObject.isEncryptedDialog(r3) == false) goto L9;
        return false;
    L9:
        if (getUserConfig().getClientUserId() == r3) goto L16;
        return true;
    L16:
        return false;
    }

    public boolean isFeatureAvailable() {
        if (TranslationProviders.isChatTranslationUnlocked(this.currentAccount) == true) goto L5;
        return false;
    L5:
        if (isChatTranslateEnabled() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isFeatureAvailable(long r2) {
        if (isChatTranslateEnabled() == true) goto L5;
        return false;
    L5:
        if (TranslationProviders.isChatTranslationUnlocked(this.currentAccount, r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isTranslateDialogHidden(long r4) {
        if (this.hideTranslateDialogs.contains(Long.valueOf(r4)) == false) goto L6;
        return true;
    L6:
        TLRPC.ChatFull r0 = getMessagesController().getChatFull(-r4);
        if (r0 != null) goto L9;
        TLRPC.UserFull r3 = getMessagesController().getUserFull(r4);
        if (r3 != null) goto L13;
        return false;
    L13:
        return r3.translations_disabled;
    L9:
        return r0.translations_disabled;
    }

    public boolean isTranslating(MessageObject r6) {
        if (r6 == null) goto L9;
        TLRPC.Message r0 = r6.messageOwner;
        if (r0 == null) goto L9;
        if (r0.summarizedOpen == false) goto L9;
        return this.loadingSummarizations.contains(Integer.valueOf(Objects.hash(new Object[]{Long.valueOf(r6.getDialogId()), Integer.valueOf(r6.getId()), Integer.valueOf(isTranslatingDialog(r6.getDialogId()) ? 1 : 0)})));
    L9:
        monitor-enter(this);
        if (r6 != null) goto L31;
    L26:
        boolean r62 = false;
    L27:
        monitor-exit(this);     // Catch: Throwable -> L18
        return r62;
    L18:
        th = move-exception;
        throw th;
    L31:
        TLRPC.Message r02 = r6.messageOwner;     // Catch: Throwable -> L18
        if (r02 == null) goto L26;
        if (r02.voiceTranscriptionOpen == true) goto L16;
    L20:
        Set<Integer> r03 = this.loadingTranslations;     // Catch: Throwable -> L18
    L22:
        if (r03.contains(Integer.valueOf(r6.getId())) == false) goto L26;
        if (isTranslatingDialog(r6.getDialogId()) == false) goto L26;
        r62 = true;
        goto L27
    L16:
        if (r02.voiceTranscriptionFinal == false) goto L20;
        r03 = this.loadingTranscriptionTranslations;     // Catch: Throwable -> L18
        goto L22
    }

    public boolean isTranslating(MessageObject r7, MessageObject.GroupedMessages r8) {
        if (r7 != null) goto L6;
        return false;
    L6:
        if (isTranslatingDialog(r7.getDialogId()) == true) goto L8;
        return false;
    L8:
        TLRPC.Message r1 = r7.messageOwner;
        if (r1 != null) goto L11;
    L15:
        boolean r12 = false;
    L16:
        monitor-enter(this);
        if (r12 == false) goto L21;
        Set<Integer> r3 = this.loadingTranscriptionTranslations;     // Catch: Throwable -> L19
    L23:
        if (r3.contains(Integer.valueOf(r7.getId())) == false) goto L26;
        monitor-exit(this);     // Catch: Throwable -> L19
        return true;
    L26:
        if (r8 == null) goto L37;
        ArrayList<MessageObject> r72 = r8.messages;     // Catch: Throwable -> L19
        int r82 = r72.size();     // Catch: Throwable -> L19
        int r32 = 0;
    L28:
        if (r32 >= r82) goto L37;
        MessageObject r4 = r72.get(r32);     // Catch: Throwable -> L19
        r32 = r32 + 1;     // Catch: Throwable -> L19
        MessageObject r42 = r4;     // Catch: Throwable -> L19
        if (r12 == false) goto L32;
        Set<Integer> r5 = this.loadingTranscriptionTranslations;     // Catch: Throwable -> L19
    L34:
        if (r5.contains(Integer.valueOf(r42.getId())) == false) goto L28;
        monitor-exit(this);     // Catch: Throwable -> L19
        return true;
    L32:
        r5 = this.loadingTranslations;     // Catch: Throwable -> L19
    L37:
        monitor-exit(this);     // Catch: Throwable -> L19
        return false;
    L21:
        r3 = this.loadingTranslations;     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        throw th;
    L11:
        if (r1.voiceTranscriptionOpen == false) goto L15;
        if (r1.voiceTranscriptionFinal == false) goto L15;
        r12 = true;
        goto L16
    }

    public boolean isTranslatingDialog(long r2) {
        if (isFeatureAvailable(r2) == true) goto L5;
        return false;
    L5:
        if (this.translatingDialogs.get(r2, Boolean.valueOf(isChatAutoTranslated(r2))).booleanValue() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isTranslatingStory(TL_stories.StoryItem r2) {
        if (r2 != null) goto L6;
        return false;
    L6:
        return this.translatingStories.contains(new StoryKey(r2));
    }

    public void reset() {
        this.translatableDialogMessages.clear();
        this.detectedDialogLanguage.clear();
    }

    public void setChatTranslateEnabled(boolean r3) {
        SharedPreferences.Editor r0 = this.messagesController.getMainSettings().edit();
        this.chatTranslateEnabled = Boolean.valueOf(r3);
        r0.putBoolean("translate_chat_button", r3).apply();
    }

    public void setContextTranslateEnabled(boolean r3) {
        SharedPreferences.Editor r0 = this.messagesController.getMainSettings().edit();
        this.contextTranslateEnabled = Boolean.valueOf(r3);
        r0.putBoolean("translate_button", r3).apply();
    }

    public void setDialogTranslateTo(long r4, String r6) {
        String r62 = TranslatorUtils.normalizeLanguageCode(r6);
        if (TextUtils.equals(getDialogTranslateTo(r4), r62) == false) goto L6;
        return;
    L6:
        if (isTranslatingDialog(r4) == false) goto L8;
        AndroidUtilities.runOnUIThread(new TranslateController$$ExternalSyntheticLambda35(this, r4, r62), 150);
    L11:
        cancelTranslations(r4);
        monitor-enter(this);
        LongSparseArray<Boolean> r63 = this.translatingDialogs;     // Catch: Throwable -> L17
        Boolean r0 = Boolean.FALSE;     // Catch: Throwable -> L17
        r63.put(r4, r0);     // Catch: Throwable -> L17
        monitor-exit(this);     // Catch: Throwable -> L17
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogTranslate, new Object[]{Long.valueOf(r4), r0});
        return;
    L17:
        th = move-exception;
        throw th;
    L8:
        monitor-enter(this);
        this.translateDialogLanguage.put(Long.valueOf(r4), r62);     // Catch: Throwable -> L20
        monitor-exit(this);     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        throw th;
    }

    public void setHideTranslateDialog(long r2, boolean r4) {
        setHideTranslateDialog(r2, r4, false);
    }

    public void setHideTranslateDialog(long r4, boolean r6, boolean r7) {
        TLRPC.TL_messages_togglePeerTranslations r0 = new TLRPC.TL_messages_togglePeerTranslations();
        r0.peer = getMessagesController().getInputPeer(r4);
        r0.disabled = r6;
        getConnectionsManager().sendRequest(r0, null);
        TLRPC.ChatFull r02 = getMessagesController().getChatFull(-r4);
        if (r02 == null) goto L5;
        r02.translations_disabled = r6;
        getMessagesStorage().updateChatInfo(r02, true);
    L5:
        TLRPC.UserFull r03 = getMessagesController().getUserFull(r4);
        if (r03 == null) goto L8;
        r03.translations_disabled = r6;
        getMessagesStorage().updateUserInfo(r03, true);
    L8:
        monitor-enter(this);
        Set<Long> r04 = this.hideTranslateDialogs;
        if (r6 == false) goto L14;
        r04.add(Long.valueOf(r4));     // Catch: Throwable -> L12
        this.translatingDialogs.remove(r4);     // Catch: Throwable -> L12
    L15:
        monitor-exit(this);     // Catch: Throwable -> L12
        saveTranslatingDialogsCache();
        if (r7 == true) goto L24;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogTranslate, new Object[]{Long.valueOf(r4), Boolean.valueOf(isTranslatingDialog(r4))});
        return;
    L24:
        return;
    L14:
        r04.remove(Long.valueOf(r4));     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        throw th;
    }

    public void toggleTranslatingDialog(long r2) {
        toggleTranslatingDialog(r2, !isTranslatingDialog(r2));
    }

    public boolean toggleTranslatingDialog(long r5, boolean r7) {
        boolean r0 = isTranslatingDialog(r5);
        boolean r1 = true;
        if (r7 == false) goto L6;
        if (r0 == true) goto L6;
        LongSparseArray<Boolean> r72 = this.translatingDialogs;
        Boolean r02 = Boolean.TRUE;
        r72.put(r5, r02);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogTranslate, new Object[]{Long.valueOf(r5), r02});
    L10:
        saveTranslatingDialogsCache();
        return r1;
    L6:
        if (r7 == true) goto L9;
        if (r0 == false) goto L9;
        LongSparseArray<Boolean> r73 = this.translatingDialogs;
        Boolean r03 = Boolean.FALSE;
        r73.put(r5, r03);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogTranslate, new Object[]{Long.valueOf(r5), r03});
        cancelTranslations(r5);
    L9:
        r1 = false;
        goto L10
    }

    public void translatePhoto(MessageObject r12, Runnable r13) {
        if (r12 != null) goto L4;
        return;
    L4:
        if (r12.messageOwner == null) goto L26;
        MessageKey r5 = new MessageKey(r12);
        String r4 = TranslateAlert2.getToLanguage();
        TLRPC.Message r0 = r12.messageOwner;
        if (r0.translatedText == null) goto L14;
        if (TextUtils.equals(r0.translatedToLanguage, r4) == false) goto L14;
        if (r13 == null) goto L24;
        r13.run();
        return;
    L24:
        return;
    L14:
        if (this.translatingPhotos.contains(r5) == false) goto L18;
        if (r13 == null) goto L25;
        r13.run();
        return;
    L25:
        return;
    L18:
        this.translatingPhotos.add(r5);
        TLRPC.TL_messages_translateText r02 = new TLRPC.TL_messages_translateText();
        r02.flags |= 2;
        TLRPC.TL_textWithEntities r9 = new TLRPC.TL_textWithEntities();
        TLRPC.Message r1 = r12.messageOwner;
        r9.text = r1.message;
        ArrayList<TLRPC.MessageEntity> r14 = r1.entities;
        r9.entities = r14;
        if (r14 != null) goto L21;
        r9.entities = new ArrayList();
    L21:
        r02.text.add(r9);
        r02.to_lang = normalizeLanguage(r4);
        getConnectionsManager().sendRequest(r02, new TranslateController$$ExternalSyntheticLambda45(this, r12, r4, r5, r13, System.currentTimeMillis(), r9));
        return;
    }

    public void translateStory(TL_stories.StoryItem r10, Runnable r11) {
        if (r10 == null) goto L20;
        StoryKey r4 = new StoryKey(r10);
        String r3 = TranslatorUtils.normalizeLanguageCode(TranslateAlert2.getToLanguage());
        if (r10.translatedText == null) goto L12;
        if (TextUtils.equals(r10.translatedLng, r3) == false) goto L12;
        if (r11 == null) goto L18;
        r11.run();
        return;
    L18:
        return;
    L12:
        if (this.translatingStories.contains(r4) == false) goto L16;
        if (r11 == null) goto L19;
        r11.run();
        return;
    L19:
        return;
    L16:
        this.translatingStories.add(r4);
        TLRPC.TL_messages_translateText r7 = new TLRPC.TL_messages_translateText();
        r7.flags |= 2;
        TLRPC.TL_textWithEntities r6 = new TLRPC.TL_textWithEntities();
        r6.text = r10.caption;
        r6.entities = r10.entities;
        r7.text.add(r6);
        r7.to_lang = normalizeLanguage(r3);
        getConnectionsManager().sendRequest(r7, new TranslateController$$ExternalSyntheticLambda48(this, r10, r3, r4, r11, r6));
        return;
    }

    public void updateDialogFull(long r5) {
        if (isFeatureAvailable(r5) == true) goto L5;
        return;
    L5:
        if (isDialogTranslatable(r5) == false) goto L31;
        boolean r0 = this.hideTranslateDialogs.contains(Long.valueOf(r5));
        TLRPC.ChatFull r1 = getMessagesController().getChatFull(-r5);
        if (r1 == null) goto L10;
        boolean r12 = r1.translations_disabled;
    L14:
        monitor-enter(this);
        Set<Long> r2 = this.hideTranslateDialogs;
        if (r12 == false) goto L20;
        r2.add(Long.valueOf(r5));     // Catch: Throwable -> L18
        this.translatingDialogs.remove(r5);     // Catch: Throwable -> L18
    L21:
        monitor-exit(this);     // Catch: Throwable -> L18
        if (r0 == r12) goto L30;
        saveTranslatingDialogsCache();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.dialogTranslate, new Object[]{Long.valueOf(r5), Boolean.valueOf(isTranslatingDialog(r5))});
        return;
    L30:
        return;
    L20:
        r2.remove(Long.valueOf(r5));     // Catch: Throwable -> L18
    L18:
        th = move-exception;
        throw th;
    L10:
        TLRPC.UserFull r13 = getMessagesController().getUserFull(r5);
        if (r13 == null) goto L13;
        r12 = r13.translations_disabled;
        goto L14
    L13:
        r12 = false;
        goto L14
    }
}
