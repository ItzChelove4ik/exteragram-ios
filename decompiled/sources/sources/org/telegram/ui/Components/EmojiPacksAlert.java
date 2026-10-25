package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.exteragram.messenger.utils.system.VibratorUtils;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.PremiumButtonView;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.ContentPreviewViewer;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes7.dex */
public class EmojiPacksAlert extends BottomSheet implements NotificationCenter.NotificationCenterDelegate {
    private static Pattern urlPattern;
    private Adapter adapter;
    private int adaptiveEmojiColor;
    private ColorFilter adaptiveEmojiColorFilter;
    private TextView addButtonView;
    private LongSparseArray<AnimatedEmojiDrawable> animatedEmojiDrawables;
    private FrameLayout buttonsView;
    private Runnable cancelSearchCreatorRunnable;
    private ContentView contentView;
    private EmojiPacksLoader customEmojiPacks;
    private BaseFragment fragment;
    private Float fromY;
    private GridLayoutManager gridLayoutManager;
    private boolean hasDescription;
    private AnimatedFloat highlightAlpha;
    int highlightEndPosition;
    private int highlightIndex;
    int highlightStartPosition;
    private float lastY;
    private boolean limitCount;
    private RecyclerListView listView;
    private ValueAnimator loadAnimator;
    private float loadT;
    boolean loaded;
    private View paddingView;
    private ActionBarPopupWindow popupWindow;
    private long premiumButtonClicked;
    private PremiumButtonView premiumButtonView;
    private ContentPreviewViewer.ContentPreviewViewerDelegate previewDelegate;
    private CircularProgressDrawable progressDrawable;
    private TextView removeButtonView;
    private RecyclerAnimationScrollHelper scrollHelper;
    private View shadowView;
    private boolean shown;

    /* renamed from: org.telegram.ui.Components.EmojiPacksAlert$1, reason: invalid class name */
    public class AnonymousClass1 implements ContentPreviewViewer.ContentPreviewViewerDelegate {
        final /* synthetic */ EmojiPacksAlert this$0;

