package com.exteragram.messenger.translator;

import android.text.TextUtils;
import android.text.style.URLSpan;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.translator.core.BaseTranslator;
import com.exteragram.messenger.translator.core.TranslationError;
import com.exteragram.messenger.translator.providers.GoogleTranslator;
import com.exteragram.messenger.translator.providers.TelegramTranslator;
import com.exteragram.messenger.utils.chats.ChatUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LanguageDetector;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.TranslateController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.TranslateAlert2;
import org.telegram.ui.RestrictedLanguagesSelectActivity;

/* loaded from: classes4.dex */
public abstract class TranslatorUtils {
    private static final String[] CHROME_VERSIONS = null;
    private static final String[] DEVICE_MODELS = null;
    private static final LinkedHashMap<String, String> detectedLanguages = null;

    /* renamed from: com.exteragram.messenger.translator.TranslatorUtils$1, reason: invalid class name */
    public class AnonymousClass1 extends LinkedHashMap<String, String> {
        public AnonymousClass1(int r1, float r2, boolean r3) {
            super(r1, r2, r3);
        }

        @Override // java.util.LinkedHashMap
        public boolean removeEldestEntry(Map.Entry<String, String> r1) {
            if (size() <= 200) goto L6;
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: com.exteragram.messenger.translator.TranslatorUtils$2, reason: invalid class name */
    public class AnonymousClass2 implements TranslateCallback {
        final /* synthetic */ TranslateCallback val$callback;

        public AnonymousClass2(TranslateCallback r1) {
            this.val$callback = r1;
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onFailed() {
            this.val$callback.onFailed();
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onReqId(int r1) {
            this.val$callback.onReqId(r1);
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onSuccess(String r3) {
            boolean r0 = TextUtils.isEmpty(r3);
            TranslateCallback r1 = this.val$callback;
            if (r0 == false) goto L6;
            Objects.requireNonNull(r1);
            AndroidUtilities.runOnUIThread(new TranslatorUtils$$ExternalSyntheticLambda9(r1));
            return;
        L6:
            r1.onSuccess(r3);
            TLRPC.TL_textWithEntities r02 = new TLRPC.TL_textWithEntities();
            r02.text = r3;
            this.val$callback.onSuccess(r02);
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onSuccess(TLObject r1, TLRPC.TL_error r2) {
            this.val$callback.onSuccess(r1, r2);
        }
    }

    /* renamed from: com.exteragram.messenger.translator.TranslatorUtils$3, reason: invalid class name */
    public class AnonymousClass3 implements TranslateCallback {
        final /* synthetic */ TranslateCallback val$callback;

        public AnonymousClass3(TranslateCallback r1) {
            this.val$callback = r1;
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onFailed() {
            this.val$callback.onFailed();
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onFailed(TranslationError r1) {
            this.val$callback.onFailed(r1);
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onReqId(int r1) {
            this.val$callback.onReqId(r1);
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onSuccess(String r3) {
            boolean r0 = TextUtils.isEmpty(r3);
            TranslateCallback r1 = this.val$callback;
            if (r0 == false) goto L6;
            r1.onFailed();
            return;
        L6:
            r1.onSuccess(r3);
            TLRPC.TL_textWithEntities r02 = new TLRPC.TL_textWithEntities();
            r02.text = r3;
            this.val$callback.onSuccess(r02);
        }

        @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
        public void onSuccess(TLObject r1, TLRPC.TL_error r2) {
            this.val$callback.onSuccess(r1, r2);
        }
    }

    public interface TranslateCallback {
        void onFailed();

        default void onFailed(TranslationError r1) {
            onFailed();
        }

        default void onReqId(int r1) {
        }

        default void onSuccess(String r1) {
        }

        default void onSuccess(TLObject r1, TLRPC.TL_error r2) {
        }

        default void onSuccess(TLRPC.TL_textWithEntities r1) {
        }
    }

    public static /* synthetic */ boolean $r8$lambda$11DvPkfP7ZwrvlXZrS9WJMogx0w(String r0, TranslateController.Language r1) {
        return TextUtils.equals(r1.code, r0);
    }

    public static /* synthetic */ void $r8$lambda$1dOyQLk62lB6Qv0B49yZEIsZQYw(TranslateCallback r0, TLRPC.TL_messages_translateResult r1, TLRPC.TL_error r2, TLRPC.TL_textWithEntities r3, TLRPC.TL_textWithEntities r4, String r5) {
        r0.onSuccess(r1, r2);
        if (r3 != null) goto L6;
        r3 = r4;
    L6:
        r0.onSuccess(r3);
        r0.onSuccess(r5);
    }

    public static /* synthetic */ void $r8$lambda$2xKhWwpI4GW7E2vW__OfQr9ITvc(String r1, Utilities.Callback r2, String r3) {
        AndroidUtilities.runOnUIThread(new TranslatorUtils$$ExternalSyntheticLambda20(r3, r1, r2));
    }

    public static /* synthetic */ Boolean $r8$lambda$7fTBIuTIO9V2wJq9zH7Ge3YAw5E(ChatActivity r2, MessageObject r3, URLSpan r4) {
        if (r2 == null) goto L5;
        r2.didPressMessageUrl(r4, false, r3, null);
    L5:
        return Boolean.TRUE;
    }

    public static /* synthetic */ String $r8$lambda$9wamQUAxKHn3zFwtVLni_f9_rT8(String r1) {
        String r12 = normalizeLanguageCode(r1);
        if (TextUtils.isEmpty(r12) == false) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return r12.toUpperCase(Locale.US);
    }

    public static /* synthetic */ String $r8$lambda$AePc8vnRmVBvbDESBiN2nEUtenQ(TranslateController.Language r0) {
        return r0.displayName;
    }

    /* renamed from: $r8$lambda$Av-BpO6NUnG0lGRWDSvK3_cNj4M, reason: not valid java name */
    public static /* synthetic */ boolean m1737$r8$lambda$AvBpO6NUnG0lGRWDSvK3_cNj4M(String r0, String r1) {
        return TextUtils.equals(r1, r0);
    }

    public static /* synthetic */ void $r8$lambda$FKvgu0pUqhWBvJ_vWJkHowACv4I(ChatActivity r1) {
        if (r1 == null) goto L5;
        r1.dimBehindView(false);
        return;
    }

    /* renamed from: $r8$lambda$I-lV4XedozzE0d4j6rEd54pleY0, reason: not valid java name */
    public static /* synthetic */ boolean m1738$r8$lambda$IlV4XedozzE0d4j6rEd54pleY0(String r0, TranslateController.Language r1) {
        return TextUtils.equals(r1.code, r0);
    }

    public static /* synthetic */ String $r8$lambda$KkUOcCeziy70Lml0BRU39cA3J5c(TranslateController.Language r0) {
        String r02 = r0.displayName;
        if (r02 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return r02;
    }

    public static /* synthetic */ void $r8$lambda$RaYg0oz_vg60aFgnHZ0iJpaAus0(BaseFragment r14, TLRPC.InputPeer r15, int r16, CharSequence r17, ArrayList r18, Utilities.CallbackReturn r19, ChatActivity r20, String r21) {
        if (r14.getContext() != null) goto L6;
        return;
    L6:
        if (TranslateController.UNKNOWN_LANGUAGE.equals(r21) == false) goto L8;
        String r7 = null;
    L9:
        TranslateAlert2.showAlert(r14.getContext(), r14, UserConfig.selectedAccount, r15, r16, false, r7, TranslateAlert2.getToLanguage(), r17, r18, false, r19, new TranslatorUtils$$ExternalSyntheticLambda16(r20));
        return;
    L8:
        r7 = r21;
        goto L9
    }

    public static /* synthetic */ String $r8$lambda$XMV_ybzhq44j929f1pF9wAYSsPc(TranslateController.Language r0) {
        return r0.ownDisplayName;
    }

    public static /* synthetic */ boolean $r8$lambda$ZtBb2LzIdqbcBJlTdJioQ0OthG0(TranslateController.Language r2) {
        if (r2 != null) goto L4;
        return true;
    L4:
        if (TextUtils.isEmpty(r2.code) == false) goto L6;
        return true;
    L6:
        if (r2.code.contains("-") == false) goto L8;
        return true;
    L8:
        if (r2.code.contains("_") == true) goto L16;
        return false;
    L16:
        return true;
    }

    /* renamed from: $r8$lambda$d4RRdZ2YONVAqB-l9t6YkN7hzKI, reason: not valid java name */
    public static /* synthetic */ void m1739$r8$lambda$d4RRdZ2YONVAqBl9t6YkN7hzKI(CharSequence r7, String r8, TranslateCallback r9, TLRPC.TL_textWithEntities r10, TLObject r11, TLRPC.TL_error r12) {
        if (r12 == null) goto L8;
        if ("TRANSLATIONS_DISABLED_ALT".equalsIgnoreCase(r12.text) == false) goto L8;
        GoogleTranslator.getInstance().translate(r7.toString(), "auto", r8, new AnonymousClass2(r9));
        return;
    L8:
        if ((r11 instanceof TLRPC.TL_messages_translateResult) == false) goto L27;
        TLRPC.TL_messages_translateResult r2 = (TLRPC.TL_messages_translateResult) r11;
        if (r2.result.isEmpty() == true) goto L27;
        if (r2.result.get(0) == null) goto L27;
        if (r2.result.get(0).text == null) goto L27;
        TLRPC.TL_textWithEntities r5 = r2.result.get(0);
        TLRPC.TL_textWithEntities r4 = TranslateAlert2.preprocess(r10, r5);
        if (r4 == null) goto L20;
        String r72 = r4.text;
        if (r72 == null) goto L20;
    L19:
        String r6 = r72;
        if (TextUtils.isEmpty(r6) == false) goto L25;
        Objects.requireNonNull(r9);
        AndroidUtilities.runOnUIThread(new TranslatorUtils$$ExternalSyntheticLambda9(r9));
        return;
    L25:
        AndroidUtilities.runOnUIThread(new TranslatorUtils$$ExternalSyntheticLambda10(r9, r2, r12, r4, r5, r6));
        return;
    L20:
        r72 = r5.text;
    L27:
        Objects.requireNonNull(r9);
        AndroidUtilities.runOnUIThread(new TranslatorUtils$$ExternalSyntheticLambda9(r9));
    }

    /* renamed from: $r8$lambda$ewB-zqNshujzN4u5khqF8JrQ9SQ, reason: not valid java name */
    public static /* synthetic */ void m1740$r8$lambda$ewBzqNshujzN4u5khqF8JrQ9SQ(CharSequence r0, String r1, ArrayList r2, TranslateCallback r3, Exception r4) {
        translate(r0, "auto", r1, r2, r3);
    }

    /* renamed from: $r8$lambda$f3d3Ft6q6zFubK-4UJE3vsMG9PY, reason: not valid java name */
    public static /* synthetic */ void m1741$r8$lambda$f3d3Ft6q6zFubK4UJE3vsMG9PY(Utilities.Callback r0, Exception r1) {
        AndroidUtilities.runOnUIThread(new TranslatorUtils$$ExternalSyntheticLambda19(r0));
    }

    public static /* synthetic */ void $r8$lambda$mPJfZoaxE0BvL4sm1mDLCdphXQc(String r1, String r2, Utilities.Callback r3) {
        if (TextUtils.isEmpty(r1) == false) goto L5;
        r1 = TranslateController.UNKNOWN_LANGUAGE;
    L5:
        detectedLanguages.put(r2, r1);
        if (r3 == null) goto L9;
        r3.run(r1);
        return;
    }

    /* renamed from: $r8$lambda$rDYsZ2-vAV7cgqwAldx9nhgHWvQ, reason: not valid java name */
    public static /* synthetic */ void m1742$r8$lambda$rDYsZ2vAV7cgqwAldx9nhgHWvQ(CharSequence r1, String r2, ArrayList r3, TranslateCallback r4, String r5) {
        if (r5 != null) goto L4;
    L6:
        r5 = "auto";
    L7:
        translate(r1, r5, r2, r3, r4);
        return;
    L4:
        if (r5.equals(TranslateController.UNKNOWN_LANGUAGE) == true) goto L6;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$sClQ6y9RXd_b_HBdxIY4yCcvIag(Utilities.Callback r1) {
        if (r1 == null) goto L5;
        r1.run(null);
        return;
    }

    static {
        DEVICE_MODELS = new String[]{"Galaxy S6", "Galaxy S7", "Galaxy S8", "Galaxy S9", "Galaxy S10", "Galaxy S21", "Pixel 3", "Pixel 4", "Pixel 5", "OnePlus 6", "OnePlus 7", "OnePlus 8", "OnePlus 9", "Xperia XZ", "Xperia XZ2", "Xperia XZ3", "Xperia 1", "Xperia 5", "Xperia 10", "Xperia L4"};
        CHROME_VERSIONS = new String[]{"111.0.5563.57", "94.0.4606.81", "80.0.3987.119", "69.0.3497.100", "92.0.4515.159", "71.0.3578.99"};
        detectedLanguages = new AnonymousClass1(16, 0.75f, true);
    }

    private static TranslateController.Language createLanguageItem(String r5) {
        String r52 = normalizeLanguageCode(r5);
        TranslateController.Language r0 = new TranslateController.Language();
        r0.code = r52;
        Locale r1 = LocaleController.getInstance().getCurrentLocale();
        if (r1 != null) goto L5;
        r1 = Locale.getDefault();
    L5:
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r52 != null) goto L8;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        Locale r32 = Locale.forLanguageTag(r3);
        String r12 = r32.getDisplayName(r1);
        String r33 = r32.getDisplayName(r32);
        if (TextUtils.isEmpty(r12) == false) goto L13;
        r12 = TranslateAlert2.capitalFirst(TranslateAlert2.languageName(r52));
    L13:
        if (TextUtils.isEmpty(r33) == false) goto L16;
        r33 = TranslateAlert2.capitalFirst(TranslateAlert2.systemLanguageName(r52, true));
    L16:
        if (TextUtils.isEmpty(r12) == false) goto L22;
        if (r52 == null) goto L20;
        r2 = r52.toUpperCase(Locale.US);
    L20:
        r12 = r2;
    L22:
        if (TextUtils.isEmpty(r33) == false) goto L24;
        r33 = r12;
    L24:
        r0.displayName = TranslateAlert2.capitalFirst(r12);
        r0.ownDisplayName = TranslateAlert2.capitalFirst(r33);
        r0.q = (r0.displayName + " " + r0.ownDisplayName).toLowerCase(Locale.US);
        return r0;
    L8:
        r3 = r52;
        goto L9
    }

    public static void detectMessageLanguage(MessageObject r2, MessageObject.GroupedMessages r3, Utilities.Callback<String> r4) {
        if (r2 != null) goto L7;
        if (r4 == null) goto L22;
        r4.run(null);
        return;
    L22:
        return;
    L7:
        String r1 = getDetectedLanguage(r2);
        if (r1 == null) goto L12;
        if (r4 == null) goto L23;
        r4.run(r1);
        return;
    L23:
        return;
    L12:
        CharSequence r32 = ChatUtils.getInstance().getMessageText(r2, r3);
        if (TextUtils.isEmpty(r32) == false) goto L15;
    L19:
        if (r4 == null) goto L24;
        r4.run(null);
        return;
    L24:
        return;
    L15:
        if (LanguageDetector.hasSupport() == false) goto L19;
        String r22 = detectedLanguageKey(r2);
        LanguageDetector.detectLanguage(r32.toString(), new TranslatorUtils$$ExternalSyntheticLambda17(r22, r4), new TranslatorUtils$$ExternalSyntheticLambda18(r4));
    }

    private static String detectedLanguageKey(MessageObject r3) {
        return r3.getDialogId() + ":" + r3.getId();
    }

    public static void ensureTargetLanguageCompatibleWithProvider() {
        if (isTargetLanguageSupportedForCurrentProvider(ExteraConfig.getTargetLang()) == true) goto L6;
        setTargetLanguage(Common.ASSET_APP);
        return;
    }

    public static String formatUserAgent() {
        String r0 = String.valueOf(Utilities.random.nextInt(7) + 6);
        String[] r1 = DEVICE_MODELS;
        String r12 = r1[Utilities.random.nextInt(r1.length)];
        String[] r2 = CHROME_VERSIONS;
        return String.format("Mozilla/5.0 (Linux; Android %s; %s) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/%s Mobile Safari/537.36", new Object[]{r0, r12, r2[Utilities.random.nextInt(r2.length)]});
    }

    public static ArrayList<TranslateController.Language> getCurrentTargetLanguages() {
        BaseTranslator r0 = getCurrentTranslator();
        Set<String> r1 = r0.getSupportedLanguages();
        if (r1 != null) goto L5;
    L19:
        ArrayList<TranslateController.Language> r12 = new ArrayList(TranslateController.getLanguages());
        if (r0 != TelegramTranslator.getInstance()) goto L22;
        r12.removeIf(new TranslatorUtils$$ExternalSyntheticLambda6());
    L22:
        return r12;
    L5:
        if (r1.isEmpty() == true) goto L19;
        HashSet r02 = new HashSet();
        Iterator<String> r13 = r1.iterator();
    L9:
        if (r13.hasNext() == false) goto L13;
        String r2 = normalizeLanguageCode(r13.next());
        if (TextUtils.isEmpty(r2) == true) goto L9;
        r02.add(r2);
        goto L9
    L13:
        ArrayList<TranslateController.Language> r14 = new ArrayList();
        Iterator r03 = r02.iterator();
    L15:
        if (r03.hasNext() == false) goto L17;
        r14.add(createLanguageItem((String) r03.next()));
        goto L15
    L17:
        r14.sort(Comparator.comparing(new TranslatorUtils$$ExternalSyntheticLambda7(), new TranslatorUtils$$ExternalSyntheticLambda8()));
        return r14;
    }

    public static BaseTranslator getCurrentTranslator() {
        return TranslationProviders.current();
    }

    public static String getCurrentTranslatorName() {
        return getCurrentTranslator().getDisplayName();
    }

    public static String getDetectedLanguage(MessageObject r1) {
        if (r1 != null) goto L5;
        return null;
    L5:
        TLRPC.Message r0 = r1.messageOwner;
        if (r0 == null) goto L12;
        if (TextUtils.isEmpty(r0.originalLanguage) == true) goto L12;
        return r1.messageOwner.originalLanguage;
    L12:
        return detectedLanguages.get(detectedLanguageKey(r1));
    }

    private static List<TranslateController.Language> getIndexedTargetLanguages() {
        return getCurrentTargetLanguages();
    }

    public static String getLanguageDisplayName(String r2) {
        return (String) getLanguages().stream().filter(new TranslatorUtils$$ExternalSyntheticLambda0(r2)).findFirst().map(new TranslatorUtils$$ExternalSyntheticLambda1()).orElse(null);
    }

    public static String getLanguageTitleSystem(String r2) {
        if ("none".equals(r2) == false) goto L7;
        return LocaleController.getString(R.string.None);
    L7:
        return (String) getLanguages().stream().filter(new TranslatorUtils$$ExternalSyntheticLambda2(r2)).findFirst().map(new TranslatorUtils$$ExternalSyntheticLambda3()).orElseGet(new TranslatorUtils$$ExternalSyntheticLambda4(r2));
    }

    private static List<TranslateController.Language> getLanguages() {
        return new ArrayList(TranslateController.getLanguages());
    }

    public static ArrayList<String> getRecentSendTargetLanguages() {
        ArrayList<String> r0 = new ArrayList();
        String r1 = ExteraConfig.getPreferences().getString("targetLangSendRecent", null);
        if (TextUtils.isEmpty(r1) == true) goto L18;
        HashSet r2 = new HashSet();
        String[] r12 = r1.split(",");
        int r3 = r12.length;
        int r4 = 0;
    L6:
        if (r4 >= r3) goto L18;
        String r5 = normalizeLanguageCode(r12[r4]);
        if (TextUtils.isEmpty(r5) == true) goto L17;
        if (r2.contains(r5) == true) goto L17;
        if (isTargetLanguageSupportedForCurrentProvider(r5) == false) goto L17;
        r2.add(r5);
        r0.add(r5);
        if (r0.size() >= 3) goto L18;
    L17:
        r4 = r4 + 1;
    L18:
        return r0;
    }

    private static String getResolvedAppLanguageCode() {
        Locale r0 = LocaleController.getInstance().getCurrentLocale();
        if (r0 != null) goto L5;
        r0 = Locale.getDefault();
    L5:
        String r1 = normalizeLanguageCode(r0.getLanguage());
        String r02 = r0.getCountry();
        if (TextUtils.isEmpty(r1) == true) goto L13;
        if (TextUtils.isEmpty(r02) == true) goto L13;
        String r03 = normalizeLanguageCode(r1 + "-" + r02);
        if (isTargetLanguageSupportedForCurrentProvider(r03) == false) goto L13;
        return r03;
    L13:
        if (isTargetLanguageSupportedForCurrentProvider(r1) == true) goto L18;
        String r04 = normalizeLanguageCode(LocaleController.getString(R.string.LanguageCode));
        if (isTargetLanguageSupportedForCurrentProvider(r04) == false) goto L18;
        return r04;
    L18:
        return r1;
    }

    public static String getResolvedSendTargetLanguageCode() {
        String r0 = getStoredSendTargetLanguage();
        if (TextUtils.isEmpty(r0) == false) goto L7;
        return "en";
    L7:
        return getResolvedTargetLanguageCode(r0);
    }

    public static String getResolvedTargetLanguageCode() {
        return getResolvedTargetLanguageCode(ExteraConfig.getTargetLang());
    }

    public static String getResolvedTargetLanguageCode(String r1) {
        if (Common.ASSET_APP.equalsIgnoreCase(r1) == true) goto L5;
        String r12 = normalizeLanguageCode(r1);
        if (TextUtils.isEmpty(r12) == true) goto L9;
        return r12;
    L9:
        return getResolvedAppLanguageCode();
    L5:
        return getResolvedAppLanguageCode();
    }

    public static int getSendTargetLanguageIndex() {
        String r0 = getStoredSendTargetLanguage();
        if (TextUtils.isEmpty(r0) == false) goto L6;
        r0 = "en";
    L6:
        return getTargetLanguageIndexByCode(r0);
    }

    public static String getSendTargetLanguageTitle() {
        return getLanguageTitleSystem(getResolvedSendTargetLanguageCode());
    }

    private static String getStoredSendTargetLanguage() {
        return ExteraConfig.getPreferences().getString("targetLangSend", null);
    }

    public static String getTargetLanguageCodeByIndex(int r2) {
        if (r2 != 0) goto L5;
        return Common.ASSET_APP;
    L5:
        List<TranslateController.Language> r0 = getIndexedTargetLanguages();
        int r22 = r2 - 1;
        if (r22 >= 0) goto L8;
        return null;
    L8:
        if (r22 < r0.size()) goto L10;
        return null;
    L10:
        return r0.get(r22).code;
    }

    public static int getTargetLanguageIndexByCode(String r4) {
        if (Common.ASSET_APP.equalsIgnoreCase(r4) == false) goto L5;
        return 0;
    L5:
        List<TranslateController.Language> r0 = getIndexedTargetLanguages();
        String r42 = getResolvedTargetLanguageCode(r4);
        int r2 = 0;
    L7:
        if (r2 >= r0.size()) goto L13;
        if (TextUtils.equals(r0.get(r2).code, r42) == true) goto L11;
        r2 = r2 + 1;
        goto L7
    L11:
        return r2 + 1;
    L13:
        return 0;
    }

    public static String getTargetLanguageTitle() {
        if (isTargetLanguageFollowApp() == false) goto L7;
        return LocaleController.getString(R.string.TranslationTargetApp);
    L7:
        return getLanguageTitleSystem(getResolvedTargetLanguageCode());
    }

    public static CharSequence[] getTargetLanguageTitles() {
        List<TranslateController.Language> r0 = getIndexedTargetLanguages();
        CharSequence[] r1 = new CharSequence[r0.size() + 1];
        int r3 = 0;
        r1[0] = LocaleController.getString(R.string.TranslationTargetApp);
    L4:
        if (r3 >= r0.size()) goto L10;
        TranslateController.Language r2 = r0.get(r3);
        r3 = r3 + 1;
        StringBuilder r4 = new StringBuilder();
        r4.append(r2.displayName);
        if (r2.ownDisplayName != null) goto L8;
        String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        r4.append(r22);
        r1[r3] = r4.toString();
        goto L4
    L8:
        r22 = " – " + r2.ownDisplayName;
        goto L9
    L10:
        return r1;
    }

    public static boolean isAlternativeProvider() {
        return TranslationProviders.isAlternative();
    }

    public static boolean isMessageLanguageRestricted(MessageObject r1) {
        String r12 = getDetectedLanguage(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        if (TranslateController.UNKNOWN_LANGUAGE.equals(r12) == false) goto L7;
        return false;
    L7:
        if (isRestrictedLanguage(r12) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public static boolean isRestrictedLanguage(String r4) {
        if (TextUtils.isEmpty(r4) == false) goto L6;
        return false;
    L6:
        if (TextUtils.equals(primaryLanguageOf(r4), primaryLanguageOf(getResolvedTargetLanguageCode())) == false) goto L8;
        return true;
    L8:
        String r42 = primaryLanguageOf(r4);
        if (TextUtils.isEmpty(r42) == false) goto L11;
        return false;
    L11:
        Iterator<String> r0 = RestrictedLanguagesSelectActivity.getRestrictedLanguages().iterator();
    L13:
        if (r0.hasNext() == false) goto L17;
        if (TextUtils.equals(r42, primaryLanguageOf(r0.next())) == false) goto L13;
        return true;
    L17:
        return false;
    }

    public static boolean isSendTargetLanguageFollowApp() {
        return Common.ASSET_APP.equalsIgnoreCase(getStoredSendTargetLanguage());
    }

    public static boolean isTargetLanguageFollowApp() {
        return Common.ASSET_APP.equalsIgnoreCase(ExteraConfig.getTargetLang());
    }

    public static boolean isTargetLanguageSupportedForCurrentProvider(String r5) {
        if (Common.ASSET_APP.equalsIgnoreCase(r5) == false) goto L5;
        return true;
    L5:
        String r52 = normalizeLanguageCode(r5);
        if (TextUtils.isEmpty(r52) == false) goto L8;
        return false;
    L8:
        BaseTranslator r0 = getCurrentTranslator();
        Set<String> r3 = r0.getSupportedLanguages();
        if (r3 == null) goto L21;
        if (r3.isEmpty() == true) goto L21;
        Iterator<String> r02 = r3.iterator();
    L15:
        if (r02.hasNext() == false) goto L19;
        if (TextUtils.equals(normalizeLanguageCode(r02.next()), r52) == false) goto L15;
        return true;
    L19:
        return false;
    L21:
        if (r0 == TelegramTranslator.getInstance()) goto L23;
    L26:
        return true;
    L23:
        if (r52.contains("-") == false) goto L26;
        return false;
    }

    public static String normalizeLanguageCode(String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L6;
        return null;
    L6:
        String r22 = r2.trim().toLowerCase(Locale.US).replace('_', SignatureVisitor.SUPER);
        if ("nb".equals(r22) == false) goto L10;
        return "no";
    L10:
        return r22;
    }

    public static String primaryLanguageOf(String r2) {
        String r22 = normalizeLanguageCode(r2);
        if (TextUtils.isEmpty(r22) == false) goto L6;
        return null;
    L6:
        int r0 = r22.indexOf(45);
        if (r0 >= 0) goto L9;
        return r22;
    L9:
        return r22.substring(0, r0);
    }

    public static void setSendTargetLanguage(String r3) {
        if (Common.ASSET_APP.equalsIgnoreCase(r3) == false) goto L5;
        String r32 = Common.ASSET_APP;
    L7:
        if (TextUtils.isEmpty(r32) == false) goto L11;
        ExteraConfig.getEditor().remove("targetLangSend").apply();
        return;
    L11:
        if (TextUtils.equals(r32, Common.ASSET_APP) == true) goto L14;
        storeRecentSendTargetLanguage(r32);
    L14:
        if (TextUtils.equals(r32, "en") == false) goto L17;
        ExteraConfig.getEditor().remove("targetLangSend").apply();
        return;
    L17:
        ExteraConfig.getEditor().putString("targetLangSend", r32).apply();
        return;
    L5:
        r32 = normalizeLanguageCode(r3);
        goto L7
    }

    public static void setTargetLanguage(String r2) {
        String r0 = Common.ASSET_APP;
        if (Common.ASSET_APP.equalsIgnoreCase(r2) == false) goto L5;
        String r22 = Common.ASSET_APP;
    L7:
        if (TextUtils.isEmpty(r22) == true) goto L10;
        r0 = r22;
    L10:
        ExteraConfig.setTargetLang(r0);
        ExteraConfig.getEditor().putString("targetLang", r0).apply();
        int r1 = 0;
        if (MessagesController.getGlobalMainSettings().getBoolean("translate_button_restricted_languages_changed", false) == true) goto L17;
        MessagesController.getGlobalMainSettings().edit().remove("translate_button_restricted_languages").apply();
        RestrictedLanguagesSelectActivity.invalidateRestrictedLanguages();
        RestrictedLanguagesSelectActivity.checkRestrictedLanguages(false);
    L14:
        if (r1 >= 16) goto L23;
        MessagesController.getInstance(r1).getTranslateController().checkRestrictedLanguagesUpdate();     // Catch: Exception -> L18
    L16:
        r1 = r1 + 1;
        goto L14
    L23:
        return;
    L17:
        return;
    L5:
        r22 = normalizeLanguageCode(r2);
        goto L7
    }

    private static void storeRecentSendTargetLanguage(String r2) {
        String r22 = normalizeLanguageCode(r2);
        if (TextUtils.isEmpty(r22) == false) goto L5;
        return;
    L5:
        ArrayList<String> r0 = getRecentSendTargetLanguages();
        r0.removeIf(new TranslatorUtils$$ExternalSyntheticLambda13(r22));
        r0.add(0, r22);
    L7:
        if (r0.size() <= 3) goto L9;
        r0.remove(r0.size() - 1);
        goto L7
    L9:
        ExteraConfig.getEditor().putString("targetLangSendRecent", TextUtils.join(",", r0)).apply();
    }

    public static void translate(CharSequence r1, String r2, String r3, TranslateCallback r4) {
        translate(r1, r2, r3, null, r4);
    }

    public static void translate(CharSequence r8, String r9, String r10, ArrayList<TLRPC.MessageEntity> r11, TranslateCallback r12) {
        BaseTranslator r0 = getCurrentTranslator();
        if (r0 != TelegramTranslator.getInstance()) goto L7;
        translateWithDefault(r8, null, 0, r10, r11, r12);
        return;
    L7:
        if (r0.isLanguageSupported(r10) == true) goto L9;
        r0 = GoogleTranslator.getInstance();
    L9:
        r0.translate(r8.toString(), r9, r10, new AnonymousClass3(r12));
    }

    public static void translate(CharSequence r3, String r4, ArrayList<TLRPC.MessageEntity> r5, TranslateCallback r6) {
        if (TextUtils.isEmpty(r3) == false) goto L5;
        return;
    L5:
        String r42 = getResolvedTargetLanguageCode(r4);
        if (LanguageDetector.hasSupport() == false) goto L9;
        LanguageDetector.detectLanguage(r3.toString(), new TranslatorUtils$$ExternalSyntheticLambda11(r3, r42, r5, r6), new TranslatorUtils$$ExternalSyntheticLambda12(r3, r42, r5, r6));
        return;
    L9:
        translate(r3, "auto", r42, r5, r6);
    }

    public static void translateWithAlert(MessageObject r10, MessageObject.GroupedMessages r11, TLRPC.InputPeer r12, int r13, BaseFragment r14) {
        if (r10 == null) goto L16;
        if (r14 != null) goto L5;
        return;
    L5:
        if (r14.getContext() == null) goto L18;
        ArrayList<TLRPC.MessageEntity> r1 = null;
        if ((r14 instanceof ChatActivity) == false) goto L10;
        ChatActivity r9 = (ChatActivity) r14;
    L11:
        TranslatorUtils$$ExternalSyntheticLambda14 r8 = new TranslatorUtils$$ExternalSyntheticLambda14(r9, r10);
        TLRPC.Message r0 = r10.messageOwner;
        if (r0 == null) goto L14;
        r1 = r0.entities;
    L14:
        detectMessageLanguage(r10, r11, new TranslatorUtils$$ExternalSyntheticLambda15(r14, r12, r13, ChatUtils.getInstance().getMessageText(r10, r11), r1, r8, r9));
        return;
    L10:
        r9 = null;
        goto L11
    L18:
        return;
    }

    public static void translateWithDefault(CharSequence r3, TLRPC.InputPeer r4, int r5, String r6, ArrayList<TLRPC.MessageEntity> r7, TranslateCallback r8) {
        String r62 = getResolvedTargetLanguageCode(r6);
        TLRPC.TL_messages_translateText r0 = new TLRPC.TL_messages_translateText();
        TLRPC.TL_textWithEntities r1 = new TLRPC.TL_textWithEntities();
        if (r3 != null) goto L5;
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r1.text = r2;
        if (r7 == null) goto L9;
        r1.entities = r7;
    L9:
        int r72 = r0.flags;
        if (r4 == null) goto L12;
        r0.flags = r72 | 1;
        r0.peer = r4;
        r0.id.add(Integer.valueOf(r5));
    L13:
        if (r62 == null) goto L15;
        String r42 = r62.trim();
    L17:
        if ("nb".equals(r42) == false) goto L19;
        r42 = "no";
    L19:
        r0.to_lang = r42;
        r8.onReqId(ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r0, new TranslatorUtils$$ExternalSyntheticLambda5(r3, r62, r8, r1)));
        return;
    L15:
        r42 = r62;
        goto L17
    L12:
        r0.flags = r72 | 2;
        r0.text.add(r1);
        goto L13
    L5:
        r2 = r3.toString();
        goto L6
    }
}
