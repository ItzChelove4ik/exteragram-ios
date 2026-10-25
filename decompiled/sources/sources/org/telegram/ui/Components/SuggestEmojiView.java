package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.utils.system.VibratorUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.ContentPreviewViewer;

/* loaded from: classes7.dex */
public class SuggestEmojiView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private Adapter adapter;
    private Integer arrowToEnd;
    private Emoji.EmojiSpan arrowToSpan;
    private Integer arrowToStart;
    private float arrowX;
    private AnimatedFloat arrowXAnimated;
    private Paint backgroundPaint;
    private Path circlePath;
    private boolean clear;
    private FrameLayout containerView;
    private final int currentAccount;
    private int direction;
    private AnchorViewDelegate enterView;
    private boolean forceClose;
    private int horizontalPadding;
    private boolean isCopyForbidden;
    private boolean isSetAsStatusForbidden;
    private ArrayList<MediaDataController.KeywordResult> keywordResults;
    private String[] lastLang;
    private long lastLangChangedTime;
    private String lastQuery;
    private int lastQueryId;
    private int lastQueryType;
    private float lastSpanY;
    private AnimatedFloat leftGradientAlpha;
    private RecyclerListView listView;
    private AnimatedFloat listViewCenterAnimated;
    private AnimatedFloat listViewWidthAnimated;
    private MediaDataController.SearchStickersKey loadingKey;
    private OvershootInterpolator overshootInterpolator;
    private Path path;
    private ContentPreviewViewer.ContentPreviewViewerDelegate previewDelegate;
    private final Theme.ResourcesProvider resourcesProvider;
    private AnimatedFloat rightGradientAlpha;
    private Runnable searchRunnable;
    private boolean show;
    private AnimatedFloat showFloat1;
    private AnimatedFloat showFloat2;
    private Runnable updateRunnable;

    /* renamed from: org.telegram.ui.Components.SuggestEmojiView$1, reason: invalid class name */
    public class AnonymousClass1 implements ContentPreviewViewer.ContentPreviewViewerDelegate {
        final /* synthetic */ SuggestEmojiView this$0;

        public static /* synthetic */ void $r8$lambda$2whgXLzBqz4yP9CNbNBbP88YKH4(String r1, BaseFragment r2) {
            if (r1 != null) goto L4;
        L7:
            BulletinFactory.of(r2).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
            return;
        L4:
            if (r1.equals("ok") == false) goto L7;
        }

        public static /* synthetic */ void $r8$lambda$3J6a49kUBlx1tYi9mLCCBJ0cROM(BaseFragment r1, TLRPC.Document r2, Runnable r3, String r4) {
            AndroidUtilities.runOnUIThread(new SuggestEmojiView$1$$ExternalSyntheticLambda3(r4, r1, r2, r3));
        }

        public static /* synthetic */ void $r8$lambda$7poGLY6Q4YTEAgwqJr6NoecHU9k(String r1, BaseFragment r2, TLRPC.Document r3, Runnable r4) {
            if (r1 != null) goto L4;
        L8:
            BulletinFactory.of(r2).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
            return;
        L4:
            if (r1.equals("ok") == false) goto L8;
            BulletinFactory.of(r2).createEmojiBulletin(r3, LocaleController.getString(R.string.SetAsBadgeStatusInfo), LocaleController.getString(R.string.UndoNoCaps), r4).show();
        }

        /* renamed from: $r8$lambda$JrN1LstNki-cpmDJhVwgVPWY3bs, reason: not valid java name */
        public static /* synthetic */ void m13159$r8$lambda$JrN1LstNkicpmDJhVwgVPWY3bs(BaseFragment r1, String r2) {
            AndroidUtilities.runOnUIThread(new SuggestEmojiView$1$$ExternalSyntheticLambda5(r2, r1));
        }

        public static /* synthetic */ void $r8$lambda$NQI6bV6YwV4nZSrYtVlxJn4FHvs(BadgeDTO r2, BaseFragment r3) {
            BadgesController.INSTANCE.updateBadge(r2, new SuggestEmojiView$1$$ExternalSyntheticLambda4(r3));
        }

        /* renamed from: $r8$lambda$eYBLFtLTzZrBk8zK4m3yzH-tseg, reason: not valid java name */
        public static /* synthetic */ void m13160$r8$lambda$eYBLFtLTzZrBk8zK4m3yzHtseg(AnonymousClass1 r0, TLRPC.EmojiStatus r1) {
            r0.lambda$setAsEmojiStatus$0(r1);
        }

        public AnonymousClass1(SuggestEmojiView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$setAsEmojiStatus$0(TLRPC.EmojiStatus r1) {
            MessagesController.getInstance(SuggestEmojiView.m13148$$Nest$fgetcurrentAccount(this.this$0)).updateEmojiStatus(r1);
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
            if (SuggestEmojiView.m13153$$Nest$fgetisSetAsStatusForbidden(this.this$0) == false) goto L6;
            return null;
        L6:
            if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L8;
            return null;
        L8:
            TLRPC.User r2 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser();
            if (r2 != null) goto L11;
            return null;
        L11:
            Long r22 = UserObject.getEmojiStatusDocumentId(r2);
            if (r3 == null) goto L17;
            if (r22 != null) goto L15;
        L16:
            boolean r23 = true;
        L19:
            return Boolean.valueOf(r23);
        L15:
            if (r22.longValue() != r3.id) goto L16;
        L17:
            r23 = false;
            goto L19
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void copyEmoji(TLRPC.Document r5) {
            SpannableStringBuilder r0 = SpannableStringBuilder.valueOf(MessageObject.findAnimatedEmojiEmoticon(r5));
            r0.setSpan(new AnimatedEmojiSpan(r5, null), 0, r0.length(), 33);
            if (AndroidUtilities.addToClipboard(r0) == true) goto L5;
            return;
        L5:
            if (SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0) == null) goto L9;
            BulletinFactory.of(SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0).getParentFragment()).createCopyBulletin(LocaleController.getString(R.string.EmojiCopied)).show();
            return;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public long getDialogId() {
            return 0;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean isInScheduleMode() {
            if (SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0) != null) goto L5;
            return false;
        L5:
            BaseFragment r2 = SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0).getParentFragment();
            if ((r2 instanceof ChatActivity) == true) goto L8;
            return false;
        L8:
            return ((ChatActivity) r2).isInScheduleMode();
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean needCopy(TLRPC.Document r1) {
            if (SuggestEmojiView.m13152$$Nest$fgetisCopyForbidden(this.this$0) == false) goto L7;
            return false;
        L7:
            return UserConfig.getInstance(UserConfig.selectedAccount).isPremium();
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean needSend(int r2) {
            if (SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0) != null) goto L5;
            return false;
        L5:
            BaseFragment r1 = SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0).getParentFragment();
            if ((r1 instanceof ChatActivity) == false) goto L17;
            ChatActivity r12 = (ChatActivity) r1;
            if (r12.canSendMessage() == false) goto L17;
            if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == false) goto L12;
            return true;
        L12:
            if (r12.getCurrentUser() == null) goto L17;
            if (UserObject.isUserSelf(r12.getCurrentUser()) == false) goto L17;
            return true;
        L17:
            return false;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean needShowEmojiSet(TLRPC.Document r1) {
            if (r1 != null) goto L4;
            return false;
        L4:
            if (MessageObject.getInputStickerSet(r1) == null) goto L9;
            return true;
        L9:
            return false;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void openSet(TLRPC.InputStickerSet r1, boolean r2) {
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void sendEmoji(TLRPC.Document r4) {
            if (SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0) == null) goto L10;
            BaseFragment r0 = SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0).getParentFragment();
            if ((r0 instanceof ChatActivity) == false) goto L9;
            ((ChatActivity) r0).sendAnimatedEmoji(r4, true, 0);
            SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0).setFieldText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void setAsBadge(TLRPC.Document r8) {
            if (r8 != null) goto L4;
            return;
        L4:
            BadgesController r0 = BadgesController.INSTANCE;
            BadgeDTO r1 = r0.getBadge();
            String r3 = null;
            if (SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0) != null) goto L7;
            BaseFragment r7 = null;
        L8:
            SuggestEmojiView$1$$ExternalSyntheticLambda0 r2 = new SuggestEmojiView$1$$ExternalSyntheticLambda0(r1, r7);
            long r5 = r8.id;
            if (r1 == null) goto L11;
            r3 = r1.getText();
        L11:
            r0.updateBadge(new BadgeDTO(r5, r3), new SuggestEmojiView$1$$ExternalSyntheticLambda1(r7, r8, r2));
            return;
        L7:
            r7 = SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0).getParentFragment();
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
            MessagesController.getInstance(SuggestEmojiView.m13148$$Nest$fgetcurrentAccount(this.this$0)).updateEmojiStatus(r62);
            SuggestEmojiView$1$$ExternalSyntheticLambda2 r63 = new SuggestEmojiView$1$$ExternalSyntheticLambda2(this, r12);
            if (SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0) != null) goto L16;
            BaseFragment r13 = null;
        L17:
            if (r13 == null) goto L23;
            if (r5 != null) goto L21;
            Bulletin.SimpleLayout r52 = new Bulletin.SimpleLayout(this.this$0.getContext(), SuggestEmojiView.m13156$$Nest$fgetresourcesProvider(this.this$0));
            r52.textView.setText(LocaleController.getString(R.string.RemoveStatusInfo));
            r52.imageView.setImageResource(R.drawable.msg_settings_premium);
            Bulletin.UndoButton r2 = new Bulletin.UndoButton(this.this$0.getContext(), true, SuggestEmojiView.m13156$$Nest$fgetresourcesProvider(this.this$0));
            r2.setUndoAction(r63);
            r52.setButton(r2);
            Bulletin.make(r13, r52, 1500).show();
            return;
        L21:
            BulletinFactory.of(r13).createEmojiBulletin(r5, LocaleController.getString(R.string.SetAsEmojiStatusInfo), LocaleController.getString(R.string.UndoNoCaps), r63).show();
            return;
        L23:
            return;
        L16:
            r13 = SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0).getParentFragment();
            goto L17
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

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void showEmojiSet(TLRPC.Document r5) {
            TLRPC.InputStickerSet r0 = MessageObject.getInputStickerSet(r5);
            if (r0 != null) goto L5;
            return;
        L5:
            ArrayList r1 = new ArrayList(1);
            r1.add(r0);
            if (SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0) != null) goto L8;
            BaseFragment r02 = null;
        L9:
            EmojiPacksAlert r2 = new EmojiPacksAlert(r02, this.this$0.getContext(), SuggestEmojiView.m13156$$Nest$fgetresourcesProvider(this.this$0), r1);
            r2.setPreviewEmoji(r5);
            if ((r02 instanceof ChatActivity) == false) goto L13;
            ChatActivity r03 = (ChatActivity) r02;
            r2.setCalcMandatoryInsets(r03.isKeyboardVisible());
            r03.showDialog(r2);
            return;
        L13:
            if (r02 == null) goto L16;
            r02.showDialog(r2);
            return;
        L16:
            r2.show();
            return;
        L8:
            r02 = SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0).getParentFragment();
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Components.SuggestEmojiView$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ SuggestEmojiView this$0;

        public AnonymousClass2(SuggestEmojiView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r2) {
            SuggestEmojiView.m13157$$Nest$mdrawContainerBegin(this.this$0, r2);
            super.dispatchDraw(r2);
            this.this$0.drawContainerEnd(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r7, int r8) {
            int r0 = SuggestEmojiView.m13151$$Nest$fgethorizontalPadding(this.this$0);
            if (SuggestEmojiView.m13149$$Nest$fgetdirection(this.this$0) != 0) goto L5;
            int r1 = AndroidUtilities.dp(8.0f);
        L6:
            int r4 = SuggestEmojiView.m13151$$Nest$fgethorizontalPadding(this.this$0);
            if (SuggestEmojiView.m13149$$Nest$fgetdirection(this.this$0) != 0) goto L9;
            int r2 = AndroidUtilities.dp(6.66f);
        L10:
            setPadding(r0, r1, r4, r2);
            super.onMeasure(r7, r8);
            return;
        L9:
            r2 = AndroidUtilities.dp(8.0f);
            goto L10
        L5:
            r1 = AndroidUtilities.dp(6.66f);
            goto L6
        }

        @Override // android.view.View
        public void setVisibility(int r4) {
            int r1 = 0;
            boolean r2 = true;
            if (getVisibility() != r4) goto L5;
            boolean r0 = true;
        L6:
            super.setVisibility(r4);
            if (r0 == true) goto L20;
            if (r4 == 0) goto L12;
            r2 = false;
        L12:
            if (SuggestEmojiView.m13155$$Nest$fgetlistView(this.this$0) != null) goto L14;
            return;
        L14:
            if (r1 >= SuggestEmojiView.m13155$$Nest$fgetlistView(this.this$0).getChildCount()) goto L25;
            SuggestEmojiView r42 = this.this$0;
            if (r2 == false) goto L18;
            ((EmojiImageView) SuggestEmojiView.m13155$$Nest$fgetlistView(r42).getChildAt(r1)).attach();
        L19:
            r1 = r1 + 1;
            goto L14
        L18:
            ((EmojiImageView) SuggestEmojiView.m13155$$Nest$fgetlistView(r42).getChildAt(r1)).detach();
            goto L19
        L25:
            return;
        L20:
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.SuggestEmojiView$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerListView {
        private boolean left;
        private boolean right;
        final /* synthetic */ SuggestEmojiView this$0;

        public AnonymousClass3(SuggestEmojiView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r7) {
            boolean r72 = ContentPreviewViewer.getInstance().onInterceptTouchEvent(r7, SuggestEmojiView.m13155$$Nest$fgetlistView(this.this$0), 0, SuggestEmojiView.m13158$$Nest$mgetPreviewDelegate(this.this$0), this.resourcesProvider);
            if (super.onInterceptTouchEvent(r7) == true) goto L8;
            if (r72 == true) goto L10;
            return false;
        L10:
            return true;
        L8:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void onScrolled(int r2, int r3) {
            super.onScrolled(r2, r3);
            boolean r22 = canScrollHorizontally(-1);
            boolean r32 = canScrollHorizontally(1);
            if (this.left != r22) goto L9;
            if (this.right != r32) goto L9;
            return;
        L9:
            if (SuggestEmojiView.m13147$$Nest$fgetcontainerView(this.this$0) == null) goto L11;
            SuggestEmojiView.m13147$$Nest$fgetcontainerView(this.this$0).invalidate();
        L11:
            this.left = r22;
            this.right = r32;
        }
    }

    /* renamed from: org.telegram.ui.Components.SuggestEmojiView$4, reason: invalid class name */
    public class AnonymousClass4 implements TextWatcher {
        final /* synthetic */ SuggestEmojiView this$0;

        public AnonymousClass4(SuggestEmojiView r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
            if (SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0) != null) goto L5;
            return;
        L5:
            if (SuggestEmojiView.m13150$$Nest$fgetenterView(this.this$0).getVisibility() != 0) goto L9;
            this.this$0.fireUpdate();
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    public class Adapter extends RecyclerListView.SelectionAdapter {
        SuggestEmojiView suggestEmojiView;
        final /* synthetic */ SuggestEmojiView this$0;

        public Adapter(SuggestEmojiView r1, SuggestEmojiView r2) {
            this.this$0 = r1;
            this.suggestEmojiView = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (SuggestEmojiView.m13154$$Nest$fgetkeywordResults(this.suggestEmojiView) != null) goto L7;
            return 0;
        L7:
            return SuggestEmojiView.m13154$$Nest$fgetkeywordResults(this.suggestEmojiView).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r2) {
            if (SuggestEmojiView.m13154$$Nest$fgetkeywordResults(this.suggestEmojiView) != null) goto L7;
            return 0;
        L7:
            return ((MediaDataController.KeywordResult) SuggestEmojiView.m13154$$Nest$fgetkeywordResults(this.suggestEmojiView).get(r2)).emoji.hashCode();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r2, int r3) {
            EmojiImageView r22 = (EmojiImageView) r2.itemView;
            if (SuggestEmojiView.m13154$$Nest$fgetkeywordResults(this.suggestEmojiView) != null) goto L5;
            String r32 = null;
        L6:
            EmojiImageView.m13162$$Nest$msetEmoji(r22, r32, this.suggestEmojiView.getDirection());
            return;
        L5:
            r32 = ((MediaDataController.KeywordResult) SuggestEmojiView.m13154$$Nest$fgetkeywordResults(this.suggestEmojiView).get(r3)).emoji;
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            return new RecyclerListView.Holder(new EmojiImageView(this.this$0, this.suggestEmojiView.getContext()));
        }
    }

    /* loaded from: classes3.dex */
    public interface AnchorViewDelegate {
        void addTextChangedListener(TextWatcher r1);

        EditTextBoldCursor getEditField();

        Editable getEditText();

        CharSequence getFieldText();

        BaseFragment getParentFragment();

        int getVisibility();

        void setFieldText(CharSequence r1);
    }

    public class EmojiImageView extends View {
        private boolean attached;
        private int direction;
        public Drawable drawable;
        private String emoji;
        private final int paddingDp;
        private AnimatedFloat pressed;
        final /* synthetic */ SuggestEmojiView this$0;

        /* renamed from: -$$Nest$fgetemoji, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m13161$$Nest$fgetemoji(EmojiImageView r0) {
            return r0.emoji;
        }

        /* renamed from: -$$Nest$msetEmoji, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13162$$Nest$msetEmoji(EmojiImageView r0, String r1, int r2) {
            r0.setEmoji(r1, r2);
        }

        public EmojiImageView(SuggestEmojiView r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.direction = 0;
            this.pressed = new AnimatedFloat(this, 350, new OvershootInterpolator(5.0f));
            this.paddingDp = 3;
        }

        private void setEmoji(String r5, int r6) {
            this.emoji = r5;
            if (r5 != null) goto L5;
        L14:
            setImageDrawable(Emoji.getEmojiBigDrawable(r5));
        L16:
            if (this.direction == r6) goto L21;
            this.direction = r6;
            requestLayout();
            return;
        L21:
            return;
        L5:
            if (r5.startsWith("animated_") == false) goto L14;
            long r0 = Long.parseLong(r5.substring(9));     // Catch: Exception -> L13
            Drawable r52 = this.drawable;     // Catch: Exception -> L13
            if ((r52 instanceof AnimatedEmojiDrawable) == true) goto L10;
        L11:
            setImageDrawable(AnimatedEmojiDrawable.make(UserConfig.selectedAccount, this.this$0.emojiCacheType(), r0));     // Catch: Exception -> L13
            goto L16
        L10:
            if (((AnimatedEmojiDrawable) r52).getDocumentId() == r0) goto L16;
        L13:
            setImageDrawable(null);
            goto L16
        }

        public void attach() {
            Drawable r0 = this.drawable;
            if ((r0 instanceof AnimatedEmojiDrawable) == false) goto L5;
            ((AnimatedEmojiDrawable) r0).addView(this);
        L5:
            this.attached = true;
        }

        public void detach() {
            Drawable r0 = this.drawable;
            if ((r0 instanceof AnimatedEmojiDrawable) == false) goto L5;
            ((AnimatedEmojiDrawable) r0).removeView(this);
        L5:
            this.attached = false;
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r10) {
            AnimatedFloat r0 = this.pressed;
            if (isPressed() == false) goto L5;
            float r1 = 1.0f;
        L6:
            float r2 = ((1.0f - r0.set(r1)) * 0.2f) + 0.8f;
            if (this.drawable == null) goto L13;
            int r02 = getWidth() / 2;
            int r12 = ((getHeight() - getPaddingBottom()) + getPaddingTop()) / 2;
            this.drawable.setBounds(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
            r10.scale(r2, r2, r02, r12);
            Drawable r03 = this.drawable;
            if ((r03 instanceof AnimatedEmojiDrawable) == false) goto L11;
            ((AnimatedEmojiDrawable) r03).setTime(System.currentTimeMillis());
        L11:
            this.drawable.draw(r10);
            return;
        L13:
            return;
        L5:
            r1 = 0.0f;
            goto L6
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            attach();
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            detach();
        }

        @Override // android.view.View
        public void onMeasure(int r6, int r7) {
            int r72 = AndroidUtilities.dp(3.0f);
            float r1 = 6.66f;
            if (this.direction != 0) goto L5;
            float r0 = 0.0f;
        L6:
            int r02 = AndroidUtilities.dp(r0 + 3.0f);
            int r3 = AndroidUtilities.dp(3.0f);
            if (this.direction == 0) goto L10;
            r1 = 0.0f;
        L10:
            setPadding(r72, r02, r3, AndroidUtilities.dp(r1 + 3.0f));
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(44.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(52.0f), TLObject.FLAG_30));
            return;
        L5:
            r0 = 6.66f;
            goto L6
        }

        public void setDirection(int r1) {
            this.direction = r1;
            invalidate();
        }

        public void setImageDrawable(Drawable r3) {
            Drawable r0 = this.drawable;
            if ((r0 instanceof AnimatedEmojiDrawable) == false) goto L5;
            ((AnimatedEmojiDrawable) r0).removeView(this);
        L5:
            this.drawable = r3;
            if ((r3 instanceof AnimatedEmojiDrawable) == true) goto L8;
            return;
        L8:
            if (this.attached == false) goto L12;
            ((AnimatedEmojiDrawable) r3).addView(this);
            return;
        }

        @Override // android.view.View
        public void setPressed(boolean r1) {
            super.setPressed(r1);
            invalidate();
        }
    }

    public static /* synthetic */ boolean $r8$lambda$C1naNxm_fsEssW0zpv6NWQz_3gw(SuggestEmojiView r0, RecyclerListView.OnItemClickListener r1, View r2, MotionEvent r3) {
        return r0.lambda$createListView$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$MKTku9BGoHiLMpbhhct2jcDFjYU(SuggestEmojiView r0, View r1, int r2) {
        r0.lambda$createListView$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$MyAg8GByAIkuvK73TrrU4fEPe3I(int r1) {
        MediaDataController.getInstance(r1).checkStickers(5);
    }

    public static /* synthetic */ void $r8$lambda$ikHnHtxMIKNvcx_lTjMdbyejJbQ(SuggestEmojiView r0, String[] r1, String r2, int r3) {
        r0.lambda$searchKeywords$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$sxXCCwycBVpQJjjqF1UlsEjFhKw(SuggestEmojiView r0) {
        r0.update();
    }

    public static /* synthetic */ void $r8$lambda$xIHdPk2K802NDYSRXFYqTn7ufbw(SuggestEmojiView r0, String r1, int r2) {
        r0.lambda$searchAnimated$6(r1, r2);
    }

    /* renamed from: $r8$lambda$yKgcBwi-yW5I4gUl2bhvDB2AsLE, reason: not valid java name */
    public static /* synthetic */ void m13146$r8$lambda$yKgcBwiyW5I4gUl2bhvDB2AsLE(SuggestEmojiView r0, int r1, String r2, HashSet r3, ArrayList r4, ArrayList r5, String r6) {
        r0.lambda$searchKeywords$3(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$zLel9_3KDbfNhp8IaTZLXTM6i1s(SuggestEmojiView r0, int r1, String r2, ArrayList r3) {
        r0.lambda$searchAnimated$5(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetcontainerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m13147$$Nest$fgetcontainerView(SuggestEmojiView r0) {
        return r0.containerView;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13148$$Nest$fgetcurrentAccount(SuggestEmojiView r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdirection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13149$$Nest$fgetdirection(SuggestEmojiView r0) {
        return r0.direction;
    }

    /* renamed from: -$$Nest$fgetenterView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnchorViewDelegate m13150$$Nest$fgetenterView(SuggestEmojiView r0) {
        return r0.enterView;
    }

    /* renamed from: -$$Nest$fgethorizontalPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13151$$Nest$fgethorizontalPadding(SuggestEmojiView r0) {
        return r0.horizontalPadding;
    }

    /* renamed from: -$$Nest$fgetisCopyForbidden, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13152$$Nest$fgetisCopyForbidden(SuggestEmojiView r0) {
        return r0.isCopyForbidden;
    }

    /* renamed from: -$$Nest$fgetisSetAsStatusForbidden, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13153$$Nest$fgetisSetAsStatusForbidden(SuggestEmojiView r0) {
        return r0.isSetAsStatusForbidden;
    }

    /* renamed from: -$$Nest$fgetkeywordResults, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m13154$$Nest$fgetkeywordResults(SuggestEmojiView r0) {
        return r0.keywordResults;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m13155$$Nest$fgetlistView(SuggestEmojiView r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m13156$$Nest$fgetresourcesProvider(SuggestEmojiView r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$mdrawContainerBegin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13157$$Nest$mdrawContainerBegin(SuggestEmojiView r0, Canvas r1) {
        r0.drawContainerBegin(r1);
    }

    /* renamed from: -$$Nest$mgetPreviewDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContentPreviewViewer.ContentPreviewViewerDelegate m13158$$Nest$mgetPreviewDelegate(SuggestEmojiView r0) {
        return r0.getPreviewDelegate();
    }

    public SuggestEmojiView(Context r3, int r4, AnchorViewDelegate r5, Theme.ResourcesProvider r6) {
        super(r3);
        this.direction = 0;
        this.horizontalPadding = AndroidUtilities.dp(10.0f);
        this.lastLangChangedTime = 0;
        this.currentAccount = r4;
        this.enterView = r5;
        this.resourcesProvider = r6;
        postDelayed(new SuggestEmojiView$$ExternalSyntheticLambda1(r4), 260);
    }

    private void createListView() {
        if (this.listView != null) goto L10;
        this.path = new Path();
        this.circlePath = new Path();
        AnonymousClass2 r2 = new AnonymousClass2(this, getContext());
        this.containerView = r2;
        CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.showFloat1 = new AnimatedFloat(r2, 120, 350, r7);
        this.showFloat2 = new AnimatedFloat(this.containerView, 150, 600, r7);
        this.overshootInterpolator = new OvershootInterpolator(0.4f);
        this.leftGradientAlpha = new AnimatedFloat(this.containerView, 300, r7);
        this.rightGradientAlpha = new AnimatedFloat(this.containerView, 300, r7);
        this.arrowXAnimated = new AnimatedFloat(this.containerView, 200, r7);
        this.listViewCenterAnimated = new AnimatedFloat(this.containerView, 350, r7);
        this.listViewWidthAnimated = new AnimatedFloat(this.containerView, 350, r7);
        AnonymousClass3 r0 = new AnonymousClass3(this, getContext());
        this.listView = r0;
        Adapter r1 = new Adapter(this, this);
        this.adapter = r1;
        r0.setAdapter(r1);
        LinearLayoutManager r02 = new LinearLayoutManager(getContext());
        r02.setOrientation(0);
        this.listView.setLayoutManager(r02);
        DefaultItemAnimator r03 = new DefaultItemAnimator();
        r03.setDurations(45);
        r03.setTranslationInterpolator(r7);
        this.listView.setItemAnimator(r03);
        this.listView.setSelectorDrawableColor(Theme.getColor(Theme.key_listSelector, this.resourcesProvider));
        RecyclerListView r04 = this.listView;
        SuggestEmojiView$$ExternalSyntheticLambda3 r12 = new SuggestEmojiView$$ExternalSyntheticLambda3(this);
        r04.setOnItemClickListener(r12);
        this.listView.setOnTouchListener(new SuggestEmojiView$$ExternalSyntheticLambda4(this, r12));
        this.containerView.addView(this.listView, LayoutHelper.createFrame(-1, 52.0f));
        addView(this.containerView, LayoutHelper.createFrame(-1.0f, 66.66f, 80));
        AnchorViewDelegate r05 = this.enterView;
        if (r05 == null) goto L9;
        r05.addTextChangedListener(new AnonymousClass4(this));
        return;
    L9:
        return;
    }

    private String[] detectKeyboardLangThrottleFirstWithDelay() {
        long r0 = System.currentTimeMillis();
        if (this.lastLang != null) goto L5;
    L9:
        this.lastLangChangedTime = r0;
        return AndroidUtilities.getCurrentKeyboardLanguage();
    L5:
        if (Math.abs(r0 - this.lastLangChangedTime) > 360) goto L9;
        this.lastLangChangedTime = r0;
        return this.lastLang;
    }

    private void drawContainerBegin(Canvas r21) {
        AnchorViewDelegate r2 = this.enterView;
        if (r2 != null) goto L5;
    L16:
        int r4 = 0;
        if (this.show == true) goto L19;
    L27:
        boolean r22 = false;
    L28:
        AnimatedFloat r5 = this.showFloat1;
        if (r22 == false) goto L31;
        float r8 = 1.0f;
    L32:
        float r52 = r5.set(r8);
        AnimatedFloat r82 = this.showFloat2;
        if (r22 == false) goto L35;
        float r9 = 1.0f;
    L36:
        float r83 = r82.set(r9);
        float r92 = this.arrowXAnimated.set(this.arrowX);
        if (r52 <= 0.0f) goto L39;
    L42:
        this.path.rewind();
        float r23 = this.listView.getLeft();
        int r84 = this.listView.getLeft();
        ArrayList<MediaDataController.KeywordResult> r10 = this.keywordResults;
        if (r10 != null) goto L45;
        int r102 = 0;
    L46:
        float r85 = r84 + (r102 * AndroidUtilities.dp(44.0f));
        if (this.listViewWidthAnimated.get() > 0.0f) goto L49;
        boolean r103 = true;
    L50:
        float r11 = r85 - r23;
        AnimatedFloat r13 = this.listViewWidthAnimated;
        if (r11 > 0.0f) goto L53;
        float r112 = r13.get();
    L54:
        float r24 = this.listViewCenterAnimated.set((r23 + r85) / 2.0f, r103);
        AnchorViewDelegate r104 = this.enterView;
        if (r104 != null) goto L57;
    L63:
        float r12 = r112 / 4.0f;
        float r113 = r112 / 2.0f;
        int r105 = (int) Math.max((this.arrowX - Math.max(r12, Math.min(r113, AndroidUtilities.dp(66.0f)))) - this.listView.getLeft(), 0.0f);
        if (this.listView.getPaddingLeft() == r105) goto L66;
        int r14 = this.listView.getPaddingLeft() - r105;
        this.listView.setPadding(r105, 0, 0, 0);
        this.listView.scrollBy(r14, 0);
    L66:
        int r122 = (int) Math.max((r92 - Math.max(r12, Math.min(r113, AndroidUtilities.dp(66.0f)))) - this.listView.getLeft(), 0.0f);
        this.listView.setTranslationX(r122 - r105);
        float r106 = ((r24 - r113) + this.listView.getPaddingLeft()) + this.listView.getTranslationX();
        float r123 = (this.listView.getTop() + this.listView.getTranslationY()) + this.listView.getPaddingTop();
        if (this.direction != 0) goto L69;
        int r132 = 0;
    L70:
        float r124 = r123 + r132;
        float r25 = Math.min(((r24 + r113) + this.listView.getPaddingLeft()) + this.listView.getTranslationX(), getWidth() - this.containerView.getPaddingRight());
        float r133 = this.listView.getBottom() + this.listView.getTranslationY();
        if (this.direction != 0) goto L73;
        r4 = AndroidUtilities.dp(6.66f);
    L73:
        float r134 = r133 - r4;
        float r42 = Math.min(AndroidUtilities.dp(9.0f), r113) * 2.0f;
        int r114 = this.direction;
        if (r114 != 0) goto L76;
        RectF r115 = AndroidUtilities.rectTmp;
        float r19 = 6.66f;
        float r142 = r134 - r42;
        float r3 = r106 + r42;
        r115.set(r106, r142, r3, r134);
        this.path.arcTo(r115, 90.0f, 90.0f);
        float r7 = r124 + r42;
        r115.set(r106, r124, r3, r7);
        this.path.arcTo(r115, -180.0f, 90.0f);
        float r32 = r25 - r42;
        r115.set(r32, r124, r25, r7);
        this.path.arcTo(r115, -90.0f, 90.0f);
        r115.set(r32, r142, r25, r134);
        this.path.arcTo(r115, 0.0f, 90.0f);
        this.path.lineTo(AndroidUtilities.dp(8.66f) + r92, r134);
        this.path.lineTo(r92, AndroidUtilities.dp(6.66f) + r134);
        this.path.lineTo(r92 - AndroidUtilities.dp(8.66f), r134);
    L79:
        this.path.close();
        if (this.backgroundPaint != null) goto L83;
        Paint r33 = new Paint(1);
        this.backgroundPaint = r33;
        r33.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(2.0f)));
        this.backgroundPaint.setShadowLayer(AndroidUtilities.dp(4.33f), 0.0f, AndroidUtilities.dp(0.33333334f), 855638016);
        this.backgroundPaint.setColor(Theme.getColor(Theme.key_chat_stickersHintPanel, this.resourcesProvider));
    L83:
        if (r52 >= 1.0f) goto L89;
        this.circlePath.rewind();
        if (this.direction != 0) goto L87;
        float r34 = AndroidUtilities.dp(r19) + r134;
    L88:
        double r6 = r92 - r106;
        double r43 = r34 - r124;
        double r125 = r92 - r25;
        double r143 = r34 - r134;
        this.circlePath.addCircle(r92, r34, ((float) Math.sqrt(Math.max(Math.max(Math.pow(r6, 2.0d) + Math.pow(r43, 2.0d), Math.pow(r125, 2.0d) + Math.pow(r43, 2.0d)), Math.max(Math.pow(r6, 2.0d) + Math.pow(r143, 2.0d), Math.pow(r125, 2.0d) + Math.pow(r143, 2.0d))))) * r52, Path.Direction.CW);
        r21.save();
        r21.clipPath(this.circlePath);
        r21.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) (255.0f * r52), 31);
        goto L89
    L87:
        r34 = r124 - AndroidUtilities.dp(r19);
    L89:
        r21.drawPath(this.path, this.backgroundPaint);
        r21.save();
        r21.clipPath(this.path);
        return;
    L76:
        r19 = 6.66f;
        if (r114 != 1) goto L79;
        RectF r35 = AndroidUtilities.rectTmp;
        float r72 = r25 - r42;
        float r116 = r124 + r42;
        r35.set(r72, r124, r25, r116);
        this.path.arcTo(r35, -90.0f, 90.0f);
        float r144 = r134 - r42;
        r35.set(r72, r144, r25, r134);
        this.path.arcTo(r35, 0.0f, 90.0f);
        float r44 = r42 + r106;
        r35.set(r106, r144, r44, r134);
        this.path.arcTo(r35, 90.0f, 90.0f);
        r35.set(r106, r124, r44, r116);
        this.path.arcTo(r35, -180.0f, 90.0f);
        this.path.lineTo(r92 - AndroidUtilities.dp(8.66f), r124);
        this.path.lineTo(r92, r124 - AndroidUtilities.dp(6.66f));
        this.path.lineTo(AndroidUtilities.dp(8.66f) + r92, r124);
        goto L79
    L69:
        r132 = AndroidUtilities.dp(6.66f);
        goto L70
    L57:
        if (r104.getEditField() == null) goto L63;
        int r107 = this.direction;
        if (r107 != 0) goto L61;
        this.containerView.setTranslationY((((-this.enterView.getEditField().getHeight()) - this.enterView.getEditField().getScrollY()) + this.lastSpanY) + AndroidUtilities.dp(5.0f));
        goto L63
    L61:
        if (r107 != 1) goto L63;
        this.containerView.setTranslationY(((((-getMeasuredHeight()) - this.enterView.getEditField().getScrollY()) + this.lastSpanY) + AndroidUtilities.dp(20.0f)) + this.containerView.getHeight());
        goto L63
    L53:
        r112 = r13.set(r11, r103);
        goto L54
    L49:
        r103 = false;
        goto L50
    L45:
        r102 = r10.size();
        goto L46
    L39:
        if (r83 > 0.0f) goto L42;
        if (r22 == true) goto L42;
        this.containerView.setVisibility(8);
        goto L42
    L35:
        r9 = 0.0f;
        goto L36
    L31:
        r8 = 0.0f;
        goto L32
    L19:
        if (this.forceClose == true) goto L27;
        ArrayList<MediaDataController.KeywordResult> r26 = this.keywordResults;
        if (r26 == null) goto L27;
        if (r26.isEmpty() == true) goto L27;
        if (this.clear == true) goto L27;
        r22 = true;
        goto L28
    L5:
        if (r2.getEditField() == null) goto L16;
        Emoji.EmojiSpan r27 = this.arrowToSpan;
        if (r27 == null) goto L12;
        if (r27.drawn == false) goto L12;
        float r28 = this.enterView.getEditField().getX() + this.enterView.getEditField().getPaddingLeft();
        Emoji.EmojiSpan r36 = this.arrowToSpan;
        this.arrowX = r28 + r36.lastDrawX;
        this.lastSpanY = r36.lastDrawY;
    L12:
        if (this.arrowToStart == null) goto L16;
        if (this.arrowToEnd == null) goto L16;
        this.arrowX = (this.enterView.getEditField().getX() + this.enterView.getEditField().getPaddingLeft()) + AndroidUtilities.dp(12.0f);
        goto L16
    }

    private ContentPreviewViewer.ContentPreviewViewerDelegate getPreviewDelegate() {
        if (this.previewDelegate != null) goto L6;
        this.previewDelegate = new AnonymousClass1(this);
    L6:
        return this.previewDelegate;
    }

    private /* synthetic */ void lambda$createListView$1(View r1, int r2) {
        onClick(EmojiImageView.m13161$$Nest$fgetemoji((EmojiImageView) r1));
    }

    private /* synthetic */ boolean lambda$createListView$2(RecyclerListView.OnItemClickListener r8, View r9, MotionEvent r10) {
        return ContentPreviewViewer.getInstance().onTouch(r10, this.listView, 0, r8, getPreviewDelegate(), this.resourcesProvider);
    }

    private /* synthetic */ void lambda$searchAnimated$5(int r2, String r3, ArrayList r4) {
        if (r2 != this.lastQueryId) goto L15;
        this.lastQuery = r3;
        this.lastQueryType = 2;
        r4.remove(r4.size() - 1);
        if (r4.isEmpty() == true) goto L13;
        this.clear = false;
        this.forceClose = false;
        createListView();
        FrameLayout r32 = this.containerView;
        if (r32 == null) goto L9;
        r32.setVisibility(0);
        this.containerView.invalidate();
    L9:
        this.keywordResults = r4;
        Adapter r1 = this.adapter;
        if (r1 == null) goto L16;
        r1.notifyDataSetChanged();
        return;
    L16:
        return;
    L13:
        this.clear = true;
        forceClose();
        return;
    }

    private /* synthetic */ void lambda$searchAnimated$6(String r8, int r9) {
        ArrayList<MediaDataController.KeywordResult> r1 = new ArrayList(1);
        r1.add(new MediaDataController.KeywordResult(r8, null));
        MediaDataController.getInstance(this.currentAccount).fillWithAnimatedEmoji(r1, 15, false, false, false, new SuggestEmojiView$$ExternalSyntheticLambda7(this, r9, r8, r1));
    }

    private /* synthetic */ void lambda$searchKeywords$3(int r5, String r6, HashSet r7, ArrayList r8, ArrayList r9, String r10) {
        if (r5 != this.lastQueryId) goto L32;
        this.lastQueryType = 1;
        this.lastQuery = r6;
        if (r9 == null) goto L13;
        int r0 = r9.size();
        int r1 = 0;
    L8:
        if (r1 >= r0) goto L13;
        Object r2 = r9.get(r1);
        r1 = r1 + 1;
        MediaDataController.KeywordResult r22 = (MediaDataController.KeywordResult) r2;
        if (r7.contains(r22.emoji) == true) goto L8;
        r7.add(r22.emoji);
        r8.add(r22);
    L13:
        if (r8.isEmpty() == true) goto L24;
        this.clear = false;
        this.forceClose = false;
        createListView();
        FrameLayout r52 = this.containerView;
        if (r52 == null) goto L17;
        r52.setVisibility(0);
    L17:
        this.lastSpanY = AndroidUtilities.dp(10.0f);
        this.keywordResults = r9;
        this.arrowToStart = 0;
        this.arrowToEnd = Integer.valueOf(r6.length());
        FrameLayout r53 = this.containerView;
        if (r53 == null) goto L20;
        r53.invalidate();
    L20:
        Adapter r4 = this.adapter;
        if (r4 == null) goto L31;
        r4.notifyDataSetChanged();
        return;
    L31:
        return;
    L24:
        this.keywordResults = null;
        this.clear = true;
        forceClose();
        return;
    }

    private /* synthetic */ void lambda$searchKeywords$4(String[] r13, String r14, int r15) {
        HashSet r4 = new HashSet();
        ArrayList r5 = new ArrayList();
        MediaDataController r6 = MediaDataController.getInstance(this.currentAccount);
        SuggestEmojiView$$ExternalSyntheticLambda6 r0 = new SuggestEmojiView$$ExternalSyntheticLambda6(this, r15, r14, r4, r5);
        if (SharedConfig.suggestAnimatedEmoji == true) goto L5;
    L8:
        boolean r12 = false;
    L9:
        r6.getEmojiSuggestions(r13, r14, true, r0, r12);
        return;
    L5:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L8;
        r12 = true;
        goto L9
    }

    private CharSequence makeEmoji(String r6) {
        if (this.enterView.getEditField() == null) goto L5;
        Paint.FontMetricsInt r0 = this.enterView.getEditField().getPaint().getFontMetricsInt();
    L6:
        if (r0 != null) goto L8;
        Paint r02 = new Paint();
        r02.setTextSize(AndroidUtilities.dp(18.0f));
        r0 = r02.getFontMetricsInt();
    L8:
        if (r6 == null) goto L20;
        if (r6.startsWith("animated_") == false) goto L20;
        long r2 = Long.parseLong(r6.substring(9));     // Catch: Exception -> L18
        TLRPC.Document r5 = AnimatedEmojiDrawable.findDocument(this.currentAccount, r2);     // Catch: Exception -> L18
        SpannableString r62 = new SpannableString(MessageObject.findAnimatedEmojiEmoticon(r5));     // Catch: Exception -> L18
        if (r5 != null) goto L15;
        AnimatedEmojiSpan r52 = new AnimatedEmojiSpan(r2, r0);     // Catch: Exception -> L18
    L16:
        r62.setSpan(r52, 0, r62.length(), 33);     // Catch: Exception -> L18
        return r62;
    L15:
        r52 = new AnimatedEmojiSpan(r5, r0);     // Catch: Exception -> L18
    L18:
        return null;
    L20:
        return Emoji.replaceEmoji(r6, r0, true);
    L5:
        r0 = null;
        goto L6
    }

    private void onClick(String r12) {
        if (this.show == false) goto L65;
        AnchorViewDelegate r0 = this.enterView;
        if (r0 != null) goto L7;
        return;
    L7:
        if ((r0.getFieldText() instanceof Spanned) == true) goto L10;
        return;
    L10:
        if (this.arrowToSpan == null) goto L12;
        int r02 = ((Spanned) this.enterView.getFieldText()).getSpanStart(this.arrowToSpan);
        int r2 = ((Spanned) this.enterView.getFieldText()).getSpanEnd(this.arrowToSpan);
    L17:
        Editable r3 = this.enterView.getEditText();
        if (r3 == null) goto L69;
        if (r02 < 0) goto L70;
        if (r2 >= 0) goto L22;
        return;
    L22:
        if (r02 <= r3.length()) goto L24;
        return;
    L24:
        if (r2 <= r3.length()) goto L27;
        return;
    L27:
        if (this.arrowToSpan != null) goto L29;
    L32:
        String r1 = r3.toString();
        String r03 = r1.substring(r02, r2);
        int r4 = r03.length();
    L33:
        r2 = r2 - r4;
        int r5 = 0;
        if (r2 < 0) goto L57;
        int r6 = r2 + r4;
        if (r1.substring(r2, r6).equals(r03) == false) goto L57;
        CharSequence r7 = makeEmoji(r12);
        if (r7 == null) goto L57;
        AnimatedEmojiSpan[] r8 = (AnimatedEmojiSpan[]) r3.getSpans(r2, r6, AnimatedEmojiSpan.class);
        if (r8 != null) goto L42;
    L44:
        Emoji.EmojiSpan[] r82 = (Emoji.EmojiSpan[]) r3.getSpans(r2, r6, Emoji.EmojiSpan.class);
        if (r82 == null) goto L49;
        int r9 = r82.length;
    L47:
        if (r5 >= r9) goto L49;
        r3.removeSpan(r82[r5]);
        r5 = r5 + 1;
    L49:
        r3.replace(r2, r6, _UrlKt.FRAGMENT_ENCODE_SET);
        r3.insert(r2, r7);
        goto L33
    L42:
        if (r8.length <= 0) goto L44;
    L57:
        performHapticFeedback(VibratorUtils.getType(3), 1);     // Catch: Exception -> L56
    L52:
        Emoji.addRecentEmoji(r12);
        this.show = false;
        this.forceClose = true;
        this.lastQueryType = 0;
        FrameLayout r11 = this.containerView;
        if (r11 == null) goto L73;
        r11.invalidate();
        return;
    L73:
        return;
    L29:
        if ((this.enterView.getFieldText() instanceof Spannable) == false) goto L31;
        ((Spannable) this.enterView.getFieldText()).removeSpan(this.arrowToSpan);
    L31:
        this.arrowToSpan = null;
        goto L32
    L70:
        return;
    L69:
        return;
    L12:
        Integer r04 = this.arrowToStart;
        if (r04 != null) goto L15;
        return;
    L15:
        if (this.arrowToEnd == null) goto L68;
        r02 = r04.intValue();
        r2 = this.arrowToEnd.intValue();
        this.arrowToEnd = null;
        this.arrowToStart = null;
        goto L17
    L68:
        return;
    }

    private void searchAnimated(String r4) {
        if (r4 == null) goto L33;
        String r0 = this.lastQuery;
        if (r0 != null) goto L7;
    L20:
        int r02 = this.lastQueryId + 1;
        this.lastQueryId = r02;
        Runnable r1 = this.searchRunnable;
        if (r1 == null) goto L23;
        AndroidUtilities.cancelRunOnUIThread(r1);
    L23:
        this.searchRunnable = new SuggestEmojiView$$ExternalSyntheticLambda2(this, r4, r02);
        ArrayList<MediaDataController.KeywordResult> r42 = this.keywordResults;
        if (r42 != null) goto L26;
    L30:
        AndroidUtilities.runOnUIThread(this.searchRunnable, 600);
        return;
    L26:
        if (r42.isEmpty() == true) goto L30;
        this.searchRunnable.run();
        return;
    L7:
        if (this.lastQueryType != 2) goto L20;
        if (r0.equals(r4) == false) goto L20;
        if (this.clear == true) goto L20;
        ArrayList<MediaDataController.KeywordResult> r03 = this.keywordResults;
        if (r03 == null) goto L20;
        if (r03.isEmpty() == true) goto L20;
        this.forceClose = false;
        createListView();
        FrameLayout r04 = this.containerView;
        if (r04 == null) goto L32;
        r04.setVisibility(0);
        this.containerView.invalidate();
        return;
    L32:
        return;
    }

    private void searchKeywords(String r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        String r0 = this.lastQuery;
        if (r0 != null) goto L7;
    L18:
        int r02 = this.lastQueryId + 1;
        this.lastQueryId = r02;
        if (this.loadingKey == null) goto L21;
        MediaDataController.getInstance(this.currentAccount).cancelSearchStickers(this.loadingKey);
        this.loadingKey = null;
    L21:
        String[] r1 = detectKeyboardLangThrottleFirstWithDelay();
        String[] r3 = this.lastLang;
        if (r3 != null) goto L24;
    L25:
        MediaDataController.getInstance(this.currentAccount).fetchNewEmojiKeywords(r1);
    L26:
        this.lastLang = r1;
        Runnable r32 = this.searchRunnable;
        if (r32 == null) goto L29;
        AndroidUtilities.cancelRunOnUIThread(r32);
        this.searchRunnable = null;
    L29:
        this.searchRunnable = new SuggestEmojiView$$ExternalSyntheticLambda5(this, r1, r5, r02);
        ArrayList<MediaDataController.KeywordResult> r52 = this.keywordResults;
        if (r52 != null) goto L32;
    L36:
        AndroidUtilities.runOnUIThread(this.searchRunnable, 600);
        return;
    L32:
        if (r52.isEmpty() == true) goto L36;
        this.searchRunnable.run();
        return;
    L24:
        if (Arrays.equals(r1, r3) == true) goto L26;
    L7:
        if (this.lastQueryType != 1) goto L18;
        if (r0.equals(r5) == false) goto L18;
        if (this.clear == true) goto L18;
        ArrayList<MediaDataController.KeywordResult> r03 = this.keywordResults;
        if (r03 == null) goto L18;
        if (r03.isEmpty() == true) goto L18;
        this.forceClose = false;
        createListView();
        this.containerView.setVisibility(0);
        this.lastSpanY = AndroidUtilities.dp(10.0f);
        this.containerView.invalidate();
    }

    private void update() {
        this.updateRunnable = null;
        AnchorViewDelegate r1 = this.enterView;
        if (r1 != null) goto L5;
    L53:
        this.show = false;
        this.forceClose = true;
        FrameLayout r10 = this.containerView;
        if (r10 == null) goto L61;
        r10.invalidate();
        return;
    L61:
        return;
    L5:
        if (r1.getEditField() == null) goto L53;
        if (this.enterView.getFieldText() == null) goto L53;
        int r12 = this.enterView.getEditField().getSelectionStart();
        int r4 = this.enterView.getEditField().getSelectionEnd();
        if (r12 == r4) goto L15;
        this.show = false;
        FrameLayout r102 = this.containerView;
        if (r102 == null) goto L57;
        r102.invalidate();
        return;
    L57:
        return;
    L15:
        CharSequence r5 = this.enterView.getFieldText();
        boolean r6 = r5 instanceof Spanned;
        if (r6 == false) goto L18;
        Emoji.EmojiSpan[] r7 = (Emoji.EmojiSpan[]) ((Spanned) r5).getSpans(Math.max(0, r4 - 24), r4, Emoji.EmojiSpan.class);
    L19:
        if (r7 != null) goto L21;
    L34:
        if (r6 == false) goto L36;
        AnimatedEmojiSpan[] r13 = (AnimatedEmojiSpan[]) ((Spanned) r5).getSpans(Math.max(0, r4), r4, AnimatedEmojiSpan.class);
    L37:
        if (r13 == null) goto L41;
        if (r13.length == 0) goto L41;
    L46:
        Runnable r14 = this.searchRunnable;
        if (r14 == null) goto L49;
        AndroidUtilities.cancelRunOnUIThread(r14);
        this.searchRunnable = null;
    L49:
        this.show = false;
        FrameLayout r103 = this.containerView;
        if (r103 == null) goto L60;
        r103.invalidate();
        return;
    L60:
        return;
    L41:
        if (r4 >= 52) goto L46;
        this.show = true;
        createListView();
        this.arrowToSpan = null;
        searchKeywords(r5.toString().substring(0, r4));
        FrameLayout r104 = this.containerView;
        if (r104 == null) goto L59;
        r104.invalidate();
        return;
    L59:
        return;
    L36:
        r13 = null;
        goto L37
    L21:
        if (r7.length <= 0) goto L34;
        if (SharedConfig.suggestAnimatedEmoji == false) goto L34;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L34;
        Emoji.EmojiSpan r42 = r7[r7.length - 1];
        if (r42 == null) goto L46;
        Spanned r62 = (Spanned) r5;
        int r72 = r62.getSpanStart(r42);
        int r63 = r62.getSpanEnd(r42);
        if (r12 != r63) goto L46;
        String r15 = r5.toString().substring(r72, r63);
        this.show = true;
        createListView();
        this.arrowToSpan = r42;
        this.arrowToEnd = null;
        this.arrowToStart = null;
        searchAnimated(r15);
        FrameLayout r105 = this.containerView;
        if (r105 == null) goto L58;
        r105.invalidate();
        return;
    L58:
        return;
    L18:
        r7 = null;
        goto L19
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.newEmojiSuggestionsAvailable) goto L11;
        ArrayList<MediaDataController.KeywordResult> r12 = this.keywordResults;
        if (r12 != null) goto L7;
        return;
    L7:
        if (r12.isEmpty() == true) goto L20;
        fireUpdate();
        return;
    L20:
        return;
    L11:
        if (r1 == NotificationCenter.emojiLoaded) goto L13;
        return;
    L13:
        if (this.listView == null) goto L22;
        int r13 = 0;
    L16:
        if (r13 >= this.listView.getChildCount()) goto L23;
        this.listView.getChildAt(r13).invalidate();
        r13 = r13 + 1;
        goto L16
    L23:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r7) {
        if (this.listView == null) goto L5;
        float r0 = this.listViewWidthAnimated.get();
        float r1 = this.listViewCenterAnimated.get();
        RectF r2 = AndroidUtilities.rectTmp;
        float r02 = r0 / 2.0f;
        r2.set(((r1 - r02) + this.listView.getPaddingLeft()) + this.listView.getTranslationX(), this.listView.getTop() + this.listView.getPaddingTop(), Math.min(((r1 + r02) + this.listView.getPaddingLeft()) + this.listView.getTranslationX(), getWidth() - this.containerView.getPaddingRight()), this.listView.getBottom());
        r2.offset(this.containerView.getX(), this.containerView.getY());
        if (this.show == false) goto L13;
        if (r2.contains(r7.getX(), r7.getY()) == false) goto L13;
        return super.dispatchTouchEvent(r7);
    L13:
        if (r7.getAction() != 0) goto L17;
        return false;
    L17:
        if (r7.getAction() != 0) goto L20;
        r7.setAction(3);
    L20:
        return super.dispatchTouchEvent(r7);
    L5:
        return super.dispatchTouchEvent(r7);
    }

    public void drawContainerEnd(Canvas r14) {
        float r0 = this.listViewWidthAnimated.get();
        float r1 = this.listViewCenterAnimated.get();
        float r02 = r0 / 2.0f;
        float r2 = ((r1 - r02) + this.listView.getPaddingLeft()) + this.listView.getTranslationX();
        float r3 = this.listView.getTop() + this.listView.getPaddingTop();
        float r03 = Math.min(((r1 + r02) + this.listView.getPaddingLeft()) + this.listView.getTranslationX(), getWidth() - this.containerView.getPaddingRight());
        float r12 = this.listView.getBottom();
        AnimatedFloat r4 = this.leftGradientAlpha;
        if (this.listView.canScrollHorizontally(-1) == false) goto L5;
        float r5 = 1.0f;
    L6:
        float r42 = r4.set(r5);
        if (r42 <= 0.0f) goto L9;
        int r22 = (int) r2;
        Theme.chat_gradientRightDrawable.setBounds(r22, (int) r3, AndroidUtilities.dp(32.0f) + r22, (int) r12);
        Theme.chat_gradientRightDrawable.setAlpha((int) (r42 * 255.0f));
        Theme.chat_gradientRightDrawable.draw(r14);
    L9:
        AnimatedFloat r23 = this.rightGradientAlpha;
        if (this.listView.canScrollHorizontally(1) == false) goto L12;
        float r43 = 1.0f;
    L13:
        float r24 = r23.set(r43);
        if (r24 <= 0.0f) goto L16;
        int r04 = (int) r03;
        Theme.chat_gradientLeftDrawable.setBounds(r04 - AndroidUtilities.dp(32.0f), (int) r3, r04, (int) r12);
        Theme.chat_gradientLeftDrawable.setAlpha((int) (r24 * 255.0f));
        Theme.chat_gradientLeftDrawable.draw(r14);
    L16:
        r14.restore();
        if (this.showFloat1.get() >= 1.0f) goto L20;
        r14.restore();
        r14.restore();
        return;
    L20:
        return;
    L12:
        r43 = 0.0f;
        goto L13
    L5:
        r5 = 0.0f;
        goto L6
    }

    public int emojiCacheType() {
        return 2;
    }

    public void fireUpdate() {
        Runnable r0 = this.updateRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
    L5:
        SuggestEmojiView$$ExternalSyntheticLambda0 r02 = new SuggestEmojiView$$ExternalSyntheticLambda0(this);
        this.updateRunnable = r02;
        AndroidUtilities.runOnUIThread(r02, 16);
    }

    public void forbidCopy() {
        this.isCopyForbidden = true;
    }

    public void forbidSetAsStatus() {
        this.isSetAsStatusForbidden = true;
    }

    public void forceClose() {
        Runnable r0 = this.updateRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.updateRunnable = null;
    L5:
        this.show = false;
        this.forceClose = true;
        FrameLayout r1 = this.containerView;
        if (r1 == null) goto L9;
        r1.invalidate();
        return;
    }

    public AnchorViewDelegate getDelegate() {
        return this.enterView;
    }

    public int getDirection() {
        return this.direction;
    }

    @Override // android.view.View
    public boolean isShown() {
        return this.show;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.newEmojiSuggestionsAvailable);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.newEmojiSuggestionsAvailable);
    }

    public void onTextSelectionChanged(int r1, int r2) {
        fireUpdate();
    }

    public void setDelegate(AnchorViewDelegate r1) {
        this.enterView = r1;
    }

    public void setDirection(int r2) {
        if (this.direction == r2) goto L6;
        this.direction = r2;
        requestLayout();
        return;
    }

    public void setHorizontalPadding(int r1) {
        this.horizontalPadding = r1;
    }

    public void updateColors() {
        Paint r0 = this.backgroundPaint;
        if (r0 == null) goto L5;
        r0.setColor(Theme.getColor(Theme.key_chat_stickersHintPanel, this.resourcesProvider));
    L5:
        Drawable r02 = Theme.chat_gradientLeftDrawable;
        int r2 = Theme.key_chat_stickersHintPanel;
        int r3 = Theme.getColor(r2, this.resourcesProvider);
        PorterDuff.Mode r4 = PorterDuff.Mode.MULTIPLY;
        r02.setColorFilter(new PorterDuffColorFilter(r3, r4));
        Theme.chat_gradientRightDrawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r2, this.resourcesProvider), r4));
    }
}
