package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import java.util.Iterator;
import me.vkryl.android.animator.ListAnimator;
import me.vkryl.android.animator.ReplaceAnimator;
import me.vkryl.core.lambda.Destroyable;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LocationController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.voip.GroupCallMessage;
import org.telegram.messenger.voip.GroupCallMessagesController;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSlider;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AudioPlayerAlert;
import org.telegram.ui.Components.HintsController;
import org.telegram.ui.Components.conference.message.GroupCallMessageCell;
import org.telegram.ui.Components.voip.CellFlickerDrawable;
import org.telegram.ui.Components.voip.VoIPHelper;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.GroupCallActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.LocationActivity;
import org.telegram.ui.Stories.LivePlayer;

/* loaded from: classes3.dex */
public class FragmentContextView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate, VoIPService.StateListener, GroupCallMessagesController.CallMessageListener {
    private static final float[] speeds = null;
    private final int account;
    private AnimatorSet animatorSet;
    private View applyingView;
    private AvatarsImageView avatars;
    private final ReplaceAnimator<CallMessageItem> callMessagesAnimator;
    private final CapsuleBlobDrawable capsuleBlobDrawable;
    private ChatActivityInterface chatActivity;
    private boolean checkCallAfterAnimation;
    private boolean checkImportAfterAnimation;
    private boolean checkLiveStoryAfterAnimation;
    private Runnable checkLocationRunnable;
    private boolean checkPlayerAfterAnimation;
    private ImageView closeButton;
    float collapseProgress;
    boolean collapseTransition;
    private int currentProgress;
    private int currentStyle;
    private FragmentContextViewDelegate delegate;
    boolean drawOverlay;
    float extraHeight;
    private boolean firstLocationsLoaded;
    private boolean flickOnAttach;
    private BaseFragment fragment;
    private FrameLayout frameLayout;
    private NotificationCenter.ObserversGroup globalObserversGroup;
    private Paint gradientPaint;
    private TextPaint gradientTextPaint;
    private int gradientWidth;
    private int groupCallMessageCounter;
    private FrameLayout groupCallMessagesContainer;
    private RLottieImageView importingImageView;
    private boolean isLocation;
    private boolean isMusic;
    private boolean isMuted;
    private final boolean isSideMenued;
    private TextView joinButton;
    private CellFlickerDrawable joinButtonFlicker;
    private int joinButtonWidth;
    private int lastLocationSharingCount;
    private MessageObject lastMessageObject;
    private long lastPlaybackClick;
    private String lastString;
    private float leftMargin;
    private LinearGradient linearGradient;
    private Matrix matrix;
    float micAmplitude;
    private RLottieImageView muteButton;
    private RLottieDrawable muteDrawable;
    private AnimationNotificationsLocker notificationsLocker;
    private AnimationNotificationsLocker notificationsLocker2;
    private ButtonBounce notifyButtonBounce;
    private boolean notifyButtonEnabled;
    private final AnimatedTextView.AnimatedTextDrawable notifyText;
    private final NotificationCenter.ObserversGroup[] observersGroup;
    private ImageView playButton;
    private PlayPauseDrawable playPauseDrawable;
    private ActionBarMenuItem playbackSpeedButton;
    private final Paint progressPaint;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean scheduleRunnableScheduled;
    private View selector;
    private FrameLayout silentButton;
    private ImageView silentButtonImage;
    private boolean slidingSpeed;
    float speakerAmplitude;
    private HintView speedHintView;
    private ViewGroup speedHintViewParent;
    private SpeedIconDrawable speedIcon;
    private ActionBarMenuItem.Item[] speedItems;
    private ActionBarMenuSlider.SpeedSlider speedSlider;
    private AudioPlayerAlert.ClippingTextViewSwitcher subtitleTextView;
    private boolean supportsCalls;
    private AudioPlayerAlert.ClippingTextViewSwitcher titleTextView;
    private int toggleGroupCallStartSubscriptionReqId;
    protected float topPadding;
    private final Runnable updateScheduleTimeRunnable;
    private boolean visible;
    boolean wasDraw;
    private boolean willBeNotified;

