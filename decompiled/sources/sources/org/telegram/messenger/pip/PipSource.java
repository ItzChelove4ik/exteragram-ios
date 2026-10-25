package org.telegram.messenger.pip;

import android.app.Activity;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.content.ComponentCallbacks2;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import androidx.media3.common.Player;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.pip.activity.IPipActivity;
import org.telegram.messenger.pip.activity.IPipActivityActionListener;
import org.telegram.messenger.pip.source.IPipSourceDelegate;
import org.telegram.messenger.pip.source.PipSourceHandlerState2;
import org.telegram.messenger.pip.utils.PipPositionObserver;
import org.telegram.messenger.pip.utils.PipSourceParams;
import org.telegram.messenger.pip.utils.PipUtils;
import org.webrtc.TextureViewRenderer;

/* loaded from: classes5.dex */
public class PipSource {
    private static int sourceIdCounter;
    private static final Rect tmpRect = null;
    public View contentView;
    public final PipActivityController controller;
    public final int cornerRadius;
    public final IPipSourceDelegate delegate;
    private boolean isAvailable;
    public final boolean needMediaSession;
    public final PipSourceParams params;
    private final PipPositionObserver pipPositionObserver;
    public View placeholderView;
    Player player;
    public final int priority;
    private ArrayList<RemoteAction> remoteActions;
    public final int sourceId;
    public final PipSourceHandlerState2 state2;
    public final String tag;

    public static class Builder {
        private final Activity activity;
        private View contentView;
        private int cornerRadius;
        private final IPipSourceDelegate delegate;
        private int height;
        private boolean needMediaSession;
        private View placeholderView;
        private Player player;
        private int priority;
        private String tagPrefix;
        private int width;

        /* renamed from: -$$Nest$fgetactionListener, reason: not valid java name */
        public static /* bridge */ /* synthetic */ IPipActivityActionListener m5152$$Nest$fgetactionListener(Builder r0) {
            r0.getClass();
            return null;
        }

        /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m5153$$Nest$fgetcontentView(Builder r0) {
            return r0.contentView;
        }

        /* renamed from: -$$Nest$fgetcornerRadius, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5154$$Nest$fgetcornerRadius(Builder r0) {
            return r0.cornerRadius;
        }

        /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ IPipSourceDelegate m5155$$Nest$fgetdelegate(Builder r0) {
            return r0.delegate;
        }

        /* renamed from: -$$Nest$fgetheight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5156$$Nest$fgetheight(Builder r0) {
            return r0.height;
        }

        /* renamed from: -$$Nest$fgetneedMediaSession, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m5157$$Nest$fgetneedMediaSession(Builder r0) {
            return r0.needMediaSession;
        }

        /* renamed from: -$$Nest$fgetplaceholderView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m5158$$Nest$fgetplaceholderView(Builder r0) {
            return r0.placeholderView;
        }

        /* renamed from: -$$Nest$fgetplayer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Player m5159$$Nest$fgetplayer(Builder r0) {
            return r0.player;
        }

        /* renamed from: -$$Nest$fgetpriority, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5160$$Nest$fgetpriority(Builder r0) {
            return r0.priority;
        }

        /* renamed from: -$$Nest$fgettagPrefix, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m5161$$Nest$fgettagPrefix(Builder r0) {
            return r0.tagPrefix;
        }

        /* renamed from: -$$Nest$fgetwidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5162$$Nest$fgetwidth(Builder r0) {
            return r0.width;
        }

        public Builder(Activity r2, IPipSourceDelegate r3) {
            this.priority = 0;
            this.needMediaSession = false;
            this.activity = r2;
            this.delegate = r3;
        }

        public PipSource build() {
            ComponentCallbacks2 r0 = this.activity;
            if ((r0 instanceof IPipActivity) == true) goto L5;
            return null;
        L5:
            return new PipSource(((IPipActivity) r0).getPipController(), this, null);
        }

        public Builder setContentRatio(int r1, int r2) {
            this.width = r1;
            this.height = r2;
            return this;
        }

        public Builder setContentView(View r1) {
            this.contentView = r1;
            return this;
        }

        public Builder setCornerRadius(int r1) {
            this.cornerRadius = r1;
            return this;
        }

        public Builder setNeedMediaSession(boolean r1) {
            this.needMediaSession = r1;
            return this;
        }

        public Builder setPlaceholderView(View r1) {
            this.placeholderView = r1;
            return this;
        }

        public Builder setPlayer(Player r1) {
            this.player = r1;
            return this;
        }

        public Builder setPriority(int r1) {
            this.priority = r1;
            return this;
        }

        public Builder setTagPrefix(String r1) {
            this.tagPrefix = r1;
            return this;
        }
    }

    static {
        tmpRect = new Rect();
    }

    private PipSource(PipActivityController r5, Builder r6) {
        int r0 = sourceIdCounter;
        sourceIdCounter = r0 + 1;
        this.sourceId = r0;
        PipSourceParams r1 = new PipSourceParams();
        this.params = r1;
        this.pipPositionObserver = new PipPositionObserver(new PipSource$$ExternalSyntheticLambda0(this));
        StringBuilder r2 = new StringBuilder();
        if (Builder.m5161$$Nest$fgettagPrefix(r6) == null) goto L5;
        String r3 = Builder.m5161$$Nest$fgettagPrefix(r6);
    L6:
        r2.append(r3);
        r2.append("-");
        r2.append(r0);
        this.tag = r2.toString();
        this.delegate = Builder.m5155$$Nest$fgetdelegate(r6);
        Builder.m5152$$Nest$fgetactionListener(r6);
        this.priority = Builder.m5160$$Nest$fgetpriority(r6);
        this.cornerRadius = Builder.m5154$$Nest$fgetcornerRadius(r6);
        this.needMediaSession = Builder.m5157$$Nest$fgetneedMediaSession(r6);
        this.controller = r5;
        r1.setRatio(Builder.m5162$$Nest$fgetwidth(r6), Builder.m5156$$Nest$fgetheight(r6));
        this.player = Builder.m5159$$Nest$fgetplayer(r6);
        this.placeholderView = Builder.m5158$$Nest$fgetplaceholderView(r6);
        this.state2 = new PipSourceHandlerState2(this);
        setContentView(Builder.m5153$$Nest$fgetcontentView(r6));
        checkAvailable(false);
        invalidateActions();
        r5.dispatchSourceRegister(this);
        return;
    L5:
        r3 = "pip-source";
        goto L6
    }

    public /* synthetic */ PipSource(PipActivityController r1, Builder r2, PipSourceIA r3) {
        this(r1, r2);
    }

