package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.camera.core.ImageCapture$$ExternalSyntheticBackport0;
import androidx.core.content.FileProvider;
import androidx.core.graphics.ColorUtils;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.pillstack.ui.pills.crypto.RatePill$$ExternalSyntheticLambda1;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.framework.CastContext;
import java.io.File;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.FileRefController;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.audioinfo.AudioInfo;
import org.telegram.messenger.chromecast.ChromecastController;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSlider;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.CastSync;
import org.telegram.ui.Cells.AudioPlayerCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.ChooseQualityLayout$QualityIcon;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SeekBarView;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.SelectAudioAlert;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes7.dex */
public class AudioPlayerAlert extends BottomSheet implements NotificationCenter.NotificationCenterDelegate, DownloadController.FileDownloadProgressListener {
    public static AudioPlayerAlert instance;
    private static final float[] speeds = null;
    private final int TAG;
    private final ActionBar actionBar;
    private AnimatorSet actionBarAnimation;
    private View actionBarBackground;
    private float actionBarSlide;
    private final Property<ActionBar, Float> actionBarSlideProperty;
    private ActionBarMenuItem addItem;
    private ClippingTextViewSwitcher authorTextView;
    private BackupImageView bigAlbumConver;
    private boolean blurredAnimationInProgress;
    private FrameLayout blurredView;
    private final View[] buttons;
    private boolean castAvailable;
    private ActionBarMenuSubItem castItem;
    private CastMediaRouteButton castItemButton;
    private CoverContainer coverContainer;
    private boolean currentAudioFinishedLoading;
    private String currentFile;
    private TextView durationTextView;
    private ImageView emptyImageView;
    private TextView emptySubtitleTextView;
    private TextView emptyTitleTextView;
    private LinearLayout emptyView;
    private final Runnable forwardSeek;
    private final boolean isProfilePlaylist;
    private ItemTouchHelper itemTouchHelper;
    private long lastBufferedPositionCheck;
    private int lastDuration;
    private MessageObject lastMessageObject;
    private long lastPlaybackClick;
    long lastRewindingTime;
    private int lastTime;
    long lastUpdateRewindingPlayerTime;
    private LinearLayoutManager layoutManager;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private RLottieImageView nextButton;
    private boolean noCover;
    private boolean noforwards;
    private ActionBarMenuItem optionsButton;
    private ChooseQualityLayout$QualityIcon optionsIcon;
    private final boolean padWithItem;
    private final LaunchActivity parentActivity;
    private ImageView playButton;
    private PlayPauseDrawable playPauseDrawable;
    private ActionBarMenuItem playbackSpeedButton;
    private FrameLayout playerLayout;
    private ArrayList<MessageObject> playlist;
    private RLottieImageView prevButton;
    private LineProgressView progressView;
    private ActionBarMenuItem repeatButton;
    private ActionBarMenuSubItem repeatListItem;
    private ActionBarMenuSubItem repeatSongItem;
    private ActionBarMenuSubItem reverseOrderItem;
    int rewindingForwardPressedCount;
    float rewindingProgress;
    int rewindingState;
    private ButtonWithCounterView saveToProfileButton;
    private MessagesController.SavedMusicList savedMusicList;
    private int scrollOffsetY;
    private boolean scrollToSong;
    private ActionBarMenuItem searchItem;
    private int searchOpenOffset;
    private int searchOpenPosition;
    private boolean searchWas;
    private boolean searching;
    private SpringAnimation seekBarBufferSpring;
    private SeekBarView seekBarView;
    private ActionBarMenuSubItem shuffleListItem;
    private boolean slidingSpeed;
    private HintView speedHintView;
    private SpeedIconDrawable speedIcon;
    private ActionBarMenuSubItem[] speedItems;
    private ActionBarMenuSlider.SpeedSlider speedSlider;
    private SimpleTextView timeTextView;
    private ClippingTextViewSwitcher titleTextView;
    private ButtonWithCounterView unsaveFromProfileButton;
    private boolean wasLight;

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass1(AudioPlayerAlert r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            long r0 = MediaController.getInstance().getDuration();
            if (r0 != 0) goto L5;
        L45:
            this.this$0.lastRewindingTime = System.currentTimeMillis();
            return;
        L5:
            if (r0 == (-9223372036854775807L)) goto L45;
            float r2 = this.this$0.rewindingProgress;
            long r3 = System.currentTimeMillis();
            AudioPlayerAlert r5 = this.this$0;
            long r6 = r3 - r5.lastRewindingTime;
            r5.lastRewindingTime = r3;
            long r8 = r3 - r5.lastUpdateRewindingPlayerTime;
            int r10 = r5.rewindingForwardPressedCount;
            if (r10 != 1) goto L12;
            long r12 = 3;
        L15:
            float r1 = ((long) ((r2 * r0) + ((r12 * r6) - r6))) / r0;
            if (r1 >= 0.0f) goto L18;
            r1 = 0.0f;
        L18:
            r5.rewindingProgress = r1;
            MessageObject r22 = MediaController.getInstance().getPlayingMessageObject();
            if (r22 != null) goto L21;
        L28:
            AudioPlayerAlert r23 = this.this$0;
            if (r23.rewindingState == 1) goto L31;
            return;
        L31:
            if (r23.rewindingForwardPressedCount > 0) goto L33;
            return;
        L33:
            if (MediaController.getInstance().isMessagePaused() == true) goto L35;
            return;
        L35:
            if (r8 <= 200) goto L37;
        L38:
            this.this$0.lastUpdateRewindingPlayerTime = r3;
            MediaController.getInstance().seekToProgress(MediaController.getInstance().getPlayingMessageObject(), r1);
        L39:
            AudioPlayerAlert r14 = this.this$0;
            if (r14.rewindingForwardPressedCount > 0) goto L42;
            return;
        L42:
            if (r14.rewindingProgress <= 0.0f) goto L51;
            AndroidUtilities.runOnUIThread(AudioPlayerAlert.m8874$$Nest$fgetforwardSeek(r14), 16);
            return;
        L51:
            return;
        L37:
            if (this.this$0.rewindingProgress != 0.0f) goto L39;
        L21:
            if (r22.isMusic() == true) goto L25;
            if (r22.isVoice() == false) goto L28;
        L25:
            if (MediaController.getInstance().isMessagePaused() == true) goto L27;
            MediaController.getInstance().getPlayingMessageObject().audioProgress = this.this$0.rewindingProgress;
        L27:
            AudioPlayerAlert.m8917$$Nest$mupdateProgress(this.this$0, r22);
            goto L28
        L12:
            if (r10 != 2) goto L14;
            r12 = 6;
            goto L15
        L14:
            r12 = 12;
            goto L15
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$10, reason: invalid class name */
    public class AnonymousClass10 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass10(AudioPlayerAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public CharSequence getContentDescription() {
            return LocaleController.formatString("AccDescrPlayerDuration", R.string.AccDescrPlayerDuration, new Object[]{LocaleController.formatPluralString("Minutes", AudioPlayerAlert.m8878$$Nest$fgetlastTime(this.this$0) / 60, new Object[0]) + ' ' + LocaleController.formatPluralString("Seconds", AudioPlayerAlert.m8878$$Nest$fgetlastTime(this.this$0) % 60, new Object[0]), LocaleController.formatPluralString("Minutes", AudioPlayerAlert.m8877$$Nest$fgetlastDuration(this.this$0) / 60, new Object[0]) + ' ' + LocaleController.formatPluralString("Seconds", AudioPlayerAlert.m8877$$Nest$fgetlastDuration(this.this$0) % 60, new Object[0])});
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r2, float r3) {
            if (r2 == false) goto L4;
            MediaController.getInstance().seekToProgress(MediaController.getInstance().getPlayingMessageObject(), r3);
        L4:
            MessageObject r22 = MediaController.getInstance().getPlayingMessageObject();
            if (r22 != null) goto L7;
            return;
        L7:
            if (r22.isMusic() == false) goto L9;
        L10:
            AudioPlayerAlert.m8917$$Nest$mupdateProgress(this.this$0, r22);
            return;
        L9:
            if (r22.isVoice() == true) goto L10;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarPressed(boolean r1) {
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$11, reason: invalid class name */
    public class AnonymousClass11 extends FrameLayout {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass11(AudioPlayerAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
            int r62 = ((r6 - r4) - AndroidUtilities.dp(248.0f)) / 4;
            int r32 = 0;
        L4:
            if (r32 >= 5) goto L6;
            int r42 = AndroidUtilities.dp((r32 * 48) + 4) + (r62 * r32);
            int r52 = AndroidUtilities.dp(9.0f);
            AudioPlayerAlert.m8871$$Nest$fgetbuttons(this.this$0)[r32].layout(r42, r52, AudioPlayerAlert.m8871$$Nest$fgetbuttons(this.this$0)[r32].getMeasuredWidth() + r42, AudioPlayerAlert.m8871$$Nest$fgetbuttons(this.this$0)[r32].getMeasuredHeight() + r52);
            r32 = r32 + 1;
            goto L4
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$12, reason: invalid class name */
    public class AnonymousClass12 extends RLottieImageView {
        private final Runnable backSeek;
        long lastTime;
        long lastUpdateTime;
        int pressedCount;
        private final Runnable pressedRunnable;
        long startTime;
        float startX;
        float startY;
        final /* synthetic */ AudioPlayerAlert this$0;
        final /* synthetic */ float val$touchSlop;

        /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$12$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ AnonymousClass12 this$1;

            public AnonymousClass1(AnonymousClass12 r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                AnonymousClass12 r0 = this.this$1;
                int r1 = r0.pressedCount + 1;
                r0.pressedCount = r1;
                if (r1 != 1) goto L7;
                AudioPlayerAlert r02 = r0.this$0;
                r02.rewindingState = -1;
                r02.rewindingProgress = MediaController.getInstance().getPlayingMessageObject().audioProgress;
                AnonymousClass12 r03 = this.this$1;
                r03.lastTime = System.currentTimeMillis();
                AndroidUtilities.runOnUIThread(this, 2000);
                AndroidUtilities.runOnUIThread(AnonymousClass12.m8918$$Nest$fgetbackSeek(this.this$1));
                return;
            L7:
                if (r1 != 2) goto L10;
                AndroidUtilities.runOnUIThread(this, 2000);
                return;
            }
        }

        /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$12$2, reason: invalid class name */
        public class AnonymousClass2 implements Runnable {
            final /* synthetic */ AnonymousClass12 this$1;

            public AnonymousClass2(AnonymousClass12 r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                long r0 = MediaController.getInstance().getDuration();
                if (r0 != 0) goto L5;
            L43:
                this.this$1.lastTime = System.currentTimeMillis();
                return;
            L5:
                if (r0 == (-9223372036854775807L)) goto L43;
                float r2 = this.this$1.this$0.rewindingProgress;
                long r3 = System.currentTimeMillis();
                AnonymousClass12 r5 = this.this$1;
                long r6 = r3 - r5.lastTime;
                r5.lastTime = r3;
                long r8 = r3 - r5.lastUpdateTime;
                int r10 = r5.pressedCount;
                if (r10 != 1) goto L12;
                long r102 = 3;
            L15:
                float r1 = ((long) ((r2 * r0) - (r6 * r102))) / r0;
                if (r1 >= 0.0f) goto L18;
                r1 = 0.0f;
            L18:
                r5.this$0.rewindingProgress = r1;
                MessageObject r22 = MediaController.getInstance().getPlayingMessageObject();
                if (r22 != null) goto L21;
            L25:
                AnonymousClass12 r23 = this.this$1;
                AudioPlayerAlert r52 = r23.this$0;
                if (r52.rewindingState == (-1)) goto L28;
                return;
            L28:
                if (r23.pressedCount > 0) goto L30;
                return;
            L30:
                if (r8 <= 200) goto L32;
            L33:
                r23.lastUpdateTime = r3;
                if (r52.rewindingProgress != 0.0f) goto L36;
                MediaController.getInstance().seekToProgress(MediaController.getInstance().getPlayingMessageObject(), 0.0f);
                MediaController.getInstance().pauseByRewind();
            L37:
                AnonymousClass12 r12 = this.this$1;
                if (r12.pressedCount > 0) goto L40;
                return;
            L40:
                if (r12.this$0.rewindingProgress <= 0.0f) goto L48;
                AndroidUtilities.runOnUIThread(AnonymousClass12.m8918$$Nest$fgetbackSeek(r12), 16);
                return;
            L48:
                return;
            L36:
                MediaController.getInstance().seekToProgress(MediaController.getInstance().getPlayingMessageObject(), r1);
                goto L37
            L32:
                if (r52.rewindingProgress != 0.0f) goto L37;
            L21:
                if (r22.isMusic() == false) goto L23;
            L24:
                AudioPlayerAlert.m8917$$Nest$mupdateProgress(this.this$1.this$0, r22);
                goto L25
            L23:
                if (r22.isVoice() == false) goto L25;
            L12:
                if (r10 != 2) goto L14;
                r102 = 6;
                goto L15
            L14:
                r102 = 12;
                goto L15
            }
        }

        /* renamed from: -$$Nest$fgetbackSeek, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m8918$$Nest$fgetbackSeek(AnonymousClass12 r0) {
            return r0.backSeek;
        }

        public AnonymousClass12(AudioPlayerAlert r1, Context r2, float r3) {
            this.this$0 = r1;
            this.val$touchSlop = r3;
            super(r2);
            this.pressedCount = 0;
            this.pressedRunnable = new AnonymousClass1(this);
            this.backSeek = new AnonymousClass2(this);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            r1.addAction(16);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r10) {
            if (AudioPlayerAlert.m8899$$Nest$fgetseekBarView(this.this$0).isDragging() == false) goto L5;
        L36:
            return false;
        L5:
            if (this.this$0.rewindingState == 1) goto L36;
            float r0 = r10.getRawX();
            float r3 = r10.getRawY();
            int r4 = r10.getAction();
            if (r4 == 0) goto L31;
            if (r4 != 1) goto L11;
        L20:
            AndroidUtilities.cancelRunOnUIThread(this.pressedRunnable);
            AndroidUtilities.cancelRunOnUIThread(this.backSeek);
            if (this.this$0.rewindingState != 0) goto L28;
            if (r10.getAction() != 1) goto L28;
            if ((System.currentTimeMillis() - this.startTime) >= 300) goto L28;
            MediaController.getInstance().playPreviousMessage();
            AudioPlayerAlert.m8890$$Nest$fgetprevButton(this.this$0).setProgress(0.0f);
            AudioPlayerAlert.m8890$$Nest$fgetprevButton(this.this$0).playAnimation();
        L28:
            if (this.pressedCount <= 0) goto L30;
            this.lastUpdateTime = 0;
            this.backSeek.run();
            MediaController.getInstance().resumeByRewind();
        L30:
            this.this$0.rewindingProgress = -1.0f;
            setPressed(false);
            this.this$0.rewindingState = 0;
            this.pressedCount = 0;
        L35:
            return true;
        L11:
            if (r4 != 2) goto L13;
            float r02 = r0 - this.startX;
            float r32 = r3 - this.startY;
            float r03 = (r02 * r02) + (r32 * r32);
            float r102 = this.val$touchSlop;
            if (r03 <= (r102 * r102)) goto L35;
            if (this.this$0.rewindingState != 0) goto L35;
            AndroidUtilities.cancelRunOnUIThread(this.pressedRunnable);
            setPressed(false);
            goto L35
        L13:
            if (r4 == 3) goto L20;
        L31:
            this.startX = r0;
            this.startY = r3;
            this.startTime = System.currentTimeMillis();
            this.this$0.rewindingState = 0;
            AndroidUtilities.runOnUIThread(this.pressedRunnable, 300);
            if (getBackground() == null) goto L34;
            getBackground().setHotspot(this.startX, this.startY);
        L34:
            setPressed(true);
            goto L35
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$13, reason: invalid class name */
    public class AnonymousClass13 extends RLottieImageView {
        boolean pressed;
        private final Runnable pressedRunnable;
        float startX;
        float startY;
        final /* synthetic */ AudioPlayerAlert this$0;
        final /* synthetic */ float val$touchSlop;

        /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$13$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ AnonymousClass13 this$1;

            public AnonymousClass1(AnonymousClass13 r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (MediaController.getInstance().getPlayingMessageObject() != null) goto L5;
                return;
            L5:
                AnonymousClass13 r0 = this.this$1;
                AudioPlayerAlert r1 = r0.this$0;
                int r2 = r1.rewindingForwardPressedCount + 1;
                r1.rewindingForwardPressedCount = r2;
                if (r2 != 1) goto L16;
                r0.pressed = true;
                r1.rewindingState = 1;
                boolean r02 = MediaController.getInstance().isMessagePaused();
                AnonymousClass13 r12 = this.this$1;
                if (r02 == false) goto L10;
                AudioPlayerAlert.m8913$$Nest$mstartForwardRewindingSeek(r12.this$0);
            L13:
                MediaController.getInstance().setPlaybackSpeed(true, 4.0f);
                AndroidUtilities.runOnUIThread(this, 2000);
                return;
            L10:
                AudioPlayerAlert r03 = r12.this$0;
                if (r03.rewindingState != 1) goto L13;
                AndroidUtilities.cancelRunOnUIThread(AudioPlayerAlert.m8874$$Nest$fgetforwardSeek(r03));
                this.this$1.this$0.lastUpdateRewindingPlayerTime = 0;
                goto L13
            L16:
                if (r2 != 2) goto L19;
                MediaController.getInstance().setPlaybackSpeed(true, 7.0f);
                AndroidUtilities.runOnUIThread(this, 2000);
                return;
            L19:
                MediaController.getInstance().setPlaybackSpeed(true, 13.0f);
            }
        }

        public AnonymousClass13(AudioPlayerAlert r1, Context r2, float r3) {
            this.this$0 = r1;
            this.val$touchSlop = r3;
            super(r2);
            this.pressedRunnable = new AnonymousClass1(this);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            r1.addAction(16);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r7) {
            if (AudioPlayerAlert.m8899$$Nest$fgetseekBarView(this.this$0).isDragging() == false) goto L5;
        L38:
            return false;
        L5:
            if (this.this$0.rewindingState == (-1)) goto L38;
            float r0 = r7.getRawX();
            float r2 = r7.getRawY();
            int r3 = r7.getAction();
            if (r3 == 0) goto L33;
            if (r3 == 1) goto L21;
            if (r3 != 2) goto L13;
            float r02 = r0 - this.startX;
            float r22 = r2 - this.startY;
            float r03 = (r02 * r02) + (r22 * r22);
            float r72 = this.val$touchSlop;
            if (r03 > (r72 * r72)) goto L18;
        L37:
            return true;
        L18:
            if (this.pressed == true) goto L37;
            AndroidUtilities.cancelRunOnUIThread(this.pressedRunnable);
            setPressed(false);
            goto L37
        L13:
            if (r3 != 3) goto L37;
        L21:
            if (this.pressed == false) goto L23;
        L27:
            AndroidUtilities.cancelRunOnUIThread(this.pressedRunnable);
            if (this.this$0.rewindingForwardPressedCount <= 0) goto L32;
            MediaController.getInstance().setPlaybackSpeed(true, 1.0f);
            if (MediaController.getInstance().isMessagePaused() == false) goto L32;
            AudioPlayerAlert r73 = this.this$0;
            r73.lastUpdateRewindingPlayerTime = 0;
            AudioPlayerAlert.m8874$$Nest$fgetforwardSeek(r73).run();
        L32:
            this.this$0.rewindingState = 0;
            setPressed(false);
            AudioPlayerAlert r6 = this.this$0;
            r6.rewindingForwardPressedCount = 0;
            r6.rewindingProgress = -1.0f;
            goto L37
        L23:
            if (r7.getAction() != 1) goto L27;
            if (isPressed() == false) goto L27;
            MediaController.getInstance().playNextMessage();
            AudioPlayerAlert.m8882$$Nest$fgetnextButton(this.this$0).setProgress(0.0f);
            AudioPlayerAlert.m8882$$Nest$fgetnextButton(this.this$0).playAnimation();
            goto L27
        L33:
            this.pressed = false;
            this.startX = r0;
            this.startY = r2;
            AndroidUtilities.runOnUIThread(this.pressedRunnable, 300);
            if (getBackground() == null) goto L36;
            getBackground().setHotspot(this.startX, this.startY);
        L36:
            setPressed(true);
            goto L37
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$14, reason: invalid class name */
    public class AnonymousClass14 extends CastMediaRouteButton {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass14(AudioPlayerAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.CastMediaRouteButton
        public void stateUpdated(boolean r2) {
            this.this$0.updateColors();
            if (AudioPlayerAlert.m8884$$Nest$fgetoptionsIcon(this.this$0) == null) goto L6;
            AudioPlayerAlert.m8884$$Nest$fgetoptionsIcon(this.this$0).setCasting(CastSync.isActive(), true);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$15, reason: invalid class name */
    public class AnonymousClass15 extends RecyclerListView {
        boolean ignoreLayout;
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass15(AudioPlayerAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean allowSelectChildAtPosition(float r1, float r2) {
            if (r2 >= (AudioPlayerAlert.m8888$$Nest$fgetplayerLayout(this.this$0).getY() - AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).getTop())) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r6, int r7, int r8, int r9, int r10) {
            super.onLayout(r6, r7, r8, r9, r10);
            if (AudioPlayerAlert.m8896$$Nest$fgetsearchOpenPosition(this.this$0) == (-1)) goto L9;
            if (AudioPlayerAlert.m8866$$Nest$fgetactionBar(this.this$0).isSearchFieldVisible() == true) goto L9;
            this.ignoreLayout = true;
            AudioPlayerAlert.m8879$$Nest$fgetlayoutManager(this.this$0).scrollToPositionWithOffset(AudioPlayerAlert.m8896$$Nest$fgetsearchOpenPosition(this.this$0), AudioPlayerAlert.m8895$$Nest$fgetsearchOpenOffset(this.this$0) - AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).getPaddingTop());
            super.onLayout(false, r7, r8, r9, r10);
            this.ignoreLayout = false;
            AudioPlayerAlert.m8905$$Nest$fputsearchOpenPosition(this.this$0, -1);
            return;
        L9:
            if (AudioPlayerAlert.m8893$$Nest$fgetscrollToSong(this.this$0) == false) goto L15;
            AudioPlayerAlert.m8903$$Nest$fputscrollToSong(this.this$0, false);
            this.ignoreLayout = true;
            if (AudioPlayerAlert.m8910$$Nest$mscrollToCurrentSong(this.this$0, true) == false) goto L13;
            super.onLayout(false, r7, r8, r9, r10);
        L13:
            this.ignoreLayout = false;
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$16, reason: invalid class name */
    public class AnonymousClass16 extends RecyclerView.OnScrollListener {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass16(AudioPlayerAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r3, int r4) {
            if (r4 != 0) goto L14;
            int r42 = AndroidUtilities.dp(13.0f);
            if ((((AudioPlayerAlert.m8892$$Nest$fgetscrollOffsetY(this.this$0) - AudioPlayerAlert.access$2500(this.this$0)) - r42) + AudioPlayerAlert.access$2600(this.this$0)) < ActionBar.getCurrentActionBarHeight()) goto L7;
            return;
        L7:
            if (AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).canScrollVertically(1) == false) goto L18;
            RecyclerListView.Holder r32 = (RecyclerListView.Holder) AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).findViewHolderForAdapterPosition(AudioPlayerAlert.m8885$$Nest$fgetpadWithItem(this.this$0) ? 1 : 0);
            if (r32 != null) goto L11;
            return;
        L11:
            if (r32.itemView.getTop() <= AndroidUtilities.dp(7.0f)) goto L20;
            AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).smoothScrollBy(0, r32.itemView.getTop() - AndroidUtilities.dp(7.0f));
            return;
        L20:
            return;
        L18:
            return;
        L14:
            if (r4 != 1) goto L21;
            AndroidUtilities.hideKeyboard(this.this$0.getCurrentFocus());
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            AudioPlayerAlert.m8916$$Nest$mupdateLayout(this.this$0);
            AudioPlayerAlert.m8915$$Nest$mupdateEmptyViewPosition(this.this$0);
            if (AudioPlayerAlert.m8897$$Nest$fgetsearchWas(this.this$0) == true) goto L20;
            int r32 = AudioPlayerAlert.m8879$$Nest$fgetlayoutManager(this.this$0).findFirstVisibleItemPosition();
            int r0 = 0;
            if (AudioPlayerAlert.m8885$$Nest$fgetpadWithItem(this.this$0) == false) goto L8;
            r32 = Math.max(0, r32 - 1);
        L8:
            if (r32 == (-1)) goto L11;
            r0 = Math.abs(AudioPlayerAlert.m8879$$Nest$fgetlayoutManager(this.this$0).findLastVisibleItemPosition() - r32) + 1;
        L11:
            int r1 = r2.getAdapter().getItemCount();
            if (SharedConfig.playOrderReversed == false) goto L17;
            if (r32 >= 10) goto L21;
            MediaController.getInstance().loadMoreMusic();
            return;
        L21:
            return;
        L17:
            if ((r32 + r0) <= (r1 - 10)) goto L22;
            MediaController.getInstance().loadMoreMusic();
            return;
        L22:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$17, reason: invalid class name */
    public class AnonymousClass17 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass17(AudioPlayerAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            if (AudioPlayerAlert.m8898$$Nest$fgetsearching(this.this$0) == false) goto L8;
            AudioPlayerAlert.m8906$$Nest$fputsearchWas(this.this$0, false);
            AudioPlayerAlert.m8907$$Nest$fputsearching(this.this$0, false);
            this.this$0.setAllowNestedScroll(true);
            AudioPlayerAlert.m8880$$Nest$fgetlistAdapter(this.this$0).search(null);
            if (AudioPlayerAlert.m8868$$Nest$fgetaddItem(this.this$0) == null) goto L9;
            AudioPlayerAlert.m8868$$Nest$fgetaddItem(this.this$0).setVisibility(0);
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            AudioPlayerAlert r0 = this.this$0;
            AudioPlayerAlert.m8905$$Nest$fputsearchOpenPosition(r0, AudioPlayerAlert.m8879$$Nest$fgetlayoutManager(r0).findLastVisibleItemPosition());
            View r02 = AudioPlayerAlert.m8879$$Nest$fgetlayoutManager(this.this$0).findViewByPosition(AudioPlayerAlert.m8896$$Nest$fgetsearchOpenPosition(this.this$0));
            AudioPlayerAlert r1 = this.this$0;
            if (r02 != null) goto L5;
            int r03 = 0;
        L6:
            AudioPlayerAlert.m8904$$Nest$fputsearchOpenOffset(r1, r03);
            AudioPlayerAlert.m8907$$Nest$fputsearching(this.this$0, true);
            this.this$0.setAllowNestedScroll(false);
            AudioPlayerAlert.m8880$$Nest$fgetlistAdapter(this.this$0).notifyDataSetChanged();
            if (AudioPlayerAlert.m8868$$Nest$fgetaddItem(this.this$0) == null) goto L10;
            AudioPlayerAlert.m8868$$Nest$fgetaddItem(this.this$0).setVisibility(8);
            return;
        L10:
            return;
        L5:
            r03 = r02.getTop();
            goto L6
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r3) {
            int r0 = r3.length();
            AudioPlayerAlert r1 = this.this$0;
            if (r0 <= 0) goto L6;
            AudioPlayerAlert.m8880$$Nest$fgetlistAdapter(r1).search(r3.getText().toString());
            return;
        L6:
            AudioPlayerAlert.m8906$$Nest$fputsearchWas(r1, false);
            AudioPlayerAlert.m8880$$Nest$fgetlistAdapter(this.this$0).search(null);
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$18, reason: invalid class name */
    public class AnonymousClass18 extends ItemTouchHelper.Callback {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass18(AudioPlayerAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void clearView(RecyclerView r1, RecyclerView.ViewHolder r2) {
            super.clearView(r1, r2);
            r2.itemView.setPressed(false);
            r2.itemView.setTag(R.id.dragging, null);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView r1, RecyclerView.ViewHolder r2) {
            if (r2.getItemViewType() == 0) goto L6;
            return 0;
        L6:
            return ItemTouchHelper.Callback.makeMovementFlags(3, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView r3, RecyclerView.ViewHolder r4, RecyclerView.ViewHolder r5) {
            int r32 = r4.getAdapterPosition();
            int r42 = r5.getAdapterPosition();
            if (AudioPlayerAlert.m8885$$Nest$fgetpadWithItem(this.this$0) == false) goto L10;
            if (r32 <= 0) goto L8;
            if (r42 <= 0) goto L13;
            AudioPlayerAlert.m8891$$Nest$fgetsavedMusicList(this.this$0).move(r32 - 1, r42 - 1);
        L11:
            AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0).clear();
            AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0).addAll(AudioPlayerAlert.m8891$$Nest$fgetsavedMusicList(this.this$0).list);
            AudioPlayerAlert.m8880$$Nest$fgetlistAdapter(this.this$0).notifyItemMoved(r32, r42);
            return true;
        L13:
            return false;
        L8:
            return false;
        L10:
            AudioPlayerAlert.m8891$$Nest$fgetsavedMusicList(this.this$0).move(r32, r42);
            goto L11
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder r3, int r4) {
            if (r3 == null) goto L5;
            AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).hideSelector(false);
        L5:
            if (r4 == 0) goto L9;
            AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).cancelClickRunnables(false);
            if (r3 == null) goto L9;
            r3.itemView.setPressed(true);
        L9:
            super.onSelectedChanged(r3, r4);
            if (r3 == null) goto L17;
            View r2 = r3.itemView;
            int r32 = R.id.dragging;
            if (r4 != 2) goto L14;
            Boolean r42 = Boolean.TRUE;
        L15:
            r2.setTag(r32, r42);
            return;
        L14:
            r42 = null;
            goto L15
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$19, reason: invalid class name */
    public class AnonymousClass19 extends FrameLayout {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass19(AudioPlayerAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (AudioPlayerAlert.m8870$$Nest$fgetblurredView(this.this$0).getTag() == null) goto L5;
            AudioPlayerAlert.m8911$$Nest$mshowAlbumCover(this.this$0, false, true);
        L5:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        private boolean ignoreLayout;
        private int lastMeasturedHeight;
        private int lastMeasturedWidth;
        private final RectF rect;
        final /* synthetic */ AudioPlayerAlert this$0;
        final /* synthetic */ MessageObject val$messageObject;

        /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$2$1, reason: invalid class name */
        public class AnonymousClass1 implements Bulletin.Delegate {
            final /* synthetic */ AnonymousClass2 this$1;

            public AnonymousClass1(AnonymousClass2 r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public int getBottomOffset(int r1) {
                return AudioPlayerAlert.m8888$$Nest$fgetplayerLayout(this.this$1.this$0).getHeight();
            }
        }

        public AnonymousClass2(AudioPlayerAlert r1, Context r2, MessageObject r3) {
            this.this$0 = r1;
            this.val$messageObject = r3;
            super(r2);
            this.rect = new RectF();
            this.ignoreLayout = false;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            Bulletin.addDelegate(this, new AnonymousClass1(this));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            Bulletin.removeDelegate(this);
        }

        @Override // android.view.View
        public void onDraw(Canvas r15) {
            int r0 = AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0).size();
            AudioPlayerAlert r1 = this.this$0;
            if (r0 > 1) goto L9;
            AudioPlayerAlert.access$500(r1).setBounds(0, (getMeasuredHeight() - AudioPlayerAlert.m8888$$Nest$fgetplayerLayout(this.this$0).getMeasuredHeight()) - AudioPlayerAlert.access$400(this.this$0), getMeasuredWidth(), getMeasuredHeight());
            AudioPlayerAlert.access$600(this.this$0).draw(r15);
            if (AudioPlayerAlert.m8875$$Nest$fgetisProfilePlaylist(this.this$0) == false) goto L34;
            AudioPlayerAlert.m8866$$Nest$fgetactionBar(this.this$0).setVisibility(8);
            return;
        L34:
            return;
        L9:
            if (AudioPlayerAlert.m8881$$Nest$fgetlistView(r1).getVisibility() != 0) goto L36;
            int r02 = AndroidUtilities.dp(13.0f);
            int r12 = (int) (((AudioPlayerAlert.m8892$$Nest$fgetscrollOffsetY(this.this$0) - AudioPlayerAlert.access$700(this.this$0)) - r02) + AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).getTranslationY());
            if (AudioPlayerAlert.m8875$$Nest$fgetisProfilePlaylist(this.this$0) == false) goto L14;
            r12 = (r12 - ActionBar.getCurrentActionBarHeight()) + AndroidUtilities.dp(10.0f);
        L14:
            int r3 = AndroidUtilities.dp(20.0f) + r12;
            int r5 = (getMeasuredHeight() + AndroidUtilities.dp(15.0f)) + AudioPlayerAlert.access$800(this.this$0);
            if (AudioPlayerAlert.m8875$$Nest$fgetisProfilePlaylist(this.this$0) == false) goto L17;
        L19:
            float r6 = 0.0f;
            float r03 = 1.0f;
        L20:
            int r10 = AndroidUtilities.statusBarHeight;
            float r62 = 1.0f - r6;
            int r32 = r3 + ((int) (r10 * r62));
            AudioPlayerAlert.access$1100(this.this$0).setBounds(0, r12 + ((int) (r10 * r62)), getMeasuredWidth(), r5);
            AudioPlayerAlert.access$1200(this.this$0).draw(r15);
            if (AudioPlayerAlert.m8875$$Nest$fgetisProfilePlaylist(this.this$0) == true) goto L26;
            if (r03 == 1.0f) goto L26;
            Theme.dialogs_onlineCirclePaint.setColor(AudioPlayerAlert.access$1300(this.this$0, Theme.key_dialogBackground));
            this.rect.set(AudioPlayerAlert.access$1400(this.this$0), AudioPlayerAlert.access$1500(this.this$0) + r1, getMeasuredWidth() - AudioPlayerAlert.access$1600(this.this$0), (AudioPlayerAlert.access$1700(this.this$0) + r1) + AndroidUtilities.dp(24.0f));
            r15.drawRoundRect(this.rect, AndroidUtilities.dp(12.0f) * r03, AndroidUtilities.dp(12.0f) * r03, Theme.dialogs_onlineCirclePaint);
        L26:
            if (AudioPlayerAlert.m8875$$Nest$fgetisProfilePlaylist(this.this$0) == true) goto L31;
            if (r03 == 0.0f) goto L31;
            int r52 = AndroidUtilities.dp(36.0f);
            this.rect.set((getMeasuredWidth() - r52) / 2, r32, (getMeasuredWidth() + r52) / 2, r32 + AndroidUtilities.dp(4.0f));
            int r33 = AudioPlayerAlert.access$1800(this.this$0, Theme.key_sheet_scrollUp);
            int r53 = Color.alpha(r33);
            Theme.dialogs_onlineCirclePaint.setColor(r33);
            Theme.dialogs_onlineCirclePaint.setAlpha((int) ((r53 * 1.0f) * r03));
            r15.drawRoundRect(this.rect, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Theme.dialogs_onlineCirclePaint);
        L31:
            if (AudioPlayerAlert.m8875$$Nest$fgetisProfilePlaylist(this.this$0) == false) goto L35;
            AudioPlayerAlert.m8866$$Nest$fgetactionBar(this.this$0).setVisibility(0);
            AudioPlayerAlert.m8866$$Nest$fgetactionBar(this.this$0).setTranslationY(Math.max(0.0f, (((r1 - AudioPlayerAlert.access$1900(this.this$0)) - AndroidUtilities.dp(10.0f)) + (AndroidUtilities.dp(6.0f) * (1.0f - AudioPlayerAlert.m8867$$Nest$fgetactionBarSlide(this.this$0)))) - AudioPlayerAlert.m8866$$Nest$fgetactionBar(this.this$0).getTop()));
            return;
        L35:
            return;
        L17:
            if ((AudioPlayerAlert.access$900(this.this$0) + r12) >= ActionBar.getCurrentActionBarHeight()) goto L19;
            float r04 = r02 + AndroidUtilities.dp(4.0f);
            r6 = Math.min(1.0f, ((ActionBar.getCurrentActionBarHeight() - r12) - AudioPlayerAlert.access$1000(this.this$0)) / r04);
            int r05 = (int) ((ActionBar.getCurrentActionBarHeight() - r04) * r6);
            r12 = r12 - r05;
            r3 = r3 - r05;
            r5 = r5 + r05;
            r03 = 1.0f - r6;
            goto L20
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r4) {
            if (r4.getAction() != 0) goto L35;
            if (AudioPlayerAlert.m8892$$Nest$fgetscrollOffsetY(this.this$0) == 0) goto L27;
            if (AudioPlayerAlert.m8866$$Nest$fgetactionBar(this.this$0).getAlpha() != 0.0f) goto L27;
            if (AudioPlayerAlert.m8880$$Nest$fgetlistAdapter(this.this$0).getItemCount() > 0) goto L11;
            float r0 = r4.getY();
            int r1 = getMeasuredHeight();
            if (AudioPlayerAlert.m8908$$Nest$misMyList(this.this$0) == false) goto L16;
        L22:
            int r2 = 0;
        L24:
            if (r0 >= (r1 - AndroidUtilities.dp(r2 + 196))) goto L35;
        L32:
            this.this$0.dismiss();
            return true;
        L16:
            if (AudioPlayerAlert.m8883$$Nest$fgetnoforwards(this.this$0) == true) goto L22;
            MessageObject r22 = this.val$messageObject;
            if (r22 == null) goto L22;
            if (r22.isVoice() == true) goto L22;
            r2 = 52;
            goto L24
        L11:
            if (r4.getY() >= (AudioPlayerAlert.m8892$$Nest$fgetscrollOffsetY(this.this$0) + AndroidUtilities.dp(12.0f))) goto L35;
        L27:
            if (AudioPlayerAlert.m8892$$Nest$fgetscrollOffsetY(this.this$0) != 0) goto L35;
            if (AudioPlayerAlert.m8888$$Nest$fgetplayerLayout(this.this$0) == null) goto L35;
            if (r4.getY() < AudioPlayerAlert.m8888$$Nest$fgetplayerLayout(this.this$0).getTop()) goto L32;
        L35:
            return super.onInterceptTouchEvent(r4);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            AudioPlayerAlert.m8916$$Nest$mupdateLayout(this.this$0);
            AudioPlayerAlert.m8915$$Nest$mupdateEmptyViewPosition(this.this$0);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r8, int r9) {
            int r92 = View.MeasureSpec.getSize(r9);
            int r0 = View.MeasureSpec.getSize(r8);
            if (r92 != this.lastMeasturedHeight) goto L7;
            if (r0 != this.lastMeasturedWidth) goto L7;
        L10:
            this.ignoreLayout = true;
            FrameLayout r1 = AudioPlayerAlert.m8888$$Nest$fgetplayerLayout(this.this$0);
            if (AudioPlayerAlert.m8897$$Nest$fgetsearchWas(this.this$0) == false) goto L13;
        L16:
            int r3 = 4;
        L17:
            r1.setVisibility(r3);
            int r12 = r92 - getPaddingTop();
            ((FrameLayout.LayoutParams) AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).getLayoutParams()).topMargin = ActionBar.getCurrentActionBarHeight() + AndroidUtilities.statusBarHeight;
            ((FrameLayout.LayoutParams) AudioPlayerAlert.m8870$$Nest$fgetblurredView(this.this$0).getLayoutParams()).topMargin = -getPaddingTop();
            int r4 = 52;
            if (AudioPlayerAlert.m8908$$Nest$misMyList(this.this$0) == false) goto L20;
        L26:
            int r32 = 0;
        L27:
            int r33 = AndroidUtilities.dp(184 + r32);
            if (AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0).size() <= 1) goto L31;
            r33 = r33 + (AudioPlayerAlert.access$100(this.this$0) + (AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0).size() * AndroidUtilities.dp(56.0f)));
        L31:
            if (AudioPlayerAlert.m8898$$Nest$fgetsearching(this.this$0) == false) goto L33;
        L63:
            int r02 = AndroidUtilities.dp(8.0f);
        L65:
            if (AudioPlayerAlert.m8885$$Nest$fgetpadWithItem(this.this$0) == false) goto L68;
            r02 = 0;
        L68:
            if (AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).getPaddingTop() == r02) goto L76;
            RecyclerListView r13 = AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0);
            if (AudioPlayerAlert.m8898$$Nest$fgetsearching(this.this$0) == true) goto L72;
        L74:
            int r34 = AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).getPaddingBottom();
        L75:
            r13.setPadding(0, r02, 0, r34);
            goto L76
        L72:
            if (AudioPlayerAlert.access$300(this.this$0) == false) goto L74;
            r34 = 0;
        L76:
            this.ignoreLayout = false;
            super.onMeasure(r8, View.MeasureSpec.makeMeasureSpec(r92, TLObject.FLAG_30));
            return;
        L33:
            if (AudioPlayerAlert.access$200(this.this$0) == true) goto L63;
            if (r33 >= r12) goto L37;
            int r03 = r12 - r33;
        L38:
            r02 = r03 + AndroidUtilities.dp(8.0f);
            if (AudioPlayerAlert.m8908$$Nest$misMyList(this.this$0) == false) goto L41;
        L47:
            int r35 = 0;
        L49:
            if (r02 > (r12 - AndroidUtilities.dp(r35 + 334))) goto L51;
        L61:
            if (r02 >= 0) goto L65;
            r02 = 0;
            goto L65
        L51:
            if (AudioPlayerAlert.m8908$$Nest$misMyList(this.this$0) == false) goto L53;
        L59:
            r4 = 0;
        L60:
            r02 = r12 - AndroidUtilities.dp(r4 + 334);
            goto L61
        L53:
            if (AudioPlayerAlert.m8883$$Nest$fgetnoforwards(this.this$0) == true) goto L59;
            MessageObject r04 = this.val$messageObject;
            if (r04 == null) goto L59;
            if (r04.isVoice() == true) goto L59;
        L41:
            if (AudioPlayerAlert.m8883$$Nest$fgetnoforwards(this.this$0) == true) goto L47;
            MessageObject r36 = this.val$messageObject;
            if (r36 == null) goto L47;
            if (r36.isVoice() == true) goto L47;
            r35 = 52;
            goto L49
        L37:
            r03 = r12 - ((int) ((r12 / 5) * 3.5f));
            goto L38
        L20:
            if (AudioPlayerAlert.m8883$$Nest$fgetnoforwards(this.this$0) == true) goto L26;
            MessageObject r37 = this.val$messageObject;
            if (r37 == null) goto L26;
            if (r37.isVoice() == true) goto L26;
            r32 = 52;
            goto L27
        L13:
            if (AudioPlayerAlert.access$000(this.this$0) == true) goto L16;
            r3 = 0;
        L7:
            if (AudioPlayerAlert.m8870$$Nest$fgetblurredView(this.this$0).getTag() == null) goto L9;
            AudioPlayerAlert.m8911$$Nest$mshowAlbumCover(this.this$0, false, false);
        L9:
            this.lastMeasturedWidth = r0;
            this.lastMeasturedHeight = r92;
            goto L10
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.isDismissed() == false) goto L5;
            return false;
        L5:
            if (super.onTouchEvent(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$20, reason: invalid class name */
    public class AnonymousClass20 extends HintView {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass20(AudioPlayerAlert r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // android.view.View
        public void setVisibility(int r1) {
            super.setVisibility(r1);
            if (r1 == 0) goto L9;
            ((ViewGroup) getParent()).removeView(this);     // Catch: Exception -> L6
            return;
        L10:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$21, reason: invalid class name */
    public class AnonymousClass21 extends AnimatorListenerAdapter {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass21(AudioPlayerAlert r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            AudioPlayerAlert.m8902$$Nest$fputblurredAnimationInProgress(this.this$0, false);
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$22, reason: invalid class name */
    public class AnonymousClass22 extends AnimatorListenerAdapter {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass22(AudioPlayerAlert r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            AudioPlayerAlert.m8870$$Nest$fgetblurredView(this.this$0).setVisibility(4);
            AudioPlayerAlert.m8869$$Nest$fgetbigAlbumConver(this.this$0).setImageBitmap(null);
            AudioPlayerAlert.m8902$$Nest$fputblurredAnimationInProgress(this.this$0, false);
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$23, reason: invalid class name */
    public class AnonymousClass23 extends AnimatorListenerAdapter {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass23(AudioPlayerAlert r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            AudioPlayerAlert.m8900$$Nest$fputactionBarAnimation(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$24, reason: invalid class name */
    public class AnonymousClass24 extends AnimationProperties.FloatProperty<ActionBar> {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass24(AudioPlayerAlert r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        public Float get(ActionBar r1) {
            return Float.valueOf(AudioPlayerAlert.m8867$$Nest$fgetactionBarSlide(this.this$0));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((ActionBar) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(ActionBar r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(ActionBar r9, float r10) {
            AudioPlayerAlert.m8901$$Nest$fputactionBarSlide(this.this$0, r10);
            SimpleTextView r0 = r9.getTitleTextView();
            ImageView r92 = r9.getBackButton();
            float r4 = 1.0f - r10;
            r0.setTranslationX(AndroidUtilities.dp(-52.0f) * r4);
            r92.setTranslationX(AndroidUtilities.dp(-52.0f) * r4);
            if (AudioPlayerAlert.m8894$$Nest$fgetsearchItem(this.this$0) != null) goto L5;
        L16:
            r92.setScaleX(AndroidUtilities.lerp(0.6f, 1.0f, r10));
            r92.setScaleY(AndroidUtilities.lerp(0.6f, 1.0f, r10));
            r92.setAlpha(AndroidUtilities.lerp(0.0f, 1.0f, r10));
            AudioPlayerAlert.access$2700(this.this$0).invalidate();
            return;
        L5:
            if (AudioPlayerAlert.m8894$$Nest$fgetsearchItem(this.this$0).getSearchContainer() == null) goto L16;
            AudioPlayerAlert.m8894$$Nest$fgetsearchItem(this.this$0).getSearchContainer().setClipChildren(false);
            AudioPlayerAlert.m8894$$Nest$fgetsearchItem(this.this$0).getSearchContainer().setClipToPadding(false);
            FrameLayout r02 = AudioPlayerAlert.m8894$$Nest$fgetsearchItem(this.this$0).getSearchContainer();
            float r6 = 66.0f;
            if (AndroidUtilities.isTablet() == false) goto L9;
            float r5 = 74.0f;
        L10:
            r02.setPadding(0, 0, AndroidUtilities.dp(r5), 0);
            FrameLayout r03 = AudioPlayerAlert.m8894$$Nest$fgetsearchItem(this.this$0).getSearchContainer();
            if (AndroidUtilities.isTablet() == false) goto L13;
            r6 = 74.0f;
        L13:
            r03.setTranslationX(AndroidUtilities.dp(r6) + (AndroidUtilities.dp(-52.0f) * r4));
            if (AudioPlayerAlert.m8894$$Nest$fgetsearchItem(this.this$0).getSearchClearButton() == null) goto L16;
            AudioPlayerAlert.m8894$$Nest$fgetsearchItem(this.this$0).getSearchClearButton().setTranslationX(AndroidUtilities.dp(52.0f) * r4);
            goto L16
        L9:
            r5 = 66.0f;
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$3, reason: invalid class name */
    public class AnonymousClass3 extends ActionBar {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass3(AudioPlayerAlert r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            AudioPlayerAlert.access$2000(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$4, reason: invalid class name */
    public class AnonymousClass4 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass4(AudioPlayerAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            AudioPlayerAlert r1 = this.this$0;
            if (r2 != (-1)) goto L6;
            r1.dismiss();
            return;
        L6:
            AudioPlayerAlert.m8909$$Nest$monSubItemClick(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$5, reason: invalid class name */
    public class AnonymousClass5 extends FrameLayout {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass5(AudioPlayerAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            r7.drawLine(0.0f, 1.0f, getMeasuredWidth(), 1.0f, Theme.forcedDividerPaint);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (AudioPlayerAlert.m8887$$Nest$fgetplaybackSpeedButton(this.this$0) != null) goto L5;
            return;
        L5:
            if (AudioPlayerAlert.m8872$$Nest$fgetdurationTextView(this.this$0) == null) goto L9;
            int r12 = (AudioPlayerAlert.m8872$$Nest$fgetdurationTextView(this.this$0).getLeft() - AndroidUtilities.dp(4.0f)) - AudioPlayerAlert.m8887$$Nest$fgetplaybackSpeedButton(this.this$0).getMeasuredWidth();
            AudioPlayerAlert.m8887$$Nest$fgetplaybackSpeedButton(this.this$0).layout(r12, AudioPlayerAlert.m8887$$Nest$fgetplaybackSpeedButton(this.this$0).getTop(), AudioPlayerAlert.m8887$$Nest$fgetplaybackSpeedButton(this.this$0).getMeasuredWidth() + r12, AudioPlayerAlert.m8887$$Nest$fgetplaybackSpeedButton(this.this$0).getBottom());
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$6, reason: invalid class name */
    public class AnonymousClass6 extends CoverContainer {
        private long pressTime;
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass6(AudioPlayerAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.AudioPlayerAlert.CoverContainer
        public void onImageUpdated(ImageReceiver r2) {
            Bitmap r22 = r2.getBitmap();
            if (AudioPlayerAlert.m8870$$Nest$fgetblurredView(this.this$0).getTag() == null) goto L6;
            AudioPlayerAlert.m8869$$Nest$fgetbigAlbumConver(this.this$0).setImageBitmap(r22);
            return;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r6) {
            int r62 = r6.getAction();
            if (r62 != 0) goto L8;
            if (getImageReceiver().hasBitmapImage() == false) goto L12;
            AudioPlayerAlert.m8911$$Nest$mshowAlbumCover(this.this$0, true, true);
            this.pressTime = SystemClock.elapsedRealtime();
        L12:
            return true;
        L8:
            if (r62 == 2) goto L12;
            if ((SystemClock.elapsedRealtime() - this.pressTime) < 400) goto L12;
            AudioPlayerAlert.m8911$$Nest$mshowAlbumCover(this.this$0, false, true);
            goto L12
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$7, reason: invalid class name */
    public class AnonymousClass7 extends ClippingTextViewSwitcher {
        final /* synthetic */ AudioPlayerAlert this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass7(AudioPlayerAlert r1, Context r2, Context r3) {
            this.this$0 = r1;
            this.val$context = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Components.AudioPlayerAlert.ClippingTextViewSwitcher
        public TextView createTextView() {
            MarqueeTextView r0 = new MarqueeTextView(this.val$context);
            r0.setTextColor(AudioPlayerAlert.access$2100(this.this$0, Theme.key_player_actionBarTitle));
            r0.setTextSize(1, 16.0f);
            r0.setTypeface(AndroidUtilities.bold());
            r0.setEllipsize(TextUtils.TruncateAt.END);
            r0.setSingleLine(true);
            return r0;
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$8, reason: invalid class name */
    public class AnonymousClass8 extends ClippingTextViewSwitcher {
        final /* synthetic */ AudioPlayerAlert this$0;
        final /* synthetic */ Context val$context;

        /* renamed from: $r8$lambda$swkdzZ1ESEEP7Pk53dCff-XZYkQ, reason: not valid java name */
        public static /* synthetic */ void m8919$r8$lambda$swkdzZ1ESEEP7Pk53dCffXZYkQ(AnonymousClass8 r0, TextView r1, View r2) {
            r0.lambda$createTextView$0(r1, r2);
        }

        public AnonymousClass8(AudioPlayerAlert r1, Context r2, Context r3) {
            this.this$0 = r1;
            this.val$context = r3;
            super(r2);
        }

        private /* synthetic */ void lambda$createTextView$0(TextView r3, View r4) {
            if (MessagesController.getInstance(AudioPlayerAlert.access$2400(this.this$0)).getTotalDialogsCount() > 10) goto L5;
            return;
        L5:
            if (TextUtils.isEmpty(r3.getText().toString()) == true) goto L16;
            String r32 = r3.getText().toString();
            if ((AudioPlayerAlert.m8886$$Nest$fgetparentActivity(this.this$0).getActionBarLayout().getLastFragment() instanceof DialogsActivity) == false) goto L13;
            DialogsActivity r42 = (DialogsActivity) AudioPlayerAlert.m8886$$Nest$fgetparentActivity(this.this$0).getActionBarLayout().getLastFragment();
            if (r42.onlyDialogsAdapter() == true) goto L13;
            r42.setShowSearch(r32, 3);
            this.this$0.dismiss();
            return;
        L13:
            DialogsActivity r43 = new DialogsActivity(null);
            r43.setSearchString(r32);
            r43.setInitialSearchType(3);
            AudioPlayerAlert.m8886$$Nest$fgetparentActivity(this.this$0).presentFragment(r43, false, false);
            this.this$0.dismiss();
            return;
        }

        @Override // org.telegram.ui.Components.AudioPlayerAlert.ClippingTextViewSwitcher
        public TextView createTextView() {
            MarqueeTextView r0 = new MarqueeTextView(this.val$context);
            r0.setTextColor(AudioPlayerAlert.access$2200(this.this$0, Theme.key_player_time));
            r0.setTextSize(1, 13.0f);
            r0.setEllipsize(TextUtils.TruncateAt.END);
            r0.setSingleLine(true);
            r0.setPadding(AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(1.0f));
            r0.setBackground(Theme.createRadSelectorDrawable(AudioPlayerAlert.access$2300(this.this$0, Theme.key_listSelector), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f)));
            r0.setOnClickListener(new AudioPlayerAlert$8$$ExternalSyntheticLambda0(this, r0));
            return r0;
        }
    }

    /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$9, reason: invalid class name */
    public class AnonymousClass9 extends SeekBarView {
        final /* synthetic */ AudioPlayerAlert this$0;

        public AnonymousClass9(AudioPlayerAlert r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.SeekBarView
        public boolean onTouch(MotionEvent r2) {
            if (this.this$0.rewindingState == 0) goto L7;
            return false;
        L7:
            return super.onTouch(r2);
        }
    }

    public static abstract class ClippingTextViewSwitcher extends FrameLayout {
        private int activeIndex;
        private AnimatorSet animatorSet;
        private final float[] clipProgress;
        private final Paint erasePaint;
        private final Matrix gradientMatrix;
        private final Paint gradientPaint;
        private LinearGradient gradientShader;
        private final int gradientSize;
        private boolean isCenter;
        private final RectF rectF;
        private int rightPadding;
        private int stableOffest;
        private final TextView[] textViews;

        /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$ClippingTextViewSwitcher$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ ClippingTextViewSwitcher this$0;
            final /* synthetic */ int val$prevIndex;

            public AnonymousClass1(ClippingTextViewSwitcher r1, int r2) {
                this.this$0 = r1;
                this.val$prevIndex = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                ClippingTextViewSwitcher.m8921$$Nest$fgettextViews(this.this$0)[this.val$prevIndex].setVisibility(8);
            }
        }

        public static /* synthetic */ void $r8$lambda$6L9Wx8TLROSD28xytT6UeTjA4VY(ClippingTextViewSwitcher r0, int r1, ValueAnimator r2) {
            r0.lambda$setText$1(r1, r2);
        }

        /* renamed from: $r8$lambda$a-Q4KUF9A6LdowAvxBgNXtwk_8c, reason: not valid java name */
        public static /* synthetic */ void m8920$r8$lambda$aQ4KUF9A6LdowAvxBgNXtwk_8c(ClippingTextViewSwitcher r0, int r1, ValueAnimator r2) {
            r0.lambda$setText$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgettextViews, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView[] m8921$$Nest$fgettextViews(ClippingTextViewSwitcher r0) {
            return r0.textViews;
        }

        public ClippingTextViewSwitcher(Context r5) {
            super(r5);
            this.textViews = new TextView[2];
            this.clipProgress = new float[]{0.0f, 0.75f};
            this.gradientSize = AndroidUtilities.dp(24.0f);
            this.stableOffest = -1;
            this.rectF = new RectF();
            int r0 = 0;
        L4:
            if (r0 >= 2) goto L9;
            this.textViews[r0] = createTextView();
            if (r0 != 1) goto L8;
            this.textViews[r0].setAlpha(0.0f);
            this.textViews[r0].setVisibility(8);
        L8:
            addView(this.textViews[r0], LayoutHelper.createFrame(-2, -1.0f));
            r0 = r0 + 1;
            goto L4
        L9:
            this.gradientMatrix = new Matrix();
            Paint r52 = new Paint(1);
            this.gradientPaint = r52;
            r52.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            Paint r53 = new Paint(1);
            this.erasePaint = r53;
            r53.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }

        private /* synthetic */ void lambda$setText$0(int r2, ValueAnimator r3) {
            this.clipProgress[r2] = ((Float) r3.getAnimatedValue()).floatValue();
            invalidate();
        }

        private /* synthetic */ void lambda$setText$1(int r2, ValueAnimator r3) {
            this.clipProgress[r2] = ((Float) r3.getAnimatedValue()).floatValue();
            invalidate();
        }

        public abstract TextView createTextView();

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r15, View r16, long r17) {
            TextView[] r2 = this.textViews;
            boolean r5 = true;
            if (r16 != r2[0]) goto L5;
            int r9 = 0;
        L7:
            if (this.isCenter == false) goto L10;
            this.stableOffest = -1;
        L10:
            if (this.stableOffest <= 0) goto L20;
            int r4 = r2.length;
            int r7 = 0;
        L12:
            if (r7 >= r4) goto L20;
            TextView r10 = r2[r7];
            if ((r10 instanceof MarqueeTextView) == false) goto L18;
            if (((MarqueeTextView) r10).isNeedMarquee() == false) goto L18;
            this.stableOffest = -1;
        L18:
            r7 = r7 + 1;
        L20:
            if (this.stableOffest > 0) goto L22;
        L37:
            boolean r12 = false;
        L39:
            if (this.clipProgress[r9] > 0.0f) goto L44;
            if (r12 == true) goto L44;
            return super.drawChild(r15, r16, r17);
        L44:
            int r22 = Math.min(r16.getWidth(), getWidth());
            float r42 = r22;
            float r52 = Math.min(r16.getHeight(), getHeight());
            int r72 = r15.saveLayer(0.0f, 0.0f, r42, r52, null, 31);
            boolean r8 = super.drawChild(r15, r16, r17);
            float r23 = r42 * (1.0f - this.clipProgress[r9]);
            float r43 = r23 + this.gradientSize;
            this.gradientMatrix.setTranslate(r23, 0.0f);
            this.gradientShader.setLocalMatrix(this.gradientMatrix);
            r15.drawRect(r23, 0.0f, r43, r52, this.gradientPaint);
            if (r42 <= r43) goto L47;
            r15.drawRect(r43, 0.0f, r42, r52, this.erasePaint);
        L47:
            if (r12 == false) goto L49;
            r15.drawRect(this.rectF, this.erasePaint);
        L49:
            r15.restoreToCount(r72);
            return r8;
        L22:
            if (this.textViews[this.activeIndex].getAlpha() == 1.0f) goto L37;
            if (this.textViews[this.activeIndex].getLayout() == null) goto L37;
            float r24 = this.textViews[this.activeIndex].getLayout().getPrimaryHorizontal(0);
            float r44 = this.textViews[this.activeIndex].getLayout().getPrimaryHorizontal(this.stableOffest);
            if (r24 != r44) goto L28;
            r5 = false;
        L32:
            if (r5 == true) goto L34;
        L36:
            r12 = r5;
            goto L39
        L34:
            if (r9 != this.activeIndex) goto L36;
            r15.save();
            r15.clipRect(this.rectF);
            this.textViews[0].draw(r15);
            r15.restore();
            goto L36
        L28:
            RectF r73 = this.rectF;
            if (r44 <= r24) goto L31;
            r73.set(r24, 0.0f, r44, getMeasuredHeight());
            goto L32
        L31:
            r73.set(r44, 0.0f, r24, getMeasuredHeight());
            goto L32
        L5:
            r9 = 1;
            goto L7
        }

        public int getCustomPaddingRight() {
            return this.rightPadding;
        }

        public TextView getNextTextView() {
            TextView[] r0 = this.textViews;
            if (this.activeIndex != 0) goto L5;
            char r1 = 1;
        L7:
            return r0[r1];
        L5:
            r1 = 0;
            goto L7
        }

        public TextView getTextView() {
            return this.textViews[this.activeIndex];
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            super.onLayout(r2, r3, r4, r5, r6);
            if (this.isCenter == false) goto L14;
            int r32 = 0;
        L5:
            TextView[] r42 = this.textViews;
            if (r32 >= r42.length) goto L19;
            TextView r43 = r42[r32];
            if (r43 == null) goto L13;
            if (r43.getMeasuredWidth() >= getMeasuredWidth()) goto L13;
            int r52 = (getMeasuredWidth() - r43.getMeasuredWidth()) / 2;
            r43.layout(r52, 0, r43.getMeasuredWidth() + r52, r43.getMeasuredHeight());
        L13:
            r32 = r32 + 1;
            goto L5
        L19:
            return;
        }

        @Override // android.view.View
        public void onSizeChanged(int r9, int r10, int r11, int r12) {
            super.onSizeChanged(r9, r10, r11, r12);
            LinearGradient r0 = new LinearGradient(this.gradientSize, 0.0f, 0.0f, 0.0f, 0, -16777216, Shader.TileMode.CLAMP);
            this.gradientShader = r0;
            this.gradientPaint.setShader(r0);
        }

        public void setCustomPaddingRight(int r6) {
            this.rightPadding = r6;
            TextView[] r0 = this.textViews;
            int r1 = r0.length;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L8;
            TextView r3 = r0[r2];
            if ((r3 instanceof MarqueeTextView) == false) goto L7;
            ((MarqueeTextView) r3).setCustomPaddingRight(r6);
        L7:
            r2 = r2 + 1;
            goto L3
        L8:
            invalidate();
        }

        public void setIsCenter() {
            this.isCenter = true;
        }

        public void setText(CharSequence r2) {
            setText(r2, true);
        }

        public void setText(CharSequence r14, boolean r15) {
            CharSequence r0 = this.textViews[this.activeIndex].getText();
            if (TextUtils.isEmpty(r0) == true) goto L28;
            if (r15 == false) goto L28;
            if (TextUtils.equals(r14, r0) == false) goto L9;
            return;
        L9:
            this.stableOffest = 0;
            int r1 = Math.min(r14.length(), r0.length());
            int r2 = 0;
        L11:
            if (r2 >= r1) goto L17;
            if (r14.charAt(r2) != r0.charAt(r2)) goto L17;
            this.stableOffest++;
            r2 = r2 + 1;
        L17:
            if (this.stableOffest > 3) goto L19;
            this.stableOffest = -1;
        L19:
            int r02 = this.activeIndex;
            if (r02 != 0) goto L22;
            int r22 = 1;
        L23:
            this.activeIndex = r22;
            AnimatorSet r4 = this.animatorSet;
            if (r4 == null) goto L26;
            r4.cancel();
        L26:
            AnimatorSet r42 = new AnimatorSet();
            this.animatorSet = r42;
            r42.addListener(new AnonymousClass1(this, r02));
            this.textViews[r22].setText(r14);
            this.textViews[r22].bringToFront();
            this.textViews[r22].setVisibility(0);
            ValueAnimator r142 = ValueAnimator.ofFloat(new float[]{this.clipProgress[r02], 0.75f});
            r142.setDuration(200);
            r142.addUpdateListener(new AudioPlayerAlert$ClippingTextViewSwitcher$$ExternalSyntheticLambda0(this, r02));
            ValueAnimator r8 = ValueAnimator.ofFloat(new float[]{this.clipProgress[r22], 0.0f});
            r8.setStartDelay(100);
            r8.setDuration(200);
            r8.addUpdateListener(new AudioPlayerAlert$ClippingTextViewSwitcher$$ExternalSyntheticLambda1(this, r22));
            Property r6 = View.ALPHA;
            ObjectAnimator r03 = ObjectAnimator.ofFloat(this.textViews[r02], r6, new float[]{0.0f});
            r03.setStartDelay(75);
            r03.setDuration(150);
            ObjectAnimator r23 = ObjectAnimator.ofFloat(this.textViews[r22], r6, new float[]{1.0f});
            r23.setStartDelay(75);
            r23.setDuration(150);
            this.animatorSet.playTogether(new Animator[]{r142, r8, r03, r23});
            this.animatorSet.start();
            return;
        L22:
            r22 = 0;
        L28:
            this.textViews[this.activeIndex].setText(r14);
        }
    }

    public static abstract class CoverContainer extends FrameLayout {
        private int activeIndex;
        private AnimatorSet animatorSet;
        private final BackupImageView[] imageViews;

        /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$CoverContainer$1, reason: invalid class name */
        public class AnonymousClass1 extends ViewOutlineProvider {
            final /* synthetic */ CoverContainer this$0;

            public AnonymousClass1(CoverContainer r1) {
                this.this$0 = r1;
            }

            @Override // android.view.ViewOutlineProvider
            public void getOutline(View r7, Outline r8) {
                r8.setRoundRect(0, 0, r7.getWidth(), r7.getHeight(), AndroidUtilities.dp(10.0f));
            }
        }

        /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$CoverContainer$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ CoverContainer this$0;
            final /* synthetic */ BackupImageView val$prevImageView;

            public AnonymousClass2(CoverContainer r1, BackupImageView r2) {
                this.this$0 = r1;
                this.val$prevImageView = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                this.val$prevImageView.setVisibility(8);
                this.val$prevImageView.setImageDrawable(null);
                this.val$prevImageView.setAlpha(1.0f);
            }
        }

        public static /* synthetic */ void $r8$lambda$KUh1H3SP_GHVwhtYm2kMJv3tCrA(BackupImageView r2, BackupImageView r3, ValueAnimator r4) {
            float r0 = ((Float) r4.getAnimatedValue()).floatValue();
            r2.setScaleX(r0);
            r2.setScaleY(r0);
            float r42 = r4.getAnimatedFraction();
            if (r42 > 0.25f) goto L5;
            return;
        L5:
            if (r3.getImageReceiver().hasBitmapImage() == true) goto L9;
            r2.setAlpha(1.0f - ((r42 - 0.25f) * 1.3333334f));
            return;
        }

        public static /* synthetic */ void $r8$lambda$RS4PiwA2welbGSsqpkDqZvq4_2w(CoverContainer r0, int r1, ImageReceiver r2, boolean r3, boolean r4, boolean r5) {
            r0.lambda$new$0(r1, r2, r3, r4, r5);
        }

        /* renamed from: $r8$lambda$fkF2vGavqOZgdsKRRz84wV-aCZk, reason: not valid java name */
        public static /* synthetic */ void m8922$r8$lambda$fkF2vGavqOZgdsKRRz84wVaCZk(BackupImageView r1, boolean r2, ValueAnimator r3) {
            float r0 = ((Float) r3.getAnimatedValue()).floatValue();
            r1.setScaleX(r0);
            r1.setScaleY(r0);
            if (r2 == true) goto L6;
            r1.setAlpha(r3.getAnimatedFraction());
            return;
        }

        public CoverContainer(Context r6) {
            super(r6);
            this.imageViews = new BackupImageView[2];
            int r1 = 0;
        L3:
            if (r1 >= 2) goto L8;
            this.imageViews[r1] = new BackupImageView(r6);
            this.imageViews[r1].setClipToOutline(true);
            this.imageViews[r1].setOutlineProvider(new AnonymousClass1(this));
            this.imageViews[r1].getImageReceiver().setDelegate(new AudioPlayerAlert$CoverContainer$$ExternalSyntheticLambda0(this, r1));
            this.imageViews[r1].setRoundRadius(AndroidUtilities.dp(10.0f));
            if (r1 != 1) goto L7;
            this.imageViews[r1].setVisibility(8);
        L7:
            addView(this.imageViews[r1], LayoutHelper.createFrame(-1, -1.0f));
            r1 = r1 + 1;
            goto L3
        }

        private /* synthetic */ void lambda$new$0(int r1, ImageReceiver r2, boolean r3, boolean r4, boolean r5) {
            if (r1 != this.activeIndex) goto L6;
            onImageUpdated(r2);
            return;
        }

        public final ImageReceiver getImageReceiver() {
            return getImageView().getImageReceiver();
        }

        public final BackupImageView getImageView() {
            return this.imageViews[this.activeIndex];
        }

        public final BackupImageView getNextImageView() {
            BackupImageView[] r0 = this.imageViews;
            if (this.activeIndex != 0) goto L5;
            char r1 = 1;
        L7:
            return r0[r1];
        L5:
            r1 = 0;
            goto L7
        }

        public abstract void onImageUpdated(ImageReceiver r1);

        public final void switchImageViews() {
            AnimatorSet r0 = this.animatorSet;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            this.animatorSet = new AnimatorSet();
            if (this.activeIndex != 0) goto L8;
            int r02 = 1;
        L9:
            this.activeIndex = r02;
            BackupImageView[] r3 = this.imageViews;
            BackupImageView r4 = r3[r02 ^ 1];
            BackupImageView r03 = r3[r02];
            boolean r32 = r4.getImageReceiver().hasBitmapImage();
            if (r32 == false) goto L12;
            float r5 = 1.0f;
        L13:
            r03.setAlpha(r5);
            r03.setScaleX(0.8f);
            r03.setScaleY(0.8f);
            r03.setVisibility(0);
            if (r32 == false) goto L16;
            r4.bringToFront();
        L17:
            ValueAnimator r7 = ValueAnimator.ofFloat(new float[]{0.8f, 1.0f});
            r7.setDuration(125);
            r7.setInterpolator(CubicBezierInterpolator.EASE_OUT);
            r7.addUpdateListener(new AudioPlayerAlert$CoverContainer$$ExternalSyntheticLambda1(r03, r32));
            if (r32 == false) goto L20;
            ValueAnimator r33 = ValueAnimator.ofFloat(new float[]{r4.getScaleX(), 0.8f});
            r33.setDuration(125);
            r33.setInterpolator(CubicBezierInterpolator.EASE_IN);
            r33.addUpdateListener(new AudioPlayerAlert$CoverContainer$$ExternalSyntheticLambda2(r4, r03));
            r33.addListener(new AnonymousClass2(this, r4));
            this.animatorSet.playSequentially(new Animator[]{r33, r7});
        L21:
            this.animatorSet.start();
            return;
        L20:
            this.animatorSet.play(r7);
            goto L21
        L16:
            r4.setVisibility(8);
            r4.setImageDrawable(null);
            goto L17
        L12:
            r5 = 0.0f;
            goto L13
        L8:
            r02 = 0;
            goto L9
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private final Context context;
        private boolean listViewIsVisible;
        private String searchQuery;
        private ArrayList<MessageObject> searchResult;
        private Runnable searchRunnable;
        final /* synthetic */ AudioPlayerAlert this$0;

        /* renamed from: org.telegram.ui.Components.AudioPlayerAlert$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass1(ListAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(300.0f), TLObject.FLAG_30));
            }
        }

        /* renamed from: $r8$lambda$8iIJsfr6oxSiLGX5QX3n-YwIkaA, reason: not valid java name */
        public static /* synthetic */ void m8923$r8$lambda$8iIJsfr6oxSiLGX5QX3nYwIkaA(ListAdapter r0, String r1) {
            r0.lambda$search$5(r1);
        }

        public static /* synthetic */ void $r8$lambda$BUmdHrrcdaAOX5z7AInOmI4VqJQ(ListAdapter r0) {
            r0.lambda$notifyDataSetChanged$2();
        }

        /* renamed from: $r8$lambda$KHmH0AcE3o-zKXd9ZmOLmMk5gjA, reason: not valid java name */
        public static /* synthetic */ void m8924$r8$lambda$KHmH0AcE3ozKXd9ZmOLmMk5gjA(ListAdapter r0, ValueAnimator r1) {
            r0.lambda$notifyDataSetChanged$1(r1);
        }

        public static /* synthetic */ boolean $r8$lambda$VBc2oUX0wg5M_77EV9_EWFP2Kes(ListAdapter r0, AudioPlayerCell r1, View r2, MotionEvent r3) {
            return r0.lambda$onBindViewHolder$3(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$dMi8f9MSOEmYv0zCpetRc6q4Dm4(ListAdapter r0, String r1) {
            r0.lambda$processSearch$7(r1);
        }

        /* renamed from: $r8$lambda$eL9Fz-hKTBxOrDov6DHOL1S_RK4, reason: not valid java name */
        public static /* synthetic */ void m8925$r8$lambda$eL9FzhKTBxOrDov6DHOL1S_RK4(ListAdapter r0, ArrayList r1, String r2) {
            r0.lambda$updateSearchResults$8(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$l4f3mTvRwRopYK3ewwjxIOLVLbM(ListAdapter r0, String r1, ArrayList r2) {
            r0.lambda$processSearch$6(r1, r2);
        }

        /* renamed from: $r8$lambda$mk-oTzQ0tK9-c1SZV-kknqfe1HI, reason: not valid java name */
        public static /* synthetic */ void m8926$r8$lambda$mkoTzQ0tK9c1SZVkknqfe1HI(ListAdapter r0, AudioPlayerCell r1, MessageObject r2, View r3) {
            r0.lambda$onBindViewHolder$4(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$ydS_3fxXJmhtavxKJTn9-hLm_mA, reason: not valid java name */
        public static /* synthetic */ void m8927$r8$lambda$ydS_3fxXJmhtavxKJTn9hLm_mA(ListAdapter r0, ValueAnimator r1) {
            r0.lambda$notifyDataSetChanged$0(r1);
        }

        public ListAdapter(AudioPlayerAlert r1, Context r2) {
            this.this$0 = r1;
            this.searchResult = new ArrayList();
            this.context = r2;
        }

        private /* synthetic */ void lambda$notifyDataSetChanged$0(ValueAnimator r1) {
            AudioPlayerAlert.access$3400(this.this$0).invalidate();
        }

        private /* synthetic */ void lambda$notifyDataSetChanged$1(ValueAnimator r1) {
            AudioPlayerAlert.access$3300(this.this$0).invalidate();
        }

        private /* synthetic */ void lambda$notifyDataSetChanged$2() {
            AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).setVisibility(8);
        }

        private /* synthetic */ boolean lambda$onBindViewHolder$3(AudioPlayerCell r1, View r2, MotionEvent r3) {
            if (r3.getAction() != 0) goto L7;
            AudioPlayerAlert.m8876$$Nest$fgetitemTouchHelper(this.this$0).startDrag(AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).getChildViewHolder(r1));
            return false;
        L7:
            return false;
        }

        private /* synthetic */ void lambda$onBindViewHolder$4(AudioPlayerCell r1, MessageObject r2, View r3) {
            AudioPlayerAlert.m8912$$Nest$mshowOptions(this.this$0, r1, r2);
        }

        private /* synthetic */ void lambda$processSearch$6(String r13, ArrayList r14) {
            String r0 = r13.trim().toLowerCase();
            if (r0.length() != 0) goto L6;
            updateSearchResults(new ArrayList(), r13);
            return;
        L6:
            String r1 = LocaleController.getInstance().getTranslitString(r0);
            if (r0.equals(r1) == false) goto L9;
        L10:
            r1 = null;
        L12:
            if (r1 == null) goto L14;
            int r4 = 1;
        L15:
            int r42 = r4 + 1;
            String[] r5 = new String[r42];
            r5[0] = r0;
            if (r1 == null) goto L18;
            r5[1] = r1;
        L18:
            ArrayList<MessageObject> r02 = new ArrayList();
            int r12 = 0;
        L20:
            if (r12 >= r14.size()) goto L54;
            MessageObject r3 = (MessageObject) r14.get(r12);
            int r6 = 0;
        L22:
            if (r6 >= r42) goto L53;
            String r7 = r5[r6];
            String r8 = r3.getDocumentName();
            if (r8 == null) goto L52;
            if (r8.length() == 0) goto L52;
            if (r8.toLowerCase().contains(r7) == true) goto L30;
            int r82 = r3.type;
            TLRPC.Message r9 = r3.messageOwner;
            if (r82 != 0) goto L34;
            TLRPC.Document r83 = r9.media.webpage.document;
        L35:
            int r92 = 0;
        L37:
            if (r92 >= r83.attributes.size()) goto L49;
            TLRPC.DocumentAttribute r10 = r83.attributes.get(r92);
            if ((r10 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L40;
            r92 = r92 + 1;
            goto L37
        L40:
            String r84 = r10.performer;
            if (r84 == null) goto L43;
            boolean r85 = r84.toLowerCase().contains(r7);
        L44:
            if (r85 == true) goto L50;
            String r93 = r10.title;
            if (r93 == null) goto L50;
            r85 = r93.toLowerCase().contains(r7);
        L50:
            if (r85 == false) goto L52;
            r02.add(r3);
            goto L53
        L43:
            r85 = false;
            goto L44
        L49:
            r85 = false;
            goto L50
        L34:
            r83 = r9.media.document;
            goto L35
        L30:
            r02.add(r3);
        L52:
            r6 = r6 + 1;
        L53:
            r12 = r12 + 1;
            goto L20
        L54:
            updateSearchResults(r02, r13);
            return;
        L14:
            r4 = 0;
            goto L15
        L9:
            if (r1.length() != 0) goto L12;
            goto L10
        }

        private /* synthetic */ void lambda$processSearch$7(String r4) {
            ArrayList r0 = new ArrayList(AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0));
            Utilities.searchQueue.postRunnable(new AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda7(this, r4, r0));
        }

        private /* synthetic */ void lambda$search$5(String r2) {
            this.searchRunnable = null;
            processSearch(r2);
        }

        private /* synthetic */ void lambda$updateSearchResults$8(ArrayList r3, String r4) {
            if (AudioPlayerAlert.m8898$$Nest$fgetsearching(this.this$0) == true) goto L5;
            return;
        L5:
            AudioPlayerAlert.m8906$$Nest$fputsearchWas(this.this$0, true);
            this.searchResult = r3;
            this.searchQuery = r4;
            notifyDataSetChanged();
            AudioPlayerAlert.m8879$$Nest$fgetlayoutManager(this.this$0).scrollToPosition(0);
            AudioPlayerAlert.m8873$$Nest$fgetemptySubtitleTextView(this.this$0).setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.NoAudioFoundPlayerInfo, new Object[]{r4})));
        }

        private void processSearch(String r2) {
            AndroidUtilities.runOnUIThread(new AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda6(this, r2));
        }

        private void updateSearchResults(ArrayList<MessageObject> r2, String r3) {
            AndroidUtilities.runOnUIThread(new AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda8(this, r2, r3));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            boolean r0 = AudioPlayerAlert.m8897$$Nest$fgetsearchWas(this.this$0);
            AudioPlayerAlert r1 = this.this$0;
            if (r0 == false) goto L8;
            boolean r02 = AudioPlayerAlert.m8885$$Nest$fgetpadWithItem(r1);
            int r2 = this.searchResult.size();
            int r03 = r02;
        L6:
            return r03 + r2;
        L8:
            if (AudioPlayerAlert.m8889$$Nest$fgetplaylist(r1).size() <= 1) goto L10;
            boolean r04 = AudioPlayerAlert.m8885$$Nest$fgetpadWithItem(this.this$0);
            r2 = AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0).size();
            r03 = r04;
            goto L6
        L10:
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (AudioPlayerAlert.m8885$$Nest$fgetpadWithItem(this.this$0) == false) goto L7;
            if (r1 != 0) goto L9;
            return 1;
        L9:
            return 0;
        L7:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (AudioPlayerAlert.m8885$$Nest$fgetpadWithItem(this.this$0) == true) goto L5;
            return true;
        L5:
            if (r1.getAdapterPosition() != 0) goto L10;
            return false;
        L10:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            if (AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0).size() <= 1) goto L5;
            boolean r0 = true;
        L7:
            if (r0 != this.listViewIsVisible) goto L9;
        L16:
            int r02 = AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0).size();
            AudioPlayerAlert r3 = this.this$0;
            if (r02 <= 1) goto L19;
            AudioPlayerAlert.m8888$$Nest$fgetplayerLayout(r3).setBackgroundColor(AudioPlayerAlert.access$2800(this.this$0, Theme.key_player_background));
            AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).setPadding(0, AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).getPaddingTop(), 0, AndroidUtilities.dp(236.0f));
        L20:
            AudioPlayerAlert.m8888$$Nest$fgetplayerLayout(this.this$0).setBackgroundColor(AudioPlayerAlert.access$3000(this.this$0, Theme.key_player_background));
            AudioPlayerAlert.m8914$$Nest$mupdateEmptyView(this.this$0);
            return;
        L19:
            AudioPlayerAlert.m8888$$Nest$fgetplayerLayout(r3).setBackgroundColor(AudioPlayerAlert.access$2900(this.this$0, Theme.key_player_background));
            AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).setPadding(0, AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).getPaddingTop(), 0, 0);
            goto L20
        L9:
            if (AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0).size() <= 1) goto L11;
            boolean r03 = true;
        L12:
            this.listViewIsVisible = r03;
            AudioPlayerAlert r32 = this.this$0;
            if (r03 == false) goto L15;
            AudioPlayerAlert.m8881$$Nest$fgetlistView(r32).setVisibility(0);
            AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).setTranslationY(AndroidUtilities.displaySize.y);
            AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).animate().translationY(0.0f).setUpdateListener(new AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda0(this)).setDuration(420).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            goto L16
        L15:
            AudioPlayerAlert.m8881$$Nest$fgetlistView(r32).animate().translationY(AndroidUtilities.displaySize.y).setUpdateListener(new AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda1(this)).setDuration(420).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).withEndAction(new AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda2(this)).start();
            goto L16
        L11:
            r03 = false;
            goto L12
        L5:
            r0 = false;
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            if (AudioPlayerAlert.m8885$$Nest$fgetpadWithItem(this.this$0) == false) goto L8;
            if (r8 != 0) goto L7;
            View r6 = r7.itemView;
            return;
        L7:
            r8 = r8 - 1;
        L8:
            AudioPlayerCell r0 = (AudioPlayerCell) r7.itemView;
            boolean r1 = false;
            if (AudioPlayerAlert.m8897$$Nest$fgetsearchWas(this.this$0) == false) goto L15;
            MessageObject r72 = this.searchResult.get(r8);
            if ((r8 + 1) >= this.searchResult.size()) goto L13;
        L12:
            r1 = true;
        L13:
            boolean r4 = r1;
            MessageObject r12 = r72;
            if (r12 == null) goto L29;
            r12.setQuery(this.searchQuery);
        L29:
            AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda4 r82 = null;
            if (AudioPlayerAlert.m8908$$Nest$misMyList(this.this$0) == false) goto L32;
            AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda3 r5 = new AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda3(this, r0);
        L33:
            r0.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, AudioPlayerAlert.access$3200(this.this$0)));
            boolean r2 = AudioPlayerAlert.m8908$$Nest$misMyList(this.this$0);
            if (AudioPlayerAlert.m8908$$Nest$misMyList(this.this$0) == false) goto L36;
        L43:
            r0.setMessageObject(r12, r2, r82, r4, r5);
            return;
        L36:
            if (AudioPlayerAlert.m8883$$Nest$fgetnoforwards(this.this$0) == true) goto L43;
            if (r12.isVoice() == true) goto L43;
            if (r12.getId() <= 0) goto L43;
            r82 = new AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda4(this, r0, r12);
            goto L43
        L32:
            r5 = null;
            goto L33
        L15:
            if (AudioPlayerAlert.m8891$$Nest$fgetsavedMusicList(this.this$0) == null) goto L20;
            if (SharedConfig.playOrderReversed == false) goto L21;
        L24:
            r72 = (MessageObject) AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0).get((AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0).size() - r8) - 1);
            if (((AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0).size() - r8) - 2) < 0) goto L13;
        L21:
            r72 = (MessageObject) AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0).get(r8);
            if ((r8 + 1) >= AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0).size()) goto L13;
        L20:
            if (SharedConfig.playOrderReversed == false) goto L24;
            goto L21
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 1) goto L6;
            AnonymousClass1 r22 = new AnonymousClass1(this, this.context);
            r22.setTag(-33024);
            return new RecyclerListView.Holder(r22);
        L6:
            Context r32 = this.context;
            boolean r0 = MediaController.getInstance().currentPlaylistIsGlobalSearch();
            Theme.ResourcesProvider r1 = AudioPlayerAlert.access$3100(this.this$0);
            return new RecyclerListView.Holder(new AudioPlayerCell(r32, r0 ? 1 : 0, r1));
        }

        public void search(String r4) {
            if (this.searchRunnable == null) goto L5;
            Utilities.searchQueue.cancelRunnable(this.searchRunnable);
            this.searchRunnable = null;
        L5:
            if (r4 != null) goto L8;
            this.searchQuery = null;
            this.searchResult.clear();
            notifyDataSetChanged();
            return;
        L8:
            DispatchQueue r0 = Utilities.searchQueue;
            AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda5 r1 = new AudioPlayerAlert$ListAdapter$$ExternalSyntheticLambda5(this, r4);
            this.searchRunnable = r1;
            r0.postRunnable(r1, 300);
        }

        public void setup() {
            boolean r2 = true;
            if (AudioPlayerAlert.m8889$$Nest$fgetplaylist(this.this$0).size() > 1) goto L6;
            r2 = false;
        L6:
            this.listViewIsVisible = r2;
            AudioPlayerAlert r0 = this.this$0;
            if (r2 == false) goto L10;
            AudioPlayerAlert.m8881$$Nest$fgetlistView(r0).setVisibility(0);
            AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).setTranslationY(0.0f);
            return;
        L10:
            AudioPlayerAlert.m8881$$Nest$fgetlistView(r0).setVisibility(8);
            AudioPlayerAlert.m8881$$Nest$fgetlistView(this.this$0).setTranslationY(AndroidUtilities.displaySize.y);
        }
    }

    /* renamed from: $r8$lambda$-DrXoS5R39WQJIas_N-YABEDebA, reason: not valid java name */
    public static /* synthetic */ void m8844$r8$lambda$DrXoS5R39WQJIas_NYABEDebA(AudioPlayerAlert r0, int r1, boolean r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$saveToProfile$29(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$-dtf8xPBXgWM94ISw34e0klirIs, reason: not valid java name */
    public static /* synthetic */ boolean m8845$r8$lambda$dtf8xPBXgWM94ISw34e0klirIs(AudioPlayerAlert r0, ItemOptions r1, View r2) {
        return r0.lambda$showMenuOptions$38(r1, r2);
    }

    /* renamed from: $r8$lambda$01ihfy-KateZ7gOjmiqJ8twbqo0, reason: not valid java name */
    public static /* synthetic */ boolean m8846$r8$lambda$01ihfyKateZ7gOjmiqJ8twbqo0(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$0EbavDZYImRq4FOrb4idFvAWa1U(AudioPlayerAlert r0, TLRPC.TL_error r1) {
        r0.lambda$saveToProfile$28(r1);
    }

    public static /* synthetic */ void $r8$lambda$0RO2r4JzqM4Zw5PFrp8bOyskIbU(AudioPlayerAlert r0, ItemOptions r1) {
        r0.lambda$buildSaveOptions$43(r1);
    }

    public static /* synthetic */ void $r8$lambda$0Y88Yn_cCydp6AJ6OOZjMs_36vA(AudioPlayerAlert r0, MessageObject r1, ItemOptions r2) {
        r0.lambda$buildSaveOptions$44(r1, r2);
    }

    /* renamed from: $r8$lambda$0ZpHYduRYdj83wH-j0r6s4hndlU, reason: not valid java name */
    public static /* synthetic */ void m8847$r8$lambda$0ZpHYduRYdj83wHj0r6s4hndlU(AudioPlayerAlert r0, ItemOptions r1) {
        r0.lambda$showMenuOptions$39(r1);
    }

    /* renamed from: $r8$lambda$0l8f3_fcoRY5C4HrGiIStTIEK-I, reason: not valid java name */
    public static /* synthetic */ void m8848$r8$lambda$0l8f3_fcoRY5C4HrGiIStTIEKI(AudioPlayerAlert r0, ItemOptions r1, MessageObject r2) {
        r0.lambda$showOptions$49(r1, r2);
    }

    /* renamed from: $r8$lambda$1JYWKsOHKizxe5kGibp9Bt_Zi-E, reason: not valid java name */
    public static /* synthetic */ void m8849$r8$lambda$1JYWKsOHKizxe5kGibp9Bt_ZiE(AudioPlayerAlert r0, MessageObject r1, ItemOptions r2) {
        r0.lambda$buildSaveOptions$45(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$2Ctg8vn2PkVdEo7nCHBNYNzOrPQ(ItemOptions r0, ItemOptions r1) {
        r0.openSwipeback(r1);
    }

    public static /* synthetic */ void $r8$lambda$2dr626vJdUqch377ml1QY7Gq6xc(AudioPlayerAlert r0, MessageObject r1, ItemOptions r2) {
        r0.lambda$showOptions$51(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$43UX2cJnuQTaAQ0tDwlmWbBVZOM(AudioPlayerAlert r0, Theme.ResourcesProvider r1, View r2) {
        return r0.lambda$new$4(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$4QQ6sjtvyktt2FMYJtm9DnKFxM8(AudioPlayerAlert r0, ArrayList r1, boolean r2, TLRPC.TL_document r3, MessageObject r4, DialogsActivity r5, ArrayList r6, CharSequence r7, boolean r8, boolean r9, int r10, int r11, TopicsFragment r12) {
        return r0.lambda$forward$60(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    public static /* synthetic */ void $r8$lambda$5ImiDCITyP0KPpKfQp31OR8oLro(AudioPlayerAlert r0, MessageObject r1) {
        r0.lambda$onSubItemClick$23(r1);
    }

    /* renamed from: $r8$lambda$5k-MOLMnc_J3M6H7Qwn2c-nzWL0, reason: not valid java name */
    public static /* synthetic */ void m8850$r8$lambda$5kMOLMnc_J3M6H7Qwn2cnzWL0(AudioPlayerAlert r0, int r1) {
        r0.onSubItemClick(r1);
    }

    /* renamed from: $r8$lambda$5zXk6-k2-p4OduzwvjHXwt-pZRk, reason: not valid java name */
    public static /* synthetic */ void m8851$r8$lambda$5zXk6k2p4OduzwvjHXwtpZRk(AudioPlayerAlert r0, boolean r1) {
        r0.lambda$setVisibleInProfile$57(r1);
    }

    public static /* synthetic */ void $r8$lambda$7XCpEB5rcktZIQjpnTpQv2Fa6L4(AudioPlayerAlert r0, TLRPC.TL_error r1) {
        r0.lambda$saveToProfile$33(r1);
    }

    /* renamed from: $r8$lambda$8B3Zemg-CfMPIy590F9WApfJ8Wc, reason: not valid java name */
    public static /* synthetic */ void m8852$r8$lambda$8B3ZemgCfMPIy590F9WApfJ8Wc(AudioPlayerAlert r0, Theme.ResourcesProvider r1) {
        r0.lambda$new$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$8IUKPHrdT1w0hEoDzyEMDNtG1OU(AudioPlayerAlert r0, ItemOptions r1) {
        r0.lambda$showMenuOptions$40(r1);
    }

    public static /* synthetic */ void $r8$lambda$Cqt4gFfN57UnjB1Iopi_5vgBiWo(AudioPlayerAlert r0) {
        r0.lambda$saveToProfile$30();
    }

    /* renamed from: $r8$lambda$Deqi5D-5y-dzurbPKR4g_IvzBqI, reason: not valid java name */
    public static /* synthetic */ void m8853$r8$lambda$Deqi5D5ydzurbPKR4g_IvzBqI(AudioPlayerAlert r0) {
        r0.lambda$getThemeDescriptions$25();
    }

    public static /* synthetic */ void $r8$lambda$FeNWvUcey5yCPVTr9Qapz_s2ujg(AudioPlayerAlert r0, boolean r1, MessageObject r2, boolean r3, Runnable r4, long r5, TLRPC.Document r7, TLObject r8, TLRPC.TL_error r9) {
        r0.lambda$saveToProfile$35(r1, r2, r3, r4, r5, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$GfXVko_Uc8RDJXQMRPAW2Pw6OYg(AudioPlayerAlert r0, AlertDialog r1, TLRPC.Document r2, TLRPC.InputFile r3) {
        r0.lambda$onSubItemClick$22(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$Mc8c-DpgoMg2chXY7XOHd-iV1e8, reason: not valid java name */
    public static /* synthetic */ void m8854$r8$lambda$Mc8cDpgoMg2chXY7XOHdiV1e8(AudioPlayerAlert r0, AlertDialog r1, TLObject r2) {
        r0.lambda$onSubItemClick$20(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ODgXfXU3qDWUWCeiFZWMSdQgTRg(AudioPlayerAlert r0, ItemOptions r1, View r2) {
        r0.lambda$showMenuOptions$41(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$OR7KLmsnNUdIKIWWLNvL_E1ZPas(AudioPlayerAlert r0, Theme.ResourcesProvider r1, View r2) {
        r0.lambda$new$11(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$PiBrYpizzDShF13KJ1swdJaxEUA(AudioPlayerAlert r0, ItemOptions r1, MessageObject r2, View r3) {
        return r0.lambda$showOptions$48(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$W0cVVTaNUiGwNt3PdvLUxZ2dYfc(AudioPlayerAlert r0, Theme.ResourcesProvider r1, BaseFragment r2, DialogsActivity r3, ArrayList r4, CharSequence r5, boolean r6, boolean r7, int r8, int r9, TopicsFragment r10) {
        return r0.lambda$new$9(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$WZ3uU5SxVchROuZtNmHfo6I1E8o(AudioPlayerAlert r0, Theme.ResourcesProvider r1, View r2) {
        r0.lambda$new$18(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$XTTahnJaC6sUXevw9iamPxqtjd8(View r0, int r1) {
        if ((r0 instanceof AudioPlayerCell) == false) goto L6;
        ((AudioPlayerCell) r0).didPressedButton();
        return;
    }

    public static /* synthetic */ void $r8$lambda$XilP3fHirgShhTqpnFmRilx2Dkw(View r1) {
        if (MediaController.getInstance().isDownloadingCurrentMessage() == false) goto L6;
        return;
    L6:
        if (MediaController.getInstance().isMessagePaused() == false) goto L9;
        MediaController.getInstance().playMessage(MediaController.getInstance().getPlayingMessageObject());
        return;
    L9:
        MediaController.getInstance().pauseMessage(MediaController.getInstance().getPlayingMessageObject());
    }

    /* renamed from: $r8$lambda$Z8R1kUGuAF6DL6rbaLZ-it0jRXQ, reason: not valid java name */
    public static /* synthetic */ void m8855$r8$lambda$Z8R1kUGuAF6DL6rbaLZit0jRXQ(AudioPlayerAlert r0, Theme.ResourcesProvider r1) {
        r0.lambda$new$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZEFEwf7GyGPrZzM7yW1HEdLICGE(AudioPlayerAlert r0, View r1) {
        r0.showMenuOptions(r1);
    }

    /* renamed from: $r8$lambda$ZJ-wOb54oiJf7lMvHkC1wkFtyVM, reason: not valid java name */
    public static /* synthetic */ void m8856$r8$lambda$ZJwOb54oiJf7lMvHkC1wkFtyVM(AudioPlayerAlert r0, ItemOptions r1, MessageObject r2) {
        r0.lambda$showOptions$47(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZWxE9jiBLRRVD_0k59NYRvb2dNM(AudioPlayerAlert r0, ItemOptions r1, MessageObject r2) {
        r0.lambda$showOptions$53(r1, r2);
    }

    /* renamed from: $r8$lambda$_QYBoJ7hdOkZa-zdRHAgdqL0fUA, reason: not valid java name */
    public static /* synthetic */ void m8857$r8$lambda$_QYBoJ7hdOkZazdRHAgdqL0fUA(AudioPlayerAlert r0, long r1, boolean r3, TLRPC.Document r4, Runnable r5) {
        r0.lambda$saveToProfile$34(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$cGUGjJHsR7SriHG3jrG6UgfDQG4(AudioPlayerAlert r0, Uri r1) {
        r0.lambda$saveToMusic$59(r1);
    }

    public static /* synthetic */ void $r8$lambda$eBkmAIavH3EwcPvkA9YmUIqdWR4(AudioPlayerAlert r0, MessageObject r1) {
        r0.lambda$onSubItemClick$19(r1);
    }

    /* renamed from: $r8$lambda$efqsy-mZEcb4vZAtBq3yEZc9Nqk, reason: not valid java name */
    public static /* synthetic */ void m8858$r8$lambda$efqsymZEcb4vZAtBq3yEZc9Nqk(AudioPlayerAlert r0, MessageObject r1, ItemOptions r2) {
        r0.lambda$showOptions$50(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$errK5gAdHU4xFAcFAcpIQZKpotE(AudioPlayerAlert r0, MessageObject r1) {
        r0.lambda$showOptions$56(r1);
    }

    public static /* synthetic */ void $r8$lambda$g4xRrwJPRJ4mq60JPheDE3dy0Ds(AudioPlayerAlert r0, boolean r1) {
        r0.lambda$setVisibleInProfile$58(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$gOc4aIjL6jG8mmDqx90pSR2sJjk(AudioPlayerAlert r0, Theme.ResourcesProvider r1, View r2) {
        return r0.lambda$new$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$h6OCshBeTgEqvWVuETjXz3pmito(AudioPlayerAlert r4, Theme.ResourcesProvider r5) {
        BulletinFactory.of((FrameLayout) r4.getContainerView(), r5).createSimpleBulletin(R.raw.ic_save_to_music, LocaleController.formatString(R.string.ChannelToSaveChanged, new Object[]{ChatUtils.getInstance().getName(ChatUtils.getInstance().getLikeDialog())})).show();
    }

    /* renamed from: $r8$lambda$lrZu_tB5RSb-enjZ31pqpe4GHqk, reason: not valid java name */
    public static /* synthetic */ void m8859$r8$lambda$lrZu_tB5RSbenjZ31pqpe4GHqk(AudioPlayerAlert r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$mGNzQXK7Eo576AlR2mPfTtFM3M0(AudioPlayerAlert r0, float[] r1, View r2) {
        r0.lambda$new$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$mnUxNYXyI5lPyqO3EcZyaYLcwp4(AudioPlayerAlert r0, Bitmap r1, Bitmap r2) {
        r0.lambda$showAlbumCover$24(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$nAh5LnrDCGTBa_e7rIo4ydqDcJI(AudioPlayerAlert r0, ItemOptions r1, MessageObject r2) {
        r0.lambda$showOptions$55(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$oAGKouMK39b6wHooNEgB3S4QT7I(AudioPlayerAlert r0, MessageObject r1, ItemOptions r2) {
        r0.lambda$buildSaveOptions$46(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$p3kOe0sqm9xMrIyfO9_Mdcoda7s(AudioPlayerAlert r0, int r1, boolean r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$saveToProfile$32(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$p8v6UeLyxRSVT6aMEpLWciTMr_o(AudioPlayerAlert r0, Theme.ResourcesProvider r1, View r2) {
        r0.lambda$new$16(r1, r2);
    }

    /* renamed from: $r8$lambda$qeptOe_Qp_42kFT-qb6-KyqITX4, reason: not valid java name */
    public static /* synthetic */ void m8860$r8$lambda$qeptOe_Qp_42kFTqb6KyqITX4(AudioPlayerAlert r0, TLRPC.TL_error r1) {
        r0.lambda$saveToProfile$31(r1);
    }

    public static /* synthetic */ void $r8$lambda$rzo0u1MMT6PSHswL2qU6ahpbfYo(ItemOptions r0, ItemOptions r1) {
        r0.openSwipeback(r1);
    }

    public static /* synthetic */ void $r8$lambda$s_X3FutqtktdpvCOt6Nx_jzvC1M(AudioPlayerAlert r0, ItemOptions r1) {
        r0.lambda$showMenuOptions$37(r1);
    }

    /* renamed from: $r8$lambda$ss1zE_oUbB5M-Nf6-lmgl6CkDKs, reason: not valid java name */
    public static /* synthetic */ boolean m8861$r8$lambda$ss1zE_oUbB5MNf6lmgl6CkDKs(AudioPlayerAlert r0, ItemOptions r1, MessageObject r2, View r3) {
        return r0.lambda$showOptions$54(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$tAAc88n3a_SWBD8-d0tIjzotUXM, reason: not valid java name */
    public static /* synthetic */ void m8862$r8$lambda$tAAc88n3a_SWBD8d0tIjzotUXM(AudioPlayerAlert r0, Float r1, Boolean r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$vUcG3KYT_Wl_S6Zr1EWZr2ccwxE(AudioPlayerAlert r0, View r1, int r2) {
        return r0.lambda$new$14(r1, r2);
    }

    /* renamed from: $r8$lambda$vfKOq6aE4KYooBQKOCbtfILL-q4, reason: not valid java name */
    public static /* synthetic */ void m8863$r8$lambda$vfKOq6aE4KYooBQKOCbtfILLq4(AudioPlayerAlert r0, TLRPC.TL_error r1) {
        r0.lambda$saveToProfile$26(r1);
    }

    /* renamed from: $r8$lambda$vmb8W-5hNil1BRBjRfjmEaU1_Wk, reason: not valid java name */
    public static /* synthetic */ void m8864$r8$lambda$vmb8W5hNil1BRBjRfjmEaU1_Wk(AudioPlayerAlert r0, AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$onSubItemClick$21(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$x1PFJe0l9HyRPCzxYJb-gC-I6WI, reason: not valid java name */
    public static /* synthetic */ void m8865$r8$lambda$x1PFJe0l9HyRPCzxYJbgCI6WI(AudioPlayerAlert r0, ItemOptions r1) {
        r0.lambda$showMenuOptions$42(r1);
    }

    public static /* synthetic */ void $r8$lambda$xAQHkL2AE83FAWjrHSovSheO1sY(AudioPlayerAlert r0) {
        r0.lambda$saveToProfile$27();
    }

    public static /* synthetic */ void $r8$lambda$xiUr4pjQRdeZvS3561FXaHE89so(AudioPlayerAlert r0, View r1) {
        r0.lambda$new$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$yFeKW_EqyTp5ocVQM3syr4RqGpI(AudioPlayerAlert r0, int r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$z407U7HtN0euPRxP9Re4zoojaKk(AudioPlayerAlert r0, int r1) {
        r0.lambda$new$6(r1);
    }

    /* renamed from: -$$Nest$fgetactionBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBar m8866$$Nest$fgetactionBar(AudioPlayerAlert r0) {
        return r0.actionBar;
    }

    /* renamed from: -$$Nest$fgetactionBarSlide, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m8867$$Nest$fgetactionBarSlide(AudioPlayerAlert r0) {
        return r0.actionBarSlide;
    }

    /* renamed from: -$$Nest$fgetaddItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m8868$$Nest$fgetaddItem(AudioPlayerAlert r0) {
        return r0.addItem;
    }

    /* renamed from: -$$Nest$fgetbigAlbumConver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m8869$$Nest$fgetbigAlbumConver(AudioPlayerAlert r0) {
        return r0.bigAlbumConver;
    }

    /* renamed from: -$$Nest$fgetblurredView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m8870$$Nest$fgetblurredView(AudioPlayerAlert r0) {
        return r0.blurredView;
    }

    /* renamed from: -$$Nest$fgetbuttons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View[] m8871$$Nest$fgetbuttons(AudioPlayerAlert r0) {
        return r0.buttons;
    }

    /* renamed from: -$$Nest$fgetdurationTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m8872$$Nest$fgetdurationTextView(AudioPlayerAlert r0) {
        return r0.durationTextView;
    }

    /* renamed from: -$$Nest$fgetemptySubtitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m8873$$Nest$fgetemptySubtitleTextView(AudioPlayerAlert r0) {
        return r0.emptySubtitleTextView;
    }

    /* renamed from: -$$Nest$fgetforwardSeek, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m8874$$Nest$fgetforwardSeek(AudioPlayerAlert r0) {
        return r0.forwardSeek;
    }

    /* renamed from: -$$Nest$fgetisProfilePlaylist, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8875$$Nest$fgetisProfilePlaylist(AudioPlayerAlert r0) {
        return r0.isProfilePlaylist;
    }

    /* renamed from: -$$Nest$fgetitemTouchHelper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ItemTouchHelper m8876$$Nest$fgetitemTouchHelper(AudioPlayerAlert r0) {
        return r0.itemTouchHelper;
    }

    /* renamed from: -$$Nest$fgetlastDuration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8877$$Nest$fgetlastDuration(AudioPlayerAlert r0) {
        return r0.lastDuration;
    }

    /* renamed from: -$$Nest$fgetlastTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8878$$Nest$fgetlastTime(AudioPlayerAlert r0) {
        return r0.lastTime;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m8879$$Nest$fgetlayoutManager(AudioPlayerAlert r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m8880$$Nest$fgetlistAdapter(AudioPlayerAlert r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m8881$$Nest$fgetlistView(AudioPlayerAlert r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetnextButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieImageView m8882$$Nest$fgetnextButton(AudioPlayerAlert r0) {
        return r0.nextButton;
    }

    /* renamed from: -$$Nest$fgetnoforwards, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8883$$Nest$fgetnoforwards(AudioPlayerAlert r0) {
        return r0.noforwards;
    }

    /* renamed from: -$$Nest$fgetoptionsIcon, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChooseQualityLayout$QualityIcon m8884$$Nest$fgetoptionsIcon(AudioPlayerAlert r0) {
        return r0.optionsIcon;
    }

    /* renamed from: -$$Nest$fgetpadWithItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8885$$Nest$fgetpadWithItem(AudioPlayerAlert r0) {
        return r0.padWithItem;
    }

    /* renamed from: -$$Nest$fgetparentActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LaunchActivity m8886$$Nest$fgetparentActivity(AudioPlayerAlert r0) {
        return r0.parentActivity;
    }

    /* renamed from: -$$Nest$fgetplaybackSpeedButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m8887$$Nest$fgetplaybackSpeedButton(AudioPlayerAlert r0) {
        return r0.playbackSpeedButton;
    }

    /* renamed from: -$$Nest$fgetplayerLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m8888$$Nest$fgetplayerLayout(AudioPlayerAlert r0) {
        return r0.playerLayout;
    }

    /* renamed from: -$$Nest$fgetplaylist, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m8889$$Nest$fgetplaylist(AudioPlayerAlert r0) {
        return r0.playlist;
    }

    /* renamed from: -$$Nest$fgetprevButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieImageView m8890$$Nest$fgetprevButton(AudioPlayerAlert r0) {
        return r0.prevButton;
    }

    /* renamed from: -$$Nest$fgetsavedMusicList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessagesController.SavedMusicList m8891$$Nest$fgetsavedMusicList(AudioPlayerAlert r0) {
        return r0.savedMusicList;
    }

    /* renamed from: -$$Nest$fgetscrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8892$$Nest$fgetscrollOffsetY(AudioPlayerAlert r0) {
        return r0.scrollOffsetY;
    }

    /* renamed from: -$$Nest$fgetscrollToSong, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8893$$Nest$fgetscrollToSong(AudioPlayerAlert r0) {
        return r0.scrollToSong;
    }

    /* renamed from: -$$Nest$fgetsearchItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m8894$$Nest$fgetsearchItem(AudioPlayerAlert r0) {
        return r0.searchItem;
    }

    /* renamed from: -$$Nest$fgetsearchOpenOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8895$$Nest$fgetsearchOpenOffset(AudioPlayerAlert r0) {
        return r0.searchOpenOffset;
    }

    /* renamed from: -$$Nest$fgetsearchOpenPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8896$$Nest$fgetsearchOpenPosition(AudioPlayerAlert r0) {
        return r0.searchOpenPosition;
    }

    /* renamed from: -$$Nest$fgetsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8897$$Nest$fgetsearchWas(AudioPlayerAlert r0) {
        return r0.searchWas;
    }

    /* renamed from: -$$Nest$fgetsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8898$$Nest$fgetsearching(AudioPlayerAlert r0) {
        return r0.searching;
    }

    /* renamed from: -$$Nest$fgetseekBarView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SeekBarView m8899$$Nest$fgetseekBarView(AudioPlayerAlert r0) {
        return r0.seekBarView;
    }

    /* renamed from: -$$Nest$fputactionBarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8900$$Nest$fputactionBarAnimation(AudioPlayerAlert r0, AnimatorSet r1) {
        r0.actionBarAnimation = r1;
    }

    /* renamed from: -$$Nest$fputactionBarSlide, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8901$$Nest$fputactionBarSlide(AudioPlayerAlert r0, float r1) {
        r0.actionBarSlide = r1;
    }

    /* renamed from: -$$Nest$fputblurredAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8902$$Nest$fputblurredAnimationInProgress(AudioPlayerAlert r0, boolean r1) {
        r0.blurredAnimationInProgress = r1;
    }

    /* renamed from: -$$Nest$fputscrollToSong, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8903$$Nest$fputscrollToSong(AudioPlayerAlert r0, boolean r1) {
        r0.scrollToSong = r1;
    }

    /* renamed from: -$$Nest$fputsearchOpenOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8904$$Nest$fputsearchOpenOffset(AudioPlayerAlert r0, int r1) {
        r0.searchOpenOffset = r1;
    }

    /* renamed from: -$$Nest$fputsearchOpenPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8905$$Nest$fputsearchOpenPosition(AudioPlayerAlert r0, int r1) {
        r0.searchOpenPosition = r1;
    }

    /* renamed from: -$$Nest$fputsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8906$$Nest$fputsearchWas(AudioPlayerAlert r0, boolean r1) {
        r0.searchWas = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8907$$Nest$fputsearching(AudioPlayerAlert r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$misMyList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8908$$Nest$misMyList(AudioPlayerAlert r0) {
        return r0.isMyList();
    }

    /* renamed from: -$$Nest$monSubItemClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8909$$Nest$monSubItemClick(AudioPlayerAlert r0, int r1) {
        r0.onSubItemClick(r1);
    }

    /* renamed from: -$$Nest$mscrollToCurrentSong, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8910$$Nest$mscrollToCurrentSong(AudioPlayerAlert r0, boolean r1) {
        return r0.scrollToCurrentSong(r1);
    }

    /* renamed from: -$$Nest$mshowAlbumCover, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8911$$Nest$mshowAlbumCover(AudioPlayerAlert r0, boolean r1, boolean r2) {
        r0.showAlbumCover(r1, r2);
    }

    /* renamed from: -$$Nest$mshowOptions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8912$$Nest$mshowOptions(AudioPlayerAlert r0, AudioPlayerCell r1, MessageObject r2) {
        r0.showOptions(r1, r2);
    }

    /* renamed from: -$$Nest$mstartForwardRewindingSeek, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8913$$Nest$mstartForwardRewindingSeek(AudioPlayerAlert r0) {
        r0.startForwardRewindingSeek();
    }

    /* renamed from: -$$Nest$mupdateEmptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8914$$Nest$mupdateEmptyView(AudioPlayerAlert r0) {
        r0.updateEmptyView();
    }

    /* renamed from: -$$Nest$mupdateEmptyViewPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8915$$Nest$mupdateEmptyViewPosition(AudioPlayerAlert r0) {
        r0.updateEmptyViewPosition();
    }

    /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8916$$Nest$mupdateLayout(AudioPlayerAlert r0) {
        r0.updateLayout();
    }

    /* renamed from: -$$Nest$mupdateProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8917$$Nest$mupdateProgress(AudioPlayerAlert r0, MessageObject r1) {
        r0.updateProgress(r1);
    }

    static {
        speeds = new float[]{0.5f, 1.0f, 1.2f, 1.5f, 1.7f, 2.0f};
    }

    public AudioPlayerAlert(Context r40, Theme.ResourcesProvider r41) {
        super(r40, true, r41);
        this.speedItems = new ActionBarMenuSubItem[6];
        View[] r10 = new View[5];
        this.buttons = r10;
        this.scrollToSong = true;
        this.noCover = false;
        this.searchOpenPosition = -1;
        this.scrollOffsetY = Integer.MAX_VALUE;
        this.rewindingProgress = -1.0f;
        this.forwardSeek = new AnonymousClass1(this);
        this.actionBarSlideProperty = new AnonymousClass24(this, "actionBarSlide");
        this.doNotOverlayNavigationBar = true;
        fixNavigationBar(getThemedColor(Theme.key_player_background));
        MessageObject r14 = MediaController.getInstance().getPlayingMessageObject();
        if (r14 == null) goto L5;
        this.currentAccount = r14.currentAccount;
    L6:
        this.parentActivity = (LaunchActivity) r40;
        this.TAG = DownloadController.getInstance(this.currentAccount).generateObserverTag();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingDidStart);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileLoadProgressChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.musicDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.moreMusicDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.musicIdsLoaded);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.messagePlayingSpeedChanged);
        AnonymousClass2 r2 = new AnonymousClass2(this, r40, r14);
        this.containerView = r2;
        r2.setWillNotDraw(false);
        ViewGroup r22 = this.containerView;
        int r4 = this.backgroundPaddingLeft;
        r22.setPadding(r4, 0, r4, 0);
        AnonymousClass3 r23 = new AnonymousClass3(this, r40, r41);
        this.actionBar = r23;
        r23.setForceDisableCenterTitle(true);
        r23.setBackgroundColor(0);
        r23.setBackButtonImage(R.drawable.ic_ab_back);
        int r42 = Theme.key_player_actionBarTitle;
        r23.setItemsColor(getThemedColor(r42), false);
        r23.setItemsBackgroundColor(getThemedColor(Theme.key_player_actionBarSelector), false);
        r23.setTitleColor(getThemedColor(r42));
        r23.setSubtitleColor(getThemedColor(Theme.key_player_actionBarSubtitle));
        r23.setOccupyStatusBar(true);
        ActionBarMenu r15 = r23.createMenu();
        r15.setLayoutParams(LayoutHelper.createFrame(-1, -1, 119));
        View r5 = new View(r40);
        this.actionBarBackground = r5;
        r5.setBackgroundColor(getThemedColor(Theme.key_dialogBackground));
        r23.addView(this.actionBarBackground, 0, LayoutHelper.createFrame(-1, -1, 119));
        this.actionBarBackground.setAlpha(0.0f);
        r23.setAlpha(0.0f);
        r23.setActionBarMenuOnItemClick(new AnonymousClass4(this));
        this.playerLayout = new AnonymousClass5(this, r40);
        AnonymousClass6 r24 = new AnonymousClass6(this, r40);
        this.coverContainer = r24;
        this.playerLayout.addView(r24, LayoutHelper.createFrame(95, 95.0f, 51, 20.0f, 20.0f, 0.0f, 0.0f));
        AnonymousClass7 r25 = new AnonymousClass7(this, r40, r40);
        this.titleTextView = r25;
        this.playerLayout.addView(r25, LayoutHelper.createFrame(-1, -2.0f, 51, 135.0f, 20.0f, 40.0f, 0.0f));
        AnonymousClass8 r26 = new AnonymousClass8(this, r40, r40);
        this.authorTextView = r26;
        this.playerLayout.addView(r26, LayoutHelper.createFrame(-1, -2.0f, 51, 129.0f, 42.0f, 20.0f, 0.0f));
        AnonymousClass9 r27 = new AnonymousClass9(this, r40, r41);
        this.seekBarView = r27;
        r27.setLineWidth(4);
        this.seekBarView.setDelegate(new AnonymousClass10(this));
        this.seekBarView.setReportChanges(true);
        this.playerLayout.addView(this.seekBarView, LayoutHelper.createFrame(-1, 38.0f, 51, 120.0f, 70.0f, 5.0f, 0.0f));
        this.seekBarBufferSpring = new SpringAnimation(new FloatValueHolder(0.0f)).setSpring(new SpringForce().setStiffness(750.0f).setDampingRatio(1.0f)).addUpdateListener(new AudioPlayerAlert$$ExternalSyntheticLambda4(this));
        LineProgressView r28 = new LineProgressView(r40);
        this.progressView = r28;
        r28.setVisibility(4);
        this.progressView.setBackgroundColor(getThemedColor(Theme.key_player_progressBackground));
        this.progressView.setProgressColor(getThemedColor(Theme.key_player_progress));
        this.playerLayout.addView(this.progressView, LayoutHelper.createFrame(-1, 2.0f, 51, 136.0f, 90.0f, 21.0f, 0.0f));
        SimpleTextView r29 = new SimpleTextView(r40);
        this.timeTextView = r29;
        r29.setTextSize(12);
        this.timeTextView.setText("0:00");
        SimpleTextView r210 = this.timeTextView;
        int r43 = Theme.key_player_time;
        r210.setTextColor(getThemedColor(r43));
        this.timeTextView.setImportantForAccessibility(2);
        this.playerLayout.addView(this.timeTextView, LayoutHelper.createFrame(100, -2.0f, 51, 135.0f, 98.0f, 0.0f, 0.0f));
        TextView r211 = new TextView(r40);
        this.durationTextView = r211;
        r211.setTextSize(1, 12.0f);
        this.durationTextView.setTextColor(getThemedColor(r43));
        this.durationTextView.setGravity(17);
        this.durationTextView.setImportantForAccessibility(2);
        this.playerLayout.addView(this.durationTextView, LayoutHelper.createFrame(-2, -2.0f, 53, 0.0f, 98.0f, 20.0f, 0.0f));
        ActionBarMenuItem r212 = new ActionBarMenuItem(r40, null, 0, getThemedColor(r43), false, r41);
        this.playbackSpeedButton = r212;
        r212.setLongClickEnabled(false);
        this.playbackSpeedButton.setShowSubmenuByMove(false);
        this.playbackSpeedButton.setAdditionalYOffset(-AndroidUtilities.dp(224.0f));
        this.playbackSpeedButton.setContentDescription(LocaleController.getString(R.string.AccDescrPlayerSpeed));
        this.playbackSpeedButton.setDelegate(new AudioPlayerAlert$$ExternalSyntheticLambda12(this));
        ActionBarMenuItem r213 = this.playbackSpeedButton;
        SpeedIconDrawable r44 = new SpeedIconDrawable(true);
        this.speedIcon = r44;
        r213.setIcon(r44);
        float[] r45 = {1.0f, 1.5f, 2.0f};
        ActionBarMenuSlider.SpeedSlider r52 = new ActionBarMenuSlider.SpeedSlider(getContext(), r41);
        this.speedSlider = r52;
        r52.setRoundRadiusDp(6.0f);
        this.speedSlider.setDrawShadow(true);
        this.speedSlider.setOnValueChange(new AudioPlayerAlert$$ExternalSyntheticLambda13(this));
        this.speedItems[0] = this.playbackSpeedButton.addSubItem(0, R.drawable.msg_speed_slow, LocaleController.getString(R.string.SpeedSlow));
        this.speedItems[1] = this.playbackSpeedButton.addSubItem(1, R.drawable.msg_speed_normal, LocaleController.getString(R.string.SpeedNormal));
        this.speedItems[2] = this.playbackSpeedButton.addSubItem(2, R.drawable.msg_speed_medium, LocaleController.getString(R.string.SpeedMedium));
        this.speedItems[3] = this.playbackSpeedButton.addSubItem(3, R.drawable.msg_speed_fast, LocaleController.getString(R.string.SpeedFast));
        this.speedItems[4] = this.playbackSpeedButton.addSubItem(4, R.drawable.msg_speed_veryfast, LocaleController.getString(R.string.SpeedVeryFast));
        this.speedItems[5] = this.playbackSpeedButton.addSubItem(5, R.drawable.msg_speed_superfast, LocaleController.getString(R.string.SpeedSuperFast));
        if (AndroidUtilities.density < 3.0f) goto L9;
        this.playbackSpeedButton.setPadding(0, 1, 0, 0);
    L9:
        this.playbackSpeedButton.setAdditionalXOffset(AndroidUtilities.dp(8.0f));
        this.playbackSpeedButton.setAdditionalYOffset(-AndroidUtilities.dp(400.0f));
        this.playbackSpeedButton.setShowedFromBottom(true);
        this.playerLayout.addView(this.playbackSpeedButton, LayoutHelper.createFrame(36, 36.0f, 53, 0.0f, 88.0f, 20.0f, 0.0f));
        this.playbackSpeedButton.setOnClickListener(new AudioPlayerAlert$$ExternalSyntheticLambda14(this, r45));
        this.playbackSpeedButton.setOnLongClickListener(new AudioPlayerAlert$$ExternalSyntheticLambda15(this, r41));
        updatePlaybackButton(false);
        AnonymousClass11 r0 = new AnonymousClass11(this, r40);
        this.playerLayout.addView(r0, LayoutHelper.createFrame(-1, 66.0f, 49, 10.0f, 116.0f, 10.0f, 0.0f));
        ActionBarMenuItem r214 = new ActionBarMenuItem(r40, null, 0, 0, false, r41);
        this.repeatButton = r214;
        r10[0] = r214;
        r214.setLongClickEnabled(false);
        this.repeatButton.setShowSubmenuByMove(false);
        this.repeatButton.setAdditionalYOffset(-AndroidUtilities.dp(166.0f));
        ActionBarMenuItem r215 = this.repeatButton;
        int r46 = Theme.key_listSelector;
        r215.setBackgroundDrawable(Theme.createSelectorDrawable(getThemedColor(r46), 1, AndroidUtilities.dp(18.0f)));
        if (r14 != null) goto L12;
    L14:
        this.repeatButton.setOnClickListener(new AudioPlayerAlert$$ExternalSyntheticLambda16(this));
        this.repeatSongItem = this.repeatButton.addSubItem(3, R.drawable.player_new_repeatone, LocaleController.getString(R.string.RepeatSong));
        this.repeatListItem = this.repeatButton.addSubItem(4, R.drawable.player_new_repeatall, LocaleController.getString(R.string.RepeatList));
        this.repeatButton.addColoredGap().getLayoutParams().height = AndroidUtilities.dp(4.0f);
        this.shuffleListItem = this.repeatButton.addSubItem(2, R.drawable.player_new_shuffle, LocaleController.getString(R.string.ShuffleList));
        this.repeatButton.addColoredGap().getLayoutParams().height = AndroidUtilities.dp(4.0f);
        this.reverseOrderItem = this.repeatButton.addSubItem(1, R.drawable.player_new_order, LocaleController.getString(R.string.ReverseOrder));
        this.repeatButton.setShowedFromBottom(true);
        this.repeatButton.setDelegate(new AudioPlayerAlert$$ExternalSyntheticLambda17(this));
        int r6 = Theme.key_player_button;
        int r7 = getThemedColor(r6);
        float r11 = ViewConfiguration.get(r40).getScaledTouchSlop();
        AnonymousClass12 r13 = new AnonymousClass12(this, r40, r11);
        this.prevButton = r13;
        r10[1] = r13;
        ImageView.ScaleType r12 = ImageView.ScaleType.CENTER;
        r13.setScaleType(r12);
        this.prevButton.setAnimation(R.raw.player_prev, 20, 20);
        this.prevButton.setLayerColor("Triangle 3", r7);
        this.prevButton.setLayerColor("Triangle 4", r7);
        this.prevButton.setLayerColor("Rectangle 4", r7);
        this.prevButton.setBackgroundDrawable(Theme.createSelectorDrawable(getThemedColor(r46), 1, AndroidUtilities.dp(22.0f)));
        if (r14 != null) goto L17;
    L19:
        this.prevButton.setContentDescription(LocaleController.getString(R.string.AccDescrPrevious));
        ImageView r216 = new ImageView(r40);
        this.playButton = r216;
        r10[2] = r216;
        r216.setScaleType(r12);
        ImageView r217 = this.playButton;
        PlayPauseDrawable r102 = new PlayPauseDrawable(28);
        this.playPauseDrawable = r102;
        r217.setImageDrawable(r102);
        this.playPauseDrawable.setPause(!MediaController.getInstance().isMessagePaused(), false);
        ImageView r218 = this.playButton;
        int r142 = getThemedColor(r6);
        PorterDuff.Mode r152 = PorterDuff.Mode.MULTIPLY;
        r218.setColorFilter(new PorterDuffColorFilter(r142, r152));
        this.playButton.setBackgroundDrawable(Theme.createSelectorDrawable(getThemedColor(r46), 1, AndroidUtilities.dp(24.0f)));
        r0.addView(this.playButton, LayoutHelper.createFrame(48, 48, 51));
        this.playButton.setOnClickListener(new AudioPlayerAlert$$ExternalSyntheticLambda18());
        AnonymousClass13 r219 = new AnonymousClass13(this, r40, r11);
        this.nextButton = r219;
        r10[3] = r219;
        r219.setScaleType(r12);
        this.nextButton.setAnimation(R.raw.player_prev, 20, 20);
        this.nextButton.setLayerColor("Triangle 3", r7);
        this.nextButton.setLayerColor("Triangle 4", r7);
        this.nextButton.setLayerColor("Rectangle 4", r7);
        this.nextButton.setRotation(180.0f);
        this.nextButton.setBackground(Theme.createSelectorDrawable(getThemedColor(r46), 1, AndroidUtilities.dp(22.0f)));
        if (r14 != null) goto L22;
    L24:
        this.nextButton.setContentDescription(LocaleController.getString(R.string.Next));
        ImageView r220 = new ImageView(r40);
        r10[4] = r220;
        r220.setScaleType(r12);
        r220.setImageResource(R.drawable.msg_reactions);
        r220.setColorFilter(new PorterDuffColorFilter(getThemedColor(r6), r152));
        r220.setBackground(Theme.createSelectorDrawable(getThemedColor(r46), 1, AndroidUtilities.dp(24.0f)));
        if (r14 != null) goto L27;
    L29:
        int r103 = 51;
    L30:
        r220.setOnLongClickListener(new AudioPlayerAlert$$ExternalSyntheticLambda19(this, r41));
        r220.setOnClickListener(new AudioPlayerAlert$$ExternalSyntheticLambda20(this, r41));
        ActionBarMenuItem r221 = new ActionBarMenuItem(r40, null, 0, r7, false, r41);
        this.optionsButton = r221;
        ChooseQualityLayout$QualityIcon r47 = new ChooseQualityLayout$QualityIcon(r40, R.drawable.ic_ab_other, r41);
        this.optionsIcon = r47;
        r221.setIcon(r47);
        this.optionsButton.setLongClickEnabled(false);
        this.optionsButton.setAdditionalYOffset(-AndroidUtilities.dp(197.0f));
        this.optionsButton.setBackgroundDrawable(Theme.createSelectorDrawable(getThemedColor(r46), 1, AndroidUtilities.dp(18.0f)));
        this.optionsButton.setOnClickListener(new AudioPlayerAlert$$ExternalSyntheticLambda5(this));
        this.playerLayout.addView(this.optionsButton, LayoutHelper.createFrame(48, 48.0f, 53, 0.0f, 7.0f, 0.0f, 0.0f));
        AnonymousClass14 r02 = new AnonymousClass14(this, r40);
        this.castItemButton = r02;
        this.castAvailable = true;
        r02.setRouteSelector(CastContext.getSharedInstance(r40).getMergedSelector());     // Catch: Exception -> L33
    L35:
        this.castItemButton.setVisibility(4);
        ChooseQualityLayout$QualityIcon r03 = this.optionsIcon;
        if (r03 == null) goto L38;
        boolean r153 = true;
        r03.setCasting(CastSync.isActive(), true);
    L39:
        this.optionsButton.setShowedFromBottom(r153);
        this.optionsButton.setDelegate(new AudioPlayerAlert$$ExternalSyntheticLambda6(this));
        this.optionsButton.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        LinearLayout r04 = new LinearLayout(r40);
        this.emptyView = r04;
        r04.setOrientation(1);
        this.emptyView.setGravity(17);
        this.emptyView.setVisibility(8);
        this.containerView.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        this.emptyView.setOnTouchListener(new AudioPlayerAlert$$ExternalSyntheticLambda7());
        ImageView r05 = new ImageView(r40);
        this.emptyImageView = r05;
        r05.setImageResource(R.drawable.music_empty);
        this.emptyImageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_dialogEmptyImage), PorterDuff.Mode.MULTIPLY));
        this.emptyView.addView(this.emptyImageView, LayoutHelper.createLinear(-2, -2));
        TextView r06 = new TextView(r40);
        this.emptyTitleTextView = r06;
        int r48 = Theme.key_dialogEmptyText;
        r06.setTextColor(getThemedColor(r48));
        this.emptyTitleTextView.setGravity(17);
        this.emptyTitleTextView.setText(LocaleController.getString(R.string.NoAudioFound));
        this.emptyTitleTextView.setTypeface(AndroidUtilities.bold());
        this.emptyTitleTextView.setTextSize(1, 17.0f);
        this.emptyTitleTextView.setPadding(AndroidUtilities.dp(40.0f), 0, AndroidUtilities.dp(40.0f), 0);
        this.emptyView.addView(this.emptyTitleTextView, LayoutHelper.createLinear(-2, -2, 17, 0, 11, 0, 0));
        TextView r07 = new TextView(r40);
        this.emptySubtitleTextView = r07;
        r07.setTextColor(getThemedColor(r48));
        this.emptySubtitleTextView.setGravity(17);
        this.emptySubtitleTextView.setTextSize(1, 15.0f);
        this.emptySubtitleTextView.setPadding(AndroidUtilities.dp(40.0f), 0, AndroidUtilities.dp(40.0f), 0);
        this.emptyView.addView(this.emptySubtitleTextView, LayoutHelper.createLinear(-2, -2, 17, 0, 6, 0, 0));
        AnonymousClass15 r08 = new AnonymousClass15(this, r40);
        this.listView = r08;
        r08.setClipToPadding(false);
        RecyclerListView r09 = this.listView;
        LinearLayoutManager r49 = new LinearLayoutManager(getContext(), 1, false);
        this.layoutManager = r49;
        r09.setLayoutManager(r49);
        this.listView.setHorizontalScrollBarEnabled(false);
        this.listView.setVerticalScrollBarEnabled(false);
        this.containerView.addView(this.listView, LayoutHelper.createFrame(-1, -1, r103));
        RecyclerListView r010 = this.listView;
        ListAdapter r410 = new ListAdapter(this, r40);
        this.listAdapter = r410;
        r010.setAdapter(r410);
        this.listView.setGlowColor(getThemedColor(Theme.key_dialogScrollGlow));
        this.listView.setOnItemClickListener(new AudioPlayerAlert$$ExternalSyntheticLambda8());
        this.listView.setOnItemLongClickListener(new AudioPlayerAlert$$ExternalSyntheticLambda9(this));
        this.listView.setOnScrollListener(new AnonymousClass16(this));
        this.saveToProfileButton = new ButtonWithCounterView(r40, r41).setRound();
        SpannableStringBuilder r011 = new SpannableStringBuilder();
        r011.append("+ ");
        r011.setSpan(new ColoredImageSpan(R.drawable.filled_track_add), 0, 1, 33);
        r011.append(LocaleController.getString(R.string.AudioAddToProfile));
        this.saveToProfileButton.setText(r011);
        this.saveToProfileButton.setOnClickListener(new AudioPlayerAlert$$ExternalSyntheticLambda10(this, r41));
        this.playerLayout.addView(this.saveToProfileButton, LayoutHelper.createFrame(-1, 42.0f, 87, 12.0f, 12.0f, 12.0f, 12.0f));
        ButtonWithCounterView r012 = new ButtonWithCounterView(r40, r41).setRound().setNeutral();
        this.unsaveFromProfileButton = r012;
        r012.setText(LocaleController.getString(R.string.AudioRemoveFromProfile));
        this.unsaveFromProfileButton.setOnClickListener(new AudioPlayerAlert$$ExternalSyntheticLambda11(this, r41));
        this.playerLayout.addView(this.unsaveFromProfileButton, LayoutHelper.createFrame(-1, 42.0f, 87, 12.0f, 12.0f, 12.0f, 12.0f));
        MessagesController.SavedMusicList r013 = MediaController.getInstance().currentSavedMusicList;
        this.savedMusicList = r013;
        if (r013 == null) goto L42;
        boolean r154 = true;
    L43:
        this.isProfilePlaylist = r154;
        this.actionBar.menuOccupyBack = r154;
        this.padWithItem = isMyList();
        this.playlist = MediaController.getInstance().getPlaylist();
        if (isMyList() == false) goto L46;
        ActionBarMenu r411 = r15;
        this.addItem = r411.addItem(8, R.drawable.msg_add);
    L47:
        ActionBarMenuItem r014 = r411.addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass17(this));
        this.searchItem = r014;
        r014.setContentDescription(LocaleController.getString(R.string.Search));
        EditTextBoldCursor r015 = this.searchItem.getSearchField();
        r015.setHint(LocaleController.getString(R.string.Search));
        int r412 = Theme.key_player_actionBarTitle;
        r015.setTextColor(getThemedColor(r412));
        r015.setHintTextColor(getThemedColor(Theme.key_player_time));
        r015.setCursorColor(getThemedColor(r412));
        if (r154 == false) goto L50;
        this.listView.setSections();
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, r41));
        this.actionBar.setAlpha(1.0f);
        float r413 = 0.0f;
        this.actionBarBackground.setAlpha(0.0f);
        this.actionBarSlideProperty.set(this.actionBar, Float.valueOf(0.0f));
    L51:
        this.listAdapter.setup();
        this.listAdapter.notifyDataSetChanged();
        this.actionBar.setTitle(LocaleController.getString(R.string.AttachMusic));
        MessagesController.SavedMusicList r016 = this.savedMusicList;
        if (r016 == null) goto L57;
        long r53 = r016.dialogId;
        long r72 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        ActionBar r54 = this.actionBar;
        if (r53 != r72) goto L56;
        r54.setTitle(LocaleController.getString(R.string.ProfilePlaylistTitleMine));
    L82:
        if (isMyList() == false) goto L84;
        this.saveToProfileButton.setVisibility(8);
        this.unsaveFromProfileButton.setVisibility(8);
        ItemTouchHelper r017 = new ItemTouchHelper(new AnonymousClass18(this));
        this.itemTouchHelper = r017;
        r017.attachToRecyclerView(this.listView);
    L84:
        ViewGroup r018 = this.containerView;
        FrameLayout r222 = this.playerLayout;
        int r143 = 52;
        if (isMyList() == false) goto L87;
    L92:
        int r55 = 0;
    L93:
        r018.addView(r222, LayoutHelper.createFrame(-1, r55 + 184, 83));
        FrameLayout.LayoutParams r019 = (FrameLayout.LayoutParams) this.playerLayout.getLayoutParams();
        if (isMyList() == false) goto L96;
    L101:
        r143 = 0;
    L102:
        r019.height = AndroidUtilities.dp(184 + r143);
        this.playerLayout.setLayoutParams(r019);
        this.containerView.addView(this.actionBar);
        AnonymousClass19 r020 = new AnonymousClass19(this, r40);
        this.blurredView = r020;
        r020.setAlpha(r413);
        this.blurredView.setVisibility(4);
        getContainer().addView(this.blurredView);
        BackupImageView r021 = new BackupImageView(r40);
        this.bigAlbumConver = r021;
        r021.setAspectFit(true);
        this.bigAlbumConver.setRoundRadius(AndroidUtilities.dp(24.0f));
        this.bigAlbumConver.setScaleX(0.9f);
        this.bigAlbumConver.setScaleY(0.9f);
        this.blurredView.addView(this.bigAlbumConver, LayoutHelper.createFrame(-1, -1.0f, 51, 30.0f, 30.0f, 30.0f, 30.0f));
        updateTitle(false);
        updateRepeatButton();
        updateEmptyView();
        return;
    L96:
        if (this.noforwards == true) goto L101;
        if (r14 == null) goto L101;
        if (r14.isVoice() == true) goto L101;
    L87:
        if (this.noforwards == true) goto L92;
        if (r14 == null) goto L92;
        if (r14.isVoice() == true) goto L92;
        r55 = 52;
        goto L93
    L56:
        r54.setTitle(LocaleController.formatString(R.string.ProfilePlaylistTitle, new Object[]{DialogObject.getShortName(this.savedMusicList.dialogId)}));
        goto L82
    L57:
        if (r14 == null) goto L82;
        if (MediaController.getInstance().currentPlaylistIsGlobalSearch() == true) goto L82;
        long r56 = r14.getDialogId();
        boolean r022 = DialogObject.isEncryptedDialog(r56);
        int r73 = this.currentAccount;
        if (r022 == false) goto L68;
        TLRPC.EncryptedChat r023 = MessagesController.getInstance(r73).getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r56)));
        if (r023 == null) goto L82;
        TLRPC.User r024 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r023.user_id));
        if (r024 == null) goto L82;
        this.actionBar.setTitle(ContactsController.formatName(r024.first_name, r024.last_name));
        goto L82
    L68:
        if (r56 != UserConfig.getInstance(r73).getClientUserId()) goto L73;
        long r57 = r14.getSavedDialogId();
        ActionBar r58 = this.actionBar;
        if (r57 != UserObject.ANONYMOUS) goto L72;
        r58.setTitle(LocaleController.getString(R.string.AnonymousForward));
        goto L82
    L72:
        r58.setTitle(LocaleController.getString(R.string.SavedMessages));
        goto L82
    L73:
        boolean r025 = DialogObject.isUserDialog(r56);
        int r74 = this.currentAccount;
        if (r025 == false) goto L78;
        TLRPC.User r026 = MessagesController.getInstance(r74).getUser(Long.valueOf(r56));
        if (r026 == null) goto L82;
        this.actionBar.setTitle(ContactsController.formatName(r026.first_name, r026.last_name));
        goto L82
    L78:
        TLRPC.Chat r027 = MessagesController.getInstance(r74).getChat(Long.valueOf(-r56));
        if (r027 == null) goto L82;
        this.actionBar.setTitle(r027.title);
        goto L82
    L50:
        r413 = 0.0f;
        goto L51
    L46:
        r411 = r15;
        goto L47
    L42:
        r154 = false;
        goto L43
    L38:
        r153 = true;
    L33:
        e = move-exception;
        FileLog.e(e);
        this.castAvailable = false;
        goto L35
    L27:
        if (r14.isVoice() == true) goto L29;
        r103 = 51;
        r0.addView(r220, LayoutHelper.createFrame(48, 48, 51));
        goto L30
    L22:
        if (r14.isVoice() == true) goto L24;
        r0.addView(this.nextButton, LayoutHelper.createFrame(48, 48, 51));
        goto L24
    L17:
        if (r14.isVoice() == true) goto L19;
        r0.addView(this.prevButton, LayoutHelper.createFrame(48, 48, 51));
        goto L19
    L12:
        if (r14.isVoice() == true) goto L14;
        r0.addView(this.repeatButton, LayoutHelper.createFrame(48, 48, 51));
        goto L14
    L5:
        this.currentAccount = UserConfig.selectedAccount;
        goto L6
    }

    public static /* synthetic */ boolean access$000(AudioPlayerAlert r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ int access$100(AudioPlayerAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1000(AudioPlayerAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ Drawable access$1100(AudioPlayerAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ Drawable access$1200(AudioPlayerAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ int access$1300(AudioPlayerAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1400(AudioPlayerAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1500(AudioPlayerAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1600(AudioPlayerAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1700(AudioPlayerAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1800(AudioPlayerAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1900(AudioPlayerAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ boolean access$200(AudioPlayerAlert r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ ViewGroup access$2000(AudioPlayerAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$2100(AudioPlayerAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2200(AudioPlayerAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2300(AudioPlayerAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2400(AudioPlayerAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2500(AudioPlayerAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2600(AudioPlayerAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ ViewGroup access$2700(AudioPlayerAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$2800(AudioPlayerAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2900(AudioPlayerAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ boolean access$300(AudioPlayerAlert r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ int access$3000(AudioPlayerAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$3100(AudioPlayerAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$3200(AudioPlayerAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$3300(AudioPlayerAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$3400(AudioPlayerAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$400(AudioPlayerAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ Drawable access$500(AudioPlayerAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ Drawable access$600(AudioPlayerAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ int access$700(AudioPlayerAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$800(AudioPlayerAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$900(AudioPlayerAlert r0) {
        return r0.backgroundPaddingTop;
    }

    private ItemOptions buildSaveOptions(ItemOptions r8, MessageObject r9) {
        MessagesController.SavedMusicIds r0 = MessagesController.getInstance(this.currentAccount).getSavedMusicIds();
        TLRPC.Document r1 = r9.getDocument();
        if (r1 == null) goto L5;
        long r12 = r1.id;
    L6:
        ItemOptions r3 = r8.makeSwipeback();
        r3.add(R.drawable.ic_ab_back, LocaleController.getString(R.string.Back), new RatePill$$ExternalSyntheticLambda1(r8));
        r3.addGap();
        r3.addIf(!r0.ids.contains(Long.valueOf(r12)), R.drawable.left_status_profile, LocaleController.getString(R.string.AudioSaveToMyProfile), new AudioPlayerAlert$$ExternalSyntheticLambda44(this, r9, r8));
        r3.add(R.drawable.msg_saved, LocaleController.getString(R.string.AudioSaveToSavedMessages), new AudioPlayerAlert$$ExternalSyntheticLambda45(this, r9, r8));
        r3.add(R.drawable.menu_download_round, LocaleController.getString(R.string.AudioSaveToMusicFolder), new AudioPlayerAlert$$ExternalSyntheticLambda46(this, r9, r8));
        r3.addGap();
        r3.addText(LocaleController.getString(R.string.AudioSaveToInfo), 12, AndroidUtilities.dp(200.0f));
        return r3;
    L5:
        r12 = 0;
        goto L6
    }

    private void checkIfMusicDownloaded(MessageObject r7) {
        String r0 = r7.messageOwner.attachPath;
        File r1 = null;
        if (r0 != null) goto L5;
    L10:
        if (r1 != null) goto L13;
        r1 = FileLoader.getInstance(this.currentAccount).getPathToMessage(r7.messageOwner);
    L13:
        if (SharedConfig.streamMedia == true) goto L15;
    L19:
        boolean r02 = false;
    L21:
        if (r1.exists() == true) goto L29;
        if (r02 == true) goto L29;
        String r72 = r7.getFileName();
        DownloadController.getInstance(this.currentAccount).addLoadingFileObserver(r72, this);
        Float r73 = ImageLoader.getInstance().getFileProgress(r72);
        LineProgressView r03 = this.progressView;
        if (r73 == null) goto L26;
        float r74 = r73.floatValue();
    L27:
        r03.setProgress(r74, false);
        this.progressView.setVisibility(0);
        this.seekBarView.setVisibility(4);
        this.playButton.setEnabled(false);
        return;
    L26:
        r74 = 0.0f;
    L29:
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
        this.progressView.setVisibility(4);
        this.seekBarView.setVisibility(0);
        this.playButton.setEnabled(true);
        return;
    L15:
        if (((int) r7.getDialogId()) == 0) goto L19;
        if (r7.isMusic() == false) goto L19;
        r02 = true;
        goto L21
    L5:
        if (r0.length() <= 0) goto L10;
        File r04 = new File(r7.messageOwner.attachPath);
        if (r04.exists() == false) goto L10;
        r1 = r04;
        goto L10
    }

    private void checkSpeedHint() {
        long r0 = System.currentTimeMillis();
        if ((r0 - this.lastPlaybackClick) <= 300) goto L10;
        int r2 = MessagesController.getGlobalNotificationsSettings().getInt("speedhint", 0) + 1;
        if (r2 <= 2) goto L7;
        r2 = -10;
    L7:
        MessagesController.getGlobalNotificationsSettings().edit().putInt("speedhint", r2).apply();
        if (r2 < 0) goto L10;
        showSpeedHint();
    L10:
        this.lastPlaybackClick = r0;
    }

    private boolean equals(float r1, float r2) {
        if (Math.abs(r1 - r2) >= 0.05f) goto L6;
        return true;
    L6:
        return false;
    }

    private void forward(MessageObject r2) {
        forward(r2, false);
    }

    private void forward(MessageObject r26, long r27) {
        int r2 = UserConfig.selectedAccount;
        int r3 = this.currentAccount;
        if (r2 == r3) goto L6;
        this.parentActivity.switchToAccount(r3, true);
    L6:
        if (r26.getId() < 0) goto L8;
        ArrayList<MessageObject> r22 = new ArrayList();
        r22.add(r26);
        ArrayList<MessageObject> r5 = r22;
        TLRPC.TL_document r6 = null;
    L12:
        int r23 = this.currentAccount;
        if (r5 == null) goto L15;
        SendMessagesHelper.getInstance(r23).sendMessage(r5, r27, false, false, true, 0, 0);
    L16:
        BaseFragment r1 = LaunchActivity.getLastFragment();
        if (r1 == null) goto L27;
        BulletinFactory r12 = BulletinFactory.of(r1);
        int r24 = R.raw.forward;
        if (r27 != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L22;
        String r0 = LocaleController.getString(R.string.FwdMessageToSavedMessages);
    L25:
        r12.createSimpleBulletin(r24, r0).show();
        return;
    L22:
        if (r27 <= 0) goto L24;
        r0 = LocaleController.formatString(R.string.FwdMessageToUser, new Object[]{DialogObject.getShortName(r27)});
        goto L25
    L24:
        r0 = LocaleController.formatString(R.string.FwdMessageToGroup, new Object[]{DialogObject.getShortName(r27)});
        goto L25
    L27:
        return;
    L15:
        SendMessagesHelper.getInstance(r23).sendMessage(SendMessagesHelper.SendMessageParams.of(r6, null, r26.messageOwner.attachPath, r27, null, null, null, null, null, null, true, 0, 0, 0, this.savedMusicList, null, false, false));
        goto L16
    L8:
        if ((r26.getDocument() instanceof TLRPC.TL_document) == false) goto L28;
        r6 = (TLRPC.TL_document) r26.getDocument();
        r5 = null;
        goto L12
    }

    private void forward(MessageObject r10, boolean r11) {
        int r0 = UserConfig.selectedAccount;
        int r1 = this.currentAccount;
        if (r0 == r1) goto L5;
        this.parentActivity.switchToAccount(r1, true);
    L5:
        Bundle r02 = new Bundle();
        r02.putBoolean("onlySelect", true);
        r02.putInt("dialogsType", 3);
        r02.putBoolean("canSelectTopics", true);
        r02.putBoolean("forward_noquote", r11);
        DialogsActivity r12 = new DialogsActivity(r02);
        if (r10.getId() < 0) goto L8;
        ArrayList r03 = new ArrayList();
        r03.add(r10);
        ArrayList r5 = r03;
        TLRPC.TL_document r7 = null;
    L12:
        r12.setDelegate(new AudioPlayerAlert$$ExternalSyntheticLambda42(this, r5, r11, r7, r10));
        this.parentActivity.presentFragment(r12);
        dismiss();
        return;
    L8:
        if ((r10.getDocument() instanceof TLRPC.TL_document) == true) goto L10;
        return;
    L10:
        r7 = (TLRPC.TL_document) r10.getDocument();
        r5 = null;
        goto L12
    }

    private ImageLocation getArtworkThumbImageLocation(MessageObject r4) {
        TLRPC.Document r3 = r4.getDocument();
        if (r3 == null) goto L5;
        TLRPC.PhotoSize r1 = FileLoader.getClosestPhotoSizeWithSize(r3.thumbs, 360);
    L7:
        if ((r1 instanceof TLRPC.TL_photoSize) == false) goto L9;
    L11:
        if (r1 != null) goto L13;
        String r32 = r4.getArtworkUrl(true);
        if (r32 != null) goto L17;
        return null;
    L17:
        return ImageLocation.getForPath(r32);
    L13:
        return ImageLocation.getForDocument(r1, r3);
    L9:
        if ((r1 instanceof TLRPC.TL_photoSizeProgressive) == true) goto L11;
        r1 = null;
        goto L11
    L5:
        r1 = null;
        goto L7
    }

    private boolean isMyList() {
        MessagesController.SavedMusicList r0 = this.savedMusicList;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.dialogId != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$buildSaveOptions$43(ItemOptions r3) {
        setVisibleInProfile(true);
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createSimpleBulletin(R.raw.saved_messages, LocaleController.getString(R.string.AudioSaveToMyProfileSaved)).show();
        r3.dismiss();
    }

    private /* synthetic */ void lambda$buildSaveOptions$44(MessageObject r3, ItemOptions r4) {
        saveToProfile(r3, true, new AudioPlayerAlert$$ExternalSyntheticLambda56(this, r4), false);
    }

    private /* synthetic */ void lambda$buildSaveOptions$45(MessageObject r3, ItemOptions r4) {
        forward(r3, UserConfig.getInstance(this.currentAccount).getClientUserId());
        r4.dismiss();
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createSimpleBulletin(R.raw.saved_messages, LocaleController.getString(R.string.AudioSaveToSavedMessagesSaved)).show();
    }

    private /* synthetic */ void lambda$buildSaveOptions$46(MessageObject r1, ItemOptions r2) {
        saveToMusic(r1);
        r2.dismiss();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ boolean lambda$forward$60(ArrayList r37, boolean r38, TLRPC.TL_document r39, MessageObject r40, DialogsActivity r41, ArrayList r42, CharSequence r43, boolean r44, boolean r45, int r46, int r47, TopicsFragment r48) {
        ArrayList r2 = r37;
        boolean r5 = r38;
        if (r42.size() <= 1) goto L5;
    L25:
        int r1 = 0;
    L27:
        if (r1 >= r42.size()) goto L37;
        long r3 = ((MessagesStorage.TopicKey) r42.get(r1)).dialogId;
        if (r43 == null) goto L31;
        long r19 = r3;
        SendMessagesHelper.getInstance(this.currentAccount).sendMessage(SendMessagesHelper.SendMessageParams.of(r43.toString(), r3, null, null, null, true, null, null, null, true, 0, 0, null, false));
    L32:
        int r32 = this.currentAccount;
        if (r2 == null) goto L35;
        int r35 = r1;
        SendMessagesHelper.getInstance(r32).sendMessage(r2, r19, r5, false, true, 0, 0);
    L36:
        r1 = r35 + 1;
        r2 = r37;
        r5 = r38;
        goto L27
    L35:
        r35 = r1;
        SendMessagesHelper.getInstance(r32).sendMessage(SendMessagesHelper.SendMessageParams.of(r39, null, r40.messageOwner.attachPath, r19, null, null, null, null, null, null, r45, r46, 0, 0, this.savedMusicList, null, false, false));
        goto L36
    L31:
        r19 = r3;
        goto L32
    L37:
        r41.finishFragment();
        BaseFragment r12 = LaunchActivity.getLastFragment();
        if (r12 == null) goto L56;
        BulletinFactory r13 = BulletinFactory.of(r12);
        int r22 = R.raw.forward;
        if (r42.size() != 1) goto L45;
        if (((MessagesStorage.TopicKey) r42.get(0)).dialogId != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L45;
        String r0 = LocaleController.getString(R.string.FwdMessageToSavedMessages);
    L55:
        r13.createSimpleBulletin(r22, r0).show();
    L45:
        if (r42.size() != 1) goto L50;
        if (((MessagesStorage.TopicKey) r42.get(0)).dialogId <= 0) goto L50;
        r0 = LocaleController.formatString(R.string.FwdMessageToUser, new Object[]{DialogObject.getShortName(((MessagesStorage.TopicKey) r42.get(0)).dialogId)});
    L50:
        if (r42.size() == 1) goto L52;
    L54:
        r0 = LocaleController.formatPluralStringComma("FwdMessageToManyChats", r42.size());
        goto L55
    L52:
        if (((MessagesStorage.TopicKey) r42.get(0)).dialogId >= 0) goto L54;
        r0 = LocaleController.formatString(R.string.FwdMessageToGroup, new Object[]{DialogObject.getShortName(((MessagesStorage.TopicKey) r42.get(0)).dialogId)});
    L56:
        return true;
    L5:
        if (((MessagesStorage.TopicKey) r42.get(0)).dialogId == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L25;
        if (r43 != null) goto L25;
        if (r2 == null) goto L25;
        MessagesStorage.TopicKey r14 = (MessagesStorage.TopicKey) r42.get(0);
        long r33 = r14.dialogId;
        Bundle r6 = new Bundle();
        r6.putBoolean("scrollToTopOnResume", true);
        if (DialogObject.isEncryptedDialog(r33) == false) goto L13;
        r6.putInt("enc_id", DialogObject.getEncryptedChatId(r33));
    L16:
        r6.putBoolean("forward_noquote", r5);
        ChatActivity r34 = new ChatActivity(r6);
        if (r14.topicId == 0) goto L20;
        ForumUtilities.applyTopic(r34, r14);
    L20:
        if (this.parentActivity.presentFragment(r34, true, false) == false) goto L24;
        r34.setForwardParams(r5);
        r34.showFieldPanelForForward(true, r2);
        if (r14.topicId == 0) goto L56;
        r41.removeSelfFromStack();
        goto L56
    L24:
        r41.finishFragment();
        goto L56
    L13:
        if (DialogObject.isUserDialog(r33) == false) goto L15;
        r6.putLong("user_id", r33);
        goto L16
    L15:
        r6.putLong("chat_id", -r33);
        goto L16
    }

    private /* synthetic */ void lambda$getThemeDescriptions$25() {
        this.searchItem.getSearchField().setCursorColor(getThemedColor(Theme.key_player_actionBarTitle));
        ActionBarMenuItem r0 = this.repeatButton;
        r0.setIconColor(getThemedColor(((Integer) r0.getTag()).intValue()));
        Drawable r02 = this.repeatButton.getBackground();
        int r1 = Theme.key_listSelector;
        Theme.setSelectorDrawableColor(r02, getThemedColor(r1), true);
        this.optionsButton.setIconColor(getThemedColor(Theme.key_player_button));
        Theme.setSelectorDrawableColor(this.optionsButton.getBackground(), getThemedColor(r1), true);
        this.progressView.setBackgroundColor(getThemedColor(Theme.key_player_progressBackground));
        this.progressView.setProgressColor(getThemedColor(Theme.key_player_progress));
        updateSubMenu();
        ActionBarMenuItem r03 = this.repeatButton;
        int r12 = Theme.key_actionBarDefaultSubmenuBackground;
        r03.redrawPopup(getThemedColor(r12));
        ActionBarMenuItem r04 = this.optionsButton;
        int r2 = Theme.key_actionBarDefaultSubmenuItem;
        r04.setPopupItemsColor(getThemedColor(r2), false);
        this.optionsButton.setPopupItemsColor(getThemedColor(r2), true);
        this.optionsButton.redrawPopup(getThemedColor(r12));
    }

    private /* synthetic */ void lambda$new$0(DynamicAnimation r1, float r2, float r3) {
        this.seekBarView.setBufferedProgress(r2 / 1000.0f);
    }

    private /* synthetic */ void lambda$new$1(int r3) {
        if (r3 < 0) goto L8;
        float[] r0 = speeds;
        if (r3 >= r0.length) goto L9;
        MediaController.getInstance().setPlaybackSpeed(true, r0[r3]);
        updatePlaybackButton(true);
        return;
    L9:
        return;
    }

    private /* synthetic */ boolean lambda$new$10(Theme.ResourcesProvider r5, View r6) {
        BaseFragment r62 = this.parentActivity.getActionBarLayout().getFragmentStack().get(this.parentActivity.getActionBarLayout().getFragmentStack().size() - 1);
        Bundle r0 = new Bundle();
        r0.putBoolean("onlySelect", true);
        r0.putInt("dialogsType", 0);
        r0.putBoolean("allowGlobalSearch", false);
        r0.putBoolean("canSelectTopics", true);
        DialogsActivity r2 = new DialogsActivity(r0);
        r2.setDelegate(new AudioPlayerAlert$$ExternalSyntheticLambda24(this, r5, r62));
        this.parentActivity.presentFragment(r2);
        dismiss();
        return true;
    }

    private /* synthetic */ void lambda$new$11(Theme.ResourcesProvider r13, View r14) {
        r14.performHapticFeedback(3, 2);
        ArrayList<MessageObject> r3 = new ArrayList(ImageCapture$$ExternalSyntheticBackport0.m(MediaController.getInstance().getPlayingMessageObject()));
        SendMessagesHelper.getInstance(this.currentAccount).sendMessage(r3, ChatUtils.getInstance(this.currentAccount).getLikeDialog(), true, true, false, 0, 0);
        BulletinFactory.of((FrameLayout) this.containerView, r13).createSimpleBulletin(R.raw.ic_save_to_music, LocaleController.formatString(R.string.TrackSaved, new Object[]{ChatUtils.getInstance().getName(ChatUtils.getInstance().getLikeDialog())})).show();
    }

    private /* synthetic */ boolean lambda$new$14(View r1, int r2) {
        if ((r1 instanceof AudioPlayerCell) == true) goto L5;
        return false;
    L5:
        if (isMyList() == true) goto L10;
        AudioPlayerCell r12 = (AudioPlayerCell) r1;
        showOptions(r12, r12.getMessageObject());
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$new$15(Theme.ResourcesProvider r2) {
        setVisibleInProfile(true);
        BulletinFactory.of((FrameLayout) this.containerView, r2).createSimpleBulletin(R.raw.saved_messages, LocaleController.getString(R.string.AudioSaveToMyProfileSaved)).show();
    }

    private /* synthetic */ void lambda$new$16(Theme.ResourcesProvider r3, View r4) {
        MessageObject r42 = MediaController.getInstance().getPlayingMessageObject();
        if (r42 != null) goto L5;
        return;
    L5:
        if (this.parentActivity == null) goto L10;
        saveToProfile(r42, true, new AudioPlayerAlert$$ExternalSyntheticLambda25(this, r3), false);
        return;
    }

    private /* synthetic */ void lambda$new$17(Theme.ResourcesProvider r2) {
        setVisibleInProfile(false);
        BulletinFactory.of((FrameLayout) this.containerView, r2).createSimpleBulletin(R.raw.ic_delete, LocaleController.getString(R.string.AudioSaveToMyProfileUnsaved)).show();
    }

    private /* synthetic */ void lambda$new$18(Theme.ResourcesProvider r2, View r3) {
        MessageObject r32 = MediaController.getInstance().getPlayingMessageObject();
        if (r32 != null) goto L5;
        return;
    L5:
        if (this.parentActivity == null) goto L10;
        saveToProfile(r32, false, new AudioPlayerAlert$$ExternalSyntheticLambda21(this, r2), false);
        return;
    }

    private /* synthetic */ void lambda$new$2(Float r2, Boolean r3) {
        this.slidingSpeed = !r3.booleanValue();
        MediaController.getInstance().setPlaybackSpeed(true, this.speedSlider.getSpeed(r2.floatValue()));
    }

    private /* synthetic */ void lambda$new$3(float[] r6, View r7) {
        float r72 = MediaController.getInstance().getPlaybackSpeed(true);
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r2 >= r6.length) goto L9;
        if ((r72 - 0.1f) <= r6[r2]) goto L10;
        r2 = r2 + 1;
    L10:
        int r22 = r2 + 1;
        if (r22 >= r6.length) goto L14;
        r1 = r22;
    L14:
        MediaController.getInstance().setPlaybackSpeed(true, r6[r1]);
        checkSpeedHint();
        return;
    L9:
        r2 = -1;
        goto L10
    }

    private /* synthetic */ boolean lambda$new$4(Theme.ResourcesProvider r4, View r5) {
        float r52 = MediaController.getInstance().getPlaybackSpeed(true);
        this.speedSlider.setSpeed(r52, false);
        this.speedSlider.setBackgroundColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, r4));
        updatePlaybackButton(false);
        this.playbackSpeedButton.setDimMenu(0.15f);
        this.playbackSpeedButton.toggleSubMenu(this.speedSlider, null);
        MessagesController.getGlobalNotificationsSettings().edit().putInt("speedhint", -15).apply();
        return true;
    }

    private /* synthetic */ void lambda$new$5(View r1) {
        updateSubMenu();
        this.repeatButton.toggleSubMenu();
    }

    private /* synthetic */ void lambda$new$6(int r5) {
        if (r5 == 1) goto L16;
        if (r5 == 2) goto L16;
        if (r5 != 4) goto L13;
        if (SharedConfig.repeatMode != 1) goto L11;
        SharedConfig.setRepeatMode(0);
    L27:
        updateRepeatButton();
        return;
    L11:
        SharedConfig.setRepeatMode(1);
        goto L27
    L13:
        if (SharedConfig.repeatMode != 2) goto L15;
        SharedConfig.setRepeatMode(0);
        goto L27
    L15:
        SharedConfig.setRepeatMode(2);
    L16:
        boolean r3 = SharedConfig.playOrderReversed;
        if (r3 == false) goto L20;
        if (r5 != 1) goto L20;
    L22:
        MediaController.getInstance().setPlaybackOrderType(0);
    L24:
        this.listAdapter.notifyDataSetChanged();
        if (r3 == SharedConfig.playOrderReversed) goto L27;
        this.listView.stopScroll();
        scrollToCurrentSong(false);
    L20:
        if (SharedConfig.shuffleMusic == false) goto L23;
        if (r5 == 2) goto L22;
    L23:
        MediaController.getInstance().setPlaybackOrderType(r5);
        goto L24
    }

    private /* synthetic */ boolean lambda$new$9(Theme.ResourcesProvider r1, BaseFragment r2, DialogsActivity r3, ArrayList r4, CharSequence r5, boolean r6, boolean r7, int r8, int r9, TopicsFragment r10) {
        ChatUtils.getInstance().setLikeDialog(((MessagesStorage.TopicKey) r4.get(0)).dialogId);
        AudioPlayerAlert r42 = new AudioPlayerAlert(this.parentActivity, r1);
        r2.showDialog(r42);
        AndroidUtilities.runOnUIThread(new AudioPlayerAlert$$ExternalSyntheticLambda43(r42, r1), 450);
        r3.finishFragment();
        return true;
    }

    private /* synthetic */ void lambda$onSubItemClick$19(MessageObject r2) {
        MessagesController.SavedMusicList r0 = this.savedMusicList;
        if (r0 == null) goto L10;
        r0.remove(r2);
        if (this.savedMusicList.list.isEmpty() == false) goto L8;
        MediaController.getInstance().cleanup();
        dismiss();
        return;
    L8:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.musicListLoaded, new Object[]{this.savedMusicList});
        return;
    }

    private /* synthetic */ void lambda$onSubItemClick$20(AlertDialog r4, TLObject r5) {
        r4.dismiss();
        if ((r5 instanceof TLRPC.TL_messageMediaDocument) == false) goto L9;
        TLRPC.TL_account_saveMusic r42 = new TLRPC.TL_account_saveMusic();
        TLRPC.TL_inputDocument r0 = new TLRPC.TL_inputDocument();
        r42.id = r0;
        TLRPC.Document r52 = ((TLRPC.TL_messageMediaDocument) r5).document;
        r0.id = r52.id;
        r0.access_hash = r52.access_hash;
        r0.file_reference = r52.file_reference;
        MessagesController.SavedMusicList r02 = this.savedMusicList;
        if (r02 == null) goto L7;
        r02.add(r52);
    L7:
        this.playlist.clear();
        this.playlist.addAll(this.savedMusicList.list);
        this.listAdapter.notifyDataSetChanged();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r42, null);
        return;
    }

    private /* synthetic */ void lambda$onSubItemClick$21(AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new AudioPlayerAlert$$ExternalSyntheticLambda58(this, r1, r2));
    }

    private /* synthetic */ void lambda$onSubItemClick$22(AlertDialog r5, TLRPC.Document r6, TLRPC.InputFile r7) {
        if (r7 != null) goto L5;
        r5.dismiss();
        return;
    L5:
        TLRPC.TL_messages_uploadMedia r0 = new TLRPC.TL_messages_uploadMedia();
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(UserConfig.getInstance(this.currentAccount).getClientUserId());
        TLRPC.TL_inputMediaUploadedDocument r1 = new TLRPC.TL_inputMediaUploadedDocument();
        r0.media = r1;
        r1.file = r7;
        r1.mime_type = r6.mime_type;
        r1.attributes.addAll(r6.attributes);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new AudioPlayerAlert$$ExternalSyntheticLambda57(this, r5));
    }

    private /* synthetic */ void lambda$onSubItemClick$23(MessageObject r6) {
        if (r6 != null) goto L4;
        return;
    L4:
        if (this.savedMusicList == null) goto L22;
        TLRPC.Document r0 = r6.getDocument();
        if (r0 != null) goto L10;
        return;
    L10:
        if (r0.id == 0) goto L16;
        TLRPC.TL_account_saveMusic r62 = new TLRPC.TL_account_saveMusic();
        TLRPC.TL_inputDocument r1 = new TLRPC.TL_inputDocument();
        r62.id = r1;
        r1.id = r0.id;
        r1.access_hash = r0.access_hash;
        r1.file_reference = r0.file_reference;
        MessagesController.SavedMusicList r12 = this.savedMusicList;
        if (r12 == null) goto L14;
        r12.add(r0);
    L14:
        this.playlist.clear();
        this.playlist.addAll(this.savedMusicList.list);
        this.listAdapter.notifyDataSetChanged();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r62, null);
        return;
    L16:
        AlertDialog r13 = new AlertDialog(getContext(), 3);
        r13.showDelayed(180);
        File r2 = new File(r6.messageOwner.attachPath);
        if (r2.exists() == false) goto L24;
        FileLoader.getInstance(this.currentAccount).uploadFile(r2.getAbsolutePath(), new AudioPlayerAlert$$ExternalSyntheticLambda47(this, r13, r0));
        return;
    L24:
        return;
    }

    private /* synthetic */ void lambda$saveToMusic$59(Uri r1) {
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createDownloadBulletin(BulletinFactory.FileType.AUDIO).show();
    }

    private /* synthetic */ void lambda$saveToProfile$26(TLRPC.TL_error r2) {
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).showForError(r2);
    }

    private /* synthetic */ void lambda$saveToProfile$27() {
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createErrorBulletin(LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{"CLIENT_MESSAGE_NOT_FOUND"})).show();
    }

    private /* synthetic */ void lambda$saveToProfile$28(TLRPC.TL_error r2) {
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).showForError(r2);
    }

    private /* synthetic */ void lambda$saveToProfile$29(int r3, boolean r4, Runnable r5, TLObject r6, TLRPC.TL_error r7) {
        if ((r6 instanceof TLRPC.messages_Messages) == false) goto L17;
        TLRPC.messages_Messages r62 = (TLRPC.messages_Messages) r6;
        int r0 = 0;
    L6:
        if (r0 >= r62.messages.size()) goto L11;
        if (r62.messages.get(r0).id == r3) goto L9;
        r0 = r0 + 1;
        goto L6
    L9:
        TLRPC.Message r32 = r62.messages.get(r0);
    L12:
        if (r32 == null) goto L15;
        saveToProfile(new MessageObject(this.currentAccount, r32, false, true), r4, r5, true);
        return;
    L15:
        AndroidUtilities.runOnUIThread(new AudioPlayerAlert$$ExternalSyntheticLambda59(this));
        return;
    L11:
        r32 = null;
        goto L12
    L17:
        if (r7 == null) goto L22;
        AndroidUtilities.runOnUIThread(new AudioPlayerAlert$$ExternalSyntheticLambda60(this, r7));
        return;
    }

    private /* synthetic */ void lambda$saveToProfile$30() {
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createErrorBulletin(LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{"CLIENT_MESSAGE_NOT_FOUND"})).show();
    }

    private /* synthetic */ void lambda$saveToProfile$31(TLRPC.TL_error r2) {
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).showForError(r2);
    }

    private /* synthetic */ void lambda$saveToProfile$32(int r3, boolean r4, Runnable r5, TLObject r6, TLRPC.TL_error r7) {
        if ((r6 instanceof TLRPC.messages_Messages) == false) goto L17;
        TLRPC.messages_Messages r62 = (TLRPC.messages_Messages) r6;
        int r0 = 0;
    L6:
        if (r0 >= r62.messages.size()) goto L11;
        if (r62.messages.get(r0).id == r3) goto L9;
        r0 = r0 + 1;
        goto L6
    L9:
        TLRPC.Message r32 = r62.messages.get(r0);
    L12:
        if (r32 == null) goto L15;
        saveToProfile(new MessageObject(this.currentAccount, r32, false, true), r4, r5, true);
        return;
    L15:
        AndroidUtilities.runOnUIThread(new AudioPlayerAlert$$ExternalSyntheticLambda61(this));
        return;
    L11:
        r32 = null;
        goto L12
    L17:
        if (r7 == null) goto L22;
        AndroidUtilities.runOnUIThread(new AudioPlayerAlert$$ExternalSyntheticLambda62(this, r7));
        return;
    }

    private /* synthetic */ void lambda$saveToProfile$33(TLRPC.TL_error r2) {
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).showForError(r2);
    }

    private /* synthetic */ void lambda$saveToProfile$34(long r4, boolean r6, TLRPC.Document r7, Runnable r8) {
        MessagesController.getInstance(this.currentAccount).getSavedMusicIds().update(r4, r6);
        long r0 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        TLRPC.UserFull r2 = MessagesController.getInstance(this.currentAccount).getUserFull(r0);
        if (r2 == null) goto L12;
        if (r6 == false) goto L6;
        r2.flags2 |= TLObject.FLAG_21;
        r2.saved_music = r7;
    L11:
        MessagesStorage.getInstance(this.currentAccount).updateUserInfo(r2, true);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.profileMusicUpdated, new Object[]{Long.valueOf(r0)});
        goto L12
    L6:
        TLRPC.Document r62 = r2.saved_music;
        if (r62 == null) goto L11;
        if (r62.id != r4) goto L11;
        r2.flags2 &= -2097153;
        r2.saved_music = null;
    L12:
        if (r8 == null) goto L15;
        r8.run();
        return;
    }

    private /* synthetic */ void lambda$saveToProfile$35(boolean r1, MessageObject r2, boolean r3, Runnable r4, long r5, TLRPC.Document r7, TLObject r8, TLRPC.TL_error r9) {
        if (r9 != null) goto L4;
    L17:
        if (r9 == null) goto L20;
        AndroidUtilities.runOnUIThread(new AudioPlayerAlert$$ExternalSyntheticLambda54(this, r9));
        return;
    L20:
        AndroidUtilities.runOnUIThread(new AudioPlayerAlert$$ExternalSyntheticLambda55(this, r5, r3, r7, r4));
        return;
    L4:
        if (FileRefController.isFileRefError(r9.text) == false) goto L17;
        if (r1 == false) goto L7;
    L15:
        AndroidUtilities.runOnUIThread(new AudioPlayerAlert$$ExternalSyntheticLambda51(this, r9));
        return;
    L7:
        if (r2.getId() < 0) goto L15;
        if (r2.getDialogId() < 0) goto L13;
        int r12 = r2.getId();
        TLRPC.TL_messages_getMessages r22 = new TLRPC.TL_messages_getMessages();
        r22.id.add(Integer.valueOf(r12));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r22, new AudioPlayerAlert$$ExternalSyntheticLambda52(this, r12, r3, r4));
        return;
    L13:
        int r13 = r2.getId();
        TLRPC.TL_channels_getMessages r52 = new TLRPC.TL_channels_getMessages();
        r52.channel = MessagesController.getInstance(this.currentAccount).getInputChannel(-r2.getDialogId());
        r52.id.add(Integer.valueOf(r13));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r52, new AudioPlayerAlert$$ExternalSyntheticLambda53(this, r13, r3, r4));
    }

    private /* synthetic */ void lambda$setVisibleInProfile$57(boolean r1) {
        ButtonWithCounterView r0 = this.saveToProfileButton;
        if (r1 == false) goto L5;
        int r12 = 8;
    L6:
        r0.setVisibility(r12);
        return;
    L5:
        r12 = 0;
        goto L6
    }

    private /* synthetic */ void lambda$setVisibleInProfile$58(boolean r1) {
        ButtonWithCounterView r0 = this.unsaveFromProfileButton;
        if (r1 == false) goto L5;
        int r12 = 0;
    L6:
        r0.setVisibility(r12);
        return;
    L5:
        r12 = 8;
        goto L6
    }

    private /* synthetic */ void lambda$showAlbumCover$24(Bitmap r1, Bitmap r2) {
        this.blurredView.setBackground(new BitmapDrawable(r1));
    }

    private /* synthetic */ void lambda$showMenuOptions$37(ItemOptions r1) {
        r1.dismiss();
        onSubItemClick(1);
    }

    private /* synthetic */ boolean lambda$showMenuOptions$38(ItemOptions r1, View r2) {
        r1.dismiss();
        onSubItemClick(9);
        return true;
    }

    private /* synthetic */ void lambda$showMenuOptions$39(ItemOptions r1) {
        r1.dismiss();
        onSubItemClick(2);
    }

    private /* synthetic */ void lambda$showMenuOptions$40(ItemOptions r1) {
        r1.dismiss();
        onSubItemClick(4);
    }

    private /* synthetic */ void lambda$showMenuOptions$41(ItemOptions r1, View r2) {
        r1.dismiss();
        onSubItemClick(6);
    }

    private /* synthetic */ void lambda$showMenuOptions$42(ItemOptions r1) {
        r1.dismiss();
        onSubItemClick(7);
    }

    private /* synthetic */ void lambda$showOptions$47(ItemOptions r1, MessageObject r2) {
        r1.dismiss();
        forward(r2);
    }

    private /* synthetic */ boolean lambda$showOptions$48(ItemOptions r1, MessageObject r2, View r3) {
        r1.dismiss();
        forward(r2, true);
        return true;
    }

    private /* synthetic */ void lambda$showOptions$49(ItemOptions r1, MessageObject r2) {
        r1.dismiss();
        share(r2);
    }

    private /* synthetic */ void lambda$showOptions$50(MessageObject r2, ItemOptions r3) {
        this.savedMusicList.remove(r2);
        this.playlist.remove(r2);
        this.listAdapter.notifyDataSetChanged();
        r3.dismiss();
        setVisibleInProfile(false);
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createSimpleBulletin(R.raw.ic_delete, LocaleController.getString(R.string.AudioSaveToMyProfileUnsaved)).show();
    }

    private /* synthetic */ void lambda$showOptions$51(MessageObject r2, ItemOptions r3) {
        saveToProfile(r2, false, new AudioPlayerAlert$$ExternalSyntheticLambda49(this, r2, r3), false);
    }

    private /* synthetic */ void lambda$showOptions$53(ItemOptions r1, MessageObject r2) {
        r1.dismiss();
        forward(r2);
    }

    private /* synthetic */ boolean lambda$showOptions$54(ItemOptions r1, MessageObject r2, View r3) {
        r1.dismiss();
        forward(r2, true);
        return true;
    }

    private /* synthetic */ void lambda$showOptions$55(ItemOptions r1, MessageObject r2) {
        r1.dismiss();
        share(r2);
    }

    private /* synthetic */ void lambda$showOptions$56(MessageObject r7) {
        int r0 = UserConfig.selectedAccount;
        int r1 = this.currentAccount;
        if (r0 == r1) goto L5;
        this.parentActivity.switchToAccount(r1, true);
    L5:
        Bundle r02 = new Bundle();
        long r12 = r7.getDialogId();
        if (DialogObject.isEncryptedDialog(r12) == false) goto L9;
        r02.putInt("enc_id", DialogObject.getEncryptedChatId(r12));
    L17:
        r02.putInt("message_id", r7.getId());
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.closeChats, new Object[0]);
        this.parentActivity.presentFragment(new ChatActivity(r02), false, false);
        dismiss();
        return;
    L9:
        if (DialogObject.isUserDialog(r12) == false) goto L11;
        r02.putLong("user_id", r12);
        goto L17
    L11:
        TLRPC.Chat r3 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r12));
        if (r3 != null) goto L14;
    L16:
        r02.putLong("chat_id", -r12);
        goto L17
    L14:
        if (r3.migrated_to == null) goto L16;
        r02.putLong("migrated_to", r12);
        r12 = -r3.migrated_to.channel_id;
        goto L16
    }

    private void onSubItemClick(int r8) {
        MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
        if (r0 == null) goto L54;
        LaunchActivity r1 = this.parentActivity;
        if (r1 != null) goto L8;
        return;
    L8:
        if (r8 != 1) goto L12;
        forward(r0, false);
        return;
    L12:
        if (r8 != 9) goto L16;
        forward(r0, true);
        return;
    L16:
        if (r8 != 2) goto L20;
        share(r0);
        return;
    L20:
        if (r8 != 4) goto L39;
        int r82 = UserConfig.selectedAccount;
        int r4 = this.currentAccount;
        if (r82 == r4) goto L24;
        r1.switchToAccount(r4, true);
    L24:
        Bundle r83 = new Bundle();
        long r12 = r0.getDialogId();
        if (DialogObject.isEncryptedDialog(r12) == false) goto L28;
        r83.putInt("enc_id", DialogObject.getEncryptedChatId(r12));
    L36:
        r83.putInt("message_id", r0.getId());
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.closeChats, new Object[0]);
        this.parentActivity.presentFragment(new ChatActivity(r83), false, false);
        dismiss();
        return;
    L28:
        if (DialogObject.isUserDialog(r12) == false) goto L30;
        r83.putLong("user_id", r12);
        goto L36
    L30:
        TLRPC.Chat r42 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r12));
        if (r42 != null) goto L33;
    L35:
        r83.putLong("chat_id", -r12);
        goto L36
    L33:
        if (r42.migrated_to == null) goto L35;
        r83.putLong("migrated_to", r12);
        r12 = -r42.migrated_to.channel_id;
        goto L35
    L39:
        if (r8 != 5) goto L43;
        saveToMusic(r0);
        return;
    L43:
        if (r8 != 6) goto L47;
        ChromecastController.getInstance().setCurrentMediaAndCastIfNeeded(MediaController.getInstance().getCurrentChromecastMedia());
        this.castItemButton.performClick();
        return;
    L47:
        if (r8 != 7) goto L51;
        saveToProfile(r0, false, new AudioPlayerAlert$$ExternalSyntheticLambda22(this, r0), false);
        return;
    L51:
        if (r8 != 8) goto L55;
        new SelectAudioAlert(getContext(), true, null, new AudioPlayerAlert$$ExternalSyntheticLambda23(this), null).withoutSavedMusic().show();
        return;
    L55:
        return;
    }

    private void preloadNeighboringThumbs() {
        MediaController r0 = MediaController.getInstance();
        ArrayList<MessageObject> r1 = r0.getPlaylist();
        if (r1.size() <= 1) goto L28;
        ArrayList r2 = new ArrayList();
        int r02 = r0.getPlayingMessageObjectNum();
        int r4 = r02 + 1;
        int r03 = r02 - 1;
        int r6 = 0;
        if (r4 < r1.size()) goto L9;
        r4 = 0;
    L9:
        if (r4 > (-1)) goto L11;
        r4 = r1.size() - 1;
    L11:
        if (r03 > (-1)) goto L14;
        r03 = r1.size() - 1;
    L14:
        if (r03 < r1.size()) goto L16;
        r03 = 0;
    L16:
        r2.add(r1.get(r4));
        if (r4 == r03) goto L19;
        r2.add(r1.get(r03));
    L19:
        int r04 = r2.size();
    L20:
        if (r6 >= r04) goto L33;
        MessageObject r9 = (MessageObject) r2.get(r6);
        ImageLocation r8 = getArtworkThumbImageLocation(r9);
        if (r8 == null) goto L27;
        if (r8.path == null) goto L26;
        ImageLoader.getInstance().preloadArtwork(r8.path);
        goto L27
    L26:
        FileLoader.getInstance(this.currentAccount).loadFile(r8, r9, null, 0, 1);
    L27:
        r6 = r6 + 1;
        goto L20
    L33:
        return;
    }

    private void saveToMusic(MessageObject r8) {
        if (Build.VERSION.SDK_INT <= 28) goto L7;
        if (BuildVars.NO_SCOPED_STORAGE == true) goto L7;
    L10:
        String r0 = FileLoader.getDocumentFileName(r8.getDocument());
        if (TextUtils.isEmpty(r0) == false) goto L13;
        r0 = r8.getFileName();
    L13:
        String r4 = r0;
        String r02 = r8.messageOwner.attachPath;
        if (r02 != null) goto L16;
    L20:
        if (r02 != null) goto L22;
    L25:
        r02 = FileLoader.getInstance(this.currentAccount).getPathToMessage(r8.messageOwner).toString();
    L24:
        String r1 = r02;
        LaunchActivity r2 = this.parentActivity;
        if (r8.getDocument() == null) goto L30;
        String r82 = r8.getDocument().mime_type;
    L29:
        String r5 = r82;
        MediaController.saveFile(r1, r2, 3, r4, r5, new AudioPlayerAlert$$ExternalSyntheticLambda48(this));
        return;
    L30:
        r82 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L29
    L22:
        if (r02.length() != 0) goto L24;
    L16:
        if (r02.length() <= 0) goto L20;
        if (new File(r02).exists() == true) goto L20;
        r02 = null;
    L7:
        if (this.parentActivity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) goto L10;
        this.parentActivity.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 4);
    }

    private void saveToProfile(MessageObject r12, boolean r13, Runnable r14, boolean r15) {
        TLRPC.Document r8 = r12.getDocument();
        if (r8 != null) goto L5;
        return;
    L5:
        long r6 = r8.id;
        TLRPC.TL_account_saveMusic r9 = new TLRPC.TL_account_saveMusic();
        r9.unsave = !r13;
        TLRPC.TL_inputDocument r0 = new TLRPC.TL_inputDocument();
        r9.id = r0;
        r0.id = r6;
        r0.access_hash = r8.access_hash;
        byte[] r1 = r8.file_reference;
        r0.file_reference = r1;
        if (r1 != null) goto L8;
        r0.file_reference = new byte[0];
    L8:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r9, new AudioPlayerAlert$$ExternalSyntheticLambda50(this, r15, r12, r13, r14, r6, r8));
    }

    private boolean scrollToCurrentSong(boolean r6) {
        MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
        if (r0 == null) goto L25;
        if (r6 == false) goto L15;
        int r62 = this.listView.getChildCount();
        int r2 = 0;
    L6:
        if (r2 >= r62) goto L15;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof AudioPlayerCell) == false) goto L14;
        if (((AudioPlayerCell) r3).getMessageObject() != r0) goto L14;
        if (r3.getBottom() > this.listView.getMeasuredHeight()) goto L15;
    L14:
        r2 = r2 + 1;
    L15:
        int r63 = this.playlist.indexOf(r0);
        if (this.padWithItem == false) goto L18;
        r63 = r63 + 1;
    L18:
        if (r63 < 0) goto L25;
        boolean r02 = SharedConfig.playOrderReversed;
        LinearLayoutManager r1 = this.layoutManager;
        if (r02 == false) goto L22;
        r1.scrollToPosition(r63);
        return true;
    L22:
        r1.scrollToPosition(this.playlist.size() - r63);
        return true;
    L25:
        return false;
    }

    private void setMenuItemChecked(ActionBarMenuSubItem r2, boolean r3) {
        if (r3 == false) goto L5;
        int r32 = Theme.key_player_buttonActive;
        r2.setTextColor(getThemedColor(r32));
        r2.setIconColor(getThemedColor(r32));
        return;
    L5:
        int r33 = Theme.key_actionBarDefaultSubmenuItem;
        r2.setTextColor(getThemedColor(r33));
        r2.setIconColor(getThemedColor(r33));
    }

    private void setVisibleInProfile(boolean r9) {
        if (isMyList() == false) goto L5;
    L34:
        this.saveToProfileButton.setVisibility(8);
        this.unsaveFromProfileButton.setVisibility(8);
        return;
    L5:
        if (this.noforwards == true) goto L34;
        if (this.lastMessageObject.isVoice() == true) goto L34;
        this.saveToProfileButton.setVisibility(0);
        this.unsaveFromProfileButton.setVisibility(0);
        ViewPropertyAnimator r0 = this.saveToProfileButton.animate();
        float r1 = 0.0f;
        float r2 = 1.0f;
        if (r9 == false) goto L12;
        float r3 = 0.0f;
    L13:
        ViewPropertyAnimator r02 = r0.alpha(r3);
        if (r9 == false) goto L16;
        float r4 = 0.8f;
    L17:
        ViewPropertyAnimator r03 = r02.scaleX(r4);
        if (r9 == false) goto L20;
        float r42 = 0.8f;
    L21:
        ViewPropertyAnimator r04 = r03.scaleY(r42).setDuration(420);
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r04.setInterpolator(r6).withEndAction(new AudioPlayerAlert$$ExternalSyntheticLambda1(this, r9)).start();
        ViewPropertyAnimator r05 = this.unsaveFromProfileButton.animate();
        if (r9 == false) goto L25;
        r1 = 1.0f;
    L25:
        ViewPropertyAnimator r06 = r05.alpha(r1);
        if (r9 == true) goto L28;
        float r12 = 0.8f;
    L29:
        ViewPropertyAnimator r07 = r06.scaleX(r12);
        if (r9 == true) goto L32;
        r2 = 0.8f;
    L32:
        r07.scaleY(r2).setDuration(420).setInterpolator(r6).withEndAction(new AudioPlayerAlert$$ExternalSyntheticLambda2(this, r9)).start();
        return;
    L28:
        r12 = 1.0f;
        goto L29
    L20:
        r42 = 1.0f;
        goto L21
    L16:
        r4 = 1.0f;
        goto L17
    L12:
        r3 = 1.0f;
        goto L13
    }

    private void share(MessageObject r6) {
    L19:
        e = move-exception;
        FileLog.e(e);
        return;
    L4:
        if (TextUtils.isEmpty(r6.messageOwner.attachPath) == true) goto L7;
        File r1 = new File(r6.messageOwner.attachPath);     // Catch: Exception -> L19
        if (r1.exists() == false) goto L7;
    L8:
        if (r1 != null) goto L11;
        r1 = FileLoader.getInstance(this.currentAccount).getPathToMessage(r6.messageOwner);     // Catch: Exception -> L19
    L11:
        if (r1.exists() == false) goto L17;
        Intent r2 = new Intent("android.intent.action.SEND");     // Catch: Exception -> L19
        r2.setType(r6.getMimeType());     // Catch: Exception -> L19
        r2.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(ApplicationLoader.applicationContext, ApplicationLoader.getApplicationId() + ".provider", r1));     // Catch: Exception -> L15
        r2.setFlags(1);     // Catch: Exception -> L15
    L16:
        this.parentActivity.startActivityForResult(Intent.createChooser(r2, LocaleController.getString(R.string.ShareFile)), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L19
        return;
    L15:
        r2.putExtra("android.intent.extra.STREAM", Uri.fromFile(r1));     // Catch: Exception -> L19
        goto L16
    L17:
        AlertDialog.Builder r62 = new AlertDialog.Builder(this.parentActivity);     // Catch: Exception -> L19
        r62.setTitle(LocaleController.getString(R.string.AppName));     // Catch: Exception -> L19
        r62.setPositiveButton(LocaleController.getString(R.string.OK), null);     // Catch: Exception -> L19
        r62.setMessage(LocaleController.getString(R.string.PleaseDownload));     // Catch: Exception -> L19
        r62.show();     // Catch: Exception -> L19
        return;
    L7:
        r1 = null;
        goto L8
    }

    private void showAlbumCover(boolean r6, boolean r7) {
        FrameLayout r0 = this.blurredView;
        if (r6 == false) goto L14;
        if (r0.getVisibility() != 0) goto L7;
        return;
    L7:
        if (this.blurredAnimationInProgress == false) goto L9;
        return;
    L9:
        if (this.noCover == true) goto L24;
        this.blurredView.setTag(1);
        this.bigAlbumConver.setImageBitmap(this.coverContainer.getImageReceiver().getBitmap());
        this.blurredAnimationInProgress = true;
        ScrimOptions.makeGlobalBlurBitmaps(new AudioPlayerAlert$$ExternalSyntheticLambda3(this));
        this.blurredView.setVisibility(0);
        this.blurredView.animate().alpha(1.0f).setDuration(180).setListener(new AnonymousClass21(this)).start();
        this.bigAlbumConver.animate().scaleX(1.0f).scaleY(1.0f).setDuration(180).start();
        return;
    L24:
        return;
    L14:
        if (r0.getVisibility() != 0) goto L23;
        this.blurredView.setTag(null);
        if (r7 == false) goto L20;
        this.blurredAnimationInProgress = true;
        this.blurredView.animate().alpha(0.0f).setDuration(180).setListener(new AnonymousClass22(this)).start();
        this.bigAlbumConver.animate().scaleX(0.9f).scaleY(0.9f).setDuration(180).start();
        return;
    L20:
        this.blurredView.setAlpha(0.0f);
        this.blurredView.setVisibility(4);
        this.bigAlbumConver.setImageBitmap(null);
        this.bigAlbumConver.setScaleX(0.9f);
        this.bigAlbumConver.setScaleY(0.9f);
        return;
    }

    private void showMenuOptions(View r11) {
        MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
        if (r0 != null) goto L5;
        return;
    L5:
        boolean r3 = true;
        ItemOptions r4 = ItemOptions.makeOptions(this.container, this.resourcesProvider, r11, true);
        ItemOptions r112 = buildSaveOptions(r4, r0);
        if (isMyList() == true) goto L13;
        r4.addIf(!this.noforwards, R.drawable.msg_stories_save, LocaleController.getString(R.string.AudioSaveTo), new AudioPlayerAlert$$ExternalSyntheticLambda35(r4, r112));
        if (this.noforwards == false) goto L10;
    L12:
        r4.addGap();
        goto L13
    L10:
        if (r4.getLast() == null) goto L12;
        r4.getLast().setRightIcon(R.drawable.msg_arrowright);
    L13:
        r4.addIf(!this.noforwards, R.drawable.msg_forward, LocaleController.getString(R.string.Forward), new AudioPlayerAlert$$ExternalSyntheticLambda36(this, r4));
        if (this.noforwards == false) goto L16;
    L18:
        r4.addIf(!this.noforwards, R.drawable.msg_shareout, LocaleController.getString(R.string.ShareFile), new AudioPlayerAlert$$ExternalSyntheticLambda38(this, r4));
        if (r0.getId() > 0) goto L22;
        r3 = false;
    L22:
        r4.addIf(r3, R.drawable.msg_message, LocaleController.getString(R.string.ShowInChat), new AudioPlayerAlert$$ExternalSyntheticLambda39(this, r4));
        if (this.castAvailable == false) goto L25;
        ActionBarMenuSubItem r113 = r4.add();
        this.castItem = r113;
        r113.setTextAndIcon(LocaleController.getString(R.string.VideoPlayerChromecast), R.drawable.menu_video_chromecast);
        this.castItem.setOnClickListener(new AudioPlayerAlert$$ExternalSyntheticLambda40(this, r4));
        AndroidUtilities.removeFromParent(this.castItemButton);
        this.castItem.addView(this.castItemButton, 0, LayoutHelper.createFrame(-1, -1.0f));
        updateColors();
    L25:
        r4.addIf(isMyList(), R.drawable.msg_delete, LocaleController.getString(R.string.ProfilePlaylistRemoveFromProfile), true, new AudioPlayerAlert$$ExternalSyntheticLambda41(this, r4));
        r4.setTranslationY(AndroidUtilities.dp(64.0f));
        r4.show();
        return;
    L16:
        if (r4.getLast() == null) goto L18;
        r4.getLast().setOnLongClickListener(new AudioPlayerAlert$$ExternalSyntheticLambda37(this, r4));
        goto L18
    }

    private void showOptions(AudioPlayerCell r7, MessageObject r8) {
        boolean r2 = true;
        ItemOptions r72 = ItemOptions.makeOptions(this.container, this.resourcesProvider, r7, true);
        if (isMyList() == false) goto L10;
        r72.addIf(!this.noforwards, R.drawable.msg_forward, LocaleController.getString(R.string.Forward), new AudioPlayerAlert$$ExternalSyntheticLambda26(this, r72, r8));
        if (this.noforwards == false) goto L7;
    L9:
        r72.addIf(!this.noforwards, R.drawable.msg_shareout, LocaleController.getString(R.string.ShareFile), new AudioPlayerAlert$$ExternalSyntheticLambda28(this, r72, r8));
        r72.add(R.drawable.msg_delete, LocaleController.getString(R.string.Delete), true, new AudioPlayerAlert$$ExternalSyntheticLambda29(this, r8, r72));
    L26:
        if (LocaleController.isRTL == false) goto L28;
        int r6 = 3;
    L29:
        r72.setGravity(r6);
        r72.show();
        return;
    L28:
        r6 = 5;
        goto L29
    L7:
        if (r72.getLast() == null) goto L9;
        r72.getLast().setOnLongClickListener(new AudioPlayerAlert$$ExternalSyntheticLambda27(this, r72, r8));
        goto L9
    L10:
        r72.addIf(!this.noforwards, R.drawable.msg_stories_save, LocaleController.getString(R.string.AudioSaveTo), new AudioPlayerAlert$$ExternalSyntheticLambda30(r72, buildSaveOptions(r72, r8)));
        if (this.noforwards == false) goto L13;
    L15:
        r72.addGap();
        r72.addIf(!this.noforwards, R.drawable.msg_share, LocaleController.getString(R.string.Forward), new AudioPlayerAlert$$ExternalSyntheticLambda31(this, r72, r8));
        if (this.noforwards == false) goto L18;
    L20:
        r72.addIf(!this.noforwards, R.drawable.msg_shareout, LocaleController.getString(R.string.ShareFile), new AudioPlayerAlert$$ExternalSyntheticLambda33(this, r72, r8));
        if (r8.getId() > 0) goto L24;
        r2 = false;
    L24:
        r72.addIf(r2, R.drawable.msg_view_file, LocaleController.getString(R.string.ShowInChat), new AudioPlayerAlert$$ExternalSyntheticLambda34(this, r8));
        goto L26
    L18:
        if (r72.getLast() == null) goto L20;
        r72.getLast().setOnLongClickListener(new AudioPlayerAlert$$ExternalSyntheticLambda32(this, r72, r8));
        goto L20
    L13:
        if (r72.getLast() == null) goto L15;
        r72.getLast().setRightIcon(R.drawable.msg_arrowright);
        goto L15
    }

    private void showSpeedHint() {
        if (this.containerView == null) goto L6;
        AnonymousClass20 r0 = new AnonymousClass20(this, getContext(), 5, false);
        this.speedHintView = r0;
        r0.setExtraTranslationY(AndroidUtilities.dp(6.0f));
        this.speedHintView.setText(LocaleController.getString(R.string.SpeedHint));
        this.playerLayout.addView(this.speedHintView, LayoutHelper.createFrame(-2, -2.0f, 48, 0.0f, 0.0f, 6.0f, 0.0f));
        this.speedHintView.showForView(this.playbackSpeedButton, true);
        return;
    }

    private void startForwardRewindingSeek() {
        if (this.rewindingState != 1) goto L6;
        this.lastRewindingTime = System.currentTimeMillis();
        this.rewindingProgress = MediaController.getInstance().getPlayingMessageObject().audioProgress;
        AndroidUtilities.cancelRunOnUIThread(this.forwardSeek);
        AndroidUtilities.runOnUIThread(this.forwardSeek);
        return;
    }

    private void updateEmptyView() {
        LinearLayout r0 = this.emptyView;
        if (this.searching == true) goto L5;
    L7:
        int r1 = 8;
    L8:
        r0.setVisibility(r1);
        updateEmptyViewPosition();
        return;
    L5:
        if (this.listAdapter.getItemCount() != 0) goto L7;
        r1 = 0;
        goto L8
    }

    private void updateEmptyViewPosition() {
        if (this.emptyView.getVisibility() == 0) goto L6;
        return;
    L6:
        if (this.playerLayout.getVisibility() != 0) goto L8;
        int r0 = AndroidUtilities.dp(150.0f);
    L9:
        this.emptyView.setTranslationY(((r1.getMeasuredHeight() - this.containerView.getMeasuredHeight()) - r0) / 2);
        return;
    L8:
        r0 = -AndroidUtilities.dp(30.0f);
        goto L9
    }

    private void updateLayout() {
        int r0 = this.listView.getChildCount();
        RecyclerListView r1 = this.listView;
        if (r0 > 0) goto L6;
        int r02 = r1.getPaddingTop();
        this.scrollOffsetY = r02;
        r1.setTopGlowOffset(r02);
        this.containerView.invalidate();
        return;
    L6:
        boolean r03 = false;
        View r12 = r1.getChildAt(0);
        RecyclerListView.Holder r2 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r12);
        if ((r12 instanceof AudioPlayerCell) == false) goto L9;
        int r13 = r12.getTop();
    L10:
        int r4 = AndroidUtilities.dp(7.0f);
        if (r13 < AndroidUtilities.dp(7.0f)) goto L16;
        if (r2 == null) goto L16;
        if (r2.getAdapterPosition() != 0) goto L16;
    L18:
        if (r13 > AndroidUtilities.dp(12.0f)) goto L20;
        boolean r22 = true;
    L21:
        float r42 = 1.0f;
        if (r22 == true) goto L24;
    L25:
        if (r22 == false) goto L27;
    L50:
        FrameLayout.LayoutParams r23 = (FrameLayout.LayoutParams) this.listView.getLayoutParams();
        int r14 = r13 + ((r23.topMargin - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(11.0f));
        if (this.scrollOffsetY == r14) goto L53;
        RecyclerListView r5 = this.listView;
        this.scrollOffsetY = r14;
        r5.setTopGlowOffset((r14 - r23.topMargin) - AndroidUtilities.statusBarHeight);
        this.containerView.invalidate();
    L53:
        int r15 = AndroidUtilities.dp(13.0f);
        if ((this.backgroundPaddingTop + ((int) (((this.scrollOffsetY - this.backgroundPaddingTop) - r15) + this.listView.getTranslationY()))) >= ActionBar.getCurrentActionBarHeight()) goto L57;
        r42 = 1.0f - Math.min(1.0f, ((ActionBar.getCurrentActionBarHeight() - r2) - this.backgroundPaddingTop) / (r15 + AndroidUtilities.dp(4.0f)));
    L57:
        if (r42 > 0.5f) goto L62;
        if (ColorUtils.calculateLuminance(getThemedColor(Theme.key_dialogBackground)) <= 0.699999988079071d) goto L62;
        r03 = true;
    L62:
        if (r03 == this.wasLight) goto L65;
        this.wasLight = r03;
        AndroidUtilities.setLightStatusBar(this, r03);
        return;
    L65:
        return;
    L27:
        if (this.actionBar.getTag() == null) goto L50;
    L28:
        ActionBar r52 = this.actionBar;
        if (r22 == false) goto L31;
        Integer r7 = 1;
    L32:
        r52.setTag(r7);
        AnimatorSet r53 = this.actionBarAnimation;
        if (r53 == null) goto L35;
        r53.cancel();
        this.actionBarAnimation = null;
    L35:
        AnimatorSet r54 = new AnimatorSet();
        this.actionBarAnimation = r54;
        boolean r6 = this.isProfilePlaylist;
        ActionBar r72 = this.actionBar;
        float r8 = 0.0f;
        if (r6 == false) goto L45;
        Property<ActionBar, Float> r62 = this.actionBarSlideProperty;
        if (r22 == false) goto L40;
        float r9 = 1.0f;
    L41:
        ObjectAnimator r63 = ObjectAnimator.ofFloat(r72, r62, new float[]{r9});
        View r73 = this.actionBarBackground;
        Property r92 = View.ALPHA;
        if (r22 == false) goto L44;
        r8 = 1.0f;
    L44:
        r54.playTogether(new Animator[]{r63, ObjectAnimator.ofFloat(r73, r92, new float[]{r8})});
    L49:
        this.actionBarAnimation.setDuration(320);
        this.actionBarAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.actionBarAnimation.addListener(new AnonymousClass23(this));
        this.actionBarAnimation.start();
        goto L50
    L40:
        r9 = 0.0f;
        goto L41
    L45:
        Property r64 = View.ALPHA;
        if (r22 == false) goto L48;
        r8 = 1.0f;
    L48:
        r54.playTogether(new Animator[]{ObjectAnimator.ofFloat(r72, r64, new float[]{r8})});
        goto L49
    L31:
        r7 = null;
        goto L32
    L24:
        if (this.actionBar.getTag() == null) goto L28;
    L20:
        r22 = false;
    L16:
        r13 = r4;
        goto L18
    L9:
        r13 = r12.getBottom();
        goto L10
    }

    private void updatePlaybackButton(boolean r6) {
        if (this.playbackSpeedButton == null) goto L14;
        float r0 = MediaController.getInstance().getPlaybackSpeed(true);
        this.speedIcon.setValue(r0, r6);
        this.speedSlider.setSpeed(r0, r6);
        updateColors();
        boolean r62 = this.slidingSpeed;
        int r1 = 0;
        this.slidingSpeed = false;
    L7:
        if (r1 >= this.speedItems.length) goto L18;
        if (r62 == false) goto L10;
    L12:
        ActionBarMenuSubItem r2 = this.speedItems[r1];
        int r3 = Theme.key_actionBarDefaultSubmenuItem;
        r2.setColors(getThemedColor(r3), getThemedColor(r3));
    L13:
        r1 = r1 + 1;
        goto L7
    L10:
        if (equals(r0, speeds[r1]) == false) goto L12;
        ActionBarMenuSubItem r22 = this.speedItems[r1];
        int r32 = Theme.key_featuredStickers_addButtonPressed;
        r22.setColors(getThemedColor(r32), getThemedColor(r32));
        goto L13
    L18:
        return;
    }

    private void updateProgress(MessageObject r2) {
        updateProgress(r2, false);
    }

    private void updateProgress(MessageObject r10, boolean r11) {
        SeekBarView r0 = this.seekBarView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isDragging() == false) goto L8;
        int r112 = (int) (r10.getDuration() * this.seekBarView.getProgress());
    L37:
        if (this.lastTime == r112) goto L39;
        this.lastTime = r112;
        this.timeTextView.setText(AndroidUtilities.formatShortDuration(r112));
    L39:
        this.seekBarView.updateTimestamps(r10, null);
        return;
    L8:
        if (this.rewindingProgress < 0.0f) goto L15;
        int r02 = this.rewindingState;
        boolean r2 = true;
        if (r02 == (-1)) goto L16;
        if (r02 != 1) goto L15;
        if (MediaController.getInstance().isMessagePaused() == false) goto L15;
    L16:
        SeekBarView r03 = this.seekBarView;
        if (r2 == false) goto L19;
        r03.setProgress(this.rewindingProgress, r11);
    L20:
        float r1 = 1.0f;
        if (this.currentAudioFinishedLoading == true) goto L31;
        long r3 = SystemClock.elapsedRealtime();
        if (Math.abs(r3 - this.lastBufferedPositionCheck) >= 500) goto L26;
        r1 = -1.0f;
        goto L31
    L26:
        if (MediaController.getInstance().isStreamingCurrentAudio() == false) goto L28;
        r1 = FileLoader.getInstance(this.currentAccount).getBufferedProgressFromPosition(r10.audioProgress, this.currentFile);
    L28:
        this.lastBufferedPositionCheck = r3;
    L31:
        if (r1 == (-1.0f)) goto L33;
        this.seekBarBufferSpring.getSpring().setFinalPosition(r1 * 1000.0f);
        this.seekBarBufferSpring.start();
    L33:
        if (r2 == false) goto L35;
        r112 = (int) (r10.getDuration() * this.seekBarView.getProgress());
        r10.audioProgressSec = r112;
        goto L37
    L35:
        r112 = r10.audioProgressSec;
        goto L37
    L19:
        r03.setProgress(r10.audioProgress, r11);
    L15:
        r2 = false;
        goto L16
    }

    private void updateSubMenu() {
        setMenuItemChecked(this.shuffleListItem, SharedConfig.shuffleMusic);
        setMenuItemChecked(this.reverseOrderItem, SharedConfig.playOrderReversed);
        ActionBarMenuSubItem r0 = this.repeatListItem;
        boolean r2 = false;
        if (SharedConfig.repeatMode != 1) goto L5;
        boolean r1 = true;
    L6:
        setMenuItemChecked(r0, r1);
        ActionBarMenuSubItem r02 = this.repeatSongItem;
        if (SharedConfig.repeatMode != 2) goto L9;
        r2 = true;
    L9:
        setMenuItemChecked(r02, r2);
        return;
    L5:
        r1 = false;
        goto L6
    }

    private void updateTitle(boolean r10) {
        MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
        if (r0 != null) goto L5;
        if (r10 == false) goto L5;
    L10:
        dismiss();
        return;
    L5:
        if (r0 != null) goto L7;
    L12:
        if (r0 != null) goto L16;
        this.lastMessageObject = null;
        return;
    L16:
        if (r0 != this.lastMessageObject) goto L18;
        boolean r102 = true;
    L19:
        this.lastMessageObject = r0;
        if (r0.eventId == 0) goto L22;
    L25:
        this.optionsButton.setVisibility(4);
    L26:
        long r3 = r0.getDialogId();
        if (r0.getDocument() == null) goto L29;
        long r7 = r0.getDocument().id;
    L31:
        if (r3 >= 0) goto L35;
        if (MessagesController.getInstance(this.currentAccount).isPeerNoForwards(r3) == false) goto L35;
    L40:
        boolean r32 = true;
    L42:
        if (r32 == this.noforwards) goto L52;
        this.noforwards = r32;
        FrameLayout.LayoutParams r4 = (FrameLayout.LayoutParams) this.playerLayout.getLayoutParams();
        if (r32 == false) goto L46;
    L50:
        int r5 = 0;
    L51:
        r4.height = AndroidUtilities.dp(184 + r5);
        this.playerLayout.setLayoutParams(r4);
        goto L52
    L46:
        if (isMyList() == true) goto L50;
        if (r0.isVoice() == true) goto L50;
        r5 = 52;
    L52:
        ActionBarMenuItem r42 = this.optionsButton;
        if (r32 == false) goto L55;
        r42.hideSubItem(1);
        this.optionsButton.hideSubItem(2);
        this.optionsButton.hideSubItem(5);
        this.optionsButton.hideSubItem(6);
        this.optionsButton.setAdditionalYOffset(-AndroidUtilities.dp(16.0f));
    L56:
        checkIfMusicDownloaded(r0);
        updateProgress(r0, !r102);
        updateCover(r0, !r102);
        boolean r33 = MediaController.getInstance().isMessagePaused();
        PlayPauseDrawable r43 = this.playPauseDrawable;
        if (r33 == false) goto L59;
        r43.setPause(false);
        this.playButton.setContentDescription(LocaleController.getString(R.string.AccActionPlay));
    L60:
        String r2 = r0.getMusicTitle();
        String r34 = r0.getMusicAuthor();
        this.titleTextView.setText(r2);
        this.authorTextView.setText(r34);
        MessagesController.SavedMusicIds r22 = MessagesController.getInstance(this.currentAccount).getSavedMusicIds();
        this.saveToProfileButton.setLoading(r22.loading);
        setVisibleInProfile(r22.ids.contains(Long.valueOf(r7)));
        int r02 = (int) r0.getDuration();
        this.lastDuration = r02;
        TextView r23 = this.durationTextView;
        if (r23 == null) goto L66;
        if (r02 == 0) goto L64;
        String r35 = AndroidUtilities.formatShortDuration(r02);
    L65:
        r23.setText(r35);
        goto L66
    L64:
        r35 = "-:--";
    L66:
        ActionBarMenuItem r24 = this.playbackSpeedButton;
        if (r02 <= 1800) goto L69;
        r24.setVisibility(0);
    L70:
        if (r102 == true) goto L73;
        preloadNeighboringThumbs();
        return;
    L73:
        return;
    L69:
        r24.setVisibility(8);
        goto L70
    L59:
        r43.setPause(true);
        this.playButton.setContentDescription(LocaleController.getString(R.string.AccActionPause));
        goto L60
    L55:
        r42.showSubItem(1);
        this.optionsButton.showSubItem(2);
        this.optionsButton.showSubItem(5);
        this.optionsButton.setAdditionalYOffset(-AndroidUtilities.dp(197.0f));
    L35:
        if (MessagesController.getInstance(this.currentAccount).isPeerNoForwards(r0.getDialogId()) == true) goto L40;
        if (r0.messageOwner.noforwards == true) goto L40;
        r32 = false;
        goto L42
    L29:
        r7 = 0;
        goto L31
    L22:
        if (r0.getId() <= (-2000000000)) goto L25;
        this.optionsButton.setVisibility(0);
        goto L26
    L18:
        r102 = false;
        goto L19
    L7:
        if (r0.isMusic() == true) goto L12;
        if (r0.isVoice() == true) goto L12;
        goto L10
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r9, int r10, Object... r11) {
        int r2 = 0;
        if (r9 != NotificationCenter.messagePlayingDidStart) goto L5;
    L70:
        int r102 = NotificationCenter.messagePlayingDidReset;
        if (r9 == r102) goto L73;
    L75:
        boolean r3 = false;
    L76:
        updateTitle(r3);
        if (r9 != r102) goto L79;
    L96:
        int r103 = this.listView.getChildCount();
        int r112 = 0;
    L97:
        if (r112 >= r103) goto L109;
        View r5 = this.listView.getChildAt(r112);
        if ((r5 instanceof AudioPlayerCell) == false) goto L107;
        AudioPlayerCell r52 = (AudioPlayerCell) r5;
        MessageObject r6 = r52.getMessageObject();
        if (r6 == null) goto L107;
        if (r6.isVoice() == false) goto L105;
    L106:
        r52.updateButtonState(false, true);
        goto L107
    L105:
        if (r6.isMusic() == true) goto L106;
    L107:
        r112 = r112 + 1;
        goto L97
    L109:
        if (r9 == NotificationCenter.messagePlayingPlayStateChanged) goto L111;
    L120:
        ChooseQualityLayout$QualityIcon r8 = this.optionsIcon;
        if (r8 == null) goto L142;
        r8.setCasting(CastSync.isActive(), true);
        return;
    L142:
        return;
    L111:
        if (MediaController.getInstance().getPlayingMessageObject() == null) goto L120;
        if (MediaController.getInstance().isMessagePaused() == false) goto L116;
        startForwardRewindingSeek();
        goto L120
    L116:
        if (this.rewindingState != 1) goto L120;
        if (this.rewindingProgress == (-1.0f)) goto L120;
        AndroidUtilities.cancelRunOnUIThread(this.forwardSeek);
        this.lastUpdateRewindingPlayerTime = 0;
        this.forwardSeek.run();
        this.rewindingProgress = -1.0f;
        goto L120
    L79:
        if (r9 == NotificationCenter.messagePlayingPlayStateChanged) goto L96;
        if (((MessageObject) r11[0]).eventId != 0) goto L144;
        int r92 = this.listView.getChildCount();
        int r104 = 0;
    L85:
        if (r104 >= r92) goto L120;
        View r113 = this.listView.getChildAt(r104);
        if ((r113 instanceof AudioPlayerCell) == false) goto L95;
        AudioPlayerCell r114 = (AudioPlayerCell) r113;
        MessageObject r0 = r114.getMessageObject();
        if (r0 == null) goto L95;
        if (r0.isVoice() == false) goto L93;
    L94:
        r114.updateButtonState(false, true);
        goto L95
    L93:
        if (r0.isMusic() == true) goto L94;
    L95:
        r104 = r104 + 1;
        goto L85
    L144:
        return;
    L73:
        if (((Boolean) r11[1]).booleanValue() == false) goto L75;
        r3 = true;
        goto L76
    L5:
        if (r9 == NotificationCenter.messagePlayingPlayStateChanged) goto L70;
        if (r9 == NotificationCenter.messagePlayingDidReset) goto L70;
        if (r9 != NotificationCenter.messagePlayingProgressDidChanged) goto L20;
        MessageObject r93 = MediaController.getInstance().getPlayingMessageObject();
        if (r93 != null) goto L14;
        return;
    L14:
        if (r93.isMusic() == false) goto L16;
    L17:
        updateProgress(r93);
        return;
    L16:
        if (r93.isVoice() == true) goto L17;
        return;
    L20:
        if (r9 != NotificationCenter.messagePlayingSpeedChanged) goto L24;
        updatePlaybackButton(true);
        return;
    L24:
        if (r9 != NotificationCenter.musicDidLoad) goto L28;
        this.savedMusicList = MediaController.getInstance().currentSavedMusicList;
        this.playlist = MediaController.getInstance().getPlaylist();
        this.listAdapter.notifyDataSetChanged();
        return;
    L28:
        if (r9 != NotificationCenter.moreMusicDidLoad) goto L40;
        this.savedMusicList = MediaController.getInstance().currentSavedMusicList;
        this.playlist = MediaController.getInstance().getPlaylist();
        this.listAdapter.notifyDataSetChanged();
        if (SharedConfig.playOrderReversed == false) goto L136;
        this.listView.stopScroll();
        int r94 = ((Integer) r11[0]).intValue();
        int r105 = this.layoutManager.findLastVisibleItemPosition();
        if (r105 == (-1)) goto L137;
        View r115 = this.layoutManager.findViewByPosition(r105);
        if (r115 == null) goto L37;
        r2 = r115.getTop();
    L37:
        this.layoutManager.scrollToPositionWithOffset(r105 + r94, r2);
        return;
    L137:
        return;
    L136:
        return;
    L40:
        if (r9 != NotificationCenter.fileLoaded) goto L46;
        if (((String) r11[0]).equals(this.currentFile) == false) goto L138;
        updateTitle(false);
        this.currentAudioFinishedLoading = true;
        return;
    L138:
        return;
    L46:
        if (r9 != NotificationCenter.fileLoadProgressChanged) goto L67;
        if (((String) r11[0]).equals(this.currentFile) == false) goto L139;
        MessageObject r95 = MediaController.getInstance().getPlayingMessageObject();
        if (r95 == null) goto L143;
        float r116 = 1.0f;
        if (this.currentAudioFinishedLoading == true) goto L63;
        long r1 = SystemClock.elapsedRealtime();
        if (Math.abs(r1 - this.lastBufferedPositionCheck) >= 500) goto L58;
        r116 = -1.0f;
        goto L63
    L58:
        if (MediaController.getInstance().isStreamingCurrentAudio() == false) goto L60;
        r116 = FileLoader.getInstance(this.currentAccount).getBufferedProgressFromPosition(r95.audioProgress, this.currentFile);
    L60:
        this.lastBufferedPositionCheck = r1;
    L63:
        if (r116 == (-1.0f)) goto L140;
        this.seekBarBufferSpring.getSpring().setFinalPosition(r116 * 1000.0f);
        this.seekBarBufferSpring.start();
        return;
    L140:
        return;
    L143:
        return;
    L139:
        return;
    L67:
        if (r9 != NotificationCenter.musicIdsLoaded) goto L141;
        updateTitle(false);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingDidStart);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileLoadProgressChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.musicDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.moreMusicDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.musicIdsLoaded);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.messagePlayingSpeedChanged);
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
        if (instance != this) goto L6;
        instance = null;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public int getContainerViewHeight() {
        if (this.playerLayout != null) goto L7;
        return 0;
    L7:
        if (this.playlist.size() <= 1) goto L9;
        int r0 = AndroidUtilities.dp(13.0f);
        int r1 = (int) (((this.scrollOffsetY - this.backgroundPaddingTop) - r0) + this.listView.getTranslationY());
        if ((this.backgroundPaddingTop + r1) >= ActionBar.getCurrentActionBarHeight()) goto L14;
        float r02 = r0 + AndroidUtilities.dp(4.0f);
        r1 = r1 - ((int) ((ActionBar.getCurrentActionBarHeight() - r02) * Math.min(1.0f, ((ActionBar.getCurrentActionBarHeight() - r1) - this.backgroundPaddingTop) / r02)));
    L14:
        return this.container.getMeasuredHeight() - (r1 + AndroidUtilities.statusBarHeight);
    L9:
        return this.playerLayout.getMeasuredHeight() + this.backgroundPaddingTop;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public int getObserverTag() {
        return this.TAG;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        AudioPlayerAlert$$ExternalSyntheticLambda0 r8 = new AudioPlayerAlert$$ExternalSyntheticLambda0(this);
        ActionBar r3 = this.actionBar;
        int r4 = ThemeDescription.FLAG_AB_ITEMSCOLOR;
        int r16 = Theme.key_player_actionBarTitle;
        r1.add(new ThemeDescription(r3, r4, null, null, null, r8, r16));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBTITLECOLOR, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_player_actionBarSelector));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCH, null, null, null, null, r16));
        ActionBar r2 = this.actionBar;
        int r19 = ThemeDescription.FLAG_AB_SEARCHPLACEHOLDER;
        int r27 = Theme.key_player_time;
        r1.add(new ThemeDescription(r2, r19, null, null, null, null, r27));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{AudioPlayerCell.class}, null, null, null, Theme.key_chat_inLoader));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{AudioPlayerCell.class}, null, null, null, Theme.key_chat_outLoader));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{AudioPlayerCell.class}, null, null, null, Theme.key_chat_inLoaderSelected));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{AudioPlayerCell.class}, null, null, null, Theme.key_chat_inMediaIcon));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{AudioPlayerCell.class}, null, null, null, Theme.key_chat_inMediaIconSelected));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{AudioPlayerCell.class}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{AudioPlayerCell.class}, null, null, null, Theme.key_chat_inAudioSelectedProgress));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{AudioPlayerCell.class}, null, null, null, Theme.key_chat_inAudioProgress));
        r1.add(new ThemeDescription(this.containerView, 0, null, null, new Drawable[]{this.shadowDrawable}, null, Theme.key_dialogBackground));
        LineProgressView r22 = this.progressView;
        int r24 = Theme.key_player_progressBackground;
        r1.add(new ThemeDescription(r22, 0, null, null, null, null, r24));
        LineProgressView r23 = this.progressView;
        int r35 = Theme.key_player_progress;
        r1.add(new ThemeDescription(r23, 0, null, null, null, null, r35));
        r1.add(new ThemeDescription(this.seekBarView, 0, null, null, null, null, r24));
        r1.add(new ThemeDescription(this.seekBarView, 0, null, null, null, null, Theme.key_player_progressCachedBackground));
        r1.add(new ThemeDescription(this.seekBarView, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, r35));
        r1.add(new ThemeDescription(this.playbackSpeedButton, ThemeDescription.FLAG_CHECKTAG | ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_inappPlayerPlayPause));
        r1.add(new ThemeDescription(this.playbackSpeedButton, ThemeDescription.FLAG_CHECKTAG | ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_inappPlayerClose));
        ActionBarMenuItem r32 = this.repeatButton;
        int r15 = Theme.key_player_button;
        r1.add(new ThemeDescription(r32, 0, null, null, null, r8, r15));
        r1.add(new ThemeDescription(this.repeatButton, 0, null, null, null, r8, Theme.key_player_buttonActive));
        ActionBarMenuItem r33 = this.repeatButton;
        int r242 = Theme.key_listSelector;
        r1.add(new ThemeDescription(r33, 0, null, null, null, r8, r242));
        ActionBarMenuItem r34 = this.repeatButton;
        int r9 = Theme.key_actionBarDefaultSubmenuItem;
        r1.add(new ThemeDescription(r34, 0, null, null, null, r8, r9));
        ActionBarMenuItem r36 = this.repeatButton;
        int r92 = Theme.key_actionBarDefaultSubmenuBackground;
        r1.add(new ThemeDescription(r36, 0, null, null, null, r8, r92));
        r1.add(new ThemeDescription(this.optionsButton, 0, null, null, null, r8, r15));
        r1.add(new ThemeDescription(this.optionsButton, 0, null, null, null, r8, r242));
        r1.add(new ThemeDescription(this.optionsButton, 0, null, null, null, r8, r9));
        r1.add(new ThemeDescription(this.optionsButton, 0, null, null, null, r8, r92));
        RLottieImageView r10 = this.prevButton;
        r1.add(new ThemeDescription(r10, 0, null, new RLottieDrawable[]{r10.getAnimatedDrawable()}, "Triangle 3", r15));
        RLottieImageView r102 = this.prevButton;
        r1.add(new ThemeDescription(r102, 0, null, new RLottieDrawable[]{r102.getAnimatedDrawable()}, "Triangle 4", r15));
        RLottieImageView r103 = this.prevButton;
        r1.add(new ThemeDescription(r103, 0, null, new RLottieDrawable[]{r103.getAnimatedDrawable()}, "Rectangle 4", r15));
        r1.add(new ThemeDescription(this.prevButton, ThemeDescription.FLAG_IMAGECOLOR | ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE, null, null, null, null, r242));
        r1.add(new ThemeDescription(this.playButton, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, r15));
        r1.add(new ThemeDescription(this.playButton, ThemeDescription.FLAG_IMAGECOLOR | ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE, null, null, null, null, r242));
        RLottieImageView r104 = this.nextButton;
        r1.add(new ThemeDescription(r104, 0, null, new RLottieDrawable[]{r104.getAnimatedDrawable()}, "Triangle 3", r15));
        RLottieImageView r105 = this.nextButton;
        r1.add(new ThemeDescription(r105, 0, null, new RLottieDrawable[]{r105.getAnimatedDrawable()}, "Triangle 4", r15));
        RLottieImageView r106 = this.nextButton;
        r1.add(new ThemeDescription(r106, 0, null, new RLottieDrawable[]{r106.getAnimatedDrawable()}, "Rectangle 4", r15));
        r1.add(new ThemeDescription(this.nextButton, ThemeDescription.FLAG_IMAGECOLOR | ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE, null, null, null, null, r242));
        r1.add(new ThemeDescription(this.playerLayout, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_player_background));
        r1.add(new ThemeDescription(this.emptyImageView, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_dialogEmptyImage));
        TextView r6 = this.emptyTitleTextView;
        int r7 = ThemeDescription.FLAG_IMAGECOLOR;
        int r152 = Theme.key_dialogEmptyText;
        r1.add(new ThemeDescription(r6, r7, null, null, null, null, r152));
        r1.add(new ThemeDescription(this.emptySubtitleTextView, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, r152));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_dialogScrollGlow));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r242));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.progressView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder));
        r1.add(new ThemeDescription(this.progressView, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_progressCircle));
        r1.add(new ThemeDescription(this.durationTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r27));
        r1.add(new ThemeDescription(this.timeTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r27));
        r1.add(new ThemeDescription(this.titleTextView.getTextView(), ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.titleTextView.getNextTextView(), ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.authorTextView.getTextView(), ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r27));
        r1.add(new ThemeDescription(this.authorTextView.getNextTextView(), ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r27));
        r1.add(new ThemeDescription(this.containerView, 0, null, null, null, null, Theme.key_sheet_scrollUp));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean isTouchOutside(float r5, float r6) {
        FrameLayout r0 = this.topBulletinContainer;
        if (r0 != null) goto L5;
    L15:
        int r02 = this.containerView.getTop();
        Drawable r2 = this.shadowDrawable;
        if (r2 == null) goto L18;
        int r22 = r2.getBounds().top;
    L20:
        if (r6 >= (r02 + r22)) goto L22;
        return true;
    L22:
        if (r5 >= this.containerView.getLeft()) goto L24;
        return true;
    L24:
        if (r5 > this.containerView.getRight()) goto L30;
        return false;
    L30:
        return true;
    L18:
        r22 = 0;
        goto L20
    L5:
        if (r0.getChildCount() <= 0) goto L15;
        View r03 = this.topBulletinContainer.getChildAt(0);
        if (r6 < (this.topBulletinContainer.getY() + r03.getY())) goto L15;
        if (r6 > ((this.topBulletinContainer.getY() + r03.getY()) + r03.getHeight())) goto L15;
        if (r5 < (this.topBulletinContainer.getX() + r03.getX())) goto L15;
        if (r5 > ((this.topBulletinContainer.getX() + r03.getX()) + r03.getWidth())) goto L15;
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        ActionBar r0 = this.actionBar;
        if (r0 == null) goto L9;
        if (r0.isSearchFieldVisible() == false) goto L9;
        this.actionBar.closeSearchField();
        return;
    L9:
        if (this.blurredView.getTag() == null) goto L12;
        showAlbumCover(false, true);
        return;
    L12:
        super.onBackPressed();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean onCustomLayout(View r1, int r2, int r3, int r4, int r5) {
        FrameLayout r22 = this.blurredView;
        if (r1 != r22) goto L6;
        r22.layout(0, 0, r22.getMeasuredWidth(), this.blurredView.getMeasuredHeight());
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean onCustomMeasure(View r1, int r2, int r3) {
        if (r1 != this.blurredView) goto L6;
        int r12 = getContainer().getMeasuredWidth();
        int r22 = getContainer().getMeasuredHeight();
        this.blurredView.measure(View.MeasureSpec.makeMeasureSpec(r12, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r22, TLObject.FLAG_30));
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onFailedDownload(String r1, boolean r2) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressDownload(String r1, long r2, long r4) {
        this.progressView.setProgress(Math.min(1.0f, r2 / r4), true);
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onSuccessDownload(String r1) {
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        instance = this;
    }

    public void updateColors() {
        boolean r1 = true;
        if (this.playbackSpeedButton != null) goto L5;
    L12:
        ActionBarMenuSubItem r0 = this.castItem;
        if (r0 == null) goto L28;
        CastMediaRouteButton r2 = this.castItemButton;
        if (r2 != null) goto L17;
    L19:
        r1 = false;
    L20:
        int r22 = getThemedColor(Theme.key_actionBarDefaultSubmenuItem);
        int r3 = getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon);
        int r4 = Theme.key_featuredStickers_addButton;
        r0.setEnabledByColor(r1, r22, r3, getThemedColor(r4));
        ActionBarMenuSubItem r02 = this.castItem;
        CastMediaRouteButton r12 = this.castItemButton;
        if (r12 != null) goto L23;
    L25:
        int r6 = getThemedColor(Theme.key_listSelector);
    L26:
        r02.setSelectorColor(r6);
        return;
    L23:
        if (r12.isConnected() == false) goto L25;
        r6 = Theme.multAlpha(getThemedColor(r4), 0.1f);
        goto L26
    L17:
        if (r2.isConnected() == false) goto L19;
    L28:
        return;
    L5:
        if (equals(MediaController.getInstance().getPlaybackSpeed(true), 1.0f) == true) goto L7;
        int r03 = Theme.key_featuredStickers_addButtonPressed;
    L8:
        int r04 = getThemedColor(r03);
        SpeedIconDrawable r23 = this.speedIcon;
        if (r23 == null) goto L11;
        r23.setColor(r04);
    L11:
        this.playbackSpeedButton.setBackground(Theme.createSelectorDrawable(r04 & 436207615, 1, AndroidUtilities.dp(14.0f)));
        goto L12
    L7:
        r03 = Theme.key_inappPlayerClose;
        goto L8
    }

    public void updateCover(MessageObject r13, boolean r14) {
        CoverContainer r0 = this.coverContainer;
        if (r14 == false) goto L6;
        BackupImageView r02 = r0.getNextImageView();
    L5:
        BackupImageView r1 = r02;
        AudioInfo r03 = MediaController.getInstance().getAudioInfo();
        if (r14 == false) goto L11;
        this.coverContainer.switchImageViews();
    L11:
        if (r03 != null) goto L13;
    L16:
        this.currentFile = FileLoader.getAttachFileName(r13.getDocument());
        this.currentAudioFinishedLoading = false;
        String r04 = r13.getArtworkUrl(false);
        ImageLocation r4 = getArtworkThumbImageLocation(r13);
        if (TextUtils.isEmpty(r04) == true) goto L20;
        r1.setImage(ImageLocation.getForPath(r04), null, r4, null, null, 0, 1, r13);
        this.noCover = false;
    L23:
        r1.invalidate();
        return;
    L20:
        if (r4 == null) goto L22;
        r1.setImage(null, null, r4, null, null, 0, 1, r13);
        this.noCover = false;
        goto L23
    L22:
        r1.setImageResource(R.drawable.nocover, Theme.getColor(Theme.key_player_button));
        this.noCover = true;
        goto L23
    L13:
        if (r03.getCover() == null) goto L16;
        r1.setImageBitmap(r03.getCover());
        this.currentFile = null;
        this.currentAudioFinishedLoading = true;
        this.noCover = false;
        return;
    L6:
        r02 = r0.getImageView();
        goto L5
    }

    public void updateRepeatButton() {
        int r0 = SharedConfig.repeatMode;
        if (r0 == 0) goto L11;
        if (r0 == 1) goto L11;
        if (r0 != 2) goto L40;
        this.repeatButton.setIcon(R.drawable.player_new_repeatone);
        ActionBarMenuItem r02 = this.repeatButton;
        int r3 = Theme.key_player_buttonActive;
        r02.setTag(Integer.valueOf(r3));
        this.repeatButton.setIconColor(getThemedColor(r3));
        Theme.setSelectorDrawableColor(this.repeatButton.getBackground(), 436207615 & getThemedColor(r3), true);
        this.repeatButton.setContentDescription(LocaleController.getString(R.string.AccDescrRepeatOne));
        return;
    L40:
        return;
    L11:
        if (SharedConfig.shuffleMusic == false) goto L17;
        ActionBarMenuItem r32 = this.repeatButton;
        if (r0 != 0) goto L15;
        r32.setIcon(R.drawable.player_new_shuffle);
    L23:
        if (r0 == 0) goto L25;
    L30:
        ActionBarMenuItem r33 = this.repeatButton;
        int r4 = Theme.key_player_buttonActive;
        r33.setTag(Integer.valueOf(r4));
        this.repeatButton.setIconColor(getThemedColor(r4));
        Theme.setSelectorDrawableColor(this.repeatButton.getBackground(), 436207615 & getThemedColor(r4), true);
        if (r0 != 0) goto L38;
        boolean r03 = SharedConfig.shuffleMusic;
        ActionBarMenuItem r6 = this.repeatButton;
        if (r03 == false) goto L36;
        r6.setContentDescription(LocaleController.getString(R.string.ShuffleList));
        return;
    L36:
        r6.setContentDescription(LocaleController.getString(R.string.ReverseOrder));
        return;
    L38:
        this.repeatButton.setContentDescription(LocaleController.getString(R.string.AccDescrRepeatList));
        return;
    L25:
        if (SharedConfig.shuffleMusic == true) goto L30;
        if (SharedConfig.playOrderReversed == true) goto L30;
        ActionBarMenuItem r04 = this.repeatButton;
        int r1 = Theme.key_player_button;
        r04.setTag(Integer.valueOf(r1));
        this.repeatButton.setIconColor(getThemedColor(r1));
        Theme.setSelectorDrawableColor(this.repeatButton.getBackground(), getThemedColor(Theme.key_listSelector), true);
        this.repeatButton.setContentDescription(LocaleController.getString(R.string.AccDescrRepeatOff));
        return;
    L15:
        r32.setIcon(R.drawable.player_new_repeat_shuffle);
        goto L23
    L17:
        if (SharedConfig.playOrderReversed == false) goto L22;
        ActionBarMenuItem r34 = this.repeatButton;
        if (r0 != 0) goto L21;
        r34.setIcon(R.drawable.player_new_order);
        goto L23
    L21:
        r34.setIcon(R.drawable.player_new_repeat_reverse);
        goto L23
    L22:
        this.repeatButton.setIcon(R.drawable.player_new_repeatall);
        goto L23
    }
}
