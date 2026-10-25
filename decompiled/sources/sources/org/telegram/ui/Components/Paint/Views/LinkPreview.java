package org.telegram.ui.Components.Paint.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Text;

/* loaded from: classes7.dex */
public class LinkPreview extends View {
    private boolean animated;
    public int backgroundColor;
    private final RectF bounds;
    private final AnimatedFloat captionAbove;
    private int currentAccount;
    public final float density;
    private StaticLayout descriptionLayout;
    private float descriptionLayoutLeft;
    private float descriptionLayoutWidth;
    private final TextPaint descriptionPaint;
    private final float flagIconPadding;
    public float h;
    private boolean hasDescription;
    public boolean hasPhoto;
    private boolean hasSiteName;
    private boolean hasTitle;
    private final AnimatedFloat height;
    private final Drawable icon;
    private final float iconPadding;
    private final float iconSize;
    private StaticLayout layout;
    private float layoutLeft;
    private final TextPaint layoutPaint;
    private float layoutWidth;
    public int maxWidth;
    private boolean messageAbove;
    private Text messageText;
    private final Paint outlinePaint;
    private final RectF padding;
    public final int padx;
    public final int pady;
    private final Path path;
    private final Path path2;
    private final AnimatedFloat photoAlphaProgress;
    private float photoHeight;
    private final ImageReceiver photoImage;
    private final AnimatedFloat photoSmallProgress;
    private float previewHeight;
    private final AnimatedFloat previewHeightProgress;
    private Paint previewPaint;
    private final AnimatedFloat previewProgress;
    private final AnimatedFloat previewTheme;
    public int previewType;
    private final RectF rect;
    private final RectF rect1;
    private final RectF rect2;
    private boolean relayout;
    private Text siteNameText;
    private boolean smallPhoto;
    private float textScale;
    private Text titleText;
    public int type;
    private boolean video;
    public float w;
    private WebPagePreview webpage;
    private final AnimatedFloat width;

    public static class WebPagePreview extends TLObject {
        public boolean captionAbove;
        public int flags;
        public boolean largePhoto;
        public String name;
        public int photoSize;
        public String url;
        public TLRPC.WebPage webpage;

        public WebPagePreview() {
            this.captionAbove = true;
        }

