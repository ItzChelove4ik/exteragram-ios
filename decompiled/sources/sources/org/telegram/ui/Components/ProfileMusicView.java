package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes7.dex */
public class ProfileMusicView extends View {
    private final Paint arrowPaint;
    private final Path arrowPath;
    private Text author;
    private ProfileActivity.AvatarImageView avatarView;
    private int backgroundColor;
    private final Paint backgroundPaint;
    private final ButtonBounce bounce;
    private final Path clipPath;
    private float currentHeight;
    private final PorterDuffColorFilter filterColorBlack;
    private final PorterDuffColorFilter filterColorWhite;
    private final Drawable icon;
    private final Paint iconPaint;
    private boolean ignoreRect;
    private float parentExpanded;
    private final RectF rect;
    private RenderNode renderNode;
    private float renderNodeScale;
    private float renderNodeTranslateY;
    private final Theme.ResourcesProvider resourcesProvider;
    private final Paint strokePaint;
    private int textColor;
    private Text title;
    private boolean withShadows;

    public ProfileMusicView(Context r6, Theme.ResourcesProvider r7) {
        super(r6);
        PorterDuff.Mode r1 = PorterDuff.Mode.SRC_IN;
        this.filterColorWhite = new PorterDuffColorFilter(-1, r1);
        this.filterColorBlack = new PorterDuffColorFilter(-16777216, r1);
        this.iconPaint = new Paint();
        Paint r0 = new Paint();
        this.arrowPaint = r0;
        Path r12 = new Path();
        this.arrowPath = r12;
        this.rect = new RectF();
        this.backgroundPaint = new Paint(1);
        this.strokePaint = new Paint(1);
        this.clipPath = new Path();
        this.bounce = new ButtonBounce(this);
        this.textColor = -1;
        this.ignoreRect = false;
        this.resourcesProvider = r7;
        this.icon = r6.getResources().getDrawable(R.drawable.files_music).mutate();
        r0.setStyle(Paint.Style.STROKE);
        r0.setStrokeCap(Paint.Cap.ROUND);
        r0.setStrokeJoin(Paint.Join.ROUND);
        r12.moveTo(0.0f, -AndroidUtilities.dpf2(3.33f));
        r12.lineTo(AndroidUtilities.dpf2(3.16f), 0.0f);
        r12.lineTo(0.0f, AndroidUtilities.dpf2(3.33f));
        setColor(null);
        setText("Author", " - Title");
    }

    private void checkTextColor() {
        if (this.parentExpanded < 0.8f) goto L5;
    L7:
        boolean r0 = false;
    L8:
        if (r0 == false) goto L10;
        int r2 = -16777216;
    L11:
        this.textColor = r2;
        Drawable r22 = this.icon;
        if (r0 == false) goto L14;
        PorterDuffColorFilter r02 = this.filterColorBlack;
    L15:
        r22.setColorFilter(r02);
        this.iconPaint.setColor(this.textColor);
        this.arrowPaint.setColor(Theme.multAlpha(this.textColor, 0.85f));
        invalidate();
        return;
    L14:
        r02 = this.filterColorWhite;
        goto L15
    L10:
        r2 = -1;
        goto L11
    L5:
        if (AndroidUtilities.computePerceivedBrightness(this.backgroundColor) <= 0.85f) goto L7;
        r0 = true;
        goto L8
    }

