package org.telegram.messenger.video;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.video.MediaCodecVideoConvertor;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFileDrawable;
import org.telegram.ui.Components.Paint.PaintTypeface;
import org.telegram.ui.Components.Paint.Views.EditTextOutline;
import org.telegram.ui.Components.RLottieNative;

/* loaded from: classes6.dex */
public class WebmEncoder {

    public static class FrameDrawer {
        private final int H;
        private final int W;
        private final Paint bitmapPaint;
        private final Paint clearPaint;
        private final Path clipPath;
        private final int fps;
        private final ArrayList<VideoEditedInfo.MediaEntity> mediaEntities;
        Path path;
        private final Bitmap photo;
        Paint textColorPaint;
        Paint xRefPaint;

        /* renamed from: org.telegram.messenger.video.WebmEncoder$FrameDrawer$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatedEmojiSpan {
            final /* synthetic */ FrameDrawer this$0;
            final /* synthetic */ VideoEditedInfo.EmojiEntity val$e;
            final /* synthetic */ EditTextOutline val$editText;
            final /* synthetic */ VideoEditedInfo.MediaEntity val$entity;

            public AnonymousClass1(FrameDrawer r1, long r2, float r4, Paint.FontMetricsInt r5, VideoEditedInfo.MediaEntity r6, EditTextOutline r7, VideoEditedInfo.EmojiEntity r8) {
                this.this$0 = r1;
                this.val$entity = r6;
                this.val$editText = r7;
                this.val$e = r8;
                super(r2, r4, r5);
            }

            @Override // org.telegram.ui.Components.AnimatedEmojiSpan, android.text.style.ReplacementSpan
            public void draw(Canvas r5, CharSequence r6, int r7, int r8, float r9, int r10, int r11, int r12, Paint r13) {
                super.draw(r5, r6, r7, r8, r9, r10, r11, r12, r13);
                VideoEditedInfo.MediaEntity r72 = this.val$entity;
                float r52 = this.val$entity.x + ((((this.val$editText.getPaddingLeft() + r9) + (this.measuredSize / 2.0f)) / r72.viewWidth) * r72.width);
                float r62 = r72.y;
                VideoEditedInfo.MediaEntity r92 = this.val$entity;
                float r73 = ((this.val$editText.getPaddingTop() + r10) + ((r12 - r10) / 2.0f)) / r92.viewHeight;
                float r102 = r92.height;
                float r63 = r62 + (r73 * r102);
                if (r92.rotation == 0.0f) goto L5;
                float r74 = r92.x + (r92.width / 2.0f);
                float r93 = r92.y + (r102 / 2.0f);
                float r103 = FrameDrawer.m5267$$Nest$fgetW(this.this$0) / FrameDrawer.m5266$$Nest$fgetH(this.this$0);
                double r112 = r52 - r74;
                double r53 = (r63 - r93) / r103;
                float r75 = r74 + ((float) ((Math.cos(-this.val$entity.rotation) * r112) - (Math.sin(-this.val$entity.rotation) * r53)));
                r63 = (((float) ((r112 * Math.sin(-this.val$entity.rotation)) + (r53 * Math.cos(-this.val$entity.rotation)))) * r103) + r93;
                r52 = r75;
            L5:
                VideoEditedInfo.MediaEntity r76 = this.val$e.entity;
                int r94 = this.measuredSize;
                VideoEditedInfo.MediaEntity r113 = this.val$entity;
                float r104 = (r94 / r113.viewWidth) * r113.width;
                r76.width = r104;
                float r95 = (r94 / r113.viewHeight) * r113.height;
                r76.height = r95;
                r76.x = r52 - (r104 / 2.0f);
                r76.y = r63 - (r95 / 2.0f);
                r76.rotation = r113.rotation;
                if (r76.bitmap != null) goto L9;
                FrameDrawer.m5268$$Nest$minitStickerEntity(this.this$0, r76);
                return;
            }
        }

