package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BlobDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RadialProgressView;

/* loaded from: classes6.dex */
public class GroupCallUserCell extends FrameLayout {
    private AccountInstance accountInstance;
    private AnimatorSet animatorSet;
    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImageView;
    private RadialProgressView avatarProgressView;
    private AvatarWavesDrawable avatarWavesDrawable;
    private Runnable checkRaiseRunnable;
    private ChatObject.Call currentCall;
    private TLRPC.Chat currentChat;
    private boolean currentIconGray;
    private int currentStatus;
    private TLRPC.User currentUser;
    private Paint dividerPaint;
    private SimpleTextView fullAboutTextView;
    private int grayIconColor;
    private boolean hasAvatar;
    private boolean isSpeaking;
    private int lastMuteColor;
    private boolean lastMuted;
    private boolean lastRaisedHand;
    public final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable leftDrawable;
    private RLottieImageView muteButton;
    private RLottieDrawable muteDrawable;
    private SimpleTextView nameTextView;
    private boolean needDivider;
    private TLRPC.GroupCallParticipant participant;
    private Drawable premiumDrawable;
    private float progressToAvatarPreview;
    private Runnable raiseHandCallback;
    public final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable rightDrawable;
    private long selfId;
    private Runnable shakeHandCallback;
    private RLottieDrawable shakeHandDrawable;
    private Drawable speakingDrawable;
    private SimpleTextView[] statusTextView;
    private Runnable updateRunnable;
    private boolean updateRunnableScheduled;
    private Runnable updateVoiceRunnable;
    private boolean updateVoiceRunnableScheduled;
    private Drawable verifiedDrawable;

    /* renamed from: org.telegram.ui.Cells.GroupCallUserCell$1, reason: invalid class name */
    public class AnonymousClass1 extends RadialProgressView {
        private Paint paint;
        final /* synthetic */ GroupCallUserCell this$0;

        public AnonymousClass1(GroupCallUserCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            Paint r12 = new Paint(1);
            this.paint = r12;
            r12.setColor(1426063360);
        }

