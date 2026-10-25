package org.telegram.ui.Components.Reactions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.Random;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.ReactionsContainerLayout;
import org.telegram.ui.Components.conference.message.GroupCallMessageCell;
import org.telegram.ui.SelectAnimatedEmojiDialog;

/* loaded from: classes7.dex */
public class ReactionsEffectOverlay {

    @SuppressLint({"StaticFieldLeak"})
    public static ReactionsEffectOverlay currentOverlay;

    @SuppressLint({"StaticFieldLeak"})
    public static ReactionsEffectOverlay currentShortOverlay;
    private static long lastHapticTime;
    private static int uniqPrefix;
    float animateInProgress;
    float animateOutProgress;
    private final int animationType;
    ArrayList<AvatarParticle> avatars;
    private View cell;
    private final FrameLayout container;
    private final int currentAccount;
    private ViewGroup decorView;
    private float dismissProgress;
    private boolean dismissed;
    private final AnimationView effectImageView;
    private final AnimationView emojiImageView;
    private final AnimationView emojiStaticImageView;
    private final long groupId;
    private ReactionsContainerLayout.ReactionHolderView holderView;
    private SelectAnimatedEmojiDialog.ImageViewEmoji holderView2;
    boolean isFinished;
    public boolean isStories;
    private float lastDrawnToX;
    private float lastDrawnToY;
    int[] loc;
    private final int messageId;
    private ReactionsEffectOverlay nextReactionOverlay;
    private final ReactionsLayoutInBubble.VisibleReaction reaction;
    public long startTime;
    public boolean started;
    private boolean useWindow;
    private boolean wasScrolled;
    private WindowManager windowManager;
    public FrameLayout windowView;

    /* renamed from: org.telegram.ui.Components.Reactions.ReactionsEffectOverlay$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ ReactionsEffectOverlay this$0;
        final /* synthetic */ int val$animationType;
        final /* synthetic */ View val$cell;
        final /* synthetic */ ChatActivity val$chatActivity;
        final /* synthetic */ int val$emojiSize;
        final /* synthetic */ BaseFragment val$fragment;
        final /* synthetic */ boolean val$fromHolder;
        final /* synthetic */ float val$fromScale;
        final /* synthetic */ float val$fromX;
        final /* synthetic */ float val$fromY;
        final /* synthetic */ boolean val$isStories;
        final /* synthetic */ MessageObject val$messageObject;
        final /* synthetic */ ReactionsLayoutInBubble.VisibleReaction val$visibleReaction;

        /* renamed from: org.telegram.ui.Components.Reactions.ReactionsEffectOverlay$1$1, reason: invalid class name and collision with other inner class name */
        public class C00561 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass1 this$1;

