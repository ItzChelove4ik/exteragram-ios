package com.exteragram.messenger.adblock.backend;

import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.adblock.interop.AdBlock;
import com.exteragram.messenger.adblock.interop.NativeAdBlock;
import com.exteragram.messenger.utils.network.RemoteUtils;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DispatchQueue;

/* loaded from: classes.dex */
public abstract class AdBlockManager {
    private static final String[] FILTERS = null;
    private static boolean loading;
    private static DispatchQueue queue;
    private static final ArrayList<Runnable> readyCallbacks = null;

    public static /* synthetic */ void $r8$lambda$NX8OXkxyVjTNyOUDKguiPVa3dS8() {
        load(null);
    }

    /* renamed from: $r8$lambda$SRj9nQUD4lLiPK5Ln8H1-nPUIPI, reason: not valid java name */
    public static /* synthetic */ void m1006$r8$lambda$SRj9nQUD4lLiPK5Ln8H1nPUIPI() {
        load(null);
    }

    public static /* synthetic */ void $r8$lambda$VkMrvF4u0rpTiyBLPhtCni5XDeE(boolean r0, Runnable r1) {
        if (r0 == false) goto L5;
        load(r1);
        return;
    L5:
        readyCallbacks.clear();
        AdBlock.destroy();
    }

    public static /* synthetic */ void $r8$lambda$VvvmAH8sWhBT5SVzdcMZl989bxQ(ArrayList r3) {
        int r0 = r3.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Object r2 = r3.get(r1);
        r1 = r1 + 1;
        ((Runnable) r2).run();
        goto L3
    }

    /* renamed from: $r8$lambda$aZ6pyyx_-VLqumhb1lWfnqqAtkI, reason: not valid java name */
    public static /* synthetic */ void m1007$r8$lambda$aZ6pyyx_VLqumhb1lWfnqqAtkI(Boolean r1) {
        getQueue().postRunnable(new AdBlockManager$$ExternalSyntheticLambda6());
    }

    public static /* synthetic */ void $r8$lambda$mAUomQJn7qBCLzHdA6TkvE_lh94() {
        getQueue().postRunnable(new AdBlockManager$$ExternalSyntheticLambda4());
    }

    public static /* synthetic */ void $r8$lambda$q9EOL701yIVVnq6u5SQnHEOAri8() {
        build();
    }

    static {
        FILTERS = new String[]{"https://ublockorigin.github.io/uAssetsCDN/filters/filters.min.txt", "https://ublockorigin.github.io/uAssetsCDN/filters/badware.min.txt", "https://ublockorigin.github.io/uAssetsCDN/filters/privacy.min.txt", "https://ublockorigin.github.io/uAssetsCDN/filters/unbreak.min.txt", "https://ublockorigin.github.io/uAssetsCDN/filters/quick-fixes.min.txt", "https://filters.adtidy.org/extension/ublock/filters/11.txt", "https://filters.adtidy.org/extension/ublock/filters/2_without_easylist.txt", "https://cdn.jsdelivr.net/gh/uBlockOrigin/uAssetsCDN@main/thirdparties/easylist.txt", "https://cdn.jsdelivr.net/gh/uBlockOrigin/uAssetsCDN@main/thirdparties/easyprivacy.txt", "https://cdn.jsdelivr.net/gh/dimisa-RUAdList/RUAdListCDN@main/lists/ruadlist.ubo.min.txt"};
        readyCallbacks = new ArrayList();
    }

    private static void build() {
        loading = false;
        if (ExteraConfig.getEnableAdBlock() == false) goto L5;
        AdBlock.build();
    L5:
        ArrayList<Runnable> r0 = readyCallbacks;
        if (r0.isEmpty() == true) goto L13;
        ArrayList r1 = new ArrayList(r0);
        r0.clear();
        if (AdBlock.isReady() == false) goto L12;
        AndroidUtilities.runOnUIThread(new AdBlockManager$$ExternalSyntheticLambda5(r1));
        return;
    L12:
        return;
    }

    private static synchronized DispatchQueue getQueue() {
        monitor-enter(AdBlockManager.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (queue != null) goto L9;
        queue = new DispatchQueue("AdBlockManager");     // Catch: Throwable -> L7
    L9:
        DispatchQueue r1 = queue;     // Catch: Throwable -> L7
        monitor-exit(AdBlockManager.class);
        return r1;
    }

    public static void initialize() {
        if (ExteraConfig.getEnableAdBlock() == false) goto L6;
        getQueue().postRunnable(new AdBlockManager$$ExternalSyntheticLambda1());
        return;
    }

    public static boolean isActive() {
        if (ExteraConfig.getEnableAdBlock() == true) goto L5;
        return false;
    L5:
        if (AdBlock.isReady() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isAvailable() {
        if (RemoteUtils.getBooleanConfigValue("use_adblock", false).booleanValue() == true) goto L5;
    L8:
        return false;
    L5:
        if (NativeAdBlock.isLoadFailed() == true) goto L8;
        return true;
    }

    private static void load(Runnable r2) {
        if (ExteraConfig.getEnableAdBlock() == true) goto L6;
        return;
    L6:
        if (AdBlock.isReady() == false) goto L11;
        if (r2 == null) goto L29;
        AndroidUtilities.runOnUIThread(r2);
        return;
    L29:
        return;
    L11:
        if (RemoteUtils.getBooleanConfigValue("use_adblock", false).booleanValue() == true) goto L13;
        return;
    L13:
        if (NativeAdBlock.loadLibraries() == false) goto L32;
        if (r2 == null) goto L18;
        readyCallbacks.add(r2);
    L18:
        if (loading == true) goto L33;
        loading = true;
        ScriptletsManager r22 = ScriptletsManager.getInstance();
        if (r22.isDownloaded() == true) goto L23;
        r22.download(new AdBlockManager$$ExternalSyntheticLambda2());
    L23:
        SubscriptionsManager r23 = SubscriptionsManager.getInstance();
        if (r23.hasFilters() == false) goto L27;
        build();
        r23.update(FILTERS, null);
        return;
    L27:
        r23.update(FILTERS, new AdBlockManager$$ExternalSyntheticLambda3());
        return;
    L33:
        return;
    }

    public static void preload() {
        if (ExteraConfig.getEnableAdBlock() == false) goto L6;
        getQueue().postRunnable(new AdBlockManager$$ExternalSyntheticLambda0(), NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
        return;
    }

    public static void setEnabled(boolean r2, Runnable r3) {
        ExteraConfig.setEnableAdBlock(r2);
        getQueue().postRunnable(new AdBlockManager$$ExternalSyntheticLambda7(r2, r3));
    }
}
