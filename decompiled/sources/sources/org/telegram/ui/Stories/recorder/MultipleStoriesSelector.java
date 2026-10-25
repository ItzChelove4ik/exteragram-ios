package org.telegram.ui.Stories.recorder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import java.io.File;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes7.dex */
public abstract class MultipleStoriesSelector extends FrameLayout {
    private AnimatedFloat animatedHint;
    private final BlurringShader.StoryBlurDrawer backgroundBlur;
    private final BlurringShader.BlurManager blurManager;
    private ButtonBounce buttonBounce;
    private final RectF buttonBounds;
    private final RectF buttonTouchBounds;
    private final Path clipPath;
    private final Path closePath;
    private Text counter;
    private final Paint darkenBackground;
    private final Runnable hideHint;
    private Text hint;
    private final RectF hintArc;
    private final RectF hintBounds;
    private final Path hintClipPath;
    private boolean hintShown;
    private final RectF listBounds;
    private boolean listShown;
    private final UniversalRecyclerView listView;
    private final Theme.ResourcesProvider resourcesProvider;
    private ArrayList<Integer> selectedOrder;
    private ArrayList<Integer> selectedStories;
    private int selectedStory;
    private ArrayList<StoryEntry> stories;
    private final Paint strokePaint;

    /* renamed from: org.telegram.ui.Stories.recorder.MultipleStoriesSelector$1, reason: invalid class name */
    public class AnonymousClass1 extends UniversalRecyclerView {
        final /* synthetic */ MultipleStoriesSelector this$0;

        /* renamed from: $r8$lambda$6_zux8rLPOLT-ODuzJA3NN_qzRQ, reason: not valid java name */
        public static /* synthetic */ void m20058$r8$lambda$6_zux8rLPOLTODuzJA3NN_qzRQ(AnonymousClass1 r0, View r1) {
            r0.lambda$swappedElements$0(r1);
        }

        public AnonymousClass1(MultipleStoriesSelector r1, Context r2, int r3, int r4, boolean r5, Utilities.Callback2 r6, Utilities.Callback5 r7, Utilities.Callback5Return r8, Theme.ResourcesProvider r9, int r10, int r11) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
        }

        private /* synthetic */ void lambda$swappedElements$0(View r3) {
            if ((r3 instanceof EntryView) == false) goto L6;
            MultipleStoriesSelector r2 = this.this$0;
            ((EntryView) r3).setPosition(MultipleStoriesSelector.m20057$$Nest$mgetPositionOf(r2, MultipleStoriesSelector.m20056$$Nest$fgetlistView(r2).getChildAdapterPosition(r3)));
            r3.setPressed(false);
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public Integer getSelectorColor(int r1) {
            return 0;
        }

        @Override // org.telegram.ui.Components.UniversalRecyclerView
        public void swappedElements() {
            AndroidUtilities.forEachViews(MultipleStoriesSelector.m20056$$Nest$fgetlistView(this.this$0), new MultipleStoriesSelector$1$$ExternalSyntheticLambda0(this));
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.MultipleStoriesSelector$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ MultipleStoriesSelector this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass2(MultipleStoriesSelector r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.val$show == true) goto L6;
            MultipleStoriesSelector.m20056$$Nest$fgetlistView(this.this$0).setVisibility(8);
            return;
        }
    }

    public static class EntryView extends View {
        private AnimatedFloat animatedChecked;
        private AnimatedFloat animatedSelected;
        private ButtonBounce checkboxBounce;
        private boolean checked;
        private final AnimatedTextView.AnimatedTextDrawable counter;
        private float cx;
        private float cy;
        private final Paint fillPaint;
        private final ImageReceiver imageReceiver;
        private String lastEntryPath;
        private int lastId;
        private View.OnClickListener onCheckboxClick;
        private float r;
        private boolean selected;
        private final Paint strokePaint;

