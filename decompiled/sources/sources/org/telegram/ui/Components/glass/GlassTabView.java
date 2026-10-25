package org.telegram.ui.Components.glass;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.IconPackType;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.utils.ui.MainTabsUiHelper;
import me.vkryl.android.AnimatorUtils;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CrossfadeDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.MainTabsLayout;

/* loaded from: classes3.dex */
public class GlassTabView extends FrameLayout implements MainTabsLayout.Tab, FactorAnimator.Target {
    private static final FloatPropertyCompat<GlassTabView> COLOR_PROGRESS = null;
    private static final FloatPropertyCompat<GlassTabView> INDICATOR_SIZE_PROGRESS = null;
    private static final RectF tmpRectF = null;
    private int additionalWidth;
    public float attachScale;
    private AvatarDrawable avatarDrawable;
    private BackupImageView backupImageView;
    private int colorDefault;
    private float colorProgress;
    private int colorSelected;
    private int colorSelectedText;
    private SpringAnimation colorSpring;
    private final AnimatedTextView.AnimatedTextDrawable counter;
    private final TextPaint defaultTextPaint;
    private float gestureSelectedOverride;
    private boolean hasGestureSelectedOverride;
    private boolean hasVisualWidth;
    private final RLottieImageView imageView;
    private float indicatorSizeProgress;
    private SpringAnimation indicatorSizeSpring;
    private final BoolAnimator isHasCounterAnimator;
    private final BoolAnimator isHasCounterErrorAnimator;
    private final BoolAnimator isSelectedAnimator;
    private long lastBotIconId;
    private int lastIconAnimationRaw;
    private boolean lastIsSelected;
    private boolean needUpdateBackupViewColor;
    private final Paint paintCounterBackground;
    private Drawable premiumStarDrawable;
    private Theme.ResourcesProvider resourcesProvider;
    private TextPaint scaledTextPaint;
    private boolean selfMeasure;
    private boolean skipDrawSelector;
    private CrossfadeDrawable staticIconDrawable;
    private Drawable staticIconFilled;
    private Drawable staticIconOutline;
    private TabAnimation tabAnimation;
    private TLRPC.TL_attachMenuBot tabAnimationBot;
    private final TextView textView;
    private boolean useMainTabSelectedIndicator;
    private boolean usePremiumCounter;
    private float visualWidth;

    /* renamed from: org.telegram.ui.Components.glass.GlassTabView$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends FloatPropertyCompat<GlassTabView> {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(GlassTabView r1) {
            return getValue2(r1);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(GlassTabView r1) {
            return GlassTabView.m13710$$Nest$fgetindicatorSizeProgress(r1);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(GlassTabView r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(GlassTabView r1, float r2) {
            GlassTabView.m13712$$Nest$fputindicatorSizeProgress(r1, r2);
            r1.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.glass.GlassTabView$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends FloatPropertyCompat<GlassTabView> {
        public AnonymousClass2(String r1) {
            super(r1);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(GlassTabView r1) {
            return getValue2(r1);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(GlassTabView r1) {
            return GlassTabView.m13709$$Nest$fgetcolorProgress(r1);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(GlassTabView r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(GlassTabView r1, float r2) {
            GlassTabView.m13711$$Nest$fputcolorProgress(r1, r2);
            GlassTabView.m13713$$Nest$mupdateColors(r1);
            r1.invalidate();
        }
    }

    public enum TabAnimation extends Enum<TabAnimation> {
        private static final /* synthetic */ TabAnimation[] $VALUES = null;
        public static final TabAnimation ARTICLE = null;
        public static final TabAnimation BOOSTS = null;
        public static final TabAnimation CALLS = null;
        public static final TabAnimation CHATS = null;
        public static final TabAnimation CHECKLIST = null;
        public static final TabAnimation COLORS = null;
        public static final TabAnimation CONTACTS = null;
        public static final TabAnimation EMOJI = null;
        public static final TabAnimation FEED = null;
        public static final TabAnimation FILES = null;
        public static final TabAnimation GALLERY = null;
        public static final TabAnimation GIFT = null;
        public static final TabAnimation LINK = null;
        public static final TabAnimation LOCATION = null;
        public static final TabAnimation MODELS = null;
        public static final TabAnimation MONETIZATION = null;
        public static final TabAnimation MUSIC = null;
        public static final TabAnimation POLL = null;
        public static final TabAnimation REPLIES = null;
        public static final TabAnimation SETTINGS = null;
        public static final TabAnimation STICKER = null;
        public static final TabAnimation SYMBOLS = null;
        public static final TabAnimation WALLET = null;
        public final int endFrameEnd;
        public final int endFrameMid;
        public final int iconDrawableFilled;
        public final int iconDrawableOutline;
        public final int iconStatic;
        public final int iconToFilled;
        public final int iconToOutline;

        private static /* synthetic */ TabAnimation[] $values() {
            return new TabAnimation[]{CONTACTS, CALLS, CHATS, SETTINGS, FEED, CHECKLIST, COLORS, FILES, GALLERY, GIFT, LOCATION, STICKER, EMOJI, MODELS, MUSIC, POLL, SYMBOLS, REPLIES, WALLET, LINK, ARTICLE, BOOSTS, MONETIZATION};
        }

