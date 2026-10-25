package org.telegram.messenger.pip;

import android.app.Activity;
import android.app.PictureInPictureParams;
import android.app.PictureInPictureUiState;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Log;
import android.view.Choreographer;
import androidx.core.math.MathUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.pip.activity.IPipActivity;
import org.telegram.messenger.pip.activity.IPipActivityActionListener;
import org.telegram.messenger.pip.activity.IPipActivityAnimationListener;
import org.telegram.messenger.pip.activity.IPipActivityHandler;
import org.telegram.messenger.pip.activity.IPipActivityListener;
import org.telegram.messenger.pip.utils.PipActions;
import org.telegram.messenger.pip.utils.PipDuration;
import org.telegram.messenger.pip.utils.PipUtils;

/* loaded from: classes.dex */
class PipActivityHandler implements IPipActivityHandler {
    private final HashMap<String, ArrayList<IPipActivityActionListener>> actionListeners;
    private final Activity activity;
    private final ArrayList<IPipActivityAnimationListener> animationListeners;
    private final BroadcastReceiver broadcastReceiver;
    private final Choreographer.FrameCallback callback;
    private final Choreographer choreographer;
    private final PipDuration durationEnter;
    private final PipDuration durationLeave;
    private boolean hasFrameListener;
    private boolean isActivityStarted;
    private boolean isInPictureInPictureModeInternal;
    private boolean isInPictureInPictureStash;
    private float lastProgress;
    private final ArrayList<IPipActivityListener> listeners;
    private PictureInPictureParams pictureInPictureParams;

    /* renamed from: org.telegram.messenger.pip.PipActivityHandler$1, reason: invalid class name */
    public class AnonymousClass1 extends BroadcastReceiver {
        final /* synthetic */ PipActivityHandler this$0;

        public AnonymousClass1(PipActivityHandler r1) {
            this.this$0 = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r1, Intent r2) {
            if (PipActions.isPipIntent(r2) == false) goto L6;
            String r12 = PipActions.getSourceId(r2);
            int r22 = PipActions.getActionId(r2);
            PipActivityHandler.m5151$$Nest$mdispatchAction(this.this$0, r12, r22);
            return;
        }
    }

    /* renamed from: $r8$lambda$UF6ILmFFeS-yNWBCh5GIuoFrHJw, reason: not valid java name */
    public static /* synthetic */ void m5150$r8$lambda$UF6ILmFFeSyNWBCh5GIuoFrHJw(PipActivityHandler r0, long r1) {
        r0.onFrameInternal(r1);
    }

