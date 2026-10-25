package org.telegram.messenger.video;

import android.media.MediaCodec;
import android.media.MediaFormat;
import androidx.car.app.media.CarAudioRecord;
import com.coremedia.iso.boxes.AbstractMediaHeaderBox;
import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.SoundMediaHeaderBox;
import com.coremedia.iso.boxes.VideoMediaHeaderBox;
import com.coremedia.iso.boxes.sampleentry.AudioSampleEntry;
import com.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import com.googlecode.mp4parser.boxes.mp4.ESDescriptorBox;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.AudioSpecificConfig;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.DecoderConfigDescriptor;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.SLConfigDescriptor;
import com.mp4parser.iso14496.part15.AvcConfigurationBox;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import kotlin.jvm.internal.LongCompanionObject;
import org.telegram.messenger.MediaController;

/* loaded from: classes6.dex */
public class Track {
    private static Map<Integer, Integer> samplingFrequencyIndexMap;
    private Date creationTime;
    private long duration;
    private boolean first;
    private String handler;
    private AbstractMediaHeaderBox headerBox;
    private int height;
    private boolean isAudio;
    private int[] sampleCompositions;
    private SampleDescriptionBox sampleDescriptionBox;
    private long[] sampleDurations;
    private ArrayList<SamplePresentationTime> samplePresentationTimes;
    private ArrayList<Sample> samples;
    private LinkedList<Integer> syncSamples;
    private int timeScale;
    private long trackId;
    private float volume;
    private int width;

    public static class SamplePresentationTime {
        private long dt;
        private int index;
        private long presentationTime;

        /* renamed from: -$$Nest$fgetdt, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m5225$$Nest$fgetdt(SamplePresentationTime r2) {
            return r2.dt;
        }

        /* renamed from: -$$Nest$fgetindex, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5226$$Nest$fgetindex(SamplePresentationTime r0) {
            return r0.index;
        }

        /* renamed from: -$$Nest$fgetpresentationTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m5227$$Nest$fgetpresentationTime(SamplePresentationTime r2) {
            return r2.presentationTime;
        }

        /* renamed from: -$$Nest$fputdt, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5228$$Nest$fputdt(SamplePresentationTime r0, long r1) {
            r0.dt = r1;
        }

        public SamplePresentationTime(int r1, long r2) {
            this.index = r1;
            this.presentationTime = r2;
        }
    }

    public static /* synthetic */ int $r8$lambda$PAG47EftV8dtSArRwd94kkDFa4U(SamplePresentationTime r4, SamplePresentationTime r5) {
        if (SamplePresentationTime.m5227$$Nest$fgetpresentationTime(r4) <= SamplePresentationTime.m5227$$Nest$fgetpresentationTime(r5)) goto L7;
        return 1;
    L7:
        if (SamplePresentationTime.m5227$$Nest$fgetpresentationTime(r4) >= SamplePresentationTime.m5227$$Nest$fgetpresentationTime(r5)) goto L10;
        return -1;
    L10:
        return 0;
    }

    static {
        HashMap r0 = new HashMap();
        samplingFrequencyIndexMap = r0;
        r0.put(96000, 0);
        samplingFrequencyIndexMap.put(88200, 1);
        samplingFrequencyIndexMap.put(64000, 2);
        samplingFrequencyIndexMap.put(48000, 3);
        samplingFrequencyIndexMap.put(44100, 4);
        samplingFrequencyIndexMap.put(32000, 5);
        samplingFrequencyIndexMap.put(24000, 6);
        samplingFrequencyIndexMap.put(22050, 7);
        samplingFrequencyIndexMap.put(Integer.valueOf(CarAudioRecord.AUDIO_CONTENT_SAMPLING_RATE), 8);
        samplingFrequencyIndexMap.put(12000, 9);
        samplingFrequencyIndexMap.put(11025, 10);
        samplingFrequencyIndexMap.put(8000, 11);
    }

