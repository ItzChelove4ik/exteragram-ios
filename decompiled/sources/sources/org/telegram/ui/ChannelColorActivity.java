package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ChatThemeController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.ResultCallback;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.EmojiThemes;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.MessageDrawable;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeColors;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.ThemePreviewMessagesCell;
import org.telegram.ui.Cells.ThemesHorizontalListCell;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.ChatThemeBottomSheet;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Easings;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.ThemeSmallPreviewView;
import org.telegram.ui.PeerColorActivity;
import org.telegram.ui.SelectAnimatedEmojiDialog;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.PreviewView;
import org.telegram.ui.ThemePreviewActivity;

/* loaded from: classes6.dex */
public class ChannelColorActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    protected Adapter adapter;
    public Drawable backgroundDrawable;
    public TL_stories.TL_premium_boostsStatus boostsStatus;
    private BaseFragment bulletinFragment;
    protected ButtonWithCounterView button;
    protected FrameLayout buttonContainer;
    private View changeDayNightView;
    private ValueAnimator changeDayNightViewAnimator;
    private float changeDayNightViewProgress;
    private final SparseIntArray currentColors;
    public int currentLevel;
    public int currentProfileColor;
    public long currentProfileEmoji;
    public int currentReplyColor;
    public long currentReplyEmoji;
    public TLRPC.EmojiStatus currentStatusEmoji;
    public TLRPC.WallPaper currentWallpaper;
    private ActionBarMenuItem dayNightItem;
    public final long dialogId;
    private final Paint dividerPaint;
    protected int emptyRow;
    private boolean forceDark;
    public TLRPC.WallPaper galleryWallpaper;
    private boolean isDark;
    protected boolean isGroup;
    protected GridLayoutManager layoutManager;
    protected RecyclerListView listView;
    private SpannableStringBuilder lock;
    protected int messagesPreviewRow;
    private final MessageDrawable msgInDrawable;
    private final MessageDrawable msgInDrawableSelected;
    private final Drawable msgOutCheckReadDrawable;
    private final MessageDrawable msgOutDrawable;
    private final MessageDrawable msgOutDrawableSelected;
    private final Drawable msgOutHalfCheckDrawable;
    protected int packEmojiHintRow;
    protected int packEmojiRow;
    protected int packStickerHintRow;
    protected int packStickerRow;
    private Theme.ResourcesProvider parentResourcesProvider;
    protected int profileColorGridRow;
    protected int profileEmojiRow;
    protected int profileHintRow;
    protected int profilePreviewRow;
    protected int removeProfileColorRow;
    protected int removeProfileColorShadowRow;
    protected int replyColorListRow;
    protected int replyEmojiRow;
    protected int replyHintRow;
    protected int rowsCount;
    private SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow selectAnimatedEmojiDialog;
    public int selectedProfileColor;
    public long selectedProfileEmoji;
    public int selectedReplyColor;
    public long selectedReplyEmoji;
    public TLRPC.EmojiStatus selectedStatusEmoji;
    public TLRPC.WallPaper selectedWallpaper;
    protected int statusEmojiRow;
    protected int statusHintRow;
    private RLottieDrawable sunDrawable;
    protected int wallpaperHintRow;
    protected int wallpaperRow;
    protected int wallpaperThemesRow;

    /* renamed from: org.telegram.ui.ChannelColorActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ChannelColorActivity this$0;

        public AnonymousClass1(ChannelColorActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L13;
            ChannelColorActivity r22 = this.this$0;
            if (r22.currentLevel >= r22.minLevelRequired()) goto L7;
        L10:
            this.this$0.finishFragment();
            return;
        L7:
            if (this.this$0.hasUnsavedChanged() == false) goto L10;
            ChannelColorActivity.m7298$$Nest$mshowUnsavedAlert(this.this$0);
            return;
        L13:
            if (r2 != 1) goto L16;
            this.this$0.toggleTheme();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChannelColorActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements ThemePreviewActivity.DayNightSwitchDelegate {
        final /* synthetic */ ChannelColorActivity this$0;

        public AnonymousClass2(ChannelColorActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ThemePreviewActivity.DayNightSwitchDelegate
        public boolean isDark() {
            if (ChannelColorActivity.access$2200(this.this$0) == null) goto L7;
            return ChannelColorActivity.access$2300(this.this$0).isDark();
        L7:
            return Theme.isCurrentThemeDark();
        }

        @Override // org.telegram.ui.ThemePreviewActivity.DayNightSwitchDelegate
        public boolean supportsAnimation() {
            return false;
        }

        @Override // org.telegram.ui.ThemePreviewActivity.DayNightSwitchDelegate
        public void switchDayNight(boolean r3) {
            if ((ChannelColorActivity.access$2400(this.this$0) instanceof ThemeDelegate) == false) goto L5;
            ((ThemeDelegate) ChannelColorActivity.access$2500(this.this$0)).toggle();
        L5:
            this.this$0.setForceDark(isDark(), false);
            this.this$0.updateColors(false);
        }
    }

    /* renamed from: org.telegram.ui.ChannelColorActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements Bulletin.Delegate {
        final /* synthetic */ ChannelColorActivity this$0;

        public AnonymousClass3(ChannelColorActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return this.this$0.buttonContainer.getMeasuredHeight();
        }
    }

    /* renamed from: org.telegram.ui.ChannelColorActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends LimitReachedBottomSheet {
        final /* synthetic */ ChannelColorActivity this$0;
        final /* synthetic */ int val$level;

        public AnonymousClass4(ChannelColorActivity r1, BaseFragment r2, Context r3, int r4, int r5, Theme.ResourcesProvider r6, int r7) {
            this.this$0 = r1;
            this.val$level = r7;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.Premium.LimitReachedBottomSheet
        public int channelColorLevelMin() {
            return this.val$level;
        }
    }

    /* renamed from: org.telegram.ui.ChannelColorActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends SelectAnimatedEmojiDialog {
        final /* synthetic */ ChannelColorActivity this$0;
        final /* synthetic */ Utilities.Callback3 val$onSet;
        final /* synthetic */ SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] val$popup;

        public AnonymousClass5(ChannelColorActivity r1, BaseFragment r2, Context r3, boolean r4, Integer r5, int r6, boolean r7, Theme.ResourcesProvider r8, int r9, int r10, Utilities.Callback3 r11, SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] r12) {
            this.this$0 = r1;
            this.val$onSet = r11;
            this.val$popup = r12;
            super(r2, r3, r4, r5, r6, r7, r8, r9, r10);
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public long getDialogId() {
            return this.this$0.dialogId;
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public float getScrimDrawableTranslationY() {
            return 0.0f;
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public void onEmojiSelected(View r1, Long r2, TLRPC.Document r3, TL_stars.TL_starGiftUnique r4, Integer r5) {
            Utilities.Callback3 r12 = this.val$onSet;
            if (r12 == null) goto L9;
            if (r2 != null) goto L6;
            long r22 = 0;
        L7:
            r12.run(Long.valueOf(r22), r5, r4);
            goto L9
        L6:
            r22 = r2.longValue();
        L9:
            if (this.val$popup[0] == null) goto L12;
            ChannelColorActivity.m7294$$Nest$fputselectAnimatedEmojiDialog(this.this$0, null);
            this.val$popup[0].dismiss();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChannelColorActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow {
        final /* synthetic */ ChannelColorActivity this$0;

        public AnonymousClass6(ChannelColorActivity r1, View r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow, android.widget.PopupWindow
        public void dismiss() {
            super.dismiss();
            ChannelColorActivity.m7294$$Nest$fputselectAnimatedEmojiDialog(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.ChannelColorActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends View {
        final /* synthetic */ ChannelColorActivity this$0;
        final /* synthetic */ Bitmap val$bitmap;
        final /* synthetic */ Canvas val$bitmapCanvas;
        final /* synthetic */ Paint val$bitmapPaint;
        final /* synthetic */ float val$cx;
        final /* synthetic */ float val$cy;
        final /* synthetic */ float val$r;
        final /* synthetic */ float val$x;
        final /* synthetic */ Paint val$xRefPaint;
        final /* synthetic */ float val$y;

        public AnonymousClass7(ChannelColorActivity r1, Context r2, Canvas r3, float r4, float r5, float r6, Paint r7, Bitmap r8, Paint r9, float r10, float r11) {
            this.this$0 = r1;
            this.val$bitmapCanvas = r3;
            this.val$cx = r4;
            this.val$cy = r5;
            this.val$r = r6;
            this.val$xRefPaint = r7;
            this.val$bitmap = r8;
            this.val$bitmapPaint = r9;
            this.val$x = r10;
            this.val$y = r11;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            super.onDraw(r7);
            if (ChannelColorActivity.m7282$$Nest$fgetisDark(this.this$0) == true) goto L5;
            r7.drawCircle(this.val$cx, this.val$cy, this.val$r * (1.0f - ChannelColorActivity.m7278$$Nest$fgetchangeDayNightViewProgress(this.this$0)), this.val$bitmapPaint);
        L9:
            r7.save();
            r7.translate(this.val$x, this.val$y);
            ChannelColorActivity.m7280$$Nest$fgetdayNightItem(this.this$0).draw(r7);
            r7.restore();
            return;
        L5:
            if (ChannelColorActivity.m7278$$Nest$fgetchangeDayNightViewProgress(this.this$0) <= 0.0f) goto L7;
            this.val$bitmapCanvas.drawCircle(this.val$cx, this.val$cy, this.val$r * ChannelColorActivity.m7278$$Nest$fgetchangeDayNightViewProgress(this.this$0), this.val$xRefPaint);
        L7:
            r7.drawBitmap(this.val$bitmap, 0.0f, 0.0f, this.val$bitmapPaint);
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.ChannelColorActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements ValueAnimator.AnimatorUpdateListener {
        boolean changedNavigationBarColor;
        final /* synthetic */ ChannelColorActivity this$0;

        public AnonymousClass8(ChannelColorActivity r1) {
            this.this$0 = r1;
            this.changedNavigationBarColor = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            ChannelColorActivity.m7292$$Nest$fputchangeDayNightViewProgress(this.this$0, ((Float) r2.getAnimatedValue()).floatValue());
            ChannelColorActivity.m7277$$Nest$fgetchangeDayNightView(this.this$0).invalidate();
            if (this.changedNavigationBarColor == false) goto L5;
            return;
        L5:
            if (ChannelColorActivity.m7278$$Nest$fgetchangeDayNightViewProgress(this.this$0) <= 0.5f) goto L9;
            this.changedNavigationBarColor = true;
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChannelColorActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ ChannelColorActivity this$0;

        public AnonymousClass9(ChannelColorActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            if (ChannelColorActivity.m7277$$Nest$fgetchangeDayNightView(this.this$0) != null) goto L5;
        L8:
            ChannelColorActivity.m7291$$Nest$fputchangeDayNightViewAnimator(this.this$0, null);
            super.onAnimationEnd(r4);
            return;
        L5:
            if (ChannelColorActivity.m7277$$Nest$fgetchangeDayNightView(this.this$0).getParent() == null) goto L7;
            ((ViewGroup) ChannelColorActivity.m7277$$Nest$fgetchangeDayNightView(this.this$0).getParent()).removeView(ChannelColorActivity.m7277$$Nest$fgetchangeDayNightView(this.this$0));
        L7:
            ChannelColorActivity.m7290$$Nest$fputchangeDayNightView(this.this$0, null);
            goto L8
        }
    }

    public class Adapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ ChannelColorActivity this$0;

        public static /* synthetic */ void $r8$lambda$m6vMWwHLWHorg0zFGpWBWKZ1vcs(Adapter r0, String r1) {
            r0.lambda$onCreateViewHolder$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$nPRRQJeNQ0_36VvFY8WSLcReBqk(Adapter r0, Integer r1) {
            r0.lambda$onCreateViewHolder$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$xl1CcyRaq6pjl3bA3r8KIMrdnRg(Adapter r0, PeerColorPicker r1, View r2, int r3) {
            r0.lambda$onCreateViewHolder$1(r1, r2, r3);
        }

        public Adapter(ChannelColorActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0(String r4) {
            if (r4 != null) goto L4;
            ChannelColorActivity r42 = this.this$0;
            r42.selectedWallpaper = r42.galleryWallpaper;
        L8:
            this.this$0.updateButton(true);
            this.this$0.updateMessagesPreview(true);
            return;
        L4:
            boolean r0 = r4.equals("❌");
            ChannelColorActivity r1 = this.this$0;
            if (r0 == false) goto L7;
            r1.selectedWallpaper = null;
            goto L8
        L7:
            r1.selectedWallpaper = new TLRPC.TL_wallPaperNoFile();
            TLRPC.WallPaper r02 = this.this$0.selectedWallpaper;
            r02.id = 0;
            r02.flags |= 4;
            r02.settings = new TLRPC.TL_wallPaperSettings();
            this.this$0.selectedWallpaper.settings.emoticon = r4;
            goto L8
        }

        private /* synthetic */ void lambda$onCreateViewHolder$1(PeerColorPicker r5, View r6, int r7) {
            ChannelColorActivity r0 = this.this$0;
            r0.selectedReplyColor = r5.toColorId(r7);
            this.this$0.updateButton(true);
            this.this$0.updateMessagesPreview(true);
            this.this$0.updateProfilePreview(true);
            if (r6.getLeft() >= (r5.listView.getPaddingLeft() + AndroidUtilities.dp(24.0f))) goto L7;
            RecyclerListView r4 = r5.listView;
            r4.smoothScrollBy(-((r4.getPaddingLeft() + AndroidUtilities.dp(48.0f)) - r6.getLeft()), 0);
            return;
        L7:
            if ((r6.getLeft() + r6.getWidth()) <= ((r5.listView.getMeasuredWidth() - r5.listView.getPaddingRight()) - AndroidUtilities.dp(24.0f))) goto L10;
            r5.listView.smoothScrollBy((r6.getLeft() + r6.getWidth()) - ((r5.listView.getMeasuredWidth() - r5.listView.getPaddingRight()) - AndroidUtilities.dp(48.0f)), 0);
            return;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$2(Integer r2) {
            ChannelColorActivity r0 = this.this$0;
            r0.selectedProfileColor = r2.intValue();
            ChannelColorActivity r22 = this.this$0;
            if ((r22.selectedStatusEmoji instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L5;
            r22.selectedStatusEmoji = null;
        L5:
            r22.updateButton(true);
            this.this$0.updateProfilePreview(true);
            this.this$0.updateColors(true);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.this$0.rowsCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            ChannelColorActivity r1 = this.this$0;
            if (r2 != r1.messagesPreviewRow) goto L7;
            return 0;
        L7:
            if (r2 != r1.wallpaperThemesRow) goto L11;
            return 2;
        L11:
            if (r2 != r1.profilePreviewRow) goto L15;
            return 1;
        L15:
            if (r2 != r1.replyColorListRow) goto L19;
            return 3;
        L19:
            if (r2 != r1.profileColorGridRow) goto L23;
            return 4;
        L23:
            if (r2 != r1.replyEmojiRow) goto L25;
            return 6;
        L25:
            if (r2 != r1.profileEmojiRow) goto L27;
            return 6;
        L27:
            if (r2 != r1.statusEmojiRow) goto L29;
            return 6;
        L29:
            if (r2 != r1.packEmojiRow) goto L31;
            return 6;
        L31:
            if (r2 != r1.packStickerRow) goto L34;
            return 6;
        L34:
            if (r2 != r1.wallpaperRow) goto L36;
            return 5;
        L36:
            if (r2 == r1.removeProfileColorRow) goto L44;
            return 7;
        L44:
            return 5;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            int r0 = r1.getItemViewType();
            if (r0 != 5) goto L5;
            return true;
        L5:
            if (r0 == 6) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
            int r0 = r8.getItemViewType();
            boolean r2 = true;
            if (r0 != 1) goto L5;
            ProfilePreview r82 = (ProfilePreview) r8.itemView;
            r82.backgroundView.setColor(ChannelColorActivity.access$1700(this.this$0), this.this$0.selectedProfileColor, false);
            r82.profileView.setColor(this.this$0.selectedProfileColor, false);
            r82.profileView.setEmoji(this.this$0.selectedProfileEmoji, false, false);
            r82.profileView.setForum(this.this$0.isForum());
            r82.profileView.setStatusEmoji(DialogObject.getEmojiStatusDocumentId(this.this$0.selectedStatusEmoji), false, false);
            r82.profileView.overrideAvatarColor(this.this$0.selectedReplyColor);
            return;
        L5:
            if (r0 != 3) goto L7;
            ((PeerColorPicker) r8.itemView).setSelected(this.this$0.selectedReplyColor, false);
            return;
        L7:
            if (r0 != 4) goto L9;
            ((PeerColorActivity.PeerColorGrid) r8.itemView).setSelected(this.this$0.selectedProfileColor, false);
            return;
        L9:
            if (r0 != 5) goto L11;
            TextCell r83 = (TextCell) r8.itemView;
            ChannelColorActivity r02 = this.this$0;
            if (r9 != r02.removeProfileColorRow) goto L103;
            r83.setText(LocaleController.getString(R.string.ChannelProfileColorReset), false);
            return;
        L103:
            r83.setText(LocaleController.getString(r02.getWallpaperStrRes()), false);
            ChannelColorActivity r92 = this.this$0;
            if (r92.currentLevel >= r92.getCustomWallpaperLevelMin()) goto L107;
            r83.setLockLevel(false, this.this$0.getCustomWallpaperLevelMin());
            return;
        L107:
            r83.setLockLevel(false, 0);
            return;
        L11:
            if (r0 != 6) goto L13;
            EmojiCell r84 = (EmojiCell) r8.itemView;
            r84.setDivider(false);
            ChannelColorActivity r03 = this.this$0;
            if (r9 != r03.replyEmojiRow) goto L56;
            r84.setAdaptiveEmojiColor(ChannelColorActivity.access$1300(r03), this.this$0.selectedReplyColor, true);
            r84.setText(LocaleController.getString(R.string.ChannelReplyLogo));
            ChannelColorActivity r93 = this.this$0;
            if (r93.currentLevel >= r93.getMessagesController().channelBgIconLevelMin) goto L52;
            r84.setLockLevel(this.this$0.getMessagesController().channelBgIconLevelMin);
        L53:
            r84.setEmoji(this.this$0.selectedReplyEmoji, false, false);
            return;
        L52:
            r84.setLockLevel(0);
            goto L53
        L56:
            if (r9 != r03.profileEmojiRow) goto L68;
            r84.setAdaptiveEmojiColor(ChannelColorActivity.access$1400(r03), this.this$0.selectedProfileColor, false);
            r84.setText(LocaleController.getString(R.string.ChannelProfileLogo));
            if (this.this$0.removeProfileColorRow >= 0) goto L61;
            r2 = false;
        L61:
            r84.setDivider(r2);
            ChannelColorActivity r94 = this.this$0;
            if (r94.currentLevel >= r94.getProfileIconLevelMin()) goto L64;
            r84.setLockLevel(this.this$0.getProfileIconLevelMin());
        L65:
            r84.setEmoji(this.this$0.selectedProfileEmoji, false, false);
            return;
        L64:
            r84.setLockLevel(0);
            goto L65
        L68:
            if (r9 != r03.statusEmojiRow) goto L76;
            r84.setAdaptiveEmojiColor(ChannelColorActivity.access$1500(r03), this.this$0.selectedProfileColor, false);
            r84.setText(LocaleController.getString(this.this$0.getEmojiStatusStrRes()));
            ChannelColorActivity r95 = this.this$0;
            if (r95.currentLevel >= r95.getEmojiStatusLevelMin()) goto L72;
            r84.setLockLevel(this.this$0.getEmojiStatusLevelMin());
        L73:
            r84.setEmoji(DialogObject.getEmojiStatusDocumentId(this.this$0.selectedStatusEmoji), DialogObject.isEmojiStatusCollectible(this.this$0.selectedStatusEmoji), false);
            return;
        L72:
            r84.setLockLevel(0);
            goto L73
        L76:
            if (r9 != r03.packEmojiRow) goto L90;
            r84.setAdaptiveEmojiColor(ChannelColorActivity.access$1600(r03), this.this$0.selectedProfileColor, false);
            r84.setText(LocaleController.getString(this.this$0.getEmojiPackStrRes()));
            ChannelColorActivity r96 = this.this$0;
            if (r96.currentLevel >= r96.getEmojiStickersLevelMin()) goto L80;
            r84.setLockLevel(this.this$0.getEmojiStickersLevelMin());
        L81:
            TLRPC.ChatFull r97 = this.this$0.getMessagesController().getChatFull(-this.this$0.dialogId);
            if (r97 == null) goto L87;
            TLRPC.StickerSet r98 = r97.emojiset;
            if (r98 == null) goto L87;
            r84.setEmoji(ChannelColorActivity.m7296$$Nest$mgetEmojiSetThumbId(this.this$0, r98), false, false);
            return;
        L87:
            r84.setEmoji(0, false, false);
            return;
        L80:
            r84.setLockLevel(0);
            goto L81
        L90:
            if (r9 != r03.packStickerRow) goto L116;
            r84.setText(LocaleController.getString(r03.getStickerPackStrRes()));
            r84.setLockLevel(0);
            TLRPC.ChatFull r99 = this.this$0.getMessagesController().getChatFull(-this.this$0.dialogId);
            if (r99 == null) goto L97;
            TLRPC.StickerSet r910 = r99.stickerset;
            if (r910 == null) goto L97;
            r84.setEmoji(ChannelColorActivity.m7295$$Nest$mgetEmojiSetThumb(this.this$0, r910), false, false);
            return;
        L97:
            r84.setEmoji(0, false, false);
            return;
        L116:
            return;
        L13:
            if (r0 != 7) goto L117;
            TextInfoPrivacyCell r85 = (TextInfoPrivacyCell) r8.itemView;
            r85.setFixedSize(0);
            ChannelColorActivity r7 = this.this$0;
            if (r9 != r7.emptyRow) goto L20;
            r85.setFixedSize(12);
            r85.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L20:
            if (r9 != r7.replyHintRow) goto L24;
            r85.setText(LocaleController.getString(R.string.ChannelReplyInfo));
            return;
        L24:
            if (r9 != r7.wallpaperHintRow) goto L28;
            r85.setText(LocaleController.getString(r7.getWallpaper2InfoStrRes()));
            return;
        L28:
            if (r9 != r7.profileHintRow) goto L32;
            r85.setText(LocaleController.getString(r7.getProfileInfoStrRes()));
            return;
        L32:
            if (r9 != r7.statusHintRow) goto L36;
            r85.setText(LocaleController.getString(r7.getEmojiStatusInfoStrRes()));
            return;
        L36:
            if (r9 != r7.packEmojiHintRow) goto L40;
            r85.setText(LocaleController.getString(r7.getEmojiPackInfoStrRes()));
            return;
        L40:
            if (r9 != r7.packStickerHintRow) goto L44;
            r85.setText(LocaleController.getString(r7.getStickerPackInfoStrRes()));
            return;
        L44:
            if (r9 != r7.removeProfileColorShadowRow) goto L115;
            r85.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            r85.setFixedSize(12);
            return;
        L115:
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r9, int r10) {
            if (r10 != 0) goto L6;
            Context r2 = this.this$0.getContext();
            INavigationLayout r3 = ChannelColorActivity.access$000(this.this$0);
            int r4 = this.this$0.getMessagePreviewType();
            ChannelColorActivity r102 = this.this$0;
            ThemePreviewMessagesCell r1 = new ThemePreviewMessagesCell(r2, r3, r4, r102.dialogId, ChannelColorActivity.access$100(r102));
            r1.customAnimation = true;
            r1.setImportantForAccessibility(4);
            ChannelColorActivity r92 = this.this$0;
            r1.fragment = r92;
            Drawable r103 = r92.backgroundDrawable;
            int r0 = ChannelColorActivity.access$200(r92);
            ChannelColorActivity r8 = this.this$0;
            Drawable r82 = PreviewView.getBackgroundDrawable(r103, r0, r8.selectedWallpaper, ChannelColorActivity.m7282$$Nest$fgetisDark(r8));
            r92.backgroundDrawable = r82;
            r1.setOverrideBackground(r82);
            FrameLayout r12 = r1;
        L34:
            return new RecyclerListView.Holder(r12);
        L6:
            if (r10 != 2) goto L9;
            ThemeChooser r13 = new ThemeChooser(this.this$0.getContext(), false, ChannelColorActivity.access$300(this.this$0), ChannelColorActivity.access$400(this.this$0));
            r13.setWithRemovedStub(true);
            r13.setSelectedEmoticon(ChannelColorActivity.m7297$$Nest$mgetThemeChooserEmoticon(this.this$0), false);
            r13.setGalleryWallpaper(this.this$0.galleryWallpaper);
            r13.setOnEmoticonSelected(new ChannelColorActivity$Adapter$$ExternalSyntheticLambda0(this));
            r12 = r13;
            goto L34
        L9:
            if (r10 != 5) goto L12;
            r12 = new TextCell(this.this$0.getContext(), this.this$0.getResourceProvider());
            goto L34
        L12:
            if (r10 != 6) goto L15;
            r12 = new EmojiCell(this.this$0.getContext(), ChannelColorActivity.access$500(this.this$0));
            goto L34
        L15:
            if (r10 != 3) goto L17;
            PeerColorPicker r14 = new PeerColorPicker(this.this$0.getContext(), ChannelColorActivity.access$600(this.this$0), ChannelColorActivity.access$700(this.this$0));
            r14.listView.setOnItemClickListener(new ChannelColorActivity$Adapter$$ExternalSyntheticLambda1(this, r14));
            r12 = r14;
            goto L34
        L17:
            if (r10 != 4) goto L19;
            PeerColorActivity.PeerColorGrid r15 = new PeerColorActivity.PeerColorGrid(this.this$0.getContext(), 0, ChannelColorActivity.access$800(this.this$0), ChannelColorActivity.access$900(this.this$0));
            r15.setDivider(false);
            r15.setOnColorClick(new ChannelColorActivity$Adapter$$ExternalSyntheticLambda2(this));
            r12 = r15;
            goto L34
        L19:
            if (r10 != 1) goto L24;
            ChannelColorActivity r93 = this.this$0;
            FrameLayout r16 = new ProfilePreview(r93, r93.getContext());
            r12 = r16;
            if (this.this$0.isGroup == false) goto L34;
            r16.setTag(-33024);
            r12 = r16;
            goto L34
        L24:
            if (r10 != 8) goto L27;
            r12 = new HeaderCell(this.this$0.getContext(), ChannelColorActivity.access$1000(this.this$0));
            goto L34
        L27:
            if (r10 != 9) goto L30;
            r12 = new PeerColorActivity.GiftCell(this.this$0.getContext(), false, ChannelColorActivity.access$1100(this.this$0));
            goto L34
        L30:
            if (r10 != 10) goto L32;
            FlickerLoadingView r17 = new FlickerLoadingView(this.this$0.getContext(), ChannelColorActivity.access$1200(this.this$0));
            r17.setIsSingleCell(true);
            r17.setViewType(35);
            r12 = r17;
            goto L34
        L32:
            r12 = new TextInfoPrivacyCell(this.this$0.getContext());
            goto L34
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r7) {
            View r0 = r7.itemView;
            if ((r0 instanceof ProfilePreview) == false) goto L10;
            ProfilePreview r02 = (ProfilePreview) r0;
            ChannelColorActivity r1 = this.this$0;
            TLRPC.EmojiStatus r2 = r1.selectedStatusEmoji;
            if ((r2 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L7;
            r02.profileView.setColor(MessagesController.PeerColor.fromCollectible(r2), false);
            r02.profileView.setEmoji(((TLRPC.TL_emojiStatusCollectible) this.this$0.selectedStatusEmoji).pattern_document_id, true, false);
        L8:
            r02.profileView.setStatusEmoji(DialogObject.getEmojiStatusDocumentId(this.this$0.selectedStatusEmoji), DialogObject.isEmojiStatusCollectible(this.this$0.selectedStatusEmoji), false);
            r02.profileView.setForum(this.this$0.isForum());
            r02.profileView.overrideAvatarColor(this.this$0.selectedReplyColor);
        L13:
            super.onViewAttachedToWindow(r7);
            return;
        L7:
            r02.profileView.setColor(r1.selectedProfileColor, false);
            r02.profileView.setEmoji(this.this$0.selectedProfileEmoji, false, false);
            goto L8
        L10:
            if ((r0 instanceof ThemePreviewMessagesCell) == false) goto L12;
            ((ThemePreviewMessagesCell) r0).setOverrideBackground(this.this$0.backgroundDrawable);
            goto L13
        L12:
            ChannelColorActivity.m7299$$Nest$mupdateColors(this.this$0, r0);
            goto L13
        }
    }

    public static class EmojiCell extends FrameLayout {
        private int color;
        private AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable imageDrawable;
        private boolean needDivider;
        private Text offText;
        private Theme.ResourcesProvider resourcesProvider;
        private SimpleTextView textView;

        /* renamed from: -$$Nest$fgetimageDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable m7300$$Nest$fgetimageDrawable(EmojiCell r0) {
            return r0.imageDrawable;
        }

        public EmojiCell(Context r9, Theme.ResourcesProvider r10) {
            super(r9);
            this.needDivider = false;
            this.resourcesProvider = r10;
            SimpleTextView r1 = new SimpleTextView(r9);
            this.textView = r1;
            r1.setTextSize(16);
            this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r10));
            addView(this.textView, LayoutHelper.createFrame(-1, -2.0f, 23, 23.0f, 0.0f, 48.0f, 0.0f));
            this.imageDrawable = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this, false, AndroidUtilities.dp(24.0f), 13);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r15) {
            super.dispatchDraw(r15);
            updateImageBounds();
            this.imageDrawable.setColor(Integer.valueOf(this.color));
            Text r2 = this.offText;
            if (r2 == null) goto L5;
            Canvas r3 = r15;
            r2.draw(r3, (getMeasuredWidth() - this.offText.getWidth()) - AndroidUtilities.dp(19.0f), getMeasuredHeight() / 2.0f, Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4, this.resourcesProvider), 1.0f);
        L7:
            if (this.needDivider == false) goto L25;
            Theme.ResourcesProvider r152 = this.resourcesProvider;
            if (r152 == null) goto L12;
            Paint r153 = r152.getPaint("paintDivider");
        L11:
            Paint r13 = r153;
            if (r13 != null) goto L15;
            return;
        L15:
            if (LocaleController.isRTL == false) goto L18;
            float r154 = 0.0f;
        L17:
            float r9 = r154;
            float r10 = getMeasuredHeight() - 1;
            int r155 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L22;
            int r0 = AndroidUtilities.dp(23.0f);
        L23:
            r3.drawLine(r9, r10, r155 - r0, getMeasuredHeight() - 1, r13);
            return;
        L22:
            r0 = 0;
            goto L23
        L18:
            r154 = AndroidUtilities.dp(23.0f);
            goto L17
        L12:
            r153 = Theme.dividerPaint;
            goto L11
        L25:
            return;
        L5:
            r3 = r15;
            this.imageDrawable.draw(r3);
            goto L7
        }

        public int getColor() {
            return this.color;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.imageDrawable.attach();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.imageDrawable.detach();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
        }

        public void setAdaptiveEmojiColor(int r3, int r4, boolean r5) {
            MessagesController.PeerColor r0 = null;
            if (r4 >= 0) goto L6;
            setAdaptiveEmojiColor(null);
        L16:
            invalidate();
            return;
        L6:
            if (r4 >= 7) goto L8;
            this.color = Theme.getColor(Theme.keys_avatar_nameInMessage[r4], this.resourcesProvider);
            goto L16
        L8:
            MessagesController r32 = MessagesController.getInstance(r3);
            if (r5 == false) goto L11;
            MessagesController.PeerColors r33 = r32.peerColors;
        L12:
            if (r33 == null) goto L15;
            r0 = r33.getColor(r4);
        L15:
            setAdaptiveEmojiColor(r0);
            goto L16
        L11:
            r33 = r32.profilePeerColors;
            goto L12
        }

        public void setAdaptiveEmojiColor(MessagesController.PeerColor r3) {
            if (r3 != null) goto L13;
            int r32 = Theme.key_actionBarDefault;
            if (AndroidUtilities.computePerceivedBrightness(Theme.getColor(r32, this.resourcesProvider)) <= 0.8f) goto L8;
            this.color = Theme.getColor(Theme.key_windowBackgroundWhiteBlueText, this.resourcesProvider);
            return;
        L8:
            if (AndroidUtilities.computePerceivedBrightness(Theme.getColor(r32, this.resourcesProvider)) >= 0.2f) goto L11;
            this.color = Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultTitle, this.resourcesProvider), 0.5f);
            return;
        L11:
            this.color = Theme.blendOver(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider), Theme.multAlpha(PeerColorActivity.adaptProfileEmojiColor(Theme.getColor(r32, this.resourcesProvider)), 0.7f));
            return;
        L13:
            this.color = r3.getColor(0, this.resourcesProvider);
        }

        public void setDivider(boolean r1) {
            this.needDivider = r1;
            setWillNotDraw(!r1);
        }

        public void setEmoji(long r4, boolean r6, boolean r7) {
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r1 = this.imageDrawable;
            if (r4 != 0) goto L7;
            r1.set(null, r7);
            if (this.offText != null) goto L8;
            this.offText = new Text(LocaleController.getString(R.string.ChannelReplyIconOff), 16.0f);
        L8:
            this.imageDrawable.setParticles(r6, r7);
            return;
        L7:
            r1.set(r4, r7);
            this.offText = null;
            goto L8
        }

        public void setEmoji(TLRPC.Document r3, boolean r4, boolean r5) {
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.imageDrawable;
            if (r3 != null) goto L7;
            r0.set(null, r5);
            if (this.offText != null) goto L8;
            this.offText = new Text(LocaleController.getString(R.string.ChannelReplyIconOff), 16.0f);
        L8:
            this.imageDrawable.setParticles(r4, r5);
            return;
        L7:
            r0.set(r3, r5);
            this.offText = null;
            goto L8
        }

        public void setLockLevel(int r5) {
            SimpleTextView r0 = this.textView;
            if (r5 > 0) goto L6;
            r0.setRightDrawable(null);
            return;
        L6:
            r0.setRightDrawable(new PeerColorActivity.LevelLock(getContext(), r5, this.resourcesProvider));
            this.textView.setDrawablePadding(AndroidUtilities.dp(6.0f));
        }

        public void setText(CharSequence r1) {
            this.textView.setText(r1);
        }

        public void updateColors() {
            this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        }

        public void updateImageBounds() {
            this.imageDrawable.setBounds((getWidth() - this.imageDrawable.getIntrinsicWidth()) - AndroidUtilities.dp(21.0f), (getHeight() - this.imageDrawable.getIntrinsicHeight()) / 2, getWidth() - AndroidUtilities.dp(21.0f), (getHeight() + this.imageDrawable.getIntrinsicHeight()) / 2);
        }
    }

    public static class PeerColorPicker extends FrameLayout {
        public final RecyclerListView.SelectionAdapter adapter;
        private final int currentAccount;
        public final LinearLayoutManager layoutManager;
        public final RecyclerListView listView;
        private final Theme.ResourcesProvider resourcesProvider;
        private int selectedPosition;

        /* renamed from: org.telegram.ui.ChannelColorActivity$PeerColorPicker$1, reason: invalid class name */
        public class AnonymousClass1 extends RecyclerListView {
            final /* synthetic */ PeerColorPicker this$0;

            public AnonymousClass1(PeerColorPicker r1, Context r2, Theme.ResourcesProvider r3) {
                this.this$0 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.Components.RecyclerListView
            public Integer getSelectorColor(int r1) {
                return 0;
            }
        }

        /* renamed from: org.telegram.ui.ChannelColorActivity$PeerColorPicker$2, reason: invalid class name */
        public class AnonymousClass2 extends RecyclerListView.SelectionAdapter {
            final /* synthetic */ PeerColorPicker this$0;
            final /* synthetic */ Context val$context;
            final /* synthetic */ int val$currentAccount;
            final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

            public AnonymousClass2(PeerColorPicker r1, Context r2, Theme.ResourcesProvider r3, int r4) {
                this.this$0 = r1;
                this.val$context = r2;
                this.val$resourcesProvider = r3;
                this.val$currentAccount = r4;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                MessagesController.PeerColors r0 = MessagesController.getInstance(this.val$currentAccount).peerColors;
                if (r0 != null) goto L7;
                return 0;
            L7:
                return r0.colors.size();
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r1) {
                return true;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r3, int r4) {
                ColorCell r32 = (ColorCell) r3.itemView;
                r32.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.val$resourcesProvider));
                if (r4 != PeerColorPicker.m7302$$Nest$fgetselectedPosition(this.this$0)) goto L5;
                boolean r0 = true;
            L6:
                r32.setSelected(r0, false);
                MessagesController.PeerColors r2 = MessagesController.getInstance(this.val$currentAccount).peerColors;
                if (r2 == null) goto L13;
                if (r4 >= 0) goto L10;
                return;
            L10:
                if (r4 >= r2.colors.size()) goto L15;
                r32.set(r2.colors.get(r4));
                return;
            L15:
                return;
            L13:
                return;
            L5:
                r0 = false;
                goto L6
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
                return new RecyclerListView.Holder(new ColorCell(this.this$0, this.val$context));
            }
        }

        public class ColorCell extends View {
            private final Paint backgroundPaint;
            private final ButtonBounce bounce;
            private final Path circlePath;
            private final Path color2Path;
            private boolean hasColor2;
            private boolean hasColor3;
            private final Paint paint1;
            private final Paint paint2;
            private final Paint paint3;
            private boolean selected;
            private final AnimatedFloat selectedT;
            final /* synthetic */ PeerColorPicker this$0;

            public ColorCell(PeerColorPicker r8, Context r9) {
                this.this$0 = r8;
                super(r9);
                Paint r82 = new Paint(1);
                this.backgroundPaint = r82;
                this.paint1 = new Paint(1);
                this.paint2 = new Paint(1);
                this.paint3 = new Paint(1);
                this.circlePath = new Path();
                this.color2Path = new Path();
                this.bounce = new ButtonBounce(this);
                this.selectedT = new AnimatedFloat(this, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
                r82.setStyle(Paint.Style.STROKE);
            }

            @Override // android.view.View
            public void dispatchDraw(Canvas r9) {
                r9.save();
                float r0 = this.bounce.getScale(0.05f);
                r9.scale(r0, r0, getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f);
                r9.save();
                r9.clipPath(this.circlePath);
                r9.drawPaint(this.paint1);
                if (this.hasColor2 == false) goto L5;
                r9.drawPath(this.color2Path, this.paint2);
            L5:
                r9.restore();
                if (this.hasColor3 == false) goto L8;
                r9.save();
                RectF r02 = AndroidUtilities.rectTmp;
                r02.set((getMeasuredWidth() - AndroidUtilities.dp(12.4f)) / 2.0f, (getMeasuredHeight() - AndroidUtilities.dp(12.4f)) / 2.0f, (getMeasuredWidth() + AndroidUtilities.dp(12.4f)) / 2.0f, (getMeasuredHeight() + AndroidUtilities.dp(12.4f)) / 2.0f);
                r9.rotate(45.0f, getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f);
                r9.drawRoundRect(r02, AndroidUtilities.dp(2.33f), AndroidUtilities.dp(2.33f), this.paint3);
                r9.restore();
            L8:
                float r03 = this.selectedT.set(this.selected);
                if (r03 <= 0.0f) goto L11;
                this.backgroundPaint.setStrokeWidth(AndroidUtilities.dpf2(2.0f));
                r9.drawCircle(getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f, AndroidUtilities.lerp(AndroidUtilities.dp(20.0f) + (this.backgroundPaint.getStrokeWidth() * 0.5f), AndroidUtilities.dp(20.0f) - (this.backgroundPaint.getStrokeWidth() * 2.0f), r03), this.backgroundPaint);
            L11:
                r9.restore();
            }

            @Override // android.view.View
            public void onMeasure(int r4, int r5) {
                setMeasuredDimension(AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f));
                this.circlePath.rewind();
                this.circlePath.addCircle(getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f, AndroidUtilities.dp(20.0f), Path.Direction.CW);
                this.color2Path.rewind();
                this.color2Path.moveTo(getMeasuredWidth(), 0.0f);
                this.color2Path.lineTo(getMeasuredWidth(), getMeasuredHeight());
                this.color2Path.lineTo(0.0f, getMeasuredHeight());
                this.color2Path.close();
            }

            public void set(MessagesController.PeerColor r5) {
                if (PeerColorPicker.m7301$$Nest$fgetresourcesProvider(this.this$0) == null) goto L5;
                boolean r0 = PeerColorPicker.m7301$$Nest$fgetresourcesProvider(this.this$0).isDark();
            L7:
                if (r0 == true) goto L9;
            L13:
                this.paint1.setColor(r5.getColor(0, PeerColorPicker.m7301$$Nest$fgetresourcesProvider(this.this$0)));
                this.paint2.setColor(r5.getColor(1, PeerColorPicker.m7301$$Nest$fgetresourcesProvider(this.this$0)));
            L14:
                this.paint3.setColor(r5.getColor(2, PeerColorPicker.m7301$$Nest$fgetresourcesProvider(this.this$0)));
                this.hasColor2 = r5.hasColor2();
                this.hasColor3 = r5.hasColor3();
                return;
            L9:
                if (r5.hasColor2() == false) goto L13;
                if (r5.hasColor3() == true) goto L13;
                this.paint1.setColor(r5.getColor(1, PeerColorPicker.m7301$$Nest$fgetresourcesProvider(this.this$0)));
                this.paint2.setColor(r5.getColor(0, PeerColorPicker.m7301$$Nest$fgetresourcesProvider(this.this$0)));
                goto L14
            L5:
                r0 = Theme.isCurrentThemeDark();
                goto L7
            }

            @Override // android.view.View
            public void setBackgroundColor(int r1) {
                this.backgroundPaint.setColor(r1);
            }

            @Override // android.view.View
            public void setPressed(boolean r1) {
                super.setPressed(r1);
                this.bounce.setPressed(r1);
            }

            public void setSelected(boolean r2, boolean r3) {
                this.selected = r2;
                if (r3 == true) goto L5;
                this.selectedT.set(r2, true);
            L5:
                invalidate();
            }
        }

        public static /* synthetic */ void $r8$lambda$OdAMGBzGM92Pp3sE4kiLELkPN2Q(PeerColorPicker r0, boolean r1, View r2) {
            r0.lambda$setSelectedPosition$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$Wy4Z2VO8Q7bVlGLbZPl2_5cIkNs(PeerColorPicker r0, MessagesController.PeerColors r1, View r2) {
            r0.lambda$updateColors$1(r1, r2);
        }

        /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m7301$$Nest$fgetresourcesProvider(PeerColorPicker r0) {
            return r0.resourcesProvider;
        }

        /* renamed from: -$$Nest$fgetselectedPosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m7302$$Nest$fgetselectedPosition(PeerColorPicker r0) {
            return r0.selectedPosition;
        }

        public PeerColorPicker(Context r6, int r7, Theme.ResourcesProvider r8) {
            super(r6);
            this.currentAccount = r7;
            this.resourcesProvider = r8;
            AnonymousClass1 r0 = new AnonymousClass1(this, r6, r8);
            this.listView = r0;
            r0.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(6.0f), 0);
            r0.setClipToPadding(false);
            AnonymousClass2 r1 = new AnonymousClass2(this, r6, r8, r7);
            this.adapter = r1;
            r0.setAdapter(r1);
            LinearLayoutManager r72 = new LinearLayoutManager(r6);
            this.layoutManager = r72;
            r72.setOrientation(0);
            r0.setLayoutManager(r72);
            addView(r0, LayoutHelper.createFrame(-1, -1.0f));
        }

        private /* synthetic */ void lambda$setSelectedPosition$0(boolean r3, View r4) {
            ColorCell r0 = (ColorCell) r4;
            if (this.listView.getChildAdapterPosition(r4) != this.selectedPosition) goto L5;
            boolean r2 = true;
        L6:
            r0.setSelected(r2, r3);
            return;
        L5:
            r2 = false;
            goto L6
        }

        private /* synthetic */ void lambda$updateColors$1(MessagesController.PeerColors r4, View r5) {
            if ((r5 instanceof ColorCell) == false) goto L11;
            ColorCell r0 = (ColorCell) r5;
            r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider));
            int r3 = this.listView.getChildAdapterPosition(r5);
            if (r4 == null) goto L12;
            if (r3 >= 0) goto L8;
            return;
        L8:
            if (r3 >= r4.colors.size()) goto L14;
            r0.set(r4.colors.get(r3));
            return;
        L14:
            return;
        L12:
            return;
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r4) {
            if (getParent() == null) goto L12;
            ViewParent r0 = getParent();
            boolean r2 = true;
            if (canScrollHorizontally(-1) == false) goto L7;
        L10:
            r0.requestDisallowInterceptTouchEvent(r2);
            goto L12
        L7:
            if (canScrollHorizontally(1) == true) goto L10;
            r2 = false;
        L12:
            return super.onInterceptTouchEvent(r4);
        }

        public void setSelected(int r1, boolean r2) {
            setSelectedPosition(toPosition(r1), r2);
        }

        public void setSelectedPosition(int r4, boolean r5) {
            if (r4 == this.selectedPosition) goto L9;
            this.selectedPosition = r4;
            if (r5 == true) goto L7;
            this.layoutManager.scrollToPositionWithOffset(r4, (AndroidUtilities.displaySize.x - AndroidUtilities.dp(56.0f)) / 2);
        L7:
            AndroidUtilities.forEachViews(this.listView, new ChannelColorActivity$PeerColorPicker$$ExternalSyntheticLambda1(this, r5));
            return;
        }

        public int toColorId(int r2) {
            MessagesController.PeerColors r1 = MessagesController.getInstance(this.currentAccount).peerColors;
            if (r1 == null) goto L10;
            if (r2 >= 0) goto L6;
            return 0;
        L6:
            if (r2 < r1.colors.size()) goto L9;
            return 0;
        L9:
            return r1.colors.get(r2).id;
        L10:
            return 0;
        }

        public int toPosition(int r4) {
            MessagesController.PeerColors r3 = MessagesController.getInstance(this.currentAccount).peerColors;
            if (r3 != null) goto L5;
            return 0;
        L5:
            int r1 = 0;
        L7:
            if (r1 >= r3.colors.size()) goto L12;
            if (r3.colors.get(r1).id == r4) goto L10;
            r1 = r1 + 1;
            goto L7
        L10:
            return r1;
        L12:
            return 0;
        }

        public void updateColors() {
            MessagesController.PeerColors r0 = MessagesController.getInstance(this.currentAccount).peerColors;
            AndroidUtilities.forEachViews(this.listView, new ChannelColorActivity$PeerColorPicker$$ExternalSyntheticLambda0(this, r0));
        }
    }

    public class ProfilePreview extends FrameLayout implements Theme.Colorable {
        public final PeerColorActivity.ColoredActionBar backgroundView;
        public LinearLayout infoLayout;
        public final PeerColorActivity.ProfilePreview profileView;
        public TextView textInfo1;
        public TextView textInfo2;
        final /* synthetic */ ChannelColorActivity this$0;
        public SimpleTextView title;

        /* renamed from: org.telegram.ui.ChannelColorActivity$ProfilePreview$1, reason: invalid class name */
        public class AnonymousClass1 extends PeerColorActivity.ProfilePreview {
            final /* synthetic */ ProfilePreview this$1;
            final /* synthetic */ ChannelColorActivity val$this$0;

            public AnonymousClass1(ProfilePreview r1, Context r2, int r3, long r4, Theme.ResourcesProvider r6, ChannelColorActivity r7) {
                this.this$1 = r1;
                this.val$this$0 = r7;
                super(r2, r3, r4, r6);
            }

            @Override // org.telegram.ui.PeerColorActivity.ProfilePreview
            public void setColor(int r1, boolean r2) {
                super.setColor(r1, r2);
                ProfilePreview r0 = this.this$1;
                TextView r12 = r0.textInfo1;
                if (r12 == null) goto L6;
                r12.setTextColor(r0.profileView.subtitleView.getTextColor());
                return;
            }
        }

        public ProfilePreview(ChannelColorActivity r19, Context r20) {
            this.this$0 = r19;
            super(r20);
            PeerColorActivity.ColoredActionBar r0 = new PeerColorActivity.ColoredActionBar(getContext(), ChannelColorActivity.access$1800(r19));
            this.backgroundView = r0;
            r0.setProgressToGradient(1.0f);
            r0.ignoreMeasure = true;
            addView(r0, LayoutHelper.createFrame(-1, -1, 119));
            AnonymousClass1 r02 = new AnonymousClass1(this, getContext(), ChannelColorActivity.access$1900(r19), r19.dialogId, ChannelColorActivity.access$2000(r19), r19);
            this.profileView = r02;
            boolean r2 = r19.isGroup;
            if (r2 == false) goto L6;
            float r3 = 230.0f;
        L5:
            float r12 = r3;
            if (r2 == false) goto L10;
            float r17 = 24.0f;
        L11:
            addView(r02, LayoutHelper.createFrame(-1, r12, 80, 0.0f, 0.0f, 0.0f, r17));
            if (r19.needBoostInfoSection() == false) goto L25;
            SimpleTextView r22 = new SimpleTextView(getContext());
            this.title = r22;
            r22.setGravity(19);
            SimpleTextView r23 = this.title;
            int r4 = Theme.key_actionBarDefaultTitle;
            r23.setTextColor(r19.getThemedColor(r4));
            this.title.setTypeface(AndroidUtilities.bold());
            this.title.setText(LocaleController.getString(R.string.ChangeChannelNameColor2));
            this.title.setAlpha(0.0f);
            setTitleSize();
            addView(this.title, LayoutHelper.createFrame(-1, -2.0f, 80, 72.0f, 0.0f, 0.0f, 16.0f));
            LinearLayout r24 = new LinearLayout(r20);
            this.infoLayout = r24;
            r24.setOrientation(0);
            this.infoLayout.setBackground(Theme.createSelectorWithBackgroundDrawable(Theme.multAlpha(-16777216, 0.065f), -16777216));
            this.infoLayout.setGravity(17);
            this.infoLayout.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
            TextView r25 = new TextView(r20);
            this.textInfo1 = r25;
            r25.setTextSize(1, 12.0f);
            this.textInfo1.setTextColor(r02.subtitleView.getTextColor());
            TextView r03 = new TextView(r20);
            this.textInfo2 = r03;
            r03.setTextSize(1, 12.0f);
            TextView r04 = this.textInfo2;
            if (r19.isGroup == true) goto L16;
        L18:
            int r26 = -1;
        L19:
            r04.setTextColor(r26);
            TextView r05 = this.textInfo1;
            TL_stories.TL_premium_boostsStatus r27 = r19.boostsStatus;
            if (r27 == null) goto L22;
            int r28 = r27.boosts;
        L23:
            r05.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGroupBoostCount", r28, new Object[0])));
            this.textInfo2.setText(LocaleController.getString(R.string.BoostingGroupBoostWhatAreBoosts));
            this.infoLayout.addView(this.textInfo1);
            this.infoLayout.addView(this.textInfo2, LayoutHelper.createLinear(-2, -2, 3.0f, 0.0f, 0.0f, 0.0f));
            addView(this.infoLayout, LayoutHelper.createFrame(-1, -2, 80));
            return;
        L22:
            r28 = 0;
            goto L23
        L16:
            if (r19.selectedProfileColor != (-1)) goto L18;
            r26 = r19.getThemedColor(r4);
            goto L19
        L25:
            return;
        L10:
            r17 = 0.0f;
            goto L11
        L6:
            r3 = 190.0f;
            goto L5
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        public void setColor(int r2, boolean r3) {
            this.profileView.setColor(r2, r3);
            this.backgroundView.setColor(ChannelColorActivity.access$2100(this.this$0), r2, r3);
        }

        public void setColor(MessagesController.PeerColor r2, boolean r3) {
            this.profileView.setColor(r2, r3);
            this.backgroundView.setColor(r2, r3);
        }

        public void setEmoji(long r1, boolean r3, boolean r4) {
            this.profileView.setEmoji(r1, r3, r4);
        }

        public void setEmojiStatus(TLRPC.EmojiStatus r3, boolean r4) {
            this.profileView.setStatusEmoji(DialogObject.getEmojiStatusDocumentId(r3), false, r4);
        }

        public void setTitleSize() {
            int r2 = 0;
            if (getResources().getConfiguration().orientation != 2) goto L5;
            boolean r0 = true;
        L6:
            SimpleTextView r1 = this.title;
            if (AndroidUtilities.isTablet() == true) goto L10;
            if (r0 == false) goto L10;
            int r3 = 18;
        L11:
            r1.setTextSize(r3);
            SimpleTextView r4 = this.title;
            if (AndroidUtilities.isTablet() == false) goto L14;
            float r02 = -2.0f;
        L17:
            r4.setTranslationY(AndroidUtilities.dp(r02));
            return;
        L14:
            if (r0 == false) goto L16;
            r2 = 4;
        L16:
            r02 = r2;
        L10:
            r3 = 20;
            goto L11
        L5:
            r0 = false;
            goto L6
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            SimpleTextView r0 = this.title;
            if (r0 == null) goto L17;
            ChannelColorActivity r1 = this.this$0;
            int r3 = -1;
            if (r1.isGroup == true) goto L7;
        L9:
            int r12 = r1.getThemedColor(Theme.key_actionBarDefaultTitle);
        L10:
            r0.setTextColor(r12);
            TextView r02 = this.textInfo2;
            ChannelColorActivity r4 = this.this$0;
            if (r4.isGroup == true) goto L13;
        L15:
            r02.setTextColor(r3);
            return;
        L13:
            if (r4.selectedProfileColor != (-1)) goto L15;
            r3 = r4.getThemedColor(Theme.key_actionBarDefaultTitle);
            goto L15
        L7:
            if (r1.selectedProfileColor == (-1)) goto L9;
            r12 = -1;
            goto L10
        }
    }

    public static class ThemeChooser extends FrameLayout {
        private final RecyclerListView.SelectionAdapter adapter;
        private final int currentAccount;
        private String currentEmoticon;
        private boolean dataLoaded;
        private TLRPC.WallPaper fallbackWallpaper;
        public final List<ChatThemeBottomSheet.ChatThemeItem> items;
        private final RecyclerListView listView;
        private final HashMap<String, Theme.ThemeInfo> loadingThemes;
        private final HashMap<Theme.ThemeInfo, String> loadingWallpapers;
        private Utilities.Callback<String> onEmoticonSelected;
        private FlickerLoadingView progressView;
        private final Theme.ResourcesProvider resourcesProvider;
        private boolean withRemovedStub;

        /* renamed from: org.telegram.ui.ChannelColorActivity$ThemeChooser$1, reason: invalid class name */
        public class AnonymousClass1 extends RecyclerListView {
            final /* synthetic */ ThemeChooser this$0;

            public AnonymousClass1(ThemeChooser r1, Context r2, Theme.ResourcesProvider r3) {
                this.this$0 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.Components.RecyclerListView
            public Integer getSelectorColor(int r1) {
                return 0;
            }
        }

        /* renamed from: org.telegram.ui.ChannelColorActivity$ThemeChooser$2, reason: invalid class name */
        public class AnonymousClass2 extends GridLayoutManager.SpanSizeLookup {
            final /* synthetic */ ThemeChooser this$0;

            public AnonymousClass2(ThemeChooser r1) {
                this.this$0 = r1;
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int r1) {
                return 1;
            }
        }

        /* renamed from: org.telegram.ui.ChannelColorActivity$ThemeChooser$3, reason: invalid class name */
        public class AnonymousClass3 extends RecyclerListView.SelectionAdapter {
            final /* synthetic */ ThemeChooser this$0;
            final /* synthetic */ int val$currentAccount;
            final /* synthetic */ boolean val$grid;
            final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

            /* renamed from: org.telegram.ui.ChannelColorActivity$ThemeChooser$3$1, reason: invalid class name */
            public class AnonymousClass1 extends ThemeSmallPreviewView {
                final /* synthetic */ AnonymousClass3 this$1;

                public AnonymousClass1(AnonymousClass3 r1, Context r2, int r3, Theme.ResourcesProvider r4, int r5) {
                    this.this$1 = r1;
                    super(r2, r3, r4, r5);
                }

                @Override // org.telegram.ui.Components.ThemeSmallPreviewView
                public String noThemeString() {
                    return LocaleController.getString(R.string.ChannelNoWallpaper);
                }

                @Override // org.telegram.ui.Components.ThemeSmallPreviewView
                public int noThemeStringTextSize() {
                    if (this.this$1.val$grid == true) goto L7;
                    return 13;
                L7:
                    return super.noThemeStringTextSize();
                }
            }

            public AnonymousClass3(ThemeChooser r1, int r2, Theme.ResourcesProvider r3, boolean r4) {
                this.this$0 = r1;
                this.val$currentAccount = r2;
                this.val$resourcesProvider = r3;
                this.val$grid = r4;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return this.this$0.items.size();
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r1) {
                return true;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
                ThemeSmallPreviewView r42 = (ThemeSmallPreviewView) r4.itemView;
                Theme.ThemeInfo r0 = this.this$0.items.get(r5).chatTheme.getThemeInfo(this.this$0.items.get(r5).themeIndex);
                if (r0 != null) goto L5;
            L11:
                ChatThemeBottomSheet.ChatThemeItem r52 = this.this$0.items.get(r5);
                r42.setEnabled(true);
                r42.setBackgroundColor(Theme.getColor(Theme.key_dialogBackgroundGray));
                r42.setItem(r52, false);
                r42.setSelected(r52.isSelected, false);
                if (r52.chatTheme.showAsRemovedStub == false) goto L14;
                TLRPC.WallPaper r3 = null;
            L15:
                r42.setFallbackWallpaper(r3);
                return;
            L14:
                r3 = ThemeChooser.m7304$$Nest$fgetfallbackWallpaper(this.this$0);
                goto L15
            L5:
                if (r0.pathToFile == null) goto L11;
                if (r0.previewParsed == true) goto L11;
                if (new File(r0.pathToFile).exists() == false) goto L11;
                ThemeChooser.m7306$$Nest$mparseTheme(this.this$0, r0);
                goto L11
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
                Context r2 = r7.getContext();
                int r3 = this.val$currentAccount;
                Theme.ResourcesProvider r4 = this.val$resourcesProvider;
                if (this.val$grid == false) goto L6;
                int r72 = 4;
            L8:
                return new RecyclerListView.Holder(new AnonymousClass1(this, r2, r3, r4, r72));
            L6:
                r72 = 3;
                goto L8
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onViewAttachedToWindow(RecyclerView.ViewHolder r5) {
                int r0 = r5.getAdapterPosition();
                if (r0 >= 0) goto L5;
                return;
            L5:
                if (r0 >= this.this$0.items.size()) goto L14;
                ChatThemeBottomSheet.ChatThemeItem r02 = this.this$0.items.get(r0);
                ((ThemeSmallPreviewView) r5.itemView).setSelected(r02.isSelected, false);
                ThemeSmallPreviewView r52 = (ThemeSmallPreviewView) r5.itemView;
                if (r02.chatTheme.showAsRemovedStub == false) goto L10;
                TLRPC.WallPaper r4 = null;
            L11:
                r52.setFallbackWallpaper(r4);
                return;
            L10:
                r4 = ThemeChooser.m7304$$Nest$fgetfallbackWallpaper(this.this$0);
                goto L11
            }
        }

        /* renamed from: org.telegram.ui.ChannelColorActivity$ThemeChooser$4, reason: invalid class name */
        public class AnonymousClass4 implements ResultCallback<List<EmojiThemes>> {
            final /* synthetic */ ThemeChooser this$0;
            final /* synthetic */ int val$currentAccount;

            /* renamed from: $r8$lambda$cue-7WAM9EVsqdsBMEkovarT9CU, reason: not valid java name */
            public static /* synthetic */ void m7307$r8$lambda$cue7WAM9EVsqdsBMEkovarT9CU(AnonymousClass4 r0, List r1) {
                r0.lambda$onComplete$0(r1);
            }

            public AnonymousClass4(ThemeChooser r1, int r2) {
                this.this$0 = r1;
                this.val$currentAccount = r2;
            }

            private /* synthetic */ void lambda$onComplete$0(List r1) {
                ThemeChooser.m7305$$Nest$monDataLoaded(this.this$0, r1);
            }

            @Override // org.telegram.tgnet.ResultCallback
            public /* bridge */ /* synthetic */ void onComplete(List<EmojiThemes> r1) {
                onComplete2(r1);
            }

            /* renamed from: onComplete, reason: avoid collision after fix types in other method */
            public void onComplete2(List<EmojiThemes> r3) {
                NotificationCenter.getInstance(this.val$currentAccount).doOnIdle(new ChannelColorActivity$ThemeChooser$4$$ExternalSyntheticLambda0(this, r3));
            }

            @Override // org.telegram.tgnet.ResultCallback
            public void onError(TLRPC.TL_error r2) {
                Toast.makeText(this.this$0.getContext(), r2.text, 0).show();
            }
        }

        public static /* synthetic */ void $r8$lambda$2LRcT_BnIGK8Rmqt2EhqCnDcark(ThemeChooser r0, TLObject r1, Theme.ThemeInfo r2) {
            r0.lambda$parseTheme$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$8cYNReSdpQBtb10TXd7efux0y74(ThemeChooser r0, View r1) {
            r0.lambda$setGalleryWallpaper$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$K9_EsYSm8zhKe13_V0TNlWC0XCc(ThemeChooser r0, View r1) {
            r0.lambda$updateColors$2(r1);
        }

        /* renamed from: $r8$lambda$MvaF-HLTc6jHTsT9l0K9yQM7zgs, reason: not valid java name */
        public static /* synthetic */ void m7303$r8$lambda$MvaFHLTc6jHTsT9l0K9yQM7zgs(ThemeChooser r0, boolean r1, View r2, int r3) {
            r0.lambda$new$1(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$gXEHMt2bvnOiQEjuFvJjQuQ7Kb4(ThemeChooser r0, Theme.ThemeInfo r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$parseTheme$4(r1, r2, r3);
        }

        /* renamed from: -$$Nest$fgetfallbackWallpaper, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.WallPaper m7304$$Nest$fgetfallbackWallpaper(ThemeChooser r0) {
            return r0.fallbackWallpaper;
        }

        /* renamed from: -$$Nest$monDataLoaded, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m7305$$Nest$monDataLoaded(ThemeChooser r0, List r1) {
            r0.onDataLoaded(r1);
        }

        /* renamed from: -$$Nest$mparseTheme, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m7306$$Nest$mparseTheme(ThemeChooser r0, Theme.ThemeInfo r1) {
            return r0.parseTheme(r1);
        }

        public ThemeChooser(Context r10, boolean r11, int r12, Theme.ResourcesProvider r13) {
            super(r10);
            this.items = new ArrayList();
            this.loadingThemes = new HashMap();
            this.loadingWallpapers = new HashMap();
            this.currentAccount = r12;
            this.resourcesProvider = r13;
            if (r11 == true) goto L5;
            FlickerLoadingView r1 = new FlickerLoadingView(getContext(), r13);
            this.progressView = r1;
            r1.setViewType(14);
            this.progressView.setVisibility(0);
            addView(this.progressView, LayoutHelper.createFrame(-1, 104.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 16.0f, 13.0f, 16.0f, 6.0f));
        L5:
            AnonymousClass1 r14 = new AnonymousClass1(this, r10, r13);
            this.listView = r14;
            r14.setClipToPadding(false);
            int r2 = AndroidUtilities.dp(16.0f);
            float r3 = 13.0f;
            int r4 = AndroidUtilities.dp(13.0f);
            int r102 = AndroidUtilities.dp(16.0f);
            if (r11 == true) goto L9;
            r3 = 6.0f;
        L9:
            r14.setPadding(r2, r4, r102, AndroidUtilities.dp(r3));
            if (r11 == false) goto L12;
            r14.setHasFixedSize(false);
            GridLayoutManager r103 = new GridLayoutManager(getContext(), 3);
            r103.setSpanSizeLookup(new AnonymousClass2(this));
            r14.setLayoutManager(r103);
        L13:
            AnonymousClass3 r104 = new AnonymousClass3(this, r12, r13, r11);
            this.adapter = r104;
            r14.setAdapter(r104);
            if (r11 == false) goto L16;
            float r105 = -1.0f;
        L17:
            addView(r14, LayoutHelper.createFrame(-1, r105));
            r14.setOnItemClickListener(new ChannelColorActivity$ThemeChooser$$ExternalSyntheticLambda2(this, r11));
            ChatThemeController r106 = ChatThemeController.getInstance(r12);
            r106.preloadAllWallpaperThumbs(true);
            r106.preloadAllWallpaperThumbs(false);
            r106.preloadAllWallpaperImages(true);
            r106.preloadAllWallpaperImages(false);
            r106.requestAllChatThemes(new AnonymousClass4(this, r12), true);
            updateState(false);
            return;
        L16:
            r105 = 130.0f;
            goto L17
        L12:
            LinearLayoutManager r107 = new LinearLayoutManager(getContext());
            r107.setOrientation(0);
            r14.setLayoutManager(r107);
            r14.setAlpha(0.0f);
            goto L13
        }

        private /* synthetic */ void lambda$new$1(boolean r6, View r7, int r8) {
            if (r8 >= 0) goto L4;
            return;
        L4:
            if (r8 >= this.items.size()) goto L20;
            ChatThemeBottomSheet.ChatThemeItem r82 = this.items.get(r8);
            if (r6 == true) goto L14;
            setSelectedEmoticon(r82.getEmoticon(), true);
            if (r7.getLeft() >= (this.listView.getPaddingLeft() + AndroidUtilities.dp(24.0f))) goto L12;
            RecyclerListView r62 = this.listView;
            r62.smoothScrollBy(-((r62.getPaddingLeft() + AndroidUtilities.dp(48.0f)) - r7.getLeft()), 0);
            goto L14
        L12:
            if ((r7.getLeft() + r7.getWidth()) <= ((this.listView.getMeasuredWidth() - this.listView.getPaddingRight()) - AndroidUtilities.dp(24.0f))) goto L14;
            this.listView.smoothScrollBy((r7.getLeft() + r7.getWidth()) - ((this.listView.getMeasuredWidth() - this.listView.getPaddingRight()) - AndroidUtilities.dp(48.0f)), 0);
        L14:
            Utilities.Callback<String> r5 = this.onEmoticonSelected;
            if (r5 == null) goto L19;
            r5.run(r82.getEmoticon());
            return;
        L19:
            return;
        }

        private /* synthetic */ void lambda$parseTheme$3(TLObject r4, Theme.ThemeInfo r5) {
            if ((r4 instanceof TLRPC.TL_wallPaper) == false) goto L8;
            TLRPC.WallPaper r42 = (TLRPC.WallPaper) r4;
            String r0 = FileLoader.getAttachFileName(r42.document);
            if (this.loadingThemes.containsKey(r0) == true) goto L10;
            this.loadingThemes.put(r0, r5);
            FileLoader.getInstance(r5.account).loadFile(r42.document, r42, 1, 1);
            return;
        L10:
            return;
        L8:
            r5.badWallpaper = true;
        }

        private /* synthetic */ void lambda$parseTheme$4(Theme.ThemeInfo r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new ChannelColorActivity$ThemeChooser$$ExternalSyntheticLambda4(this, r2, r1));
        }

        private /* synthetic */ void lambda$setGalleryWallpaper$0(View r2) {
            if ((r2 instanceof ThemeSmallPreviewView) == false) goto L10;
            ThemeSmallPreviewView r22 = (ThemeSmallPreviewView) r2;
            if (r22.chatThemeItem.chatTheme.showAsRemovedStub == false) goto L7;
            TLRPC.WallPaper r1 = null;
        L8:
            r22.setFallbackWallpaper(r1);
            return;
        L7:
            r1 = this.fallbackWallpaper;
            goto L8
        }

        private /* synthetic */ void lambda$updateColors$2(View r2) {
            ((ThemeSmallPreviewView) r2).setBackgroundColor(Theme.getColor(Theme.key_dialogBackgroundGray, this.resourcesProvider));
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void onDataLoaded(List<EmojiThemes> r8) {
            if (r8 != null) goto L4;
            return;
        L4:
            if (r8.isEmpty() == true) goto L51;
            this.dataLoaded = true;
            this.items.clear();
            this.items.add(0, new ChatThemeBottomSheet.ChatThemeItem(r8.get(0)));
            if (this.fallbackWallpaper != null) goto L9;
        L11:
            Theme.ResourcesProvider r1 = this.resourcesProvider;
            if (r1 == null) goto L14;
            int r12 = r1.isDark();
        L15:
            int r3 = 1;
        L17:
            if (r3 >= r8.size()) goto L19;
            EmojiThemes r4 = r8.get(r3);
            ChatThemeBottomSheet.ChatThemeItem r5 = new ChatThemeBottomSheet.ChatThemeItem(r4);
            r4.loadPreviewColors(this.currentAccount);
            r5.themeIndex = r12;
            this.items.add(r5);
            r3 = r3 + 1;
            goto L17
        L19:
            int r82 = -1;
            int r13 = 0;
        L21:
            if (r13 >= this.items.size()) goto L35;
            ChatThemeBottomSheet.ChatThemeItem r32 = this.items.get(r13);
            if (TextUtils.equals(this.currentEmoticon, r32.getEmoticon()) == false) goto L25;
        L30:
            boolean r42 = true;
        L31:
            r32.isSelected = r42;
            if (r42 == false) goto L34;
            r82 = r13;
        L34:
            r13 = r13 + 1;
            goto L21
        L25:
            if (TextUtils.isEmpty(this.currentEmoticon) == true) goto L27;
        L29:
            r42 = false;
            goto L31
        L27:
            if (r32.chatTheme.showAsDefaultStub == false) goto L29;
        L35:
            RecyclerListView.SelectionAdapter r14 = this.adapter;
            if (r14 == null) goto L38;
            r14.notifyDataSetChanged();
        L38:
            this.listView.animate().alpha(1.0f).setDuration(150).start();
            updateState(true);
            if (r82 >= 0) goto L41;
            return;
        L41:
            if ((this.listView.getLayoutManager() instanceof LinearLayoutManager) == false) goto L50;
            ((LinearLayoutManager) this.listView.getLayoutManager()).scrollToPositionWithOffset(r82, (AndroidUtilities.displaySize.x - AndroidUtilities.dp(83.0f)) / 2);
            return;
        L50:
            return;
        L14:
            r12 = Theme.isCurrentThemeDark();
            goto L15
        L9:
            if (this.withRemovedStub == false) goto L11;
            this.items.add(0, new ChatThemeBottomSheet.ChatThemeItem(EmojiThemes.createChatThemesRemoved(this.currentAccount)));
            goto L11
        }

        private boolean parseTheme(Theme.ThemeInfo r19) {
            if (r19 != null) goto L5;
            return false;
        L5:
            if (r19.pathToFile == null) goto L162;
            boolean r4 = true;
            FileInputStream r5 = new FileInputStream(new File(r19.pathToFile));     // Catch: Throwable -> L116
            int r0 = 0;
            boolean r6 = false;
        L140:
            int r7 = r5.read(ThemesHorizontalListCell.bytes);     // Catch: Throwable -> L30
            char r8 = 65535;
            if (r7 == (-1)) goto L114;
            int r11 = r0;
            int r9 = 0;
            int r10 = 0;
        L13:
            if (r9 >= r7) goto L109;
            byte[] r12 = ThemesHorizontalListCell.bytes;     // Catch: Throwable -> L30
            if (r12[r9] != 10) goto L107;
            int r13 = r9 - r10;     // Catch: Throwable -> L30
            int r14 = r13 + 1;     // Catch: Throwable -> L30
            String r15 = new String(r12, r10, r13, "UTF-8");     // Catch: Throwable -> L30
            if (r15.startsWith("WLS=") == false) goto L63;
            String r82 = r15.substring(4);     // Catch: Throwable -> L30
            Uri r122 = Uri.parse(r82);     // Catch: Throwable -> L30
            r19.slug = r122.getQueryParameter("slug");     // Catch: Throwable -> L30
            r19.pathToWallpaper = new File(ApplicationLoader.getFilesDirFixed(), Utilities.MD5(r82) + ".wp").getAbsolutePath();     // Catch: Throwable -> L30
            String r3 = r122.getQueryParameter("mode");     // Catch: Throwable -> L30
            if (r3 == null) goto L34;
            String[] r32 = r3.toLowerCase().split(" ");     // Catch: Throwable -> L30
            if (r32 == null) goto L34;
            if (r32.length <= 0) goto L34;
            int r83 = 0;
        L26:
            if (r83 >= r32.length) goto L34;
            if ("blur".equals(r32[r83]) == true) goto L29;
            r83 = r83 + 1;     // Catch: Throwable -> L30
            goto L26
        L29:
            r19.isBlured = r4;     // Catch: Throwable -> L30
        L34:
            if (TextUtils.isEmpty(r122.getQueryParameter("pattern")) == false) goto L147;
        L61:
            char r42 = 65535;
        L106:
            r10 = r10 + r14;     // Catch: Throwable -> L30
            r11 = r11 + r14;     // Catch: Throwable -> L30
        L108:
            r9 = r9 + 1;     // Catch: Throwable -> L30
            r8 = r42;
            r4 = true;
            goto L13
        L147:
            String r33 = r122.getQueryParameter("bg_color");     // Catch: Throwable -> L30 Exception -> L138
            if (TextUtils.isEmpty(r33) == true) goto L143;
            r19.patternBgColor = Integer.parseInt(r33.substring(0, 6), 16) | (-16777216);     // Catch: Throwable -> L30 Exception -> L138
            if (r33.length() < 13) goto L43;
            if (AndroidUtilities.isValidWallChar(r33.charAt(6)) == false) goto L43;
            r19.patternBgGradientColor1 = Integer.parseInt(r33.substring(7, 13), 16) | (-16777216);     // Catch: Throwable -> L30 Exception -> L138
        L43:
            if (r33.length() < 20) goto L48;
            if (AndroidUtilities.isValidWallChar(r33.charAt(13)) == false) goto L48;
            r19.patternBgGradientColor2 = Integer.parseInt(r33.substring(14, 20), 16) | (-16777216);     // Catch: Throwable -> L30 Exception -> L138
        L48:
            if (r33.length() != 27) goto L143;
            if (AndroidUtilities.isValidWallChar(r33.charAt(20)) == false) goto L143;
            r19.patternBgGradientColor3 = Integer.parseInt(r33.substring(21), 16) | (-16777216);     // Catch: Throwable -> L30 Exception -> L138
        L143:
            String r34 = r122.getQueryParameter("rotation");     // Catch: Throwable -> L30 Exception -> L139
            if (TextUtils.isEmpty(r34) == true) goto L55;
            r19.patternBgGradientRotation = Utilities.parseInt(r34);     // Catch: Throwable -> L30 Exception -> L139
        L55:
            String r35 = r122.getQueryParameter("intensity");     // Catch: Throwable -> L30
            if (TextUtils.isEmpty(r35) == true) goto L59;
            r19.patternIntensity = Utilities.parseInt(r35);     // Catch: Throwable -> L30
        L59:
            if (r19.patternIntensity != 0) goto L61;
            r19.patternIntensity = 50;     // Catch: Throwable -> L30
            goto L61
        L63:
            if (r15.startsWith("WPS") == true) goto L64;
            int r36 = r15.indexOf(61);     // Catch: Throwable -> L30
            r42 = 65535;
            if (r36 == (-1)) goto L106;
            int r84 = ThemeColors.stringKeyToInt(r15.substring(0, r36));     // Catch: Throwable -> L30
            if (r84 != Theme.key_chat_inBubble) goto L70;
        L79:
            String r37 = r15.substring(r36 + 1);     // Catch: Throwable -> L30
            if (r37.length() > 0) goto L83;
        L87:
            int r38 = Utilities.parseInt(r37);     // Catch: Throwable -> L30
        L89:
            if (r84 != Theme.key_chat_inBubble) goto L92;
            r19.setPreviewInColor(r38);     // Catch: Throwable -> L30
            goto L106
        L92:
            if (r84 != Theme.key_chat_outBubble) goto L95;
            r19.setPreviewOutColor(r38);     // Catch: Throwable -> L30
            goto L106
        L95:
            if (r84 != Theme.key_chat_wallpaper) goto L98;
            r19.setPreviewBackgroundColor(r38);     // Catch: Throwable -> L30
            goto L106
        L98:
            if (r84 != Theme.key_chat_wallpaper_gradient_to1) goto L101;
            r19.previewBackgroundGradientColor1 = r38;     // Catch: Throwable -> L30
            goto L106
        L101:
            if (r84 != Theme.key_chat_wallpaper_gradient_to2) goto L104;
            r19.previewBackgroundGradientColor2 = r38;     // Catch: Throwable -> L30
            goto L106
        L104:
            if (r84 != Theme.key_chat_wallpaper_gradient_to3) goto L106;
            r19.previewBackgroundGradientColor3 = r38;     // Catch: Throwable -> L30
            goto L106
        L83:
            if (r37.charAt(0) != '#') goto L87;
            r38 = Color.parseColor(r37);     // Catch: Throwable -> L30 Exception -> L86
        L86:
            r38 = Utilities.parseInt(r37);     // Catch: Throwable -> L30
            goto L89
        L70:
            if (r84 == Theme.key_chat_outBubble) goto L79;
            if (r84 == Theme.key_chat_wallpaper) goto L79;
            if (r84 == Theme.key_chat_wallpaper_gradient_to1) goto L79;
            if (r84 == Theme.key_chat_wallpaper_gradient_to2) goto L79;
            if (r84 != Theme.key_chat_wallpaper_gradient_to3) goto L106;
        L64:
            r19.previewWallpaperOffset = r14 + r11;     // Catch: Throwable -> L30
            r6 = true;
            goto L109
        L107:
            r42 = r8;
        L109:
            if (r6 == true) goto L114;
            if (r0 == r11) goto L114;
            r5.getChannel().position(r11);     // Catch: Throwable -> L30
            r0 = r11;
            r4 = true;
        L114:
            r5.close();     // Catch: Throwable -> L116
        L125:
            if (r19.pathToWallpaper != null) goto L127;
        L135:
            r19.previewParsed = true;
            return true;
        L127:
            if (r19.badWallpaper == true) goto L135;
            if (new File(r19.pathToWallpaper).exists() == true) goto L135;
            if (this.loadingWallpapers.containsKey(r19) == true) goto L161;
            this.loadingWallpapers.put(r19, r19.slug);
            TL_account.getWallPaper r02 = new TL_account.getWallPaper();
            TLRPC.TL_inputWallPaperSlug r39 = new TLRPC.TL_inputWallPaperSlug();
            r39.slug = r19.slug;
            r02.wallpaper = r39;
            ConnectionsManager.getInstance(r19.account).sendRequest(r02, new ChannelColorActivity$ThemeChooser$$ExternalSyntheticLambda3(this, r19));
            return false;
        L161:
            return false;
        L30:
            th = move-exception;
            r5.close();     // Catch: Throwable -> L120
        L160:
            throw th;     // Catch: Throwable -> L116
        L120:
            th = move-exception;
            th.addSuppressed(th);     // Catch: Throwable -> L116
            throw th;     // Catch: Throwable -> L116
        L116:
            th = move-exception;
            FileLog.e(th);
            goto L125
        L162:
            return false;
        }

        private void updateSelected() {
            int r0 = 0;
        L4:
            if (r0 >= this.listView.getChildCount()) goto L13;
            View r1 = this.listView.getChildAt(r0);
            if ((r1 instanceof ThemeSmallPreviewView) == false) goto L12;
            int r2 = this.listView.getChildAdapterPosition(r1);
            if (r2 < 0) goto L12;
            if (r2 >= this.items.size()) goto L12;
            ((ThemeSmallPreviewView) r1).setSelected(this.items.get(r2).isSelected, true);
        L12:
            r0 = r0 + 1;
            goto L4
        }

        private void updateState(boolean r4) {
            boolean r0 = this.dataLoaded;
            FlickerLoadingView r3 = this.progressView;
            if (r0 == true) goto L6;
            AndroidUtilities.updateViewVisibilityAnimated(r3, true, 1.0f, true, r4);
            return;
        L6:
            AndroidUtilities.updateViewVisibilityAnimated(r3, false, 1.0f, true, r4);
        }

        public boolean isDark() {
            Theme.ResourcesProvider r0 = this.resourcesProvider;
            if (r0 == null) goto L7;
            return r0.isDark();
        L7:
            return Theme.isCurrentThemeDark();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void setGalleryWallpaper(TLRPC.WallPaper r4) {
            this.fallbackWallpaper = r4;
            AndroidUtilities.forEachViews(this.listView, new ChannelColorActivity$ThemeChooser$$ExternalSyntheticLambda1(this));
            if (this.fallbackWallpaper != null) goto L5;
            return;
        L5:
            if (this.items.isEmpty() == true) goto L9;
            if (this.items.get(0).chatTheme.showAsDefaultStub == true) goto L9;
            return;
        L9:
            if (this.withRemovedStub == false) goto L14;
            this.items.add(0, new ChatThemeBottomSheet.ChatThemeItem(EmojiThemes.createChatThemesRemoved(this.currentAccount)));
            this.adapter.notifyDataSetChanged();
            return;
        }

        public void setOnEmoticonSelected(Utilities.Callback<String> r1) {
            this.onEmoticonSelected = r1;
        }

        public void setSelectedEmoticon(String r7, boolean r8) {
            this.currentEmoticon = r7;
            int r0 = -1;
            int r2 = 0;
        L4:
            if (r2 >= this.items.size()) goto L18;
            ChatThemeBottomSheet.ChatThemeItem r3 = this.items.get(r2);
            if (TextUtils.equals(this.currentEmoticon, r3.getEmoticon()) == false) goto L8;
        L13:
            boolean r4 = true;
        L14:
            r3.isSelected = r4;
            if (r4 == false) goto L17;
            r0 = r2;
        L17:
            r2 = r2 + 1;
            goto L4
        L8:
            if (TextUtils.isEmpty(r7) == true) goto L10;
        L12:
            r4 = false;
            goto L14
        L10:
            if (r3.chatTheme.showAsDefaultStub == false) goto L12;
        L18:
            if (r0 < 0) goto L23;
            if (r8 == true) goto L23;
            if ((this.listView.getLayoutManager() instanceof LinearLayoutManager) == false) goto L23;
            ((LinearLayoutManager) this.listView.getLayoutManager()).scrollToPositionWithOffset(r0, (AndroidUtilities.displaySize.x - AndroidUtilities.dp(83.0f)) / 2);
        L23:
            updateSelected();
        }

        public void setWithRemovedStub(boolean r1) {
            this.withRemovedStub = r1;
        }

        public void updateColors() {
            boolean r0 = isDark();
            int r1 = 0;
        L4:
            if (r1 >= this.items.size()) goto L6;
            ChatThemeBottomSheet.ChatThemeItem r2 = this.items.get(r1);
            r2.themeIndex = r0 ? 1 : 0;
            r1 = r1 + 1;
            goto L4
        L6:
            AndroidUtilities.forEachViews(this.listView, new ChannelColorActivity$ThemeChooser$$ExternalSyntheticLambda0(this));
            this.adapter.notifyDataSetChanged();
        }
    }

    public class ThemeDelegate implements Theme.ResourcesProvider {
        final /* synthetic */ ChannelColorActivity this$0;

        public ThemeDelegate(ChannelColorActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public int getColor(int r3) {
            int r0 = ChannelColorActivity.m7279$$Nest$fgetcurrentColors(this.this$0).indexOfKey(r3);
            ChannelColorActivity r1 = this.this$0;
            if (r0 < 0) goto L7;
            return ChannelColorActivity.m7279$$Nest$fgetcurrentColors(r1).valueAt(r0);
        L7:
            if (ChannelColorActivity.m7289$$Nest$fgetparentResourcesProvider(r1) == null) goto L11;
            return ChannelColorActivity.m7289$$Nest$fgetparentResourcesProvider(this.this$0).getColor(r3);
        L11:
            return Theme.getColor(r3);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public Drawable getDrawable(String r3) {
            if (r3.equals("drawableMsgIn") == false) goto L7;
            return ChannelColorActivity.m7283$$Nest$fgetmsgInDrawable(this.this$0);
        L7:
            if (r3.equals("drawableMsgInSelected") == false) goto L11;
            return ChannelColorActivity.m7284$$Nest$fgetmsgInDrawableSelected(this.this$0);
        L11:
            if (r3.equals("drawableMsgOut") == false) goto L15;
            return ChannelColorActivity.m7286$$Nest$fgetmsgOutDrawable(this.this$0);
        L15:
            if (r3.equals("drawableMsgOutSelected") == false) goto L19;
            return ChannelColorActivity.m7287$$Nest$fgetmsgOutDrawableSelected(this.this$0);
        L19:
            if (r3.equals("drawableMsgOutCheckRead") == false) goto L22;
            ChannelColorActivity.m7285$$Nest$fgetmsgOutCheckReadDrawable(this.this$0).setColorFilter(getColor(Theme.key_chat_outSentCheckRead), PorterDuff.Mode.MULTIPLY);
            return ChannelColorActivity.m7285$$Nest$fgetmsgOutCheckReadDrawable(this.this$0);
        L22:
            boolean r0 = r3.equals("drawableMsgOutHalfCheck");
            ChannelColorActivity r1 = this.this$0;
            if (r0 == false) goto L27;
            ChannelColorActivity.m7288$$Nest$fgetmsgOutHalfCheckDrawable(r1).setColorFilter(getColor(Theme.key_chat_outSentCheckRead), PorterDuff.Mode.MULTIPLY);
            return ChannelColorActivity.m7288$$Nest$fgetmsgOutHalfCheckDrawable(this.this$0);
        L27:
            if (ChannelColorActivity.m7289$$Nest$fgetparentResourcesProvider(r1) == null) goto L31;
            return ChannelColorActivity.m7289$$Nest$fgetparentResourcesProvider(this.this$0).getDrawable(r3);
        L31:
            return Theme.getThemeDrawable(r3);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public Paint getPaint(String r2) {
            if (r2.equals("paintDivider") == false) goto L7;
            return ChannelColorActivity.m7281$$Nest$fgetdividerPaint(this.this$0);
        L7:
            return super.getPaint(r2);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public boolean isDark() {
            return ChannelColorActivity.m7282$$Nest$fgetisDark(this.this$0);
        }

        public void toggle() {
            ChannelColorActivity.m7293$$Nest$fputisDark(this.this$0, !ChannelColorActivity.m7282$$Nest$fgetisDark(r0));
            this.this$0.updateThemeColors();
            this.this$0.updateColors(false);
        }
    }

    /* renamed from: $r8$lambda$07J0kbis6P0B72R-Txx4wOBmE9A, reason: not valid java name */
    public static /* synthetic */ void m7273$r8$lambda$07J0kbis6P0B72RTxx4wOBmE9A(ChannelColorActivity r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$buttonClick$9(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$0pqIxXaZHy6R6zcm0YSSOlqKR5k(ChannelColorActivity r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$buttonClick$11(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$1-0u3o4tnrkSct1rYqtRSSIhtIM, reason: not valid java name */
    public static /* synthetic */ void m7274$r8$lambda$10u3o4tnrkSct1rYqtRSSIhtIM(ChannelColorActivity r0, ChannelBoostsController.CanApplyBoost r1) {
        r0.lambda$showLimit$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$42JKq8P0MF_BVmAnL_ToLbG4hSI(ChannelColorActivity r0, TLRPC.WallPaper r1) {
        r0.lambda$createView$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$48imQQRTF38pRItAYrG0fBxAtjY(ChannelColorActivity r0) {
        r0.lambda$createView$2();
    }

    public static /* synthetic */ void $r8$lambda$C_pkY6Giu4HHaA47sr8VM3kwAao(ChannelColorActivity r0, AlertDialog r1, int r2) {
        r0.lambda$showUnsavedAlert$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$GuvliqQjZCRs_a9IL8uvMj4_2FE(ChannelColorActivity r0, TLRPC.ChatFull r1, View r2, int r3) {
        r0.lambda$createView$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$HVmp_1FrgGN87ewBGKWMsMmcvIU(ChannelColorActivity r0, int r1, View r2, Long r3, Integer r4, TL_stars.TL_starGiftUnique r5) {
        r0.lambda$createView$1(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$HZThSyHPcqqHT_WHlEwdF8CEEEQ(ChannelColorActivity r0, TLRPC.Chat r1) {
        r0.lambda$showLimit$12(r1);
    }

    /* renamed from: $r8$lambda$UA-T76r0-XPIMHO99EA3MDSSK-c, reason: not valid java name */
    public static /* synthetic */ void m7275$r8$lambda$UAT76r0XPIMHO99EA3MDSSKc(ChannelColorActivity r0, AlertDialog r1, int r2) {
        r0.lambda$showUnsavedAlert$15(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZTBnGaRCHuwxCkj80f8Ykof7zl4(ChannelColorActivity r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    /* renamed from: $r8$lambda$at-WDiqHegzsUdaiZJTNjY1ligg, reason: not valid java name */
    public static /* synthetic */ void m7276$r8$lambda$atWDiqHegzsUdaiZJTNjY1ligg(ChannelColorActivity r0, View r1) {
        r0.updateColors(r1);
    }

    public static /* synthetic */ void $r8$lambda$dm3hVaRGrt5FLzDQT5aHBRpqCzE(ChannelColorActivity r0, boolean[] r1, int[] r2, int[] r3, TLRPC.TL_error r4) {
        r0.lambda$buttonClick$7(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$e9vKab2JHyc27pnIk6bSF5JSadw(ChannelColorActivity r0, boolean[] r1, int[] r2, int[] r3, TLRPC.TL_error r4) {
        r0.lambda$buttonClick$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$eNVP1OD2cloWdLza1QQcm7sKKiQ(ChannelColorActivity r0) {
        r0.lambda$toggleTheme$17();
    }

    public static /* synthetic */ boolean $r8$lambda$hmVvo36VO0QvCdUVJUydBvkeXWs(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$onf8MtbXHzEwhDdkG9hFeMFOcL4(ChannelColorActivity r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$buttonClick$8(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$p39cUiUWjfJmDz_GMSJN_Nx8Gr8(ChannelColorActivity r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$buttonClick$10(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$r5N39FgeE9hcgtred3hVLlg36Mg(ChannelColorActivity r0, TL_stories.TL_premium_boostsStatus r1) {
        r0.updateBoostsAndLevels(r1);
    }

    public static /* synthetic */ void $r8$lambda$yR62fJLXqQavXTcV_51axQtJEvQ(ChannelColorActivity r0, TLRPC.Chat r1, TL_stories.TL_premium_boostsStatus r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetchangeDayNightView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m7277$$Nest$fgetchangeDayNightView(ChannelColorActivity r0) {
        return r0.changeDayNightView;
    }

    /* renamed from: -$$Nest$fgetchangeDayNightViewProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m7278$$Nest$fgetchangeDayNightViewProgress(ChannelColorActivity r0) {
        return r0.changeDayNightViewProgress;
    }

    /* renamed from: -$$Nest$fgetcurrentColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseIntArray m7279$$Nest$fgetcurrentColors(ChannelColorActivity r0) {
        return r0.currentColors;
    }

    /* renamed from: -$$Nest$fgetdayNightItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m7280$$Nest$fgetdayNightItem(ChannelColorActivity r0) {
        return r0.dayNightItem;
    }

    /* renamed from: -$$Nest$fgetdividerPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m7281$$Nest$fgetdividerPaint(ChannelColorActivity r0) {
        return r0.dividerPaint;
    }

    /* renamed from: -$$Nest$fgetisDark, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7282$$Nest$fgetisDark(ChannelColorActivity r0) {
        return r0.isDark;
    }

    /* renamed from: -$$Nest$fgetmsgInDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageDrawable m7283$$Nest$fgetmsgInDrawable(ChannelColorActivity r0) {
        return r0.msgInDrawable;
    }

    /* renamed from: -$$Nest$fgetmsgInDrawableSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageDrawable m7284$$Nest$fgetmsgInDrawableSelected(ChannelColorActivity r0) {
        return r0.msgInDrawableSelected;
    }

    /* renamed from: -$$Nest$fgetmsgOutCheckReadDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m7285$$Nest$fgetmsgOutCheckReadDrawable(ChannelColorActivity r0) {
        return r0.msgOutCheckReadDrawable;
    }

    /* renamed from: -$$Nest$fgetmsgOutDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageDrawable m7286$$Nest$fgetmsgOutDrawable(ChannelColorActivity r0) {
        return r0.msgOutDrawable;
    }

    /* renamed from: -$$Nest$fgetmsgOutDrawableSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageDrawable m7287$$Nest$fgetmsgOutDrawableSelected(ChannelColorActivity r0) {
        return r0.msgOutDrawableSelected;
    }

    /* renamed from: -$$Nest$fgetmsgOutHalfCheckDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m7288$$Nest$fgetmsgOutHalfCheckDrawable(ChannelColorActivity r0) {
        return r0.msgOutHalfCheckDrawable;
    }

    /* renamed from: -$$Nest$fgetparentResourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m7289$$Nest$fgetparentResourcesProvider(ChannelColorActivity r0) {
        return r0.parentResourcesProvider;
    }

    /* renamed from: -$$Nest$fputchangeDayNightView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7290$$Nest$fputchangeDayNightView(ChannelColorActivity r0, View r1) {
        r0.changeDayNightView = r1;
    }

    /* renamed from: -$$Nest$fputchangeDayNightViewAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7291$$Nest$fputchangeDayNightViewAnimator(ChannelColorActivity r0, ValueAnimator r1) {
        r0.changeDayNightViewAnimator = r1;
    }

    /* renamed from: -$$Nest$fputchangeDayNightViewProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7292$$Nest$fputchangeDayNightViewProgress(ChannelColorActivity r0, float r1) {
        r0.changeDayNightViewProgress = r1;
    }

    /* renamed from: -$$Nest$fputisDark, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7293$$Nest$fputisDark(ChannelColorActivity r0, boolean r1) {
        r0.isDark = r1;
    }

    /* renamed from: -$$Nest$fputselectAnimatedEmojiDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7294$$Nest$fputselectAnimatedEmojiDialog(ChannelColorActivity r0, SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow r1) {
        r0.selectAnimatedEmojiDialog = r1;
    }

    /* renamed from: -$$Nest$mgetEmojiSetThumb, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Document m7295$$Nest$mgetEmojiSetThumb(ChannelColorActivity r0, TLRPC.StickerSet r1) {
        return r0.getEmojiSetThumb(r1);
    }

    /* renamed from: -$$Nest$mgetEmojiSetThumbId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m7296$$Nest$mgetEmojiSetThumbId(ChannelColorActivity r0, TLRPC.StickerSet r1) {
        return r0.getEmojiSetThumbId(r1);
    }

    /* renamed from: -$$Nest$mgetThemeChooserEmoticon, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m7297$$Nest$mgetThemeChooserEmoticon(ChannelColorActivity r0) {
        return r0.getThemeChooserEmoticon();
    }

    /* renamed from: -$$Nest$mshowUnsavedAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7298$$Nest$mshowUnsavedAlert(ChannelColorActivity r0) {
        r0.showUnsavedAlert();
    }

    /* renamed from: -$$Nest$mupdateColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7299$$Nest$mupdateColors(ChannelColorActivity r0, View r1) {
        r0.updateColors(r1);
    }

    public ChannelColorActivity(long r6) {
        boolean r0 = Theme.isCurrentThemeDark();
        this.isDark = r0;
        this.rowsCount = 0;
        this.forceDark = r0;
        this.currentColors = new SparseIntArray();
        Paint r02 = new Paint(1);
        this.dividerPaint = r02;
        r02.setStrokeWidth(1.0f);
        r02.setColor(Theme.getColor(Theme.key_divider, this.resourceProvider));
        this.msgOutCheckReadDrawable = ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.msg_check_s).mutate();
        this.msgOutHalfCheckDrawable = ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.msg_halfcheck).mutate();
        this.dialogId = r6;
        TLRPC.Chat r03 = getMessagesController().getChat(Long.valueOf(-r6));
        if (r03 == null) goto L5;
        this.currentLevel = r03.level;
    L5:
        MessagesController.getInstance(this.currentAccount).getBoostsController().getBoostsStats(r6, new ChannelColorActivity$$ExternalSyntheticLambda5(this, r03));
        this.resourceProvider = new ThemeDelegate(this);
        this.msgInDrawable = new MessageDrawable(0, false, false, this.resourceProvider);
        this.msgInDrawableSelected = new MessageDrawable(0, false, true, this.resourceProvider);
        this.msgOutDrawable = new MessageDrawable(0, true, false, this.resourceProvider);
        this.msgOutDrawableSelected = new MessageDrawable(0, true, true, this.resourceProvider);
    }

    public static /* synthetic */ INavigationLayout access$000(ChannelColorActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(ChannelColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1000(ChannelColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1100(ChannelColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1200(ChannelColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$1300(ChannelColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1400(ChannelColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1500(ChannelColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1600(ChannelColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1700(ChannelColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1800(ChannelColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$1900(ChannelColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(ChannelColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2000(ChannelColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$2100(ChannelColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2200(ChannelColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2300(ChannelColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2400(ChannelColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2500(ChannelColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$300(ChannelColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$400(ChannelColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$500(ChannelColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$600(ChannelColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$700(ChannelColorActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$800(ChannelColorActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$900(ChannelColorActivity r0) {
        return r0.resourceProvider;
    }

    private void buttonClick() {
        if (this.boostsStatus != null) goto L5;
        return;
    L5:
        if (this.button.isLoading() == false) goto L8;
        return;
    L8:
        if (this.currentLevel >= minLevelRequired()) goto L11;
        this.button.setLoading(true);
        showLimit();
        return;
    L11:
        int[] r0 = {0};
        ChannelColorActivity$$ExternalSyntheticLambda7 r5 = new ChannelColorActivity$$ExternalSyntheticLambda7(this, new boolean[]{false}, new int[]{0}, r0);
        TLRPC.Chat r3 = getMessagesController().getChat(Long.valueOf(-this.dialogId));
        if (r3 != null) goto L15;
        FileLog.e("channel is null in ChannelColorAcitivity");
        BulletinFactory.of(this).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.UnknownError)).show();
        return;
    L15:
        this.button.setLoading(true);
        if (this.currentReplyColor == this.selectedReplyColor) goto L18;
    L19:
        TLRPC.TL_channels_updateColor r4 = new TLRPC.TL_channels_updateColor();
        r4.channel = getMessagesController().getInputChannel(-this.dialogId);
        r4.for_profile = false;
        if (r3.color != null) goto L22;
        r3.color = new TLRPC.TL_peerColor();
        r3.flags2 |= 128;
    L22:
        int r6 = r4.flags;
        r4.flags = r6 | 4;
        int r9 = this.selectedReplyColor;
        r4.color = r9;
        TLRPC.PeerColor r10 = r3.color;
        int r11 = r10.flags;
        int r12 = r11 | 1;
        r10.flags = r12;
        r10.color = r9;
        long r13 = this.selectedReplyEmoji;
        if (r13 == 0) goto L25;
        r4.flags = r6 | 5;
        r4.background_emoji_id = r13;
        r10.flags = r11 | 3;
        r10.background_emoji_id = r13;
    L26:
        r0[0] = r0[0] + 1;
        getConnectionsManager().sendRequest(r4, new ChannelColorActivity$$ExternalSyntheticLambda8(this, r5));
    L28:
        if (this.currentProfileColor == this.selectedProfileColor) goto L30;
    L31:
        TLRPC.TL_channels_updateColor r42 = new TLRPC.TL_channels_updateColor();
        r42.channel = getMessagesController().getInputChannel(-this.dialogId);
        r42.for_profile = true;
        if (r3.profile_color != null) goto L34;
        r3.profile_color = new TLRPC.TL_peerColor();
        r3.flags2 |= 256;
    L34:
        int r62 = this.selectedProfileColor;
        if (r62 < 0) goto L37;
        r42.flags |= 4;
        r42.color = r62;
        TLRPC.PeerColor r92 = r3.profile_color;
        r92.flags |= 1;
        r92.color = r62;
    L38:
        long r93 = this.selectedProfileEmoji;
        if (r93 == 0) goto L41;
        r42.flags |= 1;
        r42.background_emoji_id = r93;
        TLRPC.PeerColor r63 = r3.profile_color;
        r63.flags |= 2;
        r63.background_emoji_id = r93;
    L42:
        r0[0] = r0[0] + 1;
        getConnectionsManager().sendRequest(r42, new ChannelColorActivity$$ExternalSyntheticLambda9(this, r5));
    L44:
        if (ChatThemeController.wallpaperEquals(this.currentWallpaper, this.selectedWallpaper) == true) goto L64;
        TLRPC.TL_messages_setChatWallPaper r43 = new TLRPC.TL_messages_setChatWallPaper();
        r43.peer = getMessagesController().getInputPeer(this.dialogId);
        TLRPC.WallPaper r64 = this.selectedWallpaper;
        if (r64 == null) goto L56;
        boolean r65 = TextUtils.isEmpty(ChatThemeController.getWallpaperEmoticon(r64));
        int r94 = r43.flags;
        if (r65 == true) goto L50;
        r43.flags = r94 | 1;
        TLRPC.TL_inputWallPaperNoFile r66 = new TLRPC.TL_inputWallPaperNoFile();
        r43.wallpaper = r66;
        r66.id = 0;
        r43.flags |= 4;
        TLRPC.TL_wallPaperSettings r67 = new TLRPC.TL_wallPaperSettings();
        r43.settings = r67;
        r67.flags |= 128;
        r67.emoticon = ChatThemeController.getWallpaperEmoticon(this.selectedWallpaper);
        goto L56
    L50:
        r43.flags = r94 | 1;
        TLRPC.WallPaper r68 = this.selectedWallpaper;
        if ((r68 instanceof TLRPC.TL_wallPaper) == false) goto L54;
        TLRPC.TL_inputWallPaper r69 = new TLRPC.TL_inputWallPaper();
        TLRPC.WallPaper r7 = this.selectedWallpaper;
        r69.id = r7.id;
        r69.access_hash = r7.access_hash;
        r43.wallpaper = r69;
        goto L56
    L54:
        if ((r68 instanceof TLRPC.TL_wallPaperNoFile) == false) goto L56;
        TLRPC.TL_inputWallPaperNoFile r610 = new TLRPC.TL_inputWallPaperNoFile();
        r610.id = this.selectedWallpaper.id;
        r43.wallpaper = r610;
    L56:
        r0[0] = r0[0] + 1;
        getConnectionsManager().sendRequest(r43, new ChannelColorActivity$$ExternalSyntheticLambda10(this, r5));
        TLRPC.ChatFull r44 = getMessagesController().getChatFull(-this.dialogId);
        ChatThemeController.getInstance(this.currentAccount).saveChatWallpaper(this.dialogId, this.selectedWallpaper);
        if (r44 == null) goto L64;
        TLRPC.WallPaper r611 = this.selectedWallpaper;
        int r72 = r44.flags2;
        if (r611 != null) goto L61;
        r44.flags2 = r72 & (-129);
        r44.wallpaper = null;
    L62:
        getMessagesController().putChatFull(r44);
        NotificationCenter r612 = getNotificationCenter();
        int r73 = NotificationCenter.chatInfoDidLoad;
        Boolean r95 = Boolean.FALSE;
        r612.postNotificationName(r73, new Object[]{r44, 0, r95, r95});
        goto L64
    L61:
        r44.flags2 = r72 | 128;
        r44.wallpaper = r611;
    L64:
        if (DialogObject.emojiStatusesEqual(this.currentStatusEmoji, this.selectedStatusEmoji) == true) goto L77;
        TLRPC.TL_channels_updateEmojiStatus r45 = new TLRPC.TL_channels_updateEmojiStatus();
        r45.channel = getMessagesController().getInputChannel(-this.dialogId);
        TLRPC.EmojiStatus r613 = this.selectedStatusEmoji;
        if (r613 != null) goto L68;
    L74:
        r45.emoji_status = new TLRPC.TL_emojiStatusEmpty();
        r3.emoji_status = new TLRPC.TL_emojiStatusEmpty();
        r3.flags2 &= -513;
    L75:
        getMessagesController().updateEmojiStatusUntilUpdate(this.dialogId, this.selectedStatusEmoji);
        r0[0] = r0[0] + 1;
        getConnectionsManager().sendRequest(r45, new ChannelColorActivity$$ExternalSyntheticLambda11(this, r5));
        goto L77
    L68:
        if ((r613 instanceof TLRPC.TL_emojiStatusEmpty) == true) goto L74;
        if ((r613 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L73;
        TLRPC.TL_emojiStatusCollectible r614 = (TLRPC.TL_emojiStatusCollectible) r613;
        TLRPC.TL_inputEmojiStatusCollectible r74 = new TLRPC.TL_inputEmojiStatusCollectible();
        r74.collectible_id = r614.collectible_id;
        r74.flags = r614.flags;
        r74.until = r614.until;
        r45.emoji_status = r74;
        r3.emoji_status = this.selectedStatusEmoji;
        r3.flags |= 512;
        goto L75
    L73:
        r45.emoji_status = r613;
        r3.emoji_status = r613;
        r3.flags |= 512;
    L77:
        if (r0[0] != 0) goto L80;
        finishFragment();
        this.button.setLoading(false);
        return;
    L80:
        getMessagesController().putChat(r3, false);
        getNotificationCenter().postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_EMOJI_STATUS)});
        return;
    L41:
        TLRPC.PeerColor r615 = r3.profile_color;
        r615.flags &= -3;
        r615.background_emoji_id = 0;
        goto L42
    L37:
        r3.profile_color.flags &= -2;
        goto L38
    L30:
        if (this.currentProfileEmoji == this.selectedProfileEmoji) goto L44;
    L25:
        r10.flags = r12 & (-3);
        r10.background_emoji_id = 0;
        goto L26
    L18:
        if (this.currentReplyEmoji == this.selectedReplyEmoji) goto L28;
        goto L19
    }

    private TLRPC.Document getEmojiSetThumb(TLRPC.StickerSet r6) {
        if (r6 != null) goto L6;
        return null;
    L6:
        if (r6.thumb_document_id != 0) goto L11;
        TLRPC.TL_messages_stickerSet r5 = getMediaDataController().getGroupStickerSetById(r6);
        if (r5.documents.isEmpty() == true) goto L11;
        return r5.documents.get(0);
    L11:
        return null;
    }

    private long getEmojiSetThumbId(TLRPC.StickerSet r5) {
        if (r5 != null) goto L5;
        return 0;
    L5:
        long r2 = r5.thumb_document_id;
        if (r2 != 0) goto L11;
        TLRPC.TL_messages_stickerSet r4 = getMediaDataController().getGroupStickerSetById(r5);
        if (r4.documents.isEmpty() == true) goto L11;
        return r4.documents.get(0).id;
    L11:
        return r2;
    }

    private String getThemeChooserEmoticon() {
        String r0 = ChatThemeController.getWallpaperEmoticon(this.selectedWallpaper);
        if (r0 == null) goto L5;
    L10:
        return r0;
    L5:
        if (this.selectedWallpaper != null) goto L10;
        if (this.galleryWallpaper == null) goto L10;
        return "❌";
    }

    private /* synthetic */ void lambda$buttonClick$10(Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
        if ((r3 instanceof TLRPC.Updates) == false) goto L5;
        getMessagesController().processUpdates((TLRPC.Updates) r3, false);
    L5:
        if (r2 == null) goto L8;
        r2.run(r4);
        return;
    }

    private /* synthetic */ void lambda$buttonClick$11(Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
        if ((r3 instanceof TLRPC.Updates) == false) goto L5;
        getMessagesController().processUpdates((TLRPC.Updates) r3, false);
    L5:
        if (r2 == null) goto L8;
        r2.run(r4);
        return;
    }

    private /* synthetic */ void lambda$buttonClick$6(boolean[] r4, int[] r5, int[] r6, TLRPC.TL_error r7) {
        if (r4[0] == true) goto L19;
        int r1 = r5[0];
        if (r1 < r6[0]) goto L8;
        return;
    L8:
        if (r7 == null) goto L15;
        r4[0] = true;
        if ("BOOSTS_REQUIRED".equals(r7.text) == false) goto L13;
        showLimit();
        return;
    L13:
        this.button.setLoading(false);
        BulletinFactory.of(this).createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r7.text})).show();
        return;
    L15:
        int r12 = r1 + 1;
        r5[0] = r12;
        if (r12 != r6[0]) goto L20;
        finishFragment();
        showBulletin();
        this.button.setLoading(false);
        return;
    L20:
        return;
    }

    private /* synthetic */ void lambda$buttonClick$7(boolean[] r7, int[] r8, int[] r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new ChannelColorActivity$$ExternalSyntheticLambda16(this, r7, r8, r9, r10));
    }

    private /* synthetic */ void lambda$buttonClick$8(Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
        if ((r3 instanceof TLRPC.Updates) == false) goto L5;
        getMessagesController().processUpdates((TLRPC.Updates) r3, false);
    L5:
        if (r2 == null) goto L8;
        r2.run(r4);
        return;
    }

    private /* synthetic */ void lambda$buttonClick$9(Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
        if ((r3 instanceof TLRPC.Updates) == false) goto L5;
        getMessagesController().processUpdates((TLRPC.Updates) r3, false);
    L5:
        if (r2 == null) goto L8;
        r2.run(r4);
        return;
    }

    private /* synthetic */ void lambda$createView$1(int r7, View r8, Long r9, Integer r10, TL_stars.TL_starGiftUnique r11) {
        if (r7 != this.replyEmojiRow) goto L6;
        this.selectedReplyEmoji = r9.longValue();
        updateMessagesPreview(true);
    L23:
        updateButton(true);
        EmojiCell r82 = (EmojiCell) r8;
        long r92 = r9.longValue();
        if (r11 == null) goto L26;
        boolean r72 = true;
    L27:
        r82.setEmoji(r92, r72, true);
        updateColors(true);
        return;
    L26:
        r72 = false;
        goto L27
    L6:
        if (r7 != this.profileEmojiRow) goto L9;
        this.selectedProfileEmoji = r9.longValue();
        updateProfilePreview(true);
        goto L23
    L9:
        if (r7 != this.statusEmojiRow) goto L23;
        if (r9.longValue() != 0) goto L13;
        this.selectedStatusEmoji = null;
    L22:
        updateProfilePreview(true);
        goto L23
    L13:
        if (r11 == null) goto L18;
        TLRPC.TL_emojiStatusCollectible r73 = MessagesController.emojiStatusCollectibleFromGift(r11);
        if (r10 == null) goto L17;
        r73.flags |= 1;
        r73.until = r10.intValue();
    L17:
        this.selectedStatusEmoji = r73;
        this.selectedProfileColor = -1;
        this.selectedProfileEmoji = 0;
        goto L22
    L18:
        TLRPC.TL_emojiStatus r74 = new TLRPC.TL_emojiStatus();
        r74.document_id = r9.longValue();
        if (r10 == null) goto L21;
        r74.flags |= 1;
        r74.until = r10.intValue();
    L21:
        this.selectedStatusEmoji = r74;
        goto L22
    }

    private /* synthetic */ void lambda$createView$2() {
        BulletinFactory.of(this).createSimpleBulletin(R.raw.done, LocaleController.getString(R.string.ChannelWallpaperUpdated)).show();
    }

    private /* synthetic */ void lambda$createView$3(TLRPC.WallPaper r3) {
        this.currentWallpaper = r3;
        this.selectedWallpaper = r3;
        this.galleryWallpaper = r3;
        updateButton(false);
        updateMessagesPreview(false);
        AndroidUtilities.runOnUIThread(new ChannelColorActivity$$ExternalSyntheticLambda14(this), 350);
    }

    private /* synthetic */ void lambda$createView$4(TLRPC.ChatFull r11, View r12, int r13) {
        long r5 = 0;
        boolean r7 = true;
        if ((r12 instanceof EmojiCell) == false) goto L44;
        if (r13 != this.packStickerRow) goto L11;
        if (r11 == null) goto L55;
        GroupStickersActivity r2 = new GroupStickersActivity(-this.dialogId);
        r2.setInfo(r11);
        presentFragment(r2);
        return;
    L55:
        return;
    L11:
        if (r13 != this.replyEmojiRow) goto L14;
        r5 = this.selectedReplyEmoji;
    L23:
        if (r13 != this.packEmojiRow) goto L32;
        int r22 = getEmojiStickersLevelMin();
        TL_stories.TL_premium_boostsStatus r3 = this.boostsStatus;
        if (r3 != null) goto L27;
    L30:
        GroupStickersActivity r23 = new GroupStickersActivity(-this.dialogId, true);
        r23.setInfo(r11);
        presentFragment(r23);
        return;
    L27:
        if (r3.level >= r22) goto L30;
        openBoostDialog(29);
        return;
    L32:
        EmojiCell r1 = (EmojiCell) r12;
        if (r13 != this.statusEmojiRow) goto L35;
    L34:
        boolean r4 = r7;
        long r6 = r5;
        Utilities.Callback3<Long, Integer, TL_stars.TL_starGiftUnique> r52 = new ChannelColorActivity$$ExternalSyntheticLambda12(this, r13, r12);
        if ((this.selectedStatusEmoji instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L40;
        int r24 = Theme.getColor(Theme.key_windowBackgroundWhiteBlueIcon, this.resourceProvider);
    L41:
        showSelectStatusDialog(r1, r6, r4, r52, r24);
        return;
    L40:
        r24 = r1.getColor();
        goto L41
    L35:
        r7 = false;
        goto L34
    L14:
        if (r13 != this.profileEmojiRow) goto L17;
        r5 = this.selectedProfileEmoji;
        goto L23
    L17:
        if (r13 != this.statusEmojiRow) goto L23;
        TLRPC.EmojiStatus r42 = this.selectedStatusEmoji;
        if ((r42 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L21;
        r5 = ((TLRPC.TL_emojiStatusCollectible) r42).collectible_id;
        goto L23
    L21:
        r5 = DialogObject.getEmojiStatusDocumentId(r42);
        goto L23
    L44:
        if (r13 != this.removeProfileColorRow) goto L51;
        this.selectedProfileColor = -1;
        this.selectedProfileEmoji = 0;
        if ((this.selectedStatusEmoji instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L48;
        this.selectedStatusEmoji = null;
    L48:
        updateProfilePreview(true);
        updateButton(true);
        updateRows();
        updateColors(true);
        return;
    L51:
        if (r13 != this.wallpaperRow) goto L54;
        ChatThemeBottomSheet.openGalleryForBackground(getParentActivity(), this, this.dialogId, this.resourceProvider, new ChannelColorActivity$$ExternalSyntheticLambda13(this), new AnonymousClass2(this), this.boostsStatus);
        return;
    }

    private /* synthetic */ void lambda$createView$5(View r1) {
        buttonClick();
    }

    private /* synthetic */ void lambda$new$0(TLRPC.Chat r2, TL_stories.TL_premium_boostsStatus r3) {
        this.boostsStatus = r3;
        if (r3 == null) goto L7;
        int r32 = r3.level;
        this.currentLevel = r32;
        if (r2 == null) goto L7;
        r2.flags |= 1024;
        r2.level = r32;
    L7:
        updateButton(true);
        ButtonWithCounterView r1 = this.button;
        if (r1 == null) goto L11;
        r1.setLoading(false);
        return;
    }

    private /* synthetic */ void lambda$showLimit$12(TLRPC.Chat r1) {
        presentFragment(StatisticActivity.create(r1));
    }

    private /* synthetic */ void lambda$showLimit$13(ChannelBoostsController.CanApplyBoost r13) {
        MessagesController.PeerColor r2 = null;
        if (this.currentReplyColor == this.selectedReplyColor) goto L12;
        MessagesController.PeerColors r0 = getMessagesController().peerColors;
        if (r0 != null) goto L7;
        MessagesController.PeerColor r02 = null;
    L8:
        if (r02 == null) goto L12;
        if (r02.getLvl(this.isGroup) <= this.currentLevel) goto L12;
        int r03 = r02.getLvl(this.isGroup);
    L14:
        if (this.currentProfileColor == this.selectedProfileColor) goto L24;
        MessagesController.PeerColors r1 = getMessagesController().profilePeerColors;
        if (r1 == null) goto L19;
        r2 = r1.getColor(this.selectedProfileColor);
    L19:
        if (r2 == null) goto L24;
        if (r2.getLvl(this.isGroup) <= this.currentLevel) goto L24;
        r03 = r2.getLvl(this.isGroup);
        int r12 = 24;
    L23:
        int r11 = r03;
        if (this.currentReplyEmoji == this.selectedReplyEmoji) goto L31;
        if (getMessagesController().channelBgIconLevelMin <= this.currentLevel) goto L31;
        r12 = 27;
    L31:
        if (this.currentProfileEmoji == this.selectedProfileEmoji) goto L36;
        if (getProfileIconLevelMin() <= this.currentLevel) goto L36;
        r12 = 28;
    L36:
        if (DialogObject.emojiStatusesEqual(this.currentStatusEmoji, this.selectedStatusEmoji) == true) goto L44;
        if (getEmojiStatusLevelMin() <= this.currentLevel) goto L44;
        if ((this.selectedStatusEmoji instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L42;
        r12 = 26;
        goto L44
    L42:
        r12 = 25;
    L44:
        if (ChatThemeController.wallpaperEquals(this.currentWallpaper, this.selectedWallpaper) == false) goto L46;
    L48:
        int r8 = r12;
        if (getContext() != null) goto L53;
        return;
    L53:
        if (getParentActivity() == null) goto L61;
        AnonymousClass4 r4 = new AnonymousClass4(this, this, getContext(), r8, this.currentAccount, getResourceProvider(), r11);
        r4.setCanApplyBoost(r13);
        r4.setBoostsStats(this.boostsStatus, true);
        r4.setDialogId(this.dialogId);
        TLRPC.Chat r122 = getMessagesController().getChat(Long.valueOf(-this.dialogId));
        if (r122 == null) goto L58;
        r4.showStatisticButtonInLink(new ChannelColorActivity$$ExternalSyntheticLambda19(this, r122));
    L58:
        showDialog(r4);
        this.button.setLoading(false);
        return;
    L61:
        return;
    L46:
        if (TextUtils.isEmpty(ChatThemeController.getWallpaperEmoticon(this.selectedWallpaper)) == true) goto L49;
        r12 = 22;
        goto L48
    L49:
        r12 = 23;
    L24:
        r12 = 20;
        goto L23
    L7:
        r02 = r0.getColor(this.selectedReplyColor);
    L12:
        r03 = 0;
        goto L14
    }

    private /* synthetic */ void lambda$showUnsavedAlert$14(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$showUnsavedAlert$15(AlertDialog r1, int r2) {
        buttonClick();
    }

    private /* synthetic */ void lambda$toggleTheme$17() {
        Theme.ResourcesProvider r0 = this.resourceProvider;
        if ((r0 instanceof ThemeDelegate) == false) goto L5;
        ((ThemeDelegate) r0).toggle();
    L6:
        setForceDark(this.isDark, true);
        updateColors(false);
        return;
    L5:
        this.isDark = !this.isDark;
        updateThemeColors();
        goto L6
    }

    private void showBulletin() {
        BaseFragment r0 = this.bulletinFragment;
        if (r0 != null) goto L5;
        return;
    L5:
        if ((r0 instanceof ChatEditActivity) == false) goto L7;
        ((ChatEditActivity) r0).updateColorCell();
    L7:
        BulletinFactory r02 = BulletinFactory.of(this.bulletinFragment);
        int r1 = R.raw.contact_check;
        if (this.isGroup == false) goto L10;
        int r2 = R.string.GroupAppearanceUpdated;
    L11:
        r02.createSimpleBulletin(r1, LocaleController.getString(r2)).show();
        this.bulletinFragment = null;
        return;
    L10:
        r2 = R.string.ChannelAppearanceUpdated;
        goto L11
    }

    private void showLimit() {
        getMessagesController().getBoostsController().userCanBoostChannel(this.dialogId, this.boostsStatus, new ChannelColorActivity$$ExternalSyntheticLambda15(this));
    }

    private void showUnsavedAlert() {
        if (getVisibleDialog() == null) goto L5;
        return;
    L5:
        AlertDialog r0 = new AlertDialog.Builder(getContext(), getResourceProvider()).setTitle(LocaleController.getString(R.string.ChannelColorUnsaved)).setMessage(LocaleController.getString(R.string.ChannelColorUnsavedMessage)).setNegativeButton(LocaleController.getString(R.string.Dismiss), new ChannelColorActivity$$ExternalSyntheticLambda3(this)).setPositiveButton(LocaleController.getString(R.string.ApplyTheme), new ChannelColorActivity$$ExternalSyntheticLambda4(this)).create();
        showDialog(r0);
        ((TextView) r0.getButton(-2)).setTextColor(getThemedColor(Theme.key_text_RedBold));
    }

    private void updateBoostsAndLevels(TL_stories.TL_premium_boostsStatus r4) {
        if (r4 == null) goto L11;
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(-this.dialogId));
        this.boostsStatus = r4;
        int r42 = r4.level;
        this.currentLevel = r42;
        if (r0 == null) goto L6;
        r0.level = r42;
    L6:
        Adapter r43 = this.adapter;
        if (r43 == null) goto L9;
        r43.notifyDataSetChanged();
    L9:
        updateButton(true);
        return;
    }

    private void updateColors(View r1) {
        if ((r1 instanceof EmojiCell) == false) goto L7;
        ((EmojiCell) r1).updateColors();
        return;
    L7:
        if ((r1 instanceof TextCell) == false) goto L11;
        ((TextCell) r1).updateColors();
        return;
    L11:
        if ((r1 instanceof PeerColorPicker) == false) goto L15;
        ((PeerColorPicker) r1).updateColors();
        return;
    L15:
        if ((r1 instanceof ThemeChooser) == false) goto L18;
        ((ThemeChooser) r1).updateColors();
        return;
    }

    public void createListView() {
        RecyclerListView r0 = new RecyclerListView(getContext(), this.resourceProvider);
        this.listView = r0;
        r0.setSections(false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r13) {
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(-this.dialogId));
        if (r0 == null) goto L5;
        int r1 = ChatObject.getColorId(r0);
        this.selectedReplyColor = r1;
        this.currentReplyColor = r1;
        long r12 = ChatObject.getEmojiId(r0);
        this.selectedReplyEmoji = r12;
        this.currentReplyEmoji = r12;
        int r14 = ChatObject.getProfileColorId(r0);
        this.selectedProfileColor = r14;
        this.currentProfileColor = r14;
        long r15 = ChatObject.getProfileEmojiId(r0);
        this.selectedProfileEmoji = r15;
        this.currentProfileEmoji = r15;
        TLRPC.EmojiStatus r02 = r0.emoji_status;
        this.selectedStatusEmoji = r02;
        this.currentStatusEmoji = r02;
    L5:
        TLRPC.ChatFull r03 = getMessagesController().getChatFull(-this.dialogId);
        if (r03 == null) goto L10;
        TLRPC.WallPaper r16 = r03.wallpaper;
        this.selectedWallpaper = r16;
        this.currentWallpaper = r16;
        if (ChatThemeController.isNotEmoticonWallpaper(r16) == false) goto L10;
        this.galleryWallpaper = this.currentWallpaper;
    L10:
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setTitle(LocaleController.getString(R.string.ChannelColorTitle2));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        RLottieDrawable r3 = new RLottieDrawable(R.raw.sun, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), true, null);
        this.sunDrawable = r3;
        r3.setPlayInDirectionOfCustomEndFrame(true);
        boolean r2 = this.isDark;
        RLottieDrawable r32 = this.sunDrawable;
        if (r2 == true) goto L13;
        r32.setCustomEndFrame(0);
        this.sunDrawable.setCurrentFrame(0);
    L14:
        this.sunDrawable.beginApplyLayerColors();
        int r22 = Theme.getColor(Theme.key_chats_menuName, this.resourceProvider);
        this.sunDrawable.setLayerColor("Sunny", r22);
        this.sunDrawable.setLayerColor("Path 6", r22);
        this.sunDrawable.setLayerColor("Path", r22);
        this.sunDrawable.setLayerColor("Path 5", r22);
        this.dayNightItem = this.actionBar.createMenu().addItem(1, this.sunDrawable);
        FrameLayout r17 = new FrameLayout(r13);
        updateRows();
        createListView();
        if (this.isGroup == true) goto L17;
        this.actionBar.setAdaptiveBackground(this.listView);
    L17:
        RecyclerListView r23 = this.listView;
        Adapter r33 = new Adapter(this);
        this.adapter = r33;
        r23.setAdapter(r33);
        this.layoutManager = new GridLayoutManager(r13, 3);
        this.listView.setLayoutManager(new LinearLayoutManager(r13));
        RecyclerListView r24 = this.listView;
        int r34 = Theme.key_windowBackgroundGray;
        r24.setBackgroundColor(getThemedColor(r34));
        r17.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 0.0f, 68.0f));
        this.listView.setOnItemClickListener(new ChannelColorActivity$$ExternalSyntheticLambda1(this, r03));
        DefaultItemAnimator r04 = new DefaultItemAnimator();
        r04.setDurations(350);
        r04.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r04.setDelayAnimations(false);
        r04.setSupportsChangeAnimations(false);
        this.listView.setItemAnimator(r04);
        ButtonWithCounterView r05 = new ButtonWithCounterView(r13, this.resourceProvider).setRound();
        this.button = r05;
        r05.setText(LocaleController.getString(R.string.ApplyChanges), false);
        this.button.setOnClickListener(new ChannelColorActivity$$ExternalSyntheticLambda2(this));
        updateButton(false);
        FrameLayout r06 = new FrameLayout(r13);
        this.buttonContainer = r06;
        r06.setBackgroundColor(getThemedColor(r34));
        this.buttonContainer.addView(this.button, LayoutHelper.createFrame(-1, 48.0f, 80, 10.0f, 10.0f, 10.0f, 10.0f));
        r17.addView(this.buttonContainer, LayoutHelper.createFrame(-1, 68, 80));
        Bulletin.addDelegate(this, new AnonymousClass3(this));
        this.fragmentView = r17;
        return r17;
    L13:
        r32.setCurrentFrame(35);
        this.sunDrawable.setCustomEndFrame(36);
        goto L14
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r4, int r5, Object... r6) {
        if (r4 != NotificationCenter.chatWasBoostedByUser) goto L9;
        if (this.dialogId != ((Long) r6[2]).longValue()) goto L26;
        updateBoostsAndLevels((TL_stories.TL_premium_boostsStatus) r6[0]);
        return;
    L26:
        return;
    L9:
        if (r4 != NotificationCenter.boostByChannelCreated) goto L15;
        if (((Boolean) r6[1]).booleanValue() == true) goto L27;
        getMessagesController().getBoostsController().getBoostsStats(this.dialogId, new ChannelColorActivity$$ExternalSyntheticLambda6(this));
        return;
    L27:
        return;
    L15:
        if (r4 != NotificationCenter.dialogDeleted) goto L28;
        long r42 = ((Long) r6[0]).longValue();
        if (this.dialogId != r42) goto L29;
        INavigationLayout r43 = this.parentLayout;
        if (r43 != null) goto L21;
    L24:
        removeSelfFromStack();
        return;
    L21:
        if (r43.getLastFragment() != this) goto L24;
        finishFragment();
        return;
    L29:
        return;
    }

    public View findChildAt(int r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.listView.getChildCount()) goto L9;
        View r1 = this.listView.getChildAt(r0);
        if (this.listView.getChildAdapterPosition(r1) == r4) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return null;
    }

    public int getCustomWallpaperLevelMin() {
        return getMessagesController().channelCustomWallpaperLevelMin;
    }

    public int getEmojiPackInfoStrRes() {
        return 0;
    }

    public int getEmojiPackStrRes() {
        return 0;
    }

    public int getEmojiStatusInfoStrRes() {
        return R.string.ChannelEmojiStatusInfo;
    }

    public int getEmojiStatusLevelMin() {
        return getMessagesController().channelEmojiStatusLevelMin;
    }

    public int getEmojiStatusStrRes() {
        return R.string.ChannelEmojiStatus;
    }

    public int getEmojiStickersLevelMin() {
        return 0;
    }

    public int getMessagePreviewType() {
        return 3;
    }

    public int getProfileIconLevelMin() {
        return getMessagesController().channelProfileIconLevelMin;
    }

    public int getProfileInfoStrRes() {
        return R.string.ChannelProfileInfo;
    }

    public int getStickerPackInfoStrRes() {
        return 0;
    }

    public int getStickerPackStrRes() {
        return 0;
    }

    public int getWallpaper2InfoStrRes() {
        return R.string.ChannelWallpaper2Info;
    }

    public int getWallpaperLevelMin() {
        return getMessagesController().channelWallpaperLevelMin;
    }

    public int getWallpaperStrRes() {
        return R.string.ChannelWallpaper;
    }

    public boolean hasUnsavedChanged() {
        if (this.currentReplyColor == this.selectedReplyColor) goto L5;
        return true;
    L5:
        if (this.currentReplyEmoji == this.selectedReplyEmoji) goto L7;
        return true;
    L7:
        if (this.currentProfileColor == this.selectedProfileColor) goto L9;
        return true;
    L9:
        if (this.currentProfileEmoji == this.selectedProfileEmoji) goto L11;
        return true;
    L11:
        if (DialogObject.emojiStatusesEqual(this.currentStatusEmoji, this.selectedStatusEmoji) == true) goto L13;
        return true;
    L13:
        if (ChatThemeController.wallpaperEquals(this.currentWallpaper, this.selectedWallpaper) == false) goto L23;
        return false;
    L23:
        return true;
    }

    public boolean isForum() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r1) {
        if (hasUnsavedChanged() == true) goto L5;
        return true;
    L5:
        if (this.currentLevel < minLevelRequired()) goto L11;
        return false;
    L11:
        return true;
    }

    public int minLevelRequired() {
        MessagesController.PeerColor r2 = null;
        int r3 = 0;
        if (this.currentReplyColor == this.selectedReplyColor) goto L11;
        MessagesController.PeerColors r0 = getMessagesController().peerColors;
        if (r0 != null) goto L7;
        MessagesController.PeerColor r02 = null;
    L8:
        if (r02 == null) goto L11;
        r3 = Math.max(0, r02.getLvl(this.isGroup));
        goto L11
    L7:
        r02 = r0.getColor(this.selectedReplyColor);
    L11:
        if (this.currentReplyEmoji == this.selectedReplyEmoji) goto L14;
        r3 = Math.max(r3, getMessagesController().channelBgIconLevelMin);
    L14:
        if (this.currentProfileColor == this.selectedProfileColor) goto L22;
        MessagesController.PeerColors r03 = getMessagesController().profilePeerColors;
        if (r03 == null) goto L19;
        r2 = r03.getColor(this.selectedProfileColor);
    L19:
        if (r2 == null) goto L22;
        r3 = Math.max(r3, r2.getLvl(this.isGroup));
    L22:
        if (this.currentProfileEmoji == this.selectedProfileEmoji) goto L25;
        r3 = Math.max(r3, getProfileIconLevelMin());
    L25:
        if (DialogObject.emojiStatusesEqual(this.currentStatusEmoji, this.selectedStatusEmoji) == true) goto L28;
        r3 = Math.max(r3, getEmojiStatusLevelMin());
    L28:
        if (ChatThemeController.wallpaperEquals(this.currentWallpaper, this.selectedWallpaper) == false) goto L30;
        return r3;
    L30:
        return Math.max(r3, getWallpaperLevelMin());
    }

    public boolean needBoostInfoSection() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r3) {
        if (this.currentLevel < minLevelRequired()) goto L11;
        if (hasUnsavedChanged() == false) goto L11;
        if (r3 == false) goto L12;
        showUnsavedAlert();
        return false;
    L12:
        return false;
    L11:
        return super.onBackPressed(r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getMediaDataController().loadRestrictedStatusEmojis();
        getNotificationCenter().addObserver(this, NotificationCenter.boostByChannelCreated);
        getNotificationCenter().addObserver(this, NotificationCenter.chatWasBoostedByUser);
        getNotificationCenter().addObserver(this, NotificationCenter.dialogDeleted);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.boostByChannelCreated);
        getNotificationCenter().removeObserver(this, NotificationCenter.chatWasBoostedByUser);
        getNotificationCenter().removeObserver(this, NotificationCenter.dialogDeleted);
        Bulletin.removeDelegate(this);
    }

    public void openBoostDialog(int r1) {
    }

    public void setForceDark(boolean r3, boolean r4) {
        if (this.forceDark == r3) goto L24;
        this.forceDark = r3;
        int r0 = 0;
        if (r4 == false) goto L15;
        RLottieDrawable r42 = this.sunDrawable;
        if (r3 == false) goto L10;
        r0 = r42.getFramesCount();
    L10:
        r42.setCustomEndFrame(r0);
        RLottieDrawable r2 = this.sunDrawable;
        if (r2 == null) goto L22;
        r2.start();
        return;
    L22:
        return;
    L15:
        if (r3 == false) goto L17;
        int r32 = this.sunDrawable.getFramesCount() - 1;
    L18:
        this.sunDrawable.setCurrentFrame(r32, false, true);
        this.sunDrawable.setCustomEndFrame(r32);
        ActionBarMenuItem r22 = this.dayNightItem;
        if (r22 == null) goto L23;
        r22.invalidate();
        return;
    L23:
        return;
    L17:
        r32 = 0;
        goto L18
    }

    public ChannelColorActivity setOnApplied(BaseFragment r1) {
        this.bulletinFragment = r1;
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void setResourceProvider(Theme.ResourcesProvider r1) {
        this.parentResourcesProvider = r1;
    }

    public void showSelectStatusDialog(EmojiCell r19, long r20, boolean r22, Utilities.Callback3<Long, Integer, TL_stars.TL_starGiftUnique> r23, int r24) {
        if (this.selectAnimatedEmojiDialog != null) goto L37;
        if (r19 == null) goto L38;
        SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] r12 = new SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[1];
        if ((r19.getTop() + r19.getHeight()) <= (this.listView.getMeasuredHeight() / 2.0f)) goto L9;
        boolean r0 = true;
    L10:
        int r2 = (int) Math.min(AndroidUtilities.dp(330.0f), AndroidUtilities.displaySize.y * 0.75f);
        int r3 = (int) Math.min(AndroidUtilities.dp(324.0f), AndroidUtilities.displaySize.x * 0.95f);
        EmojiCell.m7300$$Nest$fgetimageDrawable(r19).removeOldDrawable();
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r4 = EmojiCell.m7300$$Nest$fgetimageDrawable(r19);
        if (EmojiCell.m7300$$Nest$fgetimageDrawable(r19) == null) goto L17;
        EmojiCell.m7300$$Nest$fgetimageDrawable(r19).play();
        r19.updateImageBounds();
        Rect r5 = AndroidUtilities.rectTmp2;
        r5.set(EmojiCell.m7300$$Nest$fgetimageDrawable(r19).getBounds());
        if (r0 == false) goto L15;
        int r6 = ((-r5.centerY()) + AndroidUtilities.dp(12.0f)) - r2;
    L16:
        int r25 = r5.centerX() - (AndroidUtilities.displaySize.x - r3);
    L18:
        if (r22 == false) goto L23;
        if (r0 == false) goto L21;
        int r32 = 10;
    L22:
        boolean r52 = r0;
        int r7 = r6;
        int r62 = r32;
        Context r33 = getContext();
        Integer r26 = Integer.valueOf(r25);
        Theme.ResourcesProvider r8 = getResourceProvider();
        if (r52 == false) goto L30;
        int r53 = 24;
    L31:
        AnonymousClass5 r02 = new AnonymousClass5(this, this, r33, true, r26, r62, true, r8, r53, r24, r23, r12);
        r02.useAccentForPlus = true;
        if (r20 != 0) goto L34;
        Long r27 = null;
    L35:
        r02.setSelected(r27);
        r02.setSaveState(3);
        r02.setScrimDrawable(r4, r19);
        AnonymousClass6 r28 = new AnonymousClass6(this, r02, -2, -2);
        this.selectAnimatedEmojiDialog = r28;
        r12[0] = r28;
        r28.showAsDropDown(r19, 0, r7, 53);
        r12[0].dimBehind();
        return;
    L34:
        r27 = Long.valueOf(r20);
        goto L35
    L30:
        r53 = 16;
        goto L31
    L21:
        r32 = 9;
        goto L22
    L23:
        if (r0 == false) goto L25;
        r32 = 5;
        goto L22
    L25:
        r32 = 7;
        goto L22
    L15:
        r6 = (-(r19.getHeight() - r5.centerY())) - AndroidUtilities.dp(16.0f);
        goto L16
    L17:
        r25 = 0;
        r6 = 0;
        goto L18
    L9:
        r0 = false;
        goto L10
    L38:
        return;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void toggleTheme() {
        FrameLayout r12 = (FrameLayout) getParentActivity().getWindow().getDecorView();
        Bitmap r8 = Bitmap.createBitmap(r12.getWidth(), r12.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas r3 = new Canvas(r8);
        this.dayNightItem.setAlpha(0.0f);
        r12.draw(r3);
        this.dayNightItem.setAlpha(1.0f);
        Paint r7 = new Paint(1);
        r7.setColor(-16777216);
        r7.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint r9 = new Paint(1);
        r9.setFilterBitmap(true);
        int[] r2 = new int[2];
        this.dayNightItem.getLocationInWindow(r2);
        float r10 = r2[0];
        float r11 = r2[1];
        float r6 = Math.max(r8.getHeight(), r8.getWidth()) + AndroidUtilities.navigationBarHeight;
        Shader.TileMode r22 = Shader.TileMode.CLAMP;
        r9.setShader(new BitmapShader(r8, r22, r22));
        AnonymousClass7 r0 = new AnonymousClass7(this, getContext(), r3, r10 + (this.dayNightItem.getMeasuredWidth() / 2.0f), r11 + (this.dayNightItem.getMeasuredHeight() / 2.0f), r6, r7, r8, r9, r10, r11);
        this.changeDayNightView = r0;
        r0.setOnTouchListener(new ChannelColorActivity$$ExternalSyntheticLambda17());
        this.changeDayNightViewProgress = 0.0f;
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.changeDayNightViewAnimator = r02;
        r02.addUpdateListener(new AnonymousClass8(this));
        this.changeDayNightViewAnimator.addListener(new AnonymousClass9(this));
        this.changeDayNightViewAnimator.setDuration(400);
        this.changeDayNightViewAnimator.setInterpolator(Easings.easeInOutQuad);
        this.changeDayNightViewAnimator.start();
        r12.addView(this.changeDayNightView, new ViewGroup.LayoutParams(-1, -1));
        AndroidUtilities.runOnUIThread(new ChannelColorActivity$$ExternalSyntheticLambda18(this));
    }

    public void updateButton(boolean r7) {
        if (this.button != null) goto L5;
        return;
    L5:
        if (this.boostsStatus == null) goto L17;
        int r0 = minLevelRequired();
        if (this.currentLevel < r0) goto L12;
        this.button.setSubText(null, r7);
        return;
    L12:
        if (this.lock != null) goto L14;
        this.lock = new SpannableStringBuilder("l");
        ColoredImageSpan r1 = new ColoredImageSpan(R.drawable.mini_switch_lock);
        r1.setTopOffset(1);
        this.lock.setSpan(r1, 0, 1, 33);
    L14:
        SpannableStringBuilder r12 = new SpannableStringBuilder();
        r12.append(this.lock).append(LocaleController.formatPluralString("BoostLevelRequired", r0, new Object[0]));
        this.button.setSubText(r12, r7);
        return;
    }

    public void updateColors(boolean r4) {
        this.actionBar.setBackgroundColor(getThemedColor(Theme.key_actionBarDefault));
        ActionBar r0 = this.actionBar;
        int r2 = -1;
        if (this.isGroup == true) goto L5;
    L7:
        int r1 = getThemedColor(Theme.key_actionBarDefaultTitle);
    L8:
        r0.setTitleColor(r1);
        ActionBar r02 = this.actionBar;
        if (this.isGroup == true) goto L11;
    L13:
        r2 = getThemedColor(Theme.key_actionBarDefaultIcon);
    L14:
        r02.setItemsColor(r2, false);
        this.actionBar.setItemsBackgroundColor(getThemedColor(Theme.key_actionBarDefaultSelector), false);
        if (r4 == true) goto L18;
        RecyclerListView r42 = this.listView;
        int r03 = Theme.key_windowBackgroundGray;
        r42.setBackgroundColor(getThemedColor(r03));
        this.adapter.notifyDataSetChanged();
        AndroidUtilities.forEachViews(this.listView, new ChannelColorActivity$$ExternalSyntheticLambda0(this));
        this.buttonContainer.setBackgroundColor(getThemedColor(r03));
        this.button.updateColors();
        setNavigationBarColor(getNavigationBarColor());
        return;
    L18:
        return;
    L11:
        if (this.selectedProfileColor == (-1)) goto L13;
    L5:
        if (this.selectedProfileColor == (-1)) goto L7;
        r1 = -1;
        goto L8
    }

    public void updateMessagesPreview(boolean r11) {
        View r0 = findChildAt(this.messagesPreviewRow);
        View r1 = findChildAt(this.replyColorListRow);
        View r2 = findChildAt(this.replyEmojiRow);
        View r3 = findChildAt(this.wallpaperThemesRow);
        if ((r0 instanceof ThemePreviewMessagesCell) == false) goto L15;
        ThemePreviewMessagesCell r02 = (ThemePreviewMessagesCell) r0;
        ChatMessageCell[] r4 = r02.getCells();
        int r6 = 0;
    L6:
        if (r6 >= r4.length) goto L13;
        ChatMessageCell r7 = r4[r6];
        if (r7 == null) goto L12;
        MessageObject r72 = r7.getMessageObject();
        if (r72 == null) goto L12;
        r72.overrideLinkColor = this.selectedReplyColor;
        r72.overrideLinkEmoji = this.selectedReplyEmoji;
        r4[r6].setAvatar(r72);
        r4[r6].invalidate();
    L12:
        r6 = r6 + 1;
        goto L6
    L13:
        Drawable r42 = PreviewView.getBackgroundDrawable(this.backgroundDrawable, this.currentAccount, this.selectedWallpaper, this.isDark);
        this.backgroundDrawable = r42;
        r02.setOverrideBackground(r42);
    L15:
        if ((r1 instanceof PeerColorActivity.PeerColorGrid) == false) goto L18;
        ((PeerColorActivity.PeerColorGrid) r1).setSelected(this.selectedReplyColor, r11);
    L21:
        if ((r2 instanceof EmojiCell) == false) goto L24;
        EmojiCell r22 = (EmojiCell) r2;
        r22.setAdaptiveEmojiColor(this.currentAccount, this.selectedReplyColor, true);
        r22.setEmoji(this.selectedReplyEmoji, false, r11);
    L24:
        if ((r3 instanceof ThemeChooser) == false) goto L31;
        ThemeChooser r32 = (ThemeChooser) r3;
        r32.setSelectedEmoticon(getThemeChooserEmoticon(), r11);
        r32.setGalleryWallpaper(this.galleryWallpaper);
        return;
    L31:
        return;
    L18:
        if ((r1 instanceof PeerColorPicker) == false) goto L21;
        ((PeerColorPicker) r1).setSelected(this.selectedReplyColor, r11);
        goto L21
    }

    public void updateProfilePreview(boolean r12) {
        View r0 = findChildAt(this.profilePreviewRow);
        View r1 = findChildAt(this.profileColorGridRow);
        View r2 = findChildAt(this.profileEmojiRow);
        View r3 = findChildAt(this.statusEmojiRow);
        View r4 = findChildAt(this.packEmojiRow);
        View r5 = findChildAt(this.packStickerRow);
        if ((r0 instanceof ProfilePreview) == false) goto L10;
        TLRPC.EmojiStatus r6 = this.selectedStatusEmoji;
        if ((r6 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L7;
        ProfilePreview r8 = (ProfilePreview) r0;
        r8.setColor(MessagesController.PeerColor.fromCollectible(r6), r12);
        r8.setEmoji(((TLRPC.TL_emojiStatusCollectible) this.selectedStatusEmoji).pattern_document_id, true, r12);
    L8:
        ProfilePreview r02 = (ProfilePreview) r0;
        r02.setEmojiStatus(this.selectedStatusEmoji, r12);
        r02.profileView.overrideAvatarColor(this.selectedReplyColor);
        r02.updateColors();
        goto L10
    L7:
        ProfilePreview r62 = (ProfilePreview) r0;
        r62.setColor(this.selectedProfileColor, r12);
        r62.setEmoji(this.selectedProfileEmoji, false, r12);
    L10:
        if ((r1 instanceof PeerColorActivity.PeerColorGrid) == false) goto L13;
        ((PeerColorActivity.PeerColorGrid) r1).setSelected(this.selectedProfileColor, r12);
    L16:
        if ((r2 instanceof EmojiCell) == false) goto L19;
        EmojiCell r22 = (EmojiCell) r2;
        r22.setAdaptiveEmojiColor(this.currentAccount, this.selectedProfileColor, false);
        r22.setEmoji(this.selectedProfileEmoji, false, r12);
    L19:
        if ((r3 instanceof EmojiCell) == false) goto L26;
        TLRPC.EmojiStatus r03 = this.selectedStatusEmoji;
        if ((r03 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L23;
        ((EmojiCell) r3).setAdaptiveEmojiColor(MessagesController.PeerColor.fromCollectible(r03));
    L24:
        ((EmojiCell) r3).setEmoji(DialogObject.getEmojiStatusDocumentId(this.selectedStatusEmoji), DialogObject.isEmojiStatusCollectible(this.selectedStatusEmoji), r12);
        goto L26
    L23:
        ((EmojiCell) r3).setAdaptiveEmojiColor(this.currentAccount, this.selectedProfileColor, false);
    L26:
        if ((r4 instanceof EmojiCell) == false) goto L34;
        EmojiCell r42 = (EmojiCell) r4;
        r42.setAdaptiveEmojiColor(this.currentAccount, this.selectedProfileColor, false);
        TLRPC.ChatFull r122 = getMessagesController().getChatFull(-this.dialogId);
        if (r122 == null) goto L32;
        TLRPC.StickerSet r123 = r122.emojiset;
        if (r123 == null) goto L32;
        r42.setEmoji(getEmojiSetThumbId(r123), false, false);
    L32:
        r42.setEmoji(0, false, false);
    L34:
        if ((r5 instanceof EmojiCell) == false) goto L41;
        TLRPC.ChatFull r124 = getMessagesController().getChatFull(-this.dialogId);
        if (r124 == null) goto L40;
        TLRPC.StickerSet r125 = r124.stickerset;
        if (r125 == null) goto L40;
        ((EmojiCell) r5).setEmoji(getEmojiSetThumb(r125), false, false);
    L40:
        ((EmojiCell) r5).setEmoji(0, false, false);
    L41:
        updateRows();
        return;
    L13:
        if ((r1 instanceof PeerColorPicker) == false) goto L16;
        ((PeerColorPicker) r1).setSelected(this.selectedReplyColor, r12);
        goto L16
    }

    public void updateRows() {
        boolean r0 = false;
        this.messagesPreviewRow = 0;
        int r2 = 1 + 1;
        this.replyColorListRow = 1;
        this.replyEmojiRow = r2;
        this.replyHintRow = r2 + 1;
        this.wallpaperThemesRow = r2 + 2;
        this.wallpaperRow = r2 + 3;
        this.wallpaperHintRow = r2 + 4;
        this.profilePreviewRow = r2 + 5;
        this.profileColorGridRow = r2 + 6;
        int r4 = r2 + 8;
        this.rowsCount = r4;
        this.profileEmojiRow = r2 + 7;
        if (this.selectedProfileEmoji != 0) goto L15;
        if (this.selectedProfileColor >= 0) goto L15;
        if ((this.selectedStatusEmoji instanceof TLRPC.TL_emojiStatusCollectible) == true) goto L15;
        int r02 = this.removeProfileColorRow;
        this.removeProfileColorRow = -1;
        if (r02 < 0) goto L22;
        Adapter r1 = this.adapter;
        if (r1 == null) goto L22;
        r1.notifyItemRemoved(r02);
        this.adapter.notifyItemChanged(this.profileEmojiRow);
    L22:
        int r03 = this.rowsCount;
        this.profileHintRow = r03;
        this.statusEmojiRow = r03 + 1;
        this.rowsCount = r03 + 3;
        this.statusHintRow = r03 + 2;
        return;
    L15:
        if (this.removeProfileColorRow < 0) goto L17;
        r0 = true;
    L17:
        this.rowsCount = r2 + 9;
        this.removeProfileColorRow = r4;
        if (r0 == true) goto L22;
        Adapter r04 = this.adapter;
        if (r04 == null) goto L22;
        r04.notifyItemInserted(r4);
        this.adapter.notifyItemChanged(this.profileEmojiRow);
        goto L22
    }

    public void updateThemeColors() {
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
        String r3 = "Blue";
        String r1 = r0.getString("lastDayTheme", "Blue");
        if (Theme.getTheme(r1) != null) goto L5;
    L6:
        r1 = "Blue";
    L7:
        String r5 = "Dark Blue";
        String r02 = r0.getString("lastDarkTheme", "Dark Blue");
        if (Theme.getTheme(r02) != null) goto L10;
    L11:
        r02 = "Dark Blue";
    L12:
        Theme.ThemeInfo r4 = Theme.getActiveTheme();
        if (r1.equals(r02) == true) goto L15;
        r5 = r02;
    L21:
        r3 = r1;
    L25:
        if (this.isDark == false) goto L27;
        Theme.ThemeInfo r03 = Theme.getTheme(r5);
    L28:
        this.currentColors.clear();
        String[] r12 = new String[1];
        String r32 = r03.assetName;
        if (r32 == null) goto L31;
        SparseIntArray r13 = Theme.getThemeFileValues(null, r32, r12);
    L32:
        int[] r33 = Theme.getDefaultColors();
        if (r33 == null) goto L38;
        int r42 = 0;
    L36:
        if (r42 >= r33.length) goto L38;
        this.currentColors.put(r42, r33[r42]);
        r42 = r42 + 1;
    L38:
        if (r13 == null) goto L46;
        int r34 = 0;
    L41:
        if (r34 >= r13.size()) goto L43;
        this.currentColors.put(r13.keyAt(r34), r13.valueAt(r34));
        r34 = r34 + 1;
        goto L41
    L43:
        Theme.ThemeAccent r04 = r03.getAccent(false);
        if (r04 == null) goto L46;
        r04.fillAccentColors(r13, this.currentColors);
    L46:
        this.dividerPaint.setColor(Theme.getColor(Theme.key_divider, this.resourceProvider));
        this.backgroundDrawable = PreviewView.getBackgroundDrawable(this.backgroundDrawable, this.currentAccount, this.selectedWallpaper, this.isDark);
        View r05 = findChildAt(this.messagesPreviewRow);
        if ((r05 instanceof ThemePreviewMessagesCell) == false) goto L52;
        ((ThemePreviewMessagesCell) r05).setOverrideBackground(this.backgroundDrawable);
        return;
    L52:
        return;
    L31:
        r13 = Theme.getThemeFileValues(new File(r03.pathToFile), null, r12);
        goto L32
    L27:
        r03 = Theme.getTheme(r3);
        goto L28
    L15:
        if (r4.isDark() == false) goto L17;
    L22:
        r5 = r02;
        goto L25
    L17:
        if (r1.equals("Dark Blue") == true) goto L22;
        if (r1.equals("Night") == false) goto L21;
    L10:
        if (Theme.getTheme(r02).isDark() == true) goto L12;
    L5:
        if (Theme.getTheme(r1).isDark() == false) goto L7;
        goto L6
    }
}
