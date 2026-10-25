package org.telegram.messenger.video;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Surface;
import android.view.View;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Bitmaps;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.video.MediaCodecVideoConvertor;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFileDrawable;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextEffects;
import org.telegram.ui.Components.FilterShaders;
import org.telegram.ui.Components.Paint.PaintTypeface;
import org.telegram.ui.Components.Paint.Views.EditTextOutline;
import org.telegram.ui.Components.Paint.Views.LinkPreview;
import org.telegram.ui.Components.Paint.Views.LocationMarker;
import org.telegram.ui.Components.RLottieNative;
import org.telegram.ui.Stories.recorder.PreviewView;
import org.telegram.ui.Stories.recorder.StoryEntry;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class TextureRenderer {
    private static final String FRAGMENT_EXTERNAL_MASK_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision highp float;\nvarying vec2 vTextureCoord;\nvarying vec2 MTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform sampler2D sMask;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord) * texture2D(sMask, MTextureCoord).a;\n}\n";
    private static final String FRAGMENT_EXTERNAL_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision highp float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);}\n";
    private static final String FRAGMENT_MASK_SHADER = "precision highp float;\nvarying vec2 vTextureCoord;\nvarying vec2 MTextureCoord;\nuniform sampler2D sTexture;\nuniform sampler2D sMask;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord) * texture2D(sMask, MTextureCoord).a;\n}\n";
    private static final String FRAGMENT_SHADER = "precision highp float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n";
    private static final String GRADIENT_FRAGMENT_SHADER = "precision highp float;\nvarying vec2 vTextureCoord;\nuniform vec4 gradientTopColor;\nuniform vec4 gradientBottomColor;\nfloat interleavedGradientNoise(vec2 n) {\n    return fract(52.9829189 * fract(.06711056 * n.x + .00583715 * n.y));\n}\nvoid main() {\n  gl_FragColor = mix(gradientTopColor, gradientBottomColor, vTextureCoord.y + (.2 * interleavedGradientNoise(gl_FragCoord.xy) - .1));\n}\n";
    public static final boolean USE_MEDIACODEC = true;
    private static final String VERTEX_SHADER = "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n";
    private static final String VERTEX_SHADER_300 = "#version 320 es\nuniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nin vec4 aPosition;\nin vec4 aTextureCoord;\nout vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n";
    private static final String VERTEX_SHADER_MASK = "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nattribute vec4 mTextureCoord;\nvarying vec2 vTextureCoord;\nvarying vec2 MTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n  MTextureCoord = (uSTMatrix * mTextureCoord).xy;\n}\n";
    private static final String VERTEX_SHADER_MASK_300 = "#version 320 es\nuniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nin vec4 aPosition;\nin vec4 aTextureCoord;\nin vec4 mTextureCoord;\nout vec2 vTextureCoord;\nout vec2 MTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n  MTextureCoord = (uSTMatrix * mTextureCoord).xy;\n}\n";
    private int NUM_EXTERNAL_SHADER;
    private int NUM_FILTER_SHADER;
    private int NUM_GRADIENT_SHADER;
    private Drawable backgroundDrawable;
    private String backgroundPath;
    private int backgroundPathIndex;
    float[] bitmapData;
    private FloatBuffer bitmapVerticesBuffer;
    private boolean blendEnabled;
    private BlurringShader blur;
    private int blurBlurImageHandle;
    private int blurInputTexCoordHandle;
    private int blurMaskImageHandle;
    private String blurPath;
    private int blurPositionHandle;
    private int blurShaderProgram;
    private int[] blurTexture;
    private FloatBuffer blurVerticesBuffer;
    private ArrayList<VideoEditedInfo.Part> collageParts;
    private int[] collageTextures;
    private final MediaController.CropState cropState;
    private FloatBuffer croppedTextureBuffer;
    private ArrayList<AnimatedEmojiDrawable> emojiDrawables;
    private FilterShaders filterShaders;
    private boolean firstFrame;
    private int gradientBottomColor;
    private int gradientBottomColorHandle;
    private FloatBuffer gradientTextureBuffer;
    private int gradientTopColor;
    private int gradientTopColorHandle;
    private FloatBuffer gradientVerticesBuffer;
    private int imageHeight;
    private String imagePath;
    private int imagePathIndex;
    private int imageWidth;
    private boolean isPhoto;
    private float[] mMVPMatrix;
    private int[] mProgram;
    private float[] mSTMatrix;
    private float[] mSTMatrixIdentity;
    private int mTextureID;
    private int[] maPositionHandle;
    private int[] maTextureHandle;
    private FloatBuffer maskTextureBuffer;
    private int[] maskTextureHandle;
    private ArrayList<VideoEditedInfo.MediaEntity> mediaEntities;
    private String messagePath;
    private int messagePathIndex;
    private String messageVideoMaskPath;
    private int[] mmTextureHandle;
    private int[] muMVPMatrixHandle;
    private int[] muSTMatrixHandle;
    private int originalHeight;
    private int originalWidth;
    private String paintPath;
    private int paintPathIndex;
    private int[] paintTexture;
    Path path;
    private FloatBuffer renderTextureBuffer;
    private Bitmap roundBitmap;
    private Canvas roundCanvas;
    private Path roundClipPath;
    private final RectF roundDst;
    private final Rect roundSrc;
    private int simpleInputTexCoordHandle;
    private int simpleInputTexCoordHandleOES;
    private int simplePositionHandle;
    private int simplePositionHandleOES;
    private int simpleShaderProgram;
    private int simpleShaderProgramOES;
    private int simpleSourceImageHandle;
    private int simpleSourceImageHandleOES;
    private Bitmap stickerBitmap;
    private Canvas stickerCanvas;
    private int[] stickerTexture;
    private int texSizeHandle;
    Paint textColorPaint;
    private FloatBuffer textureBuffer;
    private int transformedHeight;
    private int transformedWidth;
    private boolean useMatrixForImagePath;
    private FloatBuffer verticesBuffer;
    private float videoFps;
    private int videoMaskTexture;
    Paint xRefPaint;

    /* renamed from: org.telegram.messenger.video.TextureRenderer$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatedEmojiSpan {
        final /* synthetic */ TextureRenderer this$0;
        final /* synthetic */ VideoEditedInfo.EmojiEntity val$e;
        final /* synthetic */ EditTextOutline val$editText;
        final /* synthetic */ VideoEditedInfo.MediaEntity val$entity;

        public AnonymousClass1(TextureRenderer r1, long r2, float r4, Paint.FontMetricsInt r5, VideoEditedInfo.MediaEntity r6, EditTextOutline r7, VideoEditedInfo.EmojiEntity r8) {
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
            float r103 = TextureRenderer.m5223$$Nest$fgettransformedWidth(this.this$0) / TextureRenderer.m5222$$Nest$fgettransformedHeight(this.this$0);
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
            TextureRenderer.m5224$$Nest$minitStickerEntity(this.this$0, r76);
            return;
        }
    }

    /* renamed from: -$$Nest$fgettransformedHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5222$$Nest$fgettransformedHeight(TextureRenderer r0) {
        return r0.transformedHeight;
    }

    /* renamed from: -$$Nest$fgettransformedWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5223$$Nest$fgettransformedWidth(TextureRenderer r0) {
        return r0.transformedWidth;
    }

    /* renamed from: -$$Nest$minitStickerEntity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5224$$Nest$minitStickerEntity(TextureRenderer r0, VideoEditedInfo.MediaEntity r1) {
        r0.initStickerEntity(r1);
    }

    public TextureRenderer(MediaController.SavedFilterState r32, String r33, String r34, String r35, ArrayList<VideoEditedInfo.MediaEntity> r36, MediaController.CropState r37, int r38, int r39, int r40, int r41, int r42, float r43, boolean r44, Integer r45, Integer r46, StoryEntry.HDRInfo r47, MediaCodecVideoConvertor.ConvertVideoParams r48) {
        int r2 = r38;
        int r3 = r39;
        float r6 = r43;
        this.bitmapData = new float[]{-1.0f, 1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f};
        this.NUM_FILTER_SHADER = -1;
        this.NUM_EXTERNAL_SHADER = -1;
        this.NUM_GRADIENT_SHADER = -1;
        this.mMVPMatrix = new float[16];
        this.mSTMatrix = new float[16];
        this.mSTMatrixIdentity = new float[16];
        this.imagePathIndex = -1;
        this.paintPathIndex = -1;
        this.messagePathIndex = -1;
        this.backgroundPathIndex = -1;
        this.roundSrc = new Rect();
        this.roundDst = new RectF();
        this.firstFrame = true;
        this.isPhoto = r44;
        this.collageParts = r48.collageParts;
        float[] r10 = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.d("start textureRenderer w = " + r2 + " h = " + r3 + " r = " + r42 + " fps = " + r6);
        if (r37 == null) goto L7;
        FileLog.d("cropState px = " + r37.cropPx + " py = " + r37.cropPy + " cScale = " + r37.cropScale + " cropRotate = " + r37.cropRotate + " pw = " + r37.cropPw + " ph = " + r37.cropPh + " tw = " + r37.transformWidth + " th = " + r37.transformHeight + " tr = " + r37.transformRotation + " mirror = " + r37.mirrored);
    L7:
        FloatBuffer r12 = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.textureBuffer = r12;
        r12.put(r10).position(0);
        int r13 = 4;
        FloatBuffer r102 = ByteBuffer.allocateDirect(this.bitmapData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.bitmapVerticesBuffer = r102;
        r102.put(this.bitmapData).position(0);
        Matrix.setIdentityM(this.mSTMatrix, 0);
        Matrix.setIdentityM(this.mSTMatrixIdentity, 0);
        if (r32 == null) goto L10;
        FilterShaders r103 = new FilterShaders(true, r47);
        this.filterShaders = r103;
        r103.setDelegate(FilterShaders.getFilterShadersDelegate(r32));
    L10:
        this.transformedWidth = r2;
        this.transformedHeight = r3;
        this.originalWidth = r40;
        this.originalHeight = r41;
        this.imagePath = r33;
        this.paintPath = r34;
        this.messagePath = r48.messagePath;
        this.messageVideoMaskPath = r48.messageVideoMaskPath;
        this.backgroundPath = r48.backgroundPath;
        this.blurPath = r35;
        this.mediaEntities = r36;
        if (r6 != 0.0f) goto L13;
        r6 = 30.0f;
    L13:
        this.videoFps = r6;
        this.cropState = r37;
        this.NUM_EXTERNAL_SHADER = 0;
        Matrix.setIdentityM(this.mMVPMatrix, 0);
        long r14 = r48.wallpaperPeerId;
        if (r14 == Long.MIN_VALUE) goto L16;
        float r322 = 0.0f;
        this.backgroundDrawable = PreviewView.getBackgroundDrawable(null, r48.account, r14, r48.isDark);
    L33:
        int r62 = 1;
    L35:
        if (this.filterShaders == null) goto L37;
        this.NUM_FILTER_SHADER = r62;
        r62 = r62 + 1;
    L37:
        this.mProgram = new int[r62];
        this.muMVPMatrixHandle = new int[r62];
        this.muSTMatrixHandle = new int[r62];
        this.maPositionHandle = new int[r62];
        this.maTextureHandle = new int[r62];
        this.mmTextureHandle = new int[r62];
        this.maskTextureHandle = new int[r62];
        if (r37 == null) goto L62;
        android.graphics.Matrix r104 = r37.useMatrix;
        if (r104 == null) goto L58;
        this.useMatrixForImagePath = true;
        float r332 = 0.5f;
        float r7 = r37.cropPw;
        int r422 = 32;
        float r11 = r37.cropPh;
        if (((r37.orientation / 90) % 2) != 1) goto L44;
        r11 = r7;
        r7 = r11;
    L44:
        float r142 = (1.0f - r7) / 2.0f;
        float r15 = (1.0f - r11) / 2.0f;
        char r23 = 1;
        float[] r9 = new float[8];
        float r4 = r40;
        float r24 = r4 * r142;
        r9[0] = r24;
        float r5 = r41;
        float r25 = r5 * r15;
        r9[1] = r25;
        float r143 = (r142 + r7) * r4;
        r9[2] = r143;
        r9[3] = r25;
        r9[4] = r24;
        float r152 = (r15 + r11) * r5;
        r9[5] = r152;
        r9[6] = r143;
        r9[7] = r152;
        r104.mapPoints(r9);
        int r105 = 0;
    L45:
        if (r105 >= 4) goto L47;
        int r144 = r105 * 2;
        r9[r144] = ((r9[r144] / r2) * 2.0f) - 1.0f;
        int r145 = r144 + 1;
        r9[r145] = 1.0f - ((r9[r145] / r3) * 2.0f);
        r105 = r105 + 1;
        goto L45
    L47:
        FloatBuffer r22 = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.verticesBuffer = r22;
        r22.put(r9).position(0);
        float r72 = r7 * r4;
        float r63 = r72 * (-0.5f);
        float r112 = r11 * r5;
        float r310 = (-0.5f) * r112;
        float r73 = r72 * 0.5f;
        float r113 = r112 * 0.5f;
        float[] r26 = {r63, r310, r73, r310, r63, r113, r73, r113};
        float r311 = (float) ((-r37.cropRotate) * 0.017453292519943295d);
        int r64 = 0;
    L48:
        if (r64 >= r13) goto L51;
        int r74 = r64 * 2;
        float r92 = r26[r74];
        int r106 = r74 + 1;
        float r114 = r26[r106];
        double r146 = r92 - (r37.cropPx * r4);
        int r442 = r13;
        double r132 = r311;
        double r8 = r114 - (r37.cropPy * r5);
        float r122 = ((float) ((Math.cos(r132) * r146) - (Math.sin(r132) * r8))) / r4;
        float r82 = ((float) ((Math.sin(r132) * r146) + (r8 * Math.cos(r132)))) / r5;
        float r93 = r37.cropScale;
        r26[r74] = (r122 / r93) + 0.5f;
        r26[r106] = (r82 / r93) + 0.5f;
        r64 = r64 + 1;
        r13 = r442;
        goto L48
    L51:
        if (this.filterShaders == null) goto L53;
    L57:
        FloatBuffer r312 = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.croppedTextureBuffer = r312;
        int r115 = 0;
        r312.put(r26).position(0);
    L63:
        int r75 = r115;
    L65:
        if (this.filterShaders != null) goto L67;
        int r107 = 4;
        if (r75 != 90) goto L78;
        float[] r27 = new float[8];
        r27[r115] = 1.0f;
        r27[r23] = r322;
        r27[2] = 1.0f;
        r27[3] = 1.0f;
        r27[4] = r322;
        r27[5] = r322;
        r27[6] = r322;
        r27[7] = 1.0f;
    L83:
        if (r37 != null) goto L85;
    L93:
        FloatBuffer r1 = ByteBuffer.allocateDirect(r27.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.renderTextureBuffer = r1;
        r1.put(r27).position(0);
        FloatBuffer r28 = ByteBuffer.allocateDirect(r422).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.maskTextureBuffer = r28;
        r28.put(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f}).position(0);
        return;
    L85:
        if (r37.mirrored == false) goto L93;
        int r16 = 0;
    L87:
        if (r16 >= r107) goto L93;
        int r313 = r16 * 2;
        if (r27[r313] <= r332) goto L91;
        r27[r313] = r322;
    L92:
        r16 = r16 + 1;
        r107 = 4;
        goto L87
    L91:
        r27[r313] = 1.0f;
        goto L92
    L78:
        if (r75 != 180) goto L80;
        r27 = new float[8];
        r27[r115] = 1.0f;
        r27[r23] = 1.0f;
        r27[2] = r322;
        r27[3] = 1.0f;
        r27[4] = 1.0f;
        r27[5] = r322;
        r27[6] = r322;
        r27[7] = r322;
        goto L83
    L80:
        if (r75 != 270) goto L82;
        r27 = new float[8];
        r27[r115] = r322;
        r27[r23] = 1.0f;
        r27[2] = r322;
        r27[3] = r322;
        r27[4] = 1.0f;
        r27[5] = 1.0f;
        r27[6] = 1.0f;
        r27[7] = r322;
        goto L83
    L82:
        r27 = new float[8];
        r27[r115] = r322;
        r27[r23] = r322;
        r27[2] = 1.0f;
        r27[3] = r322;
        r27[4] = r322;
        r27[5] = 1.0f;
        r27[6] = 1.0f;
        r27[7] = 1.0f;
        goto L83
    L67:
        if (r75 != 90) goto L69;
        r27 = new float[8];
        r27[r115] = 1.0f;
        r27[r23] = 1.0f;
        r27[2] = 1.0f;
        r27[3] = r322;
        r107 = 4;
        r27[4] = r322;
        r27[5] = 1.0f;
        r27[6] = r322;
        r27[7] = r322;
        goto L83
    L69:
        r107 = 4;
        if (r75 != 180) goto L72;
        r27 = new float[8];
        r27[r115] = 1.0f;
        r27[r23] = r322;
        r27[2] = r322;
        r27[3] = r322;
        r27[4] = 1.0f;
        r27[5] = 1.0f;
        r27[6] = r322;
        r27[7] = 1.0f;
        goto L83
    L72:
        if (r75 != 270) goto L74;
        r27 = new float[8];
        r27[r115] = r322;
        r27[r23] = r322;
        r27[2] = r322;
        r27[3] = 1.0f;
        r27[4] = 1.0f;
        r27[5] = r322;
        r27[6] = 1.0f;
        r27[7] = 1.0f;
        goto L83
    L74:
        r27 = new float[8];
        r27[r115] = r322;
        r27[r23] = 1.0f;
        r27[2] = 1.0f;
        r27[3] = 1.0f;
        r27[4] = r322;
        r27[5] = r322;
        r27[6] = 1.0f;
        r27[7] = r322;
        goto L83
    L53:
        if (this.isPhoto == true) goto L57;
        if (this.messageVideoMaskPath != null) goto L57;
        r26[1] = 1.0f - r26[1];
        r26[3] = 1.0f - r26[3];
        r26[5] = 1.0f - r26[5];
        r26[7] = 1.0f - r26[7];
        goto L57
    L58:
        r23 = 1;
        r422 = 32;
        r332 = 0.5f;
        float[] r49 = new float[8];
        r49[0] = r322;
        r49[1] = r322;
        float r52 = r2;
        r49[2] = r52;
        r49[3] = r322;
        r49[4] = r322;
        float r65 = r3;
        r49[5] = r65;
        r49[6] = r52;
        r49[7] = r65;
        r75 = r37.transformRotation;
        this.transformedWidth = (int) (this.transformedWidth * r37.cropPw);
        this.transformedHeight = (int) (this.transformedHeight * r37.cropPh);
        float r83 = (float) ((-r37.cropRotate) * 0.017453292519943295d);
        int r108 = 4;
        int r94 = 0;
    L59:
        if (r94 >= r108) goto L61;
        int r109 = r94 * 2;
        float r123 = r49[r109] - (r2 / 2);
        int r133 = r109 + 1;
        float r147 = r49[r133] - (r3 / 2);
        double r116 = r123;
        double r29 = r83;
        double r242 = Math.cos(r29) * r116;
        double r210 = r147;
        float r211 = ((float) ((r242 - (Math.sin(r29) * r210)) + (r37.cropPx * r52))) * r37.cropScale;
        float r212 = ((float) (((r116 * Math.sin(r29)) + (Math.cos(r29) * r210)) - (r37.cropPy * r65))) * r37.cropScale;
        r49[r109] = (r211 / this.transformedWidth) * 2.0f;
        r49[r133] = (r212 / this.transformedHeight) * 2.0f;
        r94 = r94 + 1;
        r2 = r38;
        r3 = r39;
        r108 = 4;
        goto L59
    L61:
        FloatBuffer r213 = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.verticesBuffer = r213;
        r115 = 0;
        r213.put(r49).position(0);
        goto L65
    L62:
        r23 = 1;
        r422 = 32;
        r115 = 0;
        r332 = 0.5f;
        FloatBuffer r314 = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.verticesBuffer = r314;
        r314.put(new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f}).position(0);
        goto L63
    L16:
        r322 = 0.0f;
        if (r46 == null) goto L33;
        if (r45 == null) goto L33;
        FloatBuffer r76 = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.gradientVerticesBuffer = r76;
        r76.put(new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f}).position(0);
        boolean r66 = this.isPhoto;
        if (r66 == false) goto L22;
        float r77 = 1.0f;
    L23:
        if (r66 == false) goto L25;
        float r1010 = 1.0f;
    L26:
        if (r66 == false) goto L28;
        float r148 = 0.0f;
    L29:
        if (r66 == false) goto L31;
        float r67 = 0.0f;
    L32:
        float[] r153 = {0.0f, r77, 1.0f, r1010, 0.0f, r148, 1.0f, r67};
        FloatBuffer r68 = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.gradientTextureBuffer = r68;
        r68.put(r153).position(0);
        this.gradientTopColor = r45.intValue();
        this.gradientBottomColor = r46.intValue();
        this.NUM_GRADIENT_SHADER = 1;
        r62 = 2;
        goto L35
    L31:
        r67 = 1.0f;
        goto L32
    L28:
        r148 = 1.0f;
        goto L29
    L25:
        r1010 = 0.0f;
        goto L26
    L22:
        r77 = 0.0f;
        goto L23
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

    private int createProgram(String r5, String r6, boolean r7) {
        if (r7 == false) goto L18;
        int r52 = FilterShaders.loadShader(35633, r5);
        if (r52 != 0) goto L7;
        return 0;
    L7:
        int r62 = FilterShaders.loadShader(35632, r6);
        if (r62 != 0) goto L10;
        return 0;
    L10:
        int r72 = GLES20.glCreateProgram();
        if (r72 != 0) goto L13;
        return 0;
    L13:
        GLES20.glAttachShader(r72, r52);
        GLES20.glAttachShader(r72, r62);
        GLES20.glLinkProgram(r72);
        int[] r53 = new int[1];
        GLES20.glGetProgramiv(r72, 35714, r53, 0);
        if (r53[0] == 1) goto L17;
        GLES20.glDeleteProgram(r72);
        return 0;
    L17:
        return r72;
    L18:
        int r54 = FilterShaders.loadShader(35633, r5);
        if (r54 != 0) goto L21;
        return 0;
    L21:
        int r63 = FilterShaders.loadShader(35632, r6);
        if (r63 != 0) goto L24;
        return 0;
    L24:
        int r73 = GLES20.glCreateProgram();
        if (r73 != 0) goto L27;
        return 0;
    L27:
        GLES20.glAttachShader(r73, r54);
        GLES20.glAttachShader(r73, r63);
        GLES20.glLinkProgram(r73);
        int[] r55 = new int[1];
        GLES20.glGetProgramiv(r73, 35714, r55, 0);
        if (r55[0] == 1) goto L31;
        GLES20.glDeleteProgram(r73);
        return 0;
    L31:
        return r73;
    }

    private void destroyCollagePart(int r1, VideoEditedInfo.Part r2) {
        if (r2 == null) goto L15;
        AnimatedFileDrawable r0 = r2.animatedFileDrawable;
        if (r0 == null) goto L7;
        r0.recycle();
        r2.animatedFileDrawable = null;
    L7:
        MediaCodecPlayer r02 = r2.player;
        if (r02 == null) goto L10;
        r02.release();
        r2.player = null;
    L10:
        SurfaceTexture r03 = r2.surfaceTexture;
        if (r03 == null) goto L14;
        r03.release();
        r2.surfaceTexture = null;
        return;
    L14:
        return;
    }

    private void drawBackground() {
        int r0 = this.NUM_GRADIENT_SHADER;
        if (r0 < 0) goto L7;
        GLES20.glUseProgram(this.mProgram[r0]);
        GLES20.glVertexAttribPointer(this.maPositionHandle[this.NUM_GRADIENT_SHADER], 2, 5126, false, 8, this.gradientVerticesBuffer);
        GLES20.glEnableVertexAttribArray(this.maPositionHandle[this.NUM_GRADIENT_SHADER]);
        GLES20.glVertexAttribPointer(this.maTextureHandle[this.NUM_GRADIENT_SHADER], 2, 5126, false, 8, this.gradientTextureBuffer);
        GLES20.glEnableVertexAttribArray(this.maTextureHandle[this.NUM_GRADIENT_SHADER]);
        GLES20.glUniformMatrix4fv(this.muSTMatrixHandle[this.NUM_GRADIENT_SHADER], 1, false, this.mSTMatrix, 0);
        GLES20.glUniformMatrix4fv(this.muMVPMatrixHandle[this.NUM_GRADIENT_SHADER], 1, false, this.mMVPMatrix, 0);
        GLES20.glUniform4f(this.gradientTopColorHandle, Color.red(this.gradientTopColor) / 255.0f, Color.green(this.gradientTopColor) / 255.0f, Color.blue(this.gradientTopColor) / 255.0f, Color.alpha(this.gradientTopColor) / 255.0f);
        GLES20.glUniform4f(this.gradientBottomColorHandle, Color.red(this.gradientBottomColor) / 255.0f, Color.green(this.gradientBottomColor) / 255.0f, Color.blue(this.gradientBottomColor) / 255.0f, Color.alpha(this.gradientBottomColor) / 255.0f);
        GLES20.glDrawArrays(5, 0, 4);
        return;
    L7:
        if (this.backgroundPathIndex < 0) goto L10;
        GLES20.glUseProgram(this.simpleShaderProgram);
        GLES20.glActiveTexture(33984);
        GLES20.glUniform1i(this.simpleSourceImageHandle, 0);
        GLES20.glEnableVertexAttribArray(this.simpleInputTexCoordHandle);
        GLES20.glVertexAttribPointer(this.simpleInputTexCoordHandle, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(this.simplePositionHandle);
        drawTexture(true, this.paintTexture[this.backgroundPathIndex], -10000.0f, -10000.0f, -10000.0f, -10000.0f, 0.0f, false, false, -1);
        return;
    }

    private void drawCollagePart(int r7, VideoEditedInfo.Part r8, long r9) {
        if (r8.player != null) goto L5;
    L7:
        GLES20.glUseProgram(this.simpleShaderProgram);
        GLES20.glActiveTexture(33986);
        GLES20.glBindTexture(3553, this.collageTextures[r7]);
        GLES20.glUniform1i(this.simpleSourceImageHandle, 2);
        GLES20.glEnableVertexAttribArray(this.simpleInputTexCoordHandle);
        GLES20.glVertexAttribPointer(this.simpleInputTexCoordHandle, 2, 5126, false, 8, r8.uvBuffer);
        GLES20.glEnableVertexAttribArray(this.simplePositionHandle);
        GLES20.glVertexAttribPointer(this.simplePositionHandle, 2, 5126, false, 8, r8.posBuffer);
    L8:
        GLES20.glDrawArrays(5, 0, 4);
        return;
    L5:
        if (r8.isVideo == false) goto L7;
        GLES20.glUseProgram(this.simpleShaderProgramOES);
        GLES20.glActiveTexture(33987);
        GLES20.glBindTexture(36197, this.collageTextures[r7]);
        GLES20.glUniform1i(this.simpleSourceImageHandleOES, 3);
        GLES20.glEnableVertexAttribArray(this.simpleInputTexCoordHandleOES);
        GLES20.glVertexAttribPointer(this.simpleInputTexCoordHandleOES, 2, 5126, false, 8, r8.uvBuffer);
        GLES20.glEnableVertexAttribArray(this.simplePositionHandleOES);
        GLES20.glVertexAttribPointer(this.simplePositionHandleOES, 2, 5126, false, 8, r8.posBuffer);
        goto L8
    }

    private void drawEntity(VideoEditedInfo.MediaEntity r30, int r31, long r32) {
        RLottieNative r1 = r30.lottieNative;
        int r12 = 0;
        if (r1 == null) goto L25;
        Bitmap r6 = r30.bitmap;
        if (r6 != null) goto L7;
        return;
    L7:
        if (r30.W > 0) goto L9;
        return;
    L9:
        if (r30.H <= 0) goto L144;
        r1.getFrame((int) r30.currentFrame, r6, true);
        Bitmap r13 = r30.bitmap;
        if ((r30.subType & 8) == 0) goto L14;
        int r62 = r31;
    L15:
        applyRoundRadius(r30, r13, r62);
        GLES20.glBindTexture(3553, this.stickerTexture[0]);
        GLUtils.texImage2D(3553, 0, r30.bitmap, 0);
        float r14 = r30.currentFrame + r30.framesPerDraw;
        r30.currentFrame = r14;
        if (r14 < r30.lottieNative.getFrameCount()) goto L18;
        r30.currentFrame = 0.0f;
    L18:
        int r2 = this.stickerTexture[0];
        float r3 = r30.x;
        float r4 = r30.y;
        float r5 = r30.width;
        float r63 = r30.height;
        float r7 = r30.rotation;
        if ((2 & r30.subType) == 0) goto L21;
        boolean r8 = true;
    L22:
        drawTexture(false, r2, r3, r4, r5, r63, r7, r8);
        return;
    L21:
        r8 = false;
        goto L22
    L14:
        r62 = 0;
        goto L15
    L144:
        return;
    L25:
        if (r30.animatedFileDrawable == null) goto L101;
        float r33 = r30.currentFrame;
        int r64 = (int) r33;
        float r142 = 1.0f;
        if (r30.type == 5) goto L29;
        float r34 = r33 + r30.framesPerDraw;
        r30.currentFrame = r34;
        int r15 = (int) r34;
    L56:
        if (r64 == r15) goto L58;
        r30.animatedFileDrawable.getNextFrame(true);
        r15 = r15 - 1;
    L58:
        Bitmap r16 = r30.animatedFileDrawable.getBackgroundBitmap();
        if (r16 != null) goto L61;
        return;
    L61:
        if (r30.type != 5) goto L77;
        if (this.roundBitmap != null) goto L65;
        int r22 = Math.min(r16.getWidth(), r16.getHeight());
        this.roundBitmap = Bitmap.createBitmap(r22, r22, Bitmap.Config.ARGB_8888);
        this.roundCanvas = new Canvas(this.roundBitmap);
    L65:
        Bitmap r23 = this.roundBitmap;
        if (r23 == null) goto L75;
        r23.eraseColor(0);
        this.roundCanvas.save();
        if (this.roundClipPath != null) goto L70;
        this.roundClipPath = new Path();
    L70:
        this.roundClipPath.rewind();
        this.roundClipPath.addCircle(this.roundBitmap.getWidth() / 2.0f, this.roundBitmap.getHeight() / 2.0f, (this.roundBitmap.getWidth() / 2.0f) * r142, Path.Direction.CW);
        this.roundCanvas.clipPath(this.roundClipPath);
        int r24 = r16.getWidth();
        int r42 = r16.getHeight();
        Rect r72 = this.roundSrc;
        if (r24 < r42) goto L73;
        r72.set((r16.getWidth() - r16.getHeight()) / 2, 0, r16.getWidth() - ((r16.getWidth() - r16.getHeight()) / 2), r16.getHeight());
    L74:
        this.roundDst.set(0.0f, 0.0f, this.roundBitmap.getWidth(), this.roundBitmap.getHeight());
        this.roundCanvas.drawBitmap(r16, this.roundSrc, this.roundDst, null);
        this.roundCanvas.restore();
        goto L75
    L73:
        r72.set(0, (r16.getHeight() - r16.getWidth()) / 2, r16.getWidth(), r16.getHeight() - ((r16.getHeight() - r16.getWidth()) / 2));
    L75:
        Bitmap r17 = this.roundBitmap;
    L93:
        if (r17 == null) goto L141;
        GLES20.glBindTexture(3553, this.stickerTexture[0]);
        GLUtils.texImage2D(3553, 0, r17, 0);
        int r25 = this.stickerTexture[0];
        float r35 = r30.x;
        float r43 = r30.y;
        float r52 = r30.width;
        float r65 = r30.height;
        float r73 = r30.rotation;
        if ((r30.subType & 2) == 0) goto L97;
        boolean r82 = true;
    L98:
        drawTexture(false, r25, r35, r43, r52, r65, r73, r82);
        return;
    L97:
        r82 = false;
        goto L98
    L141:
        return;
    L77:
        if (this.stickerCanvas == null) goto L79;
    L85:
        Bitmap r26 = this.stickerBitmap;
        if (r26 == null) goto L92;
        r26.eraseColor(0);
        this.stickerCanvas.drawBitmap(r16, 0.0f, 0.0f, null);
        Bitmap r18 = this.stickerBitmap;
        if ((r30.subType & 8) == 0) goto L90;
        int r27 = r31;
    L91:
        applyRoundRadius(r30, r18, r27);
        goto L92
    L90:
        r27 = 0;
    L92:
        r17 = this.stickerBitmap;
        goto L93
    L79:
        if (this.stickerBitmap == null) goto L85;
        this.stickerCanvas = new Canvas(this.stickerBitmap);
        if (this.stickerBitmap.getHeight() == r16.getHeight()) goto L83;
    L84:
        this.stickerCanvas.scale(this.stickerBitmap.getWidth() / r16.getWidth(), this.stickerBitmap.getHeight() / r16.getHeight());
        goto L85
    L83:
        if (this.stickerBitmap.getWidth() == r16.getWidth()) goto L85;
    L29:
        if (this.isPhoto == false) goto L31;
        long r66 = r30.roundDuration;
        long r19 = 0;
    L32:
        long r192 = r32 / 1000000;
        if (r192 >= r19) goto L36;
        r142 = CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(Utilities.clamp(1.0f - ((r19 - r192) / 400.0f), 1.0f, 0.0f));
    L39:
        if (r142 <= 0.0f) goto L58;
        if (this.isPhoto == false) goto L43;
        long r110 = Utilities.clamp(r192, r30.roundDuration, 0);
    L45:
        if (r30.looped == true) goto L58;
        if (r30.animatedFileDrawable.getProgressMs() >= Math.min(r110, r30.animatedFileDrawable.getDurationMs())) goto L58;
        int r36 = r30.animatedFileDrawable.getProgressMs();
        r30.animatedFileDrawable.getNextFrame(false);
        if (r30.animatedFileDrawable.getProgressMs() > r36) goto L45;
        if (r30.animatedFileDrawable.getProgressMs() != 0) goto L53;
        if (r36 == 0) goto L45;
    L53:
        r30.looped = true;
        goto L58
    L43:
        r110 = Utilities.clamp((r192 - r30.roundOffset) + r30.roundLeft, r30.roundDuration, 0);
        goto L45
    L36:
        if (r192 <= r66) goto L39;
        r142 = CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(Utilities.clamp(1.0f - ((r192 - r66) / 400.0f), 1.0f, 0.0f));
        goto L39
    L31:
        long r67 = r30.roundOffset;
        r66 = r67 + (r30.roundRight - r30.roundLeft);
        r19 = r67;
        goto L32
    L101:
        if (r30.bitmap == null) goto L110;
        GLES20.glBindTexture(3553, this.stickerTexture[0]);
        GLUtils.texImage2D(3553, 0, r30.bitmap, 0);
        int r28 = this.stickerTexture[0];
        float r111 = r30.x;
        float r37 = r30.additionalWidth;
        float r112 = r111 - (r37 / 2.0f);
        float r53 = r30.y;
        float r68 = r30.additionalHeight;
        float r44 = r53 - (r68 / 2.0f);
        float r54 = r30.width + r37;
        float r69 = r68 + r30.height;
        float r74 = r30.rotation;
        if (r30.type == 2) goto L105;
    L108:
        boolean r83 = false;
    L109:
        drawTexture(false, r28, r112, r44, r54, r69, r74, r83);
        goto L110
    L105:
        if ((r30.subType & 2) == 0) goto L108;
        r83 = true;
    L110:
        ArrayList<VideoEditedInfo.EmojiEntity> r113 = r30.entities;
        if (r113 != null) goto L113;
        return;
    L113:
        if (r113.isEmpty() == false) goto L115;
        return;
    L115:
        if (r12 >= r30.entities.size()) goto L145;
        VideoEditedInfo.EmojiEntity r114 = r30.entities.get(r12);
        if (r114 == null) goto L123;
        VideoEditedInfo.MediaEntity r115 = r114.entity;
        if (r115 == null) goto L123;
        drawEntity(r115, r30.color, r32);
    L123:
        r12 = r12 + 1;
        goto L115
    }

    private void drawTexture(boolean r10, int r11) {
        drawTexture(r10, r11, -10000.0f, -10000.0f, -10000.0f, -10000.0f, 0.0f, false);
    }

    private void drawTexture(boolean r12, int r13, float r14, float r15, float r16, float r17, float r18, boolean r19) {
        drawTexture(r12, r13, r14, r15, r16, r17, r18, r19, false, -1);
    }

    private void drawTexture(boolean r21, int r22, float r23, float r24, float r25, float r26, float r27, boolean r28, boolean r29, int r30) {
        if (this.blendEnabled == true) goto L5;
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(1, 771);
        this.blendEnabled = true;
    L5:
        float[] r4 = this.bitmapData;
        int r11 = 4;
        if (r23 > (-10000.0f)) goto L8;
        r4[0] = -1.0f;
        r4[1] = 1.0f;
        r4[2] = 1.0f;
        r4[3] = 1.0f;
        r4[4] = -1.0f;
        r4[5] = -1.0f;
        r4[6] = 1.0f;
        r4[7] = -1.0f;
    L9:
        float[] r2 = this.bitmapData;
        float r42 = r2[0];
        float r5 = r2[2];
        float r6 = (r42 + r5) / 2.0f;
        if (r28 == false) goto L13;
        r2[2] = r42;
        r2[0] = r5;
        float r43 = r2[6];
        r2[6] = r2[4];
        r2[4] = r43;
    L13:
        if (r27 == 0.0f) goto L17;
        float r44 = this.transformedWidth / this.transformedHeight;
        float r52 = (r2[5] + r2[1]) / 2.0f;
        int r210 = 0;
    L15:
        if (r210 >= r11) goto L17;
        float[] r3 = this.bitmapData;
        int r7 = r210 * 2;
        float r9 = r3[r7] - r6;
        int r10 = r7 + 1;
        double r14 = r9;
        double r8 = r27;
        double r112 = (r3[r10] - r52) / r44;
        r3[r7] = ((float) ((Math.cos(r8) * r14) - (Math.sin(r8) * r112))) + r6;
        this.bitmapData[r10] = (((float) ((r14 * Math.sin(r8)) + (r112 * Math.cos(r8)))) * r44) + r52;
        r210 = r210 + 1;
        r11 = 4;
    L17:
        this.bitmapVerticesBuffer.put(this.bitmapData).position(0);
        int r1 = this.simplePositionHandle;
        if (r29 == false) goto L20;
        FloatBuffer r211 = this.verticesBuffer;
    L21:
        GLES20.glVertexAttribPointer(r1, 2, 5126, false, 8, r211);
        GLES20.glEnableVertexAttribArray(this.simpleInputTexCoordHandle);
        int r12 = this.simpleInputTexCoordHandle;
        if (r29 == false) goto L24;
        FloatBuffer r0 = this.croppedTextureBuffer;
    L25:
        GLES20.glVertexAttribPointer(r12, 2, 5126, false, 8, r0);
        if (r21 == false) goto L28;
        GLES20.glBindTexture(3553, r22);
    L28:
        GLES20.glDrawArrays(5, 0, 4);
        return;
    L24:
        r0 = this.textureBuffer;
        goto L25
    L20:
        r211 = this.bitmapVerticesBuffer;
        goto L21
    L8:
        float r212 = (r23 * 2.0f) - 1.0f;
        float r142 = ((1.0f - r24) * 2.0f) - 1.0f;
        r4[0] = r212;
        r4[1] = r142;
        float r122 = (r25 * 2.0f) + r212;
        r4[2] = r122;
        r4[3] = r142;
        r4[4] = r212;
        float r143 = r142 - (r26 * 2.0f);
        r4[5] = r143;
        r4[6] = r122;
        r4[7] = r143;
        goto L9
    }

    private FloatBuffer floats(float[] r2) {
        FloatBuffer r1 = ByteBuffer.allocateDirect(r2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        r1.put(r2).position(0);
        return r1;
    }

    private void initCollagePart(int r37, VideoEditedInfo.Part r38) {
        AtomicInteger r4 = new AtomicInteger(r38.width);
        AtomicInteger r5 = new AtomicInteger(r38.height);
        AtomicInteger r6 = new AtomicInteger(0);
        boolean r0 = r38.isVideo;
        int[] r8 = this.collageTextures;
        if (r0 == false) goto L20;
        GLES20.glBindTexture(36197, r8[r37]);
        GLES20.glTexParameteri(36197, 10241, 9728);
        GLES20.glTexParameteri(36197, 10240, 9728);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        SurfaceTexture r02 = new SurfaceTexture(this.collageTextures[r37]);
        r38.surfaceTexture = r02;
        r02.setDefaultBufferSize(r38.width, r38.height);
        r38.player = new MediaCodecPlayer(r38.path, new Surface(r38.surfaceTexture));     // Catch: Exception -> L7
    L9:
        MediaCodecPlayer r03 = r38.player;
        if (r03 == null) goto L12;
        r4.set(r03.getOrientedWidth());
        r5.set(r38.player.getOrientedHeight());
        r6.set(r38.player.getOrientation());
    L42:
        float[] r2 = {r38.part.l(2.0f) - 1.0f, -(r38.part.t(2.0f) - 1.0f), r38.part.r(2.0f) - 1.0f, -(r38.part.t(2.0f) - 1.0f), r38.part.l(2.0f) - 1.0f, -(r38.part.b(2.0f) - 1.0f), r38.part.r(2.0f) - 1.0f, -(r38.part.b(2.0f) - 1.0f)};
        float r11 = r38.part.w(this.transformedWidth);
        float r12 = r38.part.h(this.transformedHeight);
        int r42 = r4.get();
        int r52 = r5.get();
        int r62 = r6.get();
        float r43 = r42;
        float r53 = r52;
        float r04 = 1.0f / Math.max(r11 / r43, r12 / r53);
        float r112 = ((r11 * r04) / r43) / 2.0f;
        float r122 = ((r12 * r04) / r53) / 2.0f;
        if (((r62 / 90) % 2) != 1) goto L45;
        r122 = r112;
        r112 = r122;
    L45:
        float r54 = 0.5f - r112;
        float r13 = 0.5f - r122;
        float r113 = r112 + 0.5f;
        float r123 = r122 + 0.5f;
        float[] r05 = {r54, r13, r113, r13, r54, r123, r113, r123};
    L46:
        if (r62 <= 0) goto L49;
        float r44 = r05[0];
        float r55 = r05[1];
        r05[0] = r05[4];
        r05[1] = r05[5];
        r05[4] = r05[6];
        r05[5] = r05[7];
        r05[6] = r05[2];
        r05[7] = r05[3];
        r05[2] = r44;
        r05[3] = r55;
        r62 = r62 - 90;
    L49:
        if (r62 >= 0) goto L51;
        float r45 = r05[0];
        float r56 = r05[1];
        r05[0] = r05[2];
        r05[1] = r05[3];
        r05[2] = r05[6];
        r05[3] = r05[7];
        r05[6] = r05[4];
        r05[7] = r05[5];
        r05[4] = r45;
        r05[5] = r56;
        r62 = r62 + 90;
        goto L49
    L51:
        r38.posBuffer = floats(r2);
        r38.uvBuffer = floats(r05);
        return;
    L12:
        r38.surfaceTexture.release();
        r38.surfaceTexture = null;
        GLES20.glDeleteTextures(1, this.collageTextures, r37);
        GLES20.glGenTextures(1, this.collageTextures, r37);
        GLES20.glBindTexture(3553, this.collageTextures[r37]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        AnimatedFileDrawable r19 = new AnimatedFileDrawable(new File(r38.path), true, 0, 0, null, null, null, 0, UserConfig.selectedAccount, true, 512, 512, null);
        r38.animatedFileDrawable = r19;
        if (r19.decoderFailed() == true) goto L18;
        r38.framesPerDraw = r38.animatedFileDrawable.getFps() / this.videoFps;
        r38.msPerFrame = 1000.0f / r38.animatedFileDrawable.getFps();
        r38.currentFrame = 1.0f;
        Bitmap r06 = r38.animatedFileDrawable.getNextFrame(false);
        if (r06 == null) goto L17;
        GLUtils.texImage2D(3553, 0, r06, 0);
    L17:
        r4.set(r38.animatedFileDrawable.getIntrinsicWidth());
        r5.set(r38.animatedFileDrawable.getIntrinsicHeight());
        r6.set(r38.animatedFileDrawable.getOrientation());
        goto L42
    L18:
        GlShader$$ExternalSyntheticBUOutline1.m("Failed to decode with ffmpeg software codecs");
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
        r38.player = null;
        goto L9
    L20:
        GLES20.glBindTexture(3553, r8[r37]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        BitmapFactory.Options r07 = new BitmapFactory.Options();
        r07.inMutable = true;
        Bitmap r82 = BitmapFactory.decodeFile(r38.path, r07);
        Pair<Integer, Integer> r08 = AndroidUtilities.getImageOrientation(r38.path);
        if (((Integer) r08.first).intValue() == 0) goto L23;
    L26:
        android.graphics.Matrix r132 = new android.graphics.Matrix();
        if (((Integer) r08.second).intValue() == 0) goto L38;
        float r9 = -1.0f;
        if (((Integer) r08.second).intValue() != 1) goto L31;
        float r22 = -1.0f;
    L33:
        if (((Integer) r08.second).intValue() == 2) goto L36;
        r9 = 1.0f;
    L36:
        r132.postScale(r22, r9);
        goto L38
    L31:
        r22 = 1.0f;
    L38:
        if (((Integer) r08.first).intValue() == 0) goto L40;
        r132.postRotate(((Integer) r08.first).intValue());
    L40:
        r82 = Bitmaps.createBitmap(r82, 0, 0, r82.getWidth(), r82.getHeight(), r132, true);
    L41:
        GLUtils.texImage2D(3553, 0, r82, 0);
        r4.set(r82.getWidth());
        r5.set(r82.getHeight());
        goto L42
    L23:
        if (((Integer) r08.second).intValue() == 0) goto L41;
        goto L26
    }

    private void initLinkEntity(VideoEditedInfo.MediaEntity r6) {
        LinkPreview r0 = new LinkPreview(ApplicationLoader.applicationContext, r6.density);
        r0.setVideoTexture();
        r0.set(UserConfig.selectedAccount, r6.linkSettings);
        boolean r1 = r0.withPreview();
        byte r2 = r6.subType;
        if (r1 == false) goto L5;
        r0.setPreviewType(r2);
    L6:
        int r12 = r6.viewWidth;
        int r22 = r0.padx;
        r0.setMaxWidth((r12 + r22) + r22);
        r0.measure(View.MeasureSpec.makeMeasureSpec(r6.viewWidth, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r6.viewHeight, TLObject.FLAG_30));
        r0.layout(0, 0, r6.viewWidth, r6.viewHeight);
        float r13 = r6.width * this.transformedWidth;
        int r23 = r6.viewWidth;
        float r14 = r13 / r23;
        r6.bitmap = Bitmap.createBitmap(((int) (r23 * r14)) + 16, ((int) (r6.viewHeight * r14)) + 16, Bitmap.Config.ARGB_8888);
        Canvas r24 = new Canvas(r6.bitmap);
        r24.translate(8.0f, 8.0f);
        r24.scale(r14, r14);
        r0.draw(r24);
        float r02 = 16.0f * r14;
        r6.additionalWidth = r02 / this.transformedWidth;
        r6.additionalHeight = r02 / this.transformedHeight;
        return;
    L5:
        r0.setType(r2, r6.color);
        goto L6
    }

    private void initLocationEntity(VideoEditedInfo.MediaEntity r18) {
        if (r18.type != 3) goto L5;
        int r2 = 0;
    L6:
        LocationMarker r3 = new LocationMarker(ApplicationLoader.applicationContext, r2, r18.density, 0);
        r3.setIsVideo(true);
        r3.setText(r18.text);
        r3.setType(r18.subType, r18.color);
        if (r18.weather != null) goto L9;
    L11:
        r3.setMaxWidth(r18.viewWidth);
        if (r18.entities.size() != 1) goto L14;
        r3.forceEmoji();
    L14:
        r3.measure(View.MeasureSpec.makeMeasureSpec(r18.viewWidth, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r18.viewHeight, TLObject.FLAG_30));
        r3.layout(0, 0, r18.viewWidth, r18.viewHeight);
        float r22 = r18.width * this.transformedWidth;
        int r6 = r18.viewWidth;
        float r23 = r22 / r6;
        r18.bitmap = Bitmap.createBitmap(((int) (r6 * r23)) + 16, ((int) (r18.viewHeight * r23)) + 16, Bitmap.Config.ARGB_8888);
        Canvas r62 = new Canvas(r18.bitmap);
        r62.translate(8.0f, 8.0f);
        r62.scale(r23, r23);
        r3.draw(r62);
        float r63 = 16.0f * r23;
        r18.additionalWidth = r63 / this.transformedWidth;
        r18.additionalHeight = r63 / this.transformedHeight;
        if (r18.entities.size() != 1) goto L22;
        VideoEditedInfo.EmojiEntity r24 = r18.entities.get(0);
        VideoEditedInfo.MediaEntity r4 = new VideoEditedInfo.MediaEntity();
        r24.entity = r4;
        r4.text = r24.documentAbsolutePath;
        r4.subType = r24.subType;
        RectF r42 = new RectF();
        r3.getEmojiBounds(r42);
        float r32 = r18.x + ((r42.centerX() / r18.viewWidth) * r18.width);
        float r5 = r18.y;
        float r64 = r42.centerY() / r18.viewHeight;
        float r7 = r18.height;
        float r52 = r5 + (r64 * r7);
        if (r18.rotation == 0.0f) goto L19;
        float r8 = r18.x + (r18.width / 2.0f);
        float r10 = r18.y + (r7 / 2.0f);
        float r72 = this.transformedWidth / this.transformedHeight;
        double r11 = r32 - r8;
        double r53 = (r52 - r10) / r72;
        float r15 = 2.0f;
        r32 = ((float) ((Math.cos(-r6) * r11) - (Math.sin(-r18.rotation) * r53))) + r8;
        r52 = (((float) ((r11 * Math.sin(-r18.rotation)) + (r53 * Math.cos(-r18.rotation)))) * r72) + r10;
    L20:
        r24.entity.width = (r42.width() / r18.viewWidth) * r18.width;
        r24.entity.height = (r42.height() / r18.viewHeight) * r18.height;
        VideoEditedInfo.MediaEntity r25 = r24.entity;
        float r43 = r25.width * 1.2f;
        r25.width = r43;
        float r73 = r25.height * 1.2f;
        r25.height = r73;
        r25.x = r32 - (r43 / r15);
        r25.y = r52 - (r73 / r15);
        r25.rotation = r18.rotation;
        initStickerEntity(r25);
        return;
    L19:
        r15 = 2.0f;
        goto L20
    L22:
        return;
    L9:
        if (r18.entities.isEmpty() == false) goto L11;
        r3.setCodeEmoji(UserConfig.selectedAccount, r18.weather.getEmoji());
        goto L11
    L5:
        r2 = 1;
        goto L6
    }

    private void initStickerEntity(VideoEditedInfo.MediaEntity r24) {
        int r2 = (int) (r24.width * this.transformedWidth);
        r24.W = r2;
        int r3 = (int) (r24.height * this.transformedHeight);
        r24.H = r3;
        if (r2 <= 512) goto L5;
        r24.H = (int) ((r3 / r2) * 512.0f);
        r24.W = 512;
    L5:
        int r22 = r24.H;
        if (r22 <= 512) goto L8;
        r24.W = (int) ((r24.W / r22) * 512.0f);
        r24.H = 512;
    L8:
        byte r23 = r24.subType;
        float r4 = 0.0f;
        if ((r23 & 1) == 0) goto L21;
        int r25 = r24.W;
        if (r25 <= 0) goto L64;
        int r32 = r24.H;
        if (r32 <= 0) goto L68;
        r24.bitmap = Bitmap.createBitmap(r25, r32, Bitmap.Config.ARGB_8888);
        RLottieNative r26 = RLottieNative.createFromFile(r24.text);
        r24.lottieNative = r26;
        if (r26 == null) goto L18;
        r4 = r26.getFps() / this.videoFps;
    L18:
        r24.framesPerDraw = r4;
        return;
    L68:
        return;
    L64:
        return;
    L21:
        if ((r23 & 4) == 0) goto L26;
        r24.looped = false;
        r24.animatedFileDrawable = new AnimatedFileDrawable(new File(r24.text), true, 0, 0, null, null, null, 0, UserConfig.selectedAccount, true, 512, 512, null);
        r24.framesPerDraw = r7.getFps() / this.videoFps;
        r24.currentFrame = 1.0f;
        r24.animatedFileDrawable.getNextFrame(true);
        if (r24.type != 5) goto L65;
        r24.firstSeek = true;
        return;
    L65:
        return;
    L26:
        String r27 = r24.text;
        if (TextUtils.isEmpty(r24.segmentedPath) == false) goto L29;
    L31:
        BitmapFactory.Options r7 = new BitmapFactory.Options();
        if (r24.type != 2) goto L34;
        r7.inMutable = true;
    L34:
        Bitmap r28 = BitmapFactory.decodeFile(r27, r7);
        r24.bitmap = r28;
        if (r28 == null) goto L46;
        MediaController.CropState r8 = r24.crop;
        if (r8 == null) goto L46;
        Bitmap r29 = Bitmap.createBitmap((int) Math.max(1.0f, r8.cropPw * r28.getWidth()), (int) Math.max(1.0f, r24.crop.cropPh * r24.bitmap.getHeight()), Bitmap.Config.ARGB_8888);
        Canvas r82 = new Canvas(r29);
        r82.translate(r29.getWidth() / 2.0f, r29.getHeight() / 2.0f);
        r82.rotate(-r24.crop.orientation);
        int r10 = r24.bitmap.getWidth();
        int r11 = r24.bitmap.getHeight();
        MediaController.CropState r12 = r24.crop;
        if ((((r12.orientation + r12.transformRotation) / 90) % 2) != 1) goto L41;
        r10 = r24.bitmap.getHeight();
        r11 = r24.bitmap.getWidth();
    L41:
        MediaController.CropState r13 = r24.crop;
        float r14 = r13.cropPw;
        float r132 = r13.cropPh;
        float r102 = r10;
        float r112 = r11;
        r82.clipRect(((-r10) * r14) / 2.0f, ((-r11) * r132) / 2.0f, (r14 * r102) / 2.0f, (r132 * r112) / 2.0f);
        float r122 = r24.crop.cropScale;
        r82.scale(r122, r122);
        MediaController.CropState r123 = r24.crop;
        r82.translate(r123.cropPx * r102, r123.cropPy * r112);
        r82.rotate(r24.crop.cropRotate + r10.transformRotation);
        if (r24.crop.mirrored == false) goto L44;
        r82.scale(-1.0f, 1.0f);
    L44:
        r82.rotate(r24.crop.orientation);
        r82.translate((-r24.bitmap.getWidth()) / 2.0f, (-r24.bitmap.getHeight()) / 2.0f);
        r82.drawBitmap(r24.bitmap, 0.0f, 0.0f, null);
        r24.bitmap.recycle();
        r24.bitmap = r29;
    L46:
        if (r24.type != 2) goto L55;
        if (r24.bitmap == null) goto L55;
        r24.roundRadius = AndroidUtilities.dp(12.0f) / Math.min(r24.viewWidth, r24.viewHeight);
        Pair<Integer, Integer> r210 = AndroidUtilities.getImageOrientation(r24.text);
        r24.rotation = (float) (r24.rotation - Math.toRadians(((Integer) r210.first).intValue()));
        if (((((Integer) r210.first).intValue() / 90) % 2) != 1) goto L52;
        float r211 = r24.x;
        float r42 = r24.width;
        float r212 = r211 + (r42 / 2.0f);
        float r5 = r24.y;
        float r6 = r24.height;
        float r52 = r5 + (r6 / 2.0f);
        int r83 = this.transformedWidth;
        int r9 = this.transformedHeight;
        float r43 = (r42 * r83) / r9;
        float r62 = (r6 * r9) / r83;
        r24.width = r62;
        r24.height = r43;
        r24.x = r212 - (r62 / 2.0f);
        r24.y = r52 - (r43 / 2.0f);
    L52:
        applyRoundRadius(r24, r24.bitmap, 0);
        return;
    L55:
        if (r24.bitmap == null) goto L66;
        float r0 = r0.getWidth() / r24.bitmap.getHeight();
        if (r0 <= 1.0f) goto L61;
        float r213 = r24.height;
        float r02 = r213 / r0;
        r24.y += (r213 - r02) / 2.0f;
        r24.height = r02;
        return;
    L61:
        if (r0 >= 1.0f) goto L67;
        float r214 = r24.width;
        float r03 = r0 * r214;
        r24.x += (r214 - r03) / 2.0f;
        r24.width = r03;
        return;
    L67:
        return;
    L66:
        return;
    L29:
        if ((r24.subType & 16) == 0) goto L31;
        r27 = r24.segmentedPath;
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
        r7.setText(Emoji.replaceEmoji(r11, r7.getPaint().getFontMetricsInt(), false));
        r7.setTextColor(r16.color);
        Editable r06 = r7.getText();
        if (r06 == null) goto L19;
        Emoji.EmojiSpan[] r07 = (Emoji.EmojiSpan[]) r06.getSpans(0, r06.length(), Emoji.EmojiSpan.class);
        int r22 = 0;
    L17:
        if (r22 >= r07.length) goto L19;
        r07[r22].scale = 0.85f;
        r22 = r22 + 1;
    L19:
        int r08 = r16.textAlign;
        if (r08 == 1) goto L24;
        if (r08 == 2) goto L23;
        int r09 = 19;
    L25:
        r7.setGravity(r09);
        int r010 = r16.textAlign;
        if (r010 == 1) goto L35;
        if (r010 == 2) goto L33;
        if (LocaleController.isRTL == false) goto L31;
    L30:
        int r011 = 3;
    L36:
        r7.setTextAlignment(r011);
        r7.setHorizontallyScrolling(false);
        r7.setImeOptions(268435456);
        r7.setFocusableInTouchMode(true);
        r7.setInputType(r7.getInputType() | 16384);
        setBreakStrategy(r7);
        byte r15 = r16.subType;
        int r012 = -1;
        if (r15 != 0) goto L43;
        r7.setFrameColor(r16.color);
        if (AndroidUtilities.computePerceivedBrightness(r16.color) < 0.721f) goto L41;
        r012 = -16777216;
    L41:
        r7.setTextColor(r012);
    L56:
        r7.measure(View.MeasureSpec.makeMeasureSpec(r16.viewWidth, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r16.viewHeight, TLObject.FLAG_30));
        r7.layout(0, 0, r16.viewWidth, r16.viewHeight);
        r16.bitmap = Bitmap.createBitmap(r16.viewWidth, r16.viewHeight, Bitmap.Config.ARGB_8888);
        r7.draw(new Canvas(r16.bitmap));
        return;
    L43:
        if (r15 == 1) goto L45;
        if (r15 == 2) goto L51;
        if (r15 != 3) goto L56;
        r7.setFrameColor(0);
        r7.setTextColor(r16.color);
        goto L56
    L51:
        if (AndroidUtilities.computePerceivedBrightness(r16.color) < 0.25f) goto L53;
        r012 = -16777216;
    L53:
        r7.setFrameColor(r012);
        r7.setTextColor(r16.color);
        goto L56
    L45:
        if (AndroidUtilities.computePerceivedBrightness(r16.color) < 0.25f) goto L47;
        int r152 = -1728053248;
    L48:
        r7.setFrameColor(r152);
        r7.setTextColor(r16.color);
        goto L56
    L47:
        r152 = -1711276033;
    L31:
        r011 = 2;
        goto L36
    L33:
        if (LocaleController.isRTL == false) goto L30;
    L35:
        r011 = 4;
        goto L36
    L23:
        r09 = 21;
        goto L25
    L24:
        r09 = 17;
        goto L25
    }

    private boolean isCollage() {
        if (this.collageParts == null) goto L6;
        return true;
    L6:
        return false;
    }

    private void stepCollagePart(int r10, VideoEditedInfo.Part r11, long r12) {
        long r2 = (r12 / 1000000) - r11.offset;
        float r122 = r11.right;
        long r0 = r11.duration;
        long r123 = Utilities.clamp(r2, (long) (r122 * r0), (long) (r11.left * r0));
        MediaCodecPlayer r02 = r11.player;
        if (r02 == null) goto L6;
        r02.ensure(r123);
        r11.surfaceTexture.updateTexImage();
        return;
    L6:
        AnimatedFileDrawable r03 = r11.animatedFileDrawable;
        if (r03 != null) goto L9;
        return;
    L9:
        if (r03.getProgressMs() > 0) goto L11;
        boolean r04 = true;
    L13:
        if (r123 < r11.animatedFileDrawable.getProgressMs()) goto L17;
        if (r04 == false) goto L18;
        if (r123 > 1000) goto L17;
    L18:
        float r3 = r123;
        if ((r11.animatedFileDrawable.getProgressMs() + (r11.msPerFrame * 2.0f)) >= r3) goto L22;
        long r5 = r11.animatedFileDrawable.getProgressMs();
        r11.animatedFileDrawable.skipNextFrame(false);
        if (r11.animatedFileDrawable.getProgressMs() != r5) goto L18;
    L22:
        if (r04 == false) goto L24;
    L25:
        Bitmap r112 = r11.animatedFileDrawable.getNextFrame(false);
        if (r112 == null) goto L35;
        GLES20.glBindTexture(3553, this.collageTextures[r10]);
        GLUtils.texImage2D(3553, 0, r112, 0);
        return;
    L35:
        return;
    L24:
        if (r3 > (r11.animatedFileDrawable.getProgressMs() - (r11.msPerFrame / 2.0f))) goto L25;
        return;
    L17:
        r11.animatedFileDrawable.seekToSync(r123);
        goto L18
    L11:
        r04 = false;
        goto L13
    }

    public void changeFragmentShader(String r4, String r5, boolean r6) {
        if (this.messageVideoMaskPath == null) goto L7;
        if (r6 == false) goto L6;
        String r0 = VERTEX_SHADER_MASK_300;
    L10:
        int r1 = this.NUM_EXTERNAL_SHADER;
        if (r1 >= 0) goto L13;
    L17:
        int r42 = this.NUM_FILTER_SHADER;
        if (r42 >= 0) goto L20;
        return;
    L20:
        if (r42 >= this.mProgram.length) goto L26;
        int r43 = createProgram(r0, r5, r6);
        if (r43 == 0) goto L27;
        GLES20.glDeleteProgram(this.mProgram[this.NUM_FILTER_SHADER]);
        this.mProgram[this.NUM_FILTER_SHADER] = r43;
        return;
    L27:
        return;
    L26:
        return;
    L13:
        if (r1 >= this.mProgram.length) goto L17;
        int r44 = createProgram(r0, r4, r6);
        if (r44 == 0) goto L17;
        GLES20.glDeleteProgram(this.mProgram[this.NUM_EXTERNAL_SHADER]);
        this.mProgram[this.NUM_EXTERNAL_SHADER] = r44;
        this.texSizeHandle = GLES20.glGetUniformLocation(r44, "texSize");
        goto L17
    L6:
        r0 = VERTEX_SHADER_MASK;
        goto L10
    L7:
        if (r6 == false) goto L9;
        r0 = VERTEX_SHADER_300;
        goto L10
    L9:
        r0 = VERTEX_SHADER;
        goto L10
    }

    public void drawFrame(SurfaceTexture r26, long r27) {
        int r13 = 0;
        if (this.isPhoto == false) goto L5;
        drawBackground();
        int r17 = 3042;
        int r4 = 0;
        int r16 = 33985;
        int r18 = 33984;
    L47:
        if (this.blur == null) goto L63;
        if (this.blendEnabled == true) goto L52;
        GLES20.glEnable(r17);
        GLES20.glBlendFunc(1, 771);
        this.blendEnabled = true;
    L52:
        if (this.imagePath == null) goto L56;
        int[] r1 = this.paintTexture;
        if (r1 == null) goto L56;
        int r12 = r1[0];
        int r42 = this.imageWidth;
        int r6 = this.imageHeight;
    L60:
        if (r12 == (-1)) goto L63;
        this.blur.draw(null, r12, r42, r6);
        GLES20.glViewport(0, 0, this.transformedWidth, this.transformedHeight);
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glUseProgram(this.blurShaderProgram);
        GLES20.glEnableVertexAttribArray(this.blurInputTexCoordHandle);
        GLES20.glVertexAttribPointer(this.blurInputTexCoordHandle, 2, 5126, false, 8, this.gradientTextureBuffer);
        GLES20.glEnableVertexAttribArray(this.blurPositionHandle);
        GLES20.glVertexAttribPointer(this.blurPositionHandle, 2, 5126, false, 8, this.blurVerticesBuffer);
        GLES20.glUniform1i(this.blurBlurImageHandle, 0);
        GLES20.glActiveTexture(r18);
        GLES20.glBindTexture(3553, this.blur.getTexture());
        GLES20.glUniform1i(this.blurMaskImageHandle, 1);
        GLES20.glActiveTexture(r16);
        GLES20.glBindTexture(3553, this.blurTexture[0]);
        GLES20.glDrawArrays(5, 0, 4);
    L56:
        FilterShaders r14 = this.filterShaders;
        if (r14 == null) goto L59;
        r12 = r14.getRenderTexture(r4 ^ 1);
        r42 = this.filterShaders.getRenderBufferWidth();
        r6 = this.filterShaders.getRenderBufferHeight();
        goto L60
    L59:
        r12 = -1;
        r42 = 1;
        r6 = 1;
    L63:
        if (isCollage() == false) goto L69;
        int r15 = 0;
    L66:
        if (r15 >= this.collageParts.size()) goto L69;
        stepCollagePart(r15, this.collageParts.get(r15), r27);
        drawCollagePart(r15, this.collageParts.get(r15), r27);
        r15 = r15 + 1;
    L69:
        if (this.isPhoto == false) goto L71;
    L74:
        GLES20.glUseProgram(this.simpleShaderProgram);
        GLES20.glActiveTexture(r18);
        GLES20.glUniform1i(this.simpleSourceImageHandle, 0);
        GLES20.glEnableVertexAttribArray(this.simpleInputTexCoordHandle);
        GLES20.glVertexAttribPointer(this.simpleInputTexCoordHandle, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(this.simplePositionHandle);
    L76:
        if (this.imagePathIndex >= 0) goto L78;
    L86:
        int r19 = this.paintPathIndex;
        if (r19 < 0) goto L89;
        drawTexture(true, this.paintTexture[r19], -10000.0f, -10000.0f, -10000.0f, -10000.0f, 0.0f, false, false, -1);
    L89:
        int r110 = this.messagePathIndex;
        if (r110 < 0) goto L93;
        drawTexture(true, this.paintTexture[r110], -10000.0f, -10000.0f, -10000.0f, -10000.0f, 0.0f, false, false, -1);
    L93:
        if (this.stickerTexture == null) goto L97;
        int r111 = this.mediaEntities.size();
    L95:
        if (r13 >= r111) goto L97;
        drawEntity(this.mediaEntities.get(r13), this.mediaEntities.get(r13).color, r27);
        r13 = r13 + 1;
    L97:
        GLES20.glFinish();
        return;
    L78:
        if (isCollage() == true) goto L86;
        int r2 = this.paintTexture[this.imagePathIndex];
        if (this.useMatrixForImagePath == true) goto L82;
    L84:
        boolean r9 = false;
    L85:
        drawTexture(true, r2, -10000.0f, -10000.0f, -10000.0f, -10000.0f, 0.0f, false, r9, -1);
        goto L86
    L82:
        if (this.isPhoto == false) goto L84;
        r9 = true;
        goto L85
    L71:
        if (this.paintTexture != null) goto L74;
        if (this.stickerTexture == null) goto L76;
    L5:
        r26.getTransformMatrix(this.mSTMatrix);
        if (BuildVars.LOGS_ENABLED == true) goto L8;
    L14:
        r16 = 33985;
    L16:
        if (this.blendEnabled == false) goto L18;
        GLES20.glDisable(3042);
        this.blendEnabled = false;
    L18:
        FilterShaders r112 = this.filterShaders;
        if (r112 == null) goto L26;
        r112.onVideoFrameUpdate(this.mSTMatrix);
        GLES20.glViewport(0, 0, this.originalWidth, this.originalHeight);
        this.filterShaders.drawSkinSmoothPass();
        this.filterShaders.drawEnhancePass();
        this.filterShaders.drawSharpenPass();
        this.filterShaders.drawCustomParamsPass();
        boolean r113 = this.filterShaders.drawBlurPass();
        GLES20.glBindFramebuffer(36160, 0);
        int r43 = this.transformedWidth;
        if (r43 == this.originalWidth) goto L23;
    L24:
        GLES20.glViewport(0, 0, r43, this.transformedHeight);
    L25:
        int r44 = this.filterShaders.getRenderTexture(!r113 ? 1 : 0);
        int r142 = this.NUM_FILTER_SHADER;
        r17 = 3042;
        float[] r62 = this.mSTMatrixIdentity;
        int r152 = r142;
        int r143 = r44;
        r4 = r113 ? 1 : 0;
        int r114 = 3553;
    L27:
        drawBackground();
        r18 = 33984;
        GLES20.glUseProgram(this.mProgram[r152]);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(r114, r143);
        if (this.messageVideoMaskPath != null) goto L30;
    L32:
        GLES20.glVertexAttribPointer(this.maPositionHandle[r152], 2, 5126, false, 8, this.verticesBuffer);
        GLES20.glEnableVertexAttribArray(this.maPositionHandle[r152]);
        int r192 = this.maTextureHandle[r152];
        if (this.useMatrixForImagePath == false) goto L36;
        FloatBuffer r115 = this.croppedTextureBuffer;
    L37:
        GLES20.glVertexAttribPointer(r192, 2, 5126, false, 8, r115);
        GLES20.glEnableVertexAttribArray(this.maTextureHandle[r152]);
        if (this.messageVideoMaskPath != null) goto L40;
    L42:
        int r116 = this.texSizeHandle;
        if (r116 == 0) goto L45;
        GLES20.glUniform2f(r116, this.transformedWidth, this.transformedHeight);
    L45:
        GLES20.glUniformMatrix4fv(this.muSTMatrixHandle[r152], 1, false, r62, 0);
        GLES20.glUniformMatrix4fv(this.muMVPMatrixHandle[r152], 1, false, this.mMVPMatrix, 0);
        GLES20.glDrawArrays(5, 0, 4);
        goto L47
    L40:
        if (this.videoMaskTexture == (-1)) goto L42;
        GLES20.glVertexAttribPointer(this.mmTextureHandle[r152], 2, 5126, false, 8, this.maskTextureBuffer);
        GLES20.glEnableVertexAttribArray(this.mmTextureHandle[r152]);
        goto L42
    L36:
        r115 = this.renderTextureBuffer;
        goto L37
    L30:
        if (this.videoMaskTexture == (-1)) goto L32;
        GLES20.glActiveTexture(r16);
        GLES20.glBindTexture(3553, this.videoMaskTexture);
        GLES20.glUniform1i(this.maskTextureHandle[r152], 1);
        goto L32
    L23:
        if (this.transformedHeight == this.originalHeight) goto L25;
    L26:
        int r45 = this.mTextureID;
        int r144 = this.NUM_EXTERNAL_SHADER;
        r114 = 36197;
        r17 = 3042;
        r62 = this.mSTMatrix;
        r152 = r144;
        r143 = r45;
        r4 = 0;
        goto L27
    L8:
        if (this.firstFrame == false) goto L14;
        StringBuilder r117 = new StringBuilder();
        int r145 = 0;
    L10:
        float[] r153 = this.mSTMatrix;
        r16 = 33985;
        if (r145 >= r153.length) goto L13;
        r117.append(r153[r145]);
        r117.append(", ");
        r145 = r145 + 1;
        goto L10
    L13:
        FileLog.d("stMatrix = " + r117);
        this.firstFrame = false;
        goto L16
    }

    public int getTextureId() {
        return this.mTextureID;
    }

    public void release() {
        ArrayList<VideoEditedInfo.MediaEntity> r0 = this.mediaEntities;
        if (r0 == null) goto L19;
        int r02 = r0.size();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L19;
        VideoEditedInfo.MediaEntity r3 = this.mediaEntities.get(r2);
        RLottieNative r4 = r3.lottieNative;
        if (r4 == null) goto L9;
        r4.recycle();
    L9:
        AnimatedFileDrawable r42 = r3.animatedFileDrawable;
        if (r42 == null) goto L12;
        r42.recycle();
    L12:
        View r43 = r3.view;
        if ((r43 instanceof EditTextEffects) == false) goto L15;
        ((EditTextEffects) r43).recycleEmojis();
    L15:
        Bitmap r44 = r3.bitmap;
        if (r44 == null) goto L18;
        r44.recycle();
        r3.bitmap = null;
    L18:
        r2 = r2 + 1;
    L19:
        ArrayList<VideoEditedInfo.Part> r03 = this.collageParts;
        if (r03 == null) goto L27;
        int r22 = r03.size();
        int r32 = 0;
    L22:
        if (r32 >= r22) goto L33;
        VideoEditedInfo.Part r45 = r03.get(r32);
        r32 = r32 + 1;
        int r46 = 0;
    L25:
        if (r46 >= this.collageParts.size()) goto L22;
        destroyCollagePart(r46, this.collageParts.get(r46));
        r46 = r46 + 1;
        goto L25
    L33:
        return;
    }

    public void setBreakStrategy(EditTextOutline r1) {
        r1.setBreakStrategy(0);
    }

    @SuppressLint({"WrongConstant"})
    public void surfaceCreated() {
        int r2 = 0;
        int r0 = 0;
    L3:
        int[] r3 = this.mProgram;
        String r5 = null;
        if (r0 >= r3.length) goto L32;
        int r4 = this.NUM_EXTERNAL_SHADER;
        String r6 = VERTEX_SHADER_MASK;
        String r7 = VERTEX_SHADER;
        if (r0 != r4) goto L16;
        String r42 = this.messageVideoMaskPath;
        if (r42 == null) goto L10;
        r5 = FRAGMENT_EXTERNAL_MASK_SHADER;
    L11:
        if (r42 != null) goto L14;
    L13:
        r6 = VERTEX_SHADER;
    L14:
        r7 = r6;
    L26:
        if (r5 == null) goto L31;
        r3[r0] = createProgram(r7, r5, false);
        this.maPositionHandle[r0] = GLES20.glGetAttribLocation(this.mProgram[r0], "aPosition");
        this.maTextureHandle[r0] = GLES20.glGetAttribLocation(this.mProgram[r0], "aTextureCoord");
        this.mmTextureHandle[r0] = GLES20.glGetAttribLocation(this.mProgram[r0], "mTextureCoord");
        this.muMVPMatrixHandle[r0] = GLES20.glGetUniformLocation(this.mProgram[r0], "uMVPMatrix");
        this.muSTMatrixHandle[r0] = GLES20.glGetUniformLocation(this.mProgram[r0], "uSTMatrix");
        this.maskTextureHandle[r0] = GLES20.glGetUniformLocation(this.mProgram[r0], "sMask");
        if (r0 != this.NUM_GRADIENT_SHADER) goto L31;
        this.gradientTopColorHandle = GLES20.glGetUniformLocation(this.mProgram[r0], "gradientTopColor");
        this.gradientBottomColorHandle = GLES20.glGetUniformLocation(this.mProgram[r0], "gradientBottomColor");
    L31:
        r0 = r0 + 1;
        goto L3
    L10:
        r5 = FRAGMENT_EXTERNAL_SHADER;
        goto L11
    L16:
        if (r0 != this.NUM_FILTER_SHADER) goto L24;
        String r43 = this.messageVideoMaskPath;
        if (r43 == null) goto L20;
        r5 = FRAGMENT_MASK_SHADER;
    L21:
        if (r43 == null) goto L13;
    L20:
        r5 = FRAGMENT_SHADER;
        goto L21
    L24:
        if (r0 != this.NUM_GRADIENT_SHADER) goto L26;
        r5 = GRADIENT_FRAGMENT_SHADER;
        goto L26
    L32:
        int[] r02 = new int[1];
        GLES20.glGenTextures(1, r02, 0);
        int r44 = r02[0];
        this.mTextureID = r44;
        GLES20.glBindTexture(36197, r44);
        GLES20.glTexParameteri(36197, 10241, 9729);
        GLES20.glTexParameteri(36197, 10240, 9729);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        if (this.messageVideoMaskPath == null) goto L39;
        GLES20.glGenTextures(1, r02, 0);     // Catch: Exception -> L36
        int r03 = r02[0];     // Catch: Exception -> L36
        this.videoMaskTexture = r03;     // Catch: Exception -> L36
        GLES20.glBindTexture(3553, r03);     // Catch: Exception -> L36
        GLES20.glTexParameteri(3553, 10241, 9729);     // Catch: Exception -> L36
        GLES20.glTexParameteri(3553, 10240, 9729);     // Catch: Exception -> L36
        GLES20.glTexParameteri(3553, 10242, 33071);     // Catch: Exception -> L36
        GLES20.glTexParameteri(3553, 10243, 33071);     // Catch: Exception -> L36
        Bitmap r04 = BitmapFactory.decodeFile(this.messageVideoMaskPath);     // Catch: Exception -> L36
        GLUtils.texImage2D(3553, 0, r04, 0);     // Catch: Exception -> L36
        r04.recycle();     // Catch: Exception -> L36
    L36:
        e = move-exception;
        FileLog.e(e);
        this.videoMaskTexture = -1;
    L39:
        if (this.blurPath == null) goto L63;
        MediaController.CropState r05 = this.cropState;
        if (r05 == null) goto L63;
        if (r05.useMatrix == null) goto L63;
        BlurringShader r06 = new BlurringShader();
        this.blur = r06;
        if (r06.setup(this.transformedWidth / this.transformedHeight, true, 0) == true) goto L47;
        this.blur = null;
    L48:
        Bitmap r07 = BitmapFactory.decodeFile(this.blurPath);
        if (r07 == null) goto L51;
        int[] r11 = new int[1];
        this.blurTexture = r11;
        GLES20.glGenTextures(1, r11, 0);
        GLES20.glBindTexture(3553, this.blurTexture[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLUtils.texImage2D(3553, 0, r07, 0);
        r07.recycle();
    L53:
        if (this.blur == null) goto L63;
        int r112 = FilterShaders.loadShader(35633, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = inputTexCoord;}");
        int r08 = FilterShaders.loadShader(35632, "varying highp vec2 vTextureCoord;uniform sampler2D blurImage;uniform sampler2D maskImage;void main() {gl_FragColor = texture2D(blurImage, vTextureCoord) * texture2D(maskImage, vTextureCoord).a;}");
        if (r112 == 0) goto L61;
        if (r08 == 0) goto L61;
        int r52 = GLES20.glCreateProgram();
        this.blurShaderProgram = r52;
        GLES20.glAttachShader(r52, r112);
        GLES20.glAttachShader(this.blurShaderProgram, r08);
        GLES20.glBindAttribLocation(this.blurShaderProgram, 0, "position");
        GLES20.glBindAttribLocation(this.blurShaderProgram, 1, "inputTexCoord");
        GLES20.glLinkProgram(this.blurShaderProgram);
        int[] r09 = new int[1];
        GLES20.glGetProgramiv(this.blurShaderProgram, 35714, r09, 0);
        int r010 = r09[0];
        int r53 = this.blurShaderProgram;
        if (r010 != 0) goto L60;
        GLES20.glDeleteProgram(r53);
        this.blurShaderProgram = 0;
        goto L63
    L60:
        this.blurPositionHandle = GLES20.glGetAttribLocation(r53, "position");
        this.blurInputTexCoordHandle = GLES20.glGetAttribLocation(this.blurShaderProgram, "inputTexCoord");
        this.blurBlurImageHandle = GLES20.glGetUniformLocation(this.blurShaderProgram, "blurImage");
        this.blurMaskImageHandle = GLES20.glGetUniformLocation(this.blurShaderProgram, "maskImage");
        FloatBuffer r54 = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.blurVerticesBuffer = r54;
        r54.put(new float[]{-1.0f, 1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f}).position(0);
    L61:
        this.blur = null;
        goto L63
    L51:
        this.blur = null;
        goto L53
    L47:
        this.blur.updateGradient(this.gradientTopColor, this.gradientBottomColor);
        android.graphics.Matrix r011 = new android.graphics.Matrix();
        r011.postScale(this.originalWidth, this.originalHeight);
        r011.postConcat(this.cropState.useMatrix);
        r011.postScale(1.0f / this.transformedWidth, 1.0f / this.transformedHeight);
        android.graphics.Matrix r113 = new android.graphics.Matrix();
        r011.invert(r113);
        this.blur.updateTransform(r113);
    L63:
        if (this.filterShaders == null) goto L65;
    L74:
        int r15 = FilterShaders.loadShader(35633, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = inputTexCoord;}");
        int r9 = FilterShaders.loadShader(35632, "varying highp vec2 vTextureCoord;uniform sampler2D sTexture;void main() {gl_FragColor = texture2D(sTexture, vTextureCoord);}");
        if (r15 == 0) goto L82;
        if (r9 == 0) goto L82;
        int r012 = GLES20.glCreateProgram();
        this.simpleShaderProgram = r012;
        GLES20.glAttachShader(r012, r15);
        GLES20.glAttachShader(this.simpleShaderProgram, r9);
        GLES20.glBindAttribLocation(this.simpleShaderProgram, 0, "position");
        GLES20.glBindAttribLocation(this.simpleShaderProgram, 1, "inputTexCoord");
        GLES20.glLinkProgram(this.simpleShaderProgram);
        int[] r013 = new int[1];
        GLES20.glGetProgramiv(this.simpleShaderProgram, 35714, r013, 0);
        int r014 = r013[0];
        int r92 = this.simpleShaderProgram;
        if (r014 != 0) goto L80;
        GLES20.glDeleteProgram(r92);
        this.simpleShaderProgram = 0;
        goto L82
    L80:
        this.simplePositionHandle = GLES20.glGetAttribLocation(r92, "position");
        this.simpleInputTexCoordHandle = GLES20.glGetAttribLocation(this.simpleShaderProgram, "inputTexCoord");
        this.simpleSourceImageHandle = GLES20.glGetUniformLocation(this.simpleShaderProgram, "sTexture");
    L82:
        if (isCollage() == false) goto L90;
        int r015 = FilterShaders.loadShader(35633, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = inputTexCoord;}");
        int r93 = FilterShaders.loadShader(35632, "#extension GL_OES_EGL_image_external : require\n" + "varying highp vec2 vTextureCoord;uniform sampler2D sTexture;void main() {gl_FragColor = texture2D(sTexture, vTextureCoord);}".replaceAll("sampler2D", "samplerExternalOES"));
        if (r015 == 0) goto L90;
        if (r93 == 0) goto L90;
        int r114 = GLES20.glCreateProgram();
        this.simpleShaderProgramOES = r114;
        GLES20.glAttachShader(r114, r015);
        GLES20.glAttachShader(this.simpleShaderProgramOES, r93);
        GLES20.glBindAttribLocation(this.simpleShaderProgramOES, 0, "position");
        GLES20.glBindAttribLocation(this.simpleShaderProgramOES, 1, "inputTexCoord");
        GLES20.glLinkProgram(this.simpleShaderProgramOES);
        int[] r016 = new int[1];
        GLES20.glGetProgramiv(this.simpleShaderProgramOES, 35714, r016, 0);
        int r017 = r016[0];
        int r94 = this.simpleShaderProgramOES;
        if (r017 != 0) goto L89;
        GLES20.glDeleteProgram(r94);
        this.simpleShaderProgramOES = 0;
        goto L90
    L89:
        this.simplePositionHandleOES = GLES20.glGetAttribLocation(r94, "position");
        this.simpleInputTexCoordHandleOES = GLES20.glGetAttribLocation(this.simpleShaderProgramOES, "inputTexCoord");
        this.simpleSourceImageHandleOES = GLES20.glGetUniformLocation(this.simpleShaderProgramOES, "sTexture");
    L90:
        FilterShaders r018 = this.filterShaders;
        if (r018 == null) goto L93;
        r018.create();
        this.filterShaders.setRenderData(null, 0, this.mTextureID, this.originalWidth, this.originalHeight);
    L93:
        String r019 = this.imagePath;
        if (r019 == null) goto L96;
    L99:
        if (r019 == null) goto L101;
        this.imagePathIndex = 0;
        int r020 = 1;
    L103:
        if (this.paintPath == null) goto L106;
        this.paintPathIndex = r020;
        r020 = r020 + 1;
    L106:
        if (this.messagePath == null) goto L109;
        this.messagePathIndex = r020;
        r020 = r020 + 1;
    L109:
        if (this.backgroundPath == null) goto L111;
        this.backgroundPathIndex = r020;
        r020 = r020 + 1;
    L111:
        int[] r021 = new int[r020];
        this.paintTexture = r021;
        GLES20.glGenTextures(r021.length, r021, 0);
        int r022 = 0;
    L191:
        if (r022 >= this.paintTexture.length) goto L156;
        if (r022 != this.imagePathIndex) goto L120;
        String r62 = this.imagePath;     // Catch: Throwable -> L117
        Pair<Integer, Integer> r95 = AndroidUtilities.getImageOrientation(r62);     // Catch: Throwable -> L117
        int r115 = ((Integer) r95.first).intValue();     // Catch: Throwable -> L117
        int r96 = ((Integer) r95.second).intValue();     // Catch: Throwable -> L117
    L127:
        Bitmap r63 = BitmapFactory.decodeFile(r62);     // Catch: Throwable -> L117
        if (r63 == null) goto L153;
        if (r022 != this.imagePathIndex) goto L150;
        if (this.useMatrixForImagePath == true) goto L150;
        Bitmap r13 = Bitmap.createBitmap(this.transformedWidth, this.transformedHeight, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L117
        r13.eraseColor(-16777216);     // Catch: Throwable -> L117
        Canvas r14 = new Canvas(r13);     // Catch: Throwable -> L117
        if (r115 != 90) goto L136;
    L139:
        float r22 = Math.max(r63.getHeight() / this.transformedWidth, r63.getWidth() / this.transformedHeight);     // Catch: Throwable -> L117
    L140:
        android.graphics.Matrix r10 = new android.graphics.Matrix();     // Catch: Throwable -> L117
        r10.postTranslate((-r63.getWidth()) / 2, (-r63.getHeight()) / 2);     // Catch: Throwable -> L117
        float r8 = -1.0f;
        if (r96 != 1) goto L143;
        float r152 = -1.0f;
    L144:
        float r153 = r152 / r22;     // Catch: Throwable -> L117
        if (r96 == 2) goto L148;
        r8 = 1.0f;
    L148:
        r10.postScale(r153, r8 / r22);     // Catch: Throwable -> L117
        r10.postRotate(r115);     // Catch: Throwable -> L117
        r10.postTranslate(r13.getWidth() / 2, r13.getHeight() / 2);     // Catch: Throwable -> L117
        r14.drawBitmap(r63, r10, new Paint(2));     // Catch: Throwable -> L117
        r63 = r13;
        goto L150
    L143:
        r152 = 1.0f;
        goto L144
    L136:
        if (r115 == 270) goto L139;
        r22 = Math.max(r63.getWidth() / this.transformedWidth, r63.getHeight() / this.transformedHeight);     // Catch: Throwable -> L117
    L150:
        if (r022 != this.imagePathIndex) goto L152;
        this.imageWidth = r63.getWidth();     // Catch: Throwable -> L117
        this.imageHeight = r63.getHeight();     // Catch: Throwable -> L117
    L152:
        GLES20.glBindTexture(3553, this.paintTexture[r022]);     // Catch: Throwable -> L117
        GLES20.glTexParameteri(3553, 10241, 9729);     // Catch: Throwable -> L117
        GLES20.glTexParameteri(3553, 10240, 9729);     // Catch: Throwable -> L117
        GLES20.glTexParameteri(3553, 10242, 33071);     // Catch: Throwable -> L117
        GLES20.glTexParameteri(3553, 10243, 33071);     // Catch: Throwable -> L117
        GLUtils.texImage2D(3553, 0, r63, 0);     // Catch: Throwable -> L117
    L153:
        r022 = r022 + 1;
        r2 = 0;
        goto L191
    L120:
        if (r022 != this.paintPathIndex) goto L124;
        r62 = this.paintPath;     // Catch: Throwable -> L117
    L122:
        r96 = r2;
        r115 = r96;
        goto L127
    L124:
        if (r022 != this.backgroundPathIndex) goto L126;
        r62 = this.backgroundPath;     // Catch: Throwable -> L117
        goto L122
    L126:
        r62 = this.messagePath;     // Catch: Throwable -> L117
    L156:
        if (isCollage() == false) goto L165;
        int[] r023 = new int[this.collageParts.size()];     // Catch: Exception -> L162
        this.collageTextures = r023;     // Catch: Exception -> L162
        GLES20.glGenTextures(r023.length, r023, 0);     // Catch: Exception -> L162
        int r23 = 0;
    L158:
        if (r23 >= this.collageParts.size()) goto L165;
        initCollagePart(r23, this.collageParts.get(r23));     // Catch: Exception -> L162
        r23 = r23 + 1;
    L162:
        e = move-exception;
        FileLog.e(e);
    L165:
        if (this.mediaEntities == null) goto L167;
    L195:
        this.stickerBitmap = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L186
        int[] r024 = new int[1];     // Catch: Throwable -> L186
        this.stickerTexture = r024;     // Catch: Throwable -> L186
        int r24 = 0;
        GLES20.glGenTextures(1, r024, 0);     // Catch: Throwable -> L186
        GLES20.glBindTexture(3553, this.stickerTexture[0]);     // Catch: Throwable -> L186
        GLES20.glTexParameteri(3553, 10241, 9729);     // Catch: Throwable -> L186
        GLES20.glTexParameteri(3553, 10240, 9729);     // Catch: Throwable -> L186
        GLES20.glTexParameteri(3553, 10242, 33071);     // Catch: Throwable -> L186
        GLES20.glTexParameteri(3553, 10243, 33071);     // Catch: Throwable -> L186
        int r025 = this.mediaEntities.size();     // Catch: Throwable -> L186
    L169:
        if (r24 >= r025) goto L212;
        VideoEditedInfo.MediaEntity r45 = this.mediaEntities.get(r24);     // Catch: Throwable -> L186
        byte r64 = r45.type;     // Catch: Throwable -> L186
        if (r64 == 0) goto L184;
        if (r64 == 2) goto L184;
        if (r64 == 5) goto L184;
        if (r64 != 1) goto L179;
        initTextEntity(r45);     // Catch: Throwable -> L186
    L185:
        r24 = r24 + 1;
        goto L169
    L179:
        if (r64 != 3) goto L182;
        initLocationEntity(r45);     // Catch: Throwable -> L186
        goto L185
    L182:
        if (r64 != 7) goto L185;
        initLinkEntity(r45);     // Catch: Throwable -> L186
    L184:
        initStickerEntity(r45);     // Catch: Throwable -> L186
        goto L185
    L212:
        return;
    L186:
        th = move-exception;
        FileLog.e(th);
        return;
    L167:
        if (this.backgroundDrawable != null) goto L195;
        return;
    L117:
        th = move-exception;
        FileLog.e(th);
        goto L156
    L101:
        r020 = 0;
        goto L103
    L96:
        if (this.paintPath != null) goto L99;
        if (this.messagePath == null) goto L156;
    L65:
        if (this.imagePath != null) goto L74;
        if (this.paintPath != null) goto L74;
        if (this.messagePath != null) goto L74;
        if (this.mediaEntities != null) goto L74;
        if (isCollage() == false) goto L82;
        goto L74
    }
}
