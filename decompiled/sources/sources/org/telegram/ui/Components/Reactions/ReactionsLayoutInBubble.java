package org.telegram.ui.Components.Reactions;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.MessageDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.IMessageCell;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFileDrawable;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarsDrawable;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CounterView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Stars.StarsReactionsSheet;
import org.telegram.ui.recyclerview.ChatListItemAnimator;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes7.dex */
public class ReactionsLayoutInBubble {
    private static int animationUniq;
    private static final ButtonsComparator comparator = null;
    private static final Paint cutTagPaint = null;
    private static final Paint paint = null;
    private static int pointer;
    private static final Paint tagPaint = null;
    private static final TextPaint textPaint = null;
    private static final Comparator<TLObject> usersComparator = null;
    private int animateFromTotalHeight;
    public boolean animateHeight;
    private boolean animateMove;
    private boolean animateWidth;
    final HashMap<VisibleReaction, ImageReceiver> animatedReactions;
    boolean attached;
    int availableWidth;
    int currentAccount;
    public float drawServiceShaderBackground;
    public int fromWidth;
    private float fromX;
    private float fromY;
    public boolean hasPaidReaction;
    public boolean hasUnreadReactions;
    public int height;
    public boolean isEmpty;
    public boolean isSmall;
    private int lastDrawTotalHeight;
    HashMap<String, ReactionButton> lastDrawingReactionButtons;
    HashMap<String, ReactionButton> lastDrawingReactionButtonsTmp;
    private int lastDrawnWidth;
    private float lastDrawnX;
    private float lastDrawnY;
    public int lastLineX;
    ReactionButton lastSelectedButton;
    float lastX;
    float lastY;
    Runnable longPressRunnable;
    MessageObject messageObject;
    ArrayList<ReactionButton> outButtons;
    View parentView;
    public int positionOffsetY;
    boolean pressed;
    public ArrayList<ReactionButton> reactionButtons;
    private final ArrayList<Integer> reactionLineWidths;
    Theme.ResourcesProvider resourcesProvider;
    private boolean scrimDirection;
    private float scrimProgress;
    private final RectF scrimRect;
    private final Rect scrimRect2;
    private Integer scrimViewReaction;
    public boolean tags;
    public int totalHeight;
    private float touchSlop;
    private boolean wasDrawn;
    public int width;
    public int x;
    public int y;

    public static class ButtonsComparator implements Comparator<ReactionButton> {
        int currentAccount;
        long dialogId;

        private ButtonsComparator() {
        }

