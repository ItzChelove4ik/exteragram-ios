package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Paint.Views.EditTextOutline;

/* loaded from: classes7.dex */
public class PaintingOverlay extends FrameLayout {
    private Drawable backgroundDrawable;
    public boolean drawChildren;
    private boolean ignoreLayout;
    private HashMap<View, VideoEditedInfo.MediaEntity> mediaEntityViews;
    private Bitmap paintBitmap;

    /* renamed from: org.telegram.ui.Components.PaintingOverlay$1, reason: invalid class name */
    public class AnonymousClass1 extends EditTextOutline {
        final /* synthetic */ PaintingOverlay this$0;

        public AnonymousClass1(PaintingOverlay r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextEffects, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            return false;
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r1) {
            return false;
        }
    }

    public static /* synthetic */ void $r8$lambda$c7Lu5wMs_GxYiPSrH1J8Mubbub4(ImageReceiver r0, boolean r1, boolean r2, boolean r3) {
        if (r1 == false) goto L8;
        if (r2 == true) goto L9;
        RLottieDrawable r02 = r0.getLottieAnimation();
        if (r02 == null) goto L10;
        r02.start();
        return;
    L10:
        return;
    L9:
        return;
    }

    public PaintingOverlay(Context r1) {
        super(r1);
        this.drawChildren = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r1) {
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r2, View r3, long r4) {
        if (this.drawChildren == true) goto L7;
        return false;
    L7:
        return super.drawChild(r2, r3, r4);
    }

    public Bitmap getBitmap() {
        return this.paintBitmap;
    }

    public Bitmap getThumb() {
        float r0 = getMeasuredWidth();
        float r1 = getMeasuredHeight();
        float r2 = Math.max(r0 / AndroidUtilities.dp(120.0f), r1 / AndroidUtilities.dp(120.0f));
        Bitmap r02 = Bitmap.createBitmap((int) (r0 / r2), (int) (r1 / r2), Bitmap.Config.ARGB_8888);
        Canvas r12 = new Canvas(r02);
        float r3 = 1.0f / r2;
        r12.scale(r3, r3);
        draw(r12);
        return r02;
    }

    public void hideBitmap() {
        setBackground(null);
    }

