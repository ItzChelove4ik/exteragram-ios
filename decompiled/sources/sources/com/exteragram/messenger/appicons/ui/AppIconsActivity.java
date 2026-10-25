package com.exteragram.messenger.appicons.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.appicons.AppIcon;
import com.exteragram.messenger.appicons.AppIconController;
import com.exteragram.messenger.appicons.AppIconPreviewLoader;
import com.exteragram.messenger.appicons.ui.components.AppIconBulletinLayout;
import com.exteragram.messenger.appicons.ui.components.AppIconCell;
import com.exteragram.messenger.appicons.ui.components.AppIconHeroView;
import com.exteragram.messenger.utils.ui.ChatHeaderUiHelper;
import com.exteragram.messenger.utils.ui.UIUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.EdgeToEdgeSupportMode;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes4.dex */
public class AppIconsActivity extends BaseFragment {
    private AppIcon appliedIcon;
    private int backgroundColor;
    private int bottomInset;
    private ButtonWithCounterView button;
    private FrameLayout buttonContainer;
    private boolean buttonFaded;
    private float collapse;
    private ValueAnimator colorAnimator;
    private ContentView contentView;
    private Insets cutout;
    private BlurredBackgroundSourceColor glassSource;
    private int heroHeight;
    private AppIconHeroView heroView;
    private int leftInset;
    private UniversalRecyclerView listView;
    private int paneWidth;
    private AppIcon previewIcon;
    private int rightInset;
    private int scrolled;
    private float topFade;
    private boolean twoPane;

    /* renamed from: com.exteragram.messenger.appicons.ui.AppIconsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ AppIconsActivity this$0;

        public AnonymousClass1(AppIconsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.appicons.ui.AppIconsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.OnScrollListener {
        final /* synthetic */ AppIconsActivity this$0;