    /* renamed from: org.telegram.ui.Components.FragmentContextView$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass1(FragmentContextView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (FragmentContextView.m10765$$Nest$fgetgradientTextPaint(this.this$0) != null) goto L5;
        L24:
            FragmentContextView.m10793$$Nest$fputscheduleRunnableScheduled(this.this$0, false);
            return;
        L5:
            if ((FragmentContextView.m10762$$Nest$fgetfragment(this.this$0) instanceof ChatActivity) == false) goto L24;
            ChatObject.Call r0 = FragmentContextView.m10754$$Nest$fgetchatActivity(this.this$0).getGroupCall();
            if (r0 != null) goto L10;
        L22:
            FragmentContextView.m10792$$Nest$fputnotifyButtonEnabled(this.this$0, false);
            FragmentContextView.m10793$$Nest$fputscheduleRunnableScheduled(this.this$0, false);
            return;
        L10:
            if (r0.isScheduled() == false) goto L22;
            int r2 = FragmentContextView.m10762$$Nest$fgetfragment(this.this$0).getConnectionsManager().getCurrentTime();
            int r02 = r0.call.schedule_date;
            int r3 = r02 - r2;
            if (r3 < 86400) goto L15;
            String r03 = LocaleController.formatPluralString("Days", Math.round(r3 / 86400.0f), new Object[0]);
        L16:
            AnimatedTextView.AnimatedTextDrawable r1 = FragmentContextView.m10778$$Nest$fgetnotifyText(this.this$0);
            if (FragmentContextView.m10782$$Nest$fgetwillBeNotified(this.this$0) == true) goto L20;
            r03 = LocaleController.getString(R.string.VoipChatNotify);
        L20:
            r1.setText(r03, true);
            AndroidUtilities.runOnUIThread(FragmentContextView.m10781$$Nest$fgetupdateScheduleTimeRunnable(this.this$0), 1000);
            FragmentContextView.m10763$$Nest$fgetframeLayout(this.this$0).invalidate();
            return;
        L15:
            r03 = AndroidUtilities.formatFullDuration(r02 - r2);
            goto L16
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$10, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass10(FragmentContextView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0) != null) goto L5;
            return;
        L5:
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0).equals(r2) == false) goto L9;
            this.this$0.setVisibility(8);
            FragmentContextView.m10783$$Nest$fputanimatorSet(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$11, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass11 extends AnimatorListenerAdapter {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass11(FragmentContextView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0) != null) goto L5;
            return;
        L5:
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0).equals(r2) == false) goto L9;
            FragmentContextView.m10783$$Nest$fputanimatorSet(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$12, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass12 extends AnimatorListenerAdapter {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass12(FragmentContextView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            FragmentContextView.m10774$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0) != null) goto L5;
            return;
        L5:
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0).equals(r3) == false) goto L24;
            this.this$0.setVisibility(8);
            if (FragmentContextView.m10761$$Nest$fgetdelegate(this.this$0) == null) goto L9;
            FragmentContextView.m10761$$Nest$fgetdelegate(this.this$0).onAnimation(false, false);
        L9:
            FragmentContextView.m10783$$Nest$fputanimatorSet(this.this$0, null);
            boolean r32 = FragmentContextView.m10757$$Nest$fgetcheckLiveStoryAfterAnimation(this.this$0);
            FragmentContextView r1 = this.this$0;
            if (r32 == false) goto L12;
            FragmentContextView.m10795$$Nest$mcheckLiveStory(r1, false);
        L21:
            FragmentContextView.m10786$$Nest$fputcheckLiveStoryAfterAnimation(this.this$0, false);
            FragmentContextView.m10784$$Nest$fputcheckCallAfterAnimation(this.this$0, false);
            FragmentContextView.m10787$$Nest$fputcheckPlayerAfterAnimation(this.this$0, false);
            FragmentContextView.m10785$$Nest$fputcheckImportAfterAnimation(this.this$0, false);
            return;
        L12:
            boolean r33 = FragmentContextView.m10755$$Nest$fgetcheckCallAfterAnimation(r1);
            FragmentContextView r12 = this.this$0;
            if (r33 == false) goto L15;
            r12.checkCall(false);
            goto L21
        L15:
            boolean r34 = FragmentContextView.m10759$$Nest$fgetcheckPlayerAfterAnimation(r12);
            FragmentContextView r13 = this.this$0;
            if (r34 == false) goto L19;
            FragmentContextView.m10797$$Nest$mcheckPlayer(r13, false);
            goto L21
        L19:
            if (FragmentContextView.m10756$$Nest$fgetcheckImportAfterAnimation(r13) == false) goto L21;
            this.this$0.checkImport(false);
            goto L21
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$13, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass13 extends AnimatorListenerAdapter {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass13(FragmentContextView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            FragmentContextView.m10774$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0) != null) goto L5;
            return;
        L5:
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0).equals(r3) == true) goto L7;
            return;
        L7:
            if (FragmentContextView.m10761$$Nest$fgetdelegate(this.this$0) == null) goto L9;
            FragmentContextView.m10761$$Nest$fgetdelegate(this.this$0).onAnimation(false, true);
        L9:
            FragmentContextView.m10783$$Nest$fputanimatorSet(this.this$0, null);
            boolean r32 = FragmentContextView.m10757$$Nest$fgetcheckLiveStoryAfterAnimation(this.this$0);
            FragmentContextView r1 = this.this$0;
            if (r32 == false) goto L12;
            FragmentContextView.m10795$$Nest$mcheckLiveStory(r1, false);
        L21:
            FragmentContextView.m10786$$Nest$fputcheckLiveStoryAfterAnimation(this.this$0, false);
            FragmentContextView.m10784$$Nest$fputcheckCallAfterAnimation(this.this$0, false);
            FragmentContextView.m10787$$Nest$fputcheckPlayerAfterAnimation(this.this$0, false);
            FragmentContextView.m10785$$Nest$fputcheckImportAfterAnimation(this.this$0, false);
            return;
        L12:
            boolean r33 = FragmentContextView.m10755$$Nest$fgetcheckCallAfterAnimation(r1);
            FragmentContextView r12 = this.this$0;
            if (r33 == false) goto L15;
            r12.checkCall(false);
            goto L21
        L15:
            boolean r34 = FragmentContextView.m10759$$Nest$fgetcheckPlayerAfterAnimation(r12);
            FragmentContextView r13 = this.this$0;
            if (r34 == false) goto L19;
            FragmentContextView.m10797$$Nest$mcheckPlayer(r13, false);
            goto L21
        L19:
            if (FragmentContextView.m10756$$Nest$fgetcheckImportAfterAnimation(r13) == false) goto L21;
            this.this$0.checkImport(false);
            goto L21
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$14, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass14 extends AnimatorListenerAdapter {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass14(FragmentContextView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            FragmentContextView.m10774$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0) != null) goto L5;
            return;
        L5:
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0).equals(r3) == false) goto L21;
            this.this$0.setVisibility(8);
            FragmentContextView.m10783$$Nest$fputanimatorSet(this.this$0, null);
            boolean r32 = FragmentContextView.m10757$$Nest$fgetcheckLiveStoryAfterAnimation(this.this$0);
            FragmentContextView r0 = this.this$0;
            if (r32 == false) goto L9;
            FragmentContextView.m10795$$Nest$mcheckLiveStory(r0, false);
        L18:
            FragmentContextView.m10786$$Nest$fputcheckLiveStoryAfterAnimation(this.this$0, false);
            FragmentContextView.m10784$$Nest$fputcheckCallAfterAnimation(this.this$0, false);
            FragmentContextView.m10787$$Nest$fputcheckPlayerAfterAnimation(this.this$0, false);
            FragmentContextView.m10785$$Nest$fputcheckImportAfterAnimation(this.this$0, false);
            return;
        L9:
            boolean r33 = FragmentContextView.m10755$$Nest$fgetcheckCallAfterAnimation(r0);
            FragmentContextView r02 = this.this$0;
            if (r33 == false) goto L12;
            r02.checkCall(false);
            goto L18
        L12:
            boolean r34 = FragmentContextView.m10759$$Nest$fgetcheckPlayerAfterAnimation(r02);
            FragmentContextView r03 = this.this$0;
            if (r34 == false) goto L16;
            FragmentContextView.m10797$$Nest$mcheckPlayer(r03, false);
            goto L18
        L16:
            if (FragmentContextView.m10756$$Nest$fgetcheckImportAfterAnimation(r03) == false) goto L18;
            this.this$0.checkImport(false);
            goto L18
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$15, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass15 extends AnimatorListenerAdapter {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass15(FragmentContextView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            FragmentContextView.m10774$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0) != null) goto L5;
            return;
        L5:
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0).equals(r3) == true) goto L7;
            return;
        L7:
            if (FragmentContextView.m10761$$Nest$fgetdelegate(this.this$0) == null) goto L9;
            FragmentContextView.m10761$$Nest$fgetdelegate(this.this$0).onAnimation(false, true);
        L9:
            FragmentContextView.m10783$$Nest$fputanimatorSet(this.this$0, null);
            boolean r32 = FragmentContextView.m10757$$Nest$fgetcheckLiveStoryAfterAnimation(this.this$0);
            FragmentContextView r1 = this.this$0;
            if (r32 == false) goto L12;
            FragmentContextView.m10795$$Nest$mcheckLiveStory(r1, false);
        L21:
            FragmentContextView.m10786$$Nest$fputcheckLiveStoryAfterAnimation(this.this$0, false);
            FragmentContextView.m10784$$Nest$fputcheckCallAfterAnimation(this.this$0, false);
            FragmentContextView.m10787$$Nest$fputcheckPlayerAfterAnimation(this.this$0, false);
            FragmentContextView.m10785$$Nest$fputcheckImportAfterAnimation(this.this$0, false);
            return;
        L12:
            boolean r33 = FragmentContextView.m10755$$Nest$fgetcheckCallAfterAnimation(r1);
            FragmentContextView r12 = this.this$0;
            if (r33 == false) goto L15;
            r12.checkCall(false);
            goto L21
        L15:
            boolean r34 = FragmentContextView.m10759$$Nest$fgetcheckPlayerAfterAnimation(r12);
            FragmentContextView r13 = this.this$0;
            if (r34 == false) goto L19;
            FragmentContextView.m10797$$Nest$mcheckPlayer(r13, false);
            goto L21
        L19:
            if (FragmentContextView.m10756$$Nest$fgetcheckImportAfterAnimation(r13) == false) goto L21;
            this.this$0.checkImport(false);
            goto L21
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$16, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass16 extends AnimatorListenerAdapter {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass16(FragmentContextView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            FragmentContextView.m10774$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0) != null) goto L5;
            return;
        L5:
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0).equals(r3) == false) goto L21;
            this.this$0.setVisibility(8);
            FragmentContextView.m10783$$Nest$fputanimatorSet(this.this$0, null);
            boolean r32 = FragmentContextView.m10757$$Nest$fgetcheckLiveStoryAfterAnimation(this.this$0);
            FragmentContextView r0 = this.this$0;
            if (r32 == false) goto L9;
            FragmentContextView.m10795$$Nest$mcheckLiveStory(r0, false);
        L18:
            FragmentContextView.m10786$$Nest$fputcheckLiveStoryAfterAnimation(this.this$0, false);
            FragmentContextView.m10784$$Nest$fputcheckCallAfterAnimation(this.this$0, false);
            FragmentContextView.m10787$$Nest$fputcheckPlayerAfterAnimation(this.this$0, false);
            FragmentContextView.m10785$$Nest$fputcheckImportAfterAnimation(this.this$0, false);
            return;
        L9:
            boolean r33 = FragmentContextView.m10755$$Nest$fgetcheckCallAfterAnimation(r0);
            FragmentContextView r02 = this.this$0;
            if (r33 == false) goto L12;
            r02.checkCall(false);
            goto L18
        L12:
            boolean r34 = FragmentContextView.m10759$$Nest$fgetcheckPlayerAfterAnimation(r02);
            FragmentContextView r03 = this.this$0;
            if (r34 == false) goto L16;
            FragmentContextView.m10797$$Nest$mcheckPlayer(r03, false);
            goto L18
        L16:
            if (FragmentContextView.m10756$$Nest$fgetcheckImportAfterAnimation(r03) == false) goto L18;
            this.this$0.checkImport(false);
            goto L18
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$17, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass17 extends AnimatorListenerAdapter {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass17(FragmentContextView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            FragmentContextView.m10774$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0) != null) goto L5;
            return;
        L5:
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0).equals(r3) == false) goto L9;
            FragmentContextView.m10794$$Nest$fputvisible(this.this$0, false);
            FragmentContextView.m10783$$Nest$fputanimatorSet(this.this$0, null);
            FragmentContextView.m10795$$Nest$mcheckLiveStory(this.this$0, false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$18, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass18 extends AnimatorListenerAdapter {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass18(FragmentContextView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            FragmentContextView.m10775$$Nest$fgetnotificationsLocker2(this.this$0).unlock();
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0) != null) goto L5;
        L7:
            boolean r32 = FragmentContextView.m10757$$Nest$fgetcheckLiveStoryAfterAnimation(this.this$0);
            FragmentContextView r0 = this.this$0;
            if (r32 == false) goto L10;
            FragmentContextView.m10795$$Nest$mcheckLiveStory(r0, false);
        L19:
            FragmentContextView.m10786$$Nest$fputcheckLiveStoryAfterAnimation(this.this$0, false);
            FragmentContextView.m10784$$Nest$fputcheckCallAfterAnimation(this.this$0, false);
            FragmentContextView.m10787$$Nest$fputcheckPlayerAfterAnimation(this.this$0, false);
            FragmentContextView.m10785$$Nest$fputcheckImportAfterAnimation(this.this$0, false);
            FragmentContextView.m10799$$Nest$mstartJoinFlickerAnimation(this.this$0);
            return;
        L10:
            boolean r33 = FragmentContextView.m10755$$Nest$fgetcheckCallAfterAnimation(r0);
            FragmentContextView r02 = this.this$0;
            if (r33 == false) goto L13;
            r02.checkCall(false);
            goto L19
        L13:
            boolean r34 = FragmentContextView.m10759$$Nest$fgetcheckPlayerAfterAnimation(r02);
            FragmentContextView r03 = this.this$0;
            if (r34 == false) goto L17;
            FragmentContextView.m10797$$Nest$mcheckPlayer(r03, false);
            goto L19
        L17:
            if (FragmentContextView.m10756$$Nest$fgetcheckImportAfterAnimation(r03) == false) goto L19;
            this.this$0.checkImport(false);
            goto L19
        L5:
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0).equals(r3) == false) goto L7;
            FragmentContextView.m10783$$Nest$fputanimatorSet(this.this$0, null);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$19, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass19 extends AnimatorListenerAdapter {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass19(FragmentContextView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            FragmentContextView.m10774$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0) != null) goto L5;
            return;
        L5:
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0).equals(r3) == false) goto L21;
            this.this$0.setVisibility(8);
            FragmentContextView.m10783$$Nest$fputanimatorSet(this.this$0, null);
            boolean r32 = FragmentContextView.m10757$$Nest$fgetcheckLiveStoryAfterAnimation(this.this$0);
            FragmentContextView r0 = this.this$0;
            if (r32 == false) goto L9;
            FragmentContextView.m10795$$Nest$mcheckLiveStory(r0, false);
        L18:
            FragmentContextView.m10786$$Nest$fputcheckLiveStoryAfterAnimation(this.this$0, false);
            FragmentContextView.m10784$$Nest$fputcheckCallAfterAnimation(this.this$0, false);
            FragmentContextView.m10787$$Nest$fputcheckPlayerAfterAnimation(this.this$0, false);
            FragmentContextView.m10785$$Nest$fputcheckImportAfterAnimation(this.this$0, false);
            return;
        L9:
            boolean r33 = FragmentContextView.m10755$$Nest$fgetcheckCallAfterAnimation(r0);
            FragmentContextView r02 = this.this$0;
            if (r33 == false) goto L12;
            r02.checkCall(false);
            goto L18
        L12:
            boolean r34 = FragmentContextView.m10759$$Nest$fgetcheckPlayerAfterAnimation(r02);
            FragmentContextView r03 = this.this$0;
            if (r34 == false) goto L16;
            FragmentContextView.m10797$$Nest$mcheckPlayer(r03, false);
            goto L18
        L16:
            if (FragmentContextView.m10756$$Nest$fgetcheckImportAfterAnimation(r03) == false) goto L18;
            this.this$0.checkImport(false);
            goto L18
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass2(FragmentContextView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentContextView.m10796$$Nest$mcheckLocationString(this.this$0);
            AndroidUtilities.runOnUIThread(FragmentContextView.m10758$$Nest$fgetcheckLocationRunnable(this.this$0), 1000);
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$20, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass20 extends AnimatorListenerAdapter {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass20(FragmentContextView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            FragmentContextView.m10774$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0) != null) goto L5;
            return;
        L5:
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0).equals(r3) == false) goto L9;
            FragmentContextView.m10794$$Nest$fputvisible(this.this$0, false);
            FragmentContextView.m10783$$Nest$fputanimatorSet(this.this$0, null);
            this.this$0.checkCall(false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$21, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass21 extends AnimatorListenerAdapter {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass21(FragmentContextView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            FragmentContextView.m10775$$Nest$fgetnotificationsLocker2(this.this$0).unlock();
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0) != null) goto L5;
        L7:
            boolean r32 = FragmentContextView.m10757$$Nest$fgetcheckLiveStoryAfterAnimation(this.this$0);
            FragmentContextView r0 = this.this$0;
            if (r32 == false) goto L10;
            FragmentContextView.m10795$$Nest$mcheckLiveStory(r0, false);
        L19:
            FragmentContextView.m10786$$Nest$fputcheckLiveStoryAfterAnimation(this.this$0, false);
            FragmentContextView.m10784$$Nest$fputcheckCallAfterAnimation(this.this$0, false);
            FragmentContextView.m10787$$Nest$fputcheckPlayerAfterAnimation(this.this$0, false);
            FragmentContextView.m10785$$Nest$fputcheckImportAfterAnimation(this.this$0, false);
            FragmentContextView.m10799$$Nest$mstartJoinFlickerAnimation(this.this$0);
            return;
        L10:
            boolean r33 = FragmentContextView.m10755$$Nest$fgetcheckCallAfterAnimation(r0);
            FragmentContextView r02 = this.this$0;
            if (r33 == false) goto L13;
            r02.checkCall(false);
            goto L19
        L13:
            boolean r34 = FragmentContextView.m10759$$Nest$fgetcheckPlayerAfterAnimation(r02);
            FragmentContextView r03 = this.this$0;
            if (r34 == false) goto L17;
            FragmentContextView.m10797$$Nest$mcheckPlayer(r03, false);
            goto L19
        L17:
            if (FragmentContextView.m10756$$Nest$fgetcheckImportAfterAnimation(r03) == false) goto L19;
            this.this$0.checkImport(false);
            goto L19
        L5:
            if (FragmentContextView.m10751$$Nest$fgetanimatorSet(this.this$0).equals(r3) == false) goto L7;
            FragmentContextView.m10783$$Nest$fputanimatorSet(this.this$0, null);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends FrameLayout {
        private final RectF notifyButtonRect;
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass3(FragmentContextView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.notifyButtonRect = new RectF();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r11) {
            super.dispatchDraw(r11);
            if (FragmentContextView.m10760$$Nest$fgetcurrentStyle(this.this$0) == 4) goto L5;
            return;
        L5:
            if (FragmentContextView.m10777$$Nest$fgetnotifyButtonEnabled(this.this$0) == false) goto L28;
            int r0 = ((int) Math.ceil(FragmentContextView.m10778$$Nest$fgetnotifyText(this.this$0).getCurrentWidth())) + AndroidUtilities.dp(24.0f);
            if (r0 == FragmentContextView.m10766$$Nest$fgetgradientWidth(this.this$0)) goto L9;
            FragmentContextView.m10791$$Nest$fputlinearGradient(this.this$0, new LinearGradient(0.0f, 0.0f, r0, 0.0f, new int[]{-10121218, -6983683}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
            FragmentContextView.m10764$$Nest$fgetgradientPaint(this.this$0).setShader(FragmentContextView.m10770$$Nest$fgetlinearGradient(this.this$0));
            FragmentContextView.m10788$$Nest$fputgradientWidth(this.this$0, r0);
        L9:
            ChatObject.Call r1 = FragmentContextView.m10754$$Nest$fgetchatActivity(this.this$0).getGroupCall();
            if (FragmentContextView.m10762$$Nest$fgetfragment(this.this$0) == null) goto L24;
            if (r1 == null) goto L24;
            if (r1.isScheduled() == false) goto L24;
            long r12 = (r1.call.schedule_date * 1000) - FragmentContextView.m10762$$Nest$fgetfragment(this.this$0).getConnectionsManager().getCurrentTimeMillis();
            float r5 = 1.0f;
            if (r12 < 0) goto L22;
            if (r12 >= 5000) goto L20;
            r5 = 1.0f - (r12 / 5000.0f);
            goto L22
        L20:
            r5 = 0.0f;
        L22:
            if (r12 >= 6000) goto L25;
            invalidate();
        L25:
            FragmentContextView.m10771$$Nest$fgetmatrix(this.this$0).reset();
            FragmentContextView.m10771$$Nest$fgetmatrix(this.this$0).postTranslate(((-FragmentContextView.m10766$$Nest$fgetgradientWidth(this.this$0)) * 0.7f) * r5, 0.0f);
            FragmentContextView.m10770$$Nest$fgetlinearGradient(this.this$0).setLocalMatrix(FragmentContextView.m10771$$Nest$fgetmatrix(this.this$0));
            int r13 = (getMeasuredWidth() - r0) - AndroidUtilities.dp(10.0f);
            float r52 = r13;
            float r6 = AndroidUtilities.dp(10.0f);
            this.notifyButtonRect.set(r52, r6, r13 + r0, r2 + AndroidUtilities.dp(28.0f));
            r11.save();
            float r14 = FragmentContextView.m10776$$Nest$fgetnotifyButtonBounce(this.this$0).getScale(0.1f);
            r11.scale(r14, r14, this.notifyButtonRect.centerX(), this.notifyButtonRect.centerY());
            r11.translate(r52, r6);
            RectF r15 = AndroidUtilities.rectTmp;
            r15.set(0.0f, 0.0f, r0, AndroidUtilities.dp(28.0f));
            r11.drawRoundRect(r15, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), FragmentContextView.m10764$$Nest$fgetgradientPaint(this.this$0));
            r11.translate(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(6.0f));
            FragmentContextView.m10778$$Nest$fgetnotifyText(this.this$0).setBounds(0, 0, AndroidUtilities.displaySize.x, AndroidUtilities.dp(16.0f));
            FragmentContextView.m10778$$Nest$fgetnotifyText(this.this$0).draw(r11);
            r11.restore();
            return;
        L24:
            r5 = 0.0f;
            goto L25
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r6) {
            if (FragmentContextView.m10760$$Nest$fgetcurrentStyle(this.this$0) != 4) goto L24;
            if (FragmentContextView.m10777$$Nest$fgetnotifyButtonEnabled(this.this$0) == false) goto L24;
            if (FragmentContextView.m10776$$Nest$fgetnotifyButtonBounce(this.this$0) == null) goto L24;
            boolean r0 = this.notifyButtonRect.contains(r6.getX(), r6.getY());
            if (r6.getAction() != 0) goto L12;
            FragmentContextView.m10776$$Nest$fgetnotifyButtonBounce(this.this$0).setPressed(r0);
        L27:
            if (FragmentContextView.m10776$$Nest$fgetnotifyButtonBounce(this.this$0) == null) goto L31;
            if (FragmentContextView.m10776$$Nest$fgetnotifyButtonBounce(this.this$0).isPressed() == false) goto L31;
        L32:
            return true;
        L31:
            if (super.dispatchTouchEvent(r6) == true) goto L32;
            return false;
        L12:
            if (r6.getAction() != 2) goto L16;
            if (r0 == true) goto L27;
            FragmentContextView.m10776$$Nest$fgetnotifyButtonBounce(this.this$0).setPressed(false);
            goto L27
        L16:
            if (r6.getAction() != 1) goto L21;
            if (r0 == false) goto L19;
            this.this$0.toggleScheduledNotify();
        L19:
            FragmentContextView.m10776$$Nest$fgetnotifyButtonBounce(this.this$0).setPressed(false);
            goto L27
        L21:
            if (r6.getAction() != 3) goto L27;
            FragmentContextView.m10776$$Nest$fgetnotifyButtonBounce(this.this$0).setPressed(false);
        L24:
            if (FragmentContextView.m10776$$Nest$fgetnotifyButtonBounce(this.this$0) == null) goto L27;
            FragmentContextView.m10776$$Nest$fgetnotifyButtonBounce(this.this$0).setPressed(false);
            goto L27
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            if (FragmentContextView.m10752$$Nest$fgetavatars(this.this$0) != null) goto L5;
            return;
        L5:
            if (FragmentContextView.m10752$$Nest$fgetavatars(this.this$0).getVisibility() != 0) goto L9;
            FragmentContextView.m10752$$Nest$fgetavatars(this.this$0).invalidate();
            return;
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != FragmentContextView.m10778$$Nest$fgetnotifyText(this.this$0)) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends AudioPlayerAlert.ClippingTextViewSwitcher {
        final /* synthetic */ FragmentContextView this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass4(FragmentContextView r1, Context r2, Context r3) {
            this.this$0 = r1;
            this.val$context = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Components.AudioPlayerAlert.ClippingTextViewSwitcher
        public TextView createTextView() {
            TextView r0 = new TextView(this.val$context);
            r0.setMaxLines(1);
            r0.setLines(1);
            r0.setSingleLine(true);
            r0.setEllipsize(TextUtils.TruncateAt.END);
            r0.setTextSize(1, 15.0f);
            r0.setGravity(19);
            if (FragmentContextView.m10760$$Nest$fgetcurrentStyle(this.this$0) != 0) goto L5;
        L19:
            r0.setGravity(19);
            r0.setTypeface(Typeface.DEFAULT);
            r0.setTextSize(1, 15.0f);
            return r0;
        L5:
            if (FragmentContextView.m10760$$Nest$fgetcurrentStyle(this.this$0) == 2) goto L19;
            if (FragmentContextView.m10760$$Nest$fgetcurrentStyle(this.this$0) != 4) goto L12;
            r0.setGravity(51);
            r0.setTextColor(FragmentContextView.m10798$$Nest$mgetThemedColor(this.this$0, Theme.key_inappPlayerPerformer));
            r0.setTypeface(AndroidUtilities.bold());
            r0.setTextSize(1, 15.0f);
            return r0;
        L12:
            if (FragmentContextView.m10760$$Nest$fgetcurrentStyle(this.this$0) != 1) goto L14;
        L17:
            r0.setGravity(19);
            r0.setTextColor(FragmentContextView.m10798$$Nest$mgetThemedColor(this.this$0, Theme.key_returnToCallText));
            r0.setTypeface(AndroidUtilities.bold());
            r0.setTextSize(1, 14.0f);
            return r0;
        L14:
            if (FragmentContextView.m10760$$Nest$fgetcurrentStyle(this.this$0) == 3) goto L17;
            return r0;
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 extends AudioPlayerAlert.ClippingTextViewSwitcher {
        final /* synthetic */ FragmentContextView this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass5(FragmentContextView r1, Context r2, Context r3) {
            this.this$0 = r1;
            this.val$context = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Components.AudioPlayerAlert.ClippingTextViewSwitcher
        public TextView createTextView() {
            TextView r0 = new TextView(this.val$context);
            r0.setMaxLines(1);
            r0.setLines(1);
            r0.setSingleLine(true);
            r0.setEllipsize(TextUtils.TruncateAt.END);
            r0.setGravity(3);
            r0.setTextSize(1, 13.0f);
            r0.setTextColor(FragmentContextView.m10798$$Nest$mgetThemedColor(this.this$0, Theme.key_inappPlayerClose));
            return r0;
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 extends TextView {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass6(FragmentContextView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private void updateJoinButtonWidth(int r6) {
            if (FragmentContextView.m10769$$Nest$fgetjoinButtonWidth(this.this$0) == r6) goto L6;
            FragmentContextView.m10780$$Nest$fgettitleTextView(this.this$0).setPadding(FragmentContextView.m10780$$Nest$fgettitleTextView(this.this$0).getPaddingLeft(), FragmentContextView.m10780$$Nest$fgettitleTextView(this.this$0).getPaddingTop(), (FragmentContextView.m10780$$Nest$fgettitleTextView(this.this$0).getPaddingRight() - FragmentContextView.m10769$$Nest$fgetjoinButtonWidth(this.this$0)) + r6, FragmentContextView.m10780$$Nest$fgettitleTextView(this.this$0).getPaddingBottom());
            FragmentContextView.m10790$$Nest$fputjoinButtonWidth(this.this$0, r6);
            return;
        }

        @Override // android.view.View
        public void draw(Canvas r6) {
            super.draw(r6);
            int r0 = AndroidUtilities.dp(1.0f);
            RectF r1 = AndroidUtilities.rectTmp;
            float r2 = r0;
            r1.set(r2, r2, getWidth() - r0, getHeight() - r0);
            FragmentContextView.m10768$$Nest$fgetjoinButtonFlicker(this.this$0).draw(r6, r1, AndroidUtilities.dp(16.0f), this);
        }

        @Override // android.widget.TextView, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            updateJoinButtonWidth(getMeasuredWidth());
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            FragmentContextView.m10768$$Nest$fgetjoinButtonFlicker(this.this$0).setParentWidth(getWidth());
        }

        @Override // android.view.View
        public void setVisibility(int r1) {
            super.setVisibility(r1);
            if (r1 == 0) goto L6;
            updateJoinButtonWidth(0);
            FragmentContextView.m10790$$Nest$fputjoinButtonWidth(this.this$0, 0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$7, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass7 extends RLottieImageView {
        private final Runnable pressRunnable;
        boolean pressed;
        boolean scheduled;
        final /* synthetic */ FragmentContextView this$0;
        private final Runnable toggleMicRunnable;

        public static /* synthetic */ void $r8$lambda$S_NoiRFXu4mQL_zlOp3w_4Xw13c(AnonymousClass7 r0) {
            r0.lambda$$1();
        }

        public static /* synthetic */ void $r8$lambda$XypYe9DH5WZu54q4dwK0QouejM4(AnonymousClass7 r0) {
            r0.lambda$$0();
        }

        public AnonymousClass7(FragmentContextView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.toggleMicRunnable = new FragmentContextView$7$$ExternalSyntheticLambda0(this);
            this.pressRunnable = new FragmentContextView$7$$ExternalSyntheticLambda1(this);
        }

        private /* synthetic */ void lambda$$0() {
            if (VoIPService.getSharedInstance() != null) goto L5;
            return;
        L5:
            VoIPService.getSharedInstance().setMicMute(false, true, false);
            RLottieDrawable r0 = FragmentContextView.m10773$$Nest$fgetmuteDrawable(this.this$0);
            if (FragmentContextView.m10767$$Nest$fgetisMuted(this.this$0) == false) goto L8;
            int r3 = 15;
        L10:
            if (r0.setCustomEndFrame(r3) == false) goto L15;
            boolean r02 = FragmentContextView.m10767$$Nest$fgetisMuted(this.this$0);
            FragmentContextView r32 = this.this$0;
            if (r02 == false) goto L14;
            FragmentContextView.m10773$$Nest$fgetmuteDrawable(r32).setCurrentFrame(0);
            goto L15
        L14:
            FragmentContextView.m10773$$Nest$fgetmuteDrawable(r32).setCurrentFrame(14);
        L15:
            FragmentContextView.m10772$$Nest$fgetmuteButton(this.this$0).playAnimation();
            Theme.getFragmentContextViewWavesDrawable().updateState(true);
            FragmentContextView.m10753$$Nest$fgetcapsuleBlobDrawable(this.this$0).updateState(true);
            return;
        L8:
            r3 = 29;
            goto L10
        }

        private /* synthetic */ void lambda$$1() {
            if (this.scheduled == true) goto L5;
            return;
        L5:
            if (VoIPService.getSharedInstance() == null) goto L14;
            this.scheduled = false;
            this.pressed = true;
            FragmentContextView.m10789$$Nest$fputisMuted(this.this$0, false);
            AndroidUtilities.runOnUIThread(this.toggleMicRunnable, 90);
            FragmentContextView.m10772$$Nest$fgetmuteButton(this.this$0).performHapticFeedback(3, 2);     // Catch: Exception -> L10
            return;
        L15:
            return;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r2);
            r2.setClassName(Button.class.getName());
            if (FragmentContextView.m10767$$Nest$fgetisMuted(this.this$0) == false) goto L6;
            int r1 = R.string.VoipUnmute;
        L7:
            r2.setText(LocaleController.getString(r1));
            return;
        L6:
            r1 = R.string.VoipMute;
            goto L7
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r11) {
            if (FragmentContextView.m10760$$Nest$fgetcurrentStyle(this.this$0) != 3) goto L5;
        L9:
            VoIPService r0 = VoIPService.getSharedInstance();
            if (r0 != null) goto L14;
            AndroidUtilities.cancelRunOnUIThread(this.pressRunnable);
            AndroidUtilities.cancelRunOnUIThread(this.toggleMicRunnable);
            this.scheduled = false;
            this.pressed = false;
            return true;
        L14:
            if (r11.getAction() != 0) goto L19;
            if (r0.isMicMute() == false) goto L19;
            AndroidUtilities.runOnUIThread(this.pressRunnable, 300);
            this.scheduled = true;
        L40:
            return super.onTouchEvent(r11);
        L19:
            if (r11.getAction() != 1) goto L21;
        L22:
            AndroidUtilities.cancelRunOnUIThread(this.toggleMicRunnable);
            if (this.scheduled == false) goto L26;
            AndroidUtilities.cancelRunOnUIThread(this.pressRunnable);
            this.scheduled = false;
            goto L40
        L26:
            if (this.pressed == false) goto L40;
            FragmentContextView.m10789$$Nest$fputisMuted(this.this$0, true);
            if (FragmentContextView.m10773$$Nest$fgetmuteDrawable(this.this$0).setCustomEndFrame(15) == false) goto L33;
            boolean r112 = FragmentContextView.m10767$$Nest$fgetisMuted(this.this$0);
            FragmentContextView r02 = this.this$0;
            if (r112 == false) goto L32;
            FragmentContextView.m10773$$Nest$fgetmuteDrawable(r02).setCurrentFrame(0);
            goto L33
        L32:
            FragmentContextView.m10773$$Nest$fgetmuteDrawable(r02).setCurrentFrame(14);
        L33:
            FragmentContextView.m10772$$Nest$fgetmuteButton(this.this$0).playAnimation();
            if (VoIPService.getSharedInstance() == null) goto L37;
            VoIPService.getSharedInstance().setMicMute(true, true, false);
            FragmentContextView.m10772$$Nest$fgetmuteButton(this.this$0).performHapticFeedback(3, 2);     // Catch: Exception -> L41
        L37:
            this.pressed = false;
            Theme.getFragmentContextViewWavesDrawable().updateState(true);
            FragmentContextView.m10753$$Nest$fgetcapsuleBlobDrawable(this.this$0).updateState(true);
            MotionEvent r113 = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0);
            super.onTouchEvent(r113);
            r113.recycle();
            return true;
        L21:
            if (r11.getAction() != 3) goto L40;
        L5:
            if (FragmentContextView.m10760$$Nest$fgetcurrentStyle(this.this$0) == 1) goto L9;
            return super.onTouchEvent(r11);
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$8, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass8 extends FrameLayout {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass8(FragmentContextView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.FragmentContextView$9, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass9 extends HintView {
        final /* synthetic */ FragmentContextView this$0;

        public AnonymousClass9(FragmentContextView r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // android.view.View
        public void setVisibility(int r1) {
            super.setVisibility(r1);
            if (r1 == 0) goto L9;
            FragmentContextView.m10779$$Nest$fgetspeedHintViewParent(this.this$0).removeView(this);     // Catch: Exception -> L6
            return;
        L10:
            return;
        }
    }

    /* loaded from: classes7.dex */
    public static class CallMessageItem implements Destroyable {
        private final GroupCallMessageCell cell;
        private final ViewGroup parent;

        /* renamed from: -$$Nest$fgetcell, reason: not valid java name */
        public static /* bridge */ /* synthetic */ GroupCallMessageCell m10800$$Nest$fgetcell(CallMessageItem r0) {
            return r0.cell;
        }

        public CallMessageItem(ViewGroup r4, GroupCallMessage r5) {
            GroupCallMessageCell r0 = new GroupCallMessageCell(r4.getContext());
            this.cell = r0;
            r0.setBackgroundColor(ColorUtils.setAlphaComponent(-16777216, 34));
            r0.setSingleLine();
            r0.set(r5);
            r0.setAlpha(0.0f);
            this.parent = r4;
            r4.addView(r0);
        }

        @Override // me.vkryl.core.lambda.Destroyable
        public void performDestroy() {
            this.parent.removeView(this.cell);
        }
    }

    /* loaded from: classes7.dex */
    public interface FragmentContextViewDelegate {
        void onAnimation(boolean r1, boolean r2);
    }

    /* renamed from: $r8$lambda$93YgZ2-eW31pZSvBVSYnzzjdk9s, reason: not valid java name */
    public static /* synthetic */ void m10748$r8$lambda$93YgZ2eW31pZSvBVSYnzzjdk9s(FragmentContextView r0, Float r1, Boolean r2) {
        r0.lambda$createPlaybackSpeedButton$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$CIIysyeWlPLOB0ZuV7WEd25RwO8(LocationController.SharingLocationInfo r10, long r11, TLRPC.MessageMedia r13, int r14, boolean r15, int r16, long r17) {
        SendMessagesHelper.getInstance(r10.messageObject.currentAccount).sendMessage(SendMessagesHelper.SendMessageParams.of(r13, r11, null, null, null, null, r15, r16, 0));
    }

    public static /* synthetic */ boolean $r8$lambda$DYJUvBXLUgcm6h0a1a1QLvVj7ak(FragmentContextView r0, View r1) {
        return r0.lambda$createPlaybackSpeedButton$14(r1);
    }

    public static /* synthetic */ void $r8$lambda$IfP9V6h0uRWy6TFqSI6_vhs22So(FragmentContextView r0, View r1) {
        r0.lambda$checkCreateView$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$KK6Z7VTZFkCJylhsscGplMy4qII(FragmentContextView r0, float r1, Boolean r2) {
        r0.lambda$createPlaybackSpeedButton$13(r1, r2);
    }

    /* renamed from: $r8$lambda$Mrmc-EqBYGPjzg5o9jE9ub2_AAE, reason: not valid java name */
    public static /* synthetic */ void m10749$r8$lambda$MrmcEqBYGPjzg5o9jE9ub2_AAE(FragmentContextView r0, View r1) {
        r0.lambda$checkCreateView$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$O9FlCVEqNc3fR8iLxkBmuAIJsGA(FragmentContextView r0, ReplaceAnimator r1) {
        r0.onItemChanged(r1);
    }

    /* renamed from: $r8$lambda$T4PPoSTg221miyKIKDOPcOE-j-0, reason: not valid java name */
    public static /* synthetic */ void m10750$r8$lambda$T4PPoSTg221miyKIKDOPcOEj0(FragmentContextView r0, int r1) {
        r0.lambda$createPlaybackSpeedButton$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$VVB8XXP6LJT8sM5f0ZYfmLWg4b8(FragmentContextView r0, View r1) {
        r0.lambda$checkCreateView$9(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$VfKLB3ejFTa8OkLhKKg7_VE3U5g(FragmentContextView r0, View r1) {
        return r0.lambda$checkCreateView$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$Y1ic1fvFpY9YEUWltmSDYMt3EEo(FragmentContextView r0, float[] r1, View r2) {
        r0.lambda$createPlaybackSpeedButton$12(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Z_DlqeAOkoUL9cakovDkdOYGQRs(FragmentContextView r0, View r1) {
        r0.lambda$checkCreateView$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$_ymPI69Hxkpz7uS5FoWNqsxDC_0(FragmentContextView r0, AlertDialog r1, int r2) {
        r0.lambda$checkCreateView$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$daxuzjwqlYsqzrRNBztPmxKGN8Q(FragmentContextView r0, View r1) {
        r0.lambda$checkCreateView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$iuWjpfAQNPCHVHLTF13hZNPzl7Y(FragmentContextView r0) {
        r0.lambda$checkCreateView$3();
    }

    public static /* synthetic */ void $r8$lambda$p9mTvRLoGJ7rbn203IyH1G0Cuos(FragmentContextView r0, DialogInterface r1) {
        r0.lambda$checkCreateView$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$wqiwxkQpbwkQRKtzdy6zGHkC0Yw(FragmentContextView r0, LocationController.SharingLocationInfo r1) {
        r0.openSharingLocation(r1);
    }

    public static /* synthetic */ void $r8$lambda$yGALdsHYD4QKXLurpCYVeVgaNVI(FragmentContextView r0) {
        r0.lambda$startJoinFlickerAnimation$16();
    }

    public static /* synthetic */ void $r8$lambda$z7sOy2HZI8Lqwt6yFX12eiUJ64c(View r1) {
        MediaController.getInstance().updateSilent(false);
    }

    /* renamed from: -$$Nest$fgetanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m10751$$Nest$fgetanimatorSet(FragmentContextView r0) {
        return r0.animatorSet;
    }

    /* renamed from: -$$Nest$fgetavatars, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AvatarsImageView m10752$$Nest$fgetavatars(FragmentContextView r0) {
        return r0.avatars;
    }

    /* renamed from: -$$Nest$fgetcapsuleBlobDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CapsuleBlobDrawable m10753$$Nest$fgetcapsuleBlobDrawable(FragmentContextView r0) {
        return r0.capsuleBlobDrawable;
    }

    /* renamed from: -$$Nest$fgetchatActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityInterface m10754$$Nest$fgetchatActivity(FragmentContextView r0) {
        return r0.chatActivity;
    }

    /* renamed from: -$$Nest$fgetcheckCallAfterAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10755$$Nest$fgetcheckCallAfterAnimation(FragmentContextView r0) {
        return r0.checkCallAfterAnimation;
    }

    /* renamed from: -$$Nest$fgetcheckImportAfterAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10756$$Nest$fgetcheckImportAfterAnimation(FragmentContextView r0) {
        return r0.checkImportAfterAnimation;
    }

    /* renamed from: -$$Nest$fgetcheckLiveStoryAfterAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10757$$Nest$fgetcheckLiveStoryAfterAnimation(FragmentContextView r0) {
        return r0.checkLiveStoryAfterAnimation;
    }

    /* renamed from: -$$Nest$fgetcheckLocationRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m10758$$Nest$fgetcheckLocationRunnable(FragmentContextView r0) {
        return r0.checkLocationRunnable;
    }

    /* renamed from: -$$Nest$fgetcheckPlayerAfterAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10759$$Nest$fgetcheckPlayerAfterAnimation(FragmentContextView r0) {
        return r0.checkPlayerAfterAnimation;
    }

    /* renamed from: -$$Nest$fgetcurrentStyle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10760$$Nest$fgetcurrentStyle(FragmentContextView r0) {
        return r0.currentStyle;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FragmentContextViewDelegate m10761$$Nest$fgetdelegate(FragmentContextView r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetfragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m10762$$Nest$fgetfragment(FragmentContextView r0) {
        return r0.fragment;
    }

    /* renamed from: -$$Nest$fgetframeLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m10763$$Nest$fgetframeLayout(FragmentContextView r0) {
        return r0.frameLayout;
    }

    /* renamed from: -$$Nest$fgetgradientPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m10764$$Nest$fgetgradientPaint(FragmentContextView r0) {
        return r0.gradientPaint;
    }

    /* renamed from: -$$Nest$fgetgradientTextPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextPaint m10765$$Nest$fgetgradientTextPaint(FragmentContextView r0) {
        return r0.gradientTextPaint;
    }

    /* renamed from: -$$Nest$fgetgradientWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10766$$Nest$fgetgradientWidth(FragmentContextView r0) {
        return r0.gradientWidth;
    }

    /* renamed from: -$$Nest$fgetisMuted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10767$$Nest$fgetisMuted(FragmentContextView r0) {
        return r0.isMuted;
    }

    /* renamed from: -$$Nest$fgetjoinButtonFlicker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CellFlickerDrawable m10768$$Nest$fgetjoinButtonFlicker(FragmentContextView r0) {
        return r0.joinButtonFlicker;
    }

    /* renamed from: -$$Nest$fgetjoinButtonWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10769$$Nest$fgetjoinButtonWidth(FragmentContextView r0) {
        return r0.joinButtonWidth;
    }

    /* renamed from: -$$Nest$fgetlinearGradient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearGradient m10770$$Nest$fgetlinearGradient(FragmentContextView r0) {
        return r0.linearGradient;
    }

    /* renamed from: -$$Nest$fgetmatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Matrix m10771$$Nest$fgetmatrix(FragmentContextView r0) {
        return r0.matrix;
    }

    /* renamed from: -$$Nest$fgetmuteButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieImageView m10772$$Nest$fgetmuteButton(FragmentContextView r0) {
        return r0.muteButton;
    }

    /* renamed from: -$$Nest$fgetmuteDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieDrawable m10773$$Nest$fgetmuteDrawable(FragmentContextView r0) {
        return r0.muteDrawable;
    }

    /* renamed from: -$$Nest$fgetnotificationsLocker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationNotificationsLocker m10774$$Nest$fgetnotificationsLocker(FragmentContextView r0) {
        return r0.notificationsLocker;
    }

    /* renamed from: -$$Nest$fgetnotificationsLocker2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationNotificationsLocker m10775$$Nest$fgetnotificationsLocker2(FragmentContextView r0) {
        return r0.notificationsLocker2;
    }

    /* renamed from: -$$Nest$fgetnotifyButtonBounce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ButtonBounce m10776$$Nest$fgetnotifyButtonBounce(FragmentContextView r0) {
        return r0.notifyButtonBounce;
    }

    /* renamed from: -$$Nest$fgetnotifyButtonEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10777$$Nest$fgetnotifyButtonEnabled(FragmentContextView r0) {
        return r0.notifyButtonEnabled;
    }

    /* renamed from: -$$Nest$fgetnotifyText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView.AnimatedTextDrawable m10778$$Nest$fgetnotifyText(FragmentContextView r0) {
        return r0.notifyText;
    }

    /* renamed from: -$$Nest$fgetspeedHintViewParent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m10779$$Nest$fgetspeedHintViewParent(FragmentContextView r0) {
        return r0.speedHintViewParent;
    }

    /* renamed from: -$$Nest$fgettitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AudioPlayerAlert.ClippingTextViewSwitcher m10780$$Nest$fgettitleTextView(FragmentContextView r0) {
        return r0.titleTextView;
    }

    /* renamed from: -$$Nest$fgetupdateScheduleTimeRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m10781$$Nest$fgetupdateScheduleTimeRunnable(FragmentContextView r0) {
        return r0.updateScheduleTimeRunnable;
    }

    /* renamed from: -$$Nest$fgetwillBeNotified, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10782$$Nest$fgetwillBeNotified(FragmentContextView r0) {
        return r0.willBeNotified;
    }

    /* renamed from: -$$Nest$fputanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10783$$Nest$fputanimatorSet(FragmentContextView r0, AnimatorSet r1) {
        r0.animatorSet = r1;
    }

    /* renamed from: -$$Nest$fputcheckCallAfterAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10784$$Nest$fputcheckCallAfterAnimation(FragmentContextView r0, boolean r1) {
        r0.checkCallAfterAnimation = r1;
    }

    /* renamed from: -$$Nest$fputcheckImportAfterAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10785$$Nest$fputcheckImportAfterAnimation(FragmentContextView r0, boolean r1) {
        r0.checkImportAfterAnimation = r1;
    }

    /* renamed from: -$$Nest$fputcheckLiveStoryAfterAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10786$$Nest$fputcheckLiveStoryAfterAnimation(FragmentContextView r0, boolean r1) {
        r0.checkLiveStoryAfterAnimation = r1;
    }

    /* renamed from: -$$Nest$fputcheckPlayerAfterAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10787$$Nest$fputcheckPlayerAfterAnimation(FragmentContextView r0, boolean r1) {
        r0.checkPlayerAfterAnimation = r1;
    }

    /* renamed from: -$$Nest$fputgradientWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10788$$Nest$fputgradientWidth(FragmentContextView r0, int r1) {
        r0.gradientWidth = r1;
    }

    /* renamed from: -$$Nest$fputisMuted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10789$$Nest$fputisMuted(FragmentContextView r0, boolean r1) {
        r0.isMuted = r1;
    }

    /* renamed from: -$$Nest$fputjoinButtonWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10790$$Nest$fputjoinButtonWidth(FragmentContextView r0, int r1) {
        r0.joinButtonWidth = r1;
    }

    /* renamed from: -$$Nest$fputlinearGradient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10791$$Nest$fputlinearGradient(FragmentContextView r0, LinearGradient r1) {
        r0.linearGradient = r1;
    }

    /* renamed from: -$$Nest$fputnotifyButtonEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10792$$Nest$fputnotifyButtonEnabled(FragmentContextView r0, boolean r1) {
        r0.notifyButtonEnabled = r1;
    }

    /* renamed from: -$$Nest$fputscheduleRunnableScheduled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10793$$Nest$fputscheduleRunnableScheduled(FragmentContextView r0, boolean r1) {
        r0.scheduleRunnableScheduled = r1;
    }

    /* renamed from: -$$Nest$fputvisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10794$$Nest$fputvisible(FragmentContextView r0, boolean r1) {
        r0.visible = r1;
    }

    /* renamed from: -$$Nest$mcheckLiveStory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10795$$Nest$mcheckLiveStory(FragmentContextView r0, boolean r1) {
        r0.checkLiveStory(r1);
    }

    /* renamed from: -$$Nest$mcheckLocationString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10796$$Nest$mcheckLocationString(FragmentContextView r0) {
        r0.checkLocationString();
    }

    /* renamed from: -$$Nest$mcheckPlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10797$$Nest$mcheckPlayer(FragmentContextView r0, boolean r1) {
        r0.checkPlayer(r1);
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10798$$Nest$mgetThemedColor(FragmentContextView r0, int r1) {
        return r0.getThemedColor(r1);
    }

    /* renamed from: -$$Nest$mstartJoinFlickerAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10799$$Nest$mstartJoinFlickerAnimation(FragmentContextView r0) {
        r0.startJoinFlickerAnimation();
    }

    static {
        speeds = new float[]{0.5f, 1.0f, 1.2f, 1.5f, 1.7f, 2.0f};
    }

    public FragmentContextView(Context r8, BaseFragment r9, View r10, boolean r11, Theme.ResourcesProvider r12) {
        this(r8, r9, r10, r11, r12, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FragmentContextView(Context r7, BaseFragment r8, View r9, boolean r10, Theme.ResourcesProvider r11, boolean r12) {
        super(r7);
        this.capsuleBlobDrawable = new CapsuleBlobDrawable();
        this.speedItems = new ActionBarMenuItem.Item[6];
        this.currentProgress = -1;
        this.currentStyle = -1;
        this.supportsCalls = true;
        this.notifyText = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.updateScheduleTimeRunnable = new AnonymousClass1(this);
        this.account = UserConfig.selectedAccount;
        this.lastLocationSharingCount = -1;
        this.checkLocationRunnable = new AnonymousClass2(this);
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.notificationsLocker2 = new AnimationNotificationsLocker(new int[]{NotificationCenter.messagesDidLoad});
        this.observersGroup = new NotificationCenter.ObserversGroup[16];
        this.progressPaint = new Paint(1);
        this.toggleGroupCallStartSubscriptionReqId = 0;
        this.callMessagesAnimator = new ReplaceAnimator(new FragmentContextView$$ExternalSyntheticLambda13(this), CubicBezierInterpolator.EASE_OUT_QUINT, 450);
        this.groupCallMessageCounter = 0;
        this.resourcesProvider = r11;
        this.isSideMenued = r12;
        this.fragment = r8;
        if ((r8 instanceof ChatActivityInterface) == false) goto L5;
        this.chatActivity = (ChatActivityInterface) r8;
    L5:
        this.applyingView = r9;
        this.visible = true;
        this.isLocation = r10;
        if (r9 != null) goto L8;
        ((ViewGroup) r8.getFragmentView()).setClipToPadding(false);
    L8:
        setTag(1);
    }

    public FragmentContextView(Context r7, BaseFragment r8, boolean r9) {
        this(r7, r8, null, r9, null);
    }

    public FragmentContextView(Context r7, BaseFragment r8, boolean r9, Theme.ResourcesProvider r10) {
        this(r7, r8, null, r9, r10);
    }

    private void checkCreateView() {
        if (this.frameLayout == null) goto L5;
        return;
    L5:
        Context r1 = getContext();
        AnonymousClass3 r2 = new AnonymousClass3(this, r1);
        this.frameLayout = r2;
        this.notifyButtonBounce = new ButtonBounce(r2);
        this.notifyText.setOverrideFullWidth(AndroidUtilities.displaySize.x);
        this.notifyText.setScaleProperty(0.4f);
        this.notifyText.setCallback(this.frameLayout);
        this.notifyText.setTextColor(-1);
        this.notifyText.setTextSize(AndroidUtilities.dp(14.0f));
        this.notifyText.setTypeface(AndroidUtilities.bold());
        addView(this.frameLayout, LayoutHelper.createFrame(-1, 36.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        View r22 = new View(r1);
        this.selector = r22;
        this.frameLayout.addView(r22, LayoutHelper.createFrame(-1, -1.0f));
        ImageView r23 = new ImageView(r1);
        this.playButton = r23;
        ImageView.ScaleType r3 = ImageView.ScaleType.CENTER;
        r23.setScaleType(r3);
        ImageView r24 = this.playButton;
        int r6 = Theme.key_inappPlayerPlayPause;
        int r7 = getThemedColor(r6);
        PorterDuff.Mode r8 = PorterDuff.Mode.MULTIPLY;
        r24.setColorFilter(new PorterDuffColorFilter(r7, r8));
        ImageView r25 = this.playButton;
        PlayPauseDrawable r5 = new PlayPauseDrawable(16);
        this.playPauseDrawable = r5;
        r25.setImageDrawable(r5);
        this.playButton.setBackground(Theme.createSelectorDrawable(getThemedColor(r6) & 436207615, 1, AndroidUtilities.dp(14.0f)));
        addView(this.playButton, LayoutHelper.createFrame(36, 36, 51));
        this.playButton.setOnClickListener(new FragmentContextView$$ExternalSyntheticLambda5(this));
        RLottieImageView r26 = new RLottieImageView(r1);
        this.importingImageView = r26;
        r26.setScaleType(r3);
        this.importingImageView.setAutoRepeat(true);
        this.importingImageView.setAnimation(R.raw.import_progress, 30, 30);
        this.importingImageView.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(22.0f), getThemedColor(r6)));
        addView(this.importingImageView, LayoutHelper.createFrame(22, 22.0f, 51, 7.0f, 7.0f, 0.0f, 0.0f));
        AnonymousClass4 r27 = new AnonymousClass4(this, r1, r1);
        this.titleTextView = r27;
        int r11 = 64;
        if (this.isSideMenued == false) goto L8;
        int r62 = 64;
    L9:
        addView(r27, LayoutHelper.createFrame(-1, 36.0f, 51, 35.0f, 0.0f, r62 + 36, 0.0f));
        AnonymousClass5 r28 = new AnonymousClass5(this, r1, r1);
        this.subtitleTextView = r28;
        if (this.isSideMenued == true) goto L13;
        r11 = 0;
    L13:
        addView(r28, LayoutHelper.createFrame(-1, 36.0f, 51, 35.0f, 10.0f, r11 + 36, 0.0f));
        CellFlickerDrawable r29 = new CellFlickerDrawable();
        this.joinButtonFlicker = r29;
        r29.setProgress(1.0f);
        this.joinButtonFlicker.repeatEnabled = false;
        AnonymousClass6 r210 = new AnonymousClass6(this, r1);
        this.joinButton = r210;
        r210.setText(LocaleController.getString(R.string.VoipChatJoin));
        this.joinButton.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
        this.joinButton.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(16.0f), getThemedColor(Theme.key_featuredStickers_addButton), getThemedColor(Theme.key_featuredStickers_addButtonPressed)));
        this.joinButton.setTextSize(1, 14.0f);
        this.joinButton.setTypeface(AndroidUtilities.bold());
        this.joinButton.setGravity(17);
        this.joinButton.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), 0);
        addView(this.joinButton, LayoutHelper.createFrame(-2, 28.0f, 53, 0.0f, 10.0f, 14.0f, 0.0f));
        this.joinButton.setOnClickListener(new FragmentContextView$$ExternalSyntheticLambda6(this));
        if (this.flickOnAttach == false) goto L16;
        startJoinFlickerAnimation();
    L16:
        this.silentButton = new FrameLayout(r1);
        ImageView r211 = new ImageView(r1);
        this.silentButtonImage = r211;
        r211.setImageResource(R.drawable.msg_mute);
        ImageView r212 = this.silentButtonImage;
        int r14 = Theme.key_inappPlayerClose;
        r212.setColorFilter(new PorterDuffColorFilter(getThemedColor(r14), r8));
        this.silentButton.addView(this.silentButtonImage, LayoutHelper.createFrame(20, 20, 17));
        this.silentButton.setBackground(Theme.createSelectorDrawable(getThemedColor(r14) & 436207615, 1, AndroidUtilities.dp(14.0f)));
        this.silentButton.setContentDescription(LocaleController.getString(R.string.Unmute));
        this.silentButton.setOnClickListener(new FragmentContextView$$ExternalSyntheticLambda7());
        this.silentButton.setVisibility(8);
        addView(this.silentButton, LayoutHelper.createFrame(36, 36.0f, 53, 0.0f, 0.0f, 36.0f, 0.0f));
        if (this.isLocation == true) goto L19;
        createPlaybackSpeedButton();
    L19:
        AvatarsImageView r213 = new AvatarsImageView(r1, false);
        this.avatars = r213;
        r213.setAvatarsTextSize(AndroidUtilities.dp(21.0f));
        this.avatars.setDelegate(new FragmentContextView$$ExternalSyntheticLambda8(this));
        this.avatars.setVisibility(8);
        addView(this.avatars, LayoutHelper.createFrame(108, 36, 51));
        this.muteDrawable = new RLottieDrawable(R.raw.voice_muted, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(20.0f), true, null);
        AnonymousClass7 r214 = new AnonymousClass7(this, r1);
        this.muteButton = r214;
        r214.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_returnToCallText), PorterDuff.Mode.SRC_IN));
        this.muteButton.setBackground(Theme.createSelectorDrawable(getThemedColor(r14) & 436207615, 1, AndroidUtilities.dp(14.0f)));
        this.muteButton.setAnimation(this.muteDrawable);
        this.muteButton.setScaleType(r3);
        this.muteButton.setVisibility(8);
        addView(this.muteButton, LayoutHelper.createFrame(36, 36.0f, 53, 0.0f, 0.0f, 2.0f, 0.0f));
        this.muteButton.setOnClickListener(new FragmentContextView$$ExternalSyntheticLambda9(this));
        ImageView r215 = new ImageView(r1);
        this.closeButton = r215;
        r215.setImageResource(R.drawable.miniplayer_close);
        this.closeButton.setColorFilter(new PorterDuffColorFilter(getThemedColor(r14), r8));
        this.closeButton.setBackground(Theme.createSelectorDrawable(getThemedColor(r14) & 436207615, 1, AndroidUtilities.dp(14.0f)));
        this.closeButton.setScaleType(r3);
        addView(this.closeButton, LayoutHelper.createFrame(36, 36.0f, 53, 0.0f, 0.0f, 4.0f, 0.0f));
        this.closeButton.setOnClickListener(new FragmentContextView$$ExternalSyntheticLambda10(this));
        AnonymousClass8 r12 = new AnonymousClass8(this, getContext());
        this.groupCallMessagesContainer = r12;
        addView(r12, LayoutHelper.createFrame(-1, -2.0f, 48, 96.0f, 3.0f, 96.0f, 0.0f));
        setOnLongClickListener(new FragmentContextView$$ExternalSyntheticLambda11(this));
        setOnClickListener(new FragmentContextView$$ExternalSyntheticLambda12(this));
        setLeftMargin(this.leftMargin);
        return;
    L8:
        r62 = 0;
        goto L9
    }

    private void checkLiveLocation(boolean r12) {
        View r0 = this.fragment.getFragmentView();
        if (r12 == true) goto L10;
        if (r0 == null) goto L10;
        if (r0.getParent() != null) goto L8;
    L9:
        r12 = true;
        goto L10
    L8:
        if (((View) r0.getParent()).getVisibility() != 0) goto L9;
    L10:
        BaseFragment r02 = this.fragment;
        if ((r02 instanceof DialogsActivity) == true) goto L13;
        boolean r03 = LocationController.getInstance(r02.getCurrentAccount()).isSharingLocation(this.chatActivity.getDialogId());
    L18:
        if (r03 == true) goto L33;
        this.lastLocationSharingCount = -1;
        AndroidUtilities.cancelRunOnUIThread(this.checkLocationRunnable);
        if (this.visible == false) goto L81;
        this.visible = false;
        if (r12 == true) goto L24;
        AnimatorSet r122 = this.animatorSet;
        if (r122 == null) goto L31;
        r122.cancel();
        this.animatorSet = null;
    L31:
        AnimatorSet r123 = new AnimatorSet();
        this.animatorSet = r123;
        r123.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "topPadding", new float[]{0.0f})});
        this.animatorSet.setDuration(200);
        this.animatorSet.addListener(new AnonymousClass10(this));
        this.animatorSet.start();
        return;
    L24:
        if (getVisibility() == 8) goto L26;
        setVisibility(8);
    L26:
        setTopPadding(0.0f);
        return;
    L81:
        return;
    L33:
        checkCreateView();
        updateStyle(2);
        this.playButton.setImageDrawable(new ShareLocationDrawable(getContext(), 1));
        if (r12 == false) goto L39;
        if (this.topPadding != 0.0f) goto L39;
        setTopPadding(AndroidUtilities.dp2(getStyleHeight()));
    L39:
        if (this.visible == true) goto L47;
        if (r12 == true) goto L45;
        AnimatorSet r124 = this.animatorSet;
        if (r124 == null) goto L44;
        r124.cancel();
        this.animatorSet = null;
    L44:
        AnimatorSet r125 = new AnimatorSet();
        this.animatorSet = r125;
        r125.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "topPadding", new float[]{AndroidUtilities.dp2(getStyleHeight())})});
        this.animatorSet.setDuration(200);
        this.animatorSet.addListener(new AnonymousClass11(this));
        this.animatorSet.start();
    L45:
        this.visible = true;
        setVisibility(0);
    L47:
        if ((this.fragment instanceof DialogsActivity) == false) goto L75;
        String r126 = LocaleController.getString(R.string.LiveLocationContext);
        ArrayList r2 = new ArrayList();
        int r4 = 0;
    L50:
        if (r4 >= 16) goto L53;
        r2.addAll(LocationController.getInstance(r4).sharingLocationsUI);
        r4 = r4 + 1;
        goto L50
    L53:
        if (r2.size() != 1) goto L62;
        LocationController.SharingLocationInfo r1 = (LocationController.SharingLocationInfo) r2.get(0);
        long r42 = r1.messageObject.getDialogId();
        boolean r22 = DialogObject.isUserDialog(r42);
        MessageObject r13 = r1.messageObject;
        if (r22 == false) goto L57;
        String r14 = UserObject.getFirstName(MessagesController.getInstance(r13.currentAccount).getUser(Long.valueOf(r42)));
        String r23 = LocaleController.getString(R.string.AttachLiveLocationIsSharing);
    L63:
        String r15 = String.format(r23, new Object[]{r126, r14});
        int r24 = r15.indexOf(r126);
        SpannableStringBuilder r43 = new SpannableStringBuilder(r15);
        int r16 = 0;
    L64:
        if (r16 >= 2) goto L73;
        AudioPlayerAlert.ClippingTextViewSwitcher r5 = this.titleTextView;
        if (r16 != 0) goto L68;
        TextView r52 = r5.getTextView();
    L69:
        if (r52 == null) goto L72;
        r52.setEllipsize(TextUtils.TruncateAt.END);
    L72:
        r16 = r16 + 1;
        goto L64
    L68:
        r52 = r5.getNextTextView();
        goto L69
    L73:
        r43.setSpan(new TypefaceSpan(AndroidUtilities.bold(), 0, getThemedColor(Theme.key_inappPlayerPerformer)), r24, r126.length() + r24, 18);
        this.titleTextView.setText(r43, false);
        return;
    L57:
        TLRPC.Chat r17 = MessagesController.getInstance(r13.currentAccount).getChat(Long.valueOf(-r42));
        if (r17 == null) goto L60;
        r14 = r17.title;
    L61:
        r23 = LocaleController.getString(R.string.AttachLiveLocationIsSharingChat);
        goto L63
    L60:
        r14 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L61
    L62:
        r14 = LocaleController.formatPluralString("Chats", r2.size(), new Object[0]);
        r23 = LocaleController.getString(R.string.AttachLiveLocationIsSharingChats);
        goto L63
    L75:
        this.checkLocationRunnable.run();
        checkLocationString();
        return;
    L13:
        if (LocationController.getLocationsCount() == 0) goto L15;
        r03 = true;
        goto L18
    L15:
        r03 = false;
        goto L18
    }

    private void checkLiveStory(boolean r13) {
        View r0 = this.fragment.getFragmentView();
        if (r13 == true) goto L11;
        if (r0 == null) goto L11;
        if (r0.getParent() != null) goto L8;
    L9:
        r13 = true;
        goto L11
    L8:
        if (((View) r0.getParent()).getVisibility() != 0) goto L9;
    L11:
        if (LivePlayer.recording == null) goto L38;
        checkCreateView();
        int r02 = this.currentStyle;
        if (6 != r02) goto L15;
    L19:
        if (6 != r02) goto L21;
    L28:
        updateStyle(6);
        if (this.visible == true) goto L37;
        if (r13 == true) goto L35;
        AnimatorSet r132 = this.animatorSet;
        if (r132 == null) goto L34;
        r132.cancel();
        this.animatorSet = null;
    L34:
        this.animatorSet = new AnimatorSet();
        this.notificationsLocker2.lock();
        this.animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "topPadding", new float[]{AndroidUtilities.dp2(getStyleHeight())})});
        this.animatorSet.setDuration(220);
        this.animatorSet.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animatorSet.addListener(new AnonymousClass18(this));
        this.animatorSet.start();
    L36:
        this.visible = true;
        setVisibility(0);
    L59:
        LivePlayer r133 = LivePlayer.recording;
        if (r133 != null) goto L62;
        return;
    L62:
        if (this.currentStyle != 6) goto L66;
        this.titleTextView.setText(LocaleController.formatPluralStringComma("LiveStoryTopPanelWatching", r133.getWatchersCount()));
        return;
    L66:
        return;
    L35:
        setTopPadding(AndroidUtilities.dp2(getStyleHeight()));
        startJoinFlickerAnimation();
        goto L36
    L37:
        setTopPadding(AndroidUtilities.dp2(getStyleHeight()));
        setVisibility(0);
        goto L59
    L21:
        if (this.visible == false) goto L28;
        if (r13 == true) goto L28;
        AnimatorSet r134 = this.animatorSet;
        if (r134 == null) goto L26;
        r134.cancel();
        this.animatorSet = null;
    L26:
        this.notificationsLocker.lock();
        AnimatorSet r135 = new AnimatorSet();
        this.animatorSet = r135;
        r135.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "topPadding", new float[]{0.0f})});
        this.animatorSet.setDuration(220);
        this.animatorSet.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animatorSet.addListener(new AnonymousClass17(this));
        this.animatorSet.start();
        return;
    L15:
        if (this.animatorSet == null) goto L19;
        if (r13 == true) goto L19;
        this.checkLiveStoryAfterAnimation = true;
        return;
    L38:
        boolean r03 = this.visible;
        if (r03 == false) goto L55;
        if (r13 == false) goto L44;
        if (this.currentStyle != (-1)) goto L44;
    L45:
        this.visible = false;
        if (r13 == true) goto L48;
        AnimatorSet r136 = this.animatorSet;
        if (r136 == null) goto L54;
        r136.cancel();
        this.animatorSet = null;
    L54:
        this.notificationsLocker.lock();
        AnimatorSet r137 = new AnimatorSet();
        this.animatorSet = r137;
        r137.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "topPadding", new float[]{0.0f})});
        this.animatorSet.setDuration(220);
        this.animatorSet.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animatorSet.addListener(new AnonymousClass16(this));
        this.animatorSet.start();
        goto L59
    L48:
        if (getVisibility() == 8) goto L50;
        setVisibility(8);
    L50:
        setTopPadding(0.0f);
    L44:
        if (this.currentStyle == 6) goto L45;
    L55:
        if (r03 == false) goto L59;
        if (this.currentStyle != (-1)) goto L59;
        this.visible = false;
        setVisibility(8);
        goto L59
    }

    private void checkLocationString() {
        if (this.chatActivity != null) goto L5;
        return;
    L5:
        if (this.titleTextView == null) goto L71;
        checkCreateView();
        long r0 = this.chatActivity.getDialogId();
        int r2 = this.fragment.getCurrentAccount();
        ArrayList<TLRPC.Message> r3 = LocationController.getInstance(r2).locationsCache.get(r0);
        if (this.firstLocationsLoaded == true) goto L10;
        LocationController.getInstance(r2).loadLiveLocations(r0);
        this.firstLocationsLoaded = true;
    L10:
        TLRPC.User r6 = null;
        if (r3 == null) goto L27;
        long r7 = UserConfig.getInstance(r2).getClientUserId();
        int r9 = ConnectionsManager.getInstance(r2).getCurrentTime();
        int r10 = 0;
        int r11 = 0;
    L14:
        if (r10 >= r3.size()) goto L29;
        TLRPC.Message r12 = r3.get(r10);
        TLRPC.MessageMedia r13 = r12.media;
        if (r13 == null) goto L26;
        if ((r12.date + r13.period) <= r9) goto L26;
        long r122 = MessageObject.getFromChatId(r12);
        if (r6 == null) goto L23;
    L25:
        r11 = r11 + 1;
        goto L26
    L23:
        if (r122 == r7) goto L25;
        r6 = MessagesController.getInstance(r2).getUser(Long.valueOf(r122));
    L26:
        r10 = r10 + 1;
    L29:
        if (this.lastLocationSharingCount == r11) goto L72;
        this.lastLocationSharingCount = r11;
        String r32 = LocaleController.getString(R.string.LiveLocationContext);
        if (r11 != 0) goto L34;
        String r02 = r32;
    L46:
        if (r02.equals(this.lastString) == true) goto L73;
        this.lastString = r02;
        int r1 = r02.indexOf(r32);
        SpannableStringBuilder r22 = new SpannableStringBuilder(r02);
        int r03 = 0;
    L50:
        if (r03 >= 2) goto L59;
        AudioPlayerAlert.ClippingTextViewSwitcher r5 = this.titleTextView;
        if (r03 != 0) goto L54;
        TextView r52 = r5.getTextView();
    L55:
        if (r52 == null) goto L58;
        r52.setEllipsize(TextUtils.TruncateAt.END);
    L58:
        r03 = r03 + 1;
        goto L50
    L54:
        r52 = r5.getNextTextView();
        goto L55
    L59:
        if (r1 < 0) goto L61;
        r22.setSpan(new TypefaceSpan(AndroidUtilities.bold(), 0, getThemedColor(Theme.key_inappPlayerPerformer)), r1, r32.length() + r1, 18);
    L61:
        this.titleTextView.setText(r22, false);
        return;
    L73:
        return;
    L34:
        int r112 = r11 - 1;
        if (LocationController.getInstance(r2).isSharingLocation(r0) == false) goto L42;
        if (r112 == 0) goto L41;
        if (r112 != 1) goto L40;
        if (r6 == null) goto L40;
        r02 = String.format("%1$s - %2$s", new Object[]{r32, LocaleController.formatString("SharingYouAndOtherName", R.string.SharingYouAndOtherName, new Object[]{UserObject.getFirstName(r6)})});
    L40:
        r02 = String.format("%1$s - %2$s %3$s", new Object[]{r32, LocaleController.getString(R.string.ChatYourSelfName), LocaleController.formatPluralString("AndOther", r112, new Object[0])});
        goto L46
    L41:
        r02 = String.format("%1$s - %2$s", new Object[]{r32, LocaleController.getString(R.string.ChatYourSelfName)});
        goto L46
    L42:
        if (r112 == 0) goto L44;
        r02 = String.format("%1$s - %2$s %3$s", new Object[]{r32, UserObject.getFirstName(r6), LocaleController.formatPluralString("AndOther", r112, new Object[0])});
        goto L46
    L44:
        r02 = String.format("%1$s - %2$s", new Object[]{r32, UserObject.getFirstName(r6)});
        goto L46
    L72:
        return;
    L27:
        r11 = 0;
        goto L29
    }

    private void checkPlayer(boolean r14) {
        boolean r1 = true;
        if (this.visible == false) goto L15;
        int r0 = this.currentStyle;
        if (r0 != 1) goto L7;
        return;
    L7:
        if (r0 != 3) goto L9;
        return;
    L9:
        if (r0 == 4) goto L13;
        if (r0 != 5) goto L15;
    L13:
        if (isPlayingVoice() == true) goto L15;
        return;
    L15:
        MessageObject r02 = MediaController.getInstance().getPlayingMessageObject();
        View r2 = this.fragment.getFragmentView();
        if (r14 == true) goto L23;
        if (r2 == null) goto L23;
        if (r2.getParent() != null) goto L21;
    L22:
        r14 = true;
        goto L23
    L21:
        if (((View) r2.getParent()).getVisibility() != 0) goto L22;
    L23:
        boolean r22 = this.visible;
        if (r02 != null) goto L26;
    L109:
        this.lastMessageObject = null;
        if (this.supportsCalls == true) goto L112;
    L120:
        boolean r03 = false;
    L122:
        if (isPlayingVoice() == true) goto L134;
        if (r03 == true) goto L134;
        if (this.chatActivity == null) goto L134;
        if (GroupCallPip.isShowing() == true) goto L134;
        ChatObject.Call r04 = this.chatActivity.getGroupCall();
        if (r04 != null) goto L131;
    L133:
        r03 = false;
        goto L134
    L131:
        if (r04.shouldShowPanel() == false) goto L133;
        r03 = true;
    L134:
        if (r03 == false) goto L138;
        checkCall(false);
        return;
    L138:
        if (this.visible == false) goto L159;
        ActionBarMenuItem r05 = this.playbackSpeedButton;
        if (r05 != null) goto L142;
    L144:
        this.visible = false;
        if (r14 == true) goto L147;
        AnimatorSet r142 = this.animatorSet;
        if (r142 == null) goto L154;
        r142.cancel();
        this.animatorSet = null;
    L154:
        this.notificationsLocker.lock();
        AnimatorSet r143 = new AnimatorSet();
        this.animatorSet = r143;
        r143.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "topPadding", new float[]{0.0f})});
        this.animatorSet.setDuration(200);
        FragmentContextViewDelegate r144 = this.delegate;
        if (r144 == null) goto L157;
        r144.onAnimation(true, false);
    L157:
        this.animatorSet.addListener(new AnonymousClass12(this));
        this.animatorSet.start();
        return;
    L147:
        if (getVisibility() == 8) goto L149;
        setVisibility(8);
    L149:
        setTopPadding(0.0f);
        return;
    L142:
        if (r05.isSubMenuShowing() == false) goto L144;
        this.playbackSpeedButton.toggleSubMenu();
        goto L144
    L159:
        setVisibility(8);
        return;
    L112:
        if (VoIPService.getSharedInstance() == null) goto L120;
        if (VoIPService.getSharedInstance().isHangingUp() == true) goto L120;
        if (VoIPService.getSharedInstance().getCallState() == 15) goto L120;
        if (GroupCallPip.isShowing() == true) goto L120;
        r03 = true;
        goto L122
    L26:
        if (r02.getId() == 0) goto L109;
        if (r02.isVideo() == true) goto L109;
        checkCreateView();
        int r9 = this.currentStyle;
        if (r9 != 0) goto L33;
    L37:
        updateStyle(0);
        if (r14 == false) goto L45;
        if (this.topPadding != 0.0f) goto L45;
        setTopPadding(AndroidUtilities.dp2(getStyleHeight()));
        FragmentContextViewDelegate r10 = this.delegate;
        if (r10 == null) goto L45;
        r10.onAnimation(true, true);
        this.delegate.onAnimation(false, true);
    L45:
        if (this.visible == true) goto L55;
        if (r14 == true) goto L54;
        AnimatorSet r102 = this.animatorSet;
        if (r102 == null) goto L50;
        r102.cancel();
        this.animatorSet = null;
    L50:
        this.notificationsLocker.lock();
        this.animatorSet = new AnimatorSet();
        FragmentContextViewDelegate r6 = this.delegate;
        if (r6 == null) goto L53;
        r6.onAnimation(true, true);
    L53:
        this.animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "topPadding", new float[]{AndroidUtilities.dp2(getStyleHeight())})});
        this.animatorSet.setDuration(200);
        this.animatorSet.addListener(new AnonymousClass13(this));
        this.animatorSet.start();
    L54:
        this.visible = true;
        setVisibility(0);
    L55:
        boolean r3 = MediaController.getInstance().isMessagePaused();
        PlayPauseDrawable r4 = this.playPauseDrawable;
        if (r3 == false) goto L58;
        r4.setPause(false, !r14);
        this.playButton.setContentDescription(LocaleController.getString(R.string.AccActionPlay));
    L60:
        if (this.lastMessageObject != r02) goto L64;
        if (r9 != 0) goto L64;
        return;
    L64:
        this.lastMessageObject = r02;
        if (r02.isVoice() == false) goto L67;
    L86:
        this.isMusic = false;
        ActionBarMenuItem r32 = this.playbackSpeedButton;
        if (r32 == null) goto L89;
        r32.setAlpha(1.0f);
        this.playbackSpeedButton.setEnabled(true);
    L89:
        this.titleTextView.setPadding(0, 0, AndroidUtilities.dp(44.0f) + this.joinButtonWidth, 0);
        SpannableStringBuilder r33 = new SpannableStringBuilder(String.format("%s %s", new Object[]{r02.getMusicAuthor(), r02.getMusicTitle()}));
        int r5 = 0;
    L90:
        if (r5 >= 2) goto L99;
        AudioPlayerAlert.ClippingTextViewSwitcher r62 = this.titleTextView;
        if (r5 != 0) goto L94;
        TextView r63 = r62.getTextView();
    L95:
        if (r63 == null) goto L98;
        r63.setEllipsize(TextUtils.TruncateAt.MIDDLE);
    L98:
        r5 = r5 + 1;
        goto L90
    L94:
        r63 = r62.getNextTextView();
        goto L95
    L99:
        updatePlaybackButton(false);
    L100:
        r33.setSpan(new TypefaceSpan(AndroidUtilities.bold(), 0, getThemedColor(Theme.key_inappPlayerPerformer)), 0, r02.getMusicAuthor().length(), 18);
        AudioPlayerAlert.ClippingTextViewSwitcher r06 = this.titleTextView;
        if (r14 == true) goto L106;
        if (r22 == false) goto L106;
        if (this.isMusic == false) goto L106;
    L107:
        r06.setText(r33, r1);
        return;
    L106:
        r1 = false;
        goto L107
    L67:
        if (this.lastMessageObject.isRoundVideo() == true) goto L86;
        this.isMusic = true;
        if (this.playbackSpeedButton == null) goto L75;
        double r92 = r02.getDuration();
        ActionBarMenuItem r93 = this.playbackSpeedButton;
        if (r92 < 600.0d) goto L74;
        r93.setAlpha(1.0f);
        this.playbackSpeedButton.setEnabled(true);
        this.titleTextView.setPadding(0, 0, AndroidUtilities.dp(44.0f) + this.joinButtonWidth, 0);
        updatePlaybackButton(false);
    L76:
        r33 = new SpannableStringBuilder(String.format("%s - %s", new Object[]{r02.getMusicAuthor(), r02.getMusicTitle()}));
        int r52 = 0;
    L77:
        if (r52 >= 2) goto L100;
        AudioPlayerAlert.ClippingTextViewSwitcher r64 = this.titleTextView;
        if (r52 != 0) goto L81;
        TextView r65 = r64.getTextView();
    L82:
        if (r65 == null) goto L85;
        r65.setEllipsize(TextUtils.TruncateAt.END);
    L85:
        r52 = r52 + 1;
        goto L77
    L81:
        r65 = r64.getNextTextView();
        goto L82
    L74:
        r93.setAlpha(0.0f);
        this.playbackSpeedButton.setEnabled(false);
        this.titleTextView.setPadding(0, 0, this.joinButtonWidth, 0);
        goto L76
    L75:
        this.titleTextView.setPadding(0, 0, this.joinButtonWidth, 0);
        goto L76
    L58:
        r4.setPause(true, !r14);
        this.playButton.setContentDescription(LocaleController.getString(R.string.AccActionPause));
        goto L60
    L33:
        if (this.animatorSet == null) goto L37;
        if (r14 == true) goto L37;
        this.checkPlayerAfterAnimation = true;
    }

    private void checkSpeedHint() {
        long r0 = System.currentTimeMillis();
        if (this.speedHintView == null) goto L5;
    L9:
        this.lastPlaybackClick = r0;
        return;
    L5:
        if ((r0 - this.lastPlaybackClick) <= 300) goto L9;
        HintsController.Hint r2 = HintsController.Hint.PlaybackSpeedHint;
        if (r2.show() == false) goto L9;
        r2.increment();
        showSpeedHint();
        goto L9
    }

    private void createPlaybackSpeedButton() {
        if (this.playbackSpeedButton == null) goto L5;
        return;
    L5:
        ActionBarMenuItem r1 = new ActionBarMenuItem(getContext(), null, 0, getThemedColor(Theme.key_dialogTextBlack), this.resourcesProvider);
        this.playbackSpeedButton = r1;
        r1.setAdditionalYOffset(AndroidUtilities.dp(30.0f));
        this.playbackSpeedButton.setLongClickEnabled(false);
        this.playbackSpeedButton.setVisibility(8);
        this.playbackSpeedButton.setTag(null);
        this.playbackSpeedButton.setShowSubmenuByMove(false);
        this.playbackSpeedButton.setContentDescription(LocaleController.getString(R.string.AccDescrPlayerSpeed));
        this.playbackSpeedButton.setDelegate(new FragmentContextView$$ExternalSyntheticLambda0(this));
        ActionBarMenuItem r0 = this.playbackSpeedButton;
        SpeedIconDrawable r2 = new SpeedIconDrawable(true);
        this.speedIcon = r2;
        r0.setIcon(r2);
        float[] r22 = {1.0f, 1.5f, 2.0f};
        ActionBarMenuSlider.SpeedSlider r4 = new ActionBarMenuSlider.SpeedSlider(getContext(), this.resourcesProvider);
        this.speedSlider = r4;
        r4.setRoundRadiusDp(6.0f);
        this.speedSlider.setDrawShadow(true);
        this.speedSlider.setOnValueChange(new FragmentContextView$$ExternalSyntheticLambda1(this));
        this.speedItems[0] = this.playbackSpeedButton.lazilyAddSubItem(0, R.drawable.msg_speed_slow, LocaleController.getString(R.string.SpeedSlow));
        this.speedItems[1] = this.playbackSpeedButton.lazilyAddSubItem(1, R.drawable.msg_speed_normal, LocaleController.getString(R.string.SpeedNormal));
        this.speedItems[2] = this.playbackSpeedButton.lazilyAddSubItem(2, R.drawable.msg_speed_medium, LocaleController.getString(R.string.SpeedMedium));
        this.speedItems[3] = this.playbackSpeedButton.lazilyAddSubItem(3, R.drawable.msg_speed_fast, LocaleController.getString(R.string.SpeedFast));
        this.speedItems[4] = this.playbackSpeedButton.lazilyAddSubItem(4, R.drawable.msg_speed_veryfast, LocaleController.getString(R.string.SpeedVeryFast));
        this.speedItems[5] = this.playbackSpeedButton.lazilyAddSubItem(5, R.drawable.msg_speed_superfast, LocaleController.getString(R.string.SpeedSuperFast));
        if (AndroidUtilities.density < 3.0f) goto L8;
        this.playbackSpeedButton.setPadding(0, 1, 0, 0);
    L8:
        this.playbackSpeedButton.setAdditionalXOffset(AndroidUtilities.dp(8.0f));
        addView(this.playbackSpeedButton, LayoutHelper.createFrame(36, 36.0f, 53, 0.0f, 0.0f, 36.0f, 0.0f));
        this.playbackSpeedButton.setOnClickListener(new FragmentContextView$$ExternalSyntheticLambda2(this, r22));
        this.playbackSpeedButton.setOnLongClickListener(new FragmentContextView$$ExternalSyntheticLambda3(this));
        updatePlaybackButton(false);
    }

    private boolean equals(float r1, float r2) {
        if (Math.abs(r1 - r2) >= 0.05f) goto L6;
        return true;
    L6:
        return false;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private int getTitleTextColor() {
        int r0 = this.currentStyle;
        if (r0 != 4) goto L7;
        return getThemedColor(Theme.key_inappPlayerPerformer);
    L7:
        if (r0 == 1) goto L14;
        if (r0 == 3) goto L14;
        return getThemedColor(Theme.key_inappPlayerTitle);
    L14:
        return getThemedColor(Theme.key_returnToCallText);
    }

    private boolean handlePlayerTouch(boolean r17) {
        if (this.currentStyle == 0) goto L5;
        return false;
    L5:
        MessageObject r1 = MediaController.getInstance().getPlayingMessageObject();
        if (this.fragment == null) goto L50;
        if (r1 == null) goto L50;
        if (r1.isMusic() == false) goto L11;
    L12:
        if (r17 == true) goto L19;
        Activity r12 = AndroidUtilities.findActivity(getContext());
        if ((r12 instanceof LaunchActivity) == false) goto L17;
        new AudioPlayerAlert(r12, this.resourcesProvider).show();
    L49:
        return true;
    L17:
        if (AndroidUtilities.isContextSafe(LaunchActivity.instance) == false) goto L49;
        new AudioPlayerAlert(LaunchActivity.instance, this.resourcesProvider).show();
    L19:
        ChatActivityInterface r3 = this.chatActivity;
        if (r3 == null) goto L22;
        long r5 = r3.getDialogId();
    L23:
        int r32 = r1.currentAccount;
        long r7 = r1.getDialogId();
        int r10 = r1.getFeedRealId();
        if (this.chatActivity != null) goto L26;
    L30:
        LaunchActivity r0 = LaunchActivity.instance;
        if (r0 != null) goto L34;
        return false;
    L34:
        if (UserConfig.selectedAccount == r32) goto L36;
        r0.switchToAccount(r32, true);
    L36:
        Bundle r13 = new Bundle();
        if (DialogObject.isEncryptedDialog(r7) == false) goto L40;
        r13.putInt("enc_id", DialogObject.getEncryptedChatId(r7));
    L48:
        r13.putInt("message_id", r10);
        NotificationCenter.getInstance(r32).postNotificationName(NotificationCenter.closeChats, new Object[0]);
        r0.presentFragment(new ChatActivity(r13), false, false);
        goto L49
    L40:
        if (DialogObject.isUserDialog(r7) == false) goto L42;
        r13.putLong("user_id", r7);
        goto L48
    L42:
        TLRPC.Chat r52 = MessagesController.getInstance(r32).getChat(Long.valueOf(-r7));
        if (r52 != null) goto L45;
    L47:
        r13.putLong("chat_id", -r7);
        goto L48
    L45:
        if (r52.migrated_to == null) goto L47;
        r13.putLong("migrated_to", r7);
        r7 = -r52.migrated_to.channel_id;
        goto L47
    L26:
        if (r7 != r5) goto L30;
        if (r32 != this.fragment.getCurrentAccount()) goto L30;
        this.chatActivity.scrollToMessageId(r10, 0, false, 0, true, 0);
        goto L49
    L22:
        r5 = 0;
        goto L23
    L11:
        if (r1.isVoice() == false) goto L19;
    L50:
        return false;
    }

    private boolean isPlayingVoice() {
        MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isVoice() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$checkCreateView$0(View r1) {
        if (this.currentStyle == 0) goto L5;
        return;
    L5:
        if (MediaController.getInstance().isMessagePaused() == false) goto L8;
        MediaController.getInstance().playMessage(MediaController.getInstance().getPlayingMessageObject());
        return;
    L8:
        MediaController.getInstance().pauseMessage(MediaController.getInstance().getPlayingMessageObject());
    }

    private /* synthetic */ void lambda$checkCreateView$1(View r1) {
        callOnClick();
    }

    private /* synthetic */ void lambda$checkCreateView$3() {
        updateAvatars(true);
    }

    private /* synthetic */ void lambda$checkCreateView$4(View r5) {
        VoIPService r52 = VoIPService.getSharedInstance();
        if (r52 != null) goto L6;
        return;
    L6:
        if (r52.groupCall == null) goto L16;
        AccountInstance.getInstance(r52.getAccount());
        ChatObject.Call r0 = r52.groupCall;
        TLRPC.Chat r1 = r52.getChat();
        TLRPC.GroupCallParticipant r02 = r0.participants.get(r52.getSelfId());
        if (r02 == null) goto L16;
        if (r02.can_self_unmute == true) goto L16;
        if (r02.muted == false) goto L16;
        if (ChatObject.canManageCalls(r1) == true) goto L16;
        return;
    L16:
        boolean r03 = !r52.isMicMute();
        this.isMuted = r03;
        r52.setMicMute(r03, false, true);
        RLottieDrawable r53 = this.muteDrawable;
        if (this.isMuted == false) goto L19;
        int r04 = 15;
    L21:
        if (r53.setCustomEndFrame(r04) == false) goto L26;
        boolean r54 = this.isMuted;
        RLottieDrawable r05 = this.muteDrawable;
        if (r54 == false) goto L25;
        r05.setCurrentFrame(0);
        goto L26
    L25:
        r05.setCurrentFrame(14);
    L26:
        this.muteButton.playAnimation();
        Theme.getFragmentContextViewWavesDrawable().updateState(true);
        this.capsuleBlobDrawable.updateState(true);
        this.muteButton.performHapticFeedback(3, 2);     // Catch: Exception -> L29
        return;
    L34:
        return;
    L19:
        r04 = 29;
        goto L21
    }

    private /* synthetic */ void lambda$checkCreateView$5(AlertDialog r3, int r4) {
        BaseFragment r32 = this.fragment;
        if ((r32 instanceof DialogsActivity) == false) goto L9;
        int r2 = 0;
    L6:
        if (r2 >= 16) goto L8;
        LocationController.getInstance(r2).removeAllLocationSharings();
        r2 = r2 + 1;
        goto L6
    L8:
        return;
    L9:
        LocationController.getInstance(r32.getCurrentAccount()).removeSharingLocation(this.chatActivity.getDialogId());
    }

    private /* synthetic */ void lambda$checkCreateView$6(View r4) {
        if (this.currentStyle != 2) goto L17;
        AlertDialog.Builder r42 = new AlertDialog.Builder(this.fragment.getParentActivity(), this.resourcesProvider);
        r42.setTitle(LocaleController.getString(R.string.StopLiveLocationAlertToTitle));
        if ((this.fragment instanceof DialogsActivity) == false) goto L7;
        r42.setMessage(LocaleController.getString(R.string.StopLiveLocationAlertAllText));
    L13:
        r42.setPositiveButton(LocaleController.getString(R.string.Stop), new FragmentContextView$$ExternalSyntheticLambda14(this));
        r42.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r0 = r42.create();
        r42.show();
        TextView r43 = (TextView) r0.getButton(-1);
        if (r43 == null) goto L19;
        r43.setTextColor(getThemedColor(Theme.key_text_RedBold));
        return;
    L19:
        return;
    L7:
        TLRPC.Chat r02 = this.chatActivity.getCurrentChat();
        TLRPC.User r1 = this.chatActivity.getCurrentUser();
        if (r02 == null) goto L10;
        r42.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("StopLiveLocationAlertToGroupText", R.string.StopLiveLocationAlertToGroupText, new Object[]{r02.title})));
        goto L13
    L10:
        if (r1 == null) goto L12;
        r42.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("StopLiveLocationAlertToUserText", R.string.StopLiveLocationAlertToUserText, new Object[]{UserObject.getFirstName(r1)})));
        goto L13
    L12:
        r42.setMessage(LocaleController.getString(R.string.AreYouSure));
        goto L13
    L17:
        MediaController.getInstance().cleanupPlayer(true, true);
    }

    private /* synthetic */ boolean lambda$checkCreateView$7(View r1) {
        return handlePlayerTouch(true);
    }

    private /* synthetic */ void lambda$checkCreateView$8(DialogInterface r1) {
        checkImport(false);
    }

    private /* synthetic */ void lambda$checkCreateView$9(View r13) {
        int r132 = this.currentStyle;
        boolean r2 = true;
        if (r132 != 6) goto L21;
        LivePlayer r133 = LivePlayer.recording;
        if (r133 == null) goto L85;
        int r0 = r133.currentAccount;
        if (r0 == UserConfig.selectedAccount) goto L13;
        LaunchActivity r3 = LaunchActivity.instance;
        if (r3 == null) goto L86;
        r3.switchToAccount(r0, true);
        goto L13
    L86:
        return;
    L13:
        BaseFragment r02 = LaunchActivity.getSafeLastFragment();
        if (r02 == null) goto L87;
        TL_stories.StoryItem r22 = MessagesController.getInstance(r133.currentAccount).getStoriesController().findStory(r133.dialogId, r133.storyId);
        if (r22 == null) goto L81;
        r22.dialogId = r133.dialogId;
        r02.getOrCreateStoryViewer(r133.currentAccount).open(r133.currentAccount, getContext(), r22, null);
        return;
    L81:
        return;
    L87:
        return;
    L85:
        return;
    L21:
        if (r132 != 0) goto L24;
        handlePlayerTouch(false);
        return;
    L24:
        if (r132 != 1) goto L28;
        getContext().startActivity(new Intent(getContext(), LaunchActivity.class).setAction("voip"));
        return;
    L28:
        if (r132 != 2) goto L49;
        int r134 = UserConfig.selectedAccount;
        ChatActivityInterface r1 = this.chatActivity;
        if (r1 == null) goto L33;
        long r03 = r1.getDialogId();
        r134 = this.fragment.getCurrentAccount();
    L43:
        if (r03 == 0) goto L46;
        openSharingLocation(LocationController.getInstance(r134).getSharingLocationInfo(r03));
        return;
    L46:
        this.fragment.showDialog(new SharingLocationsAlert(getContext(), new FragmentContextView$$ExternalSyntheticLambda15(this), this.resourcesProvider));
        return;
    L33:
        if (LocationController.getLocationsCount() != 1) goto L41;
        int r12 = 0;
    L36:
        if (r12 >= 16) goto L41;
        if (LocationController.getInstance(r12).sharingLocationsUI.isEmpty() == false) goto L39;
        r12 = r12 + 1;
        goto L36
    L39:
        LocationController.SharingLocationInfo r135 = LocationController.getInstance(r12).sharingLocationsUI.get(0);
        r03 = r135.did;
        r134 = r135.messageObject.currentAccount;
    L41:
        r03 = 0;
        goto L43
    L49:
        if (r132 != 3) goto L57;
        if (VoIPService.getSharedInstance() != null) goto L53;
        return;
    L53:
        if ((getContext() instanceof LaunchActivity) == false) goto L83;
        GroupCallActivity.create((LaunchActivity) getContext(), AccountInstance.getInstance(VoIPService.getSharedInstance().getAccount()), null, null, false, null);
        return;
    L83:
        return;
    L57:
        if (r132 != 4) goto L73;
        if (this.fragment.getParentActivity() == null) goto L88;
        ChatObject.Call r136 = this.chatActivity.getGroupCall();
        if (r136 == null) goto L89;
        TLRPC.Chat r4 = this.fragment.getMessagesController().getChat(Long.valueOf(r136.chatId));
        TLRPC.GroupCall r137 = r136.call;
        if (r137 != null) goto L67;
    L69:
        r2 = false;
    L70:
        Boolean r8 = Boolean.valueOf(r2);
        Activity r9 = this.fragment.getParentActivity();
        BaseFragment r10 = this.fragment;
        VoIPHelper.startCall(r4, null, null, false, r8, r9, r10, r10.getAccountInstance());
        return;
    L67:
        if (r137.rtmp_stream == true) goto L69;
    L89:
        return;
    L88:
        return;
    L73:
        if (r132 == 5) goto L75;
        return;
    L75:
        if (this.fragment.getSendMessagesHelper().getImportingHistory(((ChatActivity) this.fragment).getDialogId()) == null) goto L90;
        ImportingAlert r138 = new ImportingAlert(getContext(), null, (ChatActivity) this.fragment, this.resourcesProvider);
        r138.setOnHideListener(new FragmentContextView$$ExternalSyntheticLambda16(this));
        this.fragment.showDialog(r138);
        checkImport(false);
        return;
    }

    private /* synthetic */ void lambda$createPlaybackSpeedButton$10(int r4) {
        if (r4 < 0) goto L10;
        float[] r0 = speeds;
        if (r4 >= r0.length) goto L12;
        float r1 = MediaController.getInstance().getPlaybackSpeed(this.isMusic);
        float r42 = r0[r4];
        MediaController.getInstance().setPlaybackSpeed(this.isMusic, r42);
        if (r1 == r42) goto L11;
        playbackSpeedChanged(false, r1, r42);
        return;
    L11:
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$createPlaybackSpeedButton$11(Float r2, Boolean r3) {
        this.slidingSpeed = !r3.booleanValue();
        MediaController.getInstance().setPlaybackSpeed(this.isMusic, this.speedSlider.getSpeed(r2.floatValue()));
    }

    private /* synthetic */ void lambda$createPlaybackSpeedButton$12(float[] r5, View r6) {
        float r62 = MediaController.getInstance().getPlaybackSpeed(this.isMusic);
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= r5.length) goto L9;
        if ((r62 - 0.1f) <= r5[r1]) goto L10;
        r1 = r1 + 1;
    L10:
        int r12 = r1 + 1;
        if (r12 >= r5.length) goto L14;
        r0 = r12;
    L14:
        float r52 = r5[r0];
        MediaController.getInstance().setPlaybackSpeed(this.isMusic, r52);
        playbackSpeedChanged(true, r62, r52);
        checkSpeedHint();
        return;
    L9:
        r1 = -1;
        goto L10
    }

    private /* synthetic */ void lambda$createPlaybackSpeedButton$13(float r2, Boolean r3) {
        if (r3.booleanValue() == true) goto L6;
        playbackSpeedChanged(false, r2, MediaController.getInstance().getPlaybackSpeed(this.isMusic));
        return;
    }

    private /* synthetic */ boolean lambda$createPlaybackSpeedButton$14(View r5) {
        float r52 = MediaController.getInstance().getPlaybackSpeed(this.isMusic);
        this.speedSlider.setSpeed(r52, false);
        ActionBarMenuSlider.SpeedSlider r0 = this.speedSlider;
        int r2 = Theme.key_actionBarDefaultSubmenuBackground;
        r0.setBackgroundColor(Theme.getColor(r2, this.resourcesProvider));
        this.speedSlider.invalidateBlur(this.fragment instanceof ChatActivity);
        this.playbackSpeedButton.redrawPopup(Theme.getColor(r2));
        this.playbackSpeedButton.updateColor();
        updatePlaybackButton(false);
        this.playbackSpeedButton.setDimMenu(0.3f);
        this.playbackSpeedButton.toggleSubMenu(this.speedSlider, null);
        this.playbackSpeedButton.setOnMenuDismiss(new FragmentContextView$$ExternalSyntheticLambda17(this, r52));
        MessagesController.getGlobalNotificationsSettings().edit().putInt("speedhint", -15).apply();
        return true;
    }

    private /* synthetic */ void lambda$startJoinFlickerAnimation$16() {
        this.joinButtonFlicker.setProgress(0.0f);
        this.joinButton.invalidate();
    }

    private void onItemChanged(ReplaceAnimator<?> r6) {
        float r62 = this.callMessagesAnimator.getMetadata().getTotalVisibility();
        float r63 = 1.0f - r62;
        this.titleTextView.setAlpha(r63);
        this.titleTextView.setScaleX(AndroidUtilities.lerp(0.7f, 1.0f, r63));
        this.titleTextView.setScaleY(AndroidUtilities.lerp(0.7f, 1.0f, r63));
        Iterator<ListAnimator.Entry<CallMessageItem>> r5 = this.callMessagesAnimator.iterator();
    L4:
        if (r5.hasNext() == false) goto L6;
        ListAnimator.Entry<CallMessageItem> r64 = r5.next();
        float r0 = AndroidUtilities.lerp(0.7f, 1.0f, r64.getVisibility());
        CallMessageItem.m10800$$Nest$fgetcell(r64.item).setAlpha(r64.getVisibility());
        CallMessageItem.m10800$$Nest$fgetcell(r64.item).setScaleX(r0);
        CallMessageItem.m10800$$Nest$fgetcell(r64.item).setScaleY(r0);
        goto L4
    }

    private void openSharingLocation(LocationController.SharingLocationInfo r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        if ((this.fragment.getParentActivity() instanceof LaunchActivity) == false) goto L9;
        LaunchActivity r4 = (LaunchActivity) this.fragment.getParentActivity();
        r4.switchToAccount(r5.messageObject.currentAccount, true);
        LocationActivity r0 = new LocationActivity(2);
        r0.setMessageObject(r5.messageObject);
        r0.setDelegate(new FragmentContextView$$ExternalSyntheticLambda18(r5, r5.messageObject.getDialogId()));
        r4.presentFragment(r0);
        return;
    }

    private void playbackSpeedChanged(boolean r6, float r7, float r8) {
        if (equals(r7, r8) == false) goto L6;
        return;
    L6:
        if (Math.abs(r8 - 1.0f) >= 0.05f) goto L18;
        if (r7 >= r8) goto L10;
        return;
    L10:
        String r62 = LocaleController.getString(R.string.AudioSpeedNormal);
        if (Math.abs(r7 - 2.0f) >= 0.05f) goto L14;
        int r72 = R.raw.speed_2to1;
    L34:
        BulletinFactory.of(this.fragment).createSimpleBulletin(r72, r62).show();
        return;
    L14:
        if (r8 >= r7) goto L16;
        r72 = R.raw.speed_slow;
        goto L34
    L16:
        r72 = R.raw.speed_fast;
        goto L34
    L18:
        if (r6 == true) goto L20;
    L24:
        if (r6 == true) goto L26;
    L30:
        r62 = LocaleController.formatString("AudioSpeedCustom", R.string.AudioSpeedCustom, new Object[]{SpeedIconDrawable.formatNumber(r8)});
        if (r8 >= 1.0f) goto L33;
        r72 = R.raw.speed_slow;
        goto L34
    L33:
        r72 = R.raw.speed_fast;
        goto L34
    L26:
        if (equals(r8, 2.0f) == false) goto L30;
        if (equals(r7, 1.5f) == false) goto L30;
        r62 = LocaleController.getString(R.string.AudioSpeedFast);
        r72 = R.raw.speed_15to2;
        goto L34
    L20:
        if (equals(r8, 1.5f) == false) goto L24;
        if (equals(r7, 1.0f) == false) goto L24;
        r62 = LocaleController.formatString("AudioSpeedCustom", R.string.AudioSpeedCustom, new Object[]{SpeedIconDrawable.formatNumber(r8)});
        r72 = R.raw.speed_1to15;
        goto L34
    }

    private void removeObservers() {
        NotificationCenter.ObserversGroup r0 = this.globalObserversGroup;
        if (r0 == null) goto L5;
        r0.removeAllObservers();
        this.globalObserversGroup = null;
    L5:
        int r02 = 0;
    L7:
        if (r02 >= 16) goto L12;
        NotificationCenter.ObserversGroup r2 = this.observersGroup[r02];
        if (r2 == null) goto L11;
        r2.removeAllObservers();
        this.observersGroup[r02] = null;
    L11:
        r02 = r02 + 1;
        goto L7
    }

    private void showSpeedHint() {
        if (this.fragment != null) goto L5;
        return;
    L5:
        if (this.speedHintViewParent == null) goto L9;
        AnonymousClass9 r0 = new AnonymousClass9(this, getContext(), 6, true);
        this.speedHintView = r0;
        r0.setExtraTranslationY(AndroidUtilities.dp(-12.0f));
        this.speedHintView.setText(LocaleController.getString(R.string.SpeedHint));
        ViewGroup.MarginLayoutParams r02 = new ViewGroup.MarginLayoutParams(-2, -2);
        r02.rightMargin = AndroidUtilities.dp(3.0f);
        this.speedHintViewParent.addView(this.speedHintView, r02);
        this.speedHintView.showForView(this.playbackSpeedButton, true);
        return;
    }

    private void startJoinFlickerAnimation() {
        CellFlickerDrawable r0 = this.joinButtonFlicker;
        if (r0 != null) goto L5;
    L8:
        this.flickOnAttach = true;
        return;
    L5:
        if (r0.getProgress() < 1.0f) goto L8;
        this.flickOnAttach = false;
        AndroidUtilities.runOnUIThread(new FragmentContextView$$ExternalSyntheticLambda4(this), 150);
    }

    private void updateAvatars(boolean r18) {
        checkCreateView();
        if (r18 == true) goto L7;
        ValueAnimator r3 = this.avatars.avatarsDrawable.transitionProgressAnimator;
        if (r3 == null) goto L7;
        r3.cancel();
        this.avatars.avatarsDrawable.transitionProgressAnimator = null;
    L7:
        AvatarsImageView r32 = this.avatars;
        if (r32.avatarsDrawable.transitionProgressAnimator == null) goto L10;
        r32.updateAfterTransitionEnd();
        return;
    L10:
        if (this.currentStyle != 4) goto L17;
        ChatActivityInterface r33 = this.chatActivity;
        if (r33 == null) goto L14;
        ChatObject.Call r34 = r33.getGroupCall();
        int r5 = this.fragment.getCurrentAccount();
    L15:
        int r6 = r5;
        TLRPC.User r52 = null;
    L24:
        int r9 = 0;
        if (r34 == null) goto L33;
        int r53 = r34.sortedParticipants.size();
        int r10 = 0;
    L27:
        if (r10 >= 3) goto L40;
        AvatarsImageView r11 = this.avatars;
        if (r10 >= r53) goto L31;
        r11.setObject(r10, r6, r34.sortedParticipants.get(r10));
    L32:
        r10 = r10 + 1;
        goto L27
    L31:
        r11.setObject(r10, r6, null);
    L40:
        this.avatars.commitTransition(r18);
        if (this.currentStyle != 4) goto L79;
        if (r34 != null) goto L44;
        return;
    L44:
        if (r34.call.rtmp_stream == false) goto L46;
        int r2 = 0;
    L47:
        if (r2 != 0) goto L49;
        int r22 = 10;
    L50:
        int r23 = r22 + 3;
        AudioPlayerAlert.ClippingTextViewSwitcher r4 = this.titleTextView;
        if (r18 == false) goto L55;
        int r1 = ((FrameLayout.LayoutParams) r4.getLayoutParams()).leftMargin;
        if (AndroidUtilities.dp(r23) == r1) goto L56;
        float r62 = (this.titleTextView.getTranslationX() + r1) - AndroidUtilities.dp(r4);
        this.titleTextView.setTranslationX(r62);
        this.subtitleTextView.setTranslationX(r62);
        ViewPropertyAnimator r12 = this.titleTextView.animate().translationX(0.0f).setDuration(220);
        CubicBezierInterpolator r42 = CubicBezierInterpolator.DEFAULT;
        r12.setInterpolator(r42);
        this.subtitleTextView.animate().translationX(0.0f).setDuration(220).setInterpolator(r42);
    L56:
        AudioPlayerAlert.ClippingTextViewSwitcher r13 = this.titleTextView;
        float r132 = r23;
        if (this.isSideMenued == false) goto L59;
        int r24 = 64;
    L60:
        int r63 = 36;
        if (r34.isScheduled() == false) goto L63;
        int r54 = 90;
    L64:
        r13.setLayoutParams(LayoutHelper.createFrame(-1, 20.0f, 51, r132, 5.0f, r24 + r54, 0.0f));
        AudioPlayerAlert.ClippingTextViewSwitcher r14 = this.subtitleTextView;
        if (this.isSideMenued == false) goto L68;
        r9 = 64;
    L68:
        if (r34.isScheduled() == false) goto L70;
        r63 = 90;
    L70:
        r14.setLayoutParams(LayoutHelper.createFrame(-1, 20.0f, 51, r132, 25.0f, r9 + r63, 0.0f));
        return;
    L63:
        r54 = 36;
        goto L64
    L59:
        r24 = 0;
        goto L60
    L55:
        r4.animate().cancel();
        this.subtitleTextView.animate().cancel();
        this.titleTextView.setTranslationX(0.0f);
        this.subtitleTextView.setTranslationX(0.0f);
        goto L56
    L49:
        r22 = ((r2 - 1) * 24) + 52;
        goto L50
    L46:
        r2 = Math.min(3, r34.sortedParticipants.size());
        goto L47
    L79:
        return;
    L33:
        if (r52 == null) goto L37;
        this.avatars.setObject(0, r6, r52);
        int r55 = 1;
    L35:
        if (r55 >= 3) goto L40;
        this.avatars.setObject(r55, r6, null);
        r55 = r55 + 1;
        goto L35
    L37:
        int r56 = 0;
    L38:
        if (r56 >= 3) goto L40;
        this.avatars.setObject(r56, r6, null);
        r56 = r56 + 1;
        goto L38
    L14:
        r5 = this.account;
        r34 = null;
        goto L15
    L17:
        if (VoIPService.getSharedInstance() == null) goto L23;
        r34 = VoIPService.getSharedInstance().groupCall;
        if (this.chatActivity == null) goto L21;
        r52 = null;
    L22:
        r6 = VoIPService.getSharedInstance().getAccount();
        goto L24
    L21:
        r52 = VoIPService.getSharedInstance().getUser();
        goto L22
    L23:
        r34 = null;
        r6 = this.account;
        r52 = null;
        goto L24
    }

    private void updateCallTitle() {
        checkCreateView();
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L76;
        int r1 = this.currentStyle;
        if (r1 == 1) goto L7;
        if (r1 == 3) goto L7;
        return;
    L7:
        int r12 = r0.getCallState();
        if (r0.isSwitchingStream() == true) goto L19;
        if (r12 != 1) goto L11;
    L16:
        this.titleTextView.setText(LocaleController.getString(R.string.VoipGroupConnecting), false);
        return;
    L11:
        if (r12 == 2) goto L16;
        if (r12 == 6) goto L16;
        if (r12 == 5) goto L16;
    L19:
        if (r0.isConference() == false) goto L38;
        ChatObject.Call r13 = r0.groupCall;
        if (r13 == null) goto L38;
        if (r13.sortedParticipants.size() > 1) goto L26;
        this.titleTextView.setText(LocaleController.getString(R.string.ConferenceChat), false);
        return;
    L26:
        StringBuilder r14 = new StringBuilder();
        int r3 = 0;
    L28:
        if (r3 >= Math.min(3, r0.groupCall.sortedParticipants.size())) goto L33;
        if (r3 <= 0) goto L31;
        r14.append(", ");
    L31:
        r14.append(DialogObject.getShortName(r0.getAccount(), DialogObject.getPeerDialogId(r0.groupCall.sortedParticipants.get(r3).peer)));
        r3 = r3 + 1;
        goto L28
    L33:
        if (r0.groupCall.sortedParticipants.size() <= 3) goto L35;
        r14.append(" ");
        r14.append(LocaleController.formatPluralString("AndOther", r0.groupCall.sortedParticipants.size() - 3, new Object[0]));
    L35:
        this.titleTextView.setText(r14.toString(), false);
        return;
    L38:
        if (r0.getChat() == null) goto L62;
        if (TextUtils.isEmpty(r0.groupCall.call.title) == true) goto L43;
        this.titleTextView.setText(r0.groupCall.call.title, false);
        return;
    L43:
        ChatActivityInterface r15 = this.chatActivity;
        if (r15 != null) goto L46;
    L59:
        this.titleTextView.setText(r0.getChat().title, false);
        return;
    L46:
        if (r15.getCurrentChat() == null) goto L59;
        if (this.chatActivity.getCurrentChat().id != r0.getChat().id) goto L59;
        TLRPC.Chat r02 = this.chatActivity.getCurrentChat();
        if (VoIPService.hasRtmpStream() == false) goto L53;
        this.titleTextView.setText(LocaleController.getString(R.string.VoipChannelViewVoiceChat), false);
        return;
    L53:
        boolean r03 = ChatObject.isChannelOrGiga(r02);
        AudioPlayerAlert.ClippingTextViewSwitcher r8 = this.titleTextView;
        if (r03 == false) goto L57;
        r8.setText(LocaleController.getString(R.string.VoipChannelViewVoiceChat), false);
        return;
    L57:
        r8.setText(LocaleController.getString(R.string.VoipGroupViewVoiceChat), false);
        return;
    L62:
        if (r0.getUser() == null) goto L78;
        TLRPC.User r04 = r0.getUser();
        ChatActivityInterface r16 = this.chatActivity;
        if (r16 != null) goto L66;
    L71:
        this.titleTextView.setText(ContactsController.formatName(r04.first_name, r04.last_name));
        return;
    L66:
        if (r16.getCurrentUser() == null) goto L71;
        if (this.chatActivity.getCurrentUser().id != r04.id) goto L71;
        this.titleTextView.setText(LocaleController.getString(R.string.ReturnToCall));
        return;
    L78:
        return;
    }

    private void updatePlaybackButton(boolean r7) {
        if (this.speedIcon != null) goto L5;
        return;
    L5:
        float r0 = MediaController.getInstance().getPlaybackSpeed(this.isMusic);
        this.speedIcon.setValue(r0, r7);
        updateColors();
        boolean r1 = this.slidingSpeed;
        int r2 = 0;
        this.slidingSpeed = false;
    L7:
        if (r2 >= this.speedItems.length) goto L14;
        if (r1 == false) goto L10;
    L12:
        ActionBarMenuItem.Item r3 = this.speedItems[r2];
        int r4 = Theme.key_actionBarDefaultSubmenuItem;
        r3.setColors(getThemedColor(r4), getThemedColor(r4));
    L13:
        r2 = r2 + 1;
        goto L7
    L10:
        if (Math.abs(r0 - speeds[r2]) >= 0.05f) goto L12;
        ActionBarMenuItem.Item r32 = this.speedItems[r2];
        int r42 = Theme.key_featuredStickers_addButtonPressed;
        r32.setColors(getThemedColor(r42), getThemedColor(r42));
        goto L13
    L14:
        this.speedSlider.setSpeed(r0, r7);
    }

    private void updateStyle(int r2) {
        updateStyle(r2, false);
    }

    private void updateStyle(int r23, boolean r24) {
        if (this.currentStyle != r23) goto L6;
        if (r24 == true) goto L6;
        return;
    L6:
        checkCreateView();
        int r2 = this.currentStyle;
        boolean r5 = true;
        if (r2 == 3) goto L9;
        if (r2 == 1) goto L9;
    L15:
        this.currentStyle = r23;
        FrameLayout r22 = this.frameLayout;
        int r7 = 0;
        if (r23 == 4) goto L18;
        boolean r8 = true;
    L19:
        r22.setWillNotDraw(r8);
        if (r23 == 4) goto L22;
        this.notifyButtonEnabled = false;
    L22:
        AvatarsImageView r25 = this.avatars;
        if (r25 == null) goto L25;
        r25.setStyle(this.currentStyle);
        this.avatars.setLayoutParams(LayoutHelper.createFrame(108, getStyleHeight(), 51));
    L25:
        this.frameLayout.setLayoutParams(LayoutHelper.createFrame(-1, getStyleHeight(), 51, 0.0f, 0.0f, 0.0f, 0.0f));
        float r26 = this.topPadding;
        if (r26 > 0.0f) goto L28;
    L30:
        int r12 = 64;
        if (r23 != 6) goto L48;
        this.selector.setBackground(Theme.getSelectorDrawable(false));
        this.frameLayout.setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{getThemedColor(Theme.key_stories_circle_live1), getThemedColor(Theme.key_stories_circle_live2)}));
        this.frameLayout.setTag(null);
        this.subtitleTextView.setVisibility(8);
        this.joinButton.setVisibility(8);
        this.closeButton.setVisibility(8);
        this.playButton.setVisibility(8);
        this.muteButton.setVisibility(8);
        this.importingImageView.setVisibility(8);
        this.importingImageView.stopAnimation();
        this.avatars.setVisibility(8);
        this.titleTextView.setTag(Integer.valueOf(Theme.key_returnToCallText));
        int r1 = 0;
    L33:
        if (r1 >= 2) goto L42;
        AudioPlayerAlert.ClippingTextViewSwitcher r27 = this.titleTextView;
        if (r1 != 0) goto L37;
        TextView r28 = r27.getTextView();
    L38:
        if (r28 == null) goto L41;
        r28.setGravity(19);
        r28.setTextColor(getThemedColor(Theme.key_returnToCallText));
        r28.setTypeface(AndroidUtilities.bold());
        r28.setTextSize(1, 15.0f);
    L41:
        r1 = r1 + 1;
        goto L33
    L37:
        r28 = r27.getNextTextView();
        goto L38
    L42:
        AudioPlayerAlert.ClippingTextViewSwitcher r13 = this.titleTextView;
        if (this.isSideMenued == false) goto L45;
        r7 = 64;
    L45:
        r13.setLayoutParams(LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, -1.0f, r7, 0.0f));
        return;
    L48:
        if (r23 != 5) goto L67;
        this.selector.setBackground(Theme.getSelectorDrawable(false));
        this.frameLayout.setBackgroundColor(0);
        this.frameLayout.setTag(Integer.valueOf(Theme.key_inappPlayerBackground));
        int r14 = 0;
    L50:
        if (r14 >= 2) goto L59;
        AudioPlayerAlert.ClippingTextViewSwitcher r29 = this.titleTextView;
        if (r14 != 0) goto L54;
        TextView r210 = r29.getTextView();
    L55:
        if (r210 == null) goto L58;
        r210.setGravity(19);
        r210.setTextColor(getThemedColor(Theme.key_inappPlayerTitle));
        r210.setTypeface(Typeface.DEFAULT);
        r210.setTextSize(1, 15.0f);
    L58:
        r14 = r14 + 1;
        goto L50
    L54:
        r210 = r29.getNextTextView();
        goto L55
    L59:
        this.titleTextView.setTag(Integer.valueOf(Theme.key_inappPlayerTitle));
        this.subtitleTextView.setVisibility(8);
        this.joinButton.setVisibility(8);
        this.closeButton.setVisibility(8);
        this.playButton.setVisibility(8);
        this.muteButton.setVisibility(8);
        this.avatars.setVisibility(8);
        this.importingImageView.setVisibility(0);
        this.importingImageView.playAnimation();
        this.closeButton.setContentDescription(LocaleController.getString(R.string.AccDescrClosePlayer));
        ActionBarMenuItem r15 = this.playbackSpeedButton;
        if (r15 == null) goto L62;
        r15.setVisibility(8);
        this.playbackSpeedButton.setTag(null);
    L62:
        AudioPlayerAlert.ClippingTextViewSwitcher r16 = this.titleTextView;
        if (this.isSideMenued == false) goto L65;
        r7 = 64;
    L65:
        r16.setLayoutParams(LayoutHelper.createFrame(-1, 36.0f, 51, 35.0f, 0.0f, r7 + 36, 0.0f));
        return;
    L67:
        if (r23 == 0) goto L150;
        if (r23 == 2) goto L150;
        if (r23 != 4) goto L103;
        this.selector.setBackground(Theme.getSelectorDrawable(false));
        this.frameLayout.setBackgroundColor(0);
        this.frameLayout.setTag(Integer.valueOf(Theme.key_inappPlayerBackground));
        this.muteButton.setVisibility(8);
        this.subtitleTextView.setVisibility(0);
        int r17 = 0;
    L72:
        if (r17 >= 2) goto L81;
        AudioPlayerAlert.ClippingTextViewSwitcher r211 = this.titleTextView;
        if (r17 != 0) goto L76;
        TextView r212 = r211.getTextView();
    L77:
        if (r212 == null) goto L80;
        r212.setGravity(51);
        r212.setTextColor(getThemedColor(Theme.key_inappPlayerPerformer));
        r212.setTypeface(AndroidUtilities.bold());
        r212.setTextSize(1, 15.0f);
    L80:
        r17 = r17 + 1;
        goto L72
    L76:
        r212 = r211.getNextTextView();
        goto L77
    L81:
        this.titleTextView.setTag(Integer.valueOf(Theme.key_inappPlayerPerformer));
        this.titleTextView.setPadding(0, 0, this.joinButtonWidth, 0);
        this.importingImageView.setVisibility(8);
        this.importingImageView.stopAnimation();
        ChatActivityInterface r18 = this.chatActivity;
        if (r18 != null) goto L84;
    L90:
        r5 = false;
    L91:
        AvatarsImageView r19 = this.avatars;
        if (r5 == true) goto L94;
        int r213 = 0;
    L95:
        r19.setVisibility(r213);
        if (this.avatars.getVisibility() == 8) goto L98;
        updateAvatars(false);
    L99:
        this.closeButton.setVisibility(8);
        this.playButton.setVisibility(8);
        ActionBarMenuItem r110 = this.playbackSpeedButton;
        if (r110 == null) goto L198;
        r110.setVisibility(8);
        this.playbackSpeedButton.setTag(null);
        return;
    L198:
        return;
    L98:
        this.titleTextView.setTranslationX(-AndroidUtilities.dp(36.0f));
        this.subtitleTextView.setTranslationX(-AndroidUtilities.dp(36.0f));
        goto L99
    L94:
        r213 = 8;
        goto L95
    L84:
        if (r18.getGroupCall() == null) goto L90;
        if (this.chatActivity.getGroupCall().call == null) goto L90;
        if (this.chatActivity.getGroupCall().call.rtmp_stream == false) goto L90;
    L103:
        if (r23 == 1) goto L105;
        if (r23 == 3) goto L105;
        return;
    L105:
        this.selector.setBackground(null);
        updateCallTitle();
        boolean r214 = VoIPService.hasRtmpStream();
        AvatarsImageView r6 = this.avatars;
        if (r214 == true) goto L108;
        int r82 = 0;
    L109:
        r6.setVisibility(r82);
        if (r23 != 3) goto L115;
        if (VoIPService.getSharedInstance() == null) goto L115;
        VoIPService.getSharedInstance().registerStateListener(this);
    L115:
        if (this.avatars.getVisibility() == 8) goto L117;
        updateAvatars(false);
    L118:
        RLottieImageView r111 = this.muteButton;
        if (r214 == true) goto L121;
        int r215 = 0;
    L122:
        r111.setVisibility(r215);
        if (VoIPService.getSharedInstance() != null) goto L125;
    L127:
        boolean r112 = false;
    L128:
        this.isMuted = r112;
        RLottieDrawable r216 = this.muteDrawable;
        if (r112 == false) goto L131;
        int r113 = 15;
    L132:
        r216.setCustomEndFrame(r113);
        RLottieDrawable r114 = this.muteDrawable;
        r114.setCurrentFrame(r114.getCustomEndFrame() - 1, false, true);
        this.muteButton.invalidate();
        this.frameLayout.setBackground(null);
        this.frameLayout.setBackgroundColor(0);
        this.importingImageView.setVisibility(8);
        this.importingImageView.stopAnimation();
        Theme.getFragmentContextViewWavesDrawable().addParent(this);
        this.capsuleBlobDrawable.start();
        invalidate();
        int r115 = 0;
    L133:
        if (r115 >= 2) goto L142;
        AudioPlayerAlert.ClippingTextViewSwitcher r217 = this.titleTextView;
        if (r115 != 0) goto L137;
        TextView r218 = r217.getTextView();
    L138:
        if (r218 == null) goto L141;
        r218.setGravity(19);
        r218.setTextColor(getThemedColor(Theme.key_returnToCallText));
        r218.setTypeface(AndroidUtilities.bold());
        r218.setTextSize(1, 14.0f);
    L141:
        r115 = r115 + 1;
        goto L133
    L137:
        r218 = r217.getNextTextView();
        goto L138
    L142:
        this.titleTextView.setTag(Integer.valueOf(Theme.key_returnToCallText));
        this.closeButton.setVisibility(8);
        this.playButton.setVisibility(8);
        this.subtitleTextView.setVisibility(8);
        this.joinButton.setVisibility(8);
        AudioPlayerAlert.ClippingTextViewSwitcher r116 = this.titleTextView;
        if (this.isSideMenued == true) goto L146;
        r12 = 0;
    L146:
        r116.setLayoutParams(LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, 0.0f, r12, 0.0f));
        this.titleTextView.setPadding(AndroidUtilities.dp(88.0f), 0, AndroidUtilities.dp(88.0f) + this.joinButtonWidth, 0);
        ActionBarMenuItem r117 = this.playbackSpeedButton;
        if (r117 == null) goto L200;
        r117.setVisibility(8);
        this.playbackSpeedButton.setTag(null);
        return;
    L200:
        return;
    L131:
        r113 = 29;
        goto L132
    L125:
        if (VoIPService.getSharedInstance().isMicMute() == false) goto L127;
        r112 = true;
        goto L128
    L121:
        r215 = 8;
        goto L122
    L117:
        this.titleTextView.setTranslationX(0.0f);
        this.subtitleTextView.setTranslationX(0.0f);
        goto L118
    L108:
        r82 = 8;
    L150:
        this.selector.setBackground(Theme.getSelectorDrawable(false));
        this.frameLayout.setBackgroundColor(0);
        this.frameLayout.setTag(Integer.valueOf(Theme.key_inappPlayerBackground));
        this.subtitleTextView.setVisibility(8);
        this.joinButton.setVisibility(8);
        this.closeButton.setVisibility(0);
        this.playButton.setVisibility(0);
        this.muteButton.setVisibility(8);
        this.importingImageView.setVisibility(8);
        this.importingImageView.stopAnimation();
        this.avatars.setVisibility(8);
        int r3 = 0;
    L151:
        if (r3 >= 2) goto L160;
        AudioPlayerAlert.ClippingTextViewSwitcher r4 = this.titleTextView;
        if (r3 != 0) goto L155;
        TextView r42 = r4.getTextView();
    L156:
        if (r42 == null) goto L159;
        r42.setGravity(19);
        r42.setTextColor(getThemedColor(Theme.key_inappPlayerTitle));
        r42.setTypeface(Typeface.DEFAULT);
        r42.setTextSize(1, 15.0f);
    L159:
        r3 = r3 + 1;
        goto L151
    L155:
        r42 = r4.getNextTextView();
        goto L156
    L160:
        this.titleTextView.setTag(Integer.valueOf(Theme.key_inappPlayerTitle));
        if (r23 != 6) goto L167;
        this.playButton.setLayoutParams(LayoutHelper.createFrame(36, 36.0f, 51, 8.0f, 0.0f, 0.0f, 0.0f));
        AudioPlayerAlert.ClippingTextViewSwitcher r118 = this.titleTextView;
        if (this.isSideMenued == false) goto L165;
        r7 = 64;
    L165:
        r118.setLayoutParams(LayoutHelper.createFrame(-1, 36.0f, 51, 51.0f, 0.0f, r7 + 36, 0.0f));
        this.closeButton.setVisibility(8);
        return;
    L167:
        ImageView r219 = this.playButton;
        if (r23 != 0) goto L178;
        r219.setLayoutParams(LayoutHelper.createFrame(36, 36.0f, 51, 3.0f, 0.0f, 0.0f, 0.0f));
        AudioPlayerAlert.ClippingTextViewSwitcher r119 = this.titleTextView;
        if (this.isSideMenued == true) goto L173;
        r12 = 0;
    L173:
        r119.setLayoutParams(LayoutHelper.createFrame(-1, 36.0f, 51, 37.0f, 0.0f, r12 + 36, 0.0f));
        createPlaybackSpeedButton();
        ActionBarMenuItem r120 = this.playbackSpeedButton;
        if (r120 == null) goto L176;
        r120.setVisibility(0);
        this.playbackSpeedButton.setTag(1);
    L176:
        this.closeButton.setContentDescription(LocaleController.getString(R.string.AccDescrClosePlayer));
        return;
    L178:
        r219.setLayoutParams(LayoutHelper.createFrame(36, 36.0f, 51, 8.0f, 0.0f, 0.0f, 0.0f));
        AudioPlayerAlert.ClippingTextViewSwitcher r121 = this.titleTextView;
        if (this.isSideMenued == false) goto L181;
        r7 = 64;
    L181:
        r121.setLayoutParams(LayoutHelper.createFrame(-1, 36.0f, 51, 51.0f, 0.0f, r7 + 36, 0.0f));
        this.closeButton.setContentDescription(LocaleController.getString(R.string.AccDescrStopLiveLocation));
        return;
    L28:
        if (r26 == AndroidUtilities.dp2(getStyleHeight())) goto L30;
        setTopPadding(AndroidUtilities.dp2(getStyleHeight()));
        goto L30
    L18:
        r8 = false;
    L9:
        Theme.getFragmentContextViewWavesDrawable().removeParent(this);
        this.capsuleBlobDrawable.stop();
        if (VoIPService.getSharedInstance() == null) goto L12;
        VoIPService.getSharedInstance().unregisterStateListener(this);
    L12:
        ReplaceAnimator<CallMessageItem> r220 = this.callMessagesAnimator;
        if (r220 == null) goto L15;
        r220.replace(null, true);
        goto L15
    }

    public void checkCall(boolean r17) {
        VoIPService r1 = VoIPService.getSharedInstance();
        if (this.visible == true) goto L5;
    L10:
        View r2 = this.fragment.getFragmentView();
        if (r17 == true) goto L18;
        if (r2 == null) goto L18;
        if (r2.getParent() != null) goto L16;
    L17:
        boolean r22 = true;
    L20:
        if (GroupCallPip.isShowing() == false) goto L24;
        boolean r4 = false;
    L22:
        boolean r6 = r4;
    L54:
        if (r4 == true) goto L90;
        boolean r12 = this.visible;
        if (r12 == false) goto L74;
        if (r22 == true) goto L59;
    L60:
        int r42 = this.currentStyle;
        if (r42 == 4) goto L64;
        if (r42 == 3) goto L64;
        if (r42 != 1) goto L74;
    L64:
        this.visible = false;
        if (r22 == true) goto L67;
        AnimatorSet r13 = this.animatorSet;
        if (r13 == null) goto L73;
        r13.cancel();
        this.animatorSet = null;
    L73:
        this.notificationsLocker.lock();
        AnimatorSet r14 = new AnimatorSet();
        this.animatorSet = r14;
        r14.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "topPadding", new float[]{0.0f})});
        this.animatorSet.setDuration(220);
        this.animatorSet.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animatorSet.addListener(new AnonymousClass19(this));
        this.animatorSet.start();
    L81:
        if (r22 == false) goto L192;
        ChatActivityInterface r15 = this.chatActivity;
        if (r15 != null) goto L85;
        return;
    L85:
        if (r15.openedWithLivestream() == true) goto L87;
        return;
    L87:
        if (GroupCallPip.isShowing() == true) goto L195;
        BulletinFactory.of(this.fragment).createSimpleBulletin(R.raw.linkbroken, LocaleController.getString(R.string.InviteExpired)).show();
        return;
    L195:
        return;
    L192:
        return;
    L67:
        if (getVisibility() == 8) goto L69;
        setVisibility(8);
    L69:
        setTopPadding(0.0f);
        goto L81
    L59:
        if (this.currentStyle == (-1)) goto L64;
    L74:
        if (r12 == false) goto L81;
        int r16 = this.currentStyle;
        if (r16 == (-1)) goto L80;
        if (r16 == 4) goto L80;
        if (r16 == 3) goto L80;
        if (r16 != 1) goto L81;
    L80:
        this.visible = false;
        setVisibility(8);
        goto L81
    L90:
        checkCreateView();
        if (r6 == false) goto L94;
        float r172 = 0.0f;
        int r43 = 4;
    L97:
        int r11 = this.currentStyle;
        if (r43 != r11) goto L100;
    L104:
        if (r43 != r11) goto L106;
    L113:
        if (r6 == false) goto L169;
        if (r11 == 4) goto L116;
    L118:
        boolean r18 = false;
    L119:
        updateStyle(4);
        ChatObject.Call r44 = this.chatActivity.getGroupCall();
        TLRPC.Chat r62 = this.chatActivity.getCurrentChat();
        if (r44.isScheduled() == true) goto L122;
        this.notifyButtonEnabled = false;
        this.joinButton.setVisibility(0);
        this.joinButton.setText(LocaleController.getString(R.string.VoipChatJoin));
        if (TextUtils.isEmpty(r44.call.title) == true) goto L144;
        this.titleTextView.setText(r44.call.title, false);
    L150:
        TLRPC.GroupCall r45 = r44.call;
        int r63 = r45.participants_count;
        AudioPlayerAlert.ClippingTextViewSwitcher r112 = this.subtitleTextView;
        if (r63 != 0) goto L158;
        if (r45.rtmp_stream == false) goto L155;
        int r46 = R.string.ViewersWatchingNobody;
    L156:
        r112.setText(LocaleController.getString(r46), false);
    L162:
        this.frameLayout.invalidate();
    L164:
        if (this.avatars.avatarsDrawable.wasDraw == false) goto L167;
        if (r18 == false) goto L167;
        boolean r19 = true;
    L168:
        updateAvatars(r19);
    L182:
        if (this.visible == true) goto L196;
        if (r22 == true) goto L188;
        AnimatorSet r110 = this.animatorSet;
        if (r110 == null) goto L187;
        r110.cancel();
        this.animatorSet = null;
    L187:
        this.animatorSet = new AnimatorSet();
        this.notificationsLocker2.lock();
        this.animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "topPadding", new float[]{AndroidUtilities.dp2(getStyleHeight())})});
        this.animatorSet.setDuration(220);
        this.animatorSet.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animatorSet.addListener(new AnonymousClass21(this));
        this.animatorSet.start();
    L189:
        this.visible = true;
        setVisibility(0);
        return;
    L188:
        setTopPadding(AndroidUtilities.dp2(getStyleHeight()));
        startJoinFlickerAnimation();
        goto L189
    L196:
        return;
    L167:
        r19 = false;
        goto L168
    L155:
        r46 = R.string.MembersTalkingNobody;
        goto L156
    L158:
        if (r45.rtmp_stream == false) goto L160;
        String r47 = "ViewersWatching";
    L161:
        r112.setText(LocaleController.formatPluralString(r47, r63, new Object[0]), false);
        goto L162
    L160:
        r47 = "Participants";
        goto L161
    L144:
        if (r44.call.rtmp_stream == false) goto L146;
        this.titleTextView.setText(LocaleController.getString(R.string.VoipChannelVoiceChat), false);
        goto L150
    L146:
        boolean r64 = ChatObject.isChannelOrGiga(r62);
        AudioPlayerAlert.ClippingTextViewSwitcher r113 = this.titleTextView;
        if (r64 == false) goto L149;
        r113.setText(LocaleController.getString(R.string.VoipChannelVoiceChat), false);
        goto L150
    L149:
        r113.setText(LocaleController.getString(R.string.VoipGroupVoiceChat), false);
        goto L150
    L122:
        if (this.gradientPaint != null) goto L124;
        TextPaint r114 = new TextPaint(1);
        this.gradientTextPaint = r114;
        r114.setColor(-1);
        this.gradientTextPaint.setTextSize(AndroidUtilities.dp(14.0f));
        this.gradientTextPaint.setTypeface(AndroidUtilities.bold());
        Paint r115 = new Paint(1);
        this.gradientPaint = r115;
        r115.setColor(-1);
        this.matrix = new Matrix();
    L124:
        this.notifyButtonEnabled = true;
        LocaleController.getString(R.string.VoipChatNotify);
        TLRPC.GroupCall r116 = r44.call;
        if (r116 != null) goto L127;
    L129:
        boolean r117 = false;
    L130:
        this.willBeNotified = r117;
        this.joinButton.setVisibility(8);
        if (TextUtils.isEmpty(r44.call.title) == true) goto L133;
        this.titleTextView.setText(r44.call.title, false);
    L137:
        this.subtitleTextView.setText(LocaleController.formatStartsTime(r44.call.schedule_date, 4), false);
        if (this.scheduleRunnableScheduled == true) goto L164;
        this.scheduleRunnableScheduled = true;
        this.updateScheduleTimeRunnable.run();
        goto L164
    L133:
        boolean r65 = ChatObject.isChannelOrGiga(r62);
        AudioPlayerAlert.ClippingTextViewSwitcher r118 = this.titleTextView;
        if (r65 == false) goto L136;
        r118.setText(LocaleController.getString(R.string.VoipChannelScheduledVoiceChat), false);
        goto L137
    L136:
        r118.setText(LocaleController.getString(R.string.VoipGroupScheduledVoiceChat), false);
        goto L137
    L127:
        if (r116.schedule_start_subscribed == false) goto L129;
        r117 = true;
        goto L130
    L116:
        if (this.visible == false) goto L118;
        r18 = true;
        goto L119
    L169:
        if (r1 != null) goto L171;
    L177:
        if (r11 != 1) goto L179;
        boolean r111 = true;
    L180:
        updateAvatars(r111);
        updateStyle(1);
        goto L182
    L179:
        r111 = false;
        goto L180
    L171:
        if (r1.groupCall == null) goto L177;
        if (r11 != 3) goto L175;
        boolean r48 = true;
    L176:
        updateAvatars(r48);
        updateStyle(3);
        goto L182
    L175:
        r48 = false;
        goto L176
    L106:
        if (this.visible == false) goto L113;
        if (r22 == true) goto L113;
        AnimatorSet r119 = this.animatorSet;
        if (r119 == null) goto L111;
        r119.cancel();
        this.animatorSet = null;
    L111:
        this.notificationsLocker.lock();
        AnimatorSet r120 = new AnimatorSet();
        this.animatorSet = r120;
        r120.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "topPadding", new float[]{r172})});
        this.animatorSet.setDuration(220);
        this.animatorSet.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animatorSet.addListener(new AnonymousClass20(this));
        this.animatorSet.start();
        return;
    L100:
        if (this.animatorSet == null) goto L104;
        if (r22 == true) goto L104;
        this.checkCallAfterAnimation = true;
        return;
    L94:
        if (r1.groupCall == null) goto L96;
        r172 = 0.0f;
        r43 = 3;
        goto L97
    L96:
        r43 = 1;
        r172 = 0.0f;
        goto L97
    L24:
        if (GroupCallActivity.groupCallUiVisible == false) goto L26;
    L31:
        r4 = false;
    L32:
        if (r1 == null) goto L39;
        ChatObject.Call r66 = r1.groupCall;
        if (r66 == null) goto L39;
        if ((r66.call instanceof TLRPC.TL_groupCallDiscarded) == false) goto L39;
        r4 = false;
    L39:
        if (isPlayingVoice() == false) goto L41;
    L52:
        r6 = false;
        goto L54
    L41:
        if (GroupCallActivity.groupCallUiVisible == true) goto L52;
        if (this.supportsCalls == false) goto L52;
        if (r4 == true) goto L52;
        ChatActivityInterface r67 = this.chatActivity;
        if (r67 == null) goto L52;
        ChatObject.Call r68 = r67.getGroupCall();
        if (r68 == null) goto L52;
        if (r68.shouldShowPanel() == false) goto L52;
        r4 = true;
        goto L22
    L26:
        if (this.supportsCalls == false) goto L31;
        if (r1 == null) goto L31;
        if (r1.isHangingUp() == true) goto L31;
        r4 = true;
        goto L32
    L16:
        if (((View) r2.getParent()).getVisibility() != 0) goto L17;
    L18:
        r22 = r17;
        goto L20
    L5:
        if (this.currentStyle != 5) goto L10;
        if (r1 != null) goto L8;
        return;
    L8:
        if (r1.isHangingUp() == false) goto L10;
    }

    public void checkImport(boolean r10) {
        if (this.chatActivity != null) goto L5;
        return;
    L5:
        if (this.visible == false) goto L11;
        int r0 = this.currentStyle;
        if (r0 != 1) goto L9;
        return;
    L9:
        if (r0 != 3) goto L11;
        return;
    L11:
        checkCreateView();
        SendMessagesHelper.ImportingHistory r02 = this.fragment.getSendMessagesHelper().getImportingHistory(this.chatActivity.getDialogId());
        View r2 = this.fragment.getFragmentView();
        if (r10 == true) goto L19;
        if (r2 == null) goto L19;
        if (r2.getParent() != null) goto L17;
    L18:
        r10 = true;
        goto L19
    L17:
        if (((View) r2.getParent()).getVisibility() != 0) goto L18;
    L19:
        Dialog r22 = this.fragment.getVisibleDialog();
        if (isPlayingVoice() == false) goto L22;
    L27:
        if (r02 == null) goto L30;
        r02 = null;
    L30:
        if (r02 != null) goto L56;
        if (this.visible == false) goto L50;
        if (r10 == false) goto L37;
        if (this.currentStyle != (-1)) goto L37;
    L38:
        this.visible = false;
        if (r10 == true) goto L41;
        AnimatorSet r102 = this.animatorSet;
        if (r102 == null) goto L48;
        r102.cancel();
        this.animatorSet = null;
    L48:
        this.notificationsLocker.lock();
        AnimatorSet r103 = new AnimatorSet();
        this.animatorSet = r103;
        r103.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "topPadding", new float[]{0.0f})});
        this.animatorSet.setDuration(220);
        this.animatorSet.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animatorSet.addListener(new AnonymousClass14(this));
        this.animatorSet.start();
        return;
    L41:
        if (getVisibility() == 8) goto L43;
        setVisibility(8);
    L43:
        setTopPadding(0.0f);
        return;
    L37:
        if (this.currentStyle == 5) goto L38;
    L50:
        int r104 = this.currentStyle;
        if (r104 == (-1)) goto L53;
        if (r104 == 5) goto L53;
        return;
    L53:
        this.visible = false;
        setVisibility(8);
        return;
    L56:
        if (this.currentStyle != 5) goto L58;
    L62:
        updateStyle(5);
        if (r10 == false) goto L70;
        if (this.topPadding != 0.0f) goto L70;
        setTopPadding(AndroidUtilities.dp2(getStyleHeight()));
        FragmentContextViewDelegate r3 = this.delegate;
        if (r3 == null) goto L70;
        r3.onAnimation(true, true);
        this.delegate.onAnimation(false, true);
    L70:
        if (this.visible == true) goto L80;
        if (r10 == true) goto L79;
        AnimatorSet r105 = this.animatorSet;
        if (r105 == null) goto L75;
        r105.cancel();
        this.animatorSet = null;
    L75:
        this.notificationsLocker.lock();
        this.animatorSet = new AnimatorSet();
        FragmentContextViewDelegate r106 = this.delegate;
        if (r106 == null) goto L78;
        r106.onAnimation(true, true);
    L78:
        this.animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "topPadding", new float[]{AndroidUtilities.dp2(getStyleHeight())})});
        this.animatorSet.setDuration(200);
        this.animatorSet.addListener(new AnonymousClass15(this));
        this.animatorSet.start();
    L79:
        this.visible = true;
        setVisibility(0);
    L80:
        int r107 = this.currentProgress;
        int r03 = r02.uploadProgress;
        if (r107 == r03) goto L87;
        this.currentProgress = r03;
        this.titleTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("ImportUploading", R.string.ImportUploading, new Object[]{Integer.valueOf(r03)})), false);
        return;
    L87:
        return;
    L58:
        if (this.animatorSet == null) goto L62;
        if (r10 == true) goto L62;
        this.checkImportAfterAnimation = true;
        return;
    L22:
        if (this.chatActivity.shouldShowImport() == true) goto L27;
        if ((r22 instanceof ImportingAlert) == false) goto L30;
        if (((ImportingAlert) r22).isDismissed() == true) goto L30;
        goto L27
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r13, int r14, Object... r15) {
        if (r13 != NotificationCenter.liveLocationsChanged) goto L7;
        checkLiveLocation(false);
        return;
    L7:
        if (r13 != NotificationCenter.liveStoryUpdated) goto L11;
        checkLiveStory(false);
        return;
    L11:
        if (r13 != NotificationCenter.liveLocationsCacheChanged) goto L19;
        if (this.chatActivity != null) goto L15;
        return;
    L15:
        if (this.chatActivity.getDialogId() != ((Long) r15[0]).longValue()) goto L142;
        checkLocationString();
        return;
    L142:
        return;
    L19:
        if (r13 != NotificationCenter.messagePlayingDidStart) goto L21;
    L134:
        int r132 = this.currentStyle;
        if (r132 == 1) goto L138;
        if (r132 == 3) goto L138;
        if (r132 == 4) goto L138;
    L139:
        checkPlayer(false);
        return;
    L138:
        checkCall(false);
        goto L139
    L21:
        if (r13 == NotificationCenter.messagePlayingPlayStateChanged) goto L134;
        if (r13 == NotificationCenter.messagePlayingDidReset) goto L134;
        if (r13 == NotificationCenter.didEndCall) goto L134;
        int r142 = NotificationCenter.didStartedCall;
        if (r13 != r142) goto L30;
    L105:
        checkCall(false);
        if (this.currentStyle != 3) goto L148;
        VoIPService r152 = VoIPService.getSharedInstance();
        if (r152 != null) goto L110;
        return;
    L110:
        if (r152.groupCall == null) goto L150;
        if (r13 != r142) goto L113;
        r152.registerStateListener(this);
    L113:
        int r133 = r152.getCallState();
        if (r133 != 1) goto L116;
        return;
    L116:
        if (r133 != 2) goto L118;
        return;
    L118:
        if (r133 != 6) goto L120;
        return;
    L120:
        if (r133 != 5) goto L123;
        return;
    L123:
        if (this.muteButton == null) goto L154;
        TLRPC.GroupCallParticipant r134 = r152.groupCall.participants.get(r152.getSelfId());
        if (r134 != null) goto L127;
        return;
    L127:
        if (r134.can_self_unmute == false) goto L129;
        return;
    L129:
        if (r134.muted == true) goto L131;
        return;
    L131:
        if (ChatObject.canManageCalls(r152.getChat()) == true) goto L158;
        r152.setMicMute(true, false, false);
        long r4 = SystemClock.uptimeMillis();
        this.muteButton.dispatchTouchEvent(MotionEvent.obtain(r4, r4, 3, 0.0f, 0.0f, 0));
        return;
    L158:
        return;
    L154:
        return;
    L150:
        return;
    L148:
        return;
    L30:
        if (r13 == NotificationCenter.groupCallUpdated) goto L105;
        if (r13 == NotificationCenter.groupCallVisibilityChanged) goto L105;
        if (r13 != NotificationCenter.groupCallTypingsUpdated) goto L62;
        checkCreateView();
        if (this.visible == true) goto L39;
        return;
    L39:
        if (this.currentStyle != 4) goto L144;
        ChatObject.Call r135 = this.chatActivity.getGroupCall();
        if (r135 != null) goto L43;
    L59:
        updateAvatars(true);
        return;
    L43:
        if (this.subtitleTextView == null) goto L59;
        if (r135.isScheduled() == false) goto L47;
        this.subtitleTextView.setText(LocaleController.formatStartsTime(r135.call.schedule_date, 4), false);
        goto L59
    L47:
        TLRPC.GroupCall r136 = r135.call;
        int r143 = r136.participants_count;
        AudioPlayerAlert.ClippingTextViewSwitcher r153 = this.subtitleTextView;
        if (r143 != 0) goto L55;
        if (r136.rtmp_stream == false) goto L52;
        int r137 = R.string.ViewersWatchingNobody;
    L53:
        r153.setText(LocaleController.getString(r137), false);
        goto L59
    L52:
        r137 = R.string.MembersTalkingNobody;
        goto L53
    L55:
        if (r136.rtmp_stream == false) goto L57;
        String r138 = "ViewersWatching";
    L58:
        r153.setText(LocaleController.formatPluralString(r138, r143, new Object[0]), false);
        goto L59
    L57:
        r138 = "Participants";
        goto L58
    L144:
        return;
    L62:
        if (r13 != NotificationCenter.historyImportProgressChanged) goto L71;
        int r139 = this.currentStyle;
        if (r139 == 1) goto L67;
        if (r139 == 3) goto L67;
        if (r139 == 4) goto L67;
    L68:
        checkImport(false);
        return;
    L67:
        checkCall(false);
        goto L68
    L71:
        if (r13 != NotificationCenter.messagePlayingSpeedChanged) goto L75;
        updatePlaybackButton(true);
        return;
    L75:
        if (r13 != NotificationCenter.webRtcMicAmplitudeEvent) goto L88;
        if (VoIPService.getSharedInstance() != null) goto L79;
    L82:
        this.micAmplitude = 0.0f;
    L84:
        if (VoIPService.getSharedInstance() == null) goto L145;
        Theme.getFragmentContextViewWavesDrawable().setAmplitude(Math.max(this.speakerAmplitude, this.micAmplitude));
        this.capsuleBlobDrawable.setAmplitude(Math.max(this.speakerAmplitude, this.micAmplitude));
        return;
    L145:
        return;
    L79:
        if (VoIPService.getSharedInstance().isMicMute() == true) goto L82;
        this.micAmplitude = Math.min(8500.0f, ((Float) r15[0]).floatValue() * 4000.0f) / 8500.0f;
        goto L84
    L88:
        if (r13 != NotificationCenter.webRtcSpeakerAmplitudeEvent) goto L100;
        checkCreateView();
        this.speakerAmplitude = Math.max(0.0f, Math.min((((Float) r15[0]).floatValue() * 15.0f) / 80.0f, 1.0f));
        if (VoIPService.getSharedInstance() != null) goto L92;
    L93:
        this.micAmplitude = 0.0f;
    L95:
        if (VoIPService.getSharedInstance() == null) goto L97;
        Theme.getFragmentContextViewWavesDrawable().setAmplitude(Math.max(this.speakerAmplitude, this.micAmplitude));
        this.capsuleBlobDrawable.setAmplitude(Math.max(this.speakerAmplitude, this.micAmplitude));
    L97:
        this.avatars.invalidate();
        return;
    L92:
        if (VoIPService.getSharedInstance().isMicMute() == false) goto L95;
    L100:
        if (r13 == NotificationCenter.messagePlayingProgressDidChanged) goto L102;
        return;
    L102:
        if (this.currentStyle != 0) goto L147;
        invalidate();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r19) {
        if (this.frameLayout != null) goto L6;
        return;
    L6:
        if (this.drawOverlay == true) goto L8;
    L10:
        int r0 = this.currentStyle;
        if (r0 == 3) goto L15;
        if (r0 == 1) goto L15;
        FragmentContextView r10 = this;
    L21:
        super.dispatchDraw(r19);
        if (r10.currentStyle != 0) goto L26;
        MessageObject r02 = MediaController.getInstance().getPlayingMessageObject();
        if (r02 == null) goto L26;
        float r1 = -AndroidUtilities.dpf2(1.0f);
        float r3 = AndroidUtilities.lerp(r1, r10.getMeasuredWidth() + AndroidUtilities.dpf2(1.0f), r02.audioProgress);
        float r4 = r10.getMeasuredHeight();
        float r2 = r4 - AndroidUtilities.dpf2(2.0f);
        r10.progressPaint.setColor(r10.getThemedColor(Theme.key_telegram_color));
        r19.drawRoundRect(r1, r2, r3, r4, AndroidUtilities.dpf2(1.0f), AndroidUtilities.dpf2(1.0f), r10.progressPaint);
    L26:
        r10.wasDraw = true;
        return;
    L15:
        Theme.getFragmentContextViewWavesDrawable().updateState(this.wasDraw);
        this.capsuleBlobDrawable.updateState(this.wasDraw);
        float r7 = this.topPadding / AndroidUtilities.dp(getStyleHeight());
        if (this.collapseTransition == false) goto L19;
        Theme.getFragmentContextViewWavesDrawable().draw(0.0f, this.extraHeight + 0.0f, getMeasuredWidth(), getMeasuredHeight(), r19, null, Math.min(r7, 1.0f - this.collapseProgress));
    L18:
        r10 = this;
        r10.invalidate();
        goto L21
    L19:
        Theme.getFragmentContextViewWavesDrawable().draw(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), r19, this, r7);
        goto L18
    L8:
        if (getVisibility() == 0) goto L10;
    }

    public CapsuleBlobDrawable getCapsuleBlobDrawable() {
        return this.capsuleBlobDrawable;
    }

    public int getCurrentStyle() {
        return this.currentStyle;
    }

    public int getStyleHeight() {
        if (this.currentStyle != 4) goto L6;
        return 48;
    L6:
        return 36;
    }

    @Keep
    public float getTopPadding() {
        return this.topPadding;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        int r0 = this.currentStyle;
        if (r0 == 3) goto L7;
        if (r0 == 1) goto L7;
        return;
    L7:
        if (getParent() == null) goto L11;
        ((View) getParent()).invalidate();
        return;
    }

    public boolean isCallStyle() {
        int r2 = this.currentStyle;
        if (r2 == 3) goto L8;
        if (r2 == 1) goto L8;
        return false;
    L8:
        return true;
    }

    public boolean isCallTypeVisible() {
        int r0 = this.currentStyle;
        if (r0 == 1) goto L7;
        if (r0 == 3) goto L7;
        return false;
    L7:
        if (this.visible == false) goto L11;
        return true;
    L11:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeObservers();
        int r1 = 15;
        if (this.isLocation == false) goto L5;
        this.globalObserversGroup = NotificationCenter.getGlobalInstance().createObserversGroup(this).add(NotificationCenter.liveLocationsChanged).add(NotificationCenter.liveLocationsCacheChanged);
        checkLiveLocation(true);
    L40:
        int r0 = this.currentStyle;
        if (r0 == 3) goto L49;
        if (r0 == 1) goto L49;
        if (r0 != 4) goto L68;
        if (this.scheduleRunnableScheduled == true) goto L68;
        this.scheduleRunnableScheduled = true;
        this.updateScheduleTimeRunnable.run();
    L68:
        if (this.visible == true) goto L70;
    L72:
        this.speakerAmplitude = 0.0f;
        this.micAmplitude = 0.0f;
        return;
    L70:
        if (this.topPadding != 0.0f) goto L72;
        setTopPadding(AndroidUtilities.dp2(getStyleHeight()));
    L49:
        Theme.getFragmentContextViewWavesDrawable().addParent(this);
        this.capsuleBlobDrawable.start();
        if (VoIPService.getSharedInstance() == null) goto L53;
        VoIPService.getSharedInstance().registerStateListener(this);
    L53:
        if (VoIPService.getSharedInstance() != null) goto L55;
    L57:
        boolean r02 = false;
    L59:
        if (this.isMuted == r02) goto L68;
        if (this.muteButton == null) goto L68;
        this.isMuted = r02;
        RLottieDrawable r4 = this.muteDrawable;
        if (r02 == true) goto L66;
        r1 = 29;
    L66:
        r4.setCustomEndFrame(r1);
        RLottieDrawable r03 = this.muteDrawable;
        r03.setCurrentFrame(r03.getCustomEndFrame() - 1, false, true);
        this.muteButton.invalidate();
        goto L68
    L55:
        if (VoIPService.getSharedInstance().isMicMute() == false) goto L57;
        r02 = true;
        goto L59
    L5:
        int r04 = 0;
    L7:
        if (r04 >= 16) goto L9;
        this.observersGroup[r04] = NotificationCenter.getInstance(r04).createObserversGroup(this).add(NotificationCenter.messagePlayingDidReset).add(NotificationCenter.messagePlayingPlayStateChanged).add(NotificationCenter.messagePlayingDidStart).add(NotificationCenter.groupCallUpdated).add(NotificationCenter.groupCallTypingsUpdated).add(NotificationCenter.historyImportProgressChanged).add(NotificationCenter.liveStoryUpdated).add(NotificationCenter.messagePlayingProgressDidChanged);
        GroupCallMessagesController.getInstance(r04).subscribeToCallMessages(0, this);
        r04 = r04 + 1;
        goto L7
    L9:
        this.globalObserversGroup = NotificationCenter.getGlobalInstance().createObserversGroup(this).add(NotificationCenter.messagePlayingSpeedChanged).add(NotificationCenter.didStartedCall).add(NotificationCenter.didEndCall).add(NotificationCenter.webRtcSpeakerAmplitudeEvent).add(NotificationCenter.webRtcMicAmplitudeEvent).add(NotificationCenter.groupCallVisibilityChanged);
        if (LivePlayer.recording == null) goto L13;
        checkLiveStory(true);
        goto L40
    L13:
        if (VoIPService.getSharedInstance() == null) goto L22;
        if (VoIPService.getSharedInstance().isHangingUp() == true) goto L22;
        if (VoIPService.getSharedInstance().getCallState() == 15) goto L22;
        if (GroupCallPip.isShowing() == true) goto L22;
        checkCall(true);
    L22:
        if (this.chatActivity != null) goto L24;
    L28:
        ChatActivityInterface r05 = this.chatActivity;
        if (r05 != null) goto L31;
    L39:
        checkCall(true);
        checkPlayer(true);
        updatePlaybackButton(false);
        goto L40
    L31:
        if (r05.getGroupCall() == null) goto L39;
        if (this.chatActivity.getGroupCall().shouldShowPanel() == false) goto L39;
        if (GroupCallPip.isShowing() == true) goto L39;
        if (isPlayingVoice() == true) goto L39;
        checkCall(true);
        goto L40
    L24:
        if (this.fragment.getSendMessagesHelper().getImportingHistory(this.chatActivity.getDialogId()) == null) goto L28;
        if (isPlayingVoice() == true) goto L28;
        checkImport(true);
        goto L40
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onAudioSettingsChanged() {
        if (VoIPService.getSharedInstance() != null) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if (this.isMuted == r0) goto L16;
        this.isMuted = r0;
        RLottieDrawable r3 = this.muteDrawable;
        if (r0 == false) goto L13;
        int r02 = 15;
    L14:
        r3.setCustomEndFrame(r02);
        RLottieDrawable r03 = this.muteDrawable;
        r03.setCurrentFrame(r03.getCustomEndFrame() - 1, false, true);
        this.muteButton.invalidate();
        Theme.getFragmentContextViewWavesDrawable().updateState(this.visible);
        this.capsuleBlobDrawable.updateState(this.visible);
        goto L16
    L13:
        r02 = 29;
    L16:
        if (this.isMuted == false) goto L19;
        this.micAmplitude = 0.0f;
        Theme.getFragmentContextViewWavesDrawable().setAmplitude(0.0f);
        this.capsuleBlobDrawable.setAmplitude(0.0f, false);
        return;
    L19:
        return;
    L5:
        if (VoIPService.getSharedInstance().isMicMute() == false) goto L7;
        r0 = true;
        goto L9
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimatorSet r0 = this.animatorSet;
        if (r0 == null) goto L6;
        r0.cancel();
        this.animatorSet = null;
    L6:
        if (this.scheduleRunnableScheduled == false) goto L8;
        AndroidUtilities.cancelRunOnUIThread(this.updateScheduleTimeRunnable);
        this.scheduleRunnableScheduled = false;
    L8:
        this.visible = false;
        this.notificationsLocker.unlock();
        this.topPadding = 0.0f;
        removeObservers();
        if (this.isLocation == true) goto L14;
        int r02 = 0;
    L12:
        if (r02 >= 16) goto L14;
        GroupCallMessagesController.getInstance(r02).unsubscribeFromCallMessages(0, this);
        r02 = r02 + 1;
    L14:
        int r03 = this.currentStyle;
        if (r03 != 3) goto L17;
    L18:
        Theme.getFragmentContextViewWavesDrawable().removeParent(this);
        this.capsuleBlobDrawable.stop();
    L20:
        if (VoIPService.getSharedInstance() == null) goto L22;
        VoIPService.getSharedInstance().unregisterStateListener(this);
    L22:
        this.wasDraw = false;
        return;
    L17:
        if (r03 != 1) goto L20;
        goto L18
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, AndroidUtilities.dp2(getStyleHeight()));
    }

    @Override // org.telegram.messenger.voip.GroupCallMessagesController.CallMessageListener
    public void onNewGroupCallMessage(long r5, GroupCallMessage r7) {
        if (this.groupCallMessagesContainer == null) goto L21;
        int r0 = this.currentStyle;
        if (r0 == 1) goto L11;
        if (r0 == 3) goto L11;
        return;
    L11:
        if (VoIPService.getSharedInstance() != null) goto L13;
        return;
    L13:
        if (VoIPService.getSharedInstance().getGroupCallID() != r5) goto L23;
        this.groupCallMessageCounter++;
        if (r7.isOut() == true) goto L20;
        this.callMessagesAnimator.replace(new CallMessageItem(this.groupCallMessagesContainer, r7), true);
        return;
    L20:
        return;
    L23:
        return;
    }

    public void onPanTranslationUpdate(float r2) {
        HintView r1 = this.speedHintView;
        if (r1 == null) goto L6;
        r1.setExtraTranslationY(AndroidUtilities.dp(72.0f) + r2);
        return;
    }

    @Override // org.telegram.messenger.voip.GroupCallMessagesController.CallMessageListener
    public void onPopGroupCallMessage() {
        int r0 = this.groupCallMessageCounter;
        if (r0 <= 0) goto L8;
        int r02 = r0 - 1;
        this.groupCallMessageCounter = r02;
        if (r02 != 0) goto L9;
        this.callMessagesAnimator.replace(null, true);
        return;
    L9:
        return;
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onStateChanged(int r1) {
        updateCallTitle();
    }

    public void setDelegate(FragmentContextViewDelegate r1) {
        this.delegate = r1;
    }

    public void setDrawOverlay(boolean r1) {
        this.drawOverlay = r1;
    }

    public void setLeftMargin(float r2) {
        if (this.frameLayout != null) goto L6;
        this.leftMargin = r2;
        return;
    L6:
        ImageView r0 = this.playButton;
        if (r0 == null) goto L9;
        r0.setTranslationX(r2);
    L9:
        RLottieImageView r02 = this.importingImageView;
        if (r02 == null) goto L12;
        r02.setTranslationX(r2);
    L12:
        AudioPlayerAlert.ClippingTextViewSwitcher r03 = this.titleTextView;
        if (r03 == null) goto L15;
        r03.setTranslationX(r2);
    L15:
        AudioPlayerAlert.ClippingTextViewSwitcher r04 = this.subtitleTextView;
        if (r04 == null) goto L18;
        r04.setTranslationX(r2);
    L18:
        AvatarsImageView r1 = this.avatars;
        if (r1 == null) goto L22;
        r1.setTranslationX(r2);
        return;
    }

    public void setSpeedHintViewParent(ViewGroup r1) {
        this.speedHintViewParent = r1;
    }

    public void setSupportsCalls(boolean r1) {
        this.supportsCalls = r1;
    }

    @Keep
    public void setTopPadding(float r1) {
        this.topPadding = r1;
    }

    @Override // android.view.View
    public void setVisibility(int r2) {
        super.setVisibility(r2);
        setTopPadding(this.topPadding);
        if (r2 != 8) goto L6;
        this.wasDraw = false;
        return;
    }

    public void toggleScheduledNotify() {
        if (this.fragment == null) goto L27;
        ChatActivityInterface r0 = this.chatActivity;
        if (r0 == null) goto L29;
        ChatObject.Call r02 = r0.getGroupCall();
        if (r02 != null) goto L10;
        return;
    L10:
        if (r02.call != null) goto L13;
        return;
    L13:
        if (this.toggleGroupCallStartSubscriptionReqId == 0) goto L15;
        this.fragment.getConnectionsManager().cancelRequest(this.toggleGroupCallStartSubscriptionReqId, true);
        this.toggleGroupCallStartSubscriptionReqId = 0;
    L15:
        TL_phone.toggleGroupCallStartSubscription r1 = new TL_phone.toggleGroupCallStartSubscription();
        r1.call = r02.getInputGroupCall();
        TLRPC.GroupCall r03 = r02.call;
        boolean r3 = true ^ this.willBeNotified;
        this.willBeNotified = r3;
        r03.schedule_start_subscribed = r3;
        r1.subscribed = r3;
        this.toggleGroupCallStartSubscriptionReqId = this.fragment.getConnectionsManager().sendRequest(r1, null);
        if (this.scheduleRunnableScheduled == false) goto L18;
        AndroidUtilities.cancelRunOnUIThread(this.updateScheduleTimeRunnable);
        this.scheduleRunnableScheduled = false;
    L18:
        this.updateScheduleTimeRunnable.run();
        BulletinFactory r04 = BulletinFactory.of(this.fragment);
        boolean r5 = this.willBeNotified;
        if (r5 == false) goto L21;
        int r12 = R.raw.silent_unmute;
    L22:
        if (r5 == false) goto L24;
        int r52 = R.string.LiveStreamWillNotify;
    L25:
        r04.createSimpleBulletin(r12, LocaleController.getString(r52)).show();
        return;
    L24:
        r52 = R.string.LiveStreamWillNotNotify;
        goto L25
    L21:
        r12 = R.raw.silent_mute;
        goto L22
    L29:
        return;
    }

    public void updateColors() {
        if (equals(MediaController.getInstance().getPlaybackSpeed(this.isMusic), 1.0f) == true) goto L5;
        int r0 = Theme.key_featuredStickers_addButtonPressed;
    L6:
        int r02 = getThemedColor(r0);
        SpeedIconDrawable r1 = this.speedIcon;
        if (r1 == null) goto L9;
        r1.setColor(r02);
    L9:
        ActionBarMenuItem r12 = this.playbackSpeedButton;
        if (r12 == null) goto L12;
        r12.setBackground(Theme.createSelectorDrawable(r02 & 436207615, 1, AndroidUtilities.dp(14.0f)));
    L12:
        ImageView r03 = this.playButton;
        if (r03 == null) goto L15;
        r03.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_inappPlayerPlayPause), PorterDuff.Mode.MULTIPLY));
    L15:
        ImageView r04 = this.closeButton;
        if (r04 == null) goto L19;
        r04.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_inappPlayerClose), PorterDuff.Mode.MULTIPLY));
    L19:
        if (this.subtitleTextView == null) goto L30;
        int r05 = 0;
    L21:
        if (r05 >= 2) goto L30;
        AudioPlayerAlert.ClippingTextViewSwitcher r3 = this.subtitleTextView;
        if (r05 != 0) goto L25;
        TextView r32 = r3.getTextView();
    L26:
        if (r32 == null) goto L29;
        r32.setTextColor(getThemedColor(Theme.key_inappPlayerClose));
    L29:
        r05 = r05 + 1;
        goto L21
    L25:
        r32 = r3.getNextTextView();
    L30:
        AudioPlayerAlert.ClippingTextViewSwitcher r06 = this.titleTextView;
        if (r06 == null) goto L50;
        Object r07 = r06.getTag();
        if ((r07 instanceof Integer) == false) goto L59;
        int r08 = ((Integer) r07).intValue();
        int r33 = 0;
    L35:
        if (r33 >= 2) goto L60;
        AudioPlayerAlert.ClippingTextViewSwitcher r4 = this.titleTextView;
        if (r33 != 0) goto L39;
        TextView r42 = r4.getTextView();
    L40:
        if (r42 == null) goto L49;
        r42.setTextColor(getThemedColor(r08));
        CharSequence r43 = r42.getText();
        if ((r43 instanceof Spanned) == false) goto L49;
        TypefaceSpan[] r44 = (TypefaceSpan[]) ((Spanned) r43).getSpans(0, r43.length(), TypefaceSpan.class);
        if (r44 == null) goto L49;
        int r5 = r44.length;
        int r6 = 0;
    L47:
        if (r6 >= r5) goto L49;
        r44[r6].setColor(getThemedColor(Theme.key_inappPlayerPerformer));
        r6 = r6 + 1;
    L49:
        r33 = r33 + 1;
        goto L35
    L39:
        r42 = r4.getNextTextView();
        goto L40
    L60:
        return;
    L59:
        return;
    L50:
        return;
    L5:
        r0 = Theme.key_inappPlayerClose;
        goto L6
    }
}
