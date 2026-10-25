package com.exteragram.messenger.utils.ui;

import android.animation.StateListAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.BaseCell;
import org.telegram.ui.Components.ScaleStateListAnimator;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\t\u0010\bJ!\u0010\f\u001a\u00020\u00062\b\b\u0001\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\rJS\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u00102\b\b\u0002\u0010\u0017\u001a\u00020\u0010H\u0007¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ-\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010\u001b\u001a\u00020\u00042\b\b\u0003\u0010\u0005\u001a\u00020\u00042\b\b\u0003\u0010\u001e\u001a\u00020\u0004H\u0007¢\u0006\u0004\b \u0010!J'\u0010&\u001a\u00020%2\u0006\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0004H\u0007¢\u0006\u0004\b&\u0010'J'\u0010*\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00102\b\b\u0002\u0010)\u001a\u00020\u0010¢\u0006\u0004\b*\u0010+J5\u00102\u001a\u00020\u00182\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\u001f2\u0006\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020\u00102\u0006\u00101\u001a\u00020\u0010¢\u0006\u0004\b2\u00103R \u00106\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u000205048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u0014\u00109\u001a\u0002088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010:¨\u0006;"}, d2 = {"Lcom/exteragram/messenger/utils/ui/UIUtil;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "color", "Landroid/graphics/drawable/GradientDrawable;", "createBottomFade", "(I)Landroid/graphics/drawable/GradientDrawable;", "createTopFade", "Landroid/graphics/drawable/GradientDrawable$Orientation;", "orientation", "createFade", "(ILandroid/graphics/drawable/GradientDrawable$Orientation;)Landroid/graphics/drawable/GradientDrawable;", "Landroid/view/View;", PluginsConstants.Settings.VIEW, _UrlKt.FRAGMENT_ENCODE_SET, "selectorRad", _UrlKt.FRAGMENT_ENCODE_SET, "top", "bottom", "basePadding", "scale", "tension", _UrlKt.FRAGMENT_ENCODE_SET, "applyScaleStateListAnimator", "(Landroid/view/View;FZZIFF)V", "size", "getFabSquareCornerRadiusDp", "(I)F", "pressedColor", "Landroid/graphics/drawable/Drawable;", "createFabBackground", "(III)Landroid/graphics/drawable/Drawable;", "drawable", "width", "height", "Landroid/graphics/Bitmap;", "drawableToBitmap", "(Landroid/graphics/drawable/Drawable;II)Landroid/graphics/Bitmap;", "luminance", "saturation", "adjustHsl", "(IFF)I", "Landroid/graphics/Canvas;", "canvas", "pattern", "w", "h", "alpha", "drawNowPlayingPattern", "(Landroid/graphics/Canvas;Landroid/graphics/drawable/Drawable;FFF)V", "Ljava/util/WeakHashMap;", "Landroid/view/View$OnLayoutChangeListener;", "scaleAnimatorRelayoutListeners", "Ljava/util/WeakHashMap;", _UrlKt.FRAGMENT_ENCODE_SET, "nowPlayingPattern", "[F", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUIUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UIUtil.kt\ncom/exteragram/messenger/utils/ui/UIUtil\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Bitmap.kt\nandroidx/core/graphics/BitmapKt\n*L\n1#1,254:1\n1#2:255\n83#3,6:256\n*S KotlinDebug\n*F\n+ 1 UIUtil.kt\ncom/exteragram/messenger/utils/ui/UIUtil\n*L\n161#1:256,6\n*E\n"})
/* loaded from: classes.dex */
public final class UIUtil {
    public static final UIUtil INSTANCE = null;
    private static final float[] nowPlayingPattern = null;
    private static final WeakHashMap<View, View.OnLayoutChangeListener> scaleAnimatorRelayoutListeners = null;

    /* renamed from: $r8$lambda$ERjZRGym-Kx6PQpLiaKZuzWZ1SI, reason: not valid java name */
    public static void m1801$r8$lambda$ERjZRGymKx6PQpLiaKZuzWZ1SI(int r0, int r1, float r2, boolean r3, boolean r4, int r5, float r6, float r7, View r8, int r9, int r10, int r11, int r12, int r13, int r14, int r15, int r16) {
        if (r8.getWidth() != r0) goto L8;
        if (r8.getHeight() != r1) goto L8;
        return;
    L8:
        applyScaleStateListAnimator(r8, r2, r3, r4, r5, r6, r7);
    }

    public static void $r8$lambda$WQTpFaIaGS_SgQ7OokNE_nnMyDw(Function1 r0, Float r1) {
        r0.invoke(r1);
    }