    public Track(int r17, MediaFormat r18, boolean r19) {
        this.samples = new ArrayList();
        this.duration = 0;
        this.syncSamples = null;
        this.creationTime = new Date();
        this.volume = 0.0f;
        this.samplePresentationTimes = new ArrayList();
        this.first = true;
        this.trackId = r17;
        this.isAudio = r19;
        if (r19 == true) goto L123;
        this.width = r18.getInteger("width");
        this.height = r18.getInteger("height");
        this.timeScale = 90000;
        this.syncSamples = new LinkedList();
        this.handler = "vide";
        this.headerBox = new VideoMediaHeaderBox();
        this.sampleDescriptionBox = new SampleDescriptionBox();
        String r2 = r18.getString("mime");
        if (r2.equals(MediaController.VIDEO_MIME_TYPE) == false) goto L78;
        VisualSampleEntry r22 = new VisualSampleEntry("avc1");
        r22.setDataReferenceIndex(1);
        r22.setDepth(24);
        r22.setFrameCount(1);
        r22.setHorizresolution(72.0d);
        r22.setVertresolution(72.0d);
        r22.setWidth(this.width);
        r22.setHeight(this.height);
        AvcConfigurationBox r7 = new AvcConfigurationBox();
        if (r18.getByteBuffer("csd-0") == null) goto L10;
        ArrayList r10 = new ArrayList();
        ByteBuffer r11 = r18.getByteBuffer("csd-0");
        r11.position(4);
        byte[] r12 = new byte[r11.remaining()];
        r11.get(r12);
        r10.add(r12);
        ArrayList r112 = new ArrayList();
        ByteBuffer r122 = r18.getByteBuffer("csd-1");
        r122.position(4);
        byte[] r13 = new byte[r122.remaining()];
        r122.get(r13);
        r112.add(r13);
        r7.setSequenceParameterSets(r10);
        r7.setPictureParameterSets(r112);
    L10:
        if (r18.containsKey("level") == false) goto L56;
        int r102 = r18.getInteger("level");
        if (r102 != 1) goto L14;
        r7.setAvcLevelIndication(1);
    L58:
        if (r18.containsKey("profile") == false) goto L75;
        int r1 = r18.getInteger("profile");
        if (r1 != 1) goto L62;
        r7.setAvcProfileIndication(66);
    L76:
        r7.setBitDepthLumaMinus8(-1);
        r7.setBitDepthChromaMinus8(-1);
        r7.setChromaFormat(-1);
        r7.setConfigurationVersion(1);
        r7.setLengthSizeMinusOne(3);
        r7.setProfileCompatibility(0);
        r22.addBox(r7);
        this.sampleDescriptionBox.addBox(r22);
        return;
    L62:
        if (r1 != 2) goto L64;
        r7.setAvcProfileIndication(77);
        goto L76
    L64:
        if (r1 != 4) goto L66;
        r7.setAvcProfileIndication(88);
        goto L76
    L66:
        if (r1 != 8) goto L68;
        r7.setAvcProfileIndication(100);
        goto L76
    L68:
        if (r1 != 16) goto L70;
        r7.setAvcProfileIndication(110);
        goto L76
    L70:
        if (r1 != 32) goto L72;
        r7.setAvcProfileIndication(122);
        goto L76
    L72:
        if (r1 != 64) goto L76;
        r7.setAvcProfileIndication(244);
        goto L76
    L75:
        r7.setAvcProfileIndication(100);
        goto L76
    L14:
        if (r102 != 32) goto L16;
        r7.setAvcLevelIndication(2);
        goto L58
    L16:
        if (r102 != 4) goto L18;
        r7.setAvcLevelIndication(11);
        goto L58
    L18:
        if (r102 != 8) goto L20;
        r7.setAvcLevelIndication(12);
        goto L58
    L20:
        if (r102 != 16) goto L22;
        r7.setAvcLevelIndication(13);
        goto L58
    L22:
        if (r102 != 64) goto L25;
        r7.setAvcLevelIndication(21);
        goto L58
    L25:
        if (r102 != 128) goto L28;
        r7.setAvcLevelIndication(22);
        goto L58
    L28:
        if (r102 != 256) goto L31;
        r7.setAvcLevelIndication(3);
        goto L58
    L31:
        if (r102 != 512) goto L34;
        r7.setAvcLevelIndication(31);
        goto L58
    L34:
        if (r102 != 1024) goto L37;
        r7.setAvcLevelIndication(32);
        goto L58
    L37:
        if (r102 != 2048) goto L40;
        r7.setAvcLevelIndication(4);
        goto L58
    L40:
        if (r102 != 4096) goto L43;
        r7.setAvcLevelIndication(41);
        goto L58
    L43:
        if (r102 != 8192) goto L46;
        r7.setAvcLevelIndication(42);
        goto L58
    L46:
        if (r102 != 16384) goto L49;
        r7.setAvcLevelIndication(5);
        goto L58
    L49:
        if (r102 != 32768) goto L52;
        r7.setAvcLevelIndication(51);
        goto L58
    L52:
        if (r102 != 65536) goto L54;
        r7.setAvcLevelIndication(52);
        goto L58
    L54:
        if (r102 != 2) goto L58;
        r7.setAvcLevelIndication(27);
        goto L58
    L56:
        r7.setAvcLevelIndication(13);
        goto L58
    L78:
        if (r2.equals("video/mp4v") == false) goto L81;
        VisualSampleEntry r14 = new VisualSampleEntry("mp4v");
        r14.setDataReferenceIndex(1);
        r14.setDepth(24);
        r14.setFrameCount(1);
        r14.setHorizresolution(72.0d);
        r14.setVertresolution(72.0d);
        r14.setWidth(this.width);
        r14.setHeight(this.height);
        this.sampleDescriptionBox.addBox(r14);
        return;
    L81:
        if (r2.equals("video/hevc") == true) goto L83;
        return;
    L83:
        if (r18.getByteBuffer("csd-0") == null) goto L151;
        byte[] r15 = r18.getByteBuffer("csd-0").array();
        int r23 = 0;
        int r4 = -1;
        int r5 = -1;
        int r6 = 0;
        int r72 = -1;
    L86:
        if (r23 >= r15.length) goto L103;
        if (r6 != 3) goto L99;
        if (r15[r23] != 1) goto L99;
        if (r72 != (-1)) goto L93;
        r72 = r23 - 3;
        goto L99
    L93:
        if (r4 != (-1)) goto L95;
        r4 = r23 - 3;
        goto L99
    L95:
        if (r5 != (-1)) goto L99;
        r5 = r23 - 3;
    L99:
        if (r15[r23] != 0) goto L101;
        r6 = r6 + 1;
    L102:
        r23 = r23 + 1;
        goto L86
    L101:
        r6 = 0;
        goto L102
    L103:
        byte[] r24 = new byte[r4 - 4];
        byte[] r3 = new byte[(r5 - r4) - 4];
        byte[] r62 = new byte[(r15.length - r5) - 4];
        int r73 = 0;
    L105:
        if (r73 >= r15.length) goto L137;
        if (r73 >= r4) goto L110;
        int r8 = r73 - 4;
        if (r8 < 0) goto L117;
        r24[r8] = r15[r73];
    L117:
        r73 = r73 + 1;
        goto L105
    L110:
        if (r73 >= r5) goto L114;
        int r82 = (r73 - r4) - 4;
        if (r82 < 0) goto L117;
        r3[r82] = r15[r73];
        goto L117
    L114:
        int r83 = (r73 - r5) - 4;
        if (r83 < 0) goto L117;
        r62[r83] = r15[r73];
        goto L117
    L137:
        VisualSampleEntry r16 = HevcDecoderConfigurationRecord.parseFromCsd(Arrays.asList(new ByteBuffer[]{ByteBuffer.wrap(r24), ByteBuffer.wrap(r62), ByteBuffer.wrap(r3)}));     // Catch: IOException -> L120
        r16.setWidth(this.width);     // Catch: IOException -> L120
        r16.setHeight(this.height);     // Catch: IOException -> L120
        this.sampleDescriptionBox.addBox(r16);     // Catch: IOException -> L120
        return;
    L120:
        e = move-exception;
        e.printStackTrace();
        return;
    L151:
        return;
    L123:
        this.volume = 1.0f;
        this.timeScale = r18.getInteger("sample-rate");
        this.handler = "soun";
        this.headerBox = new SoundMediaHeaderBox();
        this.sampleDescriptionBox = new SampleDescriptionBox();
        AudioSampleEntry r9 = new AudioSampleEntry("mp4a");
        r9.setChannelCount(r18.getInteger("channel-count"));
        r9.setSampleRate(r18.getInteger("sample-rate"));
        r9.setDataReferenceIndex(1);
        r9.setSampleSize(16);
        ESDescriptorBox r25 = new ESDescriptorBox();
        ESDescriptor r32 = new ESDescriptor();
        r32.setEsId(0);
        SLConfigDescriptor r63 = new SLConfigDescriptor();
        r63.setPredefined(2);
        r32.setSlConfigDescriptor(r63);
        if (r18.containsKey("mime") == false) goto L126;
        String r64 = r18.getString("mime");
    L127:
        DecoderConfigDescriptor r74 = new DecoderConfigDescriptor();
        if ("audio/mpeg".equals(r64) == false) goto L130;
        r74.setObjectTypeIndication(105);
    L131:
        r74.setStreamType(5);
        r74.setBufferSizeDB(1536);
        if (r18.containsKey("max-bitrate") == false) goto L134;
        r74.setMaxBitRate(r18.getInteger("max-bitrate"));
    L135:
        r74.setAvgBitRate(this.timeScale);
        AudioSpecificConfig r110 = new AudioSpecificConfig();
        r110.setAudioObjectType(2);
        r110.setSamplingFrequencyIndex(samplingFrequencyIndexMap.get(Integer.valueOf((int) r9.getSampleRate())).intValue());
        r110.setChannelConfiguration(r9.getChannelCount());
        r74.setAudioSpecificInfo(r110);
        r32.setDecoderConfigDescriptor(r74);
        r25.setData(r32.serialize());
        r9.addBox(r25);
        this.sampleDescriptionBox.addBox(r9);
        return;
    L134:
        r74.setMaxBitRate(96000);
        goto L135
    L130:
        r74.setObjectTypeIndication(64);
        goto L131
    L126:
        r64 = "audio/mp4-latm";
        goto L127
    }