        public static WebPagePreview TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-625858389) == r3) goto L5;
            WebPagePreview r0 = null;
        L7:
            return (WebPagePreview) TLObject.TLdeserialize(WebPagePreview.class, r0, r2, r3, r4);
        L5:
            r0 = new WebPagePreview();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            boolean r2 = false;
            if ((r0 & 8) == 0) goto L5;
            boolean r1 = true;
        L6:
            this.largePhoto = r1;
            if ((r0 & 16) == 0) goto L9;
            r2 = true;
        L9:
            this.captionAbove = r2;
            this.url = r5.readString(r6);
            if ((this.flags & 1) == 0) goto L13;
            this.webpage = TLRPC.WebPage.TLdeserialize(r5, r5.readInt32(r6), r6);
        L13:
            if ((this.flags & 2) == 0) goto L16;
            this.name = r5.readString(r6);
        L16:
            if ((this.flags & 4) == 0) goto L19;
            this.photoSize = r5.readInt32(r6);
            return;
        L19:
            return;
        L5:
            r1 = false;
            goto L6
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(-625858389);
            TLRPC.WebPage r0 = this.webpage;
            int r1 = this.flags;
            if (r0 == null) goto L5;
            int r02 = r1 | 1;
        L6:
            this.flags = r02;
            boolean r03 = TextUtils.isEmpty(this.name);
            int r12 = this.flags;
            if (r03 == true) goto L9;
            int r04 = r12 | 2;
        L10:
            this.flags = r04;
            if (this.largePhoto == false) goto L13;
            int r05 = r04 | 8;
        L14:
            this.flags = r05;
            if (this.captionAbove == false) goto L17;
            int r06 = r05 | 16;
        L18:
            this.flags = r06;
            r3.writeInt32(r06);
            r3.writeString(this.url);
            if ((this.flags & 1) == 0) goto L22;
            this.webpage.serializeToStream(r3);
        L22:
            if ((this.flags & 2) == 0) goto L25;
            r3.writeString(this.name);
        L25:
            if ((this.flags & 4) == 0) goto L28;
            r3.writeInt32(this.photoSize);
            return;
        L28:
            return;
        L17:
            r06 = r05 & (-17);
            goto L18
        L13:
            r05 = r04 & (-9);
            goto L14
        L9:
            r04 = r12 & (-3);
            goto L10
        L5:
            r02 = r1 & (-2);
            goto L6
        }
    }

    public LinkPreview(Context r13, float r14) {
        super(r13);
        this.relayout = true;
        this.textScale = 1.0f;
        TextPaint r9 = new TextPaint(1);
        this.layoutPaint = r9;
        this.padding = new RectF(4.0f, 4.33f, 7.66f, 3.0f);
        this.iconPadding = 3.25f;
        this.flagIconPadding = 2.25f;
        this.iconSize = 30.0f;
        this.outlinePaint = new Paint(1);
        this.previewPaint = new Paint(1);
        this.descriptionPaint = new TextPaint(1);
        ImageReceiver r11 = new ImageReceiver(this);
        this.photoImage = r11;
        this.bounds = new RectF();
        this.rect = new RectF();
        this.path = new Path();
        this.path2 = new Path();
        this.rect1 = new RectF();
        this.rect2 = new RectF();
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.captionAbove = new AnimatedFloat(this, 0, 350, r6);
        this.photoAlphaProgress = new AnimatedFloat(this, 0, 350, r6);
        this.photoSmallProgress = new AnimatedFloat(this, 0, 350, r6);
        this.previewProgress = new AnimatedFloat(this, 0, 350, r6);
        this.previewTheme = new AnimatedFloat(this, 0, 350, r6);
        this.previewHeightProgress = new AnimatedFloat(this, 0, 350, r6);
        this.width = new AnimatedFloat(this, 0, 350, r6);
        this.height = new AnimatedFloat(this, 0, 350, r6);
        this.density = r14;
        r11.setInvalidateAll(true);
        this.padx = (int) (r14 * 3.0f);
        this.pady = (int) (r14 * 1.0f);
        this.icon = r13.getResources().getDrawable(R.drawable.story_link).mutate();
        r9.setTextSize(24.0f * r14);
        r9.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_CONDENSED_BOLD));
    }

    public static String fromUrl(String r0) {
        return r0;
    }

    public static String fromUrlWithoutSchema(String r1) {
        if (r1.startsWith("https://") == true) goto L5;
        return r1;
    L5:
        return r1.substring(8);
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r1) {
        drawInternal(r1);
    }

    public void drawInternal(Canvas r27) {
        Canvas r2 = r27;
        setupLayout();
        float r7 = this.width.set(this.w);
        float r8 = this.height.set(this.h);
        AnimatedFloat r1 = this.previewTheme;
        if (this.previewType != 0) goto L5;
        boolean r3 = true;
    L6:
        float r9 = r1.set(r3);
        float r6 = this.previewProgress.set(withPreview());
        float r12 = AndroidUtilities.lerp(0.2f * r8, this.density * 16.66f, r6);
        RectF r32 = this.bounds;
        int r4 = this.padx;
        int r10 = this.pady;
        r32.set(r4, r10, r4 + r7, r10 + r8);
        this.outlinePaint.setColor(ColorUtils.blendARGB(this.backgroundColor, ColorUtils.blendARGB(-1, -14670807, r9), r6));
        this.path2.rewind();
        Path r33 = this.path2;
        RectF r42 = this.bounds;
        Path.Direction r11 = Path.Direction.CW;
        r33.addRoundRect(r42, r12, r12, r11);
        r2.drawPath(this.path2, this.outlinePaint);
        if (r6 <= 0.0f) goto L42;
        r2.save();
        r2.clipPath(this.path2);
        r2.translate(this.padx, this.pady);
        float r16 = this.captionAbove.set(this.messageAbove);
        float r13 = this.density;
        float r17 = (7.33f * r13) + 0.0f;
        Text r14 = this.messageText;
        if (r14 != null) goto L11;
    L14:
        float r21 = 0.0f;
        float r122 = r6;
    L13:
        float r34 = r17;
        float r15 = this.previewHeightProgress.set(this.previewHeight);
        this.previewPaint.setAlpha(25);
        RectF r43 = this.rect;
        float r5 = this.density;
        float r18 = r15 + r34;
        r43.set(r5 * 10.0f, r34, r7 - (r5 * 10.0f), r18);
        this.path.rewind();
        Path r44 = this.path;
        RectF r52 = this.rect;
        float r62 = this.density;
        float r22 = 255.0f;
        r44.addRoundRect(r52, r62 * 5.0f, r62 * 5.0f, r11);
        r2.drawPath(this.path, this.previewPaint);
        r2.save();
        r2.clipPath(this.path);
        this.previewPaint.setAlpha(255);
        float r45 = this.density;
        r27.drawRect(r45 * 10.0f, r34, r45 * 13.0f, r18, this.previewPaint);
        r27.restore();
        float r19 = this.density;
        float r132 = r34 + (5.66f * r19);
        if (this.hasSiteName == false) goto L20;
        Text r110 = this.siteNameText;
        if (r110 == null) goto L20;
        r6 = r122;
        r110.draw(r27, r19 * 20.0f, r132 + (r110.getHeight() / 2.0f), this.previewPaint.getColor(), r6);
        r132 = r132 + (this.siteNameText.getHeight() + (this.density * 2.66f));
    L22:
        if (this.hasTitle == false) goto L26;
        Text r111 = this.titleText;
        if (r111 == null) goto L26;
        r2 = r27;
        r111.draw(r2, this.density * 20.0f, r132 + (r111.getHeight() / 2.0f), ColorUtils.blendARGB(-13421773, -1, r9), r6);
        r132 = r132 + (this.titleText.getHeight() + (this.density * 2.66f));
    L28:
        if (this.hasDescription == true) goto L30;
    L32:
        float r112 = this.photoAlphaProgress.set(this.hasPhoto);
        if (r112 <= r21) goto L35;
        float r35 = this.photoSmallProgress.set(this.smallPhoto);
        RectF r46 = this.rect1;
        float r53 = this.density;
        float r23 = 1.0f;
        r46.set(r53 * 20.0f, (r53 * 2.66f) + r132, r7 - (20.0f * r53), ((r53 * 2.66f) + r132) + this.photoHeight);
        RectF r47 = this.rect2;
        float r54 = this.density;
        r47.set(((r7 - (r54 * 10.0f)) - (r54 * 6.0f)) - (r54 * 48.0f), (r54 * 6.0f) + r34, (r7 - (r54 * 10.0f)) - (r54 * 6.0f), ((6.0f * r54) + r34) + (r54 * 48.0f));
        AndroidUtilities.lerp(this.rect1, this.rect2, r35, this.rect);
        ImageReceiver r48 = this.photoImage;
        RectF r55 = this.rect;
        r48.setImageCoords(r55.left, r55.top, r55.width(), this.rect.height());
        this.photoImage.setAlpha(r112 * r6);
        this.photoImage.draw(r2);
        r132 = r132 + ((1.0f - r35) * ((this.density * 2.66f) + this.photoHeight));
    L36:
        float r113 = this.density;
        float r133 = (r132 + (7.0f * r113)) + (5.0f * r113);
        Text r114 = this.messageText;
        if (r114 != null) goto L39;
    L41:
        r2.restore();
    L44:
        if (r6 >= r23) goto L49;
        Drawable r115 = this.icon;
        int r36 = this.padx;
        float r49 = this.padding.left;
        float r56 = this.density;
        int r92 = this.pady;
        r115.setBounds(((int) (r49 * r56)) + r36, ((int) ((r8 - (r56 * 30.0f)) / 2.0f)) + r92, r36 + ((int) ((r49 + 30.0f) * r56)), r92 + ((int) (((r56 * 30.0f) + r8) / 2.0f)));
        int r37 = (int) ((r23 - r6) * r22);
        this.icon.setAlpha(r37);
        this.icon.draw(r2);
        if (this.layout == null) goto L50;
        r2.save();
        r2.translate(this.padx + (((this.padding.left + 30.0f) + 3.25f) * this.density), this.pady + (r8 / 2.0f));
        float r116 = this.textScale;
        r2.scale(r116, r116);
        r2.translate(-this.layoutLeft, (-this.layout.getHeight()) / 2.0f);
        this.layoutPaint.setAlpha(r37);
        this.layout.draw(r2);
        r2.restore();
        return;
    L50:
        return;
    L49:
        return;
    L39:
        if ((r23 - r16) <= r21) goto L41;
        r114.draw(r2, r113 * 10.0f, ((this.messageText.getHeight() + (this.density * 15.0f)) * r16) + (r133 + (r114.getHeight() / 2.0f)), -15033089, r6);
        this.messageText.getHeight();
        goto L41
    L35:
        r23 = 1.0f;
        goto L36
    L30:
        if (this.descriptionLayout == null) goto L32;
        r2.save();
        r2.translate((this.density * 20.0f) - this.descriptionLayoutLeft, r132);
        this.descriptionPaint.setColor(ColorUtils.blendARGB(-13421773, -1, r9));
        this.descriptionPaint.setAlpha((int) (r6 * 255.0f));
        this.descriptionLayout.draw(r2);
        r2.restore();
        r132 = r132 + (this.descriptionLayout.getHeight() + (this.density * 2.66f));
    L26:
        r2 = r27;
    L20:
        r6 = r122;
        goto L22
    L11:
        if (r16 <= 0.0f) goto L14;
        r21 = 0.0f;
        r14.draw(r2, r13 * 10.0f, (r17 + (r14.getHeight() / 2.0f)) - ((this.messageText.getHeight() + (this.density * 15.0f)) * (1.0f - r16)), -15033089, r6);
        r122 = r6;
        r17 = r17 + ((this.messageText.getHeight() + (this.density * 7.0f)) * r16);
        goto L13
    L42:
        r23 = 1.0f;
        r22 = 255.0f;
        goto L44
    L5:
        r3 = false;
        goto L6
    }

    public int getPhotoSide() {
        if (this.smallPhoto == false) goto L5;
        float r2 = 48.0f;
    L7:
        return ((int) r2) * 2;
    L5:
        int r0 = this.maxWidth;
        int r1 = this.padx;
        r2 = (((r0 - r1) - r1) / this.density) - 40.0f;
        goto L7
    }

    public int getPreviewType() {
        return this.previewType;
    }

    public float getRadius() {
        if (withPreview() == false) goto L7;
        float r0 = 16.66f;
        float r1 = this.density;
    L6:
        return r1 * r0;
    L7:
        r0 = 0.2f;
        r1 = this.h;
        goto L6
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.photoImage.onAttachedToWindow();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.photoImage.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onMeasure(int r3, int r4) {
        setupLayout();
        setMeasuredDimension((this.padx + ((int) Math.ceil(this.w))) + this.padx, (this.pady + ((int) Math.ceil(this.h))) + this.pady);
    }

    public void pushPhotoToCache() {
        if (this.hasPhoto == true) goto L6;
        return;
    L6:
        if (this.photoImage.hasImageLoaded() == true) goto L8;
        return;
    L8:
        if (this.photoImage.getBitmap() == null) goto L14;
        ImageLoader.getInstance().putImageToCache(new BitmapDrawable(this.photoImage.getBitmap()), this.photoImage.getImageKey(), false);
        return;
    }

    public void set(int r2, WebPagePreview r3) {
        set(r2, r3, false);
    }

    public void set(int r1, WebPagePreview r2, boolean r3) {
        this.currentAccount = r1;
        if (this.webpage != r2) goto L7;
        if (r3 == true) goto L7;
        return;
    L7:
        this.webpage = r2;
        this.relayout = true;
        this.animated = r3;
        requestLayout();
    }

    public void setMaxWidth(int r1) {
        this.maxWidth = r1;
        this.relayout = true;
    }

    public void setPreviewType(int r1) {
        this.previewType = r1;
        invalidate();
    }

    public void setType(int r4, int r5) {
        if (this.type != 1) goto L5;
        return;
    L5:
        int r0 = -16777216;
        if (r4 != 0) goto L12;
        this.backgroundColor = r5;
        if (AndroidUtilities.computePerceivedBrightness(r5) >= 0.721f) goto L11;
        r0 = -1;
    L11:
        this.layoutPaint.setColor(r0);
        this.icon.setColorFilter(new PorterDuffColorFilter(r0, PorterDuff.Mode.SRC_IN));
    L18:
        invalidate();
        return;
    L12:
        if (r4 != 1) goto L15;
        this.backgroundColor = -16777216;
        this.layoutPaint.setColor(-1);
        this.icon.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        goto L18
    L15:
        if (r4 != 2) goto L17;
        this.backgroundColor = 1275068416;
        this.layoutPaint.setColor(-1);
        this.icon.setColorFilter(null);
        goto L18
    L17:
        this.backgroundColor = -1;
        this.layoutPaint.setColor(-13397548);
        this.icon.setColorFilter(new PorterDuffColorFilter(-13397548, PorterDuff.Mode.SRC_IN));
        goto L18
    }

    public void setVideoTexture() {
        this.video = true;
    }

    public void setupLayout() {
        if (this.relayout == true) goto L5;
        return;
    L5:
        if (this.webpage == null) goto L164;
        boolean r1 = withPreview();
        WebPagePreview r2 = this.webpage;
        if (r1 == false) goto L139;
        boolean r12 = TextUtils.isEmpty(r2.name);
        WebPagePreview r22 = this.webpage;
        if (r12 == false) goto L12;
        String r13 = fromUrl(r22.url);
    L13:
        TLRPC.WebPage r23 = this.webpage.webpage;
        int r9 = this.maxWidth;
        int r10 = this.padx;
        float r92 = (r9 - r10) - r10;
        this.h = 0.0f;
        this.w = 0.0f;
        this.previewHeight = 0.0f;
        int r102 = UserObject.getColorId(UserConfig.getInstance(this.currentAccount).getCurrentUser());
        MessagesController.PeerColors r11 = MessagesController.getInstance(this.currentAccount).peerColors;
        String r122 = null;
        if (r11 != null) goto L16;
    L19:
        MessagesController.PeerColor r112 = null;
    L20:
        Paint r132 = this.previewPaint;
        if (r112 != null) goto L23;
        int[] r113 = Theme.keys_avatar_nameInMessage;
        int r103 = Theme.getColor(r113[r102 % r113.length]);
    L24:
        r132.setColor(r103);
        this.h += this.density * 7.33f;
        this.messageAbove = this.webpage.captionAbove;
        Text r14 = new Text(r13, 16.0f).setTextSizePx(this.density * 16.0f).setMaxWidth(r92 - (this.density * 20.0f));
        this.messageText = r14;
        this.w = Math.max(this.w, Math.min(r14.getCurrentWidth() + (this.density * 20.0f), r92));
        this.h = (this.h + this.messageText.getHeight()) + (this.density * 7.0f);
        if (r23.photo == null) goto L27;
    L30:
        boolean r15 = true;
    L31:
        this.hasPhoto = r15;
        WebPagePreview r16 = this.webpage;
        boolean r104 = r16.largePhoto;
        this.smallPhoto = !r104;
        if (this.video == true) goto L34;
    L36:
        if (r104 == true) goto L38;
        float r17 = 48.0f;
    L39:
        int r18 = ((int) r17) * 2;
    L40:
        this.photoImage.setRoundRadius((int) (this.density * 4.0f));
        TLRPC.Photo r4 = r23.photo;
        if (r4 == null) goto L56;
        TLRPC.PhotoSize r42 = FileLoader.getClosestPhotoSizeWithSize(r4.sizes, 1, false, null, false);
        float r162 = 7.0f;
        float r172 = 48.0f;
        TLRPC.PhotoSize r114 = FileLoader.getClosestPhotoSizeWithSize(r23.photo.sizes, (int) (r18 * this.density), false, r42, false);
        if (r114 == null) goto L46;
        int r133 = r114.w;
        int r142 = r114.h;
    L45:
        float r28 = 40.0f;
        ImageReceiver r152 = this.photoImage;
        ImageLocation r19 = ImageLocation.getForPhoto(r114, r23.photo);
        String r20 = r18 + "_" + r18;
        if (this.video == false) goto L50;
        ImageLocation r21 = null;
    L52:
        if (this.video == true) goto L55;
        r122 = r18 + "_" + r18;
    L55:
        r152.setImage(r19, r20, r21, r122, 0, null, null, 0);
    L72:
        this.previewHeight += this.density * 5.66f;
        boolean r110 = TextUtils.isEmpty(r23.site_name);
        this.hasSiteName = !r110;
        if (r110 == true) goto L87;
        Text r111 = new Text(r23.site_name, 14.0f, AndroidUtilities.bold()).setTextSizePx(this.density * 14.0f);
        float r115 = this.density;
        float r123 = r92 - (r115 * r28);
        if (this.hasPhoto == true) goto L77;
    L79:
        float r116 = 0.0f;
    L80:
        Text r117 = r111.setMaxWidth((int) Math.ceil(r123 - r116));
        this.siteNameText = r117;
        float r118 = this.w;
        float r119 = r117.getCurrentWidth();
        float r124 = this.density;
        float r120 = r119 + (r124 * r28);
        if (this.hasPhoto == true) goto L83;
    L85:
        float r125 = 0.0f;
    L86:
        this.w = Math.max(r118, Math.min(r120 + r125, r92));
        this.previewHeight = (this.previewHeight + this.siteNameText.getHeight()) + (this.density * 2.66f);
        int r121 = this.siteNameText.getLineCount();
    L88:
        boolean r1110 = TextUtils.isEmpty(r23.title);
        this.hasTitle = !r1110;
        if (r1110 == true) goto L103;
        Text r1111 = new Text(r23.title, 14.0f, AndroidUtilities.bold()).setTextSizePx(this.density * 14.0f);
        float r126 = this.density;
        float r153 = r92 - (r126 * r28);
        float r182 = 2.66f;
        if (this.hasPhoto == true) goto L93;
    L95:
        float r127 = 0.0f;
    L96:
        Text r7 = r1111.setMaxWidth((int) Math.ceil(r153 - r127));
        this.titleText = r7;
        float r8 = this.w;
        float r72 = r7.getCurrentWidth();
        float r1112 = this.density;
        float r73 = r72 + (r1112 * r28);
        if (this.hasPhoto == true) goto L99;
    L101:
        float r1113 = 0.0f;
    L102:
        this.w = Math.max(r8, Math.min(r73 + r1113, r92));
        this.previewHeight = (this.previewHeight + this.titleText.getHeight()) + (this.density * 2.66f);
        r121 = r121 + this.titleText.getLineCount();
    L104:
        boolean r43 = TextUtils.isEmpty(r23.description);
        this.hasDescription = !r43;
        if (r43 == true) goto L129;
        this.descriptionPaint.setTextSize(this.density * 14.0f);
        String r24 = r23.description;
        TextPaint r44 = this.descriptionPaint;
        int r74 = (int) Math.ceil(Math.max(1.0f, r92 - (this.density * r28)));
        if (this.hasPhoto == true) goto L109;
    L111:
        int r82 = 0;
    L112:
        int r232 = 3 - r121;
        this.descriptionLayout = ChatMessageCell.generateStaticLayout(r24, r44, r74, (int) Math.ceil(Math.max(1.0f, r92 - ((40 + r82) * this.density))), r232, 4);
        this.descriptionLayoutWidth = 0.0f;
        this.descriptionLayoutLeft = Float.MAX_VALUE;
        int r128 = 0;
    L114:
        if (r128 >= this.descriptionLayout.getLineCount()) goto L127;
        if (this.hasPhoto == true) goto L118;
    L121:
        boolean r45 = false;
    L122:
        float r5 = this.descriptionLayoutWidth;
        float r75 = this.descriptionLayout.getLineWidth(r128);
        if (r45 == false) goto L125;
        float r46 = this.density * r172;
    L126:
        this.descriptionLayoutWidth = Math.max(r5, r75 + r46);
        this.descriptionLayoutLeft = Math.min(this.descriptionLayoutLeft, this.descriptionLayout.getLineLeft(r128));
        r128 = r128 + 1;
        goto L114
    L125:
        r46 = 0.0f;
        goto L126
    L118:
        if (this.smallPhoto == false) goto L121;
        if (r128 >= r232) goto L121;
        r45 = true;
        goto L122
    L127:
        this.w = Math.max(this.w, Math.min(this.descriptionLayoutWidth + (this.density * r28), r92));
        this.previewHeight = (this.previewHeight + this.descriptionLayout.getHeight()) + (this.density * r182);
        goto L129
    L109:
        if (this.smallPhoto == false) goto L111;
        r82 = 60;
    L129:
        if (this.hasPhoto == true) goto L131;
    L138:
        float r129 = this.previewHeight;
        float r25 = this.density;
        float r130 = r129 + (r25 * r162);
        this.previewHeight = r130;
        this.h = (this.h + r130) + (r25 * 11.0f);
    L153:
        if (this.animated == true) goto L156;
        this.captionAbove.set(this.messageAbove, true);
        this.photoSmallProgress.set(this.smallPhoto, true);
        this.photoAlphaProgress.set(this.hasPhoto, true);
        this.previewHeightProgress.set(this.previewHeight, true);
    L157:
        this.relayout = false;
        return;
    L156:
        invalidate();
        goto L157
    L131:
        if (this.smallPhoto == true) goto L138;
        if (r133 <= 0) goto L136;
        if (r142 <= 0) goto L136;
        this.photoHeight = Math.min((Math.max(0.0f, this.w - (this.density * r28)) / r133) * r142, this.density * 200.0f);
    L137:
        this.previewHeight = (this.previewHeight + this.photoHeight) + (this.density * r182);
    L136:
        this.photoHeight = this.density * 120.0f;
        goto L137
    L99:
        if (this.smallPhoto == false) goto L101;
        r1113 = r1112 * 60.0f;
        goto L102
    L93:
        if (this.smallPhoto == false) goto L95;
        r127 = r126 * 60.0f;
        goto L96
    L103:
        r182 = 2.66f;
        goto L104
    L83:
        if (this.smallPhoto == false) goto L85;
        r125 = r124 * 60.0f;
        goto L86
    L77:
        if (this.smallPhoto == false) goto L79;
        r116 = r115 * 60.0f;
        goto L80
    L87:
        r121 = 0;
        goto L88
    L50:
        r21 = ImageLocation.getForPhoto(r42, r23.photo);
        goto L52
    L46:
        r133 = 0;
        r142 = 0;
        goto L45
    L56:
        r162 = 7.0f;
        r28 = 40.0f;
        r172 = 48.0f;
        TLRPC.Document r3 = r23.document;
        if (r3 == null) goto L71;
        TLRPC.PhotoSize r32 = FileLoader.getClosestPhotoSizeWithSize(r3.thumbs, 1, false, null, false);
        TLRPC.PhotoSize r47 = FileLoader.getClosestPhotoSizeWithSize(r23.document.thumbs, (int) (r18 * this.density), false, r32, false);
        if (r47 == null) goto L61;
        int r1114 = r47.w;
        r142 = r47.h;
        r133 = r1114;
    L62:
        ImageReceiver r1115 = this.photoImage;
        ImageLocation r192 = ImageLocation.getForDocument(r47, r23.document);
        String r202 = r18 + "_" + r18;
        if (this.video == false) goto L65;
        ImageLocation r212 = null;
    L67:
        if (this.video == true) goto L70;
        r122 = r18 + "_" + r18;
    L70:
        r1115.setImage(r192, r202, r212, r122, 0, null, null, 0);
        goto L72
    L65:
        r212 = ImageLocation.getForDocument(r32, r23.document);
        goto L67
    L61:
        r133 = 0;
        r142 = 0;
        goto L62
    L71:
        r133 = 0;
        r142 = 0;
        goto L72
    L38:
        r17 = (r92 / this.density) - 40.0f;
        goto L39
    L34:
        if ((r16.flags & 4) == 0) goto L36;
        r18 = r16.photoSize;
        goto L40
    L27:
        if (MessageObject.isVideoDocument(r23.document) == true) goto L30;
        r15 = false;
        goto L31
    L23:
        r103 = r112.getColor1();
        goto L24
    L16:
        if (r102 < 7) goto L19;
        r112 = r11.getColor(r102);
        goto L20
    L12:
        r13 = r22.name;
        goto L13
    L139:
        boolean r131 = TextUtils.isEmpty(r2.name);
        WebPagePreview r26 = this.webpage;
        if (r131 == false) goto L142;
        String r134 = fromUrlWithoutSchema(r26.url).toUpperCase();
    L143:
        int r27 = this.maxWidth;
        int r76 = this.padx;
        float r29 = (r27 - r76) - r76;
        RectF r77 = this.padding;
        float r210 = r29 - ((((r77.left + 30.0f) + 3.25f) + r77.right) * this.density);
        this.textScale = 1.0f;
        this.layout = new StaticLayout(TextUtils.ellipsize(r134, this.layoutPaint, (int) Math.ceil(r14), TextUtils.TruncateAt.END), this.layoutPaint, (int) Math.ceil(r210), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.layoutWidth = 0.0f;
        this.layoutLeft = Float.MAX_VALUE;
        int r135 = 0;
    L145:
        if (r135 >= this.layout.getLineCount()) goto L148;
        this.layoutWidth = Math.max(this.layoutWidth, this.layout.getLineWidth(r135));
        this.layoutLeft = Math.min(this.layoutLeft, this.layout.getLineLeft(r135));
        r135 = r135 + 1;
        goto L145
    L148:
        if (this.layout.getLineCount() <= 2) goto L150;
        this.textScale = 0.3f;
    L151:
        RectF r136 = this.padding;
        float r211 = ((r136.left + 30.0f) + 3.25f) + r136.right;
        float r48 = this.density;
        this.w = (r211 * r48) + (this.layoutWidth * this.textScale);
        this.h = ((r136.top + r136.bottom) * r48) + Math.max(r48 * 30.0f, this.layout.getHeight() * this.textScale);
        goto L153
    L150:
        this.textScale = Math.min(1.0f, r210 / this.layoutWidth);
        goto L151
    L142:
        r134 = r26.name;
        goto L143
    }

    public boolean withPreview() {
        WebPagePreview r0 = this.webpage;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.webpage == null) goto L10;
        return true;
    L10:
        return false;
    }
}
