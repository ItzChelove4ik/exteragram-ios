package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import androidx.car.app.media.CarAudioRecord;
import com.coremedia.iso.Hex;
import com.coremedia.iso.IsoTypeWriter;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

@Descriptor(objectTypeIndication = 64, tags = {5})
/* loaded from: classes5.dex */
public class AudioSpecificConfig extends BaseDescriptor {
    public static Map<Integer, String> audioObjectTypeMap;
    public static Map<Integer, Integer> samplingFrequencyIndexMap;
    public boolean aacScalefactorDataResilienceFlag;
    public boolean aacSectionDataResilienceFlag;
    public boolean aacSpectralDataResilienceFlag;
    public int audioObjectType;
    public int channelConfiguration;
    byte[] configBytes;
    public int coreCoderDelay;
    public int dependsOnCoreCoder;
    public int directMapping;
    public ELDSpecificConfig eldSpecificConfig;
    public int epConfig;
    public int erHvxcExtensionFlag;
    public int extensionAudioObjectType;
    public int extensionChannelConfiguration;
    public int extensionFlag;
    public int extensionFlag3;
    public int extensionSamplingFrequency;
    public int extensionSamplingFrequencyIndex;
    public int fillBits;
    public int frameLengthFlag;
    public boolean gaSpecificConfig;
    public int hilnContMode;
    public int hilnEnhaLayer;
    public int hilnEnhaQuantMode;
    public int hilnFrameLength;
    public int hilnMaxNumLine;
    public int hilnQuantMode;
    public int hilnSampleRateCode;
    public int hvxcRateMode;
    public int hvxcVarMode;
    public int isBaseLayer;
    public int layerNr;
    public int layer_length;
    public int numOfSubFrame;
    public int paraExtensionFlag;
    public int paraMode;
    public boolean parametricSpecificConfig;
    public boolean psPresentFlag;
    public int sacPayloadEmbedding;
    public int samplingFrequency;
    public int samplingFrequencyIndex;
    public boolean sbrPresentFlag;
    public int syncExtensionType;
    public int var_ScalableFlag;

    public class ELDSpecificConfig {
        public boolean aacScalefactorDataResilienceFlag;
        public boolean aacSectionDataResilienceFlag;
        public boolean aacSpectralDataResilienceFlag;
        public boolean frameLengthFlag;
        public boolean ldSbrCrcFlag;
        public boolean ldSbrPresentFlag;
        public boolean ldSbrSamplingRate;
        final /* synthetic */ AudioSpecificConfig this$0;

        public ELDSpecificConfig(AudioSpecificConfig r3, int r4, BitReaderBuffer r5) {
            this.this$0 = r3;
            this.frameLengthFlag = r5.readBool();
            this.aacSectionDataResilienceFlag = r5.readBool();
            this.aacScalefactorDataResilienceFlag = r5.readBool();
            this.aacSpectralDataResilienceFlag = r5.readBool();
            boolean r32 = r5.readBool();
            this.ldSbrPresentFlag = r32;
            if (r32 == false) goto L6;
            this.ldSbrSamplingRate = r5.readBool();
            this.ldSbrCrcFlag = r5.readBool();
            ld_sbr_header(r4, r5);
        L6:
            if (r5.readBits(4) == 0) goto L7;
            int r2 = r5.readBits(4);
            int r0 = 0;
            if (r2 != 15) goto L11;
            int r33 = r5.readBits(8);
            r2 = r2 + r33;
        L13:
            if (r33 != 255) goto L15;
            r2 = r2 + r5.readBits(16);
        L15:
            if (r0 >= r2) goto L6;
            r5.readBits(8);
            r0 = r0 + 1;
            goto L15
        L11:
            r33 = 0;
            goto L13
        }

        public void ld_sbr_header(int r4, BitReaderBuffer r5) {
            int r0 = 0;
            switch(r4) {
                case 1: goto L8;
                case 2: goto L8;
                case 3: goto L7;
                case 4: goto L6;
                case 5: goto L6;
                case 6: goto L6;
                case 7: goto L5;
                default: goto L4;
            };
        L4:
            int r42 = 0;
        L9:
            if (r0 >= r42) goto L10;
            new sbr_header(this.this$0, r5);
            r0 = r0 + 1;
            goto L9
        L10:
            return;
        L5:
            r42 = 4;
            goto L9
        L6:
            r42 = 3;
            goto L9
        L7:
            r42 = 2;
            goto L9
        L8:
            r42 = 1;
            goto L9
        }
    }

