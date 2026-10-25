package org.telegram.ui.Stories;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.View;
import android.view.ViewGroup;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.ManageChatUserCell;
import org.telegram.ui.Cells.ProfileChannelCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.ReactedUserHolderView;
import org.telegram.ui.Cells.SharedPhotoVideoCell2;
import org.telegram.ui.Cells.StatisticPostInfoCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BlurredRecyclerView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Stories.DialogStoriesCell;
import org.telegram.ui.Stories.StoryViewer;

/* loaded from: classes7.dex */
public class StoriesListPlaceProvider implements StoryViewer.PlaceProvider {
    public int addBottomClip;
    int[] clipPoint;
    public boolean hasPaginationParams;
    public boolean hiddedStories;
    private boolean isHiddenArchive;
    LoadNextInterface loadNextInterface;
    public boolean onlySelfStories;
    public boolean onlyUnreadStories;
    private final ProfileChannelCell profileChannelCell;
    private final RecyclerListView recyclerListView;

    /* loaded from: classes3.dex */
    public interface AvatarOverlaysView {
        boolean drawAvatarOverlays(Canvas r1);
    }

    /* loaded from: classes3.dex */
    public interface ClippedView {
        void updateClip(int[] r1);
    }

    public interface LoadNextInterface {
        void loadNext(boolean r1);
    }

