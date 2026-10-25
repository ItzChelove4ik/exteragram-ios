package org.telegram.ui.Components.poll.attached;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import java.io.File;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RadialProgress2;
import org.telegram.ui.Components.poll.PollAttachedMedia;

/* loaded from: classes7.dex */
public class PollAttachedMediaFile extends PollAttachedMedia {
    public final String ext;
    public final String name;
    public final String path;
    public final long size;
    private final StaticLayout staticLayout;
    private final Drawable thumb;
    private final TextPaint tp;
    public final Uri uri;

    /* renamed from: org.telegram.ui.Components.poll.attached.PollAttachedMediaFile$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ FileInfoDrawable val$fileInfoDrawable;

        public AnonymousClass1(FileInfoDrawable r1) {
            this.val$fileInfoDrawable = r1;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            this.val$fileInfoDrawable.radialProgress.onAttachedToWindow();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
            this.val$fileInfoDrawable.radialProgress.onDetachedFromWindow();
        }
    }

    public static class FileInfoDrawable extends Drawable {
        private int lastLayoutWidth;
        private final int lineSpacing;
        private final int paddingEnd;
        private final int paddingStart;
        private final int paddingTop;
        public RadialProgress2 radialProgress;
        private CharSequence subtitle;
        private StaticLayout subtitleLayout;
        private final TextPaint subtitlePaint;
        private CharSequence title;
        private StaticLayout titleLayout;
        private final TextPaint titlePaint;

        /* renamed from: -$$Nest$fgetsubtitlePaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextPaint m13735$$Nest$fgetsubtitlePaint(FileInfoDrawable r0) {
            return r0.subtitlePaint;
        }

        /* renamed from: -$$Nest$fgettitlePaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextPaint m13736$$Nest$fgettitlePaint(FileInfoDrawable r0) {
            return r0.titlePaint;
        }

        public FileInfoDrawable() {
            this.title = _UrlKt.FRAGMENT_ENCODE_SET;
            this.subtitle = _UrlKt.FRAGMENT_ENCODE_SET;
            this.lastLayoutWidth = -1;
            this.paddingStart = AndroidUtilities.dp(64.0f);
            this.paddingTop = AndroidUtilities.dp(10.66f);
            this.paddingEnd = AndroidUtilities.dp(12.0f);
            this.lineSpacing = AndroidUtilities.dp(4.0f);
            TextPaint r0 = new TextPaint(1);
            this.titlePaint = r0;
            r0.setTextSize(AndroidUtilities.dp(15.0f));
            r0.setTypeface(AndroidUtilities.bold());
            TextPaint r02 = new TextPaint(1);
            this.subtitlePaint = r02;
            r02.setTextSize(AndroidUtilities.dp(13.0f));
        }

        private void ensureLayout() {
            int r0 = getBounds().width();
            if (r0 > 0) goto L6;
            return;
        L6:
            if (r0 == this.lastLayoutWidth) goto L8;
        L12:
            this.lastLayoutWidth = r0;
            int r5 = (r0 - this.paddingStart) - this.paddingEnd;
            if (r5 > 0) goto L16;
            this.titleLayout = null;
            this.subtitleLayout = null;
            return;
        L16:
            float r2 = r5;
            CharSequence r3 = TextUtils.ellipsize(this.title, this.titlePaint, r2, TextUtils.TruncateAt.MIDDLE);
            CharSequence r02 = TextUtils.ellipsize(this.subtitle, this.subtitlePaint, r2, TextUtils.TruncateAt.END);
            TextPaint r4 = this.titlePaint;
            Layout.Alignment r6 = Layout.Alignment.ALIGN_NORMAL;
            this.titleLayout = new StaticLayout(r3, r4, r5, r6, 1.0f, 0.0f, false);
            this.subtitleLayout = new StaticLayout(r02, this.subtitlePaint, r5, r6, 1.0f, 0.0f, false);
            return;
        L8:
            if (this.titleLayout == null) goto L12;
            if (this.subtitleLayout == null) goto L12;
        }

        private static int getLineHeight(TextPaint r1) {
            Paint.FontMetricsInt r12 = r1.getFontMetricsInt();
            return r12.descent - r12.ascent;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r12) {
            ensureLayout();
            if (this.titleLayout != null) goto L5;
            return;
        L5:
            if (this.subtitleLayout == null) goto L10;
            Rect r0 = getBounds();
            float r1 = r0.left + this.paddingStart;
            float r2 = r0.top + this.paddingTop;
            this.radialProgress.setProgressRect(r0.left + AndroidUtilities.dp(10.0f), r0.top + AndroidUtilities.dp(9.0f), (r0.left + AndroidUtilities.dp(10.0f)) + AndroidUtilities.dp(42.0f), (r0.top + AndroidUtilities.dp(9.0f)) + AndroidUtilities.dp(42.0f));
            r12.save();
            r12.translate(r1, r2);
            this.titleLayout.draw(r12);
            r12.restore();
            r12.save();
            r12.translate(r1, (this.titleLayout.getHeight() + r2) + this.lineSpacing);
            this.subtitleLayout.draw(r12);
            r12.restore();
            this.radialProgress.draw(r12);
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return (((this.paddingTop + getLineHeight(this.titlePaint)) + this.lineSpacing) + getLineHeight(this.subtitlePaint)) + this.paddingTop;
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public void onBoundsChange(Rect r1) {
            super.onBoundsChange(r1);
            this.lastLayoutWidth = -1;
            this.titleLayout = null;
            this.subtitleLayout = null;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r4) {
            this.radialProgress.setOverrideAlpha(r4 / 255.0f);
            this.titlePaint.setAlpha(r4);
            this.subtitlePaint.setAlpha(r4);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r2) {
            this.titlePaint.setColorFilter(r2);
            this.subtitlePaint.setColorFilter(r2);
            invalidateSelf();
        }

        public void setText(CharSequence r2, CharSequence r3) {
            if (r2 != null) goto L6;
            r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            this.title = r2;
            if (r3 != null) goto L10;
            r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        L10:
            this.subtitle = r3;
            this.lastLayoutWidth = -1;
            this.titleLayout = null;
            this.subtitleLayout = null;
            invalidateSelf();
        }
    }

    public PollAttachedMediaFile(Uri r14) {
        this.path = null;
        this.uri = r14;
        String r142 = MediaController.getFileName(r14);
        String r1 = "?";
        if (r142 != null) goto L5;
        r142 = "?";
    L5:
        this.name = r142;
        String[] r2 = r142.split("\\.");
        if (r2.length <= 1) goto L8;
        r1 = r2[r2.length - 1];
    L8:
        this.ext = r1;
        this.size = 0;
        int r143 = AndroidUtilities.getThumbForNameOrMime(r142, r1, false);
        if (r143 == 0) goto L11;
        this.thumb = ApplicationLoader.applicationContext.getResources().getDrawable(r143);
    L13:
        if (TextUtils.isEmpty(r1) == true) goto L16;
        TextPaint r7 = new TextPaint(1);
        this.tp = r7;
        r7.setTextSize(AndroidUtilities.dp(13.0f));
        r7.setTypeface(AndroidUtilities.bold());
        r7.setColor(Theme.getColor(Theme.key_files_iconText));
        this.staticLayout = new StaticLayout(TextUtils.ellipsize(r1, r7, AndroidUtilities.dp(34.0f), TextUtils.TruncateAt.END), r7, AndroidUtilities.dp(34.0f), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        return;
    L16:
        this.tp = null;
        this.staticLayout = null;
        return;
    L11:
        this.thumb = null;
        goto L13
    }

    public PollAttachedMediaFile(String r13) {
        this.path = r13;
        this.uri = null;
        File r1 = new File(r13);
        long r2 = r1.length();     // Catch: Throwable -> L5
    L6:
        this.size = r2;
        String r132 = r1.getName();
        this.name = r132;
        String[] r12 = r132.split("\\.");
        if (r12.length <= 1) goto L9;
        String r14 = r12[r12.length - 1];
    L10:
        this.ext = r14;
        int r133 = AndroidUtilities.getThumbForNameOrMime(r132, r14, false);
        if (r133 == 0) goto L13;
        this.thumb = ApplicationLoader.applicationContext.getResources().getDrawable(r133);
    L15:
        if (TextUtils.isEmpty(r14) == true) goto L17;
        TextPaint r6 = new TextPaint(1);
        this.tp = r6;
        r6.setTextSize(AndroidUtilities.dp(13.0f));
        r6.setTypeface(AndroidUtilities.bold());
        r6.setColor(Theme.getColor(Theme.key_files_iconText));
        this.staticLayout = new StaticLayout(TextUtils.ellipsize(r14, r6, AndroidUtilities.dp(34.0f), TextUtils.TruncateAt.END), r6, AndroidUtilities.dp(34.0f), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        return;
    L17:
        this.tp = null;
        this.staticLayout = null;
        return;
    L13:
        this.thumb = null;
        goto L15
    L9:
        r14 = "?";
    L5:
        r2 = 0;
        goto L6
    }

    public static Drawable createMessagePreviewDrawable(View r6, String r7, String r8, TLRPC.Document r9, MessageObject r10) {
        FileInfoDrawable r0 = new FileInfoDrawable();
        FileInfoDrawable.m13736$$Nest$fgettitlePaint(r0).setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        FileInfoDrawable.m13735$$Nest$fgetsubtitlePaint(r0).setColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        RadialProgress2 r1 = new RadialProgress2(r6);
        r0.radialProgress = r1;
        r1.setCircleRadius(AndroidUtilities.dp(21.0f));
        r0.radialProgress.setColorKeys(Theme.key_chat_inLoader, Theme.key_chat_inLoaderSelected, Theme.key_chat_inMediaIcon, Theme.key_chat_inMediaIconSelected);
        if (MessageObject.isMusicDocument(r9) == true) goto L5;
        r0.radialProgress.setIcon(5, false, false);
    L13:
        r0.setText(r7, r8);
        r6.addOnAttachStateChangeListener(new AnonymousClass1(r0));
        return r0;
    L5:
        if (MessageObject.isDocumentHasThumb(r9) == false) goto L7;
        TLRPC.PhotoSize r12 = FileLoader.getClosestPhotoSizeWithSize(r9.thumbs, AndroidUtilities.dp(22.0f), true, null, false);
        TLRPC.PhotoSize r3 = FileLoader.getClosestPhotoSizeWithSize(r9.thumbs, AndroidUtilities.dp(44.0f), true, r12, true);
        r0.radialProgress.setImageOverlay(r3, r12, r9, r10);
    L11:
        r0.radialProgress.setIcon(0, false, false);
        goto L13
    L7:
        String r92 = MessageObject.getArtworkUrl(r9, true);
        boolean r102 = TextUtils.isEmpty(r92);
        RadialProgress2 r13 = r0.radialProgress;
        if (r102 == true) goto L10;
        r13.setImageOverlay(r92);
        goto L11
    L10:
        r13.setImageOverlay(null, null, null);
        goto L11
    }

    @Override // org.telegram.ui.Components.poll.PollAttachedMedia
    public void draw(Canvas r3, int r4, int r5) {
        Drawable r0 = this.thumb;
        if (r0 == null) goto L6;
        r0.setBounds(0, 0, r4, r5);
        this.thumb.draw(r3);
        r3.save();
        r3.translate((r4 - AndroidUtilities.dp(34.0f)) / 2.0f, AndroidUtilities.dp(15.0f));
        this.staticLayout.draw(r3);
        r3.restore();
        return;
    }
}
