package com.exteragram.messenger.preferences;

import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.translator.TranslationProviders;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.exteragram.messenger.utils.ExtensionsKt;
import com.exteragram.messenger.utils.text.ZalgoFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.TranslateAlert2;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.RestrictedLanguagesSelectActivity;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0012\u0018\u0000 62\u00020\u0001:\u000256B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u001e\u0010\u0013\u001a\u00020\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J0\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0014J\b\u0010(\u001a\u00020\u0010H\u0002J\u0010\u0010,\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0016H\u0002J\b\u0010-\u001a\u00020\u0010H\u0002J\b\u0010.\u001a\u00020\u0010H\u0002J\b\u00104\u001a\u00020\u0010H\u0002R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0004\n\u0002\u0010\u0007R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0014\u0010!\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020%8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0014\u0010)\u001a\u00020%8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010'R\u0014\u0010+\u001a\u00020%8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b+\u0010'R\u0014\u0010/\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0014\u00102\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b3\u00101¨\u00067"}, d2 = {"Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "<init>", "()V", "idOptions", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/CharSequence;", "translationProviders", "fiveMinutesAgo", _UrlKt.FRAGMENT_ENCODE_SET, "getFiveMinutesAgo", "()I", "fiveMinutesAgo$delegate", "Lkotlin/Lazy;", "initializeOptionStrings", _UrlKt.FRAGMENT_ENCODE_SET, "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "fillItems", PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "Landroid/view/View;", "position", "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "doNotTranslateValue", "getDoNotTranslateValue", "()Ljava/lang/CharSequence;", "contextTranslateValue", _UrlKt.FRAGMENT_ENCODE_SET, "getContextTranslateValue", "()Z", "handleContextTranslateClick", "chatTranslateValue", "getChatTranslateValue", "isChatTranslateLocked", "handleChatTranslateClick", "handleFormatTimeWithSecondsClick", "handleHidePhoneNumberClick", "customSavePathDisplayValue", "getCustomSavePathDisplayValue", "()Ljava/lang/String;", "customSavePathInfo", "getCustomSavePathInfo", "showCustomSavePathDialog", "GeneralItem", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGeneralPreferencesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GeneralPreferencesActivity.kt\ncom/exteragram/messenger/preferences/GeneralPreferencesActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,300:1\n1642#2,10:301\n1915#2:311\n1916#2:313\n1652#2:314\n1#3:312\n*S KotlinDebug\n*F\n+ 1 GeneralPreferencesActivity.kt\ncom/exteragram/messenger/preferences/GeneralPreferencesActivity\n*L\n191#1:301,10\n191#1:311\n191#1:313\n191#1:314\n191#1:312\n*E\n"})
/* loaded from: classes4.dex */
public final class GeneralPreferencesActivity extends BasePreferencesActivity {
    public static final Companion Companion = null;
    private static final Pattern SAVE_PATH_PATTERN = null;
    private final Lazy fiveMinutesAgo$delegate;
    private CharSequence[] idOptions;
    private CharSequence[] translationProviders;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SAVE_PATH_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u001b"}, d2 = {"Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity$GeneralItem;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "SHOW_TRANSLATE_BUTTON", "SHOW_TRANSLATE_CHAT_BUTTON", "TRANSLATION_PROVIDERS", "TRANSLATION_TARGET_LANGUAGE", "DO_NOT_TRANSLATE_LANGUAGES", "DISABLE_NUMBER_ROUNDING", "FORMAT_TIME_WITH_SECONDS", "RELATIVE_LAST_SEEN", "IN_APP_VIBRATION", "FILTER_ZALGO", "DOWNLOAD_SPEED_BOOST", "UPLOAD_SPEED_BOOST", "CUSTOM_SAVE_PATH", "HIDE_PHONE_NUMBER", "SHOW_ID_AND_DC", "HIDE_ARCHIVE_FOLDER", "ARCHIVE_ON_PULL", "DISABLE_UNARCHIVE_SWIPE", "DISABLE_NOTIFICATION_DELAY", "id", _UrlKt.FRAGMENT_ENCODE_SET, "getId", "()I", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum GeneralItem extends Enum<GeneralItem> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ GeneralItem[] $VALUES = null;
        public static final GeneralItem ARCHIVE_ON_PULL = null;
        public static final GeneralItem CUSTOM_SAVE_PATH = null;
        public static final GeneralItem DISABLE_NOTIFICATION_DELAY = null;
        public static final GeneralItem DISABLE_NUMBER_ROUNDING = null;
        public static final GeneralItem DISABLE_UNARCHIVE_SWIPE = null;
        public static final GeneralItem DOWNLOAD_SPEED_BOOST = null;
        public static final GeneralItem DO_NOT_TRANSLATE_LANGUAGES = null;
        public static final GeneralItem FILTER_ZALGO = null;
        public static final GeneralItem FORMAT_TIME_WITH_SECONDS = null;
        public static final GeneralItem HIDE_ARCHIVE_FOLDER = null;
        public static final GeneralItem HIDE_PHONE_NUMBER = null;
        public static final GeneralItem IN_APP_VIBRATION = null;
        public static final GeneralItem RELATIVE_LAST_SEEN = null;
        public static final GeneralItem SHOW_ID_AND_DC = null;
        public static final GeneralItem SHOW_TRANSLATE_BUTTON = null;
        public static final GeneralItem SHOW_TRANSLATE_CHAT_BUTTON = null;
        public static final GeneralItem TRANSLATION_PROVIDERS = null;
        public static final GeneralItem TRANSLATION_TARGET_LANGUAGE = null;
        public static final GeneralItem UPLOAD_SPEED_BOOST = null;
        private final int id;