    public static /* synthetic */ void $r8$lambda$DVGIpiWkCMGnkOFEE2Al1jnXguk(SharedPhotoVideoCell2 r7, RecyclerListView.FastScroll r8, int[] r9, Canvas r10, RectF r11, float r12, boolean r13) {
        r7.drawDuration(r10, r11, r12);
        r7.drawViews(r10, r11, r12);
        if (r7.isSearchingHashtag == false) goto L5;
        r7.drawAuthor(r10, r11, r12);
    L6:
        if (r8 != null) goto L8;
        return;
    L8:
        if (r8.isVisible == true) goto L10;
        return;
    L10:
        if (r8.getVisibility() != 0) goto L15;
        r10.saveLayerAlpha(0.0f, 0.0f, r10.getWidth(), r10.getHeight(), (int) (r12 * 255.0f), 31);
        r10.translate(r9[0], r9[1]);
        r8.draw(r10);
        r10.restore();
        return;
    L15:
        return;
    L5:
        r7.drawPrivacy(r10, r11, r12);
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$dVvQM61LLLmGehYO5ae62iOJhMA(Path r3, Canvas r4, RectF r5, float r6, boolean r7) {
        if (r7 == false) goto L4;
        return;
    L4:
        r3.rewind();
        float r62 = (float) Math.pow(r6, 2.0d);
        r3.addCircle((r5.right + AndroidUtilities.dp(7.0f)) - (AndroidUtilities.dp(14.0f) * r62), (r5.bottom + AndroidUtilities.dp(7.0f)) - (AndroidUtilities.dp(14.0f) * r62), AndroidUtilities.dp(11.0f), Path.Direction.CW);
        r4.clipPath(r3, Region.Op.DIFFERENCE);
    }

    public StoriesListPlaceProvider(ProfileChannelCell r2) {
        this.clipPoint = new int[2];
        this.profileChannelCell = r2;
        this.recyclerListView = null;
    }

    public StoriesListPlaceProvider(RecyclerListView r2, boolean r3) {
        this.clipPoint = new int[2];
        this.recyclerListView = r2;
        this.isHiddenArchive = r3;
        this.profileChannelCell = null;
    }

    public static StoriesListPlaceProvider of(ProfileChannelCell r1) {
        return new StoriesListPlaceProvider(r1);
    }

    public static StoriesListPlaceProvider of(RecyclerListView r1) {
        return of(r1, false);
    }

    public static StoriesListPlaceProvider of(RecyclerListView r1, boolean r2) {
        return new StoriesListPlaceProvider(r1, r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void updateClip(StoryViewer.TransitionViewHolder r3) {
        View r0 = r3.clipParent;
        if (r0 != 0) goto L6;
        return;
    L6:
        if ((r0 instanceof ClippedView) == false) goto L10;
        ((ClippedView) r0).updateClip(this.clipPoint);
        int[] r02 = this.clipPoint;
        r3.clipTop = r02[0];
        r3.clipBottom = r02[1] - this.addBottomClip;
        return;
    L10:
        if ((r0 instanceof BlurredRecyclerView) == false) goto L13;
        r3.clipTop = ((BlurredRecyclerView) r0).blurTopPadding;
        r3.clipBottom = (r0.getMeasuredHeight() - r3.clipParent.getPaddingBottom()) - this.addBottomClip;
        return;
    L13:
        r3.clipTop = r0.getPaddingTop();
        r3.clipBottom = (r3.clipParent.getMeasuredHeight() - r3.clipParent.getPaddingBottom()) - this.addBottomClip;
    }

    public StoriesListPlaceProvider addBottomClip(int r2) {
        this.addBottomClip += r2;
        return this;
    }

    @Override // org.telegram.ui.Stories.StoryViewer.PlaceProvider
    public boolean findView(long r17, int r19, int r20, int r21, StoryViewer.TransitionViewHolder r22) {
        r22.view = null;
        r22.avatarImage = null;
        r22.storyImage = null;
        r22.drawAbove = null;
        RecyclerListView r6 = this.recyclerListView;
        if (r6 != null) goto L5;
    L7:
        DialogStoriesCell r62 = null;
    L8:
        ViewGroup r7 = this.recyclerListView;
        if (r62 != null) goto L11;
    L13:
        ViewGroup r63 = this.profileChannelCell;
        if (r63 == null) goto L17;
        r7 = r63;
    L17:
        if (r7 != null) goto L19;
        return false;
    L19:
        int r8 = 0;
    L21:
        if (r8 >= r7.getChildCount()) goto L145;
        View r9 = r7.getChildAt(r8);
        if ((r9 instanceof DialogStoriesCell.StoryCell) == false) goto L34;
        DialogStoriesCell.StoryCell r10 = (DialogStoriesCell.StoryCell) r9;
        if (r10.dialogId != r17) goto L144;
        r22.view = r9;
        r22.avatarImage = r10.avatarImage;
        r22.params = r10.params;
        r22.radialProgressUpload = r10.radialProgress;
        DialogStoriesCell r0 = (DialogStoriesCell) r10.getParent().getParent();
        r22.clipParent = r0;
        r22.clipBottom = 0.0f;
        r22.clipTop = 0.0f;
        r22.alpha = 1.0f;
        if (r10.isFail == true) goto L29;
    L31:
        r22.drawClip = null;
    L32:
        return true;
    L29:
        if (r0.isExpanded() == false) goto L31;
        r22.drawClip = new StoriesListPlaceProvider$$ExternalSyntheticLambda0(new Path());
    L144:
        r8 = r8 + 1;
        goto L21
    L34:
        if ((r9 instanceof DialogCell) == false) goto L49;
        DialogCell r102 = (DialogCell) r9;
        if (r102.getDialogId() != r17) goto L40;
        if (this.isHiddenArchive == true) goto L40;
    L43:
        r22.view = r9;
        r22.params = r102.storyParams;
        r22.avatarImage = r102.avatarImage;
        r22.clipParent = (View) r102.getParent();
        if (this.isHiddenArchive == false) goto L46;
        r22.crossfadeToAvatarImage = r102.avatarImage;
        r22.isLive = r102.storyParams.drawnLive;
    L46:
        r22.alpha = 1.0f;
        updateClip(r22);
        return true;
    L40:
        if (this.isHiddenArchive == false) goto L144;
        if (r102.isDialogFolder() == false) goto L144;
    L49:
        if ((r9 instanceof ChatMessageCell) == false) goto L61;
        ChatMessageCell r103 = (ChatMessageCell) r9;
        if (r103.getMessageObject().getId() != r19) goto L144;
        r22.view = r9;
        if (r21 == 1) goto L57;
        if (r21 == 2) goto L57;
        r22.storyImage = r103.replyImageReceiver;
    L58:
        r22.clipParent = (View) r103.getParent();
        r22.alpha = 1.0f;
        updateClip(r22);
        return true;
    L57:
        r22.storyImage = r103.getPhotoImage();
        goto L58
    L61:
        if ((r9 instanceof ChatActionCell) == false) goto L71;
        ChatActionCell r104 = (ChatActionCell) r9;
        if (r104.getMessageObject().getId() != r19) goto L144;
        r22.view = r9;
        if (r104.getMessageObject().messageOwner.media.storyItem.noforwards == false) goto L67;
        r22.avatarImage = r104.getPhotoImage();
    L68:
        r22.clipParent = (View) r104.getParent();
        r22.alpha = 1.0f;
        updateClip(r22);
        return true;
    L67:
        r22.storyImage = r104.getPhotoImage();
        goto L68
    L71:
        if ((r9 instanceof SharedPhotoVideoCell2) == false) goto L91;
        if (this.recyclerListView == null) goto L91;
        SharedPhotoVideoCell2 r105 = (SharedPhotoVideoCell2) r9;
        MessageObject r14 = r105.getMessageObject();
        if (r105.getStyle() == 1) goto L77;
    L78:
        if (r14 == null) goto L144;
        if (r14.isStory() == false) goto L144;
        if (r14.getId() != r20) goto L144;
        if (r14.storyItem.dialogId != r17) goto L144;
    L85:
        RecyclerListView.FastScroll r1 = this.recyclerListView.getFastScroll();
        int[] r2 = new int[2];
        if (r1 == null) goto L88;
        r1.getLocationInWindow(r2);
    L88:
        r22.view = r9;
        r22.storyImage = r105.imageReceiver;
        r22.drawAbove = new StoriesListPlaceProvider$$ExternalSyntheticLambda1(r105, r1, r2);
        r22.clipParent = (View) r105.getParent();
        r22.alpha = 1.0f;
        updateClip(r22);
        return true;
    L77:
        if (r105.storyId == r20) goto L85;
    L91:
        if ((r9 instanceof UserCell) == false) goto L97;
        UserCell r92 = (UserCell) r9;
        if (r92.getDialogId() != r17) goto L144;
        BackupImageView r12 = r92.avatarImageView;
        r22.view = r12;
        r22.params = r92.storyParams;
        r22.avatarImage = r12.getImageReceiver();
        r22.clipParent = (View) r92.getParent();
        r22.alpha = 1.0f;
        updateClip(r22);
        return true;
    L97:
        if ((r9 instanceof ReactedUserHolderView) == false) goto L123;
        ReactedUserHolderView r93 = (ReactedUserHolderView) r9;
        if (r93.dialogId != r17) goto L144;
        BackupImageView r106 = r93.storyPreviewView;
        if (r106 != null) goto L103;
    L107:
        boolean r107 = false;
    L109:
        if (r93.storyId != r20) goto L116;
        if (r107 == false) goto L116;
        BackupImageView r13 = r93.storyPreviewView;
        r22.view = r13;
        r22.storyImage = r13.getImageReceiver();
        r22.clipParent = (View) r93.getParent();
        float r15 = r93.getAlpha() * r93.getAlphaInternal();
        r22.alpha = r15;
        if (r15 >= 1.0f) goto L114;
        Paint r16 = new Paint(1);
        r22.bgPaint = r16;
        r16.setColor(Theme.getColor(Theme.key_dialogBackground, r93.getResourcesProvider()));
    L114:
        updateClip(r22);
        return true;
    L116:
        if (r107 == true) goto L144;
        BackupImageView r18 = r93.avatarView;
        r22.view = r18;
        r22.params = r93.params;
        r22.avatarImage = r18.getImageReceiver();
        r22.clipParent = (View) r93.getParent();
        float r110 = r93.getAlpha() * r93.getAlphaInternal();
        r22.alpha = r110;
        if (r110 >= 1.0f) goto L120;
        Paint r111 = new Paint(1);
        r22.bgPaint = r111;
        r111.setColor(Theme.getColor(Theme.key_dialogBackground, r93.getResourcesProvider()));
    L120:
        updateClip(r22);
        return true;
    L103:
        if (r106.getImageReceiver() == null) goto L107;
        if (r93.storyPreviewView.getImageReceiver().getImageDrawable() == null) goto L107;
        r107 = true;
        goto L109
    L123:
        if ((r9 instanceof ProfileSearchCell) == false) goto L129;
        ProfileSearchCell r94 = (ProfileSearchCell) r9;
        if (r94.getDialogId() != r17) goto L144;
        r22.view = r94;
        r22.params = r94.avatarStoryParams;
        r22.avatarImage = r94.avatarImage;
        r22.clipParent = (View) r94.getParent();
        r22.alpha = 1.0f;
        updateClip(r22);
        return true;
    L129:
        if ((r9 instanceof StatisticPostInfoCell) == false) goto L135;
        StatisticPostInfoCell r95 = (StatisticPostInfoCell) r9;
        if (r95.getPostInfo().getId() != r20) goto L144;
        r22.view = r95.getImageView();
        r22.params = r95.getStoryAvatarParams();
        r22.storyImage = r95.getImageView().getImageReceiver();
        r22.clipParent = (View) r95.getParent();
        r22.alpha = 1.0f;
        updateClip(r22);
        return true;
    L135:
        if ((r9 instanceof ManageChatUserCell) == false) goto L144;
        ManageChatUserCell r96 = (ManageChatUserCell) r9;
        if (r96.getStoryItem() == null) goto L144;
        if (r96.getStoryItem().dialogId != r17) goto L144;
        if (r96.getStoryItem().messageId != r19) goto L144;
        r22.view = r96.getAvatarImageView();
        r22.params = r96.getStoryAvatarParams();
        r22.avatarImage = r96.getAvatarImageView().getImageReceiver();
        r22.clipParent = (View) r96.getParent();
        r22.alpha = 1.0f;
        updateClip(r22);
        return true;
    L145:
        return false;
    L11:
        if (r62.isExpanded() == true) goto L13;
        r7 = r62.listViewMini;
        goto L13
    L5:
        if ((r6.getParent() instanceof DialogStoriesCell) == false) goto L7;
        r62 = (DialogStoriesCell) this.recyclerListView.getParent();
        goto L8
    }

    @Override // org.telegram.ui.Stories.StoryViewer.PlaceProvider
    public void loadNext(boolean r1) {
        LoadNextInterface r0 = this.loadNextInterface;
        if (r0 == null) goto L6;
        r0.loadNext(r1);
        return;
    }

    @Override // org.telegram.ui.Stories.StoryViewer.PlaceProvider
    public void preLayout(long r2, int r4, Runnable r5) {
        RecyclerListView r0 = this.recyclerListView;
        if (r0 != null) goto L5;
    L12:
        RecyclerListView r02 = this.recyclerListView;
        if (r02 == null) goto L23;
        if ((r02.getParent() instanceof SelfStoryViewsPage) == false) goto L23;
        if (((SelfStoryViewsPage) this.recyclerListView.getParent()).scrollToRepostCell(r2, r4) == false) goto L20;
        this.recyclerListView.post(r5);
        return;
    L20:
        r5.run();
        return;
    L23:
        if (this.isHiddenArchive == false) goto L25;
        MessagesController.getInstance(UserConfig.selectedAccount).getStoriesController().sortHiddenStories();
    L25:
        r5.run();
        return;
    L5:
        if ((r0.getParent() instanceof DialogStoriesCell) == false) goto L12;
        DialogStoriesCell r1 = (DialogStoriesCell) this.recyclerListView.getParent();
        if (r1.scrollTo(r2) == false) goto L10;
        r1.afterNextLayout(r5);
        return;
    L10:
        r5.run();
    }

    public StoryViewer.PlaceProvider setPaginationParaments(boolean r1, boolean r2, boolean r3) {
        this.hiddedStories = r1;
        this.onlyUnreadStories = r2;
        this.onlySelfStories = r3;
        this.hasPaginationParams = true;
        return this;
    }

    public StoriesListPlaceProvider with(LoadNextInterface r1) {
        this.loadNextInterface = r1;
        return this;
    }
}
