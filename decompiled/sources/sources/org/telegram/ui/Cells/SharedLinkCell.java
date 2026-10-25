package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.net.Uri;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.RichMessageLayout;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LetterDrawable;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.spoilers.SpoilerEffect;
import org.telegram.ui.FilteredSearchView;

/* loaded from: classes6.dex */
public class SharedLinkCell extends FrameLayout {
    private StaticLayout captionLayout;
    private TextPaint captionTextPaint;
    private int captionY;
    private CheckBox2 checkBox;
    private boolean checkingForLongPress;
    private StaticLayout dateLayout;
    private int dateLayoutX;
    private SharedLinkCellDelegate delegate;
    private TextPaint description2TextPaint;
    private int description2Y;
    private StaticLayout descriptionLayout;
    private StaticLayout descriptionLayout2;
    private List<SpoilerEffect> descriptionLayout2Spoilers;
    private List<SpoilerEffect> descriptionLayoutSpoilers;
    private TextPaint descriptionTextPaint;
    private int descriptionY;
    private boolean drawLinkImageView;
    private StaticLayout fromInfoLayout;
    private AnimatedEmojiSpan.EmojiGroupedSpans fromInfoLayoutEmojis;
    private int fromInfoLayoutY;
    private LetterDrawable letterDrawable;
    private ImageReceiver linkImageView;
    private ArrayList<StaticLayout> linkLayout;
    private boolean linkPreviewPressed;
    private SparseArray<List<SpoilerEffect>> linkSpoilers;
    private int linkY;
    ArrayList<CharSequence> links;
    private LinkSpanDrawable.LinkCollector linksCollector;
    private MessageObject message;
    private boolean needDivider;
    private AtomicReference<Layout> patchedDescriptionLayout;
    private AtomicReference<Layout> patchedDescriptionLayout2;
    private Path path;
    private CheckForLongPress pendingCheckForLongPress;
    private CheckForTap pendingCheckForTap;
    private int pressCount;
    private LinkSpanDrawable pressedLink;
    private int pressedLinkIndex;
    private Theme.ResourcesProvider resourcesProvider;
    private SpoilerEffect spoilerPressed;
    private int spoilerTypePressed;
    private Stack<SpoilerEffect> spoilersPool;
    private StaticLayout titleLayout;
    private TextPaint titleTextPaint;
    private int titleY;
    private int viewType;

    public class CheckForLongPress implements Runnable {
        public int currentPressCount;
        final /* synthetic */ SharedLinkCell this$0;

