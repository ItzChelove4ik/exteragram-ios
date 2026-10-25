package org.telegram.ui.Stories;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import androidx.core.graphics.ColorUtils;
import androidx.core.widget.NestedScrollView;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.ReplyMessageLine;
import org.telegram.ui.Components.StaticLayoutEx;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.URLSpanMono;
import org.telegram.ui.Components.spoilers.SpoilerEffect;
import org.telegram.ui.Components.spoilers.SpoilersClickDetector;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.recorder.StoryEntry;

/* loaded from: classes7.dex */
public class StoryCaptionView extends NestedScrollView implements ItemOptions.ScrimView {
    private Method abortAnimatedScrollMethod;
    private float backgroundAlpha;
    public int blackoutBottomOffset;
    FrameLayout captionContainer;
    public StoryCaptionTextView captionTextview;
    private boolean disableDraw;
    public boolean disableTouches;
    private boolean dontChangeTopMargin;
    boolean expanded;
    int gradientColor;
    private boolean isLandscape;
    private float lastMotionX;
    private float lastMotionY;
    private boolean nestedScrollStarted;
    private float overScrollY;
    private final Paint paint;
    private int pendingTopMargin;
    private int prevHeight;
    private int replytextHash;
    private int replytitleHash;
    private OverScroller scroller;
    private final SpringAnimation springAnimation;
    private float startMotionX;
    private float startMotionY;
    private int textHash;
    TextSelectionHelper.SimpleTextSelectionHelper textSelectionHelper;
    GradientDrawable topOverlayGradient;
    boolean touched;
    private float velocitySign;
    private float velocityY;

    public static class Panel {
        private static CharSequence[] musicSpan;
        private final AnimatedFloat animatedSmall;
        private final Paint backgroundPaint;
        public final ButtonBounce bounce;
        public final RectF bounds;
        private final Path clipRipple;
        private int currentAccount;
        public boolean isRepostMessage;
        private final Paint linePaint;
        private boolean loaded;
        private boolean loading;
        public Integer messageId;
        public TLRPC.Document music;
        public Long peerId;
        public ReplyMessageLine repostLine;
        public final Drawable ripple;
        private boolean small;
        public Integer storyId;
        public CharSequence text;
        public Text textLayout;
        public CharSequence title;
        public Text titleLayout;
        public boolean updateText;
        private View view;
        private Runnable whenLoaded;
        private int width;

        /* renamed from: org.telegram.ui.Stories.StoryCaptionView$Panel$1, reason: invalid class name */
        public class AnonymousClass1 extends CharacterStyle {
            public AnonymousClass1() {
            }

            @Override // android.text.style.CharacterStyle
            public void updateDrawState(TextPaint r2) {
                r2.setAlpha((int) (r2.getAlpha() * 0.66f));
            }
        }

        /* renamed from: $r8$lambda$LAEuqMlk17JD4-dLmra6ZTWZsZU, reason: not valid java name */
        public static /* synthetic */ void m19744$r8$lambda$LAEuqMlk17JD4dLmra6ZTWZsZU(Panel r0, TL_stories.StoryItem r1) {
            r0.lambda$load$0(r1);
        }