    public class sbr_header {
        public boolean bs_alter_scale;
        public boolean bs_amp_res;
        public int bs_freq_scale;
        public boolean bs_header_extra_1;
        public boolean bs_header_extra_2;
        public boolean bs_interpol_freq;
        public int bs_limiter_bands;
        public int bs_limiter_gains;
        public int bs_noise_bands;
        public int bs_reserved;
        public boolean bs_smoothing_mode;
        public int bs_start_freq;
        public int bs_stop_freq;
        public int bs_xover_band;
        final /* synthetic */ AudioSpecificConfig this$0;

        public sbr_header(AudioSpecificConfig r2, BitReaderBuffer r3) {
            this.this$0 = r2;
            this.bs_amp_res = r3.readBool();
            this.bs_start_freq = r3.readBits(4);
            this.bs_stop_freq = r3.readBits(4);
            this.bs_xover_band = r3.readBits(3);
            this.bs_reserved = r3.readBits(2);
            this.bs_header_extra_1 = r3.readBool();
            this.bs_header_extra_2 = r3.readBool();
            if (this.bs_header_extra_1 == false) goto L6;
            this.bs_freq_scale = r3.readBits(2);
            this.bs_alter_scale = r3.readBool();
            this.bs_noise_bands = r3.readBits(2);
        L6:
            if (this.bs_header_extra_2 == false) goto L8;
            this.bs_limiter_bands = r3.readBits(2);
            this.bs_limiter_gains = r3.readBits(2);
            this.bs_interpol_freq = r3.readBool();
        L8:
            this.bs_smoothing_mode = r3.readBool();
        }
    }

    static {
        samplingFrequencyIndexMap = new HashMap();
        audioObjectTypeMap = new HashMap();
        samplingFrequencyIndexMap.put(0, 96000);
        samplingFrequencyIndexMap.put(1, 88200);
        samplingFrequencyIndexMap.put(2, 64000);
        samplingFrequencyIndexMap.put(3, 48000);
        samplingFrequencyIndexMap.put(4, 44100);
        samplingFrequencyIndexMap.put(5, 32000);
        samplingFrequencyIndexMap.put(6, 24000);
        samplingFrequencyIndexMap.put(7, 22050);
        samplingFrequencyIndexMap.put(8, Integer.valueOf(CarAudioRecord.AUDIO_CONTENT_SAMPLING_RATE));
        samplingFrequencyIndexMap.put(9, 12000);
        samplingFrequencyIndexMap.put(10, 11025);
        samplingFrequencyIndexMap.put(11, 8000);
        audioObjectTypeMap.put(1, "AAC main");
        audioObjectTypeMap.put(2, "AAC LC");
        audioObjectTypeMap.put(3, "AAC SSR");
        audioObjectTypeMap.put(4, "AAC LTP");
        audioObjectTypeMap.put(5, "SBR");
        audioObjectTypeMap.put(6, "AAC Scalable");
        audioObjectTypeMap.put(7, "TwinVQ");
        audioObjectTypeMap.put(8, "CELP");
        audioObjectTypeMap.put(9, "HVXC");
        audioObjectTypeMap.put(10, "(reserved)");
        audioObjectTypeMap.put(11, "(reserved)");
        audioObjectTypeMap.put(12, "TTSI");
        audioObjectTypeMap.put(13, "Main synthetic");
        audioObjectTypeMap.put(14, "Wavetable synthesis");
        audioObjectTypeMap.put(15, "General MIDI");
        audioObjectTypeMap.put(16, "Algorithmic Synthesis and Audio FX");
        audioObjectTypeMap.put(17, "ER AAC LC");
        audioObjectTypeMap.put(18, "(reserved)");
        audioObjectTypeMap.put(19, "ER AAC LTP");
        audioObjectTypeMap.put(20, "ER AAC Scalable");
        audioObjectTypeMap.put(21, "ER TwinVQ");
        audioObjectTypeMap.put(22, "ER BSAC");
        audioObjectTypeMap.put(23, "ER AAC LD");
        audioObjectTypeMap.put(24, "ER CELP");
        audioObjectTypeMap.put(25, "ER HVXC");
        audioObjectTypeMap.put(26, "ER HILN");
        audioObjectTypeMap.put(27, "ER Parametric");
        audioObjectTypeMap.put(28, "SSC");
        audioObjectTypeMap.put(29, "PS");
        audioObjectTypeMap.put(30, "MPEG Surround");
        audioObjectTypeMap.put(31, "(escape)");
        audioObjectTypeMap.put(32, "Layer-1");
        audioObjectTypeMap.put(33, "Layer-2");
        audioObjectTypeMap.put(34, "Layer-3");
        audioObjectTypeMap.put(35, "DST");
        audioObjectTypeMap.put(36, "ALS");
        audioObjectTypeMap.put(37, "SLS");
        audioObjectTypeMap.put(38, "SLS non-core");
        audioObjectTypeMap.put(39, "ER AAC ELD");
        audioObjectTypeMap.put(40, "SMR Simple");
        audioObjectTypeMap.put(41, "SMR Main");
    }