    public static Unit $r8$lambda$oVSOXQjJV1OPu6LNcwPkuNQY5Oo(View r6, float r7, float r8, int r9, boolean r10, boolean r11, float r12) {
        Drawable r0 = r6.getBackground();
        Theme.RippleRadMaskDrawable r2 = null;
        if ((r0 instanceof BaseCell.RippleDrawableSafe) == false) goto L5;
        BaseCell.RippleDrawableSafe r02 = (BaseCell.RippleDrawableSafe) r0;
    L6:
        if (r02 == null) goto L20;
        Drawable r03 = r02.mask;
        if ((r03 instanceof Theme.RippleRadMaskDrawable) == false) goto L10;
        r2 = (Theme.RippleRadMaskDrawable) r03;
    L10:
        if (r2 == null) goto L20;
        float r04 = r6.getWidth() / AndroidUtilities.density;
        float r62 = r6.getHeight() / AndroidUtilities.density;
        float r1 = 1.0f - (r7 * r12);
        float r05 = RangesKt.coerceAtLeast(((4.0f - ((r04 * r7) / 2.0f)) * r12) / r1, 0.0f);
        float r63 = RangesKt.coerceAtLeast(((4.0f - ((r62 * r7) / 2.0f)) * r12) / r1, 0.0f);
        r2.setPadding(r05, r63, r05, r63);
        float r64 = r9;
        float r72 = (r8 - (r64 * r12)) / r1;
        float r82 = ((r8 - r64) * r12) / r1;
        if (r10 == false) goto L14;
        float r65 = r72;
    L15:
        if (r11 == true) goto L18;
        r72 = r82;
    L18:
        r2.setRadius(r65, r72);
        goto L20
    L14:
        r65 = r82;
    L20:
        return Unit.INSTANCE;
    L5:
        r02 = null;
        goto L6
    }

    public static void $r8$lambda$s5RLQedcgGbbX3p4y44StfQpIhc(Function1 r0, Float r1) {
        r0.invoke(r1);
    }

    static {
        INSTANCE = new UIUtil();
        scaleAnimatorRelayoutListeners = new WeakHashMap();
        nowPlayingPattern = new float[]{-5.5f, 20.0f, 20.0f, 0.35f, -5.5f, -20.0f, 20.0f, 0.35f, -36.0f, -42.0f, 22.0f, 0.375f, -36.0f, 0.0f, 25.0f, 0.425f, -36.0f, 42.0f, 22.0f, 0.375f, -70.0f, 22.0f, 23.0f, 0.35f, -70.0f, -22.0f, 23.0f, 0.35f, -99.0f, 46.0f, 21.0f, 0.275f, -99.0f, 0.0f, 22.0f, 0.325f, -99.0f, -46.0f, 21.0f, 0.275f, -128.0f, -23.0f, 20.0f, 0.225f, -128.0f, 23.0f, 20.0f, 0.225f};
    }

    private UIUtil() {
    }

    public static /* synthetic */ int adjustHsl$default(UIUtil r0, int r1, float r2, float r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = -1.0f;
    L6:
        return r0.adjustHsl(r1, r2, r3);
    }

    @JvmStatic
    @JvmOverloads
    public static final void applyScaleStateListAnimator(View r12, float r13, boolean r14, boolean r15, int r16, float r17, float r18) {
        WeakHashMap<View, View.OnLayoutChangeListener> r0 = scaleAnimatorRelayoutListeners;
        View.OnLayoutChangeListener r1 = r0.remove(r12);
        if (r1 == null) goto L5;
        r12.removeOnLayoutChangeListener(r1);
    L5:
        StateListAnimator r19 = r12.getStateListAnimator();
        if (r19 == null) goto L8;
        r19.jumpToCurrentState();
    L8:
        UIUtil$$ExternalSyntheticLambda0 r2 = new UIUtil$$ExternalSyntheticLambda0(r12.getWidth(), r12.getHeight(), r13, r14, r15, r16, r17, r18);
        r12.addOnLayoutChangeListener(r2);
        r0.put(r12, r2);
        float r02 = Math.max(r3, r4) / AndroidUtilities.density;
        float r110 = 0.0f;
        if (r02 <= 0.0f) goto L11;
        float r7 = Math.min(r17, 8.0f / r02);
    L12:
        UIUtil$$ExternalSyntheticLambda1 r5 = new UIUtil$$ExternalSyntheticLambda1(r12, r7, r13, r16, r14, r15);
        ScaleStateListAnimator.apply(r12, r7, r18, new UIUtil$$ExternalSyntheticLambda2(r5), new UIUtil$$ExternalSyntheticLambda3(r5));
        StateListAnimator r132 = r12.getStateListAnimator();
        if (r132 == null) goto L16;
        r132.jumpToCurrentState();
    L16:
        if (r12.isPressed() == false) goto L18;
        r110 = 1.0f;
    L18:
        r5.invoke(Float.valueOf(r110));
        return;
    L11:
        r7 = r17;
        goto L12
    }

