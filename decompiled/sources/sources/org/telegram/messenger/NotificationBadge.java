package org.telegram.messenger;

import android.content.AsyncQueryHandler;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import java.io.Closeable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public class NotificationBadge {
    private static final List<Class<? extends Badger>> BADGERS = null;
    private static Badger badger;
    private static ComponentName componentName;
    private static boolean initied;

    public static class AdwHomeBadger implements Badger {
        public static final String CLASSNAME = "CNAME";
        public static final String COUNT = "COUNT";
        public static final String INTENT_UPDATE_COUNTER = "org.adw.launcher.counter.SEND";
        public static final String PACKAGENAME = "PNAME";

        public static /* synthetic */ void $r8$lambda$V5zkVH5GA4U5l5_brz14tFcF6bA(Intent r1) {
            ApplicationLoader.applicationContext.sendBroadcast(r1);
        }

        public AdwHomeBadger() {
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public void executeBadge(int r3) {
            Intent r2 = new Intent(INTENT_UPDATE_COUNTER);
            r2.putExtra(PACKAGENAME, NotificationBadge.m4781$$Nest$sfgetcomponentName().getPackageName());
            r2.putExtra(CLASSNAME, NotificationBadge.m4781$$Nest$sfgetcomponentName().getClassName());
            r2.putExtra(COUNT, r3);
            if (NotificationBadge.m4782$$Nest$smcanResolveBroadcast(r2) == false) goto L6;
            AndroidUtilities.runOnUIThread(new NotificationBadge$AdwHomeBadger$$ExternalSyntheticLambda0(r2));
            return;
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public List<String> getSupportLaunchers() {
            return Arrays.asList(new String[]{"org.adw.launcher", "org.adwfreak.launcher"});
        }
    }

    public static class ApexHomeBadger implements Badger {
        private static final String CLASS = "class";
        private static final String COUNT = "count";
        private static final String INTENT_UPDATE_COUNTER = "com.anddoes.launcher.COUNTER_CHANGED";
        private static final String PACKAGENAME = "package";

        public static /* synthetic */ void $r8$lambda$uyCxboysT_JsHR5EyVocfICJihs(Intent r1) {
            ApplicationLoader.applicationContext.sendBroadcast(r1);
        }

        public ApexHomeBadger() {
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public void executeBadge(int r3) {
            Intent r2 = new Intent(INTENT_UPDATE_COUNTER);
            r2.putExtra(PACKAGENAME, NotificationBadge.m4781$$Nest$sfgetcomponentName().getPackageName());
            r2.putExtra("count", r3);
            r2.putExtra(CLASS, NotificationBadge.m4781$$Nest$sfgetcomponentName().getClassName());
            if (NotificationBadge.m4782$$Nest$smcanResolveBroadcast(r2) == false) goto L6;
            AndroidUtilities.runOnUIThread(new NotificationBadge$ApexHomeBadger$$ExternalSyntheticLambda0(r2));
            return;
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public List<String> getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.anddoes.launcher"});
        }
    }

    public static class AsusHomeBadger implements Badger {
        private static final String INTENT_ACTION = "android.intent.action.BADGE_COUNT_UPDATE";
        private static final String INTENT_EXTRA_ACTIVITY_NAME = "badge_count_class_name";
        private static final String INTENT_EXTRA_BADGE_COUNT = "badge_count";
        private static final String INTENT_EXTRA_PACKAGENAME = "badge_count_package_name";

        public static /* synthetic */ void $r8$lambda$TxtwKPVd6RqH0dFKR_lwY2wzjf4(Intent r1) {
            ApplicationLoader.applicationContext.sendBroadcast(r1);
        }

        public AsusHomeBadger() {
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public void executeBadge(int r2) {
            Intent r1 = new Intent(INTENT_ACTION);
            r1.putExtra(INTENT_EXTRA_BADGE_COUNT, r2);
            r1.putExtra(INTENT_EXTRA_PACKAGENAME, NotificationBadge.m4781$$Nest$sfgetcomponentName().getPackageName());
            r1.putExtra(INTENT_EXTRA_ACTIVITY_NAME, NotificationBadge.m4781$$Nest$sfgetcomponentName().getClassName());
            r1.putExtra("badge_vip_count", 0);
            if (NotificationBadge.m4782$$Nest$smcanResolveBroadcast(r1) == false) goto L6;
            AndroidUtilities.runOnUIThread(new NotificationBadge$AsusHomeBadger$$ExternalSyntheticLambda0(r1));
            return;
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public List<String> getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.asus.launcher"});
        }
    }

    public interface Badger {
        void executeBadge(int r1);

        List<String> getSupportLaunchers();
    }

    public static class DefaultBadger implements Badger {
        private static final String INTENT_ACTION = "android.intent.action.BADGE_COUNT_UPDATE";
        private static final String INTENT_EXTRA_ACTIVITY_NAME = "badge_count_class_name";
        private static final String INTENT_EXTRA_BADGE_COUNT = "badge_count";
        private static final String INTENT_EXTRA_PACKAGENAME = "badge_count_package_name";

        public static /* synthetic */ void $r8$lambda$ji9g4Mah0Txoq_RpOAnB5SeLTN8(Intent r1) {
            ApplicationLoader.applicationContext.sendBroadcast(r1);     // Catch: Exception -> L4
            return;
        }

        public DefaultBadger() {
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public void executeBadge(int r2) {
            Intent r1 = new Intent(INTENT_ACTION);
            r1.putExtra(INTENT_EXTRA_BADGE_COUNT, r2);
            r1.putExtra(INTENT_EXTRA_PACKAGENAME, NotificationBadge.m4781$$Nest$sfgetcomponentName().getPackageName());
            r1.putExtra(INTENT_EXTRA_ACTIVITY_NAME, NotificationBadge.m4781$$Nest$sfgetcomponentName().getClassName());
            AndroidUtilities.runOnUIThread(new NotificationBadge$DefaultBadger$$ExternalSyntheticLambda0(r1));
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public List<String> getSupportLaunchers() {
            return Arrays.asList(new String[]{"fr.neamar.kiss", "com.quaap.launchtime", "com.quaap.launchtime_official"});
        }
    }

    public static class HuaweiHomeBadger implements Badger {
        public static /* synthetic */ void $r8$lambda$xnk9Ep8QRBi4ET5i5Wp29K48zdk(Bundle r4) {
            ApplicationLoader.applicationContext.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, r4);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        public HuaweiHomeBadger() {
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public void executeBadge(int r3) {
            Bundle r2 = new Bundle();
            r2.putString("package", ApplicationLoader.applicationContext.getPackageName());
            r2.putString("class", NotificationBadge.m4781$$Nest$sfgetcomponentName().getClassName());
            r2.putInt("badgenumber", r3);
            AndroidUtilities.runOnUIThread(new NotificationBadge$HuaweiHomeBadger$$ExternalSyntheticLambda0(r2));
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public List<String> getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.huawei.android.launcher"});
        }
    }

    public static class NewHtcHomeBadger implements Badger {
        public static final String COUNT = "count";
        public static final String EXTRA_COMPONENT = "com.htc.launcher.extra.COMPONENT";
        public static final String EXTRA_COUNT = "com.htc.launcher.extra.COUNT";
        public static final String INTENT_SET_NOTIFICATION = "com.htc.launcher.action.SET_NOTIFICATION";
        public static final String INTENT_UPDATE_SHORTCUT = "com.htc.launcher.action.UPDATE_SHORTCUT";
        public static final String PACKAGENAME = "packagename";

        /* renamed from: $r8$lambda$LNOrzcbHoddZ-HaXpWj71zfhB0w, reason: not valid java name */
        public static /* synthetic */ void m4783$r8$lambda$LNOrzcbHoddZHaXpWj71zfhB0w(Intent r1, Intent r2) {
            ApplicationLoader.applicationContext.sendBroadcast(r1);
            ApplicationLoader.applicationContext.sendBroadcast(r2);
        }

        public NewHtcHomeBadger() {
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public void executeBadge(int r4) {
            Intent r3 = new Intent(INTENT_SET_NOTIFICATION);
            r3.putExtra(EXTRA_COMPONENT, NotificationBadge.m4781$$Nest$sfgetcomponentName().flattenToShortString());
            r3.putExtra(EXTRA_COUNT, r4);
            Intent r0 = new Intent(INTENT_UPDATE_SHORTCUT);
            r0.putExtra(PACKAGENAME, NotificationBadge.m4781$$Nest$sfgetcomponentName().getPackageName());
            r0.putExtra(COUNT, r4);
            if (NotificationBadge.m4782$$Nest$smcanResolveBroadcast(r3) == false) goto L5;
        L8:
            AndroidUtilities.runOnUIThread(new NotificationBadge$NewHtcHomeBadger$$ExternalSyntheticLambda0(r3, r0));
            return;
        L5:
            if (NotificationBadge.m4782$$Nest$smcanResolveBroadcast(r0) == true) goto L8;
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public List<String> getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.htc.launcher"});
        }
    }

    public static class NovaHomeBadger implements Badger {
        private static final String CONTENT_URI = "content://com.teslacoilsw.notifier/unread_count";
        private static final String COUNT = "count";
        private static final String TAG = "tag";

        public NovaHomeBadger() {
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public void executeBadge(int r3) {
            ContentValues r2 = new ContentValues();
            r2.put(TAG, NotificationBadge.m4781$$Nest$sfgetcomponentName().getPackageName() + "/" + NotificationBadge.m4781$$Nest$sfgetcomponentName().getClassName());
            r2.put("count", Integer.valueOf(r3));
            ApplicationLoader.applicationContext.getContentResolver().insert(Uri.parse(CONTENT_URI), r2);
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public List<String> getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.teslacoilsw.launcher"});
        }
    }

    public static class OPPOHomeBader implements Badger {
        private static final String INTENT_ACTION = "com.oppo.unsettledevent";
        private static final String INTENT_EXTRA_BADGEUPGRADE_COUNT = "app_badge_count";
        private static final String INTENT_EXTRA_BADGE_COUNT = "number";
        private static final String INTENT_EXTRA_BADGE_UPGRADENUMBER = "upgradeNumber";
        private static final String INTENT_EXTRA_PACKAGENAME = "pakeageName";
        private static final String PROVIDER_CONTENT_URI = "content://com.android.badge/badge";
        private int mCurrentTotalCount;

        public OPPOHomeBader() {
            this.mCurrentTotalCount = -1;
        }

        private void executeBadgeByContentProvider(int r4) {
            Bundle r3 = new Bundle();     // Catch: Throwable -> L4
            r3.putInt(INTENT_EXTRA_BADGEUPGRADE_COUNT, r4);     // Catch: Throwable -> L4
            ApplicationLoader.applicationContext.getContentResolver().call(Uri.parse(PROVIDER_CONTENT_URI), "setAppBadgeCount", null, r3);     // Catch: Throwable -> L4
            return;
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public void executeBadge(int r2) {
            if (this.mCurrentTotalCount != r2) goto L5;
            return;
        L5:
            this.mCurrentTotalCount = r2;
            executeBadgeByContentProvider(r2);
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public List<String> getSupportLaunchers() {
            return Collections.singletonList("com.oppo.launcher");
        }
    }

    public static class SamsungHomeBadger implements Badger {
        private static final String[] CONTENT_PROJECTION = null;
        private static final String CONTENT_URI = "content://com.sec.badge/apps?notify=true";
        private static DefaultBadger defaultBadger;

        static {
            CONTENT_PROJECTION = new String[]{"_id", "class"};
        }

        public SamsungHomeBadger() {
        }

        private ContentValues getContentValues(ComponentName r2, int r3, boolean r4) {
            ContentValues r1 = new ContentValues();
            if (r4 == false) goto L5;
            r1.put("package", r2.getPackageName());
            r1.put("class", r2.getClassName());
        L5:
            r1.put("badgecount", Integer.valueOf(r3));
            return r1;
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public void executeBadge(int r11) {
            if (defaultBadger != null) goto L5;
            defaultBadger = new DefaultBadger();     // Catch: Exception -> L23
        L5:
            defaultBadger.executeBadge(r11);     // Catch: Exception -> L23
        L6:
            Uri r2 = Uri.parse(CONTENT_URI);
            ContentResolver r1 = ApplicationLoader.applicationContext.getContentResolver();
            Cursor r7 = null;
            r7 = r1.query(r2, CONTENT_PROJECTION, "package=?", new String[]{NotificationBadge.m4781$$Nest$sfgetcomponentName().getPackageName()}, null);     // Catch: Throwable -> L15
            if (r7 == null) goto L19;
            String r0 = NotificationBadge.m4781$$Nest$sfgetcomponentName().getClassName();     // Catch: Throwable -> L15
            boolean r4 = false;
        L11:
            if (r7.moveToNext() == false) goto L17;
            r1.update(r2, getContentValues(NotificationBadge.m4781$$Nest$sfgetcomponentName(), r11, false), "_id=?", new String[]{String.valueOf(r7.getInt(0))});     // Catch: Throwable -> L15
            if (r0.equals(r7.getString(r7.getColumnIndex("class"))) == false) goto L11;
            r4 = true;
            goto L11
        L17:
            if (r4 == true) goto L19;
            r1.insert(r2, getContentValues(NotificationBadge.m4781$$Nest$sfgetcomponentName(), r11, true));     // Catch: Throwable -> L15
        L19:
            NotificationBadge.close(r7);
            return;
        L15:
            th = move-exception;
            NotificationBadge.close(r7);
            throw th;
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public List<String> getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.sec.android.app.launcher", "com.sec.android.app.twlauncher"});
        }
    }

    public static class SonyHomeBadger implements Badger {
        private static final String INTENT_ACTION = "com.sonyericsson.home.action.UPDATE_BADGE";
        private static final String INTENT_EXTRA_ACTIVITY_NAME = "com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME";
        private static final String INTENT_EXTRA_MESSAGE = "com.sonyericsson.home.intent.extra.badge.MESSAGE";
        private static final String INTENT_EXTRA_PACKAGE_NAME = "com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME";
        private static final String INTENT_EXTRA_SHOW_MESSAGE = "com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE";
        private static final String PROVIDER_COLUMNS_ACTIVITY_NAME = "activity_name";
        private static final String PROVIDER_COLUMNS_BADGE_COUNT = "badge_count";
        private static final String PROVIDER_COLUMNS_PACKAGE_NAME = "package_name";
        private static final String PROVIDER_CONTENT_URI = "content://com.sonymobile.home.resourceprovider/badge";
        private static final String SONY_HOME_PROVIDER_NAME = "com.sonymobile.home.resourceprovider";
        private static AsyncQueryHandler mQueryHandler;
        private final Uri BADGE_CONTENT_URI;

        /* renamed from: org.telegram.messenger.NotificationBadge$SonyHomeBadger$1, reason: invalid class name */
        /* loaded from: classes5.dex */
        public class AnonymousClass1 extends AsyncQueryHandler {
            final /* synthetic */ SonyHomeBadger this$0;

            public AnonymousClass1(SonyHomeBadger r1, ContentResolver r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.content.AsyncQueryHandler, android.os.Handler
            public void handleMessage(Message r1) {
                super.handleMessage(r1);     // Catch: Throwable -> L4
                return;
            }
        }

        /* renamed from: $r8$lambda$H_yhqBO15iA4akNgr5hWbs4v-s0, reason: not valid java name */
        public static /* synthetic */ void m4784$r8$lambda$H_yhqBO15iA4akNgr5hWbs4vs0(Intent r1) {
            ApplicationLoader.applicationContext.sendBroadcast(r1);
        }

        public SonyHomeBadger() {
            this.BADGE_CONTENT_URI = Uri.parse(PROVIDER_CONTENT_URI);
        }

        private static void executeBadgeByBroadcast(int r3) {
            Intent r0 = new Intent(INTENT_ACTION);
            r0.putExtra(INTENT_EXTRA_PACKAGE_NAME, NotificationBadge.m4781$$Nest$sfgetcomponentName().getPackageName());
            r0.putExtra(INTENT_EXTRA_ACTIVITY_NAME, NotificationBadge.m4781$$Nest$sfgetcomponentName().getClassName());
            r0.putExtra(INTENT_EXTRA_MESSAGE, String.valueOf(r3));
            if (r3 <= 0) goto L5;
            boolean r32 = true;
        L6:
            r0.putExtra(INTENT_EXTRA_SHOW_MESSAGE, r32);
            AndroidUtilities.runOnUIThread(new NotificationBadge$SonyHomeBadger$$ExternalSyntheticLambda0(r0));
            return;
        L5:
            r32 = false;
            goto L6
        }

        private void executeBadgeByContentProvider(int r3) {
            if (r3 >= 0) goto L5;
            return;
        L5:
            if (mQueryHandler != null) goto L7;
            mQueryHandler = new AnonymousClass1(this, ApplicationLoader.applicationContext.getApplicationContext().getContentResolver());
        L7:
            insertBadgeAsync(r3, NotificationBadge.m4781$$Nest$sfgetcomponentName().getPackageName(), NotificationBadge.m4781$$Nest$sfgetcomponentName().getClassName());
        }

        private void insertBadgeAsync(int r3, String r4, String r5) {
            ContentValues r0 = new ContentValues();
            r0.put(PROVIDER_COLUMNS_BADGE_COUNT, Integer.valueOf(r3));
            r0.put(PROVIDER_COLUMNS_PACKAGE_NAME, r4);
            r0.put(PROVIDER_COLUMNS_ACTIVITY_NAME, r5);
            mQueryHandler.startInsert(0, null, this.BADGE_CONTENT_URI, r0);
        }

        private static boolean sonyBadgeContentProviderExists() {
            if (ApplicationLoader.applicationContext.getPackageManager().resolveContentProvider(SONY_HOME_PROVIDER_NAME, 0) == null) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public void executeBadge(int r2) {
            if (sonyBadgeContentProviderExists() == false) goto L6;
            executeBadgeByContentProvider(r2);
            return;
        L6:
            executeBadgeByBroadcast(r2);
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public List<String> getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.sonyericsson.home", "com.sonymobile.home"});
        }
    }

    public static class VivoHomeBadger implements Badger {
        public VivoHomeBadger() {
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public void executeBadge(int r3) {
            Intent r2 = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
            r2.setPackage("com.vivo.launcher");
            r2.putExtra("packageName", ApplicationLoader.applicationContext.getPackageName());
            r2.putExtra("className", NotificationBadge.m4781$$Nest$sfgetcomponentName().getClassName());
            r2.putExtra("notificationNum", r3);
            ApplicationLoader.applicationContext.sendBroadcast(r2);
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public List<String> getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.vivo.launcher"});
        }
    }

    public static class XiaomiHomeBadger implements Badger {
        public static final String EXTRA_UPDATE_APP_COMPONENT_NAME = "android.intent.extra.update_application_component_name";
        public static final String EXTRA_UPDATE_APP_MSG_TEXT = "android.intent.extra.update_application_message_text";
        public static final String INTENT_ACTION = "android.intent.action.APPLICATION_MESSAGE_UPDATE";

        /* renamed from: org.telegram.messenger.NotificationBadge$XiaomiHomeBadger$1, reason: invalid class name */
        /* loaded from: classes5.dex */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ XiaomiHomeBadger this$0;
            final /* synthetic */ Intent val$localIntent;

            public AnonymousClass1(XiaomiHomeBadger r1, Intent r2) {
                this.this$0 = r1;
                this.val$localIntent = r2;
            }

            @Override // java.lang.Runnable
            public void run() {
                ApplicationLoader.applicationContext.sendBroadcast(this.val$localIntent);
            }
        }

        public XiaomiHomeBadger() {
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public void executeBadge(int r5) {
            Object r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            Object r1 = Class.forName("android.app.MiuiNotification").newInstance();     // Catch: Throwable -> L9
            Field r2 = r1.getClass().getDeclaredField("messageCount");     // Catch: Throwable -> L9
            r2.setAccessible(true);     // Catch: Throwable -> L9
            if (r5 != 0) goto L6;
            Object r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        L7:
            r2.set(r1, String.valueOf(r3));     // Catch: Throwable -> L9
            return;
        L6:
            r3 = Integer.valueOf(r5);     // Catch: Throwable -> L9
        L9:
            Intent r12 = new Intent(INTENT_ACTION);
            r12.putExtra(EXTRA_UPDATE_APP_COMPONENT_NAME, NotificationBadge.m4781$$Nest$sfgetcomponentName().getPackageName() + "/" + NotificationBadge.m4781$$Nest$sfgetcomponentName().getClassName());
            if (r5 == 0) goto L13;
            r0 = Integer.valueOf(r5);
        L13:
            r12.putExtra(EXTRA_UPDATE_APP_MSG_TEXT, String.valueOf(r0));
            if (NotificationBadge.m4782$$Nest$smcanResolveBroadcast(r12) == false) goto L19;
            AndroidUtilities.runOnUIThread(new AnonymousClass1(this, r12));
            return;
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public List<String> getSupportLaunchers() {
            return Arrays.asList(new String[]{"com.miui.miuilite", "com.miui.home", "com.miui.miuihome", "com.miui.miuihome2", "com.miui.mihome", "com.miui.mihome2"});
        }
    }

    public static class ZukHomeBadger implements Badger {
        private final Uri CONTENT_URI;

        public static /* synthetic */ void $r8$lambda$vSYHECd6HD8IJWNLclrRFJqMfro(ZukHomeBadger r0, Bundle r1) {
            r0.lambda$executeBadge$0(r1);
        }

        public ZukHomeBadger() {
            this.CONTENT_URI = Uri.parse("content://com.android.badge/badge");
        }

        private /* synthetic */ void lambda$executeBadge$0(Bundle r4) {
            ApplicationLoader.applicationContext.getContentResolver().call(this.CONTENT_URI, "setAppBadgeCount", null, r4);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public void executeBadge(int r3) {
            Bundle r0 = new Bundle();
            r0.putInt("app_badge_count", r3);
            AndroidUtilities.runOnUIThread(new NotificationBadge$ZukHomeBadger$$ExternalSyntheticLambda0(this, r0));
        }

        @Override // org.telegram.messenger.NotificationBadge.Badger
        public List<String> getSupportLaunchers() {
            return Collections.singletonList("com.zui.launcher");
        }
    }

    /* renamed from: -$$Nest$sfgetcomponentName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ComponentName m4781$$Nest$sfgetcomponentName() {
        return componentName;
    }

    /* renamed from: -$$Nest$smcanResolveBroadcast, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m4782$$Nest$smcanResolveBroadcast(Intent r0) {
        return canResolveBroadcast(r0);
    }

    static {
        LinkedList r0 = new LinkedList();
        BADGERS = r0;
        r0.add(AdwHomeBadger.class);
        r0.add(ApexHomeBadger.class);
        r0.add(NewHtcHomeBadger.class);
        r0.add(NovaHomeBadger.class);
        r0.add(SonyHomeBadger.class);
        r0.add(XiaomiHomeBadger.class);
        r0.add(AsusHomeBadger.class);
        r0.add(HuaweiHomeBadger.class);
        r0.add(OPPOHomeBader.class);
        r0.add(SamsungHomeBadger.class);
        r0.add(ZukHomeBadger.class);
        r0.add(VivoHomeBadger.class);
    }

    public NotificationBadge() {
    }

    public static boolean applyCount(int r3) {
    L13:
        return false;
    L4:
        if (badger == null) goto L6;
    L8:
        Badger r1 = badger;     // Catch: Throwable -> L13
        if (r1 != null) goto L11;
        return false;
    L11:
        r1.executeBadge(r3);     // Catch: Throwable -> L13
        return true;
    L6:
        if (initied == true) goto L8;
        initBadger();     // Catch: Throwable -> L13
        initied = true;     // Catch: Throwable -> L13
        goto L8
    }

    private static boolean canResolveBroadcast(Intent r2) {
        List<ResolveInfo> r22 = ApplicationLoader.applicationContext.getPackageManager().queryBroadcastReceivers(r2, 0);
        if (r22 != null) goto L5;
    L8:
        return false;
    L5:
        if (r22.size() <= 0) goto L8;
        return true;
    }

    public static void close(Cursor r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (r1.isClosed() == true) goto L8;
        r1.close();
        return;
    }

    public static void closeQuietly(Closeable r0) {
        if (r0 == null) goto L8;
        r0.close();     // Catch: Throwable -> L5
        return;
    L9:
        return;
    }

    private static boolean initBadger() {
        Context r0 = ApplicationLoader.applicationContext;
        Intent r1 = r0.getPackageManager().getLaunchIntentForPackage(r0.getPackageName());
        int r2 = 0;
        if (r1 != null) goto L5;
        return false;
    L5:
        componentName = r1.getComponent();
        Intent r12 = new Intent("android.intent.action.MAIN");
        r12.addCategory("android.intent.category.HOME");
        ResolveInfo r3 = r0.getPackageManager().resolveActivity(r12, 65536);
        if (r3 == null) goto L21;
        String r32 = r3.activityInfo.packageName;
        Iterator<Class<? extends Badger>> r7 = BADGERS.iterator();
    L9:
        if (r7.hasNext() == false) goto L19;
        Badger r8 = r7.next().newInstance();     // Catch: Exception -> L13
    L14:
        if (r8 == null) goto L9;
        if (r8.getSupportLaunchers().contains(r32) == false) goto L9;
        badger = r8;
    L13:
        r8 = null;
    L19:
        if (badger == null) goto L21;
        return true;
    L21:
        List<ResolveInfo> r02 = r0.getPackageManager().queryIntentActivities(r12, 65536);
        if (r02 == null) goto L41;
    L24:
        if (r2 >= r02.size()) goto L41;
        String r13 = r02.get(r2).activityInfo.packageName;
        Iterator<Class<? extends Badger>> r33 = BADGERS.iterator();
    L27:
        if (r33.hasNext() == false) goto L37;
        Badger r4 = r33.next().newInstance();     // Catch: Exception -> L31
    L32:
        if (r4 == null) goto L27;
        if (r4.getSupportLaunchers().contains(r13) == false) goto L27;
        badger = r4;
    L31:
        r4 = null;
    L37:
        if (badger != null) goto L41;
        r2 = r2 + 1;
    L41:
        if (badger != null) goto L55;
        String r03 = Build.MANUFACTURER;
        if (r03.equalsIgnoreCase("Xiaomi") == false) goto L46;
        badger = new XiaomiHomeBadger();
        goto L55
    L46:
        if (r03.equalsIgnoreCase("ZUK") == false) goto L49;
        badger = new ZukHomeBadger();
        goto L55
    L49:
        if (r03.equalsIgnoreCase("OPPO") == false) goto L52;
        badger = new OPPOHomeBader();
        goto L55
    L52:
        if (r03.equalsIgnoreCase("VIVO") == false) goto L54;
        badger = new VivoHomeBadger();
        goto L55
    L54:
        badger = new DefaultBadger();
    L55:
        return true;
    }
}
