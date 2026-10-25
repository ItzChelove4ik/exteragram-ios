package com.exteragram.messenger.nowplaying.ui.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.media3.common.MediaItem;
import androidx.media3.common.audio.AudioFocusRequestCompat$$ExternalSyntheticApiModelOutline0;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.api.dto.NowPlayingDTO;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.components.SupporterBottomSheet;
import com.exteragram.messenger.nowplaying.ServiceEmoji;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.utils.ui.UIUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PlayPauseDrawable;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.LaunchActivity;
import org.webrtc.MediaStreamTrack;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\b\u0017\u0018\u0000 72\u00020\u0001:\u00017B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010&\u001a\u00020'H\u0002J\u000e\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0010J\b\u0010+\u001a\u00020)H\u0002J\b\u0010,\u001a\u00020)H\u0002J\b\u0010-\u001a\u00020\u0016H\u0002J\b\u0010.\u001a\u00020)H\u0002J\b\u0010/\u001a\u00020)H\u0002J\b\u00100\u001a\u00020)H\u0002J\u0010\u00101\u001a\u0002022\u0006\u00103\u001a\u000202H\u0002J\b\u00104\u001a\u00020)H\u0014J\b\u00105\u001a\u00020)H\u0014J\b\u00106\u001a\u00020)H\u0014R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "resourcesProvider", "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;", "<init>", "(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V", "imageView", "Lorg/telegram/ui/Components/BackupImageView;", "nameView", "Lorg/telegram/ui/ActionBar/SimpleTextView;", "artistView", "albumView", "cardLayout", "nowPlayingCardData", "Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;", "playPauseButton", "Landroid/widget/ImageView;", "playPauseDrawable", "Lorg/telegram/ui/Components/PlayPauseDrawable;", "isPlaying", _UrlKt.FRAGMENT_ENCODE_SET, "player", "Landroidx/media3/exoplayer/ExoPlayer;", "audioManager", "Landroid/media/AudioManager;", "audioFocusRequest", "Landroid/media/AudioFocusRequest;", "resumeOnFocusGain", "audioFocusChangeListener", "Landroid/media/AudioManager$OnAudioFocusChangeListener;", "emoji", "Lorg/telegram/ui/Components/AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable;", "currentDocId", _UrlKt.FRAGMENT_ENCODE_SET, "currentPreviewUrl", _UrlKt.FRAGMENT_ENCODE_SET, "getCoverCornerRadius", _UrlKt.FRAGMENT_ENCODE_SET, "set", _UrlKt.FRAGMENT_ENCODE_SET, "cardData", "initializePlayer", "togglePlayPause", "requestAudioFocus", "abandonAudioFocus", "updatePlayPauseButton", "releasePlayer", "getThemedColor", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "onAttachedToWindow", "onDetachedFromWindow", "onSavedMusicClick", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SuppressLint({"ViewConstructor"})
@SourceDebugExtension({"SMAP\nNowPlayingCard.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NowPlayingCard.kt\ncom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard\n+ 2 View.kt\nandroidx/core/view/ViewKt\n+ 3 BitmapDrawable.kt\nandroidx/core/graphics/drawable/BitmapDrawableKt\n+ 4 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,473:1\n257#2,2:474\n255#2:476\n257#2,2:477\n27#3:479\n29#4:480\n*S KotlinDebug\n*F\n+ 1 NowPlayingCard.kt\ncom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard\n*L\n261#1:474,2\n263#1:476\n290#1:477,2\n329#1:479\n370#1:480\n*E\n"})
/* loaded from: classes4.dex */
public abstract class NowPlayingCard extends FrameLayout {
    private static final Companion Companion = null;
    private final SimpleTextView albumView;
    private final SimpleTextView artistView;
    private final AudioManager.OnAudioFocusChangeListener audioFocusChangeListener;
    private AudioFocusRequest audioFocusRequest;
    private final AudioManager audioManager;
    private final FrameLayout cardLayout;
    private long currentDocId;
    private String currentPreviewUrl;
    private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable emoji;
    private final BackupImageView imageView;
    private boolean isPlaying;
    private final SimpleTextView nameView;
    private NowPlayingCardData nowPlayingCardData;
    private final ImageView playPauseButton;
    private PlayPauseDrawable playPauseDrawable;
    private ExoPlayer player;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean resumeOnFocusGain;

    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$1", "Landroid/widget/FrameLayout;", "dispatchDraw", _UrlKt.FRAGMENT_ENCODE_SET, "canvas", "Landroid/graphics/Canvas;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.nowplaying.ui.components.NowPlayingCard$1, reason: invalid class name */
    public static final class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ NowPlayingCard this$0;

