package androidx.media3.exoplayer.audio;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioDeviceInfo;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Pair;
import android.util.SparseArray;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.audio.AudioManagerCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.primitives.Ints;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes4.dex */
public final class AudioCapabilities {
    static final ImmutableMap<Integer, Integer> ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS = null;
    public static final AudioCapabilities DEFAULT_AUDIO_CAPABILITIES = null;

    @SuppressLint({"InlinedApi"})
    private static final ImmutableList<Integer> EXTERNAL_SURROUND_SOUND_ENCODINGS = null;
    private final SparseArray<AudioProfile> encodingToAudioProfile;
    private final int maxChannelCount;

    /* renamed from: androidx.media3.exoplayer.audio.AudioCapabilities$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Api23 {
        private static ImmutableSet<Integer> getAllBluetoothDeviceTypes() {
            ImmutableSet.Builder r0 = new ImmutableSet.Builder().add(new Integer[]{8, 7});
            int r1 = Build.VERSION.SDK_INT;
            if (r1 < 31) goto L6;
            r0.add(new Integer[]{26, 27});
        L6:
            if (r1 < 33) goto L9;
            r0.add(30);
        L9:
            return r0.build();
        }

        public static boolean isBluetoothConnected(AudioManager r5, AudioDeviceInfoApi23 r6) {
            if (r6 != null) goto L5;
            AudioDeviceInfo[] r52 = ((AudioManager) Assertions.checkNotNull(r5)).getDevices(2);
        L6:
            ImmutableSet<Integer> r62 = getAllBluetoothDeviceTypes();
            int r2 = r52.length;
            int r3 = 0;
        L7:
            if (r3 >= r2) goto L12;
            if (r62.contains(Integer.valueOf(r52[r3].getType())) == true) goto L10;
            r3 = r3 + 1;
            goto L7
        L10:
            return true;
        L12:
            return false;
        L5:
            r52 = new AudioDeviceInfo[]{r6.audioDeviceInfo};
            goto L6
        }
    }

    public static final class Api29 {
        public static ImmutableList<Integer> getDirectPlaybackSupportedEncodings(AudioAttributes r6) {
            ImmutableList.Builder r0 = ImmutableList.builder();
            UnmodifiableIterator<Integer> r1 = AudioCapabilities.ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.keySet().iterator();
        L4:
            if (r1.hasNext() == false) goto L11;
            Integer r2 = r1.next();
            int r3 = r2.intValue();
            if (Build.VERSION.SDK_INT < Util.getApiLevelThatAudioFormatIntroducedAudioEncoding(r3)) goto L4;
            if (AudioTrack.isDirectPlaybackSupported(new AudioFormat.Builder().setChannelMask(12).setEncoding(r3).setSampleRate(48000).build(), r6.getAudioAttributesV21().audioAttributes) == false) goto L4;
            r0.add(r2);
            goto L4
        L11:
            r0.add(2);
            return r0.build();
        }

        public static int getMaxSupportedChannelCountForPassthrough(int r3, int r4, AudioAttributes r5) {
            int r0 = 10;
        L3:
            if (r0 <= 0) goto L11;
            int r1 = Util.getAudioTrackChannelConfig(r0);
            if (r1 == 0) goto L10;
            if (AudioTrack.isDirectPlaybackSupported(new AudioFormat.Builder().setEncoding(r3).setSampleRate(r4).setChannelMask(r1).build(), r5.getAudioAttributesV21().audioAttributes) == false) goto L10;
            return r0;
        L10:
            r0 = r0 - 1;
            goto L3
        L11:
            return 0;
        }
    }

    public static final class Api33 {
        public static AudioCapabilities getCapabilitiesInternalForDirectPlayback(AudioManager r1, AudioAttributes r2) {
            return new AudioCapabilities(AudioCapabilities.access$000(r1.getDirectProfilesForAttributes(r2.getAudioAttributesV21().audioAttributes)), null);
        }

        public static AudioDeviceInfoApi23 getDefaultRoutedDeviceForAttributes(AudioManager r1, AudioAttributes r2) {
            List<AudioDeviceInfo> r12 = ((AudioManager) Assertions.checkNotNull(r1)).getAudioDevicesForAttributes(r2.getAudioAttributesV21().audioAttributes);
            if (r12.isEmpty() == false) goto L7;
            return null;
        L7:
            return new AudioDeviceInfoApi23(r12.get(0));
        }
    }

    public static final class AudioProfile {
        public static final AudioProfile DEFAULT_AUDIO_PROFILE = null;
        private final ImmutableSet<Integer> channelMasks;
        public final int encoding;
        public final int maxChannelCount;

        static {
            if (Build.VERSION.SDK_INT < 33) goto L5;
            AudioProfile r0 = new AudioProfile(2, getAllChannelMasksForMaxChannelCount(10));
        L6:
            DEFAULT_AUDIO_PROFILE = r0;
            return;
        L5:
            r0 = new AudioProfile(2, 10);
            goto L6
        }

        public AudioProfile(int r1, int r2) {
            this.encoding = r1;
            this.maxChannelCount = r2;
            this.channelMasks = null;
        }

        public AudioProfile(int r2, Set<Integer> r3) {
            this.encoding = r2;
            ImmutableSet<Integer> r22 = ImmutableSet.copyOf(r3);
            this.channelMasks = r22;
            UnmodifiableIterator<Integer> r23 = r22.iterator();
            int r32 = 0;
        L4:
            if (r23.hasNext() == false) goto L6;
            r32 = Math.max(r32, Integer.bitCount(r23.next().intValue()));
            goto L4
        L6:
            this.maxChannelCount = r32;
        }

        private static ImmutableSet<Integer> getAllChannelMasksForMaxChannelCount(int r3) {
            ImmutableSet.Builder r0 = new ImmutableSet.Builder();
            int r1 = 1;
        L3:
            if (r1 > r3) goto L6;
            r0.add(Integer.valueOf(Util.getAudioTrackChannelConfig(r1)));
            r1 = r1 + 1;
            goto L3
        L6:
            return r0.build();
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof AudioProfile) == true) goto L8;
            return false;
        L8:
            AudioProfile r52 = (AudioProfile) r5;
            if (this.encoding == r52.encoding) goto L11;
        L15:
            return false;
        L11:
            if (this.maxChannelCount != r52.maxChannelCount) goto L15;
            if (Objects.equals(this.channelMasks, r52.channelMasks) == false) goto L15;
            return true;
        }

        public int getMaxSupportedChannelCountForPassthrough(int r3, AudioAttributes r4) {
            if (this.channelMasks == null) goto L7;
            return this.maxChannelCount;
        L7:
            if (Build.VERSION.SDK_INT < 29) goto L11;
            return Api29.getMaxSupportedChannelCountForPassthrough(this.encoding, r3, r4);
        L11:
            return ((Integer) Assertions.checkNotNull(AudioCapabilities.ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.getOrDefault(Integer.valueOf(this.encoding), 0))).intValue();
        }

        public int hashCode() {
            int r0 = ((this.encoding * 31) + this.maxChannelCount) * 31;
            ImmutableSet<Integer> r2 = this.channelMasks;
            if (r2 != null) goto L5;
            int r22 = 0;
        L7:
            return r0 + r22;
        L5:
            r22 = r2.hashCode();
            goto L7
        }

        public boolean supportsChannelCount(int r3) {
            if (this.channelMasks == null) goto L5;
            int r32 = Util.getAudioTrackChannelConfig(r3);
            if (r32 != 0) goto L13;
            return false;
        L13:
            return this.channelMasks.contains(Integer.valueOf(r32));
        L5:
            if (r3 > this.maxChannelCount) goto L8;
            return true;
        L8:
            return false;
        }

        public String toString() {
            return "AudioProfile[format=" + this.encoding + ", maxChannelCount=" + this.maxChannelCount + ", channelMasks=" + this.channelMasks + "]";
        }
    }

    static {
        DEFAULT_AUDIO_CAPABILITIES = new AudioCapabilities(ImmutableList.of(AudioProfile.DEFAULT_AUDIO_PROFILE));
        EXTERNAL_SURROUND_SOUND_ENCODINGS = ImmutableList.of(2, 5, 6);
        ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS = new ImmutableMap.Builder().put(5, 6).put(17, 6).put(7, 6).put(30, 10).put(18, 6).put(6, 8).put(8, 8).put(14, 8).buildOrThrow();
    }

    private AudioCapabilities(List<AudioProfile> r6) {
        this.encodingToAudioProfile = new SparseArray();
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= r6.size()) goto L6;
        AudioProfile r2 = r6.get(r1);
        this.encodingToAudioProfile.put(r2.encoding, r2);
        r1 = r1 + 1;
        goto L4
    L6:
        int r62 = 0;
    L8:
        if (r0 >= this.encodingToAudioProfile.size()) goto L10;
        r62 = Math.max(r62, this.encodingToAudioProfile.valueAt(r0).maxChannelCount);
        r0 = r0 + 1;
        goto L8
    L10:
        this.maxChannelCount = r62;
    }

    public /* synthetic */ AudioCapabilities(List r1, AnonymousClass1 r2) {
        this(r1);
    }

