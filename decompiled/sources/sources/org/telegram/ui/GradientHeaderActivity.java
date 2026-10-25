package org.telegram.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FillLastLinearLayoutManager;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.NestedSizeNotifierLayout;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView$$ExternalSyntheticLambda1;
import org.telegram.ui.Components.SimpleThemeDescription;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes6.dex */
public abstract class GradientHeaderActivity extends BaseFragment {
    public BackgroundView backgroundView;
    protected FrameLayout contentView;
    private int currentYOffset;
    private final PremiumGradient.PremiumGradientTools darkGradientTools;
    private int firstViewHeight;
    private final Canvas gradientCanvas;
    private final Bitmap gradientTextureBitmap;
    private final PremiumGradient.PremiumGradientTools gradientTools;
    private final Paint headerBgPaint;
    private boolean inc;
    private boolean isDialogVisible;
    public boolean isLandscapeMode;
    protected LinearLayoutManager layoutManager;
    protected RecyclerListView listView;
    private int minusHeaderHeight;
    protected StarParticlesView particlesView;
    public int particlesViewHeight;
    private float progress;
    private float progressToFull;
    public int savedScrollOffset;
    public int savedScrollPosition;
    private Drawable shadowDrawable;
    public int statusBarHeight;
    private float totalProgress;
    protected boolean useFillLastLayoutManager;
    public boolean whiteBackground;
    protected int yOffset;

    /* renamed from: org.telegram.ui.GradientHeaderActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends PremiumGradient.PremiumGradientTools {
        final /* synthetic */ GradientHeaderActivity this$0;

        public AnonymousClass1(GradientHeaderActivity r1, int r2, int r3, int r4, int r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.Premium.PremiumGradient.PremiumGradientTools
        public int getThemeColorByKey(int r1) {
            return Theme.getDefaultColor(r1);
        }
    }

    /* renamed from: org.telegram.ui.GradientHeaderActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends PremiumGradient.PremiumGradientTools {
        final /* synthetic */ GradientHeaderActivity this$0;

        public AnonymousClass2(GradientHeaderActivity r1, int r2, int r3, int r4, int r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.Premium.PremiumGradient.PremiumGradientTools
        public int getThemeColorByKey(int r1) {
            return Theme.getDefaultColor(r1);
        }
    }

    /* renamed from: org.telegram.ui.GradientHeaderActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends View {
        final /* synthetic */ GradientHeaderActivity this$0;

        public AnonymousClass3(GradientHeaderActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r4, int r5) {
            GradientHeaderActivity r52 = this.this$0;
            if (r52.isLandscapeMode == false) goto L5;
            GradientHeaderActivity.m15032$$Nest$fputfirstViewHeight(r52, (r52.statusBarHeight + GradientHeaderActivity.access$000(r52).getMeasuredHeight()) - AndroidUtilities.dp(16.0f));
        L9:
            GradientHeaderActivity.m15032$$Nest$fputfirstViewHeight(this.this$0, (int) (GradientHeaderActivity.m15022$$Nest$fgetfirstViewHeight(r5) - (this.this$0.yOffset * 2.5f)));
            super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec(GradientHeaderActivity.m15022$$Nest$fgetfirstViewHeight(this.this$0), TLObject.FLAG_30));
            return;
        L5:
            int r53 = AndroidUtilities.dp(140.0f);
            GradientHeaderActivity r0 = this.this$0;
            int r54 = r53 + r0.statusBarHeight;
            if ((r0.backgroundView.getMeasuredHeight() + AndroidUtilities.dp(24.0f)) <= r54) goto L8;
            r54 = Math.max(r54, (this.this$0.backgroundView.getMeasuredHeight() + AndroidUtilities.dp(24.0f)) - GradientHeaderActivity.m15027$$Nest$fgetminusHeaderHeight(this.this$0));
        L8:
            GradientHeaderActivity.m15032$$Nest$fputfirstViewHeight(this.this$0, r54);
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.GradientHeaderActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        final /* synthetic */ GradientHeaderActivity this$0;

