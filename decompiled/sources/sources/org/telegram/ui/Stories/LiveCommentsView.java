package org.telegram.ui.Stories;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ReplacementSpan;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.CastStatusCodes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.GradientClip;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stars.StarsReactionsSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public abstract class LiveCommentsView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private final UniversalAdapter adapter;
    private boolean allowTouches;
    public final ImageView arrowButton;
    private Bulletin.UndoButton bulletinButton;
    private Bulletin.TwoLineAnimatedLottieLayout bulletinLayout;
    private boolean callHighlight;
    private Runnable closeBulletin;
    private ValueAnimator collapseAnimator;
    private boolean collapsed;
    private final int currentAccount;
    private long dialogId;
    private final GradientClip gradientClip;
    private boolean hasTopMessages;
    private long highlightingDialog;
    private int highlightingMessageId;
    private TLRPC.InputGroupCall inputCall;
    private float keyboardFinalOffset;
    private float keyboardOffset;
    private float keyboardT;
    private long lastMinStars;
    private int lastNow;
    private final LinearLayoutManager layoutManager;
    public final RecyclerListView listView;
    private LivePlayer livePlayer;
    private long localStars;
    public int maxReadId;
    private final ArrayList<Message> messages;
    private Runnable pollStarsRunnable;
    private boolean polling;
    private Runnable removeTopSendersRunnable;
    private boolean sentStars;
    private final View shadowView;
    private Bulletin starsBulletin;
    private final StoryViewer storyViewer;
    private Bulletin.TimerView timerView;
    private final UniversalAdapter topAdapter;
    private final FrameLayout topBulletinContainer;
    private ArrayList<TL_phone.groupCallDonor> topDonors;
    private final LinearLayoutManager topLayoutManager;
    public final RecyclerListView topListView;
    private final ArrayList<TopSender> topMessages;
    private final HashMap<Long, Integer> topPlaces;
    private long totalStars;
    private final Runnable updateAdapters;

    /* renamed from: org.telegram.ui.Stories.LiveCommentsView$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerListView {
        final /* synthetic */ LiveCommentsView this$0;

        public AnonymousClass1(LiveCommentsView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            int r0 = getMaxVisibleId();
            LiveCommentsView r1 = this.this$0;
            if (r0 <= r1.maxReadId) goto L5;
            r1.maxReadId = r0;
            r1.onMessagesCountUpdated();
        L5:
            super.dispatchDraw(r4);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (this.this$0.isCollapsed() == false) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r2);
        }

        public int getMaxVisibleId() {
            if (LiveCommentsView.m19347$$Nest$fgetcollapsed(this.this$0) == false) goto L5;
            return -1;
        L5:
            int r0 = 0;
        L7:
            if (r0 >= getChildCount()) goto L15;
            View r2 = getChildAt(r0);
            if ((r2 instanceof LiveCommentView) == false) goto L14;
            LiveCommentView r22 = (LiveCommentView) r2;
            if (LiveCommentView.m19355$$Nest$fgetmessage(r22) == null) goto L14;
            return LiveCommentView.m19355$$Nest$fgetmessage(r22).id;
        L14:
            r0 = r0 + 1;
            goto L7
        L15:
            return -1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public Integer getSelectorColor(int r1) {
            return 0;
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            this.this$0.invalidate();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r2, int r3) {
            int r0 = View.MeasureSpec.getSize(r2);
            View.MeasureSpec.getSize(r3);
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(r0, View.MeasureSpec.getMode(r3)));
        }
    }

    /* renamed from: org.telegram.ui.Stories.LiveCommentsView$2, reason: invalid class name */
    public class AnonymousClass2 extends UniversalAdapter {
        final /* synthetic */ LiveCommentsView this$0;

        public AnonymousClass2(LiveCommentsView r1, RecyclerListView r2, Context r3, int r4, int r5, boolean r6, Utilities.Callback2 r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // org.telegram.ui.Components.UniversalAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r2, int r3) {
            super.onBindViewHolder(r2, r3);
            if (LiveCommentsView.m19346$$Nest$fgetcallHighlight(this.this$0) == false) goto L12;
            View r22 = r2.itemView;
            if ((r22 instanceof LiveCommentView) == false) goto L13;
            LiveCommentView r23 = (LiveCommentView) r22;
            if (LiveCommentView.m19355$$Nest$fgetmessage(r23) != null) goto L9;
            return;
        L9:
            if (LiveCommentView.m19355$$Nest$fgetmessage(r23).id != LiveCommentsView.m19348$$Nest$fgethighlightingMessageId(this.this$0)) goto L15;
            r23.highlight();
            LiveCommentsView.m19350$$Nest$fputcallHighlight(this.this$0, false);
            return;
        L15:
            return;
        L13:
            return;
        }

        @Override // org.telegram.ui.Components.UniversalAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r3) {
            super.onViewAttachedToWindow(r3);
            if (LiveCommentsView.m19346$$Nest$fgetcallHighlight(this.this$0) == false) goto L12;
            View r32 = r3.itemView;
            if ((r32 instanceof LiveCommentView) == false) goto L13;
            LiveCommentView r33 = (LiveCommentView) r32;
            if (LiveCommentView.m19355$$Nest$fgetmessage(r33) != null) goto L9;
            return;
        L9:
            if (LiveCommentView.m19355$$Nest$fgetmessage(r33).id != LiveCommentsView.m19348$$Nest$fgethighlightingMessageId(this.this$0)) goto L15;
            r33.highlight();
            LiveCommentsView.m19350$$Nest$fputcallHighlight(this.this$0, false);
            return;
        L15:
            return;
        L13:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.LiveCommentsView$3, reason: invalid class name */
    public class AnonymousClass3 extends DefaultItemAnimator {
        final /* synthetic */ LiveCommentsView this$0;

        public AnonymousClass3(LiveCommentsView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public float animateByScale(View r1) {
            return 0.5f;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onAddAnimationUpdate(RecyclerView.ViewHolder r1) {
            super.onAddAnimationUpdate(r1);
            this.this$0.listView.invalidate();
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            super.onMoveAnimationUpdate(r1);
            this.this$0.listView.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Stories.LiveCommentsView$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerListView {
        final /* synthetic */ LiveCommentsView this$0;

        public AnonymousClass4(LiveCommentsView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public Integer getSelectorColor(int r1) {
            return 0;
        }
    }

    /* renamed from: org.telegram.ui.Stories.LiveCommentsView$5, reason: invalid class name */
    public class AnonymousClass5 extends DefaultItemAnimator {
        final /* synthetic */ LiveCommentsView this$0;

        public AnonymousClass5(LiveCommentsView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public float animateByScale(View r1) {
            return 0.5f;
        }
    }

    /* renamed from: org.telegram.ui.Stories.LiveCommentsView$6, reason: invalid class name */
    public class AnonymousClass6 extends DarkThemeResourceProvider {
        final /* synthetic */ LiveCommentsView this$0;

        public AnonymousClass6(LiveCommentsView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Stories.DarkThemeResourceProvider
        public void appendColors() {
            this.sparseIntArray.put(Theme.key_divider, 352321535);
        }
    }

    /* renamed from: org.telegram.ui.Stories.LiveCommentsView$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ LiveCommentsView this$0;
        final /* synthetic */ boolean val$collapsed;

        public AnonymousClass7(LiveCommentsView r1, boolean r2) {
            this.this$0 = r1;
            this.val$collapsed = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            RecyclerListView r32 = this.this$0.listView;
            float r1 = 0.0f;
            if (this.val$collapsed == false) goto L5;
            float r0 = 0.0f;
        L6:
            r32.setAlpha(r0);
            View r33 = LiveCommentsView.m19349$$Nest$fgetshadowView(this.this$0);
            if (this.val$collapsed == true) goto L10;
            r1 = 0.5f;
        L10:
            r33.setAlpha(r1);
            this.this$0.invalidate();
            return;
        L5:
            r0 = 1.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Stories.LiveCommentsView$8, reason: invalid class name */
    public class AnonymousClass8 extends DarkThemeResourceProvider {
        public AnonymousClass8() {
        }

        @Override // org.telegram.ui.Stories.DarkThemeResourceProvider
        public void appendColors() {
            this.sparseIntArray.append(Theme.key_dialogBackground, -14671840);
        }
    }

    public static class CrownDrawable extends Drawable {
        private final Drawable crown;
        private final float scale;
        private final Text text;

        public CrownDrawable(Context r3, int r4) {
            this.scale = 0.75f;
            this.crown = r3.getResources().getDrawable(R.drawable.filled_stream_crown).mutate();
            Text r32 = new Text(_UrlKt.FRAGMENT_ENCODE_SET + r4, 8.0f, AndroidUtilities.getTypeface("fonts/num.otf"));
            this.text = r32;
            r32.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r17) {
            Rect r1 = getBounds();
            r17.saveLayerAlpha(r1.left, r1.top, r1.right, r1.bottom, 255, 31);
            this.crown.setBounds(r1);
            this.crown.draw(r17);
            this.text.draw(r17, r1.centerX() - (this.text.getCurrentWidth() / 2.0f), r1.centerY() + AndroidUtilities.dp(0.15f), -1, this.crown.getAlpha() / 255.0f);
            r17.restore();
        }

        @Override // android.graphics.drawable.Drawable
        public int getAlpha() {
            return this.crown.getAlpha();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return (int) (this.crown.getIntrinsicHeight() * this.scale);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return (int) (this.crown.getIntrinsicWidth() * this.scale);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.crown.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            this.crown.setColorFilter(r1);
        }
    }

    public static class LiveCommentView extends FrameLayout implements ItemOptions.ScrimView {
        public final LinearLayout adminLayout;
        public final SpoilersTextView adminNameView;
        public final SpoilersTextView adminRoleView;
        public final AvatarDrawable avatarDrawable;
        public final BackupImageView avatarView;
        public Drawable background;
        private final Paint backgroundPaint;
        public float backgroundViewAlpha;
        private final int currentAccount;
        private boolean drawParticles;
        private boolean drawStar;
        private final boolean filled;
        private ValueAnimator highlightAnimator;
        private int highlightingMessageId;
        public final LinearLayout layout;
        private Message message;
        public final TextView smallStarsView;
        private final ColoredImageSpan[] smallStarsViewCache;
        public final TextView starsView;
        private final ColoredImageSpan[] starsViewCache;
        public CharSequence text;
        public final LinearLayout textLayout;
        public final SpoilersTextView textView;

        /* renamed from: org.telegram.ui.Stories.LiveCommentsView$LiveCommentView$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearLayout {
            Path clipPath;
            StarsReactionsSheet.Particles particles;
            final /* synthetic */ LiveCommentView this$0;

            public AnonymousClass1(LiveCommentView r1, Context r2) {
                this.this$0 = r1;
                super(r2);
                this.clipPath = new Path();
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r6) {
                if (LiveCommentView.m19352$$Nest$fgetdrawParticles(this.this$0) == false) goto L8;
                this.clipPath.rewind();
                RectF r0 = AndroidUtilities.rectTmp;
                r0.set(0.0f, 0.0f, getWidth(), getHeight());
                this.clipPath.addRoundRect(r0, AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), Path.Direction.CW);
                r6.save();
                r6.clipPath(this.clipPath);
                if (this.particles != null) goto L7;
                this.particles = new StarsReactionsSheet.Particles(1, 250);
            L7:
                this.particles.setBounds(0, 0, getWidth(), getHeight());
                this.particles.setSpeed(30.0f);
                this.particles.process();
                this.particles.draw(r6, -1, 0.85f);
                invalidate();
                r6.restore();
            L8:
                super.dispatchDraw(r6);
            }
        }

        /* renamed from: org.telegram.ui.Stories.LiveCommentsView$LiveCommentView$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ LiveCommentView this$0;

            public static /* synthetic */ void $r8$lambda$e8TdGtYQtXHKPV9fXaJsqs7mDO0(AnonymousClass2 r0, ValueAnimator r1) {
                r0.lambda$onAnimationEnd$0(r1);
            }

            public AnonymousClass2(LiveCommentView r1) {
                this.this$0 = r1;
            }

            private /* synthetic */ void lambda$onAnimationEnd$0(ValueAnimator r4) {
                float r42 = ((Float) r4.getAnimatedValue()).floatValue();
                LiveCommentView r0 = this.this$0;
                Drawable r1 = r0.background;
                if (r1 == null) goto L6;
                r1.setAlpha((int) (AndroidUtilities.lerp(1.0f, r0.backgroundViewAlpha, r42) * 255.0f));
                this.this$0.layout.invalidate();
                return;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                if (LiveCommentView.m19355$$Nest$fgetmessage(this.this$0) != null) goto L5;
                return;
            L5:
                if (LiveCommentView.m19354$$Nest$fgethighlightingMessageId(this.this$0) != LiveCommentView.m19355$$Nest$fgetmessage(this.this$0).id) goto L10;
                LiveCommentView.m19356$$Nest$fputhighlightAnimator(this.this$0, ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}));
                LiveCommentView.m19353$$Nest$fgethighlightAnimator(this.this$0).addUpdateListener(new LiveCommentsView$LiveCommentView$2$$ExternalSyntheticLambda0(this));
                LiveCommentView.m19353$$Nest$fgethighlightAnimator(this.this$0).setStartDelay(3000);
                LiveCommentView.m19353$$Nest$fgethighlightAnimator(this.this$0).setDuration(550);
                LiveCommentView.m19353$$Nest$fgethighlightAnimator(this.this$0).setInterpolator(new LinearInterpolator());
                LiveCommentView.m19353$$Nest$fgethighlightAnimator(this.this$0).start();
                return;
            }
        }

        /* renamed from: org.telegram.ui.Stories.LiveCommentsView$LiveCommentView$3, reason: invalid class name */
        public class AnonymousClass3 extends ReplacementSpan {
            private final Paint bg;
            private final RectF rect;
            private final Text text;
            final /* synthetic */ LiveCommentView this$0;

            public AnonymousClass3(LiveCommentView r4) {
                this.this$0 = r4;
                this.rect = new RectF();
                this.bg = new Paint(1);
                this.text = new Text(LocaleController.getString(R.string.LiveStoryBadge), 8.0f, AndroidUtilities.bold());
            }

            @Override // android.text.style.ReplacementSpan
            public void draw(Canvas r7, CharSequence r8, int r9, int r10, float r11, int r12, int r13, int r14, Paint r15) {
                float r3 = ((r12 + r14) / 2.0f) + AndroidUtilities.dp(0.0f);
                this.rect.set(r11, r3 - AndroidUtilities.dp(6.0f), (this.text.getWidth() + r11) + AndroidUtilities.dp(8.0f), AndroidUtilities.dp(6.0f) + r3);
                this.bg.setColor(-572850);
                RectF r82 = this.rect;
                r7.drawRoundRect(r82, r82.height() / 2.0f, this.rect.height() / 2.0f, this.bg);
                this.text.draw(r7, AndroidUtilities.dp(4.0f) + r11, r3, -1, 1.0f);
            }

            @Override // android.text.style.ReplacementSpan
            public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
                return (int) (this.text.getWidth() + AndroidUtilities.dp(8.0f));
            }
        }

        public static final class AlphaSpan extends CharacterStyle {
            private final float alpha;

            public AlphaSpan(float r1) {
                this.alpha = r1;
            }

            @Override // android.text.style.CharacterStyle
            public void updateDrawState(TextPaint r2) {
                r2.setAlpha((int) (this.alpha * r2.getAlpha()));
            }
        }

        public static class Factory extends UItem.UItemFactory<LiveCommentView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem of(Message r1) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((LiveCommentView) r1).set((Message) r2.object);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public LiveCommentView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                LiveCommentView r0 = new LiveCommentView(r1, r3, false);
                r0.setLayoutParams(new RecyclerView.LayoutParams(-2, -2));
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean equals(UItem r1, UItem r2) {
                if (r1.object != r2.object) goto L6;
                return true;
            L6:
                return false;
            }
        }

        /* renamed from: $r8$lambda$F7X9DwF-aQsZa6QS3jF4kyw0G_o, reason: not valid java name */
        public static /* synthetic */ int m19351$r8$lambda$F7X9DwFaQsZa6QS3jF4kyw0G_o(Pair r0, Pair r1) {
            return ((Integer) r0.first).intValue() - ((Integer) r1.first).intValue();
        }

        public static /* synthetic */ void $r8$lambda$geymmlyysQWIpV30SBB_OV6qUko(LiveCommentView r0, ValueAnimator r1) {
            r0.lambda$highlight$0(r1);
        }

        /* renamed from: -$$Nest$fgetdrawParticles, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19352$$Nest$fgetdrawParticles(LiveCommentView r0) {
            return r0.drawParticles;
        }

        /* renamed from: -$$Nest$fgethighlightAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ValueAnimator m19353$$Nest$fgethighlightAnimator(LiveCommentView r0) {
            return r0.highlightAnimator;
        }

        /* renamed from: -$$Nest$fgethighlightingMessageId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m19354$$Nest$fgethighlightingMessageId(LiveCommentView r0) {
            return r0.highlightingMessageId;
        }

        /* renamed from: -$$Nest$fgetmessage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Message m19355$$Nest$fgetmessage(LiveCommentView r0) {
            return r0.message;
        }

        /* renamed from: -$$Nest$fputhighlightAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19356$$Nest$fputhighlightAnimator(LiveCommentView r0, ValueAnimator r1) {
            r0.highlightAnimator = r1;
        }

        public LiveCommentView(Context r23, int r24, boolean r25) {
            super(r23);
            this.drawParticles = false;
            this.drawStar = true;
            this.backgroundViewAlpha = 0.5f;
            this.starsViewCache = new ColoredImageSpan[1];
            this.smallStarsViewCache = new ColoredImageSpan[1];
            this.backgroundPaint = new Paint(1);
            this.currentAccount = r24;
            this.filled = r25;
            AnonymousClass1 r4 = new AnonymousClass1(this, r23);
            this.layout = r4;
            r4.setOrientation(0);
            addView(r4, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 0.5f, 0.0f, 0.5f));
            this.avatarDrawable = new AvatarDrawable();
            BackupImageView r5 = new BackupImageView(r23);
            this.avatarView = r5;
            r5.setRoundRadius(AndroidUtilities.dp(11.0f));
            r4.addView(r5, LayoutHelper.createLinear(22, 22, 0.0f, 51, 3, 2, 3, 2));
            LinearLayout r52 = new LinearLayout(r23);
            this.textLayout = r52;
            r52.setOrientation(1);
            r4.addView(r52, LayoutHelper.createLinear(-2, -2, 1.0f, 51, 4, 3, 7, 3));
            LinearLayout r7 = new LinearLayout(r23);
            this.adminLayout = r7;
            r7.setOrientation(0);
            r7.setVisibility(8);
            r52.addView(r7, LayoutHelper.createLinear(-2, -2));
            SpoilersTextView r10 = new SpoilersTextView(r23);
            this.adminNameView = r10;
            r10.setTextColor(-1);
            r10.setTextSize(1, 14.0f);
            r10.setGravity(3);
            r10.setTypeface(AndroidUtilities.bold());
            r7.addView(r10, LayoutHelper.createLinear(-2, -2, 1.0f, 51, 0, 0, 16, 0));
            SpoilersTextView r102 = new SpoilersTextView(r23);
            this.adminRoleView = r102;
            r102.setTextColor(Theme.multAlpha(-1, 0.55f));
            r102.setTextSize(1, 12.0f);
            r102.setGravity(5);
            r7.addView(r102, LayoutHelper.createLinear(-2, -2, 0.0f, 53, 0, 0, 0, 0));
            SpoilersTextView r72 = new SpoilersTextView(r23);
            this.textView = r72;
            r72.setTextColor(-1);
            r72.setTextSize(1, 14.0f);
            r72.setShadowLayer(AndroidUtilities.dp(2.5f), 0.0f, AndroidUtilities.dp(1.5f), Theme.multAlpha(-16777216, 0.6f));
            NotificationCenter.listenEmojiLoading(r72);
            r52.addView(r72, LayoutHelper.createLinear(-2, -2));
            TextView r53 = new TextView(r23);
            this.starsView = r53;
            r53.setTextColor(-1);
            r53.setTextSize(1, 11.0f);
            r53.setPadding(AndroidUtilities.dp(4.66f), 0, AndroidUtilities.dp(4.66f), 0);
            r53.setVisibility(8);
            r4.addView(r53, LayoutHelper.createLinear(-2, 16, 0.0f, 21, -3, 0, 6, 0));
            TextView r2 = new TextView(r23);
            this.smallStarsView = r2;
            r2.setTextColor(-1);
            r2.setAlpha(0.65f);
            r2.setTextSize(1, 11.0f);
            r2.setVisibility(8);
            r4.addView(r2, LayoutHelper.createLinear(-2, -2, 0.0f, 85, 0, 3, 10, 0));
        }

        private /* synthetic */ void lambda$highlight$0(ValueAnimator r4) {
            float r42 = ((Float) r4.getAnimatedValue()).floatValue();
            Drawable r0 = this.background;
            if (r0 == null) goto L6;
            r0.setAlpha((int) (AndroidUtilities.lerp(this.backgroundViewAlpha, 1.0f, r42) * 255.0f));
            this.layout.invalidate();
            return;
        }

        @Override // org.telegram.ui.Components.ItemOptions.ScrimView
        public void drawScrim(Canvas r6, float r7) {
            if (this.layout.getBackground() != null) goto L5;
            this.backgroundPaint.setColor(Theme.multAlpha(-16777216, r7 * 0.5f));
            RectF r72 = AndroidUtilities.rectTmp;
            r72.set(this.layout.getX(), this.layout.getY(), this.layout.getX() + this.layout.getWidth(), this.layout.getY() + this.layout.getHeight());
            r6.drawRoundRect(r72, AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), this.backgroundPaint);
        L5:
            draw(r6);
        }

        public void getStarLocation(RectF r6) {
            if (this.starsViewCache[0] != null) goto L6;
            return;
        L6:
            if (this.starsView.getLayout() != null) goto L8;
            return;
        L8:
            float r0 = (this.starsView.getX() + this.starsView.getPaddingLeft()) + this.starsViewCache[0].translateX;
            float r2 = (this.starsView.getY() + this.starsView.getPaddingTop()) + this.starsViewCache[0].translateY;
            r6.set(r0, r2, r3.drawable.getBounds().width() + r0, this.starsViewCache[0].drawable.getBounds().height() + r2);
        }

        public void highlight() {
            ValueAnimator r0 = this.highlightAnimator;
            if (r0 == null) goto L7;
            r0.cancel();
            this.highlightAnimator = null;
            Drawable r02 = this.background;
            if (r02 == null) goto L7;
            r02.setAlpha((int) (this.backgroundViewAlpha * 255.0f));
            this.layout.invalidate();
        L7:
            Message r03 = this.message;
            if (r03 != null) goto L10;
            return;
        L10:
            if (this.background == null) goto L15;
            this.highlightingMessageId = r03.id;
            ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.highlightAnimator = r04;
            r04.addUpdateListener(new LiveCommentsView$LiveCommentView$$ExternalSyntheticLambda1(this));
            this.highlightAnimator.addListener(new AnonymousClass2(this));
            this.highlightAnimator.setDuration(350);
            this.highlightAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.highlightAnimator.start();
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            setPivotX(0.0f);
            setPivotY(getMeasuredHeight());
        }

        public void set(Message r23) {
            this.message = r23;
            if (r23 != null) goto L5;
        L6:
            ValueAnimator r4 = this.highlightAnimator;
            if (r4 == null) goto L11;
            r4.cancel();
            this.highlightAnimator = null;
            Drawable r42 = this.background;
            if (r42 == null) goto L11;
            r42.setAlpha((int) (this.backgroundViewAlpha * 255.0f));
            this.layout.invalidate();
        L11:
            long r43 = r23.dialogId;
            int r5 = this.currentAccount;
            if (r43 < 0) goto L14;
            TLRPC.User r44 = MessagesController.getInstance(r5).getUser(Long.valueOf(r23.dialogId));
            this.avatarDrawable.setInfo(r44);
            this.avatarView.setForUserOrChat(r44, this.avatarDrawable);
            String r45 = UserObject.getForcedFirstName(r44);
        L18:
            int r52 = HighlightMessageSheet.getTierOption(this.currentAccount, (int) r23.stars, HighlightMessageSheet.TIER_COLOR1);
            int r9 = HighlightMessageSheet.getTierOption(this.currentAccount, (int) r23.stars, HighlightMessageSheet.TIER_COLOR2);
            int r10 = HighlightMessageSheet.getTierOption(this.currentAccount, (int) r23.stars, HighlightMessageSheet.TIER_COLOR_BACKGROUND);
            SpannableStringBuilder r11 = new SpannableStringBuilder();
            if (r23.fromAdmin == false) goto L25;
            if (r23.stars > 0) goto L25;
            float r19 = 1.0f;
        L32:
            int r46 = HighlightMessageSheet.getTierOption(this.currentAccount, (int) r23.stars, HighlightMessageSheet.TIER_LENGTH);
            int r3 = HighlightMessageSheet.getTierOption(this.currentAccount, (int) r23.stars, HighlightMessageSheet.TIER_EMOJIS);
            TLRPC.TL_textWithEntities r47 = r23.text;
            if (r47 == null) goto L64;
            CharSequence r48 = MessageObject.formatTextWithEntities(r47, false, this.textView.getPaint());
            this.text = r48;
            CharSequence r49 = AndroidUtilities.superTrim(r48);
            this.text = r49;
            if (r49.length() > r46) goto L37;
        L39:
            CharSequence r410 = this.text;
            if ((r410 instanceof Spannable) == false) goto L59;
            Spannable r12 = (Spannable) r410;
            AnimatedEmojiSpan[] r411 = (AnimatedEmojiSpan[]) r12.getSpans(0, r410.length(), AnimatedEmojiSpan.class);
            Emoji.EmojiSpan[] r7 = (Emoji.EmojiSpan[]) r12.getSpans(0, this.text.length(), Emoji.EmojiSpan.class);
            if ((r411.length + r7.length) <= r3) goto L59;
            if (r23.fromAdmin == true) goto L59;
            ArrayList r14 = new ArrayList();
            int r15 = 0;
            boolean r20 = true;
        L47:
            if (r15 >= r411.length) goto L49;
            AnimatedEmojiSpan[] r21 = r411;
            r14.add(new Pair(Integer.valueOf(r12.getSpanStart(r411[r15])), Integer.valueOf(r12.getSpanEnd(r21[r15]))));
            r15 = r15 + 1;
            r411 = r21;
            goto L47
        L49:
            int r2 = 0;
        L51:
            if (r2 >= r7.length) goto L53;
            r14.add(new Pair(Integer.valueOf(r12.getSpanStart(r7[r2])), Integer.valueOf(r12.getSpanEnd(r7[r2]))));
            r2 = r2 + 1;
            goto L51
        L53:
            Collections.sort(r14, new LiveCommentsView$LiveCommentView$$ExternalSyntheticLambda0());
            if ((this.text instanceof SpannableStringBuilder) == true) goto L56;
            this.text = new SpannableStringBuilder(this.text);
        L56:
            int r22 = r14.size() - 1;
        L57:
            if (r22 < r3) goto L61;
            Pair r412 = (Pair) r14.get(r22);
            ((SpannableStringBuilder) this.text).replace(((Integer) r412.first).intValue(), ((Integer) r412.second).intValue(), _UrlKt.FRAGMENT_ENCODE_SET);
            r22 = r22 - 1;
        L61:
            if (r23.fromAdmin == true) goto L63;
            this.text = AndroidUtilities.replaceNewLines(this.text);
        L63:
            r11.append(this.text);
        L65:
            SpoilersTextView r24 = this.textView;
            r24.setText(Emoji.replaceEmoji(r11, r24.getPaint().getFontMetricsInt(), false));
            this.background = null;
            LinearLayout r25 = this.adminLayout;
            if (r23.fromAdmin == true) goto L68;
        L70:
            int r32 = 8;
        L71:
            r25.setVisibility(r32);
            long r26 = r23.stars;
            if (r26 <= 0) goto L87;
            LinearLayout r6 = this.layout;
            if (r26 < 250) goto L76;
            boolean r27 = r20;
        L77:
            this.drawParticles = r27;
            r6.setWillNotDraw(!r27);
            this.layout.invalidate();
            this.textView.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            LinearLayout r28 = this.layout;
            GradientDrawable r33 = Theme.createRoundRectGradientDrawable(AndroidUtilities.dp(13.0f), r52, r9);
            this.background = r33;
            r28.setBackground(r33);
            Drawable r29 = this.background;
            if (this.filled == true) goto L80;
            float r142 = 0.65f;
        L81:
            this.backgroundViewAlpha = r142;
            r29.setAlpha((int) (r142 * 255.0f));
            boolean r210 = r23.isReaction;
            TextView r34 = this.smallStarsView;
            if (r210 == true) goto L84;
            r34.setVisibility(0);
            this.smallStarsView.setText(StarsIntroActivity.replaceStars("⭐️ " + LocaleController.formatNumber(r23.stars, ','), 0.75f, this.smallStarsViewCache, 0.0f, 0.0f, 1.0f));
            this.starsView.setVisibility(8);
            this.starsView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        L91:
            this.layout.invalidate();
            return;
        L84:
            r34.setVisibility(8);
            this.smallStarsView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.starsView.setVisibility(0);
            this.starsView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(13.0f), Theme.multAlpha(r10, 0.25f)));
            this.starsView.setText(StarsIntroActivity.replaceStars("⭐️ " + LocaleController.formatNumber(r23.stars, ','), 0.75f, this.starsViewCache, 0.0f, AndroidUtilities.dp(0.66f), 1.0f));
            ColoredImageSpan r1 = this.starsViewCache[0];
            if (r1 == null) goto L91;
            r1.draw = this.drawStar;
            goto L91
        L80:
            r142 = r19;
            goto L81
        L76:
            r27 = false;
            goto L77
        L87:
            boolean r211 = r23.fromAdmin;
            LinearLayout r53 = this.layout;
            if (r211 == false) goto L90;
            this.drawParticles = false;
            r53.setWillNotDraw(r20);
            LinearLayout r212 = this.layout;
            ShapeDrawable r35 = Theme.createRoundRectDrawable(AndroidUtilities.dp(13.0f), -16777216);
            this.background = r35;
            r212.setBackground(r35);
            Drawable r213 = this.background;
            this.backgroundViewAlpha = 0.5f;
            r213.setAlpha(127);
            this.textView.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            SpannableStringBuilder r214 = new SpannableStringBuilder();
            r214.append(DialogObject.getName(this.currentAccount, r23.dialogId));
            r214.append(" ");
            int r13 = r214.length();
            r214.append(LocaleController.getString(R.string.LiveStoryBadge));
            r214.setSpan(new AnonymousClass3(this), r13, r214.length(), 33);
            this.adminNameView.setText(r214);
            this.adminRoleView.setText(LocaleController.getString(R.string.LiveStoryAdminRole));
            this.smallStarsView.setVisibility(8);
            this.starsView.setVisibility(8);
            goto L91
        L90:
            this.drawParticles = false;
            r53.setWillNotDraw(true);
            this.textView.setShadowLayer(AndroidUtilities.dp(2.5f), 0.0f, AndroidUtilities.dp(1.5f), Theme.multAlpha(-16777216, 0.6f));
            LinearLayout r16 = this.layout;
            this.background = null;
            r16.setBackground(null);
            this.smallStarsView.setVisibility(8);
            this.starsView.setVisibility(8);
            goto L91
        L68:
            if (r23.stars > 0) goto L70;
            r32 = 0;
        L59:
            r20 = true;
            goto L61
        L37:
            if (r23.fromAdmin == true) goto L39;
            this.text = this.text.subSequence(0, r46);
            goto L39
        L64:
            r20 = true;
            this.text = _UrlKt.FRAGMENT_ENCODE_SET;
        L25:
            if (r23.place <= 0) goto L27;
            r11.append("#" + r23.place);
            r19 = 1.0f;
            ColoredImageSpan r62 = new ColoredImageSpan(new CrownDrawable(getContext(), r23.place));
            r62.setTranslateY(AndroidUtilities.dp(1.0f));
            r11.setSpan(r62, 0, r11.length(), 33);
            r11.append("\u2009");
        L28:
            r11.append(TextUtils.ellipsize(r45, this.textView.getPaint(), AndroidUtilities.dp(100.0f), TextUtils.TruncateAt.END));
            if (this.filled == false) goto L31;
            r11.setSpan(new AlphaSpan(0.75f), 0, r11.length(), 33);
        L31:
            r11.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r11.length(), 33);
            r11.append(" ");
            goto L32
        L27:
            r19 = 1.0f;
            goto L28
        L14:
            TLRPC.Chat r413 = MessagesController.getInstance(r5).getChat(Long.valueOf(-r23.dialogId));
            this.avatarDrawable.setInfo(r413);
            this.avatarView.setForUserOrChat(r413, this.avatarDrawable);
            if (r413 != null) goto L17;
            r45 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L18
        L17:
            r45 = r413.title;
            goto L18
        L5:
            if (this.highlightingMessageId == r23.id) goto L11;
            goto L6
        }

        public void setDrawStar(boolean r3) {
            this.drawStar = r3;
            ColoredImageSpan r0 = this.starsViewCache[0];
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.draw == r3) goto L9;
            r0.draw = r3;
            this.starsView.invalidate();
            return;
        }
    }

    public static class LiveTopSenderView extends FrameLayout {
        public final AvatarDrawable avatarDrawable;
        public final BackupImageView avatarView;
        public final ImageView crownView;
        public final LinearLayout layout;
        private TopSender sender;
        public final TextView textView;

        /* renamed from: org.telegram.ui.Stories.LiveCommentsView$LiveTopSenderView$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearLayout {
            final AnimatedFloat animatedProgress;
            final Path clipPath;
            final Paint fillPaint;
            long lastDialogId;
            StarsReactionsSheet.Particles particles;
            final /* synthetic */ LiveTopSenderView this$0;

            public AnonymousClass1(LiveTopSenderView r8, Context r9) {
                this.this$0 = r8;
                super(r9);
                this.clipPath = new Path();
                this.fillPaint = new Paint(1);
                this.lastDialogId = 0;
                this.animatedProgress = new AnimatedFloat(this, 0, 1000, new LinearInterpolator());
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r9) {
                this.clipPath.rewind();
                RectF r0 = AndroidUtilities.rectTmp;
                r0.set(0.0f, 0.0f, getWidth(), getHeight());
                this.clipPath.addRoundRect(r0, AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), Path.Direction.CW);
                r9.save();
                r9.clipPath(this.clipPath);
                if (LiveTopSenderView.m19357$$Nest$fgetsender(this.this$0) == null) goto L8;
                int r02 = HighlightMessageSheet.getTierOption(LiveTopSenderView.m19357$$Nest$fgetsender(this.this$0).currentAccount, LiveTopSenderView.m19357$$Nest$fgetsender(this.this$0).getStars(), HighlightMessageSheet.TIER_COLOR1);
                int r1 = HighlightMessageSheet.getTierOption(LiveTopSenderView.m19357$$Nest$fgetsender(this.this$0).currentAccount, LiveTopSenderView.m19357$$Nest$fgetsender(this.this$0).getStars(), HighlightMessageSheet.TIER_COLOR_BACKGROUND);
                r9.drawColor(r02);
                if (this.lastDialogId == LiveTopSenderView.m19357$$Nest$fgetsender(this.this$0).dialogId) goto L7;
                this.animatedProgress.force(LiveTopSenderView.m19357$$Nest$fgetsender(this.this$0).getProgress());
            L7:
                float r03 = this.animatedProgress.set(LiveTopSenderView.m19357$$Nest$fgetsender(this.this$0).getProgress());
                this.lastDialogId = LiveTopSenderView.m19357$$Nest$fgetsender(this.this$0).dialogId;
                this.fillPaint.setColor(r1);
                this.fillPaint.setAlpha(127);
                Canvas r2 = r9;
                r2.drawRect(getWidth() * r03, 0.0f, getWidth(), getHeight(), this.fillPaint);
            L10:
                if (this.particles != null) goto L12;
                this.particles = new StarsReactionsSheet.Particles(1, 250);
            L12:
                this.particles.setBounds(0, 0, getWidth(), getHeight());
                this.particles.setSpeed(30.0f);
                this.particles.process();
                this.particles.draw(r2, -1, 0.85f);
                invalidate();
                r2.restore();
                super.dispatchDraw(r2);
                return;
            L8:
                r2 = r9;
                goto L10
            }
        }

        /* renamed from: org.telegram.ui.Stories.LiveCommentsView$LiveTopSenderView$2, reason: invalid class name */
        public class AnonymousClass2 extends TextView {
            private final GradientClip clip;
            final /* synthetic */ LiveTopSenderView this$0;
            private int width;

            public AnonymousClass2(LiveTopSenderView r1, Context r2) {
                this.this$0 = r1;
                super(r2);
                this.width = -1;
                this.clip = new GradientClip();
            }

            @Override // android.widget.TextView, android.view.View
            public void onDraw(Canvas r9) {
                if (this.width >= 0) goto L10;
                int r1 = 0;
                if (getLayout() == null) goto L8;
                r1 = (int) getLayout().getLineWidth(0);
            L8:
                this.width = r1;
            L10:
                if (this.width <= AndroidUtilities.dp(100.0f)) goto L13;
                r9.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
                super.onDraw(r9);
                r9.save();
                RectF r92 = AndroidUtilities.rectTmp;
                r92.set(getWidth() - AndroidUtilities.dp(15.0f), 0.0f, getWidth(), getHeight());
                this.clip.draw(r9, r92, 2, 1.0f);
                r9.restore();
                r9.restore();
                return;
            L13:
                super.onDraw(r9);
            }

            @Override // android.widget.TextView, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100.0f), Integer.MIN_VALUE), r3);
            }

            @Override // android.widget.TextView
            public void setText(CharSequence r1, TextView.BufferType r2) {
                super.setText(r1, r2);
                this.width = -1;
            }
        }

        public static class Factory extends UItem.UItemFactory<LiveTopSenderView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem of(TopSender r1) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((LiveTopSenderView) r1).set((TopSender) r2.object);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public LiveTopSenderView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new LiveTopSenderView(r1);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean equals(UItem r1, UItem r2) {
                if (r1.object != r2.object) goto L6;
                return true;
            L6:
                return false;
            }
        }

        /* renamed from: -$$Nest$fgetsender, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TopSender m19357$$Nest$fgetsender(LiveTopSenderView r0) {
            return r0.sender;
        }

        public LiveTopSenderView(Context r12) {
            super(r12);
            ScaleStateListAnimator.apply(this);
            AnonymousClass1 r0 = new AnonymousClass1(this, r12);
            this.layout = r0;
            r0.setOrientation(0);
            addView(r0, LayoutHelper.createFrame(-2, -2.0f, 119, 0.0f, 0.0f, 6.0f, 0.0f));
            this.avatarDrawable = new AvatarDrawable();
            BackupImageView r1 = new BackupImageView(r12);
            this.avatarView = r1;
            r1.setRoundRadius(AndroidUtilities.dp(11.0f));
            r0.addView(r1, LayoutHelper.createLinear(22, 22, 0.0f, 51, 3, 2, 7, 2));
            ImageView r13 = new ImageView(r12);
            this.crownView = r13;
            r13.setVisibility(8);
            r0.addView(r13, LayoutHelper.createLinear(18, 18, 19, 0, 0, 3, 0));
            AnonymousClass2 r14 = new AnonymousClass2(this, r12);
            this.textView = r14;
            r14.setLines(1);
            r14.setSingleLine();
            r14.setTextColor(-1);
            r14.setTextSize(1, 14.0f);
            r14.setTypeface(AndroidUtilities.bold());
            r0.addView(r14, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 7, 0));
        }

        public void set(TopSender r5) {
            this.sender = r5;
            if (r5.dialogId < 0) goto L5;
            TLRPC.User r0 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r5.dialogId));
            this.avatarDrawable.setInfo(r0);
            this.avatarView.setForUserOrChat(r0, this.avatarDrawable);
        L6:
            int r02 = r5.place;
            ImageView r1 = this.crownView;
            if (r02 <= 0) goto L9;
            r1.setImageDrawable(new CrownDrawable(getContext(), r5.place));
            this.crownView.setVisibility(0);
        L10:
            this.textView.setText(DialogObject.getName(r5.dialogId));
            this.layout.invalidate();
            return;
        L9:
            r1.setVisibility(8);
            goto L10
        L5:
            TLRPC.Chat r03 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-r5.dialogId));
            this.avatarDrawable.setInfo(r03);
            this.avatarView.setForUserOrChat(r03, this.avatarDrawable);
            goto L6
        }
    }

    public static class Message {
        public int date;
        public long dialogId;
        public boolean fromAdmin;
        public int id;
        public boolean isReaction;
        public int place;
        public long stars;
        public TLRPC.TL_textWithEntities text;

        public Message() {
        }
    }

    public static class TopSender {
        public int currentAccount;
        public long dialogId;
        public int lastSentDate;
        private long max_stars;
        public ArrayList<Message> messages;
        public int place;

        /* renamed from: -$$Nest$fgetmax_stars, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m19358$$Nest$fgetmax_stars(TopSender r2) {
            return r2.max_stars;
        }

        public TopSender() {
            this.messages = new ArrayList();
        }

        public int expiresAfter(int r12) {
            ArrayList<Message> r0 = this.messages;
            int r1 = r0.size();
            int r4 = r12;
            int r3 = 0;
            int r5 = 0;
        L3:
            if (r5 >= r1) goto L8;
            Message r6 = r0.get(r5);
            r5 = r5 + 1;
            Message r62 = r6;
            if (r62.stars <= 0) goto L3;
            r4 = Math.min(r4, r62.date);
            r3 = Math.max(r3, r62.date + HighlightMessageSheet.getTierOption(this.currentAccount, (int) r62.stars, HighlightMessageSheet.TIER_PERIOD));
            goto L3
        L8:
            return Math.max(0, r3 - r12);
        }

        public float getProgress() {
            return getProgress(ConnectionsManager.getInstance(this.currentAccount).getCurrentTime());
        }

        public float getProgress(int r11) {
            ArrayList<Message> r0 = this.messages;
            int r1 = r0.size();
            int r2 = 0;
            int r4 = r11;
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L8;
            Message r5 = r0.get(r3);
            r3 = r3 + 1;
            Message r52 = r5;
            if (r52.stars <= 0) goto L3;
            r4 = Math.min(r4, r52.date);
            r2 = Math.max(r2, r52.date + HighlightMessageSheet.getTierOption(this.currentAccount, (int) r52.stars, HighlightMessageSheet.TIER_PERIOD));
            goto L3
        L8:
            return AndroidUtilities.ilerp(r11, r2, r4);
        }

        public int getStars() {
            return getStars(ConnectionsManager.getInstance(this.currentAccount).getCurrentTime());
        }

        public int getStars(int r10) {
            ArrayList<Message> r0 = this.messages;
            int r1 = r0.size();
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L9;
            Message r4 = r0.get(r3);
            r3 = r3 + 1;
            Message r42 = r4;
            long r5 = r42.stars;
            if (r5 <= 0) goto L3;
            if ((r10 - r42.date) > HighlightMessageSheet.getTierOption(this.currentAccount, (int) r5, HighlightMessageSheet.TIER_PERIOD)) goto L3;
            r2 = r2 + ((int) r42.stars);
            goto L3
        L9:
            this.max_stars = Math.max(this.max_stars, r2);
            return r2;
        }

        public boolean isExpired(int r10) {
            ArrayList<Message> r0 = this.messages;
            int r1 = r0.size();
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L9;
            Message r4 = r0.get(r3);
            r3 = r3 + 1;
            Message r42 = r4;
            long r5 = r42.stars;
            if (r5 <= 0) goto L3;
            if ((r10 - r42.date) > HighlightMessageSheet.getTierOption(this.currentAccount, (int) r5, HighlightMessageSheet.TIER_PERIOD)) goto L3;
            return false;
        L9:
            return true;
        }

        public void updateLastSentDate() {
            updateLastSentDate(ConnectionsManager.getInstance(this.currentAccount).getCurrentTime());
        }

        public void updateLastSentDate(int r9) {
            ArrayList<Message> r0 = this.messages;
            int r1 = r0.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L7;
            Message r3 = r0.get(r2);
            r2 = r2 + 1;
            Message r32 = r3;
            if (r32.stars <= 0) goto L3;
            r9 = Math.min(r9, r32.date);
            goto L3
        L7:
            this.lastSentDate = r9;
        }
    }

    /* renamed from: $r8$lambda$-ueX5Ja1dicqtbnuDU4QaGnurRg, reason: not valid java name */
    public static /* synthetic */ void m19337$r8$lambda$ueX5Ja1dicqtbnuDU4QaGnurRg(LiveCommentsView r0) {
        r0.lambda$scheduleRemovingTopSenders$0();
    }

    /* renamed from: $r8$lambda$5F6k-6vdPzVlmU98j6NJwQ-srmg, reason: not valid java name */
    public static /* synthetic */ void m19338$r8$lambda$5F6k6vdPzVlmU98j6NJwQsrmg(LiveCommentsView r0) {
        r0.lambda$new$9();
    }

    /* renamed from: $r8$lambda$AtcPunXeFJjB-movfxBcbFvK2nM, reason: not valid java name */
    public static /* synthetic */ void m19339$r8$lambda$AtcPunXeFJjBmovfxBcbFvK2nM(LiveCommentsView r0) {
        r0.lambda$new$12();
    }

    /* renamed from: $r8$lambda$EjvO3DU6M9mVi-vO9ff3IEzcnnc, reason: not valid java name */
    public static /* synthetic */ void m19340$r8$lambda$EjvO3DU6M9mVivO9ff3IEzcnnc(LiveCommentsView r0, TL_phone.getGroupCallStars r1, TL_phone.groupCallStars r2, TLRPC.TL_error r3) {
        r0.lambda$pollStars$10(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$HIx6XSSS3Ak7pwgVPzYa2aam-rI, reason: not valid java name */
    public static /* synthetic */ void m19341$r8$lambda$HIx6XSSS3Ak7pwgVPzYa2aamrI(LiveCommentsView r0, int r1, TLRPC.TL_error r2, long r3, long r5, TLRPC.TL_textWithEntities r7) {
        r0.lambda$send$15(r1, r2, r3, r5, r7);
    }

    public static /* synthetic */ void $r8$lambda$IGb63cLWxy5voNbOrMvavZuKxWo(LiveCommentsView r0, LiveCommentView r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$IIq7NUSqv2LOVGbmuoVA6U1zQu4(LiveCommentsView r0, ValueAnimator r1) {
        r0.lambda$setCollapsed$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$IORWpoLeBlzLWFWGTT3GuZwuvJg(LiveCommentsView r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ Integer $r8$lambda$NFKKi_6j4akGbmJFA9TLwIltdOA(LiveCommentsView r0, Long r1, Long r2) {
        return r0.lambda$openStarsSheet$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Qgc8B_u6RK44YO0D_Ii_rHh9WBg(LiveCommentsView r0, Message r1, Boolean r2, Boolean r3, Boolean r4) {
        r0.lambda$new$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Rkiw0cZMP7nzAtys3pkwMYKiues(CheckBoxCell r1, View r2) {
        r1.setChecked(!r1.isChecked(), true);
    }

    public static /* synthetic */ void $r8$lambda$SYF2Z4QrCyRmnenMMOXObHqw2u4(LiveCommentsView r0, View r1, int r2, float r3, float r4) {
        r0.lambda$new$7(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$TGLMu4EJIB8lp-xmkPuJaSP3-lY, reason: not valid java name */
    public static /* synthetic */ void m19342$r8$lambda$TGLMu4EJIB8lpxmkPuJaSP3lY(LiveCommentsView r0, ViewGroup r1, StoryViewer r2, View r3, int r4) {
        r0.lambda$new$5(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$VjgXh1po6s-4UWjYaqiZtizPE2k, reason: not valid java name */
    public static /* synthetic */ void m19343$r8$lambda$VjgXh1po6s4UWjYaqiZtizPE2k(LiveCommentsView r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillTopItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZH4F5qdWA7jhBki2jSq5_onFrV0(LiveCommentsView r0, long r1, TLRPC.TL_textWithEntities r3, long r4) {
        r0.lambda$send$14(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$bi_6IPui0vc4eRoFAvIm42mGX0k(LiveCommentsView r0) {
        r0.lambda$new$17();
    }

    public static /* synthetic */ int $r8$lambda$cx80IySZ0ZW7rzxIwarf9KavZ0U(LiveCommentsView r0, TopSender r1, TopSender r2) {
        return r0.sortTopMessages(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$dcLSomt6nt2WWc38QL0SAeezAM0(CheckBoxCell r1, View r2) {
        r1.setChecked(!r1.isChecked(), true);
    }

    public static /* synthetic */ void $r8$lambda$gTpcrXKXW3G4czLK6ZDPFAbMT8w(LiveCommentsView r0, TL_phone.sendGroupCallMessage r1, int r2, long r3, long r5, TLRPC.TL_textWithEntities r7, TLObject r8, TLRPC.TL_error r9) {
        r0.lambda$send$16(r1, r2, r3, r5, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$jmfyXPO07HtNBDdnhW47zhF5dzI(CheckBoxCell r0, CheckBoxCell r1, CheckBoxCell r2, Utilities.Callback3 r3, BottomSheet r4, View r5) {
        boolean r02 = r0.isChecked();
        boolean r12 = r1.isChecked();
        boolean r22 = r2.isChecked();
        r3.run(Boolean.valueOf(r02), Boolean.valueOf(r12), Boolean.valueOf(r22));
        r4.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$m8DgmOXJsgbUUsocvyxYD6SOT1M(LiveCommentsView r0, View r1) {
        r0.lambda$new$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$sc5GzASxq_qsPdmswJeXfGUMOyU(CheckBoxCell r1, View r2) {
        r1.setChecked(!r1.isChecked(), true);
    }

    public static /* synthetic */ void $r8$lambda$wrOT480S2dIWY7XZ4Qt8hiBh9wk(LiveCommentsView r0, ValueAnimator r1) {
        r0.lambda$updateTopMessages$8(r1);
    }

    /* renamed from: $r8$lambda$wuKQW6P5hIT50vzcHU-n5qJIY6I, reason: not valid java name */
    public static /* synthetic */ void m19344$r8$lambda$wuKQW6P5hIT50vzcHUn5qJIY6I(LiveCommentsView r0, Message r1) {
        r0.lambda$new$4(r1);
    }

    /* renamed from: $r8$lambda$y3Wo3-CjgU70cCc3MVU1TZwWVTU, reason: not valid java name */
    public static /* synthetic */ int m19345$r8$lambda$y3Wo3CjgU70cCc3MVU1TZwWVTU(TL_phone.groupCallDonor r2, TL_phone.groupCallDonor r3) {
        return (int) (r3.stars - r2.stars);
    }

    public static /* synthetic */ void $r8$lambda$yRXhfRAzvv6nqrulicTMz6sMXac(StoryViewer r2, Message r3) {
        r2.presentFragment(ProfileActivity.of(r3.dialogId));
    }

    /* renamed from: -$$Nest$fgetcallHighlight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19346$$Nest$fgetcallHighlight(LiveCommentsView r0) {
        return r0.callHighlight;
    }

    /* renamed from: -$$Nest$fgetcollapsed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19347$$Nest$fgetcollapsed(LiveCommentsView r0) {
        return r0.collapsed;
    }

    /* renamed from: -$$Nest$fgethighlightingMessageId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19348$$Nest$fgethighlightingMessageId(LiveCommentsView r0) {
        return r0.highlightingMessageId;
    }

    /* renamed from: -$$Nest$fgetshadowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m19349$$Nest$fgetshadowView(LiveCommentsView r0) {
        return r0.shadowView;
    }

    /* renamed from: -$$Nest$fputcallHighlight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19350$$Nest$fputcallHighlight(LiveCommentsView r0, boolean r1) {
        r0.callHighlight = r1;
    }

    public LiveCommentsView(Context r20, StoryViewer r21, ViewGroup r22, View r23, FrameLayout r24) {
        super(r20);
        this.messages = new ArrayList();
        this.topMessages = new ArrayList();
        this.topPlaces = new HashMap();
        this.maxReadId = -1;
        this.allowTouches = true;
        this.gradientClip = new GradientClip();
        int r4 = UserConfig.selectedAccount;
        this.currentAccount = r4;
        this.topDonors = new ArrayList();
        this.pollStarsRunnable = new LiveCommentsView$$ExternalSyntheticLambda1(this);
        this.closeBulletin = new LiveCommentsView$$ExternalSyntheticLambda2(this);
        this.collapsed = false;
        this.updateAdapters = new LiveCommentsView$$ExternalSyntheticLambda3(this);
        this.shadowView = r23;
        this.storyViewer = r21;
        this.topBulletinContainer = r24;
        r23.setAlpha(0.5f);
        AnonymousClass1 r0 = new AnonymousClass1(this, r20);
        this.listView = r0;
        r0.setWillNotDraw(false);
        LinearLayoutManager r5 = new LinearLayoutManager(r20, 1, true);
        this.layoutManager = r5;
        r0.setLayoutManager(r5);
        AnonymousClass2 r02 = new AnonymousClass2(this, r0, r20, r4, 0, false, new LiveCommentsView$$ExternalSyntheticLambda4(this), new DarkThemeResourceProvider());
        this.adapter = r02;
        r0.setAdapter(r02);
        r02.setApplyBackground(false);
        r0.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(7.5f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(7.5f));
        r0.setClipToPadding(false);
        addView(r0, LayoutHelper.createFrame(-1, -1.0f, 87, 0.0f, 0.0f, 0.0f, 34.0f));
        r0.setOnItemClickListener(new LiveCommentsView$$ExternalSyntheticLambda5(this, r22, r21));
        AnonymousClass3 r1 = new AnonymousClass3(this);
        r1.setSupportsChangeAnimations(false);
        r1.setDelayAnimations(false);
        CubicBezierInterpolator r9 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r1.setInterpolator(r9);
        r1.setDurations(280);
        r1.setDelayIncrement(14);
        r0.setItemAnimator(r1);
        ImageView r03 = new ImageView(r20);
        this.arrowButton = r03;
        r03.setImageResource(R.drawable.msg_arrowright);
        r03.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        r03.setRotation(90.0f);
        r03.setBackground(Theme.createSelectorDrawable(1090519039));
        r03.setOnClickListener(new LiveCommentsView$$ExternalSyntheticLambda6(this));
        AnonymousClass4 r12 = new AnonymousClass4(this, r20);
        this.topListView = r12;
        r12.setWillNotDraw(false);
        LinearLayoutManager r04 = new LinearLayoutManager(r20, 0, false);
        this.topLayoutManager = r04;
        r12.setLayoutManager(r04);
        UniversalAdapter r05 = new UniversalAdapter(r12, r20, r4, 0, new LiveCommentsView$$ExternalSyntheticLambda7(this), null);
        this.topAdapter = r05;
        r12.setAdapter(r05);
        r05.setApplyBackground(false);
        r12.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        r12.setClipToPadding(false);
        addView(r12, LayoutHelper.createFrame(-1, 26.0f, 87, 0.0f, 0.0f, 0.0f, 9.66f));
        r12.setOnItemClickListener(new LiveCommentsView$$ExternalSyntheticLambda8(this));
        AnonymousClass5 r06 = new AnonymousClass5(this);
        r06.setSupportsChangeAnimations(false);
        r06.setDelayAnimations(false);
        r06.setInterpolator(r9);
        r06.setDurations(350);
        r12.setItemAnimator(r06);
        updateTopMessages(false);
    }

    private void fillItems(ArrayList<UItem> r6, UniversalAdapter r7) {
        LivePlayer r72 = this.livePlayer;
        if (r72 != null) goto L5;
        long r0 = 0;
    L6:
        this.lastMinStars = r0;
        int r73 = 0;
    L8:
        if (r73 >= this.messages.size()) goto L17;
        Message r2 = this.messages.get(r73);
        if (r2.fromAdmin == false) goto L12;
    L15:
        r6.add(LiveCommentView.Factory.of(r2));
    L16:
        r73 = r73 + 1;
        goto L8
    L12:
        if (r2.isReaction == false) goto L15;
        if (r2.stars < r0) goto L16;
    L17:
        return;
    L5:
        r0 = r72.getSendPaidMessagesStars();
        goto L6
    }

    private void fillTopItems(ArrayList<UItem> r2, UniversalAdapter r3) {
        int r32 = 0;
    L4:
        if (r32 >= this.topMessages.size()) goto L6;
        r2.add(LiveTopSenderView.Factory.of(this.topMessages.get(r32)));
        r32 = r32 + 1;
        goto L4
    }

    private long getDefaultPeerId() {
        TLRPC.Peer r0 = getDefaultSendAs();
        LivePlayer r1 = this.livePlayer;
        if (r1 != null) goto L5;
    L10:
        if (r0 != null) goto L14;
        return UserConfig.getInstance(this.currentAccount).getClientUserId();
    L14:
        return DialogObject.getPeerDialogId(r0);
    L5:
        if (r1.isAdmin() == false) goto L10;
        if (this.livePlayer.sendAsDisabled() == false) goto L10;
        return this.dialogId;
    }

    private int getListViewTop() {
        int r0 = this.listView.getHeight();
        int r1 = 0;
    L3:
        int r2 = this.listView.getChildCount();
        RecyclerListView r3 = this.listView;
        if (r1 >= r2) goto L7;
        r0 = Math.min(r3.getChildAt(r1).getTop(), r0);
        r1 = r1 + 1;
        goto L3
    L7:
        return r3.getHeight() - r0;
    }

    private int getPlace(long r1) {
        return this.topPlaces.getOrDefault(Long.valueOf(r1), 0).intValue();
    }

    private CharSequence getStarsToastSubtitle() {
        return AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("PaidMessageSentSubtitle", Math.max(0, (int) this.localStars)));
    }

    private String getStarsToastTitle() {
        return LocaleController.getString(R.string.StarsSentTitle);
    }

    private int getTotalMyStars() {
        int r0 = (int) this.localStars;
        int r1 = 0;
    L4:
        if (r1 >= this.topDonors.size()) goto L9;
        if (this.topDonors.get(r1).my == false) goto L8;
        r0 = (int) (r0 + this.topDonors.get(r1).stars);
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        return r0;
    }

    private /* synthetic */ void lambda$new$12() {
        AndroidUtilities.cancelRunOnUIThread(this.closeBulletin);
        Bulletin r0 = this.starsBulletin;
        if (r0 == null) goto L5;
        r0.hide();
        this.starsBulletin = null;
    L5:
        long r02 = this.localStars;
        if (r02 <= 0) goto L9;
        this.localStars = 0;
        this.sentStars = true;
        send(new TLRPC.TL_textWithEntities(), r02);
        return;
    L9:
        onStarsCountUpdated();
    }

    private /* synthetic */ void lambda$new$17() {
        this.adapter.update(true);
        this.topAdapter.update(true);
    }

    private /* synthetic */ void lambda$new$2(LiveCommentView r2) {
        AndroidUtilities.addToClipboard(r2.text);
        if (AndroidUtilities.shouldShowClipboardToast() == false) goto L6;
        Toast.makeText(getContext(), LocaleController.getString(R.string.TextCopied), 0).show();
        return;
    }

    private /* synthetic */ void lambda$new$3(Message r7, Boolean r8, Boolean r9, Boolean r10) {
        if (r9.booleanValue() == false) goto L5;
        TL_phone.deleteGroupCallParticipantMessages r92 = new TL_phone.deleteGroupCallParticipantMessages();
        r92.call = this.inputCall;
        r92.participant = MessagesController.getInstance(this.currentAccount).getInputPeer(r7.dialogId);
        r92.report_spam = r8.booleanValue();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r92, null);
        deleteAllFrom(r7.dialogId);
    L7:
        if (r10.booleanValue() == false) goto L14;
        long r82 = this.dialogId;
        int r93 = this.currentAccount;
        if (r82 < 0) goto L12;
        MessagesController.getInstance(r93).blockPeer(this.dialogId);
        return;
    L12:
        MessagesController.getInstance(r93).deleteParticipantFromChat(-this.dialogId, MessagesController.getInstance(this.currentAccount).getInputPeer(r7.dialogId), false, true);
        return;
    L14:
        return;
    L5:
        TL_phone.deleteGroupCallMessages r83 = new TL_phone.deleteGroupCallMessages();
        r83.call = this.inputCall;
        r83.messages.add(Integer.valueOf(r7.id));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r83, null);
        delete(r7.id);
        goto L7
    }

    private /* synthetic */ void lambda$new$4(Message r5) {
        if (isMe(r5.dialogId) == false) goto L6;
        TL_phone.deleteGroupCallMessages r0 = new TL_phone.deleteGroupCallMessages();
        r0.call = this.inputCall;
        r0.messages.add(Integer.valueOf(r5.id));
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
        delete(r5.id);
        return;
    L6:
        openDeleteMessage(getContext(), r5.dialogId, new LiveCommentsView$$ExternalSyntheticLambda20(this, r5));
    }

    private /* synthetic */ void lambda$new$5(ViewGroup r5, StoryViewer r6, View r7, int r8) {
        LiveCommentView r82 = (LiveCommentView) r7;
        Message r0 = LiveCommentView.m19355$$Nest$fgetmessage(r82);
        ItemOptions r52 = ItemOptions.makeOptions(r5, new DarkThemeResourceProvider(), r7);
        boolean r3 = true;
        r52.addText(LocaleController.formatString(R.string.LiveStoryMessageSent, new Object[]{LocaleController.formatDateTime(r0.date, true)}), 15);
        r52.addGap();
        r52.add(R.drawable.msg_openprofile, LocaleController.getString(R.string.OpenProfile), new LiveCommentsView$$ExternalSyntheticLambda13(r6, r0));
        r52.addIf(!r0.isReaction, R.drawable.msg_copy, LocaleController.getString(R.string.Copy), new LiveCommentsView$$ExternalSyntheticLambda14(this, r82));
        if (this.dialogId != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L5;
    L8:
        r52.addIf(r3, R.drawable.msg_delete, LocaleController.getString(R.string.Delete), new LiveCommentsView$$ExternalSyntheticLambda15(this, r0));
        r52.show();
        return;
    L5:
        if (isAdmin() == true) goto L8;
        r3 = false;
        goto L8
    }

    private /* synthetic */ void lambda$new$6(View r2) {
        setCollapsed(!this.collapsed, true);
    }

    private /* synthetic */ void lambda$new$7(View r10, int r11, float r12, float r13) {
        TopSender r102 = LiveTopSenderView.m19357$$Nest$fgetsender((LiveTopSenderView) r10);
        int r112 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        HashSet r122 = new HashSet();
        int r132 = 0;
        int r0 = 0;
    L3:
        long r2 = 0;
        if (r0 >= r102.messages.size()) goto L11;
        Message r1 = r102.messages.get(r0);
        long r4 = r1.stars;
        if (r4 <= 0) goto L10;
        if ((r112 - r1.date) > HighlightMessageSheet.getTierOption(this.currentAccount, (int) r4, HighlightMessageSheet.TIER_PERIOD)) goto L10;
        r122.add(Integer.valueOf(r1.id));
    L10:
        r0 = r0 + 1;
        goto L3
    L11:
        LivePlayer r113 = this.livePlayer;
        if (r113 == null) goto L15;
        r2 = r113.getSendPaidMessagesStars();
    L15:
        int r114 = 0;
        int r02 = 0;
    L16:
        int r42 = -1;
        if (r114 >= this.messages.size()) goto L35;
        Message r14 = this.messages.get(r114);
        if (r14.fromAdmin == true) goto L25;
        if (r14.isReaction == false) goto L25;
        if (r14.stars >= r2) goto L25;
    L34:
        r114 = r114 + 1;
    L25:
        if (r122.contains(Integer.valueOf(r14.id)) == true) goto L27;
    L33:
        r02 = r02 + 1;
        goto L34
    L27:
        if (this.highlightingDialog != r102.dialogId) goto L32;
        int r5 = this.highlightingMessageId;
        if (r5 == 0) goto L32;
        if (r14.id >= r5) goto L33;
    L32:
        int r115 = r14.id;
    L36:
        if (r115 >= 0) goto L52;
        int r116 = 0;
    L39:
        if (r132 >= this.messages.size()) goto L49;
        Message r03 = this.messages.get(r132);
        if (r03.fromAdmin == true) goto L47;
        if (r03.isReaction == false) goto L47;
        if (r03.stars >= r2) goto L47;
    L51:
        r132 = r132 + 1;
    L47:
        if (r122.contains(Integer.valueOf(r03.id)) == true) goto L48;
        r116 = r116 + 1;
        goto L51
    L48:
        r42 = r03.id;
    L49:
        r02 = r116;
    L53:
        if (r42 >= 0) goto L55;
        return;
    L55:
        this.highlightingDialog = r102.dialogId;
        this.highlightingMessageId = r42;
        this.callHighlight = true;
        RecyclerView.ItemAnimator r117 = this.listView.getItemAnimator();
        this.listView.setItemAnimator(null);
        this.layoutManager.scrollToPositionWithOffset(r02, this.listView.getHeight() / 2, true);
        this.adapter.notifyItemChanged(r02);
        this.listView.setItemAnimator(r117);
        return;
    L52:
        r42 = r115;
        goto L53
    L35:
        r115 = -1;
        goto L36
    }

    private /* synthetic */ void lambda$new$9() {
        pollStars();
    }

    private /* synthetic */ Integer lambda$openStarsSheet$11(Long r9, Long r10) {
        this.closeBulletin.run();
        this.localStars = r10.longValue();
        Bulletin r92 = BulletinFactory.of(this.topBulletinContainer, new DarkThemeResourceProvider()).createSimpleBulletin(R.raw.stars_topup, getStarsToastTitle(), getStarsToastSubtitle());
        boolean r0 = false;
        r92.hideAfterBottomSheet = false;
        r92.show(true);
        long r2 = 0;
        this.localStars = 0;
        this.sentStars = true;
        int r93 = send(new TLRPC.TL_textWithEntities(), r10.longValue());
        LivePlayer r4 = this.livePlayer;
        if (r4 == null) goto L7;
        r2 = r4.getSendPaidMessagesStars();
    L7:
        if (getDefaultPeerId() != this.dialogId) goto L12;
        if (isAdmin() == false) goto L12;
        r0 = true;
    L12:
        if (r10.longValue() >= r2) goto L17;
        if (r0 == true) goto L17;
        return Integer.MIN_VALUE;
    L17:
        return Integer.valueOf(r93);
    }

    private /* synthetic */ void lambda$pollStars$10(TL_phone.getGroupCallStars r7, TL_phone.groupCallStars r8, TLRPC.TL_error r9) {
        boolean r92 = false;
        this.polling = false;
        TLRPC.InputGroupCall r0 = this.inputCall;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.id != r7.call.id) goto L35;
        if (r8 == null) goto L28;
        MessagesController.getInstance(this.currentAccount).putUsers(r8.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r8.chats, false);
        int r72 = 0;
    L10:
        if (r72 >= r8.top_donors.size()) goto L17;
        if (r8.top_donors.get(r72).my == true) goto L14;
        r72 = r72 + 1;
        goto L10
    L14:
        if (r8.top_donors.get(r72).stars <= 0) goto L17;
        boolean r73 = true;
    L18:
        long r2 = r8.total_stars;
        if (r2 == this.totalStars) goto L21;
    L22:
        r92 = true;
    L23:
        this.totalStars = r2;
        this.topDonors = r8.top_donors;
        this.sentStars = r73;
        if (r92 == false) goto L26;
        onStarsCountUpdated();
    L26:
        updateMessagesPlaces();
        goto L28
    L21:
        if (this.sentStars == r73) goto L23;
    L17:
        r73 = false;
    L28:
        if (isAttachedToWindow() == false) goto L34;
        AndroidUtilities.cancelRunOnUIThread(this.pollStarsRunnable);
        AndroidUtilities.runOnUIThread(this.pollStarsRunnable, 5000);
        return;
    L34:
        return;
    }

    private /* synthetic */ void lambda$scheduleRemovingTopSenders$0() {
        removeTopSenders();
    }

    private /* synthetic */ void lambda$send$14(long r1, TLRPC.TL_textWithEntities r3, long r4) {
        send(r1, r3, r4);
    }

    private /* synthetic */ void lambda$send$15(int r11, TLRPC.TL_error r12, long r13, long r15, TLRPC.TL_textWithEntities r17) {
        delete(r11);
        if ("BALANCE_TOO_LOW".equalsIgnoreCase(r12.text) == false) goto L7;
        new StarsIntroActivity.StarsNeededSheet(getContext(), new DarkThemeResourceProvider(), r13, 17, _UrlKt.FRAGMENT_ENCODE_SET, new LiveCommentsView$$ExternalSyntheticLambda22(this, r15, r17, r13), this.dialogId).show();
        return;
    L7:
        if ("GROUPCALL_INVALID".equalsIgnoreCase(r12.text) == false) goto L12;
        LivePlayer r10 = this.livePlayer;
        if (r10 == null) goto L14;
        r10.storyDeleted();
        return;
    L14:
        return;
    L12:
        BulletinFactory.of(this.topBulletinContainer, new DarkThemeResourceProvider()).showForError(r12, true);
    }

    private /* synthetic */ void lambda$send$16(TL_phone.sendGroupCallMessage r13, int r14, long r15, long r17, TLRPC.TL_textWithEntities r19, TLObject r20, TLRPC.TL_error r21) {
        if ((r20 instanceof TLRPC.Updates) == false) goto L11;
        TLRPC.Updates r0 = (TLRPC.Updates) r20;
        ArrayList r2 = MessagesController.findUpdatesAndRemove(r0, TL_update.TL_updateMessageID.class);
        int r3 = r2.size();
        int r5 = 0;
    L5:
        if (r5 >= r3) goto L9;
        Object r6 = r2.get(r5);
        r5 = r5 + 1;
        TL_update.TL_updateMessageID r62 = (TL_update.TL_updateMessageID) r6;
        if (r13.random_id != r62.random_id) goto L5;
        updateMessageId(r14, r62.id);
        goto L5
    L9:
        MessagesController.getInstance(this.currentAccount).processUpdates(r0, false);
        return;
    L11:
        if (r21 == null) goto L19;
        AndroidUtilities.runOnUIThread(new LiveCommentsView$$ExternalSyntheticLambda21(this, r14, r21, r15, r17, r19));
        return;
    }

    private /* synthetic */ void lambda$setCollapsed$13(ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        this.listView.setAlpha(r42);
        this.shadowView.setAlpha(AndroidUtilities.lerp(0.0f, 0.5f, r42));
        invalidate();
    }

    private /* synthetic */ void lambda$updateTopMessages$8(ValueAnimator r1) {
        invalidate();
    }

    public static void openDeleteMessage(Context r21, long r22, Utilities.Callback3<Boolean, Boolean, Boolean> r24) {
        AnonymousClass8 r5 = new AnonymousClass8();
        BottomSheet r11 = new BottomSheet(r21, false, r5);
        r11.fixNavigationBar();
        LinearLayout r12 = new LinearLayout(r21);
        r12.setOrientation(1);
        TextView r0 = new TextView(r21);
        r0.setTextSize(1, 20.0f);
        r0.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r5));
        r0.setTypeface(AndroidUtilities.bold());
        r0.setText(LocaleController.getString(R.string.DeleteSingleMessagesTitle));
        r12.addView(r0, LayoutHelper.createLinear(-1, -2, 22.0f, 12.0f, 22.0f, 0.0f));
        HeaderCell r02 = new HeaderCell(r21, r5);
        r02.setText(LocaleController.getString(R.string.DeleteAdditionalActions));
        r12.addView(r02, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 4.0f));
        CheckBoxCell r03 = new CheckBoxCell(r21, 4, 21, true, r5);
        CheckBox2 r04 = r03.getCheckBoxRound();
        int r9 = Theme.key_switch2TrackChecked;
        int r10 = Theme.key_radioBackground;
        int r13 = Theme.key_checkboxCheck;
        r04.setColor(r9, r10, r13);
        r03.setText(LocaleController.getString(R.string.DeleteReportSpam), null, false, true);
        r03.setOnClickListener(new LiveCommentsView$$ExternalSyntheticLambda23(r03));
        int r15 = Theme.key_listSelector;
        r03.setBackground(Theme.createSelectorDrawable(Theme.getColor(r15, r5), 2));
        r12.addView(r03, LayoutHelper.createLinear(-1, -2));
        CheckBoxCell r05 = new CheckBoxCell(r21, 4, 21, true, r5);
        r05.getCheckBoxRound().setColor(r9, r10, r13);
        r05.setText(LocaleController.formatString(R.string.DeleteAllFrom, new Object[]{DialogObject.getName(r22)}), null, false, true);
        r05.setOnClickListener(new LiveCommentsView$$ExternalSyntheticLambda24(r05));
        r05.setBackground(Theme.createSelectorDrawable(Theme.getColor(r15, r5), 2));
        r12.addView(r05, LayoutHelper.createLinear(-1, -2));
        CheckBoxCell r06 = new CheckBoxCell(r21, 4, 21, true, r5);
        r06.getCheckBoxRound().setColor(r9, r10, r13);
        r06.setText(LocaleController.formatString(R.string.DeleteBan, new Object[]{DialogObject.getName(r22)}), null, false, false);
        r06.setOnClickListener(new LiveCommentsView$$ExternalSyntheticLambda25(r06));
        r06.setBackground(Theme.createSelectorDrawable(Theme.getColor(r15, r5), 2));
        r12.addView(r06, LayoutHelper.createLinear(-1, -2));
        TextInfoPrivacyCell r4 = new TextInfoPrivacyCell(r21, r5);
        r4.setBackgroundColor(-16777216);
        r4.setFixedSize(12);
        r12.addView(r4, LayoutHelper.createLinear(-1, -2));
        FrameLayout r42 = new FrameLayout(r21);
        ButtonWithCounterView r132 = new ButtonWithCounterView(r21, r5);
        r132.setText(LocaleController.getString(R.string.DeleteProceedBtn), false);
        r132.setOnClickListener(new LiveCommentsView$$ExternalSyntheticLambda26(r03, r05, r06, r24, r11));
        r42.addView(r132, LayoutHelper.createFrame(-1, 48.0f, 119, 16.0f, 16.0f, 16.0f, 16.0f));
        r12.addView(r42, LayoutHelper.createLinear(-1, -2));
        r11.setCustomView(r12);
        r11.show();
    }

    private void pollStars() {
        if (this.inputCall != null) goto L5;
        return;
    L5:
        if (this.polling == true) goto L10;
        AndroidUtilities.cancelRunOnUIThread(this.pollStarsRunnable);
        this.polling = true;
        TL_phone.getGroupCallStars r0 = new TL_phone.getGroupCallStars();
        r0.call = this.inputCall;
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r0, new AiTonesController$$ExternalSyntheticLambda0(), new LiveCommentsView$$ExternalSyntheticLambda9(this, r0));
        return;
    }

    private void removeTopSenders() {
        Runnable r0 = this.removeTopSendersRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.removeTopSendersRunnable = null;
    L5:
        int r02 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        int r1 = this.topMessages.size() - 1;
    L6:
        if (r1 < 0) goto L11;
        if (this.topMessages.get(r1).isExpired(r02) == false) goto L10;
        this.topMessages.remove(r1);
    L10:
        r1 = r1 - 1;
        goto L6
    L11:
        this.lastNow = r02;
        Collections.sort(this.topMessages, new LiveCommentsView$$ExternalSyntheticLambda0(this));
        this.topAdapter.update(true);
        updateTopMessages(true);
        scheduleRemovingTopSenders();
    }

    private void scheduleRemovingTopSenders() {
        Runnable r0 = this.removeTopSendersRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.removeTopSendersRunnable = null;
    L5:
        int r02 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        ArrayList<TopSender> r1 = this.topMessages;
        int r2 = r1.size();
        int r5 = 0;
        long r6 = Long.MAX_VALUE;
    L6:
        if (r5 >= r2) goto L9;
        TopSender r8 = r1.get(r5);
        r5 = r5 + 1;
        r6 = Math.min(r6, r8.expiresAfter(r02) * 1000);
        goto L6
    L9:
        if (r6 < LongCompanionObject.MAX_VALUE) goto L11;
        return;
    L11:
        LiveCommentsView$$ExternalSyntheticLambda17 r03 = new LiveCommentsView$$ExternalSyntheticLambda17(this);
        this.removeTopSendersRunnable = r03;
        AndroidUtilities.runOnUIThread(r03, r6);
    }

    private int sortTopMessages(TopSender r1, TopSender r2) {
        return r2.lastSentDate - r1.lastSentDate;
    }

    private void updateMessageId(int r5, int r6) {
        ArrayList<Message> r4 = this.messages;
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L11;
        Message r2 = r4.get(r1);
        r1 = r1 + 1;
        Message r22 = r2;
        if (r22.id != r5) goto L3;
        r22.id = r6;
        return;
    }

    private void updateMessagesPlaces() {
        this.topPlaces.clear();
        ArrayList r0 = new ArrayList();
        ArrayList<TL_phone.groupCallDonor> r1 = this.topDonors;
        if (r1 == null) goto L5;
        r0.addAll(r1);
    L5:
        Collections.sort(r0, new LiveCommentsView$$ExternalSyntheticLambda11());
        int r12 = r0.size();
        int r2 = 0;
        int r3 = Integer.MIN_VALUE;
        int r4 = 0;
        int r5 = 0;
    L6:
        if (r5 >= r12) goto L14;
        Object r6 = r0.get(r5);
        r5 = r5 + 1;
        TL_phone.groupCallDonor r62 = (TL_phone.groupCallDonor) r6;
        int r7 = (int) r62.stars;
        if (r7 == r3) goto L11;
        r4 = r4 + 1;
        r3 = r7;
    L11:
        if (r4 > 3) goto L14;
        this.topPlaces.put(Long.valueOf(DialogObject.getPeerDialogId(r62.peer_id)), Integer.valueOf(r4));
    L14:
        int r02 = 0;
    L16:
        if (r02 >= this.listView.getChildCount()) goto L25;
        View r13 = this.listView.getChildAt(r02);
        if ((r13 instanceof LiveCommentView) == false) goto L24;
        LiveCommentView r14 = (LiveCommentView) r13;
        if (LiveCommentView.m19355$$Nest$fgetmessage(r14) == null) goto L24;
        int r32 = getPlace(LiveCommentView.m19355$$Nest$fgetmessage(r14).dialogId);
        if (r32 == LiveCommentView.m19355$$Nest$fgetmessage(r14).place) goto L24;
        LiveCommentView.m19355$$Nest$fgetmessage(r14).place = r32;
        r14.set(LiveCommentView.m19355$$Nest$fgetmessage(r14));
    L24:
        r02 = r02 + 1;
        goto L16
    L25:
        int r03 = 0;
    L27:
        if (r03 >= this.messages.size()) goto L32;
        Message r15 = this.messages.get(r03);
        int r33 = getPlace(r15.dialogId);
        if (r33 == r15.place) goto L31;
        r15.place = r33;
    L31:
        r03 = r03 + 1;
        goto L27
    L32:
        int r04 = 0;
    L34:
        if (r04 >= this.topListView.getChildCount()) goto L44;
        View r16 = this.topListView.getChildAt(r04);
        if ((r16 instanceof LiveTopSenderView) == false) goto L42;
        LiveTopSenderView r17 = (LiveTopSenderView) r16;
        if (LiveTopSenderView.m19357$$Nest$fgetsender(r17) == null) goto L42;
        int r34 = getPlace(LiveTopSenderView.m19357$$Nest$fgetsender(r17).dialogId);
        if (r34 == LiveTopSenderView.m19357$$Nest$fgetsender(r17).place) goto L42;
        LiveTopSenderView.m19357$$Nest$fgetsender(r17).place = r34;
        r17.set(LiveTopSenderView.m19357$$Nest$fgetsender(r17));
    L42:
        r04 = r04 + 1;
    L44:
        if (r2 >= this.topMessages.size()) goto L49;
        TopSender r05 = this.topMessages.get(r2);
        int r18 = getPlace(r05.dialogId);
        if (r18 == r05.place) goto L48;
        r05.place = r18;
    L48:
        r2 = r2 + 1;
        goto L44
    }

    private void updateTopMessages(boolean r8) {
        if (r8 == true) goto L4;
    L6:
        boolean r0 = this.topMessages.isEmpty();
        this.hasTopMessages = !r0;
        RecyclerListView r1 = this.listView;
        float r2 = 1.0f;
        if (r8 == false) goto L22;
        ViewPropertyAnimator r82 = r1.animate();
        if (this.hasTopMessages == false) goto L11;
        float r02 = 0.0f;
    L12:
        ViewPropertyAnimator r83 = r82.translationY(r02);
        CubicBezierInterpolator r03 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r83.setInterpolator(r03).setUpdateListener(new LiveCommentsView$$ExternalSyntheticLambda19(this)).setDuration(420).start();
        ViewPropertyAnimator r84 = this.topListView.animate();
        if (this.hasTopMessages == false) goto L15;
        float r12 = 0.0f;
    L16:
        ViewPropertyAnimator r85 = r84.translationY(r12);
        if (this.hasTopMessages == true) goto L20;
        r2 = 0.0f;
    L20:
        r85.alpha(r2).setInterpolator(r03).setDuration(420).start();
        return;
    L15:
        r12 = AndroidUtilities.dp(35.0f);
        goto L16
    L11:
        r02 = AndroidUtilities.dp(35.0f);
        goto L12
    L22:
        if (r0 == true) goto L24;
        float r86 = 0.0f;
    L25:
        r1.setTranslationY(r86);
        RecyclerListView r87 = this.topListView;
        if (this.hasTopMessages == false) goto L28;
        float r04 = 0.0f;
    L29:
        r87.setTranslationY(r04);
        RecyclerListView r88 = this.topListView;
        if (this.hasTopMessages == true) goto L33;
        r2 = 0.0f;
    L33:
        r88.setAlpha(r2);
        invalidate();
        return;
    L28:
        r04 = AndroidUtilities.dp(35.0f);
        goto L29
    L24:
        r86 = AndroidUtilities.dp(35.0f);
        goto L25
    L4:
        if (this.hasTopMessages != (!this.topMessages.isEmpty())) goto L6;
    }

    public boolean areSendingStars() {
        if (this.starsBulletin == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void cancelStars() {
        this.localStars = 0;
        onCancelledStarReaction(getDefaultPeerId());
        onStarsButtonCancelled();
        onStarsCountUpdated();
    }

    public void clear() {
        this.messages.clear();
        this.adapter.update(true);
    }

    public void delete(int r7) {
        boolean r0 = false;
        int r1 = 0;
    L4:
        if (r1 >= this.messages.size()) goto L9;
        if (this.messages.get(r1).id == r7) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        Message r72 = this.messages.get(r1);
    L10:
        if (r72 != null) goto L13;
        return;
    L13:
        if (r72.id < 0) goto L15;
    L19:
        int r2 = 0;
    L21:
        if (r2 >= this.topMessages.size()) goto L29;
        if (this.topMessages.get(r2).messages.contains(r72) == true) goto L24;
        r2 = r2 + 1;
        goto L21
    L24:
        this.topMessages.get(r2).messages.remove(r72);
        boolean r73 = this.topMessages.get(r2).messages.isEmpty();
        ArrayList<TopSender> r3 = this.topMessages;
        if (r73 == false) goto L27;
        r3.remove(r2);
        r0 = true;
        goto L29
    L27:
        r3.get(r2).updateLastSentDate();
        scheduleRemovingTopSenders();
    L29:
        this.messages.remove(r1);
        this.adapter.update(true);
        if (r0 == false) goto L37;
        this.lastNow = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        Collections.sort(this.topMessages, new LiveCommentsView$$ExternalSyntheticLambda0(this));
        this.topAdapter.update(true);
        updateMessagesPlaces();
        updateTopMessages(true);
        return;
    L37:
        return;
    L15:
        if (r72.isReaction == false) goto L19;
        long r22 = r72.stars;
        if (r22 <= 0) goto L19;
        this.totalStars -= r22;
        onStarsCountUpdated();
        goto L19
    L9:
        r1 = -1;
        r72 = null;
        goto L10
    }

    public void deleteAllFrom(long r8) {
        int r1 = 0;
        boolean r2 = false;
    L4:
        if (r1 >= this.messages.size()) goto L19;
        if (this.messages.get(r1).dialogId != r8) goto L18;
        Message r3 = this.messages.get(r1);
        int r5 = 0;
    L9:
        if (r5 >= this.topMessages.size()) goto L17;
        if (this.topMessages.get(r5).messages.contains(r3) == true) goto L12;
        r5 = r5 + 1;
        goto L9
    L12:
        this.topMessages.get(r5).messages.remove(r3);
        if (this.topMessages.get(r5).messages.isEmpty() == false) goto L15;
        this.topMessages.remove(r5);
        r2 = true;
        goto L17
    L15:
        scheduleRemovingTopSenders();
    L17:
        this.messages.remove(r1);
        this.adapter.update(true);
        r1 = r1 - 1;
    L18:
        r1 = r1 + 1;
        goto L4
    L19:
        if (r2 == false) goto L27;
        this.lastNow = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        Collections.sort(this.topMessages, new LiveCommentsView$$ExternalSyntheticLambda0(this));
        this.topAdapter.update(true);
        updateMessagesPlaces();
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r13, int r14, Object... r15) {
        if (r13 != NotificationCenter.liveStoryMessageUpdate) goto L21;
        int r132 = 0;
        long r0 = ((Long) r15[0]).longValue();
        TLObject r142 = (TLObject) r15[1];
        boolean r11 = ((Boolean) r15[2]).booleanValue();
        if ((r142 instanceof TL_update.TL_updateGroupCallMessage) == false) goto L13;
        TL_update.TL_updateGroupCallMessage r143 = (TL_update.TL_updateGroupCallMessage) r142;
        TLRPC.InputGroupCall r133 = this.inputCall;
        if (r133 != null) goto L9;
        return;
    L9:
        if (r133.id != r0) goto L24;
        TLRPC.GroupCallMessage r134 = r143.message;
        int r3 = r134.date;
        int r4 = r134.id;
        boolean r5 = r134.from_admin;
        long r6 = DialogObject.getPeerDialogId(r134.from_id);
        TLRPC.GroupCallMessage r135 = r143.message;
        push(r3, r4, r5, r6, r135.message, r135.paid_message_stars, r11);
        return;
    L24:
        return;
    L13:
        if ((r142 instanceof TL_update.TL_updateDeleteGroupCallMessages) == false) goto L25;
        TL_update.TL_updateDeleteGroupCallMessages r144 = (TL_update.TL_updateDeleteGroupCallMessages) r142;
        TLRPC.InputGroupCall r12 = this.inputCall;
        if (r12 != null) goto L17;
        return;
    L17:
        if (r12.id != r0) goto L27;
        ArrayList<Integer> r122 = r144.messages;
        int r145 = r122.size();
    L19:
        if (r132 >= r145) goto L28;
        Integer r152 = r122.get(r132);
        r132 = r132 + 1;
        delete(r152.intValue());
        goto L19
    L28:
        return;
    L27:
        return;
    L25:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r4) {
        if (this.allowTouches == true) goto L6;
        return false;
    L6:
        if (r4.getAction() != 0) goto L11;
        if (r4.getY() >= top()) goto L11;
        return false;
    L11:
        return super.dispatchTouchEvent(r4);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r12, View r13, long r14) {
        RecyclerListView r0 = this.listView;
        if (r13 != r0) goto L10;
        if (r0.getAlpha() > 0.0f) goto L7;
        return true;
    L7:
        float r02 = this.listView.getY() + Math.max(0.0f, this.keyboardOffset - this.listView.getTop());
        r12.saveLayerAlpha(this.listView.getX(), this.listView.getY(), this.listView.getX() + this.listView.getWidth(), this.listView.getY() + this.listView.getHeight(), 255, 31);
        r12.save();
        r12.translate(0.0f, (1.0f - this.listView.getAlpha()) * Math.min((this.listView.getY() + this.listView.getHeight()) - r02, getListViewTop()));
        r12.clipRect(0.0f, r02, getWidth(), getHeight());
        boolean r122 = super.drawChild(r12, r13, r14);
        r12.restore();
        RectF r132 = AndroidUtilities.rectTmp;
        r132.set(0.0f, r02, getWidth(), AndroidUtilities.dp(12.0f) + r02);
        this.gradientClip.draw(r12, r132, 1, 1.0f);
        r132.set(0.0f, (this.listView.getY() + this.listView.getHeight()) - AndroidUtilities.dp(12.0f), getWidth(), this.listView.getBottom() + this.listView.getHeight());
        this.gradientClip.draw(r12, r132, 3, 1.0f);
        r12.restore();
        return r122;
    L10:
        return super.drawChild(r12, r13, r14);
    }

    public LiveCommentView findComment(int r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.listView.getChildCount()) goto L13;
        View r1 = this.listView.getChildAt(r0);
        if ((r1 instanceof LiveCommentView) == false) goto L12;
        LiveCommentView r12 = (LiveCommentView) r1;
        if (LiveCommentView.m19355$$Nest$fgetmessage(r12) == null) goto L12;
        if (LiveCommentView.m19355$$Nest$fgetmessage(r12).id != r4) goto L12;
        return r12;
    L12:
        r0 = r0 + 1;
        goto L4
    L13:
        return null;
    }

    public TLRPC.Peer getDefaultSendAs() {
        return null;
    }

    public int getListViewContentTop() {
        int r0 = this.listView.getHeight();
        int r1 = 0;
    L4:
        if (r1 >= this.listView.getChildCount()) goto L6;
        r0 = Math.min(this.listView.getChildAt(r1).getTop(), r0);
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    public int getMessagesCount() {
        return this.messages.size();
    }

    public long getStarsCount() {
        return this.totalStars + this.localStars;
    }

    public int getUnreadMessagesCount() {
        int r1 = 0;
        if (this.maxReadId >= 0) goto L5;
        return 0;
    L5:
        LivePlayer r0 = this.livePlayer;
        if (r0 != null) goto L8;
        long r2 = 0;
    L9:
        int r02 = 0;
    L11:
        if (r1 >= this.messages.size()) goto L24;
        Message r4 = this.messages.get(r1);
        int r5 = r4.id;
        if (r5 < 0) goto L23;
        if (r5 <= this.maxReadId) goto L23;
        if (r4.fromAdmin == false) goto L19;
    L22:
        r02 = r02 + 1;
        goto L23
    L19:
        if (r4.isReaction == false) goto L22;
        if (r4.stars >= r2) goto L22;
    L23:
        r1 = r1 + 1;
        goto L11
    L24:
        return r02;
    L8:
        r2 = r0.getSendPaidMessagesStars();
        goto L9
    }

    public boolean isAdmin() {
        if (getDefaultPeerId() < 0) goto L5;
    L7:
        long r4 = this.dialogId;
        if (r4 >= 0) goto L10;
        LivePlayer r0 = this.livePlayer;
        if (r0 == null) goto L23;
        TLRPC.InputGroupCall r1 = this.inputCall;
        if (r1 == null) goto L23;
        if (r1.id != r0.getCallId()) goto L23;
        if (this.livePlayer.isCreator() == false) goto L23;
        return true;
    L23:
        return ChatObject.canManageCalls(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId)));
    L10:
        if (r4 != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L12;
        return true;
    L12:
        return false;
    L5:
        if (getDefaultPeerId() == this.dialogId) goto L7;
        return false;
    }

    public boolean isCollapsed() {
        return this.collapsed;
    }

    public abstract boolean isMe(long r1);

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.liveStoryMessageUpdate);
        setAllowTouches(true);
        super.onAttachedToWindow();
        if (this.inputCall == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(this.pollStarsRunnable);
        AndroidUtilities.runOnUIThread(this.pollStarsRunnable);
        return;
    }

    public abstract void onCancelledStarReaction(long r1);

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.liveStoryMessageUpdate);
        super.onDetachedFromWindow();
        if (this.inputCall == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(this.pollStarsRunnable);
        return;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r3) {
        if (r3.getAction() != 0) goto L9;
        if (r3.getY() >= top()) goto L9;
        return false;
    L9:
        return super.onInterceptTouchEvent(r3);
    }

    public abstract void onMessagesCountUpdated();

    public abstract void onStarReaction(long r1, int r3, int r4);

    public abstract void onStarsButtonCancelled();

    public abstract void onStarsButtonPressed(long r1, boolean r3);

    public abstract void onStarsCountUpdated();

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r3) {
        if (r3.getAction() != 0) goto L9;
        if (r3.getY() >= top()) goto L9;
        return false;
    L9:
        return super.onTouchEvent(r3);
    }

    public void openStarsSheet(boolean r15) {
        this.closeBulletin.run();
        ArrayList r8 = new ArrayList();
        if (this.topDonors == null) goto L8;
        int r0 = 0;
    L6:
        if (r0 >= this.topDonors.size()) goto L8;
        TL_phone.groupCallDonor r1 = this.topDonors.get(r0);
        TLRPC.TL_messageReactor r2 = new TLRPC.TL_messageReactor();
        r2.anonymous = r1.anonymous;
        r2.my = r1.my;
        r2.count = (int) r1.stars;
        r2.peer_id = r1.peer_id;
        r8.add(r2);
        r0 = r0 + 1;
    L8:
        long r02 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        TLRPC.Peer r22 = getDefaultSendAs();
        if (r22 == null) goto L11;
        r02 = DialogObject.getPeerDialogId(r22);
    L11:
        StarsReactionsSheet r12 = new StarsReactionsSheet(getContext(), this.currentAccount, this.dialogId, null, null, r8, !r15, true, r02, new AnonymousClass6(this));
        r12.setLiveCommentsView(this);
        r12.setOnSend(new LiveCommentsView$$ExternalSyntheticLambda10(this));
        r12.show();
    }

    public void push(int r14, int r15, boolean r16, long r17, TLRPC.TL_textWithEntities r19, long r20, boolean r22) {
        int r4 = 0;
        int r5 = 0;
    L4:
        if (r5 >= this.messages.size()) goto L9;
        if (this.messages.get(r5).id == r15) goto L7;
        r5 = r5 + 1;
        goto L4
    L7:
        return;
    L9:
        int r52 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        Message r6 = new Message();
        r6.date = r14;
        r6.fromAdmin = r16;
        r6.dialogId = r17;
        r6.text = r19;
        r6.stars = r20;
        r6.id = r15;
        r6.isReaction = TextUtils.isEmpty(r19.text);
        int r142 = HighlightMessageSheet.getTierOption(this.currentAccount, (int) r6.stars, HighlightMessageSheet.TIER_PERIOD);
        TL_phone.groupCallDonor r7 = null;
        boolean r8 = true;
        if (r6.stars <= 0) goto L30;
        if (r142 <= 0) goto L30;
        if ((r52 - r6.date) > r142) goto L30;
        int r143 = 0;
    L16:
        if (r143 >= this.topMessages.size()) goto L21;
        if (this.topMessages.get(r143).dialogId == r17) goto L19;
        r143 = r143 + 1;
        goto L16
    L19:
        TopSender r144 = this.topMessages.get(r143);
    L22:
        if (r144 != null) goto L24;
        r144 = new TopSender();
        r144.currentAccount = this.currentAccount;
        r144.dialogId = r17;
        r144.messages.add(r6);
        this.topMessages.add(0, r144);
        boolean r1 = true;
    L25:
        r144.updateLastSentDate();
        updateTopMessages(true);
        scheduleRemovingTopSenders();
        this.lastNow = r52;
        Collections.sort(this.topMessages, new LiveCommentsView$$ExternalSyntheticLambda0(this));
        if (r22 == true) goto L28;
        this.topAdapter.update(true);
    L28:
        if (r1 == false) goto L30;
        this.topLayoutManager.scrollToPosition(0);
        goto L30
    L24:
        r144.messages.add(r6);
        this.topListView.invalidateViews();
        r1 = false;
        goto L25
    L21:
        r144 = null;
    L30:
        if (r22 == true) goto L37;
        if (r6.isReaction == false) goto L37;
        long r12 = r6.stars;
        if (r12 <= 0) goto L37;
        this.totalStars += r12;
        onStarsCountUpdated();
    L37:
        if (r6.id < 0) goto L44;
        int r145 = this.messages.size() - 1;
    L39:
        if (r145 < 0) goto L44;
        if (r6.id < this.messages.get(r145).id) goto L42;
        r145 = r145 - 1;
        goto L39
    L42:
        int r146 = r145 + 1;
    L45:
        this.messages.add(r146, r6);
        if (r22 == false) goto L48;
    L51:
        if (r146 > 0) goto L60;
        if (r22 == true) goto L60;
        if (this.listView.canScrollVertically(1) == true) goto L56;
    L57:
        this.layoutManager.scrollToPositionWithOffset(0, AndroidUtilities.dp(100.0f));
        int r147 = r6.id;
        if (r147 <= 0) goto L60;
        this.maxReadId = r147;
        goto L60
    L56:
        if (r6.id < 0) goto L57;
    L60:
        invalidate();
        onMessagesCountUpdated();
        if (r22 == true) goto L86;
        if (r15 <= 0) goto L86;
        if (r6.stars <= 0) goto L86;
        int r148 = 0;
    L67:
        if (r148 >= this.topDonors.size()) goto L72;
        if (DialogObject.getPeerDialogId(this.topDonors.get(r148).peer_id) == r6.dialogId) goto L70;
        r148 = r148 + 1;
        goto L67
    L70:
        r7 = this.topDonors.get(r148);
    L72:
        if (r7 != null) goto L85;
        r7 = new TL_phone.groupCallDonor();
        if (UserConfig.getInstance(this.currentAccount).getClientUserId() == r6.dialogId) goto L77;
        r8 = false;
    L77:
        r7.my = r8;
        r7.peer_id = MessagesController.getInstance(this.currentAccount).getPeer(r6.dialogId);
        r7.stars = 0;
    L79:
        if (r4 >= this.topMessages.size()) goto L84;
        if (this.topMessages.get(r4).dialogId != r6.dialogId) goto L83;
        this.topMessages.get(r4).getStars();
        r7.stars += TopSender.m19358$$Nest$fgetmax_stars(this.topMessages.get(r4));
    L83:
        r4 = r4 + 1;
        goto L79
    L84:
        this.topDonors.add(r7);
    L85:
        long r0 = r7.stars;
        long r2 = r6.stars;
        long r02 = r0 + r2;
        r7.stars = r02;
        onStarReaction(r6.dialogId, (int) r02, (int) r2);
    L86:
        updateMessagesPlaces();
        if (r22 == false) goto L89;
        AndroidUtilities.cancelRunOnUIThread(this.updateAdapters);
        AndroidUtilities.runOnUIThread(this.updateAdapters, 100);
    L89:
        saveHistory();
        return;
    L48:
        if (this.messages.size() <= 2000) goto L50;
        ArrayList<Message> r13 = this.messages;
        r13.subList(CastStatusCodes.AUTHENTICATION_FAILED, r13.size()).clear();
    L50:
        this.adapter.update(true);
    L44:
        r146 = 0;
        goto L45
    }

    public void saveHistory() {
        LivePlayer r0 = this.livePlayer;
        if (r0 == null) goto L6;
        r0.messages = this.messages;
        r0.topMessages = this.topMessages;
        return;
    }

    public int send(long r17, TLRPC.TL_textWithEntities r19, long r20) {
        int r2 = UserConfig.getInstance(this.currentAccount).getNewMessageId();
        TL_phone.sendGroupCallMessage r22 = new TL_phone.sendGroupCallMessage();
        r22.call = this.inputCall;
        r22.message = r19;
        if (r20 <= 0) goto L5;
        r22.flags |= 1;
        r22.allow_paid_stars = r20;
    L5:
        r22.random_id = Utilities.random.nextLong();
        r22.flags |= 2;
        r22.send_as = MessagesController.getInstance(this.currentAccount).getInputPeer(r17);
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r22, new LiveCommentsView$$ExternalSyntheticLambda18(this, r22, r2, r20, r17, r19));
        if (this.topDonors == null) goto L19;
        if (r20 <= 0) goto L19;
        int r1 = 0;
    L10:
        if (r1 >= this.topDonors.size()) goto L15;
        if (this.topDonors.get(r1).my == true) goto L13;
        r1 = r1 + 1;
        goto L10
    L13:
        TL_phone.groupCallDonor r12 = this.topDonors.get(r1);
    L16:
        if (r12 == null) goto L18;
        r12.stars += r20;
        goto L19
    L18:
        TL_phone.groupCallDonor r13 = new TL_phone.groupCallDonor();
        r13.my = true;
        r13.anonymous = false;
        r13.peer_id = MessagesController.getInstance(this.currentAccount).getPeer(r17);
        r13.stars = r20;
        this.topDonors.add(r13);
        goto L19
    L15:
        r12 = null;
    L19:
        int r14 = ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime();
        if (r17 != this.dialogId) goto L22;
    L25:
        int r23 = r2;
        boolean r3 = true;
    L26:
        push(r14, r23, r3, r17, r19, r20, false);
        int r32 = r23;
        setCollapsed(false, true);
        return r32;
    L22:
        if (isAdmin() == true) goto L25;
        r23 = r2;
        r3 = false;
        goto L26
    }

    public int send(TLRPC.TL_textWithEntities r7, long r8) {
        return send(getDefaultPeerId(), r7, r8);
    }

    public void sendStars(long r15, boolean r17) {
        Bulletin r0 = this.starsBulletin;
        if (r0 != null) goto L5;
    L6:
        DarkThemeResourceProvider r02 = new DarkThemeResourceProvider();
        Bulletin.TwoLineAnimatedLottieLayout r3 = new Bulletin.TwoLineAnimatedLottieLayout(getContext(), r02);
        this.bulletinLayout = r3;
        r3.setAnimation(R.raw.stars_topup, new String[0]);
        this.bulletinLayout.titleTextView.setText(getStarsToastTitle());
        Bulletin.UndoButton r32 = new Bulletin.UndoButton(getContext(), true, false, r02);
        this.bulletinButton = r32;
        r32.setText(LocaleController.getString(R.string.StarsSentUndo));
        this.bulletinButton.setUndoAction(new LiveCommentsView$$ExternalSyntheticLambda12(this));
        Bulletin.TimerView r33 = new Bulletin.TimerView(getContext(), r02);
        this.timerView = r33;
        r33.timeLeft = 5000;
        r33.setColor(Theme.getColor(Theme.key_undo_cancelColor, r02));
        this.bulletinButton.addView(this.timerView, LayoutHelper.createFrame(20, 20.0f, 21, 0.0f, 0.0f, 12.0f, 0.0f));
        this.bulletinButton.undoTextView.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(30.0f), AndroidUtilities.dp(8.0f));
        this.bulletinLayout.setButton(this.bulletinButton);
        Bulletin r03 = BulletinFactory.of(this.topBulletinContainer, r02).create(this.bulletinLayout, -1);
        this.starsBulletin = r03;
        r03.hideAfterBottomSheet = false;
        r03.show(true);
        this.starsBulletin.setOnHideListener(this.closeBulletin);
    L7:
        this.localStars += r15;
        onCancelledStarReaction(getDefaultPeerId());
        onStarReaction(getDefaultPeerId(), getTotalMyStars(), (int) this.localStars);
        this.bulletinLayout.titleTextView.setText(getStarsToastTitle());
        this.bulletinLayout.subtitleTextView.setText(getStarsToastSubtitle());
        this.timerView.timeLeft = 5000;
        AndroidUtilities.cancelRunOnUIThread(this.closeBulletin);
        AndroidUtilities.runOnUIThread(this.closeBulletin, 5000);
        onStarsButtonPressed(this.localStars, r17);
        onStarsCountUpdated();
        return;
    L5:
        if (r0.isShowing() == true) goto L7;
        goto L6
    }

    public void setAllowTouches(boolean r1) {
        this.allowTouches = r1;
    }

    public void setCollapsed(boolean r4, boolean r5) {
        if (r5 == true) goto L4;
    L6:
        this.collapsed = r4;
        ValueAnimator r0 = this.collapseAnimator;
        if (r0 == null) goto L9;
        r0.cancel();
        this.collapseAnimator = null;
    L9:
        this.listView.invalidate();
        float r02 = 1.0f;
        if (r5 == false) goto L15;
        float r52 = this.listView.getAlpha();
        if (r4 == false) goto L14;
        r02 = 0.0f;
    L14:
        ValueAnimator r53 = ValueAnimator.ofFloat(new float[]{r52, r02});
        this.collapseAnimator = r53;
        r53.addUpdateListener(new LiveCommentsView$$ExternalSyntheticLambda16(this));
        this.collapseAnimator.addListener(new AnonymousClass7(this, r4));
        this.collapseAnimator.setDuration(420);
        this.collapseAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.collapseAnimator.start();
    L23:
        invalidate();
        return;
    L15:
        View r54 = this.shadowView;
        if (r4 == false) goto L18;
        float r2 = 0.0f;
    L19:
        r54.setAlpha(r2);
        RecyclerListView r55 = this.listView;
        if (r4 == false) goto L22;
        r02 = 0.0f;
    L22:
        r55.setAlpha(r02);
        goto L23
    L18:
        r2 = 0.5f;
        goto L19
    L4:
        if (this.collapsed != r4) goto L6;
    }

    public void setKeyboardOffset(float r5, float r6, float r7) {
        this.keyboardT = r5;
        this.keyboardOffset = r6;
        if (Math.abs(this.keyboardFinalOffset - r7) <= 0.1f) goto L7;
        this.keyboardFinalOffset = r7;
        this.listView.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(7.5f) + Math.max(0, ((int) r7) - this.listView.getTop()), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(7.5f));
        if (this.listView.canScrollVertically(1) == true) goto L7;
        this.layoutManager.scrollToPositionWithOffset(0, AndroidUtilities.dp(100.0f));
    L7:
        setTranslationY(-this.keyboardOffset);
        invalidate();
    }

    public void setLivePlayer(LivePlayer r6) {
        if (this.livePlayer != null) goto L5;
        boolean r0 = true;
    L6:
        this.livePlayer = r6;
        if (r0 == false) goto L23;
        if (r6 == null) goto L24;
        ArrayList<Message> r02 = r6.messages;
        if (r02 == null) goto L25;
        ArrayList<TopSender> r3 = r6.topMessages;
        if (r3 == null) goto L26;
        ArrayList<Message> r4 = this.messages;
        if (r02 != r4) goto L16;
        return;
    L16:
        if (r3 != this.topMessages) goto L18;
        return;
    L18:
        if (r4.isEmpty() == true) goto L20;
        return;
    L20:
        if (this.topMessages.isEmpty() == false) goto L30;
        this.messages.addAll(r6.messages);
        this.topMessages.addAll(r6.topMessages);
        this.adapter.update(true);
        this.lastNow = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        Collections.sort(this.topMessages, new LiveCommentsView$$ExternalSyntheticLambda0(this));
        this.topAdapter.update(true);
        updateTopMessages(false);
        return;
    L30:
        return;
    L26:
        return;
    L25:
        return;
    L24:
        return;
    L23:
        return;
    L5:
        r0 = false;
        goto L6
    }

    public boolean setup(long r6, TLRPC.InputGroupCall r8) {
        TLRPC.InputGroupCall r0 = this.inputCall;
        long r1 = 0;
        if (r0 != null) goto L5;
        long r3 = 0;
    L6:
        if (r8 == null) goto L10;
        r1 = r8.id;
    L10:
        if (r3 == r1) goto L12;
        clear();
        boolean r02 = true;
    L13:
        this.dialogId = r6;
        this.inputCall = r8;
        if (r02 == false) goto L20;
        this.closeBulletin.run();
        Runnable r5 = this.pollStarsRunnable;
        if (r8 != null) goto L19;
        AndroidUtilities.cancelRunOnUIThread(r5);
        return r02;
    L19:
        r5.run();
    L20:
        return r02;
    L12:
        r02 = false;
        goto L13
    L5:
        r3 = r0.id;
        goto L6
    }

    public float top() {
        return this.listView.getY() + Math.max(Math.max(0.0f, this.keyboardOffset - this.listView.getTop()), getListViewContentTop());
    }

    public void updatedMinStars() {
        LivePlayer r0 = this.livePlayer;
        if (r0 != null) goto L5;
        long r02 = 0;
    L7:
        if (this.lastMinStars == r02) goto L10;
        this.adapter.update(true);
        return;
    L10:
        return;
    L5:
        r02 = r0.getSendPaidMessagesStars();
        goto L7
    }
}
