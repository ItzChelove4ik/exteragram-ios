package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.EdgeToEdgeSupportMode;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Business.AwayMessagesActivity;
import org.telegram.ui.Business.BusinessChatbotController;
import org.telegram.ui.Business.BusinessIntroActivity;
import org.telegram.ui.Business.BusinessLinksActivity;
import org.telegram.ui.Business.BusinessLinksController;
import org.telegram.ui.Business.ChatbotsActivity;
import org.telegram.ui.Business.GreetMessagesActivity;
import org.telegram.ui.Business.OpeningHoursActivity;
import org.telegram.ui.Business.QuickRepliesActivity;
import org.telegram.ui.Business.QuickRepliesController;
import org.telegram.ui.Business.TimezonesController;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FillLastLinearLayoutManager;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MediaActivity;
import org.telegram.ui.Components.Premium.AboutPremiumView;
import org.telegram.ui.Components.Premium.GLIcon.GLIconRenderer;
import org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView;
import org.telegram.ui.Components.Premium.PremiumButtonView;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.Premium.PremiumTierCell;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SimpleThemeDescription;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.URLSpanBotCommand;
import org.telegram.ui.Components.URLSpanBrowser;
import org.telegram.ui.Components.URLSpanMono;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.URLSpanReplacement;
import org.telegram.ui.Components.URLSpanUserMention;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.BlurredBackgroundWithFadeDrawable;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawableSource;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSource;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.blur3.utils.Blur3Utils;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.FilterCreateActivity;
import org.telegram.ui.SelectAnimatedEmojiDialog;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes3.dex */
public class PremiumPreviewFragment extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    BackgroundView backgroundView;
    private FrameLayout buttonContainer;
    private FrameLayout buttonContainerInternal;
    private FrameLayout contentView;
    SubscriptionTier currentSubscriptionTier;
    private int currentYOffset;
    PremiumFeatureCell dummyCell;
    PremiumTierCell dummyTierCell;
    int featuresEndRow;
    int featuresStartRow;
    private int firstViewHeight;
    private boolean forcePremium;
    final Canvas gradientCanvas;
    Paint gradientPaint;
    final Bitmap gradientTextureBitmap;
    PremiumGradient.PremiumGradientTools gradientTools;
    int helpUsRow;
    private IBlur3Capture iBlur3Capture;
    private final BlurredBackgroundDrawableViewFactory iBlur3Factory;
    private final BlurredBackgroundDrawableViewFactory iBlur3FactoryBg;
    private final RectF iBlur3PositionMainTabs;
    private final ArrayList<RectF> iBlur3Positions;
    private final BlurredBackgroundSource iBlur3Source;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlassFrosted;
    boolean inc;
    private Insets insets;
    private boolean isDialogVisible;
    boolean isLandscapeMode;
    int lastPaddingRow;
    FillLastLinearLayoutManager layoutManager;
    RecyclerListView listView;
    Matrix matrix;
    int moreFeaturesEndRow;
    int moreFeaturesStartRow;
    int moreHeaderRow;
    ArrayList<PremiumFeatureData> morePremiumFeatures;
    private BlurredBackgroundWithFadeDrawable navbarProtectionDrawable;
    int paddingRow;
    StarParticlesView particlesView;
    private PremiumButtonView premiumButtonView;
    ArrayList<PremiumFeatureData> premiumFeatures;
    int privacyRow;
    float progress;
    float progressToFull;
    int rowCount;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;
    int sectionRow;
    private SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow selectAnimatedEmojiDialog;
    private boolean selectAnnualByDefault;
    int selectedTierIndex;
    FrameLayout settingsView;
    Shader shader;
    Drawable shadowDrawable;
    int showAdsHeaderRow;
    int showAdsInfoRow;

    @Keep
    int showAdsRow;
    private String source;
    private int statusBarHeight;
    int statusRow;
    Paint strokePaint;
    Shader strokeShader;
    final ArrayList<SubscriptionTier> subscriptionTiers;
    PremiumGradient.PremiumGradientTools tiersGradientTools;
    int totalGradientHeight;
    float totalProgress;
    int totalTiersGradientHeight;
    private final int type;
    private final boolean whiteBackground;

    /* renamed from: org.telegram.ui.PremiumPreviewFragment$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 implements BlurredBackgroundSource {
        private final Paint p;
        final /* synthetic */ PremiumPreviewFragment this$0;

        public AnonymousClass1(PremiumPreviewFragment r1) {
            this.this$0 = r1;
            this.p = new Paint();
        }

        @Override // org.telegram.ui.Components.blur3.source.BlurredBackgroundSource
        public BlurredBackgroundDrawable createDrawable() {
            return new BlurredBackgroundDrawableSource(this);
        }

        @Override // org.telegram.ui.Components.blur3.source.BlurredBackgroundSource
        public void draw(Canvas r9, float r10, float r11, float r12, float r13) {
            if (PremiumPreviewFragment.m17600$$Nest$fgetwhiteBackground(this.this$0) == false) goto L6;
            this.p.setColor(this.this$0.getThemedColor(Theme.key_windowBackgroundGray));
            r9.drawRect(r10, r11, r12, r13, this.p);
            return;
        L6:
            PremiumPreviewFragment r0 = this.this$0;
            r0.gradientTools.gradientMatrix(0, 0, PremiumPreviewFragment.m17589$$Nest$fgetcontentView(r0).getMeasuredWidth(), PremiumPreviewFragment.m17589$$Nest$fgetcontentView(this.this$0).getMeasuredHeight(), ((-PremiumPreviewFragment.m17589$$Nest$fgetcontentView(this.this$0).getMeasuredWidth()) * 0.1f) * this.this$0.progress, 0.0f);
            r9.drawRect(r10, r11, r12, r13, this.this$0.gradientTools.paint);
        }
    }

    /* renamed from: org.telegram.ui.PremiumPreviewFragment$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends FrameLayout {
        private final Paint backgroundPaint;
        boolean iconInterceptedTouch;
        int lastSize;
        boolean listInterceptedTouch;
        final /* synthetic */ PremiumPreviewFragment this$0;

        public AnonymousClass2(PremiumPreviewFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.backgroundPaint = new Paint(1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r17) {
            if (Build.VERSION.SDK_INT < 31) goto L8;
            if (PremiumPreviewFragment.m17596$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L8;
            PremiumPreviewFragment.m17605$$Nest$mblur3_InvalidateBlur(this.this$0);
        L8:
            if (PremiumPreviewFragment.m17594$$Nest$fgetisDialogVisible(this.this$0) == true) goto L18;
            PremiumPreviewFragment r1 = this.this$0;
            if (r1.inc == false) goto L14;
            float r4 = r1.progress + 0.016f;
            r1.progress = r4;
            if (r4 <= 3.0f) goto L18;
            r1.inc = false;
            goto L18
        L14:
            float r42 = r1.progress - 0.016f;
            r1.progress = r42;
            if (r42 >= 1.0f) goto L18;
            r1.inc = true;
        L18:
            if (this.this$0.listView.getLayoutManager() == null) goto L20;
            View r12 = this.this$0.listView.getLayoutManager().findViewByPosition(0);
        L21:
            PremiumPreviewFragment r43 = this.this$0;
            if (r12 != null) goto L24;
            int r13 = 0;
        L25:
            PremiumPreviewFragment.m17601$$Nest$fputcurrentYOffset(r43, r13);
            float r44 = 16.0f;
            int r14 = PremiumPreviewFragment.access$000(this.this$0).getBottom() + AndroidUtilities.dp(16.0f);
            this.this$0.totalProgress = 1.0f - ((PremiumPreviewFragment.m17590$$Nest$fgetcurrentYOffset(r5) - r14) / (PremiumPreviewFragment.m17591$$Nest$fgetfirstViewHeight(this.this$0) - r14));
            PremiumPreviewFragment r15 = this.this$0;
            r15.totalProgress = Utilities.clamp(r15.totalProgress, 1.0f, 0.0f);
            int r16 = PremiumPreviewFragment.access$100(this.this$0).getBottom() + AndroidUtilities.dp(16.0f);
            if (PremiumPreviewFragment.m17590$$Nest$fgetcurrentYOffset(this.this$0) >= r16) goto L28;
            PremiumPreviewFragment.m17601$$Nest$fputcurrentYOffset(this.this$0, r16);
        L28:
            PremiumPreviewFragment r5 = this.this$0;
            float r6 = r5.progressToFull;
            r5.progressToFull = 0.0f;
            if (PremiumPreviewFragment.m17590$$Nest$fgetcurrentYOffset(r5) >= (AndroidUtilities.dp(30.0f) + r16)) goto L31;
            this.this$0.progressToFull = ((r16 + AndroidUtilities.dp(30.0f)) - PremiumPreviewFragment.m17590$$Nest$fgetcurrentYOffset(this.this$0)) / AndroidUtilities.dp(30.0f);
        L31:
            PremiumPreviewFragment r18 = this.this$0;
            if (r18.isLandscapeMode == false) goto L35;
            r18.progressToFull = 1.0f;
            r18.totalProgress = 1.0f;
        L35:
            if (r6 == r18.progressToFull) goto L37;
            r18.listView.invalidate();
        L37:
            int r19 = PremiumPreviewFragment.m17590$$Nest$fgetcurrentYOffset(this.this$0) - ((PremiumPreviewFragment.access$200(this.this$0).getMeasuredHeight() + this.this$0.backgroundView.getMeasuredHeight()) - PremiumPreviewFragment.m17598$$Nest$fgetstatusBarHeight(this.this$0));
            if (BackgroundView.m17613$$Nest$fgettierListView(this.this$0.backgroundView).getVisibility() != 0) goto L40;
            float r52 = 24.0f;
        L41:
            float r110 = Math.max((((((PremiumPreviewFragment.access$300(this.this$0).getMeasuredHeight() - PremiumPreviewFragment.m17598$$Nest$fgetstatusBarHeight(this.this$0)) - this.this$0.backgroundView.titleView.getMeasuredHeight()) / 2.0f) + PremiumPreviewFragment.m17598$$Nest$fgetstatusBarHeight(this.this$0)) - this.this$0.backgroundView.getTop()) - this.this$0.backgroundView.titleView.getTop(), (r19 + AndroidUtilities.dp(r52)) + AndroidUtilities.dp(24.0f));
            float r53 = ((-r110) / 4.0f) + AndroidUtilities.dp(16.0f);
            this.this$0.backgroundView.setTranslationY(r110);
            GLIconTextureView r111 = BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView);
            if (PremiumPreviewFragment.m17599$$Nest$fgettype(this.this$0) != 1) goto L44;
            r44 = 9.0f;
        L44:
            r111.setTranslationY(r53 + AndroidUtilities.dp(r44));
            PremiumPreviewFragment r112 = this.this$0;
            float r2 = r112.totalProgress;
            float r45 = ((1.0f - r2) * 0.4f) + 0.6f;
            if (r2 <= 0.5f) goto L47;
            float r22 = (r2 - 0.5f) / 0.5f;
        L48:
            float r23 = 1.0f - r22;
            BackgroundView.m17611$$Nest$fgetimageView(r112.backgroundView).setScaleX(r45);
            BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).setScaleY(r45);
            BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).setAlpha(r23);
            BackgroundView.m17612$$Nest$fgetsubtitleView(this.this$0.backgroundView).setAlpha(r23);
            BackgroundView.m17613$$Nest$fgettierListView(this.this$0.backgroundView).setAlpha(r23);
            PremiumPreviewFragment r113 = this.this$0;
            r113.particlesView.setAlpha(1.0f - r113.totalProgress);
            this.this$0.particlesView.setTranslationY((((-(r1.getMeasuredHeight() - BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).getMeasuredWidth())) / 2.0f) + this.this$0.backgroundView.getY()) + BackgroundView.m17610$$Nest$fgetimageFrameLayout(this.this$0.backgroundView).getY());
            float r114 = AndroidUtilities.dp(72.0f) - this.this$0.backgroundView.titleView.getLeft();
            PremiumPreviewFragment r24 = this.this$0;
            float r46 = r24.totalProgress;
            if (r46 <= 0.3f) goto L51;
            float r47 = (r46 - 0.3f) / 0.7f;
        L52:
            r24.backgroundView.titleView.setTranslationX(r114 * (1.0f - CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(1.0f - r47)));
            BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).mRenderer.gradientStartX = ((this.this$0.backgroundView.getX() + BackgroundView.m17610$$Nest$fgetimageFrameLayout(this.this$0.backgroundView).getX()) + ((getMeasuredWidth() * 0.1f) * this.this$0.progress)) / getMeasuredWidth();
            BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).mRenderer.gradientStartY = (this.this$0.backgroundView.getY() + BackgroundView.m17610$$Nest$fgetimageFrameLayout(this.this$0.backgroundView).getY()) / getMeasuredHeight();
            if (PremiumPreviewFragment.m17594$$Nest$fgetisDialogVisible(this.this$0) == true) goto L55;
            invalidate();
            PremiumPreviewFragment.m17588$$Nest$fgetbuttonContainerInternal(this.this$0).invalidate();
            PremiumPreviewFragment.m17587$$Nest$fgetbuttonContainer(this.this$0).invalidate();
        L55:
            this.this$0.gradientTools.gradientMatrix(0, 0, getMeasuredWidth(), getMeasuredHeight(), ((-getMeasuredWidth()) * 0.1f) * this.this$0.progress, 0.0f);
            if (PremiumPreviewFragment.m17600$$Nest$fgetwhiteBackground(this.this$0) == false) goto L63;
            Paint r115 = this.backgroundPaint;
            PremiumPreviewFragment r25 = this.this$0;
            int r9 = Theme.key_windowBackgroundGray;
            r115.setColor(r25.getThemedColor(r9));
            r17.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.backgroundPaint);
            PremiumPreviewFragment r116 = this.this$0;
            if (r116.progressToFull > 0.0f) goto L60;
        L62:
            Canvas r117 = r17;
        L64:
            super.dispatchDraw(r17);
            if (PremiumPreviewFragment.m17587$$Nest$fgetbuttonContainer(this.this$0).getVisibility() == 0) goto L68;
            PremiumPreviewFragment.m17595$$Nest$fgetnavbarProtectionDrawable(this.this$0).setFadeHeight(PremiumPreviewFragment.m17593$$Nest$fgetinsets(this.this$0).bottom, false);
            PremiumPreviewFragment.m17595$$Nest$fgetnavbarProtectionDrawable(this.this$0).setBounds(0, getHeight() - PremiumPreviewFragment.m17593$$Nest$fgetinsets(this.this$0).bottom, getWidth(), getHeight());
            PremiumPreviewFragment.m17595$$Nest$fgetnavbarProtectionDrawable(this.this$0).draw(r117);
        L68:
            if (PremiumPreviewFragment.access$600(this.this$0) != null) goto L70;
            return;
        L70:
            if (PremiumPreviewFragment.m17600$$Nest$fgetwhiteBackground(this.this$0) == false) goto L74;
            INavigationLayout r26 = PremiumPreviewFragment.access$800(this.this$0);
            PremiumPreviewFragment r0 = this.this$0;
            r26.drawHeaderShadow(r117, (int) (r0.progressToFull * 255.0f), PremiumPreviewFragment.access$700(r0).getBottom());
            return;
        L74:
            return;
        L60:
            if (PremiumPreviewFragment.access$400(r116) == null) goto L62;
            this.backgroundPaint.setColor(ColorUtils.blendARGB(this.this$0.getThemedColor(r9), this.this$0.getThemedColor(Theme.key_windowBackgroundWhite), this.this$0.progressToFull));
            r117 = r17;
            r117.drawRect(0.0f, 0.0f, getMeasuredWidth(), PremiumPreviewFragment.access$500(this.this$0).getHeight(), this.backgroundPaint);
            goto L64
        L63:
            r117 = r17;
            r117.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.this$0.gradientTools.paint);
            goto L64
        L51:
            r47 = 0.0f;
            goto L52
        L47:
            r22 = 0.0f;
            goto L48
        L40:
            r52 = 16.0f;
            goto L41
        L24:
            r13 = r12.getBottom();
            goto L25
        L20:
            r12 = null;
            goto L21
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r9) {
            float r0 = this.this$0.backgroundView.getX() + BackgroundView.m17610$$Nest$fgetimageFrameLayout(this.this$0.backgroundView).getX();
            float r1 = this.this$0.backgroundView.getY() + BackgroundView.m17610$$Nest$fgetimageFrameLayout(this.this$0.backgroundView).getY();
            RectF r2 = AndroidUtilities.rectTmp;
            if (BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView) != null) goto L5;
            int r3 = 0;
        L6:
            float r32 = r3 + r0;
            if (BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView) != null) goto L9;
            int r5 = 0;
        L10:
            r2.set(r0, r1, r32, r5 + r1);
            if (r2.contains(r9.getX(), r9.getY()) == true) goto L15;
            if (this.iconInterceptedTouch == true) goto L15;
        L29:
            float r02 = this.this$0.backgroundView.getX() + BackgroundView.m17613$$Nest$fgettierListView(this.this$0.backgroundView).getX();
            float r12 = this.this$0.backgroundView.getY() + BackgroundView.m17613$$Nest$fgettierListView(this.this$0.backgroundView).getY();
            r2.set(r02, r12, BackgroundView.m17613$$Nest$fgettierListView(this.this$0.backgroundView).getWidth() + r02, BackgroundView.m17613$$Nest$fgettierListView(this.this$0.backgroundView).getHeight() + r12);
            if (this.this$0.progressToFull >= 1.0f) goto L49;
            if (r2.contains(r9.getX(), r9.getY()) == true) goto L36;
            if (this.listInterceptedTouch == false) goto L49;
        L36:
            if (this.this$0.listView.scrollingByUser == true) goto L49;
            r9.offsetLocation(-r02, -r12);
            if (r9.getAction() != 0) goto L41;
            this.listInterceptedTouch = true;
        L45:
            BackgroundView.m17613$$Nest$fgettierListView(this.this$0.backgroundView).dispatchTouchEvent(r9);
            if (this.listInterceptedTouch == false) goto L49;
            return true;
        L41:
            if (r9.getAction() != 1) goto L43;
        L44:
            this.listInterceptedTouch = false;
            goto L45
        L43:
            if (r9.getAction() != 3) goto L45;
        L49:
            return super.dispatchTouchEvent(r9);
        L15:
            if (this.this$0.listView.scrollingByUser == true) goto L29;
            r9.offsetLocation(-r0, -r1);
            if (r9.getAction() != 0) goto L19;
        L26:
            this.iconInterceptedTouch = true;
        L27:
            BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).dispatchTouchEvent(r9);
            return true;
        L19:
            if (r9.getAction() == 2) goto L26;
            if (r9.getAction() != 1) goto L24;
        L25:
            this.iconInterceptedTouch = false;
            goto L27
        L24:
            if (r9.getAction() != 3) goto L27;
        L9:
            r5 = BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).getMeasuredHeight();
            goto L10
        L5:
            r3 = BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).getMeasuredWidth();
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).mRenderer.gradientScaleX = BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).getMeasuredWidth() / getMeasuredWidth();
            BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).mRenderer.gradientScaleY = BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).getMeasuredHeight() / getMeasuredHeight();
            BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).mRenderer.gradientStartX = (this.this$0.backgroundView.getX() + BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).getX()) / getMeasuredWidth();
            BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).mRenderer.gradientStartY = (this.this$0.backgroundView.getY() + BackgroundView.m17611$$Nest$fgetimageView(this.this$0.backgroundView).getY()) / getMeasuredHeight();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            PremiumPreviewFragment r0 = this.this$0;
            int r3 = 0;
            if (View.MeasureSpec.getSize(r5) <= View.MeasureSpec.getSize(r6)) goto L5;
            boolean r1 = true;
        L6:
            r0.isLandscapeMode = r1;
            PremiumPreviewFragment.m17604$$Nest$fputstatusBarHeight(this.this$0, AndroidUtilities.statusBarHeight);
            this.this$0.backgroundView.measure(r5, View.MeasureSpec.makeMeasureSpec(0, 0));
            this.this$0.particlesView.getLayoutParams().height = this.this$0.backgroundView.getMeasuredHeight();
            if (PremiumPreviewFragment.m17587$$Nest$fgetbuttonContainer(this.this$0) != null) goto L9;
        L12:
            PremiumPreviewFragment r02 = this.this$0;
            r02.layoutManager.setAdditionalHeight((PremiumPreviewFragment.m17598$$Nest$fgetstatusBarHeight(r02) + r3) - AndroidUtilities.dp(16.0f));
            this.this$0.layoutManager.setMinimumLastViewHeight(r3);
            super.onMeasure(r5, r6);
            if (this.lastSize == ((getMeasuredHeight() + getMeasuredWidth()) << 16)) goto L16;
            PremiumPreviewFragment.m17607$$Nest$mupdateBackgroundImage(this.this$0);
            return;
        L16:
            return;
        L9:
            if (PremiumPreviewFragment.m17587$$Nest$fgetbuttonContainer(this.this$0).getVisibility() == 8) goto L12;
            r3 = AndroidUtilities.dp(68.0f);
            goto L12
        L5:
            r1 = false;
            goto L6
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            PremiumPreviewFragment.m17606$$Nest$mmeasureGradient(this.this$0, r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.PremiumPreviewFragment$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends ViewOutlineProvider {
        final /* synthetic */ PremiumPreviewFragment this$0;

        public AnonymousClass3(PremiumPreviewFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r8, Outline r9) {
            r9.setRoundRect(AndroidUtilities.dp(12.0f), PremiumPreviewFragment.access$900(this.this$0).getBottom() + AndroidUtilities.dp(12.0f), r8.getWidth() - AndroidUtilities.dp(12.0f), r8.getMeasuredHeight() + AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        }
    }

    /* renamed from: org.telegram.ui.PremiumPreviewFragment$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        final /* synthetic */ PremiumPreviewFragment this$0;

        public AnonymousClass4(PremiumPreviewFragment r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r3, int r4) {
            super.onScrollStateChanged(r3, r4);
            if (r4 != 0) goto L17;
            int r32 = PremiumPreviewFragment.access$1000(this.this$0).getBottom() + AndroidUtilities.dp(16.0f);
            PremiumPreviewFragment r42 = this.this$0;
            if (r42.totalProgress <= 0.5f) goto L9;
            r42.listView.smoothScrollBy(0, PremiumPreviewFragment.m17590$$Nest$fgetcurrentYOffset(r42) - r32);
            return;
        L9:
            if (r42.listView.getLayoutManager() == null) goto L11;
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
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            super.onScrolled(r2, r3, r4);
            PremiumPreviewFragment.m17589$$Nest$fgetcontentView(this.this$0).invalidate();
            if (Build.VERSION.SDK_INT >= 31) goto L5;
            return;
        L5:
            if (PremiumPreviewFragment.m17596$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L9;
            PremiumPreviewFragment.m17596$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).onScrolled(r3, r4);
            PremiumPreviewFragment.m17605$$Nest$mblur3_InvalidateBlur(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.PremiumPreviewFragment$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PremiumPreviewFragment this$0;

        public AnonymousClass5(PremiumPreviewFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.PremiumPreviewFragment$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 implements Bulletin.Delegate {
        final /* synthetic */ PremiumPreviewFragment this$0;

        public AnonymousClass6(PremiumPreviewFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return PremiumPreviewFragment.m17593$$Nest$fgetinsets(this.this$0).bottom;
        }
    }

    /* renamed from: org.telegram.ui.PremiumPreviewFragment$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ PremiumPreviewFragment this$0;

        public AnonymousClass7(PremiumPreviewFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            PremiumPreviewFragment.m17589$$Nest$fgetcontentView(this.this$0).removeView(this.this$0.settingsView);
            this.this$0.settingsView = null;
            super.onAnimationEnd(r3);
        }
    }

    /* renamed from: org.telegram.ui.PremiumPreviewFragment$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass8 extends SelectAnimatedEmojiDialog {
        final /* synthetic */ PremiumPreviewFragment this$0;
        final /* synthetic */ Utilities.Callback2 val$onSet;
        final /* synthetic */ SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] val$popup;

        public AnonymousClass8(PremiumPreviewFragment r1, BaseFragment r2, Context r3, boolean r4, Integer r5, int r6, boolean r7, Theme.ResourcesProvider r8, int r9, Utilities.Callback2 r10, SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] r11) {
            this.this$0 = r1;
            this.val$onSet = r10;
            this.val$popup = r11;
            super(r2, r3, r4, r5, r6, r7, r8, r9);
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public float getScrimDrawableTranslationY() {
            return 0.0f;
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public void onEmojiSelected(View r1, Long r2, TLRPC.Document r3, TL_stars.TL_starGiftUnique r4, Integer r5) {
            Utilities.Callback2 r12 = this.val$onSet;
            if (r12 == null) goto L6;
            r12.run(r2, r5);
        L6:
            if (this.val$popup[0] == null) goto L9;
            PremiumPreviewFragment.m17603$$Nest$fputselectAnimatedEmojiDialog(this.this$0, null);
            this.val$popup[0].dismiss();
            return;
        }
    }

    /* renamed from: org.telegram.ui.PremiumPreviewFragment$9, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass9 extends SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow {
        final /* synthetic */ PremiumPreviewFragment this$0;

        public AnonymousClass9(PremiumPreviewFragment r1, View r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow, android.widget.PopupWindow
        public void dismiss() {
            super.dismiss();
            PremiumPreviewFragment.m17603$$Nest$fputselectAnimatedEmojiDialog(this.this$0, null);
        }
    }

    /* loaded from: classes6.dex */
    public class Adapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ PremiumPreviewFragment this$0;

        /* renamed from: org.telegram.ui.PremiumPreviewFragment$Adapter$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass1(Adapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r4, int r5) {
                PremiumPreviewFragment r52 = this.this$1.this$0;
                if (r52.isLandscapeMode == false) goto L5;
                PremiumPreviewFragment.m17602$$Nest$fputfirstViewHeight(r52, 0);
            L9:
                super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec(PremiumPreviewFragment.m17591$$Nest$fgetfirstViewHeight(this.this$1.this$0), TLObject.FLAG_30));
                return;
            L5:
                int r53 = AndroidUtilities.dp(64.0f);
                if ((this.this$1.this$0.backgroundView.getMeasuredHeight() + AndroidUtilities.dp(8.0f)) <= r53) goto L8;
                r53 = this.this$1.this$0.backgroundView.getMeasuredHeight() + AndroidUtilities.dp(8.0f);
            L8:
                PremiumPreviewFragment.m17602$$Nest$fputfirstViewHeight(this.this$1.this$0, r53);
                goto L9
            }
        }

        /* renamed from: org.telegram.ui.PremiumPreviewFragment$Adapter$2, reason: invalid class name */
        public class AnonymousClass2 extends PremiumFeatureCell {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass2(Adapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.PremiumFeatureCell, android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r7) {
                float r0 = AndroidUtilities.dp(10.0f);
                RectF r1 = AndroidUtilities.rectTmp;
                r1.set(this.imageView.getLeft(), this.imageView.getTop(), this.imageView.getRight(), this.imageView.getBottom());
                this.this$1.this$0.matrix.reset();
                this.this$1.this$0.matrix.postScale(1.0f, r2.totalGradientHeight / 100.0f, 0.0f, 0.0f);
                this.this$1.this$0.matrix.postTranslate(0.0f, -this.data.yOffset);
                PremiumPreviewFragment r2 = this.this$1.this$0;
                r2.shader.setLocalMatrix(r2.matrix);
                r7.drawRoundRect(r1, r0, r0, this.this$1.this$0.gradientPaint);
                if (PremiumPreviewFragment.access$1100(this.this$1.this$0) == null) goto L5;
                boolean r22 = PremiumPreviewFragment.access$1200(this.this$1.this$0).isDark();
            L6:
                if (r22 == false) goto L8;
                float r23 = AndroidUtilities.dp(1.0f);
                this.this$1.this$0.strokePaint.setStrokeWidth(r23);
                r7.save();
                r7.translate(r1.left, r1.top);
                r1.offset(-r1.left, -r1.top);
                float r24 = r23 / 2.0f;
                r1.inset(r24, r24);
                r7.drawRoundRect(r1, r0, r0, this.this$1.this$0.strokePaint);
                r7.restore();
            L8:
                super.dispatchDraw(r7);
                return;
            L5:
                r22 = Theme.isCurrentThemeDark();
                goto L6
            }
        }

        public static /* synthetic */ void $r8$lambda$0LrHL1DxBP4wQdXfiIuJXfjU2s8(Adapter r0) {
            r0.lambda$onBindViewHolder$0();
        }

        private Adapter(PremiumPreviewFragment r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(PremiumPreviewFragment r1, PremiumPreviewFragmentIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$onBindViewHolder$0() {
            this.this$0.showDialog(new RevenueSharingAdsInfoBottomSheet(this.this$0.getContext(), false, this.this$0.getResourceProvider(), null));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.this$0.rowCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            PremiumPreviewFragment r2 = this.this$0;
            if (r3 != r2.paddingRow) goto L6;
            return 0;
        L6:
            if (r3 < r2.featuresStartRow) goto L10;
            if (r3 >= r2.featuresEndRow) goto L10;
            return 1;
        L10:
            if (r3 < r2.moreFeaturesStartRow) goto L16;
            if (r3 >= r2.moreFeaturesEndRow) goto L16;
            return 1;
        L16:
            if (r3 != r2.helpUsRow) goto L20;
            return 4;
        L20:
            if (r3 != r2.sectionRow) goto L22;
            return 5;
        L22:
            if (r3 != r2.statusRow) goto L24;
            return 5;
        L24:
            if (r3 != r2.privacyRow) goto L26;
            return 5;
        L26:
            if (r3 != r2.showAdsInfoRow) goto L29;
            return 5;
        L29:
            if (r3 != r2.lastPaddingRow) goto L33;
            return 6;
        L33:
            if (r3 != r2.moreHeaderRow) goto L35;
            return 7;
        L35:
            if (r3 != r2.showAdsHeaderRow) goto L38;
            return 7;
        L38:
            if (r3 != r2.showAdsRow) goto L41;
            return 8;
        L41:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            if (r2.getItemViewType() != 1) goto L5;
        L9:
            return true;
        L5:
            if (r2.getItemViewType() == 8) goto L9;
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r17, int r18) {
            PremiumPreviewFragment r3 = this.this$0;
            int r4 = r3.featuresStartRow;
            int r5 = 0;
            boolean r52 = false;
            boolean r53 = false;
            boolean r6 = true;
            if (r18 >= r4) goto L5;
        L11:
            int r42 = r3.moreFeaturesStartRow;
            if (r18 >= r42) goto L14;
        L20:
            int r43 = r3.sectionRow;
            String r7 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r18 != r43) goto L25;
            TextInfoPrivacyCell r0 = (TextInfoPrivacyCell) r17.itemView;
            r0.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            r0.setFixedSize(12);
            return;
        L25:
            if (r18 != r3.statusRow) goto L27;
        L49:
            TextInfoPrivacyCell r1 = (TextInfoPrivacyCell) r17.itemView;
            if (PremiumPreviewFragment.m17600$$Nest$fgetwhiteBackground(r3) == true) goto L52;
            r1.setTextColor(Theme.multAlpha(-1, 0.75f));
            r1.getTextView().setLinkTextColor(-1);
            r1.setLinkTextRippleColor(Integer.valueOf(Theme.multAlpha(-1, 0.15f)));
        L52:
            r1.setFixedSize(0);
            PremiumPreviewFragment r32 = this.this$0;
            if (r18 != r32.showAdsInfoRow) goto L57;
            r1.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.ShowAdsInfo), new PremiumPreviewFragment$Adapter$$ExternalSyntheticLambda0(this)), true));
            return;
        L57:
            if (r18 == r32.statusRow) goto L59;
        L62:
            PremiumPreviewFragment r33 = this.this$0;
            if (r18 != r33.statusRow) goto L129;
            TLRPC.TL_help_premiumPromo r2 = r33.getMediaDataController().getPremiumPromo();
            if (r2 == null) goto L130;
            SpannableString r9 = new SpannableString(r2.status_text);
            MediaDataController.addTextStyleRuns(r2.status_entities, r2.status_text, r9);
            TextStyleSpan[] r34 = (TextStyleSpan[]) r9.getSpans(0, r9.length(), TextStyleSpan.class);
            int r44 = r34.length;
        L68:
            if (r5 >= r44) goto L122;
            TextStyleSpan.TextStyleRun r13 = r34[r5].getTextStyleRun();
            TLRPC.MessageEntity r8 = r13.urlEntity;
            if (r8 == null) goto L72;
            String r10 = r2.status_text;
            int r11 = r8.offset;
            String r82 = TextUtils.substring(r10, r11, r8.length + r11);
        L73:
            TLRPC.MessageEntity r102 = r13.urlEntity;
            if ((r102 instanceof TLRPC.TL_messageEntityBotCommand) == false) goto L78;
            r9.setSpan(new URLSpanBotCommand(r82, 0, r13), r13.start, r13.end, 33);
        L76:
            String r15 = r7;
        L119:
            if ((r13.flags & 256) == 0) goto L121;
            r9.setSpan(new TextStyleSpan(r13), r13.start, r13.end, 33);
        L121:
            r5 = r5 + 1;
            r7 = r15;
            r6 = true;
            goto L68
        L78:
            if ((r102 instanceof TLRPC.TL_messageEntityHashtag) == false) goto L80;
        L83:
            r15 = r7;
            r9.setSpan(new URLSpanNoUnderline(r82, r13), r13.start, r13.end, 33);
            goto L119
        L80:
            if ((r102 instanceof TLRPC.TL_messageEntityMention) == true) goto L83;
            if ((r102 instanceof TLRPC.TL_messageEntityCashtag) == true) goto L83;
            if ((r102 instanceof TLRPC.TL_messageEntityEmail) == false) goto L88;
            r9.setSpan(new URLSpanReplacement("mailto:" + r82, r13), r13.start, r13.end, 33);
            goto L76
        L88:
            if ((r102 instanceof TLRPC.TL_messageEntityUrl) == false) goto L94;
            if (r82.toLowerCase().contains("://") == true) goto L92;
            r9.setSpan(new URLSpanBrowser(AndroidUtilities.defaultUrlScheme(r82) + r82, r13), r13.start, r13.end, 33);
            goto L76
        L92:
            r9.setSpan(new URLSpanBrowser(r82, r13), r13.start, r13.end, 33);
            goto L76
        L94:
            if ((r102 instanceof TLRPC.TL_messageEntityBankCard) == false) goto L97;
            r9.setSpan(new URLSpanNoUnderline("card:" + r82, r13), r13.start, r13.end, 33);
            goto L76
        L97:
            if ((r102 instanceof TLRPC.TL_messageEntityPhone) == false) goto L103;
            String r103 = PhoneFormat.stripExceptNumbers(r82);
            if (r82.startsWith("+") == false) goto L101;
            r103 = "+" + r103;
        L101:
            r9.setSpan(new URLSpanBrowser("tel:" + r103, r13), r13.start, r13.end, 33);
            goto L76
        L103:
            if ((r102 instanceof TLRPC.TL_messageEntityTextUrl) == false) goto L108;
            URLSpanReplacement r83 = new URLSpanReplacement(r13.urlEntity.url, r13);
            r83.setNavigateToPremiumBot(r6);
            r9.setSpan(r83, r13.start, r13.end, 33);
            if (PremiumPreviewFragment.m17600$$Nest$fgetwhiteBackground(this.this$0) == true) goto L76;
            r9.setSpan(new TypefaceSpan(AndroidUtilities.bold()), r13.start, r13.end, 33);
            goto L76
        L108:
            if ((r102 instanceof TLRPC.TL_messageEntityMentionName) == false) goto L110;
            StringBuilder r104 = new StringBuilder(r7);
            r15 = r7;
            r104.append(((TLRPC.TL_messageEntityMentionName) r13.urlEntity).user_id);
            r9.setSpan(new URLSpanUserMention(r104.toString(), 0, r13), r13.start, r13.end, 33);
            goto L119
        L110:
            r15 = r7;
            if ((r102 instanceof TLRPC.TL_inputMessageEntityMentionName) == false) goto L114;
            r9.setSpan(new URLSpanUserMention(r15 + ((TLRPC.TL_inputMessageEntityMentionName) r13.urlEntity).user_id.user_id, 0, r13), r13.start, r13.end, 33);
            goto L119
        L114:
            if ((r13.flags & 4) == 0) goto L116;
            r9.setSpan(new URLSpanMono(r9, r13.start, r13.end, (byte) 0, r13), r13.start, r13.end, 33);
            goto L119
        L116:
            r9.setSpan(new TextStyleSpan(r13), r13.start, r13.end, 33);
            goto L121
        L72:
            r82 = null;
            goto L73
        L122:
            r1.setText(r9);
            return;
        L130:
            return;
        L129:
            return;
        L59:
            if (PremiumPreviewFragment.m17599$$Nest$fgettype(r32) != 1) goto L62;
            r1.setText(LocaleController.getString(R.string.PremiumPreviewMoreBusinessFeaturesInfo));
            return;
        L27:
            if (r18 == r3.privacyRow) goto L49;
            if (r18 == r3.showAdsInfoRow) goto L49;
            if (r18 != r3.moreHeaderRow) goto L36;
            ((HeaderCell) r17.itemView).setText(LocaleController.getString(R.string.PremiumPreviewMoreBusinessFeatures));
            return;
        L36:
            if (r18 != r3.showAdsHeaderRow) goto L40;
            ((HeaderCell) r17.itemView).setText(LocaleController.getString(R.string.ShowAdsTitle));
            return;
        L40:
            if (r18 != r3.showAdsRow) goto L128;
            TLRPC.UserFull r02 = r3.getMessagesController().getUserFull(this.this$0.getUserConfig().getClientUserId());
            TextCell r12 = (TextCell) r17.itemView;
            String r22 = LocaleController.getString(R.string.ShowAds);
            if (r02 != null) goto L44;
        L47:
            r12.setTextAndCheck(r22, r6, false);
            return;
        L44:
            if (r02.sponsored_enabled == true) goto L47;
            r6 = false;
            goto L47
        L128:
            return;
        L14:
            if (r18 >= r3.moreFeaturesEndRow) goto L20;
            PremiumFeatureCell r14 = (PremiumFeatureCell) r17.itemView;
            PremiumFeatureData r35 = r3.morePremiumFeatures.get(r18 - r42);
            if (r18 == (this.this$0.moreFeaturesEndRow - 1)) goto L18;
            r53 = true;
        L18:
            r14.setData(r35, r53);
            return;
        L5:
            if (r18 >= r3.featuresEndRow) goto L11;
            PremiumFeatureCell r16 = (PremiumFeatureCell) r17.itemView;
            PremiumFeatureData r36 = r3.premiumFeatures.get(r18 - r4);
            if (r18 == (this.this$0.featuresEndRow - 1)) goto L9;
            r52 = true;
        L9:
            r16.setData(r36, r52);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r8, int r9) {
            Context r2 = r8.getContext();
            switch(r9) {
                case 1: goto L11;
                case 2: goto L10;
                case 3: goto L4;
                case 4: goto L9;
                case 5: goto L8;
                case 6: goto L7;
                case 7: goto L6;
                case 8: goto L5;
                default: goto L4;
            };
        L4:
            View r82 = new AnonymousClass1(this, r2);
            r82.setTag(-33024);
        L12:
            r82.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r82);
        L5:
            r82 = new TextCell(r2, 23, false, true, PremiumPreviewFragment.access$1300(this.this$0));
            goto L12
        L6:
            r82 = new HeaderCell(r2);
            goto L12
        L7:
            r82 = new View(r2);
            r82.setTag(-33024);
            goto L12
        L8:
            r82 = new TextInfoPrivacyCell(r2);
            goto L12
        L9:
            r82 = new AboutPremiumView(r2);
            goto L12
        L10:
            r82 = new ShadowSectionCell(r2, 12, 0);
            goto L12
        L11:
            r82 = new AnonymousClass2(this, r2);
            goto L12
        }
    }

    /* loaded from: classes6.dex */
    public class BackgroundView extends LinearLayout {
        private final FrameLayout imageFrameLayout;
        private final GLIconTextureView imageView;
        private boolean setTierListViewVisibility;
        private final TextView subtitleView;
        final /* synthetic */ PremiumPreviewFragment this$0;
        private RecyclerListView tierListView;
        private boolean tierListViewVisible;
        TextView titleView;

        /* renamed from: org.telegram.ui.PremiumPreviewFragment$BackgroundView$1, reason: invalid class name */
        public class AnonymousClass1 extends GLIconTextureView {
            final /* synthetic */ BackgroundView this$1;
            final /* synthetic */ Context val$context;
            final /* synthetic */ PremiumPreviewFragment val$this$0;

            public AnonymousClass1(BackgroundView r1, Context r2, int r3, int r4, PremiumPreviewFragment r5, Context r6) {
                this.this$1 = r1;
                this.val$this$0 = r5;
                this.val$context = r6;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView
            public void onLongPress() {
                super.onLongPress();
                PremiumPreviewFragment r0 = this.this$1.this$0;
                if (r0.settingsView == null) goto L5;
                return;
            L5:
                if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L10;
                r0.settingsView = new FrameLayout(this.val$context);
                ScrollView r02 = new ScrollView(this.val$context);
                r02.addView(new GLIconSettingsView(this.val$context, BackgroundView.m17611$$Nest$fgetimageView(this.this$1).mRenderer));
                this.this$1.this$0.settingsView.addView(r02);
                this.this$1.this$0.settingsView.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground));
                PremiumPreviewFragment.m17589$$Nest$fgetcontentView(this.this$1.this$0).addView(this.this$1.this$0.settingsView, LayoutHelper.createFrame(-1, -1, 80));
                ((ViewGroup.MarginLayoutParams) this.this$1.this$0.settingsView.getLayoutParams()).topMargin = PremiumPreviewFragment.m17590$$Nest$fgetcurrentYOffset(this.this$1.this$0);
                this.this$1.this$0.settingsView.setTranslationY(AndroidUtilities.dp(1000.0f));
                this.this$1.this$0.settingsView.animate().translationY(1.0f).setDuration(300);
                return;
            }
        }

        /* renamed from: org.telegram.ui.PremiumPreviewFragment$BackgroundView$2, reason: invalid class name */
        public class AnonymousClass2 extends RecyclerListView {
            Paint paint;
            private Path path;
            final /* synthetic */ BackgroundView this$1;
            final /* synthetic */ PremiumPreviewFragment val$this$0;

            public AnonymousClass2(BackgroundView r1, Context r2, PremiumPreviewFragment r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
                Paint r12 = new Paint(1);
                this.paint = r12;
                r12.setColor(Theme.getColor(Theme.key_dialogBackground));
                this.path = new Path();
            }

            @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r6) {
                this.path.rewind();
                RectF r0 = AndroidUtilities.rectTmp;
                r0.set(0.0f, 0.0f, getWidth(), getHeight());
                this.path.addRoundRect(r0, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), Path.Direction.CW);
                r6.drawPath(this.path, this.paint);
                r6.save();
                r6.clipPath(this.path);
                super.dispatchDraw(r6);
                r6.restore();
            }

            @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
            public boolean dispatchTouchEvent(MotionEvent r3) {
                if (this.this$1.this$0.progressToFull < 1.0f) goto L7;
                return false;
            L7:
                return super.dispatchTouchEvent(r3);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
            public boolean onInterceptTouchEvent(MotionEvent r3) {
                if (this.this$1.this$0.progressToFull < 1.0f) goto L7;
                return false;
            L7:
                return super.onInterceptTouchEvent(r3);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
            public void onSizeChanged(int r1, int r2, int r3, int r4) {
                super.onSizeChanged(r1, r2, r3, r4);
                BackgroundView.m17614$$Nest$mmeasureGradient(this.this$1, r1, r2);
            }
        }

        /* renamed from: org.telegram.ui.PremiumPreviewFragment$BackgroundView$3, reason: invalid class name */
        public class AnonymousClass3 extends RecyclerListView.SelectionAdapter {
            final /* synthetic */ BackgroundView this$1;
            final /* synthetic */ Context val$context;
            final /* synthetic */ PremiumPreviewFragment val$this$0;

            /* renamed from: org.telegram.ui.PremiumPreviewFragment$BackgroundView$3$1, reason: invalid class name */
            public class AnonymousClass1 extends PremiumTierCell {
                final /* synthetic */ AnonymousClass3 this$2;

                public AnonymousClass1(AnonymousClass3 r1, Context r2) {
                    this.this$2 = r1;
                    super(r2);
                }

                @Override // org.telegram.ui.Components.Premium.PremiumTierCell, android.view.ViewGroup, android.view.View
                public void dispatchDraw(Canvas r10) {
                    if (this.discountView.getVisibility() != 0) goto L5;
                    RectF r0 = AndroidUtilities.rectTmp;
                    r0.set(this.discountView.getLeft(), this.discountView.getTop(), this.discountView.getRight(), this.discountView.getBottom());
                    this.this$2.this$1.this$0.tiersGradientTools.gradientMatrix(0, 0, getMeasuredWidth(), this.this$2.this$1.this$0.totalTiersGradientHeight, 0.0f, -this.tier.yOffset);
                    r10.drawRoundRect(r0, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.this$2.this$1.this$0.tiersGradientTools.paint);
                L5:
                    super.dispatchDraw(r10);
                }
            }

            public static /* synthetic */ Paint $r8$lambda$fwN_26TETxVKUGdO05jipPVilRw(AnonymousClass3 r0, PremiumTierCell r1, Void r2) {
                return r0.lambda$onCreateViewHolder$0(r1, r2);
            }

            public AnonymousClass3(BackgroundView r1, PremiumPreviewFragment r2, Context r3) {
                this.this$1 = r1;
                this.val$this$0 = r2;
                this.val$context = r3;
            }

            private /* synthetic */ Paint lambda$onCreateViewHolder$0(PremiumTierCell r8, Void r9) {
                this.this$1.this$0.tiersGradientTools.gradientMatrix(0, 0, r8.getMeasuredWidth(), this.this$1.this$0.totalTiersGradientHeight, 0.0f, -r8.getTier().yOffset);
                return this.this$1.this$0.tiersGradientTools.paint;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return this.this$1.this$0.subscriptionTiers.size();
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r1) {
                return !this.this$1.this$0.subscriptionTiers.get(r1.getAdapterPosition()).subscriptionOption.current;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
                PremiumTierCell r52 = (PremiumTierCell) r5.itemView;
                SubscriptionTier r0 = this.this$1.this$0.subscriptionTiers.get(r6);
                boolean r2 = true;
                if (r6 == (getItemCount() - 1)) goto L5;
                boolean r1 = true;
            L6:
                r52.bind(r0, r1);
                if (this.this$1.this$0.selectedTierIndex == r6) goto L10;
                r2 = false;
            L10:
                r52.setChecked(r2, false);
                return;
            L5:
                r1 = false;
                goto L6
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
                AnonymousClass1 r12 = new AnonymousClass1(this, this.val$context);
                r12.setCirclePaintProvider(new PremiumPreviewFragment$BackgroundView$3$$ExternalSyntheticLambda0(this, r12));
                return new RecyclerListView.Holder(r12);
            }
        }

        /* renamed from: org.telegram.ui.PremiumPreviewFragment$BackgroundView$4, reason: invalid class name */
        public class AnonymousClass4 extends AnimatorListenerAdapter {
            final /* synthetic */ BackgroundView this$1;
            final /* synthetic */ View val$v;

            public AnonymousClass4(BackgroundView r1, View r2) {
                this.this$1 = r1;
                this.val$v = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                this.val$v.setVisibility(8);
                int r32 = 0;
            L4:
                if (r32 >= this.this$1.this$0.backgroundView.getChildCount()) goto L9;
                View r0 = this.this$1.this$0.backgroundView.getChildAt(r32);
                if (r0 == BackgroundView.m17613$$Nest$fgettierListView(this.this$1)) goto L8;
                r0.setTranslationY(0.0f);
            L8:
                r32 = r32 + 1;
                goto L4
            }
        }

        public static /* synthetic */ void $r8$lambda$75VEInlNwfBLjtOQiegIwMjADGk(BackgroundView r0, View r1, int r2) {
            r0.lambda$new$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$_EL8VF1IqGedQlHc64RgLkCvawA(BackgroundView r0, Path r1, float[] r2, Canvas r3) {
            r0.lambda$new$1(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$tEjnUsB_E-MdrkhzTY4VLmpCKRk, reason: not valid java name */
        public static /* synthetic */ void m17609$r8$lambda$tEjnUsB_EMdrkhzTY4VLmpCKRk(BackgroundView r0, View r1, ValueAnimator r2, ValueAnimator r3) {
            r0.lambda$updateText$2(r1, r2, r3);
        }

        /* renamed from: -$$Nest$fgetimageFrameLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m17610$$Nest$fgetimageFrameLayout(BackgroundView r0) {
            return r0.imageFrameLayout;
        }

        /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ GLIconTextureView m17611$$Nest$fgetimageView(BackgroundView r0) {
            return r0.imageView;
        }

        /* renamed from: -$$Nest$fgetsubtitleView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m17612$$Nest$fgetsubtitleView(BackgroundView r0) {
            return r0.subtitleView;
        }

        /* renamed from: -$$Nest$fgettierListView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RecyclerListView m17613$$Nest$fgettierListView(BackgroundView r0) {
            return r0.tierListView;
        }

        /* renamed from: -$$Nest$mmeasureGradient, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m17614$$Nest$mmeasureGradient(BackgroundView r0, int r1, int r2) {
            r0.measureGradient(r1, r2);
        }

        public BackgroundView(PremiumPreviewFragment r19, Context r20) {
            this.this$0 = r19;
            super(r20);
            setOrientation(1);
            FrameLayout r8 = new FrameLayout(r20);
            this.imageFrameLayout = r8;
            if (PremiumPreviewFragment.m17599$$Nest$fgettype(r19) != 1) goto L5;
            int r0 = 175;
        L6:
            addView(r8, LayoutHelper.createLinear(r0, r0, 1));
            boolean r3 = PremiumPreviewFragment.m17600$$Nest$fgetwhiteBackground(r19);
            if (PremiumPreviewFragment.m17599$$Nest$fgettype(r19) != 1) goto L9;
            int r4 = 1;
        L10:
            AnonymousClass1 r02 = new AnonymousClass1(this, r20, r3 ? 1 : 0, r4, r19, r20);
            this.imageView = r02;
            r8.addView(r02, LayoutHelper.createFrame(-1, -1.0f));
            r8.setClipChildren(false);
            setClipChildren(false);
            TextView r03 = new TextView(r20);
            this.titleView = r03;
            r03.setTextSize(1, 22.0f);
            this.titleView.setTypeface(AndroidUtilities.bold());
            this.titleView.setGravity(1);
            View r04 = this.titleView;
            if (PremiumPreviewFragment.m17599$$Nest$fgettype(r19) != 1) goto L13;
            int r15 = 8;
        L14:
            addView(r04, LayoutHelper.createLinear(-2, -2, 0.0f, 1, 16, r15, 16, 0));
            TextView r05 = new TextView(r20);
            this.subtitleView = r05;
            r05.setTextSize(1, 14.0f);
            r05.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            r05.setGravity(1);
            addView(r05, LayoutHelper.createLinear(-1, -2, 0.0f, 1, 16, 7, 16, 0));
            AnonymousClass2 r06 = new AnonymousClass2(this, r20, r19);
            this.tierListView = r06;
            r06.setOverScrollMode(2);
            this.tierListView.setLayoutManager(new LinearLayoutManager(r20));
            this.tierListView.setAdapter(new AnonymousClass3(this, r19, r20));
            this.tierListView.setOnItemClickListener(new PremiumPreviewFragment$BackgroundView$$ExternalSyntheticLambda0(this));
            this.tierListView.setSelectorTransformer(new PremiumPreviewFragment$BackgroundView$$ExternalSyntheticLambda1(this, new Path(), new float[8]));
            setClipChildren(false);
            setClipToPadding(false);
            addView(this.tierListView, LayoutHelper.createLinear(-1, -2, 12.0f, 16.0f, 12.0f, 4.0f));
            updatePremiumTiers();
            updateText();
            return;
        L13:
            r15 = 20;
            goto L14
        L9:
            r4 = 0;
            goto L10
        L5:
            r0 = 190;
            goto L6
        }

        private /* synthetic */ void lambda$new$0(View r6, int r7) {
            if (r6.isEnabled() == true) goto L6;
            return;
        L6:
            if ((r6 instanceof PremiumTierCell) == false) goto L71;
            PremiumTierCell r62 = (PremiumTierCell) r6;
            PremiumPreviewFragment r72 = this.this$0;
            r72.selectedTierIndex = r72.subscriptionTiers.indexOf(r62.getTier());
            boolean r0 = true;
            PremiumPreviewFragment.m17608$$Nest$mupdateButtonText(this.this$0, true);
            r62.setChecked(true, true);
            int r1 = 0;
        L9:
            if (r1 >= this.tierListView.getChildCount()) goto L16;
            View r2 = this.tierListView.getChildAt(r1);
            if ((r2 instanceof PremiumTierCell) == false) goto L15;
            PremiumTierCell r22 = (PremiumTierCell) r2;
            if (r22.getTier() == r62.getTier()) goto L15;
            r22.setChecked(false, true);
        L15:
            r1 = r1 + 1;
            goto L9
        L16:
            int r12 = 0;
        L18:
            if (r12 >= this.tierListView.getHiddenChildCount()) goto L25;
            View r23 = this.tierListView.getHiddenChildAt(r12);
            if ((r23 instanceof PremiumTierCell) == false) goto L24;
            PremiumTierCell r24 = (PremiumTierCell) r23;
            if (r24.getTier() == r62.getTier()) goto L24;
            r24.setChecked(false, true);
        L24:
            r12 = r12 + 1;
            goto L18
        L25:
            int r13 = 0;
        L27:
            if (r13 >= this.tierListView.getCachedChildCount()) goto L34;
            View r25 = this.tierListView.getCachedChildAt(r13);
            if ((r25 instanceof PremiumTierCell) == false) goto L33;
            PremiumTierCell r26 = (PremiumTierCell) r25;
            if (r26.getTier() == r62.getTier()) goto L33;
            r26.setChecked(false, true);
        L33:
            r13 = r13 + 1;
            goto L27
        L34:
            int r14 = 0;
        L36:
            if (r14 >= this.tierListView.getAttachedScrapChildCount()) goto L43;
            View r27 = this.tierListView.getAttachedScrapChildAt(r14);
            if ((r27 instanceof PremiumTierCell) == false) goto L42;
            PremiumTierCell r28 = (PremiumTierCell) r27;
            if (r28.getTier() == r62.getTier()) goto L42;
            r28.setChecked(false, true);
        L42:
            r14 = r14 + 1;
            goto L36
        L43:
            FrameLayout r63 = PremiumPreviewFragment.m17587$$Nest$fgetbuttonContainer(this.this$0);
            if (this.this$0.getUserConfig().isPremium() == false) goto L53;
            SubscriptionTier r15 = this.this$0.currentSubscriptionTier;
            if (r15 == null) goto L52;
            int r16 = r15.getMonths();
            PremiumPreviewFragment r29 = this.this$0;
            if (r16 >= r29.subscriptionTiers.get(r29.selectedTierIndex).getMonths()) goto L52;
            if (PremiumPreviewFragment.m17592$$Nest$fgetforcePremium(this.this$0) == false) goto L53;
        L52:
            r0 = false;
        L53:
            AndroidUtilities.updateViewVisibilityAnimated(r63, r0);
            return;
        }

        private /* synthetic */ void lambda$new$1(Path r7, float[] r8, Canvas r9) {
            View r0 = this.tierListView.getPressedChildView();
            if (r0 != null) goto L5;
            int r02 = -1;
        L6:
            r7.rewind();
            Rect r1 = this.tierListView.getSelectorRect();
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set(r1.left, r1.top, r1.right, r1.bottom);
            Arrays.fill(r8, 0.0f);
            if (r02 != 0) goto L10;
            Arrays.fill(r8, 0, 4, AndroidUtilities.dp(12.0f));
        L10:
            if (r02 != (this.tierListView.getAdapter().getItemCount() - 1)) goto L12;
            Arrays.fill(r8, 4, 8, AndroidUtilities.dp(12.0f));
        L12:
            r7.addRoundRect(r2, r8, Path.Direction.CW);
            r9.clipPath(r7);
            return;
        L5:
            r02 = this.tierListView.getChildViewHolder(r0).getAdapterPosition();
            goto L6
        }

        private /* synthetic */ void lambda$updateText$2(View r4, ValueAnimator r5, ValueAnimator r6) {
            float r62 = ((Float) r6.getAnimatedValue()).floatValue();
            r4.setAlpha(r62);
            r4.setScaleX(r62);
            r4.setScaleY(r62);
            float r52 = r5.getAnimatedFraction();
            int r63 = 0;
        L4:
            if (r63 >= this.this$0.backgroundView.getChildCount()) goto L13;
            View r0 = this.this$0.backgroundView.getChildAt(r63);
            if (r0 == this.tierListView) goto L12;
            if (r0 != this.imageFrameLayout) goto L10;
            float r2 = 0.0f - (AndroidUtilities.dp(15.0f) * r52);
        L11:
            r0.setTranslationY((r4.getMeasuredHeight() * r52) + r2);
            goto L12
        L10:
            r2 = 0.0f + (AndroidUtilities.dp(8.0f) * r52);
        L12:
            r63 = r63 + 1;
            goto L4
        }

        private void measureGradient(int r7, int r8) {
            int r1 = 0;
            int r2 = 0;
        L3:
            int r3 = this.this$0.subscriptionTiers.size();
            PremiumPreviewFragment r4 = this.this$0;
            if (r1 >= r3) goto L6;
            r4.dummyTierCell.bind(r4.subscriptionTiers.get(r1), false);
            this.this$0.dummyTierCell.measure(View.MeasureSpec.makeMeasureSpec(r7, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r8, Integer.MIN_VALUE));
            this.this$0.subscriptionTiers.get(r1).yOffset = r2;
            r2 = r2 + this.this$0.dummyTierCell.getMeasuredHeight();
            r1 = r1 + 1;
            goto L3
        L6:
            r4.totalTiersGradientHeight = r2;
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r1) {
            return true;
        }

        @SuppressLint({"NotifyDataSetChanged"})
        public void updatePremiumTiers() {
            this.this$0.subscriptionTiers.clear();
            PremiumPreviewFragment r1 = this.this$0;
            r1.selectedTierIndex = -1;
            r1.currentSubscriptionTier = null;
            long r7 = 0;
            if (r1.getMediaDataController().getPremiumPromo() == null) goto L28;
            ArrayList<TLRPC.TL_premiumSubscriptionOption> r12 = this.this$0.getMediaDataController().getPremiumPromo().period_options;
            int r9 = r12.size();
            int r122 = 0;
            long r10 = 0;
        L5:
            if (r122 >= r9) goto L30;
            TLRPC.TL_premiumSubscriptionOption r13 = r12.get(r122);
            r122 = r122 + 1;
            TLRPC.TL_premiumSubscriptionOption r132 = r13;
            if (this.this$0.getUserConfig().isPremium() == false) goto L13;
            if (r132.can_purchase_upgrade == true) goto L13;
            if (r132.current == false) goto L5;
        L13:
            SubscriptionTier r14 = new SubscriptionTier(r132);
            this.this$0.subscriptionTiers.add(r14);
            if (PremiumPreviewFragment.m17597$$Nest$fgetselectAnnualByDefault(this.this$0) == false) goto L20;
            if (r132.months != 12) goto L20;
            this.this$0.selectedTierIndex = r15.subscriptionTiers.size() - 1;
        L20:
            if (r132.current == false) goto L23;
            this.this$0.currentSubscriptionTier = r14;
        L23:
            if (BuildVars.useInvoiceBilling() == false) goto L5;
            if (r14.getPricePerYear() <= r10) goto L5;
            r10 = r14.getPricePerYear();
        L30:
            if (BuildVars.useInvoiceBilling() == false) goto L35;
            if (this.this$0.getUserConfig().isPremium() == false) goto L35;
            this.this$0.subscriptionTiers.clear();
            this.this$0.currentSubscriptionTier = null;
        L52:
            if (BuildVars.useInvoiceBilling() == false) goto L57;
            ArrayList<SubscriptionTier> r15 = this.this$0.subscriptionTiers;
            int r3 = r15.size();
            int r5 = 0;
        L54:
            if (r5 >= r3) goto L80;
            SubscriptionTier r72 = r15.get(r5);
            r5 = r5 + 1;
            r72.setPricePerYearRegular(r10);
        L80:
            if (this.this$0.selectedTierIndex != (-1)) goto L91;
            int r16 = 0;
        L83:
            if (r16 >= this.this$0.subscriptionTiers.size()) goto L88;
            if (this.this$0.subscriptionTiers.get(r16).getMonths() == 12) goto L86;
            r16 = r16 + 1;
            goto L83
        L86:
            this.this$0.selectedTierIndex = r16;
        L88:
            PremiumPreviewFragment r17 = this.this$0;
            if (r17.selectedTierIndex != (-1)) goto L91;
            r17.selectedTierIndex = 0;
        L91:
            PremiumPreviewFragment.m17608$$Nest$mupdateButtonText(this.this$0, false);
            this.tierListView.getAdapter().notifyDataSetChanged();
            return;
        L57:
            if (BillingController.getInstance().isReady() == false) goto L80;
            if (BillingController.PREMIUM_PRODUCT_DETAILS == null) goto L80;
            ArrayList<SubscriptionTier> r18 = this.this$0.subscriptionTiers;
            int r32 = r18.size();
            boolean r52 = false;
            int r92 = 0;
        L61:
            if (r92 >= r32) goto L68;
            SubscriptionTier r102 = r18.get(r92);
            r92 = r92 + 1;
            SubscriptionTier r103 = r102;
            r103.setGooglePlayProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS);
            if (r103.getPricePerYear() <= r7) goto L66;
            r7 = r103.getPricePerYear();
        L66:
            if (r103.getOfferDetails() == null) goto L61;
            r52 = true;
            goto L61
        L68:
            if (r52 == false) goto L76;
            int r19 = 0;
        L71:
            if (r19 >= this.this$0.subscriptionTiers.size()) goto L76;
            if (this.this$0.subscriptionTiers.get(r19).getOfferDetails() != null) goto L75;
            this.this$0.subscriptionTiers.remove(r19);
            r19 = r19 - 1;
        L75:
            r19 = r19 + 1;
        L76:
            ArrayList<SubscriptionTier> r110 = this.this$0.subscriptionTiers;
            int r33 = r110.size();
            int r53 = 0;
        L77:
            if (r53 >= r33) goto L80;
            SubscriptionTier r93 = r110.get(r53);
            r53 = r53 + 1;
            r93.setPricePerYearRegular(r7);
        L35:
            if (BuildVars.useInvoiceBilling() == false) goto L37;
        L46:
            SubscriptionTier r111 = this.this$0.currentSubscriptionTier;
            if (r111 == null) goto L52;
            if (r111.getMonths() != 12) goto L52;
        L50:
            this.this$0.subscriptionTiers.clear();
            this.this$0.currentSubscriptionTier = null;
            goto L52
        L37:
            if (this.this$0.currentSubscriptionTier == null) goto L46;
            String r112 = BillingController.getInstance().getLastPremiumTransaction();
            TLRPC.TL_premiumSubscriptionOption r54 = this.this$0.currentSubscriptionTier.subscriptionOption;
            if (r54 == null) goto L43;
            String r55 = r54.transaction;
            if (r55 == null) goto L43;
            String r56 = r55.replaceAll("^(.*?)(?:\\.\\.\\d*|)$", "$1");
        L45:
            if (Objects.equals(r112, r56) == false) goto L50;
        L43:
            r56 = null;
            goto L45
        L28:
            r10 = 0;
            goto L30
        }

        public void updateText() {
            if (PremiumPreviewFragment.m17599$$Nest$fgettype(this.this$0) != 0) goto L17;
            TextView r0 = this.titleView;
            if (PremiumPreviewFragment.m17592$$Nest$fgetforcePremium(this.this$0) == false) goto L7;
            int r2 = R.string.TelegramPremiumSubscribedTitle;
        L8:
            r0.setText(LocaleController.getString(r2));
            TextView r02 = this.subtitleView;
            if (this.this$0.getUserConfig().isPremium() == false) goto L11;
        L14:
            int r22 = R.string.TelegramPremiumSubscribedSubtitle;
        L15:
            r02.setText(AndroidUtilities.replaceTags(LocaleController.getString(r22)));
        L30:
            this.subtitleView.getLayoutParams().width = Math.min(AndroidUtilities.displaySize.x - AndroidUtilities.dp(42.0f), HintView2.cutInFancyHalf(this.subtitleView.getText(), this.subtitleView.getPaint()));
            int r23 = 0;
            if (PremiumPreviewFragment.m17592$$Nest$fgetforcePremium(this.this$0) == false) goto L33;
        L38:
            boolean r03 = true;
        L40:
            if (this.setTierListViewVisibility == false) goto L49;
            if (r03 == false) goto L49;
            if (this.tierListView.getVisibility() != 0) goto L53;
            if (r03 == false) goto L53;
            if (this.tierListViewVisible != r03) goto L53;
            RecyclerListView r24 = this.tierListView;
            ValueAnimator r3 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f}).setDuration(250);
            r3.addUpdateListener(new PremiumPreviewFragment$BackgroundView$$ExternalSyntheticLambda2(this, r24, r3));
            r3.addListener(new AnonymousClass4(this, r24));
            r3.setInterpolator(CubicBezierInterpolator.DEFAULT);
            r3.start();
        L53:
            this.tierListViewVisible = !r03;
            return;
        L49:
            RecyclerListView r32 = this.tierListView;
            if (r03 == false) goto L52;
            r23 = 8;
        L52:
            r32.setVisibility(r23);
            this.setTierListViewVisibility = true;
            goto L53
        L33:
            if (BuildVars.IS_BILLING_UNAVAILABLE == true) goto L38;
            if (this.this$0.subscriptionTiers.size() <= 1) goto L38;
            r03 = false;
            goto L40
        L11:
            if (PremiumPreviewFragment.m17592$$Nest$fgetforcePremium(this.this$0) == true) goto L14;
            r22 = R.string.TelegramPremiumSubtitle;
            goto L15
        L7:
            r2 = R.string.TelegramPremium;
            goto L8
        L17:
            if (PremiumPreviewFragment.m17599$$Nest$fgettype(this.this$0) != 1) goto L30;
            TextView r04 = this.titleView;
            if (PremiumPreviewFragment.m17592$$Nest$fgetforcePremium(this.this$0) == false) goto L21;
            int r25 = R.string.TelegramPremiumSubscribedTitle;
        L22:
            r04.setText(LocaleController.getString(r25));
            TextView r05 = this.subtitleView;
            if (this.this$0.getUserConfig().isPremium() == false) goto L25;
        L28:
            int r26 = R.string.TelegramBusinessSubscribedSubtitleTemp;
        L29:
            r05.setText(AndroidUtilities.replaceTags(LocaleController.getString(r26)));
            goto L30
        L25:
            if (PremiumPreviewFragment.m17592$$Nest$fgetforcePremium(this.this$0) == true) goto L28;
            r26 = R.string.TelegramBusinessSubtitleTemp;
            goto L29
        L21:
            r25 = R.string.TelegramBusiness;
            goto L22
        }
    }

    /* loaded from: classes6.dex */
    public static class PremiumFeatureData {
        public final String description;
        public final int icon;
        public final CharSequence title;
        public final int type;
        public int yOffset;

        public PremiumFeatureData(int r1, int r2, CharSequence r3, String r4) {
            this.type = r1;
            this.icon = r2;
            this.title = r3;
            this.description = r4;
        }
    }

    /* loaded from: classes6.dex */
    public static final class SubscriptionTier {
        private int discount;
        private ProductDetails googlePlayProductDetails;
        private ProductDetails.SubscriptionOfferDetails offerDetails;
        private long pricePerMonth;
        private long pricePerYear;
        private long pricePerYearRegular;
        public final TLRPC.TL_premiumSubscriptionOption subscriptionOption;
        public int yOffset;

        /* renamed from: -$$Nest$fgetgooglePlayProductDetails, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ProductDetails m17615$$Nest$fgetgooglePlayProductDetails(SubscriptionTier r0) {
            return r0.googlePlayProductDetails;
        }

        public SubscriptionTier(TLRPC.TL_premiumSubscriptionOption r1) {
            this.subscriptionOption = r1;
        }

        private void checkOfferDetails() {
            ProductDetails r0 = this.googlePlayProductDetails;
            if (r0 != null) goto L6;
            return;
        L6:
            if (this.offerDetails != null) goto L27;
            Iterator<ProductDetails.SubscriptionOfferDetails> r02 = r0.getSubscriptionOfferDetails().iterator();
        L9:
            if (r02.hasNext() == false) goto L29;
            ProductDetails.SubscriptionOfferDetails r1 = r02.next();
            String r2 = r1.getPricingPhases().getPricingPhaseList().get(0).getBillingPeriod();
            if (getMonths() == 12) goto L13;
            if (r2.equals(String.format(Locale.ROOT, "P%dM", new Object[]{Integer.valueOf(getMonths())})) == false) goto L9;
        L17:
            this.offerDetails = r1;
            return;
        L13:
            if (r2.equals("P1Y") == false) goto L9;
        L29:
            return;
        }

        public String getCurrency() {
            if (BuildVars.useInvoiceBilling() == true) goto L16;
            if (this.subscriptionOption.store_product == null) goto L16;
            if (this.googlePlayProductDetails != null) goto L10;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L10:
            checkOfferDetails();
            ProductDetails.SubscriptionOfferDetails r2 = this.offerDetails;
            if (r2 != null) goto L14;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L14:
            return r2.getPricingPhases().getPricingPhaseList().get(0).getPriceCurrencyCode();
        L16:
            return this.subscriptionOption.currency;
        }

        public int getDiscount() {
            if (this.discount != 0) goto L14;
            if (getPricePerMonth() != 0) goto L9;
            return 0;
        L9:
            if (this.pricePerYearRegular == 0) goto L14;
            int r0 = (int) ((1.0d - (getPricePerYear() / this.pricePerYearRegular)) * 100.0d);
            this.discount = r0;
            if (r0 != 0) goto L14;
            this.discount = -1;
        L14:
            return this.discount;
        }

        public String getFormattedPrice() {
            if (BuildVars.useInvoiceBilling() == true) goto L14;
            if (this.subscriptionOption.store_product == null) goto L14;
            if (this.googlePlayProductDetails != null) goto L12;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L12:
            return BillingController.getInstance().formatCurrency(getPrice(), getCurrency(), 6);
        L14:
            return BillingController.getInstance().formatCurrency(getPrice(), getCurrency());
        }

        public String getFormattedPricePerMonth() {
            if (BuildVars.useInvoiceBilling() == true) goto L14;
            if (this.subscriptionOption.store_product == null) goto L14;
            if (this.googlePlayProductDetails != null) goto L12;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L12:
            return BillingController.getInstance().formatCurrency(getPricePerMonth(), getCurrency(), 6);
        L14:
            return BillingController.getInstance().formatCurrency(getPricePerMonth(), getCurrency());
        }

        public String getFormattedPricePerYear() {
            if (BuildVars.useInvoiceBilling() == true) goto L14;
            if (this.subscriptionOption.store_product == null) goto L14;
            if (this.googlePlayProductDetails != null) goto L12;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L12:
            return BillingController.getInstance().formatCurrency(getPricePerYear(), getCurrency(), 6);
        L14:
            return BillingController.getInstance().formatCurrency(getPricePerYear(), getCurrency());
        }

        public String getFormattedPricePerYearRegular() {
            if (BuildVars.useInvoiceBilling() == true) goto L14;
            if (this.subscriptionOption.store_product == null) goto L14;
            if (this.googlePlayProductDetails != null) goto L12;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L12:
            return BillingController.getInstance().formatCurrency(this.pricePerYearRegular, getCurrency(), 6);
        L14:
            return BillingController.getInstance().formatCurrency(this.pricePerYearRegular, getCurrency());
        }

        public ProductDetails getGooglePlayProductDetails() {
            return this.googlePlayProductDetails;
        }

        public int getMonths() {
            return this.subscriptionOption.months;
        }

        public ProductDetails.SubscriptionOfferDetails getOfferDetails() {
            checkOfferDetails();
            return this.offerDetails;
        }

        public long getPrice() {
            if (BuildVars.useInvoiceBilling() == true) goto L16;
            if (this.subscriptionOption.store_product == null) goto L16;
            if (this.googlePlayProductDetails != null) goto L10;
            return 0;
        L10:
            checkOfferDetails();
            ProductDetails.SubscriptionOfferDetails r3 = this.offerDetails;
            if (r3 != null) goto L14;
            return 0;
        L14:
            return r3.getPricingPhases().getPricingPhaseList().get(0).getPriceAmountMicros();
        L16:
            return this.subscriptionOption.amount;
        }

        public long getPricePerMonth() {
            if (this.pricePerMonth != 0) goto L8;
            long r0 = getPrice();
            if (r0 == 0) goto L8;
            this.pricePerMonth = r0 / this.subscriptionOption.months;
        L8:
            return this.pricePerMonth;
        }

        public long getPricePerYear() {
            if (this.pricePerYear != 0) goto L8;
            long r0 = getPrice();
            if (r0 == 0) goto L8;
            this.pricePerYear = (long) ((r0 / this.subscriptionOption.months) * 12.0d);
        L8:
            return this.pricePerYear;
        }

        public void setGooglePlayProductDetails(ProductDetails r1) {
            this.googlePlayProductDetails = r1;
        }

        public void setPricePerYearRegular(long r1) {
            this.pricePerYearRegular = r1;
        }
    }

    /* renamed from: $r8$lambda$1x7pDlBQ-NS87pU1XSDGvoqnFXQ, reason: not valid java name */
    public static /* synthetic */ void m17579$r8$lambda$1x7pDlBQNS87pU1XSDGvoqnFXQ(PremiumPreviewFragment r0, Canvas r1, RectF r2) {
        r0.lambda$createView$0(r1, r2);
    }

    /* renamed from: $r8$lambda$6jew7CtL0c-InUdEVCw9VPbiy1Y, reason: not valid java name */
    public static /* synthetic */ void m17580$r8$lambda$6jew7CtL0cInUdEVCw9VPbiy1Y(int r1, Runnable r2, BaseFragment r3, TLRPC.TL_payments_assignPlayMarketTransaction r4, TLObject r5, TLRPC.TL_error r6) {
        if ((r5 instanceof TLRPC.Updates) == false) goto L6;
        MessagesController.getInstance(r1).processUpdates((TLRPC.Updates) r5, false);
        AndroidUtilities.runOnUIThread(r2);
        return;
    L6:
        if (r6 == null) goto L9;
        AndroidUtilities.runOnUIThread(new PremiumPreviewFragment$$ExternalSyntheticLambda25(r1, r6, r3, r4));
        return;
    }

    /* renamed from: $r8$lambda$82wlIM89uxa2gEUO-Pt0ZHWL6oI, reason: not valid java name */
    public static /* synthetic */ void m17581$r8$lambda$82wlIM89uxa2gEUOPt0ZHWL6oI(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$D-1Q7Bq4QBkZlRi5z8m-h9z_tLQ, reason: not valid java name */
    public static /* synthetic */ void m17582$r8$lambda$D1Q7Bq4QBkZlRi5z8mh9z_tLQ(PremiumPreviewFragment r0, View r1, int r2) {
        r0.lambda$createView$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$E9RZV1G68eET3XZHdwIUzpBwiso(PremiumPreviewFragment r0, View r1) {
        r0.lambda$updateButtonText$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$G01WmJm8qyaKHTztBLSZ2z44v0g(BaseFragment r9, TLRPC.TL_inputStorePaymentPremiumSubscription r10, SubscriptionTier r11, BillingFlowParams.SubscriptionUpdateParams r12, int r13, TLRPC.TL_payments_canPurchaseStore r14, TLObject r15, TLRPC.TL_error r16) {
        AndroidUtilities.runOnUIThread(new PremiumPreviewFragment$$ExternalSyntheticLambda26(r15, r9, r10, r11, r12, r13, r16, r14));
    }

    public static /* synthetic */ void $r8$lambda$Kyo1SjsKZX7KBhY1IvH8PVQZPdo(PremiumPreviewFragment r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$createView$3(r1, r2);
    }

    /* renamed from: $r8$lambda$L3NU01o3sPubvPT51xDWy-vXzLQ, reason: not valid java name */
    public static /* synthetic */ void m17583$r8$lambda$L3NU01o3sPubvPT51xDWyvXzLQ(BillingResult r9, BaseFragment r10, boolean r11, List r12, int r13, BillingFlowParams.SubscriptionUpdateParams r14, SubscriptionTier r15) {
        if (r9.getResponseCode() != 0) goto L28;
        PremiumPreviewFragment$$ExternalSyntheticLambda21 r92 = new PremiumPreviewFragment$$ExternalSyntheticLambda21(r10, r11);
        if (r12 != null) goto L7;
    L20:
        BillingController.getInstance().addResultListener(BillingController.PREMIUM_PRODUCT_ID, new PremiumPreviewFragment$$ExternalSyntheticLambda23(r92));
        TLRPC.TL_payments_canPurchaseStore r8 = new TLRPC.TL_payments_canPurchaseStore();
        TLRPC.TL_inputStorePaymentPremiumSubscription r4 = new TLRPC.TL_inputStorePaymentPremiumSubscription();
        if (r14 == null) goto L23;
        r4.upgrade = true;
    L23:
        r8.purpose = r4;
        ConnectionsManager.getInstance(r13).sendRequest(r8, new PremiumPreviewFragment$$ExternalSyntheticLambda24(r10, r4, r15, r14, r13, r8));
        return;
    L7:
        if (r12.isEmpty() == true) goto L20;
        if (UserConfig.getInstance(r13).isPremium() == true) goto L20;
        Iterator r122 = r12.iterator();
    L12:
        if (r122.hasNext() == false) goto L20;
        Purchase r1 = (Purchase) r122.next();
        if (r1.getProducts().contains(BillingController.PREMIUM_PRODUCT_ID) == false) goto L12;
        TLRPC.TL_payments_assignPlayMarketTransaction r112 = new TLRPC.TL_payments_assignPlayMarketTransaction();
        TLRPC.TL_dataJSON r123 = new TLRPC.TL_dataJSON();
        r112.receipt = r123;
        r123.data = r1.getOriginalJson();
        TLRPC.TL_inputStorePaymentPremiumSubscription r124 = new TLRPC.TL_inputStorePaymentPremiumSubscription();
        r124.restore = true;
        if (r14 == null) goto L18;
        r124.upgrade = true;
    L18:
        r112.purpose = r124;
        ConnectionsManager.getInstance(r13).sendRequest(r112, new PremiumPreviewFragment$$ExternalSyntheticLambda22(r13, r92, r10, r112), 66);
        return;
    }

    public static /* synthetic */ void $r8$lambda$LgbcFJTwQExXORqclMiba8kI6Do(PremiumPreviewFragment r0, PremiumFeatureCell r1, Long r2, Integer r3) {
        r0.lambda$createView$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$LjJhhSFXJsz4l2Rs4ktCtmnJrzI(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$Qby2DM-6ewhhIPl5MPOAlDB2ptE, reason: not valid java name */
    public static /* synthetic */ void m17584$r8$lambda$Qby2DM6ewhhIPl5MPOAlDB2ptE(BaseFragment r8, boolean r9, int r10, BillingFlowParams.SubscriptionUpdateParams r11, SubscriptionTier r12, BillingResult r13, List r14) {
        AndroidUtilities.runOnUIThread(new PremiumPreviewFragment$$ExternalSyntheticLambda19(r13, r8, r9, r14, r10, r11, r12));
    }

    public static /* synthetic */ void $r8$lambda$QkvXQtb3SkLopnKoxDEgRiQKphU(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$T6YveURGNwm16rQ5iomVjkMc7oU(Runnable r0, BillingResult r1) {
        if (r1.getResponseCode() != 0) goto L6;
        AndroidUtilities.runOnUIThread(r0);
        return;
    }

    public static /* synthetic */ void $r8$lambda$TWgVAVocw5Y8qxg5BYYU_GuQCDg(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$V5qkeJBWXbchWiOpCgFHIoRIIZA(BaseFragment r2, boolean r3) {
        if ((r2 instanceof PremiumPreviewFragment) == false) goto L8;
        PremiumPreviewFragment r0 = (PremiumPreviewFragment) r2;
        if (r3 == false) goto L7;
        r0.setForcePremium();
    L7:
        r0.getMediaDataController().loadPremiumPromo(false);
        r0.listView.smoothScrollToPosition(0);
    L16:
        if (r2 != null) goto L18;
        return;
    L18:
        if ((r2.getParentActivity() instanceof LaunchActivity) == false) goto L26;
        r2.getFragmentView().performHapticFeedback(3, 2);     // Catch: Exception -> L22
    L20:
        ((LaunchActivity) r2.getParentActivity()).getFireworksOverlay().start();
        return;
    L26:
        return;
    L8:
        PremiumPreviewFragment r02 = new PremiumPreviewFragment(null);
        if (r3 == false) goto L11;
        r02.setForcePremium();
    L11:
        if (r2 == null) goto L13;
        r2.presentFragment(r02);
        goto L16
    L13:
        BaseFragment r32 = LaunchActivity.getSafeLastFragment();
        if (r32 == null) goto L16;
        r32.presentFragment(r02);
        goto L16
    }

    public static /* synthetic */ int $r8$lambda$VGbvV68_UIGPz2pW65LE7kxOmig(MessagesController r2, PremiumFeatureData r3, PremiumFeatureData r4) {
        return r2.businessFeaturesTypesToPosition.get(r3.type, Integer.MAX_VALUE) - r2.businessFeaturesTypesToPosition.get(r4.type, Integer.MAX_VALUE);
    }

    public static /* synthetic */ void $r8$lambda$VbBoeJJr1WrGPJHgBrygcEbsbl8(PremiumPreviewFragment r0) {
        r0.lambda$createView$6();
    }

    public static /* synthetic */ void $r8$lambda$X6RQjCo8PyI7akt65JZf0g5FU7g(PremiumPreviewFragment r0) {
        r0.blur3_InvalidateBlur();
    }

    public static /* synthetic */ int $r8$lambda$XiX3KqIoYuK3QCoZvEpeZF39OTQ(MessagesController r2, PremiumFeatureData r3, PremiumFeatureData r4) {
        return r2.premiumFeaturesTypesToPosition.get(r3.type, Integer.MAX_VALUE) - r2.premiumFeaturesTypesToPosition.get(r4.type, Integer.MAX_VALUE);
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$b3szoJXEK5cXV96J73hyUz406us(PremiumPreviewFragment r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$eST3Kx0XKKAYkdtVoELrVrD_yN8(PremiumPreviewFragment r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$createView$2(r1, r2);
    }

    /* renamed from: $r8$lambda$k5w78zd7EGukkl5gJ82iQ-LPhws, reason: not valid java name */
    public static /* synthetic */ void m17585$r8$lambda$k5w78zd7EGukkl5gJ82iQLPhws(int r1, TLRPC.TL_error r2, BaseFragment r3, TLRPC.TL_payments_assignPlayMarketTransaction r4) {
        AlertsCreator.processError(r1, r2, r3, r4, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$qGI3Ta1aYS9fGNAtyqcbubQDrf4(PremiumPreviewFragment r0, View r1) {
        r0.lambda$updateButtonText$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$qsVx08UW1jm3uI3klLUQ7wtXk0k(TLObject r1, BaseFragment r2, TLRPC.TL_inputStorePaymentPremiumSubscription r3, SubscriptionTier r4, BillingFlowParams.SubscriptionUpdateParams r5, int r6, TLRPC.TL_error r7, TLRPC.TL_payments_canPurchaseStore r8) {
        if ((r1 instanceof TLRPC.TL_boolTrue) == false) goto L10;
        if (r2 == null) goto L7;
        Activity r12 = r2.getParentActivity();
    L8:
        Activity r32 = r12;
        BillingController.getInstance().launchBillingFlow(r32, r2.getAccountInstance(), r3, Collections.singletonList(BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS).setOfferToken(r4.getOfferDetails().getOfferToken()).build()), r5, false);
        return;
    L7:
        r12 = AndroidUtilities.getActivity();
        goto L8
    L10:
        AlertsCreator.processError(r6, r7, r2, r8, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$sRbAs05rtw_1lXYTPdTX6gPmuZo(PremiumPreviewFragment r0) {
        r0.lambda$createView$1();
    }

    public static /* synthetic */ void $r8$lambda$wEKFN_MYxaB7w9fM83pHh0lbgzk(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$yi9e13-9mkIgZMz4t6dS8oD5Q-o, reason: not valid java name */
    public static /* synthetic */ void m17586$r8$lambda$yi9e139mkIgZMz4t6dS8oD5Qo(PremiumPreviewFragment r0) {
        r0.updateColors();
    }

    /* renamed from: -$$Nest$fgetbuttonContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m17587$$Nest$fgetbuttonContainer(PremiumPreviewFragment r0) {
        return r0.buttonContainer;
    }

    /* renamed from: -$$Nest$fgetbuttonContainerInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m17588$$Nest$fgetbuttonContainerInternal(PremiumPreviewFragment r0) {
        return r0.buttonContainerInternal;
    }

    /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m17589$$Nest$fgetcontentView(PremiumPreviewFragment r0) {
        return r0.contentView;
    }

    /* renamed from: -$$Nest$fgetcurrentYOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17590$$Nest$fgetcurrentYOffset(PremiumPreviewFragment r0) {
        return r0.currentYOffset;
    }

    /* renamed from: -$$Nest$fgetfirstViewHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17591$$Nest$fgetfirstViewHeight(PremiumPreviewFragment r0) {
        return r0.firstViewHeight;
    }

    /* renamed from: -$$Nest$fgetforcePremium, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17592$$Nest$fgetforcePremium(PremiumPreviewFragment r0) {
        return r0.forcePremium;
    }

    /* renamed from: -$$Nest$fgetinsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Insets m17593$$Nest$fgetinsets(PremiumPreviewFragment r0) {
        return r0.insets;
    }

    /* renamed from: -$$Nest$fgetisDialogVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17594$$Nest$fgetisDialogVisible(PremiumPreviewFragment r0) {
        return r0.isDialogVisible;
    }

    /* renamed from: -$$Nest$fgetnavbarProtectionDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundWithFadeDrawable m17595$$Nest$fgetnavbarProtectionDrawable(PremiumPreviewFragment r0) {
        return r0.navbarProtectionDrawable;
    }

    /* renamed from: -$$Nest$fgetscrollableViewNoiseSuppressor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownscaleScrollableNoiseSuppressor m17596$$Nest$fgetscrollableViewNoiseSuppressor(PremiumPreviewFragment r0) {
        return r0.scrollableViewNoiseSuppressor;
    }

    /* renamed from: -$$Nest$fgetselectAnnualByDefault, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17597$$Nest$fgetselectAnnualByDefault(PremiumPreviewFragment r0) {
        return r0.selectAnnualByDefault;
    }

    /* renamed from: -$$Nest$fgetstatusBarHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17598$$Nest$fgetstatusBarHeight(PremiumPreviewFragment r0) {
        return r0.statusBarHeight;
    }

    /* renamed from: -$$Nest$fgettype, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17599$$Nest$fgettype(PremiumPreviewFragment r0) {
        return r0.type;
    }

    /* renamed from: -$$Nest$fgetwhiteBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17600$$Nest$fgetwhiteBackground(PremiumPreviewFragment r0) {
        return r0.whiteBackground;
    }

    /* renamed from: -$$Nest$fputcurrentYOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17601$$Nest$fputcurrentYOffset(PremiumPreviewFragment r0, int r1) {
        r0.currentYOffset = r1;
    }

    /* renamed from: -$$Nest$fputfirstViewHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17602$$Nest$fputfirstViewHeight(PremiumPreviewFragment r0, int r1) {
        r0.firstViewHeight = r1;
    }

    /* renamed from: -$$Nest$fputselectAnimatedEmojiDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17603$$Nest$fputselectAnimatedEmojiDialog(PremiumPreviewFragment r0, SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow r1) {
        r0.selectAnimatedEmojiDialog = r1;
    }

    /* renamed from: -$$Nest$fputstatusBarHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17604$$Nest$fputstatusBarHeight(PremiumPreviewFragment r0, int r1) {
        r0.statusBarHeight = r1;
    }

    /* renamed from: -$$Nest$mblur3_InvalidateBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17605$$Nest$mblur3_InvalidateBlur(PremiumPreviewFragment r0) {
        r0.blur3_InvalidateBlur();
    }

    /* renamed from: -$$Nest$mmeasureGradient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17606$$Nest$mmeasureGradient(PremiumPreviewFragment r0, int r1, int r2) {
        r0.measureGradient(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateBackgroundImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17607$$Nest$mupdateBackgroundImage(PremiumPreviewFragment r0) {
        r0.updateBackgroundImage();
    }

    /* renamed from: -$$Nest$mupdateButtonText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17608$$Nest$mupdateButtonText(PremiumPreviewFragment r0, boolean r1) {
        r0.updateButtonText(r1);
    }

    public PremiumPreviewFragment(int r8, String r9) {
        this.premiumFeatures = new ArrayList();
        this.morePremiumFeatures = new ArrayList();
        this.subscriptionTiers = new ArrayList();
        boolean r0 = false;
        this.selectedTierIndex = 0;
        this.strokePaint = new Paint(1);
        this.matrix = new Matrix();
        this.gradientPaint = new Paint(1);
        Bitmap r1 = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        this.gradientTextureBitmap = r1;
        this.gradientCanvas = new Canvas(r1);
        this.gradientTools = new PremiumGradient.PremiumGradientTools(Theme.key_premiumGradientBackground1, Theme.key_premiumGradientBackground2, Theme.key_premiumGradientBackground3, Theme.key_premiumGradientBackground4);
        this.insets = Insets.NONE;
        PremiumGradient.PremiumGradientTools r12 = new PremiumGradient.PremiumGradientTools(Theme.key_premiumGradient1, Theme.key_premiumGradient2, -1, -1);
        this.tiersGradientTools = r12;
        r12.exactly = true;
        r12.x1 = 0.0f;
        r12.y1 = 0.0f;
        r12.x2 = 0.0f;
        r12.y2 = 1.0f;
        r12.cx = 0.0f;
        r12.cy = 0.0f;
        ArrayList<RectF> r13 = new ArrayList();
        this.iBlur3Positions = r13;
        RectF r3 = new RectF();
        this.iBlur3PositionMainTabs = r3;
        r13.add(r3);
        this.type = r8;
        if (Theme.isCurrentThemeDark() == true) goto L6;
        if (r8 != 1) goto L6;
        r0 = true;
    L6:
        this.whiteBackground = r0;
        this.source = r9;
        AnonymousClass1 r82 = new AnonymousClass1(this);
        this.iBlur3Source = r82;
        if (Build.VERSION.SDK_INT < 31) goto L9;
        DownscaleScrollableNoiseSuppressor r92 = new DownscaleScrollableNoiseSuppressor(true, true);
        this.scrollableViewNoiseSuppressor = r92;
        BlurredBackgroundSourceRenderNode r02 = new BlurredBackgroundSourceRenderNode(null);
        this.iBlur3SourceGlassFrosted = r02;
        r02.setScrollableNoiseSuppressor(r92, -3);
        r02.setUnderSource(r82);
        this.iBlur3Factory = new BlurredBackgroundDrawableViewFactory(r02);
    L10:
        this.iBlur3FactoryBg = new BlurredBackgroundDrawableViewFactory(r82);
        return;
    L9:
        this.scrollableViewNoiseSuppressor = null;
        this.iBlur3SourceGlassFrosted = null;
        this.iBlur3Factory = new BlurredBackgroundDrawableViewFactory(r82);
        goto L10
    }

    public PremiumPreviewFragment(String r2) {
        this(0, r2);
    }

    public static /* synthetic */ ActionBar access$000(PremiumPreviewFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(PremiumPreviewFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1000(PremiumPreviewFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1100(PremiumPreviewFragment r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1200(PremiumPreviewFragment r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1300(PremiumPreviewFragment r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ ActionBar access$200(PremiumPreviewFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$300(PremiumPreviewFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$400(PremiumPreviewFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$500(PremiumPreviewFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ INavigationLayout access$600(PremiumPreviewFragment r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$700(PremiumPreviewFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ INavigationLayout access$800(PremiumPreviewFragment r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$900(PremiumPreviewFragment r0) {
        return r0.actionBar;
    }

    public static CharSequence applyNewSpan(String r1) {
        return applyNewSpan(r1, -1);
    }

    public static CharSequence applyNewSpan(String r3, int r4) {
        SpannableStringBuilder r0 = new SpannableStringBuilder(r3);
        r0.append("  d");
        FilterCreateActivity.NewSpan r32 = new FilterCreateActivity.NewSpan(false, r4);
        r32.setColor(Theme.getColor(Theme.key_premiumGradient1));
        r0.setSpan(r32, r0.length() - 1, r0.length(), 0);
        return r0;
    }

    private void blur3_InvalidateBlur() {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor == null) goto L10;
        int r0 = this.fragmentView.getMeasuredHeight() + AndroidUtilities.dp(48.0f);
        this.iBlur3PositionMainTabs.set(0.0f, (this.fragmentView.getMeasuredHeight() - this.insets.bottom) - AndroidUtilities.dp(132.0f), this.fragmentView.getMeasuredWidth(), r0);
        this.scrollableViewNoiseSuppressor.setupRenderNodes(this.iBlur3Positions, 1);
        this.scrollableViewNoiseSuppressor.invalidateResultRenderNodes(this.iBlur3Capture, this.fragmentView.getMeasuredWidth(), this.fragmentView.getMeasuredHeight());
        return;
    }

    public static void buyPremium(BaseFragment r1) {
        buyPremium(r1, "settings");
    }

    public static void buyPremium(BaseFragment r2, String r3) {
        buyPremium(r2, null, r3, true);
    }

    public static void buyPremium(BaseFragment r1, SubscriptionTier r2, String r3) {
        buyPremium(r1, r2, r3, true);
    }

    public static void buyPremium(BaseFragment r1, SubscriptionTier r2, String r3, boolean r4) {
        buyPremium(r1, r2, r3, r4, null);
    }

    public static void buyPremium(BaseFragment r7, SubscriptionTier r8, String r9, boolean r10, BillingFlowParams.SubscriptionUpdateParams r11) {
        if (r7 != null) goto L5;
        int r0 = UserConfig.selectedAccount;
    L4:
        int r4 = r0;
        if (MessagesController.getInstance(r4).isFrozen() == false) goto L11;
        AccountFrozenAlert.show(r4);
        return;
    L11:
        if (r8 != null) goto L23;
        TLRPC.TL_help_premiumPromo r102 = MediaDataController.getInstance(r4).getPremiumPromo();
        if (r102 == null) goto L22;
        ArrayList<TLRPC.TL_premiumSubscriptionOption> r103 = r102.period_options;
        int r1 = r103.size();
        int r2 = 0;
    L15:
        if (r2 >= r1) goto L22;
        TLRPC.TL_premiumSubscriptionOption r3 = r103.get(r2);
        r2 = r2 + 1;
        TLRPC.TL_premiumSubscriptionOption r32 = r3;
        int r5 = r32.months;
        if (r5 == 1) goto L18;
        if (r5 != 12) goto L15;
        r8 = new SubscriptionTier(r32);
        goto L22
    L18:
        r8 = new SubscriptionTier(r32);
    L22:
        SubscriptionTier r6 = r8;
        boolean r33 = true;
    L24:
        sentPremiumButtonClick();
        if (BuildVars.useInvoiceBilling() == false) goto L55;
        if (r7 == null) goto L28;
        Activity r72 = r7.getParentActivity();
    L30:
        if ((r72 instanceof LaunchActivity) == false) goto L66;
        LaunchActivity r73 = (LaunchActivity) r72;
        if (r6 == null) goto L47;
        TLRPC.TL_premiumSubscriptionOption r82 = r6.subscriptionOption;
        if (r82 == null) goto L47;
        String r83 = r82.bot_url;
        if (r83 == null) goto L47;
        Uri r84 = Uri.parse(r83);
        if (r84.getHost().equals("t.me") == true) goto L41;
    L45:
        Browser.openUrl(r73, r6.subscriptionOption.bot_url);
        return;
    L41:
        if (r84.getPath().startsWith("/$") == true) goto L45;
        if (r84.getPath().startsWith("/invoice/") == true) goto L45;
        r73.setNavigateToPremiumBot(true);
    L47:
        MessagesController r85 = MessagesController.getInstance(r4);
        if (TextUtils.isEmpty(r85.premiumBotUsername) == true) goto L52;
        r73.setNavigateToPremiumBot(true);
        r73.onNewIntent(new Intent("android.intent.action.VIEW", Uri.parse("https://t.me/" + r85.premiumBotUsername + "?start=" + r9)), null);
        return;
    L52:
        if (TextUtils.isEmpty(r85.premiumInvoiceSlug) == true) goto L76;
        r73.onNewIntent(new Intent("android.intent.action.VIEW", Uri.parse("https://t.me/$" + r85.premiumInvoiceSlug)), null);
        return;
    L76:
        return;
    L66:
        return;
    L28:
        r72 = LaunchActivity.instance;
        goto L30
    L55:
        ProductDetails r86 = BillingController.PREMIUM_PRODUCT_DETAILS;
        if (r86 != null) goto L59;
        return;
    L59:
        if (r86.getSubscriptionOfferDetails().isEmpty() == false) goto L62;
        return;
    L62:
        if (r6.getGooglePlayProductDetails() != null) goto L65;
        r6.setGooglePlayProductDetails(BillingController.PREMIUM_PRODUCT_DETAILS);
    L65:
        if (r6.getOfferDetails() == null) goto L77;
        BillingController.getInstance().queryPurchases("subs", new PremiumPreviewFragment$$ExternalSyntheticLambda12(r7, r33, r4, r11, r6));
        return;
    L77:
        return;
    L23:
        r6 = r8;
        r33 = r10;
        goto L24
    L5:
        r0 = r7.getCurrentAccount();
        goto L4
    }

    private void closeSetting() {
        this.settingsView.animate().translationY(AndroidUtilities.dp(1000.0f)).setListener(new AnonymousClass7(this));
    }

    public static String featureTypeToServerString(int r0) {
        switch(r0) {
            case 0: goto L91;
            case 1: goto L89;
            case 2: goto L87;
            case 3: goto L85;
            case 4: goto L83;
            case 5: goto L81;
            case 6: goto L79;
            case 7: goto L77;
            case 8: goto L75;
            case 9: goto L73;
            case 10: goto L71;
            case 11: goto L69;
            case 12: goto L67;
            case 13: goto L65;
            case 14: goto L63;
            case 15: goto L61;
            case 16: goto L59;
            case 17: goto L57;
            case 18: goto L55;
            case 19: goto L53;
            case 20: goto L51;
            case 21: goto L49;
            case 22: goto L47;
            case 23: goto L45;
            case 24: goto L43;
            case 25: goto L41;
            case 26: goto L39;
            case 27: goto L37;
            case 28: goto L35;
            case 29: goto L33;
            case 30: goto L31;
            case 31: goto L29;
            case 32: goto L27;
            case 33: goto L25;
            case 34: goto L23;
            case 35: goto L21;
            case 36: goto L19;
            case 37: goto L17;
            case 38: goto L15;
            case 39: goto L13;
            case 40: goto L11;
            case 41: goto L9;
            case 42: goto L7;
            case 43: goto L5;
            default: goto L3;
        };
    L3:
        return null;
    L5:
        return "rich_formatting";
    L7:
        return "ai_compose";
    L9:
        return "pm_noforwards";
    L11:
        return "gifts";
    L13:
        return "todo";
    L15:
        return "effects";
    L17:
        return "business_links";
    L19:
        return "business_intro";
    L21:
        return "folder_tags";
    L23:
        return "business_bots";
    L25:
        return "away_message";
    L27:
        return "greeting_message";
    L29:
        return "quick_replies";
    L31:
        return "business_hours";
    L33:
        return "business_location";
    L35:
        return "business";
    L37:
        return "message_privacy";
    L39:
        return "last_seen";
    L41:
        return "stories__quality";
    L43:
        return "saved_tags";
    L45:
        return "peer_colors";
    L47:
        return "wallpapers";
    L49:
        return "stories__caption";
    L51:
        return "stories__priority_order";
    L53:
        return "stories__links_and_formatting";
    L55:
        return "stories__save_stories_to_gallery";
    L57:
        return "stories__expiration_durations";
    L59:
        return "stories__permanent_views_history";
    L61:
        return "stories__stealth_mode";
    L63:
        return "stories";
    L65:
        return "translations";
    L67:
        return "emoji_status";
    L69:
        return "animated_emoji";
    L71:
        return "app_icons";
    L73:
        return "advanced_chat_management";
    L75:
        return "voice_to_text";
    L77:
        return "animated_userpics";
    L79:
        return "profile_badge";
    L81:
        return "premium_stickers";
    L83:
        return "infinite_reactions";
    L85:
        return "no_ads";
    L87:
        return "faster_download";
    L89:
        return "more_upload";
    L91:
        return "double_limits";
    }

    public static void fillBusinessFeaturesList(ArrayList<PremiumFeatureData> r4, int r5, boolean r6) {
        MessagesController r52 = MessagesController.getInstance(r5);
        if (r6 == true) goto L5;
        r4.add(new PremiumFeatureData(29, R.drawable.filled_location, LocaleController.getString(R.string.PremiumBusinessLocation), LocaleController.getString(R.string.PremiumBusinessLocationDescription)));
        r4.add(new PremiumFeatureData(30, R.drawable.filled_premium_hours, LocaleController.getString(R.string.PremiumBusinessOpeningHours), LocaleController.getString(R.string.PremiumBusinessOpeningHoursDescription)));
        r4.add(new PremiumFeatureData(31, R.drawable.filled_open_message, LocaleController.getString(R.string.PremiumBusinessQuickReplies), LocaleController.getString(R.string.PremiumBusinessQuickRepliesDescription)));
        r4.add(new PremiumFeatureData(32, R.drawable.premium_status, LocaleController.getString(R.string.PremiumBusinessGreetingMessages), LocaleController.getString(R.string.PremiumBusinessGreetingMessagesDescription)));
        r4.add(new PremiumFeatureData(33, R.drawable.filled_premium_away, LocaleController.getString(R.string.PremiumBusinessAwayMessages), LocaleController.getString(R.string.PremiumBusinessAwayMessagesDescription)));
        r4.add(new PremiumFeatureData(34, R.drawable.filled_premium_bots, LocaleController.getString(R.string.PremiumBusinessChatbots2), LocaleController.getString(R.string.PremiumBusinessChatbotsDescription)));
        r4.add(new PremiumFeatureData(37, R.drawable.filled_premium_chatlink, LocaleController.getString(R.string.PremiumBusinessChatLinks), LocaleController.getString(R.string.PremiumBusinessChatLinksDescription)));
        r4.add(new PremiumFeatureData(36, R.drawable.filled_premium_intro, LocaleController.getString(R.string.PremiumBusinessIntro), LocaleController.getString(R.string.PremiumBusinessIntroDescription)));
    L7:
        if (r52.businessFeaturesTypesToPosition.size() <= 0) goto L17;
        int r62 = 0;
    L10:
        if (r62 >= r4.size()) goto L17;
        if (r52.businessFeaturesTypesToPosition.get(r4.get(r62).type, -1) != (-1)) goto L16;
        if (BuildVars.DEBUG_VERSION == true) goto L16;
        r4.remove(r62);
        r62 = r62 - 1;
    L16:
        r62 = r62 + 1;
    L17:
        Collections.sort(r4, new PremiumPreviewFragment$$ExternalSyntheticLambda10(r52));
        return;
    L5:
        r4.add(new PremiumFeatureData(12, R.drawable.filled_premium_status2, LocaleController.getString(R.string.PremiumPreviewBusinessEmojiStatus), LocaleController.getString(R.string.PremiumPreviewBusinessEmojiStatusDescription)));
        r4.add(new PremiumFeatureData(35, R.drawable.premium_tags, LocaleController.getString(R.string.PremiumPreviewFolderTags), LocaleController.getString(R.string.PremiumPreviewFolderTagsDescription)));
        r4.add(new PremiumFeatureData(14, R.drawable.filled_premium_camera, LocaleController.getString(R.string.PremiumPreviewBusinessStories), LocaleController.getString(R.string.PremiumPreviewBusinessStoriesDescription)));
        goto L7
    }

    public static void fillPremiumFeaturesList(ArrayList<PremiumFeatureData> r9, int r10, boolean r11) {
        MessagesController r102 = MessagesController.getInstance(r10);
        int r3 = 0;
        r9.add(new PremiumFeatureData(0, R.drawable.msg_premium_limits, LocaleController.getString(R.string.PremiumPreviewLimits), LocaleController.formatString(R.string.PremiumPreviewLimitsDescription, new Object[]{Integer.valueOf(r102.channelsLimitPremium), Integer.valueOf(r102.dialogFiltersLimitPremium), Integer.valueOf(r102.dialogFiltersPinnedLimitPremium), Integer.valueOf(r102.publicLinksLimitPremium), 4})));
        r9.add(new PremiumFeatureData(14, R.drawable.msg_filled_stories, LocaleController.getString(R.string.PremiumPreviewStories), LocaleController.formatString(R.string.PremiumPreviewStoriesDescription, new Object[0])));
        r9.add(new PremiumFeatureData(1, R.drawable.msg_premium_uploads, LocaleController.getString(R.string.PremiumPreviewUploads), LocaleController.getString(R.string.PremiumPreviewUploadsDescription)));
        r9.add(new PremiumFeatureData(2, R.drawable.msg_premium_speed, LocaleController.getString(R.string.PremiumPreviewDownloadSpeed), LocaleController.getString(R.string.PremiumPreviewDownloadSpeedDescription)));
        r9.add(new PremiumFeatureData(8, R.drawable.msg_premium_voice, LocaleController.getString(R.string.PremiumPreviewVoiceToText), LocaleController.getString(R.string.PremiumPreviewVoiceToTextDescription)));
        r9.add(new PremiumFeatureData(3, R.drawable.msg_premium_ads, LocaleController.getString(R.string.PremiumPreviewNoAds), LocaleController.getString(R.string.PremiumPreviewNoAdsDescription)));
        r9.add(new PremiumFeatureData(4, R.drawable.msg_premium_reactions, LocaleController.getString(R.string.PremiumPreviewReactions2), LocaleController.getString(R.string.PremiumPreviewReactions2Description)));
        r9.add(new PremiumFeatureData(5, R.drawable.msg_premium_stickers, LocaleController.getString(R.string.PremiumPreviewStickers), LocaleController.getString(R.string.PremiumPreviewStickersDescription)));
        r9.add(new PremiumFeatureData(11, R.drawable.msg_premium_emoji, LocaleController.getString(R.string.PremiumPreviewEmoji), LocaleController.getString(R.string.PremiumPreviewEmojiDescription)));
        r9.add(new PremiumFeatureData(9, R.drawable.menu_premium_tools, LocaleController.getString(R.string.PremiumPreviewAdvancedChatManagement), LocaleController.getString(R.string.PremiumPreviewAdvancedChatManagementDescription)));
        r9.add(new PremiumFeatureData(6, R.drawable.msg_premium_badge, LocaleController.getString(R.string.PremiumPreviewProfileBadge), LocaleController.getString(R.string.PremiumPreviewProfileBadgeDescription)));
        r9.add(new PremiumFeatureData(27, R.drawable.filled_messages_paid, LocaleController.getString(R.string.PremiumPreviewPaidMessages), LocaleController.getString(R.string.PremiumPreviewPaidMessagesDescription)));
        r9.add(new PremiumFeatureData(7, R.drawable.msg_premium_avatar, LocaleController.getString(R.string.PremiumPreviewAnimatedProfiles), LocaleController.getString(R.string.PremiumPreviewAnimatedProfilesDescription)));
        r9.add(new PremiumFeatureData(24, R.drawable.premium_tags, LocaleController.getString(R.string.PremiumPreviewTags2), LocaleController.getString(R.string.PremiumPreviewTagsDescription2)));
        r9.add(new PremiumFeatureData(12, R.drawable.premium_status, LocaleController.getString(R.string.PremiumPreviewEmojiStatus), LocaleController.getString(R.string.PremiumPreviewEmojiStatusDescription)));
        r9.add(new PremiumFeatureData(13, R.drawable.msg_premium_translate, LocaleController.getString(R.string.PremiumPreviewTranslations), LocaleController.getString(R.string.PremiumPreviewTranslationsDescription)));
        r9.add(new PremiumFeatureData(22, R.drawable.premium_wallpaper, LocaleController.getString(R.string.PremiumPreviewWallpaper), LocaleController.getString(R.string.PremiumPreviewWallpaperDescription)));
        r9.add(new PremiumFeatureData(23, R.drawable.premium_colors, LocaleController.getString(R.string.PremiumPreviewProfileColor), LocaleController.getString(R.string.PremiumPreviewProfileColorDescription)));
        r9.add(new PremiumFeatureData(26, R.drawable.menu_premium_seen, LocaleController.getString(R.string.PremiumPreviewLastSeen), LocaleController.getString(R.string.PremiumPreviewLastSeenDescription)));
        r9.add(new PremiumFeatureData(28, R.drawable.filled_premium_business, LocaleController.getString(R.string.TelegramBusiness), LocaleController.getString(R.string.PremiumPreviewBusinessDescription)));
        r9.add(new PremiumFeatureData(38, R.drawable.menu_premium_effects, LocaleController.getString(R.string.PremiumPreviewEffects), LocaleController.getString(R.string.PremiumPreviewEffectsDescription)));
        r9.add(new PremiumFeatureData(39, R.drawable.msg_premium_icons, LocaleController.getString(R.string.PremiumPreviewTodo), LocaleController.getString(R.string.PremiumPreviewTodoDescription)));
        r9.add(new PremiumFeatureData(41, R.drawable.filled_sharing_off2_24, LocaleController.getString(R.string.PremiumPreviewSharingDisable), LocaleController.getString(R.string.PremiumPreviewSharingDisableDescription)));
        r9.add(new PremiumFeatureData(42, R.drawable.premium_ai_editor, LocaleController.getString(R.string.PremiumPreviewAIEditor), LocaleController.getString(R.string.PremiumPreviewAIEditorDescription)));
        r9.add(new PremiumFeatureData(43, R.drawable.premium_rich_editor, LocaleController.getString(R.string.PremiumPreviewRichEditor), LocaleController.getString(R.string.PremiumPreviewRichEditorDescription)));
        if (r102.premiumFeaturesTypesToPosition.size() > 0) goto L5;
    L12:
        Collections.sort(r9, new PremiumPreviewFragment$$ExternalSyntheticLambda11(r102));
        return;
    L5:
        if (r3 >= r9.size()) goto L12;
        if (r102.premiumFeaturesTypesToPosition.get(r9.get(r3).type, -1) != (-1)) goto L11;
        if (BuildVars.DEBUG_VERSION == true) goto L11;
        r9.remove(r3);
        r3 = r3 - 1;
    L11:
        r3 = r3 + 1;
        goto L5
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String getPremiumButtonText(int r10, SubscriptionTier r11) {
        if (BuildVars.IS_BILLING_UNAVAILABLE == true) goto L5;
        int r0 = R.string.SubscribeToPremium;
        boolean r1 = true;
        int r2 = 0;
        if (r11 != null) goto L56;
        String r6 = null;
        TLRPC.TL_premiumSubscriptionOption r62 = null;
        r6 = null;
        r6 = null;
        if (BuildVars.useInvoiceBilling() == false) goto L34;
        TLRPC.TL_help_premiumPromo r112 = MediaDataController.getInstance(r10).getPremiumPromo();
        if (r112 == null) goto L33;
        ArrayList<TLRPC.TL_premiumSubscriptionOption> r113 = r112.period_options;
        int r7 = r113.size();
    L13:
        if (r2 >= r7) goto L20;
        TLRPC.TL_premiumSubscriptionOption r8 = r113.get(r2);
        r2 = r2 + 1;
        TLRPC.TL_premiumSubscriptionOption r82 = r8;
        int r9 = r82.months;
        if (r9 == 12) goto L16;
        if (r62 != null) goto L13;
        if (r9 != 1) goto L13;
        r62 = r82;
        goto L13
    L16:
        r62 = r82;
    L20:
        if (r62 != null) goto L24;
        return LocaleController.getString(R.string.SubscribeToPremiumNoPrice);
    L24:
        if (r62.months == 12) goto L26;
        String r102 = BillingController.getInstance().formatCurrency(r62.amount, r62.currency);
    L31:
        return LocaleController.formatString(r0, new Object[]{r102});
    L26:
        if (MessagesController.getInstance(r10).showAnnualPerMonth == false) goto L28;
        r102 = BillingController.getInstance().formatCurrency(r62.amount / 12, r62.currency);
        goto L31
    L28:
        r0 = R.string.SubscribeToPremiumPerYear;
        r102 = BillingController.getInstance().formatCurrency(r62.amount, r62.currency);
        goto L31
    L33:
        return LocaleController.getString(R.string.SubscribeToPremiumNoPrice);
    L34:
        ProductDetails r114 = BillingController.PREMIUM_PRODUCT_DETAILS;
        if (r114 == null) goto L50;
        List<ProductDetails.SubscriptionOfferDetails> r115 = r114.getSubscriptionOfferDetails();
        if (r115.isEmpty() == true) goto L50;
        Iterator<ProductDetails.PricingPhase> r116 = r115.get(0).getPricingPhases().getPricingPhaseList().iterator();
    L40:
        if (r116.hasNext() == false) goto L50;
        ProductDetails.PricingPhase r12 = r116.next();
        if (r12.getBillingPeriod().equals("P1M") == true) goto L43;
        if (r12.getBillingPeriod().equals("P1Y") == false) goto L40;
        if (MessagesController.getInstance(r10).showAnnualPerMonth == false) goto L49;
        r6 = BillingController.getInstance().formatCurrency(r12.getPriceAmountMicros() / 12, r12.getPriceCurrencyCode(), 6);
        goto L50
    L49:
        r0 = R.string.SubscribeToPremiumPerYear;
        r6 = BillingController.getInstance().formatCurrency(r12.getPriceAmountMicros(), r12.getPriceCurrencyCode(), 6);
        goto L50
    L43:
        r6 = r12.getFormattedPrice();
    L50:
        if (r6 != null) goto L54;
        return LocaleController.getString(R.string.Loading);
    L54:
        return LocaleController.formatString(r0, new Object[]{r6});
    L56:
        if (BuildVars.useInvoiceBilling() == false) goto L58;
    L61:
        boolean r02 = UserConfig.getInstance(r10).isPremium();
        if (r11.getMonths() > 12) goto L64;
    L66:
        Object[] r4 = false;
    L68:
        if (r11.getMonths() == 12) goto L71;
        r1 = false;
    L71:
        if (r1 == false) goto L73;
        String r5 = r11.getFormattedPricePerYear();
    L74:
        if (r02 == false) goto L78;
        if (r1 == false) goto L77;
        int r103 = R.string.UpgradePremiumPerYear;
    L91:
        return LocaleController.formatString(r103, new Object[]{r5});
    L77:
        r103 = R.string.UpgradePremiumPerMonth;
        goto L91
    L78:
        if (r1 == true) goto L80;
        if (r4 == true) goto L85;
        r103 = R.string.SubscribeToPremium;
        r5 = r11.getFormattedPricePerMonth();
        goto L91
    L85:
        if (MessagesController.getInstance(r10).showAnnualPerMonth == false) goto L88;
        r103 = R.string.SubscribeToPremium;
        r5 = r11.getFormattedPricePerMonth();
        goto L91
    L88:
        return LocaleController.formatString(R.string.SubscribeToPremiumPerCustom, new Object[]{r11.getFormattedPrice(), LocaleController.formatPluralString("Years", r11.getMonths() / 12, new Object[0])});
    L80:
        if (MessagesController.getInstance(r10).showAnnualPerMonth == false) goto L82;
        r103 = R.string.SubscribeToPremium;
        r5 = r11.getFormattedPricePerMonth();
        goto L91
    L82:
        r103 = R.string.SubscribeToPremiumPerYear;
        r5 = r11.getFormattedPrice();
        goto L91
    L73:
        r5 = r11.getFormattedPricePerMonth();
        goto L74
    L64:
        if ((r11.getMonths() % 12) != 0) goto L66;
        r4 = true;
        goto L68
    L58:
        if (r11.getOfferDetails() != null) goto L61;
        return LocaleController.getString(R.string.Loading);
    L5:
        return LocaleController.getString(R.string.SubscribeToPremiumNotAvailable);
    }

    private /* synthetic */ void lambda$createView$0(Canvas r2, RectF r3) {
        RecyclerListView r0 = this.listView;
        Blur3Utils.captureRelativeParent(r0, r2, r3, r0, this.contentView);
    }

    private /* synthetic */ void lambda$createView$1() {
        this.listView.postOnAnimation(new PremiumPreviewFragment$$ExternalSyntheticLambda13(this));
    }

    private /* synthetic */ void lambda$createView$2(TLRPC.TL_error r1, TLObject r2) {
        if (r1 == null) goto L6;
        BulletinFactory.showError(r1);
        return;
    L6:
        if ((r2 instanceof TLRPC.TL_boolTrue) == true) goto L9;
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
        return;
    }

    private /* synthetic */ void lambda$createView$3(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new PremiumPreviewFragment$$ExternalSyntheticLambda20(this, r3, r2));
    }

    private /* synthetic */ void lambda$createView$4(PremiumFeatureCell r5, Long r6, Integer r7) {
        if (r6 != null) goto L5;
        TLRPC.EmojiStatus r72 = new TLRPC.TL_emojiStatusEmpty();
    L9:
        getMessagesController().updateEmojiStatus(r72);
        if (r6 != null) goto L12;
        long r62 = 0;
    L13:
        r5.setEmoji(r62, true);
        return;
    L12:
        r62 = r6.longValue();
        goto L13
    L5:
        TLRPC.TL_emojiStatus r1 = new TLRPC.TL_emojiStatus();
        r1.document_id = r6.longValue();
        if (r7 == null) goto L8;
        r1.flags |= 1;
        r1.until = r7.intValue();
    L8:
        r72 = r1;
        goto L9
    }

    private /* synthetic */ void lambda$createView$5(View r12, int r13) {
        if (getUserConfig().isClientActivated() == true) goto L6;
        return;
    L6:
        if (r13 != this.showAdsRow) goto L13;
        TLRPC.UserFull r132 = getMessagesController().getUserFull(getUserConfig().getClientUserId());
        if (r132 == null) goto L77;
        TextCell r122 = (TextCell) r12;
        r122.setChecked(!r122.isChecked());
        r132.sponsored_enabled = r122.isChecked();
        TL_account.toggleSponsoredMessages r123 = new TL_account.toggleSponsoredMessages();
        r123.enabled = r132.sponsored_enabled;
        getConnectionsManager().sendRequest(r123, new PremiumPreviewFragment$$ExternalSyntheticLambda14(this));
        getMessagesStorage().updateUserInfo(r132, false);
        return;
    L77:
        return;
    L13:
        if ((r12 instanceof PremiumFeatureCell) == false) goto L74;
        PremiumFeatureCell r124 = (PremiumFeatureCell) r12;
        SubscriptionTier r0 = null;
        if (this.type == 1) goto L17;
    L62:
        sentShowFeaturePreview(this.currentAccount, r124.data.type);
        int r133 = this.selectedTierIndex;
        if (r133 >= 0) goto L65;
    L68:
        SubscriptionTier r10 = r0;
        Context r5 = getContext();
        int r6 = this.currentAccount;
        if (this.type != 1) goto L71;
        boolean r7 = true;
    L72:
        showDialog(new PremiumFeatureBottomSheet(this, r5, r6, r7, r124.data.type, false, r10));
        return;
    L71:
        r7 = false;
        goto L72
    L65:
        if (r133 >= this.subscriptionTiers.size()) goto L68;
        r0 = this.subscriptionTiers.get(this.selectedTierIndex);
        goto L68
    L17:
        if (getUserConfig().isPremium() == false) goto L62;
        int r134 = r124.data.type;
        if (r134 != 29) goto L23;
        presentFragment(new org.telegram.ui.Business.LocationActivity());
        return;
    L23:
        if (r134 != 32) goto L27;
        presentFragment(new GreetMessagesActivity());
        return;
    L27:
        if (r134 != 33) goto L31;
        presentFragment(new AwayMessagesActivity());
        return;
    L31:
        if (r134 != 30) goto L35;
        presentFragment(new OpeningHoursActivity());
        return;
    L35:
        if (r134 != 34) goto L39;
        presentFragment(new ChatbotsActivity());
        return;
    L39:
        if (r134 != 31) goto L43;
        presentFragment(new QuickRepliesActivity());
        return;
    L43:
        if (r134 != 14) goto L47;
        Bundle r125 = new Bundle();
        r125.putLong("dialog_id", UserConfig.getInstance(this.currentAccount).getClientUserId());
        r125.putInt("type", 1);
        presentFragment(new MediaActivity(r125, null));
        return;
    L47:
        if (r134 != 12) goto L51;
        showSelectStatusDialog(r124, UserObject.getEmojiStatusDocumentId(getUserConfig().getCurrentUser()), new PremiumPreviewFragment$$ExternalSyntheticLambda15(this, r124));
        return;
    L51:
        if (r134 != 35) goto L55;
        presentFragment(new FiltersSetupActivity().highlightTags());
        return;
    L55:
        if (r134 != 36) goto L59;
        presentFragment(new BusinessIntroActivity());
        return;
    L59:
        if (r134 != 37) goto L75;
        presentFragment(new BusinessLinksActivity());
        return;
    L75:
        return;
    }

    private /* synthetic */ void lambda$createView$6() {
        getMediaDataController().loadPremiumPromo(false);
    }

    private /* synthetic */ void lambda$updateButtonText$17(View r1) {
        buyPremium(this);
    }

    private /* synthetic */ void lambda$updateButtonText$19(View r4) {
        SubscriptionTier r42 = this.subscriptionTiers.get(this.selectedTierIndex);
        SubscriptionTier r0 = this.currentSubscriptionTier;
        if (r0 == null) goto L9;
        TLRPC.TL_premiumSubscriptionOption r02 = r0.subscriptionOption;
        if (r02 == null) goto L9;
        if (r02.transaction == null) goto L9;
        BillingFlowParams.SubscriptionUpdateParams r03 = BillingFlowParams.SubscriptionUpdateParams.newBuilder().setOldPurchaseToken(BillingController.getInstance().getLastPremiumToken()).setSubscriptionReplacementMode(5).build();
    L10:
        buyPremium(this, r42, "settings", true, r03);
        return;
    L9:
        r03 = null;
        goto L10
    }

    private void measureGradient(int r9, int r10) {
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= this.premiumFeatures.size()) goto L6;
        this.dummyCell.setData(this.premiumFeatures.get(r1), false);
        this.dummyCell.measure(View.MeasureSpec.makeMeasureSpec(r9, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r10, Integer.MIN_VALUE));
        this.premiumFeatures.get(r1).yOffset = r2;
        r2 = r2 + this.dummyCell.getMeasuredHeight();
        r1 = r1 + 1;
        goto L4
    L6:
        int r12 = 0;
    L8:
        if (r12 >= this.morePremiumFeatures.size()) goto L10;
        this.dummyCell.setData(this.morePremiumFeatures.get(r12), false);
        this.dummyCell.measure(View.MeasureSpec.makeMeasureSpec(r9, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r10, Integer.MIN_VALUE));
        this.morePremiumFeatures.get(r12).yOffset = r2;
        r2 = r2 + this.dummyCell.getMeasuredHeight();
        r12 = r12 + 1;
        goto L8
    L10:
        this.totalGradientHeight = r2;
    }

    private WindowInsetsCompat onApplyWindowInsets(View r4, WindowInsetsCompat r5) {
        Insets r52 = AndroidUtilities.getDefaultWindowInsets(r5, false);
        this.insets = r52;
        this.listView.setPadding(0, r52.top, 0, AndroidUtilities.dp(48.0f) + this.insets.bottom);
        RecyclerListView r53 = this.listView;
        Insets r0 = this.insets;
        AndroidUtilities.setViewLayoutMargins(r53, r0.left, 0, r0.right, 0);
        BackgroundView r54 = this.backgroundView;
        Insets r02 = this.insets;
        r54.setPadding(r02.left, 0, r02.right, 0);
        FrameLayout r42 = this.buttonContainer;
        if (r42 == null) goto L6;
        int r55 = this.insets.left;
        int r03 = AndroidUtilities.dp(14.0f);
        Insets r3 = this.insets;
        r42.setPadding(r55, r03, r3.right, r3.bottom);
    L6:
        return WindowInsetsCompat.CONSUMED;
    }

    public static void sentPremiumButtonClick() {
        TLRPC.TL_help_saveAppLog r0 = new TLRPC.TL_help_saveAppLog();
        TLRPC.TL_inputAppEvent r1 = new TLRPC.TL_inputAppEvent();
        r1.time = ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime();
        r1.type = "premium.promo_screen_accept";
        r1.data = new TLRPC.TL_jsonNull();
        r0.events.add(r1);
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r0, new PremiumPreviewFragment$$ExternalSyntheticLambda18());
    }

    public static void sentPremiumBuyCanceled() {
        TLRPC.TL_help_saveAppLog r0 = new TLRPC.TL_help_saveAppLog();
        TLRPC.TL_inputAppEvent r1 = new TLRPC.TL_inputAppEvent();
        r1.time = ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime();
        r1.type = "premium.promo_screen_fail";
        r1.data = new TLRPC.TL_jsonNull();
        r0.events.add(r1);
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r0, new PremiumPreviewFragment$$ExternalSyntheticLambda16());
    }

    public static void sentShowFeaturePreview(int r5, int r6) {
        TLRPC.TL_help_saveAppLog r0 = new TLRPC.TL_help_saveAppLog();
        TLRPC.TL_inputAppEvent r1 = new TLRPC.TL_inputAppEvent();
        r1.time = ConnectionsManager.getInstance(r5).getCurrentTime();
        r1.type = "premium.promo_screen_tap";
        TLRPC.TL_jsonObject r2 = new TLRPC.TL_jsonObject();
        r1.data = r2;
        TLRPC.TL_jsonObjectValue r3 = new TLRPC.TL_jsonObjectValue();
        String r62 = featureTypeToServerString(r6);
        if (r62 == null) goto L5;
        TLRPC.TL_jsonString r4 = new TLRPC.TL_jsonString();
        r4.value = r62;
        r3.value = r4;
    L6:
        r3.key = PluginsConstants.Settings.ITEM;
        r2.value.add(r3);
        r0.events.add(r1);
        ConnectionsManager.getInstance(r5).sendRequest(r0, new PremiumPreviewFragment$$ExternalSyntheticLambda17());
        return;
    L5:
        r3.value = new TLRPC.TL_jsonNull();
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void sentShowScreenStat(String r6) {
        ConnectionsManager r0 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        TLRPC.TL_help_saveAppLog r1 = new TLRPC.TL_help_saveAppLog();
        TLRPC.TL_inputAppEvent r2 = new TLRPC.TL_inputAppEvent();
        r2.time = r0.getCurrentTime();
        r2.type = "premium.promo_screen_show";
        TLRPC.TL_jsonObject r3 = new TLRPC.TL_jsonObject();
        r2.data = r3;
        TLRPC.TL_jsonObjectValue r4 = new TLRPC.TL_jsonObjectValue();
        if (r6 == null) goto L5;
        TLRPC.TL_jsonString r5 = new TLRPC.TL_jsonString();
        r5.value = r6;
        TLRPC.TL_jsonNull r52 = r5;
    L6:
        r4.key = "source";
        r4.value = r52;
        r3.value.add(r4);
        r1.events.add(r2);
        r0.sendRequest(r1, new PremiumPreviewFragment$$ExternalSyntheticLambda9());
        return;
    L5:
        r52 = new TLRPC.TL_jsonNull();
        goto L6
    }

    public static int serverStringToFeatureType(String r24) {
        r24.getClass();
        switch(r24.hashCode()) {
            case -2145993328: goto L178;
            case -2080028929: goto L174;
            case -2022719725: goto L170;
            case -1975141450: goto L166;
            case -1953490480: goto L162;
            case -1952597715: goto L158;
            case -1949982086: goto L154;
            case -1924078036: goto L150;
            case -1919626711: goto L146;
            case -1884266413: goto L142;
            case -1833928446: goto L138;
            case -1777612460: goto L134;
            case -1761847571: goto L130;
            case -1755514268: goto L126;
            case -1529105743: goto L122;
            case -1516580016: goto L118;
            case -1425144150: goto L114;
            case -1225497630: goto L110;
            case -1146830912: goto L106;
            case -1040323278: goto L102;
            case -1023650261: goto L98;
            case -969043445: goto L94;
            case -730864243: goto L90;
            case -448825858: goto L86;
            case -294265343: goto L82;
            case -165039170: goto L78;
            case -96210874: goto L74;
            case 3565638: goto L70;
            case 98352451: goto L66;
            case 110781770: goto L62;
            case 234735554: goto L58;
            case 388416338: goto L54;
            case 480338102: goto L50;
            case 622623867: goto L46;
            case 629542059: goto L42;
            case 705083174: goto L38;
            case 1054743185: goto L34;
            case 1080006662: goto L30;
            case 1219849581: goto L26;
            case 1438966047: goto L22;
            case 1488814760: goto L18;
            case 1537309393: goto L14;
            case 1832801148: goto L10;
            case 2013274756: goto L6;
            default: goto L4;
        };
    L4:
        char r0 = 65535;
    L181:
        switch(r0) {
            case 0: goto L249;
            case 1: goto L248;
            case 2: goto L246;
            case 3: goto L244;
            case 4: goto L242;
            case 5: goto L240;
            case 6: goto L238;
            case 7: goto L236;
            case 8: goto L235;
            case 9: goto L234;
            case 10: goto L232;
            case 11: goto L230;
            case 12: goto L228;
            case 13: goto L227;
            case 14: goto L225;
            case 15: goto L223;
            case 16: goto L222;
            case 17: goto L221;
            case 18: goto L219;
            case 19: goto L218;
            case 20: goto L217;
            case 21: goto L216;
            case 22: goto L215;
            case 23: goto L214;
            case 24: goto L212;
            case 25: goto L211;
            case 26: goto L210;
            case 27: goto L208;
            case 28: goto L206;
            case 29: goto L204;
            case 30: goto L203;
            case 31: goto L202;
            case 32: goto L200;
            case 33: goto L198;
            case 34: goto L196;
            case 35: goto L195;
            case 36: goto L193;
            case 37: goto L192;
            case 38: goto L191;
            case 39: goto L190;
            case 40: goto L188;
            case 41: goto L186;
            case 42: goto L185;
            case 43: goto L183;
            default: goto L182;
        };
    L182:
        return -1;
    L183:
        return 26;
    L185:
        return 10;
    L186:
        return 24;
    L188:
        return 43;
    L190:
        return 16;
    L191:
        return 9;
    L192:
        return 19;
    L193:
        return 41;
    L195:
        return 20;
    L196:
        return 34;
    L198:
        return 42;
    L200:
        return 31;
    L202:
        return 15;
    L203:
        return 17;
    L204:
        return 35;
    L206:
        return 40;
    L208:
        return 39;
    L210:
        return 0;
    L211:
        return 5;
    L212:
        return 32;
    L214:
        return 2;
    L215:
        return 6;
    L216:
        return 12;
    L217:
        return 1;
    L218:
        return 3;
    L219:
        return 28;
    L221:
        return 13;
    L222:
        return 11;
    L223:
        return 27;
    L225:
        return 22;
    L227:
        return 8;
    L228:
        return 23;
    L230:
        return 29;
    L232:
        return 38;
    L234:
        return 14;
    L235:
        return 18;
    L236:
        return 25;
    L238:
        return 37;
    L240:
        return 36;
    L242:
        return 30;
    L244:
        return 33;
    L246:
        return 21;
    L248:
        return 4;
    L249:
        return 7;
    L6:
        if (r24.equals("last_seen") == false) goto L4;
        r0 = SignatureVisitor.EXTENDS;
        goto L181
    L10:
        if (r24.equals("app_icons") == false) goto L4;
        r0 = '*';
        goto L181
    L14:
        if (r24.equals("saved_tags") == false) goto L4;
        r0 = ')';
        goto L181
    L18:
        if (r24.equals("rich_formatting") == false) goto L4;
        r0 = '(';
        goto L181
    L22:
        if (r24.equals("stories__permanent_views_history") == false) goto L4;
        r0 = '\'';
        goto L181
    L26:
        if (r24.equals("advanced_chat_management") == false) goto L4;
        r0 = Typography.amp;
        goto L181
    L30:
        if (r24.equals("stories__links_and_formatting") == false) goto L4;
        r0 = '%';
        goto L181
    L34:
        if (r24.equals("pm_noforwards") == false) goto L4;
        r0 = Typography.dollar;
        goto L181
    L38:
        if (r24.equals("stories__priority_order") == false) goto L4;
        r0 = '#';
        goto L181
    L42:
        if (r24.equals("business_bots") == false) goto L4;
        r0 = Typography.quote;
        goto L181
    L46:
        if (r24.equals("ai_compose") == false) goto L4;
        r0 = '!';
        goto L181
    L50:
        if (r24.equals("quick_replies") == false) goto L4;
        r0 = ' ';
        goto L181
    L54:
        if (r24.equals("stories__stealth_mode") == false) goto L4;
        r0 = 31;
        goto L181
    L58:
        if (r24.equals("stories__expiration_durations") == false) goto L4;
        r0 = 30;
        goto L181
    L62:
        if (r24.equals("folder_tags") == false) goto L4;
        r0 = 29;
        goto L181
    L66:
        if (r24.equals("gifts") == false) goto L4;
        r0 = 28;
        goto L181
    L70:
        if (r24.equals("todo") == false) goto L4;
        r0 = 27;
        goto L181
    L74:
        if (r24.equals("double_limits") == false) goto L4;
        r0 = 26;
        goto L181
    L78:
        if (r24.equals("premium_stickers") == false) goto L4;
        r0 = 25;
        goto L181
    L82:
        if (r24.equals("greeting_message") == false) goto L4;
        r0 = 24;
        goto L181
    L86:
        if (r24.equals("faster_download") == false) goto L4;
        r0 = 23;
        goto L181
    L90:
        if (r24.equals("profile_badge") == false) goto L4;
        r0 = 22;
        goto L181
    L94:
        if (r24.equals("emoji_status") == false) goto L4;
        r0 = 21;
        goto L181
    L98:
        if (r24.equals("more_upload") == false) goto L4;
        r0 = 20;
        goto L181
    L102:
        if (r24.equals("no_ads") == false) goto L4;
        r0 = 19;
        goto L181
    L106:
        if (r24.equals("business") == false) goto L4;
        r0 = 18;
        goto L181
    L110:
        if (r24.equals("translations") == false) goto L4;
        r0 = 17;
        goto L181
    L114:
        if (r24.equals("animated_emoji") == false) goto L4;
        r0 = 16;
        goto L181
    L118:
        if (r24.equals("message_privacy") == false) goto L4;
        r0 = 15;
        goto L181
    L122:
        if (r24.equals("wallpapers") == false) goto L4;
        r0 = 14;
        goto L181
    L126:
        if (r24.equals("voice_to_text") == false) goto L4;
        r0 = '\r';
        goto L181
    L130:
        if (r24.equals("peer_colors") == false) goto L4;
        r0 = '\f';
        goto L181
    L134:
        if (r24.equals("business_location") == false) goto L4;
        r0 = 11;
        goto L181
    L138:
        if (r24.equals("effects") == false) goto L4;
        r0 = '\n';
        goto L181
    L142:
        if (r24.equals("stories") == false) goto L4;
        r0 = '\t';
        goto L181
    L146:
        if (r24.equals("stories__save_stories_to_gallery") == false) goto L4;
        r0 = '\b';
        goto L181
    L150:
        if (r24.equals("stories__quality") == false) goto L4;
        r0 = 7;
        goto L181
    L154:
        if (r24.equals("business_links") == false) goto L4;
        r0 = 6;
        goto L181
    L158:
        if (r24.equals("business_intro") == false) goto L4;
        r0 = 5;
        goto L181
    L162:
        if (r24.equals("business_hours") == false) goto L4;
        r0 = 4;
        goto L181
    L166:
        if (r24.equals("away_message") == false) goto L4;
        r0 = 3;
        goto L181
    L170:
        if (r24.equals("stories__caption") == false) goto L4;
        r0 = 2;
        goto L181
    L174:
        if (r24.equals("infinite_reactions") == false) goto L4;
        r0 = 1;
        goto L181
    L178:
        if (r24.equals("animated_userpics") == false) goto L4;
        r0 = 0;
        goto L181
    }

    private void updateBackgroundImage() {
        if (this.contentView.getMeasuredWidth() != 0) goto L5;
        return;
    L5:
        if (this.contentView.getMeasuredHeight() == 0) goto L18;
        BackgroundView r0 = this.backgroundView;
        if (r0 != null) goto L9;
        return;
    L9:
        if (BackgroundView.m17611$$Nest$fgetimageView(r0) != null) goto L12;
        return;
    L12:
        if (this.whiteBackground == false) goto L15;
        Bitmap r02 = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        new Canvas(r02).drawColor(ColorUtils.blendARGB(getThemedColor(Theme.key_premiumGradient2), getThemedColor(Theme.key_dialogBackground), 0.5f));
        BackgroundView.m17611$$Nest$fgetimageView(this.backgroundView).setBackgroundBitmap(r02);
        return;
    L15:
        this.gradientTools.gradientMatrix(0, 0, this.contentView.getMeasuredWidth(), this.contentView.getMeasuredHeight(), 0.0f, 0.0f);
        this.gradientCanvas.save();
        this.gradientCanvas.scale(100.0f / this.contentView.getMeasuredWidth(), 100.0f / this.contentView.getMeasuredHeight());
        this.gradientCanvas.drawRect(0.0f, 0.0f, this.contentView.getMeasuredWidth(), this.contentView.getMeasuredHeight(), this.gradientTools.paint);
        this.gradientCanvas.restore();
        BackgroundView.m17611$$Nest$fgetimageView(this.backgroundView).setBackgroundBitmap(this.gradientTextureBitmap);
        return;
    }

    private void updateButtonText(boolean r7) {
        if (this.premiumButtonView != null) goto L6;
        return;
    L6:
        if (getUserConfig().isPremium() == false) goto L15;
        if (this.currentSubscriptionTier == null) goto L15;
        if (this.selectedTierIndex >= this.subscriptionTiers.size()) goto L15;
        if (this.subscriptionTiers.get(this.selectedTierIndex).getMonths() >= this.currentSubscriptionTier.getMonths()) goto L15;
        return;
    L15:
        if (LocaleController.isRTL == false) goto L18;
        r7 = false;
    L18:
        if (BuildVars.IS_BILLING_UNAVAILABLE == false) goto L24;
        if (this.selectedTierIndex >= this.subscriptionTiers.size()) goto L24;
        this.premiumButtonView.setButton(getPremiumButtonText(this.currentAccount, this.subscriptionTiers.get(this.selectedTierIndex)), null, r7);
        this.buttonContainerInternal.setOnClickListener(new PremiumPreviewFragment$$ExternalSyntheticLambda0(this));
        return;
    L24:
        if (BuildVars.useInvoiceBilling() == true) goto L36;
        if (BillingController.getInstance().isReady() == true) goto L28;
    L33:
        this.premiumButtonView.setButton(LocaleController.getString(R.string.Loading), null, r7);
        this.buttonContainerInternal.setOnClickListener(new PremiumPreviewFragment$$ExternalSyntheticLambda1());
        this.premiumButtonView.setFlickerDisabled(true);
        return;
    L28:
        if (this.subscriptionTiers.isEmpty() == true) goto L33;
        if (this.selectedTierIndex >= this.subscriptionTiers.size()) goto L33;
        if (SubscriptionTier.m17615$$Nest$fgetgooglePlayProductDetails(this.subscriptionTiers.get(this.selectedTierIndex)) == null) goto L33;
    L36:
        if (this.subscriptionTiers.isEmpty() == false) goto L38;
        return;
    L38:
        if (this.selectedTierIndex >= this.subscriptionTiers.size()) goto L42;
        this.premiumButtonView.setButton(getPremiumButtonText(this.currentAccount, this.subscriptionTiers.get(this.selectedTierIndex)), null, r7);
        this.buttonContainerInternal.setOnClickListener(new PremiumPreviewFragment$$ExternalSyntheticLambda2(this));
        this.premiumButtonView.setFlickerDisabled(false);
        return;
    }

    private void updateColors() {
        if (this.backgroundView == null) goto L34;
        ActionBar r0 = this.actionBar;
        if (r0 != null) goto L8;
        return;
    L8:
        if (this.whiteBackground == false) goto L10;
        int r1 = Theme.key_windowBackgroundWhiteBlackText;
    L11:
        r0.setItemsColor(Theme.getColor(r1), true);
        ActionBar r02 = this.actionBar;
        if (this.whiteBackground == false) goto L14;
        int r12 = Theme.key_windowBackgroundWhiteBlackText;
    L15:
        r02.setItemsColor(Theme.getColor(r12), false);
        ActionBar r03 = this.actionBar;
        int r13 = Theme.key_premiumGradientBackgroundOverlay;
        r03.setItemsBackgroundColor(ColorUtils.setAlphaComponent(Theme.getColor(r13), 60), false);
        this.particlesView.drawable.updateColors();
        BackgroundView r04 = this.backgroundView;
        if (r04 == null) goto L32;
        TextView r05 = r04.titleView;
        if (this.whiteBackground == false) goto L20;
        int r2 = Theme.key_windowBackgroundWhiteBlackText;
    L21:
        r05.setTextColor(Theme.getColor(r2));
        TextView r06 = BackgroundView.m17612$$Nest$fgetsubtitleView(this.backgroundView);
        if (this.whiteBackground == false) goto L24;
        r13 = Theme.key_windowBackgroundWhiteBlackText;
    L24:
        r06.setTextColor(Theme.getColor(r13));
        if (BackgroundView.m17611$$Nest$fgetimageView(this.backgroundView) == null) goto L32;
        if (BackgroundView.m17611$$Nest$fgetimageView(this.backgroundView).mRenderer == null) goto L32;
        if (this.whiteBackground == false) goto L31;
        GLIconRenderer r07 = BackgroundView.m17611$$Nest$fgetimageView(this.backgroundView).mRenderer;
        r07.colorKey1 = Theme.key_premiumCoinGradient1;
        GLIconRenderer r08 = BackgroundView.m17611$$Nest$fgetimageView(this.backgroundView).mRenderer;
        r08.colorKey2 = Theme.key_premiumCoinGradient2;
    L31:
        BackgroundView.m17611$$Nest$fgetimageView(this.backgroundView).mRenderer.updateColors();
        goto L32
    L20:
        r2 = r13;
    L32:
        updateBackgroundImage();
        return;
    L14:
        r12 = Theme.key_premiumGradientBackgroundOverlay;
        goto L15
    L10:
        r1 = Theme.key_premiumGradientBackgroundOverlay;
        goto L11
    }

    private void updateDialogVisibility(boolean r2) {
        if (r2 == this.isDialogVisible) goto L11;
        this.isDialogVisible = r2;
        BackgroundView r0 = this.backgroundView;
        if (r0 != null) goto L7;
    L9:
        this.particlesView.setPaused(r2);
        this.contentView.invalidate();
        return;
    L7:
        if (BackgroundView.m17611$$Nest$fgetimageView(r0) == null) goto L9;
        BackgroundView.m17611$$Nest$fgetimageView(this.backgroundView).setDialogVisible(r2);
        goto L9
    }

    private void updateRows() {
        this.sectionRow = -1;
        this.privacyRow = -1;
        this.moreHeaderRow = -1;
        this.moreFeaturesStartRow = -1;
        this.moreFeaturesEndRow = -1;
        this.showAdsHeaderRow = -1;
        this.showAdsRow = -1;
        this.showAdsInfoRow = -1;
        boolean r0 = true;
        this.rowCount = 1;
        int r1 = 0;
        this.paddingRow = 0;
        this.featuresStartRow = 1;
        int r2 = this.premiumFeatures.size() + 1;
        this.rowCount = r2;
        this.featuresEndRow = r2;
        if (this.type == 1) goto L5;
    L7:
        int r22 = this.rowCount;
        this.statusRow = r22;
        this.rowCount = r22 + 2;
        this.lastPaddingRow = r22 + 1;
        if (this.type == 1) goto L10;
    L12:
        FrameLayout r23 = this.buttonContainer;
        if (getUserConfig().isPremium() == false) goto L22;
        SubscriptionTier r3 = this.currentSubscriptionTier;
        if (r3 != null) goto L17;
    L21:
        r0 = false;
        goto L22
    L17:
        if (r3.getMonths() >= this.subscriptionTiers.get(this.selectedTierIndex).getMonths()) goto L21;
        if (this.forcePremium == true) goto L21;
    L22:
        AndroidUtilities.updateViewVisibilityAnimated(r23, r0, 1.0f, false);
        if (this.buttonContainer.getVisibility() != 0) goto L25;
        r1 = AndroidUtilities.dp(64.0f);
    L25:
        this.layoutManager.setAdditionalHeight((this.statusBarHeight + r1) - AndroidUtilities.dp(16.0f));
        this.layoutManager.setMinimumLastViewHeight(r1);
        return;
    L10:
        if (getUserConfig().isPremium() == false) goto L12;
        int r24 = this.rowCount;
        this.showAdsHeaderRow = r24;
        this.showAdsRow = r24 + 1;
        this.rowCount = r24 + 3;
        this.showAdsInfoRow = r24 + 2;
        goto L12
    L5:
        if (getUserConfig().isPremium() == false) goto L7;
        int r25 = this.rowCount;
        int r32 = r25 + 1;
        this.sectionRow = r25;
        int r26 = r25 + 2;
        this.rowCount = r26;
        this.moreHeaderRow = r32;
        this.moreFeaturesStartRow = r26;
        int r27 = r26 + this.morePremiumFeatures.size();
        this.rowCount = r27;
        this.moreFeaturesEndRow = r27;
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean canBeginSlide() {
        BackgroundView r0 = this.backgroundView;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (BackgroundView.m17611$$Nest$fgetimageView(r0) != null) goto L7;
        return true;
    L7:
        if (BackgroundView.m17611$$Nest$fgetimageView(this.backgroundView).touched == false) goto L14;
        return false;
    L14:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    @SuppressLint({"NotifyDataSetChanged"})
    public View createView(Context r22) {
        this.iBlur3Capture = new PremiumPreviewFragment$$ExternalSyntheticLambda4(this);
        this.hasOwnBackground = true;
        Shader.TileMode r20 = Shader.TileMode.CLAMP;
        LinearGradient r3 = new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(28.0f), new int[]{1308622847, 0, 452984831}, new float[]{0.0f, 0.5f, 1.0f}, r20);
        this.strokeShader = r3;
        this.strokePaint.setShader(r3);
        this.strokePaint.setStyle(Paint.Style.STROKE);
        int r32 = Theme.getColor(Theme.key_premiumGradient4);
        int r4 = Theme.getColor(Theme.key_premiumGradient3);
        int r5 = Theme.key_premiumGradient2;
        LinearGradient r13 = new LinearGradient(0.0f, 0.0f, 0.0f, 100.0f, new int[]{r32, r4, Theme.getColor(r5), Theme.getColor(Theme.key_premiumGradient1), Theme.getColor(Theme.key_premiumGradient0)}, new float[]{0.0f, 0.32f, 0.5f, 0.7f, 1.0f}, r20);
        this.shader = r13;
        r13.setLocalMatrix(this.matrix);
        this.gradientPaint.setShader(this.shader);
        this.dummyCell = new PremiumFeatureCell(r22);
        this.dummyTierCell = new PremiumTierCell(r22);
        this.premiumFeatures.clear();
        this.morePremiumFeatures.clear();
        int r33 = this.type;
        ArrayList<PremiumFeatureData> r42 = this.premiumFeatures;
        if (r33 != 0) goto L5;
        fillPremiumFeaturesList(r42, this.currentAccount, false);
    L11:
        Rect r34 = new Rect();
        Drawable r43 = r22.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        this.shadowDrawable = r43;
        r43.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_dialogBackground), PorterDuff.Mode.MULTIPLY));
        this.shadowDrawable.getPadding(r34);
        this.statusBarHeight = AndroidUtilities.statusBarHeight;
        this.contentView = new AnonymousClass2(this, r22);
        this.iBlur3Factory.setSourceRootView(new ViewPositionWatcher(this.contentView), this.contentView);
        this.iBlur3FactoryBg.setSourceRootView(new ViewPositionWatcher(this.contentView), this.contentView);
        RecyclerListView r35 = new RecyclerListView(r22);
        this.listView = r35;
        r35.setClipToOutline(true);
        this.listView.setOutlineProvider(new AnonymousClass3(this));
        this.listView.addEdgeEffectListener(new PremiumPreviewFragment$$ExternalSyntheticLambda5(this));
        this.listView.setCaptureSectionsDecoratorAllowed(true);
        this.listView.setSections(true);
        this.listView.setClipToPadding(false);
        RecyclerListView r36 = this.listView;
        FillLastLinearLayoutManager r44 = new FillLastLinearLayoutManager(r22, (AndroidUtilities.dp(68.0f) + this.statusBarHeight) - AndroidUtilities.dp(16.0f), this.listView);
        this.layoutManager = r44;
        r36.setLayoutManager(r44);
        this.layoutManager.setFixedLastItemHeight();
        this.listView.setAdapter(new Adapter(this, null));
        this.listView.addOnScrollListener(new AnonymousClass4(this));
        this.backgroundView = new BackgroundView(this, r22);
        StarParticlesView r37 = new StarParticlesView(r22);
        this.particlesView = r37;
        r37.setClipWithGradient();
        if (this.type != 1) goto L17;
        boolean r38 = this.whiteBackground;
        StarParticlesView r45 = this.particlesView;
        if (r38 == false) goto L16;
        StarParticlesView.Drawable r39 = r45.drawable;
        r39.useGradient = true;
        r39.useBlur = false;
        r39.checkBounds = true;
        r39.isCircle = true;
        r39.centerOffsetY = AndroidUtilities.dp(-14.0f);
        StarParticlesView.Drawable r310 = this.particlesView.drawable;
        r310.minLifeTime = 2000;
        r310.randLifeTime = 3000;
        r310.size1 = 16;
        r310.useRotate = false;
        r310.type = 28;
        r310.colorKey = r5;
        goto L17
    L16:
        StarParticlesView.Drawable r311 = r45.drawable;
        r311.isCircle = true;
        r311.centerOffsetY = AndroidUtilities.dp(28.0f);
        StarParticlesView.Drawable r312 = this.particlesView.drawable;
        r312.minLifeTime = 2000;
        r312.randLifeTime = 3000;
        r312.size1 = 16;
        r312.useRotate = false;
        r312.type = 28;
    L17:
        BackgroundView.m17611$$Nest$fgetimageView(this.backgroundView).setStarParticlesView(this.particlesView);
        this.contentView.addView(this.particlesView, LayoutHelper.createFrame(-1, -2.0f));
        this.contentView.addView(this.backgroundView, LayoutHelper.createFrame(-1, -2.0f));
        this.listView.setOnItemClickListener(new PremiumPreviewFragment$$ExternalSyntheticLambda6(this));
        this.contentView.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 0.0f, -48.0f));
        this.buttonContainerInternal = new FrameLayout(r22);
        PremiumButtonView r313 = new PremiumButtonView(r22, false, getResourceProvider());
        this.premiumButtonView = r313;
        r313.setNonClickable();
        updateButtonText(false);
        this.buttonContainer = new FrameLayout(r22);
        this.buttonContainerInternal.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        this.buttonContainerInternal.addView(this.premiumButtonView, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r1 = this.buttonContainerInternal;
        r1.setBackground(this.iBlur3Factory.create(r1).setColorProvider(BlurredBackgroundProviderImpl.premiumButton(this.resourceProvider)).setRadius(AndroidUtilities.dp(28.0f)).setPadding(AndroidUtilities.dp(5.0f)));
        ScaleStateListAnimator.apply(this.buttonContainerInternal, 0.02f, 1.5f);
        this.buttonContainer.addView(this.buttonContainerInternal, LayoutHelper.createFrame(-1, 64.0f, 80, 4.0f, 0.0f, 4.0f, 0.0f));
        BlurredBackgroundWithFadeDrawable r12 = new BlurredBackgroundWithFadeDrawable(this.iBlur3Factory.create(this.buttonContainer));
        r12.setFadeHeight(AndroidUtilities.dp(40.0f), false);
        this.navbarProtectionDrawable = new BlurredBackgroundWithFadeDrawable(this.iBlur3Factory.create(this.contentView));
        this.buttonContainer.setBackground(r12);
        if (getUserConfig().isClientActivated() == false) goto L20;
        this.contentView.addView(this.buttonContainer, LayoutHelper.createFrame(-1, -2, 80));
    L20:
        this.fragmentView = this.contentView;
        this.actionBar.setBackground(null);
        this.actionBar.setCastShadows(false);
        INavigationLayout r14 = this.parentLayout;
        if (r14 != null) goto L23;
    L25:
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
    L26:
        this.actionBar.setAddToContainer(false);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass5(this));
        this.actionBar.setForceSkipTouches(true);
        this.contentView.addView(this.actionBar, LayoutHelper.createFrame(-1, -2, 48));
        updateColors();
        updateRows();
        BackgroundView.m17611$$Nest$fgetimageView(this.backgroundView).startEnterAnimation(-180, 200);
        if (this.forcePremium == false) goto L29;
        AndroidUtilities.runOnUIThread(new PremiumPreviewFragment$$ExternalSyntheticLambda7(this), 400);
    L29:
        MediaDataController.getInstance(this.currentAccount).preloadPremiumPreviewStickers();
        sentShowScreenStat(this.source);
        ViewCompat.setOnApplyWindowInsetsListener(this.fragmentView, new PremiumPreviewFragment$$ExternalSyntheticLambda8(this));
        return this.fragmentView;
    L23:
        if (r14.isRightLayout() == false) goto L25;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_close);
        goto L26
    L5:
        fillBusinessFeaturesList(r42, this.currentAccount, false);
        fillBusinessFeaturesList(this.morePremiumFeatures, this.currentAccount, true);
        QuickRepliesController.getInstance(this.currentAccount).load();
        if (getUserConfig().isPremium() == false) goto L11;
        TLRPC.TL_inputStickerSetShortName r314 = new TLRPC.TL_inputStickerSetShortName();
        r314.short_name = "RestrictedEmoji";
        MediaDataController.getInstance(this.currentAccount).getStickerSet(r314, false);
        BusinessChatbotController.getInstance(this.currentAccount).load(null);
        if (getMessagesController().suggestedFilters.isEmpty() == false) goto L10;
        getMessagesController().loadSuggestedFilters();
    L10:
        BusinessLinksController.getInstance(this.currentAccount).load(false);
        goto L11
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    @SuppressLint({"NotifyDataSetChanged"})
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.billingProductDetailsUpdated) goto L5;
    L6:
        updateButtonText(false);
        this.backgroundView.updatePremiumTiers();
    L8:
        if (r1 != NotificationCenter.currentUserPremiumStatusChanged) goto L10;
    L13:
        this.backgroundView.updateText();
        this.backgroundView.updatePremiumTiers();
        updateRows();
        this.listView.getAdapter().notifyDataSetChanged();
        return;
    L10:
        if (r1 == NotificationCenter.premiumPromoUpdated) goto L13;
        return;
    L5:
        if (r1 != NotificationCenter.premiumPromoUpdated) goto L8;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean drawEdgeNavigationBar() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public EdgeToEdgeSupportMode getEdgeToEdgeSupportMode() {
        return EdgeToEdgeSupportMode.FULL;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        return SimpleThemeDescription.createThemeDescriptions(new PremiumPreviewFragment$$ExternalSyntheticLambda3(this), new int[]{Theme.key_premiumGradient1, Theme.key_premiumGradient2, Theme.key_premiumGradient3, Theme.key_premiumGradient4, Theme.key_premiumGradientBackground1, Theme.key_premiumGradientBackground2, Theme.key_premiumGradientBackground3, Theme.key_premiumGradientBackground4, Theme.key_premiumGradientBackgroundOverlay, Theme.key_premiumStarGradient1, Theme.key_premiumStarGradient2, Theme.key_premiumStartSmallStarsColor, Theme.key_premiumStartSmallStarsColor2});
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isActionBarCrossfadeEnabled() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        return this.whiteBackground;
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
    public boolean onBackPressed(boolean r2) {
        if (this.settingsView == null) goto L9;
        if (r2 == false) goto L10;
        closeSetting();
        return false;
    L10:
        return false;
    L9:
        return super.onBackPressed(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onDialogDismiss(Dialog r1) {
        super.onDialogDismiss(r1);
        updateDialogVisibility(false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        if (getMessagesController().premiumFeaturesBlocked() == false) goto L5;
        return false;
    L5:
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        getNotificationCenter().addObserver(this, NotificationCenter.premiumPromoUpdated);
        if (getMediaDataController().getPremiumPromo() == null) goto L11;
        ArrayList<TLRPC.Document> r0 = getMediaDataController().getPremiumPromo().videos;
        int r2 = r0.size();
        int r3 = 0;
    L8:
        if (r3 >= r2) goto L11;
        TLRPC.Document r4 = r0.get(r3);
        r3 = r3 + 1;
        FileLoader.getInstance(this.currentAccount).loadFile(r4, getMediaDataController().getPremiumPromo(), 3, 0);
    L11:
        if (this.type != 1) goto L14;
        TimezonesController.getInstance(this.currentAccount).load();
    L14:
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        getNotificationCenter().removeObserver(this, NotificationCenter.premiumPromoUpdated);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        BackgroundView r0 = this.backgroundView;
        if (r0 != null) goto L5;
    L7:
        StarParticlesView r02 = this.particlesView;
        if (r02 == null) goto L10;
        r02.setPaused(true);
    L10:
        Bulletin.removeDelegate(this);
        return;
    L5:
        if (BackgroundView.m17611$$Nest$fgetimageView(r0) == null) goto L7;
        BackgroundView.m17611$$Nest$fgetimageView(this.backgroundView).setDialogVisible(true);
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        BackgroundView r0 = this.backgroundView;
        if (r0 != null) goto L5;
    L7:
        this.particlesView.setPaused(false);
        Bulletin.addDelegate(this, new AnonymousClass6(this));
        return;
    L5:
        if (BackgroundView.m17611$$Nest$fgetimageView(r0) == null) goto L7;
        BackgroundView.m17611$$Nest$fgetimageView(this.backgroundView).setPaused(false);
        BackgroundView.m17611$$Nest$fgetimageView(this.backgroundView).setDialogVisible(false);
        goto L7
    }

    public PremiumPreviewFragment setForcePremium() {
        this.forcePremium = true;
        return this;
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

    public void showSelectStatusDialog(PremiumFeatureCell r20, Long r21, Utilities.Callback2<Long, Integer> r22) {
        if (this.selectAnimatedEmojiDialog != null) goto L33;
        if (r20 == null) goto L34;
        SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] r11 = new SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[1];
        if ((r20.getTop() + r20.getHeight()) <= (this.listView.getMeasuredHeight() / 2.0f)) goto L9;
        boolean r0 = true;
    L10:
        int r2 = (int) Math.min(AndroidUtilities.dp(330.0f), AndroidUtilities.displaySize.y * 0.75f);
        int r3 = (int) Math.min(AndroidUtilities.dp(324.0f), AndroidUtilities.displaySize.x * 0.95f);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r4 = r20.imageDrawable;
        if (r4 == null) goto L21;
        r4.removeOldDrawable();
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r42 = r20.imageDrawable;
        if (r42 == null) goto L19;
        r42.play();
        r20.updateImageBounds();
        Rect r5 = AndroidUtilities.rectTmp2;
        r5.set(r20.imageDrawable.getBounds());
        if (r0 == false) goto L17;
        int r6 = ((-r5.centerY()) + AndroidUtilities.dp(12.0f)) - r2;
    L18:
        int r23 = r5.centerX() - (AndroidUtilities.displaySize.x - r3);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r15 = r42;
        PremiumFeatureCell r43 = r20;
    L22:
        if (r0 == false) goto L25;
        int r32 = 12;
    L24:
        boolean r52 = r0;
        int r7 = r6;
        int r62 = r32;
        Context r33 = getContext();
        Integer r24 = Integer.valueOf(r23);
        Theme.ResourcesProvider r8 = getResourceProvider();
        if (r52 == false) goto L30;
        int r53 = 24;
    L29:
        int r9 = r53;
        PremiumFeatureCell r54 = r43;
        AnonymousClass8 r02 = new AnonymousClass8(this, this, r33, true, r24, r62, true, r8, r9, r22, r11);
        r02.useAccentForPlus = true;
        r02.setSelected(r21);
        r02.setSaveState(3);
        r02.setScrimDrawable(r15, r54);
        AnonymousClass9 r25 = new AnonymousClass9(this, r02, -2, -2);
        this.selectAnimatedEmojiDialog = r25;
        r11[0] = r25;
        r25.showAsDropDown(r20, 0, r7, 53);
        r11[0].dimBehind();
        return;
    L30:
        r53 = 16;
        goto L29
    L25:
        r32 = 0;
        goto L24
    L17:
        r6 = (-(r20.getHeight() - r5.centerY())) - AndroidUtilities.dp(16.0f);
        goto L18
    L19:
        r15 = r42;
        r43 = r20;
    L20:
        r23 = 0;
        r6 = 0;
        goto L22
    L21:
        r43 = null;
        r15 = null;
        goto L20
    L9:
        r0 = false;
        goto L10
    L34:
        return;
    }
}