    public static /* synthetic */ ImmutableList access$000(List r0) {
        return getAudioProfiles(r0);
    }

    private static boolean deviceMaySetExternalSurroundSoundGlobalSetting() {
        String r0 = Build.MANUFACTURER;
        if (r0.equals("Amazon") == false) goto L5;
        return true;
    L5:
        if (r0.equals("Xiaomi") == true) goto L11;
        return false;
    L11:
        return true;
    }

    @SuppressLint({"WrongConstant"})
    private static ImmutableList<AudioProfile> getAudioProfiles(List<android.media.AudioProfile> r6) {
        HashMap r0 = new HashMap();
        r0.put(2, new HashSet(Ints.asList(new int[]{12})));
        int r1 = 0;
    L4:
        if (r1 >= r6.size()) goto L18;
        android.media.AudioProfile r2 = AudioCapabilities$$ExternalSyntheticApiModelOutline0.m(r6.get(r1));
        if (r2.getEncapsulationType() == 1) goto L17;
        int r3 = r2.getFormat();
        if (Util.isEncodingLinearPcm(r3) == true) goto L14;
        if (ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.containsKey(Integer.valueOf(r3)) == false) goto L17;
    L14:
        if (r0.containsKey(Integer.valueOf(r3)) == false) goto L16;
        ((Set) Assertions.checkNotNull((Set) r0.get(Integer.valueOf(r3)))).addAll(Ints.asList(r2.getChannelMasks()));
        goto L17
    L16:
        r0.put(Integer.valueOf(r3), new HashSet(Ints.asList(r2.getChannelMasks())));
    L17:
        r1 = r1 + 1;
        goto L4
    L18:
        ImmutableList.Builder r62 = ImmutableList.builder();
        Iterator r02 = r0.entrySet().iterator();
    L20:
        if (r02.hasNext() == false) goto L23;
        Map.Entry r12 = (Map.Entry) r02.next();
        r62.add(new AudioProfile(((Integer) r12.getKey()).intValue(), (Set) r12.getValue()));
        goto L20
    L23:
        return r62.build();
    }