    public static CharSequence getAuthor(TLRPC.Document r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r4.attributes.size()) goto L15;
        TLRPC.DocumentAttribute r2 = r4.attributes.get(r1);
        if ((r2 instanceof TLRPC.TL_documentAttributeAudio) == false) goto L14;
        if (r2.voice == true) goto L14;
        return r2.performer;
    L14:
        r1 = r1 + 1;
        goto L7
    L15:
        return null;
    }

    public static CharSequence getTitle(TLRPC.Document r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r4.attributes.size()) goto L19;
        TLRPC.DocumentAttribute r2 = r4.attributes.get(r1);
        if ((r2 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L10;
        r1 = r1 + 1;
        goto L7
    L10:
        String r0 = r2.title;
        if (r0 == null) goto L17;
        if (r0.length() == 0) goto L17;
        return r0;
    L17:
        return FileLoader.getDocumentFileName(r4);
    L19:
        String r42 = FileLoader.getDocumentFileName(r4);
        if (TextUtils.isEmpty(r42) == true) goto L22;
        return r42;
    L22:
        return null;
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent r4) {
        if (Utilities.clamp01(this.currentHeight / AndroidUtilities.dp(21.0f)) > 0.0f) goto L6;
        return false;
    L6:
        if (r4.getAction() != 0) goto L9;
        this.bounce.setPressed(this.rect.contains(r4.getX(), r4.getY()));
    L25:
        return this.bounce.isPressed();
    L9:
        if (r4.getAction() != 2) goto L16;
        if (this.bounce.isPressed() == false) goto L16;
        if (this.rect.contains(r4.getX(), r4.getY()) == true) goto L25;
        this.bounce.setPressed(false);
    L16:
        if (r4.getAction() != 3) goto L19;
        this.bounce.setPressed(false);
        goto L25
    L19:
        if (r4.getAction() != 1) goto L25;
        if (this.bounce.isPressed() == false) goto L23;
        performClick();
    L23:
        this.bounce.setPressed(false);
        goto L25
    }

    public void drawingBlur(RenderNode r2, ProfileActivity.AvatarImageView r3, float r4, float r5) {
        this.ignoreRect = false;
        this.renderNode = r2;
        this.avatarView = r3;
        this.renderNodeScale = r4;
        this.renderNodeTranslateY = r5;
        invalidate();
    }

    public void drawingBlur(boolean r2) {
        if (this.ignoreRect == r2) goto L5;
    L8:
        this.ignoreRect = r2;
        this.renderNode = null;
        this.avatarView = null;
        invalidate();
        return;
    L5:
        if (this.renderNode != null) goto L8;
    }

    @Override // android.view.View
    public void onDraw(Canvas r15) {
        if (this.author != null) goto L5;
        return;
    L5:
        if (this.title == null) goto L33;
        float r6 = Utilities.clamp01(this.currentHeight / AndroidUtilities.dp(21.0f));
        float r0 = this.bounce.getScale(0.02f);
        if (r6 <= 0.0f) goto L34;
        int r2 = getWidth() - (AndroidUtilities.dp(12.0f) * 2);
        this.author.ellipsize((r2 - AndroidUtilities.dp(35.0f)) / 2.0f);
        this.title.ellipsize((r2 - this.author.getWidth()) - AndroidUtilities.dp(35.0f));
        float r22 = ((AndroidUtilities.dp(16.6f) + this.author.getWidth()) + this.title.getWidth()) + AndroidUtilities.dp(8.0f);
        float r3 = AndroidUtilities.dp(16.0f) + r22;
        r15.save();
        r15.scale(r0, r0, getWidth() / 2.0f, getHeight() / 2.0f);
        this.rect.set((getWidth() - r3) / 2.0f, AndroidUtilities.dp(10.0f), (getWidth() + r3) / 2.0f, AndroidUtilities.dp(10.0f) + (AndroidUtilities.dp(17.0f) * r6));
        if (this.withShadows == true) goto L13;
    L15:
        this.backgroundPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
    L16:
        int r02 = this.backgroundPaint.getAlpha();
        this.backgroundPaint.setAlpha((int) (r02 * r6));
        if (this.withShadows == true) goto L19;
    L21:
        RectF r4 = this.rect;
        r15.drawRoundRect(r4, r4.height() / 2.0f, this.rect.height() / 2.0f, this.backgroundPaint);
        this.backgroundPaint.setAlpha(r02);
        this.clipPath.rewind();
        Path r03 = this.clipPath;
        RectF r42 = this.rect;
        r03.addRoundRect(r42, r42.height() / 2.0f, this.rect.height() / 2.0f, Path.Direction.CW);
        r15.save();
        r15.clipPath(this.clipPath);
        if (this.ignoreRect == false) goto L24;
    L30:
        r15.translate((getWidth() - r22) / 2.0f, 0.0f);
        float r43 = getHeight() / 2.0f;
        AndroidUtilities.dp(6.0f);
        AndroidUtilities.dp(2.0f);
        int r04 = AndroidUtilities.dp(13.0f);
        int r5 = (int) r43;
        int r8 = r04 / 2;
        this.icon.setBounds(0, r5 - r8, r04, r5 + r8);
        this.icon.draw(r15);
        r15.translate(AndroidUtilities.dp(16.6f), 0.0f);
        this.author.draw(r15, 0.0f, r43, this.textColor, r6);
        r15.translate(this.author.getWidth(), 0.0f);
        this.title.draw(r15, 0.0f, r43, this.textColor, r6 * 0.85f);
        r15.translate(this.title.getWidth(), 0.0f);
        this.arrowPaint.setStrokeWidth(AndroidUtilities.dpf2(1.16f));
        r15.translate(AndroidUtilities.dpf2(4.8f), r43);
        r15.drawPath(this.arrowPath, this.arrowPaint);
        r15.restore();
        r15.restore();
        return;
    L24:
        if (this.renderNode == null) goto L30;
        if (Build.VERSION.SDK_INT < 29) goto L30;
        if (r15.isHardwareAccelerated() == false) goto L30;
        r15.save();
        r15.translate(0.0f, this.renderNodeTranslateY);
        float r05 = this.renderNodeScale;
        r15.scale(r05, r05);
        r15.drawRenderNode(this.renderNode);
        r15.restore();
        goto L30
    L19:
        if (SharedConfig.shadowsInSections == false) goto L21;
        RectF r44 = this.rect;
        r15.drawRoundRect(r44, r44.height() / 2.0f, this.rect.height() / 2.0f, this.strokePaint);
        goto L21
    L13:
        if (SharedConfig.shadowsInSections == false) goto L15;
        this.backgroundPaint.setShadowLayer(AndroidUtilities.dpf2(2.0f), 0.0f, AndroidUtilities.dpf2(0.33f), Theme.multAlpha(167772160, r6));
        this.strokePaint.setShadowLayer(AndroidUtilities.dpf2(0.33f), 0.0f, 0.0f, Theme.multAlpha(201326592, r6));
        this.strokePaint.setColor(0);
        goto L16
    L34:
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(37.0f), TLObject.FLAG_30));
    }

    public void setColor(MessagesController.PeerColor r3) {
        if (r3 != null) goto L4;
        int r0 = Theme.getColor(Theme.key_actionBarDefault, this.resourcesProvider);
        int r1 = r0;
    L5:
        if (r3 != null) goto L7;
        this.backgroundColor = Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider);
        this.withShadows = true;
    L8:
        this.backgroundPaint.setColor(this.backgroundColor);
        checkTextColor();
        return;
    L7:
        this.backgroundColor = Theme.adaptHSV(ColorUtils.blendARGB(r0, r1, 0.15f), 0.04f, -0.09f);
        this.withShadows = false;
        goto L8
    L4:
        r0 = r3.getBgColor1(Theme.isCurrentThemeDark());
        r1 = r3.getBgColor2(Theme.isCurrentThemeDark());
        goto L5
    }

    public void setMusicDocument(TLRPC.Document r5) {
        CharSequence r0 = getAuthor(r5);
        CharSequence r52 = getTitle(r5);
        if (TextUtils.isEmpty(r0) == false) goto L9;
        if (TextUtils.isEmpty(r52) == false) goto L7;
        r0 = LocaleController.getString(R.string.AudioUnknownArtist);
        r52 = " - " + LocaleController.getString(R.string.AudioUnknownTitle);
    L12:
        setText(r0, r52);
        return;
    L7:
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L12
    L9:
        if (TextUtils.isEmpty(r52) == true) goto L11;
        r52 = " - " + r52;
        goto L12
    L11:
        r52 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L12
    }

    public void setParentExpanded(float r2) {
        if (this.parentExpanded == r2) goto L6;
        this.parentExpanded = r2;
        checkTextColor();
        invalidate();
        return;
    }

    public void setText(CharSequence r4, CharSequence r5) {
        this.author = new Text(r4, 11.0f, AndroidUtilities.bold());
        this.title = new Text(r5, 11.0f);
        setContentDescription(LocaleController.getString(R.string.AccDescrProfileMusic) + " " + r4 + " — " + r5);
    }

    public void updatePosition(float r1, float r2) {
        this.currentHeight = r2;
        setTranslationY(r1 - AndroidUtilities.dp(12.0f));
        invalidate();
    }
}
