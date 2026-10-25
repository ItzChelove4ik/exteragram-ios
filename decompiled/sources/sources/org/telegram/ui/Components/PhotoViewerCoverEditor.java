package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.GallerySheet;
import org.telegram.ui.Stories.recorder.TimelineView;

/* loaded from: classes7.dex */
public class PhotoViewerCoverEditor extends FrameLayout {
    public ActionBar actionBar;
    private float aspectRatio;
    public ButtonWithCounterView button;
    public Runnable close;
    private GallerySheet gallerySheet;
    private Utilities.Callback<MediaController.PhotoEntry> onGalleryListener;
    public EditCoverButton openGalleryButton;
    private long time;
    public TimelineView timelineView;
    private VideoPlayer videoPlayer;

    /* renamed from: org.telegram.ui.Components.PhotoViewerCoverEditor$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PhotoViewerCoverEditor this$0;

        public AnonymousClass1(PhotoViewerCoverEditor r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L8;
            Runnable r1 = this.this$0.close;
            if (r1 == null) goto L9;
            AndroidUtilities.runOnUIThread(r1);
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.PhotoViewerCoverEditor$2, reason: invalid class name */
    public class AnonymousClass2 implements TimelineView.TimelineDelegate {
        private Runnable betterSeek;
        final /* synthetic */ PhotoViewerCoverEditor this$0;

        public static /* synthetic */ void $r8$lambda$pp9rz38SpA1TL08o4rSi_XOmI9Y(AnonymousClass2 r0) {
            r0.lambda$$0();
        }

        public AnonymousClass2(PhotoViewerCoverEditor r1) {
            this.this$0 = r1;
            this.betterSeek = new PhotoViewerCoverEditor$2$$ExternalSyntheticLambda0(this);
        }

