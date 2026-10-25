package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.CacheFetcher;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes3.dex */
public abstract class StickerCategoriesListView extends RecyclerListView {
    private static EmojiGroupFetcher fetcher;
    static int loadedCategoryIcons;
    private static Set<Integer> loadedIconsType;
    public static CacheFetcher<String, TLRPC.TL_emojiList> search;
    private Adapter adapter;
    private Paint backgroundPaint;
    private EmojiCategory[] categories;
    private boolean categoriesShouldShow;
    private ValueAnimator categoriesShownAnimator;
    private float categoriesShownT;
    private int categoriesType;
    private int dontOccupyWidth;
    public boolean isGlassDesign;
    public Integer layerNum;
    private LinearLayoutManager layoutManager;
    private AnimatedFloat leftBoundAlpha;
    private Drawable leftBoundDrawable;
    private Utilities.Callback<EmojiCategory> onCategoryClick;
    private Utilities.Callback<Boolean> onScrollFully;
    private Utilities.Callback<Integer> onScrollIntoOccupiedWidth;
    private View paddingView;
    private int paddingWidth;
    private final RectF rect1;
    private final RectF rect2;
    private final RectF rect3;
    private AnimatedFloat rightBoundAlpha;
    private Drawable rightBoundDrawable;
    private boolean scrolledFully;
    private boolean scrolledIntoOccupiedWidth;
    private AnimatedFloat selectedAlpha;
    private int selectedCategoryIndex;
    private AnimatedFloat selectedIndex;
    private Paint selectedPaint;
    private float shownButtonsAtStart;

    /* renamed from: org.telegram.ui.Components.StickerCategoriesListView$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ StickerCategoriesListView this$0;

        public AnonymousClass1(StickerCategoriesListView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            StickerCategoriesListView r22 = this.this$0;
            StickerCategoriesListView.m13061$$Nest$msetCategoriesShownT(r22, ((Float) StickerCategoriesListView.m13051$$Nest$fgetcategoriesShownAnimator(r22).getAnimatedValue()).floatValue());
            StickerCategoriesListView.m13057$$Nest$fputcategoriesShownAnimator(this.this$0, null);
        }
    }

    /* loaded from: classes7.dex */
    public class Adapter extends RecyclerListView.SelectionAdapter {
        private int lastItemCount;
        final /* synthetic */ StickerCategoriesListView this$0;

        /* renamed from: org.telegram.ui.Components.StickerCategoriesListView$Adapter$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass1(Adapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r6, int r7) {
                int r62 = View.MeasureSpec.getSize(r6);
                if (r62 > 0) goto L5;
                r62 = ((View) getParent()).getMeasuredWidth();
            L5:
                int r0 = View.MeasureSpec.getSize(r7) - AndroidUtilities.dp(4.0f);
                StickerCategoriesListView r2 = this.this$1.this$0;
                if (StickerCategoriesListView.m13053$$Nest$fgetdontOccupyWidth(r2) <= 0) goto L8;
                int r3 = StickerCategoriesListView.m13053$$Nest$fgetdontOccupyWidth(this.this$1.this$0) + AndroidUtilities.dp(4.0f);
            L9:
                int r63 = Math.max(r3, (int) (r62 - Math.min(((this.this$1.getItemCount() - 1) * r0) + AndroidUtilities.dp(4.0f), StickerCategoriesListView.m13056$$Nest$fgetshownButtonsAtStart(this.this$1.this$0) * r0)));
                StickerCategoriesListView.m13059$$Nest$fputpaddingWidth(r2, r63);
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(r63, TLObject.FLAG_30), r7);
                return;
            L8:
                r3 = 0;
                goto L9
            }
        }

        private Adapter(StickerCategoriesListView r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(StickerCategoriesListView r1, StickerCategoriesListViewIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (StickerCategoriesListView.m13050$$Nest$fgetcategories(this.this$0) != null) goto L5;
            int r0 = 0;
        L6:
            int r02 = r0 + 1;
            if (r02 != this.lastItemCount) goto L9;
        L12:
            return r02;
        L9:
            if (StickerCategoriesListView.m13054$$Nest$fgetpaddingView(this.this$0) == null) goto L11;
            StickerCategoriesListView.m13054$$Nest$fgetpaddingView(this.this$0).requestLayout();
        L11:
            this.lastItemCount = r02;
            goto L12
        L5:
            r0 = StickerCategoriesListView.m13050$$Nest$fgetcategories(this.this$0).length;
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (r1 != 0) goto L5;
            return 0;
        L5:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 1) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
            boolean r1 = true;
            if (r5.getItemViewType() == 1) goto L5;
            return;
        L5:
            if (StickerCategoriesListView.m13050$$Nest$fgetcategories(this.this$0) == null) goto L13;
            int r62 = r6 - 1;
            EmojiCategory r0 = StickerCategoriesListView.m13050$$Nest$fgetcategories(this.this$0)[r62];
            CategoryButton r52 = (CategoryButton) r5.itemView;
            if (StickerCategoriesListView.m13055$$Nest$fgetselectedCategoryIndex(this.this$0) == r62) goto L10;
            r1 = false;
        L10:
            r52.set(r0, r62, r1);
            r52.setAlpha(StickerCategoriesListView.m13052$$Nest$fgetcategoriesShownT(this.this$0));
            r52.setScaleX(StickerCategoriesListView.m13052$$Nest$fgetcategoriesShownT(this.this$0));
            r52.setScaleY(StickerCategoriesListView.m13052$$Nest$fgetcategoriesShownT(this.this$0));
            r52.play(false);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L4;
            StickerCategoriesListView r22 = this.this$0;
            View r32 = new AnonymousClass1(this, this.this$0.getContext());
            StickerCategoriesListView.m13058$$Nest$fputpaddingView(r22, r32);
        L6:
            return new RecyclerListView.Holder(r32);
        L4:
            StickerCategoriesListView r1 = this.this$0;
            r32 = new CategoryButton(r1, r1.getContext());
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r4) {
            boolean r1 = true;
            if (r4.getItemViewType() != 1) goto L10;
            CategoryButton r0 = (CategoryButton) r4.itemView;
            if (StickerCategoriesListView.m13055$$Nest$fgetselectedCategoryIndex(this.this$0) == (r4.getAdapterPosition() - 1)) goto L8;
            r1 = false;
        L8:
            r0.setSelected(r1, false);
            r0.play(false);
            return;
        }
    }

    /* loaded from: classes7.dex */
    public class CategoryButton extends RLottieImageView {
        ValueAnimator backAnimator;
        private int imageColor;
        private int index;
        private long lastPlayed;
        ValueAnimator loadAnimator;
        float loadProgress;
        private boolean loaded;
        float pressedProgress;
        private ValueAnimator selectedAnimator;
        private float selectedT;
        final /* synthetic */ StickerCategoriesListView this$0;