    public AudioSpecificConfig() {
    }

    private int gaSpecificConfigSize() {
        return 0;
    }

    private int getAudioObjectType(BitReaderBuffer r2) {
        int r1 = r2.readBits(5);
        if (r1 == 31) goto L5;
        return r1;
    L5:
        return r2.readBits(6) + 32;
    }

    private void parseErHvxcConfig(int r1, int r2, int r3, BitReaderBuffer r4) {
        this.hvxcVarMode = r4.readBits(1);
        this.hvxcRateMode = r4.readBits(2);
        int r22 = r4.readBits(1);
        this.erHvxcExtensionFlag = r22;
        if (r22 != 1) goto L6;
        this.var_ScalableFlag = r4.readBits(1);
        return;
    }

    private void parseGaSpecificConfig(int r2, int r3, int r4, BitReaderBuffer r5) {
        this.frameLengthFlag = r5.readBits(1);
        int r0 = r5.readBits(1);
        this.dependsOnCoreCoder = r0;
        if (r0 != 1) goto L5;
        this.coreCoderDelay = r5.readBits(14);
    L5:
        this.extensionFlag = r5.readBits(1);
        if (r3 != 0) goto L8;
        ByteString$$ExternalSyntheticBUOutline0.m("can't parse program_config_element yet");
        return;
    L8:
        if (r4 == 6) goto L10;
        if (r4 == 20) goto L10;
    L12:
        if (this.extensionFlag == 1) goto L14;
    L25:
        this.gaSpecificConfig = true;
        return;
    L14:
        if (r4 != 22) goto L17;
        this.numOfSubFrame = r5.readBits(5);
        this.layer_length = r5.readBits(11);
    L17:
        if (r4 != 17) goto L19;
    L23:
        this.aacSectionDataResilienceFlag = r5.readBool();
        this.aacScalefactorDataResilienceFlag = r5.readBool();
        this.aacSpectralDataResilienceFlag = r5.readBool();
    L24:
        this.extensionFlag3 = r5.readBits(1);
        goto L25
    L19:
        if (r4 == 19) goto L23;
        if (r4 == 20) goto L23;
        if (r4 != 23) goto L24;
    L10:
        this.layerNr = r5.readBits(3);
        goto L12
    }

    private void parseHilnConfig(int r1, int r2, int r3, BitReaderBuffer r4) {
        this.hilnQuantMode = r4.readBits(1);
        this.hilnMaxNumLine = r4.readBits(8);
        this.hilnSampleRateCode = r4.readBits(4);
        this.hilnFrameLength = r4.readBits(12);
        this.hilnContMode = r4.readBits(2);
    }

    private void parseHilnEnexConfig(int r1, int r2, int r3, BitReaderBuffer r4) {
        int r22 = r4.readBits(1);
        this.hilnEnhaLayer = r22;
        if (r22 != 1) goto L6;
        this.hilnEnhaQuantMode = r4.readBits(2);
        return;
    }

