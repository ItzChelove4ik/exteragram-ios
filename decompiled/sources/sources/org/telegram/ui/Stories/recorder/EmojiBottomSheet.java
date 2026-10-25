package org.telegram.ui.Stories.recorder;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.text.Editable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ContextLinkCell;
import org.telegram.ui.Cells.StickerSetNameCell;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedFileDrawable;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CloseProgressDrawable2;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.DrawingInBackgroundThreadDrawable;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EmojiTabsStrip;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.ExtendedGridLayoutManager;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LoadingSpan;
import org.telegram.ui.Components.PermissionRequest;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.Reactions.ReactionImageHolder;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.RecyclerAnimationScrollHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SearchStateDrawable;
import org.telegram.ui.Components.Size;
import org.telegram.ui.Components.StickerCategoriesListView;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.ContentPreviewViewer;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.SelectAnimatedEmojiDialog;
import org.telegram.ui.Stories.StoryReactionWidgetBackground;
import org.telegram.ui.Stories.recorder.Weather;
import org.telegram.ui.WrappedResourceProvider;
import org.telegram.ui.recyclerview.LinearSmoothScrollerCustom;

/* loaded from: classes7.dex */
public class EmojiBottomSheet extends BottomSheet implements NotificationCenter.NotificationCenterDelegate {
    public static int savedPosition = 1;
    private int categoryIndex;
    private Utilities.Callback2<Bitmap, Float> drawBlurBitmap;
    private final boolean greeting;
    private float maxPadding;
    private Utilities.Callback3Return<Object, TLRPC.Document, Boolean, Boolean> onDocumentSelected;
    private Runnable onPlusSelected;
    private Utilities.CallbackReturn<Integer, Boolean> onWidgetSelected;
    private final boolean onlyStickers;
    public final TLRPC.Document plus;
    private String query;
    private TabsView tabsView;
    private float top;
    private final ViewPagerFixed viewPager;
    private boolean wasKeyboardVisible;
    public final TLRPC.Document widgets;

