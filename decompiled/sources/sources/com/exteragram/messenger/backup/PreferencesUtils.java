package com.exteragram.messenger.backup;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.collection.LongSparseArray;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.ai.AiConfig;
import com.exteragram.messenger.ai.network.backend.OnDeviceAvailability;
import com.exteragram.messenger.pillstack.core.PillStackConfig;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.translator.TranslationProviders;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ShareAlert;

/* loaded from: classes.dex */
public class PreferencesUtils {
    private static final BackupItem[] aiConfigKeys = null;
    private static final String[] configs = null;
    private static final Set<String> excludedExteraKeys = null;
    private static final BackupItem[] extraExteraKeys = null;
    private static PreferencesUtils instance;
    private static final BackupItem[] mainConfigKeys = null;
    private static final BackupItem[] pillStackConfigKeys = null;
    private final Gson gson;

    /* renamed from: com.exteragram.messenger.backup.PreferencesUtils$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass1 extends ShareAlert {
        final /* synthetic */ PreferencesUtils this$0;
        final /* synthetic */ BaseFragment val$fragment;

        public static /* synthetic */ void $r8$lambda$L3igPCL8wrlthy9BsJ9gFuvBx8I(BaseFragment r2) {
            BulletinFactory.of(r2).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.SettingsSaved)).show();
        }

        public AnonymousClass1(PreferencesUtils r16, Context r17, ChatActivity r18, ArrayList r19, String r20, String r21, String r22, boolean r23, String r24, String r25, boolean r26, boolean r27, boolean r28, Integer r29, Theme.ResourcesProvider r30, BaseFragment r31) {
            this.this$0 = r16;
            this.val$fragment = r31;
            super(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30);
        }

        @Override // org.telegram.ui.Components.ShareAlert
        public void onSend(LongSparseArray<TLRPC.Dialog> r1, int r2, TLRPC.TL_forumTopic r3, boolean r4) {
            if (r4 == false) goto L5;
            AndroidUtilities.runOnUIThread(new PreferencesUtils$1$$ExternalSyntheticLambda0(this.val$fragment), 250);
            return;
        }
    }

    public static class BackupItem implements Serializable {
        public Class<?> clazz;
        public String key;

        public BackupItem(String r1, Class<?> r2) {
            this.key = r1;
            this.clazz = r2;
        }

        public boolean equals(Object r3) {
            if (this != r3) goto L5;
            return true;
        L5:
            if (r3 != null) goto L7;
            return false;
        L7:
            if (getClass() == r3.getClass()) goto L10;
            return false;
        L10:
            return this.key.equals(((BackupItem) r3).key);
        }
    }

    static {
        excludedExteraKeys = PreferencesUtils$$ExternalSyntheticBackport0.m(new String[]{"pluginsUnknownSources", "editingIconPackId", "iconPacksLayout", "iconPacksHidden", "updateScheduleTimestamp", "sdkUpdateScheduleTimestamp", "selectedService", "selectedServiceId", "lastActivePillId", "customWeatherLocation", "customWeatherAddress", "debugCameraMetrics", "forceCompactSavedMusic", "disableApiRequests", "disableChatFadeWallpaperBlend", "chatFadeUseWhiteBackground", "heapMonitorEnabled", "heapMonitorLimitMb"});
        extraExteraKeys = new BackupItem[]{new BackupItem("bottomNavigationBarMode", Integer.class), new BackupItem("mainMenuLayout", String.class), new BackupItem("mainMenuHiddenItems", String.class), new BackupItem("targetLangSend", String.class), new BackupItem("pluginsEngine", Boolean.class), new BackupItem("pinnedPlugins", Set.class), new BackupItem("hideStickerTime", Boolean.class), new BackupItem("tabCounter", Boolean.class), new BackupItem("springAnimations", Boolean.class), new BackupItem("aospTransitions", Boolean.class), new BackupItem("stickerCornerRoundness", Integer.class), new BackupItem("saveHistory", Boolean.class), new BackupItem("responseStreaming", Boolean.class), new BackupItem("temperature", Integer.class), new BackupItem("showResponseOnly", Boolean.class), new BackupItem("insertAsQuote", Boolean.class), new BackupItem("onDeviceReasoning", Boolean.class), new BackupItem("onDevicePreviewModel", Boolean.class), new BackupItem("onDeviceFastModel", Boolean.class), new BackupItem("replaceTelegramEditor", Boolean.class), new BackupItem("replaceTelegramSummaries", Boolean.class), new BackupItem("selectedRole", String.class), new BackupItem("infiniteScrolling", Boolean.class), new BackupItem("useCurrentLocation", Boolean.class), new BackupItem("gramTargetCurrency", String.class), new BackupItem("btcTargetCurrency", String.class), new BackupItem("usdTargetCurrency", String.class)};
        aiConfigKeys = new BackupItem[]{new BackupItem("roles", String.class)};
        pillStackConfigKeys = new BackupItem[]{new BackupItem("activePills", String.class), new BackupItem("hiddenPills", String.class)};
        mainConfigKeys = new BackupItem[]{new BackupItem("ChatSwipeAction", Integer.class), new BackupItem("mediaColumnsCount", Integer.class), new BackupItem("bubbleRadius", Integer.class), new BackupItem("fons_size", Integer.class)};
        configs = new String[]{"exteraconfig", "aiConfig", "pillstackconfig", "mainconfig"};
    }

    public PreferencesUtils() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    private boolean checkKeys(JsonObject r12) {
        String[] r0 = configs;
        int r1 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L15;
        String r4 = r0[r3];
        JsonObject r5 = getConfigObject(r12, r4);
        if (r5 == null) goto L14;
        Iterator<String> r6 = r5.keySet().iterator();
    L8:
        if (r6.hasNext() == false) goto L14;
        String r7 = r6.next();
        JsonElement r8 = r5.get(r7);
        if (isExpectedValue(r4, r7, r8) == true) goto L11;
        FileLog.e("Unexpected value: " + r7 + " " + r8);
        goto L8
    L11:
        return true;
    L14:
        r3 = r3 + 1;
        goto L3
    L15:
        return false;
    }

    public static void clearPreferences() {
        AiConfig.getEditor().clear().apply();
        PillStackConfig.getEditor().clear().apply();
        ExteraConfig.getEditor().clear().apply();
        ExteraConfig.reloadConfig();
        PillStackConfig.reloadConfig();
    }

    private BackupItem findBackupItem(String r6, String r7) {
        if (TextUtils.isEmpty(r6) == false) goto L5;
    L50:
        return null;
    L5:
        if (TextUtils.isEmpty(r7) == true) goto L50;
        r6.getClass();
        int r2 = 0;
        char r3 = 65535;
        switch(r6.hashCode()) {
            case -2023441014: goto L23;
            case -1951469327: goto L19;
            case 22218901: goto L15;
            case 918264379: goto L11;
            default: goto L26;
        };
    L26:
        switch(r3) {
            case 0: goto L49;
            case 1: goto L47;
            case 2: goto L30;
            case 3: goto L29;
            default: goto L27;
        };
    L27:
        return null;
    L30:
        BackupItem[] r5 = extraExteraKeys;
        int r62 = r5.length;
        int r0 = 0;
    L31:
        if (r0 >= r62) goto L37;
        BackupItem r32 = r5[r0];
        if (r32.key.equals(r7) == true) goto L34;
        r0 = r0 + 1;
        goto L31
    L34:
        return r32;
    L37:
        if (excludedExteraKeys.contains(r7) == false) goto L39;
        return null;
    L39:
        BackupItem[] r52 = ExteraConfig.getBackupKeys();
        int r63 = r52.length;
    L40:
        if (r2 >= r63) goto L45;
        BackupItem r02 = r52[r2];
        if (r02.key.equals(r7) == true) goto L43;
        r2 = r2 + 1;
        goto L40
    L43:
        return r02;
    L45:
        return null;
    L29:
        return findBackupItem(mainConfigKeys, r7);
    L47:
        return findBackupItem(pillStackConfigKeys, r7);
    L49:
        return findBackupItem(aiConfigKeys, r7);
    L11:
        if (r6.equals("mainconfig") == false) goto L26;
        r3 = 3;
        goto L26
    L15:
        if (r6.equals("exteraconfig") == false) goto L26;
        r3 = 2;
        goto L26
    L19:
        if (r6.equals("pillstackconfig") == false) goto L26;
        r3 = 1;
        goto L26
    L23:
        if (r6.equals("aiConfig") == false) goto L26;
        r3 = 0;
        goto L26
    }

    private BackupItem findBackupItem(BackupItem[] r4, String r5) {
        int r3 = r4.length;
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L8;
        BackupItem r1 = r4[r0];
        if (r1.key.equals(r5) == true) goto L6;
        r0 = r0 + 1;
        goto L3
    L6:
        return r1;
    L8:
        return null;
    }

    public static String generateBackupName(String r1) {
        StringBuilder r0 = new StringBuilder();
        if (r1 != null) goto L6;
        r1 = "backup";
    L6:
        r0.append(r1);
        r0.append("-");
        r0.append(Utilities.generateRandomString(4));
        r0.append(".extera");
        return r0.toString();
    }

    private JsonObject getConfigObject(JsonObject r2, String r3) {
        if (r2.has(r3) == false) goto L9;
        if (r2.get(r3).isJsonObject() == false) goto L9;
        return r2.getAsJsonObject(r3);
    L9:
        if ("mainconfig".equals(r3) == false) goto L19;
        Iterator<String> r1 = r2.keySet().iterator();
    L12:
        if (r1.hasNext() == false) goto L27;
        String r32 = r1.next();
        if (r32.matches("^mainconfig\\d+$") == false) goto L12;
        if (r2.get(r32).isJsonObject() == false) goto L12;
        return r2.getAsJsonObject(r32);
    L27:
        return null;
    L19:
        return null;
    }

    private static Context getContext() {
        if (ApplicationLoader.applicationContext == null) goto L7;
        return ApplicationLoader.applicationContext;
    L7:
        return AndroidUtilities.getActivity();
    }

    private Integer getExactInteger(JsonElement r2) {
        if (r2.isJsonPrimitive() == true) goto L5;
    L9:
        return null;
    L5:
        if (r2.getAsJsonPrimitive().isNumber() == false) goto L9;
        return Integer.valueOf(new BigDecimal(r2.getAsString()).intValueExact());
    }

    private Long getExactLong(JsonElement r2) {
        if (r2.isJsonPrimitive() == true) goto L5;
    L9:
        return null;
    L5:
        if (r2.getAsJsonPrimitive().isNumber() == false) goto L9;
        return Long.valueOf(new BigDecimal(r2.getAsString()).longValueExact());
    }

    public static PreferencesUtils getInstance() {
        if (instance != null) goto L6;
        instance = new PreferencesUtils();
    L6:
        return instance;
    }

    public static SharedPreferences getPreferences(String r2) {
        return getContext().getSharedPreferences(r2, 0);
    }

    private void importConfig(JsonObject r8, String r9) {
        JsonObject r82 = getConfigObject(r8, r9);
        if (r82 == null) goto L48;
        SharedPreferences.Editor r0 = getPreferences(r9).edit();
        Iterator<String> r1 = r82.keySet().iterator();
    L6:
        if (r1.hasNext() == false) goto L34;
        String r2 = r1.next();
        JsonElement r3 = r82.get(r2);
        BackupItem r4 = findBackupItem(r9, r2);
        if (r4 == null) goto L6;
        if (isExpectedValue(r9, r2, r3) == false) goto L6;
        if (r4.clazz.equals(Boolean.class) == false) goto L16;
        r0.putBoolean(r2, r3.getAsBoolean());
    L30:
        if ("exteraconfig".equals(r9) == false) goto L6;
        if (r2.equals("iconPack") == false) goto L6;
        r0.remove("iconPacksLayout");
        r0.remove("iconPacksHidden");
        goto L6
    L16:
        if (r4.clazz.equals(Float.class) == false) goto L19;
        r0.putFloat(r2, r3.getAsFloat());
        goto L30
    L19:
        if (r4.clazz.equals(String.class) == false) goto L22;
        r0.putString(r2, r3.getAsString());
        goto L30
    L22:
        if (r4.clazz.equals(Set.class) == false) goto L28;
        HashSet r42 = new HashSet();
        Iterator<JsonElement> r32 = r3.getAsJsonArray().iterator();
    L25:
        if (r32.hasNext() == false) goto L27;
        r42.add(r32.next().getAsString());
        goto L25
    L27:
        r0.putStringSet(r2, r42);
        goto L30
    L28:
        r0.putInt(r2, r3.getAsInt());
        goto L30
    L34:
        r0.apply();
        return;
    }

    private boolean isExpectedFloat(String r4, float r5) {
        if (Float.isFinite(r5) == true) goto L5;
        return false;
    L5:
        r4.getClass();
        char r2 = 65535;
        switch(r4.hashCode()) {
            case -1698736169: goto L29;
            case 332409125: goto L25;
            case 1035744803: goto L21;
            case 1371159497: goto L17;
            case 1596683998: goto L13;
            case 1979539437: goto L9;
            default: goto L33;
        };
    L9:
        if (r4.equals("predictiveBackIntensity") == false) goto L33;
        r2 = 5;
        goto L33
    L13:
        if (r4.equals("stickerSize") == false) goto L33;
        r2 = 4;
        goto L33
    L17:
        if (r4.equals("flashWarmth") == false) goto L33;
        r2 = 3;
        goto L33
    L21:
        if (r4.equals("flashIntensity") == false) goto L33;
        r2 = 2;
        goto L33
    L25:
        if (r4.equals("avatarCorners") == false) goto L33;
        r2 = 1;
        goto L33
    L29:
        if (r4.equals("sectionRadius") == false) goto L33;
        r2 = 0;
    L33:
        switch(r2) {
            case 0: goto L60;
            case 1: goto L54;
            case 2: goto L48;
            case 3: goto L48;
            case 4: goto L42;
            case 5: goto L36;
            default: goto L34;
        };
    L34:
        return false;
    L36:
        if (r5 >= 0.0f) goto L38;
    L40:
        return false;
    L38:
        if (r5 > 5.0f) goto L40;
        return true;
    L42:
        if (r5 >= 4.0f) goto L44;
    L46:
        return false;
    L44:
        if (r5 > 20.0f) goto L46;
        return true;
    L48:
        if (r5 >= 0.0f) goto L50;
    L52:
        return false;
    L50:
        if (r5 > 1.0f) goto L52;
        return true;
    L54:
        if (r5 >= 0.0f) goto L56;
    L58:
        return false;
    L56:
        if (r5 > 28.0f) goto L58;
        return true;
    L60:
        if (r5 >= 0.0f) goto L62;
    L64:
        return false;
    L62:
        if (r5 > 28.0f) goto L64;
        return true;
    }

    private boolean isExpectedInteger(String r14, int r15) {
        r14.getClass();
        char r12 = 65535;
        switch(r14.hashCode()) {
            case -2135756891: goto L110;
            case -2042456988: goto L106;
            case -2012107777: goto L102;
            case -2011270082: goto L98;
            case -1930192008: goto L94;
            case -1760806602: goto L90;
            case -1666784700: goto L86;
            case -1568535214: goto L82;
            case -1486206679: goto L78;
            case -1366314387: goto L74;
            case -1294437140: goto L70;
            case -1261503057: goto L66;
            case -1220992488: goto L62;
            case -927786875: goto L58;
            case -900922648: goto L54;
            case -738054606: goto L50;
            case -337321073: goto L46;
            case -143982549: goto L42;
            case 31430900: goto L38;
            case 318680851: goto L34;
            case 321701236: goto L30;
            case 443069197: goto L26;
            case 641570999: goto L22;
            case 767180638: goto L18;
            case 914824130: goto L14;
            case 1768235325: goto L10;
            case 2013084318: goto L6;
            default: goto L113;
        };
    L113:
        switch(r12) {
            case 0: goto L161;
            case 1: goto L157;
            case 2: goto L157;
            case 3: goto L153;
            case 4: goto L149;
            case 5: goto L145;
            case 6: goto L157;
            case 7: goto L140;
            case 8: goto L157;
            case 9: goto L157;
            case 10: goto L157;
            case 11: goto L157;
            case 12: goto L157;
            case 13: goto L157;
            case 14: goto L136;
            case 15: goto L157;
            case 16: goto L132;
            case 17: goto L157;
            case 18: goto L128;
            case 19: goto L161;
            case 20: goto L124;
            case 21: goto L120;
            case 22: goto L157;
            case 23: goto L161;
            case 24: goto L115;
            case 25: goto L157;
            case 26: goto L157;
            default: goto L114;
        };
    L114:
        return false;
    L115:
        if (r15 >= 0) goto L117;
    L119:
        return false;
    L117:
        if (r15 > TranslationProviders.getLastIndex()) goto L119;
        return true;
    L120:
        if (r15 < 0) goto L123;
        if (r15 > 9) goto L123;
        return true;
    L123:
        return false;
    L124:
        if (r15 < 0) goto L127;
        if (r15 > 20) goto L127;
        return true;
    L127:
        return false;
    L128:
        if (r15 < 0) goto L131;
        if (r15 > 4) goto L131;
        return true;
    L131:
        return false;
    L132:
        if (r15 < 0) goto L135;
        if (r15 > 7) goto L135;
        return true;
    L135:
        return false;
    L136:
        if (r15 < 0) goto L139;
        if (r15 > 8) goto L139;
        return true;
    L139:
        return false;
    L140:
        if (r15 >= 12) goto L142;
    L144:
        return false;
    L142:
        if (r15 > 30) goto L144;
        return true;
    L145:
        if (r15 < 2) goto L148;
        if (r15 > 9) goto L148;
        return true;
    L148:
        return false;
    L149:
        if (r15 < 0) goto L152;
        if (r15 > 5) goto L152;
        return true;
    L152:
        return false;
    L153:
        if (r15 < 0) goto L156;
        if (r15 > 17) goto L156;
        return true;
    L156:
        return false;
    L157:
        if (r15 < 0) goto L160;
        if (r15 > 2) goto L160;
        return true;
    L160:
        return false;
    L161:
        if (r15 < 0) goto L164;
        if (r15 > 3) goto L164;
        return true;
    L164:
        return false;
    L6:
        if (r14.equals("showIdAndDc") == false) goto L113;
        r12 = 26;
        goto L113
    L10:
        if (r14.equals("bottomButton") == false) goto L113;
        r12 = 25;
        goto L113
    L14:
        if (r14.equals("translationProvider") == false) goto L113;
        r12 = 24;
        goto L113
    L18:
        if (r14.equals("doubleTapSeekDuration") == false) goto L113;
        r12 = 23;
        goto L113
    L22:
        if (r14.equals("bottomNavigationBarMode") == false) goto L113;
        r12 = 22;
        goto L113
    L26:
        if (r14.equals("doubleTapActionOutOwner") == false) goto L113;
        r12 = 21;
        goto L113
    L30:
        if (r14.equals("temperature") == false) goto L113;
        r12 = 20;
        goto L113
    L34:
        if (r14.equals("stickerCornerRoundness") == false) goto L113;
        r12 = 19;
        goto L113
    L38:
        if (r14.equals("eventType") == false) goto L113;
        r12 = 18;
        goto L113
    L42:
        if (r14.equals("glassOutlineStyle") == false) goto L113;
        r12 = 17;
        goto L113
    L46:
        if (r14.equals("doNotUseProxy") == false) goto L113;
        r12 = 16;
        goto L113
    L50:
        if (r14.equals("iconPack") == false) goto L113;
        r12 = 15;
        goto L113
    L54:
        if (r14.equals("doubleTapAction") == false) goto L113;
        r12 = 14;
        goto L113
    L58:
        if (r14.equals("tabIcons") == false) goto L113;
        r12 = '\r';
        goto L113
    L62:
        if (r14.equals("dividerStyle") == false) goto L113;
        r12 = '\f';
        goto L113
    L66:
        if (r14.equals("transitionAnimation") == false) goto L113;
        r12 = 11;
        goto L113
    L70:
        if (r14.equals("videoMessagesCamera") == false) goto L113;
        r12 = '\n';
        goto L113
    L74:
        if (r14.equals("stickerTimeMode") == false) goto L113;
        r12 = '\t';
        goto L113
    L78:
        if (r14.equals("tabletMode") == false) goto L113;
        r12 = '\b';
        goto L113
    L82:
        if (r14.equals("fons_size") == false) goto L113;
        r12 = 7;
        goto L113
    L86:
        if (r14.equals("downloadSpeedBoost") == false) goto L113;
        r12 = 6;
        goto L113
    L90:
        if (r14.equals("mediaColumnsCount") == false) goto L113;
        r12 = 5;
        goto L113
    L94:
        if (r14.equals("ChatSwipeAction") == false) goto L113;
        r12 = 4;
        goto L113
    L98:
        if (r14.equals("bubbleRadius") == false) goto L113;
        r12 = 3;
        goto L113
    L102:
        if (r14.equals("cameraType") == false) goto L113;
        r12 = 2;
        goto L113
    L106:
        if (r14.equals("stickerShape") == false) goto L113;
        r12 = 1;
        goto L113
    L110:
        if (r14.equals("titleText") == false) goto L113;
        r12 = 0;
        goto L113
    }

    private boolean isExpectedMainMenuLayout(String r6, boolean r7) {
        JsonElement r62 = (JsonElement) this.gson.fromJson(r6, JsonElement.class);
        if (r62 != null) goto L5;
    L24:
        return false;
    L5:
        if (r62.isJsonArray() == false) goto L24;
        if (r62.getAsJsonArray().size() > 100) goto L24;
        HashSet r1 = new HashSet();
        Iterator<JsonElement> r63 = r62.getAsJsonArray().iterator();
    L11:
        if (r63.hasNext() == false) goto L22;
        Integer r2 = getExactInteger(r63.next());
        if (r2 == null) goto L14;
        if (r2.intValue() == (-1)) goto L17;
        if (r1.add(r2) == true) goto L11;
        return false;
    L17:
        if (r7 == true) goto L11;
        return false;
    L14:
        return false;
    L22:
        return true;
    }

    private boolean isExpectedPillsLayout(String r6) {
        if (r6.length() > 4096) goto L25;
        if (TextUtils.isEmpty(r6) == true) goto L25;
        HashSet r5 = new HashSet();
        String[] r62 = r6.split(",");
        int r0 = r62.length;
        int r2 = 0;
    L8:
        if (r2 >= r0) goto L20;
        int r3 = Integer.parseInt(r62[r2].trim());     // Catch: NumberFormatException -> L26
        if (r3 <= 0) goto L18;
        if (r3 > 100000) goto L18;
        if (r5.add(Integer.valueOf(r3)) == false) goto L18;
        r2 = r2 + 1;
    L18:
        return false;
    L20:
        if (r5.size() > 100) goto L23;
        return true;
    L23:
        return false;
    L25:
        return TextUtils.isEmpty(r6);
    }

    private boolean isExpectedRoles(String r7) {
        JsonElement r72 = (JsonElement) this.gson.fromJson(r7, JsonElement.class);
        if (r72 != null) goto L5;
    L40:
        return false;
    L5:
        if (r72.isJsonArray() == false) goto L40;
        if (r72.getAsJsonArray().size() > 100) goto L40;
        HashSet r1 = new HashSet();
        Iterator<JsonElement> r73 = r72.getAsJsonArray().iterator();
    L11:
        if (r73.hasNext() == false) goto L38;
        JsonElement r2 = r73.next();
        if (r2.isJsonObject() == false) goto L14;
        JsonObject r22 = r2.getAsJsonObject();
        if (isRequiredString(r22, "name", 256) == false) goto L37;
        if (isRequiredString(r22, "prompt", 65536) == false) goto L37;
        if (r1.add(r22.get("name").getAsString()) == false) goto L37;
        if (r22.has("emojiId") == false) goto L30;
        if (r22.get("emojiId").isJsonNull() == true) goto L30;
        if (getExactLong(r22.get("emojiId")) != null) goto L30;
        return false;
    L30:
        if (r22.has("isSuggestion") == false) goto L11;
        if (r22.get("isSuggestion").isJsonNull() == true) goto L11;
        if (r22.get("isSuggestion").isJsonPrimitive() == false) goto L37;
        if (r22.get("isSuggestion").getAsJsonPrimitive().isBoolean() == true) goto L11;
    L37:
        return false;
    L14:
        return false;
    L38:
        return true;
    }

    private boolean isExpectedString(String r5, String r6, String r7) {
        if (r7 != null) goto L5;
    L89:
        return false;
    L5:
        if (r7.length() > 1048576) goto L89;
        r6.getClass();
        char r3 = 65535;
        switch(r6.hashCode()) {
            case -1754521167: goto L35;
            case -721687453: goto L31;
            case -597720621: goto L27;
            case -41482238: goto L23;
            case 486360863: goto L19;
            case 1088283463: goto L15;
            case 1662029406: goto L11;
            default: goto L38;
        };
    L38:
        switch(r3) {
            case 0: goto L85;
            case 1: goto L78;
            case 2: goto L76;
            case 3: goto L74;
            case 4: goto L67;
            case 5: goto L67;
            case 6: goto L65;
            default: goto L40;
        };
    L40:
        if ("aiConfig".equals(r5) == false) goto L46;
        if (r6.equals("roles") == false) goto L46;
        return isExpectedRoles(r7);
    L46:
        if ("pillstackconfig".equals(r5) == false) goto L54;
        if (r6.equals("activePills") == true) goto L52;
        if (r6.equals("hiddenPills") == false) goto L54;
    L52:
        return isExpectedPillsLayout(r7);
    L54:
        if (r6.equals("gramTargetCurrency") == true) goto L63;
        if (r6.equals("btcTargetCurrency") == true) goto L63;
        if (r6.equals("usdTargetCurrency") == true) goto L63;
        return !TextUtils.isEmpty(r7);
    L63:
        return r7.matches("^[A-Z]{3,5}$");
    L65:
        return isExpectedMainMenuLayout(r7, false);
    L67:
        if (r7.equalsIgnoreCase(Common.ASSET_APP) == false) goto L69;
    L72:
        return true;
    L69:
        if (r7.matches("^[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*$") == true) goto L72;
        return false;
    L74:
        return isExpectedMainMenuLayout(r7, true);
    L76:
        return r7.matches("^(?!\\.{1,2}$)[A-Za-z0-9._ -]{1,255}$");
    L78:
        if (r7.isEmpty() == false) goto L80;
    L83:
        return true;
    L80:
        if (r7.matches("^\\d{1,2}(,\\d{1,2})*$") == true) goto L83;
        return false;
    L85:
        if (TextUtils.isEmpty(r7) == true) goto L89;
        if (r7.length() > 256) goto L89;
        return true;
    L11:
        if (r6.equals("mainMenuHiddenItems") == false) goto L38;
        r3 = 6;
        goto L38
    L15:
        if (r6.equals("targetLangSend") == false) goto L38;
        r3 = 5;
        goto L38
    L19:
        if (r6.equals("targetLang") == false) goto L38;
        r3 = 4;
        goto L38
    L23:
        if (r6.equals("mainMenuLayout") == false) goto L38;
        r3 = 3;
        goto L38
    L27:
        if (r6.equals("customSavePath") == false) goto L38;
        r3 = 2;
        goto L38
    L31:
        if (r6.equals("swipeActions") == false) goto L38;
        r3 = 1;
        goto L38
    L35:
        if (r6.equals("selectedRole") == false) goto L38;
        r3 = 0;
        goto L38
    }

    private boolean isExpectedStringSet(String r4, JsonElement r5) {
        if (r4.equals("pinnedPlugins") == true) goto L5;
    L26:
        return false;
    L5:
        if (r5.isJsonArray() == false) goto L26;
        if (r5.getAsJsonArray().size() > 1000) goto L26;
        HashSet r3 = new HashSet();
        Iterator<JsonElement> r52 = r5.getAsJsonArray().iterator();
    L11:
        if (r52.hasNext() == false) goto L24;
        JsonElement r0 = r52.next();
        if (r0.isJsonPrimitive() == false) goto L23;
        if (r0.getAsJsonPrimitive().isString() == false) goto L23;
        String r02 = r0.getAsString();
        if (TextUtils.isEmpty(r02) == true) goto L23;
        if (r02.length() > 255) goto L23;
        if (r3.add(r02) == true) goto L11;
    L23:
        return false;
    L24:
        return true;
    }

    private boolean isExpectedValue(String r6, String r7, Object r8) {
        BackupItem r0 = findBackupItem(r6, r7);
        if (r0 == null) goto L54;
        if (r8 == null) goto L54;
        if ((r8 instanceof JsonElement) == false) goto L9;
        JsonElement r82 = (JsonElement) r8;
    L55:
    L17:
        e = move-exception;
        FileLog.e(e);
        goto L54
    L11:
        if (r0.clazz.equals(Boolean.class) == false) goto L21;
        if (r82.isJsonPrimitive() == true) goto L15;
    L19:
        return false;
    L15:
        if (r82.getAsJsonPrimitive().isBoolean() == false) goto L19;
        return true;
    L21:
        if (r0.clazz.equals(Float.class) == false) goto L31;
        if (r82.isJsonPrimitive() == true) goto L25;
    L29:
        return false;
    L25:
        if (r82.getAsJsonPrimitive().isNumber() == false) goto L29;
        if (isExpectedFloat(r7, r82.getAsFloat()) == false) goto L29;
        return true;
    L31:
        if (r0.clazz.equals(String.class) == false) goto L41;
        if (r82.isJsonPrimitive() == true) goto L35;
    L39:
        return false;
    L35:
        if (r82.getAsJsonPrimitive().isString() == false) goto L39;
        if (isExpectedString(r6, r7, r82.getAsString()) == false) goto L39;
        return true;
    L41:
        if (r0.clazz.equals(Set.class) == false) goto L45;
        return isExpectedStringSet(r7, r82);
    L45:
        if (r0.clazz.equals(Long.class) == false) goto L47;
        return false;
    L47:
        Integer r62 = getExactInteger(r82);     // Catch: Exception -> L17
        if (r62 != null) goto L50;
    L52:
        return false;
    L50:
        if (isExpectedInteger(r7, r62.intValue()) == false) goto L52;
        return true;
    L9:
        r82 = this.gson.toJsonTree(r8);
    L54:
        return false;
    }

    private boolean isRequiredString(JsonObject r1, String r2, int r3) {
        if (r1.has(r2) == true) goto L5;
        return false;
    L5:
        if (r1.get(r2).isJsonNull() == false) goto L7;
        return false;
    L7:
        if (r1.get(r2).isJsonPrimitive() == true) goto L9;
        return false;
    L9:
        if (r1.get(r2).getAsJsonPrimitive().isString() == true) goto L11;
        return false;
    L11:
        if (TextUtils.isEmpty(r1.get(r2).getAsString()) == false) goto L13;
        return false;
    L13:
        if (r1.get(r2).getAsString().length() > r3) goto L22;
        return true;
    L22:
        return false;
    }

    private String readAndDecryptFile(File r2) {
        BufferedReader r1 = new BufferedReader(new FileReader(r2));
        StringBuilder r22 = new StringBuilder();     // Catch: Throwable -> L7
    L4:
        String r0 = r1.readLine();     // Catch: Throwable -> L7
        if (r0 == null) goto L9;
        r22.append(r0);     // Catch: Throwable -> L7
        goto L4
    L9:
        String r23 = r22.toString();     // Catch: Throwable -> L7
        if (InvisibleEncryptor.isEncrypted(r23) == false) goto L12;
        r23 = InvisibleEncryptor.decode(r23);     // Catch: Throwable -> L7
    L12:
        r1.close();
        return r23;
    L7:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L16
    L18:
        throw th;
    L16:
        th = move-exception;
        th.addSuppressed(th);
        goto L18
    }

    private JsonObject toJsonObject(String r5, Map<String, ?> r6) {
        JsonObject r0 = new JsonObject();
        Iterator<Map.Entry<String, ?>> r62 = r6.entrySet().iterator();
    L4:
        if (r62.hasNext() == false) goto L8;
        Map.Entry<String, ?> r1 = r62.next();
        String r2 = r1.getKey();
        Object r12 = r1.getValue();
        if (isExpectedValue(r5, r2, r12) == false) goto L4;
        r0.add(r2, this.gson.toJsonTree(r12));
        goto L4
    L8:
        return r0;
    }

    public void exportSettings(BaseFragment r21) {
        File r0 = new File(FileLoader.getDirectory(4), generateBackupName(null));
        if (r0.exists() == false) goto L10;
        r0.delete();
    L10:
        OutputStreamWriter r2 = new OutputStreamWriter(new FileOutputStream(r0), StandardCharsets.UTF_8);     // Catch: Exception -> L7
        r2.write(getBackup(true));     // Catch: Exception -> L7
        r2.flush();     // Catch: Exception -> L7
        r2.close();     // Catch: Exception -> L7
        r21.showDialog(new AnonymousClass1(this, r21.getParentActivity(), null, null, r0.getAbsolutePath(), null, null, false, null, null, false, false, false, null, null, r21));     // Catch: Exception -> L7
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
    }

    public String getBackup(boolean r8) {
        AiConfig.ensureConfigMigrated();
        JsonObject r0 = new JsonObject();
        String[] r1 = configs;
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L8;
        String r4 = r1[r3];
        JsonObject r5 = toJsonObject(r4, getPreferences(r4).getAll());
        if (r5.isEmpty() == true) goto L7;
        r0.add(r4, r5);
    L7:
        r3 = r3 + 1;
        goto L3
    L8:
        String r7 = this.gson.toJson(r0);
        if (r8 == true) goto L11;
        return r7;
    L11:
        return InvisibleEncryptor.encode(r7);
    }

    public int getDiff(JsonObject r12) {
        int r0 = 0;
        if (r12 != null) goto L5;
        return 0;
    L5:
        JsonObject r1 = (JsonObject) this.gson.fromJson(getBackup(false), JsonObject.class);
        Iterator<String> r2 = r12.keySet().iterator();
    L7:
        if (r2.hasNext() == false) goto L33;
        String r3 = r2.next();
        if (r3.matches("^mainconfig\\d+$") == false) goto L11;
        String r4 = "mainconfig";
    L13:
        if (r12.get(r3).isJsonObject() == false) goto L7;
        JsonObject r32 = r12.getAsJsonObject(r3);
        if (r1.has(r4) == true) goto L17;
        Iterator<String> r5 = r32.keySet().iterator();
    L29:
        if (r5.hasNext() == false) goto L7;
        String r6 = r5.next();
        if (isExpectedValue(r4, r6, r32.get(r6)) == false) goto L29;
        r0 = r0 + 1;
        goto L29
    L17:
        JsonObject r52 = r1.getAsJsonObject(r4);
        Iterator<String> r62 = r32.keySet().iterator();
    L19:
        if (r62.hasNext() == false) goto L7;
        String r7 = r62.next();
        JsonElement r8 = r32.get(r7);
        JsonElement r9 = r52.get(r7);
        if (r52.has(r7) == false) goto L25;
        if (r8.equals(r9) == true) goto L19;
    L25:
        if (isExpectedValue(r4, r7, r8) == false) goto L19;
        r0 = r0 + 1;
        goto L19
    L11:
        r4 = r3;
        goto L13
    L33:
        return r0;
    }

    public int getDiff(File r1) {
        return getDiff(getJsonObject(r1));
    }

    public JsonObject getJsonObject(File r2) {
        String r22 = readAndDecryptFile(r2);     // Catch: Exception -> L6
        if (r22 == null) goto L12;
        return (JsonObject) this.gson.fromJson(r22, JsonObject.class);
    L12:
        return null;
    L6:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public void importSettings(File r6, Activity r7, INavigationLayout r8) {
        if (isBackup(r6) == true) goto L5;
        return;
    L5:
        AiConfig.ensureConfigMigrated();
        JsonObject r62 = getJsonObject(r6);
        String[] r0 = configs;
        int r1 = r0.length;
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L8;
        importConfig(r62, r0[r3]);
        r3 = r3 + 1;
        goto L6
    L8:
        ExteraConfig.reloadConfig();
        PillStackConfig.reloadConfig();
        SharedConfig.reloadConfig();
        OnDeviceAvailability.onModelConfigChanged(null);
        PluginsController.getInstance().restart();
        LocaleController.getInstance().recreateFormatters();
        Theme.reloadAllResources(r7);
        r8.rebuildAllFragmentViews(false, false);
        NotificationCenter r5 = AccountInstance.getInstance(UserConfig.selectedAccount).getNotificationCenter();
        r5.postNotificationName(NotificationCenter.reloadInterface, new Object[0]);
        r5.postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_CHAT)});
        r5.postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
        r5.postNotificationName(NotificationCenter.dialogFiltersUpdated, new Object[0]);
    }

    public boolean isBackup(File r4) {
        if (r4 != null) goto L5;
    L13:
        return false;
    L5:
        if (r4.getName().toLowerCase().endsWith(".extera") == false) goto L13;
        JsonObject r42 = getJsonObject(r4);
        if (r42 == null) goto L13;
        if (checkKeys(r42) == false) goto L13;
        return true;
    }

    public boolean isBackup(MessageObject r2) {
        String r0 = ChatUtils.getInstance().getPathToMessage(r2);
        if (r2 != null) goto L5;
        return false;
    L5:
        if (r2.getDocumentName() != null) goto L7;
        return false;
    L7:
        if (TextUtils.isEmpty(r0) == false) goto L9;
        return false;
    L9:
        if (isBackup(new File(r0)) == false) goto L16;
        return true;
    L16:
        return false;
    }
}
