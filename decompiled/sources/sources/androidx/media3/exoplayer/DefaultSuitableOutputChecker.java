package androidx.media3.exoplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.media.MediaRouter2$ControllerCallback;
import android.media.MediaRouter2$RouteCallback;
import android.media.RouteDiscoveryPreference;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.SuitableOutputChecker;
import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import java.util.Objects;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes4.dex */
final class DefaultSuitableOutputChecker implements SuitableOutputChecker {
    private final SuitableOutputChecker impl;

    /* renamed from: androidx.media3.exoplayer.DefaultSuitableOutputChecker$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class ImplApi23 implements SuitableOutputChecker {
        private AudioDeviceCallback audioDeviceCallback;
        private AudioManager audioManager;
        private BackgroundThreadStateHandler<Boolean> isSuitableForPlaybackState;

        /* renamed from: androidx.media3.exoplayer.DefaultSuitableOutputChecker$ImplApi23$1, reason: invalid class name */
        public class AnonymousClass1 extends AudioDeviceCallback {
            final /* synthetic */ ImplApi23 this$0;

            public AnonymousClass1(ImplApi23 r1) {
                this.this$0 = r1;
            }

            @Override // android.media.AudioDeviceCallback
            public void onAudioDevicesAdded(AudioDeviceInfo[] r1) {
                ImplApi23.access$600(this.this$0).setStateInBackground(Boolean.valueOf(ImplApi23.access$500(this.this$0)));
            }

            @Override // android.media.AudioDeviceCallback
            public void onAudioDevicesRemoved(AudioDeviceInfo[] r1) {
                ImplApi23.access$600(this.this$0).setStateInBackground(Boolean.valueOf(ImplApi23.access$500(this.this$0)));
            }
        }

        public static /* synthetic */ void $r8$lambda$K0yPnH7TYWkiAnvFwq9ZTUz0tso(SuitableOutputChecker.Callback r0, Boolean r1, Boolean r2) {
            r0.onSelectedOutputSuitabilityChanged(r2.booleanValue());
        }

        public static /* synthetic */ void $r8$lambda$nzgMNMGIdDD2279gYiEKPY49zSc(ImplApi23 r3, Context r4) {
            Assertions.checkNotNull(r3.isSuitableForPlaybackState);
            if (Util.isWear(r4) == false) goto L10;
            AudioManager r42 = (AudioManager) r4.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
            if (r42 != null) goto L8;
            return;
        L8:
            r3.audioManager = r42;
            AnonymousClass1 r0 = new AnonymousClass1(r3);
            r3.audioDeviceCallback = r0;
            r42.registerAudioDeviceCallback(r0, new Handler((Looper) Assertions.checkNotNull(Looper.myLooper())));
            r3.isSuitableForPlaybackState.setStateInBackground(Boolean.valueOf(r3.hasSupportedAudioOutput()));
            return;
        }

        public static /* synthetic */ void $r8$lambda$sy28lDPudbVyaw948hTCSilVIxg(ImplApi23 r1) {
            AudioManager r0 = r1.audioManager;
            if (r0 == null) goto L6;
            r0.unregisterAudioDeviceCallback((AudioDeviceCallback) Assertions.checkNotNull(r1.audioDeviceCallback));
            return;
        }

        private ImplApi23() {
        }

        public /* synthetic */ ImplApi23(AnonymousClass1 r1) {
            this();
        }

        public static /* synthetic */ boolean access$500(ImplApi23 r0) {
            return r0.hasSupportedAudioOutput();
        }

        public static /* synthetic */ BackgroundThreadStateHandler access$600(ImplApi23 r0) {
            return r0.isSuitableForPlaybackState;
        }

        private boolean hasSupportedAudioOutput() {
            AudioDeviceInfo[] r9 = ((AudioManager) Assertions.checkStateNotNull(this.audioManager)).getDevices(2);
            int r0 = r9.length;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L41;
            AudioDeviceInfo r3 = r9[r2];
            if (r3.getType() == 8) goto L40;
            if (r3.getType() == 5) goto L40;
            if (r3.getType() == 6) goto L40;
            if (r3.getType() == 11) goto L40;
            if (r3.getType() == 4) goto L40;
            if (r3.getType() == 3) goto L40;
            int r4 = Build.VERSION.SDK_INT;
            if (r4 < 26) goto L23;
            if (r3.getType() != 22) goto L23;
            return true;
        L23:
            if (r4 < 28) goto L28;
            if (r3.getType() != 23) goto L28;
            return true;
        L28:
            if (r4 < 31) goto L35;
            if (r3.getType() == 26) goto L33;
            if (r3.getType() != 27) goto L35;
        L33:
            return true;
        L35:
            if (r4 < 33) goto L39;
            if (r3.getType() != 30) goto L39;
            return true;
        L39:
            r2 = r2 + 1;
        L40:
            return true;
        L41:
            return false;
        }