        public static class Factory extends UItem.UItemFactory<EntryView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asStoryEntry(int r1, int r2, StoryEntry r3) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = r1;
                r0.object = r3;
                r0.intValue = r2;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                EntryView r12 = (EntryView) r1;
                r12.set(r2.id, r2.intValue, (StoryEntry) r2.object);
                r12.setSelected(r2.checked, false);
                r12.setChecked(r2.collapsed, false);
                r12.setOnCheckboxClick(r2.clickCallback);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public EntryView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new EntryView(r1, r5);
            }
        }

        public static /* synthetic */ void $r8$lambda$4LRnIb5HHLbZMCCWlUlA2ZpytIc(EntryView r0, StoryEntry r1) {
            r0.lambda$set$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$EIYcOXPQTkMIPZavGyIrq_j18TY(EntryView r0, Bitmap r1) {
            r0.lambda$set$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$F974ugRmKqQONAqQ_hhSjKxa4dM(EntryView r0, Bitmap r1) {
            r0.lambda$set$0(r1);
        }

        /* renamed from: $r8$lambda$bFC8Z8xh27Dgk-aFG1wFrAOCyZ8, reason: not valid java name */
        public static /* synthetic */ void m20059$r8$lambda$bFC8Z8xh27DgkaFG1wFrAOCyZ8(EntryView r0, StoryEntry r1) {
            r0.lambda$set$3(r1);
        }

        public EntryView(Context r13, Theme.ResourcesProvider r14) {
            super(r13);
            ImageReceiver r132 = new ImageReceiver(this);
            this.imageReceiver = r132;
            Paint r0 = new Paint(1);
            this.strokePaint = r0;
            Paint r2 = new Paint(1);
            this.fillPaint = r2;
            AnimatedTextView.AnimatedTextDrawable r3 = new AnimatedTextView.AnimatedTextDrawable();
            this.counter = r3;
            this.checkboxBounce = new ButtonBounce(this);
            this.lastId = -1;
            CubicBezierInterpolator r9 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.animatedSelected = new AnimatedFloat(this, 0, 320, r9);
            this.animatedChecked = new AnimatedFloat(this, 0, 320, r9);
            r3.setCallback(this);
            r3.setTextColor(-1);
            r3.setGravity(17);
            r3.setTextSize(AndroidUtilities.dp(16.0f));
            r3.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
            r3.setOverrideFullWidth(AndroidUtilities.displaySize.x);
            r3.setAnimationProperties(0.65f, 0, 480, r9);
            r3.setScaleProperty(0.35f);
            r0.setStyle(Paint.Style.STROKE);
            r0.setColor(-1);
            r2.setColor(Theme.getColor(Theme.key_featuredStickers_addButton, r14));
            r132.setRoundRadius(AndroidUtilities.dp(6.0f));
            ScaleStateListAnimator.apply(this);
        }

        private /* synthetic */ void lambda$set$0(Bitmap r1) {
            this.imageReceiver.setImageBitmap(r1);
        }

        private /* synthetic */ void lambda$set$1(StoryEntry r5) {
            BitmapFactory.Options r0 = new BitmapFactory.Options();
            r0.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(r5.draftThumbFile.getPath(), r0);
            StoryEntry.setupScale(r0, AndroidUtilities.dp(94.0f), AndroidUtilities.dp(112.0f));
            r0.inPreferredConfig = Bitmap.Config.ARGB_8888;
            r0.inDither = true;
            r0.inJustDecodeBounds = false;
            AndroidUtilities.runOnUIThread(new MultipleStoriesSelector$EntryView$$ExternalSyntheticLambda3(this, BitmapFactory.decodeFile(r5.draftThumbFile.getPath(), r0)));
        }

        private /* synthetic */ void lambda$set$2(Bitmap r1) {
            this.imageReceiver.setImageBitmap(r1);
        }

        private /* synthetic */ void lambda$set$3(StoryEntry r5) {
            BitmapFactory.Options r0 = new BitmapFactory.Options();
            r0.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(r5.file.getPath(), r0);
            StoryEntry.setupScale(r0, AndroidUtilities.dp(94.0f), AndroidUtilities.dp(112.0f));
            r0.inPreferredConfig = Bitmap.Config.ARGB_8888;
            r0.inDither = true;
            r0.inJustDecodeBounds = false;
            AndroidUtilities.runOnUIThread(new MultipleStoriesSelector$EntryView$$ExternalSyntheticLambda2(this, BitmapFactory.decodeFile(r5.file.getPath(), r0)));
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r9) {
            super.dispatchDraw(r9);
            this.imageReceiver.setImageCoords(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(94.0f), AndroidUtilities.dp(112.0f));
            this.imageReceiver.draw(r9);
            this.strokePaint.setStrokeWidth(AndroidUtilities.dp(1.5f));
            float r0 = this.animatedSelected.set(this.selected);
            if (r0 <= 0.0f) goto L5;
            RectF r4 = AndroidUtilities.rectTmp;
            r4.set(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(96.0f), AndroidUtilities.dp(116.0f));
            this.strokePaint.setAlpha((int) (r0 * 255.0f));
            r9.drawRoundRect(r4, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.strokePaint);
        L5:
            this.cx = (getWidth() - AndroidUtilities.dp(17.163f)) - AndroidUtilities.dp(3.0f);
            this.cy = AndroidUtilities.dp(17.833f) + AndroidUtilities.dp(3.0f);
            this.r = AndroidUtilities.dp(12.833f);
            float r02 = this.animatedChecked.set(this.checked);
            float r1 = this.checkboxBounce.getScale(0.075f);
            r9.save();
            r9.scale(r1, r1, this.cx, this.cy);
            if (r02 <= 0.0f) goto L8;
            this.fillPaint.setAlpha((int) (r02 * 255.0f));
            r9.drawCircle(this.cx, this.cy, this.r, this.fillPaint);
        L8:
            this.strokePaint.setAlpha(255);
            r9.drawCircle(this.cx, this.cy, this.r - AndroidUtilities.dp(1.0f), this.strokePaint);
            if (r02 <= 0.0f) goto L11;
            AnimatedTextView.AnimatedTextDrawable r12 = this.counter;
            float r2 = this.cx;
            float r3 = this.r;
            float r6 = this.cy;
            r12.setBounds(r2 - r3, r6, r2 + r3, r6);
            this.counter.setAlpha((int) (r02 * 255.0f));
            this.counter.draw(r9);
        L11:
            r9.restore();
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.imageReceiver.onAttachedToWindow();
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.imageReceiver.onDetachedFromWindow();
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(98.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(120.0f), TLObject.FLAG_30));
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r7) {
            if (r7.getX() >= (this.cx - AndroidUtilities.dp(14.0f))) goto L5;
        L11:
            boolean r0 = false;
        L13:
            if (r7.getAction() != 0) goto L16;
            this.checkboxBounce.setPressed(r0);
        L28:
            if (this.checkboxBounce.isPressed() == false) goto L30;
        L33:
            return true;
        L30:
            if (super.onTouchEvent(r7) == true) goto L33;
            return false;
        L16:
            if (r7.getAction() != 1) goto L25;
            if (this.checkboxBounce.isPressed() == false) goto L23;
            if (r0 == false) goto L23;
            View.OnClickListener r02 = this.onCheckboxClick;
            if (r02 == null) goto L23;
            r02.onClick(this);
        L23:
            this.checkboxBounce.setPressed(false);
            goto L28
        L25:
            if (r7.getAction() != 3) goto L28;
            this.checkboxBounce.setPressed(false);
            goto L28
        L5:
            if (r7.getX() > (this.cx + AndroidUtilities.dp(14.0f))) goto L11;
            if (r7.getY() < (this.cy - AndroidUtilities.dp(14.0f))) goto L11;
            if (r7.getY() > (this.cy + AndroidUtilities.dp(14.0f))) goto L11;
            r0 = true;
            goto L13
        }

        public void set(int r5, int r6, StoryEntry r7) {
            if (this.lastId == r5) goto L5;
            this.lastEntryPath = null;
            this.imageReceiver.clearImage();
            this.lastId = r5;
        L5:
            this.counter.setText(Integer.toString(r6 + 1), false);
            File r52 = r7.draftThumbFile;
            if (r52 == null) goto L13;
            if (TextUtils.equals(this.lastEntryPath, r52.getPath()) == true) goto L48;
            this.lastEntryPath = r7.draftThumbFile.getPath();
            Utilities.searchQueue.postRunnable(new MultipleStoriesSelector$EntryView$$ExternalSyntheticLambda0(this, r7));
            return;
        L48:
            return;
        L13:
            if (r7.isVideo == false) goto L37;
            Bitmap r53 = r7.blurredVideoThumb;
            if (r53 != null) goto L18;
            r53 = null;
        L18:
            if (r53 != null) goto L35;
            String r62 = r7.thumbPath;
            if (r62 == null) goto L35;
            if (r62.startsWith("vthumb://") == false) goto L35;
            if (TextUtils.equals(this.lastEntryPath, r7.thumbPath) == true) goto L49;
            String r63 = r7.thumbPath;
            this.lastEntryPath = r63;
            long r2 = Long.parseLong(r63.substring(9));
            if (r53 != null) goto L35;
            if (Build.VERSION.SDK_INT < 29) goto L35;
            if (r7.isVideo == false) goto L33;
            Uri r64 = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, r2);     // Catch: Exception -> L44
        L34:
            r53 = getContext().getContentResolver().loadThumbnail(r64, new Size(AndroidUtilities.dp(94.0f), AndroidUtilities.dp(112.0f)), null);     // Catch: Exception -> L44
            goto L35
        L33:
            r64 = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, r2);     // Catch: Exception -> L44
            goto L34
        L49:
            return;
        L35:
            this.imageReceiver.setImageBitmap(r53);
            return;
        L37:
            File r54 = r7.file;
            if (r54 != null) goto L40;
            return;
        L40:
            if (TextUtils.equals(this.lastEntryPath, r54.getPath()) == true) goto L50;
            this.lastEntryPath = r7.file.getPath();
            Utilities.searchQueue.postRunnable(new MultipleStoriesSelector$EntryView$$ExternalSyntheticLambda1(this, r7));
            return;
        }

        public void setChecked(boolean r2, boolean r3) {
            if (this.checked != r2) goto L5;
            return;
        L5:
            this.checked = r2;
            if (r3 == true) goto L8;
            this.animatedChecked.force(r2);
        L8:
            invalidate();
        }

        public void setOnCheckboxClick(View.OnClickListener r1) {
            this.onCheckboxClick = r1;
        }

        public void setPosition(int r2) {
            AnimatedTextView.AnimatedTextDrawable r1 = this.counter;
            if (r2 >= 0) goto L5;
            String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            r1.setText(r22, true);
            return;
        L5:
            r22 = Integer.toString(r2 + 1);
            goto L6
        }

        public void setSelected(boolean r2, boolean r3) {
            if (this.selected != r2) goto L5;
            return;
        L5:
            this.selected = r2;
            if (r3 == true) goto L8;
            this.animatedSelected.force(r2);
        L8:
            invalidate();
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.counter) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: $r8$lambda$9ni5-vrxyouETRXZGZcqEW__lac, reason: not valid java name */
    public static /* synthetic */ void m20052$r8$lambda$9ni5vrxyouETRXZGZcqEW__lac(MultipleStoriesSelector r0, ValueAnimator r1) {
        r0.lambda$showList$4(r1);
    }

    /* renamed from: $r8$lambda$HUYw-PEz99mnK7mfr2OEBLDHhYU, reason: not valid java name */
    public static /* synthetic */ void m20053$r8$lambda$HUYwPEz99mnK7mfr2OEBLDHhYU(MultipleStoriesSelector r0, View r1) {
        r0.lambda$updateItemsAnimated$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$HqvZ3DfvE8nEGz4uIm79gx2KQbU(MultipleStoriesSelector r0, int r1, View r2) {
        r0.lambda$setSelected$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$NtOP71D62gwf6V7X58ZiNgbqJ3k(MultipleStoriesSelector r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onItemClick(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$UNdvPX9jGct2SkfVkzHJZe-nroc, reason: not valid java name */
    public static /* synthetic */ void m20054$r8$lambda$UNdvPX9jGct2SkfVkzHJZenroc(MultipleStoriesSelector r0, int r1, ArrayList r2) {
        r0.whenReordered(r1, r2);
    }

    /* renamed from: $r8$lambda$hXaZZgojojhPtiR__wkK_5bdY-c, reason: not valid java name */
    public static /* synthetic */ void m20055$r8$lambda$hXaZZgojojhPtiR__wkK_5bdYc(MultipleStoriesSelector r0, int r1, View r2) {
        r0.lambda$fillItems$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$qqIVRSYAQNhIitek8Ay3uroXDjw(MultipleStoriesSelector r0) {
        r0.lambda$new$2();
    }

    public static /* synthetic */ void $r8$lambda$zyJ6SBoBJp8HcT_ufgLTNKIx9PA(MultipleStoriesSelector r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalRecyclerView m20056$$Nest$fgetlistView(MultipleStoriesSelector r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$mgetPositionOf, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20057$$Nest$mgetPositionOf(MultipleStoriesSelector r0, int r1) {
        return r0.getPositionOf(r1);
    }

    public MultipleStoriesSelector(Context r16, Theme.ResourcesProvider r17, BlurringShader.BlurManager r18) {
        super(r16);
        this.stories = new ArrayList();
        this.selectedOrder = new ArrayList();
        this.selectedStories = new ArrayList();
        this.hideHint = new MultipleStoriesSelector$$ExternalSyntheticLambda1(this);
        this.buttonBounce = new ButtonBounce(this);
        this.buttonBounds = new RectF();
        this.buttonTouchBounds = new RectF();
        Paint r12 = new Paint(1);
        this.strokePaint = r12;
        Path r8 = new Path();
        this.closePath = r8;
        this.listBounds = new RectF();
        this.darkenBackground = new Paint(1);
        this.hintBounds = new RectF();
        this.hintArc = new RectF();
        this.hintClipPath = new Path();
        this.animatedHint = new AnimatedFloat(this, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.clipPath = new Path();
        this.listShown = true;
        this.resourcesProvider = r17;
        this.blurManager = r18;
        r8.rewind();
        r8.moveTo(-AndroidUtilities.dp(4.33f), -AndroidUtilities.dp(4.33f));
        r8.lineTo(AndroidUtilities.dp(4.33f), AndroidUtilities.dp(4.33f));
        r8.moveTo(-AndroidUtilities.dp(4.33f), AndroidUtilities.dp(4.33f));
        r8.lineTo(AndroidUtilities.dp(4.33f), -AndroidUtilities.dp(4.33f));
        this.backgroundBlur = new BlurringShader.StoryBlurDrawer(r18, this, 0, !customBlur());
        setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(44.0f));
        AnonymousClass1 r0 = new AnonymousClass1(this, r16, UserConfig.selectedAccount, 0, false, new MultipleStoriesSelector$$ExternalSyntheticLambda2(this), new MultipleStoriesSelector$$ExternalSyntheticLambda3(this), null, r17, -1, 0);
        this.listView = r0;
        r0.adapter.setApplyBackground(false);
        r0.setClipToPadding(false);
        r0.setClipChildren(false);
        r0.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
        addView(r0, LayoutHelper.createFrame(-2, 120, 85));
        r0.allowReorder(true);
        r0.listenReorder(new MultipleStoriesSelector$$ExternalSyntheticLambda4(this), true);
        showList(false, false);
        setWillNotDraw(false);
        r12.setStyle(Paint.Style.STROKE);
        r12.setStrokeJoin(Paint.Join.ROUND);
        r12.setStrokeCap(Paint.Cap.ROUND);
        r12.setColor(-1);
    }

    private void drawBlur(Canvas r12, RectF r13, float r14, float r15) {
        if (r15 >= 1.0f) goto L5;
        r12.saveLayerAlpha(r13, (int) (255.0f * r15), 31);
    L5:
        boolean r4 = customBlur();
        BlurringShader.StoryBlurDrawer r1 = this.backgroundBlur;
        if (r4 == false) goto L8;
        drawBlur(r1, r12, r13, r14, false, 0.0f, 0.0f, true, 1.0f);
        this.darkenBackground.setAlpha(38);
        r12.drawRoundRect(r13, r14, r14, this.darkenBackground);
    L21:
        if (r15 >= 1.0f) goto L24;
        r12.restore();
        return;
    L24:
        return;
    L8:
        Paint[] r16 = r1.getPaints(1.0f, 0.0f, 0.0f);
        if (r16 != null) goto L11;
    L20:
        this.darkenBackground.setAlpha(128);
        r12.drawRoundRect(r13, r14, r14, this.darkenBackground);
        goto L21
    L11:
        if (r16[1] == null) goto L20;
        Paint r6 = r16[0];
        if (r6 == null) goto L16;
        r12.drawRoundRect(r13, r14, r14, r6);
    L16:
        Paint r17 = r16[1];
        if (r17 == null) goto L19;
        r12.drawRoundRect(r13, r14, r14, r17);
    L19:
        this.darkenBackground.setAlpha((int) (51.0f * r15));
        r12.drawRoundRect(r13, r14, r14, this.darkenBackground);
        goto L21
    }

    private void fillItems(ArrayList<UItem> r8, UniversalAdapter r9) {
        r9.reorderSectionStart();
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= this.selectedOrder.size()) goto L13;
        Integer r3 = this.selectedOrder.get(r1);
        int r4 = r3.intValue();
        UItem r5 = EntryView.Factory.asStoryEntry(r4, r2, this.stories.get(r4));
        if (this.selectedStory != r4) goto L8;
        boolean r6 = true;
    L9:
        r8.add(r5.setChecked(r6).setCollapsed(this.selectedStories.contains(r3)).setClickCallback(new MultipleStoriesSelector$$ExternalSyntheticLambda7(this, r4)));
        if (this.selectedStories.contains(r3) == false) goto L12;
        r2 = r2 + 1;
    L12:
        r1 = r1 + 1;
        goto L4
    L8:
        r6 = false;
        goto L9
    L13:
        r9.reorderSectionEnd();
    }

    private int getPositionOf(int r5) {
        if (this.selectedOrder.contains(Integer.valueOf(r5)) == true) goto L6;
        return -1;
    L6:
        int r0 = 0;
        int r1 = 0;
    L8:
        if (r0 >= Math.min(r5, this.stories.size())) goto L13;
        if (this.selectedOrder.contains(Integer.valueOf(r0)) == false) goto L12;
        r1 = r1 + 1;
    L12:
        r0 = r0 + 1;
        goto L8
    L13:
        return r1;
    }

    private /* synthetic */ void lambda$fillItems$1(int r2, View r3) {
        boolean r32 = this.selectedStories.contains(Integer.valueOf(r2));
        ArrayList<Integer> r0 = this.selectedStories;
        if (r32 == true) goto L5;
        r0.add(Integer.valueOf(r2));
    L9:
        updateItemsAnimated();
        return;
    L5:
        if (r0.size() > 1) goto L7;
        return;
    L7:
        this.selectedStories.remove(Integer.valueOf(r2));
        goto L9
    }

    private /* synthetic */ void lambda$new$2() {
        if (this.hintShown == false) goto L6;
        this.hintShown = false;
        invalidate();
        return;
    }

    private /* synthetic */ void lambda$setSelected$3(int r4, View r5) {
        if ((r5 instanceof EntryView) == false) goto L13;
        int r0 = this.listView.getChildAdapterPosition(r5);
        UItem r1 = this.listView.adapter.getItem(r0);
        if (r1 == null) goto L14;
        EntryView r2 = (EntryView) r5;
        r2.setPosition(getPositionOf(r0));
        if (r4 != r1.id) goto L10;
        boolean r3 = true;
    L11:
        r2.setSelected(r3, true);
        r5.setPressed(false);
        return;
    L10:
        r3 = false;
        goto L11
    L14:
        return;
    }

    private /* synthetic */ void lambda$showList$4(ValueAnimator r1) {
        invalidate();
    }

    private /* synthetic */ void lambda$updateItemsAnimated$0(View r7) {
        if ((r7 instanceof EntryView) == false) goto L13;
        int r0 = this.listView.getChildAdapterPosition(r7);
        UItem r1 = this.listView.adapter.getItem(r0);
        if (r1 == null) goto L14;
        EntryView r2 = (EntryView) r7;
        r2.setPosition(getPositionOf(r0));
        if (this.selectedStory != r1.id) goto L10;
        boolean r02 = true;
    L11:
        r2.setSelected(r02, true);
        r2.setChecked(this.selectedStories.contains(Integer.valueOf(r1.id)), true);
        r7.setPressed(false);
        return;
    L10:
        r02 = false;
        goto L11
    L14:
        return;
    }

    private void onItemClick(UItem r1, View r2, int r3, float r4, float r5) {
        onSwitchToStory(r1.id, (StoryEntry) r1.object);
    }

    private void updateItemsAnimated() {
        AndroidUtilities.forEachViews(this.listView, new MultipleStoriesSelector$$ExternalSyntheticLambda6(this));
    }

    private void whenReordered(int r4, ArrayList<UItem> r5) {
        this.selectedOrder.clear();
        int r42 = r5.size();
        int r0 = 0;
    L3:
        if (r0 >= r42) goto L5;
        UItem r1 = r5.get(r0);
        r0 = r0 + 1;
        this.selectedOrder.add(Integer.valueOf(r1.id));
        goto L3
    L5:
        updateItemsAnimated();
    }

    public abstract boolean customBlur();

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r20) {
        float r1 = this.buttonBounce.getScale(0.1f);
        r20.save();
        this.buttonBounds.set(getWidth() - AndroidUtilities.dp(42.0f), getHeight() - AndroidUtilities.dp(34.0f), getWidth() - AndroidUtilities.dp(12.0f), getHeight() - AndroidUtilities.dp(4.0f));
        this.buttonTouchBounds.set(this.buttonBounds);
        this.buttonTouchBounds.inset(-AndroidUtilities.dp(8.0f), -AndroidUtilities.dp(8.0f));
        r20.scale(r1, r1, this.buttonBounds.centerX(), this.buttonBounds.centerY());
        RectF r12 = this.buttonBounds;
        drawBlur(r20, r12, r12.width() / 2.0f, 1.0f);
        this.strokePaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.strokePaint.setAlpha(255);
        r20.drawCircle(this.buttonBounds.centerX(), this.buttonBounds.centerY(), (this.buttonBounds.width() / 2.0f) - AndroidUtilities.dp(0.9f), this.strokePaint);
        Text r13 = this.counter;
        if (r13 == null) goto L6;
        r13.draw(r20, this.buttonBounds.centerX() - (this.counter.getCurrentWidth() / 2.0f), this.buttonBounds.centerY() - AndroidUtilities.dp(0.6f), -1, 1.0f - this.listView.getAlpha());
    L6:
        if (this.listView.getAlpha() <= 0.0f) goto L8;
        r20.save();
        r20.translate(this.buttonBounds.centerX(), this.buttonBounds.centerY());
        this.strokePaint.setAlpha((int) (this.listView.getAlpha() * 255.0f));
        r20.drawPath(this.closePath, this.strokePaint);
        r20.restore();
    L8:
        r20.restore();
        if (this.hint == null) goto L13;
        float r6 = this.animatedHint.set(this.hintShown);
        if (r6 <= 0.0f) goto L13;
        float r14 = AndroidUtilities.lerp(0.6f, 1.0f, r6);
        float r5 = (AndroidUtilities.dp(11.0f) + this.hint.getWidth()) + AndroidUtilities.dp(11.0f);
        float r11 = AndroidUtilities.dp(32.0f);
        RectF r122 = this.hintBounds;
        RectF r132 = this.buttonBounds;
        float r142 = r132.right - r5;
        float r133 = (r132.top - AndroidUtilities.dp(9.66f)) - r11;
        RectF r4 = this.buttonBounds;
        r122.set(r142, r133, r4.right, r4.top - AndroidUtilities.dp(9.66f));
        RectF r42 = this.hintBounds;
        float r7 = r42.right - (r42.width() * r14);
        RectF r8 = this.hintBounds;
        float r123 = r8.bottom - (r8.height() * r14);
        RectF r82 = this.hintBounds;
        r42.set(r7, r123, r82.right, r82.bottom);
        this.hintBounds.offset(0.0f, AndroidUtilities.dp(4.0f) * (1.0f - r6));
        this.hintClipPath.rewind();
        float r43 = AndroidUtilities.dp(8.0f);
        RectF r72 = this.hintArc;
        RectF r83 = this.hintBounds;
        float r10 = r83.left;
        float r84 = r83.top;
        r72.set(r10, r84, r10 + r43, r84 + r43);
        this.hintClipPath.arcTo(this.hintArc, 180.0f, 90.0f, false);
        RectF r73 = this.hintArc;
        RectF r85 = this.hintBounds;
        float r102 = r85.right;
        float r86 = r85.top;
        r73.set(r102 - r43, r86, r102, r86 + r43);
        this.hintClipPath.arcTo(this.hintArc, 270.0f, 90.0f, false);
        RectF r74 = this.hintArc;
        RectF r87 = this.hintBounds;
        float r9 = r87.right;
        float r88 = r87.bottom;
        r74.set(r9 - r43, r88 - r43, r9, r88);
        this.hintClipPath.arcTo(this.hintArc, 0.0f, 90.0f, false);
        this.hintClipPath.lineTo(this.hintBounds.right - AndroidUtilities.dp(8.0f), this.hintBounds.bottom);
        this.hintClipPath.lineTo(this.hintBounds.right - AndroidUtilities.dp(14.5f), this.hintBounds.bottom + AndroidUtilities.dp(5.66f));
        this.hintClipPath.lineTo(this.hintBounds.right - AndroidUtilities.dp(21.0f), this.hintBounds.bottom);
        RectF r3 = this.hintArc;
        RectF r75 = this.hintBounds;
        float r89 = r75.left;
        float r76 = r75.bottom;
        r3.set(r89, r76 - r43, r89 + r43, r76);
        this.hintClipPath.arcTo(this.hintArc, 90.0f, 90.0f, false);
        this.hintClipPath.close();
        this.hintBounds.bottom += AndroidUtilities.dp(5.66f);
        r20.save();
        r20.clipPath(this.hintClipPath);
        drawBlur(r20, this.hintBounds, r43, r6);
        r20.restore();
        r20.save();
        RectF r32 = this.hintBounds;
        r20.scale(r14, r14, r32.right, r32.bottom);
        this.hint.draw(r20, (this.buttonBounds.right - r5) + AndroidUtilities.dp(11.0f), (this.buttonBounds.top - AndroidUtilities.dp(9.66f)) - (r11 / 2.0f), -1, r6);
        r20.restore();
    L13:
        super.dispatchDraw(r20);
    }

    public abstract void drawBlur(BlurringShader.StoryBlurDrawer r1, Canvas r2, RectF r3, float r4, boolean r5, float r6, float r7, boolean r8, float r9);

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r7, View r8, long r9) {
        UniversalRecyclerView r0 = this.listView;
        if (r8 != r0) goto L7;
        this.listBounds.set(r0.getX(), this.listView.getY(), this.listView.getX() + this.listView.getWidth(), this.listView.getY() + this.listView.getHeight());
        AndroidUtilities.scaleRect(this.listBounds, this.listView.getScaleX(), this.listView.getX() + this.listView.getPivotX(), this.listView.getY() + this.listView.getPivotY());
        drawBlur(r7, this.listBounds, AndroidUtilities.dp(10.0f), this.listView.getAlpha());
        this.clipPath.rewind();
        this.clipPath.addRoundRect(this.listBounds, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), Path.Direction.CW);
        r7.save();
        r7.clipPath(this.clipPath);
        boolean r6 = super.drawChild(r7, r8, r9);
        r7.restore();
        return r6;
    L7:
        return super.drawChild(r7, r8, r9);
    }

    public boolean onBackPressed() {
        if (this.listShown == false) goto L6;
        showList(false, true);
        return true;
    L6:
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        this.listView.setPivotX(r1.getWidth() - AndroidUtilities.dp(15.0f));
        this.listView.setPivotY(r0.getHeight());
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(176.0f), TLObject.FLAG_30));
    }

    public abstract void onSwitchToStory(int r1, StoryEntry r2);

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r6) {
        boolean r0 = this.buttonBounds.contains(r6.getX(), r6.getY());
        if (r6.getAction() != 0) goto L12;
        this.buttonBounce.setPressed(r0);
        if (this.listShown == false) goto L25;
        if (r0 == true) goto L25;
        if (this.listBounds.contains(r6.getX(), r6.getY()) == true) goto L25;
        showList(false, true);
        return true;
    L25:
        if (this.buttonBounce.isPressed() == false) goto L27;
    L30:
        return true;
    L27:
        if (super.onTouchEvent(r6) == true) goto L30;
        return false;
    L12:
        if (r6.getAction() != 2) goto L16;
        if (r0 == true) goto L25;
        this.buttonBounce.setPressed(false);
        goto L25
    L16:
        if (r6.getAction() != 1) goto L22;
        if (this.buttonBounce.isPressed() == false) goto L20;
        showList(!this.listShown, true);
    L20:
        this.buttonBounce.setPressed(false);
        goto L25
    L22:
        if (r6.getAction() != 3) goto L25;
        this.buttonBounce.setPressed(false);
        goto L25
    }

    public void set(ArrayList<StoryEntry> r4, ArrayList<Integer> r5, ArrayList<Integer> r6) {
        showList(false, false);
        this.stories = r4;
        this.selectedOrder = r5;
        this.selectedStories = r6;
        this.counter = new Text(Integer.toString(r4.size()), 20.0f, AndroidUtilities.getTypeface("fonts/num.otf"));
        this.hint = new Text(LocaleController.formatPluralStringComma("HintViewStoriesMultiple", r4.size()), 14.0f);
        this.listView.adapter.update(false);
    }

    public void setSelected(int r3) {
        if (this.selectedStory != r3) goto L5;
        return;
    L5:
        this.selectedStory = r3;
        AndroidUtilities.forEachViews(this.listView, new MultipleStoriesSelector$$ExternalSyntheticLambda0(this, r3));
    }

    public void showHint() {
        if (this.hintShown == false) goto L5;
        return;
    L5:
        if (this.listShown == true) goto L13;
        int r0 = MessagesController.getGlobalMainSettings().getInt("multistorieshint", 0);
        if (r0 >= 3) goto L14;
        MessagesController.getGlobalMainSettings().edit().putInt("multistorieshint", r0 + 1).apply();
        AndroidUtilities.cancelRunOnUIThread(this.hideHint);
        this.hintShown = true;
        invalidate();
        AndroidUtilities.runOnUIThread(this.hideHint, 5500);
        return;
    L14:
        return;
    }

    public void showList(boolean r6, boolean r7) {
        if (this.listShown == r6) goto L39;
        this.listShown = r6;
        this.listView.animate().cancel();
        UniversalRecyclerView r0 = this.listView;
        float r1 = 0.0f;
        float r3 = 0.65f;
        if (r7 == false) goto L18;
        r0.setVisibility(0);
        ViewPropertyAnimator r72 = this.listView.animate();
        if (r6 == false) goto L10;
        r1 = 1.0f;
    L10:
        ViewPropertyAnimator r73 = r72.alpha(r1);
        if (r6 == false) goto L13;
        float r02 = 1.0f;
    L14:
        ViewPropertyAnimator r74 = r73.scaleX(r02);
        if (r6 == false) goto L17;
        r3 = 1.0f;
    L17:
        r74.scaleY(r3).setListener(new AnonymousClass2(this, r6)).setUpdateListener(new MultipleStoriesSelector$$ExternalSyntheticLambda5(this)).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(360).start();
    L32:
        if (r6 == true) goto L34;
        return;
    L34:
        if (this.hintShown == false) goto L38;
        this.hintShown = false;
        invalidate();
        return;
    L38:
        return;
    L13:
        r02 = 0.65f;
        goto L14
    L18:
        if (r6 == false) goto L20;
        int r75 = 0;
    L21:
        r0.setVisibility(r75);
        UniversalRecyclerView r76 = this.listView;
        if (r6 == false) goto L24;
        r1 = 1.0f;
    L24:
        r76.setAlpha(r1);
        UniversalRecyclerView r77 = this.listView;
        if (r6 == false) goto L27;
        float r03 = 1.0f;
    L28:
        r77.setScaleX(r03);
        UniversalRecyclerView r78 = this.listView;
        if (r6 == false) goto L31;
        r3 = 1.0f;
    L31:
        r78.setScaleY(r3);
        invalidate();
        goto L32
    L27:
        r03 = 0.65f;
        goto L28
    L20:
        r75 = 8;
        goto L21
    }

    public void update() {
        this.listView.adapter.update(false);
    }
}
