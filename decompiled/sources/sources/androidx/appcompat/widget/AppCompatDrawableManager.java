package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$drawable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.core.graphics.ColorUtils;

/* loaded from: classes3.dex */
public final class AppCompatDrawableManager {
    private static final PorterDuff.Mode DEFAULT_MODE = null;
    private static AppCompatDrawableManager INSTANCE;
    private ResourceManagerInternal mResourceManager;

    /* renamed from: androidx.appcompat.widget.AppCompatDrawableManager$1, reason: invalid class name */
    public class AnonymousClass1 implements ResourceManagerInternal.ResourceManagerHooks {
        private final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
        private final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
        private final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
        private final int[] TINT_CHECKABLE_BUTTON_LIST;
        private final int[] TINT_COLOR_CONTROL_NORMAL;
        private final int[] TINT_COLOR_CONTROL_STATE_LIST;

        public AnonymousClass1() {
            this.COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[]{R$drawable.abc_textfield_search_default_mtrl_alpha, R$drawable.abc_textfield_default_mtrl_alpha, R$drawable.abc_ab_share_pack_mtrl_alpha};
            this.TINT_COLOR_CONTROL_NORMAL = new int[]{R$drawable.abc_ic_commit_search_api_mtrl_alpha, R$drawable.abc_seekbar_tick_mark_material, R$drawable.abc_ic_menu_share_mtrl_alpha, R$drawable.abc_ic_menu_copy_mtrl_am_alpha, R$drawable.abc_ic_menu_cut_mtrl_alpha, R$drawable.abc_ic_menu_selectall_mtrl_alpha, R$drawable.abc_ic_menu_paste_mtrl_am_alpha};
            this.COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[]{R$drawable.abc_textfield_activated_mtrl_alpha, R$drawable.abc_textfield_search_activated_mtrl_alpha, R$drawable.abc_cab_background_top_mtrl_alpha, R$drawable.abc_text_cursor_material, R$drawable.abc_text_select_handle_left_mtrl, R$drawable.abc_text_select_handle_middle_mtrl, R$drawable.abc_text_select_handle_right_mtrl};
            this.COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[]{R$drawable.abc_popup_background_mtrl_mult, R$drawable.abc_cab_background_internal_bg, R$drawable.abc_menu_hardkey_panel_mtrl_mult};
            this.TINT_COLOR_CONTROL_STATE_LIST = new int[]{R$drawable.abc_tab_indicator_material, R$drawable.abc_textfield_search_material};
            this.TINT_CHECKABLE_BUTTON_LIST = new int[]{R$drawable.abc_btn_check_material, R$drawable.abc_btn_radio_material, R$drawable.abc_btn_check_material_anim, R$drawable.abc_btn_radio_material_anim};
        }

        private boolean arrayContains(int[] r4, int r5) {
            int r3 = r4.length;
            int r1 = 0;
        L3:
            if (r1 >= r3) goto L9;
            if (r4[r1] == r5) goto L6;
            r1 = r1 + 1;
            goto L3
        L6:
            return true;
        L9:
            return false;
        }

        private ColorStateList createBorderlessButtonColorStateList(Context r2) {
            return createButtonColorStateList(r2, 0);
        }

        private ColorStateList createButtonColorStateList(Context r5, int r6) {
            int r1 = ThemeUtils.getThemeAttrColor(r5, R$attr.colorControlHighlight);
            int r52 = ThemeUtils.getDisabledThemeAttrColor(r5, R$attr.colorButtonNormal);
            return new ColorStateList(new int[][]{ThemeUtils.DISABLED_STATE_SET, ThemeUtils.PRESSED_STATE_SET, ThemeUtils.FOCUSED_STATE_SET, ThemeUtils.EMPTY_STATE_SET}, new int[]{r52, ColorUtils.compositeColors(r1, r6), ColorUtils.compositeColors(r1, r6), r6});
        }

        private ColorStateList createColoredButtonColorStateList(Context r2) {
            return createButtonColorStateList(r2, ThemeUtils.getThemeAttrColor(r2, R$attr.colorAccent));
        }

        private ColorStateList createDefaultButtonColorStateList(Context r2) {
            return createButtonColorStateList(r2, ThemeUtils.getThemeAttrColor(r2, R$attr.colorButtonNormal));
        }