        /* renamed from: org.telegram.ui.Components.StickerCategoriesListView$CategoryButton$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ CategoryButton this$1;

            public AnonymousClass1(CategoryButton r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                CategoryButton r22 = this.this$1;
                r22.loadProgress = 1.0f;
                r22.invalidate();
                this.this$1.loadAnimator = null;
            }
        }

        /* renamed from: org.telegram.ui.Components.StickerCategoriesListView$CategoryButton$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ CategoryButton this$1;

            public AnonymousClass2(CategoryButton r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                CategoryButton r22 = this.this$1;
                CategoryButton.m13065$$Nest$mupdateSelectedT(r22, ((Float) CategoryButton.m13063$$Nest$fgetselectedAnimator(r22).getAnimatedValue()).floatValue());
                CategoryButton.m13064$$Nest$fputselectedAnimator(this.this$1, null);
            }
        }

        /* renamed from: org.telegram.ui.Components.StickerCategoriesListView$CategoryButton$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ CategoryButton this$1;

            public AnonymousClass3(CategoryButton r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                super.onAnimationEnd(r1);
                this.this$1.backAnimator = null;
            }
        }

        public static /* synthetic */ void $r8$lambda$68_rboJihzlgwlTkoRWdWBGQ3Lw(CategoryButton r0) {
            r0.lambda$set$1();
        }

        public static /* synthetic */ void $r8$lambda$8m2Rrmc6pp66ssVSl8PoTG2NP9Y(CategoryButton r0, ValueAnimator r1) {
            r0.lambda$onLoaded$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$YyiQZ3NrDfeSFBz5H4sRPbpmljs(CategoryButton r0, ValueAnimator r1) {
            r0.lambda$setPressed$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$fNuYMzTPIbUE2XpvTa9nANANcvE(CategoryButton r0, boolean r1, TLRPC.Document r2) {
            r0.lambda$set$0(r1, r2);
        }

        /* renamed from: $r8$lambda$qzFFO-IgxuCCCl_FI-zxOIFb6sk, reason: not valid java name */
        public static /* synthetic */ void m13062$r8$lambda$qzFFOIgxuCCCl_FIzxOIFb6sk(CategoryButton r0, ValueAnimator r1) {
            r0.lambda$setSelected$3(r1);
        }

        /* renamed from: -$$Nest$fgetselectedAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ValueAnimator m13063$$Nest$fgetselectedAnimator(CategoryButton r0) {
            return r0.selectedAnimator;
        }

        /* renamed from: -$$Nest$fputselectedAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13064$$Nest$fputselectedAnimator(CategoryButton r0, ValueAnimator r1) {
            r0.selectedAnimator = r1;
        }

        /* renamed from: -$$Nest$mupdateSelectedT, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13065$$Nest$mupdateSelectedT(CategoryButton r0, float r1) {
            r0.updateSelectedT(r1);
        }

        public CategoryButton(StickerCategoriesListView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.loaded = false;
            this.loadProgress = 1.0f;
            if (r1.isGlassDesign == false) goto L5;
            int r22 = StickerCategoriesListView.m13060$$Nest$mgetGlassIconColor(r1, 0.4f);
        L6:
            setImageColor(r22);
            setScaleType(ImageView.ScaleType.CENTER);
            setLayerNum(r1.layerNum);
            return;
        L5:
            r22 = r1.getThemedColor(Theme.key_chat_emojiPanelIcon);
            goto L6
        }

        private /* synthetic */ void lambda$onLoaded$2(ValueAnimator r1) {
            this.loadProgress = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
        }

        private /* synthetic */ void lambda$set$0(boolean r1, TLRPC.Document r2) {
            setOnlyLastFrame(!r1);
            setAnimation(r2, 24, 24);
            playAnimation();
        }

        private /* synthetic */ void lambda$set$1() {
            if (this.loaded == true) goto L6;
            this.loadProgress = 0.0f;
            return;
        }

        private /* synthetic */ void lambda$setPressed$4(ValueAnimator r1) {
            this.pressedProgress = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
        }

        private /* synthetic */ void lambda$setSelected$3(ValueAnimator r1) {
            updateSelectedT(((Float) r1.getAnimatedValue()).floatValue());
        }

        private void updateSelectedT(float r4) {
            this.selectedT = r4;
            StickerCategoriesListView r0 = this.this$0;
            if (r0.isGlassDesign == false) goto L5;
            setImageColor(StickerCategoriesListView.m13060$$Nest$mgetGlassIconColor(r0, AndroidUtilities.lerp(0.4f, 0.8f, r4)));
        L6:
            invalidate();
            return;
        L5:
            setImageColor(ColorUtils.blendARGB(r0.getThemedColor(Theme.key_chat_emojiPanelIcon), this.this$0.getThemedColor(Theme.key_chat_emojiPanelIconSelected), this.selectedT));
            goto L6
        }

        @Override // android.view.View
        public void draw(Canvas r6) {
            updatePressedProgress();
            float r0 = getScale();
            if (r0 == 1.0f) goto L5;
            r6.save();
            r6.scale(r0, r0, getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f);
        L5:
            super.draw(r6);
            if (r0 == 1.0f) goto L9;
            r6.restore();
            return;
        }

        public float getScale() {
            return (((1.0f - this.pressedProgress) * 0.15f) + 0.85f) * this.loadProgress;
        }

        @Override // org.telegram.ui.Components.RLottieImageView
        public void onLoaded() {
            this.loaded = true;
            if (this.loadProgress >= 1.0f) goto L9;
            ValueAnimator r1 = this.loadAnimator;
            if (r1 == null) goto L7;
            r1.cancel();
            this.loadAnimator = null;
        L7:
            ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{this.loadProgress, 1.0f});
            this.loadAnimator = r0;
            r0.addUpdateListener(new StickerCategoriesListView$CategoryButton$$ExternalSyntheticLambda4(this));
            this.loadAnimator.addListener(new AnonymousClass1(this));
            this.loadAnimator.setDuration(320);
            this.loadAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.loadAnimator.start();
            return;
        }

        @Override // android.widget.ImageView, android.view.View
        public void onMeasure(int r2, int r3) {
            int r22 = View.MeasureSpec.getSize(r3);
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r22 - AndroidUtilities.dp(4.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r22, TLObject.FLAG_30));
        }

