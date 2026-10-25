package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.FileRefController;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.ActionBar.MessageDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ClipRoundedDrawable;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.URLSpanNoUnderline;

/* loaded from: classes6.dex */
public abstract class BotHelpCell extends View {
    private boolean animating;
    private final int currentAccount;
    private String currentPhotoKey;
    private BotHelpCellDelegate delegate;
    private int height;
    private int imagePadding;
    private ImageReceiver imageReceiver;
    private boolean isPhotoVisible;
    private boolean isTextVisible;
    private LinkSpanDrawable.LinkCollector links;
    private String oldManagerBotName;
    private String oldText;
    private int photoHeight;
    private LinkSpanDrawable<ClickableSpan> pressedLink;
    private final Theme.ResourcesProvider resourcesProvider;
    private Drawable selectorDrawable;
    private int selectorDrawableRadius;
    private StaticLayout textLayout;
    private int textX;
    private int textY;
    public boolean wasDraw;
    private int width;

    public interface BotHelpCellDelegate {
        void didPressUrl(String r1);
    }

    public class BotIntroDrawable extends Drawable {
        private int alpha;
        private final Paint backgroundPaint;
        private final Paint codePaint;
        private ColorFilter colorFilter;
        private final Drawable icon;
        private final Paint layerPaint;
        final /* synthetic */ BotHelpCell this$0;

        public BotIntroDrawable(BotHelpCell r11, Context r12) {
            this.this$0 = r11;
            Paint r112 = new Paint(1);
            this.backgroundPaint = r112;
            Paint r1 = new Paint(1);
            this.codePaint = r1;
            this.layerPaint = new Paint();
            this.alpha = 255;
            Drawable r122 = r12.getResources().getDrawable(R.drawable.msg_folders_bots).mutate();
            this.icon = r122;
            r122.setColorFilter(new PorterDuffColorFilter(-13628751, PorterDuff.Mode.SRC_IN));
            r112.setShader(new RadialGradient(400.0f, 213.0f, 500.0f, -4811527, -9674273, Shader.TileMode.CLAMP));
            r1.setColor(-1);
            r1.setStyle(Paint.Style.STROKE);
            r1.setStrokeCap(Paint.Cap.ROUND);
        }

        private void drawBot(Canvas r5, float r6, float r7, float r8, float r9) {
            int r0 = this.icon.getIntrinsicWidth();
            int r1 = this.icon.getIntrinsicHeight();
            if (r0 <= 0) goto L8;
            if (r1 <= 0) goto L9;
            this.icon.setAlpha(Math.round(this.alpha * r9));
            this.icon.setBounds(0, 0, r0, r1);
            r5.save();
            r5.translate(r6, r7);
            r5.scale(r8 / r0, r8 / r1);
            r5.translate((-r0) / 2.0f, (-r1) / 2.0f);
            this.icon.draw(r5);
            r5.restore();
            return;
        L9:
            return;
        }

