package androidx.core.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutInfoCompatSaver;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.util.Preconditions;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public abstract class ShortcutManagerCompat {
    private static volatile List<Object> sShortcutInfoChangeListeners;
    private static volatile ShortcutInfoCompatSaver<?> sShortcutInfoCompatSaver;

    /* renamed from: androidx.core.content.pm.ShortcutManagerCompat$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass1 extends BroadcastReceiver {
        final /* synthetic */ IntentSender val$callback;

        public AnonymousClass1(IntentSender r1) {
            this.val$callback = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r7, Intent r8) {
            this.val$callback.sendIntent(r7, 0, null, null, null);     // Catch: IntentSender.SendIntentException -> L4
            return;
        }
    }

    /* loaded from: classes3.dex */
    public static class Api25Impl {
        public static String getShortcutInfoWithLowestRank(List<ShortcutInfo> r5) {
            Iterator<ShortcutInfo> r52 = r5.iterator();
            int r0 = -1;
            String r1 = null;
        L4:
            if (r52.hasNext() == false) goto L8;
            ShortcutInfo r2 = r52.next();
            if (r2.getRank() <= r0) goto L4;
            String r02 = r2.getId();
            r1 = r02;
            r0 = r2.getRank();
            goto L4
        L8:
            return r1;
        }
    }

    static {
    }

    public static boolean addDynamicShortcuts(Context r4, List<ShortcutInfoCompat> r5) {
        List<ShortcutInfoCompat> r52 = removeShortcutsExcludedFromSurface(r5, 1);
        int r1 = Build.VERSION.SDK_INT;
        if (r1 > 29) goto L6;
        convertUriIconsToBitmapIcons(r4, r52);
    L6:
        if (r1 < 25) goto L15;
        ArrayList r12 = new ArrayList();
        Iterator<ShortcutInfoCompat> r2 = r52.iterator();
    L9:
        if (r2.hasNext() == false) goto L12;
        r12.add(r2.next().toShortcutInfo());
        goto L9
    L12:
        if (ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(r4.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).addDynamicShortcuts(r12) == true) goto L15;
        return false;
    L15:
        getShortcutInfoSaverInstance(r4).addShortcuts(r52);
        Iterator<Object> r42 = getShortcutInfoListeners(r4).iterator();
        if (r42.hasNext() == true) goto L18;
        return true;
    L18:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42.next());
        throw null;
    }

    public static boolean convertUriIconToBitmapIcon(Context r6, ShortcutInfoCompat r7) {
        IconCompat r0 = r7.mIcon;
        if (r0 != null) goto L5;
        return false;
    L5:
        int r2 = r0.mType;
        if (r2 != 6) goto L8;
    L10:
        InputStream r62 = r0.getUriInputStream(r6);
        if (r62 != null) goto L13;
        return false;
    L13:
        Bitmap r63 = BitmapFactory.decodeStream(r62);
        if (r63 != null) goto L16;
        return false;
    L16:
        if (r2 != 6) goto L18;
        IconCompat r64 = IconCompat.createWithAdaptiveBitmap(r63);
    L19:
        r7.mIcon = r64;
        return true;
    L18:
        r64 = IconCompat.createWithBitmap(r63);
        goto L19
    L8:
        if (r2 == 4) goto L10;
        return true;
    }

    public static void convertUriIconsToBitmapIcons(Context r5, List<ShortcutInfoCompat> r6) {
        ArrayList r0 = new ArrayList(r6);
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L7;
        Object r3 = r0.get(r2);
        r2 = r2 + 1;
        ShortcutInfoCompat r32 = (ShortcutInfoCompat) r3;
        if (convertUriIconToBitmapIcon(r5, r32) == true) goto L3;
        r6.remove(r32);
        goto L3
    }

    public static List<ShortcutInfoCompat> getDynamicShortcuts(Context r4) {
        if (Build.VERSION.SDK_INT < 25) goto L13;
        List<ShortcutInfo> r0 = ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(r4.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).getDynamicShortcuts();
        ArrayList r1 = new ArrayList(r0.size());
        Iterator<ShortcutInfo> r02 = r0.iterator();
    L6:
        if (r02.hasNext() == false) goto L8;
        r1.add(new ShortcutInfoCompat.Builder(r4, ShortcutInfoCompat$$ExternalSyntheticApiModelOutline2.m(r02.next())).build());
        goto L6
    L8:
        return r1;
    L13:
        return getShortcutInfoSaverInstance(r4).getShortcuts();
    L12:
        return new ArrayList();
    }

    public static int getMaxShortcutCountPerActivity(Context r2) {
        Preconditions.checkNotNull(r2);
        if (Build.VERSION.SDK_INT >= 25) goto L5;
        return 5;
    L5:
        return ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(r2.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).getMaxShortcutCountPerActivity();
    }

    private static String getShortcutInfoCompatWithLowestRank(List<ShortcutInfoCompat> r5) {
        Iterator<ShortcutInfoCompat> r52 = r5.iterator();
        int r0 = -1;
        String r1 = null;
    L4:
        if (r52.hasNext() == false) goto L8;
        ShortcutInfoCompat r2 = r52.next();
        if (r2.getRank() <= r0) goto L4;
        String r02 = r2.getId();
        r1 = r02;
        r0 = r2.getRank();
        goto L4
    L8:
        return r1;
    }

    private static List<Object> getShortcutInfoListeners(Context r5) {
        if (sShortcutInfoChangeListeners != null) goto L22;
        ArrayList r0 = new ArrayList();
        PackageManager r1 = r5.getPackageManager();
        Intent r2 = new Intent("androidx.core.content.pm.SHORTCUT_LISTENER");
        r2.setPackage(r5.getPackageName());
        Iterator<ResolveInfo> r12 = r1.queryIntentActivities(r2, 128).iterator();
    L6:
        if (r12.hasNext() == false) goto L19;
        ActivityInfo r22 = r12.next().activityInfo;
        if (r22 == null) goto L6;
        Bundle r23 = r22.metaData;
        if (r23 == null) goto L6;
        String r24 = r23.getString("androidx.core.content.pm.shortcut_listener_impl");
        if (r24 == null) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Class.forName(r24, false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{r5}));     // Catch: Exception -> L23
        r0.add(null);     // Catch: Exception -> L23
        goto L6
    L19:
        if (sShortcutInfoChangeListeners != null) goto L22;
        sShortcutInfoChangeListeners = r0;
    L22:
        return sShortcutInfoChangeListeners;
    }

    private static ShortcutInfoCompatSaver<?> getShortcutInfoSaverInstance(Context r3) {
        if (sShortcutInfoCompatSaver != null) goto L9;
        sShortcutInfoCompatSaver = (ShortcutInfoCompatSaver) Class.forName("androidx.sharetarget.ShortcutInfoCompatSaverImpl", false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{r3});     // Catch: Exception -> L10
    L6:
        if (sShortcutInfoCompatSaver != null) goto L9;
        sShortcutInfoCompatSaver = new ShortcutInfoCompatSaver.NoopImpl();
    L9:
        return sShortcutInfoCompatSaver;
    }

    public static List<ShortcutInfoCompat> getShortcuts(Context r3, int r4) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 30) goto L7;
        return ShortcutInfoCompat.fromShortcuts(r3, ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(r3.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).getShortcuts(r4));
    L7:
        if (r0 < 25) goto L20;
        ShortcutManager r02 = ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(r3.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m()));
        ArrayList r1 = new ArrayList();
        if ((r4 & 1) == 0) goto L12;
        r1.addAll(r02.getManifestShortcuts());
    L12:
        if ((r4 & 2) == 0) goto L15;
        r1.addAll(r02.getDynamicShortcuts());
    L15:
        if ((r4 & 4) == 0) goto L18;
        r1.addAll(r02.getPinnedShortcuts());
    L18:
        return ShortcutInfoCompat.fromShortcuts(r3, r1);
    L20:
        if ((r4 & 2) == 0) goto L24;
        return getShortcutInfoSaverInstance(r3).getShortcuts();
    L24:
        return Collections.EMPTY_LIST;
    }

    public static boolean isRequestPinShortcutSupported(Context r4) {
        if (Build.VERSION.SDK_INT < 26) goto L7;
        return ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(r4.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).isRequestPinShortcutSupported();
    L7:
        if (ContextCompat.checkSelfPermission(r4, "com.android.launcher.permission.INSTALL_SHORTCUT") == 0) goto L9;
        return false;
    L9:
        Iterator<ResolveInfo> r42 = r4.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 0).iterator();
    L11:
        if (r42.hasNext() == false) goto L18;
        String r1 = r42.next().activityInfo.permission;
        if (TextUtils.isEmpty(r1) == true) goto L16;
        if ("com.android.launcher.permission.INSTALL_SHORTCUT".equals(r1) == false) goto L11;
        return true;
    L16:
        return true;
    L18:
        return false;
    }

    public static boolean pushDynamicShortcut(Context r7, ShortcutInfoCompat r8) {
        Preconditions.checkNotNull(r7);
        Preconditions.checkNotNull(r8);
        int r0 = Build.VERSION.SDK_INT;
        if (r0 <= 32) goto L5;
    L11:
        int r1 = getMaxShortcutCountPerActivity(r7);
        if (r1 != 0) goto L15;
        return false;
    L15:
        if (r0 > 29) goto L18;
        convertUriIconToBitmapIcon(r7, r8);
    L18:
        if (r0 < 30) goto L21;
        ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(r7.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).pushDynamicShortcut(r8.toShortcutInfo());
    L29:
        ShortcutInfoCompatSaver<?> r02 = getShortcutInfoSaverInstance(r7);
        List<ShortcutInfoCompat> r5 = r02.getShortcuts();     // Catch: Throwable -> L33 Exception -> L48
        if (r5.size() < r1) goto L35;
        r02.removeShortcuts(Arrays.asList(new String[]{getShortcutInfoCompatWithLowestRank(r5)}));     // Catch: Throwable -> L33 Exception -> L48
    L35:
        r02.addShortcuts(Arrays.asList(new ShortcutInfoCompat[]{r8}));     // Catch: Throwable -> L33 Exception -> L48
        Iterator<Object> r03 = getShortcutInfoListeners(r7).iterator();
        if (r03.hasNext() == true) goto L40;
        reportShortcutUsed(r7, r8.getId());
        return true;
    L40:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r03.next());
        Collections.singletonList(r8);
        throw null;
    L48:
        Iterator<Object> r04 = getShortcutInfoListeners(r7).iterator();
        if (r04.hasNext() == true) goto L52;
        reportShortcutUsed(r7, r8.getId());
        return false;
    L52:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r04.next());
        Collections.singletonList(r8);
        throw null;
    L33:
        th = move-exception;
        Iterator<Object> r12 = getShortcutInfoListeners(r7).iterator();
        if (r12.hasNext() == false) goto L46;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r12.next());
        Collections.singletonList(r8);
        throw null;
    L46:
        reportShortcutUsed(r7, r8.getId());
        throw th;
    L21:
        if (r0 < 25) goto L29;
        ShortcutManager r05 = ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(r7.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m()));
        if (r05.isRateLimitingActive() == false) goto L25;
        return false;
    L25:
        List<ShortcutInfo> r52 = r05.getDynamicShortcuts();
        if (r52.size() < r1) goto L28;
        r05.removeDynamicShortcuts(Arrays.asList(new String[]{Api25Impl.getShortcutInfoWithLowestRank(r52)}));
    L28:
        r05.addDynamicShortcuts(Arrays.asList(new ShortcutInfo[]{r8.toShortcutInfo()}));
        goto L29
    L5:
        if (r8.isExcludedFromSurfaces(1) == false) goto L11;
        Iterator<Object> r72 = getShortcutInfoListeners(r7).iterator();
        if (r72.hasNext() == true) goto L9;
        return true;
    L9:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r72.next());
        Collections.singletonList(r8);
        throw null;
    }

    public static void removeAllDynamicShortcuts(Context r2) {
        if (Build.VERSION.SDK_INT < 25) goto L5;
        ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(r2.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).removeAllDynamicShortcuts();
    L5:
        getShortcutInfoSaverInstance(r2).removeAllShortcuts();
        Iterator<Object> r22 = getShortcutInfoListeners(r2).iterator();
        if (r22.hasNext() == true) goto L8;
        return;
    L8:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r22.next());
        throw null;
    }

    public static void removeDynamicShortcuts(Context r2, List<String> r3) {
        if (Build.VERSION.SDK_INT < 25) goto L5;
        ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(r2.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).removeDynamicShortcuts(r3);
    L5:
        getShortcutInfoSaverInstance(r2).removeShortcuts(r3);
        Iterator<Object> r22 = getShortcutInfoListeners(r2).iterator();
        if (r22.hasNext() == true) goto L8;
        return;
    L8:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r22.next());
        throw null;
    }

    private static List<ShortcutInfoCompat> removeShortcutsExcludedFromSurface(List<ShortcutInfoCompat> r3, int r4) {
        Objects.requireNonNull(r3);
        if (Build.VERSION.SDK_INT <= 32) goto L5;
        return r3;
    L5:
        ArrayList r0 = new ArrayList(r3);
        Iterator<ShortcutInfoCompat> r32 = r3.iterator();
    L7:
        if (r32.hasNext() == false) goto L11;
        ShortcutInfoCompat r1 = r32.next();
        if (r1.isExcludedFromSurfaces(r4) == false) goto L7;
        r0.remove(r1);
        goto L7
    L11:
        return r0;
    }

    public static void reportShortcutUsed(Context r2, String r3) {
        Preconditions.checkNotNull(r2);
        Preconditions.checkNotNull(r3);
        if (Build.VERSION.SDK_INT < 25) goto L5;
        ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(r2.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).reportShortcutUsed(r3);
    L5:
        Iterator<Object> r22 = getShortcutInfoListeners(r2).iterator();
        if (r22.hasNext() == true) goto L8;
        return;
    L8:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r22.next());
        Collections.singletonList(r3);
        throw null;
    }

    public static boolean requestPinShortcut(Context r12, ShortcutInfoCompat r13, IntentSender r14) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 > 32) goto L8;
        if (r13.isExcludedFromSurfaces(1) == false) goto L8;
        return false;
    L8:
        if (r0 < 26) goto L12;
        return ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(r12.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).requestPinShortcut(r13.toShortcutInfo(), r14);
    L12:
        if (isRequestPinShortcutSupported(r12) == true) goto L14;
        return false;
    L14:
        Intent r5 = r13.addToIntent(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"));
        if (r14 != null) goto L18;
        r12.sendBroadcast(r5);
        return true;
    L18:
        r12.sendOrderedBroadcast(r5, null, new AnonymousClass1(r14), null, -1, null, null);
        return true;
    }

    public static boolean updateShortcuts(Context r4, List<ShortcutInfoCompat> r5) {
        List<ShortcutInfoCompat> r52 = removeShortcutsExcludedFromSurface(r5, 1);
        int r1 = Build.VERSION.SDK_INT;
        if (r1 > 29) goto L6;
        convertUriIconsToBitmapIcons(r4, r52);
    L6:
        if (r1 < 25) goto L15;
        ArrayList r12 = new ArrayList();
        Iterator<ShortcutInfoCompat> r2 = r52.iterator();
    L9:
        if (r2.hasNext() == false) goto L12;
        r12.add(r2.next().toShortcutInfo());
        goto L9
    L12:
        if (ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(r4.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).updateShortcuts(r12) == true) goto L15;
        return false;
    L15:
        getShortcutInfoSaverInstance(r4).addShortcuts(r52);
        Iterator<Object> r42 = getShortcutInfoListeners(r4).iterator();
        if (r42.hasNext() == true) goto L18;
        return true;
    L18:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r42.next());
        throw null;
    }
}
