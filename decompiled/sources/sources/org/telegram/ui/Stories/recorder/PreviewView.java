package org.telegram.ui.Stories.recorder;

import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.google.zxing.common.detector.MathUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatThemeController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.wallpaper.WallpaperBitmapHolder;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.EmojiThemes;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.theme.ThemeKey;
import org.telegram.ui.ChatBackgroundDrawable;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.Paint.Views.RoundView;
import org.telegram.ui.Components.PhotoFilterView;
import org.telegram.ui.Components.VideoEditTextureView;
import org.telegram.ui.Components.VideoPlayer;
import org.telegram.ui.Stories.recorder.StoryEntry;
import org.telegram.ui.Stories.recorder.TimelineView;

/* loaded from: classes7.dex */
public abstract class PreviewView extends FrameLayout {
    private boolean allowCropping;
    private boolean allowRotation;
    private boolean allowWithSingleTouch;
    private float angle;
    private VideoPlayer audioPlayer;
    private Bitmap bitmap;
    private final Rect bitmapDst;
    private final Paint bitmapPaint;
    private final Rect bitmapSrc;
    private final BlurringShader.BlurManager blurManager;
    private CollageLayoutView2 collage;
    private CropEditor cropEditorDrawing;
    private float cx;
    private float cy;
    private boolean doNotSpanRotation;
    private boolean draw;
    public boolean drawForThemeToggle;
    private StoryEntry entry;
    public TextureView filterTextureView;
    private Matrix finalMatrix;
    private long finalSeekPosition;
    private int gradientBottom;
    private final Paint gradientPaint;
    private int gradientTop;
    private float h;
    public Runnable invalidateBlur;
    private final Matrix invertMatrix;
    public boolean isMuted;
    private long lastPos;
    private final PointF lastTouch;
    private float lastTouchDistance;
    private double lastTouchRotation;
    private Drawable lastWallpaperDrawable;
    private final Matrix matrix;
    private boolean moving;
    private boolean multitouch;
    private Runnable onErrorListener;
    private Runnable onTap;
    private final HashSet<Integer> pauseLinks;
    private PhotoFilterView photoFilterView;
    private float rotationDiff;
    private VideoPlayer roundPlayer;
    private int roundPlayerHeight;
    private int roundPlayerWidth;
    private RoundView roundView;
    private long seekedLastTime;
    private Runnable slowerSeek;
    private boolean slowerSeekScheduled;
    private final Paint snapPaint;
    private boolean snappedRotation;
    private boolean snappedToCenterAndScaled;
    private long tapTime;
    private VideoEditTextureView textureView;
    private final TextureViewHolder textureViewHolder;
    private final AnimatedFloat thumbAlpha;
    private Bitmap thumbBitmap;
    private TimelineView timelineView;
    private final PointF touch;
    private Matrix touchMatrix;
    private final Matrix transformBackMatrix;
    private final Matrix transformMatrix;
    private final Runnable updateAudioProgressRunnable;
    private final Runnable updateProgressRunnable;
    private final Runnable updateRoundProgressRunnable;
    private final float[] vertices;
    private int videoHeight;
    private VideoPlayer videoPlayer;
    private int videoWidth;
    private float w;
    private Drawable wallpaperDrawable;
    private AnimatedFloat wallpaperDrawableCrossfade;

    /* renamed from: org.telegram.ui.Stories.recorder.PreviewView$1, reason: invalid class name */
    public class AnonymousClass1 implements VideoPlayer.VideoPlayerDelegate {
        final /* synthetic */ PreviewView this$0;

