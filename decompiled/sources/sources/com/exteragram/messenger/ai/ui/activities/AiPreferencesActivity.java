package com.exteragram.messenger.ai.ui.activities;

import android.text.TextUtils;
import android.view.View;
import com.exteragram.messenger.ai.AiConfig;
import com.exteragram.messenger.ai.AiController;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.exteragram.messenger.preferences.utils.SettingsRegistry;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u001eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\u001e\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0014J0\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\fH\u0002J\u0010\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0014H\u0002J\b\u0010\u001d\u001a\u00020\u0005H\u0002¨\u0006\u001f"}, d2 = {"Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "<init>", "()V", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "needHideTitle", _UrlKt.FRAGMENT_ENCODE_SET, "fillItems", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "Landroid/view/View;", "position", _UrlKt.FRAGMENT_ENCODE_SET, "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "getHistoryValue", _UrlKt.FRAGMENT_ENCODE_SET, "createTemperatureSliderItem", "formatTemperature", "value", "getEndpointValue", "PreferenceItem", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AiPreferencesActivity extends BasePreferencesActivity {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u0011"}, d2 = {"Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "ENDPOINT", "ROLE", "HISTORY", "TEMPERATURE", "RESPONSE_STREAMING", "SHOW_RESPONSE_ONLY", "INSERT_AS_QUOTE", "REPLACE_TELEGRAM_EDITOR", "REPLACE_TELEGRAM_SUMMARIES", "id", _UrlKt.FRAGMENT_ENCODE_SET, "getId", "()I", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum PreferenceItem extends Enum<PreferenceItem> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ PreferenceItem[] $VALUES = null;
        public static final PreferenceItem ENDPOINT = null;
        public static final PreferenceItem HISTORY = null;
        public static final PreferenceItem INSERT_AS_QUOTE = null;
        public static final PreferenceItem REPLACE_TELEGRAM_EDITOR = null;
        public static final PreferenceItem REPLACE_TELEGRAM_SUMMARIES = null;
        public static final PreferenceItem RESPONSE_STREAMING = null;
        public static final PreferenceItem ROLE = null;
        public static final PreferenceItem SHOW_RESPONSE_ONLY = null;
        public static final PreferenceItem TEMPERATURE = null;
        private final int id;

        private static final /* synthetic */ PreferenceItem[] $values() {
            return new PreferenceItem[]{ENDPOINT, ROLE, HISTORY, TEMPERATURE, RESPONSE_STREAMING, SHOW_RESPONSE_ONLY, INSERT_AS_QUOTE, REPLACE_TELEGRAM_EDITOR, REPLACE_TELEGRAM_SUMMARIES};
        }

        static {
            ENDPOINT = new PreferenceItem("ENDPOINT", 0);
            ROLE = new PreferenceItem("ROLE", 1);
            HISTORY = new PreferenceItem("HISTORY", 2);
            TEMPERATURE = new PreferenceItem("TEMPERATURE", 3);
            RESPONSE_STREAMING = new PreferenceItem("RESPONSE_STREAMING", 4);
            SHOW_RESPONSE_ONLY = new PreferenceItem("SHOW_RESPONSE_ONLY", 5);
            INSERT_AS_QUOTE = new PreferenceItem("INSERT_AS_QUOTE", 6);
            REPLACE_TELEGRAM_EDITOR = new PreferenceItem("REPLACE_TELEGRAM_EDITOR", 7);
            REPLACE_TELEGRAM_SUMMARIES = new PreferenceItem("REPLACE_TELEGRAM_SUMMARIES", 8);
            PreferenceItem[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        PreferenceItem(String r1, int r2) {
            this.id = ordinal() + 1;
        }

        public static EnumEntries<PreferenceItem> getEntries() {
            return $ENTRIES;
        }

        public static PreferenceItem valueOf(String r1) {
            return (PreferenceItem) Enum.valueOf(PreferenceItem.class, r1);
        }

        public static PreferenceItem[] values() {
            return (PreferenceItem[]) $VALUES.clone();
        }

        public final int getId() {
            return this.id;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[PreferenceItem.values().length];
            r0[PreferenceItem.ENDPOINT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L13
        L23:
            r0[PreferenceItem.ROLE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L14
        L35:
            r0[PreferenceItem.HISTORY.ordinal()] = 3;     // Catch: NoSuchFieldError -> L15
        L25:
            r0[PreferenceItem.RESPONSE_STREAMING.ordinal()] = 4;     // Catch: NoSuchFieldError -> L16
        L27:
            r0[PreferenceItem.SHOW_RESPONSE_ONLY.ordinal()] = 5;     // Catch: NoSuchFieldError -> L17
        L21:
            r0[PreferenceItem.INSERT_AS_QUOTE.ordinal()] = 6;     // Catch: NoSuchFieldError -> L18
        L29:
            r0[PreferenceItem.REPLACE_TELEGRAM_EDITOR.ordinal()] = 7;     // Catch: NoSuchFieldError -> L19
        L31:
            r0[PreferenceItem.REPLACE_TELEGRAM_SUMMARIES.ordinal()] = 8;     // Catch: NoSuchFieldError -> L20
        L11:
            $EnumSwitchMapping$0 = r0;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.ai.ui.activities.AiPreferencesActivity$createTemperatureSliderItem$3, reason: invalid class name */
    public static final /* synthetic */ class AnonymousClass3 extends MutablePropertyReference0Impl {
        public static final AnonymousClass3 INSTANCE = null;

        static {
            INSTANCE = new AnonymousClass3();
        }

        public AnonymousClass3() {
            super(AiConfig.class, "temperature", "getTemperature()I", 1);
        }

        @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
        public Object get() {
            return Integer.valueOf(AiConfig.getTemperature());
        }

        @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
        public void set(Object r1) {
            AiConfig.setTemperature(((Number) r1).intValue());
        }
    }

    /* renamed from: $r8$lambda$6tbZ2h-zPWYoXcUbJaDq4t3v0s0, reason: not valid java name */
    public static void m1082$r8$lambda$6tbZ2hzPWYoXcUbJaDq4t3v0s0(boolean r0) {
        AiConfig.setInsertAsQuote(r0);
    }

    /* renamed from: $r8$lambda$MEeAGcO-LHjFdBVYKqgO4RBWzjU, reason: not valid java name */
    public static void m1083$r8$lambda$MEeAGcOLHjFdBVYKqgO4RBWzjU(boolean r0) {
        AiConfig.setResponseStreaming(r0);
    }

    /* renamed from: $r8$lambda$U1W9kDS-YFv1-OHo29RqURfUPCQ, reason: not valid java name */
    public static void m1084$r8$lambda$U1W9kDSYFv1OHo29RqURfUPCQ(boolean r0) {
        AiConfig.setReplaceTelegramSummaries(r0);
    }

    /* renamed from: $r8$lambda$bqyY2-5q31Vei_eltdFFjHL042A, reason: not valid java name */
    public static /* synthetic */ CharSequence m1085$r8$lambda$bqyY25q31Vei_eltdFFjHL042A(AiPreferencesActivity r0, int r1) {
        return r0.formatTemperature(r1);
    }

    public static void $r8$lambda$lTV6ilwpipr7r3e4uylWI7OO4d4(boolean r0) {
        AiConfig.setReplaceTelegramEditor(r0);
    }

    /* renamed from: $r8$lambda$onHHKwH_Ba-sJsDEm0bE2Vp9bMA, reason: not valid java name */
    public static void m1086$r8$lambda$onHHKwH_BasJsDEm0bE2Vp9bMA(boolean r0) {
        AiConfig.setShowResponseOnly(r0);
    }

    public AiPreferencesActivity() {
    }

    private final UItem createTemperatureSliderItem() {
        UItem r6 = UItem.asIntSlideView(1, 0, AiConfig.getTemperature(), 20, new AiPreferencesActivity$$ExternalSyntheticLambda0(this), new AiPreferencesActivity$$ExternalSyntheticLambda1(AnonymousClass3.INSTANCE));
        r6.id = PreferenceItem.TEMPERATURE.getId();
        r6.text = LocaleController.getString(R.string.AITemperature);
        return r6;
    }

    private final CharSequence formatTemperature(int r2) {
        StringCompanionObject r1 = StringCompanionObject.INSTANCE;
        return String.format(Locale.US, "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(r2 / 10.0f)}, 1));
    }

    private final String getEndpointValue() {
        if (AiController.getInstance().getSelected().isOnDevice() == true) goto L5;
        String r4 = new URL(AiController.getInstance().getSelected().getUrl()).getHost();     // Catch: MalformedURLException -> L21
        if (TextUtils.isEmpty(r4) == true) goto L20;
        if (AiController.canUseAI() == false) goto L20;
        if (StringsKt.contains$default(r4, "generativelanguage.googleapis", false, 2, null) == false) goto L24;
        return "Gemini";
    L24:
        return r4;
    L20:
        return LocaleController.getString(R.string.BlockedEmpty);
    L22:
        return LocaleController.getString(R.string.BlockedEmpty);
    L5:
        if (AiController.canUseAI() == false) goto L9;
        return LocaleController.getString(R.string.AIOnDevice);
    L9:
        return LocaleController.getString(R.string.BlockedEmpty);
    }

    private final CharSequence getHistoryValue() {
        if (AiConfig.getSaveHistory() == false) goto L5;
        int r2 = AiConfig.getConversationHistory().size();
        if (r2 != 0) goto L11;
        return LocaleController.getString(R.string.BlockedEmpty);
    L11:
        return LocaleController.formatPluralString("messages", r2, new Object[0]);
    L5:
        return LocaleController.getString(R.string.BlurOff);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r17, UniversalAdapter r18) {
        r17.add(UItem.asTopView(getTitle(), LocaleController.getString(R.string.AIChatInfo2), "exteraGramPlaceholders", "🤖"));
        r17.add(UItem.asShadow());
        r17.add(UItem.asHeader(LocaleController.getString(R.string.General)));
        PreferenceItem r1 = PreferenceItem.ENDPOINT;
        String r2 = LocaleController.getString(R.string.Services);
        int r3 = R.drawable.msg_language;
        String r4 = getEndpointValue();
        Boolean r11 = Boolean.TRUE;
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, r1, r2, r3, r4, null, null, false, "aiServices", false, false, r11, 880, null));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, PreferenceItem.ROLE, LocaleController.getString(R.string.Roles), R.drawable.msg_openprofile, AiConfig.getSelectedRole(), null, null, false, "aiRoles", false, false, r11, 880, null));
        r17.add(BasePreferencesActivity.uButton$TMessagesProj$default(this, PreferenceItem.HISTORY, LocaleController.getString(R.string.MessageHistory), R.drawable.msg_discuss, getHistoryValue(), null, null, false, "aiHistory", false, false, r11, 880, null));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.HistoryInfo)));
        r17.add(UItem.asHeader(LocaleController.getString(R.string.AIReplaceTelegram)));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, PreferenceItem.REPLACE_TELEGRAM_EDITOR, LocaleController.getString(R.string.AIFeaturesEditor), AiConfig.getReplaceTelegramEditor$default(0, 1, null), LocaleController.getString(R.string.AIReplaceTelegramEditorInfo), 0, false, "replaceTelegramEditor", false, 0, 432, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, PreferenceItem.REPLACE_TELEGRAM_SUMMARIES, LocaleController.getString(R.string.AIFeaturesSummaries), AiConfig.getReplaceTelegramSummaries$default(0, 1, null), LocaleController.getString(R.string.AIReplaceTelegramSummariesInfo), 0, false, "replaceTelegramSummaries", false, 0, 432, null).showDivider(false));
        r17.add(UItem.asShadow(LocaleController.getString(R.string.AIReplaceTelegramInfo)));
        r17.add(UItem.asHeader(LocaleController.getString(R.string.AIGeneration)));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, PreferenceItem.RESPONSE_STREAMING, LocaleController.getString(R.string.ResponseStreaming), AiConfig.getResponseStreaming(), LocaleController.getString(R.string.ResponseStreamingInfo), 0, false, "responseStreaming", false, 0, 432, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, PreferenceItem.SHOW_RESPONSE_ONLY, LocaleController.getString(R.string.ShowResponseOnly), AiConfig.getShowResponseOnly(), null, 0, false, "showResponseOnly", false, 0, 440, null));
        r17.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, PreferenceItem.INSERT_AS_QUOTE, LocaleController.getString(R.string.InsertResponseAsQuote), AiConfig.getInsertAsQuote(), null, 0, false, "insertResponseAsQuote", false, 0, 440, null).showDivider(false));
        r17.add(UItem.asShadow());
        if (SettingsRegistry.markAsNewFeature("aiTemperature") == false) goto L5;
        CharSequence r22 = LocaleUtils.applyNewSpan(LocaleController.getString(R.string.AITemperature));
    L6:
        r17.add(UItem.asHeader(r22));
        UItem r23 = createTemperatureSliderItem();
        r23.showDivider(false);
        r23.setSearchable(this);
        r23.setLinkAlias("aiTemperature", this);
        r17.add(r23);
        r17.add(UItem.asShadow(LocaleController.getString(R.string.AITemperatureInfo)));
        return;
    L5:
        r22 = LocaleController.getString(R.string.AITemperature);
        goto L6
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.AIChat);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public boolean needHideTitle() {
        return true;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        int r22 = r1.id;
        if (r22 > 0) goto L5;
        return;
    L5:
        if (r22 <= PreferenceItem.getEntries().size()) goto L8;
        return;
    L8:
        switch(WhenMappings.$EnumSwitchMapping$0[PreferenceItem.getEntries().get(r1.id - 1).ordinal()]) {
            case 1: goto L24;
            case 2: goto L22;
            case 3: goto L20;
            case 4: goto L18;
            case 5: goto L16;
            case 6: goto L14;
            case 7: goto L12;
            case 8: goto L10;
            default: goto L28;
        };
    L10:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new AiPreferencesActivity$$ExternalSyntheticLambda6());
        return;
    L12:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new AiPreferencesActivity$$ExternalSyntheticLambda5());
        return;
    L14:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new AiPreferencesActivity$$ExternalSyntheticLambda4());
        return;
    L16:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new AiPreferencesActivity$$ExternalSyntheticLambda3());
        return;
    L18:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new AiPreferencesActivity$$ExternalSyntheticLambda2());
        return;
    L20:
        presentFragment(new AiHistoryActivity());
        return;
    L22:
        presentFragment(new RolesActivity());
        return;
    L24:
        presentFragment(new ServicesActivity());
        return;
    }
}