    private void parseParaConfig(int r3, int r4, int r5, BitReaderBuffer r6) {
        int r0 = r6.readBits(2);
        this.paraMode = r0;
        if (r0 == 1) goto L6;
        parseErHvxcConfig(r3, r4, r5, r6);
    L6:
        if (this.paraMode == 0) goto L8;
        parseHilnConfig(r3, r4, r5, r6);
    L8:
        this.paraExtensionFlag = r6.readBits(1);
        this.parametricSpecificConfig = true;
    }

    private void parseParametricSpecificConfig(int r3, int r4, int r5, BitReaderBuffer r6) {
        int r1 = r6.readBits(1);
        this.isBaseLayer = r1;
        if (r1 != 1) goto L6;
        parseParaConfig(r3, r4, r5, r6);
        return;
    L6:
        parseHilnEnexConfig(r3, r4, r5, r6);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L140:
        return false;
    L8:
        if (getClass() != r5.getClass()) goto L140;
        AudioSpecificConfig r52 = (AudioSpecificConfig) r5;
        if (this.aacScalefactorDataResilienceFlag == r52.aacScalefactorDataResilienceFlag) goto L14;
        return false;
    L14:
        if (this.aacSectionDataResilienceFlag == r52.aacSectionDataResilienceFlag) goto L17;
        return false;
    L17:
        if (this.aacSpectralDataResilienceFlag == r52.aacSpectralDataResilienceFlag) goto L20;
        return false;
    L20:
        if (this.audioObjectType == r52.audioObjectType) goto L23;
        return false;
    L23:
        if (this.channelConfiguration == r52.channelConfiguration) goto L26;
        return false;
    L26:
        if (this.coreCoderDelay == r52.coreCoderDelay) goto L29;
        return false;
    L29:
        if (this.dependsOnCoreCoder == r52.dependsOnCoreCoder) goto L32;
        return false;
    L32:
        if (this.directMapping == r52.directMapping) goto L35;
        return false;
    L35:
        if (this.epConfig == r52.epConfig) goto L38;
        return false;
    L38:
        if (this.erHvxcExtensionFlag == r52.erHvxcExtensionFlag) goto L41;
        return false;
    L41:
        if (this.extensionAudioObjectType == r52.extensionAudioObjectType) goto L44;
        return false;
    L44:
        if (this.extensionChannelConfiguration == r52.extensionChannelConfiguration) goto L47;
        return false;
    L47:
        if (this.extensionFlag == r52.extensionFlag) goto L50;
        return false;
    L50:
        if (this.extensionFlag3 == r52.extensionFlag3) goto L53;
        return false;
    L53:
        if (this.extensionSamplingFrequency == r52.extensionSamplingFrequency) goto L56;
        return false;
    L56:
        if (this.extensionSamplingFrequencyIndex == r52.extensionSamplingFrequencyIndex) goto L59;
        return false;
    L59:
        if (this.fillBits == r52.fillBits) goto L62;
        return false;
    L62:
        if (this.frameLengthFlag == r52.frameLengthFlag) goto L65;
        return false;
    L65:
        if (this.gaSpecificConfig == r52.gaSpecificConfig) goto L68;
        return false;
    L68:
        if (this.hilnContMode == r52.hilnContMode) goto L71;
        return false;
    L71:
        if (this.hilnEnhaLayer == r52.hilnEnhaLayer) goto L74;
        return false;
    L74:
        if (this.hilnEnhaQuantMode == r52.hilnEnhaQuantMode) goto L77;
        return false;
    L77:
        if (this.hilnFrameLength == r52.hilnFrameLength) goto L80;
        return false;
    L80:
        if (this.hilnMaxNumLine == r52.hilnMaxNumLine) goto L83;
        return false;
    L83:
        if (this.hilnQuantMode == r52.hilnQuantMode) goto L86;
        return false;
    L86:
        if (this.hilnSampleRateCode == r52.hilnSampleRateCode) goto L89;
        return false;
    L89:
        if (this.hvxcRateMode == r52.hvxcRateMode) goto L92;
        return false;
    L92:
        if (this.hvxcVarMode == r52.hvxcVarMode) goto L95;
        return false;
    L95:
        if (this.isBaseLayer == r52.isBaseLayer) goto L98;
        return false;
    L98:
        if (this.layerNr == r52.layerNr) goto L101;
        return false;
    L101:
        if (this.layer_length == r52.layer_length) goto L104;
        return false;
    L104:
        if (this.numOfSubFrame == r52.numOfSubFrame) goto L107;
        return false;
    L107:
        if (this.paraExtensionFlag == r52.paraExtensionFlag) goto L110;
        return false;
    L110:
        if (this.paraMode == r52.paraMode) goto L113;
        return false;
    L113:
        if (this.parametricSpecificConfig == r52.parametricSpecificConfig) goto L116;
        return false;
    L116:
        if (this.psPresentFlag == r52.psPresentFlag) goto L119;
        return false;
    L119:
        if (this.sacPayloadEmbedding == r52.sacPayloadEmbedding) goto L122;
        return false;
    L122:
        if (this.samplingFrequency == r52.samplingFrequency) goto L125;
        return false;
    L125:
        if (this.samplingFrequencyIndex == r52.samplingFrequencyIndex) goto L128;
        return false;
    L128:
        if (this.sbrPresentFlag == r52.sbrPresentFlag) goto L131;
        return false;
    L131:
        if (this.syncExtensionType == r52.syncExtensionType) goto L134;
        return false;
    L134:
        if (this.var_ScalableFlag == r52.var_ScalableFlag) goto L137;
        return false;
    L137:
        if (Arrays.equals(this.configBytes, r52.configBytes) == true) goto L139;
        return false;
    L139:
        return true;
    }