        public void play(boolean r5) {
            if ((System.currentTimeMillis() - this.lastPlayed) > 250) goto L5;
            if (r5 == true) goto L5;
            return;
        L5:
            this.lastPlayed = System.currentTimeMillis();
            RLottieDrawable r52 = getAnimatedDrawable();
            if (r52 == null) goto L8;
        L10:
            if (r52 == null) goto L13;
            r52.stop();
            r52.setCurrentFrame(0);
            r52.restart(true);
            return;
        L13:
            if (r52 != null) goto L17;
            setProgress(0.0f);
            playAnimation();
            return;
        L17:
            return;
        L8:
            if (getImageReceiver() == null) goto L10;
            r52 = getImageReceiver().getLottieAnimation();
            goto L10
        }

        public void set(EmojiCategory r5, int r6, boolean r7) {
            this.index = r6;
            if (TextUtils.isEmpty(r5.title) == true) goto L6;
            setContentDescription(r5.title);
        L9:
            ValueAnimator r62 = this.loadAnimator;
            if (r62 == null) goto L13;
            r62.cancel();
            this.loadAnimator = null;
        L13:
            if (r5.remote == false) goto L16;
            setImageResource(0);
            clearAnimationDrawable();
            boolean r63 = this.this$0.isTabIconsAnimationEnabled(true);
            this.loaded = false;
            this.loadProgress = 1.0f;
            AnimatedEmojiDrawable.getDocumentFetcher(UserConfig.selectedAccount).fetchDocument(r5.documentId, new StickerCategoriesListView$CategoryButton$$ExternalSyntheticLambda1(this, r63));
            AndroidUtilities.runOnUIThread(new StickerCategoriesListView$CategoryButton$$ExternalSyntheticLambda2(this), 60);
        L19:
            setSelected(r7, false);
            return;
        L16:
            if (r5.animated == false) goto L18;
            this.cached = false;
            setImageResource(0);
            setAnimation(r5.iconResId, 24, 24);
            playAnimation();
            this.loadProgress = 1.0f;
            goto L19
        L18:
            clearAnimationDrawable();
            setImageResource(r5.iconResId);
            this.loadProgress = 1.0f;
            goto L19
        L6:
            if (TextUtils.isEmpty(r5.emojis) == true) goto L8;
            setContentDescription(r5.emojis);
            goto L9
        L8:
            setContentDescription(null);
            goto L9
        }

        public void setImageColor(int r3) {
            if (this.imageColor == r3) goto L6;
            this.imageColor = r3;
            setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN));
            return;
        }

