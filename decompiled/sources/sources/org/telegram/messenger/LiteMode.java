package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.PowerManager;
import androidx.core.math.MathUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;

/* loaded from: classes.dex */
public class LiteMode {
    private static int BATTERY_HIGH = 10;
    private static int BATTERY_LOW = 10;
    private static int BATTERY_MEDIUM = 10;
    public static final int FLAGS_ANIMATED_EMOJI = 28700;
    public static final int FLAGS_ANIMATED_STICKERS = 3;
    public static final int FLAGS_CHAT = 360928;
    public static final int FLAG_ANIMATED_EMOJI_CHAT = 4112;
    public static final int FLAG_ANIMATED_EMOJI_CHAT_NOT_PREMIUM = 4096;
    public static final int FLAG_ANIMATED_EMOJI_CHAT_PREMIUM = 16;
    public static final int FLAG_ANIMATED_EMOJI_KEYBOARD = 16388;
    public static final int FLAG_ANIMATED_EMOJI_KEYBOARD_NOT_PREMIUM = 16384;
    public static final int FLAG_ANIMATED_EMOJI_KEYBOARD_PREMIUM = 4;
    public static final int FLAG_ANIMATED_EMOJI_REACTIONS = 8200;
    public static final int FLAG_ANIMATED_EMOJI_REACTIONS_NOT_PREMIUM = 8192;
    public static final int FLAG_ANIMATED_EMOJI_REACTIONS_PREMIUM = 8;
    public static final int FLAG_ANIMATED_STICKERS_CHAT = 2;
    public static final int FLAG_ANIMATED_STICKERS_KEYBOARD = 1;
    public static final int FLAG_AUTOPLAY_GIFS = 2048;
    public static final int FLAG_AUTOPLAY_VIDEOS = 1024;
    public static final int FLAG_CALLS_ANIMATIONS = 512;
    public static final int FLAG_CHAT_BACKGROUND = 32;
    public static final int FLAG_CHAT_BLUR = 256;
    public static final int FLAG_CHAT_FORUM_TWOCOLUMN = 64;
    public static final int FLAG_CHAT_SCALE = 32768;
    public static final int FLAG_CHAT_SPOILER = 128;
    public static final int FLAG_CHAT_THANOS = 65536;
    public static final int FLAG_LIQUID_GLASS = 262144;
    public static final int FLAG_PARTICLES = 131072;
    public static int PRESET_HIGH = 262143;
    public static int PRESET_LOW = 198684;
    public static int PRESET_MEDIUM = 204383;
    public static int PRESET_POWER_SAVER = 0;
    private static int lastBatteryLevelCached = -1;
    private static long lastBatteryLevelChecked;
    private static boolean lastPowerSaverApplied;
    private static boolean loaded;
    private static HashSet<Utilities.Callback<Boolean>> onPowerSaverAppliedListeners;
    private static boolean powerSaverFollowSystem;
    private static int powerSaverLevel;
    private static int value;

