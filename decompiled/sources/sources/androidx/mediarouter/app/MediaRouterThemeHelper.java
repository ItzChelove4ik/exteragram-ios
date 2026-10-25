package androidx.mediarouter.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.R$attr;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.mediarouter.R;

/* loaded from: classes4.dex */
final class MediaRouterThemeHelper {
    static final int COLOR_DARK_ON_LIGHT_BACKGROUND = -570425344;
    private static final int COLOR_DARK_ON_LIGHT_BACKGROUND_RES_ID = 0;
    static final int COLOR_WHITE_ON_DARK_BACKGROUND = -1;
    private static final float MIN_CONTRAST = 3.0f;

    static {
        COLOR_DARK_ON_LIGHT_BACKGROUND_RES_ID = R.color.mr_dynamic_dialog_icon_light;
    }

    private MediaRouterThemeHelper() {
    }

    public static Context createThemedButtonContext(Context r2) {
        ContextThemeWrapper r0 = new ContextThemeWrapper(r2, getRouterThemeId(r2));
        int r22 = getThemeResource(r0, R.attr.mediaRouteTheme);
        if (r22 != 0) goto L5;
        return r0;
    L5:
        return new ContextThemeWrapper(r0, r22);
    }

    public static Context createThemedDialogContext(Context r0, int r1, boolean r2) {
        if (r1 != 0) goto L7;
        if (r2 == true) goto L5;
        int r12 = R$attr.dialogTheme;
    L6:
        r1 = getThemeResource(r0, r12);
        goto L7
    L5:
        r12 = R$attr.alertDialogTheme;
    L7:
        ContextThemeWrapper r22 = new ContextThemeWrapper(r0, r1);
        if (getThemeResource(r22, R.attr.mediaRouteTheme) != 0) goto L10;
        return r22;
    L10:
        return new ContextThemeWrapper(r22, getRouterThemeId(r22));
    }

    public static int createThemedDialogStyle(Context r1) {
        int r0 = getThemeResource(r1, R.attr.mediaRouteTheme);
        if (r0 == 0) goto L5;
        return r0;
    L5:
        return getRouterThemeId(r1);
    }

    public static int getButtonTextColor(Context r6) {
        int r0 = getThemeColor(r6, 0, R$attr.colorPrimary);
        if (ColorUtils.calculateContrast(r0, getThemeColor(r6, 0, android.R.attr.colorBackground)) < 3.0d) goto L5;
        return r0;
    L5:
        return getThemeColor(r6, 0, R$attr.colorAccent);
    }

    public static Drawable getCheckBoxDrawableIcon(Context r1) {
        return getIconByDrawableId(r1, R.drawable.mr_cast_checkbox);
    }

    public static int getControllerColor(Context r4, int r5) {
        if (ColorUtils.calculateContrast(-1, getThemeColor(r4, r5, R$attr.colorPrimary)) < 3.0d) goto L5;
        return -1;
    L5:
        return COLOR_DARK_ON_LIGHT_BACKGROUND;
    }

    public static Drawable getDefaultDrawableIcon(Context r1) {
        return getIconByAttrId(r1, R.attr.mediaRouteDefaultIconDrawable);
    }

    public static float getDisabledAlpha(Context r3) {
        TypedValue r0 = new TypedValue();
        if (r3.getTheme().resolveAttribute(android.R.attr.disabledAlpha, r0, true) == true) goto L5;
        return 0.5f;
    L5:
        return r0.getFloat();
    }

    private static Drawable getIconByAttrId(Context r2, int r3) {
        TypedArray r32 = r2.obtainStyledAttributes(new int[]{r3});
        Drawable r0 = DrawableCompat.wrap(AppCompatResources.getDrawable(r2, r32.getResourceId(0, 0)));
        if (isLightTheme(r2) == false) goto L5;
        DrawableCompat.setTint(r0, ContextCompat.getColor(r2, COLOR_DARK_ON_LIGHT_BACKGROUND_RES_ID));
    L5:
        r32.recycle();
        return r0;
    }

    private static Drawable getIconByDrawableId(Context r1, int r2) {
        Drawable r22 = DrawableCompat.wrap(AppCompatResources.getDrawable(r1, r2));
        if (isLightTheme(r1) == false) goto L5;
        DrawableCompat.setTint(r22, ContextCompat.getColor(r1, COLOR_DARK_ON_LIGHT_BACKGROUND_RES_ID));
    L5:
        return r22;
    }

    public static Drawable getMuteButtonDrawableIcon(Context r1) {
        return getIconByDrawableId(r1, R.drawable.mr_cast_mute_button);
    }

    private static int getRouterThemeId(Context r3) {
        if (isLightTheme(r3) == false) goto L11;
        if (getControllerColor(r3, 0) != COLOR_DARK_ON_LIGHT_BACKGROUND) goto L9;
        return R.style.Theme_MediaRouter_Light;
    L9:
        return R.style.Theme_MediaRouter_Light_DarkControlPanel;
    L11:
        if (getControllerColor(r3, 0) != COLOR_DARK_ON_LIGHT_BACKGROUND) goto L15;
        return R.style.Theme_MediaRouter_LightControlPanel;
    L15:
        return R.style.Theme_MediaRouter;
    }