        /* renamed from: -$$Nest$fgetH, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5266$$Nest$fgetH(FrameDrawer r0) {
            return r0.H;
        }

        /* renamed from: -$$Nest$fgetW, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5267$$Nest$fgetW(FrameDrawer r0) {
            return r0.W;
        }

        /* renamed from: -$$Nest$minitStickerEntity, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5268$$Nest$minitStickerEntity(FrameDrawer r0, VideoEditedInfo.MediaEntity r1) {
            r0.initStickerEntity(r1);
        }

        public FrameDrawer(MediaCodecVideoConvertor.ConvertVideoParams r11) {
            ArrayList<VideoEditedInfo.MediaEntity> r0 = new ArrayList();
            this.mediaEntities = r0;
            this.clearPaint = new Paint(1);
            this.bitmapPaint = new Paint(5);
            int r1 = r11.resultWidth;
            this.W = r1;
            int r4 = r11.resultHeight;
            this.H = r4;
            this.fps = r11.framerate;
            Path r5 = new Path();
            this.clipPath = r5;
            r5.addRoundRect(new RectF(0.0f, 0.0f, r1, r4), r1 * 0.125f, r4 * 0.125f, Path.Direction.CW);
            this.photo = BitmapFactory.decodeFile(r11.videoPath);
            r0.addAll(r11.mediaEntities);
            int r112 = r0.size();
            int r02 = 0;
        L3:
            if (r02 >= r112) goto L14;
            VideoEditedInfo.MediaEntity r12 = this.mediaEntities.get(r02);
            byte r42 = r12.type;
            if (r42 != 0) goto L7;
        L12:
            initStickerEntity(r12);
        L13:
            r02 = r02 + 1;
            goto L3
        L7:
            if (r42 == 2) goto L12;
            if (r42 == 5) goto L12;
            if (r42 != 1) goto L13;
            initTextEntity(r12);
            goto L13
        L14:
            this.clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }

        private void applyRoundRadius(VideoEditedInfo.MediaEntity r8, Bitmap r9, int r10) {
            if (r9 == null) goto L26;
            if (r8 != null) goto L5;
            return;
        L5:
            if (r8.roundRadius != 0.0f) goto L9;
            if (r10 != 0) goto L9;
            return;
        L9:
            if (r8.roundRadiusCanvas != null) goto L12;
            r8.roundRadiusCanvas = new Canvas(r9);
        L12:
            if (r8.roundRadius != 0.0f) goto L14;
        L20:
            if (r10 != 0) goto L22;
            return;
        L22:
            if (this.textColorPaint != null) goto L24;
            Paint r0 = new Paint(1);
            this.textColorPaint = r0;
            r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        L24:
            this.textColorPaint.setColor(r10);
            r8.roundRadiusCanvas.drawRect(0.0f, 0.0f, r9.getWidth(), r9.getHeight(), this.textColorPaint);
            return;
        L14:
            if (this.path != null) goto L17;
            this.path = new Path();
        L17:
            if (this.xRefPaint != null) goto L19;
            Paint r02 = new Paint(1);
            this.xRefPaint = r02;
            r02.setColor(-16777216);
            this.xRefPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        L19:
            float r03 = Math.min(r9.getWidth(), r9.getHeight()) * r8.roundRadius;
            this.path.rewind();
            this.path.addRoundRect(new RectF(0.0f, 0.0f, r9.getWidth(), r9.getHeight()), r03, r03, Path.Direction.CCW);
            this.path.toggleInverseFillType();
            r8.roundRadiusCanvas.drawPath(this.path, this.xRefPaint);
            goto L20
        }

        private void drawEntity(Canvas r9, VideoEditedInfo.MediaEntity r10, int r11, long r12) {
            RLottieNative r0 = r10.lottieNative;
            int r1 = 0;
            if (r0 == null) goto L20;
            Bitmap r122 = r10.bitmap;
            if (r122 != null) goto L7;
            return;
        L7:
            if (r10.W > 0) goto L9;
            return;
        L9:
            if (r10.H <= 0) goto L53;
            r0.getFrame((int) r10.currentFrame, r122, true);
            Bitmap r123 = r10.bitmap;
            if ((r10.subType & 8) != 0) goto L15;
            r11 = 0;
        L15:
            applyRoundRadius(r10, r123, r11);
            r9.drawBitmap(r10.bitmap, r10.matrix, this.bitmapPaint);
            float r8 = r10.currentFrame + r10.framesPerDraw;
            r10.currentFrame = r8;
            if (r8 < r10.lottieNative.getFrameCount()) goto L49;
            r10.currentFrame = 0.0f;
            return;
        L49:
            return;
        L53:
            return;
        L20:
            if (r10.animatedFileDrawable == null) goto L29;
            float r112 = r10.currentFrame;
            int r124 = (int) r112;
            float r113 = r112 + r10.framesPerDraw;
            r10.currentFrame = r113;
            int r114 = (int) r113;
        L22:
            AnimatedFileDrawable r13 = r10.animatedFileDrawable;
            if (r124 == r114) goto L25;
            r13.getNextFrame(true);
            r114 = r114 - 1;
            goto L22
        L25:
            Bitmap r115 = r13.getBackgroundBitmap();
            if (r115 == null) goto L50;
            r9.drawBitmap(r115, r10.matrix, this.bitmapPaint);
            return;
        L50:
            return;
        L29:
            r9.drawBitmap(r10.bitmap, r10.matrix, this.bitmapPaint);
            ArrayList<VideoEditedInfo.EmojiEntity> r116 = r10.entities;
            if (r116 != null) goto L32;
            return;
        L32:
            if (r116.isEmpty() == false) goto L34;
            return;
        L34:
            if (r1 >= r10.entities.size()) goto L54;
            VideoEditedInfo.EmojiEntity r117 = r10.entities.get(r1);
            if (r117 != null) goto L38;
        L37:
            FrameDrawer r2 = this;
            Canvas r3 = r9;
            long r6 = r12;
        L42:
            r1 = r1 + 1;
            FrameDrawer this = r2;
            r9 = r3;
            r12 = r6;
            goto L34
        L38:
            VideoEditedInfo.MediaEntity r4 = r117.entity;
            if (r4 == null) goto L37;
            r2 = this;
            r3 = r9;
            r6 = r12;
            r2.drawEntity(r3, r4, r10.color, r6);
            goto L42
        }

        private void initStickerEntity(VideoEditedInfo.MediaEntity r23) {
            int r2 = (int) (r23.width * this.W);
            r23.W = r2;
            int r3 = (int) (r23.height * this.H);
            r23.H = r3;
            if (r2 <= 512) goto L5;
            r23.H = (int) ((r3 / r2) * 512.0f);
            r23.W = 512;
        L5:
            int r22 = r23.H;
            if (r22 <= 512) goto L8;
            r23.W = (int) ((r23.W / r22) * 512.0f);
            r23.H = 512;
        L8:
            byte r24 = r23.subType;
            if ((r24 & 1) == 0) goto L22;
            int r25 = r23.W;
            if (r25 <= 0) goto L20;
            int r32 = r23.H;
            if (r32 <= 0) goto L50;
            r23.bitmap = Bitmap.createBitmap(r25, r32, Bitmap.Config.ARGB_8888);
            RLottieNative r26 = RLottieNative.createFromFile(r23.text);
            r23.lottieNative = r26;
            if (r26 == null) goto L18;
            float r27 = r26.getFps() / this.fps;
        L19:
            r23.framesPerDraw = r27;
        L48:
            setupMatrix(r23);
            return;
        L18:
            r27 = 0.0f;
            goto L19
        L50:
            return;
        L20:
            return;
        L22:
            if ((r24 & 4) == 0) goto L26;
            r23.looped = false;
            r23.animatedFileDrawable = new AnimatedFileDrawable(new File(r23.text), true, 0, 0, null, null, null, 0, UserConfig.selectedAccount, true, 512, 512, null);
            r23.framesPerDraw = r6.getFps() / this.fps;
            r23.currentFrame = 1.0f;
            r23.animatedFileDrawable.getNextFrame(true);
            if (r23.type != 5) goto L48;
            r23.firstSeek = true;
            goto L48
        L26:
            String r28 = r23.text;
            if (TextUtils.isEmpty(r23.segmentedPath) == false) goto L29;
        L31:
            BitmapFactory.Options r6 = new BitmapFactory.Options();
            if (r23.type != 2) goto L34;
            r6.inMutable = true;
        L34:
            Bitmap r29 = BitmapFactory.decodeFile(r28, r6);
            r23.bitmap = r29;
            if (r23.type != 2) goto L41;
            if (r29 == null) goto L41;
            r23.roundRadius = AndroidUtilities.dp(12.0f) / Math.min(r23.viewWidth, r23.viewHeight);
            Pair<Integer, Integer> r210 = AndroidUtilities.getImageOrientation(r23.text);
            r23.rotation = (float) (r23.rotation - Math.toRadians(((Integer) r210.first).intValue()));
            if (((((Integer) r210.first).intValue() / 90) % 2) != 1) goto L40;
            float r211 = r23.x;
            float r4 = r23.width;
            float r212 = r211 + (r4 / 2.0f);
            float r5 = r23.y;
            float r62 = r23.height;
            float r52 = r5 + (r62 / 2.0f);
            int r8 = this.W;
            int r9 = this.H;
            float r42 = (r4 * r8) / r9;
            float r63 = (r62 * r9) / r8;
            r23.width = r63;
            r23.height = r42;
            r23.x = r212 - (r63 / 2.0f);
            r23.y = r52 - (r42 / 2.0f);
        L40:
            applyRoundRadius(r23, r23.bitmap, 0);
        L41:
            if (r29 == null) goto L48;
            float r213 = r29.getWidth() / r23.bitmap.getHeight();
            if (r213 <= 1.0f) goto L46;
            float r33 = r23.height;
            float r214 = r33 / r213;
            r23.y += (r33 - r214) / 2.0f;
            r23.height = r214;
            goto L48
        L46:
            if (r213 >= 1.0f) goto L48;
            float r34 = r23.width;
            float r215 = r213 * r34;
            r23.x += (r34 - r215) / 2.0f;
            r23.width = r215;
            goto L48
        L29:
            if ((r23.subType & 16) == 0) goto L31;
            r28 = r23.segmentedPath;
            goto L31
        }

        private void initTextEntity(VideoEditedInfo.MediaEntity r16) {
            EditTextOutline r7 = new EditTextOutline(ApplicationLoader.applicationContext);
            r7.getPaint().setAntiAlias(true);
            r7.drawAnimatedEmojiDrawables = false;
            r7.setBackgroundColor(0);
            r7.setPadding(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f));
            PaintTypeface r0 = r16.textTypeface;
            if (r0 == null) goto L7;
            Typeface r02 = r0.getTypeface();
            if (r02 == null) goto L7;
            r7.setTypeface(r02);
        L7:
            r7.setTextSize(0, r16.fontSize);
            SpannableString r11 = new SpannableString(r16.text);
            ArrayList<VideoEditedInfo.EmojiEntity> r12 = r16.entities;
            int r13 = r12.size();
            int r03 = 0;
        L8:
            if (r03 >= r13) goto L13;
            int r14 = r03 + 1;
            VideoEditedInfo.EmojiEntity r8 = r12.get(r03);
            if (r8.documentAbsolutePath == null) goto L11;
            VideoEditedInfo.MediaEntity r04 = new VideoEditedInfo.MediaEntity();
            r8.entity = r04;
            r04.text = r8.documentAbsolutePath;
            r04.subType = r8.subType;
            AnonymousClass1 r05 = new AnonymousClass1(this, 0, 1.0f, r7.getPaint().getFontMetricsInt(), r16, r7, r8);
            int r2 = r8.offset;
            r11.setSpan(r05, r2, r8.length + r2, 33);
        L11:
            r03 = r14;
            goto L8
        L13:
            CharSequence r06 = Emoji.replaceEmoji(r11, r7.getPaint().getFontMetricsInt(), false);
            if ((r06 instanceof Spanned) == false) goto L21;
            Emoji.EmojiSpan[] r22 = (Emoji.EmojiSpan[]) ((Spanned) r06).getSpans(0, r06.length(), Emoji.EmojiSpan.class);
            if (r22 == null) goto L21;
            int r3 = 0;
        L19:
            if (r3 >= r22.length) goto L21;
            r22[r3].scale = 0.85f;
            r3 = r3 + 1;
        L21:
            r7.setText(r06);
            r7.setTextColor(r16.color);
            int r07 = r16.textAlign;
            if (r07 == 1) goto L26;
            if (r07 == 2) goto L25;
            int r08 = 19;
        L27:
            r7.setGravity(r08);
            int r09 = r16.textAlign;
            if (r09 == 1) goto L37;
            if (r09 == 2) goto L35;
            if (LocaleController.isRTL == false) goto L33;
        L32:
            int r010 = 3;
        L38:
            r7.setTextAlignment(r010);
            r7.setHorizontallyScrolling(false);
            r7.setImeOptions(268435456);
            r7.setFocusableInTouchMode(true);
            r7.setInputType(r7.getInputType() | 16384);
            setBreakStrategy(r7);
            byte r011 = r16.subType;
            int r4 = -1;
            if (r011 != 0) goto L45;
            r7.setFrameColor(r16.color);
            if (AndroidUtilities.computePerceivedBrightness(r16.color) < 0.721f) goto L43;
            r4 = -16777216;
        L43:
            r7.setTextColor(r4);
        L58:
            r7.measure(View.MeasureSpec.makeMeasureSpec(r16.viewWidth, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r16.viewHeight, TLObject.FLAG_30));
            r7.layout(0, 0, r16.viewWidth, r16.viewHeight);
            r16.bitmap = Bitmap.createBitmap(r16.viewWidth, r16.viewHeight, Bitmap.Config.ARGB_8888);
            r7.draw(new Canvas(r16.bitmap));
            setupMatrix(r16);
            return;
        L45:
            if (r011 == 1) goto L47;
            if (r011 == 2) goto L53;
            if (r011 != 3) goto L58;
            r7.setFrameColor(0);
            r7.setTextColor(r16.color);
            goto L58
        L53:
            if (AndroidUtilities.computePerceivedBrightness(r16.color) < 0.25f) goto L55;
            r4 = -16777216;
        L55:
            r7.setFrameColor(r4);
            r7.setTextColor(r16.color);
            goto L58
        L47:
            if (AndroidUtilities.computePerceivedBrightness(r16.color) < 0.25f) goto L49;
            int r012 = -1728053248;
        L50:
            r7.setFrameColor(r012);
            r7.setTextColor(r16.color);
            goto L58
        L49:
            r012 = -1711276033;
        L33:
            r010 = 2;
            goto L38
        L35:
            if (LocaleController.isRTL == false) goto L32;
        L37:
            r010 = 4;
            goto L38
        L25:
            r08 = 21;
            goto L27
        L26:
            r08 = 17;
            goto L27
        }

        private void setupMatrix(VideoEditedInfo.MediaEntity r6) {
            r6.matrix = new Matrix();
            Bitmap r0 = r6.bitmap;
            if (r0 != null) goto L8;
            AnimatedFileDrawable r1 = r6.animatedFileDrawable;
            if (r1 == null) goto L8;
            r0 = r1.getBackgroundBitmap();
        L8:
            if (r0 == null) goto L11;
            r6.matrix.postScale(1.0f / r0.getWidth(), 1.0f / r0.getHeight());
        L11:
            if (r6.type != 1) goto L13;
        L15:
            r6.matrix.postScale(r6.width * this.W, r6.height * this.H);
            r6.matrix.postTranslate(r6.x * this.W, r6.y * this.H);
            r6.matrix.postRotate((float) (((-r6.rotation) / 3.141592653589793d) * 180.0d), (r6.x + (r6.width / 2.0f)) * this.W, (r6.y + (r6.height / 2.0f)) * this.H);
            return;
        L13:
            if ((r6.subType & 2) == 0) goto L15;
            r6.matrix.postScale(-1.0f, 1.0f, 0.5f, 0.5f);
            goto L15
        }

        public void draw(Canvas r13, int r14) {
            r13.drawPaint(this.clearPaint);
            r13.save();
            r13.clipPath(this.clipPath);
            Bitmap r0 = this.photo;
            if (r0 == null) goto L5;
            r13.drawBitmap(r0, 0.0f, 0.0f, null);
        L5:
            long r10 = r14 * (1000000000 / this.fps);
            int r142 = this.mediaEntities.size();
            int r02 = 0;
        L6:
            if (r02 >= r142) goto L8;
            VideoEditedInfo.MediaEntity r8 = this.mediaEntities.get(r02);
            drawEntity(r13, r8, r8.color, r10);
            r02 = r02 + 1;
            goto L6
        L8:
            r13.restore();
        }

        public void setBreakStrategy(EditTextOutline r1) {
            r1.setBreakStrategy(0);
        }
    }

    public WebmEncoder() {
    }

    public static boolean convert(MediaCodecVideoConvertor.ConvertVideoParams r20, int r21) {
        int r4 = r20.resultWidth;
        int r5 = r20.resultHeight;
        long r6 = createEncoder(r20.cacheFile.getAbsolutePath(), r4, r5, r20.framerate, r20.bitrate);
        boolean r3 = true;
        if (r6 != 0) goto L5;
        return true;
    L5:
        Bitmap r10 = null;
        r10 = Bitmap.createBitmap(r4, r5, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L14 Exception -> L16
        ByteBuffer r0 = ByteBuffer.allocateDirect(r10.getByteCount());     // Catch: Throwable -> L28 Exception -> L39
        Canvas r11 = new Canvas(r10);     // Catch: Throwable -> L28 Exception -> L39
        FrameDrawer r12 = new FrameDrawer(r20);     // Catch: Throwable -> L28 Exception -> L39
        int r8 = (int) Math.ceil(r20.framerate * (r20.duration / 1000.0d));     // Catch: Throwable -> L28 Exception -> L39
        int r13 = 0;
    L8:
        if (r13 >= r8) goto L41;
        r12.draw(r11, r13);     // Catch: Throwable -> L28 Exception -> L39
        r10.copyPixelsToBuffer(r0);     // Catch: Throwable -> L28 Exception -> L39
        r0.flip();     // Catch: Throwable -> L28 Exception -> L39
        if (writeFrame(r6, r0, r4, r5) == false) goto L11;
        MediaController.VideoConvertorListener r14 = r20.callback;     // Catch: Throwable -> L28 Exception -> L39
        if (r14 == null) goto L32;
        boolean r17 = r3;
        Bitmap r18 = r10;
        int r19 = r4;
        r14.didWriteData(Math.min(261120, r20.cacheFile.length()), r13 / r8);     // Catch: Throwable -> L24 Exception -> L26
    L33:
        if ((r13 % 3) != 0) goto L38;
        MediaController.VideoConvertorListener r32 = r20.callback;     // Catch: Throwable -> L24 Exception -> L26
        if (r32 == null) goto L38;
        r32.checkConversionCanceled();     // Catch: Throwable -> L24 Exception -> L26
    L38:
        r13 = r13 + 1;
        r3 = r17;
        r10 = r18;
        r4 = r19;
        goto L8
    L26:
        e = e;
        r10 = r18;
    L42:
        FileLog.e(e);     // Catch: Throwable -> L14
        stop(r6);
        if (r10 == null) goto L46;
        r10.recycle();
    L46:
        boolean r9 = r17;
    L47:
        long r33 = r20.cacheFile.length();
        if (r21 > 0) goto L50;
    L53:
        MediaController.VideoConvertorListener r02 = r20.callback;
        if (r02 == null) goto L56;
        r02.didWriteData(r33, 1.0f);
    L56:
        FileLog.d("webm encoded to " + r20.cacheFile + " with size=" + r33 + " triesLeft=" + r21);
        return r9;
    L50:
        if (r33 <= 261120) goto L53;
        int r03 = r20.bitrate;
        r20.bitrate = (int) (r03 * ((261120.0f / r33) * 0.9f));
        r20.cacheFile.delete();
        FileLog.d("webm encoded too much, got " + r33 + ", old bitrate = " + r03 + " new bitrate = " + r20.bitrate);
        return convert(r20, r21 - 1);
    L24:
        th = th;
        r10 = r18;
    L58:
        stop(r6);
        if (r10 == null) goto L61;
        r10.recycle();
    L61:
        throw th;
    L30:
        e = e;
        goto L42
    L32:
        r17 = r3;
        r19 = r4;
        r18 = r10;
        goto L33
    L11:
        FileLog.d("webm writeFile error at " + r13 + "/" + r8);     // Catch: Throwable -> L14 Exception -> L16
        stop(r6);
        r10.recycle();
        return r3;
    L41:
        stop(r6);
        r10.recycle();
        r9 = false;
    L39:
        e = e;
        r17 = r3;
    L28:
        th = th;
    L14:
        th = th;
    L16:
        e = e;
        r17 = r3;
        goto L42
    }

    private static native long createEncoder(String r0, int r1, int r2, int r3, long r4);

    public static native void stop(long r0);

    private static native boolean writeFrame(long r0, ByteBuffer r2, int r3, int r4);
}
