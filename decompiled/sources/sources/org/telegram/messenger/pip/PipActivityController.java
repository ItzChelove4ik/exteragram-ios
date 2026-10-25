package org.telegram.messenger.pip;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import androidx.media3.common.Player;
import androidx.media3.session.MediaSession;
import java.util.HashMap;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.pip.activity.IPipActivityActionListener;
import org.telegram.messenger.pip.activity.IPipActivityAnimationListener;
import org.telegram.messenger.pip.activity.IPipActivityHandler;
import org.telegram.messenger.pip.activity.IPipActivityListener;
import org.telegram.messenger.pip.utils.PipUtils;

/* loaded from: classes.dex */
public class PipActivityController {
    public final Activity activity;
    private final PipActivityHandler handler;
    private PipSource maxPrioritySource;
    private MediaSession mediaSession;
    private PipActivityContentLayout pipContentView;
    private final HashMap<String, PipSource> sources;

    /* renamed from: org.telegram.messenger.pip.PipActivityController$1, reason: invalid class name */
    public class AnonymousClass1 implements IPipActivityListener {
        final /* synthetic */ PipActivityController this$0;

        public AnonymousClass1(PipActivityController r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.messenger.pip.activity.IPipActivityListener
        public void onCompleteEnterToPip() {
            Log.d("PIP_DEBUG", "onCompleteEnterToPip");
        }

        @Override // org.telegram.messenger.pip.activity.IPipActivityListener
        public void onCompleteExitFromPip(boolean r2) {
            Log.d("PIP_DEBUG", "onCompleteExitFromPip: byActivityStop=" + r2);
        }

        @Override // org.telegram.messenger.pip.activity.IPipActivityListener
        public void onPipStashEnd() {
            Log.d("PIP_DEBUG", "onPipStashStop");
        }

        @Override // org.telegram.messenger.pip.activity.IPipActivityListener
        public void onPipStashStart() {
            Log.d("PIP_DEBUG", "onPipStashStart");
        }

        @Override // org.telegram.messenger.pip.activity.IPipActivityListener
        public void onStartEnterToPip() {
            Log.d("PIP_DEBUG", "onStartEnterToPip");
        }

        @Override // org.telegram.messenger.pip.activity.IPipActivityListener
        public void onStartExitFromPip(boolean r2) {
            Log.d("PIP_DEBUG", "onStartExitFromPip: byActivityStop=" + r2);
        }
    }

    /* renamed from: org.telegram.messenger.pip.PipActivityController$2, reason: invalid class name */
    public class AnonymousClass2 implements IPipActivityAnimationListener {
        final /* synthetic */ PipActivityController this$0;

        public AnonymousClass2(PipActivityController r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.messenger.pip.activity.IPipActivityAnimationListener
        public void onEnterAnimationEnd(long r2) {
            Log.d("PIP_DEBUG", "onEnterAnimationEnd: duration=" + r2);
        }

        @Override // org.telegram.messenger.pip.activity.IPipActivityAnimationListener
        public void onEnterAnimationStart(long r2) {
            Log.d("PIP_DEBUG", "onEnterAnimationStart: estimatedDuration=" + r2);
        }

        @Override // org.telegram.messenger.pip.activity.IPipActivityAnimationListener
        public void onLeaveAnimationEnd(long r2) {
            Log.d("PIP_DEBUG", "onLeaveAnimationEnd: duration=" + r2);
        }

        @Override // org.telegram.messenger.pip.activity.IPipActivityAnimationListener
        public void onLeaveAnimationStart(long r2) {
            Log.d("PIP_DEBUG", "onLeaveAnimationStart: estimatedDuration=" + r2);
        }

        @Override // org.telegram.messenger.pip.activity.IPipActivityAnimationListener
        public void onTransitionAnimationFrame() {
            Log.d("PIP_DEBUG", "onTransitionAnimationFrame");
        }

        @Override // org.telegram.messenger.pip.activity.IPipActivityAnimationListener
        public void onTransitionAnimationProgress(float r2) {
            Log.d("PIP_DEBUG", "onTransitionAnimationProgress: estimatedProgress=" + r2);
        }
    }

    public PipActivityController(Activity r2) {
        this.sources = new HashMap();
        this.activity = r2;
        PipActivityHandler r0 = new PipActivityHandler(r2);
        this.handler = r0;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L6;
        r0.addPipListener(new AnonymousClass1(this));
        r0.addAnimationListener(new AnonymousClass2(this));
        return;
    }