        public Panel() {
            this.small = true;
            this.animatedSmall = new AnimatedFloat(0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.bounce = new ButtonBounce(null);
            this.ripple = Theme.createRadSelectorDrawable(553648127, 0, 0);
            this.backgroundPaint = new Paint(1);
            this.linePaint = new Paint(1);
            this.clipRipple = new Path();
            this.bounds = new RectF();
        }

        public static Panel from(int r8, TL_stories.StoryItem r9) {
            Panel r0 = null;
            if (r9 != null) goto L6;
            return null;
        L6:
            if (r9.fwd_from == null) goto L30;
            Panel r02 = new Panel();
            r02.currentAccount = r8;
            TL_stories.StoryFwdHeader r1 = r9.fwd_from;
            TLRPC.Peer r4 = r1.from;
            if (r4 == null) goto L22;
            long r42 = DialogObject.getPeerDialogId(r4);
            r02.peerId = Long.valueOf(r42);
            if (r42 < 0) goto L12;
            r02.title = new SpannableStringBuilder(MessageObject.userSpan()).append(" ").append(UserObject.getUserName(MessagesController.getInstance(r8).getUser(Long.valueOf(r42))));
        L24:
            r02.small = true;
            TL_stories.StoryFwdHeader r82 = r9.fwd_from;
            if ((r82.flags & 4) == 0) goto L27;
            r02.storyId = Integer.valueOf(r82.story_id);
        L27:
            r02.load();
            return r02;
        L12:
            TLRPC.Chat r83 = MessagesController.getInstance(r8).getChat(Long.valueOf(-r42));
            if (ChatObject.isChannelAndNotMegaGroup(r83) == false) goto L15;
            CharSequence r43 = MessageObject.channelSpan();
        L16:
            SpannableStringBuilder r12 = new SpannableStringBuilder(r43).append(" ");
            if (r83 == null) goto L19;
            String r84 = r83.title;
        L20:
            r02.title = r12.append(r84);
            goto L24
        L19:
            r84 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L20
        L15:
            r43 = MessageObject.groupSpan();
            goto L16
        L22:
            if (r1.from_name == null) goto L24;
            r02.title = new SpannableStringBuilder(MessageObject.userSpan()).append(" ").append(r9.fwd_from.from_name);
            goto L24
        L30:
            if (r9.media_areas == null) goto L46;
            int r13 = 0;
            TL_stories.TL_mediaAreaChannelPost r44 = null;
        L33:
            if (r13 >= r9.media_areas.size()) goto L38;
            if ((r9.media_areas.get(r13) instanceof TL_stories.TL_mediaAreaChannelPost) == false) goto L37;
            r44 = (TL_stories.TL_mediaAreaChannelPost) r9.media_areas.get(r13);
        L37:
            r13 = r13 + 1;
            goto L33
        L38:
            if (r44 == null) goto L46;
            TLRPC.Chat r92 = MessagesController.getInstance(r8).getChat(Long.valueOf(r44.channel_id));
            if (r92 == null) goto L46;
            r0 = new Panel();
            r0.peerId = Long.valueOf(-r92.id);
            r0.isRepostMessage = true;
            r0.currentAccount = r8;
            r0.small = true;
            r0.messageId = Integer.valueOf(r44.msg_id);
            if (ChatObject.isChannelAndNotMegaGroup(r92) == false) goto L44;
            CharSequence r14 = MessageObject.channelSpan();
        L45:
            r0.title = new SpannableStringBuilder(r14).append(" ").append(r92.title);
            goto L46
        L44:
            r14 = MessageObject.groupSpan();
        L46:
            return r0;
        }

        public static Panel from(TLRPC.Document r6) {
            if (r6 != null) goto L5;
            return null;
        L5:
            TLRPC.TL_documentAttributeAudio r1 = (TLRPC.TL_documentAttributeAudio) AndroidUtilities.find(r6.attributes, TLRPC.TL_documentAttributeAudio.class);
            if (r1 != null) goto L8;
            return null;
        L8:
            String r2 = r1.title;
            String r12 = r1.performer;
            if (TextUtils.isEmpty(r2) == true) goto L11;
        L13:
            Panel r0 = new Panel();
            r0.small = true;
            r0.music = r6;
            if (TextUtils.isEmpty(r2) == false) goto L18;
            r0.title = new SpannableStringBuilder(musicSpan()).append(" ").append(r12);
            return r0;
        L18:
            if (TextUtils.isEmpty(r12) == false) goto L21;
            r0.title = new SpannableStringBuilder(musicSpan()).append(" ").append(r2);
            return r0;
        L21:
            SpannableStringBuilder r62 = new SpannableStringBuilder(musicSpan()).append(" ").append(r12);
            r0.title = r62;
            int r63 = r62.length();
            ((SpannableStringBuilder) r0.title).append(" ・ ");
            ((SpannableStringBuilder) r0.title).setSpan(new AnonymousClass1(), r63, r0.title.length(), 33);
            ((SpannableStringBuilder) r0.title).append(r2);
            return r0;
        L11:
            if (TextUtils.isEmpty(r12) == false) goto L13;
            return null;
        }

        public static Panel from(StoriesController.UploadingStory r6) {
            Panel r0 = null;
            if (r6 == null) goto L26;
            StoryEntry r1 = r6.entry;
            if (r1 == null) goto L26;
            if (r1.isRepost == false) goto L12;
            Panel r02 = new Panel();
            StoryEntry r62 = r6.entry;
            r02.title = r62.repostPeerName;
            String r63 = r62.repostCaption;
            r02.text = r63;
            r02.small = TextUtils.isEmpty(r63);
            return r02;
        L12:
            if (r1.isRepostMessage == false) goto L26;
            ArrayList<MessageObject> r12 = r1.messageObjects;
            if (r12 == null) goto L26;
            if (r12.size() <= 0) goto L26;
            MessageObject r64 = r6.entry.messageObjects.get(0);
            long r13 = StoryEntry.getRepostDialogId(r64);
            if (r13 >= 0) goto L26;
            TLRPC.Chat r3 = MessagesController.getInstance(r64.currentAccount).getChat(Long.valueOf(-r13));
            if (r3 == null) goto L26;
            r0 = new Panel();
            r0.peerId = Long.valueOf(r13);
            r0.isRepostMessage = true;
            r0.currentAccount = r64.currentAccount;
            r0.small = true;
            r0.messageId = Integer.valueOf(StoryEntry.getRepostMessageId(r64));
            if (ChatObject.isChannelAndNotMegaGroup(r3) == false) goto L24;
            CharSequence r14 = MessageObject.channelSpan();
        L25:
            r0.title = new SpannableStringBuilder(r14).append(" ").append(r3.title);
            goto L26
        L24:
            r14 = MessageObject.groupSpan();
        L26:
            return r0;
        }

        private /* synthetic */ void lambda$load$0(TL_stories.StoryItem r2) {
            this.loaded = true;
            if (r2 == null) goto L13;
            String r22 = r2.caption;
            if (r22 == null) goto L14;
            this.updateText = true;
            this.text = r22;
            this.small = TextUtils.isEmpty(r22);
            View r23 = this.view;
            if (r23 == null) goto L9;
            r23.invalidate();
        L9:
            Runnable r1 = this.whenLoaded;
            if (r1 == null) goto L15;
            r1.run();
            return;
        L15:
            return;
        L14:
            return;
        }

        public static CharSequence musicSpan() {
            return musicSpan(0);
        }

        public static CharSequence musicSpan(int r5) {
            if (musicSpan != null) goto L5;
            musicSpan = new CharSequence[2];
        L5:
            CharSequence[] r0 = musicSpan;
            if (r0[r5] != null) goto L12;
            r0[r5] = new SpannableStringBuilder("u");
            ColoredImageSpan r02 = new ColoredImageSpan(R.drawable.filled_widget_music);
            r02.setSize(AndroidUtilities.dp(16.0f));
            r02.spaceScaleX = 1.0f;
            if (r5 != 0) goto L10;
            r02.translate(-AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f));
        L10:
            ((SpannableStringBuilder) musicSpan[r5]).setSpan(r02, 0, 1, 33);
        L12:
            return musicSpan[r5];
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.CharSequence] */
        public void draw(Canvas r19, float r20) {
            Text r3 = this.titleLayout;
            String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r3 != null) goto L16;
            CharSequence r7 = this.title;
            if (r7 != null) goto L7;
            r7 = _UrlKt.FRAGMENT_ENCODE_SET;
        L7:
            TLRPC.Document r8 = this.music;
            if (r8 == null) goto L10;
            float r9 = 12.0f;
        L11:
            if (r8 == null) goto L13;
            Typeface r82 = null;
        L14:
            this.titleLayout = new Text(r7, r9, r82);
            goto L16
        L13:
            r82 = AndroidUtilities.bold();
            goto L14
        L10:
            r9 = 14.0f;
        L16:
            if (this.textLayout != null) goto L18;
        L19:
            ?? r72 = this.text;
            if (r72 == 0) goto L23;
            r5 = r72;
        L23:
            this.textLayout = new Text(r5, 14.0f);
        L24:
            float r32 = this.animatedSmall.set(this.small);
            this.backgroundPaint.setColor(TLObject.FLAG_30);
            int r52 = (int) Math.min(r20, AndroidUtilities.lerp(AndroidUtilities.dp(20.0f), AndroidUtilities.dp(18.0f), r32) + Math.max(this.titleLayout.getCurrentWidth(), this.textLayout.getCurrentWidth()));
            this.width = r52;
            int r83 = AndroidUtilities.lerp(AndroidUtilities.dp(42.0f), AndroidUtilities.dp(22.0f), r32);
            float r10 = r52;
            this.bounds.set(0.0f, 0.0f, r10, r83);
            r19.save();
            float r92 = this.bounce.getScale(0.02f);
            r19.scale(r92, r92, this.bounds.centerX(), this.bounds.centerY());
            float r11 = AndroidUtilities.lerp(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(11.0f), r32);
            r19.drawRoundRect(this.bounds, r11, r11, this.backgroundPaint);
            r19.save();
            this.clipRipple.rewind();
            this.clipRipple.addRoundRect(this.bounds, r11, r11, Path.Direction.CW);
            r19.clipPath(this.clipRipple);
            this.ripple.setBounds(0, 0, r52, r83);
            this.ripple.draw(r19);
            r19.restore();
            r19.save();
            r19.clipRect(0, 0, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(42.0f));
            RectF r4 = AndroidUtilities.rectTmp;
            r4.set(0.0f, 0.0f, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(42.0f));
            this.linePaint.setColor(-1);
            float r112 = 1.0f - r32;
            this.linePaint.setAlpha((int) (255.0f * r112));
            r19.drawRoundRect(r4, AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f), this.linePaint);
            r19.restore();
            int r53 = r52 - AndroidUtilities.dp(20.0f);
            if (r10 >= r20) goto L27;
            r53 = (int) Math.min(r53 + AndroidUtilities.dp(12.0f), r20 - AndroidUtilities.dp(20.0f));
        L27:
            float r73 = r53;
            this.titleLayout.ellipsize(r73).draw(r19, AndroidUtilities.lerp(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(7.0f), r32), AndroidUtilities.lerp(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(11.0f), r32), -1, 1.0f);
            this.textLayout.ellipsize(r73).draw(r19, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(30.0f), -1, r112);
            r19.restore();
            return;
        L18:
            if (this.updateText == false) goto L24;
            goto L19
        }

        public int height() {
            if (this.small == false) goto L7;
            float r0 = 22.0f;
        L6:
            return AndroidUtilities.dp(r0);
        L7:
            r0 = 42.0f;
            goto L6
        }

        public void listen(View r1, Runnable r2) {
            this.view = r1;
            this.whenLoaded = r2;
            this.repostLine = new ReplyMessageLine(r1);
            this.ripple.setCallback(r1);
            this.animatedSmall.setParent(r1);
            this.bounce.setView(r1);
            load();
        }

        public void load() {
            if (this.loaded == false) goto L5;
            return;
        L5:
            if (this.loading == false) goto L7;
            return;
        L7:
            if (this.peerId != null) goto L9;
            return;
        L9:
            if (this.storyId != null) goto L11;
            return;
        L11:
            if (this.view == null) goto L18;
            this.loading = true;
            MessagesController.getInstance(this.currentAccount).getStoriesController().resolveStoryLink(this.peerId.longValue(), this.storyId.intValue(), new StoryCaptionView$Panel$$ExternalSyntheticLambda0(this));
            return;
        }

        public void setPressed(boolean r5, float r6, float r7) {
            this.bounce.setPressed(r5);
            Drawable r0 = this.ripple;
            if (r5 == false) goto L5;
            int[] r2 = {android.R.attr.state_pressed, android.R.attr.state_enabled};
        L6:
            r0.setState(r2);
            if (r5 == false) goto L10;
            this.ripple.setHotspot(r6, r7);
            return;
        L10:
            return;
        L5:
            r2 = new int[0];
            goto L6
        }

        public int width() {
            return this.width;
        }
    }

    public class StoryCaptionTextView extends View implements TextSelectionHelper.SimpleSelectabeleView {
        public boolean allowClickSpoilers;
        private final PorterDuffColorFilter emojiColorFilter;
        int horizontalPadding;
        private boolean isSpoilersRevealed;
        private Path path;
        float progressToExpand;
        boolean shouldCollapse;
        StaticLayout showMore;
        TextPaint showMorePaint;
        float showMoreX;
        float showMoreY;
        int sizeCached;
        TextState[] state;
        TextPaint textPaint;
        final /* synthetic */ StoryCaptionView this$0;
        private ValueAnimator updateAnimator;
        public float updateT;
        public boolean updating;
        int verticalPadding;
        private final Paint xRefGradinetPaint;
        private final Paint xRefPaint;

        /* renamed from: org.telegram.ui.Stories.StoryCaptionView$StoryCaptionTextView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ StoryCaptionTextView this$1;

            public AnonymousClass1(StoryCaptionTextView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                StoryCaptionTextView r22 = this.this$1;
                r22.updating = false;
                r22.updateT = 0.0f;
                r22.invalidate();
                this.this$1.requestLayout();
                this.this$1.this$0.requestLayout();
            }
        }

        public class LineInfo {
            float collapsedX;
            float collapsedY;
            float finalX;
            float finalY;
            public AnimatedEmojiSpan.EmojiGroupedSpans layoutEmoji;
            StaticLayout staticLayout;
            final /* synthetic */ StoryCaptionTextView this$1;

            public LineInfo(StoryCaptionTextView r1) {
                this.this$1 = r1;
            }
        }

        public class TextState {
            public Panel bottomPanel;
            private final SpoilersClickDetector clickDetector;
            int collapsedTextHeight;
            StaticLayout firstLayout;
            private AnimatedEmojiSpan.EmojiGroupedSpans firstLayoutEmoji;
            StaticLayout fullLayout;
            private AnimatedEmojiSpan.EmojiGroupedSpans fullLayoutEmoji;
            private final LinkSpanDrawable.LinkCollector links;
            private final LoadingDrawable loadingDrawable;
            private Path loadingPath;
            LineInfo[] nextLinesLayouts;
            final AtomicReference<Layout> patchedLayout;
            private AnimatedEmojiSpan pressedEmoji;
            private LinkSpanDrawable<CharacterStyle> pressedLink;
            protected final List<SpoilerEffect> spoilers;
            private final Stack<SpoilerEffect> spoilersPool;
            CharSequence text;
            int textHeight;
            final /* synthetic */ StoryCaptionTextView this$1;
            public Panel topPanel;
            public final AnimatedFloat translateT;
            public boolean translating;

            /* renamed from: $r8$lambda$8MzfPd-xtI5HsgKqpJrWPwM3yNg, reason: not valid java name */
            public static /* synthetic */ void m19751$r8$lambda$8MzfPdxtI5HsgKqpJrWPwM3yNg(TextState r0, SpoilerEffect r1, float r2, float r3) {
                r0.lambda$new$2(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$92FsZx1vTKNvsRCJM_qhnuOQOa4(TextState r0) {
                r0.lambda$setup$4();
            }

            public static /* synthetic */ void $r8$lambda$V4ugOCRAZ53poSsNf7ISk7SaAlg(TextState r0) {
                r0.lambda$new$1();
            }

            public static /* synthetic */ void $r8$lambda$Zq5ARveuje14XhQrLtPZC6TPGJY(TextState r0) {
                r0.lambda$setup$3();
            }

            /* renamed from: $r8$lambda$n9dqVH_8EPT2_7BMxEua-oTSTwU, reason: not valid java name */
            public static /* synthetic */ void m19752$r8$lambda$n9dqVH_8EPT2_7BMxEuaoTSTwU(TextState r0, LinkSpanDrawable r1) {
                r0.lambda$touch$5(r1);
            }

            /* renamed from: $r8$lambda$oucM0H_VjXnvak5mdIcy-Dcusv8, reason: not valid java name */
            public static /* synthetic */ void m19753$r8$lambda$oucM0H_VjXnvak5mdIcyDcusv8(TextState r0) {
                r0.lambda$new$0();
            }

            /* renamed from: -$$Nest$fgetclickDetector, reason: not valid java name */
            public static /* bridge */ /* synthetic */ SpoilersClickDetector m19754$$Nest$fgetclickDetector(TextState r0) {
                return r0.clickDetector;
            }

            /* renamed from: -$$Nest$fgetlinks, reason: not valid java name */
            public static /* bridge */ /* synthetic */ LinkSpanDrawable.LinkCollector m19755$$Nest$fgetlinks(TextState r0) {
                return r0.links;
            }

            /* renamed from: -$$Nest$fgetloadingDrawable, reason: not valid java name */
            public static /* bridge */ /* synthetic */ LoadingDrawable m19756$$Nest$fgetloadingDrawable(TextState r0) {
                return r0.loadingDrawable;
            }

            /* renamed from: -$$Nest$fputpressedLink, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19757$$Nest$fputpressedLink(TextState r0, LinkSpanDrawable r1) {
                r0.pressedLink = r1;
            }

            public TextState(StoryCaptionTextView r10) {
                this.this$1 = r10;
                this.links = new LinkSpanDrawable.LinkCollector(r10);
                ArrayList r0 = new ArrayList();
                this.spoilers = r0;
                this.spoilersPool = new Stack();
                this.text = _UrlKt.FRAGMENT_ENCODE_SET;
                this.translateT = new AnimatedFloat(r10.this$0, 0, 400, CubicBezierInterpolator.EASE_OUT_QUINT);
                this.loadingPath = new Path();
                this.patchedLayout = new AtomicReference();
                this.clickDetector = new SpoilersClickDetector(r10, r0, new StoryCaptionView$StoryCaptionTextView$TextState$$ExternalSyntheticLambda0(this));
                LoadingDrawable r02 = new LoadingDrawable();
                this.loadingDrawable = r02;
                r02.usePath(this.loadingPath);
                r02.setRadiiDp(4.0f);
                r02.setColors(Theme.multAlpha(-1, 0.3f), Theme.multAlpha(-1, 0.1f), Theme.multAlpha(-1, 0.2f), Theme.multAlpha(-1, 0.7f));
                r02.setCallback(r10);
            }

            private void drawInternal(Canvas r18, float r19) {
                if (this.topPanel == null) goto L5;
                r18.save();
                StoryCaptionTextView r2 = this.this$1;
                r18.translate(r2.horizontalPadding, r2.verticalPadding);
                Panel r22 = this.topPanel;
                int r3 = this.this$1.getWidth();
                int r4 = this.this$1.horizontalPadding;
                r22.draw(r18, (r3 - r4) - r4);
                int r23 = this.topPanel.height() + AndroidUtilities.dp(8.0f);
                r18.restore();
                int r12 = r23;
            L6:
                r18.save();
                StoryCaptionTextView r24 = this.this$1;
                r18.translate(r24.horizontalPadding, r24.verticalPadding + r12);
                if (this.links.draw(r18) == false) goto L9;
                this.this$1.invalidate();
            L9:
                r18.restore();
                if (r19 <= 0.0f) goto L12;
                boolean r15 = true;
            L13:
                this.loadingPath.rewind();
                if (this.spoilers.isEmpty() == false) goto L50;
                if (this.firstLayout == null) goto L50;
                if (this.this$1.this$0.textSelectionHelper.isInSelectionMode() == false) goto L22;
                r18.save();
                StoryCaptionTextView r25 = this.this$1;
                r18.translate(r25.horizontalPadding, r25.verticalPadding + r12);
                this.this$1.this$0.textSelectionHelper.draw(r18);
                r18.restore();
            L22:
                if (this.firstLayout == null) goto L27;
                r18.save();
                StoryCaptionTextView r26 = this.this$1;
                r18.translate(r26.horizontalPadding, r26.verticalPadding + r12);
                drawLayout(this.firstLayout, r18, this.spoilers);
                AnimatedEmojiSpan.EmojiGroupedSpans r32 = AnimatedEmojiSpan.update(0, this.this$1, this.firstLayoutEmoji, new Layout[]{this.firstLayout});
                this.firstLayoutEmoji = r32;
                AnimatedEmojiSpan.drawAnimatedEmojis(r18, this.firstLayout, r32, 0.0f, this.spoilers, 0.0f, 0.0f, 0.0f, 1.0f, StoryCaptionTextView.m19746$$Nest$fgetemojiColorFilter(this.this$1));
                r18.restore();
                if (r15 == false) goto L27;
                StaticLayout r27 = this.firstLayout;
                StoryCaptionTextView r33 = this.this$1;
                putLayoutRects(r27, r33.horizontalPadding, r33.verticalPadding + r12);
            L27:
                if (this.nextLinesLayouts == null) goto L58;
                int r8 = 0;
            L29:
                LineInfo[] r28 = this.nextLinesLayouts;
                if (r8 >= r28.length) goto L58;
                LineInfo r9 = r28[r8];
                if (r9 != null) goto L34;
            L33:
                int r16 = r8;
            L48:
                r8 = r16 + 1;
                goto L29
            L34:
                r18.save();
                float r29 = r9.collapsedX;
                float r34 = r9.finalX;
                StoryCaptionTextView r5 = this.this$1;
                if (r29 == r34) goto L37;
                r16 = r8;
                float r210 = AndroidUtilities.lerp(r29, r34, r5.progressToExpand);
                float r35 = AndroidUtilities.lerp(r9.collapsedY, r9.finalY, CubicBezierInterpolator.EASE_OUT.getInterpolation(this.this$1.progressToExpand));
                StoryCaptionTextView r42 = this.this$1;
                r18.translate(r42.horizontalPadding + r210, (r42.verticalPadding + r12) + r35);
                if (r15 == false) goto L46;
                StaticLayout r43 = r9.staticLayout;
                StoryCaptionTextView r52 = this.this$1;
                putLayoutRects(r43, r52.horizontalPadding + r210, (r52.verticalPadding + r12) + r35);
            L46:
                r9.staticLayout.draw(r18);
                AnimatedEmojiSpan.EmojiGroupedSpans r36 = AnimatedEmojiSpan.update(0, this.this$1, r9.layoutEmoji, new Layout[]{r9.staticLayout});
                r9.layoutEmoji = r36;
                AnimatedEmojiSpan.drawAnimatedEmojis(r18, r9.staticLayout, r36, 0.0f, this.spoilers, 0.0f, 0.0f, 0.0f, 1.0f, StoryCaptionTextView.m19746$$Nest$fgetemojiColorFilter(this.this$1));
            L47:
                r18.restore();
                goto L48
            L37:
                if (r5.progressToExpand == 0.0f) goto L33;
                r18.translate(r5.horizontalPadding + r34, (r5.verticalPadding + r12) + r9.finalY);
                r18.saveLayerAlpha(0.0f, 0.0f, r9.staticLayout.getWidth(), r9.staticLayout.getHeight(), (int) (this.this$1.progressToExpand * 255.0f), 31);
                drawLayout(r9.staticLayout, r18, this.spoilers);
                if (r15 == false) goto L42;
                StaticLayout r211 = r9.staticLayout;
                StoryCaptionTextView r37 = this.this$1;
                putLayoutRects(r211, r37.horizontalPadding + r9.finalX, (r37.verticalPadding + r12) + r9.finalY);
            L42:
                r9.staticLayout.draw(r18);
                AnimatedEmojiSpan.EmojiGroupedSpans r38 = AnimatedEmojiSpan.update(0, this.this$1, r9.layoutEmoji, new Layout[]{r9.staticLayout});
                r9.layoutEmoji = r38;
                StaticLayout r212 = r9.staticLayout;
                List<SpoilerEffect> r53 = this.spoilers;
                StoryCaptionTextView r44 = this.this$1;
                r16 = r8;
                AnimatedEmojiSpan.drawAnimatedEmojis(r18, r212, r38, 0.0f, r53, 0.0f, 0.0f, 0.0f, r44.progressToExpand, StoryCaptionTextView.m19746$$Nest$fgetemojiColorFilter(r44));
                r18.restore();
            L58:
                if (this.bottomPanel == null) goto L64;
                r18.save();
                StoryCaptionTextView r213 = this.this$1;
                r18.translate(r213.horizontalPadding, (r213.verticalPadding + AndroidUtilities.lerp(this.collapsedTextHeight, this.textHeight, r213.progressToExpand)) - this.bottomPanel.height());
                Panel r214 = this.bottomPanel;
                int r39 = this.this$1.getWidth();
                int r0 = this.this$1.horizontalPadding;
                r214.draw(r18, (r39 - r0) - r0);
                r18.restore();
                return;
            L64:
                return;
            L50:
                if (this.fullLayout == null) goto L58;
                r18.save();
                StoryCaptionTextView r215 = this.this$1;
                r18.translate(r215.horizontalPadding, r215.verticalPadding + r12);
                if (this.this$1.this$0.textSelectionHelper.isInSelectionMode() == false) goto L54;
                this.this$1.this$0.textSelectionHelper.draw(r18);
            L54:
                drawLayout(this.fullLayout, r18, this.spoilers);
                AnimatedEmojiSpan.EmojiGroupedSpans r310 = AnimatedEmojiSpan.update(0, this.this$1, this.fullLayoutEmoji, new Layout[]{this.fullLayout});
                this.fullLayoutEmoji = r310;
                AnimatedEmojiSpan.drawAnimatedEmojis(r18, this.fullLayout, r310, 0.0f, this.spoilers, 0.0f, 0.0f, 0.0f, 1.0f, StoryCaptionTextView.m19746$$Nest$fgetemojiColorFilter(this.this$1));
                r18.restore();
                if (r15 == false) goto L58;
                StaticLayout r216 = this.fullLayout;
                StoryCaptionTextView r311 = this.this$1;
                putLayoutRects(r216, r311.horizontalPadding, r311.verticalPadding + r12);
                goto L58
            L12:
                r15 = false;
                goto L13
            L5:
                r12 = 0;
                goto L6
            }

            private void drawLayout(StaticLayout r12, Canvas r13, List<SpoilerEffect> r14) {
                if (r14.isEmpty() == true) goto L6;
                SpoilerEffect.renderWithRipple(this.this$1, false, -1, 0, this.patchedLayout, 0, r12, r14, r13, false);
                return;
            L6:
                r12.draw(r13);
            }

            private /* synthetic */ void lambda$new$0() {
                StoryCaptionTextView.m19748$$Nest$fputisSpoilersRevealed(this.this$1, true);
            }

            private /* synthetic */ void lambda$new$1() {
                this.this$1.post(new StoryCaptionView$StoryCaptionTextView$TextState$$ExternalSyntheticLambda6(this));
            }

            private /* synthetic */ void lambda$new$2(SpoilerEffect r7, float r8, float r9) {
                if (StoryCaptionTextView.m19747$$Nest$fgetisSpoilersRevealed(this.this$1) == true) goto L9;
                r7.setOnRippleEndCallback(new StoryCaptionView$StoryCaptionTextView$TextState$$ExternalSyntheticLambda5(this));
                float r72 = (float) Math.sqrt(Math.pow(this.this$1.getWidth(), 2.0d) + Math.pow(this.this$1.getHeight(), 2.0d));
                Iterator<SpoilerEffect> r6 = this.spoilers.iterator();
            L7:
                if (r6.hasNext() == false) goto L11;
                r6.next().startRipple(r8, r9, r72);
                goto L7
            L11:
                return;
            }

            private /* synthetic */ void lambda$setup$3() {
                StoryCaptionTextView r0 = this.this$1;
                r0.sizeCached = 0;
                r0.requestLayout();
                this.this$1.this$0.updateTopMargin();
                this.this$1.this$0.requestLayout();
            }

            private /* synthetic */ void lambda$setup$4() {
                StoryCaptionTextView r0 = this.this$1;
                r0.sizeCached = 0;
                r0.requestLayout();
                this.this$1.this$0.updateTopMargin();
                this.this$1.this$0.requestLayout();
            }

            private /* synthetic */ void lambda$touch$5(LinkSpanDrawable r5) {
                LinkSpanDrawable<CharacterStyle> r0 = this.pressedLink;
                if (r5 != r0) goto L9;
                if (r0 != null) goto L6;
                return;
            L6:
                if ((r0.getSpan() instanceof URLSpan) == false) goto L11;
                StoryCaptionView r52 = this.this$1.this$0;
                URLSpan r02 = (URLSpan) this.pressedLink.getSpan();
                StoryCaptionTextView r1 = this.this$1;
                LinkSpanDrawable.LinkCollector r2 = this.links;
                Objects.requireNonNull(r2);
                r52.onLinkLongPress(r02, r1, new StoryCaptionView$StoryCaptionTextView$TextState$$ExternalSyntheticLambda4(r2));
                this.pressedLink = null;
                return;
            L11:
                return;
            }

            private void putLayoutRects(Layout r13, float r14, float r15) {
                float r0 = 0.0f;
                int r1 = 0;
            L4:
                if (r1 >= r13.getLineCount()) goto L12;
                float r2 = r13.getLineLeft(r1) - (this.this$1.horizontalPadding / 3.0f);
                float r3 = r13.getLineRight(r1) + (this.this$1.horizontalPadding / 3.0f);
                if (r1 != 0) goto L8;
                r0 = r13.getLineTop(r1) - (this.this$1.verticalPadding / 3.0f);
            L8:
                float r5 = r13.getLineBottom(r1);
                if (r1 < (r13.getLineCount() - 1)) goto L11;
                r5 = r5 + (this.this$1.verticalPadding / 3.0f);
            L11:
                this.loadingPath.addRect(r14 + r2, r15 + r0, r14 + r3, r15 + r5, Path.Direction.CW);
                r1 = r1 + 1;
                r0 = r5;
                goto L4
            }

            public int collapsedTextHeight(int r5) {
                Panel r0 = this.topPanel;
                int r1 = 0;
                if (r0 == null) goto L5;
                int r02 = r0.height() + AndroidUtilities.dp(8.0f);
            L6:
                Panel r3 = this.bottomPanel;
                if (r3 == null) goto L9;
                r1 = r3.height() + AndroidUtilities.dp(8.0f);
            L9:
                int r03 = r02 + r1;
                StaticLayout r12 = this.fullLayout;
                if (r12 != null) goto L15;
                int r04 = this.this$1.verticalPadding * 2;
                int r4 = this.textHeight;
            L12:
                r03 = r04 + r4;
            L14:
                return r5 - r03;
            L15:
                int r13 = r12.getLineCount();
                StoryCaptionTextView r2 = this.this$1;
                if (r2.shouldCollapse == true) goto L18;
                r04 = r2.verticalPadding * 2;
                r4 = this.textHeight;
                goto L12
            L18:
                r5 = r5 - (this.this$1.textPaint.getFontMetricsInt(null) * (Math.min(3, r13) + 1));
                goto L14
            L5:
                r02 = 0;
                goto L6
            }

            public void detach() {
                AnimatedEmojiSpan.release(this.this$1, this.fullLayoutEmoji);
                AnimatedEmojiSpan.release(this.this$1, this.firstLayoutEmoji);
                if (this.nextLinesLayouts == null) goto L12;
                int r0 = 0;
            L5:
                LineInfo[] r1 = this.nextLinesLayouts;
                if (r0 >= r1.length) goto L16;
                LineInfo r12 = r1[r0];
                if (r12 == null) goto L11;
                AnimatedEmojiSpan.release(this.this$1, r12.layoutEmoji);
            L11:
                r0 = r0 + 1;
                goto L5
            L16:
                return;
            }

            public void draw(Canvas r12, float r13) {
                float r0 = this.translateT.set(this.translating);
                if (r13 <= 0.0f) goto L17;
                float r132 = AndroidUtilities.lerp(r13, 0.7f * r13, r0);
                if (r132 < 1.0f) goto L8;
                drawInternal(r12, r0);
                Canvas r4 = r12;
            L10:
                if (r0 <= 0.0f) goto L12;
            L15:
                this.loadingDrawable.setAlpha((int) ((r0 * 255.0f) * r132));
                this.loadingDrawable.draw(r4);
                this.this$1.invalidate();
                return;
            L12:
                if (this.translating == true) goto L15;
                return;
            L8:
                r4 = r12;
                r4.saveLayerAlpha(0.0f, 0.0f, this.this$1.this$0.getWidth(), this.this$1.this$0.getHeight(), (int) (r132 * 255.0f), 31);
                drawInternal(r4, r0);
                r4.restore();
                goto L10
            }

            public void measure(int r12) {
                if (TextUtils.isEmpty(this.text) == false) goto L15;
                this.fullLayout = null;
                this.textHeight = 0;
                Panel r122 = this.topPanel;
                if (r122 == null) goto L7;
                this.textHeight = r122.height() + AndroidUtilities.dp(4.0f);
            L7:
                Panel r123 = this.bottomPanel;
                if (r123 == null) goto L10;
                this.textHeight += r123.height() + AndroidUtilities.dp(4.0f);
            L10:
                this.collapsedTextHeight = this.textHeight;
                StoryCaptionTextView r124 = this.this$1;
                if (this != r124.state[0]) goto L13;
                r124.showMore = null;
            L13:
                this.firstLayout = null;
                this.spoilersPool.addAll(this.spoilers);
                this.spoilers.clear();
                return;
            L15:
                StoryCaptionTextView r0 = this.this$1;
                StaticLayout r02 = StoryCaptionTextView.m19750$$Nest$mmakeTextLayout(r0, r0.textPaint, this.text, r12);
                this.fullLayout = r02;
                this.textHeight = r02.getHeight();
                Panel r03 = this.topPanel;
                if (r03 == null) goto L18;
                int r04 = r03.height() + AndroidUtilities.dp(8.0f);
            L19:
                Panel r4 = this.bottomPanel;
                if (r4 == null) goto L22;
                this.textHeight += r4.height() + AndroidUtilities.dp(8.0f);
            L22:
                this.textHeight += r04;
                float r42 = this.this$1.textPaint.measureText(" ");
                StoryCaptionTextView r5 = this.this$1;
                int r7 = 3;
                if (this.fullLayout.getLineCount() <= 3) goto L25;
                boolean r6 = true;
            L26:
                r5.shouldCollapse = r6;
                if (this.this$1.shouldCollapse == true) goto L29;
            L33:
                StoryCaptionTextView r52 = this.this$1;
                if (r52.shouldCollapse == false) goto L69;
                float r53 = this.fullLayout.getLineTop(2) + this.fullLayout.getTopPadding();
                if (this != this.this$1.state[0]) goto L38;
                String r8 = LocaleController.getString(R.string.ShowMore);
                StoryCaptionTextView r9 = this.this$1;
                r9.showMore = StoryCaptionTextView.m19750$$Nest$mmakeTextLayout(r9, r9.showMorePaint, r8, r12);
                this.this$1.showMoreY = ((r9.verticalPadding + r04) + r53) - AndroidUtilities.dpf2(0.3f);
                StoryCaptionTextView r05 = this.this$1;
                r05.showMoreX = (r05.horizontalPadding + r12) - r05.showMorePaint.measureText(r8);
            L38:
                int r06 = this.fullLayout.getLineBottom(2) + this.fullLayout.getTopPadding();
                Panel r82 = this.topPanel;
                if (r82 == null) goto L41;
                int r83 = r82.height() + AndroidUtilities.dp(8.0f);
            L42:
                int r07 = r06 + r83;
                Panel r84 = this.bottomPanel;
                if (r84 == null) goto L45;
                int r85 = r84.height() + AndroidUtilities.dp(8.0f);
            L46:
                this.collapsedTextHeight = r07 + r85;
                StoryCaptionTextView r08 = this.this$1;
                this.firstLayout = StoryCaptionTextView.m19750$$Nest$mmakeTextLayout(r08, r08.textPaint, this.text.subSequence(0, this.fullLayout.getLineEnd(2)), r12);
                this.spoilersPool.addAll(this.spoilers);
                this.spoilers.clear();
                SpoilerEffect.addSpoilers(this.this$1.this$0, this.fullLayout, this.spoilersPool, this.spoilers);
                float r09 = this.fullLayout.getLineRight(2) + r42;
                if (this.nextLinesLayouts == null) goto L56;
                int r3 = 0;
            L49:
                LineInfo[] r62 = this.nextLinesLayouts;
                if (r3 >= r62.length) goto L56;
                LineInfo r63 = r62[r3];
                if (r63 == null) goto L55;
                AnimatedEmojiSpan.release(this.this$1.this$0, r63.layoutEmoji);
            L55:
                r3 = r3 + 1;
            L56:
                this.nextLinesLayouts = new LineInfo[this.fullLayout.getLineCount() - 3];
                if (this.spoilers.isEmpty() == true) goto L59;
            L72:
                SpoilersClickDetector r125 = this.clickDetector;
                StoryCaptionTextView r11 = this.this$1;
                r125.setAdditionalOffsets(r11.horizontalPadding, r11.verticalPadding);
                return;
            L59:
                if (r7 >= this.fullLayout.getLineCount()) goto L72;
                int r32 = this.fullLayout.getLineStart(r7);
                int r64 = this.fullLayout.getLineEnd(r7);
                CharSequence r33 = this.text.subSequence(Math.min(r32, r64), Math.max(r32, r64));
                if (TextUtils.isEmpty(r33) == false) goto L63;
                this.nextLinesLayouts[r7 - 3] = null;
            L67:
                r7 = r7 + 1;
                goto L59
            L63:
                StoryCaptionTextView r65 = this.this$1;
                StaticLayout r34 = StoryCaptionTextView.m19750$$Nest$mmakeTextLayout(r65, r65.textPaint, r33, r12);
                LineInfo r66 = new LineInfo(this.this$1);
                this.nextLinesLayouts[r7 - 3] = r66;
                r66.staticLayout = r34;
                r66.finalX = this.fullLayout.getLineLeft(r7);
                r66.finalY = this.fullLayout.getLineTop(r7) + this.fullLayout.getTopPadding();
                if (r09 >= (this.this$1.showMoreX - AndroidUtilities.dp(16.0f))) goto L66;
                r66.collapsedY = r53;
                r66.collapsedX = r09;
                r09 = r09 + (Math.abs(r34.getLineRight(0) - r34.getLineLeft(0)) + r42);
                goto L67
            L66:
                r66.collapsedY = r66.finalY;
                r66.collapsedX = r66.finalX;
                goto L67
            L45:
                r85 = 0;
                goto L46
            L41:
                r83 = 0;
                goto L42
            L69:
                if (this != r52.state[0]) goto L71;
                r52.showMore = null;
            L71:
                this.firstLayout = null;
                this.collapsedTextHeight = this.textHeight;
                this.spoilersPool.addAll(this.spoilers);
                this.spoilers.clear();
                SpoilerEffect.addSpoilers(this.this$1, this.fullLayout, this.spoilersPool, this.spoilers);
                goto L72
            L29:
                if (this.fullLayout.getLineCount() != 4) goto L33;
                if (TextUtils.getTrimmedLength(this.text.subSequence(this.fullLayout.getLineStart(2), this.fullLayout.getLineEnd(2))) != 0) goto L33;
                this.this$1.shouldCollapse = false;
                goto L33
            L25:
                r6 = false;
                goto L26
            L18:
                r04 = 0;
                goto L19
            }

            public void setup(CharSequence r1, Panel r2, Panel r3) {
                this.text = r1;
                this.topPanel = r2;
                this.bottomPanel = r3;
                if (r2 == null) goto L5;
                r2.listen(this.this$1, new StoryCaptionView$StoryCaptionTextView$TextState$$ExternalSyntheticLambda2(this));
            L5:
                Panel r12 = this.bottomPanel;
                if (r12 == null) goto L8;
                r12.listen(this.this$1, new StoryCaptionView$StoryCaptionTextView$TextState$$ExternalSyntheticLambda3(this));
            L8:
                StoryCaptionTextView r0 = this.this$1;
                r0.sizeCached = 0;
                r0.requestLayout();
            }

            public boolean touch(MotionEvent r12) {
                StoryCaptionTextView r0 = this.this$1;
                boolean r2 = false;
                if (r0.showMore == null) goto L7;
                RectF r4 = AndroidUtilities.rectTmp;
                float r5 = r0.showMoreX;
                r4.set(r5, r0.showMoreY, r1.getWidth() + r5, this.this$1.showMoreY + r6.showMore.getHeight());
                if (r4.contains(r12.getX(), r12.getY()) == false) goto L7;
                boolean r02 = false;
            L9:
                if (r02 == false) goto L13;
                if (r12.getAction() != 0) goto L13;
            L18:
                Panel r03 = this.topPanel;
                if (r03 != null) goto L21;
                int r04 = 0;
            L22:
                Panel r52 = this.bottomPanel;
                if (r52 != null) goto L25;
                int r53 = 0;
            L26:
                int r42 = (int) (r12.getX() - this.this$1.horizontalPadding);
                int r05 = (int) ((r12.getY() - this.this$1.verticalPadding) - (r04 + r53));
                int r54 = this.fullLayout.getLineForVertical(r05);
                float r43 = r42;
                int r6 = this.fullLayout.getOffsetForHorizontal(r54, r43);
                float r7 = this.fullLayout.getLineLeft(r54);
                if (r7 <= r43) goto L29;
            L58:
                CharacterStyle r55 = null;
                AnimatedEmojiSpan r62 = null;
            L60:
                if (r12.getAction() != 1) goto L72;
                this.links.clear();
                LinkSpanDrawable<CharacterStyle> r122 = this.pressedLink;
                if (r122 != null) goto L64;
            L66:
                AnimatedEmojiSpan r123 = this.pressedEmoji;
                if (r123 == null) goto L70;
                if (r123 != r62) goto L70;
                this.this$1.this$0.onEmojiClick(r123);
            L70:
                this.pressedLink = null;
                this.pressedEmoji = null;
                return true;
            L64:
                if (r122.getSpan() != r55) goto L66;
                this.this$1.this$0.onLinkClick(this.pressedLink.getSpan(), this.this$1.this$0);
                goto L70
            L72:
                return r2;
            L29:
                if ((r7 + this.fullLayout.getLineWidth(r54)) < r43) goto L58;
                if (r05 < 0) goto L58;
                if (r05 > this.fullLayout.getHeight()) goto L58;
                SpannableString r06 = new SpannableString(this.text);
                CharacterStyle[] r44 = (CharacterStyle[]) r06.getSpans(r6, r6, ClickableSpan.class);
                if (r44 != null) goto L36;
            L37:
                r44 = (CharacterStyle[]) r06.getSpans(r6, r6, URLSpanMono.class);
            L38:
                if (r44 != null) goto L40;
            L45:
                r55 = null;
            L44:
                boolean r45 = false;
            L47:
                if (this.pressedLink != null) goto L57;
                if (r45 == true) goto L57;
                AnimatedEmojiSpan[] r07 = (AnimatedEmojiSpan[]) r06.getSpans(r6, r6, AnimatedEmojiSpan.class);
                if (r07 == null) goto L57;
                if (r07.length == 0) goto L57;
                r62 = r07[0];
                if (r12.getAction() != 0) goto L56;
                this.pressedLink = null;
                this.pressedEmoji = r07[0];
                r2 = true;
            L56:
                r2 = r45;
            L57:
                r62 = null;
                goto L56
            L40:
                if (r44.length == 0) goto L45;
                r55 = r44[0];
                if (r12.getAction() != 0) goto L44;
                this.links.clear();
                this.pressedEmoji = null;
                LinkSpanDrawable<CharacterStyle> r72 = new LinkSpanDrawable(r44[0], null, r12.getX(), r12.getY());
                this.pressedLink = r72;
                r72.setColor(Theme.multAlpha(-1, 0.2f));
                this.links.addLink(this.pressedLink);
                int r46 = r06.getSpanStart(this.pressedLink.getSpan());
                int r73 = r06.getSpanEnd(this.pressedLink.getSpan());
                LinkPath r8 = this.pressedLink.obtainNewPath();
                r8.setCurrentLayout(this.fullLayout, r46, this.this$1.getPaddingTop());
                this.fullLayout.getSelectionPath(r46, r73, r8);
                LinkSpanDrawable<CharacterStyle> r47 = this.pressedLink;
                this.this$1.this$0.textSelectionHelper.clear();
                this.this$1.postDelayed(new StoryCaptionView$StoryCaptionTextView$TextState$$ExternalSyntheticLambda1(this, r47), ViewConfiguration.getLongPressTimeout());
                r45 = true;
                goto L47
            L36:
                if (r44.length != 0) goto L38;
            L25:
                r53 = r52.height() + AndroidUtilities.dp(8.0f);
                goto L26
            L21:
                r04 = r03.height() + AndroidUtilities.dp(8.0f);
            L13:
                if (this.pressedLink != null) goto L17;
                if (this.pressedEmoji != null) goto L17;
            L74:
                if (r12.getAction() != 3) goto L77;
                StoryCaptionTextView.m19749$$Nest$mclearPressedLinks(this.this$1);
                this.pressedEmoji = null;
                return true;
            L77:
                return false;
            L17:
                if (r12.getAction() != 1) goto L74;
            L7:
                r02 = true;
                goto L9
            }
        }

        /* renamed from: $r8$lambda$sbKBUhSgZVstHuX4N0yw-lwv0Uo, reason: not valid java name */
        public static /* synthetic */ void m19745$r8$lambda$sbKBUhSgZVstHuX4N0ywlwv0Uo(StoryCaptionTextView r0, ValueAnimator r1) {
            r0.lambda$animateUpdate$0(r1);
        }

        /* renamed from: -$$Nest$fgetemojiColorFilter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ PorterDuffColorFilter m19746$$Nest$fgetemojiColorFilter(StoryCaptionTextView r0) {
            return r0.emojiColorFilter;
        }

        /* renamed from: -$$Nest$fgetisSpoilersRevealed, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19747$$Nest$fgetisSpoilersRevealed(StoryCaptionTextView r0) {
            return r0.isSpoilersRevealed;
        }

        /* renamed from: -$$Nest$fputisSpoilersRevealed, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19748$$Nest$fputisSpoilersRevealed(StoryCaptionTextView r0, boolean r1) {
            r0.isSpoilersRevealed = r1;
        }

        /* renamed from: -$$Nest$mclearPressedLinks, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19749$$Nest$mclearPressedLinks(StoryCaptionTextView r0) {
            r0.clearPressedLinks();
        }

        /* renamed from: -$$Nest$mmakeTextLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ StaticLayout m19750$$Nest$mmakeTextLayout(StoryCaptionTextView r0, TextPaint r1, CharSequence r2, int r3) {
            return r0.makeTextLayout(r1, r2, r3);
        }

        public StoryCaptionTextView(StoryCaptionView r18, Context r19, Theme.ResourcesProvider r20) {
            this.this$0 = r18;
            super(r19);
            this.textPaint = new TextPaint(1);
            this.showMorePaint = new TextPaint(1);
            Paint r1 = new Paint();
            this.xRefPaint = r1;
            Paint r3 = new Paint(1);
            this.xRefGradinetPaint = r3;
            this.state = new TextState[2];
            this.sizeCached = 0;
            this.path = new Path();
            this.allowClickSpoilers = true;
            this.updating = false;
            this.state[0] = new TextState(this);
            this.state[1] = null;
            this.textPaint.setColor(-1);
            TextPaint r2 = this.textPaint;
            r2.linkColor = -1;
            r2.setTextSize(AndroidUtilities.dp(15.0f));
            this.showMorePaint.setColor(-1);
            this.showMorePaint.setTypeface(AndroidUtilities.bold());
            this.showMorePaint.setTextSize(AndroidUtilities.dp(16.0f));
            r1.setColor(-16777216);
            PorterDuff.Mode r8 = PorterDuff.Mode.DST_OUT;
            r1.setXfermode(new PorterDuffXfermode(r8));
            r3.setShader(new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(16.0f), 0.0f, new int[]{0, -1}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
            r3.setXfermode(new PorterDuffXfermode(r8));
            this.emojiColorFilter = new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN);
        }

        private void clearPressedLinks() {
            TextState.m19755$$Nest$fgetlinks(this.state[0]).clear();
            TextState.m19757$$Nest$fputpressedLink(this.state[0], null);
            invalidate();
        }

        private /* synthetic */ void lambda$animateUpdate$0(ValueAnimator r1) {
            this.updateT = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
            requestLayout();
            this.this$0.requestLayout();
        }

        private StaticLayout makeTextLayout(TextPaint r2, CharSequence r3, int r4) {
            StaticLayout.Builder r1 = StaticLayout.Builder.obtain(r3, 0, r3.length(), r2, r4).setBreakStrategy(0).setHyphenationFrequency(0);
            if (LocaleController.isRTL == false) goto L5;
            Layout.Alignment r22 = StaticLayoutEx.ALIGN_RIGHT();
        L7:
            return r1.setAlignment(r22).build();
        L5:
            r22 = StaticLayoutEx.ALIGN_LEFT();
            goto L7
        }

        public void animateUpdate() {
            ValueAnimator r0 = this.updateAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            this.updating = true;
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.updateT, 0.0f});
            this.updateAnimator = r02;
            r02.addUpdateListener(new StoryCaptionView$StoryCaptionTextView$$ExternalSyntheticLambda0(this));
            this.updateAnimator.addListener(new AnonymousClass1(this));
            this.updateAnimator.setDuration(180);
            this.updateAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
            this.updateAnimator.start();
        }

        public int collapsedTextHeight(int r5) {
            int r1 = 0;
            int r0 = this.state[0].collapsedTextHeight(r5);
            TextState r2 = this.state[1];
            if (r2 == null) goto L7;
            r1 = r2.collapsedTextHeight(r5);
        L7:
            return AndroidUtilities.lerp(r0, r1, this.updateT);
        }

        @Override // android.view.View
        public boolean dispatchTouchEvent(MotionEvent r17) {
            if (r17.getAction() != 0) goto L5;
            StoryCaptionView.m19742$$Nest$fputstartMotionX(this.this$0, r17.getX());
            StoryCaptionView.m19743$$Nest$fputstartMotionY(this.this$0, r17.getY());
        L5:
            StoryCaptionView.m19740$$Nest$fputlastMotionX(this.this$0, r17.getX());
            StoryCaptionView.m19741$$Nest$fputlastMotionY(this.this$0, r17.getY());
            if (this.showMore == null) goto L10;
            RectF r5 = AndroidUtilities.rectTmp;
            float r6 = this.showMoreX;
            r5.set(r6, this.showMoreY, r2.getWidth() + r6, this.showMoreY + this.showMore.getHeight());
            if (r5.contains(r17.getX(), r17.getY()) == false) goto L10;
            boolean r2 = false;
        L11:
            TextState r52 = this.state[0];
            if (r52 == null) goto L39;
            Panel r53 = r52.topPanel;
            if (r53 == null) goto L39;
            int r54 = r53.height() + AndroidUtilities.dp(8.0f);
            RectF r8 = AndroidUtilities.rectTmp;
            r8.set(this.horizontalPadding, this.verticalPadding, r9 + this.state[0].topPanel.width(), this.verticalPadding + this.state[0].topPanel.height());
            boolean r82 = r8.contains(r17.getX(), r17.getY());
            if (r82 == false) goto L19;
            r2 = false;
        L19:
            if (r17.getAction() != 0) goto L23;
            if (r82 == false) goto L23;
            this.state[0].topPanel.setPressed(true, r17.getX(), r17.getY());
        L40:
            if (r82 == true) goto L71;
            TextState r9 = this.state[0];
            if (r9 == null) goto L71;
            if (r9.bottomPanel == null) goto L71;
            RectF r10 = AndroidUtilities.rectTmp;
            float r11 = this.horizontalPadding;
            float r92 = (this.verticalPadding + AndroidUtilities.lerp(r9.collapsedTextHeight, r9.textHeight, this.progressToExpand)) - this.state[0].bottomPanel.height();
            float r12 = this.horizontalPadding + this.state[0].bottomPanel.width();
            int r13 = this.verticalPadding;
            TextState r14 = this.state[0];
            r10.set(r11, r92, r12, r13 + AndroidUtilities.lerp(r14.collapsedTextHeight, r14.textHeight, this.progressToExpand));
            boolean r62 = r10.contains(r17.getX(), r17.getY());
            if (r62 == false) goto L49;
            r2 = false;
        L49:
            if (r17.getAction() != 0) goto L53;
            if (r62 == false) goto L53;
            this.state[0].bottomPanel.setPressed(true, r17.getX(), r17.getY());
        L69:
            if (r62 == false) goto L71;
            return true;
        L53:
            if (r17.getAction() != 2) goto L59;
            if (this.state[0].bottomPanel.bounce.isPressed() == false) goto L69;
            if (r62 == true) goto L69;
            this.state[0].bottomPanel.setPressed(false, r17.getX(), r17.getY());
            goto L69
        L59:
            if (r17.getAction() == 1) goto L63;
            if (r17.getAction() != 3) goto L69;
        L63:
            if (r17.getAction() != 1) goto L68;
            if (r62 == false) goto L68;
            if (this.state[0].bottomPanel.bounce.isPressed() == false) goto L68;
            this.this$0.onReplyClick(this, this.state[0].bottomPanel);
        L68:
            this.state[0].bottomPanel.setPressed(false, r17.getX(), r17.getY());
        L71:
            if (r2 == false) goto L80;
            StoryCaptionView r63 = this.this$0;
            if (r63.expanded == true) goto L78;
            TextState r7 = this.state[0];
            if (r7 == null) goto L78;
            if (r7.firstLayout != null) goto L80;
        L78:
            r63.textSelectionHelper.update(this.horizontalPadding, this.verticalPadding + r54);
            this.this$0.textSelectionHelper.onTouchEvent(r17);
        L80:
            if (this.this$0.textSelectionHelper.isInSelectionMode() == true) goto L89;
            if (r2 == false) goto L89;
            if (this.allowClickSpoilers == false) goto L89;
            if (TextState.m19754$$Nest$fgetclickDetector(this.state[0]).onTouchEvent(r17) == false) goto L89;
            getParent().requestDisallowInterceptTouchEvent(true);
            this.this$0.textSelectionHelper.clear();
            return true;
        L89:
            if (super.dispatchTouchEvent(r17) == true) goto L93;
            if (r82 == true) goto L93;
            return false;
        L93:
            return true;
        L23:
            if (r17.getAction() != 2) goto L29;
            if (this.state[0].topPanel.bounce.isPressed() == false) goto L40;
            if (r82 == true) goto L40;
            this.state[0].topPanel.setPressed(false, r17.getX(), r17.getY());
            goto L40
        L29:
            if (r17.getAction() == 1) goto L33;
            if (r17.getAction() != 3) goto L40;
        L33:
            if (r17.getAction() != 1) goto L38;
            if (r82 == false) goto L38;
            if (this.state[0].topPanel.bounce.isPressed() == false) goto L38;
            this.this$0.onReplyClick(this, this.state[0].topPanel);
        L38:
            this.state[0].topPanel.setPressed(false, r17.getX(), r17.getY());
        L39:
            r54 = 0;
            r82 = false;
        L10:
            r2 = true;
            goto L11
        }

        public float getAnimatedHeight() {
            int r0 = this.verticalPadding * 2;
            TextState[] r1 = this.state;
            int r2 = 0;
            int r3 = r1[0].textHeight;
            TextState r12 = r1[1];
            if (r12 == null) goto L7;
            r2 = r12.textHeight;
        L7:
            return r0 + AndroidUtilities.lerp(r3, r2, this.updateT);
        }

        public Paint getPaint() {
            return this.textPaint;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.SimpleSelectabeleView
        public Layout getStaticTextLayout() {
            return this.state[0].fullLayout;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.SimpleSelectabeleView
        public CharSequence getText() {
            return this.state[0].text;
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.state[0].detach();
        }

        @Override // android.view.View
        public void onDraw(Canvas r15) {
            if (this.showMore == null) goto L5;
            r15.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), 255, 31);
            Canvas r8 = r15;
        L6:
            this.state[0].draw(r8, 1.0f - this.updateT);
            TextState r152 = this.state[1];
            if (r152 == null) goto L10;
            r152.draw(r8, this.updateT);
        L10:
            if (this.showMore == null) goto L12;
            float r153 = this.showMoreY + this.this$0.getScrollY();
            int r1 = (int) ((1.0f - Utilities.clamp(this.progressToExpand / 0.5f, 1.0f, 0.0f)) * 255.0f);
            this.xRefGradinetPaint.setAlpha(r1);
            this.xRefPaint.setAlpha(r1);
            this.showMorePaint.setAlpha(r1);
            r8.save();
            r8.translate(this.showMoreX - AndroidUtilities.dp(32.0f), r153);
            r8.drawRect(0.0f, 0.0f, AndroidUtilities.dp(32.0f), this.showMore.getHeight() + this.verticalPadding, this.xRefGradinetPaint);
            r8.restore();
            r8.drawRect(this.showMoreX - AndroidUtilities.dp(16.0f), r153, getMeasuredWidth(), (this.showMore.getHeight() + r153) + this.verticalPadding, this.xRefPaint);
            r8.save();
            r8.translate(this.showMoreX, r153);
            this.showMore.draw(r8);
            r8.restore();
        L12:
            r8.restore();
            return;
        L5:
            r8 = r15;
            r8.save();
            goto L6
        }

        @Override // android.view.View
        @SuppressLint({"DrawAllocation"})
        public void onMeasure(int r5, int r6) {
            int r62 = (r6 + r5) << 16;
            this.horizontalPadding = AndroidUtilities.dp(16.0f);
            this.verticalPadding = AndroidUtilities.dp(8.0f);
            int r2 = 0;
            if (this.sizeCached == r62) goto L7;
            this.sizeCached = r62;
            int r63 = Math.max(0, View.MeasureSpec.getSize(r5) - (this.horizontalPadding * 2));
            this.state[0].measure(r63);
            TextState r0 = this.state[1];
            if (r0 == null) goto L7;
            r0.measure(r63);
        L7:
            int r64 = this.verticalPadding * 2;
            TextState[] r02 = this.state;
            int r3 = r02[0].textHeight;
            TextState r03 = r02[1];
            if (r03 == null) goto L11;
            r2 = r03.textHeight;
        L11:
            super.onMeasure(r5, View.MeasureSpec.makeMeasureSpec(r64 + AndroidUtilities.lerp(r3, r2, this.updateT), TLObject.FLAG_30));
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r4) {
            if (this.this$0.disableTouches == false) goto L5;
            return false;
        L5:
            TextState[] r0 = this.state;
            if (r0 == null) goto L18;
            TextState r02 = r0[0];
            if (r02.fullLayout == null) goto L18;
            if (r02.touch(r4) == false) goto L13;
            return true;
        L13:
            if (super.onTouchEvent(r4) == true) goto L19;
            return false;
        L19:
            return true;
        L18:
            return false;
        }

        @Override // android.view.View
        public void setPressed(boolean r2) {
            if (r2 == isPressed()) goto L5;
            boolean r0 = true;
        L6:
            super.setPressed(r2);
            if (r0 == false) goto L10;
            invalidate();
            return;
        L10:
            return;
        L5:
            r0 = false;
            goto L6
        }

        public void setText(CharSequence r7, Panel r8, Panel r9, boolean r10, boolean r11) {
            if (r7 != null) goto L5;
            r7 = _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            if (MediaDataController.stringsEqual(this.state[0].text, r7) == false) goto L12;
            TextState r0 = this.state[0];
            if (r0.topPanel != r8) goto L12;
            if (r0.bottomPanel != r9) goto L12;
            r0.translating = r10;
            invalidate();
            return;
        L12:
            this.isSpoilersRevealed = false;
            ValueAnimator r02 = this.updateAnimator;
            if (r02 == null) goto L15;
            r02.cancel();
        L15:
            this.updating = false;
            TextState[] r03 = this.state;
            if (r11 == true) goto L18;
            r03[0].setup(r7, r8, r9);
            this.state[0].translating = r10;
            invalidate();
            this.updateT = 0.0f;
            return;
        L18:
            if (r03[1] != null) goto L20;
            r03[1] = new TextState(this);
        L20:
            TextState[] r04 = this.state;
            TextState r3 = r04[1];
            TextState r05 = r04[0];
            r3.setup(r05.text, r05.topPanel, r05.bottomPanel);
            TextState[] r06 = this.state;
            TextState r32 = r06[1];
            TextState r07 = r06[0];
            r32.translating = r07.translating;
            r32.translateT.set(r07.translateT.get(), true);
            this.state[0].setup(r7, r8, r9);
            TextState r72 = this.state[0];
            r72.translating = r10;
            r72.translateT.set(0.0f, true);
            this.updateT = 1.0f;
            animateUpdate();
        }

        @Override // android.view.View
        public void setTranslationY(float r2) {
            if (getTranslationY() == r2) goto L6;
            super.setTranslationY(r2);
            this.this$0.invalidate();
            return;
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r4) {
            TextState r0 = this.state[0];
            if (r0 != null) goto L5;
        L15:
            TextState r02 = this.state[1];
            if (r02 == null) goto L29;
            if (TextState.m19756$$Nest$fgetloadingDrawable(r02) == r4) goto L27;
            TextState r03 = this.state[1];
            Panel r1 = r03.topPanel;
            if (r1 != null) goto L22;
        L23:
            Panel r04 = r03.bottomPanel;
            if (r04 == null) goto L29;
            if (r04.ripple != r4) goto L29;
        L22:
            if (r1.ripple != r4) goto L23;
        L27:
            return true;
        L29:
            return super.verifyDrawable(r4);
        L5:
            if (TextState.m19756$$Nest$fgetloadingDrawable(r0) == r4) goto L14;
            TextState r05 = this.state[0];
            Panel r12 = r05.topPanel;
            if (r12 != null) goto L9;
        L10:
            Panel r06 = r05.bottomPanel;
            if (r06 == null) goto L15;
            if (r06.ripple != r4) goto L15;
        L9:
            if (r12.ripple != r4) goto L10;
        L14:
            return true;
        }
    }

    public static /* synthetic */ void $r8$lambda$GjMM_ONPiGdAeDxCAU89bDabj0k(StoryCaptionView r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$HiBOSYHBjGiRd0SZeQUIAWK-dlg, reason: not valid java name */
    public static /* synthetic */ void m19738$r8$lambda$HiBOSYHBjGiRd0SZeQUIAWKdlg(StoryCaptionView r0, float r1, float r2, float r3, float r4, ValueAnimator r5) {
        r0.lambda$collapse$2(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$f9MwkloWTxqdcR-GY_ONzCmAjiM, reason: not valid java name */
    public static /* synthetic */ void m19739$r8$lambda$f9MwkloWTxqdcRGY_ONzCmAjiM(StoryCaptionView r0, float r1, float r2, float r3, ValueAnimator r4) {
        r0.lambda$expand$1(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fputlastMotionX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19740$$Nest$fputlastMotionX(StoryCaptionView r0, float r1) {
        r0.lastMotionX = r1;
    }

    /* renamed from: -$$Nest$fputlastMotionY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19741$$Nest$fputlastMotionY(StoryCaptionView r0, float r1) {
        r0.lastMotionY = r1;
    }

    /* renamed from: -$$Nest$fputstartMotionX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19742$$Nest$fputstartMotionX(StoryCaptionView r0, float r1) {
        r0.startMotionX = r1;
    }

    /* renamed from: -$$Nest$fputstartMotionY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19743$$Nest$fputstartMotionY(StoryCaptionView r0, float r1) {
        r0.startMotionY = r1;
    }

    public StoryCaptionView(Context r11, Theme.ResourcesProvider r12) {
        super(r11);
        Paint r1 = new Paint(1);
        this.paint = r1;
        this.backgroundAlpha = 1.0f;
        this.pendingTopMargin = -1;
        this.gradientColor = ColorUtils.setAlphaComponent(-16777216, 51);
        this.topOverlayGradient = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0, this.gradientColor});
        this.captionContainer = new FrameLayout(r11);
        setClipChildren(false);
        setOverScrollMode(2);
        NotificationCenter.listenEmojiLoading(this);
        StoryCaptionTextView r112 = new StoryCaptionTextView(this, getContext(), r12);
        this.captionTextview = r112;
        TextSelectionHelper.SimpleTextSelectionHelper r5 = new TextSelectionHelper.SimpleTextSelectionHelper(r112, r12);
        this.textSelectionHelper = r5;
        r5.useMovingOffset = false;
        this.captionContainer.addView(this.captionTextview, -1, -2);
        addView(this.captionContainer, new ViewGroup.LayoutParams(-1, -2));
        r1.setColor(-16777216);
        setFadingEdgeLength(AndroidUtilities.dp(12.0f));
        setVerticalFadingEdgeEnabled(true);
        setWillNotDraw(false);
        SpringAnimation r113 = new SpringAnimation(this.captionTextview, DynamicAnimation.TRANSLATION_Y, 0.0f);
        this.springAnimation = r113;
        r113.getSpring().setStiffness(100.0f);
        r113.setMinimumVisibleChange(1.0f);
        r113.addUpdateListener(new StoryCaptionView$$ExternalSyntheticLambda1(this));
        r113.getSpring().setDampingRatio(1.0f);
        Method r122 = NestedScrollView.class.getDeclaredMethod("abortAnimatedScroll", null);     // Catch: Exception -> L5
        this.abortAnimatedScrollMethod = r122;     // Catch: Exception -> L5
        r122.setAccessible(true);     // Catch: Exception -> L5
    L14:
        Field r123 = NestedScrollView.class.getDeclaredField("mScroller");     // Catch: Exception -> L9
        r123.setAccessible(true);     // Catch: Exception -> L9
        this.scroller = (OverScroller) r123.get(this);     // Catch: Exception -> L9
        return;
    L9:
        e = move-exception;
        this.scroller = null;
        FileLog.e(e);
        return;
    L5:
        e = move-exception;
        this.abortAnimatedScrollMethod = null;
        FileLog.e(e);
        goto L14
    }

    private /* synthetic */ void lambda$collapse$2(float r1, float r2, float r3, float r4, ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        setScrollY((int) AndroidUtilities.lerp(r1, r2, r52));
        StoryCaptionTextView r12 = this.captionTextview;
        r12.progressToExpand = AndroidUtilities.lerp(r3, r4, r52);
        this.captionTextview.invalidate();
    }

    private /* synthetic */ void lambda$expand$1(float r4, float r5, float r6, ValueAnimator r7) {
        float r72 = ((Float) r7.getAnimatedValue()).floatValue();
        setScrollY((int) AndroidUtilities.lerp(r4, Math.min((getMeasuredHeight() - this.blackoutBottomOffset) - AndroidUtilities.dp(64.0f), this.captionContainer.getBottom() - getMeasuredHeight()), r72));
        this.captionTextview.progressToExpand = AndroidUtilities.lerp(r5, r6, r72);
        this.captionTextview.invalidate();
    }

    private /* synthetic */ void lambda$new$0(DynamicAnimation r1, float r2, float r3) {
        this.overScrollY = r2;
        this.velocityY = r3;
    }

    private void startSpringAnimationIfNotRunning(float r2) {
        if (this.springAnimation.isRunning() == true) goto L6;
        this.springAnimation.setStartVelocity(r2);
        this.springAnimation.start();
    L6:
        if (getScrollY() >= AndroidUtilities.dp(2.0f)) goto L9;
        collapse();
        return;
    }

    private void updateTopMargin(int r1, int r2) {
        int r12 = calculateNewContainerMarginTop(r1, r2);
        if (r12 >= 0) goto L5;
        return;
    L5:
        if (this.dontChangeTopMargin == false) goto L8;
        this.pendingTopMargin = r12;
        return;
    L8:
        ((ViewGroup.MarginLayoutParams) this.captionContainer.getLayoutParams()).topMargin = r12;
        this.pendingTopMargin = -1;
    }

    public boolean allowInterceptTouchEvent(float r2, float r3) {
        if (this.captionTextview.progressToExpand == 1.0f) goto L5;
        return false;
    L5:
        if (this.disableTouches == false) goto L7;
        return false;
    L7:
        if (r3 <= ((this.captionContainer.getTop() - getScrollY()) + this.captionTextview.getTranslationY())) goto L13;
        return true;
    L13:
        return false;
    }

    public int calculateNewContainerMarginTop(int r9, int r10) {
        if (r9 == 0) goto L37;
        if (r10 == 0) goto L37;
        StoryCaptionTextView r1 = this.captionTextview;
        boolean r3 = false;
        StoryCaptionTextView.TextState r2 = r1.state[0];
        CharSequence r4 = r2.text;
        Panel r22 = r2.topPanel;
        CharSequence r5 = null;
        if (r22 == null) goto L9;
        CharSequence r6 = r22.title;
    L10:
        if (r22 == null) goto L12;
        r5 = r22.text;
    L12:
        int r23 = r4.hashCode();
        if (r6 == null) goto L15;
        int r42 = r6.hashCode();
    L16:
        if (r5 == null) goto L18;
        int r52 = r5.hashCode();
    L19:
        Point r62 = AndroidUtilities.displaySize;
        if (r62.x <= r62.y) goto L23;
        r3 = true;
    L23:
        if (this.textHash == r23) goto L25;
    L35:
        this.textHash = r23;
        this.replytitleHash = r42;
        this.replytextHash = r52;
        this.isLandscape = r3;
        this.prevHeight = r10;
        r1.measure(View.MeasureSpec.makeMeasureSpec(r9, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r10, Integer.MIN_VALUE));
        return r1.collapsedTextHeight(r10);
    L25:
        if (this.replytitleHash != r42) goto L35;
        if (this.replytextHash != r52) goto L35;
        if (this.isLandscape != r3) goto L35;
        if (this.prevHeight != r10) goto L35;
        if (r1.updating == true) goto L35;
        return -1;
    L18:
        r52 = 0;
        goto L19
    L15:
        r42 = 0;
        goto L16
    L9:
        r6 = null;
    L37:
        return -1;
    }

    public void cancelTouch() {
        this.touched = false;
    }

    public void checkCancelTextSelection() {
        if (this.textSelectionHelper.isInSelectionMode() == true) goto L5;
        return;
    L5:
        if (Math.abs(this.startMotionX - this.lastMotionX) < AndroidUtilities.touchSlop) goto L7;
        return;
    L7:
        if (Math.abs(this.startMotionY - this.lastMotionY) >= AndroidUtilities.touchSlop) goto L12;
        this.textSelectionHelper.getOverlayView(getContext()).checkCancel(this.lastMotionX, this.lastMotionY, false);
        return;
    }

    public void collapse() {
        if (this.expanded == true) goto L5;
        return;
    L5:
        this.expanded = false;
        float r3 = getScrollY();
        float r5 = this.captionTextview.progressToExpand;
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r0.addUpdateListener(new StoryCaptionView$$ExternalSyntheticLambda0(this, r3, 0.0f, r5, 0.0f));
        r0.setDuration(250);
        r0.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r0.start();
    }

    @Override // androidx.core.widget.NestedScrollView, android.view.View
    public void computeScroll() {
        super.computeScroll();
        if (this.nestedScrollStarted == false) goto L5;
        return;
    L5:
        if (this.overScrollY == 0.0f) goto L13;
        OverScroller r0 = this.scroller;
        if (r0 != null) goto L9;
        return;
    L9:
        if (r0.isFinished() == false) goto L15;
        startSpringAnimationIfNotRunning(0.0f);
        return;
    L15:
        return;
    }

    public void disableDraw(boolean r2) {
        if (this.disableDraw == r2) goto L6;
        this.disableDraw = r2;
        invalidate();
        return;
    }

    @Override // androidx.core.widget.NestedScrollView
    public boolean dispatchNestedPreScroll(int r4, int r5, int[] r6, int[] r7, int r8) {
        r6[1] = 0;
        if (this.nestedScrollStarted == false) goto L22;
        float r82 = this.overScrollY;
        if (r82 <= 0.0f) goto L8;
        if (r5 <= 0) goto L8;
    L10:
        float r1 = r5;
        float r2 = r82 - r1;
        if (r82 <= 0.0f) goto L17;
        if (r2 >= 0.0f) goto L15;
        this.overScrollY = 0.0f;
        r6[1] = (int) (0 + (r1 + r2));
    L20:
        this.captionTextview.setTranslationY(this.overScrollY);
        this.textSelectionHelper.invalidate();
        return true;
    L15:
        this.overScrollY = r2;
        r6[1] = r5;
        goto L20
    L17:
        if (r2 <= 0.0f) goto L19;
        this.overScrollY = 0.0f;
        r6[1] = (int) (0 + (r1 + r2));
        goto L20
    L19:
        this.overScrollY = r2;
        r6[1] = r5;
    L8:
        if (r82 >= 0.0f) goto L22;
        if (r5 < 0) goto L10;
    L22:
        return false;
    }

    @Override // androidx.core.widget.NestedScrollView
    public void dispatchNestedScroll(int r1, int r2, int r3, int r4, int[] r5, int r6, int[] r7) {
        if (r4 == 0) goto L25;
        int r12 = Math.round(r4 * (1.0f - Math.abs((-this.overScrollY) / this.captionContainer.getTop())));
        if (r12 == 0) goto L25;
        if (this.nestedScrollStarted == false) goto L8;
        float r22 = this.overScrollY - r12;
        this.overScrollY = r22;
        this.captionTextview.setTranslationY(r22);
        goto L25
    L8:
        if (this.springAnimation.isRunning() == true) goto L25;
        OverScroller r23 = this.scroller;
        if (r23 == null) goto L12;
        float r24 = r23.getCurrVelocity();
    L14:
        if (Float.isNaN(r24) == true) goto L20;
        Point r32 = AndroidUtilities.displaySize;
        if (r32.x <= r32.y) goto L18;
        float r33 = 3000.0f;
    L19:
        float r34 = Math.min(r33, r24);
        r12 = (int) ((r12 * r34) / r24);
        float r35 = r34 * (-this.velocitySign);
    L21:
        if (r12 == 0) goto L23;
        float r25 = this.overScrollY - r12;
        this.overScrollY = r25;
        this.captionTextview.setTranslationY(r25);
    L23:
        startSpringAnimationIfNotRunning(r35);
        goto L25
    L18:
        r33 = 5000.0f;
        goto L19
    L20:
        r35 = 0.0f;
        goto L21
    L12:
        r24 = Float.NaN;
    L25:
        this.textSelectionHelper.invalidate();
    }

    @Override // androidx.core.widget.NestedScrollView, android.view.View
    public void draw(Canvas r7) {
        if (this.disableDraw == false) goto L5;
        return;
    L5:
        int r0 = getWidth();
        int r1 = getHeight();
        int r2 = getScrollY();
        int r3 = r7.save();
        int r12 = r1 + r2;
        r7.clipRect(0, r2, r0, this.blackoutBottomOffset + r12);
        r7.clipRect(0, r2, r0, r12);
        super.draw(r7);
        r7.restoreToCount(r3);
    }

    @Override // org.telegram.ui.Components.ItemOptions.ScrimView
    public void drawScrim(Canvas r6, float r7) {
        StoryCaptionTextView.TextState[] r72 = this.captionTextview.state;
        if (r72 == null) goto L11;
        StoryCaptionTextView.TextState r73 = r72[0];
        if (r73 == null) goto L11;
        if (r73.bottomPanel == null) goto L11;
        r6.save();
        float r74 = (this.captionTextview.getX() - getScrollX()) + this.captionContainer.getX();
        float r75 = r74 + r1.horizontalPadding;
        float r1 = (this.captionTextview.getY() - getScrollY()) + this.captionContainer.getY();
        StoryCaptionTextView.TextState r3 = this.captionTextview.state[0];
        r6.translate(r75, ((r1 + r2.verticalPadding) + AndroidUtilities.lerp(r3.collapsedTextHeight, r3.textHeight, r2.progressToExpand)) - this.captionTextview.state[0].bottomPanel.height());
        Panel r76 = this.captionTextview.state[0].bottomPanel;
        int r0 = getWidth();
        int r5 = this.captionTextview.horizontalPadding;
        r76.draw(r6, (r0 - r5) - r5);
        r6.restore();
        return;
    L11:
        draw(r6);
    }

    public void expand() {
        expand(false);
    }

    public void expand(boolean r5) {
        if (this.expanded == false) goto L6;
        if (r5 == true) goto L6;
        return;
    L6:
        this.expanded = true;
        float r52 = getScrollY();
        float r0 = this.captionTextview.progressToExpand;
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r1.addUpdateListener(new StoryCaptionView$$ExternalSyntheticLambda2(this, r52, r0, 1.0f));
        r1.setDuration(250);
        r1.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r1.start();
    }

    @Override // androidx.core.widget.NestedScrollView
    public void fling(int r1) {
        super.fling(r1);
        this.velocitySign = Math.signum(r1);
        this.velocityY = 0.0f;
    }

    @Override // androidx.core.widget.NestedScrollView, android.view.View
    public float getBottomFadingEdgeStrength() {
        return 1.0f;
    }

    @Override // org.telegram.ui.Components.ItemOptions.ScrimView
    public void getBounds(RectF r8) {
        StoryCaptionTextView r0 = this.captionTextview;
        StoryCaptionTextView.TextState[] r1 = r0.state;
        if (r1 == null) goto L11;
        StoryCaptionTextView.TextState r12 = r1[0];
        if (r12 != null) goto L7;
        return;
    L7:
        if (r12.bottomPanel == null) goto L13;
        float r3 = r0.horizontalPadding;
        float r02 = (r0.verticalPadding + AndroidUtilities.lerp(r12.collapsedTextHeight, r12.textHeight, r0.progressToExpand)) - this.captionTextview.state[0].bottomPanel.height();
        int r13 = getWidth();
        StoryCaptionTextView r4 = this.captionTextview;
        float r14 = r13 - r4.horizontalPadding;
        int r5 = r4.verticalPadding;
        StoryCaptionTextView.TextState r2 = r4.state[0];
        r8.set(r3, r02, r14, r5 + AndroidUtilities.lerp(r2.collapsedTextHeight, r2.textHeight, r4.progressToExpand));
        r8.offset((this.captionTextview.getX() - getScrollX()) + this.captionContainer.getX(), (this.captionTextview.getY() - getScrollY()) + this.captionContainer.getY());
        return;
    L13:
        return;
    }

    public float getMaxTop() {
        return this.captionContainer.getTop() - (this.captionContainer.getBottom() - getMeasuredHeight());
    }

    public int getPendingMarginTopDiff() {
        int r0 = this.pendingTopMargin;
        if (r0 >= 0) goto L5;
        return 0;
    L5:
        return r0 - ((ViewGroup.MarginLayoutParams) this.captionContainer.getLayoutParams()).topMargin;
    }

    public float getProgressToBlackout() {
        return Utilities.clamp((getScrollY() - this.captionTextview.getTranslationY()) / Math.min(this.prevHeight, AndroidUtilities.dp(40.0f)), 1.0f, 0.0f);
    }

    public float getTextTop() {
        return (this.captionContainer.getTop() + this.captionTextview.getTranslationY()) - getScrollY();
    }

    @Override // androidx.core.widget.NestedScrollView, android.view.View
    public float getTopFadingEdgeStrength() {
        return 1.0f;
    }

    public boolean hasScroll() {
        if ((this.captionContainer.getBottom() - getMeasuredHeight()) <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        if (getParent() == null) goto L5;
        ((View) getParent()).invalidate();
    L5:
        this.textSelectionHelper.invalidate();
    }

    public boolean isTouched() {
        return this.touched;
    }

    public void onEmojiClick(AnimatedEmojiSpan r1) {
    }

    @Override // androidx.core.widget.NestedScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r5) {
        if (this.captionTextview.progressToExpand != 1.0f) goto L24;
        if (this.disableTouches == true) goto L24;
        if (r5.getAction() != 0) goto L12;
        if (r5.getY() < ((this.captionContainer.getTop() - getScrollY()) + this.captionTextview.getTranslationY())) goto L24;
    L12:
        if (r5.getAction() != 0) goto L15;
        this.touched = true;
        invalidate();
    L22:
        return super.onInterceptTouchEvent(r5);
    L15:
        if (this.touched == false) goto L22;
        if (r5.getAction() != 1) goto L19;
    L20:
        this.touched = false;
        invalidate();
        goto L22
    L19:
        if (r5.getAction() != 3) goto L22;
    L24:
        if (this.touched == false) goto L26;
        this.touched = false;
        invalidate();
    L26:
        return false;
    }

    public void onLinkClick(CharacterStyle r1, View r2) {
    }

    public void onLinkLongPress(URLSpan r1, View r2, Runnable r3) {
    }

    @Override // androidx.core.widget.NestedScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        updateTopMargin(View.MeasureSpec.getSize(r3), View.MeasureSpec.getSize(r4));
        super.onMeasure(r3, r4);
    }

    public void onReplyClick(View r1, Panel r2) {
    }

    @Override // androidx.core.widget.NestedScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent r5) {
        if (this.captionTextview.progressToExpand != 1.0f) goto L24;
        if (this.disableTouches == true) goto L24;
        if (r5.getAction() != 0) goto L12;
        if (r5.getY() < ((this.captionContainer.getTop() - getScrollY()) + this.captionTextview.getTranslationY())) goto L24;
    L12:
        if (r5.getAction() != 0) goto L15;
        this.touched = true;
        invalidate();
    L22:
        return super.onTouchEvent(r5);
    L15:
        if (this.touched == false) goto L22;
        if (r5.getAction() != 1) goto L19;
    L20:
        this.touched = false;
        invalidate();
        goto L22
    L19:
        if (r5.getAction() != 3) goto L22;
    L24:
        if (this.touched == false) goto L26;
        this.touched = false;
        invalidate();
    L26:
        return false;
    }

    public void reset() {
        scrollTo(0, 0);
        this.expanded = false;
        StoryCaptionTextView r1 = this.captionTextview;
        r1.progressToExpand = 0.0f;
        r1.invalidate();
    }

    @Override // android.view.View
    public void scrollBy(int r1, int r2) {
        super.scrollBy(r1, r2);
        invalidate();
    }

    @Override // androidx.core.widget.NestedScrollView
    public boolean startNestedScroll(int r1, int r2) {
        if (r2 != 0) goto L5;
        this.springAnimation.cancel();
        this.nestedScrollStarted = true;
        this.overScrollY = this.captionTextview.getTranslationY();
    L5:
        return true;
    }

    @Override // androidx.core.widget.NestedScrollView
    public void stopNestedScroll(int r2) {
        if (this.nestedScrollStarted == false) goto L13;
        if (r2 != 0) goto L14;
        this.nestedScrollStarted = false;
        if (this.overScrollY == 0.0f) goto L15;
        OverScroller r22 = this.scroller;
        if (r22 != null) goto L10;
        return;
    L10:
        if (r22.isFinished() == false) goto L17;
        startSpringAnimationIfNotRunning(this.velocityY);
        return;
    L17:
        return;
    L15:
        return;
    L14:
        return;
    }

    public void updateTopMargin() {
        updateTopMargin(getWidth(), getHeight());
    }
}