        private ColorStateList createSwitchThumbColorStateList(Context r7) {
            int[][] r0 = new int[3][];
            int[] r6 = new int[3];
            ColorStateList r1 = ThemeUtils.getThemeAttrColorStateList(r7, R$attr.colorSwitchThumbNormal);
            if (r1 != null) goto L5;
        L7:
            r0[0] = ThemeUtils.DISABLED_STATE_SET;
            r6[0] = ThemeUtils.getDisabledThemeAttrColor(r7, R$attr.colorSwitchThumbNormal);
            r0[1] = ThemeUtils.CHECKED_STATE_SET;
            r6[1] = ThemeUtils.getThemeAttrColor(r7, R$attr.colorControlActivated);
            r0[2] = ThemeUtils.EMPTY_STATE_SET;
            r6[2] = ThemeUtils.getThemeAttrColor(r7, R$attr.colorSwitchThumbNormal);
        L9:
            return new ColorStateList(r0, r6);
        L5:
            if (r1.isStateful() == false) goto L7;
            int[] r5 = ThemeUtils.DISABLED_STATE_SET;
            r0[0] = r5;
            r6[0] = r1.getColorForState(r5, 0);
            r0[1] = ThemeUtils.CHECKED_STATE_SET;
            r6[1] = ThemeUtils.getThemeAttrColor(r7, R$attr.colorControlActivated);
            r0[2] = ThemeUtils.EMPTY_STATE_SET;
            r6[2] = r1.getDefaultColor();
            goto L9
        }

        private LayerDrawable getRatingBarLayerDrawable(ResourceManagerInternal r4, Context r5, int r6) {
            int r3 = r5.getResources().getDimensionPixelSize(r6);
            Drawable r62 = r4.getDrawable(r5, R$drawable.abc_star_black_48dp);
            Drawable r42 = r4.getDrawable(r5, R$drawable.abc_star_half_black_48dp);
            if ((r62 instanceof BitmapDrawable) == true) goto L5;
        L9:
            Bitmap r52 = Bitmap.createBitmap(r3, r3, Bitmap.Config.ARGB_8888);
            Canvas r1 = new Canvas(r52);
            r62.setBounds(0, 0, r3, r3);
            r62.draw(r1);
            BitmapDrawable r63 = new BitmapDrawable(r52);
            BitmapDrawable r53 = new BitmapDrawable(r52);
        L10:
            r53.setTileModeX(Shader.TileMode.REPEAT);
            if ((r42 instanceof BitmapDrawable) == true) goto L13;
        L17:
            Bitmap r12 = Bitmap.createBitmap(r3, r3, Bitmap.Config.ARGB_8888);
            Canvas r2 = new Canvas(r12);
            r42.setBounds(0, 0, r3, r3);
            r42.draw(r2);
            BitmapDrawable r43 = new BitmapDrawable(r12);
        L18:
            LayerDrawable r32 = new LayerDrawable(new Drawable[]{r63, r43, r53});
            r32.setId(0, R.id.background);
            r32.setId(1, R.id.secondaryProgress);
            r32.setId(2, R.id.progress);
            return r32;
        L13:
            if (r42.getIntrinsicWidth() != r3) goto L17;
            if (r42.getIntrinsicHeight() != r3) goto L17;
            r43 = (BitmapDrawable) r42;
            goto L18
        L5:
            if (r62.getIntrinsicWidth() != r3) goto L9;
            if (r62.getIntrinsicHeight() != r3) goto L9;
            r63 = (BitmapDrawable) r62;
            r53 = new BitmapDrawable(r63.getBitmap());
            goto L10
        }

        private void setPorterDuffColorFilter(Drawable r1, int r2, PorterDuff.Mode r3) {
            Drawable r0 = r1.mutate();
            if (r3 != null) goto L5;
            r3 = AppCompatDrawableManager.access$000();
        L5:
            r0.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(r2, r3));
        }

        @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
        public Drawable createDrawableFor(ResourceManagerInternal r2, Context r3, int r4) {
            if (r4 != R$drawable.abc_cab_background_top_material) goto L7;
            return new LayerDrawable(new Drawable[]{r2.getDrawable(r3, R$drawable.abc_cab_background_internal_bg), r2.getDrawable(r3, R$drawable.abc_cab_background_top_mtrl_alpha)});
        L7:
            if (r4 != R$drawable.abc_ratingbar_material) goto L11;
            return getRatingBarLayerDrawable(r2, r3, R$dimen.abc_star_big);
        L11:
            if (r4 != R$drawable.abc_ratingbar_indicator_material) goto L15;
            return getRatingBarLayerDrawable(r2, r3, R$dimen.abc_star_medium);
        L15:
            if (r4 == R$drawable.abc_ratingbar_small_material) goto L17;
            return null;
        L17:
            return getRatingBarLayerDrawable(r2, r3, R$dimen.abc_star_small);
        }

