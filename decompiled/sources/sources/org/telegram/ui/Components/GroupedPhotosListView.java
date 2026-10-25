package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.tl.TL_iv;

/* loaded from: classes7.dex */
public class GroupedPhotosListView extends View implements GestureDetector.OnGestureListener {
    private boolean animateAllLine;
    private boolean animateBackground;
    private int animateToDX;
    private int animateToDXStart;
    private int animateToItem;
    private boolean animateToItemFast;
    private boolean animationsEnabled;
    private Paint backgroundPaint;
    private long currentGroupId;
    private int currentImage;
    private float currentItemProgress;
    private ArrayList<Object> currentObjects;
    public ArrayList<ImageLocation> currentPhotos;
    private GroupedPhotosListViewDelegate delegate;
    private float drawAlpha;
    private int drawDx;
    private GestureDetector gestureDetector;
    private boolean hasPhotos;
    private ValueAnimator hideAnimator;
    private boolean ignoreChanges;
    private ArrayList<ImageReceiver> imagesToDraw;
    private int itemHeight;
    private int itemSpacing;
    private int itemWidth;
    private int itemY;
    private long lastUpdateTime;
    private float moveLineProgress;
    private boolean moving;
    private int nextImage;
    private float nextItemProgress;
    private int nextPhotoScrolling;
    private android.widget.Scroller scroll;
    private boolean scrolling;
    private ValueAnimator showAnimator;
    private boolean stopedScrolling;
    private ArrayList<ImageReceiver> unusedReceivers;