        public static /* synthetic */ void $r8$lambda$4e3Hi5_xUp5TmQhiAmgTG4l8tgE(AnonymousClass1 r0, String r1) {
            r0.lambda$setAsBadge$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$Fbi_ouWoQdEWjjkbWozVwmtofwc(AnonymousClass1 r0, String r1) {
            r0.lambda$setAsBadge$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$Fca7XOFqVDfvVhPAyi8HhCXyFSA(AnonymousClass1 r0, TLRPC.EmojiStatus r1) {
            r0.lambda$setAsEmojiStatus$0(r1);
        }

        /* renamed from: $r8$lambda$f-u1YjfoAu2z7K_jmUS6Js2aAFM, reason: not valid java name */
        public static /* synthetic */ void m10239$r8$lambda$fu1YjfoAu2z7K_jmUS6Js2aAFM(AnonymousClass1 r0, BadgeDTO r1) {
            r0.lambda$setAsBadge$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$gMcNkxO1pyKQJUaUlbiohjazZn8(AnonymousClass1 r0, String r1, TLRPC.Document r2, Runnable r3) {
            r0.lambda$setAsBadge$4(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$qfE2vkyV810tTZEbAxCPYMghhko(AnonymousClass1 r0, TLRPC.Document r1, Runnable r2, String r3) {
            r0.lambda$setAsBadge$5(r1, r2, r3);
        }

        public AnonymousClass1(EmojiPacksAlert r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$setAsBadge$1(String r2) {
            if (r2 != null) goto L4;
        L7:
            BulletinFactory.of((FrameLayout) EmojiPacksAlert.access$1200(this.this$0), EmojiPacksAlert.access$1300(this.this$0)).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
            return;
        L4:
            if (r2.equals("ok") == false) goto L7;
        }

        private /* synthetic */ void lambda$setAsBadge$2(String r2) {
            AndroidUtilities.runOnUIThread(new EmojiPacksAlert$1$$ExternalSyntheticLambda5(this, r2));
        }

        private /* synthetic */ void lambda$setAsBadge$3(BadgeDTO r3) {
            BadgesController.INSTANCE.updateBadge(r3, new EmojiPacksAlert$1$$ExternalSyntheticLambda3(this));
        }

        private /* synthetic */ void lambda$setAsBadge$4(String r2, TLRPC.Document r3, Runnable r4) {
            if (r2 != null) goto L4;
        L8:
            BulletinFactory.of((FrameLayout) EmojiPacksAlert.access$800(this.this$0), EmojiPacksAlert.access$900(this.this$0)).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
            return;
        L4:
            if (r2.equals("ok") == false) goto L8;
            BulletinFactory.of((FrameLayout) EmojiPacksAlert.access$1000(this.this$0), EmojiPacksAlert.access$1100(this.this$0)).createEmojiBulletin(r3, LocaleController.getString(R.string.SetAsBadgeStatusInfo), LocaleController.getString(R.string.UndoNoCaps), r4).show();
        }

        private /* synthetic */ void lambda$setAsBadge$5(TLRPC.Document r2, Runnable r3, String r4) {
            AndroidUtilities.runOnUIThread(new EmojiPacksAlert$1$$ExternalSyntheticLambda4(this, r4, r2, r3));
        }

        private /* synthetic */ void lambda$setAsEmojiStatus$0(TLRPC.EmojiStatus r1) {
            MessagesController.getInstance(EmojiPacksAlert.access$1400(this.this$0)).updateEmojiStatus(r1);
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean can() {
            return true;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean canSchedule() {
            return false;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public Boolean canSetAsStatus(TLRPC.Document r3) {
            if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L5;
        L19:
            return null;
        L5:
            if (MessageObject.isAnimatedEmoji(r3) == false) goto L19;
            TLRPC.User r2 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser();
            if (r2 != null) goto L10;
            return null;
        L10:
            Long r22 = UserObject.getEmojiStatusDocumentId(r2);
            if (r3 == null) goto L16;
            if (r22 != null) goto L14;
        L15:
            boolean r23 = true;
        L18:
            return Boolean.valueOf(r23);
        L14:
            if (r22.longValue() != r3.id) goto L15;
        L16:
            r23 = false;
            goto L18
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void copyEmoji(TLRPC.Document r5) {
            SpannableStringBuilder r0 = SpannableStringBuilder.valueOf(MessageObject.findAnimatedEmojiEmoticon(r5));
            r0.setSpan(new AnimatedEmojiSpan(r5, null), 0, r0.length(), 33);
            if (AndroidUtilities.addToClipboard(r0) == false) goto L6;
            BulletinFactory.of((FrameLayout) EmojiPacksAlert.access$000(this.this$0), EmojiPacksAlert.access$100(this.this$0)).createCopyBulletin(LocaleController.getString(R.string.EmojiCopied)).show();
            return;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public long getDialogId() {
            return 0;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean isInScheduleMode() {
            if ((EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0) instanceof ChatActivity) == true) goto L5;
            return false;
        L5:
            return ((ChatActivity) EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0)).isInScheduleMode();
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean needCopy(TLRPC.Document r1) {
            if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L7;
            if (LocaleUtils.canUseLocalPremiumEmojis() == true) goto L7;
            return false;
        L7:
            if (MessageObject.isAnimatedEmoji(r1) == false) goto L12;
            return true;
        L12:
            return false;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean needSend(int r1) {
            if ((EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0) instanceof ChatActivity) == true) goto L5;
            return false;
        L5:
            if (((ChatActivity) EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0)).canSendMessage() == true) goto L7;
            return false;
        L7:
            if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == false) goto L9;
            return true;
        L9:
            if (((ChatActivity) EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0)).getCurrentUser() == null) goto L13;
            if (UserObject.isUserSelf(((ChatActivity) EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0)).getCurrentUser()) == false) goto L13;
            return true;
        L13:
            if (LocaleUtils.canUseLocalPremiumEmojis() == false) goto L21;
            return true;
        L21:
            return false;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void openSet(TLRPC.InputStickerSet r1, boolean r2) {
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void sendEmoji(TLRPC.Document r4) {
            if ((EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0) instanceof ChatActivity) == false) goto L5;
            ((ChatActivity) EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0)).sendAnimatedEmoji(r4, true, 0);
        L5:
            this.this$0.onCloseByLink();
            this.this$0.dismiss();
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void setAsBadge(TLRPC.Document r7) {
            if (r7 != null) goto L4;
            return;
        L4:
            BadgesController r0 = BadgesController.INSTANCE;
            BadgeDTO r1 = r0.getBadge();
            EmojiPacksAlert$1$$ExternalSyntheticLambda0 r2 = new EmojiPacksAlert$1$$ExternalSyntheticLambda0(this, r1);
            long r4 = r7.id;
            if (r1 == null) goto L7;
            String r12 = r1.getText();
        L8:
            r0.updateBadge(new BadgeDTO(r4, r12), new EmojiPacksAlert$1$$ExternalSyntheticLambda1(this, r7, r2));
            return;
        L7:
            r12 = null;
            goto L8
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void setAsEmojiStatus(TLRPC.Document r5, Integer r6) {
            if (r5 != null) goto L5;
            TLRPC.EmojiStatus r62 = new TLRPC.TL_emojiStatusEmpty();
        L9:
            TLRPC.User r1 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser();
            if (r1 != null) goto L12;
            TLRPC.EmojiStatus r12 = new TLRPC.TL_emojiStatusEmpty();
        L13:
            MessagesController.getInstance(EmojiPacksAlert.access$200(this.this$0)).updateEmojiStatus(r62);
            EmojiPacksAlert$1$$ExternalSyntheticLambda2 r63 = new EmojiPacksAlert$1$$ExternalSyntheticLambda2(this, r12);
            if (r5 != null) goto L17;
            Bulletin.SimpleLayout r52 = new Bulletin.SimpleLayout(this.this$0.getContext(), EmojiPacksAlert.access$300(this.this$0));
            r52.textView.setText(LocaleController.getString(R.string.RemoveStatusInfo));
            r52.imageView.setImageResource(R.drawable.msg_settings_premium);
            Bulletin.UndoButton r13 = new Bulletin.UndoButton(this.this$0.getContext(), true, EmojiPacksAlert.access$400(this.this$0));
            r13.setUndoAction(r63);
            r52.setButton(r13);
            Bulletin.make((FrameLayout) EmojiPacksAlert.access$500(this.this$0), r52, 1500).show();
            return;
        L17:
            BulletinFactory.of((FrameLayout) EmojiPacksAlert.access$600(this.this$0), EmojiPacksAlert.access$700(this.this$0)).createEmojiBulletin(r5, LocaleController.getString(R.string.SetAsEmojiStatusInfo), LocaleController.getString(R.string.UndoNoCaps), r63).show();
            return;
        L12:
            r12 = r1.emoji_status;
            goto L13
        L5:
            TLRPC.TL_emojiStatus r14 = new TLRPC.TL_emojiStatus();
            r14.document_id = r5.id;
            if (r6 == null) goto L8;
            r14.flags |= 1;
            r14.until = r6.intValue();
        L8:
            r62 = r14;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiPacksAlert$2, reason: invalid class name */
    public class AnonymousClass2 extends EmojiPacksLoader {
        final /* synthetic */ EmojiPacksAlert this$0;

        public AnonymousClass2(EmojiPacksAlert r1, int r2, ArrayList r3, TLObject r4) {
            this.this$0 = r1;
            super(r1, r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.EmojiPacksAlert.EmojiPacksLoader
        public void onUpdate() {
            EmojiPacksAlert.m10235$$Nest$mupdateButton(this.this$0);
            if (EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0) != null) goto L5;
            return;
        L5:
            if (EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getAdapter() == null) goto L9;
            EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getAdapter().notifyDataSetChanged();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiPacksAlert$3, reason: invalid class name */
    public class AnonymousClass3 extends View {
        final /* synthetic */ EmojiPacksAlert this$0;

        public AnonymousClass3(EmojiPacksAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            Point r32 = AndroidUtilities.displaySize;
            int r0 = r32.x;
            int r33 = r32.y;
            if (r0 >= r33) goto L5;
            boolean r02 = true;
        L6:
            float r34 = r33;
            if (r02 == false) goto L9;
            float r03 = 0.56f;
        L10:
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec((int) (r34 * r03), TLObject.FLAG_30));
            return;
        L9:
            r03 = 0.3f;
            goto L10
        L5:
            r02 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiPacksAlert$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerListView {
        private Paint highlightPaint;
        final /* synthetic */ EmojiPacksAlert this$0;

        public AnonymousClass4(EmojiPacksAlert r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
            this.highlightPaint = new Paint(1);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r11) {
            if (EmojiPacksAlert.m10220$$Nest$fgethighlightAlpha(this.this$0) == null) goto L29;
            EmojiPacksAlert r0 = this.this$0;
            if (r0.highlightStartPosition < 0) goto L29;
            if (r0.highlightEndPosition < 0) goto L29;
            if (EmojiPacksAlert.m10213$$Nest$fgetadapter(r0) == null) goto L29;
            if (isAttachedToWindow() == false) goto L29;
            float r02 = EmojiPacksAlert.m10220$$Nest$fgethighlightAlpha(this.this$0).set(0.0f);
            if (r02 <= 0.0f) goto L29;
            int r1 = Integer.MAX_VALUE;
            int r2 = Integer.MIN_VALUE;
            int r3 = 0;
        L16:
            if (r3 >= getChildCount()) goto L25;
            View r4 = getChildAt(r3);
            int r5 = getChildAdapterPosition(r4);
            if (r5 == (-1)) goto L24;
            EmojiPacksAlert r6 = this.this$0;
            if (r5 < r6.highlightStartPosition) goto L24;
            if (r5 > r6.highlightEndPosition) goto L24;
            r1 = Math.min(r1, r4.getTop() + ((int) r4.getTranslationY()));
            r2 = Math.max(r2, r4.getBottom() + ((int) r4.getTranslationY()));
        L24:
            r3 = r3 + 1;
            goto L16
        L25:
            if (r1 >= r2) goto L27;
            this.highlightPaint.setColor(Theme.multAlpha(getThemedColor(Theme.key_chat_linkSelectBackground), r02));
            Canvas r42 = r11;
            r42.drawRect(0.0f, r1, getMeasuredWidth(), r2, this.highlightPaint);
        L28:
            invalidate();
        L30:
            super.dispatchDraw(r42);
            return;
        L27:
            r42 = r11;
        L29:
            r42 = r11;
            goto L30
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            AnimatedEmojiSpan.release(EmojiPacksAlert.access$1600(this.this$0), EmojiPacksAlert.m10214$$Nest$fgetanimatedEmojiDrawables(this.this$0));
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r7) {
            boolean r72 = ContentPreviewViewer.getInstance().onInterceptTouchEvent(r7, EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0), 0, EmojiPacksAlert.m10224$$Nest$fgetpreviewDelegate(this.this$0), this.resourcesProvider);
            if (super.onInterceptTouchEvent(r7) == true) goto L8;
            if (r72 == true) goto L10;
            return false;
        L10:
            return true;
        L8:
            return true;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            EmojiPacksAlert.m10215$$Nest$fgetcontentView(this.this$0).updateEmojiDrawables();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r3, int r4) {
            View.MeasureSpec.getSize(r3);
            EmojiPacksAlert.m10218$$Nest$fgetgridLayoutManager(this.this$0).setSpanCount(40);
            super.onMeasure(r3, r4);
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void onScrolled(int r1, int r2) {
            super.onScrolled(r1, r2);
            EmojiPacksAlert.m10215$$Nest$fgetcontentView(this.this$0).updateEmojiDrawables();
            EmojiPacksAlert.access$1500(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiPacksAlert$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.ItemDecoration {
        final /* synthetic */ EmojiPacksAlert this$0;

        public AnonymousClass5(EmojiPacksAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            boolean r32 = r2 instanceof SeparatorView;
            EmojiPacksAlert r42 = this.this$0;
            if (r32 == false) goto L7;
            r1.left = -EmojiPacksAlert.m10222$$Nest$fgetlistView(r42).getPaddingLeft();
            r1.right = -EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getPaddingRight();
            return;
        L7:
            if (EmojiPacksAlert.m10222$$Nest$fgetlistView(r42).getChildAdapterPosition(r2) != 1) goto L10;
            r1.top = AndroidUtilities.dp(14.0f);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiPacksAlert$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerView.OnScrollListener {
        final /* synthetic */ EmojiPacksAlert this$0;

        public AnonymousClass6(EmojiPacksAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (EmojiPacksAlert.m10215$$Nest$fgetcontentView(this.this$0) != null) goto L5;
            return;
        L5:
            if (EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).scrollingByUser == false) goto L9;
            EmojiPacksAlert.m10215$$Nest$fgetcontentView(this.this$0).hidePreviewEmoji();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiPacksAlert$7, reason: invalid class name */
    public class AnonymousClass7 extends EmojiPacksAlert {
        final /* synthetic */ EmojiPacksAlert this$0;

        public AnonymousClass7(EmojiPacksAlert r1, BaseFragment r2, Context r3, Theme.ResourcesProvider r4, ArrayList r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.EmojiPacksAlert
        public void onCloseByLink() {
            this.this$0.dismiss();
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiPacksAlert$8, reason: invalid class name */
    public class AnonymousClass8 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ EmojiPacksAlert this$0;

        public AnonymousClass8(EmojiPacksAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r6) {
            if (EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getAdapter() == null) goto L35;
            if (EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getAdapter().getItemViewType(r6) != 1) goto L35;
            int r0 = 0;
            int r2 = 0;
        L9:
            if (r0 >= EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data.length) goto L18;
            int r3 = EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data[r0].size();
            if (EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data.length <= 1) goto L13;
            r3 = Math.min(EmojiPacksAlert.m10218$$Nest$fgetgridLayoutManager(this.this$0).getSpanCount() * 2, r3);
        L13:
            r2 = r2 + (r3 + 2);
            if (r6 < r2) goto L18;
            r0 = r0 + 1;
        L18:
            if (EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).stickerSets != null) goto L20;
        L23:
            TLRPC.TL_messages_stickerSet r5 = null;
        L24:
            if (r5 == null) goto L32;
            TLRPC.StickerSet r52 = r5.set;
            if (r52 != null) goto L28;
            return 5;
        L28:
            if (r52.emojis == true) goto L39;
            return 8;
        L39:
            return 5;
        L32:
            return 5;
        L20:
            if (r0 >= EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).stickerSets.size()) goto L23;
            r5 = EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).stickerSets.get(r0);
        L35:
            return EmojiPacksAlert.m10218$$Nest$fgetgridLayoutManager(this.this$0).getSpanCount();
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiPacksAlert$9, reason: invalid class name */
    public class AnonymousClass9 extends ShareAlert {
        final /* synthetic */ EmojiPacksAlert this$0;

        public static /* synthetic */ void $r8$lambda$Hv2yghxT1kEr1R0l_GrvRGlZhdc(AnonymousClass9 r0, androidx.collection.LongSparseArray r1, int r2) {
            r0.lambda$onSend$0(r1, r2);
        }

        public AnonymousClass9(EmojiPacksAlert r1, Context r2, ArrayList r3, String r4, boolean r5, String r6, boolean r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        private /* synthetic */ void lambda$onSend$0(androidx.collection.LongSparseArray r9, int r10) {
            boolean r0 = EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0) instanceof ChatActivity;
            EmojiPacksAlert r1 = this.this$0;
            if (r0 == false) goto L7;
            UndoView r8 = ((ChatActivity) EmojiPacksAlert.m10217$$Nest$fgetfragment(r1)).getUndoView();
        L5:
            UndoView r02 = r8;
            if (r02 != null) goto L12;
            return;
        L12:
            if (r9.size() != 1) goto L15;
            r02.showWithAction(((TLRPC.Dialog) r9.valueAt(0)).id, 53, Integer.valueOf(r10));
            return;
        L15:
            r02.showWithAction(0, 53, Integer.valueOf(r10), Integer.valueOf(r9.size()), null, null);
            return;
        L7:
            if ((EmojiPacksAlert.m10217$$Nest$fgetfragment(r1) instanceof ProfileActivity) == false) goto L9;
            r8 = ((ProfileActivity) EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0)).getUndoView();
            goto L5
        L9:
            r8 = null;
            goto L5
        }

        @Override // org.telegram.ui.Components.ShareAlert
        public void onSend(androidx.collection.LongSparseArray<TLRPC.Dialog> r1, int r2, TLRPC.TL_forumTopic r3, boolean r4) {
            AndroidUtilities.runOnUIThread(new EmojiPacksAlert$9$$ExternalSyntheticLambda0(this, r1, r2), 100);
        }
    }

    public class Adapter extends RecyclerListView.SelectionAdapter {
        private final int VIEW_TYPE_EMOJI;
        private final int VIEW_TYPE_HEADER;
        private final int VIEW_TYPE_PADDING;
        private final int VIEW_TYPE_SEPARATOR;
        private final int VIEW_TYPE_TEXT;
        final /* synthetic */ EmojiPacksAlert this$0;

        private Adapter(EmojiPacksAlert r1) {
            this.this$0 = r1;
            this.VIEW_TYPE_PADDING = 0;
            this.VIEW_TYPE_EMOJI = 1;
            this.VIEW_TYPE_HEADER = 2;
            this.VIEW_TYPE_TEXT = 3;
            this.VIEW_TYPE_SEPARATOR = 4;
        }

        public /* synthetic */ Adapter(EmojiPacksAlert r1, EmojiPacksAlertIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            EmojiPacksAlert r0 = this.this$0;
            if (UserConfig.getInstance(EmojiPacksAlert.access$2500(r0)).isPremium() == false) goto L5;
        L13:
            boolean r1 = false;
        L14:
            EmojiPacksAlert.m10229$$Nest$fputhasDescription(r0, r1);
            return (((EmojiPacksAlert.m10219$$Nest$fgethasDescription(this.this$0) ? 1 : 0) + 1) + EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).getItemsCount()) + Math.max(0, EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data.length - 1);
        L5:
            if (LocaleUtils.canUseLocalPremiumEmojis(EmojiPacksAlert.access$2600(this.this$0)) == true) goto L13;
            if (EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).stickerSets == null) goto L13;
            if (EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).stickerSets.size() != 1) goto L13;
            if (MessageObject.isPremiumEmojiPack(EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).stickerSets.get(0)) == false) goto L13;
            r1 = true;
            goto L14
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r7) {
            int r0 = 0;
            if (r7 != 0) goto L5;
            return 0;
        L5:
            int r1 = r7 - 1;
            if (EmojiPacksAlert.m10219$$Nest$fgethasDescription(this.this$0) == false) goto L12;
            if (r1 != 1) goto L10;
            return 3;
        L10:
            if (r1 <= 0) goto L12;
            r1 = r7 - 2;
        L12:
            int r72 = 0;
        L14:
            if (r0 >= EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data.length) goto L26;
            if (r1 == r72) goto L17;
            int r4 = EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data[r0].size();
            if (EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data.length <= 1) goto L21;
            r4 = Math.min(EmojiPacksAlert.m10218$$Nest$fgetgridLayoutManager(this.this$0).getSpanCount() * 2, r4);
        L21:
            int r73 = r72 + (r4 + 1);
            if (r1 == r73) goto L23;
            r72 = r73 + 1;
            r0 = r0 + 1;
            goto L14
        L23:
            return 4;
        L17:
            return 2;
        L26:
            return 1;
        }

        public int getSetEndPosition(int r7) {
            if (EmojiPacksAlert.m10219$$Nest$fgethasDescription(this.this$0) == false) goto L5;
            int r0 = 2;
        L6:
            int r3 = 0;
        L8:
            if (r3 >= EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data.length) goto L16;
            int r4 = EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data[r3].size();
            if (EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data.length <= 1) goto L12;
            r4 = Math.min(EmojiPacksAlert.m10218$$Nest$fgetgridLayoutManager(this.this$0).getSpanCount() * 2, r4);
        L12:
            if (r3 == r7) goto L14;
            r0 = r0 + (r4 + 2);
            r3 = r3 + 1;
            goto L8
        L14:
            return (r0 + r4) + 1;
        L16:
            return r0;
        L5:
            r0 = 1;
            goto L6
        }

        public int getSetHeaderPosition(int r7) {
            if (EmojiPacksAlert.m10219$$Nest$fgethasDescription(this.this$0) == false) goto L5;
            int r0 = 2;
        L6:
            int r3 = 0;
        L8:
            if (r3 >= EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data.length) goto L15;
            if (r3 == r7) goto L15;
            int r4 = EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data[r3].size();
            if (EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data.length <= 1) goto L14;
            r4 = Math.min(EmojiPacksAlert.m10218$$Nest$fgetgridLayoutManager(this.this$0).getSpanCount() * 2, r4);
        L14:
            r0 = r0 + (r4 + 2);
            r3 = r3 + 1;
        L15:
            return r0;
        L5:
            r0 = 1;
            goto L6
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 1) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            int r0 = r10 - 1;
            int r1 = r9.getItemViewType();
            TLRPC.TL_messages_stickerSet r2 = null;
            int r4 = 0;
            boolean r5 = true;
            if (r1 == 1) goto L51;
            if (r1 == 2) goto L11;
            if (r1 != 3) goto L93;
            TextView r92 = (TextView) r9.itemView;
            r92.setTextSize(1, 13.0f);
            r92.setTextColor(EmojiPacksAlert.access$2400(this.this$0, Theme.key_chat_emojiPanelTrendingDescription));
            r92.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PremiumPreviewEmojiPack)));
            r92.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(30.0f), AndroidUtilities.dp(14.0f));
            return;
        L93:
            return;
        L11:
            if (EmojiPacksAlert.m10219$$Nest$fgethasDescription(this.this$0) == false) goto L14;
            if (r0 <= 0) goto L14;
            r0 = r10 - 2;
        L14:
            int r102 = 0;
            int r12 = 0;
        L16:
            if (r102 >= EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data.length) goto L24;
            int r6 = EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data[r102].size();
            if (EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data.length <= 1) goto L20;
            r6 = Math.min(EmojiPacksAlert.m10218$$Nest$fgetgridLayoutManager(this.this$0).getSpanCount() * 2, r6);
        L20:
            if (r0 == r12) goto L24;
            r12 = r12 + (r6 + 2);
            r102 = r102 + 1;
        L24:
            if (EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).stickerSets != null) goto L26;
        L29:
            if (r2 != null) goto L31;
        L39:
            r5 = false;
        L41:
            if (r102 >= EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data.length) goto L90;
            EmojiPackHeader r8 = (EmojiPackHeader) r9.itemView;
            if (r2 == null) goto L48;
            ArrayList<TLRPC.Document> r93 = r2.documents;
            if (r93 == null) goto L48;
            r4 = r93.size();
        L48:
            r8.set(r2, r4, r5);
            return;
        L90:
            return;
        L31:
            if (r2.documents == null) goto L39;
            int r02 = 0;
        L34:
            if (r02 >= r2.documents.size()) goto L39;
            if (MessageObject.isFreeEmoji(r2.documents.get(r02)) == false) goto L41;
            r02 = r02 + 1;
            goto L34
        L26:
            if (r102 >= EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).stickerSets.size()) goto L29;
            r2 = EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).stickerSets.get(r102);
            goto L29
        L51:
            if (EmojiPacksAlert.m10219$$Nest$fgethasDescription(this.this$0) == false) goto L53;
            r0 = r10 - 2;
        L53:
            EmojiImageView r94 = (EmojiImageView) r9.itemView;
            int r103 = 0;
        L55:
            if (r4 >= EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data.length) goto L64;
            int r13 = EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data[r4].size();
            if (EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data.length <= 1) goto L59;
            r13 = Math.min(EmojiPacksAlert.m10218$$Nest$fgetgridLayoutManager(this.this$0).getSpanCount() * 2, r13);
        L59:
            if (r0 <= r103) goto L63;
            if (r0 > (r103 + r13)) goto L63;
            EmojiView.CustomEmoji r82 = EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data[r4].get((r0 - r103) - 1);
        L65:
            AnimatedEmojiSpan r104 = r94.span;
            if (r104 != null) goto L68;
            if (r82 == null) goto L68;
        L73:
            if (r82 != null) goto L76;
            r94.span = null;
            return;
        L76:
            TLRPC.TL_inputStickerSetID r105 = new TLRPC.TL_inputStickerSetID();
            TLRPC.StickerSet r03 = r82.stickerSet.set;
            r105.id = r03.id;
            r105.short_name = r03.short_name;
            r105.access_hash = r03.access_hash;
            TLRPC.Document r106 = r82.getDocument();
            if (r106 == null) goto L80;
            r94.span = new AnimatedEmojiSpan(r106, null);
            return;
        L80:
            r94.span = new AnimatedEmojiSpan(r82.documentId, null);
            return;
        L68:
            if (r82 != null) goto L70;
            if (r104 != null) goto L73;
        L70:
            if (r82 != null) goto L72;
            return;
        L72:
            if (r104.documentId != r82.documentId) goto L73;
            return;
        L63:
            r103 = r103 + (r13 + 2);
            r4 = r4 + 1;
            goto L55
        L64:
            r82 = null;
            goto L65
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 0) goto L4;
            View r2 = EmojiPacksAlert.m10223$$Nest$fgetpaddingView(this.this$0);
        L23:
            return new RecyclerListView.Holder(r2);
        L4:
            boolean r32 = true;
            if (r4 != 1) goto L9;
            View r33 = new EmojiImageView(this.this$0.getContext());
        L7:
            r2 = r33;
            goto L23
        L9:
            if (r4 != 2) goto L16;
            EmojiPacksAlert r0 = this.this$0;
            Context r1 = r0.getContext();
            if (EmojiPacksAlert.m10216$$Nest$fgetcustomEmojiPacks(this.this$0).data.length <= 1) goto L14;
            r32 = false;
        L14:
            r2 = new EmojiPackHeader(r0, r1, r32);
            goto L23
        L16:
            if (r4 != 3) goto L19;
            r33 = new TextView(this.this$0.getContext());
            goto L7
        L19:
            if (r4 != 4) goto L21;
            EmojiPacksAlert r22 = this.this$0;
            r33 = new SeparatorView(r22, r22.getContext());
            goto L7
        L21:
            r2 = null;
            goto L23
        }
    }

    public class ContentView extends FrameLayout {
        boolean attached;
        private Boolean lastOpen;
        ArrayList<DrawingInBackgroundLine> lineDrawables;
        ArrayList<DrawingInBackgroundLine> lineDrawablesTmp;
        private Paint paint;
        private Path path;
        private ImageReceiver previewImageReceiver;
        private boolean previewImageVisible;
        private final AnimatedFloat previewImageVisibleT;
        private final AnimatedFloat statusBarT;
        final /* synthetic */ EmojiPacksAlert this$0;
        ArrayList<ArrayList<EmojiImageView>> unusedArrays;
        ArrayList<DrawingInBackgroundLine> unusedLineDrawables;
        SparseArray<ArrayList<EmojiImageView>> viewsGroupedByLines;

        public class DrawingInBackgroundLine extends DrawingInBackgroundThreadDrawable {
            ArrayList<EmojiImageView> drawInBackgroundViews;
            ArrayList<EmojiImageView> imageViewEmojis;
            public int position;
            final /* synthetic */ ContentView this$1;

            public DrawingInBackgroundLine(ContentView r1) {
                this.this$1 = r1;
                this.drawInBackgroundViews = new ArrayList();
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void draw(Canvas r8, long r9, int r11, int r12, float r13) {
                ArrayList<EmojiImageView> r0 = this.imageViewEmojis;
                if (r0 != null) goto L5;
                return;
            L5:
                int r2 = 0;
                boolean r3 = true;
                if (r0.size() > 3) goto L8;
            L11:
                boolean r02 = true;
            L12:
                if (r02 == false) goto L14;
            L27:
                r3 = r02;
            L28:
                if (r3 == false) goto L31;
                prepareDraw(System.currentTimeMillis());
                drawInUiThread(r8, r13);
                reset();
                return;
            L31:
                super.draw(r8, r9, r11, r12, r13);
                return;
            L14:
                if (r2 >= this.imageViewEmojis.size()) goto L27;
                EmojiImageView r1 = this.imageViewEmojis.get(r2);
                if (EmojiImageView.m10241$$Nest$fgetpressedProgress(r1) != 0.0f) goto L28;
                if (r1.backAnimator != null) goto L28;
                if (r1.getTranslationX() != 0.0f) goto L28;
                if (r1.getTranslationY() != 0.0f) goto L28;
                if (r1.getAlpha() != 1.0f) goto L28;
                r2 = r2 + 1;
                goto L14
            L8:
                if (SharedConfig.getDevicePerformanceClass() == 0) goto L11;
                r02 = false;
                goto L12
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void drawInBackground(Canvas r5) {
                int r0 = 0;
            L4:
                if (r0 >= this.drawInBackgroundViews.size()) goto L6;
                EmojiImageView r1 = this.drawInBackgroundViews.get(r0);
                r1.imageReceiver.draw(r5, r1.backgroundThreadDrawHolder[this.threadIndex]);
                r0 = r0 + 1;
                goto L4
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void drawInUiThread(Canvas r12, float r13) {
                if (this.imageViewEmojis == null) goto L22;
                int r0 = 0;
            L6:
                if (r0 >= this.imageViewEmojis.size()) goto L29;
                EmojiImageView r1 = this.imageViewEmojis.get(r0);
                if (r1.span == null) goto L21;
                AnimatedEmojiDrawable r2 = (AnimatedEmojiDrawable) EmojiPacksAlert.m10214$$Nest$fgetanimatedEmojiDrawables(this.this$1.this$0).get(r1.span.getDocumentId());
                if (r2 == null) goto L21;
                if (r2.getImageReceiver() == null) goto L21;
                if (r1.imageReceiver == null) goto L21;
                r2.setAlpha((int) ((255.0f * r13) * r1.getAlpha()));
                float r3 = ((r1.getWidth() - r1.getPaddingLeft()) - r1.getPaddingRight()) / 2.0f;
                float r5 = ((r1.getHeight() - r1.getPaddingTop()) - r1.getPaddingBottom()) / 2.0f;
                float r6 = (r1.getLeft() + r1.getRight()) / 2.0f;
                float r4 = r1.getPaddingTop() + r5;
                float r8 = 1.0f;
                if (EmojiImageView.m10241$$Nest$fgetpressedProgress(r1) == 0.0f) goto L20;
                r8 = 1.0f * (((1.0f - EmojiImageView.m10241$$Nest$fgetpressedProgress(r1)) * 0.2f) + 0.8f);
            L20:
                r2.setBounds((int) (r6 - ((r1.getScaleX() * r3) * r8)), (int) (r4 - ((r1.getScaleY() * r5) * r8)), (int) (r6 + ((r3 * r1.getScaleX()) * r8)), (int) (r4 + ((r5 * r1.getScaleY()) * r8)));
                r2.draw(r12);
            L21:
                r0 = r0 + 1;
                goto L6
            L29:
                return;
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void onFrameReady() {
                super.onFrameReady();
                int r0 = 0;
            L4:
                if (r0 >= this.drawInBackgroundViews.size()) goto L6;
                this.drawInBackgroundViews.get(r0).backgroundThreadDrawHolder[this.threadIndex].release();
                r0 = r0 + 1;
                goto L4
            L6:
                EmojiPacksAlert.access$2200(this.this$1.this$0).invalidate();
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void prepareDraw(long r10) {
                this.drawInBackgroundViews.clear();
                int r0 = 0;
            L4:
                if (r0 >= this.imageViewEmojis.size()) goto L15;
                EmojiImageView r1 = this.imageViewEmojis.get(r0);
                if (r1.span == null) goto L14;
                AnimatedEmojiDrawable r2 = (AnimatedEmojiDrawable) EmojiPacksAlert.m10214$$Nest$fgetanimatedEmojiDrawables(this.this$1.this$0).get(r1.span.getDocumentId());
                if (r2 == null) goto L14;
                if (r2.getImageReceiver() == null) goto L14;
                r2.update(r10);
                ImageReceiver.BackgroundThreadDrawHolder[] r3 = r1.backgroundThreadDrawHolder;
                int r4 = this.threadIndex;
                ImageReceiver r5 = r2.getImageReceiver();
                ImageReceiver.BackgroundThreadDrawHolder[] r6 = r1.backgroundThreadDrawHolder;
                int r7 = this.threadIndex;
                r3[r4] = r5.setDrawInBackgroundThread(r6[r7], r7);
                r1.backgroundThreadDrawHolder[this.threadIndex].time = r10;
                r2.setAlpha(255);
                Rect r32 = AndroidUtilities.rectTmp2;
                r32.set(r1.getLeft() + r1.getPaddingLeft(), r1.getPaddingTop(), r1.getRight() - r1.getPaddingRight(), r1.getMeasuredHeight() - r1.getPaddingBottom());
                r1.backgroundThreadDrawHolder[this.threadIndex].setBounds(r32);
                EmojiPacksAlert r33 = this.this$1.this$0;
                r2.setColorFilter(EmojiPacksAlert.m10232$$Nest$mgetAdaptiveEmojiColorFilter(r33, EmojiPacksAlert.access$2100(r33, Theme.key_windowBackgroundWhiteBlackText)));
                r1.imageReceiver = r2.getImageReceiver();
                this.drawInBackgroundViews.add(r1);
            L14:
                r0 = r0 + 1;
                goto L4
            }
        }

        public ContentView(EmojiPacksAlert r9, Context r10) {
            this.this$0 = r9;
            super(r10);
            this.paint = new Paint();
            this.path = new Path();
            this.lastOpen = null;
            this.viewsGroupedByLines = new SparseArray();
            this.lineDrawables = new ArrayList();
            this.lineDrawablesTmp = new ArrayList();
            this.unusedArrays = new ArrayList();
            this.unusedLineDrawables = new ArrayList();
            CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.statusBarT = new AnimatedFloat(this, 0, 350, r6);
            this.previewImageVisibleT = new AnimatedFloat(this, 0, 320, r6);
        }

        private AnimatedEmojiSpan[] getAnimatedEmojiSpans() {
            int r1 = 0;
            if (EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0) == null) goto L5;
            AnimatedEmojiSpan[] r0 = new AnimatedEmojiSpan[EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getChildCount()];
        L8:
            if (r1 >= EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getChildCount()) goto L13;
            View r2 = EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getChildAt(r1);
            if ((r2 instanceof EmojiImageView) == false) goto L12;
            r0[r1] = ((EmojiImageView) r2).span;
        L12:
            r1 = r1 + 1;
            goto L8
        L13:
            return r0;
        L5:
            return new AnimatedEmojiSpan[0];
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r18) {
            Canvas r1 = r18;
            if (this.attached == true) goto L5;
            return;
        L5:
            this.paint.setColor(EmojiPacksAlert.access$1700(this.this$0, Theme.key_dialogBackground));
            Theme.applyDefaultShadow(this.paint);
            this.path.reset();
            EmojiPacksAlert r2 = this.this$0;
            float r3 = EmojiPacksAlert.m10233$$Nest$mgetListTop(r2);
            EmojiPacksAlert.m10230$$Nest$fputlastY(r2, r3);
            AnimatedFloat r22 = this.statusBarT;
            boolean r8 = true;
            int r9 = 0;
            if (r3 > EmojiPacksAlert.access$1800(this.this$0).getPaddingTop()) goto L8;
            boolean r4 = true;
        L9:
            float r23 = r22.set(r4);
            float r32 = AndroidUtilities.lerp(r3, 0.0f, r23);
            if (this.previewImageReceiver == null) goto L18;
            float r42 = AndroidUtilities.dp(140.0f);
            float r7 = AndroidUtilities.dp(20.0f);
            if (r32 >= (r42 + r7)) goto L14;
            this.previewImageVisible = false;
        L14:
            this.previewImageReceiver.setAlpha(this.previewImageVisibleT.set(this.previewImageVisible));
            float r12 = this.previewImageReceiver.getAlpha();
            ImageReceiver r13 = this.previewImageReceiver;
            if (r12 <= 0.0f) goto L17;
            float r132 = ((r13.getAlpha() * 0.4f) + 0.6f) * r42;
            float r14 = r132 / 2.0f;
            this.previewImageReceiver.setImageCoords((getWidth() / 2.0f) - r14, ((r32 - r7) - (r42 / 2.0f)) - r14, r132, r132);
            this.previewImageReceiver.draw(r1);
            goto L18
        L17:
            r13.onDetachedFromWindow();
            this.previewImageReceiver = null;
        L18:
            float r43 = AndroidUtilities.dp((1.0f - r23) * 14.0f);
            RectF r72 = AndroidUtilities.rectTmp;
            r72.set(getPaddingLeft(), r32, getWidth() - getPaddingRight(), getBottom() + r43);
            this.path.addRoundRect(r72, r43, r43, Path.Direction.CW);
            r1.drawPath(this.path, this.paint);
            if (r23 <= 0.5f) goto L21;
            boolean r24 = true;
        L22:
            Boolean r44 = this.lastOpen;
            if (r44 != null) goto L25;
        L26:
            EmojiPacksAlert r45 = this.this$0;
            this.lastOpen = Boolean.valueOf(r24);
            EmojiPacksAlert.m10236$$Nest$mupdateLightStatusBar(r45, r24);
        L27:
            Theme.dialogs_onlineCirclePaint.setColor(EmojiPacksAlert.access$1900(this.this$0, Theme.key_sheet_scrollUp));
            Theme.dialogs_onlineCirclePaint.setAlpha((int) (MathUtils.clamp(r32 / AndroidUtilities.dp(20.0f), 0.0f, 1.0f) * Theme.dialogs_onlineCirclePaint.getAlpha()));
            int r25 = AndroidUtilities.dp(36.0f);
            float r133 = r32 + AndroidUtilities.dp(10.0f);
            r72.set((getMeasuredWidth() - r25) / 2, r133, (getMeasuredWidth() + r25) / 2, AndroidUtilities.dp(4.0f) + r133);
            r1.drawRoundRect(r72, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Theme.dialogs_onlineCirclePaint);
            View r26 = EmojiPacksAlert.m10227$$Nest$fgetshadowView(this.this$0);
            if (EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).canScrollVertically(1) == false) goto L30;
        L33:
            int r33 = 0;
        L34:
            r26.setVisibility(r33);
            if (EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0) == null) goto L94;
            r1.save();
            r1.translate(EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getLeft(), EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getY() + 0.0f);
            r1.clipRect(0, 0, EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getWidth(), EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getHeight());
            r1.saveLayerAlpha(0.0f, 0.0f, EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getWidth(), EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getHeight(), (int) (EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getAlpha() * 255.0f), 31);
            int r27 = 0;
        L37:
            int r34 = this.viewsGroupedByLines.size();
            SparseArray<ArrayList<EmojiImageView>> r46 = this.viewsGroupedByLines;
            if (r27 >= r34) goto L40;
            ArrayList<EmojiImageView> r35 = r46.valueAt(r27);
            r35.clear();
            this.unusedArrays.add(r35);
            r27 = r27 + 1;
            goto L37
        L40:
            r46.clear();
            int r28 = 0;
        L42:
            if (r28 >= EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getChildCount()) goto L64;
            View r36 = EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getChildAt(r28);
            if ((r36 instanceof EmojiImageView) == false) goto L62;
            EmojiImageView r47 = (EmojiImageView) r36;
            r47.updatePressedProgress();
            if (EmojiPacksAlert.m10214$$Nest$fgetanimatedEmojiDrawables(this.this$0) == null) goto L63;
            AnimatedEmojiSpan r5 = r47.span;
            if (r5 == null) goto L63;
            AnimatedEmojiDrawable r52 = (AnimatedEmojiDrawable) EmojiPacksAlert.m10214$$Nest$fgetanimatedEmojiDrawables(this.this$0).get(r5.getDocumentId());
            if (r52 == null) goto L63;
            EmojiPacksAlert r6 = this.this$0;
            r52.setColorFilter(EmojiPacksAlert.m10232$$Nest$mgetAdaptiveEmojiColorFilter(r6, EmojiPacksAlert.access$2000(r6, Theme.key_windowBackgroundWhiteBlackText)));
            ArrayList<EmojiImageView> r53 = this.viewsGroupedByLines.get(r36.getTop());
            if (r53 == null) goto L57;
        L61:
            r53.add(r47);
            goto L63
        L57:
            if (this.unusedArrays.isEmpty() == true) goto L59;
            ArrayList<ArrayList<EmojiImageView>> r54 = this.unusedArrays;
            r53 = r54.remove(r54.size() - 1);
        L60:
            this.viewsGroupedByLines.put(r36.getTop(), r53);
            goto L61
        L59:
            r53 = new ArrayList();
        L63:
            r28 = r28 + 1;
            goto L42
        L62:
            r1.save();
            r1.translate(r36.getLeft(), r36.getTop());
            r36.draw(r1);
            r1.restore();
            goto L63
        L64:
            this.lineDrawablesTmp.clear();
            this.lineDrawablesTmp.addAll(this.lineDrawables);
            this.lineDrawables.clear();
            long r37 = System.currentTimeMillis();
            int r15 = 0;
        L66:
            if (r15 >= this.viewsGroupedByLines.size()) goto L83;
            ArrayList<EmojiImageView> r29 = this.viewsGroupedByLines.valueAt(r15);
            EmojiImageView r55 = r29.get(r9);
            int r62 = EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getChildAdapterPosition(r55);
            boolean r16 = r8;
            int r73 = r9;
        L69:
            if (r73 >= this.lineDrawablesTmp.size()) goto L74;
            if (this.lineDrawablesTmp.get(r73).position == r62) goto L72;
            r73 = r73 + 1;
            goto L69
        L72:
            DrawingInBackgroundLine r82 = this.lineDrawablesTmp.get(r73);
            this.lineDrawablesTmp.remove(r73);
        L75:
            if (r82 != null) goto L82;
            if (this.unusedLineDrawables.isEmpty() == true) goto L80;
            DrawingInBackgroundLine r74 = this.unusedLineDrawables.remove(r7.size() - 1);
        L79:
            r82 = r74;
            r82.position = r62;
            r82.onAttachToWindow();
            goto L82
        L80:
            r74 = new DrawingInBackgroundLine(this);
            r74.setLayerNum(7);
        L82:
            this.lineDrawables.add(r82);
            r82.imageViewEmojis = r29;
            r1.save();
            r1.translate(0.0f, r55.getY() + r55.getPaddingTop());
            Canvas r210 = r1;
            r82.draw(r210, r37, getMeasuredWidth(), r55.getMeasuredHeight() - r55.getPaddingBottom(), 1.0f);
            r1 = r210;
            r1.restore();
            r15 = r15 + 1;
            r8 = r16;
            r9 = 0;
            goto L66
        L74:
            r82 = null;
            goto L75
        L83:
            int r92 = 0;
        L85:
            if (r92 >= this.lineDrawablesTmp.size()) goto L91;
            if (this.unusedLineDrawables.size() >= 3) goto L89;
            this.unusedLineDrawables.add(this.lineDrawablesTmp.get(r92));
            this.lineDrawablesTmp.get(r92).imageViewEmojis = null;
            this.lineDrawablesTmp.get(r92).reset();
        L90:
            r92 = r92 + 1;
            goto L85
        L89:
            this.lineDrawablesTmp.get(r92).onDetachFromWindow();
            goto L90
        L91:
            this.lineDrawablesTmp.clear();
            r1.restore();
            r1.restore();
            if (EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getAlpha() >= 1.0f) goto L94;
            int r211 = getWidth() / 2;
            int r38 = (((int) r133) + getHeight()) / 2;
            int r48 = AndroidUtilities.dp(16.0f);
            EmojiPacksAlert.m10225$$Nest$fgetprogressDrawable(this.this$0).setAlpha((int) ((1.0f - EmojiPacksAlert.m10222$$Nest$fgetlistView(this.this$0).getAlpha()) * 255.0f));
            EmojiPacksAlert.m10225$$Nest$fgetprogressDrawable(this.this$0).setBounds(r211 - r48, r38 - r48, r211 + r48, r38 + r48);
            EmojiPacksAlert.m10225$$Nest$fgetprogressDrawable(this.this$0).draw(r1);
            invalidate();
        L94:
            super.dispatchDraw(r18);
            return;
        L30:
            if (EmojiPacksAlert.m10226$$Nest$fgetremoveButtonView(this.this$0).getVisibility() == 0) goto L33;
            r33 = 4;
            goto L34
        L25:
            if (r24 == r44.booleanValue()) goto L27;
        L21:
            r24 = false;
            goto L22
        L8:
            r4 = false;
            goto L9
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r4) {
            if (r4.getAction() != 0) goto L8;
            if (r4.getY() >= (EmojiPacksAlert.m10233$$Nest$mgetListTop(this.this$0) - AndroidUtilities.dp(6.0f))) goto L8;
            this.this$0.dismiss();
        L8:
            return super.dispatchTouchEvent(r4);
        }

        public void hidePreviewEmoji() {
            if (this.previewImageVisible == false) goto L6;
            this.previewImageVisible = false;
            invalidate();
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.attached = true;
            ImageReceiver r1 = this.previewImageReceiver;
            if (r1 == null) goto L6;
            r1.onAttachedToWindow();
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            int r0 = 0;
            this.attached = false;
            int r1 = 0;
        L4:
            if (r1 >= this.lineDrawables.size()) goto L7;
            this.lineDrawables.get(r1).onDetachFromWindow();
            r1 = r1 + 1;
        L7:
            if (r0 >= this.unusedLineDrawables.size()) goto L9;
            this.unusedLineDrawables.get(r0).onDetachFromWindow();
            r0 = r0 + 1;
            goto L7
        L9:
            this.lineDrawables.clear();
            AnimatedEmojiSpan.release(this, EmojiPacksAlert.m10214$$Nest$fgetanimatedEmojiDrawables(this.this$0));
            ImageReceiver r3 = this.previewImageReceiver;
            if (r3 == null) goto L15;
            r3.onDetachedFromWindow();
            return;
        }

        public void setPreviewEmoji(TLRPC.Document r19) {
            ImageReceiver r2 = new ImageReceiver(this);
            this.previewImageReceiver = r2;
            if (this.attached == false) goto L5;
            r2.onAttachedToWindow();
        L5:
            this.previewImageVisible = true;
            this.previewImageVisibleT.set(1.0f, true);
            TLRPC.PhotoSize r3 = FileLoader.getClosestPhotoSizeWithSize(r19.thumbs, 90);
            SvgHelper.SvgDrawable r12 = DocumentObject.getSvgThumb(r19.thumbs, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f, true);
            this.previewImageReceiver.setImage(ImageLocation.getForDocument(r19), "140_140", ImageLocation.getForDocument(r3, r19), "140_140", r12, 0, null, null, 0);
            this.previewImageReceiver.setLayerNum(7);
            this.previewImageReceiver.setAllowStartLottieAnimation(true);
            this.previewImageReceiver.setAllowStartAnimation(true);
            this.previewImageReceiver.setAutoRepeat(1);
            this.previewImageReceiver.setAllowDecodeSingleFrame(true);
            this.previewImageReceiver.setParentView(this);
        }

        public void updateEmojiDrawables() {
            EmojiPacksAlert.m10228$$Nest$fputanimatedEmojiDrawables(this.this$0, AnimatedEmojiSpan.update(3, this, getAnimatedEmojiSpans(), EmojiPacksAlert.m10214$$Nest$fgetanimatedEmojiDrawables(this.this$0)));
        }
    }

    public static class EmojiImageView extends View {
        ValueAnimator backAnimator;
        public ImageReceiver.BackgroundThreadDrawHolder[] backgroundThreadDrawHolder;
        public ImageReceiver imageReceiver;
        private float pressedProgress;
        public AnimatedEmojiSpan span;

        /* renamed from: org.telegram.ui.Components.EmojiPacksAlert$EmojiImageView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ EmojiImageView this$0;

            public AnonymousClass1(EmojiImageView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                super.onAnimationEnd(r1);
                this.this$0.backAnimator = null;
            }
        }

        /* renamed from: $r8$lambda$i4u-Ra2NUdeu8xGf01sFFph2eps, reason: not valid java name */
        public static /* synthetic */ void m10240$r8$lambda$i4uRa2NUdeu8xGf01sFFph2eps(EmojiImageView r0, ValueAnimator r1) {
            r0.lambda$setPressed$0(r1);
        }

        /* renamed from: -$$Nest$fgetpressedProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10241$$Nest$fgetpressedProgress(EmojiImageView r0) {
            return r0.pressedProgress;
        }

        public EmojiImageView(Context r1) {
            super(r1);
            this.backgroundThreadDrawHolder = new ImageReceiver.BackgroundThreadDrawHolder[2];
        }

        private /* synthetic */ void lambda$setPressed$0(ValueAnimator r1) {
            this.pressedProgress = ((Float) r1.getAnimatedValue()).floatValue();
            if ((getParent() instanceof View) == false) goto L6;
            ((View) getParent()).invalidate();
            return;
        }

        public TLRPC.Document getDocument() {
            AnimatedEmojiSpan r2 = this.span;
            if (r2 == null) goto L9;
            TLRPC.Document r0 = r2.document;
            if (r0 != null) goto L8;
            long r02 = r2.getDocumentId();
            return AnimatedEmojiDrawable.findDocument(UserConfig.selectedAccount, r02);
        L8:
            return r0;
        L9:
            return null;
        }

        @Override // android.view.View
        public void onMeasure(int r4, int r5) {
            setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f));
            super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r4), TLObject.FLAG_30));
        }

        @Override // android.view.View
        public void setPressed(boolean r4) {
            if (isPressed() == r4) goto L14;
            super.setPressed(r4);
            invalidate();
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
            r43.addUpdateListener(new EmojiPacksAlert$EmojiImageView$$ExternalSyntheticLambda0(this));
            this.backAnimator.addListener(new AnonymousClass1(this));
            this.backAnimator.setInterpolator(new OvershootInterpolator(5.0f));
            this.backAnimator.setDuration(350);
            this.backAnimator.start();
            return;
        L16:
            return;
        L15:
            return;
        }

        public void updatePressedProgress() {
            if (isPressed() == false) goto L8;
            float r0 = this.pressedProgress;
            if (r0 == 1.0f) goto L9;
            this.pressedProgress = Utilities.clamp(r0 + 0.16f, 1.0f, 0.0f);
            invalidate();
            return;
        L9:
            return;
        }
    }

    public class EmojiPackHeader extends FrameLayout {
        public TextView addButtonView;
        private ValueAnimator animator;
        public BaseFragment dummyFragment;
        public ActionBarMenuItem optionsButton;
        public TextView removeButtonView;
        private TLRPC.TL_messages_stickerSet set;
        private boolean single;
        public TextView subtitleView;
        final /* synthetic */ EmojiPacksAlert this$0;
        public LinkSpanDrawable.LinksTextView titleView;
        private float toggleT;
        private boolean toggled;
        public PremiumButtonView unlockButtonView;

        /* renamed from: org.telegram.ui.Components.EmojiPacksAlert$EmojiPackHeader$1, reason: invalid class name */
        public class AnonymousClass1 extends BaseFragment {
            final /* synthetic */ EmojiPackHeader this$1;

            public AnonymousClass1(EmojiPackHeader r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public int getCurrentAccount() {
                return this.currentAccount;
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public View getFragmentView() {
                return EmojiPacksAlert.access$2700(this.this$1.this$0);
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public FrameLayout getLayoutContainer() {
                return (FrameLayout) EmojiPacksAlert.access$2800(this.this$1.this$0);
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public Theme.ResourcesProvider getResourceProvider() {
                return EmojiPacksAlert.access$2900(this.this$1.this$0);
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiPacksAlert$EmojiPackHeader$2, reason: invalid class name */
        public class AnonymousClass2 extends ActionBarMenuItem {
            final /* synthetic */ EmojiPackHeader this$1;
            final /* synthetic */ EmojiPacksAlert val$this$0;

            public AnonymousClass2(EmojiPackHeader r1, Context r2, ActionBarMenu r3, int r4, int r5, Theme.ResourcesProvider r6, EmojiPacksAlert r7) {
                this.this$1 = r1;
                this.val$this$0 = r7;
                super(r2, r3, r4, r5, r6);
            }

            @Override // org.telegram.ui.ActionBar.ActionBarMenuItem, android.view.View
            public boolean onTouchEvent(MotionEvent r3) {
                if (r3.getActionMasked() != 0) goto L8;
                if (getParent() == null) goto L8;
                getParent().requestDisallowInterceptTouchEvent(true);
            L8:
                return super.onTouchEvent(r3);
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiPacksAlert$EmojiPackHeader$3, reason: invalid class name */
        public class AnonymousClass3 extends URLSpanNoUnderline {
            final /* synthetic */ EmojiPackHeader this$1;

            public AnonymousClass3(EmojiPackHeader r1, String r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.URLSpanNoUnderline, android.text.style.URLSpan, android.text.style.ClickableSpan
            public void onClick(View r4) {
                MessagesController.getInstance(EmojiPacksAlert.access$4500(this.this$1.this$0)).openByUserName(getURL(), EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$1.this$0), 1);
                this.this$1.this$0.onCloseByLink();
                this.this$1.this$0.dismiss();
            }
        }

        /* renamed from: $r8$lambda$07I-MrcnMzRbMGZess1tx4oimQs, reason: not valid java name */
        public static /* synthetic */ void m10242$r8$lambda$07IMrcnMzRbMGZess1tx4oimQs(EmojiPackHeader r0) {
            r0.lambda$new$2();
        }

        public static /* synthetic */ void $r8$lambda$0ssiRJUzEOune7xgwbVbtqkj50o(EmojiPackHeader r0, ValueAnimator r1) {
            r0.lambda$toggle$5(r1);
        }

        /* renamed from: $r8$lambda$8QTX5s-IeMLbi6koMJk9lqZeOoU, reason: not valid java name */
        public static /* synthetic */ void m10243$r8$lambda$8QTX5sIeMLbi6koMJk9lqZeOoU(EmojiPackHeader r0, View r1) {
            r0.lambda$new$4(r1);
        }

        /* renamed from: $r8$lambda$AASrpNueqEPsTC-8O__eiMwSrIA, reason: not valid java name */
        public static /* synthetic */ void m10244$r8$lambda$AASrpNueqEPsTC8O__eiMwSrIA(EmojiPacksAlert r0, int r1) {
            EmojiPacksAlert.m10234$$Nest$monSubItemClick(r0, r1);
        }

        /* renamed from: $r8$lambda$d0T2QW7gg0-2bF1DqdSritd0IOw, reason: not valid java name */
        public static /* synthetic */ void m10245$r8$lambda$d0T2QW7gg02bF1DqdSritd0IOw(EmojiPackHeader r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$rpeuFjl977NreA0OtCHBo3SLi7w(EmojiPackHeader r0, View r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$vO0MzZzEb60uL9c3rX95oW6ScQ4(EmojiPackHeader r0, View r1) {
            r0.lambda$new$3(r1);
        }

        /* renamed from: -$$Nest$fgetset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.TL_messages_stickerSet m10246$$Nest$fgetset(EmojiPackHeader r0) {
            return r0.set;
        }

        /* renamed from: -$$Nest$mtoggle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10247$$Nest$mtoggle(EmojiPackHeader r0, boolean r1, boolean r2) {
            r0.toggle(r1, r2);
        }

        public EmojiPackHeader(EmojiPacksAlert r27, Context r28, boolean r29) {
            this.this$0 = r27;
            super(r28);
            this.dummyFragment = new AnonymousClass1(this);
            this.toggled = false;
            this.toggleT = 0.0f;
            this.single = r29;
            if (r29 == true) goto L12;
            float r14 = 8.0f;
            if (UserConfig.getInstance(EmojiPacksAlert.access$3000(r27)).isPremium() == false) goto L7;
        L9:
            float r16 = 16.0f;
        L10:
            TextView r6 = new TextView(r28);
            this.addButtonView = r6;
            r6.setTypeface(AndroidUtilities.bold());
            this.addButtonView.setTextColor(EmojiPacksAlert.access$3300(r27, Theme.key_featuredStickers_buttonText));
            TextView r62 = this.addButtonView;
            int r9 = Theme.key_featuredStickers_addButton;
            r62.setBackground(Theme.AdaptiveRipple.filledRect(EmojiPacksAlert.access$3400(r27, r9), new float[]{14.0f}));
            this.addButtonView.setText(LocaleController.getString(R.string.Add));
            this.addButtonView.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
            this.addButtonView.setGravity(17);
            this.addButtonView.setOnClickListener(new EmojiPacksAlert$EmojiPackHeader$$ExternalSyntheticLambda2(this));
            addView(this.addButtonView, LayoutHelper.createFrameRelatively(-2.0f, 28.0f, 8388661, 0.0f, 15.66f, 5.66f, 0.0f));
            this.addButtonView.measure(View.MeasureSpec.makeMeasureSpec(99999, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(28.0f), TLObject.FLAG_30));
            float r5 = Math.max(r14, (this.addButtonView.getMeasuredWidth() + AndroidUtilities.dp(r16)) / AndroidUtilities.density);
            TextView r63 = new TextView(r28);
            this.removeButtonView = r63;
            r63.setTypeface(AndroidUtilities.bold());
            this.removeButtonView.setTextColor(EmojiPacksAlert.access$3500(r27, r9));
            this.removeButtonView.setBackground(Theme.createRadSelectorDrawable(EmojiPacksAlert.access$3600(r27, r9) & 268435455, 4, 4));
            this.removeButtonView.setText(LocaleController.getString(R.string.StickersRemove));
            this.removeButtonView.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
            this.removeButtonView.setGravity(17);
            this.removeButtonView.setOnClickListener(new EmojiPacksAlert$EmojiPackHeader$$ExternalSyntheticLambda3(this));
            this.removeButtonView.setClickable(false);
            addView(this.removeButtonView, LayoutHelper.createFrameRelatively(-2.0f, 28.0f, 8388661, 0.0f, 15.66f, 5.66f, 0.0f));
            this.removeButtonView.setScaleX(0.0f);
            this.removeButtonView.setScaleY(0.0f);
            this.removeButtonView.setAlpha(0.0f);
            this.removeButtonView.measure(View.MeasureSpec.makeMeasureSpec(99999, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(28.0f), TLObject.FLAG_30));
            float r4 = Math.max(r5, (this.removeButtonView.getMeasuredWidth() + AndroidUtilities.dp(r16)) / AndroidUtilities.density);
        L11:
            float r142 = r4;
            LinkSpanDrawable.LinksTextView r42 = new LinkSpanDrawable.LinksTextView(r28, EmojiPacksAlert.access$3700(r27));
            this.titleView = r42;
            r42.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
            this.titleView.setTypeface(AndroidUtilities.bold());
            LinkSpanDrawable.LinksTextView r3 = this.titleView;
            TextUtils.TruncateAt r43 = TextUtils.TruncateAt.END;
            r3.setEllipsize(r43);
            this.titleView.setSingleLine(true);
            this.titleView.setLines(1);
            this.titleView.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText, EmojiPacksAlert.access$3800(r27)));
            this.titleView.setTextColor(EmojiPacksAlert.access$3900(r27, Theme.key_dialogTextBlack));
            LinkSpanDrawable.LinksTextView r32 = this.titleView;
            if (r29 == false) goto L16;
            r32.setTextSize(1, 20.0f);
            addView(this.titleView, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, 8388659, 12.0f, 11.0f, r142, 0.0f));
        L17:
            if (r29 == true) goto L19;
            TextView r33 = new TextView(r28);
            this.subtitleView = r33;
            r33.setTextSize(1, 13.0f);
            this.subtitleView.setTextColor(EmojiPacksAlert.access$4000(r27, Theme.key_dialogTextGray2));
            this.subtitleView.setEllipsize(r43);
            this.subtitleView.setSingleLine(true);
            this.subtitleView.setLines(1);
            addView(this.subtitleView, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, 8388659, 8.0f, 31.66f, r142, 0.0f));
        L19:
            if (r29 == false) goto L22;
            AnonymousClass2 r0 = new AnonymousClass2(this, r28, null, 0, EmojiPacksAlert.access$4100(r27, Theme.key_sheet_other), EmojiPacksAlert.access$4200(r27), r27);
            this.optionsButton = r0;
            r0.setLongClickEnabled(true);
            this.optionsButton.setShowSubmenuByMove(true);
            this.optionsButton.setSubMenuOpenSide(2);
            this.optionsButton.setIcon(R.drawable.ic_ab_other);
            this.optionsButton.setBackgroundDrawable(Theme.createSelectorDrawable(EmojiPacksAlert.access$4300(r27, Theme.key_player_actionBarSelector), 1));
            addView(this.optionsButton, LayoutHelper.createFrame(40, 40.0f, 53, 0.0f, 5.0f, 5.0f - (EmojiPacksAlert.access$4400(r27) / AndroidUtilities.density), 0.0f));
            this.optionsButton.addSubItem(1, R.drawable.msg_share, LocaleController.getString(R.string.StickersShare));
            this.optionsButton.addSubItem(2, R.drawable.msg_link, LocaleController.getString(R.string.CopyLink));
            this.optionsButton.addSubItem(3, R.drawable.msg_openprofile, LocaleController.getString(R.string.ChannelCreator));
            this.optionsButton.setOnClickListener(new EmojiPacksAlert$EmojiPackHeader$$ExternalSyntheticLambda4(this));
            this.optionsButton.setDelegate(new EmojiPacksAlert$EmojiPackHeader$$ExternalSyntheticLambda5(r27));
            this.optionsButton.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
            return;
        L22:
            return;
        L16:
            r32.setTextSize(1, 17.0f);
            addView(this.titleView, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, 8388659, 6.0f, 10.0f, r142, 0.0f));
            goto L17
        L7:
            if (LocaleUtils.canUseLocalPremiumEmojis(EmojiPacksAlert.access$3100(r27)) == true) goto L9;
            r16 = 16.0f;
            PremiumButtonView r64 = new PremiumButtonView(r28, AndroidUtilities.dp(4.0f), false, EmojiPacksAlert.access$3200(r27));
            this.unlockButtonView = r64;
            r64.setButton(LocaleController.getString(R.string.Unlock), new EmojiPacksAlert$EmojiPackHeader$$ExternalSyntheticLambda1(this));
            this.unlockButtonView.setIcon(R.raw.unlock_icon);
            ViewGroup.MarginLayoutParams r65 = (ViewGroup.MarginLayoutParams) this.unlockButtonView.getIconView().getLayoutParams();
            r65.leftMargin = AndroidUtilities.dp(1.0f);
            r65.topMargin = AndroidUtilities.dp(1.0f);
            int r92 = AndroidUtilities.dp(20.0f);
            r65.height = r92;
            r65.width = r92;
            ((ViewGroup.MarginLayoutParams) this.unlockButtonView.getTextView().getLayoutParams()).leftMargin = AndroidUtilities.dp(3.0f);
            this.unlockButtonView.getChildAt(0).setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
            addView(this.unlockButtonView, LayoutHelper.createFrameRelatively(-2.0f, 28.0f, 8388661, 0.0f, 15.66f, 5.66f, 0.0f));
            this.unlockButtonView.measure(View.MeasureSpec.makeMeasureSpec(99999, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(28.0f), TLObject.FLAG_30));
            r14 = (this.unlockButtonView.getMeasuredWidth() + AndroidUtilities.dp(16.0f)) / AndroidUtilities.density;
            goto L10
        L12:
            r4 = 32.0f;
            goto L11
        }

        private /* synthetic */ void lambda$new$0(View r3) {
            EmojiPacksAlert.m10231$$Nest$fputpremiumButtonClicked(this.this$0, SystemClock.elapsedRealtime());
            this.this$0.showPremiumAlert();
        }

        private /* synthetic */ void lambda$new$1(View r3) {
            EmojiPacksAlert.installSet(this.dummyFragment, this.set, true);
            toggle(true, true);
        }

        private /* synthetic */ void lambda$new$2() {
            toggle(true, true);
        }

        private /* synthetic */ void lambda$new$3(View r4) {
            EmojiPacksAlert.uninstallSet(this.dummyFragment, this.set, true, new EmojiPacksAlert$EmojiPackHeader$$ExternalSyntheticLambda6(this), true);
            toggle(false, true);
        }

        private /* synthetic */ void lambda$new$4(View r1) {
            this.optionsButton.toggleSubMenu();
        }

        private /* synthetic */ void lambda$toggle$5(ValueAnimator r3) {
            float r32 = ((Float) r3.getAnimatedValue()).floatValue();
            this.toggleT = r32;
            this.addButtonView.setScaleX(1.0f - r32);
            this.addButtonView.setScaleY(1.0f - this.toggleT);
            this.addButtonView.setAlpha(1.0f - this.toggleT);
            this.removeButtonView.setScaleX(this.toggleT);
            this.removeButtonView.setScaleY(this.toggleT);
            this.removeButtonView.setAlpha(this.toggleT);
        }

        private void toggle(boolean r4, boolean r5) {
            if (this.toggled == r4) goto L49;
            this.toggled = r4;
            ValueAnimator r0 = this.animator;
            if (r0 == null) goto L8;
            r0.cancel();
            this.animator = null;
        L8:
            TextView r02 = this.addButtonView;
            if (r02 != null) goto L11;
            return;
        L11:
            if (this.removeButtonView == null) goto L50;
            r02.setClickable(!r4);
            this.removeButtonView.setClickable(r4);
            float r03 = 0.0f;
            if (r5 == false) goto L20;
            float r52 = this.toggleT;
            if (r4 == false) goto L18;
            r03 = 1.0f;
        L18:
            ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{r52, r03});
            this.animator = r42;
            r42.addUpdateListener(new EmojiPacksAlert$EmojiPackHeader$$ExternalSyntheticLambda0(this));
            this.animator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.animator.setDuration(250);
            this.animator.start();
            return;
        L20:
            if (r4 == false) goto L22;
            float r53 = 1.0f;
        L23:
            this.toggleT = r53;
            TextView r54 = this.addButtonView;
            if (r4 == false) goto L26;
            float r2 = 0.0f;
        L27:
            r54.setScaleX(r2);
            TextView r55 = this.addButtonView;
            if (r4 == false) goto L30;
            float r22 = 0.0f;
        L31:
            r55.setScaleY(r22);
            TextView r56 = this.addButtonView;
            if (r4 == false) goto L34;
            float r23 = 0.0f;
        L35:
            r56.setAlpha(r23);
            TextView r57 = this.removeButtonView;
            if (r4 == false) goto L38;
            float r24 = 1.0f;
        L39:
            r57.setScaleX(r24);
            TextView r58 = this.removeButtonView;
            if (r4 == false) goto L42;
            float r25 = 1.0f;
        L43:
            r58.setScaleY(r25);
            TextView r3 = this.removeButtonView;
            if (r4 == false) goto L46;
            r03 = 1.0f;
        L46:
            r3.setAlpha(r03);
            return;
        L42:
            r25 = 0.0f;
            goto L43
        L38:
            r24 = 0.0f;
            goto L39
        L34:
            r23 = 1.0f;
            goto L35
        L30:
            r22 = 1.0f;
            goto L31
        L26:
            r2 = 1.0f;
            goto L27
        L22:
            r53 = 0.0f;
            goto L23
        L50:
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            if (this.single == false) goto L5;
            float r32 = 42.0f;
        L6:
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r32), TLObject.FLAG_30));
            return;
        L5:
            r32 = 56.0f;
            goto L6
        }

        public void set(TLRPC.TL_messages_stickerSet r10, int r11, boolean r12) {
            this.set = r10;
            SpannableStringBuilder r0 = null;
            if (r10 != null) goto L5;
        L34:
            this.titleView.setText(null);
        L35:
            TextView r02 = this.subtitleView;
            if (r02 == null) goto L46;
            if (r10 == null) goto L44;
            TLRPC.StickerSet r2 = r10.set;
            if (r2 == null) goto L44;
            if (r2.emojis == true) goto L44;
            r02.setText(LocaleController.formatPluralString("Stickers", r11, new Object[0]));
        L44:
            r02.setText(LocaleController.formatPluralString("EmojiCount", r11, new Object[0]));
        L46:
            if (r12 == true) goto L48;
        L59:
            PremiumButtonView r122 = this.unlockButtonView;
            if (r122 == null) goto L62;
            r122.setVisibility(8);
        L62:
            TextView r112 = this.addButtonView;
            if (r112 == null) goto L65;
            r112.setVisibility(0);
        L65:
            TextView r113 = this.removeButtonView;
            if (r113 == null) goto L68;
            r113.setVisibility(0);
        L68:
            if (r10 != null) goto L70;
        L72:
            boolean r102 = false;
        L73:
            toggle(r102, false);
            return;
        L70:
            if (MediaDataController.getInstance(EmojiPacksAlert.access$4800(this.this$0)).isStickerPackInstalled(r10.set.id) == false) goto L72;
            r102 = true;
            goto L73
        L48:
            if (this.unlockButtonView == null) goto L59;
            if (UserConfig.getInstance(EmojiPacksAlert.access$4600(this.this$0)).isPremium() == true) goto L59;
            if (LocaleUtils.canUseLocalPremiumEmojis(EmojiPacksAlert.access$4700(this.this$0)) == true) goto L59;
            this.unlockButtonView.setVisibility(0);
            TextView r103 = this.addButtonView;
            if (r103 == null) goto L56;
            r103.setVisibility(8);
        L56:
            TextView r9 = this.removeButtonView;
            if (r9 == null) goto L84;
            r9.setVisibility(8);
            return;
        L84:
            return;
        L5:
            if (r10.set == null) goto L34;
        L9:
            e = e;
        L28:
            FileLog.e(e);
            SpannableStringBuilder r3 = r0;
        L29:
            LinkSpanDrawable.LinksTextView r03 = this.titleView;
            CharSequence r32 = r3;
            if (r3 != null) goto L33;
            r32 = r10.set.title;
        L33:
            r03.setText(r32);
            goto L35
        L7:
            if (EmojiPacksAlert.m10237$$Nest$sfgeturlPattern() != null) goto L11;
            EmojiPacksAlert.m10238$$Nest$sfputurlPattern(Pattern.compile("@[a-zA-Z\\d_]{1,32}"));     // Catch: Exception -> L9
        L11:
            Matcher r22 = EmojiPacksAlert.m10237$$Nest$sfgeturlPattern().matcher(r10.set.title);     // Catch: Exception -> L9
            r3 = null;
        L77:
            SpannableStringBuilder r33 = r3;
            if (r22.find() == false) goto L29;
            if (r3 != null) goto L23;
            SpannableStringBuilder r4 = new SpannableStringBuilder(r10.set.title);     // Catch: Exception -> L21
            this.titleView.setMovementMethod(new LinkMovementMethodMy(null));     // Catch: Exception -> L19
            r33 = r4;
        L19:
            e = e;
            r0 = r4;
        L23:
            int r42 = r22.start();     // Catch: Exception -> L21
            int r5 = r22.end();     // Catch: Exception -> L21
            if (r10.set.title.charAt(r42) == '@') goto L26;
            r42 = r42 + 1;     // Catch: Exception -> L21
        L26:
            r33.setSpan(new AnonymousClass3(this, r10.set.title.subSequence(r42 + 1, r5).toString()), r42, r5, 0);     // Catch: Exception -> L21
            r3 = r33;
        L21:
            e = e;
            r0 = r3;
            goto L28
        }
    }

    public class EmojiPacksLoader implements NotificationCenter.NotificationCenterDelegate {
        private int currentAccount;
        public ArrayList<EmojiView.CustomEmoji>[] data;
        public ArrayList<TLRPC.InputStickerSet> inputStickerSets;
        final int loadingStickersCount;
        public TLObject parentObject;
        private boolean started;
        public ArrayList<TLRPC.TL_messages_stickerSet> stickerSets;
        final /* synthetic */ EmojiPacksAlert this$0;

        public static /* synthetic */ void $r8$lambda$3e6gROIDHYk_KnGjlYJyrtD4w7Y(EmojiPacksLoader r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$init$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$5JkuVCmb5gGVQS42_uvlTf_BWac(EmojiPacksLoader r0) {
            r0.lambda$init$2();
        }

        public static /* synthetic */ void $r8$lambda$6sKouT2_FgCmmDhPbC4qUDMD6lI(EmojiPacksLoader r0, boolean[] r1, TLRPC.TL_messages_stickerSet r2) {
            r0.lambda$init$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$SZcICe4KCOO1RhTYzTyNJV_Qt_A(EmojiPacksLoader r0) {
            r0.lambda$init$4();
        }

        public static /* synthetic */ void $r8$lambda$qmWWiUhonMgGoiaIFrYrF4yRqS4(EmojiPacksLoader r0, TLRPC.TL_error r1, TLObject r2) {
            r0.lambda$init$0(r1, r2);
        }

        public EmojiPacksLoader(EmojiPacksAlert r1, int r2, ArrayList<TLRPC.InputStickerSet> r3, TLObject r4) {
            this.this$0 = r1;
            this.loadingStickersCount = 12;
            this.started = false;
            this.currentAccount = r2;
            if (r3 != null) goto L6;
            if (r4 != null) goto L6;
            r3 = new ArrayList();
        L6:
            this.inputStickerSets = r3;
            this.parentObject = r4;
        }

        private /* synthetic */ void lambda$init$0(TLRPC.TL_error r3, TLObject r4) {
            if (r3 == null) goto L4;
        L20:
            this.this$0.dismiss();
            if (EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0) != null) goto L23;
            return;
        L23:
            if (EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0).getParentActivity() == null) goto L31;
            BulletinFactory.of(EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0)).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
            return;
        L31:
            return;
        L4:
            if ((r4 instanceof Vector) == false) goto L20;
            Vector r42 = (Vector) r4;
            if (this.inputStickerSets != null) goto L9;
            this.inputStickerSets = new ArrayList();
        L9:
            int r32 = 0;
        L11:
            if (r32 >= r42.objects.size()) goto L18;
            Object r0 = r42.objects.get(r32);
            if ((r0 instanceof TLRPC.StickerSetCovered) == false) goto L17;
            TLRPC.StickerSet r02 = ((TLRPC.StickerSetCovered) r0).set;
            if (r02 == null) goto L17;
            this.inputStickerSets.add(MediaDataController.getInputStickerSet(r02));
        L17:
            r32 = r32 + 1;
            goto L11
        L18:
            this.parentObject = null;
            init();
        }

        private /* synthetic */ void lambda$init$1(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new EmojiPacksAlert$EmojiPacksLoader$$ExternalSyntheticLambda3(this, r3, r2));
        }

        private /* synthetic */ void lambda$init$2() {
            this.this$0.dismiss();
            if (EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0) != null) goto L5;
            return;
        L5:
            if (EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0).getParentActivity() == null) goto L9;
            BulletinFactory.of(EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0)).createErrorBulletin(LocaleController.getString(R.string.AddEmojiNotFound)).show();
            return;
        }

        private /* synthetic */ void lambda$init$3(boolean[] r2, TLRPC.TL_messages_stickerSet r3) {
            if (r3 == null) goto L4;
            return;
        L4:
            if (r2[0] == true) goto L8;
            r2[0] = true;
            AndroidUtilities.runOnUIThread(new EmojiPacksAlert$EmojiPacksLoader$$ExternalSyntheticLambda4(this));
            return;
        }

        private /* synthetic */ void lambda$init$4() {
            this.this$0.dismiss();
        }

        private void putStickerSet(int r7, TLRPC.TL_messages_stickerSet r8) {
            if (r7 < 0) goto L33;
            ArrayList<EmojiView.CustomEmoji>[] r0 = this.data;
            if (r7 >= r0.length) goto L40;
            int r2 = 0;
            if (r8 != null) goto L9;
        L30:
            r0[r7] = new ArrayList(12);
        L31:
            if (r2 >= 12) goto L43;
            this.data[r7].add(null);
            r2 = r2 + 1;
            goto L31
        L43:
            return;
        L9:
            if (r8.documents == null) goto L30;
            r0[r7] = new ArrayList();
        L13:
            if (r2 >= r8.documents.size()) goto L41;
            TLRPC.Document r02 = r8.documents.get(r2);
            if (r02 != null) goto L17;
            this.data[r7].add(null);
        L29:
            r2 = r2 + 1;
            goto L13
        L17:
            EmojiView.CustomEmoji r3 = new EmojiView.CustomEmoji();
            r3.emoticon = findEmoticon(r8, r02.id);
            r3.stickerSet = r8;
            r3.documentId = r02.id;
            this.data[r7].add(r3);
            if (EmojiPacksAlert.m10221$$Nest$fgetlimitCount(this.this$0) == false) goto L29;
            TLRPC.StickerSet r03 = r8.set;
            if (r03 != null) goto L22;
        L25:
            int r04 = 16;
        L27:
            if (this.data[r7].size() < r04) goto L29;
            return;
        L22:
            if (r03.emojis == true) goto L25;
            r04 = 10;
            goto L27
        L41:
            return;
        L40:
            return;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r9, int r10, Object... r11) {
            if (r9 != NotificationCenter.groupStickersDidLoad) goto L34;
            int r92 = 0;
        L6:
            if (r92 >= this.stickerSets.size()) goto L27;
            if (this.stickerSets.get(r92) != null) goto L26;
            TLRPC.TL_messages_stickerSet r102 = MediaDataController.getInstance(this.currentAccount).getStickerSet(this.inputStickerSets.get(r92), true);
            if (this.stickerSets.size() != 1) goto L23;
            if (r102 == null) goto L23;
            TLRPC.StickerSet r112 = r102.set;
            if (r112 == null) goto L23;
            if (r112.emojis == true) goto L23;
            this.this$0.dismiss();
            Context r1 = this.this$0.getContext();
            BaseFragment r2 = EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0);
            TLRPC.InputStickerSet r3 = this.inputStickerSets.get(r92);
            if ((EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0) instanceof ChatActivity) == false) goto L20;
            ChatActivityEnterView r93 = ((ChatActivity) EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0)).getChatActivityEnterView();
        L19:
            ChatActivityEnterView r5 = r93;
            new StickersAlert(r1, r2, r3, null, r5, EmojiPacksAlert.access$5000(this.this$0), false).show();
            return;
        L20:
            r93 = null;
        L23:
            this.stickerSets.set(r92, r102);
            if (r102 == null) goto L26;
            putStickerSet(r92, r102);
        L26:
            r92 = r92 + 1;
            goto L6
        L27:
            onUpdate();
            return;
        }

        public String findEmoticon(TLRPC.TL_messages_stickerSet r5, long r6) {
            if (r5 != null) goto L5;
            return null;
        L5:
            int r0 = 0;
        L7:
            if (r0 >= r5.packs.size()) goto L15;
            TLRPC.TL_stickerPack r1 = r5.packs.get(r0);
            ArrayList<Long> r2 = r1.documents;
            if (r2 == null) goto L14;
            if (r2.contains(Long.valueOf(r6)) == false) goto L14;
            return r1.emoticon;
        L14:
            r0 = r0 + 1;
            goto L7
        L15:
            return null;
        }

        public int getItemsCount() {
            int r1 = 0;
            if (this.data != null) goto L5;
            return 0;
        L5:
            int r0 = 0;
        L6:
            ArrayList<EmojiView.CustomEmoji>[] r2 = this.data;
            if (r1 >= r2.length) goto L18;
            ArrayList<EmojiView.CustomEmoji> r3 = r2[r1];
            if (r3 == null) goto L17;
            if (r2.length != 1) goto L15;
            int r22 = r3.size();
        L16:
            r0 = (r0 + r22) + 1;
            goto L17
        L15:
            r22 = Math.min(EmojiPacksAlert.m10218$$Nest$fgetgridLayoutManager(this.this$0).getSpanCount() * 2, this.data[r1].size());
        L17:
            r1 = r1 + 1;
            goto L6
        L18:
            return r0;
        }

        public void init() {
            TLObject r0 = this.parentObject;
            ChatActivityEnterView r2 = null;
            if ((r0 instanceof TLRPC.Photo) == false) goto L5;
        L6:
            ArrayList<TLRPC.InputStickerSet> r02 = this.inputStickerSets;
            if (r02 != null) goto L9;
        L29:
            this.data = new ArrayList[2];
            putStickerSet(0, null);
            putStickerSet(1, null);
            TLRPC.TL_messages_getAttachedStickers r03 = new TLRPC.TL_messages_getAttachedStickers();
            TLObject r1 = this.parentObject;
            if ((r1 instanceof TLRPC.Photo) == false) goto L36;
            TLRPC.Photo r12 = (TLRPC.Photo) r1;
            TLRPC.TL_inputStickeredMediaPhoto r22 = new TLRPC.TL_inputStickeredMediaPhoto();
            TLRPC.TL_inputPhoto r3 = new TLRPC.TL_inputPhoto();
            r22.id = r3;
            r3.id = r12.id;
            r3.access_hash = r12.access_hash;
            byte[] r13 = r12.file_reference;
            r3.file_reference = r13;
            if (r13 != null) goto L34;
            r3.file_reference = new byte[0];
        L34:
            r03.media = r22;
        L41:
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r03, new EmojiPacksAlert$EmojiPacksLoader$$ExternalSyntheticLambda0(this));
            return;
        L36:
            if ((r1 instanceof TLRPC.Document) == false) goto L41;
            TLRPC.Document r14 = (TLRPC.Document) r1;
            TLRPC.TL_inputStickeredMediaDocument r23 = new TLRPC.TL_inputStickeredMediaDocument();
            TLRPC.TL_inputDocument r32 = new TLRPC.TL_inputDocument();
            r23.id = r32;
            r32.id = r14.id;
            r32.access_hash = r14.access_hash;
            byte[] r15 = r14.file_reference;
            r32.file_reference = r15;
            if (r15 != null) goto L40;
            r32.file_reference = new byte[0];
        L40:
            r03.media = r23;
            goto L41
        L9:
            if (r02.isEmpty() == true) goto L29;
        L11:
            this.stickerSets = new ArrayList(this.inputStickerSets.size());
            this.data = new ArrayList[this.inputStickerSets.size()];
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.groupStickersDidLoad);
            boolean[] r04 = new boolean[1];
            int r16 = 0;
        L13:
            if (r16 >= this.data.length) goto L27;
            TLRPC.TL_messages_stickerSet r5 = MediaDataController.getInstance(this.currentAccount).getStickerSet(this.inputStickerSets.get(r16), null, false, new EmojiPacksAlert$EmojiPacksLoader$$ExternalSyntheticLambda1(this, r04));
            if (this.data.length != 1) goto L26;
            if (r5 == null) goto L26;
            TLRPC.StickerSet r6 = r5.set;
            if (r6 == null) goto L26;
            if (r6.emojis == true) goto L26;
            AndroidUtilities.runOnUIThread(new EmojiPacksAlert$EmojiPacksLoader$$ExternalSyntheticLambda2(this));
            Context r4 = this.this$0.getContext();
            BaseFragment r52 = EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0);
            TLRPC.InputStickerSet r62 = this.inputStickerSets.get(r16);
            if ((EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0) instanceof ChatActivity) == false) goto L24;
            r2 = ((ChatActivity) EmojiPacksAlert.m10217$$Nest$fgetfragment(this.this$0)).getChatActivityEnterView();
        L24:
            new StickersAlert(r4, r52, r62, null, r2, EmojiPacksAlert.access$4900(this.this$0), false).show();
            return;
        L26:
            this.stickerSets.add(r5);
            putStickerSet(r16, r5);
            r16 = r16 + 1;
            goto L13
        L27:
            onUpdate();
            return;
        L5:
            if ((r0 instanceof TLRPC.Document) == false) goto L11;
            goto L6
        }