        private static final /* synthetic */ GeneralItem[] $values() {
            return new GeneralItem[]{SHOW_TRANSLATE_BUTTON, SHOW_TRANSLATE_CHAT_BUTTON, TRANSLATION_PROVIDERS, TRANSLATION_TARGET_LANGUAGE, DO_NOT_TRANSLATE_LANGUAGES, DISABLE_NUMBER_ROUNDING, FORMAT_TIME_WITH_SECONDS, RELATIVE_LAST_SEEN, IN_APP_VIBRATION, FILTER_ZALGO, DOWNLOAD_SPEED_BOOST, UPLOAD_SPEED_BOOST, CUSTOM_SAVE_PATH, HIDE_PHONE_NUMBER, SHOW_ID_AND_DC, HIDE_ARCHIVE_FOLDER, ARCHIVE_ON_PULL, DISABLE_UNARCHIVE_SWIPE, DISABLE_NOTIFICATION_DELAY};
        }

        static {
            SHOW_TRANSLATE_BUTTON = new GeneralItem("SHOW_TRANSLATE_BUTTON", 0);
            SHOW_TRANSLATE_CHAT_BUTTON = new GeneralItem("SHOW_TRANSLATE_CHAT_BUTTON", 1);
            TRANSLATION_PROVIDERS = new GeneralItem("TRANSLATION_PROVIDERS", 2);
            TRANSLATION_TARGET_LANGUAGE = new GeneralItem("TRANSLATION_TARGET_LANGUAGE", 3);
            DO_NOT_TRANSLATE_LANGUAGES = new GeneralItem("DO_NOT_TRANSLATE_LANGUAGES", 4);
            DISABLE_NUMBER_ROUNDING = new GeneralItem("DISABLE_NUMBER_ROUNDING", 5);
            FORMAT_TIME_WITH_SECONDS = new GeneralItem("FORMAT_TIME_WITH_SECONDS", 6);
            RELATIVE_LAST_SEEN = new GeneralItem("RELATIVE_LAST_SEEN", 7);
            IN_APP_VIBRATION = new GeneralItem("IN_APP_VIBRATION", 8);
            FILTER_ZALGO = new GeneralItem("FILTER_ZALGO", 9);
            DOWNLOAD_SPEED_BOOST = new GeneralItem("DOWNLOAD_SPEED_BOOST", 10);
            UPLOAD_SPEED_BOOST = new GeneralItem("UPLOAD_SPEED_BOOST", 11);
            CUSTOM_SAVE_PATH = new GeneralItem("CUSTOM_SAVE_PATH", 12);
            HIDE_PHONE_NUMBER = new GeneralItem("HIDE_PHONE_NUMBER", 13);
            SHOW_ID_AND_DC = new GeneralItem("SHOW_ID_AND_DC", 14);
            HIDE_ARCHIVE_FOLDER = new GeneralItem("HIDE_ARCHIVE_FOLDER", 15);
            ARCHIVE_ON_PULL = new GeneralItem("ARCHIVE_ON_PULL", 16);
            DISABLE_UNARCHIVE_SWIPE = new GeneralItem("DISABLE_UNARCHIVE_SWIPE", 17);
            DISABLE_NOTIFICATION_DELAY = new GeneralItem("DISABLE_NOTIFICATION_DELAY", 18);
            GeneralItem[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        GeneralItem(String r1, int r2) {
            this.id = ordinal() + 1;
        }

        public static EnumEntries<GeneralItem> getEntries() {
            return $ENTRIES;
        }

        public static GeneralItem valueOf(String r1) {
            return (GeneralItem) Enum.valueOf(GeneralItem.class, r1);
        }

        public static GeneralItem[] values() {
            return (GeneralItem[]) $VALUES.clone();
        }

        public final int getId() {
            return this.id;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[GeneralItem.values().length];
            r0[GeneralItem.SHOW_TRANSLATE_BUTTON.ordinal()] = 1;     // Catch: NoSuchFieldError -> L23
        L63:
            r0[GeneralItem.SHOW_TRANSLATE_CHAT_BUTTON.ordinal()] = 2;     // Catch: NoSuchFieldError -> L24
        L75:
            r0[GeneralItem.TRANSLATION_PROVIDERS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L25
        L47:
            r0[GeneralItem.TRANSLATION_TARGET_LANGUAGE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L26
        L51:
            r0[GeneralItem.DO_NOT_TRANSLATE_LANGUAGES.ordinal()] = 5;     // Catch: NoSuchFieldError -> L27
        L59:
            r0[GeneralItem.DISABLE_NUMBER_ROUNDING.ordinal()] = 6;     // Catch: NoSuchFieldError -> L28
        L69:
            r0[GeneralItem.FORMAT_TIME_WITH_SECONDS.ordinal()] = 7;     // Catch: NoSuchFieldError -> L29
        L55:
            r0[GeneralItem.RELATIVE_LAST_SEEN.ordinal()] = 8;     // Catch: NoSuchFieldError -> L30
        L65:
            r0[GeneralItem.IN_APP_VIBRATION.ordinal()] = 9;     // Catch: NoSuchFieldError -> L31
        L67:
            r0[GeneralItem.DISABLE_NOTIFICATION_DELAY.ordinal()] = 10;     // Catch: NoSuchFieldError -> L32
        L43:
            r0[GeneralItem.FILTER_ZALGO.ordinal()] = 11;     // Catch: NoSuchFieldError -> L33
        L61:
            r0[GeneralItem.UPLOAD_SPEED_BOOST.ordinal()] = 12;     // Catch: NoSuchFieldError -> L34
        L73:
            r0[GeneralItem.CUSTOM_SAVE_PATH.ordinal()] = 13;     // Catch: NoSuchFieldError -> L35
        L41:
            r0[GeneralItem.HIDE_PHONE_NUMBER.ordinal()] = 14;     // Catch: NoSuchFieldError -> L36
        L53:
            r0[GeneralItem.SHOW_ID_AND_DC.ordinal()] = 15;     // Catch: NoSuchFieldError -> L37
        L71:
            r0[GeneralItem.HIDE_ARCHIVE_FOLDER.ordinal()] = 16;     // Catch: NoSuchFieldError -> L38
        L45:
            r0[GeneralItem.ARCHIVE_ON_PULL.ordinal()] = 17;     // Catch: NoSuchFieldError -> L39
        L49:
            r0[GeneralItem.DISABLE_UNARCHIVE_SWIPE.ordinal()] = 18;     // Catch: NoSuchFieldError -> L40
        L21:
            $EnumSwitchMapping$0 = r0;
        }
    }

    /* renamed from: $r8$lambda$-1zVscb9gTLjNdEc6JbOjLBvQAk, reason: not valid java name */
    public static void m1639$r8$lambda$1zVscb9gTLjNdEc6JbOjLBvQAk(boolean r0) {
        ExteraConfig.setFormatTimeWithSeconds(r0);
    }

    /* renamed from: $r8$lambda$-6792H4QVEdinaIDuZk7bIWlb0s, reason: not valid java name */
    public static void m1640$r8$lambda$6792H4QVEdinaIDuZk7bIWlb0s(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    /* renamed from: $r8$lambda$5GYjEBDTw6R69LJOxk-OvhZShiQ, reason: not valid java name */
    public static void m1641$r8$lambda$5GYjEBDTw6R69LJOxkOvhZShiQ(boolean r0) {
        ExteraConfig.setArchiveOnPull(r0);
    }

    /* renamed from: $r8$lambda$65QAtQ0CrA2nUJeI6PU4vcpP-xA, reason: not valid java name */
    public static void m1642$r8$lambda$65QAtQ0CrA2nUJeI6PU4vcpPxA(boolean r0) {
        ExteraConfig.setHideArchiveFolder(r0);
    }

    /* renamed from: $r8$lambda$6zgVf5fhCgfMnlN9P1_-x3spU2E, reason: not valid java name */
    public static void m1643$r8$lambda$6zgVf5fhCgfMnlN9P1_x3spU2E(EditTextBoldCursor r0, DialogInterface r1) {
        AndroidUtilities.hideKeyboard(r0);
    }

    public static void $r8$lambda$CLrsnmEDdSD_bnhQjPlKm3Ogncs(GeneralPreferencesActivity r3) {
        r3.showDialog(new PremiumFeatureBottomSheet(r3, 13, false));
    }

    /* renamed from: $r8$lambda$Dm6JSeTzSHL1BBskGBexT-XhGzI, reason: not valid java name */
    public static void m1644$r8$lambda$Dm6JSeTzSHL1BBskGBexTXhGzI(boolean r0) {
        ExteraConfig.setDisableNotificationDelay(r0);
    }

    public static void $r8$lambda$J831koXHuOVSMRGTuNEtCDswmvo(boolean r0) {
        ExteraConfig.setDisableNumberRounding(r0);
    }

    public static void $r8$lambda$K9eupNPj_rS_RS7_N0xpbJJpzkM(GeneralPreferencesActivity r0, boolean r1) {
        r0.getMessagesController().getTranslateController().setContextTranslateEnabled(r1);
    }

    public static void $r8$lambda$KrWwAmRC7Guvl72AisMkEr5VwJU(GeneralPreferencesActivity r0, int r1) {
        ExteraConfig.setShowIdAndDc(r1);
        r0.parentLayout.rebuildFragments(0);
    }

    public static void $r8$lambda$L_UdzkNIPcT9sRPhsIbeSp_0nU4(boolean r0) {
        ExteraConfig.setInAppVibration(r0);
    }

    public static void $r8$lambda$TGgWqWciHfVoWWoSPJGiGkq3kAc(EditTextBoldCursor r1, GeneralPreferencesActivity r2, AlertDialog r3, int r4) {
        Editable r42 = r1.getText();
        if (r42 == null) goto L8;
        String r43 = r42.toString();
        if (r43 == null) goto L8;
        String r44 = StringsKt.trim(r43).toString();
        if (r44 == null) goto L8;
    L10:
        if (r44.length() > 0) goto L12;
    L15:
        ExteraConfig.setCustomSavePath(r44);
        r2.getListView$TMessagesProj().adapter.update(true);
        r3.dismiss();
        return;
    L12:
        if (SAVE_PATH_PATTERN.matcher(r44).matches() == true) goto L15;
        AndroidUtilities.shakeView(r1);
        return;
    L8:
        r44 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L10
    }

    /* renamed from: $r8$lambda$U3axzru9WxR8DMYs4-Fwp9CNRKA, reason: not valid java name */
    public static int m1645$r8$lambda$U3axzru9WxR8DMYs4Fwp9CNRKA(GeneralPreferencesActivity r0) {
        return ConnectionsManager.getInstance(r0.currentAccount).getCurrentTime() - 300;
    }

    public static void $r8$lambda$VZeNIa2dT8V95SSPsFSkahviBm8(boolean r0) {
        ExteraConfig.setHidePhoneNumber(r0);
    }

    public static void $r8$lambda$aJNHyEfWVAyoGhMbpbKilQ468y8(boolean r0) {
        ExteraConfig.setRelativeLastSeen(r0);
    }

    public static void $r8$lambda$eGlFftM4yupT7qEkp7fuBtEHT5w(GeneralPreferencesActivity r0, boolean r1) {
        r0.getMessagesController().getTranslateController().setChatTranslateEnabled(r1);
    }

    public static void $r8$lambda$ey9elelLTBGwmTmpDtFlhV0SDII(boolean r0) {
        ExteraConfig.setUploadSpeedBoost(r0);
    }

    /* renamed from: $r8$lambda$fkxN65LZaTfMEbFYf6y-GJljFos, reason: not valid java name */
    public static void m1646$r8$lambda$fkxN65LZaTfMEbFYf6yGJljFos(EditTextBoldCursor r0, DialogInterface r1) {
        r0.requestFocus();
        r0.setSelection(r0.length());
        AndroidUtilities.showKeyboard(r0);
    }

    /* renamed from: $r8$lambda$moiwS_OdMKGCLwy70XJIPkqI-cc, reason: not valid java name */
    public static void m1647$r8$lambda$moiwS_OdMKGCLwy70XJIPkqIcc(boolean r0) {
        ExteraConfig.setDisableUnarchiveSwipe(r0);
    }

    public static void $r8$lambda$pPeUA80MUZui_qVriAzotjwYxPQ(boolean r0) {
        ExteraConfig.setFilterZalgo(r0);
    }

    /* renamed from: $r8$lambda$pnIgrca-oicP1fGeYl5KQLx5eEI, reason: not valid java name */
    public static void m1648$r8$lambda$pnIgrcaoicP1fGeYl5KQLx5eEI(GeneralPreferencesActivity r0, int r1) {
        ExteraConfig.setTranslationProvider(r1);
        TranslatorUtils.ensureTargetLanguageCompatibleWithProvider();
        r0.parentLayout.rebuildFragments(0);
    }

    public static void $r8$lambda$we9EboXXYvclBnRhBglfMmxCWbw(Integer r0) {
        ExteraConfig.setDownloadSpeedBoost(r0.intValue());
    }

    static {
        Companion = new Companion(null);
        SAVE_PATH_PATTERN = Pattern.compile("^(?!\\.{1,2}$)[A-Za-z0-9._ -]{1,255}$");
    }

    public GeneralPreferencesActivity() {
        this.fiveMinutesAgo$delegate = LazyKt.lazy(new GeneralPreferencesActivity$$ExternalSyntheticLambda0(this));
    }

    private final boolean getChatTranslateValue() {
        return getMessagesController().getTranslateController().isChatTranslateEnabled();
    }

    private final boolean getContextTranslateValue() {
        return getMessagesController().getTranslateController().isContextTranslateEnabled();
    }

    private final String getCustomSavePathDisplayValue() {
        if (TextUtils.isEmpty(ExteraConfig.getCustomSavePath()) == false) goto L7;
        return LocaleController.getString(R.string.CustomSavePathDefault);
    L7:
        return ExteraConfig.getCustomSavePath();
    }

    private final String getCustomSavePathInfo() {
        if (TextUtils.isEmpty(ExteraConfig.getCustomSavePath()) == false) goto L7;
        return LocaleController.getString(R.string.CustomSavePathInfo);
    L7:
        return LocaleController.formatString(R.string.CustomSavePathInfoFolder, new Object[]{ExteraConfig.getCustomSavePath()});
    }

    private final CharSequence getDoNotTranslateValue() {
        HashSet<String> r10 = RestrictedLanguagesSelectActivity.getRestrictedLanguages();
        boolean[] r0 = new boolean[1];
        ArrayList r1 = new ArrayList();
        Iterator<T> r102 = r10.iterator();
    L4:
        if (r102.hasNext() == false) goto L9;
        String r2 = TranslateAlert2.capitalFirst(TranslateAlert2.languageName((String) r102.next(), r0));
        if (r2 == null) goto L4;
        r1.add(r2);
        goto L4
    L9:
        return CollectionsKt.joinToString$default(r1, ", ", null, null, 0, null, null, 62, null);
    }

    private final int getFiveMinutesAgo() {
        return ((Number) this.fiveMinutesAgo$delegate.getValue()).intValue();
    }

    private final void handleChatTranslateClick(UItem r5) {
        if (r5.checked == false) goto L5;
    L8:
        toggleBooleanSettingAndRefresh$TMessagesProj(r5, new GeneralPreferencesActivity$$ExternalSyntheticLambda21(this));
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateSearchSettings, new Object[0]);
        this.parentLayout.rebuildFragments(0);
        return;
    L5:
        if (isChatTranslateLocked() == false) goto L8;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.msg_translate, LocaleController.getString(R.string.ShowTranslateChatButtonLocked), LocaleController.getString(R.string.MoreInfo), new GeneralPreferencesActivity$$ExternalSyntheticLambda20(this)).show();
    }

    private final void handleContextTranslateClick() {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateSearchSettings, new Object[0]);
        this.parentLayout.rebuildFragments(0);
    }

    private final void handleFormatTimeWithSecondsClick() {
        LocaleController.getInstance().recreateFormatters();
        this.parentLayout.rebuildFragments(0);
    }

    private final void handleHidePhoneNumberClick() {
        getNotificationCenter().postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
        this.parentLayout.rebuildFragments(0);
    }

    private final boolean isChatTranslateLocked() {
        if (getUserConfig().isPremium() == false) goto L5;
        return false;
    L5:
        if (TranslatorUtils.isAlternativeProvider() == true) goto L10;
        return true;
    L10:
        return false;
    }

    private final void showCustomSavePathDialog() {
        if (getContext() != null) goto L5;
        return;
    L5:
        EditTextBoldCursor r0 = new EditTextBoldCursor(getContext());
        r0.lineYFix = true;
        r0.setTextSize(1, 18.0f);
        r0.setText(ExteraConfig.getCustomSavePath());
        r0.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourceProvider));
        r0.setHintColor(Theme.getColor(Theme.key_groupcreate_hintText, this.resourceProvider));
        r0.setHintText(LocaleController.getString(R.string.CustomSavePathHint));
        r0.setFocusable(true);
        r0.setSingleLine(true);
        r0.setInputType(1);
        r0.setBackground(null);
        int r2 = Theme.getColor(Theme.key_windowBackgroundWhiteInputField, this.resourceProvider);
        int r3 = Theme.key_windowBackgroundWhiteInputFieldActivated;
        r0.setLineColors(r2, Theme.getColor(r3, this.resourceProvider), Theme.getColor(Theme.key_text_RedRegular, this.resourceProvider));
        r0.setCursorColor(Theme.getColor(r3, this.resourceProvider));
        r0.setPadding(0, AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f));
        LinearLayout r22 = new LinearLayout(getContext());
        r22.setOrientation(1);
        r22.addView(r0, LayoutHelper.createLinear(-1, -2, 24.0f, 0.0f, 24.0f, 10.0f));
        AlertDialog.Builder r1 = new AlertDialog.Builder(getContext(), this.resourceProvider);
        r1.setTitle(LocaleController.getString(R.string.CustomSavePath));
        r1.makeCustomMaxHeight();
        r1.setView(r22);
        r1.setWidth(AndroidUtilities.dp(292.0f));
        r1.setPositiveButton(LocaleController.getString(R.string.Done), new GeneralPreferencesActivity$$ExternalSyntheticLambda16(r0, this));
        r1.setNegativeButton(LocaleController.getString(R.string.Cancel), new GeneralPreferencesActivity$$ExternalSyntheticLambda17());
        AlertDialog r12 = r1.create();
        r12.setOnDismissListener(new GeneralPreferencesActivity$$ExternalSyntheticLambda18(r0));
        r12.setOnShowListener(new GeneralPreferencesActivity$$ExternalSyntheticLambda19(r0));
        r12.setDismissDialogByButtons(false);
        showDialog(r12);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r17, UniversalAdapter r18) {
        r17.add(UItem.asHeader(LocaleController.getString(R.string.TranslateMessages)));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, GeneralItem.SHOW_TRANSLATE_BUTTON, LocaleController.getString(R.string.ShowTranslateButton), getContextTranslateValue(), null, 0, false, "showTranslateButton", false, 0, 440, null));
        GeneralItem r1 = GeneralItem.SHOW_TRANSLATE_CHAT_BUTTON;
        String r2 = LocaleController.getString(R.string.ShowTranslateChatButton);
        boolean r3 = getChatTranslateValue();
        if (isChatTranslateLocked() == false) goto L6;
        int r0 = R.drawable.permission_locked;
    L7:
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, r1, r2, r3, null, r0, false, "showTranslateChatButton", false, 0, 424, null));
        GeneralItem r12 = GeneralItem.TRANSLATION_PROVIDERS;
        String r22 = LocaleController.getString(R.string.TranslationProvider);
        CharSequence[] r32 = this.translationProviders;
        CharSequence[] r15 = null;
        if (r32 != null) goto L10;
        r32 = null;
    L10:
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r12, r22, 0, r32[ExteraConfig.getTranslationProvider()], null, null, false, "translationProvider", false, false, null, 1908, null));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, GeneralItem.TRANSLATION_TARGET_LANGUAGE, LocaleController.getString(R.string.TranslationTarget), 0, ExteraConfig.getCurrentLangName(), null, null, false, "translationTargetLanguage", false, false, null, 1908, null));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, GeneralItem.DO_NOT_TRANSLATE_LANGUAGES, LocaleController.getString(R.string.DoNotTranslate), 0, getDoNotTranslateValue(), null, null, false, "doNotTranslateLanguages", false, false, null, 1908, null));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.TranslateMessagesInfo1)));
        r17.add(UItem.asHeader(LocaleController.getString(R.string.General)));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, GeneralItem.DISABLE_NUMBER_ROUNDING, LocaleController.getString(R.string.DisableNumberRounding), ExteraConfig.getDisableNumberRounding(), "1.23K -> 1,234", 0, false, "disableNumberRounding", false, 0, 304, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, GeneralItem.FORMAT_TIME_WITH_SECONDS, LocaleController.getString(R.string.FormatTimeWithSeconds), ExteraConfig.getFormatTimeWithSeconds(), "12:34 -> 12:34:56", 0, false, "formatTimeWithSeconds", false, 0, 304, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, GeneralItem.IN_APP_VIBRATION, LocaleController.getString(R.string.InAppVibration), ExteraConfig.getInAppVibration(), null, 0, false, "inAppVibration", false, 0, 440, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, GeneralItem.DISABLE_NOTIFICATION_DELAY, LocaleController.getString(R.string.DisableNotificationDelay), ExteraConfig.getDisableNotificationDelay(), null, 0, false, "disableNotificationDelay", false, 0, 440, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, GeneralItem.FILTER_ZALGO, LocaleController.getString(R.string.FilterZalgo), ExteraConfig.getFilterZalgo(), null, 0, false, "filterZalgo", false, 0, 440, null));
        r17.add(UItem.asShadow(LocaleController.formatString(R.string.FilterZalgoInfo, new Object[]{ZalgoFilter.filter("Z̷͍͌ā̸̜l̸̞̂g̷͍̝o̶̩̓")})));
        r17.add(UItem.asHeader(LocaleController.getString(R.string.DownloadSpeedBoost)));
        r17.add(UItem.asSlideView(GeneralItem.DOWNLOAD_SPEED_BOOST.getId(), new String[]{LocaleController.getString(R.string.BlurOff), LocaleController.getString(R.string.SpeedFast), LocaleController.getString(R.string.Ultra)}, ExteraConfig.getDownloadSpeedBoost(), new GeneralPreferencesActivity$$ExternalSyntheticLambda1()).setLinkAlias("downloadSpeedBoost", this));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, GeneralItem.UPLOAD_SPEED_BOOST, LocaleController.getString(R.string.UploadSpeedBoost), ExteraConfig.getUploadSpeedBoost(), null, 0, false, "uploadSpeedBoost", false, 0, 440, null));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.SpeedBoostInfo)));
        r17.add(UItem.asHeader(LocaleController.getString(R.string.StorageSettings)));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, GeneralItem.CUSTOM_SAVE_PATH, LocaleController.getString(R.string.CustomSavePath), 0, getCustomSavePathDisplayValue(), null, null, false, "customSavePath", false, false, null, 1908, null));
        r17.add(UItem.asShadow(getCustomSavePathInfo()));
        r17.add(UItem.asHeader(LocaleController.getString(R.string.Profile)));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, GeneralItem.RELATIVE_LAST_SEEN, LocaleController.getString(R.string.RelativeLastSeen), ExteraConfig.getRelativeLastSeen(), LocaleController.formatDateOnline(getFiveMinutesAgo(), null, new boolean[1]), 0, false, "relativeLastSeen", false, 0, 304, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, GeneralItem.HIDE_PHONE_NUMBER, LocaleController.getString(R.string.HidePhoneNumber), ExteraConfig.getHidePhoneNumber(), null, 0, false, "hidePhoneNumber", false, 0, 440, null));
        GeneralItem r13 = GeneralItem.SHOW_ID_AND_DC;
        String r23 = LocaleController.getString(R.string.ShowIdAndDc);
        CharSequence[] r33 = this.idOptions;
        if (r33 == null) goto L14;
        r15 = r33;
    L14:
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r13, r23, 0, r15[ExteraConfig.getShowIdAndDc()], null, null, false, "showIdAndDc", false, false, null, 1908, null));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.ShowIdAndDcInfo)));
        r17.add(UItem.asHeader(LocaleController.getString(R.string.ArchivedChats)));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, GeneralItem.HIDE_ARCHIVE_FOLDER, LocaleController.getString(R.string.HideArchiveFolder), ExteraConfig.getHideArchiveFolder(), null, 0, false, "hideArchiveFolder", false, 0, 440, null));
        ExtensionsKt.addIf(r17, !ExteraConfig.getHideArchiveFolder(), BasePreferencesActivity.uCheck$TMessagesProj$default(this, GeneralItem.ARCHIVE_ON_PULL, LocaleController.getString(R.string.ArchiveOnPull), ExteraConfig.getArchiveOnPull(), null, 0, false, "archiveOnPull", false, 0, 440, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, GeneralItem.DISABLE_UNARCHIVE_SWIPE, LocaleController.getString(R.string.DisableUnarchiveSwipe), ExteraConfig.getDisableUnarchiveSwipe(), null, 0, false, "disableUnarchiveSwipe", false, 0, 440, null));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.DisableUnarchiveSwipeInfo)));
        return;
    L6:
        r0 = 0;
        goto L7
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.General);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void initializeOptionStrings() {
        this.idOptions = new CharSequence[]{LocaleController.getString(R.string.Hide), "Telegram API", "Bot API"};
        this.translationProviders = TranslationProviders.names();
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r7, View r8, int r9, float r10, float r11) {
        int r82 = r7.id;
        if (r82 > 0) goto L5;
        return;
    L5:
        if (r82 <= GeneralItem.getEntries().size()) goto L8;
        return;
    L8:
        switch(WhenMappings.$EnumSwitchMapping$0[GeneralItem.getEntries().get(r7.id - 1).ordinal()]) {
            case 1: goto L51;
            case 2: goto L49;
            case 3: goto L44;
            case 4: goto L42;
            case 5: goto L40;
            case 6: goto L38;
            case 7: goto L36;
            case 8: goto L34;
            case 9: goto L32;
            case 10: goto L30;
            case 11: goto L28;
            case 12: goto L26;
            case 13: goto L24;
            case 14: goto L22;
            case 15: goto L16;
            case 16: goto L14;
            case 17: goto L12;
            case 18: goto L10;
            default: goto L55;
        };
    L10:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new GeneralPreferencesActivity$$ExternalSyntheticLambda6());
        return;
    L12:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new GeneralPreferencesActivity$$ExternalSyntheticLambda5());
        return;
    L14:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new GeneralPreferencesActivity$$ExternalSyntheticLambda4());
        MessagesController.getInstance(this.currentAccount).checkArchiveFolder();
        return;
    L16:
        CharSequence[] r83 = this.idOptions;
        if (r83 != null) goto L19;
        CharSequence[] r2 = null;
    L20:
        showListDialog(r7, r2, LocaleController.getString(R.string.ShowIdAndDc), ExteraConfig.getShowIdAndDc(), new GeneralPreferencesActivity$$ExternalSyntheticLambda3(this));
        return;
    L19:
        r2 = r83;
        goto L20
    L22:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new GeneralPreferencesActivity$$ExternalSyntheticLambda15());
        handleHidePhoneNumberClick();
        return;
    L24:
        showCustomSavePathDialog();
        return;
    L26:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new GeneralPreferencesActivity$$ExternalSyntheticLambda14());
        return;
    L28:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new GeneralPreferencesActivity$$ExternalSyntheticLambda13());
        return;
    L30:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new GeneralPreferencesActivity$$ExternalSyntheticLambda12());
        return;
    L32:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new GeneralPreferencesActivity$$ExternalSyntheticLambda11());
        return;
    L34:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new GeneralPreferencesActivity$$ExternalSyntheticLambda10());
        return;
    L36:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new GeneralPreferencesActivity$$ExternalSyntheticLambda9());
        handleFormatTimeWithSecondsClick();
        return;
    L38:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new GeneralPreferencesActivity$$ExternalSyntheticLambda8());
        return;
    L40:
        presentFragment(new RestrictedLanguagesSelectActivity());
        return;
    L42:
        presentFragment(new RestrictedLanguagesSelectActivity(1));
        return;
    L44:
        CharSequence[] r84 = this.translationProviders;
        if (r84 != null) goto L47;
        r84 = null;
    L47:
        showListDialog(r7, r84, LocaleController.getString(R.string.TranslationProvider), ExteraConfig.getTranslationProvider(), new GeneralPreferencesActivity$$ExternalSyntheticLambda7(this));
        return;
    L49:
        handleChatTranslateClick(r7);
        return;
    L51:
        toggleBooleanSettingAndRefresh$TMessagesProj(r7, new GeneralPreferencesActivity$$ExternalSyntheticLambda2(this));
        handleContextTranslateClick();
        return;
    }
}
