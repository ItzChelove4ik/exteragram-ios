package org.telegram.messenger.video;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Build;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.signature.SignatureVisitor;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.video.audio_input.AudioInput;
import org.telegram.messenger.video.audio_input.GeneralAudioInput;
import org.telegram.ui.Stories.recorder.CollageLayout;
import org.telegram.ui.Stories.recorder.StoryEntry;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class MediaCodecVideoConvertor {
    private static final int MEDIACODEC_TIMEOUT_DEFAULT = 2500;
    private static final int MEDIACODEC_TIMEOUT_INCREASED = 22000;
    private static final int PROCESSOR_TYPE_INTEL = 2;
    private static final int PROCESSOR_TYPE_MTK = 3;
    private static final int PROCESSOR_TYPE_OTHER = 0;
    private static final int PROCESSOR_TYPE_QCOM = 1;
    private static final int PROCESSOR_TYPE_SEC = 4;
    private static final int PROCESSOR_TYPE_TI = 5;
    private MediaController.VideoConvertorListener callback;
    private long endPresentationTime;
    private MediaExtractor extractor;
    private Muxer muxer;
    private String outputMimeType;

    public class ConversionCanceledException extends RuntimeException {
        final /* synthetic */ MediaCodecVideoConvertor this$0;

        public ConversionCanceledException(MediaCodecVideoConvertor r1) {
            this.this$0 = r1;
            super("canceled conversion");
        }
    }

    public static class ConvertVideoParams {
        int account;
        long avatarStartTime;
        String backgroundPath;
        int bitrate;
        String blurPath;
        File cacheFile;
        MediaController.VideoConvertorListener callback;
        CollageLayout collage;
        ArrayList<VideoEditedInfo.Part> collageParts;
        MediaController.CropState cropState;
        long duration;
        long endTime;
        int framerate;
        Integer gradientBottomColor;
        Integer gradientTopColor;
        StoryEntry.HDRInfo hdrInfo;
        boolean isDark;
        boolean isPhoto;
        boolean isRound;
        boolean isSecret;
        boolean isSticker;
        boolean isStory;
        ArrayList<VideoEditedInfo.MediaEntity> mediaEntities;
        String messagePath;
        String messageVideoMaskPath;
        boolean muted;
        boolean needCompress;
        int originalBitrate;
        int originalHeight;
        int originalWidth;
        String paintPath;
        int resultHeight;
        int resultWidth;
        int rotationValue;
        MediaController.SavedFilterState savedFilterState;
        public ArrayList<MixedSoundInfo> soundInfos;
        long startTime;
        long videoOffset;
        String videoPath;
        float volume;
        long wallpaperPeerId;

        private ConvertVideoParams() {
            this.soundInfos = new ArrayList();
        }

        public static ConvertVideoParams of(String r2, File r3, long r4, int r6, boolean r7, int r8, int r9, int r10, int r11, int r12, int r13, int r14, long r15, long r17, long r19, boolean r21, long r22, MediaController.VideoConvertorListener r24, VideoEditedInfo r25) {
            ConvertVideoParams r1 = new ConvertVideoParams();
            r1.videoPath = r2;
            r1.videoOffset = r4;
            r1.cacheFile = r3;
            r1.rotationValue = r6;
            r1.isSecret = r7;
            r1.originalWidth = r8;
            r1.originalHeight = r9;
            r1.resultWidth = r10;
            r1.resultHeight = r11;
            r1.framerate = r12;
            r1.bitrate = r13;
            r1.originalBitrate = r14;
            r1.startTime = r15;
            r1.endTime = r17;
            r1.avatarStartTime = r19;
            r1.needCompress = r21;
            r1.duration = r22;
            r1.savedFilterState = r25.filterState;
            r1.paintPath = r25.paintPath;
            r1.blurPath = r25.blurPath;
            r1.mediaEntities = r25.mediaEntities;
            r1.isPhoto = r25.isPhoto;
            r1.cropState = r25.cropState;
            r1.isRound = r25.roundVideo;
            r1.callback = r24;
            r1.gradientTopColor = r25.gradientTopColor;
            r1.gradientBottomColor = r25.gradientBottomColor;
            r1.muted = r25.muted;
            r1.volume = r25.volume;
            r1.isStory = r25.isStory;
            r1.hdrInfo = r25.hdrInfo;
            r1.isDark = r25.isDark;
            r1.wallpaperPeerId = r25.wallpaperPeerId;
            r1.account = r25.account;
            r1.messagePath = r25.messagePath;
            r1.messageVideoMaskPath = r25.messageVideoMaskPath;
            r1.backgroundPath = r25.backgroundPath;
            r1.isSticker = r25.isSticker;
            r1.collage = r25.collage;
            r1.collageParts = r25.collageParts;
            return r1;
        }
    }

    public static class MixedSoundInfo {
        final String audioFile;
        public long audioOffset;
        public long duration;
        public long startTime;
        public float volume;

        public MixedSoundInfo(String r2) {
            this.volume = 1.0f;
            this.audioFile = r2;
        }
    }

    public static class Muxer {
        public final MediaMuxer mediaMuxer;
        public final MP4Builder mp4Builder;
        private boolean started;

        public Muxer(MediaMuxer r2) {
            this.started = false;
            this.mp4Builder = null;
            this.mediaMuxer = r2;
        }

        public Muxer(MP4Builder r2) {
            this.started = false;
            this.mp4Builder = r2;
            this.mediaMuxer = null;
        }

        public int addTrack(MediaFormat r2, boolean r3) {
            MediaMuxer r0 = this.mediaMuxer;
            if (r0 != null) goto L5;
            MP4Builder r1 = this.mp4Builder;
            if (r1 != null) goto L9;
            return 0;
        L9:
            return r1.addTrack(r2, r3);
        L5:
            return r0.addTrack(r2);
        }

        public void finishMovie() {
            MediaMuxer r0 = this.mediaMuxer;
            if (r0 == null) goto L6;
            r0.stop();
            this.mediaMuxer.release();
            return;
        L6:
            MP4Builder r1 = this.mp4Builder;
            if (r1 == null) goto L10;
            r1.finishMovie();
            return;
        }

        public long getLastFrameTimestamp(int r2, MediaCodec.BufferInfo r3) {
            if (this.mediaMuxer != null) goto L5;
            MP4Builder r1 = this.mp4Builder;
            if (r1 != null) goto L9;
            return 0;
        L9:
            return r1.getLastFrameTimestamp(r2);
        L5:
            return r3.presentationTimeUs;
        }

        public void start() {
            MediaMuxer r0 = this.mediaMuxer;
            if (r0 == null) goto L6;
            r0.start();
            return;
        }

        public long writeSampleData(int r4, ByteBuffer r5, MediaCodec.BufferInfo r6, boolean r7) {
            MediaMuxer r0 = this.mediaMuxer;
            if (r0 != null) goto L5;
            MP4Builder r3 = this.mp4Builder;
            if (r3 != null) goto L12;
            return 0;
        L12:
            return r3.writeSampleData(r4, r5, r6, r7);
        L5:
            if (this.started == true) goto L7;
            r0.start();
            this.started = true;
        L7:
            this.mediaMuxer.writeSampleData(r4, r5, r6);
            return 0;
        }
    }

    public MediaCodecVideoConvertor() {
    }

    private static void applyAudioInputs(ArrayList<MixedSoundInfo> r9, ArrayList<AudioInput> r10) {
        if (r9 == null) goto L23;
        int r0 = 0;
    L6:
        if (r0 >= r9.size()) goto L29;
        MixedSoundInfo r1 = r9.get(r0);
        GeneralAudioInput r2 = new GeneralAudioInput(r1.audioFile);     // Catch: Exception -> L20
        r2.setVolume(r1.volume);
        long r3 = r1.startTime;
        if (r3 <= 0) goto L12;
        r2.setStartOffsetUs(r3);
    L12:
        long r32 = r1.audioOffset;
        if (r32 <= 0) goto L15;
        r2.setStartTimeUs(r32);
    L16:
        long r7 = r1.duration;
        if (r7 <= 0) goto L19;
        r2.setEndTimeUs(r32 + r7);
    L19:
        r10.add(r2);
    L22:
        r0 = r0 + 1;
        goto L6
    L15:
        r32 = 0;
    L20:
        e = move-exception;
        FileLog.e(e);
        goto L22
    L29:
        return;
    }

    private void checkConversionCanceled() {
        MediaController.VideoConvertorListener r0 = this.callback;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.checkConversionCanceled() == true) goto L8;
        return;
    L8:
        throw new ConversionCanceledException(this);
    }

    /*  JADX ERROR: NullPointerException in pass: ConstructorVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(jadx.core.dex.instructions.args.InsnArg)" because "resultArg" is null
        	at jadx.core.dex.visitors.MoveInlineVisitor.processMove(MoveInlineVisitor.java:52)
        	at jadx.core.dex.visitors.MoveInlineVisitor.moveInline(MoveInlineVisitor.java:41)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:43)
        */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 7 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 8 */
    private boolean convertVideoInternal(
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r134v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:405)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */
    /*  JADX ERROR: NullPointerException in pass: ConstructorVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(jadx.core.dex.instructions.args.InsnArg)" because "resultArg" is null
        	at jadx.core.dex.visitors.MoveInlineVisitor.processMove(MoveInlineVisitor.java:52)
        	at jadx.core.dex.visitors.MoveInlineVisitor.moveInline(MoveInlineVisitor.java:41)
        */

    private MediaCodec createEncoderForMimeType() {
        if (this.outputMimeType.equals("video/hevc") == false) goto L11;
        if (Build.VERSION.SDK_INT < 29) goto L11;
        String r0 = SharedConfig.findGoodHevcEncoder();
        if (r0 == null) goto L9;
        MediaCodec r02 = MediaCodec.createByCodecName(r0);
    L14:
        if (r02 == null) goto L16;
    L19:
        return r02;
    L16:
        if (this.outputMimeType.equals("video/hevc") == false) goto L19;
        this.outputMimeType = MediaController.VIDEO_MIME_TYPE;
        return MediaCodec.createEncoderByType(MediaController.VIDEO_MIME_TYPE);
    L9:
        r02 = null;
    L11:
        if (this.outputMimeType.equals("video/hevc") == false) goto L13;
        this.outputMimeType = MediaController.VIDEO_MIME_TYPE;
    L13:
        r02 = MediaCodec.createEncoderByType(this.outputMimeType);
        goto L14
    }

    private static String createFragmentShader(int r16, int r17, int r18, int r19, boolean r20, int r21, boolean r22) {
        if (r22 == false) goto L5;
        int r4 = r19;
    L6:
        if (r22 == false) goto L8;
        int r5 = r18;
    L9:
        float r6 = r16;
        float r42 = r6 / r4;
        float r7 = r17;
        float r52 = r7 / r5;
        int r9 = 1;
        int r8 = Math.max(1, Math.round(r42));
        int r10 = Math.max(1, Math.round(r52));
        if (SharedConfig.deviceIsAverage() == false) goto L12;
        r10 = 1;
    L13:
        int r82 = Math.min(r21, r9);
        int r2 = Math.min(r21, r10);
        float r92 = r42 / r82;
        float r102 = r52 / r2;
        float r11 = (-(r82 - 1)) / 2.0f;
        float r13 = (-(r2 - 1)) / 2.0f;
        if ((r82 & 1) != 0) goto L17;
        r11 = r11 + 0.01f;
    L17:
        if ((r2 & 1) != 0) goto L19;
        r13 = r13 + 0.01f;
    L19:
        FileLog.d("source size " + r16 + "x" + r17 + "    dest size " + r18 + "x" + r19 + "   rotated " + r22 + "   ratio " + r42 + "x" + r52 + "   samples " + r82 + "x" + r2 + "   kernel scale " + r92 + "x" + r102);
        String r0 = glslFloat(r11);
        String r1 = glslFloat(r13);
        String r3 = glslFloat(r92);
        String r43 = glslFloat(r102);
        String r53 = glslFloat((float) (r82 * r2));
        String r62 = glslFloat(1.0f / r6);
        String r72 = glslFloat(1.0f / r7);
        if (r20 == false) goto L22;
        String r93 = "#extension GL_OES_EGL_image_external : require\nuniform samplerExternalOES sTexture;\n";
    L24:
        return r93 + "precision highp float;\nvarying vec2 vTextureCoord;\nconst float offsetX = " + r0 + ";\nconst float offsetY = " + r1 + ";\nconst float kernelScaleX = " + r3 + ";\nconst float kernelScaleY = " + r43 + ";\nconst float weightsum = " + r53 + ";\nconst float pixelSizeX = " + r62 + ";\nconst float pixelSizeY = " + r72 + ";\nvoid main() {\n    vec3 accumulation = vec3(0.0);\n    for (int i = 0; i < " + r82 + "; ++i) {\n        for (int j = 0; j < " + r2 + "; ++j) {\n            float x = (offsetX + float(i)) * kernelScaleX;\n            float y = (offsetY + float(j)) * kernelScaleY;\n            vec2 uv = vTextureCoord + vec2(\n                    x * pixelSizeX,\n                    y * pixelSizeY\n            );\n            accumulation += texture2D(sTexture, uv).rgb;\n        }\n    }\n    gl_FragColor = vec4(accumulation / weightsum, 1.0);\n}\n";
    L22:
        r93 = "uniform sampler2D sTexture;\n";
        goto L24
    L12:
        r9 = r8;
        goto L13
    L8:
        r5 = r19;
        goto L9
    L5:
        r4 = r18;
        goto L6
    }

    public static void cutOfNalData(String r4, ByteBuffer r5, MediaCodec.BufferInfo r6) {
        if (r4.equals("video/hevc") == false) goto L5;
        int r42 = 3;
    L7:
        if (r6.size <= 100) goto L24;
        r5.position(r6.offset);
        byte[] r1 = new byte[100];
        r5.get(r1);
        int r52 = 0;
        int r2 = 0;
    L10:
        if (r52 >= 96) goto L32;
        if (r1[r52] != 0) goto L23;
        if (r1[r52 + 1] != 0) goto L23;
        if (r1[r52 + 2] != 0) goto L23;
        if (r1[r52 + 3] != 1) goto L23;
        r2 = r2 + 1;
        if (r2 <= r42) goto L23;
        r6.offset += r52;
        r6.size -= r52;
        return;
    L23:
        r52 = r52 + 1;
        goto L10
    L32:
        return;
    L24:
        return;
    L5:
        r42 = 1;
        goto L7
    }

    private MediaCodec getDecoderByFormat(MediaFormat r5) {
        MediaCodec r4 = null;
        if (r5 == null) goto L18;
        ArrayList r0 = new ArrayList();
        String r2 = r5.getString("mime");
        r0.add(r2);
        if ("video/dolby-vision".equals(r2) == false) goto L7;
        r0.add("video/hevc");
        r0.add(MediaController.VIDEO_MIME_TYPE);
    L7:
        Exception r22 = null;
    L9:
        if (r0.isEmpty() == true) goto L16;
        String r3 = (String) r0.remove(0);     // Catch: Exception -> L13
        r5.setString("mime", r3);     // Catch: Exception -> L13
        return MediaCodec.createDecoderByType(r3);
    L13:
        e = move-exception;
        if (r22 != null) goto L9;
        r22 = e;
        goto L9
    L16:
        HttpUrl$$ExternalSyntheticBUOutline0.m(r22);
        return r4;
    L18:
        GlShader$$ExternalSyntheticBUOutline1.m("getDecoderByFormat: format is null");
        return null;
    }

    private static String glslFloat(float r7) {
        if (r7 >= 0.0f) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == false) goto L8;
        r7 = -r7;
    L8:
        long r1 = Math.round(r7 * 1000000.0f);
        long r5 = r1 / 1000000;
        long r12 = r1 % 1000000;
        StringBuilder r72 = new StringBuilder();
        if (r0 == false) goto L11;
        r72.append(SignatureVisitor.SUPER);
    L11:
        r72.append(r5);
        r72.append('.');
        String r02 = String.valueOf(r12);
        int r13 = r02.length();
    L13:
        if (r13 >= 6) goto L15;
        r72.append('0');
        r13 = r13 + 1;
        goto L13
    L15:
        r72.append(r02);
        return r72.toString();
    L5:
        r0 = false;
        goto L6
    }

    private static String hdrFragmentShader(int r16, int r17, int r18, int r19, boolean r20, StoryEntry.HDRInfo r21, int r22, boolean r23) {
        if (r20 == true) goto L6;
        return createFragmentShader(r16, r17, r18, r19, false, r22, r23);
    L6:
        if (r23 == false) goto L8;
        int r2 = r19;
    L9:
        if (r23 == false) goto L11;
        int r3 = r18;
    L12:
        float r4 = r16;
        float r24 = r4 / r2;
        float r7 = r17;
        float r32 = r7 / r3;
        int r8 = Math.max(1, Math.round(r24));
        int r10 = Math.max(1, Math.round(r32));
        if (SharedConfig.deviceIsAverage() == false) goto L15;
        r8 = 1;
        r10 = 1;
    L15:
        int r82 = Math.min(r22, r8);
        int r5 = Math.min(r22, r10);
        float r102 = r24 / r82;
        float r11 = r32 / r5;
        float r12 = (-(r82 - 1)) / 2.0f;
        float r14 = (-(r5 - 1)) / 2.0f;
        if ((r82 & 1) != 0) goto L19;
        r12 = r12 + 0.01f;
    L19:
        if ((r5 & 1) != 0) goto L21;
        r14 = r14 + 0.01f;
    L21:
        FileLog.d("HDR source size " + r16 + "x" + r17 + "    dest size " + r18 + "x" + r19 + "   rotated " + r23 + "   ratio " + r24 + "x" + r32 + "   samples " + r82 + "x" + r5 + "   kernel scale " + r102 + "x" + r11);
        String r0 = glslFloat(r12);
        String r1 = glslFloat(r14);
        String r25 = glslFloat(r102);
        String r33 = glslFloat(r11);
        String r6 = glslFloat((float) (r82 * r5));
        String r42 = glslFloat(1.0f / r4);
        String r72 = glslFloat(1.0f / r7);
        if (r21.getHDRType() != 1) goto L24;
        String r9 = AndroidUtilities.readRes(R.raw.hdr2sdr_hlg);
    L26:
        return r9 + "\nvarying vec2 vTextureCoord;\nconst float offsetX = " + r0 + ";\nconst float offsetY = " + r1 + ";\nconst float kernelScaleX = " + r25 + ";\nconst float kernelScaleY = " + r33 + ";\nconst float weightsum = " + r6 + ";\nconst float pixelSizeX = " + r42 + ";\nconst float pixelSizeY = " + r72 + ";\nvoid main() {\n    vec3 accumulation = vec3(0.0);\n    for (int i = 0; i < " + r82 + "; ++i) {\n        for (int j = 0; j < " + r5 + "; ++j) {\n            float x = (offsetX + float(i)) * kernelScaleX;\n            float y = (offsetY + float(j)) * kernelScaleY;\n            vec2 uv = vTextureCoord + vec2(\n                    x * pixelSizeX,\n                    y * pixelSizeY\n            );\n            accumulation += TEX(uv).rgb;\n        }\n    }\n    gl_FragColor = vec4(accumulation / weightsum, 1.0);\n}\n";
    L24:
        r9 = AndroidUtilities.readRes(R.raw.hdr2sdr_pq);
        goto L26
    L11:
        r3 = r19;
        goto L12
    L8:
        r2 = r18;
        goto L9
    }

    private boolean isMediatekAvcEncoder(MediaCodec r1) {
        return r1.getName().equals("c2.mtk.avc.encoder");
    }

    private long readAndWriteTracks(MediaExtractor r29, Muxer r30, MediaCodec.BufferInfo r31, long r32, long r34, long r36, File r38, boolean r39) {
        int r6 = 0;
        int r7 = MediaController.findTrack(r29, false);
        if (r39 == false) goto L6;
        int r10 = MediaController.findTrack(r29, true);
    L7:
        float r12 = r36 / 1000.0f;
        if (r7 < 0) goto L19;
        r29.selectTrack(r7);
        MediaFormat r0 = r29.getTrackFormat(r7);
        int r16 = r30.addTrack(r0, false);
        int r02 = r0.getInteger("max-input-size");     // Catch: Exception -> L12
    L15:
        if (r32 <= 0) goto L17;
        r29.seekTo(r32, 0);
    L18:
        float r362 = 1000.0f;
        int r11 = r02;
    L20:
        if (r10 < 0) goto L33;
        r29.selectTrack(r10);
        MediaFormat r03 = r29.getTrackFormat(r10);
        if (r03.getString("mime").equals("audio/unknown") == false) goto L24;
        int r8 = -1;
        r10 = -1;
    L34:
        if (r11 > 0) goto L36;
        r11 = 65536;
    L36:
        ByteBuffer r04 = ByteBuffer.allocateDirect(r11);
        long r13 = -1;
        if (r10 >= 0) goto L41;
        if (r7 >= 0) goto L41;
        return -1;
    L41:
        checkConversionCanceled();
        boolean r15 = false;
        long r21 = -1;
        long r19 = 0;
    L42:
        if (r15 == true) goto L125;
        checkConversionCanceled();
        long r23 = r13;
        if (Build.VERSION.SDK_INT < 28) goto L48;
        long r132 = r29.getSampleSize();
        int r37 = r10;
        if (r132 <= r11) goto L49;
        int r05 = (int) (r132 + 1024);
        r11 = r05;
        r04 = ByteBuffer.allocateDirect(r05);
    L49:
        r31.size = r29.readSampleData(r04, r6);
        int r9 = r29.getSampleTrackIndex();
        if (r9 != r7) goto L53;
        r10 = r37;
        int r133 = r16;
    L57:
        if (r133 == (-1)) goto L118;
        if (r9 == r10) goto L85;
        byte[] r14 = r04.array();
        if (r14 == null) goto L85;
        int r25 = r04.arrayOffset();
        int r26 = r25 + r04.limit();
        int r4 = r25;
        int r62 = -1;
    L62:
        int r5 = r26 - 4;
        if (r4 > r5) goto L85;
        if (r14[r4] == 0) goto L67;
    L74:
        int r392 = r8;
        int r252 = r11;
    L75:
        if (r4 != r5) goto L84;
    L77:
        if (r62 == (-1)) goto L83;
        int r82 = r4 - r62;
        if (r4 == r5) goto L81;
        int r52 = 4;
    L82:
        int r83 = r82 - r52;
        r14[r62] = (byte) (r83 >> 24);
        r14[r62 + 1] = (byte) (r83 >> 16);
        r14[r62 + 2] = (byte) (r83 >> 8);
        r14[r62 + 3] = (byte) r83;
        goto L83
    L81:
        r52 = 0;
    L83:
        r62 = r4;
    L84:
        r4 = r4 + 1;
        r8 = r392;
        r11 = r252;
        goto L62
    L67:
        if (r14[r4 + 1] != 0) goto L74;
        if (r14[r4 + 2] != 0) goto L74;
        r392 = r8;
        r252 = r11;
        if (r14[r4 + 3] == 1) goto L77;
    L85:
        int r393 = r8;
        int r253 = r11;
        if (r31.size < 0) goto L88;
        r31.presentationTimeUs = r29.getSampleTime();
        boolean r42 = false;
    L90:
        if (r31.size <= 0) goto L115;
        if (r42 == true) goto L115;
        if (r9 != r7) goto L100;
        if (r32 <= 0) goto L100;
        if (r21 != r23) goto L100;
        r21 = r31.presentationTimeUs;
    L100:
        if (r34 < 0) goto L107;
        if (r31.presentationTimeUs < r34) goto L107;
        r42 = true;
    L107:
        r31.offset = 0;
        r31.flags = r29.getSampleFlags();
        long r84 = r30.writeSampleData(r133, r04, r31, false);
        if (r84 == 0) goto L115;
        MediaController.VideoConvertorListener r112 = this.callback;
        if (r112 == null) goto L115;
        long r134 = r31.presentationTimeUs;
        if ((r134 - r21) <= r19) goto L114;
        r19 = r134 - r21;
    L114:
        long r135 = r19;
        r112.didWriteData(r84, (r135 / r362) / r12);
        r19 = r135;
    L115:
        if (r42 == true) goto L122;
        r29.advance();
    L122:
        if (r42 == false) goto L124;
        r15 = true;
    L124:
        r8 = r393;
        r13 = r23;
        r11 = r253;
        r6 = 0;
        goto L42
    L88:
        r31.size = 0;
        r42 = true;
        goto L90
    L118:
        r393 = r8;
        r253 = r11;
        if (r9 != (-1)) goto L121;
        r42 = true;
        goto L122
    L121:
        r29.advance();
        r42 = false;
        goto L122
    L53:
        r10 = r37;
        if (r9 != r10) goto L56;
        r133 = r8;
        goto L57
    L56:
        r133 = -1;
        goto L57
    L48:
        r37 = r10;
        goto L49
    L125:
        if (r7 < 0) goto L127;
        r29.unselectTrack(r7);
    L127:
        if (r10 < 0) goto L129;
        r29.unselectTrack(r10);
    L129:
        return r21;
    L24:
        r8 = r30.addTrack(r03, true);
        r11 = Math.max(r03.getInteger("max-input-size"), r11);     // Catch: Exception -> L27
    L30:
        if (r32 <= 0) goto L32;
        r29.seekTo(r32, 0);
        goto L34
    L32:
        r29.seekTo(0, 0);
    L27:
        e = move-exception;
        FileLog.e(e);
        goto L30
    L33:
        r8 = -1;
        goto L34
    L17:
        r29.seekTo(0, 0);
    L12:
        e = move-exception;
        FileLog.e(e);
        r02 = 0;
        goto L15
    L19:
        r362 = 1000.0f;
        r16 = -1;
        r11 = 0;
        goto L20
    L6:
        r10 = -1;
        goto L7
    }

    public boolean convertVideo(ConvertVideoParams r3) {
        if (r3.isSticker == true) goto L5;
        this.callback = r3.callback;
        return convertVideoInternal(r3, false, 0);
    L5:
        return WebmEncoder.convert(r3, 0);
    }

    public long getLastFrameTimestamp() {
        return this.endPresentationTime;
    }
}