        static {
            CONTACTS = new TabAnimation("CONTACTS", 0, R.raw.tab_contacts, R.drawable.tabs_contact_active_24, R.drawable.tabs_contacts_24, -1, -1);
            CALLS = new TabAnimation("CALLS", 1, R.raw.tab_calls, R.drawable.tabs_calls_active_24, R.drawable.tabs_calls_24, -1, -1);
            CHATS = new TabAnimation("CHATS", 2, R.raw.tab_chats, R.drawable.tabs_chats_active_24, R.drawable.tabs_chats_24, -1, -1);
            SETTINGS = new TabAnimation("SETTINGS", 3, R.raw.tab_settings, R.drawable.filled_profile_settings, R.drawable.outline_profile_settings, -1, -1);
            FEED = new TabAnimation("FEED", 4, 0, R.drawable.ic_feed_filled, R.drawable.ic_feed, -1, -1);
            CHECKLIST = new TabAnimation("CHECKLIST", 5, R.raw.tab_checklist, R.raw.tab_checklist_reverse);
            COLORS = new TabAnimation("COLORS", 6, R.raw.tab_colors, R.raw.tab_colors_reverse);
            FILES = new TabAnimation("FILES", 7, R.raw.tab_files, R.raw.tab_files_reverse);
            GALLERY = new TabAnimation("GALLERY", 8, R.raw.tab_gallery, R.raw.tab_gallery_reverse);
            GIFT = new TabAnimation("GIFT", 9, R.raw.tab_gift, R.raw.tab_gift_reverse);
            LOCATION = new TabAnimation("LOCATION", 10, R.raw.tab_location, R.raw.tab_location_reverse);
            STICKER = new TabAnimation("STICKER", 11, R.raw.tab_sticker, R.raw.tab_sticker_reverse);
            EMOJI = new TabAnimation("EMOJI", 12, R.raw.tab_emoji, R.raw.tab_emoji_reverse);
            MODELS = new TabAnimation("MODELS", 13, R.raw.tab_models, R.raw.tab_models_reverse);
            MUSIC = new TabAnimation("MUSIC", 14, R.raw.tab_music, R.raw.tab_music_reverse);
            POLL = new TabAnimation("POLL", 15, R.raw.tab_poll, R.raw.tab_poll_reverse);
            SYMBOLS = new TabAnimation("SYMBOLS", 16, R.raw.tab_symbols, R.raw.tab_symbols_reverse);
            REPLIES = new TabAnimation("REPLIES", 17, R.raw.tab_reply, R.raw.tab_reply_reverse);
            WALLET = new TabAnimation("WALLET", 18, R.raw.tab_wallet, R.raw.tab_wallet_reverse);
            LINK = new TabAnimation("LINK", 19, TabAnimationType.STATIC, R.drawable.tabs_link_24);
            ARTICLE = new TabAnimation("ARTICLE", 20, R.raw.tab_article, R.raw.tab_article_reverse);
            BOOSTS = new TabAnimation("BOOSTS", 21, R.raw.boosts, 25, 49);
            MONETIZATION = new TabAnimation("MONETIZATION", 22, R.raw.monetize, 19, 45);
            $VALUES = $values();
        }

        TabAnimation(String r1, int r2, int r3, int r4) {
            this.iconToFilled = r3;
            this.iconToOutline = r4;
            this.iconDrawableFilled = 0;
            this.iconDrawableOutline = 0;
            this.endFrameMid = -1;
            this.endFrameEnd = -1;
            this.iconStatic = -1;
        }

        TabAnimation(String r1, int r2, int r3, int r4, int r5) {
            this.iconToFilled = r3;
            this.iconToOutline = r3;
            this.iconDrawableFilled = 0;
            this.iconDrawableOutline = 0;
            this.endFrameMid = r4;
            this.endFrameEnd = r5;
            this.iconStatic = -1;
        }

        TabAnimation(String r1, int r2, int r3, int r4, int r5, int r6, int r7) {
            this.iconToFilled = r3;
            this.iconToOutline = r3;
            this.iconDrawableFilled = r4;
            this.iconDrawableOutline = r5;
            this.endFrameMid = r6;
            this.endFrameEnd = r7;
            this.iconStatic = -1;
        }

        TabAnimation(String r1, int r2, TabAnimationType r3, int r4) {
            if (r3 != TabAnimationType.LOTTIE) goto L5;
            this.iconToFilled = r4;
            this.iconToOutline = r4;
            this.iconStatic = -1;
        L6:
            this.iconDrawableFilled = 0;
            this.iconDrawableOutline = 0;
            this.endFrameMid = -1;
            this.endFrameEnd = -1;
            return;
        L5:
            this.iconStatic = r4;
            this.iconToFilled = -1;
            this.iconToOutline = -1;
            goto L6
        }

        public static TabAnimation valueOf(String r1) {
            return (TabAnimation) Enum.valueOf(TabAnimation.class, r1);
        }