    public void addSample(long r6, MediaCodec.BufferInfo r8) {
        if (this.isAudio == true) goto L7;
        boolean r1 = true;
        if ((r8.flags & 1) == 0) goto L7;
    L8:
        this.samples.add(new Sample(r6, r8.size));
        LinkedList<Integer> r62 = this.syncSamples;
        if (r62 == null) goto L12;
        if (r1 == false) goto L12;
        r62.add(Integer.valueOf(this.samples.size()));
    L12:
        ArrayList<SamplePresentationTime> r63 = this.samplePresentationTimes;
        r63.add(new SamplePresentationTime(r63.size(), ((r8.presentationTimeUs * this.timeScale) + 500000) / 1000000));
        return;
    L7:
        r1 = false;
        goto L8
    }

    public Date getCreationTime() {
        return this.creationTime;
    }

    public long getDuration() {
        return this.duration;
    }

    public String getHandler() {
        return this.handler;
    }

    public int getHeight() {
        return this.height;
    }

    public long getLastFrameTimestamp() {
        return (((this.duration - this.sampleDurations[r2.length - 1]) * 1000000) - 500000) / this.timeScale;
    }

    public AbstractMediaHeaderBox getMediaHeaderBox() {
        return this.headerBox;
    }

    public int[] getSampleCompositions() {
        return this.sampleCompositions;
    }