    public void hideEntities() {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        getChildAt(r1).setVisibility(4);
        r1 = r1 + 1;
        goto L3
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r1) {
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r8, int r9, int r10, int r11, int r12) {
        if (this.mediaEntityViews == null) goto L19;
        int r82 = getMeasuredWidth();
        int r92 = getMeasuredHeight();
        int r102 = getChildCount();
        int r112 = 0;
    L5:
        if (r112 >= r102) goto L23;
        View r122 = getChildAt(r112);
        VideoEditedInfo.MediaEntity r0 = this.mediaEntityViews.get(r122);
        if (r0 == null) goto L18;
        int r1 = r122.getMeasuredWidth();
        int r2 = r122.getMeasuredHeight();
        if ((r122 instanceof EditTextOutline) == true) goto L12;
        int r3 = (int) (r82 * r0.x);
        int r02 = (int) (r92 * r0.y);
    L17:
        r122.layout(r3, r02, r1 + r3, r2 + r02);
        goto L18
    L12:
        if (r0.customTextView == false) goto L15;
        r3 = ((int) (r82 * (r0.x + (r0.width / 2.0f)))) - (r122.getMeasuredWidth() / 2);
        int r03 = (int) (r92 * (r0.y + (r0.height / 2.0f)));
        int r4 = r122.getMeasuredHeight() / 2;
    L14:
        r02 = r03 - r4;
        goto L17
    L15:
        r3 = ((int) (r82 * r0.textViewX)) - (r122.getMeasuredWidth() / 2);
        r03 = (int) (r92 * r0.textViewY);
        r4 = r122.getMeasuredHeight() / 2;
    L18:
        r112 = r112 + 1;
        goto L5
    L23:
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r9, int r10) {
        this.ignoreLayout = true;
        setMeasuredDimension(View.MeasureSpec.getSize(r9), View.MeasureSpec.getSize(r10));
        if (this.mediaEntityViews == null) goto L18;
        int r92 = getMeasuredWidth();
        int r0 = getMeasuredHeight();
        int r1 = getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L18;
        View r3 = getChildAt(r2);
        VideoEditedInfo.MediaEntity r4 = this.mediaEntityViews.get(r3);
        if (r4 == null) goto L17;
        if ((r3 instanceof EditTextOutline) == false) goto L16;
        r3.measure(View.MeasureSpec.makeMeasureSpec(r4.viewWidth, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
        if (r4.customTextView == false) goto L14;
        float r5 = (r4.width * getMeasuredWidth()) / r4.viewWidth;
    L15:
        r3.setScaleX(r5);
        r3.setScaleY(r5);
        goto L17
    L14:
        r5 = r4.scale * ((r4.textViewWidth * r92) / r4.viewWidth);
        goto L15
    L16:
        r3.measure(View.MeasureSpec.makeMeasureSpec((int) (r92 * r4.width), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (r0 * r4.height), TLObject.FLAG_30));
    L17:
        r2 = r2 + 1;
    L18:
        this.ignoreLayout = false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r1) {
        return false;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void reset() {
        this.paintBitmap = null;
        this.backgroundDrawable = null;
        setBackground(null);
        HashMap<View, VideoEditedInfo.MediaEntity> r0 = this.mediaEntityViews;
        if (r0 == null) goto L5;
        r0.clear();
    L5:
        removeAllViews();
    }

    @Override // android.view.View
    public void setAlpha(float r5) {
        super.setAlpha(r5);
        Drawable r0 = this.backgroundDrawable;
        if (r0 == null) goto L5;
        r0.setAlpha((int) (255.0f * r5));
    L5:
        int r02 = getChildCount();
        int r1 = 0;
    L6:
        if (r1 >= r02) goto L13;
        View r2 = getChildAt(r1);
        if (r2 == null) goto L12;
        if (r2.getParent() != this) goto L12;
        r2.setAlpha(r5);
    L12:
        r1 = r1 + 1;
        goto L6
    }

    public void setBitmap(Bitmap r2) {
        this.paintBitmap = r2;
        BitmapDrawable r0 = new BitmapDrawable(r2);
        this.backgroundDrawable = r0;
        setBackground(r0);
    }

    public void setData(String r1, ArrayList<VideoEditedInfo.MediaEntity> r2, boolean r3, boolean r4, boolean r5) {
        setEntities(r2, r3, r4, r5);
        if (r1 == null) goto L6;
        this.paintBitmap = BitmapFactory.decodeFile(r1);
        BitmapDrawable r12 = new BitmapDrawable(this.paintBitmap);
        this.backgroundDrawable = r12;
        setBackground(r12);
        return;
    L6:
        this.paintBitmap = null;
        this.backgroundDrawable = null;
        setBackground(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v2, types: [android.view.View, android.widget.TextView, org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, org.telegram.ui.Components.Paint.Views.EditTextOutline, org.telegram.ui.Components.PaintingOverlay$1] */
    public void setEntities(ArrayList<VideoEditedInfo.MediaEntity> r24, boolean r25, boolean r26, boolean r27) {
        setClipChildren(r27);
        reset();
        this.mediaEntityViews = new HashMap();
        if (r24 != null) goto L5;
        return;
    L5:
        if (r24.isEmpty() == true) goto L80;
        int r2 = r24.size();
        int r4 = 0;
    L7:
        if (r4 >= r2) goto L81;
        VideoEditedInfo.MediaEntity r5 = r24.get(r4);
        byte r6 = r5.type;
        if (r6 != 0) goto L19;
        BackupImageView r62 = new BackupImageView(getContext());
        r62.setLayerNum(12);
        r62.setAspectFit(true);
        ImageReceiver r10 = r62.getImageReceiver();
        if (r25 == false) goto L15;
        r10.setAllowDecodeSingleFrame(true);
        r10.setAllowStartLottieAnimation(false);
        if (r26 == false) goto L15;
        r10.setDelegate(new PaintingOverlay$$ExternalSyntheticLambda0());
    L15:
        r10.setImage(ImageLocation.getForDocument(r5.document), null, null, null, ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r5.document.thumbs, 90), r5.document), null, null, 0, "webp", r5.parentObject, 1);
        if ((2 & r5.subType) == 0) goto L18;
        r62.setScaleX(-1.0f);
    L18:
        r5.view = r62;
        BackupImageView r63 = r62;
    L71:
        if (r63 == null) goto L73;
        addView(r63);
        r63.setRotation((float) (((-r5.rotation) / 3.141592653589793d) * 180.0d));
        this.mediaEntityViews.put(r63, r5);
    L73:
        r4 = r4 + 1;
        goto L7
    L19:
        if (r6 != 1) goto L70;
        ?? r64 = new AnonymousClass1(this, getContext());
        r64.setBackgroundColor(0);
        r64.setPadding(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f));
        r64.setTextSize(0, r5.fontSize);
        r64.setTypeface(r5.textTypeface.getTypeface());
        SpannableString r9 = new SpannableString(Emoji.replaceEmoji(r5.text, r64.getPaint().getFontMetricsInt(), false));
        ArrayList<VideoEditedInfo.EmojiEntity> r102 = r5.entities;
        int r11 = r102.size();
        int r12 = 0;
    L21:
        if (r12 >= r11) goto L23;
        VideoEditedInfo.EmojiEntity r13 = r102.get(r12);
        r12 = r12 + 1;
        VideoEditedInfo.EmojiEntity r132 = r13;
        AnimatedEmojiSpan r14 = new AnimatedEmojiSpan(r132.document_id, r64.getPaint().getFontMetricsInt());
        int r7 = r132.offset;
        r9.setSpan(r14, r7, r132.length + r7, 33);
        goto L21
    L23:
        Emoji.EmojiSpan[] r72 = (Emoji.EmojiSpan[]) r9.getSpans(0, r9.length(), Emoji.EmojiSpan.class);
        if (r72 == null) goto L29;
        int r8 = 0;
    L27:
        if (r8 >= r72.length) goto L29;
        r72[r8].scale = 0.85f;
        r8 = r8 + 1;
    L29:
        r64.setText(r9);
        int r73 = 17;
        r64.setGravity(17);
        int r82 = r5.textAlign;
        if (r82 == 1) goto L35;
        int r15 = 2;
        if (r82 == 2) goto L34;
        r73 = 19;
    L36:
        r64.setGravity(r73);
        int r74 = r5.textAlign;
        if (r74 == 1) goto L48;
        int r17 = 3;
        if (r74 == r15) goto L46;
        if (LocaleController.isRTL == true) goto L49;
    L43:
        r17 = 2;
    L49:
        r64.setTextAlignment(r17);
        r64.setHorizontallyScrolling(false);
        r64.setImeOptions(268435456);
        r64.setFocusableInTouchMode(true);
        r64.setEnabled(false);
        r64.setInputType(r64.getInputType() | 16384);
        r64.setBreakStrategy(0);
        r64.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        int r75 = r5.color;
        byte r83 = r5.subType;
        int r92 = -1;
        if (r83 != 0) goto L56;
        r64.setFrameColor(r75);
        if (AndroidUtilities.computePerceivedBrightness(r5.color) < 0.721f) goto L54;
        r75 = -16777216;
    L69:
        r64.setTextColor(r75);
        r64.setCursorColor(r75);
        r64.setHandlesColor(r75);
        r64.setHighlightColor(Theme.multAlpha(r75, 0.4f));
        r5.view = r64;
        r63 = r64;
        goto L71
    L54:
        r75 = -1;
        goto L69
    L56:
        if (r83 != 1) goto L63;
        if (AndroidUtilities.computePerceivedBrightness(r75) < 0.25f) goto L60;
        int r84 = -1728053248;
    L61:
        r64.setFrameColor(r84);
        goto L69
    L60:
        r84 = -1711276033;
        goto L61
    L63:
        if (r83 == 2) goto L65;
        r64.setFrameColor(0);
        goto L69
    L65:
        if (AndroidUtilities.computePerceivedBrightness(r75) < 0.25f) goto L67;
        r92 = -16777216;
    L67:
        r64.setFrameColor(r92);
        goto L69
    L46:
        if (LocaleController.isRTL == false) goto L49;
    L48:
        r17 = 4;
        goto L49
    L34:
        r73 = 21;
        goto L36
    L35:
        r15 = 2;
        goto L36
    L70:
        r63 = null;
        goto L71
    L81:
        return;
    }

    public void showAll() {
        int r0 = getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        getChildAt(r2).setVisibility(0);
        r2 = r2 + 1;
        goto L3
    L5:
        setBackground(this.backgroundDrawable);
    }
}
