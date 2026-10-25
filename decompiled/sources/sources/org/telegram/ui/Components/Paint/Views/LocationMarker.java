package org.telegram.ui.Components.Paint.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes7.dex */
public class LocationMarker extends View {
    private AnimatedFloat animatedVideo;
    private boolean attachedToWindow;
    private final RectF bounds;
    public final float density;
    private TLRPC.Document flagAnimatedDocument;
    private final ImageReceiver flagAnimatedImageReceiver;
    private TLRPC.Document flagDocument;
    private final float flagIconPadding;
    private final ImageReceiver flagImageReceiver;
    private boolean forceEmoji;
    private float h;
    private boolean hasFlag;
    private final Drawable icon;
    private final float iconPadding;
    private final float iconSize;
    private boolean isVideo;
    private StaticLayout layout;
    private float layoutLeft;
    private float layoutWidth;
    private int maxWidth;
    public final Paint outlinePaint;
    private final RectF padding;
    public final int padx;
    public final int pady;
    private final Path path;
    private boolean relayout;
    private String text;
    private final TextPaint textPaint;
    private float textScale;
    public final int type;
    public final int variant;
    private float w;

    /* renamed from: org.telegram.ui.Components.Paint.Views.LocationMarker$1, reason: invalid class name */
    public class AnonymousClass1 extends Drawable {
        final /* synthetic */ LocationMarker this$0;
        final /* synthetic */ Drawable val$emojiDrawable;