        public AnonymousClass1(Context r1, NowPlayingCard r2) {
            this.this$0 = r2;
            super(r1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r10) {
            if (NowPlayingCard.access$getNowPlayingCardData$p(this.this$0) == null) goto L24;
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = NowPlayingCard.access$getEmoji$p(this.this$0);
            NowPlayingCardData r1 = NowPlayingCard.access$getNowPlayingCardData$p(this.this$0);
            NowPlayingCardData r2 = null;
            if (r1 != null) goto L7;
            r1 = null;
        L7:
            Integer r12 = r1.getAccentColor();
            NowPlayingCard r3 = this.this$0;
            if (r12 != null) goto L10;
            Integer r13 = Integer.valueOf(NowPlayingCard.access$getThemedColor(r3, Theme.key_windowBackgroundWhiteBlackText));
        L14:
            r0.setColor(r13);
            UIUtil r32 = UIUtil.INSTANCE;
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r5 = NowPlayingCard.access$getEmoji$p(this.this$0);
            float r6 = getWidth();
            float r7 = getHeight();
            NowPlayingCardData r02 = NowPlayingCard.access$getNowPlayingCardData$p(this.this$0);
            if (r02 == null) goto L19;
            r2 = r02;
        L19:
            if (r2.getCoverBitmap() != null) goto L22;
            float r03 = 0.4f;
        L21:
            Canvas r4 = r10;
            r32.drawNowPlayingPattern(r4, r5, r6, r7, r03);
        L25:
            super.dispatchDraw(r4);
            return;
        L22:
            r03 = 1.0f;
            goto L21
        L10:
            NowPlayingCardData r14 = NowPlayingCard.access$getNowPlayingCardData$p(r3);
            if (r14 != null) goto L13;
            r14 = null;
        L13:
            r13 = r14.getAccentColor();
            goto L14
        L24:
            r4 = r10;
            goto L25
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "CARD_CONTENT_PADDING_DP", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    public static boolean $r8$lambda$1BAf14SAIk8s4HkHdGaX6glplHA(NowPlayingDTO r0, NowPlayingCard r1, FrameLayout r2, NowPlayingCardData r3, View r4) {
        if (Intrinsics.areEqual(r0.getPlatform(), "TELEGRAM") == false) goto L5;
        r1.onSavedMusicClick();
        return true;
    L5:
        Browser.openUrl(r2.getContext(), r3.getNowPlayingDTO().getSongUrl());
        return true;
    }

    public static void $r8$lambda$FzMm4OZnNI9aFq2UwuvuSO_aGjo(NowPlayingCard r0, View r1) {
        r0.togglePlayPause();
    }

    public static void $r8$lambda$Hrhdg_VFnUTPkcpH4kxy4QConTU(NowPlayingDTO r0, NowPlayingCard r1, boolean r2, FrameLayout r3, NowPlayingCardData r4, View r5) {
        if (Intrinsics.areEqual(r0.getPlatform(), "TELEGRAM") == false) goto L6;
        r1.onSavedMusicClick();
        return;
    L6:
        BaseFragment r02 = LaunchActivity.getSafeLastFragment();
        if (r2 == false) goto L10;
        Browser.openUrl(r3.getContext(), r4.getNowPlayingDTO().getSongUrl());
        return;
    L10:
        if (r02 == null) goto L13;
        SupporterBottomSheet.showAlert(r02);
        return;
    }

    /* renamed from: $r8$lambda$s9QIjAj9D55Esk_1M7EGE-0vK2g, reason: not valid java name */
    public static void m1508$r8$lambda$s9QIjAj9D55Esk_1M7EGE0vK2g(NowPlayingCard r3, int r4) {
        if (r4 != (-3)) goto L5;
    L21:
        ExoPlayer r42 = r3.player;
        if (r42 != null) goto L24;
        return;
    L24:
        if (r42.isPlaying() != true) goto L32;
        r3.resumeOnFocusGain = true;
        ExoPlayer r32 = r3.player;
        if (r32 == null) goto L33;
        r32.pause();
        return;
    L33:
        return;
    L32:
        return;
    L5:
        if (r4 == (-2)) goto L21;
        if (r4 == (-1)) goto L17;
        if (r4 == 1) goto L11;
        return;
    L11:
        if (r3.resumeOnFocusGain == false) goto L29;
        ExoPlayer r43 = r3.player;
        if (r43 == null) goto L15;
        r43.play();
    L15:
        r3.resumeOnFocusGain = false;
        return;
    L29:
        return;
    L17:
        r3.resumeOnFocusGain = false;
        ExoPlayer r33 = r3.player;
        if (r33 == null) goto L30;
        r33.pause();
        return;
    }

    static {
        Companion = new Companion(null);
    }

    public NowPlayingCard(Context r18, Theme.ResourcesProvider r19) {
        super(r18);
        this.resourcesProvider = r19;
        this.audioManager = (AudioManager) r18.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        this.audioFocusChangeListener = new NowPlayingCard$$ExternalSyntheticLambda2(this);
        this.currentDocId = -1;
        setClickable(false);
        setWillNotDraw(false);
        AnonymousClass1 r3 = new AnonymousClass1(r18, this);
        NowPlayingCard$2$drawable$1 r4 = new NowPlayingCard$2$drawable$1();
        r4.setCornerRadius(AndroidUtilities.dpf2(ExteraConfig.getSectionRadiusDp()));
        r3.setBackground(r4);
        r3.setClipToOutline(true);
        r3.setOutlineProvider(ViewOutlineProviderImpl.fromDrawable(r4));
        r3.setClickable(true);
        ScaleStateListAnimator.apply(r3, 0.035f, 1.5f);
        this.cardLayout = r3;
        addView(r3, LayoutHelper.createFrame(-1, -2.0f));
        this.emoji = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(r3, false, AndroidUtilities.dp(20.0f), 13);
        LinearLayout r42 = new LinearLayout(r18);
        r42.setOrientation(0);
        r3.addView(r42, LayoutHelper.createLinear(-1, -2, 119, 12, 12, 12, 12));
        BackupImageView r32 = new BackupImageView(r18);
        r32.setClipToOutline(true);
        r32.setOutlineProvider(ViewOutlineProviderImpl.boundsWithRoundRect(getCoverCornerRadius()));
        this.imageView = r32;
        r42.addView(r32, LayoutHelper.createLinear(64, 64, 19, 0, 0, 12, 0));
        LinearLayout r33 = new LinearLayout(r18);
        r33.setOrientation(1);
        r42.addView(r33, LayoutHelper.createLinear(0, -2, 1.0f, 16));
        SimpleTextView r5 = new SimpleTextView(r18);
        r5.setGravity(19);
        r5.setTextColor(-1);
        r5.setTextSize(16);
        r5.setTypeface(AndroidUtilities.bold());
        NotificationCenter.listenEmojiLoading(r5);
        this.nameView = r5;
        r33.addView(r5, LayoutHelper.createLinear(-1, 22));
        SimpleTextView r52 = new SimpleTextView(r18);
        r52.setGravity(19);
        r52.setTypeface(AndroidUtilities.regular());
        r52.setTextSize(14);
        r52.setTextColor(-1);
        r52.setAlpha(0.6f);
        NotificationCenter.listenEmojiLoading(r52);
        this.artistView = r52;
        r33.addView(r52, LayoutHelper.createLinear(-1, 19, 0.0f, 2.0f, 0.0f, 0.0f));
        SimpleTextView r53 = new SimpleTextView(r18);
        r53.setGravity(19);
        r53.setTypeface(AndroidUtilities.regular());
        r53.setTextSize(14);
        r53.setTextColor(-1);
        r53.setAlpha(0.6f);
        NotificationCenter.listenEmojiLoading(r53);
        this.albumView = r53;
        r33.addView(r53, LayoutHelper.createLinear(-1, 19, 0.0f, 2.0f, 0.0f, 0.0f));
        PlayPauseDrawable r34 = new PlayPauseDrawable(16);
        r34.setPause(false);
        r34.setColor(-1);
        this.playPauseDrawable = r34;
        ImageView r2 = new ImageView(r18);
        ScaleStateListAnimator.apply(r2);
        r2.setScaleType(ImageView.ScaleType.CENTER);
        r2.setImageDrawable(this.playPauseDrawable);
        r2.setOnClickListener(new NowPlayingCard$$ExternalSyntheticLambda3(this));
        this.playPauseButton = r2;
        r42.addView(r2, LayoutHelper.createLinear(32, 32, 16, 8, 0, 8, 0));
    }

    private final void abandonAudioFocus() {
        if (Build.VERSION.SDK_INT < 26) goto L8;
        AudioFocusRequest r0 = this.audioFocusRequest;
        if (r0 == null) goto L10;
        this.audioManager.abandonAudioFocusRequest(r0);
        this.audioFocusRequest = null;
        return;
    L10:
        return;
    L8:
        this.audioManager.abandonAudioFocus(this.audioFocusChangeListener);
    }

    public static final /* synthetic */ void access$abandonAudioFocus(NowPlayingCard r0) {
        r0.abandonAudioFocus();
    }

    public static final /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable access$getEmoji$p(NowPlayingCard r0) {
        return r0.emoji;
    }

    public static final /* synthetic */ NowPlayingCardData access$getNowPlayingCardData$p(NowPlayingCard r0) {
        return r0.nowPlayingCardData;
    }

    public static final /* synthetic */ int access$getThemedColor(NowPlayingCard r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static final /* synthetic */ void access$setPlaying$p(NowPlayingCard r0, boolean r1) {
        r0.isPlaying = r1;
    }

    public static final /* synthetic */ void access$updatePlayPauseButton(NowPlayingCard r0) {
        r0.updatePlayPauseButton();
    }

    private final float getCoverCornerRadius() {
        return AndroidUtilities.dpf2(RangesKt.coerceAtLeast(ExteraConfig.getSectionRadiusDp() - 12, 8));
    }

    private final int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private final void initializePlayer() {
        releasePlayer();
        NowPlayingCardData r0 = this.nowPlayingCardData;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        String r02 = r0.getNowPlayingDTO().getPreviewUrl();
        if (r02 != null) goto L8;
        return;
    L8:
        ExoPlayer r1 = new ExoPlayer.Builder(getContext()).build();
        r1.setMediaSource(new ProgressiveMediaSource.Factory(new DefaultDataSource.Factory(getContext())).createMediaSource(MediaItem.fromUri(Uri.parse(r02))));
        r1.prepare();
        r1.addListener(new NowPlayingCard$initializePlayer$1$1(this));
        this.player = r1;
    }

    private final void releasePlayer() {
        ExoPlayer r0 = this.player;
        if (r0 == null) goto L5;
        r0.release();
    L5:
        this.player = null;
        this.isPlaying = false;
        updatePlayPauseButton();
        abandonAudioFocus();
    }

    private final boolean requestAudioFocus() {
        if (Build.VERSION.SDK_INT < 26) goto L9;
        AudioAttributes r0 = new AudioAttributes.Builder().setUsage(1).setContentType(2).build();
        AudioFocusRequest r02 = AudioFocusRequestCompat$$ExternalSyntheticApiModelOutline0.m(2).setAudioAttributes(r0).setOnAudioFocusChangeListener(this.audioFocusChangeListener).build();
        this.audioFocusRequest = r02;
        if (this.audioManager.requestAudioFocus(r02) != 1) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (this.audioManager.requestAudioFocus(this.audioFocusChangeListener, 3, 2) != 1) goto L11;
        return true;
    L11:
        return false;
    }

    private final void togglePlayPause() {
        ExoPlayer r0 = this.player;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.isPlaying() == false) goto L10;
        r0.pause();
        abandonAudioFocus();
        return;
    L10:
        if (requestAudioFocus() == true) goto L12;
        return;
    L12:
        if (r0.getPlaybackState() != 4) goto L14;
        r0.seekTo(0);
    L14:
        r0.play();
    }

    private final void updatePlayPauseButton() {
        this.playPauseDrawable.setPause(this.isPlaying);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.emoji.attach();
        if (this.player == null) goto L5;
        return;
    L5:
        if (this.nowPlayingCardData == null) goto L9;
        initializePlayer();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.emoji.detach();
        releasePlayer();
    }

    public abstract void onSavedMusicClick();

    public final void set(NowPlayingCardData r17) {
        this.nowPlayingCardData = r17;
        NowPlayingDTO r1 = r17.getNowPlayingDTO();
        List<String> r0 = r1.getArtists();
        BitmapDrawable r6 = null;
        this.artistView.setText(null);
        this.nameView.setText(null);
        this.albumView.setText(null);
        List<String> r3 = r0;
        if (r3 != null) goto L5;
    L8:
        this.artistView.setText(LocaleController.getString(R.string.AudioUnknownArtist));
    L9:
        int r7 = 0;
        this.nameView.setText(Emoji.replaceEmoji(r1.getTrackName(), this.nameView.getPaint().getFontMetricsInt(), false));
        SimpleTextView r02 = this.albumView;
        String r32 = r1.getAlbumName();
        if (r32 != null) goto L12;
    L17:
        boolean r33 = false;
    L19:
        if (r33 == false) goto L21;
        int r34 = 0;
    L22:
        r02.setVisibility(r34);
        if (this.albumView.getVisibility() != 0) goto L25;
        this.albumView.setText(Emoji.replaceEmoji(r1.getAlbumName(), this.albumView.getPaint().getFontMetricsInt(), false));
    L25:
        setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        Drawable r03 = this.cardLayout.getBackground();
        if ((r03 instanceof GradientDrawable) == false) goto L37;
        GradientDrawable r04 = (GradientDrawable) r03;
        r04.mutate();
        r04.setDither(true);
        r04.setGradientType(1);
        r04.setGradientCenter(1.0f, 0.5f);
        Integer r35 = r17.getBackgroundColor();
        if (r35 == null) goto L31;
        int r36 = r35.intValue();
    L30:
        int r10 = r36;
        if (r17.getBackgroundColor() == null) goto L35;
        int r37 = UIUtil.adjustHsl$default(UIUtil.INSTANCE, r10, 1.5f, 0.0f, 4, null);
    L36:
        r04.setColors(new int[]{r10, r37});
        goto L37
    L35:
        r37 = r10;
        goto L36
    L31:
        r36 = getThemedColor(Theme.key_windowBackgroundWhite);
    L37:
        ImageView r05 = this.playPauseButton;
        String r38 = r1.getPreviewUrl();
        if (r38 != null) goto L40;
    L45:
        boolean r39 = false;
    L46:
        if (r39 == true) goto L49;
        r7 = 8;
    L49:
        r05.setVisibility(r7);
        ImageView r06 = this.playPauseButton;
        int r310 = AndroidUtilities.dp(32.0f);
        NowPlayingCardData r72 = this.nowPlayingCardData;
        if (r72 != null) goto L52;
        r72 = null;
    L52:
        Integer r73 = r72.getAccentColor();
        if (r73 == null) goto L55;
        int r74 = r73.intValue();
    L56:
        r06.setBackground(Theme.createCircleDrawable(r310, r74));
        if (r17.getUserEmoji() > 0) goto L59;
    L61:
        long r75 = ServiceEmoji.Companion.fromString(r1.getPlatform()).getDocumentId();
    L63:
        if (r75 == this.currentDocId) goto L65;
        this.currentDocId = r75;
        this.emoji.set(r75, true);
    L65:
        FrameLayout r07 = this.cardLayout;
        r07.setOnClickListener(new NowPlayingCard$$ExternalSyntheticLambda0(r1, this, BadgesController.hasBadge$default(BadgesController.INSTANCE, null, 1, null), r07, r17));
        r07.setOnLongClickListener(new NowPlayingCard$$ExternalSyntheticLambda1(r1, this, r07, r17));
        if (r17.getImageLocation() == null) goto L74;
        Bitmap r08 = r17.getCoverBitmap();
        if (r08 == null) goto L70;
        r6 = new BitmapDrawable(getContext().getResources(), r08);
    L70:
        this.imageView.setImage(r17.getImageLocation(), null, r6, 0, null);
        if (r17.getCoverBitmap() == null) goto L73;
        this.artistView.setTextColor(-1);
        this.nameView.setTextColor(-1);
        this.albumView.setTextColor(-1);
    L75:
        String r09 = r17.getNowPlayingDTO().getPreviewUrl();
        if (Intrinsics.areEqual(r09, this.currentPreviewUrl) == true) goto L78;
        this.currentPreviewUrl = r09;
        initializePlayer();
    L78:
        invalidate();
        return;
    L73:
        int r010 = getThemedColor(Theme.key_windowBackgroundWhiteBlackText);
        this.artistView.setTextColor(r010);
        this.nameView.setTextColor(r010);
        this.albumView.setTextColor(r010);
        goto L75
    L74:
        this.imageView.setImageResource(R.drawable.nocover, getThemedColor(Theme.key_player_button));
        int r011 = getThemedColor(Theme.key_windowBackgroundWhiteBlackText);
        this.artistView.setTextColor(r011);
        this.nameView.setTextColor(r011);
        this.albumView.setTextColor(r011);
        goto L75
    L59:
        if (Intrinsics.areEqual(r1.getPlatform(), "TELEGRAM") == false) goto L61;
        r75 = r17.getUserEmoji();
        goto L63
    L55:
        r74 = getThemedColor(Theme.key_featuredStickers_addButton);
        goto L56
    L40:
        if (r38.length() == 0) goto L45;
        if (Intrinsics.areEqual(r1.getPlatform(), "TELEGRAM") == true) goto L45;
        r39 = true;
        goto L46
    L21:
        r34 = 8;
        goto L22
    L12:
        if (r32.length() == 0) goto L17;
        if (Intrinsics.areEqual(r1.getTrackName(), r1.getAlbumName()) == true) goto L17;
        r33 = true;
        goto L19
    L5:
        if (r3.isEmpty() == true) goto L8;
        this.artistView.setText(CollectionsKt.joinToString$default(r0, ", ", null, null, 0, null, null, 62, null));
        goto L9
    }
}
