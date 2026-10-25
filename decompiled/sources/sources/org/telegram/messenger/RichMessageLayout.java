package org.telegram.messenger;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.MetricAffectingSpan;
import android.text.style.ReplacementSpan;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.AppDataDirGuesser;
import com.exteragram.messenger.utils.ui.FontUtils;
import com.google.android.gms.cast.MediaError;
import j$.net.URLDecoder;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.text.Typography;
import me.vkryl.android.util.ClickHelper;
import me.vkryl.core.BitwiseUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.CodeHighlighting;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.messenger.utils.tlutils.TLKeyboardHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ArticleViewer;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.AnimatedArrowDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFileDrawable;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CheckBoxBase;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ForegroundColorSpanThemable;
import org.telegram.ui.Components.FormattedDateSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RadialProgress2;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ReplyMessageLine;
import org.telegram.ui.Components.SeekBar;
import org.telegram.ui.Components.SquigglyLinesSpan;
import org.telegram.ui.Components.TableLayout;
import org.telegram.ui.Components.TextPaintImageReceiverSpan;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.TornEdge;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.URLSpanBotCommand;
import org.telegram.ui.Components.URLSpanMono;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.URLSpanReplacement;
import org.telegram.ui.Components.URLSpanUserMention;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.UnsupportedBlockDrawable;
import org.telegram.ui.Components.spoilers.SpoilerEffect;
import org.telegram.ui.Components.spoilers.SpoilerEffect2;
import org.telegram.ui.GradientClip;
import org.telegram.ui.LinkManager;
import org.telegram.ui.MultiLayoutTypingAnimator;
import org.telegram.ui.iv.Latex;
import org.telegram.ui.iv.RichHtml;
import org.telegram.ui.iv.RichTextStyle;
import org.telegram.ui.web.WebInstantView;

/* loaded from: classes5.dex */
public class RichMessageLayout {
    private static final int ORDERED_LIST_MARKER_START_DP = 6;
    private static final int ORDERED_LIST_MARKER_WIDTH_DP = 28;
    public static final int PART_MAX_HEIGHT_DP = 900;
    public static final int QUOTE_NEST_VPAD = 3;
    public static final int TEXT_FLAG_BLOCKS = 15;
    public static final int TEXT_FLAG_BLOCK_BUTTON = 13;
    public static final int TEXT_FLAG_BLOCK_CAPTION = 10;
    public static final int TEXT_FLAG_BLOCK_CODE = 8;
    public static final int TEXT_FLAG_BLOCK_FOOTER = 7;
    public static final int TEXT_FLAG_BLOCK_HEADING1 = 1;
    public static final int TEXT_FLAG_BLOCK_HEADING2 = 2;
    public static final int TEXT_FLAG_BLOCK_HEADING3 = 3;
    public static final int TEXT_FLAG_BLOCK_HEADING4 = 4;
    public static final int TEXT_FLAG_BLOCK_HEADING5 = 5;
    public static final int TEXT_FLAG_BLOCK_HEADING6 = 6;
    public static final int TEXT_FLAG_BLOCK_PULLQUOTE = 12;
    public static final int TEXT_FLAG_BLOCK_QUOTE = 9;
    public static final int TEXT_FLAG_BLOCK_QUOTE_CAPTION = 11;
    public static final int TEXT_FLAG_BLOCK_TABLE = 14;
    public static final int TEXT_FLAG_BLOCK_TABLE_TITLE = 15;
    public static final int TEXT_FLAG_BOLD = 16;
    public static final int TEXT_FLAG_ITALIC = 32;
    public static final int TEXT_FLAG_MARKED = 8192;
    public static final int TEXT_FLAG_MONO = 256;
    public static final int TEXT_FLAG_STRIKETHROUGH = 128;
    public static final int TEXT_FLAG_SUBSCRIPT = 2048;
    public static final int TEXT_FLAG_SUPERSCRIPT = 4096;
    public static final int TEXT_FLAG_UNDERLINE = 64;
    public static final int TEXT_FLAG_URL = 512;
    public static final int TEXT_FLAG_WEBPAGE_URL = 1024;
    public final HashMap<String, Integer> anchors;
    public final HashMap<TL_iv.pageBlockAudio, MessageObject> audioBlocks;
    public final ArrayList<MessageObject> audioMessages;
    public boolean blockquoteAnimating;
    public final ArrayList<RichBlock> blocks;
    private ChatMessageCell cell;
    public final GradientClip clip;
    public final int currentAccount;
    private ChatMessageCell.ChatMessageCellDelegate delegate;
    private float density;
    public boolean detailsAnimating;
    private float detailsAnimationProgress;
    private int fontSize;
    public boolean forceTranslationLoading;
    protected int height;
    public boolean invalidateAnimatedEmojiInParent;
    public boolean isPart;
    public CharSequence joinedText;
    protected int maxWidth;
    public final MessageObject messageObject;
    protected int minWidth;
    public final TextPaint numTextPaint;
    public Utilities.Callback<URLSpan> onLinkPress;
    public int padLeft;
    public int padRight;
    private RichBlock pressedBlock;
    private int pressedBlockY;
    private RichMessageLayout prev;
    private Drawable pullquoteIcon;
    public final ReplyMessageLine quoteLine;
    public final ArrayList<QuoteBackground> quotes;
    protected Theme.ResourcesProvider resourcesProvider;
    public TL_iv.RichMessage richMessage;
    private ButtonBounce showMoreBounce;
    private LoadingDrawable showMoreLoading;
    private Paint showMorePaint;
    private boolean showMorePressed;
    private final RectF showMoreRect;
    private org.telegram.ui.Components.Text showMoreText;
    private SpoilerEffect2 spoilerEffect2;
    public final HashMap<String, TL_iv.textAnchor> textAnchors;
    public final ArrayList<Integer> textBlockBlockIndex;
    public final ArrayList<Integer> textBlockCharOffsets;
    public final ArrayList<TextSelectionHelper.TextLayoutBlock> textBlocks;
    public final TextPaint textPaint;
    private AnimatedFloat translationLoadingFloat;
    public float translationLoadingValue;
    public MultiLayoutTypingAnimator typingAnimator;
    public final ArrayList<RichUnsupportedBlock> unsupportedBlocks;
    public final ArrayList<RichUnsupportedBlock> unsupportedBlocksRoot;
    public View view;

    /* renamed from: org.telegram.messenger.RichMessageLayout$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$telegram$messenger$RichMessageLayout$RichButtonRowBlock$Align = null;

        static {
            int[] r0 = new int[RichButtonRowBlock.Align.values().length];
            $SwitchMap$org$telegram$messenger$RichMessageLayout$RichButtonRowBlock$Align = r0;
            r0[RichButtonRowBlock.Align.RIGHT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L6
        L8:
            $SwitchMap$org$telegram$messenger$RichMessageLayout$RichButtonRowBlock$Align[RichButtonRowBlock.Align.CENTER.ordinal()] = 2;     // Catch: NoSuchFieldError -> L7
            return;
        }
    }

    public static class AnchorSpan extends CharacterStyle {
        public final String name;

        public AnchorSpan(String r1) {
            this.name = r1;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
        }
    }

    public static class FoundLink {
        public int end;
        public StaticLayout layout;
        public int originalWidth;
        public int start;
        public float x;
        public float y;

        public FoundLink() {
        }
    }

    public static class MediaCell implements DownloadController.FileDownloadProgressListener {
        private static ColorMatrixColorFilter fancyBlurFilter;
        public final float aspectRatio;
        public boolean autoDownload;
        public final ImageReceiver blurImageReceiver;
        private Bitmap blurSource;
        private boolean buttonPressed;
        private final int buttonSize;
        private int buttonState;
        private int buttonX;
        private int buttonY;
        public final TLRPC.Document document;
        public int h;
        public final ImageReceiver imageReceiver;
        public final boolean isVideo;
        private boolean mediaForced;
        private final int observerTag;
        public final TL_iv.PageBlock pageBlock;
        private View parentView;
        public final TLRPC.Photo photo;
        private boolean photoPressed;
        public final TLRPC.PhotoSize previewThumb;
        public RadialProgress2 radialProgress;
        public final boolean realVideo;
        public final RichMessageLayout root;
        public final TLRPC.PhotoSize sizeFull;
        private final SpoilerReveal spoilerReveal;
        public final TLRPC.PhotoSize strippedSize;
        public final TLRPC.PhotoSize strippedThumb;
        public int w;
        public int x;
        public int y;

        /* renamed from: org.telegram.messenger.RichMessageLayout$MediaCell$1, reason: invalid class name */
        public class AnonymousClass1 implements ImageReceiver.ImageReceiverDelegate {
            final /* synthetic */ MediaCell this$0;

            public AnonymousClass1(MediaCell r1) {
                this.this$0 = r1;
            }

            @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
            public void didSetImage(ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
            }

            @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
            public void onAnimationReady(ImageReceiver r2) {
                MediaCell r1 = this.this$0;
                r1.updateButtonState(MediaCell.m4827$$Nest$fgetparentView(r1), true);
            }
        }

        /* renamed from: -$$Nest$fgetparentView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m4827$$Nest$fgetparentView(MediaCell r0) {
            return r0.parentView;
        }

        private MediaCell(RichMessageLayout r6, TL_iv.pageBlockPhoto r7) {
            ImageReceiver r0 = new ImageReceiver();
            this.imageReceiver = r0;
            ImageReceiver r1 = new ImageReceiver();
            this.blurImageReceiver = r1;
            this.buttonState = -1;
            this.buttonSize = AndroidUtilities.dp(48.0f);
            this.spoilerReveal = new SpoilerReveal();
            this.root = r6;
            this.pageBlock = r7;
            TLRPC.Photo r72 = r6.getPhoto(r7.photo_id);
            this.photo = r72;
            if (r72 == null) goto L5;
            this.sizeFull = FileLoader.getClosestPhotoSizeWithSize(r72.sizes, AndroidUtilities.getPhotoSize());
            this.strippedSize = FileLoader.getStrippedPhotoSize(r72.sizes);
        L6:
            this.document = null;
            this.previewThumb = null;
            this.strippedThumb = null;
            this.isVideo = false;
            this.realVideo = false;
            TLRPC.PhotoSize r73 = this.sizeFull;
            if (r73 == null) goto L11;
            int r2 = r73.h;
            if (r2 <= 0) goto L11;
            float r74 = r73.w / r2;
        L12:
            this.aspectRatio = r74;
            this.observerTag = DownloadController.getInstance(r6.currentAccount).generateObserverTag();
            r0.setAllowLoadingOnAttachedOnly(true);
            r1.setAllowLoadingOnAttachedOnly(true);
            return;
        L11:
            r74 = 1.0f;
            goto L12
        L5:
            this.sizeFull = null;
            this.strippedSize = null;
            goto L6
        }

        private MediaCell(RichMessageLayout r6, TL_iv.pageBlockVideo r7) {
            this.imageReceiver = new ImageReceiver();
            this.blurImageReceiver = new ImageReceiver();
            this.buttonState = -1;
            this.buttonSize = AndroidUtilities.dp(48.0f);
            this.spoilerReveal = new SpoilerReveal();
            this.root = r6;
            this.pageBlock = r7;
            this.photo = null;
            this.sizeFull = null;
            this.strippedSize = null;
            TLRPC.Document r72 = r6.getDocument(r7.video_id);
            this.document = r72;
            boolean r1 = MessageObject.isVideoDocument(r72);
            this.realVideo = r1;
            int r2 = 0;
            if (r1 == false) goto L5;
        L8:
            boolean r12 = true;
        L9:
            this.isVideo = r12;
            if (r72 == null) goto L12;
            this.previewThumb = FileLoader.getClosestPhotoSizeWithSize(r72.thumbs, 320, false, null, true);
            this.strippedThumb = FileLoader.getStrippedPhotoSize(r72.thumbs);
        L13:
            if (r72 != null) goto L15;
        L22:
            float r73 = 1.0f;
        L23:
            this.aspectRatio = r73;
            this.observerTag = DownloadController.getInstance(r6.currentAccount).generateObserverTag();
            this.imageReceiver.setAllowLoadingOnAttachedOnly(true);
            this.blurImageReceiver.setAllowLoadingOnAttachedOnly(true);
            this.imageReceiver.setDelegate(new AnonymousClass1(this));
            return;
        L15:
            if (r2 >= this.document.attributes.size()) goto L22;
            TLRPC.DocumentAttribute r74 = this.document.attributes.get(r2);
            if ((r74 instanceof TLRPC.TL_documentAttributeVideo) == false) goto L21;
            int r0 = r74.h;
            if (r0 <= 0) goto L21;
            r73 = r74.w / r0;
        L21:
            r2 = r2 + 1;
            goto L15
        L12:
            this.previewThumb = null;
            this.strippedThumb = null;
            goto L13
        L5:
            if (MessageObject.isGifDocument(r72) == true) goto L8;
            r12 = false;
            goto L9
        }

        private boolean allowAutoplay() {
            if (this.realVideo == false) goto L7;
            return SharedConfig.isAutoplayVideo();
        L7:
            return SharedConfig.isAutoplayGifs();
        }

        private void applyImage(boolean r17) {
            TLRPC.Photo r1 = this.photo;
            ImageLocation r2 = null;
            if (r1 != null) goto L5;
        L15:
            TLRPC.Document r12 = this.document;
            if (r12 == null) goto L36;
            TLRPC.PhotoSize r3 = this.strippedThumb;
            if (r3 == null) goto L20;
            ImageLocation r8 = ImageLocation.getForDocument(r3, r12);
        L21:
            TLRPC.PhotoSize r13 = this.previewThumb;
            if (r13 == null) goto L24;
            r2 = ImageLocation.getForDocument(r13, this.document);
        L24:
            ImageLocation r6 = r2;
            if (r17 == true) goto L27;
        L34:
            this.imageReceiver.setImage(null, null, r6, null, r8, "b1", null, this.document.size, "mp4", this.root.messageObject, 1);
            return;
        L27:
            if (this.isVideo == false) goto L34;
            if (allowAutoplay() == false) goto L31;
        L32:
            this.imageReceiver.setAllowStartAnimation(true);
            this.imageReceiver.setAutoRepeat(1);
            this.imageReceiver.setImage(ImageLocation.getForDocument(this.document), ImageLoader.AUTOPLAY_FILTER, r6, null, r8, "b1", null, this.document.size, "mp4", this.root.messageObject, 1);
            return;
        L31:
            if (this.mediaForced == false) goto L34;
        L20:
            r8 = null;
            goto L21
        L36:
            return;
        L5:
            if (this.sizeFull == null) goto L15;
            TLRPC.PhotoSize r32 = this.strippedSize;
            if (r32 == null) goto L9;
            r2 = ImageLocation.getForPhoto(r32, r1);
        L9:
            ImageLocation r82 = r2;
            ImageReceiver r33 = this.imageReceiver;
            if (r17 == false) goto L13;
            r33.setImage(null, null, ImageLocation.getForPhoto(this.sizeFull, this.photo), null, r82, "b1", null, this.sizeFull.size, null, this.root.messageObject, 1);
            return;
        L13:
            r33.setImage(null, null, null, null, r82, "b1", null, this.sizeFull.size, null, this.root.messageObject, 1);
        }

        private boolean computeAutoDownload() {
            if (this.document == null) goto L14;
            if (this.isVideo == true) goto L7;
            return true;
        L7:
            if (allowAutoplay() == true) goto L9;
        L11:
            return false;
        L9:
            if (DownloadController.getInstance(this.root.currentAccount).canDownloadMedia(4, this.document.size) == false) goto L11;
            return true;
        L14:
            if ((DownloadController.getInstance(this.root.currentAccount).getCurrentDownloadMask() & 1) == 0) goto L16;
            return true;
        L16:
            return false;
        }

        private void didPressButton(View r6, boolean r7) {
            int r0 = this.buttonState;
            if (r0 != 0) goto L14;
            this.mediaForced = true;
            RadialProgress2 r02 = this.radialProgress;
            if (r02 == null) goto L7;
            r02.setProgress(0.0f, r7);
        L7:
            applyImage(true);
            this.buttonState = 1;
            RadialProgress2 r5 = this.radialProgress;
            if (r5 == null) goto L10;
            r5.setIcon(3, true, r7);
        L10:
            if (r6 == null) goto L33;
            r6.invalidate();
            return;
        L33:
            return;
        L14:
            if (r0 != 1) goto L21;
            this.mediaForced = false;
            this.imageReceiver.cancelLoadImage();
            this.buttonState = 0;
            RadialProgress2 r52 = this.radialProgress;
            if (r52 == null) goto L18;
            r52.setIcon(2, false, r7);
        L18:
            if (r6 == null) goto L34;
            r6.invalidate();
            return;
        L34:
            return;
        L21:
            if (r0 != 2) goto L28;
            this.mediaForced = true;
            this.imageReceiver.setAllowStartAnimation(true);
            applyImage(true);
            this.imageReceiver.startAnimation();
            this.buttonState = -1;
            RadialProgress2 r53 = this.radialProgress;
            if (r53 == null) goto L25;
            r53.setIcon(4, false, r7);
        L25:
            if (r6 == null) goto L35;
            r6.invalidate();
            return;
        L35:
            return;
        L28:
            if (r0 == 3) goto L30;
            return;
        L30:
            if (RichMessageLayout.m4821$$Nest$fgetdelegate(this.root) == null) goto L37;
            RichMessageLayout.m4821$$Nest$fgetdelegate(this.root).openArticlePhoto(RichMessageLayout.m4820$$Nest$fgetcell(this.root), this.pageBlock);
            return;
        }

        private void drawSpoiler(Canvas r12) {
            prepareBlurImage();
            float r0 = this.imageReceiver.getImageX();
            float r1 = this.imageReceiver.getImageY();
            float r2 = this.imageReceiver.getImageWidth();
            float r3 = this.imageReceiver.getImageHeight();
            if (r2 > 0.0f) goto L5;
            return;
        L5:
            if (r3 <= 0.0f) goto L20;
            r12.save();
            r12.clipRect(r0, r1, r0 + r2, r1 + r3);
            this.spoilerReveal.clipOut(r12);
            if (this.blurImageReceiver.getBitmap() == null) goto L10;
            this.blurImageReceiver.setImageCoords(r0, r1, r2, r3);
            this.blurImageReceiver.setAlpha(this.imageReceiver.getCurrentAlpha());
            this.blurImageReceiver.draw(r12);
        L10:
            SpoilerEffect2 r5 = this.root.getMediaSpoilerEffect();
            if (r5 == null) goto L13;
            r12.translate(r0, r1);
            Canvas r6 = r12;
            r5.draw(r6, this.parentView, Math.round(r2), Math.round(r3), this.imageReceiver.getCurrentAlpha());
        L14:
            r6.restore();
            View r11 = this.parentView;
            if (r11 == null) goto L19;
            r11.invalidate();
            return;
        L19:
            return;
        L13:
            r6 = r12;
            goto L14
        }

        public static MediaCell forPageBlock(RichMessageLayout r1, TL_iv.PageBlock r2) {
            if ((r2 instanceof TL_iv.pageBlockPhoto) == false) goto L7;
            return new MediaCell(r1, (TL_iv.pageBlockPhoto) r2);
        L7:
            if ((r2 instanceof TL_iv.pageBlockVideo) == true) goto L9;
            return null;
        L9:
            return new MediaCell(r1, (TL_iv.pageBlockVideo) r2);
        }

        private boolean isOnButton(float r3, float r4) {
            if (this.buttonState != (-1)) goto L5;
            return false;
        L5:
            if (r3 < this.buttonX) goto L16;
            int r1 = this.buttonSize;
            if (r3 <= (r0 + r1)) goto L9;
            return false;
        L9:
            if (r4 >= this.buttonY) goto L11;
            return false;
        L11:
            if (r4 > (r2 + r1)) goto L19;
            return true;
        L19:
            return false;
        L16:
            return false;
        }

        private boolean isSpoiler() {
            TL_iv.PageBlock r1 = this.pageBlock;
            if ((r1 instanceof TL_iv.pageBlockPhoto) == false) goto L7;
            return ((TL_iv.pageBlockPhoto) r1).spoiler;
        L7:
            if ((r1 instanceof TL_iv.pageBlockVideo) == true) goto L9;
            return false;
        L9:
            return ((TL_iv.pageBlockVideo) r1).spoiler;
        }

        private void prepareBlurImage() {
            if (this.blurImageReceiver.getBitmap() != null) goto L5;
        L7:
            Bitmap r0 = this.imageReceiver.getBitmap();
            if (r0 != null) goto L10;
            return;
        L10:
            if (r0.isRecycled() == false) goto L13;
            return;
        L13:
            if (r0 == this.blurSource) goto L15;
        L17:
            this.blurSource = r0;
            this.blurImageReceiver.setImageBitmap(Utilities.stackBlurBitmapMax(r0, false));
            if (fancyBlurFilter != null) goto L20;
            ColorMatrix r02 = new ColorMatrix();
            AndroidUtilities.multiplyBrightnessColorMatrix(r02, 0.9f);
            AndroidUtilities.adjustSaturationColorMatrix(r02, 0.6f);
            fancyBlurFilter = new ColorMatrixColorFilter(r02);
        L20:
            this.blurImageReceiver.setColorFilter(fancyBlurFilter);
            return;
        L15:
            if (this.blurImageReceiver.getBitmap() == null) goto L17;
            return;
        L5:
            if (this.imageReceiver.getAnimation() == null) goto L7;
        }

        public void attach(View r2) {
            this.parentView = r2;
            this.imageReceiver.setParentView(r2);
            this.imageReceiver.onAttachedToWindow();
            this.blurImageReceiver.setParentView(r2);
            this.blurImageReceiver.onAttachedToWindow();
            ensureProgress(r2);
            updateButtonState(r2, false);
        }

        public void detach() {
            this.imageReceiver.onDetachedFromWindow();
            this.blurImageReceiver.onDetachedFromWindow();
            this.blurSource = null;
            DownloadController.getInstance(this.root.currentAccount).removeLoadingFileObserver(this);
        }

        public void draw(Canvas r3) {
            this.imageReceiver.draw(r3);
            if (isSpoiler() == true) goto L5;
        L8:
            RadialProgress2 r0 = this.radialProgress;
            if (r0 != null) goto L11;
            return;
        L11:
            if (this.buttonState == (-1)) goto L15;
            r0.draw(r3);
            return;
        L15:
            return;
        L5:
            if (this.spoilerReveal.fullyRevealed() == true) goto L8;
            drawSpoiler(r3);
        }

        public void ensureProgress(View r5) {
            RadialProgress2 r0 = this.radialProgress;
            if (r0 != null) goto L7;
            if (r5 == null) goto L7;
            RadialProgress2 r02 = new RadialProgress2(r5);
            this.radialProgress = r02;
            r02.setProgressColor(-1);
            this.radialProgress.setColors(1711276032, 2130706432, -1, -2500135);
            RadialProgress2 r52 = this.radialProgress;
            int r03 = this.buttonX;
            int r1 = this.buttonY;
            int r4 = this.buttonSize;
            r52.setProgressRect(r03, r1, r03 + r4, r4 + r1);
            return;
        L7:
            if (r0 == null) goto L11;
            if (r5 == null) goto L12;
            r0.setParent(r5);
            RadialProgress2 r53 = this.radialProgress;
            int r04 = this.buttonX;
            int r12 = this.buttonY;
            int r42 = this.buttonSize;
            r53.setProgressRect(r04, r12, r04 + r42, r42 + r12);
            return;
        L12:
            return;
        }

        public boolean fileExists() {
            if (this.sizeFull == null) goto L13;
            File r0 = FileLoader.getInstance(this.root.currentAccount).getPathToAttach(this.sizeFull, true);
            File r4 = FileLoader.getInstance(this.root.currentAccount).getPathToAttach(this.sizeFull, false);
            if (r0.exists() == true) goto L11;
            if (r4 != null) goto L8;
        L10:
            return false;
        L8:
            if (r4.exists() == false) goto L10;
        L11:
            return true;
        L13:
            if (this.document == null) goto L23;
            File r02 = FileLoader.getInstance(this.root.currentAccount).getPathToAttach(this.document);
            File r42 = FileLoader.getInstance(this.root.currentAccount).getPathToAttach(this.document, true);
            if (r02 != null) goto L17;
        L18:
            if (r42 != null) goto L20;
        L22:
            return false;
        L20:
            if (r42.exists() == false) goto L22;
        L21:
            return true;
        L17:
            if (r02.exists() == true) goto L21;
        L23:
            return true;
        }

        public CharSequence getAccessibilityText() {
            if (this.isVideo == false) goto L5;
            int r0 = R.string.AttachVideo;
        L6:
            String r02 = LocaleController.getString(r0);
            if (isSpoiler() == true) goto L9;
        L12:
            return r02;
        L9:
            if (this.spoilerReveal.fullyRevealed() == true) goto L12;
            return TextUtils.concat(new CharSequence[]{r02, ", ", LocaleController.getString(R.string.Spoiler)});
        L5:
            r0 = R.string.AttachPhoto;
            goto L6
        }

        public String getFileName() {
            TLRPC.PhotoSize r0 = this.sizeFull;
            if (r0 != null) goto L5;
            TLRPC.Document r1 = this.document;
            if (r1 != null) goto L9;
            return null;
        L9:
            return FileLoader.getAttachFileName(r1);
        L5:
            return FileLoader.getAttachFileName(r0);
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public int getObserverTag() {
            return this.observerTag;
        }

        public boolean isInside(float r3, float r4) {
            if (r3 >= this.x) goto L5;
            return false;
        L5:
            if (r3 <= (r0 + this.w)) goto L7;
            return false;
        L7:
            if (r4 >= this.y) goto L9;
            return false;
        L9:
            if (r4 > (r3 + this.h)) goto L16;
            return true;
        L16:
            return false;
        }

        public boolean onAccessibilityClick(View r9) {
            if (isSpoiler() == false) goto L9;
            if (this.spoilerReveal.isRevealing() == true) goto L9;
            float r6 = this.imageReceiver.getImageWidth();
            float r7 = this.imageReceiver.getImageHeight();
            float r4 = r6 / 2.0f;
            this.spoilerReveal.start(r9, r4 + this.imageReceiver.getImageX(), this.imageReceiver.getImageY() + (r7 / 2.0f), r6, r7);
            return true;
        L9:
            if (RichMessageLayout.m4821$$Nest$fgetdelegate(this.root) == null) goto L12;
            RichMessageLayout.m4821$$Nest$fgetdelegate(this.root).openArticlePhoto(RichMessageLayout.m4820$$Nest$fgetcell(this.root), this.pageBlock);
            return true;
        L12:
            return false;
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onFailedDownload(String r1, boolean r2) {
            updateButtonState(this.parentView, false);
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onProgressDownload(String r4, long r5, long r7) {
            RadialProgress2 r42 = this.radialProgress;
            if (r42 == null) goto L10;
            if (r7 > 0) goto L7;
            float r52 = 0.0f;
        L8:
            r42.setProgress(Math.min(1.0f, r52), true);
            goto L10
        L7:
            r52 = r5 / r7;
        L10:
            if (this.buttonState == 1) goto L13;
            updateButtonState(this.parentView, true);
            return;
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onSuccessDownload(String r3) {
            RadialProgress2 r32 = this.radialProgress;
            if (r32 == null) goto L6;
            r32.setProgress(1.0f, true);
        L6:
            if (this.isVideo == true) goto L8;
        L12:
            updateButtonState(this.parentView, true);
            return;
        L8:
            if (allowAutoplay() == false) goto L10;
        L11:
            applyImage(true);
            goto L12
        L10:
            if (this.mediaForced == false) goto L12;
            goto L11
        }

        public boolean onTouchEvent(MotionEvent r9, View r10) {
            int r0 = r9.getActionMasked();
            float r1 = r9.getX();
            float r92 = r9.getY();
            boolean r2 = isInside(r1, r92);
            boolean r93 = isOnButton(r1, r92);
            if (r0 != 0) goto L18;
            if (r2 == false) goto L14;
            if (r93 == true) goto L10;
            int r94 = this.buttonState;
            if (r94 == 0) goto L10;
            if (r94 != 2) goto L14;
        L10:
            this.buttonPressed = true;
            if (r10 == null) goto L13;
            r10.invalidate();
        L13:
            return true;
        L14:
            if (r2 == false) goto L17;
            this.photoPressed = true;
            return true;
        L17:
            return false;
        L18:
            if (r0 != 1) goto L43;
            if (this.buttonPressed == false) goto L27;
            this.buttonPressed = false;
            if (r10 == null) goto L24;
            r10.playSoundEffect(0);
            r10.invalidate();
        L24:
            didPressButton(r10, true);
            return true;
        L27:
            if (this.photoPressed == false) goto L41;
            this.photoPressed = false;
            if (r2 == false) goto L41;
            if (r10 == null) goto L33;
            r10.playSoundEffect(0);
        L33:
            if (isSpoiler() == false) goto L38;
            if (this.spoilerReveal.isRevealing() == true) goto L38;
            float r6 = this.imageReceiver.getImageWidth();
            float r7 = this.imageReceiver.getImageHeight();
            this.spoilerReveal.start(r10, this.imageReceiver.getImageX() + (r6 / 2.0f), this.imageReceiver.getImageY() + (r7 / 2.0f), r6, r7);
        L40:
            return true;
        L38:
            if (RichMessageLayout.m4821$$Nest$fgetdelegate(this.root) == null) goto L40;
            RichMessageLayout.m4821$$Nest$fgetdelegate(this.root).openArticlePhoto(RichMessageLayout.m4820$$Nest$fgetcell(this.root), this.pageBlock);
        L41:
            return false;
        L43:
            if (r0 != 3) goto L47;
            this.photoPressed = false;
            this.buttonPressed = false;
            return false;
        L47:
            if (this.photoPressed == false) goto L49;
        L52:
            return true;
        L49:
            if (this.buttonPressed == true) goto L52;
            return false;
        }

        public void setRect(int r6, int r7, int r8, int r9) {
            this.x = r6;
            this.y = r7;
            this.w = r8;
            this.h = r9;
            this.imageReceiver.setImageCoords(r6, r7, r8, r9);
            int r0 = this.buttonSize;
            int r62 = r6 + ((r8 - r0) / 2);
            this.buttonX = r62;
            int r72 = r7 + ((r9 - r0) / 2);
            this.buttonY = r72;
            RadialProgress2 r82 = this.radialProgress;
            if (r82 == null) goto L5;
            r82.setProgressRect(r62, r72, r62 + r0, r0 + r72);
        L5:
            boolean r63 = computeAutoDownload();
            this.autoDownload = r63;
            if (r63 == false) goto L8;
        L11:
            boolean r64 = true;
        L12:
            applyImage(r64);
            return;
        L8:
            if (fileExists() == true) goto L11;
            r64 = false;
            goto L12
        }

        public void updateButtonState(View r10, boolean r11) {
            if (r10 != null) goto L4;
            r10 = this.parentView;
        L4:
            ensureProgress(r10);
            String r0 = getFileName();
            if (TextUtils.isEmpty(r0) == false) goto L10;
            this.buttonState = -1;
            RadialProgress2 r9 = this.radialProgress;
            if (r9 == null) goto L70;
            r9.setIcon(4, false, false);
            return;
        L70:
            return;
        L10:
            AnimatedFileDrawable r1 = this.imageReceiver.getAnimation();
            if (r1 != null) goto L13;
        L17:
            boolean r12 = false;
        L19:
            if (fileExists() == false) goto L21;
        L48:
            DownloadController.getInstance(this.root.currentAccount).removeLoadingFileObserver(this);
            if (this.realVideo == false) goto L55;
            if (r12 == true) goto L55;
            this.buttonState = 3;
            RadialProgress2 r92 = this.radialProgress;
            if (r92 == null) goto L67;
            r92.setIcon(0, false, r11);
        L67:
            if (r10 == null) goto L71;
            r10.invalidate();
            return;
        L71:
            return;
        L55:
            if (this.isVideo == false) goto L64;
            if (r12 == true) goto L64;
            if (allowAutoplay() == true) goto L64;
            if (this.mediaForced == true) goto L64;
            this.buttonState = 2;
            RadialProgress2 r93 = this.radialProgress;
            if (r93 == null) goto L67;
            r93.setIcon(8, false, r11);
        L64:
            this.buttonState = -1;
            RadialProgress2 r94 = this.radialProgress;
            if (r94 == null) goto L67;
            r94.setIcon(4, false, r11);
            goto L67
        L21:
            if (this.isVideo == false) goto L24;
            if (r12 == true) goto L48;
        L24:
            DownloadController.getInstance(this.root.currentAccount).addLoadingFileObserver(r0, null, this);
            float r2 = 0.0f;
            if (this.autoDownload == false) goto L27;
        L39:
            this.buttonState = 1;
            Float r02 = ImageLoader.getInstance().getFileProgress(r0);
            if (r02 == null) goto L42;
            r2 = r02.floatValue();
        L42:
            RadialProgress2 r03 = this.radialProgress;
            if (r03 == null) goto L45;
            r03.setIcon(3, true, r11);
        L45:
            RadialProgress2 r95 = this.radialProgress;
            if (r95 == null) goto L67;
            r95.setProgress(r2, false);
            goto L67
        L27:
            if (this.mediaForced == true) goto L39;
            if (FileLoader.getInstance(this.root.currentAccount).isLoadingFile(r0) == true) goto L39;
            if (this.realVideo == false) goto L36;
            this.buttonState = 3;
            RadialProgress2 r04 = this.radialProgress;
            if (r04 == null) goto L45;
            r04.setIcon(0, true, r11);
            goto L45
        L36:
            this.buttonState = 0;
            RadialProgress2 r05 = this.radialProgress;
            if (r05 == null) goto L45;
            r05.setIcon(2, true, r11);
            goto L45
        L13:
            if (r1.hasBitmap() == false) goto L15;
        L16:
            r12 = true;
            goto L19
        L15:
            if (this.imageReceiver.isAnimationRunning() == false) goto L17;
            goto L16
        }
    }

    public static class PreviewView extends View implements TextSelectionHelper.ArticleSelectableView {
        private boolean allowActions;
        private final int currentAccount;
        private int insetBottom;
        private int insetLeft;
        private int insetRight;
        private int insetTop;
        private RichMessageLayout layout;
        private int maxHeight;
        private MessageObject messageObject;
        private int minHeight;
        private Utilities.Callback<URLSpan> onLinkPress;
        private float pressedX;
        private float pressedY;
        private Theme.ResourcesProvider resourcesProvider;
        private TL_iv.RichMessage richMessage;
        private TextSelectionHelper.ArticleTextSelectionHelper textSelectionHelper;
        private Runnable textSelectionLongPressRunnable;
        private final int touchSlop;
        private boolean translationLoading;

        public static final class Factory extends UItem.UItemFactory<PreviewView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem of(TL_iv.RichMessage r1) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                PreviewView r12 = (PreviewView) r1;
                r12.set((TL_iv.RichMessage) r2.object);
                r12.setTranslationLoading(r2.checked);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean contentsEquals(UItem r2, UItem r3) {
                if (r2.id == r3.id) goto L5;
                return false;
            L5:
                if (r2.object == r3.object) goto L7;
                return false;
            L7:
                if (r2.checked != r3.checked) goto L13;
                return true;
            L13:
                return false;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public PreviewView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                PreviewView r0 = new PreviewView(r1, r3, r5);
                r0.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(16.0f));
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean equals(UItem r1, UItem r2) {
                if (r1.id != r2.id) goto L6;
                return true;
            L6:
                return false;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean isClickable() {
                return false;
            }
        }

        public static class PaddedTextLayoutBlock implements TextSelectionHelper.TextLayoutBlock {
            private final TextSelectionHelper.TextLayoutBlock inner;
            private final int px;
            private final int py;

            public PaddedTextLayoutBlock(TextSelectionHelper.TextLayoutBlock r1, int r2, int r3) {
                this.inner = r1;
                this.px = r2;
                this.py = r3;
            }

            @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
            public Layout getLayout() {
                return this.inner.getLayout();
            }

            @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
            public CharSequence getPrefix() {
                return this.inner.getPrefix();
            }

            @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
            public int getRow() {
                return this.inner.getRow();
            }

            @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
            public /* bridge */ /* synthetic */ Rect getSelectionBounds() {
                return super.getSelectionBounds();
            }

            @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
            public CharSequence getText() {
                return this.inner.getText();
            }

            @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
            public int getX() {
                return this.inner.getX() + this.px;
            }

            @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
            public int getY() {
                return this.inner.getY() + this.py;
            }
        }

        public static /* synthetic */ void $r8$lambda$faJqZEgTF_KWe4vR580ObFjUuVE(PreviewView r0) {
            r0.lambda$onTouchEvent$0();
        }

        public PreviewView(Context r3) {
            this(r3, UserConfig.selectedAccount, null);
        }

        public PreviewView(Context r2, int r3, Theme.ResourcesProvider r4) {
            super(r2);
            this.minHeight = -1;
            this.maxHeight = -1;
            this.allowActions = true;
            this.currentAccount = r3;
            this.resourcesProvider = r4;
            this.touchSlop = ViewConfiguration.get(r2).getScaledTouchSlop();
            NotificationCenter.listenEmojiLoading(this);
        }

        private void buildLayout(int r4) {
            if (r4 <= 0) goto L19;
            TL_iv.RichMessage r1 = this.richMessage;
            if (r1 == null) goto L19;
            RichMessageLayout r2 = this.layout;
            if (r2 != null) goto L10;
        L12:
            RichMessageLayout r12 = this.layout;
            if (r12 == null) goto L15;
            r12.detach(this);
        L15:
            RichMessageLayout r13 = new RichMessageLayout(this.messageObject, r4, null);
            this.layout = r13;
            r13.forceTranslationLoading = this.translationLoading;
            r13.onLinkPress = this.onLinkPress;
            r13.setResourcesProvider(this.resourcesProvider);
            RichMessageLayout r42 = this.layout;
            r42.invalidateAnimatedEmojiInParent = true;
            r42.checkQuoteLine(null, null);
            if (isAttachedToWindow() == false) goto L21;
            this.layout.attach(this);
            this.layout.updateAnimatedEmojis(0);
            return;
        L21:
            return;
        L10:
            if (r2.needsUpdate(r1, r4) == true) goto L12;
            return;
        L19:
            this.layout = null;
        }

        private /* synthetic */ void lambda$onTouchEvent$0() {
            RichMessageLayout r0 = this.layout;
            if (r0 != null) goto L5;
        L7:
            this.textSelectionHelper.trySelect(this);
            return;
        L5:
            if (r0.isPressingLink() == false) goto L7;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.ArticleSelectableView
        public void fillTextLayoutBlocks(ArrayList<TextSelectionHelper.TextLayoutBlock> r6) {
            if (this.layout == null) goto L8;
            int r0 = this.insetLeft;
            int r1 = this.insetTop;
            int r2 = 0;
        L6:
            if (r2 >= this.layout.textBlocks.size()) goto L10;
            r6.add(new PaddedTextLayoutBlock(this.layout.textBlocks.get(r2), r0, r1));
            r2 = r2 + 1;
            goto L6
        L10:
            return;
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            RichMessageLayout r0 = this.layout;
            if (r0 == null) goto L6;
            r0.attach(this);
            this.layout.updateAnimatedEmojis(0);
            return;
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            RichMessageLayout r0 = this.layout;
            if (r0 == null) goto L6;
            r0.detach(this);
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r11) {
            if (this.layout == null) goto L28;
            float r7 = (getHeight() - this.insetTop) - this.insetBottom;
            int r8 = 0;
            if (this.layout.getHeight() <= ((getHeight() - this.insetTop) - this.insetBottom)) goto L8;
            boolean r9 = true;
        L9:
            r11.save();
            if (r9 == false) goto L12;
            r11.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
        L12:
            r11.translate(this.insetLeft, this.insetTop);
            this.layout.draw(r11, this.insetLeft, Math.max(0, (getWidth() - this.layout.getMinWidth()) - this.insetLeft), null, 0.0f, r7);
            if (this.layout.hasOverlay() == false) goto L15;
            this.layout.drawOverlay(r11, null);
        L15:
            if (r9 == false) goto L17;
            r11.translate(-this.insetLeft, -this.insetTop);
            r11.save();
            RectF r1 = AndroidUtilities.rectTmp;
            r1.set(0.0f, getHeight() - AndroidUtilities.dp(24.0f), getWidth(), getHeight());
            this.layout.clip.draw(r11, r1, 3, 1.0f);
            r11.restore();
            r11.restore();
        L17:
            r11.restore();
            TextSelectionHelper.ArticleTextSelectionHelper r12 = this.textSelectionHelper;
            if (r12 != null) goto L20;
            return;
        L20:
            if (r12.isInSelectionMode() == false) goto L27;
            int r13 = this.insetLeft;
            int r2 = this.insetTop;
        L23:
            if (r8 >= this.layout.textBlocks.size()) goto L29;
            TextSelectionHelper.TextLayoutBlock r3 = this.layout.textBlocks.get(r8);
            r11.save();
            r11.translate(r3.getX() + r13, r3.getY() + r2);
            this.textSelectionHelper.draw(r11, this, r8);
            r11.restore();
            r8 = r8 + 1;
            goto L23
        L29:
            return;
        L27:
            return;
        L8:
            r9 = false;
            goto L9
        }

        @Override // android.view.View
        public void onMeasure(int r4, int r5) {
            int r42 = View.MeasureSpec.getSize(r4);
            buildLayout((r42 - this.insetLeft) - this.insetRight);
            RichMessageLayout r0 = this.layout;
            if (r0 == null) goto L5;
            int r02 = r0.getHeight();
        L6:
            int r03 = (r02 + this.insetTop) + this.insetBottom;
            int r1 = this.maxHeight;
            if (r1 <= 0) goto L10;
            if (r03 <= r1) goto L10;
            r03 = r1;
        L10:
            int r12 = this.minHeight;
            if (r12 <= 0) goto L14;
            if (r03 >= r12) goto L14;
            r03 = r12;
        L14:
            int r13 = View.MeasureSpec.getMode(r5);
            if (r13 != Integer.MIN_VALUE) goto L17;
            r03 = Math.min(r03, View.MeasureSpec.getSize(r5));
        L21:
            setMeasuredDimension(r42, r03);
            return;
        L17:
            if (r13 != 1073741824) goto L21;
            r03 = View.MeasureSpec.getSize(r5);
            goto L21
        L5:
            r02 = 0;
            goto L6
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r6) {
            if (this.allowActions == false) goto L5;
            int r0 = r6.getActionMasked();
            if (r0 != 0) goto L9;
            this.pressedX = r6.getX();
            this.pressedY = r6.getY();
        L17:
            TextSelectionHelper.ArticleTextSelectionHelper r3 = this.textSelectionHelper;
            if (r3 == null) goto L33;
            if (r0 == 0) goto L28;
            if (r0 == 1) goto L25;
            if (r0 == 2) goto L25;
            if (r0 != 3) goto L33;
        L25:
            Runnable r1 = this.textSelectionLongPressRunnable;
            if (r1 == null) goto L33;
            removeCallbacks(r1);
            goto L33
        L28:
            r3.setMaybeView((int) r6.getX(), (int) r6.getY(), this);
            if (this.textSelectionLongPressRunnable != null) goto L31;
            this.textSelectionLongPressRunnable = new RichMessageLayout$PreviewView$$ExternalSyntheticLambda0(this);
        L31:
            removeCallbacks(this.textSelectionLongPressRunnable);
            postDelayed(this.textSelectionLongPressRunnable, ViewConfiguration.getLongPressTimeout());
        L33:
            if (this.layout == null) goto L40;
            r6.offsetLocation(-this.insetLeft, -this.insetTop);
            boolean r12 = this.layout.onTouchEvent(r6);
            r6.offsetLocation(this.insetLeft, this.insetTop);
            if (r12 == false) goto L40;
            if (r0 != 1) goto L38;
            requestLayout();
        L38:
            return true;
        L40:
            if (super.onTouchEvent(r6) == false) goto L42;
        L47:
            return true;
        L42:
            if (this.textSelectionHelper == null) goto L45;
            if (r0 == 0) goto L47;
            return false;
        L45:
            return false;
        L9:
            if (r0 != 2) goto L17;
            if (this.textSelectionHelper == null) goto L17;
            if (Math.abs(r6.getX() - this.pressedX) >= this.touchSlop) goto L17;
            if (Math.abs(r6.getY() - this.pressedY) >= this.touchSlop) goto L17;
            return true;
        L5:
            return super.onTouchEvent(r6);
        }

        public void set(TL_iv.RichMessage r5) {
            if (this.richMessage != r5) goto L5;
            return;
        L5:
            this.richMessage = r5;
            if (this.messageObject != null) goto L8;
            TLRPC.TL_message r0 = new TLRPC.TL_message();
            r0.out = true;
            this.messageObject = new MessageObject(this.currentAccount, r0, false, false);
        L8:
            this.messageObject.messageOwner.rich_message = r5;
            RichMessageLayout r52 = this.layout;
            if (r52 == null) goto L11;
            r52.detach(this);
        L11:
            this.layout = null;
            requestLayout();
            invalidate();
        }

        public void setAllowActions(boolean r1) {
            this.allowActions = r1;
        }

        public void setMaxHeight(int r1) {
            this.maxHeight = r1;
        }

        public void setMinHeight(int r1) {
            this.minHeight = r1;
        }

        public void setOnLinkPress(Utilities.Callback<URLSpan> r1) {
            this.onLinkPress = r1;
            RichMessageLayout r0 = this.layout;
            if (r0 == null) goto L6;
            r0.onLinkPress = r1;
            return;
        }

        @Override // android.view.View
        public void setPadding(int r2, int r3, int r4, int r5) {
            if (this.insetLeft == r2) goto L5;
        L11:
            this.insetLeft = r2;
            this.insetTop = r3;
            this.insetRight = r4;
            this.insetBottom = r5;
            requestLayout();
            invalidate();
            return;
        L5:
            if (this.insetTop != r3) goto L11;
            if (this.insetRight != r4) goto L11;
            if (this.insetBottom != r5) goto L11;
        }

        public void setResourcesProvider(Theme.ResourcesProvider r1) {
            this.resourcesProvider = r1;
            RichMessageLayout r0 = this.layout;
            if (r0 == null) goto L6;
            r0.setResourcesProvider(r1);
            return;
        }

        public void setTextSelectionHelper(TextSelectionHelper.ArticleTextSelectionHelper r1) {
            this.textSelectionHelper = r1;
        }

        public void setTranslationLoading(boolean r2) {
            this.translationLoading = r2;
            RichMessageLayout r0 = this.layout;
            if (r0 == null) goto L5;
            r0.forceTranslationLoading = r2;
        L5:
            invalidate();
        }
    }

    public static class QuoteBackground {
        int endBlockIndex;
        int level;
        int outerBottomVpad;
        int outerTopVpad;
        int padding;
        int startBlockIndex;

        public QuoteBackground(int r8, int r9, int r10, int r11) {
            this(r8, r9, r10, r11, 0, 0);
        }

        public QuoteBackground(int r8, int r9, int r10, int r11, int r12) {
            this(r8, r9, r10, r11, r12, r12);
        }

        public QuoteBackground(int r1, int r2, int r3, int r4, int r5, int r6) {
            this.startBlockIndex = r1;
            this.endBlockIndex = r2;
            this.padding = r3;
            this.level = r4;
            this.outerTopVpad = r5;
            this.outerBottomVpad = r6;
        }
    }

    public static class RichAudioBlock extends RichBlock implements DownloadController.FileDownloadProgressListener, NotificationCenter.NotificationCenterDelegate {
        private final TextPaint audioTimePaint;
        public final TL_iv.pageBlockAudio block;
        private boolean buttonPressed;
        private int buttonState;
        private final int buttonX;
        private final int buttonY;
        private final TLRPC.Document currentDocument;
        private final MessageObject currentMessageObject;
        private StaticLayout durationLayout;
        private String lastTimeString;
        private int layoutWidth;
        private final int observerTag;
        private final RadialProgress2 radialProgress;
        private final SeekBar seekBar;
        private int seekBarWidth;
        private int seekBarX;
        private int seekBarY;
        private final int size;
        private StaticLayout titleLayout;

        public static /* synthetic */ void $r8$lambda$lMQ1Ki15kGhTBdyJ5VOJTAMYc0k(RichAudioBlock r0, float r1) {
            r0.lambda$new$0(r1);
        }

        public RichAudioBlock(RichMessageLayout r4, Rect r5, int r6, TL_iv.pageBlockAudio r7) {
            super(r4, r5, r6);
            TextPaint r52 = new TextPaint(1);
            this.audioTimePaint = r52;
            int r62 = AndroidUtilities.dp(16.0f);
            this.buttonX = r62;
            int r0 = AndroidUtilities.dp(9.0f);
            this.buttonY = r0;
            int r1 = AndroidUtilities.dp(44.0f);
            this.size = r1;
            this.layoutWidth = -1;
            this.block = r7;
            MessageObject r72 = r4.audioBlocks.get(r7);
            this.currentMessageObject = r72;
            if (r72 == null) goto L5;
            TLRPC.Document r73 = r72.getDocument();
        L6:
            this.currentDocument = r73;
            this.observerTag = DownloadController.getInstance(r4.currentAccount).generateObserverTag();
            r52.setTypeface(AndroidUtilities.regular());
            RadialProgress2 r42 = new RadialProgress2(null);
            this.radialProgress = r42;
            r42.setCircleRadius(AndroidUtilities.dp(24.0f));
            r42.setProgressRect(r62, r0, r62 + r1, r1 + r0);
            SeekBar r43 = new SeekBar(null);
            this.seekBar = r43;
            r43.setDelegate(new RichMessageLayout$RichAudioBlock$$ExternalSyntheticLambda0(this));
            layoutInner();
            updateButtonState(false);
            return;
        L5:
            r73 = null;
            goto L6
        }

        private boolean canStream() {
            if (SharedConfig.streamMedia == false) goto L14;
            MessageObject r0 = this.currentMessageObject;
            if (r0 != null) goto L7;
            return false;
        L7:
            if (r0.isMusic() == true) goto L9;
            return false;
        L9:
            if (this.currentMessageObject.shouldEncryptPhotoOrVideo() == false) goto L11;
            return false;
        L11:
            if (DialogObject.isEncryptedDialog(this.currentMessageObject.getDialogId()) == true) goto L19;
            return true;
        L19:
            return false;
        L14:
            return false;
        }

        private void didPressedButton(boolean r12) {
            int r0 = this.root.currentAccount;
            int r1 = this.buttonState;
            if (r1 == 0) goto L5;
            if (r1 != 1) goto L18;
            if (MediaController.getInstance().pauseMessage(this.currentMessageObject) == false) goto L30;
            this.buttonState = 0;
            this.radialProgress.setIcon(getIconForCurrentState(), false, r12);
            View r11 = this.view;
            if (r11 == null) goto L31;
            r11.invalidate();
            return;
        L31:
            return;
        L30:
            return;
        L18:
            if (r1 != 2) goto L23;
            this.radialProgress.setProgress(0.0f, false);
            FileLoader.getInstance(r0).loadFile(this.currentDocument, this.root.messageObject, 1, 1);
            this.buttonState = 3;
            this.radialProgress.setIcon(getIconForCurrentState(), true, r12);
            View r112 = this.view;
            if (r112 == null) goto L32;
            r112.invalidate();
            return;
        L32:
            return;
        L23:
            if (r1 != 3) goto L33;
            FileLoader.getInstance(r0).cancelLoadFile(this.currentDocument);
            this.buttonState = 2;
            this.radialProgress.setIcon(getIconForCurrentState(), false, r12);
            View r113 = this.view;
            if (r113 == null) goto L34;
            r113.invalidate();
            return;
        L34:
            return;
        L33:
            return;
        L5:
            if (MediaController.getInstance().setPlaylist(this.root.audioMessages, this.currentMessageObject, 0, false, null) == false) goto L28;
            this.buttonState = 1;
            this.radialProgress.setIcon(getIconForCurrentState(), false, r12);
            View r114 = this.view;
            if (r114 == null) goto L29;
            r114.invalidate();
            return;
        L29:
            return;
        }

        private int getIconForCurrentState() {
            int r1 = this.buttonState;
            if (r1 != 1) goto L6;
            return 1;
        L6:
            if (r1 != 2) goto L9;
            return 2;
        L9:
            if (r1 != 3) goto L11;
            return 3;
        L11:
            return 0;
        }

        private /* synthetic */ void lambda$new$0(float r2) {
            MessageObject r0 = this.currentMessageObject;
            if (r0 != null) goto L5;
            return;
        L5:
            r0.audioProgress = r2;
            MediaController.getInstance().seekToProgress(this.currentMessageObject, r2);
        }

        private void layoutInner() {
            int r0 = this.maxWidth;
            RichMessageLayout r1 = this.root;
            this.layoutWidth = (r0 + r1.padLeft) + r1.padRight;
            int r02 = (this.buttonX + AndroidUtilities.dp(50.0f)) + this.size;
            this.seekBarX = r02;
            this.seekBarWidth = Math.max(0, (this.layoutWidth - r02) - AndroidUtilities.dp(18.0f));
            MessageObject r2 = this.currentMessageObject;
            if (r2 == null) goto L5;
            String r22 = r2.getMusicAuthor(false);
        L6:
            MessageObject r4 = this.currentMessageObject;
            if (r4 == null) goto L9;
            String r42 = r4.getMusicTitle(false);
        L11:
            if (TextUtils.isEmpty(r42) == false) goto L17;
            if (TextUtils.isEmpty(r22) == false) goto L17;
            this.titleLayout = null;
            this.seekBarY = this.buttonY + ((this.size - AndroidUtilities.dp(30.0f)) / 2);
        L29:
            this.seekBar.setSize(this.seekBarWidth, AndroidUtilities.dp(30.0f));
            return;
        L17:
            if (TextUtils.isEmpty(r42) == true) goto L22;
            if (TextUtils.isEmpty(r22) == true) goto L22;
            SpannableStringBuilder r3 = new SpannableStringBuilder(String.format("%s - %s", new Object[]{r22, r42}));
        L26:
            if (TextUtils.isEmpty(r22) == true) goto L28;
            r3.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r22.length(), 18);
        L28:
            this.audioTimePaint.setTextSize(AndroidUtilities.dp(16.0f));
            int r10 = this.seekBarWidth + AndroidUtilities.dp(50.0f);
            this.titleLayout = new StaticLayout(TextUtils.ellipsize(r3, this.audioTimePaint, r10, TextUtils.TruncateAt.END), this.audioTimePaint, r10, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            this.seekBarY = (this.buttonY + ((this.size - AndroidUtilities.dp(30.0f)) / 2)) + AndroidUtilities.dp(11.0f);
        L22:
            if (TextUtils.isEmpty(r42) == true) goto L24;
            r3 = new SpannableStringBuilder(r42);
            goto L26
        L24:
            r3 = new SpannableStringBuilder(r22);
            goto L26
        L9:
            r42 = null;
            goto L11
        L5:
            r22 = null;
            goto L6
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r3, int r4, Object... r5) {
            MessageObject r42 = this.currentMessageObject;
            if (r42 != null) goto L6;
            return;
        L6:
            if (r3 != NotificationCenter.messagePlayingDidStart) goto L10;
            updateButtonState(true);
            return;
        L10:
            if (r3 != NotificationCenter.messagePlayingDidReset) goto L12;
        L22:
            updateButtonState(true);
            return;
        L12:
            if (r3 == NotificationCenter.messagePlayingPlayStateChanged) goto L22;
            if (r3 != NotificationCenter.messagePlayingProgressDidChanged) goto L24;
            Integer r32 = (Integer) r5[0];
            if (r42.getId() != r32.intValue()) goto L25;
            MessageObject r33 = MediaController.getInstance().getPlayingMessageObject();
            if (r33 == null) goto L26;
            MessageObject r43 = this.currentMessageObject;
            r43.audioProgress = r33.audioProgress;
            r43.audioProgressSec = r33.audioProgressSec;
            r43.audioPlayerDuration = r33.audioPlayerDuration;
            updatePlayingMessageProgress();
            return;
        L26:
            return;
        L25:
            return;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            return (this.padding.top + AndroidUtilities.dp(62.0f)) + this.padding.bottom;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getLastLineWidth() {
            return getMinWidth();
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public int getObserverTag() {
            return this.observerTag;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean isHorizontallyDragging() {
            return this.seekBar.isDragging();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onAttachedToWindow() {
            View r0 = this.view;
            if (r0 == null) goto L5;
            this.radialProgress.setParent(r0);
            this.seekBar.setParent(this.view);
        L5:
            updateButtonState(false);
            NotificationCenter.getInstance(this.root.currentAccount).addObserver(this, NotificationCenter.messagePlayingDidStart);
            NotificationCenter.getInstance(this.root.currentAccount).addObserver(this, NotificationCenter.messagePlayingDidReset);
            NotificationCenter.getInstance(this.root.currentAccount).addObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
            NotificationCenter.getInstance(this.root.currentAccount).addObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDetachedFromWindow() {
            DownloadController.getInstance(this.root.currentAccount).removeLoadingFileObserver(this);
            NotificationCenter.getInstance(this.root.currentAccount).removeObserver(this, NotificationCenter.messagePlayingDidStart);
            NotificationCenter.getInstance(this.root.currentAccount).removeObserver(this, NotificationCenter.messagePlayingDidReset);
            NotificationCenter.getInstance(this.root.currentAccount).removeObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
            NotificationCenter.getInstance(this.root.currentAccount).removeObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r8) {
            if (this.currentMessageObject != null) goto L5;
            return;
        L5:
            if (this.currentDocument == null) goto L59;
            int r0 = this.layoutWidth;
            int r1 = this.maxWidth;
            RichMessageLayout r2 = this.root;
            if (r0 == ((r1 + r2.padLeft) + r2.padRight)) goto L10;
            layoutInner();
        L10:
            r8.save();
            r8.translate(-this.root.padLeft, 0.0f);
            RadialProgress2 r02 = this.radialProgress;
            if (this.root.isOut() == false) goto L13;
            int r12 = Theme.key_chat_outLoader;
        L15:
            if (this.root.isOut() == false) goto L17;
            int r22 = Theme.key_chat_outLoaderSelected;
        L19:
            if (this.root.isOut() == false) goto L21;
            int r3 = Theme.key_chat_outMediaIcon;
        L23:
            if (this.root.isOut() == false) goto L25;
            int r4 = Theme.key_chat_outMediaIconSelected;
        L26:
            r02.setColorKeys(r12, r22, r3, r4);
            RadialProgress2 r03 = this.radialProgress;
            RichMessageLayout r13 = this.root;
            if (r13.isOut() == false) goto L29;
            int r23 = Theme.key_chat_outFileProgress;
        L30:
            r03.setProgressColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r13, r23));
            this.radialProgress.draw(r8);
            SeekBar r14 = this.seekBar;
            RichMessageLayout r04 = this.root;
            if (r04.isOut() == false) goto L33;
            int r24 = Theme.key_chat_outAudioSeekbar;
        L34:
            int r25 = RichMessageLayout.m4824$$Nest$mgetThemedColor(r04, r24);
            RichMessageLayout r05 = this.root;
            if (r05.isOut() == false) goto L37;
            int r32 = Theme.key_chat_outAudioCacheSeekbar;
        L38:
            int r33 = RichMessageLayout.m4824$$Nest$mgetThemedColor(r05, r32);
            RichMessageLayout r06 = this.root;
            if (r06.isOut() == false) goto L41;
            int r42 = Theme.key_chat_outAudioSeekbarFill;
        L42:
            int r43 = RichMessageLayout.m4824$$Nest$mgetThemedColor(r06, r42);
            RichMessageLayout r07 = this.root;
            if (r07.isOut() == false) goto L45;
            int r5 = Theme.key_chat_outAudioSeekbarFill;
        L46:
            int r52 = RichMessageLayout.m4824$$Nest$mgetThemedColor(r07, r5);
            RichMessageLayout r08 = this.root;
            if (r08.isOut() == false) goto L49;
            int r6 = Theme.key_chat_outAudioSeekbarSelected;
        L50:
            r14.setColors(r25, r33, r43, r52, RichMessageLayout.m4824$$Nest$mgetThemedColor(r08, r6));
            r8.save();
            r8.translate(this.seekBarX, this.seekBarY);
            this.seekBar.draw(r8);
            r8.restore();
            if (this.durationLayout == null) goto L54;
            r8.save();
            r8.translate(this.buttonX + AndroidUtilities.dp(54.0f), this.seekBarY + AndroidUtilities.dp(6.0f));
            this.durationLayout.draw(r8);
            r8.restore();
        L54:
            if (this.titleLayout == null) goto L56;
            r8.save();
            r8.translate(this.buttonX + AndroidUtilities.dp(54.0f), this.seekBarY - AndroidUtilities.dp(16.0f));
            this.titleLayout.draw(r8);
            r8.restore();
        L56:
            r8.restore();
            return;
        L49:
            r6 = Theme.key_chat_inAudioSeekbarSelected;
            goto L50
        L45:
            r5 = Theme.key_chat_inAudioSeekbarFill;
            goto L46
        L41:
            r42 = Theme.key_chat_inAudioSeekbarFill;
            goto L42
        L37:
            r32 = Theme.key_chat_inAudioCacheSeekbar;
            goto L38
        L33:
            r24 = Theme.key_chat_inAudioSeekbar;
            goto L34
        L29:
            r23 = Theme.key_chat_inFileProgress;
            goto L30
        L25:
            r4 = Theme.key_chat_inMediaIconSelected;
            goto L26
        L21:
            r3 = Theme.key_chat_inMediaIcon;
            goto L23
        L17:
            r22 = Theme.key_chat_inLoaderSelected;
            goto L19
        L13:
            r12 = Theme.key_chat_inLoader;
            goto L15
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onFailedDownload(String r1, boolean r2) {
            updateButtonState(true);
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onProgressDownload(String r3, long r4, long r6) {
            RadialProgress2 r32 = this.radialProgress;
            if (r6 > 0) goto L5;
            float r42 = 0.0f;
        L6:
            r32.setProgress(Math.min(1.0f, r42), true);
            if (this.buttonState == 3) goto L10;
            updateButtonState(true);
            return;
        L10:
            return;
        L5:
            r42 = r4 / r6;
            goto L6
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onSuccessDownload(String r3) {
            this.radialProgress.setProgress(1.0f, true);
            updateButtonState(true);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r7) {
            int r0 = r7.getActionMasked();
            float r1 = r7.getX() + this.root.padLeft;
            float r72 = r7.getY();
            if (this.seekBar.onTouch(r0, r1 - this.seekBarX, r72 - this.seekBarY) == false) goto L13;
            if (r0 != 0) goto L6;
            requestDisallowParentIntercept(true);
        L6:
            if (r0 == 1) goto L8;
            if (r0 == 3) goto L8;
        L9:
            View r6 = this.view;
            if (r6 == null) goto L12;
            r6.invalidate();
        L12:
            return true;
        L8:
            requestDisallowParentIntercept(false);
            goto L9
        L13:
            if (r0 == 0) goto L15;
            if (r0 == 1) goto L30;
            if (r0 != 3) goto L41;
            this.buttonPressed = false;
        L41:
            return this.buttonPressed;
        L30:
            if (this.buttonPressed == false) goto L41;
            this.buttonPressed = false;
            View r73 = this.view;
            if (r73 == null) goto L34;
            r73.playSoundEffect(0);
        L34:
            didPressedButton(true);
            View r62 = this.view;
            if (r62 == null) goto L37;
            r62.invalidate();
        L37:
            return true;
        L15:
            if (this.buttonState == (-1)) goto L41;
            if (r1 < this.buttonX) goto L41;
            if (r1 > (r0 + AndroidUtilities.dp(48.0f))) goto L41;
            if (r72 < this.buttonY) goto L41;
            if (r72 > (r0 + AndroidUtilities.dp(48.0f))) goto L41;
            this.buttonPressed = true;
            View r63 = this.view;
            if (r63 == null) goto L27;
            r63.invalidate();
        L27:
            return true;
        }

        public void updateButtonState(boolean r8) {
            int r0 = this.root.currentAccount;
            String r1 = FileLoader.getAttachFileName(this.currentDocument);
            int r4 = 1;
            if (this.currentDocument != null) goto L5;
            File r2 = null;
        L7:
            if (r2 != null) goto L9;
        L11:
            boolean r22 = false;
        L13:
            if (TextUtils.isEmpty(r1) == false) goto L16;
            this.radialProgress.setIcon(4, false, false);
            return;
        L16:
            if (r22 == false) goto L25;
            DownloadController.getInstance(r0).removeLoadingFileObserver(this);
            if (MediaController.getInstance().isPlayingMessage(this.currentMessageObject) == true) goto L20;
        L23:
            this.buttonState = 0;
        L24:
            this.radialProgress.setIcon(getIconForCurrentState(), false, r8);
        L41:
            updatePlayingMessageProgress();
            return;
        L20:
            if (MediaController.getInstance().isMessagePaused() == true) goto L23;
            this.buttonState = 1;
            goto L24
        L25:
            DownloadController.getInstance(r0).addLoadingFileObserver(r1, null, this);
            if (canStream() == true) goto L28;
            float r23 = 0.0f;
            if (FileLoader.getInstance(r0).isLoadingFile(r1) == true) goto L37;
            this.buttonState = 2;
            this.radialProgress.setProgress(0.0f, r8);
            this.radialProgress.setIcon(getIconForCurrentState(), false, r8);
            goto L41
        L37:
            this.buttonState = 3;
            Float r02 = ImageLoader.getInstance().getFileProgress(r1);
            RadialProgress2 r12 = this.radialProgress;
            if (r02 == null) goto L40;
            r23 = r02.floatValue();
        L40:
            r12.setProgress(r23, r8);
            this.radialProgress.setIcon(getIconForCurrentState(), true, r8);
            goto L41
        L28:
            if (MediaController.getInstance().isPlayingMessage(this.currentMessageObject) == true) goto L30;
        L32:
            r4 = 0;
        L33:
            this.buttonState = r4;
            this.radialProgress.setIcon(getIconForCurrentState(), false, r8);
            goto L41
        L30:
            if (MediaController.getInstance().isMessagePaused() == true) goto L32;
        L9:
            if (r2.exists() == false) goto L11;
            r22 = true;
            goto L13
        L5:
            r2 = FileLoader.getInstance(r0).getPathToAttach(this.currentDocument, true);
            goto L7
        }

        public void updatePlayingMessageProgress() {
            if (this.currentDocument != null) goto L5;
            return;
        L5:
            if (this.currentMessageObject != null) goto L8;
            return;
        L8:
            if (this.seekBar.isDragging() == true) goto L11;
            this.seekBar.setProgress(this.currentMessageObject.audioProgress);
        L11:
            if (MediaController.getInstance().isPlayingMessage(this.currentMessageObject) == false) goto L13;
            int r0 = this.currentMessageObject.audioProgressSec;
        L20:
            String r2 = AndroidUtilities.formatShortDuration(r0);
            String r02 = this.lastTimeString;
            if (r02 != null) goto L23;
        L24:
            this.lastTimeString = r2;
            this.audioTimePaint.setTextSize(AndroidUtilities.dp(16.0f));
            this.durationLayout = new StaticLayout(r2, this.audioTimePaint, (int) Math.ceil(this.audioTimePaint.measureText(r2)), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        L25:
            TextPaint r03 = this.audioTimePaint;
            RichMessageLayout r1 = this.root;
            if (r1.isOut() == false) goto L28;
            int r22 = Theme.key_chat_messageTextOut;
        L29:
            r03.setColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r1, r22));
            View r9 = this.view;
            if (r9 == null) goto L36;
            r9.invalidate();
            return;
        L36:
            return;
        L28:
            r22 = Theme.key_chat_messageTextIn;
            goto L29
        L23:
            if (r02.equals(r2) == true) goto L25;
        L13:
            r0 = 0;
            int r12 = 0;
        L15:
            if (r12 >= this.currentDocument.attributes.size()) goto L20;
            TLRPC.DocumentAttribute r23 = this.currentDocument.attributes.get(r12);
            if ((r23 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L18;
            r12 = r12 + 1;
            goto L15
        L18:
            r0 = (int) r23.duration;
            goto L20
        }
    }

    public static abstract class RichBlock implements MultiLayoutTypingAnimator.Block {
        public int accessibilityLabelResId;
        public int accessibilityParentLabelResId;
        private CheckBoxBase checkbox;
        private ButtonBounce checkboxBounce;
        private final RectF checkboxHit;
        private TLObject checkboxItem;
        private boolean checkboxPressed;
        private float checkboxY;
        public int currH;
        public boolean currVisible;
        public float currY;
        protected int layoutRow;
        protected int layoutX;
        protected int layoutY;
        public boolean listCheckbox;
        public boolean listChecked;
        public int listLevel;
        private int listMarkerWidth;
        public boolean listOrdered;
        public final int maxWidth;
        private StaticLayout numLayout;
        private int numLayoutLeft;
        private int numLayoutRight;
        private float numLayoutY;
        public final Rect padding;
        public RichDetailsBlock parentDetails;
        public int prevH;
        public boolean prevVisible;
        public float prevY;
        public final RichMessageLayout root;
        public MultiLayoutTypingAnimator typingAnimator;
        protected View view;

        public static /* synthetic */ void $r8$lambda$94GcODwgj897vHAGqsK6wGRmTO4(RichBlock r0, boolean r1) {
            r0.lambda$toggleCheckbox$1(r1);
        }

        public static /* synthetic */ int $r8$lambda$Eafq4HGhd5N7a_h3ExMSg505OHs(Spanned r0, TextSelectionHelper.ReplaceCopyTextSpannable r1, TextSelectionHelper.ReplaceCopyTextSpannable r2) {
            return r0.getSpanStart(r2) - r0.getSpanStart(r1);
        }

        public RichBlock(RichMessageLayout r2, Rect r3, int r4) {
            this.checkboxHit = new RectF();
            this.currVisible = true;
            this.prevVisible = true;
            this.root = r2;
            this.padding = new Rect(r3);
            this.maxWidth = (r4 - r3.left) - r3.right;
        }

        public static void appendText(SpannableStringBuilder r4, Text r5, Text[] r6) {
            if (r5 == null) goto L9;
            StaticLayout r0 = r5.layout;
            if (r0 == null) goto L9;
            if (TextUtils.isEmpty(r0.getText()) == true) goto L9;
            r4.append(withReplacements(r5.layout.getText()));
            return;
        L9:
            if (r6 == null) goto L25;
            int r52 = r6.length;
            int r02 = 0;
        L11:
            if (r02 >= r52) goto L31;
            Text r1 = r6[r02];
            if (r1 == null) goto L24;
            StaticLayout r2 = r1.layout;
            if (r2 == null) goto L24;
            if (TextUtils.isEmpty(r2.getText()) == true) goto L24;
            if (r4.length() > 0) goto L21;
        L23:
            r4.append(withReplacements(r1.layout.getText()));
            goto L24
        L21:
            if (r4.charAt(r4.length() - 1) == '\n') goto L23;
            r4.append('\n');
        L24:
            r02 = r02 + 1;
            goto L11
        L31:
            return;
        }

        private boolean canToggleCheckbox() {
            if (this.checkbox != null) goto L5;
            return false;
        L5:
            if (this.checkboxItem != null) goto L7;
            return false;
        L7:
            if (this.root.getCell() != null) goto L9;
            return false;
        L9:
            if (this.root.getDelegate() != null) goto L11;
            return false;
        L11:
            if (this.root.getDelegate().canToggleRichMessageCheckbox(this.root.getCell()) == false) goto L19;
            return true;
        L19:
            return false;
        }

        private int getCheckboxAccessibilityElementCount() {
            if (this.checkbox != null) goto L6;
            return 0;
        L6:
            return 1;
        }

        private boolean getCheckboxChecked() {
            TLObject r0 = this.checkboxItem;
            if ((r0 instanceof TL_iv.PageListItem) == false) goto L7;
            return ((TL_iv.PageListItem) r0).checked;
        L7:
            if ((r0 instanceof TL_iv.PageListOrderedItem) == true) goto L9;
            CheckBoxBase r2 = this.checkbox;
            if (r2 != null) goto L13;
            return false;
        L13:
            if (r2.isChecked() == false) goto L18;
            return true;
        L18:
            return false;
        L9:
            return ((TL_iv.PageListOrderedItem) r0).checked;
        }

        private void invalidateCell() {
            View r0 = this.root.view;
            if (r0 == null) goto L6;
            r0.invalidate();
            return;
        }

        private /* synthetic */ void lambda$toggleCheckbox$1(boolean r3) {
            setCheckboxChecked(!r3);
            View r0 = this.root.view;
            if (r0 == null) goto L5;
            this.checkbox.setParentView(r0);
        L5:
            this.checkbox.setChecked(!r3, true);
            invalidateCell();
        }

        private void setCheckboxChecked(boolean r2) {
            TLObject r1 = this.checkboxItem;
            if ((r1 instanceof TL_iv.PageListItem) == false) goto L7;
            ((TL_iv.PageListItem) r1).checked = r2;
            return;
        L7:
            if ((r1 instanceof TL_iv.PageListOrderedItem) == false) goto L10;
            ((TL_iv.PageListOrderedItem) r1).checked = r2;
            return;
        }

        private void toggleCheckbox() {
            if (canToggleCheckbox() == true) goto L6;
            return;
        L6:
            if (MessagesController.getInstance(this.root.currentAccount).richEditorAllowed() == true) goto L9;
            new PremiumFeatureBottomSheet(RichMessageLayout.m4820$$Nest$fgetcell(this.root).getContext(), 43, true, this.root.resourcesProvider).show();
            return;
        L9:
            boolean r0 = !getCheckboxChecked();
            setCheckboxChecked(r0);
            View r2 = this.root.view;
            if (r2 == null) goto L12;
            this.checkbox.setParentView(r2);
        L12:
            this.checkbox.setChecked(r0, true);
            invalidateCell();
            View r1 = this.root.view;
            if (r1 == null) goto L15;
            r1.performHapticFeedback(3, 2);
        L15:
            this.root.getDelegate().didToggleRichMessageCheckbox(this.root.getCell(), r0, new RichMessageLayout$RichBlock$$ExternalSyntheticLambda1(this, r0));
        }

        public static CharSequence withReplacements(CharSequence r8) {
            if ((r8 instanceof Spanned) == true) goto L5;
            return r8;
        L5:
            Spanned r0 = (Spanned) r8;
            int r3 = 0;
            TextSelectionHelper.ReplaceCopyTextSpannable[] r1 = (TextSelectionHelper.ReplaceCopyTextSpannable[]) r0.getSpans(0, r0.length(), TextSelectionHelper.ReplaceCopyTextSpannable.class);
            if (r1 != null) goto L8;
        L25:
            return r8;
        L8:
            if (r1.length == 0) goto L25;
            SpannableStringBuilder r2 = new SpannableStringBuilder(r8);
            Arrays.sort(r1, new RichMessageLayout$RichBlock$$ExternalSyntheticLambda0(r0));
            int r82 = r1.length;
        L11:
            if (r3 >= r82) goto L24;
            TextSelectionHelper.ReplaceCopyTextSpannable r4 = r1[r3];
            int r5 = r0.getSpanStart(r4);
            int r6 = r0.getSpanEnd(r4);
            if (r5 < 0) goto L23;
            if (r6 < 0) goto L23;
            if (r5 > r6) goto L23;
            if (r6 > r2.length()) goto L23;
            CharSequence r42 = r4.replacement;
            if (r42 != null) goto L22;
            r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        L22:
            r2.replace(r5, r6, r42);
        L23:
            r3 = r3 + 1;
            goto L11
        L24:
            return r2;
        }

        public void appendAccessibilityText(SpannableStringBuilder r1) {
        }

        public void attach(View r2) {
            View r0 = this.view;
            if (r0 != r2) goto L5;
            return;
        L5:
            if (r0 == null) goto L10;
            onDetachedFromWindow();
            CheckBoxBase r02 = this.checkbox;
            if (r02 == null) goto L9;
            r02.onDetachedFromWindow();
        L9:
            this.view = null;
        L10:
            this.view = r2;
            CheckBoxBase r03 = this.checkbox;
            if (r03 == null) goto L13;
            r03.setParentView(r2);
            this.checkbox.onAttachedToWindow();
        L13:
            onAttachedToWindow();
        }

        public boolean canDragHorizontally() {
            return false;
        }

        public void collectAnimatorBlocks(List<MultiLayoutTypingAnimator.Block> r1) {
            r1.add(this);
        }

        public void detach(View r2) {
            View r0 = this.view;
            if (r0 == null) goto L12;
            if (r0 == r2) goto L7;
            return;
        L7:
            onDetachedFromWindow();
            CheckBoxBase r22 = this.checkbox;
            if (r22 == null) goto L10;
            r22.onDetachedFromWindow();
        L10:
            this.view = null;
            return;
        }

        public void draw(Canvas r3) {
            draw(r3, Integer.MIN_VALUE, 0.0f);
        }

        public void draw(Canvas r11, int r12, float r13) {
            r11.save();
            Rect r0 = this.padding;
            r11.translate(r0.left, r0.top);
            boolean r02 = this.root.isRtl();
            int r1 = (this.root.getMinWidth() + this.root.padRight) - AndroidUtilities.dp(14.0f);
            Rect r2 = this.padding;
            float r14 = (r1 - r2.right) - r2.left;
            if (this.numLayout == null) goto L25;
            this.root.numTextPaint.setTextSize(AndroidUtilities.dp(SharedConfig.fontSize));
            RichMessageLayout r22 = this.root;
            TextPaint r5 = r22.numTextPaint;
            if (r22.isOut() == false) goto L7;
            int r6 = Theme.key_chat_messageTextOut;
        L8:
            r5.setColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r22, r6));
            r11.save();
            int r52 = 0;
            if (this.listOrdered == false) goto L11;
        L17:
            if (r02 == false) goto L22;
            float r23 = (AndroidUtilities.dp(6.0f) + r14) - this.numLayoutLeft;
            if (this.checkbox == null) goto L21;
            r52 = AndroidUtilities.dp(26.0f);
        L21:
            r11.translate(r23 + r52, this.numLayoutY);
            this.numLayout.draw(r11);
        L23:
            r11.restore();
            goto L25
        L22:
            r11.translate((AndroidUtilities.dp(6.0f) - this.listMarkerWidth) - this.numLayoutLeft, this.numLayoutY);
            this.numLayout.draw(r11);
            goto L23
        L11:
            if (this.listCheckbox == true) goto L17;
            float r24 = AndroidUtilities.dpf2(4.3f);
            if (r02 == false) goto L15;
            float r7 = ((AndroidUtilities.dp(18.0f) + r14) - AndroidUtilities.dpf2(5.66f)) - (r24 / 2.0f);
        L16:
            r11.drawCircle(r7, (this.numLayoutY + this.numLayout.getLineBaseline(0)) - (AndroidUtilities.dp(SharedConfig.fontSize) * 0.35f), r24 / 2.0f, this.root.numTextPaint);
            goto L23
        L15:
            r7 = (AndroidUtilities.dpf2(5.66f) + (r24 / 2.0f)) - AndroidUtilities.dp(18.0f);
            goto L16
        L7:
            r6 = Theme.key_chat_messageTextIn;
        L25:
            if (this.checkbox == null) goto L40;
            if (r02 == false) goto L28;
            int r03 = (int) (r14 + AndroidUtilities.dp(6.0f));
        L29:
            this.checkboxHit.set(r03 - AndroidUtilities.dp(6.0f), this.checkboxY - AndroidUtilities.dp(6.0f), (AndroidUtilities.dp(20.0f) + r03) + AndroidUtilities.dp(6.0f), (this.checkboxY + AndroidUtilities.dp(20.0f)) + AndroidUtilities.dp(6.0f));
            if (this.root.view != null) goto L32;
        L34:
            ButtonBounce r15 = this.checkboxBounce;
            if (r15 == null) goto L37;
            float r16 = r15.getScale(0.1f);
        L38:
            r11.save();
            r11.scale(r16, r16, AndroidUtilities.dp(10.0f) + r03, this.checkboxY + AndroidUtilities.dp(10.0f));
            this.checkbox.setBounds(r03, (int) this.checkboxY, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f));
            this.checkbox.draw(r11);
            r11.restore();
            goto L40
        L37:
            r16 = 1.0f;
            goto L38
        L32:
            if (this.checkbox.getParentView() != null) goto L34;
            this.checkbox.setParentView(this.root.view);
            goto L34
        L28:
            r03 = -AndroidUtilities.dp(26.0f);
        L40:
            if (r12 != Integer.MIN_VALUE) goto L42;
            onDraw(r11);
        L43:
            r11.restore();
            return;
        L42:
            onDrawFaded(r11, r12, r13);
            goto L43
        }

        public boolean drawOverlay(Canvas r2) {
            return drawOverlay(r2, null);
        }

        public boolean drawOverlay(Canvas r18, ColorFilter r19) {
            TextSelectionHelper.TextLayoutBlock[] r0 = getText();
            int r1 = 0;
            if (r0 != null) goto L5;
            return false;
        L5:
            int r2 = r0.length;
            boolean r3 = false;
        L6:
            if (r1 >= r2) goto L17;
            TextSelectionHelper.TextLayoutBlock r4 = r0[r1];
            if ((r4 instanceof Text) == false) goto L16;
            Text r42 = (Text) r4;
            AnimatedEmojiSpan.EmojiGroupedSpans r5 = r42.animatedEmojiStack;
            if (r5 == null) goto L16;
            if (r5.holders.isEmpty() == true) goto L16;
            r18.save();
            r18.translate(r42.x, r42.y - this.currY);
            AnimatedEmojiSpan.drawAnimatedEmojis(r18, r42.layout, r42.animatedEmojiStack, 0.0f, r42.spoilers, 0.0f, 0.0f, 0.0f, 1.0f, r19);
            r18.restore();
            r3 = true;
        L16:
            r1 = r1 + 1;
            goto L6
        L17:
            return r3;
        }

        public void drawWithTyping(Canvas r10) {
            MultiLayoutTypingAnimator r0 = this.typingAnimator;
            if (r0 != null) goto L5;
        L22:
            draw(r10);
            return;
        L5:
            if (r0.isRunning() == false) goto L22;
            if (r0.indexOf(this) < 0) goto L22;
            if (r0.needDraw(this) == true) goto L12;
            return;
        L12:
            if (r0.isFadeBlock(this) == false) goto L15;
            draw(r10, r0.getFadeLineIndex(this), r0.getFadeXPosition(this));
            return;
        L15:
            float r02 = r0.getBlockAlpha(this);
            if (r02 > 0.0f) goto L19;
            return;
        L19:
            if (r02 >= 1.0f) goto L22;
            Rect r1 = this.padding;
            int r102 = r10.saveLayerAlpha(0.0f, 0.0f, (r1.left + this.maxWidth) + r1.right, getHeight(), (int) (r02 * 255.0f));
            draw(r10);
            r10.restoreToCount(r102);
        }

        public boolean findLink(CharacterStyle r7, int r8, FoundLink r9) {
            TextSelectionHelper.TextLayoutBlock[] r0 = getText();
            if (r0 != null) goto L5;
            return false;
        L5:
            int r2 = r0.length;
            int r3 = 0;
        L6:
            if (r3 >= r2) goto L15;
            TextSelectionHelper.TextLayoutBlock r4 = r0[r3];
            if ((r4 instanceof Text) == false) goto L14;
            if (((Text) r4).fillFoundLink(r7, r9) == false) goto L14;
            Rect r6 = this.padding;
            r9.x = r6.left - r4.left;
            r9.y = r8 + r6.top;
            return true;
        L14:
            r3 = r3 + 1;
            goto L6
        L15:
            return false;
        }

        public boolean forcesTimeToNewLine() {
            if (getLastLineWidth() < getMinWidth()) goto L6;
            return true;
        L6:
            return false;
        }

        public final void getAccessibilityElementBounds(int r5, Rect r6) {
            if (this.checkbox == null) goto L6;
            if (r5 != 0) goto L6;
            int r52 = this.padding.left;
            float r0 = this.currY;
            r6.set(r52, (int) r0, this.maxWidth + r52, (int) (r0 + getHeight()));
        L7:
            RichMessageLayout r53 = this.root;
            int r02 = -r53.padLeft;
            int r54 = r53.getMinWidth() + this.root.padRight;
            int r4 = Math.min(r54, Math.max(r6.left, r02));
            r6.left = r4;
            r6.right = Math.max(r4, Math.min(r6.right, r54));
            return;
        L6:
            getBlockAccessibilityElementBounds(r5 - getCheckboxAccessibilityElementCount(), r6);
            goto L7
        }

        public final int getAccessibilityElementCount() {
            return getCheckboxAccessibilityElementCount() + getBlockAccessibilityElementCount();
        }

        public final CharSequence getAccessibilityElementStateDescription(int r2) {
            if (isAccessibilityElementCheckbox(r2) == false) goto L11;
            if (getCheckboxChecked() == false) goto L7;
            int r1 = R.string.AccDescrChecked;
        L9:
            return LocaleController.getString(r1);
        L7:
            r1 = R.string.AccDescrNotChecked;
            goto L9
        L11:
            return getBlockAccessibilityElementStateDescription(r2 - getCheckboxAccessibilityElementCount());
        }

        public final CharSequence getAccessibilityElementText(int r4) {
            if (this.checkbox == null) goto L17;
            if (r4 != 0) goto L17;
            SpannableStringBuilder r42 = new SpannableStringBuilder();
            CharSequence r0 = getAccessibilityListMarker();
            CharSequence r1 = getAccessibilityLabel();
            if (TextUtils.isEmpty(r0) == true) goto L9;
            r42.append(r0).append(' ');
        L9:
            if (TextUtils.isEmpty(r1) == true) goto L11;
            r42.append(r1).append(", ");
        L11:
            appendAccessibilityText(r42);
            if (r42.length() <= 0) goto L15;
            return r42;
        L15:
            return LocaleController.getString(R.string.AccDescrCheckbox);
        L17:
            return getBlockAccessibilityElementText(r4 - getCheckboxAccessibilityElementCount());
        }

        public CharSequence getAccessibilityLabel() {
            int r0 = this.accessibilityParentLabelResId;
            String r1 = null;
            if (r0 != 0) goto L5;
            String r02 = null;
        L6:
            int r3 = this.accessibilityLabelResId;
            if (r3 == 0) goto L11;
            r1 = LocaleController.getString(r3);
        L11:
            if (TextUtils.isEmpty(r02) == false) goto L14;
            return r1;
        L14:
            if (TextUtils.isEmpty(r1) == false) goto L17;
            return r02;
        L17:
            return TextUtils.concat(new CharSequence[]{r02, ", ", r1});
        L5:
            r02 = LocaleController.getString(r0);
            goto L6
        }

        public CharSequence getAccessibilityListMarker() {
            if (this.listOrdered == false) goto L8;
            StaticLayout r1 = this.numLayout;
            if (r1 != null) goto L7;
            return null;
        L7:
            return r1.getText();
        L8:
            return null;
        }

        public float getBackgroundScale() {
            return 1.0f;
        }

        public void getBlockAccessibilityElementBounds(int r4, Rect r5) {
            int r42 = this.padding.left;
            float r0 = this.currY;
            r5.set(r42, (int) r0, this.maxWidth + r42, (int) (r0 + getHeight()));
        }

        public int getBlockAccessibilityElementCount() {
            return 0;
        }

        public CharSequence getBlockAccessibilityElementStateDescription(int r1) {
            return null;
        }

        public CharSequence getBlockAccessibilityElementText(int r1) {
            return null;
        }

        public int getContentPaddingTop() {
            return 0;
        }

        public int getHeight() {
            return 0;
        }

        public int getLastLineWidth() {
            return getMinWidth();
        }

        @Override // org.telegram.ui.MultiLayoutTypingAnimator.Block
        public Layout getLayout() {
            return null;
        }

        public int getMinWidth() {
            Rect r0 = this.padding;
            return (r0.left + this.maxWidth) + r0.right;
        }

        @Override // org.telegram.ui.MultiLayoutTypingAnimator.Block
        public View getParentView() {
            return null;
        }

        public TextSelectionHelper.TextLayoutBlock[] getText() {
            return null;
        }

        public final boolean isAccessibilityElementCheckbox(int r1) {
            if (this.checkbox == null) goto L7;
            if (r1 != 0) goto L9;
            return true;
        L9:
            return false;
        L7:
            return false;
        }

        public final boolean isAccessibilityElementChecked(int r1) {
            if (isAccessibilityElementCheckbox(r1) == true) goto L5;
            return false;
        L5:
            if (getCheckboxChecked() == false) goto L10;
            return true;
        L10:
            return false;
        }

        public final boolean isAccessibilityElementClickable(int r1) {
            if (isAccessibilityElementCheckbox(r1) == true) goto L5;
            return true;
        L5:
            if (canToggleCheckbox() == true) goto L11;
            return false;
        L11:
            return true;
        }

        public final boolean isAccessibilityElementText(int r2) {
            if (isAccessibilityElementCheckbox(r2) == false) goto L5;
            return false;
        L5:
            if (isBlockAccessibilityElementText(r2 - getCheckboxAccessibilityElementCount()) == false) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isAttachedToWindow() {
            if (this.view == null) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isBlockAccessibilityElementText(int r1) {
            return false;
        }

        public boolean isHorizontallyDragging() {
            return false;
        }

        public boolean isInQuote() {
            if (this.root.quotes.isEmpty() == false) goto L5;
            return false;
        L5:
            int r0 = this.root.blocks.indexOf(this);
            if (r0 >= 0) goto L8;
            return false;
        L8:
            int r2 = 0;
        L10:
            if (r2 >= this.root.quotes.size()) goto L18;
            QuoteBackground r3 = this.root.quotes.get(r2);
            if (r0 < r3.startBlockIndex) goto L17;
            if (r0 > r3.endBlockIndex) goto L17;
            return true;
        L17:
            r2 = r2 + 1;
            goto L10
        L18:
            return false;
        }

        public boolean isPressingLink() {
            TextSelectionHelper.TextLayoutBlock[] r5 = getText();
            if (r5 != null) goto L5;
            return false;
        L5:
            int r1 = r5.length;
            int r2 = 0;
        L6:
            if (r2 >= r1) goto L14;
            TextSelectionHelper.TextLayoutBlock r3 = r5[r2];
            if ((r3 instanceof Text) == false) goto L13;
            if (((Text) r3).isPressingLink() == false) goto L13;
            return true;
        L13:
            r2 = r2 + 1;
            goto L6
        L14:
            return false;
        }

        public boolean isVisible() {
            RichDetailsBlock r0 = this.parentDetails;
            if (r0 != null) goto L7;
            return true;
        L7:
            if (r0.isOpen() == true) goto L11;
            return false;
        L11:
            return this.parentDetails.isVisible();
        }

        public final boolean onAccessibilityElementClick(int r2, View r3) {
            if (this.checkbox == null) goto L12;
            if (r2 != 0) goto L12;
            if (canToggleCheckbox() == true) goto L9;
            return false;
        L9:
            toggleCheckbox();
            return true;
        L12:
            return onBlockAccessibilityElementClick(r2 - getCheckboxAccessibilityElementCount(), r3);
        }

        public void onAttachedToWindow() {
        }

        public boolean onBlockAccessibilityElementClick(int r1, View r2) {
            return false;
        }

        public void onDetachedFromWindow() {
        }

        public void onDraw(Canvas r1) {
        }

        public void onDrawFaded(Canvas r1, int r2, float r3) {
            onDraw(r1);
        }

        public boolean onTouchEvent(MotionEvent r1) {
            return false;
        }

        public void placeTexts(int r5, int r6, int r7) {
            this.layoutX = r5;
            this.layoutY = r6;
            this.layoutRow = r7;
            TextSelectionHelper.TextLayoutBlock[] r4 = getText();
            if (r4 == null) goto L11;
            int r0 = r4.length;
            int r1 = 0;
        L6:
            if (r1 >= r0) goto L15;
            TextSelectionHelper.TextLayoutBlock r2 = r4[r1];
            if ((r2 instanceof Text) == false) goto L10;
            Text r22 = (Text) r2;
            r22.setX(r5 - r22.left);
            r22.setY(r6);
            r22.setRow(r7);
        L10:
            r1 = r1 + 1;
            goto L6
        L15:
            return;
        }

        public void requestDisallowParentIntercept(boolean r1) {
            View r0 = this.view;
            if (r0 == null) goto L8;
            ViewParent r02 = r0.getParent();
        L6:
            if (r02 == null) goto L10;
            r02.requestDisallowInterceptTouchEvent(r1);
            r02 = r02.getParent();
            goto L6
        L10:
            return;
        }

        public void setCheckbox(boolean r2) {
            setCheckbox(r2, null);
        }

        public void setCheckbox(boolean r4, TLObject r5) {
            this.checkboxItem = r5;
            if (this.checkbox != null) goto L5;
            CheckBoxBase r52 = new CheckBoxBase(null, 20, this.root.resourcesProvider);
            this.checkbox = r52;
            r52.setColor(Theme.key_telegram_color, Theme.key_dialogCheckboxSquareDisabled, Theme.key_checkboxCheck);
            this.checkbox.setBackgroundType(10);
            this.checkbox.setDrawUnchecked(true);
            this.checkbox.setCustomRadius(AndroidUtilities.dp(5.0f));
        L5:
            this.checkbox.setChecked(r4, false);
            updateListMarkerY();
        }

        public void setListMarkerWidth(int r1) {
            this.listMarkerWidth = r1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void setNum(String r14) {
            this.root.numTextPaint.setTextSize(AndroidUtilities.dp(SharedConfig.fontSize));
            Layout r0 = getLayout();
            if (r0 != null) goto L5;
            CharSequence r02 = null;
        L6:
            int r2 = 0;
            if (TextUtils.isEmpty(r14) == false) goto L9;
        L18:
            String r6 = r14;
        L19:
            int r142 = this.listMarkerWidth;
            if (r142 <= 0) goto L22;
        L21:
            int r8 = r142;
            RichMessageLayout r143 = this.root;
            TextPaint r7 = r143.numTextPaint;
            if (r143.isRtl() == false) goto L31;
            Layout.Alignment r144 = Layout.Alignment.ALIGN_NORMAL;
        L32:
            this.numLayout = new StaticLayout(r6, r7, r8, r144, 1.0f, 0.0f, false);
            this.numLayoutLeft = AndroidUtilities.dp(RichMessageLayout.m4823$$Nest$fgetfontSize(this.root) + 4);
            this.numLayoutRight = 0;
        L34:
            if (r2 >= this.numLayout.getLineCount()) goto L36;
            this.numLayoutLeft = Math.min(this.numLayoutLeft, (int) this.numLayout.getLineLeft(r2));
            this.numLayoutRight = Math.max(this.numLayoutRight, (int) this.numLayout.getLineRight(r2));
            r2 = r2 + 1;
            goto L34
        L36:
            updateListMarkerY();
            return;
        L31:
            r144 = Layout.Alignment.ALIGN_OPPOSITE;
            goto L32
        L22:
            boolean r145 = this.root.isRtl();
            Rect r03 = this.padding;
            if (r145 == false) goto L25;
            int r146 = r03.right;
        L26:
            r142 = Math.max(r146, AndroidUtilities.dp(RichMessageLayout.m4823$$Nest$fgetfontSize(this.root) + 4));
            goto L21
        L25:
            r146 = r03.left;
            goto L26
        L9:
            if ((r02 instanceof Spanned) == false) goto L18;
            if (r02.length() <= 0) goto L18;
            StyleSpan[] r04 = (StyleSpan[]) ((Spanned) r02).getSpans(0, 1, StyleSpan.class);
            int r1 = r04.length;
            int r4 = 0;
        L13:
            if (r4 >= r1) goto L18;
            if (TLObject.hasFlag(r04[r4].flags, 16) == true) goto L16;
            r4 = r4 + 1;
            goto L13
        L16:
            SpannableString r05 = new SpannableString(r14);
            r05.setSpan(new android.text.style.StyleSpan(1), 0, r05.length(), 33);
            r6 = r05;
            goto L19
        L5:
            r02 = r0.getText();
            goto L6
        }

        public void snapshot() {
            this.prevY = this.currY;
            this.prevH = this.currH;
            this.prevVisible = this.currVisible;
        }

        public boolean touchEvent(MotionEvent r6) {
            Rect r0 = this.padding;
            r6.offsetLocation(-r0.left, -r0.top);
        L15:
            th = move-exception;
            Rect r5 = this.padding;
            r6.offsetLocation(r5.left, r5.top);
            throw th;
        L4:
            if (this.checkbox == null) goto L44;
            int r02 = r6.getActionMasked();     // Catch: Throwable -> L15
            boolean r1 = this.checkboxHit.contains(r6.getX(), r6.getY());     // Catch: Throwable -> L15
            if (r02 != 0) goto L24;
            if (r1 == false) goto L44;
            if (canToggleCheckbox() == false) goto L44;
            this.checkboxPressed = true;     // Catch: Throwable -> L15
            if (this.checkboxBounce != null) goto L17;
            View r03 = this.root.view;     // Catch: Throwable -> L15
            if (r03 == null) goto L17;
            this.checkboxBounce = new ButtonBounce(r03);     // Catch: Throwable -> L15
        L17:
            ButtonBounce r04 = this.checkboxBounce;     // Catch: Throwable -> L15
            if (r04 == null) goto L20;
            r04.setPressed(true);     // Catch: Throwable -> L15
        L20:
            invalidateCell();     // Catch: Throwable -> L15
        L21:
            Rect r52 = this.padding;
            r6.offsetLocation(r52.left, r52.top);
            return true;
        L24:
            if (this.checkboxPressed == false) goto L44;
            if (r02 != 2) goto L31;
            if (r1 == true) goto L21;
            this.checkboxPressed = false;     // Catch: Throwable -> L15
            ButtonBounce r05 = this.checkboxBounce;     // Catch: Throwable -> L15
            if (r05 == null) goto L21;
            r05.setPressed(false);     // Catch: Throwable -> L15
            goto L21
        L31:
            if (r02 != 1) goto L33;
        L34:
            if (r02 != 1) goto L37;
            if (r1 == false) goto L37;
            boolean r06 = true;
        L38:
            this.checkboxPressed = false;     // Catch: Throwable -> L15
            ButtonBounce r12 = this.checkboxBounce;     // Catch: Throwable -> L15
            if (r12 == null) goto L41;
            r12.setPressed(false);     // Catch: Throwable -> L15
        L41:
            if (r06 == false) goto L43;
            toggleCheckbox();     // Catch: Throwable -> L15
        L43:
            invalidateCell();     // Catch: Throwable -> L15
        L37:
            r06 = false;
            goto L38
        L33:
            if (r02 == 3) goto L34;
        L44:
            boolean r07 = onTouchEvent(r6);     // Catch: Throwable -> L15
            Rect r53 = this.padding;
            r6.offsetLocation(r53.left, r53.top);
            return r07;
        }

        public final void updateListMarkerY() {
            if (this.numLayout == null) goto L13;
            if (getLayout() != null) goto L7;
        L11:
            int r0 = AndroidUtilities.dp(RichMessageLayout.m4823$$Nest$fgetfontSize(this.root) + 14);
            int r3 = getHeight();
            Rect r4 = this.padding;
            this.numLayoutY = (Math.min(r0, (r3 - r4.top) - r4.bottom) - this.numLayout.getHeight()) / 2.0f;
            goto L13
        L7:
            if (getLayout().getLineCount() <= 0) goto L11;
            if (this.numLayout.getLineCount() <= 0) goto L11;
            this.numLayoutY = (getContentPaddingTop() + getLayout().getLineBaseline(0)) - this.numLayout.getLineBaseline(0);
        L13:
            if (this.checkbox != null) goto L15;
            return;
        L15:
            if (getLayout() != null) goto L17;
        L20:
            int r02 = AndroidUtilities.dp(RichMessageLayout.m4823$$Nest$fgetfontSize(this.root) + 14);
            int r2 = getHeight();
            Rect r42 = this.padding;
            this.checkboxY = (Math.min(r02, (r2 - r42.top) - r42.bottom) - AndroidUtilities.dp(20.0f)) / 2.0f;
            return;
        L17:
            if (getLayout().getLineCount() <= 0) goto L20;
            this.checkboxY = (getContentPaddingTop() + getLayout().getLineBaseline(0)) - (AndroidUtilities.dp(20.0f) * 0.7f);
        }
    }

    public static class RichButton {
        private static final float ICON_OFFSET_X = 12.33f;
        private static final float ICON_OFFSET_Y = 11.66f;
        private static final float ICON_OFFSET_Y_INLINE = 9.33f;
        public static final int INLINE_PADDING_HORIZONTAL = 7;
        private static final int MIN_PADDING = 8;
        private static final int PADDING = 20;
        private static final int PADDING_WITH_ICON = 26;
        private static final float PRESS_SCALE = 0.04f;
        private static final float PRESS_SCALE_INLINE = 0.09f;
        private static final Xfermode SRC_OUT = null;
        private static final Paint SRC_OUT_PAINT = null;
        public int backgroundColor;
        public final Paint backgroundPaint;
        public int backgroundPressedColor;
        private final ForegroundColorSpanThemable colorSpan;
        private final boolean emojiFirst;
        private final boolean emojiLast;
        private Drawable iconDrawable;
        private final boolean inline;
        public final Runnable invalidateRunnable;
        public final boolean isDisabled;
        private int lastLinkColorFilterColor;
        private final RichMessageLayout layout;
        private final boolean link;
        private ColorFilter linkColorFilter;
        private boolean loading;
        public LoadingDrawable loadingDrawable;
        private final RectF loadingRect;
        private final int maxWidth;
        private boolean needSaveLayer;
        private final boolean out;
        public final TL_keyboard.PageButton pageButton;
        public ValueAnimator pressAnimator;
        public float pressT;
        public boolean pressed;
        public final TL_keyboard.RichButtonStyle style;
        private final Theme.IvButtonColors styleKeys;
        public final Text text;
        public int textColor;
        private ColorFilter textColorFilter;
        public int textColorKey;
        private final RectF textFadeRect;
        public final TL_keyboard.InlineButtonType type;
        public int width;
        public int x;

        /* renamed from: org.telegram.messenger.RichMessageLayout$RichButton$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ RichButton this$0;

            public AnonymousClass1(RichButton r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                super.onAnimationEnd(r1);
                this.this$0.pressAnimator = null;
            }
        }

        public static /* synthetic */ void $r8$lambda$ebSeZRyUTf31EtrjqvOj67Iq4YI(RichButton r0, ValueAnimator r1) {
            r0.lambda$setPressed$0(r1);
        }

        /* renamed from: -$$Nest$fgetlink, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4828$$Nest$fgetlink(RichButton r0) {
            return r0.link;
        }

        static {
            PorterDuffXfermode r0 = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
            SRC_OUT = r0;
            Paint r1 = new Paint();
            SRC_OUT_PAINT = r1;
            r1.setXfermode(r0);
        }

        public RichButton(RichMessageLayout r6, int r7, CharSequence r8, TL_keyboard.PageButton r9, TL_keyboard.InlineButtonType r10, TL_keyboard.RichButtonStyle r11, boolean r12, boolean r13, boolean r14, boolean r15, boolean r16, boolean r17, Boolean r18, Runnable r19) {
            this.backgroundPaint = new Paint(1);
            this.loadingRect = new RectF();
            this.textFadeRect = new RectF();
            this.layout = r6;
            this.maxWidth = Math.max(1, r7);
            this.invalidateRunnable = r19;
            this.pageButton = r9;
            this.type = r10;
            this.style = r11;
            this.isDisabled = r12;
            this.emojiFirst = r14;
            this.emojiLast = r15;
            this.inline = r17;
            this.link = r16;
            if (r18 == null) goto L5;
            boolean r72 = r18.booleanValue();
        L6:
            this.out = r72;
            Theme.IvButtonColors r92 = Theme.IvButtonColors.of(r11);
            if (r92 != Theme.IvButtonColors.DEFAULT) goto L10;
            if (r17 == false) goto L10;
            r92 = Theme.IvButtonColors.DEFAULT_IN_TEXT;
        L10:
            this.styleKeys = r92;
            ForegroundColorSpanThemable r112 = new ForegroundColorSpanThemable(r92.getTextKey(r72));
            this.colorSpan = r112;
            if (r12 == false) goto L13;
            float r73 = 0.5f;
        L14:
            r112.setAlpha(r73);
            SpannableStringBuilder r74 = new SpannableStringBuilder(r8);
            int r93 = 0;
            if (r16 == false) goto L17;
            r74.setSpan(new URLSpanNoUnderline(_UrlKt.FRAGMENT_ENCODE_SET), 0, r74.length(), 33);
        L18:
            AndroidUtilities.replaceNewLines(r74);
            Math.max(1, ((int) Math.ceil(Layout.getDesiredWidth(r74, r6.textPaint))) + AndroidUtilities.dp(2.0f));
            Text r82 = new Text(r6, r74, AppDataDirGuesser.PER_USER_RANGE, Layout.Alignment.ALIGN_CENTER);
            this.text = r82;
            r82.setDrawAtOrigin(true);
            r82.doNotInvalidateEmojiInParent = true;
            if (r13 == false) goto L21;
            r93 = getButtonIcon(r10);
        L21:
            if (r93 == 0) goto L23;
            this.iconDrawable = ApplicationLoader.applicationContext.getResources().getDrawable(r93).mutate();
        L23:
            this.width = getPreferredWidth();
            updateColors();
            return;
        L17:
            r74.setSpan(r112, 0, r74.length(), 33);
            goto L18
        L13:
            r73 = 1.0f;
            goto L14
        L5:
            r72 = r6.isOut();
            goto L6
        }

        public RichButton(RichMessageLayout r16, int r17, TL_keyboard.PageButton r18, Runnable r19) {
            this(r16, r17, r16.formatText(r18.text, RichMessageLayout.setBlockFlags(16, 13)), r18, r18.type, r18.style, TLKeyboardHelper.isType(r18, TL_keyboard.TL_inlineButtonTypeDisabled.class), true, false, false, false, false, null, r19);
        }

        private void drawLoading(Canvas r7) {
            if (this.pageButton != null) goto L5;
        L9:
            boolean r0 = false;
        L10:
            setLoading(r0);
            LoadingDrawable r1 = this.loadingDrawable;
            if (r1 == null) goto L17;
            if (r0 == false) goto L14;
        L15:
            float r02 = this.loadingDrawable.strokePaint.getStrokeWidth();
            this.loadingRect.set(0.0f, 0.0f, this.width, getHeight());
            float r03 = r02 / 2.0f;
            this.loadingRect.inset(r03, r03);
            this.loadingDrawable.setRadii((getHeight() / 2.0f) - r03);
            this.loadingDrawable.setBounds(this.loadingRect);
            this.loadingDrawable.setColors(Theme.multAlpha(this.textColor, 0.07f), Theme.multAlpha(this.textColor, 0.175f), Theme.multAlpha(this.textColor, 0.175f), Theme.multAlpha(this.textColor, 0.42f));
            this.loadingDrawable.setAlpha(255);
            this.loadingDrawable.draw(r7);
            this.invalidateRunnable.run();
            return;
        L14:
            if (r1.isDisappearing() == true) goto L15;
            return;
        L17:
            return;
        L5:
            if (RichMessageLayout.m4820$$Nest$fgetcell(this.layout) == null) goto L9;
            if (RichMessageLayout.m4820$$Nest$fgetcell(this.layout).drawButtonProgress(this.pageButton) == false) goto L9;
            r0 = true;
            goto L10
        }

        private static int getButtonIcon(TL_keyboard.InlineButtonType r1) {
            if ((r1 instanceof TL_keyboard.TL_inlineButtonTypeCopy) == false) goto L7;
            return R.drawable.mini_inline_copy_16;
        L7:
            if ((r1 instanceof TL_keyboard.TL_inlineButtonTypeUrlAuth) == false) goto L11;
            return R.drawable.mini_inline_arrow_16;
        L11:
            if ((r1 instanceof TL_keyboard.TL_inlineButtonTypeUrl) == false) goto L19;
            if (LinkManager.isWebAppLink(((TL_keyboard.TL_inlineButtonTypeUrl) r1).url) == false) goto L17;
            return R.drawable.bot_webview;
        L17:
            return R.drawable.mini_inline_arrow_16;
        L19:
            if ((r1 instanceof TL_keyboard.TL_inlineButtonTypeWebView) == false) goto L23;
            return R.drawable.bot_webview;
        L23:
            if ((r1 instanceof TL_keyboard.TL_inlineButtonTypeSwitchInline) == false) goto L27;
            return R.drawable.mini_inline_switch_16;
        L27:
            if ((r1 instanceof TL_keyboard.TL_inlineButtonTypeBuy) == false) goto L31;
            return R.drawable.bot_card;
        L31:
            if ((r1 instanceof TL_keyboard.TL_inlineButtonTypeUserProfile) == true) goto L33;
            return 0;
        L33:
            return R.drawable.mini_inline_profile_16;
        }

        private int getTextAvailableWidth() {
            return Math.max(1, getTextViewportRight() - getTextViewportLeft());
        }

        private int getTextViewportLeft() {
            if (this.link == false) goto L7;
            return 0;
        L7:
            if (this.inline == false) goto L11;
            return getPaddingLeft();
        L11:
            return AndroidUtilities.dp(8.0f);
        }

        private int getTextViewportRight() {
            if (this.link == true) goto L5;
            boolean r0 = this.inline;
            int r1 = this.width;
            if (r0 == false) goto L11;
            int r2 = getPaddingRight();
        L10:
            return r1 - r2;
        L11:
            r2 = Math.max(AndroidUtilities.dp(8.0f), getIconReserve());
            goto L10
        L5:
            return this.width;
        }

        private /* synthetic */ void lambda$setPressed$0(ValueAnimator r1) {
            this.pressT = ((Float) r1.getAnimatedValue()).floatValue();
            this.invalidateRunnable.run();
        }

        public void attach(View r1) {
            this.text.attach(r1);
        }

        public boolean contains(float r3) {
            if (r3 >= this.x) goto L5;
            return false;
        L5:
            if (r3 > (r0 + this.width)) goto L10;
            return true;
        L10:
            return false;
        }

        public void detach(View r1) {
            this.text.detach(r1);
        }

        public void draw(Canvas r19) {
            float r2 = getPressScale();
            r19.save();
            if (r2 == 1.0f) goto L5;
            r19.scale(r2, r2, this.width / 2.0f, getHeight() / 2.0f);
        L5:
            boolean r22 = this.link;
            boolean r9 = false;
            if (this.needSaveLayer == false) goto L9;
            if (r22 == true) goto L9;
            boolean r13 = true;
        L10:
            if (r13 == false) goto L12;
            r19.saveLayer(0.0f, 0.0f, this.width, getHeight(), null);
        L20:
            r19.save();
            int r7 = getTextViewportLeft();
            int r14 = getTextViewportRight();
            if (getTextWidth() <= getTextAvailableWidth()) goto L23;
            boolean r15 = true;
        L25:
            if (r15 == false) goto L27;
            Canvas r1 = r19;
            r1.saveLayer(r7, -AndroidUtilities.dp(10.0f), r14, getHeight() + AndroidUtilities.dp(10.0f), null);
        L28:
            r1.clipRect(r7, -AndroidUtilities.dp(10.0f), r14, getHeight() + AndroidUtilities.dp(10.0f));
            r1.save();
            int r23 = this.text.getBaseline();
            if (this.text.getEmojiOnlyCount() <= 0) goto L31;
            r9 = true;
        L31:
            if (r9 == false) goto L33;
            r1.translate(getTextX() - this.text.left, (getHeight() - this.text.getHeight()) / 2.0f);
        L39:
            this.text.draw(r1);
            if (this.layout.isOverlayActive() == false) goto L56;
            if (r9 == false) goto L44;
            float r24 = 0.0f;
        L47:
            r1.translate(0.0f, AndroidUtilities.dp(r24));
            boolean r25 = this.link;
            Text r4 = this.text;
            if (r25 == false) goto L55;
            int r26 = r4.layout.getPaint().linkColor;
            if (this.lastLinkColorFilterColor == r26) goto L52;
        L53:
            this.lastLinkColorFilterColor = r26;
            this.linkColorFilter = new PorterDuffColorFilter(r26, PorterDuff.Mode.SRC_IN);
        L54:
            Text r27 = this.text;
            float r17 = 2.0f;
            float r12 = 0.0f;
            AnimatedEmojiSpan.drawAnimatedEmojis(r1, r27.layout, r27.animatedEmojiStack, 0.0f, r27.spoilers, 0.0f, 0.0f, 0.0f, 1.0f, this.linkColorFilter);
            r1 = r19;
        L57:
            r1.restore();
            if (r15 == false) goto L60;
            this.textFadeRect.set(r14 - AndroidUtilities.dp(8.0f), r12, r14, getHeight());
            this.layout.clip.draw(r1, this.textFadeRect, 2, 1.0f);
            r1.restore();
        L60:
            r1.restore();
            Drawable r28 = this.iconDrawable;
            if (r28 == null) goto L67;
            float r3 = this.width - AndroidUtilities.dp(ICON_OFFSET_X);
            if (this.inline == false) goto L65;
            float r42 = ICON_OFFSET_Y_INLINE;
        L66:
            DrawableUtils.setBounds(r28, r3, AndroidUtilities.dp(r42), 17);
            this.iconDrawable.draw(r1);
            goto L67
        L65:
            r42 = ICON_OFFSET_Y;
        L67:
            if (r13 == false) goto L73;
            Paint r8 = SRC_OUT_PAINT;
            if (this.pressed == false) goto L71;
            int r29 = this.backgroundPressedColor;
        L72:
            r8.setColor(r29);
            r1.drawRoundRect(0.0f, 0.0f, this.width, getHeight(), getHeight() / r17, getHeight() / r17, r8);
            r19.restore();
            drawLoading(r19);
            goto L73
        L71:
            r29 = this.backgroundColor;
        L73:
            r19.restore();
            return;
        L52:
            if (this.linkColorFilter != null) goto L54;
        L55:
            r17 = 2.0f;
            r12 = 0.0f;
            r1 = r19;
            AnimatedEmojiSpan.drawAnimatedEmojis(r1, r4.layout, r4.animatedEmojiStack, 0.0f, r4.spoilers, 0.0f, 0.0f, 0.0f, 1.0f, this.textColorFilter);
            goto L57
        L44:
            if (this.link == false) goto L46;
            r24 = 0.66f;
            goto L47
        L46:
            r24 = 2.0f;
            goto L47
        L56:
            r17 = 2.0f;
            r12 = 0.0f;
            goto L57
        L33:
            if (r23 > 0) goto L35;
            r1.translate(getTextX() - this.text.left, ((getHeight() - this.text.getHeight()) / 2.0f) - AndroidUtilities.dp(1.0f));
            goto L39
        L35:
            if (this.inline == false) goto L37;
            int r32 = (getHeight() * 43) / 56;
            r1.translate(getTextX() - this.text.left, (-r23) + r32);
            goto L39
        L37:
            int r33 = (getHeight() * 65) / 102;
            r1.translate(getTextX() - this.text.left, (-r23) + r33);
            goto L39
        L27:
            r1 = r19;
            goto L28
        L23:
            r15 = false;
            goto L25
        L12:
            Paint r16 = this.backgroundPaint;
            if (this.pressed == false) goto L15;
            int r34 = this.backgroundPressedColor;
        L16:
            r16.setColor(r34);
            if (r22 == true) goto L19;
            r19.drawRoundRect(0.0f, 0.0f, this.width, getHeight(), getHeight() / 2.0f, getHeight() / 2.0f, this.backgroundPaint);
        L19:
            drawLoading(r19);
            goto L20
        L15:
            r34 = this.backgroundColor;
        L9:
            r13 = false;
            goto L10
        }

        public int getHeight() {
            boolean r0 = this.inline;
            RichMessageLayout r1 = this.layout;
            if (r0 == false) goto L7;
            float r12 = RichMessageLayout.m4823$$Nest$fgetfontSize(r1) * 1.166666f;
        L6:
            return AndroidUtilities.dp(r12);
        L7:
            r12 = RichMessageLayout.m4823$$Nest$fgetfontSize(r1) + 18;
            goto L6
        }

        public int getIconReserve() {
            if (this.iconDrawable != null) goto L5;
            return 0;
        L5:
            return AndroidUtilities.dp(ICON_OFFSET_X) + (AndroidUtilities.dp(ICON_OFFSET_Y) / 2);
        }

        public int getMinWidth() {
            return Math.min(getPreferredWidth(), getHeight() + getIconReserve());
        }

        public int getPaddingLeft() {
            if (this.link == false) goto L7;
            return 0;
        L7:
            if (this.emojiFirst == false) goto L13;
            if (this.inline == false) goto L13;
            return AndroidUtilities.dp(4.0f);
        L13:
            if (this.inline == false) goto L17;
            return AndroidUtilities.dp(7.0f);
        L17:
            if (this.iconDrawable == null) goto L19;
            float r1 = 26.0f;
        L21:
            return AndroidUtilities.dp(r1);
        L19:
            r1 = 20.0f;
            goto L21
        }

        public int getPaddingRight() {
            int r1 = 0;
            if (this.link == false) goto L6;
            return 0;
        L6:
            if (this.emojiLast == true) goto L8;
        L14:
            boolean r0 = this.inline;
            Drawable r3 = this.iconDrawable;
            if (r0 == false) goto L20;
            if (r3 == null) goto L19;
            r1 = 14;
        L19:
            return AndroidUtilities.dp(r1 + 7);
        L20:
            if (r3 == null) goto L22;
            float r32 = 26.0f;
        L24:
            return AndroidUtilities.dp(r32);
        L22:
            r32 = 20.0f;
            goto L24
        L8:
            if (this.inline == false) goto L14;
            if (this.iconDrawable == null) goto L13;
            r1 = 14;
        L13:
            return AndroidUtilities.dp(r1 + 4);
        }

        public int getPreferredWidth() {
            return Math.min(this.maxWidth, (getTextWidth() + getPaddingLeft()) + getPaddingRight());
        }

        public float getPressScale() {
            if (this.pressed == false) goto L8;
            float r0 = this.pressT;
            if (r0 == 1.0f) goto L8;
            float r02 = r0 + (Math.min(40.0f, 1000.0f / AndroidUtilities.screenRefreshRate) / 100.0f);
            this.pressT = r02;
            this.pressT = Utilities.clamp(r02, 1.0f, 0.0f);
            this.invalidateRunnable.run();
        L8:
            if (this.inline == false) goto L10;
            float r03 = PRESS_SCALE_INLINE;
        L12:
            return (1.0f - r03) + (r03 * (1.0f - this.pressT));
        L10:
            r03 = PRESS_SCALE;
            goto L12
        }

        public int getTextWidth() {
            Text r1 = this.text;
            return r1.right - r1.left;
        }

        public float getTextX() {
            if (this.inline == false) goto L7;
            return getPaddingLeft();
        L7:
            if (getTextWidth() > getTextAvailableWidth()) goto L9;
            Drawable r0 = this.iconDrawable;
            int r1 = this.width;
            if (r0 != null) goto L15;
            return (r1 - getTextWidth()) / 2.0f;
        L15:
            return Math.max(Math.min((r1 - getTextWidth()) / 2.0f, (this.width - getIconReserve()) - getTextWidth()), AndroidUtilities.dp(8.0f));
        L9:
            return getTextViewportLeft();
        }

        public boolean isLoading() {
            return this.loading;
        }

        public void setLoading(boolean r2) {
            if (this.loading != r2) goto L5;
            return;
        L5:
            this.loading = r2;
            LoadingDrawable r0 = this.loadingDrawable;
            if (r2 == false) goto L10;
            if (r0 != null) goto L9;
            LoadingDrawable r22 = new LoadingDrawable();
            this.loadingDrawable = r22;
            r22.setAppearByGradient(true);
            this.loadingDrawable.strokePaint.setStrokeWidth(AndroidUtilities.dpf2(1.25f));
        L12:
            this.invalidateRunnable.run();
            return;
        L9:
            r0.reset();
            this.loadingDrawable.resetDisappear();
            goto L12
        L10:
            if (r0 == null) goto L12;
            r0.disappear();
            goto L12
        }

        public void setPressed(boolean r4) {
            if (this.pressed == r4) goto L25;
            this.pressed = r4;
            this.invalidateRunnable.run();
            if (r4 == false) goto L11;
            ValueAnimator r42 = this.pressAnimator;
            if (r42 == null) goto L23;
            r42.removeAllListeners();
            this.pressAnimator.cancel();
            this.pressAnimator = null;
            return;
        L23:
            return;
        L11:
            float r43 = this.pressT;
            if (r43 == 0.0f) goto L24;
            ValueAnimator r44 = ValueAnimator.ofFloat(new float[]{r43, 0.0f});
            this.pressAnimator = r44;
            r44.addUpdateListener(new RichMessageLayout$RichButton$$ExternalSyntheticLambda0(this));
            this.pressAnimator.addListener(new AnonymousClass1(this));
            ValueAnimator r45 = this.pressAnimator;
            if (this.inline == false) goto L16;
            float r1 = 3.5f;
        L17:
            r45.setInterpolator(new OvershootInterpolator(r1));
            ValueAnimator r46 = this.pressAnimator;
            if (this.inline == false) goto L20;
            long r0 = 420;
        L21:
            r46.setDuration(r0);
            this.pressAnimator.start();
            return;
        L20:
            r0 = 350;
            goto L21
        L16:
            r1 = 2.0f;
            goto L17
        L24:
            return;
        }

        public void setTextColorKey(int r4) {
            this.textColorKey = r4;
            int r0 = RichMessageLayout.m4824$$Nest$mgetThemedColor(this.layout, r4);
            if (this.isDisabled == false) goto L6;
            r0 = Theme.multAlpha(r0, 0.5f);
        L6:
            if (r0 == this.textColor) goto L8;
        L9:
            this.textColor = r0;
            this.textColorFilter = new PorterDuffColorFilter(this.textColor, PorterDuff.Mode.SRC_IN);
        L10:
            this.colorSpan.setColorKey(r4);
            this.invalidateRunnable.run();
            Drawable r42 = this.iconDrawable;
            if (r42 == null) goto L14;
            r42.setColorFilter(new PorterDuffColorFilter(this.textColor, PorterDuff.Mode.MULTIPLY));
            return;
        L14:
            return;
        L8:
            if (this.textColorFilter != null) goto L10;
            goto L9
        }

        public void updateColors() {
            Theme.ResourcesProvider r0 = this.layout.resourcesProvider;
            if (r0 == null) goto L5;
            boolean r02 = r0.isDark();
        L7:
            if (this.out == false) goto L14;
            if (r02 == false) goto L14;
            int r03 = RichMessageLayout.m4824$$Nest$mgetThemedColor(this.layout, Theme.key_chat_outBubbleGradient1);
            int r1 = RichMessageLayout.m4824$$Nest$mgetThemedColor(this.layout, Theme.key_chat_outBubbleGradient2);
            int r4 = RichMessageLayout.m4824$$Nest$mgetThemedColor(this.layout, Theme.key_chat_outBubbleGradient3);
            if (r03 != 0) goto L13;
            if (r1 != 0) goto L13;
            if (r4 == 0) goto L14;
        L13:
            boolean r04 = true;
        L15:
            Theme.IvButtonColors r12 = this.styleKeys;
            if (r04 == true) goto L18;
        L20:
            int r42 = r12.getBackgroundKey(this.out);
            int r5 = r12.getBackgroundPressedKey(this.out);
            int r6 = r12.getTextKey(this.out);
            int r7 = RichMessageLayout.m4824$$Nest$mgetThemedColor(this.layout, r6);
            this.needSaveLayer = false;
            if (r04 == true) goto L23;
        L25:
            float r2 = 0.1f;
            if (Theme.hasThemeKey(r42) == false) goto L28;
        L34:
            this.backgroundColor = RichMessageLayout.m4824$$Nest$mgetThemedColor(this.layout, r42);
            if (Theme.hasThemeKey(r5) == false) goto L37;
            this.backgroundPressedColor = RichMessageLayout.m4824$$Nest$mgetThemedColor(this.layout, r5);
        L38:
            setTextColorKey(r6);
            return;
        L37:
            this.backgroundPressedColor = ColorUtils.blendARGB(this.backgroundColor, r7, 0.1f);
            goto L38
        L28:
            if (r12 == Theme.IvButtonColors.PRIMARY) goto L34;
            int r05 = RichMessageLayout.m4824$$Nest$mgetThemedColor(this.layout, r6);
            if (r12 != Theme.IvButtonColors.DEFAULT) goto L33;
            r2 = 0.08f;
        L33:
            this.backgroundColor = Theme.multAlpha(r05, r2);
            this.backgroundPressedColor = Theme.multAlpha(RichMessageLayout.m4824$$Nest$mgetThemedColor(this.layout, r6), 0.16f);
            goto L38
        L23:
            if (r12 != Theme.IvButtonColors.PRIMARY) goto L25;
            int r06 = RichMessageLayout.m4824$$Nest$mgetThemedColor(this.layout, r6);
            this.backgroundPressedColor = r06;
            this.backgroundColor = r06;
            this.needSaveLayer = true;
            goto L38
        L18:
            if (r12 == Theme.IvButtonColors.PRIMARY) goto L20;
            r12 = Theme.IvButtonColors.DEFAULT;
        L14:
            r04 = false;
            goto L15
        L5:
            r02 = Theme.isCurrentThemeDark();
            goto L7
        }
    }

    public static class RichButtonRowBlock extends RichBlock {
        private static final int GAP = 7;
        private final Align align;
        private final RichButton[] buttons;
        private final ClickHelper clickHelper;
        private int layoutWidth;
        private RichButton pressedButton;
        private RichButton touchButton;

        /* renamed from: org.telegram.messenger.RichMessageLayout$RichButtonRowBlock$1, reason: invalid class name */
        public class AnonymousClass1 implements ClickHelper.Delegate {
            final /* synthetic */ RichButtonRowBlock this$0;

            public AnonymousClass1(RichButtonRowBlock r1) {
                this.this$0 = r1;
            }

            @Override // me.vkryl.android.util.ClickHelper.Delegate
            public /* bridge */ /* synthetic */ boolean forceEnableVibration() {
                return super.forceEnableVibration();
            }

            @Override // me.vkryl.android.util.ClickHelper.Delegate
            public /* bridge */ /* synthetic */ long getLongPressDuration() {
                return super.getLongPressDuration();
            }

            @Override // me.vkryl.android.util.ClickHelper.Delegate
            public /* bridge */ /* synthetic */ boolean ignoreHapticFeedbackSettings(float r1, float r2) {
                return super.ignoreHapticFeedbackSettings(r1, r2);
            }

            @Override // me.vkryl.android.util.ClickHelper.Delegate
            public /* bridge */ /* synthetic */ boolean needCancelTouchBySlopMove() {
                return super.needCancelTouchBySlopMove();
            }

            @Override // me.vkryl.android.util.ClickHelper.Delegate
            public boolean needClickAt(View r1, float r2, float r3) {
                RichButton r12 = RichButtonRowBlock.m4831$$Nest$mgetButtonAt(this.this$0, r2, r3);
                RichButtonRowBlock r22 = this.this$0;
                if (r12 != null) goto L5;
            L7:
                r12 = null;
            L8:
                RichButtonRowBlock.m4830$$Nest$fputtouchButton(r22, r12);
                if (RichButtonRowBlock.m4829$$Nest$fgettouchButton(this.this$0) == null) goto L12;
                return true;
            L12:
                return false;
            L5:
                if (r12.isDisabled == true) goto L7;
                goto L7
            }

            @Override // me.vkryl.android.util.ClickHelper.Delegate
            public boolean needLongPress(float r1, float r2) {
                return true;
            }

            @Override // me.vkryl.android.util.ClickHelper.Delegate
            public void onClickAt(View r1, float r2, float r3) {
                if (RichButtonRowBlock.m4829$$Nest$fgettouchButton(this.this$0) == null) goto L6;
                RichButtonRowBlock r0 = this.this$0;
                RichButtonRowBlock.m4832$$Nest$monButtonClick(r0, RichButtonRowBlock.m4829$$Nest$fgettouchButton(r0));
                return;
            }

            @Override // me.vkryl.android.util.ClickHelper.Delegate
            public void onClickTouchDown(View r1, float r2, float r3) {
                RichButtonRowBlock r0 = this.this$0;
                RichButtonRowBlock.m4834$$Nest$msetPressedButton(r0, RichButtonRowBlock.m4829$$Nest$fgettouchButton(r0));
            }

            @Override // me.vkryl.android.util.ClickHelper.Delegate
            public void onClickTouchMove(View r1, float r2, float r3) {
                RichButtonRowBlock r12 = this.this$0;
                if (RichButtonRowBlock.m4831$$Nest$mgetButtonAt(r12, r2, r3) != RichButtonRowBlock.m4829$$Nest$fgettouchButton(this.this$0)) goto L5;
                RichButton r0 = RichButtonRowBlock.m4829$$Nest$fgettouchButton(this.this$0);
            L6:
                RichButtonRowBlock.m4834$$Nest$msetPressedButton(r12, r0);
                return;
            L5:
                r0 = null;
                goto L6
            }

            @Override // me.vkryl.android.util.ClickHelper.Delegate
            public void onClickTouchUp(View r1, float r2, float r3) {
                RichButtonRowBlock.m4834$$Nest$msetPressedButton(this.this$0, null);
                RichButtonRowBlock.m4830$$Nest$fputtouchButton(this.this$0, null);
            }

            @Override // me.vkryl.android.util.ClickHelper.Delegate
            public /* bridge */ /* synthetic */ void onLongPressCancelled(View r1, float r2, float r3) {
                super.onLongPressCancelled(r1, r2, r3);
            }

            @Override // me.vkryl.android.util.ClickHelper.Delegate
            public /* bridge */ /* synthetic */ void onLongPressFinish(View r1, float r2, float r3) {
                super.onLongPressFinish(r1, r2, r3);
            }

            @Override // me.vkryl.android.util.ClickHelper.Delegate
            public /* bridge */ /* synthetic */ void onLongPressMove(View r1, MotionEvent r2, float r3, float r4, float r5, float r6) {
                super.onLongPressMove(r1, r2, r3, r4, r5, r6);
            }

            @Override // me.vkryl.android.util.ClickHelper.Delegate
            public boolean onLongPressRequestedAt(View r1, float r2, float r3) {
                if (RichButtonRowBlock.m4829$$Nest$fgettouchButton(this.this$0) != null) goto L5;
                return false;
            L5:
                if (RichButtonRowBlock.m4831$$Nest$mgetButtonAt(this.this$0, r2, r3) != RichButtonRowBlock.m4829$$Nest$fgettouchButton(this.this$0)) goto L11;
                RichButtonRowBlock r0 = this.this$0;
                RichButtonRowBlock.m4833$$Nest$monButtonLongClick(r0, RichButtonRowBlock.m4829$$Nest$fgettouchButton(r0));
                return true;
            L11:
                return false;
            }
        }

        public enum Align extends Enum<Align> {
            private static final /* synthetic */ Align[] $VALUES = null;
            public static final Align CENTER = null;
            public static final Align FILL = null;
            public static final Align LEFT = null;
            public static final Align RIGHT = null;

            private static /* synthetic */ Align[] $values() {
                return new Align[]{LEFT, RIGHT, CENTER, FILL};
            }

            static {
                LEFT = new Align("LEFT", 0);
                RIGHT = new Align("RIGHT", 1);
                CENTER = new Align("CENTER", 2);
                FILL = new Align("FILL", 3);
                $VALUES = $values();
            }

            Align(String r1, int r2) {
            }

            public static Align valueOf(String r1) {
                return (Align) Enum.valueOf(Align.class, r1);
            }

            public static Align[] values() {
                return (Align[]) $VALUES.clone();
            }
        }

        public static /* synthetic */ void $r8$lambda$X8DAsRtHOUByswd_QIAJHmyQcVQ(RichButtonRowBlock r0) {
            r0.invalidate();
        }

        /* renamed from: -$$Nest$fgettouchButton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RichButton m4829$$Nest$fgettouchButton(RichButtonRowBlock r0) {
            return r0.touchButton;
        }

        /* renamed from: -$$Nest$fputtouchButton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4830$$Nest$fputtouchButton(RichButtonRowBlock r0, RichButton r1) {
            r0.touchButton = r1;
        }

        /* renamed from: -$$Nest$mgetButtonAt, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RichButton m4831$$Nest$mgetButtonAt(RichButtonRowBlock r0, float r1, float r2) {
            return r0.getButtonAt(r1, r2);
        }

        /* renamed from: -$$Nest$monButtonClick, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4832$$Nest$monButtonClick(RichButtonRowBlock r0, RichButton r1) {
            r0.onButtonClick(r1);
        }

        /* renamed from: -$$Nest$monButtonLongClick, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4833$$Nest$monButtonLongClick(RichButtonRowBlock r0, RichButton r1) {
            r0.onButtonLongClick(r1);
        }

        /* renamed from: -$$Nest$msetPressedButton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4834$$Nest$msetPressedButton(RichButtonRowBlock r0, RichButton r1) {
            r0.setPressedButton(r1);
        }

        public RichButtonRowBlock(RichMessageLayout r6, Rect r7, int r8, TL_iv.pageBlockButtonRow r9) {
            super(r6, r7, r8);
            this.layoutWidth = -1;
            this.clickHelper = new ClickHelper(new AnonymousClass1(this));
            this.buttons = new RichButton[r9.buttons.size()];
            int r72 = r9.buttons.size();
            int r0 = 0;
        L3:
            if (r0 >= r72) goto L6;
            this.buttons[r0] = new RichButton(r6, r8, r9.buttons.get(r0), new RichMessageLayout$RichButtonRowBlock$$ExternalSyntheticLambda0(this));
            r0 = r0 + 1;
            goto L3
        L6:
            if (r9.align_left == false) goto L9;
            this.align = Align.LEFT;
        L15:
            layout(getIntrinsicWidth());
            return;
        L9:
            if (r9.align_center == false) goto L12;
            this.align = Align.CENTER;
            goto L15
        L12:
            if (r9.align_right == false) goto L14;
            this.align = Align.RIGHT;
            goto L15
        L14:
            this.align = Align.FILL;
            goto L15
        }

        private RichButton getButtonAt(float r6, float r7) {
            updateLayout();
            RichButton[] r0 = this.buttons;
            int r2 = 0;
            if (r0.length != 0) goto L5;
            int r02 = AndroidUtilities.dp(RichMessageLayout.m4823$$Nest$fgetfontSize(this.root) + 18);
        L6:
            float r1 = (getHeight() - r02) / 2.0f;
            if (r7 >= r1) goto L9;
        L17:
            return null;
        L9:
            if (r7 > (r1 + r02)) goto L17;
            RichButton[] r5 = this.buttons;
            int r72 = r5.length;
        L12:
            if (r2 >= r72) goto L17;
            RichButton r03 = r5[r2];
            if (r03.contains(r6) == true) goto L15;
            r2 = r2 + 1;
            goto L12
        L15:
            return r03;
        L5:
            r02 = r0[0].getHeight();
            goto L6
        }

        private int getIntrinsicWidth() {
            int r0 = this.buttons.length;
            int r1 = 0;
            if (r0 != 0) goto L5;
            return 0;
        L5:
            int r2 = AndroidUtilities.dp(7.0f) * (r0 - 1);
            RichButton[] r02 = this.buttons;
            int r3 = r02.length;
        L6:
            if (r1 >= r3) goto L9;
            r2 = r2 + r02[r1].getPreferredWidth();
            r1 = r1 + 1;
            goto L6
        L9:
            return Math.min(this.maxWidth, r2);
        }

        private void invalidate() {
            View r0 = this.view;
            if (r0 == null) goto L6;
            r0.invalidate();
            return;
        }

        private void layout(int r12) {
            this.layoutWidth = r12;
            int r0 = this.buttons.length;
            if (r0 == 0) goto L26;
            int r1 = AndroidUtilities.dp(7.0f);
            int r02 = (r0 - 1) * r1;
            int r4 = 0;
            int r3 = Math.max(0, r12 - r02);
            RichButton[] r5 = this.buttons;
            int r6 = r5.length;
            int r7 = 0;
            int r8 = 0;
        L6:
            if (r7 >= r6) goto L8;
            RichButton r9 = r5[r7];
            int r10 = r9.getPreferredWidth();
            r9.width = r10;
            r8 = r8 + r10;
            r7 = r7 + 1;
            goto L6
        L8:
            if (r8 <= r3) goto L11;
            squeeze(r3, r8);
        L13:
            RichButton[] r32 = this.buttons;
            int r52 = r32.length;
            int r62 = 0;
        L14:
            if (r62 >= r52) goto L16;
            r02 = r02 + r32[r62].width;
            r62 = r62 + 1;
            goto L14
        L16:
            int r33 = AnonymousClass1.$SwitchMap$org$telegram$messenger$RichMessageLayout$RichButtonRowBlock$Align[this.align.ordinal()];
            if (r33 != 1) goto L19;
            int r122 = r12 - r02;
        L23:
            RichButton[] r11 = this.buttons;
            int r03 = r11.length;
        L24:
            if (r4 >= r03) goto L30;
            RichButton r2 = r11[r4];
            r2.x = r122;
            r122 = r122 + (r2.width + r1);
            r4 = r4 + 1;
            goto L24
        L30:
            return;
        L19:
            if (r33 == 2) goto L21;
            r122 = 0;
            goto L23
        L21:
            r122 = (r12 - r02) / 2;
            goto L23
        L11:
            if (this.align != Align.FILL) goto L13;
            stretch(r3);
            goto L13
        }

        private void onButtonClick(RichButton r2) {
            if (RichMessageLayout.m4821$$Nest$fgetdelegate(this.root) == null) goto L6;
            RichMessageLayout.m4821$$Nest$fgetdelegate(this.root).didPressBotButton(RichMessageLayout.m4820$$Nest$fgetcell(this.root), r2.pageButton);
            return;
        }

        private void onButtonLongClick(RichButton r2) {
            if (RichMessageLayout.m4821$$Nest$fgetdelegate(this.root) == null) goto L6;
            RichMessageLayout.m4821$$Nest$fgetdelegate(this.root).didLongPressBotButton(RichMessageLayout.m4820$$Nest$fgetcell(this.root), r2.pageButton);
            return;
        }

        private void setPressedButton(RichButton r3) {
            RichButton r0 = this.pressedButton;
            if (r0 == r3) goto L12;
            if (r0 == null) goto L7;
            r0.setPressed(false);
        L7:
            this.pressedButton = r3;
            if (r3 == null) goto L11;
            r3.setPressed(true);
            return;
        L11:
            return;
        }

        private void squeeze(int r10, int r11) {
            RichButton[] r0 = this.buttons;
            int r1 = r0.length;
            int r2 = 0;
            int r3 = 0;
            int r4 = 0;
        L3:
            if (r3 >= r1) goto L5;
            RichButton r5 = r0[r3];
            r4 = r4 + (r5.width - r5.getMinWidth());
            r3 = r3 + 1;
            goto L3
        L5:
            if (r4 > 0) goto L9;
            RichButton[] r9 = this.buttons;
            int r102 = r9.length;
        L7:
            if (r2 >= r102) goto L17;
            RichButton r112 = r9[r2];
            r112.width = r112.getMinWidth();
            r2 = r2 + 1;
            goto L7
        L17:
            return;
        L9:
            int r103 = Math.min(r11 - r10, r4);
            int r113 = 0;
        L10:
            RichButton[] r02 = this.buttons;
            if (r2 >= r02.length) goto L23;
            RichButton r03 = r02[r2];
            int r12 = r03.width - r03.getMinWidth();
            if (r2 != (this.buttons.length - 1)) goto L15;
            int r32 = r103 - r113;
        L16:
            int r13 = Math.min(r32, r12);
            r03.width -= r13;
            r113 = r113 + r13;
            r2 = r2 + 1;
            goto L10
        L15:
            r32 = (int) ((r103 * r12) / r4);
            goto L16
        }

        private void stretch(int r9) {
            RichButton[] r0 = this.buttons;
            boolean[] r1 = new boolean[r0.length];
            int r02 = r0.length;
            boolean r3 = true;
        L3:
            boolean r4 = false;
            if (r3 == false) goto L16;
            if (r02 <= 0) goto L16;
            int r32 = r9 / r02;
            int r5 = 0;
        L7:
            RichButton[] r6 = this.buttons;
            if (r5 >= r6.length) goto L15;
            if (r1[r5] == true) goto L14;
            int r62 = r6[r5].width;
            if (r62 <= r32) goto L14;
            r1[r5] = true;
            r9 = r9 - r62;
            r02 = r02 - 1;
            r4 = true;
        L14:
            r5 = r5 + 1;
            goto L7
        L15:
            r3 = r4;
        L16:
            if (r02 <= 0) goto L29;
            int r33 = r9 / r02;
            int r92 = r9 - (r02 * r33);
            int r03 = 0;
        L19:
            RichButton[] r52 = this.buttons;
            if (r03 >= r52.length) goto L39;
            if (r1[r03] == true) goto L28;
            RichButton r53 = r52[r03];
            int r63 = r92 - 1;
            if (r92 <= 0) goto L26;
            int r93 = 1;
        L27:
            r53.width = r93 + r33;
            r92 = r63;
            goto L28
        L26:
            r93 = 0;
        L28:
            r03 = r03 + 1;
            goto L19
        L39:
            return;
        }

        private void updateLayout() {
            int r0 = this.root.getMinWidth();
            RichMessageLayout r1 = this.root;
            int r2 = r1.padLeft;
            int r02 = ((r0 + r2) + r1.padRight) - (r2 * 2);
            Rect r12 = this.padding;
            int r03 = Math.max(0, (r02 - r12.left) - r12.right);
            if (this.layoutWidth == r03) goto L6;
            layout(r03);
            return;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void draw(Canvas r8) {
            updateLayout();
            RichButton[] r0 = this.buttons;
            int r1 = r0.length;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            RichButton r3 = r0[r2];
            r8.save();
            r8.translate(r3.x, Math.round((getHeight() - r3.getHeight()) / 2.0f));
            r3.draw(r8);
            r8.restore();
            r2 = r2 + 1;
            goto L3
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            RichButton[] r0 = this.buttons;
            if (r0.length != 0) goto L5;
            int r02 = AndroidUtilities.dp(RichMessageLayout.m4823$$Nest$fgetfontSize(this.root) + 18);
        L7:
            return ((this.padding.top + r02) + AndroidUtilities.dp(4.333f)) + this.padding.bottom;
        L5:
            r02 = r0[0].getHeight();
            goto L7
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            return (this.padding.left + getIntrinsicWidth()) + this.padding.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean isHorizontallyDragging() {
            if (this.touchButton == null) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            RichButton[] r0 = this.buttons;
            int r1 = r0.length;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            r0[r2].attach(this.view);
            r2 = r2 + 1;
            goto L3
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            RichButton[] r0 = this.buttons;
            int r1 = r0.length;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            r0[r2].detach(this.view);
            r2 = r2 + 1;
            goto L3
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r2) {
            View r0 = this.view;
            if (r0 != null) goto L7;
            return false;
        L7:
            return this.clickHelper.onTouchEvent(r0, r2);
        }
    }

    public static class RichButtonSpan extends ReplacementSpan {
        private static final int MARGIN_HORIZONTAL = 1;
        private final RectF bounds;
        private final RichButton button;
        private int minimumLineHeight;
        private boolean preserveFontMetrics;
        private float scale;
        private final TL_iv.textButton textButton;
        private View v;

        /* renamed from: $r8$lambda$LNuNT9lyuYoOAXPgC9fvJ-OAP8w, reason: not valid java name */
        public static /* synthetic */ void m4835$r8$lambda$LNuNT9lyuYoOAXPgC9fvJOAP8w(RichButtonSpan r0) {
            r0.invalidate();
        }

        /* renamed from: -$$Nest$fgetbutton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RichButton m4836$$Nest$fgetbutton(RichButtonSpan r0) {
            return r0.button;
        }

        /* renamed from: -$$Nest$fputminimumLineHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4837$$Nest$fputminimumLineHeight(RichButtonSpan r0, int r1) {
            r0.minimumLineHeight = r1;
        }

        /* renamed from: -$$Nest$fputpreserveFontMetrics, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4838$$Nest$fputpreserveFontMetrics(RichButtonSpan r0, boolean r1) {
            r0.preserveFontMetrics = r1;
        }

        /* renamed from: -$$Nest$fputscale, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4839$$Nest$fputscale(RichButtonSpan r0, float r1) {
            r0.scale = r1;
        }

        public RichButtonSpan(RichMessageLayout r2, int r3, TL_iv.textButton r4) {
            this(r2, r3, r4, null);
        }

        private RichButtonSpan(RichMessageLayout r22, int r23, TL_iv.textButton r24, Boolean r25) {
            this.bounds = new RectF();
            this.textButton = r24;
            TL_iv.RichText r2 = r24.text;
            if ((r2 instanceof TL_iv.textConcat) == false) goto L7;
            TL_iv.textConcat r26 = (TL_iv.textConcat) r2;
            if (r26.texts.isEmpty() == true) goto L7;
            boolean r3 = r26.texts.get(0) instanceof TL_iv.textCustomEmoji;
            ArrayList<TL_iv.RichText> r27 = r26.texts;
            boolean r16 = r27.get(r27.size() - 1) instanceof TL_iv.textCustomEmoji;
            boolean r15 = r3;
        L8:
            TL_keyboard.RichButtonStyle r28 = r24.style;
            if (r28 != null) goto L11;
        L13:
            boolean r17 = false;
        L14:
            if (r17 == false) goto L16;
            int r29 = RichMessageLayout.setBlockFlags(512, 0);
        L17:
            CharSequence r9 = r22.formatText(r24.text, r29);
            TL_keyboard.InlineButtonType r11 = r24.type;
            RichButton r6 = new RichButton(r22, r23, r9, null, r11, r24.style, r11 instanceof TL_keyboard.TL_inlineButtonTypeDisabled, true, r15, r16, r17, true, r25, new RichMessageLayout$RichButtonSpan$$ExternalSyntheticLambda0(this));
            this.button = r6;
            r6.width = r6.getPreferredWidth();
            return;
        L16:
            r29 = RichMessageLayout.setBlockFlags(16, 13);
            goto L17
        L11:
            if (r28.link == false) goto L13;
            r17 = true;
        L7:
            r15 = false;
            r16 = false;
            goto L8
        }

        public /* synthetic */ RichButtonSpan(RichMessageLayout r1, int r2, TL_iv.textButton r3, Boolean r4, RichMessageLayoutIA r5) {
            this(r1, r2, r3, r4);
        }

        private static void expandFontMetrics(Paint.FontMetricsInt r3, int r4) {
            int r0 = r3.descent;
            int r1 = r3.ascent;
            int r2 = r0 - r1;
            if (r4 > r2) goto L5;
            return;
        L5:
            int r42 = r4 - r2;
            int r22 = (r42 + 1) / 2;
            int r12 = r1 - r22;
            r3.ascent = r12;
            r3.descent = r0 + (r42 - r22);
            r3.top = Math.min(r3.top, r12);
            r3.bottom = Math.max(r3.bottom, r3.descent);
        }

        private void invalidate() {
            View r0 = this.v;
            if (r0 == null) goto L6;
            r0.invalidate();
            return;
        }

        public void attach(View r1) {
            this.v = r1;
            this.button.attach(r1);
        }

        public boolean contains(float r1, float r2) {
            return this.bounds.contains(r1, r2);
        }

        public boolean contains(float r2, float r3, float r4) {
            RectF r1 = this.bounds;
            if (r2 >= (r1.left - r4)) goto L5;
            return false;
        L5:
            if (r2 < (r1.right + r4)) goto L7;
            return false;
        L7:
            if (r3 >= (r1.top - r4)) goto L9;
            return false;
        L9:
            if (r3 >= (r1.bottom + r4)) goto L16;
            return true;
        L16:
            return false;
        }

        public void detach(View r2) {
            this.v = null;
            this.button.detach(r2);
        }

        public void didPress(ChatMessageCell r1, ChatMessageCell.ChatMessageCellDelegate r2, boolean r3) {
            if (r1 == null) goto L11;
            if (r2 == null) goto L12;
            TL_iv.textButton r0 = this.textButton;
            if (r3 == false) goto L9;
            r2.didLongPressBotButton(r1, r0);
            return;
        L9:
            r2.didPressBotButton(r1, r0);
            return;
        L12:
            return;
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r6, CharSequence r7, int r8, int r9, float r10, int r11, int r12, int r13, Paint r14) {
            int r2 = 0;
            if (this.button.text.getEmojiOnlyCount() > 0) goto L5;
        L7:
            int r0 = 0;
        L8:
            if (r0 != 0) goto L20;
            if (this.button.text.getEmojiOnlyCount() <= 0) goto L20;
            if ((r7 instanceof Spanned) == false) goto L20;
            StyleSpan[] r72 = (StyleSpan[]) ((Spanned) r7).getSpans(r8, r9, StyleSpan.class);
            int r82 = r72.length;
            int r92 = 0;
        L14:
            if (r92 >= r82) goto L20;
            if ((r72[r92].flags & 15) == 14) goto L17;
            r92 = r92 + 1;
            goto L14
        L17:
            r0 = 1;
        L20:
            if (r0 == 0) goto L22;
            float r83 = (r11 + r13) / 2.0f;
        L23:
            r6.save();
            if (RichButton.m4828$$Nest$fgetlink(this.button) == true) goto L27;
            r2 = AndroidUtilities.dp(1.0f);
        L27:
            int r93 = Math.round(r10 + r2);
            int r73 = (int) Math.ceil((r83 - (this.button.getHeight() / 2.0f)) + (r0 ^ 1));
            RectF r84 = this.bounds;
            float r102 = r93;
            float r112 = r73;
            RichButton r122 = this.button;
            r84.set(r102, r112, r93 + r122.width, r73 + r122.getHeight());
            r6.translate(r102, r112);
            this.button.draw(r6);
            r6.restore();
            return;
        L22:
            Paint.FontMetricsInt r85 = r14.getFontMetricsInt();
            r83 = ((r85.ascent + r85.descent) / 2.0f) + r12;
            goto L23
        L5:
            if (this.minimumLineHeight <= 0) goto L7;
            r0 = 1;
            goto L8
        }

        public RichButton getButton() {
            return this.button;
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r9, CharSequence r10, int r11, int r12, Paint.FontMetricsInt r13) {
            int r102 = 0;
            if (this.preserveFontMetrics == false) goto L6;
            if (r13 == null) goto L6;
            boolean r92 = true;
        L7:
            if (r92 == false) goto L9;
            int r112 = r13.top;
        L10:
            if (r92 == false) goto L12;
            int r122 = r13.ascent;
        L13:
            if (r92 == false) goto L15;
            int r0 = r13.descent;
        L16:
            if (r92 == false) goto L18;
            int r1 = r13.bottom;
        L19:
            if (r92 == false) goto L21;
            int r2 = r13.leading;
        L22:
            int r3 = AndroidUtilities.dp(8.0f);
            int r4 = AndroidUtilities.dp(10.0f);
            if (r13 != null) goto L25;
        L27:
            if (r92 == false) goto L29;
            r13.top = r112;
            r13.ascent = r122;
            r13.descent = r0;
            r13.bottom = r1;
            r13.leading = r2;
            expandFontMetrics(r13, this.minimumLineHeight);
        L29:
            RichButton r8 = this.button;
            int r93 = r8.width;
            if (RichButton.m4828$$Nest$fgetlink(r8) == true) goto L34;
            r102 = AndroidUtilities.dp(1.0f) * 2;
        L34:
            return r93 + r102;
        L25:
            if (RichButton.m4828$$Nest$fgetlink(this.button) == false) goto L27;
            float r5 = (-r4) - r3;
            float r6 = this.scale;
            r13.top = (int) (r5 * r6);
            float r32 = r4 - r3;
            r13.bottom = (int) (r32 * r6);
            r13.ascent = (int) (r5 * r6);
            r13.descent = (int) (r32 * r6);
            r13.leading = 0;
            goto L27
        L21:
            r2 = 0;
            goto L22
        L18:
            r1 = 0;
            goto L19
        L15:
            r0 = 0;
            goto L16
        L12:
            r122 = 0;
            goto L13
        L9:
            r112 = 0;
        L6:
            r92 = false;
            goto L7
        }

        public boolean isDisabled() {
            return this.button.isDisabled;
        }

        public void setPressed(boolean r1) {
            this.button.setPressed(r1);
        }
    }

    public static class RichCaptionBlock extends RichBlock {
        public final Text caption;
        public final Text credit;
        public final boolean rtl;
        private final TextSelectionHelper.TextLayoutBlock[] texts;

        public RichCaptionBlock(RichMessageLayout r2, Rect r3, int r4, CharSequence r5, CharSequence r6) {
            super(r2, r3, r4);
            Text r42 = null;
            if (TextUtils.isEmpty(r5) == true) goto L5;
            Text r32 = new Text(r2, r5, this.maxWidth);
        L6:
            this.caption = r32;
            if (TextUtils.isEmpty(r6) == true) goto L9;
            r42 = new Text(r2, r6, this.maxWidth);
        L9:
            this.credit = r42;
            this.rtl = r2.isRtl();
            ArrayList r22 = new ArrayList(2);
            if (r32 == null) goto L12;
            r22.add(r32);
        L12:
            if (r42 == null) goto L14;
            r22.add(r42);
        L14:
            this.texts = (TextSelectionHelper.TextLayoutBlock[]) r22.toArray(new TextSelectionHelper.TextLayoutBlock[0]);
            return;
        L5:
            r32 = null;
            goto L6
        }

        private int captionHeight() {
            Text r0 = this.caption;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return r0.getHeight();
        }

        private int creditDrawX() {
            if (this.credit != null) goto L5;
        L9:
            return 0;
        L5:
            if (this.rtl == false) goto L9;
            int r0 = this.root.getMinWidth();
            Rect r2 = this.padding;
            return Math.max(0, ((r0 - r2.left) - r2.right) - this.credit.getMinWidth());
        }

        private int creditHeight() {
            Text r0 = this.credit;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return r0.getHeight();
        }

        private int gap() {
            if (this.caption != null) goto L5;
            return 0;
        L5:
            if (this.credit != null) goto L7;
            return 0;
        L7:
            return AndroidUtilities.dp(4.0f);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void appendAccessibilityText(SpannableStringBuilder r3) {
            RichBlock.appendText(r3, this.caption, null);
            Text r0 = this.credit;
            if (r0 == null) goto L15;
            StaticLayout r02 = r0.layout;
            if (r02 != null) goto L7;
            return;
        L7:
            if (TextUtils.isEmpty(r02.getText()) == false) goto L9;
            return;
        L9:
            if (r3.length() > 0) goto L11;
        L13:
            r3.append(this.credit.layout.getText());
            return;
        L11:
            if (r3.charAt(r3.length() - 1) == '\n') goto L13;
            r3.append('\n');
            goto L13
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean forcesTimeToNewLine() {
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            return (((this.padding.top + captionHeight()) + gap()) + creditHeight()) + this.padding.bottom;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getLastLineWidth() {
            Text r0 = this.credit;
            if (r0 == null) goto L7;
            int r1 = this.padding.left + r0.getLastLineWidth();
            int r2 = this.padding.right;
        L6:
            return r1 + r2;
        L7:
            Text r02 = this.caption;
            Rect r12 = this.padding;
            if (r02 == null) goto L11;
            r1 = r12.left + r02.getLastLineWidth();
            r2 = this.padding.right;
            goto L6
        L11:
            return r12.left + r12.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            Text r0 = this.caption;
            int r1 = 0;
            if (r0 == null) goto L5;
            r1 = Math.max(0, r0.getMinWidth());
        L5:
            Text r02 = this.credit;
            if (r02 == null) goto L8;
            r1 = Math.max(r1, r02.getMinWidth());
        L8:
            Rect r2 = this.padding;
            return (r2.left + r1) + r2.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public TextSelectionHelper.TextLayoutBlock[] getText() {
            return this.texts;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onAttachedToWindow() {
            Text r0 = this.caption;
            if (r0 == null) goto L5;
            r0.attach(this.view);
        L5:
            Text r02 = this.credit;
            if (r02 == null) goto L9;
            r02.attach(this.view);
            return;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDetachedFromWindow() {
            Text r0 = this.caption;
            if (r0 == null) goto L5;
            r0.detach(this.view);
        L5:
            Text r02 = this.credit;
            if (r02 == null) goto L9;
            r02.detach(this.view);
            return;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r4) {
            Text r0 = this.caption;
            if (r0 == null) goto L6;
            r0.draw(r4);
        L6:
            if (this.credit == null) goto L9;
            r4.save();
            r4.translate(creditDrawX(), captionHeight() + gap());
            this.credit.draw(r4);
            r4.restore();
            return;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r7) {
            int r0 = captionHeight();
            int r1 = gap();
            float r2 = r7.getY();
            int r3 = this.padding.top;
            float r22 = r2 - r3;
            if (this.caption == null) goto L11;
            if (r22 < 0.0f) goto L11;
            if (r22 >= r0) goto L11;
            r7.offsetLocation(0.0f, -r3);
            boolean r02 = this.caption.onTouchEvent(r7);
            r7.offsetLocation(0.0f, this.padding.top);
            return r02;
        L11:
            if (this.credit != null) goto L13;
            return false;
        L13:
            if (r22 < (r0 + r1)) goto L18;
            int r32 = (r3 + r0) + r1;
            int r03 = creditDrawX();
            r7.offsetLocation(-r03, -r32);
            boolean r6 = this.credit.onTouchEvent(r7);
            r7.offsetLocation(r03, r32);
            return r6;
        L18:
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void placeTexts(int r3, int r4, int r5) {
            this.layoutX = r3;
            this.layoutY = r4;
            this.layoutRow = r5;
            Text r0 = this.caption;
            if (r0 == null) goto L5;
            r0.setX(r3 - r0.left);
            this.caption.setY(r4);
            this.caption.setRow(r5);
        L5:
            Text r02 = this.credit;
            if (r02 == null) goto L9;
            r02.setX((r3 + creditDrawX()) - this.credit.left);
            this.credit.setY((r4 + captionHeight()) + gap());
            this.credit.setRow(r5);
            return;
        }
    }

    public static class RichCollageBlock extends RichBlock {
        private static Paint mediaBgPaint;
        public final TL_iv.pageBlockCollage block;
        private int[] cellFlags;
        public final ArrayList<MediaCell> cells;
        private int contentHeight;
        public final boolean first;
        private MediaCell pressedCell;

        public RichCollageBlock(RichMessageLayout r1, Rect r2, int r3, TL_iv.pageBlockCollage r4, boolean r5) {
            super(r1, r2, r3);
            this.cells = new ArrayList();
            this.block = r4;
            this.first = r5;
            int r22 = 0;
        L4:
            if (r22 >= r4.items.size()) goto L9;
            MediaCell r32 = MediaCell.forPageBlock(r1, r4.items.get(r22));
            if (r32 == null) goto L8;
            this.cells.add(r32);
        L8:
            r22 = r22 + 1;
            goto L4
        L9:
            layoutCells();
        }

        private void layoutCells() {
            this.cellFlags = new int[this.cells.size()];
            if (this.cells.isEmpty() == false) goto L6;
            this.contentHeight = 0;
            return;
        L6:
            int r1 = this.cells.size();
            ArrayList<MediaCell> r3 = this.cells;
            float r4 = 0.0f;
            byte r5 = 1;
            if (r1 != 1) goto L16;
            MediaCell r12 = r3.get(0);
            float r32 = r12.aspectRatio;
            if (r32 > 0.0f) goto L11;
            r32 = 1.0f;
        L11:
            int r42 = this.maxWidth;
            int r52 = (int) (r42 / r32);
            Point r6 = AndroidUtilities.displaySize;
            int r62 = (int) (Math.max(r6.x, r6.y) * 0.55f);
            if (r52 <= r62) goto L14;
            r42 = (int) (r62 * r32);
            r52 = r62;
        L14:
            r12.setRect(0, 0, r42, r52);
            this.cellFlags[0] = 15;
            this.contentHeight = r52;
            return;
        L16:
            float[] r13 = new float[r3.size()];
            int r33 = 0;
        L18:
            if (r33 >= this.cells.size()) goto L20;
            r13[r33] = this.cells.get(r33).aspectRatio;
            r33 = r33 + 1;
            goto L18
        L20:
            MessageObject.GroupedMessagePosition[] r14 = RichMessageLayout.computeGrouped(r13);
            int r34 = r14.length;
            int r63 = 0;
            int r7 = 0;
        L21:
            if (r63 >= r34) goto L23;
            r7 = Math.max(r7, r14[r63].maxY);
            r63 = r63 + 1;
            goto L21
        L23:
            int r35 = r7 + 1;
            float[] r64 = new float[r35];
            int r8 = r14.length;
            int r9 = 0;
        L24:
            if (r9 >= r8) goto L29;
            MessageObject.GroupedMessagePosition r10 = r14[r9];
            byte r11 = r10.minY;
            if (r11 != r10.maxY) goto L28;
            r64[r11] = Math.max(r64[r11], r10.ph);
        L28:
            r9 = r9 + 1;
            goto L24
        L29:
            int r82 = r14.length;
            int r92 = 0;
        L30:
            if (r92 >= r82) goto L45;
            MessageObject.GroupedMessagePosition r102 = r14[r92];
            int r112 = r102.minY;
            byte r122 = r102.maxY;
            if (r112 == r122) goto L44;
            int r123 = (r122 - r112) + 1;
            float[] r132 = r102.siblingHeights;
            if (r132 != null) goto L36;
        L40:
            float r133 = r102.ph / r123;
        L42:
            if (r112 > r102.maxY) goto L44;
            r64[r112] = Math.max(r64[r112], r133);
            r112 = r112 + 1;
            goto L42
        L36:
            if (r132.length != r123) goto L40;
            int r113 = 0;
        L38:
            if (r113 >= r123) goto L44;
            byte r134 = r102.minY;
            r64[r134 + r113] = Math.max(r64[r134 + r113], r102.siblingHeights[r113]);
            r113 = r113 + 1;
        L44:
            r92 = r92 + 1;
            goto L30
        L45:
            Point r83 = AndroidUtilities.displaySize;
            float r84 = Math.max(r83.x, r83.y) * 0.5f;
            int[] r93 = new int[r7 + 2];
            int r103 = 0;
        L46:
            if (r103 > r7) goto L48;
            r93[r103] = Math.round(r4 * r84);
            r4 = r4 + r64[r103];
            r103 = r103 + 1;
            goto L46
        L48:
            r93[r35] = Math.round(r4 * r84);
            int r43 = AndroidUtilities.dp(2.0f);
            int r65 = 0;
        L50:
            if (r65 >= r14.length) goto L76;
            MessageObject.GroupedMessagePosition r72 = r14[r65];
            int r85 = r93[r72.minY];
            int r104 = r93[r72.maxY + r5] - r85;
            if (r72.leftSpanOffset <= 0) goto L54;
            int r114 = Math.round((r11 * this.maxWidth) / 1000.0f);
        L69:
            if ((r72.flags & 2) == 0) goto L71;
            int r53 = this.maxWidth - r114;
        L73:
            if ((r72.flags & 8) != 0) goto L75;
            r104 = r104 - r43;
        L75:
            this.cells.get(r65).setRect(r114, r85, Math.max(0, r53), Math.max(0, r104));
            this.cellFlags[r65] = r72.flags;
            r65 = r65 + 1;
            r5 = 1;
            goto L50
        L71:
            r53 = Math.round((r72.pw * this.maxWidth) / 1000.0f) - r43;
            goto L73
        L54:
            int r115 = 0;
            int r135 = 0;
        L56:
            if (r115 >= r14.length) goto L67;
            if (r115 == r65) goto L66;
            MessageObject.GroupedMessagePosition r142 = r14[r115];
            byte r15 = r142.minY;
            byte r54 = r72.minY;
            if (r15 > r54) goto L66;
            if (r142.maxY < r54) goto L66;
            if (r142.minX >= r72.minX) goto L66;
            r135 = r135 + r142.pw;
        L66:
            r115 = r115 + 1;
            goto L56
        L67:
            r114 = Math.round((r135 * this.maxWidth) / 1000.0f);
            goto L69
        L76:
            this.contentHeight = r93[r35];
        }

        private void updateRoundRadius(ImageReceiver r7, int r8, boolean r9) {
            boolean r1 = true;
            int r2 = 0;
            if ((r8 & 4) == 0) goto L5;
            boolean r0 = true;
        L7:
            if ((r8 & 8) == 0) goto L9;
            boolean r3 = true;
        L11:
            if ((r8 & 1) == 0) goto L13;
            boolean r4 = true;
        L15:
            if ((r8 & 2) != 0) goto L18;
            r1 = false;
        L18:
            if (r9 == false) goto L37;
            int r6 = AndroidUtilities.dp(8.0f);
            if (r0 == false) goto L23;
            if (r4 == false) goto L23;
            int r82 = r6;
        L24:
            if (r0 == false) goto L27;
            if (r1 == false) goto L27;
            int r92 = r6;
        L28:
            if (r3 == false) goto L31;
            if (r1 == false) goto L31;
            int r02 = r6;
        L32:
            if (r3 == false) goto L35;
            if (r4 == false) goto L35;
            r2 = r6;
        L35:
            r7.setRoundRadius(r82, r92, r02, r2);
            return;
        L31:
            r02 = 0;
        L27:
            r92 = 0;
        L23:
            r82 = 0;
            goto L24
        L37:
            int r83 = SharedConfig.bubbleRadius;
            if (r83 <= 2) goto L40;
            int r84 = AndroidUtilities.dp(r83 - 2);
        L41:
            int r93 = Math.min(AndroidUtilities.dp(3.0f), r84);
            if (r0 == false) goto L54;
            if (r4 == false) goto L54;
            if (this.first == true) goto L47;
        L53:
            int r5 = r93;
        L55:
            if (r0 == false) goto L67;
            if (r1 == false) goto L67;
            if (this.first == true) goto L60;
        L66:
            r84 = r93;
        L68:
            if (r3 == false) goto L71;
            if (r1 == false) goto L71;
            int r62 = r93;
        L72:
            if (r3 == false) goto L75;
            if (r4 == false) goto L75;
            r2 = r93;
        L75:
            r7.setRoundRadius(r5, r84, r62, r2);
            return;
        L71:
            r62 = 0;
            goto L72
        L60:
            if (this.root.hasNameOffset() == true) goto L66;
            if (this.root.isOut() == false) goto L68;
            if (this.root.isPinnedTop() == true) goto L66;
        L67:
            r84 = 0;
            goto L68
        L47:
            if (this.root.hasNameOffset() == true) goto L53;
            if (this.root.isOut() == false) goto L51;
        L52:
            r5 = r84;
            goto L55
        L51:
            if (this.root.isPinnedTop() == true) goto L53;
        L54:
            r5 = 0;
            goto L55
        L40:
            r84 = AndroidUtilities.dp(r83);
            goto L41
        L13:
            r4 = false;
            goto L15
        L9:
            r3 = false;
            goto L11
        L5:
            r0 = false;
            goto L7
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void getBlockAccessibilityElementBounds(int r4, Rect r5) {
            if (r4 >= 0) goto L4;
            return;
        L4:
            if (r4 >= this.cells.size()) goto L9;
            MediaCell r42 = this.cells.get(r4);
            Rect r0 = this.padding;
            int r1 = r0.left + r42.x;
            int r3 = (((int) this.currY) + r0.top) + r42.y;
            r5.set(r1, r3, r42.w + r1, r42.h + r3);
            return;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getBlockAccessibilityElementCount() {
            return this.cells.size();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public CharSequence getBlockAccessibilityElementText(int r2) {
            if (r2 >= 0) goto L4;
            return null;
        L4:
            if (r2 < this.cells.size()) goto L7;
            return null;
        L7:
            return this.cells.get(r2).getAccessibilityText();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            return this.contentHeight;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getLastLineWidth() {
            return getMinWidth();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            Rect r0 = this.padding;
            return (r0.left + this.maxWidth) + r0.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onAttachedToWindow() {
            ArrayList<MediaCell> r0 = this.cells;
            int r1 = r0.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            MediaCell r3 = r0.get(r2);
            r2 = r2 + 1;
            r3.attach(this.view);
            goto L3
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onBlockAccessibilityElementClick(int r2, View r3) {
            if (r2 >= 0) goto L4;
            return false;
        L4:
            if (r2 < this.cells.size()) goto L7;
            return false;
        L7:
            return this.cells.get(r2).onAccessibilityClick(r3);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDetachedFromWindow() {
            ArrayList<MediaCell> r3 = this.cells;
            int r0 = r3.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            MediaCell r2 = r3.get(r1);
            r1 = r1 + 1;
            r2.detach();
            goto L3
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r18) {
            if (mediaBgPaint != null) goto L5;
            Paint r1 = new Paint(1);
            mediaBgPaint = r1;
            r1.setColor(251658240);
        L5:
            boolean r12 = isInQuote();
            int r2 = AndroidUtilities.dp(2.0f);
            if (r12 == false) goto L8;
            int r4 = 0;
        L9:
            if (r12 == false) goto L11;
            int r5 = 0;
        L12:
            int r22 = this.maxWidth;
            if (r22 <= 0) goto L17;
            if (r4 > 0) goto L16;
            if (r5 <= 0) goto L17;
        L16:
            float r52 = ((r22 + r4) + r5) / r22;
        L18:
            int r23 = 0;
        L20:
            if (r23 >= this.cells.size()) goto L35;
            MediaCell r7 = this.cells.get(r23);
            int r8 = Math.round(r7.x * r52) - r4;
            int r9 = Math.round(r7.w * r52);
            ImageReceiver r10 = r7.imageReceiver;
            int[] r11 = this.cellFlags;
            if (r11 != null) goto L24;
        L26:
            int r112 = 0;
        L27:
            updateRoundRadius(r10, r112, r12);
            float r122 = r8;
            r7.imageReceiver.setImageCoords(r122, r7.y, r9, r7.h);
            if (r7.imageReceiver.hasBitmapImage() == true) goto L30;
        L33:
            Canvas r113 = r18;
            r113.drawRect(r122, r7.y, r8 + r9, r10 + r7.h, mediaBgPaint);
        L34:
            r7.draw(r113);
            r23 = r23 + 1;
            goto L20
        L30:
            if (r7.imageReceiver.getCurrentAlpha() != 1.0f) goto L33;
            r113 = r18;
            goto L34
        L24:
            if (r23 >= r11.length) goto L26;
            r112 = r11[r23];
            goto L27
        L35:
            return;
        L17:
            r52 = 1.0f;
            goto L18
        L11:
            r5 = this.root.padRight - r2;
            goto L12
        L8:
            r4 = this.root.padLeft - r2;
            goto L9
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r7) {
            int r0 = r7.getActionMasked();
            Rect r1 = this.padding;
            r7.offsetLocation(-r1.left, -r1.top);
            boolean r3 = false;
            if (r0 != 0) goto L19;
            this.pressedCell = null;     // Catch: Throwable -> L14
            int r02 = 0;
        L6:
            if (r02 >= this.cells.size()) goto L17;
            MediaCell r12 = this.cells.get(r02);     // Catch: Throwable -> L14
            if (r12.isInside(r7.getX(), r7.getY()) == false) goto L16;
            if (r12.onTouchEvent(r7, this.view) == false) goto L16;
            this.pressedCell = r12;     // Catch: Throwable -> L14
            Rect r6 = this.padding;
            r7.offsetLocation(r6.left, r6.top);
            return true;
        L16:
            r02 = r02 + 1;
        L17:
            Rect r62 = this.padding;
            r7.offsetLocation(r62.left, r62.top);
            return r3;
        L19:
            MediaCell r4 = this.pressedCell;     // Catch: Throwable -> L14
            if (r4 == null) goto L17;
            r3 = r4.onTouchEvent(r7, this.view);     // Catch: Throwable -> L14
            if (r0 != 1) goto L25;
        L26:
            this.pressedCell = null;     // Catch: Throwable -> L14
            goto L17
        L25:
            if (r0 != 3) goto L17;
        L14:
            th = move-exception;
            Rect r63 = this.padding;
            r7.offsetLocation(r63.left, r63.top);
            throw th;
        }
    }

    public static class RichDetailsBlock extends RichBlock {
        private static final float ARROW_HEIGHT_DP = 6.16f;
        private static final float ARROW_LEFT_DP = 22.6f;
        private static final float ARROW_STROKE_DP = 1.66f;
        private static final float ARROW_TOP_DP = 21.66f;
        private static final float ARROW_WIDTH_DP = 12.66f;
        private static final float TEXT_BOTTOM_DP = 12.66f;
        private static final float TEXT_LEFT_DP = 53.0f;
        private static final float TEXT_RIGHT_DP = 16.0f;
        private static final float TEXT_TOP_DP = 14.0f;
        public float animClipBottom;
        public float animClipTop;
        public final AnimatedArrowDrawable arrow;
        public final TL_iv.pageBlockDetails block;
        private ButtonBounce bounce;
        private final Paint linePaint;
        private boolean pressed;
        public final Text[] texts;
        public final Text title;

        /* renamed from: -$$Nest$mupdateBubbleInsets, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4840$$Nest$mupdateBubbleInsets(RichDetailsBlock r0) {
            r0.updateBubbleInsets();
        }

        public RichDetailsBlock(RichMessageLayout r2, Rect r3, int r4, TL_iv.pageBlockDetails r5, CharSequence r6) {
            super(r2, r3, r4);
            this.linePaint = new Paint(1);
            this.block = r5;
            Text r42 = new Text(r2, r6, Math.max(0, (this.maxWidth - AndroidUtilities.dp(TEXT_LEFT_DP)) - AndroidUtilities.dp(TEXT_RIGHT_DP)));
            this.title = r42;
            this.texts = new Text[]{r42};
            if (r2.isOut() == false) goto L5;
            int r32 = Theme.key_chat_outArticleDetailsArrow;
        L6:
            AnimatedArrowDrawable r33 = new AnimatedArrowDrawable(RichMessageLayout.m4824$$Nest$mgetThemedColor(r2, r32), 12.66f, ARROW_HEIGHT_DP, ARROW_STROKE_DP);
            this.arrow = r33;
            if (r5.open == false) goto L9;
            float r1 = 0.0f;
        L10:
            r33.setAnimationProgress(r1);
            return;
        L9:
            r1 = 1.0f;
            goto L10
        L5:
            r32 = Theme.key_chat_inArticleDetailsArrow;
            goto L6
        }

        private void ensureBounce() {
            if (this.bounce != null) goto L8;
            View r0 = this.root.view;
            if (r0 == null) goto L9;
            this.bounce = new ButtonBounce(r0);
            return;
        L9:
            return;
        }

        private int getContentHeight() {
            return Math.max(AndroidUtilities.dp(27.82f), (AndroidUtilities.dp(TEXT_TOP_DP) + this.title.getHeight()) + AndroidUtilities.dp(12.66f));
        }

        private void toggle() {
            this.root.snapshotForDetailsAnimation();
            TL_iv.pageBlockDetails r0 = this.block;
            boolean r1 = r0.open;
            r0.open = !r1;
            AnimatedArrowDrawable r02 = this.arrow;
            if (r1 == true) goto L5;
            float r12 = 0.0f;
        L6:
            r02.setAnimationProgressAnimated(r12);
            RichMessageLayout r03 = this.root;
            r03.detailsAnimating = true;
            r03.reposition();
            View r04 = this.root.view;
            if (r04 == null) goto L9;
            r04.invalidate();
        L9:
            ChatMessageCell r05 = this.root.getCell();
            ChatMessageCell.ChatMessageCellDelegate r3 = this.root.getDelegate();
            if (r05 == null) goto L14;
            if (r3 == null) goto L15;
            r3.forceUpdate(r05, true, true);
            return;
        L15:
            return;
        L14:
            return;
        L5:
            r12 = 1.0f;
            goto L6
        }

        private void updateBubbleInsets() {
            this.title.setX(((this.layoutX - this.root.padLeft) + AndroidUtilities.dp(TEXT_LEFT_DP)) - this.title.left);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void appendAccessibilityText(SpannableStringBuilder r2) {
            RichBlock.appendText(r2, this.title, this.texts);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean findLink(CharacterStyle r2, int r3, FoundLink r4) {
            if (this.title.fillFoundLink(r2, r4) == false) goto L6;
            r4.x = ((this.padding.left - this.root.padLeft) + AndroidUtilities.dp(TEXT_LEFT_DP)) - this.title.left;
            r4.y = (r3 + this.padding.top) + AndroidUtilities.dp(TEXT_TOP_DP);
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void getBlockAccessibilityElementBounds(int r4, Rect r5) {
            int r42 = (int) this.currY;
            Rect r0 = this.padding;
            int r43 = r42 + r0.top;
            int r02 = r0.left;
            RichMessageLayout r1 = this.root;
            r5.set(r02 - r1.padLeft, r43, (r1.getMinWidth() + this.root.padRight) - this.padding.right, getContentHeight() + r43);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getBlockAccessibilityElementCount() {
            return 1;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public CharSequence getBlockAccessibilityElementStateDescription(int r1) {
            if (isOpen() == false) goto L5;
            int r0 = R.string.AccDescrExpanded;
        L7:
            return LocaleController.getString(r0);
        L5:
            r0 = R.string.AccDescrCollapsed;
            goto L7
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public CharSequence getBlockAccessibilityElementText(int r7) {
            Text r72 = this.title;
            if (r72 == null) goto L7;
            StaticLayout r73 = r72.layout;
            if (r73 == null) goto L7;
            CharSequence r74 = RichBlock.withReplacements(r73.getText());
        L8:
            String r0 = LocaleController.getString(R.string.ArticleToggleBlock);
            if (isOpen() == false) goto L11;
            int r6 = R.string.AccDescrExpanded;
        L12:
            String r62 = LocaleController.getString(r6);
            if (TextUtils.isEmpty(r74) == false) goto L15;
            CharSequence r75 = _UrlKt.FRAGMENT_ENCODE_SET;
        L17:
            return TextUtils.concat(new CharSequence[]{r0, ", ", r62, r75});
        L15:
            r75 = TextUtils.concat(new CharSequence[]{", ", r74});
            goto L17
        L11:
            r6 = R.string.AccDescrCollapsed;
        L7:
            r74 = null;
            goto L8
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            return (this.padding.top + getContentHeight()) + this.padding.bottom;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getLastLineWidth() {
            return getMinWidth();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            return (((this.padding.left + AndroidUtilities.dp(TEXT_LEFT_DP)) + this.title.getMinWidth()) + AndroidUtilities.dp(TEXT_RIGHT_DP)) + this.padding.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public TextSelectionHelper.TextLayoutBlock[] getText() {
            return this.texts;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean isBlockAccessibilityElementText(int r1) {
            return true;
        }

        public boolean isOpen() {
            return this.block.open;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onAttachedToWindow() {
            this.title.attach(this.view);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onBlockAccessibilityElementClick(int r1, View r2) {
            toggle();
            return true;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDetachedFromWindow() {
            this.title.detach(this.view);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r11) {
            ButtonBounce r0 = this.bounce;
            if (r0 == null) goto L5;
            float r02 = r0.getScale(0.02f);
        L6:
            RichMessageLayout r2 = this.root;
            float r5 = -r2.padLeft;
            int r22 = r2.getMinWidth() + this.root.padRight;
            Rect r3 = this.padding;
            float r7 = (r22 - r3.left) - r3.right;
            if (r02 == 1.0f) goto L9;
            r11.save();
            r11.scale(r02, r02, (r5 + r7) / 2.0f, getContentHeight() / 2.0f);
        L9:
            RichMessageLayout r03 = this.root;
            if (r03.isOut() == false) goto L12;
            int r32 = Theme.key_chat_outArticleDetailsArrow;
        L13:
            this.arrow.setColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r03, r32));
            r11.save();
            r11.translate(AndroidUtilities.dpf2(ARROW_LEFT_DP) + r5, AndroidUtilities.dpf2(ARROW_TOP_DP));
            this.arrow.draw(r11);
            r11.restore();
            r11.save();
            r11.translate(AndroidUtilities.dp(TEXT_LEFT_DP) + r5, AndroidUtilities.dp(TEXT_TOP_DP));
            this.title.draw(r11);
            r11.restore();
            if (r02 == 1.0f) goto L17;
            r11.restore();
        L17:
            if (isOpen() == true) goto L26;
            RichMessageLayout r04 = this.root;
            if (r04.detailsAnimating == true) goto L27;
            Paint r23 = this.linePaint;
            if (r04.isOut() == false) goto L23;
            int r33 = Theme.key_chat_outArticleDetailsLine;
        L24:
            r23.setColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r04, r33));
            r11.drawRect(r5, getContentHeight() - 1.0f, r7, getContentHeight(), this.linePaint);
            return;
        L23:
            r33 = Theme.key_chat_inArticleDetailsLine;
            goto L24
        L27:
            return;
        L26:
            return;
        L12:
            r32 = Theme.key_chat_inArticleDetailsArrow;
            goto L13
        L5:
            r02 = 1.0f;
            goto L6
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r3) {
            int r32 = r3.getActionMasked();
            if (r32 != 0) goto L9;
            this.pressed = true;
            ensureBounce();
            ButtonBounce r2 = this.bounce;
            if (r2 == null) goto L7;
            r2.setPressed(true);
        L7:
            return true;
        L9:
            if (r32 != 1) goto L22;
            if (this.pressed == false) goto L20;
            this.pressed = false;
            ButtonBounce r33 = this.bounce;
            if (r33 == null) goto L15;
            r33.setPressed(false);
        L15:
            View r34 = this.root.view;
            if (r34 == null) goto L18;
            r34.playSoundEffect(0);
        L18:
            toggle();
            return true;
        L20:
            return false;
        L22:
            if (r32 != 3) goto L27;
            this.pressed = false;
            ButtonBounce r35 = this.bounce;
            if (r35 == null) goto L27;
            r35.setPressed(false);
        L27:
            return this.pressed;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void placeTexts(int r3, int r4, int r5) {
            this.layoutX = r3;
            this.layoutY = r4;
            this.layoutRow = r5;
            this.title.setX(((r3 - this.root.padLeft) + AndroidUtilities.dp(TEXT_LEFT_DP)) - this.title.left);
            this.title.setY(r4 + AndroidUtilities.dp(TEXT_TOP_DP));
            this.title.setRow(r5);
        }
    }

    public static class RichDetailsEndBlock extends RichBlock {
        private final Paint paint;

        public RichDetailsEndBlock(RichMessageLayout r1, Rect r2, int r3) {
            super(r1, r2, r3);
            this.paint = new Paint(1);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            return ((this.padding.top + AndroidUtilities.dp(6.0f)) + 1) + this.padding.bottom;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            return (this.padding.left + AndroidUtilities.dp(32.0f)) + this.padding.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r9) {
            Paint r0 = this.paint;
            RichMessageLayout r1 = this.root;
            if (r1.isOut() == false) goto L5;
            int r2 = Theme.key_chat_outArticleDetailsLine;
        L6:
            r0.setColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r1, r2));
            RichMessageLayout r02 = this.root;
            float r3 = -r02.padLeft;
            int r03 = r02.getMinWidth();
            RichMessageLayout r12 = this.root;
            int r04 = r03 + r12.padRight;
            Rect r22 = this.padding;
            float r5 = (r04 - r22.left) - r22.right;
            float r23 = 0.0f;
            if (r12.detailsAnimating == false) goto L17;
            if (this.prevVisible == false) goto L11;
            float r05 = 1.0f;
        L13:
            if (this.currVisible == false) goto L15;
            r23 = 1.0f;
        L15:
            r23 = AndroidUtilities.lerp(r05, r23, RichMessageLayout.m4822$$Nest$fgetdetailsAnimationProgress(r12));
        L19:
            RichDetailsBlock r06 = this.parentDetails;
            if (r06 == null) goto L22;
            int r07 = r06.padding.bottom;
        L23:
            float r08 = AndroidUtilities.lerp((-1.0f) - r07, AndroidUtilities.dp(6.0f), r23);
            r9.drawRect(r3, r08, r5, r08 + 1.0f, this.paint);
            return;
        L22:
            r07 = 0;
            goto L23
        L11:
            r05 = 0.0f;
            goto L13
        L17:
            if (this.currVisible == false) goto L19;
            r23 = 1.0f;
            goto L19
        L5:
            r2 = Theme.key_chat_inArticleDetailsLine;
            goto L6
        }
    }

    public static class RichDividerBlock extends RichBlock {
        public final Paint paint;

        public RichDividerBlock(RichMessageLayout r1, Rect r2, int r3) {
            super(r1, r2, r3);
            this.paint = new Paint(1);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            return (this.padding.top + AndroidUtilities.dp(12.0f)) + this.padding.bottom;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            return AndroidUtilities.dp(32.0f);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r8) {
            int r0 = this.root.getMinWidth();
            RichMessageLayout r1 = this.root;
            int r2 = r1.padLeft;
            int r02 = (r0 + r2) + r1.padRight;
            Rect r12 = this.padding;
            int r3 = r12.left;
            int r13 = ((r02 - r3) - r12.right) / 2;
            float r03 = (((r0 - r13) / 2.0f) - r2) + r3;
            float r22 = (AndroidUtilities.dp(12.0f) - AndroidUtilities.dp(1.0f)) / 2.0f;
            Paint r4 = this.paint;
            RichMessageLayout r5 = this.root;
            if (r5.isOut() == false) goto L5;
            int r6 = Theme.key_chat_outDivider;
        L6:
            r4.setColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r5, r6));
            RectF r42 = AndroidUtilities.rectTmp;
            r42.set(r03, r22, r13 + r03, AndroidUtilities.dp(1.0f) + r22);
            r8.drawRoundRect(r42, AndroidUtilities.dp(0.5f), AndroidUtilities.dp(0.5f), this.paint);
            return;
        L5:
            r6 = Theme.key_chat_inDivider;
            goto L6
        }
    }

    public static class RichDocumentBlock extends RichBlock implements DownloadController.FileDownloadProgressListener {
        private static final int MIN_WIDTH_DP = 220;
        public final TL_iv.pageBlockDocument block;
        private final int buttonSize;
        private int buttonState;
        private final int buttonTextSpacing;
        private final int buttonX;
        private final int buttonY;
        private final TLRPC.Document document;
        private final boolean hasPreview;
        private int layoutWidth;
        private final int observerTag;
        private final RectF optionsHit;
        private boolean optionsPressed;
        private boolean pressed;
        private final Paint previewBackgroundPaint;
        private final ImageReceiver previewImage;
        private final int previewX;
        private final RadialProgress2 radialProgress;
        private StaticLayout sizeLayout;
        private final TextPaint sizePaint;
        private StaticLayout titleLayout;
        private final TextPaint titlePaint;

        public RichDocumentBlock(RichMessageLayout r11, Rect r12, int r13, TL_iv.pageBlockDocument r14) {
            super(r11, r12, r13);
            ImageReceiver r0 = new ImageReceiver();
            this.previewImage = r0;
            this.previewBackgroundPaint = new Paint(1);
            this.titlePaint = new TextPaint(1);
            this.sizePaint = new TextPaint(1);
            int r122 = AndroidUtilities.dp(16.0f);
            this.buttonX = r122;
            int r1 = AndroidUtilities.dp(9.0f);
            this.buttonY = r1;
            int r2 = AndroidUtilities.dp(42.0f);
            this.buttonSize = r2;
            this.buttonTextSpacing = AndroidUtilities.dp(14.0f);
            this.optionsHit = new RectF();
            this.layoutWidth = -1;
            this.block = r14;
            TLRPC.Document r142 = r11.getDocument(r14.document_id);
            this.document = r142;
            int r3 = r11.padLeft + AndroidUtilities.dp(10.0f);
            this.previewX = r3;
            boolean r5 = MessageObject.isDocumentHasThumb(r142);
            this.hasPreview = r5;
            this.observerTag = DownloadController.getInstance(r11.currentAccount).generateObserverTag();
            ImageLocation r7 = null;
            RadialProgress2 r6 = new RadialProgress2(null);
            this.radialProgress = r6;
            r6.setCircleRadius(r2 / 2);
            if (r5 == false) goto L5;
            r122 = ((AndroidUtilities.dp(86.0f) - r2) / 2) + r3;
        L5:
            if (r5 == false) goto L7;
            r1 = AndroidUtilities.dp(10.0f) + ((AndroidUtilities.dp(86.0f) - r2) / 2);
        L7:
            r6.setProgressRect(r122, r1, r122 + r2, r2 + r1);
            r0.setRoundRadius(AndroidUtilities.dp(6.0f));
            r0.setAllowLoadingOnAttachedOnly(true);
            if (r5 == false) goto L14;
            TLRPC.PhotoSize r132 = FileLoader.getClosestPhotoSizeWithSize(r142.thumbs, 320, false, null, true);
            r0.setImageCoords(r3, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(86.0f), AndroidUtilities.dp(86.0f));
            if (r132 == null) goto L13;
            r7 = ImageLocation.getForDocument(r132, r142);
        L13:
            r0.setImage(r7, "86_86", ImageLoader.createStripedBitmap(r142.thumbs), null, r11.messageObject, 1);
        L14:
            rebuildLayouts();
            updateButtonState(false);
        }

        private boolean canShowOptions() {
            if (this.document != null) goto L5;
            return false;
        L5:
            if (RichMessageLayout.m4820$$Nest$fgetcell(this.root) != null) goto L7;
            return false;
        L7:
            if (RichMessageLayout.m4821$$Nest$fgetdelegate(this.root) != null) goto L9;
            return false;
        L9:
            if (RichMessageLayout.m4821$$Nest$fgetdelegate(this.root).canSaveRichDocument(RichMessageLayout.m4820$$Nest$fgetcell(this.root)) == false) goto L16;
            return true;
        L16:
            return false;
        }

        private static Activity findActivity(Context r1) {
        L3:
            if ((r1 instanceof ContextWrapper) == false) goto L10;
            if ((r1 instanceof Activity) == true) goto L7;
            r1 = ((ContextWrapper) r1).getBaseContext();
            goto L3
        L7:
            return (Activity) r1;
        L10:
            if ((r1 instanceof Activity) == true) goto L12;
            return null;
        L12:
            return (Activity) r1;
        }

        private int getLayoutWidth() {
            int r0 = this.root.getMinWidth();
            int r1 = this.maxWidth;
            if (r0 <= 0) goto L5;
            Rect r2 = this.padding;
            r1 = Math.min(r1, Math.max(0, (r0 - r2.left) - r2.right));
        L5:
            RichMessageLayout r4 = this.root;
            return (r1 + r4.padLeft) + r4.padRight;
        }

        private int getMenuX() {
            return (this.layoutWidth + this.padding.right) - AndroidUtilities.dp(32.0f);
        }

        private File path() {
            if (this.document != null) goto L6;
            return null;
        L6:
            File r0 = FileLoader.getInstance(this.root.currentAccount).getPathToAttach(this.document, false);
            if (r0 == null) goto L12;
            if (r0.exists() == false) goto L12;
            return r0;
        L12:
            return FileLoader.getInstance(this.root.currentAccount).getPathToAttach(this.document, true);
        }

        private void press() {
            int r0 = this.buttonState;
            if (r0 != 0) goto L13;
            View r02 = this.view;
            if (r02 != null) goto L7;
            Context r03 = null;
        L8:
            Activity r4 = findActivity(r03);
            File r1 = path();
            if (r4 == null) goto L21;
            if (r1 == null) goto L21;
            AndroidUtilities.openForView(r1, FileLoader.getDocumentFileName(this.document), this.document.mime_type, r4, this.root.resourcesProvider, false);
        L21:
            View r7 = this.view;
            if (r7 == null) goto L25;
            r7.invalidate();
            return;
        L25:
            return;
        L7:
            r03 = r02.getContext();
            goto L8
        L13:
            if (r0 == 1) goto L15;
        L17:
            if (r0 != 2) goto L21;
            if (this.document == null) goto L21;
            FileLoader.getInstance(this.root.currentAccount).cancelLoadFile(this.document);
            this.buttonState = 1;
            this.radialProgress.setIcon(2, false, true);
            goto L21
        L15:
            if (this.document == null) goto L17;
            FileLoader.getInstance(this.root.currentAccount).loadFile(this.document, this.root.messageObject, 1, 1);
            this.buttonState = 2;
            this.radialProgress.setIcon(3, true, true);
            goto L21
        }

        private void rebuildLayouts() {
            this.layoutWidth = getLayoutWidth();
            if (this.hasPreview == false) goto L6;
            int r0 = this.previewX;
            int r1 = AndroidUtilities.dp(97.0f);
        L7:
            int r6 = Math.max(AndroidUtilities.dp(40.0f), (this.layoutWidth - (r0 + r1)) - AndroidUtilities.dp(48.0f));
            this.titlePaint.setTextSize(AndroidUtilities.dp(RichMessageLayout.m4823$$Nest$fgetfontSize(this.root) - 1));
            this.titlePaint.setTypeface(AndroidUtilities.bold());
            this.sizePaint.setTextSize(AndroidUtilities.dp(RichMessageLayout.m4823$$Nest$fgetfontSize(this.root) - 3));
            TLRPC.Document r02 = this.document;
            String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r02 != null) goto L10;
            String r03 = _UrlKt.FRAGMENT_ENCODE_SET;
        L11:
            CharSequence r4 = TextUtils.ellipsize(r03, this.titlePaint, r6, TextUtils.TruncateAt.END);
            TextPaint r5 = this.titlePaint;
            Layout.Alignment r7 = Layout.Alignment.ALIGN_NORMAL;
            this.titleLayout = new StaticLayout(r4, r5, r6, r7, 1.0f, 0.0f, false);
            TLRPC.Document r04 = this.document;
            if (r04 == null) goto L15;
            r12 = AndroidUtilities.formatFileSize(r04.size);
        L15:
            this.sizeLayout = new StaticLayout(r12, this.sizePaint, r6, r7, 1.0f, 0.0f, false);
            return;
        L10:
            r03 = FileLoader.getDocumentFileName(r02);
            goto L11
        L6:
            r0 = this.buttonX + this.buttonSize;
            r1 = this.buttonTextSpacing;
            goto L7
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            int r0 = this.padding.top;
            if (this.hasPreview == false) goto L5;
            float r1 = 106.0f;
        L7:
            return (r0 + AndroidUtilities.dp(r1)) + this.padding.bottom;
        L5:
            r1 = 60.0f;
            goto L7
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getLastLineWidth() {
            if (this.hasPreview == false) goto L5;
            int r0 = (this.previewX - this.root.padLeft) + AndroidUtilities.dp(86.0f);
        L12:
            Rect r3 = this.padding;
            return (r3.left + r0) + r3.right;
        L5:
            StaticLayout r02 = this.sizeLayout;
            if (r02 != null) goto L8;
        L10:
            int r03 = 0;
        L11:
            r0 = r03 + (((this.buttonX - this.root.padLeft) + this.buttonSize) + this.buttonTextSpacing);
            goto L12
        L8:
            if (r02.getLineCount() <= 0) goto L10;
            StaticLayout r04 = this.sizeLayout;
            r03 = (int) Math.ceil(r04.getLineWidth(r04.getLineCount() - 1));
            goto L11
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            TLRPC.Document r0 = this.document;
            String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r0 != null) goto L5;
            String r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            TLRPC.Document r2 = this.document;
            if (r2 == null) goto L10;
            r1 = AndroidUtilities.formatFileSize(r2.size);
        L10:
            int r03 = (int) Math.ceil(Math.max(this.titlePaint.measureText(r02), this.sizePaint.measureText(r1)));
            if (this.hasPreview == false) goto L13;
            int r12 = AndroidUtilities.dp(107.0f);
        L14:
            int r04 = Math.min(this.maxWidth, Math.max(AndroidUtilities.dp(220.0f), (r12 + r03) + AndroidUtilities.dp(48.0f)));
            Rect r4 = this.padding;
            return (r4.left + r04) + r4.right;
        L13:
            r12 = (this.buttonX + this.buttonSize) + this.buttonTextSpacing;
            goto L14
        L5:
            r02 = FileLoader.getDocumentFileName(r0);
            goto L6
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public int getObserverTag() {
            return this.observerTag;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onAttachedToWindow() {
            View r0 = this.view;
            if (r0 == null) goto L5;
            this.radialProgress.setParent(r0);
            this.previewImage.setParentView(this.view);
        L5:
            this.previewImage.onAttachedToWindow();
            updateButtonState(false);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDetachedFromWindow() {
            this.previewImage.onDetachedFromWindow();
            DownloadController.getInstance(this.root.currentAccount).removeLoadingFileObserver(this);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r11) {
            if (this.document != null) goto L6;
            return;
        L6:
            if (this.layoutWidth == getLayoutWidth()) goto L8;
            rebuildLayouts();
        L8:
            r11.save();
            r11.translate(-this.root.padLeft, 0.0f);
            if (this.hasPreview == true) goto L11;
        L17:
            Canvas r2 = r11;
        L18:
            this.radialProgress.draw(r2);
            TextPaint r112 = this.titlePaint;
            RichMessageLayout r0 = this.root;
            if (r0.isOut() == false) goto L21;
            int r3 = Theme.key_chat_outFileNameText;
        L22:
            r112.setColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r0, r3));
            TextPaint r113 = this.sizePaint;
            RichMessageLayout r02 = this.root;
            if (r02.isOut() == false) goto L25;
            int r32 = Theme.key_chat_outTimeText;
        L26:
            r113.setColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r02, r32));
            if (this.hasPreview == false) goto L30;
            int r114 = this.previewX;
            int r03 = AndroidUtilities.dp(97.0f);
        L29:
            int r115 = r114 + r03;
            int r04 = AndroidUtilities.dp(11.0f);
            int r33 = (this.titleLayout.getHeight() + r04) + AndroidUtilities.dp(2.0f);
            r2.save();
            float r116 = r115;
            r2.translate(r116, r04);
            this.titleLayout.draw(r2);
            r2.restore();
            r2.save();
            r2.translate(r116, r33);
            this.sizeLayout.draw(r2);
            r2.restore();
            if (canShowOptions() == true) goto L34;
            this.optionsHit.setEmpty();
        L39:
            r2.restore();
            return;
        L34:
            if (this.root.isOut() == false) goto L36;
            Drawable r117 = RichMessageLayout.m4825$$Nest$mgetThemedDrawable(this.root, "drawableMsgOutMenu");
        L37:
            int r05 = getMenuX();
            int r34 = AndroidUtilities.dp(7.0f);
            r117.setBounds(r05, r34, r117.getIntrinsicWidth() + r05, r117.getIntrinsicHeight() + r34);
            r117.draw(r2);
            this.optionsHit.set(r05 - AndroidUtilities.dp(8.0f), 0.0f, (r05 + r117.getIntrinsicWidth()) + AndroidUtilities.dp(8.0f), AndroidUtilities.dp(54.0f));
            goto L39
        L36:
            r117 = Theme.chat_msgInMenuDrawable;
            goto L37
        L30:
            r114 = this.buttonX + this.buttonSize;
            r03 = this.buttonTextSpacing;
            goto L29
        L25:
            r32 = Theme.key_chat_inTimeText;
            goto L26
        L21:
            r3 = Theme.key_chat_inFileNameText;
            goto L22
        L11:
            if (this.previewImage.draw(r11) == true) goto L17;
            Paint r06 = this.previewBackgroundPaint;
            RichMessageLayout r22 = this.root;
            if (r22.isOut() == false) goto L15;
            int r35 = Theme.key_chat_outFileBackground;
        L16:
            r06.setColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r22, r35));
            r2 = r11;
            r2.drawRoundRect(this.previewX, AndroidUtilities.dp(10.0f), this.previewX + AndroidUtilities.dp(86.0f), AndroidUtilities.dp(96.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.previewBackgroundPaint);
            goto L18
        L15:
            r35 = Theme.key_chat_inFileBackground;
            goto L16
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onFailedDownload(String r1, boolean r2) {
            updateButtonState(true);
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onProgressDownload(String r3, long r4, long r6) {
            RadialProgress2 r32 = this.radialProgress;
            if (r6 > 0) goto L5;
            float r42 = 0.0f;
        L6:
            r32.setProgress(r42, true);
            if (this.buttonState == 2) goto L10;
            updateButtonState(true);
            return;
        L10:
            return;
        L5:
            r42 = Math.min(1.0f, r4 / r6);
            goto L6
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onSuccessDownload(String r3) {
            this.radialProgress.setProgress(1.0f, true);
            updateButtonState(true);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r9) {
            float r0 = r9.getX() + this.root.padLeft;
            float r1 = r9.getY();
            boolean r2 = this.optionsHit.contains(r0, r1);
            if (r9.getActionMasked() != 0) goto L8;
            if (r2 == false) goto L8;
            this.optionsPressed = true;
            return true;
        L8:
            if (this.optionsPressed == false) goto L35;
            if (r9.getActionMasked() != 2) goto L15;
            if (r2 == true) goto L15;
            this.optionsPressed = false;
            return true;
        L15:
            if (r9.getActionMasked() == 1) goto L19;
            if (r9.getActionMasked() != 3) goto L35;
        L19:
            if (r9.getActionMasked() != 1) goto L22;
            if (r2 == false) goto L22;
            boolean r92 = true;
        L23:
            this.optionsPressed = false;
            if (r92 == true) goto L26;
        L33:
            return true;
        L26:
            if (this.document == null) goto L33;
            if (canShowOptions() == false) goto L33;
            View r93 = this.view;
            if (r93 == null) goto L32;
            r93.playSoundEffect(0);
        L32:
            RichMessageLayout.m4821$$Nest$fgetdelegate(this.root).didPressRichDocumentOptions(RichMessageLayout.m4820$$Nest$fgetcell(this.root), this.document, ((RichMessageLayout.m4820$$Nest$fgetcell(this.root).getTextX() + this.padding.left) - this.root.padLeft) + getMenuX(), ((RichMessageLayout.m4820$$Nest$fgetcell(this.root).getTextY() + this.currY) + this.padding.top) + AndroidUtilities.dp(7.0f));
        L22:
            r92 = false;
        L35:
            if (this.hasPreview == false) goto L37;
            int r22 = this.previewX;
        L39:
            if (r0 >= r22) goto L41;
        L51:
            boolean r02 = false;
        L53:
            if (r9.getActionMasked() != 0) goto L58;
            if (r02 == false) goto L58;
            this.pressed = true;
            return true;
        L58:
            if (r9.getActionMasked() != 1) goto L69;
            if (this.pressed == false) goto L69;
            this.pressed = false;
            if (r02 == false) goto L67;
            View r94 = this.view;
            if (r94 == null) goto L66;
            r94.playSoundEffect(0);
        L66:
            press();
        L67:
            return true;
        L69:
            if (r9.getActionMasked() != 3) goto L72;
            this.pressed = false;
        L72:
            return this.pressed;
        L41:
            if (r0 > (this.layoutWidth - AndroidUtilities.dp(12.0f))) goto L51;
            if (r1 < AndroidUtilities.dp(10.0f)) goto L51;
            if (this.hasPreview == false) goto L47;
            float r03 = 96.0f;
        L49:
            if (r1 > AndroidUtilities.dp(r03)) goto L51;
            r02 = true;
            goto L53
        L47:
            r03 = 53.0f;
            goto L49
        L37:
            r22 = this.buttonX;
            goto L39
        }

        public void updateButtonState(boolean r7) {
            boolean r0 = this.hasPreview;
            RadialProgress2 r1 = this.radialProgress;
            if (r0 == false) goto L6;
            r1.setColorKeys(Theme.key_chat_mediaLoaderPhoto, Theme.key_chat_mediaLoaderPhotoSelected, Theme.key_chat_mediaLoaderPhotoIcon, Theme.key_chat_mediaLoaderPhotoIconSelected);
            this.radialProgress.setProgressColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(this.root, Theme.key_chat_mediaProgress));
        L26:
            String r02 = FileLoader.getAttachFileName(this.document);
            File r12 = path();
            int r2 = 4;
            if (r12 == null) goto L36;
            if (r12.exists() == false) goto L36;
            DownloadController.getInstance(this.root.currentAccount).removeLoadingFileObserver(this);
            this.buttonState = 0;
            RadialProgress2 r03 = this.radialProgress;
            if (this.hasPreview == true) goto L34;
            r2 = 5;
        L34:
            r03.setIcon(r2, false, r7);
        L46:
            View r6 = this.view;
            if (r6 == null) goto L50;
            r6.invalidate();
            return;
        L50:
            return;
        L36:
            if (TextUtils.isEmpty(r02) == true) goto L45;
            DownloadController.getInstance(this.root.currentAccount).addLoadingFileObserver(r02, null, this);
            float r4 = 0.0f;
            if (FileLoader.getInstance(this.root.currentAccount).isLoadingFile(r02) == false) goto L44;
            this.buttonState = 2;
            Float r04 = ImageLoader.getInstance().getFileProgress(r02);
            RadialProgress2 r13 = this.radialProgress;
            if (r04 == null) goto L43;
            r4 = r04.floatValue();
        L43:
            r13.setProgress(r4, r7);
            this.radialProgress.setIcon(3, true, r7);
            goto L46
        L44:
            this.buttonState = 1;
            this.radialProgress.setProgress(0.0f, r7);
            this.radialProgress.setIcon(2, false, r7);
            goto L46
        L45:
            this.radialProgress.setIcon(4, false, r7);
            goto L46
        L6:
            if (this.root.isOut() == false) goto L8;
            int r05 = Theme.key_chat_outLoader;
        L10:
            if (this.root.isOut() == false) goto L12;
            int r22 = Theme.key_chat_outLoaderSelected;
        L14:
            if (this.root.isOut() == false) goto L16;
            int r3 = Theme.key_chat_outMediaIcon;
        L18:
            if (this.root.isOut() == false) goto L20;
            int r42 = Theme.key_chat_outMediaIconSelected;
        L21:
            r1.setColorKeys(r05, r22, r3, r42);
            RadialProgress2 r06 = this.radialProgress;
            RichMessageLayout r14 = this.root;
            if (r14.isOut() == false) goto L24;
            int r23 = Theme.key_chat_outFileProgress;
        L25:
            r06.setProgressColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r14, r23));
            goto L26
        L24:
            r23 = Theme.key_chat_inFileProgress;
            goto L25
        L20:
            r42 = Theme.key_chat_inMediaIconSelected;
            goto L21
        L16:
            r3 = Theme.key_chat_inMediaIcon;
            goto L18
        L12:
            r22 = Theme.key_chat_inLoaderSelected;
            goto L14
        L8:
            r05 = Theme.key_chat_inLoader;
            goto L10
        }
    }

    public static class RichMapBlock extends RichBlock {
        private static Paint mapBgPaint;
        public final TL_iv.pageBlockMap block;
        private int currentMapProvider;
        public final ImageReceiver imageReceiver;
        private final int imgHeight;
        private final int imgWidth;
        private boolean photoPressed;
        private Drawable redPinIcon;

        public RichMapBlock(RichMessageLayout r5, Rect r6, int r7, TL_iv.pageBlockMap r8) {
            super(r5, r6, r7);
            ImageReceiver r52 = new ImageReceiver();
            this.imageReceiver = r52;
            this.block = r8;
            r52.setAllowLoadingOnAttachedOnly(true);
            int r72 = r8.w;
            int r0 = 100;
            if (r72 > 0) goto L6;
            r72 = 100;
        L6:
            int r82 = r8.h;
            if (r82 <= 0) goto L9;
            r0 = r82;
        L9:
            int r83 = this.maxWidth;
            int r1 = (int) ((r83 / Math.max(1, r72)) * r0);
            Point r2 = AndroidUtilities.displaySize;
            int r22 = (int) (Math.max(r2.x, r2.y) * 0.55f);
            if (r1 <= r22) goto L12;
            r83 = (int) ((r22 / Math.max(1, r0)) * r72);
            r1 = r22;
        L12:
            this.imgWidth = r83;
            this.imgHeight = r1;
            r52.setImageCoords(0.0f, 0.0f, r83, r1);
            applyImage();
        }

        private void applyImage() {
            if (this.block.geo == null) goto L21;
            int r2 = this.root.currentAccount;
            int r1 = MessagesController.getInstance(r2).mapProvider;
            this.currentMapProvider = r1;
            float r3 = this.imgWidth;
            float r4 = AndroidUtilities.density;
            int r7 = (int) (r3 / r4);
            int r8 = (int) (this.imgHeight / r4);
            TL_iv.pageBlockMap r32 = this.block;
            int r5 = r32.zoom;
            if (r5 <= 0) goto L8;
        L7:
            int r10 = r5;
            if (r1 != 2) goto L15;
            WebFile r12 = WebFile.createWithGeoPoint(r32.geo, r7, r8, r10, Math.min(2, (int) Math.ceil(r4)));
            if (r12 == null) goto L19;
            this.imageReceiver.setImage(ImageLocation.getForWebFile(r12), null, null, null, this.root.messageObject, 0);
            return;
        L19:
            return;
        L15:
            TLRPC.GeoPoint r13 = r32.geo;
            String r132 = AndroidUtilities.formapMapUrl(r2, r13.lat, r13._long, r7, r8, true, r10, -1);
            if (r132 == null) goto L20;
            this.imageReceiver.setImage(r132, null, null, null, 0);
            return;
        L20:
            return;
        L8:
            r5 = 15;
            goto L7
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            Rect r0 = this.padding;
            return (r0.top + this.imgHeight) + r0.bottom;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getLastLineWidth() {
            return getMinWidth();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            Rect r0 = this.padding;
            return (r0.left + this.imgWidth) + r0.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onAttachedToWindow() {
            this.imageReceiver.setParentView(this.view);
            this.imageReceiver.onAttachedToWindow();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDetachedFromWindow() {
            this.imageReceiver.onDetachedFromWindow();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r10) {
            if (mapBgPaint != null) goto L5;
            mapBgPaint = new Paint(1);
        L5:
            mapBgPaint.setColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(this.root, Theme.key_chat_inLocationBackground));
            boolean r0 = isInQuote();
            int r1 = 0;
            if (r0 == false) goto L8;
            int r2 = 0;
        L9:
            if (r0 == true) goto L12;
            r1 = this.root.padRight;
        L12:
            float r4 = -r2;
            r10.drawRect(r4, 0.0f, this.imgWidth + r1, this.imgHeight, mapBgPaint);
            Drawable r102 = Theme.chat_locationDrawable[this.root.isOut() ? 1 : 0];
            if (r102 == null) goto L15;
            int r5 = r102.getIntrinsicWidth();
            int r6 = r102.getIntrinsicHeight();
            int r7 = (this.imgWidth - r5) / 2;
            int r8 = (this.imgHeight - r6) / 2;
            r102.setBounds(r7, r8, r5 + r7, r6 + r8);
            r102.draw(r10);
        L15:
            this.imageReceiver.setImageCoords(r4, 0.0f, (this.imgWidth + r2) + r1, this.imgHeight);
            this.imageReceiver.draw(r10);
            if (this.currentMapProvider == 2) goto L18;
            return;
        L18:
            if (this.imageReceiver.hasNotThumb() == true) goto L20;
            return;
        L20:
            if (this.redPinIcon != null) goto L25;
            View r103 = this.view;
            if (r103 == null) goto L25;
            this.redPinIcon = ContextCompat.getDrawable(r103.getContext(), R.drawable.map_pin).mutate();
        L25:
            if (this.redPinIcon == null) goto L30;
            int r104 = (int) (r10.getIntrinsicWidth() * 0.8f);
            int r12 = (int) (this.redPinIcon.getIntrinsicHeight() * 0.8f);
            int r22 = (this.imgWidth - r104) / 2;
            int r42 = (this.imgHeight / 2) - r12;
            this.redPinIcon.setAlpha((int) (this.imageReceiver.getCurrentAlpha() * 255.0f));
            this.redPinIcon.setBounds(r22, r42, r104 + r22, r12 + r42);
            this.redPinIcon.draw(r10);
            return;
        L30:
            return;
        L8:
            r2 = this.root.padLeft;
            goto L9
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r10) {
            int r2 = r10.getActionMasked();
            float r3 = r10.getX() - this.padding.left;
            float r102 = r10.getY() - this.padding.top;
            if (r3 >= 0.0f) goto L5;
        L11:
            boolean r103 = false;
        L12:
            if (r2 != 0) goto L17;
            if (r103 == false) goto L16;
            this.photoPressed = true;
            return true;
        L16:
            return false;
        L17:
            if (r2 != 1) goto L34;
            if (this.photoPressed == false) goto L32;
            this.photoPressed = false;
            if (r103 == false) goto L32;
            if (this.block.geo == null) goto L32;
            View r104 = this.view;
            if (r104 == null) goto L32;
            r104.playSoundEffect(0);
            TLRPC.GeoPoint r105 = this.block.geo;     // Catch: Exception -> L29
            double r22 = r105.lat;     // Catch: Exception -> L29
            double r4 = r105._long;     // Catch: Exception -> L29
            this.view.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("geo:" + r22 + "," + r4 + "?q=" + r22 + "," + r4)));     // Catch: Exception -> L29
        L31:
            return true;
        L29:
            e = move-exception;
            FileLog.e(e);
        L32:
            return false;
        L34:
            if (r2 != 3) goto L37;
            this.photoPressed = false;
        L37:
            return this.photoPressed;
        L5:
            if (r3 > this.imgWidth) goto L11;
            if (r102 < 0.0f) goto L11;
            if (r102 > this.imgHeight) goto L11;
            r103 = true;
            goto L12
        }
    }

    public static class RichMathBlock extends RichBlock {
        private static final int HPAD = 0;
        private static final int VPAD = 8;
        private Bitmap bitmap;
        private final TL_iv.pageBlockMath block;
        private int contentH;
        private int contentW;
        private final int contentWidth;
        private int downScrollX;
        private float downX;
        private boolean dragging;
        private final Runnable flingTick;
        private int maxFlingVelocity;
        private final int maxScrollX;
        private int minFlingVelocity;
        private final Paint paint;
        private int scrollX;
        private OverScroller scroller;
        private int touchSlop;
        private VelocityTracker velocityTracker;
        private final int viewportWidth;

        /* renamed from: org.telegram.messenger.RichMessageLayout$RichMathBlock$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ RichMathBlock this$0;

            public AnonymousClass1(RichMathBlock r1) {
                this.this$0 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (RichMathBlock.m4843$$Nest$fgetscroller(this.this$0) == null) goto L22;
                RichMathBlock r0 = this.this$0;
                if (r0.view != null) goto L8;
                return;
            L8:
                if (RichMathBlock.m4843$$Nest$fgetscroller(r0).computeScrollOffset() == false) goto L23;
                int r02 = RichMathBlock.m4843$$Nest$fgetscroller(this.this$0).getCurrX();
                if (r02 >= 0) goto L13;
                r02 = 0;
            L13:
                if (r02 <= RichMathBlock.m4841$$Nest$fgetmaxScrollX(this.this$0)) goto L16;
                r02 = RichMathBlock.m4841$$Nest$fgetmaxScrollX(this.this$0);
            L16:
                if (r02 == RichMathBlock.m4842$$Nest$fgetscrollX(this.this$0)) goto L19;
                RichMathBlock.m4844$$Nest$fputscrollX(this.this$0, r02);
                this.this$0.view.invalidate();
            L19:
                if (RichMathBlock.m4843$$Nest$fgetscroller(this.this$0).isFinished() == true) goto L24;
                this.this$0.view.postOnAnimation(this);
                return;
            L24:
                return;
            L23:
                return;
            }
        }

        /* renamed from: -$$Nest$fgetmaxScrollX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m4841$$Nest$fgetmaxScrollX(RichMathBlock r0) {
            return r0.maxScrollX;
        }

        /* renamed from: -$$Nest$fgetscrollX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m4842$$Nest$fgetscrollX(RichMathBlock r0) {
            return r0.scrollX;
        }

        /* renamed from: -$$Nest$fgetscroller, reason: not valid java name */
        public static /* bridge */ /* synthetic */ OverScroller m4843$$Nest$fgetscroller(RichMathBlock r0) {
            return r0.scroller;
        }

        /* renamed from: -$$Nest$fputscrollX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4844$$Nest$fputscrollX(RichMathBlock r0, int r1) {
            r0.scrollX = r1;
        }

        public RichMathBlock(RichMessageLayout r2, Rect r3, int r4, TL_iv.pageBlockMath r5) {
            super(r2, r3, r4);
            this.paint = new Paint(3);
            this.flingTick = new AnonymousClass1(this);
            this.block = r5;
            int r32 = this.maxWidth;
            this.viewportWidth = r32;
            if (r5 != null) goto L5;
        L7:
            Latex r22 = null;
        L8:
            if (r22 == null) goto L10;
            this.bitmap = r22.bitmap;
            this.contentW = r22.width;
            this.contentH = r22.height;
        L10:
            int r23 = this.contentW + (AndroidUtilities.dp(0.0f) * 2);
            this.contentWidth = r23;
            this.maxScrollX = Math.max(0, r23 - r32);
            return;
        L5:
            if (TextUtils.isEmpty(r5.source) == true) goto L7;
            r22 = Latex.render(r5.source, AndroidUtilities.dp(RichMessageLayout.m4823$$Nest$fgetfontSize(r2) + 4), false);
            goto L8
        }

        private void ensureTouchConfig() {
            if (this.touchSlop != 0) goto L8;
            View r0 = this.view;
            if (r0 == null) goto L8;
            ViewConfiguration r02 = ViewConfiguration.get(r0.getContext());
            this.touchSlop = r02.getScaledTouchSlop();
            this.minFlingVelocity = r02.getScaledMinimumFlingVelocity();
            this.maxFlingVelocity = r02.getScaledMaximumFlingVelocity();
        L8:
            if (this.scroller == null) goto L10;
            return;
        L10:
            if (this.view == null) goto L14;
            this.scroller = new OverScroller(this.view.getContext());
            return;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void appendAccessibilityText(SpannableStringBuilder r2) {
            TL_iv.pageBlockMath r0 = this.block;
            if (r0 != null) goto L5;
            return;
        L5:
            if (TextUtils.isEmpty(r0.source) == true) goto L9;
            r2.append(this.block.source);
            return;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean canDragHorizontally() {
            if (this.maxScrollX <= 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            return ((this.padding.top + this.contentH) + (AndroidUtilities.dp(8.0f) * 2)) + this.padding.bottom;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getLastLineWidth() {
            return getMinWidth();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            return (this.padding.left + Math.min(this.viewportWidth, this.contentWidth)) + this.padding.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean isHorizontallyDragging() {
            if (this.dragging == true) goto L11;
            OverScroller r1 = this.scroller;
            if (r1 != null) goto L7;
            return false;
        L7:
            if (r1.isFinished() == false) goto L14;
            return false;
        L14:
            return true;
        L11:
            return true;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r14) {
            if (this.bitmap != null) goto L5;
            return;
        L5:
            Paint r0 = this.paint;
            RichMessageLayout r1 = this.root;
            if (r1.isOut() == false) goto L8;
            int r2 = Theme.key_chat_messageTextOut;
        L9:
            r0.setColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r1, r2));
            int r02 = this.contentH + (AndroidUtilities.dp(8.0f) * 2);
            int r22 = this.maxScrollX;
            RichMessageLayout r4 = this.root;
            if (r22 <= 0) goto L12;
            float r7 = -r4.padLeft;
            int r23 = r4.getMinWidth() + this.root.padRight;
            Rect r42 = this.padding;
            Canvas r6 = r14;
            r6.saveLayerAlpha(r7, 0.0f, (r23 - r42.left) - r42.right, r02, 255, 31);
            r6.save();
            r6.translate(AndroidUtilities.dp(0.0f) - this.scrollX, AndroidUtilities.dp(8.0f));
        L13:
            r6.drawBitmap(this.bitmap, 0.0f, 0.0f, this.paint);
            if (this.maxScrollX <= 0) goto L16;
            r6.restore();
            RectF r142 = AndroidUtilities.rectTmp;
            int r12 = this.root.padLeft;
            float r03 = r02;
            r142.set(-r12, 0.0f, (-r12) + AndroidUtilities.dp(12.0f), r03);
            this.root.clip.draw(r6, r142, 0, 1.0f);
            int r13 = this.root.getMinWidth() + this.root.padRight;
            Rect r24 = this.padding;
            r142.set(r1 - AndroidUtilities.dp(12.0f), 0.0f, (r13 - r24.left) - r24.right, r03);
            this.root.clip.draw(r6, r142, 2, 1.0f);
        L16:
            r6.restore();
            return;
        L12:
            r6 = r14;
            r6.save();
            r6.translate(((r4.getMinWidth() / 2.0f) - this.padding.left) - (this.contentW / 2.0f), AndroidUtilities.dp(8.0f));
            goto L13
        L8:
            r2 = Theme.key_chat_messageTextIn;
            goto L9
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r15) {
            int r1 = 0;
            if (this.maxScrollX > 0) goto L5;
            return false;
        L5:
            int r0 = r15.getActionMasked();
            if (r0 != 0) goto L19;
            ensureTouchConfig();
            OverScroller r02 = this.scroller;
            if (r02 != null) goto L10;
        L12:
            this.downX = r15.getX();
            this.downScrollX = this.scrollX;
            this.dragging = false;
            VelocityTracker r03 = this.velocityTracker;
            if (r03 != null) goto L15;
            this.velocityTracker = VelocityTracker.obtain();
        L16:
            this.velocityTracker.addMovement(r15);
            return true;
        L15:
            r03.clear();
            goto L16
        L10:
            if (r02.isFinished() == true) goto L12;
            this.scroller.forceFinished(true);
            goto L12
        L19:
            if (r0 != 2) goto L44;
            VelocityTracker r04 = this.velocityTracker;
            if (r04 == null) goto L23;
            r04.addMovement(r15);
        L23:
            float r152 = r15.getX() - this.downX;
            if (this.dragging == true) goto L29;
            if (Math.abs(r152) <= this.touchSlop) goto L29;
            this.dragging = true;
            requestDisallowParentIntercept(true);
        L29:
            if (this.dragging == false) goto L43;
            int r153 = (int) (this.downScrollX - r152);
            if (r153 < 0) goto L34;
            r1 = r153;
        L34:
            int r154 = this.maxScrollX;
            if (r1 <= r154) goto L38;
            r1 = r154;
        L38:
            if (r1 == this.scrollX) goto L42;
            this.scrollX = r1;
            View r14 = this.view;
            if (r14 == null) goto L42;
            r14.invalidate();
        L42:
            return true;
        L43:
            return false;
        L44:
            if (r0 != 1) goto L46;
        L49:
            boolean r3 = this.dragging;
            this.dragging = false;
            if (r3 == false) goto L62;
            requestDisallowParentIntercept(false);
            if (r0 != 1) goto L62;
            VelocityTracker r4 = this.velocityTracker;
            if (r4 == null) goto L62;
            if (this.scroller == null) goto L62;
            if (this.view == null) goto L62;
            r4.addMovement(r15);
            this.velocityTracker.computeCurrentVelocity(1000, this.maxFlingVelocity);
            float r155 = -this.velocityTracker.getXVelocity();
            if (Math.abs(r155) <= this.minFlingVelocity) goto L62;
            this.scroller.fling(this.scrollX, 0, (int) r155, 0, 0, this.maxScrollX, 0, 0);
            this.view.postOnAnimation(this.flingTick);
        L62:
            VelocityTracker r156 = this.velocityTracker;
            if (r156 == null) goto L65;
            r156.recycle();
            this.velocityTracker = null;
        L65:
            if (r3 == true) goto L69;
            if (r0 == 1) goto L69;
            return false;
        L69:
            return true;
        L46:
            if (r0 == 3) goto L49;
            return false;
        }
    }

    public static abstract class RichMediaBlock extends RichBlock implements DownloadController.FileDownloadProgressListener {
        private static ColorMatrixColorFilter fancyBlurFilter;
        private static Paint mediaBgPaint;
        protected boolean autoDownload;
        public final ImageReceiver blurImageReceiver;
        private Bitmap blurSource;
        private boolean buttonPressed;
        private final int buttonSize;
        private int buttonState;
        private int buttonX;
        private int buttonY;
        private final Path clipPath;
        public final boolean first;
        public final ImageReceiver imageReceiver;
        protected int imgHeight;
        protected int imgWidth;
        protected boolean mediaForced;
        private final int observerTag;
        private boolean photoPressed;
        protected RadialProgress2 radialProgress;
        private final SpoilerReveal spoilerReveal;

        /* renamed from: org.telegram.messenger.RichMessageLayout$RichMediaBlock$1, reason: invalid class name */
        public class AnonymousClass1 implements ImageReceiver.ImageReceiverDelegate {
            final /* synthetic */ RichMediaBlock this$0;

            public AnonymousClass1(RichMediaBlock r1) {
                this.this$0 = r1;
            }

            @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
            public void didSetImage(ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
            }

            @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
            public void onAnimationReady(ImageReceiver r1) {
                this.this$0.updateButtonState(true);
            }
        }

        public RichMediaBlock(RichMessageLayout r2, Rect r3, int r4, boolean r5) {
            super(r2, r3, r4);
            ImageReceiver r32 = new ImageReceiver();
            this.imageReceiver = r32;
            ImageReceiver r42 = new ImageReceiver();
            this.blurImageReceiver = r42;
            this.buttonState = -1;
            this.buttonSize = AndroidUtilities.dp(48.0f);
            this.clipPath = new Path();
            this.spoilerReveal = new SpoilerReveal();
            this.first = r5;
            this.observerTag = DownloadController.getInstance(r2.currentAccount).generateObserverTag();
            r32.setAllowLoadingOnAttachedOnly(true);
            r42.setAllowLoadingOnAttachedOnly(true);
            r32.setDelegate(new AnonymousClass1(this));
        }

        private int availWidth() {
            int r0 = this.root.getMinWidth();
            Rect r2 = this.padding;
            return (r0 - r2.left) - r2.right;
        }

        private void didPressButton(boolean r6) {
            int r0 = this.buttonState;
            if (r0 != 0) goto L15;
            this.mediaForced = true;
            RadialProgress2 r02 = this.radialProgress;
            if (r02 == null) goto L7;
            r02.setProgress(0.0f, r6);
        L7:
            applyImage(true);
            this.buttonState = 1;
            RadialProgress2 r03 = this.radialProgress;
            if (r03 == null) goto L10;
            r03.setIcon(3, true, r6);
        L10:
            View r5 = this.view;
            if (r5 == null) goto L36;
            r5.invalidate();
            return;
        L36:
            return;
        L15:
            if (r0 != 1) goto L23;
            this.mediaForced = false;
            this.imageReceiver.cancelLoadImage();
            this.buttonState = 0;
            RadialProgress2 r04 = this.radialProgress;
            if (r04 == null) goto L19;
            r04.setIcon(2, false, r6);
        L19:
            View r52 = this.view;
            if (r52 == null) goto L37;
            r52.invalidate();
            return;
        L37:
            return;
        L23:
            if (r0 != 2) goto L31;
            this.mediaForced = true;
            this.imageReceiver.setAllowStartAnimation(true);
            applyImage(true);
            this.imageReceiver.startAnimation();
            this.buttonState = -1;
            RadialProgress2 r05 = this.radialProgress;
            if (r05 == null) goto L27;
            r05.setIcon(4, false, r6);
        L27:
            View r53 = this.view;
            if (r53 == null) goto L38;
            r53.invalidate();
            return;
        L38:
            return;
        L31:
            if (r0 == 3) goto L33;
            return;
        L33:
            if (RichMessageLayout.m4821$$Nest$fgetdelegate(this.root) == null) goto L40;
            RichMessageLayout.m4821$$Nest$fgetdelegate(this.root).openArticlePhoto(RichMessageLayout.m4820$$Nest$fgetcell(this.root), getBlock());
            return;
        }

        private void drawMediaSpoiler(Canvas r12) {
            if (this.spoilerReveal.fullyRevealed() == true) goto L23;
            prepareBlurImage();
            float r0 = this.imageReceiver.getImageX();
            float r1 = this.imageReceiver.getImageY();
            float r2 = this.imageReceiver.getImageWidth();
            float r3 = this.imageReceiver.getImageHeight();
            if (r2 > 0.0f) goto L8;
            return;
        L8:
            if (r3 <= 0.0f) goto L24;
            r12.save();
            r12.clipRect(r0, r1, r0 + r2, r1 + r3);
            this.spoilerReveal.clipOut(r12);
            if (this.blurImageReceiver.getBitmap() == null) goto L13;
            updateRoundRadius(this.blurImageReceiver, false);
            this.blurImageReceiver.setImageCoords(r0, r1, r2, r3);
            this.blurImageReceiver.setAlpha(this.imageReceiver.getCurrentAlpha());
            this.blurImageReceiver.draw(r12);
        L13:
            SpoilerEffect2 r5 = this.root.getMediaSpoilerEffect();
            if (r5 == null) goto L16;
            r12.translate(r0, r1);
            Canvas r6 = r12;
            r5.draw(r6, this.view, Math.round(r2), Math.round(r3), this.imageReceiver.getCurrentAlpha());
        L17:
            r6.restore();
            View r11 = this.view;
            if (r11 == null) goto L22;
            r11.invalidate();
            return;
        L22:
            return;
        L16:
            r6 = r12;
            goto L17
        L24:
            return;
        }

        private void ensureProgress() {
            RadialProgress2 r0 = this.radialProgress;
            if (r0 != null) goto L8;
            View r1 = this.view;
            if (r1 == null) goto L8;
            RadialProgress2 r02 = new RadialProgress2(r1);
            this.radialProgress = r02;
            r02.setProgressColor(-1);
            this.radialProgress.setColors(1711276032, 2130706432, -1, -2500135);
            RadialProgress2 r03 = this.radialProgress;
            int r12 = this.buttonX;
            int r2 = this.buttonY;
            int r5 = this.buttonSize;
            r03.setProgressRect(r12, r2, r12 + r5, r5 + r2);
            return;
        L8:
            if (r0 == null) goto L13;
            View r13 = this.view;
            if (r13 == null) goto L14;
            r0.setParent(r13);
            RadialProgress2 r04 = this.radialProgress;
            int r14 = this.buttonX;
            int r22 = this.buttonY;
            int r52 = this.buttonSize;
            r04.setProgressRect(r14, r22, r14 + r52, r52 + r22);
            return;
        L14:
            return;
        }

        private void prepareBlurImage() {
            if (this.blurImageReceiver.getBitmap() != null) goto L5;
        L7:
            Bitmap r0 = this.imageReceiver.getBitmap();
            if (r0 != null) goto L10;
            return;
        L10:
            if (r0.isRecycled() == false) goto L13;
            return;
        L13:
            if (r0 == this.blurSource) goto L15;
        L17:
            this.blurSource = r0;
            this.blurImageReceiver.setImageBitmap(Utilities.stackBlurBitmapMax(r0, false));
            if (fancyBlurFilter != null) goto L20;
            ColorMatrix r02 = new ColorMatrix();
            AndroidUtilities.multiplyBrightnessColorMatrix(r02, 0.9f);
            AndroidUtilities.adjustSaturationColorMatrix(r02, 0.6f);
            fancyBlurFilter = new ColorMatrixColorFilter(r02);
        L20:
            this.blurImageReceiver.setColorFilter(fancyBlurFilter);
            return;
        L15:
            if (this.blurImageReceiver.getBitmap() == null) goto L17;
            return;
        L5:
            if (this.imageReceiver.getAnimation() == null) goto L7;
        }

        private void startSpoilerReveal() {
            float r5 = this.imageReceiver.getImageWidth();
            float r6 = this.imageReceiver.getImageHeight();
            this.spoilerReveal.start(this.view, this.imageReceiver.getImageX() + (r5 / 2.0f), this.imageReceiver.getImageY() + (r6 / 2.0f), r5, r6);
        }

        private void updateRoundRadius(ImageReceiver r4, boolean r5) {
            if (r5 == false) goto L5;
            r4.setRoundRadius(0);
            return;
        L5:
            int r52 = SharedConfig.bubbleRadius;
            if (r52 <= 2) goto L8;
            int r53 = AndroidUtilities.dp(r52 - 2);
        L9:
            int r0 = Math.min(AndroidUtilities.dp(3.0f), r53);
            if (this.first == true) goto L12;
        L18:
            int r1 = r0;
        L20:
            if (this.first == true) goto L22;
        L26:
            r53 = r0;
        L27:
            r4.setRoundRadius(r1, r53, r0, r0);
            return;
        L22:
            if (this.root.isOut() == false) goto L27;
            if (this.root.isPinnedTop() == true) goto L26;
        L12:
            if (this.root.hasNameOffset() == true) goto L18;
            if (this.root.isOut() == false) goto L16;
        L17:
            r1 = r53;
            goto L20
        L16:
            if (this.root.isPinnedTop() == true) goto L18;
        L8:
            r53 = AndroidUtilities.dp(r52);
            goto L9
        }

        public boolean allowAutoplay() {
            return true;
        }

        public abstract void applyImage(boolean r1);

        public boolean computeAutoDownload() {
            if ((DownloadController.getInstance(this.root.currentAccount).getCurrentDownloadMask() & 1) == 0) goto L5;
            return true;
        L5:
            return false;
        }

        public abstract boolean fileExists();

        public void finishLayout() {
            this.imageReceiver.setImageCoords(0.0f, 0.0f, this.imgWidth, this.imgHeight);
            int r0 = this.imgWidth;
            int r1 = this.buttonSize;
            this.buttonX = (r0 - r1) / 2;
            this.buttonY = (this.imgHeight - r1) / 2;
            boolean r02 = computeAutoDownload();
            this.autoDownload = r02;
            if (r02 == false) goto L5;
        L8:
            boolean r03 = true;
        L9:
            applyImage(r03);
            return;
        L5:
            if (fileExists() == true) goto L8;
            r03 = false;
            goto L9
        }

        public abstract TL_iv.PageBlock getBlock();

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void getBlockAccessibilityElementBounds(int r3, Rect r4) {
            int r32 = this.padding.left + getImageLeft();
            int r0 = ((int) this.currY) + this.padding.top;
            r4.set(r32, r0, this.imgWidth + r32, this.imgHeight + r0);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getBlockAccessibilityElementCount() {
            return 1;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public CharSequence getBlockAccessibilityElementText(int r3) {
            if (isRealVideo() == false) goto L5;
            int r32 = R.string.AttachVideo;
        L6:
            String r33 = LocaleController.getString(r32);
            if (isSpoiler() == true) goto L9;
        L12:
            return r33;
        L9:
            if (this.spoilerReveal.fullyRevealed() == true) goto L12;
            return TextUtils.concat(new CharSequence[]{r33, ", ", LocaleController.getString(R.string.Spoiler)});
        L5:
            r32 = R.string.AttachPhoto;
            goto L6
        }

        public abstract String getFileName();

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            Rect r0 = this.padding;
            return (r0.top + this.imgHeight) + r0.bottom;
        }

        public int getImageLeft() {
            int r0 = availWidth();
            int r1 = this.imgWidth;
            if (r0 > r1) goto L5;
            return 0;
        L5:
            return (r0 - r1) / 2;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getLastLineWidth() {
            return getMinWidth();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            Rect r0 = this.padding;
            return (r0.left + this.imgWidth) + r0.right;
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public int getObserverTag() {
            return this.observerTag;
        }

        public boolean isAnimatedContent() {
            return false;
        }

        public boolean isRealVideo() {
            return false;
        }

        public boolean isSpoiler() {
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onAttachedToWindow() {
            this.imageReceiver.setParentView(this.view);
            this.imageReceiver.onAttachedToWindow();
            this.blurImageReceiver.setParentView(this.view);
            this.blurImageReceiver.onAttachedToWindow();
            updateButtonState(false);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onBlockAccessibilityElementClick(int r2, View r3) {
            if (isSpoiler() == false) goto L9;
            if (this.spoilerReveal.isRevealing() == true) goto L9;
            startSpoilerReveal();
            return true;
        L9:
            if (RichMessageLayout.m4821$$Nest$fgetdelegate(this.root) == null) goto L12;
            RichMessageLayout.m4821$$Nest$fgetdelegate(this.root).openArticlePhoto(RichMessageLayout.m4820$$Nest$fgetcell(this.root), getBlock());
            return true;
        L12:
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDetachedFromWindow() {
            this.imageReceiver.onDetachedFromWindow();
            this.blurImageReceiver.onDetachedFromWindow();
            this.blurSource = null;
            DownloadController.getInstance(this.root.currentAccount).removeLoadingFileObserver(this);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r23) {
            if (mediaBgPaint != null) goto L5;
            Paint r2 = new Paint(1);
            mediaBgPaint = r2;
            r2.setColor(251658240);
        L5:
            int r22 = this.root.getMinWidth();
            Rect r3 = this.padding;
            int r8 = (r22 - r3.left) - r3.right;
            boolean r9 = isInQuote();
            int r24 = AndroidUtilities.dp(2.0f);
            if (r9 == false) goto L8;
            int r11 = 0;
        L9:
            if (r9 == false) goto L11;
            int r12 = 0;
        L13:
            if (availWidth() <= this.imgWidth) goto L15;
            boolean r13 = true;
        L16:
            if (r9 == false) goto L19;
            r23.save();
            this.clipPath.rewind();
            this.clipPath.addRoundRect(0.0f, 0.0f, r8, this.imgHeight, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), Path.Direction.CW);
            r23.clipPath(this.clipPath);
        L19:
            if (this.imageReceiver.hasBitmapImage() == true) goto L21;
        L22:
            r23.drawRect(-r11, 0.0f, r8 + r12, this.imgHeight, mediaBgPaint);
        L24:
            if (r13 == false) goto L29;
            prepareBlurImage();
            updateRoundRadius(this.blurImageReceiver, false);
            updateRoundRadius(this.imageReceiver, true);
            if (this.blurImageReceiver.getBitmap() == null) goto L28;
            this.blurImageReceiver.setImageCoords(-r11, 0.0f, (r11 + r8) + r12, this.imgHeight);
            this.blurImageReceiver.setAlpha(this.imageReceiver.getCurrentAlpha());
            this.blurImageReceiver.draw(r23);
        L28:
            this.imageReceiver.setAspectFit(true);
            this.imageReceiver.setImageCoords(0.0f, 0.0f, availWidth(), this.imgHeight);
        L30:
            this.imageReceiver.draw(r23);
            if (isSpoiler() == false) goto L36;
            if (this.spoilerReveal.fullyRevealed() == true) goto L36;
            drawMediaSpoiler(r23);
        L40:
            if (r9 == false) goto L43;
            r23.restore();
            return;
        L43:
            return;
        L36:
            if (this.radialProgress == null) goto L40;
            if (this.buttonState == (-1)) goto L40;
            int r25 = getImageLeft();
            RadialProgress2 r32 = this.radialProgress;
            int r4 = this.buttonX;
            int r5 = r25 + r4;
            int r6 = this.buttonY;
            int r26 = r25 + r4;
            int r42 = this.buttonSize;
            r32.setProgressRect(r5, r6, r26 + r42, r42 + r6);
            this.radialProgress.draw(r23);
            goto L40
        L29:
            updateRoundRadius(this.imageReceiver, false);
            this.imageReceiver.setAspectFit(false);
            this.imageReceiver.setImageCoords(-r11, 0.0f, (r11 + r8) + r12, this.imgHeight);
            goto L30
        L21:
            if (this.imageReceiver.getCurrentAlpha() == 1.0f) goto L24;
        L15:
            r13 = false;
            goto L16
        L11:
            r12 = this.root.padRight - r24;
            goto L13
        L8:
            r11 = this.root.padLeft - r24;
            goto L9
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onFailedDownload(String r1, boolean r2) {
            updateButtonState(false);
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onProgressDownload(String r4, long r5, long r7) {
            RadialProgress2 r42 = this.radialProgress;
            if (r42 == null) goto L10;
            if (r7 > 0) goto L7;
            float r52 = 0.0f;
        L8:
            r42.setProgress(Math.min(1.0f, r52), true);
            goto L10
        L7:
            r52 = r5 / r7;
        L10:
            if (this.buttonState == 1) goto L13;
            updateButtonState(true);
            return;
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onSuccessDownload(String r3) {
            RadialProgress2 r32 = this.radialProgress;
            if (r32 == null) goto L6;
            r32.setProgress(1.0f, true);
        L6:
            if (isAnimatedContent() == true) goto L8;
        L12:
            updateButtonState(true);
            return;
        L8:
            if (allowAutoplay() == false) goto L10;
        L11:
            applyImage(true);
            goto L12
        L10:
            if (this.mediaForced == false) goto L12;
            goto L11
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r9) {
            int r0 = r9.getActionMasked();
            float r1 = (r9.getX() - this.padding.left) - getImageLeft();
            float r92 = r9.getY() - this.padding.top;
            if (r1 >= 0.0f) goto L5;
        L11:
            boolean r2 = false;
        L12:
            int r3 = this.buttonState;
            if (r3 != (-1)) goto L15;
        L23:
            boolean r93 = false;
        L24:
            if (r0 != 0) goto L38;
            if (r2 == false) goto L34;
            if (r93 == true) goto L30;
            if (r3 == 0) goto L30;
            if (r3 != 2) goto L34;
        L30:
            this.buttonPressed = true;
            View r8 = this.view;
            if (r8 == null) goto L33;
            r8.invalidate();
        L33:
            return true;
        L34:
            if (r2 == false) goto L37;
            this.photoPressed = true;
            return true;
        L37:
            return false;
        L38:
            if (r0 != 1) goto L64;
            if (this.buttonPressed == false) goto L47;
            this.buttonPressed = false;
            View r94 = this.view;
            if (r94 == null) goto L44;
            r94.playSoundEffect(0);
            this.view.invalidate();
        L44:
            didPressButton(true);
            return true;
        L47:
            if (this.photoPressed == false) goto L62;
            this.photoPressed = false;
            if (r2 == false) goto L62;
            View r95 = this.view;
            if (r95 == null) goto L54;
            r95.playSoundEffect(0);
        L54:
            if (isSpoiler() == false) goto L59;
            if (this.spoilerReveal.isRevealing() == true) goto L59;
            startSpoilerReveal();
        L61:
            return true;
        L59:
            if (RichMessageLayout.m4821$$Nest$fgetdelegate(this.root) == null) goto L61;
            RichMessageLayout.m4821$$Nest$fgetdelegate(this.root).openArticlePhoto(RichMessageLayout.m4820$$Nest$fgetcell(this.root), getBlock());
        L62:
            return false;
        L64:
            if (r0 != 3) goto L68;
            this.photoPressed = false;
            this.buttonPressed = false;
            return false;
        L68:
            if (this.photoPressed == false) goto L70;
        L73:
            return true;
        L70:
            if (this.buttonPressed == true) goto L73;
            return false;
        L15:
            if (r1 < this.buttonX) goto L23;
            int r7 = this.buttonSize;
            if (r1 > (r6 + r7)) goto L23;
            if (r92 < this.buttonY) goto L23;
            if (r92 > (r1 + r7)) goto L23;
            r93 = true;
            goto L24
        L5:
            if (r1 > this.imgWidth) goto L11;
            if (r92 < 0.0f) goto L11;
            if (r92 > this.imgHeight) goto L11;
            r2 = true;
            goto L12
        }

        public void updateButtonState(boolean r10) {
            ensureProgress();
            String r0 = getFileName();
            if (TextUtils.isEmpty(r0) == false) goto L8;
            this.buttonState = -1;
            RadialProgress2 r9 = this.radialProgress;
            if (r9 == null) goto L69;
            r9.setIcon(4, false, false);
            return;
        L69:
            return;
        L8:
            AnimatedFileDrawable r1 = this.imageReceiver.getAnimation();
            if (r1 != null) goto L11;
        L15:
            boolean r12 = false;
        L17:
            if (fileExists() == false) goto L19;
        L46:
            DownloadController.getInstance(this.root.currentAccount).removeLoadingFileObserver(this);
            if (isRealVideo() == false) goto L53;
            if (r12 == true) goto L53;
            this.buttonState = 3;
            RadialProgress2 r02 = this.radialProgress;
            if (r02 == null) goto L65;
            r02.setIcon(0, false, r10);
        L65:
            View r92 = this.view;
            if (r92 == null) goto L70;
            r92.invalidate();
            return;
        L70:
            return;
        L53:
            if (isAnimatedContent() == false) goto L62;
            if (r12 == true) goto L62;
            if (allowAutoplay() == true) goto L62;
            if (this.mediaForced == true) goto L62;
            this.buttonState = 2;
            RadialProgress2 r03 = this.radialProgress;
            if (r03 == null) goto L65;
            r03.setIcon(8, false, r10);
        L62:
            this.buttonState = -1;
            RadialProgress2 r04 = this.radialProgress;
            if (r04 == null) goto L65;
            r04.setIcon(4, false, r10);
            goto L65
        L19:
            if (isAnimatedContent() == false) goto L22;
            if (r12 == true) goto L46;
        L22:
            DownloadController.getInstance(this.root.currentAccount).addLoadingFileObserver(r0, null, this);
            float r2 = 0.0f;
            if (this.autoDownload == false) goto L25;
        L37:
            this.buttonState = 1;
            Float r05 = ImageLoader.getInstance().getFileProgress(r0);
            if (r05 == null) goto L40;
            r2 = r05.floatValue();
        L40:
            RadialProgress2 r06 = this.radialProgress;
            if (r06 == null) goto L43;
            r06.setIcon(3, true, r10);
        L43:
            RadialProgress2 r102 = this.radialProgress;
            if (r102 == null) goto L65;
            r102.setProgress(r2, false);
            goto L65
        L25:
            if (this.mediaForced == true) goto L37;
            if (FileLoader.getInstance(this.root.currentAccount).isLoadingFile(r0) == true) goto L37;
            if (isRealVideo() == false) goto L34;
            this.buttonState = 3;
            RadialProgress2 r07 = this.radialProgress;
            if (r07 == null) goto L43;
            r07.setIcon(0, true, r10);
            goto L43
        L34:
            this.buttonState = 0;
            RadialProgress2 r08 = this.radialProgress;
            if (r08 == null) goto L43;
            r08.setIcon(2, true, r10);
            goto L43
        L11:
            if (r1.hasBitmap() == false) goto L13;
        L14:
            r12 = true;
            goto L17
        L13:
            if (this.imageReceiver.isAnimationRunning() == false) goto L15;
            goto L14
        }
    }

    public static class RichPhotoBlock extends RichMediaBlock {
        public final TL_iv.pageBlockPhoto block;
        public final TLRPC.Photo photo;
        public final TLRPC.PhotoSize sizeFull;
        public final TLRPC.PhotoSize strippedSize;

        public RichPhotoBlock(RichMessageLayout r3, Rect r4, int r5, TL_iv.pageBlockPhoto r6, boolean r7) {
            super(r3, r4, r5, r7);
            this.block = r6;
            TLRPC.Photo r32 = r3.getPhoto(r6.photo_id);
            this.photo = r32;
            if (r32 == null) goto L5;
            this.sizeFull = FileLoader.getClosestPhotoSizeWithSize(r32.sizes, AndroidUtilities.getPhotoSize());
            this.strippedSize = FileLoader.getStrippedPhotoSize(r32.sizes);
        L6:
            TLRPC.PhotoSize r33 = this.sizeFull;
            int r42 = 100;
            if (r33 == null) goto L9;
            int r52 = r33.w;
        L10:
            if (r33 == null) goto L12;
            r42 = r33.h;
        L12:
            int r34 = this.maxWidth;
            int r62 = (int) ((r34 / Math.max(1, r52)) * r42);
            Point r0 = AndroidUtilities.displaySize;
            int r02 = (int) (Math.max(r0.x, r0.y) * 0.55f);
            if (r62 <= r02) goto L15;
            r34 = (int) ((r02 / Math.max(1, r42)) * r52);
            r62 = r02;
        L15:
            this.imgWidth = r34;
            this.imgHeight = r62;
            finishLayout();
            return;
        L9:
            r52 = 100;
            goto L10
        L5:
            this.sizeFull = null;
            this.strippedSize = null;
            goto L6
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichMediaBlock
        public void applyImage(boolean r15) {
            TLRPC.Photo r0 = this.photo;
            if (r0 != null) goto L5;
            return;
        L5:
            if (this.sizeFull == null) goto L19;
            TLRPC.PhotoSize r1 = this.strippedSize;
            if (r1 == null) goto L11;
            ImageLocation r02 = ImageLocation.getForPhoto(r1, r0);
        L10:
            ImageLocation r6 = r02;
            ImageReceiver r12 = this.imageReceiver;
            if (r15 == false) goto L16;
            r12.setImage(null, null, ImageLocation.getForPhoto(this.sizeFull, this.photo), null, r6, "b1", null, this.sizeFull.size, null, this.root.messageObject, 1);
            return;
        L16:
            r12.setImage(null, null, null, null, r6, "b1", null, this.sizeFull.size, null, this.root.messageObject, 1);
            return;
        L11:
            r02 = null;
            goto L10
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichMediaBlock
        public boolean fileExists() {
            if (this.sizeFull != null) goto L5;
            return true;
        L5:
            File r0 = FileLoader.getInstance(this.root.currentAccount).getPathToAttach(this.sizeFull, true);
            File r4 = FileLoader.getInstance(this.root.currentAccount).getPathToAttach(this.sizeFull, false);
            if (r0.exists() == true) goto L12;
            if (r4 != null) goto L9;
        L11:
            return false;
        L9:
            if (r4.exists() == false) goto L11;
        L12:
            return true;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichMediaBlock
        public TL_iv.PageBlock getBlock() {
            return this.block;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichMediaBlock
        public String getFileName() {
            return FileLoader.getAttachFileName(this.sizeFull);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichMediaBlock
        public boolean isSpoiler() {
            TL_iv.pageBlockPhoto r0 = this.block;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.spoiler == false) goto L10;
            return true;
        L10:
            return false;
        }
    }

    public static class RichPreformattedBlock extends RichBlock {
        private static final int BACKGROUND_OUTER_VPAD = 7;
        private static final int HPAD = 0;
        private static final int SCROLLBAR_HEIGHT = 5;
        private static final int SCROLLBAR_HPAD = 6;
        private static final int SCROLLBAR_VPAD = 7;
        private static final int VPAD = 8;
        private final Paint bgPaint;
        public SpannableString content;
        private final int contentWidth;
        private int downScrollX;
        private float downX;
        private boolean dragging;
        private final Runnable flingTick;
        public final String language;
        private int maxFlingVelocity;
        private final int maxScrollX;
        private int minFlingVelocity;
        public String plain;
        private int scrollX;
        private OverScroller scroller;
        public final Text text;
        private boolean textHandlingTouch;
        public final Text[] texts;
        private int touchSlop;
        private VelocityTracker velocityTracker;
        private final int viewportWidth;

        /* renamed from: org.telegram.messenger.RichMessageLayout$RichPreformattedBlock$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ RichPreformattedBlock this$0;

            public AnonymousClass1(RichPreformattedBlock r1) {
                this.this$0 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (RichPreformattedBlock.m4847$$Nest$fgetscroller(this.this$0) == null) goto L22;
                RichPreformattedBlock r0 = this.this$0;
                if (r0.view != null) goto L8;
                return;
            L8:
                if (RichPreformattedBlock.m4847$$Nest$fgetscroller(r0).computeScrollOffset() == false) goto L23;
                int r02 = RichPreformattedBlock.m4847$$Nest$fgetscroller(this.this$0).getCurrX();
                if (r02 >= 0) goto L13;
                r02 = 0;
            L13:
                if (r02 <= RichPreformattedBlock.m4845$$Nest$fgetmaxScrollX(this.this$0)) goto L16;
                r02 = RichPreformattedBlock.m4845$$Nest$fgetmaxScrollX(this.this$0);
            L16:
                if (r02 == RichPreformattedBlock.m4846$$Nest$fgetscrollX(this.this$0)) goto L19;
                RichPreformattedBlock.m4848$$Nest$fputscrollX(this.this$0, r02);
                RichPreformattedBlock r03 = this.this$0;
                r03.placeTexts(r03.layoutX, r03.layoutY, r03.layoutRow);
                this.this$0.view.invalidate();
            L19:
                if (RichPreformattedBlock.m4847$$Nest$fgetscroller(this.this$0).isFinished() == true) goto L24;
                this.this$0.view.postOnAnimation(this);
                return;
            L24:
                return;
            L23:
                return;
            }
        }

        /* renamed from: -$$Nest$fgetmaxScrollX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m4845$$Nest$fgetmaxScrollX(RichPreformattedBlock r0) {
            return r0.maxScrollX;
        }

        /* renamed from: -$$Nest$fgetscrollX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m4846$$Nest$fgetscrollX(RichPreformattedBlock r0) {
            return r0.scrollX;
        }

        /* renamed from: -$$Nest$fgetscroller, reason: not valid java name */
        public static /* bridge */ /* synthetic */ OverScroller m4847$$Nest$fgetscroller(RichPreformattedBlock r0) {
            return r0.scroller;
        }

        /* renamed from: -$$Nest$fputscrollX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4848$$Nest$fputscrollX(RichPreformattedBlock r0, int r1) {
            r0.scrollX = r1;
        }

        public RichPreformattedBlock(RichMessageLayout r17, Rect r18, int r19, TL_iv.pageBlockPreformatted r20, RichPreformattedBlock r21) {
            super(r17, r18, r19);
            this.bgPaint = new Paint(1);
            this.flingTick = new AnonymousClass1(this);
            this.viewportWidth = this.maxWidth;
            this.language = r20.language;
            String r3 = RichMessageLayout.getString(r20.text);
            this.plain = r3;
            if (r3 != null) goto L5;
            this.plain = _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            SpannableString r32 = new SpannableString(this.plain);
            this.content = r32;
            if (r32.length() <= 0) goto L35;
            this.content.setSpan(new StyleSpan(r17, 8), 0, this.content.length(), 33);
            if (r21 == null) goto L33;
            CharSequence r33 = r21.content;
            if ((r33 instanceof CodeHighlighting.LockedWithFallbackSpannableString) == true) goto L12;
        L15:
            if (r33 == null) goto L33;
            if (r33.length() <= 0) goto L33;
            if (this.plain.length() < r33.length()) goto L33;
            if ((r33 instanceof CodeHighlighting.LockedWithFallbackSpannableString) == false) goto L23;
            ((CodeHighlighting.LockedWithFallbackSpannableString) r33).fallback = null;
        L23:
            SpannableStringBuilder r34 = new SpannableStringBuilder(r33).append(this.plain.substring(r33.length()));
            StyleSpan[] r4 = (StyleSpan[]) r34.getSpans(0, r34.length(), StyleSpan.class);
            int r6 = 0;
        L25:
            if (r6 >= r4.length) goto L27;
            r34.removeSpan(r4[r6]);
            r6 = r6 + 1;
            goto L25
        L27:
            r34.setSpan(new StyleSpan(r17, 8), 0, r34.length(), 33);
            CodeHighlighting.Span[] r42 = (CodeHighlighting.Span[]) r34.getSpans(0, r34.length(), CodeHighlighting.Span.class);
            int r5 = 0;
        L29:
            if (r5 >= r42.length) goto L31;
            int r62 = r34.getSpanStart(r42[r5]);
            int r10 = r34.getSpanStart(r42[r5]);
            r34.removeSpan(r42[r5]);
            r34.setSpan(r42[r5], r62, r10, 33);
            r5 = r5 + 1;
            goto L29
        L31:
            this.content = new CodeHighlighting.LockedWithFallbackSpannableString(this.content, r34);
            goto L33
        L12:
            if (((CodeHighlighting.LockedWithFallbackSpannableString) r33).ready == true) goto L15;
            r33 = ((CodeHighlighting.LockedWithFallbackSpannableString) r33).fallback;
        L33:
            if (TextUtils.isEmpty(r20.language) == true) goto L35;
            SpannableString r9 = this.content;
            CodeHighlighting.highlight(r9, 0, r9.length(), r20.language, 0, null, false);
        L35:
            Text r1 = new Text(r17, this.content, AndroidUtilities.dp(5000.0f), Layout.Alignment.ALIGN_NORMAL, 1.3f);
            this.text = r1;
            this.texts = new Text[]{r1};
            int r12 = Math.max(0, r1.right - r1.left) + (AndroidUtilities.dp(0.0f) * 2);
            this.contentWidth = r12;
            int r13 = Math.max(0, r12 - this.viewportWidth);
            this.maxScrollX = r13;
            if (r21 == null) goto L41;
            this.scrollX = Utilities.clamp(r21.scrollX, r13, 0);
            return;
        }

        private void drawBackground(Canvas r14) {
            Paint r0 = this.bgPaint;
            RichMessageLayout r1 = this.root;
            if (r1.isOut() == false) goto L5;
            int r2 = Theme.key_chat_outArticleCodeBackground;
        L6:
            r0.setColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r1, r2));
            int r02 = this.padding.left;
            RichMessageLayout r12 = this.root;
            if (r02 <= 0) goto L10;
            int r03 = r12.getMinWidth();
            Rect r13 = this.padding;
            r14.drawRect(0.0f, 0.0f, (r03 - r13.left) - r13.right, getBackgroundHeight(), this.bgPaint);
            return;
        L10:
            r14.drawRect(-r12.padLeft, 0.0f, r12.getMinWidth() + this.root.padRight, getBackgroundHeight(), this.bgPaint);
            return;
        L5:
            r2 = Theme.key_chat_inArticleCodeBackground;
            goto L6
        }

        private void drawScrollbar(Canvas r19) {
            if (this.maxScrollX <= 0) goto L22;
            int r1 = this.padding.left;
            RichMessageLayout r2 = this.root;
            if (r1 <= 0) goto L8;
            int r12 = r2.getMinWidth();
            Rect r22 = this.padding;
            float r13 = (r12 - r22.left) - r22.right;
            float r23 = 0.0f;
        L9:
            float r6 = r23 + AndroidUtilities.dp(6.0f);
            float r8 = r13 - AndroidUtilities.dp(6.0f);
            if (r8 > r6) goto L12;
            return;
        L12:
            float r7 = this.text.getHeight() + AndroidUtilities.dp(23.0f);
            float r9 = r7 + AndroidUtilities.dp(5.0f);
            float r14 = AndroidUtilities.dp(2.5f);
            Paint r24 = this.bgPaint;
            RichMessageLayout r3 = this.root;
            if (r3.isOut() == false) goto L15;
            int r4 = Theme.key_chat_outArticleCodeScrollbarBackground;
        L16:
            r24.setColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r3, r4));
            r19.drawRoundRect(r6, r7, r8, r9, r14, r14, this.bgPaint);
            float r82 = r8 - r6;
            float r15 = Math.min(r82, Math.max(AndroidUtilities.dp(5.0f), (this.viewportWidth * r82) / this.contentWidth));
            float r10 = r6 + (((r82 - r15) * this.scrollX) / this.maxScrollX);
            Paint r25 = this.bgPaint;
            RichMessageLayout r32 = this.root;
            if (r32.isOut() == false) goto L19;
            int r42 = Theme.key_chat_outArticleCodeScrollbar;
        L20:
            r25.setColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r32, r42));
            r19.drawRoundRect(r10, r7, r10 + r15, r9, r14, r14, this.bgPaint);
            return;
        L19:
            r42 = Theme.key_chat_inArticleCodeScrollbar;
            goto L20
        L15:
            r4 = Theme.key_chat_inArticleCodeScrollbarBackground;
            goto L16
        L8:
            float r16 = -r2.padLeft;
            float r26 = r2.getMinWidth() + this.root.padRight;
            r23 = r16;
            r13 = r26;
            goto L9
        }

        private void drawTextContent(Canvas r14, boolean r15, int r16, float r17) {
            int r1 = Math.min(this.viewportWidth, this.contentWidth);
            int r2 = this.text.getHeight() + (AndroidUtilities.dp(8.0f) * 2);
            if (this.padding.left <= 0) goto L10;
            r14.save();
            r14.clipRect(0, 0, r1, r2);
            r14.translate(AndroidUtilities.dp(0.0f) - this.scrollX, AndroidUtilities.dp(8.0f));
            Text r13 = this.text;
            if (r15 == false) goto L7;
            r13.drawFade(r14, r16, r17);
        L8:
            r14.restore();
            return;
        L7:
            r13.draw(r14);
            goto L8
        L10:
            RichMessageLayout r3 = this.root;
            float r4 = -r3.padLeft;
            float r32 = r1 + r3.padRight;
            float r12 = r2;
            r14.saveLayerAlpha(r4, 0.0f, r32, r12, 255, 31);
            r14.save();
            r14.translate(AndroidUtilities.dp(0.0f) - this.scrollX, AndroidUtilities.dp(8.0f));
            Text r18 = this.text;
            if (r15 == false) goto L13;
            r18.drawFade(r14, r16, r17);
        L14:
            r14.restore();
            RectF r19 = AndroidUtilities.rectTmp;
            int r22 = this.root.padLeft;
            r19.set(-r22, 0.0f, (-r22) + AndroidUtilities.dp(12.0f), r12);
            this.root.clip.draw(r14, r19, 0, 1.0f);
            int r23 = this.root.getMinWidth() + this.root.padRight;
            Rect r6 = this.padding;
            r19.set(r2 - AndroidUtilities.dp(12.0f), 0.0f, (r23 - r6.left) - r6.right, r12);
            this.root.clip.draw(r14, r19, 2, 1.0f);
            r14.restore();
            return;
        L13:
            r18.draw(r14);
            goto L14
        }

        private void ensureTouchConfig() {
            if (this.touchSlop != 0) goto L8;
            View r0 = this.view;
            if (r0 == null) goto L8;
            ViewConfiguration r02 = ViewConfiguration.get(r0.getContext());
            this.touchSlop = r02.getScaledTouchSlop();
            this.minFlingVelocity = r02.getScaledMinimumFlingVelocity();
            this.maxFlingVelocity = r02.getScaledMaximumFlingVelocity();
        L8:
            if (this.scroller == null) goto L10;
            return;
        L10:
            if (this.view == null) goto L14;
            this.scroller = new OverScroller(this.view.getContext());
            return;
        }

        private int getBackgroundHeight() {
            int r0 = this.text.getHeight() + AndroidUtilities.dp(16.0f);
            if (this.maxScrollX <= 0) goto L5;
            int r2 = AndroidUtilities.dp(19.0f);
        L7:
            return r0 + r2;
        L5:
            r2 = 0;
            goto L7
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void appendAccessibilityText(SpannableStringBuilder r2) {
            RichBlock.appendText(r2, this.text, this.texts);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean canDragHorizontally() {
            if (this.maxScrollX <= 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean findLink(CharacterStyle r2, int r3, FoundLink r4) {
            if (this.text.fillFoundLink(r2, r4) == false) goto L6;
            r4.x = ((this.padding.left + AndroidUtilities.dp(0.0f)) - this.scrollX) - this.text.left;
            r4.y = (r3 + this.padding.top) + AndroidUtilities.dp(15.0f);
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public CharSequence getAccessibilityLabel() {
            CharSequence r0 = super.getAccessibilityLabel();
            if (TextUtils.isEmpty(this.language) == true) goto L7;
            String r3 = MessageObject.TextLayoutBlock.capitalizeLanguage(this.language);
            if (TextUtils.isEmpty(r3) == true) goto L7;
            return TextUtils.concat(new CharSequence[]{r0, " (", r3, ")"});
        L7:
            return r0;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            return ((this.padding.top + (AndroidUtilities.dp(7.0f) * 2)) + getBackgroundHeight()) + this.padding.bottom;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getLastLineWidth() {
            return getMinWidth();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock, org.telegram.ui.MultiLayoutTypingAnimator.Block
        public Layout getLayout() {
            return this.text.layout;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            return (this.padding.left + Math.min(this.viewportWidth, this.contentWidth)) + this.padding.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public TextSelectionHelper.TextLayoutBlock[] getText() {
            return this.texts;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean isHorizontallyDragging() {
            if (this.dragging == true) goto L11;
            OverScroller r1 = this.scroller;
            if (r1 != null) goto L7;
            return false;
        L7:
            if (r1.isFinished() == false) goto L14;
            return false;
        L14:
            return true;
        L11:
            return true;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onAttachedToWindow() {
            this.text.attach(this.view);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDetachedFromWindow() {
            this.text.detach(this.view);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r3) {
            r3.save();
            r3.translate(0.0f, AndroidUtilities.dp(7.0f));
            drawBackground(r3);
            drawTextContent(r3, false, 0, 0.0f);
            drawScrollbar(r3);
            r3.restore();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDrawFaded(Canvas r3, int r4, float r5) {
            StaticLayout r0 = this.text.layout;
            if (r0 == null) goto L10;
            if (r4 < 0) goto L10;
            if (r4 >= r0.getLineCount()) goto L10;
            r3.save();
            r3.translate(0.0f, AndroidUtilities.dp(7.0f));
            drawBackground(r3);
            drawTextContent(r3, true, r4, r5);
            drawScrollbar(r3);
            r3.restore();
            return;
        L10:
            onDraw(r3);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r20) {
            int r2 = r20.getActionMasked();
            float r3 = AndroidUtilities.dp(0.0f) - this.scrollX;
            float r4 = AndroidUtilities.dp(15.0f);
            int r5 = 0;
            if (r2 != 0) goto L16;
            ensureTouchConfig();
            OverScroller r22 = this.scroller;
            if (r22 != null) goto L7;
        L9:
            this.downX = r20.getX();
            this.downScrollX = this.scrollX;
            this.dragging = false;
            VelocityTracker r23 = this.velocityTracker;
            if (r23 != null) goto L12;
            this.velocityTracker = VelocityTracker.obtain();
        L13:
            this.velocityTracker.addMovement(r20);
            r20.offsetLocation(-r3, -r4);
            this.textHandlingTouch = this.text.onTouchEvent(r20);
            r20.offsetLocation(r3, r4);
            return true;
        L12:
            r23.clear();
            goto L13
        L7:
            if (r22.isFinished() == true) goto L9;
            this.scroller.forceFinished(true);
            goto L9
        L16:
            if (r2 != 2) goto L46;
            VelocityTracker r24 = this.velocityTracker;
            if (r24 == null) goto L20;
            r24.addMovement(r20);
        L20:
            float r25 = r20.getX() - this.downX;
            if (this.dragging == true) goto L30;
            if (this.maxScrollX <= 0) goto L30;
            if (Math.abs(r25) <= this.touchSlop) goto L30;
            this.dragging = true;
            requestDisallowParentIntercept(true);
            if (this.textHandlingTouch == false) goto L30;
            MotionEvent r1 = MotionEvent.obtain(r20);
            r1.setAction(3);
            r1.offsetLocation(-r3, -r4);
            this.text.onTouchEvent(r1);
            r1.recycle();
            this.textHandlingTouch = false;
        L30:
            if (this.dragging == false) goto L45;
            int r12 = (int) (this.downScrollX - r25);
            if (r12 < 0) goto L35;
            r5 = r12;
        L35:
            int r13 = this.maxScrollX;
            if (r5 <= r13) goto L39;
            r5 = r13;
        L39:
            if (r5 == this.scrollX) goto L43;
            this.scrollX = r5;
            placeTexts(this.layoutX, this.layoutY, this.layoutRow);
            View r0 = this.view;
            if (r0 == null) goto L43;
            r0.invalidate();
        L43:
            return true;
        L45:
            return this.textHandlingTouch;
        L46:
            if (r2 == 1) goto L50;
            if (r2 == 3) goto L50;
            return false;
        L50:
            boolean r7 = this.dragging;
            this.dragging = false;
            if (r7 == false) goto L63;
            requestDisallowParentIntercept(false);
            if (r2 != 1) goto L63;
            VelocityTracker r8 = this.velocityTracker;
            if (r8 == null) goto L63;
            if (this.scroller == null) goto L63;
            if (this.view == null) goto L63;
            r8.addMovement(r20);
            this.velocityTracker.computeCurrentVelocity(1000, this.maxFlingVelocity);
            float r82 = -this.velocityTracker.getXVelocity();
            if (Math.abs(r82) <= this.minFlingVelocity) goto L63;
            this.scroller.fling(this.scrollX, 0, (int) r82, 0, 0, this.maxScrollX, 0, 0);
            this.view.postOnAnimation(this.flingTick);
        L63:
            if (r7 == false) goto L65;
        L67:
            this.textHandlingTouch = false;
            VelocityTracker r14 = this.velocityTracker;
            if (r14 == null) goto L70;
            r14.recycle();
            this.velocityTracker = null;
        L70:
            if (r7 == true) goto L74;
            if (r2 == 1) goto L74;
            return false;
        L74:
            return true;
        L65:
            if (this.textHandlingTouch == false) goto L67;
            r20.offsetLocation(-r3, -r4);
            this.text.onTouchEvent(r20);
            r20.offsetLocation(r3, r4);
            goto L67
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void placeTexts(int r3, int r4, int r5) {
            this.layoutX = r3;
            this.layoutY = r4;
            this.layoutRow = r5;
            this.text.setX(((r3 + AndroidUtilities.dp(0.0f)) - this.scrollX) - this.text.left);
            this.text.setY(r4 + AndroidUtilities.dp(15.0f));
            this.text.setRow(r5);
        }
    }

    public static class RichPullquoteBlock extends RichTextWithAuthorBlock {
        public RichPullquoteBlock(RichMessageLayout r8, Rect r9, int r10, CharSequence r11, CharSequence r12) {
            super(r8, r9, r10, r11, r12, Layout.Alignment.ALIGN_CENTER);
        }
    }

    public static class RichQuoteBlock extends RichTextWithAuthorBlock {
        public RichQuoteBlock(RichMessageLayout r8, Rect r9, int r10, CharSequence r11, CharSequence r12) {
            super(r8, r9, r10, r11, r12, Layout.Alignment.ALIGN_NORMAL);
        }
    }

    public static class RichSlideshowBlock extends RichBlock {
        private static Paint mediaBgPaint;
        private static Paint slideDotPaint;
        public final TL_iv.pageBlockSlideshow block;
        public final ArrayList<MediaCell> cells;
        private final Path clipPath;
        private int currentPage;
        private int dotsHeight;
        private float downX;
        private float downY;
        private boolean dragging;
        public final boolean first;
        private int maxFlingVelocity;
        private int minFlingVelocity;
        private float pageOffset;
        private ValueAnimator settleAnimator;
        private int slideHeight;
        private int slideWidth;
        private int touchSlop;
        private VelocityTracker velocityTracker;
        private boolean verticalDragging;

        /* renamed from: org.telegram.messenger.RichMessageLayout$RichSlideshowBlock$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ RichSlideshowBlock this$0;
            final /* synthetic */ int val$target;

            public AnonymousClass1(RichSlideshowBlock r1, int r2) {
                this.this$0 = r1;
                this.val$target = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                RichSlideshowBlock.m4850$$Nest$fputcurrentPage(this.this$0, this.val$target);
                RichSlideshowBlock.m4851$$Nest$fputpageOffset(this.this$0, 0.0f);
                View r1 = this.this$0.view;
                if (r1 == null) goto L6;
                r1.invalidate();
                return;
            }
        }

        /* renamed from: $r8$lambda$tO1nqTh7J-ShZdS7Esu9riZGSpo, reason: not valid java name */
        public static /* synthetic */ void m4849$r8$lambda$tO1nqTh7JShZdS7Esu9riZGSpo(RichSlideshowBlock r0, ValueAnimator r1) {
            r0.lambda$settle$0(r1);
        }

        /* renamed from: -$$Nest$fputcurrentPage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4850$$Nest$fputcurrentPage(RichSlideshowBlock r0, int r1) {
            r0.currentPage = r1;
        }

        /* renamed from: -$$Nest$fputpageOffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4851$$Nest$fputpageOffset(RichSlideshowBlock r0, float r1) {
            r0.pageOffset = r1;
        }

        public RichSlideshowBlock(RichMessageLayout r1, Rect r2, int r3, TL_iv.pageBlockSlideshow r4, boolean r5) {
            super(r1, r2, r3);
            this.cells = new ArrayList();
            this.clipPath = new Path();
            this.block = r4;
            this.first = r5;
            int r22 = 0;
        L4:
            if (r22 >= r4.items.size()) goto L9;
            MediaCell r32 = MediaCell.forPageBlock(r1, r4.items.get(r22));
            if (r32 == null) goto L8;
            this.cells.add(r32);
        L8:
            r22 = r22 + 1;
            goto L4
        L9:
            layoutCells();
        }

        private /* synthetic */ void lambda$settle$0(ValueAnimator r1) {
            this.pageOffset = ((Float) r1.getAnimatedValue()).floatValue();
            View r0 = this.view;
            if (r0 == null) goto L6;
            r0.invalidate();
            return;
        }

        private void layoutCells() {
            if (this.cells.isEmpty() == false) goto L6;
            this.slideHeight = 0;
            this.slideWidth = 0;
            return;
        L6:
            this.slideWidth = this.maxWidth;
            ArrayList<MediaCell> r0 = this.cells;
            int r2 = r0.size();
            int r5 = 0;
            float r4 = 0.0f;
        L7:
            if (r5 >= r2) goto L12;
            MediaCell r6 = r0.get(r5);
            r5 = r5 + 1;
            float r62 = r6.aspectRatio;
            if (r62 > 0.0f) goto L11;
            r62 = 1.0f;
        L11:
            r4 = r4 + r62;
            goto L7
        L12:
            int r02 = (int) (this.slideWidth / Math.max(0.5f, r4 / this.cells.size()));
            Point r22 = AndroidUtilities.displaySize;
            int r23 = (int) (Math.max(r22.x, r22.y) * 0.55f);
            if (r02 <= r23) goto L15;
            r02 = r23;
        L15:
            this.slideHeight = r02;
            this.dotsHeight = 0;
            ArrayList<MediaCell> r03 = this.cells;
            int r24 = r03.size();
            int r3 = 0;
        L16:
            if (r3 >= r24) goto L18;
            MediaCell r42 = r03.get(r3);
            r3 = r3 + 1;
            r42.setRect(0, 0, this.slideWidth, this.slideHeight);
            goto L16
        }

        private void settle(float r6) {
            if (r6 < 0.0f) goto L5;
        L7:
            int r0 = -1;
            if (r6 <= 0.0f) goto L13;
            if (this.currentPage <= 0) goto L13;
        L23:
            int r02 = r0 + this.currentPage;
            ValueAnimator r62 = ValueAnimator.ofFloat(new float[]{this.pageOffset, r02 - r6});
            this.settleAnimator = r62;
            r62.setDuration(420);
            this.settleAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.settleAnimator.addUpdateListener(new RichMessageLayout$RichSlideshowBlock$$ExternalSyntheticLambda0(this));
            this.settleAnimator.addListener(new AnonymousClass1(this, r02));
            this.settleAnimator.start();
            return;
        L13:
            if (this.pageOffset <= 0.15f) goto L18;
            if (this.currentPage >= (this.cells.size() - 1)) goto L18;
        L6:
            r0 = 1;
        L18:
            if (this.pageOffset < (-0.15f)) goto L20;
        L22:
            r0 = 0;
            goto L23
        L20:
            if (this.currentPage <= 0) goto L22;
        L5:
            if (this.currentPage >= (this.cells.size() - 1)) goto L7;
            goto L6
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean canDragHorizontally() {
            if (this.cells.size() <= 1) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void getBlockAccessibilityElementBounds(int r3, Rect r4) {
            Rect r32 = this.padding;
            int r0 = r32.left;
            int r1 = ((int) this.currY) + r32.top;
            r4.set(r0, r1, this.slideWidth + r0, this.slideHeight + r1);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getBlockAccessibilityElementCount() {
            return !this.cells.isEmpty() ? 1 : 0;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public CharSequence getBlockAccessibilityElementText(int r5) {
            if (this.cells.isEmpty() == false) goto L6;
            return null;
        L6:
            int r52 = Math.max(0, Math.min(this.currentPage, this.cells.size() - 1));
            return TextUtils.concat(new CharSequence[]{this.cells.get(r52).getAccessibilityText(), ", ", LocaleController.formatString(R.string.Of, new Object[]{Integer.valueOf(r52 + 1), Integer.valueOf(this.cells.size())})});
        }

        public int getCurrentPage() {
            return this.currentPage;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            return this.slideHeight + this.dotsHeight;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getLastLineWidth() {
            return getMinWidth();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            Rect r0 = this.padding;
            return (r0.left + this.maxWidth) + r0.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean isHorizontallyDragging() {
            if (this.dragging == true) goto L11;
            ValueAnimator r1 = this.settleAnimator;
            if (r1 != null) goto L7;
            return false;
        L7:
            if (r1.isRunning() == true) goto L14;
            return false;
        L14:
            return true;
        L11:
            return true;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onAttachedToWindow() {
            ArrayList<MediaCell> r0 = this.cells;
            int r1 = r0.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            MediaCell r3 = r0.get(r2);
            r2 = r2 + 1;
            r3.attach(this.view);
            goto L3
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onBlockAccessibilityElementClick(int r3, View r4) {
            if (this.cells.isEmpty() == false) goto L6;
            return false;
        L6:
            return this.cells.get(Math.max(0, Math.min(this.currentPage, this.cells.size() - 1))).onAccessibilityClick(r4);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDetachedFromWindow() {
            int r0 = 0;
            requestDisallowParentIntercept(false);
            this.dragging = false;
            this.verticalDragging = false;
            VelocityTracker r1 = this.velocityTracker;
            if (r1 == null) goto L5;
            r1.recycle();
            this.velocityTracker = null;
        L5:
            ArrayList<MediaCell> r3 = this.cells;
            int r12 = r3.size();
        L6:
            if (r0 >= r12) goto L8;
            MediaCell r2 = r3.get(r0);
            r0 = r0 + 1;
            r2.detach();
            goto L6
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r27) {
            if (this.cells.isEmpty() == true) goto L121;
            int r7 = 1;
            if (mediaBgPaint != null) goto L8;
            Paint r2 = new Paint(1);
            mediaBgPaint = r2;
            r2.setColor(251658240);
        L8:
            boolean r22 = isInQuote();
            int r3 = AndroidUtilities.dp(2.0f);
            int r9 = 0;
            if (r22 == false) goto L11;
            int r10 = 0;
        L12:
            if (r22 == false) goto L14;
            int r11 = 0;
        L15:
            int r12 = (this.slideWidth + r10) + r11;
            r27.save();
            char r15 = 2;
            if (r22 == false) goto L19;
            int r23 = AndroidUtilities.dp(8.0f);
            this.clipPath.rewind();
            float r6 = r23;
            this.clipPath.addRoundRect(0.0f, 0.0f, this.slideWidth, this.slideHeight, r6, r6, Path.Direction.CW);
            r27.clipPath(this.clipPath);
            int r18 = r23;
            float r16 = 2.0f;
            float r17 = 3.0f;
            float r25 = 8.0f;
            int r8 = r18;
            int r13 = r8;
            int r14 = r13;
        L42:
            int r24 = this.currentPage;
            float r32 = 0.0f;
            if (r24 != 0) goto L47;
            if (this.pageOffset >= 0.0f) goto L47;
        L50:
            RichMessageLayout r26 = this.root;
            if (r26.isOut() == false) goto L53;
            int r4 = Theme.key_chat_outReplyNameText;
        L54:
            r27.drawColor(Theme.multAlpha(RichMessageLayout.m4824$$Nest$mgetThemedColor(r26, r4), 0.2f));
        L55:
            float r42 = r12;
            float r19 = (-this.pageOffset) * r42;
            int r28 = this.currentPage - 1;
        L57:
            if (r28 > (this.currentPage + r7)) goto L102;
            if (r28 >= 0) goto L60;
        L61:
            float r242 = r42;
            int r232 = r7;
            char r21 = r15;
            int r152 = r28;
            float r72 = r32;
        L101:
            r28 = r152 + 1;
            r32 = r72;
            r15 = r21;
            r7 = r232;
            r42 = r242;
            r9 = 0;
            goto L57
        L60:
            if (r28 >= this.cells.size()) goto L61;
            MediaCell r5 = this.cells.get(r28);
            r27.save();
            r27.translate(((r28 - this.currentPage) * r12) + r19, r32);
            ImageReceiver r62 = r5.imageReceiver;
            r21 = r15;
            if (r28 != 0) goto L65;
            int r153 = r8;
        L67:
            if (r28 != (this.cells.size() - r7)) goto L70;
            int r92 = r13;
        L69:
            r232 = r7;
            if (r28 != (this.cells.size() - 1)) goto L74;
            int r73 = r14;
        L75:
            if (r28 != 0) goto L77;
            int r33 = r18;
        L78:
            r62.setRoundRadius(r153, r92, r73, r33);
            ImageReceiver r34 = r5.blurImageReceiver;
            if (r28 != 0) goto L81;
            int r63 = r8;
        L83:
            if (r28 != (this.cells.size() - 1)) goto L85;
            int r74 = r13;
        L87:
            if (r28 != (this.cells.size() - 1)) goto L89;
            int r93 = r14;
        L90:
            if (r28 != 0) goto L92;
            int r154 = r18;
        L93:
            r34.setRoundRadius(r63, r74, r93, r154);
            float r64 = -r10;
            r5.imageReceiver.setImageCoords(r64, 0.0f, r42, this.slideHeight);
            if (r5.imageReceiver.hasBitmapImage() == true) goto L96;
        L99:
            r152 = r28;
            MediaCell r94 = r5;
            r72 = 0.0f;
            r242 = r42;
            r27.drawRect(r64, 0.0f, r12 + r11, this.slideHeight, mediaBgPaint);
        L100:
            r94.draw(r27);
            r27.restore();
            goto L101
        L96:
            if (r5.imageReceiver.getCurrentAlpha() != 1.0f) goto L99;
            r152 = r28;
            r242 = r42;
            r72 = 0.0f;
            r94 = r5;
            goto L100
        L92:
            r154 = 0;
            goto L93
        L89:
            r93 = 0;
            goto L90
        L85:
            r74 = 0;
            goto L87
        L81:
            r63 = 0;
            goto L83
        L77:
            r33 = 0;
            goto L78
        L74:
            r73 = 0;
            goto L75
        L70:
            r92 = 0;
            goto L69
        L65:
            r153 = r9;
            goto L67
        L102:
            int r233 = r7;
            float r75 = r32;
            r27.restore();
            int r29 = this.cells.size();
            if (r29 > r233) goto L105;
            return;
        L105:
            if (slideDotPaint != null) goto L107;
            Paint r43 = new Paint(r233);
            slideDotPaint = r43;
            r43.setColor(-1);
            slideDotPaint.setShadowLayer(AndroidUtilities.dpf2(r17), r75, AndroidUtilities.dpf2(1.0f), Integer.MIN_VALUE);
        L107:
            float r35 = (this.slideHeight - AndroidUtilities.dp(23.0f)) + AndroidUtilities.dp(5.0f);
            int r52 = ((AndroidUtilities.dp(7.0f) * r29) + ((r29 - 1) * AndroidUtilities.dp(6.0f))) + AndroidUtilities.dp(4.0f);
            float r82 = this.currentPage + this.pageOffset;
            if (r52 >= r12) goto L110;
            float r53 = (r12 - r52) / r16;
        L111:
            r27.save();
            int r142 = 0;
            r27.clipRect(0, this.slideHeight - AndroidUtilities.dp(23.0f), r12, this.slideHeight);
        L112:
            if (r142 >= r29) goto L114;
            float r0 = Math.max(r75, 1.0f - Math.abs(r142 - r82));
            slideDotPaint.setAlpha((int) ((r0 * 95.0f) + 160.0f));
            r27.drawCircle((AndroidUtilities.dp(4.0f) + r53) + (AndroidUtilities.dp(13.0f) * r142), r35, AndroidUtilities.dp(r16) + (AndroidUtilities.dp(1.0f) * r0), slideDotPaint);
            r142 = r142 + 1;
            goto L112
        L114:
            r27.restore();
            return;
        L110:
            float r54 = AndroidUtilities.dp(4.0f);
            int r102 = AndroidUtilities.dp(13.0f);
            r53 = r54 - (Utilities.clamp(r82 - (((r12 - AndroidUtilities.dp(r25)) / 2) / r102), Math.max(0, (r29 - (r11 * 2)) - 1), r75) * r102);
            goto L111
        L53:
            r4 = Theme.key_chat_inReplyNameText;
        L47:
            if (r24 != (this.cells.size() - 1)) goto L55;
            if (this.pageOffset <= 0.0f) goto L55;
        L19:
            if (this.first == false) goto L41;
            int r210 = SharedConfig.bubbleRadius;
            if (r210 <= 2) goto L23;
            int r211 = AndroidUtilities.dp(r210 - 2);
        L24:
            int r36 = Math.min(AndroidUtilities.dp(3.0f), r211);
            if (this.root.isOut() == true) goto L29;
            if (this.root.isPinnedTop() == false) goto L29;
        L31:
            int r44 = r36;
        L33:
            if (this.root.isOut() == false) goto L37;
            if (this.root.isPinnedTop() == false) goto L37;
        L39:
            r211 = r36;
        L40:
            float r55 = r44;
            float r65 = r211;
            r16 = 2.0f;
            float r83 = r36;
            r17 = 3.0f;
            this.clipPath.rewind();
            r25 = 8.0f;
            this.clipPath.addRoundRect(-r10, 0.0f, this.slideWidth + r11, this.slideHeight, new float[]{r55, r55, r65, r65, r83, r83, r83, r83}, Path.Direction.CW);
            r27.clipPath(this.clipPath);
            r13 = r211;
            r14 = r36;
            r18 = r14;
            r8 = r44;
        L37:
            if (this.root.hasNameOffset() == true) goto L39;
        L29:
            if (this.root.hasNameOffset() == true) goto L31;
            r44 = r211;
            goto L33
        L23:
            r211 = AndroidUtilities.dp(r210);
            goto L24
        L41:
            r16 = 2.0f;
            r17 = 3.0f;
            r25 = 8.0f;
            r27.clipRect(-r10, 0, this.root.getMinWidth() + r11, this.slideHeight);
            r8 = 0;
            r13 = 0;
            r14 = 0;
            r18 = 0;
            goto L42
        L14:
            r11 = this.root.padRight - r3;
            goto L15
        L11:
            r10 = this.root.padLeft - r3;
            goto L12
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r10) {
            int r0 = r10.getActionMasked();
            Rect r1 = this.padding;
            r10.offsetLocation(-r1.left, -r1.top);
            if (r0 == 0) goto L5;
            float r5 = 0.0f;
            if (r0 == 2) goto L28;
            if (r0 == 1) goto L82;
            if (r0 == 3) goto L82;
        L78:
            Rect r9 = this.padding;
            r10.offsetLocation(r9.left, r9.top);
            return false;
        L82:
            boolean r4 = this.verticalDragging;     // Catch: Throwable -> L9
            this.verticalDragging = false;     // Catch: Throwable -> L9
            if (r4 == true) goto L92;
            if (r0 != 1) goto L92;
            VelocityTracker r02 = this.velocityTracker;     // Catch: Throwable -> L9
            if (r02 == null) goto L92;
            r02.addMovement(r10);     // Catch: Throwable -> L9
            this.velocityTracker.computeCurrentVelocity(1000, this.maxFlingVelocity);     // Catch: Throwable -> L9
            float r03 = this.velocityTracker.getXVelocity();     // Catch: Throwable -> L9
            float r6 = this.velocityTracker.getYVelocity();     // Catch: Throwable -> L9
            if (Math.abs(r03) < this.minFlingVelocity) goto L92;
            if (Math.abs(r03) <= Math.abs(r6)) goto L92;
            r5 = r03;
        L92:
            VelocityTracker r04 = this.velocityTracker;     // Catch: Throwable -> L9
            if (r04 == null) goto L95;
            r04.recycle();     // Catch: Throwable -> L9
            this.velocityTracker = null;     // Catch: Throwable -> L9
        L95:
            requestDisallowParentIntercept(false);     // Catch: Throwable -> L9
            if (r4 == false) goto L99;
        L23:
            Rect r92 = this.padding;
            r10.offsetLocation(r92.left, r92.top);
            return true;
        L99:
            if (this.dragging == false) goto L101;
            this.dragging = false;     // Catch: Throwable -> L9
            settle(r5);     // Catch: Throwable -> L9
            goto L23
        L101:
            int r05 = this.currentPage;     // Catch: Throwable -> L9
            if (r05 < 0) goto L78;
            if (r05 >= this.cells.size()) goto L78;
            boolean r06 = this.cells.get(this.currentPage).onTouchEvent(r10, this.view);     // Catch: Throwable -> L9
        L76:
            Rect r93 = this.padding;
            r10.offsetLocation(r93.left, r93.top);
            return r06;
        L28:
            if (this.verticalDragging == true) goto L23;
            VelocityTracker r07 = this.velocityTracker;     // Catch: Throwable -> L9
            if (r07 == null) goto L33;
            r07.addMovement(r10);     // Catch: Throwable -> L9
        L33:
            float r08 = r10.getX() - this.downX;     // Catch: Throwable -> L9
            float r12 = r10.getY() - this.downY;     // Catch: Throwable -> L9
            if (this.dragging == true) goto L46;
            if (Math.abs(r12) <= this.touchSlop) goto L46;
            if (Math.abs(r12) <= Math.abs(r08)) goto L46;
            this.verticalDragging = true;     // Catch: Throwable -> L9
            int r09 = this.currentPage;     // Catch: Throwable -> L9
            if (r09 >= 0) goto L42;
        L44:
            requestDisallowParentIntercept(false);     // Catch: Throwable -> L9
            goto L23
        L42:
            if (r09 >= this.cells.size()) goto L44;
            MotionEvent r010 = MotionEvent.obtain(r10);     // Catch: Throwable -> L9
            r010.setAction(3);     // Catch: Throwable -> L9
            this.cells.get(this.currentPage).onTouchEvent(r010, this.view);     // Catch: Throwable -> L9
            r010.recycle();     // Catch: Throwable -> L9
        L46:
            if (this.dragging == true) goto L57;
            if (Math.abs(r08) <= this.touchSlop) goto L57;
            if (Math.abs(r08) <= Math.abs(r12)) goto L57;
            this.dragging = true;     // Catch: Throwable -> L9
            int r13 = this.currentPage;     // Catch: Throwable -> L9
            if (r13 < 0) goto L57;
            if (r13 >= this.cells.size()) goto L57;
            MotionEvent r14 = MotionEvent.obtain(r10);     // Catch: Throwable -> L9
            r14.setAction(3);     // Catch: Throwable -> L9
            this.cells.get(this.currentPage).onTouchEvent(r14, this.view);     // Catch: Throwable -> L9
            r14.recycle();     // Catch: Throwable -> L9
        L57:
            if (this.dragging == false) goto L71;
            float r011 = (-r08) / this.slideWidth;     // Catch: Throwable -> L9
            int r15 = this.currentPage;     // Catch: Throwable -> L9
            if (r15 != 0) goto L64;
            if (r011 >= 0.0f) goto L64;
            r011 = r011 * 0.3f;     // Catch: Throwable -> L9
        L64:
            if (r15 == (this.cells.size() - 1)) goto L66;
        L68:
            this.pageOffset = r011;     // Catch: Throwable -> L9
            View r012 = this.view;     // Catch: Throwable -> L9
            if (r012 == null) goto L23;
            r012.invalidate();     // Catch: Throwable -> L9
            goto L23
        L66:
            if (r011 <= 0.0f) goto L68;
            r011 = r011 * 0.3f;     // Catch: Throwable -> L9
            goto L68
        L71:
            int r013 = this.currentPage;     // Catch: Throwable -> L9
            if (r013 < 0) goto L78;
            if (r013 >= this.cells.size()) goto L78;
            r06 = this.cells.get(this.currentPage).onTouchEvent(r10, this.view);     // Catch: Throwable -> L9
            goto L76
        L5:
            if (this.touchSlop != 0) goto L11;
            View r014 = this.view;     // Catch: Throwable -> L9
            if (r014 == null) goto L11;
            ViewConfiguration r015 = ViewConfiguration.get(r014.getContext());     // Catch: Throwable -> L9
            this.touchSlop = r015.getScaledTouchSlop();     // Catch: Throwable -> L9
            this.minFlingVelocity = r015.getScaledMinimumFlingVelocity();     // Catch: Throwable -> L9
            this.maxFlingVelocity = r015.getScaledMaximumFlingVelocity();     // Catch: Throwable -> L9
        L11:
            this.downX = r10.getX();     // Catch: Throwable -> L9
            this.downY = r10.getY();     // Catch: Throwable -> L9
            this.dragging = false;     // Catch: Throwable -> L9
            this.verticalDragging = false;     // Catch: Throwable -> L9
            VelocityTracker r016 = this.velocityTracker;     // Catch: Throwable -> L9
            if (r016 != null) goto L14;
            this.velocityTracker = VelocityTracker.obtain();     // Catch: Throwable -> L9
        L15:
            this.velocityTracker.addMovement(r10);     // Catch: Throwable -> L9
            requestDisallowParentIntercept(true);     // Catch: Throwable -> L9
            ValueAnimator r017 = this.settleAnimator;     // Catch: Throwable -> L9
            if (r017 == null) goto L18;
            r017.cancel();     // Catch: Throwable -> L9
            this.settleAnimator = null;     // Catch: Throwable -> L9
        L18:
            int r018 = this.currentPage;     // Catch: Throwable -> L9
            if (r018 < 0) goto L23;
            if (r018 >= this.cells.size()) goto L23;
            this.cells.get(this.currentPage).onTouchEvent(r10, this.view);     // Catch: Throwable -> L9
            goto L23
        L14:
            r016.clear();     // Catch: Throwable -> L9
        L9:
            th = move-exception;
            Rect r94 = this.padding;
            r10.offsetLocation(r94.left, r94.top);
            throw th;
        }

        public void setCurrentPage(int r4) {
            int r42 = Math.max(0, Math.min(r4, this.cells.size() - 1));
            ValueAnimator r1 = this.settleAnimator;
            if (r1 == null) goto L6;
            r1.cancel();
            this.settleAnimator = null;
        L6:
            if (this.currentPage == r42) goto L8;
        L10:
            this.currentPage = r42;
            this.pageOffset = 0.0f;
            this.dragging = false;
            this.verticalDragging = false;
            View r3 = this.view;
            if (r3 == null) goto L14;
            r3.invalidate();
            return;
        L14:
            return;
        L8:
            if (this.pageOffset != 0.0f) goto L10;
        }
    }

    public static class RichTableBlock extends RichBlock implements TableLayout.TableLayoutDelegate {
        private static final int VERTICAL_PADDING_DP = 10;
        private final ArrayList<CellBlock> cellBlocks;
        private float cellDx;
        private float cellDy;
        private final ArrayList<Text> cellTexts;
        private int contentHeight;
        private final int contentMeasuredWidth;
        private int downScrollX;
        private float downX;
        private float downY;
        private boolean dragging;
        private final Runnable flingTick;
        private Paint halfLinePaint;
        private Paint headerPaint;
        private final int intrinsicContentWidth;
        private final int intrinsicTableWidth;
        private Paint linePaint;
        private int maxFlingVelocity;
        private final int maxScrollX;
        private int minFlingVelocity;
        public final TL_iv.pageBlockTable pageBlock;
        private Text pressedCellText;
        private int resolvedTableWidth;
        private int scrollX;
        private OverScroller scroller;
        private Paint stripPaint;
        public final TableLayout tableLayout;
        private boolean textHandlingTouch;
        private final TextSelectionHelper.TextLayoutBlock[] textsArr;
        private final Text title;
        private final int titleHeight;
        private int touchSlop;
        private VelocityTracker velocityTracker;
        private final int viewportWidth;

        /* renamed from: org.telegram.messenger.RichMessageLayout$RichTableBlock$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ RichTableBlock this$0;

            public AnonymousClass1(RichTableBlock r1) {
                this.this$0 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (RichTableBlock.m4854$$Nest$fgetscroller(this.this$0) == null) goto L22;
                RichTableBlock r0 = this.this$0;
                if (r0.view != null) goto L8;
                return;
            L8:
                if (RichTableBlock.m4854$$Nest$fgetscroller(r0).computeScrollOffset() == false) goto L23;
                int r02 = RichTableBlock.m4854$$Nest$fgetscroller(this.this$0).getCurrX();
                if (r02 >= 0) goto L13;
                r02 = 0;
            L13:
                if (r02 <= RichTableBlock.m4852$$Nest$fgetmaxScrollX(this.this$0)) goto L16;
                r02 = RichTableBlock.m4852$$Nest$fgetmaxScrollX(this.this$0);
            L16:
                if (r02 == RichTableBlock.m4853$$Nest$fgetscrollX(this.this$0)) goto L19;
                RichTableBlock.m4855$$Nest$fputscrollX(this.this$0, r02);
                RichTableBlock r03 = this.this$0;
                r03.placeTexts(r03.layoutX, r03.layoutY, r03.layoutRow);
                this.this$0.view.invalidate();
            L19:
                if (RichTableBlock.m4854$$Nest$fgetscroller(this.this$0).isFinished() == true) goto L24;
                this.this$0.view.postOnAnimation(this);
                return;
            L24:
                return;
            L23:
                return;
            }
        }

        public static final class CellBlock implements MultiLayoutTypingAnimator.Block {
            final TableLayout.Child child;

            public CellBlock(TableLayout.Child r1) {
                this.child = r1;
            }

            @Override // org.telegram.ui.MultiLayoutTypingAnimator.Block
            public Layout getLayout() {
                TableLayout.CellText r0 = this.child.textLayout;
                if (r0 != null) goto L7;
                return null;
            L7:
                return r0.getLayout();
            }

            @Override // org.telegram.ui.MultiLayoutTypingAnimator.Block
            public View getParentView() {
                return null;
            }
        }

        /* renamed from: -$$Nest$fgetmaxScrollX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m4852$$Nest$fgetmaxScrollX(RichTableBlock r0) {
            return r0.maxScrollX;
        }

        /* renamed from: -$$Nest$fgetscrollX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m4853$$Nest$fgetscrollX(RichTableBlock r0) {
            return r0.scrollX;
        }

        /* renamed from: -$$Nest$fgetscroller, reason: not valid java name */
        public static /* bridge */ /* synthetic */ OverScroller m4854$$Nest$fgetscroller(RichTableBlock r0) {
            return r0.scroller;
        }

        /* renamed from: -$$Nest$fputscrollX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4855$$Nest$fputscrollX(RichTableBlock r0, int r1) {
            r0.scrollX = r1;
        }

        /* renamed from: -$$Nest$mresolveWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4856$$Nest$mresolveWidth(RichTableBlock r0, int r1) {
            r0.resolveWidth(r1);
        }

        public RichTableBlock(RichMessageLayout r7, Rect r8, int r9, TL_iv.pageBlockTable r10) {
            super(r7, r8, r9);
            this.cellTexts = new ArrayList();
            this.cellBlocks = new ArrayList();
            this.flingTick = new AnonymousClass1(this);
            this.padding.top += AndroidUtilities.dp(10.0f);
            this.pageBlock = r10;
            int r82 = this.maxWidth;
            this.viewportWidth = r82;
            TableLayout r92 = new TableLayout(ApplicationLoader.applicationContext, this, null);
            this.tableLayout = r92;
            r92.setOrientation(0);
            r92.setRowOrderPreserved(true);
            r92.setDrawLines(r10.bordered);
            r92.setStriped(r10.striped);
            r92.setRtl(r7.isRtl());
            r92.setFillWidth(false);
            if (r10.compact == false) goto L6;
            r92.setCellPadding(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f));
        L6:
            if (r10.compact == false) goto L8;
            float r3 = 18.0f;
        L9:
            r92.setMinimumCellHeight(AndroidUtilities.dp(r3));
            r92.addRows(r10.rows);
            r92.measure(View.MeasureSpec.makeMeasureSpec(this.maxWidth, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            int r32 = r92.getMeasuredWidth();
            this.contentMeasuredWidth = r32;
            this.intrinsicContentWidth = r32;
            this.contentHeight = r92.getMeasuredHeight();
            this.maxScrollX = Math.max(0, r32 - r82);
            TL_iv.RichText r93 = r10.title;
            if (r93 != null) goto L12;
        L16:
            this.title = null;
            this.titleHeight = 0;
        L17:
            Text r72 = this.title;
            if (r72 == null) goto L20;
            int r73 = r72.getMinWidth();
        L21:
            int r74 = Math.max(0, Math.min(r82, Math.max(r32, r73)));
            this.intrinsicTableWidth = r74;
            this.resolvedTableWidth = r74;
            int r75 = 0;
        L23:
            if (r75 >= this.tableLayout.getChildCount()) goto L28;
            TableLayout.Child r83 = this.tableLayout.getChildAt(r75);
            TableLayout.CellText r94 = r83.textLayout;
            if ((r94 instanceof Text) == false) goto L27;
            this.cellTexts.add((Text) r94);
            this.cellBlocks.add(new CellBlock(r83));
        L27:
            r75 = r75 + 1;
            goto L23
        L28:
            ArrayList r76 = new ArrayList();
            Text r84 = this.title;
            if (r84 == null) goto L31;
            r76.add(r84);
        L31:
            r76.addAll(this.cellTexts);
            this.textsArr = (TextSelectionHelper.TextLayoutBlock[]) r76.toArray(new TextSelectionHelper.TextLayoutBlock[0]);
            return;
        L20:
            r73 = 0;
            goto L21
        L12:
            if ((r93 instanceof TL_iv.textEmpty) == true) goto L16;
            if (TextUtils.isEmpty(RichMessageLayout.getString(r93)) == true) goto L16;
            Text r1 = new Text(r7, r7.formatText(r10.title, RichMessageLayout.setBlockFlags(0, 15)), r82);
            this.title = r1;
            r1.setDrawAtOrigin(true);
            this.titleHeight = r1.getHeight() + AndroidUtilities.dp(9.0f);
            goto L17
        L8:
            r3 = 36.0f;
            goto L9
        }

        private void drawCellsWithTyping(Canvas r18, MultiLayoutTypingAnimator r19, float r20) {
            RichMessageLayout r1 = this.root;
            float r2 = -r1.padLeft;
            int r12 = r1.getMinWidth() + this.root.padRight;
            Rect r3 = this.padding;
            int r10 = r18.saveLayerAlpha(r2, 0.0f, (r12 - r3.left) - r3.right, this.contentHeight, (int) (r20 * 255.0f), 31);
            r18.save();
            r18.translate(-this.scrollX, 0.0f);
            int r122 = this.tableLayout.getChildCount();
            int r22 = 0;
            int r14 = 0;
        L4:
            if (r14 >= r122) goto L32;
            TableLayout.Child r15 = this.tableLayout.getChildAt(r14);
            if (r22 < this.cellBlocks.size()) goto L8;
        L11:
            CellBlock r4 = null;
        L10:
            int r16 = r22;
            if (r4 != null) goto L15;
            r15.draw(r18, this.view);
        L31:
            r14 = r14 + 1;
            r22 = r16;
            goto L4
        L15:
            if (r19.needDraw(r4) == true) goto L18;
            r15.draw(r18, this.view, false);
            goto L31
        L18:
            if (r19.isFadeBlock(r4) == false) goto L22;
            r15.draw(r18, this.view, false);
            if ((r15.textLayout instanceof Text) == false) goto L31;
            r18.save();
            r18.translate(r15.getTextX(), r15.getTextY());
            ((Text) r15.textLayout).drawFade(r18, r19.getFadeLineIndex(r4), r19.getFadeXPosition(r4));
            r18.restore();
            goto L31
        L22:
            float r23 = r19.getBlockAlpha(r4);
            if (r23 < 1.0f) goto L26;
            r15.draw(r18, this.view);
            goto L31
        L26:
            if (r23 > 0.0f) goto L28;
        L30:
            r15.draw(r18, this.view, false);
            goto L31
        L28:
            if (r15.textLayout == null) goto L30;
            r15.draw(r18, this.view, false);
            r18.save();
            r18.translate(r15.getTextX(), r15.getTextY());
            int r24 = r18.saveLayerAlpha(0.0f, 0.0f, r15.getMeasuredWidth(), r15.getMeasuredHeight(), (int) (r23 * 255.0f), 31);
            r15.textLayout.draw(r18, this.view);
            r18.restoreToCount(r24);
            r18.restore();
            goto L31
        L8:
            if (this.cellBlocks.get(r22).child != r15) goto L11;
            r4 = this.cellBlocks.get(r22);
            r22 = r22 + 1;
            goto L10
        L32:
            r18.restore();
            RectF r25 = AndroidUtilities.rectTmp;
            int r42 = this.root.padLeft;
            r25.set(-r42, 0.0f, (-r42) + AndroidUtilities.dp(12.0f), this.contentHeight);
            this.root.clip.draw(r18, r25, 0, 1.0f);
            int r43 = this.root.getMinWidth() + this.root.padRight;
            Rect r5 = this.padding;
            r25.set(r4 - AndroidUtilities.dp(12.0f), 0.0f, (r43 - r5.left) - r5.right, this.contentHeight);
            this.root.clip.draw(r18, r25, 2, 1.0f);
            r18.restoreToCount(r10);
        }

        private void drawTitle(Canvas r3) {
            if (this.title != null) goto L5;
            return;
        L5:
            r3.save();
            r3.translate(titleDrawX(), 0.0f);
            this.title.draw(r3);
            r3.restore();
        }

        private void ensurePaints() {
            if (this.linePaint != null) goto L5;
            Paint r0 = new Paint(1);
            this.linePaint = r0;
            Paint.Style r1 = Paint.Style.STROKE;
            r0.setStyle(r1);
            this.linePaint.setStrokeWidth(AndroidUtilities.dpf2(0.66f));
            Paint r02 = new Paint();
            this.halfLinePaint = r02;
            r02.setStyle(r1);
            this.halfLinePaint.setStrokeWidth(AndroidUtilities.dpf2(0.66f));
            this.headerPaint = new Paint();
            this.stripPaint = new Paint();
        L5:
            RichMessageLayout r03 = this.root;
            if (r03.isOut() == false) goto L8;
            int r12 = Theme.key_chat_outTableBorder;
        L9:
            int r04 = RichMessageLayout.m4824$$Nest$mgetThemedColor(r03, r12);
            this.linePaint.setColor(r04);
            this.halfLinePaint.setColor(r04);
            Paint r05 = this.headerPaint;
            RichMessageLayout r13 = this.root;
            if (r13.isOut() == false) goto L12;
            int r2 = Theme.key_chat_outTableBackground;
        L13:
            r05.setColor(RichMessageLayout.m4824$$Nest$mgetThemedColor(r13, r2));
            this.stripPaint.setColor(167772160);
            return;
        L12:
            r2 = Theme.key_chat_inTableBackground;
            goto L13
        L8:
            r12 = Theme.key_chat_inTableBorder;
            goto L9
        }

        private void ensureTouchConfig() {
            if (this.touchSlop != 0) goto L8;
            View r0 = this.view;
            if (r0 == null) goto L8;
            ViewConfiguration r02 = ViewConfiguration.get(r0.getContext());
            this.touchSlop = r02.getScaledTouchSlop();
            this.minFlingVelocity = r02.getScaledMinimumFlingVelocity();
            this.maxFlingVelocity = r02.getScaledMaximumFlingVelocity();
        L8:
            if (this.scroller == null) goto L10;
            return;
        L10:
            if (this.view == null) goto L14;
            this.scroller = new OverScroller(this.view.getContext());
            return;
        }

        private TableLayout.Child findCellChildAt(float r6, float r7) {
            float r62 = r6 + this.scrollX;
            float r72 = r7 - this.titleHeight;
            int r0 = this.tableLayout.getChildCount();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L17;
            TableLayout.Child r2 = this.tableLayout.getChildAt(r1);
            if ((r2.textLayout instanceof Text) == false) goto L16;
            if (r62 < r2.x) goto L16;
            if (r62 >= (r3 + r2.getMeasuredWidth())) goto L16;
            if (r72 < r2.y) goto L16;
            if (r72 >= (r3 + r2.getMeasuredHeight())) goto L16;
            return r2;
        L16:
            r1 = r1 + 1;
            goto L3
        L17:
            return null;
        }

        private void resolveWidth(int r5) {
            int r0 = this.intrinsicTableWidth;
            int r1 = this.viewportWidth;
            Rect r2 = this.padding;
            int r52 = Math.max(r0, Math.min(r1, Math.max(0, (r5 - r2.left) - r2.right)));
            this.resolvedTableWidth = r52;
            this.tableLayout.setRenderWidth(r52);
            this.contentHeight = this.tableLayout.getRenderHeight();
        }

        private int titleDrawX() {
            Text r0 = this.title;
            if (r0 != null) goto L6;
            return 0;
        L6:
            int r2 = this.resolvedTableWidth;
            int r1 = r0.right;
            return Math.round(((r2 - (r1 - r0)) / 2.0f) - r0.left);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void appendAccessibilityText(SpannableStringBuilder r5) {
            RichBlock.appendText(r5, this.title, null);
            int r0 = 0;
        L4:
            if (r0 >= this.cellTexts.size()) goto L18;
            Text r1 = this.cellTexts.get(r0);
            if (r1 == null) goto L17;
            StaticLayout r2 = r1.layout;
            if (r2 == null) goto L17;
            if (TextUtils.isEmpty(r2.getText()) == true) goto L17;
            if (r5.length() > 0) goto L14;
        L16:
            r5.append(r1.layout.getText());
            goto L17
        L14:
            if (r5.charAt(r5.length() - 1) == '\n') goto L16;
            r5.append(", ");
        L17:
            r0 = r0 + 1;
            goto L4
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean canDragHorizontally() {
            if (this.maxScrollX <= 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void collectAnimatorBlocks(List<MultiLayoutTypingAnimator.Block> r2) {
            if (this.cellBlocks.isEmpty() == false) goto L6;
            super.collectAnimatorBlocks(r2);
            return;
        L6:
            r2.addAll(this.cellBlocks);
        }

        @Override // org.telegram.ui.Components.TableLayout.TableLayoutDelegate
        public Text createTextLayout(TL_iv.pageTableCell r4, int r5) {
            if (r4 != null) goto L5;
            return null;
        L5:
            int r0 = RichMessageLayout.setBlockFlags(0, 14);
            CharSequence r02 = this.root.formatText(r4.text, r0);
            if (r4.align_right == false) goto L9;
            Layout.Alignment r42 = Layout.Alignment.ALIGN_OPPOSITE;
        L12:
            Text r1 = new Text(this.root, r02, r5, r42);
            r1.setDrawAtOrigin(true);
            return r1;
        L9:
            if (r4.align_center == false) goto L11;
            r42 = Layout.Alignment.ALIGN_CENTER;
            goto L12
        L11:
            r42 = Layout.Alignment.ALIGN_NORMAL;
            goto L12
        }

        @Override // org.telegram.ui.Components.TableLayout.TableLayoutDelegate
        public /* bridge */ /* synthetic */ TableLayout.CellText createTextLayout(TL_iv.pageTableCell r1, int r2) {
            return createTextLayout(r1, r2);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean drawOverlay(Canvas r18, ColorFilter r19) {
            r18.save();
            Rect r2 = this.padding;
            r18.translate(r2.left, r2.top);
            Text r22 = this.title;
            boolean r11 = true;
            if (r22 == null) goto L9;
            AnimatedEmojiSpan.EmojiGroupedSpans r23 = r22.animatedEmojiStack;
            if (r23 == null) goto L9;
            if (r23.holders.isEmpty() == true) goto L9;
            r18.save();
            r18.translate(titleDrawX(), 0.0f);
            Text r24 = this.title;
            AnimatedEmojiSpan.drawAnimatedEmojis(r18, r24.layout, r24.animatedEmojiStack, 0.0f, r24.spoilers, 0.0f, 0.0f, 0.0f, 1.0f, r19);
            r18.restore();
            boolean r25 = true;
        L10:
            r18.translate(0.0f, this.titleHeight);
            int r3 = this.cellTexts.size();
            int r4 = 0;
        L11:
            if (r4 >= r3) goto L30;
            AnimatedEmojiSpan.EmojiGroupedSpans r5 = this.cellTexts.get(r4).animatedEmojiStack;
            if (r5 == null) goto L29;
            if (r5.holders.isEmpty() == true) goto L29;
            RichMessageLayout r26 = this.root;
            float r32 = -r26.padLeft;
            int r27 = r26.getMinWidth() + this.root.padRight;
            Rect r42 = this.padding;
            int r14 = r18.saveLayerAlpha(r32, 0.0f, (r27 - r42.left) - r42.right, this.contentHeight, 255, 31);
            r18.save();
            r18.translate(-this.scrollX, 0.0f);
            int r15 = this.tableLayout.getChildCount();
            int r28 = 0;
        L17:
            if (r28 >= r15) goto L28;
            TableLayout.CellText r43 = this.tableLayout.getChildAt(r28).textLayout;
            if ((r43 instanceof Text) == true) goto L21;
        L20:
            int r16 = r28;
        L27:
            r28 = r16 + 1;
            goto L17
        L21:
            Text r44 = (Text) r43;
            AnimatedEmojiSpan.EmojiGroupedSpans r52 = r44.animatedEmojiStack;
            if (r52 == null) goto L20;
            if (r52.holders.isEmpty() == true) goto L20;
            r18.save();
            r18.translate(r3.getTextX(), r3.getTextY());
            r16 = r28;
            AnimatedEmojiSpan.drawAnimatedEmojis(r18, r44.layout, r44.animatedEmojiStack, 0.0f, r44.spoilers, 0.0f, 0.0f, 0.0f, 1.0f, r19);
            r18.restore();
            goto L27
        L28:
            r18.restore();
            RectF r29 = AndroidUtilities.rectTmp;
            int r33 = this.root.padLeft;
            r29.set(-r33, 0.0f, (-r33) + AndroidUtilities.dp(12.0f), this.contentHeight);
            this.root.clip.draw(r18, r29, 0, 1.0f);
            int r34 = this.root.getMinWidth() + this.root.padRight;
            Rect r6 = this.padding;
            r29.set(r3 - AndroidUtilities.dp(12.0f), 0.0f, (r34 - r6.left) - r6.right, this.contentHeight);
            this.root.clip.draw(r18, r29, 2, 1.0f);
            r18.restoreToCount(r14);
        L31:
            r18.restore();
            return r11;
        L29:
            r4 = r4 + 1;
            goto L11
        L30:
            r11 = r25;
        L9:
            r25 = false;
            goto L10
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void drawWithTyping(Canvas r6) {
            MultiLayoutTypingAnimator r0 = this.typingAnimator;
            if (r0 != null) goto L5;
        L16:
            draw(r6);
            return;
        L5:
            if (r0.isRunning() == false) goto L16;
            if (this.cellBlocks.isEmpty() == true) goto L16;
            if (r0.indexOf(this.cellBlocks.get(0)) < 0) goto L16;
            float r1 = r0.getBlockAlpha(this.cellBlocks.get(0));
            if (r1 > 0.0f) goto L14;
            return;
        L14:
            r6.save();
            Rect r3 = this.padding;
            r6.translate(r3.left, r3.top);
            drawTitle(r6);
            r6.translate(0.0f, this.titleHeight);
            drawCellsWithTyping(r6, r0, r1);
            r6.restore();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean findLink(CharacterStyle r8, int r9, FoundLink r10) {
            Text r0 = this.title;
            if (r0 != null) goto L5;
        L8:
            int r02 = this.tableLayout.getChildCount();
            int r3 = 0;
        L9:
            if (r3 >= r02) goto L18;
            TableLayout.CellText r5 = this.tableLayout.getChildAt(r3).textLayout;
            if ((r5 instanceof Text) == false) goto L17;
            if (((Text) r5).fillFoundLink(r8, r10) == false) goto L17;
            r10.x = ((this.padding.left + r4.getTextX()) - this.scrollX) - r5.drawLeft();
            r10.y = ((r9 + this.padding.top) + this.titleHeight) + r4.getTextY();
            return true;
        L17:
            r3 = r3 + 1;
            goto L9
        L18:
            return false;
        L5:
            if (r0.fillFoundLink(r8, r10) == false) goto L8;
            r10.x = (this.padding.left + titleDrawX()) - this.title.drawLeft();
            r10.y = r9 + this.padding.top;
            return true;
        }

        public Paint getHalfLinePaint() {
            ensurePaints();
            return this.halfLinePaint;
        }

        @Override // org.telegram.ui.Components.TableLayout.TableLayoutDelegate
        public Paint getHeaderPaint() {
            ensurePaints();
            return this.headerPaint;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            return (((this.padding.top + this.titleHeight) + this.contentHeight) + AndroidUtilities.dp(10.0f)) + this.padding.bottom;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getLastLineWidth() {
            return getMinWidth();
        }

        @Override // org.telegram.ui.Components.TableLayout.TableLayoutDelegate
        public Paint getLinePaint() {
            ensurePaints();
            return this.linePaint;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            Rect r0 = this.padding;
            return (r0.left + this.intrinsicTableWidth) + r0.right;
        }

        @Override // org.telegram.ui.Components.TableLayout.TableLayoutDelegate
        public Paint getStripPaint() {
            ensurePaints();
            return this.stripPaint;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public TextSelectionHelper.TextLayoutBlock[] getText() {
            return this.textsArr;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean isHorizontallyDragging() {
            if (this.dragging == true) goto L11;
            OverScroller r1 = this.scroller;
            if (r1 != null) goto L7;
            return false;
        L7:
            if (r1.isFinished() == false) goto L14;
            return false;
        L14:
            return true;
        L11:
            return true;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onAttachedToWindow() {
            Text r0 = this.title;
            if (r0 == null) goto L5;
            r0.attach(this.view);
        L5:
            ArrayList<Text> r02 = this.cellTexts;
            int r1 = r02.size();
            int r2 = 0;
        L6:
            if (r2 >= r1) goto L8;
            Text r3 = r02.get(r2);
            r2 = r2 + 1;
            r3.attach(this.view);
            goto L6
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDetachedFromWindow() {
            Text r0 = this.title;
            if (r0 == null) goto L5;
            r0.detach(this.view);
        L5:
            ArrayList<Text> r02 = this.cellTexts;
            int r1 = r02.size();
            int r2 = 0;
        L6:
            if (r2 >= r1) goto L8;
            Text r3 = r02.get(r2);
            r2 = r2 + 1;
            r3.detach(this.view);
            goto L6
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r11) {
            drawTitle(r11);
            r11.save();
            r11.translate(0.0f, this.titleHeight);
            RichMessageLayout r0 = this.root;
            float r4 = -r0.padLeft;
            int r02 = r0.getMinWidth() + this.root.padRight;
            Rect r2 = this.padding;
            r11.saveLayerAlpha(r4, 0.0f, (r02 - r2.left) - r2.right, this.contentHeight, 255, 31);
            r11.save();
            r11.translate(-this.scrollX, 0.0f);
            int r112 = this.tableLayout.getChildCount();
            int r22 = 0;
        L3:
            if (r22 >= r112) goto L5;
            this.tableLayout.getChildAt(r22).draw(r11, this.view);
            r22 = r22 + 1;
            goto L3
        L5:
            r11.restore();
            RectF r113 = AndroidUtilities.rectTmp;
            int r23 = this.root.padLeft;
            r113.set(-r23, 0.0f, (-r23) + AndroidUtilities.dp(12.0f), this.contentHeight);
            this.root.clip.draw(r11, r113, 0, 1.0f);
            int r03 = this.root.getMinWidth() + this.root.padRight;
            Rect r24 = this.padding;
            r113.set(r0 - AndroidUtilities.dp(12.0f), 0.0f, (r03 - r24.left) - r24.right, this.contentHeight);
            this.root.clip.draw(r11, r113, 2, 1.0f);
            r11.restore();
            r11.restore();
        }

        @Override // org.telegram.ui.Components.TableLayout.TableLayoutDelegate
        public /* bridge */ /* synthetic */ void onLayoutChild(TableLayout.CellText r1, int r2, int r3) {
            super.onLayoutChild(r1, r2, r3);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r18) {
            int r2 = r18.getActionMasked();
            int r5 = 0;
            if (r2 != 0) goto L33;
            ensureTouchConfig();
            OverScroller r22 = this.scroller;
            if (r22 != null) goto L7;
        L9:
            this.downX = r18.getX();
            this.downY = r18.getY();
            this.downScrollX = this.scrollX;
            this.dragging = false;
            VelocityTracker r23 = this.velocityTracker;
            if (r23 != null) goto L12;
            this.velocityTracker = VelocityTracker.obtain();
        L13:
            this.velocityTracker.addMovement(r18);
            this.pressedCellText = null;
            this.textHandlingTouch = false;
            if (this.title == null) goto L21;
            if (r18.getY() >= this.titleHeight) goto L21;
            float r24 = titleDrawX();
            r18.offsetLocation(-r24, 0.0f);
            boolean r3 = this.title.onTouchEvent(r18);
            r18.offsetLocation(r24, 0.0f);
            if (r3 == false) goto L21;
            this.pressedCellText = this.title;
            this.cellDx = r24;
            this.cellDy = 0.0f;
            this.textHandlingTouch = true;
        L21:
            if (this.textHandlingTouch == true) goto L26;
            TableLayout.Child r25 = findCellChildAt(r18.getX(), r18.getY());
            if (r25 == null) goto L26;
            this.pressedCellText = (Text) r25.textLayout;
            this.cellDx = r25.getTextX() - this.scrollX;
            this.cellDy = this.titleHeight + r25.getTextY();
            Text.m4866$$Nest$msetSoleButtonHitBounds(this.pressedCellText, r25.x - r25.getTextX(), r25.y - r25.getTextY(), (r25.x + r25.getMeasuredWidth()) - r25.getTextX(), (r25.y + r25.getMeasuredHeight()) - r25.getTextY());
            r18.offsetLocation(-this.cellDx, -this.cellDy);
            this.textHandlingTouch = this.pressedCellText.onTouchEvent(r18);
            r18.offsetLocation(this.cellDx, this.cellDy);
        L26:
            if (this.textHandlingTouch == false) goto L28;
        L31:
            return true;
        L28:
            if (this.maxScrollX > 0) goto L31;
            return false;
        L12:
            r23.clear();
            goto L13
        L7:
            if (r22.isFinished() == true) goto L9;
            this.scroller.forceFinished(true);
            goto L9
        L33:
            if (r2 != 2) goto L65;
            VelocityTracker r26 = this.velocityTracker;
            if (r26 == null) goto L37;
            r26.addMovement(r18);
        L37:
            float r27 = r18.getX() - this.downX;
            if (this.dragging == true) goto L49;
            if (this.maxScrollX <= 0) goto L49;
            if (Math.abs(r27) <= this.touchSlop) goto L49;
            this.dragging = true;
            requestDisallowParentIntercept(true);
            if (this.textHandlingTouch == false) goto L49;
            if (this.pressedCellText == null) goto L49;
            MotionEvent r1 = MotionEvent.obtain(r18);
            r1.setAction(3);
            r1.offsetLocation(-this.cellDx, -this.cellDy);
            this.pressedCellText.onTouchEvent(r1);
            r1.recycle();
            this.textHandlingTouch = false;
        L49:
            if (this.dragging == false) goto L64;
            int r12 = (int) (this.downScrollX - r27);
            if (r12 < 0) goto L54;
            r5 = r12;
        L54:
            int r13 = this.maxScrollX;
            if (r5 <= r13) goto L58;
            r5 = r13;
        L58:
            if (r5 == this.scrollX) goto L62;
            this.scrollX = r5;
            placeTexts(this.layoutX, this.layoutY, this.layoutRow);
            View r0 = this.view;
            if (r0 == null) goto L62;
            r0.invalidate();
        L62:
            return true;
        L64:
            return this.textHandlingTouch;
        L65:
            if (r2 == 1) goto L69;
            if (r2 == 3) goto L69;
            return false;
        L69:
            boolean r6 = this.dragging;
            this.dragging = false;
            if (r6 == false) goto L82;
            requestDisallowParentIntercept(false);
            if (r2 != 1) goto L82;
            VelocityTracker r28 = this.velocityTracker;
            if (r28 == null) goto L82;
            if (this.scroller == null) goto L82;
            if (this.view == null) goto L82;
            r28.addMovement(r18);
            this.velocityTracker.computeCurrentVelocity(1000, this.maxFlingVelocity);
            float r29 = -this.velocityTracker.getXVelocity();
            if (Math.abs(r29) <= this.minFlingVelocity) goto L82;
            this.scroller.fling(this.scrollX, 0, (int) r29, 0, 0, this.maxScrollX, 0, 0);
            this.view.postOnAnimation(this.flingTick);
        L82:
            if (r6 == false) goto L84;
        L88:
            boolean r14 = this.textHandlingTouch;
            this.textHandlingTouch = false;
            this.pressedCellText = null;
            VelocityTracker r210 = this.velocityTracker;
            if (r210 == null) goto L91;
            r210.recycle();
            this.velocityTracker = null;
        L91:
            if (r6 == true) goto L95;
            if (r14 == true) goto L95;
            return false;
        L95:
            return true;
        L84:
            if (this.textHandlingTouch == false) goto L88;
            if (this.pressedCellText == null) goto L88;
            r18.offsetLocation(-this.cellDx, -this.cellDy);
            this.pressedCellText.onTouchEvent(r18);
            r18.offsetLocation(this.cellDx, this.cellDy);
            goto L88
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void placeTexts(int r7, int r8, int r9) {
            this.layoutX = r7;
            this.layoutY = r8;
            this.layoutRow = r9;
            Text r0 = this.title;
            if (r0 == null) goto L5;
            r0.setX((titleDrawX() + r7) - this.title.drawLeft());
            this.title.setY(r8);
            this.title.setRow(r9);
        L5:
            int r02 = this.tableLayout.getChildCount();
            int r1 = 0;
        L6:
            if (r1 >= r02) goto L11;
            TableLayout.Child r2 = this.tableLayout.getChildAt(r1);
            TableLayout.CellText r3 = r2.textLayout;
            if ((r3 instanceof Text) == false) goto L10;
            Text r32 = (Text) r3;
            r32.setX(((r2.getTextX() + r7) - this.scrollX) - r32.drawLeft());
            r32.setY((this.titleHeight + r8) + r2.getTextY());
            r32.setRow(r9);
        L10:
            r1 = r1 + 1;
            goto L6
        }
    }

    public static class RichTextBlock extends RichBlock {
        private final boolean centered;
        protected int contentPaddingBottom;
        protected int contentPaddingTop;
        public int quoteAuthorStart;
        public final Text text;
        public final Text[] texts;

        public RichTextBlock(RichMessageLayout r7, Rect r8, int r9, CharSequence r10) {
            this(r7, r8, r9, r10, Layout.Alignment.ALIGN_NORMAL);
        }

        public RichTextBlock(RichMessageLayout r3, Rect r4, int r5, CharSequence r6, Layout.Alignment r7) {
            super(r3, r4, r5);
            this.quoteAuthorStart = -1;
            if (r7 != Layout.Alignment.ALIGN_CENTER) goto L5;
            boolean r42 = true;
        L6:
            this.centered = r42;
            Text r43 = new Text(r3, r6, this.maxWidth, r7);
            this.text = r43;
            this.texts = new Text[]{r43};
            return;
        L5:
            r42 = false;
            goto L6
        }

        private int rtlOffset() {
            boolean r0 = this.centered;
            RichMessageLayout r1 = this.root;
            if (r0 == false) goto L8;
            int r02 = r1.getMinWidth();
            RichMessageLayout r12 = this.root;
            int r03 = (((r02 + r12.padRight) - r12.padLeft) - this.text.getMinWidth()) / 2;
            int r3 = this.padding.left;
        L6:
            return r03 - r3;
        L8:
            if (r1.isRtl() == true) goto L11;
            return 0;
        L11:
            int r04 = (this.root.getMinWidth() + this.root.padRight) - AndroidUtilities.dp(14.0f);
            Rect r13 = this.padding;
            r03 = (r04 - r13.right) - r13.left;
            r3 = this.text.getMinWidth();
            goto L6
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void appendAccessibilityText(SpannableStringBuilder r2) {
            RichBlock.appendText(r2, this.text, this.texts);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean findLink(CharacterStyle r2, int r3, FoundLink r4) {
            if (this.text.fillFoundLink(r2, r4) == false) goto L6;
            r4.x = (this.padding.left + rtlOffset()) - this.text.left;
            r4.y = (r3 + this.padding.top) + this.contentPaddingTop;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean forcesTimeToNewLine() {
            return this.centered;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getContentPaddingTop() {
            return this.contentPaddingTop;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            return (((this.padding.top + this.contentPaddingTop) + this.text.getHeight()) + this.contentPaddingBottom) + this.padding.bottom;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getLastLineWidth() {
            return (this.padding.left + this.text.getLastLineWidth()) + this.padding.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock, org.telegram.ui.MultiLayoutTypingAnimator.Block
        public Layout getLayout() {
            return this.text.layout;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            return (this.padding.left + this.text.getMinWidth()) + this.padding.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public TextSelectionHelper.TextLayoutBlock[] getText() {
            return this.texts;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onAttachedToWindow() {
            this.text.attach(this.view);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDetachedFromWindow() {
            this.text.detach(this.view);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r6) {
            r6.save();
            r6.translate(0.0f, this.contentPaddingTop);
            int r0 = rtlOffset();
            Text r2 = this.text;
            if (r0 == 0) goto L5;
            r2.setX((this.padding.left + r0) - r2.left);
            r6.save();
            r6.translate(r0, 0.0f);
            this.text.draw(r6);
            r6.restore();
        L6:
            r6.restore();
            return;
        L5:
            r2.draw(r6);
            goto L6
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDrawFaded(Canvas r6, int r7, float r8) {
            r6.save();
            r6.translate(0.0f, this.contentPaddingTop);
            int r0 = rtlOffset();
            Text r2 = this.text;
            if (r0 == 0) goto L5;
            r2.setX((this.padding.left + r0) - r2.left);
            r6.save();
            r6.translate(r0, 0.0f);
            this.text.drawFade(r6, r7, r8);
            r6.restore();
        L6:
            r6.restore();
            return;
        L5:
            r2.drawFade(r6, r7, r8);
            goto L6
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r4) {
            int r0 = rtlOffset();
            r4.offsetLocation(-r0, -this.contentPaddingTop);
            boolean r1 = this.text.onTouchEvent(r4);
            r4.offsetLocation(r0, this.contentPaddingTop);
            return r1;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void placeTexts(int r2, int r3, int r4) {
            super.placeTexts(r2, r3 + this.contentPaddingTop, r4);
            int r32 = rtlOffset();
            if (r32 == 0) goto L6;
            Text r1 = this.text;
            r1.setX((r2 + r32) - r1.left);
            return;
        }

        public void setContentPadding(int r1, int r2) {
            this.contentPaddingTop = r1;
            this.contentPaddingBottom = r2;
            updateListMarkerY();
        }
    }

    public static class RichTextBlockQuote extends RichTextBlock {
        public final TL_iv.pageBlockBlockquote block;
        private ButtonBounce bounce;
        private boolean capturedByParent;
        private GradientClip clip;
        public int collapsedHeightToDraw;
        public float collapsedProgress;
        private boolean currentCollapsed;
        private boolean pressed;
        private boolean prevCollapsed;
        public final Drawable quoteArrow;
        private int quoteArrowColor;

        /* renamed from: -$$Nest$fgetcurrentCollapsed, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4857$$Nest$fgetcurrentCollapsed(RichTextBlockQuote r0) {
            return r0.currentCollapsed;
        }

        /* renamed from: -$$Nest$fgetprevCollapsed, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4858$$Nest$fgetprevCollapsed(RichTextBlockQuote r0) {
            return r0.prevCollapsed;
        }

        public RichTextBlockQuote(RichMessageLayout r1, Rect r2, int r3, TL_iv.pageBlockBlockquote r4, CharSequence r5) {
            super(r1, r2, r3, r5);
            this.block = r4;
            this.quoteArrow = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.arrow_more).mutate();
            boolean r12 = r4.collapsed;
            this.currentCollapsed = r12;
            this.prevCollapsed = r12;
        }

        private void ensureBounce() {
            if (this.bounce != null) goto L8;
            View r0 = this.root.view;
            if (r0 == null) goto L9;
            this.bounce = new ButtonBounce(r0);
            return;
        L9:
            return;
        }

        private void toggle() {
            this.root.snapshotForBlockquoteAnimation();
            this.currentCollapsed = !this.currentCollapsed;
            RichMessageLayout r0 = this.root;
            r0.blockquoteAnimating = true;
            r0.reposition();
            View r02 = this.root.view;
            if (r02 == null) goto L5;
            r02.invalidate();
        L5:
            ChatMessageCell r03 = this.root.getCell();
            ChatMessageCell.ChatMessageCellDelegate r2 = this.root.getDelegate();
            if (r03 == null) goto L10;
            if (r2 == null) goto L11;
            r2.forceUpdate(r03, true, true);
            return;
        L11:
            return;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public float getBackgroundScale() {
            ButtonBounce r1 = this.bounce;
            if (r1 != null) goto L5;
            return 1.0f;
        L5:
            return r1.getScale(0.01f);
        }

        public int getCollapsedHeight() {
            return (int) Math.min(((this.padding.top + this.contentPaddingTop) + ((this.text.layout.getPaint().getTextSize() * 1.4f) * 3.0f)) + this.padding.bottom, super.getHeight());
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichTextBlock, org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            if (this.currentCollapsed == false) goto L7;
            return getCollapsedHeight();
        L7:
            return super.getHeight();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichTextBlock, org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r15) {
            ButtonBounce r0 = this.bounce;
            if (r0 == null) goto L5;
            float r02 = r0.getScale(0.01f);
        L7:
            if (r02 == 1.0f) goto L10;
            r15.save();
            r15.scale(r02, r02, this.maxWidth / 2.0f, getHeight() / 2.0f);
        L10:
            if (this.collapsedProgress <= 0.0f) goto L12;
            boolean r03 = true;
        L13:
            int r5 = (this.collapsedHeightToDraw - this.padding.bottom) - this.contentPaddingBottom;
            r15.save();
            r15.clipRect(0, 0, this.maxWidth, r5);
            if (r03 == false) goto L16;
            Canvas r7 = r15;
            r7.saveLayer(0.0f, 0.0f, this.maxWidth, r5, null);
        L17:
            super.onDraw(r7);
            if (r03 == true) goto L20;
        L23:
            r7.restore();
            if (this.root.quoteLine.getColor() == this.quoteArrowColor) goto L26;
            Drawable r04 = this.quoteArrow;
            int r6 = this.root.quoteLine.getColor();
            this.quoteArrowColor = r6;
            r04.setColorFilter(new PorterDuffColorFilter(r6, PorterDuff.Mode.SRC_IN));
        L26:
            DrawableUtils.setBounds(this.quoteArrow, this.root.getMinWidth() - AndroidUtilities.dp(24.0f), ((r5 - AndroidUtilities.dp(16.0f)) - AndroidUtilities.dp(2.0f)) + AndroidUtilities.dp(8.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), 17);
            r7.save();
            r7.rotate(AndroidUtilities.lerp(180, 0, this.collapsedProgress), this.quoteArrow.getBounds().exactCenterX(), this.quoteArrow.getBounds().exactCenterY());
            this.quoteArrow.draw(r7);
            r7.restore();
            if (r02 == 1.0f) goto L30;
            r7.restore();
            return;
        L30:
            return;
        L20:
            if (this.clip != null) goto L22;
            this.clip = new GradientClip();
        L22:
            RectF r05 = AndroidUtilities.rectTmp;
            r05.set(0.0f, r5 - AndroidUtilities.dp(24.0f), this.maxWidth, r5 + 1);
            this.clip.draw(r7, r05, 3, this.collapsedProgress);
            r7.restore();
            goto L23
        L16:
            r7 = r15;
            goto L17
        L12:
            r03 = false;
            goto L13
        L5:
            r02 = 1.0f;
            goto L7
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichTextBlock, org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r6) {
            int r0 = r6.getActionMasked();
            if (this.capturedByParent == false) goto L9;
            if (r0 == 1) goto L6;
            if (r0 == 3) goto L6;
        L8:
            return super.onTouchEvent(r6);
        L6:
            this.capturedByParent = false;
            goto L8
        L9:
            if (r0 != 0) goto L17;
            boolean r62 = super.onTouchEvent(r6);
            this.capturedByParent = r62;
            if (r62 == false) goto L13;
            return true;
        L13:
            this.pressed = true;
            ensureBounce();
            ButtonBounce r5 = this.bounce;
            if (r5 == null) goto L16;
            r5.setPressed(true);
        L16:
            return true;
        L17:
            if (r0 == 1) goto L19;
            if (r0 != 3) goto L34;
            this.pressed = false;
            ButtonBounce r63 = this.bounce;
            if (r63 == null) goto L34;
            r63.setPressed(false);
        L34:
            return this.pressed;
        L19:
            if (this.pressed == false) goto L28;
            this.pressed = false;
            ButtonBounce r64 = this.bounce;
            if (r64 == null) goto L23;
            r64.setPressed(false);
        L23:
            View r65 = this.root.view;
            if (r65 == null) goto L26;
            r65.playSoundEffect(0);
        L26:
            toggle();
            return true;
        L28:
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void snapshot() {
            super.snapshot();
            this.prevCollapsed = this.currentCollapsed;
        }
    }

    public static class RichTextWithAuthorBlock extends RichBlock {
        public final Text author;
        private final boolean centered;
        public final Text text;
        private final Text[] texts;

        public RichTextWithAuthorBlock(RichMessageLayout r3, Rect r4, int r5, CharSequence r6, CharSequence r7, Layout.Alignment r8) {
            super(r3, r4, r5);
            if (r8 != Layout.Alignment.ALIGN_CENTER) goto L5;
            boolean r42 = true;
        L6:
            this.centered = r42;
            Text r43 = new Text(r3, r6, this.maxWidth, r8);
            this.text = r43;
            if (TextUtils.isEmpty(r7) == true) goto L9;
            Text r62 = new Text(r3, r7, this.maxWidth, r8);
        L10:
            this.author = r62;
            if (r62 != null) goto L13;
            Text[] r32 = {r43};
        L14:
            this.texts = r32;
            return;
        L13:
            r32 = new Text[]{r43, r62};
            goto L14
        L9:
            r62 = null;
            goto L10
        L5:
            r42 = false;
            goto L6
        }

        private int gap() {
            if (this.author != null) goto L5;
            return 0;
        L5:
            return AndroidUtilities.dp(2.0f);
        }

        private int offset(Text r4) {
            boolean r0 = this.centered;
            RichMessageLayout r1 = this.root;
            if (r0 == false) goto L8;
            int r02 = r1.getMinWidth();
            RichMessageLayout r12 = this.root;
            int r03 = (((r02 + r12.padRight) - r12.padLeft) - r4.getMinWidth()) / 2;
            int r3 = this.padding.left;
        L6:
            return r03 - r3;
        L8:
            if (r1.isRtl() == true) goto L11;
            return 0;
        L11:
            int r04 = (this.root.getMinWidth() + this.root.padRight) - AndroidUtilities.dp(14.0f);
            Rect r32 = this.padding;
            r03 = (r04 - r32.right) - r32.left;
            r3 = r4.getMinWidth();
            goto L6
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void appendAccessibilityText(SpannableStringBuilder r2) {
            RichBlock.appendText(r2, null, this.texts);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean findLink(CharacterStyle r3, int r4, FoundLink r5) {
            if (this.text.fillFoundLink(r3, r5) == false) goto L6;
            r5.x = (this.padding.left + offset(this.text)) - this.text.left;
            r5.y = r4 + this.padding.top;
            return true;
        L6:
            Text r0 = this.author;
            if (r0 != null) goto L9;
            return false;
        L9:
            if (r0.fillFoundLink(r3, r5) == false) goto L14;
            r5.x = (this.padding.left + offset(this.author)) - this.author.left;
            r5.y = ((r4 + this.padding.top) + this.text.getHeight()) + gap();
            return true;
        L14:
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean forcesTimeToNewLine() {
            return this.centered;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            int r0 = (this.padding.top + this.text.getHeight()) + gap();
            Text r1 = this.author;
            if (r1 == null) goto L5;
            int r12 = r1.getHeight();
        L7:
            return (r0 + r12) + this.padding.bottom;
        L5:
            r12 = 0;
            goto L7
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getLastLineWidth() {
            Text r0 = this.author;
            if (r0 != null) goto L7;
            r0 = this.text;
        L7:
            return (this.padding.left + r0.getLastLineWidth()) + this.padding.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock, org.telegram.ui.MultiLayoutTypingAnimator.Block
        public Layout getLayout() {
            return this.text.layout;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            return (this.padding.left + getTextWidth()) + this.padding.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public TextSelectionHelper.TextLayoutBlock[] getText() {
            return this.texts;
        }

        public int getTextWidth() {
            int r0 = this.text.getMinWidth();
            Text r1 = this.author;
            if (r1 == null) goto L5;
            int r12 = r1.getMinWidth();
        L7:
            return Math.max(r0, r12);
        L5:
            r12 = 0;
            goto L7
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onAttachedToWindow() {
            this.text.attach(this.view);
            Text r0 = this.author;
            if (r0 == null) goto L6;
            r0.attach(this.view);
            return;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDetachedFromWindow() {
            this.text.detach(this.view);
            Text r0 = this.author;
            if (r0 == null) goto L6;
            r0.detach(this.view);
            return;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r4) {
            r4.save();
            r4.translate(offset(this.text), 0.0f);
            this.text.draw(r4);
            r4.restore();
            if (this.author == null) goto L6;
            r4.save();
            r4.translate(offset(this.author), this.text.getHeight() + gap());
            this.author.draw(r4);
            r4.restore();
            return;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r5) {
            int r0 = this.text.getHeight();
            float r1 = r5.getY();
            int r3 = 0;
            if (r1 < 0.0f) goto L8;
            if (r1 >= r0) goto L8;
            Text r02 = this.text;
            int r4 = offset(r02);
        L12:
            r5.offsetLocation(-r4, -r3);
            boolean r03 = r02.onTouchEvent(r5);
            r5.offsetLocation(r4, r3);
            return r03;
        L8:
            if (this.author != null) goto L10;
        L14:
            return false;
        L10:
            if (r1 < (gap() + r0)) goto L14;
            Text r12 = this.author;
            int r2 = offset(r12);
            r3 = r0 + gap();
            r02 = r12;
            r4 = r2;
            goto L12
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void placeTexts(int r4, int r5, int r6) {
            Text r0 = this.text;
            r0.setX((offset(r0) + r4) - this.text.left);
            this.text.setY(r5);
            this.text.setRow(r6);
            Text r02 = this.author;
            if (r02 == null) goto L6;
            r02.setX((r4 + offset(r02)) - this.author.left);
            this.author.setY((r5 + this.text.getHeight()) + gap());
            this.author.setRow(r6);
            return;
        }
    }

    public static class RichThinkingBlock extends RichBlock {
        public LinearGradient gradient;
        public int gradientColor;
        public final Matrix matrix;
        public final Paint paint;
        public final Text text;
        public final Text[] texts;

        /* renamed from: $r8$lambda$o-X0cinN8i2jHavfbf61etAy_tU, reason: not valid java name */
        public static /* synthetic */ void m4859$r8$lambda$oX0cinN8i2jHavfbf61etAy_tU(RichThinkingBlock r0, View r1, Canvas r2) {
            r0.lambda$onDrawFaded$0(r1, r2);
        }

        public RichThinkingBlock(RichMessageLayout r2, Rect r3, int r4, CharSequence r5) {
            super(r2, r3, r4);
            this.matrix = new Matrix();
            this.paint = new Paint(1);
            Text r32 = new Text(r2, r5, this.maxWidth);
            this.text = r32;
            this.texts = new Text[]{r32};
        }

        private /* synthetic */ void lambda$onDrawFaded$0(View r22, Canvas r23) {
            Text r1 = this.text;
            SpoilerEffect.renderWithRipple(r22, false, -1, 0, r1.spoilersPatchedTextLayout, 0, r1.layout, r1.spoilers, r23, false);
            Text r0 = this.text;
            AnimatedEmojiSpan.drawAnimatedEmojis(r23, r0.layout, r0.animatedEmojiStack, 0.0f, r0.spoilers, 0.0f, 0.0f, 0.0f, 1.0f);
        }

        private int rtlOffset() {
            if (this.root.isRtl() == true) goto L6;
            return 0;
        L6:
            int r0 = (this.root.getMinWidth() + this.root.padRight) - AndroidUtilities.dp(14.0f);
            Rect r1 = this.padding;
            return ((r0 - r1.right) - r1.left) - this.text.getMinWidth();
        }

        private void updateGradient() {
            RichMessageLayout r0 = this.root;
            if (r0.isOut() == false) goto L5;
            int r1 = Theme.key_chat_messageTextOut;
        L6:
            int r02 = RichMessageLayout.m4824$$Nest$mgetThemedColor(r0, r1);
            if (this.gradient != null) goto L9;
        L12:
            this.gradientColor = r02;
            LinearGradient r2 = new LinearGradient(0.0f, 0.0f, this.maxWidth, 0.0f, new int[]{Theme.multAlpha(r02, 0.7f), Theme.multAlpha(r02, 0.25f), Theme.multAlpha(r02, 0.7f)}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.REPEAT);
            this.gradient = r2;
            this.paint.setShader(r2);
            this.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            return;
        L9:
            if (this.gradientColor != r02) goto L12;
            return;
        L5:
            r1 = Theme.key_chat_messageTextIn;
            goto L6
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void appendAccessibilityText(SpannableStringBuilder r2) {
            RichBlock.appendText(r2, this.text, this.texts);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean drawOverlay(Canvas r1, ColorFilter r2) {
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean findLink(CharacterStyle r2, int r3, FoundLink r4) {
            if (this.text.fillFoundLink(r2, r4) == false) goto L6;
            r4.x = (this.padding.left + rtlOffset()) - this.text.left;
            r4.y = r3 + this.padding.top;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            return (this.padding.top + this.text.getHeight()) + this.padding.bottom;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getLastLineWidth() {
            return (this.padding.left + this.text.getLastLineWidth()) + this.padding.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock, org.telegram.ui.MultiLayoutTypingAnimator.Block
        public Layout getLayout() {
            return this.text.layout;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getMinWidth() {
            return (this.padding.left + this.text.getMinWidth()) + this.padding.right;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public TextSelectionHelper.TextLayoutBlock[] getText() {
            return this.texts;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onAttachedToWindow() {
            this.text.attach(this.view);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDetachedFromWindow() {
            this.text.detach(this.view);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDraw(Canvas r13) {
            r13.saveLayerAlpha(0.0f, 0.0f, this.root.getMinWidth(), getHeight(), 255, 31);
            int r10 = rtlOffset();
            if (r10 == 0) goto L5;
            Text r2 = this.text;
            r2.setX((this.padding.left + r10) - r2.left);
            r13.save();
            r13.translate(r10, 0.0f);
        L5:
            this.text.draw(r13);
            if (this.root.isOverlayActive() == false) goto L8;
            r13.save();
            r13.translate(-this.text.left, 0.0f);
            Text r22 = this.text;
            AnimatedEmojiSpan.drawAnimatedEmojis(r13, r22.layout, r22.animatedEmojiStack, 0.0f, r22.spoilers, 0.0f, 0.0f, 0.0f, 1.0f);
            r13.restore();
        L8:
            if (r10 == 0) goto L10;
            r13.restore();
        L10:
            updateGradient();
            this.matrix.reset();
            this.matrix.postTranslate(((System.currentTimeMillis() % 2000) / 2000.0f) * this.maxWidth, 0.0f);
            this.gradient.setLocalMatrix(this.matrix);
            r13.drawRect(0.0f, 0.0f, this.root.getMinWidth(), getHeight(), this.paint);
            r13.restore();
            View r0 = this.view;
            if (r0 == null) goto L14;
            r0.invalidate();
            return;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void onDrawFaded(Canvas r9, int r10, float r11) {
            r9.saveLayerAlpha(0.0f, 0.0f, this.root.getMinWidth(), getHeight(), 255, 31);
            int r2 = rtlOffset();
            if (r2 == 0) goto L5;
            Text r3 = this.text;
            r3.setX((this.padding.left + r2) - r3.left);
        L5:
            r9.save();
            r9.translate(r2 - this.text.left, 0.0f);
            int r22 = this.root.textPaint.getColor();
            this.root.textPaint.setColor(-1);
            RichMessageLayout r4 = this.root;
            TextPaint r5 = r4.textPaint;
            if (r4.isOut() == false) goto L8;
            int r6 = Theme.key_chat_messageLinkOut;
        L9:
            r5.linkColor = RichMessageLayout.m4824$$Nest$mgetThemedColor(r4, r6);
            MultiLayoutTypingAnimator.drawLayoutWithLastLineFade(r9, this.text.layout, r10, r11, new RichMessageLayout$RichThinkingBlock$$ExternalSyntheticLambda0(this, this.view));
            r9.restore();
            this.root.textPaint.setColor(r22);
            updateGradient();
            this.matrix.reset();
            this.matrix.postTranslate(((System.currentTimeMillis() % 2000) / 2000.0f) * this.maxWidth, 0.0f);
            this.gradient.setLocalMatrix(this.matrix);
            r9.drawRect(0.0f, 0.0f, this.root.getMinWidth(), getHeight(), this.paint);
            r9.restore();
            View r0 = this.view;
            if (r0 == null) goto L13;
            r0.invalidate();
            return;
        L13:
            return;
        L8:
            r6 = Theme.key_chat_messageLinkIn;
            goto L9
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r4) {
            int r0 = rtlOffset();
            if (r0 == 0) goto L5;
            r4.offsetLocation(-r0, 0.0f);
            boolean r3 = this.text.onTouchEvent(r4);
            r4.offsetLocation(r0, 0.0f);
            return r3;
        L5:
            return this.text.onTouchEvent(r4);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void placeTexts(int r1, int r2, int r3) {
            super.placeTexts(r1, r2, r3);
            int r22 = rtlOffset();
            if (r22 == 0) goto L6;
            Text r0 = this.text;
            r0.setX((r1 + r22) - r0.left);
            return;
        }
    }

    public static class RichUnsupportedBlock extends RichBlock implements Drawable.Callback {
        public final int index;
        public final int level;
        public Bitmap tornBitmap;
        public TornEdge.Params tornParams;
        public final UnsupportedBlockDrawable unsupportedBlockDrawable;
        public final int unsupportedBlockHeight;
        public final int unsupportedBlockWidth;

        /* renamed from: $r8$lambda$hWM9UH9UMJ4IikZyjX2Z-myCfbc, reason: not valid java name */
        public static /* synthetic */ void m4860$r8$lambda$hWM9UH9UMJ4IikZyjX2ZmyCfbc(RichMessageLayout r1) {
            if (RichMessageLayout.m4821$$Nest$fgetdelegate(r1) == null) goto L6;
            RichMessageLayout.m4821$$Nest$fgetdelegate(r1).didPressAppUpdateButton();
            return;
        }

        public RichUnsupportedBlock(RichMessageLayout r1, Rect r2, int r3, int r4, int r5) {
            super(r1, r2, r3);
            this.index = r4;
            this.level = r5;
            UnsupportedBlockDrawable r22 = new UnsupportedBlockDrawable(r1.resourcesProvider);
            this.unsupportedBlockDrawable = r22;
            r22.setCallback(this);
            r22.setTitle(LocaleController.getString(R.string.UnsupportedBlockTitle));
            r22.setSubtitle(LocaleController.getString(R.string.UnsupportedBlockMessage));
            r22.setButtonText(LocaleController.getString(R.string.UnsupportedUpdate));
            r22.setOnClickListener(new RichMessageLayout$RichUnsupportedBlock$$ExternalSyntheticLambda0(r1));
            int r12 = this.maxWidth;
            this.unsupportedBlockWidth = r12;
            this.unsupportedBlockHeight = r22.measure(r12);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public void draw(Canvas r6) {
            UnsupportedBlockDrawable r0 = this.unsupportedBlockDrawable;
            Rect r1 = this.padding;
            int r2 = r1.left;
            int r12 = r1.top;
            r0.setBounds(r2, r12, this.unsupportedBlockWidth + r2, this.unsupportedBlockHeight + r12);
            this.unsupportedBlockDrawable.draw(r6);
        }

        public float getHeight(ChatMessageCell.TransitionParams r3) {
            if (r3 == null) goto L10;
            RichMessageLayout r0 = this.root;
            if (r0.detailsAnimating == false) goto L6;
        L7:
            int r2 = AndroidUtilities.lerp(this.prevH, this.currH, Math.max(0.0f, Math.min(1.0f, r3.animateChangeProgress)));
        L9:
            return r2;
        L6:
            if (r0.blockquoteAnimating == true) goto L7;
        L10:
            r2 = this.currH;
            goto L9
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public int getHeight() {
            Rect r0 = this.padding;
            return (r0.top + this.unsupportedBlockHeight) + r0.bottom;
        }

        public float getY(ChatMessageCell.TransitionParams r3) {
            if (r3 == null) goto L10;
            RichMessageLayout r0 = this.root;
            if (r0.detailsAnimating == false) goto L6;
        L7:
            float r32 = Math.max(0.0f, Math.min(1.0f, r3.animateChangeProgress));
            return AndroidUtilities.lerp(this.prevY, this.currY, r32);
        L6:
            if (r0.blockquoteAnimating == true) goto L7;
        L10:
            return this.currY;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable r1) {
            View r0 = this.view;
            if (r0 == null) goto L6;
            r0.invalidate();
            return;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichBlock
        public boolean onTouchEvent(MotionEvent r2) {
            View r0 = this.view;
            if (r0 == null) goto L7;
            return this.unsupportedBlockDrawable.onTouchEvent(r0, r2);
        L7:
            return super.onTouchEvent(r2);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable r1, Runnable r2) {
        }
    }

    public static class RichVideoBlock extends RichMediaBlock {
        public final TL_iv.pageBlockVideo block;
        public final TLRPC.Document document;
        public final boolean isVideo;
        public final TLRPC.PhotoSize previewThumb;
        public final boolean realVideo;
        public final TLRPC.PhotoSize strippedThumb;

        public RichVideoBlock(RichMessageLayout r3, Rect r4, int r5, TL_iv.pageBlockVideo r6, boolean r7) {
            super(r3, r4, r5, r7);
            this.block = r6;
            TLRPC.Document r32 = r3.getDocument(r6.video_id);
            this.document = r32;
            boolean r42 = MessageObject.isVideoDocument(r32);
            this.realVideo = r42;
            int r52 = 0;
            if (r42 == false) goto L5;
        L8:
            boolean r43 = true;
        L9:
            this.isVideo = r43;
            if (r32 == null) goto L12;
            this.previewThumb = FileLoader.getClosestPhotoSizeWithSize(r32.thumbs, 320, false, null, true);
            this.strippedThumb = FileLoader.getStrippedPhotoSize(r32.thumbs);
        L13:
            int r44 = 100;
            if (r32 != null) goto L16;
            int r33 = 100;
        L34:
            int r53 = this.maxWidth;
            int r72 = (int) ((r53 / Math.max(1, r44)) * r33);
            Point r0 = AndroidUtilities.displaySize;
            int r02 = (int) (Math.max(r0.x, r0.y) * 0.55f);
            if (r72 <= r02) goto L37;
            r53 = (int) ((r02 / Math.max(1, r33)) * r44);
            r72 = r02;
        L37:
            this.imgWidth = r53;
            this.imgHeight = r72;
            finishLayout();
            return;
        L16:
            if (r52 >= this.document.attributes.size()) goto L21;
            TLRPC.DocumentAttribute r34 = this.document.attributes.get(r52);
            if ((r34 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L19;
            r52 = r52 + 1;
            goto L16
        L19:
            int r54 = r34.w;
            r33 = r34.h;
        L22:
            if (r54 <= 0) goto L26;
            if (r33 <= 0) goto L26;
        L25:
            r44 = r54;
        L26:
            TLRPC.PhotoSize r35 = this.previewThumb;
            if (r35 == null) goto L29;
            r54 = r35.w;
        L30:
            if (r35 == null) goto L32;
            r44 = r35.h;
        L32:
            r33 = r44;
            goto L25
        L29:
            r54 = 100;
            goto L30
        L21:
            r33 = 100;
            r54 = 100;
            goto L22
        L12:
            this.previewThumb = null;
            this.strippedThumb = null;
            goto L13
        L5:
            if (MessageObject.isGifDocument(r32) == true) goto L8;
            r43 = false;
            goto L9
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichMediaBlock
        public boolean allowAutoplay() {
            if (this.realVideo == false) goto L7;
            return SharedConfig.isAutoplayVideo();
        L7:
            return SharedConfig.isAutoplayGifs();
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichMediaBlock
        public void applyImage(boolean r18) {
            TLRPC.Document r1 = this.document;
            if (r1 != null) goto L5;
            return;
        L5:
            TLRPC.PhotoSize r2 = this.strippedThumb;
            ImageLocation r3 = null;
            if (r2 == null) goto L8;
            ImageLocation r9 = ImageLocation.getForDocument(r2, r1);
        L9:
            TLRPC.PhotoSize r12 = this.previewThumb;
            if (r12 == null) goto L12;
            r3 = ImageLocation.getForDocument(r12, this.document);
        L12:
            ImageLocation r7 = r3;
            if (r18 == true) goto L15;
        L22:
            this.imageReceiver.setImage(null, null, r7, null, r9, "b1", null, this.document.size, "mp4", this.root.messageObject, 1);
            return;
        L15:
            if (this.isVideo == false) goto L22;
            if (allowAutoplay() == false) goto L19;
        L20:
            this.imageReceiver.setAllowStartAnimation(true);
            this.imageReceiver.setAutoRepeat(1);
            this.imageReceiver.setImage(ImageLocation.getForDocument(this.document), ImageLoader.AUTOPLAY_FILTER, r7, null, r9, "b1", null, this.document.size, "mp4", this.root.messageObject, 1);
            return;
        L19:
            if (this.mediaForced == false) goto L22;
        L8:
            r9 = null;
            goto L9
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichMediaBlock
        public boolean computeAutoDownload() {
            if (this.document != null) goto L6;
            return false;
        L6:
            if (this.isVideo == true) goto L8;
            return true;
        L8:
            if (allowAutoplay() == true) goto L10;
        L12:
            return false;
        L10:
            if (DownloadController.getInstance(this.root.currentAccount).canDownloadMedia(4, this.document.size) == false) goto L12;
            return true;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichMediaBlock
        public boolean fileExists() {
            if (this.document != null) goto L5;
            return true;
        L5:
            File r0 = FileLoader.getInstance(this.root.currentAccount).getPathToAttach(this.document);
            File r3 = FileLoader.getInstance(this.root.currentAccount).getPathToAttach(this.document, true);
            if (r0 != null) goto L8;
        L9:
            if (r3 != null) goto L11;
            return false;
        L11:
            if (r3.exists() == false) goto L15;
        L12:
            return true;
        L15:
            return false;
        L8:
            if (r0.exists() == true) goto L12;
            goto L9
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichMediaBlock
        public TL_iv.PageBlock getBlock() {
            return this.block;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichMediaBlock
        public String getFileName() {
            return FileLoader.getAttachFileName(this.document);
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichMediaBlock
        public boolean isAnimatedContent() {
            return this.isVideo;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichMediaBlock
        public boolean isRealVideo() {
            return this.realVideo;
        }

        @Override // org.telegram.messenger.RichMessageLayout.RichMediaBlock
        public boolean isSpoiler() {
            TL_iv.pageBlockVideo r0 = this.block;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.spoiler == false) goto L10;
            return true;
        L10:
            return false;
        }
    }

    public static final class SpoilerReveal {
        private ValueAnimator animator;
        private float cx;
        private float cy;
        private float maxR;
        private final Path path;
        float progress;
        boolean revealed;

        /* renamed from: org.telegram.messenger.RichMessageLayout$SpoilerReveal$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ SpoilerReveal this$0;
            final /* synthetic */ View val$view;

            public AnonymousClass1(SpoilerReveal r1, View r2) {
                this.this$0 = r1;
                this.val$view = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                SpoilerReveal r22 = this.this$0;
                r22.revealed = true;
                SpoilerReveal.m4862$$Nest$fputanimator(r22, null);
                View r1 = this.val$view;
                if (r1 == null) goto L6;
                r1.invalidate();
                return;
            }
        }

        /* renamed from: $r8$lambda$oUwmpVBwcCiqG-BU8dZzzI6lgcI, reason: not valid java name */
        public static /* synthetic */ void m4861$r8$lambda$oUwmpVBwcCiqGBU8dZzzI6lgcI(SpoilerReveal r0, View r1, ValueAnimator r2) {
            r0.lambda$start$0(r1, r2);
        }

        /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4862$$Nest$fputanimator(SpoilerReveal r0, ValueAnimator r1) {
            r0.animator = r1;
        }

        public SpoilerReveal() {
            this.path = new Path();
        }

        private /* synthetic */ void lambda$start$0(View r1, ValueAnimator r2) {
            this.progress = ((Float) r2.getAnimatedValue()).floatValue();
            if (r1 == null) goto L6;
            r1.invalidate();
            return;
        }

        public void clipOut(Canvas r6) {
            if (this.progress <= 0.0f) goto L6;
            this.path.rewind();
            this.path.addCircle(this.cx, this.cy, this.maxR * this.progress, Path.Direction.CW);
            r6.clipPath(this.path, Region.Op.DIFFERENCE);
            return;
        }

        public boolean fullyRevealed() {
            if (this.revealed == true) goto L5;
            return false;
        L5:
            if (this.progress < 1.0f) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isRevealing() {
            if (this.revealed == false) goto L5;
            return true;
        L5:
            if (this.animator != null) goto L11;
            return false;
        L11:
            return true;
        }

        public void start(View r2, float r3, float r4, float r5, float r6) {
            if (this.revealed == false) goto L5;
            return;
        L5:
            if (this.animator != null) goto L10;
            this.cx = r3;
            this.cy = r4;
            this.maxR = (float) Math.sqrt((r5 * r5) + (r6 * r6));
            ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.animator = r32;
            r32.setDuration((long) Utilities.clamp(this.maxR * 0.3f, 550.0f, 250.0f));
            this.animator.setInterpolator(CubicBezierInterpolator.EASE_BOTH);
            this.animator.addUpdateListener(new RichMessageLayout$SpoilerReveal$$ExternalSyntheticLambda0(this, r2));
            this.animator.addListener(new AnonymousClass1(this, r2));
            this.animator.start();
            return;
        }
    }

    public static class StyleSpan extends MetricAffectingSpan {
        public final int flags;
        private boolean fullSizeTableEmoji;
        public final boolean metricsOnly;
        public final RichMessageLayout root;

        /* renamed from: -$$Nest$fputfullSizeTableEmoji, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4863$$Nest$fputfullSizeTableEmoji(StyleSpan r0, boolean r1) {
            r0.fullSizeTableEmoji = r1;
        }

        public StyleSpan(RichMessageLayout r2, int r3) {
            this(r2, r3, false);
        }

        public StyleSpan(RichMessageLayout r1, int r2, boolean r3) {
            this.root = r1;
            this.flags = r2;
            this.metricsOnly = r3;
        }

        public void applyStyle(TextPaint r5) {
            Typeface r0 = getTypeface();
            if (r0 == null) goto L5;
            r5.setTypeface(r0);
        L5:
            int r02 = getTextSize();
            if (TLObject.hasFlag(this.flags, 6144) == false) goto L8;
            r02 = r02 - AndroidUtilities.dp(4.0f);
        L8:
            r5.setTextSize(r02);
            if (this.metricsOnly == true) goto L14;
            r5.setFlags(TLObject.setFlag(TLObject.setFlag(r5.getFlags(), 8, TLObject.hasFlag(this.flags, 64)), 16, TLObject.hasFlag(this.flags, 128)));
            if ((this.flags & 15) == 8) goto L14;
            r5.setColor(getTextColor());
        L14:
            if (isBold() == false) goto L19;
            if (FontUtils.isMediumWeightSupported() == true) goto L19;
            r5.setStrokeWidth(0.65f);
            r5.setStyle(Paint.Style.FILL_AND_STROKE);
        L19:
            if (isItalic() == false) goto L24;
            if (FontUtils.isItalicSupported() == true) goto L24;
            r5.setTextSkewX(-0.25f);
        L24:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L28;
            r5.baselineShift -= AndroidUtilities.dp(6.0f);
            return;
        L28:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L31;
            r5.baselineShift += AndroidUtilities.dp(2.0f);
            return;
        }

        public int getTextColor() {
            int r0 = this.flags & 15;
            if (r0 != 11) goto L10;
            RichMessageLayout r2 = this.root;
            if (r2.isOut() == false) goto L7;
            int r02 = Theme.key_chat_outReplyNameText;
        L9:
            return RichMessageLayout.m4824$$Nest$mgetThemedColor(r2, r02);
        L7:
            r02 = Theme.key_chat_inReplyNameText;
            goto L9
        L10:
            RichMessageLayout r22 = this.root;
            if (r0 != 10) goto L19;
            if (r22.isOut() == false) goto L15;
            int r03 = Theme.key_chat_messageTextOut;
        L17:
            return Theme.multAlpha(RichMessageLayout.m4824$$Nest$mgetThemedColor(r22, r03), 0.5f);
        L15:
            r03 = Theme.key_chat_messageTextIn;
            goto L17
        L19:
            if (r22.isOut() == false) goto L21;
            int r04 = Theme.key_chat_messageTextOut;
        L23:
            return RichMessageLayout.m4824$$Nest$mgetThemedColor(r22, r04);
        L21:
            r04 = Theme.key_chat_messageTextIn;
            goto L23
        }

        public int getTextSize() {
            int r0 = this.flags & 15;
            int r1 = SharedConfig.fontSize;
            if (r0 == 14) goto L5;
        L8:
            switch(r0) {
                case 1: goto L34;
                case 2: goto L32;
                case 3: goto L30;
                case 4: goto L28;
                case 5: goto L26;
                case 6: goto L24;
                case 7: goto L22;
                case 8: goto L20;
                case 9: goto L18;
                case 10: goto L16;
                case 11: goto L18;
                case 12: goto L18;
                case 13: goto L22;
                case 14: goto L14;
                case 15: goto L12;
                default: goto L10;
            };
        L10:
            return AndroidUtilities.dp(r1);
        L12:
            return AndroidUtilities.dp(r1 - 2);
        L14:
            return AndroidUtilities.dp(Math.max(8, r1 - 2));
        L16:
            return AndroidUtilities.dp(r1 - 2);
        L18:
            return AndroidUtilities.dp(r1 - 2);
        L20:
            return AndroidUtilities.dp(r1 - 1);
        L22:
            return AndroidUtilities.dp(r1 - 2);
        L24:
            return AndroidUtilities.dp(r1 - 2);
        L26:
            return AndroidUtilities.dp(r1 - 1);
        L28:
            return AndroidUtilities.dp(r1);
        L30:
            return AndroidUtilities.dp(r1 + 1);
        L32:
            return AndroidUtilities.dp(r1 + 2);
        L34:
            return AndroidUtilities.dp(r1 + 3);
        L5:
            if (this.fullSizeTableEmoji == false) goto L8;
            return AndroidUtilities.dp(r1);
        }

        public Typeface getTypeface() {
            int r0 = this.flags;
            int r1 = r0 & 15;
            boolean r2 = true;
            if (r1 >= 1) goto L5;
        L7:
            r2 = false;
        L9:
            if (r1 != 8) goto L13;
            return Typeface.MONOSPACE;
        L13:
            if (TLObject.hasFlag(r0, 256) == false) goto L19;
            if (r2 == true) goto L19;
            if (r1 == 11) goto L19;
            return Typeface.MONOSPACE;
        L19:
            boolean r02 = isBold();
            boolean r4 = isItalic();
            if (r02 == false) goto L28;
            if (r4 == false) goto L28;
            if (FontUtils.isMediumWeightSupported() == false) goto L28;
            if (FontUtils.isItalicSupported() == false) goto L28;
            return AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MEDIUM_ITALIC);
        L28:
            if (r02 == true) goto L30;
        L33:
            if (r4 == false) goto L39;
            if (FontUtils.isItalicSupported() == false) goto L39;
            return AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_ITALIC);
        L39:
            return AndroidUtilities.regular();
        L30:
            if (FontUtils.isMediumWeightSupported() == false) goto L33;
            return AndroidUtilities.bold();
        L5:
            if (r1 > 6) goto L7;
            goto L7
        }

        public boolean isBold() {
            int r2 = this.flags;
            int r0 = r2 & 15;
            if (TLObject.hasFlag(r2, 16) == false) goto L5;
        L12:
            return true;
        L5:
            if (r0 == 11) goto L12;
            if (r0 >= 1) goto L8;
            return false;
        L8:
            if (r0 <= 6) goto L12;
            return false;
        }

        public boolean isItalic() {
            int r2 = this.flags;
            int r0 = r2 & 15;
            if (TLObject.hasFlag(r2, 32) == false) goto L5;
            return true;
        L5:
            if (r0 == 12) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            applyStyle(r1);
        }

        @Override // android.text.style.MetricAffectingSpan
        public void updateMeasureState(TextPaint r1) {
            applyStyle(r1);
        }
    }

    public static class Text implements TextSelectionHelper.TextLayoutBlock, TableLayout.CellText {
        private static final int EMOJI_LINE_HEIGHT_MIN_PERCENT = 70;
        private static Paint markPaint;
        public AnimatedEmojiSpan.EmojiGroupedSpans animatedEmojiStack;
        public int blockX;
        public int blockY;
        public boolean doNotInvalidateEmojiInParent;
        private boolean drawAtOrigin;
        private int emojiOnlyCount;
        public int lastLineRight;
        public final StaticLayout layout;
        public int left;
        public LinkSpanDrawable.LinkCollector linkCollector;
        private boolean longPressFired;
        private Runnable longPressRunnable;
        public LinkPath markPath;
        private RichButtonSpan pressedButtonSpan;
        private AnimatedEmojiSpan pressedEmoji;
        private CharacterStyle pressedLink;
        private LinkSpanDrawable<CharacterStyle> pressedLinkDrawable;
        private int pressedLinkEnd;
        private int pressedLinkStart;
        private SpoilerEffect pressedSpoiler;
        public int right;
        public final RichMessageLayout root;
        public int row;
        private final RectF soleButtonHitBounds;
        public final List<SpoilerEffect> spoilers;
        public final AtomicReference<Layout> spoilersPatchedTextLayout;
        public final Stack<SpoilerEffect> spoilersPool;
        private LoadingDrawable translationLoadingDrawable;
        private LinkPath translationLoadingPath;
        public View view;
        public int x;
        public int y;

        public static class EmojiLineMetrics {
            private int contentCount;
            private int emojiCount;
            private int emojiSide;

            /* renamed from: -$$Nest$fgetcontentCount, reason: not valid java name */
            public static /* bridge */ /* synthetic */ int m4867$$Nest$fgetcontentCount(EmojiLineMetrics r0) {
                return r0.contentCount;
            }

            /* renamed from: -$$Nest$fgetemojiCount, reason: not valid java name */
            public static /* bridge */ /* synthetic */ int m4868$$Nest$fgetemojiCount(EmojiLineMetrics r0) {
                return r0.emojiCount;
            }

            /* renamed from: -$$Nest$fgetemojiSide, reason: not valid java name */
            public static /* bridge */ /* synthetic */ int m4869$$Nest$fgetemojiSide(EmojiLineMetrics r0) {
                return r0.emojiSide;
            }

            /* renamed from: -$$Nest$fputcontentCount, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m4870$$Nest$fputcontentCount(EmojiLineMetrics r0, int r1) {
                r0.contentCount = r1;
            }

            /* renamed from: -$$Nest$fputemojiCount, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m4871$$Nest$fputemojiCount(EmojiLineMetrics r0, int r1) {
                r0.emojiCount = r1;
            }

            /* renamed from: -$$Nest$fputemojiSide, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m4872$$Nest$fputemojiSide(EmojiLineMetrics r0, int r1) {
                r0.emojiSide = r1;
            }

            /* renamed from: -$$Nest$mallowsEmojiLineHeight, reason: not valid java name */
            public static /* bridge */ /* synthetic */ boolean m4873$$Nest$mallowsEmojiLineHeight(EmojiLineMetrics r0) {
                return r0.allowsEmojiLineHeight();
            }

            private EmojiLineMetrics() {
            }

            public /* synthetic */ EmojiLineMetrics(RichMessageLayoutIA r1) {
                this();
            }

            private boolean allowsEmojiLineHeight() {
                int r0 = this.contentCount;
                if (r0 > 0) goto L5;
                return false;
            L5:
                if ((this.emojiCount * 100) < (r0 * 70)) goto L10;
                return true;
            L10:
                return false;
            }
        }

        public static /* synthetic */ int $r8$lambda$8kZ87wDke8KEzxFP1RMnCjBCZ3k(Spanned r0, RichButtonSpan r1, RichButtonSpan r2) {
            return r0.getSpanStart(r2) - r0.getSpanStart(r1);
        }

        public static /* synthetic */ void $r8$lambda$FcVwBuows9AUlNSOa160M6fr7m4(Text r0, View r1, RichMessageLayout r2) {
            r0.lambda$revealSpoilers$4(r1, r2);
        }

        /* renamed from: $r8$lambda$LGcirVhm4y6D-CVcEjjKKm9lAL4, reason: not valid java name */
        public static /* synthetic */ void m4864$r8$lambda$LGcirVhm4y6DCVcEjjKKm9lAL4(Text r0, View r1, int r2, Canvas r3) {
            r0.lambda$drawFade$1(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$hYpFj49UpEOJaZV0x4lJi7iUKD4(Text r0, RichMessageLayout r1, View r2) {
            r0.lambda$revealSpoilers$3(r1, r2);
        }

        /* renamed from: $r8$lambda$pVv6wk6c-E2Yem6jBbNwtoGXKGc, reason: not valid java name */
        public static /* synthetic */ void m4865$r8$lambda$pVv6wk6cE2Yem6jBbNwtoGXKGc(Text r0) {
            r0.lambda$scheduleLongPress$2();
        }

        /* renamed from: -$$Nest$msetSoleButtonHitBounds, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4866$$Nest$msetSoleButtonHitBounds(Text r0, float r1, float r2, float r3, float r4) {
            r0.setSoleButtonHitBounds(r1, r2, r3, r4);
        }

        public Text(RichMessageLayout r2, CharSequence r3, int r4) {
            this(r2, r3, r4, Layout.Alignment.ALIGN_NORMAL);
        }

        public Text(RichMessageLayout r7, CharSequence r8, int r9, Layout.Alignment r10) {
            this(r7, r8, r9, r10, 1.0f);
        }

        public Text(RichMessageLayout r14, CharSequence r15, int r16, Layout.Alignment r17, float r18) {
            this.spoilers = new ArrayList();
            this.spoilersPool = new Stack();
            this.spoilersPatchedTextLayout = new AtomicReference();
            this.soleButtonHitBounds = new RectF();
            this.root = r14;
            Paint.FontMetricsInt r1 = r14.textPaint.getFontMetricsInt();
            if ((r15 instanceof Spanned) == false) goto L12;
            StyleSpan[] r2 = (StyleSpan[]) ((Spanned) r15).getSpans(0, r15.length(), StyleSpan.class);
            int r6 = r2.length;
            int r7 = 0;
        L5:
            if (r7 >= r6) goto L12;
            StyleSpan r8 = r2[r7];
            int r9 = r8.flags & 15;
            if (r9 < 1) goto L11;
            if (r9 > 6) goto L11;
            TextPaint r12 = new TextPaint(r14.textPaint);
            r8.applyStyle(r12);
            r1 = r12.getFontMetricsInt();
            boolean r22 = true;
        L13:
            if (r22 == false) goto L15;
            float r23 = 0.85f;
        L16:
            CharSequence r62 = configureEmojiLineHeights(Emoji.replaceEmoji(r15, r1, false, r23), r14.textPaint);
            int r0 = RichTextStyle.emojiOnlyCount(r62);
            this.emojiOnlyCount = r0;
            if (r0 != 0) goto L33;
            if ((r62 instanceof Spanned) == false) goto L33;
            Spanned r02 = (Spanned) r62;
            RichButtonSpan[] r13 = (RichButtonSpan[]) r02.getSpans(0, r62.length(), RichButtonSpan.class);
            if (r13.length <= 0) goto L33;
            Arrays.sort(r13, new RichMessageLayout$Text$$ExternalSyntheticLambda3(r02));
            SpannableStringBuilder r24 = new SpannableStringBuilder(r62);
            int r72 = r13.length;
            int r82 = 0;
        L23:
            if (r82 >= r72) goto L31;
            RichButtonSpan r92 = r13[r82];
            int r10 = r02.getSpanStart(r92);
            int r11 = r02.getSpanEnd(r92);
            if (r10 < 0) goto L33;
            if (r11 <= r10) goto L33;
            if (r92.getButton().text.getEmojiOnlyCount() == 0) goto L33;
            r24.replace(r10, r11, r92.getButton().text.layout.getText());
            r82 = r82 + 1;
            goto L23
        L31:
            this.emojiOnlyCount = RichTextStyle.emojiOnlyCount(r24);
        L33:
            if (this.emojiOnlyCount <= 0) goto L43;
            if ((r62 instanceof Spanned) == false) goto L43;
            StyleSpan[] r03 = (StyleSpan[]) ((Spanned) r62).getSpans(0, r62.length(), StyleSpan.class);
            int r19 = r03.length;
            int r25 = 0;
        L37:
            if (r25 >= r19) goto L43;
            StyleSpan r73 = r03[r25];
            if ((r73.flags & 15) != 14) goto L41;
            StyleSpan.m4863$$Nest$fputfullSizeTableEmoji(r73, true);
        L41:
            r25 = r25 + 1;
        L43:
            if ((r62 instanceof Spanned) == false) goto L50;
            StyleSpan[] r04 = (StyleSpan[]) ((Spanned) r62).getSpans(0, r62.length(), StyleSpan.class);
            int r110 = r04.length;
            int r26 = 0;
        L45:
            if (r26 >= r110) goto L50;
            if ((r04[r26].flags & 15) != 0) goto L50;
            r26 = r26 + 1;
        L50:
            this.layout = MessageObject.makeStaticLayout(r62, r14.textPaint, r16, r18, 0.0f, false, r17);
            this.left = r16;
            this.right = 0;
            int r142 = 0;
        L52:
            if (r142 >= this.layout.getLineCount()) goto L54;
            this.left = Math.min(this.left, (int) Math.floor(this.layout.getLineLeft(r142)));
            this.right = Math.max(this.right, (int) Math.ceil(this.layout.getLineRight(r142)));
            r142 = r142 + 1;
            goto L52
        L54:
            this.lastLineRight = 0;
            if (this.layout.getLineCount() <= 0) goto L57;
            StaticLayout r143 = this.layout;
            this.lastLineRight = (int) Math.ceil(r143.getLineRight(r143.getLineCount() - 1));
        L57:
            LinkPath r27 = null;
            SpoilerEffect.addSpoilers(null, this.layout, this.spoilersPool, this.spoilers);
            if ((this.layout.getText() instanceof Spanned) == false) goto L107;
            Spanned r144 = (Spanned) this.layout.getText();
            StyleSpan[] r05 = (StyleSpan[]) r144.getSpans(0, r144.length(), StyleSpan.class);
            int r111 = r05.length;
            int r3 = 0;
        L60:
            if (r3 >= r111) goto L85;
            StyleSpan r63 = r05[r3];
            if (TLObject.hasFlag(r63.flags, 8192) == false) goto L84;
            int r74 = r144.getSpanStart(r63);
            int r83 = r144.getSpanEnd(r63);
            if (r74 < 0) goto L84;
            if (r83 <= r74) goto L84;
            if (r27 != null) goto L70;
            r27 = new LinkPath(true);
            r27.setAllowReset(false);
        L70:
            r27.setCurrentLayout(this.layout, r74, 0.0f);
            if (TLObject.hasFlag(r63.flags, 4096) == false) goto L74;
            int r64 = -AndroidUtilities.dp(6.0f);
        L77:
            if (r64 == 0) goto L82;
            if (r64 <= 0) goto L80;
            float r93 = 5.0f;
        L81:
            int r65 = r64 + AndroidUtilities.dp(r93);
        L83:
            r27.setBaselineShift(r65);
            this.layout.getSelectionPath(r74, r83, r27);
            goto L84
        L80:
            r93 = -2.0f;
            goto L81
        L82:
            r65 = 0;
            goto L83
        L74:
            if (TLObject.hasFlag(r63.flags, 2048) == false) goto L76;
            r64 = AndroidUtilities.dp(2.0f);
            goto L77
        L76:
            r64 = 0;
        L84:
            r3 = r3 + 1;
            goto L60
        L85:
            if (r27 == null) goto L108;
            r27.setAllowReset(true);
            this.markPath = r27;
            return;
        L108:
            return;
        L107:
            return;
        L15:
            r23 = 1.0f;
        L11:
            r7 = r7 + 1;
        L12:
            r22 = false;
            goto L13
        }

        private static void allowEmojiLineHeight(Spanned r6, int r7, int r8, int r9) {
            AnimatedEmojiSpan[] r0 = (AnimatedEmojiSpan[]) r6.getSpans(r7, r8, AnimatedEmojiSpan.class);
            int r1 = r0.length;
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L9;
            AnimatedEmojiSpan r4 = r0[r3];
            int r5 = r6.getSpanStart(r4);
            if (r5 < r7) goto L8;
            if (r5 >= r8) goto L8;
            r4.setMinimumLineHeight(r9);
        L8:
            r3 = r3 + 1;
            goto L3
        L9:
            Emoji.EmojiSpan[] r02 = (Emoji.EmojiSpan[]) r6.getSpans(r7, r8, Emoji.EmojiSpan.class);
            int r12 = r02.length;
            int r32 = 0;
        L10:
            if (r32 >= r12) goto L16;
            Emoji.EmojiSpan r42 = r02[r32];
            int r52 = r6.getSpanStart(r42);
            if (r52 < r7) goto L15;
            if (r52 >= r8) goto L15;
            r42.setMinimumLineHeight(r9);
        L15:
            r32 = r32 + 1;
            goto L10
        L16:
            RichButtonSpan[] r03 = (RichButtonSpan[]) r6.getSpans(r7, r8, RichButtonSpan.class);
            int r13 = r03.length;
        L17:
            if (r2 >= r13) goto L24;
            RichButtonSpan r33 = r03[r2];
            int r43 = r6.getSpanStart(r33);
            if (RichButtonSpan.m4836$$Nest$fgetbutton(r33).text.getEmojiOnlyCount() <= 0) goto L23;
            if (r43 < r7) goto L23;
            if (r43 >= r8) goto L23;
            RichButtonSpan.m4837$$Nest$fputminimumLineHeight(r33, r9);
        L23:
            r2 = r2 + 1;
            goto L17
        }

        private boolean buttonContains(RichButtonSpan r2, float r3, float r4) {
            if (r2.contains(r3, r4, AndroidUtilities.dp(8.0f)) == false) goto L5;
            return true;
        L5:
            if (r2 == getSoleButtonSpan()) goto L7;
            return false;
        L7:
            if (this.soleButtonHitBounds.contains(r3, r4) == true) goto L14;
            return false;
        L14:
            return true;
        }

        private void cancelLongPress() {
            Runnable r0 = this.longPressRunnable;
            if (r0 == null) goto L6;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.longPressRunnable = null;
            return;
        }

        private static CharSequence configureEmojiLineHeights(CharSequence r7, TextPaint r8) {
            if ((r7 instanceof Spanned) == true) goto L5;
            return r7;
        L5:
            SpannableStringBuilder r0 = new SpannableStringBuilder(r7);
            AnimatedEmojiSpan[] r72 = (AnimatedEmojiSpan[]) r0.getSpans(0, r0.length(), AnimatedEmojiSpan.class);
            int r1 = r72.length;
            int r3 = 0;
        L7:
            if (r3 >= r1) goto L9;
            r72[r3].setPreserveFontMetrics(true);
            r3 = r3 + 1;
            goto L7
        L9:
            Emoji.EmojiSpan[] r73 = (Emoji.EmojiSpan[]) r0.getSpans(0, r0.length(), Emoji.EmojiSpan.class);
            int r12 = r73.length;
            int r32 = 0;
        L10:
            if (r32 >= r12) goto L12;
            r73[r32].setPreserveFontMetrics(true);
            r32 = r32 + 1;
            goto L10
        L12:
            RichButtonSpan[] r74 = (RichButtonSpan[]) r0.getSpans(0, r0.length(), RichButtonSpan.class);
            int r13 = r74.length;
            int r33 = 0;
        L13:
            if (r33 >= r13) goto L18;
            RichButtonSpan r5 = r74[r33];
            if (r5.getButton().text.getEmojiOnlyCount() <= 0) goto L17;
            RichButtonSpan.m4838$$Nest$fputpreserveFontMetrics(r5, true);
        L17:
            r33 = r33 + 1;
            goto L13
        L18:
            int r75 = 0;
        L20:
            if (r75 >= r0.length()) goto L35;
            int r14 = TextUtils.indexOf(r0, '\n', r75);
            if (r14 < 0) goto L24;
            boolean r34 = true;
        L25:
            if (r34 == true) goto L27;
            r14 = r0.length();
        L27:
            EmojiLineMetrics r52 = measureEmojiLine(r0, r75, r14, r8);
            if (EmojiLineMetrics.m4873$$Nest$mallowsEmojiLineHeight(r52) == true) goto L30;
        L32:
            if (r34 == false) goto L35;
            r75 = r14 + 1;
            goto L20
        L30:
            if (EmojiLineMetrics.m4869$$Nest$fgetemojiSide(r52) <= 0) goto L32;
            allowEmojiLineHeight(r0, r75, r14, EmojiLineMetrics.m4869$$Nest$fgetemojiSide(r52));
            goto L32
        L24:
            r34 = false;
        L35:
            return r0;
        }

        private void dispatchLinkClick(CharacterStyle r7, boolean r8) {
            if (r7 != null) goto L5;
            return;
        L5:
            if (r8 == false) goto L7;
        L18:
            String r2 = null;
        L19:
            if (r2 == null) goto L30;
            if (r2.startsWith("#") == true) goto L30;
            if (this.root.onLinkPress == null) goto L30;
            View r82 = this.view;
            if (r82 == null) goto L27;
            r82.playSoundEffect(0);
        L27:
            this.root.onLinkPress.run((URLSpan) r7);
            return;
        L30:
            if ((r7 instanceof StyleSpan) == true) goto L32;
        L34:
            CharacterStyle r0 = r7;
        L35:
            ChatMessageCell.ChatMessageCellDelegate r22 = this.root.getDelegate();
            ChatMessageCell r3 = this.root.getCell();
            if (r22 == null) goto L44;
            if (r3 == null) goto L44;
            View r6 = this.view;
            if (r6 == null) goto L42;
            if (r8 == true) goto L42;
            r6.playSoundEffect(0);
        L42:
            r22.didPressUrl(r3, r0, r8);
            return;
        L44:
            if (r8 == true) goto L52;
            View r83 = this.view;
            if (r83 != null) goto L48;
            return;
        L48:
            if ((r7 instanceof ClickableSpan) == false) goto L54;
            r83.playSoundEffect(0);
            ((ClickableSpan) r7).onClick(this.view);
            return;
        L54:
            return;
        L52:
            return;
        L32:
            if (TLObject.hasFlag(((StyleSpan) r7).flags, 256) == false) goto L34;
            r0 = new URLSpanMono(this.layout.getText(), this.pressedLinkStart, this.pressedLinkEnd, this.root.isOut() ? 1 : 0);
            goto L35
        L7:
            if ((r7 instanceof URLSpan) == false) goto L18;
            r2 = ((URLSpan) r7).getURL();
            if (r2 == null) goto L19;
            if (r2.startsWith("#") == false) goto L19;
            if (RichMessageLayout.m4826$$Nest$mhandleAnchorClick(this.root, r2) == false) goto L19;
            View r62 = this.view;
            if (r62 == null) goto L51;
            r62.playSoundEffect(0);
            return;
        }

        private void drawTranslationLoading(Canvas r8) {
            RichMessageLayout r0 = this.root;
            float r1 = r0.translationLoadingValue;
            if (r1 > 0.0f) goto L5;
            return;
        L5:
            boolean r02 = r0.isTranslating();
            if (this.translationLoadingDrawable != null) goto L8;
            LoadingDrawable r3 = new LoadingDrawable();
            this.translationLoadingDrawable = r3;
            r3.setAppearByGradient(true);
            LinkPath r32 = new LinkPath(true);
            this.translationLoadingPath = r32;
            r32.setUseCornerPathImplementation(true);
            this.translationLoadingDrawable.usePath(this.translationLoadingPath);
            this.translationLoadingDrawable.setRadiiDp(5.0f);
            this.translationLoadingDrawable.reset();
            this.translationLoadingPath.reset();
            this.translationLoadingPath.setCurrentLayout(this.layout, 0, 0.0f);
            this.translationLoadingPath.setAllowReset(false);
            StaticLayout r2 = this.layout;
            r2.getSelectionPath(0, r2.getText().length(), this.translationLoadingPath);
            this.translationLoadingPath.setAllowReset(true);
            this.translationLoadingPath.closeRects();
            this.translationLoadingDrawable.updateBounds();
        L8:
            if (r02 == true) goto L10;
        L14:
            if (r02 == false) goto L16;
        L20:
            RichMessageLayout r03 = this.root;
            if (r03.isOut() == false) goto L23;
            int r22 = Theme.key_chat_messageLinkOut;
        L24:
            int r04 = RichMessageLayout.m4824$$Nest$mgetThemedColor(r03, r22);
            this.translationLoadingDrawable.setColors(Theme.multAlpha(r04, 0.05f), Theme.multAlpha(r04, 0.15f), Theme.multAlpha(r04, 0.1f), Theme.multAlpha(r04, 0.3f));
            this.translationLoadingDrawable.setAlpha((int) (r1 * 255.0f));
            this.translationLoadingDrawable.draw(r8);
            return;
        L23:
            r22 = Theme.key_chat_messageLinkIn;
            goto L24
        L16:
            if (this.translationLoadingDrawable.isDisappearing() == true) goto L20;
            if (this.translationLoadingDrawable.isDisappeared() == true) goto L20;
            this.translationLoadingDrawable.disappear();
            goto L20
        L10:
            if (this.translationLoadingDrawable.isDisappearing() == false) goto L12;
        L13:
            this.translationLoadingDrawable.reset();
            this.translationLoadingDrawable.resetDisappear();
            goto L20
        L12:
            if (this.translationLoadingDrawable.isDisappeared() == false) goto L14;
            goto L13
        }

        private RichButtonSpan[] getButtonSpans() {
            if ((this.layout.getText() instanceof Spanned) == true) goto L6;
            return null;
        L6:
            Spanned r3 = (Spanned) this.layout.getText();
            return (RichButtonSpan[]) r3.getSpans(0, r3.length(), RichButtonSpan.class);
        }

        private RichButtonSpan getSoleButtonSpan() {
            if ((this.layout.getText() instanceof Spanned) == true) goto L5;
            return null;
        L5:
            Spanned r7 = (Spanned) this.layout.getText();
            RichButtonSpan[] r0 = (RichButtonSpan[]) r7.getSpans(0, r7.length(), RichButtonSpan.class);
            if (r0.length == 1) goto L8;
            return null;
        L8:
            int r2 = r7.getSpanStart(r0[0]);
            int r4 = r7.getSpanEnd(r0[0]);
            int r5 = 0;
        L10:
            if (r5 >= r7.length()) goto L18;
            if (r5 < r2) goto L14;
            if (r5 >= r4) goto L14;
        L16:
            r5 = r5 + 1;
        L14:
            if (Character.isWhitespace(r7.charAt(r5)) == true) goto L16;
            return null;
        L18:
            return r0[0];
        }

        private /* synthetic */ void lambda$drawFade$1(View r21, int r22, Canvas r23) {
            SpoilerEffect.renderWithRipple(r21, false, r22, 0, this.spoilersPatchedTextLayout, 0, this.layout, this.spoilers, r23, false);
            SquigglyLinesSpan.drawOnText(r23, this.layout);
            AnimatedEmojiSpan.drawAnimatedEmojis(r23, this.layout, this.animatedEmojiStack, 0.0f, this.spoilers, 0.0f, 0.0f, 0.0f, 1.0f);
        }

        private /* synthetic */ void lambda$revealSpoilers$3(RichMessageLayout r4, View r5) {
            if (r4 == null) goto L11;
            MessageObject r3 = r4.messageObject;
            if (r3 == null) goto L6;
            r3.isSpoilersRevealed = true;
        L6:
            ArrayList<TextSelectionHelper.TextLayoutBlock> r32 = r4.textBlocks;
            int r42 = r32.size();
            int r0 = 0;
        L7:
            if (r0 >= r42) goto L12;
            TextSelectionHelper.TextLayoutBlock r1 = r32.get(r0);
            r0 = r0 + 1;
            TextSelectionHelper.TextLayoutBlock r12 = r1;
            if ((r12 instanceof Text) == false) goto L7;
            ((Text) r12).spoilers.clear();
        L12:
            r5.invalidate();
            return;
        L11:
            this.spoilers.clear();
            goto L12
        }

        private /* synthetic */ void lambda$revealSpoilers$4(View r2, RichMessageLayout r3) {
            if (r2 != null) goto L4;
            return;
        L4:
            r2.post(new RichMessageLayout$Text$$ExternalSyntheticLambda4(this, r3, r2));
        }

        private /* synthetic */ void lambda$scheduleLongPress$2() {
            this.longPressRunnable = null;
            if (this.pressedButtonSpan == null) goto L10;
            this.longPressFired = true;
            View r0 = this.view;
            if (r0 != null) goto L24;
        L7:
            this.pressedButtonSpan.didPress(this.root.getCell(), this.root.getDelegate(), true);
            return;
        L24:
            r0.performHapticFeedback(0);     // Catch: Exception -> L20
            goto L7
        L10:
            if (this.pressedLink != null) goto L12;
            return;
        L12:
            this.longPressFired = true;
            View r1 = this.view;
            if (r1 != null) goto L22;
        L15:
            dispatchLinkClick(this.pressedLink, true);
            LinkSpanDrawable.LinkCollector r12 = this.linkCollector;
            if (r12 == null) goto L18;
            r12.clear();
        L18:
            this.pressedLinkDrawable = null;
            return;
        L22:
            r1.performHapticFeedback(0);     // Catch: Exception -> L21
            goto L15
        }

        private static EmojiLineMetrics measureEmojiLine(Spanned r17, int r18, int r19, TextPaint r20) {
            EmojiLineMetrics r8 = new EmojiLineMetrics(null);
            int r10 = r19 - r18;
            if (r10 <= 0) goto L64;
            AnimatedEmojiSpan[] r11 = (AnimatedEmojiSpan[]) r17.getSpans(r18, r19, AnimatedEmojiSpan.class);
            Emoji.EmojiSpan[] r12 = (Emoji.EmojiSpan[]) r17.getSpans(r18, r19, Emoji.EmojiSpan.class);
            if (r11.length == 0) goto L8;
        L10:
            int[] r13 = new int[r10];
        L11:
            int r14 = r11.length;
            int r0 = 0;
        L12:
            if (r0 >= r14) goto L20;
            int r3 = r0;
            AnimatedEmojiSpan r02 = r11[r3];
            int r32 = r17.getSpanStart(r02);
            int r5 = Math.min(r19, r17.getSpanEnd(r02));
            if (r32 < r18) goto L18;
            if (r32 >= r19) goto L18;
            if (r5 <= r32) goto L18;
            int r16 = r32 - r18;
            r13[r16] = Math.max(r13[r16], r5);
            int r162 = r3;
            EmojiLineMetrics.m4872$$Nest$fputemojiSide(r8, Math.max(EmojiLineMetrics.m4869$$Nest$fgetemojiSide(r8), r02.getSize(r20, r17, r32, r5, null) + 1));
        L19:
            r0 = r162 + 1;
        L18:
            r162 = r3;
            goto L19
        L20:
            int r9 = r12.length;
            int r112 = 0;
        L21:
            if (r112 >= r9) goto L28;
            Emoji.EmojiSpan r03 = r12[r112];
            int r33 = r17.getSpanStart(r03);
            int r4 = Math.min(r19, r17.getSpanEnd(r03));
            if (r33 < r18) goto L27;
            if (r33 >= r19) goto L27;
            if (r4 <= r33) goto L27;
            int r1 = r33 - r18;
            r13[r1] = Math.max(r13[r1], r4);
            EmojiLineMetrics.m4872$$Nest$fputemojiSide(r8, Math.max(EmojiLineMetrics.m4869$$Nest$fgetemojiSide(r8), r03.getSize(r20, r17, r33, r4, null)));
        L27:
            r112 = r112 + 1;
            goto L21
        L28:
            RichButtonSpan[] r04 = (RichButtonSpan[]) r17.getSpans(r18, r19, RichButtonSpan.class);
            if (r04.length <= 0) goto L40;
            int r15 = r04.length;
            int r34 = 0;
            RichButtonSpan[] r42 = null;
        L31:
            if (r34 >= r15) goto L41;
            RichButtonSpan r52 = r04[r34];
            int r92 = r17.getSpanStart(r52);
            if (RichButtonSpan.m4836$$Nest$fgetbutton(r52).text.getEmojiOnlyCount() <= 0) goto L39;
            if (r92 < r18) goto L39;
            if (r92 >= r19) goto L39;
            if (r42 != null) goto L38;
            r42 = new RichButtonSpan[r10];
        L38:
            r42[r92 - r18] = r52;
        L39:
            r34 = r34 + 1;
        L41:
            if (r13 != null) goto L44;
            if (r42 == null) goto L64;
        L44:
            int r05 = r18;
        L45:
            if (r05 >= r19) goto L64;
            int r110 = r05 - r18;
            if (r42 != null) goto L49;
            RichButtonSpan r35 = null;
        L50:
            if (r35 == null) goto L55;
            if ((RichButtonSpan.m4836$$Nest$fgetbutton(r35).text.layout.getText() instanceof Spanned) == false) goto L55;
            Spanned r06 = (Spanned) RichButtonSpan.m4836$$Nest$fgetbutton(r35).text.layout.getText();
            EmojiLineMetrics r07 = measureEmojiLine(r06, 0, r06.length(), RichButtonSpan.m4836$$Nest$fgetbutton(r35).text.layout.getPaint());
            EmojiLineMetrics.m4871$$Nest$fputemojiCount(r8, EmojiLineMetrics.m4868$$Nest$fgetemojiCount(r8) + EmojiLineMetrics.m4868$$Nest$fgetemojiCount(r07));
            EmojiLineMetrics.m4870$$Nest$fputcontentCount(r8, EmojiLineMetrics.m4867$$Nest$fgetcontentCount(r8) + Math.max(1, EmojiLineMetrics.m4867$$Nest$fgetcontentCount(r07)));
            EmojiLineMetrics.m4872$$Nest$fputemojiSide(r8, Math.max(EmojiLineMetrics.m4869$$Nest$fgetemojiSide(r8), EmojiLineMetrics.m4869$$Nest$fgetemojiSide(r07)));
            r05 = Math.min(r19, r17.getSpanEnd(r35));
        L55:
            if (r13 != null) goto L57;
            int r111 = 0;
        L58:
            if (r111 > r05) goto L59;
            int r113 = Character.codePointAt(r17, r05);
            if (Character.isWhitespace(r113) == true) goto L63;
            EmojiLineMetrics.m4870$$Nest$fputcontentCount(r8, EmojiLineMetrics.m4867$$Nest$fgetcontentCount(r8) + 1);
        L63:
            r05 = r05 + Character.charCount(r113);
            goto L45
        L59:
            EmojiLineMetrics.m4871$$Nest$fputemojiCount(r8, EmojiLineMetrics.m4868$$Nest$fgetemojiCount(r8) + 1);
            EmojiLineMetrics.m4870$$Nest$fputcontentCount(r8, EmojiLineMetrics.m4867$$Nest$fgetcontentCount(r8) + 1);
            r05 = r111;
            goto L45
        L57:
            r111 = r13[r110];
            goto L58
        L49:
            r35 = r42[r110];
            goto L50
        L40:
            r42 = null;
            goto L41
        L8:
            if (r12.length != 0) goto L10;
            r13 = null;
        L64:
            return r8;
        }

        private void revealSpoilers(int r6, int r7) {
            if (this.pressedSpoiler == null) goto L14;
            float r0 = this.layout.getWidth();
            float r1 = this.layout.getHeight();
            float r02 = (float) Math.sqrt((r0 * r0) + (r1 * r1));
            View r12 = this.view;
            this.pressedSpoiler.setOnRippleEndCallback(new RichMessageLayout$Text$$ExternalSyntheticLambda2(this, r12, this.root));
            Iterator<SpoilerEffect> r5 = this.spoilers.iterator();
        L7:
            if (r5.hasNext() == false) goto L9;
            r5.next().startRipple(r6, r7, r02);
            goto L7
        L9:
            if (r12 == null) goto L13;
            r12.playSoundEffect(0);
            return;
        L13:
            return;
        }

        private void scheduleLongPress() {
            cancelLongPress();
            RichMessageLayout$Text$$ExternalSyntheticLambda0 r0 = new RichMessageLayout$Text$$ExternalSyntheticLambda0(this);
            this.longPressRunnable = r0;
            AndroidUtilities.runOnUIThread(r0, ViewConfiguration.getLongPressTimeout());
        }

        private void setSoleButtonHitBounds(float r1, float r2, float r3, float r4) {
            this.soleButtonHitBounds.set(r1, r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.TableLayout.CellText
        public void attach(View r2) {
            View r0 = this.view;
            if (r2 != r0) goto L5;
            return;
        L5:
            if (r0 == null) goto L7;
            detach(r0);
        L7:
            this.view = r2;
            onAttachedToWindow();
        }

        @Override // org.telegram.ui.Components.TableLayout.CellText
        public void detach(View r2) {
            View r0 = this.view;
            if (r0 != r2) goto L9;
            if (r0 != null) goto L7;
            return;
        L7:
            this.view = null;
            onDetachedFromWindow();
            return;
        }

        public void draw(Canvas r2) {
            draw(r2, this.view);
        }

        @Override // org.telegram.ui.Components.TableLayout.CellText
        public void draw(Canvas r11, View r12) {
            r11.save();
            r11.translate(-drawLeft(), 0.0f);
            RichMessageLayout r1 = this.root;
            if (r1.isOut() == false) goto L5;
            int r2 = Theme.key_chat_messageTextOut;
        L6:
            int r22 = RichMessageLayout.m4824$$Nest$mgetThemedColor(r1, r2);
            this.root.textPaint.setColor(r22);
            RichMessageLayout r13 = this.root;
            TextPaint r3 = r13.textPaint;
            if (r13.isOut() == false) goto L9;
            int r4 = Theme.key_chat_messageLinkOut;
        L10:
            r3.linkColor = RichMessageLayout.m4824$$Nest$mgetThemedColor(r13, r4);
            if (this.markPath != null) goto L13;
        L16:
            if (r12 == null) goto L18;
            View r14 = r12;
        L19:
            LinkSpanDrawable.LinkCollector r32 = this.linkCollector;
            if (r32 != null) goto L22;
        L25:
            SpoilerEffect.renderWithRipple(r14, false, r22, 0, this.spoilersPatchedTextLayout, 0, this.layout, this.spoilers, r11, false);
            SquigglyLinesSpan.drawOnText(r11, this.layout);
            if (this.root.isOverlayActive() == true) goto L28;
            AnimatedEmojiSpan.drawAnimatedEmojis(r11, this.layout, this.animatedEmojiStack, 0.0f, this.spoilers, 0.0f, 0.0f, 0.0f, 1.0f);
        L28:
            drawTranslationLoading(r11);
            r11.restore();
            return;
        L22:
            if (r32.draw(r11) == false) goto L25;
            if (r14 == null) goto L25;
            r14.invalidate();
            goto L25
        L18:
            r14 = this.view;
            goto L19
        L13:
            if (markPaint != null) goto L15;
            Paint r15 = new Paint(1);
            markPaint = r15;
            r15.setPathEffect(LinkPath.getRoundedEffect());
        L15:
            markPaint.setColor((this.root.quoteLine.getColor() & 16777215) | 855638016);
            r11.drawPath(this.markPath, markPaint);
            goto L16
        L9:
            r4 = Theme.key_chat_messageLinkIn;
            goto L10
        L5:
            r2 = Theme.key_chat_messageTextIn;
            goto L6
        }

        public void drawFade(Canvas r5, int r6, float r7) {
            r5.save();
            r5.translate(-drawLeft(), 0.0f);
            RichMessageLayout r0 = this.root;
            if (r0.isOut() == false) goto L5;
            int r1 = Theme.key_chat_messageTextOut;
        L6:
            int r02 = RichMessageLayout.m4824$$Nest$mgetThemedColor(r0, r1);
            this.root.textPaint.setColor(r02);
            RichMessageLayout r12 = this.root;
            TextPaint r2 = r12.textPaint;
            if (r12.isOut() == false) goto L9;
            int r3 = Theme.key_chat_messageLinkOut;
        L10:
            r2.linkColor = RichMessageLayout.m4824$$Nest$mgetThemedColor(r12, r3);
            MultiLayoutTypingAnimator.drawLayoutWithLastLineFade(r5, this.layout, r6, r7, new RichMessageLayout$Text$$ExternalSyntheticLambda1(this, this.view, r02));
            r5.restore();
            return;
        L9:
            r3 = Theme.key_chat_messageLinkIn;
            goto L10
        L5:
            r1 = Theme.key_chat_messageTextIn;
            goto L6
        }

        public int drawLeft() {
            if (this.drawAtOrigin == false) goto L7;
            return 0;
        L7:
            return this.left;
        }

        public boolean fillFoundLink(CharacterStyle r4, FoundLink r5) {
            if ((this.layout.getText() instanceof Spanned) == true) goto L5;
            return false;
        L5:
            Spanned r0 = (Spanned) this.layout.getText();
            int r2 = r0.getSpanStart(r4);
            int r42 = r0.getSpanEnd(r4);
            if (r2 < 0) goto L11;
            if (r42 <= r2) goto L11;
            StaticLayout r3 = this.layout;
            r5.layout = r3;
            r5.start = r2;
            r5.end = r42;
            r5.originalWidth = r3.getWidth();
            return true;
        L11:
            return false;
        }

        public int getBaseline() {
            StaticLayout r0 = this.layout;
            if (r0 != null) goto L5;
            return -1;
        L5:
            if (r0.getLineCount() > 0) goto L7;
            return -1;
        L7:
            return this.layout.getLineBaseline(0);
        }

        @Override // org.telegram.ui.Components.TableLayout.CellText
        public int getEmojiOnlyCount() {
            return this.emojiOnlyCount;
        }

        public int getHeight() {
            return this.layout.getHeight();
        }

        public int getLastLineWidth() {
            return Math.max(0, this.lastLineRight - this.left);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public Layout getLayout() {
            return this.layout;
        }

        public int getMinWidth() {
            return Math.max(0, this.right - this.left);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public /* bridge */ /* synthetic */ CharSequence getPrefix() {
            return super.getPrefix();
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getRow() {
            return this.row;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public /* bridge */ /* synthetic */ Rect getSelectionBounds() {
            return super.getSelectionBounds();
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public /* bridge */ /* synthetic */ CharSequence getText() {
            return super.getText();
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getX() {
            return this.blockX + this.x;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getY() {
            return this.blockY + this.y;
        }

        public boolean isAttached() {
            if (this.view == null) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isPressingLink() {
            if (this.pressedLink == null) goto L5;
            return true;
        L5:
            if (this.pressedButtonSpan != null) goto L11;
            return false;
        L11:
            return true;
        }

        public Text offset(int r2, int r3) {
            this.x += r2;
            this.y += r3;
            return this;
        }

        public void onAttachedToWindow() {
            View r0 = this.view;
            int r3 = 0;
            if (this.root.invalidateAnimatedEmojiInParent == true) goto L5;
        L7:
            boolean r1 = false;
        L8:
            this.animatedEmojiStack = AnimatedEmojiSpan.update(0, r0, r1, this.animatedEmojiStack, new Layout[]{this.layout});
            LinkSpanDrawable.LinkCollector r02 = this.linkCollector;
            if (r02 == null) goto L11;
            r02.setParent(this.view);
        L11:
            RichButtonSpan[] r03 = getButtonSpans();
            if (r03 == null) goto L16;
            int r12 = r03.length;
        L14:
            if (r3 >= r12) goto L18;
            r03[r3].attach(this.view);
            r3 = r3 + 1;
            goto L14
        L18:
            return;
        L16:
            return;
        L5:
            if (this.doNotInvalidateEmojiInParent == true) goto L7;
            r1 = true;
            goto L8
        }

        public void onDetachedFromWindow() {
            AnimatedEmojiSpan.release(this.view, this.animatedEmojiStack);
            this.animatedEmojiStack = null;
            LinkSpanDrawable.LinkCollector r1 = this.linkCollector;
            if (r1 == null) goto L5;
            r1.setParent(null);
        L5:
            RichButtonSpan[] r0 = getButtonSpans();
            if (r0 == null) goto L10;
            int r12 = r0.length;
            int r2 = 0;
        L8:
            if (r2 >= r12) goto L12;
            r0[r2].detach(this.view);
            r2 = r2 + 1;
            goto L8
        L12:
            return;
        }

        public boolean onTouchEvent(MotionEvent r17) {
            int r1 = r17.getActionMasked();
            int r2 = ((int) r17.getX()) + drawLeft();
            int r3 = (int) r17.getY();
            boolean r4 = true;
            if (r1 != 0) goto L103;
            this.pressedSpoiler = null;
            this.pressedLink = null;
            this.pressedEmoji = null;
            this.pressedButtonSpan = null;
            Iterator<SpoilerEffect> r12 = this.spoilers.iterator();
        L6:
            if (r12.hasNext() == false) goto L11;
            SpoilerEffect r7 = r12.next();
            if (r7.getBounds().contains(r2, r3) == false) goto L6;
            this.pressedSpoiler = r7;
            return true;
        L11:
            RichButtonSpan[] r13 = getButtonSpans();
            if (r13 == null) goto L27;
            int r72 = r13.length;
            int r8 = 0;
        L14:
            if (r8 >= r72) goto L27;
            RichButtonSpan r9 = r13[r8];
            if (buttonContains(r9, r2, r3) == true) goto L18;
            r8 = r8 + 1;
            goto L14
        L18:
            if (r9.isDisabled() == false) goto L20;
            return true;
        L20:
            this.pressedButtonSpan = r9;
            this.longPressFired = false;
            r9.setPressed(true);
            View r14 = this.view;
            if (r14 == null) goto L23;
            r14.invalidate();
        L23:
            scheduleLongPress();
            return true;
        L27:
            if ((this.layout.getText() instanceof Spannable) == false) goto L101;
            if (r3 < 0) goto L101;
            if (r3 >= this.layout.getHeight()) goto L101;
            int r15 = this.layout.getLineForVertical(r3);
            float r73 = this.layout.getLineLeft(r15);
            float r82 = this.layout.getLineWidth(r15) + r73;
            float r122 = r2;
            if (r122 < r73) goto L101;
            if (r122 > r82) goto L101;
            int r16 = this.layout.getOffsetForHorizontal(r15, r122);
            Spannable r22 = (Spannable) this.layout.getText();
            ClickableSpan[] r74 = (ClickableSpan[]) r22.getSpans(r16, r16, ClickableSpan.class);
            if (r74 != null) goto L38;
        L47:
            StyleSpan[] r92 = (StyleSpan[]) r22.getSpans(r16, r16, StyleSpan.class);
            if (r92 == null) goto L55;
            int r11 = r92.length;
            int r132 = 0;
        L50:
            if (r132 >= r11) goto L55;
            StyleSpan r142 = r92[r132];
            if (TLObject.hasFlag(r142.flags, 256) == true) goto L56;
            r132 = r132 + 1;
        L56:
            if (r142 == null) goto L95;
            int r18 = r22.getSpanStart(r142);
            int r93 = r22.getSpanEnd(r142);
        L58:
            if (r18 <= 0) goto L73;
            int r112 = r18 - 1;
            StyleSpan[] r113 = (StyleSpan[]) r22.getSpans(r112, r112, StyleSpan.class);
            int r133 = r113.length;
            int r152 = 0;
        L60:
            boolean r172 = r4;
            if (r152 >= r133) goto L66;
            StyleSpan r42 = r113[r152];
            if (TLObject.hasFlag(r42.flags, 256) == true) goto L67;
            r152 = r152 + 1;
            r4 = r172;
        L67:
            if (r42 == null) goto L74;
            int r43 = r22.getSpanStart(r42);
            if (r43 >= r18) goto L74;
            r18 = r43;
            r4 = r172;
        L74:
            int r44 = r93;
            if (r44 >= r22.length()) goto L87;
            StyleSpan[] r5 = (StyleSpan[]) r22.getSpans(r44, r44, StyleSpan.class);
            int r94 = r5.length;
            int r114 = 0;
        L77:
            if (r114 >= r94) goto L82;
            StyleSpan r134 = r5[r114];
            if (TLObject.hasFlag(r134.flags, 256) == true) goto L83;
            r114 = r114 + 1;
        L83:
            if (r134 == null) goto L87;
            r93 = r22.getSpanEnd(r134);
            if (r93 > r44) goto L74;
        L82:
            r134 = null;
        L87:
            this.pressedLink = r142;
            this.pressedLinkStart = r18;
            this.pressedLinkEnd = r44;
            this.longPressFired = false;
            LinkSpanDrawable<CharacterStyle> r95 = new LinkSpanDrawable(r142, this.root.resourcesProvider, r122, r3, true);
            LinkPath r23 = r95.obtainNewPath();
            r23.setCurrentLayout(this.layout, r18, 0.0f);
            this.layout.getSelectionPath(r18, r44, r23);
            this.pressedLinkDrawable = r95;
            if (this.linkCollector != null) goto L90;
            this.linkCollector = new LinkSpanDrawable.LinkCollector(this.view);
        L90:
            this.linkCollector.addLink(r95);
            View r19 = this.view;
            if (r19 == null) goto L93;
            r19.invalidate();
        L93:
            scheduleLongPress();
            return r172;
        L66:
            r42 = null;
            goto L67
        L73:
            r172 = r4;
            goto L74
        L95:
            AnimatedEmojiSpan[] r110 = (AnimatedEmojiSpan[]) r22.getSpans(r16, r16, AnimatedEmojiSpan.class);
            if (r110 == null) goto L101;
            if (r110.length <= 0) goto L101;
            this.pressedEmoji = r110[0];
            return true;
        L55:
            r142 = null;
            goto L56
        L38:
            if (r74.length <= 0) goto L47;
            ClickableSpan r111 = r74[0];
            this.pressedLink = r111;
            this.pressedLinkStart = r22.getSpanStart(r111);
            this.pressedLinkEnd = r22.getSpanEnd(this.pressedLink);
            this.longPressFired = false;
            LinkSpanDrawable<CharacterStyle> r96 = new LinkSpanDrawable(this.pressedLink, this.root.resourcesProvider, r122, r3, false);
            LinkPath r115 = r96.obtainNewPath();
            r115.setCurrentLayout(this.layout, this.pressedLinkStart, 0.0f);
            this.layout.getSelectionPath(this.pressedLinkStart, this.pressedLinkEnd, r115);
            this.pressedLinkDrawable = r96;
            if (this.linkCollector != null) goto L42;
            this.linkCollector = new LinkSpanDrawable.LinkCollector(this.view);
        L42:
            this.linkCollector.addLink(r96);
            View r116 = this.view;
            if (r116 == null) goto L45;
            r116.invalidate();
        L45:
            scheduleLongPress();
            return true;
        L101:
            return false;
        L103:
            if (r1 != 2) goto L112;
            RichButtonSpan r117 = this.pressedButtonSpan;
            if (r117 != null) goto L107;
            return false;
        L107:
            if (buttonContains(r117, r2, r3) == true) goto L109;
            cancelLongPress();
            this.pressedButtonSpan.setPressed(false);
            this.pressedButtonSpan = null;
            this.longPressFired = false;
        L109:
            return true;
        L112:
            if (r1 != 1) goto L150;
            if (this.pressedButtonSpan == null) goto L124;
            cancelLongPress();
            RichButtonSpan r118 = this.pressedButtonSpan;
            this.pressedButtonSpan = null;
            r118.setPressed(false);
            if (this.longPressFired == true) goto L121;
            View r24 = this.view;
            if (r24 == null) goto L120;
            r24.playSoundEffect(0);
        L120:
            r118.didPress(this.root.getCell(), this.root.getDelegate(), false);
        L121:
            this.longPressFired = false;
            return true;
        L124:
            if (this.pressedSpoiler == null) goto L128;
            revealSpoilers(r2, r3);
            this.pressedSpoiler = null;
            return true;
        L128:
            if (this.pressedLink == null) goto L137;
            cancelLongPress();
            if (this.longPressFired == true) goto L132;
            dispatchLinkClick(this.pressedLink, false);
        L132:
            LinkSpanDrawable.LinkCollector r119 = this.linkCollector;
            if (r119 == null) goto L135;
            r119.clear();
        L135:
            this.pressedLink = null;
            this.pressedLinkDrawable = null;
            this.longPressFired = false;
            return true;
        L137:
            AnimatedEmojiSpan r120 = this.pressedEmoji;
            if (r120 == null) goto L148;
            this.pressedEmoji = null;
            ChatMessageCell r25 = this.root.getCell();
            ChatMessageCell.ChatMessageCellDelegate r32 = this.root.getDelegate();
            if (r25 == null) goto L180;
            if (r32 == null) goto L181;
            View r0 = this.view;
            if (r0 == null) goto L145;
            r0.playSoundEffect(0);
        L145:
            r32.didPressAnimatedEmoji(r25, r120);
            return true;
        L181:
            return true;
        L180:
            return true;
        L148:
            return false;
        L150:
            if (r1 != 3) goto L160;
            this.pressedSpoiler = null;
            this.pressedEmoji = null;
            if (this.pressedButtonSpan == null) goto L155;
            cancelLongPress();
            this.pressedButtonSpan.setPressed(false);
            this.pressedButtonSpan = null;
            this.longPressFired = false;
        L155:
            if (this.pressedLink == null) goto L160;
            cancelLongPress();
            LinkSpanDrawable.LinkCollector r121 = this.linkCollector;
            if (r121 == null) goto L159;
            r121.clear();
        L159:
            this.pressedLink = null;
            this.pressedLinkDrawable = null;
            this.longPressFired = false;
        L160:
            return false;
        }

        public void refreshAnimatedEmoji(int r7) {
            View r0 = this.view;
            if (r0 != null) goto L5;
            return;
        L5:
            AnimatedEmojiSpan.release(r0, this.animatedEmojiStack);
            this.animatedEmojiStack = null;
            View r1 = this.view;
            if (this.root.invalidateAnimatedEmojiInParent == true) goto L8;
        L10:
            boolean r2 = false;
        L11:
            this.animatedEmojiStack = AnimatedEmojiSpan.update(r7, r1, r2, null, new Layout[]{this.layout});
            return;
        L8:
            if (this.doNotInvalidateEmojiInParent == true) goto L10;
            r2 = true;
            goto L11
        }

        public void setBlockX(int r1) {
            this.blockX = r1;
        }

        public void setBlockY(int r1) {
            this.blockY = r1;
        }

        public void setDrawAtOrigin(boolean r1) {
            this.drawAtOrigin = r1;
        }

        @Override // org.telegram.ui.Components.TableLayout.CellText
        public void setRow(int r1) {
            this.row = r1;
        }

        @Override // org.telegram.ui.Components.TableLayout.CellText
        public void setX(int r1) {
            this.x = r1;
        }

        @Override // org.telegram.ui.Components.TableLayout.CellText
        public void setY(int r1) {
            this.y = r1;
        }
    }

    public static /* synthetic */ int $r8$lambda$znvpMFV93ubL1Q0IKFmuT0P_gFQ(QuoteBackground r0, QuoteBackground r1) {
        return r0.level - r1.level;
    }

    /* renamed from: -$$Nest$fgetcell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m4820$$Nest$fgetcell(RichMessageLayout r0) {
        return r0.cell;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell.ChatMessageCellDelegate m4821$$Nest$fgetdelegate(RichMessageLayout r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdetailsAnimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m4822$$Nest$fgetdetailsAnimationProgress(RichMessageLayout r0) {
        return r0.detailsAnimationProgress;
    }

    /* renamed from: -$$Nest$fgetfontSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4823$$Nest$fgetfontSize(RichMessageLayout r0) {
        return r0.fontSize;
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4824$$Nest$mgetThemedColor(RichMessageLayout r0, int r1) {
        return r0.getThemedColor(r1);
    }

    /* renamed from: -$$Nest$mgetThemedDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m4825$$Nest$mgetThemedDrawable(RichMessageLayout r0, String r1) {
        return r0.getThemedDrawable(r1);
    }

    /* renamed from: -$$Nest$mhandleAnchorClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m4826$$Nest$mhandleAnchorClick(RichMessageLayout r0, String r1) {
        return r0.handleAnchorClick(r1);
    }

    private RichMessageLayout(int r5, int r6, Theme.ResourcesProvider r7) {
        this.unsupportedBlocks = new ArrayList();
        this.unsupportedBlocksRoot = new ArrayList();
        this.blocks = new ArrayList();
        this.quotes = new ArrayList();
        this.anchors = new HashMap();
        this.textAnchors = new HashMap();
        this.audioMessages = new ArrayList();
        this.audioBlocks = new HashMap();
        this.textBlocks = new ArrayList();
        this.textBlockCharOffsets = new ArrayList();
        this.textBlockBlockIndex = new ArrayList();
        this.joinedText = _UrlKt.FRAGMENT_ENCODE_SET;
        this.detailsAnimationProgress = 1.0f;
        TextPaint r0 = new TextPaint(1);
        this.textPaint = r0;
        TextPaint r2 = new TextPaint(1);
        this.numTextPaint = r2;
        this.quoteLine = new ReplyMessageLine(null);
        this.clip = new GradientClip();
        this.showMoreRect = new RectF();
        this.messageObject = null;
        this.currentAccount = r5;
        this.maxWidth = r6;
        this.resourcesProvider = r7;
        this.fontSize = SharedConfig.fontSize;
        this.density = AndroidUtilities.density;
        r0.setTextSize(AndroidUtilities.dp(r5));
        r2.setTextSize(AndroidUtilities.dp(this.fontSize));
    }

    public RichMessageLayout(MessageObject r5, int r6, RichMessageLayout r7) {
        this.unsupportedBlocks = new ArrayList();
        this.unsupportedBlocksRoot = new ArrayList();
        this.blocks = new ArrayList();
        this.quotes = new ArrayList();
        this.anchors = new HashMap();
        this.textAnchors = new HashMap();
        this.audioMessages = new ArrayList();
        this.audioBlocks = new HashMap();
        this.textBlocks = new ArrayList();
        this.textBlockCharOffsets = new ArrayList();
        this.textBlockBlockIndex = new ArrayList();
        this.joinedText = _UrlKt.FRAGMENT_ENCODE_SET;
        this.detailsAnimationProgress = 1.0f;
        TextPaint r0 = new TextPaint(1);
        this.textPaint = r0;
        TextPaint r2 = new TextPaint(1);
        this.numTextPaint = r2;
        this.quoteLine = new ReplyMessageLine(null);
        this.clip = new GradientClip();
        this.showMoreRect = new RectF();
        this.messageObject = r5;
        this.maxWidth = r6;
        this.currentAccount = r5.currentAccount;
        r0.setTypeface(AndroidUtilities.regular());
        r2.setTypeface(AndroidUtilities.regular());
        layout(r7);
    }

    private void appendSelectionPiece(StringBuilder r1, CharSequence r2, int r3, int r4, boolean r5) {
        if (r4 <= r3) goto L16;
        String r0 = RichHtml.inlineToHtml(toRichHtmlSpannable(r2.subSequence(r3, r4)));
        if (r0.isEmpty() == false) goto L7;
        return;
    L7:
        if (r5 == false) goto L9;
        String r22 = "<cite>";
    L10:
        r1.append(r22);
        r1.append(r0);
        if (r5 == false) goto L13;
        String r02 = "</cite>";
    L14:
        r1.append(r02);
        return;
    L13:
        r02 = "</p>";
        goto L14
    L9:
        r22 = "<p>";
        goto L10
    }

    private void applyListPaddingFromBlocks() {
        int r1 = 0;
    L4:
        if (r1 >= this.blocks.size()) goto L32;
        RichBlock r2 = this.blocks.get(r1);
        if ((r2 instanceof RichTextBlock) == false) goto L31;
        if (r2.listLevel <= 0) goto L31;
        boolean r3 = true;
        if (r1 > 0) goto L13;
    L15:
        boolean r4 = false;
    L16:
        int r5 = r1 + 1;
        if (r5 < this.blocks.size()) goto L19;
    L21:
        r3 = false;
    L22:
        RichTextBlock r22 = (RichTextBlock) r2;
        if (r4 == false) goto L25;
        float r42 = 2.0f;
    L26:
        int r43 = AndroidUtilities.dp(r42);
        if (r3 == false) goto L29;
        float r32 = 5.0f;
    L30:
        r22.setContentPadding(r43, AndroidUtilities.dp(r32));
        goto L31
    L29:
        r32 = 9.0f;
        goto L30
    L25:
        r42 = 6.0f;
        goto L26
    L19:
        if (this.blocks.get(r5).listLevel <= 0) goto L21;
    L13:
        if (this.blocks.get(r1 - 1).listLevel <= 0) goto L15;
        r4 = true;
    L31:
        r1 = r1 + 1;
        goto L4
    }

    private void closeLists(StringBuilder r1, ArrayList<Boolean> r2) {
    L3:
        if (r2.isEmpty() == true) goto L9;
        if (r2.remove(r2.size() - 1).booleanValue() == false) goto L7;
        String r0 = "</ol>";
    L8:
        r1.append(r0);
        goto L3
    L7:
        r0 = "</ul>";
        goto L8
    }

    private void computeBlockquoteClips(float r7) {
        int r0 = 0;
    L4:
        if (r0 >= this.blocks.size()) goto L17;
        RichBlock r1 = this.blocks.get(r0);
        if ((r1 instanceof RichTextBlockQuote) == false) goto L16;
        RichTextBlockQuote r12 = (RichTextBlockQuote) r1;
        float r3 = 0.0f;
        if (RichTextBlockQuote.m4858$$Nest$fgetprevCollapsed(r12) == false) goto L11;
        float r2 = 1.0f;
    L13:
        if (RichTextBlockQuote.m4857$$Nest$fgetcurrentCollapsed(r12) == false) goto L15;
        r3 = 1.0f;
    L15:
        r12.collapsedProgress = AndroidUtilities.lerp(r2, r3, r7);
        r12.collapsedHeightToDraw = AndroidUtilities.lerp(r12.prevH, r12.currH, r7);
        goto L16
    L11:
        r2 = 0.0f;
    L16:
        r0 = r0 + 1;
        goto L4
    }

    private void computeDetailsClips(float r6) {
        int r0 = 0;
    L4:
        if (r0 >= this.blocks.size()) goto L18;
        RichBlock r1 = this.blocks.get(r0);
        if ((r1 instanceof RichDetailsBlock) == false) goto L17;
        RichDetailsBlock r12 = (RichDetailsBlock) r1;
        r12.animClipTop = AndroidUtilities.lerp(r12.prevY, r12.currY, r6) + r12.getHeight();
        int r2 = r0 + 1;
    L10:
        if (r2 >= this.blocks.size()) goto L15;
        if (isDescendantOf(this.blocks.get(r2), r12) == false) goto L14;
        r2 = r2 + 1;
        goto L10
    L14:
        float r22 = AndroidUtilities.lerp(this.blocks.get(r2).prevY, this.blocks.get(r2).currY, r6);
    L16:
        r12.animClipBottom = r22;
        goto L17
    L15:
        r22 = Float.MAX_VALUE;
    L17:
        r0 = r0 + 1;
        goto L4
    }

    public static MessageObject.GroupedMessagePosition[] computeGrouped(float[] r31) {
        int r1 = r31.length;
        MessageObject.GroupedMessagePosition[] r2 = new MessageObject.GroupedMessagePosition[r1];
        if (r1 == 0) goto L200;
        StringBuilder r3 = new StringBuilder();
        int r5 = 0;
        float r7 = 0.0f;
        int r6 = 0;
        boolean r8 = false;
    L6:
        float r10 = 1.0f;
        if (r6 >= r1) goto L23;
        float r12 = r31[r6];
        if (r12 <= 0.0f) goto L12;
        r10 = r12;
    L12:
        MessageObject.GroupedMessagePosition r122 = new MessageObject.GroupedMessagePosition();
        r2[r6] = r122;
        r122.aspectRatio = r10;
        if (r10 <= 1.2f) goto L16;
        r3.append("w");
    L19:
        r7 = r7 + r10;
        if (r10 <= 2.0f) goto L22;
        r8 = true;
    L22:
        r6 = r6 + 1;
        goto L6
    L16:
        if (r10 >= 0.8f) goto L18;
        r3.append("n");
        goto L19
    L18:
        r3.append("q");
        goto L19
    L23:
        float r72 = r7 / r1;
        int r62 = AndroidUtilities.dp(120.0f);
        float r0 = AndroidUtilities.dp(120.0f);
        Point r123 = AndroidUtilities.displaySize;
        int r02 = (int) (r0 / (Math.min(r123.x, r123.y) / 1000.0f));
        float r124 = AndroidUtilities.dp(40.0f);
        Point r14 = AndroidUtilities.displaySize;
        int r125 = (int) (r124 / (Math.min(r14.x, r14.y) / 1000.0f));
        float r15 = 814.0f;
        float r142 = AndroidUtilities.dp(100.0f) / 814.0f;
        if (r1 != 1) goto L27;
        r2[0].set(0, 0, 0, 0, 1000, Math.round(Math.min(1000.0f / r0.aspectRatio, 407.0f)) / 814.0f, 15);
        return r2;
    L27:
        int r9 = 4;
        int r13 = 2;
        int r18 = 1;
        int r11 = 3;
        if (r8 == true) goto L33;
        if (r1 == 2) goto L35;
        if (r1 == 3) goto L35;
        if (r1 != 4) goto L33;
    L35:
        if (r1 != 2) goto L58;
        MessageObject.GroupedMessagePosition r16 = r2[0];
        MessageObject.GroupedMessagePosition r52 = r2[1];
        String r32 = r3.toString();
        if (r32.equals("ww") == false) goto L45;
        if (r72 <= 1.7199017f) goto L45;
        if ((r16.aspectRatio - r52.aspectRatio) >= 0.2f) goto L45;
        float r23 = Math.round(Math.min(1000.0f / r7, Math.min(1000.0f / r11, 407.0f))) / 814.0f;
        r16.set(0, 0, 0, 0, 1000, r23, 7);
        r52.set(0, 0, 1, 1, 1000, r23, 11);
        return r2;
    L45:
        if (r32.equals("ww") == false) goto L47;
    L55:
        float r29 = Math.round(Math.min(500.0f / r16.aspectRatio, Math.min(500.0f / r52.aspectRatio, 814.0f))) / 814.0f;
        r16.set(0, 0, 0, 0, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, r29, 13);
        r52.set(1, 1, 0, 0, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, r29, 14);
        return r2;
    L47:
        if (r32.equals("qq") == true) goto L55;
        float r33 = r16.aspectRatio;
        int r34 = (int) Math.max(400.0f, Math.round((1000.0f / r33) / ((1.0f / r33) + (1.0f / r52.aspectRatio))));
        int r53 = 1000 - r34;
        if (r53 >= r02) goto L52;
        r34 = r34 - (r02 - r53);
    L53:
        float r292 = Math.min(814.0f, Math.round(Math.min(r02 / r16.aspectRatio, r34 / r52.aspectRatio))) / 814.0f;
        r16.set(0, 0, 0, 0, r02, r292, 13);
        r52.set(1, 1, 0, 0, r34, r292, 14);
        return r2;
    L52:
        r02 = r53;
        goto L53
    L58:
        if (r1 != 3) goto L69;
        MessageObject.GroupedMessagePosition r17 = r2[0];
        MessageObject.GroupedMessagePosition r63 = r2[1];
        MessageObject.GroupedMessagePosition r82 = r2[2];
        if (r3.charAt(0) != 'n') goto L63;
        float r35 = r63.aspectRatio;
        float r36 = Math.min(407.0f, Math.round((r35 * 1000.0f) / (r82.aspectRatio + r35)));
        int r03 = (int) Math.max(r02, Math.min(500.0f, Math.round(Math.min(r82.aspectRatio * r36, r63.aspectRatio * r4))));
        r17.set(0, 0, 0, 1, Math.round(Math.min((r17.aspectRatio * 814.0f) + r125, 1000 - r03)), 1.0f, 13);
        r63.set(1, 1, 0, 0, r03, (814.0f - r36) / 814.0f, 6);
        r82.set(1, 1, 1, 1, r03, r36 / 814.0f, 10);
        return r2;
    L63:
        float r232 = Math.round(Math.min(1000.0f / r17.aspectRatio, 537.24005f)) / 814.0f;
        r17.set(0, 1, 0, 0, 1000, r232, 7);
        float r04 = Math.min(814.0f - r232, Math.round(Math.min(500.0f / r63.aspectRatio, 500.0f / r82.aspectRatio))) / 814.0f;
        if (r04 >= r142) goto L66;
        float r293 = r142;
    L67:
        r63.set(0, 0, 1, 1, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, r293, 9);
        r82.set(1, 1, 1, 1, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, r293, 10);
        return r2;
    L66:
        r293 = r04;
        goto L67
    L69:
        MessageObject.GroupedMessagePosition r19 = r2[0];
        MessageObject.GroupedMessagePosition r4 = r2[1];
        MessageObject.GroupedMessagePosition r92 = r2[2];
        MessageObject.GroupedMessagePosition r112 = r2[3];
        if (r3.charAt(0) != 'w') goto L80;
        float r233 = Math.round(Math.min(1000.0f / r19.aspectRatio, 537.24005f)) / 814.0f;
        r19.set(0, 2, 0, 0, 1000, r233, 7);
        float r110 = Math.round(1000.0f / ((r4.aspectRatio + r92.aspectRatio) + r112.aspectRatio));
        float r05 = r02;
        int r37 = (int) Math.max(r05, Math.min(400.0f, r4.aspectRatio * r110));
        int r06 = (int) Math.max(Math.max(r05, 330.0f), r112.aspectRatio * r110);
        int r54 = (1000 - r37) - r06;
        if (r54 >= AndroidUtilities.dp(58.0f)) goto L74;
        int r73 = AndroidUtilities.dp(58.0f) - r54;
        r54 = AndroidUtilities.dp(58.0f);
        int r64 = r73 / 2;
        r37 = r37 - r64;
        r06 = r06 - (r73 - r64);
    L74:
        int r28 = r37;
        float r111 = Math.min(814.0f - r233, r110) / 814.0f;
        if (r111 >= r142) goto L77;
        float r294 = r142;
    L78:
        r4.set(0, 0, 1, 1, r28, r294, 9);
        r92.set(1, 1, 1, 1, r54, r294, 8);
        r112.set(2, 2, 1, 1, r06, r294, 10);
        return r2;
    L77:
        r294 = r111;
        goto L78
    L80:
        int r07 = Math.max(r02, Math.round(814.0f / (((1.0f / r4.aspectRatio) + (1.0f / r92.aspectRatio)) + (1.0f / r112.aspectRatio))));
        float r65 = r62;
        float r74 = r07;
        float r83 = Math.min(0.33f, Math.max(r65, r74 / r4.aspectRatio) / 814.0f);
        float r66 = Math.min(0.33f, Math.max(r65, r74 / r92.aspectRatio) / 814.0f);
        float r102 = (1.0f - r83) - r66;
        r19.set(0, 0, 0, 2, Math.round(Math.min((r19.aspectRatio * 814.0f) + r125, 1000 - r07)), (r83 + r66) + r102, 13);
        r4.set(1, 1, 0, 0, r07, r83, 6);
        r92.set(1, 1, 1, 1, r07, r66, 2);
        r112.set(1, 1, 2, 2, r07, r102, 10);
        return r2;
    L33:
        int r38 = 1000;
        float[] r42 = new float[r1];
        int r67 = 0;
    L83:
        if (r67 >= r1) goto L89;
        float r84 = r2[r67].aspectRatio;
        if (r72 <= 1.1f) goto L87;
        r42[r67] = Math.max(1.0f, r84);
    L88:
        r42[r67] = Math.max(0.66667f, Math.min(1.7f, r42[r67]));
        r67 = r67 + 1;
        goto L83
    L87:
        r42[r67] = Math.min(1.0f, r84);
        goto L88
    L89:
        ArrayList r68 = new ArrayList();
        ArrayList r85 = new ArrayList();
        int r103 = 1;
    L90:
        if (r103 >= r1) goto L97;
        int r126 = r1 - r103;
        if (r103 > 3) goto L94;
        if (r126 > 3) goto L94;
        r68.add(new int[]{r103, r126});
        float r312 = r15;
        r85.add(new float[]{multiHeight(r42, 0, r103, 1000), multiHeight(r42, r103, r1, 1000)});
    L96:
        r103 = r103 + 1;
        r15 = r312;
    L94:
        r312 = r15;
        goto L96
    L97:
        float r313 = r15;
        int r104 = 1;
    L99:
        if (r104 >= (r1 - 1)) goto L116;
        int r127 = 1;
    L101:
        int r152 = r1 - r104;
        if (r127 >= r152) goto L115;
        int r153 = r152 - r127;
        if (r104 <= 3) goto L106;
        int r192 = r13;
    L114:
        r127 = r127 + 1;
        r13 = r192;
        r9 = 4;
        goto L101
    L106:
        if (r72 >= 0.85f) goto L108;
        r192 = r13;
        int r132 = r9;
    L109:
        if (r127 > r132) goto L114;
        if (r153 > 3) goto L114;
        r68.add(new int[]{r104, r127, r153});
        float r133 = multiHeight(r42, 0, r104, 1000);
        int r154 = r104 + r127;
        float r20 = multiHeight(r42, r104, r154, 1000);
        float r155 = multiHeight(r42, r154, r1, 1000);
        float[] r93 = new float[3];
        r93[0] = r133;
        r93[1] = r20;
        r93[r192] = r155;
        r85.add(r93);
        goto L114
    L108:
        r192 = r13;
        r132 = 3;
        goto L109
    L115:
        r104 = r104 + 1;
        r9 = 4;
        goto L99
    L116:
        int r193 = r13;
        int r75 = 1;
    L118:
        if (r75 >= (r1 - 2)) goto L135;
        int r94 = 1;
    L120:
        int r105 = r1 - r75;
        if (r94 >= r105) goto L134;
        int r128 = 1;
    L123:
        int r134 = r105 - r94;
        if (r128 >= r134) goto L133;
        int r135 = r134 - r128;
        if (r75 > r11) goto L130;
        if (r94 > r11) goto L130;
        if (r128 > r11) goto L130;
        if (r135 > r11) goto L130;
        r68.add(new int[]{r75, r94, r128, r135});
        float r136 = multiHeight(r42, r5, r75, r38);
        int r156 = r75 + r94;
        float r202 = multiHeight(r42, r75, r156, r38);
        int r22 = r5;
        int r55 = r156 + r128;
        float r157 = multiHeight(r42, r156, r55, r38);
        float r56 = multiHeight(r42, r55, r1, r38);
        int r21 = r11;
        float[] r113 = new float[4];
        r113[r22] = r136;
        r113[1] = r202;
        r113[r193] = r157;
        r113[r21] = r56;
        r85.add(r113);
    L132:
        r128 = r128 + 1;
        r11 = r21;
        r5 = r22;
        r38 = 1000;
    L130:
        r22 = r5;
        r21 = r11;
        goto L132
    L133:
        r94 = r94 + 1;
        r38 = 1000;
        goto L120
    L134:
        r75 = r75 + 1;
        r38 = 1000;
        goto L118
    L135:
        int r222 = r5;
        int r212 = r11;
        int r76 = r222;
        int r95 = -1;
        float r106 = 0.0f;
    L137:
        if (r76 >= r68.size()) goto L172;
        float[] r114 = (float[]) r85.get(r76);
        int[] r129 = (int[]) r68.get(r76);
        int r137 = r114.length;
        float r158 = Float.MAX_VALUE;
        int r39 = r222;
        float r24 = 0.0f;
    L139:
        if (r39 >= r137) goto L144;
        float r25 = r114[r39];
        r24 = r24 + r25;
        if (r25 >= r158) goto L143;
        r158 = r25;
    L143:
        r39 = r39 + 1;
        goto L139
    L144:
        float r310 = Math.abs(r24 - 1333.3334f);
        int r138 = r18;
        if (r129.length <= r138) goto L161;
        int r115 = r129[r222];
        int r139 = r129[r138];
        if (r115 > r139) goto L159;
        int r57 = r193;
        if (r129.length > r57) goto L151;
    L154:
        int r1310 = r212;
        if (r129.length > r1310) goto L157;
    L163:
        if (r158 >= r02) goto L166;
        r310 = r310 * 1.5f;
    L166:
        if (r95 != (-1)) goto L168;
    L169:
        r106 = r310;
        r95 = r76;
    L170:
        r76 = r76 + 1;
        r193 = r57;
        r212 = r1310;
        r18 = 1;
        goto L137
    L168:
        if (r310 >= r106) goto L170;
    L157:
        if (r129[r57] <= r129[r1310]) goto L163;
    L160:
        r310 = r310 * 1.2f;
        goto L163
    L151:
        if (r139 <= r129[r57]) goto L154;
    L153:
        r1310 = r212;
        goto L160
    L159:
        r57 = r193;
        goto L153
    L161:
        r57 = r193;
        r1310 = r212;
        goto L163
    L172:
        if (r95 != (-1)) goto L176;
        int r58 = r222;
    L174:
        if (r58 >= r1) goto L200;
        int r203 = r58;
        r2[r58].set(0, 0, r203, r58, 1000, 0.4f, 3);
        r58 = r203 + 1;
        goto L174
    L176:
        int[] r08 = (int[]) r68.get(r95);
        float[] r116 = (float[]) r85.get(r95);
        int r59 = r222;
        int r86 = r59;
    L178:
        if (r86 >= r08.length) goto L200;
        int r1311 = r08[r86];
        float r159 = r116[r86];
        MessageObject.GroupedMessagePosition r69 = null;
        int r162 = r59;
        int r77 = r222;
        int r510 = 1000;
    L180:
        if (r77 >= r1311) goto L197;
        int r107 = (int) (r42[r162] * r159);
        int r172 = r510 - r107;
        MessageObject.GroupedMessagePosition r511 = r2[r162];
        if (r86 != 0) goto L184;
        int r96 = 4;
    L186:
        if (r86 != (r08.length - 1)) goto L188;
        r96 = r96 | 8;
    L188:
        if (r77 != 0) goto L191;
        r96 = r96 | 1;
    L191:
        if (r77 != (r1311 - 1)) goto L194;
        r96 = r96 | 2;
        MessageObject.GroupedMessagePosition r194 = r511;
    L195:
        int r610 = r77;
        r511.set(r610, r77, r86, r86, r107, Math.max(r142, r159 / r313), r96);
        r162 = r162 + 1;
        r77 = r610 + 1;
        r510 = r172;
        r69 = r194;
        goto L180
    L194:
        r194 = r69;
        goto L195
    L184:
        r96 = r222;
        goto L186
    L197:
        if (r69 == null) goto L199;
        r69.pw += r510;
        r69.spanSize += r510;
    L199:
        r86 = r86 + 1;
        r59 = r162;
    L200:
        return r2;
    }

    public static RichButtonSpan createEditorButtonSpan(int r6, int r7, Theme.ResourcesProvider r8, TL_iv.textButton r9) {
        return new RichButtonSpan(new RichMessageLayout(r6, r7, r8), r7, r9, Boolean.FALSE, null);
    }

    public static RichButton createEditorPageButton(int r15, int r16, Theme.ResourcesProvider r17, TL_keyboard.PageButton r18, Runnable r19) {
        RichMessageLayout r1 = new RichMessageLayout(r15, r16, r17);
        CharSequence r3 = r1.formatText(r18.text, setBlockFlags(16, 13));
        TL_keyboard.InlineButtonType r5 = r18.type;
        return new RichButton(r1, r16, r3, r18, r5, r18.style, r5 instanceof TL_keyboard.TL_inlineButtonTypeDisabled, true, false, false, false, false, Boolean.FALSE, r19);
    }

    private void drawBackground(Canvas r17, ChatMessageCell.TransitionParams r18) {
        int r11 = 0;
        if (this.quotes.isEmpty() == true) goto L11;
        ArrayList<QuoteBackground> r12 = this.quotes;
        int r13 = r12.size();
        int r1 = 0;
    L5:
        if (r1 >= r13) goto L11;
        int r14 = r1 + 1;
        QuoteBackground r3 = r12.get(r1);
        int r15 = getBlockTop(r3.startBlockIndex, r18);
        int r4 = getBlockBottom(r3.endBlockIndex, r18);
        float r5 = getBlockBackgroundScale(r3.startBlockIndex, r3.endBlockIndex);
        int r6 = r3.level * AndroidUtilities.dp(3.0f);
        int r8 = r3.outerTopVpad + r6;
        int r62 = r6 + r3.outerBottomVpad;
        if ((r4 - r15) <= (r8 + r62)) goto L9;
        r15 = r15 + r8;
        r4 = r4 - r62;
    L9:
        RectF r63 = AndroidUtilities.rectTmp;
        r63.set(r3.padding, r15, getMinWidth() - AndroidUtilities.dp(r3.level * 12), r4);
        r17.save();
        r17.scale(r5, r5, r63.centerX(), r63.centerY());
        float r42 = (float) Math.floor(SharedConfig.bubbleRadius / 3.0f);
        this.quoteLine.drawBackground(r17, r63, r42, r42, r42, 1.0f, false, false);
        this.quoteLine.drawLine(r17, r63);
        r17.restore();
        r1 = r14;
    L11:
        if (r11 >= this.blocks.size()) goto L21;
        RichBlock r16 = this.blocks.get(r11);
        if ((r16 instanceof RichPullquoteBlock) == false) goto L20;
        if (r16.currVisible == false) goto L17;
    L19:
        drawPullquoteBackground(r17, (RichPullquoteBlock) r16, r18);
        goto L20
    L17:
        if (r16.prevVisible == true) goto L19;
    L20:
        r11 = r11 + 1;
        goto L11
    }

    private void drawInternal(Canvas r9, ChatMessageCell.TransitionParams r10) {
        ChatMessageCell r0 = this.cell;
        if (r0 != null) goto L5;
    L8:
        boolean r1 = false;
    L7:
        boolean r5 = r1;
        if (r5 == false) goto L12;
        float r12 = r0.childPosition - r0.textY;
        float r7 = r0.visibleHeight + r12;
        float r6 = r12;
    L13:
        drawInternal(r9, r10, r5, r6, r7);
        return;
    L12:
        r6 = 0.0f;
        r7 = 0.0f;
        goto L13
    L5:
        if (r0.visibleHeight <= 0) goto L8;
        r1 = true;
        goto L7
    }

    private void drawInternal(Canvas r18, ChatMessageCell.TransitionParams r19, boolean r20, float r21, float r22) {
        drawBackground(r18, r19);
        updateTranslationLoading();
        float r8 = 0.0f;
        float r9 = 1.0f;
        if (r19 != null) goto L5;
    L9:
        float r10 = 1.0f;
    L10:
        this.detailsAnimationProgress = r10;
        if (r10 < 1.0f) goto L13;
        this.detailsAnimating = false;
        this.blockquoteAnimating = false;
    L13:
        boolean r3 = this.detailsAnimating;
        if (r3 == false) goto L16;
    L19:
        boolean r12 = false;
    L20:
        if (r3 == true) goto L22;
    L24:
        boolean r13 = false;
    L25:
        if (r13 == false) goto L27;
        computeDetailsClips(r10);
    L27:
        computeBlockquoteClips(r10);
        int r14 = 0;
    L29:
        if (r14 >= this.blocks.size()) goto L76;
        RichBlock r15 = this.blocks.get(r14);
        if (r15.currVisible == false) goto L33;
    L35:
        float r2 = AndroidUtilities.lerp(r15.prevY, r15.currY, r10);
        boolean r32 = r15 instanceof RichDetailsEndBlock;
        if (r32 == false) goto L39;
        float r4 = r9;
    L48:
        if (r4 <= r8) goto L34;
        int r5 = r15.getHeight();
        if (r12 == true) goto L53;
    L57:
        r18.save();
        if (r13 == false) goto L69;
        RichDetailsBlock r6 = r15.parentDetails;
        if (r6 == null) goto L69;
        if (r32 == true) goto L69;
        float r33 = -3.4028235E38f;
        float r7 = Float.MAX_VALUE;
    L63:
        if (r6 == null) goto L65;
        r33 = Math.max(r33, r6.animClipTop);
        r7 = Math.min(r7, r6.animClipBottom);
        r6 = r6.parentDetails;
        r9 = r9;
        goto L63
    L65:
        float r16 = r9;
        if (r7 > r33) goto L68;
        r18.restore();
    L75:
        r14 = r14 + 1;
        r9 = r16;
        goto L29
    L68:
        r18.clipRect(-this.padLeft, r33, getMinWidth() + this.padRight, r7);
        r8 = 0.0f;
    L70:
        r18.translate(r8, r2);
        if (r4 >= r16) goto L73;
        int r23 = r18.saveLayerAlpha(-this.padLeft, 0.0f, getMinWidth() + this.padRight, r5, (int) (r4 * 255.0f), 31);
        r15.drawWithTyping(r18);
        r18.restoreToCount(r23);
    L74:
        r18.restore();
        goto L75
    L73:
        r15.drawWithTyping(r18);
    L69:
        r16 = r9;
        goto L70
    L53:
        if ((r5 + r2) <= r21) goto L34;
        if (r2 < r22) goto L57;
    L34:
        r16 = r9;
        goto L75
    L39:
        if (r15.prevVisible == false) goto L41;
        float r42 = r9;
    L43:
        if (r15.currVisible == false) goto L45;
        float r52 = r9;
    L46:
        r4 = AndroidUtilities.lerp(r42, r52, r10);
        goto L48
    L45:
        r52 = r8;
        goto L46
    L41:
        r42 = r8;
        goto L43
    L33:
        if (r15.prevVisible == true) goto L35;
    L76:
        if (r10 < 1.0f) goto L84;
        snapshotForDetailsAnimation();
        return;
    L84:
        return;
    L22:
        if (r10 >= 1.0f) goto L24;
        r13 = true;
        goto L25
    L16:
        if (this.blockquoteAnimating == true) goto L19;
        r12 = r20;
        goto L20
    L5:
        if (this.detailsAnimating == false) goto L7;
    L8:
        r10 = Math.max(0.0f, Math.min(1.0f, r19.animateChangeProgress));
        goto L10
    L7:
        if (this.blockquoteAnimating == false) goto L9;
        goto L8
    }

    private void drawPullquoteBackground(Canvas r18, RichPullquoteBlock r19, ChatMessageCell.TransitionParams r20) {
        int r3 = r19.getTextWidth();
        if (r3 <= 0) goto L49;
        if (r20 != null) goto L7;
    L11:
        boolean r4 = false;
    L13:
        if (r4 == false) goto L15;
        float r2 = Math.max(0.0f, Math.min(1.0f, r20.animateChangeProgress));
    L16:
        if (r4 == false) goto L27;
        if (r19.prevVisible == false) goto L20;
        float r6 = 1.0f;
    L22:
        if (r19.currVisible == false) goto L24;
        float r7 = 1.0f;
    L25:
        float r72 = AndroidUtilities.lerp(r6, r7, r2);
    L31:
        if (r72 <= 0.0f) goto L50;
        float r5 = ((getMinWidth() + this.padRight) - this.padLeft) / 2.0f;
        float r32 = r3 / 2.0f;
        float r11 = (r5 - r32) - AndroidUtilities.dp(30.0f);
        float r12 = (r5 + r32) + AndroidUtilities.dp(30.0f);
        if (r4 == false) goto L36;
        float r33 = AndroidUtilities.lerp(r19.prevY, r19.currY, r2);
    L37:
        if (r4 == false) goto L40;
        int r1 = AndroidUtilities.lerp(r19.prevH, r19.currH, r2);
    L41:
        float r14 = r33 + AndroidUtilities.dp(8.0f);
        float r15 = (r33 + r1) - AndroidUtilities.dp(8.0f);
        if (r15 > r14) goto L44;
        return;
    L44:
        RectF r34 = AndroidUtilities.rectTmp;
        r34.set(r11, r14, r12, r15);
        float r42 = (float) Math.floor(SharedConfig.bubbleRadius / 2.0f);
        this.quoteLine.drawBackground(r18, r34, r42, r42, r42, r72, false, false);
        if (this.pullquoteIcon != null) goto L47;
        this.pullquoteIcon = ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.mini_quote).mutate();
    L47:
        this.pullquoteIcon.setColorFilter(this.quoteLine.getColor(), PorterDuff.Mode.SRC_IN);
        this.pullquoteIcon.setAlpha((int) (r72 * 255.0f));
        int r13 = this.pullquoteIcon.getIntrinsicWidth();
        int r35 = this.pullquoteIcon.getIntrinsicHeight();
        r18.save();
        r18.clipRect(r11, r14, r12, r15);
        int r52 = (int) r11;
        int r73 = (int) r14;
        this.pullquoteIcon.setBounds(AndroidUtilities.dp(8.0f) + r52, AndroidUtilities.dp(7.0f) + r73, (r52 + AndroidUtilities.dp(8.0f)) + r13, (r73 + AndroidUtilities.dp(7.0f)) + r35);
        r18.scale(-1.0f, -1.0f, this.pullquoteIcon.getBounds().centerX(), this.pullquoteIcon.getBounds().centerY());
        this.pullquoteIcon.draw(r18);
        r18.restore();
        r18.save();
        r18.clipRect(r11, r14, r12, r15);
        Drawable r43 = this.pullquoteIcon;
        int r53 = (int) r12;
        int r74 = (r53 - AndroidUtilities.dp(8.0f)) - r13;
        int r16 = (int) r15;
        r43.setBounds(r74, (r16 - AndroidUtilities.dp(7.0f)) - r35, r53 - AndroidUtilities.dp(8.0f), r16 - AndroidUtilities.dp(7.0f));
        r18.scale(1.0f, -1.0f, this.pullquoteIcon.getBounds().centerX(), this.pullquoteIcon.getBounds().centerY());
        this.pullquoteIcon.draw(r18);
        r18.restore();
        this.pullquoteIcon.setAlpha(255);
        return;
    L40:
        r1 = r19.getHeight();
        goto L41
    L36:
        r33 = r19.currY;
        goto L37
    L50:
        return;
    L24:
        r7 = 0.0f;
        goto L25
    L20:
        r6 = 0.0f;
        goto L22
    L27:
        if (r19.currVisible == false) goto L29;
        r72 = 1.0f;
        goto L31
    L29:
        r72 = 0.0f;
        goto L31
    L15:
        r2 = 1.0f;
        goto L16
    L7:
        if (this.detailsAnimating == false) goto L9;
    L10:
        r4 = true;
        goto L13
    L9:
        if (this.blockquoteAnimating == false) goto L11;
    }

    private void drawShowMoreButton(Canvas r11, int r12) {
        if (isOut() == false) goto L5;
        int r0 = Theme.key_chat_outPreviewInstantText;
    L6:
        int r5 = getThemedColor(r0);
        if (this.showMoreText != null) goto L9;
        this.showMoreText = new org.telegram.ui.Components.Text(LocaleController.getString(R.string.ShowMore), 16.0f, AndroidUtilities.bold());
    L9:
        ButtonBounce r02 = this.showMoreBounce;
        if (r02 != null) goto L12;
        this.showMoreBounce = new ButtonBounce(this.view, 1.5f, 2.0f);
    L16:
        if (this.showMorePaint != null) goto L18;
        this.showMorePaint = new Paint(1);
    L18:
        this.showMorePaint.setColor(Theme.multAlpha(r5, 0.1f));
        float r03 = AndroidUtilities.dp(42.0f);
        float r4 = this.showMoreText.getCurrentWidth();
        float r6 = ((getMinWidth() + this.padLeft) + this.padRight) - AndroidUtilities.dp(24.0f);
        int r7 = getMinWidth();
        float r72 = (((r7 + r8) + this.padRight) / 2.0f) - this.padLeft;
        float r122 = r12 + AndroidUtilities.dp(4.0f);
        float r62 = r6 / 2.0f;
        this.showMoreRect.set(r72 - r62, r122, r72 + r62, r03 + r122);
        ChatMessageCell r123 = this.cell;
        boolean r04 = false;
        if (r123 == null) goto L25;
        ChatMessageCell.ChatMessageCellDelegate r63 = this.delegate;
        if (r63 == null) goto L25;
        if (r63.isProgressLoading(r123, 7) == false) goto L25;
        boolean r124 = true;
    L26:
        LoadingDrawable r64 = this.showMoreLoading;
        if (r64 == null) goto L34;
        if (r124 == true) goto L34;
        if (r64.isDisappeared() == true) goto L34;
        if (this.showMoreLoading.isDisappearing() == true) goto L34;
        this.showMoreLoading.disappear();
    L34:
        LoadingDrawable r65 = this.showMoreLoading;
        if (r65 != null) goto L38;
        if (r124 == false) goto L38;
        LoadingDrawable r125 = new LoadingDrawable();
        this.showMoreLoading = r125;
        r125.strokePaint.setStrokeWidth(AndroidUtilities.dp(1.25f));
        this.showMoreLoading.setAppearByGradient(true);
    L45:
        LoadingDrawable r126 = this.showMoreLoading;
        if (r126 == null) goto L48;
        r126.setColors(Theme.multAlpha(r5, 0.1f), Theme.multAlpha(r5, 0.3f), Theme.multAlpha(r5, 0.3f), Theme.multAlpha(r5, 1.2f));
    L48:
        float r127 = this.showMoreBounce.getScale(0.075f);
        if (r127 == 1.0f) goto L51;
        r04 = true;
    L51:
        if (r04 == false) goto L53;
        r11.save();
        r11.scale(r127, r127, this.showMoreRect.centerX(), this.showMoreRect.centerY());
    L53:
        r11.drawRoundRect(this.showMoreRect, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.showMorePaint);
        LoadingDrawable r128 = this.showMoreLoading;
        if (r128 != null) goto L56;
    L60:
        this.showMoreText.draw(r11, this.showMoreRect.centerX() - (r4 / 2.0f), this.showMoreRect.centerY(), r5, 1.0f);
        if (r04 == false) goto L64;
        r11.restore();
        return;
    L64:
        return;
    L56:
        if (r128.isDisappeared() == true) goto L60;
        this.showMoreLoading.setBounds(this.showMoreRect);
        this.showMoreLoading.setRadiiDp(8.0f);
        this.showMoreLoading.draw(r11);
        View r129 = this.view;
        if (r129 == null) goto L60;
        r129.invalidate();
    L38:
        if (r65 == null) goto L45;
        if (r124 == false) goto L45;
        if (r65.isDisappeared() == false) goto L43;
    L44:
        this.showMoreLoading.reset();
        this.showMoreLoading.resetDisappear();
        goto L45
    L43:
        if (this.showMoreLoading.isDisappearing() == false) goto L45;
    L25:
        r124 = false;
        goto L26
    L12:
        View r05 = r02.getView();
        View r2 = this.view;
        if (r05 == r2) goto L16;
        this.showMoreBounce.setView(r2);
        goto L16
    L5:
        r0 = Theme.key_chat_inPreviewInstantText;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v79, types: [org.telegram.messenger.RichMessageLayout$RichTextBlock, org.telegram.messenger.RichMessageLayout$RichTextBlockQuote] */
    private RichBlock emitBlock(TL_iv.PageBlock r26, int r27, Rect r28, int r29, boolean r30) {
        RichMessageLayout r1 = this;
        int r4 = r29;
        RichBlock r6 = null;
        if ((r28.left + r28.right) < r1.maxWidth) goto L6;
        return null;
    L6:
        if ((r26 instanceof TL_iv.pageBlockThinking) == false) goto L9;
        RichThinkingBlock r2 = new RichThinkingBlock(r1, new Rect(), r1.maxWidth, r1.formatText(r26.text));
        r1.blocks.add(r2);
        return r2;
    L9:
        int r8 = 0;
        if (ArticleViewer.isHeadingBlock(r26) == true) goto L276;
        if ((r26 instanceof TL_iv.pageBlockFooter) == true) goto L276;
        if ((r26 instanceof TL_iv.pageBlockParagraph) == true) goto L276;
        if ((r26 instanceof TL_iv.pageBlockPreformatted) == false) goto L20;
        RichPreformattedBlock r3 = new RichPreformattedBlock(r1, r28, r1.maxWidth, (TL_iv.pageBlockPreformatted) r26, (RichPreformattedBlock) r1.findPrevBlock(r26, RichPreformattedBlock.class));
        r3.accessibilityLabelResId = R.string.ArticleCode;
        r1.blocks.add(r3);
        return r3;
    L20:
        int r11 = 1;
        if ((r26 instanceof TL_iv.pageBlockList) == false) goto L66;
        TL_iv.pageBlockList r7 = (TL_iv.pageBlockList) r26;
        int r22 = r27 + 1;
        r1.numTextPaint.setTextSize(AndroidUtilities.dp(SharedConfig.fontSize));
        int r12 = AndroidUtilities.dp(18.0f);
        int r5 = 0;
    L24:
        if (r5 >= r7.items.size()) goto L30;
        if (r7.items.get(r5).checkbox == false) goto L28;
        int r13 = AndroidUtilities.dp(26.0f);
    L29:
        r12 = Math.max(r12, r13);
        r5 = r5 + 1;
        goto L24
    L28:
        r13 = AndroidUtilities.dp(18.0f);
        goto L29
    L30:
        Rect r132 = new Rect(r28);
        if (r1.isRtl() == false) goto L33;
        r132.right += r12;
    L34:
        int r9 = 0;
    L36:
        if (r9 >= r7.items.size()) goto L65;
        TL_iv.PageListItem r0 = r7.items.get(r9);
        if ((r0 instanceof TL_iv.TL_pageListItemText) == false) goto L45;
        TL_iv.TL_pageListItemText r02 = (TL_iv.TL_pageListItemText) r0;
        RichBlock r16 = r6;
        RichTextBlock r32 = new RichTextBlock(r1, new Rect(r132), r1.maxWidth, r1.formatText(r02.text, r4));
        r32.setListMarkerWidth(r12);
        if (r02.checkbox == false) goto L42;
        r32.setCheckbox(r02.checked, r02);
    L43:
        markListItem(r32, r22, false, r02.checkbox, r02.checked);
        r1.blocks.add(r32);
    L44:
        int r03 = r22;
        RichMessageLayout r23 = r1;
    L63:
        r9 = r9 + 1;
        r1 = r23;
        r6 = r16;
        r22 = r03;
        goto L36
    L42:
        r32.setNum("•◦▪".charAt(r27 % 3) + _UrlKt.FRAGMENT_ENCODE_SET);
        goto L43
    L45:
        r16 = r6;
        if ((r0 instanceof TL_iv.TL_pageListItemBlocks) == false) goto L44;
        TL_iv.TL_pageListItemBlocks r62 = (TL_iv.TL_pageListItemBlocks) r0;
        if (r62.blocks.isEmpty() == true) goto L44;
        int r15 = r1.blocks.size();
        int r04 = 0;
        boolean r17 = false;
    L52:
        if (r04 >= r62.blocks.size()) goto L62;
        int r18 = r04;
        RichBlock r14 = emitBlock(r62.blocks.get(r04), r22, new Rect(r132), r4, previousBlockIsParagraph(r62.blocks, r04));
        int r24 = r22;
        if (r14 == null) goto L61;
        if (r17 == true) goto L61;
        r14.setListMarkerWidth(r12);
        if (r62.checkbox == false) goto L59;
        r14.setCheckbox(r62.checked, r62);
    L60:
        markListItem(r14, r24, false, r62.checkbox, r62.checked);
        r17 = true;
        goto L61
    L59:
        r14.setNum("•◦▪".charAt(r27 % 3) + _UrlKt.FRAGMENT_ENCODE_SET);
    L61:
        r22 = r24;
        r04 = r18 + 1;
        r1 = this;
        goto L52
    L62:
        r03 = r22;
        r23 = r1;
        r23.markListMembership(r15, r23.blocks.size(), r03, false);
        goto L63
    L65:
        return r6;
    L33:
        r132.left += r12;
        goto L34
    L66:
        RichMessageLayout r25 = r1;
        if ((r26 instanceof TL_iv.pageBlockOrderedList) == false) goto L112;
        TL_iv.pageBlockOrderedList r63 = (TL_iv.pageBlockOrderedList) r26;
        int r05 = r27 + 1;
        r25.numTextPaint.setTextSize(AndroidUtilities.dp(SharedConfig.fontSize));
        new TextPaint(r25.numTextPaint).setTypeface(AndroidUtilities.bold());
        int r72 = AndroidUtilities.dp(28.0f);
        int r10 = 0;
    L70:
        if (r10 >= r63.items.size()) goto L76;
        TL_iv.PageListOrderedItem r122 = r63.items.get(r10);
        String r133 = orderedListMarker(r63, r122, r10);
        if (r122.checkbox == false) goto L74;
        int r123 = AndroidUtilities.dp(26.0f);
    L75:
        r72 = Math.max(r72, (((int) Math.ceil(r1.measureText(r133))) + AndroidUtilities.dp(10.0f)) + r123);
        r10 = r10 + 1;
        goto L70
    L74:
        r123 = 0;
        goto L75
    L76:
        Rect r102 = new Rect(r28);
        if (r25.isRtl() == false) goto L79;
        r102.right += r72;
    L80:
        int r92 = 0;
    L82:
        if (r92 >= r63.items.size()) goto L111;
        TL_iv.PageListOrderedItem r19 = r63.items.get(r92);
        if ((r19 instanceof TL_iv.TL_pageListOrderedItemText) == false) goto L91;
        TL_iv.TL_pageListOrderedItemText r110 = (TL_iv.TL_pageListOrderedItemText) r19;
        RichTextBlock r33 = new RichTextBlock(r25, new Rect(r102), r25.maxWidth, r25.formatText(r110.text, r4));
        r33.setListMarkerWidth(r72);
        r33.setNum(orderedListMarker(r63, r110, r92));
        if (r110.checkbox == false) goto L88;
        r33.setCheckbox(r110.checked, r110);
    L88:
        markListItem(r33, r05, true, r110.checkbox, r110.checked);
        r25.blocks.add(r33);
    L89:
        int r34 = r4;
    L110:
        r92 = r92 + 1;
        r4 = r34;
        goto L82
    L91:
        if ((r19 instanceof TL_iv.TL_pageListOrderedItemBlocks) == false) goto L89;
        TL_iv.TL_pageListOrderedItemBlocks r124 = (TL_iv.TL_pageListOrderedItemBlocks) r19;
        if (r124.blocks.isEmpty() == true) goto L89;
        int r134 = r25.blocks.size();
        int r142 = 0;
        boolean r152 = false;
    L97:
        if (r142 >= r124.blocks.size()) goto L109;
        RichMessageLayout r242 = r25;
        int r210 = r05;
        RichBlock r111 = r242.emitBlock(r124.blocks.get(r142), r210, new Rect(r102), r4, previousBlockIsParagraph(r124.blocks, r142));
        r25 = r242;
        r05 = r210;
        int r35 = r4;
        if (r111 == null) goto L108;
        if (r152 == true) goto L108;
        r111.setListMarkerWidth(r72);
        if (r124.checkbox == false) goto L104;
        r111.setCheckbox(r124.checked, r124);
    L104:
        r111.setNum(orderedListMarker(r63, r124, r92));
        if (r124.checkbox == false) goto L107;
        r111.setCheckbox(r124.checked, r124);
    L107:
        markListItem(r111, r05, true, r124.checkbox, r124.checked);
        r152 = true;
    L108:
        r142 = r142 + 1;
        r4 = r35;
        goto L97
    L109:
        r34 = r4;
        r25.markListMembership(r134, r25.blocks.size(), r05, true);
        goto L110
    L111:
        return null;
    L79:
        r102.left += r72;
        goto L80
    L112:
        int r36 = r4;
        float r125 = 4.0f;
        float r135 = 12.0f;
        if ((r26 instanceof TL_iv.pageBlockBlockquote) == false) goto L135;
        int r73 = r28.left;
        int r103 = r25.blocks.size();
        TL_iv.pageBlockBlockquote r42 = (TL_iv.pageBlockBlockquote) r26;
        CharSequence r06 = r25.formatText(r26.text, setBlockFlags(r36, getBlockTextFlag(r26)));
        TL_iv.RichText r52 = r42.caption;
        if (r52 != null) goto L117;
    L119:
        CharSequence r53 = null;
    L121:
        if (r42.collapsed == false) goto L129;
        SpannableStringBuilder r112 = new SpannableStringBuilder(r06);
        if (r53 == null) goto L126;
        r112.append('\n');
        int r07 = r112.length();
        r112.append(r53);
    L125:
        int r143 = r07;
        ?? r08 = new RichTextBlockQuote(r25, new Rect(r28.left + AndroidUtilities.dp(12.0f), r28.top + AndroidUtilities.dp(4.0f), r28.right + AndroidUtilities.dp(20.0f), r28.bottom + AndroidUtilities.dp(4.0f)), r25.maxWidth, r42, r112);
        TL_iv.pageBlockBlockquote r126 = r42;
        r08.quoteAuthorStart = r143;
        r08.setContentPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        RichMessageLayout r113 = r25;
        RichQuoteBlock r09 = r08;
    L128:
        RichQuoteBlock r93 = r09;
        r93.accessibilityLabelResId = R.string.ArticleQuote;
        r113.blocks.add(r93);
        ArrayList<QuoteBackground> r136 = r113.quotes;
        int r211 = r113.blocks.size() - 1;
        int r54 = AndroidUtilities.dp(8.0f);
        if (r126.collapsed == false) goto L133;
        r8 = AndroidUtilities.dp(8.0f);
    L133:
        r136.add(new QuoteBackground(r103, r211, r73, r27, r54, r8));
        return r93;
    L126:
        r07 = -1;
        goto L125
    L129:
        r126 = r42;
        r113 = r25;
        r09 = new RichQuoteBlock(r113, new Rect(r28.left + AndroidUtilities.dp(12.0f), r28.top + AndroidUtilities.dp(12.0f), r28.right + AndroidUtilities.dp(12.0f), r28.bottom + AndroidUtilities.dp(14.0f)), r25.maxWidth, r06, r53);
        goto L128
    L117:
        if (TextUtils.isEmpty(getString(r52)) == true) goto L119;
        r53 = r25.formatText(r42.caption, setBlockFlags(r36, 11));
        goto L121
    L135:
        RichMessageLayout r114 = r25;
        if ((r26 instanceof TL_iv.pageBlockBlockquoteBlocks) == false) goto L173;
        int r104 = r28.left;
        int r212 = r27 + 1;
        int r43 = r114.blocks.size();
        TL_iv.pageBlockBlockquoteBlocks r010 = (TL_iv.pageBlockBlockquoteBlocks) r26;
        TL_iv.RichText r55 = r010.caption;
        if (r55 != null) goto L140;
    L142:
        boolean r172 = false;
    L143:
        int r56 = 0;
    L145:
        if (r56 >= r010.blocks.size()) goto L165;
        if (r56 != 0) goto L149;
        int r74 = r11;
    L148:
        float r192 = r125;
        if (r56 != (r010.blocks.size() - r11)) goto L154;
        int r127 = r11;
    L153:
        float r20 = r135;
        TL_iv.PageBlock r137 = r010.blocks.get(r56);
        int r21 = r11;
        int r82 = r28.left + AndroidUtilities.dp(r20);
        int r64 = r28.top;
        if (r74 == 0) goto L158;
        int r75 = AndroidUtilities.dp(r192);
    L159:
        int r65 = r64 + r75;
        int r76 = r28.right + AndroidUtilities.dp(r20);
        int r153 = r28.bottom;
        if (r127 == 0) goto L163;
        if (r172 == true) goto L163;
        int r128 = AndroidUtilities.dp(r192);
    L164:
        Rect r115 = new Rect(r82, r65, r76, r153 + r128);
        int r77 = r43;
        int r44 = setBlockFlags(r36, 9);
        boolean r66 = previousBlockIsParagraph(r010.blocks, r56);
        TL_iv.pageBlockBlockquoteBlocks r83 = r010;
        RichMessageLayout r011 = r114;
        r011.emitBlock(r137, r212, r115, r44, r66);
        r114 = r011;
        r56 = r56 + 1;
        r36 = r36;
        r43 = r77;
        r010 = r83;
        r125 = r192;
        r135 = r20;
        r11 = r21;
        r8 = 0;
    L163:
        r128 = 0;
        goto L164
    L158:
        r75 = 0;
        goto L159
    L154:
        r127 = r8;
        goto L153
    L149:
        r74 = r8;
        goto L148
    L165:
        TL_iv.pageBlockBlockquoteBlocks r84 = r010;
        int r67 = r36;
        int r78 = r43;
        float r202 = r135;
        if (r114.blocks.size() <= r78) goto L168;
        r114.blocks.get(r78).accessibilityParentLabelResId = R.string.ArticleQuote;
    L168:
        if (r172 == false) goto L170;
        RichTextBlock r213 = new RichTextBlock(r114, new Rect(r28.left + AndroidUtilities.dp(r202), r28.top, r28.right + AndroidUtilities.dp(r202), r28.bottom + AndroidUtilities.dp(6.0f)), r114.maxWidth, new SpannableStringBuilder(r114.formatText(r84.caption, setBlockFlags(r67, 11))));
        r213.quoteAuthorStart = 0;
        r213.setContentPadding(AndroidUtilities.dp(2.0f), 0);
        r114.blocks.add(r213);
    L170:
        r114.quotes.add(new QuoteBackground(r78, r114.blocks.size() - 1, r104, r27));
        return null;
    L140:
        if (TextUtils.isEmpty(getString(r55)) == true) goto L142;
        r172 = true;
        goto L143
    L173:
        if ((r26 instanceof TL_iv.pageBlockPullquote) == false) goto L183;
        TL_iv.pageBlockPullquote r214 = (TL_iv.pageBlockPullquote) r26;
        CharSequence r45 = r114.formatText(r26.text, setBlockFlags(r36, getBlockTextFlag(r26)));
        TL_iv.RichText r012 = r214.caption;
        if (r012 != null) goto L177;
    L179:
        CharSequence r57 = null;
    L180:
        RichPullquoteBlock r013 = new RichPullquoteBlock(r114, new Rect(r28.left + AndroidUtilities.dp(30.0f), r28.top + AndroidUtilities.dp(16.0f), r28.right + AndroidUtilities.dp(30.0f), r28.bottom + AndroidUtilities.dp(16.0f)), r114.maxWidth, r45, r57);
        r013.accessibilityLabelResId = R.string.ArticlePullquote;
        r114.blocks.add(r013);
        return r013;
    L177:
        if (TextUtils.isEmpty(getString(r012)) == true) goto L179;
        r57 = r114.formatText(r214.caption, setBlockFlags(r36, 11));
        goto L180
    L183:
        if ((r26 instanceof TL_iv.pageBlockButtonRow) == false) goto L187;
        RichButtonRowBlock r215 = new RichButtonRowBlock(r114, r28, r114.maxWidth, (TL_iv.pageBlockButtonRow) r26);
        r215.accessibilityLabelResId = R.string.AccDescrIVButtons;
        r114.blocks.add(r215);
        return r215;
    L187:
        if ((r26 instanceof TL_iv.pageBlockTable) == false) goto L191;
        RichTableBlock r216 = new RichTableBlock(r114, r28, r114.maxWidth, (TL_iv.pageBlockTable) r26);
        r216.accessibilityLabelResId = R.string.AccDescrIVTable;
        r114.blocks.add(r216);
        return r216;
    L191:
        if ((r26 instanceof TL_iv.pageBlockMath) == false) goto L195;
        RichMathBlock r217 = new RichMathBlock(r114, r28, r114.maxWidth, (TL_iv.pageBlockMath) r26);
        r114.blocks.add(r217);
        return r217;
    L195:
        if ((r26 instanceof TL_iv.pageBlockDivider) == false) goto L199;
        RichDividerBlock r014 = new RichDividerBlock(r114, r28, r114.maxWidth);
        r114.blocks.add(r014);
        return r014;
    L199:
        if ((r26 instanceof TL_iv.pageBlockPhoto) == false) goto L203;
        TL_iv.pageBlockPhoto r46 = (TL_iv.pageBlockPhoto) r26;
        RichPhotoBlock r015 = new RichPhotoBlock(r114, r28, r114.maxWidth, r46, r114.blocks.isEmpty());
        r114.blocks.add(r015);
        r114.emitCaption(r46.caption, r28, r36);
        return r015;
    L203:
        if ((r26 instanceof TL_iv.pageBlockVideo) == false) goto L207;
        TL_iv.pageBlockVideo r47 = (TL_iv.pageBlockVideo) r26;
        RichVideoBlock r016 = new RichVideoBlock(r114, r28, r114.maxWidth, r47, r114.blocks.isEmpty());
        r114.blocks.add(r016);
        r114.emitCaption(r47.caption, r28, r36);
        return r016;
    L207:
        if ((r26 instanceof TL_iv.pageBlockCollage) == false) goto L211;
        TL_iv.pageBlockCollage r48 = (TL_iv.pageBlockCollage) r26;
        RichCollageBlock r017 = new RichCollageBlock(r114, r28, r114.maxWidth, r48, r114.blocks.isEmpty());
        r114.blocks.add(r017);
        r114.emitCaption(r48.caption, r28, r36);
        return r017;
    L211:
        if ((r26 instanceof TL_iv.pageBlockSlideshow) == false) goto L215;
        TL_iv.pageBlockSlideshow r49 = (TL_iv.pageBlockSlideshow) r26;
        RichSlideshowBlock r018 = new RichSlideshowBlock(r114, r28, r114.maxWidth, r49, r114.blocks.isEmpty());
        r114.blocks.add(r018);
        r114.emitCaption(r49.caption, r28, r36);
        return r018;
    L215:
        if ((r26 instanceof TL_iv.pageBlockMap) == false) goto L219;
        TL_iv.pageBlockMap r019 = (TL_iv.pageBlockMap) r26;
        RichMapBlock r37 = new RichMapBlock(r114, r28, r114.maxWidth, r019);
        r114.blocks.add(r37);
        r114.emitCaption(r019.caption, r28, r36);
        return r37;
    L219:
        if ((r26 instanceof TL_iv.pageBlockAudio) == false) goto L231;
        TL_iv.pageBlockAudio r020 = (TL_iv.pageBlockAudio) r26;
        if (r114.audioBlocks.get(r020) != null) goto L228;
        TLRPC.Document r38 = r114.getDocument(r020.audio_id);
        if (r38 == null) goto L228;
        TLRPC.TL_message r410 = new TLRPC.TL_message();
        r410.out = true;
        int r58 = -Long.valueOf(r020.audio_id).hashCode();
        r020.mid = r58;
        r410.id = r58;
        r410.realId = r114.messageObject.getRealId();
        r410.dialog_id = r114.messageObject.getDialogId();
        TLRPC.Peer r59 = r114.messageObject.messageOwner.peer_id;
        r410.peer_id = r59;
        if (r59 != null) goto L227;
        TLRPC.TL_peerUser r510 = new TLRPC.TL_peerUser();
        r410.peer_id = r510;
        r510.user_id = UserConfig.getInstance(r114.currentAccount).getClientUserId();
    L227:
        TLRPC.TL_peerUser r511 = new TLRPC.TL_peerUser();
        r410.from_id = r511;
        r511.user_id = UserConfig.getInstance(r114.currentAccount).getClientUserId();
        r410.date = (int) (System.currentTimeMillis() / 1000);
        r410.message = _UrlKt.FRAGMENT_ENCODE_SET;
        TLRPC.TL_messageMediaDocument r512 = new TLRPC.TL_messageMediaDocument();
        r410.media = r512;
        r512.flags |= 3;
        r512.document = r38;
        r410.flags |= 768;
        MessageObject r39 = new MessageObject(r114.currentAccount, r410, false, true);
        r114.audioMessages.add(r39);
        r114.audioBlocks.put(r020, r39);
    L228:
        RichAudioBlock r310 = new RichAudioBlock(r114, r28, r114.maxWidth, r020);
        r114.blocks.add(r310);
        r114.emitCaption(r020.caption, r28, r36);
        return r310;
    L231:
        if ((r26 instanceof TL_iv.pageBlockDocument) == false) goto L235;
        TL_iv.pageBlockDocument r021 = (TL_iv.pageBlockDocument) r26;
        RichDocumentBlock r311 = new RichDocumentBlock(r114, r28, r114.maxWidth, r021);
        r114.blocks.add(r311);
        r114.emitCaption(r021.caption, r28, r36);
        return r311;
    L235:
        if ((r26 instanceof TL_iv.pageBlockCover) == false) goto L239;
        return r114.emitBlock(((TL_iv.pageBlockCover) r26).cover, r27, r28, r36, false);
    L239:
        if ((r26 instanceof TL_iv.pageBlockAnchor) == false) goto L245;
        String r022 = ((TL_iv.pageBlockAnchor) r26).name;
        if (r022 == null) goto L243;
        r114.anchors.put(r022.toLowerCase(), Integer.valueOf(r114.blocks.size()));
    L243:
        return null;
    L245:
        if ((r26 instanceof TL_iv.pageBlockUnsupported) == false) goto L258;
        if (BitwiseUtils.hasFlag(r36, 9) == false) goto L249;
        int r85 = AndroidUtilities.dp(6.0f);
    L252:
        RichUnsupportedBlock r023 = new RichUnsupportedBlock(r114, new Rect((r28.left + r85) - AndroidUtilities.dp(7.0f), Math.max(AndroidUtilities.dp(14.0f), r28.top), (r28.right + r85) - AndroidUtilities.dp(7.0f), Math.max(AndroidUtilities.dp(14.0f), r28.bottom)), r114.maxWidth, r114.blocks.size(), r27);
        r114.unsupportedBlocks.add(r023);
        if (r27 != 0) goto L255;
        r114.unsupportedBlocksRoot.add(r023);
    L255:
        r114.blocks.add(r023);
        return r023;
    L249:
        if (r27 <= 0) goto L251;
        r85 = AndroidUtilities.dp(4.0f);
        goto L252
    L251:
        r85 = 0;
        goto L252
    L258:
        if ((r26 instanceof TL_iv.pageBlockDetails) == false) goto L272;
        TL_iv.pageBlockDetails r411 = (TL_iv.pageBlockDetails) r26;
        RichDetailsBlock r024 = new RichDetailsBlock(r114, r28, r114.maxWidth, r411, r114.formatText(r411.title, r36 & (-17)));
        r114.blocks.add(r024);
        int r94 = r114.blocks.size();
        int r105 = 0;
    L261:
        if (r105 >= r411.blocks.size()) goto L263;
        RichMessageLayout r312 = r114;
        r312.emitBlock(r411.blocks.get(r105), r27 + 1, r28, r36, previousBlockIsParagraph(r411.blocks, r105));
        r114 = r312;
        r105 = r105 + 1;
        goto L261
    L263:
        r114.blocks.add(new RichDetailsEndBlock(r114, new Rect(r28.left, 0, r28.right, 0), r114.maxWidth));
    L265:
        if (r94 >= r114.blocks.size()) goto L270;
        RichBlock r025 = r114.blocks.get(r94);
        if (r025.parentDetails != null) goto L269;
        r025.parentDetails = r024;
    L269:
        r94 = r94 + 1;
        goto L265
    L270:
        return r024;
    L272:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L275;
        RichTextBlock r313 = new RichTextBlock(r114, r28, r114.maxWidth, "unsupported block " + r26);
        r114.blocks.add(r313);
        return r313;
    L275:
        return null;
    L276:
        boolean r314 = ArticleViewer.isHeadingBlock(r26);
        RichTextBlock r79 = new RichTextBlock(r1, r28, r1.maxWidth, r1.formatText(r26.text, setBlockFlags(r4, getBlockTextFlag(r26))));
        if (r314 == false) goto L280;
        r79.setContentPadding(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(6.0f));
    L285:
        r79.accessibilityLabelResId = getBlockAccessibilityLabel(r26);
        r1.blocks.add(r79);
        return r79;
    L280:
        if ((r26 instanceof TL_iv.pageBlockParagraph) == false) goto L285;
        if (r30 == false) goto L283;
        int r86 = 0;
    L284:
        r79.setContentPadding(r86, AndroidUtilities.dp(4.66f));
        goto L285
    L283:
        r86 = AndroidUtilities.dp(5.0f);
        goto L284
    }

    private void emitCaption(TL_iv.PageCaption r10, Rect r11, int r12) {
        if (r10 == null) goto L26;
        TL_iv.RichText r0 = r10.text;
        boolean r1 = false;
        if (r0 != null) goto L7;
    L9:
        boolean r02 = false;
    L10:
        TL_iv.RichText r3 = r10.credit;
        if (r3 != null) goto L13;
    L15:
        if (r02 == true) goto L18;
        if (r1 == true) goto L18;
        return;
    L18:
        int r122 = setBlockFlags(r12, 10);
        CharSequence r2 = null;
        if (r02 == false) goto L21;
        CharSequence r7 = formatText(r10.text, r122);
    L22:
        if (r1 == false) goto L24;
        r2 = formatText(r10.credit, r122);
    L24:
        this.blocks.add(new RichCaptionBlock(this, r11, this.maxWidth, r7, r2));
        return;
    L21:
        r7 = null;
        goto L22
    L13:
        if ((r3 instanceof TL_iv.textEmpty) == true) goto L15;
        r1 = true;
        goto L15
    L7:
        if ((r0 instanceof TL_iv.textEmpty) == true) goto L9;
        r02 = true;
        goto L10
    }

    private <T extends RichBlock> T findPrevBlock(TL_iv.PageBlock r8, Class<T> r9) {
        RichMessageLayout r0 = this.prev;
        if (r0 != null) goto L5;
        return null;
    L5:
        ArrayList<RichBlock> r02 = r0.blocks;
        int r2 = r02.size();
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L16;
        RichBlock r4 = r02.get(r3);
        r3 = r3 + 1;
        RichBlock r42 = r4;
        if (r9.isInstance(r42) == false) goto L6;
        if ((r42 instanceof RichPreformattedBlock) == false) goto L6;
        if (prefixEquals(((RichPreformattedBlock) r42).plain, getString(r8.text)) == false) goto L6;
        return r9.cast(r42);
    L16:
        return null;
    }

    private CharSequence formatTextAndSetSpan(TL_iv.RichText r2, SpannableStringBuilder r3, int r4, Object r5) {
        int r0 = r3.length();
        formatText(r2, r3, r4);
        if (r3.length() <= r0) goto L5;
        setSpansWithoutClash(r5, r3, r0, r3.length());
    L5:
        return r3;
    }

    private CharSequence formatTextAndSetSpan(TL_iv.RichText r2, SpannableStringBuilder r3, int r4, Object r5, Object r6) {
        int r0 = r3.length();
        formatText(r2, r3, r4);
        if (r3.length() <= r0) goto L5;
        setSpansWithoutClash(r5, r3, r0, r3.length());
        setSpansWithoutClash(r6, r3, r0, r3.length());
    L5:
        return r3;
    }

    private static int getBlockAccessibilityLabel(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockHeading1) == false) goto L7;
        return R.string.ArticleHeading1;
    L7:
        if ((r1 instanceof TL_iv.pageBlockHeading2) == false) goto L11;
        return R.string.ArticleHeading2;
    L11:
        if ((r1 instanceof TL_iv.pageBlockHeading3) == false) goto L15;
        return R.string.ArticleHeading3;
    L15:
        if ((r1 instanceof TL_iv.pageBlockHeading4) == false) goto L19;
        return R.string.ArticleHeading4;
    L19:
        if ((r1 instanceof TL_iv.pageBlockHeading5) == false) goto L23;
        return R.string.ArticleHeading5;
    L23:
        if ((r1 instanceof TL_iv.pageBlockHeading6) == false) goto L27;
        return R.string.ArticleHeading6;
    L27:
        if ((r1 instanceof TL_iv.pageBlockFooter) == true) goto L29;
        return 0;
    L29:
        return R.string.ArticleFooter;
    }

    private float getBlockBackgroundScale(int r4, int r5) {
        float r0 = 1.0f;
    L4:
        if (r4 >= Math.min(r5 + 1, this.blocks.size())) goto L9;
        RichBlock r1 = this.blocks.get(r4);
        if (r1.isVisible() == false) goto L8;
        r0 = r0 * r1.getBackgroundScale();
    L8:
        r4 = r4 + 1;
        goto L4
    L9:
        return r0;
    }

    private int getBlockBottom(int r12, ChatMessageCell.TransitionParams r13) {
        int r3 = 0;
        if (r12 >= 0) goto L5;
    L20:
        boolean r4 = false;
        int r5 = 0;
    L22:
        if (r3 >= this.blocks.size()) goto L46;
        RichBlock r6 = this.blocks.get(r3);
        boolean r7 = r6.isVisible();
        if (r7 == false) goto L27;
        if (r4 == false) goto L27;
        r5 = r5 + getGap();
    L27:
        if (r7 == false) goto L40;
        if (r13 != null) goto L30;
    L35:
        int r8 = r6.getHeight();
    L34:
        r5 = r5 + r8;
        if (r3 != r12) goto L40;
        if (r6.padding.bottom <= AndroidUtilities.dp(4.0f)) goto L40;
        r5 = r5 - (r6.padding.bottom - AndroidUtilities.dp(4.0f));
        goto L40
    L30:
        if (this.detailsAnimating == false) goto L32;
    L33:
        r8 = AndroidUtilities.lerp(r6.prevH, r6.currH, Math.max(0.0f, Math.min(1.0f, r13.animateChangeProgress)));
        goto L34
    L32:
        if (this.blockquoteAnimating == false) goto L35;
    L40:
        if (r3 == r12) goto L41;
        if (r7 == false) goto L44;
        r4 = true;
    L44:
        r3 = r3 + 1;
        goto L22
    L41:
        return r5;
    L46:
        return this.height;
    L5:
        if (r12 >= this.blocks.size()) goto L20;
        if (r13 == null) goto L20;
        if (this.detailsAnimating == false) goto L10;
    L11:
        float r132 = Math.max(0.0f, Math.min(1.0f, r13.animateChangeProgress));
        RichBlock r11 = this.blocks.get(r12);
        int r122 = Math.max(0, r11.padding.bottom - AndroidUtilities.dp(4.0f));
        float r0 = r11.prevY + r11.prevH;
        if (r11.prevVisible == false) goto L14;
        int r1 = r122;
    L15:
        float r02 = r0 - r1;
        float r14 = r11.currY + r11.currH;
        if (r11.currVisible == false) goto L19;
        r3 = r122;
    L19:
        return Math.round(AndroidUtilities.lerp(r02, r14 - r3, r132));
    L14:
        r1 = 0;
        goto L15
    L10:
        if (this.blockquoteAnimating == false) goto L20;
        goto L11
    }

    private static int getBlockTextFlag(TL_iv.PageBlock r2) {
        if ((r2 instanceof TL_iv.pageBlockHeading1) == false) goto L7;
        return 1;
    L7:
        if ((r2 instanceof TL_iv.pageBlockHeading2) == false) goto L11;
        return 2;
    L11:
        if ((r2 instanceof TL_iv.pageBlockHeading3) == false) goto L15;
        return 3;
    L15:
        if ((r2 instanceof TL_iv.pageBlockHeading4) == false) goto L19;
        return 4;
    L19:
        if ((r2 instanceof TL_iv.pageBlockHeading5) == false) goto L23;
        return 5;
    L23:
        if ((r2 instanceof TL_iv.pageBlockHeading6) == false) goto L27;
        return 6;
    L27:
        if ((r2 instanceof TL_iv.pageBlockBlockquote) == false) goto L30;
        return 9;
    L30:
        if ((r2 instanceof TL_iv.pageBlockBlockquoteBlocks) == false) goto L33;
        return 9;
    L33:
        if ((r2 instanceof TL_iv.pageBlockPullquote) == false) goto L37;
        return 12;
    L37:
        if ((r2 instanceof TL_iv.pageBlockFooter) == false) goto L40;
        return 7;
    L40:
        return 0;
    }

    private int getBlockTop(int r9, ChatMessageCell.TransitionParams r10) {
        if (r9 >= 0) goto L5;
    L13:
        int r2 = 0;
        boolean r3 = false;
        int r4 = 0;
    L15:
        if (r2 >= this.blocks.size()) goto L34;
        RichBlock r5 = this.blocks.get(r2);
        boolean r6 = r5.isVisible();
        if (r6 == false) goto L20;
        if (r3 == false) goto L20;
        r4 = r4 + getGap();
    L20:
        if (r2 == r9) goto L21;
        if (r6 == false) goto L32;
        if (r10 != null) goto L25;
    L30:
        int r32 = r5.getHeight();
    L29:
        r4 = r4 + r32;
        r3 = true;
        goto L32
    L25:
        if (this.detailsAnimating == false) goto L27;
    L28:
        r32 = AndroidUtilities.lerp(r5.prevH, r5.currH, Math.max(0.0f, Math.min(1.0f, r10.animateChangeProgress)));
        goto L29
    L27:
        if (this.blockquoteAnimating == false) goto L30;
    L32:
        r2 = r2 + 1;
        goto L15
    L21:
        return r4;
    L34:
        return this.height;
    L5:
        if (r9 >= this.blocks.size()) goto L13;
        if (r10 == null) goto L13;
        if (this.detailsAnimating == false) goto L10;
    L11:
        float r102 = Math.max(0.0f, Math.min(1.0f, r10.animateChangeProgress));
        RichBlock r8 = this.blocks.get(r9);
        return Math.round(AndroidUtilities.lerp(r8.prevY, r8.currY, r102));
    L10:
        if (this.blockquoteAnimating == false) goto L13;
        goto L11
    }

    public static String getString(TL_iv.RichText r1) {
        StringBuilder r0 = new StringBuilder();
        getString(r1, r0);
        return r0.toString();
    }

    public static void getString(TL_iv.RichText r2, StringBuilder r3) {
        if ((r2 instanceof TL_iv.textPlain) == false) goto L7;
        r3.append(((TL_iv.textPlain) r2).text);
        return;
    L7:
        if ((r2 instanceof TL_iv.textDiff) == false) goto L17;
        TL_iv.textDiff r22 = (TL_iv.textDiff) r2;
        if (RichTextStyle.isEmpty(r22.text) == true) goto L13;
        getString(r22.text, r3);
        return;
    L13:
        if (RichTextStyle.isEmpty(r22.old_text) == true) goto L27;
        getString(r22.old_text, r3);
        return;
    L27:
        return;
    L17:
        if ((r2 instanceof TL_iv.textConcat) == false) goto L22;
        int r0 = 0;
    L20:
        if (r0 >= r2.texts.size()) goto L29;
        getString(r2.texts.get(r0), r3);
        r0 = r0 + 1;
        goto L20
    L29:
        return;
    L22:
        TL_iv.RichText r23 = r2.text;
        if (r23 == null) goto L28;
        getString(r23, r3);
        return;
    }

    private static TextStyleSpan.TextStyleRun getTextStyleRun(int r1) {
        TextStyleSpan.TextStyleRun r0 = new TextStyleSpan.TextStyleRun();
        r0.flags = r1;
        return r0;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private Drawable getThemedDrawable(String r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        Drawable r02 = r0.getDrawable(r1);
    L6:
        if (r02 == null) goto L9;
        return r02;
    L9:
        return Theme.getThemeDrawable(r1);
    L5:
        r02 = null;
        goto L6
    }

    private boolean handleAnchorClick(String r5) {
        if (r5 != null) goto L5;
    L23:
        return false;
    L5:
        if (r5.startsWith("#") == false) goto L23;
        String r52 = URLDecoder.decode(r5.substring(1), "UTF-8");     // Catch: Exception -> L10
    L12:
        if (TextUtils.isEmpty(r52) == false) goto L14;
        return false;
    L14:
        String r53 = r52.toLowerCase();
        TL_iv.textAnchor r0 = this.textAnchors.get(r53);
        if (r0 != null) goto L17;
        Integer r54 = this.anchors.get(r53);
        if (r54 != null) goto L21;
        return true;
    L21:
        return scrollToPageBlockAnchor(r54.intValue());
    L17:
        return showFootnoteSheet(r0);
    L10:
        r52 = r5.substring(1);
        goto L12
    }

    private boolean hasCustomIncomingQuoteColor(TLRPC.User r7, TLRPC.Chat r8) {
        MessageObject r0 = this.messageObject;
        if (r0.overrideLinkColor < 0) goto L5;
    L66:
        return true;
    L5:
        if (r0.overrideLinkPeerColor != null) goto L66;
        if (r0.isSponsored() == false) goto L14;
        TLRPC.PeerColor r02 = this.messageObject.sponsoredColor;
        if (r02 == null) goto L14;
        if (r02.color == (-1)) goto L14;
        return true;
    L14:
        MessageObject r03 = this.messageObject;
        TLRPC.Message r1 = r03.messageOwner;
        if (r1 == null) goto L35;
        TLRPC.MessageFwdHeader r12 = r1.fwd_from;
        if (r12 == null) goto L35;
        TLRPC.Peer r13 = r12.from_id;
        if (r13 == null) goto L35;
        long r72 = DialogObject.getPeerDialogId(r13);
        int r6 = this.currentAccount;
        if (r72 >= 0) goto L28;
        TLRPC.Chat r62 = MessagesController.getInstance(r6).getChat(Long.valueOf(-r72));
        if (r62 != null) goto L25;
    L27:
        return false;
    L25:
        if (hasCustomPeerColor(r62.color) == false) goto L27;
        return true;
    L28:
        TLRPC.User r63 = MessagesController.getInstance(r6).getUser(Long.valueOf(r72));
        if (r63 != null) goto L31;
    L33:
        return false;
    L31:
        if (hasCustomPeerColor(r63.color) == false) goto L33;
        return true;
    L35:
        if (DialogObject.isEncryptedDialog(r03.getDialogId()) == false) goto L37;
    L61:
        if (r7 != null) goto L63;
    L65:
        return false;
    L63:
        if (hasCustomPeerColor(r7.color) == false) goto L65;
        return true;
    L37:
        if (this.messageObject.isFromUser() == true) goto L61;
        if (this.messageObject.isFromChannel() == false) goto L60;
        if (r8 == null) goto L60;
        if (r8.signature_profiles == false) goto L59;
        long r73 = this.messageObject.getFromChatId();
        int r64 = this.currentAccount;
        if (r73 < 0) goto L52;
        TLRPC.User r65 = MessagesController.getInstance(r64).getUser(Long.valueOf(r73));
        if (r65 != null) goto L49;
    L51:
        return false;
    L49:
        if (hasCustomPeerColor(r65.color) == false) goto L51;
        return true;
    L52:
        TLRPC.Chat r66 = MessagesController.getInstance(r64).getChat(Long.valueOf(-r73));
        if (r66 != null) goto L55;
    L57:
        return false;
    L55:
        if (hasCustomPeerColor(r66.color) == false) goto L57;
        return true;
    L59:
        return hasCustomPeerColor(r8.color);
    L60:
        return false;
    }

    private static boolean hasCustomPeerColor(TLRPC.PeerColor r2) {
        if ((r2 instanceof TLRPC.TL_peerColorCollectible) == false) goto L5;
    L11:
        return true;
    L5:
        if ((r2 instanceof TLRPC.TL_peerColor) == true) goto L7;
        return false;
    L7:
        if ((r2.flags & 1) != 0) goto L11;
        return false;
    }

    private static boolean isDescendantOf(RichBlock r0, RichDetailsBlock r1) {
    L2:
        r0 = r0.parentDetails;
        if (r0 == null) goto L7;
        if (r0 != r1) goto L2;
        return true;
    L7:
        return false;
    }

    private static void markListItem(RichBlock r0, int r1, boolean r2, boolean r3, boolean r4) {
        if (r0 != null) goto L4;
        return;
    L4:
        r0.listLevel = r1;
        r0.listOrdered = r2;
        r0.listCheckbox = r3;
        r0.listChecked = r4;
    }

    private void markListMembership(int r3, int r4, int r5, boolean r6) {
    L2:
        if (r3 >= r4) goto L7;
        RichBlock r0 = this.blocks.get(r3);
        if (r0.listLevel != 0) goto L6;
        r0.listLevel = r5;
        r0.listOrdered = r6;
    L6:
        r3 = r3 + 1;
        goto L2
    }

    private static float multiHeight(float[] r2, int r3, int r4, int r5) {
        float r0 = 0.0f;
    L3:
        if (r3 >= r4) goto L6;
        r0 = r0 + r2[r3];
        r3 = r3 + 1;
        goto L3
    L6:
        return r5 / Math.max(1.0E-4f, r0);
    }

    private static String orderedListMarker(TL_iv.pageBlockOrderedList r3, TL_iv.PageListOrderedItem r4, int r5) {
        if (TextUtils.isEmpty(r4.num) == true) goto L10;
        boolean r32 = r4.num.endsWith(".");
        String r42 = r4.num;
        if (r32 == false) goto L8;
        return r42;
    L8:
        return r42 + ".";
    L10:
        if (TLObject.hasFlag(r4.flags, 8) == false) goto L14;
        return r4.value + ".";
    L14:
        if (TLObject.hasFlag(r3.flags, 1) == false) goto L21;
        StringBuilder r43 = new StringBuilder();
        int r0 = r3.start;
        if (r3.reversed == false) goto L18;
        r5 = -r5;
    L18:
        r43.append(r0 + r5);
        r43.append(".");
        return r43.toString();
    L21:
        return (r5 + 1) + ".";
    }

    private boolean prefixEquals(String r3, String r4) {
        if (r3 == null) goto L14;
        if (r4 != null) goto L7;
        return false;
    L7:
        if (r3.length() <= r4.length()) goto L10;
        return false;
    L10:
        if (r4.length() > 0) goto L13;
        return false;
    L13:
        return r4.startsWith(r3);
    L14:
        return false;
    }

    private static boolean previousBlockIsParagraph(List<TL_iv.PageBlock> r1, int r2) {
        if (r2 > 0) goto L4;
        return false;
    L4:
        if ((r1.get(r2 - 1) instanceof TL_iv.pageBlockParagraph) == false) goto L8;
        return true;
    L8:
        return false;
    }

    private ArrayList<QuoteBackground> quotesFor(int r6) {
        ArrayList<QuoteBackground> r0 = new ArrayList();
        if (r6 < 0) goto L12;
        ArrayList<QuoteBackground> r5 = this.quotes;
        int r1 = r5.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L11;
        QuoteBackground r3 = r5.get(r2);
        r2 = r2 + 1;
        QuoteBackground r32 = r3;
        if (r6 < r32.startBlockIndex) goto L5;
        if (r6 > r32.endBlockIndex) goto L5;
        r0.add(r32);
        goto L5
    L11:
        Collections.sort(r0, new RichMessageLayout$$ExternalSyntheticLambda0());
    L12:
        return r0;
    }

    private static boolean sameQuotes(ArrayList<QuoteBackground> r4, ArrayList<QuoteBackground> r5) {
        if (r4.size() == r5.size()) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= r4.size()) goto L12;
        if (r4.get(r0) != r5.get(r0)) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        return false;
    L12:
        return true;
    }

    private boolean scrollToPageBlockAnchor(int r5) {
        if (this.cell != null) goto L5;
        return false;
    L5:
        if (r5 >= 0) goto L7;
    L21:
        return false;
    L7:
        if (r5 >= this.blocks.size()) goto L21;
        ViewParent r0 = this.cell.getParent();
    L11:
        if (r0 == null) goto L16;
        if ((r0 instanceof RecyclerView) == true) goto L14;
        r0 = r0.getParent();
        goto L11
    L14:
        RecyclerView r02 = (RecyclerView) r0;
    L17:
        if (r02 != null) goto L19;
        return false;
    L19:
        r02.smoothScrollBy(0, (((this.cell.getTop() + this.cell.textY) + getBlockTop(r5, null)) - r02.getPaddingTop()) - AndroidUtilities.dp(8.0f));
        return true;
    L16:
        r02 = null;
        goto L17
    }

    public static int setBlockFlags(int r0, int r1) {
        if (r1 != 0) goto L5;
        return r0;
    L5:
        return (r0 & (-16)) | r1;
    }

    private void setBubblePaddings(int r2, int r3) {
        if (this.padLeft == r2) goto L5;
    L7:
        this.padLeft = r2;
        this.padRight = r3;
        int r22 = 0;
    L9:
        if (r22 >= this.blocks.size()) goto L18;
        RichBlock r32 = this.blocks.get(r22);
        if ((r32 instanceof RichDetailsBlock) == false) goto L13;
        RichDetailsBlock.m4840$$Nest$mupdateBubbleInsets((RichDetailsBlock) r32);
    L13:
        r22 = r22 + 1;
        goto L9
    L18:
        return;
    L5:
        if (this.padRight != r3) goto L7;
    }

    private void setSpansWithoutClash(Object r6, SpannableStringBuilder r7, int r8, int r9) {
        if ((r6 instanceof StyleSpan) == false) goto L20;
        StyleSpan r62 = (StyleSpan) r6;
        StyleSpan[] r0 = (StyleSpan[]) r7.getSpans(r8, r9, StyleSpan.class);
        if (r0 != null) goto L7;
    L18:
        setStyleRange(r7, r8, r9, r62.flags);
        return;
    L7:
        if (r0.length <= 0) goto L18;
        Arrays.sort(r0, Comparator.comparingInt(new RichMessageLayout$$ExternalSyntheticLambda1(r7)));
        int r1 = 0;
    L10:
        if (r1 >= r0.length) goto L15;
        int r2 = r7.getSpanStart(r0[r1]);
        int r3 = r7.getSpanEnd(r0[r1]);
        if (r2 <= r8) goto L14;
        setStyleRange(r7, r8, r2, r62.flags);
    L14:
        r8 = Math.max(r8, r3);
        r1 = r1 + 1;
        goto L10
    L15:
        if (r8 >= r9) goto L25;
        setStyleRange(r7, r8, r9, r62.flags);
        return;
    L25:
        return;
    L20:
        r7.setSpan(r6, r8, r9, 33);
    }

    private void setStyleRange(SpannableStringBuilder r4, int r5, int r6, int r7) {
    L2:
        if (r5 >= r6) goto L8;
        int r1 = r4.nextSpanTransition(r5, r6, URLSpan.class);
        if (((URLSpan[]) r4.getSpans(r5, r1, URLSpan.class)).length <= 0) goto L6;
        boolean r0 = true;
    L7:
        r4.setSpan(new StyleSpan(this, r7, r0), r5, r1, 33);
        r5 = r1;
        goto L2
    L6:
        r0 = false;
        goto L7
    }

    private boolean showFootnoteSheet(TL_iv.textAnchor r13) {
        View r0 = this.view;
        if (r0 != null) goto L5;
        return false;
    L5:
        Context r02 = r0.getContext();
        if (r02 != null) goto L8;
        return false;
    L8:
        TL_iv.RichText r2 = r13.text;
        if (r2 != null) goto L11;
    L23:
        return false;
    L11:
        if ((r2 instanceof TL_iv.textEmpty) == true) goto L23;
        String r22 = r13.name;
        if (r22 != null) goto L16;
        String r23 = _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        CharSequence r132 = formatText(WebInstantView.filterRecursiveAnchorLinks(r13.text, _UrlKt.FRAGMENT_ENCODE_SET, r23));
        BottomSheet.Builder r24 = new BottomSheet.Builder(r02, true, this.resourcesProvider);
        r24.setApplyTopPadding(false);
        r24.setApplyBottomPadding(false);
        LinearLayout r3 = new LinearLayout(r02);
        r3.setOrientation(1);
        TextView r5 = new TextView(r02);
        r5.setTextSize(1, 16.0f);
        r5.setTypeface(AndroidUtilities.bold());
        r5.setText(LocaleController.getString(R.string.InstantViewReference));
        if (LocaleController.isRTL == false) goto L20;
        int r7 = 5;
    L21:
        r5.setGravity(r7 | 16);
        int r72 = Theme.key_dialogTextBlack;
        r5.setTextColor(getThemedColor(r72));
        r5.setPadding(AndroidUtilities.dp(22.0f), 0, AndroidUtilities.dp(22.0f), 0);
        r3.addView(r5, new LinearLayout.LayoutParams(-1, AndroidUtilities.dp(48.0f)));
        LinkSpanDrawable.LinksTextView r52 = new LinkSpanDrawable.LinksTextView(r02, this.resourcesProvider);
        r52.setTextSize(1, SharedConfig.fontSize);
        r52.setTextColor(getThemedColor(r72));
        r52.setLinkTextColor(getThemedColor(Theme.key_dialogTextLink));
        r52.setMovementMethod(LinkMovementMethod.getInstance());
        r52.setPadding(AndroidUtilities.dp(22.0f), 0, AndroidUtilities.dp(22.0f), AndroidUtilities.dp(16.0f));
        r52.setText(r132);
        r3.addView(r52, new LinearLayout.LayoutParams(-1, -2));
        FrameLayout r12 = new FrameLayout(r02);
        r12.addView(r3, LayoutHelper.createFrame(-1, -2.0f));
        r24.setCustomView(r12);
        r24.show();
        return true;
    L20:
        r7 = 3;
        goto L21
    L16:
        r23 = r22.toLowerCase();
        goto L17
    }

    private void syncLists(StringBuilder r5, ArrayList<Boolean> r6, int r7, boolean r8) {
    L2:
        String r0 = "</ul>";
        if (r6.size() <= r7) goto L8;
        if (r6.remove(r6.size() - 1).booleanValue() == false) goto L7;
        r0 = "</ol>";
    L7:
        r5.append(r0);
    L8:
        String r2 = "<ul>";
        if (r6.size() >= r7) goto L14;
        if (r8 == false) goto L12;
        r2 = "<ol>";
    L12:
        r5.append(r2);
        r6.add(Boolean.valueOf(r8));
        goto L8
    L14:
        if (r6.isEmpty() == false) goto L16;
        return;
    L16:
        if (r6.get(r6.size() - 1).booleanValue() != r8) goto L18;
        return;
    L18:
        if (r6.remove(r6.size() - 1).booleanValue() == false) goto L20;
        r0 = "</ol>";
    L20:
        r5.append(r0);
        if (r8 == false) goto L23;
        r2 = "<ol>";
    L23:
        r5.append(r2);
        r6.add(Boolean.valueOf(r8));
    }

    private void syncQuotes(StringBuilder r3, ArrayList<QuoteBackground> r4, ArrayList<QuoteBackground> r5) {
        int r2 = 0;
    L4:
        if (r2 >= r4.size()) goto L11;
        if (r2 >= r5.size()) goto L11;
        if (r4.get(r2) != r5.get(r2)) goto L11;
        r2 = r2 + 1;
    L11:
        if (r4.size() <= r2) goto L14;
        r3.append("</blockquote>");
        r4.remove(r4.size() - 1);
    L14:
        if (r4.size() >= r5.size()) goto L16;
        r3.append("<blockquote>");
        r4.add(r5.get(r4.size()));
        goto L14
    }

    private SpannableStringBuilder toRichHtmlSpannable(CharSequence r7) {
        SpannableStringBuilder r6 = new SpannableStringBuilder(r7);
        int r1 = 0;
        StyleSpan[] r72 = (StyleSpan[]) r6.getSpans(0, r6.length(), StyleSpan.class);
        int r0 = r72.length;
    L3:
        if (r1 >= r0) goto L12;
        StyleSpan r2 = r72[r1];
        int r3 = r6.getSpanStart(r2);
        int r4 = r6.getSpanEnd(r2);
        if (r4 <= r3) goto L11;
        int r22 = toTextStyleFlags(r2.flags);
        if (r22 == 0) goto L11;
        TextStyleSpan.TextStyleRun r5 = new TextStyleSpan.TextStyleRun();
        r5.flags = r22;
        r6.setSpan(new TextStyleSpan(r5), r3, r4, 33);
    L11:
        r1 = r1 + 1;
        goto L3
    L12:
        return r6;
    }

    private static int toTextStyleFlags(int r2) {
        if ((r2 & 16) == 0) goto L5;
        int r0 = 1;
    L7:
        if ((r2 & 32) == 0) goto L10;
        r0 = r0 | 2;
    L10:
        if ((r2 & 64) == 0) goto L13;
        r0 = r0 | 16;
    L13:
        if ((r2 & 128) == 0) goto L16;
        r0 = r0 | 8;
    L16:
        if ((r2 & 256) == 0) goto L19;
        r0 = r0 | 4;
    L19:
        if ((r2 & 2048) == 0) goto L22;
        r0 = r0 | 16384;
    L22:
        if ((r2 & 4096) == 0) goto L25;
        r0 = r0 | 32768;
    L25:
        if ((r2 & 8192) != 0) goto L27;
        return r0;
    L27:
        return 65536 | r0;
    L5:
        r0 = 0;
        goto L7
    }

    private void updateTranslationLoading() {
        boolean r0 = isTranslating();
        if (r0 == true) goto L9;
        if (this.translationLoadingFloat != null) goto L9;
        this.translationLoadingValue = 0.0f;
        return;
    L9:
        if (this.translationLoadingFloat != null) goto L11;
        this.translationLoadingFloat = new AnimatedFloat(0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
    L11:
        AnimatedFloat r2 = this.translationLoadingFloat;
        if (r0 == false) goto L14;
        float r02 = 1.0f;
    L15:
        float r03 = r2.set(r02);
        this.translationLoadingValue = r03;
        if (r03 <= 0.0f) goto L21;
        View r9 = this.view;
        if (r9 == null) goto L22;
        r9.invalidate();
        return;
    L22:
        return;
    L21:
        return;
    L14:
        r02 = 0.0f;
        goto L15
    }

    public void attach(View r3) {
        View r0 = this.view;
        if (r3 == r0) goto L11;
        if (r0 == null) goto L7;
        detach(r0);
    L7:
        this.view = r3;
        int r02 = 0;
    L9:
        if (r02 >= this.blocks.size()) goto L13;
        this.blocks.get(r02).attach(r3);
        r02 = r02 + 1;
        goto L9
    L13:
        return;
    }

    public boolean canScrollHorizontallyAt(float r7) {
        int r1 = 0;
        boolean r2 = false;
        int r3 = 0;
    L4:
        if (r1 >= this.blocks.size()) goto L18;
        RichBlock r4 = this.blocks.get(r1);
        if (r4.isVisible() == false) goto L17;
        if (r2 == false) goto L10;
        r3 = r3 + getGap();
    L10:
        int r22 = r4.getHeight();
        if (r7 >= r3) goto L13;
    L16:
        r3 = r3 + r22;
        r2 = true;
        goto L17
    L13:
        if (r7 >= (r3 + r22)) goto L16;
        return r4.canDragHorizontally();
    L17:
        r1 = r1 + 1;
        goto L4
    L18:
        return false;
    }

    public void checkQuoteLine(TLRPC.User r7, TLRPC.Chat r8) {
        this.quoteLine.check(this.messageObject, r7, r8, this.resourcesProvider, 1);
        MessageObject r72 = this.messageObject;
        if (r72 != null) goto L5;
        return;
    L5:
        if (r72.isOutOwner() == false) goto L7;
        return;
    L7:
        if (this.messageObject.shouldDrawWithoutBackground() == false) goto L9;
        return;
    L9:
        if (hasCustomIncomingQuoteColor(r7, r8) == true) goto L20;
        Theme.ResourcesProvider r73 = this.resourcesProvider;
        if (r73 == null) goto L14;
        boolean r74 = r73.isDark();
    L15:
        this.quoteLine.setSimpleColor(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), r74);
        return;
    L14:
        r74 = Theme.isCurrentThemeDark();
        goto L15
    }

    public void collectMediaBlocks(List<TL_iv.PageBlock> r7) {
        int r1 = 0;
    L4:
        if (r1 >= this.blocks.size()) goto L22;
        RichBlock r2 = this.blocks.get(r1);
        if ((r2 instanceof RichPhotoBlock) == false) goto L9;
        r7.add(((RichPhotoBlock) r2).block);
    L21:
        r1 = r1 + 1;
        goto L4
    L9:
        if ((r2 instanceof RichVideoBlock) == false) goto L12;
        r7.add(((RichVideoBlock) r2).block);
        goto L21
    L12:
        if ((r2 instanceof RichCollageBlock) == false) goto L17;
        ArrayList<MediaCell> r22 = ((RichCollageBlock) r2).cells;
        int r3 = r22.size();
        int r4 = 0;
    L14:
        if (r4 >= r3) goto L21;
        MediaCell r5 = r22.get(r4);
        r4 = r4 + 1;
        r7.add(r5.pageBlock);
        goto L14
    L17:
        if ((r2 instanceof RichSlideshowBlock) == false) goto L21;
        ArrayList<MediaCell> r23 = ((RichSlideshowBlock) r2).cells;
        int r32 = r23.size();
        int r42 = 0;
    L19:
        if (r42 >= r32) goto L21;
        MediaCell r52 = r23.get(r42);
        r42 = r42 + 1;
        r7.add(r52.pageBlock);
        goto L19
    }

    public void detach(View r4) {
        View r0 = this.view;
        if (r0 != r4) goto L20;
        if (r0 == null) goto L21;
        SpoilerEffect2 r02 = this.spoilerEffect2;
        if (r02 == null) goto L10;
        r02.detach(r4);
        this.spoilerEffect2 = null;
    L10:
        this.view = null;
        int r03 = 0;
    L12:
        if (r03 >= this.blocks.size()) goto L15;
        this.blocks.get(r03).detach(r4);
        r03 = r03 + 1;
        goto L12
    L15:
        if (r4 != this.cell) goto L19;
        this.cell = null;
        this.delegate = null;
        return;
    L19:
        return;
    L21:
        return;
    }

    public void draw(Canvas r11, int r12, int r13, ChatMessageCell.TransitionParams r14) {
        setBubblePaddings(r12, r13);
        TextPaint r0 = this.textPaint;
        if (isOut() == false) goto L5;
        int r1 = Theme.key_chat_messageLinkOut;
    L6:
        r0.linkColor = getThemedColor(r1);
        boolean r02 = this.isPart;
        int r15 = Math.min(this.height, AndroidUtilities.dp(900.0f));
        if (r02 == false) goto L9;
        Canvas r3 = r11;
        r3.saveLayerAlpha(-r12, 0.0f, getMinWidth() + r13, r15, 255, 31);
    L10:
        drawInternal(r3, r14);
        if (r02 == false) goto L14;
        RectF r112 = AndroidUtilities.rectTmp;
        r112.set(-r12, r15 - AndroidUtilities.dp(32.0f), getMinWidth() + r13, r15);
        this.clip.draw(r3, r112, 3, 1.0f);
        r3.restore();
        drawShowMoreButton(r3, r15);
        return;
    L14:
        return;
    L9:
        r3 = r11;
        goto L10
    L5:
        r1 = Theme.key_chat_messageLinkIn;
        goto L6
    }

    public void draw(Canvas r7, int r8, int r9, ChatMessageCell.TransitionParams r10, float r11, float r12) {
        setBubblePaddings(r8, r9);
        TextPaint r82 = this.textPaint;
        if (isOut() == false) goto L5;
        int r92 = Theme.key_chat_messageLinkOut;
    L6:
        r82.linkColor = getThemedColor(r92);
        if (r12 <= r11) goto L10;
        boolean r83 = true;
    L11:
        drawInternal(r7, r10, r83, r11, r12);
        return;
    L10:
        r83 = false;
        goto L11
    L5:
        r92 = Theme.key_chat_messageLinkIn;
        goto L6
    }

    public boolean drawOverlay(Canvas r2) {
        return drawOverlay(r2, null);
    }

    public boolean drawOverlay(Canvas r6, ColorFilter r7) {
        int r1 = 0;
        if (isOverlayActive() == true) goto L5;
        return false;
    L5:
        boolean r0 = false;
    L7:
        if (r1 >= this.blocks.size()) goto L16;
        RichBlock r2 = this.blocks.get(r1);
        if (r2.currVisible == false) goto L15;
        r6.save();
        r6.translate(0.0f, r2.currY);
        if (r2.drawOverlay(r6, r7) == false) goto L14;
        r0 = true;
    L14:
        r6.restore();
    L15:
        r1 = r1 + 1;
        goto L7
    L16:
        return r0;
    }

    public FoundLink findLink(CharacterStyle r8) {
        if (r8 != null) goto L5;
        return null;
    L5:
        FoundLink r1 = new FoundLink();
        int r2 = 0;
        boolean r3 = false;
        int r4 = 0;
    L7:
        if (r2 >= this.blocks.size()) goto L18;
        RichBlock r5 = this.blocks.get(r2);
        if (r5.isVisible() == false) goto L17;
        if (r3 == false) goto L14;
        r4 = r4 + getGap();
    L14:
        if (r5.findLink(r8, r4, r1) == true) goto L15;
        r4 = r4 + r5.getHeight();
        r3 = true;
        goto L17
    L15:
        return r1;
    L17:
        r2 = r2 + 1;
        goto L7
    L18:
        return null;
    }

    public ImageReceiver findMediaImageReceiver(TL_iv.PageBlock r12, int[] r13) {
        int r1 = 0;
    L3:
        TL_iv.PageBlock r3 = null;
        if (r1 >= this.blocks.size()) goto L47;
        RichBlock r2 = this.blocks.get(r1);
        if ((r2 instanceof RichPhotoBlock) == false) goto L10;
        RichPhotoBlock r32 = (RichPhotoBlock) r2;
        TL_iv.PageBlock r4 = r32.block;
        ImageReceiver r33 = r32.imageReceiver;
    L8:
        TL_iv.PageBlock r10 = r4;
        ImageReceiver r42 = r33;
        r3 = r10;
    L39:
        if (r3 != r12) goto L46;
        if (r42 == null) goto L46;
        if (r13 != null) goto L43;
    L45:
        return r42;
    L43:
        if (r13.length < 2) goto L45;
        Rect r11 = r2.padding;
        r13[0] = r11.left;
        r13[1] = r2.layoutY + r11.top;
    L46:
        r1 = r1 + 1;
        goto L3
    L10:
        if ((r2 instanceof RichVideoBlock) == false) goto L13;
        RichVideoBlock r34 = (RichVideoBlock) r2;
        r4 = r34.block;
        r33 = r34.imageReceiver;
        goto L8
    L13:
        if ((r2 instanceof RichCollageBlock) == false) goto L25;
        ArrayList<MediaCell> r35 = ((RichCollageBlock) r2).cells;
        int r43 = r35.size();
        int r7 = 0;
    L15:
        if (r7 >= r43) goto L46;
        MediaCell r8 = r35.get(r7);
        r7 = r7 + 1;
        MediaCell r82 = r8;
        if (r82.pageBlock != r12) goto L15;
        if (r13 == null) goto L23;
        if (r13.length < 2) goto L23;
        Rect r112 = r2.padding;
        r13[0] = r112.left;
        r13[1] = r2.layoutY + r112.top;
    L23:
        return r82.imageReceiver;
    L25:
        if ((r2 instanceof RichSlideshowBlock) == false) goto L38;
        RichSlideshowBlock r36 = (RichSlideshowBlock) r2;
        int r44 = r36.getCurrentPage();
        if (r44 < 0) goto L46;
        if (r44 >= r36.cells.size()) goto L46;
        if (r36.cells.get(r44).pageBlock != r12) goto L46;
        if (r13 == null) goto L37;
        if (r13.length < 2) goto L37;
        Rect r113 = r2.padding;
        r13[0] = r113.left;
        r13[1] = r2.layoutY + r113.top;
    L37:
        return r36.cells.get(r44).imageReceiver;
    L38:
        r42 = null;
        goto L39
    L47:
        return null;
    }

    public boolean forceNewLineForTime() {
        if (this.blocks.isEmpty() == false) goto L5;
    L18:
        return true;
    L5:
        if (this.isPart == true) goto L18;
        if (isRtl() == true) goto L18;
        if (this.quotes.isEmpty() == true) goto L16;
        ArrayList<QuoteBackground> r0 = this.quotes;
        int r2 = r0.size();
        int r3 = 0;
    L12:
        if (r3 >= r2) goto L16;
        QuoteBackground r4 = r0.get(r3);
        r3 = r3 + 1;
        if (r4.endBlockIndex < (this.blocks.size() - 1)) goto L12;
        return true;
    L16:
        ArrayList<RichBlock> r6 = this.blocks;
        return r6.get(r6.size() - 1).forcesTimeToNewLine();
    }

    public CharSequence formatText(TL_iv.RichText r3) {
        return formatText(r3, new SpannableStringBuilder(), 0);
    }

    public CharSequence formatText(TL_iv.RichText r3, int r4) {
        if (r4 != 0) goto L6;
        return formatText(r3, new SpannableStringBuilder(), 0);
    L6:
        return formatTextAndSetSpan(r3, new SpannableStringBuilder(), r4, new StyleSpan(this, r4));
    }

    public CharSequence formatText(TL_iv.RichText r13, SpannableStringBuilder r14, int r15) {
        if ((r13 instanceof TL_iv.textEmpty) == false) goto L6;
        return r14;
    L6:
        if ((r13 instanceof TL_iv.textPlain) == false) goto L10;
        r14.append(((TL_iv.textPlain) r13).text);
        return r14;
    L10:
        if ((r13 instanceof TL_iv.textDiff) == false) goto L25;
        TL_iv.textDiff r132 = (TL_iv.textDiff) r13;
        boolean r0 = RichTextStyle.isEmpty(r132.text);
        boolean r3 = RichTextStyle.isEmpty(r132.old_text);
        if (r0 == false) goto L16;
        if (r3 == true) goto L16;
        formatTextAndSetSpan(r132.old_text, r14, r15, new TextStyleSpan(getTextStyleRun(8192)));
        return r14;
    L16:
        if (r0 == true) goto L20;
        if (r3 == false) goto L20;
        formatTextAndSetSpan(r132.text, r14, r15, new TextStyleSpan(getTextStyleRun(4096)));
        return r14;
    L20:
        if (r0 == true) goto L186;
        formatTextAndSetSpan(r132.text, r14, r15, new SquigglyLinesSpan());
        return r14;
    L186:
        return r14;
    L25:
        if ((r13 instanceof TL_iv.textBold) == false) goto L29;
        int r152 = r15 | 16;
        formatTextAndSetSpan(r13.text, r14, r152, new StyleSpan(this, r152));
        return r14;
    L29:
        if ((r13 instanceof TL_iv.textItalic) == false) goto L33;
        int r153 = r15 | 32;
        formatTextAndSetSpan(r13.text, r14, r153, new StyleSpan(this, r153));
        return r14;
    L33:
        if ((r13 instanceof TL_iv.textUnderline) == false) goto L37;
        int r154 = r15 | 64;
        formatTextAndSetSpan(r13.text, r14, r154, new StyleSpan(this, r154));
        return r14;
    L37:
        if ((r13 instanceof TL_iv.textStrike) == false) goto L41;
        int r155 = r15 | 128;
        formatTextAndSetSpan(r13.text, r14, r155, new StyleSpan(this, r155));
        return r14;
    L41:
        if ((r13 instanceof TL_iv.textFixed) == false) goto L45;
        int r156 = r15 | 256;
        formatTextAndSetSpan(r13.text, r14, r156, new StyleSpan(this, r156));
        return r14;
    L45:
        if ((r13 instanceof TL_iv.textUrl) == false) goto L49;
        formatTextAndSetSpan(r13.text, r14, r15, new URLSpanReplacement(((TL_iv.textUrl) r13).url, getTextStyleRun(1024)));
        return r14;
    L49:
        if ((r13 instanceof TL_iv.textEmail) == false) goto L53;
        TL_iv.textEmail r02 = (TL_iv.textEmail) r13;
        formatTextAndSetSpan(r13.text, r14, r15, new URLSpanReplacement("mailto:" + r02.email, getTextStyleRun(1024)));
        return r14;
    L53:
        if ((r13 instanceof TL_iv.textConcat) == false) goto L59;
        int r03 = 0;
    L56:
        if (r03 >= r13.texts.size()) goto L186;
        formatText(r13.texts.get(r03), r14, r15);
        r03 = r03 + 1;
        goto L56
    L59:
        if ((r13 instanceof TL_iv.textSubscript) == false) goto L63;
        int r157 = r15 | 2048;
        formatTextAndSetSpan(r13.text, r14, r157, new StyleSpan(this, r157));
        return r14;
    L63:
        if ((r13 instanceof TL_iv.textSuperscript) == false) goto L67;
        int r158 = r15 | 4096;
        formatTextAndSetSpan(r13.text, r14, r158, new StyleSpan(this, r158));
        return r14;
    L67:
        if ((r13 instanceof TL_iv.textMarked) == false) goto L71;
        int r159 = r15 | 8192;
        formatTextAndSetSpan(r13.text, r14, r159, new StyleSpan(this, r159));
        return r14;
    L71:
        if ((r13 instanceof TL_iv.textPhone) == false) goto L77;
        TL_iv.textPhone r04 = (TL_iv.textPhone) r13;
        String r32 = PhoneFormat.stripExceptNumbers(r04.phone);
        if (r04.phone.startsWith("+") == false) goto L75;
        r32 = "+" + r32;
    L75:
        formatTextAndSetSpan(r13.text, r14, r15, new URLSpanReplacement("tel:" + r32, getTextStyleRun(1024)));
        return r14;
    L77:
        boolean r05 = r13 instanceof TL_iv.textAnchor;
        String r6 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r05 == false) goto L93;
        TL_iv.textAnchor r06 = (TL_iv.textAnchor) r13;
        String r1 = r06.name;
        if (r1 == null) goto L87;
        String r12 = r1.toLowerCase();
        if ((r06.text instanceof TL_iv.textEmpty) == true) goto L85;
        this.textAnchors.put(r12, r06);
        goto L87
    L85:
        if (this.anchors.containsKey(r12) == true) goto L87;
        this.anchors.put(r12, Integer.valueOf(this.blocks.size()));
    L87:
        TL_iv.RichText r133 = r13.text;
        String r07 = r06.name;
        if (r07 == null) goto L91;
        r6 = r07.toLowerCase();
    L91:
        formatTextAndSetSpan(r133, r14, r15, new AnchorSpan(r6));
        return r14;
    L93:
        int r7 = 4;
        if ((r13 instanceof TL_iv.textMath) == false) goto L114;
        TL_iv.textMath r134 = (TL_iv.textMath) r13;
        if (r134.bitmap != null) goto L103;
        if (r134.tried == true) goto L103;
        r134.tried = true;
        Latex r1510 = Latex.render(r134.source, AndroidUtilities.dp(this.fontSize + 4), true);
        if (r1510 == null) goto L103;
        r134.w = r1510.width;
        r134.h = r1510.height;
        r134.depth = r1510.depth;
        r134.bitmap = r1510.bitmap;
    L103:
        if (r134.bitmap != null) goto L108;
        String r122 = r134.source;
        if (r122 != null) goto L107;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L107:
        return r122;
    L108:
        int r1511 = r14.length();
        r14.append(" ");
        int r08 = r14.length();
        r14.setSpan(new TextPaintImageReceiverSpan(null, r134.bitmap, r134.w, r134.h, Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider), r134.depth), r1511, r08, 33);
        String r123 = r134.source;
        if (r123 == null) goto L186;
        if (r123.isEmpty() == true) goto L186;
        r14.setSpan(new TextSelectionHelper.ReplaceCopyTextSpannable(r134.source), r1511, r08, 33);
        return r14;
    L114:
        float r11 = 1.2f;
        if ((r13 instanceof TL_iv.textCustomEmoji) == false) goto L135;
        TL_iv.textCustomEmoji r135 = (TL_iv.textCustomEmoji) r13;
        if (TextUtils.isEmpty(r135.alt) == false) goto L119;
        CharSequence r09 = "😀";
    L120:
        int r16 = r14.length();
        r14.append(r09);
        int r010 = r14.length();
        boolean r2 = BitwiseUtils.hasFlag(r15, 13);
        int r33 = r15 & 15;
        if (r33 >= 1) goto L123;
    L125:
        long r34 = r135.document_id;
        if (r2 == false) goto L128;
        r11 = 1.0f;
    L128:
        AnimatedEmojiSpan r1512 = new AnimatedEmojiSpan(r34, r11, null);
        int r124 = this.fontSize + 4;
        if (r2 == false) goto L131;
        r7 = -2;
    L131:
        Object r125 = r1512.setSize(AndroidUtilities.dp(r124 + r7));
    L132:
        r14.setSpan(r125, r16, r010, 33);
        return r14;
    L123:
        if (r33 > 6) goto L125;
        TextPaint r22 = new TextPaint(this.textPaint);
        new StyleSpan(this, r15, true).applyStyle(r22);
        r125 = new AnimatedEmojiSpan(r135.document_id, 0.85f, r22.getFontMetricsInt());
        goto L132
    L119:
        r09 = r135.alt;
        goto L120
    L135:
        if ((r13 instanceof TL_iv.textSpoiler) == false) goto L139;
        formatTextAndSetSpan(r13.text, r14, r15, new TextStyleSpan(getTextStyleRun(256)));
        return r14;
    L139:
        if ((r13 instanceof TL_iv.textMention) == false) goto L143;
        TLRPC.TL_messageEntityMention r011 = new TLRPC.TL_messageEntityMention();
        TextStyleSpan.TextStyleRun r17 = new TextStyleSpan.TextStyleRun();
        r17.urlEntity = r011;
        formatTextAndSetSpan(r13.text, r14, r15, new URLSpanNoUnderline(getString(r13), r17));
        return r14;
    L143:
        if ((r13 instanceof TL_iv.textHashtag) == false) goto L147;
        TLRPC.TL_messageEntityHashtag r012 = new TLRPC.TL_messageEntityHashtag();
        TextStyleSpan.TextStyleRun r18 = new TextStyleSpan.TextStyleRun();
        r18.urlEntity = r012;
        formatTextAndSetSpan(r13.text, r14, r15, new URLSpanNoUnderline(getString(r13), r18));
        return r14;
    L147:
        if ((r13 instanceof TL_iv.textBotCommand) == false) goto L151;
        formatTextAndSetSpan(r13.text, r14, r15, new URLSpanBotCommand(getString(r13), isOut() ? 1 : 0));
        return r14;
    L151:
        if ((r13 instanceof TL_iv.textCashtag) == false) goto L155;
        TLRPC.TL_messageEntityCashtag r013 = new TLRPC.TL_messageEntityCashtag();
        TextStyleSpan.TextStyleRun r19 = new TextStyleSpan.TextStyleRun();
        r19.urlEntity = r013;
        formatTextAndSetSpan(r13.text, r14, r15, new URLSpanNoUnderline(getString(r13), r19));
        return r14;
    L155:
        if ((r13 instanceof TL_iv.textAutoUrl) == false) goto L159;
        formatTextAndSetSpan(r13.text, r14, r15, new URLSpanReplacement(getString(r13), getTextStyleRun(1024)));
        return r14;
    L159:
        if ((r13 instanceof TL_iv.textAutoEmail) == false) goto L163;
        String r014 = getString(r13);
        formatTextAndSetSpan(r13.text, r14, r15, new URLSpanReplacement("mailto:" + r014, getTextStyleRun(1024)));
        return r14;
    L163:
        if ((r13 instanceof TL_iv.textAutoPhone) == false) goto L170;
        String r015 = getString(r13);
        String r35 = PhoneFormat.stripExceptNumbers(r015);
        if (r015.startsWith("+") == false) goto L167;
        r35 = "+" + r35;
    L167:
        formatTextAndSetSpan(r13.text, r14, r15, new URLSpanReplacement("tel:" + r35, getTextStyleRun(1024)));
        return r14;
    L170:
        if ((r13 instanceof TL_iv.textBankCard) == false) goto L174;
        String r016 = getString(r13);
        formatTextAndSetSpan(r13.text, r14, r15, new URLSpanNoUnderline("card:" + r016));
        return r14;
    L174:
        if ((r13 instanceof TL_iv.textMentionName) == false) goto L178;
        TL_iv.textMentionName r017 = (TL_iv.textMentionName) r13;
        formatTextAndSetSpan(r13.text, r14, r15, new URLSpanUserMention(_UrlKt.FRAGMENT_ENCODE_SET + r017.user_id, isOut() ? 1 : 0));
        return r14;
    L178:
        if ((r13 instanceof TL_iv.textDate) == false) goto L182;
        TL_iv.textDate r018 = (TL_iv.textDate) r13;
        TLRPC.TL_messageEntityFormattedDate r110 = new TLRPC.TL_messageEntityFormattedDate();
        r110.relative = r018.relative;
        r110.short_time = r018.short_time;
        r110.long_time = r018.long_time;
        r110.short_date = r018.short_date;
        r110.long_date = r018.long_date;
        r110.day_of_week = r018.day_of_week;
        r110.date = r018.date;
        int r5 = r15 | 512;
        formatTextAndSetSpan(r13.text, r14, r5, new StyleSpan(this, r5), new FormattedDateSpan(getString(r13), null, r110));
        return r14;
    L182:
        if ((r13 instanceof TL_iv.textButton) == false) goto L187;
        int r126 = r14.length();
        r14.append("*");
        RichButtonSpan r142 = new RichButtonSpan(this, this.maxWidth, (TL_iv.textButton) r13);
        RichButtonSpan.m4839$$Nest$fputscale(r142, 1.2f);
        r14.setSpan(r142, r126, r14.length(), 33);
        return r14;
    L187:
        return r14;
    }

    public List<MultiLayoutTypingAnimator.Block> getAnimatorBlocks() {
        ArrayList r0 = new ArrayList(this.blocks.size());
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r2 >= this.blocks.size()) goto L7;
        this.blocks.get(r2).collectAnimatorBlocks(r0);
        r2 = r2 + 1;
    L7:
        if (r1 >= r0.size()) goto L9;
        ((MultiLayoutTypingAnimator.Block) r0.get(r1)).getClass();
        r1 = r1 + 1;
        goto L7
    L9:
        return r0;
    }

    public ChatMessageCell getCell() {
        return this.cell;
    }

    public ChatMessageCell.ChatMessageCellDelegate getDelegate() {
        return this.delegate;
    }

    public TLRPC.Document getDocument(long r7) {
        TL_iv.RichMessage r6 = this.richMessage;
        if (r6 != null) goto L5;
        return null;
    L5:
        ArrayList<TLRPC.Document> r62 = r6.documents;
        int r1 = r62.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L10;
        TLRPC.Document r3 = r62.get(r2);
        r2 = r2 + 1;
        TLRPC.Document r32 = r3;
        if (r32.id != r7) goto L6;
        return r32;
    L10:
        return null;
    }

    public int getGap() {
        return 0;
    }

    public int getHeight() {
        if (this.isPart == true) goto L5;
    L8:
        int r0 = this.height;
        if (this.isPart == false) goto L11;
        int r2 = AndroidUtilities.dp(50.0f);
    L13:
        return r0 + r2;
    L11:
        r2 = 0;
        goto L13
    L5:
        if (this.height <= AndroidUtilities.dp(900.0f)) goto L8;
        return AndroidUtilities.dp(950.0f);
    }

    public int getLastLineWidth() {
        if (this.blocks.isEmpty() == true) goto L24;
        if (this.isPart == true) goto L24;
        if (isRtl() == true) goto L24;
        if (this.quotes.isEmpty() == true) goto L17;
        ArrayList<QuoteBackground> r0 = this.quotes;
        int r1 = r0.size();
        int r2 = 0;
    L12:
        if (r2 >= r1) goto L17;
        QuoteBackground r3 = r0.get(r2);
        r2 = r2 + 1;
        if (r3.endBlockIndex < (this.blocks.size() - 1)) goto L12;
        return getMinWidth();
    L17:
        RichBlock r02 = this.blocks.get(r0.size() - 1);
        if (r02.forcesTimeToNewLine() == false) goto L22;
        return getMinWidth();
    L22:
        return r02.getLastLineWidth();
    L24:
        return getMinWidth();
    }

    public SpoilerEffect2 getMediaSpoilerEffect() {
        if (this.view != null) goto L5;
    L17:
        return null;
    L5:
        if (SpoilerEffect2.supports() == false) goto L17;
        SpoilerEffect2 r0 = this.spoilerEffect2;
        if (r0 == null) goto L13;
        if (r0.destroyed == false) goto L13;
        this.spoilerEffect2 = null;
    L13:
        if (this.spoilerEffect2 != null) goto L16;
        this.spoilerEffect2 = SpoilerEffect2.getInstance(this.view);
    L16:
        return this.spoilerEffect2;
    }

    public int getMinWidth() {
        return this.minWidth;
    }

    public TLRPC.Photo getPhoto(long r7) {
        TL_iv.RichMessage r6 = this.richMessage;
        if (r6 != null) goto L5;
        return null;
    L5:
        ArrayList<TLRPC.Photo> r62 = r6.photos;
        int r1 = r62.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L10;
        TLRPC.Photo r3 = r62.get(r2);
        r2 = r2 + 1;
        TLRPC.Photo r32 = r3;
        if (r32.id != r7) goto L6;
        return r32;
    L10:
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [org.telegram.messenger.RichMessageLayout$RichBlock] */
    public String getSelectionHtml(int r18, int r19) {
        RichMessageLayout r0 = this;
        String r6 = null;
        if (r0.textBlocks.isEmpty() == false) goto L5;
        return null;
    L5:
        int r7 = Math.min(r18, r19);
        int r8 = Math.max(r18, r19);
        if (r8 > r7) goto L8;
        return null;
    L8:
        StringBuilder r1 = new StringBuilder();
        ArrayList<QuoteBackground> r9 = new ArrayList();
        ArrayList<Boolean> r10 = new ArrayList();
        int r13 = 0;
        int r14 = -1;
    L10:
        if (r13 >= r0.textBlocks.size()) goto L75;
        Layout r2 = r0.textBlocks.get(r13).getLayout();
        if (r2 != null) goto L14;
    L15:
        String r16 = r6;
    L74:
        r13 = r13 + 1;
        r6 = r16;
        goto L10
    L14:
        if (r2.getText() == null) goto L15;
        CharSequence r22 = r2.getText();
        if (r13 >= r0.textBlockCharOffsets.size()) goto L19;
        int r4 = r0.textBlockCharOffsets.get(r13).intValue();
    L20:
        int r5 = r22.length() + r4;
        int r15 = Math.max(r7, r4);
        int r52 = Math.min(r8, r5);
        if (r52 <= r15) goto L15;
        if (r13 >= r0.textBlockBlockIndex.size()) goto L26;
        int r3 = r0.textBlockBlockIndex.get(r13).intValue();
    L27:
        r16 = r6;
        if (r3 >= 0) goto L30;
    L32:
        RichTextBlock r62 = r16;
    L34:
        if ((r62 instanceof RichTableBlock) == false) goto L38;
        if (r3 == r14) goto L74;
        r0.closeLists(r1, r10);
        r0.syncQuotes(r1, r9, r0.quotesFor(r3));
        r1.append(RichHtml.tableToHtml(((RichTableBlock) r62).pageBlock));
        r14 = r3;
        goto L74
    L38:
        ArrayList<QuoteBackground> r32 = r0.quotesFor(r3);
        if (sameQuotes(r9, r32) == true) goto L41;
        r0.closeLists(r1, r10);
        r0.syncQuotes(r1, r9, r32);
    L41:
        int r33 = r15 - r4;
        int r42 = r52 - r4;
        if ((r62 instanceof RichTextBlock) == false) goto L44;
        int r11 = r62.quoteAuthorStart;
    L46:
        if ((r62 instanceof RichTextWithAuthorBlock) == true) goto L48;
    L50:
        boolean r53 = false;
    L51:
        if (r62 == 0) goto L53;
        int r152 = r62.listLevel;
    L54:
        if (r152 <= 0) goto L62;
        if (r11 >= 0) goto L62;
        r0.syncLists(r1, r10, r152, r62.listOrdered);
        r1.append("<li");
        if (r62.listCheckbox == false) goto L61;
        r1.append(" data-checkbox=\"1\"");
        if (r62.listChecked == false) goto L61;
        r1.append(" data-checked=\"1\"");
    L61:
        r1.append(Typography.greater);
        r1.append(RichHtml.inlineToHtml(r0.toRichHtmlSpannable(r22.subSequence(r33, r42))));
        r1.append("</li>");
    L62:
        r0.closeLists(r1, r10);
        if ((r62 instanceof RichPreformattedBlock) == false) goto L65;
        r1.append(RichHtml.preToHtml(r0.toRichHtmlSpannable(r22.subSequence(r33, r42)), ((RichPreformattedBlock) r62).language));
        goto L74
    L65:
        if (r53 == false) goto L67;
        r0.appendSelectionPiece(r1, r22, r33, r42, true);
        r0 = this;
        goto L74
    L67:
        if (r11 >= 0) goto L70;
        r0 = this;
        r0.appendSelectionPiece(r1, r22, r33, r42, false);
        goto L74
    L70:
        if (r11 <= 0) goto L72;
        int r02 = r11 - 1;
    L73:
        int r43 = Math.min(r42, r02);
        r0 = this;
        r0.appendSelectionPiece(r1, r22, r33, r43, false);
        r0.appendSelectionPiece(r1, r22, Math.max(r33, r11), r42, true);
        goto L74
    L72:
        r02 = 0;
        goto L73
    L53:
        r152 = 0;
        goto L54
    L48:
        if (r62.author != r0.textBlocks.get(r13)) goto L50;
        r53 = true;
        goto L51
    L44:
        r11 = -1;
        goto L46
    L30:
        if (r3 >= r0.blocks.size()) goto L32;
        r62 = r0.blocks.get(r3);
        goto L34
    L26:
        r3 = -1;
        goto L27
    L19:
        r4 = 0;
        goto L20
    L75:
        String r162 = r6;
        r0.closeLists(r1, r10);
    L77:
        if (r9.isEmpty() == true) goto L80;
        r1.append("</blockquote>");
        r9.remove(r9.size() - 1);
        goto L77
    L80:
        if (r1.length() != 0) goto L83;
        return r162;
    L83:
        return r1.toString();
    }

    public ArrayList<RichUnsupportedBlock> getUnsupportedHoles() {
        return this.unsupportedBlocks;
    }

    public ArrayList<RichUnsupportedBlock> getUnsupportedHolesRoot() {
        return this.unsupportedBlocksRoot;
    }

    public boolean hasNameOffset() {
        ChatMessageCell r0 = this.cell;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.namesOffset <= 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasOverlay() {
        if (isOverlayActive() == true) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= this.textBlocks.size()) goto L17;
        TextSelectionHelper.TextLayoutBlock r2 = this.textBlocks.get(r0);
        if ((r2 instanceof Text) == false) goto L16;
        AnimatedEmojiSpan.EmojiGroupedSpans r22 = ((Text) r2).animatedEmojiStack;
        if (r22 == null) goto L16;
        if (r22.holders.isEmpty() == true) goto L16;
        return true;
    L16:
        r0 = r0 + 1;
        goto L7
    L17:
        return false;
    }

    public boolean hasRootUnsupportedBlocks() {
        return !this.unsupportedBlocksRoot.isEmpty();
    }

    public boolean hasUnsupportedBlocks() {
        return !this.unsupportedBlocks.isEmpty();
    }

    public boolean isAttached() {
        if (this.view == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isHorizontallyDragging() {
        RichBlock r0 = this.pressedBlock;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isHorizontallyDragging() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isOut() {
        MessageObject r0 = this.messageObject;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isOutOwner() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isOverlayActive() {
        MultiLayoutTypingAnimator r0 = this.typingAnimator;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.isRunning() == false) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isPinnedTop() {
        ChatMessageCell r0 = this.cell;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isPinnedTop() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isPressingLink() {
        RichBlock r0 = this.pressedBlock;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isPressingLink() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isRtl() {
        TL_iv.RichMessage r0 = this.richMessage;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.rtl == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isTranslating() {
        if (this.forceTranslationLoading == false) goto L5;
        return true;
    L5:
        if (this.messageObject != null) goto L7;
        return false;
    L7:
        if (MessagesController.getInstance(this.currentAccount).getTranslateController().isTranslating(this.messageObject) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public void layout(RichMessageLayout r11) {
        int r0 = 0;
        this.height = 0;
        this.minWidth = 0;
        View r1 = this.view;
        if (r1 == null) goto L8;
        int r2 = 0;
    L6:
        if (r2 >= this.blocks.size()) goto L8;
        this.blocks.get(r2).detach(r1);
        r2 = r2 + 1;
    L8:
        this.unsupportedBlocks.clear();
        this.unsupportedBlocksRoot.clear();
        this.blocks.clear();
        this.quotes.clear();
        this.anchors.clear();
        this.textAnchors.clear();
        this.audioMessages.clear();
        this.audioBlocks.clear();
        this.textBlocks.clear();
        this.textBlockCharOffsets.clear();
        this.textBlockBlockIndex.clear();
        this.joinedText = _UrlKt.FRAGMENT_ENCODE_SET;
        this.fontSize = SharedConfig.fontSize;
        this.density = AndroidUtilities.density;
        this.textPaint.setTextSize(AndroidUtilities.dp(SharedConfig.fontSize));
        this.numTextPaint.setTextSize(AndroidUtilities.dp(SharedConfig.fontSize));
        this.isPart = false;
        this.richMessage = null;
        MessageObject r3 = this.messageObject;
        if (r3 != null) goto L11;
        return;
    L11:
        if (r3.messageOwner != null) goto L13;
        return;
    L13:
        if (r3.getDisplayRichMessage() == null) goto L57;
        TL_iv.RichMessage r32 = this.messageObject.getDisplayRichMessage();
        this.richMessage = r32;
        this.isPart = r32.part;
        this.prev = r11;
        int r112 = 0;
    L17:
        if (r112 >= this.richMessage.blocks.size()) goto L35;
        TL_iv.PageBlock r5 = this.richMessage.blocks.get(r112);
        RichMessageLayout r4 = this;
        RichBlock r10 = r4.emitBlock(r5, 0, new Rect(), 0, previousBlockIsParagraph(this.richMessage.blocks, r112));
        if ((r10 instanceof RichTextBlock) == false) goto L34;
        boolean r33 = r5 instanceof TL_iv.pageBlockParagraph;
        if (r33 == false) goto L23;
    L24:
        RichTextBlock r102 = (RichTextBlock) r10;
        if (r112 != 0) goto L28;
        if (r33 == false) goto L28;
        int r34 = 0;
    L30:
        if (r112 != (r4.richMessage.blocks.size() - 1)) goto L32;
        int r52 = 0;
    L33:
        r102.setContentPadding(r34, r52);
        goto L34
    L32:
        r52 = r102.contentPaddingBottom;
    L28:
        r34 = r102.contentPaddingTop;
        goto L30
    L23:
        if (ArticleViewer.isHeadingBlock(r5) == true) goto L24;
    L34:
        r112 = r112 + 1;
        RichMessageLayout this = r4;
        goto L17
    L35:
        RichMessageLayout r42 = this;
        r42.applyListPaddingFromBlocks();
        r42.prev = null;
        if (r42.typingAnimator == null) goto L42;
        int r103 = 0;
    L39:
        if (r103 >= r42.blocks.size()) goto L41;
        r42.blocks.get(r103).typingAnimator = r42.typingAnimator;
        r103 = r103 + 1;
        goto L39
    L41:
        r42.typingAnimator.setBlocks(r42.getAnimatorBlocks());
    L42:
        if (r1 != null) goto L44;
    L46:
        r42.reposition();
        r42.snapshotForDetailsAnimation();
        return;
    L44:
        if (r0 >= r42.blocks.size()) goto L46;
        r42.blocks.get(r0).attach(r1);
        r0 = r0 + 1;
        goto L44
    }

    public boolean needsUpdate(TL_iv.RichMessage r2, int r3) {
        if (this.richMessage == r2) goto L5;
        return true;
    L5:
        if (this.fontSize == SharedConfig.fontSize) goto L7;
        return true;
    L7:
        if (Math.abs(this.density - AndroidUtilities.density) <= 0.1f) goto L9;
        return true;
    L9:
        if (r3 != this.maxWidth) goto L17;
        return false;
    L17:
        return true;
    }

    public boolean onTouchEvent(MotionEvent r11) {
        int r0 = r11.getActionMasked();
        if (r0 != 0) goto L10;
        if (r11.getX() >= this.padLeft) goto L7;
    L8:
        return false;
    L7:
        if (r11.getX() > (getMinWidth() + this.padRight)) goto L8;
    L10:
        if (this.isPart == false) goto L52;
        float r2 = r11.getX();
        float r5 = r11.getY();
        if (r0 != 0) goto L20;
        if (this.showMoreRect.contains(r2, r5) == false) goto L52;
        this.showMorePressed = true;
        ButtonBounce r10 = this.showMoreBounce;
        if (r10 == null) goto L18;
        r10.setPressed(true);
    L18:
        return true;
    L20:
        if (this.showMorePressed == false) goto L52;
        if (r0 == 2) goto L24;
        if (r0 == 1) goto L32;
        if (r0 != 3) goto L52;
    L32:
        ButtonBounce r112 = this.showMoreBounce;
        if (r112 == null) goto L35;
        r112.setPressed(false);
    L35:
        if (r0 == 1) goto L37;
    L39:
        boolean r113 = false;
    L40:
        this.showMorePressed = false;
        if (r113 == true) goto L43;
    L50:
        return true;
    L43:
        if (this.delegate == null) goto L50;
        if (this.cell == null) goto L50;
        View r114 = this.view;
        if (r114 == null) goto L49;
        r114.performHapticFeedback(3, 2);
    L49:
        this.delegate.didPressShowMore(this.cell);
        goto L50
    L37:
        if (this.showMoreRect.contains(r2, r5) == false) goto L39;
        r113 = true;
        goto L40
    L24:
        if (this.showMoreRect.contains(r2, r5) == true) goto L29;
        ButtonBounce r115 = this.showMoreBounce;
        if (r115 == null) goto L28;
        r115.setPressed(false);
    L28:
        this.showMorePressed = false;
    L29:
        return true;
    L52:
        if (r0 != 0) goto L73;
        this.pressedBlock = null;
        float r02 = r11.getY();
        int r22 = 0;
        boolean r3 = false;
        int r6 = 0;
    L55:
        if (r22 >= this.blocks.size()) goto L71;
        RichBlock r7 = this.blocks.get(r22);
        if (r7.isVisible() == false) goto L70;
        if (r3 == false) goto L61;
        r6 = r6 + getGap();
    L61:
        int r32 = r7.getHeight();
        float r8 = r6;
        if (r02 >= r8) goto L64;
    L69:
        r6 = r6 + r32;
        r3 = true;
        goto L70
    L64:
        if (r02 >= (r6 + r32)) goto L69;
        r11.offsetLocation(0.0f, -r6);
        boolean r03 = r7.touchEvent(r11);
        r11.offsetLocation(0.0f, r8);
        if (r03 == false) goto L71;
        this.pressedBlock = r7;
        this.pressedBlockY = r6;
        return true;
    L70:
        r22 = r22 + 1;
    L71:
        return false;
    L73:
        if (this.pressedBlock != null) goto L75;
        return false;
    L75:
        r11.offsetLocation(0.0f, -this.pressedBlockY);
        boolean r1 = this.pressedBlock.touchEvent(r11);
        r11.offsetLocation(0.0f, this.pressedBlockY);
        if (r0 == 1) goto L80;
        if (r0 == 3) goto L80;
        return r1;
    L80:
        this.pressedBlock = null;
        return r1;
    }

    public void reposition() {
        this.height = 0;
        this.minWidth = 0;
        this.textBlocks.clear();
        this.textBlockCharOffsets.clear();
        this.textBlockBlockIndex.clear();
        int r1 = 0;
    L4:
        if (r1 >= this.blocks.size()) goto L6;
        this.minWidth = Math.max(this.minWidth, this.blocks.get(r1).getMinWidth());
        r1 = r1 + 1;
        goto L4
    L6:
        int r12 = 0;
    L8:
        if (r12 >= this.blocks.size()) goto L13;
        RichBlock r2 = this.blocks.get(r12);
        if ((r2 instanceof RichTableBlock) == false) goto L12;
        RichTableBlock.m4856$$Nest$mresolveWidth((RichTableBlock) r2, this.minWidth);
    L12:
        r12 = r12 + 1;
        goto L8
    L13:
        StringBuilder r13 = new StringBuilder();
        int r22 = 0;
        int r3 = 0;
        boolean r4 = false;
    L15:
        if (r22 >= this.blocks.size()) goto L40;
        RichBlock r5 = this.blocks.get(r22);
        boolean r6 = r5.isVisible();
        if (r6 == false) goto L20;
        if (r4 == false) goto L20;
        r3 = r3 + getGap();
    L20:
        r5.currY = r3;
        r5.currH = 0;
        r5.currVisible = r6;
        Rect r7 = r5.padding;
        r5.placeTexts(r7.left, r7.top + r3, r22);
        if (r6 == false) goto L39;
        TextSelectionHelper.TextLayoutBlock[] r42 = r5.getText();
        if (r42 == null) goto L38;
        int r62 = r42.length;
        int r72 = 0;
    L25:
        if (r72 >= r62) goto L38;
        TextSelectionHelper.TextLayoutBlock r8 = r42[r72];
        if (r8 == null) goto L37;
        if (r8.getLayout() == null) goto L37;
        if (r13.length() <= 0) goto L34;
        r13.append('\n');
    L34:
        this.textBlockCharOffsets.add(Integer.valueOf(r13.length()));
        this.textBlockBlockIndex.add(Integer.valueOf(r22));
        this.textBlocks.add(r8);
        CharSequence r82 = r8.getLayout().getText();
        if (r82 == null) goto L37;
        r13.append(r82);
    L37:
        r72 = r72 + 1;
    L38:
        int r43 = r5.getHeight();
        r5.currH = r43;
        r3 = r3 + r43;
        r4 = true;
    L39:
        r22 = r22 + 1;
        goto L15
    L40:
        this.height = r3;
        this.joinedText = r13;
    }

    public void setChatMessageCellDelegate(ChatMessageCell r1, ChatMessageCell.ChatMessageCellDelegate r2) {
        this.cell = r1;
        this.delegate = r2;
    }

    public void setResourcesProvider(Theme.ResourcesProvider r1) {
        this.resourcesProvider = r1;
    }

    public boolean setSlideshowPage(TL_iv.PageBlock r6) {
        if (r6 != null) goto L5;
        return false;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= this.blocks.size()) goto L20;
        RichBlock r2 = this.blocks.get(r1);
        if ((r2 instanceof RichSlideshowBlock) == false) goto L19;
        RichSlideshowBlock r22 = (RichSlideshowBlock) r2;
        int r3 = 0;
    L13:
        if (r3 >= r22.cells.size()) goto L19;
        if (r22.cells.get(r3).pageBlock == r6) goto L16;
        r3 = r3 + 1;
        goto L13
    L16:
        r22.setCurrentPage(r3);
        return true;
    L19:
        r1 = r1 + 1;
        goto L7
    L20:
        return false;
    }

    public void setTypingAnimator(MultiLayoutTypingAnimator r3) {
        this.typingAnimator = r3;
        int r0 = 0;
    L4:
        if (r0 >= this.blocks.size()) goto L6;
        this.blocks.get(r0).typingAnimator = r3;
        this.blocks.get(r0);
        this.blocks.get(r0).getClass();
        r0 = r0 + 1;
        goto L4
    }

    public void snapshotForBlockquoteAnimation() {
        snapshotForDetailsAnimation();
    }

    public void snapshotForDetailsAnimation() {
        int r0 = 0;
    L4:
        if (r0 >= this.blocks.size()) goto L6;
        this.blocks.get(r0).snapshot();
        r0 = r0 + 1;
        goto L4
    }

    public boolean startsWithMedia() {
        if (this.blocks.isEmpty() == false) goto L5;
        return false;
    L5:
        RichBlock r2 = this.blocks.get(0);
        if ((r2 instanceof RichPhotoBlock) == false) goto L8;
        return true;
    L8:
        if ((r2 instanceof RichVideoBlock) == false) goto L10;
        return true;
    L10:
        if ((r2 instanceof RichCollageBlock) == false) goto L12;
        return true;
    L12:
        if ((r2 instanceof RichSlideshowBlock) == true) goto L19;
        return false;
    L19:
        return true;
    }

    public void updateAnimatedEmojis(int r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.textBlocks.size()) goto L9;
        TextSelectionHelper.TextLayoutBlock r1 = this.textBlocks.get(r0);
        if ((r1 instanceof Text) == false) goto L8;
        ((Text) r1).refreshAnimatedEmoji(r4);
    L8:
        r0 = r0 + 1;
        goto L4
    }
}