        public CheckForLongPress(SharedLinkCell r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SharedLinkCell.m7016$$Nest$fgetcheckingForLongPress(this.this$0) == true) goto L5;
            return;
        L5:
            if (this.this$0.getParent() != null) goto L7;
            return;
        L7:
            if (this.currentPressCount != SharedLinkCell.m7019$$Nest$fgetpressCount(this.this$0)) goto L20;
            SharedLinkCell.m7021$$Nest$fputcheckingForLongPress(this.this$0, false);
            this.this$0.performHapticFeedback(0);     // Catch: Exception -> L15
        L11:
            if (SharedLinkCell.m7020$$Nest$fgetpressedLinkIndex(this.this$0) < 0) goto L13;
            SharedLinkCellDelegate r0 = SharedLinkCell.m7017$$Nest$fgetdelegate(this.this$0);
            SharedLinkCell r1 = this.this$0;
            r0.onLinkPress(r1.links.get(SharedLinkCell.m7020$$Nest$fgetpressedLinkIndex(r1)).toString(), true);
        L13:
            MotionEvent r02 = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0);
            this.this$0.onTouchEvent(r02);
            r02.recycle();
            return;
        }
    }

    public final class CheckForTap implements Runnable {
        final /* synthetic */ SharedLinkCell this$0;

        private CheckForTap(SharedLinkCell r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ CheckForTap(SharedLinkCell r1, SharedLinkCellIA r2) {
            this(r1);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SharedLinkCell.m7018$$Nest$fgetpendingCheckForLongPress(this.this$0) != null) goto L5;
            SharedLinkCell r0 = this.this$0;
            SharedLinkCell.m7022$$Nest$fputpendingCheckForLongPress(r0, new CheckForLongPress(r0));
        L5:
            CheckForLongPress r02 = SharedLinkCell.m7018$$Nest$fgetpendingCheckForLongPress(this.this$0);
            SharedLinkCell r1 = this.this$0;
            int r2 = SharedLinkCell.m7019$$Nest$fgetpressCount(r1) + 1;
            SharedLinkCell.m7023$$Nest$fputpressCount(r1, r2);
            r02.currentPressCount = r2;
            SharedLinkCell r3 = this.this$0;
            r3.postDelayed(SharedLinkCell.m7018$$Nest$fgetpendingCheckForLongPress(r3), ViewConfiguration.getLongPressTimeout() - ViewConfiguration.getTapTimeout());
        }
    }

    /* loaded from: classes3.dex */
    public interface SharedLinkCellDelegate {
        boolean canPerformActions();

        void needOpenWebView(TLRPC.WebPage r1, MessageObject r2);

        void onLinkPress(String r1, boolean r2);
    }

    /* renamed from: $r8$lambda$G0HC-Pya4Qwn80U2X4rWSW8WIXo, reason: not valid java name */
    public static /* synthetic */ void m7014$r8$lambda$G0HCPya4Qwn80U2X4rWSW8WIXo(SharedLinkCell r0) {
        r0.lambda$startSpoilerRipples$1();
    }

    /* renamed from: $r8$lambda$MKPOzRb-OZ_e9dLxJTTGINpPe5I, reason: not valid java name */
    public static /* synthetic */ void m7015$r8$lambda$MKPOzRbOZ_e9dLxJTTGINpPe5I(SharedLinkCell r0) {
        r0.lambda$startSpoilerRipples$0();
    }

    /* renamed from: -$$Nest$fgetcheckingForLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7016$$Nest$fgetcheckingForLongPress(SharedLinkCell r0) {
        return r0.checkingForLongPress;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SharedLinkCellDelegate m7017$$Nest$fgetdelegate(SharedLinkCell r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetpendingCheckForLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CheckForLongPress m7018$$Nest$fgetpendingCheckForLongPress(SharedLinkCell r0) {
        return r0.pendingCheckForLongPress;
    }

    /* renamed from: -$$Nest$fgetpressCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7019$$Nest$fgetpressCount(SharedLinkCell r0) {
        return r0.pressCount;
    }

    /* renamed from: -$$Nest$fgetpressedLinkIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7020$$Nest$fgetpressedLinkIndex(SharedLinkCell r0) {
        return r0.pressedLinkIndex;
    }

    /* renamed from: -$$Nest$fputcheckingForLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7021$$Nest$fputcheckingForLongPress(SharedLinkCell r0, boolean r1) {
        r0.checkingForLongPress = r1;
    }

    /* renamed from: -$$Nest$fputpendingCheckForLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7022$$Nest$fputpendingCheckForLongPress(SharedLinkCell r0, CheckForLongPress r1) {
        r0.pendingCheckForLongPress = r1;
    }

    /* renamed from: -$$Nest$fputpressCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7023$$Nest$fputpressCount(SharedLinkCell r0, int r1) {
        r0.pressCount = r1;
    }

    public SharedLinkCell(Context r2, int r3) {
        this(r2, r3, null);
    }

    public SharedLinkCell(Context r11, int r12, Theme.ResourcesProvider r13) {
        super(r11);
        this.checkingForLongPress = false;
        this.pendingCheckForLongPress = null;
        this.pressCount = 0;
        this.pendingCheckForTap = null;
        this.linksCollector = new LinkSpanDrawable.LinkCollector(this);
        this.links = new ArrayList();
        this.linkLayout = new ArrayList();
        this.linkSpoilers = new SparseArray();
        this.descriptionLayoutSpoilers = new ArrayList();
        this.descriptionLayout2Spoilers = new ArrayList();
        this.spoilersPool = new Stack();
        this.path = new Path();
        this.spoilerTypePressed = -1;
        this.titleY = AndroidUtilities.dp(10.0f);
        this.descriptionY = AndroidUtilities.dp(30.0f);
        this.patchedDescriptionLayout = new AtomicReference();
        this.description2Y = AndroidUtilities.dp(30.0f);
        this.patchedDescriptionLayout2 = new AtomicReference();
        this.captionY = AndroidUtilities.dp(30.0f);
        this.fromInfoLayoutY = AndroidUtilities.dp(30.0f);
        this.resourcesProvider = r13;
        this.viewType = r12;
        setFocusable(true);
        TextPaint r3 = new TextPaint(1);
        this.titleTextPaint = r3;
        r3.setTypeface(AndroidUtilities.bold());
        this.titleTextPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r13));
        this.descriptionTextPaint = new TextPaint(1);
        this.titleTextPaint.setTextSize(AndroidUtilities.dp(14.0f));
        this.descriptionTextPaint.setTextSize(AndroidUtilities.dp(14.0f));
        setWillNotDraw(false);
        ImageReceiver r32 = new ImageReceiver(this);
        this.linkImageView = r32;
        r32.setRoundRadius(AndroidUtilities.dp(8.0f));
        this.letterDrawable = new LetterDrawable(r13, 0);
        CheckBox2 r33 = new CheckBox2(r11, 21, r13);
        this.checkBox = r33;
        r33.setVisibility(4);
        this.checkBox.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
        this.checkBox.setDrawUnchecked(false);
        this.checkBox.setDrawBackgroundAsArc(2);
        CheckBox2 r112 = this.checkBox;
        boolean r132 = LocaleController.isRTL;
        if (r132 == false) goto L5;
        int r0 = 5;
    L6:
        int r5 = r0 | 48;
        if (r132 == false) goto L9;
        float r6 = 0.0f;
    L10:
        if (r132 == false) goto L12;
        float r8 = 44.0f;
    L13:
        addView(r112, LayoutHelper.createFrame(24, 24.0f, r5, r6, 44.0f, r8, 0.0f));
        if (r12 != 1) goto L16;
        TextPaint r122 = new TextPaint(1);
        this.description2TextPaint = r122;
        r122.setTextSize(AndroidUtilities.dp(13.0f));
    L16:
        TextPaint r123 = new TextPaint(1);
        this.captionTextPaint = r123;
        r123.setTextSize(AndroidUtilities.dp(13.0f));
        return;
    L12:
        r8 = 0.0f;
        goto L13
    L9:
        r6 = 44.0f;
        goto L10
    L5:
        r0 = 3;
        goto L6
    }

    private void gatherLink(String r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.isEmpty() == true) goto L19;
        String r32 = r3.trim();
        if (r32.startsWith("#") == false) goto L10;
        return;
    L10:
        if (AndroidUtilities.charSequenceContains(r32, "://") == false) goto L12;
    L16:
        this.links.add(SpannableString.valueOf(r32));
        return;
    L12:
        if (r32.toString().toLowerCase().indexOf("http") == 0) goto L16;
        if (r32.toString().toLowerCase().indexOf("mailto") == 0) goto L16;
        r32 = AndroidUtilities.defaultUrlScheme(r32) + r32;
        goto L16
    }

    private void gatherRichMessageLinks(ArrayList<TL_iv.PageBlock> r4) {
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        TL_iv.PageBlock r2 = r4.get(r1);
        r1 = r1 + 1;
        gatherRichMessageLinks(r2);
        goto L3
    }

    private void gatherRichMessageLinks(TL_iv.PageBlock r2) {
        if ((r2 instanceof TL_iv.pageBlockParagraph) == false) goto L5;
    L33:
        gatherRichMessageLinks(r2.text);
        return;
    L5:
        if ((r2 instanceof TL_iv.pageBlockHeading1) == true) goto L33;
        if ((r2 instanceof TL_iv.pageBlockHeading2) == true) goto L33;
        if ((r2 instanceof TL_iv.pageBlockHeading3) == true) goto L33;
        if ((r2 instanceof TL_iv.pageBlockHeading4) == true) goto L33;
        if ((r2 instanceof TL_iv.pageBlockHeading5) == true) goto L33;
        if ((r2 instanceof TL_iv.pageBlockHeading6) == true) goto L33;
        if ((r2 instanceof TL_iv.pageBlockPreformatted) == true) goto L33;
        if ((r2 instanceof TL_iv.pageBlockFooter) == true) goto L33;
        if ((r2 instanceof TL_iv.pageBlockBlockquote) == true) goto L33;
        if ((r2 instanceof TL_iv.pageBlockPullquote) == true) goto L33;
        if ((r2 instanceof TL_iv.pageBlockCover) == false) goto L30;
        gatherRichMessageLinks(((TL_iv.pageBlockCover) r2).cover);
        return;
    L30:
        if ((r2 instanceof TL_iv.pageBlockBlockquoteBlocks) == false) goto L35;
        gatherRichMessageLinks(((TL_iv.pageBlockBlockquoteBlocks) r2).blocks);
        return;
    }

    private void gatherRichMessageLinks(TL_iv.RichText r3) {
        if ((r3 instanceof TL_iv.textUrl) == false) goto L7;
        gatherLink(r3.url);
        return;
    L7:
        if ((r3 instanceof TL_iv.textAutoUrl) == false) goto L11;
        gatherLink(RichMessageLayout.getString(r3));
        return;
    L11:
        if ((r3 instanceof TL_iv.textEmail) == false) goto L15;
        gatherLink("mailto:" + ((TL_iv.textEmail) r3).email);
        return;
    L15:
        if ((r3 instanceof TL_iv.textAutoEmail) == false) goto L19;
        gatherLink("mailto:" + RichMessageLayout.getString(r3));
        return;
    L19:
        if ((r3 instanceof TL_iv.textConcat) == false) goto L25;
        int r0 = 0;
    L22:
        if (r0 >= r3.texts.size()) goto L47;
        gatherRichMessageLinks(r3.texts.get(r0));
        r0 = r0 + 1;
        goto L22
    L47:
        return;
    L25:
        if ((r3 instanceof TL_iv.textBold) == false) goto L27;
    L44:
        gatherRichMessageLinks(r3.text);
        return;
    L27:
        if ((r3 instanceof TL_iv.textItalic) == true) goto L44;
        if ((r3 instanceof TL_iv.textUnderline) == true) goto L44;
        if ((r3 instanceof TL_iv.textStrike) == true) goto L44;
        if ((r3 instanceof TL_iv.textFixed) == true) goto L44;
        if ((r3 instanceof TL_iv.textSubscript) == true) goto L44;
        if ((r3 instanceof TL_iv.textSuperscript) == true) goto L44;
        if ((r3 instanceof TL_iv.textMarked) == true) goto L44;
        if ((r3 instanceof TL_iv.textAnchor) == true) goto L44;
    }

    private int getYOffsetForType(int r2) {
        if (r2 == 1) goto L11;
        if (r2 == 2) goto L9;
        return this.linkY;
    L9:
        return this.description2Y;
    L11:
        return this.descriptionY;
    }

    private /* synthetic */ void lambda$startSpoilerRipples$0() {
        this.message.isSpoilersRevealed = true;
        this.linkSpoilers.clear();
        this.descriptionLayoutSpoilers.clear();
        this.descriptionLayout2Spoilers.clear();
        invalidate();
    }

    private /* synthetic */ void lambda$startSpoilerRipples$1() {
        post(new SharedLinkCell$$ExternalSyntheticLambda1(this));
    }

    private void startSpoilerRipples(int r11, int r12, int r13) {
        if (LocaleController.isRTL == false) goto L5;
        float r0 = 8.0f;
    L6:
        int r02 = AndroidUtilities.dp(r0);
        resetPressedLink();
        this.spoilerPressed.setOnRippleEndCallback(new SharedLinkCell$$ExternalSyntheticLambda0(this));
        int r112 = r11 - r02;
        float r03 = (float) Math.sqrt(Math.pow(getWidth(), 2.0d) + Math.pow(getHeight(), 2.0d));
        int r1 = this.spoilerTypePressed;
        if (r1 == 0) goto L19;
        if (r1 == 1) goto L15;
        if (r1 != 2) goto L27;
        Iterator<SpoilerEffect> r132 = this.descriptionLayout2Spoilers.iterator();
    L13:
        if (r132.hasNext() == false) goto L27;
        r132.next().startRipple(r112, r12 - getYOffsetForType(2), r03);
    L27:
        int r113 = 0;
    L28:
        if (r113 > 2) goto L52;
        if (r113 == this.spoilerTypePressed) goto L51;
        if (r113 == 0) goto L43;
        if (r113 == 1) goto L39;
        if (r113 != 2) goto L51;
        Iterator<SpoilerEffect> r122 = this.descriptionLayout2Spoilers.iterator();
    L37:
        if (r122.hasNext() == false) goto L51;
        r122.next().startRipple(r13.getBounds().centerX(), r13.getBounds().centerY(), r03);
        goto L37
    L39:
        Iterator<SpoilerEffect> r123 = this.descriptionLayoutSpoilers.iterator();
    L41:
        if (r123.hasNext() == false) goto L51;
        r123.next().startRipple(r13.getBounds().centerX(), r13.getBounds().centerY(), r03);
        goto L41
    L43:
        int r124 = 0;
    L45:
        if (r124 >= this.linkLayout.size()) goto L51;
        StaticLayout r133 = this.linkLayout.get(r124);
        r133.getLineBottom(r133.getLineCount() - 1);
        Iterator<SpoilerEffect> r134 = this.linkSpoilers.get(r124).iterator();
    L48:
        if (r134.hasNext() == false) goto L50;
        r134.next().startRipple(r1.getBounds().centerX(), r1.getBounds().centerY(), r03);
        goto L48
    L50:
        r124 = r124 + 1;
    L51:
        r113 = r113 + 1;
        goto L28
    L52:
        this.spoilerTypePressed = -1;
        this.spoilerPressed = null;
        return;
    L15:
        Iterator<SpoilerEffect> r135 = this.descriptionLayoutSpoilers.iterator();
    L17:
        if (r135.hasNext() == false) goto L27;
        r135.next().startRipple(r112, r12 - getYOffsetForType(1), r03);
        goto L17
    L19:
        float r14 = 0.0f;
        int r5 = 0;
    L21:
        if (r5 >= this.linkLayout.size()) goto L27;
        StaticLayout r6 = this.linkLayout.get(r5);
        r14 = r14 + r6.getLineBottom(r6.getLineCount() - 1);
        Iterator<SpoilerEffect> r62 = this.linkSpoilers.get(r5).iterator();
    L24:
        if (r62.hasNext() == false) goto L26;
        r62.next().startRipple(r112, ((r12 - getYOffsetForType(0)) - r13) + r14, r03);
        goto L24
    L26:
        r5 = r5 + 1;
        goto L21
    L5:
        r0 = AndroidUtilities.leftBaseline;
        goto L6
    }

    public void cancelCheckLongPress() {
        this.checkingForLongPress = false;
        CheckForLongPress r0 = this.pendingCheckForLongPress;
        if (r0 == null) goto L5;
        removeCallbacks(r0);
    L5:
        CheckForTap r02 = this.pendingCheckForTap;
        if (r02 == null) goto L9;
        removeCallbacks(r02);
        return;
    }

    public String getLink(int r2) {
        if (r2 >= 0) goto L4;
        return null;
    L4:
        if (r2 < this.links.size()) goto L7;
        return null;
    L7:
        return this.links.get(r2).toString();
    }

    public ImageReceiver getLinkImageView() {
        return this.linkImageView;
    }

    public MessageObject getMessage() {
        return this.message;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.drawLinkImageView == false) goto L5;
        this.linkImageView.onAttachedToWindow();
    L5:
        this.fromInfoLayoutEmojis = AnimatedEmojiSpan.update(0, this, this.fromInfoLayoutEmojis, new Layout[]{this.fromInfoLayout});
        ImageReceiver r4 = this.linkImageView;
        if (r4 == null) goto L9;
        r4.onAttachedToWindow();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.drawLinkImageView == false) goto L5;
        this.linkImageView.onDetachedFromWindow();
    L5:
        AnimatedEmojiSpan.release(this, this.fromInfoLayoutEmojis);
        ImageReceiver r1 = this.linkImageView;
        if (r1 == null) goto L9;
        r1.onDetachedFromWindow();
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r20) {
        Canvas r8 = r20;
        if (this.viewType != 1) goto L5;
        this.description2TextPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3, this.resourcesProvider));
    L5:
        float r12 = 8.0f;
        if (this.dateLayout == null) goto L17;
        r8.save();
        if (LocaleController.isRTL == false) goto L10;
        float r1 = 8.0f;
    L11:
        int r13 = AndroidUtilities.dp(r1);
        if (LocaleController.isRTL == false) goto L14;
        int r2 = 0;
    L15:
        r8.translate(r13 + r2, this.titleY);
        this.dateLayout.draw(r8);
        r8.restore();
        goto L17
    L14:
        r2 = this.dateLayoutX;
        goto L15
    L10:
        r1 = AndroidUtilities.leftBaseline;
    L17:
        if (this.titleLayout == null) goto L31;
        r8.save();
        if (LocaleController.isRTL == false) goto L21;
        float r14 = 8.0f;
    L22:
        float r15 = AndroidUtilities.dp(r14);
        if (LocaleController.isRTL == false) goto L29;
        StaticLayout r22 = this.dateLayout;
        if (r22 != null) goto L27;
        float r23 = 0.0f;
    L28:
        r15 = r15 + r23;
        goto L29
    L27:
        r23 = r22.getWidth() + AndroidUtilities.dp(4.0f);
    L29:
        r8.translate(r15, this.titleY);
        this.titleLayout.draw(r8);
        r8.restore();
        goto L31
    L21:
        r14 = AndroidUtilities.leftBaseline;
    L31:
        if (this.captionLayout == null) goto L38;
        this.captionTextPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        r8.save();
        if (LocaleController.isRTL == false) goto L35;
        float r16 = 8.0f;
    L36:
        r8.translate(AndroidUtilities.dp(r16), this.captionY);
        this.captionLayout.draw(r8);
        r8.restore();
        goto L38
    L35:
        r16 = AndroidUtilities.leftBaseline;
    L38:
        if (this.descriptionLayout == null) goto L45;
        this.descriptionTextPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        r8.save();
        if (LocaleController.isRTL == false) goto L42;
        float r17 = 8.0f;
    L43:
        r8.translate(AndroidUtilities.dp(r17), this.descriptionY);
        SpoilerEffect.renderWithRipple(this, false, this.descriptionTextPaint.getColor(), -AndroidUtilities.dp(2.0f), this.patchedDescriptionLayout, 0, this.descriptionLayout, this.descriptionLayoutSpoilers, r8, false);
        r8.restore();
        goto L45
    L42:
        r17 = AndroidUtilities.leftBaseline;
    L45:
        if (this.descriptionLayout2 == null) goto L51;
        this.descriptionTextPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        r8.save();
        if (LocaleController.isRTL == false) goto L49;
        float r18 = 8.0f;
    L50:
        r8.translate(AndroidUtilities.dp(r18), this.description2Y);
        SpoilerEffect.renderWithRipple(this, false, this.descriptionTextPaint.getColor(), -AndroidUtilities.dp(2.0f), this.patchedDescriptionLayout2, 0, this.descriptionLayout2, this.descriptionLayout2Spoilers, r8, false);
        SharedLinkCell r9 = this;
        r8.restore();
    L53:
        if (r9.linkLayout.isEmpty() == true) goto L86;
        r9.descriptionTextPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText, r9.resourcesProvider));
        int r0 = 0;
        int r19 = 0;
    L56:
        if (r0 >= r9.linkLayout.size()) goto L83;
        StaticLayout r24 = r9.linkLayout.get(r0);
        List<SpoilerEffect> r3 = r9.linkSpoilers.get(r0);
        if (r24.getLineCount() <= 0) goto L81;
        r8.save();
        if (LocaleController.isRTL == false) goto L62;
        float r4 = 8.0f;
    L63:
        r8.translate(AndroidUtilities.dp(r4), r9.linkY + r19);
        r9.path.rewind();
        if (r3 == null) goto L69;
        Iterator<SpoilerEffect> r42 = r3.iterator();
    L67:
        if (r42.hasNext() == false) goto L69;
        Rect r5 = r42.next().getBounds();
        r9.path.addRect(r5.left, r5.top, r5.right, r5.bottom, Path.Direction.CW);
    L69:
        r8.save();
        r8.clipPath(r9.path, Region.Op.DIFFERENCE);
        r24.draw(r8);
        r8.restore();
        r8.save();
        r8.clipPath(r9.path);
        r9.path.rewind();
        if (r3 != null) goto L72;
    L74:
        r8.clipPath(r9.path);
        r24.draw(r8);
        r8.restore();
        if (r3 == null) goto L80;
        Iterator<SpoilerEffect> r32 = r3.iterator();
    L78:
        if (r32.hasNext() == false) goto L80;
        r32.next().draw(r8);
    L80:
        r8.restore();
        r19 = r19 + r24.getLineBottom(r24.getLineCount() - 1);
        goto L81
    L72:
        if (r3.isEmpty() == true) goto L74;
        r3.get(0).getRipplePath(r9.path);
        goto L74
    L62:
        r4 = AndroidUtilities.leftBaseline;
    L81:
        r0 = r0 + 1;
        goto L56
    L83:
        if (r9.linksCollector.draw(r8) == false) goto L86;
        r9.invalidate();
    L86:
        if (r9.fromInfoLayout == null) goto L92;
        r8.save();
        if (LocaleController.isRTL == true) goto L91;
        r12 = AndroidUtilities.leftBaseline;
    L91:
        r8.translate(AndroidUtilities.dp(r12), r9.fromInfoLayoutY);
        r9.fromInfoLayout.draw(r8);
        AnimatedEmojiSpan.drawAnimatedEmojis(r20, r9.fromInfoLayout, r9.fromInfoLayoutEmojis, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f);
        r8 = r20;
        r8.restore();
    L92:
        r9.letterDrawable.draw(r8);
        if (r9.drawLinkImageView == false) goto L96;
        r9.linkImageView.draw(r8);
    L96:
        if (r9.needDivider == true) goto L98;
        return;
    L98:
        if (LocaleController.isRTL == false) goto L101;
        r8.drawLine(0.0f, r9.getMeasuredHeight() - 1, r9.getMeasuredWidth() - AndroidUtilities.dp(AndroidUtilities.leftBaseline), r9.getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L101:
        r20.drawLine(AndroidUtilities.dp(AndroidUtilities.leftBaseline), r9.getMeasuredHeight() - 1, r9.getMeasuredWidth(), r9.getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L49:
        r18 = AndroidUtilities.leftBaseline;
        goto L50
    L51:
        r9 = this;
        goto L53
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r4) {
        super.onInitializeAccessibilityNodeInfo(r4);
        StringBuilder r0 = new StringBuilder();
        StaticLayout r1 = this.titleLayout;
        if (r1 == null) goto L6;
        r0.append(r1.getText());
    L6:
        if (this.descriptionLayout == null) goto L9;
        r0.append(", ");
        r0.append(this.descriptionLayout.getText());
    L9:
        if (this.descriptionLayout2 == null) goto L11;
        r0.append(", ");
        r0.append(this.descriptionLayout2.getText());
    L11:
        r4.setText(r0.toString());
        if (this.checkBox.isChecked() == false) goto L15;
        r4.setChecked(true);
        r4.setCheckable(true);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r3v32, types: [android.text.Spannable, android.text.SpannableStringBuilder] */
    /* JADX WARN: Type inference failed for: r3v73, types: [android.text.Spannable, android.text.SpannableStringBuilder] */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.CharSequence] */
    @Override // android.widget.FrameLayout, android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onMeasure(int r30, int r31) {
        int r2 = 0;
        this.drawLinkImageView = false;
        this.descriptionLayout = null;
        this.titleLayout = null;
        this.descriptionLayout2 = null;
        this.captionLayout = null;
        this.linkLayout.clear();
        this.links.clear();
        float r4 = 8.0f;
        int r8 = (View.MeasureSpec.getSize(r30) - AndroidUtilities.dp(AndroidUtilities.leftBaseline)) - AndroidUtilities.dp(8.0f);
        MessageObject r0 = this.message;
        TLRPC.MessageMedia r5 = r0.messageOwner.media;
        int r14 = 1;
        if ((r5 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L21;
        TLRPC.WebPage r52 = r5.webpage;
        if ((r52 instanceof TLRPC.TL_webPage) == false) goto L21;
        if (r0.photoThumbs != null) goto L12;
        if (r52.photo == null) goto L12;
        r0.generateThumbs(true);
    L12:
        if (r52.photo != null) goto L14;
    L16:
        boolean r02 = false;
    L17:
        String r6 = r52.title;
        if (r6 != null) goto L20;
        r6 = r52.site_name;
    L20:
        String r7 = r52.description;
        String r53 = r52.url;
        boolean r54 = r02;
        String r03 = r6;
        String r62 = r53;
    L22:
        MessageObject r9 = this.message;
        int r10 = 46;
        if (r9 != null) goto L25;
    L147:
        float r312 = 8.0f;
        boolean r18 = r54;
        int r16 = 1;
        SpannableStringBuilder r22 = null;
    L148:
        if (r62 != null) goto L150;
    L152:
        MessageObject r3 = this.message;
        if (r3 == null) goto L174;
        TLRPC.Message r32 = r3.messageOwner;
        if (r32 == null) goto L174;
        TL_iv.RichMessage r33 = r32.rich_message;
        if (r33 == null) goto L174;
        gatherRichMessageLinks(r33.blocks);
        if (this.links.isEmpty() == true) goto L174;
        String r34 = this.links.get(0).toString();
        if (r03 != null) goto L163;
    L164:
        r03 = Uri.parse(r34.toString()).getHost();
        if (r03 != null) goto L167;
        r03 = r34.toString();
    L167:
        if (r03 == null) goto L174;
        int r42 = r03.lastIndexOf(46);
        if (r42 < 0) goto L174;
        String r04 = r03.substring(0, r42);
        int r35 = r04.lastIndexOf(46);
        if (r35 < 0) goto L173;
        r04 = r04.substring(r35 + 1);
    L173:
        StringBuilder r36 = new StringBuilder();
        int r43 = r16;
        r36.append(r04.substring(0, r43).toUpperCase());
        r36.append(r04.substring(r43));
        r03 = r36.toString();
        goto L174
    L163:
        if (r03.length() == 0) goto L164;
    L174:
        String r37 = r03;
        if (this.viewType != 1) goto L177;
        String r92 = LocaleController.stringForMessageListDate(this.message.messageOwner.date);
        int r11 = (int) Math.ceil(this.description2TextPaint.measureText(r92));
        this.dateLayout = ChatMessageCell.generateStaticLayout(r92, this.description2TextPaint, r11, r11, 0, 1);
        this.dateLayoutX = (r8 - r11) - AndroidUtilities.dp(r312);
        int r05 = AndroidUtilities.dp(12.0f) + r11;
    L179:
        if (r37 != null) goto L343;
    L191:
        this.description2Y = this.descriptionY;
        StaticLayout r06 = this.titleLayout;
        if (r06 == null) goto L194;
        int r07 = r06.getLineCount();
    L195:
        int r112 = Math.max(1, 4 - r07);
        if (this.viewType != 1) goto L198;
        r22 = null;
        String r63 = null;
    L200:
        if (r63 == null) goto L212;
        StaticLayout r08 = ChatMessageCell.generateStaticLayout(r63, this.descriptionTextPaint, r8, r8, 0, r112);     // Catch: Exception -> L204
        this.descriptionLayout = r08;     // Catch: Exception -> L204
        if (r08.getLineCount() <= 0) goto L206;
        this.description2Y = (this.descriptionY + this.descriptionLayout.getLineBottom(r5.getLineCount() - 1)) + AndroidUtilities.dp(5.0f);     // Catch: Exception -> L204
    L206:
        this.spoilersPool.addAll(this.descriptionLayoutSpoilers);     // Catch: Exception -> L204
        this.descriptionLayoutSpoilers.clear();     // Catch: Exception -> L204
        if (this.message.isSpoilersRevealed == true) goto L212;
        SpoilerEffect.addSpoilers(this, this.descriptionLayout, this.spoilersPool, this.descriptionLayoutSpoilers);     // Catch: Exception -> L204
    L204:
        e = move-exception;
        FileLog.e(e);
    L212:
        if (r22 != null) goto L341;
        int r23 = r112;
    L228:
        MessageObject r09 = this.message;
        if (r09 != null) goto L231;
    L235:
        TLRPC.PhotoSize r142 = null;
    L236:
        StaticLayout r010 = this.captionLayout;
        if (r010 == null) goto L240;
        int r64 = this.descriptionY;
        this.captionY = r64;
        int r65 = r64 + (r010.getLineBottom(r010.getLineCount() - 1) + AndroidUtilities.dp(5.0f));
        this.descriptionY = r65;
        this.description2Y = r65;
    L240:
        if (this.links.isEmpty() == true) goto L271;
        int r011 = 0;
    L243:
        if (r011 >= this.linkSpoilers.size()) goto L245;
        this.spoilersPool.addAll(this.linkSpoilers.get(r011));
        r011 = r011 + 1;
        goto L243
    L245:
        this.linkSpoilers.clear();
        int r15 = 0;
    L247:
        if (r15 >= this.links.size()) goto L271;
        CharSequence r012 = this.links.get(r15);     // Catch: Exception -> L257
        int r66 = (int) Math.ceil(this.descriptionTextPaint.measureText(r012, 0, r012.length()));     // Catch: Exception -> L257
        CharSequence r72 = TextUtils.ellipsize(AndroidUtilities.replaceNewLines(SpannableStringBuilder.valueOf(r012)), this.descriptionTextPaint, Math.min(r66, r8), TextUtils.TruncateAt.MIDDLE);     // Catch: Exception -> L257
        int r93 = r8;
        StaticLayout r67 = new StaticLayout(r72, this.descriptionTextPaint, r93, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L267
        r8 = r93;
        this.linkY = this.description2Y;     // Catch: Exception -> L257
        StaticLayout r013 = this.descriptionLayout2;     // Catch: Exception -> L257
        if (r013 == null) goto L260;
        if (r013.getLineCount() == 0) goto L260;
        this.linkY = this.linkY + (this.descriptionLayout2.getLineBottom(r9.getLineCount() - 1) + AndroidUtilities.dp(5.0f));     // Catch: Exception -> L257
    L260:
        if (this.message.isSpoilersRevealed == true) goto L265;
        ArrayList r014 = new ArrayList();     // Catch: Exception -> L257
        if ((r72 instanceof Spannable) == false) goto L264;
        SpoilerEffect.addSpoilers(this, r67, (Spannable) r72, this.spoilersPool, r014);     // Catch: Exception -> L257
    L264:
        this.linkSpoilers.put(r15, r014);     // Catch: Exception -> L257
    L265:
        this.linkLayout.add(r67);     // Catch: Exception -> L257
    L270:
        r15 = r15 + 1;
    L267:
        e = e;
        r8 = r93;
    L269:
        FileLog.e(e);
    L257:
        e = e;
    L271:
        int r015 = AndroidUtilities.dp(52.0f);
        if (LocaleController.isRTL == false) goto L274;
        int r68 = (View.MeasureSpec.getSize(r30) - AndroidUtilities.dp(10.0f)) - r015;
    L275:
        this.letterDrawable.setBounds(r68, AndroidUtilities.dp(11.0f), r68 + r015, AndroidUtilities.dp(63.0f));
        if (r18 == false) goto L287;
        TLRPC.PhotoSize r73 = FileLoader.getClosestPhotoSizeWithSize(this.message.photoThumbs, r015, true);
        TLRPC.PhotoSize r102 = FileLoader.getClosestPhotoSizeWithSize(this.message.photoThumbs, 80);
        if (r102 == r73) goto L282;
        r142 = r102;
    L282:
        if (r73 == null) goto L284;
        r73.size = -1;
    L284:
        if (r142 == null) goto L286;
        r142.size = -1;
    L286:
        float r113 = r015;
        this.linkImageView.setImageCoords(r68, AndroidUtilities.dp(11.0f), r113, r113);
        FileLoader.getAttachFileName(r73);
        Locale r69 = Locale.US;
        this.linkImageView.setImage(ImageLocation.getForObject(r73, this.message.photoThumbsObject), String.format(r69, "%d_%d", new Object[]{Integer.valueOf(r015), Integer.valueOf(r015)}), ImageLocation.getForObject(r142, this.message.photoThumbsObject), String.format(r69, "%d_%d_b", new Object[]{Integer.valueOf(r015), Integer.valueOf(r015)}), 0, null, this.message, 0);
        int r12 = 1;
        this.drawLinkImageView = true;
    L289:
        if (this.viewType != r12) goto L291;
        StaticLayout r016 = ChatMessageCell.generateStaticLayout(FilteredSearchView.createFromInfoString(this.message, r12, 2, this.description2TextPaint), this.description2TextPaint, r8, r8, 0, r23);
        this.fromInfoLayout = r016;
        AnimatedEmojiSpan.EmojiGroupedSpans r24 = this.fromInfoLayoutEmojis;
        Layout[] r610 = new Layout[r12];
        int r74 = 0;
        r610[0] = r016;
        this.fromInfoLayoutEmojis = AnimatedEmojiSpan.update(0, this, r24, r610);
    L292:
        StaticLayout r017 = this.titleLayout;
        if (r017 != null) goto L295;
    L297:
        int r44 = r74;
    L298:
        StaticLayout r018 = this.captionLayout;
        if (r018 != null) goto L301;
    L303:
        StaticLayout r019 = this.descriptionLayout;
        if (r019 != null) goto L306;
    L308:
        StaticLayout r020 = this.descriptionLayout2;
        if (r020 != null) goto L311;
    L315:
        int r25 = r74;
    L317:
        if (r25 >= this.linkLayout.size()) goto L322;
        StaticLayout r021 = this.linkLayout.get(r25);
        if (r021.getLineCount() <= 0) goto L321;
        r74 = r74 + r021.getLineBottom(r021.getLineCount() - 1);
    L321:
        r25 = r25 + 1;
        goto L317
    L322:
        int r45 = r44 + r74;
        if (this.fromInfoLayout == null) goto L325;
        this.fromInfoLayoutY = (this.linkY + r74) + AndroidUtilities.dp(5.0f);
        r45 = r45 + (this.fromInfoLayout.getLineBottom(r0.getLineCount() - 1) + AndroidUtilities.dp(5.0f));
    L325:
        this.checkBox.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), TLObject.FLAG_30));
        setMeasuredDimension(View.MeasureSpec.getSize(r30), Math.max(AndroidUtilities.dp(76.0f), r45 + AndroidUtilities.dp(17.0f)) + (this.needDivider ? 1 : 0));
        return;
    L311:
        if (r020.getLineCount() == 0) goto L315;
        r44 = r44 + (this.descriptionLayout2.getLineBottom(r0.getLineCount() - 1) + AndroidUtilities.dp(5.0f));
        if (this.descriptionLayout == null) goto L315;
        r44 = r44 + AndroidUtilities.dp(10.0f);
        goto L315
    L306:
        if (r019.getLineCount() == 0) goto L308;
        r44 = r44 + (this.descriptionLayout.getLineBottom(r0.getLineCount() - 1) + AndroidUtilities.dp(5.0f));
        goto L308
    L301:
        if (r018.getLineCount() == 0) goto L303;
        r44 = r44 + (this.captionLayout.getLineBottom(r0.getLineCount() - 1) + AndroidUtilities.dp(5.0f));
        goto L303
    L295:
        if (r017.getLineCount() == 0) goto L297;
        r44 = this.titleLayout.getLineBottom(r0.getLineCount() - 1) + AndroidUtilities.dp(4.0f);
        goto L298
    L291:
        r74 = 0;
        goto L292
    L287:
        r12 = 1;
        goto L289
    L274:
        r68 = AndroidUtilities.dp(10.0f);
        goto L275
    L231:
        if (TextUtils.isEmpty(r09.messageOwner.message) == true) goto L235;
        r142 = null;
        CharSequence r022 = AndroidUtilities.highlightText(Emoji.replaceEmoji(this.message.messageOwner.message.replace("\n", " ").replaceAll(" +", " ").trim(), Theme.chat_msgTextPaint.getFontMetricsInt(), false), this.message.highlightedWords, null);
        if (r022 == null) goto L236;
        this.captionLayout = new StaticLayout(TextUtils.ellipsize(AndroidUtilities.ellipsizeCenterEnd(r022, this.message.highlightedWords.get(0), r8, this.captionTextPaint, 130), this.captionTextPaint, r8, TextUtils.TruncateAt.END), this.captionTextPaint, r8 + AndroidUtilities.dp(4.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        goto L236
    L341:
        SpannableStringBuilder r611 = r22;
        r23 = r112;
        this.descriptionLayout2 = ChatMessageCell.generateStaticLayout(r611, this.descriptionTextPaint, r8, r8, 0, r112);     // Catch: Exception -> L218
        if (this.descriptionLayout == null) goto L220;
        this.description2Y += AndroidUtilities.dp(10.0f);
    L220:
        this.spoilersPool.addAll(this.descriptionLayout2Spoilers);     // Catch: Exception -> L218
        this.descriptionLayout2Spoilers.clear();     // Catch: Exception -> L218
        if (this.message.isSpoilersRevealed == true) goto L228;
        SpoilerEffect.addSpoilers(this, this.descriptionLayout2, this.spoilersPool, this.descriptionLayout2Spoilers);     // Catch: Exception -> L218
    L218:
        e = e;
    L226:
        FileLog.e(e);
    L224:
        e = e;
        r23 = r112;
        goto L226
    L198:
        r63 = r7;
        goto L200
    L194:
        r07 = 0;
        goto L195
    L343:
        ?? r55 = AndroidUtilities.highlightText(r37, this.message.highlightedWords, null);     // Catch: Exception -> L188
        if (r55 == 0) goto L183;
        String r94 = r55;
    L184:
        int r023 = r8 - r05;     // Catch: Exception -> L188
        StaticLayout r024 = ChatMessageCell.generateStaticLayout(r94, this.titleTextPaint, r023 - AndroidUtilities.dp(4.0f), r023 - AndroidUtilities.dp(4.0f), 0, 3);     // Catch: Exception -> L188
        this.titleLayout = r024;     // Catch: Exception -> L188
        if (r024.getLineCount() <= 0) goto L190;
        this.descriptionY = (this.titleY + this.titleLayout.getLineBottom(r5.getLineCount() - 1)) + AndroidUtilities.dp(4.0f);     // Catch: Exception -> L188
    L190:
        this.letterDrawable.setTitle(r37);
        goto L191
    L183:
        r94 = r37;
    L188:
        e = move-exception;
        FileLog.e(e);
        goto L190
    L177:
        r05 = 0;
        goto L179
    L150:
        if (this.links.isEmpty() == false) goto L152;
        this.links.add(r62);
        goto L152
    L25:
        if (r9.messageOwner.entities.isEmpty() == true) goto L147;
        int r114 = 0;
        String r95 = r7;
        String r75 = r03;
        SpannableStringBuilder r025 = null;
        boolean r56 = r54;
    L28:
        if (r114 >= this.message.messageOwner.entities.size()) goto L146;
        TLRPC.MessageEntity r122 = this.message.messageOwner.entities.get(r114);
        if (r122.length <= 0) goto L35;
        int r13 = r122.offset;
        if (r13 < 0) goto L35;
        if (r13 >= this.message.messageOwner.message.length()) goto L35;
        if ((r122.offset + r122.length) <= this.message.messageOwner.message.length()) goto L39;
        r122.length = this.message.messageOwner.message.length() - r122.offset;
    L39:
        if (r114 != 0) goto L49;
        if (r62 == null) goto L49;
        if (r122.offset != 0) goto L46;
        if (r122.length == this.message.messageOwner.message.length()) goto L49;
    L46:
        if (this.message.messageOwner.entities.size() != r14) goto L50;
        if (r95 != null) goto L49;
        r025 = SpannableStringBuilder.valueOf(this.message.messageOwner.message);
        MediaDataController.addTextStyleRuns(this.message, r025);
        goto L49
    L50:
        r025 = SpannableStringBuilder.valueOf(this.message.messageOwner.message);
        MediaDataController.addTextStyleRuns(this.message, r025);
    L49:
        SpannableStringBuilder r132 = r025;
        if ((r122 instanceof TLRPC.TL_messageEntityTextUrl) == false) goto L54;
    L55:
        float r313 = r4;
        if ((r122 instanceof TLRPC.TL_messageEntityUrl) == false) goto L82;
        String r026 = this.message.messageOwner.message;     // Catch: Exception -> L74
        int r38 = r122.offset;     // Catch: Exception -> L74
        String r027 = r026.substring(r38, r122.length + r38);     // Catch: Exception -> L74
    L83:
        if (r027 != null) goto L85;
    L88:
        if (r75 != null) goto L90;
    L91:
        r75 = Uri.parse(r027.toString()).getHost();     // Catch: Exception -> L74
        if (r75 != null) goto L94;
        r75 = r027.toString();     // Catch: Exception -> L74
    L94:
        if (r75 == null) goto L102;
        int r39 = r75.lastIndexOf(r10);     // Catch: Exception -> L74
        if (r39 < 0) goto L102;
        String r76 = r75.substring(r2, r39);     // Catch: Exception -> L74
        int r310 = r76.lastIndexOf(r10);     // Catch: Exception -> L74
        if (r310 < 0) goto L100;
        r76 = r76.substring(r310 + 1);     // Catch: Exception -> L74
    L100:
        r75 = r76.substring(r2, r14).toUpperCase() + r76.substring(r14);     // Catch: Exception -> L74
    L102:
        if (r122.offset == 0) goto L104;
    L105:
        ?? r311 = SpannableStringBuilder.valueOf(this.message.messageOwner.message);     // Catch: Exception -> L74
        MediaDataController.addTextStyleRuns(this.message, r311);     // Catch: Exception -> L74
        String r314 = r311;
    L77:
        r95 = r314;
    L107:
        if (r027 != null) goto L339;
        boolean r182 = r56;
        String r19 = r75;
        int r162 = r14;
    L143:
        r025 = r132;
        r75 = r19;
    L145:
        r114 = r114 + 1;
        r4 = r313;
        r14 = r162;
        r56 = r182;
        r2 = 0;
        r10 = 46;
        goto L28
    L339:
        if (AndroidUtilities.charSequenceContains(r027, "://") == false) goto L111;
    L116:
        int r315 = r2;
    L117:
        SpannableString r028 = SpannableString.valueOf(r027);     // Catch: Exception -> L140
        int r46 = r122.offset;     // Catch: Exception -> L140
        int r123 = r122.length + r46;     // Catch: Exception -> L140
        ArrayList<TLRPC.MessageEntity> r152 = this.message.messageOwner.entities;     // Catch: Exception -> L140
        r162 = r14;
        int r143 = r152.size();     // Catch: Exception -> L136
        int r103 = r2;
        boolean r57 = r56;
    L120:
        if (r103 >= r143) goto L138;
        TLRPC.MessageEntity r17 = r152.get(r103);     // Catch: Exception -> L136
        r103 = r103 + 1;     // Catch: Exception -> L136
        TLRPC.MessageEntity r26 = r17;     // Catch: Exception -> L136
        int r172 = r315;
        int r316 = r26.offset;     // Catch: Exception -> L136
        boolean r183 = r57;
        int r58 = r26.length + r316;     // Catch: Exception -> L132
        if ((r26 instanceof TLRPC.TL_messageEntitySpoiler) == false) goto L134;
        if (r46 > r58) goto L134;
        if (r123 < r316) goto L134;
        TextStyleSpan.TextStyleRun r27 = new TextStyleSpan.TextStyleRun();     // Catch: Exception -> L132
        String r192 = r75;
        r27.flags |= 256;
        r028.setSpan(new TextStyleSpan(r27), Math.max(r46, r316), Math.min(r123, r58) + r172, 33);     // Catch: Exception -> L130
    L135:
        r315 = r172;
        r57 = r183;
        r75 = r192;
    L130:
        e = e;
        r75 = r192;
        r182 = r183;
    L144:
        FileLog.e(e);
        r025 = r132;
    L134:
        r192 = r75;
    L132:
        e = e;
    L133:
        r182 = r183;
        goto L144
    L138:
        r182 = r57;
        r19 = r75;
        this.links.add(r028);     // Catch: Exception -> L130
    L136:
        e = e;
        r183 = r56;
        goto L133
    L111:
        if (r027.toString().toLowerCase().indexOf("http") == 0) goto L116;
        if (r027.toString().toLowerCase().indexOf("mailto") == 0) goto L116;
        String r317 = AndroidUtilities.defaultUrlScheme(r027);     // Catch: Exception -> L74
        r027 = r317 + r027;     // Catch: Exception -> L74
        r315 = r317.length();     // Catch: Exception -> L74
    L140:
        e = e;
        boolean r184 = r56;
    L66:
        r162 = r14;
        r182 = r184;
        goto L144
    L104:
        if (r122.length == this.message.messageOwner.message.length()) goto L107;
    L90:
        if (r75.length() != 0) goto L107;
    L85:
        if (r027.toString().startsWith("tg://emoji?id=") == false) goto L88;
        r182 = r56;
        r025 = r132;
    L87:
        r162 = r14;
        goto L145
    L82:
        r027 = r122.url;     // Catch: Exception -> L74
        goto L83
    L54:
        if ((r122 instanceof TLRPC.TL_messageEntityUrl) == true) goto L55;
        if ((r122 instanceof TLRPC.TL_messageEntityEmail) == false) goto L62;
        if (r75 != null) goto L60;
    L67:
        StringBuilder r029 = new StringBuilder();     // Catch: Exception -> L63
        r029.append("mailto:");     // Catch: Exception -> L63
        String r153 = this.message.messageOwner.message;     // Catch: Exception -> L63
        r313 = r4;
        int r47 = r122.offset;     // Catch: Exception -> L74
        r029.append(r153.substring(r47, r122.length + r47));     // Catch: Exception -> L74
        r027 = r029.toString();     // Catch: Exception -> L74
        String r318 = this.message.messageOwner.message;     // Catch: Exception -> L74
        int r48 = r122.offset;     // Catch: Exception -> L74
        r75 = r318.substring(r48, r122.length + r48);     // Catch: Exception -> L74
        if (r122.offset == 0) goto L72;
    L76:
        ?? r319 = SpannableStringBuilder.valueOf(this.message.messageOwner.message);     // Catch: Exception -> L74
        MediaDataController.addTextStyleRuns(this.message, r319);     // Catch: Exception -> L74
        r314 = r319;
        goto L77
    L72:
        if (r122.length == this.message.messageOwner.message.length()) goto L107;
    L74:
        e = e;
    L65:
        r184 = r56;
        goto L66
    L60:
        if (r75.length() == 0) goto L67;
    L62:
        r313 = r4;
        r027 = null;
    L63:
        e = e;
        r313 = r4;
    L35:
        r313 = r4;
        r182 = r56;
        goto L87
    L146:
        r312 = r4;
        r18 = r56;
        r16 = r14;
        r22 = r025;
        r03 = r75;
        r7 = r95;
        goto L148
    L14:
        if (this.message.photoThumbs == null) goto L16;
        r02 = true;
    L21:
        r54 = false;
        r03 = null;
        r62 = null;
        r7 = null;
        goto L22
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r17) {
        if (this.message != null) goto L5;
    L134:
        resetPressedLink();
    L135:
        boolean r0 = false;
    L136:
        if (r0 == false) goto L138;
        return true;
    L138:
        if (super.onTouchEvent(r17) == false) goto L141;
        return true;
    L141:
        return false;
    L5:
        if (this.linkLayout.isEmpty() == true) goto L134;
        SharedLinkCellDelegate r02 = this.delegate;
        if (r02 == null) goto L134;
        if (r02.canPerformActions() == false) goto L134;
        if (r17.getAction() != 0) goto L13;
    L22:
        int r4 = (int) r17.getX();
        int r5 = (int) r17.getY();
        int r03 = 0;
        int r6 = 0;
    L24:
        if (r03 >= this.linkLayout.size()) goto L89;
        StaticLayout r7 = this.linkLayout.get(r03);
        if (r7.getLineCount() <= 0) goto L88;
        int r9 = r7.getLineBottom(r7.getLineCount() - 1);
        if (LocaleController.isRTL == false) goto L30;
        float r10 = 8.0f;
    L31:
        int r102 = AndroidUtilities.dp(r10);
        float r11 = r4;
        float r12 = r102;
        if (r11 >= (r7.getLineLeft(0) + r12)) goto L34;
    L87:
        r6 = r6 + r9;
        goto L88
    L34:
        if (r11 > (r7.getLineWidth(0) + r12)) goto L87;
        int r112 = this.linkY;
        if (r5 < (r112 + r6)) goto L87;
        if (r5 > ((r112 + r6) + r9)) goto L87;
        TLRPC.WebPage r113 = null;
        if (r17.getAction() != 0) goto L65;
        this.spoilerPressed = null;
        if (this.linkSpoilers.get(r03, null) == null) goto L50;
        Iterator<SpoilerEffect> r92 = this.linkSpoilers.get(r03).iterator();
    L45:
        if (r92.hasNext() == false) goto L50;
        SpoilerEffect r13 = r92.next();
        if (r13.getBounds().contains(r4 - r102, (r5 - this.linkY) - r6) == false) goto L45;
        resetPressedLink();
        this.spoilerPressed = r13;
        this.spoilerTypePressed = 0;
    L50:
        if (this.spoilerPressed == null) goto L54;
    L51:
        r0 = true;
    L52:
        boolean r62 = r0;
    L91:
        if (r17.getAction() != 0) goto L127;
        if (LocaleController.isRTL == false) goto L95;
        float r8 = 8.0f;
    L96:
        int r72 = AndroidUtilities.dp(r8);
        StaticLayout r82 = this.descriptionLayout;
        if (r82 == null) goto L111;
        if (r4 < r72) goto L111;
        if (r4 > (r82.getWidth() + r72)) goto L111;
        int r83 = this.descriptionY;
        if (r5 < r83) goto L111;
        if (r5 > (r83 + this.descriptionLayout.getHeight())) goto L111;
        Iterator<SpoilerEffect> r84 = this.descriptionLayoutSpoilers.iterator();
    L107:
        if (r84.hasNext() == false) goto L111;
        SpoilerEffect r93 = r84.next();
        if (r93.getBounds().contains(r4 - r72, r5 - this.descriptionY) == false) goto L107;
        this.spoilerPressed = r93;
        this.spoilerTypePressed = 1;
        r0 = true;
        r62 = true;
    L111:
        StaticLayout r85 = this.descriptionLayout2;
        if (r85 == null) goto L132;
        if (r4 < r72) goto L132;
        if (r4 > (r85.getWidth() + r72)) goto L132;
        int r86 = this.description2Y;
        if (r5 < r86) goto L132;
        if (r5 > (r86 + this.descriptionLayout2.getHeight())) goto L132;
        Iterator<SpoilerEffect> r87 = this.descriptionLayout2Spoilers.iterator();
    L122:
        if (r87.hasNext() == false) goto L132;
        SpoilerEffect r94 = r87.next();
        if (r94.getBounds().contains(r4 - r72, r5 - this.description2Y) == false) goto L122;
        this.spoilerPressed = r94;
        this.spoilerTypePressed = 2;
    L131:
        r0 = true;
        r62 = true;
    L132:
        if (r62 == true) goto L136;
        resetPressedLink();
        goto L136
    L95:
        r8 = AndroidUtilities.leftBaseline;
        goto L96
    L127:
        if (r17.getAction() != 1) goto L132;
        if (this.spoilerPressed == null) goto L132;
        startSpoilerRipples(r4, r5, 0);
        goto L131
    L54:
        if (this.pressedLinkIndex == r03) goto L56;
    L59:
        resetPressedLink();
        this.pressedLinkIndex = r03;
        LinkSpanDrawable r04 = new LinkSpanDrawable(null, this.resourcesProvider, r4 - r102, (r5 - this.linkY) - r6);
        this.pressedLink = r04;
        LinkPath r05 = r04.obtainNewPath();
        this.linkPreviewPressed = true;
        this.linksCollector.addLink(this.pressedLink);
        startCheckLongPress();
        r05.setCurrentLayout(r7, 0, r12, this.linkY + r6);     // Catch: Exception -> L62
        r7.getSelectionPath(0, r7.getText().length(), r05);     // Catch: Exception -> L62
    L62:
        e = move-exception;
        FileLog.e(e);
        goto L51
    L56:
        if (this.pressedLink == null) goto L59;
        if (this.linkPreviewPressed == true) goto L51;
    L65:
        if (this.linkPreviewPressed == false) goto L84;
    L71:
        e = move-exception;
        FileLog.e(e);
    L82:
        resetPressedLink();
        goto L51
    L67:
        if (this.pressedLinkIndex != 0) goto L73;
        TLRPC.MessageMedia r06 = this.message.messageOwner.media;     // Catch: Exception -> L71
        if (r06 == null) goto L73;
        r113 = r06.webpage;     // Catch: Exception -> L71
    L73:
        if (r113 == null) goto L79;
        String r07 = r113.embed_url;     // Catch: Exception -> L71
        if (r07 == null) goto L79;
        if (r07.length() == 0) goto L79;
        this.delegate.needOpenWebView(r113, this.message);     // Catch: Exception -> L71
    L79:
        this.delegate.onLinkPress(this.links.get(this.pressedLinkIndex).toString(), false);     // Catch: Exception -> L71
        goto L82
    L84:
        if (this.spoilerPressed == null) goto L86;
        startSpoilerRipples(r4, r5, r6);
        goto L51
    L86:
        r62 = true;
        r0 = false;
        goto L91
    L30:
        r10 = AndroidUtilities.leftBaseline;
    L88:
        r03 = r03 + 1;
        goto L24
    L89:
        r0 = false;
        goto L52
    L13:
        if (this.linkPreviewPressed == true) goto L17;
        if (this.spoilerPressed != null) goto L17;
    L20:
        if (r17.getAction() != 3) goto L135;
        resetPressedLink();
    L17:
        if (r17.getAction() != 1) goto L20;
        goto L22
    }

    public void resetPressedLink() {
        this.linksCollector.clear(true);
        this.pressedLinkIndex = -1;
        this.pressedLink = null;
        this.linkPreviewPressed = false;
        cancelCheckLongPress();
        invalidate();
    }

    public void setChecked(boolean r3, boolean r4) {
        if (this.checkBox.getVisibility() == 0) goto L5;
        this.checkBox.setVisibility(0);
    L5:
        this.checkBox.setChecked(r3, r4);
    }

    public void setDelegate(SharedLinkCellDelegate r1) {
        this.delegate = r1;
    }

    public void setLink(MessageObject r1, boolean r2) {
        this.needDivider = r2;
        resetPressedLink();
        this.message = r1;
        requestLayout();
    }

    public void startCheckLongPress() {
        if (this.checkingForLongPress == false) goto L5;
        return;
    L5:
        this.checkingForLongPress = true;
        if (this.pendingCheckForTap != null) goto L8;
        this.pendingCheckForTap = new CheckForTap(this, null);
    L8:
        postDelayed(this.pendingCheckForTap, ViewConfiguration.getTapTimeout());
    }
}