        @Override // androidx.media3.exoplayer.SuitableOutputChecker
        public void disable() {
            ((BackgroundThreadStateHandler) Assertions.checkNotNull(this.isSuitableForPlaybackState)).runInBackground(new DefaultSuitableOutputChecker$ImplApi23$$ExternalSyntheticLambda0(this));
        }

        @Override // androidx.media3.exoplayer.SuitableOutputChecker
        public void enable(SuitableOutputChecker.Callback r7, Context r8, Looper r9, Looper r10, Clock r11) {
            BackgroundThreadStateHandler<Boolean> r0 = new BackgroundThreadStateHandler(Boolean.TRUE, r10, r9, r11, new DefaultSuitableOutputChecker$ImplApi23$$ExternalSyntheticLambda1(r7));
            this.isSuitableForPlaybackState = r0;
            r0.runInBackground(new DefaultSuitableOutputChecker$ImplApi23$$ExternalSyntheticLambda2(this, r8));
        }

        @Override // androidx.media3.exoplayer.SuitableOutputChecker
        public boolean isSelectedOutputSuitableForPlayback() {
            BackgroundThreadStateHandler<Boolean> r0 = this.isSuitableForPlaybackState;
            if (r0 != null) goto L7;
            return true;
        L7:
            return r0.get().booleanValue();
        }
    }

    public static final class ImplApi35 implements SuitableOutputChecker {
        private static final RouteDiscoveryPreference EMPTY_DISCOVERY_PREFERENCE = null;
        private MediaRouter2$ControllerCallback controllerCallback;
        private BackgroundThreadStateHandler<Boolean> isSuitableForPlaybackState;
        private MediaRouter2$RouteCallback routeCallback;
        private MediaRouter2 router;

        /* renamed from: androidx.media3.exoplayer.DefaultSuitableOutputChecker$ImplApi35$1, reason: invalid class name */
        public class AnonymousClass1 extends MediaRouter2$RouteCallback {
            final /* synthetic */ ImplApi35 this$0;

            public AnonymousClass1(ImplApi35 r1) {
                this.this$0 = r1;
            }
        }

        /* renamed from: androidx.media3.exoplayer.DefaultSuitableOutputChecker$ImplApi35$2, reason: invalid class name */
        public class AnonymousClass2 extends MediaRouter2$ControllerCallback {
            final /* synthetic */ ImplApi35 this$0;

            public AnonymousClass2(ImplApi35 r1) {
                this.this$0 = r1;
            }

            public void onControllerUpdated(MediaRouter2.RoutingController r1) {
                ImplApi35.access$400(this.this$0).setStateInBackground(Boolean.valueOf(ImplApi35.access$300(ImplApi35.access$200(this.this$0))));
            }
        }

        public static /* synthetic */ void $r8$lambda$C7hvk343s8kpImofY5V4I7EgdcI(ImplApi35 r2) {
            ((MediaRouter2) Assertions.checkNotNull(r2.router)).unregisterControllerCallback((MediaRouter2$ControllerCallback) Assertions.checkNotNull(r2.controllerCallback));
            r2.controllerCallback = null;
            r2.router.unregisterRouteCallback((MediaRouter2$RouteCallback) Assertions.checkNotNull(r2.routeCallback));
        }

        /* renamed from: $r8$lambda$KnhfDAEV-pnnPnhMMTY9Ee046Vo, reason: not valid java name */
        public static /* synthetic */ void m765$r8$lambda$KnhfDAEVpnnPnhMMTY9Ee046Vo(SuitableOutputChecker.Callback r0, Boolean r1, Boolean r2) {
            r0.onSelectedOutputSuitabilityChanged(r2.booleanValue());
        }

        public static /* synthetic */ void $r8$lambda$wzOoCunQO4W9nW53PwcC5hc5oik(ImplApi35 r3, Context r4) {
            Assertions.checkNotNull(r3.isSuitableForPlaybackState);
            r3.router = MediaRouter2.getInstance(r4);
            r3.routeCallback = new AnonymousClass1(r3);
            BackgroundThreadStateHandler<Boolean> r42 = r3.isSuitableForPlaybackState;
            Objects.requireNonNull(r42);
            DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticLambda7 r0 = new DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticLambda7(r42);
            r3.router.registerRouteCallback(r0, r3.routeCallback, EMPTY_DISCOVERY_PREFERENCE);
            AnonymousClass2 r43 = new AnonymousClass2(r3);
            r3.controllerCallback = r43;
            r3.router.registerControllerCallback(r0, r43);
            r3.isSuitableForPlaybackState.setStateInBackground(Boolean.valueOf(isSelectedOutputSuitableForPlayback(r3.router)));
        }

        static {
            DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline1.m();
            EMPTY_DISCOVERY_PREFERENCE = DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline0.m(ImmutableList.of(), false).build();
        }

        private ImplApi35() {
        }

        public /* synthetic */ ImplApi35(AnonymousClass1 r1) {
            this();
        }

        public static /* synthetic */ MediaRouter2 access$200(ImplApi35 r0) {
            return r0.router;
        }

        public static /* synthetic */ boolean access$300(MediaRouter2 r0) {
            return isSelectedOutputSuitableForPlayback(r0);
        }

        public static /* synthetic */ BackgroundThreadStateHandler access$400(ImplApi35 r0) {
            return r0.isSuitableForPlaybackState;
        }

        private static boolean isRouteSuitableForMediaPlayback(MediaRoute2Info r2, int r3, boolean r4) {
            int r22 = r2.getSuitabilityStatus();
            if (r22 != 1) goto L10;
            if (r3 != 1) goto L6;
        L7:
            if (r4 == false) goto L9;
            return true;
        L9:
            return false;
        L6:
            if (r3 != 2) goto L9;
        L10:
            if (r22 != 0) goto L12;
            return true;
        L12:
            return false;
        }

        private static boolean isSelectedOutputSuitableForPlayback(MediaRouter2 r3) {
            int r0 = DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline2.m(Assertions.checkNotNull(r3)).getSystemController().getRoutingSessionInfo().getTransferReason();
            boolean r1 = r3.getSystemController().wasTransferInitiatedBySelf();
            Iterator<MediaRoute2Info> r32 = r3.getSystemController().getSelectedRoutes().iterator();
        L4:
            if (r32.hasNext() == false) goto L9;
            if (isRouteSuitableForMediaPlayback(DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticApiModelOutline3.m(r32.next()), r0, r1) == false) goto L4;
            return true;
        L9:
            return false;
        }

        @Override // androidx.media3.exoplayer.SuitableOutputChecker
        public void disable() {
            ((BackgroundThreadStateHandler) Assertions.checkStateNotNull(this.isSuitableForPlaybackState)).runInBackground(new DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticLambda4(this));
        }

        @Override // androidx.media3.exoplayer.SuitableOutputChecker
        @SuppressLint({"ThreadSafe"})
        public void enable(SuitableOutputChecker.Callback r7, Context r8, Looper r9, Looper r10, Clock r11) {
            BackgroundThreadStateHandler<Boolean> r0 = new BackgroundThreadStateHandler(Boolean.TRUE, r10, r9, r11, new DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticLambda5(r7));
            this.isSuitableForPlaybackState = r0;
            r0.runInBackground(new DefaultSuitableOutputChecker$ImplApi35$$ExternalSyntheticLambda6(this, r8));
        }

        @Override // androidx.media3.exoplayer.SuitableOutputChecker
        public boolean isSelectedOutputSuitableForPlayback() {
            BackgroundThreadStateHandler<Boolean> r0 = this.isSuitableForPlaybackState;
            if (r0 != null) goto L7;
            return true;
        L7:
            return r0.get().booleanValue();
        }
    }

    public DefaultSuitableOutputChecker() {
        if (Build.VERSION.SDK_INT < 35) goto L6;
        this.impl = new ImplApi35(null);
        return;
    L6:
        this.impl = new ImplApi23(null);
    }

    @Override // androidx.media3.exoplayer.SuitableOutputChecker
    public void disable() {
        SuitableOutputChecker r0 = this.impl;
        if (r0 == null) goto L6;
        r0.disable();
        return;
    }

    @Override // androidx.media3.exoplayer.SuitableOutputChecker
    public void enable(SuitableOutputChecker.Callback r1, Context r2, Looper r3, Looper r4, Clock r5) {
        SuitableOutputChecker r0 = this.impl;
        if (r0 == null) goto L6;
        r0.enable(r1, r2, r3, r4, r5);
        return;
    }

    @Override // androidx.media3.exoplayer.SuitableOutputChecker
    public boolean isSelectedOutputSuitableForPlayback() {
        SuitableOutputChecker r0 = this.impl;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.isSelectedOutputSuitableForPlayback() == true) goto L11;
        return false;
    L11:
        return true;
    }
}