    /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends TLRPC.Document {
        final /* synthetic */ EmojiBottomSheet this$0;

        public AnonymousClass1(EmojiBottomSheet r1) {
            this.this$0 = r1;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends TLRPC.Document {
        final /* synthetic */ EmojiBottomSheet this$0;

        public AnonymousClass2(EmojiBottomSheet r1) {
            this.this$0 = r1;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends BaseFragment {
        final /* synthetic */ EmojiBottomSheet this$0;

        /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$3$1, reason: invalid class name */
        public class AnonymousClass1 extends WrappedResourceProvider {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1, Theme.ResourcesProvider r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.WrappedResourceProvider
            public void appendColors() {
                this.sparseIntArray.append(Theme.key_dialogBackground, -14803426);
                this.sparseIntArray.append(Theme.key_windowBackgroundGray, -16777216);
            }
        }

        public AnonymousClass3(EmojiBottomSheet r1) {
            this.this$0 = r1;
            this.currentAccount = EmojiBottomSheet.access$3900(r1);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Activity getParentActivity() {
            return LaunchActivity.instance;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Theme.ResourcesProvider getResourceProvider() {
            return new AnonymousClass1(this, EmojiBottomSheet.access$4000(this.this$0));
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public boolean isLightStatusBar() {
            return false;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Dialog showDialog(Dialog r1) {
            r1.show();
            return r1;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends ViewPagerFixed {
        final /* synthetic */ EmojiBottomSheet this$0;

        public AnonymousClass4(EmojiBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabAnimationUpdate(boolean r2) {
            if (EmojiBottomSheet.m19954$$Nest$fgettabsView(this.this$0) == null) goto L5;
            EmojiBottomSheet.m19954$$Nest$fgettabsView(this.this$0).setType(EmojiBottomSheet.m19956$$Nest$fgetviewPager(this.this$0).getPositionAnimated());
        L5:
            EmojiBottomSheet.access$4100(this.this$0).invalidate();
            invalidate();
            EmojiBottomSheet.savedPosition = EmojiBottomSheet.m19956$$Nest$fgetviewPager(this.this$0).getCurrentPosition();
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends ViewPagerFixed.Adapter {
        final /* synthetic */ EmojiBottomSheet this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ boolean val$onlyStickers;

        public AnonymousClass5(EmojiBottomSheet r1, boolean r2, Context r3) {
            this.this$0 = r1;
            this.val$onlyStickers = r2;
            this.val$context = r3;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r1, int r2, int r3) {
            IPage r12 = (IPage) r1;
            if (this.val$onlyStickers == false) goto L5;
            r2 = 1;
        L5:
            r12.bind(r2);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r2) {
            if (r2 != 1) goto L7;
            return new GifPage(this.this$0, this.val$context);
        L7:
            return new Page(this.this$0, this.val$context);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            if (this.val$onlyStickers == false) goto L6;
            return 1;
        L6:
            return 3;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemViewType(int r1) {
            if (r1 != 0) goto L4;
            return 0;
        L4:
            if (r1 == 1) goto L9;
            return 1;
        L9:
            return 0;
        }
    }

    public class ContainerView extends FrameLayout {
        private final Paint backgroundBlurPaint;
        private final Paint backgroundPaint;
        private Bitmap blurBitmap;
        private Matrix blurBitmapMatrix;
        private BitmapShader blurBitmapShader;
        private final Paint handlePaint;
        private final RectF handleRect;
        private final AnimatedFloat isActionBarT;
        private Boolean overStatusBar;
        final /* synthetic */ EmojiBottomSheet this$0;

        public ContainerView(EmojiBottomSheet r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            this.backgroundPaint = new Paint(1);
            this.backgroundBlurPaint = new Paint(3);
            this.handlePaint = new Paint(1);
            this.isActionBarT = new AnimatedFloat(this, 0, 250, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.handleRect = new RectF();
        }

        private void setupBlurBitmap() {
            if (this.blurBitmap == null) goto L5;
            return;
        L5:
            if (EmojiBottomSheet.access$4200(this.this$0) != null) goto L10;
            if (Theme.isCurrentThemeDark() == true) goto L12;
            return;
        L12:
            if (EmojiBottomSheet.m19947$$Nest$fgetdrawBlurBitmap(this.this$0) != null) goto L14;
            return;
        L14:
            if (SharedConfig.getDevicePerformanceClass() > 0) goto L16;
            return;
        L16:
            if (LiteMode.isPowerSaverApplied() == true) goto L28;
            Point r0 = AndroidUtilities.displaySize;
            Bitmap r02 = Bitmap.createBitmap(r0.x / 16, r0.y / 16, Bitmap.Config.ARGB_8888);
            EmojiBottomSheet.m19947$$Nest$fgetdrawBlurBitmap(this.this$0).run(r02, Float.valueOf(16.0f));
            Utilities.stackBlurBitmap(r02, 8);
            this.blurBitmap = r02;
            Paint r03 = this.backgroundBlurPaint;
            Bitmap r3 = this.blurBitmap;
            Shader.TileMode r4 = Shader.TileMode.CLAMP;
            BitmapShader r1 = new BitmapShader(r3, r4, r4);
            this.blurBitmapShader = r1;
            r03.setShader(r1);
            if (this.blurBitmapMatrix != null) goto L21;
            this.blurBitmapMatrix = new Matrix();
        L21:
            this.blurBitmapMatrix.postScale(16.0f, 16.0f);
            this.blurBitmapShader.setLocalMatrix(this.blurBitmapMatrix);
            invalidate();
            return;
        L28:
            return;
        L10:
            if (EmojiBottomSheet.access$4300(this.this$0).isDark() == true) goto L12;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r12) {
            this.backgroundPaint.setColor(Theme.getColor(Theme.key_dialogBackground, EmojiBottomSheet.access$4400(this.this$0)));
            Paint r0 = this.backgroundPaint;
            if (this.blurBitmap != null) goto L5;
            float r1 = 1.0f;
        L6:
            r0.setAlpha((int) (r1 * 255.0f));
            View[] r02 = EmojiBottomSheet.m19956$$Nest$fgetviewPager(this.this$0).getViewPages();
            EmojiBottomSheet.m19960$$Nest$fputtop(this.this$0, 0.0f);
            boolean r13 = false;
            int r4 = 0;
        L8:
            if (r4 >= r02.length) goto L16;
            View r5 = r02[r4];
            if ((r5 instanceof IPage) == false) goto L15;
            IPage r52 = (IPage) r5;
            EmojiBottomSheet r6 = this.this$0;
            EmojiBottomSheet.m19960$$Nest$fputtop(r6, EmojiBottomSheet.m19955$$Nest$fgettop(r6) + (r52.top() * Utilities.clamp(1.0f - Math.abs(r52.getTranslationX() / r52.getMeasuredWidth()), 1.0f, 0.0f)));
            if (r52.getVisibility() != 0) goto L15;
            r52.updateTops();
        L15:
            r4 = r4 + 1;
            goto L8
        L16:
            AnimatedFloat r03 = this.isActionBarT;
            if (EmojiBottomSheet.m19955$$Nest$fgettop(this.this$0) > 0.0f) goto L19;
            float r42 = 1.0f;
        L20:
            float r04 = r03.set(r42);
            float r43 = (EmojiBottomSheet.m19955$$Nest$fgettop(this.this$0) + EmojiBottomSheet.m19956$$Nest$fgetviewPager(this.this$0).getPaddingTop()) - AndroidUtilities.lerp(AndroidUtilities.dp(8.0f), EmojiBottomSheet.m19956$$Nest$fgetviewPager(this.this$0).getPaddingTop(), r04);
            RectF r62 = AndroidUtilities.rectTmp;
            r62.set(EmojiBottomSheet.access$4500(this.this$0), r43, getWidth() - EmojiBottomSheet.access$4600(this.this$0), getHeight() + AndroidUtilities.dp(8.0f));
            if (this.blurBitmap == null) goto L24;
            this.blurBitmapMatrix.reset();
            this.blurBitmapMatrix.postScale(16.0f, 16.0f);
            this.blurBitmapMatrix.postTranslate(0.0f, -getY());
            this.blurBitmapShader.setLocalMatrix(this.blurBitmapMatrix);
            r12.drawRoundRect(r62, AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), this.backgroundBlurPaint);
        L24:
            if (r62.top >= AndroidUtilities.statusBarHeight) goto L26;
            boolean r3 = true;
        L27:
            Boolean r8 = this.overStatusBar;
            if (r8 != null) goto L30;
        L31:
            this.overStatusBar = Boolean.valueOf(r3);
            Window r82 = this.this$0.getWindow();
            if (r3 == true) goto L34;
        L36:
            AndroidUtilities.setLightStatusBar(r82, r13);
        L37:
            float r2 = 1.0f - r04;
            r12.drawRoundRect(r62, AndroidUtilities.dp(14.0f) * r2, AndroidUtilities.dp(14.0f) * r2, this.backgroundPaint);
            this.handleRect.set((getWidth() - AndroidUtilities.dp(36.0f)) / 2.0f, AndroidUtilities.dp(9.66f) + r43, (getWidth() + AndroidUtilities.dp(36.0f)) / 2.0f, r43 + AndroidUtilities.dp(13.66f));
            this.handlePaint.setColor(1367573379);
            this.handlePaint.setAlpha((int) (r2 * 81.0f));
            r12.drawRoundRect(this.handleRect, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.handlePaint);
            r12.save();
            r12.clipRect(r62);
            super.dispatchDraw(r12);
            r12.restore();
            return;
        L34:
            if (AndroidUtilities.computePerceivedBrightness(this.backgroundPaint.getColor()) < 0.721f) goto L36;
            r13 = true;
            goto L36
        L30:
            if (r8.booleanValue() == r3) goto L37;
        L26:
            r3 = false;
            goto L27
        L19:
            r42 = 0.0f;
            goto L20
        L5:
            r1 = 0.85f;
            goto L6
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L9;
            if (r3.getY() >= EmojiBottomSheet.m19955$$Nest$fgettop(this.this$0)) goto L9;
            this.this$0.dismiss();
            return true;
        L9:
            return super.dispatchTouchEvent(r3);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            Bitmap r0 = this.blurBitmap;
            if (r0 == null) goto L5;
            r0.recycle();
        L5:
            this.backgroundBlurPaint.setShader(null);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            setupBlurBitmap();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            int r62 = View.MeasureSpec.getSize(r6);
            int r72 = View.MeasureSpec.getSize(r7);
            EmojiBottomSheet.m19958$$Nest$fputmaxPadding(this.this$0, Math.min(r72 * 0.45f, (AndroidUtilities.dp(350.0f) / 0.55f) * 0.45f));
            EmojiBottomSheet.m19956$$Nest$fgetviewPager(this.this$0).setPadding(0, AndroidUtilities.statusBarHeight, 0, 0);
            EmojiBottomSheet.m19956$$Nest$fgetviewPager(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30));
            if (EmojiBottomSheet.m19954$$Nest$fgettabsView(this.this$0) == null) goto L5;
            EmojiBottomSheet.m19954$$Nest$fgettabsView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30), 0);
        L5:
            setMeasuredDimension(r62, r72);
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            invalidate();
        }
    }

    public static class EmojiListView extends RecyclerListView {
        private float bottomBound;
        public boolean emoji;
        private boolean invalidated;
        private final ArrayList<DrawingInBackgroundLine> lineDrawables;
        private final ArrayList<DrawingInBackgroundLine> lineDrawablesTmp;
        private RecyclerAnimationScrollHelper scrollHelper;
        public boolean smoothScrolling;
        private float topBound;
        private final ArrayList<ArrayList<EmojiImageView>> unusedArrays;
        private final ArrayList<DrawingInBackgroundLine> unusedLineDrawables;
        private final SparseArray<ArrayList<EmojiImageView>> viewsGroupedByLines;
        private final ColorFilter whiteFilter;

        /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$EmojiListView$1, reason: invalid class name */
        public class AnonymousClass1 extends RecyclerAnimationScrollHelper.AnimationCallback {
            final /* synthetic */ EmojiListView this$0;

            public AnonymousClass1(EmojiListView r1) {
                this.this$0 = r1;
            }

            @Override // org.telegram.ui.Components.RecyclerAnimationScrollHelper.AnimationCallback
            public void onEndAnimation() {
                this.this$0.smoothScrolling = false;
            }

            @Override // org.telegram.ui.Components.RecyclerAnimationScrollHelper.AnimationCallback
            public void onPreAnimation() {
                this.this$0.smoothScrolling = true;
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$EmojiListView$2, reason: invalid class name */
        public class AnonymousClass2 extends LinearSmoothScrollerCustom {
            final /* synthetic */ EmojiListView this$0;

            public AnonymousClass2(EmojiListView r1, Context r2, int r3) {
                this.this$0 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.recyclerview.LinearSmoothScrollerCustom
            public void onEnd() {
                this.this$0.smoothScrolling = false;
            }

            @Override // org.telegram.ui.recyclerview.LinearSmoothScrollerCustom, androidx.recyclerview.widget.RecyclerView.SmoothScroller
            public void onStart() {
                this.this$0.smoothScrolling = true;
            }
        }

        public class DrawingInBackgroundLine extends DrawingInBackgroundThreadDrawable {
            ArrayList<EmojiImageView> drawInBackgroundViews;
            ArrayList<EmojiImageView> imageViewEmojis;
            boolean lite;
            public int position;
            public int startOffset;
            final /* synthetic */ EmojiListView this$0;

            public DrawingInBackgroundLine(EmojiListView r1) {
                this.this$0 = r1;
                this.drawInBackgroundViews = new ArrayList();
                this.lite = LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS);
            }

            private void drawImage(Canvas r3, Drawable r4, EmojiImageView r5, float r6) {
                if (r4 == null) goto L6;
                r4.setAlpha((int) (r6 * 255.0f));
                r4.draw(r3);
                return;
            L6:
                if (r5.imageReceiver == null) goto L9;
                r3.save();
                r3.clipRect(r5.imageReceiver.getImageX(), r5.imageReceiver.getImageY(), r5.imageReceiver.getImageX2(), r5.imageReceiver.getImageY2());
                r5.imageReceiver.setAlpha(r6);
                r5.imageReceiver.draw(r3);
                r3.restore();
                return;
            }

            private float getAspectRatio(ImageReceiver r3) {
                if (r3 != null) goto L5;
                return 1.0f;
            L5:
                RLottieDrawable r0 = r3.getLottieAnimation();
                if (r0 != null) goto L8;
            L12:
                AnimatedFileDrawable r02 = r3.getAnimation();
                if (r02 != null) goto L15;
            L17:
                Bitmap r03 = r3.getBitmap();
                if (r03 == null) goto L20;
                float r2 = r03.getWidth();
                int r32 = r03.getHeight();
            L11:
                return r2 / r32;
            L20:
                Drawable r33 = r3.getStaticThumb();
                if (r33 != null) goto L23;
            L25:
                return 1.0f;
            L23:
                if (r33.getIntrinsicHeight() == 0) goto L25;
                r2 = r33.getIntrinsicWidth();
                r32 = r33.getIntrinsicHeight();
                goto L11
            L15:
                if (r02.getIntrinsicHeight() == 0) goto L17;
                r2 = r02.getIntrinsicWidth();
                r32 = r02.getIntrinsicHeight();
                goto L11
            L8:
                if (r0.getIntrinsicHeight() == 0) goto L12;
                r2 = r0.getIntrinsicWidth();
                r32 = r0.getIntrinsicHeight();
                goto L11
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void draw(Canvas r8, long r9, int r11, int r12, float r13) {
                if (this.imageViewEmojis != null) goto L5;
                return;
            L5:
                int r1 = 0;
                boolean r2 = true;
                if (this.this$0.isAnimating() == false) goto L8;
            L13:
                boolean r0 = true;
            L14:
                if (r0 == false) goto L16;
            L21:
                r2 = r0;
            L22:
                if (r2 == false) goto L25;
                prepareDraw(System.currentTimeMillis());
                drawInUiThread(r8, r13);
                reset();
                return;
            L25:
                super.draw(r8, r9, r11, r12, r13);
                return;
            L16:
                if (r1 >= this.imageViewEmojis.size()) goto L21;
                if (this.imageViewEmojis.get(r1).getScale() != 1.0f) goto L22;
                r1 = r1 + 1;
                goto L16
            L8:
                if (this.imageViewEmojis.size() <= 4) goto L13;
                if (this.lite == false) goto L13;
                r0 = false;
                goto L14
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void drawInBackground(Canvas r5) {
                int r0 = 0;
            L4:
                if (r0 >= this.drawInBackgroundViews.size()) goto L12;
                EmojiImageView r1 = this.drawInBackgroundViews.get(r0);
                if (r1.notDraw == true) goto L11;
                AnimatedEmojiDrawable r2 = r1.drawable;
                if (r2 == null) goto L10;
                r2.setColorFilter(EmojiListView.m19962$$Nest$fgetwhiteFilter(this.this$0));
            L10:
                r1.imageReceiverToDraw.draw(r5, r1.backgroundThreadDrawHolder[this.threadIndex]);
            L11:
                r0 = r0 + 1;
                goto L4
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void drawInUiThread(Canvas r11, float r12) {
                if (this.imageViewEmojis == null) goto L32;
                r11.save();
                r11.translate(-this.startOffset, 0.0f);
                int r0 = 0;
            L6:
                if (r0 >= this.imageViewEmojis.size()) goto L26;
                EmojiImageView r1 = this.imageViewEmojis.get(r0);
                if (r1.notDraw == true) goto L25;
                float r2 = r1.getScale();
                float r3 = r1.getAlpha() * r12;
                Rect r4 = AndroidUtilities.rectTmp2;
                r4.set(((int) r1.getX()) + r1.getPaddingLeft(), r1.getPaddingTop(), (((int) r1.getX()) + r1.getWidth()) - r1.getPaddingRight(), r1.getHeight() - r1.getPaddingBottom());
                AnimatedEmojiDrawable r5 = r1.drawable;
                if (r5 == null) goto L13;
                r5.setBounds(r4);
            L13:
                ImageReceiver r6 = r1.imageReceiver;
                if (r6 == null) goto L17;
                r6.setImageCoords(r4);
            L17:
                if (EmojiListView.m19962$$Nest$fgetwhiteFilter(this.this$0) == null) goto L22;
                if (r1.drawable == null) goto L22;
                r1.drawable.setColorFilter(EmojiListView.m19962$$Nest$fgetwhiteFilter(this.this$0));
            L22:
                if (r2 == 1.0f) goto L24;
                r11.save();
                r11.scale(r2, r2, r4.centerX(), r4.centerY());
                drawImage(r11, r5, r1, r3);
                r11.restore();
                goto L25
            L24:
                drawImage(r11, r5, r1, r3);
            L25:
                r0 = r0 + 1;
                goto L6
            L26:
                r11.restore();
                return;
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void onFrameReady() {
                super.onFrameReady();
                int r0 = 0;
            L4:
                if (r0 >= this.drawInBackgroundViews.size()) goto L9;
                ImageReceiver.BackgroundThreadDrawHolder r1 = this.drawInBackgroundViews.get(r0).backgroundThreadDrawHolder[this.threadIndex];
                if (r1 == null) goto L8;
                r1.release();
            L8:
                r0 = r0 + 1;
                goto L4
            L9:
                this.this$0.invalidate();
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void prepareDraw(long r11) {
                this.drawInBackgroundViews.clear();
                int r1 = 0;
            L4:
                if (r1 >= this.imageViewEmojis.size()) goto L25;
                EmojiImageView r2 = this.imageViewEmojis.get(r1);
                if (r2.notDraw == true) goto L24;
                AnimatedEmojiDrawable r3 = r2.drawable;
                if (r3 == null) goto L11;
                ImageReceiver r32 = r3.getImageReceiver();
            L12:
                if (r32 == null) goto L24;
                r32.setAlpha(r2.getAlpha());
                AnimatedEmojiDrawable r4 = r2.drawable;
                if (r4 == null) goto L17;
                r4.setColorFilter(EmojiListView.m19962$$Nest$fgetwhiteFilter(this.this$0));
            L17:
                ImageReceiver.BackgroundThreadDrawHolder[] r42 = r2.backgroundThreadDrawHolder;
                int r5 = this.threadIndex;
                r42[r5] = r32.setDrawInBackgroundThread(r42[r5], r5);
                r2.backgroundThreadDrawHolder[this.threadIndex].time = r11;
                r2.imageReceiverToDraw = r32;
                r2.update(r11);
                Rect r43 = AndroidUtilities.rectTmp2;
                r43.set(r2.getPaddingLeft(), r2.getPaddingTop(), r2.getWidth() - r2.getPaddingRight(), r2.getHeight() - r2.getPaddingBottom());
                float r33 = getAspectRatio(r32);
                if (r33 >= 1.0f) goto L21;
                float r52 = (r43.height() * r33) / 2.0f;
                int r34 = (int) (r43.centerX() - r52);
                r43.left = r34;
                r43.right = (int) (r43.centerX() + r52);
            L23:
                r43.offset((r2.getLeft() + ((int) r2.getTranslationX())) - this.startOffset, 0);
                r2.backgroundThreadDrawHolder[this.threadIndex].setBounds(r43);
                this.drawInBackgroundViews.add(r2);
                goto L24
            L21:
                if (r33 <= 1.0f) goto L23;
                float r53 = (r43.width() / r33) / 2.0f;
                int r35 = (int) (r43.centerY() - r53);
                r43.top = r35;
                r43.bottom = (int) (r43.centerY() + r53);
                goto L23
            L11:
                r32 = r2.imageReceiver;
            L24:
                r1 = r1 + 1;
                goto L4
            }
        }

        public static class EmojiImageView extends View {
            boolean attached;
            public ImageReceiver.BackgroundThreadDrawHolder[] backgroundThreadDrawHolder;
            private final ButtonBounce bounce;
            private final int currentAccount;
            private long documentId;
            public AnimatedEmojiDrawable drawable;
            public boolean emoji;
            public ImageReceiver imageReceiver;
            public ImageReceiver imageReceiverToDraw;
            private final EmojiListView listView;
            public boolean notDraw;

            public EmojiImageView(Context r4, EmojiListView r5) {
                super(r4);
                this.currentAccount = UserConfig.selectedAccount;
                this.backgroundThreadDrawHolder = new ImageReceiver.BackgroundThreadDrawHolder[2];
                this.bounce = new ButtonBounce(this);
                setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f));
                this.listView = r5;
            }

            public float getScale() {
                return this.bounce.getScale(0.15f);
            }

            @Override // android.view.View
            public void invalidate() {
                this.listView.invalidate();
            }

            @Override // android.view.View
            public void onAttachedToWindow() {
                super.onAttachedToWindow();
                this.attached = true;
                AnimatedEmojiDrawable r0 = this.drawable;
                if (r0 == null) goto L5;
                r0.addView(this);
            L5:
                ImageReceiver r1 = this.imageReceiver;
                if (r1 == null) goto L9;
                r1.onAttachedToWindow();
                return;
            }

            @Override // android.view.View
            public void onDetachedFromWindow() {
                super.onDetachedFromWindow();
                this.attached = false;
                AnimatedEmojiDrawable r0 = this.drawable;
                if (r0 == null) goto L5;
                r0.removeView(this);
            L5:
                ImageReceiver r1 = this.imageReceiver;
                if (r1 == null) goto L9;
                r1.onDetachedFromWindow();
                return;
            }

            @Override // android.view.View
            public void onDraw(Canvas r7) {
                ImageReceiver r0 = this.imageReceiver;
                if (r0 == null) goto L6;
                r0.setImageCoords(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
                this.imageReceiver.draw(r7);
                return;
            L6:
                AnimatedEmojiDrawable r02 = this.drawable;
                if (r02 == null) goto L10;
                r02.setBounds(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
                this.drawable.draw(r7);
                return;
            }

            @Override // android.view.View
            public void onMeasure(int r1, int r2) {
                int r12 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r1), TLObject.FLAG_30);
                super.onMeasure(r12, r12);
            }

            public void setDrawable(Drawable r3) {
                AnimatedEmojiDrawable r0 = this.drawable;
                if (r0 == null) goto L5;
                r0.removeView(this);
            L5:
                this.drawable = null;
                this.documentId = 0;
                this.emoji = false;
                if (this.imageReceiver != null) goto L10;
                ImageReceiver r02 = new ImageReceiver();
                this.imageReceiver = r02;
                r02.setLayerNum(7);
                this.imageReceiver.setAspectFit(true);
                if (this.attached == false) goto L10;
                this.imageReceiver.onAttachedToWindow();
            L10:
                this.imageReceiver.setImageBitmap(r3);
            }

            public void setEmoji(TLRPC.Document r7, boolean r8) {
                long r0 = this.documentId;
                if (r7 != null) goto L5;
                long r4 = 0;
            L7:
                if (r0 == r4) goto L20;
                AnimatedEmojiDrawable r02 = this.drawable;
                if (r02 == null) goto L12;
                r02.removeView(this);
            L12:
                if (r7 == null) goto L17;
                this.emoji = true;
                this.documentId = r7.id;
                AnimatedEmojiDrawable r72 = AnimatedEmojiDrawable.make(this.currentAccount, EmojiBottomSheet.getCacheType(r8), r7);
                this.drawable = r72;
                if (this.attached == false) goto L19;
                r72.addView(this);
                return;
            L19:
                return;
            L17:
                this.emoji = false;
                this.documentId = 0;
                this.drawable = null;
                return;
            L20:
                return;
            L5:
                r4 = r7.id;
                goto L7
            }

            public void setEmojiId(long r4, boolean r6) {
                if (this.documentId == r4) goto L17;
                AnimatedEmojiDrawable r0 = this.drawable;
                if (r0 == null) goto L9;
                r0.removeView(this);
            L9:
                if (r4 == 0) goto L14;
                this.emoji = true;
                this.documentId = r4;
                AnimatedEmojiDrawable r42 = AnimatedEmojiDrawable.make(this.currentAccount, EmojiBottomSheet.getCacheType(r6), r4);
                this.drawable = r42;
                if (this.attached == false) goto L16;
                r42.addView(this);
                return;
            L16:
                return;
            L14:
                this.emoji = false;
                this.documentId = 0;
                this.drawable = null;
                return;
            }

            @Override // android.view.View
            public void setPressed(boolean r1) {
                super.setPressed(r1);
                this.bounce.setPressed(r1);
            }

            public void setSticker(TLRPC.Document r15) {
                this.emoji = false;
                if (r15 == null) goto L25;
                long r0 = this.documentId;
                long r2 = r15.id;
                if (r0 == r2) goto L30;
                this.documentId = r2;
                if (this.imageReceiver != null) goto L12;
                ImageReceiver r02 = new ImageReceiver();
                this.imageReceiver = r02;
                r02.setLayerNum(7);
                this.imageReceiver.setAspectFit(true);
                if (this.attached == false) goto L12;
                this.imageReceiver.onAttachedToWindow();
            L12:
                ImageReceiver r03 = this.imageReceiver;
                if (this.emoji == true) goto L15;
                View r22 = this;
            L16:
                r03.setParentView(r22);
                TLRPC.PhotoSize r04 = FileLoader.getClosestPhotoSizeWithSize(r15.thumbs, 90);
                if ("video/webm".equals(r15.mime_type) == false) goto L19;
                String r23 = "80_80_g";
            L21:
                if (LiteMode.isEnabled(1) == true) goto L23;
                r23 = r23.concat("_firstframe");
            L23:
                ImageReceiver r3 = this.imageReceiver;
                ImageLocation r4 = ImageLocation.getForDocument(r15);
                r3.setImage(r4, r23, ImageLocation.getForDocument(r04, r15), "80_80", null, 0, null, r15, 0);
                return;
            L19:
                r23 = "80_80";
                goto L21
            L15:
                r22 = this.listView;
                goto L16
            L30:
                return;
            L25:
                ImageReceiver r152 = this.imageReceiver;
                if (r152 == null) goto L29;
                this.documentId = 0;
                r152.clearImage();
                return;
            }

            public void update(long r3) {
                ImageReceiver r0 = this.imageReceiverToDraw;
                if (r0 != null) goto L5;
                return;
            L5:
                if (r0.getLottieAnimation() == null) goto L8;
                this.imageReceiverToDraw.getLottieAnimation().updateCurrentFrame(r3, true);
            L8:
                if (this.imageReceiverToDraw.getAnimation() == null) goto L12;
                this.imageReceiverToDraw.getAnimation().updateCurrentFrame(r3, true);
                return;
            }
        }

        /* renamed from: -$$Nest$fgetwhiteFilter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ColorFilter m19962$$Nest$fgetwhiteFilter(EmojiListView r0) {
            return r0.whiteFilter;
        }

        /* renamed from: -$$Nest$mscrollToPosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19963$$Nest$mscrollToPosition(EmojiListView r0, int r1, int r2) {
            r0.scrollToPosition(r1, r2);
        }

        public EmojiListView(Context r3) {
            super(r3);
            this.smoothScrolling = false;
            this.viewsGroupedByLines = new SparseArray();
            this.unusedArrays = new ArrayList();
            this.unusedLineDrawables = new ArrayList();
            this.lineDrawables = new ArrayList();
            this.lineDrawablesTmp = new ArrayList();
            this.whiteFilter = new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN);
        }

        private void scrollToPosition(int r5, int r6) {
            if (this.scrollHelper != null) goto L5;
            return;
        L5:
            if ((getLayoutManager() instanceof GridLayoutManager) == false) goto L22;
            GridLayoutManager r0 = (GridLayoutManager) getLayoutManager();
            View r1 = r0.findViewByPosition(r5);
            int r2 = r0.findFirstVisibleItemPosition();
            if (r1 != null) goto L12;
            if (Math.abs(r5 - r2) <= (r0.getSpanCount() * 9.0f)) goto L12;
        L13:
            RecyclerAnimationScrollHelper r12 = this.scrollHelper;
            if (r0.findFirstVisibleItemPosition() >= r5) goto L16;
            int r02 = 0;
        L17:
            r12.setScrollDirection(r02);
            this.scrollHelper.scrollToPosition(r5, r6, false, true);
            return;
        L16:
            r02 = 1;
        L12:
            if (SharedConfig.animationsEnabled() == false) goto L13;
            AnonymousClass2 r13 = new AnonymousClass2(this, getContext(), 2);
            r13.setTargetPosition(r5);
            r13.setOffset(r6);
            r0.startSmoothScroll(r13);
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r13) {
            if (getVisibility() == 0) goto L5;
            return;
        L5:
            int r0 = 0;
            this.invalidated = false;
            int r1 = r13.getSaveCount();
            r13.save();
            r13.clipRect(0.0f, this.topBound, getWidth(), this.bottomBound);
            if (this.emoji == true) goto L10;
            super.dispatchDraw(r13);
            r13.restore();
            return;
        L10:
            if (this.selectorRect.isEmpty() == true) goto L15;
            this.selectorDrawable.setBounds(this.selectorRect);
            r13.save();
            Consumer<Canvas> r2 = this.selectorTransformer;
            if (r2 == null) goto L14;
            r2.accept(r13);
        L14:
            this.selectorDrawable.draw(r13);
            r13.restore();
        L15:
            int r22 = 0;
        L16:
            int r3 = this.viewsGroupedByLines.size();
            SparseArray<ArrayList<EmojiImageView>> r4 = this.viewsGroupedByLines;
            if (r22 >= r3) goto L19;
            ArrayList<EmojiImageView> r32 = r4.valueAt(r22);
            r32.clear();
            this.unusedArrays.add(r32);
            r22 = r22 + 1;
            goto L16
        L19:
            r4.clear();
            int r23 = 0;
        L21:
            if (r23 >= getChildCount()) goto L42;
            View r33 = getChildAt(r23);
            if ((r33 instanceof EmojiImageView) == false) goto L41;
            EmojiImageView r34 = (EmojiImageView) r33;
            if (r34.getY() >= this.bottomBound) goto L41;
            if ((r34.getY() + r34.getHeight()) <= this.topBound) goto L41;
            if (this.smoothScrolling == false) goto L32;
            int r42 = (int) r34.getY();
        L33:
            ArrayList<EmojiImageView> r5 = this.viewsGroupedByLines.get(r42);
            if (r5 == null) goto L36;
        L40:
            r5.add(r34);
            goto L41
        L36:
            if (this.unusedArrays.isEmpty() == true) goto L38;
            r5 = this.unusedArrays.remove(r5.size() - 1);
        L39:
            this.viewsGroupedByLines.put(r42, r5);
            goto L40
        L38:
            r5 = new ArrayList();
            goto L39
        L32:
            r42 = r34.getTop();
        L41:
            r23 = r23 + 1;
            goto L21
        L42:
            this.lineDrawablesTmp.clear();
            this.lineDrawablesTmp.addAll(this.lineDrawables);
            this.lineDrawables.clear();
            r13.save();
            r13.clipRect(0, getPaddingTop(), getWidth(), getHeight() - getPaddingBottom());
            long r7 = System.currentTimeMillis();
            int r24 = 0;
        L43:
            DrawingInBackgroundLine r43 = null;
            if (r24 >= this.viewsGroupedByLines.size()) goto L64;
            ArrayList<EmojiImageView> r35 = this.viewsGroupedByLines.valueAt(r24);
            EmojiImageView r52 = r35.get(0);
            int r6 = getChildAdapterPosition(r52);
            int r9 = 0;
        L47:
            if (r9 >= this.lineDrawablesTmp.size()) goto L52;
            if (this.lineDrawablesTmp.get(r9).position == r6) goto L50;
            r9 = r9 + 1;
            goto L47
        L50:
            r43 = this.lineDrawablesTmp.get(r9);
            this.lineDrawablesTmp.remove(r9);
        L52:
            if (r43 == null) goto L54;
        L58:
            this.lineDrawables.add(r43);
            r43.imageViewEmojis = r35;
            r13.save();
            r13.translate(r52.getLeft(), r52.getY());
            r43.startOffset = r52.getLeft();
            int r92 = getMeasuredWidth() - (r52.getLeft() * 2);
            int r10 = r52.getMeasuredHeight();
            if (r92 <= 0) goto L62;
            if (r10 <= 0) goto L62;
            Canvas r62 = r13;
            r43.draw(r62, r7, r92, r10, getAlpha());
        L63:
            r62.restore();
            r24 = r24 + 1;
            r13 = r62;
        L62:
            r62 = r13;
            goto L63
        L54:
            if (this.unusedLineDrawables.isEmpty() == true) goto L56;
            r43 = this.unusedLineDrawables.remove(r4.size() - 1);
        L57:
            r43.position = r6;
            r43.onAttachToWindow();
            goto L58
        L56:
            r43 = new DrawingInBackgroundLine(this);
            r43.setLayerNum(7);
            goto L57
        L64:
            Canvas r63 = r13;
            int r132 = 0;
        L66:
            if (r132 >= this.lineDrawablesTmp.size()) goto L72;
            if (this.unusedLineDrawables.size() >= 3) goto L70;
            this.unusedLineDrawables.add(this.lineDrawablesTmp.get(r132));
            this.lineDrawablesTmp.get(r132).imageViewEmojis = null;
            this.lineDrawablesTmp.get(r132).reset();
        L71:
            r132 = r132 + 1;
            goto L66
        L70:
            this.lineDrawablesTmp.get(r132).onDetachFromWindow();
            goto L71
        L72:
            this.lineDrawablesTmp.clear();
        L74:
            if (r0 >= getChildCount()) goto L86;
            View r133 = getChildAt(r0);
            if (r133 == null) goto L85;
            if ((r133 instanceof EmojiImageView) == true) goto L85;
            if (r133.getY() > (getHeight() - getPaddingBottom())) goto L85;
            if ((r133.getY() + r133.getHeight()) < getPaddingTop()) goto L85;
            r63.save();
            r63.translate((int) r133.getX(), (int) r133.getY());
            r133.draw(r63);
            r63.restore();
        L85:
            r0 = r0 + 1;
            goto L74
        L86:
            r63.restore();
            r63.restoreToCount(r1);
        }

        public void setBounds(float r1, float r2) {
            this.topBound = r1;
            this.bottomBound = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void setLayoutManager(RecyclerView.LayoutManager r2) {
            super.setLayoutManager(r2);
            this.scrollHelper = null;
            if ((r2 instanceof LinearLayoutManager) == false) goto L6;
            RecyclerAnimationScrollHelper r0 = new RecyclerAnimationScrollHelper(this, (LinearLayoutManager) r2);
            this.scrollHelper = r0;
            r0.setAnimationCallback(new AnonymousClass1(this));
            this.scrollHelper.setScrollListener(new EmojiBottomSheet$EmojiListView$$ExternalSyntheticLambda0(this));
            return;
        }
    }

    public class GifPage extends IPage implements NotificationCenter.NotificationCenterDelegate {
        public GifAdapter adapter;
        private final ArrayList<TLRPC.BotInlineResult> gifs;
        public ExtendedGridLayoutManager layoutManager;
        public RecyclerListView listView;
        private final ArrayList<TLRPC.Document> mygifs;
        private ContentPreviewViewer.ContentPreviewViewerDelegate previewDelegate;
        public SearchField searchField;
        final /* synthetic */ EmojiBottomSheet this$0;

        /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$GifPage$1, reason: invalid class name */
        public class AnonymousClass1 extends RecyclerListView {
            final /* synthetic */ GifPage this$1;
            final /* synthetic */ EmojiBottomSheet val$this$0;

            public AnonymousClass1(GifPage r1, Context r2, EmojiBottomSheet r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
            public boolean onInterceptTouchEvent(MotionEvent r7) {
                ContentPreviewViewer r0 = ContentPreviewViewer.getInstance();
                GifPage r1 = this.this$1;
                boolean r72 = r0.onInterceptTouchEvent(r7, r1.listView, 0, GifPage.m19967$$Nest$fgetpreviewDelegate(r1), this.resourcesProvider);
                if (super.onInterceptTouchEvent(r7) == true) goto L8;
                if (r72 == true) goto L10;
                return false;
            L10:
                return true;
            L8:
                return true;
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$GifPage$2, reason: invalid class name */
        public class AnonymousClass2 extends RecyclerView.ItemDecoration {
            final /* synthetic */ GifPage this$1;
            final /* synthetic */ EmojiBottomSheet val$this$0;

            public AnonymousClass2(GifPage r1, EmojiBottomSheet r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
                if (this.this$1.layoutManager.isLastInRow(r3.getChildAdapterPosition(r2)) == false) goto L5;
                int r0 = 0;
            L6:
                r1.right = r0;
                r1.bottom = AndroidUtilities.dp(4.0f);
                return;
            L5:
                r0 = AndroidUtilities.dp(4.0f);
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$GifPage$3, reason: invalid class name */
        public class AnonymousClass3 extends RecyclerView.OnScrollListener {
            final /* synthetic */ GifPage this$1;
            final /* synthetic */ EmojiBottomSheet val$this$0;

            public AnonymousClass3(GifPage r1, EmojiBottomSheet r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r1, int r2, int r3) {
                EmojiBottomSheet.access$000(this.this$1.this$0).invalidate();
                if (EmojiBottomSheet.access$100(this.this$1.this$0) == false) goto L12;
                GifPage r12 = this.this$1;
                if (r12.listView.scrollingByUser == false) goto L12;
                SearchField r13 = r12.searchField;
                if (r13 == null) goto L12;
                if (SearchField.m19986$$Nest$fgeteditText(r13) == null) goto L12;
                this.this$1.this$0.closeKeyboard();
            L12:
                if ((this.this$1.layoutManager.findLastCompletelyVisibleItemPosition() + 7) < (this.this$1.adapter.getItemCount() - 1)) goto L15;
                GifAdapter.m19971$$Nest$mrequest(this.this$1.adapter);
                return;
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$GifPage$4, reason: invalid class name */
        public class AnonymousClass4 implements ContentPreviewViewer.ContentPreviewViewerDelegate {
            final /* synthetic */ GifPage this$1;

            public AnonymousClass4(GifPage r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
            public boolean canSchedule() {
                return false;
            }

            @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
            public long getDialogId() {
                return 0;
            }

            @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
            public boolean isInScheduleMode() {
                return false;
            }

            @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
            public boolean isPhotoEditor() {
                return true;
            }

            @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
            public boolean needSend(int r1) {
                return false;
            }

            @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
            public void openSet(TLRPC.InputStickerSet r1, boolean r2) {
            }
        }

        public class GifAdapter extends RecyclerListView.SelectionAdapter {
            private TLRPC.User bot;
            private int currentReqId;
            private String offset;
            private String query;
            private boolean requestedBot;
            private boolean requesting;
            private Runnable searchRunnable;
            final /* synthetic */ GifPage this$1;

            public static /* synthetic */ void $r8$lambda$2SPsyeaEnLW8O_87CeFn5lDh6yU(GifAdapter r0, TLObject r1, String r2, boolean r3) {
                r0.lambda$request$2(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$Fimq5Ak0ikWtcT9psqNpPRZHc7c(GifAdapter r0, boolean r1, TLRPC.TL_messages_getInlineBotResults r2, String r3, TLObject r4, TLRPC.TL_error r5) {
                r0.lambda$request$5(r1, r2, r3, r4, r5);
            }

            /* renamed from: $r8$lambda$Kua113-DtW0wMaGa1Bz7YChIXaA, reason: not valid java name */
            public static /* synthetic */ void m19968$r8$lambda$Kua113DtW0wMaGa1Bz7YChIXaA(GifAdapter r0, String r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
                r0.lambda$request$3(r1, r2, r3, r4);
            }

            public static /* synthetic */ void $r8$lambda$LORDLlOSE_Yvc9nd0eQd_vbTIDA(GifAdapter r0) {
                r0.request();
            }

            public static /* synthetic */ void $r8$lambda$QRt6zM62u5vGAo2QTrEjT6K84vs(GifAdapter r0, TLObject r1, TLRPC.TL_error r2) {
                r0.lambda$request$1(r1, r2);
            }

            /* renamed from: $r8$lambda$ey4fP-ZfTKnK-upTAp0ai0f8Nc4, reason: not valid java name */
            public static /* synthetic */ void m19969$r8$lambda$ey4fPZfTKnKupTAp0ai0f8Nc4(GifAdapter r0, TLObject r1, boolean r2, TLRPC.TL_messages_getInlineBotResults r3, String r4) {
                r0.lambda$request$4(r1, r2, r3, r4);
            }

            /* renamed from: $r8$lambda$zkRgI8-YOjwweyGp-QPH6Wiu3Wk, reason: not valid java name */
            public static /* synthetic */ void m19970$r8$lambda$zkRgI8YOjwweyGpQPH6Wiu3Wk(GifAdapter r0, TLObject r1) {
                r0.lambda$request$0(r1);
            }

            /* renamed from: -$$Nest$mrequest, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19971$$Nest$mrequest(GifAdapter r0) {
                r0.request();
            }

            /* renamed from: -$$Nest$mupdateRecent, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19972$$Nest$mupdateRecent(GifAdapter r0, boolean r1) {
                r0.updateRecent(r1);
            }

            private GifAdapter(GifPage r1) {
                this.this$1 = r1;
                this.searchRunnable = new EmojiBottomSheet$GifPage$GifAdapter$$ExternalSyntheticLambda0(this);
                this.currentReqId = -1;
                this.requesting = false;
            }

            public /* synthetic */ GifAdapter(GifPage r1, EmojiBottomSheetIA r2) {
                this(r1);
            }

            private /* synthetic */ void lambda$request$0(TLObject r5) {
                if ((r5 instanceof TLRPC.TL_contacts_resolvedPeer) == false) goto L5;
                TLRPC.TL_contacts_resolvedPeer r52 = (TLRPC.TL_contacts_resolvedPeer) r5;
                MessagesController.getInstance(EmojiBottomSheet.access$1900(this.this$1.this$0)).putUsers(r52.users, false);
                MessagesController.getInstance(EmojiBottomSheet.access$2000(this.this$1.this$0)).putChats(r52.chats, false);
                MessagesStorage.getInstance(EmojiBottomSheet.access$2100(this.this$1.this$0)).putUsersAndChats(r52.users, r52.chats, true, true);
            L5:
                this.requestedBot = true;
                request();
            }

            private /* synthetic */ void lambda$request$1(TLObject r1, TLRPC.TL_error r2) {
                AndroidUtilities.runOnUIThread(new EmojiBottomSheet$GifPage$GifAdapter$$ExternalSyntheticLambda3(this, r1));
            }

            private /* synthetic */ void lambda$request$2(TLObject r2, String r3, boolean r4) {
                if (this.requesting == true) goto L6;
                return;
            L6:
                if ((r2 instanceof TLRPC.messages_BotResults) == false) goto L11;
                TLRPC.messages_BotResults r22 = (TLRPC.messages_BotResults) r2;
                MessagesStorage.getInstance(EmojiBottomSheet.access$1800(this.this$1.this$0)).saveBotCache(r3, r22);
                this.offset = r22.next_offset;
                if (r4 == false) goto L10;
                GifPage.m19965$$Nest$fgetgifs(this.this$1).clear();
            L10:
                GifPage.m19965$$Nest$fgetgifs(this.this$1).size();
                GifPage.m19965$$Nest$fgetgifs(this.this$1).addAll(r22.results);
                notifyDataSetChanged();
            L11:
                this.this$1.searchField.showProgress(false);
                this.requesting = false;
            }

            private /* synthetic */ void lambda$request$3(String r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
                AndroidUtilities.runOnUIThread(new EmojiBottomSheet$GifPage$GifAdapter$$ExternalSyntheticLambda6(this, r3, r1, r2));
            }

            private /* synthetic */ void lambda$request$4(TLObject r2, boolean r3, TLRPC.TL_messages_getInlineBotResults r4, String r5) {
                if (this.requesting == true) goto L6;
                return;
            L6:
                if ((r2 instanceof TLRPC.messages_BotResults) == false) goto L12;
                TLRPC.messages_BotResults r22 = (TLRPC.messages_BotResults) r2;
                this.offset = r22.next_offset;
                if (r3 == false) goto L10;
                GifPage.m19965$$Nest$fgetgifs(this.this$1).clear();
            L10:
                GifPage.m19965$$Nest$fgetgifs(this.this$1).size();
                GifPage.m19965$$Nest$fgetgifs(this.this$1).addAll(r22.results);
                notifyDataSetChanged();
                this.this$1.searchField.showProgress(false);
                this.requesting = false;
                return;
            L12:
                this.currentReqId = ConnectionsManager.getInstance(EmojiBottomSheet.access$1700(this.this$1.this$0)).sendRequest(r4, new EmojiBottomSheet$GifPage$GifAdapter$$ExternalSyntheticLambda5(this, r5, r3));
            }

            private /* synthetic */ void lambda$request$5(boolean r2, TLRPC.TL_messages_getInlineBotResults r3, String r4, TLObject r5, TLRPC.TL_error r6) {
                AndroidUtilities.runOnUIThread(new EmojiBottomSheet$GifPage$GifAdapter$$ExternalSyntheticLambda4(this, r5, r2, r3, r4));
            }

            private void request() {
                if (this.requesting == true) goto L30;
                this.requesting = true;
                this.this$1.searchField.showProgress(true);
                if (this.currentReqId < 0) goto L9;
                ConnectionsManager.getInstance(EmojiBottomSheet.access$1000(this.this$1.this$0)).cancelRequest(this.currentReqId, true);
                this.currentReqId = -1;
            L9:
                if (this.bot != null) goto L13;
                TLObject r0 = MessagesController.getInstance(EmojiBottomSheet.access$1200(this.this$1.this$0)).getUserOrChat(MessagesController.getInstance(EmojiBottomSheet.access$1100(this.this$1.this$0)).gifSearchBot);
                if ((r0 instanceof TLRPC.User) == false) goto L13;
                this.bot = (TLRPC.User) r0;
            L13:
                TLRPC.User r02 = this.bot;
                if (r02 == null) goto L16;
            L19:
                if (r02 != null) goto L21;
                return;
            L21:
                TLRPC.TL_messages_getInlineBotResults r03 = new TLRPC.TL_messages_getInlineBotResults();
                r03.bot = MessagesController.getInstance(EmojiBottomSheet.access$1500(this.this$1.this$0)).getInputUser(this.bot);
                String r1 = this.query;
                String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
                if (r1 != null) goto L24;
                r1 = _UrlKt.FRAGMENT_ENCODE_SET;
            L24:
                r03.query = r1;
                boolean r12 = TextUtils.isEmpty(this.offset);
                String r3 = this.offset;
                if (r3 == null) goto L28;
                r2 = r3;
            L28:
                r03.offset = r2;
                r03.peer = new TLRPC.TL_inputPeerEmpty();
                String r22 = "gif_search_" + r03.query + "_" + r03.offset;
                MessagesStorage.getInstance(EmojiBottomSheet.access$1600(this.this$1.this$0)).getBotCache(r22, new EmojiBottomSheet$GifPage$GifAdapter$$ExternalSyntheticLambda2(this, r12, r03, r22));
                return;
            L16:
                if (this.requestedBot == true) goto L19;
                TLRPC.TL_contacts_resolveUsername r04 = new TLRPC.TL_contacts_resolveUsername();
                r04.username = MessagesController.getInstance(EmojiBottomSheet.access$1300(this.this$1.this$0)).gifSearchBot;
                this.currentReqId = ConnectionsManager.getInstance(EmojiBottomSheet.access$1400(this.this$1.this$0)).sendRequest(r04, new EmojiBottomSheet$GifPage$GifAdapter$$ExternalSyntheticLambda1(this));
                return;
            }

            private void updateRecent(boolean r3) {
                GifPage.m19966$$Nest$fgetmygifs(this.this$1).clear();
                GifPage.m19966$$Nest$fgetmygifs(this.this$1).addAll(MediaDataController.getInstance(EmojiBottomSheet.access$900(this.this$1.this$0)).getRecentGifs());
                if (r3 == false) goto L6;
                notifyDataSetChanged();
                return;
            }

            public Object getItem(int r2) {
                int r22 = r2 - 1;
                if (GifPage.m19966$$Nest$fgetmygifs(this.this$1).isEmpty() == true) goto L13;
                if (TextUtils.isEmpty(this.query) == false) goto L13;
                if (r22 >= 0) goto L8;
            L11:
                r22 = r22 - GifPage.m19966$$Nest$fgetmygifs(this.this$1).size();
                goto L13
            L8:
                if (r22 >= GifPage.m19966$$Nest$fgetmygifs(this.this$1).size()) goto L11;
                return GifPage.m19966$$Nest$fgetmygifs(this.this$1).get(r22);
            L13:
                if (GifPage.m19965$$Nest$fgetgifs(this.this$1).isEmpty() == false) goto L15;
                return null;
            L15:
                if (GifPage.m19966$$Nest$fgetmygifs(this.this$1).isEmpty() == false) goto L17;
            L19:
                if (r22 >= 0) goto L21;
                return null;
            L21:
                if (r22 < GifPage.m19965$$Nest$fgetgifs(this.this$1).size()) goto L23;
                return null;
            L23:
                return GifPage.m19965$$Nest$fgetgifs(this.this$1).get(r22);
            L17:
                if (TextUtils.isEmpty(this.query) == false) goto L19;
                r22 = r22 - 1;
                goto L19
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                int r1 = 0;
                if (GifPage.m19966$$Nest$fgetmygifs(this.this$1).isEmpty() == false) goto L5;
            L7:
                int r0 = 0;
            L8:
                int r02 = r0 + 1;
                if (GifPage.m19965$$Nest$fgetgifs(this.this$1).isEmpty() == true) goto L18;
                if (GifPage.m19966$$Nest$fgetmygifs(this.this$1).isEmpty() == false) goto L14;
            L16:
                r1 = r1 + GifPage.m19965$$Nest$fgetgifs(this.this$1).size();
                goto L18
            L14:
                if (TextUtils.isEmpty(this.query) == false) goto L16;
                r1 = 1;
            L18:
                return r02 + r1;
            L5:
                if (TextUtils.isEmpty(this.query) == false) goto L7;
                r0 = GifPage.m19966$$Nest$fgetmygifs(this.this$1).size();
                goto L8
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int r2) {
                if (r2 != 0) goto L5;
                return 0;
            L5:
                int r22 = r2 - 1;
                if (GifPage.m19966$$Nest$fgetmygifs(this.this$1).isEmpty() == true) goto L11;
                if (TextUtils.isEmpty(this.query) == false) goto L11;
                r22 = r22 - GifPage.m19966$$Nest$fgetmygifs(this.this$1).size();
            L11:
                if (GifPage.m19965$$Nest$fgetgifs(this.this$1).isEmpty() == false) goto L13;
                return 2;
            L13:
                if (GifPage.m19966$$Nest$fgetmygifs(this.this$1).isEmpty() == false) goto L15;
                return 2;
            L15:
                if (TextUtils.isEmpty(this.query) == false) goto L22;
                if (r22 != 0) goto L23;
                return 1;
            L23:
                return 2;
            L22:
                return 2;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r1) {
                if (r1.getItemViewType() != 2) goto L6;
                return true;
            L6:
                return false;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
                int r0 = r8.getItemViewType();
                if (r0 != 0) goto L7;
                r8.itemView.setTag(34);
                r8.itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, (int) EmojiBottomSheet.m19949$$Nest$fgetmaxPadding(this.this$1.this$0)));
                return;
            L7:
                if (r0 != 2) goto L16;
                ContextLinkCell r02 = (ContextLinkCell) r8.itemView;
                Object r82 = getItem(r9);
                if ((r82 instanceof TLRPC.Document) == false) goto L13;
                r02.setGif((TLRPC.Document) r82, false);
                return;
            L13:
                if ((r82 instanceof TLRPC.BotInlineResult) == false) goto L17;
                r02.setLink((TLRPC.BotInlineResult) r82, this.bot, true, false, false, true);
                return;
            L17:
                return;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r2v3, types: [android.view.View, org.telegram.ui.Cells.StickerSetNameCell] */
            /* JADX WARN: Type inference failed for: r2v5, types: [android.view.View] */
            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
                if (r3 != 0) goto L5;
                ContextLinkCell r22 = new View(this.this$1.getContext());
            L9:
                return new RecyclerListView.Holder(r22);
            L5:
                if (r3 != 1) goto L7;
                ?? r23 = new StickerSetNameCell(this.this$1.getContext(), false, EmojiBottomSheet.access$700(this.this$1.this$0), false);
                r23.setText(LocaleController.getString(R.string.FeaturedGifs), 0);
                RecyclerView.LayoutParams r1 = new RecyclerView.LayoutParams(-1, -2);
                ((ViewGroup.MarginLayoutParams) r1).topMargin = AndroidUtilities.dp(2.5f);
                ((ViewGroup.MarginLayoutParams) r1).bottomMargin = AndroidUtilities.dp(5.5f);
                r23.setLayoutParams(r1);
                r22 = r23;
                goto L9
            L7:
                ContextLinkCell r32 = new ContextLinkCell(this.this$1.getContext());
                r32.getPhotoImage().setLayerNum(7);
                r32.allowButtonBounce(true);
                r32.setIsKeyboard(true);
                r32.setCanPreviewGif(true);
                r22 = r32;
                goto L9
            }

            public void updateItems(String r6) {
                if (TextUtils.equals(this.query, r6) == false) goto L5;
            L8:
                boolean r0 = TextUtils.isEmpty(this.query);
                this.query = r6;
                AndroidUtilities.cancelRunOnUIThread(this.searchRunnable);
                if (TextUtils.isEmpty(r6) == false) goto L12;
                GifPage.m19965$$Nest$fgetgifs(this.this$1).clear();
                this.this$1.searchField.showProgress(false);
                notifyDataSetChanged();
                return;
            L12:
                if (r0 == false) goto L14;
                notifyDataSetChanged();
            L14:
                this.this$1.searchField.showProgress(true);
                AndroidUtilities.runOnUIThread(this.searchRunnable, 1500);
                return;
            L5:
                if (this.currentReqId == (-1)) goto L7;
                ConnectionsManager.getInstance(EmojiBottomSheet.access$800(this.this$1.this$0)).cancelRequest(this.currentReqId, true);
                this.currentReqId = -1;
            L7:
                this.requesting = false;
                this.offset = _UrlKt.FRAGMENT_ENCODE_SET;
                goto L8
            }
        }

        public class GifLayoutManager extends ExtendedGridLayoutManager {
            private final Size size;
            final /* synthetic */ GifPage this$1;

            /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$GifPage$GifLayoutManager$1, reason: invalid class name */
            public class AnonymousClass1 extends GridLayoutManager.SpanSizeLookup {
                final /* synthetic */ GifLayoutManager this$2;
                final /* synthetic */ GifPage val$this$1;

                public AnonymousClass1(GifLayoutManager r1, GifPage r2) {
                    this.this$2 = r1;
                    this.val$this$1 = r2;
                }

                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int r2) {
                    Object r0 = this.this$2.this$1.adapter.getItem(r2);
                    GifLayoutManager r1 = this.this$2;
                    if (r0 != null) goto L7;
                    return r1.getSpanCount();
                L7:
                    return r1.getSpanSizeForItem(r2);
                }
            }

            public GifLayoutManager(GifPage r3, Context r4) {
                this.this$1 = r3;
                super(r4, 100, true);
                this.size = new Size();
                setSpanSizeLookup(new AnonymousClass1(this, r3));
            }

            @Override // org.telegram.ui.Components.ExtendedGridLayoutManager
            public int getFlowItemCount() {
                return getItemCount();
            }

            @Override // org.telegram.ui.Components.ExtendedGridLayoutManager
            public Size getSizeForItem(int r3) {
                this.size.full = false;
                Object r32 = this.this$1.adapter.getItem(r3);
                if ((r32 instanceof TLRPC.BotInlineResult) == false) goto L15;
                TLRPC.BotInlineResult r33 = (TLRPC.BotInlineResult) r32;
                TLRPC.Document r0 = r33.document;
                if (r0 == null) goto L7;
                ArrayList<TLRPC.DocumentAttribute> r34 = r0.attributes;
            L18:
                return getSizeForItem(r0, r34);
            L7:
                TLRPC.WebDocument r1 = r33.content;
                if (r1 == null) goto L10;
                r34 = r1.attributes;
                goto L18
            L10:
                TLRPC.WebDocument r35 = r33.thumb;
                if (r35 == null) goto L13;
                r34 = r35.attributes;
                goto L18
            L13:
                r34 = null;
                goto L18
            L15:
                if ((r32 instanceof TLRPC.Document) == false) goto L19;
                r0 = (TLRPC.Document) r32;
                r34 = r0.attributes;
                goto L18
            L19:
                Size r2 = this.size;
                r2.full = true;
                return r2;
            }

            public Size getSizeForItem(TLRPC.Document r4, List<TLRPC.DocumentAttribute> r5) {
                Size r0 = this.size;
                r0.height = 100.0f;
                r0.width = 100.0f;
                int r1 = 0;
                r0.full = false;
                if (r4 == null) goto L11;
                TLRPC.PhotoSize r42 = FileLoader.getClosestPhotoSizeWithSize(r4.thumbs, 90);
                if (r42 == null) goto L11;
                int r02 = r42.w;
                if (r02 == 0) goto L11;
                int r43 = r42.h;
                if (r43 == 0) goto L11;
                Size r2 = this.size;
                r2.width = r02;
                r2.height = r43;
            L11:
                if (r5 == null) goto L22;
            L13:
                if (r1 >= r5.size()) goto L22;
                TLRPC.DocumentAttribute r44 = r5.get(r1);
                if ((r44 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L20;
                if ((r44 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L20;
                r1 = r1 + 1;
            L20:
                Size r52 = this.size;
                r52.width = r44.w;
                r52.height = r44.h;
            L22:
                return this.size;
            }
        }

        public static /* synthetic */ void $r8$lambda$YITvzXe_OYy72nYYfDY3NsF3jBQ(GifPage r0, String r1, Integer r2) {
            r0.lambda$new$2(r1, r2);
        }

        /* renamed from: $r8$lambda$aSbDKLY4VnhrVV-h2TK8DN-U9Ms, reason: not valid java name */
        public static /* synthetic */ boolean m19964$r8$lambda$aSbDKLY4VnhrVVh2TK8DNU9Ms(GifPage r0, RecyclerListView.OnItemClickListener r1, View r2, MotionEvent r3) {
            return r0.lambda$new$1(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$qwGoZwTYoOQ8KzV2Gq0md3OYxfg(GifPage r0, View r1, int r2) {
            r0.lambda$new$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetgifs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m19965$$Nest$fgetgifs(GifPage r0) {
            return r0.gifs;
        }

        /* renamed from: -$$Nest$fgetmygifs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m19966$$Nest$fgetmygifs(GifPage r0) {
            return r0.mygifs;
        }

        /* renamed from: -$$Nest$fgetpreviewDelegate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ContentPreviewViewer.ContentPreviewViewerDelegate m19967$$Nest$fgetpreviewDelegate(GifPage r0) {
            return r0.previewDelegate;
        }

        public GifPage(EmojiBottomSheet r10, Context r11) {
            this.this$0 = r10;
            super(r10, r11);
            this.previewDelegate = new AnonymousClass4(this);
            this.mygifs = new ArrayList();
            this.gifs = new ArrayList();
            AnonymousClass1 r0 = new AnonymousClass1(this, r11, r10);
            this.listView = r0;
            GifAdapter r1 = new GifAdapter(this, null);
            this.adapter = r1;
            r0.setAdapter(r1);
            RecyclerListView r02 = this.listView;
            GifLayoutManager r12 = new GifLayoutManager(this, r11);
            this.layoutManager = r12;
            r02.setLayoutManager(r12);
            this.listView.addItemDecoration(new AnonymousClass2(this, r10));
            this.listView.setClipToPadding(true);
            this.listView.setVerticalScrollBarEnabled(false);
            EmojiBottomSheet$GifPage$$ExternalSyntheticLambda0 r03 = new EmojiBottomSheet$GifPage$$ExternalSyntheticLambda0(this);
            this.listView.setOnTouchListener(new EmojiBottomSheet$GifPage$$ExternalSyntheticLambda1(this, r03));
            this.listView.setOnItemClickListener(r03);
            this.listView.setOnScrollListener(new AnonymousClass3(this, r10));
            addView(this.listView, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 58.0f, 0.0f, 40.0f));
            SearchField r04 = new SearchField(r11, EmojiBottomSheet.access$200(r10));
            this.searchField = r04;
            r04.setOnSearchQuery(new EmojiBottomSheet$GifPage$$ExternalSyntheticLambda2(this));
            this.searchField.checkCategoriesView(2, false);
            addView(this.searchField, LayoutHelper.createFrame(-1, -2, 48));
        }

        private /* synthetic */ void lambda$new$0(View r3, int r4) {
            Object r32 = this.adapter.getItem(r4);
            if ((r32 instanceof TLRPC.BotInlineResult) == false) goto L6;
            TLRPC.BotInlineResult r33 = (TLRPC.BotInlineResult) r32;
            TLRPC.Document r42 = r33.document;
        L9:
            if (EmojiBottomSheet.m19950$$Nest$fgetonDocumentSelected(this.this$0) == null) goto L11;
            EmojiBottomSheet.m19950$$Nest$fgetonDocumentSelected(this.this$0).run(r33, r42, Boolean.TRUE);
        L11:
            this.this$0.dismiss();
            return;
        L6:
            if ((r32 instanceof TLRPC.Document) == false) goto L13;
            r42 = (TLRPC.Document) r32;
            r33 = null;
            goto L9
        }

        private /* synthetic */ boolean lambda$new$1(RecyclerListView.OnItemClickListener r8, View r9, MotionEvent r10) {
            return ContentPreviewViewer.getInstance().onTouch(r10, this.listView, 0, r8, this.previewDelegate, EmojiBottomSheet.access$2200(this.this$0));
        }

        private /* synthetic */ void lambda$new$2(String r2, Integer r3) {
            EmojiBottomSheet.m19959$$Nest$fputquery(this.this$0, r2);
            EmojiBottomSheet.m19957$$Nest$fputcategoryIndex(this.this$0, r3.intValue());
            this.adapter.updateItems(r2);
        }

        @Override // org.telegram.ui.Stories.recorder.EmojiBottomSheet.IPage
        public void bind(int r2) {
            GifAdapter.m19972$$Nest$mupdateRecent(this.adapter, false);
            if (this.gifs.isEmpty() == true) goto L5;
        L7:
            this.adapter.updateItems(null);
            return;
        L5:
            if (TextUtils.isEmpty(EmojiBottomSheet.m19953$$Nest$fgetquery(this.this$0)) == false) goto L7;
            GifAdapter.m19971$$Nest$mrequest(this.adapter);
            goto L7
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 != NotificationCenter.recentDocumentsDidLoad) goto L6;
            GifAdapter.m19972$$Nest$mupdateRecent(this.adapter, true);
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            NotificationCenter.getInstance(EmojiBottomSheet.access$500(this.this$0)).addObserver(this, NotificationCenter.recentDocumentsDidLoad);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            NotificationCenter.getInstance(EmojiBottomSheet.access$600(this.this$0)).removeObserver(this, NotificationCenter.recentDocumentsDidLoad);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            setPadding(EmojiBottomSheet.access$300(this.this$0), 0, EmojiBottomSheet.access$400(this.this$0), AndroidUtilities.navigationBarHeight);
            super.onMeasure(r5, r6);
        }

        @Override // org.telegram.ui.Stories.recorder.EmojiBottomSheet.IPage
        public float top() {
            int r1 = 0;
        L4:
            if (r1 >= this.listView.getChildCount()) goto L12;
            Object r3 = this.listView.getChildAt(r1).getTag();
            if ((r3 instanceof Integer) == false) goto L11;
            if (((Integer) r3).intValue() != 34) goto L11;
            return Math.max(0, r2.getBottom());
        L11:
            r1 = r1 + 1;
            goto L4
        L12:
            return 0.0f;
        }

        @Override // org.telegram.ui.Stories.recorder.EmojiBottomSheet.IPage
        public void updateTops() {
            this.searchField.setTranslationY(AndroidUtilities.dp(10.0f) + Math.max(0.0f, top()));
        }
    }

    public abstract class IPage extends FrameLayout {
        public int currentType;
        final /* synthetic */ EmojiBottomSheet this$0;

        public IPage(EmojiBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        public abstract void bind(int r1);

        public abstract float top();

        public abstract void updateTops();
    }

    public static class NoEmojiView extends FrameLayout {
        BackupImageView imageView;
        private int lastI;
        TextView textView;

        public NoEmojiView(Context r8, boolean r9) {
            super(r8);
            this.lastI = -1;
            BackupImageView r0 = new BackupImageView(r8);
            this.imageView = r0;
            addView(r0, LayoutHelper.createFrame(36, 36, 17));
            TextView r02 = new TextView(r8);
            this.textView = r02;
            r02.setTextSize(1, 14.0f);
            this.textView.setTextColor(-8553090);
            TextView r82 = this.textView;
            if (r9 == false) goto L6;
            int r92 = R.string.NoEmojiFound;
        L7:
            r82.setText(LocaleController.getString(r92));
            addView(this.textView, LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, 34.0f, 0.0f, 0.0f));
            return;
        L6:
            r92 = R.string.NoStickersFound;
            goto L7
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r4), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) Math.max(AndroidUtilities.dp(170.0f), (AndroidUtilities.displaySize.y * 0.34999996f) - AndroidUtilities.dp(142.0f)), TLObject.FLAG_30));
        }

        public void update() {
            SelectAnimatedEmojiDialog.updateSearchEmptyViewImage(UserConfig.selectedAccount, this.imageView);
        }

        public void update(int r2) {
            if (this.lastI == r2) goto L6;
            this.lastI = r2;
            update();
            return;
        }
    }

    public class Page extends IPage {
        public Adapter adapter;
        public GridLayoutManager layoutManager;
        public EmojiListView listView;
        private float lockTop;
        private boolean resetOnce;
        public SearchField searchField;
        public int spanCount;
        public EmojiTabsStrip tabsStrip;
        final /* synthetic */ EmojiBottomSheet this$0;

        /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$Page$1, reason: invalid class name */
        public class AnonymousClass1 extends GridLayoutManager.SpanSizeLookup {
            final /* synthetic */ Page this$1;
            final /* synthetic */ EmojiBottomSheet val$this$0;

            public AnonymousClass1(Page r1, EmojiBottomSheet r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int r2) {
                if (this.this$1.adapter.getItemViewType(r2) != 2) goto L5;
                return 1;
            L5:
                return this.this$1.spanCount;
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$Page$2, reason: invalid class name */
        public class AnonymousClass2 extends RecyclerView.OnScrollListener {
            final /* synthetic */ Page this$1;
            final /* synthetic */ EmojiBottomSheet val$this$0;

            public AnonymousClass2(Page r1, EmojiBottomSheet r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView r1, int r2) {
                if (r2 == 0) goto L4;
                return;
            L4:
                if (Page.m19973$$Nest$fgetlockTop(this.this$1) >= 0.0f) goto L6;
                return;
            L6:
                if (this.this$1.atTop() == false) goto L11;
                Page.m19974$$Nest$fputlockTop(this.this$1, -1.0f);
                return;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r4, int r5, int r6) {
                EmojiBottomSheet.access$2300(this.this$1.this$0).invalidate();
                int r52 = -1;
                if (Page.m19973$$Nest$fgetlockTop(this.this$1) >= 0.0f) goto L5;
                int r42 = this.this$1.layoutManager.findFirstCompletelyVisibleItemPosition();
            L15:
                int r62 = Adapter.m19978$$Nest$fgetpositionToSection(this.this$1.adapter).size() - 1;
            L16:
                if (r62 < 0) goto L21;
                int r1 = Adapter.m19978$$Nest$fgetpositionToSection(this.this$1.adapter).keyAt(r62);
                int r2 = Adapter.m19978$$Nest$fgetpositionToSection(this.this$1.adapter).valueAt(r62);
                if (r42 >= r1) goto L19;
                r62 = r62 - 1;
                goto L16
            L19:
                r52 = r2;
            L21:
                if (r52 < 0) goto L24;
                this.this$1.tabsStrip.select(r52, true);
            L24:
                if (EmojiBottomSheet.access$2400(this.this$1.this$0) == false) goto L37;
                Page r43 = this.this$1;
                if (r43.listView.scrollingByUser == false) goto L38;
                SearchField r44 = r43.searchField;
                if (r44 != null) goto L30;
                return;
            L30:
                if (SearchField.m19986$$Nest$fgeteditText(r44) == null) goto L40;
                this.this$1.this$0.closeKeyboard();
                return;
            L40:
                return;
            L38:
                return;
            L37:
                return;
            L5:
                int r45 = 0;
            L7:
                if (r45 >= this.this$1.listView.getChildCount()) goto L12;
                View r63 = this.this$1.listView.getChildAt(r45);
                if ((r63.getY() + r63.getHeight()) > (Page.m19973$$Nest$fgetlockTop(this.this$1) + this.this$1.listView.getPaddingTop())) goto L10;
                r45 = r45 + 1;
                goto L7
            L10:
                r42 = this.this$1.listView.getChildAdapterPosition(r63);
            L13:
                if (r42 != (-1)) goto L15;
                return;
            L12:
                r42 = -1;
                goto L13
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$Page$3, reason: invalid class name */
        public class AnonymousClass3 extends EmojiTabsStrip {
            final /* synthetic */ Page this$1;
            final /* synthetic */ EmojiBottomSheet val$this$0;

            public AnonymousClass3(Page r1, Context r2, Theme.ResourcesProvider r3, boolean r4, boolean r5, boolean r6, boolean r7, int r8, Runnable r9, EmojiBottomSheet r10) {
                this.this$1 = r1;
                this.val$this$0 = r10;
                super(r2, r3, r4, r5, r6, r7, r8, r9);
            }

            @Override // org.telegram.ui.Components.EmojiTabsStrip
            public boolean onTabClick(int r4) {
                int r1 = 0;
                if (this.scrollingAnimation == false) goto L5;
                return false;
            L5:
                SearchField r0 = this.this$1.searchField;
                if (r0 != null) goto L8;
            L13:
                Adapter r02 = this.this$1.adapter;
                if (r02 == null) goto L17;
                Adapter.m19982$$Nest$mupdateItems(r02, null);
            L17:
                if (r1 >= Adapter.m19978$$Nest$fgetpositionToSection(this.this$1.adapter).size()) goto L22;
                int r03 = Adapter.m19978$$Nest$fgetpositionToSection(this.this$1.adapter).keyAt(r1);
                if (Adapter.m19978$$Nest$fgetpositionToSection(this.this$1.adapter).valueAt(r1) == r4) goto L23;
                r1 = r1 + 1;
            L23:
                if (r03 < 0) goto L30;
                Page r3 = this.this$1;
                EmojiListView.m19963$$Nest$mscrollToPosition(r3.listView, r03, ((int) r3.lockTop()) - AndroidUtilities.dp(102.0f));
                return true;
            L30:
                return true;
            L22:
                r03 = -1;
                goto L23
            L8:
                if (SearchField.m19983$$Nest$fgetcategoriesListView(r0) == null) goto L13;
                if (SearchField.m19983$$Nest$fgetcategoriesListView(this.this$1.searchField).getSelectedCategory() == null) goto L12;
                EmojiListView.m19963$$Nest$mscrollToPosition(this.this$1.listView, 0, 0);
                SearchField.m19983$$Nest$fgetcategoriesListView(this.this$1.searchField).selectCategory(null);
            L12:
                SearchField.m19983$$Nest$fgetcategoriesListView(this.this$1.searchField).scrollToStart();
                SearchField.m19988$$Nest$mclear(this.this$1.searchField);
                goto L13
            }
        }

        public class Adapter extends RecyclerView.Adapter {
            private String activeQuery;
            private final HashMap<String, ArrayList<Long>> allEmojis;
            private final ArrayList<TLRPC.TL_messages_stickerSet> allStickerSets;
            private final ArrayList<Long> documentIds;
            private final ArrayList<TLRPC.Document> documents;
            private TLRPC.TL_messages_stickerSet faveSet;
            private boolean includeNotFound;
            private int itemsCount;
            private int lastAllSetsCount;
            private String[] lastLang;
            private final ArrayList<EmojiView.EmojiPack> packs;
            private final HashMap<Long, ArrayList<TLRPC.TL_stickerPack>> packsBySet;
            private final SparseIntArray positionToSection;
            private String query;
            private TLRPC.TL_messages_stickerSet recentSet;
            private HashSet<Long> searchDocumentIds;
            private int searchId;
            private final Runnable searchRunnable;
            private final HashMap<Long, Object> setByDocumentId;
            private final TLRPC.TL_inputStickerSetShortName staticEmojiInput;
            private final ArrayList<TLRPC.TL_messages_stickerSet> stickerSets;
            final /* synthetic */ Page this$1;

            public static /* synthetic */ void $r8$lambda$2qnvQoYpRNwvFbRiJPYApNg819Q(Adapter r0, String r1, TLObject r2) {
                r0.lambda$new$0(r1, r2);
            }

            public static /* synthetic */ void $r8$lambda$FRmnd3hwwf8A6or0wakcgl4NF1o(Adapter r0, String r1, TLObject r2, TLRPC.TL_error r3) {
                r0.lambda$new$1(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$G18FCpCGB4eibwiEBMXbcPVTaOU(Adapter r0, String r1, MediaDataController r2, ArrayList r3, String r4) {
                r0.lambda$new$2(r1, r2, r3, r4);
            }

            public static /* synthetic */ void $r8$lambda$h64str5LBjvxJ46jBwCuRXzS_q4(EmojiBottomSheet r0, int r1) {
                EmojiBottomSheet.m19961$$Nest$monWidgetClick(r0, r1);
            }

            public static /* synthetic */ void $r8$lambda$nGajmflhEBxCWR4w2cYdU9cUZEw(Adapter r0) {
                r0.lambda$new$3();
            }

            /* renamed from: -$$Nest$fgetdocumentIds, reason: not valid java name */
            public static /* bridge */ /* synthetic */ ArrayList m19976$$Nest$fgetdocumentIds(Adapter r0) {
                return r0.documentIds;
            }

            /* renamed from: -$$Nest$fgetdocuments, reason: not valid java name */
            public static /* bridge */ /* synthetic */ ArrayList m19977$$Nest$fgetdocuments(Adapter r0) {
                return r0.documents;
            }

            /* renamed from: -$$Nest$fgetpositionToSection, reason: not valid java name */
            public static /* bridge */ /* synthetic */ SparseIntArray m19978$$Nest$fgetpositionToSection(Adapter r0) {
                return r0.positionToSection;
            }

            /* renamed from: -$$Nest$fgetsearchRunnable, reason: not valid java name */
            public static /* bridge */ /* synthetic */ Runnable m19979$$Nest$fgetsearchRunnable(Adapter r0) {
                return r0.searchRunnable;
            }

            /* renamed from: -$$Nest$fgetsetByDocumentId, reason: not valid java name */
            public static /* bridge */ /* synthetic */ HashMap m19980$$Nest$fgetsetByDocumentId(Adapter r0) {
                return r0.setByDocumentId;
            }

            /* renamed from: -$$Nest$fputquery, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19981$$Nest$fputquery(Adapter r0, String r1) {
                r0.query = r1;
            }

            /* renamed from: -$$Nest$mupdateItems, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m19982$$Nest$mupdateItems(Adapter r0, String r1) {
                r0.updateItems(r1);
            }

            public Adapter(Page r1) {
                this.this$1 = r1;
                this.allEmojis = new HashMap();
                this.packsBySet = new HashMap();
                this.setByDocumentId = new HashMap();
                this.allStickerSets = new ArrayList();
                this.stickerSets = new ArrayList();
                this.packs = new ArrayList();
                this.documents = new ArrayList();
                this.documentIds = new ArrayList();
                this.itemsCount = 0;
                this.positionToSection = new SparseIntArray();
                this.searchDocumentIds = new HashSet();
                this.searchRunnable = new EmojiBottomSheet$Page$Adapter$$ExternalSyntheticLambda0(this);
                TLRPC.TL_inputStickerSetShortName r12 = new TLRPC.TL_inputStickerSetShortName();
                this.staticEmojiInput = r12;
                r12.short_name = "StaticEmoji";
            }

            private /* synthetic */ void lambda$new$0(String r4, TLObject r5) {
                if (TextUtils.equals(r4, this.query) == true) goto L5;
                return;
            L5:
                this.itemsCount = 0;
                this.documents.clear();
                this.documentIds.clear();
                this.positionToSection.clear();
                this.stickerSets.clear();
                this.itemsCount++;
                this.documents.add(null);
                this.documentIds.add(0L);
                if ((r5 instanceof TLRPC.TL_messages_stickers) == false) goto L8;
                TLRPC.TL_messages_stickers r52 = (TLRPC.TL_messages_stickers) r5;
                this.documents.addAll(r52.stickers);
                this.itemsCount += r52.stickers.size();
            L8:
                this.activeQuery = this.query;
                notifyDataSetChanged();
                EmojiListView.m19963$$Nest$mscrollToPosition(this.this$1.listView, 0, 0);
                this.this$1.searchField.showProgress(false);
                this.this$1.tabsStrip.showSelected(false);
            }

            private /* synthetic */ void lambda$new$1(String r1, TLObject r2, TLRPC.TL_error r3) {
                AndroidUtilities.runOnUIThread(new EmojiBottomSheet$Page$Adapter$$ExternalSyntheticLambda4(this, r1, r2));
            }

            private /* synthetic */ void lambda$new$2(String r10, MediaDataController r11, ArrayList r12, String r13) {
                if (TextUtils.equals(r10, this.query) == true) goto L5;
                return;
            L5:
                ArrayList<Emoji.EmojiSpanRange> r102 = Emoji.parseEmojis(this.query);
                int r0 = 0;
            L7:
                if (r0 >= r102.size()) goto L10;
                MediaDataController.KeywordResult r1 = new MediaDataController.KeywordResult();     // Catch: Exception -> L109
                r1.emoji = r102.get(r0).code.toString();     // Catch: Exception -> L109
                r12.add(r1);     // Catch: Exception -> L109
            L9:
                r0 = r0 + 1;
                goto L7
            L10:
                this.itemsCount = 0;
                this.documents.clear();
                this.documentIds.clear();
                this.positionToSection.clear();
                this.stickerSets.clear();
                this.itemsCount++;
                this.documents.add(null);
                this.documentIds.add(0L);
                if (this.this$1.currentType != 0) goto L28;
                this.searchDocumentIds.clear();
                int r103 = 0;
            L14:
                if (r103 >= r12.size()) goto L23;
                MediaDataController.KeywordResult r112 = (MediaDataController.KeywordResult) r12.get(r103);
                String r3 = r112.emoji;
                if (r3 == null) goto L22;
                if (r3.startsWith("animated_") == true) goto L22;
                ArrayList<Long> r113 = this.allEmojis.get(r112.emoji);
                if (r113 == null) goto L22;
                this.searchDocumentIds.addAll(r113);
            L22:
                r103 = r103 + 1;
                goto L14
            L23:
                this.documentIds.addAll(this.searchDocumentIds);
                int r104 = 0;
            L25:
                if (r104 >= this.searchDocumentIds.size()) goto L27;
                this.documents.add(null);
                r104 = r104 + 1;
                goto L25
            L27:
                this.itemsCount += this.searchDocumentIds.size();
            L82:
                String r105 = AndroidUtilities.translitSafe((this.query + _UrlKt.FRAGMENT_ENCODE_SET).toLowerCase());
                int r122 = 0;
            L84:
                if (r122 >= this.allStickerSets.size()) goto L97;
                TLRPC.TL_messages_stickerSet r2 = this.allStickerSets.get(r122);
                if (r2 == null) goto L95;
                if (r2.set == null) goto L95;
                String r32 = AndroidUtilities.translitSafe((r2.set.title + _UrlKt.FRAGMENT_ENCODE_SET).toLowerCase());
                if (r32.startsWith(r105) == false) goto L93;
            L94:
                int r33 = this.stickerSets.size();
                this.stickerSets.add(r2);
                this.positionToSection.put(this.itemsCount, r33);
                this.documents.add(null);
                this.itemsCount++;
                this.documents.addAll(r2.documents);
                this.itemsCount += r2.documents.size();
                goto L95
            L93:
                if (r32.contains(" " + r105) == true) goto L94;
            L95:
                r122 = r122 + 1;
                goto L84
            L97:
                if (this.documentIds.size() <= 1) goto L99;
            L101:
                boolean r106 = false;
            L102:
                this.includeNotFound = r106;
                if (r106 == false) goto L105;
                this.itemsCount++;
            L105:
                if (r106 == true) goto L107;
                this.searchId++;
            L107:
                this.activeQuery = this.query;
                notifyDataSetChanged();
                EmojiListView.m19963$$Nest$mscrollToPosition(this.this$1.listView, 0, 0);
                this.this$1.searchField.showProgress(false);
                this.this$1.tabsStrip.showSelected(false);
                return;
            L99:
                if (this.documents.size() > 1) goto L101;
                r106 = true;
                goto L102
            L28:
                HashMap<String, ArrayList<TLRPC.Document>> r107 = r11.getAllStickers();
                int r34 = 0;
            L30:
                if (r34 >= r12.size()) goto L51;
                MediaDataController.KeywordResult r4 = (MediaDataController.KeywordResult) r12.get(r34);
                String r5 = r4.emoji;
                if (r5 == null) goto L50;
                if (r5.startsWith("animated_") == true) goto L50;
                ArrayList<TLRPC.Document> r42 = r107.get(r4.emoji);
                if (r42 == null) goto L50;
                if (r42.isEmpty() == true) goto L50;
                int r52 = 0;
            L43:
                if (r52 >= r42.size()) goto L50;
                TLRPC.Document r6 = r42.get(r52);
                if (r6 == null) goto L49;
                if (this.documents.contains(r6) == true) goto L49;
                this.documents.add(r6);
                this.itemsCount++;
            L49:
                r52 = r52 + 1;
            L50:
                r34 = r34 + 1;
                goto L30
            L51:
                ArrayList<TLRPC.StickerSetCovered> r108 = r11.getFeaturedStickerSets();
                int r114 = 0;
            L53:
                if (r114 >= r12.size()) goto L82;
                MediaDataController.KeywordResult r35 = (MediaDataController.KeywordResult) r12.get(r114);
                String r43 = r35.emoji;
                if (r43 == null) goto L81;
                if (r43.startsWith("animated_") == true) goto L81;
                int r44 = 0;
            L61:
                if (r44 >= r108.size()) goto L81;
                TLRPC.StickerSetCovered r53 = r108.get(r44);
                if ((r53 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L66;
                ArrayList<TLRPC.Document> r54 = ((TLRPC.TL_stickerSetFullCovered) r53).documents;
            L71:
                int r62 = 0;
            L73:
                if (r62 >= r54.size()) goto L80;
                String r7 = MessageObject.findAnimatedEmojiEmoticon(r54.get(r62), null);
                if (r7 == null) goto L79;
                if (r7.contains(r35.emoji) == false) goto L79;
                this.documents.add(r54.get(r62));
                this.itemsCount++;
            L79:
                r62 = r62 + 1;
            L80:
                r44 = r44 + 1;
                goto L61
            L66:
                if (r53.covers.isEmpty() == true) goto L69;
                r54 = r53.covers;
                goto L71
            L69:
                if (r53.cover == null) goto L80;
                ArrayList<TLRPC.Document> r63 = new ArrayList();
                r63.add(r53.cover);
                r54 = r63;
            L81:
                r114 = r114 + 1;
                goto L53
            }

            private /* synthetic */ void lambda$new$3() {
                MediaDataController r1 = MediaDataController.getInstance(EmojiBottomSheet.access$3500(this.this$1.this$0));
                String r0 = this.query;
                if ("premium".equalsIgnoreCase(r0) == false) goto L7;
                ArrayList<TLRPC.Document> r02 = r1.getRecentStickers(7);
                this.itemsCount = 0;
                this.documents.clear();
                this.documentIds.clear();
                this.positionToSection.clear();
                this.stickerSets.clear();
                this.itemsCount++;
                this.documents.add(null);
                this.documentIds.add(0L);
                this.documents.addAll(r02);
                this.itemsCount += r02.size();
                this.activeQuery = this.query;
                notifyDataSetChanged();
                EmojiListView.m19963$$Nest$mscrollToPosition(this.this$1.listView, 0, 0);
                this.this$1.searchField.showProgress(false);
                this.this$1.tabsStrip.showSelected(false);
                return;
            L7:
                if (this.this$1.currentType == 1) goto L9;
            L12:
                String[] r2 = AndroidUtilities.getCurrentKeyboardLanguage();
                String[] r3 = this.lastLang;
                if (r3 != null) goto L15;
            L16:
                MediaDataController.getInstance(EmojiBottomSheet.access$3700(this.this$1.this$0)).fetchNewEmojiKeywords(r2);
            L17:
                this.lastLang = r2;
                r1.getEmojiSuggestions(r2, this.query, false, new EmojiBottomSheet$Page$Adapter$$ExternalSyntheticLambda3(this, r0, r1), null, false, false, false, true, 50, false);
                return;
            L15:
                if (Arrays.equals(r2, r3) == true) goto L17;
            L9:
                if (Emoji.fullyConsistsOfEmojis(this.query) == false) goto L12;
                TLRPC.TL_messages_getStickers r12 = new TLRPC.TL_messages_getStickers();
                r12.emoticon = this.query;
                r12.hash = 0;
                ConnectionsManager.getInstance(EmojiBottomSheet.access$3600(this.this$1.this$0)).sendRequest(r12, new EmojiBottomSheet$Page$Adapter$$ExternalSyntheticLambda2(this, r0));
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r10v14 */
            /* JADX WARN: Type inference failed for: r10v3 */
            /* JADX WARN: Type inference failed for: r10v4, types: [int] */
            /* JADX WARN: Type inference failed for: r13v0 */
            /* JADX WARN: Type inference failed for: r13v1, types: [int] */
            /* JADX WARN: Type inference failed for: r13v3 */
            /* JADX WARN: Type inference failed for: r14v11, types: [boolean, int] */
            /* JADX WARN: Type inference failed for: r14v12 */
            /* JADX WARN: Type inference failed for: r14v13 */
            private void updateItems(String r17) {
                this.query = r17;
                Page r2 = this.this$1;
                boolean r3 = false;
                boolean r4 = true;
                if (r17 == null) goto L6;
                r2.searchField.showProgress(true);
                this.this$1.tabsStrip.showSelected(false);
                AndroidUtilities.cancelRunOnUIThread(this.searchRunnable);
                AndroidUtilities.runOnUIThread(this.searchRunnable, 100);
                return;
            L6:
                r2.tabsStrip.showSelected(true);
                AndroidUtilities.cancelRunOnUIThread(this.searchRunnable);
                MediaDataController r1 = MediaDataController.getInstance(EmojiBottomSheet.access$3000(this.this$1.this$0));
                this.itemsCount = 0;
                this.documents.clear();
                this.documentIds.clear();
                this.positionToSection.clear();
                this.stickerSets.clear();
                this.allStickerSets.clear();
                this.setByDocumentId.clear();
                this.itemsCount++;
                this.documents.add(null);
                this.packs.clear();
                Page r22 = this.this$1;
                if (r22.currentType == 1) goto L9;
            L30:
                ArrayList<TLRPC.TL_messages_stickerSet> r23 = this.stickerSets;
                if (this.this$1.currentType != 0) goto L33;
                int r6 = 5;
            L34:
                r23.addAll(r1.getStickerSets(r6));
                int r24 = 0;
            L36:
                if (r24 >= this.stickerSets.size()) goto L56;
                TLRPC.TL_messages_stickerSet r62 = this.stickerSets.get(r24);
                this.positionToSection.put(this.itemsCount, r24);
                this.documents.add(null);
                this.itemsCount++;
                this.documents.addAll(r62.documents);
                this.itemsCount += r62.documents.size();
                if (r62 != this.recentSet) goto L41;
                Object r7 = "recent";
            L44:
                int r8 = 0;
            L46:
                if (r8 >= r62.documents.size()) goto L48;
                this.setByDocumentId.put(Long.valueOf(r62.documents.get(r8).id), r7);
                r8 = r8 + 1;
                goto L46
            L48:
                EmojiView.EmojiPack r72 = new EmojiView.EmojiPack();
                r72.documents = r62.documents;
                r72.set = r62.set;
                r72.installed = true;
                r72.featured = false;
                r72.expanded = true;
                r72.free = true;
                if (r62 != this.faveSet) goto L52;
                r72.resId = R.drawable.emoji_tabs_faves;
            L54:
                this.packs.add(r72);
                this.allStickerSets.add(r62);
                r24 = r24 + 1;
                goto L36
            L52:
                if (r62 != this.recentSet) goto L54;
                r72.resId = R.drawable.msg_emoji_recent;
                goto L54
            L41:
                if (r62 != this.faveSet) goto L43;
                r7 = "fav";
                goto L44
            L43:
                r7 = r62;
                goto L44
            L56:
                if (this.this$1.currentType != 0) goto L124;
                ArrayList<TLRPC.StickerSetCovered> r63 = r1.getFeaturedEmojiSets();
                if (r63 == null) goto L113;
                int r73 = 0;
            L61:
                if (r73 >= r63.size()) goto L113;
                TLRPC.StickerSetCovered r82 = r63.get(r73);
                if ((r82 instanceof TLRPC.TL_stickerSetNoCovered) == false) goto L69;
                TLRPC.TL_messages_stickerSet r83 = MediaDataController.getInstance(EmojiBottomSheet.access$3100(this.this$1.this$0)).getStickerSet(MediaDataController.getInputStickerSet(r82.set), r3);
                if (r83 == null) goto L112;
                ?? r14 = r4;
            L96:
                if (r83.set == null) goto L112;
                int r32 = 0;
            L100:
                if (r32 >= this.packs.size()) goto L107;
                TLRPC.StickerSet r42 = this.packs.get(r32).set;
                if (r42 == null) goto L106;
                if (r42.id == r83.set.id) goto L112;
            L106:
                r32 = r32 + 1;
                goto L100
            L107:
                this.stickerSets.add(r83);
                this.allStickerSets.add(r83);
                this.positionToSection.put(this.itemsCount, r24);
                r24 = r24 + 1;
                this.documents.add(null);
                this.itemsCount += r14;
                this.documents.addAll(r83.documents);
                this.itemsCount += r83.documents.size();
                int r33 = 0;
            L109:
                if (r33 >= r83.documents.size()) goto L111;
                this.setByDocumentId.put(Long.valueOf(r83.documents.get(r33).id), r83);
                r33 = r33 + 1;
                goto L109
            L111:
                EmojiView.EmojiPack r34 = new EmojiView.EmojiPack();
                r34.documents = r83.documents;
                r34.set = r83.set;
                r34.installed = false;
                r34.featured = r14;
                r34.expanded = r14;
                r34.free = r14;
                this.packs.add(r34);
            L112:
                r73 = r73 + 1;
                r3 = false;
                r4 = true;
                goto L61
            L69:
                if ((r82 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L112;
                TLRPC.TL_messages_stickerSet r9 = new TLRPC.TL_messages_stickerSet();
                TLRPC.StickerSet r10 = r82.set;
                r9.set = r10;
                r9.documents = ((TLRPC.TL_stickerSetFullCovered) r82).documents;
                ArrayList<TLRPC.TL_stickerPack> r84 = this.packsBySet.get(Long.valueOf(r10.id));
                r9.packs = r84;
                if (r84 != null) goto L93;
                HashMap r85 = new HashMap();
                ?? r102 = r3;
            L74:
                if (r102 >= r9.documents.size()) goto L88;
                TLRPC.Document r11 = r9.documents.get(r102);
                if (r11 == null) goto L87;
                ArrayList<Emoji.EmojiSpanRange> r12 = Emoji.parseEmojis(MessageObject.findAnimatedEmojiEmoticon(r11, null));
                if (r12 == null) goto L87;
                ?? r13 = r3;
            L82:
                if (r13 >= r12.size()) goto L87;
                String r142 = r12.get(r13).code.toString();
                ArrayList r15 = (ArrayList) r85.get(r142);
                if (r15 != null) goto L86;
                r15 = new ArrayList();
                r85.put(r142, r15);
            L86:
                r15.add(Long.valueOf(r11.id));
                r4 = r4;
                r13 = r13 + 1;
            L87:
                r4 = r4;
                r3 = false;
                r102 = r102 + 1;
                goto L74
            L88:
                boolean r143 = r4;
                r9.packs = new ArrayList();
                Iterator r35 = r85.entrySet().iterator();
            L90:
                if (r35.hasNext() == false) goto L92;
                Map.Entry r43 = (Map.Entry) r35.next();
                TLRPC.TL_stickerPack r86 = new TLRPC.TL_stickerPack();
                r86.emoticon = (String) r43.getKey();
                r86.documents = (ArrayList) r43.getValue();
                r9.packs.add(r86);
                goto L90
            L92:
                this.packsBySet.put(Long.valueOf(r9.set.id), r9.packs);
            L94:
                r83 = r9;
                r14 = r143;
                goto L96
            L93:
                r143 = r4;
            L113:
                int r25 = 0;
                boolean r36 = false;
            L115:
                if (r25 >= this.allStickerSets.size()) goto L120;
                r36 = this.allStickerSets.get(r25).set.title.toLowerCase().contains("staticemoji");     // Catch: Exception -> L143
            L117:
                if (r36 == true) goto L120;
                r25 = r25 + 1;
            L120:
                if (r36 == true) goto L124;
                TLRPC.TL_messages_stickerSet r16 = r1.getStickerSet(this.staticEmojiInput, false);
                if (r16 == null) goto L124;
                this.allStickerSets.add(r16);
            L124:
                Page.m19975$$Nest$fputresetOnce(this.this$1, true);
                if (this.lastAllSetsCount == this.allStickerSets.size()) goto L141;
                this.allEmojis.clear();
                int r44 = 0;
            L127:
                int r18 = this.allStickerSets.size();
                ArrayList<TLRPC.TL_messages_stickerSet> r26 = this.allStickerSets;
                if (r44 >= r18) goto L140;
                TLRPC.TL_messages_stickerSet r19 = r26.get(r44);
                if (r19 == null) goto L139;
                int r27 = 0;
            L134:
                if (r27 >= r19.packs.size()) goto L139;
                String r37 = r19.packs.get(r27).emoticon;
                ArrayList<Long> r64 = this.allEmojis.get(r37);
                if (r64 != null) goto L138;
                HashMap<String, ArrayList<Long>> r65 = this.allEmojis;
                ArrayList<Long> r74 = new ArrayList();
                r65.put(r37, r74);
                r64 = r74;
            L138:
                r64.addAll(r19.packs.get(r27).documents);
                r27 = r27 + 1;
            L139:
                r44 = r44 + 1;
                goto L127
            L140:
                this.lastAllSetsCount = r26.size();
            L141:
                this.includeNotFound = false;
                this.this$1.tabsStrip.updateEmojiPacks(this.packs);
                this.activeQuery = null;
                notifyDataSetChanged();
                return;
            L33:
                r6 = 0;
                goto L34
            L9:
                if (r22.this$0.hasWidgets() == false) goto L11;
                this.documents.add(this.this$1.this$0.widgets);
                this.itemsCount++;
            L11:
                ArrayList<TLRPC.Document> r28 = r1.getRecentStickers(2);
                if (r28 != null) goto L14;
            L19:
                ArrayList<TLRPC.Document> r29 = r1.getRecentStickers(0);
                if (r29 == null) goto L30;
                if (r29.isEmpty() == true) goto L30;
                if (this.recentSet != null) goto L26;
                this.recentSet = new TLRPC.TL_messages_stickerSet();
            L26:
                this.recentSet.documents = r29;
                if (EmojiBottomSheet.m19951$$Nest$fgetonPlusSelected(this.this$1.this$0) == null) goto L29;
                this.recentSet.documents.add(0, this.this$1.this$0.plus);
            L29:
                this.recentSet.set = new TLRPC.TL_stickerSet();
                this.recentSet.set.title = LocaleController.getString(R.string.RecentStickers);
                this.stickerSets.add(this.recentSet);
                goto L30
            L14:
                if (r28.isEmpty() == true) goto L19;
                if (this.faveSet != null) goto L18;
                this.faveSet = new TLRPC.TL_messages_stickerSet();
            L18:
                TLRPC.TL_messages_stickerSet r66 = this.faveSet;
                r66.documents = r28;
                r66.set = new TLRPC.TL_stickerSet();
                this.faveSet.set.title = LocaleController.getString(R.string.FavoriteStickers);
                this.stickerSets.add(this.faveSet);
                goto L19
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return this.itemsCount;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int r4) {
                if (r4 != 0) goto L6;
                return 0;
            L6:
                if (this.includeNotFound == false) goto L12;
                if (r4 != (this.itemsCount - 1)) goto L12;
                return 3;
            L12:
                if (this.positionToSection.get(r4, -1) < 0) goto L14;
                return 1;
            L14:
                if (r4 >= 0) goto L16;
                return 2;
            L16:
                if (r4 < this.documents.size()) goto L18;
                return 2;
            L18:
                if (this.documents.get(r4) != this.this$1.this$0.widgets) goto L24;
                return 4;
            L24:
                return 2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r10, int r11) {
                int r0 = r10.getItemViewType();
                if (r0 != 0) goto L6;
                r10.itemView.setTag(34);
                r10.itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, (int) EmojiBottomSheet.m19949$$Nest$fgetmaxPadding(this.this$1.this$0)));
                return;
            L6:
                boolean r1 = false;
                if (r0 != 1) goto L31;
                int r112 = this.positionToSection.get(r11);
                if (r112 >= 0) goto L11;
                return;
            L11:
                if (r112 >= this.stickerSets.size()) goto L71;
                TLRPC.TL_messages_stickerSet r113 = this.stickerSets.get(r112);
                if (r113 == null) goto L19;
                TLRPC.StickerSet r114 = r113.set;
                if (r114 == null) goto L19;
                String r115 = r114.title;
            L20:
                StickerSetNameCell r102 = (StickerSetNameCell) r10.itemView;
                if (this.activeQuery != null) goto L24;
                r102.setText(r115, 0);
                return;
            L24:
                int r02 = r115.toLowerCase().indexOf(this.activeQuery.toLowerCase());
                if (r02 >= 0) goto L28;
                r102.setText(r115, 0);
                return;
            L28:
                r102.setText(r115, 0, r02, this.activeQuery.length());
                return;
            L19:
                r115 = _UrlKt.FRAGMENT_ENCODE_SET;
                goto L20
            L71:
                return;
            L31:
                if (r0 != 2) goto L66;
                if (r11 < this.documents.size()) goto L35;
                TLRPC.Document r03 = null;
            L36:
                EmojiListView.EmojiImageView r103 = (EmojiListView.EmojiImageView) r10.itemView;
                if (r03 != this.this$1.this$0.plus) goto L41;
                r103.setSticker(null);
                int r116 = AndroidUtilities.dp(28.0f);
                EmojiBottomSheet r04 = this.this$1.this$0;
                int r12 = Theme.key_chat_emojiPanelIcon;
                ShapeDrawable r117 = Theme.createRoundRectDrawable(r116, Theme.multAlpha(EmojiBottomSheet.access$3300(r04, r12), 0.12f));
                Drawable r05 = this.this$1.getResources().getDrawable(R.drawable.filled_add_sticker).mutate();
                r05.setColorFilter(new PorterDuffColorFilter(EmojiBottomSheet.access$3400(this.this$1.this$0, r12), PorterDuff.Mode.MULTIPLY));
                CombinedDrawable r9 = new CombinedDrawable(r117, r05);
                r9.setCustomSize(AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f));
                r9.setIconSize(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
                r9.setCenter(true);
                r103.setDrawable(r9);
                return;
            L41:
                if (r11 < this.documentIds.size()) goto L43;
                long r7 = 0;
            L44:
                if (r03 == null) goto L46;
            L48:
                int r118 = this.this$1.currentType;
                if (r118 != 0) goto L61;
                if (r03 == null) goto L56;
                r103.setSticker(null);
                if (this.this$1.currentType != 1) goto L54;
                r1 = true;
            L54:
                r103.setEmoji(r03, r1);
                return;
            L56:
                r103.setSticker(null);
                if (this.this$1.currentType != 1) goto L59;
                r1 = true;
            L59:
                r103.setEmojiId(r7, r1);
                return;
            L61:
                if (r118 != 1) goto L63;
                r1 = true;
            L63:
                r103.setEmoji(null, r1);
                r103.setSticker(r03);
                return;
            L46:
                if (r7 != 0) goto L48;
                return;
            L43:
                r7 = this.documentIds.get(r11).longValue();
                goto L44
            L35:
                r03 = this.documents.get(r11);
                goto L36
            L66:
                if (r0 != 3) goto L70;
                ((NoEmojiView) r10.itemView).update(this.searchId);
                return;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
                if (r4 != 0) goto L4;
                View r32 = new View(this.this$1.getContext());
            L19:
                return new RecyclerListView.Holder(r32);
            L4:
                boolean r33 = false;
                if (r4 != 1) goto L9;
                View r42 = new StickerSetNameCell(this.this$1.getContext(), true, EmojiBottomSheet.access$3200(this.this$1.this$0), false);
            L7:
                r32 = r42;
                goto L19
            L9:
                if (r4 != 3) goto L15;
                Context r1 = this.this$1.getContext();
                if (this.this$1.currentType != 0) goto L13;
                r33 = true;
            L13:
                r42 = new NoEmojiView(r1, r33);
                goto L7
            L15:
                if (r4 != 4) goto L17;
                Page r43 = this.this$1;
                StoryWidgetsCell r34 = new StoryWidgetsCell(r43.this$0, r43.getContext());
                r34.setOnButtonClickListener(new EmojiBottomSheet$Page$Adapter$$ExternalSyntheticLambda1(this.this$1.this$0));
                r32 = r34;
                goto L19
            L17:
                r32 = new EmojiListView.EmojiImageView(this.this$1.getContext(), this.this$1.listView);
                goto L19
            }

            public void update() {
                if (this.query != null) goto L6;
                updateItems(null);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$6LHJhlRhgP1ji5lRA6XKGTKjnqo(Page r0, String r1, Integer r2) {
            r0.lambda$new$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$N3cfPhAcC1kkJWCsLxVWAE2YKPA(Page r0, View r1, int r2) {
            r0.lambda$new$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetlockTop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m19973$$Nest$fgetlockTop(Page r0) {
            return r0.lockTop;
        }

        /* renamed from: -$$Nest$fputlockTop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19974$$Nest$fputlockTop(Page r0, float r1) {
            r0.lockTop = r1;
        }

        /* renamed from: -$$Nest$fputresetOnce, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19975$$Nest$fputresetOnce(Page r0, boolean r1) {
            r0.resetOnce = r1;
        }

        public Page(EmojiBottomSheet r13, Context r14) {
            this.this$0 = r13;
            super(r13, r14);
            this.spanCount = 8;
            this.lockTop = -1.0f;
            this.resetOnce = false;
            EmojiListView r4 = new EmojiListView(r14);
            this.listView = r4;
            Adapter r5 = new Adapter(this);
            this.adapter = r5;
            r4.setAdapter(r5);
            EmojiListView r42 = this.listView;
            GridLayoutManager r52 = new GridLayoutManager(r14, this.spanCount);
            this.layoutManager = r52;
            r42.setLayoutManager(r52);
            this.listView.setClipToPadding(true);
            this.listView.setVerticalScrollBarEnabled(false);
            this.layoutManager.setSpanSizeLookup(new AnonymousClass1(this, r13));
            this.listView.setOnItemClickListener(new EmojiBottomSheet$Page$$ExternalSyntheticLambda0(this));
            this.listView.setOnScrollListener(new AnonymousClass2(this, r13));
            DefaultItemAnimator r3 = new DefaultItemAnimator();
            r3.setAddDelay(0);
            r3.setAddDuration(220);
            r3.setMoveDuration(220);
            r3.setChangeDuration(160);
            r3.setMoveInterpolator(CubicBezierInterpolator.EASE_OUT);
            this.listView.setItemAnimator(r3);
            addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
            SearchField r0 = new SearchField(r14, EmojiBottomSheet.access$2500(r13));
            this.searchField = r0;
            r0.setOnSearchQuery(new EmojiBottomSheet$Page$$ExternalSyntheticLambda1(this));
            addView(this.searchField, LayoutHelper.createFrame(-1, -2, 48));
            AnonymousClass3 r02 = new AnonymousClass3(this, r14, EmojiBottomSheet.access$2600(r13), false, false, false, true, 0, null, r13);
            this.tabsStrip = r02;
            addView(r02, LayoutHelper.createFrame(-1, 36.0f));
        }

        private /* synthetic */ void lambda$new$0(View r7, int r8) {
            if (r8 >= 0) goto L5;
            return;
        L5:
            if (this.layoutManager.getItemViewType(r7) != 4) goto L8;
            return;
        L8:
            if (r8 < Adapter.m19977$$Nest$fgetdocuments(this.adapter).size()) goto L10;
            TLRPC.Document r0 = null;
        L11:
            EmojiBottomSheet r1 = this.this$0;
            if (r0 != r1.plus) goto L19;
            if (EmojiBottomSheet.m19951$$Nest$fgetonPlusSelected(r1) == null) goto L16;
            EmojiBottomSheet.m19951$$Nest$fgetonPlusSelected(this.this$0).run();
        L16:
            this.this$0.dismiss();
            return;
        L19:
            if (r8 < Adapter.m19976$$Nest$fgetdocumentIds(this.adapter).size()) goto L21;
            long r4 = 0;
        L22:
            if (r0 == null) goto L24;
        L28:
            if (r0 == null) goto L30;
        L32:
            if (r0 != null) goto L35;
            return;
        L35:
            if (EmojiBottomSheet.m19950$$Nest$fgetonDocumentSelected(this.this$0) == null) goto L37;
            EmojiBottomSheet.m19950$$Nest$fgetonDocumentSelected(this.this$0).run(Adapter.m19980$$Nest$fgetsetByDocumentId(this.adapter).get(Long.valueOf(r0.id)), r0, Boolean.FALSE);
        L37:
            this.this$0.dismiss();
            return;
        L30:
            if (r4 == 0) goto L32;
            r0 = AnimatedEmojiDrawable.findDocument(EmojiBottomSheet.access$3800(this.this$0), r4);
            goto L32
        L24:
            if ((r7 instanceof EmojiListView.EmojiImageView) == false) goto L28;
            AnimatedEmojiDrawable r72 = ((EmojiListView.EmojiImageView) r7).drawable;
            if (r72 == null) goto L28;
            r0 = r72.getDocument();
            goto L28
        L21:
            r4 = ((Long) Adapter.m19976$$Nest$fgetdocumentIds(this.adapter).get(r8)).longValue();
            goto L22
        L10:
            r0 = (TLRPC.Document) Adapter.m19977$$Nest$fgetdocuments(this.adapter).get(r8);
            goto L11
        }

        private /* synthetic */ void lambda$new$1(String r2, Integer r3) {
            EmojiBottomSheet.m19959$$Nest$fputquery(this.this$0, r2);
            EmojiBottomSheet.m19957$$Nest$fputcategoryIndex(this.this$0, r3.intValue());
            Adapter.m19982$$Nest$mupdateItems(this.adapter, r2);
        }

        public boolean atTop() {
            return !this.listView.canScrollVertically(-1);
        }

        @Override // org.telegram.ui.Stories.recorder.EmojiBottomSheet.IPage
        public void bind(int r6) {
            this.currentType = r6;
            EmojiListView r0 = this.listView;
            int r2 = 0;
            if (r6 != 0) goto L5;
            boolean r3 = true;
        L6:
            r0.emoji = r3;
            GridLayoutManager r02 = this.layoutManager;
            if (r6 != 0) goto L9;
            int r4 = 8;
        L10:
            this.spanCount = r4;
            r02.setSpanCount(r4);
            if (this.resetOnce == true) goto L14;
            Adapter.m19982$$Nest$mupdateItems(this.adapter, null);
        L14:
            if (EmojiBottomSheet.m19946$$Nest$fgetcategoryIndex(this.this$0) < 0) goto L20;
            SearchField r03 = this.searchField;
            r03.ignoreTextChange = true;
            SearchField.m19986$$Nest$fgeteditText(r03).setText(_UrlKt.FRAGMENT_ENCODE_SET);
            SearchField r04 = this.searchField;
            r04.ignoreTextChange = false;
            if (SearchField.m19983$$Nest$fgetcategoriesListView(r04) == null) goto L27;
            SearchField.m19983$$Nest$fgetcategoriesListView(this.searchField).selectCategory(EmojiBottomSheet.m19946$$Nest$fgetcategoryIndex(this.this$0));
            SearchField.m19983$$Nest$fgetcategoriesListView(this.searchField).scrollToSelected();
            if (SearchField.m19983$$Nest$fgetcategoriesListView(this.searchField).getSelectedCategory() == null) goto L27;
            Adapter.m19981$$Nest$fputquery(this.adapter, SearchField.m19983$$Nest$fgetcategoriesListView(this.searchField).getSelectedCategory().emojis);
            AndroidUtilities.cancelRunOnUIThread(Adapter.m19979$$Nest$fgetsearchRunnable(this.adapter));
            AndroidUtilities.runOnUIThread(Adapter.m19979$$Nest$fgetsearchRunnable(this.adapter));
        L27:
            this.searchField.checkCategoriesView(r6, EmojiBottomSheet.m19948$$Nest$fgetgreeting(this.this$0));
            MediaDataController r5 = MediaDataController.getInstance(EmojiBottomSheet.access$2900(this.this$0));
            if (r6 != 0) goto L30;
            r2 = 5;
        L30:
            r5.checkStickers(r2);
            return;
        L20:
            boolean r05 = TextUtils.isEmpty(EmojiBottomSheet.m19953$$Nest$fgetquery(this.this$0));
            SearchField r1 = this.searchField;
            if (r05 == true) goto L26;
            SearchField.m19986$$Nest$fgeteditText(r1).setText(EmojiBottomSheet.m19953$$Nest$fgetquery(this.this$0));
            if (SearchField.m19983$$Nest$fgetcategoriesListView(this.searchField) == null) goto L25;
            SearchField.m19983$$Nest$fgetcategoriesListView(this.searchField).selectCategory(null);
            SearchField.m19983$$Nest$fgetcategoriesListView(this.searchField).scrollToStart();
        L25:
            AndroidUtilities.cancelRunOnUIThread(Adapter.m19979$$Nest$fgetsearchRunnable(this.adapter));
            AndroidUtilities.runOnUIThread(Adapter.m19979$$Nest$fgetsearchRunnable(this.adapter));
            goto L27
        L26:
            SearchField.m19988$$Nest$mclear(r1);
            goto L27
        L9:
            r4 = 5;
            goto L10
        L5:
            r3 = false;
            goto L6
        }

        public float lockTop() {
            float r0 = this.lockTop;
            if (r0 < 0.0f) goto L7;
            int r2 = this.listView.getPaddingTop();
        L6:
            return r0 + r2;
        L7:
            r0 = top();
            this.lockTop = r0;
            r2 = this.listView.getPaddingTop();
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r7, int r8) {
            setPadding(EmojiBottomSheet.access$2700(this.this$0), 0, EmojiBottomSheet.access$2800(this.this$0), 0);
            this.tabsStrip.setTranslationY(AndroidUtilities.dp(16.0f));
            this.searchField.setTranslationY(AndroidUtilities.dp(52.0f));
            EmojiListView r0 = this.listView;
            int r2 = AndroidUtilities.dp(5.0f);
            int r3 = AndroidUtilities.dp(102.0f);
            int r1 = AndroidUtilities.dp(5.0f);
            int r4 = AndroidUtilities.navigationBarHeight;
            if (EmojiBottomSheet.m19952$$Nest$fgetonlyStickers(this.this$0) == false) goto L5;
            float r5 = 0.0f;
        L6:
            r0.setPadding(r2, r3, r1, r4 + AndroidUtilities.dp(r5));
            super.onMeasure(r7, r8);
            return;
        L5:
            r5 = 40.0f;
            goto L6
        }

        @Override // org.telegram.ui.Stories.recorder.EmojiBottomSheet.IPage
        public float top() {
            float r0 = this.lockTop;
            if (r0 < 0.0f) goto L5;
            return r0;
        L5:
            int r2 = 0;
        L7:
            if (r2 >= this.listView.getChildCount()) goto L15;
            Object r4 = this.listView.getChildAt(r2).getTag();
            if ((r4 instanceof Integer) == false) goto L14;
            if (((Integer) r4).intValue() != 34) goto L14;
            return Math.max(0, r3.getBottom() - AndroidUtilities.dp(102.0f));
        L14:
            r2 = r2 + 1;
            goto L7
        L15:
            return 0.0f;
        }

        @Override // org.telegram.ui.Stories.recorder.EmojiBottomSheet.IPage
        public void updateTops() {
            float r0 = Math.max(0.0f, top());
            this.tabsStrip.setTranslationY(AndroidUtilities.dp(16.0f) + r0);
            this.searchField.setTranslationY(AndroidUtilities.dp(52.0f) + r0);
            this.listView.setBounds(r0 + r1.getPaddingTop(), this.listView.getHeight() - this.listView.getPaddingBottom());
        }
    }

    public static class SearchField extends FrameLayout {
        private final FrameLayout box;
        private StickerCategoriesListView categoriesListView;
        private int categoriesListViewType;
        private final ImageView clear;
        private boolean clearVisible;
        private final EditTextBoldCursor editText;
        public boolean ignoreTextChange;
        private final FrameLayout inputBox;
        private boolean isprogress;
        private Utilities.Callback2<String, Integer> onSearchQuery;
        private final Theme.ResourcesProvider resourcesProvider;
        private final SearchStateDrawable searchImageDrawable;
        private final ImageView searchImageView;

        /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$SearchField$1, reason: invalid class name */
        public class AnonymousClass1 extends ViewOutlineProvider {
            final /* synthetic */ SearchField this$0;

            public AnonymousClass1(SearchField r1) {
                this.this$0 = r1;
            }

            @Override // android.view.ViewOutlineProvider
            public void getOutline(View r7, Outline r8) {
                r8.setRoundRect(0, 0, r7.getWidth(), r7.getHeight(), AndroidUtilities.dp(18.0f));
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$SearchField$2, reason: invalid class name */
        public class AnonymousClass2 extends EditTextBoldCursor {
            final /* synthetic */ SearchField this$0;

            public AnonymousClass2(SearchField r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
            public void onFocusChanged(boolean r1, int r2, Rect r3) {
                super.onFocusChanged(r1, r2, r3);
                if (r1 == true) goto L6;
                AndroidUtilities.hideKeyboard(SearchField.m19986$$Nest$fgeteditText(this.this$0));
                return;
            }

            @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
            public boolean onTouchEvent(MotionEvent r2) {
                if (SearchField.m19986$$Nest$fgeteditText(this.this$0).isEnabled() == true) goto L7;
                return super.onTouchEvent(r2);
            L7:
                if (r2.getAction() != 0) goto L10;
                SearchField.m19986$$Nest$fgeteditText(this.this$0).requestFocus();
                AndroidUtilities.showKeyboard(SearchField.m19986$$Nest$fgeteditText(this.this$0));
            L10:
                return super.onTouchEvent(r2);
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$SearchField$3, reason: invalid class name */
        public class AnonymousClass3 implements TextWatcher {
            final /* synthetic */ SearchField this$0;

            public static /* synthetic */ void $r8$lambda$l9SrJIFz9ylYg16TiNavpvi7HqY(AnonymousClass3 r0) {
                r0.lambda$afterTextChanged$0();
            }

            public AnonymousClass3(SearchField r1) {
                this.this$0 = r1;
            }

            private /* synthetic */ void lambda$afterTextChanged$0() {
                if (SearchField.m19985$$Nest$fgetclearVisible(this.this$0) == true) goto L6;
                SearchField.m19984$$Nest$fgetclear(this.this$0).setVisibility(8);
                return;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r6) {
                SearchField r62 = this.this$0;
                if (r62.ignoreTextChange == true) goto L40;
                SearchField.m19990$$Nest$mupdateButton(r62);
                String r63 = SearchField.m19986$$Nest$fgeteditText(this.this$0).getText().toString();
                SearchField r0 = this.this$0;
                if (TextUtils.isEmpty(r63) == false) goto L8;
                String r1 = null;
            L9:
                SearchField.m19989$$Nest$msearch(r0, r1, -1);
                if (SearchField.m19983$$Nest$fgetcategoriesListView(this.this$0) == null) goto L13;
                SearchField.m19983$$Nest$fgetcategoriesListView(this.this$0).selectCategory(null);
                SearchField.m19983$$Nest$fgetcategoriesListView(this.this$0).updateCategoriesShown(TextUtils.isEmpty(r63), true);
            L13:
                if (SearchField.m19986$$Nest$fgeteditText(this.this$0) == null) goto L37;
                SearchField.m19986$$Nest$fgeteditText(this.this$0).animate().cancel();
                float r02 = 0.0f;
                ViewPropertyAnimator r64 = SearchField.m19986$$Nest$fgeteditText(this.this$0).animate().translationX(0.0f);
                CubicBezierInterpolator r2 = CubicBezierInterpolator.EASE_OUT_QUINT;
                r64.setInterpolator(r2).start();
                if (SearchField.m19984$$Nest$fgetclear(this.this$0) != null) goto L17;
                return;
            L17:
                if (SearchField.m19985$$Nest$fgetclearVisible(this.this$0) == (!TextUtils.isEmpty(SearchField.m19986$$Nest$fgeteditText(this.this$0).getText()))) goto L39;
                SearchField r65 = this.this$0;
                SearchField.m19987$$Nest$fputclearVisible(r65, true ^ SearchField.m19985$$Nest$fgetclearVisible(r65));
                SearchField.m19984$$Nest$fgetclear(this.this$0).animate().cancel();
                if (SearchField.m19985$$Nest$fgetclearVisible(this.this$0) == false) goto L21;
                SearchField.m19984$$Nest$fgetclear(this.this$0).setVisibility(0);
            L21:
                ViewPropertyAnimator r66 = SearchField.m19984$$Nest$fgetclear(this.this$0).animate();
                float r3 = 0.7f;
                if (SearchField.m19985$$Nest$fgetclearVisible(this.this$0) == false) goto L24;
                float r12 = 1.0f;
            L25:
                ViewPropertyAnimator r67 = r66.scaleX(r12);
                if (SearchField.m19985$$Nest$fgetclearVisible(this.this$0) == false) goto L28;
                r3 = 1.0f;
            L28:
                ViewPropertyAnimator r68 = r67.scaleY(r3);
                if (SearchField.m19985$$Nest$fgetclearVisible(this.this$0) == false) goto L31;
                r02 = 1.0f;
            L31:
                ViewPropertyAnimator r69 = r68.alpha(r02).withEndAction(new EmojiBottomSheet$SearchField$3$$ExternalSyntheticLambda0(this)).setInterpolator(r2).setDuration(320);
                if (SearchField.m19985$$Nest$fgetclearVisible(this.this$0) == false) goto L34;
                long r03 = 240;
            L35:
                r69.setStartDelay(r03).start();
                return;
            L34:
                r03 = 0;
                goto L35
            L24:
                r12 = 0.7f;
                goto L25
            L39:
                return;
            L37:
                return;
            L8:
                r1 = r63;
                goto L9
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$SearchField$4, reason: invalid class name */
        public class AnonymousClass4 extends CloseProgressDrawable2 {
            final /* synthetic */ SearchField this$0;
            final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

            public AnonymousClass4(SearchField r1, float r2, Theme.ResourcesProvider r3) {
                this.this$0 = r1;
                this.val$resourcesProvider = r3;
                super(r2);
                setSide(AndroidUtilities.dp(7.0f));
            }

            @Override // org.telegram.ui.Components.CloseProgressDrawable2
            public int getCurrentColor() {
                return Theme.getColor(Theme.key_chat_emojiSearchIcon, this.val$resourcesProvider);
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$SearchField$5, reason: invalid class name */
        public class AnonymousClass5 extends StickerCategoriesListView {
            final /* synthetic */ SearchField this$0;
            final /* synthetic */ boolean val$greeting;

            public AnonymousClass5(SearchField r1, Context r2, StickerCategoriesListView.EmojiCategory[] r3, int r4, Theme.ResourcesProvider r5, boolean r6) {
                this.this$0 = r1;
                this.val$greeting = r6;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.StickerCategoriesListView
            public boolean isTabIconsAnimationEnabled(boolean r1) {
                return LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS);
            }

            @Override // org.telegram.ui.Components.StickerCategoriesListView
            public StickerCategoriesListView.EmojiCategory[] preprocessCategories(StickerCategoriesListView.EmojiCategory[] r5) {
                if (r5 != null) goto L4;
            L23:
                return r5;
            L4:
                if (this.val$greeting == false) goto L23;
                int r0 = 0;
            L7:
                if (r0 >= r5.length) goto L14;
                StickerCategoriesListView.EmojiCategory r1 = r5[r0];
                if (r1 == null) goto L13;
                if (r1.greeting == false) goto L13;
            L15:
                if (r0 < 0) goto L23;
                int r12 = r5.length;
                StickerCategoriesListView.EmojiCategory[] r2 = new StickerCategoriesListView.EmojiCategory[r12];
                r2[0] = r5[r0];
                int r4 = 1;
            L17:
                if (r4 >= r12) goto L22;
                if (r4 > r0) goto L20;
                int r3 = r4 - 1;
            L21:
                r2[r4] = r5[r3];
                r4 = r4 + 1;
                goto L17
            L20:
                r3 = r4;
                goto L21
            L22:
                return r2;
            L13:
                r0 = r0 + 1;
                goto L7
            L14:
                r0 = -1;
                goto L15
            }

            @Override // org.telegram.ui.Components.StickerCategoriesListView
            public void selectCategory(int r1) {
                super.selectCategory(r1);
                SearchField.m19990$$Nest$mupdateButton(this.this$0);
            }
        }

        public static /* synthetic */ void $r8$lambda$4Iq9fwW48iKVbnQEHDSS6GOmkdg(SearchField r0, Integer r1) {
            r0.lambda$checkCategoriesView$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$9MoUjJ6TLBfQY6VyfVvLsEDdab4(SearchField r0, View r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$JvggXm6dK2eFgaTMv49AM0BXkhs(SearchField r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$o5LPKoVdZccUHknSygYD2IlCYcI(SearchField r0, StickerCategoriesListView.EmojiCategory r1) {
            r0.lambda$checkCategoriesView$3(r1);
        }

        /* renamed from: -$$Nest$fgetcategoriesListView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ StickerCategoriesListView m19983$$Nest$fgetcategoriesListView(SearchField r0) {
            return r0.categoriesListView;
        }

        /* renamed from: -$$Nest$fgetclear, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageView m19984$$Nest$fgetclear(SearchField r0) {
            return r0.clear;
        }

        /* renamed from: -$$Nest$fgetclearVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19985$$Nest$fgetclearVisible(SearchField r0) {
            return r0.clearVisible;
        }

        /* renamed from: -$$Nest$fgeteditText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EditTextBoldCursor m19986$$Nest$fgeteditText(SearchField r0) {
            return r0.editText;
        }

        /* renamed from: -$$Nest$fputclearVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19987$$Nest$fputclearVisible(SearchField r0, boolean r1) {
            r0.clearVisible = r1;
        }

        /* renamed from: -$$Nest$mclear, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19988$$Nest$mclear(SearchField r0) {
            r0.clear();
        }

        /* renamed from: -$$Nest$msearch, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19989$$Nest$msearch(SearchField r0, String r1, int r2) {
            r0.search(r1, r2);
        }

        /* renamed from: -$$Nest$mupdateButton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19990$$Nest$mupdateButton(SearchField r0) {
            r0.updateButton();
        }

        public SearchField(Context r20, Theme.ResourcesProvider r21) {
            super(r20);
            this.categoriesListViewType = -1;
            this.resourcesProvider = r21;
            FrameLayout r3 = new FrameLayout(r20);
            this.box = r3;
            r3.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(18.0f), Theme.getColor(Theme.key_chat_emojiSearchBackground, r21)));
            r3.setClipToOutline(true);
            r3.setOutlineProvider(new AnonymousClass1(this));
            addView(r3, LayoutHelper.createFrame(-1, 36.0f, 119, 10.0f, 6.0f, 10.0f, 8.0f));
            FrameLayout r5 = new FrameLayout(r20);
            this.inputBox = r5;
            r3.addView(r5, LayoutHelper.createFrame(-1, 40.0f, 51, 38.0f, 0.0f, 0.0f, 0.0f));
            ImageView r6 = new ImageView(r20);
            this.searchImageView = r6;
            ImageView.ScaleType r7 = ImageView.ScaleType.CENTER;
            r6.setScaleType(r7);
            SearchStateDrawable r8 = new SearchStateDrawable();
            this.searchImageDrawable = r8;
            r8.setIconState(0, false);
            int r10 = Theme.key_chat_emojiSearchIcon;
            r8.setColor(Theme.getColor(r10, r21));
            r6.setImageDrawable(r8);
            r3.addView(r6, LayoutHelper.createFrame(36, 36, 51));
            AnonymousClass2 r82 = new AnonymousClass2(this, r20);
            this.editText = r82;
            r82.setTextSize(1, 16.0f);
            r82.setHintTextColor(Theme.getColor(r10, r21));
            r82.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r21));
            r82.setBackgroundDrawable(null);
            r82.setPadding(0, 0, 0, 0);
            r82.setMaxLines(1);
            r82.setLines(1);
            r82.setSingleLine(true);
            r82.setImeOptions(268435459);
            r82.setHint(LocaleController.getString(R.string.Search));
            int r9 = Theme.key_featuredStickers_addedIcon;
            r82.setCursorColor(Theme.getColor(r9, r21));
            r82.setHandlesColor(Theme.getColor(r9, r21));
            r82.setCursorSize(AndroidUtilities.dp(20.0f));
            r82.setCursorWidth(1.5f);
            r82.setTranslationY(AndroidUtilities.dp(-2.0f));
            r5.addView(r82, LayoutHelper.createFrame(-1, 40.0f, 51, 0.0f, 0.0f, 28.0f, 0.0f));
            r82.addTextChangedListener(new AnonymousClass3(this));
            ImageView r52 = new ImageView(r20);
            this.clear = r52;
            r52.setScaleType(r7);
            r52.setImageDrawable(new AnonymousClass4(this, 1.25f, r21));
            r52.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector, r21), 1, AndroidUtilities.dp(15.0f)));
            r52.setAlpha(0.0f);
            r52.setScaleX(0.7f);
            r52.setScaleY(0.7f);
            r52.setVisibility(8);
            r52.setOnClickListener(new EmojiBottomSheet$SearchField$$ExternalSyntheticLambda0(this));
            r3.addView(r52, LayoutHelper.createFrame(36, 36, 53));
            r6.setOnClickListener(new EmojiBottomSheet$SearchField$$ExternalSyntheticLambda1(this));
        }

        private void clear() {
            this.editText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            search(null, -1);
            StickerCategoriesListView r2 = this.categoriesListView;
            if (r2 == null) goto L6;
            r2.selectCategory(null);
            return;
        }

        private /* synthetic */ void lambda$checkCategoriesView$2(Integer r3) {
            this.editText.animate().cancel();
            this.editText.setTranslationX(-Math.max(0, r3.intValue()));
            updateButton();
        }

        private /* synthetic */ void lambda$checkCategoriesView$3(StickerCategoriesListView.EmojiCategory r3) {
            StickerCategoriesListView r0 = this.categoriesListView;
            if (r0 != null) goto L5;
            return;
        L5:
            StickerCategoriesListView.EmojiCategory r02 = r0.getSelectedCategory();
            StickerCategoriesListView r1 = this.categoriesListView;
            if (r02 != r3) goto L9;
            r1.selectCategory(null);
            search(null, -1);
            return;
        L9:
            r1.selectCategory(r3);
            search(r3.emojis, this.categoriesListView.getCategoryIndex());
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            clear();
        }

        private /* synthetic */ void lambda$new$1(View r2) {
            if (this.searchImageDrawable.getIconState() != 1) goto L9;
            clear();
            StickerCategoriesListView r1 = this.categoriesListView;
            if (r1 == null) goto L12;
            r1.scrollToStart();
            return;
        L12:
            return;
        L9:
            if (this.searchImageDrawable.getIconState() != 0) goto L13;
            this.editText.requestFocus();
            return;
        }

        private void search(String r1, int r2) {
            Utilities.Callback2<String, Integer> r0 = this.onSearchQuery;
            if (r0 == null) goto L6;
            r0.run(r1, Integer.valueOf(r2));
            return;
        }

        private void updateButton() {
            updateButton(false);
        }

        private void updateButton(boolean r3) {
            if (this.isprogress == false) goto L14;
            if (this.editText.length() != 0) goto L10;
            StickerCategoriesListView r0 = this.categoriesListView;
            if (r0 == null) goto L14;
            if (r0.getSelectedCategory() == null) goto L14;
        L10:
            if (r3 == true) goto L14;
            return;
        L14:
            if (this.editText.length() > 0) goto L27;
            StickerCategoriesListView r32 = this.categoriesListView;
            if (r32 != null) goto L18;
        L26:
            int r33 = 0;
        L28:
            this.searchImageDrawable.setIconState(r33);
            this.isprogress = false;
            return;
        L18:
            if (r32.isCategoriesShown() == false) goto L26;
            StickerCategoriesListView r34 = this.categoriesListView;
            if (r34 == null) goto L24;
            if (r34.isScrolledIntoOccupiedWidth() == true) goto L27;
        L24:
            if (this.categoriesListView.getSelectedCategory() == null) goto L26;
        L27:
            r33 = 1;
            goto L28
        }

        public void checkCategoriesView(int r10, boolean r11) {
            if (this.categoriesListViewType == r10) goto L5;
        L7:
            StickerCategoriesListView r0 = this.categoriesListView;
            if (r0 == null) goto L10;
            this.box.removeView(r0);
        L10:
            Context r4 = getContext();
            if (r10 != 1) goto L13;
            int r6 = 3;
        L14:
            AnonymousClass5 r2 = new AnonymousClass5(this, r4, null, r6, this.resourcesProvider, r11);
            this.categoriesListView = r2;
            r2.setDontOccupyWidth(((int) this.editText.getPaint().measureText(this.editText.getHint() + _UrlKt.FRAGMENT_ENCODE_SET)) + AndroidUtilities.dp(16.0f));
            this.categoriesListView.setOnScrollIntoOccupiedWidth(new EmojiBottomSheet$SearchField$$ExternalSyntheticLambda2(this));
            this.categoriesListView.setOnCategoryClick(new EmojiBottomSheet$SearchField$$ExternalSyntheticLambda3(this));
            FrameLayout r9 = this.box;
            r9.addView(this.categoriesListView, Math.max(0, r9.getChildCount() - 1), LayoutHelper.createFrame(-1, 36.0f, 51, 36.0f, 0.0f, 0.0f, 0.0f));
            return;
        L13:
            r6 = 0;
            goto L14
        L5:
            if (this.categoriesListView == null) goto L7;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
        }

        public void setOnSearchQuery(Utilities.Callback2<String, Integer> r1) {
            this.onSearchQuery = r1;
        }

        public void showProgress(boolean r1) {
            this.isprogress = r1;
            if (r1 == false) goto L6;
            this.searchImageDrawable.setIconState(2);
            return;
        L6:
            updateButton(true);
        }
    }

    public class StoryWidgetsCell extends View {
        private final Paint bgPaint;
        float[] lineWidths;
        private Utilities.Callback<Integer> onClickListener;
        private final TextPaint textPaint;
        final /* synthetic */ EmojiBottomSheet this$0;
        private final List<BaseWidget> widgets;

        public abstract class BaseWidget {
            public AnimatedFloat animatedWidth;
            ButtonBounce bounce;
            RectF bounds;
            float height;
            int id;
            int layoutLine;
            float layoutX;
            final /* synthetic */ StoryWidgetsCell this$1;
            float width;

            private BaseWidget(StoryWidgetsCell r5) {
                this.this$1 = r5;
                this.layoutX = 0.0f;
                this.layoutLine = 0;
                this.bounds = new RectF();
                this.bounce = new ButtonBounce(r5);
                this.animatedWidth = new AnimatedFloat(r5, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
            }

            public /* synthetic */ BaseWidget(StoryWidgetsCell r1, EmojiBottomSheetIA r2) {
                this(r1);
            }

            public abstract void draw(Canvas r1, float r2, float r3);

            public void onAttachToWindow(boolean r1) {
            }
        }

        public class Button extends BaseWidget {
            Drawable drawable;
            String emojiDrawable;
            StaticLayout layout;
            Drawable lockDrawable;
            Paint lockPaint;
            float textLeft;
            float textWidth;
            final /* synthetic */ StoryWidgetsCell this$1;

            public Button(StoryWidgetsCell r10, int r11, int r12, String r13) {
                this.this$1 = r10;
                super(r10, null);
                this.id = r11;
                Drawable r112 = r10.getContext().getResources().getDrawable(r12).mutate();
                this.drawable = r112;
                r112.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
                StaticLayout r1 = new StaticLayout(TextUtils.ellipsize(r13.toUpperCase(), StoryWidgetsCell.m19992$$Nest$fgettextPaint(r10), AndroidUtilities.displaySize.x * 0.8f, TextUtils.TruncateAt.END), StoryWidgetsCell.m19992$$Nest$fgettextPaint(r10), 99999, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                this.layout = r1;
                float r113 = 0.0f;
                if (r1.getLineCount() <= 0) goto L5;
                float r102 = this.layout.getLineWidth(0);
            L6:
                this.textWidth = r102;
                if (this.layout.getLineCount() <= 0) goto L9;
                r113 = this.layout.getLineLeft(0);
            L9:
                this.textLeft = r113;
                this.width = AndroidUtilities.dpf2(45.6f) + this.textWidth;
                this.height = AndroidUtilities.dpf2(36.0f);
                return;
            L5:
                r102 = 0.0f;
                goto L6
            }

            public Button(StoryWidgetsCell r10, View r11, int r12, CharSequence r13) {
                this.this$1 = r10;
                super(r10, null);
                this.id = r12;
                StaticLayout r1 = new StaticLayout(TextUtils.ellipsize(r13, StoryWidgetsCell.m19992$$Nest$fgettextPaint(r10), AndroidUtilities.displaySize.x * 0.8f, TextUtils.TruncateAt.END), StoryWidgetsCell.m19992$$Nest$fgettextPaint(r10), 99999, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                this.layout = r1;
                float r112 = 0.0f;
                if (r1.getLineCount() <= 0) goto L5;
                float r102 = this.layout.getLineWidth(0);
            L6:
                this.textWidth = r102;
                if (this.layout.getLineCount() <= 0) goto L9;
                r112 = this.layout.getLineLeft(0);
            L9:
                this.textLeft = r112;
                this.width = AndroidUtilities.dpf2(12.0f) + this.textWidth;
                this.height = AndroidUtilities.dpf2(36.0f);
                return;
            L5:
                r102 = 0.0f;
                goto L6
            }

            @Override // org.telegram.ui.Stories.recorder.EmojiBottomSheet.StoryWidgetsCell.BaseWidget
            public void draw(Canvas r9, float r10, float r11) {
                this.bounds.set(r10, r11, this.width + r10, this.height + r11);
                float r102 = this.bounce.getScale(0.05f);
                r9.save();
                r9.scale(r102, r102, this.bounds.centerX(), this.bounds.centerY());
                r9.drawRoundRect(this.bounds, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), StoryWidgetsCell.m19991$$Nest$fgetbgPaint(this.this$1));
                if (this.lockDrawable == null) goto L5;
                r9.saveLayerAlpha(this.bounds, 255, 31);
            L5:
                int r112 = 0;
                if (this.drawable != null) goto L11;
                Drawable r103 = Emoji.getEmojiBigDrawable(this.emojiDrawable);
                this.drawable = r103;
                if ((r103 instanceof Emoji.EmojiDrawable) == false) goto L11;
                ((Emoji.EmojiDrawable) r103).fullSize = false;
            L11:
                if (this.drawable == null) goto L18;
                if (this.emojiDrawable != null) goto L15;
                float r104 = 24.0f;
            L16:
                int r105 = AndroidUtilities.dp(r104);
                Drawable r2 = this.drawable;
                float r106 = r105 / 2;
                int r3 = (int) ((this.bounds.left + AndroidUtilities.dp(18.0f)) - r106);
                RectF r4 = this.bounds;
                r2.setBounds(r3, (int) ((r4.top + (this.height / 2.0f)) - r106), (int) ((r4.left + AndroidUtilities.dp(18.0f)) + r106), (int) ((this.bounds.top + (this.height / 2.0f)) + r106));
                this.drawable.draw(r9);
                goto L18
            L15:
                r104 = 22.0f;
            L18:
                if (this.lockDrawable == null) goto L20;
                RectF r107 = AndroidUtilities.rectTmp;
                r107.set(this.bounds.left + AndroidUtilities.dp(18.55f), ((this.bounds.top + this.height) - AndroidUtilities.dp(5.0f)) - AndroidUtilities.dp(12.55f), this.bounds.left + AndroidUtilities.dp(29.45f), this.bounds.left + AndroidUtilities.dp(31.0f));
                r9.drawRoundRect(r107, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.lockPaint);
                this.lockDrawable.setBounds((int) (this.bounds.left + AndroidUtilities.dp(18.0f)), (int) (((this.bounds.top + this.height) - AndroidUtilities.dp(5.0f)) - AndroidUtilities.dp(12.0f)), (int) (this.bounds.left + AndroidUtilities.dp(30.0f)), (int) ((this.bounds.top + this.height) - AndroidUtilities.dp(5.0f)));
                this.lockDrawable.draw(r9);
                r9.restore();
            L20:
                float r108 = this.bounds.left;
                if (this.drawable == null) goto L23;
            L25:
                r112 = 28;
            L26:
                r9.translate((r108 + AndroidUtilities.dp(6 + r112)) - this.textLeft, (this.bounds.top + (this.height / 2.0f)) - (this.layout.getHeight() / 2.0f));
                this.layout.draw(r9);
                r9.restore();
                return;
            L23:
                if (this.emojiDrawable != null) goto L25;
                goto L25
            }

            public Button needsPremium() {
                if (UserConfig.getInstance(EmojiBottomSheet.access$4800(this.this$1.this$0)).isPremium() == true) goto L5;
                Drawable r0 = this.this$1.getContext().getResources().getDrawable(R.drawable.msg_mini_lock3).mutate();
                this.lockDrawable = r0;
                r0.setColorFilter(new PorterDuffColorFilter(Theme.multAlpha(-1, 0.6f), PorterDuff.Mode.SRC_IN));
                Paint r02 = new Paint(1);
                this.lockPaint = r02;
                r02.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            L5:
                return this;
            }

            public void setText(CharSequence r12) {
                StaticLayout r3 = new StaticLayout(TextUtils.ellipsize(r12, StoryWidgetsCell.m19992$$Nest$fgettextPaint(this.this$1), AndroidUtilities.displaySize.x * 0.8f, TextUtils.TruncateAt.END), StoryWidgetsCell.m19992$$Nest$fgettextPaint(this.this$1), 99999, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                this.layout = r3;
                float r0 = 0.0f;
                if (r3.getLineCount() <= 0) goto L5;
                float r122 = this.layout.getLineWidth(0);
            L6:
                this.textWidth = r122;
                if (this.layout.getLineCount() <= 0) goto L9;
                r0 = this.layout.getLineLeft(0);
            L9:
                this.textLeft = r0;
                this.width = AndroidUtilities.dpf2(17.6f) + this.textWidth;
                this.height = AndroidUtilities.dpf2(36.0f);
                return;
            L5:
                r122 = 0.0f;
                goto L6
            }
        }

        public class ReactionWidget extends BaseWidget {
            StoryReactionWidgetBackground background;
            int currentIndex;
            ReactionImageHolder nextReactionHolder;
            AnimatedFloat progressToNext;
            ReactionImageHolder reactionHolder;
            final /* synthetic */ StoryWidgetsCell this$1;
            Timer timeTimer;
            ArrayList<ReactionsLayoutInBubble.VisibleReaction> visibleReactions;

            /* renamed from: org.telegram.ui.Stories.recorder.EmojiBottomSheet$StoryWidgetsCell$ReactionWidget$1, reason: invalid class name */
            public class AnonymousClass1 extends TimerTask {
                final /* synthetic */ ReactionWidget this$2;

                public static /* synthetic */ void $r8$lambda$1qQyB6fPrJKEqALoAtg3rpaWsGI(AnonymousClass1 r0) {
                    r0.lambda$run$0();
                }

                public AnonymousClass1(ReactionWidget r1) {
                    this.this$2 = r1;
                }

                private /* synthetic */ void lambda$run$0() {
                    if (this.this$2.visibleReactions.isEmpty() == false) goto L5;
                    return;
                L5:
                    this.this$2.progressToNext.set(0.0f, true);
                    ReactionWidget r0 = this.this$2;
                    int r1 = r0.currentIndex + 1;
                    r0.currentIndex = r1;
                    if (r1 <= (r0.visibleReactions.size() - 1)) goto L8;
                    this.this$2.currentIndex = 0;
                L8:
                    ReactionWidget r02 = this.this$2;
                    ReactionImageHolder r12 = r02.nextReactionHolder;
                    r12.setVisibleReaction(r02.visibleReactions.get(r02.currentIndex));
                    ReactionWidget r3 = this.this$2;
                    r3.nextReactionHolder = r3.reactionHolder;
                    r3.reactionHolder = r12;
                    r3.this$1.invalidate();
                }

                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    AndroidUtilities.runOnUIThread(new EmojiBottomSheet$StoryWidgetsCell$ReactionWidget$1$$ExternalSyntheticLambda0(this));
                }
            }

            /* renamed from: $r8$lambda$M0r5PWt2OPKJuCVT6lv-8Fy7TH8, reason: not valid java name */
            public static /* synthetic */ int m19993$r8$lambda$M0r5PWt2OPKJuCVT6lv8Fy7TH8(ReactionsLayoutInBubble.VisibleReaction r3, ReactionsLayoutInBubble.VisibleReaction r4) {
                String r32 = r3.emojicon;
                int r0 = 0;
                if (r32 != null) goto L5;
            L7:
                int r33 = 0;
            L8:
                String r42 = r4.emojicon;
                if (r42 == null) goto L14;
                if (r42.equals("❤") == false) goto L14;
                r0 = -1;
            L14:
                return r33 - r0;
            L5:
                if (r32.equals("❤") == false) goto L7;
                r33 = -1;
                goto L8
            }

            public ReactionWidget(StoryWidgetsCell r4) {
                this.this$1 = r4;
                super(r4, null);
                this.reactionHolder = new ReactionImageHolder(r4);
                this.nextReactionHolder = new ReactionImageHolder(r4);
                this.progressToNext = new AnimatedFloat(r4);
                this.background = new StoryReactionWidgetBackground(r4);
                this.visibleReactions = new ArrayList();
                this.id = 3;
                this.width = AndroidUtilities.dp(44.0f);
                this.height = AndroidUtilities.dp(36.0f);
                List<TLRPC.TL_availableReaction> r42 = MediaDataController.getInstance(EmojiBottomSheet.access$4900(r4.this$0)).getReactionsList();
                int r0 = 0;
            L3:
                int r1 = Math.min(r42.size(), 8);
                ArrayList<ReactionsLayoutInBubble.VisibleReaction> r2 = this.visibleReactions;
                if (r0 >= r1) goto L6;
                r2.add(ReactionsLayoutInBubble.VisibleReaction.fromEmojicon(r42.get(r0)));
                r0 = r0 + 1;
                goto L3
            L6:
                Collections.sort(r2, new EmojiBottomSheet$StoryWidgetsCell$ReactionWidget$$ExternalSyntheticLambda0());
                if (this.visibleReactions.isEmpty() == true) goto L9;
                this.reactionHolder.setVisibleReaction(this.visibleReactions.get(this.currentIndex));
            L9:
                this.progressToNext.set(1.0f, true);
            }

            @Override // org.telegram.ui.Stories.recorder.EmojiBottomSheet.StoryWidgetsCell.BaseWidget
            public void draw(Canvas r5, float r6, float r7) {
                float r72 = r7 - AndroidUtilities.dp(4.0f);
                float r3 = this.width;
                this.bounds.set((int) r6, (int) r72, (int) (r6 + r3), (int) (r72 + r3));
                float r62 = this.bounce.getScale(0.05f);
                r5.save();
                r5.scale(r62, r62, this.bounds.centerX(), this.bounds.centerY());
                StoryReactionWidgetBackground r63 = this.background;
                RectF r73 = this.bounds;
                r63.setBounds((int) r73.left, (int) r73.top, (int) r73.right, (int) r73.bottom);
                this.background.draw(r5);
                float r64 = AndroidUtilities.dp(30.0f);
                Rect r74 = AndroidUtilities.rectTmp2;
                float r65 = r64 / 2.0f;
                r74.set((int) (this.bounds.centerX() - r65), (int) (this.bounds.centerY() - r65), (int) (this.bounds.centerX() + r65), (int) (this.bounds.centerY() + r65));
                float r66 = this.progressToNext.set(1.0f);
                this.nextReactionHolder.setBounds(r74);
                this.reactionHolder.setBounds(r74);
                if (r66 != 1.0f) goto L5;
                this.reactionHolder.draw(r5);
            L6:
                r5.restore();
                return;
            L5:
                r5.save();
                float r0 = 1.0f - r66;
                r5.scale(r0, r0, this.bounds.centerX(), this.bounds.top);
                this.nextReactionHolder.setAlpha(r0);
                this.nextReactionHolder.draw(r5);
                r5.restore();
                r5.save();
                r5.scale(r66, r66, this.bounds.centerX(), this.bounds.bottom);
                this.reactionHolder.setAlpha(r66);
                this.reactionHolder.draw(r5);
                r5.restore();
                goto L6
            }

            @Override // org.telegram.ui.Stories.recorder.EmojiBottomSheet.StoryWidgetsCell.BaseWidget
            public void onAttachToWindow(boolean r8) {
                super.onAttachToWindow(r8);
                this.reactionHolder.onAttachedToWindow(r8);
                this.nextReactionHolder.onAttachedToWindow(r8);
                Timer r0 = this.timeTimer;
                if (r0 == null) goto L5;
                r0.cancel();
                this.timeTimer = null;
            L5:
                if (r8 == false) goto L8;
                Timer r1 = new Timer();
                this.timeTimer = r1;
                r1.schedule(new AnonymousClass1(this), 2000, 2000);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$n_dt5VHIBdWZANpUXvyBvonVD8w(StoryWidgetsCell r0, Button[] r1, Weather.State r2) {
            r0.lambda$new$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetbgPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m19991$$Nest$fgetbgPaint(StoryWidgetsCell r0) {
            return r0.bgPaint;
        }

        /* renamed from: -$$Nest$fgettextPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextPaint m19992$$Nest$fgettextPaint(StoryWidgetsCell r0) {
            return r0.textPaint;
        }

        public StoryWidgetsCell(EmojiBottomSheet r9, Context r10) {
            this.this$0 = r9;
            super(r10);
            Paint r102 = new Paint(1);
            this.bgPaint = r102;
            TextPaint r1 = new TextPaint(1);
            this.textPaint = r1;
            r102.setColor(436207615);
            r1.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_CONDENSED_BOLD));
            r1.setTextSize(AndroidUtilities.dpf2(21.3f));
            r1.setColor(-1);
            ArrayList r103 = new ArrayList();
            this.widgets = r103;
            setPadding(0, 0, 0, 0);
            if (r9.canShowWidget(4) == false) goto L6;
            r103.add(new Button(this, 4, R.drawable.msg_limit_links, LocaleController.getString(R.string.StoryWidgetLink)).needsPremium());
        L6:
            if (r9.canShowWidget(0) == false) goto L9;
            r103.add(new Button(this, 0, R.drawable.map_pin3, LocaleController.getString(R.string.StoryWidgetLocation)));
        L9:
            if (r9.canShowWidget(5) == false) goto L32;
            Weather.State r4 = Weather.getCached();
            Button[] r5 = {null};
            StringBuilder r6 = new StringBuilder();
            if (r4 != null) goto L13;
            String r7 = "🌤";
        L14:
            r6.append(r7);
            r6.append(" ");
            if (r4 == null) goto L17;
            String r72 = r4.getTemperature();
        L21:
            r6.append(r72);
            CharSequence r12 = Emoji.replaceEmoji(r6.toString(), r1.getFontMetricsInt(), false);
            CharSequence r13 = r12;
            if (MessagesController.getInstance(EmojiBottomSheet.access$4700(r9)).storyWeatherPreload == false) goto L27;
            r13 = r12;
            r13 = r12;
            if (PermissionRequest.hasPermission("android.permission.ACCESS_COARSE_LOCATION") == false) goto L27;
            if (r4 != null) goto L27;
            SpannableStringBuilder r14 = new SpannableStringBuilder("___");
            r14.setSpan(new LoadingSpan(this, AndroidUtilities.dp(68.0f)), 0, r14.length(), 33);
            r5[0] = new Button(this, this, 5, r14);
            Weather.fetch(false, new EmojiBottomSheet$StoryWidgetsCell$$ExternalSyntheticLambda0(this, r5));
            r13 = r14;
        L27:
            Button r2 = r5[0];
            if (r2 != null) goto L30;
            r2 = new Button(this, this, 5, r13);
        L30:
            r103.add(r2);
            goto L32
        L17:
            if (Weather.isDefaultCelsius() == false) goto L19;
            r72 = "24°C";
            goto L21
        L19:
            r72 = "72°F";
            goto L21
        L13:
            r7 = r4.getEmoji();
        L32:
            if (r9.canShowWidget(1) == false) goto L35;
            r103.add(new Button(this, 1, R.drawable.filled_widget_music, LocaleController.getString(R.string.StoryWidgetAudio)));
        L35:
            if (r9.canShowWidget(2) == false) goto L38;
            r103.add(new Button(this, 2, R.drawable.filled_premium_camera, LocaleController.getString(R.string.StoryWidgetPhoto)));
        L38:
            if (r9.canShowWidget(3) == false) goto L41;
            r103.add(new ReactionWidget(this));
            return;
        }

        private /* synthetic */ void lambda$new$0(Button[] r4, Weather.State r5) {
            Button r42 = r4[0];
            StringBuilder r1 = new StringBuilder();
            if (r5 != null) goto L5;
            String r2 = "🌤";
        L6:
            r1.append(r2);
            r1.append(" ");
            if (r5 == null) goto L9;
            String r52 = r5.getTemperature();
        L13:
            r1.append(r52);
            r42.setText(Emoji.replaceEmoji(r1.toString(), this.textPaint.getFontMetricsInt(), false));
            invalidate();
            requestLayout();
            return;
        L9:
            if (Weather.isDefaultCelsius() == false) goto L11;
            r52 = "24°C";
            goto L13
        L11:
            r52 = "72°F";
            goto L13
        L5:
            r2 = r5.getEmoji();
            goto L6
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r8) {
            int r0 = 0;
        L22:
            float[] r1 = this.lineWidths;     // Catch: Exception -> L6
            if (r0 >= r1.length) goto L8;
            r1[r0] = 0.0f;     // Catch: Exception -> L6
            r0 = r0 + 1;     // Catch: Exception -> L6
            goto L22
        L8:
            Iterator<BaseWidget> r02 = this.widgets.iterator();     // Catch: Exception -> L6
        L9:
            if (r02.hasNext() == false) goto L17;
            BaseWidget r12 = r02.next();     // Catch: Exception -> L6
            int r2 = r12.layoutLine - 1;
            float[] r4 = this.lineWidths;     // Catch: Exception -> L6
            float r5 = r4[r2];     // Catch: Exception -> L6
            if (r5 <= 0.0f) goto L14;
            r4[r2] = r5 + AndroidUtilities.dp(10.0f);     // Catch: Exception -> L6
        L14:
            float[] r42 = this.lineWidths;     // Catch: Exception -> L6
            r42[r2] = r42[r2] + r12.animatedWidth.set(r12.width);     // Catch: Exception -> L6
        L17:
            Iterator<BaseWidget> r03 = this.widgets.iterator();
        L19:
            if (r03.hasNext() == false) goto L21;
            BaseWidget r13 = r03.next();
            r13.draw(r8, (getPaddingLeft() + ((((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - this.lineWidths[r13.layoutLine - 1]) / 2.0f)) + r13.layoutX, AndroidUtilities.dp(12.0f) + ((r13.layoutLine - 1) * AndroidUtilities.dp(48.0f)));
            goto L19
        L21:
            return;
        L6:
            e = move-exception;
            FileLog.e(e);
            goto L17
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            Iterator<BaseWidget> r2 = this.widgets.iterator();
        L4:
            if (r2.hasNext() == false) goto L6;
            r2.next().onAttachToWindow(true);
            goto L4
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            Iterator<BaseWidget> r2 = this.widgets.iterator();
        L4:
            if (r2.hasNext() == false) goto L6;
            r2.next().onAttachToWindow(false);
            goto L4
        }

        @Override // android.view.View
        public void onMeasure(int r10, int r11) {
            int r102 = View.MeasureSpec.getSize(r10);
            int r112 = (r102 - getPaddingLeft()) - getPaddingRight();
            Iterator<BaseWidget> r0 = this.widgets.iterator();
            int r3 = 1;
            float r4 = 0.0f;
        L4:
            if (r0.hasNext() == false) goto L9;
            BaseWidget r5 = r0.next();
            r5.layoutX = r4;
            r4 = r4 + (r5.width + AndroidUtilities.dp(10.0f));
            if (r4 <= r112) goto L8;
            r3 = r3 + 1;
            r5.layoutX = 0.0f;
            r4 = (r5.width + AndroidUtilities.dp(10.0f)) + 0.0f;
        L8:
            r5.layoutLine = r3;
            goto L4
        L9:
            float[] r113 = this.lineWidths;
            if (r113 != null) goto L12;
        L15:
            this.lineWidths = new float[r3];
        L16:
            Iterator<BaseWidget> r114 = this.widgets.iterator();
        L18:
            if (r114.hasNext() == false) goto L23;
            BaseWidget r02 = r114.next();
            int r42 = r02.layoutLine - 1;
            float[] r52 = this.lineWidths;
            float r7 = r52[r42];
            if (r7 <= 0.0f) goto L22;
            r52[r42] = r7 + AndroidUtilities.dp(10.0f);
        L22:
            float[] r53 = this.lineWidths;
            r53[r42] = r53[r42] + r02.width;
            goto L18
        L23:
            setMeasuredDimension(r102, (AndroidUtilities.dp(24.0f) + (AndroidUtilities.dp(36.0f) * r3)) + ((r3 - 1) * AndroidUtilities.dp(12.0f)));
            return;
        L12:
            if (r113.length != r3) goto L15;
            Arrays.fill(r113, 0.0f);
            goto L16
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r7) {
            Iterator<BaseWidget> r0 = this.widgets.iterator();
        L4:
            if (r0.hasNext() == false) goto L8;
            BaseWidget r1 = r0.next();
            if (r1.bounds.contains(r7.getX(), r7.getY()) == false) goto L4;
        L9:
            Iterator<BaseWidget> r02 = this.widgets.iterator();
        L11:
            if (r02.hasNext() == false) goto L16;
            BaseWidget r2 = r02.next();
            if (r2 == r1) goto L11;
            r2.bounce.setPressed(false);
            goto L11
        L16:
            if (r1 == null) goto L25;
            ButtonBounce r22 = r1.bounce;
            if (r7.getAction() != 1) goto L20;
        L22:
            boolean r4 = false;
        L23:
            r22.setPressed(r4);
            goto L25
        L20:
            if (r7.getAction() == 3) goto L22;
            r4 = true;
        L25:
            if (r7.getAction() != 1) goto L30;
            if (r1 == null) goto L30;
            Utilities.Callback<Integer> r6 = this.onClickListener;
            if (r6 == null) goto L30;
            r6.run(Integer.valueOf(r1.id));
        L30:
            if (r1 == null) goto L32;
            return true;
        L32:
            return false;
        L8:
            r1 = null;
            goto L9
        }

        public void setOnButtonClickListener(Utilities.Callback<Integer> r1) {
            this.onClickListener = r1;
        }
    }

    public static class TabsView extends View {
        private StaticLayout emojiLayout;
        private float emojiLayoutLeft;
        private float emojiLayoutWidth;
        private final RectF emojiRect;
        private StaticLayout gifsLayout;
        private float gifsLayoutLeft;
        private float gifsLayoutWidth;
        private final RectF gifsRect;
        private int lastWidth;
        private Utilities.Callback<Integer> onTypeSelected;
        private final Paint selectPaint;
        private final RectF selectRect;
        private StaticLayout stickersLayout;
        private float stickersLayoutLeft;
        private float stickersLayoutWidth;
        private final RectF stickersRect;
        private final TextPaint textPaint;
        private float type;

        public TabsView(Context r2) {
            super(r2);
            this.textPaint = new TextPaint(1);
            this.selectPaint = new Paint(1);
            this.emojiRect = new RectF();
            this.stickersRect = new RectF();
            this.gifsRect = new RectF();
            this.selectRect = new RectF();
        }

        private RectF getRect(int r2) {
            if (r2 > 0) goto L6;
            return this.emojiRect;
        L6:
            if (r2 != 1) goto L10;
            return this.stickersRect;
        L10:
            return this.gifsRect;
        }

        private void updateLayouts() {
            this.textPaint.setTextSize(AndroidUtilities.dp(14.0f));
            this.textPaint.setTypeface(AndroidUtilities.bold());
            String r4 = LocaleController.getString("Emoji");
            TextPaint r5 = this.textPaint;
            int r6 = getMeasuredWidth();
            Layout.Alignment r11 = Layout.Alignment.ALIGN_NORMAL;
            StaticLayout r3 = new StaticLayout(r4, r5, r6, r11, 1.0f, 0.0f, false);
            this.emojiLayout = r3;
            float r2 = 0.0f;
            if (r3.getLineCount() < 1) goto L5;
            float r0 = this.emojiLayout.getLineWidth(0);
        L6:
            this.emojiLayoutWidth = r0;
            if (this.emojiLayout.getLineCount() < 1) goto L9;
            float r02 = this.emojiLayout.getLineLeft(0);
        L10:
            this.emojiLayoutLeft = r02;
            StaticLayout r7 = new StaticLayout(LocaleController.getString("AccDescrStickers"), this.textPaint, getMeasuredWidth(), r11, 1.0f, 0.0f, false);
            this.stickersLayout = r7;
            if (r7.getLineCount() < 1) goto L13;
            float r03 = this.stickersLayout.getLineWidth(0);
        L14:
            this.stickersLayoutWidth = r03;
            if (this.stickersLayout.getLineCount() < 1) goto L17;
            float r04 = this.stickersLayout.getLineLeft(0);
        L18:
            this.stickersLayoutLeft = r04;
            StaticLayout r72 = new StaticLayout(LocaleController.getString(R.string.AccDescrGIFs), this.textPaint, getMeasuredWidth(), r11, 1.0f, 0.0f, false);
            this.gifsLayout = r72;
            if (r72.getLineCount() < 1) goto L21;
            float r05 = this.gifsLayout.getLineWidth(0);
        L22:
            this.gifsLayoutWidth = r05;
            if (this.gifsLayout.getLineCount() < 1) goto L25;
            r2 = this.gifsLayout.getLineLeft(0);
        L25:
            this.gifsLayoutLeft = r2;
            float r06 = AndroidUtilities.dp(14.0f) / 2.0f;
            float r42 = AndroidUtilities.dp(66.0f) / 2.0f;
            float r52 = (getMeasuredWidth() - ((((((AndroidUtilities.dp(12.0f) + this.emojiLayoutWidth) + AndroidUtilities.dp(36.0f)) + this.stickersLayoutWidth) + AndroidUtilities.dp(36.0f)) + this.gifsLayoutWidth) + AndroidUtilities.dp(12.0f))) / 2.0f;
            this.emojiRect.set(r52, r06, (this.emojiLayoutWidth + r52) + AndroidUtilities.dp(24.0f), r42);
            float r53 = r52 + (this.emojiLayoutWidth + AndroidUtilities.dp(36.0f));
            this.stickersRect.set(r53, r06, (this.stickersLayoutWidth + r53) + AndroidUtilities.dp(24.0f), r42);
            float r54 = r53 + (this.stickersLayoutWidth + AndroidUtilities.dp(36.0f));
            this.gifsRect.set(r54, r06, (this.gifsLayoutWidth + r54) + AndroidUtilities.dp(24.0f), r42);
            AndroidUtilities.dp(36.0f);
            return;
        L21:
            r05 = 0.0f;
            goto L22
        L17:
            r04 = 0.0f;
            goto L18
        L13:
            r03 = 0.0f;
            goto L14
        L9:
            r02 = 0.0f;
            goto L10
        L5:
            r0 = 0.0f;
            goto L6
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r11) {
            r11.drawColor(-14737633);
            this.selectPaint.setColor(-13224394);
            AndroidUtilities.lerp(getRect((int) this.type), getRect((int) Math.ceil(this.type)), this.type - ((int) r2), this.selectRect);
            r11.drawRoundRect(this.selectRect, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f), this.selectPaint);
            if (this.emojiLayout == null) goto L6;
            r11.save();
            float r0 = (this.emojiRect.left + AndroidUtilities.dp(12.0f)) - this.emojiLayoutLeft;
            RectF r7 = this.emojiRect;
            r11.translate(r0, r7.top + ((r7.height() - this.emojiLayout.getHeight()) / 2.0f));
            this.textPaint.setColor(ColorUtils.blendARGB(-8158333, -1, Utilities.clamp(1.0f - Math.abs(this.type - 0.0f), 1.0f, 0.0f)));
            this.emojiLayout.draw(r11);
            r11.restore();
        L6:
            if (this.stickersLayout == null) goto L9;
            r11.save();
            float r02 = (this.stickersRect.left + AndroidUtilities.dp(12.0f)) - this.stickersLayoutLeft;
            RectF r72 = this.stickersRect;
            r11.translate(r02, r72.top + ((r72.height() - this.stickersLayout.getHeight()) / 2.0f));
            this.textPaint.setColor(ColorUtils.blendARGB(-8158333, -1, Utilities.clamp(1.0f - Math.abs(this.type - 1.0f), 1.0f, 0.0f)));
            this.stickersLayout.draw(r11);
            r11.restore();
        L9:
            if (this.gifsLayout == null) goto L12;
            r11.save();
            float r03 = (this.gifsRect.left + AndroidUtilities.dp(12.0f)) - this.gifsLayoutLeft;
            RectF r3 = this.gifsRect;
            r11.translate(r03, r3.top + ((r3.height() - this.gifsLayout.getHeight()) / 2.0f));
            this.textPaint.setColor(ColorUtils.blendARGB(-8158333, -1, Utilities.clamp(1.0f - Math.abs(this.type - 2.0f), 1.0f, 0.0f)));
            this.gifsLayout.draw(r11);
            r11.restore();
            return;
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            setMeasuredDimension(View.MeasureSpec.getSize(r2), AndroidUtilities.dp(40.0f) + AndroidUtilities.navigationBarHeight);
            if (getMeasuredWidth() == this.lastWidth) goto L5;
        L6:
            updateLayouts();
        L7:
            this.lastWidth = getMeasuredWidth();
            return;
        L5:
            if (this.emojiLayout != null) goto L7;
            goto L6
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r5) {
            if (r5.getAction() != 0) goto L6;
            return true;
        L6:
            if (r5.getAction() != 1) goto L20;
            if (this.onTypeSelected == null) goto L20;
            if (this.emojiRect.contains(r5.getX(), r5.getY()) == false) goto L13;
            this.onTypeSelected.run(0);
        L18:
            return true;
        L13:
            if (this.stickersRect.contains(r5.getX(), r5.getY()) == false) goto L16;
            this.onTypeSelected.run(1);
            goto L18
        L16:
            if (this.gifsRect.contains(r5.getX(), r5.getY()) == false) goto L18;
            this.onTypeSelected.run(2);
        L20:
            return super.onTouchEvent(r5);
        }

        public void setOnTypeSelected(Utilities.Callback<Integer> r1) {
            this.onTypeSelected = r1;
        }

        public void setType(float r1) {
            this.type = r1;
            invalidate();
        }
    }

    public static /* synthetic */ void $r8$lambda$Ho5A05scjtybijY4fZohSztZWUo(EmojiBottomSheet r0, Integer r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: $r8$lambda$jDyMBPFlvD9Y6To2ozg6Ggr0-8U, reason: not valid java name */
    public static /* synthetic */ void m19945$r8$lambda$jDyMBPFlvD9Y6To2ozg6Ggr08U(EmojiBottomSheet r0, Integer r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$kY3jSaq3YS30VzqzH4jg34TFYDc(EmojiBottomSheet r0, int r1) {
        r0.lambda$onWidgetClick$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$y16UEqRX3x_dL59f8gBfoQ6JYzE(EmojiBottomSheet r0) {
        r0.openPremium();
    }

    public static /* synthetic */ void $r8$lambda$ygUdWrss1ezr8ItKKGxYiZAH86Y(DialogInterface r0) {
    }

    /* renamed from: -$$Nest$fgetcategoryIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19946$$Nest$fgetcategoryIndex(EmojiBottomSheet r0) {
        return r0.categoryIndex;
    }

    /* renamed from: -$$Nest$fgetdrawBlurBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback2 m19947$$Nest$fgetdrawBlurBitmap(EmojiBottomSheet r0) {
        return r0.drawBlurBitmap;
    }

    /* renamed from: -$$Nest$fgetgreeting, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19948$$Nest$fgetgreeting(EmojiBottomSheet r0) {
        return r0.greeting;
    }

    /* renamed from: -$$Nest$fgetmaxPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19949$$Nest$fgetmaxPadding(EmojiBottomSheet r0) {
        return r0.maxPadding;
    }

    /* renamed from: -$$Nest$fgetonDocumentSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback3Return m19950$$Nest$fgetonDocumentSelected(EmojiBottomSheet r0) {
        return r0.onDocumentSelected;
    }

    /* renamed from: -$$Nest$fgetonPlusSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m19951$$Nest$fgetonPlusSelected(EmojiBottomSheet r0) {
        return r0.onPlusSelected;
    }

    /* renamed from: -$$Nest$fgetonlyStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19952$$Nest$fgetonlyStickers(EmojiBottomSheet r0) {
        return r0.onlyStickers;
    }

    /* renamed from: -$$Nest$fgetquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m19953$$Nest$fgetquery(EmojiBottomSheet r0) {
        return r0.query;
    }

    /* renamed from: -$$Nest$fgettabsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TabsView m19954$$Nest$fgettabsView(EmojiBottomSheet r0) {
        return r0.tabsView;
    }

    /* renamed from: -$$Nest$fgettop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19955$$Nest$fgettop(EmojiBottomSheet r0) {
        return r0.top;
    }

    /* renamed from: -$$Nest$fgetviewPager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPagerFixed m19956$$Nest$fgetviewPager(EmojiBottomSheet r0) {
        return r0.viewPager;
    }

    /* renamed from: -$$Nest$fputcategoryIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19957$$Nest$fputcategoryIndex(EmojiBottomSheet r0, int r1) {
        r0.categoryIndex = r1;
    }

    /* renamed from: -$$Nest$fputmaxPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19958$$Nest$fputmaxPadding(EmojiBottomSheet r0, float r1) {
        r0.maxPadding = r1;
    }

    /* renamed from: -$$Nest$fputquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19959$$Nest$fputquery(EmojiBottomSheet r0, String r1) {
        r0.query = r1;
    }

    /* renamed from: -$$Nest$fputtop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19960$$Nest$fputtop(EmojiBottomSheet r0, float r1) {
        r0.top = r1;
    }

    /* renamed from: -$$Nest$monWidgetClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19961$$Nest$monWidgetClick(EmojiBottomSheet r0, int r1) {
        r0.onWidgetClick(r1);
    }

    static {
    }

    public EmojiBottomSheet(Context r7, boolean r8, Theme.ResourcesProvider r9, boolean r10) {
        super(r7, true, r9);
        this.query = null;
        this.categoryIndex = -1;
        this.widgets = new AnonymousClass1(this);
        this.plus = new AnonymousClass2(this);
        this.maxPadding = -1.0f;
        this.onlyStickers = r8;
        this.greeting = r10;
        this.useSmoothKeyboard = true;
        fixNavigationBar(Theme.getColor(Theme.key_dialogBackground, r9));
        this.occupyNavigationBar = true;
        setUseLightStatusBar(false);
        this.containerView = new ContainerView(this, r7);
        AnonymousClass4 r102 = new AnonymousClass4(this, r7);
        this.viewPager = r102;
        if (r8 == false) goto L5;
        int r2 = 0;
    L6:
        r102.currentPosition = r2;
        r102.setAdapter(new AnonymousClass5(this, r8, r7));
        this.containerView.addView(r102, LayoutHelper.createFrame(-1, -1, 87));
        new KeyboardNotifier(this.containerView, new EmojiBottomSheet$$ExternalSyntheticLambda1(this));
        if (r8 == true) goto L9;
        TabsView r22 = new TabsView(r7);
        this.tabsView = r22;
        r22.setOnTypeSelected(new EmojiBottomSheet$$ExternalSyntheticLambda2(this));
        this.tabsView.setType(r102.currentPosition);
        this.containerView.addView(this.tabsView, LayoutHelper.createFrame(-1, -2, 87));
    L9:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.stickersDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.groupStickersDidLoad);
        FileLog.disableGson(true);
        if (r8 == true) goto L12;
        MediaDataController.getInstance(this.currentAccount).checkStickers(5);
        MediaDataController.getInstance(this.currentAccount).checkFeaturedEmoji();
        MediaDataController.getInstance(this.currentAccount).loadRecents(0, true, true, false);
    L12:
        MediaDataController.getInstance(this.currentAccount).checkStickers(0);
        MediaDataController.getInstance(this.currentAccount).loadRecents(0, false, true, false);
        MediaDataController.getInstance(this.currentAccount).loadRecents(2, false, true, false);
        MediaDataController.getInstance(this.currentAccount).loadRecents(7, false, true, false);
        return;
    L5:
        r2 = savedPosition;
        goto L6
    }

    public static /* synthetic */ ViewGroup access$000(EmojiBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ boolean access$100(EmojiBottomSheet r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ int access$1000(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1100(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1200(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1300(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1400(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1500(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1600(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1700(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1800(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1900(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(EmojiBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$2000(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2100(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2200(EmojiBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$2300(EmojiBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ boolean access$2400(EmojiBottomSheet r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2500(EmojiBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2600(EmojiBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$2700(EmojiBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$2800(EmojiBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$2900(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(EmojiBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$3000(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3100(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$3200(EmojiBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$3300(EmojiBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$3400(EmojiBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$3500(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3600(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3700(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3800(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3900(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(EmojiBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4000(EmojiBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$4100(EmojiBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4200(EmojiBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4300(EmojiBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4400(EmojiBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$4500(EmojiBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$4600(EmojiBottomSheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$4700(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4800(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4900(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$500(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$600(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$700(EmojiBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$800(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$900(EmojiBottomSheet r0) {
        return r0.currentAccount;
    }

    public static int getCacheType(boolean r0) {
        if (r0 == false) goto L4;
        int r02 = 1;
    L6:
        if (LiteMode.isEnabled(r02) == false) goto L9;
        return 3;
    L9:
        return 13;
    L4:
        r02 = LiteMode.FLAG_ANIMATED_EMOJI_KEYBOARD;
        goto L6
    }

    private /* synthetic */ void lambda$new$2(Integer r4) {
        boolean r42 = this.wasKeyboardVisible;
        boolean r0 = this.keyboardVisible;
        if (r42 == r0) goto L9;
        this.wasKeyboardVisible = r0;
        this.container.clearAnimation();
        float r02 = 0.0f;
        if (this.keyboardVisible == false) goto L7;
        int r43 = AndroidUtilities.displaySize.y;
        int r1 = this.keyboardHeight;
        r02 = Math.min(0.0f, Math.max(((r43 - r1) * 0.3f) - this.top, (-r1) / 3.0f));
    L7:
        this.container.animate().translationY(r02).setDuration(250).setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator).start();
        return;
    }

    private /* synthetic */ void lambda$new$3(Integer r3) {
        if (this.viewPager.isManualScrolling() == false) goto L5;
        return;
    L5:
        if (this.viewPager.getCurrentPosition() == r3.intValue()) goto L9;
        this.viewPager.scrollToPosition(r3.intValue());
        this.tabsView.setType(r3.intValue());
        return;
    }

    private /* synthetic */ void lambda$onWidgetClick$1(int r1) {
        onWidgetClick(r1);
    }

    private void onWidgetClick(int r2) {
        if (canClickWidget(Integer.valueOf(r2)) == true) goto L5;
        return;
    L5:
        if (r2 != 1) goto L10;
        if (checkAudioPermission(new EmojiBottomSheet$$ExternalSyntheticLambda3(this, r2)) == true) goto L10;
        return;
    L10:
        if (this.onWidgetSelected.run(Integer.valueOf(r2)).booleanValue() == false) goto L14;
        dismiss();
        return;
    }

    private void openPremium() {
        Bulletin.hideVisible();
        PremiumFeatureBottomSheet r0 = new PremiumFeatureBottomSheet(new AnonymousClass3(this), 14, false);
        r0.setOnDismissListener(new EmojiBottomSheet$$ExternalSyntheticLambda0());
        r0.show();
    }

    private CharSequence premiumText(String r4) {
        return AndroidUtilities.replaceSingleTag(r4, Theme.key_chat_messageLinkIn, 0, new EmojiBottomSheet$$ExternalSyntheticLambda4(this), this.resourcesProvider);
    }

    public boolean canClickWidget(Integer r1) {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        if (this.viewPager.getTranslationY() < ((int) this.maxPadding)) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean canShowWidget(Integer r1) {
        return true;
    }

    public boolean checkAudioPermission(Runnable r1) {
        return true;
    }

    public void closeKeyboard() {
        int r0 = 0;
        this.keyboardVisible = false;
        this.container.animate().translationY(0.0f).setDuration(250).setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator).start();
        View[] r4 = this.viewPager.getViewPages();
    L4:
        if (r0 >= r4.length) goto L16;
        View r1 = r4[r0];
        if ((r1 instanceof Page) == false) goto L11;
        SearchField r12 = ((Page) r1).searchField;
        if (r12 == null) goto L15;
        AndroidUtilities.hideKeyboard(SearchField.m19986$$Nest$fgeteditText(r12));
    L15:
        r0 = r0 + 1;
        goto L4
    L11:
        if ((r1 instanceof GifPage) == false) goto L15;
        SearchField r13 = ((GifPage) r1).searchField;
        if (r13 == null) goto L15;
        AndroidUtilities.hideKeyboard(SearchField.m19986$$Nest$fgeteditText(r13));
        goto L15
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r5, int r6, Object... r7) {
        if (r5 != NotificationCenter.stickersDidLoad) goto L5;
    L6:
        View[] r4 = this.viewPager.getViewPages();
        int r0 = 0;
    L8:
        if (r0 >= r4.length) goto L29;
        View r1 = r4[r0];
        if ((r1 instanceof Page) == false) goto L22;
        Page r12 = (Page) r1;
        if (r5 != NotificationCenter.groupStickersDidLoad) goto L14;
    L21:
        r12.adapter.update();
        goto L22
    L14:
        if (r12.currentType != 0) goto L18;
        if (((Integer) r7[0]).intValue() == 5) goto L21;
    L18:
        if (r12.currentType != 1) goto L22;
        if (((Integer) r7[0]).intValue() == 0) goto L21;
    L22:
        r0 = r0 + 1;
        goto L8
    L29:
        return;
    L5:
        if (r5 == NotificationCenter.groupStickersDidLoad) goto L6;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.stickersDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.groupStickersDidLoad);
        closeKeyboard();
        super.dismiss();
        FileLog.disableGson(false);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public int getContainerViewHeight() {
        if (this.containerView.getMeasuredHeight() > 0) goto L7;
        return AndroidUtilities.displaySize.y;
    L7:
        return (int) (this.containerView.getMeasuredHeight() - this.viewPager.getY());
    }

    public boolean hasWidgets() {
        if (this.onWidgetSelected != null) goto L5;
    L15:
        return false;
    L5:
        if (canShowWidget(0) == false) goto L7;
    L14:
        return true;
    L7:
        if (canShowWidget(1) == true) goto L14;
        if (canShowWidget(2) == true) goto L14;
        if (canShowWidget(3) == true) goto L14;
        if (canShowWidget(4) == false) goto L15;
        goto L14
    }

    public void setBlurDelegate(Utilities.Callback2<Bitmap, Float> r1) {
        this.drawBlurBitmap = r1;
    }

    public void showPremiumBulletin(String r4) {
        this.container.performHapticFeedback(3);     // Catch: Exception -> L5
    L3:
        BulletinFactory.of(this.container, this.resourcesProvider).createSimpleBulletin(R.raw.star_premium_2, LocaleController.getString(R.string.IncreaseLimit), premiumText(r4)).show(true);
    }

    public EmojiBottomSheet whenDocumentSelected(Utilities.Callback3Return<Object, TLRPC.Document, Boolean, Boolean> r1) {
        this.onDocumentSelected = r1;
        return this;
    }

    public EmojiBottomSheet whenPlusSelected(Runnable r4) {
        this.onPlusSelected = r4;
        View[] r42 = this.viewPager.getViewPages();
        int r0 = 0;
    L4:
        if (r0 >= r42.length) goto L9;
        View r1 = r42[r0];
        if ((r1 instanceof Page) == false) goto L8;
        ((Page) r1).adapter.update();
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        return this;
    }

    public EmojiBottomSheet whenWidgetSelected(Utilities.CallbackReturn<Integer, Boolean> r4) {
        this.onWidgetSelected = r4;
        View[] r42 = this.viewPager.getViewPages();
        int r0 = 0;
    L4:
        if (r0 >= r42.length) goto L9;
        View r1 = r42[r0];
        if ((r1 instanceof Page) == false) goto L8;
        ((Page) r1).adapter.update();
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        return this;
    }
}
