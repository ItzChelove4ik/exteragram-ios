package com.exteragram.messenger.debug;

import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import androidx.collection.LongSparseArray;
import com.exteragram.messenger.api.ApiController;
import com.exteragram.messenger.api.db.ExteraDatabase;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.exteragram.messenger.utils.network.RemoteUtils;
import java.io.File;
import java.util.ArrayList;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001\u001dB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J(\u0010\u0006\u001a\u00020\u00072\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014J0\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0014J\b\u0010\u0017\u001a\u00020\nH\u0002J\b\u0010\u0018\u001a\u00020\nH\u0002J\b\u0010\u0019\u001a\u00020\nH\u0002J\b\u0010\u001a\u001a\u00020\u0005H\u0002J\b\u0010\u001b\u001a\u00020\u0005H\u0002J\b\u0010\u001c\u001a\u00020\u0007H\u0002¨\u0006\u001e"}, d2 = {"Lcom/exteragram/messenger/debug/DebugActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "<init>", "()V", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "fillItems", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "Lkotlin/collections/ArrayList;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "Landroid/view/View;", "position", _UrlKt.FRAGMENT_ENCODE_SET, "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "createFreezeThresholdSliderItem", "createLoadThresholdSliderItem", "createHeapLimitSliderItem", "getHeapUsageValue", "getIpConfigOverrideValue", "showIpConfigOverrideDialog", "DebugItem", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDebugActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DebugActivity.kt\ncom/exteragram/messenger/debug/DebugActivity\n+ 2 SparseArray.kt\nandroidx/core/util/SparseArrayKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,352:1\n25#2:353\n1#3:354\n*S KotlinDebug\n*F\n+ 1 DebugActivity.kt\ncom/exteragram/messenger/debug/DebugActivity\n*L\n176#1:353\n*E\n"})
/* loaded from: classes4.dex */
public final class DebugActivity extends BasePreferencesActivity {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0018\n\u0002\u0010\b\n\u0000\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0019\u001a\u00020\u001aj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018¨\u0006\u001b"}, d2 = {"Lcom/exteragram/messenger/debug/DebugActivity$DebugItem;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "DEBUG_CAMERA_METRICS", "FORCE_COMPACT_SAVED_MUSIC", "DISABLE_API_REQUESTS", "DISABLE_CHAT_FADE_WALLPAPER_BLEND", "CHAT_FADE_USE_WHITE_BACKGROUND", "FORCE_REFRESH_REMOTE_CONFIG", "CLEAR_DB", "CLEAR_TRANSLATIONS", "SET_IPCONFIG_OVERRIDE", "CLEAR_IPCONFIG_OVERRIDE", "HEAP_MONITOR", "DUMP_HEAP", "SHARE_HEAP_DUMP", "DELETE_HEAP_DUMPS", "LOAD_MONITOR", "CAPTURE_LOAD_REPORT", "SHARE_LOAD_REPORT", "DELETE_LOAD_REPORTS", "FREEZE_MONITOR", "SHARE_FREEZE_REPORT", "DELETE_FREEZE_REPORTS", "getId", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum DebugItem extends Enum<DebugItem> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ DebugItem[] $VALUES = null;
        public static final DebugItem CAPTURE_LOAD_REPORT = null;
        public static final DebugItem CHAT_FADE_USE_WHITE_BACKGROUND = null;
        public static final DebugItem CLEAR_DB = null;
        public static final DebugItem CLEAR_IPCONFIG_OVERRIDE = null;
        public static final DebugItem CLEAR_TRANSLATIONS = null;
        public static final DebugItem DEBUG_CAMERA_METRICS = null;
        public static final DebugItem DELETE_FREEZE_REPORTS = null;
        public static final DebugItem DELETE_HEAP_DUMPS = null;
        public static final DebugItem DELETE_LOAD_REPORTS = null;
        public static final DebugItem DISABLE_API_REQUESTS = null;
        public static final DebugItem DISABLE_CHAT_FADE_WALLPAPER_BLEND = null;
        public static final DebugItem DUMP_HEAP = null;
        public static final DebugItem FORCE_COMPACT_SAVED_MUSIC = null;
        public static final DebugItem FORCE_REFRESH_REMOTE_CONFIG = null;
        public static final DebugItem FREEZE_MONITOR = null;
        public static final DebugItem HEAP_MONITOR = null;
        public static final DebugItem LOAD_MONITOR = null;
        public static final DebugItem SET_IPCONFIG_OVERRIDE = null;
        public static final DebugItem SHARE_FREEZE_REPORT = null;
        public static final DebugItem SHARE_HEAP_DUMP = null;
        public static final DebugItem SHARE_LOAD_REPORT = null;

        private static final /* synthetic */ DebugItem[] $values() {
            return new DebugItem[]{DEBUG_CAMERA_METRICS, FORCE_COMPACT_SAVED_MUSIC, DISABLE_API_REQUESTS, DISABLE_CHAT_FADE_WALLPAPER_BLEND, CHAT_FADE_USE_WHITE_BACKGROUND, FORCE_REFRESH_REMOTE_CONFIG, CLEAR_DB, CLEAR_TRANSLATIONS, SET_IPCONFIG_OVERRIDE, CLEAR_IPCONFIG_OVERRIDE, HEAP_MONITOR, DUMP_HEAP, SHARE_HEAP_DUMP, DELETE_HEAP_DUMPS, LOAD_MONITOR, CAPTURE_LOAD_REPORT, SHARE_LOAD_REPORT, DELETE_LOAD_REPORTS, FREEZE_MONITOR, SHARE_FREEZE_REPORT, DELETE_FREEZE_REPORTS};
        }

        static {
            DEBUG_CAMERA_METRICS = new DebugItem("DEBUG_CAMERA_METRICS", 0);
            FORCE_COMPACT_SAVED_MUSIC = new DebugItem("FORCE_COMPACT_SAVED_MUSIC", 1);
            DISABLE_API_REQUESTS = new DebugItem("DISABLE_API_REQUESTS", 2);
            DISABLE_CHAT_FADE_WALLPAPER_BLEND = new DebugItem("DISABLE_CHAT_FADE_WALLPAPER_BLEND", 3);
            CHAT_FADE_USE_WHITE_BACKGROUND = new DebugItem("CHAT_FADE_USE_WHITE_BACKGROUND", 4);
            FORCE_REFRESH_REMOTE_CONFIG = new DebugItem("FORCE_REFRESH_REMOTE_CONFIG", 5);
            CLEAR_DB = new DebugItem("CLEAR_DB", 6);
            CLEAR_TRANSLATIONS = new DebugItem("CLEAR_TRANSLATIONS", 7);
            SET_IPCONFIG_OVERRIDE = new DebugItem("SET_IPCONFIG_OVERRIDE", 8);
            CLEAR_IPCONFIG_OVERRIDE = new DebugItem("CLEAR_IPCONFIG_OVERRIDE", 9);
            HEAP_MONITOR = new DebugItem("HEAP_MONITOR", 10);
            DUMP_HEAP = new DebugItem("DUMP_HEAP", 11);
            SHARE_HEAP_DUMP = new DebugItem("SHARE_HEAP_DUMP", 12);
            DELETE_HEAP_DUMPS = new DebugItem("DELETE_HEAP_DUMPS", 13);
            LOAD_MONITOR = new DebugItem("LOAD_MONITOR", 14);
            CAPTURE_LOAD_REPORT = new DebugItem("CAPTURE_LOAD_REPORT", 15);
            SHARE_LOAD_REPORT = new DebugItem("SHARE_LOAD_REPORT", 16);
            DELETE_LOAD_REPORTS = new DebugItem("DELETE_LOAD_REPORTS", 17);
            FREEZE_MONITOR = new DebugItem("FREEZE_MONITOR", 18);
            SHARE_FREEZE_REPORT = new DebugItem("SHARE_FREEZE_REPORT", 19);
            DELETE_FREEZE_REPORTS = new DebugItem("DELETE_FREEZE_REPORTS", 20);
            DebugItem[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        DebugItem(String r1, int r2) {
        }

        public static EnumEntries<DebugItem> getEntries() {
            return $ENTRIES;
        }

        public static DebugItem valueOf(String r1) {
            return (DebugItem) Enum.valueOf(DebugItem.class, r1);
        }

        public static DebugItem[] values() {
            return (DebugItem[]) $VALUES.clone();
        }

        public final int getId() {
            return ordinal() + 1;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[DebugItem.values().length];
            r0[DebugItem.DEBUG_CAMERA_METRICS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L26
        L75:
            r0[DebugItem.FORCE_COMPACT_SAVED_MUSIC.ordinal()] = 2;     // Catch: NoSuchFieldError -> L27
        L87:
            r0[DebugItem.DISABLE_API_REQUESTS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L28
        L55:
            r0[DebugItem.DISABLE_CHAT_FADE_WALLPAPER_BLEND.ordinal()] = 4;     // Catch: NoSuchFieldError -> L29
        L59:
            r0[DebugItem.CHAT_FADE_USE_WHITE_BACKGROUND.ordinal()] = 5;     // Catch: NoSuchFieldError -> L30
        L69:
            r0[DebugItem.FORCE_REFRESH_REMOTE_CONFIG.ordinal()] = 6;     // Catch: NoSuchFieldError -> L31
        L81:
            r0[DebugItem.CLEAR_DB.ordinal()] = 7;     // Catch: NoSuchFieldError -> L32
        L63:
            r0[DebugItem.CLEAR_TRANSLATIONS.ordinal()] = 8;     // Catch: NoSuchFieldError -> L33
        L77:
            r0[DebugItem.SET_IPCONFIG_OVERRIDE.ordinal()] = 9;     // Catch: NoSuchFieldError -> L34
        L79:
            r0[DebugItem.CLEAR_IPCONFIG_OVERRIDE.ordinal()] = 10;     // Catch: NoSuchFieldError -> L35
        L49:
            r0[DebugItem.HEAP_MONITOR.ordinal()] = 11;     // Catch: NoSuchFieldError -> L36
        L73:
            r0[DebugItem.DUMP_HEAP.ordinal()] = 12;     // Catch: NoSuchFieldError -> L37
        L85:
            r0[DebugItem.SHARE_HEAP_DUMP.ordinal()] = 13;     // Catch: NoSuchFieldError -> L38
        L47:
            r0[DebugItem.DELETE_HEAP_DUMPS.ordinal()] = 14;     // Catch: NoSuchFieldError -> L39
        L61:
            r0[DebugItem.LOAD_MONITOR.ordinal()] = 15;     // Catch: NoSuchFieldError -> L40
        L83:
            r0[DebugItem.CAPTURE_LOAD_REPORT.ordinal()] = 16;     // Catch: NoSuchFieldError -> L41
        L53:
            r0[DebugItem.SHARE_LOAD_REPORT.ordinal()] = 17;     // Catch: NoSuchFieldError -> L42
        L57:
            r0[DebugItem.DELETE_LOAD_REPORTS.ordinal()] = 18;     // Catch: NoSuchFieldError -> L43
        L71:
            r0[DebugItem.FREEZE_MONITOR.ordinal()] = 19;     // Catch: NoSuchFieldError -> L44
        L51:
            r0[DebugItem.SHARE_FREEZE_REPORT.ordinal()] = 20;     // Catch: NoSuchFieldError -> L45
        L67:
            r0[DebugItem.DELETE_FREEZE_REPORTS.ordinal()] = 21;     // Catch: NoSuchFieldError -> L46
        L24:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static Unit $r8$lambda$0H08GdubpMnhY7z1VPaMUMp0YVg(DebugActivity r2) {
        r2.getListView$TMessagesProj().adapter.update(true);
        BulletinFactory.of(r2).createSimpleBulletin(R.raw.contact_check, "Heap dumps deleted.").show();
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$2gZKtkRetst5P45VLoPMSHCuyhk(DebugActivity r2) {
        BulletinFactory.of(r2).createSimpleBulletin(R.raw.contact_check, "Translation cache cleared.").show();
    }

    public static void $r8$lambda$3HuiuI9fIVgRDzaMKeItB0b4lQU(int r1, Integer r2) {
        HeapMonitor.INSTANCE.setLimitMb(r2.intValue() * r1);
    }

    public static void $r8$lambda$3ftFdNOmn0AoZ3uOqD7uoYEEzXE(DebugActivity r2) {
        ExteraDatabase.Companion.getInstance().clearAllTables();
        ApiController.resetSyncState$default(null, 1, null);
        AndroidUtilities.runOnUIThread(new DebugActivity$$ExternalSyntheticLambda19(r2));
    }

    public static void $r8$lambda$77RamS_C6OiBWYNEOLpxH5fXQak(boolean r0) {
        DebugConfig.setDisableChatFadeWallpaperBlend(r0);
    }

    public static void $r8$lambda$7adC3wPZzincW442S_BS0QlhFJo(boolean r1) {
        LoadMonitor.INSTANCE.setEnabled(r1);
    }

    public static CharSequence $r8$lambda$AZJ401Rohj7ho11R6f40saRWxB4(int r0, Integer r1) {
        return (r1.intValue() * r0) + " ms";
    }

    /* renamed from: $r8$lambda$CbguHwmSr_eAdbsmez-XuoYkLaE, reason: not valid java name */
    public static CharSequence m1234$r8$lambda$CbguHwmSr_eAdbsmezXuoYkLaE(int r0, Integer r1) {
        return (r1.intValue() * r0) + " MB";
    }

    /* renamed from: $r8$lambda$DwC3d-sDDK3RMjtCUOUet7rjvWk, reason: not valid java name */
    public static void m1235$r8$lambda$DwC3dsDDK3RMjtCUOUet7rjvWk(boolean r1) {
        FreezeMonitor.INSTANCE.setEnabled(r1);
    }

    public static Unit $r8$lambda$FXqHAInsOSCxaiN3tolJfNNz8Vg(DebugActivity r2) {
        r2.getListView$TMessagesProj().adapter.update(true);
        BulletinFactory.of(r2).createSimpleBulletin(R.raw.contact_check, "Freeze reports deleted.").show();
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$M3FuCoLT6hNuzwgZD4llqu1--yE, reason: not valid java name */
    public static void m1236$r8$lambda$M3FuCoLT6hNuzwgZD4llqu1yE(Integer r1) {
        LoadMonitor.INSTANCE.setCpuPercent(r1.intValue());
    }

    public static Unit $r8$lambda$SJdEHBZnL9NoA7X68vORHRou62M(DebugActivity r2) {
        r2.getListView$TMessagesProj().adapter.update(true);
        BulletinFactory.of(r2).createSimpleBulletin(R.raw.contact_check, "Load reports deleted.").show();
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$TSIsEP8Zu81ieOsxL1x8oUKcIAA(EditTextBoldCursor r1, DebugActivity r2, AlertDialog r3, int r4) {
        Editable r12 = r1.getText();
        if (r12 == null) goto L7;
        String r13 = r12.toString();
        if (r13 == null) goto L7;
        String r14 = StringsKt.trim(r13).toString();
    L8:
        if (r14 != null) goto L11;
        r14 = _UrlKt.FRAGMENT_ENCODE_SET;
    L11:
        if (TextUtils.isEmpty(r14) == false) goto L14;
        ConnectionsManager.setDebugDnsConfigOverride(null);
        r2.getListView$TMessagesProj().adapter.update(true);
        BulletinFactory.of(r2).createSimpleBulletin(R.raw.contact_check, "ipconfigv3 override cleared.").show();
        return;
    L14:
        boolean r15 = ConnectionsManager.setAndApplyDebugDnsConfigOverride(r2.currentAccount, r14);
        r2.getListView$TMessagesProj().adapter.update(true);
        BulletinFactory r22 = BulletinFactory.of(r2);
        if (r15 == false) goto L17;
        int r32 = R.raw.contact_check;
    L18:
        if (r15 == false) goto L20;
        String r16 = "ipconfigv3 override saved and submitted.";
    L21:
        r22.createSimpleBulletin(r32, r16).show();
        return;
    L20:
        r16 = "Failed to decode ipconfigv3 override.";
        goto L21
    L17:
        r32 = R.raw.error;
    L7:
        r14 = null;
        goto L8
    }

    public static void $r8$lambda$V9rk6H6byQOV8r82VudFyLqtxZc(boolean r1) {
        HeapMonitor.INSTANCE.setEnabled(r1);
    }

    public static void $r8$lambda$W9lQE8eAEF3_4jJ_BLykbIqcH1c(DebugActivity r2) {
        BulletinFactory.of(r2).createSimpleBulletin(R.raw.contact_check, "Successfully cleared all tables.").show();
    }

    public static void $r8$lambda$e2nrzEVKUQt9LORey3r4IIWe8dU(boolean r0) {
        DebugConfig.setForceCompactSavedMusic(r0);
    }

    public static CharSequence $r8$lambda$fwK_7Z5MlqQ9cVB6ltUWJjyzrWE(Integer r1) {
        return r1 + "% of a core";
    }

    public static void $r8$lambda$hHOCITIPCaVcH_OtOkujnw_jtRI(boolean r0) {
        DebugConfig.setChatFadeUseWhiteBackground(r0);
    }

    /* renamed from: $r8$lambda$lzo-wUdGdWliwSXzF7c5jQegn1E, reason: not valid java name */
    public static void m1237$r8$lambda$lzowUdGdWliwSXzF7c5jQegn1E(int r1, Integer r2) {
        FreezeMonitor.INSTANCE.setThresholdMs(r2.intValue() * r1);
    }

    /* renamed from: $r8$lambda$oRUDN5OsGz-ENNFOvogpUjB8-Hk, reason: not valid java name */
    public static void m1238$r8$lambda$oRUDN5OsGzENNFOvogpUjB8Hk(boolean r0) {
        DebugConfig.setDebugCameraMetrics(r0);
    }

    public static void $r8$lambda$w76dGtnD41ADWvGSvDkImgYRL_M(boolean r0) {
        DebugConfig.setDisableApiRequests(r0);
    }

    public DebugActivity() {
    }

    private final UItem createFreezeThresholdSliderItem() {
        return UItem.asIntSlideView(1, 2, FreezeMonitor.INSTANCE.getThresholdMs() / 100, 20, new DebugActivity$$ExternalSyntheticLambda2(100), new DebugActivity$$ExternalSyntheticLambda3(100));
    }

    private final UItem createHeapLimitSliderItem() {
        HeapMonitor r8 = HeapMonitor.INSTANCE;
        return UItem.asIntSlideView(1, 4, r8.getLimitMb() / 16, r8.getMaxLimitMb() / 16, new DebugActivity$$ExternalSyntheticLambda0(16), new DebugActivity$$ExternalSyntheticLambda1(16));
    }

    private final UItem createLoadThresholdSliderItem() {
        return UItem.asIntSlideView(1, 1, LoadMonitor.INSTANCE.getCpuPercent(), 50, new DebugActivity$$ExternalSyntheticLambda4(), new DebugActivity$$ExternalSyntheticLambda5());
    }

    private final String getHeapUsageValue() {
        return AndroidUtilities.formatFileSize(HeapMonitor.INSTANCE.getUsedBytes(), true, false) + " / " + AndroidUtilities.formatFileSize(Runtime.getRuntime().maxMemory(), true, false);
    }

    private final String getIpConfigOverrideValue() {
        String r1 = ConnectionsManager.getDebugDnsConfigOverride();
        if (r1 != null) goto L5;
        return "Not set";
    L5:
        if (StringsKt.isBlank(r1) == false) goto L8;
        return "Not set";
    L8:
        return r1.length() + " chars";
    }

    private final void showIpConfigOverrideDialog() {
        Activity r0 = getParentActivity();
        if (r0 != null) goto L5;
        return;
    L5:
        EditTextBoldCursor r1 = new EditTextBoldCursor(r0);
        r1.lineYFix = true;
        r1.setTextSize(1, 18.0f);
        r1.setText(ConnectionsManager.getDebugDnsConfigOverride());
        r1.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourceProvider));
        r1.setHintColor(Theme.getColor(Theme.key_dialogTextHint, this.resourceProvider));
        r1.setHintText("Base64 from Firebase Remote Config");
        r1.setGravity(8388659);
        r1.setMinLines(6);
        r1.setMaxLines(10);
        r1.setInputType(655361);
        int r2 = Theme.key_dialogInputFieldActivated;
        r1.setCursorColor(Theme.getColor(r2, this.resourceProvider));
        r1.setLineColors(Theme.getColor(Theme.key_dialogInputField, this.resourceProvider), Theme.getColor(r2, this.resourceProvider), Theme.getColor(Theme.key_text_RedRegular, this.resourceProvider));
        r1.setBackground(null);
        int r5 = 0;
        r1.setPadding(0, AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f));
        Editable r3 = r1.getText();
        if (r3 == null) goto L8;
        r5 = r3.length();
    L8:
        r1.setSelection(r5);
        FrameLayout r32 = new FrameLayout(r0);
        r32.addView(r1, LayoutHelper.createFrame(-1, -2.0f, 8388659, 24.0f, 12.0f, 24.0f, 0.0f));
        showDialog(new AlertDialog.Builder(r0).setTitle("ipconfigv3 override").setView(r32).setPositiveButton("Apply", new DebugActivity$$ExternalSyntheticLambda20(r1, this)).setNegativeButton("Cancel", null).create());
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r7, UniversalAdapter r8) {
        r7.add(UItem.asCheck(DebugItem.DEBUG_CAMERA_METRICS.getId(), "Metrics in InstantCameraView").setChecked(DebugConfig.getDebugCameraMetrics()));
        r7.add(UItem.asCheck(DebugItem.FORCE_COMPACT_SAVED_MUSIC.getId(), "Compact Saved Music view").setChecked(DebugConfig.getForceCompactSavedMusic()));
        r7.add(UItem.asCheck(DebugItem.DISABLE_API_REQUESTS.getId(), "Disable exteraGram API requests").setChecked(DebugConfig.getDisableApiRequests()));
        r7.add(UItem.asCheck(DebugItem.DISABLE_CHAT_FADE_WALLPAPER_BLEND.getId(), "Disable chat fade wallpaper blend").setChecked(DebugConfig.getDisableChatFadeWallpaperBlend()));
        r7.add(UItem.asCheck(DebugItem.CHAT_FADE_USE_WHITE_BACKGROUND.getId(), "Use windowBackgroundWhite for chat fade").setChecked(DebugConfig.getChatFadeUseWhiteBackground()));
        r7.add(UItem.asShadow());
        r7.add(UItem.asButton(DebugItem.FORCE_REFRESH_REMOTE_CONFIG.getId(), "Refresh Remote Config"));
        r7.add(UItem.asButton(DebugItem.CLEAR_DB.getId(), "Clear exteraDatabase"));
        r7.add(UItem.asButton(DebugItem.CLEAR_TRANSLATIONS.getId(), "Clear translations cache"));
        r7.add(UItem.asShadow());
        r7.add(UItem.asButton(DebugItem.SET_IPCONFIG_OVERRIDE.getId(), "Set ipconfigv3 override", getIpConfigOverrideValue()));
        r7.add(UItem.asButton(DebugItem.CLEAR_IPCONFIG_OVERRIDE.getId(), "Clear ipconfigv3 override"));
        r7.add(UItem.asShadow());
        r7.add(UItem.asCheck(DebugItem.HEAP_MONITOR.getId(), "Heap limit monitor").setChecked(DebugConfig.getHeapMonitorEnabled()));
        if (DebugConfig.getHeapMonitorEnabled() == false) goto L5;
        r7.add(createHeapLimitSliderItem());
    L5:
        r7.add(UItem.asButton(DebugItem.DUMP_HEAP.getId(), "Dump & share heap", getHeapUsageValue()));
        HeapMonitor r82 = HeapMonitor.INSTANCE;
        File r0 = r82.getLastDump();
        if (r0 == null) goto L8;
        r7.add(UItem.asButton(DebugItem.SHARE_HEAP_DUMP.getId(), "Share last heap dump", AndroidUtilities.formatFileSize(r0.length())));
    L8:
        long r02 = r82.getDumpsSize();
        if (r02 <= 0) goto L11;
        r7.add(UItem.asButton(DebugItem.DELETE_HEAP_DUMPS.getId(), "Delete heap dumps", AndroidUtilities.formatFileSize(r02)));
    L11:
        r7.add(UItem.asShadow());
        r7.add(UItem.asCheck(DebugItem.LOAD_MONITOR.getId(), "Load monitor").setChecked(DebugConfig.getLoadMonitorEnabled()));
        if (DebugConfig.getLoadMonitorEnabled() == false) goto L14;
        r7.add(createLoadThresholdSliderItem());
    L14:
        int r83 = DebugItem.CAPTURE_LOAD_REPORT.getId();
        LoadMonitor r03 = LoadMonitor.INSTANCE;
        r7.add(UItem.asButton(r83, "Capture & share load report", r03.getSummary()));
        File r84 = r03.getLastReport();
        if (r84 == null) goto L17;
        r7.add(UItem.asButton(DebugItem.SHARE_LOAD_REPORT.getId(), "Share last load report", AndroidUtilities.formatFileSize(r84.length())));
    L17:
        long r04 = r03.getReportsSize();
        if (r04 <= 0) goto L20;
        r7.add(UItem.asButton(DebugItem.DELETE_LOAD_REPORTS.getId(), "Delete load reports", AndroidUtilities.formatFileSize(r04)));
    L20:
        r7.add(UItem.asShadow());
        r7.add(UItem.asCheck(DebugItem.FREEZE_MONITOR.getId(), "Freeze monitor").setChecked(DebugConfig.getFreezeMonitorEnabled()));
        if (DebugConfig.getFreezeMonitorEnabled() == false) goto L23;
        r7.add(createFreezeThresholdSliderItem());
    L23:
        FreezeMonitor r6 = FreezeMonitor.INSTANCE;
        File r85 = r6.getReport();
        if (r85 == null) goto L26;
        r7.add(UItem.asButton(DebugItem.SHARE_FREEZE_REPORT.getId(), "Share freeze report", AndroidUtilities.formatFileSize(r85.length())));
    L26:
        long r05 = r6.getReportsSize();
        if (r05 <= 0) goto L29;
        r7.add(UItem.asButton(DebugItem.DELETE_FREEZE_REPORTS.getId(), "Delete freeze reports", AndroidUtilities.formatFileSize(r05)));
    L29:
        r7.add(UItem.asShadow());
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return "Debug";
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r5, View r6, int r7, float r8, float r9) {
        int r62 = r5.id;
        if (r62 > 0) goto L5;
        return;
    L5:
        if (r62 <= DebugItem.getEntries().size()) goto L8;
        return;
    L8:
        switch(WhenMappings.$EnumSwitchMapping$0[DebugItem.getEntries().get(r5.id - 1).ordinal()]) {
            case 1: goto L82;
            case 2: goto L80;
            case 3: goto L78;
            case 4: goto L76;
            case 5: goto L74;
            case 6: goto L72;
            case 7: goto L70;
            case 8: goto L47;
            case 9: goto L45;
            case 10: goto L43;
            case 11: goto L41;
            case 12: goto L37;
            case 13: goto L33;
            case 14: goto L31;
            case 15: goto L29;
            case 16: goto L25;
            case 17: goto L21;
            case 18: goto L19;
            case 19: goto L17;
            case 20: goto L13;
            case 21: goto L11;
            default: goto L9;
        };
    L9:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return;
    L11:
        FreezeMonitor.INSTANCE.deleteReports(new DebugActivity$$ExternalSyntheticLambda9(this));
        return;
    L13:
        Activity r4 = getParentActivity();
        if (r4 == null) goto L90;
        FreezeMonitor.INSTANCE.shareReport(r4);
        return;
    L90:
        return;
    L17:
        toggleBooleanSettingAndRefresh$TMessagesProj(r5, new DebugActivity$$ExternalSyntheticLambda8());
        return;
    L19:
        LoadMonitor.INSTANCE.deleteReports(new DebugActivity$$ExternalSyntheticLambda7(this));
        return;
    L21:
        Activity r42 = getParentActivity();
        if (r42 == null) goto L91;
        LoadMonitor.INSTANCE.shareLastReport(r42);
        return;
    L91:
        return;
    L25:
        Activity r43 = getParentActivity();
        if (r43 == null) goto L92;
        LoadMonitor.INSTANCE.captureAndShare(r43);
        return;
    L92:
        return;
    L29:
        toggleBooleanSettingAndRefresh$TMessagesProj(r5, new DebugActivity$$ExternalSyntheticLambda18());
        return;
    L31:
        HeapMonitor.INSTANCE.deleteDumps(new DebugActivity$$ExternalSyntheticLambda17(this));
        return;
    L33:
        Activity r44 = getParentActivity();
        if (r44 == null) goto L93;
        HeapMonitor.INSTANCE.shareLastDump(r44);
        return;
    L93:
        return;
    L37:
        Activity r52 = getParentActivity();
        if (r52 == null) goto L94;
        HeapMonitor.INSTANCE.dumpAndShare(r52, this.resourceProvider);
        return;
    L94:
        return;
    L41:
        toggleBooleanSettingAndRefresh$TMessagesProj(r5, new DebugActivity$$ExternalSyntheticLambda16());
        return;
    L43:
        ConnectionsManager.setDebugDnsConfigOverride(null);
        getListView$TMessagesProj().adapter.update(true);
        BulletinFactory.of(this).createSimpleBulletin(R.raw.contact_check, "ipconfigv3 override cleared.").show();
        return;
    L45:
        showIpConfigOverrideDialog();
        return;
    L47:
        getMessagesController().getTranslateController().clearTranslationCache();
        LongSparseArray<ArrayList<MessageObject>> r53 = getMessagesController().dialogMessage;
        int r63 = 0;
        if (r53 == null) goto L58;
        int r72 = r53.size();
        int r82 = 0;
    L50:
        if (r82 >= r72) goto L58;
        ArrayList<MessageObject> r92 = r53.valueAt(r82);
        if (r92 == null) goto L57;
        int r0 = r92.size();
        int r1 = 0;
    L55:
        if (r1 >= r0) goto L57;
        getMessagesController().getTranslateController().clearMessageTranslationState(r92.get(r1));
        r1 = r1 + 1;
    L57:
        r82 = r82 + 1;
    L58:
        SparseArray<MessageObject> r54 = getMessagesController().dialogMessagesByIds;
        if (r54 == null) goto L63;
        int r73 = r54.size();
        int r83 = 0;
    L61:
        if (r83 >= r73) goto L63;
        getMessagesController().getTranslateController().clearMessageTranslationState(r54.valueAt(r83));
        r83 = r83 + 1;
    L63:
        LongSparseArray<MessageObject> r55 = getMessagesController().dialogMessagesByRandomIds;
        if (r55 == null) goto L68;
        int r74 = r55.size();
    L66:
        if (r63 >= r74) goto L68;
        getMessagesController().getTranslateController().clearMessageTranslationState(r55.valueAt(r63));
        r63 = r63 + 1;
    L68:
        AndroidUtilities.runOnUIThread(new DebugActivity$$ExternalSyntheticLambda15(this));
        return;
    L70:
        Utilities.globalQueue.postRunnable(new DebugActivity$$ExternalSyntheticLambda14(this));
        return;
    L72:
        RemoteUtils.forceRefresh();
        return;
    L74:
        toggleBooleanSettingAndRefresh$TMessagesProj(r5, new DebugActivity$$ExternalSyntheticLambda13());
        return;
    L76:
        toggleBooleanSettingAndRefresh$TMessagesProj(r5, new DebugActivity$$ExternalSyntheticLambda12());
        return;
    L78:
        toggleBooleanSettingAndRefresh$TMessagesProj(r5, new DebugActivity$$ExternalSyntheticLambda11());
        return;
    L80:
        toggleBooleanSettingAndRefresh$TMessagesProj(r5, new DebugActivity$$ExternalSyntheticLambda10());
        return;
    L82:
        toggleBooleanSettingAndRefresh$TMessagesProj(r5, new DebugActivity$$ExternalSyntheticLambda6());
    }
}