        @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
        public ColorStateList getTintListForDrawableRes(Context r2, int r3) {
            if (r3 != R$drawable.abc_edit_text_material) goto L7;
            return AppCompatResources.getColorStateList(r2, R$color.abc_tint_edittext);
        L7:
            if (r3 != R$drawable.abc_switch_track_mtrl_alpha) goto L11;
            return AppCompatResources.getColorStateList(r2, R$color.abc_tint_switch_track);
        L11:
            if (r3 != R$drawable.abc_switch_thumb_material) goto L15;
            return createSwitchThumbColorStateList(r2);
        L15:
            if (r3 != R$drawable.abc_btn_default_mtrl_shape) goto L19;
            return createDefaultButtonColorStateList(r2);
        L19:
            if (r3 != R$drawable.abc_btn_borderless_material) goto L23;
            return createBorderlessButtonColorStateList(r2);
        L23:
            if (r3 != R$drawable.abc_btn_colored_material) goto L27;
            return createColoredButtonColorStateList(r2);
        L27:
            if (r3 == R$drawable.abc_spinner_mtrl_am_alpha) goto L50;
            if (r3 == R$drawable.abc_spinner_textfield_background_material) goto L50;
            if (arrayContains(this.TINT_COLOR_CONTROL_NORMAL, r3) == false) goto L36;
            return ThemeUtils.getThemeAttrColorStateList(r2, R$attr.colorControlNormal);
        L36:
            if (arrayContains(this.TINT_COLOR_CONTROL_STATE_LIST, r3) == false) goto L40;
            return AppCompatResources.getColorStateList(r2, R$color.abc_tint_default);
        L40:
            if (arrayContains(this.TINT_CHECKABLE_BUTTON_LIST, r3) == false) goto L44;
            return AppCompatResources.getColorStateList(r2, R$color.abc_tint_btn_checkable);
        L44:
            if (r3 == R$drawable.abc_seekbar_thumb_material) goto L46;
            return null;
        L46:
            return AppCompatResources.getColorStateList(r2, R$color.abc_tint_seek_thumb);
        L50:
            return AppCompatResources.getColorStateList(r2, R$color.abc_tint_spinner);
        }

        @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
        public PorterDuff.Mode getTintModeForDrawableRes(int r1) {
            if (r1 == R$drawable.abc_switch_thumb_material) goto L5;
            return null;
        L5:
            return PorterDuff.Mode.MULTIPLY;
        }

        @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
        public boolean tintDrawable(Context r6, int r7, Drawable r8) {
            if (r7 != R$drawable.abc_seekbar_track_material) goto L7;
            LayerDrawable r82 = (LayerDrawable) r8;
            setPorterDuffColorFilter(r82.findDrawableByLayerId(R.id.background), ThemeUtils.getThemeAttrColor(r6, R$attr.colorControlNormal), AppCompatDrawableManager.access$000());
            setPorterDuffColorFilter(r82.findDrawableByLayerId(R.id.secondaryProgress), ThemeUtils.getThemeAttrColor(r6, R$attr.colorControlNormal), AppCompatDrawableManager.access$000());
            setPorterDuffColorFilter(r82.findDrawableByLayerId(R.id.progress), ThemeUtils.getThemeAttrColor(r6, R$attr.colorControlActivated), AppCompatDrawableManager.access$000());
            return true;
        L7:
            if (r7 != R$drawable.abc_ratingbar_material) goto L9;
        L15:
            LayerDrawable r83 = (LayerDrawable) r8;
            setPorterDuffColorFilter(r83.findDrawableByLayerId(R.id.background), ThemeUtils.getDisabledThemeAttrColor(r6, R$attr.colorControlNormal), AppCompatDrawableManager.access$000());
            setPorterDuffColorFilter(r83.findDrawableByLayerId(R.id.secondaryProgress), ThemeUtils.getThemeAttrColor(r6, R$attr.colorControlActivated), AppCompatDrawableManager.access$000());
            setPorterDuffColorFilter(r83.findDrawableByLayerId(R.id.progress), ThemeUtils.getThemeAttrColor(r6, R$attr.colorControlActivated), AppCompatDrawableManager.access$000());
            return true;
        L9:
            if (r7 == R$drawable.abc_ratingbar_indicator_material) goto L15;
            if (r7 == R$drawable.abc_ratingbar_small_material) goto L15;
            return false;
        }