        public AnonymousClass1(PreviewView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onError(VideoPlayer r1, Exception r2) {
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onRenderedFirstFrame() {
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onStateChanged(boolean r1, int r2) {
            AndroidUtilities.cancelRunOnUIThread(PreviewView.m20162$$Nest$fgetupdateAudioProgressRunnable(this.this$0));
            if (PreviewView.m20153$$Nest$fgetaudioPlayer(this.this$0) != null) goto L5;
            return;
        L5:
            if (PreviewView.m20153$$Nest$fgetaudioPlayer(this.this$0).isPlaying() == false) goto L9;
            AndroidUtilities.runOnUIThread(PreviewView.m20162$$Nest$fgetupdateAudioProgressRunnable(this.this$0));
            return;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onSurfaceTextureUpdated(SurfaceTexture r1) {
            this.this$0.invalidateTextureViewHolder();
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onVideoSizeChanged(int r1, int r2, int r3, float r4) {
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PreviewView$2, reason: invalid class name */
    public class AnonymousClass2 implements TimelineView.TimelineDelegate {
        final /* synthetic */ PreviewView this$0;

        public AnonymousClass2(PreviewView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onAudioLeftChange(float r2) {
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0) != null) goto L5;
            return;
        L5:
            PreviewView.m20156$$Nest$fgetentry(this.this$0).audioLeft = r2;
            PreviewView.m20156$$Nest$fgetentry(this.this$0).editedMedia = true;
            this.this$0.updateAudioPlayer(true);
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onAudioOffsetChange(long r2) {
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0) != null) goto L5;
            return;
        L5:
            PreviewView.m20156$$Nest$fgetentry(this.this$0).audioOffset = r2;
            PreviewView.m20156$$Nest$fgetentry(this.this$0).editedMedia = true;
            this.this$0.updateAudioPlayer(true);
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onAudioRemove() {
            this.this$0.setupAudio(null, true);
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onAudioRightChange(float r2) {
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0) != null) goto L5;
            return;
        L5:
            PreviewView.m20156$$Nest$fgetentry(this.this$0).audioRight = r2;
            PreviewView.m20156$$Nest$fgetentry(this.this$0).editedMedia = true;
            this.this$0.updateAudioPlayer(true);
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onAudioVolumeChange(float r2) {
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0) != null) goto L5;
            return;
        L5:
            PreviewView.m20156$$Nest$fgetentry(this.this$0).audioVolume = r2;
            PreviewView.m20156$$Nest$fgetentry(this.this$0).editedMedia = true;
            this.this$0.checkVolumes();
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onProgressChange(long r3, boolean r5) {
            PreviewView r0 = this.this$0;
            if (r5 == true) goto L6;
            PreviewView.m20173$$Nest$mseekTo(r0, r3);
            return;
        L6:
            VideoPlayer r52 = PreviewView.m20166$$Nest$fgetvideoPlayer(r0);
            PreviewView r02 = this.this$0;
            if (r52 == null) goto L10;
            PreviewView.m20166$$Nest$fgetvideoPlayer(r02).seekTo(r3, true);
            return;
        L10:
            boolean r53 = r02.isCollage();
            PreviewView r03 = this.this$0;
            if (r53 == false) goto L15;
            PreviewView.m20155$$Nest$fgetcollage(r03).seekTo(r3, true);
            return;
        L15:
            if (PreviewView.m20153$$Nest$fgetaudioPlayer(r03) == null) goto L18;
            PreviewView.m20153$$Nest$fgetaudioPlayer(this.this$0).seekTo(r3, false);
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onProgressDragChange(boolean r2) {
            if (this.this$0.isCollage() == false) goto L5;
            PreviewView.m20155$$Nest$fgetcollage(this.this$0).forceNotRestorePosition();
        L5:
            this.this$0.updatePauseReason(-4, r2);
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onRoundLeftChange(float r2) {
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0) != null) goto L5;
            return;
        L5:
            PreviewView.m20156$$Nest$fgetentry(this.this$0).roundLeft = r2;
            PreviewView.m20156$$Nest$fgetentry(this.this$0).editedMedia = true;
            this.this$0.updateRoundPlayer(true);
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onRoundOffsetChange(long r2) {
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0) != null) goto L5;
            return;
        L5:
            PreviewView.m20156$$Nest$fgetentry(this.this$0).roundOffset = r2;
            PreviewView.m20156$$Nest$fgetentry(this.this$0).editedMedia = true;
            this.this$0.updateRoundPlayer(true);
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onRoundRemove() {
            this.this$0.setupRound(null, null, true);
            this.this$0.onRoundRemove();
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onRoundRightChange(float r2) {
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0) != null) goto L5;
            return;
        L5:
            PreviewView.m20156$$Nest$fgetentry(this.this$0).roundRight = r2;
            PreviewView.m20156$$Nest$fgetentry(this.this$0).editedMedia = true;
            this.this$0.updateRoundPlayer(true);
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onRoundSelectChange(boolean r1) {
            this.this$0.onRoundSelectChange(r1);
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onRoundVolumeChange(float r2) {
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0) != null) goto L5;
            return;
        L5:
            PreviewView.m20156$$Nest$fgetentry(this.this$0).roundVolume = r2;
            PreviewView.m20156$$Nest$fgetentry(this.this$0).editedMedia = true;
            this.this$0.checkVolumes();
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onVideoLeftChange(int r2, float r3) {
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0) != null) goto L5;
            return;
        L5:
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0).collageContent == null) goto L12;
            if (r2 >= 0) goto L8;
            return;
        L8:
            if (r2 >= PreviewView.m20156$$Nest$fgetentry(this.this$0).collageContent.size()) goto L14;
            PreviewView.m20156$$Nest$fgetentry(this.this$0).collageContent.get(r2).videoLeft = r3;
            return;
        L14:
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onVideoLeftChange(boolean r5, float r6) {
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0) == null) goto L13;
            PreviewView.m20156$$Nest$fgetentry(this.this$0).left = r6;
            PreviewView.m20156$$Nest$fgetentry(this.this$0).editedMedia = true;
            if (PreviewView.m20166$$Nest$fgetvideoPlayer(this.this$0) != null) goto L8;
            return;
        L8:
            if (PreviewView.m20166$$Nest$fgetvideoPlayer(this.this$0).getDuration() == (-9223372036854775807L)) goto L12;
            PreviewView.m20173$$Nest$mseekTo(this.this$0, (long) (r6 * PreviewView.m20166$$Nest$fgetvideoPlayer(r4).getDuration()));
            return;
        L12:
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onVideoOffsetChange(int r2, long r3) {
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0) != null) goto L5;
            return;
        L5:
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0).collageContent == null) goto L12;
            if (r2 >= 0) goto L8;
            return;
        L8:
            if (r2 >= PreviewView.m20156$$Nest$fgetentry(this.this$0).collageContent.size()) goto L14;
            PreviewView.m20156$$Nest$fgetentry(this.this$0).collageContent.get(r2).videoOffset = r3;
            return;
        L14:
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onVideoRightChange(int r2, float r3) {
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0) != null) goto L5;
            return;
        L5:
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0).collageContent == null) goto L12;
            if (r2 >= 0) goto L8;
            return;
        L8:
            if (r2 >= PreviewView.m20156$$Nest$fgetentry(this.this$0).collageContent.size()) goto L14;
            PreviewView.m20156$$Nest$fgetentry(this.this$0).collageContent.get(r2).videoRight = r3;
            return;
        L14:
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onVideoRightChange(boolean r1, float r2) {
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0) != null) goto L5;
            return;
        L5:
            PreviewView.m20156$$Nest$fgetentry(this.this$0).right = r2;
            PreviewView.m20156$$Nest$fgetentry(this.this$0).editedMedia = true;
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onVideoSelected(int r2) {
            if (PreviewView.m20155$$Nest$fgetcollage(this.this$0) == null) goto L6;
            PreviewView.m20155$$Nest$fgetcollage(this.this$0).highlight(r2);
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onVideoVolumeChange(float r2) {
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0) != null) goto L5;
            return;
        L5:
            PreviewView.m20156$$Nest$fgetentry(this.this$0).videoVolume = r2;
            this.this$0.checkVolumes();
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onVideoVolumeChange(int r2, float r3) {
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0) != null) goto L5;
            return;
        L5:
            if (PreviewView.m20156$$Nest$fgetentry(this.this$0).collageContent == null) goto L12;
            if (r2 >= 0) goto L8;
            return;
        L8:
            if (r2 >= PreviewView.m20156$$Nest$fgetentry(this.this$0).collageContent.size()) goto L14;
            PreviewView.m20156$$Nest$fgetentry(this.this$0).collageContent.get(r2).videoVolume = r3;
            return;
        L14:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PreviewView$3, reason: invalid class name */
    public class AnonymousClass3 implements VideoPlayer.VideoPlayerDelegate {
        final /* synthetic */ PreviewView this$0;
        final /* synthetic */ StoryEntry val$entry;
        final /* synthetic */ Runnable[] val$whenReadyFinal;

        public static /* synthetic */ void $r8$lambda$UZL5bUVHLEXtT6Gt7e7DvJkHjJM(AnonymousClass3 r0, StoryEntry r1) {
            r0.lambda$onRenderedFirstFrame$0(r1);
        }

        public AnonymousClass3(PreviewView r1, StoryEntry r2, Runnable[] r3) {
            this.this$0 = r1;
            this.val$entry = r2;
            this.val$whenReadyFinal = r3;
        }

        private /* synthetic */ void lambda$onRenderedFirstFrame$0(StoryEntry r4) {
            if (PreviewView.m20154$$Nest$fgetbitmap(this.this$0) == null) goto L9;
            PreviewView.m20154$$Nest$fgetbitmap(this.this$0).recycle();
            if (r4.blurredVideoThumb != PreviewView.m20154$$Nest$fgetbitmap(this.this$0)) goto L7;
            r4.blurredVideoThumb = null;
        L7:
            PreviewView.m20168$$Nest$fputbitmap(this.this$0, null);
            this.this$0.invalidate();
            return;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onError(VideoPlayer r1, Exception r2) {
            if (PreviewView.m20157$$Nest$fgetonErrorListener(this.this$0) == null) goto L6;
            PreviewView.m20157$$Nest$fgetonErrorListener(this.this$0).run();
            return;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onRenderedFirstFrame() {
            if (PreviewView.m20161$$Nest$fgettextureViewHolder(this.this$0) != null) goto L5;
        L7:
            Runnable r0 = this.val$whenReadyFinal[0];
            PreviewView r2 = this.this$0;
            if (r0 == null) goto L17;
            r2.post(r0);
            this.val$whenReadyFinal[0] = null;
            if (PreviewView.m20154$$Nest$fgetbitmap(this.this$0) == null) goto L24;
            PreviewView.m20154$$Nest$fgetbitmap(this.this$0).recycle();
            if (this.val$entry.blurredVideoThumb != PreviewView.m20154$$Nest$fgetbitmap(this.this$0)) goto L14;
            this.val$entry.blurredVideoThumb = null;
        L14:
            PreviewView.m20168$$Nest$fputbitmap(this.this$0, null);
            this.this$0.invalidate();
            return;
        L24:
            return;
        L17:
            if (PreviewView.m20160$$Nest$fgettextureView(r2) != null) goto L19;
            return;
        L19:
            if (PreviewView.m20161$$Nest$fgettextureViewHolder(this.this$0) != null) goto L21;
        L22:
            PreviewView.m20160$$Nest$fgettextureView(this.this$0).animate().alpha(1.0f).setDuration(180).withEndAction(new PreviewView$3$$ExternalSyntheticLambda0(this, this.val$entry)).start();
            return;
        L21:
            if (PreviewView.m20161$$Nest$fgettextureViewHolder(this.this$0).active == false) goto L22;
            return;
        L5:
            if (PreviewView.m20161$$Nest$fgettextureViewHolder(this.this$0).active == false) goto L7;
            PreviewView.m20161$$Nest$fgettextureViewHolder(this.this$0).activateTextureView(PreviewView.m20167$$Nest$fgetvideoWidth(this.this$0), PreviewView.m20165$$Nest$fgetvideoHeight(this.this$0));
            goto L7
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onStateChanged(boolean r1, int r2) {
            if (PreviewView.m20166$$Nest$fgetvideoPlayer(this.this$0) != null) goto L6;
            return;
        L6:
            if (PreviewView.m20166$$Nest$fgetvideoPlayer(this.this$0) != null) goto L8;
        L11:
            AndroidUtilities.cancelRunOnUIThread(PreviewView.m20163$$Nest$fgetupdateProgressRunnable(this.this$0));
            return;
        L8:
            if (PreviewView.m20166$$Nest$fgetvideoPlayer(this.this$0).isPlaying() == false) goto L11;
            AndroidUtilities.runOnUIThread(PreviewView.m20163$$Nest$fgetupdateProgressRunnable(this.this$0));
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onSurfaceTextureUpdated(SurfaceTexture r1) {
            this.this$0.invalidateTextureViewHolder();
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onVideoSizeChanged(int r3, int r4, int r5, float r6) {
            StoryEntry r52 = this.val$entry;
            if (r52 == null) goto L7;
            r52.hdrInfo = PreviewView.m20166$$Nest$fgetvideoPlayer(this.this$0).getHDRStaticInfo(this.val$entry.hdrInfo);
            if (PreviewView.m20160$$Nest$fgettextureView(this.this$0) == null) goto L7;
            PreviewView.m20160$$Nest$fgettextureView(this.this$0).setHDRInfo(this.val$entry.hdrInfo);
        L7:
            PreviewView.m20172$$Nest$fputvideoWidth(this.this$0, (int) (r3 * r6));
            PreviewView.m20171$$Nest$fputvideoHeight(this.this$0, (int) (r4 * r6));
            StoryEntry r32 = this.val$entry;
            if (r32 != null) goto L10;
        L14:
            this.this$0.applyMatrix();
            if (PreviewView.m20160$$Nest$fgettextureView(this.this$0) == null) goto L18;
            PreviewView.m20160$$Nest$fgettextureView(this.this$0).setVideoSize(PreviewView.m20167$$Nest$fgetvideoWidth(this.this$0), PreviewView.m20165$$Nest$fgetvideoHeight(this.this$0));
            return;
        L18:
            return;
        L10:
            if (r32.width == PreviewView.m20167$$Nest$fgetvideoWidth(this.this$0)) goto L12;
        L13:
            this.val$entry.width = PreviewView.m20167$$Nest$fgetvideoWidth(this.this$0);
            this.val$entry.height = PreviewView.m20165$$Nest$fgetvideoHeight(this.this$0);
            this.val$entry.setupMatrix();
            goto L14
        L12:
            if (this.val$entry.height == PreviewView.m20165$$Nest$fgetvideoHeight(this.this$0)) goto L14;
            goto L13
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PreviewView$4, reason: invalid class name */
    public class AnonymousClass4 implements VideoPlayer.VideoPlayerDelegate {
        final /* synthetic */ PreviewView this$0;

        public AnonymousClass4(PreviewView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onError(VideoPlayer r1, Exception r2) {
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onRenderedFirstFrame() {
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onStateChanged(boolean r1, int r2) {
            if (PreviewView.m20158$$Nest$fgetroundPlayer(this.this$0) != null) goto L6;
            return;
        L6:
            if (PreviewView.m20158$$Nest$fgetroundPlayer(this.this$0) != null) goto L8;
        L11:
            AndroidUtilities.cancelRunOnUIThread(PreviewView.m20164$$Nest$fgetupdateRoundProgressRunnable(this.this$0));
            return;
        L8:
            if (PreviewView.m20158$$Nest$fgetroundPlayer(this.this$0).isPlaying() == false) goto L11;
            AndroidUtilities.runOnUIThread(PreviewView.m20164$$Nest$fgetupdateRoundProgressRunnable(this.this$0));
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onVideoSizeChanged(int r1, int r2, int r3, float r4) {
            PreviewView.m20170$$Nest$fputroundPlayerWidth(this.this$0, r1);
            PreviewView.m20169$$Nest$fputroundPlayerHeight(this.this$0, r2);
            if (PreviewView.m20159$$Nest$fgetroundView(this.this$0) == null) goto L6;
            PreviewView.m20159$$Nest$fgetroundView(this.this$0).resizeTextureView(r1, r2);
            return;
        }
    }

    public static class TextureViewHolder {
        public boolean active;
        private TextureView textureView;
        public boolean textureViewActive;
        public int videoHeight;
        public int videoWidth;
        private Utilities.Callback2<Integer, Integer> whenTextureViewActive;
        private Utilities.Callback<TextureView> whenTextureViewReceived;

        public TextureViewHolder() {
        }

        public void activateTextureView(int r2, int r3) {
            this.textureViewActive = true;
            this.videoWidth = r2;
            this.videoHeight = r3;
            Utilities.Callback2<Integer, Integer> r32 = this.whenTextureViewActive;
            if (r32 == null) goto L6;
            r32.run(Integer.valueOf(r2), Integer.valueOf(this.videoHeight));
            return;
        }

        public void setTextureView(TextureView r3) {
            TextureView r0 = this.textureView;
            if (r0 == r3) goto L15;
            if (r0 == null) goto L10;
            ViewParent r02 = r0.getParent();
            if ((r02 instanceof ViewGroup) == false) goto L9;
            ((ViewGroup) r02).removeView(this.textureView);
        L9:
            this.textureView = null;
        L10:
            this.textureViewActive = false;
            this.textureView = r3;
            Utilities.Callback<TextureView> r2 = this.whenTextureViewReceived;
            if (r2 == null) goto L14;
            r2.run(r3);
            return;
        L14:
            return;
        }

        public void takeTextureView(Utilities.Callback<TextureView> r1, Utilities.Callback2<Integer, Integer> r2) {
            this.whenTextureViewReceived = r1;
            this.whenTextureViewActive = r2;
            TextureView r22 = this.textureView;
            if (r22 == null) goto L7;
            if (r1 == null) goto L7;
            r1.run(r22);
        L7:
            if (this.textureViewActive == false) goto L12;
            Utilities.Callback2<Integer, Integer> r12 = this.whenTextureViewActive;
            if (r12 == null) goto L13;
            r12.run(Integer.valueOf(this.videoWidth), Integer.valueOf(this.videoHeight));
            return;
        L13:
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$0IG3_3cErD2eiM7Frof0cm650Fw(PreviewView r0, int r1, int[] r2) {
        r0.lambda$setupGradient$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$5V9rmXkiaEDit09trkr7GTfV98w(PreviewView r0) {
        r0.lambda$new$12();
    }

    public static /* synthetic */ void $r8$lambda$CdwQPudGZoFEkgY51W58TfqoWbc(PreviewView r0) {
        r0.setupGradient();
    }

    /* renamed from: $r8$lambda$FSW-QZqYS3VDNRF0xVhDK9oVE70, reason: not valid java name */
    public static /* synthetic */ void m20149$r8$lambda$FSWQZqYS3VDNRF0xVhDK9oVE70(PreviewView r0) {
        r0.lambda$new$11();
    }

    public static /* synthetic */ void $r8$lambda$G62VkjFvkuZ4miCHU5hzCs_sObI(PreviewView r0, Bitmap[] r1, StoryEntry r2, boolean[] r3) {
        r0.lambda$setupImage$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$GH_9lL1Tx1Krc4A89qkYNRCsW3I(PreviewView r0, int r1, int[] r2) {
        r0.lambda$setupGradient$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$GrAyjDVfHpaNXQtElJZ3WXyjfkc(PreviewView r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$YvX6qPvkc_kWlwMcyzka-Jt4bas, reason: not valid java name */
    public static /* synthetic */ void m20150$r8$lambda$YvX6qPvkc_kWlwMcyzkaJt4bas(EmojiThemes r4, boolean r5, boolean r6, MotionBackgroundDrawable r7, int r8, Pair r9) {
        if (r9 == null) goto L11;
        long r0 = ((Long) r9.first).longValue();
        Bitmap r92 = ((WallpaperBitmapHolder) r9.second).bitmap;
        if (r0 != r4.getThemeId(r5 ? 1 : 0)) goto L9;
        if (r92 == null) goto L10;
        r7.setPatternBitmap(r4.getWallpaper(r6 ? 1 : 0).settings.intensity, r92);
        r7.setPatternColorFilter(r8);
        r7.setPatternAlpha(1.0f);
        return;
    L10:
        return;
    L9:
        return;
    }

    public static /* synthetic */ void $r8$lambda$fW1tJ093VnwnLqk21bvzrBt4U3s(PreviewView r0) {
        r0.lambda$new$10();
    }

    public static /* synthetic */ void $r8$lambda$gtUDqzZcecXMSQuffPZSxUaUDy4(Utilities.Callback r0, Bitmap r1) {
        r0.run(r1);
    }

    /* renamed from: $r8$lambda$oMIzzk-VsyTwP8oaTDeZfzJOXEg, reason: not valid java name */
    public static /* synthetic */ void m20151$r8$lambda$oMIzzkVsyTwP8oaTDeZfzJOXEg(PreviewView r0) {
        r0.lambda$setupVideoPlayer$8();
    }

    public static /* synthetic */ void $r8$lambda$qPX0vvL9BrttsjUCPPw9nuDSrCU(PreviewView r0, StoryEntry.HDRInfo r1) {
        r0.lambda$setupVideoPlayer$9(r1);
    }

    /* renamed from: $r8$lambda$vTyITAeWgntpjJfLjvEqgXkFG-Y, reason: not valid java name */
    public static /* synthetic */ Bitmap m20152$r8$lambda$vTyITAeWgntpjJfLjvEqgXkFGY(PreviewView r0, StoryEntry r1, long r2, String r4, BitmapFactory.Options r5) {
        return r0.lambda$setupImage$3(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$vzwAhpfFpreCXFiK_tN5sxGxuqE(PreviewView r0, StoryEntry r1) {
        r0.lambda$setupImage$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$xW4_UpPPqR_o185V2LHvtWoY7CI(int r5, int r6, int r7, Bitmap[] r8, Utilities.Callback r9) {
        Bitmap r52 = Bitmap.createBitmap(r5, r6, Bitmap.Config.ARGB_8888);
        Canvas r62 = new Canvas(r52);
        Path r0 = new Path();
        RectF r1 = new RectF();
        r1.set(0.0f, 0.0f, r52.getWidth(), r52.getHeight());
        float r72 = r7;
        r0.addRoundRect(r1, r72, r72, Path.Direction.CW);
        r62.clipPath(r0);
        int r73 = 0;
    L4:
        if (r73 >= r8.length) goto L10;
        if (r8[r73] == null) goto L9;
        r62.save();
        r62.translate(r52.getWidth() / 2.0f, r52.getHeight() / 2.0f);
        float r02 = Math.max(r52.getWidth() / r8[r73].getWidth(), r52.getHeight() / r8[r73].getHeight());
        r62.scale(r02, r02);
        r62.translate((-r8[r73].getWidth()) / 2.0f, (-r8[r73].getHeight()) / 2.0f);
        r62.drawBitmap(r8[r73], 0.0f, 0.0f, null);
        r62.restore();
        AndroidUtilities.recycleBitmap(r8[r73]);
    L9:
        r73 = r73 + 1;
        goto L4
    L10:
        Utilities.stackBlurBitmap(r52, 1);
        AndroidUtilities.runOnUIThread(new PreviewView$$ExternalSyntheticLambda14(r9, r52));
    }

    /* renamed from: -$$Nest$fgetaudioPlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoPlayer m20153$$Nest$fgetaudioPlayer(PreviewView r0) {
        return r0.audioPlayer;
    }

    /* renamed from: -$$Nest$fgetbitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m20154$$Nest$fgetbitmap(PreviewView r0) {
        return r0.bitmap;
    }

    /* renamed from: -$$Nest$fgetcollage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CollageLayoutView2 m20155$$Nest$fgetcollage(PreviewView r0) {
        return r0.collage;
    }

    /* renamed from: -$$Nest$fgetentry, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryEntry m20156$$Nest$fgetentry(PreviewView r0) {
        return r0.entry;
    }

    /* renamed from: -$$Nest$fgetonErrorListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m20157$$Nest$fgetonErrorListener(PreviewView r0) {
        return r0.onErrorListener;
    }

    /* renamed from: -$$Nest$fgetroundPlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoPlayer m20158$$Nest$fgetroundPlayer(PreviewView r0) {
        return r0.roundPlayer;
    }

    /* renamed from: -$$Nest$fgetroundView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundView m20159$$Nest$fgetroundView(PreviewView r0) {
        return r0.roundView;
    }

    /* renamed from: -$$Nest$fgettextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoEditTextureView m20160$$Nest$fgettextureView(PreviewView r0) {
        return r0.textureView;
    }

    /* renamed from: -$$Nest$fgettextureViewHolder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureViewHolder m20161$$Nest$fgettextureViewHolder(PreviewView r0) {
        return r0.textureViewHolder;
    }

    /* renamed from: -$$Nest$fgetupdateAudioProgressRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m20162$$Nest$fgetupdateAudioProgressRunnable(PreviewView r0) {
        return r0.updateAudioProgressRunnable;
    }

    /* renamed from: -$$Nest$fgetupdateProgressRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m20163$$Nest$fgetupdateProgressRunnable(PreviewView r0) {
        return r0.updateProgressRunnable;
    }

    /* renamed from: -$$Nest$fgetupdateRoundProgressRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m20164$$Nest$fgetupdateRoundProgressRunnable(PreviewView r0) {
        return r0.updateRoundProgressRunnable;
    }

    /* renamed from: -$$Nest$fgetvideoHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20165$$Nest$fgetvideoHeight(PreviewView r0) {
        return r0.videoHeight;
    }

    /* renamed from: -$$Nest$fgetvideoPlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoPlayer m20166$$Nest$fgetvideoPlayer(PreviewView r0) {
        return r0.videoPlayer;
    }

    /* renamed from: -$$Nest$fgetvideoWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20167$$Nest$fgetvideoWidth(PreviewView r0) {
        return r0.videoWidth;
    }

    /* renamed from: -$$Nest$fputbitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20168$$Nest$fputbitmap(PreviewView r0, Bitmap r1) {
        r0.bitmap = r1;
    }

    /* renamed from: -$$Nest$fputroundPlayerHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20169$$Nest$fputroundPlayerHeight(PreviewView r0, int r1) {
        r0.roundPlayerHeight = r1;
    }

    /* renamed from: -$$Nest$fputroundPlayerWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20170$$Nest$fputroundPlayerWidth(PreviewView r0, int r1) {
        r0.roundPlayerWidth = r1;
    }

    /* renamed from: -$$Nest$fputvideoHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20171$$Nest$fputvideoHeight(PreviewView r0, int r1) {
        r0.videoHeight = r1;
    }

    /* renamed from: -$$Nest$fputvideoWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20172$$Nest$fputvideoWidth(PreviewView r0, int r1) {
        r0.videoWidth = r1;
    }

    /* renamed from: -$$Nest$mseekTo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20173$$Nest$mseekTo(PreviewView r0, long r1) {
        r0.seekTo(r1);
    }

    public PreviewView(Context r10, BlurringShader.BlurManager r11, TextureViewHolder r12) {
        super(r10);
        this.bitmapSrc = new Rect();
        this.bitmapDst = new Rect();
        Paint r7 = new Paint(1);
        this.snapPaint = r7;
        this.slowerSeek = new PreviewView$$ExternalSyntheticLambda2(this);
        this.updateProgressRunnable = new PreviewView$$ExternalSyntheticLambda3(this);
        this.updateAudioProgressRunnable = new PreviewView$$ExternalSyntheticLambda4(this);
        this.updateRoundProgressRunnable = new PreviewView$$ExternalSyntheticLambda5(this);
        this.wallpaperDrawableCrossfade = new AnimatedFloat(this, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.bitmapPaint = new Paint(7);
        this.gradientPaint = new Paint(1);
        this.matrix = new Matrix();
        this.vertices = new float[2];
        this.draw = true;
        this.thumbAlpha = new AnimatedFloat(this, 0, 320, CubicBezierInterpolator.EASE_OUT);
        this.drawForThemeToggle = false;
        this.invertMatrix = new Matrix();
        this.transformMatrix = new Matrix();
        this.transformBackMatrix = new Matrix();
        this.allowCropping = true;
        this.lastTouch = new PointF();
        this.touch = new PointF();
        this.touchMatrix = new Matrix();
        this.finalMatrix = new Matrix();
        this.pauseLinks = new HashSet();
        this.blurManager = r11;
        this.textureViewHolder = r12;
        r7.setStrokeWidth(AndroidUtilities.dp(1.0f));
        r7.setStyle(Paint.Style.STROKE);
        r7.setColor(-1);
        r7.setShadowLayer(AndroidUtilities.dp(3.0f), 0.0f, AndroidUtilities.dp(1.0f), TLObject.FLAG_30);
    }

    private void extractPointsData(Matrix r8) {
        if (this.entry != null) goto L5;
        return;
    L5:
        float[] r1 = this.vertices;
        r1[0] = r0.width / 2.0f;
        r1[1] = r0.height / 2.0f;
        r8.mapPoints(r1);
        float[] r0 = this.vertices;
        this.cx = r0[0];
        this.cy = r0[1];
        StoryEntry r12 = this.entry;
        r0[0] = r12.width;
        r0[1] = r12.height / 2.0f;
        r8.mapPoints(r0);
        float[] r02 = this.vertices;
        this.angle = (float) Math.toDegrees(Math.atan2(r02[1] - this.cy, r02[0] - this.cx));
        float r03 = this.cx;
        float r13 = this.cy;
        float[] r5 = this.vertices;
        this.w = MathUtils.distance(r03, r13, r5[0], r5[1]) * 2.0f;
        float[] r04 = this.vertices;
        StoryEntry r14 = this.entry;
        r04[0] = r14.width / 2.0f;
        r04[1] = r14.height;
        r8.mapPoints(r04);
        float r82 = this.cx;
        float r05 = this.cy;
        float[] r15 = this.vertices;
        this.h = MathUtils.distance(r82, r05, r15[0], r15[1]) * 2.0f;
    }

    public static Drawable getBackgroundDrawable(Drawable r4, int r5, long r6, boolean r8) {
        TLRPC.WallPaper r1 = null;
        if (r6 != Long.MIN_VALUE) goto L6;
        return null;
    L6:
        if (r6 < 0) goto L10;
        TLRPC.UserFull r62 = MessagesController.getInstance(r5).getUserFull(r6);
        if (r62 == null) goto L14;
        r1 = r62.wallpaper;
    L14:
        return getBackgroundDrawable(r4, r5, r1, r8);
    L10:
        TLRPC.ChatFull r63 = MessagesController.getInstance(r5).getChatFull(-r6);
        if (r63 == null) goto L14;
        r1 = r63.wallpaper;
        goto L14
    }

    public static Drawable getBackgroundDrawable(Drawable r5, int r6, TLRPC.WallPaper r7, boolean r8) {
        if (r7 == null) goto L8;
        if (TextUtils.isEmpty(ChatThemeController.getWallpaperEmoticon(r7)) == false) goto L8;
        return ChatBackgroundDrawable.getOrCreate(r5, r7, r8);
    L8:
        if (r7 != null) goto L10;
    L12:
        EmojiThemes r72 = null;
    L14:
        if (r72 != null) goto L16;
        SharedPreferences r62 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
        String r1 = "Blue";
        String r73 = r62.getString("lastDayTheme", "Blue");
        if (Theme.getTheme(r73) != null) goto L20;
    L21:
        r73 = "Blue";
    L22:
        String r3 = "Dark Blue";
        String r63 = r62.getString("lastDarkTheme", "Dark Blue");
        if (Theme.getTheme(r63) != null) goto L25;
    L26:
        r63 = "Dark Blue";
    L27:
        Theme.ThemeInfo r2 = Theme.getActiveTheme();
        if (r73.equals(r63) == true) goto L30;
        r3 = r63;
    L36:
        r1 = r73;
    L39:
        if (r8 == false) goto L41;
        Theme.ThemeInfo r64 = Theme.getTheme(r3);
    L42:
        SparseIntArray r74 = new SparseIntArray();
        String[] r12 = new String[1];
        String r22 = r64.assetName;
        if (r22 == null) goto L45;
        SparseIntArray r52 = Theme.getThemeFileValues(null, r22, r12);
    L46:
        int[] r23 = Theme.getDefaultColors();
        if (r23 == null) goto L52;
        int r32 = 0;
    L50:
        if (r32 >= r23.length) goto L52;
        r74.put(r32, r23[r32]);
        r32 = r32 + 1;
    L52:
        Theme.ThemeAccent r24 = r64.getAccent(false);
        if (r24 == null) goto L55;
        r24.fillAccentColors(r52, r74);
    L60:
        Theme.BackgroundDrawableSettings r53 = Theme.createBackgroundDrawable(r64, r74, r12[0], 0, true);
        Drawable r65 = r53.themedWallpaper;
        if (r65 == null) goto L64;
        return r65;
    L64:
        return r53.wallpaper;
    L55:
        if (r52 == null) goto L60;
        int r25 = 0;
    L58:
        if (r25 >= r52.size()) goto L60;
        r74.put(r52.keyAt(r25), r52.valueAt(r25));
        r25 = r25 + 1;
        goto L58
    L45:
        r52 = Theme.getThemeFileValues(new File(r64.pathToFile), null, r12);
        goto L46
    L41:
        r64 = Theme.getTheme(r1);
        goto L42
    L30:
        if (r2.isDark() == false) goto L32;
    L37:
        r3 = r63;
        goto L39
    L32:
        if (r73.equals("Dark Blue") == true) goto L37;
        if (r73.equals("Night") == false) goto L36;
    L25:
        if (Theme.getTheme(r63).isDark() == true) goto L27;
    L20:
        if (Theme.getTheme(r73).isDark() == false) goto L22;
    L16:
        return getBackgroundDrawableFromTheme(r6, r72, 0, r8);
    L10:
        if (r7.settings == null) goto L12;
        r72 = ChatThemeController.getInstance(r6).getTheme(ThemeKey.ofEmoticon(r7.settings.emoticon));
        goto L14
    }

    public static Drawable getBackgroundDrawableFromTheme(int r1, String r2, boolean r3) {
        return getBackgroundDrawableFromTheme(r1, r2, r3, false);
    }

    public static Drawable getBackgroundDrawableFromTheme(int r1, String r2, boolean r3, boolean r4) {
        EmojiThemes r22 = ChatThemeController.getInstance(r1).getTheme(ThemeKey.ofEmoticon(r2));
        if (r22 != null) goto L7;
        return Theme.getCachedWallpaper();
    L7:
        return getBackgroundDrawableFromTheme(r1, r22, 0, r3, r4);
    }

    public static Drawable getBackgroundDrawableFromTheme(int r1, EmojiThemes r2, int r3, boolean r4) {
        return getBackgroundDrawableFromTheme(r1, r2, r3, r4, false);
    }

    public static Drawable getBackgroundDrawableFromTheme(int r10, EmojiThemes r11, int r12, boolean r13, boolean r14) {
        if (r11.isAnyStub() == false) goto L6;
        Drawable r102 = Theme.createBackgroundDrawable(EmojiThemes.getDefaultThemeInfo(r13), r11.getPreviewColors(r10, r13 ? 1 : 0), r11.getWallpaperLink(r13 ? 1 : 0), r12, false).wallpaper;
        return new ColorDrawable(-16777216);
    L6:
        SparseIntArray r103 = r11.getPreviewColors(r10, r13 ? 1 : 0);
        int r0 = Theme.key_chat_wallpaper;
        int r4 = r103.get(r0, Theme.getColor(r0));
        int r02 = Theme.key_chat_wallpaper_gradient_to1;
        int r5 = r103.get(r02, Theme.getColor(r02));
        int r03 = Theme.key_chat_wallpaper_gradient_to2;
        int r6 = r103.get(r03, Theme.getColor(r03));
        int r04 = Theme.key_chat_wallpaper_gradient_to3;
        int r7 = r103.get(r04, Theme.getColor(r04));
        MotionBackgroundDrawable r3 = new MotionBackgroundDrawable();
        r3.isPreview = r14;
        r3.setPatternBitmap(r11.getWallpaper(r13 ? 1 : 0).settings.intensity);
        r3.setColors(r4, r5, r6, r7, 0, true);
        r3.setPhase(r12);
        PreviewView$$ExternalSyntheticLambda0 r05 = new PreviewView$$ExternalSyntheticLambda0(r11, r13, r13, r3, r3.getPatternColor());
        r11.loadWallpaper(r13 ? 1 : 0, r05);
        return r3;
    }

    private /* synthetic */ void lambda$new$0() {
        seekTo(this.finalSeekPosition);
        this.slowerSeekScheduled = false;
    }

    private /* synthetic */ void lambda$new$10() {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.timelineView == null) goto L35;
        long r02 = r0.getCurrentPosition();
        if (getDuration() <= 1) goto L28;
        float r2 = r02 / getDuration();
        boolean r4 = true;
        if (this.timelineView.isDragging() == true) goto L19;
        StoryEntry r3 = this.entry;
        if (r2 < r3.left) goto L16;
        if (r2 <= r3.right) goto L19;
    L16:
        if ((System.currentTimeMillis() - this.seekedLastTime) <= 500) goto L19;
        this.seekedLastTime = System.currentTimeMillis();
        VideoPlayer r03 = this.videoPlayer;
        long r1 = (long) (this.entry.left * getDuration());
        r03.seekTo(r1);
        updateAudioPlayer(true);
        updateRoundPlayer(true);
        r02 = r1;
    L27:
        this.timelineView.setProgress(this.videoPlayer.getCurrentPosition());
    L30:
        if (this.videoPlayer.isPlaying() == false) goto L32;
        AndroidUtilities.cancelRunOnUIThread(this.updateProgressRunnable);
        AndroidUtilities.runOnUIThread(this.updateProgressRunnable, (long) (1000.0f / AndroidUtilities.screenRefreshRate));
    L32:
        this.lastPos = r02;
        return;
    L19:
        if (r02 >= this.lastPos) goto L21;
        boolean r22 = true;
    L22:
        updateAudioPlayer(r22);
        if (r02 < this.lastPos) goto L26;
        r4 = false;
    L26:
        updateRoundPlayer(r4);
        goto L27
    L21:
        r22 = false;
        goto L22
    L28:
        this.timelineView.setProgress(this.videoPlayer.getCurrentPosition());
        goto L30
    }

    private /* synthetic */ void lambda$new$11() {
        if (this.audioPlayer != null) goto L5;
        return;
    L5:
        if (this.videoPlayer == null) goto L7;
        return;
    L7:
        if (this.roundPlayer == null) goto L9;
        return;
    L9:
        if (this.timelineView != null) goto L11;
        return;
    L11:
        if (isCollage() == true) goto L31;
        long r0 = this.audioPlayer.getCurrentPosition();
        StoryEntry r2 = this.entry;
        if (r2 == null) goto L22;
        float r3 = r0;
        float r4 = r2.audioLeft;
        long r5 = r2.audioDuration;
        if (r3 < (r4 * r5)) goto L20;
        if (r3 <= (r2.audioRight * r5)) goto L22;
    L20:
        if ((System.currentTimeMillis() - this.seekedLastTime) <= 500) goto L22;
        this.seekedLastTime = System.currentTimeMillis();
        VideoPlayer r02 = this.audioPlayer;
        long r1 = (long) (this.entry.audioLeft * r1.audioDuration);
        r02.seekTo(r1);
        r0 = r1;
    L22:
        this.timelineView.setProgress(r0);
        if (this.audioPlayer.isPlaying() == false) goto L30;
        AndroidUtilities.cancelRunOnUIThread(this.updateAudioProgressRunnable);
        AndroidUtilities.runOnUIThread(this.updateAudioProgressRunnable, (long) (1000.0f / AndroidUtilities.screenRefreshRate));
        return;
    L30:
        return;
    }

    private /* synthetic */ void lambda$new$12() {
        if (this.roundPlayer != null) goto L5;
        return;
    L5:
        if (this.videoPlayer == null) goto L7;
        return;
    L7:
        if (isCollage() == false) goto L9;
        return;
    L9:
        if (this.timelineView == null) goto L28;
        long r0 = this.roundPlayer.getCurrentPosition();
        StoryEntry r2 = this.entry;
        if (r2 == null) goto L20;
        float r3 = r0;
        float r4 = r2.roundLeft;
        long r5 = r2.roundDuration;
        if (r3 < (r4 * r5)) goto L18;
        if (r3 <= (r2.roundRight * r5)) goto L20;
    L18:
        if ((System.currentTimeMillis() - this.seekedLastTime) <= 500) goto L20;
        this.seekedLastTime = System.currentTimeMillis();
        VideoPlayer r02 = this.roundPlayer;
        long r1 = (long) (this.entry.roundLeft * r1.roundDuration);
        r02.seekTo(r1);
        updateAudioPlayer(true);
        r0 = r1;
    L20:
        this.timelineView.setProgress(r0);
        if (this.roundPlayer.isPlaying() == false) goto L27;
        AndroidUtilities.cancelRunOnUIThread(this.updateRoundProgressRunnable);
        AndroidUtilities.runOnUIThread(this.updateRoundProgressRunnable, (long) (1000.0f / AndroidUtilities.screenRefreshRate));
        return;
    L27:
        return;
    }

    private /* synthetic */ void lambda$setupGradient$6(int r10, int[] r11) {
        StoryEntry r0 = this.entry;
        int r1 = r11[0];
        this.gradientTop = r1;
        r0.gradientTopColor = r1;
        int r12 = r11[1];
        this.gradientBottom = r12;
        r0.gradientBottomColor = r12;
        this.gradientPaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, r10, r11, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
        invalidate();
        VideoEditTextureView r102 = this.textureView;
        if (r102 == null) goto L5;
        r102.updateUiBlurGradient(this.gradientTop, this.gradientBottom);
    L5:
        PhotoFilterView r103 = this.photoFilterView;
        if (r103 == null) goto L9;
        r103.updateUiBlurGradient(this.gradientTop, this.gradientBottom);
        return;
    }

    private /* synthetic */ void lambda$setupGradient$7(int r10, int[] r11) {
        StoryEntry r0 = this.entry;
        int r1 = r11[0];
        this.gradientTop = r1;
        r0.gradientTopColor = r1;
        int r12 = r11[1];
        this.gradientBottom = r12;
        r0.gradientBottomColor = r12;
        this.gradientPaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, r10, r11, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
        invalidate();
        VideoEditTextureView r102 = this.textureView;
        if (r102 == null) goto L5;
        r102.updateUiBlurGradient(this.gradientTop, this.gradientBottom);
    L5:
        PhotoFilterView r103 = this.photoFilterView;
        if (r103 == null) goto L9;
        r103.updateUiBlurGradient(this.gradientTop, this.gradientBottom);
        return;
    }

    private /* synthetic */ Bitmap lambda$setupImage$3(StoryEntry r2, long r3, String r5, BitmapFactory.Options r6) {
        if (r2.isVideo == false) goto L13;
        String r22 = r2.thumbPath;
        if (r22 != null) goto L7;
        return MediaStore.Video.Thumbnails.getThumbnail(getContext().getContentResolver(), r3, 1, r6);
    L10:
        this.invalidate();
        return null;
    L7:
        return BitmapFactory.decodeFile(r22, r6);
    L13:
        return BitmapFactory.decodeFile(r5, r6);
    }

    private /* synthetic */ void lambda$setupImage$4(Bitmap[] r3, StoryEntry r4, boolean[] r5) {
        Bitmap r0 = this.bitmap;
        if (r0 != null) goto L5;
    L7:
        Bitmap r32 = r3[0];
        this.bitmap = r32;
        if (r4 == null) goto L16;
        if (r4.isDraft == true) goto L16;
        if (r4.isVideo == false) goto L16;
        if (r32 == null) goto L16;
        r4.width = r32.getWidth();
        r4.height = this.bitmap.getHeight();
        r4.setupMatrix();
    L16:
        if (r5[0] == false) goto L25;
        if (r4 == null) goto L25;
        BlurringShader.BlurManager r33 = this.blurManager;
        if (r33 == null) goto L25;
        if (this.bitmap == null) goto L25;
        r33.resetBitmap();
        this.blurManager.setFallbackBlur(r4.buildBitmap(0.2f, this.bitmap), 0);
        Runnable r34 = this.invalidateBlur;
        if (r34 == null) goto L25;
        r34.run();
    L25:
        setupGradient();
        invalidate();
        return;
    L5:
        if (r0.isRecycled() == true) goto L7;
        this.bitmap.recycle();
        goto L7
    }

    private /* synthetic */ void lambda$setupImage$5(StoryEntry r15) {
        Bitmap[] r1 = new Bitmap[1];
        boolean[] r2 = {true};
        if (r15 != null) goto L5;
    L49:
        AndroidUtilities.runOnUIThread(new PreviewView$$ExternalSyntheticLambda13(this, r1, r15, r2));
        return;
    L5:
        if (getMeasuredWidth() > 0) goto L8;
        int r4 = AndroidUtilities.displaySize.x;
    L7:
        int r6 = r4;
        int r7 = (int) ((r6 * 16) / 9.0f);
        if (r15.isVideo == false) goto L30;
        Bitmap r42 = r15.blurredVideoThumb;
        if (r42 == null) goto L15;
        r1[0] = r42;
    L15:
        if (r1[0] != null) goto L30;
        String r43 = r15.thumbPath;
        if (r43 == null) goto L30;
        if (r43.startsWith("vthumb://") == false) goto L30;
        long r44 = Long.parseLong(r15.thumbPath.substring(9));
        if (r1[0] == null) goto L23;
    L29:
        long r11 = r44;
        if (r11 >= 0) goto L40;
        if (r15.isVideo == false) goto L40;
        if (r15.thumbPath != null) goto L40;
        invalidate();
        return;
    L40:
        if (r1[0] != null) goto L49;
        File r45 = r15.getOriginalFile();
        if (r45 != null) goto L44;
        return;
    L44:
        PreviewView$$ExternalSyntheticLambda12 r5 = new PreviewView$$ExternalSyntheticLambda12(this, r15, r11, r45.getPath());
        boolean r46 = r15.isVideo;
        if (r46 == true) goto L47;
        int r8 = r15.orientation;
    L48:
        r1[0] = StoryEntry.getScaledBitmap(r5, r6, r7, r8, false, !r46);
        r2[0] = false;
        goto L49
    L47:
        r8 = 0;
        goto L48
    L23:
        if (Build.VERSION.SDK_INT < 29) goto L29;
        if (r15.isVideo == false) goto L27;
        Uri r82 = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, r44);     // Catch: Exception -> L51
    L28:
        r1[0] = getContext().getContentResolver().loadThumbnail(r82, new Size(r6, r7), null);     // Catch: Exception -> L51
        goto L29
    L27:
        r82 = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, r44);     // Catch: Exception -> L51
    L30:
        r44 = -1;
        goto L29
    L8:
        r4 = getMeasuredWidth();
        goto L7
    }

    private /* synthetic */ void lambda$setupVideoPlayer$8() {
        VideoEditTextureView r0 = this.textureView;
        if (r0 == null) goto L6;
        r0.release();
        removeView(this.textureView);
        this.textureView = null;
        return;
    }

    private /* synthetic */ void lambda$setupVideoPlayer$9(StoryEntry.HDRInfo r1) {
        VideoEditTextureView r0 = this.textureView;
        if (r0 == null) goto L6;
        r0.setHDRInfo(r1);
        return;
    }

    private void seekTo(long r2) {
        seekTo(r2, false);
    }

    private void setupCollage(StoryEntry r1) {
        TimelineView r0 = this.timelineView;
        if (r0 == null) goto L9;
        if (r1 == null) goto L6;
        ArrayList<StoryEntry> r12 = r1.collageContent;
    L7:
        r0.setCollage(r12);
        return;
    L6:
        r12 = null;
        goto L7
    }

    private void setupGradient() {
        if (this.entry != null) goto L6;
        return;
    L6:
        if (getMeasuredHeight() <= 0) goto L8;
        int r0 = getMeasuredHeight();
    L9:
        StoryEntry r1 = this.entry;
        if (r1.gradientTopColor != 0) goto L12;
    L20:
        Bitmap r12 = this.bitmap;
        if (r12 == null) goto L23;
        DominantColors.getColors(true, r12, true, new PreviewView$$ExternalSyntheticLambda9(this, r0));
    L27:
        invalidate();
        return;
    L23:
        Bitmap r13 = this.thumbBitmap;
        if (r13 == null) goto L26;
        DominantColors.getColors(true, r13, true, new PreviewView$$ExternalSyntheticLambda10(this, r0));
        goto L27
    L26:
        this.gradientPaint.setShader(null);
        goto L27
    L12:
        if (r1.gradientBottomColor == 0) goto L20;
        Paint r14 = this.gradientPaint;
        float r6 = r0;
        StoryEntry r02 = this.entry;
        int r3 = r02.gradientTopColor;
        this.gradientTop = r3;
        int r03 = r02.gradientBottomColor;
        this.gradientBottom = r03;
        r14.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, r6, new int[]{r3, r03}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
        VideoEditTextureView r04 = this.textureView;
        if (r04 == null) goto L17;
        r04.updateUiBlurGradient(this.gradientTop, this.gradientBottom);
    L17:
        PhotoFilterView r05 = this.photoFilterView;
        if (r05 == null) goto L27;
        r05.updateUiBlurGradient(this.gradientTop, this.gradientBottom);
        goto L27
    L8:
        r0 = AndroidUtilities.displaySize.y;
        goto L9
    }

    private void setupImage(StoryEntry r3) {
        Utilities.searchQueue.postRunnable(new PreviewView$$ExternalSyntheticLambda6(this, r3));
    }

    private boolean tapTouchEvent(MotionEvent r9) {
        if (r9.getAction() != 0) goto L7;
        this.tapTime = System.currentTimeMillis();
        return true;
    L7:
        if (r9.getAction() != 1) goto L16;
        if ((System.currentTimeMillis() - this.tapTime) > ViewConfiguration.getTapTimeout()) goto L13;
        Runnable r92 = this.onTap;
        if (r92 == null) goto L13;
        r92.run();
    L13:
        this.tapTime = 0;
        return true;
    L16:
        if (r9.getAction() != 3) goto L20;
        this.tapTime = 0;
        return false;
    L20:
        return false;
    }

    private boolean touchEvent(MotionEvent r18) {
        if (this.allowCropping == true) goto L6;
        return false;
    L6:
        if (r18.getPointerCount() <= 1) goto L8;
        boolean r2 = true;
    L9:
        PointF r5 = this.touch;
        if (r2 == false) goto L12;
        r5.x = (r18.getX(0) + r18.getX(1)) / 2.0f;
        this.touch.y = (r18.getY(0) + r18.getY(1)) / 2.0f;
        float r52 = MathUtils.distance(r18.getX(0), r18.getY(0), r18.getX(1), r18.getY(1));
        double r7 = Math.atan2(r18.getY(1) - r18.getY(0), r18.getX(1) - r18.getX(0));
    L14:
        if (this.multitouch == r2) goto L17;
        PointF r9 = this.lastTouch;
        PointF r10 = this.touch;
        r9.x = r10.x;
        r9.y = r10.y;
        this.lastTouchDistance = r52;
        this.lastTouchRotation = r7;
        this.multitouch = r2;
    L17:
        if (this.entry != null) goto L19;
        return false;
    L19:
        float r22 = r2.resultWidth / getWidth();
        if (r18.getActionMasked() != 0) goto L23;
        this.rotationDiff = 0.0f;
        this.snappedRotation = false;
        this.snappedToCenterAndScaled = false;
        this.doNotSpanRotation = false;
        invalidate();
        this.moving = true;
        this.touchMatrix.set(this.entry.matrix);
    L23:
        if (r18.getActionMasked() != 2) goto L73;
        if (this.moving == false) goto L73;
        if (this.entry == null) goto L73;
        PointF r92 = this.touch;
        float r102 = r92.x * r22;
        float r93 = r92.y * r22;
        PointF r11 = this.lastTouch;
        float r12 = r11.x * r22;
        float r112 = r11.y * r22;
        if (r18.getPointerCount() <= 1) goto L55;
        float r23 = this.lastTouchDistance;
        if (r23 == 0.0f) goto L33;
        float r24 = r52 / r23;
        this.touchMatrix.postScale(r24, r24, r102, r93);
    L33:
        float r25 = (float) Math.toDegrees(r7 - this.lastTouchRotation);
        float r14 = this.rotationDiff + r25;
        this.rotationDiff = r14;
        if (this.allowRotation == false) goto L36;
        float r16 = 90.0f;
    L52:
        if (this.allowRotation == false) goto L54;
        this.touchMatrix.postRotate(r25, r102, r93);
    L54:
        this.allowWithSingleTouch = true;
    L57:
        if (r18.getPointerCount() <= 1) goto L59;
    L60:
        this.touchMatrix.postTranslate(r102 - r12, r93 - r112);
    L61:
        this.finalMatrix.set(this.touchMatrix);
        this.matrix.set(this.touchMatrix);
        extractPointsData(this.matrix);
        float r26 = (Math.round(this.angle / r16) * r16) - this.angle;
        if (this.allowRotation == true) goto L64;
    L71:
        this.entry.matrix.set(this.finalMatrix);
        this.entry.editedMedia = true;
        applyMatrix();
        invalidate();
        goto L73
    L64:
        if (this.doNotSpanRotation == true) goto L71;
        if (Math.abs(r26) >= 3.5f) goto L70;
        this.finalMatrix.postRotate(r26, this.cx, this.cy);
        if (this.snappedRotation == true) goto L71;
        AndroidUtilities.vibrateCursor(this);
        this.snappedRotation = true;
        goto L71
    L70:
        this.snappedRotation = false;
        goto L71
    L59:
        if (this.allowWithSingleTouch == false) goto L61;
    L36:
        if (Math.abs(r14) <= 20.0f) goto L38;
        boolean r142 = true;
    L39:
        this.allowRotation = r142;
        if (r142 == true) goto L46;
        extractPointsData(this.touchMatrix);
        r16 = 90.0f;
        if (((Math.round(this.angle / 90.0f) * 90.0f) - this.angle) <= 20.0f) goto L44;
        boolean r13 = true;
    L45:
        this.allowRotation = r13;
    L48:
        if (this.snappedRotation == true) goto L52;
        AndroidUtilities.vibrateCursor(this);
        this.snappedRotation = true;
        goto L52
    L44:
        r13 = false;
        goto L45
    L46:
        r16 = 90.0f;
        goto L48
    L38:
        r142 = false;
        goto L39
    L55:
        r16 = 90.0f;
    L73:
        if (r18.getAction() == 1) goto L77;
        if (r18.getAction() == 3) goto L77;
    L80:
        PointF r1 = this.lastTouch;
        PointF r27 = this.touch;
        r1.x = r27.x;
        r1.y = r27.y;
        this.lastTouchDistance = r52;
        this.lastTouchRotation = r7;
        return true;
    L77:
        if (r18.getPointerCount() > 1) goto L79;
        this.allowWithSingleTouch = false;
        onEntityDraggedTop(false);
        onEntityDraggedBottom(false);
    L79:
        this.moving = false;
        this.allowRotation = false;
        this.rotationDiff = 0.0f;
        this.snappedToCenterAndScaled = false;
        this.snappedRotation = false;
        invalidate();
        goto L80
    L12:
        r5.x = r18.getX(0);
        this.touch.y = r18.getY(0);
        r7 = 0.0d;
        r52 = 0.0f;
        goto L14
    L8:
        r2 = false;
        goto L9
    }

    public abstract boolean additionalTouchEvent(MotionEvent r1);

    public void applyMatrix() {
        StoryEntry r0 = this.entry;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isRepostMessage == false) goto L8;
        return;
    L8:
        if (this.textureView == null) goto L16;
        this.matrix.set(r0.matrix);
        Matrix r02 = this.matrix;
        float r1 = 1.0f / getWidth();
        int r3 = this.entry.width;
        if (r3 >= 0) goto L12;
        r3 = this.videoWidth;
    L12:
        float r12 = r1 * r3;
        float r2 = 1.0f / getHeight();
        int r32 = this.entry.height;
        if (r32 >= 0) goto L15;
        r32 = this.videoHeight;
    L15:
        r02.preScale(r12, r2 * r32);
        this.matrix.postScale(getWidth() / this.entry.resultWidth, getHeight() / this.entry.resultHeight);
        this.transformBackMatrix.reset();
        this.transformMatrix.invert(this.transformBackMatrix);
        this.textureView.setTransform(this.matrix);
        this.textureView.invalidate();
    L16:
        invalidate();
    }

    public void attachRoundView(RoundView r1) {
        this.roundView = r1;
        if (r1 == null) goto L8;
        VideoPlayer r0 = this.roundPlayer;
        if (r0 == null) goto L9;
        r0.setTextureView(r1.textureView);
        return;
    L9:
        return;
    }

    public void checkVolumes() {
        VideoPlayer r0 = this.videoPlayer;
        float r1 = 0.0f;
        if (r0 != null) goto L5;
    L16:
        VideoPlayer r02 = this.roundPlayer;
        if (r02 != null) goto L19;
    L26:
        VideoPlayer r03 = this.audioPlayer;
        if (r03 != null) goto L29;
    L36:
        CollageLayoutView2 r04 = this.collage;
        if (r04 == null) goto L40;
        r04.setMuted(this.isMuted);
        return;
    L40:
        return;
    L29:
        if (this.isMuted == true) goto L35;
        StoryEntry r12 = this.entry;
        if (r12 == null) goto L34;
        r1 = r12.audioVolume;
        goto L35
    L34:
        r1 = 1.0f;
    L35:
        r03.setVolume(r1);
        goto L36
    L19:
        if (this.isMuted == false) goto L21;
        float r3 = 0.0f;
    L25:
        r02.setVolume(r3);
        goto L26
    L21:
        StoryEntry r32 = this.entry;
        if (r32 == null) goto L24;
        r3 = r32.roundVolume;
        goto L25
    L24:
        r3 = 1.0f;
        goto L25
    L5:
        if (this.isMuted == true) goto L14;
        StoryEntry r33 = this.entry;
        if (r33 != null) goto L9;
    L11:
        if (r33 == null) goto L13;
        float r34 = r33.videoVolume;
    L15:
        r0.setVolume(r34);
        goto L16
    L13:
        r34 = 1.0f;
        goto L15
    L9:
        if (r33.muted == false) goto L11;
    L14:
        r34 = 0.0f;
        goto L15
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r15) {
        drawBackground(r15);
        CropEditor r0 = this.cropEditorDrawing;
        if (r0 == null) goto L6;
        r0.contentView.drawImage(r15, true);
    L41:
        super.dispatchDraw(r15);
        return;
    L6:
        if (this.draw == false) goto L41;
        if (this.entry == null) goto L41;
        if (isCollage() == true) goto L41;
        AnimatedFloat r02 = this.thumbAlpha;
        if (this.bitmap != null) goto L14;
        boolean r2 = true;
    L15:
        float r03 = r02.set(r2);
        if (this.thumbBitmap == null) goto L30;
        if (r03 <= 0.0f) goto L30;
        r15.save();
        r15.scale(getWidth() / this.entry.resultWidth, getHeight() / this.entry.resultHeight);
        r15.concat(this.entry.matrix);
        if (this.entry.crop == null) goto L28;
        r15.translate(r7.width / 2.0f, r7.height / 2.0f);
        r15.rotate(-this.entry.orientation);
        StoryEntry r7 = this.entry;
        int r8 = r7.width;
        int r9 = r7.height;
        int r10 = r7.orientation;
        MediaController.CropState r72 = r7.crop;
        if ((((r10 + r72.transformRotation) / 90) % 2) != 1) goto L24;
        r9 = r8;
        r8 = r9;
    L24:
        float r11 = r72.cropPw;
        float r73 = r72.cropPh;
        float r82 = r8;
        float r92 = r9;
        r15.clipRect(((-r8) * r11) / 2.0f, ((-r9) * r73) / 2.0f, (r11 * r82) / 2.0f, (r73 * r92) / 2.0f);
        float r74 = this.entry.crop.cropScale;
        r15.scale(r74, r74);
        MediaController.CropState r75 = this.entry.crop;
        r15.translate(r75.cropPx * r82, r75.cropPy * r92);
        r15.rotate(this.entry.crop.cropRotate + r7.transformRotation);
        if (this.entry.crop.mirrored == false) goto L27;
        r15.scale(-1.0f, 1.0f);
    L27:
        r15.rotate(this.entry.orientation);
        StoryEntry r76 = this.entry;
        r15.translate((-r76.width) / 2.0f, (-r76.height) / 2.0f);
    L28:
        r15.scale(this.entry.width / this.thumbBitmap.getWidth(), this.entry.height / this.thumbBitmap.getHeight());
        this.bitmapPaint.setAlpha(255);
        r15.drawBitmap(this.thumbBitmap, 0.0f, 0.0f, this.bitmapPaint);
        r15.restore();
    L30:
        if (this.bitmap == null) goto L41;
        r15.save();
        r15.scale(getWidth() / this.entry.resultWidth, getHeight() / this.entry.resultHeight);
        r15.concat(this.entry.matrix);
        if (this.entry.crop == null) goto L40;
        r15.translate(r2.width / 2.0f, r2.height / 2.0f);
        r15.rotate(-this.entry.orientation);
        StoryEntry r22 = this.entry;
        int r77 = r22.width;
        int r83 = r22.height;
        int r93 = r22.orientation;
        MediaController.CropState r23 = r22.crop;
        if ((((r93 + r23.transformRotation) / 90) % 2) != 1) goto L36;
        r83 = r77;
        r77 = r83;
    L36:
        float r94 = r23.cropPw;
        float r24 = r23.cropPh;
        float r78 = r77;
        float r84 = r83;
        r15.clipRect(((-r77) * r94) / 2.0f, ((-r83) * r24) / 2.0f, (r94 * r78) / 2.0f, (r24 * r84) / 2.0f);
        float r1 = this.entry.crop.cropScale;
        r15.scale(r1, r1);
        MediaController.CropState r12 = this.entry.crop;
        r15.translate(r12.cropPx * r78, r12.cropPy * r84);
        r15.rotate(this.entry.crop.cropRotate + r1.transformRotation);
        if (this.entry.crop.mirrored == false) goto L39;
        r15.scale(-1.0f, 1.0f);
    L39:
        r15.rotate(this.entry.orientation);
        StoryEntry r13 = this.entry;
        r15.translate((-r13.width) / 2.0f, (-r13.height) / 2.0f);
    L40:
        this.bitmapPaint.setAlpha((int) ((1.0f - r03) * 255.0f));
        this.bitmapSrc.set(0, 0, this.bitmap.getWidth(), this.bitmap.getHeight());
        Rect r04 = this.bitmapDst;
        StoryEntry r14 = this.entry;
        r04.set(0, 0, r14.width, r14.height);
        r15.drawBitmap(this.bitmap, this.bitmapSrc, this.bitmapDst, this.bitmapPaint);
        r15.restore();
        goto L41
    L14:
        r2 = false;
        goto L15
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r4) {
        boolean r0 = touchEvent(r4);
        if (additionalTouchEvent(r4) == true) goto L7;
        if (r0 == true) goto L7;
        boolean r02 = false;
    L8:
        tapTouchEvent(r4);
        if (r02 == false) goto L15;
        if (r4.getPointerCount() > 1) goto L13;
        super.dispatchTouchEvent(r4);
    L13:
        return true;
    L15:
        return super.dispatchTouchEvent(r4);
    L7:
        r02 = true;
        goto L8
    }

    public void drawBackground(Canvas r8) {
        if (this.wallpaperDrawable == null) goto L21;
        float r1 = 0.0f;
        if (this.drawForThemeToggle == false) goto L7;
        Path r0 = new Path();
        RectF r2 = AndroidUtilities.rectTmp;
        r2.set(0.0f, 0.0f, getWidth(), getHeight());
        r0.addRoundRect(r2, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), Path.Direction.CW);
        r8.save();
        r8.clipPath(r0);
    L7:
        Drawable r02 = this.wallpaperDrawable;
        if ((r02 instanceof MotionBackgroundDrawable) == true) goto L10;
    L11:
        r1 = this.wallpaperDrawableCrossfade.set(1.0f);
    L12:
        Drawable r03 = this.lastWallpaperDrawable;
        if (r03 != null) goto L15;
    L17:
        this.wallpaperDrawable.setAlpha((int) (r1 * 255.0f));
        StoryEntry.drawBackgroundDrawable(r8, this.wallpaperDrawable, getWidth(), getHeight());
        if (this.drawForThemeToggle == false) goto L23;
        r8.restore();
        return;
    L23:
        return;
    L15:
        if (r1 >= 1.0f) goto L17;
        r03.setAlpha((int) ((1.0f - r1) * 255.0f));
        StoryEntry.drawBackgroundDrawable(r8, this.lastWallpaperDrawable, getWidth(), getHeight());
        goto L17
    L10:
        if (((MotionBackgroundDrawable) r02).getPatternBitmap() == null) goto L12;
    L21:
        r8.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.gradientPaint);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r9, View r10, long r11) {
        VideoEditTextureView r0 = this.textureView;
        if (r10 != r0) goto L10;
        StoryEntry r1 = this.entry;
        if (r1 == null) goto L10;
        if (r1.isRepostMessage == false) goto L10;
        return false;
    L10:
        if (r10 != r0) goto L12;
    L13:
        StoryEntry r02 = this.entry;
        if (r02 != null) goto L16;
    L26:
        return super.drawChild(r9, r10, r11);
    L16:
        if (r02.crop == null) goto L26;
        r9.save();
        r9.scale(getWidth() / this.entry.resultWidth, getHeight() / this.entry.resultHeight);
        r9.concat(this.entry.matrix);
        if (this.entry.crop == null) goto L23;
        r9.translate(r0.width / 2.0f, r0.height / 2.0f);
        r9.rotate(-this.entry.orientation);
        StoryEntry r03 = this.entry;
        int r12 = r03.width;
        int r3 = r03.height;
        int r4 = r03.orientation;
        MediaController.CropState r04 = r03.crop;
        if ((((r4 + r04.transformRotation) / 90) % 2) != 1) goto L22;
        r3 = r12;
        r12 = r3;
    L22:
        float r5 = r04.cropPw;
        float r05 = r04.cropPh;
        r9.clipRect(((-r12) * r5) / 2.0f, ((-r3) * r05) / 2.0f, (r12 * r5) / 2.0f, (r3 * r05) / 2.0f);
        r9.rotate(this.entry.orientation);
        StoryEntry r06 = this.entry;
        r9.translate((-r06.width) / 2.0f, (-r06.height) / 2.0f);
    L23:
        r9.concat(this.invertMatrix);
        r9.scale(1.0f / (getWidth() / this.entry.resultWidth), 1.0f / (getHeight() / this.entry.resultHeight));
        boolean r8 = super.drawChild(r9, r10, r11);
        r9.restore();
        return r8;
    L12:
        if (r10 != this.filterTextureView) goto L26;
        goto L13
    }

    public void drawContent(Canvas r5) {
        if (this.textureView == null) goto L7;
        r5.save();
        r5.scale(getContentWidth() / getWidth(), getContentHeight() / getHeight());
        r5.concat(this.transformBackMatrix);
        this.textureView.draw(r5);
        r5.restore();
        return;
    L7:
        if (this.bitmap != null) goto L9;
        return;
    L9:
        if (this.entry == null) goto L13;
        this.matrix.reset();
        this.matrix.preScale(this.entry.width / this.bitmap.getWidth(), this.entry.height / this.bitmap.getHeight());
        this.bitmapPaint.setAlpha(255);
        r5.drawBitmap(this.bitmap, this.matrix, this.bitmapPaint);
        return;
    }

    public int getContentHeight() {
        StoryEntry r0 = this.entry;
        if (r0 != null) goto L7;
        return 1;
    L7:
        return r0.height;
    }

    public int getContentWidth() {
        StoryEntry r0 = this.entry;
        if (r0 != null) goto L7;
        return 1;
    L7:
        return r0.width;
    }

    public void getCoverBitmap(Utilities.Callback<Bitmap> r10, View... r11) {
        int r2 = (int) (AndroidUtilities.dp(26.0f) * AndroidUtilities.density);
        int r3 = (int) (AndroidUtilities.dp(30.33f) * AndroidUtilities.density);
        int r4 = (int) (AndroidUtilities.dp(4.0f) * AndroidUtilities.density);
        Bitmap[] r5 = new Bitmap[r11.length];
        int r0 = 0;
    L4:
        if (r0 >= r11.length) goto L26;
        View r1 = r11[r0];
        if (r1 == null) goto L25;
        if (r1.getWidth() < 0) goto L25;
        if (r11[r0].getHeight() <= 0) goto L25;
        View r12 = r11[r0];
        if (r12 != this) goto L18;
        VideoEditTextureView r6 = this.textureView;
        if (r6 == null) goto L18;
        r5[r0] = r6.getBitmap();
    L18:
        if ((r12 instanceof TextureView) == false) goto L21;
        r5[r0] = ((TextureView) r12).getBitmap();
        goto L25
    L21:
        if ((r12 instanceof ViewGroup) == false) goto L25;
        if (((ViewGroup) r12).getChildCount() <= 0) goto L25;
        r5[r0] = Bitmap.createBitmap(r2, r3, Bitmap.Config.ARGB_8888);
        Canvas r13 = new Canvas(r5[r0]);
        r13.save();
        float r62 = Math.max(r2 / r11[r0].getWidth(), r3 / r11[r0].getHeight());
        r13.scale(r62, r62);
        r11[r0].draw(r13);
        r13.restore();
    L25:
        r0 = r0 + 1;
        goto L4
    L26:
        Utilities.globalQueue.postRunnable(new PreviewView$$ExternalSyntheticLambda11(r2, r3, r4, r5, r10));
    }

    public long getCurrentPosition() {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 != null) goto L5;
        VideoPlayer r02 = this.roundPlayer;
        if (r02 != null) goto L9;
        VideoPlayer r2 = this.audioPlayer;
        if (r2 != null) goto L13;
        return 0;
    L13:
        return r2.getCurrentPosition();
    L9:
        return r02.getCurrentPosition();
    L5:
        return r0.getCurrentPosition();
    }

    public long getDuration() {
        StoryEntry r0 = this.entry;
        if (r0 == null) goto L8;
        double r02 = r0.fileDuration;
        if (r02 < 0.0d) goto L8;
        return (long) (r02 * 1000.0d);
    L8:
        VideoPlayer r03 = this.videoPlayer;
        if (r03 != null) goto L11;
        return 1;
    L11:
        if (r03.getDuration() != (-9223372036854775807L)) goto L13;
        return 1;
    L13:
        return this.videoPlayer.getDuration();
    }

    public int getOrientation() {
        StoryEntry r0 = this.entry;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.orientation;
    }

    public Pair<Integer, Integer> getPaintSize() {
        if (this.entry != null) goto L7;
        return new Pair(1080, 1920);
    L7:
        return new Pair(Integer.valueOf(this.entry.resultWidth), Integer.valueOf(this.entry.resultHeight));
    }

    public Bitmap getPhotoBitmap() {
        return this.bitmap;
    }

    public VideoEditTextureView getTextureView() {
        return this.textureView;
    }

    public abstract void invalidateTextureViewHolder();

    public boolean isCollage() {
        if (this.collage == null) goto L10;
        StoryEntry r1 = this.entry;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.isCollage() == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean isPlaying() {
        return !this.pauseLinks.contains(-9982);
    }

    public void mute(boolean r1) {
        this.isMuted = r1;
        checkVolumes();
    }

    public abstract void onAudioChanged();

    public abstract void onEntityDraggedBottom(boolean r1);

    public abstract void onEntityDraggedTop(boolean r1);

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r2) {
        if (this.allowCropping == false) goto L7;
        touchEvent(r2);
        return true;
    L7:
        return super.onInterceptTouchEvent(r2);
    }

    public abstract void onRoundRemove();

    public abstract void onRoundSelectChange(boolean r1);

    public void play(boolean r2) {
        updatePauseReason(-9982, !r2);
    }

    public void preset(StoryEntry r4) {
        this.entry = r4;
        if (r4 != null) goto L7;
        setupImage(null);
        setupWallpaper(null, false);
        this.gradientPaint.setShader(null);
        setupAudio(null, false);
        setupRound(null, null, false);
        return;
    L7:
        if (r4.isVideo == false) goto L15;
        setupImage(r4);
        if (r4.gradientTopColor == 0) goto L11;
    L14:
        setupGradient();
    L16:
        applyMatrix();
        setupWallpaper(r4, false);
        setupAudio(r4, false);
        setupRound(r4, null, false);
        return;
    L11:
        if (r4.gradientBottomColor != 0) goto L14;
        r4.setupGradient(new PreviewView$$ExternalSyntheticLambda1(this));
        goto L16
    L15:
        setupImage(r4);
        setupGradient();
        goto L16
    }

    public long release() {
        VideoPlayer r0 = this.audioPlayer;
        if (r0 == null) goto L5;
        r0.pause();
        this.audioPlayer.releasePlayer(true);
        this.audioPlayer = null;
    L5:
        VideoPlayer r02 = this.roundPlayer;
        if (r02 == null) goto L8;
        long r3 = r02.getCurrentPosition();
        this.roundPlayer.pause();
        this.roundPlayer.releasePlayer(true);
        this.roundPlayer = null;
    L9:
        VideoPlayer r03 = this.videoPlayer;
        if (r03 == null) goto L13;
        long r32 = r03.getCurrentPosition();
        this.videoPlayer.pause();
        this.videoPlayer.releasePlayer(true);
        this.videoPlayer = null;
        return r32;
    L13:
        return r3;
    L8:
        r3 = 0;
        goto L9
    }

    public void seek(long r1) {
        seekTo(r1);
        TimelineView r0 = this.timelineView;
        if (r0 == null) goto L6;
        r0.setProgress(0);
        return;
    }

    public void seekTo(long r6, boolean r8) {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L6;
        r0.seekTo(r6, r8);
    L14:
        updateAudioPlayer(true);
        updateRoundPlayer(true);
        if (r8 == true) goto L17;
        return;
    L17:
        if (this.slowerSeekScheduled == true) goto L19;
    L20:
        this.slowerSeekScheduled = true;
        AndroidUtilities.cancelRunOnUIThread(this.slowerSeek);
        AndroidUtilities.runOnUIThread(this.slowerSeek, 60);
    L21:
        this.finalSeekPosition = r6;
        return;
    L19:
        if (Math.abs(this.finalSeekPosition - r6) <= 450) goto L21;
    L6:
        if (isCollage() == false) goto L8;
        this.collage.seekTo(r6, r8);
        goto L14
    L8:
        VideoPlayer r02 = this.roundPlayer;
        if (r02 == null) goto L11;
        r02.seekTo(r6, r8);
        goto L14
    L11:
        VideoPlayer r03 = this.audioPlayer;
        if (r03 == null) goto L14;
        r03.seekTo(r6, r8);
        goto L14
    }

    public void set(StoryEntry r4) {
        set(r4, null, 0);
    }

    public void set(StoryEntry r4, Runnable r5, long r6) {
        this.entry = r4;
        if (r4 != null) goto L7;
        setupVideoPlayer(null, r5, r6);
        setupImage(null);
        setupCollage(null);
        setupWallpaper(null, false);
        this.gradientPaint.setShader(null);
        setupAudio(null, false);
        setupRound(null, null, false);
        return;
    L7:
        if (r4.isCollage() == false) goto L10;
        setupImage(null);
        setupVideoPlayer(null, r5, r6);
        setupCollage(r4);
    L19:
        applyMatrix();
        setupWallpaper(r4, false);
        setupAudio(r4, false);
        setupRound(r4, null, false);
        return;
    L10:
        if (r4.isVideo == false) goto L18;
        setupImage(r4);
        setupCollage(null);
        setupVideoPlayer(r4, r5, r6);
        if (r4.gradientTopColor == 0) goto L14;
    L17:
        setupGradient();
        goto L19
    L14:
        if (r4.gradientBottomColor != 0) goto L17;
        r4.setupGradient(new PreviewView$$ExternalSyntheticLambda1(this));
        goto L19
    L18:
        setupCollage(null);
        setupVideoPlayer(null, r5, 0);
        setupImage(r4);
        setupGradient();
        goto L19
    }

    public void setAllowCropping(boolean r1) {
        this.allowCropping = r1;
    }

    public void setCollageView(CollageLayoutView2 r1) {
        this.collage = r1;
    }

    public void setCropEditorDrawing(CropEditor r2) {
        if (this.cropEditorDrawing == r2) goto L6;
        this.cropEditorDrawing = r2;
        invalidate();
        return;
    }

    public void setDraw(boolean r1) {
        this.draw = r1;
        invalidate();
    }

    public void setFilterTextureView(TextureView r2, PhotoFilterView r3) {
        TextureView r0 = this.filterTextureView;
        if (r0 == null) goto L5;
        removeView(r0);
        this.filterTextureView = null;
    L5:
        this.photoFilterView = r3;
        this.filterTextureView = r2;
        if (r3 == null) goto L8;
        r3.updateUiBlurGradient(this.gradientTop, this.gradientBottom);
    L8:
        TextureView r22 = this.filterTextureView;
        if (r22 == null) goto L12;
        addView(r22);
        return;
    }

    public void setOnTapListener(Runnable r1) {
        this.onTap = r1;
    }

    public void setVideoTimelineView(TimelineView r2) {
        this.timelineView = r2;
        if (r2 == null) goto L6;
        r2.setDelegate(new AnonymousClass2(this));
        return;
    }

    @Override // android.view.View
    public void setVisibility(int r2) {
        super.setVisibility(r2);
        if (r2 != 8) goto L6;
        set(null);
        return;
    }

    public void setupAudio(MessageObject r11, boolean r12) {
        StoryEntry r0 = this.entry;
        if (r0 == null) goto L65;
        int r1 = 1;
        r0.editedMedia = true;
        float r3 = 1.0f;
        if (r11 != null) goto L7;
    L64:
        r0.audioPath = null;
        r0.audioDocument = null;
        r0.audioAuthor = null;
        r0.audioTitle = null;
        r0.audioOffset = 0;
        r0.audioDuration = 0;
        r0.audioLeft = 0.0f;
        r0.audioRight = 1.0f;
        goto L65
    L7:
        if (r11.messageOwner == null) goto L64;
        TLRPC.Document r02 = r11.getDocument();
        if (r02 != null) goto L12;
    L14:
        int r8 = 0;
        if (TextUtils.isEmpty(r11.messageOwner.attachPath) == true) goto L17;
        this.entry.audioPath = r11.messageOwner.attachPath;
    L28:
        StoryEntry r112 = this.entry;
        r112.audioAuthor = null;
        r112.audioTitle = null;
        if (r02 == null) goto L41;
        ArrayList<TLRPC.DocumentAttribute> r113 = r02.attributes;
        int r03 = r113.size();
    L31:
        if (r8 >= r03) goto L41;
        TLRPC.DocumentAttribute r6 = r113.get(r8);
        r8 = r8 + 1;
        TLRPC.DocumentAttribute r62 = r6;
        if ((r62 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L34;
        if ((r62 instanceof TLRPC.TL_documentAttributeFilename) == false) goto L31;
        this.entry.audioTitle = r62.file_name;
        goto L31
    L34:
        this.entry.audioAuthor = r62.performer;
        if (TextUtils.isEmpty(r62.title) == true) goto L37;
        this.entry.audioTitle = r62.title;
    L37:
        this.entry.audioDuration = (long) (r62.duration * 1000.0d);
    L41:
        StoryEntry r114 = this.entry;
        r114.audioOffset = 0;
        if (r114.isVideo == false) goto L44;
        r114.audioOffset = (long) (r114.left * getDuration());
    L44:
        this.entry.audioLeft = 0.0f;
        if (isCollage() == true) goto L47;
    L49:
        StoryEntry r115 = this.entry;
        if (r115.isVideo == false) goto L52;
        long r63 = getDuration();
    L53:
        TimelineView r116 = this.timelineView;
        if (r116 == null) goto L57;
        r1 = r116.getMaxCount();
    L57:
        StoryEntry r117 = this.entry;
        if (r117.audioDuration == 0) goto L61;
        r3 = Math.min(1.0f, Math.min(r63, r1 * 59000) / this.entry.audioDuration);
    L61:
        r117.audioRight = r3;
        goto L65
    L52:
        r63 = r115.audioDuration;
        goto L53
    L47:
        if (this.collage.hasVideo() == false) goto L49;
        r63 = this.collage.getDuration();
        goto L53
    L17:
        File r7 = FileLoader.getInstance(r11.currentAccount).getPathToAttach(r02, null, false, true);
        if (r7 != null) goto L20;
    L21:
        r7 = FileLoader.getInstance(r11.currentAccount).getPathToAttach(r02, null, true, true);
        if (r7 != null) goto L24;
    L62:
        StoryEntry r10 = this.entry;
        r10.audioPath = null;
        r10.audioDocument = null;
        r10.audioAuthor = null;
        r10.audioTitle = null;
        r10.audioOffset = 0;
        r10.audioDuration = 0;
        r10.audioLeft = 0.0f;
        r10.audioRight = 1.0f;
        return;
    L24:
        if (r7.exists() == false) goto L62;
        this.entry.audioPath = r7.getAbsolutePath();
    L27:
        this.entry.audioPath = r7.getAbsolutePath();
        goto L28
    L20:
        if (r7.exists() == true) goto L27;
    L12:
        if (r02.id == 0) goto L14;
        this.entry.audioDocument = new TLRPC.TL_inputDocument();
        TLRPC.InputDocument r72 = this.entry.audioDocument;
        r72.id = r02.id;
        r72.file_reference = r02.file_reference;
        r72.access_hash = r02.access_hash;
    L65:
        setupAudio(this.entry, r12);
    }

    public void setupAudio(StoryEntry r17, boolean r18) {
        VideoPlayer r2 = this.audioPlayer;
        if (r2 == null) goto L5;
        r2.pause();
        this.audioPlayer.releasePlayer(true);
        this.audioPlayer = null;
    L5:
        if (r17 != null) goto L7;
        return;
    L7:
        TimelineView r4 = this.timelineView;
        if (r4 == null) goto L11;
        r4.setAudio(r17.audioPath, r17.audioAuthor, r17.audioTitle, r17.audioDuration, r17.audioOffset, r17.audioLeft, r17.audioRight, r17.audioVolume, r18);
    L11:
        if (r17.audioPath == null) goto L18;
        VideoPlayer r22 = new VideoPlayer();
        this.audioPlayer = r22;
        r22.allowMultipleInstances = true;
        r22.setDelegate(new AnonymousClass1(this));
        this.audioPlayer.preparePlayer(Uri.fromFile(new File(r17.audioPath)), "other");
        checkVolumes();
        if (this.videoPlayer != null) goto L15;
    L17:
        updateAudioPlayer(true);
        goto L18
    L15:
        if (getDuration() <= 0) goto L17;
        long r1 = (long) (r17.left * getDuration());
        this.videoPlayer.seekTo(r1);
        this.timelineView.setProgress(r1);
    L18:
        onAudioChanged();
    }

    public void setupRound(StoryEntry r13, RoundView r14, boolean r15) {
        if (r13 != null) goto L5;
    L12:
        VideoPlayer r132 = this.roundPlayer;
        if (r132 == null) goto L15;
        r132.pause();
        this.roundPlayer.releasePlayer(true);
        this.roundPlayer = null;
    L15:
        TimelineView r133 = this.timelineView;
        if (r133 == null) goto L18;
        r133.setRoundNull(r15);
    L18:
        this.roundView = null;
        AndroidUtilities.cancelRunOnUIThread(this.updateProgressRunnable);
        return;
    L5:
        if (r13.round == null) goto L12;
        VideoPlayer r2 = this.roundPlayer;
        if (r2 == null) goto L10;
        r2.releasePlayer(true);
        this.roundPlayer = null;
    L10:
        VideoPlayer r0 = new VideoPlayer();
        this.roundPlayer = r0;
        r0.allowMultipleInstances = true;
        r0.setDelegate(new AnonymousClass4(this));
        Uri r02 = Uri.fromFile(r13.round);
        this.roundPlayer.preparePlayer(r02, "other");
        checkVolumes();
        attachRoundView(r14);
        this.timelineView.setRound(r13.round.getAbsolutePath(), r13.roundDuration, r13.roundOffset, r13.roundLeft, r13.roundRight, r13.roundVolume, r15);
        updateRoundPlayer(true);
    }

    public void setupVideoPlayer(StoryEntry r12, Runnable r13, long r14) {
        float r0 = 0.0f;
        BlurringShader.BlurManager r1 = null;
        if (r12 != null) goto L5;
    L48:
        VideoPlayer r122 = this.videoPlayer;
        if (r122 == null) goto L51;
        r122.pause();
        this.videoPlayer.releasePlayer(true);
        this.videoPlayer = null;
    L51:
        TextureViewHolder r123 = this.textureViewHolder;
        if (r123 != null) goto L54;
    L56:
        VideoEditTextureView r124 = this.textureView;
        if (r124 == null) goto L59;
        r124.clearAnimation();
        this.textureView.animate().alpha(0.0f).withEndAction(new PreviewView$$ExternalSyntheticLambda7(this)).start();
    L59:
        TimelineView r02 = this.timelineView;
        if (r02 == null) goto L62;
        r02.setVideo(false, null, 1, 0.0f);
    L62:
        AndroidUtilities.cancelRunOnUIThread(this.updateProgressRunnable);
        if (r13 == null) goto L68;
        AndroidUtilities.runOnUIThread(r13);
        return;
    L68:
        return;
    L54:
        if (r123.active == false) goto L56;
        r123.setTextureView(null);
        goto L59
    L5:
        if (r12.isCollage() == true) goto L48;
        VideoPlayer r3 = this.videoPlayer;
        if (r3 == null) goto L10;
        r3.releasePlayer(true);
        this.videoPlayer = null;
    L10:
        VideoPlayer r5 = new VideoPlayer();
        this.videoPlayer = r5;
        r5.allowMultipleInstances = true;
        r5.setDelegate(new AnonymousClass3(this, r12, new Runnable[]{r13}));
        VideoEditTextureView r32 = this.textureView;
        if (r32 == null) goto L13;
        r32.clearAnimation();
        this.textureView.release();
        removeView(this.textureView);
        this.textureView = null;
    L13:
        this.textureView = new VideoEditTextureView(getContext(), this.videoPlayer);
        this.blurManager.resetBitmap();
        VideoEditTextureView r33 = this.textureView;
        if (r12.isRepostMessage == true) goto L17;
        r1 = this.blurManager;
    L17:
        r33.updateUiBlurManager(r1);
        this.textureView.setOpaque(false);
        applyMatrix();
        TextureViewHolder r15 = this.textureViewHolder;
        if (r15 != null) goto L20;
    L22:
        VideoEditTextureView r16 = this.textureView;
        if (r13 == null) goto L26;
        r0 = 1.0f;
    L26:
        r16.setAlpha(r0);
        addView(this.textureView, LayoutHelper.createFrame(-2, -2, 51));
    L27:
        r12.detectHDR(new PreviewView$$ExternalSyntheticLambda8(this));
        this.videoPlayer.preparePlayer(Uri.fromFile(r12.getOriginalFile()), "other");
        this.videoPlayer.setPlayWhenReady(this.pauseLinks.isEmpty());
        this.videoPlayer.setLooping(true);
        if (r12.isEditSaved == false) goto L31;
        r14 = (long) ((r12.left * r12.duration) + r14);
    L31:
        if (r14 <= 0) goto L33;
        this.videoPlayer.seekTo(r14);
    L33:
        checkVolumes();
        updateAudioPlayer(true);
        if (r12.isRepostMessage == false) goto L42;
        ArrayList<MessageObject> r03 = r12.messageObjects;
        if (r03 == null) goto L42;
        if (r03.size() != 1) goto L42;
        if (r12.messageObjects.get(0).type != 5) goto L42;
        boolean r6 = true;
    L43:
        this.timelineView.setVideo(r6, r12.getOriginalFile().getAbsolutePath(), getDuration(), r12.videoVolume);
        this.timelineView.setVideoLeft(r12.left);
        this.timelineView.setVideoRight(r12.right);
        TimelineView r11 = this.timelineView;
        if (r11 == null) goto L66;
        if (r14 <= 0) goto L67;
        r11.setProgress(r14);
        return;
    L67:
        return;
    L66:
        return;
    L42:
        r6 = false;
        goto L43
    L20:
        if (r15.active == false) goto L22;
        r15.setTextureView(this.textureView);
        goto L27
    }

    public void setupWallpaper(StoryEntry r7, boolean r8) {
        Drawable r0 = this.wallpaperDrawable;
        this.lastWallpaperDrawable = r0;
        if (r0 == null) goto L5;
        r0.setCallback(null);
    L5:
        if (r7 != null) goto L8;
        this.wallpaperDrawable = null;
        return;
    L8:
        long r2 = r7.backgroundWallpaperPeerId;
        String r02 = r7.backgroundWallpaperEmoticon;
        if (r02 == null) goto L12;
        Drawable r03 = getBackgroundDrawableFromTheme(r7.currentAccount, r02, r7.isDark);
        r7.backgroundDrawable = r03;
        this.wallpaperDrawable = r03;
    L15:
        if (this.lastWallpaperDrawable == this.wallpaperDrawable) goto L19;
        if (r8 == false) goto L18;
        this.wallpaperDrawableCrossfade.set(0.0f, true);
        goto L19
    L18:
        this.lastWallpaperDrawable = null;
    L19:
        Drawable r72 = this.wallpaperDrawable;
        if (r72 == null) goto L22;
        r72.setCallback(this);
    L22:
        BlurringShader.BlurManager r73 = this.blurManager;
        if (r73 == null) goto L38;
        Drawable r82 = this.wallpaperDrawable;
        if (r82 != null) goto L27;
        r73.setFallbackBlur(null, 0);
        goto L38
    L27:
        if ((r82 instanceof BitmapDrawable) == false) goto L29;
        r73.setFallbackBlur(((BitmapDrawable) r82).getBitmap(), 0);
        goto L38
    L29:
        int r74 = r82.getIntrinsicWidth();
        int r83 = this.wallpaperDrawable.getIntrinsicHeight();
        if (r74 <= 0) goto L32;
        if (r83 <= 0) goto L32;
    L33:
        float r1 = r74;
        float r5 = r83;
        float r3 = Math.max(100.0f / r1, 100.0f / r5);
        if (r3 <= 1.0f) goto L36;
        r74 = (int) (r1 * r3);
        r83 = (int) (r5 * r3);
    L36:
        Bitmap r12 = Bitmap.createBitmap(r74, r83, Bitmap.Config.ARGB_8888);
        this.wallpaperDrawable.setBounds(0, 0, r74, r83);
        this.wallpaperDrawable.draw(new Canvas(r12));
        this.blurManager.setFallbackBlur(r12, 0, true);
    L32:
        r74 = 1080;
        r83 = 1920;
    L38:
        invalidate();
        return;
    L12:
        if (r2 == Long.MIN_VALUE) goto L40;
        Drawable r04 = getBackgroundDrawable(this.wallpaperDrawable, r7.currentAccount, r2, r7.isDark);
        r7.backgroundDrawable = r04;
        this.wallpaperDrawable = r04;
        goto L15
    L40:
        this.wallpaperDrawable = null;
    }

    public void updateAudioPlayer(boolean r14) {
        if (this.audioPlayer != null) goto L5;
        return;
    L5:
        if (this.entry == null) goto L61;
        boolean r1 = true;
        if (this.videoPlayer != null) goto L26;
        if (this.roundPlayer != null) goto L26;
        if (isCollage() == true) goto L26;
        this.audioPlayer.setPlayWhenReady(this.pauseLinks.isEmpty());
        this.audioPlayer.setLooping(true);
        long r0 = this.audioPlayer.getCurrentPosition();
        if (r14 == true) goto L16;
        return;
    L16:
        if (this.audioPlayer.getDuration() == (-9223372036854775807L)) goto L56;
        float r142 = r0 / this.audioPlayer.getDuration();
        StoryEntry r02 = this.entry;
        if (r142 < r02.audioLeft) goto L22;
        if (r142 > r02.audioRight) goto L22;
        return;
    L22:
        if ((System.currentTimeMillis() - this.seekedLastTime) <= 500) goto L58;
        this.seekedLastTime = System.currentTimeMillis();
        this.audioPlayer.seekTo(-this.entry.audioOffset);
        return;
    L58:
        return;
    L56:
        return;
    L26:
        if (isCollage() == false) goto L28;
        long r2 = this.collage.getPositionWithOffset();
        boolean r03 = this.collage.isPlaying();
    L33:
        StoryEntry r4 = this.entry;
        float r5 = r4.audioRight;
        float r6 = r4.audioLeft;
        long r7 = r4.audioDuration;
        long r9 = (long) ((r5 - r6) * r7);
        if (r03 == false) goto L40;
        long r11 = r4.audioOffset;
        if (r2 < r11) goto L40;
        if (r2 > (r11 + r9)) goto L40;
    L41:
        long r22 = r2 - (r4.audioOffset - ((long) (r6 * r7)));
        if (this.audioPlayer.isPlaying() == r1) goto L45;
        this.audioPlayer.setPlayWhenReady(r1);
        this.audioPlayer.seekTo(r22);
        return;
    L45:
        if (r14 == false) goto L59;
        long r04 = Math.abs(this.audioPlayer.getCurrentPosition() - r22);
        if (isCollage() == false) goto L49;
        int r143 = 300;
    L51:
        if (r04 <= r143) goto L60;
        this.audioPlayer.seekTo(r22);
        return;
    L60:
        return;
    L49:
        r143 = 120;
        goto L51
    L59:
        return;
    L40:
        r1 = false;
        goto L41
    L28:
        VideoPlayer r05 = this.videoPlayer;
        if (r05 != null) goto L32;
        r05 = this.roundPlayer;
    L32:
        r2 = r05.getCurrentPosition();
        r03 = r05.isPlaying();
        goto L33
    }

    public void updatePauseReason(int r2, boolean r3) {
        HashSet<Integer> r0 = this.pauseLinks;
        if (r3 == false) goto L5;
        r0.add(Integer.valueOf(r2));
    L6:
        VideoPlayer r22 = this.videoPlayer;
        if (r22 == null) goto L9;
        r22.setPlayWhenReady(this.pauseLinks.isEmpty());
    L9:
        CollageLayoutView2 r23 = this.collage;
        if (r23 == null) goto L12;
        r23.setPlaying(this.pauseLinks.isEmpty());
    L12:
        updateAudioPlayer(true);
        updateRoundPlayer(true);
        return;
    L5:
        r0.remove(Integer.valueOf(r2));
        goto L6
    }

    public void updateRoundPlayer(boolean r14) {
        if (this.roundPlayer != null) goto L5;
        return;
    L5:
        if (this.entry == null) goto L62;
        boolean r1 = false;
        if (this.videoPlayer != null) goto L27;
        if (isCollage() == true) goto L27;
        this.roundPlayer.setPlayWhenReady(this.pauseLinks.isEmpty());
        this.roundPlayer.setLooping(true);
        RoundView r0 = this.roundView;
        if (r0 == null) goto L14;
        r0.setShown(true, false);
    L14:
        long r02 = this.roundPlayer.getCurrentPosition();
        if (r14 == true) goto L17;
        return;
    L17:
        if (this.roundPlayer.getDuration() == (-9223372036854775807L)) goto L57;
        float r142 = r02 / this.roundPlayer.getDuration();
        StoryEntry r03 = this.entry;
        if (r142 < r03.roundLeft) goto L23;
        if (r142 > r03.roundRight) goto L23;
        return;
    L23:
        if ((System.currentTimeMillis() - this.seekedLastTime) <= 500) goto L59;
        this.seekedLastTime = System.currentTimeMillis();
        this.roundPlayer.seekTo(-this.entry.roundOffset);
        return;
    L59:
        return;
    L57:
        return;
    L27:
        if (isCollage() == false) goto L29;
        long r3 = this.collage.getPositionWithOffset();
        boolean r04 = this.collage.isPlaying();
    L30:
        StoryEntry r5 = this.entry;
        float r6 = r5.roundRight;
        float r7 = r5.roundLeft;
        long r8 = r5.roundDuration;
        long r10 = (long) ((r6 - r7) * r8);
        long r52 = r5.roundOffset;
        if (r3 >= r52) goto L33;
    L35:
        boolean r102 = false;
    L36:
        if (r04 == false) goto L39;
        if (r102 == false) goto L39;
        r1 = true;
    L39:
        long r32 = (r3 - r52) + ((long) (r7 * r8));
        RoundView r05 = this.roundView;
        if (r05 == null) goto L43;
        r05.setShown(r102, true);
    L43:
        if (this.roundPlayer.isPlaying() == r1) goto L46;
        this.roundPlayer.setPlayWhenReady(r1);
        this.roundPlayer.seekTo(r32);
        return;
    L46:
        if (r14 == false) goto L60;
        long r06 = Math.abs(this.roundPlayer.getCurrentPosition() - r32);
        if (isCollage() == false) goto L50;
        int r143 = 300;
    L52:
        if (r06 <= r143) goto L61;
        this.roundPlayer.seekTo(r32);
        return;
    L61:
        return;
    L50:
        r143 = 120;
        goto L52
    L60:
        return;
    L33:
        if (r3 > (r10 + r52)) goto L35;
        r102 = true;
        goto L36
    L29:
        r3 = this.videoPlayer.getCurrentPosition();
        r04 = this.videoPlayer.isPlaying();
        goto L30
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (this.wallpaperDrawable != r2) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public void whenError(Runnable r1) {
        this.onErrorListener = r1;
    }
}
