package androidx.media3.exoplayer.audio;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.net.Uri;
import android.os.Handler;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.audio.AudioManagerCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class AudioCapabilitiesReceiver {
    private AudioAttributes audioAttributes;
    private AudioCapabilities audioCapabilities;
    private final AudioDeviceCallbackV23 audioDeviceCallback;
    private final Context context;
    private final ExternalSurroundSoundSettingObserver externalSurroundSoundSettingObserver;
    private final Handler handler;
    private final BroadcastReceiver hdmiAudioPlugBroadcastReceiver;
    private final Listener listener;
    private boolean registered;
    private AudioDeviceInfoApi23 routedDevice;

    /* renamed from: androidx.media3.exoplayer.audio.AudioCapabilitiesReceiver$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Api23 {
        public static void registerAudioDeviceCallback(Context r0, AudioDeviceCallback r1, Handler r2) {
            AudioManagerCompat.getAudioManager(r0).registerAudioDeviceCallback(r1, r2);
        }

        public static void unregisterAudioDeviceCallback(Context r0, AudioDeviceCallback r1) {
            AudioManagerCompat.getAudioManager(r0).unregisterAudioDeviceCallback(r1);
        }
    }

    public final class AudioDeviceCallbackV23 extends AudioDeviceCallback {
        final /* synthetic */ AudioCapabilitiesReceiver this$0;

        private AudioDeviceCallbackV23(AudioCapabilitiesReceiver r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ AudioDeviceCallbackV23(AudioCapabilitiesReceiver r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // android.media.AudioDeviceCallback
        public void onAudioDevicesAdded(AudioDeviceInfo[] r3) {
            AudioCapabilitiesReceiver r32 = this.this$0;
            AudioCapabilitiesReceiver.access$400(r32, AudioCapabilities.getCapabilitiesInternal(AudioCapabilitiesReceiver.access$500(r32), AudioCapabilitiesReceiver.access$200(this.this$0), AudioCapabilitiesReceiver.access$300(this.this$0)));
        }

        @Override // android.media.AudioDeviceCallback
        public void onAudioDevicesRemoved(AudioDeviceInfo[] r3) {
            if (Util.contains(r3, AudioCapabilitiesReceiver.access$300(this.this$0)) == false) goto L5;
            AudioCapabilitiesReceiver.access$302(this.this$0, null);
        L5:
            AudioCapabilitiesReceiver r32 = this.this$0;
            AudioCapabilitiesReceiver.access$400(r32, AudioCapabilities.getCapabilitiesInternal(AudioCapabilitiesReceiver.access$500(r32), AudioCapabilitiesReceiver.access$200(this.this$0), AudioCapabilitiesReceiver.access$300(this.this$0)));
        }
    }

    public final class ExternalSurroundSoundSettingObserver extends ContentObserver {
        private final ContentResolver resolver;
        private final Uri settingUri;
        final /* synthetic */ AudioCapabilitiesReceiver this$0;

        public ExternalSurroundSoundSettingObserver(AudioCapabilitiesReceiver r1, Handler r2, ContentResolver r3, Uri r4) {
            this.this$0 = r1;
            super(r2);
            this.resolver = r3;
            this.settingUri = r4;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean r3) {
            AudioCapabilitiesReceiver r32 = this.this$0;
            AudioCapabilitiesReceiver.access$400(r32, AudioCapabilities.getCapabilitiesInternal(AudioCapabilitiesReceiver.access$500(r32), AudioCapabilitiesReceiver.access$200(this.this$0), AudioCapabilitiesReceiver.access$300(this.this$0)));
        }

        public void register() {
            this.resolver.registerContentObserver(this.settingUri, false, this);
        }

        public void unregister() {
            this.resolver.unregisterContentObserver(this);
        }
    }

    public final class HdmiAudioPlugBroadcastReceiver extends BroadcastReceiver {
        final /* synthetic */ AudioCapabilitiesReceiver this$0;

        private HdmiAudioPlugBroadcastReceiver(AudioCapabilitiesReceiver r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ HdmiAudioPlugBroadcastReceiver(AudioCapabilitiesReceiver r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r3, Intent r4) {
            if (isInitialStickyBroadcast() == true) goto L6;
            AudioCapabilitiesReceiver r0 = this.this$0;
            AudioCapabilitiesReceiver.access$400(r0, AudioCapabilities.getCapabilitiesInternal(r3, r4, AudioCapabilitiesReceiver.access$200(r0), AudioCapabilitiesReceiver.access$300(this.this$0)));
            return;
        }
    }

    public interface Listener {
        void onAudioCapabilitiesChanged(AudioCapabilities r1);
    }

    public AudioCapabilitiesReceiver(Context r1, Listener r2, AudioAttributes r3, AudioDeviceInfoApi23 r4) {
        Context r12 = r1.getApplicationContext();
        this.context = r12;
        this.listener = (Listener) Assertions.checkNotNull(r2);
        this.audioAttributes = r3;
        this.routedDevice = r4;
        Handler r22 = Util.createHandlerForCurrentOrMainLooper();
        this.handler = r22;
        ExternalSurroundSoundSettingObserver r42 = null;
        this.audioDeviceCallback = new AudioDeviceCallbackV23(this, null);
        this.hdmiAudioPlugBroadcastReceiver = new HdmiAudioPlugBroadcastReceiver(this, null);
        Uri r32 = AudioCapabilities.getExternalSurroundSoundGlobalSettingUri();
        if (r32 == null) goto L5;
        r42 = new ExternalSurroundSoundSettingObserver(this, r22, r12.getContentResolver(), r32);
    L5:
        this.externalSurroundSoundSettingObserver = r42;
    }

    public static /* synthetic */ AudioAttributes access$200(AudioCapabilitiesReceiver r0) {
        return r0.audioAttributes;
    }

    public static /* synthetic */ AudioDeviceInfoApi23 access$300(AudioCapabilitiesReceiver r0) {
        return r0.routedDevice;
    }

    public static /* synthetic */ AudioDeviceInfoApi23 access$302(AudioCapabilitiesReceiver r0, AudioDeviceInfoApi23 r1) {
        r0.routedDevice = r1;
        return r1;
    }

    public static /* synthetic */ void access$400(AudioCapabilitiesReceiver r0, AudioCapabilities r1) {
        r0.onNewAudioCapabilities(r1);
    }

    public static /* synthetic */ Context access$500(AudioCapabilitiesReceiver r0) {
        return r0.context;
    }

    private void onNewAudioCapabilities(AudioCapabilities r2) {
        if (this.registered == true) goto L5;
        return;
    L5:
        if (r2.equals(this.audioCapabilities) == true) goto L9;
        this.audioCapabilities = r2;
        this.listener.onAudioCapabilitiesChanged(r2);
        return;
    }

    public void overrideCapabilities(AudioCapabilities r1) {
        onNewAudioCapabilities(r1);
    }

    public AudioCapabilities register() {
        if (this.registered == true) goto L5;
        this.registered = true;
        ExternalSurroundSoundSettingObserver r0 = this.externalSurroundSoundSettingObserver;
        if (r0 == null) goto L9;
        r0.register();
    L9:
        AudioDeviceCallbackV23 r02 = this.audioDeviceCallback;
        if (r02 == null) goto L12;
        Api23.registerAudioDeviceCallback(this.context, r02, this.handler);
    L12:
        Intent r03 = this.context.registerReceiver(this.hdmiAudioPlugBroadcastReceiver, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"), null, this.handler);
        AudioCapabilities r04 = AudioCapabilities.getCapabilitiesInternal(this.context, r03, this.audioAttributes, this.routedDevice);
        this.audioCapabilities = r04;
        return r04;
    L5:
        return (AudioCapabilities) Assertions.checkNotNull(this.audioCapabilities);
    }

    public void setAudioAttributes(AudioAttributes r3) {
        this.audioAttributes = r3;
        onNewAudioCapabilities(AudioCapabilities.getCapabilitiesInternal(this.context, r3, this.routedDevice));
    }

    public void setRoutedDevice(AudioDeviceInfo r3) {
        AudioDeviceInfoApi23 r0 = this.routedDevice;
        AudioDeviceInfoApi23 r1 = null;
        if (r0 != null) goto L5;
        AudioDeviceInfo r02 = null;
    L7:
        if (Objects.equals(r3, r02) == false) goto L9;
        return;
    L9:
        if (r3 == null) goto L11;
        r1 = new AudioDeviceInfoApi23(r3);
    L11:
        this.routedDevice = r1;
        onNewAudioCapabilities(AudioCapabilities.getCapabilitiesInternal(this.context, this.audioAttributes, r1));
        return;
    L5:
        r02 = r0.audioDeviceInfo;
        goto L7
    }

    public void unregister() {
        if (this.registered == true) goto L5;
        return;
    L5:
        this.audioCapabilities = null;
        AudioDeviceCallbackV23 r0 = this.audioDeviceCallback;
        if (r0 == null) goto L8;
        Api23.unregisterAudioDeviceCallback(this.context, r0);
    L8:
        this.context.unregisterReceiver(this.hdmiAudioPlugBroadcastReceiver);
        ExternalSurroundSoundSettingObserver r02 = this.externalSurroundSoundSettingObserver;
        if (r02 == null) goto L11;
        r02.unregister();
    L11:
        this.registered = false;
    }
}
