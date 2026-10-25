package com.exteragram.messenger.appicons;

import android.content.Context;
import android.content.pm.PackageManager;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public abstract class AppIconController {
    private static List<AppIcon> allIcons;
    private static AppIcon selectedIcon;

    /* renamed from: $r8$lambda$-OlWDDNnew4nPmFUo8i45cwfaNo, reason: not valid java name */
    public static /* synthetic */ void m1100$r8$lambda$OlWDDNnew4nPmFUo8i45cwfaNo(AppIcon r0) {
        selectedIcon = r0;
    }

    /* renamed from: $r8$lambda$5uIc0UfuYc-xsOt72Pr_KfKeTK8, reason: not valid java name */
    public static /* synthetic */ void m1101$r8$lambda$5uIc0UfuYcxsOt72Pr_KfKeTK8(List r3, AppIcon r4) {
        Iterator r0 = r3.iterator();
    L4:
        if (r0.hasNext() == false) goto L11;
        AppIcon r1 = (AppIcon) r0.next();
        if (isEnabled(r1) == false) goto L4;
        AndroidUtilities.runOnUIThread(new AppIconController$$ExternalSyntheticLambda1(r1));
        if (r1.isMonet() == false) goto L16;
        applyIcon(r4, r3);
        applyIcon(r1, r3);
        return;
    L16:
        return;
    L11:
        AndroidUtilities.runOnUIThread(new AppIconController$$ExternalSyntheticLambda2(r4));
        applyIcon(r4, r3);
    }

    public static /* synthetic */ void $r8$lambda$W5UL3Z5w24Mq929fjLlgCr0zt7k(AppIcon r0) {
        selectedIcon = r0;
    }

    public static /* synthetic */ void $r8$lambda$e50DBscmx5OEPuYBDGfVTLU4Vmo(AppIcon r0, List r1) {
        applyIcon(r0, r1);
    }

    private static void applyIcon(AppIcon r5, List<AppIcon> r6) {
        Context r0 = ApplicationLoader.applicationContext;
        PackageManager r1 = r0.getPackageManager();
        r1.setComponentEnabledSetting(r5.getComponentName(r0), 1, 1);
        Iterator<AppIcon> r62 = r6.iterator();
    L4:
        if (r62.hasNext() == false) goto L9;
        AppIcon r2 = r62.next();
        if (r2 == r5) goto L4;
        r1.setComponentEnabledSetting(r2.getComponentName(r0), 2, 1);
        goto L4
    }

    public static AppIcon findById(String r3) {
        Iterator<AppIcon> r0 = getAllIcons().iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        AppIcon r1 = r0.next();
        if (r1.id.equals(r3) == false) goto L4;
        return r1;
    L8:
        return null;
    }

    public static void fixLauncherIconIfNeeded() {
        List<AppIcon> r0 = getAllIcons();
        AppIcon r1 = getDefaultIcon();
        Utilities.globalQueue.postRunnable(new AppIconController$$ExternalSyntheticLambda0(r0, r1));
    }

    public static List<AppIcon> getAllIcons() {
        if (allIcons != null) goto L6;
        allIcons = Collections.unmodifiableList(Arrays.asList(GeneratedAppIcons.create()));
    L6:
        return allIcons;
    }

    public static List<AppIcon> getAvailableIcons() {
        ArrayList r0 = new ArrayList();
        Iterator<AppIcon> r1 = getAllIcons().iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        AppIcon r2 = r1.next();
        if (r2.isAvailable() == false) goto L4;
        r0.add(r2);
        goto L4
    L8:
        return r0;
    }

    public static AppIcon getDefaultIcon() {
        AppIcon r0 = findById(PluginsConstants.Settings.DEFAULT);
        if (r0 == null) goto L6;
        return r0;
    L6:
        return getAllIcons().get(0);
    }

    public static AppIcon getSelectedIcon() {
        if (selectedIcon != null) goto L14;
        Iterator<AppIcon> r0 = getAllIcons().iterator();
    L6:
        if (r0.hasNext() == false) goto L11;
        AppIcon r1 = r0.next();
        if (isEnabled(r1) == false) goto L6;
        selectedIcon = r1;
    L11:
        if (selectedIcon != null) goto L14;
        selectedIcon = getDefaultIcon();
    L14:
        return selectedIcon;
    }

    public static boolean isEnabled(AppIcon r2) {
        Context r0 = ApplicationLoader.applicationContext;
        int r02 = r0.getPackageManager().getComponentEnabledSetting(r2.getComponentName(r0));
        if (r02 == 1) goto L10;
        if (r02 == 0) goto L6;
        return false;
    L6:
        if (r2.isDefault() == true) goto L10;
        return false;
    L10:
        return true;
    }

    public static void setIcon(AppIcon r3) {
        selectedIcon = r3;
        List<AppIcon> r0 = getAllIcons();
        Utilities.globalQueue.postRunnable(new AppIconController$$ExternalSyntheticLambda3(r3, r0));
    }
}