        public AnonymousClass2(AppIconsActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            AppIconsActivity.m1128$$Nest$mupdateCollapse(this.this$0, r3);
        }
    }

    /* renamed from: com.exteragram.messenger.appicons.ui.AppIconsActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ AppIconsActivity this$0;
        final /* synthetic */ int val$color;

        public AnonymousClass3(AppIconsActivity r1, int r2) {
            this.this$0 = r1;
            this.val$color = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            AppIconsActivity.m1117$$Nest$fputbackgroundColor(this.this$0, this.val$color);
            AppIconsActivity.m1130$$Nest$mupdateStatusBar(this.this$0);
        }
    }

    public class ContentView extends FrameLayout implements Theme.Colorable {
        private int glowColor;
        private float glowCx;
        private float glowCy;
        private int glowHeight;
        private final Paint glowPaint;
        private int glowWidth;
        private boolean heroLinkPressed;
        private final Paint panelPaint;
        private final Path panelPath;
        private final float[] panelRadii;
        final /* synthetic */ AppIconsActivity this$0;
        private int topFadeColor;
        private Drawable topFadeDrawable;

        public static /* synthetic */ void $r8$lambda$VxbAo0vmS5dtJ4IaA9UxvSTsBHw(AppIconsActivity r0) {
            AppIconsActivity.m1129$$Nest$mupdateListPadding(r0);
        }

        public ContentView(AppIconsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.glowPaint = new Paint(1);
            this.panelPaint = new Paint(1);
            this.panelPath = new Path();
            this.panelRadii = new float[8];
            setWillNotDraw(false);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r4) {
            if (AppIconsActivity.m1109$$Nest$fgetheroView(this.this$0) == null) goto L18;
            int r0 = r4.getActionMasked();
            if (r0 != 0) goto L10;
            boolean r02 = AppIconsActivity.m1109$$Nest$fgetheroView(this.this$0).dispatchLinkTouch(r4);
            this.heroLinkPressed = r02;
            if (r02 == false) goto L18;
            return true;
        L10:
            if (this.heroLinkPressed == false) goto L18;
            if (r0 != 1) goto L13;
        L14:
            this.heroLinkPressed = false;
        L15:
            AppIconsActivity.m1109$$Nest$fgetheroView(this.this$0).dispatchLinkTouch(r4);
            return true;
        L13:
            if (r0 != 3) goto L15;
        L18:
            return super.dispatchTouchEvent(r4);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r7, View r8, long r9) {
            if (r8 != AppIconsActivity.m1111$$Nest$fgetlistView(this.this$0)) goto L5;
            float r0 = AndroidUtilities.dp(20.0f);
            Arrays.fill(this.panelRadii, 0.0f);
            boolean r1 = AppIconsActivity.m1116$$Nest$fgettwoPane(this.this$0);
            float[] r3 = this.panelRadii;
            if (r1 == false) goto L9;
            r3[7] = r0;
            r3[6] = r0;
            r3[1] = r0;
            r3[0] = r0;
            AndroidUtilities.rectTmp.set(AppIconsActivity.m1112$$Nest$fgetpaneWidth(this.this$0), 0.0f, getWidth(), getHeight());
        L10:
            this.panelPath.rewind();
            Path r02 = this.panelPath;
            RectF r12 = AndroidUtilities.rectTmp;
            r02.addRoundRect(r12, this.panelRadii, Path.Direction.CW);
            float r03 = r12.top;
            Paint r13 = this.panelPaint;
            AppIconsActivity r32 = this.this$0;
            int r4 = Theme.key_windowBackgroundWhite;
            r13.setColor(r32.getThemedColor(r4));
            r7.drawPath(this.panelPath, this.panelPaint);
            r7.save();
            r7.clipPath(this.panelPath);
            boolean r82 = super.drawChild(r7, r8, r9);
            if (AppIconsActivity.m1115$$Nest$fgettopFade(this.this$0) <= 0.0f) goto L18;
            int r92 = this.this$0.getThemedColor(r4);
            if (this.topFadeDrawable != null) goto L15;
        L16:
            this.topFadeColor = r92;
            this.topFadeDrawable = UIUtil.createTopFade(r92);
        L17:
            int r10 = (int) r03;
            this.topFadeDrawable.setBounds(0, r10, getWidth(), AndroidUtilities.dp(24.0f) + r10);
            this.topFadeDrawable.setAlpha((int) (AppIconsActivity.m1115$$Nest$fgettopFade(this.this$0) * 255.0f));
            this.topFadeDrawable.draw(r7);
            goto L18
        L15:
            if (this.topFadeColor == r92) goto L17;
        L18:
            r7.restore();
            return r82;
        L9:
            r3[3] = r0;
            r3[2] = r0;
            r3[1] = r0;
            r3[0] = r0;
            AndroidUtilities.rectTmp.set(0.0f, (AppIconsActivity.m1111$$Nest$fgetlistView(this.this$0).getY() + AppIconsActivity.m1108$$Nest$fgetheroHeight(this.this$0)) - (AppIconsActivity.m1123$$Nest$mgetCollapseRange(this.this$0) * AppIconsActivity.m1107$$Nest$fgetcollapse(this.this$0)), getWidth(), getHeight());
            goto L10
        L5:
            return super.drawChild(r7, r8, r9);
        }

        @Override // android.view.View
        public void onDraw(Canvas r11) {
            r11.drawColor(AppIconsActivity.m1122$$Nest$mgetBaseColor(this.this$0));
            if (AppIconsActivity.m1103$$Nest$fgetbackgroundColor(this.this$0) != 0) goto L5;
            return;
        L5:
            if (AppIconsActivity.m1107$$Nest$fgetcollapse(this.this$0) < 1.0f) goto L8;
            return;
        L8:
            if (AppIconsActivity.m1116$$Nest$fgettwoPane(this.this$0) == false) goto L11;
            int r0 = AppIconsActivity.m1110$$Nest$fgetleftInset(this.this$0) + AppIconsActivity.m1112$$Nest$fgetpaneWidth(this.this$0);
        L10:
            float r02 = r0 / 2.0f;
            float r5 = (AppIconsActivity.m1109$$Nest$fgetheroView(this.this$0).getTop() + AppIconsActivity.m1109$$Nest$fgetheroView(this.this$0).getPaddingTop()) + (AppIconsActivity.m1109$$Nest$fgetheroView(this.this$0).getPreviewSize() / 2.0f);
            if (this.glowColor != AppIconsActivity.m1103$$Nest$fgetbackgroundColor(this.this$0)) goto L23;
            if (this.glowWidth != getWidth()) goto L23;
            if (this.glowHeight != getHeight()) goto L23;
            if (this.glowCx != r02) goto L23;
            if (this.glowCy != r5) goto L23;
        L30:
            this.glowPaint.setAlpha((int) ((1.0f - AppIconsActivity.m1107$$Nest$fgetcollapse(this.this$0)) * 255.0f));
            r11.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.glowPaint);
            return;
        L23:
            if (AppIconsActivity.m1116$$Nest$fgettwoPane(this.this$0) == false) goto L25;
            int r03 = AppIconsActivity.m1112$$Nest$fgetpaneWidth(this.this$0);
        L26:
            float r6 = Math.max(r03, AppIconsActivity.m1108$$Nest$fgetheroHeight(this.this$0)) * 0.9f;
            if (r6 <= 0.0f) goto L34;
            this.glowColor = AppIconsActivity.m1103$$Nest$fgetbackgroundColor(this.this$0);
            this.glowWidth = getWidth();
            this.glowHeight = getHeight();
            this.glowCx = r02;
            this.glowCy = r5;
            this.glowPaint.setShader(new RadialGradient(r02, r5, r6, new int[]{ColorUtils.setAlphaComponent(AppIconsActivity.m1103$$Nest$fgetbackgroundColor(this.this$0), 200), ColorUtils.setAlphaComponent(AppIconsActivity.m1103$$Nest$fgetbackgroundColor(this.this$0), 0)}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
            goto L30
        L34:
            return;
        L25:
            r03 = getWidth();
            goto L26
        L11:
            r0 = getWidth();
            goto L10
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
            if (AppIconsActivity.m1116$$Nest$fgettwoPane(this.this$0) == true) goto L6;
            super.onLayout(r3, r4, r5, r6, r7);
            AppIconsActivity.m1128$$Nest$mupdateCollapse(this.this$0, 0);
            return;
        L6:
            int r62 = r6 - r4;
            int r72 = r7 - r5;
            AppIconsActivity.access$500(this.this$0).layout(AppIconsActivity.m1110$$Nest$fgetleftInset(this.this$0), 0, AppIconsActivity.m1110$$Nest$fgetleftInset(this.this$0) + AppIconsActivity.access$300(this.this$0).getMeasuredWidth(), AppIconsActivity.access$400(this.this$0).getMeasuredHeight());
            AppIconsActivity.m1111$$Nest$fgetlistView(this.this$0).layout(AppIconsActivity.m1112$$Nest$fgetpaneWidth(this.this$0), 0, r62, r72);
            int r32 = AppIconsActivity.m1105$$Nest$fgetbuttonContainer(this.this$0).getMeasuredHeight();
            int r33 = r72 - r32;
            AppIconsActivity.m1105$$Nest$fgetbuttonContainer(this.this$0).layout(AppIconsActivity.m1110$$Nest$fgetleftInset(this.this$0), r33, AppIconsActivity.m1110$$Nest$fgetleftInset(this.this$0) + AppIconsActivity.m1105$$Nest$fgetbuttonContainer(this.this$0).getMeasuredWidth(), r72);
            int r42 = AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight();
            int r43 = r42 + Math.max(0, ((r33 - r42) - AppIconsActivity.m1108$$Nest$fgetheroHeight(this.this$0)) / 2);
            AppIconsActivity.m1109$$Nest$fgetheroView(this.this$0).layout(AppIconsActivity.m1110$$Nest$fgetleftInset(this.this$0), r43, AppIconsActivity.m1110$$Nest$fgetleftInset(this.this$0) + AppIconsActivity.m1109$$Nest$fgetheroView(this.this$0).getMeasuredWidth(), AppIconsActivity.m1108$$Nest$fgetheroHeight(this.this$0) + r43);
            AppIconsActivity.m1128$$Nest$mupdateCollapse(this.this$0, 0);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            int r0 = View.MeasureSpec.getSize(r9);
            int r1 = View.MeasureSpec.getSize(r10);
            boolean r2 = AppIconsActivity.m1116$$Nest$fgettwoPane(this.this$0);
            AppIconsActivity.m1121$$Nest$fputtwoPane(this.this$0, AppIconsActivity.m1131$$Nest$smisTwoPane(r0, r1));
            AppIconsActivity r3 = this.this$0;
            if (AppIconsActivity.m1116$$Nest$fgettwoPane(r3) == false) goto L5;
            int r4 = AppIconsActivity.m1132$$Nest$smpaneWidthFor(r0);
        L6:
            AppIconsActivity.m1120$$Nest$fputpaneWidth(r3, r4);
            boolean r32 = AppIconsActivity.m1116$$Nest$fgettwoPane(this.this$0);
            AppIconsActivity r42 = this.this$0;
            if (r32 == false) goto L9;
            AppIconsActivity.m1109$$Nest$fgetheroView(r42).setPadding(0, 0, 0, 0);
        L10:
            AppIconsActivity.m1109$$Nest$fgetheroView(this.this$0).setPreviewSizeDp(AppIconsActivity.m1125$$Nest$mgetPreviewSizeDp(this.this$0, r1));
            FrameLayout r33 = AppIconsActivity.m1105$$Nest$fgetbuttonContainer(this.this$0);
            if (AppIconsActivity.m1116$$Nest$fgettwoPane(this.this$0) == false) goto L13;
            int r43 = 0;
        L15:
            if (AppIconsActivity.m1116$$Nest$fgettwoPane(this.this$0) == false) goto L17;
            int r6 = 0;
        L18:
            r33.setPadding(r43, 0, r6, AppIconsActivity.m1104$$Nest$fgetbottomInset(this.this$0));
            if (AppIconsActivity.m1106$$Nest$fgetbuttonFaded(this.this$0) == (!AppIconsActivity.m1116$$Nest$fgettwoPane(this.this$0))) goto L25;
            AppIconsActivity.m1118$$Nest$fputbuttonFaded(this.this$0, !AppIconsActivity.m1116$$Nest$fgettwoPane(r3));
            FrameLayout r34 = AppIconsActivity.m1105$$Nest$fgetbuttonContainer(this.this$0);
            if (AppIconsActivity.m1106$$Nest$fgetbuttonFaded(this.this$0) == false) goto L23;
            GradientDrawable r44 = UIUtil.createBottomFade(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
        L24:
            r34.setBackground(r44);
            goto L25
        L23:
            r44 = null;
        L25:
            AppIconsActivity r35 = this.this$0;
            int r36 = AppIconsActivity.m1126$$Nest$mgetSpanCount(r35, AppIconsActivity.m1124$$Nest$mgetGridWidth(r35, r0, r1));
            if (AppIconsActivity.m1111$$Nest$fgetlistView(this.this$0).getSpanCount() == r36) goto L29;
            AppIconsActivity.m1111$$Nest$fgetlistView(this.this$0).setSpanCount(r36);
        L29:
            if (AppIconsActivity.m1116$$Nest$fgettwoPane(this.this$0) == true) goto L31;
            super.onMeasure(r9, r10);
        L32:
            int r92 = AppIconsActivity.m1109$$Nest$fgetheroView(this.this$0).getMeasuredHeight();
            if (r92 > 0) goto L35;
            return;
        L35:
            if (r92 == AppIconsActivity.m1108$$Nest$fgetheroHeight(this.this$0)) goto L37;
        L38:
            AppIconsActivity.m1119$$Nest$fputheroHeight(this.this$0, r92);
            AndroidUtilities.runOnUIThread(new AppIconsActivity$ContentView$$ExternalSyntheticLambda0(this.this$0));
            return;
        L37:
            if (r2 != AppIconsActivity.m1116$$Nest$fgettwoPane(this.this$0)) goto L38;
            return;
        L31:
            int r93 = View.MeasureSpec.makeMeasureSpec(AppIconsActivity.m1112$$Nest$fgetpaneWidth(this.this$0) - AppIconsActivity.m1110$$Nest$fgetleftInset(this.this$0), TLObject.FLAG_30);
            int r37 = View.MeasureSpec.makeMeasureSpec(r1, Integer.MIN_VALUE);
            AppIconsActivity.access$000(this.this$0).measure(r93, r37);
            AppIconsActivity.m1105$$Nest$fgetbuttonContainer(this.this$0).measure(r93, r37);
            AppIconsActivity.m1109$$Nest$fgetheroView(this.this$0).measure(r93, r37);
            AppIconsActivity.m1111$$Nest$fgetlistView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r0 - AppIconsActivity.m1112$$Nest$fgetpaneWidth(this.this$0), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30));
            setMeasuredDimension(r0, r1);
            goto L32
        L17:
            r6 = AppIconsActivity.m1114$$Nest$fgetrightInset(this.this$0);
            goto L18
        L13:
            r43 = AppIconsActivity.m1110$$Nest$fgetleftInset(this.this$0);
            goto L15
        L9:
            AppIconsActivity.m1109$$Nest$fgetheroView(r42).setPadding(0, AndroidUtilities.statusBarHeight + AndroidUtilities.dp(28.0f), 0, AndroidUtilities.dp(40.0f));
            goto L10
        L5:
            r4 = r0;
            goto L6
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            AppIconsActivity r0 = this.this$0;
            AppIconsActivity.m1117$$Nest$fputbackgroundColor(r0, AppIconPreviewLoader.getAccentTint(AppIconsActivity.m1113$$Nest$fgetpreviewIcon(r0)));
            AppIconsActivity.access$100(this.this$0).setItemsColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlackText), false);
            AppIconsActivity.access$200(this.this$0).setItemsBackgroundColor(this.this$0.getThemedColor(Theme.key_listSelector), false);
            if (AppIconsActivity.m1106$$Nest$fgetbuttonFaded(this.this$0) == false) goto L5;
            AppIconsActivity.m1105$$Nest$fgetbuttonContainer(this.this$0).setBackground(UIUtil.createBottomFade(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite)));
        L5:
            AppIconsActivity.m1127$$Nest$minvalidateHeader(this.this$0);
        }
    }

    public static /* synthetic */ void $r8$lambda$4dj22e82XBXlCeF6CGfYnsdFUWI(AppIconsActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onItemClick(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$8m0fA0i-NYVZPB5jN0Cz6AN8qY0, reason: not valid java name */
    public static /* synthetic */ void m1102$r8$lambda$8m0fA0iNYVZPB5jN0Cz6AN8qY0(AppIconsActivity r0, View r1) {
        r0.lambda$createView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$MFj9XffOvXPdaF8RIpzAFpVAHLY(AppIconsActivity r0, View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        r0.lambda$createView$1(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$QrANRZKcT7nJzSaGvtR1GaLUQ_E(AppIconsActivity r0) {
        r0.applyAccent();
    }

    public static /* synthetic */ void $r8$lambda$esv9szpVo7ie9KUo8mcOorlUazk(AppIconsActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$mVqiqKsVPuA0GZXntromFEpd73o(AppIconsActivity r0, int r1, int r2, ValueAnimator r3) {
        r0.lambda$animateBackgroundColor$2(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetbackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1103$$Nest$fgetbackgroundColor(AppIconsActivity r0) {
        return r0.backgroundColor;
    }

    /* renamed from: -$$Nest$fgetbottomInset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1104$$Nest$fgetbottomInset(AppIconsActivity r0) {
        return r0.bottomInset;
    }

    /* renamed from: -$$Nest$fgetbuttonContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m1105$$Nest$fgetbuttonContainer(AppIconsActivity r0) {
        return r0.buttonContainer;
    }

    /* renamed from: -$$Nest$fgetbuttonFaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1106$$Nest$fgetbuttonFaded(AppIconsActivity r0) {
        return r0.buttonFaded;
    }

    /* renamed from: -$$Nest$fgetcollapse, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m1107$$Nest$fgetcollapse(AppIconsActivity r0) {
        return r0.collapse;
    }

    /* renamed from: -$$Nest$fgetheroHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1108$$Nest$fgetheroHeight(AppIconsActivity r0) {
        return r0.heroHeight;
    }

    /* renamed from: -$$Nest$fgetheroView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AppIconHeroView m1109$$Nest$fgetheroView(AppIconsActivity r0) {
        return r0.heroView;
    }

    /* renamed from: -$$Nest$fgetleftInset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1110$$Nest$fgetleftInset(AppIconsActivity r0) {
        return r0.leftInset;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalRecyclerView m1111$$Nest$fgetlistView(AppIconsActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetpaneWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1112$$Nest$fgetpaneWidth(AppIconsActivity r0) {
        return r0.paneWidth;
    }

    /* renamed from: -$$Nest$fgetpreviewIcon, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AppIcon m1113$$Nest$fgetpreviewIcon(AppIconsActivity r0) {
        return r0.previewIcon;
    }

    /* renamed from: -$$Nest$fgetrightInset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1114$$Nest$fgetrightInset(AppIconsActivity r0) {
        return r0.rightInset;
    }

    /* renamed from: -$$Nest$fgettopFade, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m1115$$Nest$fgettopFade(AppIconsActivity r0) {
        return r0.topFade;
    }

    /* renamed from: -$$Nest$fgettwoPane, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1116$$Nest$fgettwoPane(AppIconsActivity r0) {
        return r0.twoPane;
    }

    /* renamed from: -$$Nest$fputbackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1117$$Nest$fputbackgroundColor(AppIconsActivity r0, int r1) {
        r0.backgroundColor = r1;
    }

    /* renamed from: -$$Nest$fputbuttonFaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1118$$Nest$fputbuttonFaded(AppIconsActivity r0, boolean r1) {
        r0.buttonFaded = r1;
    }

    /* renamed from: -$$Nest$fputheroHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1119$$Nest$fputheroHeight(AppIconsActivity r0, int r1) {
        r0.heroHeight = r1;
    }

    /* renamed from: -$$Nest$fputpaneWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1120$$Nest$fputpaneWidth(AppIconsActivity r0, int r1) {
        r0.paneWidth = r1;
    }

    /* renamed from: -$$Nest$fputtwoPane, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1121$$Nest$fputtwoPane(AppIconsActivity r0, boolean r1) {
        r0.twoPane = r1;
    }

    /* renamed from: -$$Nest$mgetBaseColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1122$$Nest$mgetBaseColor(AppIconsActivity r0) {
        return r0.getBaseColor();
    }

    /* renamed from: -$$Nest$mgetCollapseRange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1123$$Nest$mgetCollapseRange(AppIconsActivity r0) {
        return r0.getCollapseRange();
    }

    /* renamed from: -$$Nest$mgetGridWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1124$$Nest$mgetGridWidth(AppIconsActivity r0, int r1, int r2) {
        return r0.getGridWidth(r1, r2);
    }

    /* renamed from: -$$Nest$mgetPreviewSizeDp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1125$$Nest$mgetPreviewSizeDp(AppIconsActivity r0, int r1) {
        return r0.getPreviewSizeDp(r1);
    }

    /* renamed from: -$$Nest$mgetSpanCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1126$$Nest$mgetSpanCount(AppIconsActivity r0, int r1) {
        return r0.getSpanCount(r1);
    }

    /* renamed from: -$$Nest$minvalidateHeader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1127$$Nest$minvalidateHeader(AppIconsActivity r0) {
        r0.invalidateHeader();
    }

    /* renamed from: -$$Nest$mupdateCollapse, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1128$$Nest$mupdateCollapse(AppIconsActivity r0, int r1) {
        r0.updateCollapse(r1);
    }

    /* renamed from: -$$Nest$mupdateListPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1129$$Nest$mupdateListPadding(AppIconsActivity r0) {
        r0.updateListPadding();
    }

    /* renamed from: -$$Nest$mupdateStatusBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1130$$Nest$mupdateStatusBar(AppIconsActivity r0) {
        r0.updateStatusBar();
    }

    /* renamed from: -$$Nest$smisTwoPane, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1131$$Nest$smisTwoPane(int r0, int r1) {
        return isTwoPane(r0, r1);
    }

    /* renamed from: -$$Nest$smpaneWidthFor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1132$$Nest$smpaneWidthFor(int r0) {
        return paneWidthFor(r0);
    }

    public AppIconsActivity() {
        this.cutout = Insets.NONE;
        this.buttonFaded = true;
    }

    public static /* synthetic */ ActionBar access$000(AppIconsActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(AppIconsActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$200(AppIconsActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$300(AppIconsActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$400(AppIconsActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$500(AppIconsActivity r0) {
        return r0.actionBar;
    }

    private void animateBackgroundColor(int r5) {
        if (this.backgroundColor != r5) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.colorAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
        this.colorAnimator = null;
    L8:
        int r02 = this.backgroundColor;
        if (r02 != 0) goto L12;
        this.backgroundColor = r5;
        invalidateHeader();
        updateStatusBar();
        return;
    L12:
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(280);
        this.colorAnimator = r1;
        r1.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.colorAnimator.addUpdateListener(new AppIconsActivity$$ExternalSyntheticLambda5(this, r02, r5));
        this.colorAnimator.addListener(new AnonymousClass3(this, r5));
        this.colorAnimator.start();
    }

    private void applyAccent() {
        AppIcon r0 = this.previewIcon;
        if (r0 == null) goto L10;
        int r02 = AppIconPreviewLoader.getAccentTint(r0);
        if (r02 == 0) goto L9;
        animateBackgroundColor(r02);
        return;
    L9:
        return;
    }

    private void fillItems(ArrayList<UItem> r3, UniversalAdapter r4) {
        List<AppIcon> r42 = AppIconController.getAvailableIcons();
        AppIcon r0 = this.previewIcon;
        if (r0 != null) goto L5;
    L6:
        AppIcon r02 = AppIconController.getSelectedIcon();
        this.appliedIcon = r02;
        this.previewIcon = r02;
    L7:
        Iterator<AppIcon> r43 = r42.iterator();
    L9:
        if (r43.hasNext() == false) goto L15;
        AppIcon r03 = r43.next();
        if (r03 != this.previewIcon) goto L13;
        boolean r1 = true;
    L14:
        r3.add(AppIconCell.Factory.asAppIcon(r03, r1));
        goto L9
    L13:
        r1 = false;
        goto L14
    L15:
        return;
    L5:
        if (r42.contains(r0) == true) goto L7;
        goto L6
    }

    private int getBaseColor() {
        int r0 = getThemedColor(Theme.key_windowBackgroundGray);
        int r2 = this.backgroundColor;
        if (r2 != 0) goto L6;
        return r0;
    L6:
        return ColorUtils.blendARGB(r0, r2, 0.25f);
    }

    private int getCollapseRange() {
        if (this.twoPane == false) goto L6;
        return 0;
    L6:
        return Math.max(0, (this.heroHeight - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.statusBarHeight);
    }

    private int getGridWidth(int r1, int r2) {
        if (isTwoPane(r1, r2) == false) goto L7;
        int r12 = r1 - paneWidthFor(r1);
        int r0 = this.rightInset;
    L6:
        return r12 - r0;
    L7:
        r12 = r1 - this.leftInset;
        r0 = this.rightInset;
        goto L6
    }

    private int getPreviewSizeDp(int r3) {
        if (this.heroView != null) goto L6;
        return 128;
    L6:
        if (this.twoPane == false) goto L9;
        int r32 = ((r3 - AndroidUtilities.statusBarHeight) - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.dp(72.0f);
        int r0 = this.bottomInset;
    L8:
        int r33 = r32 - r0;
        return Math.max(64, Math.min(128, (int) ((r33 - this.heroView.getTextBlockHeight()) / AndroidUtilities.density)));
    L9:
        r32 = ((int) (r3 * 0.45f)) - AndroidUtilities.statusBarHeight;
        r0 = AndroidUtilities.dp(68.0f);
        goto L8
    }

    private int getSpanCount(int r3) {
        float r2 = r3 - (AndroidUtilities.dp(8.0f) * 2);
        int r32 = Math.max(3, Math.min(8, Math.round(r2 / AndroidUtilities.dp(100.0f))));
        if (r32 < 8) goto L5;
        return r32;
    L5:
        if ((r2 / r32) > AndroidUtilities.dp(112.0f)) goto L7;
        return r32;
    L7:
        return r32 + 1;
    }

    private int getTopColor() {
        if (this.backgroundColor != 0) goto L7;
        return getBaseColor();
    L7:
        return ColorUtils.blendARGB(getBaseColor(), this.backgroundColor, 0.7f);
    }

    private void invalidateHeader() {
        this.contentView.invalidate();
        BlurredBackgroundSourceColor r0 = this.glassSource;
        if (r0 == null) goto L6;
        r0.setColor(getTopColor());
        this.actionBar.invalidate();
        return;
    }

    private static boolean isTwoPane(int r0, int r1) {
        if (r0 > r1) goto L4;
        return false;
    L4:
        if (r0 < AndroidUtilities.dp(560.0f)) goto L9;
        return true;
    L9:
        return false;
    }

    private /* synthetic */ void lambda$animateBackgroundColor$2(int r1, int r2, ValueAnimator r3) {
        this.backgroundColor = ColorUtils.blendARGB(r1, r2, ((Float) r3.getAnimatedValue()).floatValue());
        invalidateHeader();
    }

    private /* synthetic */ void lambda$createView$0(View r1) {
        onButtonClick();
    }

    private /* synthetic */ void lambda$createView$1(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        updateListPadding();
    }

    private void onButtonClick() {
        AppIcon r0 = this.previewIcon;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0 != this.appliedIcon) goto L9;
        finishFragment();
        return;
    L9:
        AppIconController.setIcon(r0);
        this.appliedIcon = this.previewIcon;
        updateButton(true);
        Bulletin.make(this, new AppIconBulletinLayout(getContext(), this.appliedIcon, getResourceProvider()), 1500).show();
    }

    private void onItemClick(UItem r1, View r2, int r3, float r4, float r5) {
        Object r12 = r1.object;
        if ((r12 instanceof AppIcon) == false) goto L9;
        AppIcon r13 = (AppIcon) r12;
        if (r13 == this.previewIcon) goto L10;
        this.previewIcon = r13;
        this.heroView.set(r13);
        applyAccent();
        updateButton(true);
        this.listView.adapter.update(true);
        return;
    L10:
        return;
    }

    private static int paneWidthFor(int r3) {
        return Math.max(AndroidUtilities.dp(280.0f), Math.min(AndroidUtilities.dp(400.0f), (int) (r3 * 0.42f)));
    }

    private void updateButton(boolean r3) {
        ButtonWithCounterView r0 = this.button;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.previewIcon != this.appliedIcon) goto L8;
        int r2 = R.string.AppIconsKeep;
    L9:
        r0.setText(LocaleController.getString(r2), r3);
        return;
    L8:
        r2 = R.string.AppIconsApply;
        goto L9
    }

    private void updateCollapse(int r5) {
        if (this.listView != null) goto L5;
        return;
    L5:
        if (this.heroView == null) goto L28;
        int r0 = this.scrolled + r5;
        int r1 = 0;
    L9:
        if (r1 >= this.listView.getChildCount()) goto L14;
        View r2 = this.listView.getChildAt(r1);
        if (this.listView.getChildAdapterPosition(r2) == 0) goto L12;
        r1 = r1 + 1;
        goto L9
    L12:
        r0 = this.listView.getPaddingTop() - r2.getTop();
    L14:
        this.scrolled = Math.max(0, r0);
        int r52 = getCollapseRange();
        if (r52 > 0) goto L17;
        float r53 = 0.0f;
    L18:
        float r02 = Math.min(1.0f, this.scrolled / AndroidUtilities.dp(16.0f));
        if (Math.abs(r53 - this.collapse) < 5.0E-4f) goto L21;
    L23:
        this.collapse = r53;
        this.topFade = r02;
        this.heroView.setCollapse(r53);
        this.contentView.invalidate();
        return;
    L21:
        if (Math.abs(r02 - this.topFade) >= 5.0E-4f) goto L23;
        return;
    L17:
        r53 = Math.min(1.0f, this.scrolled / r52);
        goto L18
    }

    private void updateListPadding() {
        if (this.listView != null) goto L5;
        return;
    L5:
        int r1 = AndroidUtilities.dp(8.0f);
        if (this.twoPane == false) goto L8;
        int r2 = 0;
    L9:
        int r12 = r1 + r2;
        int r0 = AndroidUtilities.dp(8.0f) + this.rightInset;
        if (this.twoPane == false) goto L12;
        int r22 = AndroidUtilities.statusBarHeight + AndroidUtilities.dp(10.0f);
        int r5 = this.bottomInset + AndroidUtilities.dp(10.0f);
    L21:
        if (this.listView.getPaddingTop() == r22) goto L23;
    L28:
        this.listView.setPadding(r12, r22, r0, r5);
    L29:
        updateCollapse(0);
        return;
    L23:
        if (this.listView.getPaddingBottom() != r5) goto L28;
        if (this.listView.getPaddingLeft() != r12) goto L28;
        if (this.listView.getPaddingRight() == r0) goto L29;
    L12:
        r22 = this.heroHeight + AndroidUtilities.dp(10.0f);
        FrameLayout r4 = this.buttonContainer;
        if (r4 != null) goto L15;
    L19:
        int r42 = this.bottomInset + AndroidUtilities.dp(72.0f);
    L18:
        r5 = r42;
        goto L21
    L15:
        if (r4.getHeight() <= 0) goto L19;
        r42 = this.buttonContainer.getHeight();
        goto L18
    L8:
        r2 = this.leftInset;
        goto L9
    }

    private void updateStatusBar() {
        if (getParentActivity() == null) goto L6;
        AndroidUtilities.setLightStatusBar(getParentActivity().getWindow(), isLightStatusBar());
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r15) {
        AppIcon r0 = AppIconController.getSelectedIcon();
        this.appliedIcon = r0;
        this.previewIcon = r0;
        this.backgroundColor = AppIconPreviewLoader.getAccentTint(r0);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(false);
        this.actionBar.setCastShadows(false);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setItemsColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText), false);
        this.actionBar.setItemsBackgroundColor(getThemedColor(Theme.key_listSelector), false);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.contentView = new ContentView(this, r15);
        BlurredBackgroundSourceColor r02 = new BlurredBackgroundSourceColor();
        this.glassSource = r02;
        r02.setColor(getTopColor());
        BlurredBackgroundDrawableViewFactory r03 = new BlurredBackgroundDrawableViewFactory(this.glassSource);
        r03.setSourceRootView(new ViewPositionWatcher(this.contentView), this.contentView);
        this.actionBar.setGlassPadding(AndroidUtilities.dp(12.0f));
        this.actionBar.setupGlass(r03, BlurredBackgroundProviderImpl.topPanelChatActivity(getResourceProvider()));
        ChatHeaderUiHelper.applyChatHeaderGlassStyle(this.actionBar, true);
        AppIconHeroView r04 = new AppIconHeroView(r15, this);
        this.heroView = r04;
        r04.setOnPreviewReady(new AppIconsActivity$$ExternalSyntheticLambda0(this));
        this.heroView.set(this.previewIcon);
        this.contentView.addView(this.heroView, LayoutHelper.createFrame(-1, -2, 48));
        UniversalRecyclerView r05 = new UniversalRecyclerView(this, new AppIconsActivity$$ExternalSyntheticLambda1(this), new AppIconsActivity$$ExternalSyntheticLambda2(this), null);
        this.listView = r05;
        Point r3 = AndroidUtilities.displaySize;
        r05.setSpanCount(getSpanCount(getGridWidth(r3.x, r3.y)));
        this.listView.adapter.setApplyBackground(false);
        this.listView.setDrawSelection(false);
        this.listView.setClipToPadding(false);
        this.listView.addOnScrollListener(new AnonymousClass2(this));
        this.contentView.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        ButtonWithCounterView r06 = new ButtonWithCounterView(r15, true, getResourceProvider());
        this.button = r06;
        r06.setRound();
        this.button.setOnClickListener(new AppIconsActivity$$ExternalSyntheticLambda3(this));
        updateButton(false);
        FrameLayout r07 = new FrameLayout(r15);
        this.buttonContainer = r07;
        r07.setBackground(UIUtil.createBottomFade(getThemedColor(Theme.key_windowBackgroundWhite)));
        this.buttonContainer.addView(this.button, LayoutHelper.createFrame(-1, 48.0f, 80, 16.0f, 12.0f, 16.0f, 12.0f));
        this.buttonContainer.addOnLayoutChangeListener(new AppIconsActivity$$ExternalSyntheticLambda4(this));
        this.contentView.addView(this.buttonContainer, LayoutHelper.createFrame(-1, -2, 80));
        this.contentView.addView(this.actionBar);
        ContentView r152 = this.contentView;
        this.fragmentView = r152;
        return r152;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public EdgeToEdgeSupportMode getEdgeToEdgeSupportMode() {
        return EdgeToEdgeSupportMode.FULL;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        return !AndroidUtilities.isDarkColor(getTopColor());
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        ValueAnimator r0 = this.colorAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.colorAnimator = null;
    L5:
        AppIconPreviewLoader.trimAbove(AndroidUtilities.dp(68.0f));
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.bottomInset = r4;
        this.leftInset = Math.max(r1, this.cutout.left);
        this.rightInset = Math.max(r3, this.cutout.right);
        ContentView r12 = this.contentView;
        if (r12 == null) goto L5;
        r12.requestLayout();
    L5:
        updateListPadding();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public WindowInsetsCompat onInsetsInternal(View r2, WindowInsetsCompat r3) {
        this.cutout = r3.getInsets(WindowInsetsCompat.Type.displayCutout());
        return super.onInsetsInternal(r2, r3);
    }
}