        public AnonymousClass1(LocationMarker r1, Drawable r2) {
            this.this$0 = r1;
            this.val$emojiDrawable = r2;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r4) {
            r4.save();
            if (this.val$emojiDrawable.getBounds() == null) goto L5;
            r4.scale(0.8333333f, 0.8333333f, this.val$emojiDrawable.getBounds().centerX(), this.val$emojiDrawable.getBounds().centerY());
        L5:
            this.val$emojiDrawable.draw(r4);
            r4.restore();
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return this.val$emojiDrawable.getOpacity();
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.val$emojiDrawable.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(int r1, int r2, int r3, int r4) {
            this.val$emojiDrawable.setBounds(r1, r2, r3, r4);
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(Rect r1) {
            this.val$emojiDrawable.setBounds(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            this.val$emojiDrawable.setColorFilter(r1);
        }
    }

    /* renamed from: $r8$lambda$mKKWC-Trba1vsE2rllRPjvjgU6U, reason: not valid java name */
    public static /* synthetic */ void m11517$r8$lambda$mKKWCTrba1vsE2rllRPjvjgU6U(LocationMarker r0, String r1, TLRPC.TL_messages_stickerSet r2) {
        r0.lambda$setCodeEmoji$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$p89tekFUQaVZlVbiV2DdXnxvoCg(LocationMarker r0, String r1, TLRPC.TL_messages_stickerSet r2) {
        r0.lambda$setCodeEmoji$1(r1, r2);
    }

    public LocationMarker(Context r11, int r12, float r13, int r14) {
        super(r11);
        this.text = _UrlKt.FRAGMENT_ENCODE_SET;
        this.padding = new RectF(4.0f, 4.33f, 7.66f, 3.0f);
        this.iconPadding = 3.25f;
        this.flagIconPadding = 2.25f;
        this.iconSize = 21.33f;
        TextPaint r0 = new TextPaint(1);
        this.textPaint = r0;
        this.outlinePaint = new Paint(1);
        ImageReceiver r2 = new ImageReceiver(this);
        this.flagImageReceiver = r2;
        ImageReceiver r3 = new ImageReceiver(this);
        this.flagAnimatedImageReceiver = r3;
        this.textScale = 1.0f;
        this.bounds = new RectF();
        this.path = new Path();
        this.animatedVideo = new AnimatedFloat(this, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.variant = r12;
        this.density = r13;
        r2.setCrossfadeWithOldImage(true);
        r2.setInvalidateAll(true);
        r3.setCrossfadeWithOldImage(true);
        r3.setInvalidateAll(true);
        int r122 = (int) (3.0f * r13);
        this.padx = r122;
        int r1 = (int) (1.0f * r13);
        this.pady = r1;
        setPadding(r122, r1, r122, r1);
        this.type = r14;
        this.icon = r11.getResources().getDrawable(R.drawable.map_pin3).mutate();
        r0.setTextSize(r13 * 24.0f);
        r0.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_CONDENSED_BOLD));
        NotificationCenter.listenEmojiLoading(this);
    }

    private boolean containsEmoji(String r3, String r4) {
        if (r3 == null) goto L14;
        if (r4 == null) goto L14;
        ArrayList<Emoji.EmojiSpanRange> r32 = Emoji.parseEmojis(r3);
        int r0 = 0;
    L8:
        if (r0 >= r32.size()) goto L14;
        if (TextUtils.equals(r32.get(r0).code, r4) == true) goto L11;
        r0 = r0 + 1;
        goto L8
    L11:
        return true;
    L14:
        return false;
    }

    private TLRPC.Document findDocument(TLRPC.TL_messages_stickerSet r9, String r10) {
        if (r9 != null) goto L5;
    L25:
        return null;
    L5:
        if (r9.packs == null) goto L25;
        if (r9.documents == null) goto L25;
        int r2 = 0;
    L11:
        if (r2 >= r9.packs.size()) goto L25;
        TLRPC.TL_stickerPack r3 = r9.packs.get(r2);
        if (containsEmoji(r3.emoticon, r10) == false) goto L24;
        if (r3.documents.isEmpty() == true) goto L24;
        long r32 = r3.documents.get(0).longValue();
        int r5 = 0;
    L18:
        if (r5 >= r9.documents.size()) goto L24;
        if (r9.documents.get(r5).id == r32) goto L22;
        r5 = r5 + 1;
        goto L18
    L22:
        return r9.documents.get(r5);
    L24:
        r2 = r2 + 1;
        goto L11
    }

    private Drawable getEmojiThumb(String r3) {
        Drawable r32 = Emoji.getEmojiBigDrawable(r3);
        if ((r32 instanceof Emoji.SimpleEmojiDrawable) == false) goto L5;
        ((Emoji.SimpleEmojiDrawable) r32).fullSize = false;
    L5:
        if (r32 != null) goto L9;
        return null;
    L9:
        return new AnonymousClass1(this, r32);
    }

    private /* synthetic */ void lambda$setCodeEmoji$0(String r24, TLRPC.TL_messages_stickerSet r25) {
        TLRPC.Document r2 = findDocument(r25, r24);
        this.flagDocument = r2;
        this.flagImageReceiver.setImage(ImageLocation.getForDocument(r2), "80_80", getEmojiThumb(r24), null, null, 0);
        this.flagAnimatedImageReceiver.setImage(ImageLocation.getForDocument(this.flagAnimatedDocument), "80_80", ImageLocation.getForDocument(this.flagDocument), "80_80", null, null, getEmojiThumb(r24), 0, null, null, 0);
    }

    private /* synthetic */ void lambda$setCodeEmoji$1(String r14, TLRPC.TL_messages_stickerSet r15) {
        TLRPC.Document r152 = findDocument(r15, r14);
        this.flagAnimatedDocument = r152;
        if (r152 != null) goto L5;
        return;
    L5:
        this.flagAnimatedImageReceiver.setImage(ImageLocation.getForDocument(r152), "80_80", ImageLocation.getForDocument(this.flagDocument), "80_80", null, null, getEmojiThumb(r14), 0, null, null, 0);
    }

    public void attachInternal() {
        this.attachedToWindow = true;
        if (this.isVideo == false) goto L6;
        this.flagAnimatedImageReceiver.onAttachedToWindow();
        return;
    L6:
        this.flagImageReceiver.onAttachedToWindow();
    }

    public void detachInternal() {
        this.attachedToWindow = false;
        this.flagImageReceiver.onDetachedFromWindow();
        this.flagAnimatedImageReceiver.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r1) {
        drawInternal(r1);
    }

    public void drawInternal(Canvas r14) {
        setupLayout();
        if (this.layout != null) goto L5;
        return;
    L5:
        RectF r0 = this.bounds;
        int r1 = this.padx;
        int r3 = this.pady;
        r0.set(r1, r3, r1 + this.w, r3 + this.h);
        RectF r02 = this.bounds;
        float r12 = this.h;
        r14.drawRoundRect(r02, r12 * 0.2f, r12 * 0.2f, this.outlinePaint);
        float r13 = 0.0f;
        if (this.hasFlag == false) goto L14;
        float r03 = this.animatedVideo.set(this.isVideo);
        if (r03 <= 0.0f) goto L11;
        ImageReceiver r5 = this.flagAnimatedImageReceiver;
        float r7 = this.padx;
        float r8 = this.padding.left + 2.25f;
        float r9 = this.density;
        r5.setImageCoords(r7 + (r8 * r9), this.pady + ((this.h - (r9 * 21.33f)) / 2.0f), r9 * 21.33f, r9 * 21.33f);
        r14.save();
        r14.scale(1.2f, 1.2f, this.flagAnimatedImageReceiver.getCenterX(), this.flagAnimatedImageReceiver.getCenterY());
        this.flagAnimatedImageReceiver.setAlpha(r03);
        this.flagAnimatedImageReceiver.draw(r14);
        r14.restore();
    L11:
        if (r03 >= 1.0f) goto L17;
        ImageReceiver r72 = this.flagImageReceiver;
        float r82 = this.padx;
        float r92 = this.padding.left + 2.25f;
        float r10 = this.density;
        r72.setImageCoords(r82 + (r92 * r10), this.pady + ((this.h - (r10 * 21.33f)) / 2.0f), r10 * 21.33f, r10 * 21.33f);
        r14.save();
        r14.scale(1.2f, 1.2f, this.flagImageReceiver.getCenterX(), this.flagImageReceiver.getCenterY());
        this.flagImageReceiver.setAlpha(1.0f - r03);
        this.flagImageReceiver.draw(r14);
        r14.restore();
    L17:
        r14.save();
        float r04 = this.padx;
        float r52 = this.padding.left;
        if (this.hasFlag == false) goto L20;
    L21:
        r13 = 2.25f;
    L22:
        r14.translate(r04 + ((((r52 + r13) + 21.33f) + 3.25f) * this.density), this.pady + (this.h / 2.0f));
        float r05 = this.textScale;
        r14.scale(r05, r05);
        r14.translate(-this.layoutLeft, (-this.layout.getHeight()) / 2.0f);
        this.layout.draw(r14);
        r14.restore();
        return;
    L20:
        if (this.forceEmoji == false) goto L22;
    L14:
        if (this.forceEmoji == true) goto L17;
        Drawable r06 = this.icon;
        int r53 = this.padx;
        float r6 = this.padding.left;
        float r73 = this.density;
        int r93 = this.pady;
        float r102 = this.h;
        r06.setBounds(((int) (r6 * r73)) + r53, ((int) ((r102 - (r73 * 21.33f)) / 2.0f)) + r93, r53 + ((int) ((r6 + 21.33f) * r73)), r93 + ((int) ((r102 + (r73 * 21.33f)) / 2.0f)));
        this.icon.draw(r14);
        goto L17
    }

    public void forceEmoji() {
        this.forceEmoji = true;
        this.relayout = true;
        requestLayout();
    }

    public TLRPC.Document getCodeEmojiDocument() {
        if (this.isVideo == false) goto L8;
        TLRPC.Document r0 = this.flagAnimatedDocument;
        if (r0 == null) goto L8;
        return r0;
    L8:
        return this.flagDocument;
    }

    public void getEmojiBounds(RectF r11) {
        int r0 = this.padx;
        float r2 = this.padding.left;
        float r5 = this.density;
        int r4 = this.pady;
        float r10 = this.h;
        r11.set(r0 + ((r2 + 2.25f) * r5), r4 + ((r10 - (r5 * 21.33f)) / 2.0f), r0 + (((r2 + 2.25f) + 21.33f) * r5), r4 + ((r10 + (r5 * 21.33f)) / 2.0f));
    }

    public int getHeightInternal() {
        return (this.pady + Math.round(this.h)) + this.pady;
    }

    public float getRadius() {
        return this.h * 0.2f;
    }

    public String getText() {
        return this.text;
    }

    public int getTypesCount() {
        return 4;
    }

    public int getWidthInternal() {
        return (this.padx + Math.round(this.w)) + this.padx;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        attachInternal();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        detachInternal();
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        setupLayout();
        setMeasuredDimension(getWidthInternal(), getHeightInternal());
    }

    public void setCodeEmoji(int r29, String r30) {
        if (TextUtils.isEmpty(r30) == false) goto L5;
        this.hasFlag = false;
        this.flagDocument = null;
        this.flagAnimatedDocument = null;
        this.flagImageReceiver.clearImage();
        this.flagAnimatedImageReceiver.clearImage();
    L6:
        this.relayout = true;
        requestLayout();
        return;
    L5:
        this.hasFlag = true;
        this.flagDocument = null;
        this.flagAnimatedDocument = null;
        TLRPC.TL_inputStickerSetShortName r4 = new TLRPC.TL_inputStickerSetShortName();
        r4.short_name = "StaticEmoji";
        MediaDataController.getInstance(r29).getStickerSet(r4, 0, false, new LocationMarker$$ExternalSyntheticLambda0(this, r30));
        TLRPC.TL_inputStickerSetShortName r42 = new TLRPC.TL_inputStickerSetShortName();
        r42.short_name = "RestrictedEmoji";
        MediaDataController.getInstance(r29).getStickerSet(r42, 0, false, new LocationMarker$$ExternalSyntheticLambda1(this, r30));
        this.flagImageReceiver.setImage(ImageLocation.getForDocument(this.flagDocument), "80_80", getEmojiThumb(r30), null, null, 0);
        this.flagAnimatedImageReceiver.setImage(ImageLocation.getForDocument(this.flagAnimatedDocument), "80_80", ImageLocation.getForDocument(this.flagDocument), "80_80", null, null, getEmojiThumb(r30), 0, null, null, 0);
        goto L6
    }

    public void setIsVideo(boolean r2) {
        if (this.isVideo != r2) goto L5;
    L10:
        this.isVideo = r2;
        invalidate();
        return;
    L5:
        if (this.attachedToWindow == false) goto L10;
        ImageReceiver r0 = this.flagImageReceiver;
        if (r2 == false) goto L9;
        r0.onDetachedFromWindow();
        this.flagAnimatedImageReceiver.onAttachedToWindow();
        goto L10
    L9:
        r0.onAttachedToWindow();
        this.flagAnimatedImageReceiver.onDetachedFromWindow();
        goto L10
    }

    public void setMaxWidth(int r1) {
        this.maxWidth = r1;
        this.relayout = true;
    }

    public void setText(String r1) {
        this.text = r1;
        this.relayout = true;
        requestLayout();
    }

    public void setType(int r6, int r7) {
        int r0 = -16777216;
        if (r6 != 0) goto L6;
        this.outlinePaint.setColor(-16777216);
        this.textPaint.setColor(-1);
        this.icon.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
    L16:
        invalidate();
        return;
    L6:
        if (r6 != 1) goto L8;
        this.outlinePaint.setColor(1275068416);
        this.textPaint.setColor(-1);
        this.icon.setColorFilter(null);
        goto L16
    L8:
        Paint r2 = this.outlinePaint;
        if (r6 != 2) goto L11;
        r2.setColor(-1);
        this.textPaint.setColor(-16777216);
        this.icon.setColorFilter(null);
        goto L16
    L11:
        r2.setColor(r7);
        if (AndroidUtilities.computePerceivedBrightness(r7) >= 0.721f) goto L15;
        r0 = -1;
    L15:
        this.textPaint.setColor(r0);
        this.icon.setColorFilter(new PorterDuffColorFilter(r0, PorterDuff.Mode.SRC_IN));
        goto L16
    }

    public void setupLayout() {
        if (this.relayout == true) goto L5;
        return;
    L5:
        float r1 = this.textPaint.measureText(this.text);
        int r2 = this.maxWidth;
        int r3 = this.padx;
        float r22 = (r2 - r3) - r3;
        RectF r32 = this.padding;
        float r4 = r32.left;
        float r6 = 2.25f;
        if (this.hasFlag == false) goto L8;
    L11:
        float r5 = 2.25f;
    L12:
        float r23 = r22 - (((((r4 + r5) + 21.33f) + 3.25f) + r32.right) * this.density);
        float r33 = Math.min(1.0f, r23 / r1);
        this.textScale = r33;
        if (r33 >= 0.4f) goto L15;
        String r10 = this.text;
        TextPaint r11 = this.textPaint;
        this.layout = new StaticLayout(r10, r11, HintView2.cutInFancyHalf(r10, r11), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
    L16:
        this.layoutWidth = 0.0f;
        this.layoutLeft = Float.MAX_VALUE;
        int r34 = 0;
    L18:
        if (r34 >= this.layout.getLineCount()) goto L21;
        this.layoutWidth = Math.max(this.layoutWidth, this.layout.getLineWidth(r34));
        this.layoutLeft = Math.min(this.layoutLeft, this.layout.getLineLeft(r34));
        r34 = r34 + 1;
        goto L18
    L21:
        if (this.layout.getLineCount() <= 2) goto L23;
        this.textScale = 0.3f;
    L24:
        RectF r24 = this.padding;
        float r35 = r24.left;
        if (this.hasFlag == false) goto L27;
    L30:
        float r36 = (((r35 + r6) + 21.33f) + 3.25f) + r24.right;
        float r42 = this.density;
        this.w = (r36 * r42) + (this.layoutWidth * this.textScale);
        this.h = ((r24.top + r24.bottom) * r42) + Math.max(r42 * 21.33f, this.layout.getHeight() * this.textScale);
        this.relayout = false;
        return;
    L27:
        if (this.forceEmoji == true) goto L30;
        r6 = 0.0f;
        goto L30
    L23:
        this.textScale = Math.min(1.0f, r23 / this.layoutWidth);
        goto L24
    L15:
        this.layout = new StaticLayout(this.text, this.textPaint, (int) Math.ceil(r1), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        goto L16
    L8:
        if (this.forceEmoji == true) goto L11;
        r5 = 0.0f;
        goto L12
    }
}