        private void drawLine(Canvas r6, Rect r7, float r8, float r9, float r10, float r11, float r12, float r13) {
            int r1 = r7.left;
            int r0 = r7.top;
            r6.drawLine(r1 + (r10 * r8), r0 + (r11 * r9), r1 + (r12 * r8), r0 + (r13 * r9), this.codePaint);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r11) {
            Rect r2 = getBounds();
            if (r2.isEmpty() == false) goto L5;
            return;
        L5:
            if (this.alpha == 0) goto L17;
            ColorFilter r0 = this.colorFilter;
            if (r0 == null) goto L10;
            this.layerPaint.setColorFilter(r0);
            int r112 = r11.saveLayer(r2.left, r2.top, r2.right, r2.bottom, this.layerPaint);
            Canvas r4 = r11;
        L11:
            float r02 = r2.width() / 800.0f;
            float r1 = r2.height() / 427.0f;
            this.backgroundPaint.setAlpha(this.alpha);
            r4.save();
            r4.translate(r2.left, r2.top);
            r4.scale(r02, r1);
            Canvas r3 = r4;
            r3.drawRect(0.0f, 0.0f, 800.0f, 427.0f, this.backgroundPaint);
            r3.restore();
            this.codePaint.setAlpha(this.alpha);
            this.codePaint.setStrokeWidth(Math.min(r02, r1) * 16.4f);
            drawLine(r3, r2, r02, r1, 449.809f, 246.04f, 483.703f, 212.418f);
            drawLine(r3, r2, r02, r1, 483.703f, 212.418f, 451.291f, 179.901f);
            drawLine(r3, r2, r02, r1, 350.197f, 246.04f, 316.302f, 212.418f);
            drawLine(r3, r2, r02, r1, 316.302f, 212.418f, 348.716f, 179.901f);
            drawLine(r3, r2, r02, r1, 379.613f, 278.0f, 420.657f, 146.0f);
            r3.save();
            r3.translate(r2.left, r2.top);
            r3.scale(r02, r1);
            drawBot(r3, 299.339f, 60.9305f, 56.0f, 0.1629f);
            drawBot(r3, 500.651f, 60.9305f, 56.0f, 0.1629f);
            drawBot(r3, 579.018f, 225.477f, 56.0f, 0.2239f);
            drawBot(r3, 220.979f, 225.477f, 56.0f, 0.2239f);
            drawBot(r3, 548.102f, 359.18f, 52.0f, 0.1816f);
            drawBot(r3, 400.0f, 382.172f, 52.0f, 0.123f);
            drawBot(r3, 251.891f, 359.18f, 52.0f, 0.1816f);
            drawBot(r3, 698.424f, 175.361f, 42.0f, 0.12f);
            drawBot(r3, 572.549f, 125.064f, 42.0f, 0.2409f);
            drawBot(r3, 650.19f, 61.1149f, 42.0f, 0.0764f);
            drawBot(r3, 399.994f, 18.0016f, 42.0f, 0.1231f);
            drawBot(r3, 149.799f, 61.1149f, 42.0f, 0.0753f);
            drawBot(r3, 227.44f, 125.064f, 42.0f, 0.2409f);
            drawBot(r3, 101.565f, 175.361f, 42.0f, 0.12f);
            drawBot(r3, 167.057f, 316.916f, 42.0f, 0.15f);
            drawBot(r3, 114.502f, 408.888f, 42.0f, 0.075f);
            drawBot(r3, 678.299f, 408.888f, 42.0f, 0.075f);
            drawBot(r3, 655.947f, 316.916f, 42.0f, 0.15f);
            r3.restore();
            if (r112 < 0) goto L16;
            r3.restoreToCount(r112);
            return;
        L16:
            return;
        L10:
            r4 = r11;
            r112 = -1;
            goto L11
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(427.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(800.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r2) {
            int r22 = Math.max(0, Math.min(255, r2));
            if (this.alpha == r22) goto L6;
            this.alpha = r22;
            invalidateSelf();
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r2) {
            if (this.colorFilter == r2) goto L6;
            this.colorFilter = r2;
            invalidateSelf();
            return;
        }
    }

    public BotHelpCell(Context r1, int r2, Theme.ResourcesProvider r3) {
        super(r1);
        this.links = new LinkSpanDrawable.LinkCollector(this);
        this.imagePadding = AndroidUtilities.dp(4.0f);
        this.currentAccount = r2;
        this.resourcesProvider = r3;
        ImageReceiver r12 = new ImageReceiver(this);
        this.imageReceiver = r12;
        r12.setInvalidateAll(true);
        this.imageReceiver.setCrossfadeWithOldImage(true);
        this.imageReceiver.setCrossfadeDuration(300);
        int r13 = Theme.getColor(Theme.key_listSelector, r3);
        int r22 = SharedConfig.bubbleRadius;
        this.selectorDrawableRadius = r22;
        Drawable r14 = Theme.createRadSelectorDrawable(r13, r22, r22);
        this.selectorDrawable = r14;
        r14.setCallback(this);
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

    private void resetPressedLink() {
        if (this.pressedLink == null) goto L5;
        this.pressedLink = null;
    L5:
        this.links.clear();
        invalidate();
    }

    public boolean animating() {
        return this.animating;
    }

    public int getSideMenuWidth() {
        return 0;
    }

    public CharSequence getText() {
        StaticLayout r0 = this.textLayout;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getText();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.imageReceiver.onAttachedToWindow();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.imageReceiver.onDetachedFromWindow();
        this.wasDraw = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas r12) {
        r12.save();
        r12.translate(getSideMenuWidth() / 2.0f, 0.0f);
        int r0 = (getWidth() - this.width) / 2;
        int r2 = this.photoHeight + AndroidUtilities.dp(2.0f);
        Drawable r3 = Theme.chat_msgInMediaDrawable.getShadowDrawable();
        if (r3 == null) goto L5;
        r3.setBounds(r0, r2, this.width + r0, this.height + r2);
        r3.draw(r12);
    L5:
        Point r32 = AndroidUtilities.displaySize;
        int r4 = r32.x;
        int r33 = r32.y;
        if ((getParent() instanceof View) == false) goto L8;
        View r34 = (View) getParent();
        r4 = r34.getMeasuredWidth();
        r33 = r34.getMeasuredHeight();
    L8:
        MessageDrawable r5 = (MessageDrawable) getThemedDrawable("drawableMsgInMedia");
        int r6 = (int) getY();
        r5.setTop(r6, r4, r33, false, false);
        r5.setBounds(r0, 0, this.width + r0, this.height);
        r5.draw(r12);
        Drawable r35 = this.selectorDrawable;
        if (r35 == null) goto L14;
        int r42 = this.selectorDrawableRadius;
        int r52 = SharedConfig.bubbleRadius;
        if (r42 == r52) goto L13;
        this.selectorDrawableRadius = r52;
        Theme.setMaskDrawableRad(r35, r52, r52);
    L13:
        this.selectorDrawable.setBounds(AndroidUtilities.dp(2.0f) + r0, AndroidUtilities.dp(2.0f), (this.width + r0) - AndroidUtilities.dp(2.0f), this.height - AndroidUtilities.dp(2.0f));
        this.selectorDrawable.draw(r12);
    L14:
        this.imageReceiver.setImageCoords(r0 + r3, this.imagePadding, this.width - (r3 * 2), this.photoHeight - r3);
        this.imageReceiver.draw(r12);
        Theme.chat_msgTextPaint.setColor(getThemedColor(Theme.key_chat_messageTextIn));
        ((TextPaint) Theme.chat_msgTextPaint).linkColor = getThemedColor(Theme.key_chat_messageLinkIn);
        r12.save();
        if (this.isPhotoVisible == false) goto L17;
        float r1 = 14.0f;
    L18:
        int r13 = AndroidUtilities.dp(r1) + r0;
        this.textX = r13;
        float r02 = r13;
        int r14 = AndroidUtilities.dp(11.0f) + r2;
        this.textY = r14;
        r12.translate(r02, r14);
        if (this.links.draw(r12) == false) goto L21;
        invalidate();
    L21:
        StaticLayout r03 = this.textLayout;
        if (r03 == null) goto L24;
        r03.draw(r12);
    L24:
        r12.restore();
        r12.restore();
        this.wasDraw = true;
        return;
    L17:
        r1 = 11.0f;
        goto L18
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
        super.onInitializeAccessibilityNodeInfo(r1);
        StaticLayout r0 = this.textLayout;
        if (r0 == null) goto L6;
        r1.setText(r0.getText());
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), this.height + AndroidUtilities.dp(8.0f));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r13) {
        float r0 = r13.getX();
        float r1 = r13.getY();
        if (this.textLayout != null) goto L5;
    L67:
        boolean r02 = false;
    L69:
        if (this.selectorDrawable == null) goto L88;
        if (r02 == true) goto L80;
        if (r1 <= 0.0f) goto L80;
        if (r13.getAction() != 0) goto L80;
        if (isClickable() == false) goto L80;
        this.selectorDrawable.setState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled});
        this.selectorDrawable.setHotspot(r13.getX(), r13.getY());
        invalidate();
    L78:
        r02 = true;
    L80:
        if (r13.getAction() != 1) goto L82;
    L83:
        this.selectorDrawable.setState(new int[0]);
        invalidate();
        if (r02 == true) goto L78;
        if (r13.getAction() != 1) goto L78;
        performClick();
        goto L78
    L82:
        if (r13.getAction() == 3) goto L83;
    L88:
        if (r02 == false) goto L90;
        return true;
    L90:
        if (super.onTouchEvent(r13) == false) goto L93;
        return true;
    L93:
        return false;
    L5:
        if (r13.getAction() == 0) goto L15;
        if (this.pressedLink == null) goto L12;
        if (r13.getAction() == 1) goto L15;
    L12:
        if (r13.getAction() != 3) goto L67;
        resetPressedLink();
    L15:
        if (r13.getAction() != 0) goto L38;
        resetPressedLink();
        int r2 = (int) (r1 - this.textY);     // Catch: Exception -> L32
        int r7 = this.textLayout.getLineForVertical(r2);     // Catch: Exception -> L32
        float r03 = (int) (r0 - this.textX);     // Catch: Exception -> L32
        int r8 = this.textLayout.getOffsetForHorizontal(r7, r03);     // Catch: Exception -> L32
        float r9 = this.textLayout.getLineLeft(r7);     // Catch: Exception -> L32
        if (r9 <= r03) goto L20;
    L35:
        resetPressedLink();     // Catch: Exception -> L32
        goto L67
    L20:
        if ((r9 + this.textLayout.getLineWidth(r7)) < r03) goto L35;
        Spannable r72 = (Spannable) this.textLayout.getText();     // Catch: Exception -> L32
        ClickableSpan[] r82 = (ClickableSpan[]) r72.getSpans(r8, r8, ClickableSpan.class);     // Catch: Exception -> L32
        if (r82.length == 0) goto L34;
        resetPressedLink();     // Catch: Exception -> L32
        this.pressedLink = new LinkSpanDrawable(r82[0], this.resourcesProvider, r03, r2);     // Catch: Exception -> L32
        int r04 = r72.getSpanStart(r82[0]);     // Catch: Exception -> L26
        LinkPath r22 = this.pressedLink.obtainNewPath();     // Catch: Exception -> L26
        r22.setCurrentLayout(this.textLayout, r04, 0.0f);     // Catch: Exception -> L26
        this.textLayout.getSelectionPath(r04, r72.getSpanEnd(r82[0]), r22);     // Catch: Exception -> L26
    L28:
        this.links.addLink(this.pressedLink);     // Catch: Exception -> L30
        invalidate();     // Catch: Exception -> L30
    L66:
        r02 = true;
        goto L69
    L30:
        e = e;
        boolean r23 = true;
    L37:
        resetPressedLink();
        FileLog.e(e);
        r02 = r23;
    L26:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L30
        goto L28
    L34:
        resetPressedLink();     // Catch: Exception -> L32
    L32:
        e = e;
        r23 = false;
        goto L37
    L38:
        LinkSpanDrawable<ClickableSpan> r05 = this.pressedLink;
        if (r05 == null) goto L67;
        ClickableSpan r06 = (ClickableSpan) r05.getSpan();     // Catch: Exception -> L51
        if ((r06 instanceof URLSpanNoUnderline) == false) goto L57;
        String r07 = ((URLSpanNoUnderline) r06).getURL();     // Catch: Exception -> L51
        if (r07.startsWith("@") == false) goto L45;
    L53:
        BotHelpCellDelegate r24 = this.delegate;     // Catch: Exception -> L51
        if (r24 == null) goto L94;
        r24.didPressUrl(r07);     // Catch: Exception -> L51
    L94:
    L65:
        resetPressedLink();
        goto L66
    L45:
        if (r07.startsWith("#") == true) goto L53;
        if (r07.startsWith("/") == true) goto L53;
        if (r07.startsWith("$") == false) goto L94;
    L57:
        if ((r06 instanceof URLSpan) == false) goto L61;
        BotHelpCellDelegate r25 = this.delegate;     // Catch: Exception -> L51
        if (r25 == null) goto L94;
        r25.didPressUrl(((URLSpan) r06).getURL());     // Catch: Exception -> L51
        goto L94
    L61:
        if (r06 == null) goto L94;
        r06.onClick(this);     // Catch: Exception -> L51
    L51:
        e = move-exception;
        FileLog.e(e);
        goto L65
    }

    public void setAnimating(boolean r1) {
        this.animating = r1;
    }

    public void setDelegate(BotHelpCellDelegate r1) {
        this.delegate = r1;
    }

    public void setText(boolean r34, long r35, String r37, TLObject r38, TL_bots.BotInfo r39, String r40) {
        int r6 = 0;
        if (r38 == null) goto L5;
        boolean r7 = true;
    L6:
        boolean r8 = TextUtils.isEmpty(r37);
        if (r37 == null) goto L11;
        if (r37.length() == 0) goto L11;
    L15:
        if (r37 != null) goto L17;
        String r9 = _UrlKt.FRAGMENT_ENCODE_SET;
    L19:
        if (r9.equals(this.oldText) == false) goto L26;
        if (TextUtils.equals(this.oldManagerBotName, r40) == false) goto L26;
        if (this.isPhotoVisible != r7) goto L26;
        return;
    L26:
        if (TextUtils.isEmpty(r9) == false) goto L33;
        if (r38 != null) goto L33;
        if (TextUtils.isEmpty(r40) == true) goto L33;
        if (r35 == 0) goto L33;
        boolean r10 = true;
    L34:
        if (r7 == true) goto L38;
        if (r10 == true) goto L38;
        boolean r72 = false;
    L39:
        this.isPhotoVisible = r72;
        if (r8 == false) goto L44;
        if (r10 == true) goto L44;
        boolean r82 = false;
    L45:
        this.isTextVisible = r82;
        float r83 = 2.0f;
        float r11 = 4.0f;
        if (r10 == true) goto L48;
        if (r72 == true) goto L55;
    L53:
        float r16 = 4.0f;
    L77:
        this.oldText = AndroidUtilities.getSafeString(r9);
        this.oldManagerBotName = r40;
        setVisibility(0);
        if (AndroidUtilities.isTablet() == false) goto L81;
        int r0 = AndroidUtilities.getMinTabletSide();
    L80:
        int r02 = (int) (r0 * 0.7f);
        if (this.isTextVisible == false) goto L119;
        String[] r84 = r9.split("\n");
        SpannableStringBuilder r92 = new SpannableStringBuilder();
        if (r10 == false) goto L87;
        r92.append(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.ManagedBotChatInfo, new Object[]{DialogObject.getName(this.currentAccount, r35), r40})));
    L100:
        Emoji.replaceEmoji(r92, Theme.chat_msgTextPaint.getFontMetricsInt(), false);
        com.exteragram.messenger.utils.ui.TextPaint r20 = Theme.chat_msgTextPaint;     // Catch: Exception -> L104
        if (this.isPhotoVisible == false) goto L106;
        int r2 = AndroidUtilities.dp(5.0f);     // Catch: Exception -> L104
    L107:
        StaticLayout r18 = new StaticLayout(r92, r20, r02 - r2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L104
        this.textLayout = r18;     // Catch: Exception -> L104
        this.width = 0;     // Catch: Exception -> L104
        this.height = r18.getHeight() + AndroidUtilities.dp(22.0f);     // Catch: Exception -> L104
        int r22 = this.textLayout.getLineCount();     // Catch: Exception -> L104
    L108:
        int r3 = this.width;
        if (r6 >= r22) goto L112;
        float r32 = r3;
        this.width = (int) Math.ceil(Math.max(r32, this.textLayout.getLineWidth(r6) + this.textLayout.getLineLeft(r6)));     // Catch: Exception -> L104
        r6 = r6 + 1;     // Catch: Exception -> L104
        goto L108
    L112:
        if (r3 <= r02) goto L114;
    L115:
        this.width = r02;     // Catch: Exception -> L104
    L121:
        int r03 = this.width + AndroidUtilities.dp(22.0f);
        this.width = r03;
        if (this.isPhotoVisible == false) goto L134;
        int r23 = this.height;
        int r04 = (int) (r03 * 0.5625d);
        this.photoHeight = r04;
        this.height = r23 + (r04 + AndroidUtilities.dp(r16));
        return;
    L134:
        return;
    L114:
        if (this.isPhotoVisible == false) goto L121;
    L106:
        r2 = 0;
    L104:
        e = move-exception;
        FileLog.e(e);
        goto L121
    L87:
        String r24 = LocaleController.getString(R.string.BotInfoTitle);
        if (r34 == false) goto L90;
        r92.append(r24);
        r92.append("\n\n");
    L90:
        int r33 = 0;
    L92:
        if (r33 >= r84.length) goto L97;
        r92.append(r84[r33].trim());
        if (r33 == (r84.length - 1)) goto L96;
        r92.append("\n");
    L96:
        r33 = r33 + 1;
        goto L92
    L97:
        MessageObject.addLinks(false, r92);
        if (r34 == false) goto L100;
        r92.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r24.length(), 33);
        goto L100
    L119:
        if (this.isPhotoVisible == false) goto L121;
        this.width = r02;
        goto L121
    L81:
        Point r05 = AndroidUtilities.displaySize;
        r0 = Math.min(r05.x, r05.y);
        goto L80
    L55:
        String r73 = FileRefController.getKeyForParentObject(r39);
        if (Objects.equals(this.currentPhotoKey, r73) == true) goto L53;
        this.currentPhotoKey = r73;
        if ((r38 instanceof TLRPC.TL_photo) == false) goto L62;
        TLRPC.Photo r06 = (TLRPC.Photo) r38;
        this.imageReceiver.setImage(ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r06.sizes, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN), r06), "400_400", null, "jpg", r39, 0);
    L60:
        float r372 = 2.0f;
        r16 = 4.0f;
    L73:
        int r07 = AndroidUtilities.dp(SharedConfig.bubbleRadius) - AndroidUtilities.dp(r372);
        int r5 = AndroidUtilities.dp(r16);
        if (this.isTextVisible == true) goto L76;
        r5 = r07;
    L76:
        this.imageReceiver.setRoundRadius(r07, r07, r5, r5);
        goto L77
    L62:
        if ((r38 instanceof TLRPC.Document) == false) goto L60;
        TLRPC.Document r08 = (TLRPC.Document) r38;
        TLRPC.PhotoSize r74 = FileLoader.getClosestPhotoSizeWithSize(r08.thumbs, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
        BitmapDrawable r13 = null;
        if (SharedConfig.getDevicePerformanceClass() == 0) goto L72;
        ArrayList<TLRPC.PhotoSize> r12 = r08.thumbs;
        int r14 = r12.size();
        int r15 = 0;
    L66:
        if (r15 >= r14) goto L72;
        TLRPC.PhotoSize r162 = r12.get(r15);
        r15 = r15 + 1;
        TLRPC.PhotoSize r52 = r162;
        float r373 = r83;
        if ((r52 instanceof TLRPC.TL_photoStrippedSize) == false) goto L70;
        float r163 = r11;
        r13 = new BitmapDrawable(getResources(), ImageLoader.getStrippedPhotoBitmap(r52.bytes, "b"));
    L71:
        r83 = r373;
        r11 = r163;
        goto L66
    L70:
        r163 = r11;
    L72:
        r372 = r83;
        r16 = r11;
        this.imageReceiver.setImage(ImageLocation.getForDocument(r08), ImageLoader.AUTOPLAY_FILTER, ImageLocation.getForDocument(MessageObject.getDocumentVideoThumb(r08), r08), null, ImageLocation.getForDocument(r74, r08), "86_86_b", r13, r08.size, "mp4", r39, 0);
        goto L73
    L48:
        if (Objects.equals(this.currentPhotoKey, "setup") == true) goto L53;
        this.currentPhotoKey = "setup";
        this.imageReceiver.setImageBitmap(new ClipRoundedDrawable(new BotIntroDrawable(this, getContext())));
        int r09 = AndroidUtilities.dp(SharedConfig.bubbleRadius) - AndroidUtilities.dp(2.0f);
        int r75 = AndroidUtilities.dp(4.0f);
        if (this.isTextVisible == true) goto L52;
        r75 = r09;
    L52:
        this.imageReceiver.setRoundRadius(r09, r09, r75, r75);
    L44:
        r82 = true;
    L38:
        r72 = true;
    L33:
        r10 = false;
        goto L34
    L17:
        r9 = r37;
    L11:
        if (TextUtils.isEmpty(r40) == false) goto L15;
        if (r7 == true) goto L15;
        setVisibility(8);
        return;
    L5:
        r7 = false;
        goto L6
    }

    public void setText(boolean r9, String r10) {
        setText(r9, 0, r10, null, null, null);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.selectorDrawable) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