    public static Drawable getSpeakerDrawableIcon(Context r1) {
        return getIconByAttrId(r1, R.attr.mediaRouteSpeakerIconDrawable);
    }

    public static Drawable getSpeakerGroupDrawableIcon(Context r1) {
        return getIconByAttrId(r1, R.attr.mediaRouteSpeakerGroupIconDrawable);
    }

    public static TypedArray getStyledAttributes(Context r4) {
        return r4.obtainStyledAttributes(new int[]{R.attr.mediaRouteDefaultIconDrawable, R.attr.mediaRouteTvIconDrawable, R.attr.mediaRouteSpeakerIconDrawable, R.attr.mediaRouteSpeakerGroupIconDrawable});
    }

    private static int getThemeColor(Context r2, int r3, int r4) {
        if (r3 == 0) goto L6;
        TypedArray r32 = r2.obtainStyledAttributes(r3, new int[]{r4});
        int r0 = r32.getColor(0, 0);
        r32.recycle();
        if (r0 == 0) goto L6;
        return r0;
    L6:
        TypedValue r33 = new TypedValue();
        r2.getTheme().resolveAttribute(r4, r33, true);
        if (r33.resourceId == 0) goto L11;
        return r2.getResources().getColor(r33.resourceId);
    L11:
        return r33.data;
    }

    public static int getThemeResource(Context r2, int r3) {
        TypedValue r0 = new TypedValue();
        if (r2.getTheme().resolveAttribute(r3, r0, true) == true) goto L5;
        return 0;
    L5:
        return r0.resourceId;
    }

    public static Drawable getTvDrawableIcon(Context r1) {
        return getIconByAttrId(r1, R.attr.mediaRouteTvIconDrawable);
    }

    private static boolean isLightTheme(Context r3) {
        TypedValue r0 = new TypedValue();
        if (r3.getTheme().resolveAttribute(R$attr.isLightTheme, r0, true) == true) goto L5;
        return false;
    L5:
        if (r0.data == 0) goto L9;
        return true;
    L9:
        return false;
    }

    public static void setDialogBackgroundColor(Context r1, Dialog r2) {
        View r22 = r2.getWindow().getDecorView();
        if (isLightTheme(r1) == false) goto L5;
        int r0 = R.color.mr_dynamic_dialog_background_light;
    L6:
        r22.setBackgroundColor(ContextCompat.getColor(r1, r0));
        return;
    L5:
        r0 = R.color.mr_dynamic_dialog_background_dark;
        goto L6
    }

    public static void setIndeterminateProgressBarColor(Context r1, ProgressBar r2) {
        if (r2.isIndeterminate() == true) goto L6;
        return;
    L6:
        if (isLightTheme(r1) == false) goto L8;
        int r0 = R.color.mr_cast_progressbar_progress_and_thumb_light;
    L9:
        int r12 = ContextCompat.getColor(r1, r0);
        r2.getIndeterminateDrawable().setColorFilter(r12, PorterDuff.Mode.SRC_IN);
        return;
    L8:
        r0 = R.color.mr_cast_progressbar_progress_and_thumb_dark;
        goto L9
    }

    public static void setMediaControlsBackgroundColor(Context r3, View r4, View r5, boolean r6) {
        int r0 = getThemeColor(r3, 0, R$attr.colorPrimary);
        int r2 = getThemeColor(r3, 0, R$attr.colorPrimaryDark);
        if (r6 == true) goto L5;
    L7:
        r4.setBackgroundColor(r0);
        r5.setBackgroundColor(r2);
        r4.setTag(Integer.valueOf(r0));
        r5.setTag(Integer.valueOf(r2));
        return;
    L5:
        if (getControllerColor(r3, 0) != COLOR_DARK_ON_LIGHT_BACKGROUND) goto L7;
        r2 = r0;
        r0 = -1;
        goto L7
    }

    public static void setVolumeSliderColor(Context r2, MediaRouteVolumeSlider r3) {
        if (isLightTheme(r2) == false) goto L5;
        int r0 = ContextCompat.getColor(r2, R.color.mr_cast_progressbar_progress_and_thumb_light);
        int r22 = ContextCompat.getColor(r2, R.color.mr_cast_progressbar_background_light);
    L6:
        r3.setColor(r0, r22);
        return;
    L5:
        r0 = ContextCompat.getColor(r2, R.color.mr_cast_progressbar_progress_and_thumb_dark);
        r22 = ContextCompat.getColor(r2, R.color.mr_cast_progressbar_background_dark);
        goto L6
    }

    public static void setVolumeSliderColor(Context r2, MediaRouteVolumeSlider r3, View r4) {
        int r22 = getControllerColor(r2, 0);
        if (Color.alpha(r22) == 255) goto L5;
        r22 = ColorUtils.compositeColors(r22, ((Integer) r4.getTag()).intValue());
    L5:
        r3.setColor(r22);
    }
}