        public static TabAnimation[] values() {
            return (TabAnimation[]) $VALUES.clone();
        }
    }

    public enum TabAnimationType extends Enum<TabAnimationType> {
        private static final /* synthetic */ TabAnimationType[] $VALUES = null;
        public static final TabAnimationType LOTTIE = null;
        public static final TabAnimationType STATIC = null;

        private static /* synthetic */ TabAnimationType[] $values() {
            return new TabAnimationType[]{LOTTIE, STATIC};
        }

        static {
            LOTTIE = new TabAnimationType("LOTTIE", 0);
            STATIC = new TabAnimationType("STATIC", 1);
            $VALUES = $values();
        }

        TabAnimationType(String r1, int r2) {
        }

        public static TabAnimationType valueOf(String r1) {
            return (TabAnimationType) Enum.valueOf(TabAnimationType.class, r1);
        }

        public static TabAnimationType[] values() {
            return (TabAnimationType[]) $VALUES.clone();
        }
    }

    /* renamed from: -$$Nest$fgetcolorProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13709$$Nest$fgetcolorProgress(GlassTabView r0) {
        return r0.colorProgress;
    }

    /* renamed from: -$$Nest$fgetindicatorSizeProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13710$$Nest$fgetindicatorSizeProgress(GlassTabView r0) {
        return r0.indicatorSizeProgress;
    }

    /* renamed from: -$$Nest$fputcolorProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13711$$Nest$fputcolorProgress(GlassTabView r0, float r1) {
        r0.colorProgress = r1;
    }

    /* renamed from: -$$Nest$fputindicatorSizeProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13712$$Nest$fputindicatorSizeProgress(GlassTabView r0, float r1) {
        r0.indicatorSizeProgress = r1;
    }

    /* renamed from: -$$Nest$mupdateColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13713$$Nest$mupdateColors(GlassTabView r0) {
        r0.updateColors();
    }

    static {
        INDICATOR_SIZE_PROGRESS = new AnonymousClass1("indicatorSizeProgress");
        COLOR_PROGRESS = new AnonymousClass2("colorProgress");
        tmpRectF = new RectF();
    }

    public GlassTabView(Context r15) {
        super(r15);
        this.paintCounterBackground = new Paint(1);
        this.isSelectedAnimator = new BoolAnimator(0, this, AnimatorUtils.DECELERATE_INTERPOLATOR, 320);
        CubicBezierInterpolator r11 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.isHasCounterAnimator = new BoolAnimator(1, this, r11, 380);
        this.isHasCounterErrorAnimator = new BoolAnimator(2, this, r11, 380);
        this.attachScale = 1.0f;
        RLottieImageView r14 = new RLottieImageView(r15);
        this.imageView = r14;
        addView(r14, LayoutHelper.createFrame(44, 44.0f, 49, 0.0f, -6.0f, 0.0f, 0.0f));
        r14.setColorFilter(new PorterDuffColorFilter(-16777216, PorterDuff.Mode.SRC_IN));
        TextView r142 = new TextView(r15);
        this.textView = r142;
        r142.setTextSize(1, 12.0f);
        r142.setSingleLine();
        r142.setLines(1);
        r142.setEllipsize(TextUtils.TruncateAt.END);
        r142.setTypeface(AndroidUtilities.bold());
        r142.setGravity(17);
        this.defaultTextPaint = new TextPaint(r142.getPaint());
        addView(r142, LayoutHelper.createFrame(-1, -2.0f, 49, 0.0f, 28.33f, 0.0f, 0.0f));
        AnimatedTextView.AnimatedTextDrawable r143 = new AnimatedTextView.AnimatedTextDrawable();
        this.counter = r143;
        r143.setTypeface(AndroidUtilities.bold());
        r143.setCallback(this);
        r143.setGravity(17);
        r143.setTextColor(Theme.getColor(Theme.key_glass_targetMainTabs));
        r143.setTextSize(AndroidUtilities.dp(10.0f));
    }

    private void checkPlayAnimation(boolean r11) {
        boolean r0 = this.isSelectedAnimator.getValue();
        TLRPC.TL_attachMenuBot r1 = this.tabAnimationBot;
        boolean r2 = true;
        if (r1 == null) goto L21;
        TLRPC.TL_attachMenuBotIcon r112 = MediaDataController.getAnimatedAttachMenuBotIcon(r1, r0);
        if (r112 != null) goto L7;
        r112 = MediaDataController.getStaticAttachMenuBotIcon(this.tabAnimationBot);
        r2 = false;
    L7:
        if (r112 == null) goto L18;
        TLRPC.Document r113 = r112.icon;
        if (r113 == null) goto L18;
        if (this.lastBotIconId == r113.id) goto L19;
        BackupImageView r3 = this.backupImageView;
        ImageLocation r4 = ImageLocation.getForDocument(r113);
        ImageLocation r6 = ImageLocation.getForDocument(r113);
        if (r2 == false) goto L16;
        SvgHelper.SvgDrawable r02 = null;
    L17:
        r3.setImage(r4, "24_24_lastframe", r6, "24_24_lastframe", r02, this.tabAnimationBot);
        this.lastBotIconId = r113.id;
        goto L19
    L16:
        r02 = DocumentObject.getSvgThumb(r113, Theme.key_windowBackgroundGray, 1.0f);
    L19:
        updateColors();
        return;
    L18:
        this.backupImageView.clearImage();
        goto L19
    L21:
        TabAnimation r12 = this.tabAnimation;
        if (r12 != null) goto L25;
        return;
    L25:
        if (r12.iconToFilled != 0) goto L27;
    L28:
        TabAnimation r13 = this.tabAnimation;
        int r42 = r13.iconDrawableOutline;
        if (r42 == 0) goto L31;
    L91:
        if (r42 == 0) goto L93;
        int r114 = r42;
    L94:
        int r03 = r13.iconDrawableFilled;
        if (r03 == 0) goto L98;
        r42 = r03;
    L98:
        if (this.lastIconAnimationRaw == r42) goto L100;
        this.lastIconAnimationRaw = r42;
        this.staticIconOutline = getContext().getDrawable(r114).mutate();
        this.staticIconFilled = getContext().getDrawable(r42).mutate();
        this.staticIconDrawable = new CrossfadeDrawable(this.staticIconOutline, this.staticIconFilled);
        this.imageView.clearAnimationDrawable();
        this.imageView.setImageDrawable(this.staticIconDrawable);
    L100:
        updateColors();
        return;
    L93:
        r114 = r13.iconDrawableFilled;
        goto L94
    L31:
        if (r13.iconDrawableFilled != 0) goto L91;
    L33:
        TabAnimation r14 = this.tabAnimation;
        int r43 = r14.iconStatic;
        if (r43 == (-1)) goto L37;
        this.imageView.setImageResource(r43);
        updateColors();
        return;
    L37:
        if (r0 == false) goto L39;
        int r44 = r14.iconToFilled;
    L41:
        if (r14.endFrameMid == (-1)) goto L68;
        if (this.lastIsSelected == r0) goto L45;
        boolean r115 = true;
    L47:
        if (this.lastIconAnimationRaw == r44) goto L49;
        this.lastIconAnimationRaw = r44;
        this.imageView.setAnimation(r44, 24, 24);
        r115 = true;
    L49:
        if (r115 == false) goto L65;
        RLottieDrawable r116 = this.imageView.getAnimatedDrawable();
        if (r116 == null) goto L105;
        if (r0 == false) goto L61;
        r116.setCustomEndFrame(this.tabAnimation.endFrameMid);
        if (r116.getCurrentFrame() < (this.tabAnimation.endFrameEnd - 2)) goto L57;
        r116.setCurrentFrame(0, false);
    L57:
        int r15 = r116.getCurrentFrame();
        int r22 = this.tabAnimation.endFrameMid;
        if (r15 > r22) goto L60;
        r116.start();
        goto L65
    L60:
        r116.setCurrentFrame(r22);
        goto L65
    L61:
        int r16 = r116.getCurrentFrame();
        TabAnimation r45 = this.tabAnimation;
        if (r16 < (r45.endFrameMid - 1)) goto L64;
        r116.setCustomEndFrame(r45.endFrameEnd - 1);
        r116.start();
        goto L65
    L64:
        r116.setCustomEndFrame(0);
        r116.setCurrentFrame(0);
        goto L65
    L105:
        return;
    L65:
        this.lastIsSelected = r0;
        return;
    L45:
        r115 = false;
        goto L47
    L68:
        if (r14.iconToFilled == r14.iconToOutline) goto L78;
        if (this.lastIconAnimationRaw == r44) goto L102;
        this.lastIconAnimationRaw = r44;
        this.imageView.setAnimation(r44, 24, 24);
        this.imageView.getAnimatedDrawable().setPlayInDirectionOfCustomEndFrame(false);
        RLottieImageView r04 = this.imageView;
        if (r11 == false) goto L75;
        r04.getAnimatedDrawable().setCurrentFrame(0);
        this.imageView.playAnimation();
        return;
    L75:
        r04.getAnimatedDrawable().setProgress(0.99f);
        return;
    L102:
        return;
    L78:
        if (this.imageView.getAnimatedDrawable() != null) goto L80;
        this.imageView.setAnimation(this.tabAnimation.iconToFilled, 24, 24);
    L80:
        RLottieDrawable r117 = this.imageView.getAnimatedDrawable();
        if (r117 != null) goto L84;
        return;
    L84:
        if (this.lastIsSelected == r0) goto L103;
        this.lastIsSelected = r0;
        if (r0 == false) goto L88;
        r117.setPlayInDirectionOfCustomEndFrame(false);
        r117.setCurrentFrame(0);
        r117.setCustomEndFrame(r117.getFramesCount());
    L89:
        this.imageView.playAnimation();
        return;
    L88:
        r117.setPlayInDirectionOfCustomEndFrame(true);
        r117.setCurrentFrame(r117.getFramesCount());
        r117.setCustomEndFrame(0);
        goto L89
    L103:
        return;
    L39:
        r44 = r14.iconToOutline;
        goto L41
    L27:
        if (IconManager.INSTANCE.isBasePackOnly(IconPackType.DEFAULT) == true) goto L33;
        goto L28
    }

    private void checkVisualWidth() {
        if (this.hasVisualWidth == false) goto L6;
        float r0 = (this.visualWidth - getMeasuredWidth()) / 2.0f;
        this.imageView.setTranslationX(r0);
        this.textView.setTranslationX(r0);
        return;
    }

    public static GlassTabView createAttachBotTab(Context r9, Theme.ResourcesProvider r10) {
        GlassTabView r0 = new GlassTabView(r9);
        r0.resourcesProvider = r10;
        r0.selfMeasure = true;
        r0.textView.setTextSize(1, 11.0f);
        r0.textView.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        r0.imageView.setVisibility(8);
        r0.checkPlayAnimation(false);
        BackupImageView r1 = new BackupImageView(r9);
        r0.backupImageView = r1;
        r0.addView(r1, LayoutHelper.createFrame(24, 24.0f, 49, 0.0f, 4.0f, 0.0f, 0.0f));
        r0.colorDefault = Theme.getColor(Theme.key_glass_tabUnselected, r10);
        r0.colorSelected = Theme.getColor(Theme.key_glass_tabSelected, r10);
        r0.colorSelectedText = Theme.getColor(Theme.key_glass_tabSelectedText, r10);
        r0.updateColors();
        return r0;
    }

    public static GlassTabView createAttachTab(Context r8, Theme.ResourcesProvider r9) {
        GlassTabView r0 = new GlassTabView(r8);
        r0.resourcesProvider = r9;
        r0.selfMeasure = true;
        r0.textView.setTextSize(1, 11.0f);
        r0.textView.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        r0.checkPlayAnimation(false);
        r0.imageView.setLayoutParams(LayoutHelper.createFrame(24, 24.0f, 49, 0.0f, 4.0f, 0.0f, 0.0f));
        r0.colorDefault = Theme.getColor(Theme.key_glass_tabUnselected, r9);
        r0.colorSelected = Theme.getColor(Theme.key_glass_tabSelected, r9);
        r0.colorSelectedText = Theme.getColor(Theme.key_glass_tabSelectedText, r9);
        r0.updateColors();
        return r0;
    }

    public static GlassTabView createAvatar(Context r9, Theme.ResourcesProvider r10, int r11, int r12) {
        GlassTabView r0 = new GlassTabView(r9);
        r0.textView.setText(LocaleController.getString(r12));
        r0.imageView.setVisibility(8);
        TLRPC.User r112 = MessagesController.getInstance(r11).getUser(Long.valueOf(UserConfig.getInstance(r11).getClientUserId()));
        AvatarDrawable r122 = new AvatarDrawable(r112);
        BackupImageView r1 = new BackupImageView(r9);
        r1.setForUserOrChat(r112, r122);
        r1.setRoundRadius(ExteraConfig.getAvatarCorners(22.0f));
        r0.backupImageView = r1;
        r0.addView(r1, LayoutHelper.createFrame(22, 22.0f, 49, 0.0f, 5.0f, 0.0f, 0.0f));
        r0.colorDefault = Theme.getColor(Theme.key_glass_tabUnselected, r10);
        r0.colorSelected = Theme.getColor(Theme.key_glass_tabSelected, r10);
        r0.colorSelectedText = Theme.getColor(Theme.key_glass_tabSelectedText, r10);
        r0.updateColors();
        return r0;
    }

    public static GlassTabView createMainNavigationAvatar(Context r0, Theme.ResourcesProvider r1, int r2, int r3) {
        GlassTabView r02 = createAvatar(r0, r1, r2, r3);
        r02.setMainTabStyle();
        return r02;
    }

    public static GlassTabView createMainNavigationTab(Context r0, Theme.ResourcesProvider r1, TabAnimation r2, int r3) {
        GlassTabView r02 = createMainTab(r0, r1, r2, r3);
        r02.setMainTabStyle();
        return r02;
    }

    public static GlassTabView createMainTab(Context r8, Theme.ResourcesProvider r9, TabAnimation r10, int r11) {
        GlassTabView r0 = new GlassTabView(r8);
        r0.resourcesProvider = r9;
        r0.tabAnimation = r10;
        r0.textView.setText(LocaleController.getString(r11));
        r0.checkPlayAnimation(false);
        r0.imageView.setLayoutParams(LayoutHelper.createFrame(24, 24.0f, 49, 0.0f, 4.0f, 0.0f, 0.0f));
        r0.colorDefault = Theme.getColor(Theme.key_glass_tabUnselected, r9);
        r0.colorSelected = Theme.getColor(Theme.key_glass_tabSelected, r9);
        r0.colorSelectedText = Theme.getColor(Theme.key_glass_tabSelectedText, r9);
        r0.updateColors();
        return r0;
    }

    private void setMainTabStyle() {
        if (MainTabsUiHelper.isMaterial3NavigationBar() == true) goto L5;
        return;
    L5:
        this.useMainTabSelectedIndicator = true;
        this.imageView.setLayoutParams(LayoutHelper.createFrame(24, 24.0f, 49, 0.0f, MainTabsUiHelper.getMaterial3MainTabIconTopDp(), 0.0f, 0.0f));
        BackupImageView r0 = this.backupImageView;
        if (r0 == null) goto L8;
        r0.setLayoutParams(LayoutHelper.createFrame(22, 22.0f, 49, 0.0f, MainTabsUiHelper.getMaterial3MainTabAvatarTopDp(), 0.0f, 0.0f));
    L8:
        this.indicatorSizeSpring = MainTabsUiHelper.createMaterial3IndicatorSizeSpring(this, INDICATOR_SIZE_PROGRESS);
        this.colorSpring = MainTabsUiHelper.createMaterial3ColorSpring(this, COLOR_PROGRESS);
        MainTabsUiHelper.applyMaterial3MainTabStyle(this.textView);
    }

    private void updateColors() {
        if (this.useMainTabSelectedIndicator == false) goto L5;
        float r0 = this.colorProgress;
    L6:
        int r1 = ColorUtils.blendARGB(this.colorDefault, this.colorSelected, r0);
        int r2 = ColorUtils.blendARGB(this.colorDefault, this.colorSelectedText, r0);
        PorterDuffColorFilter r3 = new PorterDuffColorFilter(r1, PorterDuff.Mode.SRC_IN);
        BackupImageView r12 = this.backupImageView;
        if (r12 == null) goto L12;
        if (this.needUpdateBackupViewColor == false) goto L12;
        r12.setColorFilter(r3);
        this.backupImageView.invalidate();
    L12:
        if (this.staticIconDrawable == null) goto L14;
        this.staticIconOutline.setColorFilter(r3);
        this.staticIconFilled.setColorFilter(r3);
        this.staticIconDrawable.setProgress(MathUtils.clamp(r0, 0.0f, 1.0f));
    L14:
        this.imageView.setColorFilter(r3);
        this.textView.setTextColor(r2);
        this.counter.setTextColor(Theme.getColor(Theme.key_glass_targetMainTabs));
        return;
    L5:
        r0 = this.isSelectedAnimator.getFloatValue();
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r22) {
        if (this.hasVisualWidth == false) goto L6;
        float r2 = this.visualWidth;
    L5:
        float r4 = r2;
        if (this.hasGestureSelectedOverride == false) goto L10;
        float r23 = this.gestureSelectedOverride;
    L11:
        boolean r3 = this.useMainTabSelectedIndicator;
        if (r3 == false) goto L14;
        float r5 = this.indicatorSizeProgress;
    L15:
        float r9 = 1.0f;
        if (r5 <= 0.0f) goto L24;
        if (this.skipDrawSelector == true) goto L24;
        if (r3 == false) goto L21;
        this.paintCounterBackground.setColor(MainTabsUiHelper.getMainTabSelectedIndicatorColor(this.colorSelected, MathUtils.clamp(r5, 0.0f, 1.0f)));
        MainTabsUiHelper.setMainTabSelectedIndicatorBounds(tmpRectF, r4, getHeight(), r5);
        float r24 = 1.0f;
    L22:
        RectF r32 = tmpRectF;
        float r52 = Math.min(r32.width(), r32.height()) / 2.0f;
        float r25 = r24 * MathUtils.clamp(this.attachScale, 0.0f, 1.0f);
        r22.save();
        r22.scale(r25, r25, r32.centerX(), r32.centerY());
        r22.drawRoundRect(r32, r52, r52, this.paintCounterBackground);
        r22.restore();
        goto L24
    L21:
        this.paintCounterBackground.setColor(Theme.multAlpha(this.colorSelected, AnimatorUtils.DECELERATE_INTERPOLATOR.getInterpolation(r23) * 0.09f));
        tmpRectF.set(0.0f, 0.0f, r4, getHeight());
        r24 = MainTabsUiHelper.getSelectedBackgroundScale(r23);
    L24:
        if (this.usePremiumCounter == true) goto L27;
        r9 = this.isHasCounterAnimator.getFloatValue();
    L27:
        float r92 = r9 * this.attachScale;
        if (r92 <= 0.0f) goto L31;
        boolean r26 = true;
    L30:
        boolean r10 = r26;
        if (r10 == false) goto L34;
        r22.saveLayer(0.0f, 0.0f, r4, getHeight(), null);
    L34:
        super.dispatchDraw(r22);
        if (r92 <= 0.0f) goto L44;
        r22.save();
        float r27 = AndroidUtilities.dpf2(1.33f);
        float r42 = (r4 / 2.0f) + AndroidUtilities.dpf2(11.0f);
        float r33 = MainTabsUiHelper.getMainTabCounterCenterY(this.useMainTabSelectedIndicator);
        float r6 = AndroidUtilities.dpf2(16.0f);
        float r7 = Math.max(r6, this.counter.getCurrentWidth() + AndroidUtilities.dp(8.0f));
        float r12 = AndroidUtilities.dpf2(9.333f);
        float r11 = AndroidUtilities.dpf2(8.0f);
        RectF r13 = tmpRectF;
        float r72 = r7 / 2.0f;
        float r62 = r6 / 2.0f;
        r13.set((r42 - r72) - r27, (r33 - r62) - r27, (r72 + r42) + r27, (r62 + r33) + r27);
        r22.scale(r92, r92, r42, r33);
        r22.drawRoundRect(r13, r12, r12, Theme.PAINT_CLEAR);
        r13.inset(r27, r27);
        if (this.usePremiumCounter == true) goto L39;
        this.paintCounterBackground.setColor(ColorUtils.blendARGB(Theme.getColor(Theme.key_telegram_color), Theme.getColor(Theme.key_fill_RedNormal), this.isHasCounterErrorAnimator.getFloatValue()));
        r22.drawRoundRect(r13, r11, r11, this.paintCounterBackground);
        this.counter.setBounds(r13);
        this.counter.draw(r22);
    L43:
        r22.restore();
        goto L44
    L39:
        if (this.premiumStarDrawable != null) goto L41;
        this.premiumStarDrawable = getContext().getResources().getDrawable(R.drawable.star).mutate();
    L41:
        PremiumGradient.getInstance().updateMainGradientMatrix(0, 0, AndroidUtilities.dp(96.0f), AndroidUtilities.dp(16.0f), 0.0f, 0.0f);
        r22.drawRoundRect(r13, r11, r11, PremiumGradient.getInstance().getMainGradientPaint());
        int r43 = (int) (r42 - AndroidUtilities.dpf2(7.0f));
        int r28 = (int) (r33 - AndroidUtilities.dpf2(7.0f));
        this.premiumStarDrawable.setBounds(r43, r28, AndroidUtilities.dp(14.0f) + r43, AndroidUtilities.dp(14.0f) + r28);
        this.premiumStarDrawable.draw(r22);
    L44:
        if (r10 == false) goto L47;
        r22.restore();
        return;
    L47:
        return;
    L31:
        r26 = false;
        goto L30
    L14:
        r5 = r23;
        goto L15
    L10:
        r23 = this.isSelectedAnimator.getFloatValue();
        goto L11
    L6:
        r2 = getWidth();
        goto L5
    }

    public BackupImageView getBackupImageView() {
        return this.backupImageView;
    }

    public boolean isTabSelected() {
        return this.isSelectedAnimator.getValue();
    }

    public float measureAttachTabWidth() {
        float r0 = AndroidUtilities.lerp(AndroidUtilities.dpf2(16.0f), AndroidUtilities.dp(8.0f), MathUtils.clamp((measureTextWidth() - AndroidUtilities.dp(40.0f)) / AndroidUtilities.dp(16.0f), 0.0f, 1.0f));
        return Math.min(AndroidUtilities.dp(84.0f), (int) (r5 + (r0 * 2.0f)));
    }

    public float measureTextWidth() {
        return this.defaultTextPaint.measureText(this.textView.getText().toString());
    }

    @Override // org.telegram.ui.MainTabsLayout.Tab
    public float measureTextWidth(float r3) {
        if (this.scaledTextPaint != null) goto L5;
        this.scaledTextPaint = new TextPaint(this.defaultTextPaint);
    L5:
        this.scaledTextPaint.setTextSize(AndroidUtilities.dp(r3));
        return this.scaledTextPaint.measureText(this.textView.getText().toString());
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 0) goto L4;
        updateColors();
    L4:
        invalidate();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        if (this.selfMeasure == false) goto L6;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(((int) measureAttachTabWidth()) + this.additionalWidth, TLObject.FLAG_30), r3);
        return;
    L6:
        super.onMeasure(r2, r3);
    }

    public void onPreBind() {
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        checkVisualWidth();
    }

    public void setAdditionalWidth(int r1) {
        this.additionalWidth = r1;
        this.selfMeasure = true;
    }

    public void setAttachBot(TLRPC.User r8, TLRPC.TL_attachMenuBot r9, int r10) {
        if (r8 == null) goto L7;
        if (r9 == null) goto L8;
        this.tabAnimation = null;
        this.tabAnimationBot = r9;
        this.lastIconAnimationRaw = 0;
        this.lastBotIconId = 0;
        this.textView.setText(r9.short_name);
        this.backupImageView.setRoundRadius(0);
        this.backupImageView.setSize(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
        this.backupImageView.setLayoutParams(LayoutHelper.createFrame(24, 24.0f, 49, 0.0f, 4.0f, 0.0f, 0.0f));
        this.needUpdateBackupViewColor = true;
        checkPlayAnimation(false);
        updateColors();
        invalidate();
        return;
    L8:
        return;
    }

    public void setAttachBotUser(TLRPC.User r10, int r11) {
        if (r10 != null) goto L4;
        return;
    L4:
        this.tabAnimation = null;
        this.tabAnimationBot = null;
        this.lastIconAnimationRaw = 0;
        this.lastBotIconId = 0;
        this.textView.setText(ContactsController.formatName(r10.first_name, r10.last_name));
        if (this.avatarDrawable != null) goto L7;
        this.avatarDrawable = new AvatarDrawable();
    L7:
        this.avatarDrawable.setInfo(r11, r10);
        this.backupImageView.setForUserOrChat(r10, this.avatarDrawable);
        this.backupImageView.setSize(-1, -1);
        this.backupImageView.setRoundRadius(ExteraConfig.getAvatarCorners(22.0f));
        this.backupImageView.setLayoutParams(LayoutHelper.createFrame(22, 22.0f, 49, 0.0f, 5.0f, 0.0f, 0.0f));
        this.backupImageView.setColorFilter(null);
        this.needUpdateBackupViewColor = false;
        invalidate();
    }

    public void setAttachScale(float r2) {
        this.textView.setScaleX(r2);
        this.textView.setScaleY(r2);
        this.imageView.setScaleX(r2);
        this.imageView.setScaleY(r2);
        BackupImageView r0 = this.backupImageView;
        if (r0 == null) goto L5;
        r0.setScaleX(r2);
        this.backupImageView.setScaleY(r2);
    L5:
        this.attachScale = r2;
        invalidate();
    }

    public void setCounter(String r2, boolean r3, boolean r4) {
        this.counter.setText(r2, r4);
        this.isHasCounterAnimator.setValue(!TextUtils.isEmpty(r2), r4);
        this.isHasCounterErrorAnimator.setValue(r3, r4);
    }

    public void setGestureSelectedOverride(float r1, boolean r2) {
        this.gestureSelectedOverride = r1;
        if (r2 == true) goto L5;
    L7:
        boolean r12 = false;
    L8:
        this.hasGestureSelectedOverride = r12;
        invalidate();
        return;
    L5:
        if (this.useMainTabSelectedIndicator == true) goto L7;
        r12 = true;
        goto L8
    }

    public void setPremiumBadge(boolean r1) {
        this.usePremiumCounter = r1;
    }

    public void setSelected(boolean r3, boolean r4) {
        boolean r0 = this.useMainTabSelectedIndicator;
        BoolAnimator r1 = this.isSelectedAnimator;
        if (r0 == false) goto L12;
        r1.setValue(r3, false);
        if (r3 == false) goto L7;
        float r02 = 1.0f;
    L8:
        SpringAnimation r12 = this.indicatorSizeSpring;
        if (r4 == false) goto L11;
        r12.animateToFinalPosition(r02);
        this.colorSpring.animateToFinalPosition(r02);
    L13:
        checkPlayAnimation(r4);
        TextView r42 = this.textView;
        if (this.useMainTabSelectedIndicator == true) goto L18;
        if (r3 == false) goto L18;
        Typeface r2 = AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_EXTRA_BOLD);
    L19:
        r42.setTypeface(r2);
        return;
    L18:
        r2 = AndroidUtilities.bold();
        goto L19
    L11:
        r12.cancel();
        this.colorSpring.cancel();
        this.indicatorSizeProgress = r02;
        this.colorProgress = r02;
        updateColors();
        invalidate();
        goto L13
    L7:
        r02 = 0.0f;
        goto L8
    L12:
        r1.setValue(r3, r4);
        goto L13
    }

    public void setSkipDrawSelector(boolean r2) {
        if (this.skipDrawSelector == r2) goto L6;
        this.skipDrawSelector = r2;
        invalidate();
        return;
    }

    public void setTabAnimation(TabAnimation r4) {
        this.tabAnimation = r4;
        this.tabAnimationBot = null;
        this.lastIconAnimationRaw = 0;
        this.lastBotIconId = 0;
        this.staticIconDrawable = null;
        this.imageView.clearAnimationDrawable();
        checkPlayAnimation(false);
    }

    public void setText(CharSequence r1) {
        this.textView.setText(r1);
    }

    @Override // org.telegram.ui.MainTabsLayout.Tab
    public void setTextSizeDp(float r4) {
        float r0 = AndroidUtilities.dp(r4);
        if (this.textView.getTextSize() == r0) goto L6;
        this.textView.setTextSize(1, r4);
        this.defaultTextPaint.setTextSize(r0);
        return;
    }

    public void setVisualWidth(float r2) {
        this.hasVisualWidth = true;
        if (this.visualWidth == r2) goto L6;
        this.visualWidth = r2;
        checkVisualWidth();
        invalidate();
        return;
    }

    public void updateColorsLottie() {
        this.colorDefault = Theme.getColor(Theme.key_glass_tabUnselected, this.resourcesProvider);
        this.colorSelected = Theme.getColor(Theme.key_glass_tabSelected, this.resourcesProvider);
        this.colorSelectedText = Theme.getColor(Theme.key_glass_tabSelectedText, this.resourcesProvider);
        updateColors();
        invalidate();
    }

    public void updateUserAvatar(int r4) {
        TLRPC.User r42 = MessagesController.getInstance(r4).getUser(Long.valueOf(UserConfig.getInstance(r4).getClientUserId()));
        AvatarDrawable r0 = new AvatarDrawable(r42);
        this.backupImageView.setForUserOrChat(r42, r0);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.counter) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
