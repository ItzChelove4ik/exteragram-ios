package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatThemeController;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.EmojiThemes;
import org.telegram.ui.ActionBar.MessageDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.theme.ITheme;
import org.telegram.ui.ChatBackgroundDrawable;
import org.telegram.ui.Components.ChatThemeBottomSheet;

/* loaded from: classes7.dex */
public class ThemeSmallPreviewView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private final float BUBBLE_HEIGHT;
    private final float BUBBLE_WIDTH;
    private final float INNER_RADIUS;
    private final float INNER_RECT_SPACE;
    private final float STROKE_RADIUS;
    ThemeDrawable animateOutThemeDrawable;
    Runnable animationCancelRunnable;
    boolean attached;
    private AvatarDrawable avatarDrawable;
    private final ImageReceiver avatarImageReceiver;
    private final Paint backgroundFillPaint;
    private BackupImageView backupImageView;
    private float changeThemeProgress;
    ChatBackgroundDrawable chatBackgroundDrawable;
    public ChatThemeBottomSheet.ChatThemeItem chatThemeItem;
    private final Path clipPath;
    private final int currentAccount;
    private int currentType;
    public TLRPC.WallPaper fallbackWallpaper;
    private boolean hasAnimatedEmoji;
    boolean isSelected;
    public int lastThemeIndex;
    MessageDrawable messageDrawableIn;
    MessageDrawable messageDrawableOut;
    private TextPaint noThemeTextPaint;
    Paint outlineBackgroundPaint;
    int patternColor;
    private final RectF rectF;
    private final Theme.ResourcesProvider resourcesProvider;
    private float selectionProgress;
    private ValueAnimator strokeAlphaAnimator;
    private StaticLayout textLayout;
    ThemeDrawable themeDrawable;
    private long themeUserByUserId;

    /* renamed from: org.telegram.ui.Components.ThemeSmallPreviewView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ThemeSmallPreviewView this$0;
        final /* synthetic */ boolean val$selected;

        public AnonymousClass1(ThemeSmallPreviewView r1, boolean r2) {
            this.this$0 = r1;
            this.val$selected = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            super.onAnimationEnd(r2);
            ThemeSmallPreviewView r22 = this.this$0;
            if (this.val$selected == false) goto L5;
            float r0 = 1.0f;
        L6:
            ThemeSmallPreviewView.m13314$$Nest$fputselectionProgress(r22, r0);
            this.this$0.invalidate();
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    public class ThemeDrawable {
        private final Paint inBubblePaint;
        private final Paint outBubblePaintSecond;
        Drawable previewDrawable;
        Drawable rotateDrawable;
        private final Paint strokePaint;
        final /* synthetic */ ThemeSmallPreviewView this$0;

        /* renamed from: -$$Nest$fgetinBubblePaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m13317$$Nest$fgetinBubblePaint(ThemeDrawable r0) {
            return r0.inBubblePaint;
        }

        /* renamed from: -$$Nest$fgetoutBubblePaintSecond, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m13318$$Nest$fgetoutBubblePaintSecond(ThemeDrawable r0) {
            return r0.outBubblePaintSecond;
        }

        /* renamed from: -$$Nest$fgetstrokePaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m13319$$Nest$fgetstrokePaint(ThemeDrawable r0) {
            return r0.strokePaint;
        }

        public ThemeDrawable(ThemeSmallPreviewView r3) {
            this.this$0 = r3;
            Paint r32 = new Paint(1);
            this.strokePaint = r32;
            this.outBubblePaintSecond = new Paint(1);
            this.inBubblePaint = new Paint(1);
            r32.setStyle(Paint.Style.STROKE);
            r32.setStrokeWidth(AndroidUtilities.dp(2.0f));
        }

        public void draw(Canvas r19, float r20) {
            ThemeSmallPreviewView r2 = this.this$0;
            if (r2.isSelected == false) goto L5;
        L6:
            ChatThemeBottomSheet.ChatThemeItem r22 = this.this$0.chatThemeItem;
            EmojiThemes.ThemeItem r23 = r22.chatTheme.getThemeItem(r22.themeIndex);
            if (this.this$0.chatThemeItem.chatTheme.isAnyStub() == false) goto L9;
            int r24 = ThemeSmallPreviewView.m13316$$Nest$mgetThemedColor(this.this$0, Theme.key_featuredStickers_addButton);
        L10:
            this.strokePaint.setColor(r24);
            this.strokePaint.setAlpha((int) ((ThemeSmallPreviewView.m13311$$Nest$fgetselectionProgress(this.this$0) * r20) * 255.0f));
            float r25 = (this.strokePaint.getStrokeWidth() * 0.5f) + (AndroidUtilities.dp(4.0f) * (1.0f - ThemeSmallPreviewView.m13311$$Nest$fgetselectionProgress(this.this$0)));
            ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).set(r25, r25, this.this$0.getWidth() - r25, this.this$0.getHeight() - r25);
            r19.drawRoundRect(ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0), ThemeSmallPreviewView.m13305$$Nest$fgetSTROKE_RADIUS(this.this$0), ThemeSmallPreviewView.m13305$$Nest$fgetSTROKE_RADIUS(this.this$0), this.strokePaint);
        L11:
            int r3 = (int) (r20 * 255.0f);
            this.outBubblePaintSecond.setAlpha(r3);
            this.inBubblePaint.setAlpha(r3);
            ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).set(ThemeSmallPreviewView.m13304$$Nest$fgetINNER_RECT_SPACE(this.this$0), ThemeSmallPreviewView.m13304$$Nest$fgetINNER_RECT_SPACE(this.this$0), this.this$0.getWidth() - ThemeSmallPreviewView.m13304$$Nest$fgetINNER_RECT_SPACE(this.this$0), this.this$0.getHeight() - ThemeSmallPreviewView.m13304$$Nest$fgetINNER_RECT_SPACE(this.this$0));
            EmojiThemes r26 = this.this$0.chatThemeItem.chatTheme;
            if (r26 != null) goto L14;
        L77:
            ThemeSmallPreviewView r27 = this.this$0;
            if (r27.fallbackWallpaper != null) goto L84;
            r19.drawRoundRect(ThemeSmallPreviewView.m13310$$Nest$fgetrectF(r27), ThemeSmallPreviewView.m13303$$Nest$fgetINNER_RADIUS(this.this$0), ThemeSmallPreviewView.m13303$$Nest$fgetINNER_RADIUS(this.this$0), ThemeSmallPreviewView.m13307$$Nest$fgetbackgroundFillPaint(this.this$0));
            r19.save();
            StaticLayout r28 = ThemeSmallPreviewView.m13315$$Nest$mgetNoThemeStaticLayout(this.this$0);
            r19.translate((this.this$0.getWidth() - r28.getWidth()) * 0.5f, AndroidUtilities.dp(18.0f));
            r28.draw(r19);
            r19.restore();
            return;
        L84:
            return;
        L14:
            if (r26.isAnyStub() == false) goto L19;
            if (this.this$0.chatThemeItem.chatTheme.wallpaper == null) goto L77;
        L19:
            if (ThemeSmallPreviewView.m13309$$Nest$fgetcurrentType(this.this$0) == 4) goto L81;
            int r29 = ThemeSmallPreviewView.m13309$$Nest$fgetcurrentType(this.this$0);
            ThemeSmallPreviewView r32 = this.this$0;
            if (r29 == 2) goto L23;
            float r210 = ThemeSmallPreviewView.m13304$$Nest$fgetINNER_RECT_SPACE(r32) + AndroidUtilities.dp(8.0f);
            float r4 = ThemeSmallPreviewView.m13304$$Nest$fgetINNER_RECT_SPACE(this.this$0);
            if (ThemeSmallPreviewView.m13309$$Nest$fgetcurrentType(this.this$0) != 3) goto L29;
            float r8 = 5.0f;
        L30:
            float r42 = r4 + AndroidUtilities.dp(r8);
            if (ThemeSmallPreviewView.m13309$$Nest$fgetcurrentType(this.this$0) != 0) goto L33;
        L36:
            RectF r82 = ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0);
            float r13 = ThemeSmallPreviewView.m13302$$Nest$fgetBUBBLE_WIDTH(this.this$0);
            if (ThemeSmallPreviewView.m13309$$Nest$fgetcurrentType(this.this$0) != 3) goto L39;
            float r14 = 1.2f;
        L40:
            r82.set(r42, r210, (r13 * r14) + r42, ThemeSmallPreviewView.m13301$$Nest$fgetBUBBLE_HEIGHT(this.this$0) + r210);
        L42:
            if (ThemeSmallPreviewView.m13309$$Nest$fgetcurrentType(this.this$0) != 3) goto L44;
            Paint r43 = this.inBubblePaint;
        L46:
            if (ThemeSmallPreviewView.m13309$$Nest$fgetcurrentType(this.this$0) != 0) goto L48;
        L49:
            float r202 = 8.0f;
            r19.drawRoundRect(ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0), ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).height() * 0.5f, ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).height() * 0.5f, r43);
        L53:
            if (ThemeSmallPreviewView.m13309$$Nest$fgetcurrentType(this.this$0) != 0) goto L55;
        L58:
            float r33 = ThemeSmallPreviewView.m13304$$Nest$fgetINNER_RECT_SPACE(this.this$0) + AndroidUtilities.dp(5.0f);
            float r211 = r210 + (ThemeSmallPreviewView.m13301$$Nest$fgetBUBBLE_HEIGHT(this.this$0) + AndroidUtilities.dp(4.0f));
            RectF r44 = ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0);
            float r5 = ThemeSmallPreviewView.m13302$$Nest$fgetBUBBLE_WIDTH(this.this$0);
            if (ThemeSmallPreviewView.m13309$$Nest$fgetcurrentType(this.this$0) != 3) goto L61;
            float r16 = 0.8f;
        L62:
            r44.set(r33, r211, (r5 * r16) + r33, ThemeSmallPreviewView.m13301$$Nest$fgetBUBBLE_HEIGHT(this.this$0) + r211);
        L64:
            if (ThemeSmallPreviewView.m13309$$Nest$fgetcurrentType(this.this$0) != 0) goto L66;
        L70:
            r19.drawRoundRect(ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0), ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).height() * 0.5f, ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).height() * 0.5f, this.inBubblePaint);
            if (ThemeSmallPreviewView.m13313$$Nest$fgetthemeUserByUserId(this.this$0) == 0) goto L83;
            float r212 = ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).centerY();
            float r34 = ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).left + (ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).height() / 2.0f);
            float r45 = ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).right - (ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).height() / 2.0f);
            ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).set(r34 - AndroidUtilities.dp(r202), r212 - AndroidUtilities.dp(r202), r34 + AndroidUtilities.dp(r202), AndroidUtilities.dp(r202) + r212);
            ThemeSmallPreviewView.m13306$$Nest$fgetavatarImageReceiver(this.this$0).setImageCoords(ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0));
            ThemeSmallPreviewView.m13306$$Nest$fgetavatarImageReceiver(this.this$0).draw(r19);
            if (this.rotateDrawable != null) goto L75;
            this.rotateDrawable = this.this$0.getContext().getDrawable(R.drawable.mini_replace_16).mutate();
        L75:
            int r46 = (int) r45;
            int r213 = (int) r212;
            this.rotateDrawable.setBounds(r46 - AndroidUtilities.dp(r202), r213 - AndroidUtilities.dp(r202), r46 + AndroidUtilities.dp(r202), r213 + AndroidUtilities.dp(r202));
            this.rotateDrawable.draw(r19);
            return;
        L83:
            return;
        L66:
            if (ThemeSmallPreviewView.m13309$$Nest$fgetcurrentType(this.this$0) == 3) goto L70;
            ThemeSmallPreviewView r214 = this.this$0;
            r214.messageDrawableIn.setBounds(((int) ThemeSmallPreviewView.m13310$$Nest$fgetrectF(r214).left) - AndroidUtilities.dp(4.0f), ((int) ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).top) - AndroidUtilities.dp(2.0f), (int) ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).right, ((int) ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).bottom) + AndroidUtilities.dp(2.0f));
            ThemeSmallPreviewView r215 = this.this$0;
            r215.messageDrawableIn.setRoundRadius((int) (ThemeSmallPreviewView.m13310$$Nest$fgetrectF(r215).height() * 0.5f));
            this.this$0.messageDrawableIn.draw(r19, this.inBubblePaint);
            return;
        L61:
            r16 = 1.0f;
            goto L62
        L55:
            if (ThemeSmallPreviewView.m13309$$Nest$fgetcurrentType(this.this$0) == 3) goto L58;
            float r35 = this.this$0.getMeasuredWidth() * 0.1f;
            ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).set(r35, this.this$0.getMeasuredHeight() * 0.35f, this.this$0.getMeasuredWidth() * 0.65f, this.this$0.getMeasuredHeight() * 0.55f);
            goto L64
        L48:
            if (ThemeSmallPreviewView.m13309$$Nest$fgetcurrentType(this.this$0) == 3) goto L49;
            ThemeSmallPreviewView r83 = this.this$0;
            r202 = 8.0f;
            r83.messageDrawableOut.setBounds((int) ThemeSmallPreviewView.m13310$$Nest$fgetrectF(r83).left, ((int) ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).top) - AndroidUtilities.dp(2.0f), ((int) ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).right) + AndroidUtilities.dp(4.0f), ((int) ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).bottom) + AndroidUtilities.dp(2.0f));
            ThemeSmallPreviewView r36 = this.this$0;
            r36.messageDrawableOut.setRoundRadius((int) (ThemeSmallPreviewView.m13310$$Nest$fgetrectF(r36).height() * 0.5f));
            this.this$0.messageDrawableOut.draw(r19, r43);
            goto L53
        L44:
            r43 = this.outBubblePaintSecond;
            goto L46
        L39:
            r14 = 1.0f;
            goto L40
        L33:
            if (ThemeSmallPreviewView.m13309$$Nest$fgetcurrentType(this.this$0) == 3) goto L36;
            r210 = this.this$0.getMeasuredHeight() * 0.12f;
            ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0).set(this.this$0.getMeasuredWidth() - (this.this$0.getMeasuredWidth() * 0.65f), r210, this.this$0.getMeasuredWidth() - (this.this$0.getMeasuredWidth() * 0.1f), this.this$0.getMeasuredHeight() * 0.32f);
            goto L42
        L29:
            r8 = 22.0f;
            goto L30
        L23:
            if (r32.chatThemeItem.icon == null) goto L82;
            r19.drawBitmap(this.this$0.chatThemeItem.icon, (r32.getWidth() - this.this$0.chatThemeItem.icon.getWidth()) * 0.5f, AndroidUtilities.dp(21.0f), null);
            return;
        L82:
            return;
        L81:
            return;
        L9:
            r24 = r23.outLineColor;
            goto L10
        L5:
            if (ThemeSmallPreviewView.m13312$$Nest$fgetstrokeAlphaAnimator(r2) == null) goto L11;
            goto L6
        }

        public void drawBackground(Canvas r7, float r8) {
            if (this.previewDrawable == null) goto L20;
            r7.save();
            r7.clipPath(ThemeSmallPreviewView.m13308$$Nest$fgetclipPath(this.this$0));
            Drawable r0 = this.previewDrawable;
            if ((r0 instanceof BitmapDrawable) == false) goto L10;
            float r02 = r0.getIntrinsicWidth();
            float r1 = this.previewDrawable.getIntrinsicHeight();
            float r3 = r02 / r1;
            float r4 = this.this$0.getWidth() / this.this$0.getHeight();
            ThemeSmallPreviewView r42 = this.this$0;
            if (r3 <= r4) goto L9;
            int r03 = (int) ((r42.getWidth() * r1) / r02);
            int r12 = (r03 - this.this$0.getWidth()) / 2;
            this.previewDrawable.setBounds(r12, 0, r03 + r12, this.this$0.getHeight());
        L11:
            this.previewDrawable.setAlpha((int) (255.0f * r8));
            this.previewDrawable.draw(r7);
            Drawable r04 = this.previewDrawable;
            if ((r04 instanceof ColorDrawable) == false) goto L14;
        L17:
            int r05 = this.this$0.outlineBackgroundPaint.getAlpha();
            this.this$0.outlineBackgroundPaint.setAlpha((int) (r05 * r8));
            float r82 = ThemeSmallPreviewView.m13304$$Nest$fgetINNER_RECT_SPACE(this.this$0);
            RectF r13 = AndroidUtilities.rectTmp;
            r13.set(r82, r82, this.this$0.getWidth() - r82, this.this$0.getHeight() - r82);
            r7.drawRoundRect(r13, ThemeSmallPreviewView.m13303$$Nest$fgetINNER_RADIUS(this.this$0), ThemeSmallPreviewView.m13303$$Nest$fgetINNER_RADIUS(this.this$0), this.this$0.outlineBackgroundPaint);
            this.this$0.outlineBackgroundPaint.setAlpha(r05);
        L18:
            r7.restore();
            return;
        L14:
            if ((r04 instanceof MotionBackgroundDrawable) == false) goto L18;
            if (((MotionBackgroundDrawable) r04).isOneColor() == false) goto L18;
        L9:
            int r06 = (int) ((r42.getHeight() * r1) / r02);
            int r14 = (this.this$0.getHeight() - r06) / 2;
            this.previewDrawable.setBounds(0, r14, this.this$0.getWidth(), r06 + r14);
            goto L11
        L10:
            r0.setBounds(0, 0, this.this$0.getWidth(), this.this$0.getHeight());
            goto L11
        L20:
            ChatThemeBottomSheet.ChatThemeItem r83 = this.this$0.chatThemeItem;
            if (r83 == null) goto L30;
            EmojiThemes r84 = r83.chatTheme;
            if (r84 == null) goto L30;
            if (r84.isAnyStub() == false) goto L30;
            if (this.this$0.chatBackgroundDrawable == null) goto L30;
            return;
        L30:
            r7.drawRoundRect(ThemeSmallPreviewView.m13310$$Nest$fgetrectF(this.this$0), ThemeSmallPreviewView.m13303$$Nest$fgetINNER_RADIUS(this.this$0), ThemeSmallPreviewView.m13303$$Nest$fgetINNER_RADIUS(this.this$0), ThemeSmallPreviewView.m13307$$Nest$fgetbackgroundFillPaint(this.this$0));
        }
    }

    public static /* synthetic */ void $r8$lambda$5qG8zpAtq6HOXpldrDmqQ00ItsA(ThemeSmallPreviewView r0) {
        r0.lambda$playEmojiAnimation$5();
    }

    public static /* synthetic */ void $r8$lambda$QMSOJIft2nL9m8T1onAzRNoH3TQ(ThemeSmallPreviewView r0, long r1, ChatThemeBottomSheet.ChatThemeItem r3, int r4, Pair r5) {
        r0.lambda$setItem$0(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$WJvmzbAM3IC9hi8HqQLMnqJf_1w(ThemeSmallPreviewView r0, ValueAnimator r1) {
        r0.lambda$setSelected$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$X2HKrMs6KrXOKswCmqPEr_KUwgc(ThemeSmallPreviewView r0, ChatThemeBottomSheet.ChatThemeItem r1, int r2, Bitmap r3) {
        r0.lambda$setItem$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$c3FhGGpnuJJJCGSHcZlVd9y4_Dg(ThemeSmallPreviewView r0, ChatThemeBottomSheet.ChatThemeItem r1, TLRPC.WallPaper r2, ImageReceiver r3, boolean r4, boolean r5, boolean r6) {
        r0.lambda$setItem$1(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$j9Gc1z_HRBKMYj7sPCojNwxbkK0(ThemeSmallPreviewView r0, ChatThemeBottomSheet.ChatThemeItem r1, int r2) {
        r0.lambda$setItem$3(r1, r2);
    }

    /* renamed from: -$$Nest$fgetBUBBLE_HEIGHT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13301$$Nest$fgetBUBBLE_HEIGHT(ThemeSmallPreviewView r0) {
        return r0.BUBBLE_HEIGHT;
    }

    /* renamed from: -$$Nest$fgetBUBBLE_WIDTH, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13302$$Nest$fgetBUBBLE_WIDTH(ThemeSmallPreviewView r0) {
        return r0.BUBBLE_WIDTH;
    }

    /* renamed from: -$$Nest$fgetINNER_RADIUS, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13303$$Nest$fgetINNER_RADIUS(ThemeSmallPreviewView r0) {
        return r0.INNER_RADIUS;
    }

    /* renamed from: -$$Nest$fgetINNER_RECT_SPACE, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13304$$Nest$fgetINNER_RECT_SPACE(ThemeSmallPreviewView r0) {
        return r0.INNER_RECT_SPACE;
    }

    /* renamed from: -$$Nest$fgetSTROKE_RADIUS, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13305$$Nest$fgetSTROKE_RADIUS(ThemeSmallPreviewView r0) {
        return r0.STROKE_RADIUS;
    }

    /* renamed from: -$$Nest$fgetavatarImageReceiver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageReceiver m13306$$Nest$fgetavatarImageReceiver(ThemeSmallPreviewView r0) {
        return r0.avatarImageReceiver;
    }

    /* renamed from: -$$Nest$fgetbackgroundFillPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m13307$$Nest$fgetbackgroundFillPaint(ThemeSmallPreviewView r0) {
        return r0.backgroundFillPaint;
    }

    /* renamed from: -$$Nest$fgetclipPath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Path m13308$$Nest$fgetclipPath(ThemeSmallPreviewView r0) {
        return r0.clipPath;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13309$$Nest$fgetcurrentType(ThemeSmallPreviewView r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetrectF, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m13310$$Nest$fgetrectF(ThemeSmallPreviewView r0) {
        return r0.rectF;
    }

    /* renamed from: -$$Nest$fgetselectionProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13311$$Nest$fgetselectionProgress(ThemeSmallPreviewView r0) {
        return r0.selectionProgress;
    }

    /* renamed from: -$$Nest$fgetstrokeAlphaAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m13312$$Nest$fgetstrokeAlphaAnimator(ThemeSmallPreviewView r0) {
        return r0.strokeAlphaAnimator;
    }

    /* renamed from: -$$Nest$fgetthemeUserByUserId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m13313$$Nest$fgetthemeUserByUserId(ThemeSmallPreviewView r2) {
        return r2.themeUserByUserId;
    }

    /* renamed from: -$$Nest$fputselectionProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13314$$Nest$fputselectionProgress(ThemeSmallPreviewView r0, float r1) {
        r0.selectionProgress = r1;
    }

    /* renamed from: -$$Nest$mgetNoThemeStaticLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StaticLayout m13315$$Nest$mgetNoThemeStaticLayout(ThemeSmallPreviewView r0) {
        return r0.getNoThemeStaticLayout();
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13316$$Nest$mgetThemedColor(ThemeSmallPreviewView r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public ThemeSmallPreviewView(Context r8, int r9, Theme.ResourcesProvider r10, int r11) {
        super(r8);
        this.STROKE_RADIUS = AndroidUtilities.dp(8.0f);
        this.INNER_RADIUS = AndroidUtilities.dp(6.0f);
        this.INNER_RECT_SPACE = AndroidUtilities.dp(4.0f);
        this.BUBBLE_HEIGHT = AndroidUtilities.dp(21.0f);
        this.BUBBLE_WIDTH = AndroidUtilities.dp(41.0f);
        this.themeDrawable = new ThemeDrawable(this);
        this.changeThemeProgress = 1.0f;
        this.outlineBackgroundPaint = new Paint(1);
        this.backgroundFillPaint = new Paint(1);
        this.rectF = new RectF();
        this.clipPath = new Path();
        this.messageDrawableOut = new MessageDrawable(0, true, false);
        this.messageDrawableIn = new MessageDrawable(0, false, false);
        this.currentType = r11;
        this.currentAccount = r9;
        this.resourcesProvider = r10;
        ImageReceiver r92 = new ImageReceiver(this);
        this.avatarImageReceiver = r92;
        r92.setRoundRadius(AndroidUtilities.dp(8.0f));
        setBackgroundColor(getThemedColor(Theme.key_dialogBackgroundGray));
        BackupImageView r93 = new BackupImageView(r8);
        this.backupImageView = r93;
        r93.getImageReceiver().setCrossfadeWithOldImage(true);
        this.backupImageView.getImageReceiver().setAllowStartLottieAnimation(false);
        this.backupImageView.getImageReceiver().setAutoRepeat(0);
        if (r11 != 0) goto L5;
    L10:
        addView(this.backupImageView, LayoutHelper.createFrame(28, 28.0f, 81, 0.0f, 0.0f, 0.0f, 12.0f));
    L11:
        this.outlineBackgroundPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.outlineBackgroundPaint.setStyle(Paint.Style.STROKE);
        this.outlineBackgroundPaint.setColor(551805923);
        return;
    L5:
        if (r11 == 3) goto L10;
        if (r11 == 2) goto L10;
        addView(this.backupImageView, LayoutHelper.createFrame(36, 36.0f, 81, 0.0f, 0.0f, 0.0f, 12.0f));
        goto L11
    }

    private void fillOutBubblePaint(Paint r10, List<Integer> r11) {
        if (r11.size() <= 1) goto L10;
        int[] r6 = new int[r11.size()];
        int r0 = 0;
    L6:
        if (r0 == r11.size()) goto L8;
        r6[r0] = r11.get(r0).intValue() | (-16777216);
        r0 = r0 + 1;
        goto L6
    L8:
        float r3 = this.INNER_RECT_SPACE + AndroidUtilities.dp(8.0f);
        r10.setShader(new LinearGradient(0.0f, r3, 0.0f, r3 + this.BUBBLE_HEIGHT, r6, null, Shader.TileMode.CLAMP));
        return;
    L10:
        r10.setShader(null);
    }

    private StaticLayout getNoThemeStaticLayout() {
        StaticLayout r0 = this.textLayout;
        if (r0 == null) goto L5;
        return r0;
    L5:
        TextPaint r02 = new TextPaint(129);
        this.noThemeTextPaint = r02;
        r02.setColor(getThemedColor(Theme.key_chat_emojiPanelTrendingDescription));
        this.noThemeTextPaint.setTextSize(AndroidUtilities.dp(noThemeStringTextSize()));
        this.noThemeTextPaint.setTypeface(AndroidUtilities.bold());
        int r03 = AndroidUtilities.dp(52.0f);
        int r1 = this.currentType;
        if (r1 != 3) goto L8;
    L11:
        r03 = AndroidUtilities.dp(77.0f);
    L10:
        int r3 = r03;
        StaticLayout r04 = StaticLayoutEx.createStaticLayout2(noThemeString(), this.noThemeTextPaint, r3, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, true, TextUtils.TruncateAt.END, r3, 3);
        this.textLayout = r04;
        return r04;
    L8:
        if (r1 != 4) goto L10;
        goto L10
    }

    private Drawable getPreviewDrawable(EmojiThemes.ThemeItem r10) {
        Drawable r1 = null;
        r1 = null;
        if (this.chatThemeItem != null) goto L5;
        return null;
    L5:
        int r3 = r10.patternBgColor;
        int r4 = r10.patternBgGradientColor1;
        int r5 = r10.patternBgGradientColor2;
        int r6 = r10.patternBgGradientColor3;
        int r7 = r10.patternBgRotation;
        if (r10.themeInfo.getAccent(false) == null) goto L11;
        if (r4 == 0) goto L10;
        MotionBackgroundDrawable r2 = new MotionBackgroundDrawable(r3, r4, r5, r6, r7, true);
        this.patternColor = r2.getPatternColor();
    L9:
        r1 = r2;
    L30:
        this.chatThemeItem.previewDrawable = r1;
        return r1;
    L10:
        r2 = new MotionBackgroundDrawable(r3, r3, r3, r3, r7, true);
        this.patternColor = -16777216;
        goto L9
    L11:
        if (r3 == 0) goto L14;
        if (r4 == 0) goto L14;
        r2 = new MotionBackgroundDrawable(r3, r4, r5, r6, r7, true);
    L14:
        if (r3 == 0) goto L16;
        r1 = new ColorDrawable(r3);
        goto L30
    L16:
        Theme.ThemeInfo r0 = r10.themeInfo;
        if (r0 != null) goto L19;
    L25:
        EmojiThemes r102 = this.chatThemeItem.chatTheme;
        if (r102 != null) goto L28;
    L29:
        r2 = new MotionBackgroundDrawable(-2368069, -9722489, -2762611, -7817084, true);
        goto L9
    L28:
        if (r102.isAnyStub() == true) goto L30;
    L19:
        if (r0.previewWallpaperOffset <= 0) goto L21;
    L22:
        float r02 = AndroidUtilities.dp(112.0f);
        float r22 = AndroidUtilities.dp(134.0f);
        Theme.ThemeInfo r103 = r10.themeInfo;
        Bitmap r104 = AndroidUtilities.getScaledBitmap(r02, r22, r103.pathToWallpaper, r103.pathToFile, r103.previewWallpaperOffset);
        if (r104 == null) goto L30;
        BitmapDrawable r12 = new BitmapDrawable(r104);
        r12.setFilterBitmap(true);
        r1 = r12;
        goto L30
    L21:
        if (r0.pathToWallpaper == null) goto L25;
        goto L22
    }

    private Drawable getPreviewDrawable(ITheme r12, int r13) {
        if (this.chatThemeItem != null) goto L6;
        return null;
    L6:
        if (r13 < 0) goto L8;
        TLRPC.WallPaperSettings r122 = r12.getThemeSettings(r13).wallpaper.settings;
        int r132 = r122.background_color;
        int r0 = r122.second_background_color;
        int r1 = r122.third_background_color;
        int r4 = r122.fourth_background_color;
        int r2 = r0;
        int r3 = r1;
        int r14 = r132;
    L9:
        if (r2 == 0) goto L11;
        MotionBackgroundDrawable r02 = new MotionBackgroundDrawable(r14, r2, r3, r4, true);
        this.patternColor = r02.getPatternColor();
    L12:
        this.chatThemeItem.previewDrawable = r02;
        return r02;
    L11:
        MotionBackgroundDrawable r5 = new MotionBackgroundDrawable(r14, r14, r14, r14, true);
        this.patternColor = -16777216;
        r02 = r5;
        goto L12
    L8:
        r14 = 0;
        r2 = 0;
        r3 = 0;
        r4 = 0;
        goto L9
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$playEmojiAnimation$5() {
        this.animationCancelRunnable = null;
        this.backupImageView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
    }

    private /* synthetic */ void lambda$setItem$0(long r3, ChatThemeBottomSheet.ChatThemeItem r5, int r6, Pair r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        if (((Long) r7.first).longValue() != r3) goto L15;
        Drawable r32 = r5.previewDrawable;
        if ((r32 instanceof MotionBackgroundDrawable) == false) goto L12;
        MotionBackgroundDrawable r33 = (MotionBackgroundDrawable) r32;
        if (r6 < 0) goto L10;
        int r4 = 100;
    L11:
        r33.setPatternBitmap(r4, prescaleBitmap((Bitmap) r7.second), true);
        r33.setPatternColorFilter(this.patternColor);
        goto L12
    L10:
        r4 = -100;
    L12:
        invalidate();
        return;
    }

    private /* synthetic */ void lambda$setItem$1(ChatThemeBottomSheet.ChatThemeItem r1, TLRPC.WallPaper r2, ImageReceiver r3, boolean r4, boolean r5, boolean r6) {
        ImageReceiver.BitmapHolder r32 = r3.getBitmapSafe();
        if (r4 == false) goto L19;
        if (r32 == null) goto L22;
        Bitmap r33 = r32.bitmap;
        if (r33 == null) goto L20;
        Drawable r12 = r1.previewDrawable;
        if ((r12 instanceof MotionBackgroundDrawable) == false) goto L21;
        MotionBackgroundDrawable r13 = (MotionBackgroundDrawable) r12;
        TLRPC.WallPaperSettings r22 = r2.settings;
        if (r22 != null) goto L13;
    L16:
        int r23 = 100;
    L17:
        r13.setPatternBitmap(r23, prescaleBitmap(r33), true);
        r13.setPatternColorFilter(this.patternColor);
        invalidate();
        return;
    L13:
        if (r22.intensity >= 0) goto L16;
        r23 = -100;
        goto L17
    L21:
        return;
    L20:
        return;
    L22:
        return;
    }

    private /* synthetic */ void lambda$setItem$2(ChatThemeBottomSheet.ChatThemeItem r2, int r3, Bitmap r4) {
        Drawable r22 = r2.previewDrawable;
        if ((r22 instanceof MotionBackgroundDrawable) == false) goto L6;
        MotionBackgroundDrawable r23 = (MotionBackgroundDrawable) r22;
        r23.setPatternBitmap(r3, prescaleBitmap(r4), true);
        r23.setPatternColorFilter(this.patternColor);
        invalidate();
        return;
    }

    private /* synthetic */ void lambda$setItem$3(ChatThemeBottomSheet.ChatThemeItem r6, int r7) {
        AndroidUtilities.runOnUIThread(new ThemeSmallPreviewView$$ExternalSyntheticLambda5(this, r6, r7, SvgHelper.getBitmap(R.raw.default_pattern, AndroidUtilities.dp(120.0f), AndroidUtilities.dp(140.0f), -16777216, AndroidUtilities.density)));
    }

    private /* synthetic */ void lambda$setSelected$4(ValueAnimator r1) {
        this.selectionProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private Bitmap prescaleBitmap(Bitmap r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        float r2 = Math.max(AndroidUtilities.dp(120.0f) / r3.getWidth(), AndroidUtilities.dp(140.0f) / r3.getHeight());
        if (r3.getWidth() > 0) goto L8;
    L18:
        return r3;
    L8:
        if (r3.getHeight() <= 0) goto L18;
        if (Math.abs(r2 - 1.0f) < 0.0125f) goto L18;
        int r0 = (int) (r3.getWidth() * r2);
        int r22 = (int) (r3.getHeight() * r2);
        if (r22 <= 0) goto L18;
        if (r0 <= 0) goto L18;
        return Bitmap.createScaledBitmap(r3, r0, r22, true);
    }

    public void cancelAnimation() {
        Runnable r0 = this.animationCancelRunnable;
        if (r0 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.animationCancelRunnable.run();
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.emojiLoaded) goto L6;
        invalidate();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r5) {
        if (this.chatThemeItem != null) goto L7;
        super.dispatchDraw(r5);
        return;
    L7:
        if (this.chatBackgroundDrawable == null) goto L10;
        r5.save();
        r5.clipPath(this.clipPath);
        this.chatBackgroundDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.chatBackgroundDrawable.draw(r5);
        r5.restore();
    L10:
        if (this.changeThemeProgress == 1.0f) goto L14;
        ThemeDrawable r0 = this.animateOutThemeDrawable;
        if (r0 == null) goto L14;
        r0.drawBackground(r5, 1.0f);
    L14:
        float r02 = this.changeThemeProgress;
        if (r02 == 0.0f) goto L18;
        this.themeDrawable.drawBackground(r5, r02);
    L18:
        if (this.changeThemeProgress == 1.0f) goto L22;
        ThemeDrawable r03 = this.animateOutThemeDrawable;
        if (r03 == null) goto L22;
        r03.draw(r5, 1.0f);
    L22:
        float r04 = this.changeThemeProgress;
        if (r04 == 0.0f) goto L25;
        this.themeDrawable.draw(r5, r04);
    L25:
        float r05 = this.changeThemeProgress;
        if (r05 == 1.0f) goto L31;
        float r06 = r05 + 0.10666667f;
        this.changeThemeProgress = r06;
        if (r06 < 1.0f) goto L30;
        this.changeThemeProgress = 1.0f;
    L30:
        invalidate();
    L31:
        super.dispatchDraw(r5);
    }

    public String noThemeString() {
        return LocaleController.getString(R.string.ChatNoTheme);
    }

    public int noThemeStringTextSize() {
        return 14;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        this.attached = true;
        ChatBackgroundDrawable r0 = this.chatBackgroundDrawable;
        if (r0 == null) goto L5;
        r0.onAttachedToWindow(this);
    L5:
        this.avatarImageReceiver.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        this.attached = false;
        ChatBackgroundDrawable r0 = this.chatBackgroundDrawable;
        if (r0 == null) goto L5;
        r0.onDetachedFromWindow(this);
    L5:
        this.avatarImageReceiver.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setEnabled(true);
        r2.setSelected(this.isSelected);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        int r0 = this.currentType;
        if (r0 != 1) goto L5;
    L14:
        int r42 = View.MeasureSpec.getSize(r4);
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(r42, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (r42 * 1.2f), TLObject.FLAG_30));
    L15:
        this.backupImageView.setPivotY(r4.getMeasuredHeight());
        this.backupImageView.setPivotX(r3.getMeasuredWidth() / 2.0f);
        return;
    L5:
        if (r0 == 4) goto L14;
        if (r0 != 0) goto L9;
        float r43 = 77.0f;
    L10:
        int r44 = AndroidUtilities.dp(r43);
        int r52 = View.MeasureSpec.getSize(r5);
        if (r52 != 0) goto L13;
        r52 = (int) (r44 * 1.35f);
    L13:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(r44, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r52, TLObject.FLAG_30));
        goto L15
    L9:
        r43 = 83.0f;
        goto L10
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        if (r1 != r3) goto L6;
        if (r2 != r4) goto L6;
        return;
    L6:
        RectF r32 = this.rectF;
        float r42 = this.INNER_RECT_SPACE;
        r32.set(r42, r42, r1 - r42, r2 - r42);
        this.clipPath.reset();
        Path r12 = this.clipPath;
        RectF r22 = this.rectF;
        float r0 = this.INNER_RADIUS;
        r12.addRoundRect(r22, r0, r0, Path.Direction.CW);
    }

    public void playEmojiAnimation() {
        if (this.backupImageView.getImageReceiver().getLottieAnimation() == null) goto L9;
        AndroidUtilities.cancelRunOnUIThread(this.animationCancelRunnable);
        this.backupImageView.setVisibility(0);
        if (this.backupImageView.getImageReceiver().getLottieAnimation().isRunning == true) goto L7;
        this.backupImageView.getImageReceiver().getLottieAnimation().setCurrentFrame(0, true);
        this.backupImageView.getImageReceiver().getLottieAnimation().start();
    L7:
        this.backupImageView.animate().scaleX(2.0f).scaleY(2.0f).setDuration(300).setInterpolator(AndroidUtilities.overshootInterpolator).start();
        ThemeSmallPreviewView$$ExternalSyntheticLambda0 r0 = new ThemeSmallPreviewView$$ExternalSyntheticLambda0(this);
        this.animationCancelRunnable = r0;
        AndroidUtilities.runOnUIThread(r0, 2500);
        return;
    }

    @Override // android.view.View
    public void setBackgroundColor(int r2) {
        this.backgroundFillPaint.setColor(getThemedColor(Theme.key_dialogBackgroundGray));
        TextPaint r22 = this.noThemeTextPaint;
        if (r22 == null) goto L5;
        r22.setColor(getThemedColor(Theme.key_chat_emojiPanelTrendingDescription));
    L5:
        invalidate();
    }

    public void setFallbackWallpaper(TLRPC.WallPaper r2) {
        if (this.fallbackWallpaper == r2) goto L12;
        this.fallbackWallpaper = r2;
        ChatThemeBottomSheet.ChatThemeItem r22 = this.chatThemeItem;
        if (r22 == null) goto L13;
        EmojiThemes r0 = r22.chatTheme;
        if (r0 != null) goto L9;
    L10:
        this.chatThemeItem = null;
        setItem(r22, false);
        return;
    L9:
        if (r0.wallpaper == null) goto L10;
        return;
    L13:
        return;
    }

    public void setItem(ChatThemeBottomSheet.ChatThemeItem r16, long r17, boolean r19) {
        if (this.chatThemeItem == r16) goto L5;
        boolean r0 = true;
    L6:
        int r3 = this.lastThemeIndex;
        int r5 = r16.themeIndex;
        if (r3 == r5) goto L9;
        boolean r32 = true;
    L10:
        this.lastThemeIndex = r5;
        this.chatThemeItem = r16;
        this.hasAnimatedEmoji = false;
        TLRPC.Document r52 = r16.chatTheme.getEmojiAnimatedSticker();
        long r7 = r16.chatTheme.getBusyByUserId();
        this.themeUserByUserId = r7;
        if (r17 != r7) goto L14;
        this.themeUserByUserId = 0;
    L14:
        if (this.themeUserByUserId != 0) goto L16;
        this.avatarImageReceiver.clearImage();
    L20:
        Theme.ThemeAccent r10 = null;
        if (r0 == false) goto L26;
        Runnable r11 = this.animationCancelRunnable;
        if (r11 == null) goto L25;
        AndroidUtilities.cancelRunOnUIThread(r11);
        this.animationCancelRunnable = null;
    L25:
        this.backupImageView.animate().cancel();
        this.backupImageView.setScaleX(1.0f);
        this.backupImageView.setScaleY(1.0f);
    L26:
        if (r0 == false) goto L49;
        if (r52 == null) goto L29;
        Drawable r112 = DocumentObject.getSvgThumb(r52, Theme.key_emptyListPlaceholder, 0.2f);
    L30:
        if (r112 != null) goto L32;
        Emoji.preloadEmoji(r16.chatTheme.getEmoticon());
        r112 = Emoji.getEmojiDrawable(r16.chatTheme.getEmoticon());
    L32:
        this.backupImageView.setImage(ImageLocation.getForDocument(r52), "50_50", r112, null);
        TLRPC.WallPaper r53 = r16.chatTheme.wallpaper;
        if (r53 != null) goto L35;
        r53 = this.fallbackWallpaper;
    L35:
        boolean r113 = this.attached;
        if (r53 == null) goto L44;
        if (r113 == false) goto L41;
        ChatBackgroundDrawable r114 = this.chatBackgroundDrawable;
        if (r114 == null) goto L41;
        r114.onDetachedFromWindow(this);
    L41:
        ChatBackgroundDrawable r115 = new ChatBackgroundDrawable(r53, false, true);
        this.chatBackgroundDrawable = r115;
        r115.setParent(this);
        if (this.attached == false) goto L49;
        this.chatBackgroundDrawable.onAttachedToWindow(this);
        goto L49
    L44:
        if (r113 == false) goto L48;
        ChatBackgroundDrawable r2 = this.chatBackgroundDrawable;
        if (r2 == null) goto L48;
        r2.onDetachedFromWindow(this);
    L48:
        this.chatBackgroundDrawable = null;
        goto L49
    L29:
        r112 = null;
    L49:
        BackupImageView r22 = this.backupImageView;
        if (r16.chatTheme.isAnyStub() == true) goto L52;
    L54:
        int r54 = 0;
    L55:
        r22.setVisibility(r54);
        if (r0 == true) goto L58;
        if (r32 == true) goto L58;
    L86:
        if (r19 == true) goto L90;
        this.backupImageView.animate().cancel();
        this.backupImageView.setScaleX(1.0f);
        this.backupImageView.setScaleY(1.0f);
        AndroidUtilities.cancelRunOnUIThread(this.animationCancelRunnable);
        if (this.backupImageView.getImageReceiver().getLottieAnimation() == null) goto L90;
        this.backupImageView.getImageReceiver().getLottieAnimation().stop();
        this.backupImageView.getImageReceiver().getLottieAnimation().setCurrentFrame(0, false);
    L90:
        EmojiThemes r02 = this.chatThemeItem.chatTheme;
        if (r02 != null) goto L93;
    L97:
        setContentDescription(LocaleController.getString(R.string.ChatNoTheme));
        return;
    L93:
        if (r02.isAnyStub() == true) goto L97;
        setContentDescription(this.chatThemeItem.chatTheme.getEmoticonOrSlug());
        return;
    L58:
        if (r19 == false) goto L60;
        this.changeThemeProgress = 0.0f;
        this.animateOutThemeDrawable = this.themeDrawable;
        this.themeDrawable = new ThemeDrawable(this);
        invalidate();
    L61:
        updatePreviewBackground(this.themeDrawable);
        long r23 = r16.chatTheme.getThemeId(this.lastThemeIndex);
        EmojiThemes r55 = r16.chatTheme;
        if (r23 == 0) goto L66;
        TLRPC.WallPaper r03 = r55.getWallpaper(this.lastThemeIndex);
        if (r03 == null) goto L86;
        r16.chatTheme.loadWallpaperThumb(this.lastThemeIndex, new ThemeSmallPreviewView$$ExternalSyntheticLambda2(this, r23, r16, r03.settings.intensity));
        goto L86
    L66:
        SparseArray<Theme.ThemeAccent> r04 = r55.getThemeInfo(this.lastThemeIndex).themeAccentsMap;
        if (r04 == null) goto L69;
        r10 = r04.get(r16.chatTheme.getAccentId(this.lastThemeIndex));
    L69:
        if (r10 == null) goto L79;
        TLRPC.TL_theme r05 = r10.info;
        if (r05 == null) goto L79;
        if (r05.settings.size() <= 0) goto L79;
        TLRPC.WallPaper r06 = r10.info.settings.get(0).wallpaper;
        if (r06 == null) goto L86;
        TLRPC.Document r24 = r06.document;
        if (r24 == null) goto L86;
        ImageLocation r9 = ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r24.thumbs, 120), r24);
        ImageReceiver r8 = new ImageReceiver();
        r8.setAllowLoadingOnAttachedOnly(false);
        r8.setImage(r9, "120_140", null, null, null, 1);
        r8.setDelegate(new ThemeSmallPreviewView$$ExternalSyntheticLambda3(this, r16, r06));
        ImageLoader.getInstance().loadImageForImageReceiver(r8);
    L79:
        if (r10 == null) goto L86;
        if (r10.info != null) goto L86;
        int r07 = (int) (r10.patternIntensity * 100.0f);
        Drawable r25 = r16.previewDrawable;
        if ((r25 instanceof MotionBackgroundDrawable) == false) goto L85;
        ((MotionBackgroundDrawable) r25).setPatternBitmap(r07);
    L85:
        ChatThemeController.chatThemeQueue.postRunnable(new ThemeSmallPreviewView$$ExternalSyntheticLambda4(this, r16, r07));
        goto L86
    L60:
        this.changeThemeProgress = 1.0f;
        goto L61
    L52:
        if (this.fallbackWallpaper == null) goto L54;
        r54 = 8;
        goto L55
    L16:
        if (this.avatarDrawable != null) goto L18;
        this.avatarDrawable = new AvatarDrawable();
    L18:
        TLObject r72 = MessagesController.getInstance(this.currentAccount).getUserOrChat(this.themeUserByUserId);
        this.avatarDrawable.setInfo(this.currentAccount, r72);
        this.avatarImageReceiver.setForUserOrChat(r72, this.avatarDrawable);
        goto L20
    L9:
        r32 = false;
        goto L10
    L5:
        r0 = false;
        goto L6
    }

    public void setItem(ChatThemeBottomSheet.ChatThemeItem r3, boolean r4) {
        setItem(r3, 0, r4);
    }

    public void setSelected(boolean r4, boolean r5) {
        float r0 = 0.0f;
        if (r5 == true) goto L13;
        ValueAnimator r52 = this.strokeAlphaAnimator;
        if (r52 == null) goto L7;
        r52.cancel();
    L7:
        this.isSelected = r4;
        if (r4 == false) goto L10;
        r0 = 1.0f;
    L10:
        this.selectionProgress = r0;
        invalidate();
        return;
    L13:
        if (this.isSelected == r4) goto L20;
        float r53 = this.selectionProgress;
        ValueAnimator r2 = this.strokeAlphaAnimator;
        if (r2 == null) goto L17;
        r2.cancel();
    L17:
        if (r4 == false) goto L19;
        r0 = 1.0f;
    L19:
        ValueAnimator r54 = ValueAnimator.ofFloat(new float[]{r53, r0});
        this.strokeAlphaAnimator = r54;
        r54.addUpdateListener(new ThemeSmallPreviewView$$ExternalSyntheticLambda1(this));
        this.strokeAlphaAnimator.addListener(new AnonymousClass1(this, r4));
        this.strokeAlphaAnimator.setDuration(250);
        this.strokeAlphaAnimator.start();
    L20:
        this.isSelected = r4;
    }

    public void updatePreviewBackground(ThemeDrawable r7) {
        ChatThemeBottomSheet.ChatThemeItem r0 = this.chatThemeItem;
        if (r0 == null) goto L22;
        EmojiThemes r1 = r0.chatTheme;
        if (r1 == null) goto L23;
        EmojiThemes.ThemeItem r02 = r1.getThemeItem(r0.themeIndex);
        int r12 = r02.inBubbleColor;
        if (this.themeUserByUserId == 0) goto L10;
        r12 = r02.patternBgColor;
    L10:
        ThemeDrawable.m13317$$Nest$fgetinBubblePaint(r7).setColor(r12);
        int r13 = r02.outBubbleColor;
        ThemeDrawable.m13318$$Nest$fgetoutBubblePaintSecond(r7).setColor(r13);
        if (this.chatThemeItem.chatTheme.isAnyStub() == false) goto L13;
        int r03 = getThemedColor(Theme.key_featuredStickers_addButton);
    L14:
        int r14 = ThemeDrawable.m13319$$Nest$fgetstrokePaint(r7).getAlpha();
        ThemeDrawable.m13319$$Nest$fgetstrokePaint(r7).setColor(r03);
        ThemeDrawable.m13319$$Nest$fgetstrokePaint(r7).setAlpha(r14);
        ChatThemeBottomSheet.ChatThemeItem r04 = this.chatThemeItem;
        ITheme r05 = r04.chatTheme.getITheme(r04.themeIndex);
        if (r05 != null) goto L17;
    L19:
        ChatThemeBottomSheet.ChatThemeItem r06 = this.chatThemeItem;
        getPreviewDrawable(r06.chatTheme.getThemeItem(r06.themeIndex));
    L20:
        r7.previewDrawable = this.chatThemeItem.previewDrawable;
        invalidate();
        return;
    L17:
        if (r05.getThemeId() == 0) goto L19;
        ChatThemeBottomSheet.ChatThemeItem r15 = this.chatThemeItem;
        int r16 = r15.chatTheme.getSettingsIndex(r15.themeIndex);
        TLRPC.ThemeSettings r2 = r05.getThemeSettings(r16);
        fillOutBubblePaint(ThemeDrawable.m13318$$Nest$fgetoutBubblePaintSecond(r7), r2.message_colors);
        ThemeDrawable.m13318$$Nest$fgetoutBubblePaintSecond(r7).setAlpha(255);
        getPreviewDrawable(r05, r16);
        goto L20
    L13:
        r03 = r02.outLineColor;
        goto L14
    L23:
        return;
    }
}
