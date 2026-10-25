package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes6.dex */
public class SharedPhotoVideoCell extends FrameLayout {
    private Paint backgroundPaint;
    private int currentAccount;
    private SharedPhotoVideoCellDelegate delegate;
    private boolean ignoreLayout;
    private int[] indeces;
    private boolean isFirst;
    private int itemsCount;
    private MessageObject[] messageObjects;
    private PhotoVideoView[] photoVideoViews;
    private int type;

    public class PhotoVideoView extends FrameLayout {
        private AnimatorSet animator;
        private CheckBox2 checkBox;
        private FrameLayout container;
        private MessageObject currentMessageObject;
        private BackupImageView imageView;
        private View selector;
        final /* synthetic */ SharedPhotoVideoCell this$0;
        private FrameLayout videoInfoContainer;
        private TextView videoTextView;

        /* renamed from: org.telegram.ui.Cells.SharedPhotoVideoCell$PhotoVideoView$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {
            private RectF rect;
            final /* synthetic */ PhotoVideoView this$1;
            final /* synthetic */ SharedPhotoVideoCell val$this$0;

            public AnonymousClass1(PhotoVideoView r1, Context r2, SharedPhotoVideoCell r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
                this.rect = new RectF();
            }

            @Override // android.view.View
            public void onDraw(Canvas r5) {
                this.rect.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
                r5.drawRoundRect(this.rect, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), Theme.chat_timeBackgroundPaint);
            }
        }

        /* renamed from: org.telegram.ui.Cells.SharedPhotoVideoCell$PhotoVideoView$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ PhotoVideoView this$1;

            public AnonymousClass2(PhotoVideoView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator r2) {
                if (PhotoVideoView.m7026$$Nest$fgetanimator(this.this$1) != null) goto L5;
                return;
            L5:
                if (PhotoVideoView.m7026$$Nest$fgetanimator(this.this$1).equals(r2) == false) goto L9;
                PhotoVideoView.m7029$$Nest$fputanimator(this.this$1, null);
                return;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (PhotoVideoView.m7026$$Nest$fgetanimator(this.this$1) != null) goto L5;
                return;
            L5:
                if (PhotoVideoView.m7026$$Nest$fgetanimator(this.this$1).equals(r2) == false) goto L9;
                PhotoVideoView.m7029$$Nest$fputanimator(this.this$1, null);
                return;
            }
        }

        /* renamed from: -$$Nest$fgetanimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatorSet m7026$$Nest$fgetanimator(PhotoVideoView r0) {
            return r0.animator;
        }

        /* renamed from: -$$Nest$fgetcheckBox, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CheckBox2 m7027$$Nest$fgetcheckBox(PhotoVideoView r0) {
            return r0.checkBox;
        }

        /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BackupImageView m7028$$Nest$fgetimageView(PhotoVideoView r0) {
            return r0.imageView;
        }

        /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m7029$$Nest$fputanimator(PhotoVideoView r0, AnimatorSet r1) {
            r0.animator = r1;
        }

        public PhotoVideoView(SharedPhotoVideoCell r13, Context r14) {
            this.this$0 = r13;
            super(r14);
            setWillNotDraw(false);
            FrameLayout r1 = new FrameLayout(r14);
            this.container = r1;
            addView(r1, LayoutHelper.createFrame(-1, -1.0f));
            BackupImageView r12 = new BackupImageView(r14);
            this.imageView = r12;
            r12.getImageReceiver().setNeedsQualityThumb(true);
            this.imageView.getImageReceiver().setShouldGenerateQualityThumb(true);
            this.container.addView(this.imageView, LayoutHelper.createFrame(-1, -1.0f));
            AnonymousClass1 r15 = new AnonymousClass1(this, r14, r13);
            this.videoInfoContainer = r15;
            r15.setWillNotDraw(false);
            this.videoInfoContainer.setPadding(AndroidUtilities.dp(5.0f), 0, AndroidUtilities.dp(5.0f), 0);
            this.container.addView(this.videoInfoContainer, LayoutHelper.createFrame(-2, 17.0f, 83, 4.0f, 0.0f, 0.0f, 4.0f));
            ImageView r132 = new ImageView(r14);
            r132.setImageResource(R.drawable.play_mini_video);
            this.videoInfoContainer.addView(r132, LayoutHelper.createFrame(-2, -2, 19));
            TextView r133 = new TextView(r14);
            this.videoTextView = r133;
            if (Theme.isCurrentThemeMonet() == false) goto L5;
            int r16 = Theme.getColor(Theme.key_chat_mediaTimeText);
        L6:
            r133.setTextColor(r16);
            this.videoTextView.setTypeface(AndroidUtilities.bold());
            this.videoTextView.setTextSize(1, 12.0f);
            this.videoTextView.setImportantForAccessibility(2);
            this.videoInfoContainer.addView(this.videoTextView, LayoutHelper.createFrame(-2, -2.0f, 19, 13.0f, -0.7f, 0.0f, 0.0f));
            View r134 = new View(r14);
            this.selector = r134;
            r134.setBackgroundDrawable(Theme.getSelectorDrawable(false));
            addView(this.selector, LayoutHelper.createFrame(-1, -1.0f));
            CheckBox2 r135 = new CheckBox2(r14, 21);
            this.checkBox = r135;
            r135.setVisibility(4);
            this.checkBox.setColor(-1, Theme.key_sharedMedia_photoPlaceholder, Theme.key_checkboxCheck);
            this.checkBox.setDrawUnchecked(false);
            this.checkBox.setDrawBackgroundAsArc(1);
            addView(this.checkBox, LayoutHelper.createFrame(24, 24.0f, 53, 0.0f, 1.0f, 1.0f, 0.0f));
            return;
        L5:
            r16 = -1;
            goto L6
        }

        @Override // android.view.View
        public void clearAnimation() {
            super.clearAnimation();
            AnimatorSet r0 = this.animator;
            if (r0 == null) goto L6;
            r0.cancel();
            this.animator = null;
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            if (this.checkBox.isChecked() == false) goto L5;
        L12:
            r8.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), SharedPhotoVideoCell.m7024$$Nest$fgetbackgroundPaint(this.this$0));
            return;
        L5:
            if (this.imageView.getImageReceiver().hasBitmapImage() == false) goto L12;
            if (this.imageView.getImageReceiver().getCurrentAlpha() != 1.0f) goto L12;
            if (PhotoViewer.isShowingImage(this.currentMessageObject) == true) goto L12;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r4) {
            super.onInitializeAccessibilityNodeInfo(r4);
            if (this.currentMessageObject.isLivePhoto() == false) goto L6;
            r4.setText(LocaleController.getString(R.string.AttachLivePhoto));
        L10:
            if (this.checkBox.isChecked() == false) goto L13;
            r4.setCheckable(true);
            r4.setChecked(true);
            return;
        L13:
            return;
        L6:
            if (this.currentMessageObject.isVideo() == false) goto L8;
            r4.setText(LocaleController.getString(R.string.AttachVideo) + ", " + LocaleController.formatDuration((int) this.currentMessageObject.getDuration()));
            goto L10
        L8:
            r4.setText(LocaleController.getString(R.string.AttachPhoto));
            goto L10
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r4) {
            this.selector.drawableHotspotChanged(r4.getX(), r4.getY());
            return super.onTouchEvent(r4);
        }

        public void setChecked(boolean r8, boolean r9) {
            if (this.checkBox.getVisibility() == 0) goto L5;
            this.checkBox.setVisibility(0);
        L5:
            this.checkBox.setChecked(r8, r9);
            AnimatorSet r0 = this.animator;
            if (r0 == null) goto L8;
            r0.cancel();
            this.animator = null;
        L8:
            float r02 = 1.0f;
            if (r9 == false) goto L19;
            AnimatorSet r92 = new AnimatorSet();
            this.animator = r92;
            FrameLayout r2 = this.container;
            if (r8 == false) goto L13;
            float r4 = 0.81f;
        L14:
            ObjectAnimator r22 = ObjectAnimator.ofFloat(r2, View.SCALE_X, new float[]{r4});
            FrameLayout r42 = this.container;
            if (r8 == false) goto L17;
            r02 = 0.81f;
        L17:
            r92.playTogether(new Animator[]{r22, ObjectAnimator.ofFloat(r42, View.SCALE_Y, new float[]{r02})});
            this.animator.setDuration(200);
            this.animator.addListener(new AnonymousClass2(this));
            this.animator.start();
            return;
        L13:
            r4 = 1.0f;
            goto L14
        L19:
            FrameLayout r93 = this.container;
            if (r8 == false) goto L22;
            float r23 = 0.85f;
        L23:
            r93.setScaleX(r23);
            FrameLayout r7 = this.container;
            if (r8 == false) goto L26;
            r02 = 0.85f;
        L26:
            r7.setScaleY(r02);
            return;
        L22:
            r23 = 1.0f;
            goto L23
        }

        public void setMessageObject(MessageObject r12) {
            this.currentMessageObject = r12;
            int r3 = 1;
            this.imageView.getImageReceiver().setVisible(!PhotoViewer.isShowingImage(r12), false);
            if (TextUtils.isEmpty(MessagesController.getInstance(SharedPhotoVideoCell.m7025$$Nest$fgetcurrentAccount(this.this$0)).getRestrictionReason(r12.messageOwner.restriction_reason)) == true) goto L6;
            this.videoInfoContainer.setVisibility(4);
            this.imageView.setImageResource(R.drawable.photo_placeholder_in);
            return;
        L6:
            TLRPC.PhotoSize r5 = null;
            if (r12.isVideo() == false) goto L21;
            this.videoInfoContainer.setVisibility(0);
            this.videoTextView.setText(AndroidUtilities.formatShortDuration((int) r12.getDuration()));
            TLRPC.Document r1 = r12.getDocument();
            TLRPC.PhotoSize r2 = FileLoader.getClosestPhotoSizeWithSize(r1.thumbs, 50);
            TLRPC.PhotoSize r32 = FileLoader.getClosestPhotoSizeWithSize(r1.thumbs, 320);
            if (r2 == r32) goto L12;
            r5 = r32;
        L12:
            if (r2 == null) goto L19;
            BitmapDrawable r33 = r12.strippedThumb;
            BackupImageView r0 = this.imageView;
            if (r33 == null) goto L17;
            r0.setImage(ImageLocation.getForDocument(r5, r1), "100_100", null, r12.strippedThumb, r12);
            return;
        L17:
            r0.setImage(ImageLocation.getForDocument(r5, r1), "100_100", ImageLocation.getForDocument(r2, r1), "b", ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.photo_placeholder_in), null, null, 0, r12);
            return;
        L19:
            this.imageView.setImageResource(R.drawable.photo_placeholder_in);
            return;
        L21:
            TLRPC.MessageMedia r13 = r12.messageOwner.media;
            if ((r13 instanceof TLRPC.TL_messageMediaPhoto) == true) goto L24;
        L60:
            this.videoInfoContainer.setVisibility(4);
            this.imageView.setImageResource(R.drawable.photo_placeholder_in);
            return;
        L24:
            if (r13.photo == null) goto L60;
            if (r12.photoThumbs.isEmpty() == true) goto L60;
            this.videoInfoContainer.setVisibility(4);
            TLRPC.PhotoSize r14 = FileLoader.getClosestPhotoSizeWithSize(r12.photoThumbs, 50);
            TLRPC.PhotoSize r22 = FileLoader.getClosestPhotoSizeWithSize(r12.photoThumbs, 320, false, r14, false);
            if (r12.mediaExists == false) goto L30;
        L38:
            if (r22 == r14) goto L41;
            r5 = r14;
        L41:
            BitmapDrawable r15 = r12.strippedThumb;
            BackupImageView r02 = this.imageView;
            long r6 = 0;
            if (r15 == null) goto L52;
            ImageReceiver r03 = r02.getImageReceiver();
            ImageLocation r16 = ImageLocation.getForObject(r22, r12.photoThumbsObject);
            BitmapDrawable r52 = r12.strippedThumb;
            if (r22 == null) goto L47;
            r6 = r22.size;
        L47:
            if (r12.shouldEncryptPhotoOrVideo() == false) goto L49;
            int r10 = 2;
        L50:
            r03.setImage(r16, "100_100", null, null, r52, r6, null, r12, r10);
            return;
        L49:
            r10 = 1;
            goto L50
        L52:
            ImageReceiver r04 = r02.getImageReceiver();
            ImageLocation r17 = ImageLocation.getForObject(r22, r12.photoThumbsObject);
            ImageLocation r53 = ImageLocation.getForObject(r5, r12.photoThumbsObject);
            if (r22 == null) goto L56;
            r6 = r22.size;
        L56:
            if (r12.shouldEncryptPhotoOrVideo() == false) goto L58;
            r3 = 2;
        L58:
            r04.setImage(r17, "100_100", r53, "b", r6, null, r12, r3);
            return;
        L30:
            if (DownloadController.getInstance(SharedPhotoVideoCell.m7025$$Nest$fgetcurrentAccount(this.this$0)).canDownloadMedia(r12) == true) goto L38;
            BitmapDrawable r54 = r12.strippedThumb;
            BackupImageView r05 = this.imageView;
            if (r54 == null) goto L36;
            r05.setImage(null, null, null, null, r54, null, null, 0, r12);
            return;
        L36:
            r05.setImage(null, null, ImageLocation.getForObject(r14, r12.photoThumbsObject), "b", ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.photo_placeholder_in), null, null, 0, r12);
        }
    }

    public interface SharedPhotoVideoCellDelegate {
        void didClickItem(SharedPhotoVideoCell r1, int r2, MessageObject r3, int r4);

        boolean didLongClickItem(SharedPhotoVideoCell r1, int r2, MessageObject r3, int r4);
    }

    public static /* synthetic */ boolean $r8$lambda$zPjoBdsSfgedt3tq2gqSkAq4fNQ(SharedPhotoVideoCell r0, View r1) {
        return r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$zor1Ec8Z9OJ5I1KvTC8nJyB1vTQ(SharedPhotoVideoCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m7024$$Nest$fgetbackgroundPaint(SharedPhotoVideoCell r0) {
        return r0.backgroundPaint;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7025$$Nest$fgetcurrentAccount(SharedPhotoVideoCell r0) {
        return r0.currentAccount;
    }

    public SharedPhotoVideoCell(Context r4, int r5) {
        super(r4);
        Paint r0 = new Paint();
        this.backgroundPaint = r0;
        this.currentAccount = UserConfig.selectedAccount;
        this.type = r5;
        r0.setColor(Theme.getColor(Theme.key_sharedMedia_photoPlaceholder));
        this.messageObjects = new MessageObject[6];
        this.photoVideoViews = new PhotoVideoView[6];
        this.indeces = new int[6];
        int r02 = 0;
    L3:
        if (r02 >= 6) goto L5;
        this.photoVideoViews[r02] = new PhotoVideoView(this, r4);
        addView(this.photoVideoViews[r02]);
        this.photoVideoViews[r02].setVisibility(4);
        this.photoVideoViews[r02].setTag(Integer.valueOf(r02));
        this.photoVideoViews[r02].setOnClickListener(new SharedPhotoVideoCell$$ExternalSyntheticLambda0(this));
        this.photoVideoViews[r02].setOnLongClickListener(new SharedPhotoVideoCell$$ExternalSyntheticLambda1(this));
        r02 = r02 + 1;
        goto L3
    }

    public static int getItemSize(int r3) {
        if (AndroidUtilities.isTablet() == false) goto L7;
        return (AndroidUtilities.dp(490.0f) - ((r3 - 1) * AndroidUtilities.dp(2.0f))) / r3;
    L7:
        return (AndroidUtilities.displaySize.x - ((r3 - 1) * AndroidUtilities.dp(2.0f))) / r3;
    }

    private /* synthetic */ void lambda$new$0(View r4) {
        if (this.delegate == null) goto L6;
        int r42 = ((Integer) r4.getTag()).intValue();
        this.delegate.didClickItem(this, this.indeces[r42], this.messageObjects[r42], r42);
        return;
    }

    private /* synthetic */ boolean lambda$new$1(View r4) {
        if (this.delegate == null) goto L6;
        int r42 = ((Integer) r4.getTag()).intValue();
        return this.delegate.didLongClickItem(this, this.indeces[r42], this.messageObjects[r42], r42);
    L6:
        return false;
    }

    public SharedPhotoVideoCellDelegate getDelegate() {
        return this.delegate;
    }

    public BackupImageView getImageView(int r2) {
        if (r2 < this.itemsCount) goto L7;
        return null;
    L7:
        return PhotoVideoView.m7028$$Nest$fgetimageView(this.photoVideoViews[r2]);
    }

    public MessageObject getMessageObject(int r2) {
        if (r2 < this.itemsCount) goto L7;
        return null;
    L7:
        return this.messageObjects[r2];
    }

    @Override // android.view.View
    public void invalidate() {
        int r0 = 0;
    L4:
        if (r0 >= 6) goto L6;
        this.photoVideoViews[r0].invalidate();
        r0 = r0 + 1;
        goto L4
    L6:
        super.invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r9, int r10) {
        if (this.type != 1) goto L5;
        int r102 = (View.MeasureSpec.getSize(r9) - ((this.itemsCount - 1) * AndroidUtilities.dp(2.0f))) / this.itemsCount;
    L6:
        this.ignoreLayout = true;
        int r2 = 0;
        int r3 = 0;
    L8:
        if (r3 >= this.itemsCount) goto L21;
        FrameLayout.LayoutParams r4 = (FrameLayout.LayoutParams) this.photoVideoViews[r3].getLayoutParams();
        if (this.isFirst == false) goto L12;
        int r5 = 0;
    L13:
        r4.topMargin = r5;
        r4.leftMargin = (AndroidUtilities.dp(2.0f) + r102) * r3;
        if (r3 == (this.itemsCount - 1)) goto L16;
        r4.width = r102;
    L20:
        r4.height = r102;
        r4.gravity = 51;
        this.photoVideoViews[r3].setLayoutParams(r4);
        r3 = r3 + 1;
        goto L8
    L16:
        if (AndroidUtilities.isTablet() == false) goto L18;
        r4.width = AndroidUtilities.dp(490.0f) - ((this.itemsCount - 1) * (AndroidUtilities.dp(2.0f) + r102));
        goto L20
    L18:
        r4.width = AndroidUtilities.displaySize.x - ((this.itemsCount - 1) * (AndroidUtilities.dp(2.0f) + r102));
        goto L20
    L12:
        r5 = AndroidUtilities.dp(2.0f);
        goto L13
    L21:
        this.ignoreLayout = false;
        if (this.isFirst == true) goto L25;
        r2 = AndroidUtilities.dp(2.0f);
    L25:
        super.onMeasure(r9, View.MeasureSpec.makeMeasureSpec(r2 + r102, TLObject.FLAG_30));
        return;
    L5:
        r102 = getItemSize(this.itemsCount);
        goto L6
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void setChecked(int r1, boolean r2, boolean r3) {
        this.photoVideoViews[r1].setChecked(r2, r3);
    }

    public void setDelegate(SharedPhotoVideoCellDelegate r1) {
        this.delegate = r1;
    }

    public void setIsFirst(boolean r1) {
        this.isFirst = r1;
    }

    public void setItem(int r2, int r3, MessageObject r4) {
        this.messageObjects[r2] = r4;
        this.indeces[r2] = r3;
        PhotoVideoView[] r32 = this.photoVideoViews;
        if (r4 == null) goto L6;
        r32[r2].setVisibility(0);
        this.photoVideoViews[r2].setMessageObject(r4);
        return;
    L6:
        r32[r2].clearAnimation();
        this.photoVideoViews[r2].setVisibility(4);
        this.messageObjects[r2] = null;
    }

    public void setItemsCount(int r5) {
        int r1 = 0;
    L3:
        PhotoVideoView[] r2 = this.photoVideoViews;
        if (r1 >= r2.length) goto L10;
        r2[r1].clearAnimation();
        PhotoVideoView r22 = this.photoVideoViews[r1];
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
        this.itemsCount = r5;
    }

    public void updateCheckboxColor() {
        int r0 = 0;
    L4:
        if (r0 >= 6) goto L6;
        PhotoVideoView.m7027$$Nest$fgetcheckBox(this.photoVideoViews[r0]).invalidate();
        r0 = r0 + 1;
        goto L4
    }
}