    /* renamed from: -$$Nest$mdispatchAction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5151$$Nest$mdispatchAction(PipActivityHandler r0, String r1, int r2) {
        r0.dispatchAction(r1, r2);
    }

    public PipActivityHandler(Activity r3) {
        this.listeners = new ArrayList();
        this.animationListeners = new ArrayList();
        this.actionListeners = new HashMap();
        this.lastProgress = -1.0f;
        this.durationEnter = new PipDuration("enter");
        this.durationLeave = new PipDuration("leave");
        this.choreographer = Choreographer.getInstance();
        this.callback = new PipActivityHandler$$ExternalSyntheticLambda0(this);
        this.broadcastReceiver = new AnonymousClass1(this);
        this.activity = r3;
    }

    private void dispatchAction(String r1, int r2) {
        ArrayList<IPipActivityActionListener> r0 = this.actionListeners.get(r1);
        if (r0 == null) goto L10;
        Iterator<IPipActivityActionListener> r02 = r0.iterator();
        if (r02.hasNext() == true) goto L8;
        return;
    L8:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r02.next());
        throw null;
    }

    private void dispatchCompleteEnterPip() {
        dispatchEnterAnimationEnd();
        ArrayList<IPipActivityListener> r3 = this.listeners;
        int r0 = r3.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        IPipActivityListener r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.onCompleteEnterToPip();
        goto L3
    }

    private void dispatchCompleteExitPip(boolean r4) {
        dispatchLeaveAnimationEnd();
        int r0 = 0;
        this.isInPictureInPictureModeInternal = false;
        ArrayList<IPipActivityListener> r3 = this.listeners;
        int r1 = r3.size();
    L3:
        if (r0 >= r1) goto L5;
        IPipActivityListener r2 = r3.get(r0);
        r0 = r0 + 1;
        r2.onCompleteExitFromPip(r4);
        goto L3
    }

    private void dispatchEnterAnimationEnd() {
        dispatchTransitionAnimationProgress(1.0f);
        long r0 = this.durationEnter.end();
        ArrayList<IPipActivityAnimationListener> r2 = this.animationListeners;
        int r3 = r2.size();
        int r4 = 0;
    L3:
        if (r4 >= r3) goto L5;
        IPipActivityAnimationListener r5 = r2.get(r4);
        r4 = r4 + 1;
        r5.onEnterAnimationEnd(r0);
        goto L3
    L5:
        unsubscribeFromFrameUpdates();
    }

    private void dispatchEnterAnimationStart() {
        long r0 = this.durationEnter.estimated();
        ArrayList<IPipActivityAnimationListener> r2 = this.animationListeners;
        int r3 = r2.size();
        int r4 = 0;
    L3:
        if (r4 >= r3) goto L5;
        IPipActivityAnimationListener r5 = r2.get(r4);
        r4 = r4 + 1;
        r5.onEnterAnimationStart(r0);
        goto L3
    L5:
        dispatchTransitionAnimationProgress(0.0f);
        this.durationEnter.start();
        subscribeToFrameUpdates();
    }

    private void dispatchLeaveAnimationEnd() {
        dispatchTransitionAnimationProgress(0.0f);
        long r0 = this.durationLeave.end();
        ArrayList<IPipActivityAnimationListener> r2 = this.animationListeners;
        int r3 = r2.size();
        int r4 = 0;
    L3:
        if (r4 >= r3) goto L5;
        IPipActivityAnimationListener r5 = r2.get(r4);
        r4 = r4 + 1;
        r5.onLeaveAnimationEnd(r0);
        goto L3
    L5:
        unsubscribeFromFrameUpdates();
    }

    private void dispatchLeaveAnimationStart() {
        long r0 = this.durationLeave.estimated();
        ArrayList<IPipActivityAnimationListener> r2 = this.animationListeners;
        int r3 = r2.size();
        int r4 = 0;
    L3:
        if (r4 >= r3) goto L5;
        IPipActivityAnimationListener r5 = r2.get(r4);
        r4 = r4 + 1;
        r5.onLeaveAnimationStart(r0);
        goto L3
    L5:
        dispatchTransitionAnimationProgress(1.0f);
        this.durationLeave.start();
        subscribeToFrameUpdates();
    }

    private void dispatchStartEnterPip() {
        this.isInPictureInPictureModeInternal = true;
        int r0 = 0;
        this.isInPictureInPictureStash = false;
        ArrayList<IPipActivityListener> r1 = this.listeners;
        int r2 = r1.size();
    L3:
        if (r0 >= r2) goto L5;
        IPipActivityListener r3 = r1.get(r0);
        r0 = r0 + 1;
        r3.onStartEnterToPip();
        goto L3
    L5:
        dispatchEnterAnimationStart();
    }

    private void dispatchStartExitPip(boolean r5) {
        ArrayList<IPipActivityListener> r0 = this.listeners;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        IPipActivityListener r3 = r0.get(r2);
        r2 = r2 + 1;
        r3.onStartExitFromPip(r5);
        goto L3
    L5:
        dispatchLeaveAnimationStart();
    }

    private void dispatchStashEndPip() {
        ArrayList<IPipActivityListener> r3 = this.listeners;
        int r0 = r3.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        IPipActivityListener r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.onPipStashEnd();
        goto L3
    }

    private void dispatchStashStartPip() {
        ArrayList<IPipActivityListener> r3 = this.listeners;
        int r0 = r3.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        IPipActivityListener r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.onPipStashStart();
        goto L3
    }

    private void dispatchTransitionAnimationProgress(float r4) {
        if (r4 == this.lastProgress) goto L8;
        this.lastProgress = r4;
        ArrayList<IPipActivityAnimationListener> r3 = this.animationListeners;
        int r0 = r3.size();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L10;
        IPipActivityAnimationListener r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.onTransitionAnimationProgress(r4);
        goto L6
    L10:
        return;
    }

    private boolean hasContentForPictureInPictureMode() {
        ComponentCallbacks2 r1 = this.activity;
        if ((r1 instanceof IPipActivity) == true) goto L5;
        return false;
    L5:
        return ((IPipActivity) r1).getPipController().hasContentForPictureInPictureMode();
    }

    private void manualEnterPictureInPictureModeInternal() {
        if (this.isInPictureInPictureModeInternal == false) goto L6;
        return;
    L6:
        if (PipUtils.useAutoEnterInPictureInPictureMode() == false) goto L9;
        return;
    L9:
        if (Build.VERSION.SDK_INT >= 26) goto L11;
        return;
    L11:
        if (this.pictureInPictureParams != null) goto L13;
        return;
    L13:
        if (hasContentForPictureInPictureMode() == false) goto L18;
        dispatchStartEnterPip();
        this.activity.enterPictureInPictureMode(this.pictureInPictureParams);
        return;
    }

    private void onFrameInternal(long r3) {
        if (this.hasFrameListener == true) goto L5;
        return;
    L5:
        ArrayList<IPipActivityAnimationListener> r32 = this.animationListeners;
        int r4 = r32.size();
        int r0 = 0;
    L6:
        if (r0 >= r4) goto L9;
        IPipActivityAnimationListener r1 = r32.get(r0);
        r0 = r0 + 1;
        r1.onTransitionAnimationFrame();
        goto L6
    L9:
        if (this.durationEnter.isStarted() == false) goto L12;
        dispatchTransitionAnimationProgress(MathUtils.clamp(this.durationEnter.progress() / 0.95f, 0.0f, 1.0f));
    L14:
        this.choreographer.postFrameCallback(this.callback);
        return;
    L12:
        if (this.durationLeave.isStarted() == false) goto L14;
        dispatchTransitionAnimationProgress(MathUtils.clamp(1.0f - (this.durationLeave.progress() / 0.95f), 0.0f, 1.0f));
        goto L14
    }

    private void subscribeToFrameUpdates() {
        if (this.hasFrameListener == false) goto L5;
        return;
    L5:
        this.hasFrameListener = true;
        this.choreographer.postFrameCallback(this.callback);
    }

    private void unsubscribeFromFrameUpdates() {
        if (this.hasFrameListener == true) goto L5;
        return;
    L5:
        this.hasFrameListener = false;
        this.choreographer.removeFrameCallback(this.callback);
    }

    public void addActionListener(String r2, IPipActivityActionListener r3) {
        ArrayList<IPipActivityActionListener> r0 = this.actionListeners.get(r2);
        if (r0 != null) goto L5;
        r0 = new ArrayList();
        this.actionListeners.put(r2, r0);
    L5:
        r0.add(r3);
    }

    public void addAnimationListener(IPipActivityAnimationListener r1) {
        this.animationListeners.add(r1);
    }

    public void addPipListener(IPipActivityListener r1) {
        this.listeners.add(r1);
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityHandler
    public void onConfigurationChanged(Configuration r1) {
        Log.i("PIP_DEBUG", "[Activity] onConfigurationChanged");
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityHandler
    public void onPause() {
        Log.i("PIP_DEBUG", "[Activity] onPause");
        if (AndroidUtilities.isInPictureInPictureMode(this.activity) == true) goto L5;
        return;
    L5:
        if (hasContentForPictureInPictureMode() == true) goto L7;
        return;
    L7:
        if (PipUtils.useAutoEnterInPictureInPictureMode() == false) goto L12;
        dispatchStartEnterPip();
        return;
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityHandler
    public void onPictureInPictureModeChanged(boolean r2, Configuration r3) {
        Log.i("PIP_DEBUG", "[Activity] onPictureInPictureModeChanged " + r2);
        if (this.isInPictureInPictureModeInternal == false) goto L13;
        if (r2 == false) goto L8;
        dispatchCompleteEnterPip();
        return;
    L8:
        if (this.isActivityStarted == false) goto L11;
        dispatchStartExitPip(false);
        return;
    L11:
        dispatchCompleteExitPip(true);
        return;
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityHandler
    public void onPictureInPictureRequested() {
        Log.i("PIP_DEBUG", "[Activity] onPictureInPictureRequested");
        manualEnterPictureInPictureModeInternal();
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityHandler
    public void onPictureInPictureUiStateChanged(PictureInPictureUiState r5) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 >= 31) goto L5;
        return;
    L5:
        if (r0 < 35) goto L11;
        Log.i("PIP_DEBUG", "[Activity] onPictureInPictureUiStateChanged " + r5.isStashed() + " " + r5.isTransitioningToPip());
        if (r5.isTransitioningToPip() == true) goto L9;
    L12:
        boolean r52 = r5.isStashed();
        if (this.isInPictureInPictureStash == r52) goto L21;
        this.isInPictureInPictureStash = r52;
        if (r52 == false) goto L18;
        dispatchStashStartPip();
        return;
    L18:
        dispatchStashEndPip();
        return;
    L21:
        return;
    L9:
        if (hasContentForPictureInPictureMode() == false) goto L12;
        dispatchStartEnterPip();
        goto L12
    L11:
        Log.i("PIP_DEBUG", "[Activity] onPictureInPictureUiStateChanged " + r5.isStashed());
        goto L12
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityHandler
    public void onResume() {
        Log.i("PIP_DEBUG", "[Activity] onResume");
        if (this.isInPictureInPictureModeInternal == false) goto L6;
        dispatchCompleteExitPip(false);
        return;
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityHandler
    public void onStart() {
        Log.i("PIP_DEBUG", "[Activity] onStart");
        this.isActivityStarted = true;
        IntentFilter r0 = new IntentFilter("PIP_CUSTOM_EVENT");
        int r1 = Build.VERSION.SDK_INT;
        Activity r2 = this.activity;
        if (r1 < 33) goto L6;
        r2.registerReceiver(this.broadcastReceiver, r0, 4);
        return;
    L6:
        r2.registerReceiver(this.broadcastReceiver, r0);
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityHandler
    public void onStop() {
        Log.i("PIP_DEBUG", "[Activity] onStop");
        this.isActivityStarted = false;
        if (this.isInPictureInPictureModeInternal == false) goto L5;
        dispatchStartExitPip(true);
    L5:
        this.activity.unregisterReceiver(this.broadcastReceiver);
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityHandler
    public void onUserLeaveHint() {
        Log.i("PIP_DEBUG", "[Activity] onUserLeaveHint");
        manualEnterPictureInPictureModeInternal();
    }

    public void removeActionListener(String r2, IPipActivityActionListener r3) {
        ArrayList<IPipActivityActionListener> r0 = this.actionListeners.get(r2);
        if (r0 == null) goto L10;
        r0.remove(r3);
        if (r0.isEmpty() == false) goto L9;
        this.actionListeners.remove(r2);
        return;
    L9:
        return;
    }

    public void removeAnimationListener(IPipActivityAnimationListener r1) {
        this.animationListeners.remove(r1);
    }

    public void removePipListener(IPipActivityListener r1) {
        this.listeners.remove(r1);
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityHandler
    public void setPictureInPictureParams(PictureInPictureParams r3) {
        Log.i("PIP_DEBUG", "[Activity] setPictureInPictureParams");
        this.pictureInPictureParams = r3;
    }
}
