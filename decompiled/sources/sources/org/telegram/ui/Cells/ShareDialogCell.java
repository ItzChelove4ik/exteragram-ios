package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.exteragram.messenger.ExteraConfig;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Stars.StarsIntroActivity;

/* loaded from: classes6.dex */
public class ShareDialogCell extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private final AvatarDrawable avatarDrawable;
    private final CheckBox2 checkBox;
    private final int currentAccount;
    private long currentDialog;
    private final int currentType;
    private final BackupImageView imageView;
    private long lastUpdateTime;
    private Drawable lockDrawable;
    private final TextView nameTextView;
    private float onlineProgress;
    private boolean premiumBlocked;
    private final AnimatedFloat premiumBlockedT;
    private PremiumGradient.PremiumGradientTools premiumGradient;
    private final Paint priceBackgroundPaint;
    private Text priceText;
    private long priceTextValue;
    private RepostStoryDrawable repostStoryDrawable;
    public final Theme.ResourcesProvider resourcesProvider;
    private final AnimatedFloat starsBlockedT;
    private long starsPriceBlocked;
    private final SimpleTextView topicTextView;
    private boolean topicWasVisible;
    private TLRPC.User user;

    /* renamed from: org.telegram.ui.Cells.ShareDialogCell$1, reason: invalid class name */
    public class AnonymousClass1 extends AvatarDrawable {
        final /* synthetic */ ShareDialogCell this$0;

        public AnonymousClass1(ShareDialogCell r1, Theme.ResourcesProvider r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.graphics.drawable.Drawable
        public void invalidateSelf() {
            super.invalidateSelf();
            ShareDialogCell.m7009$$Nest$fgetimageView(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Cells.ShareDialogCell$2, reason: invalid class name */
    public class AnonymousClass2 extends TextView {
        final /* synthetic */ ShareDialogCell this$0;

        public AnonymousClass2(ShareDialogCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView
        public void setText(CharSequence r3, TextView.BufferType r4) {
            super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
        }
    }

    /* loaded from: classes3.dex */
    public static class RepostStoryDrawable extends Drawable {
        int alpha;
        private final Drawable drawable;
        private final LinearGradient gradient;
        private final RLottieDrawable lottieDrawable;
        private final Paint paint;

        public RepostStoryDrawable(Context r7, View r8, int r9, Theme.ResourcesProvider r10) {
            this(r7, r8, false, r9, r10);
        }

        public RepostStoryDrawable(Context r11, View r12, boolean r13, int r14, Theme.ResourcesProvider r15) {
            Paint r0 = new Paint(1);
            this.paint = r0;
            this.alpha = 255;
            LinearGradient r2 = new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f), new int[]{Theme.getColor(Theme.key_stories_circle1, r15), Theme.getColor(Theme.key_stories_circle2, r15)}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            this.gradient = r2;
            r0.setShader(r2);
            if (r13 == false) goto L6;
            RLottieDrawable r02 = new RLottieDrawable(R.raw.story_repost, AndroidUtilities.dp(42.0f), AndroidUtilities.dp(42.0f), true, null);
            this.lottieDrawable = r02;
            r02.setMasterParent(r12);
            Objects.requireNonNull(r02);
            AndroidUtilities.runOnUIThread(new ShareDialogCell$RepostStoryDrawable$$ExternalSyntheticLambda0(r02), 450);
            this.drawable = null;
            return;
        L6:
            this.lottieDrawable = null;
            Drawable r112 = r11.getResources().getDrawable(r14).mutate();
            this.drawable = r112;
            r112.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        }

        public RepostStoryDrawable(Context r7, View r8, boolean r9, Theme.ResourcesProvider r10) {
            this(r7, r8, r9, R.drawable.large_repost_story, r10);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r7) {
            r7.save();
            r7.translate(getBounds().left, getBounds().top);
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(0.0f, 0.0f, getBounds().width(), getBounds().height());
            this.paint.setAlpha(this.alpha);
            float r1 = ExteraConfig.getAvatarCorners(getBounds().width(), true);
            r7.drawRoundRect(r0, r1, r1, this.paint);
            r7.restore();
            if (this.lottieDrawable == null) goto L5;
            float r02 = 20.0f;
        L6:
            int r03 = AndroidUtilities.dp(r02);
            Rect r12 = AndroidUtilities.rectTmp2;
            r12.set(getBounds().centerX() - r03, getBounds().centerY() - r03, getBounds().centerX() + r03, getBounds().centerY() + r03);
            Drawable r04 = this.lottieDrawable;
            if (r04 != null) goto L9;
            r04 = this.drawable;
        L9:
            if (r04 == null) goto L12;
            r04.setBounds(r12);
            r04.setAlpha(this.alpha);
            r04.draw(r7);
            return;
        L12:
            return;
        L5:
            r02 = 15.0f;
            goto L6
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(56.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(56.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.alpha = r1;
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public static /* synthetic */ void $r8$lambda$Jj28vlpYsVWpeSV6jZvFJ7nu4EQ(ShareDialogCell r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
        r0.lambda$setTopic$2(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$l2ZzbiygbdBiJHpm_5fwk-zpBv8, reason: not valid java name */
    public static /* synthetic */ void m7008$r8$lambda$l2ZzbiygbdBiJHpm_5fwkzpBv8(ShareDialogCell r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$setTopic$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$uTZL8yUehm0RgnmWVZpprPGOodQ(ShareDialogCell r0, float r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m7009$$Nest$fgetimageView(ShareDialogCell r0) {
        return r0.imageView;
    }

    public ShareDialogCell(Context r18, int r19, Theme.ResourcesProvider r20) {
        super(r18);
        this.currentAccount = UserConfig.selectedAccount;
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.premiumBlockedT = new AnimatedFloat(this, 0, 350, r6);
        this.starsBlockedT = new AnimatedFloat(this, 0, 350, r6);
        this.priceBackgroundPaint = new Paint();
        this.resourcesProvider = r20;
        this.avatarDrawable = new AnonymousClass1(this, r20);
        setWillNotDraw(false);
        this.currentType = r19;
        BackupImageView r2 = new BackupImageView(r18);
        this.imageView = r2;
        if (r19 != 2) goto L5;
        r2.setRoundRadius(ExteraConfig.getAvatarCorners(48.0f));
        addView(r2, LayoutHelper.createFrame(48, 48.0f, 49, 0.0f, 7.0f, 0.0f, 0.0f));
    L6:
        AnonymousClass2 r22 = new AnonymousClass2(this, r18);
        this.nameTextView = r22;
        NotificationCenter.listenEmojiLoading(r22);
        if (this.premiumBlocked == false) goto L9;
        int r4 = Theme.key_windowBackgroundWhiteGrayText5;
    L10:
        r22.setTextColor(getThemedColor(r4));
        r22.setTextSize(1, 12.0f);
        r22.setMaxLines(2);
        r22.setGravity(49);
        r22.setLines(2);
        r22.setEllipsize(TextUtils.TruncateAt.END);
        if (r19 != 2) goto L13;
        float r14 = 58.0f;
    L14:
        addView(r22, LayoutHelper.createFrame(-1, -2.0f, 51, 6.0f, r14, 6.0f, 0.0f));
        SimpleTextView r23 = new SimpleTextView(r18);
        this.topicTextView = r23;
        r23.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        r23.setTextSize(12);
        r23.setMaxLines(2);
        r23.setGravity(49);
        r23.setAlignment(Layout.Alignment.ALIGN_CENTER);
        if (r19 != 2) goto L17;
        float r142 = 58.0f;
    L18:
        addView(r23, LayoutHelper.createFrame(-1, -2.0f, 51, 6.0f, r142, 6.0f, 0.0f));
        CheckBox2 r24 = new CheckBox2(r18, 21, r20);
        this.checkBox = r24;
        r24.setColor(Theme.key_dialogRoundCheckBox, Theme.key_dialogBackground, Theme.key_dialogRoundCheckBoxCheck);
        r24.setDrawUnchecked(false);
        r24.setDrawBackgroundAsArc(4);
        r24.setProgressDelegate(new ShareDialogCell$$ExternalSyntheticLambda0(this));
        if (r19 != 2) goto L22;
        float r0 = -40.0f;
    L23:
        addView(r24, LayoutHelper.createFrame(24, 24.0f, 49, 19.0f, r0, 0.0f, 0.0f));
        setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, r20), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f)));
        return;
    L22:
        r0 = 42.0f;
        goto L23
    L17:
        r142 = 66.0f;
        goto L18
    L13:
        r14 = 66.0f;
        goto L14
    L9:
        r4 = Theme.key_dialogTextBlack;
        goto L10
    L5:
        r2.setRoundRadius(ExteraConfig.getAvatarCorners(56.0f));
        addView(r2, LayoutHelper.createFrame(56, 56.0f, 49, 0.0f, 7.0f, 0.0f, 0.0f));
        goto L6
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$new$0(float r2) {
        float r0 = 1.0f - (this.checkBox.getProgress() * 0.143f);
        this.imageView.setScaleX(r0);
        this.imageView.setScaleY(r0);
        invalidate();
    }

    private /* synthetic */ void lambda$setTopic$1(DynamicAnimation r3, float r4, float r5) {
        float r42 = r4 / 1000.0f;
        this.topicTextView.setAlpha(r42);
        float r52 = 1.0f - r42;
        this.nameTextView.setAlpha(r52);
        this.topicTextView.setTranslationX(r52 * (-AndroidUtilities.dp(10.0f)));
        this.nameTextView.setTranslationX(r42 * AndroidUtilities.dp(10.0f));
    }

    private /* synthetic */ void lambda$setTopic$2(DynamicAnimation r1, boolean r2, float r3, float r4) {
        this.topicTextView.setTag(R.id.spring_tag, null);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 == NotificationCenter.userIsPremiumBlockedUpadted) goto L5;
        return;
    L5:
        if (this.user == null) goto L7;
        TL_account.RequirementToContact r32 = MessagesController.getInstance(this.currentAccount).isUserContactBlocked(this.user.id);
    L9:
        if (this.currentDialog >= 0) goto L11;
        long r42 = MessagesController.getInstance(this.currentAccount).getSendPaidMessagesStars(this.currentDialog);
    L13:
        if (this.premiumBlocked == DialogObject.isPremiumBlocked(r32)) goto L15;
    L16:
        boolean r33 = DialogObject.isPremiumBlocked(r32);
        this.premiumBlocked = r33;
        this.starsPriceBlocked = r42;
        TextView r43 = this.nameTextView;
        if (r33 == false) goto L19;
        int r34 = Theme.key_windowBackgroundWhiteGrayText5;
    L20:
        r43.setTextColor(getThemedColor(r34));
        invalidate();
        return;
    L19:
        r34 = Theme.key_dialogTextBlack;
        goto L20
    L15:
        if (this.starsPriceBlocked != r42) goto L16;
        return;
    L11:
        r42 = DialogObject.getMessagesStarsPrice(r32);
        goto L13
    L7:
        r32 = null;
        goto L9
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r25, View r26, long r27) {
        boolean r7 = super.drawChild(r25, r26, r27);
        if (r26 == this.imageView) goto L5;
    L82:
        return r7;
    L5:
        if (this.currentType == 2) goto L82;
        TLRPC.User r1 = this.user;
        if (r1 == null) goto L82;
        if (MessagesController.isSupportUser(r1) == true) goto L82;
        long r3 = SystemClock.elapsedRealtime();
        long r5 = r3 - this.lastUpdateTime;
        long r8 = 17;
        if (r5 > 17) goto L14;
        r8 = r5;
    L14:
        this.lastUpdateTime = r3;
        AnimatedFloat r12 = this.starsBlockedT;
        if (this.starsPriceBlocked <= 0) goto L17;
        boolean r32 = true;
    L18:
        float r122 = r12.set(r32);
        if (r122 <= 0.0f) goto L36;
        float r13 = (this.imageView.getLeft() + (this.imageView.getMeasuredWidth() / 2.0f)) + AndroidUtilities.dp(18.0f);
        float r4 = (this.imageView.getTop() + (this.imageView.getMeasuredHeight() / 2.0f)) - AndroidUtilities.dp(20.83f);
        if (this.priceText == null) goto L27;
        long r52 = this.priceTextValue;
        float r28 = 0.0f;
        long r132 = this.starsPriceBlocked;
        if (r52 != r132) goto L25;
    L29:
        Text r33 = this.priceText;
        if (r33 != null) goto L32;
        float r34 = r28;
    L33:
        float r53 = AndroidUtilities.dp(14.33f);
        RectF r6 = AndroidUtilities.rectTmp;
        float r35 = (r34 + AndroidUtilities.dp(10.0f)) / 2.0f;
        float r133 = r13 - r35;
        float r54 = r53 / 2.0f;
        r6.set(r133, r4 - r54, r13 + r35, r54 + r4);
        r6.inset(-AndroidUtilities.dp(1.33f), AndroidUtilities.dp(-1.33f));
        this.priceBackgroundPaint.setColor(getThemedColor(Theme.key_dialogBackground));
        r25.drawRoundRect(r6, r6.height() / 2.0f, r6.height() / 2.0f, this.priceBackgroundPaint);
        r6.inset(AndroidUtilities.dp(1.33f), AndroidUtilities.dp(1.33f));
        this.priceBackgroundPaint.setColor(getThemedColor(Theme.key_dialogRoundCheckBox));
        r25.drawRoundRect(r6, r6.height() / 2.0f, r6.height() / 2.0f, this.priceBackgroundPaint);
        Text r14 = this.priceText;
        if (r14 == null) goto L37;
        r14.draw(r25, AndroidUtilities.dp(5.0f) + r133, r4, -1, 1.0f);
    L37:
        float r15 = this.premiumBlockedT.set(this.premiumBlocked);
        if (r15 <= r28) goto L47;
        int r36 = this.imageView.getBottom() - AndroidUtilities.dp(9.0f);
        int r42 = this.imageView.getRight() - AndroidUtilities.dp(9.33f);
        r25.save();
        Theme.dialogs_onlineCirclePaint.setColor(getThemedColor(Theme.key_windowBackgroundWhite));
        float r55 = r42;
        float r62 = r36;
        r25.drawCircle(r55, r62, AndroidUtilities.dp(12.0f) * r15, Theme.dialogs_onlineCirclePaint);
        if (this.premiumGradient != null) goto L42;
        this.premiumGradient = new PremiumGradient.PremiumGradientTools(Theme.key_premiumGradient1, Theme.key_premiumGradient2, -1, -1, -1, this.resourcesProvider);
    L42:
        this.premiumGradient.gradientMatrix(r42 - AndroidUtilities.dp(10.0f), r36 - AndroidUtilities.dp(10.0f), r42 + AndroidUtilities.dp(10.0f), r36 + AndroidUtilities.dp(10.0f), 0.0f, 0.0f);
        r25.drawCircle(r55, r62, AndroidUtilities.dp(10.0f) * r15, this.premiumGradient.paint);
        if (this.lockDrawable != null) goto L45;
        Drawable r37 = getContext().getResources().getDrawable(R.drawable.msg_mini_lock2).mutate();
        this.lockDrawable = r37;
        r37.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
    L45:
        this.lockDrawable.setBounds((int) (r55 - (((r3.getIntrinsicWidth() / 2.0f) * 0.875f) * r15)), (int) (r62 - (((this.lockDrawable.getIntrinsicHeight() / 2.0f) * 0.875f) * r15)), (int) (r55 + (((this.lockDrawable.getIntrinsicWidth() / 2.0f) * 0.875f) * r15)), (int) (r62 + (((this.lockDrawable.getIntrinsicHeight() / 2.0f) * 0.875f) * r15)));
        this.lockDrawable.setAlpha((int) (255.0f * r15));
        this.lockDrawable.draw(r25);
        r25.restore();
    L47:
        if (this.premiumBlocked == true) goto L59;
        TLRPC.User r38 = this.user;
        if (r38.self == true) goto L59;
        if (r38.bot == true) goto L59;
        TLRPC.UserStatus r39 = r38.status;
        if (r39 == null) goto L57;
        if (r39.expires <= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L57;
    L58:
        boolean r10 = true;
    L60:
        if (r10 == false) goto L62;
    L63:
        float r43 = (this.imageView.getWidth() / 2.0f) * this.imageView.getScaleX();
        float r310 = this.imageView.getX() + (this.imageView.getWidth() / 2.0f);
        float r63 = r310 + r43;
        float r311 = r310 - r43;
        float r56 = (this.imageView.getY() + (this.imageView.getHeight() / 2.0f)) + r43;
        float r44 = ExteraConfig.getOnlineDotOuterRadius();
        float r11 = ExteraConfig.getOnlineDotInnerRadius();
        float r134 = ExteraConfig.getOnlineDotOffset(AndroidUtilities.dp(10.0f), r44);
        float r142 = ExteraConfig.getOnlineDotOffset(AndroidUtilities.dp(6.0f), r44);
        if (LocaleController.isRTL == false) goto L66;
        float r312 = r311 + r134;
    L67:
        float r57 = r56 - r142;
        float r64 = (this.onlineProgress * (1.0f - r15)) * (1.0f - r122);
        Theme.dialogs_onlineCirclePaint.setColor(getThemedColor(Theme.key_windowBackgroundWhite));
        r25.drawCircle(r312, r57, r44 * r64, Theme.dialogs_onlineCirclePaint);
        Theme.dialogs_onlineCirclePaint.setColor(getThemedColor(Theme.key_chats_onlineCircle));
        r25.drawCircle(r312, r57, r11 * r64, Theme.dialogs_onlineCirclePaint);
        float r16 = this.onlineProgress;
        if (r10 == false) goto L77;
        if (r16 >= 1.0f) goto L82;
        float r17 = r16 + (r8 / 150.0f);
        this.onlineProgress = r17;
        if (r17 <= 1.0f) goto L74;
        this.onlineProgress = 1.0f;
    L74:
        this.imageView.invalidate();
        invalidate();
        return r7;
    L77:
        if (r16 <= r28) goto L82;
        float r18 = r16 - (r8 / 150.0f);
        this.onlineProgress = r18;
        if (r18 >= r28) goto L81;
        this.onlineProgress = r28;
    L81:
        this.imageView.invalidate();
        invalidate();
        goto L82
    L66:
        r312 = r63 - r134;
        goto L67
    L62:
        if (this.onlineProgress == r28) goto L82;
    L57:
        if (MessagesController.getInstance(this.currentAccount).onlinePrivacy.containsKey(Long.valueOf(this.user.id)) == true) goto L58;
    L59:
        r10 = false;
        goto L60
    L32:
        r34 = r33.getCurrentWidth();
        goto L33
    L25:
        if (r132 <= 0) goto L29;
    L28:
        StringBuilder r58 = new StringBuilder("⭐️");
        long r135 = this.starsPriceBlocked;
        this.priceTextValue = r135;
        r58.append(AndroidUtilities.formatWholeNumber((int) r135, 0));
        this.priceText = new Text(StarsIntroActivity.replaceStars(r58.toString(), 0.65f), 9.33f, AndroidUtilities.bold());
        goto L29
    L27:
        r28 = 0.0f;
        goto L28
    L36:
        r28 = 0.0f;
        goto L37
    L17:
        r32 = false;
        goto L18
    }

    public long getCurrentDialog() {
        return this.currentDialog;
    }

    public BackupImageView getImageView() {
        return this.imageView;
    }

    public long getStarsPrice() {
        return this.starsPriceBlocked;
    }

    public boolean isBlocked() {
        return this.premiumBlocked;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.userIsPremiumBlockedUpadted);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.userIsPremiumBlockedUpadted);
    }

    @Override // android.view.View
    public void onDraw(Canvas r7) {
        int r0 = this.imageView.getLeft() + (this.imageView.getMeasuredWidth() / 2);
        int r1 = this.imageView.getTop() + (this.imageView.getMeasuredHeight() / 2);
        Theme.checkboxSquare_checkPaint.setColor(getThemedColor(Theme.key_dialogRoundCheckBox));
        Theme.checkboxSquare_checkPaint.setAlpha((int) (this.checkBox.getProgress() * 255.0f));
        if (this.currentType != 2) goto L5;
        float r2 = 24.0f;
    L6:
        int r22 = AndroidUtilities.dp(r2);
        RectF r3 = AndroidUtilities.rectTmp;
        r3.set(r0 - r22, r1 - r22, r0 + r22, r1 + r22);
        r7.drawRoundRect(r3, this.imageView.getRoundRadius()[0] + AndroidUtilities.dp(2.0f), this.imageView.getRoundRadius()[0] + AndroidUtilities.dp(2.0f), Theme.checkboxSquare_checkPaint);
        super.onDraw(r7);
        return;
    L5:
        r2 = 28.0f;
        goto L6
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
        super.onInitializeAccessibilityNodeInfo(r1);
        if (this.checkBox.isChecked() == false) goto L6;
        r1.setSelected(true);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        if (this.currentType != 2) goto L5;
        float r32 = 95.0f;
    L6:
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r32), TLObject.FLAG_30));
        return;
    L5:
        r32 = 103.0f;
        goto L6
    }

    public String repostToCustomName() {
        return LocaleController.getString(R.string.FwdMyStory);
    }

    public void setChecked(boolean r2, boolean r3) {
        this.checkBox.setChecked(r2, r3);
        if (r2 == true) goto L6;
        setTopic(null, true);
        return;
    }

    public void setDialog(long r11, boolean r13, CharSequence r14) {
        this.avatarDrawable.setScaleSize(1.0f);
        boolean r1 = true;
        if (r11 != LongCompanionObject.MAX_VALUE) goto L9;
        this.nameTextView.setText(repostToCustomName());
        if (this.repostStoryDrawable != null) goto L7;
        this.repostStoryDrawable = new RepostStoryDrawable(getContext(), this.imageView, true, this.resourcesProvider);
    L7:
        this.imageView.setImage(null, null, this.repostStoryDrawable, null);
    L58:
        this.currentDialog = r11;
        this.checkBox.setChecked(r13, false);
        return;
    L9:
        if (DialogObject.isUserDialog(r11) == false) goto L36;
        this.user = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r11));
        TL_account.RequirementToContact r0 = MessagesController.getInstance(this.currentAccount).isUserContactBlocked(r11);
        this.premiumBlocked = DialogObject.isPremiumBlocked(r0);
        this.starsPriceBlocked = DialogObject.getMessagesStarsPrice(r0);
        TextView r02 = this.nameTextView;
        if (this.premiumBlocked == false) goto L13;
        int r6 = Theme.key_windowBackgroundWhiteGrayText5;
    L14:
        r02.setTextColor(getThemedColor(r6));
        this.premiumBlockedT.force(this.premiumBlocked);
        AnimatedFloat r03 = this.starsBlockedT;
        if (this.starsPriceBlocked <= 0) goto L17;
        boolean r62 = true;
    L18:
        r03.force(r62);
        invalidate();
        this.avatarDrawable.setInfo(this.currentAccount, this.user);
        if (this.currentType == 2) goto L24;
        if (UserObject.isReplyUser(this.user) == false) goto L24;
        this.nameTextView.setText(LocaleController.getString(R.string.RepliesTitle));
        this.avatarDrawable.setAvatarType(12);
        this.imageView.setImage(null, null, this.avatarDrawable, this.user);
    L35:
        this.imageView.setRoundRadius(ExteraConfig.getAvatarCorners(56.0f));
    L24:
        if (this.currentType != 2) goto L26;
    L28:
        if (r14 == null) goto L30;
        this.nameTextView.setText(r14);
    L34:
        this.imageView.setForUserOrChat(this.user, this.avatarDrawable);
        goto L35
    L30:
        TLRPC.User r142 = this.user;
        TextView r04 = this.nameTextView;
        if (r142 == null) goto L33;
        r04.setText(ContactsController.formatName(r142.first_name, r142.last_name));
        goto L34
    L33:
        r04.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L34
    L26:
        if (UserObject.isUserSelf(this.user) == false) goto L28;
        this.nameTextView.setText(LocaleController.getString(R.string.SavedMessages));
        this.avatarDrawable.setAvatarType(1);
        this.imageView.setImage(null, null, this.avatarDrawable, this.user);
        goto L35
    L17:
        r62 = false;
        goto L18
    L13:
        r6 = Theme.key_dialogTextBlack;
        goto L14
    L36:
        this.user = null;
        this.premiumBlocked = false;
        this.premiumBlockedT.force(0.0f);
        this.starsPriceBlocked = MessagesController.getInstance(this.currentAccount).getSendPaidMessagesStars(r11);
        this.starsBlockedT.force(false);
        TLRPC.Chat r05 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r11));
        if (r14 == null) goto L39;
        this.nameTextView.setText(r14);
    L46:
        if (ChatObject.isMonoForum(r05) == false) goto L48;
        ForumUtilities.setMonoForumAvatar(this.currentAccount, r05, this.avatarDrawable, this.imageView);
    L49:
        BackupImageView r143 = this.imageView;
        if (r05 != null) goto L52;
    L56:
        r1 = false;
    L57:
        r143.setRoundRadius(ExteraConfig.getAvatarCorners(56.0f, false, r1));
        goto L58
    L52:
        if (r05.forum == true) goto L57;
        if (r05.monoforum == false) goto L56;
    L48:
        this.avatarDrawable.setInfo(this.currentAccount, r05);
        this.imageView.setForUserOrChat(r05, this.avatarDrawable);
        goto L49
    L39:
        if (r05 == null) goto L44;
        boolean r144 = r05.monoforum;
        TextView r3 = this.nameTextView;
        if (r144 == false) goto L43;
        r3.setText(ForumUtilities.getMonoForumTitle(this.currentAccount, r05));
        goto L46
    L43:
        r3.setText(r05.title);
        goto L46
    L44:
        this.nameTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L46
    }

    public void setTopic(TLRPC.TL_forumTopic r2, boolean r3) {
        setTopic(r2, false, r3);
    }

    public void setTopic(TLRPC.TL_forumTopic r6, boolean r7, boolean r8) {
        boolean r0 = this.topicWasVisible;
        if (r6 == null) goto L5;
        boolean r2 = true;
    L6:
        if (r0 != r2) goto L10;
        if (r8 == false) goto L10;
        return;
    L10:
        SpringAnimation r02 = (SpringAnimation) this.topicTextView.getTag(R.id.spring_tag);
        if (r02 == null) goto L13;
        r02.cancel();
    L13:
        if (r2 == false) goto L19;
        SimpleTextView r03 = this.topicTextView;
        if (r7 == false) goto L17;
        r03.setText(MessagesController.getInstance(this.currentAccount).getPeerName(DialogObject.getPeerDialogId(r6.from_id)));
    L18:
        this.topicTextView.requestLayout();
        goto L19
    L17:
        r03.setText(ForumUtilities.getTopicSpannedName(r6, r03.getTextPaint(), false));
    L19:
        float r72 = 0.0f;
        if (r8 == true) goto L22;
        SimpleTextView r82 = this.topicTextView;
        if (r2 == false) goto L32;
        r82.setAlpha(1.0f);
        this.nameTextView.setAlpha(0.0f);
        this.topicTextView.setTranslationX(0.0f);
        this.nameTextView.setTranslationX(AndroidUtilities.dp(10.0f));
    L33:
        this.topicWasVisible = r2;
        return;
    L32:
        r82.setAlpha(0.0f);
        this.nameTextView.setAlpha(1.0f);
        this.topicTextView.setTranslationX(-AndroidUtilities.dp(10.0f));
        this.nameTextView.setTranslationX(0.0f);
        goto L33
    L22:
        if (r2 == false) goto L24;
        float r3 = 0.0f;
    L25:
        SpringAnimation r83 = new SpringAnimation(new FloatValueHolder(r3));
        if (r2 == false) goto L28;
        r72 = 1000.0f;
    L28:
        SpringAnimation r62 = r83.setSpring(new SpringForce(r72).setStiffness(1500.0f).setDampingRatio(1.0f)).addUpdateListener(new ShareDialogCell$$ExternalSyntheticLambda1(this)).addEndListener(new ShareDialogCell$$ExternalSyntheticLambda2(this));
        this.topicTextView.setTag(R.id.spring_tag, r62);
        r62.start();
        goto L33
    L24:
        r3 = 1000.0f;
        goto L25
    L5:
        r2 = false;
        goto L6
    }
}