    private void checkAvailable(boolean r3) {
        if (this.params.isValid() == true) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if (this.isAvailable == r0) goto L14;
        this.isAvailable = r0;
        if (r3 == false) goto L15;
        this.controller.dispatchSourceAvailabilityChanged(this);
        return;
    L15:
        return;
    L14:
        return;
    L5:
        if (this.delegate.pipIsAvailable() == false) goto L7;
        r0 = true;
        goto L9
    }

    private void updateContentPosition(View r4) {
        if (AndroidUtilities.isInPictureInPictureMode(this.controller.activity) == true) goto L18;
        Activity r0 = this.controller.activity;
        Rect r1 = tmpRect;
        PipUtils.getPipSourceRectHintPosition(r0, r4, r1);
        boolean r02 = this.params.setPosition(r1);
        if ((r4 instanceof TextureViewRenderer) == false) goto L10;
        TextureViewRenderer r42 = (TextureViewRenderer) r4;
        int r12 = r42.rotatedFrameWidth;
        int r43 = r42.rotatedFrameHeight;
        boolean r44 = this.params.setRatio(r12, r43);
    L8:
        r02 = r02 | r44;
    L14:
        if (r02 == false) goto L17;
        checkAvailable(true);
        this.controller.dispatchSourceParamsChanged(this);
        return;
    L17:
        return;
    L10:
        if (r4.getWidth() == 0) goto L14;
        if (r4.getHeight() == 0) goto L14;
        int r13 = r4.getWidth();
        int r45 = r4.getHeight();
        r44 = this.params.setRatio(r13, r45);
        goto L8
    }

    public PictureInPictureParams buildPictureInPictureParams() {
        PictureInPictureParams.Builder r0 = this.params.build();
        r0.setActions(this.remoteActions);
        if (Build.VERSION.SDK_INT < 31) goto L6;
        r0.setAutoEnterEnabled(PipUtils.useAutoEnterInPictureInPictureMode());
    L6:
        return r0.build();
    }

    public void destroy() {
        this.pipPositionObserver.stop();
        this.controller.dispatchSourceUnregister(this);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void invalidateActions() {
    }

    public void invalidateAvailability() {
        checkAvailable(true);
    }

    public void invalidatePosition() {
        View r0 = this.contentView;
        if (r0 == null) goto L6;
        updateContentPosition(r0);
        return;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setContentRatio(int r2, int r3) {
        if (this.params.setRatio(r2, r3) == false) goto L6;
        checkAvailable(true);
        this.controller.dispatchSourceParamsChanged(this);
        return;
    }

    public void setContentView(View r2) {
        this.pipPositionObserver.start(r2);
        this.contentView = r2;
        if (r2 == null) goto L6;
        updateContentPosition(r2);
        return;
    }

    public void setPlaceholderView(View r1) {
        this.placeholderView = r1;
    }

    public void setPlayer(Player r1) {
        this.player = r1;
        checkAvailable(true);
        this.controller.dispatchSourceParamsChanged(this);
    }
}