    public SampleDescriptionBox getSampleDescriptionBox() {
        return this.sampleDescriptionBox;
    }

    public long[] getSampleDurations() {
        return this.sampleDurations;
    }

    public ArrayList<Sample> getSamples() {
        return this.samples;
    }

    public long[] getSyncSamples() {
        LinkedList<Integer> r0 = this.syncSamples;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.isEmpty() == true) goto L15;
        long[] r02 = new long[this.syncSamples.size()];
        int r1 = 0;
    L9:
        if (r1 >= this.syncSamples.size()) goto L11;
        r02[r1] = this.syncSamples.get(r1).intValue();
        r1 = r1 + 1;
        goto L9
    L11:
        return r02;
    L15:
        return null;
    }

    public int getTimeScale() {
        return this.timeScale;
    }

    public long getTrackId() {
        return this.trackId;
    }

    public float getVolume() {
        return this.volume;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isAudio() {
        return this.isAudio;
    }

    public void prepare() {
        long r1 = 0;
        this.duration = 0;
        ArrayList r3 = new ArrayList(this.samplePresentationTimes);
        Collections.sort(r3, new Track$$ExternalSyntheticLambda0());
        this.sampleDurations = new long[this.samplePresentationTimes.size()];
        int r4 = 0;
        int r5 = 0;
    L3:
        int r6 = r3.size();
        int r9 = 1;
        long r10 = LongCompanionObject.MAX_VALUE;
        if (r5 >= r6) goto L30;
        if (SamplePresentationTime.m5226$$Nest$fgetindex((SamplePresentationTime) r3.get(r5)) != r5) goto L7;
        r5 = r5 + 1;
        goto L3
    L7:
        long r12 = 0;
        int r52 = 0;
    L9:
        if (r52 >= r3.size()) goto L19;
        SamplePresentationTime r62 = (SamplePresentationTime) r3.get(r52);
        long r14 = SamplePresentationTime.m5227$$Nest$fgetpresentationTime(r62) - r12;
        r12 = SamplePresentationTime.m5227$$Nest$fgetpresentationTime(r62);
        long r16 = r1;
        this.sampleDurations[SamplePresentationTime.m5226$$Nest$fgetindex(r62)] = r14;
        if (SamplePresentationTime.m5226$$Nest$fgetindex(r62) == 0) goto L14;
        this.duration += r14;
    L14:
        if (r14 <= r16) goto L18;
        if (r14 >= 2147483647L) goto L18;
        r10 = Math.min(r10, r14);
    L18:
        r52 = r52 + 1;
        r1 = r16;
        goto L9
    L19:
        long[] r13 = this.sampleDurations;
        if (r13.length <= 0) goto L23;
        r13[0] = r10;
        this.duration += r10;
    L23:
        if (r9 >= this.samplePresentationTimes.size()) goto L25;
        SamplePresentationTime.m5228$$Nest$fputdt(this.samplePresentationTimes.get(r9), this.sampleDurations[r9] + SamplePresentationTime.m5225$$Nest$fgetdt(this.samplePresentationTimes.get(r9 - 1)));
        r9 = r9 + 1;
        goto L23
    L25:
        this.sampleCompositions = new int[r3.size()];
    L27:
        if (r4 >= r3.size()) goto L49;
        SamplePresentationTime r15 = (SamplePresentationTime) r3.get(r4);
        this.sampleCompositions[SamplePresentationTime.m5226$$Nest$fgetindex(r15)] = (int) (SamplePresentationTime.m5227$$Nest$fgetpresentationTime(r15) - SamplePresentationTime.m5225$$Nest$fgetdt(r15));
        r4 = r4 + 1;
        goto L27
    L49:
        return;
    L30:
        int r17 = 0;
        long r53 = Long.MAX_VALUE;
    L31:
        int r2 = r17 + 1;
        if (r2 >= r3.size()) goto L39;
        long r122 = SamplePresentationTime.m5227$$Nest$fgetpresentationTime((SamplePresentationTime) r3.get(r2)) - SamplePresentationTime.m5227$$Nest$fgetpresentationTime((SamplePresentationTime) r3.get(r17));
        this.sampleDurations[r17] = r122;
        if (r122 <= 0) goto L38;
        if (r122 >= 2147483647L) goto L38;
        r53 = Math.min(r53, r122);
    L38:
        r17 = r2;
        goto L31
    L39:
        long[] r18 = this.sampleDurations;
        if (r18.length <= 0) goto L46;
        int r22 = r18.length - 1;
        if (r53 != LongCompanionObject.MAX_VALUE) goto L45;
        r53 = Math.max(1, this.timeScale / 30);
    L45:
        r18[r22] = r53;
    L46:
        long[] r19 = this.sampleDurations;
        if (r4 >= r19.length) goto L65;
        this.duration += r19[r4];
        r4 = r4 + 1;
        goto L46
    }
}