    private void onMaxPrioritySourceChanged(PipSource r6, PipSource r7) {
        StringBuilder r0 = new StringBuilder("onMaxPrioritySourceChanged ");
        if (r7 == null) goto L5;
        String r2 = r7.tag;
    L6:
        r0.append(r2);
        Log.i("PIP_DEBUG", r0.toString());
        PipUtils.applyPictureInPictureParams(this.activity, r7);
        boolean r02 = true;
        if (r6 != null) goto L9;
    L11:
        boolean r3 = false;
    L12:
        if (r7 != null) goto L14;
    L16:
        r02 = false;
    L17:
        if (r3 == r02) goto L23;
        MediaSession r03 = this.mediaSession;
        if (r03 == null) goto L21;
        r03.release();
        this.mediaSession = null;
    L21:
        if (r7 == null) goto L23;
        this.mediaSession = new MediaSession.Builder(this.activity, r7.player).setId("pip-media-session").build();
    L23:
        if (r6 == null) goto L25;
        r6.state2.onLoseMaxPriority();
    L25:
        if (r7 == null) goto L32;
        MediaSession r62 = this.mediaSession;
        if (r62 == null) goto L31;
        Player r63 = r62.getPlayer();
        Player r04 = r7.player;
        if (r63 == r04) goto L31;
        this.mediaSession.setPlayer(r04);
    L31:
        this.pipContentView.bringToFront();
        r7.state2.onReceiveMaxPriority();
    L36:
        this.pipContentView.invalidate();
        return;
    L32:
        if (r6 == null) goto L36;
        if (AndroidUtilities.isInPictureInPictureMode(this.activity) == false) goto L36;
        this.activity.moveTaskToBack(false);
        goto L36
    L14:
        if (r7.needMediaSession == false) goto L16;
    L9:
        if (r6.needMediaSession == false) goto L11;
        r3 = true;
        goto L12
    L5:
        r2 = null;
        goto L6
    }

    private void updateSources() {
        PipSource r0 = this.maxPrioritySource;
        Iterator<PipSource> r1 = this.sources.values().iterator();
        PipSource r2 = null;
    L4:
        if (r1.hasNext() == false) goto L15;
        PipSource r3 = r1.next();
        if (r3.isAvailable() == true) goto L10;
        if (r3.state2.isAttachedToPip() == false) goto L4;
    L10:
        if (r2 == null) goto L14;
        if (r3.priority <= r2.priority) goto L4;
    L14:
        r2 = r3;
        goto L4
    L15:
        if (r0 == r2) goto L27;
        this.maxPrioritySource = r2;
        onMaxPrioritySourceChanged(r0, r2);
        return;
    }

    public void addActionListener(String r1, IPipActivityActionListener r2) {
        this.handler.addActionListener(r1, r2);
    }

    public void addAnimationListener(IPipActivityAnimationListener r1) {
        this.handler.addAnimationListener(r1);
    }

    public void addPipListener(IPipActivityListener r1) {
        this.handler.addPipListener(r1);
    }

    public void dispatchSourceAvailabilityChanged(PipSource r1) {
        updateSources();
        this.pipContentView.invalidate();
    }

    public void dispatchSourceParamsChanged(PipSource r2) {
        if (this.maxPrioritySource != r2) goto L9;
        PipUtils.applyPictureInPictureParams(this.activity, r2);
        MediaSession r0 = this.mediaSession;
        if (r0 == null) goto L9;
        Player r02 = r0.getPlayer();
        Player r22 = r2.player;
        if (r02 == r22) goto L9;
        this.mediaSession.setPlayer(r22);
    L9:
        this.pipContentView.invalidate();
    }

    public void dispatchSourceRegister(PipSource r3) {
        this.sources.put(r3.tag, r3);
        updateSources();
    }

    public void dispatchSourceUnregister(PipSource r2) {
        if (this.sources.remove(r2.tag) == null) goto L6;
        updateSources();
        return;
    }

    public IPipActivityHandler getHandler() {
        return this.handler;
    }

    public ViewGroup getPipContentView() {
        if (this.pipContentView != null) goto L6;
        this.pipContentView = new PipActivityContentLayout(this.activity);
    L6:
        return this.pipContentView;
    }

    public boolean hasContentForPictureInPictureMode() {
        if (this.maxPrioritySource == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void removeActionListener(String r1, IPipActivityActionListener r2) {
        this.handler.removeActionListener(r1, r2);
    }

    public void removeAnimationListener(IPipActivityAnimationListener r1) {
        this.handler.removeAnimationListener(r1);
    }

    public void removePipListener(IPipActivityListener r1) {
        this.handler.removePipListener(r1);
    }
}