    /* renamed from: org.telegram.ui.Components.GroupedPhotosListView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupedPhotosListView this$0;

        public AnonymousClass1(GroupedPhotosListView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (GroupedPhotosListView.m10888$$Nest$fgethideAnimator(this.this$0) != r2) goto L6;
            GroupedPhotosListView.m10890$$Nest$fputhideAnimator(this.this$0, null);
            this.this$0.fillList();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.GroupedPhotosListView$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupedPhotosListView this$0;

        public AnonymousClass2(GroupedPhotosListView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (GroupedPhotosListView.m10889$$Nest$fgetshowAnimator(this.this$0) != r2) goto L6;
            GroupedPhotosListView.m10891$$Nest$fputshowAnimator(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            if (GroupedPhotosListView.m10887$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            GroupedPhotosListView.m10887$$Nest$fgetdelegate(this.this$0).onShowAnimationStart();
            return;
        }
    }

    public interface GroupedPhotosListViewDelegate {
        boolean forceAll();

        long getAvatarsDialogId();

        int getCurrentAccount();

        int getCurrentIndex();

        ArrayList<MessageObject> getImagesArr();

        ArrayList<ImageLocation> getImagesArrLocations();

        List<TL_iv.PageBlock> getPageBlockArr();

        Object getParentObject();

        int getSlideshowMessageId();

        void onShowAnimationStart();

        void onStopScrolling();

        void setCurrentIndex(int r1);
    }

    public static /* synthetic */ void $r8$lambda$o8NQ0rsy4CQs0t8VF6H6Au5UIzA(GroupedPhotosListView r0, ValueAnimator r1) {
        r0.lambda$fillList$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$yeTB1co2wSP_lJfUDjKdL1iszhw(GroupedPhotosListView r0, ValueAnimator r1) {
        r0.lambda$fillList$0(r1);
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupedPhotosListViewDelegate m10887$$Nest$fgetdelegate(GroupedPhotosListView r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgethideAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m10888$$Nest$fgethideAnimator(GroupedPhotosListView r0) {
        return r0.hideAnimator;
    }

    /* renamed from: -$$Nest$fgetshowAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m10889$$Nest$fgetshowAnimator(GroupedPhotosListView r0) {
        return r0.showAnimator;
    }

    /* renamed from: -$$Nest$fputhideAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10890$$Nest$fputhideAnimator(GroupedPhotosListView r0, ValueAnimator r1) {
        r0.hideAnimator = r1;
    }

    /* renamed from: -$$Nest$fputshowAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10891$$Nest$fputshowAnimator(GroupedPhotosListView r0, ValueAnimator r1) {
        r0.showAnimator = r1;
    }

    public GroupedPhotosListView(Context r4, int r5) {
        super(r4);
        this.backgroundPaint = new Paint();
        this.unusedReceivers = new ArrayList();
        this.imagesToDraw = new ArrayList();
        this.currentPhotos = new ArrayList();
        this.currentObjects = new ArrayList();
        this.currentItemProgress = 1.0f;
        this.nextItemProgress = 0.0f;
        this.animateToItem = -1;
        this.animationsEnabled = true;
        this.nextPhotoScrolling = -1;
        this.animateBackground = true;
        this.gestureDetector = new GestureDetector(r4, this);
        this.scroll = new android.widget.Scroller(r4);
        this.itemWidth = AndroidUtilities.dp(42.0f);
        this.itemHeight = AndroidUtilities.dp(56.0f);
        this.itemSpacing = AndroidUtilities.dp(1.0f);
        this.itemY = r5;
        this.backgroundPaint.setColor(2130706432);
    }

    private void fillImages(boolean r22, int r23) {
        if (r22 == false) goto L5;
    L7:
        invalidate();
        if (getMeasuredWidth() != 0) goto L10;
        return;
    L10:
        if (this.currentPhotos.isEmpty() == true) goto L67;
        int r2 = getMeasuredWidth();
        int r3 = (getMeasuredWidth() / 2) - (this.itemWidth / 2);
        if (r22 == false) goto L22;
        int r6 = this.imagesToDraw.size();
        int r7 = 0;
        int r9 = Integer.MAX_VALUE;
        int r8 = Integer.MIN_VALUE;
    L15:
        if (r7 >= r6) goto L24;
        ImageReceiver r10 = this.imagesToDraw.get(r7);
        int r11 = r10.getParam();
        int r12 = r11 - this.currentImage;
        int r13 = this.itemWidth;
        int r122 = ((r12 * (this.itemSpacing + r13)) + r3) + r23;
        if (r122 <= r2) goto L19;
    L20:
        this.unusedReceivers.add(r10);
        this.imagesToDraw.remove(r7);
        r6 = r6 - 1;
        r7 = r7 - 1;
    L21:
        r9 = Math.min(r9, r11 - 1);
        r8 = Math.max(r8, r11 + 1);
        r7 = r7 + 1;
        goto L15
    L19:
        if ((r122 + r13) >= 0) goto L21;
    L24:
        if (r8 == Integer.MIN_VALUE) goto L38;
        int r5 = this.currentPhotos.size();
    L26:
        if (r8 >= r5) goto L38;
        int r72 = (((r8 - this.currentImage) * (this.itemWidth + this.itemSpacing)) + r3) + r23;
        if (r72 >= r2) goto L38;
        ImageLocation r14 = this.currentPhotos.get(r8);
        ImageReceiver r112 = getFreeReceiver();
        r112.setImageCoords(r72, this.itemY, this.itemWidth, this.itemHeight);
        boolean r73 = this.currentObjects.get(0) instanceof MessageObject;
        ArrayList<Object> r102 = this.currentObjects;
        if (r73 == false) goto L33;
        Object r74 = r102.get(r8);
    L37:
        r112.setImage(null, null, r14, "80_80", 0, null, r74, 1);
        r112.setParam(r8);
        r8 = r8 + 1;
        goto L26
    L33:
        boolean r75 = r102.get(0) instanceof TL_iv.PageBlock;
        GroupedPhotosListViewDelegate r103 = this.delegate;
        if (r75 == false) goto L36;
        r74 = r103.getParentObject();
        goto L37
    L36:
        r74 = "avatar_" + r103.getAvatarsDialogId();
    L38:
        if (r9 == Integer.MAX_VALUE) goto L51;
    L39:
        if (r9 < 0) goto L51;
        int r24 = r9 - this.currentImage;
        int r4 = this.itemWidth;
        int r25 = (((r24 * (this.itemSpacing + r4)) + r3) + r23) + r4;
        if (r25 <= 0) goto L51;
        ImageLocation r132 = this.currentPhotos.get(r9);
        ImageReceiver r104 = getFreeReceiver();
        r104.setImageCoords(r25, this.itemY, this.itemWidth, this.itemHeight);
        boolean r26 = this.currentObjects.get(0) instanceof MessageObject;
        ArrayList<Object> r42 = this.currentObjects;
        if (r26 == false) goto L46;
        Object r27 = r42.get(r9);
    L50:
        r104.setImage(null, null, r132, "80_80", 0, null, r27, 1);
        r104.setParam(r9);
        r9 = r9 - 1;
        goto L39
    L46:
        boolean r28 = r42.get(0) instanceof TL_iv.PageBlock;
        GroupedPhotosListViewDelegate r43 = this.delegate;
        if (r28 == false) goto L49;
        r27 = r43.getParentObject();
        goto L50
    L49:
        r27 = "avatar_" + r43.getAvatarsDialogId();
    L51:
        ValueAnimator r1 = this.showAnimator;
        if (r1 != null) goto L54;
        return;
    L54:
        if (r1.isStarted() == true) goto L66;
        this.showAnimator.start();
        return;
    L66:
        return;
    L22:
        r8 = this.currentImage;
        r9 = r8 - 1;
        goto L24
    L67:
        return;
    L5:
        if (this.imagesToDraw.isEmpty() == true) goto L7;
        this.unusedReceivers.addAll(this.imagesToDraw);
        this.imagesToDraw.clear();
        this.moving = false;
        this.moveLineProgress = 1.0f;
        this.currentItemProgress = 1.0f;
        this.nextItemProgress = 0.0f;
        goto L7
    }

    private ImageReceiver getFreeReceiver() {
        if (this.unusedReceivers.isEmpty() == false) goto L5;
        ImageReceiver r0 = new ImageReceiver(this);
        r0.setAllowLoadingOnAttachedOnly(false);
    L6:
        this.imagesToDraw.add(r0);
        r0.setCurrentAccount(this.delegate.getCurrentAccount());
        return r0;
    L5:
        r0 = this.unusedReceivers.get(0);
        this.unusedReceivers.remove(0);
        goto L6
    }

    private int getMaxScrollX() {
        return this.currentImage * (this.itemWidth + (this.itemSpacing * 2));
    }

    private int getMinScrollX() {
        return (-((this.currentPhotos.size() - this.currentImage) - 1)) * (this.itemWidth + (this.itemSpacing * 2));
    }

    private /* synthetic */ void lambda$fillList$0(ValueAnimator r1) {
        this.drawAlpha = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$fillList$1(ValueAnimator r1) {
        this.drawAlpha = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private void stopScrolling() {
        this.scrolling = false;
        if (this.scroll.isFinished() == true) goto L5;
        this.scroll.abortAnimation();
    L5:
        int r1 = this.nextPhotoScrolling;
        if (r1 >= 0) goto L8;
    L12:
        invalidate();
        return;
    L8:
        if (r1 >= this.currentObjects.size()) goto L12;
        this.stopedScrolling = true;
        this.animateToItemFast = false;
        int r0 = this.nextPhotoScrolling;
        this.animateToItem = r0;
        this.nextImage = r0;
        this.animateToDX = (this.currentImage - r0) * (this.itemWidth + this.itemSpacing);
        this.animateToDXStart = this.drawDx;
        this.moveLineProgress = 1.0f;
        this.nextPhotoScrolling = -1;
        GroupedPhotosListViewDelegate r02 = this.delegate;
        if (r02 == null) goto L12;
        r02.onStopScrolling();
        goto L12
    }

    private void updateAfterScroll() {
        int r0 = this.drawDx;
        int r1 = Math.abs(r0);
        int r2 = this.itemWidth;
        int r4 = this.itemSpacing;
        int r5 = -1;
        if (r1 <= ((r2 / 2) + r4)) goto L8;
        if (r0 <= 0) goto L6;
        int r02 = r0 - ((r2 / 2) + r4);
        int r12 = 1;
    L7:
        int r13 = r12 + (r02 / (r2 + (r4 * 2)));
    L9:
        this.nextPhotoScrolling = this.currentImage - r13;
        int r03 = this.delegate.getCurrentIndex();
        ArrayList<ImageLocation> r14 = this.delegate.getImagesArrLocations();
        ArrayList<MessageObject> r22 = this.delegate.getImagesArr();
        List<TL_iv.PageBlock> r3 = this.delegate.getPageBlockArr();
        int r42 = this.nextPhotoScrolling;
        if (r03 == r42) goto L30;
        if (r42 < 0) goto L30;
        if (r42 >= this.currentPhotos.size()) goto L30;
        Object r04 = this.currentObjects.get(this.nextPhotoScrolling);
        if (r22 != null) goto L17;
    L19:
        if (r3 != null) goto L21;
    L23:
        if (r14 != null) goto L25;
    L27:
        if (r5 < 0) goto L30;
        this.ignoreChanges = true;
        this.delegate.setCurrentIndex(r5);
        goto L30
    L25:
        if (r14.isEmpty() == true) goto L27;
        r5 = r14.indexOf((ImageLocation) r04);
        goto L27
    L21:
        if (r3.isEmpty() == true) goto L23;
        r5 = r3.indexOf((TL_iv.PageBlock) r04);
        goto L27
    L17:
        if (r22.isEmpty() == true) goto L19;
        r5 = r22.indexOf((MessageObject) r04);
    L30:
        if (this.scrolling == true) goto L32;
        this.scrolling = true;
        this.stopedScrolling = false;
    L32:
        fillImages(true, this.drawDx);
        return;
    L6:
        r02 = r0 + ((r2 / 2) + r4);
        r12 = -1;
        goto L7
    L8:
        r13 = 0;
        goto L9
    }

    public void clear() {
        this.currentPhotos.clear();
        this.currentObjects.clear();
        this.imagesToDraw.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v10 */
    /* JADX WARN: Type inference failed for: r17v11 */
    /* JADX WARN: Type inference failed for: r17v12 */
    /* JADX WARN: Type inference failed for: r17v13 */
    /* JADX WARN: Type inference failed for: r17v14 */
    /* JADX WARN: Type inference failed for: r17v15 */
    public void fillList() {
        boolean r2 = false;
        if (this.ignoreChanges == false) goto L6;
        this.ignoreChanges = false;
        return;
    L6:
        int r1 = this.delegate.getCurrentIndex();
        ArrayList<ImageLocation> r3 = this.delegate.getImagesArrLocations();
        ArrayList<MessageObject> r4 = this.delegate.getImagesArr();
        List<TL_iv.PageBlock> r5 = this.delegate.getPageBlockArr();
        int r6 = this.delegate.getSlideshowMessageId();
        this.delegate.getCurrentAccount();
        this.hasPhotos = false;
        long r8 = 0;
        if (r3 != null) goto L9;
    L14:
        if (r4 != null) goto L16;
    L58:
        boolean r17 = false;
        ArrayList<ImageLocation> r18 = r3;
        long r19 = 0;
        if (r5 != null) goto L61;
    L81:
        Object r11 = null;
        int r32 = 0;
        boolean r12 = false;
        boolean r172 = r17;
    L82:
        if (r11 != null) goto L85;
        return;
    L85:
        if (this.animationsEnabled == false) goto L108;
        if (this.hasPhotos == true) goto L99;
        ValueAnimator r22 = this.showAnimator;
        if (r22 == null) goto L92;
        r22.cancel();
        this.showAnimator = null;
    L92:
        if (this.drawAlpha <= 0.0f) goto L108;
        if (this.currentPhotos.size() <= 1) goto L108;
        if (this.hideAnimator != null) goto L219;
        float[] r23 = new float[2];
        r23[r172] = this.drawAlpha;
        r23[1] = 0.0f;
        ValueAnimator r13 = ValueAnimator.ofFloat(r23);
        this.hideAnimator = r13;
        r13.setDuration((long) (this.drawAlpha * 200.0f));
        this.hideAnimator.addListener(new AnonymousClass1(this));
        this.hideAnimator.addUpdateListener(new GroupedPhotosListView$$ExternalSyntheticLambda0(this));
        this.hideAnimator.start();
        return;
    L219:
        return;
    L99:
        ValueAnimator r24 = this.hideAnimator;
        if (r24 == null) goto L102;
        this.hideAnimator = null;
        r24.cancel();
    L102:
        float r25 = this.drawAlpha;
        if (r25 >= 1.0f) goto L108;
        if (this.showAnimator != null) goto L108;
        float[] r82 = new float[2];
        r82[r172] = r25;
        r82[1] = 1.0f;
        ValueAnimator r26 = ValueAnimator.ofFloat(r82);
        this.showAnimator = r26;
        r26.setDuration((long) ((1.0f - this.drawAlpha) * 200.0f));
        this.showAnimator.addListener(new AnonymousClass2(this));
        this.showAnimator.addUpdateListener(new GroupedPhotosListView$$ExternalSyntheticLambda1(this));
    L108:
        if (r12 == false) goto L110;
    L130:
        boolean r33 = r172;
    L132:
        if (r12 == false) goto L220;
        int r7 = this.currentPhotos.size();
        this.animateAllLine = r33;
        this.currentPhotos.clear();
        this.currentObjects.clear();
        if (r18 != null) goto L136;
    L138:
        if (r4 != null) goto L140;
    L170:
        if (r5 != null) goto L172;
    L186:
        if (this.currentPhotos.size() != 1) goto L189;
        this.currentPhotos.clear();
        this.currentObjects.clear();
    L189:
        if (this.currentPhotos.size() == r7) goto L191;
        requestLayout();
    L191:
        fillImages(false, 0);
        return;
    L172:
        if (r5.isEmpty() == true) goto L186;
        if (this.currentGroupId == r19) goto L186;
        int r34 = r5.size();
        int r42 = r1;
    L176:
        if (r42 >= r34) goto L180;
        TL_iv.PageBlock r62 = r5.get(r42);
        if (r62.groupId != this.currentGroupId) goto L180;
        this.currentObjects.add(r62);
        this.currentPhotos.add(ImageLocation.getForObject(r62.thumb, r62.thumbObject));
        r42 = r42 + 1;
    L180:
        this.currentImage = 0;
        this.animateToItem = -1;
        this.animateToItemFast = false;
        int r14 = r1 - 1;
    L181:
        if (r14 < 0) goto L186;
        TL_iv.PageBlock r27 = r5.get(r14);
        if (r27.groupId != this.currentGroupId) goto L186;
        this.currentObjects.add(0, r27);
        this.currentPhotos.add(0, ImageLocation.getForObject(r27.thumb, r27.thumbObject));
        this.currentImage++;
        r14 = r14 - 1;
        goto L181
    L140:
        if (r4.isEmpty() == true) goto L170;
        if (this.delegate.forceAll() == true) goto L144;
    L146:
        boolean r35 = false;
    L148:
        if (this.currentGroupId != r19) goto L151;
        if (r35 == true) goto L151;
        if (r6 == 0) goto L186;
    L151:
        int r52 = Math.min(r1 + 10, r4.size());
        int r83 = r1;
    L153:
        if (r83 >= r52) goto L162;
        MessageObject r112 = r4.get(r83);
        if (r6 != 0) goto L161;
        if (r35 == true) goto L161;
        if (r112.getGroupIdForUse() != this.currentGroupId) goto L162;
    L161:
        this.currentObjects.add(r112);
        this.currentPhotos.add(ImageLocation.getForObject(FileLoader.getClosestPhotoSizeWithSize(r112.photoThumbs, 56, true), r112.photoThumbsObject));
        r83 = r83 + 1;
    L162:
        this.currentImage = 0;
        this.animateToItem = -1;
        this.animateToItemFast = false;
        int r28 = Math.max(r1 - 10, 0);
        int r15 = r1 - 1;
    L163:
        if (r15 < r28) goto L186;
        MessageObject r53 = r4.get(r15);
        if (r6 != 0) goto L169;
        if (r35 == true) goto L169;
        if (r53.getGroupIdForUse() != this.currentGroupId) goto L186;
    L169:
        this.currentObjects.add(0, r53);
        this.currentPhotos.add(0, ImageLocation.getForObject(FileLoader.getClosestPhotoSizeWithSize(r53.photoThumbs, 56, true), r53.photoThumbsObject));
        this.currentImage++;
        r15 = r15 - 1;
        goto L163
    L144:
        if (r4.size() <= 1) goto L146;
        r35 = true;
        goto L148
    L136:
        if (r18.isEmpty() == true) goto L138;
        ArrayList<ImageLocation> r43 = r18;
        this.currentObjects.addAll(r43);
        this.currentPhotos.addAll(r43);
        this.currentImage = r1;
        this.animateToItem = -1;
        this.animateToItemFast = false;
        goto L186
    L220:
        return;
    L110:
        if (r32 == this.currentPhotos.size()) goto L112;
    L113:
        r33 = r172;
        r12 = true;
        goto L132
    L112:
        if (this.currentObjects.contains(r11) == false) goto L113;
        int r36 = this.currentObjects.indexOf(r11);
        int r72 = this.currentImage;
        if (r72 == r36) goto L130;
        if (r36 == (-1)) goto L130;
        boolean r84 = this.animateAllLine;
        if (r84 == false) goto L120;
    L126:
        if (r84 == false) goto L128;
        this.animateToItem = r36;
        this.nextImage = r36;
        this.animateToDX = (r72 - r36) * (this.itemWidth + this.itemSpacing);
        this.moving = true;
        this.animateAllLine = r172;
        this.lastUpdateTime = System.currentTimeMillis();
        invalidate();
        r33 = false;
    L129:
        this.drawDx = r33 ? 1 : 0;
        goto L132
    L128:
        fillImages(true, (r72 - r36) * (this.itemWidth + this.itemSpacing));
        this.currentImage = r36;
        r33 = false;
        this.moving = false;
        goto L129
    L120:
        if (this.moving == true) goto L126;
        if (r36 != (r72 - 1)) goto L124;
    L125:
        this.animateToItemFast = true;
        r84 = true;
        goto L126
    L124:
        if (r36 != (r72 + 1)) goto L126;
    L61:
        if (r5.isEmpty() == true) goto L81;
        TL_iv.PageBlock r113 = r5.get(r1);
        int r29 = r113.groupId;
        if (r29 == this.currentGroupId) goto L65;
        this.currentGroupId = r29;
        r12 = true;
    L67:
        if (this.currentGroupId == 0) goto L80;
        this.hasPhotos = true;
        int r210 = r5.size();
        int r85 = r1;
        r32 = 0;
        TL_iv.PageBlock r114 = r113;
    L69:
        if (r85 >= r210) goto L73;
        TL_iv.PageBlock r152 = r114;
        if (r5.get(r85).groupId != this.currentGroupId) goto L74;
        r32 = r32 + 1;
        r85 = r85 + 1;
        r114 = r152;
    L74:
        int r211 = r1 - 1;
    L75:
        if (r211 < 0) goto L79;
        if (r5.get(r211).groupId != this.currentGroupId) goto L79;
        r32 = r32 + 1;
        r211 = r211 - 1;
    L79:
        r11 = r152;
        r172 = r17;
        goto L82
    L73:
        r152 = r114;
        goto L74
    L80:
        r32 = 0;
        r11 = r113;
        r172 = r17;
        goto L82
    L65:
        r12 = false;
        goto L67
    L16:
        if (r4.isEmpty() == true) goto L58;
        if (r1 < r4.size()) goto L20;
        r1 = r4.size() - 1;
    L20:
        MessageObject r115 = r4.get(r1);
        long r122 = r115.getGroupIdForUse();
        if (r122 == this.currentGroupId) goto L23;
        this.currentGroupId = r122;
        r12 = true;
    L25:
        if (r4.size() > 1) goto L27;
    L29:
        boolean r132 = false;
    L31:
        if (this.currentGroupId != 0) goto L35;
        if (r132 == true) goto L35;
        int r16 = 0;
        boolean r173 = false;
        r18 = r3;
        r19 = 0;
    L57:
        r32 = r16;
        r11 = r115;
        r172 = r173;
    L35:
        this.hasPhotos = true;
        int r142 = Math.min(r1 + 10, r4.size());
        int r153 = r1;
        r16 = 0;
    L36:
        if (r153 >= r142) goto L45;
        MessageObject r174 = r4.get(r153);
        if (r6 != 0) goto L43;
        if (r132 == true) goto L43;
        r19 = r8;
        if (r174.getGroupIdForUse() == this.currentGroupId) goto L44;
    L46:
        int r86 = Math.max(r1 - 10, 0);
        int r9 = r1 - 1;
    L47:
        if (r9 < r86) goto L56;
        MessageObject r143 = r4.get(r9);
        if (r6 != 0) goto L54;
        if (r132 == true) goto L54;
        r173 = r2;
        r18 = r3;
        if (r143.getGroupIdForUse() != this.currentGroupId) goto L57;
    L55:
        r16 = r16 + 1;
        r9 = r9 - 1;
        r2 = r173;
        r3 = r18;
    L54:
        r173 = r2;
        r18 = r3;
        goto L55
    L56:
        r173 = r2;
        r18 = r3;
    L44:
        r16 = r16 + 1;
        r153 = r153 + 1;
        r8 = r19;
    L43:
        r19 = r8;
        goto L44
    L45:
        r19 = r8;
        goto L46
    L27:
        if (this.delegate.forceAll() == false) goto L29;
        r132 = true;
        goto L31
    L23:
        r12 = false;
        goto L25
    L9:
        if (r3.isEmpty() == true) goto L14;
        if (r1 < r3.size()) goto L13;
        r1 = r3.size() - 1;
    L13:
        ImageLocation r116 = r3.get(r1);
        int r123 = r3.size();
        this.hasPhotos = true;
        r172 = 0;
        r18 = r3;
        r19 = 0;
        r32 = r123;
        r12 = false;
        r11 = r116;
        goto L82
    }

    public int getCount() {
        return this.currentPhotos.size();
    }

    public int getIndex() {
        return this.currentImage;
    }

    public boolean hasPhotos() {
        if (this.hasPhotos == true) goto L5;
        return false;
    L5:
        if (this.hideAnimator == null) goto L7;
        return false;
    L7:
        if (this.drawAlpha <= 0.0f) goto L9;
        return true;
    L9:
        if (this.animateBackground == false) goto L18;
        ValueAnimator r2 = this.showAnimator;
        if (r2 != null) goto L13;
        return false;
    L13:
        if (r2.isStarted() == false) goto L22;
        return true;
    L22:
        return false;
    L18:
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent r1) {
        if (this.scroll.isFinished() == true) goto L5;
        this.scroll.abortAnimation();
    L5:
        this.animateToItem = -1;
        this.animateToItemFast = false;
        return true;
    }

    @Override // android.view.View
    public void onDraw(Canvas r20) {
        if (this.hasPhotos == false) goto L5;
    L7:
        float r1 = this.drawAlpha;
        if (this.animateBackground == false) goto L10;
    L13:
        this.backgroundPaint.setAlpha((int) (r1 * 127.0f));
        r20.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.backgroundPaint);
        if (this.imagesToDraw.isEmpty() == true) goto L118;
        int r12 = this.imagesToDraw.size();
        int r2 = this.drawDx;
        int r5 = (int) (this.itemWidth * 2.0f);
        int r7 = AndroidUtilities.dp(8.0f);
        ImageLocation r8 = this.currentPhotos.get(this.currentImage);
        if (r8 != null) goto L19;
    L21:
        int r82 = this.itemHeight;
    L22:
        int r83 = Math.min(r5, r82);
        float r72 = r7 * 2;
        float r9 = this.currentItemProgress;
        int r10 = (int) (r72 * r9);
        int r11 = (this.itemWidth + ((int) ((r83 - r11) * r9))) + r10;
        int r84 = this.nextImage;
        if (r84 >= 0) goto L25;
    L32:
        int r85 = this.itemWidth;
    L33:
        int r52 = Math.min(r5, r85);
        float r86 = this.nextItemProgress;
        int r73 = (int) (r72 * r86);
        float r22 = r2;
        int r122 = this.itemWidth;
        float r92 = (((r52 + r73) - r122) / 2) * r86;
        if (this.nextImage <= this.currentImage) goto L36;
        int r13 = -1;
    L37:
        int r23 = (int) (r22 + (r92 * r13));
        int r123 = (r122 + ((int) ((r52 - r122) * r86))) + r73;
        int r53 = (getMeasuredWidth() - r11) / 2;
        int r93 = 0;
    L38:
        if (r93 >= r12) goto L62;
        ImageReceiver r132 = this.imagesToDraw.get(r93);
        int r14 = r132.getParam();
        int r6 = this.currentImage;
        if (r14 != r6) goto L42;
        r132.setImageX((r53 + r23) + (r10 / 2));
        r132.setImageWidth(r11 - r10);
    L61:
        r132.setAlpha(this.drawAlpha);
        r132.setRoundRadius(AndroidUtilities.dp(2.0f));
        r132.draw(r20);
        r93 = r93 + 1;
        goto L38
    L42:
        int r15 = this.nextImage;
        if (r15 >= r6) goto L51;
        if (r14 >= r6) goto L49;
        if (r14 > r15) goto L48;
        int r62 = (r132.getParam() - this.currentImage) + 1;
        int r152 = this.itemWidth;
        int r87 = this.itemSpacing;
        r132.setImageX((((r62 * (r152 + r87)) + r53) - (r87 + r123)) + r23);
    L58:
        if (r14 != this.nextImage) goto L60;
        r132.setImageWidth(r123 - r73);
        r132.setImageX((int) (r132.getImageX() + (r73 / 2)));
        goto L61
    L60:
        r132.setImageWidth(this.itemWidth);
        goto L61
    L48:
        r132.setImageX((((r132.getParam() - this.currentImage) * (this.itemWidth + this.itemSpacing)) + r53) + r23);
        goto L58
    L49:
        r132.setImageX((((r53 + r11) + this.itemSpacing) + (((r132.getParam() - this.currentImage) - 1) * (this.itemWidth + this.itemSpacing))) + r23);
        goto L58
    L51:
        if (r14 >= r6) goto L53;
        r132.setImageX((((r132.getParam() - this.currentImage) * (this.itemWidth + this.itemSpacing)) + r53) + r23);
        goto L58
    L53:
        int r4 = this.itemSpacing;
        if (r14 > r15) goto L56;
        r132.setImageX((((r53 + r11) + r4) + (((r132.getParam() - this.currentImage) - 1) * (this.itemWidth + this.itemSpacing))) + r23);
        goto L58
    L56:
        int r63 = (r53 + r11) + r4;
        int r42 = (r132.getParam() - this.currentImage) - 2;
        int r88 = this.itemWidth;
        int r153 = this.itemSpacing;
        r132.setImageX(((r63 + (r42 * (r88 + r153))) + (r153 + r123)) + r23);
        goto L58
    L62:
        long r16 = System.currentTimeMillis();
        long r43 = r16 - this.lastUpdateTime;
        if (r43 <= 17) goto L65;
        r43 = 17;
    L65:
        this.lastUpdateTime = r16;
        int r17 = this.animateToItem;
        if (r17 < 0) goto L95;
        float r64 = this.moveLineProgress;
        if (r64 <= 0.0f) goto L93;
        float r74 = r43;
        if (this.animateToItemFast == false) goto L72;
        float r89 = 100.0f;
    L73:
        float r65 = r64 - (r74 / r89);
        this.moveLineProgress = r65;
        if (r17 != this.currentImage) goto L81;
        float r18 = this.currentItemProgress;
        if (r18 >= 1.0f) goto L80;
        float r19 = r18 + (r74 / 200.0f);
        this.currentItemProgress = r19;
        if (r19 <= 1.0f) goto L80;
        this.currentItemProgress = 1.0f;
    L80:
        this.drawDx = this.animateToDXStart + ((int) Math.ceil(this.currentItemProgress * (this.animateToDX - r1)));
    L91:
        if (this.moveLineProgress > 0.0f) goto L93;
        this.currentImage = this.animateToItem;
        this.moveLineProgress = 1.0f;
        this.currentItemProgress = 1.0f;
        this.nextItemProgress = 0.0f;
        this.moving = false;
        this.stopedScrolling = false;
        this.drawDx = 0;
        this.animateToItem = -1;
        this.animateToItemFast = false;
        goto L93
    L81:
        CubicBezierInterpolator r110 = CubicBezierInterpolator.EASE_OUT;
        this.nextItemProgress = r110.getInterpolation(1.0f - r65);
        if (this.stopedScrolling == false) goto L89;
        float r111 = this.currentItemProgress;
        if (r111 <= 0.0f) goto L88;
        float r112 = r111 - (r74 / 200.0f);
        this.currentItemProgress = r112;
        if (r112 >= 0.0f) goto L88;
        this.currentItemProgress = 0.0f;
    L88:
        this.drawDx = this.animateToDXStart + ((int) Math.ceil(r6 * (this.animateToDX - r1)));
        goto L91
    L89:
        this.currentItemProgress = r110.getInterpolation(this.moveLineProgress);
        this.drawDx = (int) Math.ceil(this.nextItemProgress * this.animateToDX);
        goto L91
    L72:
        r89 = 200.0f;
    L93:
        fillImages(true, this.drawDx);
        invalidate();
    L95:
        if (this.scrolling == false) goto L103;
        float r113 = this.currentItemProgress;
        if (r113 <= 0.0f) goto L103;
        float r114 = r113 - (r43 / 200.0f);
        this.currentItemProgress = r114;
        if (r114 >= 0.0f) goto L101;
        this.currentItemProgress = 0.0f;
    L101:
        invalidate();
    L103:
        if (this.scroll.isFinished() == false) goto L105;
        return;
    L105:
        if (this.scroll.computeScrollOffset() == false) goto L108;
        this.drawDx = this.scroll.getCurrX();
        updateAfterScroll();
        invalidate();
    L108:
        if (this.scroll.isFinished() == false) goto L116;
        stopScrolling();
        return;
    L116:
        return;
    L36:
        r13 = 1;
        goto L37
    L25:
        if (r84 >= this.currentPhotos.size()) goto L32;
        ImageLocation r810 = this.currentPhotos.get(this.nextImage);
        if (r810 != null) goto L29;
    L31:
        r85 = this.itemHeight;
        goto L33
    L29:
        if (r810.photoSize == null) goto L31;
        r85 = Math.max(this.itemWidth, (int) (r8.w * (this.itemHeight / r8.h)));
        goto L33
    L19:
        if (r8.photoSize == null) goto L21;
        r82 = Math.max(this.itemWidth, (int) (r8.w * (this.itemHeight / r8.h)));
        goto L22
    L118:
        return;
    L10:
        if (this.hasPhotos == false) goto L12;
        r1 = 1.0f;
        goto L13
    L12:
        r1 = 0.0f;
        goto L13
    L5:
        if (this.imagesToDraw.isEmpty() == false) goto L7;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent r10, MotionEvent r11, float r12, float r13) {
        this.scroll.abortAnimation();
        if (this.currentPhotos.size() < 10) goto L7;
        this.scroll.fling(this.drawDx, 0, Math.round(r12), 0, getMinScrollX(), getMaxScrollX(), 0, 0);
        return false;
    L7:
        return false;
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        fillImages(false, 0);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent r1) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent r1, MotionEvent r2, float r3, float r4) {
        this.drawDx = (int) (this.drawDx - r3);
        int r12 = getMinScrollX();
        int r22 = getMaxScrollX();
        int r32 = this.drawDx;
        if (r32 >= r12) goto L5;
        this.drawDx = r12;
    L7:
        updateAfterScroll();
        return false;
    L5:
        if (r32 <= r22) goto L7;
        this.drawDx = r22;
        goto L7
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent r1) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent r11) {
        int r0 = this.delegate.getCurrentIndex();
        ArrayList<ImageLocation> r1 = this.delegate.getImagesArrLocations();
        ArrayList<MessageObject> r2 = this.delegate.getImagesArr();
        List<TL_iv.PageBlock> r3 = this.delegate.getPageBlockArr();
        stopScrolling();
        int r4 = this.imagesToDraw.size();
        int r6 = 0;
    L3:
        if (r6 >= r4) goto L35;
        ImageReceiver r7 = this.imagesToDraw.get(r6);
        if (r7.isInsideImage(r11.getX(), r11.getY()) == true) goto L6;
        r6 = r6 + 1;
        goto L3
    L6:
        int r112 = r7.getParam();
        if (r112 >= 0) goto L9;
    L33:
        return true;
    L9:
        if (r112 >= this.currentObjects.size()) goto L33;
        if (r2 != null) goto L14;
    L19:
        if (r3 != null) goto L21;
    L26:
        if (r1 == null) goto L35;
        if (r1.isEmpty() == true) goto L35;
        int r113 = r1.indexOf((ImageLocation) this.currentObjects.get(r112));
        if (r0 != r113) goto L32;
        return true;
    L32:
        this.moveLineProgress = 1.0f;
        this.animateAllLine = true;
        this.delegate.setCurrentIndex(r113);
        goto L35
    L21:
        if (r3.isEmpty() == true) goto L26;
        int r114 = r3.indexOf((TL_iv.PageBlock) this.currentObjects.get(r112));
        if (r0 != r114) goto L25;
        return true;
    L25:
        this.moveLineProgress = 1.0f;
        this.animateAllLine = true;
        this.delegate.setCurrentIndex(r114);
        goto L35
    L14:
        if (r2.isEmpty() == true) goto L19;
        int r115 = r2.indexOf((MessageObject) this.currentObjects.get(r112));
        if (r0 != r115) goto L18;
        return true;
    L18:
        this.moveLineProgress = 1.0f;
        this.animateAllLine = true;
        this.delegate.setCurrentIndex(r115);
    L35:
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r4) {
        boolean r1 = false;
        if (this.currentPhotos.isEmpty() == false) goto L5;
    L19:
        return r1;
    L5:
        if (getAlpha() != 1.0f) goto L19;
        if (this.gestureDetector.onTouchEvent(r4) == false) goto L10;
    L11:
        r1 = true;
    L13:
        if (this.scrolling == false) goto L19;
        if (r4.getAction() != 1) goto L19;
        if (this.scroll.isFinished() == false) goto L19;
        stopScrolling();
        goto L19
    L10:
        if (super.onTouchEvent(r4) == false) goto L13;
        goto L11
    }

    public void reset() {
        this.hasPhotos = false;
        if (this.animationsEnabled == false) goto L6;
        this.drawAlpha = 0.0f;
        return;
    }

    public void setAnimateBackground(boolean r1) {
        this.animateBackground = r1;
    }

    public void setAnimationsEnabled(boolean r2) {
        if (this.animationsEnabled == r2) goto L14;
        this.animationsEnabled = r2;
        if (r2 == true) goto L15;
        ValueAnimator r22 = this.showAnimator;
        if (r22 == null) goto L9;
        r22.cancel();
        this.showAnimator = null;
    L9:
        ValueAnimator r23 = this.hideAnimator;
        if (r23 == null) goto L12;
        r23.cancel();
        this.hideAnimator = null;
    L12:
        this.drawAlpha = 0.0f;
        invalidate();
        return;
    L15:
        return;
    }

    public void setDelegate(GroupedPhotosListViewDelegate r1) {
        this.delegate = r1;
    }

    public void setMoveProgress(float r7) {
        if (this.scrolling == false) goto L5;
        return;
    L5:
        if (this.animateToItem >= 0) goto L36;
        int r2 = this.currentImage;
        if (r7 <= 0.0f) goto L10;
        this.nextImage = r2 - 1;
    L11:
        int r22 = this.nextImage;
        if (r22 >= 0) goto L14;
    L16:
        this.currentItemProgress = 1.0f;
    L17:
        this.nextItemProgress = 1.0f - this.currentItemProgress;
        if (r7 == 0.0f) goto L20;
        boolean r23 = true;
    L21:
        this.moving = r23;
        invalidate();
        if (this.currentPhotos.isEmpty() == false) goto L24;
        return;
    L24:
        if (r7 < 0.0f) goto L26;
    L27:
        if (r7 > 0.0f) goto L29;
    L31:
        int r72 = (int) (r7 * (this.itemWidth + this.itemSpacing));
        this.drawDx = r72;
        fillImages(true, r72);
        return;
    L29:
        if (this.currentImage != 0) goto L31;
        return;
    L26:
        if (this.currentImage != (this.currentPhotos.size() - 1)) goto L27;
        return;
    L20:
        r23 = false;
        goto L21
    L14:
        if (r22 >= this.currentPhotos.size()) goto L16;
        this.currentItemProgress = 1.0f - Math.abs(r7);
        goto L17
    L10:
        this.nextImage = r2 + 1;
        goto L11
    }
}
