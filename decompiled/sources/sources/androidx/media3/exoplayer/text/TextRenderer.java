package androidx.media3.exoplayer.text;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.extractor.text.CueDecoder;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleDecoder;
import androidx.media3.extractor.text.SubtitleDecoderException;
import androidx.media3.extractor.text.SubtitleInputBuffer;
import androidx.media3.extractor.text.SubtitleOutputBuffer;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class TextRenderer extends BaseRenderer implements Handler.Callback {
    private final CueDecoder cueDecoder;
    private final DecoderInputBuffer cueDecoderInputBuffer;
    private CuesResolver cuesResolver;
    private int decoderReplacementState;
    private long finalStreamEndPositionUs;
    private final FormatHolder formatHolder;
    private boolean inputStreamEnded;
    private long lastRendererPositionUs;
    private boolean legacyDecodingEnabled;
    private SubtitleOutputBuffer nextSubtitle;
    private int nextSubtitleEventIndex;
    private SubtitleInputBuffer nextSubtitleInputBuffer;
    private final TextOutput output;
    private final Handler outputHandler;
    private boolean outputStreamEnded;
    private Format streamFormat;
    private SubtitleOutputBuffer subtitle;
    private SubtitleDecoder subtitleDecoder;
    private final SubtitleDecoderFactory subtitleDecoderFactory;
    private boolean waitingForKeyFrame;

    public TextRenderer(TextOutput r2, Looper r3) {
        this(r2, r3, SubtitleDecoderFactory.DEFAULT);
    }

    public TextRenderer(TextOutput r2, Looper r3, SubtitleDecoderFactory r4) {
        super(3);
        this.output = (TextOutput) Assertions.checkNotNull(r2);
        if (r3 != null) goto L5;
        Handler r22 = null;
    L6:
        this.outputHandler = r22;
        this.subtitleDecoderFactory = r4;
        this.cueDecoder = new CueDecoder();
        this.cueDecoderInputBuffer = new DecoderInputBuffer(1);
        this.formatHolder = new FormatHolder();
        this.finalStreamEndPositionUs = -9223372036854775807L;
        this.lastRendererPositionUs = -9223372036854775807L;
        this.legacyDecodingEnabled = false;
        return;
    L5:
        r22 = Util.createHandler(r3, this);
        goto L6
    }

    @RequiresNonNull({"streamFormat"})
    private void assertLegacyDecodingEnabledIfRequired() {
        if (this.legacyDecodingEnabled == false) goto L5;
    L12:
        boolean r0 = true;
    L13:
        Assertions.checkState(r0, "Legacy decoding is disabled, can't handle " + this.streamFormat.sampleMimeType + " samples (expected application/x-media3-cues).");
        return;
    L5:
        if (Objects.equals(this.streamFormat.sampleMimeType, "application/cea-608") == true) goto L12;
        if (Objects.equals(this.streamFormat.sampleMimeType, "application/x-mp4-cea-608") == true) goto L12;
        if (Objects.equals(this.streamFormat.sampleMimeType, "application/cea-708") == true) goto L12;
        r0 = false;
        goto L13
    }

    private void clearOutput() {
        updateOutput(new CueGroup(ImmutableList.of(), getPresentationTimeUs(this.lastRendererPositionUs)));
    }

    @RequiresNonNull({MediaTrack.ROLE_SUBTITLE})
    @SideEffectFree
    private long getCurrentEventTimeUs(long r2) {
        int r22 = this.subtitle.getNextEventTimeIndex(r2);
        if (r22 == 0) goto L14;
        if (this.subtitle.getEventTimeCount() == 0) goto L14;
        SubtitleOutputBuffer r1 = this.subtitle;
        if (r22 != (-1)) goto L12;
        return r1.getEventTime(r1.getEventTimeCount() - 1);
    L12:
        return r1.getEventTime(r22 - 1);
    L14:
        return this.subtitle.timeUs;
    }

    private long getNextEventTime() {
        if (this.nextSubtitleEventIndex != (-1)) goto L5;
        return LongCompanionObject.MAX_VALUE;
    L5:
        Assertions.checkNotNull(this.subtitle);
        if (this.nextSubtitleEventIndex < this.subtitle.getEventTimeCount()) goto L9;
        return LongCompanionObject.MAX_VALUE;
    L9:
        return this.subtitle.getEventTime(this.nextSubtitleEventIndex);
    }

    @SideEffectFree
    private long getPresentationTimeUs(long r3) {
        if (r3 == (-9223372036854775807L)) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        return r3 - getStreamOffsetUs();
    L5:
        r0 = false;
        goto L6
    }

    private void handleDecoderError(SubtitleDecoderException r3) {
        Log.e("TextRenderer", "Subtitle decoding failed. streamFormat=" + this.streamFormat, r3);
        clearOutput();
        replaceSubtitleDecoder();
    }

    private static boolean hasEventsAfter(Subtitle r4, long r5) {
        if (r4 != null) goto L4;
        return false;
    L4:
        if (r4.getEventTimeCount() > 0) goto L6;
        return false;
    L6:
        if (r4.getEventTime(r4.getEventTimeCount() - 1) <= r5) goto L11;
        return true;
    L11:
        return false;
    }

    private void initSubtitleDecoder() {
        this.waitingForKeyFrame = true;
        SubtitleDecoder r0 = this.subtitleDecoderFactory.createDecoder((Format) Assertions.checkNotNull(this.streamFormat));
        this.subtitleDecoder = r0;
        r0.setOutputStartTimeUs(getLastResetPositionUs());
    }

    private void invokeUpdateOutputInternal(CueGroup r3) {
        this.output.onCues(r3.cues);
        this.output.onCues(r3);
    }

    @SideEffectFree
    private static boolean isCuesWithTiming(Format r1) {
        return Objects.equals(r1.sampleMimeType, "application/x-media3-cues");
    }

    @RequiresNonNull({"this.cuesResolver"})
    private boolean readAndDecodeCuesWithTiming(long r8) {
        if (this.inputStreamEnded == false) goto L6;
        return false;
    L6:
        if (readSource(this.formatHolder, this.cueDecoderInputBuffer, 0) == (-4)) goto L9;
        return false;
    L9:
        if (this.cueDecoderInputBuffer.isEndOfStream() == false) goto L12;
        this.inputStreamEnded = true;
        return false;
    L12:
        this.cueDecoderInputBuffer.flip();
        ByteBuffer r0 = (ByteBuffer) Assertions.checkNotNull(this.cueDecoderInputBuffer.data);
        CuesWithTiming r02 = this.cueDecoder.decode(this.cueDecoderInputBuffer.timeUs, r0.array(), r0.arrayOffset(), r0.limit());
        this.cueDecoderInputBuffer.clear();
        return this.cuesResolver.addCues(r02, r8);
    }

    private void releaseSubtitleBuffers() {
        this.nextSubtitleInputBuffer = null;
        this.nextSubtitleEventIndex = -1;
        SubtitleOutputBuffer r1 = this.subtitle;
        if (r1 == null) goto L5;
        r1.release();
        this.subtitle = null;
    L5:
        SubtitleOutputBuffer r12 = this.nextSubtitle;
        if (r12 == null) goto L9;
        r12.release();
        this.nextSubtitle = null;
        return;
    }

    private void releaseSubtitleDecoder() {
        releaseSubtitleBuffers();
        ((SubtitleDecoder) Assertions.checkNotNull(this.subtitleDecoder)).release();
        this.subtitleDecoder = null;
        this.decoderReplacementState = 0;
    }

    @RequiresNonNull({"this.cuesResolver"})
    private void renderFromCuesWithTiming(long r7) {
        boolean r0 = readAndDecodeCuesWithTiming(r7);
        long r1 = this.cuesResolver.getNextCueChangeTimeUs(this.lastRendererPositionUs);
        if (r1 == Long.MIN_VALUE) goto L5;
    L8:
        if (r1 != Long.MIN_VALUE) goto L10;
    L12:
        if (r0 == false) goto L14;
        ImmutableList<Cue> r02 = this.cuesResolver.getCuesAtTimeUs(r7);
        long r12 = this.cuesResolver.getPreviousCueChangeTimeUs(r7);
        updateOutput(new CueGroup(r02, getPresentationTimeUs(r12)));
        this.cuesResolver.discardCuesBeforeTimeUs(r12);
    L14:
        this.lastRendererPositionUs = r7;
        return;
    L10:
        if (r1 > r7) goto L12;
        r0 = true;
        goto L12
    L5:
        if (this.inputStreamEnded == false) goto L8;
        if (r0 == true) goto L8;
        this.outputStreamEnded = true;
        goto L8
    }

    private void renderFromSubtitles(long r11) {
        this.lastRendererPositionUs = r11;
        if (this.nextSubtitle != null) goto L11;
        ((SubtitleDecoder) Assertions.checkNotNull(this.subtitleDecoder)).setPositionUs(r11);
        this.nextSubtitle = ((SubtitleDecoder) Assertions.checkNotNull(this.subtitleDecoder)).dequeueOutputBuffer();     // Catch: SubtitleDecoderException -> L7
    L7:
        e = move-exception;
        handleDecoderError(e);
        return;
    L11:
        if (getState() == 2) goto L14;
        return;
    L14:
        if (this.subtitle == null) goto L19;
        long r4 = getNextEventTime();
        boolean r0 = false;
    L17:
        if (r4 > r11) goto L20;
        this.nextSubtitleEventIndex++;
        r4 = getNextEventTime();
        r0 = true;
    L20:
        SubtitleOutputBuffer r42 = this.nextSubtitle;
        if (r42 != null) goto L23;
    L37:
        if (r0 == false) goto L40;
        Assertions.checkNotNull(this.subtitle);
        updateOutput(new CueGroup(this.subtitle.getCues(r11), getPresentationTimeUs(getCurrentEventTimeUs(r11))));
    L40:
        if (this.decoderReplacementState == 2) goto L93;
    L76:
    L50:
        e = move-exception;
        handleDecoderError(e);
        return;
    L43:
        if (this.inputStreamEnded == true) goto L94;
        SubtitleInputBuffer r112 = this.nextSubtitleInputBuffer;     // Catch: SubtitleDecoderException -> L50
        if (r112 != null) goto L53;
        r112 = ((SubtitleDecoder) Assertions.checkNotNull(this.subtitleDecoder)).dequeueInputBuffer();     // Catch: SubtitleDecoderException -> L50
        if (r112 == null) goto L71;
        this.nextSubtitleInputBuffer = r112;     // Catch: SubtitleDecoderException -> L50
        goto L53
    L71:
        return;
    L53:
        if (this.decoderReplacementState == 1) goto L54;
        int r12 = readSource(this.formatHolder, r112, 0);     // Catch: SubtitleDecoderException -> L50
        if (r12 == (-4)) goto L59;
        if (r12 != (-3)) goto L76;
        return;
    L59:
        if (r112.isEndOfStream() == false) goto L61;
        this.inputStreamEnded = true;     // Catch: SubtitleDecoderException -> L50
        this.waitingForKeyFrame = false;     // Catch: SubtitleDecoderException -> L50
    L66:
        if (this.waitingForKeyFrame == true) goto L76;
        ((SubtitleDecoder) Assertions.checkNotNull(this.subtitleDecoder)).queueInputBuffer(r112);     // Catch: SubtitleDecoderException -> L50
        this.nextSubtitleInputBuffer = null;     // Catch: SubtitleDecoderException -> L50
        goto L76
    L61:
        Format r122 = this.formatHolder.format;     // Catch: SubtitleDecoderException -> L50
        if (r122 == null) goto L91;
        r112.subsampleOffsetUs = r122.subsampleOffsetUs;     // Catch: SubtitleDecoderException -> L50
        r112.flip();     // Catch: SubtitleDecoderException -> L50
        this.waitingForKeyFrame &= !r112.isKeyFrame();
        goto L66
    L91:
        return;
    L54:
        r112.setFlags(4);     // Catch: SubtitleDecoderException -> L50
        ((SubtitleDecoder) Assertions.checkNotNull(this.subtitleDecoder)).queueInputBuffer(r112);     // Catch: SubtitleDecoderException -> L50
        this.nextSubtitleInputBuffer = null;     // Catch: SubtitleDecoderException -> L50
        this.decoderReplacementState = 2;     // Catch: SubtitleDecoderException -> L50
        return;
    L94:
        return;
    L93:
        return;
    L23:
        if (r42.isEndOfStream() == false) goto L32;
        if (r0 == true) goto L37;
        if (getNextEventTime() != LongCompanionObject.MAX_VALUE) goto L37;
        if (this.decoderReplacementState != 2) goto L30;
        replaceSubtitleDecoder();
        goto L37
    L30:
        releaseSubtitleBuffers();
        this.outputStreamEnded = true;
        goto L37
    L32:
        if (r42.timeUs > r11) goto L37;
        SubtitleOutputBuffer r02 = this.subtitle;
        if (r02 == null) goto L36;
        r02.release();
    L36:
        this.nextSubtitleEventIndex = r42.getNextEventTimeIndex(r11);
        this.subtitle = r42;
        this.nextSubtitle = null;
        r0 = true;
        goto L37
    L19:
        r0 = false;
        goto L20
    }

    private void replaceSubtitleDecoder() {
        releaseSubtitleDecoder();
        initSubtitleDecoder();
    }

    private void updateOutput(CueGroup r2) {
        Handler r0 = this.outputHandler;
        if (r0 == null) goto L6;
        r0.obtainMessage(1, r2).sendToTarget();
        return;
    L6:
        invokeUpdateOutputInternal(r2);
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return "TextRenderer";
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message r3) {
        if (r3.what != 1) goto L6;
        invokeUpdateOutputInternal((CueGroup) r3.obj);
        return true;
    L6:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return false;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        Format r0 = this.streamFormat;
        if (r0 != null) goto L6;
        return true;
    L6:
        if (isCuesWithTiming((Format) Assertions.checkNotNull(r0)) == false) goto L14;
        if (((CuesResolver) Assertions.checkNotNull(this.cuesResolver)).getNextCueChangeTimeUs(this.lastRendererPositionUs) == Long.MIN_VALUE) goto L25;
        return true;
    L25:
        maybeThrowStreamError();     // Catch: IOException -> L12
        return true;
    L12:
        return false;
    L14:
        if (this.outputStreamEnded == false) goto L16;
    L24:
        return false;
    L16:
        if (this.inputStreamEnded == true) goto L18;
    L23:
        return true;
    L18:
        if (hasEventsAfter(this.subtitle, this.lastRendererPositionUs) == true) goto L23;
        if (hasEventsAfter(this.nextSubtitle, this.lastRendererPositionUs) == true) goto L23;
        if (this.nextSubtitleInputBuffer != null) goto L24;
        goto L23
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.streamFormat = null;
        this.finalStreamEndPositionUs = -9223372036854775807L;
        clearOutput();
        this.lastRendererPositionUs = -9223372036854775807L;
        if (this.subtitleDecoder == null) goto L6;
        releaseSubtitleDecoder();
        return;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long r1, boolean r3) {
        this.lastRendererPositionUs = r1;
        CuesResolver r12 = this.cuesResolver;
        if (r12 == null) goto L5;
        r12.clear();
    L5:
        clearOutput();
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        this.finalStreamEndPositionUs = -9223372036854775807L;
        Format r13 = this.streamFormat;
        if (r13 != null) goto L8;
        return;
    L8:
        if (isCuesWithTiming(r13) == false) goto L10;
        return;
    L10:
        if (this.decoderReplacementState == 0) goto L13;
        replaceSubtitleDecoder();
        return;
    L13:
        releaseSubtitleBuffers();
        SubtitleDecoder r14 = (SubtitleDecoder) Assertions.checkNotNull(this.subtitleDecoder);
        r14.flush();
        r14.setOutputStartTimeUs(getLastResetPositionUs());
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStreamChanged(Format[] r1, long r2, long r4, MediaSource.MediaPeriodId r6) {
        Format r12 = r1[0];
        this.streamFormat = r12;
        if (isCuesWithTiming(r12) == true) goto L11;
        assertLegacyDecodingEnabledIfRequired();
        if (this.subtitleDecoder == null) goto L8;
        this.decoderReplacementState = 1;
        return;
    L8:
        initSubtitleDecoder();
        return;
    L11:
        if (this.streamFormat.cueReplacementBehavior != 1) goto L13;
        CuesResolver r13 = new MergingCuesResolver();
    L14:
        this.cuesResolver = r13;
        return;
    L13:
        r13 = new ReplacingCuesResolver();
        goto L14
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long r3, long r5) {
        if (isCurrentStreamFinal() == false) goto L10;
        long r52 = this.finalStreamEndPositionUs;
        if (r52 == (-9223372036854775807L)) goto L10;
        if (r3 < r52) goto L10;
        releaseSubtitleBuffers();
        this.outputStreamEnded = true;
    L10:
        if (this.outputStreamEnded == false) goto L13;
        return;
    L13:
        if (isCuesWithTiming((Format) Assertions.checkNotNull(this.streamFormat)) == false) goto L16;
        Assertions.checkNotNull(this.cuesResolver);
        renderFromCuesWithTiming(r3);
        return;
    L16:
        assertLegacyDecodingEnabledIfRequired();
        renderFromSubtitles(r3);
    }

    public void setFinalStreamEndPositionUs(long r2) {
        Assertions.checkState(isCurrentStreamFinal());
        this.finalStreamEndPositionUs = r2;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public int supportsFormat(Format r2) {
        if (isCuesWithTiming(r2) == true) goto L14;
        if (this.subtitleDecoderFactory.supportsFormat(r2) == true) goto L14;
        if (MimeTypes.isText(r2.sampleMimeType) == false) goto L12;
        return RendererCapabilities.create(1);
    L12:
        return RendererCapabilities.create(0);
    L14:
        if (r2.cryptoType != 0) goto L16;
        int r1 = 4;
    L18:
        return RendererCapabilities.create(r1);
    L16:
        r1 = 2;
        goto L18
    }
}
