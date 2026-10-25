package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class PhotoPickerPhotoCell extends FrameLayout {
    private Paint backgroundPaint;
    public CheckBox2 checkBox;
    public FrameLayout checkFrame;
    private int extraWidth;
    public BackupImageView imageView;
    private int itemWidth;
    public FrameLayout videoInfoContainer;
    public TextView videoTextView;

    /* renamed from: org.telegram.ui.Cells.PhotoPickerPhotoCell$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        private Paint paint;
        private Path path;
        float[] radii;
        private RectF rect;
        final /* synthetic */ PhotoPickerPhotoCell this$0;

        public AnonymousClass1(PhotoPickerPhotoCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.path = new Path();
            this.radii = new float[8];
            this.rect = new RectF();
            this.paint = new Paint(1);
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            this.rect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            float[] r0 = this.radii;
            r0[3] = 0.0f;
            r0[2] = 0.0f;
            r0[1] = 0.0f;
            r0[0] = 0.0f;
            float r1 = AndroidUtilities.dp(4.0f);
            r0[7] = r1;
            r0[6] = r1;
            r0[5] = r1;
            r0[4] = r1;
            this.path.reset();
            this.path.addRoundRect(this.rect, this.radii, Path.Direction.CW);
            this.path.close();
            this.paint.setColor(2130706432);
            r5.drawPath(this.path, this.paint);
        }
    }

    public PhotoPickerPhotoCell(Context r12) {
        super(r12);
        this.backgroundPaint = new Paint();
        setWillNotDraw(false);
        BackupImageView r1 = new BackupImageView(r12);
        this.imageView = r1;
        r1.setRoundRadius(AndroidUtilities.dp(4.0f));
        addView(this.imageView, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r13 = new FrameLayout(r12);
        this.checkFrame = r13;
        addView(r13, LayoutHelper.createFrame(42, 42, 53));
        AnonymousClass1 r14 = new AnonymousClass1(this, r12);
        this.videoInfoContainer = r14;
        r14.setWillNotDraw(false);
        this.videoInfoContainer.setPadding(AndroidUtilities.dp(3.0f), 0, AndroidUtilities.dp(3.0f), 0);
        addView(this.videoInfoContainer, LayoutHelper.createFrame(-1, 16, 83));
        ImageView r15 = new ImageView(r12);
        r15.setImageResource(R.drawable.ic_video);
        this.videoInfoContainer.addView(r15, LayoutHelper.createFrame(-2, -2, 19));
        TextView r16 = new TextView(r12);
        this.videoTextView = r16;
        r16.setTextColor(-1);
        this.videoTextView.setTextSize(1, 12.0f);
        this.videoTextView.setImportantForAccessibility(2);
        this.videoInfoContainer.addView(this.videoTextView, LayoutHelper.createFrame(-2, -2.0f, 19, 18.0f, -0.7f, 0.0f, 0.0f));
        CheckBox2 r17 = new CheckBox2(r12, 24);
        this.checkBox = r17;
        r17.setDrawBackgroundAsArc(11);
        this.checkBox.setColor(Theme.key_chat_attachCheckBoxBackground, Theme.key_chat_attachPhotoBackground, Theme.key_chat_attachCheckBoxCheck);
        addView(this.checkBox, LayoutHelper.createFrame(26, 26.0f, 51, 55.0f, 4.0f, 0.0f, 0.0f));
        this.checkBox.setVisibility(0);
        setFocusable(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateColors();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.itemWidth + this.extraWidth, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.itemWidth, TLObject.FLAG_30));
    }

    public void setChecked(int r1, boolean r2, boolean r3) {
        this.checkBox.setChecked(r1, r2, r3);
    }

    public void setImage(MediaController.PhotoEntry r8) {
        Drawable r0 = getResources().getDrawable(R.drawable.nophotos);
        String r1 = r8.thumbPath;
        if (r1 == null) goto L6;
        this.imageView.setImage(r1, null, r0);
        return;
    L6:
        String r12 = r8.path;
        BackupImageView r3 = this.imageView;
        if (r12 == null) goto L18;
        r3.setOrientation(r8.orientation, r8.invert, true);
        if (r8.isLivePhoto() == false) goto L12;
        this.videoInfoContainer.setVisibility(4);
        setContentDescription(LocaleController.getString(R.string.AttachLivePhoto));
        this.imageView.setImage("thumb://" + r8.imageId + ":" + r8.path, null, r0);
        return;
    L12:
        boolean r13 = r8.isVideo;
        FrameLayout r6 = this.videoInfoContainer;
        if (r13 == false) goto L16;
        r6.setVisibility(0);
        this.videoTextView.setText(AndroidUtilities.formatShortDuration(r8.duration));
        setContentDescription(LocaleController.getString(R.string.AttachVideo) + ", " + LocaleController.formatDuration(r8.duration));
        this.imageView.setImage("vthumb://" + r8.imageId + ":" + r8.path, null, r0);
        return;
    L16:
        r6.setVisibility(4);
        setContentDescription(LocaleController.getString(R.string.AttachPhoto));
        this.imageView.setImage("thumb://" + r8.imageId + ":" + r8.path, null, r0);
        return;
    L18:
        r3.setImageDrawable(r0);
    }

    public void setImage(MediaController.SearchImage r5) {
        Drawable r0 = getResources().getDrawable(R.drawable.nophotos);
        TLRPC.PhotoSize r1 = r5.thumbPhotoSize;
        if (r1 == null) goto L6;
        this.imageView.setImage(ImageLocation.getForPhoto(r1, r5.photo), null, r0, r5);
        return;
    L6:
        TLRPC.PhotoSize r12 = r5.photoSize;
        if (r12 == null) goto L10;
        this.imageView.setImage(ImageLocation.getForPhoto(r12, r5.photo), "80_80", r0, r5);
        return;
    L10:
        String r13 = r5.thumbPath;
        if (r13 == null) goto L14;
        this.imageView.setImage(r13, null, r0);
        return;
    L14:
        String r14 = r5.thumbUrl;
        if (r14 == null) goto L21;
        if (r14.length() <= 0) goto L21;
        this.imageView.setImage(r5.thumbUrl, null, r0);
        return;
    L21:
        if (MessageObject.isDocumentHasThumb(r5.document) == false) goto L24;
        TLRPC.PhotoSize r15 = FileLoader.getClosestPhotoSizeWithSize(r5.document.thumbs, 320);
        this.imageView.setImage(ImageLocation.getForDocument(r15, r5.document), null, r0, r5);
        return;
    L24:
        this.imageView.setImageDrawable(r0);
    }

    public void setItemWidth(int r1, int r2) {
        this.itemWidth = r1;
        this.extraWidth = r2;
        ((FrameLayout.LayoutParams) this.checkFrame.getLayoutParams()).rightMargin = r2;
        ((FrameLayout.LayoutParams) this.imageView.getLayoutParams()).rightMargin = r2;
        ((FrameLayout.LayoutParams) this.videoInfoContainer.getLayoutParams()).rightMargin = r2;
    }

    public void setNum(int r1) {
        this.checkBox.setNum(r1);
    }

    public void updateColors() {
        this.checkBox.setColor(Theme.key_chat_attachCheckBoxBackground, Theme.key_chat_attachPhotoBackground, Theme.key_chat_attachCheckBoxCheck);
    }
}