    private static ImmutableList<AudioProfile> getAudioProfiles(int[] r4, int r5) {
        ImmutableList.Builder r0 = ImmutableList.builder();
        int r1 = 0;
        if (r4 != null) goto L6;
        r4 = new int[0];
    L6:
        if (r1 >= r4.length) goto L9;
        r0.add(new AudioProfile(r4[r1], r5));
        r1 = r1 + 1;
        goto L6
    L9:
        return r0.build();
    }

    @SuppressLint({"InlinedApi"})
    public static AudioCapabilities getCapabilitiesInternal(Context r4, Intent r5, AudioAttributes r6, AudioDeviceInfoApi23 r7) {
        AudioManager r0 = AudioManagerCompat.getAudioManager(r4);
        if (r7 == null) goto L6;
    L9:
        int r2 = Build.VERSION.SDK_INT;
        if (r2 < 33) goto L18;
        if (Util.isTv(r4) == true) goto L16;
        if (Util.isAutomotive(r4) == false) goto L18;
    L16:
        return Api33.getCapabilitiesInternalForDirectPlayback(r0, r6);
    L18:
        if (Api23.isBluetoothConnected(r0, r7) == true) goto L20;
        ImmutableSet.Builder r72 = new ImmutableSet.Builder();
        r72.add(2);
        if (r2 >= 29) goto L24;
    L29:
        ContentResolver r42 = r4.getContentResolver();
        if (Settings.Global.getInt(r42, "use_external_surround_sound_flag", 0) != 1) goto L32;
        boolean r62 = true;
    L33:
        if (r62 == true) goto L37;
        if (deviceMaySetExternalSurroundSoundGlobalSetting() == true) goto L37;
    L39:
        if (r5 == null) goto L49;
        if (r62 == true) goto L49;
        if (r5.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) != 1) goto L49;
        int[] r43 = r5.getIntArrayExtra("android.media.extra.ENCODINGS");
        if (r43 == null) goto L47;
        r72.addAll(Ints.asList(r43));
    L47:
        return new AudioCapabilities(getAudioProfiles(Ints.toArray(r72.build()), r5.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 10)));
    L49:
        return new AudioCapabilities(getAudioProfiles(Ints.toArray(r72.build()), 10));
    L37:
        if (Settings.Global.getInt(r42, "external_surround_sound_enabled", 0) != 1) goto L39;
        r72.addAll(EXTERNAL_SURROUND_SOUND_ENCODINGS);
        goto L39
    L32:
        r62 = false;
        goto L33
    L24:
        if (Util.isTv(r4) == false) goto L26;
    L27:
        r72.addAll(Api29.getDirectPlaybackSupportedEncodings(r6));
        return new AudioCapabilities(getAudioProfiles(Ints.toArray(r72.build()), 10));
    L26:
        if (Util.isAutomotive(r4) == false) goto L29;
    L20:
        return DEFAULT_AUDIO_CAPABILITIES;
    L6:
        if (Build.VERSION.SDK_INT < 33) goto L8;
        r7 = Api33.getDefaultRoutedDeviceForAttributes(r0, r6);
        goto L9
    L8:
        r7 = null;
        goto L9
    }

    @SuppressLint({"UnprotectedReceiver"})
    public static AudioCapabilities getCapabilitiesInternal(Context r2, AudioAttributes r3, AudioDeviceInfoApi23 r4) {
        return getCapabilitiesInternal(r2, r2.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG")), r3, r4);
    }

    private static int getChannelConfigForPassthrough(int r2) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 > 28) goto L15;
        if (r2 != 7) goto L8;
        r2 = 8;
        goto L15
    L8:
        if (r2 != 3) goto L10;
    L13:
        r2 = 6;
        goto L15
    L10:
        if (r2 == 4) goto L13;
        if (r2 == 5) goto L13;
    L15:
        if (r0 > 26) goto L22;
        if ("fugu".equals(Build.DEVICE) == false) goto L22;
        if (r2 != 1) goto L22;
        r2 = 2;
    L22:
        return Util.getAudioTrackChannelConfig(r2);
    }

    public static Uri getExternalSurroundSoundGlobalSettingUri() {
        if (deviceMaySetExternalSurroundSoundGlobalSetting() == true) goto L5;
        return null;
    L5:
        return Settings.Global.getUriFor("external_surround_sound_enabled");
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof AudioCapabilities) == true) goto L8;
        return false;
    L8:
        AudioCapabilities r52 = (AudioCapabilities) r5;
        if (Util.contentEquals(this.encodingToAudioProfile, r52.encodingToAudioProfile) == true) goto L11;
    L13:
        return false;
    L11:
        if (this.maxChannelCount != r52.maxChannelCount) goto L13;
        return true;
    }

    public Pair<Integer, Integer> getEncodingAndChannelConfigForPassthrough(Format r6, AudioAttributes r7) {
        int r0 = MimeTypes.getEncoding((String) Assertions.checkNotNull(r6.sampleMimeType), r6.codecs);
        if (ALL_SURROUND_ENCODINGS_AND_MAX_CHANNELS.containsKey(Integer.valueOf(r0)) == true) goto L6;
        return null;
    L6:
        if (r0 != 18) goto L11;
        if (supportsEncoding(18) == true) goto L11;
        r0 = 6;
    L20:
        if (supportsEncoding(r0) == true) goto L22;
        return null;
    L22:
        AudioProfile r5 = (AudioProfile) Assertions.checkNotNull(this.encodingToAudioProfile.get(r0));
        int r3 = r6.channelCount;
        if (r3 == (-1)) goto L36;
        if (r0 == 18) goto L36;
        if (r6.sampleMimeType.equals("audio/vnd.dts.uhd;profile=p2") == false) goto L34;
        if (Build.VERSION.SDK_INT >= 33) goto L34;
        if (r3 <= 10) goto L41;
        return null;
    L41:
        int r52 = getChannelConfigForPassthrough(r3);
        if (r52 != 0) goto L45;
        return null;
    L45:
        return Pair.create(Integer.valueOf(r0), Integer.valueOf(r52));
    L34:
        if (r5.supportsChannelCount(r3) == true) goto L41;
        return null;
    L36:
        int r62 = r6.sampleRate;
        if (r62 != (-1)) goto L40;
        r62 = 48000;
    L40:
        r3 = r5.getMaxSupportedChannelCountForPassthrough(r62, r7);
    L11:
        if (r0 != 8) goto L15;
        if (supportsEncoding(8) == true) goto L15;
    L18:
        r0 = 7;
    L15:
        if (r0 != 30) goto L20;
        if (supportsEncoding(30) == true) goto L20;
        goto L18
    }

    public int hashCode() {
        return this.maxChannelCount + (Util.contentHashCode(this.encodingToAudioProfile) * 31);
    }

    public boolean isPassthroughPlaybackSupported(Format r1, AudioAttributes r2) {
        if (getEncodingAndChannelConfigForPassthrough(r1, r2) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean supportsEncoding(int r1) {
        return Util.contains(this.encodingToAudioProfile, r1);
    }

    public String toString() {
        return "AudioCapabilities[maxChannelCount=" + this.maxChannelCount + ", audioProfiles=" + this.encodingToAudioProfile + "]";
    }
}