        private /* synthetic */ void lambda$$0() {
            PhotoViewerCoverEditor.m11754$$Nest$fgetvideoPlayer(this.this$0).seekTo(PhotoViewerCoverEditor.m11753$$Nest$fgettime(this.this$0), false);
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onVideoLeftChange(boolean r6, float r7) {
            if (PhotoViewerCoverEditor.m11754$$Nest$fgetvideoPlayer(this.this$0) == null) goto L10;
            float r3 = 2.8f / Math.max(60, r0);
            PhotoViewerCoverEditor.m11755$$Nest$fputtime(this.this$0, (long) ((r7 + (r3 * (r7 / (1.0f - r3)))) * PhotoViewerCoverEditor.m11754$$Nest$fgetvideoPlayer(this.this$0).getDuration()));
            PhotoViewerCoverEditor.m11754$$Nest$fgetvideoPlayer(this.this$0).seekTo(PhotoViewerCoverEditor.m11753$$Nest$fgettime(this.this$0), !r6);
            if (r6 == true) goto L9;
            AndroidUtilities.cancelRunOnUIThread(this.betterSeek);
            AndroidUtilities.runOnUIThread(this.betterSeek, 120);
            return;
        L9:
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$IZS29R4A2GCxRcpg_YNxIiAsMss(PhotoViewerCoverEditor r0, Context r1, Theme.ResourcesProvider r2, View r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$qROWXGyO-0kgVd19ct9hE_iHhWc, reason: not valid java name */
    public static /* synthetic */ void m11752$r8$lambda$qROWXGyO0kgVd19ct9hE_iHhWc(PhotoViewerCoverEditor r0) {
        r0.lambda$new$0();
    }

    /* renamed from: -$$Nest$fgettime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m11753$$Nest$fgettime(PhotoViewerCoverEditor r2) {
        return r2.time;
    }

    /* renamed from: -$$Nest$fgetvideoPlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoPlayer m11754$$Nest$fgetvideoPlayer(PhotoViewerCoverEditor r0) {
        return r0.videoPlayer;
    }

    /* renamed from: -$$Nest$fputtime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11755$$Nest$fputtime(PhotoViewerCoverEditor r0, long r1) {
        r0.time = r1;
    }

    public PhotoViewerCoverEditor(Context r15, Theme.ResourcesProvider r16, PhotoViewer r17, BlurringShader.BlurManager r18) {
        super(r15);
        this.time = -1;
        this.aspectRatio = 1.39f;
        ActionBar r0 = new ActionBar(r15, r16);
        this.actionBar = r0;
        r0.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setTitle(LocaleController.getString(R.string.EditorSetCoverTitle));
        this.actionBar.setItemsColor(-1, false);
        this.actionBar.setItemsBackgroundColor(587202559, false);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        addView(this.actionBar, LayoutHelper.createFrame(-1, -2, 55));
        TimelineView r02 = new TimelineView(r15, null, null, r16, r18);
        this.timelineView = r02;
        r02.setCover();
        addView(this.timelineView, LayoutHelper.createFrame(-1, TimelineView.heightDp(), 87, 0.0f, 0.0f, 0.0f, 74.0f));
        ButtonWithCounterView r03 = new ButtonWithCounterView(r15, r16);
        this.button = r03;
        r03.setText(LocaleController.getString(R.string.EditorSetCoverSave), false);
        this.button.setRound();
        addView(this.button, LayoutHelper.createFrame(-1, 48.0f, 87, 16.0f, 10.0f, 16.0f, 16.0f));
        EditCoverButton r04 = new EditCoverButton(r15, LocaleController.getString(R.string.EditorSetCoverGallery), true);
        this.openGalleryButton = r04;
        r04.setOnClickListener(new PhotoViewerCoverEditor$$ExternalSyntheticLambda0(this, r15, r16));
        addView(this.openGalleryButton, LayoutHelper.createFrame(-1, 32.0f, 87, 60.0f, 0.0f, 60.0f, 134.0f));
        this.timelineView.setDelegate(new AnonymousClass2(this));
    }

    private /* synthetic */ void lambda$new$0() {
        this.gallerySheet = null;
    }

    private /* synthetic */ void lambda$new$1(Context r7, Theme.ResourcesProvider r8, View r9) {
        if (this.gallerySheet != null) goto L5;
        GallerySheet r0 = new GallerySheet(r7, r8, LocaleController.getString(R.string.VideoChooseCover), true, this.aspectRatio);
        this.gallerySheet = r0;
        r0.setOnDismissListener(new PhotoViewerCoverEditor$$ExternalSyntheticLambda1(this));
        this.gallerySheet.setOnGalleryImage(this.onGalleryListener);
    L5:
        this.gallerySheet.show();
    }

    public void closeGallery() {
        GallerySheet r0 = this.gallerySheet;
        if (r0 == null) goto L6;
        r0.dismiss();
        this.gallerySheet = null;
        return;
    }

    public void destroy() {
        this.videoPlayer = null;
        this.timelineView.setVideo(false, null, 0, 0.0f);
    }

    public long getTime() {
        return this.time;
    }

    public void set(MediaController.PhotoEntry r11, VideoPlayer r12, Theme.ResourcesProvider r13) {
        this.button.updateColors(r13);
        int r132 = r11.width;
        if (r132 <= 0) goto L7;
        int r1 = r11.height;
        if (r1 <= 0) goto L7;
        this.aspectRatio = Utilities.clamp(r1 / r132, 1.39f, 0.85f);
    L8:
        this.videoPlayer = r12;
        long r0 = r11.coverSavedPosition;
        if (r0 < 0) goto L11;
        this.time = r0;
        r12.seekTo(r0, false);
    L12:
        this.timelineView.setVideo(false, r12.getCurrentUri().getPath(), r12.getDuration(), r12.player.getVolume());
        long r02 = r12.getDuration();
        float r133 = 2.8f / Math.max(60, r02);
        float r112 = (this.time / Math.max(1, r12.getDuration())) * (1.0f - r133);
        this.timelineView.setVideoLeft(r112);
        this.timelineView.setVideoRight(r112 + r133);
        this.timelineView.setCoverVideo(0, r02);
        this.timelineView.normalizeScrollByVideo();
        return;
    L11:
        this.time = r12.getCurrentPosition();
    L7:
        this.aspectRatio = 1.39f;
        goto L8
    }

    public void setOnClose(Runnable r1) {
        this.close = r1;
    }

    public void setOnGalleryImage(Utilities.Callback<MediaController.PhotoEntry> r1) {
        this.onGalleryListener = r1;
    }
}