        public /* synthetic */ ButtonsComparator(ReactionsLayoutInBubbleIA r1) {
            this();
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(ReactionButton r1, ReactionButton r2) {
            return compare2(r1, r2);
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(ReactionButton r5, ReactionButton r6) {
            if (this.dialogId < 0) goto L22;
            boolean r4 = r5.paid;
            if (r4 == r6.paid) goto L9;
            if (r4 == false) goto L8;
            return -1;
        L8:
            return 1;
        L9:
            boolean r42 = r5.isSelected;
            if (r42 == r6.isSelected) goto L14;
            if (r42 == false) goto L13;
            return -1;
        L13:
            return 1;
        L14:
            if (r42 == false) goto L19;
            int r43 = r5.choosenOrder;
            int r0 = r6.choosenOrder;
            if (r43 == r0) goto L19;
            return r43 - r0;
        L19:
            int r44 = ReactionButton.m12380$$Nest$fgetreactionCount(r5).lastDrawnPosition;
            int r52 = ReactionButton.m12380$$Nest$fgetreactionCount(r6).lastDrawnPosition;
        L21:
            return r44 - r52;
        L22:
            boolean r45 = r5.paid;
            if (r45 == r6.paid) goto L27;
            if (r45 == false) goto L26;
            return -1;
        L26:
            return 1;
        L27:
            int r46 = r5.realCount;
            int r02 = r6.realCount;
            if (r46 != r02) goto L30;
            r44 = ReactionButton.m12380$$Nest$fgetreactionCount(r5).lastDrawnPosition;
            r52 = ReactionButton.m12380$$Nest$fgetreactionCount(r6).lastDrawnPosition;
            goto L21
        L30:
            return r02 - r46;
        }
    }

    public static class ReactionButton {
        public int animateFromWidth;
        public int animateFromX;
        public int animateFromY;
        public AnimatedEmojiDrawable animatedEmojiDrawable;
        int animatedEmojiDrawableColor;
        public int animationType;
        public boolean attached;
        AvatarsDrawable avatarsDrawable;
        int backgroundColor;
        public final ButtonBounce bounce;
        private final RectF bounds;
        public boolean choosen;
        public int choosenOrder;
        public int count;
        public String countText;
        public CounterView.CounterDrawable counterDrawable;
        private final int currentAccount;
        public boolean drawBgOnlyIfChosen;
        public boolean drawImage;
        Rect drawingImageRect;
        public int fromBackgroundColor;
        public int fromTagDotColor;
        public int fromTextColor;
        public boolean hasName;
        public int height;
        public ImageReceiver imageReceiver;
        public boolean inGroup;
        boolean isSelected;
        private final boolean isSmall;
        public boolean isTag;
        public String key;
        public int lastDrawnBackgroundColor;
        public int lastDrawnTagDotColor;
        public int lastDrawnTextColor;
        public boolean lastImageDrawn;
        private boolean lastScrimProgressDirection;
        public String name;
        public boolean paid;
        private final View parentView;
        private StarsReactionsSheet.Particles particles;
        public AnimatedEmojiDrawable previewAnimatedEmojiDrawable;
        public ImageReceiver previewImageReceiver;
        public TLRPC.Reaction reaction;
        private final TLRPC.ReactionCount reactionCount;
        public int realCount;
        private final RectF rect2;
        private final Theme.ResourcesProvider resourcesProvider;
        public AnimatedTextView.AnimatedTextDrawable scrimPreviewCounterDrawable;
        int serviceBackgroundColor;
        int serviceTextColor;
        private RLottieDrawable starDrawable;
        private final Drawable.Callback supercallback;
        private final Path tagPath;
        int textColor;
        public AnimatedTextView.AnimatedTextDrawable textDrawable;
        public int top;
        ArrayList<TLObject> users;
        VisibleReaction visibleReaction;
        public boolean wasDrawn;
        public int width;
        public int x;
        public int y;

        /* renamed from: org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble$ReactionButton$1, reason: invalid class name */
        public class AnonymousClass1 implements Drawable.Callback {
            final /* synthetic */ ReactionButton this$0;

            public AnonymousClass1(ReactionButton r1) {
                this.this$0 = r1;
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void invalidateDrawable(Drawable r2) {
                if (ReactionButton.m12379$$Nest$fgetparentView(this.this$0) == null) goto L12;
                ReactionButton.m12379$$Nest$fgetparentView(this.this$0).invalidate();
                ReactionButton r22 = this.this$0;
                if (r22.inGroup == true) goto L7;
                return;
            L7:
                if (ReactionButton.m12379$$Nest$fgetparentView(r22).getParent() != null) goto L9;
                return;
            L9:
                if ((ReactionButton.m12379$$Nest$fgetparentView(this.this$0).getParent().getParent() instanceof View) == false) goto L15;
                ((View) ReactionButton.m12379$$Nest$fgetparentView(this.this$0).getParent().getParent()).invalidate();
                return;
            L15:
                return;
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void scheduleDrawable(Drawable r2, Runnable r3, long r4) {
                if (ReactionButton.m12379$$Nest$fgetparentView(this.this$0) == null) goto L6;
                ReactionButton.m12379$$Nest$fgetparentView(this.this$0).scheduleDrawable(r2, r3, r4);
                return;
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void unscheduleDrawable(Drawable r2, Runnable r3) {
                if (ReactionButton.m12379$$Nest$fgetparentView(this.this$0) == null) goto L6;
                ReactionButton.m12379$$Nest$fgetparentView(this.this$0).unscheduleDrawable(r2, r3);
                return;
            }
        }

        /* renamed from: -$$Nest$fgetisSmall, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m12378$$Nest$fgetisSmall(ReactionButton r0) {
            return r0.isSmall;
        }

        /* renamed from: -$$Nest$fgetparentView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m12379$$Nest$fgetparentView(ReactionButton r0) {
            return r0.parentView;
        }

        /* renamed from: -$$Nest$fgetreactionCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.ReactionCount m12380$$Nest$fgetreactionCount(ReactionButton r0) {
            return r0.reactionCount;
        }

        public ReactionButton(ReactionButton r19, int r20, View r21, TLRPC.ReactionCount r22, boolean r23, boolean r24, Theme.ResourcesProvider r25) {
            this.drawImage = true;
            this.drawingImageRect = new Rect();
            this.bounds = new RectF();
            this.rect2 = new RectF();
            this.tagPath = new Path();
            AnonymousClass1 r7 = new AnonymousClass1(this);
            this.supercallback = r7;
            this.currentAccount = r20;
            this.parentView = r21;
            this.bounce = new ButtonBounce(r21);
            this.resourcesProvider = r25;
            this.isTag = r24;
            if (r19 == null) goto L6;
            this.counterDrawable = r19.counterDrawable;
        L6:
            if (this.imageReceiver != null) goto L9;
            this.imageReceiver = new ImageReceiver();
        L9:
            if (this.counterDrawable != null) goto L12;
            this.counterDrawable = new CounterView.CounterDrawable(r21, false, null);
        L12:
            if (this.textDrawable != null) goto L15;
            AnimatedTextView.AnimatedTextDrawable r11 = new AnimatedTextView.AnimatedTextDrawable(true, true, true);
            this.textDrawable = r11;
            r11.ignoreRTL = true;
            r11.setAnimationProperties(0.4f, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.textDrawable.setTextSize(AndroidUtilities.dp(13.0f));
            this.textDrawable.setCallback(r7);
            this.textDrawable.setTypeface(AndroidUtilities.bold());
            this.textDrawable.setOverrideFullWidth(AndroidUtilities.displaySize.x);
        L15:
            if (this.scrimPreviewCounterDrawable != null) goto L17;
            AnimatedTextView.AnimatedTextDrawable r8 = new AnimatedTextView.AnimatedTextDrawable(false, false, false, true);
            this.scrimPreviewCounterDrawable = r8;
            r8.setTextSize(AndroidUtilities.dp(12.0f));
            this.scrimPreviewCounterDrawable.setCallback(r7);
            this.scrimPreviewCounterDrawable.setTypeface(AndroidUtilities.bold());
            this.scrimPreviewCounterDrawable.setOverrideFullWidth(AndroidUtilities.displaySize.x);
            this.scrimPreviewCounterDrawable.setScaleProperty(0.35f);
        L17:
            this.reactionCount = r22;
            TLRPC.Reaction r72 = r22.reaction;
            this.reaction = r72;
            this.visibleReaction = VisibleReaction.fromTL(r72);
            int r73 = r22.count;
            this.count = r73;
            this.choosen = r22.chosen;
            this.realCount = r73;
            this.choosenOrder = r22.chosen_order;
            this.isSmall = r23;
            TLRPC.Reaction r74 = this.reaction;
            if ((r74 instanceof TLRPC.TL_reactionPaid) == false) goto L21;
            this.key = "stars";
        L26:
            this.imageReceiver.setParentView(r21);
            this.isSelected = r22.chosen;
            CounterView.CounterDrawable r3 = this.counterDrawable;
            r3.updateVisibility = false;
            r3.shortFormat = true;
            if (this.reaction == null) goto L57;
            VisibleReaction r32 = this.visibleReaction;
            if (r32.isStar == false) goto L50;
            this.paid = true;
            if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS) == false) goto L38;
            if (r19 == null) goto L36;
            RLottieDrawable r33 = r19.starDrawable;
            if (r33 == null) goto L36;
            this.starDrawable = r33;
        L37:
            this.imageReceiver.setImageBitmap(this.starDrawable);
        L39:
            if (r19 == null) goto L44;
            StarsReactionsSheet.Particles r1 = r19.particles;
            if (r1 == null) goto L44;
        L48:
            this.particles = r1;
        L44:
            if (SharedConfig.getDevicePerformanceClass() != 2) goto L46;
            int r34 = 18;
        L47:
            r1 = new StarsReactionsSheet.Particles(1, r34);
            goto L48
        L46:
            r34 = 8;
        L36:
            this.starDrawable = new RLottieDrawable(R.raw.star_reaction_click, AndroidUtilities.dp(40.0f), AndroidUtilities.dp(40.0f));
            goto L37
        L38:
            this.imageReceiver.setImageBitmap(ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.star_reaction).mutate());
            goto L39
        L50:
            if (r32.emojicon == null) goto L55;
            TLRPC.TL_availableReaction r12 = MediaDataController.getInstance(r20).getReactionsMap().get(this.visibleReaction.emojicon);
            if (r12 == null) goto L57;
            this.imageReceiver.setImage(ImageLocation.getForDocument(r12.center_icon), "40_40_lastreactframe", DocumentObject.getSvgThumb(r12.static_icon, Theme.key_windowBackgroundGray, 1.0f), "webp", r12, 1);
            goto L57
        L55:
            if (r32.documentId == 0) goto L57;
            this.animatedEmojiDrawable = new AnimatedEmojiDrawable(getCacheType(), r20, this.visibleReaction.documentId);
        L57:
            this.counterDrawable.setSize(AndroidUtilities.dp(26.0f), AndroidUtilities.dp(100.0f));
            this.counterDrawable.textPaint = ReactionsLayoutInBubble.m12376$$Nest$sfgettextPaint();
            if (r24 == false) goto L60;
            this.name = MessagesController.getInstance(r20).getSavedTagName(this.reaction);
            this.hasName = !TextUtils.isEmpty(r1);
        L60:
            boolean r13 = this.hasName;
            AnimatedTextView.AnimatedTextDrawable r2 = this.textDrawable;
            if (r13 == false) goto L66;
            r2.setText(Emoji.replaceEmoji(this.name, r2.getPaint().getFontMetricsInt(), false), !LocaleController.isRTL);
            if (drawTextWithCounter() == false) goto L65;
            this.countText = Integer.toString(r22.count);
            this.counterDrawable.setCount(this.count, false);
        L69:
            this.counterDrawable.setType(2);
            this.counterDrawable.gravity = 3;
            return;
        L65:
            this.countText = _UrlKt.FRAGMENT_ENCODE_SET;
            this.counterDrawable.setCount(0, false);
            goto L69
        L66:
            if (r2 == null) goto L68;
            r2.setText(_UrlKt.FRAGMENT_ENCODE_SET, false);
        L68:
            this.countText = Integer.toString(r22.count);
            this.counterDrawable.setCount(this.count, false);
            goto L69
        L21:
            if ((r74 instanceof TLRPC.TL_reactionEmoji) == false) goto L24;
            this.key = ((TLRPC.TL_reactionEmoji) r74).emoticon;
            goto L26
        L24:
            if ((r74 instanceof TLRPC.TL_reactionCustomEmoji) == false) goto L71;
            this.key = Long.toString(((TLRPC.TL_reactionCustomEmoji) r74).document_id);
            goto L26
        L71:
            GlShader$$ExternalSyntheticBUOutline1.m("unsupported");
            throw null;
        }

        private void drawImage(Canvas r8, Rect r9, float r10) {
            AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
            if (r0 != null) goto L5;
        L7:
            ImageReceiver r02 = this.imageReceiver;
        L8:
            if (r02 == null) goto L11;
            if (r9 == null) goto L11;
            r02.setImageCoords(r9);
        L11:
            AnimatedEmojiDrawable r92 = this.animatedEmojiDrawable;
            if (r92 != null) goto L14;
        L16:
            boolean r1 = false;
            if (this.drawImage == true) goto L19;
        L54:
            r02.setAlpha(0.0f);
            r02.draw(r8);
            this.lastImageDrawn = false;
            return;
        L19:
            if (this.paid == false) goto L21;
        L26:
            ImageReceiver r93 = getImageReceiver();
            if (r93 != null) goto L29;
            r1 = true;
        L50:
            if (r1 == false) goto L52;
            r02.draw(r8);
        L52:
            this.lastImageDrawn = true;
            return;
        L29:
            if (r93.getLottieAnimation() != null) goto L31;
        L33:
            r1 = true;
        L35:
            if (r10 == 1.0f) goto L40;
            r93.setAlpha(r10);
            if (r10 > 0.0f) goto L48;
            r93.onDetachedFromWindow();
            removeImageReceiver();
        L48:
            r93.setImageCoords(r02.getImageX() - (r02.getImageWidth() / 2.0f), r02.getImageY() - (r02.getImageWidth() / 2.0f), r02.getImageWidth() * 2.0f, r02.getImageHeight() * 2.0f);
            r93.draw(r8);
            goto L50
        L40:
            if (r93.getLottieAnimation() == null) goto L48;
            if (r93.getLottieAnimation().isRunning() == true) goto L48;
            float r102 = r93.getAlpha() - 0.08f;
            if (r102 > 0.0f) goto L46;
            r93.onDetachedFromWindow();
            removeImageReceiver();
        L47:
            this.parentView.invalidate();
            r1 = true;
            goto L48
        L46:
            r93.setAlpha(r102);
            goto L47
        L31:
            if (r93.getLottieAnimation().hasBitmap() == false) goto L33;
        L21:
            if (this.realCount > 1) goto L26;
            if (isPlaying() == false) goto L26;
            if (this.isSelected == true) goto L54;
        L14:
            if (this.animatedEmojiDrawableColor == this.lastDrawnTextColor) goto L16;
            int r2 = this.lastDrawnTextColor;
            this.animatedEmojiDrawableColor = r2;
            r92.setColorFilter(new PorterDuffColorFilter(r2, PorterDuff.Mode.SRC_IN));
            goto L16
        L5:
            if (r0.getImageReceiver() == null) goto L7;
            r02 = this.animatedEmojiDrawable.getImageReceiver();
            goto L8
        }

        private void drawRoundRect(Canvas r3, RectF r4, float r5, Paint r6) {
            if (this.isTag == false) goto L15;
            RectF r52 = this.bounds;
            if (r52.left == r4.left) goto L7;
        L12:
            r52.set(r4);
            ReactionsLayoutInBubble.fillTagPath(this.bounds, this.rect2, this.tagPath);
        L13:
            r3.drawPath(this.tagPath, r6);
            return;
        L7:
            if (r52.top != r4.top) goto L12;
            if (r52.right != r4.right) goto L12;
            if (r52.bottom == r4.bottom) goto L13;
        L15:
            r3.drawRoundRect(r4, r5, r5, r6);
        }

        public void attach() {
            this.attached = true;
            ImageReceiver r0 = this.imageReceiver;
            if (r0 == null) goto L5;
            r0.onAttachedToWindow();
        L5:
            AvatarsDrawable r02 = this.avatarsDrawable;
            if (r02 == null) goto L8;
            r02.onAttachedToWindow();
        L8:
            AnimatedEmojiDrawable r03 = this.animatedEmojiDrawable;
            if (r03 == null) goto L12;
            r03.addView(this.parentView);
            return;
        }

        public void attachPreview(View r9) {
            if (this.previewImageReceiver == null) goto L5;
            return;
        L5:
            if (this.previewAnimatedEmojiDrawable != null) goto L35;
            View r92 = this.parentView;
            if (r92 != null) goto L10;
        L12:
            View r93 = this.parentView;
        L14:
            if (this.reaction == null) goto L31;
            VisibleReaction r0 = this.visibleReaction;
            if (r0.isStar == false) goto L19;
            return;
        L19:
            if (r0.emojicon == null) goto L27;
            TLRPC.TL_availableReaction r6 = MediaDataController.getInstance(this.currentAccount).getReactionsMap().get(this.visibleReaction.emojicon);
            if (r6 != null) goto L23;
            return;
        L23:
            if (r6.activate_animation == null) goto L33;
            SvgHelper.SvgDrawable r4 = DocumentObject.getSvgThumb(r6.static_icon, Theme.key_windowBackgroundGray, 1.0f);
            ImageReceiver r02 = new ImageReceiver(r93);
            this.previewImageReceiver = r02;
            r02.setLayerNum(7);
            this.previewImageReceiver.onAttachedToWindow();
            this.previewImageReceiver.setRoundRadius(AndroidUtilities.dp(14.0f));
            this.previewImageReceiver.setAllowStartLottieAnimation(true);
            this.previewImageReceiver.setAllowStartAnimation(true);
            this.previewImageReceiver.setAutoRepeat(1);
            this.previewImageReceiver.setAllowDecodeSingleFrame(true);
            this.previewImageReceiver.setImage(ImageLocation.getForDocument(r6.activate_animation), "140_140", r4, null, r6, 1);
            return;
        L33:
            return;
        L27:
            if (r0.documentId == 0) goto L34;
            AnimatedEmojiDrawable r03 = new AnimatedEmojiDrawable(24, this.currentAccount, this.visibleReaction.documentId);
            this.previewAnimatedEmojiDrawable = r03;
            r03.addView(r93);
            return;
        L34:
            return;
        L31:
            return;
        L10:
            if ((r92.getParent() instanceof View) == false) goto L12;
            r93 = (View) this.parentView.getParent();
            goto L14
        }

        public void detach() {
            this.attached = false;
            ImageReceiver r0 = this.imageReceiver;
            if (r0 == null) goto L5;
            r0.onDetachedFromWindow();
        L5:
            AvatarsDrawable r02 = this.avatarsDrawable;
            if (r02 == null) goto L8;
            r02.onDetachedFromWindow();
        L8:
            AnimatedEmojiDrawable r03 = this.animatedEmojiDrawable;
            if (r03 == null) goto L11;
            r03.removeView(this.parentView);
        L11:
            detachPreview();
        }

        public void detachPreview() {
            ImageReceiver r0 = this.previewImageReceiver;
            if (r0 != null) goto L8;
            if (this.previewAnimatedEmojiDrawable != null) goto L8;
            return;
        L8:
            if (r0 == null) goto L12;
            r0.onDetachedFromWindow();
            this.previewImageReceiver = null;
            return;
        L12:
            if (this.previewAnimatedEmojiDrawable == null) goto L21;
            View r02 = this.parentView;
            if (r02 != null) goto L16;
        L18:
            View r03 = this.parentView;
        L19:
            this.previewAnimatedEmojiDrawable.removeView(r03);
            this.previewAnimatedEmojiDrawable = null;
            return;
        L16:
            if ((r02.getParent() instanceof View) == false) goto L18;
            r03 = (View) this.parentView.getParent();
            goto L19
        }

        public void draw(Canvas r26, float r27, float r28, float r29, float r30, boolean r31, boolean r32, float r33) {
            this.wasDrawn = true;
            AnimatedEmojiDrawable r8 = this.animatedEmojiDrawable;
            if (r8 == null) goto L5;
            ImageReceiver r82 = r8.getImageReceiver();
        L7:
            if (this.isSmall == false) goto L11;
            if (r82 == null) goto L11;
            r82.setAlpha(r30);
            this.drawingImageRect.set((int) r27, (int) r28, AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f));
            r82.setImageCoords(this.drawingImageRect);
            r82.setRoundRadius(0);
            drawImage(r26, this.drawingImageRect, r30);
            return;
        L11:
            boolean r9 = this.choosen;
            boolean r11 = this.paid;
            if (r9 == false) goto L35;
            if (r11 == false) goto L16;
            this.backgroundColor = -1529086;
            this.textColor = -1;
            this.serviceTextColor = -1;
            this.serviceBackgroundColor = -1529086;
        L47:
            if (this.drawBgOnlyIfChosen == false) goto L49;
            this.backgroundColor = 0;
            this.serviceBackgroundColor = 0;
        L49:
            updateColors(r29);
            ReactionsLayoutInBubble.m12376$$Nest$sfgettextPaint().setColor(this.lastDrawnTextColor);
            AnimatedTextView.AnimatedTextDrawable r92 = this.textDrawable;
            if (r92 == null) goto L52;
            r92.setTextColor(this.lastDrawnTextColor);
        L52:
            ReactionsLayoutInBubble.m12374$$Nest$sfgetpaint().setColor(this.lastDrawnBackgroundColor);
            if (this.isTag == true) goto L55;
        L59:
            boolean r93 = false;
        L61:
            if (r30 == 1.0f) goto L63;
            ReactionsLayoutInBubble.m12376$$Nest$sfgettextPaint().setAlpha((int) (ReactionsLayoutInBubble.m12376$$Nest$sfgettextPaint().getAlpha() * r30));
            ReactionsLayoutInBubble.m12374$$Nest$sfgetpaint().setAlpha((int) (ReactionsLayoutInBubble.m12374$$Nest$sfgetpaint().getAlpha() * r30));
        L63:
            if (r82 == null) goto L66;
            r82.setAlpha(r30);
        L66:
            if (r33 > 0.0f) goto L68;
        L74:
            float r23 = 1.0f;
            float r24 = 0.0f;
        L75:
            float r6 = this.bounce.getScale(0.1f);
            int r112 = this.width;
            if (r33 <= 0.0f) goto L89;
            float r322 = 6.0f;
            if (this.isTag == false) goto L80;
        L88:
            float r332 = 8.0f;
        L91:
            if (r29 != r23) goto L93;
        L95:
            RectF r12 = AndroidUtilities.rectTmp;
            float r113 = r112;
            r12.set(r27, r28, r27 + r113, this.height + r28);
            if (r6 == r23) goto L98;
            r26.save();
            r26.scale(r6, r6, (r113 / 2.0f) + r27, (this.height / 2.0f) + r28);
            boolean r62 = true;
        L99:
            float r7 = ExteraConfig.getAvatarCorners(this.height, true);
            if (getDrawServiceShaderBackground() > r24) goto L102;
        L112:
            boolean r20 = r62;
            float r18 = 2.0f;
        L113:
            if (r31 == true) goto L115;
        L123:
            if (r93 == false) goto L126;
            r12.right += AndroidUtilities.dp(4.0f);
            r26.saveLayerAlpha(r12, 255, 31);
            r12.right -= AndroidUtilities.dp(4.0f);
        L126:
            if (this.particles == null) goto L128;
            LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS);
        L128:
            drawRoundRect(r26, r12, r7, ReactionsLayoutInBubble.m12374$$Nest$sfgetpaint());
            if (this.isTag == true) goto L131;
        L136:
            if (r93 == false) goto L138;
            r26.restore();
        L138:
            if (r82 != null) goto L140;
        L150:
            AnimatedTextView.AnimatedTextDrawable r63 = this.textDrawable;
            int r83 = 8;
            if (r63 != null) goto L153;
        L165:
            float r122 = r24;
        L166:
            float r64 = 5.0f;
            if (r33 > 0.0f) goto L169;
        L188:
            int r14 = 0;
            if (this.counterDrawable != null) goto L191;
        L210:
            if (this.isTag == false) goto L212;
        L214:
            if (r20 == false) goto L217;
            r26.restore();
            return;
        L217:
            return;
        L212:
            if (this.avatarsDrawable == null) goto L214;
            r26.save();
            r26.translate(((r27 + AndroidUtilities.dp(10.0f)) + AndroidUtilities.dp(20.0f)) + AndroidUtilities.dp(r18), r28);
            this.avatarsDrawable.setAlpha(r30);
            this.avatarsDrawable.setTransitionProgress(r29);
            this.avatarsDrawable.onDraw(r26);
            r26.restore();
            goto L214
        L191:
            if (drawCounter() == false) goto L210;
            r26.save();
            if (this.hasName == false) goto L198;
            if (drawTagDot() == true) goto L198;
            float r72 = 10.0f;
        L201:
            float r73 = (AndroidUtilities.dp(r72) + r27) + AndroidUtilities.dp(20.0f);
            if (this.animatedEmojiDrawable != null) goto L205;
            r64 = r18;
        L205:
            float r74 = (r73 + AndroidUtilities.dp(r64)) + r122;
            if (this.paid == false) goto L208;
            r14 = -AndroidUtilities.dp(r23);
        L208:
            r26.translate(r74 + r14, r28);
            this.counterDrawable.draw(r26);
            r26.restore();
        L198:
            if (this.hasName == false) goto L200;
            r83 = 9;
        L200:
            r72 = r83;
            goto L201
        L169:
            if (this.isTag == true) goto L188;
            if (this.scrimPreviewCounterDrawable == null) goto L188;
            if (this.avatarsDrawable != null) goto L188;
            r26.save();
            if (this.hasName == false) goto L180;
            if (drawTagDot() == true) goto L180;
            float r84 = 10.0f;
        L183:
            float r85 = (AndroidUtilities.dp(r84) + r27) + AndroidUtilities.dp(20.0f);
            if (this.animatedEmojiDrawable != null) goto L187;
            r64 = r18;
        L187:
            r26.translate(r85 + AndroidUtilities.dp(r64), r28 - AndroidUtilities.dp(r23));
            this.scrimPreviewCounterDrawable.setBounds(0, 0, this.width, this.height);
            this.scrimPreviewCounterDrawable.draw(r26);
            this.scrimPreviewCounterDrawable.setAlpha((int) (255.0f * r30));
            r26.restore();
        L180:
            if (this.hasName == false) goto L182;
            r83 = 9;
        L182:
            r84 = r83;
            goto L183
        L153:
            if (r63.isNotEmpty() <= r24) goto L165;
            r26.save();
            if (this.hasName == false) goto L160;
            if (drawTagDot() == true) goto L160;
            float r65 = 10.0f;
        L164:
            r26.translate(((AndroidUtilities.dp(r65) + r27) + AndroidUtilities.dp(20.0f)) + AndroidUtilities.dp(r18), r28);
            this.textDrawable.setBounds(0, 0, this.width, this.height);
            this.textDrawable.draw(r26);
            this.textDrawable.setAlpha((int) (r30 * 255.0f));
            r26.restore();
            r122 = this.textDrawable.getCurrentWidth() + (AndroidUtilities.dp(4.0f) * this.textDrawable.isNotEmpty());
        L160:
            if (this.hasName == false) goto L162;
            int r66 = 9;
        L163:
            r65 = r66;
            goto L164
        L162:
            r66 = 8;
            goto L163
        L140:
            if (this.paid == false) goto L143;
            int r67 = AndroidUtilities.dp(22.0f);
            int r75 = AndroidUtilities.dp(4.0f);
        L146:
            int r86 = (int) ((this.height - r67) / r18);
            if (this.isTag == false) goto L149;
            r75 = r75 - AndroidUtilities.dp(r18);
        L149:
            int r10 = ((int) r27) + r75;
            int r76 = ((int) r28) + r86;
            this.drawingImageRect.set(r10, r76, r10 + r67, r67 + r76);
            drawImage(r26, this.drawingImageRect, r30);
            goto L150
        L143:
            if (this.animatedEmojiDrawable == null) goto L145;
            r67 = AndroidUtilities.dp(24.0f);
            r75 = AndroidUtilities.dp(r322);
            r82.setRoundRadius(AndroidUtilities.dp(r322));
            goto L146
        L145:
            r67 = AndroidUtilities.dp(20.0f);
            r75 = AndroidUtilities.dp(r332);
            r82.setRoundRadius(0);
            goto L146
        L131:
            if (drawTagDot() == false) goto L136;
            if (r93 == false) goto L134;
            Paint r68 = ReactionsLayoutInBubble.m12373$$Nest$sfgetcutTagPaint();
        L135:
            r26.drawCircle(r12.right - AndroidUtilities.dp(8.4f), r12.centerY(), AndroidUtilities.dp(2.66f), r68);
            goto L136
        L134:
            ReactionsLayoutInBubble.m12375$$Nest$sfgettagPaint().setColor(this.lastDrawnTagDotColor);
            ReactionsLayoutInBubble.m12375$$Nest$sfgettagPaint().setAlpha((int) (ReactionsLayoutInBubble.m12375$$Nest$sfgettagPaint().getAlpha() * r30));
            r68 = ReactionsLayoutInBubble.m12375$$Nest$sfgettagPaint();
            goto L135
        L115:
            if (getDrawServiceShaderBackground() >= r23) goto L123;
            View r69 = this.parentView;
            if ((r69 instanceof ChatMessageCell) == false) goto L123;
            MessageDrawable r610 = ((ChatMessageCell) r69).getCurrentBackgroundDrawable(false);
            if (r610 == null) goto L123;
            if (this.isTag == true) goto L123;
            r26.drawRoundRect(r12, r7, r7, r610.getPaint());
            goto L123
        L102:
            if (this.drawBgOnlyIfChosen == true) goto L112;
            Paint r114 = Theme.getThemePaint("paintChatActionBackground", this.resourcesProvider);
            r18 = 2.0f;
            Paint r142 = Theme.getThemePaint("paintChatActionBackgroundDarken", this.resourcesProvider);
            int r15 = r114.getAlpha();
            int r102 = r142.getAlpha();
            r20 = r62;
            r114.setAlpha((int) ((r15 * r30) * getDrawServiceShaderBackground()));
            r142.setAlpha((int) ((r102 * r30) * getDrawServiceShaderBackground()));
            drawRoundRect(r26, r12, r7, r114);
            Theme.ResourcesProvider r611 = this.resourcesProvider;
            if (r611 == null) goto L109;
            if (r611.hasGradientService() == true) goto L110;
        L111:
            r114.setAlpha(r15);
            r142.setAlpha(r102);
        L110:
            drawRoundRect(r26, r12, r7, r142);
            goto L111
        L109:
            if (Theme.hasGradientService() == false) goto L111;
        L98:
            r62 = false;
            goto L99
        L93:
            if (this.animationType != 3) goto L95;
            r112 = (int) ((r112 * r29) + (this.animateFromWidth * (r23 - r29)));
            goto L95
        L80:
            if (this.scrimPreviewCounterDrawable == null) goto L88;
            if (this.avatarsDrawable != null) goto L88;
            int r115 = AndroidUtilities.dp(8.0f) + AndroidUtilities.dp(20.0f);
            if (this.animatedEmojiDrawable == null) goto L86;
            float r123 = 6.0f;
        L87:
            r112 = (int) (((r115 + AndroidUtilities.dp(r123)) + this.scrimPreviewCounterDrawable.getCurrentWidth()) + AndroidUtilities.dp(8.0f));
            r332 = 8.0f;
            this.scrimPreviewCounterDrawable.setTextColor(this.lastDrawnTextColor);
            goto L95
        L86:
            r123 = 4.0f;
            goto L87
        L89:
            r332 = 8.0f;
            r322 = 6.0f;
            goto L91
        L68:
            if (this.lastScrimProgressDirection == r32) goto L74;
            AnimatedTextView.AnimatedTextDrawable r152 = this.scrimPreviewCounterDrawable;
            if (r32 == false) goto L72;
            r152.setAnimationProperties(0.6f, 0, 650, 1.6f, CubicBezierInterpolator.EASE_OUT_BACK);
            this.scrimPreviewCounterDrawable.setText(AndroidUtilities.formatWholeNumber(this.count, 0), false);
            r23 = 1.0f;
            r24 = 0.0f;
            this.scrimPreviewCounterDrawable.setText(LocaleController.formatNumber(this.count, ','), true);
        L73:
            this.lastScrimProgressDirection = r32;
            goto L75
        L72:
            r23 = 1.0f;
            r24 = 0.0f;
            r152.setAnimationProperties(0.6f, 0, 320, 1.6f, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.scrimPreviewCounterDrawable.setText(AndroidUtilities.formatWholeNumber(this.count, 0), true);
            goto L73
        L55:
            if (drawTagDot() == false) goto L59;
            if (Color.alpha(this.lastDrawnTagDotColor) != 0) goto L59;
            r93 = true;
            goto L61
        L16:
            if (isOutOwner() == false) goto L18;
            int r94 = Theme.key_chat_outReactionButtonBackground;
        L19:
            this.backgroundColor = Theme.getColor(r94, this.resourcesProvider);
            if (isOutOwner() == false) goto L22;
            int r95 = Theme.key_chat_outReactionButtonTextSelected;
        L23:
            this.textColor = Theme.getColor(r95, this.resourcesProvider);
            if ((this.parentView instanceof ChatActionCell) == false) goto L27;
            this.serviceTextColor = Theme.getColor(Theme.key_chat_reactionServiceButtonTextSelected, this.resourcesProvider);
            this.serviceBackgroundColor = Theme.getColor(Theme.key_chat_reactionServiceButtonBackgroundSelected, this.resourcesProvider);
            goto L47
        L27:
            if (isOutOwner() == false) goto L29;
            int r96 = Theme.key_chat_outReactionButtonBackground;
        L30:
            this.serviceTextColor = Theme.getColor(r96, this.resourcesProvider);
            if (isOutOwner() == false) goto L33;
            int r97 = Theme.key_chat_outBubble;
        L34:
            this.serviceBackgroundColor = Theme.getColor(r97, this.resourcesProvider);
            goto L47
        L33:
            r97 = Theme.key_chat_inBubble;
            goto L34
        L29:
            r96 = Theme.key_chat_inReactionButtonBackground;
            goto L30
        L22:
            r95 = Theme.key_chat_inReactionButtonTextSelected;
            goto L23
        L18:
            r94 = Theme.key_chat_inReactionButtonBackground;
            goto L19
        L35:
            if (r11 == false) goto L38;
            this.textColor = -1529086;
            this.backgroundColor = 1088989954;
            this.serviceTextColor = -1;
            this.serviceBackgroundColor = 0;
            goto L47
        L38:
            if (isOutOwner() == false) goto L40;
            int r98 = Theme.key_chat_outReactionButtonText;
        L41:
            this.textColor = Theme.getColor(r98, this.resourcesProvider);
            if (isOutOwner() == false) goto L44;
            int r99 = Theme.key_chat_outReactionButtonBackground;
        L45:
            int r910 = Theme.getColor(r99, this.resourcesProvider);
            this.backgroundColor = r910;
            this.backgroundColor = ColorUtils.setAlphaComponent(r910, (int) (Color.alpha(r910) * 0.156f));
            this.serviceTextColor = Theme.getColor(Theme.key_chat_serviceText, this.resourcesProvider);
            this.serviceBackgroundColor = 0;
            goto L47
        L44:
            r99 = Theme.key_chat_inReactionButtonBackground;
            goto L45
        L40:
            r98 = Theme.key_chat_inReactionButtonText;
            goto L41
        L5:
            r82 = this.imageReceiver;
            goto L7
        }

        public boolean drawCounter() {
            int r0 = this.count;
            if (r0 == 0) goto L10;
            if (this.isTag == true) goto L7;
        L11:
            return true;
        L7:
            if (this.hasName == true) goto L11;
            if (r0 != 1) goto L11;
        L10:
            if (this.counterDrawable.countChangeProgress != 1.0f) goto L11;
            return false;
        }

        public boolean drawOverlay(Canvas r4, float r5, float r6, float r7, float r8, boolean r9) {
            if (this.particles != null) goto L6;
            return false;
        L6:
            if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS) == true) goto L8;
        L14:
            return false;
        L8:
            if (LiteMode.isEnabled(131072) == false) goto L14;
            RectF r72 = AndroidUtilities.rectTmp;
            r72.set(r5, r6, this.width + r5, this.height + r6);
            float r52 = this.height / 2.0f;
            this.particles.bounds.set(r72);
            this.particles.bounds.inset(-AndroidUtilities.dp(4.0f), -AndroidUtilities.dp(4.0f));
            StarsReactionsSheet.Particles r62 = this.particles;
            r62.setBounds(r62.bounds);
            boolean r63 = this.particles.process();
            this.particles.draw(r4, ColorUtils.blendARGB(ColorUtils.setAlphaComponent(this.backgroundColor, 255), ColorUtils.blendARGB(this.serviceTextColor, ColorUtils.setAlphaComponent(this.backgroundColor, 255), 0.4f), getDrawServiceShaderBackground()));
            if (this.isSelected == false) goto L13;
            this.tagPath.rewind();
            this.tagPath.addRoundRect(r72, r52, r52, Path.Direction.CW);
            r4.save();
            r4.clipPath(this.tagPath);
            this.particles.draw(r4, this.textColor);
            r4.restore();
        L13:
            return r63;
        }

        public void drawPreview(View r5, Canvas r6, RectF r7, float r8) {
            if (r8 <= 0.0f) goto L15;
            ImageReceiver r0 = this.previewImageReceiver;
            if (r0 == null) goto L8;
            r0.setImageCoords(r7);
            this.previewImageReceiver.setAlpha(r8);
            this.previewImageReceiver.draw(r6);
        L11:
            if (r5 == null) goto L14;
            r5.invalidate();
            return;
        L14:
            return;
        L8:
            AnimatedEmojiDrawable r02 = this.previewAnimatedEmojiDrawable;
            if (r02 == null) goto L11;
            r02.setBounds((int) r7.left, (int) r7.top, (int) r7.right, (int) r7.bottom);
            this.previewAnimatedEmojiDrawable.setAlpha((int) (r8 * 255.0f));
            this.previewAnimatedEmojiDrawable.draw(r6);
            goto L11
        }

        public boolean drawTagDot() {
            return true;
        }

        public boolean drawTextWithCounter() {
            return false;
        }

        public int getCacheType() {
            if (this.isTag == false) goto L6;
            return 18;
        L6:
            return 3;
        }

        public float getDrawServiceShaderBackground() {
            return 0.0f;
        }

        public ImageReceiver getImageReceiver() {
            return null;
        }

        public boolean isOutOwner() {
            return false;
        }

        public boolean isPlaying() {
            return false;
        }

        public void removeImageReceiver() {
        }

        public void setUsers(ArrayList<TLObject> r6) {
            this.users = r6;
            if (r6 == null) goto L21;
            Collections.sort(r6, ReactionsLayoutInBubble.m12377$$Nest$sfgetusersComparator());
            if (this.avatarsDrawable != null) goto L8;
            AvatarsDrawable r0 = new AvatarsDrawable(this.parentView, false);
            this.avatarsDrawable = r0;
            r0.transitionDuration = 250;
            r0.transitionInterpolator = ChatListItemAnimator.DEFAULT_INTERPOLATOR;
            r0.setSize(AndroidUtilities.dp(20.0f));
            this.avatarsDrawable.width = AndroidUtilities.dp(100.0f);
            AvatarsDrawable r02 = this.avatarsDrawable;
            r02.height = this.height;
            r02.setAvatarsTextSize(AndroidUtilities.dp(22.0f));
        L8:
            if (this.attached == false) goto L10;
            this.avatarsDrawable.onAttachedToWindow();
        L10:
            int r03 = 0;
        L12:
            if (r03 >= r6.size()) goto L17;
            if (r03 == 3) goto L17;
            this.avatarsDrawable.setObject(r03, this.currentAccount, r6.get(r03));
            r03 = r03 + 1;
        L17:
            this.avatarsDrawable.commitTransition(false);
            return;
        }

        public void startAnimation() {
            AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
            if (r0 != null) goto L5;
        L7:
            ImageReceiver r1 = this.imageReceiver;
        L8:
            if (r1 == null) goto L17;
            RLottieDrawable r02 = r1.getLottieAnimation();
            if (r02 == null) goto L13;
            r02.restart(true);
            return;
        L13:
            AnimatedFileDrawable r12 = r1.getAnimation();
            if (r12 == null) goto L18;
            r12.start();
            return;
        L18:
            return;
        L17:
            return;
        L5:
            if (r0.getImageReceiver() == null) goto L7;
            r1 = this.animatedEmojiDrawable.getImageReceiver();
            goto L8
        }

        public void stopAnimation() {
            AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
            if (r0 != null) goto L5;
        L7:
            ImageReceiver r1 = this.imageReceiver;
        L8:
            if (r1 == null) goto L17;
            RLottieDrawable r02 = r1.getLottieAnimation();
            if (r02 == null) goto L13;
            r02.stop();
            return;
        L13:
            AnimatedFileDrawable r12 = r1.getAnimation();
            if (r12 == null) goto L18;
            r12.stop();
            return;
        L18:
            return;
        L17:
            return;
        L5:
            if (r0.getImageReceiver() == null) goto L7;
            r1 = this.animatedEmojiDrawable.getImageReceiver();
            goto L8
        }

        public void updateColors(float r5) {
            this.lastDrawnTextColor = ColorUtils.blendARGB(this.fromTextColor, ColorUtils.blendARGB(this.textColor, this.serviceTextColor, getDrawServiceShaderBackground()), r5);
            int r0 = ColorUtils.blendARGB(this.fromBackgroundColor, ColorUtils.blendARGB(this.backgroundColor, this.serviceBackgroundColor, getDrawServiceShaderBackground()), r5);
            this.lastDrawnBackgroundColor = r0;
            int r1 = this.fromTagDotColor;
            if (AndroidUtilities.computePerceivedBrightness(r0) <= 0.8f) goto L5;
            int r02 = 0;
        L6:
            this.lastDrawnTagDotColor = ColorUtils.blendARGB(r1, r02, r5);
            return;
        L5:
            r02 = 1526726655;
            goto L6
        }
    }

    public class ReactionLayoutButton extends ReactionButton {
        final /* synthetic */ ReactionsLayoutInBubble this$0;

        public ReactionLayoutButton(ReactionsLayoutInBubble r9, ReactionButton r10, TLRPC.ReactionCount r11, boolean r12, boolean r13) {
            this.this$0 = r9;
            super(r10, r9.currentAccount, r9.parentView, r11, r12, r13, r9.resourcesProvider);
        }

        @Override // org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble.ReactionButton
        public float getDrawServiceShaderBackground() {
            return this.this$0.drawServiceShaderBackground;
        }

        @Override // org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble.ReactionButton
        public ImageReceiver getImageReceiver() {
            return this.this$0.animatedReactions.get(this.visibleReaction);
        }

        @Override // org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble.ReactionButton
        public boolean isOutOwner() {
            return this.this$0.messageObject.isOutOwner();
        }

        @Override // org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble.ReactionButton
        public boolean isPlaying() {
            return ReactionsEffectOverlay.isPlaying(this.this$0.messageObject.getId(), this.this$0.messageObject.getGroupId(), this.visibleReaction);
        }

        @Override // org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble.ReactionButton
        public void removeImageReceiver() {
            this.this$0.animatedReactions.remove(this.visibleReaction);
        }
    }

    /* loaded from: classes3.dex */
    public static class VisibleReaction {
        public long documentId;
        public long effectId;
        public String emojicon;
        public long hash;
        public boolean isEffect;
        public boolean isStar;
        public boolean premium;
        public boolean sticker;

        public VisibleReaction() {
        }

        public static VisibleReaction asStar() {
            VisibleReaction r0 = new VisibleReaction();
            r0.isStar = true;
            return r0;
        }

        public static VisibleReaction fromCustomEmoji(Long r3) {
            VisibleReaction r0 = new VisibleReaction();
            long r1 = r3.longValue();
            r0.documentId = r1;
            r0.hash = r1;
            return r0;
        }

        public static VisibleReaction fromEmojicon(String r3) {
            if (r3 != null) goto L4;
            r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        L4:
            VisibleReaction r0 = new VisibleReaction();
            if (r3.startsWith("animated_") == true) goto L13;
            r0.emojicon = r3;
            r0.hash = r3.hashCode();
            return r0;
        L13:
            long r1 = Long.parseLong(r3.substring(9));     // Catch: Exception -> L9
            r0.documentId = r1;     // Catch: Exception -> L9
            r0.hash = r1;     // Catch: Exception -> L9
            return r0;
        L9:
            r0.emojicon = r3;
            r0.hash = r3.hashCode();
            return r0;
        }

        public static VisibleReaction fromEmojicon(TLRPC.TL_availableReaction r3) {
            VisibleReaction r0 = new VisibleReaction();
            r0.emojicon = r3.reaction;
            r0.hash = r3.hashCode();
            return r0;
        }

        public static VisibleReaction fromTL(TLRPC.Reaction r3) {
            VisibleReaction r0 = new VisibleReaction();
            if ((r3 instanceof TLRPC.TL_reactionPaid) == false) goto L7;
            r0.isStar = true;
            return r0;
        L7:
            if ((r3 instanceof TLRPC.TL_reactionEmoji) == false) goto L11;
            r0.emojicon = ((TLRPC.TL_reactionEmoji) r3).emoticon;
            r0.hash = r3.hashCode();
            return r0;
        L11:
            if ((r3 instanceof TLRPC.TL_reactionCustomEmoji) == false) goto L13;
            long r1 = ((TLRPC.TL_reactionCustomEmoji) r3).document_id;
            r0.documentId = r1;
            r0.hash = r1;
        L13:
            return r0;
        }

        public static VisibleReaction fromTL(TLRPC.TL_availableEffect r8) {
            VisibleReaction r0 = new VisibleReaction();
            boolean r1 = true;
            r0.isEffect = true;
            long r2 = r8.id;
            r0.effectId = r2;
            if (r8.effect_animation_id == 0) goto L6;
            r1 = false;
        L6:
            r0.sticker = r1;
            r0.documentId = r8.effect_sticker_id;
            r0.hash = r2;
            r0.premium = r8.premium_required;
            r0.emojicon = r8.emoticon;
            return r0;
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L15:
            return false;
        L8:
            if (getClass() != r7.getClass()) goto L15;
            VisibleReaction r72 = (VisibleReaction) r7;
            if (this.documentId != r72.documentId) goto L15;
            if (Objects.equals(this.emojicon, r72.emojicon) == false) goto L15;
            return true;
        }

        public VisibleReaction flatten() {
            long r0 = this.documentId;
            if (r0 == 0) goto L8;
            String r02 = MessageObject.findAnimatedEmojiEmoticon(AnimatedEmojiDrawable.findDocument(UserConfig.selectedAccount, r0), null);
            if (r02 != null) goto L7;
            return this;
        L7:
            return fromEmojicon(r02);
        L8:
            return this;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.emojicon, Long.valueOf(this.documentId)});
        }

        public boolean isSame(TLRPC.Reaction r5) {
            if ((r5 instanceof TLRPC.TL_reactionEmoji) == false) goto L7;
            return TextUtils.equals(((TLRPC.TL_reactionEmoji) r5).emoticon, this.emojicon);
        L7:
            if ((r5 instanceof TLRPC.TL_reactionCustomEmoji) == true) goto L9;
        L12:
            return false;
        L9:
            if (((TLRPC.TL_reactionCustomEmoji) r5).document_id != this.documentId) goto L12;
            return true;
        }

        public CharSequence toCharSequence(int r6) {
            TextPaint r0 = new TextPaint();
            r0.setTextSize(AndroidUtilities.dp(r6));
            if (TextUtils.isEmpty(this.emojicon) == false) goto L5;
            SpannableString r62 = new SpannableString("😀");
            r62.setSpan(new AnimatedEmojiSpan(this.documentId, r0.getFontMetricsInt()), 0, r62.length(), 17);
            return r62;
        L5:
            return Emoji.replaceEmoji(this.emojicon, r0.getFontMetricsInt(), false);
        }

        public CharSequence toCharSequence(Paint.FontMetricsInt r5) {
            if (TextUtils.isEmpty(this.emojicon) == false) goto L5;
            SpannableString r0 = new SpannableString("😀");
            r0.setSpan(new AnimatedEmojiSpan(this.documentId, r5), 0, r0.length(), 17);
            return r0;
        L5:
            return this.emojicon;
        }

        public String toString() {
            if (TextUtils.isEmpty(this.emojicon) == false) goto L5;
            long r0 = this.documentId;
            if (r0 == 0) goto L13;
            TLRPC.Document r02 = AnimatedEmojiDrawable.findDocument(UserConfig.selectedAccount, r0);
            if (r02 == null) goto L13;
            return MessageObject.findAnimatedEmojiEmoticon(r02, null);
        L13:
            return "VisibleReaction{" + this.documentId + ", " + this.emojicon + "}";
        L5:
            return this.emojicon;
        }

        public TLRPC.Reaction toTLReaction() {
            if (this.isStar == false) goto L7;
            return new TLRPC.TL_reactionPaid();
        L7:
            if (this.emojicon == null) goto L10;
            TLRPC.TL_reactionEmoji r0 = new TLRPC.TL_reactionEmoji();
            r0.emoticon = this.emojicon;
            return r0;
        L10:
            TLRPC.TL_reactionCustomEmoji r02 = new TLRPC.TL_reactionCustomEmoji();
            r02.document_id = this.documentId;
            return r02;
        }
    }

    public static /* synthetic */ int $r8$lambda$PUvINze9IkR1wVS4VbZduDR1Brs(TLObject r2, TLObject r3) {
        return (int) (getPeerId(r2) - getPeerId(r3));
    }

    public static /* synthetic */ void $r8$lambda$XCNyZJqLNv4yv8v4g8XM7DNl1FY(ReactionsLayoutInBubble r0, ReactionButton r1) {
        r0.lambda$checkTouchEvent$1(r1);
    }

    /* renamed from: -$$Nest$sfgetcutTagPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m12373$$Nest$sfgetcutTagPaint() {
        return cutTagPaint;
    }

    /* renamed from: -$$Nest$sfgetpaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m12374$$Nest$sfgetpaint() {
        return paint;
    }

    /* renamed from: -$$Nest$sfgettagPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m12375$$Nest$sfgettagPaint() {
        return tagPaint;
    }

    /* renamed from: -$$Nest$sfgettextPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextPaint m12376$$Nest$sfgettextPaint() {
        return textPaint;
    }

    /* renamed from: -$$Nest$sfgetusersComparator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Comparator m12377$$Nest$sfgetusersComparator() {
        return usersComparator;
    }

    static {
        paint = new Paint(1);
        tagPaint = new Paint(1);
        cutTagPaint = new Paint(1);
        textPaint = new TextPaint(1);
        comparator = new ButtonsComparator(null);
        pointer = 1;
        usersComparator = new ReactionsLayoutInBubble$$ExternalSyntheticLambda0();
    }

    public ReactionsLayoutInBubble(View r2) {
        this.reactionButtons = new ArrayList();
        this.outButtons = new ArrayList();
        this.lastDrawingReactionButtons = new HashMap();
        this.lastDrawingReactionButtonsTmp = new HashMap();
        this.animatedReactions = new HashMap();
        this.reactionLineWidths = new ArrayList();
        this.scrimRect = new RectF();
        this.scrimRect2 = new Rect();
        this.parentView = r2;
        this.currentAccount = UserConfig.selectedAccount;
        initPaints(this.resourcesProvider);
        this.touchSlop = ViewConfiguration.get(ApplicationLoader.applicationContext).getScaledTouchSlop();
    }

    private void didPressReaction(TLRPC.ReactionCount r2, boolean r3, float r4, float r5) {
        KeyEvent.Callback r1 = this.parentView;
        if ((r1 instanceof IMessageCell) == false) goto L6;
        ((IMessageCell) r1).didPressReactionFromLayout(r2, r3, r4, r5);
        return;
    }

    public static boolean equalsTLReaction(TLRPC.Reaction r4, TLRPC.Reaction r5) {
        if ((r4 instanceof TLRPC.TL_reactionEmoji) == false) goto L9;
        if ((r5 instanceof TLRPC.TL_reactionEmoji) == false) goto L9;
        return TextUtils.equals(((TLRPC.TL_reactionEmoji) r4).emoticon, ((TLRPC.TL_reactionEmoji) r5).emoticon);
    L9:
        if ((r4 instanceof TLRPC.TL_reactionCustomEmoji) == true) goto L11;
    L16:
        return false;
    L11:
        if ((r5 instanceof TLRPC.TL_reactionCustomEmoji) == false) goto L16;
        if (((TLRPC.TL_reactionCustomEmoji) r4).document_id != ((TLRPC.TL_reactionCustomEmoji) r5).document_id) goto L16;
        return true;
    }

    private boolean equalsUsersList(ArrayList<TLObject> r6, ArrayList<TLObject> r7) {
        if (r6 == null) goto L25;
        if (r7 != null) goto L6;
        return false;
    L6:
        if (r6.size() != r7.size()) goto L27;
        int r0 = 0;
    L10:
        if (r0 >= r6.size()) goto L19;
        TLObject r1 = r6.get(r0);
        TLObject r2 = r7.get(r0);
        if (r1 == null) goto L18;
        if (r2 == null) goto L18;
        if (getPeerId(r1) != getPeerId(r2)) goto L18;
        r0 = r0 + 1;
    L18:
        return false;
    L19:
        return true;
    L27:
        return false;
    L25:
        return false;
    }

    public static void fillTagPath(RectF r1, Path r2) {
        fillTagPath(r1, AndroidUtilities.rectTmp, r2);
    }

    public static void fillTagPath(RectF r20, RectF r21, Path r22) {
        r22.rewind();
        float r3 = r20.left;
        r21.set(r3, r20.top, AndroidUtilities.dp(12.0f) + r3, r20.top + AndroidUtilities.dp(12.0f));
        r22.arcTo(r21, -90.0f, -90.0f, false);
        r21.set(r20.left, r20.bottom - AndroidUtilities.dp(12.0f), r20.left + AndroidUtilities.dp(12.0f), r20.bottom);
        r22.arcTo(r21, -180.0f, -90.0f, false);
        if (r20.height() <= AndroidUtilities.dp(26.0f)) goto L5;
        float r1 = 1.4f;
    L6:
        float r32 = r20.right - AndroidUtilities.dpf2(9.09f);
        float r8 = r32 - AndroidUtilities.dpf2(0.056f);
        float r4 = AndroidUtilities.dpf2(1.22f) + r32;
        float r6 = r32 + AndroidUtilities.dpf2(3.07f);
        float r5 = AndroidUtilities.dpf2(2.406f) + r32;
        float r9 = r32 + AndroidUtilities.dpf2(8.27f + r1);
        float r10 = r32 + AndroidUtilities.dpf2(8.923f + r1);
        float r11 = AndroidUtilities.dpf2(1.753f) + r20.top;
        float r7 = r20.bottom - AndroidUtilities.dpf2(1.753f);
        float r13 = AndroidUtilities.dpf2(0.663f) + r20.top;
        float r33 = r20.bottom - AndroidUtilities.dpf2(0.663f);
        float r14 = 10.263f + r1;
        float r12 = r20.top + AndroidUtilities.dpf2(r14);
        float r15 = r20.bottom - AndroidUtilities.dpf2(r14);
        float r16 = r1 + 11.333f;
        float r142 = r20.top + AndroidUtilities.dpf2(r16);
        float r162 = r20.bottom - AndroidUtilities.dpf2(r16);
        r22.lineTo(r8, r20.bottom);
        r22.cubicTo(r4, r20.bottom, r5, r33, r6, r7);
        r22.lineTo(r9, r15);
        r22.cubicTo(r10, r162, r10, r142, r9, r12);
        r22.lineTo(r6, r11);
        float r42 = r20.top;
        r22.cubicTo(r5, r13, r4, r42, r8, r42);
        r22.close();
        return;
    L5:
        r1 = 0.0f;
        goto L6
    }

    private int getParentWidth() {
        View r1 = this.parentView;
        if ((r1 instanceof ChatMessageCell) == false) goto L7;
        return ((ChatMessageCell) r1).getParentWidth();
    L7:
        return AndroidUtilities.displaySize.x;
    }

    private static long getPeerId(TLObject r2) {
        if ((r2 instanceof TLRPC.User) == false) goto L7;
        return ((TLRPC.User) r2).id;
    L7:
        if ((r2 instanceof TLRPC.Chat) == true) goto L9;
        return 0;
    L9:
        return ((TLRPC.Chat) r2).id;
    }

    public static void initPaints(Theme.ResourcesProvider r2) {
        paint.setColor(Theme.getColor(Theme.key_chat_inLoader, r2));
        TextPaint r0 = textPaint;
        r0.setColor(Theme.getColor(Theme.key_featuredStickers_buttonText, r2));
        r0.setTextSize(AndroidUtilities.dp(12.0f));
        r0.setTypeface(AndroidUtilities.bold());
        cutTagPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    private /* synthetic */ void lambda$checkTouchEvent$1(ReactionButton r4) {
        didPressReaction(ReactionButton.m12380$$Nest$fgetreactionCount(r4), true, 0.0f, 0.0f);
        r4.bounce.setPressed(false);
        this.lastSelectedButton = null;
        this.pressed = false;
        this.longPressRunnable = null;
    }

    public static boolean reactionsEqual(TLRPC.Reaction r4, TLRPC.Reaction r5) {
        if ((r4 instanceof TLRPC.TL_reactionEmoji) == false) goto L10;
        if ((r5 instanceof TLRPC.TL_reactionEmoji) == true) goto L8;
        return false;
    L8:
        return TextUtils.equals(((TLRPC.TL_reactionEmoji) r4).emoticon, ((TLRPC.TL_reactionEmoji) r5).emoticon);
    L10:
        if ((r4 instanceof TLRPC.TL_reactionCustomEmoji) == true) goto L12;
    L18:
        return false;
    L12:
        if ((r5 instanceof TLRPC.TL_reactionCustomEmoji) == true) goto L15;
        return false;
    L15:
        if (((TLRPC.TL_reactionCustomEmoji) r4).document_id != ((TLRPC.TL_reactionCustomEmoji) r5).document_id) goto L18;
        return true;
    }

    public boolean animateChange() {
        int r1 = 0;
        if (this.messageObject != null) goto L5;
        return false;
    L5:
        this.lastDrawingReactionButtonsTmp.clear();
        int r0 = 0;
    L6:
        int r2 = this.outButtons.size();
        ArrayList<ReactionButton> r3 = this.outButtons;
        if (r0 >= r2) goto L9;
        r3.get(r0).detach();
        r0 = r0 + 1;
        goto L6
    L9:
        r3.clear();
        this.lastDrawingReactionButtonsTmp.putAll(this.lastDrawingReactionButtons);
        int r02 = 0;
        boolean r22 = false;
    L11:
        if (r02 >= this.reactionButtons.size()) goto L57;
        ReactionButton r32 = this.reactionButtons.get(r02);
        ReactionButton r5 = this.lastDrawingReactionButtonsTmp.get(r32.key);
        if (r5 != null) goto L15;
    L17:
        if (r5 == null) goto L54;
        this.lastDrawingReactionButtonsTmp.remove(r32.key);
        int r6 = r32.x;
        int r7 = r5.x;
        if (r6 == r7) goto L21;
    L34:
        r32.animateFromX = r7;
        r32.animateFromY = r5.y;
        r32.animateFromWidth = r5.width;
        r32.fromTextColor = r5.lastDrawnTextColor;
        r32.fromBackgroundColor = r5.lastDrawnBackgroundColor;
        r32.fromTagDotColor = r5.lastDrawnTagDotColor;
        r32.animationType = 3;
        int r23 = r32.count;
        int r62 = r5.count;
        if (r23 == r62) goto L39;
        CounterView.CounterDrawable r24 = r32.counterDrawable;
        if (r24 == null) goto L39;
        r24.setCount(r62, false);
        r32.counterDrawable.setCount(r32.count, true);
    L39:
        AvatarsDrawable r25 = r32.avatarsDrawable;
        if (r25 == null) goto L42;
    L43:
        if (r25 != null) goto L46;
        r32.setUsers(new ArrayList());
    L46:
        if (r5.avatarsDrawable != null) goto L49;
        r5.setUsers(new ArrayList());
    L49:
        if (equalsUsersList(r5.users, r32.users) == true) goto L53;
        AvatarsDrawable r26 = r32.avatarsDrawable;
        if (r26 == null) goto L53;
        r26.animateFromState(r5.avatarsDrawable, this.currentAccount, false);
    L53:
        r22 = true;
    L55:
        r02 = r02 + 1;
        goto L11
    L42:
        if (r5.avatarsDrawable == null) goto L53;
    L21:
        if (r32.y != r5.y) goto L34;
        if (r32.width != r5.width) goto L34;
        if (r32.count != r5.count) goto L34;
        if (r32.choosen != r5.choosen) goto L34;
        if (r32.avatarsDrawable != null) goto L34;
        if (r5.avatarsDrawable != null) goto L34;
        r32.animationType = 0;
        goto L55
    L54:
        r32.animationType = 1;
        goto L53
    L15:
        if (ReactionButton.m12378$$Nest$fgetisSmall(r32) == ReactionButton.m12378$$Nest$fgetisSmall(r5)) goto L17;
        r5 = null;
        goto L17
    L57:
        if (this.lastDrawingReactionButtonsTmp.isEmpty() == true) goto L64;
        this.outButtons.addAll(this.lastDrawingReactionButtonsTmp.values());
    L60:
        if (r1 >= this.outButtons.size()) goto L62;
        this.outButtons.get(r1).drawImage = this.outButtons.get(r1).lastImageDrawn;
        this.outButtons.get(r1).attach();
        r1 = r1 + 1;
        goto L60
    L62:
        r22 = true;
    L64:
        if (this.wasDrawn == false) goto L70;
        float r03 = this.lastDrawnX;
        if (r03 == this.x) goto L68;
    L69:
        this.animateMove = true;
        this.fromX = r03;
        this.fromY = this.lastDrawnY;
        r22 = true;
        goto L70
    L68:
        if (this.lastDrawnY != this.y) goto L69;
    L70:
        int r04 = this.lastDrawnWidth;
        if (r04 == this.width) goto L73;
        this.animateWidth = true;
        this.fromWidth = r04;
        r22 = true;
    L73:
        int r05 = this.lastDrawTotalHeight;
        if (r05 == this.totalHeight) goto L77;
        this.animateHeight = true;
        this.animateFromTotalHeight = r05;
        return true;
    L77:
        return r22;
    }

    public void animateReaction(VisibleReaction r12) {
        int r1 = 0;
        if (r12.documentId != 0) goto L12;
        if (this.animatedReactions.get(r12) != null) goto L12;
        ImageReceiver r4 = new ImageReceiver();
        r4.setParentView(this.parentView);
        int r0 = animationUniq;
        animationUniq = r0 + 1;
        r4.setUniqKeyPrefix(Integer.toString(r0));
        TLRPC.TL_availableReaction r9 = MediaDataController.getInstance(this.currentAccount).getReactionsMap().get(r12.emojicon);
        if (r9 == null) goto L9;
        r4.setImage(ImageLocation.getForDocument(r9.center_icon), "40_40_nolimit", null, "tgs", r9, 1);
    L9:
        r4.setAutoRepeat(0);
        r4.onAttachedToWindow();
        this.animatedReactions.put(r12, r4);
        return;
    L12:
        if (this.tags == true) goto L14;
        return;
    L14:
        if (r12.documentId != 0) goto L16;
        return;
    L16:
        if (r1 >= this.reactionButtons.size()) goto L26;
        if (r12.isSame(this.reactionButtons.get(r1).reaction) == true) goto L19;
        r1 = r1 + 1;
        goto L16
    L19:
        this.reactionButtons.get(r1).startAnimation();
        return;
    }

    public boolean checkTouchEvent(MotionEvent r10) {
        int r1 = 0;
        if (this.isEmpty == false) goto L5;
    L69:
        return false;
    L5:
        if (this.isSmall == true) goto L69;
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L69;
        TLRPC.Message r02 = r0.messageOwner;
        if (r02 == null) goto L69;
        if (r02.reactions == null) goto L69;
        float r03 = r10.getX();
        float r2 = r10.getY();
        if ((this.parentView instanceof IMessageCell) == false) goto L18;
        r2 = r2 - r3.getPaddingTop();
        if ((this.parentView instanceof ChatActionCell) == false) goto L18;
        r03 = r03 - (((ChatActionCell) r3).sideMenuWidth / 2.0f);
    L18:
        float r04 = r03 - this.x;
        float r3 = r2 - this.y;
        if (r10.getAction() != 0) goto L36;
        int r4 = this.reactionButtons.size();
    L21:
        if (r1 >= r4) goto L68;
        if (r04 <= this.reactionButtons.get(r1).x) goto L34;
        if (r04 >= (this.reactionButtons.get(r1).x + this.reactionButtons.get(r1).width)) goto L34;
        if (r3 <= this.reactionButtons.get(r1).y) goto L34;
        if (r3 >= (this.reactionButtons.get(r1).y + this.reactionButtons.get(r1).height)) goto L34;
        this.lastX = r10.getX();
        this.lastY = r2;
        this.lastSelectedButton = this.reactionButtons.get(r1);
        Runnable r102 = this.longPressRunnable;
        if (r102 == null) goto L33;
        AndroidUtilities.cancelRunOnUIThread(r102);
        this.longPressRunnable = null;
    L33:
        this.lastSelectedButton.bounce.setPressed(true);
        ReactionsLayoutInBubble$$ExternalSyntheticLambda1 r05 = new ReactionsLayoutInBubble$$ExternalSyntheticLambda1(this, this.lastSelectedButton);
        this.longPressRunnable = r05;
        AndroidUtilities.runOnUIThread(r05, ViewConfiguration.getLongPressTimeout());
        this.pressed = true;
    L34:
        r1 = r1 + 1;
    L68:
        return this.pressed;
    L36:
        if (r10.getAction() != 2) goto L50;
        if (this.pressed == false) goto L42;
        if (Math.abs(r10.getX() - this.lastX) <= this.touchSlop) goto L42;
    L43:
        this.pressed = false;
        ReactionButton r103 = this.lastSelectedButton;
        if (r103 == null) goto L46;
        r103.bounce.setPressed(false);
    L46:
        this.lastSelectedButton = null;
        Runnable r104 = this.longPressRunnable;
        if (r104 == null) goto L68;
        AndroidUtilities.cancelRunOnUIThread(r104);
        this.longPressRunnable = null;
    L42:
        if (Math.abs(r2 - this.lastY) <= this.touchSlop) goto L68;
    L50:
        if (r10.getAction() != 1) goto L52;
    L53:
        Runnable r06 = this.longPressRunnable;
        if (r06 == null) goto L57;
        AndroidUtilities.cancelRunOnUIThread(r06);
        this.longPressRunnable = null;
    L57:
        if (this.pressed == true) goto L59;
    L63:
        this.pressed = false;
        ReactionButton r105 = this.lastSelectedButton;
        if (r105 == null) goto L66;
        r105.bounce.setPressed(false);
    L66:
        this.lastSelectedButton = null;
        goto L68
    L59:
        if (this.lastSelectedButton == null) goto L63;
        if (r10.getAction() != 1) goto L63;
        didPressReaction(ReactionButton.m12380$$Nest$fgetreactionCount(this.lastSelectedButton), false, r10.getX(), r2);
        goto L63
    L52:
        if (r10.getAction() != 3) goto L68;
        goto L53
    }

    public void draw(Canvas r19, float r20, Integer r21) {
        Canvas r2 = r19;
        if (this.isEmpty == true) goto L5;
    L7:
        float r1 = this.x;
        float r3 = this.y;
        if (this.isEmpty == false) goto L12;
        r1 = this.lastDrawnX;
        r3 = this.lastDrawnY;
    L10:
        float r11 = r1;
        float r12 = r3;
        int r13 = 0;
        int r14 = 0;
    L16:
        if (r14 >= this.reactionButtons.size()) goto L51;
        ReactionButton r15 = this.reactionButtons.get(r14);
        if (this.scrimViewReaction != null) goto L24;
        if (r21 != null) goto L24;
        if (this.scrimProgress >= 0.5f) goto L24;
        r15.detachPreview();
    L24:
        if (Integer.valueOf(r15.reaction.hashCode()).equals(this.scrimViewReaction) == true) goto L49;
        if (r21 != null) goto L28;
    L30:
        r2.save();
        int r32 = r15.x;
        float r4 = r32;
        int r5 = r15.y;
        float r6 = r5;
        if (r20 == 1.0f) goto L36;
        if (r15.animationType != 3) goto L36;
        float r62 = 1.0f - r20;
        r4 = (r15.animateFromX * r62) + (r32 * r20);
        r6 = (r5 * r20) + (r15.animateFromY * r62);
    L36:
        if (r20 != 1.0f) goto L38;
    L40:
        float r33 = r6;
        float r63 = 1.0f;
    L41:
        float r42 = r4 + r11;
        float r34 = r33 + r12;
        if (r15.animationType != 3) goto L44;
        float r52 = r20;
    L45:
        if (r21 == null) goto L47;
        boolean r7 = true;
    L48:
        r15.draw(r2, r42, r34, r52, r63, r7, this.scrimDirection, this.scrimProgress);
        r2.restore();
        goto L49
    L47:
        r7 = false;
        goto L48
    L44:
        r52 = 1.0f;
        goto L45
    L38:
        if (r15.animationType != 1) goto L40;
        float r53 = (r20 * 0.5f) + 0.5f;
        r2.scale(r53, r53, (r11 + r4) + (r15.width / 2.0f), (r12 + r6) + (r15.height / 2.0f));
        r33 = r6;
        r63 = r20;
        goto L41
    L28:
        if (r15.reaction.hashCode() == r21.intValue()) goto L30;
    L49:
        r14 = r14 + 1;
    L51:
        if (r13 >= this.outButtons.size()) goto L59;
        ReactionButton r16 = this.outButtons.get(r13);
        float r64 = 1.0f - r20;
        float r35 = (r64 * 0.5f) + 0.5f;
        r2.save();
        r2.scale(r35, r35, (r16.x + r11) + (r16.width / 2.0f), (r16.y + r12) + (r16.height / 2.0f));
        this.outButtons.get(r13).draw(r2, r16.x + r11, r16.y + r12, 1.0f, r64, false, this.scrimDirection, this.scrimProgress);
        r19.restore();
        r13 = r13 + 1;
        r2 = r19;
        goto L51
    L59:
        return;
    L12:
        if (this.animateMove == false) goto L10;
        float r54 = 1.0f - r20;
        r1 = (r1 * r20) + (this.fromX * r54);
        r3 = (r3 * r20) + (this.fromY * r54);
        goto L10
    L5:
        if (this.outButtons.isEmpty() == false) goto L7;
    }

    public boolean drawOverlay(Canvas r20, float r21) {
        Canvas r2 = r20;
        if (this.isEmpty == true) goto L5;
    L7:
        float r1 = this.x;
        float r3 = this.y;
        float r9 = 1.0f;
        if (this.isEmpty == false) goto L12;
        r1 = this.lastDrawnX;
        r3 = this.lastDrawnY;
    L10:
        float r10 = r1;
        float r11 = r3;
        boolean r12 = false;
        int r122 = 0;
    L16:
        if (r122 >= this.reactionButtons.size()) goto L42;
        ReactionButton r32 = this.reactionButtons.get(r122);
        if (r32.paid == true) goto L20;
        float r17 = r9;
    L41:
        r122 = r122 + 1;
        r9 = r17;
        goto L16
    L20:
        r2.save();
        int r4 = r32.x;
        float r5 = r4;
        int r6 = r32.y;
        float r7 = r6;
        r17 = r9;
        if (r21 != r9) goto L23;
    L25:
        if (r21 != r9) goto L27;
    L29:
        float r62 = r17;
    L30:
        if (r12 == true) goto L39;
        float r52 = r5 + r10;
        float r42 = r11 + r7;
        if (r32.animationType != 3) goto L34;
        ReactionButton r13 = r32;
        float r33 = r52;
        float r53 = r21;
    L36:
        if (r13.drawOverlay(r2, r33, r42, r53, r62, false) == true) goto L39;
        r12 = false;
    L40:
        r2.restore();
        goto L41
    L34:
        r13 = r32;
        r33 = r52;
        r53 = r17;
    L39:
        r12 = true;
        goto L40
    L27:
        if (r32.animationType != 1) goto L29;
        float r43 = (r21 * 0.5f) + 0.5f;
        r2.scale(r43, r43, (r10 + r5) + (r32.width / 2.0f), (r11 + r7) + (r32.height / 2.0f));
        r62 = r21;
        goto L30
    L23:
        if (r32.animationType != 3) goto L25;
        float r92 = r17 - r21;
        r5 = (r32.animateFromX * r92) + (r4 * r21);
        r7 = (r6 * r21) + (r32.animateFromY * r92);
        goto L25
    L42:
        float r172 = r9;
        int r8 = 0;
    L44:
        if (r8 >= this.outButtons.size()) goto L57;
        if (this.outButtons.get(r8).paid == false) goto L56;
        float r63 = r172 - r21;
        float r44 = (r63 * 0.5f) + 0.5f;
        r2.save();
        r2.scale(r44, r44, (r3.x + r10) + (r3.width / 2.0f), (r3.y + r11) + (r3.height / 2.0f));
        if (r12 == false) goto L51;
    L54:
        r12 = true;
    L55:
        r20.restore();
        goto L56
    L51:
        if (this.outButtons.get(r8).drawOverlay(r2, r3.x + r10, r3.y + r11, 1.0f, r63, false) == true) goto L54;
        r12 = false;
    L56:
        r8 = r8 + 1;
        r2 = r20;
        goto L44
    L57:
        return r12;
    L12:
        if (this.animateMove == false) goto L10;
        float r54 = 1.0f - r21;
        r1 = (r1 * r21) + (this.fromX * r54);
        r3 = (r3 * r21) + (this.fromY * r54);
        goto L10
    L5:
        if (this.outButtons.isEmpty() == false) goto L7;
        return false;
    }

    public void drawPreview(View r11, Canvas r12, int r13, Integer r14) {
        if (this.isEmpty == true) goto L5;
    L7:
        int r0 = 0;
    L9:
        if (r0 >= this.reactionButtons.size()) goto L23;
        ReactionButton r1 = this.reactionButtons.get(r0);
        if (r14 != null) goto L13;
    L15:
        if (r14 == null) goto L17;
        RectF r2 = AndroidUtilities.rectTmp;
        r2.set(r1.drawingImageRect);
        float r3 = AndroidUtilities.dp(140.0f);
        float r4 = AndroidUtilities.dp(14.0f);
        float r5 = Utilities.clamp(r2.left - AndroidUtilities.dp(12.0f), (getParentWidth() - r3) - AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
        RectF r6 = this.scrimRect;
        float r7 = r2.top;
        float r9 = r13;
        r6.set(r5, ((r7 - r4) - r3) + r9, r3 + r5, (r7 - r4) + r9);
        float r32 = CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(this.scrimProgress);
        RectF r42 = this.scrimRect;
        AndroidUtilities.lerp(r2, r42, r32, r42);
        r1.attachPreview(r11);
        Rect r22 = this.scrimRect2;
        RectF r43 = this.scrimRect;
        r22.set((int) r43.left, (int) r43.top, (int) r43.right, (int) r43.bottom);
        r1.drawPreview(r11, r12, this.scrimRect, r32);
    L17:
        r0 = r0 + 1;
        goto L9
    L13:
        if (r1.reaction.hashCode() == r14.intValue()) goto L15;
    L23:
        return;
    L5:
        if (this.outButtons.isEmpty() == false) goto L7;
    }

    public float getCurrentTotalHeight(float r3) {
        if (this.animateHeight == false) goto L7;
        return (this.animateFromTotalHeight * (1.0f - r3)) + (this.totalHeight * r3);
    L7:
        return this.totalHeight;
    }

    public float getCurrentWidth(float r3) {
        if (this.animateWidth == false) goto L7;
        return (this.fromWidth * (1.0f - r3)) + (this.width * r3);
    L7:
        return this.width;
    }

    public ReactionButton getReactionButton(String r4) {
        if (this.isSmall == false) goto L8;
        ReactionButton r0 = this.lastDrawingReactionButtons.get(r4 + "_");
        if (r0 == null) goto L8;
        return r0;
    L8:
        return this.lastDrawingReactionButtons.get(r4);
    }

    public ReactionButton getReactionButton(VisibleReaction r3) {
        if (r3.isStar == false) goto L5;
        String r32 = "stars";
    L10:
        return getReactionButton(r32);
    L5:
        String r0 = r3.emojicon;
        if (r0 == null) goto L8;
        r32 = r0;
        goto L10
    L8:
        r32 = Long.toString(r3.documentId);
        goto L10
    }

    public boolean hasOverlay() {
        if (this.hasPaidReaction == true) goto L5;
        return false;
    L5:
        if (this.isEmpty == false) goto L9;
        if (this.outButtons.isEmpty() == false) goto L9;
        return false;
    L9:
        if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS) == true) goto L11;
        return false;
    L11:
        if (LiteMode.isEnabled(131072) == false) goto L18;
        return true;
    L18:
        return false;
    }

    public void measure(int r19, int r20) {
        this.height = 0;
        this.width = 0;
        this.positionOffsetY = 0;
        this.totalHeight = 0;
        if (this.isEmpty == false) goto L5;
        return;
    L5:
        this.availableWidth = r19;
        this.reactionLineWidths.clear();
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;
    L7:
        if (r4 >= this.reactionButtons.size()) goto L49;
        ReactionButton r9 = this.reactionButtons.get(r4);
        if (ReactionButton.m12378$$Nest$fgetisSmall(r9) == false) goto L12;
        r9.width = AndroidUtilities.dp(14.0f);
        r9.height = AndroidUtilities.dp(14.0f);
    L43:
        if ((r9.width + r5) <= r19) goto L45;
        this.reactionLineWidths.add(Integer.valueOf(r5));
        r7 = r7 + (r9.height + AndroidUtilities.dp(4.0f));
        r8 = r8 + 1;
        r5 = 0;
    L45:
        r9.x = r5;
        r9.y = r7;
        r9.top = r8;
        r5 = r5 + (r9.width + AndroidUtilities.dp(4.0f));
        if (r5 <= r6) goto L48;
        r6 = r5;
    L48:
        r4 = r4 + 1;
        goto L7
    L12:
        if (r9.isTag == false) goto L21;
        r9.width = AndroidUtilities.dp(42.0f);
        r9.height = AndroidUtilities.dp(26.0f);
        if (r9.hasName == false) goto L16;
        r9.width = (int) (r9.width + (r9.textDrawable.getAnimateToWidth() + AndroidUtilities.dp(8.0f)));
        goto L43
    L16:
        CounterView.CounterDrawable r10 = r9.counterDrawable;
        if (r10 == null) goto L43;
        if (r9.count <= 1) goto L43;
        r9.width += r10.getCurrentWidth() + AndroidUtilities.dp(8.0f);
        goto L43
    L21:
        int r13 = AndroidUtilities.dp(8.0f) + AndroidUtilities.dp(20.0f);
        if (r9.animatedEmojiDrawable == null) goto L24;
        float r102 = 6.0f;
    L25:
        r9.width = r13 + AndroidUtilities.dp(r102);
        if (r9.avatarsDrawable == null) goto L35;
        if (r9.users.size() <= 0) goto L35;
        r9.users.size();
        if (r9.users.size() <= 1) goto L32;
        int r103 = r9.users.size() - 1;
    L33:
        r9.width = (int) (r9.width + (((AndroidUtilities.dp(2.0f) + AndroidUtilities.dp(20.0f)) + ((r103 * AndroidUtilities.dp(20.0f)) * 0.8f)) + AndroidUtilities.dp(1.0f)));
        r9.avatarsDrawable.height = AndroidUtilities.dp(26.0f);
    L41:
        r9.height = AndroidUtilities.dp(26.0f);
        goto L43
    L32:
        r103 = 0;
    L35:
        if (r9.hasName == false) goto L37;
        r9.width = (int) (r9.width + (r9.textDrawable.getAnimateToWidth() + AndroidUtilities.dp(8.0f)));
        goto L41
    L37:
        int r104 = r9.counterDrawable.getCurrentWidth();
        int r12 = r9.width;
        if (r104 <= 0) goto L40;
        r9.width = r12 + (r9.counterDrawable.getCurrentWidth() + AndroidUtilities.dp(8.0f));
        goto L41
    L40:
        r9.width = r12 - AndroidUtilities.dp(1.0f);
        goto L41
    L24:
        r102 = 4.0f;
        goto L25
    L49:
        this.reactionLineWidths.add(Integer.valueOf(r5));
        if (r20 == 5) goto L52;
    L66:
        if (r20 == 1) goto L68;
    L80:
        this.lastLineX = r5;
        if (r20 == 5) goto L85;
        if (r20 == 1) goto L85;
        this.width = r6;
    L87:
        if (this.reactionButtons.size() != 0) goto L89;
        int r3 = 0;
    L90:
        this.height = r7 + r3;
        this.drawServiceShaderBackground = 0.0f;
        return;
    L89:
        r3 = AndroidUtilities.dp(26.0f);
    L85:
        this.width = r19;
        goto L87
    L68:
        if (this.reactionButtons.isEmpty() == true) goto L80;
        int r32 = 0;
    L71:
        if (r32 >= this.reactionButtons.size()) goto L80;
        ReactionButton r92 = this.reactionButtons.get(r32);
        int r105 = r92.top;
        if (r105 >= 0) goto L75;
    L78:
        float r106 = 0.0f;
    L79:
        r92.x = (int) (r92.x + ((r19 - r106) / 2.0f));
        r32 = r32 + 1;
        goto L71
    L75:
        if (r105 >= this.reactionLineWidths.size()) goto L78;
        r106 = this.reactionLineWidths.get(r92.top).intValue();
        goto L79
    L52:
        if (this.reactionButtons.isEmpty() == true) goto L66;
        int r93 = this.reactionButtons.get(0).y;
        int r107 = 0;
        int r132 = 0;
    L54:
        int r14 = this.reactionButtons.size();
        ArrayList<ReactionButton> r15 = this.reactionButtons;
        if (r107 >= r14) goto L63;
        if (r15.get(r107).y == r93) goto L62;
        int r152 = r107 - 1;
        int r33 = r19 - (this.reactionButtons.get(r152).x + this.reactionButtons.get(r152).width);
    L59:
        if (r132 >= r107) goto L61;
        this.reactionButtons.get(r132).x += r33;
        r132 = r132 + 1;
        goto L59
    L61:
        r132 = r107;
    L62:
        r107 = r107 + 1;
        goto L54
    L63:
        int r34 = r15.size() - 1;
        int r94 = r19 - (this.reactionButtons.get(r34).x + this.reactionButtons.get(r34).width);
    L64:
        if (r132 > r34) goto L80;
        this.reactionButtons.get(r132).x += r94;
        r132 = r132 + 1;
        goto L64
    }

    public void onAttachToWindow() {
        this.attached = true;
        int r0 = 0;
    L4:
        if (r0 >= this.reactionButtons.size()) goto L6;
        this.reactionButtons.get(r0).attach();
        r0 = r0 + 1;
        goto L4
    }

    public void onDetachFromWindow() {
        int r0 = 0;
        this.attached = false;
    L4:
        if (r0 >= this.reactionButtons.size()) goto L7;
        this.reactionButtons.get(r0).detach();
        r0 = r0 + 1;
        goto L4
    L7:
        if (this.animatedReactions.isEmpty() == true) goto L12;
        Iterator<ImageReceiver> r02 = this.animatedReactions.values().iterator();
    L10:
        if (r02.hasNext() == false) goto L12;
        r02.next().onDetachedFromWindow();
    L12:
        this.animatedReactions.clear();
    }

    public void recordDrawingState() {
        this.lastDrawingReactionButtons.clear();
        int r0 = 0;
    L4:
        if (r0 >= this.reactionButtons.size()) goto L6;
        this.lastDrawingReactionButtons.put(this.reactionButtons.get(r0).key, this.reactionButtons.get(r0));
        r0 = r0 + 1;
        goto L4
    L6:
        this.wasDrawn = !this.isEmpty;
        this.lastDrawnX = this.x;
        this.lastDrawnY = this.y;
        this.lastDrawnWidth = this.width;
        this.lastDrawTotalHeight = this.totalHeight;
    }

    public void resetAnimation() {
        int r1 = 0;
    L3:
        int r2 = this.outButtons.size();
        ArrayList<ReactionButton> r3 = this.outButtons;
        if (r1 >= r2) goto L6;
        r3.get(r1).detach();
        r1 = r1 + 1;
        goto L3
    L6:
        r3.clear();
        this.animateMove = false;
        this.animateWidth = false;
        this.animateHeight = false;
        int r12 = 0;
    L8:
        if (r12 >= this.reactionButtons.size()) goto L10;
        this.reactionButtons.get(r12).animationType = 0;
        r12 = r12 + 1;
        goto L8
    }

    public void setMessage(MessageObject r21, boolean r22, boolean r23, Theme.ResourcesProvider r24) {
        boolean r4 = r22;
        boolean r5 = r23;
        this.resourcesProvider = r24;
        this.isSmall = r4;
        this.tags = r5;
        this.messageObject = r21;
        ArrayList r7 = new ArrayList(this.reactionButtons);
        boolean r8 = false;
        this.hasUnreadReactions = false;
        this.hasPaidReaction = false;
        this.reactionButtons.clear();
        if (r21 == null) goto L104;
        comparator.dialogId = r21.getDialogId();
        TLRPC.TL_messageReactions r0 = r21.messageOwner.reactions;
        if (r0 != null) goto L7;
    L95:
        boolean r42 = true;
        long r16 = 0;
    L96:
        if (r22 == false) goto L98;
    L103:
        this.hasUnreadReactions = MessageObject.hasUnreadReactions(r21.messageOwner);
    L105:
        int r02 = 0;
    L107:
        if (r02 >= r7.size()) goto L109;
        ((ReactionButton) r7.get(r02)).detach();
        r02 = r02 + 1;
        goto L107
    L109:
        if (r21 == null) goto L111;
        long r2 = r21.getChatId();
    L113:
        if (r2 != r16) goto L115;
    L117:
        boolean r25 = false;
    L119:
        if (ExteraConfig.getHideReactionsInChannels() == false) goto L122;
        if (r25 == false) goto L122;
    L131:
        if (this.tags == true) goto L133;
    L134:
        r8 = r42;
    L135:
        this.isEmpty = r8;
        return;
    L133:
        if (this.reactionButtons.isEmpty() == false) goto L135;
    L122:
        if (ExteraConfig.getHideReactionsInGroups() == false) goto L126;
        if (r2 == r16) goto L126;
        if (r25 == false) goto L131;
    L126:
        if (ExteraConfig.getHideReactionsInPrivateChats() == false) goto L133;
        if (r21 == null) goto L133;
        if ((r21.messageOwner.peer_id instanceof TLRPC.TL_peerUser) == false) goto L133;
    L115:
        if (ChatObject.isChannelAndNotMegaGroup(r2, this.currentAccount) == false) goto L117;
        r25 = r42;
        goto L119
    L111:
        r2 = r16;
        goto L113
    L98:
        if (this.reactionButtons.isEmpty() == true) goto L103;
        ButtonsComparator r03 = comparator;
        r03.currentAccount = this.currentAccount;
        Collections.sort(this.reactionButtons, r03);
        int r04 = 0;
    L101:
        if (r04 >= this.reactionButtons.size()) goto L103;
        TLRPC.ReactionCount r26 = ReactionButton.m12380$$Nest$fgetreactionCount(this.reactionButtons.get(r04));
        int r3 = pointer;
        pointer = r3 + 1;
        r26.lastDrawnPosition = r3;
        r04 = r04 + 1;
        goto L101
    L7:
        if (r0.results == null) goto L95;
        int r05 = 0;
        int r12 = 0;
    L10:
        if (r05 >= r21.messageOwner.reactions.results.size()) goto L12;
        r12 = r12 + r21.messageOwner.reactions.results.get(r05).count;
        r05 = r05 + 1;
        goto L10
    L12:
        MessagesController.getInstance(this.currentAccount).getChatFull(-r21.getDialogId());
        if (r4 == true) goto L15;
        r21.messageOwner.reactions.results.isEmpty();
    L15:
        ArrayList r13 = new ArrayList();
        int r14 = -r13.size();
    L17:
        if (r14 >= r21.messageOwner.reactions.results.size()) goto L95;
        if (r14 >= 0) goto L21;
        TLRPC.ReactionCount r06 = new TLRPC.TL_reactionCount();
        r06.reaction = (TLRPC.Reaction) r13.get(r13.size() + r14);
        r06.chosen = false;
        r06.count = 0;
    L20:
        TLRPC.ReactionCount r32 = r06;
        int r07 = 0;
    L23:
        ArrayList<TLObject> r15 = null;
        if (r07 >= r7.size()) goto L29;
        ReactionButton r27 = (ReactionButton) r7.get(r07);
        r16 = 0;
        if (r27.reaction.equals(r32.reaction) == true) goto L30;
        r07 = r07 + 1;
    L30:
        ReactionLayoutButton r08 = new ReactionLayoutButton(this, r27, r32, r4, r5);
        r08.inGroup = r21.hasValidGroupId();
        this.reactionButtons.add(r08);
        if (this.hasPaidReaction == false) goto L33;
    L36:
        boolean r28 = true;
    L37:
        this.hasPaidReaction = r28;
        if (r22 == true) goto L61;
        if (r23 == true) goto L61;
        if (r21.messageOwner.reactions.recent_reactions == null) goto L61;
        if (r21.getDialogId() <= r16) goto L63;
        if (UserObject.isReplyUser(r21.getDialogId()) == true) goto L63;
        ArrayList<TLObject> r43 = new ArrayList();
        TLRPC.User r52 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        TLRPC.User r9 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r21.getDialogId()));
        if (r32.count != 2) goto L53;
        if (r52 == null) goto L50;
        r43.add(r52);
    L50:
        if (r9 == null) goto L58;
        r43.add(r9);
    L58:
        r08.setUsers(r43);
        if (r43.isEmpty() == true) goto L61;
        r08.count = 0;
        r08.counterDrawable.setCount(0, false);
        goto L61
    L53:
        if (r32.chosen == false) goto L56;
        if (r52 == null) goto L58;
        r43.add(r52);
        goto L58
    L56:
        if (r9 == null) goto L58;
        r43.add(r9);
    L63:
        if (r32.count > 3) goto L61;
        if (r12 > 3) goto L61;
        int r44 = 0;
    L67:
        if (r44 >= r21.messageOwner.reactions.recent_reactions.size()) goto L75;
        TLRPC.MessagePeerReaction r53 = r21.messageOwner.reactions.recent_reactions.get(r44);
        VisibleReaction r92 = VisibleReaction.fromTL(r53.reaction);
        VisibleReaction r10 = VisibleReaction.fromTL(r32.reaction);
        int r19 = r12;
        TLObject r29 = MessagesController.getInstance(this.currentAccount).getUserOrChat(MessageObject.getPeerId(r53.peer_id));
        if (r92.equals(r10) == false) goto L74;
        if (r29 == null) goto L74;
        if (r15 != null) goto L73;
        r15 = new ArrayList();
    L73:
        r15.add(r29);
    L74:
        r44 = r44 + 1;
        r12 = r19;
        goto L67
    L75:
        int r192 = r12;
        r08.setUsers(r15);
        if (r15 != null) goto L78;
    L80:
        if (r22 == false) goto L86;
        r42 = true;
        if (r32.count > 1) goto L84;
    L87:
        if (r22 == false) goto L92;
        if (r14 == 2) goto L96;
    L92:
        if (this.attached == false) goto L94;
        r08.attach();
    L94:
        r14 = r14 + 1;
        r4 = r22;
        r5 = r23;
        r12 = r192;
        goto L17
    L84:
        if (r32.chosen == false) goto L86;
        ReactionLayoutButton r09 = new ReactionLayoutButton(this, null, r32, r22, r23);
        r09.inGroup = r21.hasValidGroupId();
        this.reactionButtons.add(r09);
        this.reactionButtons.get(0).isSelected = false;
        r42 = true;
        this.reactionButtons.get(1).isSelected = true;
        this.reactionButtons.get(0).realCount = 1;
        this.reactionButtons.get(1).realCount = 1;
        this.reactionButtons.get(1).key = this.reactionButtons.get(1).key + "_";
    L86:
        r42 = true;
        goto L87
    L78:
        if (r15.isEmpty() == true) goto L80;
        r08.count = 0;
        r08.counterDrawable.setCount(0, false);
    L61:
        r192 = r12;
        goto L80
    L33:
        if (r08.paid == true) goto L36;
        r28 = false;
        goto L37
    L29:
        r16 = 0;
        r27 = null;
        goto L30
    L21:
        r06 = r21.messageOwner.reactions.results.get(r14);
        goto L20
    L104:
        r42 = true;
        r16 = 0;
        goto L105
    }

    public void setScrimProgress(float r1) {
        this.scrimProgress = r1;
    }

    public void setScrimProgress(float r1, boolean r2) {
        this.scrimProgress = r1;
        this.scrimDirection = r2;
    }

    public void setScrimReaction(Integer r1) {
        this.scrimViewReaction = r1;
    }

    public boolean verifyDrawable(Drawable r1) {
        if ((r1 instanceof AnimatedTextView.AnimatedTextDrawable) == false) goto L6;
        return true;
    L6:
        return false;
    }
}