        public abstract void onUpdate();

        public void recycle() {
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.groupStickersDidLoad);
        }

        public void start() {
            if (this.started == false) goto L5;
            return;
        L5:
            this.started = true;
            init();
        }
    }

    public static class LinkMovementMethodMy extends LinkMovementMethod {
        private LinkMovementMethodMy() {
        }

        public /* synthetic */ LinkMovementMethodMy(EmojiPacksAlertIA r1) {
            this();
        }

        @Override // android.text.method.LinkMovementMethod, android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
        public boolean onTouchEvent(TextView r2, Spannable r3, MotionEvent r4) {
            boolean r1 = super.onTouchEvent(r2, r3, r4);     // Catch: Exception -> L10
            if (r4.getAction() != 1) goto L5;
        L8:
            Selection.removeSelection(r3);     // Catch: Exception -> L10
            return r1;
        L5:
            if (r4.getAction() == 3) goto L8;
            return r1;
        L10:
            e = move-exception;
            FileLog.e(e);
            return false;
        }
    }

    public class SeparatorView extends View {
        final /* synthetic */ EmojiPacksAlert this$0;

        public SeparatorView(EmojiPacksAlert r2, Context r3) {
            this.this$0 = r2;
            super(r3);
            setBackgroundColor(EmojiPacksAlert.access$2300(r2, Theme.key_chat_emojiPanelShadowLine));
            RecyclerView.LayoutParams r22 = new RecyclerView.LayoutParams(-1, AndroidUtilities.getShadowHeight());
            ((ViewGroup.MarginLayoutParams) r22).topMargin = AndroidUtilities.dp(14.0f);
            setLayoutParams(r22);
        }
    }

    public static /* synthetic */ void $r8$lambda$0CNAkXPpZF3Nj1UzCRRM9Ajt6DI(EmojiPacksAlert r0, Runnable r1) {
        r0.lambda$onSubItemClick$14(r1);
    }

    public static /* synthetic */ void $r8$lambda$1_OJYGZW8IqaZ5S7v1uI2a5vqlM(EmojiPacksAlert r0, AnimatedEmojiSpan r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$1fvAOqrJS106LZQA04xLvrvva0g(Runnable r0, ArrayList r1) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$DRTuFT6Kev3wPBEh99dSJi5vWhs(EmojiPacksAlert r0, int[] r1, int r2, ArrayList r3, Boolean r4) {
        r0.lambda$updateButton$9(r1, r2, r3, r4);
    }

    public static /* synthetic */ boolean $r8$lambda$EHl3PWTxr0WpQvvdB0OgelcTuSI(EmojiPacksAlert r0, RecyclerListView.OnItemClickListener r1, View r2, MotionEvent r3) {
        return r0.lambda$new$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$J78LGBHBjL4u2ybO4A7CTn5sY_E(EmojiPacksAlert r0, View r1) {
        r0.lambda$new$4(r1);
    }

    /* renamed from: $r8$lambda$Mh3tb7sh-UOlc7hESdFEs2EK3EM, reason: not valid java name */
    public static /* synthetic */ void m10210$r8$lambda$Mh3tb7shUOlc7hESdFEs2EK3EM(TLRPC.StickerSet r11, boolean r12, View r13, BaseFragment r14, TLRPC.TL_messages_stickerSet r15, int r16, Utilities.Callback r17, Runnable r18, TLObject r19, TLRPC.TL_error r20) {
        AndroidUtilities.runOnUIThread(new EmojiPacksAlert$$ExternalSyntheticLambda10(r11, r20, r12, r13, r14, r15, r19, r16, r17, r18));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: $r8$lambda$Mi5pnaOTVrNYesse-_DeFfyypO0, reason: not valid java name */
    public static /* synthetic */ void m10211$r8$lambda$Mi5pnaOTVrNYesse_DeFfyypO0(TLRPC.StickerSet r12, TLRPC.TL_error r13, boolean r14, View r15, BaseFragment r16, TLRPC.TL_messages_stickerSet r17, TLObject r18, int r19, Utilities.Callback r20, Runnable r21) {
        if (r12.masks == false) goto L6;
        int r3 = 1;
    L34:
        if (r13 != null) goto L24;
        if (r14 == false) goto L20;
        if (r15 == null) goto L20;
        Context r7 = r16.getFragmentView().getContext();     // Catch: Exception -> L17
        if (r17 != 0) goto L15;
        TLRPC.StickerSet r8 = r12;
    L16:
        Bulletin.make(r16, new StickerSetBulletinLayout(r7, r8, 2, null, r16.getResourceProvider()), 1500).show();     // Catch: Exception -> L17
        goto L20
    L15:
        r8 = r17;
    L20:
        if ((r18 instanceof TLRPC.TL_messages_stickerSetInstallResultArchive) == false) goto L22;
        MediaDataController.getInstance(r19).processStickerSetInstallResultArchive(r16, true, r3, (TLRPC.TL_messages_stickerSetInstallResultArchive) r18);     // Catch: Exception -> L17
    L22:
        if (r20 == null) goto L32;
        r20.run(Boolean.TRUE);     // Catch: Exception -> L17
    L32:
        MediaDataController.getInstance(r19).loadStickers(r3, false, true, false, new EmojiPacksAlert$$ExternalSyntheticLambda14(r21));
        return;
    L24:
        if (r15 == null) goto L28;
        Toast.makeText(r16.getFragmentView().getContext(), LocaleController.getString(R.string.ErrorOccurred), 0).show();     // Catch: Exception -> L17
        if (r20 == null) goto L32;
        r20.run(Boolean.FALSE);     // Catch: Exception -> L17
        goto L32
    L28:
        if (r20 == null) goto L32;
        r20.run(Boolean.FALSE);     // Catch: Exception -> L17
    L17:
        e = move-exception;
        FileLog.e(e);
        goto L32
    L6:
        if (r12.emojis == false) goto L8;
        r3 = 5;
        goto L34
    L8:
        r3 = 0;
        goto L34
    }

    public static /* synthetic */ void $r8$lambda$OGLswxA7TNfJMA67YAqtBSXL5rs(EmojiPacksAlert r0, ValueAnimator r1) {
        r0.lambda$loadAnimation$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$R19NU60NbCzdqLNxTziiKFZWtu0(EmojiPacksAlert r0, ArrayList r1, BaseFragment r2, Theme.ResourcesProvider r3, View r4, int r5) {
        r0.lambda$new$0(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$VKxvX6gwltt-dBOznV3Wb-TEkVE, reason: not valid java name */
    public static /* synthetic */ void m10212$r8$lambda$VKxvX6gwlttdBOznV3WbTEkVE(EmojiPacksAlert r0, ArrayList r1, View r2) {
        r0.lambda$updateButton$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$XDp65xP8whiDR5fvNX2IVnrWusY(EmojiPacksAlert r0, ProfileActivity r1) {
        r0.lambda$onSubItemClick$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$YqDdfH8r3QBSMifxsj48SLtuCiY(EmojiPacksAlert r0, ArrayList r1, View r2) {
        r0.lambda$updateButton$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$c9EZH4JLP45T4EFagPkVYWyPeM4(EmojiPacksAlert r0, AlertDialog r1, TLRPC.TL_messages_stickerSet r2, TLRPC.User r3) {
        r0.lambda$onSubItemClick$13(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$l3j_pdC8zZQMdWTWm5prlhFcKlI(EmojiPacksAlert r0, DialogInterface r1) {
        r0.lambda$onSubItemClick$15(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$zcy2WozwwNWu09tsMSdSsrHYNxg(EmojiPacksAlert r0, Context r1, View r2, int r3) {
        return r0.lambda$new$2(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Adapter m10213$$Nest$fgetadapter(EmojiPacksAlert r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetanimatedEmojiDrawables, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m10214$$Nest$fgetanimatedEmojiDrawables(EmojiPacksAlert r0) {
        return r0.animatedEmojiDrawables;
    }

    /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContentView m10215$$Nest$fgetcontentView(EmojiPacksAlert r0) {
        return r0.contentView;
    }

    /* renamed from: -$$Nest$fgetcustomEmojiPacks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiPacksLoader m10216$$Nest$fgetcustomEmojiPacks(EmojiPacksAlert r0) {
        return r0.customEmojiPacks;
    }

    /* renamed from: -$$Nest$fgetfragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m10217$$Nest$fgetfragment(EmojiPacksAlert r0) {
        return r0.fragment;
    }

    /* renamed from: -$$Nest$fgetgridLayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GridLayoutManager m10218$$Nest$fgetgridLayoutManager(EmojiPacksAlert r0) {
        return r0.gridLayoutManager;
    }

    /* renamed from: -$$Nest$fgethasDescription, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10219$$Nest$fgethasDescription(EmojiPacksAlert r0) {
        return r0.hasDescription;
    }

    /* renamed from: -$$Nest$fgethighlightAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedFloat m10220$$Nest$fgethighlightAlpha(EmojiPacksAlert r0) {
        return r0.highlightAlpha;
    }

    /* renamed from: -$$Nest$fgetlimitCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10221$$Nest$fgetlimitCount(EmojiPacksAlert r0) {
        return r0.limitCount;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m10222$$Nest$fgetlistView(EmojiPacksAlert r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetpaddingView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m10223$$Nest$fgetpaddingView(EmojiPacksAlert r0) {
        return r0.paddingView;
    }

    /* renamed from: -$$Nest$fgetpreviewDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContentPreviewViewer.ContentPreviewViewerDelegate m10224$$Nest$fgetpreviewDelegate(EmojiPacksAlert r0) {
        return r0.previewDelegate;
    }

    /* renamed from: -$$Nest$fgetprogressDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CircularProgressDrawable m10225$$Nest$fgetprogressDrawable(EmojiPacksAlert r0) {
        return r0.progressDrawable;
    }

    /* renamed from: -$$Nest$fgetremoveButtonView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m10226$$Nest$fgetremoveButtonView(EmojiPacksAlert r0) {
        return r0.removeButtonView;
    }

    /* renamed from: -$$Nest$fgetshadowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m10227$$Nest$fgetshadowView(EmojiPacksAlert r0) {
        return r0.shadowView;
    }

    /* renamed from: -$$Nest$fputanimatedEmojiDrawables, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10228$$Nest$fputanimatedEmojiDrawables(EmojiPacksAlert r0, LongSparseArray r1) {
        r0.animatedEmojiDrawables = r1;
    }

    /* renamed from: -$$Nest$fputhasDescription, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10229$$Nest$fputhasDescription(EmojiPacksAlert r0, boolean r1) {
        r0.hasDescription = r1;
    }

    /* renamed from: -$$Nest$fputlastY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10230$$Nest$fputlastY(EmojiPacksAlert r0, float r1) {
        r0.lastY = r1;
    }

    /* renamed from: -$$Nest$fputpremiumButtonClicked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10231$$Nest$fputpremiumButtonClicked(EmojiPacksAlert r0, long r1) {
        r0.premiumButtonClicked = r1;
    }

    /* renamed from: -$$Nest$mgetAdaptiveEmojiColorFilter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ColorFilter m10232$$Nest$mgetAdaptiveEmojiColorFilter(EmojiPacksAlert r0, int r1) {
        return r0.getAdaptiveEmojiColorFilter(r1);
    }

    /* renamed from: -$$Nest$mgetListTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10233$$Nest$mgetListTop(EmojiPacksAlert r0) {
        return r0.getListTop();
    }

    /* renamed from: -$$Nest$monSubItemClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10234$$Nest$monSubItemClick(EmojiPacksAlert r0, int r1) {
        r0.onSubItemClick(r1);
    }

    /* renamed from: -$$Nest$mupdateButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10235$$Nest$mupdateButton(EmojiPacksAlert r0) {
        r0.updateButton();
    }

    /* renamed from: -$$Nest$mupdateLightStatusBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10236$$Nest$mupdateLightStatusBar(EmojiPacksAlert r0, boolean r1) {
        r0.updateLightStatusBar(r1);
    }

    /* renamed from: -$$Nest$sfgeturlPattern, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Pattern m10237$$Nest$sfgeturlPattern() {
        return urlPattern;
    }

    /* renamed from: -$$Nest$sfputurlPattern, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10238$$Nest$sfputurlPattern(Pattern r0) {
        urlPattern = r0;
    }

    public EmojiPacksAlert(BaseFragment r7, Context r8, Theme.ResourcesProvider r9, ArrayList<TLRPC.InputStickerSet> r10) {
        this(r7, r8, r9, r10, null);
    }

    private EmojiPacksAlert(BaseFragment r21, Context r22, Theme.ResourcesProvider r23, ArrayList<TLRPC.InputStickerSet> r24, TLObject r25) {
        if (r21 != null) goto L5;
    L7:
        Theme.ResourcesProvider r4 = r23;
    L8:
        super(r22, false, r4);
        this.highlightStartPosition = -1;
        this.highlightEndPosition = -1;
        this.previewDelegate = new AnonymousClass1(this);
        this.highlightIndex = -1;
        this.shown = false;
        this.loaded = true;
        this.cancelSearchCreatorRunnable = null;
        this.fragment = r21;
        fixNavigationBar();
        if (r24 != null) goto L11;
    L15:
        this.customEmojiPacks = new AnonymousClass2(this, this.currentAccount, r24, r25);
        float r9 = AndroidUtilities.dp(32.0f);
        float r10 = AndroidUtilities.dp(3.5f);
        int r11 = Theme.key_featuredStickers_addButton;
        this.progressDrawable = new CircularProgressDrawable(r9, r10, getThemedColor(r11));
        ContentView r8 = new ContentView(this, r22);
        this.contentView = r8;
        this.containerView = r8;
        this.paddingView = new AnonymousClass3(this, r22);
        AnonymousClass4 r14 = new AnonymousClass4(this, r22, this.resourcesProvider);
        this.listView = r14;
        this.highlightAlpha = new AnimatedFloat(0.0f, r14, 0, 1250, CubicBezierInterpolator.EASE_IN);
        ViewGroup r82 = this.containerView;
        int r92 = this.backgroundPaddingLeft;
        r82.setPadding(r92, AndroidUtilities.statusBarHeight, r92, 0);
        this.containerView.setClipChildren(false);
        this.containerView.setClipToPadding(false);
        this.containerView.setWillNotDraw(false);
        this.listView.setWillNotDraw(false);
        this.listView.setSelectorRadius(AndroidUtilities.dp(6.0f));
        this.listView.setSelectorDrawableColor(Theme.getColor(Theme.key_listSelector, r4));
        RecyclerListView r83 = this.listView;
        float r93 = 8.0f;
        int r102 = AndroidUtilities.dp(8.0f);
        int r12 = AndroidUtilities.dp(8.0f);
        if (this.limitCount == true) goto L19;
        r93 = 68.0f;
    L19:
        r83.setPadding(r102, 0, r12, AndroidUtilities.dp(r93));
        RecyclerListView r84 = this.listView;
        GridLayoutManager r94 = new GridLayoutManager(r22, 8);
        this.gridLayoutManager = r94;
        r84.setLayoutManager(r94);
        this.listView.addItemDecoration(new AnonymousClass5(this));
        this.listView.addOnScrollListener(new AnonymousClass6(this));
        RecyclerListView r85 = this.listView;
        EmojiPacksAlert$$ExternalSyntheticLambda0 r95 = new EmojiPacksAlert$$ExternalSyntheticLambda0(this, r24, r21, r4);
        r85.setOnItemClickListener(r95);
        this.listView.setOnItemLongClickListener(new EmojiPacksAlert$$ExternalSyntheticLambda1(this, r22));
        this.listView.setOnTouchListener(new EmojiPacksAlert$$ExternalSyntheticLambda2(this, r95));
        this.gridLayoutManager.setReverseLayout(false);
        this.gridLayoutManager.setSpanSizeLookup(new AnonymousClass8(this));
        this.scrollHelper = new RecyclerAnimationScrollHelper(this.listView, this.gridLayoutManager);
        this.containerView.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        View r1 = new View(r22);
        this.shadowView = r1;
        r1.setBackgroundColor(Theme.getColor(Theme.key_dialogShadowLine));
        this.containerView.addView(this.shadowView, LayoutHelper.createFrame(-1.0f, 1.0f / AndroidUtilities.density, 80));
        this.shadowView.setTranslationY(-AndroidUtilities.dp(68.0f));
        FrameLayout r13 = new FrameLayout(r22);
        this.buttonsView = r13;
        r13.setBackgroundColor(getThemedColor(Theme.key_dialogBackground));
        this.containerView.addView(this.buttonsView, LayoutHelper.createFrame(-1, 68, 87));
        TextView r15 = new TextView(r22);
        this.addButtonView = r15;
        r15.setVisibility(8);
        this.addButtonView.setBackground(Theme.AdaptiveRipple.filledRect(getThemedColor(r11), new float[]{24.0f}));
        this.addButtonView.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
        this.addButtonView.setTypeface(AndroidUtilities.bold());
        this.addButtonView.setGravity(17);
        ScaleStateListAnimator.apply(this.addButtonView, 0.02f, 1.2f);
        this.buttonsView.addView(this.addButtonView, LayoutHelper.createFrame(-1, 48.0f, 80, 12.0f, 10.0f, 12.0f, 10.0f));
        TextView r16 = new TextView(r22);
        this.removeButtonView = r16;
        r16.setVisibility(8);
        TextView r17 = this.removeButtonView;
        int r42 = Theme.key_text_RedBold;
        r17.setBackground(Theme.createRadSelectorDrawable(getThemedColor(r42) & 268435455, 0, 0));
        this.removeButtonView.setTextColor(getThemedColor(r42));
        this.removeButtonView.setTypeface(AndroidUtilities.bold());
        this.removeButtonView.setGravity(17);
        this.removeButtonView.setClickable(true);
        this.buttonsView.addView(this.removeButtonView, LayoutHelper.createFrame(-1, -1.0f, 80, 0.0f, 0.0f, 0.0f, 19.0f));
        PremiumButtonView r18 = new PremiumButtonView(r22, false, this.resourcesProvider);
        this.premiumButtonView = r18;
        r18.setButton(LocaleController.getString(R.string.UnlockPremiumEmoji), new EmojiPacksAlert$$ExternalSyntheticLambda3(this));
        this.premiumButtonView.setIcon(R.raw.unlock_icon);
        this.premiumButtonView.buttonLayout.setClickable(true);
        this.buttonsView.addView(this.premiumButtonView, LayoutHelper.createFrame(-1, 48.0f, 80, 12.0f, 10.0f, 12.0f, 10.0f));
        return;
    L11:
        if (r24.size() <= 1) goto L13;
        boolean r86 = true;
    L14:
        this.limitCount = r86;
        goto L15
    L13:
        r86 = false;
        goto L14
    L5:
        if (r21.getResourceProvider() == null) goto L7;
        r4 = r21.getResourceProvider();
        goto L8
    }

    public static /* synthetic */ ViewGroup access$000(EmojiPacksAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(EmojiPacksAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$1000(EmojiPacksAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1100(EmojiPacksAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$1200(EmojiPacksAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1300(EmojiPacksAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$1400(EmojiPacksAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ViewGroup access$1500(EmojiPacksAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$1600(EmojiPacksAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$1700(EmojiPacksAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ ViewGroup access$1800(EmojiPacksAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$1900(EmojiPacksAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$200(EmojiPacksAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2000(EmojiPacksAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2100(EmojiPacksAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ ViewGroup access$2200(EmojiPacksAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$2300(EmojiPacksAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2400(EmojiPacksAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2500(EmojiPacksAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2600(EmojiPacksAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ViewGroup access$2700(EmojiPacksAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$2800(EmojiPacksAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2900(EmojiPacksAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$300(EmojiPacksAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$3000(EmojiPacksAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3100(EmojiPacksAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$3200(EmojiPacksAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$3300(EmojiPacksAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$3400(EmojiPacksAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$3500(EmojiPacksAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$3600(EmojiPacksAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$3700(EmojiPacksAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$3800(EmojiPacksAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$3900(EmojiPacksAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$400(EmojiPacksAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$4000(EmojiPacksAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$4100(EmojiPacksAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4200(EmojiPacksAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$4300(EmojiPacksAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$4400(EmojiPacksAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$4500(EmojiPacksAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4600(EmojiPacksAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4700(EmojiPacksAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4800(EmojiPacksAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4900(EmojiPacksAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$500(EmojiPacksAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$5000(EmojiPacksAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$600(EmojiPacksAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$700(EmojiPacksAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$800(EmojiPacksAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$900(EmojiPacksAlert r0) {
        return r0.resourcesProvider;
    }

    private ColorFilter getAdaptiveEmojiColorFilter(int r3) {
        if (r3 == this.adaptiveEmojiColor) goto L5;
    L6:
        this.adaptiveEmojiColor = r3;
        this.adaptiveEmojiColorFilter = new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN);
    L8:
        return this.adaptiveEmojiColorFilter;
    L5:
        if (this.adaptiveEmojiColorFilter != null) goto L8;
        goto L6
    }

    private int getListTop() {
        if (this.containerView != null) goto L5;
        return 0;
    L5:
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L17;
        if (r0.getChildCount() < 1) goto L17;
        View r02 = this.listView.getChildAt(0);
        View r1 = this.paddingView;
        if (r02 == r1) goto L15;
        return this.containerView.getPaddingTop();
    L15:
        return r1.getBottom() + ((int) this.listView.getY());
    L17:
        return this.containerView.getPaddingTop();
    }

    public static void installSet(BaseFragment r1, TLObject r2, boolean r3) {
        installSet(r1, r2, r3, null, null);
    }

    public static void installSet(BaseFragment r10, TLObject r11, boolean r12, Utilities.Callback<Boolean> r13, Runnable r14) {
        if (r10 != null) goto L5;
        int r0 = UserConfig.selectedAccount;
    L4:
        int r7 = r0;
        TLRPC.StickerSet r02 = null;
        if (r10 != null) goto L9;
        View r4 = null;
    L10:
        if (r11 != null) goto L13;
        return;
    L13:
        if ((r11 instanceof TLRPC.TL_messages_stickerSet) == false) goto L15;
        TLRPC.TL_messages_stickerSet r6 = (TLRPC.TL_messages_stickerSet) r11;
    L16:
        if (r6 == null) goto L20;
        r02 = r6.set;
    L18:
        TLRPC.StickerSet r2 = r02;
        if (r2 != null) goto L25;
        return;
    L25:
        if (MediaDataController.getInstance(r7).cancelRemovingStickerSet(r2.id) == false) goto L29;
        if (r13 == null) goto L31;
        r13.run(Boolean.TRUE);
        return;
    L31:
        return;
    L29:
        TLRPC.TL_messages_installStickerSet r112 = new TLRPC.TL_messages_installStickerSet();
        TLRPC.TL_inputStickerSetID r03 = new TLRPC.TL_inputStickerSetID();
        r112.stickerset = r03;
        r03.id = r2.id;
        r03.access_hash = r2.access_hash;
        ConnectionsManager.getInstance(r7).sendRequest(r112, new EmojiPacksAlert$$ExternalSyntheticLambda9(r2, r12, r4, r10, r6, r7, r13, r14));
        return;
    L20:
        if ((r11 instanceof TLRPC.StickerSet) == false) goto L18;
        r02 = (TLRPC.StickerSet) r11;
        goto L18
    L15:
        r6 = null;
        goto L16
    L9:
        r4 = r10.getFragmentView();
        goto L10
    L5:
        r0 = r10.getCurrentAccount();
        goto L4
    }

    private /* synthetic */ void lambda$loadAnimation$8(ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.loadT = r22;
        this.listView.setAlpha(r22);
        this.addButtonView.setAlpha(this.loadT);
        this.removeButtonView.setAlpha(this.loadT);
        this.containerView.invalidate();
    }

    private /* synthetic */ void lambda$new$0(ArrayList r8, BaseFragment r9, Theme.ResourcesProvider r10, View r11, int r12) {
        TLRPC.TL_messages_stickerSet r0 = null;
        int r1 = 0;
        if (r8 == null) goto L31;
        if (r8.size() <= 1) goto L31;
        if ((SystemClock.elapsedRealtime() - this.premiumButtonClicked) < 250) goto L62;
        int r82 = 0;
    L11:
        ArrayList<EmojiView.CustomEmoji>[] r112 = this.customEmojiPacks.data;
        if (r1 >= r112.length) goto L20;
        int r113 = r112[r1].size();
        if (this.customEmojiPacks.data.length <= 1) goto L16;
        r113 = Math.min(this.gridLayoutManager.getSpanCount() * 2, r113);
    L16:
        r82 = r82 + (r113 + 2);
        if (r12 < r82) goto L20;
        r1 = r1 + 1;
    L20:
        ArrayList<TLRPC.TL_messages_stickerSet> r83 = this.customEmojiPacks.stickerSets;
        if (r83 != null) goto L23;
    L26:
        if (r0 != null) goto L28;
        return;
    L28:
        if (r0.set == null) goto L58;
        ArrayList r6 = new ArrayList();
        TLRPC.TL_inputStickerSetID r84 = new TLRPC.TL_inputStickerSetID();
        TLRPC.StickerSet r114 = r0.set;
        r84.id = r114.id;
        r84.access_hash = r114.access_hash;
        r6.add(r84);
        new AnonymousClass7(this, r9, getContext(), r10, r6).show();
        return;
    L58:
        return;
    L23:
        if (r1 >= r83.size()) goto L26;
        r0 = this.customEmojiPacks.stickerSets.get(r1);
        goto L26
    L62:
        return;
    L31:
        ActionBarPopupWindow r85 = this.popupWindow;
        if (r85 == null) goto L36;
        r85.dismiss();
        this.popupWindow = null;
        return;
    L36:
        if ((r9 instanceof ChatActivity) == true) goto L38;
        return;
    L38:
        if (((ChatActivity) r9).getChatActivityEnterView().getVisibility() == 0) goto L40;
        return;
    L40:
        if ((r11 instanceof EmojiImageView) == false) goto L61;
        AnimatedEmojiSpan r86 = ((EmojiImageView) r11).span;
        TLRPC.Document r102 = r86.document;     // Catch: Exception -> L50
        if (r102 != null) goto L45;
        r102 = AnimatedEmojiDrawable.findDocument(this.currentAccount, r86.getDocumentId());     // Catch: Exception -> L50
    L45:
        SpannableString r92 = new SpannableString(MessageObject.findAnimatedEmojiEmoticon(r102));     // Catch: Exception -> L50
        r92.setSpan(r86, 0, r92.length(), 33);     // Catch: Exception -> L50
        ((ChatActivity) r9).getChatActivityEnterView().messageEditText.getText().append(r92);     // Catch: Exception -> L50
        onCloseByLink();     // Catch: Exception -> L50
        dismiss();     // Catch: Exception -> L50
    L51:
        r11.performHapticFeedback(VibratorUtils.getType(3), 1);     // Catch: Exception -> L49
        return;
    L63:
        return;
    }

    private /* synthetic */ void lambda$new$1(AnimatedEmojiSpan r4, View r5) {
        ActionBarPopupWindow r52 = this.popupWindow;
        if (r52 == null) goto L10;
        r52.dismiss();
        this.popupWindow = null;
        SpannableString r53 = new SpannableString(MessageObject.findAnimatedEmojiEmoticon(AnimatedEmojiDrawable.findDocument(this.currentAccount, r4.getDocumentId())));
        r53.setSpan(r4, 0, r53.length(), 33);
        if (AndroidUtilities.addToClipboard(r53) == false) goto L9;
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createCopyBulletin(LocaleController.getString(R.string.EmojiCopied)).show();
        return;
    L9:
        return;
    }

    private /* synthetic */ boolean lambda$new$2(Context r7, View r8, int r9) {
        if ((r8 instanceof EmojiImageView) == false) goto L10;
        AnimatedEmojiSpan r92 = ((EmojiImageView) r8).span;
        if (r92 != null) goto L7;
        return false;
    L7:
        ActionBarMenuSubItem r1 = new ActionBarMenuSubItem(getContext(), true, true);
        r1.setItemHeight(48);
        r1.setPadding(AndroidUtilities.dp(26.0f), 0, AndroidUtilities.dp(26.0f), 0);
        r1.setText(LocaleController.getString(R.string.Copy));
        r1.getTextView().setTextSize(1, 14.4f);
        r1.getTextView().setTypeface(AndroidUtilities.bold());
        r1.setOnClickListener(new EmojiPacksAlert$$ExternalSyntheticLambda6(this, r92));
        LinearLayout r93 = new LinearLayout(r7);
        Drawable r72 = ContextCompat.getDrawable(getContext(), R.drawable.popup_fixed_alert).mutate();
        r72.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_actionBarDefaultSubmenuBackground), PorterDuff.Mode.MULTIPLY));
        r93.setBackground(r72);
        r93.addView(r1);
        ActionBarPopupWindow r73 = new ActionBarPopupWindow(r93, -2, -2);
        this.popupWindow = r73;
        r73.setClippingEnabled(true);
        this.popupWindow.setLayoutInScreen(true);
        this.popupWindow.setInputMethodMode(2);
        this.popupWindow.setSoftInputMode(0);
        this.popupWindow.setOutsideTouchable(true);
        this.popupWindow.setAnimationStyle(R.style.PopupAnimation);
        int[] r74 = new int[2];
        r8.getLocationInWindow(r74);
        this.popupWindow.showAtLocation(r8, 51, (r74[0] - AndroidUtilities.dp(49.0f)) + (r8.getMeasuredWidth() / 2), r74[1] - AndroidUtilities.dp(52.0f));
        r8.performHapticFeedback(VibratorUtils.getType(0), 1);     // Catch: Exception -> L11
    L9:
        return true;
    L10:
        return false;
    }

    private /* synthetic */ boolean lambda$new$3(RecyclerListView.OnItemClickListener r8, View r9, MotionEvent r10) {
        return ContentPreviewViewer.getInstance().onTouch(r10, this.listView, 0, r8, this.previewDelegate, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$new$4(View r1) {
        showPremiumAlert();
    }

    private /* synthetic */ void lambda$onSubItemClick$12(ProfileActivity r3) {
        this.fragment.presentFragment(r3, false, false);
        dismiss();
    }

    private /* synthetic */ void lambda$onSubItemClick$13(AlertDialog r3, TLRPC.TL_messages_stickerSet r4, TLRPC.User r5) {
        r3.dismiss();
        if (r5 == null) goto L6;
        Bundle r32 = new Bundle();
        r32.putLong("user_id", r5.id);
        AndroidUtilities.runOnUIThread(new EmojiPacksAlert$$ExternalSyntheticLambda15(this, new ProfileActivity(r32)));
        return;
    L6:
        AndroidUtilities.addToClipboard(String.valueOf(ChatUtils.extractOwnerId(r4.set.id)));
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
    }

    private /* synthetic */ void lambda$onSubItemClick$14(Runnable r1) {
        this.cancelSearchCreatorRunnable = r1;
    }

    private /* synthetic */ void lambda$onSubItemClick$15(DialogInterface r1) {
        Runnable r12 = this.cancelSearchCreatorRunnable;
        if (r12 == null) goto L6;
        r12.run();
        this.cancelSearchCreatorRunnable = null;
        return;
    }

    private /* synthetic */ void lambda$updateButton$10(ArrayList r9, View r10) {
        int r102 = r9.size();
        int[] r0 = new int[2];
        int r2 = 0;
    L4:
        if (r2 >= r9.size()) goto L14;
        BaseFragment r3 = this.fragment;
        TLObject r5 = (TLObject) r9.get(r2);
        if (r102 != 1) goto L8;
        boolean r6 = true;
    L10:
        if (r102 <= 1) goto L12;
        EmojiPacksAlert$$ExternalSyntheticLambda7 r4 = new EmojiPacksAlert$$ExternalSyntheticLambda7(this, r0, r102, r9);
    L13:
        installSet(r3, r5, r6, r4, null);
        r2 = r2 + 1;
        goto L4
    L12:
        r4 = null;
        goto L13
    L8:
        r6 = false;
        goto L10
    L14:
        onButtonClicked(true);
        if (r102 > 1) goto L21;
        dismiss();
        return;
    }

    private /* synthetic */ void lambda$updateButton$11(ArrayList r6, View r7) {
        dismiss();
        BaseFragment r72 = this.fragment;
        if (r72 == null) goto L5;
        MediaDataController.getInstance(r72.getCurrentAccount()).removeMultipleStickerSets(this.fragment.getContext(), this.fragment, r6);
    L13:
        onButtonClicked(false);
        return;
    L5:
        int r73 = 0;
    L7:
        if (r73 >= r6.size()) goto L13;
        TLRPC.TL_messages_stickerSet r1 = (TLRPC.TL_messages_stickerSet) r6.get(r73);
        Context r2 = getContext();
        if (r73 != 0) goto L11;
        boolean r3 = true;
    L12:
        uninstallSet(r2, r1, r3, null);
        r73 = r73 + 1;
        goto L7
    L11:
        r3 = false;
        goto L12
    }

    private /* synthetic */ void lambda$updateButton$9(int[] r11, int r12, ArrayList r13, Boolean r14) {
        r11[0] = r11[0] + 1;
        if (r14.booleanValue() == false) goto L6;
        r11[1] = r11[1] + 1;
    L6:
        if (r11[0] == r12) goto L8;
        return;
    L8:
        if (r11[1] <= 0) goto L12;
        dismiss();
        Bulletin.make(this.fragment, new StickerSetBulletinLayout(this.fragment.getFragmentView().getContext(), (TLObject) r13.get(0), r11[1], 2, null, this.fragment.getResourceProvider()), 1500).show();
        return;
    }

    private void loadAnimation() {
        if (this.loadAnimator == null) goto L5;
        return;
    L5:
        this.loadAnimator = ValueAnimator.ofFloat(new float[]{this.loadT, 1.0f});
        this.fromY = Float.valueOf(this.lastY + this.containerView.getY());
        this.loadAnimator.addUpdateListener(new EmojiPacksAlert$$ExternalSyntheticLambda8(this));
        this.loadAnimator.setDuration(250);
        this.loadAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.loadAnimator.start();
    }

    private void onSubItemClick(int r12) {
        EmojiPacksLoader r0 = this.customEmojiPacks;
        if (r0 == null) goto L51;
        ArrayList<TLRPC.TL_messages_stickerSet> r02 = r0.stickerSets;
        if (r02 != null) goto L7;
        return;
    L7:
        if (r02.isEmpty() == true) goto L55;
        TLRPC.TL_messages_stickerSet r03 = this.customEmojiPacks.stickerSets.get(0);
        TLRPC.StickerSet r1 = r03.set;
        if (r1 != null) goto L12;
    L15:
        String r13 = "https://" + MessagesController.getInstance(this.currentAccount).linkPrefix + "/addstickers/" + r03.set.short_name;
    L14:
        String r6 = r13;
        Context r14 = null;
        if (r12 != 1) goto L30;
        BaseFragment r122 = this.fragment;
        if (r122 == null) goto L21;
        r14 = r122.getParentActivity();
    L21:
        if (r14 != null) goto L23;
        r14 = getContext();
    L23:
        AnonymousClass9 r2 = new AnonymousClass9(this, r14, null, r6, false, r6, false, this.resourcesProvider);
        BaseFragment r11 = this.fragment;
        if (r11 == null) goto L27;
        r11.showDialog(r2);
        return;
    L27:
        r2.show();
        return;
    L30:
        if (r12 != 2) goto L36;
        AndroidUtilities.addToClipboard(r6);     // Catch: Exception -> L33
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createCopyLinkBulletin().show();     // Catch: Exception -> L33
        return;
    L33:
        e = move-exception;
        FileLog.e(e);
        return;
    L36:
        if (r12 != 3) goto L54;
        AlertDialog r123 = new AlertDialog(getContext(), 3);     // Catch: Exception -> L45
        r123.setCancelDialog(true);     // Catch: Exception -> L45
        r123.show();     // Catch: Exception -> L45
        this.cancelSearchCreatorRunnable = ChatUtils.getInstance().searchUserById(Long.valueOf(ChatUtils.extractOwnerId(r03.set.id)), new EmojiPacksAlert$$ExternalSyntheticLambda11(this, r123, r03), new EmojiPacksAlert$$ExternalSyntheticLambda12(this));     // Catch: Exception -> L45
        if (r123.isDismissed() == false) goto L43;
        Runnable r112 = this.cancelSearchCreatorRunnable;     // Catch: Exception -> L45
        if (r112 == null) goto L43;
        r112.run();     // Catch: Exception -> L45
        this.cancelSearchCreatorRunnable = null;     // Catch: Exception -> L45
        return;
    L43:
        r123.setOnCancelListener(new EmojiPacksAlert$$ExternalSyntheticLambda13(this));     // Catch: Exception -> L45
        return;
    L45:
        e = move-exception;
        FileLog.e(e);
        return;
    L54:
        return;
    L12:
        if (r1.emojis == false) goto L15;
        r13 = "https://" + MessagesController.getInstance(this.currentAccount).linkPrefix + "/addemoji/" + r03.set.short_name;
        goto L14
    L55:
        return;
    }

    public static void uninstallSet(Context r10, TLRPC.TL_messages_stickerSet r11, boolean r12, Runnable r13) {
        if (r11 != null) goto L4;
        return;
    L4:
        MediaDataController.getInstance(UserConfig.selectedAccount).toggleStickerSet(r10, r11, 0, null, true, r12, r13, true);
    }

    public static void uninstallSet(BaseFragment r10, TLRPC.TL_messages_stickerSet r11, boolean r12, Runnable r13, boolean r14) {
        if (r10 == null) goto L9;
        if (r11 != null) goto L5;
        return;
    L5:
        if (r10.getFragmentView() == null) goto L11;
        MediaDataController.getInstance(r10.getCurrentAccount()).toggleStickerSet(r10.getFragmentView().getContext(), r11, 0, r10, true, r12, r13, r14);
        return;
    L11:
        return;
    }

    private void updateButton() {
        if (this.buttonsView != null) goto L6;
        return;
    L6:
        if (this.customEmojiPacks.stickerSets != null) goto L8;
        ArrayList r0 = new ArrayList();
    L9:
        int r2 = 0;
    L11:
        if (r2 >= r0.size()) goto L16;
        if (r0.get(r2) != null) goto L15;
        r0.remove(r2);
        r2 = r2 - 1;
    L15:
        r2 = r2 + 1;
        goto L11
    L16:
        MediaDataController r22 = MediaDataController.getInstance(this.currentAccount);
        ArrayList r3 = new ArrayList();
        ArrayList r5 = new ArrayList();
        int r6 = 0;
    L18:
        if (r6 >= r0.size()) goto L28;
        TLRPC.TL_messages_stickerSet r7 = (TLRPC.TL_messages_stickerSet) r0.get(r6);
        if (r7 == null) goto L27;
        TLRPC.StickerSet r8 = r7.set;
        if (r8 == null) goto L27;
        if (r22.isStickerPackInstalled(r8.id) == true) goto L26;
        r5.add(r7);
        goto L27
    L26:
        r3.add(r7);
    L27:
        r6 = r6 + 1;
        goto L18
    L28:
        ArrayList r23 = new ArrayList(r5);
        if (this.customEmojiPacks.inputStickerSets != null) goto L31;
    L33:
        boolean r02 = false;
    L35:
        if (this.loaded == true) goto L38;
        if (r02 == false) goto L38;
        loadAnimation();
    L38:
        this.loaded = r02;
        if (r02 == true) goto L42;
        this.listView.setAlpha(0.0f);
    L53:
        if (this.loaded == true) goto L55;
    L75:
        this.premiumButtonView.setVisibility(8);
        this.addButtonView.setVisibility(8);
        this.removeButtonView.setVisibility(8);
        updateShowButton(false);
        return;
    L55:
        if (this.limitCount == true) goto L75;
        this.premiumButtonView.setVisibility(4);
        if (r23.size() <= 0) goto L65;
        this.addButtonView.setVisibility(0);
        this.removeButtonView.setVisibility(8);
        int r03 = r23.size();
        TextView r32 = this.addButtonView;
        if (r03 != 1) goto L62;
        r32.setText(LocaleController.formatPluralString("AddManyEmojiCount", ((TLRPC.TL_messages_stickerSet) r23.get(0)).documents.size(), new Object[0]));
    L63:
        this.addButtonView.setOnClickListener(new EmojiPacksAlert$$ExternalSyntheticLambda4(this, r23));
        updateShowButton(true);
        return;
    L62:
        r32.setText(LocaleController.formatPluralString("AddManyEmojiPacksCount", r23.size(), new Object[0]));
        goto L63
    L65:
        int r04 = r3.size();
        TextView r24 = this.addButtonView;
        if (r04 <= 0) goto L73;
        r24.setVisibility(8);
        this.removeButtonView.setVisibility(0);
        int r05 = r3.size();
        TextView r25 = this.removeButtonView;
        if (r05 != 1) goto L70;
        r25.setText(LocaleController.formatPluralString("RemoveManyEmojiCount", ((TLRPC.TL_messages_stickerSet) r3.get(0)).documents.size(), new Object[0]));
    L71:
        this.removeButtonView.setOnClickListener(new EmojiPacksAlert$$ExternalSyntheticLambda5(this, r3));
        updateShowButton(true);
        return;
    L70:
        r25.setText(LocaleController.formatPluralString("RemoveManyEmojiPacksCount", r3.size(), new Object[0]));
        goto L71
    L73:
        r24.setVisibility(8);
        this.removeButtonView.setVisibility(8);
        updateShowButton(false);
        return;
    L42:
        if (this.highlightIndex < 0) goto L53;
        int r06 = this.gridLayoutManager.findFirstVisibleItemPosition();
        int r52 = this.adapter.getSetHeaderPosition(this.highlightIndex);
        if (Math.abs(r06 - r52) <= 54) goto L50;
        RecyclerAnimationScrollHelper r62 = this.scrollHelper;
        if (r06 >= r52) goto L48;
        int r07 = 0;
    L49:
        r62.setScrollDirection(r07);
        this.scrollHelper.scrollToPosition(r52, (AndroidUtilities.displaySize.y / 2) - AndroidUtilities.dp(170.0f), false, true);
    L51:
        this.highlightStartPosition = this.adapter.getSetHeaderPosition(this.highlightIndex);
        this.highlightEndPosition = this.adapter.getSetEndPosition(this.highlightIndex);
        this.highlightAlpha.set(1.0f, true);
        this.listView.invalidate();
        this.highlightIndex = -1;
        goto L53
    L48:
        r07 = 1;
        goto L49
    L50:
        this.listView.smoothScrollToPosition(r52);
        goto L51
    L31:
        if (r0.size() != this.customEmojiPacks.inputStickerSets.size()) goto L33;
        r02 = true;
        goto L35
    L8:
        r0 = new ArrayList(this.customEmojiPacks.stickerSets);
        goto L9
    }

    private void updateLightStatusBar(boolean r7) {
        boolean r2 = false;
        if (AndroidUtilities.computePerceivedBrightness(getThemedColor(Theme.key_dialogBackground)) <= 0.721f) goto L5;
        boolean r0 = true;
    L7:
        if (AndroidUtilities.computePerceivedBrightness(Theme.blendOver(getThemedColor(Theme.key_actionBarDefault), 855638016)) <= 0.721f) goto L9;
        r2 = true;
    L9:
        if (r7 == true) goto L12;
        r0 = r2;
    L12:
        AndroidUtilities.setLightStatusBar(this, r0);
        return;
    L5:
        r0 = false;
        goto L7
    }

    private void updateShowButton(boolean r10) {
        int r1 = 0;
        if (this.shown == true) goto L6;
        if (r10 == false) goto L6;
        boolean r0 = true;
    L8:
        if (this.removeButtonView.getVisibility() != 0) goto L10;
        r1 = AndroidUtilities.dp(19.0f);
    L10:
        float r12 = r1;
        FrameLayout r2 = this.buttonsView;
        float r5 = 1.0f;
        float r6 = 0.0f;
        if (r0 == false) goto L34;
        ViewPropertyAnimator r02 = r2.animate();
        if (r10 == false) goto L15;
        float r22 = r12;
    L16:
        ViewPropertyAnimator r03 = r02.translationY(r22);
        if (r10 == false) goto L19;
        float r23 = 1.0f;
    L20:
        ViewPropertyAnimator r04 = r03.alpha(r23).setDuration(250);
        CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r04.setInterpolator(r7).start();
        ViewPropertyAnimator r05 = this.shadowView.animate();
        if (r10 == false) goto L23;
        float r8 = -(AndroidUtilities.dp(68.0f) - r12);
    L24:
        ViewPropertyAnimator r06 = r05.translationY(r8);
        if (r10 == true) goto L28;
        r5 = 0.0f;
    L28:
        r06.alpha(r5).setDuration(250).setInterpolator(r7).start();
        ViewPropertyAnimator r07 = this.listView.animate();
        if (this.limitCount == true) goto L33;
        if (r10 == true) goto L33;
        r6 = AndroidUtilities.dp(68.0f) - r12;
    L33:
        r07.translationY(r6).setDuration(250).setInterpolator(r7).start();
    L55:
        this.shown = r10;
        return;
    L23:
        r8 = 0.0f;
        goto L24
    L19:
        r23 = 0.0f;
        goto L20
    L15:
        r22 = AndroidUtilities.dp(16.0f);
        goto L16
    L34:
        if (r10 == false) goto L36;
        float r08 = 1.0f;
    L37:
        r2.setAlpha(r08);
        FrameLayout r09 = this.buttonsView;
        if (r10 == false) goto L40;
        float r24 = r12;
    L41:
        r09.setTranslationY(r24);
        View r010 = this.shadowView;
        if (r10 == true) goto L45;
        r5 = 0.0f;
    L45:
        r010.setAlpha(r5);
        View r011 = this.shadowView;
        if (r10 == false) goto L48;
        float r25 = -(AndroidUtilities.dp(68.0f) - r12);
    L49:
        r011.setTranslationY(r25);
        RecyclerListView r012 = this.listView;
        if (this.limitCount == true) goto L54;
        if (r10 == true) goto L54;
        r6 = AndroidUtilities.dp(68.0f) - r12;
    L54:
        r012.setTranslationY(r6);
        goto L55
    L48:
        r25 = 0.0f;
        goto L49
    L40:
        r24 = AndroidUtilities.dp(16.0f);
        goto L41
    L36:
        r08 = 0.0f;
    L6:
        r0 = false;
        goto L8
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.stickersDidLoad) goto L6;
        updateInstallment();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        ContentView r0 = this.contentView;
        if (r0 == null) goto L5;
        r0.hidePreviewEmoji();
    L5:
        super.dismiss();
        EmojiPacksLoader r2 = this.customEmojiPacks;
        if (r2 == null) goto L8;
        r2.recycle();
    L8:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{4});
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        ContentPreviewViewer.getInstance().clearDelegate(this.previewDelegate);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public int getContainerViewHeight() {
        RecyclerListView r0 = this.listView;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = r02 - getListTop();
        ViewGroup r3 = this.containerView;
        if (r3 == null) goto L11;
        r1 = r3.getPaddingTop();
    L11:
        return ((r03 + r1) + AndroidUtilities.navigationBarHeight) + AndroidUtilities.dp(8.0f);
    L5:
        r02 = r0.getMeasuredHeight();
        goto L6
    }

    public void highlight(int r1) {
        this.highlightIndex = r1;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.stickersDidLoad);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        if (ContentPreviewViewer.getInstance().isVisible() == false) goto L6;
        ContentPreviewViewer.getInstance().closeWithMenu();
        return;
    L6:
        super.onBackPressed();
    }

    public void onButtonClicked(boolean r1) {
    }

    public void onCloseByLink() {
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.stickersDidLoad);
    }

    public void setPreviewEmoji(TLRPC.Document r1) {
        this.contentView.setPreviewEmoji(r1);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        RecyclerListView r0 = this.listView;
        Adapter r1 = new Adapter(this, null);
        this.adapter = r1;
        r0.setAdapter(r1);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{4});
        this.customEmojiPacks.start();
        updateButton();
        BaseFragment r3 = this.fragment;
        if (r3 != null) goto L5;
        int r32 = UserConfig.selectedAccount;
    L6:
        MediaDataController.getInstance(r32).checkStickers(5);
        return;
    L5:
        r32 = r3.getCurrentAccount();
        goto L6
    }

    public void showPremiumAlert() {
        BaseFragment r0 = this.fragment;
        if (r0 == null) goto L7;
        new PremiumFeatureBottomSheet(r0, 11, false).show();
        return;
    L7:
        if ((getContext() instanceof LaunchActivity) == false) goto L10;
        ((LaunchActivity) getContext()).presentFragment(new PremiumPreviewFragment(null));
        return;
    }

    public void updateInstallment() {
        int r0 = 0;
    L4:
        if (r0 >= this.listView.getChildCount()) goto L13;
        View r1 = this.listView.getChildAt(r0);
        if ((r1 instanceof EmojiPackHeader) == false) goto L12;
        EmojiPackHeader r12 = (EmojiPackHeader) r1;
        if (EmojiPackHeader.m10246$$Nest$fgetset(r12) == null) goto L12;
        if (EmojiPackHeader.m10246$$Nest$fgetset(r12).set == null) goto L12;
        EmojiPackHeader.m10247$$Nest$mtoggle(r12, MediaDataController.getInstance(this.currentAccount).isStickerPackInstalled(EmojiPackHeader.m10246$$Nest$fgetset(r12).set.id), true);
    L12:
        r0 = r0 + 1;
        goto L4
    L13:
        updateButton();
    }
}
