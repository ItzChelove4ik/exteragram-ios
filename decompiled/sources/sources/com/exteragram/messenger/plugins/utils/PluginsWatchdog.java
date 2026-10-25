package com.exteragram.messenger.plugins.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import com.exteragram.messenger.plugins.Plugin;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.pip.PipController;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.LaunchActivity;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001c\u001dB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010J\u0010\u0010\u0012\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u000e\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0018\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u001b\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;", _UrlKt.FRAGMENT_ENCODE_SET, "controller", "Lcom/exteragram/messenger/plugins/PluginsController;", "<init>", "(Lcom/exteragram/messenger/plugins/PluginsController;)V", "executingPlugins", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/lang/Thread;", "Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog$ExecutionInfo;", "frozenExecutions", "scheduledChecks", "Ljava/util/concurrent/ScheduledFuture;", "scheduler", "Ljava/util/concurrent/ScheduledExecutorService;", "start", _UrlKt.FRAGMENT_ENCODE_SET, "stop", "onPluginExecutionStarted", "pluginId", _UrlKt.FRAGMENT_ENCODE_SET, "onPluginExecutionFinished", "forceDisablePlugin", "activity", "Landroid/app/Activity;", "forceDeletePlugin", "disablePluginPref", "restartApp", "ExecutionInfo", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginsWatchdog.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginsWatchdog.kt\ncom/exteragram/messenger/plugins/utils/PluginsWatchdog\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,222:1\n2792#2,3:223\n2792#2,3:226\n41#3,12:229\n*S KotlinDebug\n*F\n+ 1 PluginsWatchdog.kt\ncom/exteragram/messenger/plugins/utils/PluginsWatchdog\n*L\n84#1:223,3\n127#1:226,3\n157#1:229,12\n*E\n"})
/* loaded from: classes.dex */
public final class PluginsWatchdog {
    public static final Companion Companion = null;
    private final PluginsController controller;
    private final ConcurrentHashMap<Thread, ExecutionInfo> executingPlugins;
    private final ConcurrentHashMap<Thread, ExecutionInfo> frozenExecutions;
    private final ConcurrentHashMap<Thread, ScheduledFuture<?>> scheduledChecks;
    private ScheduledExecutorService scheduler;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "showNotRespondingAlert", _UrlKt.FRAGMENT_ENCODE_SET, "plugin", "Lcom/exteragram/messenger/plugins/Plugin;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public static void $r8$lambda$YVUu_yybweI20Wq2RsPMfENiUXc(Plugin r0, Activity r1, DialogInterface r2, int r3) {
            if (r3 != 1) goto L5;
            PluginsController.Companion.getInstance().getWatchdog().forceDisablePlugin(r0.getId(), r1);
            return;
        L5:
            if (r3 == 2) goto L7;
            return;
        L7:
            PluginsController.Companion.getInstance().getWatchdog().forceDeletePlugin(r0.getId(), r1);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final void showNotRespondingAlert(Plugin r6) {
            BaseFragment r5 = LaunchActivity.getSafeLastFragment();
            if (r5 != null) goto L5;
            return;
        L5:
            Activity r0 = r5.getParentActivity();
            AlertDialog r52 = new AlertDialog.Builder(r0, r5.getResourceProvider()).setTitle(LocaleController.formatString(R.string.PluginIsNotRespondingAlert, new Object[]{r6.getName()})).setItems(new String[]{LocaleController.getString(R.string.WaitMore), LocaleController.getString(R.string.Disable), LocaleController.getString(R.string.Delete)}, new int[]{R.drawable.msg_recent, R.drawable.msg_block, R.drawable.msg_delete}, new PluginsWatchdog$Companion$$ExternalSyntheticLambda0(r6, r0)).create();
            r52.show();
            r52.setItemColor(r52.getItemsCount() - 1, Theme.getColor(Theme.key_text_RedBold), Theme.getColor(Theme.key_text_RedRegular));
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog$ExecutionInfo;", _UrlKt.FRAGMENT_ENCODE_SET, "pluginId", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;)V", "getPluginId", "()Ljava/lang/String;", "component1", "copy", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ExecutionInfo {
        private final String pluginId;

        public ExecutionInfo(String r1) {
            this.pluginId = r1;
        }

        public static /* synthetic */ ExecutionInfo copy$default(ExecutionInfo r0, String r1, int r2, Object r3) {
            if ((r2 & 1) == 0) goto L6;
            r1 = r0.pluginId;
        L6:
            return r0.copy(r1);
        }

        public final String component1() {
            return this.pluginId;
        }

        public final ExecutionInfo copy(String r1) {
            return new ExecutionInfo(r1);
        }

        public boolean equals(Object r4) {
            if (this != r4) goto L6;
            return true;
        L6:
            if ((r4 instanceof ExecutionInfo) == true) goto L9;
            return false;
        L9:
            if (Intrinsics.areEqual(this.pluginId, ((ExecutionInfo) r4).pluginId) == true) goto L11;
            return false;
        L11:
            return true;
        }

        public final String getPluginId() {
            return this.pluginId;
        }

        public int hashCode() {
            return this.pluginId.hashCode();
        }

        public String toString() {
            return "ExecutionInfo(pluginId=" + this.pluginId + ")";
        }
    }

    public static void $r8$lambda$UoMELmYGmxSMvupkn4u6yrELpNM(PluginsWatchdog r7, Thread r8, ExecutionInfo r9, String r10) {
        Ref.BooleanRef r5 = new Ref.BooleanRef();     // Catch: Exception -> L6
        r7.executingPlugins.computeIfPresent(r8, new PluginsWatchdog$$ExternalSyntheticLambda2(new PluginsWatchdog$$ExternalSyntheticLambda1(r9, r7, r8, r10, r5)));     // Catch: Exception -> L6
        if (r5.element == false) goto L11;
        NotificationCenter.getGlobalInstance().postNotificationNameOnUIThread(NotificationCenter.pluginIsNotResponding, new Object[0]);     // Catch: Exception -> L6
        return;
    L11:
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
    }

    public static ExecutionInfo $r8$lambda$WLajENZhHGT32H6z54wIMVmTszc(Function2 r0, Object r1, Object r2) {
        return (ExecutionInfo) r0.invoke(r1, r2);
    }

    public static ExecutionInfo $r8$lambda$WVisd4rB5sLtocqueDN9mCZdTTk(ExecutionInfo r0, PluginsWatchdog r1, Thread r2, String r3, Ref.BooleanRef r4, Thread r5, ExecutionInfo r6) {
        if (r6 != r0) goto L8;
        r1.frozenExecutions.put(r2, r0);
        Plugin r02 = r1.controller.getPlugins().get(r3);
        if (r02 == null) goto L8;
        if (r02.isNotResponding() == true) goto L8;
        r02.setNotResponding(true);
        r4.element = true;
    L8:
        return r6;
    }

    public static void $r8$lambda$fgbn8TOwtFd9GpkUps4U7wX0DBU(Activity r2) {
        if (r2 == null) goto L6;
        Intent r0 = r2.getPackageManager().getLaunchIntentForPackage(r2.getPackageName());
        r2.finishAffinity();
        if (r0 == null) goto L6;
        r2.startActivity(r0);
    L6:
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    static {
        Companion = new Companion(null);
    }

    public PluginsWatchdog(PluginsController r1) {
        this.controller = r1;
        this.executingPlugins = new ConcurrentHashMap();
        this.frozenExecutions = new ConcurrentHashMap();
        this.scheduledChecks = new ConcurrentHashMap();
    }

    private final void disablePluginPref(String r3) {
        SharedPreferences.Editor r2 = this.controller.getPreferences().edit();
        r2.putBoolean(PluginsController.PREF_PLUGIN_ENABLED_KEY_PREFIX + r3, false);
        r2.apply();
    }

    private final void restartApp(Activity r3) {
        AndroidUtilities.runOnUIThread(new PluginsWatchdog$$ExternalSyntheticLambda0(r3), 200);
    }

    @JvmStatic
    public static final void showNotRespondingAlert(Plugin r1) {
        Companion.showNotRespondingAlert(r1);
    }

    public final void forceDeletePlugin(String r5, Activity r6) {
        disablePluginPref(r5);
        PluginsController.Companion.setPluginPinned(r5, false);
        this.controller.cleanupPlugin(r5);
        PipController.INSTANCE.uninstallDependencies(r5);     // Catch: Exception -> L5
    L7:
        this.controller.clearPluginSettingsPreferences(r5, true);
        new File(this.controller.getPluginsDir(), r5 + ".py").delete();
        this.controller.getPlugins().remove(r5);
        this.controller.notifyPluginsChanged();
        restartApp(r6);
        return;
    L5:
        e = move-exception;
        FileLog.e("Failed to uninstall dependencies for frozen plugin " + r5, e);
        goto L7
    }

    public final void forceDisablePlugin(String r1, Activity r2) {
        disablePluginPref(r1);
        restartApp(r2);
    }

    public final void onPluginExecutionFinished(String r5) {
        Thread r0 = Thread.currentThread();
        ExecutionInfo r1 = this.executingPlugins.get(r0);
        if (r1 != null) goto L5;
        return;
    L5:
        if (Intrinsics.areEqual(r1.getPluginId(), r5) == true) goto L7;
        return;
    L7:
        if (this.executingPlugins.remove(r0, r1) == false) goto L32;
        ScheduledFuture<?> r52 = this.scheduledChecks.remove(r0);
        if (r52 == null) goto L11;
        r52.cancel(false);
    L11:
        ExecutionInfo r53 = this.frozenExecutions.remove(r0);
        if (r53 == null) goto L33;
        Collection<ExecutionInfo> r02 = this.frozenExecutions.values();
        if (r02.isEmpty() == true) goto L22;
        Iterator<T> r03 = r02.iterator();
    L18:
        if (r03.hasNext() == false) goto L22;
        if (Intrinsics.areEqual(((ExecutionInfo) r03.next()).getPluginId(), r53.getPluginId()) == false) goto L18;
        return;
    L22:
        Plugin r4 = this.controller.getPlugins().get(r53.getPluginId());
        if (r4 == null) goto L25;
        r4.setNotResponding(false);
    L25:
        NotificationCenter.getGlobalInstance().postNotificationNameOnUIThread(NotificationCenter.pluginIsNotResponding, new Object[0]);
        return;
    L33:
        return;
    }

    public final void onPluginExecutionStarted(String r8) {
        if (r8 == null) goto L37;
        Thread r0 = Thread.currentThread();
        ExecutionInfo r1 = new ExecutionInfo(r8);
        this.executingPlugins.put(r0, r1);
        ExecutionInfo r2 = this.frozenExecutions.remove(r0);
        if (r2 == null) goto L22;
        boolean r4 = Intrinsics.areEqual(r2.getPluginId(), r8);
        ConcurrentHashMap<Thread, ExecutionInfo> r5 = this.frozenExecutions;
        if (r4 == false) goto L9;
        r5.put(r0, r1);
        goto L22
    L9:
        Collection<ExecutionInfo> r42 = r5.values();
        if (r42.isEmpty() == true) goto L18;
        Iterator<T> r43 = r42.iterator();
    L14:
        if (r43.hasNext() == false) goto L18;
        if (Intrinsics.areEqual(((ExecutionInfo) r43.next()).getPluginId(), r2.getPluginId()) == false) goto L14;
    L18:
        Plugin r22 = this.controller.getPlugins().get(r2.getPluginId());
        if (r22 == null) goto L21;
        r22.setNotResponding(false);
    L21:
        NotificationCenter.getGlobalInstance().postNotificationNameOnUIThread(NotificationCenter.pluginIsNotResponding, new Object[0]);
    L22:
        ScheduledFuture<?> r23 = this.scheduledChecks.remove(r0);
        if (r23 == null) goto L25;
        r23.cancel(false);
    L25:
        ScheduledExecutorService r24 = this.scheduler;
        if (r24 != null) goto L32;
        return;
    L32:
        this.scheduledChecks.put(r0, r24.schedule(new PluginsWatchdog$$ExternalSyntheticLambda3(this, r0, r1, r8), 5, TimeUnit.SECONDS));     // Catch: RejectedExecutionException -> L30
        return;
    L30:
        this.executingPlugins.remove(r0, r1);
        return;
    }

    public final void start() {
        ScheduledExecutorService r0 = this.scheduler;
        if (r0 != null) goto L5;
    L7:
        ScheduledThreadPoolExecutor r02 = new ScheduledThreadPoolExecutor(1);
        r02.setRemoveOnCancelPolicy(true);
        this.scheduler = r02;
        return;
    L5:
        if (r0.isShutdown() == true) goto L7;
    }

    public final void stop() {
        Iterator<ScheduledFuture<?>> r0 = this.scheduledChecks.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().cancel(false);
        goto L4
    L6:
        this.scheduledChecks.clear();
        ScheduledExecutorService r02 = this.scheduler;
        if (r02 == null) goto L9;
        r02.shutdownNow();
    L9:
        this.scheduler = null;
        Iterator<ExecutionInfo> r03 = this.frozenExecutions.values().iterator();
    L11:
        if (r03.hasNext() == false) goto L15;
        ExecutionInfo r1 = r03.next();
        Plugin r12 = this.controller.getPlugins().get(r1.getPluginId());
        if (r12 == null) goto L11;
        r12.setNotResponding(false);
        goto L11
    L15:
        this.frozenExecutions.clear();
        this.executingPlugins.clear();
    }
}