        @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
        public boolean tintDrawableUsingColorFilter(Context r6, int r7, Drawable r8) {
            PorterDuff.Mode r0 = AppCompatDrawableManager.access$000();
            if (arrayContains(this.COLORFILTER_TINT_COLOR_CONTROL_NORMAL, r7) == false) goto L8;
            int r5 = R$attr.colorControlNormal;
        L5:
            boolean r1 = true;
        L6:
            int r72 = -1;
        L21:
            if (r1 == false) goto L26;
            Drawable r82 = r8.mutate();
            r82.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(r6, r5), r0));
            if (r72 == (-1)) goto L25;
            r82.setAlpha(r72);
        L25:
            return true;
        L26:
            return false;
        L8:
            if (arrayContains(this.COLORFILTER_COLOR_CONTROL_ACTIVATED, r7) == false) goto L11;
            r5 = R$attr.colorControlActivated;
            goto L5
        L11:
            if (arrayContains(this.COLORFILTER_COLOR_BACKGROUND_MULTIPLY, r7) == false) goto L15;
            r0 = PorterDuff.Mode.MULTIPLY;
        L13:
            r5 = 16842801;
            goto L5
        L15:
            if (r7 != R$drawable.abc_list_divider_mtrl_alpha) goto L18;
            r72 = Math.round(40.8f);
            r5 = 16842800;
            r1 = true;
            goto L21
        L18:
            if (r7 == R$drawable.abc_dialog_material_background) goto L13;
            r5 = 0;
            r1 = false;
            goto L6
        }
    }

    static {
        DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    }

    public AppCompatDrawableManager() {
    }

    public static /* synthetic */ PorterDuff.Mode access$000() {
        return DEFAULT_MODE;
    }

    public static synchronized AppCompatDrawableManager get() {
        monitor-enter(AppCompatDrawableManager.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (INSTANCE != null) goto L9;
        preload();     // Catch: Throwable -> L7
    L9:
        AppCompatDrawableManager r1 = INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(AppCompatDrawableManager.class);
        return r1;
    }

    public static synchronized PorterDuffColorFilter getPorterDuffColorFilter(int r1, PorterDuff.Mode r2) {
        monitor-enter(AppCompatDrawableManager.class);
        PorterDuffColorFilter r12 = ResourceManagerInternal.getPorterDuffColorFilter(r1, r2);     // Catch: Throwable -> L7
        monitor-exit(AppCompatDrawableManager.class);
        return r12;
    L7:
        th = move-exception;
        throw th;
    }

    public static synchronized void preload() {
        monitor-enter(AppCompatDrawableManager.class);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (INSTANCE != null) goto L10;
        AppCompatDrawableManager r1 = new AppCompatDrawableManager();     // Catch: Throwable -> L8
        INSTANCE = r1;     // Catch: Throwable -> L8
        r1.mResourceManager = ResourceManagerInternal.get();     // Catch: Throwable -> L8
        INSTANCE.mResourceManager.setHooks(new AnonymousClass1());     // Catch: Throwable -> L8
    L10:
        monitor-exit(AppCompatDrawableManager.class);
    }

    public static void tintDrawable(Drawable r0, TintInfo r1, int[] r2) {
        ResourceManagerInternal.tintDrawable(r0, r1, r2);
    }

    public synchronized Drawable getDrawable(Context r2, int r3) {
        monitor-enter(this);
        Drawable r22 = this.mResourceManager.getDrawable(r2, r3);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r22;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized Drawable getDrawable(Context r2, int r3, boolean r4) {
        monitor-enter(this);
        Drawable r22 = this.mResourceManager.getDrawable(r2, r3, r4);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r22;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized ColorStateList getTintList(Context r2, int r3) {
        monitor-enter(this);
        ColorStateList r22 = this.mResourceManager.getTintList(r2, r3);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r22;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized void onConfigurationChanged(Context r2) {
        monitor-enter(this);
        this.mResourceManager.onConfigurationChanged(r2);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
