package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.SpannableString;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.AppDataDirGuesser;
import com.exteragram.messenger.ExteraConfig;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.MentionsAdapter;
import org.telegram.ui.Adapters.PaddedListAdapter;
import org.telegram.ui.Business.QuickRepliesActivity;
import org.telegram.ui.Cells.ContextLinkCell;
import org.telegram.ui.Cells.MentionCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.ContentPreviewViewer;
import org.telegram.ui.PhotoViewer;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes7.dex */
public abstract class MentionsContainerView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private MentionsAdapter adapter;
    private int animationIndex;
    private BlurredBackgroundDrawable backgroundDrawable;
    WeakReference<BaseFragment> baseFragment;
    private PhotoViewer.PhotoViewerProvider botContextProvider;
    private ArrayList<Object> botContextResults;
    private final RectF clipBounds;
    private final Path clipPath;
    private Integer color;
    private float containerBottom;
    private float containerPadding;
    private float containerTop;
    private WeakReference<Delegate> delegate;
    private ExtendedGridLayoutManager gridLayoutManager;
    private float hideT;
    private boolean ignoreLayout;
    private LinearLayoutManager linearLayoutManager;
    private MentionsListView listView;
    private boolean listViewHiding;
    private float listViewPadding;
    private SpringAnimation listViewTranslationAnimator;
    private RecyclerListView.OnItemClickListener mentionsOnItemClickListener;
    private PaddedListAdapter paddedAdapter;
    private Paint paint;
    private Rect rect;
    private final Theme.ResourcesProvider resourcesProvider;
    private int scrollRangeUpdateTries;
    private boolean scrollToFirst;
    private boolean shouldLiftMentions;
    private boolean shown;
    private boolean switchLayoutManagerOnEnd;
    private Runnable updateVisibilityRunnable;

    /* renamed from: org.telegram.ui.Components.MentionsContainerView$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearLayoutManager {
        final /* synthetic */ MentionsContainerView this$0;

        public AnonymousClass1(MentionsContainerView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void setReverseLayout(boolean r2) {
            super.setReverseLayout(r2);
            MentionsListView r1 = MentionsContainerView.m11246$$Nest$fgetlistView(this.this$0);
            if (r2 == false) goto L5;
            int r22 = -1;
        L6:
            r1.setTranslationY(r22 * AndroidUtilities.dp(6.0f));
            return;
        L5:
            r22 = 1;
            goto L6
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.MentionsContainerView$2, reason: invalid class name */
    public class AnonymousClass2 extends ExtendedGridLayoutManager {
        private Size size;
        final /* synthetic */ MentionsContainerView this$0;

        public AnonymousClass2(MentionsContainerView r1, Context r2, int r3, boolean r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
            this.size = new Size();
        }

        @Override // org.telegram.ui.Components.ExtendedGridLayoutManager
        public int getFlowItemCount() {
            if (MentionsContainerView.m11240$$Nest$fgetadapter(this.this$0).getBotContextSwitch() != null) goto L10;
            if (MentionsContainerView.m11240$$Nest$fgetadapter(this.this$0).getBotWebViewSwitch() != null) goto L10;
            return super.getFlowItemCount();
        L10:
            return getItemCount() - 1;
        }

        @Override // org.telegram.ui.Components.ExtendedGridLayoutManager
        public Size getSizeForItem(int r6) {
            Size r0 = this.size;
            int r1 = 0;
            r0.full = false;
            if (r6 != 0) goto L6;
            r0.width = getWidth();
            this.size.height = MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0).getPadding();
            Size r5 = this.size;
            r5.full = true;
            return r5;
        L6:
            int r02 = r6 - 1;
            if (MentionsContainerView.m11240$$Nest$fgetadapter(this.this$0).getBotContextSwitch() == null) goto L9;
        L12:
            Size r03 = this.size;
            r03.width = 0.0f;
            r03.height = 0.0f;
            Object r62 = MentionsContainerView.m11240$$Nest$fgetadapter(this.this$0).getItem(r6);
            if ((r62 instanceof TLRPC.BotInlineResult) == false) goto L61;
            TLRPC.BotInlineResult r63 = (TLRPC.BotInlineResult) r62;
            TLRPC.Document r04 = r63.document;
            if (r04 == null) goto L34;
            TLRPC.PhotoSize r05 = FileLoader.getClosestPhotoSizeWithSize(r04.thumbs, 90);
            Size r2 = this.size;
            float r3 = 100.0f;
            if (r05 == null) goto L19;
            float r4 = r05.w;
        L20:
            r2.width = r4;
            if (r05 == null) goto L23;
            r3 = r05.h;
        L23:
            r2.height = r3;
        L25:
            if (r1 >= r63.document.attributes.size()) goto L61;
            TLRPC.DocumentAttribute r06 = r63.document.attributes.get(r1);
            if ((r06 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L32;
            if ((r06 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L32;
            r1 = r1 + 1;
        L32:
            Size r64 = this.size;
            r64.width = r06.w;
            r64.height = r06.h;
            goto L61
        L19:
            r4 = 100.0f;
            goto L20
        L34:
            if (r63.content == null) goto L45;
        L36:
            if (r1 >= r63.content.attributes.size()) goto L61;
            TLRPC.DocumentAttribute r07 = r63.content.attributes.get(r1);
            if ((r07 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L43;
            if ((r07 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L43;
            r1 = r1 + 1;
        L43:
            Size r65 = this.size;
            r65.width = r07.w;
            r65.height = r07.h;
            goto L61
        L45:
            if (r63.thumb != null) goto L47;
            TLRPC.Photo r66 = r63.photo;
            if (r66 == null) goto L61;
            if (FileLoader.getClosestPhotoSizeWithSize(r66.sizes, AndroidUtilities.getPhotoSize()) == null) goto L61;
            Size r08 = this.size;
            r08.width = r6.w;
            r08.height = r6.h;
        L47:
            if (r1 >= r63.thumb.attributes.size()) goto L61;
            TLRPC.DocumentAttribute r09 = r63.thumb.attributes.get(r1);
            if ((r09 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L54;
            if ((r09 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L54;
            r1 = r1 + 1;
        L54:
            Size r67 = this.size;
            r67.width = r09.w;
            r67.height = r09.h;
        L61:
            return this.size;
        L9:
            if (MentionsContainerView.m11240$$Nest$fgetadapter(this.this$0).getBotWebViewSwitch() != null) goto L12;
            r6 = r02;
            goto L12
        }
    }

    /* renamed from: org.telegram.ui.Components.MentionsContainerView$3, reason: invalid class name */
    public class AnonymousClass3 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ MentionsContainerView this$0;

        public AnonymousClass3(MentionsContainerView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r4) {
            if (r4 != 0) goto L5;
            return 100;
        L5:
            Object r1 = MentionsContainerView.m11240$$Nest$fgetadapter(this.this$0).getItem(r4 - 1);
            if ((r1 instanceof TLRPC.TL_inlineBotSwitchPM) == false) goto L9;
            return 100;
        L9:
            if ((r1 instanceof TLRPC.Document) == false) goto L13;
            return 20;
        L13:
            if (MentionsContainerView.m11240$$Nest$fgetadapter(this.this$0).getBotContextSwitch() == null) goto L15;
        L16:
            r4 = r4 - 1;
        L18:
            return MentionsContainerView.m11243$$Nest$fgetgridLayoutManager(this.this$0).getSpanSizeForItem(r4);
        L15:
            if (MentionsContainerView.m11240$$Nest$fgetadapter(this.this$0).getBotWebViewSwitch() == null) goto L18;
            goto L16
        }
    }

    /* renamed from: org.telegram.ui.Components.MentionsContainerView$4, reason: invalid class name */
    public class AnonymousClass4 implements MentionsAdapter.MentionsAdapterDelegate {
        final /* synthetic */ MentionsContainerView this$0;

        public AnonymousClass4(MentionsContainerView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Adapters.MentionsAdapter.MentionsAdapterDelegate
        public void needChangePanelVisibility(boolean r4) {
            boolean r2 = false;
            if (this.this$0.getNeededLayoutManager() != this.this$0.getCurrentLayoutManager()) goto L5;
        L11:
            if (r4 == true) goto L13;
        L15:
            if (r4 == true) goto L17;
        L19:
            r2 = r4;
        L20:
            this.this$0.updateVisibility(r2);
            return;
        L17:
            if (MentionsContainerView.m11240$$Nest$fgetadapter(this.this$0).getItemCountInternal() > 0) goto L19;
        L13:
            if (this.this$0.canOpen() == true) goto L15;
            r4 = false;
            goto L15
        L5:
            if (this.this$0.canOpen() == false) goto L11;
            int r0 = MentionsContainerView.m11240$$Nest$fgetadapter(this.this$0).getLastItemCount();
            MentionsContainerView r1 = this.this$0;
            if (r0 <= 0) goto L10;
            MentionsContainerView.m11255$$Nest$fputswitchLayoutManagerOnEnd(r1, true);
            this.this$0.updateVisibility(false);
            return;
        L10:
            MentionsContainerView.m11246$$Nest$fgetlistView(r1).setLayoutManager(this.this$0.getNeededLayoutManager());
            goto L11
        }

        @Override // org.telegram.ui.Adapters.MentionsAdapter.MentionsAdapterDelegate
        public void onContextClick(TLRPC.BotInlineResult r1) {
            this.this$0.onContextClick(r1);
        }

        @Override // org.telegram.ui.Adapters.MentionsAdapter.MentionsAdapterDelegate
        public void onContextSearch(boolean r1) {
            this.this$0.onContextSearch(r1);
        }

        @Override // org.telegram.ui.Adapters.MentionsAdapter.MentionsAdapterDelegate
        public void onItemCountUpdate(int r3, int r4) {
            if (MentionsContainerView.m11246$$Nest$fgetlistView(this.this$0).getLayoutManager() != MentionsContainerView.m11243$$Nest$fgetgridLayoutManager(this.this$0)) goto L5;
            return;
        L5:
            if (MentionsContainerView.m11250$$Nest$fgetshown(this.this$0) == false) goto L17;
            AndroidUtilities.cancelRunOnUIThread(MentionsContainerView.m11251$$Nest$fgetupdateVisibilityRunnable(this.this$0));
            Runnable r32 = MentionsContainerView.m11251$$Nest$fgetupdateVisibilityRunnable(this.this$0);
            WeakReference<BaseFragment> r42 = this.this$0.baseFragment;
            if (r42 != null) goto L9;
        L13:
            long r0 = 100;
        L14:
            AndroidUtilities.runOnUIThread(r32, r0);
            return;
        L9:
            if (r42.get() == null) goto L13;
            if (this.this$0.baseFragment.get().getFragmentBeginToShow() == false) goto L13;
            r0 = 0;
            goto L14
        }
    }

    /* renamed from: org.telegram.ui.Components.MentionsContainerView$5, reason: invalid class name */
    public class AnonymousClass5 extends PhotoViewer.EmptyPhotoViewerProvider {
        final /* synthetic */ MentionsContainerView this$0;

        public AnonymousClass5(MentionsContainerView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject r4, TLRPC.FileLocation r5, int r6, boolean r7, boolean r8) {
            if (r6 >= 0) goto L5;
        L19:
            return null;
        L5:
            if (r6 >= MentionsContainerView.m11241$$Nest$fgetbotContextResults(this.this$0).size()) goto L19;
            int r52 = this.this$0.getListView().getChildCount();
            Object r62 = MentionsContainerView.m11241$$Nest$fgetbotContextResults(this.this$0).get(r6);
            int r82 = 0;
        L8:
            if (r82 >= r52) goto L19;
            View r0 = this.this$0.getListView().getChildAt(r82);
            if ((r0 instanceof ContextLinkCell) == false) goto L14;
            ContextLinkCell r1 = (ContextLinkCell) r0;
            if (r1.getResult() != r62) goto L14;
            ImageReceiver r12 = r1.getPhotoImage();
        L15:
            if (r12 != null) goto L16;
            r82 = r82 + 1;
            goto L8
        L16:
            int[] r42 = new int[2];
            r0.getLocationInWindow(r42);
            PhotoViewer.PlaceProviderObject r53 = new PhotoViewer.PlaceProviderObject();
            r53.viewX = r42[0];
            r53.viewY = r42[1];
            r53.parentView = this.this$0.getListView();
            r53.imageReceiver = r12;
            r53.thumb = r12.getBitmapSafe();
            r53.radius = r12.getRoundRadius(true);
            return r53;
        L14:
            r12 = null;
            goto L15
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void sendButtonPressed(int r1, VideoEditedInfo r2, boolean r3, int r4, int r5, boolean r6) {
            if (r1 >= 0) goto L4;
            return;
        L4:
            if (r1 < MentionsContainerView.m11241$$Nest$fgetbotContextResults(this.this$0).size()) goto L6;
            return;
        L6:
            if (MentionsContainerView.m11242$$Nest$fgetdelegate(this.this$0) != null) goto L8;
            return;
        L8:
            if (MentionsContainerView.m11242$$Nest$fgetdelegate(this.this$0).get() == null) goto L15;
            ((Delegate) MentionsContainerView.m11242$$Nest$fgetdelegate(this.this$0).get()).sendBotInlineResult((TLRPC.BotInlineResult) MentionsContainerView.m11241$$Nest$fgetbotContextResults(this.this$0).get(r1), r3, r4);
            return;
        }
    }

    public interface Delegate {
        default void addEmojiToRecent(String r1) {
        }

        Paint.FontMetricsInt getFontMetrics();

        default void onStickerSelected(TLRPC.TL_document r1, String r2, Object r3) {
        }

        void replaceText(int r1, int r2, CharSequence r3, boolean r4);

        default void sendBotInlineResult(TLRPC.BotInlineResult r1, boolean r2, int r3) {
        }
    }

    public class MentionsListView extends RecyclerListView {
        private boolean isDragging;
        private boolean isScrolling;
        private int lastHeight;
        private int lastWidth;
        final /* synthetic */ MentionsContainerView this$0;

        /* renamed from: org.telegram.ui.Components.MentionsContainerView$MentionsListView$1, reason: invalid class name */
        public class AnonymousClass1 extends RecyclerView.OnScrollListener {
            final /* synthetic */ MentionsListView this$1;
            final /* synthetic */ MentionsContainerView val$this$0;

            public AnonymousClass1(MentionsListView r1, MentionsContainerView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView r4, int r5) {
                MentionsListView r42 = this.this$1;
                boolean r0 = false;
                if (r5 == 0) goto L5;
                boolean r2 = true;
            L6:
                MentionsListView.m11258$$Nest$fputisScrolling(r42, r2);
                MentionsListView r3 = this.this$1;
                if (r5 != 1) goto L9;
                r0 = true;
            L9:
                MentionsListView.m11257$$Nest$fputisDragging(r3, r0);
                return;
            L5:
                r2 = false;
                goto L6
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r2, int r3, int r4) {
                RecyclerView.LayoutManager r22 = this.this$1.getLayoutManager();
                ExtendedGridLayoutManager r32 = MentionsContainerView.m11243$$Nest$fgetgridLayoutManager(this.this$1.this$0);
                MentionsListView r42 = this.this$1;
                if (r22 != r32) goto L5;
                int r23 = MentionsContainerView.m11243$$Nest$fgetgridLayoutManager(r42.this$0).findLastVisibleItemPosition();
            L7:
                if (r23 != (-1)) goto L9;
                int r43 = 0;
            L10:
                if (r43 > 0) goto L12;
            L14:
                this.this$1.this$0.onScrolled(!r2.canScrollVertically(-1), true ^ this.this$1.canScrollVertically(1));
                MentionsContainerView.m11256$$Nest$mcheckBackgroundBounds(this.this$1.this$0);
                return;
            L12:
                if (r23 <= (MentionsContainerView.m11240$$Nest$fgetadapter(this.this$1.this$0).getLastItemCount() - 5)) goto L14;
                MentionsContainerView.m11240$$Nest$fgetadapter(this.this$1.this$0).searchForContextBotForNextOffset();
                goto L14
            L9:
                r43 = r23;
                goto L10
            L5:
                r23 = MentionsContainerView.m11245$$Nest$fgetlinearLayoutManager(r42.this$0).findLastVisibleItemPosition();
                goto L7
            }
        }

        /* renamed from: org.telegram.ui.Components.MentionsContainerView$MentionsListView$2, reason: invalid class name */
        public class AnonymousClass2 extends RecyclerView.ItemDecoration {
            final /* synthetic */ MentionsListView this$1;
            final /* synthetic */ MentionsContainerView val$this$0;

            public AnonymousClass2(MentionsListView r1, MentionsContainerView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect r3, View r4, RecyclerView r5, RecyclerView.State r6) {
                int r62 = 0;
                r3.left = 0;
                r3.right = 0;
                r3.top = 0;
                r3.bottom = 0;
                if (r5.getLayoutManager() != MentionsContainerView.m11243$$Nest$fgetgridLayoutManager(this.this$1.this$0)) goto L27;
                int r42 = r5.getChildAdapterPosition(r4);
                if (r42 != 0) goto L8;
                return;
            L8:
                if (MentionsContainerView.m11240$$Nest$fgetadapter(this.this$1.this$0).isStickers() == false) goto L11;
                return;
            L11:
                if (MentionsContainerView.m11240$$Nest$fgetadapter(this.this$1.this$0).getBotContextSwitch() == null) goto L13;
            L16:
                if (r42 == 0) goto L30;
                r42 = r42 - 1;
                if (MentionsContainerView.m11243$$Nest$fgetgridLayoutManager(this.this$1.this$0).isFirstRow(r42) == true) goto L22;
                r3.top = AndroidUtilities.dp(2.0f);
            L22:
                if (MentionsContainerView.m11243$$Nest$fgetgridLayoutManager(this.this$1.this$0).isLastInRow(r42) == true) goto L25;
                r62 = AndroidUtilities.dp(2.0f);
            L25:
                r3.right = r62;
                return;
            L30:
                return;
            L13:
                if (MentionsContainerView.m11240$$Nest$fgetadapter(this.this$1.this$0).getBotWebViewSwitch() != null) goto L16;
                r3.top = AndroidUtilities.dp(2.0f);
                goto L22
            }
        }

        /* renamed from: -$$Nest$fputisDragging, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11257$$Nest$fputisDragging(MentionsListView r0, boolean r1) {
            r0.isDragging = r1;
        }

        /* renamed from: -$$Nest$fputisScrolling, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11258$$Nest$fputisScrolling(MentionsListView r0, boolean r1) {
            r0.isScrolling = r1;
        }

        public MentionsListView(MentionsContainerView r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
            setOnScrollListener(new AnonymousClass1(this, r1));
            addItemDecoration(new AnonymousClass2(this, r1));
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r10) {
            boolean r0 = MentionsContainerView.m11245$$Nest$fgetlinearLayoutManager(this.this$0).getReverseLayout();
            boolean r1 = this.isDragging;
            if (r0 == false) goto L14;
            if (r1 == true) goto L25;
            if (MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0) == null) goto L25;
            if (MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0).paddingView == null) goto L25;
            if (MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0).paddingViewAttached == false) goto L25;
            if (r10.getY() <= MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0).paddingView.getTop()) goto L25;
            return false;
        L25:
            if (this.isScrolling == true) goto L29;
            MotionEvent r4 = r10;
            if (ContentPreviewViewer.getInstance().onInterceptTouchEvent(r4, MentionsContainerView.m11246$$Nest$fgetlistView(this.this$0), 0, null, this.resourcesProvider) == false) goto L30;
            boolean r102 = true;
        L32:
            if (MentionsContainerView.m11240$$Nest$fgetadapter(this.this$0).isStickers() == false) goto L36;
            if (r4.getAction() != 0) goto L36;
        L37:
            MentionsContainerView.m11240$$Nest$fgetadapter(this.this$0).doSomeStickersAction();
        L39:
            if (super.onInterceptTouchEvent(r4) == true) goto L43;
            if (r102 == true) goto L43;
            return false;
        L43:
            return true;
        L36:
            if (r4.getAction() != 2) goto L39;
        L30:
            r102 = false;
            goto L32
        L29:
            r4 = r10;
            goto L30
        L14:
            if (r1 == true) goto L25;
            if (MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0) == null) goto L25;
            if (MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0).paddingView == null) goto L25;
            if (MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0).paddingViewAttached == false) goto L25;
            if (r10.getY() >= MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0).paddingView.getBottom()) goto L25;
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r8, int r9, int r10, int r11, int r12) {
            int r0 = r11 - r9;
            int r1 = r12 - r10;
            boolean r2 = this.this$0.isReversed();
            LinearLayoutManager r3 = this.this$0.getCurrentLayoutManager();
            if (r2 == false) goto L5;
            int r4 = r3.findFirstVisibleItemPosition();
        L6:
            View r5 = r3.findViewByPosition(r4);
            if (r5 == null) goto L13;
            int r52 = r5.getTop();
            if (r2 == false) goto L11;
            int r22 = 0;
        L12:
            int r53 = r52 - r22;
        L14:
            super.onLayout(r8, r9, r10, r11, r12);
            if (MentionsContainerView.m11249$$Nest$fgetscrollToFirst(this.this$0) == false) goto L18;
            MentionsContainerView.m11252$$Nest$fputignoreLayout(this.this$0, true);
            r3.scrollToPositionWithOffset(0, AppDataDirGuesser.PER_USER_RANGE);
            super.onLayout(false, r9, r10, r11, r12);
            MentionsContainerView.m11252$$Nest$fputignoreLayout(this.this$0, false);
            MentionsContainerView.m11254$$Nest$fputscrollToFirst(this.this$0, false);
        L24:
            this.lastHeight = r1;
            this.lastWidth = r0;
            return;
        L18:
            if (r4 == (-1)) goto L24;
            if (r0 != this.lastWidth) goto L24;
            if ((r1 - this.lastHeight) == 0) goto L24;
            MentionsContainerView.m11252$$Nest$fputignoreLayout(this.this$0, true);
            r3.scrollToPositionWithOffset(r4, r53, false);
            super.onLayout(false, r9, r10, r11, r12);
            MentionsContainerView.m11252$$Nest$fputignoreLayout(this.this$0, false);
            goto L24
        L11:
            r22 = this.lastHeight - r1;
            goto L12
        L13:
            r53 = 0;
            goto L14
        L5:
            r4 = r3.findLastVisibleItemPosition();
            goto L6
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r5, int r6) {
            int r62 = View.MeasureSpec.getSize(r6);
            if (MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0) == null) goto L5;
            MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0).setPadding(r62);
        L5:
            MentionsContainerView.m11253$$Nest$fputlistViewPadding(this.this$0, (int) Math.min(AndroidUtilities.dp(126.0f), AndroidUtilities.displaySize.y * 0.22f));
            super.onMeasure(r5, View.MeasureSpec.makeMeasureSpec(r62 + ((int) MentionsContainerView.m11247$$Nest$fgetlistViewPadding(this.this$0)), TLObject.FLAG_30));
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void onScrolled(int r1, int r2) {
            super.onScrolled(r1, r2);
            this.this$0.invalidate();
            MentionsContainerView.m11256$$Nest$mcheckBackgroundBounds(this.this$0);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public boolean onTouchEvent(MotionEvent r4) {
            boolean r0 = MentionsContainerView.m11245$$Nest$fgetlinearLayoutManager(this.this$0).getReverseLayout();
            boolean r1 = this.isDragging;
            if (r0 == false) goto L14;
            if (r1 == true) goto L25;
            if (MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0) == null) goto L25;
            if (MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0).paddingView == null) goto L25;
            if (MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0).paddingViewAttached == false) goto L25;
            if (r4.getY() <= MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0).paddingView.getTop()) goto L25;
            return false;
        L25:
            return super.onTouchEvent(r4);
        L14:
            if (r1 == true) goto L25;
            if (MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0) == null) goto L25;
            if (MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0).paddingView == null) goto L25;
            if (MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0).paddingViewAttached == false) goto L25;
            if (r4.getY() >= MentionsContainerView.m11248$$Nest$fgetpaddedAdapter(this.this$0).paddingView.getBottom()) goto L25;
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (MentionsContainerView.m11244$$Nest$fgetignoreLayout(this.this$0) == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            this.this$0.invalidate();
            MentionsContainerView.m11256$$Nest$mcheckBackgroundBounds(this.this$0);
        }
    }

    public static /* synthetic */ void $r8$lambda$MleTDL2j1i5cjBW58sa4BD1z4fI(MentionsContainerView r0, Delegate r1, View r2, int r3) {
        r0.lambda$withDelegate$4(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$UqWP8-_zpas1gvXY34-zy2hFTjs, reason: not valid java name */
    public static /* synthetic */ void m11238$r8$lambda$UqWP8_zpas1gvXY34zy2hFTjs(MentionsContainerView r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$dd_L2UtPYzHyIxI7hKB0lrroym8(DynamicAnimation r0, boolean r1, float r2, float r3) {
    }

    public static /* synthetic */ boolean $r8$lambda$gnnajvVGDQZvcbMuUn0OBb5VIhQ(MentionsContainerView r0, View r1, MotionEvent r2) {
        return r0.lambda$withDelegate$5(r1, r2);
    }

    /* renamed from: $r8$lambda$pcVG2CgXmC1rV_CFvbqN1-idwpA, reason: not valid java name */
    public static /* synthetic */ void m11239$r8$lambda$pcVG2CgXmC1rV_CFvbqN1idwpA(View r1) {
        if ((r1 instanceof MentionCell) == false) goto L7;
        ((MentionCell) r1).invalidateEmojis();
        return;
    L7:
        if ((r1 instanceof QuickRepliesActivity.QuickReplyView) == false) goto L10;
        ((QuickRepliesActivity.QuickReplyView) r1).invalidateEmojis();
        return;
    L10:
        r1.invalidate();
    }

    public static /* synthetic */ void $r8$lambda$uRBGmG7gxFOcCAcGSkaV79EuAgM(MentionsContainerView r0, boolean r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        r0.lambda$updateListViewTranslation$2(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$uS7DXN16iRvLd78lvRuIaz9FjZc(MentionsContainerView r0, float r1, float r2, float r3, float r4, DynamicAnimation r5, float r6, float r7) {
        r0.lambda$updateListViewTranslation$1(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MentionsAdapter m11240$$Nest$fgetadapter(MentionsContainerView r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetbotContextResults, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m11241$$Nest$fgetbotContextResults(MentionsContainerView r0) {
        return r0.botContextResults;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WeakReference m11242$$Nest$fgetdelegate(MentionsContainerView r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetgridLayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ExtendedGridLayoutManager m11243$$Nest$fgetgridLayoutManager(MentionsContainerView r0) {
        return r0.gridLayoutManager;
    }

    /* renamed from: -$$Nest$fgetignoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11244$$Nest$fgetignoreLayout(MentionsContainerView r0) {
        return r0.ignoreLayout;
    }

    /* renamed from: -$$Nest$fgetlinearLayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m11245$$Nest$fgetlinearLayoutManager(MentionsContainerView r0) {
        return r0.linearLayoutManager;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MentionsListView m11246$$Nest$fgetlistView(MentionsContainerView r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetlistViewPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11247$$Nest$fgetlistViewPadding(MentionsContainerView r0) {
        return r0.listViewPadding;
    }

    /* renamed from: -$$Nest$fgetpaddedAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaddedListAdapter m11248$$Nest$fgetpaddedAdapter(MentionsContainerView r0) {
        return r0.paddedAdapter;
    }

    /* renamed from: -$$Nest$fgetscrollToFirst, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11249$$Nest$fgetscrollToFirst(MentionsContainerView r0) {
        return r0.scrollToFirst;
    }

    /* renamed from: -$$Nest$fgetshown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11250$$Nest$fgetshown(MentionsContainerView r0) {
        return r0.shown;
    }

    /* renamed from: -$$Nest$fgetupdateVisibilityRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m11251$$Nest$fgetupdateVisibilityRunnable(MentionsContainerView r0) {
        return r0.updateVisibilityRunnable;
    }

    /* renamed from: -$$Nest$fputignoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11252$$Nest$fputignoreLayout(MentionsContainerView r0, boolean r1) {
        r0.ignoreLayout = r1;
    }

    /* renamed from: -$$Nest$fputlistViewPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11253$$Nest$fputlistViewPadding(MentionsContainerView r0, float r1) {
        r0.listViewPadding = r1;
    }

    /* renamed from: -$$Nest$fputscrollToFirst, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11254$$Nest$fputscrollToFirst(MentionsContainerView r0, boolean r1) {
        r0.scrollToFirst = r1;
    }

    /* renamed from: -$$Nest$fputswitchLayoutManagerOnEnd, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11255$$Nest$fputswitchLayoutManagerOnEnd(MentionsContainerView r0, boolean r1) {
        r0.switchLayoutManagerOnEnd = r1;
    }

    /* renamed from: -$$Nest$mcheckBackgroundBounds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11256$$Nest$mcheckBackgroundBounds(MentionsContainerView r0) {
        r0.checkBackgroundBounds();
    }

    public MentionsContainerView(Context r13, long r14, long r16, BaseFragment r18, Theme.ResourcesProvider r19) {
        super(r13);
        this.shouldLiftMentions = false;
        this.rect = new Rect();
        this.ignoreLayout = false;
        this.scrollToFirst = false;
        this.shown = false;
        this.updateVisibilityRunnable = new MentionsContainerView$$ExternalSyntheticLambda1(this);
        this.animationIndex = -1;
        this.listViewHiding = false;
        this.hideT = 0.0f;
        this.switchLayoutManagerOnEnd = false;
        this.botContextProvider = new AnonymousClass5(this);
        this.clipPath = new Path();
        this.clipBounds = new RectF();
        this.baseFragment = new WeakReference(r18);
        this.resourcesProvider = r19;
        setVisibility(8);
        setWillNotDraw(false);
        setClipToOutline(true);
        this.listViewPadding = (int) Math.min(AndroidUtilities.dp(126.0f), AndroidUtilities.displaySize.y * 0.22f);
        this.listView = new MentionsListView(this, r13, r19);
        AnonymousClass1 r1 = new AnonymousClass1(this, r13);
        this.linearLayoutManager = r1;
        r1.setOrientation(1);
        AnonymousClass2 r2 = new AnonymousClass2(this, r13, 100, false, false);
        this.gridLayoutManager = r2;
        r2.setSpanSizeLookup(new AnonymousClass3(this));
        DefaultItemAnimator r0 = new DefaultItemAnimator();
        r0.setAddDuration(150);
        r0.setMoveDuration(150);
        r0.setChangeDuration(150);
        r0.setRemoveDuration(150);
        r0.setTranslationInterpolator(CubicBezierInterpolator.DEFAULT);
        r0.setDelayAnimations(false);
        this.listView.setItemAnimator(r0);
        this.listView.setClipToPadding(false);
        this.listView.setLayoutManager(this.linearLayoutManager);
        MentionsAdapter r02 = new MentionsAdapter(r13, false, r14, r16, new AnonymousClass4(this), r19, isStories());
        this.adapter = r02;
        PaddedListAdapter r132 = new PaddedListAdapter(r02);
        this.paddedAdapter = r132;
        this.listView.setAdapter(r132);
        this.listView.setTranslationY(AndroidUtilities.dp(6.0f));
        addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        setReversed(false);
    }

    private void checkBackgroundBounds() {
        if (this.listView != null) goto L5;
        return;
    L5:
        if (this.linearLayoutManager == null) goto L29;
        boolean r0 = isReversed();
        this.containerPadding = 0.0f;
        PaddedListAdapter r2 = this.paddedAdapter;
        if (r0 == false) goto L15;
        if (r2.paddingViewAttached == false) goto L12;
        int r02 = r2.paddingView.getTop();
    L13:
        float r03 = Math.min(Math.max(0.0f, r02 + this.listView.getTranslationY()) + this.containerPadding, (1.0f - this.hideT) * getHeight());
        this.containerTop = 0.0f;
        this.containerBottom = r03;
    L19:
        BlurredBackgroundDrawable r04 = this.backgroundDrawable;
        if (r04 == null) goto L28;
        r04.setBounds(0, ((int) this.containerTop) - AndroidUtilities.dp(5.0f), getMeasuredWidth(), ((int) this.containerBottom) + AndroidUtilities.dp(5.0f));
        this.clipPath.rewind();
        this.clipBounds.set(this.backgroundDrawable.getPaddedBounds());
        if (isGif() == false) goto L24;
        this.clipBounds.inset(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f));
        this.clipPath.addRoundRect(this.clipBounds, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f), Path.Direction.CW);
    L25:
        this.clipPath.close();
        invalidate();
        return;
    L24:
        this.clipPath.addRoundRect(this.clipBounds, AndroidUtilities.dp(22.0f), AndroidUtilities.dp(22.0f), Path.Direction.CW);
        goto L25
    L28:
        return;
    L12:
        r02 = getHeight();
        goto L13
    L15:
        if (r2.paddingViewAttached == false) goto L17;
        int r05 = r2.paddingView.getBottom();
    L18:
        this.containerTop = Math.max(Math.max(0.0f, r05 + this.listView.getTranslationY()) - this.containerPadding, this.hideT * getHeight());
        this.containerBottom = getMeasuredHeight();
        goto L19
    L17:
        r05 = 0;
        goto L18
    }

    private void checkListViewPadding() {
        if (this.listView != null) goto L5;
        return;
    L5:
        if (this.linearLayoutManager == null) goto L27;
        boolean r0 = isGif();
        BlurredBackgroundDrawable r1 = this.backgroundDrawable;
        MentionsListView r7 = this.listView;
        int r2 = 0;
        if (r1 != null) goto L11;
        r7.setPadding(0, 0, 0, 0);
        return;
    L11:
        float r12 = 5.0f;
        if (r0 == false) goto L14;
        float r4 = 7.0f;
    L15:
        int r42 = AndroidUtilities.dp(r4);
        if (r0 == false) goto L18;
        int r6 = AndroidUtilities.dp(2.0f);
    L19:
        if (r0 == false) goto L21;
        r12 = 7.0f;
    L21:
        int r13 = AndroidUtilities.dp(r12);
        if (r0 == false) goto L24;
        r2 = AndroidUtilities.dp(2.0f);
    L24:
        r7.setPadding(r42, r6, r13, r2);
        return;
    L18:
        r6 = 0;
        goto L19
    L14:
        r4 = 5.0f;
        goto L15
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private boolean isGif() {
        MentionsListView r0 = this.listView;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (this.gridLayoutManager != null) goto L7;
        return false;
    L7:
        if (r0.getLayoutManager() != this.gridLayoutManager) goto L17;
        MentionsAdapter r2 = this.adapter;
        if (r2 != null) goto L11;
        return false;
    L11:
        if (r2.isBotContext() == false) goto L19;
        return true;
    L19:
        return false;
    L17:
        return false;
    }

    private /* synthetic */ void lambda$new$0() {
        updateListViewTranslation(!this.shown, true);
    }

    private /* synthetic */ void lambda$updateListViewTranslation$1(float r1, float r2, float r3, float r4, DynamicAnimation r5, float r6, float r7) {
        this.listView.setTranslationY(r6);
        onAnimationScroll();
        this.hideT = AndroidUtilities.lerp(r1, r2, (r6 - r3) / (r4 - r3));
    }

    private /* synthetic */ void lambda$updateListViewTranslation$2(boolean r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        if (r3 == true) goto L12;
        this.listViewTranslationAnimator = null;
        if (r1 == false) goto L6;
        int r32 = 8;
    L7:
        setVisibility(r32);
        if (this.switchLayoutManagerOnEnd == false) goto L13;
        if (r1 == false) goto L14;
        this.switchLayoutManagerOnEnd = false;
        this.listView.setLayoutManager(getNeededLayoutManager());
        this.shown = true;
        updateVisibility(true);
        return;
    L14:
        return;
    L13:
        return;
    L6:
        r32 = 0;
        goto L7
    }

    private /* synthetic */ void lambda$withDelegate$4(Delegate r20, View r21, int r22) {
        if (r22 != 0) goto L5;
        return;
    L5:
        if (getAdapter().isBannedInline() == true) goto L101;
        WeakReference<Delegate> r2 = this.delegate;
        if (r2 != null) goto L9;
        return;
    L9:
        if (r2.get() == null) goto L105;
        int r23 = r22 - 1;
        Object r3 = getAdapter().getItem(r23);
        int r4 = getAdapter().getResultStartPosition();
        int r5 = getAdapter().getResultLength();
        boolean r6 = getAdapter().isLocalHashtagHint(r23);
        String r7 = _UrlKt.FRAGMENT_ENCODE_SET;
        String r9 = " ";
        if (r6 == false) goto L24;
        TLRPC.Chat r24 = getAdapter().chat;
        if (r24 == null) goto L16;
    L18:
        StringBuilder r32 = new StringBuilder();
        r32.append(getAdapter().getHashtagHint());
        if (r24 == null) goto L21;
        r7 = "@" + ChatObject.getPublicUsername(r24);
    L21:
        r32.append(r7);
        r32.append(" ");
        r20.replaceText(r4, r5, r32.toString(), false);
        return;
    L16:
        if (getAdapter().parentFragment == null) goto L18;
        r24 = getAdapter().parentFragment.getCurrentChat();
        goto L18
    L24:
        if (getAdapter().isGlobalHashtagHint(r23) == false) goto L28;
        r20.replaceText(r4, r5, getAdapter().getHashtagHint() + " ", false);
        return;
    L28:
        if ((r3 instanceof TLRPC.TL_document) == false) goto L31;
        TLRPC.TL_document r0 = (TLRPC.TL_document) r3;
        Object r42 = getAdapter().getItemParent(r23);
        this.delegate.get().onStickerSelected(r0, MessageObject.findAnimatedEmojiEmoticon(r0), r42);
    L73:
        if ((r3 instanceof TLRPC.BotInlineResult) == false) goto L103;
        TLRPC.BotInlineResult r33 = (TLRPC.BotInlineResult) r3;
        if (r33.type.equals("photo") == false) goto L81;
        if (r33.photo == null) goto L79;
    L90:
        ArrayList<Object> r13 = new ArrayList(getAdapter().getSearchResultBotContext());
        this.botContextResults = r13;
        PhotoViewer r02 = PhotoViewer.getInstance();
        WeakReference<BaseFragment> r34 = this.baseFragment;
        if (r34 == null) goto L93;
        BaseFragment r62 = r34.get();
    L94:
        r02.setParentActivity(r62, this.resourcesProvider);
        PhotoViewer.getInstance().openPhotoForSelect(r13, getAdapter().getItemPosition(r23), 3, false, this.botContextProvider, null);
        return;
    L93:
        r62 = null;
        goto L94
    L79:
        if (r33.content != null) goto L90;
    L81:
        if (r33.type.equals("gif") == false) goto L87;
        if (r33.document != null) goto L90;
        if (r33.content != null) goto L90;
    L87:
        if (r33.type.equals(MediaStreamTrack.VIDEO_TRACK_KIND) == true) goto L89;
    L95:
        this.delegate.get().sendBotInlineResult(r33, true, 0);
        return;
    L89:
        if (r33.document == null) goto L95;
    L103:
        return;
    L31:
        if ((r3 instanceof TLRPC.Chat) == false) goto L39;
        String r03 = ChatObject.getPublicUsername((TLRPC.Chat) r3);
        if (r03 == null) goto L73;
        Delegate r72 = this.delegate.get();
        StringBuilder r132 = new StringBuilder("@");
        r132.append(r03);
        if (ExteraConfig.getAddCommaAfterMention() == false) goto L37;
        r9 = ", ";
    L37:
        r132.append(r9);
        r72.replaceText(r4, r5, r132.toString(), false);
        goto L73
    L39:
        if ((r3 instanceof TLRPC.User) == false) goto L51;
        TLRPC.User r04 = (TLRPC.User) r3;
        if (UserObject.getPublicUsername(r04) == null) goto L46;
        Delegate r73 = this.delegate.get();
        StringBuilder r133 = new StringBuilder("@");
        r133.append(UserObject.getPublicUsername(r04));
        if (ExteraConfig.getAddCommaAfterMention() == false) goto L45;
        r9 = ", ";
    L45:
        r133.append(r9);
        r73.replaceText(r4, r5, r133.toString(), false);
        goto L73
    L46:
        String r8 = UserObject.getFirstName(r04, false);
        StringBuilder r15 = new StringBuilder();
        r15.append(r8);
        if (ExteraConfig.getAddCommaAfterMention() == false) goto L49;
        r9 = ", ";
    L49:
        r15.append(r9);
        SpannableString r14 = new SpannableString(r15.toString());
        r14.setSpan(new URLSpanUserMention(_UrlKt.FRAGMENT_ENCODE_SET + r04.id, 3), 0, r8.length() + 1, 33);
        this.delegate.get().replaceText(r4, r5, r14, false);
        goto L73
    L51:
        if ((r3 instanceof String) == false) goto L54;
        this.delegate.get().replaceText(r4, r5, r3 + " ", false);
        goto L73
    L54:
        if ((r3 instanceof MediaDataController.KeywordResult) == false) goto L73;
        String r63 = ((MediaDataController.KeywordResult) r3).emoji;
        this.delegate.get().addEmojiToRecent(r63);
        if (r63 != null) goto L58;
    L70:
        this.delegate.get().replaceText(r4, r5, r63, true);
    L71:
        updateVisibility(false);
        goto L73
    L58:
        if (r63.startsWith("animated_") == false) goto L70;
        Paint.FontMetricsInt r05 = this.delegate.get().getFontMetrics();     // Catch: Exception -> L61
    L63:
        long r74 = Long.parseLong(r63.substring(9));     // Catch: Exception -> L69
        TLRPC.Document r92 = AnimatedEmojiDrawable.findDocument(UserConfig.selectedAccount, r74);     // Catch: Exception -> L69
        SpannableString r12 = new SpannableString(MessageObject.findAnimatedEmojiEmoticon(r92));     // Catch: Exception -> L69
        if (r92 == null) goto L66;
        AnimatedEmojiSpan r75 = new AnimatedEmojiSpan(r92, r05);     // Catch: Exception -> L69
    L67:
        r12.setSpan(r75, 0, r12.length(), 33);     // Catch: Exception -> L69
        this.delegate.get().replaceText(r4, r5, r12, false);     // Catch: Exception -> L69
        goto L71
    L66:
        r75 = new AnimatedEmojiSpan(r74, r05);     // Catch: Exception -> L69
        goto L67
    L61:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L69
        r05 = null;
    L69:
        this.delegate.get().replaceText(r4, r5, r63, true);
        goto L71
    L105:
        return;
    }

    private /* synthetic */ boolean lambda$withDelegate$5(View r8, MotionEvent r9) {
        return ContentPreviewViewer.getInstance().onTouch(r9, getListView(), 0, this.mentionsOnItemClickListener, null, this.resourcesProvider);
    }

    private void updateListViewTranslation(boolean r13, boolean r14) {
        if (this.listView == null) goto L70;
        if (this.paddedAdapter == null) goto L70;
        if (this.listViewHiding == false) goto L16;
        SpringAnimation r0 = this.listViewTranslationAnimator;
        if (r0 == null) goto L16;
        if (r0.isRunning() == false) goto L16;
        if (r13 == false) goto L16;
        this.scrollRangeUpdateTries = 0;
        return;
    L16:
        boolean r02 = isReversed();
        if (r13 == false) goto L19;
        float r3 = (-this.containerPadding) - AndroidUtilities.dp(6.0f);
    L28:
        this.scrollRangeUpdateTries = 0;
        float r4 = this.listViewPadding;
        float r5 = 0.0f;
        if (r02 == false) goto L31;
        float r32 = -Math.max(0.0f, r4 - r3);
    L32:
        if (r13 == false) goto L35;
        if (r02 == true) goto L35;
        r32 = r32 + this.listView.computeVerticalScrollOffset();
    L35:
        float r11 = r32;
        SpringAnimation r03 = this.listViewTranslationAnimator;
        if (r03 == null) goto L38;
        r03.cancel();
    L38:
        int r04 = 8;
        Integer r33 = null;
        if (r14 == false) goto L59;
        this.listViewHiding = r13;
        float r10 = this.listView.getTranslationY();
        float r8 = this.hideT;
        if (r13 == false) goto L43;
        float r9 = 1.0f;
    L45:
        if (r10 != r11) goto L55;
        this.listViewTranslationAnimator = null;
        if (r13 == true) goto L50;
        r04 = 0;
    L50:
        r33 = Integer.valueOf(r04);
        if (this.switchLayoutManagerOnEnd == false) goto L54;
        if (r13 == false) goto L54;
        this.switchLayoutManagerOnEnd = false;
        this.listView.setLayoutManager(getNeededLayoutManager());
        this.shown = true;
        updateVisibility(true);
    L54:
        MentionsContainerView r7 = this;
    L65:
        if (r33 != null) goto L67;
        return;
    L67:
        if (r7.getVisibility() == r33.intValue()) goto L73;
        r7.setVisibility(r33.intValue());
        return;
    L73:
        return;
    L55:
        SpringAnimation r142 = new SpringAnimation(new FloatValueHolder(r10)).setSpring(new SpringForce(r11).setDampingRatio(1.0f).setStiffness(550.0f));
        this.listViewTranslationAnimator = r142;
        r7 = this;
        r142.addUpdateListener(new MentionsContainerView$$ExternalSyntheticLambda2(r7, r8, r9, r10, r11));
        if (r13 == false) goto L58;
        r7.listViewTranslationAnimator.addEndListener(new MentionsContainerView$$ExternalSyntheticLambda3(r7, r13));
    L58:
        r7.listViewTranslationAnimator.addEndListener(new MentionsContainerView$$ExternalSyntheticLambda4());
        r7.listViewTranslationAnimator.start();
        goto L65
    L43:
        r9 = 0.0f;
        goto L45
    L59:
        r7 = this;
        if (r13 == false) goto L62;
        r5 = 1.0f;
    L62:
        r7.hideT = r5;
        r7.listView.setTranslationY(r11);
        if (r13 == false) goto L65;
        r33 = 8;
        goto L65
    L31:
        r32 = Math.max(0.0f, r4 - r3) + (-r4);
        goto L32
    L19:
        int r34 = this.listView.computeVerticalScrollRange();
        float r42 = (r34 - this.paddedAdapter.getPadding()) + this.containerPadding;
        if (r34 <= 0) goto L22;
    L27:
        r3 = r42;
        goto L28
    L22:
        if (this.adapter.getItemCountInternal() <= 0) goto L27;
        int r35 = this.scrollRangeUpdateTries;
        if (r35 >= 3) goto L27;
        this.scrollRangeUpdateTries = r35 + 1;
        updateVisibility(true);
        return;
    L70:
        this.scrollRangeUpdateTries = 0;
    }

    public boolean canOpen() {
        return true;
    }

    public float clipBottom() {
        if (getVisibility() == 0) goto L6;
        return 0.0f;
    L6:
        if (isReversed() == false) goto L9;
        return 0.0f;
    L9:
        return getMeasuredHeight() - this.containerTop;
    }

    public float clipTop() {
        if (getVisibility() == 0) goto L6;
        return 0.0f;
    L6:
        if (isReversed() == true) goto L8;
        return 0.0f;
    L8:
        return this.containerBottom;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.emojiLoaded) goto L6;
        AndroidUtilities.forEachViews(this.listView, new MentionsContainerView$$ExternalSyntheticLambda0());
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r10) {
        BlurredBackgroundDrawable r0 = this.backgroundDrawable;
        if (r0 == null) goto L6;
        r0.draw(r10);
        r10.save();
        r10.clipPath(this.clipPath);
        super.dispatchDraw(r10);
        r10.restore();
        return;
    L6:
        boolean r02 = isReversed();
        if (this.adapter.isStickers() == true) goto L11;
        if (this.adapter.isBotContext() == true) goto L11;
    L17:
        boolean r1 = false;
    L19:
        if (r1 == false) goto L21;
        int r12 = 2;
    L22:
        this.containerPadding = AndroidUtilities.dp(r12 + 2);
        r10.save();
        float r13 = AndroidUtilities.dp(6.0f);
        float r4 = this.containerTop;
        if (r02 == false) goto L32;
        PaddedListAdapter r03 = this.paddedAdapter;
        if (r03.paddingViewAttached == false) goto L27;
        int r04 = r03.paddingView.getTop();
    L28:
        float r05 = Math.min(Math.max(0.0f, r04 + this.listView.getTranslationY()) + this.containerPadding, (1.0f - this.hideT) * getHeight());
        Rect r6 = this.rect;
        this.containerTop = 0.0f;
        int r7 = getMeasuredWidth();
        this.containerBottom = r05;
        r6.set(0, 0, r7, (int) r05);
        float r06 = Math.min(r13, Math.abs(getMeasuredHeight() - this.containerBottom));
        if (r06 <= 0.0f) goto L42;
        r10.clipRect(0, 0, getWidth(), getHeight());
        this.rect.top -= (int) r06;
    L42:
        if (Math.abs(r4 - this.containerTop) <= 0.1f) goto L45;
        onAnimationScroll();
    L45:
        if (this.paint != null) goto L47;
        Paint r14 = new Paint(1);
        this.paint = r14;
        r14.setShadowLayer(AndroidUtilities.dp(4.0f), 0.0f, 0.0f, 503316480);
    L47:
        Paint r15 = this.paint;
        Integer r2 = this.color;
        if (r2 == null) goto L50;
        int r22 = r2.intValue();
    L51:
        r15.setColor(r22);
        drawRoundRect(r10, this.rect, r06);
        r10.clipRect(this.rect);
        super.dispatchDraw(r10);
        r10.restore();
        return;
    L50:
        r22 = getThemedColor(Theme.key_chat_messagePanelBackground);
        goto L51
    L27:
        r04 = getHeight();
        goto L28
    L32:
        if (this.listView.getLayoutManager() != this.gridLayoutManager) goto L34;
        this.containerPadding += AndroidUtilities.dp(2.0f);
        r13 = r13 + AndroidUtilities.dp(2.0f);
    L34:
        PaddedListAdapter r07 = this.paddedAdapter;
        if (r07.paddingViewAttached == false) goto L37;
        int r08 = r07.paddingView.getBottom();
    L38:
        float r09 = Math.max(0.0f, r08 + this.listView.getTranslationY()) - this.containerPadding;
        this.containerTop = r09;
        float r010 = Math.max(r09, this.hideT * getHeight());
        Rect r62 = this.rect;
        this.containerTop = r010;
        int r72 = getMeasuredWidth();
        float r8 = getMeasuredHeight();
        this.containerBottom = r8;
        r62.set(0, (int) r010, r72, (int) r8);
        r06 = Math.min(r13, Math.abs(this.containerTop));
        if (r06 <= 0.0f) goto L42;
        r10.clipRect(0, 0, getWidth(), getHeight());
        this.rect.bottom += (int) r06;
        goto L42
    L37:
        r08 = 0;
        goto L38
    L21:
        r12 = 0;
    L11:
        if (this.adapter.isMediaLayout() == false) goto L17;
        if (this.adapter.getBotContextSwitch() != null) goto L17;
        if (this.adapter.getBotWebViewSwitch() != null) goto L17;
        r1 = true;
        goto L19
    }

    public void drawRoundRect(Canvas r2, Rect r3, float r4) {
        RectF r0 = AndroidUtilities.rectTmp;
        r0.set(r3);
        r2.drawRoundRect(r0, r4, r4, this.paint);
    }

    public MentionsAdapter getAdapter() {
        return this.adapter;
    }

    public LinearLayoutManager getCurrentLayoutManager() {
        RecyclerView.LayoutManager r0 = this.listView.getLayoutManager();
        LinearLayoutManager r1 = this.linearLayoutManager;
        if (r0 != r1) goto L6;
        return r1;
    L6:
        return this.gridLayoutManager;
    }

    public MentionsListView getListView() {
        return this.listView;
    }

    public LinearLayoutManager getNeededLayoutManager() {
        if (this.adapter.isStickers() == true) goto L7;
        if (this.adapter.isBotContext() == true) goto L7;
    L11:
        return this.linearLayoutManager;
    L7:
        if (this.adapter.isMediaLayout() == false) goto L11;
        return this.gridLayoutManager;
    }

    public boolean isOpen() {
        return this.shown;
    }

    public boolean isReversed() {
        RecyclerView.LayoutManager r0 = this.listView.getLayoutManager();
        LinearLayoutManager r1 = this.linearLayoutManager;
        if (r0 == r1) goto L5;
        return false;
    L5:
        if (r1.getReverseLayout() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isStories() {
        return false;
    }

    public void onAnimationScroll() {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
    }

    public void onClose() {
    }

    public void onContextClick(TLRPC.BotInlineResult r1) {
    }

    public void onContextSearch(boolean r1) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        checkBackgroundBounds();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        checkListViewPadding();
        super.onMeasure(r1, r2);
    }

    public void onOpen() {
    }

    public void onScrolled(boolean r1, boolean r2) {
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void setBackgroundDrawable(BlurredBackgroundDrawable r2) {
        this.backgroundDrawable = r2;
        r2.setRadius(AndroidUtilities.dp(22.0f));
        this.backgroundDrawable.setPadding(AndroidUtilities.dp(5.0f));
        checkListViewPadding();
    }

    public void setDialogId(long r1) {
        this.adapter.setDialogId(r1);
    }

    public void setIgnoreLayout(boolean r1) {
        this.ignoreLayout = r1;
    }

    public void setOverrideColor(int r1) {
        this.color = Integer.valueOf(r1);
        invalidate();
    }

    public void setReversed(boolean r2) {
        if (r2 == isReversed()) goto L6;
        this.scrollToFirst = true;
        this.linearLayoutManager.setReverseLayout(r2);
        this.adapter.setIsReversed(r2);
        return;
    }

    public void updateVisibility(boolean r5) {
        if (r5 == false) goto L20;
        boolean r1 = isReversed();
        if (this.shown == true) goto L19;
        this.scrollToFirst = true;
        RecyclerView.LayoutManager r2 = this.listView.getLayoutManager();
        LinearLayoutManager r3 = this.linearLayoutManager;
        if (r2 != r3) goto L13;
        if (r1 == false) goto L10;
        int r22 = -100000;
    L11:
        r3.scrollToPositionWithOffset(0, r22);
        goto L13
    L10:
        r22 = AppDataDirGuesser.PER_USER_RANGE;
    L13:
        if (getVisibility() != 8) goto L19;
        this.hideT = 1.0f;
        MentionsListView r23 = this.listView;
        if (r1 == false) goto L17;
        float r12 = -(this.listViewPadding + AndroidUtilities.dp(12.0f));
    L18:
        r23.setTranslationY(r12);
        goto L19
    L17:
        r12 = r23.computeVerticalScrollOffset() + this.listViewPadding;
    L19:
        setVisibility(0);
    L21:
        this.shown = r5;
        AndroidUtilities.cancelRunOnUIThread(this.updateVisibilityRunnable);
        SpringAnimation r0 = this.listViewTranslationAnimator;
        if (r0 == null) goto L24;
        r0.cancel();
    L24:
        Runnable r02 = this.updateVisibilityRunnable;
        WeakReference<BaseFragment> r13 = this.baseFragment;
        if (r13 != null) goto L27;
    L31:
        long r14 = 100;
    L32:
        AndroidUtilities.runOnUIThread(r02, r14);
        if (r5 == false) goto L36;
        onOpen();
        return;
    L36:
        onClose();
        return;
    L27:
        if (r13.get() == null) goto L31;
        if (this.baseFragment.get().getFragmentBeginToShow() == false) goto L31;
        r14 = 0;
        goto L32
    L20:
        this.scrollToFirst = false;
        goto L21
    }

    public void withDelegate(Delegate r3) {
        this.delegate = new WeakReference(r3);
        MentionsListView r0 = getListView();
        MentionsContainerView$$ExternalSyntheticLambda5 r1 = new MentionsContainerView$$ExternalSyntheticLambda5(this, r3);
        this.mentionsOnItemClickListener = r1;
        r0.setOnItemClickListener(r1);
        getListView().setOnTouchListener(new MentionsContainerView$$ExternalSyntheticLambda6(this));
    }
}