    public static class BatteryReceiver extends BroadcastReceiver {
        public BatteryReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r2, Intent r3) {
            int r1 = r3.getIntExtra("level", -1);
            int r22 = r3.getIntExtra("scale", -1);
            if (r1 < 0) goto L6;
            if (r22 <= 0) goto L6;
            LiteMode.m4233$$Nest$sfputlastBatteryLevelCached((r1 * 100) / r22);
            LiteMode.m4234$$Nest$sfputlastBatteryLevelChecked(System.currentTimeMillis());
        L7:
            LiteMode.getValue();
            return;
        L6:
            LiteMode.m4234$$Nest$sfputlastBatteryLevelChecked(0);
            goto L7
        }
    }

    public static /* synthetic */ void $r8$lambda$wUa83SN3wAsqe5yQuuAFmvxX3L4(boolean r3) {
        Iterator<Utilities.Callback<Boolean>> r0 = onPowerSaverAppliedListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        Utilities.Callback<Boolean> r1 = r0.next();
        if (r1 == null) goto L4;
        r1.run(Boolean.valueOf(r3));
        goto L4
    }

    /* renamed from: -$$Nest$sfputlastBatteryLevelCached, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4233$$Nest$sfputlastBatteryLevelCached(int r0) {
        lastBatteryLevelCached = r0;
    }

    /* renamed from: -$$Nest$sfputlastBatteryLevelChecked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4234$$Nest$sfputlastBatteryLevelChecked(long r0) {
        lastBatteryLevelChecked = r0;
    }

    static {
    }

    public LiteMode() {
    }

    public static void addOnPowerSaverAppliedListener(Utilities.Callback<Boolean> r1) {
        if (onPowerSaverAppliedListeners != null) goto L5;
        onPowerSaverAppliedListeners = new HashSet();
    L5:
        onPowerSaverAppliedListeners.add(r1);
    }

    public static int getBatteryLevel() {
        if (lastBatteryLevelCached < 0) goto L7;
        long r0 = System.currentTimeMillis();
        if ((r0 - lastBatteryLevelChecked) <= 12000) goto L12;
    L8:
        BatteryManager r2 = (BatteryManager) ApplicationLoader.applicationContext.getSystemService("batterymanager");
        if (r2 == null) goto L12;
        lastBatteryLevelCached = r2.getIntProperty(4);
        lastBatteryLevelChecked = r0;
    L12:
        return lastBatteryLevelCached;
    L7:
        r0 = 0;
        goto L8
    }

    public static int getPowerSaverLevel() {
        if (loaded == true) goto L6;
        loadPreference();
    L6:
        return powerSaverLevel;
    }

    public static int getValue() {
        return getValue(false);
    }

    public static int getValue(boolean r2) {
        if (loaded == true) goto L5;
        loadPreference();
    L5:
        if (r2 == true) goto L27;
        if (powerSaverFollowSystem == true) goto L9;
        int r22 = getBatteryLevel();
        int r1 = powerSaverLevel;
        if (r22 > r1) goto L24;
        if (r1 <= 0) goto L24;
        if (lastPowerSaverApplied == true) goto L22;
        lastPowerSaverApplied = true;
        onPowerSaverApplied(true);
    L22:
        return PRESET_POWER_SAVER;
    L24:
        if (lastPowerSaverApplied == false) goto L27;
        lastPowerSaverApplied = false;
        onPowerSaverApplied(false);
        goto L27
    L9:
        if (isSystemPowerSaveMode() == false) goto L24;
        if (lastPowerSaverApplied == true) goto L14;
        lastPowerSaverApplied = true;
        onPowerSaverApplied(true);
    L14:
        return PRESET_POWER_SAVER;
    L27:
        return value;
    }

    public static boolean isEnabled(int r2) {
        if (r2 == 64) goto L5;
    L7:
        int r0 = getValue();
        if ((preprocessFlag(r2) & r0) <= 0) goto L10;
        return true;
    L10:
        return false;
    L5:
        if (AndroidUtilities.isTablet() == false) goto L7;
        return true;
    }

    public static boolean isEnabledSetting(int r2) {
        if ((r2 & getValue(true)) <= 0) goto L5;
        return true;
    L5:
        return false;
    }

    public static boolean isPowerSaverApplied() {
        getValue(false);
        return lastPowerSaverApplied;
    }

    public static boolean isPowerSaverFollowSystem() {
        if (loaded == true) goto L6;
        loadPreference();
    L6:
        return powerSaverFollowSystem;
    }

    private static boolean isSystemPowerSaveMode() {
        PowerManager r0 = (PowerManager) ApplicationLoader.applicationContext.getSystemService("power");
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isPowerSaveMode() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static void loadPreference() {
        int r0 = PRESET_HIGH;
        int r1 = BATTERY_HIGH;
        if (SharedConfig.getDevicePerformanceClass() != 0) goto L6;
        r0 = PRESET_LOW;
        r1 = BATTERY_LOW;
    L8:
        SharedPreferences r2 = MessagesController.getGlobalMainSettings();
        if (r2.contains("lite_mode6") == false) goto L11;
    L64:
        int r5 = value;
        int r02 = r2.getInt("lite_mode6", r0);
        value = r02;
        if (loaded == false) goto L67;
        onFlagsUpdate(r5, r02);
    L67:
        powerSaverLevel = r2.getInt("lite_mode_battery_level", r1);
        powerSaverFollowSystem = r2.getBoolean("lite_mode_battery_follow_system", false);
        loaded = true;
        return;
    L11:
        if (r2.contains("lite_mode5") == false) goto L14;
        r0 = r2.getInt("lite_mode5", r0) & (-262145);
        r2.edit().putInt("lite_mode6", r0).apply();
        goto L64
    L14:
        if (r2.contains("lite_mode4") == false) goto L17;
        r0 = r2.getInt("lite_mode4", r0);
        r2.edit().putInt("lite_mode5", r0).apply();
        goto L64
    L17:
        if (r2.contains("lite_mode3") == false) goto L20;
        r0 = r2.getInt("lite_mode3", r0) | 131072;
        r2.edit().putInt("lite_mode5", r0).apply();
        goto L64
    L20:
        if (r2.contains("lite_mode2") == false) goto L23;
        r0 = r2.getInt("lite_mode2", r0) | 65536;
        r2.edit().putInt("lite_mode3", r0).apply();
        goto L64
    L23:
        if (r2.contains("lite_mode") == false) goto L28;
        r0 = r2.getInt("lite_mode", r0);
        if (r0 != 4095) goto L64;
        r0 = PRESET_HIGH;
        goto L64
    L28:
        if (r2.contains("light_mode") == false) goto L38;
        if (SharedConfig.getDevicePerformanceClass() != 0) goto L32;
        int r03 = 1;
    L34:
        if ((r2.getInt("light_mode", r03) & 1) <= 0) goto L36;
        r0 = PRESET_LOW;
        goto L38
    L36:
        r0 = PRESET_HIGH;
        goto L38
    L32:
        r03 = 0;
    L38:
        if (r2.contains("loopStickers") == false) goto L44;
        if (r2.getBoolean("loopStickers", true) == false) goto L42;
        r0 = r0 | 2;
        goto L44
    L42:
        r0 = r0 & (-3);
    L44:
        if (r2.contains("autoplay_video") == false) goto L53;
        if (r2.getBoolean("autoplay_video", true) == false) goto L48;
    L51:
        r0 = r0 | 1024;
        goto L53
    L48:
        if (r2.getBoolean("autoplay_video_liteforce", false) == true) goto L51;
        r0 = r0 & (-1025);
    L53:
        if (r2.contains("autoplay_gif") == false) goto L59;
        if (r2.getBoolean("autoplay_gif", true) == false) goto L57;
        r0 = r0 | 2048;
        goto L59
    L57:
        r0 = r0 & (-2049);
    L59:
        if (r2.contains("chatBlur") == false) goto L64;
        if (r2.getBoolean("chatBlur", true) == false) goto L63;
        r0 = r0 | 256;
        goto L64
    L63:
        r0 = r0 & (-257);
        goto L64
    L6:
        if (SharedConfig.getDevicePerformanceClass() != 1) goto L8;
        r0 = PRESET_MEDIUM;
        r1 = BATTERY_MEDIUM;
        goto L8
    }

    private static void onFlagsUpdate(int r0, int r1) {
        int r02 = (~r0) & r1;
        if ((r02 & FLAGS_ANIMATED_EMOJI) <= 0) goto L5;
        AnimatedEmojiDrawable.updateAll();
    L5:
        int r03 = r02 & 32;
        if (r03 <= 0) goto L8;
        SvgHelper.SvgDrawable.updateLiteValues();
    L8:
        if (r03 <= 0) goto L11;
        Theme.reloadWallpaper(true);
        return;
    }

    private static void onPowerSaverApplied(boolean r2) {
        if (r2 == false) goto L5;
        onFlagsUpdate(getValue(true), PRESET_POWER_SAVER);
    L7:
        if (onPowerSaverAppliedListeners == null) goto L10;
        AndroidUtilities.runOnUIThread(new LiteMode$$ExternalSyntheticLambda0(r2));
        return;
    L10:
        return;
    L5:
        onFlagsUpdate(PRESET_POWER_SAVER, getValue(true));
        goto L7
    }

    private static int preprocessFlag(int r1) {
        if ((r1 & FLAG_ANIMATED_EMOJI_KEYBOARD) <= 0) goto L10;
        int r12 = r1 & (-16389);
        if (UserConfig.hasPremiumOnAccounts() == false) goto L7;
        int r0 = 4;
    L8:
        r1 = r12 | r0;
        goto L10
    L7:
        r0 = 16384;
    L10:
        if ((r1 & FLAG_ANIMATED_EMOJI_REACTIONS) <= 0) goto L17;
        int r13 = r1 & (-8201);
        if (UserConfig.hasPremiumOnAccounts() == false) goto L14;
        int r02 = 8;
    L15:
        r1 = r13 | r02;
        goto L17
    L14:
        r02 = 8192;
    L17:
        if ((r1 & FLAG_ANIMATED_EMOJI_CHAT) <= 0) goto L24;
        int r14 = r1 & (-4113);
        if (UserConfig.hasPremiumOnAccounts() == false) goto L21;
        int r03 = 16;
    L23:
        return r14 | r03;
    L21:
        r03 = 4096;
        goto L23
    L24:
        return r1;
    }

    public static void removeOnPowerSaverAppliedListener(Utilities.Callback<Boolean> r1) {
        HashSet<Utilities.Callback<Boolean>> r0 = onPowerSaverAppliedListeners;
        if (r0 == null) goto L6;
        r0.remove(r1);
        return;
    }

    public static void savePreference() {
        MessagesController.getGlobalMainSettings().edit().putInt("lite_mode6", value).putInt("lite_mode_battery_level", powerSaverLevel).putBoolean("lite_mode_battery_follow_system", powerSaverFollowSystem).apply();
    }

    public static void setAllFlags(int r0) {
        value = r0;
        savePreference();
    }

    public static void setPowerSaverFollowSystem(boolean r0) {
        powerSaverFollowSystem = r0;
        savePreference();
        getValue(false);
    }

    public static void setPowerSaverLevel(int r2) {
        powerSaverLevel = MathUtils.clamp(r2, 0, 100);
        savePreference();
        getValue(false);
    }

    public static void toggleFlag(int r1) {
        toggleFlag(r1, !isEnabled(r1));
    }

    public static void toggleFlag(int r1, boolean r2) {
        if (r2 == false) goto L5;
        int r12 = r1 | getValue(true);
    L6:
        setAllFlags(r12);
        return;
    L5:
        int r22 = getValue(true);
        r12 = (~r1) & r22;
        goto L6
    }

    public static void updatePresets(TLRPC.TL_jsonObject r8) {
        int r1 = 0;
    L4:
        if (r1 >= r8.value.size()) goto L24;
        TLRPC.TL_jsonObjectValue r2 = r8.value.get(r1);
        if ("settings_mask".equals(r2.key) == false) goto L15;
        TLRPC.JSONValue r3 = r2.value;
        if ((r3 instanceof TLRPC.TL_jsonArray) == false) goto L15;
        ArrayList<TLRPC.JSONValue> r22 = ((TLRPC.TL_jsonArray) r3).value;
        PRESET_LOW = (int) ((TLRPC.TL_jsonNumber) r22.get(0)).value;     // Catch: Exception -> L12
        PRESET_MEDIUM = (int) ((TLRPC.TL_jsonNumber) r22.get(1)).value;     // Catch: Exception -> L12
        PRESET_HIGH = (int) ((TLRPC.TL_jsonNumber) r22.get(2)).value;     // Catch: Exception -> L12
    L23:
        r1 = r1 + 1;
    L12:
        e = move-exception;
        FileLog.e(e);
    L15:
        if ("battery_low".equals(r2.key) == false) goto L23;
        TLRPC.JSONValue r23 = r2.value;
        if ((r23 instanceof TLRPC.TL_jsonArray) == false) goto L23;
        ArrayList<TLRPC.JSONValue> r24 = ((TLRPC.TL_jsonArray) r23).value;
        BATTERY_LOW = (int) ((TLRPC.TL_jsonNumber) r24.get(0)).value;     // Catch: Exception -> L21
        BATTERY_MEDIUM = (int) ((TLRPC.TL_jsonNumber) r24.get(1)).value;     // Catch: Exception -> L21
        BATTERY_HIGH = (int) ((TLRPC.TL_jsonNumber) r24.get(2)).value;     // Catch: Exception -> L21
    L21:
        e = move-exception;
        FileLog.e(e);
        goto L23
    L24:
        loadPreference();
    }
}