        @Override // android.view.View
        public void setPressed(boolean r4) {
            if (isPressed() == r4) goto L14;
            super.setPressed(r4);
            invalidate();
            this.this$0.invalidate();
            if (r4 == false) goto L9;
            ValueAnimator r0 = this.backAnimator;
            if (r0 == null) goto L9;
            r0.removeAllListeners();
            this.backAnimator.cancel();
        L9:
            if (r4 == true) goto L15;
            float r42 = this.pressedProgress;
            if (r42 == 0.0f) goto L16;
            ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r42, 0.0f});
            this.backAnimator = r43;
            r43.addUpdateListener(new StickerCategoriesListView$CategoryButton$$ExternalSyntheticLambda0(this));
            this.backAnimator.addListener(new AnonymousClass3(this));
            this.backAnimator.setInterpolator(new OvershootInterpolator(3.0f));
            this.backAnimator.setDuration(350);
            this.backAnimator.start();
            return;
        L16:
            return;
        L15:
            return;
        }

        public void setSelected(boolean r3, boolean r4) {
            if (Math.abs(this.selectedT - (r3 ? 1.0f : 0.0f)) <= 0.01f) goto L18;
            ValueAnimator r0 = this.selectedAnimator;
            if (r0 == null) goto L7;
            r0.cancel();
            this.selectedAnimator = null;
        L7:
            float r02 = 0.0f;
            if (r4 == false) goto L14;
            float r42 = this.selectedT;
            if (r3 == false) goto L12;
            r02 = 1.0f;
        L12:
            ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{r42, r02});
            this.selectedAnimator = r32;
            r32.addUpdateListener(new StickerCategoriesListView$CategoryButton$$ExternalSyntheticLambda3(this));
            this.selectedAnimator.addListener(new AnonymousClass2(this));
            this.selectedAnimator.setDuration(350);
            this.selectedAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.selectedAnimator.start();
            return;
        L14:
            if (r3 == false) goto L16;
            r02 = 1.0f;
        L16:
            updateSelectedT(r02);
            return;
        }

        public void updatePressedProgress() {
            if (isPressed() == false) goto L8;
            float r0 = this.pressedProgress;
            if (r0 == 1.0f) goto L9;
            this.pressedProgress = Utilities.clamp(r0 + ((1000.0f / AndroidUtilities.screenRefreshRate) / 100.0f), 1.0f, 0.0f);
            invalidate();
            this.this$0.invalidate();
            return;
        L9:
            return;
        }
    }

    /* loaded from: classes7.dex */
    public static class EmojiCategory {
        public boolean animated;
        public long documentId;
        public String emojis;
        public boolean greeting;
        public int iconResId;
        public boolean premium;
        public boolean remote;
        public String title;

        public EmojiCategory() {
        }

        public static EmojiCategory remote(TLRPC.EmojiGroup r4) {
            EmojiCategory r0 = new EmojiCategory();
            r0.remote = true;
            r0.documentId = r4.icon_emoji_id;
            if ((r4 instanceof TLRPC.TL_emojiGroupPremium) == false) goto L5;
            r0.emojis = "premium";
            r0.premium = true;
        L6:
            r0.greeting = r4 instanceof TLRPC.TL_emojiGroupGreeting;
            r0.title = r4.title;
            return r0;
        L5:
            r0.emojis = TextUtils.concat((CharSequence[]) r4.emoticons.toArray(new String[0])).toString();
            goto L6
        }
    }

    public static class EmojiGroupFetcher extends CacheFetcher<Integer, TLRPC.TL_messages_emojiGroups> {
        public static /* synthetic */ void $r8$lambda$ItTxN5yQgcyJ0ciauXDJGTpsOXk(int r2, TLRPC.TL_messages_emojiGroups r3, Integer r4) {
            SQLiteDatabase r22 = MessagesStorage.getInstance(r2).getDatabase();     // Catch: Exception -> L10
            if (r22 == null) goto L14;
            if (r3 != null) goto L8;
            r22.executeFast("DELETE FROM emoji_groups WHERE type = " + r4).stepThis().dispose();     // Catch: Exception -> L10
            return;
        L8:
            SQLitePreparedStatement r23 = r22.executeFast("REPLACE INTO emoji_groups VALUES(?, ?)");     // Catch: Exception -> L10
            r23.requery();     // Catch: Exception -> L10
            NativeByteBuffer r0 = new NativeByteBuffer(r3.getObjectSize());     // Catch: Exception -> L10
            r3.serializeToStream(r0);     // Catch: Exception -> L10
            r23.bindInteger(1, r4.intValue());     // Catch: Exception -> L10
            r23.bindByteBuffer(2, r0);     // Catch: Exception -> L10
            r23.step();     // Catch: Exception -> L10
            r0.reuse();     // Catch: Exception -> L10
            r23.dispose();     // Catch: Exception -> L10
            return;
        L14:
            return;
        L10:
            e = move-exception;
            FileLog.e(e);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void $r8$lambda$d9VgXuQKrOQ1qkFLkDr7Mpark10(int r4, Integer r5, Utilities.Callback2 r6) {
            SQLiteCursor r1 = null;
            SQLiteDatabase r42 = MessagesStorage.getInstance(r4).getDatabase();     // Catch: Throwable -> L21 Exception -> L23
            if (r42 == null) goto L25;
            SQLiteCursor r43 = r42.queryFinalized("SELECT data FROM emoji_groups WHERE type = ?", new Object[]{r5});     // Catch: Throwable -> L21 Exception -> L23
        L13:
            e = e;
        L28:
            FileLog.e(e);     // Catch: Throwable -> L11
            r6.run(0L, null);     // Catch: Throwable -> L11
            if (r43 == null) goto L39;
            r43.dispose();
            return;
        L39:
            return;
        L7:
            if (r43.next() == false) goto L15;
            NativeByteBuffer r2 = r43.byteBufferValue(0);     // Catch: Throwable -> L11 Exception -> L13
            if (r2 == null) goto L15;
            TLRPC.messages_EmojiGroups r52 = TLRPC.messages_EmojiGroups.TLdeserialize(r2, r2.readInt32(false), true);     // Catch: Throwable -> L11 Exception -> L13
            r2.reuse();     // Catch: Throwable -> L11 Exception -> L13
        L17:
            if ((r52 instanceof TLRPC.TL_messages_emojiGroups) == true) goto L19;
            r6.run(0L, null);     // Catch: Throwable -> L11 Exception -> L13
        L20:
            r1 = r43;
            goto L25
        L19:
            r6.run(Long.valueOf(r5.hash), (TLRPC.TL_messages_emojiGroups) r52);     // Catch: Throwable -> L11 Exception -> L13
        L15:
            r52 = null;
            goto L17
        L25:
            if (r1 == null) goto L38;
            r1.dispose();
            return;
        L38:
            return;
        L11:
            th = th;
            r1 = r4;
        L32:
            if (r1 == null) goto L34;
            r1.dispose();
        L34:
            throw th;
        L23:
            e = e;
            r43 = null;
        L21:
            th = th;
            goto L32
        }

        public static /* synthetic */ void $r8$lambda$hoVrwIB0C7JCXIxZyF899CAsQ48(Utilities.Callback4 r2, TLObject r3, TLRPC.TL_error r4) {
            if ((r3 instanceof TLRPC.TL_messages_emojiGroupsNotModified) == false) goto L7;
            Boolean r32 = Boolean.TRUE;
            r2.run(r32, null, 0L, r32);
            return;
        L7:
            if ((r3 instanceof TLRPC.TL_messages_emojiGroups) == false) goto L10;
            r2.run(Boolean.FALSE, (TLRPC.TL_messages_emojiGroups) r3, Long.valueOf(r3.hash), Boolean.TRUE);
            return;
        L10:
            r2.run(Boolean.FALSE, null, 0L, Boolean.TRUE);
        }

        private EmojiGroupFetcher() {
        }

        public /* synthetic */ EmojiGroupFetcher(StickerCategoriesListViewIA r1) {
            this();
        }

        /* renamed from: getLocal, reason: avoid collision after fix types in other method */
        public void getLocal2(int r2, Integer r3, Utilities.Callback2<Long, TLRPC.TL_messages_emojiGroups> r4) {
            MessagesStorage.getInstance(r2).getStorageQueue().postRunnable(new StickerCategoriesListView$EmojiGroupFetcher$$ExternalSyntheticLambda2(r2, r3, r4));
        }

        @Override // org.telegram.messenger.CacheFetcher
        public /* bridge */ /* synthetic */ void getLocal(int r1, Integer r2, Utilities.Callback2<Long, TLRPC.TL_messages_emojiGroups> r3) {
            getLocal2(r1, r2, r3);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: getRemote, reason: avoid collision after fix types in other method */
        public void getRemote2(int r2, Integer r3, long r4, Utilities.Callback4<Boolean, TLRPC.TL_messages_emojiGroups, Long, Boolean> r6) {
            if (r3.intValue() != 1) goto L6;
            TLRPC.TL_messages_getEmojiStatusGroups r1 = new TLRPC.TL_messages_getEmojiStatusGroups();
            r1.hash = (int) r4;
            TLRPC.TL_messages_getEmojiGroups r12 = r1;
        L12:
            ConnectionsManager.getInstance(r2).sendRequest(r12, new StickerCategoriesListView$EmojiGroupFetcher$$ExternalSyntheticLambda1(r6));
            return;
        L6:
            if (r3.intValue() != 2) goto L9;
            TLRPC.TL_messages_getEmojiProfilePhotoGroups r13 = new TLRPC.TL_messages_getEmojiProfilePhotoGroups();
            r13.hash = (int) r4;
            r12 = r13;
            goto L12
        L9:
            if (r3.intValue() != 3) goto L11;
            TLRPC.TL_messages_getEmojiStickerGroups r14 = new TLRPC.TL_messages_getEmojiStickerGroups();
            r14.hash = (int) r4;
            r12 = r14;
            goto L12
        L11:
            TLRPC.TL_messages_getEmojiGroups r15 = new TLRPC.TL_messages_getEmojiGroups();
            r15.hash = (int) r4;
            r12 = r15;
            goto L12
        }

        @Override // org.telegram.messenger.CacheFetcher
        public /* bridge */ /* synthetic */ void getRemote(int r1, Integer r2, long r3, Utilities.Callback4<Boolean, TLRPC.TL_messages_emojiGroups, Long, Boolean> r5) {
            getRemote2(r1, r2, r3, r5);
        }

        /* renamed from: setLocal, reason: avoid collision after fix types in other method */
        public void setLocal2(int r1, Integer r2, TLRPC.TL_messages_emojiGroups r3, long r4) {
            MessagesStorage.getInstance(r1).getStorageQueue().postRunnable(new StickerCategoriesListView$EmojiGroupFetcher$$ExternalSyntheticLambda0(r1, r3, r2));
        }

        @Override // org.telegram.messenger.CacheFetcher
        public /* bridge */ /* synthetic */ void setLocal(int r1, Integer r2, TLRPC.TL_messages_emojiGroups r3, long r4) {
            setLocal2(r1, r2, r3, r4);
        }
    }

    public static class EmojiSearch extends CacheFetcher<String, TLRPC.TL_emojiList> {
        /* renamed from: $r8$lambda$S8qTPEYrUlxmW8suBr-CSOYi_S0, reason: not valid java name */
        public static /* synthetic */ void m13066$r8$lambda$S8qTPEYrUlxmW8suBrCSOYi_S0(Utilities.Callback4 r2, TLObject r3, TLRPC.TL_error r4) {
            if ((r3 instanceof TLRPC.TL_emojiListNotModified) == false) goto L7;
            Boolean r32 = Boolean.TRUE;
            r2.run(r32, null, 0L, r32);
            return;
        L7:
            if ((r3 instanceof TLRPC.TL_emojiList) == false) goto L10;
            TLRPC.TL_emojiList r33 = (TLRPC.TL_emojiList) r3;
            r2.run(Boolean.FALSE, r33, Long.valueOf(r33.hash), Boolean.TRUE);
            return;
        L10:
            r2.run(Boolean.FALSE, null, 0L, Boolean.TRUE);
        }

        private EmojiSearch() {
        }

        public /* synthetic */ EmojiSearch(StickerCategoriesListViewIA r1) {
            this();
        }

        @Override // org.telegram.messenger.CacheFetcher
        public /* bridge */ /* synthetic */ void getRemote(int r1, String r2, long r3, Utilities.Callback4<Boolean, TLRPC.TL_emojiList, Long, Boolean> r5) {
            getRemote2(r1, r2, r3, r5);
        }

        /* renamed from: getRemote, reason: avoid collision after fix types in other method */
        public void getRemote2(int r1, String r2, long r3, Utilities.Callback4<Boolean, TLRPC.TL_emojiList, Long, Boolean> r5) {
            TLRPC.TL_messages_searchCustomEmoji r0 = new TLRPC.TL_messages_searchCustomEmoji();
            r0.emoticon = r2;
            r0.hash = r3;
            ConnectionsManager.getInstance(r1).sendRequest(r0, new StickerCategoriesListView$EmojiSearch$$ExternalSyntheticLambda0(r5));
        }
    }

    public static /* synthetic */ void $r8$lambda$GUcuwSya8NetiFUSbLXIncy9mzw(StickerCategoriesListView r0, int r1) {
        r0.lambda$scrollToSelected$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$XakrOCr4ECRNAxv8ph6efx4LSUQ(StickerCategoriesListView r0, EmojiCategory[] r1, long r2, TLRPC.TL_messages_emojiGroups r4) {
        r0.lambda$new$3(r1, r2, r4);
    }

    /* renamed from: $r8$lambda$ZDQ_VKiRHbB-Vs7puOYEMcax-ZQ, reason: not valid java name */
    public static /* synthetic */ void m13048$r8$lambda$ZDQ_VKiRHbBVs7puOYEMcaxZQ(int r6, TLRPC.TL_messages_emojiGroups r7) {
        if (r7 == null) goto L9;
        ArrayList<TLRPC.EmojiGroup> r72 = r7.groups;
        if (r72 == null) goto L11;
        int r0 = r72.size();
        int r1 = 0;
    L7:
        if (r1 >= r0) goto L12;
        TLRPC.EmojiGroup r2 = r72.get(r1);
        r1 = r1 + 1;
        AnimatedEmojiDrawable.getDocumentFetcher(r6).fetchDocument(r2.icon_emoji_id, null);
        goto L7
    L12:
        return;
    L11:
        return;
    }

    public static /* synthetic */ void $r8$lambda$i64CMdbnaa5wt6SC5Y6RRFimNiQ(StickerCategoriesListView r0, View r1, int r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$kAz_4FH1siPaK5NYbTi_hjqP1Bw(StickerCategoriesListView r0, ValueAnimator r1) {
        r0.lambda$updateCategoriesShown$5(r1);
    }

    /* renamed from: $r8$lambda$zAffNpcQxMQaK40tEAla4-DVSqo, reason: not valid java name */
    public static /* synthetic */ void m13049$r8$lambda$zAffNpcQxMQaK40tEAla4DVSqo(StickerCategoriesListView r0, EmojiCategory[] r1, TLRPC.TL_messages_emojiGroups r2, long r3) {
        r0.lambda$new$2(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetcategories, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiCategory[] m13050$$Nest$fgetcategories(StickerCategoriesListView r0) {
        return r0.categories;
    }

    /* renamed from: -$$Nest$fgetcategoriesShownAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m13051$$Nest$fgetcategoriesShownAnimator(StickerCategoriesListView r0) {
        return r0.categoriesShownAnimator;
    }

    /* renamed from: -$$Nest$fgetcategoriesShownT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13052$$Nest$fgetcategoriesShownT(StickerCategoriesListView r0) {
        return r0.categoriesShownT;
    }

    /* renamed from: -$$Nest$fgetdontOccupyWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13053$$Nest$fgetdontOccupyWidth(StickerCategoriesListView r0) {
        return r0.dontOccupyWidth;
    }

    /* renamed from: -$$Nest$fgetpaddingView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m13054$$Nest$fgetpaddingView(StickerCategoriesListView r0) {
        return r0.paddingView;
    }

    /* renamed from: -$$Nest$fgetselectedCategoryIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13055$$Nest$fgetselectedCategoryIndex(StickerCategoriesListView r0) {
        return r0.selectedCategoryIndex;
    }

    /* renamed from: -$$Nest$fgetshownButtonsAtStart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13056$$Nest$fgetshownButtonsAtStart(StickerCategoriesListView r0) {
        return r0.shownButtonsAtStart;
    }

    /* renamed from: -$$Nest$fputcategoriesShownAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13057$$Nest$fputcategoriesShownAnimator(StickerCategoriesListView r0, ValueAnimator r1) {
        r0.categoriesShownAnimator = r1;
    }

    /* renamed from: -$$Nest$fputpaddingView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13058$$Nest$fputpaddingView(StickerCategoriesListView r0, View r1) {
        r0.paddingView = r1;
    }

    /* renamed from: -$$Nest$fputpaddingWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13059$$Nest$fputpaddingWidth(StickerCategoriesListView r0, int r1) {
        r0.paddingWidth = r1;
    }

    /* renamed from: -$$Nest$mgetGlassIconColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13060$$Nest$mgetGlassIconColor(StickerCategoriesListView r0, float r1) {
        return r0.getGlassIconColor(r1);
    }

    /* renamed from: -$$Nest$msetCategoriesShownT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13061$$Nest$msetCategoriesShownT(StickerCategoriesListView r0, float r1) {
        r0.setCategoriesShownT(r1);
    }

    static {
        fetcher = new EmojiGroupFetcher(null);
        search = new EmojiSearch(null);
        loadedIconsType = new HashSet();
        loadedCategoryIcons = 0;
    }

    public StickerCategoriesListView(Context r2, int r3, Theme.ResourcesProvider r4) {
        this(r2, null, r3, r4);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public StickerCategoriesListView(Context r6, EmojiCategory[] r7, int r8, Theme.ResourcesProvider r9) {
        super(r6, r9);
        this.shownButtonsAtStart = 6.5f;
        this.categories = null;
        CubicBezierInterpolator r1 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.leftBoundAlpha = new AnimatedFloat(this, 360, r1);
        this.rightBoundAlpha = new AnimatedFloat(this, 360, r1);
        this.selectedPaint = new Paint(1);
        this.selectedCategoryIndex = -1;
        this.categoriesShownT = 0.0f;
        this.categoriesShouldShow = true;
        this.selectedAlpha = new AnimatedFloat(this, 350, r1);
        this.selectedIndex = new AnimatedFloat(this, 350, r1);
        this.rect1 = new RectF();
        this.rect2 = new RectF();
        this.rect3 = new RectF();
        this.categoriesType = r8;
        setPadding(0, 0, AndroidUtilities.dp(2.0f), 0);
        Adapter r0 = new Adapter(this, null);
        this.adapter = r0;
        setAdapter(r0);
        LinearLayoutManager r92 = new LinearLayoutManager(r6);
        this.layoutManager = r92;
        setLayoutManager(r92);
        this.layoutManager.setOrientation(0);
        setSelectorRadius(AndroidUtilities.dp(15.0f));
        setSelectorType(1);
        int r62 = Theme.key_listSelector;
        setSelectorDrawableColor(getThemedColor(r62));
        this.selectedPaint.setColor(getThemedColor(r62));
        setSelectorRadius(0);
        setSelectorType(100);
        setSelectorDrawableColor(0);
        setWillNotDraw(false);
        setOnItemClickListener(new StickerCategoriesListView$$ExternalSyntheticLambda1(this));
        long r02 = System.currentTimeMillis();
        fetcher.fetch(UserConfig.selectedAccount, Integer.valueOf(r8), new StickerCategoriesListView$$ExternalSyntheticLambda2(this, r7, r02));
    }

    private void drawSelectedHighlight(Canvas r10) {
        AnimatedFloat r0 = this.selectedAlpha;
        if (this.selectedCategoryIndex < 0) goto L5;
        float r1 = 1.0f;
    L6:
        float r02 = r0.set(r1);
        int r12 = this.selectedCategoryIndex;
        AnimatedFloat r4 = this.selectedIndex;
        if (r12 < 0) goto L9;
        float r13 = r4.set(r12);
    L11:
        if (r02 > 0.0f) goto L13;
        return;
    L13:
        float r14 = r13 + 1.0f;
        double r2 = r14;
        int r42 = Math.max(1, (int) Math.floor(r2));
        int r22 = Math.max(1, (int) Math.ceil(r2));
        View r3 = null;
        int r6 = 0;
        View r5 = null;
    L15:
        if (r6 >= getChildCount()) goto L25;
        View r7 = getChildAt(r6);
        int r8 = getChildAdapterPosition(r7);
        if (r8 != r42) goto L19;
        r3 = r7;
    L19:
        if (r8 != r22) goto L21;
        r5 = r7;
    L21:
        if (r3 == null) goto L24;
        if (r5 != null) goto L25;
    L24:
        r6 = r6 + 1;
    L25:
        int r62 = this.selectedPaint.getAlpha();
        this.selectedPaint.setAlpha((int) (r62 * r02));
        if (r3 == null) goto L32;
        if (r5 == null) goto L32;
        if (r42 != r22) goto L30;
        float r03 = 0.5f;
    L31:
        getChildBounds(r3, this.rect1);
        getChildBounds(r5, this.rect2);
        AndroidUtilities.lerp(this.rect1, this.rect2, r03, this.rect3);
        r10.drawRoundRect(this.rect3, AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f), this.selectedPaint);
        goto L32
    L30:
        r03 = (r14 - r42) / (r22 - r42);
    L32:
        this.selectedPaint.setAlpha(r62);
        return;
    L9:
        r13 = r4.get();
        goto L11
    L5:
        r1 = 0.0f;
        goto L6
    }

    private void getChildBounds(View r5, RectF r6) {
        float r4 = (r5.getRight() + r5.getLeft()) / 2.0f;
        float r1 = (r5.getBottom() + r5.getTop()) / 2.0f;
        float r0 = 1.0f;
        float r2 = (r5.getWidth() / 2.0f) - AndroidUtilities.dp(1.0f);
        if ((r5 instanceof CategoryButton) == false) goto L5;
        r0 = ((CategoryButton) r5).getScale();
    L5:
        float r22 = r2 * r0;
        r6.set(r4 - r22, r1 - r22, r4 + r22, r1 + r22);
    }

    private int getGlassIconColor(float r2) {
        return ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_glass_defaultIcon, this.resourcesProvider), (int) (r2 * 255.0f));
    }

    private int getScrollToStartWidth() {
        if (getChildCount() <= 0) goto L10;
        View r0 = getChildAt(0);
        if ((r0 instanceof CategoryButton) == false) goto L9;
        return (this.paddingWidth + Math.max(0, (getChildAdapterPosition(r0) - 1) * getHeight())) + (-r0.getLeft());
    L9:
        return -r0.getLeft();
    L10:
        return 0;
    }

    private /* synthetic */ void lambda$new$1(View r1, int r2) {
        onItemClick(r2, r1);
    }

    private /* synthetic */ void lambda$new$2(EmojiCategory[] r6, TLRPC.TL_messages_emojiGroups r7, long r8) {
        boolean r0 = false;
        if (r6 != null) goto L5;
        int r1 = 0;
    L6:
        this.categories = new EmojiCategory[r1 + r7.groups.size()];
        int r12 = 0;
        if (r6 != null) goto L9;
    L11:
        int r62 = 0;
    L12:
        int r2 = r7.groups.size();
        EmojiCategory[] r3 = this.categories;
        if (r62 >= r2) goto L15;
        r3[r12 + r62] = EmojiCategory.remote(r7.groups.get(r62));
        r62 = r62 + 1;
        goto L12
    L15:
        this.categories = preprocessCategories(r3);
        this.adapter.notifyDataSetChanged();
        setCategoriesShownT(0.0f);
        boolean r63 = this.categoriesShouldShow;
        if ((System.currentTimeMillis() - r8) <= 16) goto L18;
        r0 = true;
    L18:
        updateCategoriesShown(r63, r0);
        return;
    L9:
        if (r12 >= r6.length) goto L11;
        this.categories[r12] = r6[r12];
        r12 = r12 + 1;
        goto L9
    L5:
        r1 = r6.length;
        goto L6
    }

    private /* synthetic */ void lambda$new$3(EmojiCategory[] r7, long r8, TLRPC.TL_messages_emojiGroups r10) {
        if (r10 == null) goto L5;
        StickerCategoriesListView$$ExternalSyntheticLambda4 r0 = new StickerCategoriesListView$$ExternalSyntheticLambda4(this, r7, r10, r8);
        NotificationCenter.getInstance(UserConfig.selectedAccount).doOnIdle(r0);
        return;
    }

    private /* synthetic */ void lambda$scrollToSelected$4(int r2) {
        onScrolled(r2, 0);
    }

    private /* synthetic */ void lambda$updateCategoriesShown$5(ValueAnimator r1) {
        setCategoriesShownT(((Float) r1.getAnimatedValue()).floatValue());
    }

    private void onItemClick(int r4, View r5) {
        if (r4 < 1) goto L19;
        EmojiCategory[] r1 = this.categories;
        if (r1 == null) goto L20;
        EmojiCategory r42 = r1[r4 - 1];
        int r0 = AndroidUtilities.dp(64.0f);
        if ((getMeasuredWidth() - r5.getRight()) >= r0) goto L12;
        smoothScrollBy(r0 - (getMeasuredWidth() - r5.getRight()), 0, CubicBezierInterpolator.EASE_OUT_QUINT);
    L14:
        Utilities.Callback<EmojiCategory> r3 = this.onCategoryClick;
        if (r3 == null) goto L18;
        r3.run(r42);
        return;
    L18:
        return;
    L12:
        if (r5.getLeft() >= r0) goto L14;
        smoothScrollBy(-(r0 - r5.getLeft()), 0, CubicBezierInterpolator.EASE_OUT_QUINT);
        goto L14
    L20:
        return;
    }

    public static void preload(int r2, int r3) {
        fetcher.fetch(r2, Integer.valueOf(r3), new StickerCategoriesListView$$ExternalSyntheticLambda0(r2));
    }

    private void setCategoriesShownT(float r7) {
        this.categoriesShownT = r7;
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L14;
        View r2 = getChildAt(r1);
        if ((r2 instanceof CategoryButton) == false) goto L13;
        int r3 = getChildAdapterPosition(r2);
        float r32 = AndroidUtilities.cascade(r7, (getChildCount() - 1) - r3, getChildCount() - 1, 3.0f);
        if (r32 > 0.0f) goto L10;
    L12:
        r2.setAlpha(r32);
        r2.setScaleX(r32);
        r2.setScaleY(r32);
        goto L13
    L10:
        if (r2.getAlpha() > 0.0f) goto L12;
        ((CategoryButton) r2).play(false);
    L13:
        r1 = r1 + 1;
        goto L4
    L14:
        invalidate();
    }

    @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r3) {
        if (r3.getAction() != 0) goto L11;
        View r0 = findChildViewUnder(r3.getX(), r3.getY());
        if ((r0 instanceof CategoryButton) == true) goto L7;
        return false;
    L7:
        if (r0.getAlpha() >= 0.5f) goto L11;
        return false;
    L11:
        return super.dispatchTouchEvent(r3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void draw(Canvas r11) {
        float r1 = 1.0f;
        if (this.backgroundPaint == null) goto L17;
        int r0 = Integer.MAX_VALUE;
        int r3 = Integer.MIN_VALUE;
        int r4 = 0;
    L6:
        if (r4 >= getChildCount()) goto L11;
        View r5 = getChildAt(r4);
        if ((r5 instanceof CategoryButton) == false) goto L10;
        r0 = Math.min(r0, r5.getLeft());
        r3 = Math.max(r3, r5.getRight());
    L10:
        r4 = r4 + 1;
        goto L6
    L11:
        if (r0 >= r3) goto L17;
        int r02 = (int) (r0 + ((getWidth() + AndroidUtilities.dp(32.0f)) * (1.0f - this.categoriesShownT)));
        int r32 = (int) (r3 + ((getWidth() + AndroidUtilities.dp(32.0f)) * (1.0f - this.categoriesShownT)));
        Canvas r42 = r11;
        r42.drawRect(r02, 0.0f, r32, getHeight(), this.backgroundPaint);
        if (r32 >= getWidth()) goto L18;
        Drawable r112 = this.leftBoundDrawable;
        if (r112 == null) goto L18;
        r112.setAlpha(255);
        Drawable r113 = this.leftBoundDrawable;
        r113.setBounds(r32, 0, r113.getIntrinsicWidth() + r32, getHeight());
        this.leftBoundDrawable.draw(r42);
    L18:
        drawSelectedHighlight(r42);
        super.draw(r42);
        Drawable r114 = this.leftBoundDrawable;
        if (r114 == null) goto L33;
        AnimatedFloat r03 = this.leftBoundAlpha;
        if (canScrollHorizontally(-1) == true) goto L23;
    L25:
        r1 = 0.0f;
    L26:
        r114.setAlpha((int) ((255.0f * r03.set(r1)) * this.categoriesShownT));
        if (this.leftBoundDrawable.getAlpha() <= 0) goto L34;
        Drawable r115 = this.leftBoundDrawable;
        r115.setBounds(0, 0, r115.getIntrinsicWidth(), getHeight());
        this.leftBoundDrawable.draw(r42);
        return;
    L34:
        return;
    L23:
        if (this.scrolledFully == false) goto L25;
    L33:
        return;
    L17:
        r42 = r11;
        goto L18
    }

    public int getCategoryIndex() {
        return this.selectedCategoryIndex;
    }

    public EmojiCategory getSelectedCategory() {
        EmojiCategory[] r0 = this.categories;
        if (r0 == null) goto L11;
        int r2 = this.selectedCategoryIndex;
        if (r2 >= 0) goto L7;
        return null;
    L7:
        if (r2 < r0.length) goto L10;
        return null;
    L10:
        return r0[r2];
    L11:
        return null;
    }

    public boolean isCategoriesShown() {
        if (this.categoriesShownT <= 0.5f) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isScrolledIntoOccupiedWidth() {
        return this.scrolledIntoOccupiedWidth;
    }

    public abstract boolean isTabIconsAnimationEnabled(boolean r1);

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateCategoriesShown(this.categoriesShouldShow, false);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration r1) {
        super.onConfigurationChanged(r1);
        View r0 = this.paddingView;
        if (r0 == null) goto L6;
        r0.requestLayout();
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onScrolled(int r5, int r6) {
        super.onScrolled(r5, r6);
        int r62 = 0;
        if (getChildCount() <= 0) goto L12;
        View r52 = getChildAt(0);
        boolean r1 = true;
        if ((r52 instanceof CategoryButton) == false) goto L8;
        boolean r53 = true;
    L13:
        boolean r0 = this.scrolledIntoOccupiedWidth;
        if (r0 == r1) goto L21;
        this.scrolledIntoOccupiedWidth = r1;
        Utilities.Callback<Integer> r02 = this.onScrollIntoOccupiedWidth;
        if (r02 == null) goto L20;
        if (r1 == false) goto L19;
        r62 = Math.max(0, getScrollToStartWidth() - (this.paddingWidth - this.dontOccupyWidth));
    L19:
        r02.run(Integer.valueOf(r62));
    L20:
        invalidate();
    L26:
        if (this.scrolledFully == r53) goto L32;
        this.scrolledFully = r53;
        Utilities.Callback<Boolean> r63 = this.onScrollFully;
        if (r63 == null) goto L30;
        r63.run(Boolean.valueOf(r53));
    L30:
        invalidate();
        return;
    L32:
        return;
    L21:
        if (r0 == false) goto L26;
        Utilities.Callback<Integer> r03 = this.onScrollIntoOccupiedWidth;
        if (r03 == null) goto L26;
        r03.run(Integer.valueOf(Math.max(0, getScrollToStartWidth() - (this.paddingWidth - this.dontOccupyWidth))));
        goto L26
    L8:
        if (r52.getRight() <= this.dontOccupyWidth) goto L11;
        r1 = false;
    L11:
        r53 = false;
        goto L13
    L12:
        r53 = false;
        r1 = false;
        goto L13
    }

    public EmojiCategory[] preprocessCategories(EmojiCategory[] r1) {
        return r1;
    }

    public void scrollToSelected() {
        int r0 = ((-getScrollToStartWidth()) - Math.max(0, this.dontOccupyWidth)) + (this.selectedCategoryIndex * AndroidUtilities.dp(34.0f));
        scrollBy(r0, 0);
        post(new StickerCategoriesListView$$ExternalSyntheticLambda5(this, r0));
    }

    public void scrollToStart() {
        smoothScrollBy(-getScrollToStartWidth(), 0, CubicBezierInterpolator.EASE_OUT_QUINT);
    }

    public void selectCategory(int r6) {
        if (this.selectedCategoryIndex >= 0) goto L6;
        if (r6 < 0) goto L6;
        this.selectedIndex.set(r6, true);
    L6:
        this.selectedCategoryIndex = r6;
        int r0 = 0;
    L8:
        if (r0 >= getChildCount()) goto L17;
        View r2 = getChildAt(r0);
        if ((r2 instanceof CategoryButton) == false) goto L16;
        int r3 = getChildAdapterPosition(r2);
        CategoryButton r22 = (CategoryButton) r2;
        if (this.selectedCategoryIndex != (r3 - 1)) goto L14;
        boolean r32 = true;
    L15:
        r22.setSelected(r32, true);
        goto L16
    L14:
        r32 = false;
    L16:
        r0 = r0 + 1;
        goto L8
    L17:
        invalidate();
    }

    public void selectCategory(EmojiCategory r4) {
        if (this.categories == null) goto L11;
        int r0 = 0;
    L5:
        EmojiCategory[] r1 = this.categories;
        if (r0 >= r1.length) goto L11;
        if (r1[r0] == r4) goto L12;
        r0 = r0 + 1;
    L12:
        selectCategory(r0);
        return;
    L11:
        r0 = -1;
        goto L12
    }

    @Override // android.view.View
    public void setBackgroundColor(int r4) {
        if (this.backgroundPaint != null) goto L5;
        this.backgroundPaint = new Paint(1);
    L5:
        this.backgroundPaint.setColor(r4);
        Drawable r0 = getContext().getResources().getDrawable(R.drawable.gradient_right).mutate();
        this.leftBoundDrawable = r0;
        PorterDuff.Mode r2 = PorterDuff.Mode.MULTIPLY;
        r0.setColorFilter(new PorterDuffColorFilter(r4, r2));
        Drawable r02 = getContext().getResources().getDrawable(R.drawable.gradient_left).mutate();
        this.rightBoundDrawable = r02;
        r02.setColorFilter(new PorterDuffColorFilter(r4, r2));
    }

    public void setDontOccupyWidth(int r1) {
        this.dontOccupyWidth = r1;
    }

    public void setOnCategoryClick(Utilities.Callback<EmojiCategory> r1) {
        this.onCategoryClick = r1;
    }

    public void setOnScrollFully(Utilities.Callback<Boolean> r1) {
        this.onScrollFully = r1;
    }

    public void setOnScrollIntoOccupiedWidth(Utilities.Callback<Integer> r1) {
        this.onScrollIntoOccupiedWidth = r1;
    }

    public void setShownButtonsAtStart(float r1) {
        this.shownButtonsAtStart = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [int] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    public void updateCategoriesShown(boolean r5, boolean r6) {
        this.categoriesShouldShow = r5;
        ?? r52 = r5;
        if (this.categories != null) goto L6;
        r52 = 0;
    L6:
        if (this.categoriesShownT != ((float) r52)) goto L8;
        return;
    L8:
        ValueAnimator r0 = this.categoriesShownAnimator;
        if (r0 == null) goto L11;
        r0.cancel();
        this.categoriesShownAnimator = null;
    L11:
        float r02 = 0.0f;
        if (r6 == false) goto L22;
        float r62 = this.categoriesShownT;
        if (r52 == 0) goto L16;
        r02 = 1.0f;
    L16:
        ValueAnimator r53 = ValueAnimator.ofFloat(new float[]{r62, r02});
        this.categoriesShownAnimator = r53;
        r53.addUpdateListener(new StickerCategoriesListView$$ExternalSyntheticLambda3(this));
        this.categoriesShownAnimator.addListener(new AnonymousClass1(this));
        this.categoriesShownAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        ValueAnimator r54 = this.categoriesShownAnimator;
        EmojiCategory[] r63 = this.categories;
        if (r63 != null) goto L19;
        int r64 = 5;
    L20:
        r54.setDuration(r64 * 120);
        this.categoriesShownAnimator.start();
        return;
    L19:
        r64 = r63.length;
        goto L20
    L22:
        if (r52 == 0) goto L24;
        r02 = 1.0f;
    L24:
        setCategoriesShownT(r02);
    }
}
