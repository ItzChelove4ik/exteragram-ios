package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.timepicker.TimeModel;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class PhotoPickerAlbumsCell extends FrameLayout {
    private MediaController.AlbumEntry[] albumEntries;
    private AlbumView[] albumViews;
    private int albumsCount;
    private Paint backgroundPaint;
    private PhotoPickerAlbumsCellDelegate delegate;

    public class AlbumView extends FrameLayout {
        private TextView countTextView;
        private BackupImageView imageView;
        private TextView nameTextView;
        private View selector;
        final /* synthetic */ PhotoPickerAlbumsCell this$0;

        /* renamed from: -$$Nest$fgetcountTextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m6997$$Nest$fgetcountTextView(AlbumView r0) {
            return r0.countTextView;
        }

        /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BackupImageView m6998$$Nest$fgetimageView(AlbumView r0) {
            return r0.imageView;
        }

        /* renamed from: -$$Nest$fgetnameTextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m6999$$Nest$fgetnameTextView(AlbumView r0) {
            return r0.nameTextView;
        }

        public AlbumView(PhotoPickerAlbumsCell r19, Context r20) {
            this.this$0 = r19;
            super(r20);
            BackupImageView r1 = new BackupImageView(r20);
            this.imageView = r1;
            addView(r1, LayoutHelper.createFrame(-1, -1.0f));
            LinearLayout r12 = new LinearLayout(r20);
            r12.setOrientation(0);
            r12.setBackgroundResource(R.drawable.album_shadow);
            addView(r12, LayoutHelper.createFrame(-1, 60, 83));
            TextView r6 = new TextView(r20);
            this.nameTextView = r6;
            r6.setTextSize(1, 13.0f);
            this.nameTextView.setTextColor(-1);
            this.nameTextView.setSingleLine(true);
            TextView r62 = this.nameTextView;
            TextUtils.TruncateAt r9 = TextUtils.TruncateAt.END;
            r62.setEllipsize(r9);
            this.nameTextView.setMaxLines(1);
            this.nameTextView.setGravity(80);
            r12.addView(this.nameTextView, LayoutHelper.createLinear(0, -1, 1.0f, 8, 0, 0, 5));
            TextView r63 = new TextView(r20);
            this.countTextView = r63;
            r63.setTextSize(1, 13.0f);
            this.countTextView.setTextColor(-1);
            this.countTextView.setSingleLine(true);
            this.countTextView.setEllipsize(r9);
            this.countTextView.setMaxLines(1);
            this.countTextView.setGravity(80);
            r12.addView(this.countTextView, LayoutHelper.createLinear(-2, -1, 4.0f, 0.0f, 7.0f, 5.0f));
            View r13 = new View(r20);
            this.selector = r13;
            r13.setBackgroundDrawable(Theme.getSelectorDrawable(false));
            addView(this.selector, LayoutHelper.createFrame(-1, -1.0f));
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            if (this.imageView.getImageReceiver().hasNotThumb() == true) goto L5;
        L8:
            PhotoPickerAlbumsCell.m6996$$Nest$fgetbackgroundPaint(this.this$0).setColor(Theme.getColor(Theme.key_chat_attachPhotoBackground));
            r8.drawRect(0.0f, 0.0f, this.imageView.getMeasuredWidth(), this.imageView.getMeasuredHeight(), PhotoPickerAlbumsCell.m6996$$Nest$fgetbackgroundPaint(this.this$0));
            return;
        L5:
            if (this.imageView.getImageReceiver().getCurrentAlpha() != 1.0f) goto L8;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r4) {
            this.selector.drawableHotspotChanged(r4.getX(), r4.getY());
            return super.onTouchEvent(r4);
        }
    }

    public interface PhotoPickerAlbumsCellDelegate {
        void didSelectAlbum(MediaController.AlbumEntry r1);
    }

    public static /* synthetic */ void $r8$lambda$LiJ0IR_DJC_oZvNQptvgWryP9xU(PhotoPickerAlbumsCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m6996$$Nest$fgetbackgroundPaint(PhotoPickerAlbumsCell r0) {
        return r0.backgroundPaint;
    }

    public PhotoPickerAlbumsCell(Context r5) {
        super(r5);
        this.backgroundPaint = new Paint();
        this.albumEntries = new MediaController.AlbumEntry[4];
        this.albumViews = new AlbumView[4];
        int r1 = 0;
    L3:
        if (r1 >= 4) goto L5;
        this.albumViews[r1] = new AlbumView(this, r5);
        addView(this.albumViews[r1]);
        this.albumViews[r1].setVisibility(4);
        this.albumViews[r1].setTag(Integer.valueOf(r1));
        this.albumViews[r1].setOnClickListener(new PhotoPickerAlbumsCell$$ExternalSyntheticLambda0(this));
        r1 = r1 + 1;
        goto L3
    }

    private /* synthetic */ void lambda$new$0(View r2) {
        PhotoPickerAlbumsCellDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.didSelectAlbum(this.albumEntries[((Integer) r2.getTag()).intValue()]);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        if (AndroidUtilities.isTablet() == false) goto L5;
        int r62 = ((AndroidUtilities.dp(490.0f) - AndroidUtilities.dp(12.0f)) - ((this.albumsCount - 1) * AndroidUtilities.dp(4.0f))) / this.albumsCount;
    L6:
        int r0 = 0;
    L8:
        if (r0 >= this.albumsCount) goto L10;
        FrameLayout.LayoutParams r2 = (FrameLayout.LayoutParams) this.albumViews[r0].getLayoutParams();
        r2.topMargin = AndroidUtilities.dp(4.0f);
        r2.leftMargin = (AndroidUtilities.dp(4.0f) + r62) * r0;
        r2.width = r62;
        r2.height = r62;
        r2.gravity = 51;
        this.albumViews[r0].setLayoutParams(r2);
        r0 = r0 + 1;
        goto L8
    L10:
        super.onMeasure(r5, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(4.0f) + r62, TLObject.FLAG_30));
        return;
    L5:
        r62 = ((AndroidUtilities.displaySize.x - AndroidUtilities.dp(12.0f)) - ((this.albumsCount - 1) * AndroidUtilities.dp(4.0f))) / this.albumsCount;
        goto L6
    }

    public void setAlbum(int r5, MediaController.AlbumEntry r6) {
        this.albumEntries[r5] = r6;
        AlbumView[] r4 = this.albumViews;
        if (r6 == null) goto L15;
        AlbumView r42 = r4[r5];
        AlbumView.m6998$$Nest$fgetimageView(r42).setOrientation(0, true);
        MediaController.PhotoEntry r52 = r6.coverPhoto;
        if (r52 != null) goto L7;
    L12:
        AlbumView.m6998$$Nest$fgetimageView(r42).setImageDrawable(Theme.chat_attachEmptyDrawable);
    L13:
        AlbumView.m6999$$Nest$fgetnameTextView(r42).setText(r6.bucketName);
        AlbumView.m6997$$Nest$fgetcountTextView(r42).setText(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r6.photos.size())}));
        return;
    L7:
        if (r52.path == null) goto L12;
        BackupImageView r53 = AlbumView.m6998$$Nest$fgetimageView(r42);
        MediaController.PhotoEntry r0 = r6.coverPhoto;
        r53.setOrientation(r0.orientation, r0.invert, true);
        if (r6.coverPhoto.isVideo == false) goto L11;
        AlbumView.m6998$$Nest$fgetimageView(r42).setImage("vthumb://" + r6.coverPhoto.imageId + ":" + r6.coverPhoto.path, null, Theme.chat_attachEmptyDrawable);
        goto L13
    L11:
        AlbumView.m6998$$Nest$fgetimageView(r42).setImage("thumb://" + r6.coverPhoto.imageId + ":" + r6.coverPhoto.path, null, Theme.chat_attachEmptyDrawable);
        goto L13
    L15:
        r4[r5].setVisibility(4);
    }

    public void setAlbumsCount(int r5) {
        int r1 = 0;
    L3:
        AlbumView[] r2 = this.albumViews;
        if (r1 >= r2.length) goto L10;
        AlbumView r22 = r2[r1];
        if (r1 >= r5) goto L8;
        int r3 = 0;
    L9:
        r22.setVisibility(r3);
        r1 = r1 + 1;
        goto L3
    L8:
        r3 = 4;
        goto L9
    L10:
        this.albumsCount = r5;
    }

    public void setDelegate(PhotoPickerAlbumsCellDelegate r1) {
        this.delegate = r1;
    }
}