        public AnonymousClass4(GradientHeaderActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r3, int r4) {
            super.onScrollStateChanged(r3, r4);
            if (r4 != 0) goto L17;
            int r32 = GradientHeaderActivity.access$100(this.this$0).getBottom() + AndroidUtilities.dp(16.0f);
            float r42 = GradientHeaderActivity.m15030$$Nest$fgettotalProgress(this.this$0);
            GradientHeaderActivity r0 = this.this$0;
            if (r42 <= 0.5f) goto L9;
            r0.listView.smoothScrollBy(0, GradientHeaderActivity.m15021$$Nest$fgetcurrentYOffset(r0) - r32);
            return;
        L9:
            if (r0.listView.getLayoutManager() == null) goto L11;
            View r33 = this.this$0.listView.getLayoutManager().findViewByPosition(0);
        L12:
            if (r33 != null) goto L14;
            return;
        L14:
            if (r33.getTop() >= 0) goto L19;
            this.this$0.listView.smoothScrollBy(0, r33.getTop());
            return;
        L19:
            return;
        L11:
            r33 = null;
            goto L12
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            this.this$0.contentView.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.GradientHeaderActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends BackgroundView {
        final /* synthetic */ GradientHeaderActivity this$0;

        public AnonymousClass5(GradientHeaderActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }
    }

    /* renamed from: org.telegram.ui.GradientHeaderActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ GradientHeaderActivity this$0;

        public AnonymousClass6(GradientHeaderActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.GradientHeaderActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends StarParticlesView {
        final /* synthetic */ GradientHeaderActivity this$0;

        /* renamed from: org.telegram.ui.GradientHeaderActivity$7$1, reason: invalid class name */
        public class AnonymousClass1 extends StarParticlesView.Drawable {
            final /* synthetic */ AnonymousClass7 this$1;

            public AnonymousClass1(AnonymousClass7 r1, int r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.Premium.StarParticlesView.Drawable
            public int getPathColor(int r1) {
                return ColorUtils.setAlphaComponent(Theme.getDefaultColor(this.colorKey), 200);
            }
        }

        public AnonymousClass7(GradientHeaderActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.Premium.StarParticlesView
        public void configure() {
            AnonymousClass1 r0 = new AnonymousClass1(this, 50);
            this.drawable = r0;
            r0.type = 100;
            r0.roundEffect = false;
            r0.useRotate = false;
            r0.useBlur = true;
            r0.checkBounds = true;
            r0.isCircle = false;
            r0.size1 = 4;
            r0.k3 = 0.98f;
            r0.k2 = 0.98f;
            r0.k1 = 0.98f;
            r0.init();
        }

        @Override // org.telegram.ui.Components.Premium.StarParticlesView
        public int getStarsRectWidth() {
            return getMeasuredWidth();
        }
    }

    public static class BackgroundView extends LinearLayout {
        private final FrameLayout aboveTitleLayout;
        private final FrameLayout belowSubTitleLayout;
        public final LinkSpanDrawable.LinksTextView subtitleView;
        private final TextView titleView;

        /* renamed from: -$$Nest$fgetaboveTitleLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m15038$$Nest$fgetaboveTitleLayout(BackgroundView r0) {
            return r0.aboveTitleLayout;
        }

        /* renamed from: -$$Nest$fgetbelowSubTitleLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m15039$$Nest$fgetbelowSubTitleLayout(BackgroundView r0) {
            return r0.belowSubTitleLayout;
        }

        /* renamed from: -$$Nest$fgettitleView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m15040$$Nest$fgettitleView(BackgroundView r0) {
            return r0.titleView;
        }

        public BackgroundView(Context r18) {
            super(r18);
            setOrientation(1);
            FrameLayout r3 = new FrameLayout(r18);
            this.aboveTitleLayout = r3;
            addView(r3, LayoutHelper.createLinear(-1, -2, 1));
            r3.setClipChildren(false);
            setClipChildren(false);
            TextView r32 = new TextView(r18);
            this.titleView = r32;
            r32.setTextSize(1, 22.0f);
            r32.setTypeface(AndroidUtilities.bold());
            r32.setGravity(1);
            addView(r32, LayoutHelper.createLinear(-2, -2, 0.0f, 1, 16, 20, 16, 0));
            LinkSpanDrawable.LinksTextView r33 = new LinkSpanDrawable.LinksTextView(r18);
            this.subtitleView = r33;
            r33.setTextSize(1, 14.0f);
            r33.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            r33.setGravity(1);
            addView(r33, LayoutHelper.createLinear(-1, -2, 0.0f, 1, 24, 7, 24, 0));
            FrameLayout r34 = new FrameLayout(r18);
            this.belowSubTitleLayout = r34;
            addView(r34, LayoutHelper.createLinear(-1, -2, 1));
            r34.setClipChildren(false);
        }

        public void setData(CharSequence r5, CharSequence r6, View r7, View r8) {
            this.titleView.setText(r5);
            this.subtitleView.setText(r6);
            LinkSpanDrawable.LinksTextView r52 = this.subtitleView;
            r52.setMaxWidth(HintView2.cutInFancyHalf(r52.getText(), this.subtitleView.getPaint()));
            FrameLayout r53 = this.aboveTitleLayout;
            if (r7 == null) goto L5;
            r53.removeAllViews();
            this.aboveTitleLayout.addView(r7, LayoutHelper.createFrame(-1, -2, 1));
            this.aboveTitleLayout.setClickable(r7.isClickable());
        L6:
            FrameLayout r54 = this.belowSubTitleLayout;
            if (r8 == null) goto L9;
            r54.removeAllViews();
            this.belowSubTitleLayout.addView(r8, LayoutHelper.createFrame(-1, -2, 1));
            this.belowSubTitleLayout.setClickable(r8.isClickable());
        L10:
            requestLayout();
            return;
        L9:
            r54.setClickable(false);
            goto L10
        L5:
            r53.setClickable(false);
            goto L6
        }
    }

    public class ContentView extends NestedSizeNotifierLayout {
        private final Paint backgroundGradientPaint;
        private final Paint backgroundPaint;
        boolean bottomInterceptedTouch;
        boolean isTouchedActionBarBackButton;
        int lastSize;
        private Boolean lightStatusBar;
        boolean subtitleInterceptedTouch;
        final /* synthetic */ GradientHeaderActivity this$0;
        boolean topInterceptedTouch;

        public ContentView(GradientHeaderActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.backgroundPaint = new Paint(1);
            this.backgroundGradientPaint = new Paint(1);
        }

        private void setLightStatusBar(int r3) {
            if (AndroidUtilities.computePerceivedBrightness(r3) < 0.721f) goto L5;
            boolean r32 = true;
        L6:
            Boolean r0 = this.lightStatusBar;
            if (r0 != null) goto L9;
        L12:
            View r02 = this.this$0.fragmentView;
            this.lightStatusBar = Boolean.valueOf(r32);
            AndroidUtilities.setLightStatusBar(r02, r32);
            return;
        L9:
            if (r0.booleanValue() != r32) goto L12;
            return;
        L5:
            r32 = false;
            goto L6
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r18) {
            GradientHeaderActivity r1 = this.this$0;
            BackgroundView r2 = r1.backgroundView;
            int r3 = 0;
            if (GradientHeaderActivity.m15026$$Nest$fgetisDialogVisible(r1) == true) goto L13;
            boolean r12 = GradientHeaderActivity.m15025$$Nest$fgetinc(this.this$0);
            GradientHeaderActivity r5 = this.this$0;
            if (r12 == false) goto L9;
            GradientHeaderActivity.m15034$$Nest$fputprogress(r5, GradientHeaderActivity.m15028$$Nest$fgetprogress(r5) + 0.016f);
            if (GradientHeaderActivity.m15028$$Nest$fgetprogress(this.this$0) <= 3.0f) goto L13;
            GradientHeaderActivity.m15033$$Nest$fputinc(this.this$0, false);
            goto L13
        L9:
            GradientHeaderActivity.m15034$$Nest$fputprogress(r5, GradientHeaderActivity.m15028$$Nest$fgetprogress(r5) - 0.016f);
            if (GradientHeaderActivity.m15028$$Nest$fgetprogress(this.this$0) >= 1.0f) goto L13;
            GradientHeaderActivity.m15033$$Nest$fputinc(this.this$0, true);
        L13:
            if (this.this$0.listView.getLayoutManager() == null) goto L15;
            View r13 = this.this$0.listView.getLayoutManager().findViewByPosition(0);
        L16:
            GradientHeaderActivity r52 = this.this$0;
            if (r13 == null) goto L20;
            r3 = r13.getBottom();
        L20:
            GradientHeaderActivity.m15031$$Nest$fputcurrentYOffset(r52, r3);
            int r14 = GradientHeaderActivity.access$700(this.this$0).getBottom() + AndroidUtilities.dp(16.0f);
            GradientHeaderActivity.m15036$$Nest$fputtotalProgress(this.this$0, 1.0f - ((GradientHeaderActivity.m15021$$Nest$fgetcurrentYOffset(r5) - r14) / (GradientHeaderActivity.m15022$$Nest$fgetfirstViewHeight(this.this$0) - r14)));
            GradientHeaderActivity r15 = this.this$0;
            float r6 = 0.0f;
            GradientHeaderActivity.m15036$$Nest$fputtotalProgress(r15, Utilities.clamp(GradientHeaderActivity.m15030$$Nest$fgettotalProgress(r15), 1.0f, 0.0f));
            int r16 = GradientHeaderActivity.access$800(this.this$0).getBottom() + AndroidUtilities.dp(16.0f);
            if (GradientHeaderActivity.m15021$$Nest$fgetcurrentYOffset(this.this$0) >= r16) goto L23;
            GradientHeaderActivity.m15031$$Nest$fputcurrentYOffset(this.this$0, r16);
        L23:
            float r53 = GradientHeaderActivity.m15029$$Nest$fgetprogressToFull(this.this$0);
            GradientHeaderActivity.m15035$$Nest$fputprogressToFull(this.this$0, 0.0f);
            if (GradientHeaderActivity.m15021$$Nest$fgetcurrentYOffset(this.this$0) >= (AndroidUtilities.dp(30.0f) + r16)) goto L26;
            GradientHeaderActivity.m15035$$Nest$fputprogressToFull(this.this$0, ((r16 + AndroidUtilities.dp(30.0f)) - GradientHeaderActivity.m15021$$Nest$fgetcurrentYOffset(this.this$0)) / AndroidUtilities.dp(30.0f));
        L26:
            GradientHeaderActivity r17 = this.this$0;
            if (r17.isLandscapeMode == false) goto L30;
            GradientHeaderActivity.m15035$$Nest$fputprogressToFull(r17, 1.0f);
            GradientHeaderActivity.m15036$$Nest$fputtotalProgress(this.this$0, 1.0f);
        L30:
            if (r53 == GradientHeaderActivity.m15029$$Nest$fgetprogressToFull(this.this$0)) goto L32;
            this.this$0.listView.invalidate();
        L32:
            float r19 = Math.max((((((GradientHeaderActivity.access$1000(this.this$0).getMeasuredHeight() - this.this$0.statusBarHeight) - BackgroundView.m15040$$Nest$fgettitleView(r2).getMeasuredHeight()) / 2.0f) + this.this$0.statusBarHeight) - r2.getTop()) - BackgroundView.m15040$$Nest$fgettitleView(r2).getTop(), (GradientHeaderActivity.m15021$$Nest$fgetcurrentYOffset(this.this$0) - ((GradientHeaderActivity.access$900(this.this$0).getMeasuredHeight() + r2.getMeasuredHeight()) - this.this$0.statusBarHeight)) + AndroidUtilities.dp(16.0f));
            r2.setTranslationY(r19);
            BackgroundView.m15038$$Nest$fgetaboveTitleLayout(r2).setTranslationY((((-r19) / 4.0f) + AndroidUtilities.dp(16.0f)) + AndroidUtilities.dp(16.0f));
            float r110 = ((1.0f - GradientHeaderActivity.m15030$$Nest$fgettotalProgress(this.this$0)) * 0.4f) + 0.6f;
            if (GradientHeaderActivity.m15030$$Nest$fgettotalProgress(this.this$0) <= 0.5f) goto L35;
            float r32 = (GradientHeaderActivity.m15030$$Nest$fgettotalProgress(this.this$0) - 0.5f) / 0.5f;
        L36:
            float r33 = 1.0f - r32;
            BackgroundView.m15038$$Nest$fgetaboveTitleLayout(r2).setScaleX(r110);
            BackgroundView.m15038$$Nest$fgetaboveTitleLayout(r2).setScaleY(r110);
            BackgroundView.m15038$$Nest$fgetaboveTitleLayout(r2).setAlpha(r33);
            BackgroundView.m15039$$Nest$fgetbelowSubTitleLayout(r2).setAlpha(r33);
            r2.subtitleView.setAlpha(r33);
            GradientHeaderActivity r111 = this.this$0;
            r111.particlesView.setAlpha(1.0f - GradientHeaderActivity.m15030$$Nest$fgettotalProgress(r111));
            this.this$0.particlesView.setTranslationY((r2.getY() + BackgroundView.m15038$$Nest$fgetaboveTitleLayout(r2).getY()) - AndroidUtilities.dp(30.0f));
            float r112 = AndroidUtilities.dp(72.0f) - BackgroundView.m15040$$Nest$fgettitleView(r2).getLeft();
            if (GradientHeaderActivity.m15030$$Nest$fgettotalProgress(this.this$0) <= 0.3f) goto L39;
            r6 = (GradientHeaderActivity.m15030$$Nest$fgettotalProgress(this.this$0) - 0.3f) / 0.7f;
        L39:
            BackgroundView.m15040$$Nest$fgettitleView(r2).setTranslationX(r112 * (1.0f - CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(1.0f - r6)));
            if (GradientHeaderActivity.m15026$$Nest$fgetisDialogVisible(this.this$0) == true) goto L42;
            invalidate();
        L42:
            GradientHeaderActivity.m15023$$Nest$fgetgradientTools(this.this$0).gradientMatrix(0, 0, getMeasuredWidth(), getMeasuredHeight(), ((-getMeasuredWidth()) * 0.1f) * GradientHeaderActivity.m15028$$Nest$fgetprogress(this.this$0), 0.0f);
            GradientHeaderActivity r113 = this.this$0;
            if (r113.whiteBackground == false) goto L45;
            this.backgroundGradientPaint.setColor(r113.getThemedColor(Theme.key_windowBackgroundGray));
            r18.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.backgroundGradientPaint);
        L46:
            int r114 = this.this$0.getThemedColor(Theme.key_dialogTextBlack);
            GradientHeaderActivity r54 = this.this$0;
            if (r54.whiteBackground == false) goto L49;
            int r62 = Theme.key_windowBackgroundWhiteBlackText;
        L50:
            int r115 = ColorUtils.blendARGB(r114, r54.getThemedColor(r62), r33);
            GradientHeaderActivity.access$1100(this.this$0).getBackButton().setColorFilter(r115);
            BackgroundView.m15040$$Nest$fgettitleView(r2).setTextColor(r115);
            GradientHeaderActivity.m15024$$Nest$fgetheaderBgPaint(this.this$0).setAlpha((int) ((1.0f - r33) * 255.0f));
            setLightStatusBar(Theme.blendOver(Theme.getColor(Theme.key_premiumGradientBackground4, GradientHeaderActivity.access$1200(this.this$0)), GradientHeaderActivity.m15024$$Nest$fgetheaderBgPaint(this.this$0).getColor()));
            r18.drawRect(0.0f, 0.0f, getMeasuredWidth(), GradientHeaderActivity.access$1300(this.this$0).getMeasuredHeight(), GradientHeaderActivity.m15024$$Nest$fgetheaderBgPaint(this.this$0));
            super.dispatchDraw(r18);
            if (r33 <= 0.01f) goto L53;
            return;
        L53:
            if (this.this$0.drawActionBarShadow() == false) goto L57;
            GradientHeaderActivity.access$1500(this.this$0).drawHeaderShadow(r18, 255, GradientHeaderActivity.access$1400(this.this$0).getMeasuredHeight());
            return;
        L57:
            return;
        L49:
            r62 = Theme.key_premiumGradientBackgroundOverlay;
            goto L50
        L45:
            r18.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), GradientHeaderActivity.m15023$$Nest$fgetgradientTools(this.this$0).paint);
            goto L46
        L35:
            r32 = 0.0f;
            goto L36
        L15:
            r13 = null;
            goto L16
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r13) {
            if (GradientHeaderActivity.access$500(this.this$0) == null) goto L25;
            ImageView r0 = GradientHeaderActivity.access$600(this.this$0).getBackButton();
            if (r0 == null) goto L25;
            if (r0.getVisibility() != 0) goto L25;
            if (r13.getAction() != 0) goto L16;
            RectF r4 = AndroidUtilities.rectTmp;
            if (ViewPositionWatcher.computeRectInParent(r0, this, r4) == false) goto L16;
            if (r4.contains(r13.getX(), r13.getY()) == false) goto L16;
            this.isTouchedActionBarBackButton = true;
        L16:
            if (this.isTouchedActionBarBackButton == false) goto L25;
            boolean r02 = super.dispatchTouchEvent(r13);
            if (r13.getAction() != 1) goto L20;
        L23:
            this.isTouchedActionBarBackButton = false;
            return r02;
        L20:
            if (r13.getAction() == 3) goto L23;
            return r02;
        L25:
            BackgroundView r03 = this.this$0.backgroundView;
            float r42 = r03.getX() + r03.subtitleView.getX();
            float r5 = r03.getY() + r03.subtitleView.getY();
            RectF r6 = AndroidUtilities.rectTmp;
            r6.set(r42, r5, r03.subtitleView.getMeasuredWidth() + r42, r03.subtitleView.getMeasuredHeight() + r5);
            if (r6.contains(r13.getX(), r13.getY()) == true) goto L30;
            if (this.subtitleInterceptedTouch == true) goto L30;
        L48:
            float r43 = r03.getX() + BackgroundView.m15038$$Nest$fgetaboveTitleLayout(r03).getX();
            float r52 = r03.getY() + BackgroundView.m15038$$Nest$fgetaboveTitleLayout(r03).getY();
            boolean r7 = BackgroundView.m15038$$Nest$fgetaboveTitleLayout(r03).isClickable();
            r6.set(r43, r52, BackgroundView.m15038$$Nest$fgetaboveTitleLayout(r03).getMeasuredWidth() + r43, BackgroundView.m15038$$Nest$fgetaboveTitleLayout(r03).getMeasuredHeight() + r52);
            if (r6.contains(r13.getX(), r13.getY()) == false) goto L51;
        L52:
            GradientHeaderActivity r10 = this.this$0;
            if (r10.listView.scrollingByUser == true) goto L70;
            if (r7 == false) goto L70;
            if (GradientHeaderActivity.m15029$$Nest$fgetprogressToFull(r10) >= 1.0f) goto L70;
            r13.offsetLocation(-r43, -r52);
            if (r13.getAction() != 0) goto L60;
        L67:
            this.topInterceptedTouch = true;
        L68:
            BackgroundView.m15038$$Nest$fgetaboveTitleLayout(r03).dispatchTouchEvent(r13);
            return true;
        L60:
            if (r13.getAction() == 2) goto L67;
            if (r13.getAction() != 1) goto L65;
        L66:
            this.topInterceptedTouch = false;
            goto L68
        L65:
            if (r13.getAction() != 3) goto L68;
        L70:
            float r44 = r03.getX() + BackgroundView.m15039$$Nest$fgetbelowSubTitleLayout(r03).getX();
            float r53 = r03.getY() + BackgroundView.m15039$$Nest$fgetbelowSubTitleLayout(r03).getY();
            r6.set(r44, r53, BackgroundView.m15039$$Nest$fgetbelowSubTitleLayout(r03).getMeasuredWidth() + r44, BackgroundView.m15039$$Nest$fgetbelowSubTitleLayout(r03).getMeasuredHeight() + r53);
            if (r6.contains(r13.getX(), r13.getY()) == false) goto L73;
        L74:
            GradientHeaderActivity r62 = this.this$0;
            if (r62.listView.scrollingByUser == false) goto L77;
        L90:
            return super.dispatchTouchEvent(r13);
        L77:
            if (GradientHeaderActivity.m15029$$Nest$fgetprogressToFull(r62) >= 1.0f) goto L90;
            r13.offsetLocation(-r44, -r53);
            if (r13.getAction() != 0) goto L82;
            this.bottomInterceptedTouch = true;
        L86:
            BackgroundView.m15039$$Nest$fgetbelowSubTitleLayout(r03).dispatchTouchEvent(r13);
            if (this.bottomInterceptedTouch == false) goto L90;
            return true;
        L82:
            if (r13.getAction() != 1) goto L84;
        L85:
            this.bottomInterceptedTouch = false;
            goto L86
        L84:
            if (r13.getAction() != 3) goto L86;
        L73:
            if (this.bottomInterceptedTouch == false) goto L90;
        L51:
            if (this.topInterceptedTouch == false) goto L70;
        L30:
            if (this.this$0.listView.scrollingByUser == true) goto L48;
            if (r03.subtitleView.hasLinks() == false) goto L48;
            if (GradientHeaderActivity.m15029$$Nest$fgetprogressToFull(this.this$0) >= 1.0f) goto L48;
            r13.offsetLocation(-r42, -r5);
            if (r13.getAction() != 0) goto L38;
        L45:
            this.subtitleInterceptedTouch = true;
        L46:
            r03.subtitleView.dispatchTouchEvent(r13);
            return true;
        L38:
            if (r13.getAction() == 2) goto L45;
            if (r13.getAction() != 1) goto L43;
        L44:
            this.subtitleInterceptedTouch = false;
            goto L46
        L43:
            if (r13.getAction() != 3) goto L46;
            goto L44
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r5, View r6, long r7) {
            if (r6 != this.this$0.listView) goto L7;
            r5.save();
            r5.clipRect(0, GradientHeaderActivity.access$1600(this.this$0).getBottom(), getMeasuredWidth(), getMeasuredHeight());
            super.drawChild(r5, r6, r7);
            r5.restore();
            return true;
        L7:
            return super.drawChild(r5, r6, r7);
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, org.telegram.ui.Components.SizeNotifierFrameLayout, org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            GradientHeaderActivity r0 = this.this$0;
            BackgroundView r1 = r0.backgroundView;
            if (View.MeasureSpec.getSize(r6) <= View.MeasureSpec.getSize(r7)) goto L5;
            boolean r2 = true;
        L6:
            r0.isLandscapeMode = r2;
            GradientHeaderActivity r02 = this.this$0;
            if (GradientHeaderActivity.access$200(r02) != null) goto L9;
        L11:
            int r22 = AndroidUtilities.statusBarHeight;
        L12:
            r02.statusBarHeight = r22;
            r1.measure(r6, View.MeasureSpec.makeMeasureSpec(0, 0));
            ViewGroup.LayoutParams r03 = this.this$0.particlesView.getLayoutParams();
            int r23 = this.this$0.particlesViewHeight;
            if (r23 > 0) goto L16;
            r23 = r1.getMeasuredHeight();
        L16:
            r03.height = r23;
            GradientHeaderActivity r04 = this.this$0;
            LinearLayoutManager r12 = r04.layoutManager;
            if ((r12 instanceof FillLastLinearLayoutManager) == false) goto L19;
            ((FillLastLinearLayoutManager) r12).setAdditionalHeight(GradientHeaderActivity.access$400(r04).getMeasuredHeight());
            ((FillLastLinearLayoutManager) this.this$0.layoutManager).setMinimumLastViewHeight(0);
        L19:
            super.onMeasure(r6, r7);
            if (this.lastSize == ((getMeasuredHeight() + getMeasuredWidth()) << 16)) goto L23;
            GradientHeaderActivity.m15037$$Nest$mupdateBackgroundImage(this.this$0);
            return;
        L23:
            return;
        L9:
            if (GradientHeaderActivity.access$300(this.this$0).isLayersLayout() == false) goto L11;
            r22 = 0;
            goto L12
        L5:
            r2 = false;
            goto L6
        }
    }

    public static /* synthetic */ Boolean $r8$lambda$dtUZEhPMgoIteEHw4_iWKLch04o(GradientHeaderActivity r0, View r1) {
        return r0.lambda$createView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$sJ8OgiQ5toa3w06Mi6yKGMqp2CI(GradientHeaderActivity r0) {
        r0.updateColors();
    }

    /* renamed from: -$$Nest$fgetcurrentYOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15021$$Nest$fgetcurrentYOffset(GradientHeaderActivity r0) {
        return r0.currentYOffset;
    }

    /* renamed from: -$$Nest$fgetfirstViewHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15022$$Nest$fgetfirstViewHeight(GradientHeaderActivity r0) {
        return r0.firstViewHeight;
    }

    /* renamed from: -$$Nest$fgetgradientTools, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PremiumGradient.PremiumGradientTools m15023$$Nest$fgetgradientTools(GradientHeaderActivity r0) {
        return r0.gradientTools;
    }

    /* renamed from: -$$Nest$fgetheaderBgPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m15024$$Nest$fgetheaderBgPaint(GradientHeaderActivity r0) {
        return r0.headerBgPaint;
    }

    /* renamed from: -$$Nest$fgetinc, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15025$$Nest$fgetinc(GradientHeaderActivity r0) {
        return r0.inc;
    }

    /* renamed from: -$$Nest$fgetisDialogVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15026$$Nest$fgetisDialogVisible(GradientHeaderActivity r0) {
        return r0.isDialogVisible;
    }

    /* renamed from: -$$Nest$fgetminusHeaderHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15027$$Nest$fgetminusHeaderHeight(GradientHeaderActivity r0) {
        return r0.minusHeaderHeight;
    }

    /* renamed from: -$$Nest$fgetprogress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15028$$Nest$fgetprogress(GradientHeaderActivity r0) {
        return r0.progress;
    }

    /* renamed from: -$$Nest$fgetprogressToFull, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15029$$Nest$fgetprogressToFull(GradientHeaderActivity r0) {
        return r0.progressToFull;
    }

    /* renamed from: -$$Nest$fgettotalProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15030$$Nest$fgettotalProgress(GradientHeaderActivity r0) {
        return r0.totalProgress;
    }

    /* renamed from: -$$Nest$fputcurrentYOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15031$$Nest$fputcurrentYOffset(GradientHeaderActivity r0, int r1) {
        r0.currentYOffset = r1;
    }

    /* renamed from: -$$Nest$fputfirstViewHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15032$$Nest$fputfirstViewHeight(GradientHeaderActivity r0, int r1) {
        r0.firstViewHeight = r1;
    }

    /* renamed from: -$$Nest$fputinc, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15033$$Nest$fputinc(GradientHeaderActivity r0, boolean r1) {
        r0.inc = r1;
    }

    /* renamed from: -$$Nest$fputprogress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15034$$Nest$fputprogress(GradientHeaderActivity r0, float r1) {
        r0.progress = r1;
    }

    /* renamed from: -$$Nest$fputprogressToFull, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15035$$Nest$fputprogressToFull(GradientHeaderActivity r0, float r1) {
        r0.progressToFull = r1;
    }

    /* renamed from: -$$Nest$fputtotalProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15036$$Nest$fputtotalProgress(GradientHeaderActivity r0, float r1) {
        r0.totalProgress = r1;
    }

    /* renamed from: -$$Nest$mupdateBackgroundImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15037$$Nest$mupdateBackgroundImage(GradientHeaderActivity r0) {
        r0.updateBackgroundImage();
    }

    public GradientHeaderActivity() {
        int r2 = Theme.key_premiumGradientBackground1;
        int r3 = Theme.key_premiumGradientBackground2;
        int r4 = Theme.key_premiumGradientBackground3;
        int r5 = Theme.key_premiumGradientBackground4;
        this.gradientTools = new AnonymousClass1(this, r2, r3, r4, r5);
        AnonymousClass2 r1 = new AnonymousClass2(this, r2, r3, r4, r5);
        this.darkGradientTools = r1;
        Bitmap r0 = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        this.gradientTextureBitmap = r0;
        this.gradientCanvas = new Canvas(r0);
        this.particlesViewHeight = -1;
        this.useFillLastLayoutManager = true;
        this.headerBgPaint = new Paint();
        r1.darkColors = true;
        this.savedScrollPosition = -1;
    }

    public static /* synthetic */ ActionBar access$000(GradientHeaderActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(GradientHeaderActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1000(GradientHeaderActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1100(GradientHeaderActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1200(GradientHeaderActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ ActionBar access$1300(GradientHeaderActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1400(GradientHeaderActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ INavigationLayout access$1500(GradientHeaderActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$1600(GradientHeaderActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ INavigationLayout access$200(GradientHeaderActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$300(GradientHeaderActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$400(GradientHeaderActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$500(GradientHeaderActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$600(GradientHeaderActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$700(GradientHeaderActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$800(GradientHeaderActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$900(GradientHeaderActivity r0) {
        return r0.actionBar;
    }

    private /* synthetic */ Boolean lambda$createView$0(View r2) {
        if (r2.getParent() == this.listView) goto L7;
        return Boolean.FALSE;
    L7:
        return Boolean.valueOf(!UniversalAdapter.isShadow(r1.getChildViewHolder(r2).getItemViewType()));
    }

    private void updateBackgroundImage() {
        if (this.contentView.getMeasuredWidth() != 0) goto L5;
        return;
    L5:
        if (this.contentView.getMeasuredHeight() != 0) goto L7;
        return;
    L7:
        if (this.backgroundView == null) goto L13;
        this.gradientTools.gradientMatrix(0, 0, this.contentView.getMeasuredWidth(), this.contentView.getMeasuredHeight(), 0.0f, 0.0f);
        this.gradientCanvas.save();
        this.gradientCanvas.scale(100.0f / this.contentView.getMeasuredWidth(), 100.0f / this.contentView.getMeasuredHeight());
        this.gradientCanvas.drawRect(0.0f, 0.0f, this.contentView.getMeasuredWidth(), this.contentView.getMeasuredHeight(), this.gradientTools.paint);
        this.gradientCanvas.restore();
        return;
    }

    private void updateColors() {
        if (this.backgroundView != null) goto L5;
        return;
    L5:
        if (this.actionBar == null) goto L16;
        this.headerBgPaint.setColor(getThemedColor(Theme.key_dialogBackground));
        ActionBar r0 = this.actionBar;
        int r1 = Theme.key_premiumGradientBackgroundOverlay;
        r0.setItemsColor(Theme.getColor(r1), false);
        this.actionBar.setItemsBackgroundColor(ColorUtils.setAlphaComponent(Theme.getColor(r1), 60), false);
        this.particlesView.drawable.updateColors();
        BackgroundView r02 = this.backgroundView;
        if (r02 != null) goto L10;
    L13:
        updateBackgroundImage();
        return;
    L10:
        if (this.whiteBackground == false) goto L12;
        TextView r03 = BackgroundView.m15040$$Nest$fgettitleView(r02);
        int r12 = Theme.key_windowBackgroundWhiteBlackText;
        r03.setTextColor(Theme.getColor(r12));
        this.backgroundView.subtitleView.setTextColor(Theme.getColor(r12));
        this.backgroundView.subtitleView.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn));
        goto L13
    L12:
        BackgroundView.m15040$$Nest$fgettitleView(r02).setTextColor(Theme.getColor(r1));
        this.backgroundView.subtitleView.setTextColor(Theme.getColor(r1));
        this.backgroundView.subtitleView.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn));
        goto L13
    }

    public void applyScrolledPosition() {
        applyScrolledPosition(false);
    }

    public void applyScrolledPosition(boolean r4) {
        RecyclerListView r0 = this.listView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.layoutManager != null) goto L7;
        return;
    L7:
        if (this.savedScrollPosition < 0) goto L16;
        int r1 = this.savedScrollOffset;
        RecyclerView.ViewHolder r02 = r0.findViewHolderForAdapterPosition(0);
        if (r4 == false) goto L12;
        if (r02 == null) goto L12;
        r1 = r1 - Math.max(r02.itemView.getBottom() - this.listView.getPaddingTop(), 0);
    L12:
        this.layoutManager.scrollToPositionWithOffset(this.savedScrollPosition, r1);
        this.savedScrollPosition = -1;
        return;
    }

    public void configureHeader(CharSequence r1, CharSequence r2, View r3, View r4) {
        this.backgroundView.setData(r1, r2, r3, r4);
    }

    public abstract RecyclerView.Adapter<?> createAdapter();

    public ContentView createContentView() {
        return new ContentView(this, getContext());
    }

    public StarParticlesView createParticlesView() {
        return new AnonymousClass7(this, getContext());
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r10) {
        this.hasOwnBackground = true;
        Rect r1 = new Rect();
        Drawable r2 = ContextCompat.getDrawable(r10, R.drawable.sheet_shadow_round).mutate();
        this.shadowDrawable = r2;
        r2.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_dialogBackground), PorterDuff.Mode.MULTIPLY));
        this.shadowDrawable.getPadding(r1);
        INavigationLayout r12 = this.parentLayout;
        if (r12 != null) goto L5;
    L7:
        int r13 = AndroidUtilities.statusBarHeight;
    L8:
        this.statusBarHeight = r13;
        this.contentView = createContentView();
        INavigationLayout r14 = this.parentLayout;
        if (r14 != null) goto L11;
    L13:
        this.actionBar.setAddToContainer(false);
        this.listView = new RecyclerListView(r10);
        if (this.useFillLastLayoutManager == false) goto L16;
        this.layoutManager = new FillLastLinearLayoutManager(r10, (AndroidUtilities.dp(68.0f) + this.statusBarHeight) - AndroidUtilities.dp(16.0f), this.listView);
    L17:
        this.listView.setLayoutManager(this.layoutManager);
        LinearLayoutManager r15 = this.layoutManager;
        if ((r15 instanceof FillLastLinearLayoutManager) == false) goto L20;
        ((FillLastLinearLayoutManager) r15).setFixedLastItemHeight();
    L20:
        RecyclerView.Adapter<?> r16 = createAdapter();
        this.listView.setAdapter(r16);
        boolean r17 = r16 instanceof UniversalAdapter;
        RecyclerListView r3 = this.listView;
        if (r17 == false) goto L23;
        GradientHeaderActivity$$ExternalSyntheticLambda1 r4 = new GradientHeaderActivity$$ExternalSyntheticLambda1(this);
        int r5 = AndroidUtilities.dp(12.0f);
        float r6 = AndroidUtilities.dp(ExteraConfig.getSectionRadiusDp());
        RecyclerListView r18 = this.listView;
        Objects.requireNonNull(r18);
        r3.setSections(r4, r5, r6, new RecyclerListView$$ExternalSyntheticLambda1(r18), true);
    L24:
        this.listView.setClipToPadding(false);
        this.listView.addOnScrollListener(new AnonymousClass4(this));
        this.backgroundView = new AnonymousClass5(this, r10);
        FrameLayout r102 = this.contentView;
        StarParticlesView r19 = createParticlesView();
        this.particlesView = r19;
        r102.addView(r19, LayoutHelper.createFrame(-1, -2.0f));
        this.contentView.addView(this.backgroundView, LayoutHelper.createFrame(-1, -2.0f));
        this.contentView.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.contentView.addView(this.actionBar);
        this.fragmentView = this.contentView;
        this.actionBar.setBackground(null);
        this.actionBar.setCastShadows(false);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass6(this));
        this.actionBar.setForceSkipTouches(true);
        updateColors();
        return this.fragmentView;
    L23:
        r3.setSections(true);
        goto L24
    L16:
        this.layoutManager = new LinearLayoutManager(r10);
        goto L17
    L11:
        if (r14.isLayersLayout() == false) goto L13;
        this.actionBar.setOccupyStatusBar(false);
        goto L13
    L5:
        if (r12.isLayersLayout() == false) goto L7;
        r13 = 0;
        goto L8
    }

    public boolean drawActionBarShadow() {
        return true;
    }

    public View getHeader(Context r2) {
        AnonymousClass3 r0 = new AnonymousClass3(this, r2);
        r0.setTag(-33024);
        return r0;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        return SimpleThemeDescription.createThemeDescriptions(new GradientHeaderActivity$$ExternalSyntheticLambda0(this), new int[]{Theme.key_premiumGradient1, Theme.key_premiumGradient2, Theme.key_premiumGradient3, Theme.key_premiumGradient4, Theme.key_premiumGradientBackground1, Theme.key_premiumGradientBackground2, Theme.key_premiumGradientBackground3, Theme.key_premiumGradientBackground4, Theme.key_premiumGradientBackgroundOverlay, Theme.key_premiumStarGradient1, Theme.key_premiumStarGradient2, Theme.key_premiumStartSmallStarsColor, Theme.key_premiumStartSmallStarsColor2});
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isActionBarCrossfadeEnabled() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (this.whiteBackground == true) goto L5;
        return false;
    L5:
        if (Theme.isCurrentThemeDark() == true) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r1) {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onDialogDismiss(Dialog r1) {
        super.onDialogDismiss(r1);
        updateDialogVisibility(false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        StarParticlesView r1 = this.particlesView;
        if (r1 == null) goto L6;
        r1.setPaused(true);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        this.particlesView.setPaused(false);
    }

    public void saveScrollPosition() {
        RecyclerListView r0 = this.listView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getChildCount() <= 0) goto L24;
        int r02 = 0;
    L8:
        if (r02 >= this.listView.getChildCount()) goto L15;
        View r1 = this.listView.getChildAt(r02);
        int r2 = this.listView.getChildAdapterPosition(r1);
        if (r2 < 0) goto L14;
        if (r1.getTop() >= Integer.MAX_VALUE) goto L14;
        r1.getTop();
    L16:
        if (r1 == null) goto L25;
        this.savedScrollPosition = r2;
        this.savedScrollOffset = r1.getTop();
        return;
    L25:
        return;
    L14:
        r02 = r02 + 1;
        goto L8
    L15:
        r1 = null;
        r2 = -1;
        goto L16
    }

    public Paint setDarkGradientLocation(float r8, float r9) {
        this.darkGradientTools.gradientMatrix(0, 0, this.contentView.getMeasuredWidth(), this.contentView.getMeasuredHeight(), (-r8) - ((this.contentView.getMeasuredWidth() * 0.1f) * this.progress), -r9);
        return this.darkGradientTools.paint;
    }

    public void setMinusHeaderHeight(int r1) {
        this.minusHeaderHeight = r1;
    }

    public void setWhiteBackground(boolean r1) {
        this.whiteBackground = r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public Dialog showDialog(Dialog r2) {
        Dialog r22 = super.showDialog(r2);
        if (r22 == null) goto L5;
        boolean r0 = true;
    L6:
        updateDialogVisibility(r0);
        return r22;
    L5:
        r0 = false;
        goto L6
    }

    public void updateDialogVisibility(boolean r2) {
        if (r2 == this.isDialogVisible) goto L6;
        this.isDialogVisible = r2;
        this.particlesView.setPaused(r2);
        this.contentView.invalidate();
        return;
    }
}