    @JvmStatic
    public static final GradientDrawable createBottomFade(int r2) {
        return INSTANCE.createFade(r2, GradientDrawable.Orientation.TOP_BOTTOM);
    }

    @JvmStatic
    @JvmOverloads
    public static final Drawable createFabBackground(int r2, int r3, int r4) {
        if (r2 != 40) goto L5;
        int r32 = Theme.key_windowBackgroundWhite;
        Pair r33 = TuplesKt.to(Integer.valueOf(ColorUtils.blendARGB(Theme.getColor(r32), -1, 0.1f)), Integer.valueOf(Theme.blendOver(Theme.getColor(r32), Theme.getColor(Theme.key_listSelector))));
    L6:
        int r42 = ((Number) r33.component1()).intValue();
        int r34 = ((Number) r33.component2()).intValue();
        if (ExteraConfig.getSquareFab() == false) goto L9;
        float r22 = getFabSquareCornerRadiusDp(r2);
    L11:
        return Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(r22), r42, r34);
    L9:
        r22 = r2 / 2.0f;
        goto L11
    L5:
        r33 = TuplesKt.to(Integer.valueOf(r3), Integer.valueOf(r4));
        goto L6
    }

    private final GradientDrawable createFade(int r3, GradientDrawable.Orientation r4) {
        return new GradientDrawable(r4, new int[]{ColorUtils.setAlphaComponent(r3, 0), ColorUtils.setAlphaComponent(r3, 60), r3});
    }

    @JvmStatic
    public static final GradientDrawable createTopFade(int r2) {
        return INSTANCE.createFade(r2, GradientDrawable.Orientation.BOTTOM_TOP);
    }

    @JvmStatic
    public static final Bitmap drawableToBitmap(Drawable r3, int r4, int r5) {
        Bitmap r42 = Bitmap.createBitmap(r4, r5, Bitmap.Config.ARGB_8888);
        Canvas r52 = new Canvas(r42);
        r3.setBounds(0, 0, r52.getWidth(), r52.getHeight());
        r3.draw(r52);
        return r42;
    }

    @JvmStatic
    public static final float getFabSquareCornerRadiusDp(int r2) {
        return (float) Math.ceil((r2 * 16) / 56.0f);
    }

    public final int adjustHsl(int r3, float r4, float r5) {
        float[] r2 = new float[3];
        ColorUtils.colorToHSL(r3, r2);
        if (r5 <= 0.0f) goto L5;
        r2[1] = RangesKt.coerceAtMost(r2[1] * r5, 1.0f);
    L5:
        r2[2] = RangesKt.coerceAtMost(r2[2] * r4, 1.0f);
        return ColorUtils.HSLToColor(r2);
    }

    public final void drawNowPlayingPattern(Canvas r10, Drawable r11, float r12, float r13, float r14) {
        if (r14 <= 0.0f) goto L9;
        int r9 = 0;
    L6:
        float[] r0 = nowPlayingPattern;
        if (r9 >= r0.length) goto L11;
        float r1 = r0[r9];
        float r2 = r0[r9 + 1];
        float r3 = r0[r9 + 2];
        float r02 = r0[r9 + 3];
        int r4 = (int) ((AndroidUtilities.dpf2(r1) + r12) - (AndroidUtilities.dpf2(r3) / 2.0f));
        float r5 = r13 / 2.0f;
        r11.setBounds(r4, (int) ((AndroidUtilities.dpf2(r2) + r5) - (AndroidUtilities.dpf2(r3) / 2.0f)), (int) ((AndroidUtilities.dpf2(r1) + r12) + (AndroidUtilities.dpf2(r3) / 2.0f)), (int) ((r5 + AndroidUtilities.dpf2(r2)) + (AndroidUtilities.dpf2(r3) / 2.0f)));
        r11.setAlpha((int) ((255.0f * r14) * r02));
        r11.draw(r10);
        r9 = r9 + 4;
        goto L6
    L11:
        return;
    }
}
