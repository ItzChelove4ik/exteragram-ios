package com.exteragram.messenger.appicons;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.utils.AppUtils;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;

/* loaded from: classes4.dex */
public final class AppIcon {
    public final String authorName;
    public final String authorUsername;
    public final String availability;
    public final int background;
    public final boolean backgroundIsColor;
    public final int color;
    public final String component;
    private ComponentName componentName;
    public final String description;
    public final int descriptionRes;
    public final int foreground;
    public final String id;
    public final int monochrome;
    public final String title;
    public final int titleRes;

    private AppIcon(String r1, String r2, String r3, int r4, String r5, int r6, String r7, int r8, String r9, String r10, int r11, boolean r12, int r13, int r14) {
        this.id = r1;
        this.component = r2;
        this.availability = r3;
        this.titleRes = r4;
        this.title = r5;
        this.descriptionRes = r6;
        this.description = r7;
        this.color = r8;
        this.authorUsername = r9;
        this.authorName = r10;
        this.background = r11;
        this.backgroundIsColor = r12;
        this.foreground = r13;
        this.monochrome = r14;
    }

    public static AppIcon of(String r15, String r16, String r17, int r18, String r19, int r20, String r21, int r22, String r23, String r24, int r25, boolean r26, int r27, int r28) {
        return new AppIcon(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28);
    }

    public String getAuthor() {
        String r0 = this.authorName;
        if (r0 == null) goto L5;
        return r0;
    L5:
        String r02 = this.authorUsername;
        if (r02 != null) goto L8;
        return null;
    L8:
        if ("exteraGram".equalsIgnoreCase(r02) == false) goto L11;
        return null;
    L11:
        return "@" + this.authorUsername;
    }

    public int getBackground() {
        if (isDefault() == false) goto L9;
        if (BuildVars.isBetaApp() == false) goto L9;
        return R.mipmap.ic_launcher_beta_background;
    L9:
        return this.background;
    }

    public ComponentName getComponentName(Context r3) {
        if (this.componentName != null) goto L6;
        this.componentName = new ComponentName(r3.getPackageName(), this.component);
    L6:
        return this.componentName;
    }

    public CharSequence getDescription() {
        int r0 = this.descriptionRes;
        if (r0 == 0) goto L7;
        return LocaleController.getString(r0);
    L7:
        return this.description;
    }

    public int getForeground() {
        if (isDefault() == false) goto L9;
        if (BuildVars.isBetaApp() == false) goto L9;
        return R.mipmap.ic_launcher_beta_foreground;
    L9:
        return this.foreground;
    }

    public CharSequence getTitle() {
        int r0 = this.titleRes;
        if (r0 == 0) goto L7;
        return LocaleController.getString(r0);
    L7:
        return this.title;
    }

    public boolean isAvailable() {
        if ("winter".equals(this.availability) == false) goto L7;
        return AppUtils.isWinter();
    L7:
        if (isMonet() == false) goto L15;
        int r2 = Build.VERSION.SDK_INT;
        if (r2 >= 31) goto L11;
        return false;
    L11:
        if (r2 > 32) goto L16;
        return true;
    L16:
        return false;
    L15:
        return true;
    }

    public boolean isBackgroundColor() {
        if (this.backgroundIsColor == true) goto L5;
        return false;
    L5:
        if (isDefault() == true) goto L7;
        return true;
    L7:
        if (BuildVars.isBetaApp() == true) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean isDefault() {
        return PluginsConstants.Settings.DEFAULT.equals(this.id);
    }

    public boolean isMonet() {
        return "monet".equals(this.availability);
    }
}