            public C00561(AnonymousClass1 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                ReactionsEffectOverlay.m12372$$Nest$mremoveCurrentView(this.this$1.this$0);
            }
        }

        public static /* synthetic */ void $r8$lambda$A1r5SfGreCdH1dNbn61qji7lPRA(AnonymousClass1 r0) {
            r0.lambda$dispatchDraw$0();
        }

        public static /* synthetic */ void $r8$lambda$_D_1QYCfGfBtWRmvVirOBQNfrdo(AnonymousClass1 r0) {
            r0.lambda$dispatchDraw$1();
        }

        public AnonymousClass1(ReactionsEffectOverlay r1, Context r2, BaseFragment r3, View r4, boolean r5, MessageObject r6, ChatActivity r7, int r8, int r9, boolean r10, float r11, float r12, float r13, ReactionsLayoutInBubble.VisibleReaction r14) {
            this.this$0 = r1;
            this.val$fragment = r3;
            this.val$cell = r4;
            this.val$isStories = r5;
            this.val$messageObject = r6;
            this.val$chatActivity = r7;
            this.val$emojiSize = r8;
            this.val$animationType = r9;
            this.val$fromHolder = r10;
            this.val$fromScale = r11;
            this.val$fromX = r12;
            this.val$fromY = r13;
            this.val$visibleReaction = r14;
            super(r2);
        }

        private /* synthetic */ void lambda$dispatchDraw$0() {
            ReactionsEffectOverlay.m12372$$Nest$mremoveCurrentView(this.this$0);
        }

        private /* synthetic */ void lambda$dispatchDraw$1() {
            ReactionsEffectOverlay.m12372$$Nest$mremoveCurrentView(this.this$0);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r23) {
            boolean r2 = ReactionsEffectOverlay.m12358$$Nest$fgetdismissed(this.this$0);
            ReactionsEffectOverlay r3 = this.this$0;
            if (r2 == false) goto L15;
            if (ReactionsEffectOverlay.m12357$$Nest$fgetdismissProgress(r3) == 1.0f) goto L10;
            ReactionsEffectOverlay r22 = this.this$0;
            ReactionsEffectOverlay.m12369$$Nest$fputdismissProgress(r22, ReactionsEffectOverlay.m12357$$Nest$fgetdismissProgress(r22) + 0.10666667f);
            if (ReactionsEffectOverlay.m12357$$Nest$fgetdismissProgress(this.this$0) <= 1.0f) goto L10;
            ReactionsEffectOverlay.m12369$$Nest$fputdismissProgress(this.this$0, 1.0f);
            AndroidUtilities.runOnUIThread(new ReactionsEffectOverlay$1$$ExternalSyntheticLambda0(this));
        L10:
            if (ReactionsEffectOverlay.m12357$$Nest$fgetdismissProgress(this.this$0) == 1.0f) goto L12;
            setAlpha(1.0f - ReactionsEffectOverlay.m12357$$Nest$fgetdismissProgress(this.this$0));
            super.dispatchDraw(r23);
        L12:
            invalidate();
            return;
        L15:
            if (r3.started == true) goto L19;
            invalidate();
            return;
        L19:
            if (ReactionsEffectOverlay.m12362$$Nest$fgetholderView(r3) == null) goto L21;
            ReactionsEffectOverlay.m12362$$Nest$fgetholderView(this.this$0).enterImageView.setAlpha(0.0f);
            ReactionsEffectOverlay.m12362$$Nest$fgetholderView(this.this$0).pressedBackupImageView.setAlpha(0.0f);
        L21:
            BaseFragment r24 = this.val$fragment;
            if ((r24 instanceof ChatActivity) == false) goto L24;
            View r25 = ((ChatActivity) r24).findCell(ReactionsEffectOverlay.m12365$$Nest$fgetmessageId(this.this$0), false);
        L26:
            if (this.val$isStories == true) goto L28;
            MessageObject r6 = this.val$messageObject;
            if (r6 != null) goto L35;
        L38:
            int r62 = AndroidUtilities.dp(14.0f);
        L37:
            float r63 = r62;
            if (r25 == null) goto L63;
            r25.getLocationInWindow(this.this$0.loc);
            ReactionsEffectOverlay r12 = this.this$0;
            int[] r13 = r12.loc;
            float r14 = r13[0];
            float r132 = r13[1];
            if ((r25 instanceof ChatMessageCell) == false) goto L50;
            ChatMessageCell r15 = (ChatMessageCell) r25;
            ReactionsLayoutInBubble.ReactionButton r122 = r15.getReactionButton(ReactionsEffectOverlay.m12367$$Nest$fgetreaction(r12));
            float r16 = 0.10666667f;
            if (r15.drawPinnedBottom == true) goto L46;
        L48:
            int r4 = r15.getPaddingTop();
        L49:
            r132 = r132 + r4;
        L57:
            if (r122 == null) goto L59;
            Rect r42 = r122.drawingImageRect;
            r14 = r14 + r42.left;
            r132 = r132 + r42.top;
        L59:
            ChatActivity r43 = this.val$chatActivity;
            if (r43 == null) goto L62;
            r132 = r132 + r43.drawingChatListViewYoffset;
        L62:
            ReactionsEffectOverlay.m12370$$Nest$fputlastDrawnToX(this.this$0, r14);
            ReactionsEffectOverlay.m12371$$Nest$fputlastDrawnToY(this.this$0, r132);
        L67:
            BaseFragment r44 = this.val$fragment;
            if (r44 == null) goto L81;
            if (r44.getParentActivity() == null) goto L81;
            if (this.val$fragment.getFragmentView() == null) goto L81;
            if (this.val$fragment.getFragmentView().getParent() == null) goto L81;
            if (this.val$fragment.getFragmentView().getVisibility() != 0) goto L81;
            if (this.val$fragment.getFragmentView() == null) goto L81;
            this.val$fragment.getFragmentView().getLocationOnScreen(this.this$0.loc);
            setAlpha(((View) this.val$fragment.getFragmentView().getParent()).getAlpha());
        L85:
            boolean r26 = r25 instanceof GroupCallMessageCell;
            int r45 = this.val$emojiSize;
            if (r26 == false) goto L89;
            float r27 = r14 - (r45 / 2.0f);
            float r46 = r45;
        L88:
            float r47 = r132 - (r46 / 2.0f);
            if (this.val$isStories == false) goto L96;
            if (this.val$animationType != 0) goto L96;
            r27 = r27 + AndroidUtilities.dp(40.0f);
        L96:
            if (this.val$animationType != 1) goto L98;
        L105:
            CubicBezierInterpolator r123 = CubicBezierInterpolator.DEFAULT;
            float r152 = r123.getInterpolation(this.this$0.animateOutProgress);
            if (this.val$animationType != 2) goto L109;
            float r8 = CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(r152);
            float r124 = r123.getInterpolation(r152);
            float r19 = 2.0f;
        L112:
            float r10 = 1.0f - r8;
            float r32 = (this.val$fromScale * r10) + r8;
            float r64 = r63 / this.val$emojiSize;
            if (this.val$animationType != 1) goto L115;
            r32 = 1.0f;
        L116:
            ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).setTranslationX(r27);
            ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).setTranslationY(r47);
            float r5 = 1.0f - r152;
            ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).setAlpha(r5);
            ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).setScaleX(r32);
            ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).setScaleY(r32);
            int r7 = this.val$animationType;
            if (r7 != 2) goto L121;
            r32 = (this.val$fromScale * r10) + (r64 * r8);
            r27 = (this.val$fromX * r10) + (r14 * r8);
            float r48 = this.val$fromY * (1.0f - r124);
            float r133 = r132 * r124;
        L119:
            r47 = r48 + r133;
        L124:
            if (r7 == 1) goto L134;
            boolean r72 = this.val$isStories;
            ReactionsEffectOverlay r82 = this.this$0;
            if (r72 == true) goto L132;
            AnimationView r73 = ReactionsEffectOverlay.m12361$$Nest$fgetemojiStaticImageView(r82);
            if (r152 <= 0.7f) goto L130;
            float r83 = (r152 - 0.7f) / 0.3f;
        L131:
            r73.setAlpha(r83);
            goto L134
        L130:
            r83 = 0.0f;
            goto L131
        L132:
            ReactionsEffectOverlay.m12361$$Nest$fgetemojiStaticImageView(r82).setAlpha(1.0f);
        L134:
            if (this.val$animationType == 0) goto L136;
        L138:
            ReactionsEffectOverlay.m12356$$Nest$fgetcontainer(this.this$0).setTranslationX(r27);
            ReactionsEffectOverlay.m12356$$Nest$fgetcontainer(this.this$0).setTranslationY(r47);
            ReactionsEffectOverlay.m12356$$Nest$fgetcontainer(this.this$0).setScaleX(r32);
            ReactionsEffectOverlay.m12356$$Nest$fgetcontainer(this.this$0).setScaleY(r32);
            super.dispatchDraw(r23);
            if (this.val$animationType != 1) goto L141;
        L142:
            ReactionsEffectOverlay r28 = this.this$0;
            float r49 = r28.animateInProgress;
            if (r49 != 1.0f) goto L145;
        L152:
            if (this.val$animationType != 2) goto L154;
        L158:
            float r52 = 0.045714285f;
            float r102 = 16.0f;
        L185:
            ReactionsEffectOverlay r29 = this.this$0;
            float r33 = r29.animateOutProgress;
            if (r33 == 1.0f) goto L236;
            int r410 = this.val$animationType;
            if (r410 != 1) goto L190;
            r29.animateOutProgress = 1.0f;
        L195:
            if (r29.animateOutProgress > 0.7f) goto L197;
        L205:
            ReactionsEffectOverlay r210 = this.this$0;
            if (r210.animateOutProgress < 1.0f) goto L236;
            int r34 = this.val$animationType;
            if (r34 == 0) goto L210;
            if (r34 == 2) goto L210;
        L216:
            this.this$0.animateOutProgress = 1.0f;
            if (this.val$animationType != 1) goto L219;
            ReactionsEffectOverlay.currentShortOverlay = null;
        L220:
            View r211 = this.val$cell;
            if (r211 == null) goto L230;
            r211.invalidate();
            View r212 = this.val$cell;
            if ((r212 instanceof ChatMessageCell) == false) goto L230;
            if (((ChatMessageCell) r212).getCurrentMessagesGroup() == null) goto L230;
            if (this.val$cell.getParent() == null) goto L230;
            ((View) this.val$cell.getParent()).invalidate();
        L230:
            if (this.val$isStories == true) goto L232;
        L234:
            AndroidUtilities.runOnUIThread(new ReactionsEffectOverlay$1$$ExternalSyntheticLambda1(this));
            goto L236
        L232:
            if (this.val$animationType != 2) goto L234;
        L219:
            ReactionsEffectOverlay.currentOverlay = null;
        L210:
            View r35 = this.val$cell;
            if ((r35 instanceof ChatMessageCell) == false) goto L214;
            ((ChatMessageCell) r35).reactionsLayoutInBubble.animateReaction(ReactionsEffectOverlay.m12367$$Nest$fgetreaction(r210));
            goto L216
        L214:
            if ((r35 instanceof ChatActionCell) == false) goto L216;
            ((ChatActionCell) r35).reactionsLayoutInBubble.animateReaction(ReactionsEffectOverlay.m12367$$Nest$fgetreaction(r210));
            goto L216
        L197:
            if (this.val$isStories == false) goto L204;
            if (r410 != 2) goto L204;
            if (r29.isFinished == true) goto L205;
            r29.isFinished = true;
            performHapticFeedback(0);     // Catch: Exception -> L277
        L203:
            ((ViewGroup) getParent()).addView(ReactionsEffectOverlay.m12366$$Nest$fgetnextReactionOverlay(this.this$0).windowView);
            ReactionsEffectOverlay.m12366$$Nest$fgetnextReactionOverlay(this.this$0).isStories = true;
            ReactionsEffectOverlay.m12366$$Nest$fgetnextReactionOverlay(this.this$0).started = true;
            ReactionsEffectOverlay.m12366$$Nest$fgetnextReactionOverlay(this.this$0).startTime = System.currentTimeMillis();
            ReactionsEffectOverlay.m12366$$Nest$fgetnextReactionOverlay(this.this$0).windowView.setTag(R.id.parent_tag, 1);
            animate().scaleX(0.0f).scaleY(0.0f).setStartDelay(1000).setDuration(150).setListener(new C00561(this));
        L204:
            ReactionsEffectOverlay.startShortAnimation();
            goto L205
        L190:
            if (r410 != 2) goto L192;
            float r74 = 350.0f;
        L193:
            r29.animateOutProgress = r33 + (r102 / r74);
            goto L195
        L192:
            r74 = 220.0f;
        L236:
            if (this.this$0.avatars.isEmpty() == false) goto L238;
        L275:
            invalidate();
            return;
        L238:
            if (ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).wasPlaying == false) goto L275;
            RLottieDrawable r213 = ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).getImageReceiver().getLottieAnimation();
            int r36 = 0;
        L241:
            if (r36 >= this.this$0.avatars.size()) goto L275;
            AvatarParticle r411 = this.this$0.avatars.get(r36);
            float r65 = r411.progress;
            if (r213 != null) goto L245;
        L248:
            float r75 = r411.outProgress;
            if (r75 == 1.0f) goto L254;
            float r76 = r75 + r16;
            r411.outProgress = r76;
            if (r76 <= 1.0f) goto L254;
            r411.outProgress = 1.0f;
            this.this$0.avatars.remove(r36);
            r36 = r36 - 1;
            float r18 = r52;
        L274:
            r36 = r36 + 1;
            r52 = r18;
        L254:
            if (r65 >= 0.5f) goto L256;
            float r77 = r65 / 0.5f;
            float r21 = 1.0f;
        L257:
            float r9 = r21 - r65;
            float r84 = (r411.fromX * r9) + (r411.toX * r65);
            float r125 = ((r411.fromY * r9) + (r411.toY * r65)) - (r411.jumpY * r77);
            float r78 = (r411.randomScale * r65) * (1.0f - r411.outProgress);
            float r92 = ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).getX() + ((ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).getWidth() * ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).getScaleX()) * r84);
            float r85 = ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).getY() + ((ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).getHeight() * ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).getScaleY()) * r125);
            int r126 = AndroidUtilities.dp(r102);
            float r142 = r126;
            float r153 = r142 / r19;
            r18 = r52;
            this.this$0.avatars.get(r36).imageReceiver.setImageCoords(r92 - r153, r85 - r153, r142, r142);
            this.this$0.avatars.get(r36).imageReceiver.setRoundRadius(r126 >> 1);
            r23.save();
            r23.translate(0.0f, r411.globalTranslationY);
            r23.scale(r78, r78, r92, r85);
            r23.rotate(r411.currentRotation, r92, r85);
            this.this$0.avatars.get(r36).imageReceiver.draw(r23);
            r23.restore();
            float r53 = r411.progress;
            if (r53 >= 1.0f) goto L263;
            float r54 = r53 + r18;
            r411.progress = r54;
            if (r54 <= 1.0f) goto L263;
            r411.progress = 1.0f;
        L263:
            if (r65 < 1.0f) goto L265;
            r411.globalTranslationY += (AndroidUtilities.dp(20.0f) * r102) / 500.0f;
        L265:
            boolean r55 = r411.incrementRotation;
            float r66 = r411.currentRotation;
            if (r55 == false) goto L271;
            float r56 = r411.randomRotation;
            float r67 = r66 + (r56 / 250.0f);
            r411.currentRotation = r67;
            if (r67 <= r56) goto L274;
            r411.incrementRotation = false;
            goto L274
        L271:
            float r93 = r411.randomRotation;
            float r68 = r66 - (r93 / 250.0f);
            r411.currentRotation = r68;
            if (r68 >= (-r93)) goto L274;
            r411.incrementRotation = true;
            goto L274
        L256:
            r21 = 1.0f;
            r77 = 1.0f - ((r65 - 0.5f) / 0.5f);
            goto L257
        L245:
            if (r213.isRunning() == false) goto L248;
            long r79 = ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).getImageReceiver().getLottieAnimation().getDuration();
            float r710 = r79;
            if (((int) (r710 - ((ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).getImageReceiver().getLottieAnimation().getCurrentFrame() / ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).getImageReceiver().getLottieAnimation().getFramesCount()) * r710))) >= r411.leftTime) goto L254;
        L154:
            if (ReactionsEffectOverlay.m12368$$Nest$fgetwasScrolled(this.this$0) == false) goto L160;
            if (this.val$animationType == 0) goto L158;
        L160:
            if (this.val$animationType == 1) goto L168;
            if (ReactionsEffectOverlay.m12360$$Nest$fgetemojiImageView(this.this$0).wasPlaying == false) goto L168;
            if (ReactionsEffectOverlay.m12360$$Nest$fgetemojiImageView(this.this$0).getImageReceiver().getLottieAnimation() == null) goto L168;
            if (ReactionsEffectOverlay.m12360$$Nest$fgetemojiImageView(this.this$0).getImageReceiver().getLottieAnimation().isRunning() == false) goto L158;
        L168:
            if (this.val$visibleReaction.documentId == 0) goto L172;
            r52 = 0.045714285f;
            r102 = 16.0f;
            if ((System.currentTimeMillis() - this.this$0.startTime) > 2000) goto L185;
        L174:
            if (this.val$animationType != 1) goto L182;
            if (ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).wasPlaying == false) goto L182;
            if (ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).getImageReceiver().getLottieAnimation() == null) goto L182;
            if (ReactionsEffectOverlay.m12359$$Nest$fgeteffectImageView(this.this$0).getImageReceiver().getLottieAnimation().isRunning() == false) goto L185;
        L182:
            if (this.val$visibleReaction.documentId == 0) goto L236;
            if ((System.currentTimeMillis() - this.this$0.startTime) <= 2000) goto L236;
        L172:
            r52 = 0.045714285f;
            r102 = 16.0f;
            goto L174
        L145:
            if (this.val$fromHolder == false) goto L147;
            r28.animateInProgress = r49 + 0.045714285f;
        L149:
            if (r28.animateInProgress <= 1.0f) goto L152;
            r28.animateInProgress = 1.0f;
            goto L152
        L147:
            r28.animateInProgress = r49 + 0.07272727f;
            goto L149
        L141:
            if (ReactionsEffectOverlay.m12360$$Nest$fgetemojiImageView(this.this$0).wasPlaying == false) goto L152;
        L136:
            if (this.val$isStories == false) goto L138;
            ReactionsEffectOverlay.m12360$$Nest$fgetemojiImageView(this.this$0).setAlpha(r5);
            goto L138
        L121:
            if (r152 == 0.0f) goto L124;
            r32 = (r32 * r5) + (r64 * r152);
            r27 = (r27 * r5) + (r14 * r152);
            r48 = r47 * r5;
            r133 = r132 * r152;
            goto L119
        L115:
            r27 = (r27 * r8) + (this.val$fromX * r10);
            r47 = (r47 * r124) + (this.val$fromY * (1.0f - r124));
            goto L116
        L109:
            if (this.val$fromHolder == false) goto L111;
            r19 = 2.0f;
            r8 = CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(this.this$0.animateInProgress);
            r124 = r123.getInterpolation(this.this$0.animateInProgress);
            goto L112
        L111:
            r19 = 2.0f;
            r8 = this.this$0.animateInProgress;
            r124 = r8;
            goto L112
        L98:
            if (this.val$isStories == true) goto L105;
            int r127 = this.this$0.loc[0];
            if (r27 >= r127) goto L103;
            r27 = r127;
        L103:
            if ((this.val$emojiSize + r27) <= (r127 + getMeasuredWidth())) goto L105;
            r27 = (this.this$0.loc[0] + getMeasuredWidth()) - this.val$emojiSize;
            goto L105
        L89:
            r27 = r14 - ((r45 - r63) / 2.0f);
            r46 = r45 - r63;
        L81:
            if (this.val$isStories == true) goto L85;
            if ((r25 instanceof GroupCallMessageCell) == true) goto L85;
            return;
        L46:
            if (r15.shouldDrawTimeOnMedia() == true) goto L48;
            r132 = r132 + AndroidUtilities.dp(2.0f);
            goto L48
        L50:
            r16 = 0.10666667f;
            if ((r25 instanceof ChatActionCell) == false) goto L54;
            r122 = ((ChatActionCell) r25).getReactionButton(ReactionsEffectOverlay.m12367$$Nest$fgetreaction(r12));
            r4 = r25.getPaddingTop();
            goto L49
        L54:
            if ((r25 instanceof GroupCallMessageCell) == false) goto L56;
            r14 = r14 + ((GroupCallMessageCell) r25).getReactionCenterX();
            r132 = r132 + (r25.getMeasuredHeight() / 2.0f);
        L56:
            r122 = null;
            goto L57
        L63:
            r16 = 0.10666667f;
            if (this.val$isStories == false) goto L66;
            float r128 = r63 / 2.0f;
            r14 = (getMeasuredWidth() / 2.0f) - r128;
            r132 = (getMeasuredHeight() / 2.0f) - r128;
            goto L67
        L66:
            r14 = ReactionsEffectOverlay.m12363$$Nest$fgetlastDrawnToX(this.this$0);
            r132 = ReactionsEffectOverlay.m12364$$Nest$fgetlastDrawnToY(this.this$0);
            goto L67
        L35:
            if (r6.shouldDrawReactionsInLayout() == false) goto L38;
            r62 = AndroidUtilities.dp(20.0f);
            goto L37
        L28:
            if (SharedConfig.deviceIsHigh() == false) goto L31;
            float r69 = 120.0f;
        L30:
            r62 = AndroidUtilities.dp(r69);
            goto L37
        L31:
            r69 = 50.0f;
            goto L30
        L24:
            r25 = this.val$cell;
            goto L26
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            int r0 = 0;
        L4:
            if (r0 >= this.this$0.avatars.size()) goto L6;
            this.this$0.avatars.get(r0).imageReceiver.onAttachedToWindow();
            r0 = r0 + 1;
            goto L4
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            int r0 = 0;
        L4:
            if (r0 >= this.this$0.avatars.size()) goto L6;
            this.this$0.avatars.get(r0).imageReceiver.onDetachedFromWindow();
            r0 = r0 + 1;
            goto L4
        }
    }

    public class AnimationView extends BackupImageView {
        AnimatedEmojiDrawable animatedEmojiDrawable;
        boolean attached;
        AnimatedEmojiEffect emojiEffect;
        final /* synthetic */ ReactionsEffectOverlay this$0;
        boolean wasPlaying;

        public AnimationView(ReactionsEffectOverlay r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            getImageReceiver().setFileLoadingPriority(3);
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.attached = true;
            AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
            if (r0 == null) goto L5;
            r0.addView(this);
        L5:
            AnimatedEmojiEffect r02 = this.emojiEffect;
            if (r02 == null) goto L9;
            r02.setView(this);
            return;
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.attached = false;
            AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
            if (r0 == null) goto L5;
            r0.removeView(this);
        L5:
            AnimatedEmojiEffect r02 = this.emojiEffect;
            if (r02 == null) goto L9;
            r02.removeView(this);
            return;
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDraw(Canvas r6) {
            AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
            if (r0 == null) goto L6;
            r0.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            this.animatedEmojiDrawable.setAlpha(255);
            this.animatedEmojiDrawable.draw(r6);
            this.wasPlaying = true;
            return;
        L6:
            AnimatedEmojiEffect r02 = this.emojiEffect;
            if (r02 == null) goto L11;
            r02.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            this.emojiEffect.draw(r6);
            this.wasPlaying = true;
            return;
        L11:
            if (getImageReceiver().getLottieAnimation() == null) goto L16;
            if (getImageReceiver().getLottieAnimation().isRunning() == false) goto L16;
            this.wasPlaying = true;
        L16:
            if (this.wasPlaying == false) goto L18;
        L27:
            super.onDraw(r6);
            return;
        L18:
            if (getImageReceiver().getLottieAnimation() == null) goto L27;
            if (getImageReceiver().getLottieAnimation().isRunning() == true) goto L27;
            if (ReactionsEffectOverlay.m12355$$Nest$fgetanimationType(this.this$0) == 2) goto L24;
        L26:
            getImageReceiver().getLottieAnimation().setCurrentFrame(0, false);
            getImageReceiver().getLottieAnimation().start();
            goto L27
        L24:
            if (this.this$0.isStories == true) goto L26;
            getImageReceiver().getLottieAnimation().setCurrentFrame(getImageReceiver().getLottieAnimation().getFramesCount() - 1, false);
            goto L27
        }

        public void setAnimatedEmojiEffect(AnimatedEmojiEffect r1) {
            this.emojiEffect = r1;
        }

        public void setAnimatedReactionDrawable(AnimatedEmojiDrawable r2) {
            if (r2 == null) goto L4;
            r2.removeView(this);
        L4:
            this.animatedEmojiDrawable = r2;
            if (this.attached == false) goto L9;
            if (r2 == null) goto L10;
            r2.addView(this);
            return;
        L10:
            return;
        }
    }

    public class AvatarParticle {
        float currentRotation;
        float fromX;
        float fromY;
        float globalTranslationY;
        ImageReceiver imageReceiver;
        boolean incrementRotation;
        float jumpY;
        public int leftTime;
        float outProgress;
        float progress;
        float randomRotation;
        float randomScale;
        final /* synthetic */ ReactionsEffectOverlay this$0;
        float toX;
        float toY;

        private AvatarParticle(ReactionsEffectOverlay r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ AvatarParticle(ReactionsEffectOverlay r1, ReactionsEffectOverlayIA r2) {
            this(r1);
        }
    }

    /* renamed from: -$$Nest$fgetanimationType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12355$$Nest$fgetanimationType(ReactionsEffectOverlay r0) {
        return r0.animationType;
    }

    /* renamed from: -$$Nest$fgetcontainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m12356$$Nest$fgetcontainer(ReactionsEffectOverlay r0) {
        return r0.container;
    }

    /* renamed from: -$$Nest$fgetdismissProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12357$$Nest$fgetdismissProgress(ReactionsEffectOverlay r0) {
        return r0.dismissProgress;
    }

    /* renamed from: -$$Nest$fgetdismissed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12358$$Nest$fgetdismissed(ReactionsEffectOverlay r0) {
        return r0.dismissed;
    }

    /* renamed from: -$$Nest$fgeteffectImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationView m12359$$Nest$fgeteffectImageView(ReactionsEffectOverlay r0) {
        return r0.effectImageView;
    }

    /* renamed from: -$$Nest$fgetemojiImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationView m12360$$Nest$fgetemojiImageView(ReactionsEffectOverlay r0) {
        return r0.emojiImageView;
    }

    /* renamed from: -$$Nest$fgetemojiStaticImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationView m12361$$Nest$fgetemojiStaticImageView(ReactionsEffectOverlay r0) {
        return r0.emojiStaticImageView;
    }

    /* renamed from: -$$Nest$fgetholderView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ReactionsContainerLayout.ReactionHolderView m12362$$Nest$fgetholderView(ReactionsEffectOverlay r0) {
        return r0.holderView;
    }

    /* renamed from: -$$Nest$fgetlastDrawnToX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12363$$Nest$fgetlastDrawnToX(ReactionsEffectOverlay r0) {
        return r0.lastDrawnToX;
    }

    /* renamed from: -$$Nest$fgetlastDrawnToY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12364$$Nest$fgetlastDrawnToY(ReactionsEffectOverlay r0) {
        return r0.lastDrawnToY;
    }

    /* renamed from: -$$Nest$fgetmessageId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12365$$Nest$fgetmessageId(ReactionsEffectOverlay r0) {
        return r0.messageId;
    }

    /* renamed from: -$$Nest$fgetnextReactionOverlay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ReactionsEffectOverlay m12366$$Nest$fgetnextReactionOverlay(ReactionsEffectOverlay r0) {
        return r0.nextReactionOverlay;
    }

    /* renamed from: -$$Nest$fgetreaction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ReactionsLayoutInBubble.VisibleReaction m12367$$Nest$fgetreaction(ReactionsEffectOverlay r0) {
        return r0.reaction;
    }

    /* renamed from: -$$Nest$fgetwasScrolled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12368$$Nest$fgetwasScrolled(ReactionsEffectOverlay r0) {
        return r0.wasScrolled;
    }

    /* renamed from: -$$Nest$fputdismissProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12369$$Nest$fputdismissProgress(ReactionsEffectOverlay r0, float r1) {
        r0.dismissProgress = r1;
    }

    /* renamed from: -$$Nest$fputlastDrawnToX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12370$$Nest$fputlastDrawnToX(ReactionsEffectOverlay r0, float r1) {
        r0.lastDrawnToX = r1;
    }

    /* renamed from: -$$Nest$fputlastDrawnToY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12371$$Nest$fputlastDrawnToY(ReactionsEffectOverlay r0, float r1) {
        r0.lastDrawnToY = r1;
    }

    /* renamed from: -$$Nest$mremoveCurrentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12372$$Nest$mremoveCurrentView(ReactionsEffectOverlay r0) {
        r0.removeCurrentView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v36 */
    /* JADX WARN: Type inference failed for: r7v37 */
    /* JADX WARN: Type inference failed for: r7v38, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r7v55 */
    public ReactionsEffectOverlay(Context r36, BaseFragment r37, ReactionsContainerLayout r38, View r39, View r40, float r41, float r42, ReactionsLayoutInBubble.VisibleReaction r43, int r44, int r45, boolean r46) {
        this.loc = new int[2];
        this.holderView = null;
        this.holderView2 = null;
        this.avatars = new ArrayList();
        this.isStories = r46;
        boolean r3 = r39 instanceof ChatMessageCell;
        if (r3 == false) goto L6;
        MessageObject r8 = ((ChatMessageCell) r39).getMessageObject();
        this.messageId = r8.getId();
        this.groupId = r8.getGroupId();
    L9:
        this.reaction = r43;
        this.animationType = r45;
        this.currentAccount = r44;
        this.cell = r39;
        if (r3 == false) goto L13;
        ReactionsLayoutInBubble.ReactionButton r32 = ((ChatMessageCell) r39).getReactionButton(r43);
    L16:
        if (r46 == false) goto L19;
        if (r45 != 2) goto L19;
        View r7 = r40;
        ReactionsLayoutInBubble.ReactionButton r14 = r32;
        long r18 = 0;
        MessageObject r15 = r8;
        ReactionsContainerLayout r5 = r38;
        View r6 = r39;
        BaseFragment r4 = r37;
        ReactionsEffectOverlay r2 = new ReactionsEffectOverlay(r36, r4, r5, r6, r7, r41, r42, r43, r44, 1, true);
        Context r22 = r36;
        int r33 = r44;
        this.nextReactionOverlay = r2;
        currentShortOverlay = r2;
    L21:
        if ((r4 instanceof ChatActivity) == false) goto L23;
        ChatActivity r82 = (ChatActivity) r4;
    L24:
        if (r5 == null) goto L34;
        int r9 = 0;
    L27:
        if (r9 >= r5.recyclerListView.getChildCount()) goto L34;
        if ((r5.recyclerListView.getChildAt(r9) instanceof ReactionsContainerLayout.ReactionHolderView) == false) goto L33;
        if (((ReactionsContainerLayout.ReactionHolderView) r5.recyclerListView.getChildAt(r9)).currentReaction.equals(this.reaction) == false) goto L33;
        this.holderView = (ReactionsContainerLayout.ReactionHolderView) r5.recyclerListView.getChildAt(r9);
    L33:
        r9 = r9 + 1;
    L34:
        int r10 = 1;
        if (r45 != 1) goto L86;
        Random r11 = new Random();
        if (r15 == null) goto L41;
        TLRPC.TL_messageReactions r12 = r15.messageOwner.reactions;
        if (r12 == null) goto L41;
        ArrayList<TLRPC.MessagePeerReaction> r122 = r12.recent_reactions;
    L42:
        if (r122 == null) goto L86;
        if (r82 == null) goto L86;
        if (r82.getDialogId() >= r18) goto L86;
        float r382 = 0.8f;
        int r13 = 0;
    L48:
        if (r13 >= r122.size()) goto L84;
        if (this.reaction.equals(r122.get(r13).reaction) == true) goto L52;
    L82:
        ChatActivity r30 = r82;
        int r222 = r10;
        Random r20 = r11;
    L83:
        r13 = r13 + 1;
        r33 = r44;
        r11 = r20;
        r10 = r222;
        r82 = r30;
        goto L48
    L52:
        if (r122.get(r13).unread == false) goto L82;
        AvatarDrawable r52 = new AvatarDrawable();
        ImageReceiver r92 = new ImageReceiver();
        r222 = r10;
        r20 = r11;
        long r102 = MessageObject.getPeerId(r122.get(r13).peer_id);
        if (r102 >= r18) goto L59;
        TLRPC.Chat r47 = MessagesController.getInstance(r33).getChat(Long.valueOf(-r102));
        if (r47 != null) goto L58;
    L57:
        r30 = r82;
        goto L83
    L58:
        r52.setInfo(r33, r47);
        r92.setForUserOrChat(r47, r52);
    L63:
        AvatarParticle r48 = new AvatarParticle(this, null);
        r48.imageReceiver = r92;
        r48.fromX = 0.5f;
        r48.fromY = 0.5f;
        r48.jumpY = ((Math.abs(r20.nextInt() % 100) / 100.0f) * 0.1f) + 0.3f;
        r48.randomScale = ((Math.abs(r20.nextInt() % 100) / 100.0f) * 0.4f) + 0.8f;
        r48.randomRotation = (Math.abs(r20.nextInt() % 100) * 60) / 100.0f;
        r48.leftTime = (int) (((Math.abs(r20.nextInt() % 100) / 100.0f) * 200.0f) + 400.0f);
        if (this.avatars.isEmpty() == false) goto L66;
        r48.toX = ((Math.abs(r20.nextInt() % 100) * 0.6f) / 100.0f) + 0.2f;
        r48.toY = (Math.abs(r20.nextInt() % 100) * 0.4f) / 100.0f;
        r30 = r82;
    L81:
        this.avatars.add(r48);
        goto L83
    L66:
        float r53 = 0.0f;
        float r103 = 0.0f;
        float r26 = 0.0f;
        int r93 = 0;
    L68:
        if (r93 >= 10) goto L80;
        float r112 = ((Math.abs(r20.nextInt() % 100) * 0.6f) / 100.0f) + 0.2f;
        float r34 = ((Math.abs(r20.nextInt() % 100) * 0.4f) / 100.0f) + 0.2f;
        float r28 = 2.1474836E9f;
        ChatActivity r302 = r82;
        int r35 = 0;
    L71:
        if (r35 >= this.avatars.size()) goto L77;
        float r83 = this.avatars.get(r35).toX - r112;
        float r84 = this.avatars.get(r35).toY - r34;
        float r31 = (r83 * r83) + (r84 * r84);
        if (r31 >= r28) goto L75;
        r28 = r31;
    L75:
        r35 = r35 + 1;
        goto L71
    L77:
        if (r28 <= r26) goto L79;
        r53 = r112;
        r26 = r28;
        r103 = r34;
    L79:
        r93 = r93 + 1;
        r82 = r302;
        goto L68
    L80:
        r30 = r82;
        r48.toX = r53;
        r48.toY = r103;
        goto L81
    L59:
        TLRPC.User r49 = MessagesController.getInstance(r33).getUser(Long.valueOf(r102));
        if (r49 == null) goto L57;
        r52.setInfo(r33, r49);
        r92.setForUserOrChat(r49, r52);
        goto L63
    L84:
        ChatActivity r303 = r82;
        int r223 = r10;
    L87:
        ReactionsContainerLayout.ReactionHolderView r310 = this.holderView;
        if (r310 == null) goto L90;
    L95:
        boolean r104 = r223;
    L97:
        if (r7 == null) goto L106;
        r7.getLocationOnScreen(this.loc);
        int[] r311 = this.loc;
        float r54 = r311[0];
        float r312 = r311[r223];
        float r85 = r7.getWidth() * r7.getScaleX();
        if ((r7 instanceof SelectAnimatedEmojiDialog.ImageViewEmoji) == false) goto L103;
        float r94 = ((SelectAnimatedEmojiDialog.ImageViewEmoji) r7).bigReactionSelectedProgress;
        if (r94 <= 0.0f) goto L103;
        r85 = r7.getWidth() * ((r94 * 2.0f) + 1.0f);
        r54 = r54 - ((r85 - r7.getWidth()) / 2.0f);
        r312 = r312 - (r85 - r7.getWidth());
    L103:
        float r132 = r312;
        float r123 = r54;
    L130:
        if (r45 != 2) goto L138;
        if (r46 == true) goto L133;
    L136:
        float r313 = 34.0f;
    L135:
        int r314 = AndroidUtilities.dp(r313);
        int r55 = (int) ((r314 * 2.0f) / AndroidUtilities.density);
        int r410 = r314;
        int r315 = r223;
    L154:
        float r72 = r85;
        int r86 = r410 >> 1;
        int r95 = r55 >> 1;
        float r113 = r72 / r86;
        this.animateInProgress = 0.0f;
        this.animateOutProgress = 0.0f;
        FrameLayout r73 = new FrameLayout(r22);
        this.container = r73;
        int r322 = r410;
        int r16 = r55;
        View r411 = r6;
        MessageObject r62 = r15;
        boolean r152 = r315;
        this.windowView = new AnonymousClass1(this, r22, r37, r411, r46, r62, r303, r86, r45, r104, r113, r123, r132, r43);
        AnimationView r0 = new AnimationView(this, r22);
        this.effectImageView = r0;
        AnimationView r316 = new AnimationView(this, r22);
        this.emojiImageView = r316;
        AnimationView r412 = new AnimationView(this, r22);
        this.emojiStaticImageView = r412;
        if (r43.emojicon == null) goto L157;
        TLRPC.TL_availableReaction r23 = MediaDataController.getInstance(r44).getReactionsMap().get(this.reaction.emojicon);
    L158:
        if (r23 == null) goto L160;
    L164:
        if (r23 == null) goto L194;
        int r63 = 2;
        if (r45 == 2) goto L183;
        if (r45 == r152) goto L169;
    L170:
        if (r45 != 0) goto L178;
    L171:
        if (r45 != r152) goto L173;
        TLRPC.Document r64 = r23.around_animation;
    L174:
        if (r45 != r152) goto L176;
        String r74 = getFilterForAroundAnimation();
    L177:
        ImageReceiver r105 = r0.getImageReceiver();
        StringBuilder r114 = new StringBuilder();
        int r124 = uniqPrefix;
        uniqPrefix = r124 + 1;
        r114.append(r124);
        r114.append("_");
        r114.append(this.messageId);
        r114.append("_");
        r105.setUniqKeyPrefix(r114.toString());
        r0.setImage(ImageLocation.getForDocument(r64), r74, null, null, 0, null);
        boolean r75 = false;
        r0.getImageReceiver().setAutoRepeat(0);
        r0.getImageReceiver().setAllowStartAnimation(false);
    L180:
        if (r0.getImageReceiver().getLottieAnimation() == null) goto L182;
        r0.getImageReceiver().getLottieAnimation().setCurrentFrame(r75 ? 1 : 0, r75);
        r0.getImageReceiver().getLottieAnimation().start();
    L182:
        r63 = 2;
        boolean r76 = r75;
    L184:
        if (r45 != r63) goto L191;
        if (r46 == false) goto L187;
        TLRPC.Document r65 = r23.select_animation;
    L188:
        ImageReceiver r106 = r316.getImageReceiver();
        StringBuilder r115 = new StringBuilder();
        int r125 = uniqPrefix;
        uniqPrefix = r125 + 1;
        r115.append(r125);
        r115.append("_");
        r115.append(this.messageId);
        r115.append("_");
        r106.setUniqKeyPrefix(r115.toString());
        r316.setImage(ImageLocation.getForDocument(r65), r95 + "_" + r95, null, null, 0, null);
    L189:
        int r126 = r86;
        ?? r77 = r76;
    L226:
        r316.getImageReceiver().setAutoRepeat(r77);
        r316.getImageReceiver().setAllowStartAnimation(r77);
        if (r316.getImageReceiver().getLottieAnimation() == null) goto L233;
        if (r45 != 2) goto L232;
        r316.getImageReceiver().getLottieAnimation().setCurrentFrame(r316.getImageReceiver().getLottieAnimation().getFramesCount() - (r152 ? 1 : 0), r77);
        goto L233
    L232:
        r316.getImageReceiver().getLottieAnimation().setCurrentFrame(r77, r77);
        r316.getImageReceiver().getLottieAnimation().start();
    L233:
        int r56 = r322 - r126;
        int r66 = r56 >> 1;
        if (r45 != r152) goto L236;
        r56 = r66;
    L236:
        r73.addView(r316);
        r316.getLayoutParams().width = r126;
        r316.getLayoutParams().height = r126;
        ((FrameLayout.LayoutParams) r316.getLayoutParams()).topMargin = r66;
        ((FrameLayout.LayoutParams) r316.getLayoutParams()).leftMargin = r56;
        if (r45 == r152) goto L242;
        if (r46 == true) goto L242;
        if (r23 == null) goto L241;
        r412.getImageReceiver().setImage(ImageLocation.getForDocument(r23.center_icon), "40_40_lastreactframe", null, "webp", r23, 1);
    L241:
        r73.addView(r412);
        r412.getLayoutParams().width = r126;
        r412.getLayoutParams().height = r126;
        ((FrameLayout.LayoutParams) r412.getLayoutParams()).topMargin = r66;
        ((FrameLayout.LayoutParams) r412.getLayoutParams()).leftMargin = r56;
    L242:
        this.windowView.addView(r73);
        r73.getLayoutParams().width = r322;
        r73.getLayoutParams().height = r322;
        int r317 = -r66;
        ((FrameLayout.LayoutParams) r73.getLayoutParams()).topMargin = r317;
        int r413 = -r56;
        ((FrameLayout.LayoutParams) r73.getLayoutParams()).leftMargin = r413;
        this.windowView.addView(r0);
        r0.getLayoutParams().width = r322;
        r0.getLayoutParams().height = r322;
        r0.getLayoutParams().width = r322;
        r0.getLayoutParams().height = r322;
        ((FrameLayout.LayoutParams) r0.getLayoutParams()).topMargin = r317;
        ((FrameLayout.LayoutParams) r0.getLayoutParams()).leftMargin = r413;
        r73.setPivotX(r56);
        r73.setPivotY(r66);
        return;
    L187:
        r65 = r23.appear_animation;
        goto L188
    L191:
        if (r45 != 0) goto L189;
        TLRPC.Document r67 = r23.activate_animation;
        ImageReceiver r107 = r316.getImageReceiver();
        StringBuilder r127 = new StringBuilder();
        int r133 = uniqPrefix;
        uniqPrefix = r133 + 1;
        r127.append(r133);
        r127.append("_");
        r127.append(this.messageId);
        r127.append("_");
        r107.setUniqKeyPrefix(r127.toString());
        r316.setImage(ImageLocation.getForDocument(r67), r95 + "_" + r95, null, null, 0, null);
        goto L189
    L176:
        r74 = r16 + "_" + r16;
        goto L177
    L173:
        r64 = r23.effect_animation;
        goto L174
    L178:
        r75 = false;
        goto L180
    L169:
        if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_CHAT) == true) goto L171;
    L183:
        r76 = false;
        goto L184
    L194:
        if (r45 != 0) goto L196;
        int r134 = r44;
        r316.setAnimatedReactionDrawable(new AnimatedEmojiDrawable(r152 ? 1 : 0, r134, r43.documentId));
        r126 = r86;
        int r57 = 2;
    L200:
        if (r45 == 0) goto L204;
        if (r45 == r152) goto L204;
        r77 = 0;
    L204:
        AnimatedEmojiDrawable r78 = new AnimatedEmojiDrawable(r57, r134, r43.documentId);
        if (r62 != null) goto L207;
        int r58 = -1;
    L221:
        r78.setColorFilter(new PorterDuffColorFilter(r58, PorterDuff.Mode.SRC_IN));
        if (r45 != 0) goto L224;
        boolean r59 = r152 ? 1 : 0;
    L225:
        r0.setAnimatedEmojiEffect(AnimatedEmojiEffect.createFrom(r78, r59, !r59));
        r77 = 0;
        this.windowView.setClipChildren(false);
        goto L226
    L224:
        r59 = false;
        goto L225
    L207:
        if (r62.shouldDrawWithoutBackground() == false) goto L213;
        if (r62.isOutOwner() == false) goto L211;
        int r510 = Theme.key_chat_outReactionButtonBackground;
    L216:
        if (r37 == null) goto L218;
        Theme.ResourcesProvider r68 = r37.getResourceProvider();
    L219:
        r58 = Theme.getColor(r510, r68);
        goto L221
    L218:
        r68 = null;
        goto L219
    L211:
        r510 = Theme.key_chat_inReactionButtonBackground;
        goto L216
    L213:
        if (r62.isOutOwner() == false) goto L215;
        r510 = Theme.key_chat_outReactionButtonTextSelected;
        goto L216
    L215:
        r510 = Theme.key_chat_inReactionButtonTextSelected;
        goto L216
    L196:
        r134 = r44;
        r57 = 2;
        if (r45 != 2) goto L199;
        r126 = r86;
        r316.setAnimatedReactionDrawable(new AnimatedEmojiDrawable(2, r134, r43.documentId));
        goto L200
    L199:
        r126 = r86;
        goto L200
    L160:
        if (r43.documentId != r18) goto L164;
        this.dismissed = r152;
        return;
    L157:
        r23 = null;
        goto L158
    L133:
        if (SharedConfig.deviceIsHigh() == false) goto L136;
        r313 = 60.0f;
        goto L135
    L138:
        r315 = r223;
        if (r45 == r315) goto L141;
        int r414 = AndroidUtilities.dp(350.0f);
        Point r511 = AndroidUtilities.displaySize;
        r410 = Math.round(Math.min(r414, Math.min(r511.x, r511.y)) * r382);
        r55 = sizeForBigReaction();
        goto L154
    L141:
        if (r46 == true) goto L143;
        r410 = AndroidUtilities.dp(80.0f);
        r55 = sizeForAroundReaction();
        goto L154
    L143:
        if (SharedConfig.deviceIsHigh() == false) goto L146;
        float r79 = 240.0f;
    L145:
        int r710 = AndroidUtilities.dp(r79);
        if (SharedConfig.deviceIsHigh() == false) goto L150;
        int r415 = (int) ((AndroidUtilities.dp(80.0f) * 2.0f) / AndroidUtilities.density);
    L151:
        r55 = r415;
        r410 = r710;
        goto L154
    L150:
        r415 = sizeForAroundReaction();
        goto L151
    L146:
        r79 = 140.0f;
        goto L145
    L106:
        if (r310 == null) goto L109;
        r310.getLocationOnScreen(this.loc);
        float r318 = this.loc[0] + this.holderView.loopImageView.getX();
        float r512 = this.loc[r223] + this.holderView.loopImageView.getY();
        r85 = this.holderView.getScaleX() * this.holderView.loopImageView.getWidth();
    L108:
        r123 = r318;
        r132 = r512;
        goto L130
    L109:
        if (r14 == null) goto L122;
        r6.getLocationInWindow(this.loc);
        float r319 = this.loc[0];
        ImageReceiver r513 = r14.imageReceiver;
        if (r513 != null) goto L113;
        float r514 = 0.0f;
    L114:
        r318 = r319 + r514;
        float r515 = this.loc[r223];
        ImageReceiver r711 = r14.imageReceiver;
        if (r711 != null) goto L117;
        float r712 = 0.0f;
    L118:
        r512 = r515 + r712;
        ImageReceiver r713 = r14.imageReceiver;
        if (r713 != null) goto L121;
        r85 = 0.0f;
        goto L108
    L121:
        r85 = r713.getImageHeight();
        goto L108
    L117:
        r712 = r711.getImageY();
        goto L118
    L113:
        r514 = r513.getImageX();
        goto L114
    L122:
        if (r6 == null) goto L129;
        ((View) r6.getParent()).getLocationInWindow(this.loc);
        int[] r320 = this.loc;
        float r516 = r320[0] + r41;
        float r321 = r320[r223] + r42;
        if ((r6 instanceof ChatMessageCell) == false) goto L126;
        int r714 = ((ChatMessageCell) r6).starsPriceTopPadding;
    L127:
        r132 = r321 + r714;
        r123 = r516;
    L128:
        r85 = 0.0f;
        goto L130
    L126:
        r714 = 0;
        goto L127
    L129:
        r123 = r41;
        r132 = r42;
        goto L128
    L90:
        if (r41 != 0.0f) goto L92;
    L94:
        r104 = 0;
        goto L97
    L92:
        if (r42 == 0.0f) goto L94;
    L41:
        r122 = null;
    L86:
        r303 = r82;
        r223 = 1;
        r382 = 0.8f;
        goto L87
    L23:
        r82 = null;
    L19:
        r22 = r36;
        r7 = r40;
        r14 = r32;
        r18 = 0;
        r15 = r8;
        r33 = r44;
        r5 = r38;
        r6 = r39;
        r4 = r37;
        goto L21
    L13:
        if ((r39 instanceof ChatActionCell) == false) goto L15;
        r32 = ((ChatActionCell) r39).getReactionButton(r43);
        goto L16
    L15:
        r32 = null;
        goto L16
    L6:
        if ((r39 instanceof ChatActionCell) == false) goto L8;
        r8 = ((ChatActionCell) r39).getMessageObject();
        this.messageId = r8.getId();
        this.groupId = 0;
        goto L9
    L8:
        this.messageId = 0;
        this.groupId = 0;
        r8 = null;
        goto L9
    }

    public static void dismissAll() {
        ReactionsEffectOverlay r0 = currentOverlay;
        if (r0 == null) goto L5;
        r0.dismissed = true;
    L5:
        ReactionsEffectOverlay r02 = currentShortOverlay;
        if (r02 == null) goto L9;
        r02.dismissed = true;
        return;
    }

    public static String getFilterForAroundAnimation() {
        return sizeForAroundReaction() + "_" + sizeForAroundReaction() + "_nolimit_pcache";
    }

    public static boolean isPlaying(int r6, long r7, ReactionsLayoutInBubble.VisibleReaction r9) {
        ReactionsEffectOverlay r0 = currentOverlay;
        if (r0 == null) goto L17;
        int r2 = r0.animationType;
        if (r2 == 2) goto L7;
        if (r2 != 0) goto L17;
    L7:
        long r22 = r0.groupId;
        if (r22 == 0) goto L12;
        if (r7 != r22) goto L12;
    L14:
        if (r0.reaction.equals(r9) == false) goto L17;
        return true;
    L12:
        if (r6 == r0.messageId) goto L14;
    L17:
        return false;
    }

    public static void onScrolled(int r3) {
        ReactionsEffectOverlay r0 = currentOverlay;
        if (r0 == null) goto L8;
        r0.lastDrawnToY -= r3;
        if (r3 == 0) goto L9;
        r0.wasScrolled = true;
        return;
    L9:
        return;
    }

    public static void removeCurrent(boolean r3) {
        int r0 = 0;
    L4:
        if (r0 >= 2) goto L13;
        if (r0 != 0) goto L7;
        ReactionsEffectOverlay r1 = currentOverlay;
    L8:
        if (r1 == null) goto L12;
        if (r3 == false) goto L11;
        r1.removeCurrentView();
        goto L12
    L11:
        r1.dismissed = true;
    L12:
        r0 = r0 + 1;
        goto L4
    L7:
        r1 = currentShortOverlay;
        goto L8
    L13:
        currentShortOverlay = null;
        currentOverlay = null;
    }

    private void removeCurrentView() {
        if (this.useWindow == false) goto L6;
        this.windowManager.removeView(this.windowView);     // Catch: Exception -> L8
        return;
    L6:
        AndroidUtilities.removeFromParent(this.windowView);     // Catch: Exception -> L8
        return;
    }

    public static void show(BaseFragment r16, ReactionsContainerLayout r17, View r18, View r19, float r20, float r21, ReactionsLayoutInBubble.VisibleReaction r22, int r23, int r24) {
        if (r18 == null) goto L76;
        if (r22 == null) goto L77;
        if (r16 != null) goto L7;
        return;
    L7:
        if (r16.getParentActivity() == null) goto L86;
        boolean r12 = r16 instanceof ChatActivity;
        if (r12 == false) goto L26;
        ChatActivity r1 = (ChatActivity) r16;
        if (ExteraConfig.getHideReactionsInChannels() == false) goto L16;
        if (r1.isChannel() == false) goto L16;
        return;
    L16:
        if (ExteraConfig.getHideReactionsInGroups() == false) goto L22;
        if (r1.isChannel() == true) goto L22;
        if (r1.getCurrentChat() == null) goto L22;
        return;
    L22:
        if (ExteraConfig.getHideReactionsInPrivateChats() == false) goto L26;
        if (r1.getCurrentUser() == null) goto L26;
        return;
    L26:
        boolean r13 = r18 instanceof ChatMessageCell;
        if (r13 == false) goto L43;
        ChatMessageCell r14 = (ChatMessageCell) r18;
        if (ExteraConfig.getHideReactionsInChannels() == false) goto L33;
        if (ChatObject.isChannelAndNotMegaGroup(r14.getCurrentChat()) == false) goto L33;
        return;
    L33:
        if (ExteraConfig.getHideReactionsInGroups() == false) goto L39;
        if (ChatObject.isChannelAndNotMegaGroup(r14.getCurrentChat()) == true) goto L39;
        if (r14.getCurrentChat() == null) goto L39;
        return;
    L39:
        if (ExteraConfig.getHideReactionsInPrivateChats() == false) goto L43;
        if (r14.getCurrentUser() == null) goto L43;
        return;
    L43:
        boolean r142 = true;
        if (MessagesController.getGlobalMainSettings().getBoolean("view_animations", true) == true) goto L47;
        return;
    L47:
        if (r24 == 2) goto L49;
        if (r24 == 0) goto L49;
    L50:
        ReactionsEffectOverlay r0 = new ReactionsEffectOverlay(r16.getParentActivity(), r16, r17, r18, r19, r20, r21, r22, r23, r24, false);
        if (r24 != 1) goto L53;
        currentShortOverlay = r0;
    L54:
        if (r12 == false) goto L63;
        ChatActivity r15 = (ChatActivity) r16;
        if (r24 == 0) goto L58;
        if (r24 != 2) goto L63;
    L58:
        ActionBarPopupWindow r110 = r15.scrimPopupWindow;
        if (r110 == null) goto L63;
        if (r110.isShowing() == false) goto L63;
    L64:
        r0.useWindow = r142;
        if (r142 == false) goto L67;
        WindowManager.LayoutParams r111 = new WindowManager.LayoutParams();
        r111.height = -1;
        r111.width = -1;
        r111.type = 1000;
        r111.flags = 65816;
        r111.format = -3;
        WindowManager r2 = r16.getParentActivity().getWindowManager();
        r0.windowManager = r2;
        AndroidUtilities.setPreferredMaxRefreshRate(r2, r0.windowView, r111);
        r0.windowManager.addView(r0.windowView, r111);
    L68:
        r18.invalidate();
        if (r13 == true) goto L71;
        return;
    L71:
        if (((ChatMessageCell) r18).getCurrentMessagesGroup() != null) goto L73;
        return;
    L73:
        if (r18.getParent() == null) goto L85;
        ((View) r18.getParent()).invalidate();
        return;
    L85:
        return;
    L67:
        FrameLayout r112 = (FrameLayout) r16.getParentActivity().getWindow().getDecorView();
        r0.decorView = r112;
        r112.addView(r0.windowView);
    L63:
        r142 = false;
        goto L64
    L53:
        currentOverlay = r0;
    L49:
        show(r16, null, r18, r19, 0.0f, 0.0f, r22, r23, 1);
        goto L50
    L86:
        return;
    L77:
        return;
    }

    public static int sizeForAroundReaction() {
        return (int) ((AndroidUtilities.dp(40.0f) * 2.0f) / AndroidUtilities.density);
    }

    public static int sizeForBigReaction() {
        int r0 = AndroidUtilities.dp(350.0f);
        Point r1 = AndroidUtilities.displaySize;
        return (int) (Math.round(Math.min(r0, Math.min(r1.x, r1.y)) * 0.7f) / AndroidUtilities.density);
    }

    public static void startAnimation() {
        ReactionsEffectOverlay r0 = currentOverlay;
        if (r0 == null) goto L10;
        r0.started = true;
        r0.startTime = System.currentTimeMillis();
        if (currentOverlay.animationType == 0) goto L7;
        return;
    L7:
        if ((System.currentTimeMillis() - lastHapticTime) <= 200) goto L21;
        lastHapticTime = System.currentTimeMillis();
        currentOverlay.cell.performHapticFeedback(3);
        return;
    L21:
        return;
    L10:
        startShortAnimation();
        ReactionsEffectOverlay r02 = currentShortOverlay;
        if (r02 == null) goto L22;
        View r1 = r02.cell;
        if ((r1 instanceof ChatMessageCell) == false) goto L17;
        ((ChatMessageCell) r1).reactionsLayoutInBubble.animateReaction(r02.reaction);
        return;
    L17:
        if ((r1 instanceof ChatActionCell) == false) goto L23;
        ((ChatActionCell) r1).reactionsLayoutInBubble.animateReaction(r02.reaction);
        return;
    L23:
        return;
    }

    public static void startShortAnimation() {
        ReactionsEffectOverlay r0 = currentShortOverlay;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.started == true) goto L15;
        r0.started = true;
        r0.startTime = System.currentTimeMillis();
        if (currentShortOverlay.animationType == 1) goto L9;
        return;
    L9:
        if ((System.currentTimeMillis() - lastHapticTime) <= 200) goto L17;
        lastHapticTime = System.currentTimeMillis();
        View r02 = currentShortOverlay.cell;
        if (r02 == null) goto L18;
        r02.performHapticFeedback(3);
        return;
    L18:
        return;
    L17:
        return;
    }
}