    public int hashCode() {
        byte[] r0 = this.configBytes;
        if (r0 == null) goto L5;
        int r02 = Arrays.hashCode(r0);
    L7:
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((r02 * 31) + this.audioObjectType) * 31) + this.samplingFrequencyIndex) * 31) + this.samplingFrequency) * 31) + this.channelConfiguration) * 31) + this.extensionAudioObjectType) * 31) + (this.sbrPresentFlag ? 1 : 0)) * 31) + (this.psPresentFlag ? 1 : 0)) * 31) + this.extensionSamplingFrequencyIndex) * 31) + this.extensionSamplingFrequency) * 31) + this.extensionChannelConfiguration) * 31) + this.sacPayloadEmbedding) * 31) + this.fillBits) * 31) + this.epConfig) * 31) + this.directMapping) * 31) + this.syncExtensionType) * 31) + this.frameLengthFlag) * 31) + this.dependsOnCoreCoder) * 31) + this.coreCoderDelay) * 31) + this.extensionFlag) * 31) + this.layerNr) * 31) + this.numOfSubFrame) * 31) + this.layer_length) * 31) + (this.aacSectionDataResilienceFlag ? 1 : 0)) * 31) + (this.aacScalefactorDataResilienceFlag ? 1 : 0)) * 31) + (this.aacSpectralDataResilienceFlag ? 1 : 0)) * 31) + this.extensionFlag3) * 31) + (this.gaSpecificConfig ? 1 : 0)) * 31) + this.isBaseLayer) * 31) + this.paraMode) * 31) + this.paraExtensionFlag) * 31) + this.hvxcVarMode) * 31) + this.hvxcRateMode) * 31) + this.erHvxcExtensionFlag) * 31) + this.var_ScalableFlag) * 31) + this.hilnQuantMode) * 31) + this.hilnMaxNumLine) * 31) + this.hilnSampleRateCode) * 31) + this.hilnFrameLength) * 31) + this.hilnContMode) * 31) + this.hilnEnhaLayer) * 31) + this.hilnEnhaQuantMode) * 31) + (this.parametricSpecificConfig ? 1 : 0);
    L5:
        r02 = 0;
        goto L7
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public void parseDetail(ByteBuffer r10) {
        ByteBuffer r0 = r10.slice();
        r0.limit(this.sizeOfInstance);
        r10.position(r10.position() + this.sizeOfInstance);
        byte[] r102 = new byte[this.sizeOfInstance];
        this.configBytes = r102;
        r0.get(r102);
        r0.rewind();
        BitReaderBuffer r103 = new BitReaderBuffer(r0);
        this.audioObjectType = getAudioObjectType(r103);
        int r1 = r103.readBits(4);
        this.samplingFrequencyIndex = r1;
        if (r1 != 15) goto L5;
        this.samplingFrequency = r103.readBits(24);
    L5:
        this.channelConfiguration = r103.readBits(4);
        int r12 = this.audioObjectType;
        if (r12 == 5) goto L10;
        if (r12 == 29) goto L10;
        this.extensionAudioObjectType = 0;
    L19:
        int r13 = this.audioObjectType;
        switch(r13) {
            case 1: goto L50;
            case 2: goto L50;
            case 3: goto L50;
            case 4: goto L50;
            case 5: goto L51;
            case 6: goto L50;
            case 7: goto L50;
            case 8: goto L48;
            case 9: goto L46;
            case 10: goto L51;
            case 11: goto L51;
            case 12: goto L44;
            case 13: goto L42;
            case 14: goto L42;
            case 15: goto L42;
            case 16: goto L42;
            case 17: goto L50;
            case 18: goto L51;
            case 19: goto L50;
            case 20: goto L50;
            case 21: goto L50;
            case 22: goto L50;
            case 23: goto L50;
            case 24: goto L40;
            case 25: goto L38;
            case 26: goto L37;
            case 27: goto L37;
            case 28: goto L35;
            case 29: goto L51;
            case 30: goto L33;
            case 31: goto L51;
            case 32: goto L31;
            case 33: goto L31;
            case 34: goto L31;
            case 35: goto L29;
            case 36: goto L27;
            case 37: goto L25;
            case 38: goto L25;
            case 39: goto L24;
            case 40: goto L22;
            case 41: goto L22;
            default: goto L51;
        };
    L22:
        ByteString$$ExternalSyntheticBUOutline0.m("can't parse SymbolicMusicSpecificConfig yet");
        return;
    L24:
        this.eldSpecificConfig = new ELDSpecificConfig(this, this.channelConfiguration, r103);
        goto L51
    L25:
        ByteString$$ExternalSyntheticBUOutline0.m("can't parse SLSSpecificConfig yet");
        return;
    L27:
        this.fillBits = r103.readBits(5);
        ByteString$$ExternalSyntheticBUOutline0.m("can't parse ALSSpecificConfig yet");
        return;
    L29:
        ByteString$$ExternalSyntheticBUOutline0.m("can't parse DSTSpecificConfig yet");
        return;
    L31:
        ByteString$$ExternalSyntheticBUOutline0.m("can't parse MPEG_1_2_SpecificConfig yet");
        return;
    L33:
        this.sacPayloadEmbedding = r103.readBits(1);
        ByteString$$ExternalSyntheticBUOutline0.m("can't parse SpatialSpecificConfig yet");
        return;
    L35:
        ByteString$$ExternalSyntheticBUOutline0.m("can't parse SSCSpecificConfig yet");
        return;
    L37:
        parseParametricSpecificConfig(this.samplingFrequencyIndex, this.channelConfiguration, r13, r103);
        goto L51
    L38:
        ByteString$$ExternalSyntheticBUOutline0.m("can't parse ErrorResilientHvxcSpecificConfig yet");
        return;
    L40:
        ByteString$$ExternalSyntheticBUOutline0.m("can't parse ErrorResilientCelpSpecificConfig yet");
        return;
    L42:
        ByteString$$ExternalSyntheticBUOutline0.m("can't parse StructuredAudioSpecificConfig yet");
        return;
    L44:
        ByteString$$ExternalSyntheticBUOutline0.m("can't parse TTSSpecificConfig yet");
        return;
    L46:
        ByteString$$ExternalSyntheticBUOutline0.m("can't parse HvxcSpecificConfig yet");
        return;
    L48:
        ByteString$$ExternalSyntheticBUOutline0.m("can't parse CelpSpecificConfig yet");
        return;
    L50:
        parseGaSpecificConfig(this.samplingFrequencyIndex, this.channelConfiguration, r13, r103);
    L51:
        int r14 = this.audioObjectType;
        if (r14 != 17) goto L54;
    L57:
        int r5 = r103.readBits(2);
        this.epConfig = r5;
        if (r5 != 2) goto L60;
    L94:
        ByteString$$ExternalSyntheticBUOutline0.m("can't parse ErrorProtectionSpecificConfig yet");
        return;
    L60:
        if (r5 == 3) goto L94;
        if (r5 != 3) goto L68;
        int r15 = r103.readBits(1);
        this.directMapping = r15;
        if (r15 != 0) goto L68;
        GlShader$$ExternalSyntheticBUOutline1.m("not implemented");
        return;
    L68:
        if (this.extensionAudioObjectType != 5) goto L70;
        return;
    L70:
        if (r103.remainingBits() < 16) goto L97;
        int r52 = r103.readBits(11);
        this.syncExtensionType = r52;
        if (r52 != 695) goto L98;
        int r53 = getAudioObjectType(r103);
        this.extensionAudioObjectType = r53;
        if (r53 != 5) goto L86;
        boolean r54 = r103.readBool();
        this.sbrPresentFlag = r54;
        if (r54 == false) goto L86;
        int r55 = r103.readBits(4);
        this.extensionSamplingFrequencyIndex = r55;
        if (r55 != 15) goto L81;
        this.extensionSamplingFrequency = r103.readBits(24);
    L81:
        if (r103.remainingBits() < 12) goto L86;
        int r16 = r103.readBits(11);
        this.syncExtensionType = r16;
        if (r16 != 1352) goto L86;
        this.psPresentFlag = r103.readBool();
    L86:
        if (this.extensionAudioObjectType != 22) goto L99;
        boolean r17 = r103.readBool();
        this.sbrPresentFlag = r17;
        if (r17 == false) goto L92;
        int r18 = r103.readBits(4);
        this.extensionSamplingFrequencyIndex = r18;
        if (r18 != 15) goto L92;
        this.extensionSamplingFrequency = r103.readBits(24);
    L92:
        this.extensionChannelConfiguration = r103.readBits(4);
        return;
    L99:
        return;
    L98:
        return;
    L97:
        return;
    L54:
        if (r14 == 39) goto L57;
        switch(r14) {
            case 19: goto L57;
            case 20: goto L57;
            case 21: goto L57;
            case 22: goto L57;
            case 23: goto L57;
            case 24: goto L57;
            case 25: goto L57;
            case 26: goto L57;
            case 27: goto L57;
            default: goto L68;
        };
    L10:
        this.extensionAudioObjectType = 5;
        this.sbrPresentFlag = true;
        if (r12 != 29) goto L13;
        this.psPresentFlag = true;
    L13:
        int r19 = r103.readBits(4);
        this.extensionSamplingFrequencyIndex = r19;
        if (r19 != 15) goto L16;
        this.extensionSamplingFrequency = r103.readBits(24);
    L16:
        int r110 = getAudioObjectType(r103);
        this.audioObjectType = r110;
        if (r110 != 22) goto L19;
        this.extensionChannelConfiguration = r103.readBits(4);
        goto L19
    }

    public ByteBuffer serialize() {
        ByteBuffer r0 = ByteBuffer.allocate(serializedSize());
        IsoTypeWriter.writeUInt8(r0, 5);
        IsoTypeWriter.writeUInt8(r0, serializedSize() - 2);
        BitWriterBuffer r2 = new BitWriterBuffer(r0);
        r2.writeBits(this.audioObjectType, 5);
        r2.writeBits(this.samplingFrequencyIndex, 4);
        if (this.samplingFrequencyIndex == 15) goto L6;
        r2.writeBits(this.channelConfiguration, 4);
        return r0;
    L6:
        ByteString$$ExternalSyntheticBUOutline0.m("can't serialize that yet");
        return null;
    }

    public int serializedSize() {
        if (this.audioObjectType == 2) goto L5;
        ByteString$$ExternalSyntheticBUOutline0.m("can't serialize that yet");
        return 0;
    L5:
        return gaSpecificConfigSize() + 4;
    }

    public void setAudioObjectType(int r1) {
        this.audioObjectType = r1;
    }

    public void setChannelConfiguration(int r1) {
        this.channelConfiguration = r1;
    }

    public void setSamplingFrequencyIndex(int r1) {
        this.samplingFrequencyIndex = r1;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("AudioSpecificConfig{configBytes=");
        r0.append(Hex.encodeHex(this.configBytes));
        r0.append(", audioObjectType=");
        r0.append(this.audioObjectType);
        r0.append(" (");
        r0.append(audioObjectTypeMap.get(Integer.valueOf(this.audioObjectType)));
        r0.append("), samplingFrequencyIndex=");
        r0.append(this.samplingFrequencyIndex);
        r0.append(" (");
        r0.append(samplingFrequencyIndexMap.get(Integer.valueOf(this.samplingFrequencyIndex)));
        r0.append("), samplingFrequency=");
        r0.append(this.samplingFrequency);
        r0.append(", channelConfiguration=");
        r0.append(this.channelConfiguration);
        if (this.extensionAudioObjectType <= 0) goto L5;
        r0.append(", extensionAudioObjectType=");
        r0.append(this.extensionAudioObjectType);
        r0.append(" (");
        r0.append(audioObjectTypeMap.get(Integer.valueOf(this.extensionAudioObjectType)));
        r0.append("), sbrPresentFlag=");
        r0.append(this.sbrPresentFlag);
        r0.append(", psPresentFlag=");
        r0.append(this.psPresentFlag);
        r0.append(", extensionSamplingFrequencyIndex=");
        r0.append(this.extensionSamplingFrequencyIndex);
        r0.append(" (");
        r0.append(samplingFrequencyIndexMap.get(Integer.valueOf(this.extensionSamplingFrequencyIndex)));
        r0.append("), extensionSamplingFrequency=");
        r0.append(this.extensionSamplingFrequency);
        r0.append(", extensionChannelConfiguration=");
        r0.append(this.extensionChannelConfiguration);
    L5:
        r0.append(", syncExtensionType=");
        r0.append(this.syncExtensionType);
        if (this.gaSpecificConfig == false) goto L9;
        r0.append(", frameLengthFlag=");
        r0.append(this.frameLengthFlag);
        r0.append(", dependsOnCoreCoder=");
        r0.append(this.dependsOnCoreCoder);
        r0.append(", coreCoderDelay=");
        r0.append(this.coreCoderDelay);
        r0.append(", extensionFlag=");
        r0.append(this.extensionFlag);
        r0.append(", layerNr=");
        r0.append(this.layerNr);
        r0.append(", numOfSubFrame=");
        r0.append(this.numOfSubFrame);
        r0.append(", layer_length=");
        r0.append(this.layer_length);
        r0.append(", aacSectionDataResilienceFlag=");
        r0.append(this.aacSectionDataResilienceFlag);
        r0.append(", aacScalefactorDataResilienceFlag=");
        r0.append(this.aacScalefactorDataResilienceFlag);
        r0.append(", aacSpectralDataResilienceFlag=");
        r0.append(this.aacSpectralDataResilienceFlag);
        r0.append(", extensionFlag3=");
        r0.append(this.extensionFlag3);
    L9:
        if (this.parametricSpecificConfig == false) goto L11;
        r0.append(", isBaseLayer=");
        r0.append(this.isBaseLayer);
        r0.append(", paraMode=");
        r0.append(this.paraMode);
        r0.append(", paraExtensionFlag=");
        r0.append(this.paraExtensionFlag);
        r0.append(", hvxcVarMode=");
        r0.append(this.hvxcVarMode);
        r0.append(", hvxcRateMode=");
        r0.append(this.hvxcRateMode);
        r0.append(", erHvxcExtensionFlag=");
        r0.append(this.erHvxcExtensionFlag);
        r0.append(", var_ScalableFlag=");
        r0.append(this.var_ScalableFlag);
        r0.append(", hilnQuantMode=");
        r0.append(this.hilnQuantMode);
        r0.append(", hilnMaxNumLine=");
        r0.append(this.hilnMaxNumLine);
        r0.append(", hilnSampleRateCode=");
        r0.append(this.hilnSampleRateCode);
        r0.append(", hilnFrameLength=");
        r0.append(this.hilnFrameLength);
        r0.append(", hilnContMode=");
        r0.append(this.hilnContMode);
        r0.append(", hilnEnhaLayer=");
        r0.append(this.hilnEnhaLayer);
        r0.append(", hilnEnhaQuantMode=");
        r0.append(this.hilnEnhaQuantMode);
    L11:
        r0.append('}');
        return r0.toString();
    }
}