        @Override // org.telegram.ui.Components.RadialProgressView, android.view.View
        public void onDraw(Canvas r5) {
            if (GroupCallUserCell.m6946$$Nest$fgetavatarImageView(this.this$0).getImageReceiver().hasNotThumb() == true) goto L5;
        L7:
            GroupCallUserCell.m6947$$Nest$fgetavatarProgressView(this.this$0).setProgressColor(ColorUtils.setAlphaComponent(-1, (int) ((GroupCallUserCell.m6946$$Nest$fgetavatarImageView(this.this$0).getImageReceiver().getCurrentAlpha() * 255.0f) * GroupCallUserCell.m6946$$Nest$fgetavatarImageView(this.this$0).getAlpha())));
            super.onDraw(r5);
            return;
        L5:
            if (GroupCallUserCell.m6946$$Nest$fgetavatarImageView(this.this$0).getAlpha() <= 0.0f) goto L7;
            this.paint.setAlpha((int) ((GroupCallUserCell.m6946$$Nest$fgetavatarImageView(this.this$0).getImageReceiver().getCurrentAlpha() * 85.0f) * GroupCallUserCell.m6946$$Nest$fgetavatarImageView(this.this$0).getAlpha()));
            r5.drawCircle(getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f, getMeasuredWidth() / 2.0f, this.paint);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Cells.GroupCallUserCell$2, reason: invalid class name */
    public class AnonymousClass2 extends SimpleTextView {
        float originalAlpha;
        final /* synthetic */ GroupCallUserCell this$0;
        final /* synthetic */ int val$num;

        public AnonymousClass2(GroupCallUserCell r1, Context r2, int r3) {
            this.this$0 = r1;
            this.val$num = r3;
            super(r2);
        }

        @Override // android.view.View
        public float getAlpha() {
            return this.originalAlpha;
        }

        @Override // android.view.View
        public void setAlpha(float r5) {
            this.originalAlpha = r5;
            int r0 = this.val$num;
            GroupCallUserCell r1 = this.this$0;
            if (r0 != 4) goto L16;
            float r02 = GroupCallUserCell.m6949$$Nest$fgetstatusTextView(r1)[4].getFullAlpha();
            if (this.this$0.isSelfUser() == false) goto L11;
            if (GroupCallUserCell.m6948$$Nest$fgetprogressToAvatarPreview(this.this$0) <= 0.0f) goto L11;
            super.setAlpha(1.0f - GroupCallUserCell.m6948$$Nest$fgetprogressToAvatarPreview(this.this$0));
            return;
        L11:
            if (r02 <= 0.0f) goto L14;
            super.setAlpha(Math.max(r5, r02));
            return;
        L14:
            super.setAlpha(r5);
            return;
        L16:
            super.setAlpha(r5 * (1.0f - GroupCallUserCell.m6949$$Nest$fgetstatusTextView(r1)[4].getFullAlpha()));
        }

        @Override // org.telegram.ui.ActionBar.SimpleTextView
        public void setFullAlpha(float r3) {
            super.setFullAlpha(r3);
            int r32 = 0;
        L4:
            if (r32 >= GroupCallUserCell.m6949$$Nest$fgetstatusTextView(this.this$0).length) goto L6;
            GroupCallUserCell.m6949$$Nest$fgetstatusTextView(this.this$0)[r32].setAlpha(GroupCallUserCell.m6949$$Nest$fgetstatusTextView(this.this$0)[r32].getAlpha());
            r32 = r32 + 1;
            goto L4
        }

        @Override // android.view.View
        public void setTranslationY(float r3) {
            if (this.val$num == 4) goto L5;
        L7:
            super.setTranslationY(r3);
            return;
        L5:
            if (getFullAlpha() <= 0.0f) goto L7;
            r3 = 0.0f;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Cells.GroupCallUserCell$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatedEmojiDrawable.WrapSizeDrawable {
        final /* synthetic */ GroupCallUserCell this$0;

        public AnonymousClass3(GroupCallUserCell r1, Drawable r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.AnimatedEmojiDrawable.WrapSizeDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas r3) {
            r3.save();
            r3.translate(AndroidUtilities.dp(-2.0f), AndroidUtilities.dp(0.0f));
            super.draw(r3);
            r3.restore();
        }
    }

    /* renamed from: org.telegram.ui.Cells.GroupCallUserCell$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallUserCell this$0;
        final /* synthetic */ int val$newStatus;

        public AnonymousClass4(GroupCallUserCell r1, int r2) {
            this.this$0 = r1;
            this.val$newStatus = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.this$0.isSelfUser() == true) goto L5;
            GroupCallUserCell.m6951$$Nest$mapplyStatus(this.this$0, this.val$newStatus);
        L5:
            GroupCallUserCell.m6950$$Nest$fputanimatorSet(this.this$0, null);
        }
    }

    public static class AvatarWavesDrawable {
        float amplitude;
        float animateAmplitudeDiff;
        float animateToAmplitude;
        private BlobDrawable blobDrawable;
        private BlobDrawable blobDrawable2;
        private boolean hasCustomColor;
        boolean invalidateColor;
        private int isMuted;
        private float progressToMuted;
        boolean showWaves;
        float wavesEnter;

        public AvatarWavesDrawable(int r3, int r4) {
            this.wavesEnter = 0.0f;
            this.progressToMuted = 0.0f;
            this.invalidateColor = true;
            this.blobDrawable = new BlobDrawable(6);
            BlobDrawable r0 = new BlobDrawable(8);
            this.blobDrawable2 = r0;
            BlobDrawable r1 = this.blobDrawable;
            float r32 = r3;
            r1.minRadius = r32;
            float r42 = r4;
            r1.maxRadius = r42;
            r0.minRadius = r32;
            r0.maxRadius = r42;
            r1.generateBlob();
            this.blobDrawable2.generateBlob();
            Paint r33 = this.blobDrawable.paint;
            int r43 = Theme.key_voipgroup_speakingText;
            r33.setColor(ColorUtils.setAlphaComponent(Theme.getColor(r43), 38));
            this.blobDrawable2.paint.setColor(ColorUtils.setAlphaComponent(Theme.getColor(r43), 38));
        }

        public void draw(Canvas r8, float r9, float r10, View r11) {
            if (LiteMode.isEnabled(512) == false) goto L40;
            float r1 = (this.amplitude * 0.4f) + 0.8f;
            if (this.showWaves == false) goto L8;
        L9:
            r8.save();
            float r12 = r1 * CubicBezierInterpolator.DEFAULT.getInterpolation(this.wavesEnter);
            r8.scale(r12, r12, r9, r10);
            if (this.hasCustomColor == true) goto L34;
            int r0 = this.isMuted;
            if (r0 == 1) goto L19;
            float r5 = this.progressToMuted;
            if (r5 == 1.0f) goto L19;
            float r52 = r5 + 0.10666667f;
            this.progressToMuted = r52;
            if (r52 <= 1.0f) goto L18;
            this.progressToMuted = 1.0f;
        L18:
            this.invalidateColor = true;
        L27:
            if (this.invalidateColor == false) goto L34;
            int r02 = Theme.getColor(Theme.key_voipgroup_speakingText);
            if (this.isMuted != 2) goto L32;
            int r3 = Theme.key_voipgroup_mutedByAdminIcon;
        L31:
            int r32 = Theme.getColor(r3);
            int r03 = ColorUtils.blendARGB(r02, r32, this.progressToMuted);
            this.blobDrawable.paint.setColor(ColorUtils.setAlphaComponent(r03, 38));
            goto L34
        L32:
            r3 = Theme.key_voipgroup_listeningText;
        L19:
            if (r0 != 1) goto L27;
            float r04 = this.progressToMuted;
            if (r04 == 0.0f) goto L27;
            float r05 = r04 - 0.10666667f;
            this.progressToMuted = r05;
            if (r05 >= 0.0f) goto L25;
            this.progressToMuted = 0.0f;
        L25:
            this.invalidateColor = true;
        L34:
            this.blobDrawable.update(this.amplitude, 1.0f);
            BlobDrawable r06 = this.blobDrawable;
            r06.draw(r9, r10, r8, r06.paint);
            this.blobDrawable2.update(this.amplitude, 1.0f);
            this.blobDrawable2.draw(r9, r10, r8, this.blobDrawable.paint);
            r8.restore();
        L36:
            if (this.wavesEnter == 0.0f) goto L39;
            r11.invalidate();
            return;
        L39:
            return;
        L8:
            if (this.wavesEnter == 0.0f) goto L36;
        }

        public float getAvatarScale() {
            float r1 = (this.amplitude * 0.2f) + 0.9f;
            float r2 = CubicBezierInterpolator.EASE_OUT.getInterpolation(this.wavesEnter);
            return (r1 * r2) + ((1.0f - r2) * 1.0f);
        }

        public void setAmplitude(double r3) {
            float r32 = ((float) r3) / 80.0f;
            float r0 = 0.0f;
            if (this.showWaves == true) goto L6;
            r32 = 0.0f;
        L6:
            if (r32 <= 1.0f) goto L9;
            r0 = 1.0f;
        L12:
            this.animateToAmplitude = r0;
            this.animateAmplitudeDiff = (r0 - this.amplitude) / 200.0f;
            return;
        L9:
            if (r32 < 0.0f) goto L12;
            r0 = r32;
            goto L12
        }

        public void setColor(int r2) {
            this.hasCustomColor = true;
            this.blobDrawable.paint.setColor(r2);
        }

        public void setMuted(int r2, boolean r3) {
            this.isMuted = r2;
            if (r3 == true) goto L8;
            if (r2 == 1) goto L6;
            float r22 = 1.0f;
        L7:
            this.progressToMuted = r22;
            goto L8
        L6:
            r22 = 0.0f;
        L8:
            this.invalidateColor = true;
        }

        public void setShowWaves(boolean r2, View r3) {
            if (this.showWaves == r2) goto L5;
            r3.invalidate();
        L5:
            this.showWaves = r2;
        }

        public void update() {
            float r0 = this.animateToAmplitude;
            float r1 = this.amplitude;
            if (r0 == r1) goto L12;
            float r2 = this.animateAmplitudeDiff;
            float r12 = r1 + (16.0f * r2);
            this.amplitude = r12;
            if (r2 <= 0.0f) goto L10;
            if (r12 <= r0) goto L12;
            this.amplitude = r0;
            goto L12
        L10:
            if (r12 >= r0) goto L12;
            this.amplitude = r0;
        L12:
            boolean r02 = this.showWaves;
            if (r02 == false) goto L20;
            float r22 = this.wavesEnter;
            if (r22 == 1.0f) goto L20;
            float r23 = r22 + 0.045714285f;
            this.wavesEnter = r23;
            if (r23 <= 1.0f) goto L27;
            this.wavesEnter = 1.0f;
            return;
        L27:
            return;
        L20:
            if (r02 == true) goto L28;
            float r03 = this.wavesEnter;
            if (r03 == 0.0f) goto L29;
            float r04 = r03 - 0.045714285f;
            this.wavesEnter = r04;
            if (r04 >= 0.0f) goto L30;
            this.wavesEnter = 0.0f;
            return;
        L30:
            return;
        L29:
            return;
        }
    }

    public static class VerifiedDrawable extends Drawable {
        private Drawable[] drawables;

        public VerifiedDrawable(Context r5) {
            Drawable[] r0 = new Drawable[2];
            this.drawables = r0;
            r0[0] = r5.getResources().getDrawable(R.drawable.verified_area).mutate();
            this.drawables[0].setColorFilter(new PorterDuffColorFilter(-9063442, PorterDuff.Mode.MULTIPLY));
            this.drawables[1] = r5.getResources().getDrawable(R.drawable.verified_check).mutate();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r4) {
            int r0 = 0;
        L3:
            Drawable[] r1 = this.drawables;
            if (r0 >= r1.length) goto L6;
            r1[r0].setBounds(getBounds());
            this.drawables[r0].draw(r4);
            r0 = r0 + 1;
            goto L3
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.drawables[0].getIntrinsicHeight();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.drawables[0].getIntrinsicWidth();
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r4) {
            int r0 = 0;
        L3:
            Drawable[] r1 = this.drawables;
            if (r0 >= r1.length) goto L6;
            r1[r0].setAlpha(r4);
            r0 = r0 + 1;
            goto L3
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    /* renamed from: $r8$lambda$F-mTN01MrpMQTI7LSVEDl-ezRJ0, reason: not valid java name */
    public static /* synthetic */ void m6943$r8$lambda$FmTN01MrpMQTI7LSVEDlezRJ0(GroupCallUserCell r0) {
        r0.lambda$new$1();
    }

    /* renamed from: $r8$lambda$KGgeNxya4m-px7A3hsBgizIcB8A, reason: not valid java name */
    public static /* synthetic */ void m6944$r8$lambda$KGgeNxya4mpx7A3hsBgizIcB8A(GroupCallUserCell r0, int r1, int r2, ValueAnimator r3) {
        r0.lambda$applyParticipantChanges$6(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$Q-un_e0ZmT1SSSb_BbQLME5_2fk, reason: not valid java name */
    public static /* synthetic */ void m6945$r8$lambda$Qun_e0ZmT1SSSb_BbQLME5_2fk(GroupCallUserCell r0, View r1) {
        r0.lambda$new$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$SUjg_TxmvNWgvAxWsUyQNILAfhU(GroupCallUserCell r0) {
        r0.lambda$new$2();
    }

    public static /* synthetic */ void $r8$lambda$aRpPDTzEiL1sGebkzi6oeqB587g(GroupCallUserCell r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$hoLcAWOylk3e__CIHHu9Msk3W2Q(GroupCallUserCell r0) {
        r0.lambda$new$3();
    }

    public static /* synthetic */ void $r8$lambda$t2PqefUIM7dnG9huRo4xGFc9eog(GroupCallUserCell r0) {
        r0.lambda$new$4();
    }

    /* renamed from: -$$Nest$fgetavatarImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m6946$$Nest$fgetavatarImageView(GroupCallUserCell r0) {
        return r0.avatarImageView;
    }

    /* renamed from: -$$Nest$fgetavatarProgressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgressView m6947$$Nest$fgetavatarProgressView(GroupCallUserCell r0) {
        return r0.avatarProgressView;
    }

    /* renamed from: -$$Nest$fgetprogressToAvatarPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m6948$$Nest$fgetprogressToAvatarPreview(GroupCallUserCell r0) {
        return r0.progressToAvatarPreview;
    }

    /* renamed from: -$$Nest$fgetstatusTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SimpleTextView[] m6949$$Nest$fgetstatusTextView(GroupCallUserCell r0) {
        return r0.statusTextView;
    }

    /* renamed from: -$$Nest$fputanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6950$$Nest$fputanimatorSet(GroupCallUserCell r0, AnimatorSet r1) {
        r0.animatorSet = r1;
    }

    /* renamed from: -$$Nest$mapplyStatus, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6951$$Nest$mapplyStatus(GroupCallUserCell r0, int r1) {
        r0.applyStatus(r1);
    }

    public GroupCallUserCell(Context r26) {
        super(r26);
        int r2 = 5;
        this.statusTextView = new SimpleTextView[5];
        this.shakeHandCallback = new GroupCallUserCell$$ExternalSyntheticLambda1(this);
        this.raiseHandCallback = new GroupCallUserCell$$ExternalSyntheticLambda2(this);
        this.grayIconColor = Theme.key_voipgroup_mutedIcon;
        this.checkRaiseRunnable = new GroupCallUserCell$$ExternalSyntheticLambda3(this);
        this.updateRunnable = new GroupCallUserCell$$ExternalSyntheticLambda4(this);
        this.updateVoiceRunnable = new GroupCallUserCell$$ExternalSyntheticLambda5(this);
        Paint r3 = new Paint();
        this.dividerPaint = r3;
        r3.setColor(Theme.getColor(Theme.key_voipgroup_actionBar));
        this.avatarDrawable = new AvatarDrawable();
        setClipChildren(false);
        BackupImageView r4 = new BackupImageView(r26);
        this.avatarImageView = r4;
        r4.setRoundRadius(ExteraConfig.getAvatarCorners(46.0f));
        BackupImageView r42 = this.avatarImageView;
        boolean r5 = LocaleController.isRTL;
        if (r5 == false) goto L5;
        int r7 = 5;
    L6:
        int r10 = r7 | 48;
        if (r5 == false) goto L9;
        float r11 = 0.0f;
    L10:
        if (r5 == false) goto L12;
        float r13 = 11.0f;
    L13:
        addView(r42, LayoutHelper.createFrame(46, 46.0f, r10, r11, 6.0f, r13, 0.0f));
        AnonymousClass1 r43 = new AnonymousClass1(this, r26);
        this.avatarProgressView = r43;
        r43.setSize(AndroidUtilities.dp(26.0f));
        this.avatarProgressView.setProgressColor(-1);
        this.avatarProgressView.setNoProgress(false);
        RadialProgressView r44 = this.avatarProgressView;
        boolean r8 = LocaleController.isRTL;
        if (r8 == false) goto L16;
        int r9 = 5;
    L17:
        int r18 = r9 | 48;
        if (r8 == false) goto L20;
        float r19 = 0.0f;
    L21:
        if (r8 == false) goto L23;
        float r21 = 11.0f;
    L24:
        addView(r44, LayoutHelper.createFrame(46, 46.0f, r18, r19, 6.0f, r21, 0.0f));
        AndroidUtilities.updateViewVisibilityAnimated(this.avatarProgressView, false, 1.0f, false);
        SimpleTextView r45 = new SimpleTextView(r26);
        this.nameTextView = r45;
        r45.setTextColor(Theme.getColor(Theme.key_voipgroup_nameText));
        this.nameTextView.setTypeface(AndroidUtilities.bold());
        this.nameTextView.setTextSize(16);
        this.nameTextView.setDrawablePadding(AndroidUtilities.dp(6.0f));
        SimpleTextView r46 = this.nameTextView;
        if (LocaleController.isRTL == false) goto L27;
        int r82 = 5;
    L28:
        r46.setGravity(r82 | 48);
        SimpleTextView r47 = this.nameTextView;
        boolean r83 = LocaleController.isRTL;
        if (r83 == false) goto L31;
        int r92 = 5;
    L32:
        int r12 = r92 | 48;
        if (r83 == false) goto L35;
        float r132 = 54.0f;
    L36:
        if (r83 == false) goto L38;
        float r15 = 67.0f;
    L39:
        addView(r47, LayoutHelper.createFrame(-1, 20.0f, r12, r132, 10.0f, r15, 0.0f));
        this.leftDrawable = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this.nameTextView, AndroidUtilities.dp(20.0f), 9);
        this.rightDrawable = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this.nameTextView, AndroidUtilities.dp(20.0f), 9);
        Drawable r48 = r26.getResources().getDrawable(R.drawable.voice_volume_mini);
        this.speakingDrawable = r48;
        r48.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_voipgroup_speakingText), PorterDuff.Mode.MULTIPLY));
        int r49 = 0;
    L40:
        SimpleTextView[] r84 = this.statusTextView;
        if (r49 >= r84.length) goto L81;
        r84[r49] = new AnonymousClass2(this, r26, r49);
        this.statusTextView[r49].setTextSize(15);
        SimpleTextView r85 = this.statusTextView[r49];
        if (LocaleController.isRTL == false) goto L45;
        int r102 = 5;
    L46:
        r85.setGravity(r102 | 48);
        if (r49 != 4) goto L60;
        this.statusTextView[r49].setBuildFullLayout(true);
        this.statusTextView[r49].setTextColor(Theme.getColor(Theme.key_voipgroup_mutedIcon));
        SimpleTextView r86 = this.statusTextView[r49];
        boolean r103 = LocaleController.isRTL;
        if (r103 == false) goto L51;
        int r112 = 5;
    L52:
        int r20 = r112 | 48;
        if (r103 == false) goto L55;
        float r212 = 54.0f;
    L56:
        if (r103 == false) goto L58;
        float r23 = 67.0f;
    L59:
        addView(r86, LayoutHelper.createFrame(-1, -2.0f, r20, r212, 32.0f, r23, 0.0f));
    L80:
        r49 = r49 + 1;
        goto L40
    L58:
        r23 = 54.0f;
        goto L59
    L55:
        r212 = 67.0f;
        goto L56
    L51:
        r112 = 3;
        goto L52
    L60:
        if (r49 != 0) goto L62;
        this.statusTextView[r49].setTextColor(Theme.getColor(Theme.key_voipgroup_listeningText));
        this.statusTextView[r49].setText(LocaleController.getString(R.string.Listening));
    L68:
        SimpleTextView r87 = this.statusTextView[r49];
        boolean r104 = LocaleController.isRTL;
        if (r104 == false) goto L71;
        int r113 = 5;
    L72:
        int r202 = r113 | 48;
        if (r104 == false) goto L75;
        float r213 = 54.0f;
    L76:
        if (r104 == false) goto L78;
        float r232 = 67.0f;
    L79:
        addView(r87, LayoutHelper.createFrame(-1, 20.0f, r202, r213, 32.0f, r232, 0.0f));
        goto L80
    L78:
        r232 = 54.0f;
        goto L79
    L75:
        r213 = 67.0f;
        goto L76
    L71:
        r113 = 3;
        goto L72
    L62:
        if (r49 != 1) goto L64;
        this.statusTextView[r49].setTextColor(Theme.getColor(Theme.key_voipgroup_speakingText));
        this.statusTextView[r49].setText(LocaleController.getString(R.string.Speaking));
        this.statusTextView[r49].setDrawablePadding(AndroidUtilities.dp(2.0f));
        goto L68
    L64:
        if (r49 != 2) goto L66;
        this.statusTextView[r49].setTextColor(Theme.getColor(Theme.key_voipgroup_mutedByAdminIcon));
        this.statusTextView[r49].setText(LocaleController.getString(R.string.VoipGroupMutedForMe));
        goto L68
    L66:
        if (r49 != 3) goto L68;
        this.statusTextView[r49].setTextColor(Theme.getColor(Theme.key_voipgroup_listeningText));
        this.statusTextView[r49].setText(LocaleController.getString(R.string.WantsToSpeak));
        goto L68
    L45:
        r102 = 3;
        goto L46
    L81:
        SimpleTextView r410 = new SimpleTextView(r26);
        this.fullAboutTextView = r410;
        r410.setMaxLines(3);
        this.fullAboutTextView.setTextSize(15);
        this.fullAboutTextView.setTextColor(Theme.getColor(Theme.key_voipgroup_mutedIcon));
        this.fullAboutTextView.setVisibility(8);
        SimpleTextView r411 = this.fullAboutTextView;
        if (LocaleController.isRTL == false) goto L84;
        int r88 = 5;
    L85:
        addView(r411, LayoutHelper.createFrame(-1, 60.0f, r88 | 48, 14.0f, 32.0f, 14.0f, 0.0f));
        this.muteDrawable = new RLottieDrawable(R.raw.voice_outlined2, AndroidUtilities.dp(34.0f), AndroidUtilities.dp(32.0f), true, null);
        this.shakeHandDrawable = new RLottieDrawable(R.raw.hand_1, AndroidUtilities.dp(34.0f), AndroidUtilities.dp(32.0f), true, null);
        RLottieImageView r412 = new RLottieImageView(r26);
        this.muteButton = r412;
        r412.setScaleType(ImageView.ScaleType.CENTER);
        this.muteButton.setAnimation(this.muteDrawable);
        RippleDrawable r1 = (RippleDrawable) Theme.createSelectorDrawable(Theme.getColor(this.grayIconColor) & 620756991);
        Theme.setRippleDrawableForceSoftware(r1);
        this.muteButton.setBackground(r1);
        this.muteButton.setImportantForAccessibility(2);
        RLottieImageView r14 = this.muteButton;
        if (LocaleController.isRTL == false) goto L88;
        r2 = 3;
    L88:
        addView(r14, LayoutHelper.createFrame(48, -1.0f, r2 | 16, 6.0f, 0.0f, 6.0f, 0.0f));
        this.muteButton.setOnClickListener(new GroupCallUserCell$$ExternalSyntheticLambda6(this));
        this.avatarWavesDrawable = new AvatarWavesDrawable(AndroidUtilities.dp(26.0f), AndroidUtilities.dp(29.0f));
        setWillNotDraw(false);
        setFocusable(true);
        return;
    L84:
        r88 = 3;
        goto L85
    L38:
        r15 = 54.0f;
        goto L39
    L35:
        r132 = 67.0f;
        goto L36
    L31:
        r92 = 3;
        goto L32
    L27:
        r82 = 3;
        goto L28
    L23:
        r21 = 0.0f;
        goto L24
    L20:
        r19 = 11.0f;
        goto L21
    L16:
        r9 = 3;
        goto L17
    L12:
        r13 = 0.0f;
        goto L13
    L9:
        r11 = 11.0f;
        goto L10
    L5:
        r7 = 3;
        goto L6
    }

    private void applyParticipantChanges(boolean r21, boolean r22) {
        if (this.currentCall != null) goto L5;
        return;
    L5:
        RLottieImageView r2 = this.muteButton;
        boolean r6 = false;
        if (isSelfUser() == true) goto L8;
    L11:
        boolean r3 = true;
    L12:
        r2.setEnabled(r3);
        long r23 = SystemClock.elapsedRealtime();
        TLRPC.GroupCallParticipant r8 = this.participant;
        if ((r23 - r8.lastVoiceUpdateTime) >= 500) goto L15;
        boolean r24 = r8.hasVoiceDelayed;
    L16:
        if (r22 == true) goto L31;
        long r11 = SystemClock.uptimeMillis() - this.participant.lastSpeakTime;
        if (r11 >= 500) goto L20;
        boolean r32 = true;
    L22:
        if (this.isSpeaking == false) goto L25;
        if (r32 == false) goto L25;
        if (r24 == false) goto L31;
    L25:
        this.isSpeaking = r32;
        if (this.updateRunnableScheduled == false) goto L29;
        AndroidUtilities.cancelRunOnUIThread(this.updateRunnable);
        this.updateRunnableScheduled = false;
    L29:
        if (this.isSpeaking == false) goto L31;
        AndroidUtilities.runOnUIThread(this.updateRunnable, 500 - r11);
        this.updateRunnableScheduled = true;
        goto L31
    L20:
        r32 = false;
    L31:
        TLRPC.GroupCallParticipant r33 = this.currentCall.participants.get(MessageObject.getPeerId(this.participant.peer));
        if (r33 == null) goto L35;
        this.participant = r33;
    L35:
        if (this.participant.muted_by_you == true) goto L37;
    L39:
        boolean r34 = false;
    L41:
        if (isSelfUser() == false) goto L52;
        if (VoIPService.getSharedInstance() != null) goto L45;
    L50:
        boolean r82 = false;
    L58:
        if (r82 == false) goto L60;
        boolean r9 = this.participant.can_self_unmute;
    L60:
        boolean r92 = TextUtils.isEmpty(this.participant.about);
        this.currentIconGray = false;
        AndroidUtilities.cancelRunOnUIThread(this.checkRaiseRunnable);
        TLRPC.GroupCallParticipant r10 = this.participant;
        if (r10.muted == true) goto L63;
    L64:
        if (r34 == false) goto L100;
    L65:
        boolean r25 = r10.can_self_unmute;
        if (r25 == false) goto L74;
        if (r34 == true) goto L74;
        int r26 = Theme.getColor(this.grayIconColor);
        this.currentIconGray = true;
        if (r92 == true) goto L72;
        int r35 = 4;
    L73:
        int r4 = r35;
        boolean r36 = false;
    L109:
        if (isSelfUser() == true) goto L112;
        this.statusTextView[4].setTextColor(Theme.getColor(this.grayIconColor));
    L112:
        if (isSelfUser() == false) goto L135;
        if (r92 == true) goto L115;
    L121:
        if (r92 == false) goto L127;
        TLRPC.User r5 = this.currentUser;
        SimpleTextView[] r102 = this.statusTextView;
        if (r5 == null) goto L125;
        r102[4].setText(LocaleController.getString(R.string.TapToAddBio));
    L126:
        this.statusTextView[4].setTextColor(Theme.getColor(this.grayIconColor));
    L131:
        SimpleTextView r52 = this.fullAboutTextView;
        if (r92 == true) goto L134;
        r52.setText(AndroidUtilities.replaceNewLines(this.participant.about));
        this.fullAboutTextView.setTextColor(Theme.getColor(Theme.key_voipgroup_mutedIcon));
    L139:
        AnimatorSet r53 = this.animatorSet;
        if (r53 != null) goto L142;
    L146:
        boolean r93 = false;
    L148:
        if (r21 == false) goto L150;
        if (r93 == true) goto L150;
    L152:
        if (r21 == true) goto L154;
    L156:
        if (r21 == false) goto L158;
        ArrayList r54 = new ArrayList();
        int r112 = this.lastMuteColor;
        this.lastMuteColor = r26;
        ValueAnimator r12 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r12.addUpdateListener(new GroupCallUserCell$$ExternalSyntheticLambda0(this, r112, r26));
        r54.add(r12);
    L160:
        if (r4 != 1) goto L169;
        int r27 = ChatObject.getParticipantVolume(this.participant);
        int r113 = r27 / 100;
        SimpleTextView[] r122 = this.statusTextView;
        if (r113 == 100) goto L167;
        r122[1].setLeftDrawable(this.speakingDrawable);
        SimpleTextView r123 = this.statusTextView[1];
        int r13 = R.string.SpeakingWithVolume;
        if (r27 >= 100) goto L166;
        r113 = 1;
    L166:
        r123.setText(LocaleController.formatString("SpeakingWithVolume", r13, new Object[]{Integer.valueOf(r113)}));
        goto L169
    L167:
        r122[1].setLeftDrawable(null);
        this.statusTextView[1].setText(LocaleController.getString(R.string.Speaking));
    L169:
        if (isSelfUser() == false) goto L171;
        applyStatus(4);
    L176:
        float r17 = 0.0f;
    L213:
        this.avatarWavesDrawable.setMuted(r4, r21);
        if (r54 == null) goto L219;
        AnimatorSet r28 = this.animatorSet;
        if (r28 == null) goto L218;
        r28.cancel();
        this.animatorSet = null;
    L218:
        AnimatorSet r29 = new AnimatorSet();
        this.animatorSet = r29;
        r29.addListener(new AnonymousClass4(this, r4));
        this.animatorSet.playTogether(r54);
        this.animatorSet.setDuration(180);
        this.animatorSet.start();
    L219:
        if (r21 == false) goto L225;
        if (this.lastMuted != r82) goto L225;
        if (this.lastRaisedHand != r36) goto L225;
    L258:
        if (this.isSpeaking == true) goto L260;
        this.avatarWavesDrawable.setAmplitude(0.0d);
    L260:
        AvatarWavesDrawable r1 = this.avatarWavesDrawable;
        if (this.isSpeaking == true) goto L263;
    L265:
        r1.setShowWaves(r6, this);
        return;
    L263:
        if (this.progressToAvatarPreview != r17) goto L265;
        r6 = true;
    L225:
        if (r36 == false) goto L230;
        boolean r55 = this.muteDrawable.setCustomEndFrame(84);
        RLottieDrawable r94 = this.muteDrawable;
        if (r21 == false) goto L229;
        r94.setOnFinishCallback(this.raiseHandCallback, 83);
    L240:
        if (r21 == false) goto L255;
        if (r55 == true) goto L243;
    L254:
        this.muteButton.playAnimation();
    L256:
        this.lastMuted = r82;
        this.lastRaisedHand = r36;
        goto L258
    L243:
        if (r4 != 3) goto L245;
        this.muteDrawable.setCurrentFrame(63);
        goto L254
    L245:
        if (r82 == true) goto L247;
    L250:
        RLottieDrawable r14 = this.muteDrawable;
        if (r82 == false) goto L253;
        r14.setCurrentFrame(43);
        goto L254
    L253:
        r14.setCurrentFrame(21);
        goto L254
    L247:
        if (this.lastRaisedHand == false) goto L250;
        if (r36 == true) goto L250;
        this.muteDrawable.setCurrentFrame(0);
        goto L254
    L255:
        RLottieDrawable r15 = this.muteDrawable;
        r15.setCurrentFrame(r15.getCustomEndFrame() - 1, false, true);
        this.muteButton.invalidate();
        goto L256
    L229:
        r94.setOnFinishCallback(null, 0);
        goto L240
    L230:
        this.muteButton.setAnimation(this.muteDrawable);
        this.muteDrawable.setOnFinishCallback(null, 0);
        if (r82 == true) goto L233;
    L235:
        RLottieDrawable r56 = this.muteDrawable;
        if (r82 == false) goto L238;
        int r95 = 64;
    L239:
        r55 = r56.setCustomEndFrame(r95);
        goto L240
    L238:
        r95 = 42;
        goto L239
    L233:
        if (this.lastRaisedHand == false) goto L235;
        r55 = this.muteDrawable.setCustomEndFrame(21);
        goto L240
    L171:
        if (r21 == true) goto L173;
    L177:
        if (r21 == false) goto L211;
        if (r54 != null) goto L180;
        r54 = new ArrayList();
    L180:
        Property r210 = View.ALPHA;
        Property r124 = View.TRANSLATION_Y;
        if (r4 != 0) goto L196;
        int r142 = 0;
    L183:
        SimpleTextView[] r152 = this.statusTextView;
        if (r142 >= r152.length) goto L195;
        SimpleTextView r96 = r152[r142];
        if (r142 != r4) goto L189;
        float r153 = 0.0f;
    L190:
        r54.add(ObjectAnimator.ofFloat(r96, r124, new float[]{r153}));
        SimpleTextView r97 = this.statusTextView[r142];
        if (r142 != r4) goto L193;
        float r114 = 1.0f;
    L194:
        r54.add(ObjectAnimator.ofFloat(r97, r210, new float[]{r114}));
        r142 = r142 + 1;
        goto L183
    L193:
        r114 = 0.0f;
        goto L194
    L189:
        r153 = AndroidUtilities.dp(-2.0f);
        goto L190
    L195:
        r17 = 0.0f;
    L212:
        this.currentStatus = r4;
        goto L213
    L196:
        r17 = 0.0f;
        int r98 = 0;
    L197:
        SimpleTextView[] r115 = this.statusTextView;
        if (r98 >= r115.length) goto L212;
        SimpleTextView r116 = r115[r98];
        if (r98 != r4) goto L202;
        float r143 = 0.0f;
    L206:
        r54.add(ObjectAnimator.ofFloat(r116, r124, new float[]{r143}));
        SimpleTextView r117 = this.statusTextView[r98];
        if (r98 != r4) goto L209;
        float r144 = 1.0f;
    L210:
        r54.add(ObjectAnimator.ofFloat(r117, r210, new float[]{r144}));
        r98 = r98 + 1;
        goto L197
    L209:
        r144 = 0.0f;
        goto L210
    L202:
        if (r98 != 0) goto L204;
        float r145 = 2.0f;
    L205:
        r143 = AndroidUtilities.dp(r145);
        goto L206
    L204:
        r145 = -2.0f;
        goto L205
    L211:
        r17 = 0.0f;
        applyStatus(r4);
        goto L212
    L173:
        if (r4 != this.currentStatus) goto L177;
        if (r93 == false) goto L176;
    L158:
        RLottieImageView r57 = this.muteButton;
        this.lastMuteColor = r26;
        r57.setColorFilter(new PorterDuffColorFilter(r26, PorterDuff.Mode.SRC_IN));
        Theme.setSelectorDrawableColor(this.muteButton.getDrawable(), r26 & 620756991, true);
    L159:
        r54 = null;
        goto L160
    L154:
        if (this.lastMuteColor != r26) goto L156;
        if (r93 == false) goto L159;
    L150:
        if (r53 == null) goto L152;
        r53.cancel();
        this.animatorSet = null;
        goto L152
    L142:
        if (r4 == this.currentStatus) goto L144;
    L145:
        r93 = true;
        goto L148
    L144:
        if (this.lastMuteColor == r26) goto L146;
    L134:
        r52.setText(this.statusTextView[r4].getText());
        this.fullAboutTextView.setTextColor(this.statusTextView[r4].getTextColor());
        goto L139
    L125:
        r102[4].setText(LocaleController.getString(R.string.TapToAddDescription));
        goto L126
    L127:
        boolean r58 = this.hasAvatar;
        SimpleTextView[] r103 = this.statusTextView;
        if (r58 == true) goto L130;
        r103[4].setText(LocaleController.getString(R.string.TapToAddPhoto));
        this.statusTextView[4].setTextColor(Theme.getColor(this.grayIconColor));
        goto L131
    L130:
        r103[4].setText(LocaleController.getString(R.string.ThisIsYou));
        this.statusTextView[4].setTextColor(Theme.getColor(Theme.key_voipgroup_listeningText));
        goto L131
    L115:
        if (this.hasAvatar == true) goto L121;
        TLRPC.User r59 = this.currentUser;
        SimpleTextView[] r104 = this.statusTextView;
        if (r59 == null) goto L119;
        r104[4].setText(LocaleController.getString(R.string.TapToAddPhotoOrBio));
    L120:
        this.statusTextView[4].setTextColor(Theme.getColor(this.grayIconColor));
        goto L131
    L119:
        r104[4].setText(LocaleController.getString(R.string.TapToAddPhotoOrDescription));
        goto L120
    L135:
        SimpleTextView[] r510 = this.statusTextView;
        if (r92 == true) goto L138;
        r510[4].setText(AndroidUtilities.replaceNewLines(this.participant.about));
        this.fullAboutTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L139
    L138:
        r510[4].setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.fullAboutTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L139
    L72:
        r35 = 0;
    L74:
        if (r25 == false) goto L76;
    L78:
        boolean r211 = false;
    L79:
        if (r211 == false) goto L92;
        int r105 = Theme.getColor(Theme.key_voipgroup_listeningText);
        long r154 = SystemClock.elapsedRealtime();
        long r42 = this.participant.lastRaiseHandDate;
        long r155 = r154 - r42;
        if (r42 != 0) goto L83;
    L86:
        if (r34 == false) goto L88;
        int r37 = 2;
    L91:
        r4 = r37;
        r36 = r211;
        r26 = r105;
        goto L109
    L88:
        if (r92 == true) goto L90;
        r37 = 4;
        goto L91
    L90:
        r37 = 0;
        goto L91
    L83:
        if (r155 > 5000) goto L86;
        AndroidUtilities.runOnUIThread(this.checkRaiseRunnable, 5000 - r155);
        r37 = 3;
        goto L91
    L92:
        int r43 = Theme.getColor(Theme.key_voipgroup_mutedByAdminIcon);
        if (r34 == false) goto L95;
        int r38 = 2;
    L98:
        int r19 = r38;
        r36 = r211;
        r26 = r43;
        r4 = r19;
        goto L109
    L95:
        if (r92 == true) goto L97;
        r38 = 4;
        goto L98
    L97:
        r38 = 0;
        goto L98
    L76:
        if (r10.raise_hand_rating == 0) goto L78;
        r211 = true;
        goto L79
    L100:
        if (this.isSpeaking == false) goto L103;
        if (r24 == false) goto L103;
        r26 = Theme.getColor(Theme.key_voipgroup_speakingText);
        r36 = false;
        r4 = 1;
    L103:
        r26 = Theme.getColor(this.grayIconColor);
        if (r92 == true) goto L106;
        r35 = 4;
    L107:
        this.currentIconGray = true;
        goto L73
    L106:
        r35 = 0;
        goto L107
    L63:
        if (this.isSpeaking == false) goto L65;
    L45:
        if (VoIPService.getSharedInstance().isMicMute() == false) goto L50;
        if (this.isSpeaking == false) goto L49;
        if (r24 == true) goto L50;
    L49:
        r82 = true;
        goto L58
    L52:
        if (this.participant.muted == true) goto L54;
    L56:
        if (r34 == false) goto L50;
    L54:
        if (this.isSpeaking == false) goto L49;
        if (r24 == false) goto L49;
    L37:
        if (isSelfUser() == true) goto L39;
        r34 = true;
        goto L41
    L15:
        r24 = r8.hasVoice;
        goto L16
    L8:
        if (this.participant.raise_hand_rating != 0) goto L11;
        r3 = false;
        goto L12
    }

    private void applyStatus(int r7) {
        int r0 = 0;
        int r1 = 0;
    L3:
        SimpleTextView[] r2 = this.statusTextView;
        if (r1 >= r2.length) goto L11;
        SimpleTextView r22 = r2[r1];
        if (r1 != r7) goto L8;
        int r3 = 1;
    L9:
        r22.setImportantForAccessibility(r3);
        r1 = r1 + 1;
        goto L3
    L8:
        r3 = 2;
        goto L9
    L11:
        if (r7 != 0) goto L23;
    L12:
        SimpleTextView[] r4 = this.statusTextView;
        if (r0 >= r4.length) goto L37;
        SimpleTextView r42 = r4[r0];
        if (r0 != r7) goto L17;
        float r5 = 0.0f;
    L18:
        r42.setTranslationY(r5);
        SimpleTextView r43 = this.statusTextView[r0];
        if (r0 != r7) goto L21;
        float r52 = 1.0f;
    L22:
        r43.setAlpha(r52);
        r0 = r0 + 1;
        goto L12
    L21:
        r52 = 0.0f;
        goto L22
    L17:
        r5 = AndroidUtilities.dp(-2.0f);
        goto L18
    L37:
        return;
    L23:
        SimpleTextView[] r44 = this.statusTextView;
        if (r0 >= r44.length) goto L47;
        SimpleTextView r45 = r44[r0];
        if (r0 != r7) goto L28;
        float r53 = 0.0f;
    L32:
        r45.setTranslationY(r53);
        SimpleTextView r46 = this.statusTextView[r0];
        if (r0 != r7) goto L35;
        float r54 = 1.0f;
    L36:
        r46.setAlpha(r54);
        r0 = r0 + 1;
        goto L23
    L35:
        r54 = 0.0f;
        goto L36
    L28:
        if (r0 != 0) goto L30;
        float r55 = 2.0f;
    L31:
        r53 = AndroidUtilities.dp(r55);
        goto L32
    L30:
        r55 = -2.0f;
        goto L31
    }

    private /* synthetic */ void lambda$applyParticipantChanges$6(int r2, int r3, ValueAnimator r4) {
        int r22 = AndroidUtilities.getOffsetColor(r2, r3, r4.getAnimatedFraction(), 1.0f);
        this.muteButton.setColorFilter(new PorterDuffColorFilter(r22, PorterDuff.Mode.SRC_IN));
        Theme.setSelectorDrawableColor(this.muteButton.getDrawable(), r22 & 620756991, true);
    }

    private /* synthetic */ void lambda$new$0() {
        this.shakeHandDrawable.setOnFinishCallback(null, 0);
        this.muteDrawable.setOnFinishCallback(null, 0);
        this.muteButton.setAnimation(this.muteDrawable);
    }

    private /* synthetic */ void lambda$new$1() {
        int r0 = Utilities.random.nextInt(100);
        int r2 = 120;
        if (r0 >= 32) goto L5;
        int r3 = 0;
    L15:
        this.shakeHandDrawable.setCustomEndFrame(r2);
        this.shakeHandDrawable.setOnFinishCallback(this.shakeHandCallback, r2 - 1);
        this.muteButton.setAnimation(this.shakeHandDrawable);
        this.shakeHandDrawable.setCurrentFrame(r3);
        this.muteButton.playAnimation();
        return;
    L5:
        r3 = 240;
        if (r0 >= 64) goto L8;
    L7:
        int r4 = r3;
        r3 = r2;
        r2 = r4;
        goto L15
    L8:
        r2 = MediaError.DetailedErrorCode.DASH_MANIFEST_UNKNOWN;
        if (r0 < 97) goto L15;
        r3 = 540;
        if (r0 == 98) goto L7;
        r2 = 720;
        goto L15
    }

    private /* synthetic */ void lambda$new$2() {
        applyParticipantChanges(true, true);
    }

    private /* synthetic */ void lambda$new$3() {
        this.isSpeaking = false;
        applyParticipantChanges(true, true);
        this.avatarWavesDrawable.setAmplitude(0.0d);
        this.updateRunnableScheduled = false;
    }

    private /* synthetic */ void lambda$new$4() {
        applyParticipantChanges(true, true);
        this.updateVoiceRunnableScheduled = false;
    }

    private /* synthetic */ void lambda$new$5(View r1) {
        onMuteClick(this);
    }

    public void applyParticipantChanges(boolean r2) {
        applyParticipantChanges(r2, false);
    }

    public boolean clickMuteButton() {
        if (this.muteButton.isEnabled() == false) goto L6;
        this.muteButton.callOnClick();
        return true;
    L6:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r11) {
        if (this.needDivider == false) goto L17;
        int r0 = this.dividerPaint.getAlpha();
        float r2 = this.progressToAvatarPreview;
        Paint r4 = this.dividerPaint;
        if (r2 == 0.0f) goto L7;
        r4.setAlpha((int) ((1.0f - r2) * r0));
    L9:
        if (LocaleController.isRTL == false) goto L11;
        float r5 = 0.0f;
    L12:
        float r6 = getMeasuredHeight() - 1;
        int r22 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L15;
        int r3 = AndroidUtilities.dp(68.0f);
    L16:
        Canvas r42 = r11;
        r42.drawLine(r5, r6, r22 - r3, getMeasuredHeight() - 1, this.dividerPaint);
        this.dividerPaint.setAlpha(r0);
    L18:
        int r112 = this.avatarImageView.getLeft() + (this.avatarImageView.getMeasuredWidth() / 2);
        int r02 = this.avatarImageView.getTop() + (this.avatarImageView.getMeasuredHeight() / 2);
        this.avatarWavesDrawable.update();
        if (this.progressToAvatarPreview != 0.0f) goto L21;
        this.avatarWavesDrawable.draw(r42, r112, r02, this);
    L21:
        this.avatarImageView.setScaleX(this.avatarWavesDrawable.getAvatarScale());
        this.avatarImageView.setScaleY(this.avatarWavesDrawable.getAvatarScale());
        this.avatarProgressView.setScaleX(this.avatarWavesDrawable.getAvatarScale());
        this.avatarProgressView.setScaleY(this.avatarWavesDrawable.getAvatarScale());
        super.dispatchDraw(r42);
        return;
    L15:
        r3 = 0;
        goto L16
    L11:
        r5 = AndroidUtilities.dp(68.0f);
        goto L12
    L7:
        r4.setAlpha((int) ((1.0f - this.statusTextView[4].getFullAlpha()) * r0));
        goto L9
    L17:
        r42 = r11;
        goto L18
    }

    public BackupImageView getAvatarImageView() {
        return this.avatarImageView;
    }

    public AvatarWavesDrawable getAvatarWavesDrawable() {
        return this.avatarWavesDrawable;
    }

    public int getClipHeight() {
        if (TextUtils.isEmpty(this.fullAboutTextView.getText()) == false) goto L5;
    L7:
        SimpleTextView r0 = this.statusTextView[4];
    L9:
        if (r0.getLineCount() <= 1) goto L13;
        int r3 = r0.getTextHeight();
        return (r0.getTop() + r3) + AndroidUtilities.dp(8.0f);
    L13:
        return getMeasuredHeight();
    L5:
        if (this.hasAvatar == false) goto L7;
        r0 = this.fullAboutTextView;
        goto L9
    }

    public CharSequence getName() {
        return this.nameTextView.getText();
    }

    public TLRPC.GroupCallParticipant getParticipant() {
        return this.participant;
    }

    public long getPeerId() {
        TLRPC.GroupCallParticipant r2 = this.participant;
        if (r2 != null) goto L7;
        return 0;
    L7:
        return MessageObject.getPeerId(r2.peer);
    }

    public boolean hasAvatarSet() {
        return this.avatarImageView.getImageReceiver().hasNotThumb();
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public boolean isHandRaised() {
        return this.lastRaisedHand;
    }

    public boolean isSelfUser() {
        long r0 = this.selfId;
        if (r0 <= 0) goto L10;
        TLRPC.User r7 = this.currentUser;
        if (r7 != null) goto L7;
    L9:
        return false;
    L7:
        if (r7.id != r0) goto L9;
        return true;
    L10:
        TLRPC.Chat r72 = this.currentChat;
        if (r72 != null) goto L13;
    L15:
        return false;
    L13:
        if (r72.id != (-r0)) goto L15;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        applyParticipantChanges(false);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.rightDrawable;
        if (r0 == null) goto L5;
        r0.attach();
    L5:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r1 = this.leftDrawable;
        if (r1 == null) goto L9;
        r1.attach();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.updateRunnableScheduled == false) goto L6;
        AndroidUtilities.cancelRunOnUIThread(this.updateRunnable);
        this.updateRunnableScheduled = false;
    L6:
        if (this.updateVoiceRunnableScheduled == false) goto L8;
        AndroidUtilities.cancelRunOnUIThread(this.updateVoiceRunnable);
        this.updateVoiceRunnableScheduled = false;
    L8:
        AnimatorSet r0 = this.animatorSet;
        if (r0 == null) goto L11;
        r0.cancel();
    L11:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r02 = this.rightDrawable;
        if (r02 == null) goto L14;
        r02.detach();
    L14:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r2 = this.leftDrawable;
        if (r2 == null) goto L18;
        r2.detach();
        return;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        if (r3.isEnabled() == false) goto L13;
        TLRPC.GroupCallParticipant r2 = this.participant;
        if (r2.muted == true) goto L7;
    L10:
        int r22 = R.string.VoipMute;
    L11:
        r3.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, LocaleController.getString(r22)));
        return;
    L7:
        if (r2.can_self_unmute == true) goto L10;
        r22 = R.string.VoipUnmute;
        goto L11
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(58.0f), TLObject.FLAG_30));
    }

    public void onMuteClick(GroupCallUserCell r1) {
    }

    public void setAboutVisible(boolean r4) {
        SimpleTextView[] r0 = this.statusTextView;
        if (r4 == false) goto L5;
        r0[4].setTranslationY(0.0f);
    L6:
        invalidate();
        return;
    L5:
        r0[4].setFullAlpha(0.0f);
        goto L6
    }

    public void setAboutVisibleProgress(int r2, float r3) {
        if (TextUtils.isEmpty(this.statusTextView[4].getText()) == false) goto L5;
        r3 = 0.0f;
    L5:
        this.statusTextView[4].setFullAlpha(r3);
        this.statusTextView[4].setFullLayoutAdditionalWidth(0, 0);
        invalidate();
    }

    public void setAmplitude(double r5) {
        if (r5 > 1.5d) goto L5;
        this.avatarWavesDrawable.setAmplitude(0.0d);
        return;
    L5:
        if (this.updateRunnableScheduled == false) goto L8;
        AndroidUtilities.cancelRunOnUIThread(this.updateRunnable);
    L8:
        if (this.isSpeaking == true) goto L10;
        this.isSpeaking = true;
        applyParticipantChanges(true);
    L10:
        this.avatarWavesDrawable.setAmplitude(r5);
        AndroidUtilities.runOnUIThread(this.updateRunnable, 500);
        this.updateRunnableScheduled = true;
    }

    public void setData(AccountInstance r9, TLRPC.GroupCallParticipant r10, ChatObject.Call r11, long r12, TLRPC.FileLocation r14, boolean r15) {
        this.currentCall = r11;
        this.accountInstance = r9;
        this.selfId = r12;
        this.participant = r10;
        long r102 = MessageObject.getPeerId(r10.peer);
        AccountInstance r1 = this.accountInstance;
        boolean r2 = false;
        if (r102 <= 0) goto L32;
        this.currentUser = r1.getMessagesController().getUser(Long.valueOf(r102));
        this.currentChat = null;
        this.avatarDrawable.setInfo(this.accountInstance.getCurrentAccount(), this.currentUser);
        this.nameTextView.setText(UserObject.getUserName(this.currentUser));
        long r103 = DialogObject.getBotVerificationIcon(this.currentUser);
        TLRPC.User r0 = this.currentUser;
        if (r0 != null) goto L7;
    L12:
        if (r0 != null) goto L14;
    L16:
        TLRPC.User r02 = this.currentUser;
        if (r02 != null) goto L19;
    L24:
        this.rightDrawable.set(null, r15);
    L25:
        this.rightDrawable.setColor(Integer.valueOf(Theme.getColor(Theme.key_premiumGradient1)));
        this.nameTextView.setRightDrawable(this.rightDrawable);
        this.avatarImageView.getImageReceiver().setCurrentAccount(r9.getCurrentAccount());
        if (r14 == null) goto L28;
        this.hasAvatar = true;
        this.avatarImageView.setImage(ImageLocation.getForLocal(r14), "50_50", this.avatarDrawable, null);
    L52:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r122 = this.leftDrawable;
        if (r103 == 0) goto L55;
        r122.set(r103, r15);
        this.nameTextView.setLeftDrawable(this.leftDrawable);
        this.leftDrawable.setColor(Integer.valueOf(Theme.getColor(Theme.key_premiumGradient1)));
    L56:
        applyParticipantChanges(r15);
        return;
    L55:
        r122.set(null, r15);
        this.nameTextView.setLeftDrawable(null);
        goto L56
    L28:
        ImageLocation r92 = ImageLocation.getForUser(r9.getCurrentAccount(), this.currentUser, 1);
        if (r92 == null) goto L31;
        r2 = true;
    L31:
        this.hasAvatar = r2;
        this.avatarImageView.setImage(r92, "50_50", this.avatarDrawable, this.currentUser);
        goto L52
    L19:
        if (r02.premium == false) goto L24;
        if (this.premiumDrawable != null) goto L23;
        this.premiumDrawable = getContext().getResources().getDrawable(R.drawable.msg_premium_liststar).mutate();
        this.premiumDrawable = new AnonymousClass3(this, this.premiumDrawable, AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f));
    L23:
        this.rightDrawable.set(this.premiumDrawable, r15);
        goto L25
    L14:
        if (DialogObject.getEmojiStatusDocumentId(r0.emoji_status) == 0) goto L16;
        this.rightDrawable.set(DialogObject.getEmojiStatusDocumentId(this.currentUser.emoji_status), r15);
        goto L25
    L7:
        if (r0.verified == false) goto L12;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r03 = this.rightDrawable;
        Drawable r13 = this.verifiedDrawable;
        if (r13 != null) goto L11;
        r13 = new VerifiedDrawable(getContext());
    L11:
        this.verifiedDrawable = r13;
        r03.set(r13, r15);
        goto L25
    L32:
        this.currentChat = r1.getMessagesController().getChat(Long.valueOf(-r102));
        this.currentUser = null;
        this.avatarDrawable.setInfo(this.accountInstance.getCurrentAccount(), this.currentChat);
        r103 = DialogObject.getBotVerificationIcon(this.currentChat);
        TLRPC.Chat r04 = this.currentChat;
        if (r04 == null) goto L52;
        this.nameTextView.setText(r04.title);
        TLRPC.Chat r05 = this.currentChat;
        if (r05.verified == false) goto L40;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r06 = this.rightDrawable;
        Drawable r16 = this.verifiedDrawable;
        if (r16 != null) goto L39;
        r16 = new VerifiedDrawable(getContext());
    L39:
        this.verifiedDrawable = r16;
        r06.set(r16, r15);
    L45:
        this.avatarImageView.getImageReceiver().setCurrentAccount(r9.getCurrentAccount());
        if (r14 == null) goto L48;
        this.hasAvatar = true;
        this.avatarImageView.setImage(ImageLocation.getForLocal(r14), "50_50", this.avatarDrawable, null);
        goto L52
    L48:
        ImageLocation r93 = ImageLocation.getForChat(this.currentChat, 1);
        if (r93 == null) goto L51;
        r2 = true;
    L51:
        this.hasAvatar = r2;
        this.avatarImageView.setImage(r93, "50_50", this.avatarDrawable, this.currentChat);
        goto L52
    L40:
        if (r05 != null) goto L42;
    L44:
        this.rightDrawable.set(null, r15);
        goto L45
    L42:
        if (DialogObject.getEmojiStatusDocumentId(r05.emoji_status) == 0) goto L44;
        this.rightDrawable.set(DialogObject.getEmojiStatusDocumentId(this.currentChat.emoji_status), r15);
        goto L45
    }

    public void setDrawAvatar(boolean r2) {
        if (this.avatarImageView.getImageReceiver().getVisible() == r2) goto L6;
        this.avatarImageView.getImageReceiver().setVisible(r2, true);
        return;
    }

    public void setDrawDivider(boolean r1) {
        this.needDivider = r1;
        invalidate();
    }

    public void setGrayIconColor(int r3, int r4) {
        if (this.grayIconColor == r3) goto L9;
        if (this.currentIconGray == false) goto L7;
        this.lastMuteColor = Theme.getColor(r3);
    L7:
        this.grayIconColor = r3;
    L9:
        if (this.currentIconGray == false) goto L12;
        this.muteButton.setColorFilter(new PorterDuffColorFilter(r4, PorterDuff.Mode.SRC_IN));
        Theme.setSelectorDrawableColor(this.muteButton.getDrawable(), 620756991 & r4, true);
        return;
    }

    public void setProgressToAvatarPreview(float r11) {
        this.progressToAvatarPreview = r11;
        SimpleTextView r0 = this.nameTextView;
        if (LocaleController.isRTL == false) goto L5;
        int r1 = AndroidUtilities.dp(53.0f);
    L6:
        r0.setTranslationX(r1 * r11);
        boolean r12 = true;
        float r5 = 0.0f;
        if (isSelfUser() == true) goto L9;
    L19:
        this.fullAboutTextView.setVisibility(8);
        int r02 = 0;
    L20:
        SimpleTextView[] r7 = this.statusTextView;
        if (r02 >= r7.length) goto L37;
        if (TextUtils.isEmpty(r7[4].getText()) == false) goto L25;
    L31:
        SimpleTextView r72 = this.statusTextView[r02];
        if (LocaleController.isRTL == false) goto L34;
        int r8 = AndroidUtilities.dp(53.0f);
    L35:
        r72.setTranslationX(r8 * r11);
        this.statusTextView[r02].setFullLayoutAdditionalWidth(0, 0);
    L36:
        r02 = r02 + 1;
        goto L20
    L34:
        r8 = -AndroidUtilities.dp(53.0f);
        goto L35
    L25:
        if (this.statusTextView[4].getLineCount() <= 1) goto L31;
        SimpleTextView r73 = this.statusTextView[r02];
        int r82 = AndroidUtilities.dp(92.0f);
        if (LocaleController.isRTL == false) goto L29;
        int r9 = AndroidUtilities.dp(48.0f);
    L30:
        r73.setFullLayoutAdditionalWidth(r82, r9);
        this.statusTextView[r02].setFullAlpha(r11);
        this.statusTextView[r02].setTranslationX(0.0f);
        this.statusTextView[r02].invalidate();
        goto L36
    L29:
        r9 = AndroidUtilities.dp(53.0f);
    L37:
        BackupImageView r03 = this.avatarImageView;
        if (r11 != 0.0f) goto L40;
        r5 = 1.0f;
    L40:
        r03.setAlpha(r5);
        AvatarWavesDrawable r04 = this.avatarWavesDrawable;
        if (this.isSpeaking == false) goto L44;
        if (r11 != 0.0f) goto L44;
    L45:
        r04.setShowWaves(r12, this);
        float r3 = 1.0f - r11;
        this.muteButton.setAlpha(r3);
        float r32 = (r3 * 0.4f) + 0.6f;
        this.muteButton.setScaleX(r32);
        this.muteButton.setScaleY(r32);
        invalidate();
        return;
    L44:
        r12 = false;
        goto L45
    L9:
        if (r11 <= 0.0f) goto L19;
        SimpleTextView r05 = this.fullAboutTextView;
        if (LocaleController.isRTL == false) goto L13;
        int r74 = -AndroidUtilities.dp(53.0f);
    L14:
        float r83 = 1.0f - r11;
        r05.setTranslationX(r74 * r83);
        this.fullAboutTextView.setVisibility(0);
        this.fullAboutTextView.setAlpha(r11);
        this.statusTextView[4].setAlpha(r83);
        SimpleTextView r06 = this.statusTextView[4];
        boolean r4 = LocaleController.isRTL;
        int r2 = AndroidUtilities.dp(53.0f);
        if (r4 == true) goto L18;
        r2 = -r2;
    L18:
        r06.setTranslationX(r2 * r11);
        goto L37
    L13:
        r74 = AndroidUtilities.dp(53.0f);
        goto L14
    L5:
        r1 = -AndroidUtilities.dp(53.0f);
        goto L6
    }

    public void setUploadProgress(float r2, boolean r3) {
        this.avatarProgressView.setProgress(r2);
        RadialProgressView r1 = this.avatarProgressView;
        if (r2 >= 1.0f) goto L6;
        AndroidUtilities.updateViewVisibilityAnimated(r1, true, 1.0f, r3);
        return;
    L6:
        AndroidUtilities.updateViewVisibilityAnimated(r1, false, 1.0f, r3);
    }
}
