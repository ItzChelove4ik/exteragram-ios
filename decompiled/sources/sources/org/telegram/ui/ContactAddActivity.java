package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Property;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.utils.PhotoUtilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.ImageUpdater;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.LNavigation.NavigationExt;

/* loaded from: classes6.dex */
public class ContactAddActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate, ImageUpdater.ImageUpdaterDelegate {
    private boolean addContact;
    private TLRPC.FileLocation avatar;
    private AnimatorSet avatarAnimation;
    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImage;
    private View avatarOverlay;
    private RadialProgressView avatarProgressView;
    private boolean checkShare;
    private ContactAddActivityDelegate delegate;
    private MessagesController.DialogPhotos dialogPhotos;
    private View doneButton;
    private EditTextCell firstNameField;
    private String firstNameFromCard;
    private boolean firstSet;
    private boolean focusNotes;
    private ImageUpdater imageUpdater;
    private FrameLayout infoLayout;
    private EditTextCell lastNameField;
    private String lastNameFromCard;
    private UniversalRecyclerView listView;
    private TextView nameTextView;
    private boolean needAddException;
    private EditTextCell noteField;
    private BackupImageView oldAvatarView;
    private TextCell oldPhotoCell;
    private TextView onlineTextView;
    boolean paused;
    private String phone;
    private int photoSelectedType;
    private int photoSelectedTypeFinal;
    private TLRPC.Photo prevAvatar;
    private Theme.ResourcesProvider resourcesProvider;
    private TextCell setAvatarCell;
    private TextCell suggestBirthday;
    private TextCell suggestPhoto;
    MessageObject suggestPhotoMessageFinal;
    private long user_id;

    /* renamed from: org.telegram.ui.ContactAddActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ContactAddActivity this$0;

        public AnonymousClass1(ContactAddActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r7) {
            if (r7 != (-1)) goto L7;
            this.this$0.finishFragment();
            return;
        L7:
            if (r7 == 1) goto L9;
            return;
        L9:
            if (ContactAddActivity.m14043$$Nest$fgetfirstNameField(this.this$0).getText().length() == 0) goto L28;
            TLRPC.User r72 = this.this$0.getMessagesController().getUser(Long.valueOf(ContactAddActivity.m14049$$Nest$fgetuser_id(this.this$0)));
            TLRPC.UserFull r1 = this.this$0.getMessagesController().getUserFull(ContactAddActivity.m14049$$Nest$fgetuser_id(this.this$0));
            r72.first_name = ContactAddActivity.m14043$$Nest$fgetfirstNameField(this.this$0).getText().toString();
            r72.last_name = ContactAddActivity.m14044$$Nest$fgetlastNameField(this.this$0).getText().toString();
            r72.contact = true;
            TLRPC.TL_textWithEntities r2 = ContactAddActivity.m14047$$Nest$fgetnoteField(this.this$0).getTextWithEntities();
            boolean r4 = false;
            this.this$0.getMessagesController().putUser(r72, false);
            ContactsController r3 = ContactAddActivity.access$000(this.this$0);
            if (ContactAddActivity.m14046$$Nest$fgetneedAddException(this.this$0) == true) goto L13;
        L15:
            r3.addContact(r72, r2, r4);
            MessagesController.getNotificationsSettings(ContactAddActivity.access$100(this.this$0)).edit().putInt("dialog_bar_vis3" + ContactAddActivity.m14049$$Nest$fgetuser_id(this.this$0), 3).apply();
            this.this$0.getNotificationCenter().postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_NAME)});
            this.this$0.getNotificationCenter().postNotificationName(NotificationCenter.peerSettingsDidLoad, new Object[]{Long.valueOf(ContactAddActivity.m14049$$Nest$fgetuser_id(this.this$0))});
            if (r1 == null) goto L23;
            if (r2 != null) goto L19;
        L21:
            r1.flags2 &= -4194305;
            r1.note = null;
        L22:
            MessagesStorage.getInstance(ContactAddActivity.access$200(this.this$0)).updateUserInfo(r1, true);
            this.this$0.getNotificationCenter().postNotificationName(NotificationCenter.userInfoDidLoad, new Object[]{Long.valueOf(r1.id), r1});
            goto L23
        L19:
            if (r2.text.length() <= 0) goto L21;
            r1.flags2 |= 4194304;
            r1.note = r2;
        L23:
            this.this$0.finishFragment();
            if (ContactAddActivity.m14042$$Nest$fgetdelegate(this.this$0) == null) goto L29;
            ContactAddActivity.m14042$$Nest$fgetdelegate(this.this$0).didAddToContacts();
            return;
        L29:
            return;
        L13:
            if (ContactAddActivity.m14041$$Nest$fgetcheckShare(this.this$0) == false) goto L15;
            r4 = true;
            goto L15
        }
    }

    /* renamed from: org.telegram.ui.ContactAddActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends View {
        final /* synthetic */ ContactAddActivity this$0;
        final /* synthetic */ Paint val$paint;

        public AnonymousClass2(ContactAddActivity r1, Context r2, Paint r3) {
            this.this$0 = r1;
            this.val$paint = r3;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            if (ContactAddActivity.m14038$$Nest$fgetavatarImage(this.this$0) != null) goto L5;
            return;
        L5:
            if (ContactAddActivity.m14038$$Nest$fgetavatarImage(this.this$0).getImageReceiver().hasNotThumb() == false) goto L9;
            this.val$paint.setAlpha((int) (ContactAddActivity.m14038$$Nest$fgetavatarImage(this.this$0).getImageReceiver().getCurrentAlpha() * 85.0f));
            r5.drawCircle(getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f, getMeasuredWidth() / 2.0f, this.val$paint);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ContactAddActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements View.OnFocusChangeListener {
        boolean focused;
        final /* synthetic */ ContactAddActivity this$0;

        public AnonymousClass3(ContactAddActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View r1, boolean r2) {
            this.focused = r2;
        }
    }

    /* renamed from: org.telegram.ui.ContactAddActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends TextCell {
        final /* synthetic */ ContactAddActivity this$0;

        public AnonymousClass4(ContactAddActivity r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Cells.TextCell, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            int r12 = AndroidUtilities.dp(21.0f);
            int r22 = (getMeasuredHeight() - ContactAddActivity.m14048$$Nest$fgetoldAvatarView(this.this$0).getMeasuredHeight()) / 2;
            ContactAddActivity.m14048$$Nest$fgetoldAvatarView(this.this$0).layout(r12, r22, ContactAddActivity.m14048$$Nest$fgetoldAvatarView(this.this$0).getMeasuredWidth() + r12, ContactAddActivity.m14048$$Nest$fgetoldAvatarView(this.this$0).getMeasuredHeight() + r22);
        }

        @Override // org.telegram.ui.Cells.TextCell, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            super.onMeasure(r4, r5);
            ContactAddActivity.m14048$$Nest$fgetoldAvatarView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(30.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(30.0f), TLObject.FLAG_30));
            ContactAddActivity.m14048$$Nest$fgetoldAvatarView(this.this$0).setRoundRadius(ExteraConfig.getAvatarCorners(30.0f));
        }
    }

    /* renamed from: org.telegram.ui.ContactAddActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ContactAddActivity this$0;
        final /* synthetic */ FrameLayout val$frameLayout;

        public AnonymousClass5(ContactAddActivity r1, FrameLayout r2) {
            this.this$0 = r1;
            this.val$frameLayout = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (ContactAddActivity.m14045$$Nest$fgetlistView(this.this$0).scrollingByUser == false) goto L6;
            AndroidUtilities.hideKeyboard(this.val$frameLayout);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ContactAddActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ ContactAddActivity this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass6(ContactAddActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            ContactAddActivity.m14050$$Nest$fputavatarAnimation(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (ContactAddActivity.m14037$$Nest$fgetavatarAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (ContactAddActivity.m14040$$Nest$fgetavatarProgressView(this.this$0) != null) goto L8;
            return;
        L8:
            if (this.val$show == true) goto L10;
            ContactAddActivity.m14040$$Nest$fgetavatarProgressView(this.this$0).setVisibility(4);
            ContactAddActivity.m14039$$Nest$fgetavatarOverlay(this.this$0).setVisibility(4);
        L10:
            ContactAddActivity.m14050$$Nest$fputavatarAnimation(this.this$0, null);
        }
    }

    public interface ContactAddActivityDelegate {
        void didAddToContacts();
    }

    public static /* synthetic */ void $r8$lambda$45TFOTli08OMsKbiUjniubUwJGw(ContactAddActivity r0, TLRPC.FileLocation r1, TLRPC.InputFile r2, TLRPC.FileLocation r3, int r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$sendPhotoChangedRequest$22(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ boolean $r8$lambda$5p50b3AduAFZDWRB4w21tHjp2YE(ContactAddActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$1(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$6OHA6uDLN4eWZ5DnhAIB23BhmOo(ContactAddActivity r0, BaseFragment r1) {
        return r0.lambda$didUploadPhoto$18(r1);
    }

    /* renamed from: $r8$lambda$8Ph0RmmEfKh55oLgyISyo9-VRO4, reason: not valid java name */
    public static /* synthetic */ void m14031$r8$lambda$8Ph0RmmEfKh55oLgyISyo9VRO4(ContactAddActivity r0, TLRPC.User r1, View r2) {
        r0.lambda$createView$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$B0XiioB57ijEsSFYvGCA1aqlFvg(ContactAddActivity r0, RLottieDrawable r1, DialogInterface r2) {
        r0.lambda$createView$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$CIL3B2VgHXMTnMNhOooGhAtOXJ8(ContactAddActivity r0, RLottieDrawable r1, DialogInterface r2) {
        r0.lambda$createView$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Fc1iZqmd6sUKumkRXlXLa0Ehpmk(ContactAddActivity r0, TLRPC.User r1) {
        r0.lambda$createView$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$Gceqha0X4KYvE5KDzIt_Zgp1UvI(ContactAddActivity r0, Context r1, TLRPC.User r2, View r3) {
        r0.lambda$createView$10(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$IP54Tfo1yeNBg6mUy0UAXx2Ytho(ContactAddActivity r0, TLRPC.User r1, RLottieDrawable r2, View r3) {
        r0.lambda$createView$8(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$LPPvgrrSUfJi0dFhajZWoG7VFqg() {
    }

    public static /* synthetic */ void $r8$lambda$PXF9akA3dPK4_3lVMQcU3Q8_k2k(ContactAddActivity r0, TLRPC.User r1, AlertDialog r2, int r3) {
        r0.lambda$onItemClick$17(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$SkEaSfoJmTJjXKvFq_DEvtemdhY(ContactAddActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$createView$12(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$T3UyGjbuZKVdUVEdKgulniQB12c(ContactAddActivity r0) {
        r0.lambda$fillItems$16();
    }

    /* renamed from: $r8$lambda$XTDe8-q4r0op780WW2EQW3aTb24, reason: not valid java name */
    public static /* synthetic */ void m14032$r8$lambda$XTDe8q4r0op780WW2EQW3aTb24(ContactAddActivity r0, TLRPC.FileLocation r1, TLRPC.InputFile r2, TLObject r3, TLRPC.FileLocation r4, int r5) {
        r0.lambda$sendPhotoChangedRequest$21(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$aYOFfL9uaHs-p83EJATsrHqQ24E, reason: not valid java name */
    public static /* synthetic */ boolean m14033$r8$lambda$aYOFfL9uaHsp83EJATsrHqQ24E(ContactAddActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$azyBPMuF_WNc8EtD7-1OFIEXakU, reason: not valid java name */
    public static /* synthetic */ void m14034$r8$lambda$azyBPMuF_WNc8EtD71OFIEXakU(ContactAddActivity r0) {
        r0.lambda$getThemeDescriptions$23();
    }

    public static /* synthetic */ boolean $r8$lambda$bv5zWKKHzgobPNQbtRZ25KehNhQ(ContactAddActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$eRKJ3zhlKGBtOJ4Znwp3Z707xas(ContactAddActivity r0, TLRPC.User r1, RLottieDrawable r2, View r3) {
        r0.lambda$createView$5(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$lcX-bOVMbARDEJ_92w3Mqi6pNFI, reason: not valid java name */
    public static /* synthetic */ void m14035$r8$lambda$lcXbOVMbARDEJ_92w3Mqi6pNFI(ContactAddActivity r0, TLRPC.User r1) {
        r0.lambda$fillItems$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$n8l3lHpCyxOoj7pmmh4iobgwNv8(ContactAddActivity r0) {
        r0.lambda$didUploadFailed$20();
    }

    /* renamed from: $r8$lambda$qnTjsk-qw6g_lGNrawrTaWc3jnQ, reason: not valid java name */
    public static /* synthetic */ void m14036$r8$lambda$qnTjskqw6g_lGNrawrTaWc3jnQ(ContactAddActivity r0, TL_account.TL_birthday r1) {
        r0.lambda$createView$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$rerun6Y2Rw_4zO9hAXgQqp8kLt8(ContactAddActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$si3lf_99yDdDgeweloxCggGRXak(ContactAddActivity r0, TLRPC.PhotoSize r1, TLRPC.InputFile r2, TLRPC.InputFile r3, TLRPC.PhotoSize r4, TLRPC.VideoSize r5, double r6, boolean r8) {
        r0.lambda$didUploadPhoto$19(r1, r2, r3, r4, r5, r6, r8);
    }

    public static /* synthetic */ void $r8$lambda$wXNNDiDuTlPHGGCXyjzhxyH7bzk(ContactAddActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onItemClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$xBHCyOJY0xiiShPXNdsiY1Ru3IA() {
    }

    public static /* synthetic */ void $r8$lambda$yYTcTyAtpKR5E96MwIL1jbXn0vA(ContactAddActivity r0) {
        r0.lambda$createView$11();
    }

    /* renamed from: -$$Nest$fgetavatarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m14037$$Nest$fgetavatarAnimation(ContactAddActivity r0) {
        return r0.avatarAnimation;
    }

    /* renamed from: -$$Nest$fgetavatarImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m14038$$Nest$fgetavatarImage(ContactAddActivity r0) {
        return r0.avatarImage;
    }

    /* renamed from: -$$Nest$fgetavatarOverlay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m14039$$Nest$fgetavatarOverlay(ContactAddActivity r0) {
        return r0.avatarOverlay;
    }

    /* renamed from: -$$Nest$fgetavatarProgressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgressView m14040$$Nest$fgetavatarProgressView(ContactAddActivity r0) {
        return r0.avatarProgressView;
    }

    /* renamed from: -$$Nest$fgetcheckShare, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14041$$Nest$fgetcheckShare(ContactAddActivity r0) {
        return r0.checkShare;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContactAddActivityDelegate m14042$$Nest$fgetdelegate(ContactAddActivity r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetfirstNameField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextCell m14043$$Nest$fgetfirstNameField(ContactAddActivity r0) {
        return r0.firstNameField;
    }

    /* renamed from: -$$Nest$fgetlastNameField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextCell m14044$$Nest$fgetlastNameField(ContactAddActivity r0) {
        return r0.lastNameField;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalRecyclerView m14045$$Nest$fgetlistView(ContactAddActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetneedAddException, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14046$$Nest$fgetneedAddException(ContactAddActivity r0) {
        return r0.needAddException;
    }

    /* renamed from: -$$Nest$fgetnoteField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextCell m14047$$Nest$fgetnoteField(ContactAddActivity r0) {
        return r0.noteField;
    }

    /* renamed from: -$$Nest$fgetoldAvatarView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m14048$$Nest$fgetoldAvatarView(ContactAddActivity r0) {
        return r0.oldAvatarView;
    }

    /* renamed from: -$$Nest$fgetuser_id, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m14049$$Nest$fgetuser_id(ContactAddActivity r2) {
        return r2.user_id;
    }

    /* renamed from: -$$Nest$fputavatarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14050$$Nest$fputavatarAnimation(ContactAddActivity r0, AnimatorSet r1) {
        r0.avatarAnimation = r1;
    }

    public ContactAddActivity(Bundle r3) {
        super(r3);
        this.checkShare = false;
        this.firstSet = true;
        this.imageUpdater = new ImageUpdater(true, 0, true);
    }

    public ContactAddActivity(Bundle r2, Theme.ResourcesProvider r3) {
        super(r2);
        this.checkShare = false;
        this.firstSet = true;
        this.resourcesProvider = r3;
        this.imageUpdater = new ImageUpdater(true, 0, true);
    }

    public static /* synthetic */ ContactsController access$000(ContactAddActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ int access$100(ContactAddActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(ContactAddActivity r0) {
        return r0.currentAccount;
    }

    private void createServiceMessageLocal(TLRPC.PhotoSize r5, TLRPC.PhotoSize r6, boolean r7) {
        TLRPC.TL_messageService r0 = new TLRPC.TL_messageService();
        r0.random_id = SendMessagesHelper.getInstance(this.currentAccount).getNextRandomId();
        r0.dialog_id = this.user_id;
        r0.unread = true;
        r0.out = true;
        int r1 = getUserConfig().getNewMessageId();
        r0.id = r1;
        r0.local_id = r1;
        TLRPC.TL_peerUser r12 = new TLRPC.TL_peerUser();
        r0.from_id = r12;
        r12.user_id = getUserConfig().getClientUserId();
        r0.flags |= 256;
        TLRPC.TL_peerUser r13 = new TLRPC.TL_peerUser();
        r0.peer_id = r13;
        r13.user_id = this.user_id;
        r0.date = getConnectionsManager().getCurrentTime();
        TLRPC.TL_messageActionSuggestProfilePhoto r14 = new TLRPC.TL_messageActionSuggestProfilePhoto();
        r0.action = r14;
        TLRPC.TL_photo r2 = new TLRPC.TL_photo();
        r14.photo = r2;
        r2.sizes.add(r5);
        r14.photo.sizes.add(r6);
        r14.video = r7;
        r14.photo.file_reference = new byte[0];
        ArrayList<MessageObject> r52 = new ArrayList();
        MessageObject r72 = new MessageObject(this.currentAccount, r0, false, false);
        this.suggestPhotoMessageFinal = r72;
        r52.add(r72);
        new ArrayList().add(r0);
        MessagesController.getInstance(this.currentAccount).updateInterfaceWithMessages(this.user_id, r52, 0);
        getMessagesController().photoSuggestion.put(r0.local_id, this.imageUpdater);
    }

    private void fillItems(ArrayList<UItem> r6, UniversalAdapter r7) {
        TLRPC.User r72 = getMessagesController().getUser(Long.valueOf(this.user_id));
        r6.add(UItem.asCustom(this.infoLayout));
        r6.add(UItem.asCustom(this.firstNameField));
        r6.add(UItem.asCustom(this.lastNameField));
        if (this.addContact == true) goto L5;
        r6.add(UItem.asShadow(null));
    L15:
        r6.add(UItem.asCustom(this.noteField));
        r6.add(UItem.asShadow(LocaleController.getString(R.string.AddNotesInfo)));
        if (this.addContact == true) goto L30;
        TLRPC.UserFull r0 = getMessagesController().getUserFull(this.user_id);
        if (r0 != null) goto L20;
    L22:
        r6.add(UItem.asCustom(this.suggestPhoto));
        r6.add(UItem.asCustom(this.setAvatarCell));
        if (r72 == null) goto L29;
        TLRPC.UserProfilePhoto r02 = r72.photo;
        if (r02 == null) goto L29;
        if (r02.personal == false) goto L29;
        r6.add(UItem.asCustom(this.oldPhotoCell));
    L29:
        r6.add(UItem.asShadow(null));
        r6.add(UItem.asButton(1, LocaleController.getString(R.string.DeleteContact)).red());
        goto L30
    L20:
        if (r0.birthday != null) goto L22;
        r6.add(UItem.asCustom(this.suggestBirthday));
    L30:
        r6.add(UItem.asShadow(null));
        if (this.firstSet == false) goto L34;
        AndroidUtilities.runOnUIThread(new ContactAddActivity$$ExternalSyntheticLambda13(this, r72));
        this.firstSet = false;
        AndroidUtilities.runOnUIThread(new ContactAddActivity$$ExternalSyntheticLambda14(this), 200);
        return;
    L34:
        return;
    L5:
        if (TextUtils.isEmpty(getPhone()) == false) goto L8;
        r6.add(UItem.asShadow(AndroidUtilities.replaceCharSequence("%1$s", AndroidUtilities.replaceTags(LocaleController.getString(R.string.MobileHiddenExceptionInfo)), UserObject.getFirstName(r72))));
    L12:
        if (this.needAddException == false) goto L15;
        this.checkShare = false;
        r6.add(UItem.asCheck(2, LocaleController.getString(R.string.AddContactShareNumber)).setChecked(this.checkShare));
        r6.add(UItem.asShadow(LocaleController.formatString(R.string.AddContactShareNumberInfo, new Object[]{UserObject.getFirstName(r72)})));
        goto L15
    L8:
        if (this.needAddException == false) goto L10;
        r6.add(UItem.asShadow(AndroidUtilities.replaceTags(LocaleController.formatString("MobileVisibleInfo", R.string.MobileVisibleInfo, new Object[]{UserObject.getFirstName(r72)}))));
        goto L12
    L10:
        r6.add(UItem.asShadow(null));
        goto L12
    }

    private String getPhone() {
        TLRPC.User r0 = getMessagesController().getUser(Long.valueOf(this.user_id));
        if (r0 == null) goto L9;
        if (TextUtils.isEmpty(r0.phone) == true) goto L9;
        return r0.phone;
    L9:
        return this.phone;
    }

    private /* synthetic */ boolean lambda$createView$0(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L6;
        this.lastNameField.editText.requestFocus();
        EditTextCaption r0 = this.lastNameField.editText;
        r0.setSelection(r0.length());
        return true;
    L6:
        return false;
    }

    private /* synthetic */ boolean lambda$createView$1(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L7;
        this.doneButton.performClick();
        return true;
    L7:
        if (r2 != 5) goto L10;
        this.noteField.editText.requestFocus();
        this.noteField.editText.setSelection(this.lastNameField.editText.length());
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$createView$10(Context r7, TLRPC.User r8, View r9) {
        AlertsCreator.createSimpleAlert(r7, LocaleController.getString(R.string.ResetToOriginalPhotoTitle), LocaleController.formatString(R.string.ResetToOriginalPhotoMessage, new Object[]{r8.first_name}), LocaleController.getString(R.string.Reset), new ContactAddActivity$$ExternalSyntheticLambda16(this, r8), this.resourcesProvider).show();
    }

    private /* synthetic */ void lambda$createView$11() {
        presentFragment(ChatActivity.of(this.user_id), true);
    }

    private /* synthetic */ void lambda$createView$12(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ContactAddActivity$$ExternalSyntheticLambda24(this));
    }

    private /* synthetic */ void lambda$createView$13(TL_account.TL_birthday r5) {
        TLRPC.TL_users_suggestBirthday r0 = new TLRPC.TL_users_suggestBirthday();
        r0.id = getMessagesController().getInputUser(this.user_id);
        r0.birthday = r5;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new ContactAddActivity$$ExternalSyntheticLambda23(this));
    }

    private /* synthetic */ void lambda$createView$14(TLRPC.User r10, View r11) {
        showDialog(AlertsCreator.createBirthdayPickerDialog(getContext(), LocaleController.formatString(R.string.UserSuggestBirthdayTitle, new Object[]{UserObject.getForcedFirstName(r10)}), LocaleController.getString(R.string.UserSuggestBirthdayButton), null, new ContactAddActivity$$ExternalSyntheticLambda15(this), null, false, false, this.resourcesProvider).create());
    }

    private /* synthetic */ boolean lambda$createView$2(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L6;
        this.doneButton.performClick();
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$createView$4(RLottieDrawable r1, DialogInterface r2) {
        if (this.imageUpdater.isUploadingImage() == true) goto L6;
        r1.setCustomEndFrame(85);
        this.suggestPhoto.imageView.playAnimation();
        return;
    L6:
        r1.setCurrentFrame(0, false);
    }

    private /* synthetic */ void lambda$createView$5(TLRPC.User r5, RLottieDrawable r6, View r7) {
        boolean r72 = true;
        this.photoSelectedType = 1;
        this.imageUpdater.setUser(r5);
        if (r5 == null) goto L8;
        TLRPC.UserProfilePhoto r52 = r5.photo;
        if (r52 == null) goto L8;
        TLRPC.FileLocation r53 = r52.photo_small;
    L9:
        ImageUpdater r0 = this.imageUpdater;
        if (r53 != null) goto L13;
        r72 = false;
    L13:
        r0.openMenu(r72, new ContactAddActivity$$ExternalSyntheticLambda19(), new ContactAddActivity$$ExternalSyntheticLambda20(this, r6), 2);
        r6.setCurrentFrame(0);
        r6.setCustomEndFrame(43);
        this.suggestPhoto.imageView.playAnimation();
        return;
    L8:
        r53 = null;
        goto L9
    }

    private /* synthetic */ void lambda$createView$7(RLottieDrawable r1, DialogInterface r2) {
        if (this.imageUpdater.isUploadingImage() == true) goto L6;
        r1.setCustomEndFrame(86);
        this.setAvatarCell.imageView.playAnimation();
        return;
    L6:
        r1.setCurrentFrame(0, false);
    }

    private /* synthetic */ void lambda$createView$8(TLRPC.User r5, RLottieDrawable r6, View r7) {
        this.photoSelectedType = 2;
        this.imageUpdater.setUser(r5);
        if (r5 == null) goto L8;
        TLRPC.UserProfilePhoto r52 = r5.photo;
        if (r52 == null) goto L8;
        TLRPC.FileLocation r53 = r52.photo_small;
    L9:
        ImageUpdater r72 = this.imageUpdater;
        if (r53 == null) goto L12;
        boolean r54 = true;
    L13:
        r72.openMenu(r54, new ContactAddActivity$$ExternalSyntheticLambda17(), new ContactAddActivity$$ExternalSyntheticLambda18(this, r6), 1);
        r6.setCurrentFrame(0);
        r6.setCustomEndFrame(43);
        this.setAvatarCell.imageView.playAnimation();
        return;
    L12:
        r54 = false;
    L8:
        r53 = null;
        goto L9
    }

    private /* synthetic */ void lambda$createView$9(TLRPC.User r11) {
        this.avatar = null;
        sendPhotoChangedRequest(null, null, null, null, null, 0.0d, 2);
        TLRPC.User r10 = getMessagesController().getUser(Long.valueOf(this.user_id));
        r10.photo.personal = false;
        TLRPC.UserFull r2 = MessagesController.getInstance(this.currentAccount).getUserFull(this.user_id);
        if (r2 == null) goto L5;
        r2.personal_photo = null;
        r2.flags &= -2097153;
        getMessagesStorage().updateUserInfo(r2, true);
    L5:
        TLRPC.Photo r22 = this.prevAvatar;
        if (r22 == null) goto L12;
        r10.photo.photo_id = r22.id;
        ArrayList<TLRPC.PhotoSize> r23 = r22.sizes;
        TLRPC.PhotoSize r5 = FileLoader.getClosestPhotoSizeWithSize(r23, 100);
        TLRPC.PhotoSize r24 = FileLoader.getClosestPhotoSizeWithSize(r23, 1000);
        if (r5 == null) goto L10;
        r10.photo.photo_small = r5.location;
    L10:
        if (r24 == null) goto L13;
        r10.photo.photo_big = r24.location;
    L13:
        ArrayList r102 = new ArrayList();
        r102.add(r11);
        getMessagesStorage().putUsersAndChats(r102, null, false, true);
        updateCustomPhotoInfo();
        getNotificationCenter().postNotificationName(NotificationCenter.reloadDialogPhotos, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_AVATAR)});
        return;
    L12:
        r10.photo = null;
        r10.flags &= -33;
        goto L13
    }

    private /* synthetic */ void lambda$didUploadFailed$20() {
        super.didUploadFailed();
        if (this.suggestPhotoMessageFinal == null) goto L6;
        ArrayList r0 = new ArrayList();
        r0.add(Integer.valueOf(this.suggestPhotoMessageFinal.getId()));
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.messagesDeleted, new Object[]{r0, 0L, Boolean.FALSE});
        return;
    }

    private /* synthetic */ boolean lambda$didUploadPhoto$18(BaseFragment r7) {
        if ((r7 instanceof ChatActivity) == false) goto L10;
        ChatActivity r72 = (ChatActivity) r7;
        if (r72.getDialogId() != this.user_id) goto L10;
        if (r72.getChatMode() != 0) goto L10;
        r72.scrollToLastMessage(true, false);
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$didUploadPhoto$19(TLRPC.PhotoSize r14, TLRPC.InputFile r15, TLRPC.InputFile r16, TLRPC.PhotoSize r17, TLRPC.VideoSize r18, double r19, boolean r21) {
        if (this.imageUpdater.isCanceled() == false) goto L5;
        return;
    L5:
        int r3 = this.photoSelectedTypeFinal;
        if (r3 != 2) goto L8;
        this.avatar = r14.location;
    L11:
        if (r15 != null) goto L18;
        if (r16 != null) goto L18;
        this.avatarImage.setImage(ImageLocation.getForLocal(this.avatar), "50_50", this.avatarDrawable, getMessagesController().getUser(Long.valueOf(this.user_id)));
        if (this.photoSelectedTypeFinal != 2) goto L17;
        showAvatarProgress(true, false);
    L26:
        updateCustomPhotoInfo();
        return;
    L17:
        createServiceMessageLocal(r14, r17, r21);
    L18:
        TLRPC.User r32 = getMessagesController().getUser(Long.valueOf(this.user_id));
        if (this.suggestPhotoMessageFinal != null) goto L25;
        if (r32 == null) goto L25;
        if (r16 == null) goto L23;
        boolean r4 = true;
    L24:
        PhotoUtilities.applyPhotoToUser(r14, r17, r4, r32, true);
        ArrayList r1 = new ArrayList();
        r1.add(r32);
        getMessagesStorage().putUsersAndChats(r1, null, false, true);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadDialogPhotos, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_AVATAR)});
        goto L25
    L23:
        r4 = false;
    L25:
        sendPhotoChangedRequest(this.avatar, r17.location, r15, r16, r18, r19, this.photoSelectedTypeFinal);
        showAvatarProgress(false, true);
        goto L26
    L8:
        if (r3 != 1) goto L11;
        NavigationExt.backToFragment(this, new ContactAddActivity$$ExternalSyntheticLambda21(this));
        goto L11
    }

    private /* synthetic */ void lambda$fillItems$15(TLRPC.User r3) {
        if (r3 != null) goto L4;
    L13:
        TLRPC.UserFull r32 = getMessagesController().getUserFull(this.user_id);
        if (r32 == null) goto L20;
        TLRPC.TL_textWithEntities r33 = r32.note;
        EditTextCell r0 = this.noteField;
        if (r33 == null) goto L18;
        r0.setText(r33);
        goto L20
    L18:
        r0.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    L20:
        if (this.focusNotes == false) goto L23;
        this.noteField.editText.requestFocus();
        AndroidUtilities.showKeyboard(this.noteField.editText);
        return;
    L23:
        return;
    L4:
        if (this.firstNameFromCard != null) goto L13;
        if (this.lastNameFromCard != null) goto L13;
        if (r3.phone != null) goto L12;
        String r02 = this.phone;
        if (r02 == null) goto L12;
        r3.phone = PhoneFormat.stripExceptNumbers(r02);
    L12:
        this.firstNameField.setText(r3.first_name);
        EditTextCaption r03 = this.firstNameField.editText;
        r03.setSelection(r03.length());
        this.lastNameField.setText(r3.last_name);
        goto L13
    }

    private /* synthetic */ void lambda$fillItems$16() {
        if (this.focusNotes == false) goto L6;
        this.noteField.editText.requestFocus();
        AndroidUtilities.showKeyboard(this.noteField.editText);
        return;
    }

    private /* synthetic */ void lambda$getThemeDescriptions$23() {
        if (this.avatarImage == null) goto L9;
        TLRPC.User r0 = getMessagesController().getUser(Long.valueOf(this.user_id));
        if (r0 == null) goto L10;
        this.avatarDrawable.setInfo(this.currentAccount, r0);
        this.avatarImage.invalidate();
        return;
    L10:
        return;
    }

    private /* synthetic */ void lambda$onItemClick$17(TLRPC.User r2, AlertDialog r3, int r4) {
        ArrayList<TLRPC.User> r32 = new ArrayList();
        r32.add(r2);
        getContactsController().deleteContact(r32, true);
        if (r2 == null) goto L5;
        r2.contact = false;
    L5:
        finishFragment();
    }

    private /* synthetic */ void lambda$sendPhotoChangedRequest$21(TLRPC.FileLocation r11, TLRPC.InputFile r12, TLObject r13, TLRPC.FileLocation r14, int r15) {
        if (this.suggestPhotoMessageFinal != null) goto L30;
        if (r11 != null) goto L8;
        if (r12 != null) goto L8;
        return;
    L8:
        if (r13 == null) goto L29;
        TLRPC.TL_photos_photo r132 = (TLRPC.TL_photos_photo) r13;
        ArrayList<TLRPC.PhotoSize> r122 = r132.photo.sizes;
        TLRPC.User r0 = getMessagesController().getUser(Long.valueOf(this.user_id));
        TLRPC.UserFull r1 = MessagesController.getInstance(this.currentAccount).getUserFull(this.user_id);
        if (r1 == null) goto L13;
        r1.personal_photo = r132.photo;
        r1.flags |= TLObject.FLAG_21;
        getMessagesStorage().updateUserInfo(r1, true);
    L13:
        if (r0 == null) goto L27;
        TLRPC.PhotoSize r3 = FileLoader.getClosestPhotoSizeWithSize(r122, 100);
        TLRPC.PhotoSize r123 = FileLoader.getClosestPhotoSizeWithSize(r122, 1000);
        if (r3 == null) goto L18;
        if (r11 == null) goto L18;
        FileLoader.getInstance(this.currentAccount).getPathToAttach(r11, true).renameTo(FileLoader.getInstance(this.currentAccount).getPathToAttach(r3, true));
        ImageLoader.getInstance().replaceImageInCache(r11.volume_id + "_" + r11.local_id + "@50_50", r3.location.volume_id + "_" + r3.location.local_id + "@50_50", ImageLocation.getForUser(this.currentAccount, r0, 1), false);
    L18:
        if (r123 == null) goto L21;
        if (r14 == null) goto L21;
        FileLoader.getInstance(this.currentAccount).getPathToAttach(r14, true).renameTo(FileLoader.getInstance(this.currentAccount).getPathToAttach(r123, true));
    L21:
        PhotoUtilities.applyPhotoToUser(r132.photo, r0, true);
        ArrayList r112 = new ArrayList();
        r112.add(r0);
        getMessagesStorage().putUsersAndChats(r112, null, false, true);
        getMessagesController().getDialogPhotos(this.user_id).addPhotoAtStart(r132.photo);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadDialogPhotos, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_AVATAR)});
        if (getParentActivity() == null) goto L27;
        if (r15 != 2) goto L26;
        BulletinFactory.of(this).createUsersBulletin(r112, AndroidUtilities.replaceTags(LocaleController.formatString("UserCustomPhotoSeted", R.string.UserCustomPhotoSeted, new Object[]{r0.first_name}))).show();
        goto L27
    L26:
        BulletinFactory.of(this).createUsersBulletin(r112, AndroidUtilities.replaceTags(LocaleController.formatString("UserCustomPhotoSeted", R.string.UserCustomPhotoSeted, new Object[]{r0.first_name}))).show();
    L27:
        this.avatar = null;
        updateCustomPhotoInfo();
        return;
    L29:
        return;
    }

    private /* synthetic */ void lambda$sendPhotoChangedRequest$22(TLRPC.FileLocation r1, TLRPC.InputFile r2, TLRPC.FileLocation r3, int r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new ContactAddActivity$$ExternalSyntheticLambda25(this, r1, r2, r5, r3, r4));
    }

    private void onItemClick(UItem r1, View r2, int r3, float r4, float r5) {
        int r12 = r1.id;
        if (r12 != 1) goto L12;
        TLRPC.User r13 = getMessagesController().getUser(Long.valueOf(this.user_id));
        if (r13 != null) goto L7;
        return;
    L7:
        if (getParentActivity() == null) goto L17;
        new AlertDialog.Builder(getParentActivity(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.DeleteContact)).setMessage(LocaleController.getString(R.string.AreYouSureDeleteContact)).setPositiveButton(LocaleController.getString(R.string.Delete), new ContactAddActivity$$ExternalSyntheticLambda12(this, r13)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).makeRed(-1).show();
        return;
    L17:
        return;
    L12:
        if (r12 != 2) goto L16;
        boolean r14 = !this.checkShare;
        this.checkShare = r14;
        ((TextCheckCell) r2).setChecked(r14);
        return;
    }

    private void sendPhotoChangedRequest(TLRPC.FileLocation r8, TLRPC.FileLocation r9, TLRPC.InputFile r10, TLRPC.InputFile r11, TLRPC.VideoSize r12, double r13, int r15) {
        TLRPC.TL_photos_uploadContactProfilePhoto r0 = new TLRPC.TL_photos_uploadContactProfilePhoto();
        r0.user_id = getMessagesController().getInputUser(this.user_id);
        if (r10 == null) goto L5;
        r0.file = r10;
        r0.flags |= 1;
    L5:
        if (r11 == null) goto L7;
        r0.video = r11;
        int r102 = r0.flags;
        r0.video_start_ts = r13;
        r0.flags = r102 | 6;
    L7:
        if (r12 == null) goto L9;
        r0.flags |= 32;
        r0.video_emoji_markup = r12;
    L9:
        if (r15 != 1) goto L11;
        r0.suggest = true;
        r0.flags |= 8;
    L12:
        getConnectionsManager().sendRequest(r0, new ContactAddActivity$$ExternalSyntheticLambda22(this, r8, r11, r9, r15));
        return;
    L11:
        r0.save = true;
        r0.flags |= 16;
        goto L12
    }

    private void showAvatarProgress(boolean r9, boolean r10) {
        if (this.avatarProgressView != null) goto L5;
        return;
    L5:
        AnimatorSet r0 = this.avatarAnimation;
        if (r0 == null) goto L9;
        r0.cancel();
        this.avatarAnimation = null;
    L9:
        if (r10 == false) goto L16;
        AnimatorSet r102 = new AnimatorSet();
        this.avatarAnimation = r102;
        RadialProgressView r3 = this.avatarProgressView;
        if (r9 == false) goto L13;
        r3.setVisibility(0);
        this.avatarOverlay.setVisibility(0);
        AnimatorSet r103 = this.avatarAnimation;
        RadialProgressView r02 = this.avatarProgressView;
        Property r32 = View.ALPHA;
        r103.playTogether(new Animator[]{ObjectAnimator.ofFloat(r02, r32, new float[]{1.0f}), ObjectAnimator.ofFloat(this.avatarOverlay, r32, new float[]{1.0f})});
    L14:
        this.avatarAnimation.setDuration(180);
        this.avatarAnimation.addListener(new AnonymousClass6(this, r9));
        this.avatarAnimation.start();
        return;
    L13:
        Property r1 = View.ALPHA;
        r102.playTogether(new Animator[]{ObjectAnimator.ofFloat(r3, r1, new float[]{0.0f}), ObjectAnimator.ofFloat(this.avatarOverlay, r1, new float[]{0.0f})});
        goto L14
    L16:
        RadialProgressView r104 = this.avatarProgressView;
        if (r9 == false) goto L20;
        r104.setAlpha(1.0f);
        this.avatarProgressView.setVisibility(0);
        this.avatarOverlay.setAlpha(1.0f);
        this.avatarOverlay.setVisibility(0);
        return;
    L20:
        r104.setAlpha(0.0f);
        this.avatarProgressView.setVisibility(4);
        this.avatarOverlay.setAlpha(0.0f);
        this.avatarOverlay.setVisibility(4);
    }

    private void updateAvatarLayout() {
        if (this.nameTextView == null) goto L17;
        TLRPC.User r0 = getMessagesController().getUser(Long.valueOf(this.user_id));
        if (r0 == null) goto L18;
        boolean r1 = TextUtils.isEmpty(getPhone());
        TextView r2 = this.nameTextView;
        if (r1 == false) goto L11;
        r2.setText(LocaleController.getString(R.string.MobileHidden));
    L12:
        this.onlineTextView.setText(LocaleController.formatUserStatus(this.currentAccount, r0));
        if (this.avatar != null) goto L16;
        BackupImageView r12 = this.avatarImage;
        AvatarDrawable r22 = new AvatarDrawable(r0);
        this.avatarDrawable = r22;
        r12.setForUserOrChat(r0, r22);
        return;
    L16:
        return;
    L11:
        r2.setText(PhoneFormat.getInstance().format("+" + getPhone()));
        goto L12
    L18:
        return;
    }

    private void updateCustomPhotoInfo() {
        if (this.addContact == false) goto L5;
        return;
    L5:
        TLRPC.User r0 = getMessagesController().getUser(Long.valueOf(this.user_id));
        TLRPC.UserProfilePhoto r1 = r0.photo;
        if (r1 == null) goto L13;
        if (r1.personal == false) goto L13;
        TLRPC.Photo r12 = this.prevAvatar;
        if (r12 == null) goto L13;
        ImageLocation r13 = ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r12.sizes, 1000), this.prevAvatar);
        this.oldAvatarView.setImage(r13, "50_50", this.avatarDrawable, null);
    L13:
        if (this.avatarDrawable != null) goto L15;
        this.avatarDrawable = new AvatarDrawable(r0);
    L15:
        TLRPC.FileLocation r14 = this.avatar;
        BackupImageView r3 = this.avatarImage;
        if (r14 != null) goto L19;
        r3.setForUserOrChat(r0, this.avatarDrawable);
        return;
    L19:
        r3.setImage(ImageLocation.getForLocal(r14), "50_50", this.avatarDrawable, getMessagesController().getUser(Long.valueOf(this.user_id)));
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public boolean canFinishFragment() {
        if (this.photoSelectedTypeFinal == 1) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r24) {
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_avatar_actionBarSelectorBlue, this.resourcesProvider), false);
        this.actionBar.setItemsColor(Theme.getColor(Theme.key_actionBarDefaultIcon, this.resourcesProvider), false);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        boolean r1 = this.addContact;
        ActionBar r3 = this.actionBar;
        if (r1 == false) goto L5;
        r3.setTitle(LocaleController.getString(R.string.NewContact));
    L6:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.doneButton = this.actionBar.createMenu().addItem(1, LocaleController.getString(R.string.Done).toUpperCase());
        FrameLayout r10 = new FrameLayout(r24);
        int r11 = Theme.key_windowBackgroundGray;
        r10.setBackgroundColor(getThemedColor(r11));
        FrameLayout r12 = new FrameLayout(r24);
        this.infoLayout = r12;
        int r122 = Theme.key_windowBackgroundWhite;
        r12.setBackgroundColor(getThemedColor(r122));
        BackupImageView r13 = new BackupImageView(r24);
        this.avatarImage = r13;
        r13.setRoundRadius(ExteraConfig.getAvatarCorners(64.0f));
        FrameLayout r14 = this.infoLayout;
        BackupImageView r32 = this.avatarImage;
        int r5 = 3;
        if (LocaleController.isRTL == false) goto L9;
        int r4 = 5;
    L10:
        r14.addView(r32, LayoutHelper.createFrame(64, 64.0f, r4 | 48, 16.0f, 13.0f, 16.0f, 13.0f));
        Paint r15 = new Paint(1);
        r15.setColor(1426063360);
        AnonymousClass2 r33 = new AnonymousClass2(this, r24, r15);
        this.avatarOverlay = r33;
        FrameLayout r16 = this.infoLayout;
        if (LocaleController.isRTL == false) goto L13;
        int r42 = 5;
    L14:
        r16.addView(r33, LayoutHelper.createFrame(64, 64.0f, r42 | 48, 16.0f, 13.0f, 16.0f, 13.0f));
        RadialProgressView r17 = new RadialProgressView(r24);
        this.avatarProgressView = r17;
        r17.setSize(AndroidUtilities.dp(30.0f));
        this.avatarProgressView.setProgressColor(-1);
        this.avatarProgressView.setNoProgress(false);
        FrameLayout r18 = this.infoLayout;
        RadialProgressView r34 = this.avatarProgressView;
        if (LocaleController.isRTL == false) goto L17;
        int r43 = 5;
    L18:
        r18.addView(r34, LayoutHelper.createFrame(64, 64.0f, r43 | 48, 16.0f, 13.0f, 16.0f, 13.0f));
        showAvatarProgress(false, false);
        TextView r19 = new TextView(r24);
        this.nameTextView = r19;
        r19.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        this.nameTextView.setTextSize(1, 18.0f);
        this.nameTextView.setLines(1);
        this.nameTextView.setMaxLines(1);
        this.nameTextView.setSingleLine(true);
        TextView r110 = this.nameTextView;
        TextUtils.TruncateAt r35 = TextUtils.TruncateAt.END;
        r110.setEllipsize(r35);
        TextView r111 = this.nameTextView;
        if (LocaleController.isRTL == false) goto L21;
        int r44 = 5;
    L22:
        r111.setGravity(r44);
        this.nameTextView.setTypeface(AndroidUtilities.bold());
        FrameLayout r112 = this.infoLayout;
        TextView r45 = this.nameTextView;
        boolean r6 = LocaleController.isRTL;
        if (r6 == false) goto L25;
        int r7 = 5;
    L26:
        int r172 = r7 | 48;
        if (r6 == false) goto L29;
        float r182 = 0.0f;
    L30:
        if (r6 == false) goto L32;
        float r20 = 94.0f;
    L33:
        r112.addView(r45, LayoutHelper.createFrame(-2, -2.0f, r172, r182, 25.66f, r20, 0.0f));
        TextView r113 = new TextView(r24);
        this.onlineTextView = r113;
        r113.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3, this.resourcesProvider));
        this.onlineTextView.setTextSize(1, 14.0f);
        this.onlineTextView.setLines(1);
        this.onlineTextView.setMaxLines(1);
        this.onlineTextView.setSingleLine(true);
        this.onlineTextView.setEllipsize(r35);
        TextView r114 = this.onlineTextView;
        if (LocaleController.isRTL == false) goto L36;
        int r36 = 5;
    L37:
        r114.setGravity(r36);
        FrameLayout r115 = this.infoLayout;
        TextView r37 = this.onlineTextView;
        boolean r46 = LocaleController.isRTL;
        if (r46 == false) goto L40;
        r5 = 5;
    L40:
        int r173 = r5 | 48;
        if (r46 == false) goto L43;
        float r183 = 0.0f;
    L44:
        if (r46 == false) goto L46;
        float r202 = 94.0f;
    L47:
        r115.addView(r37, LayoutHelper.createFrame(-2, -2.0f, r173, r183, 49.66f, r202, 0.0f));
        EditTextCell r116 = new EditTextCell(r24, LocaleController.getString(R.string.FirstName), false, false, -1, this.resourcesProvider);
        this.firstNameField = r116;
        r116.editText.setImeOptions(5);
        this.firstNameField.setBackgroundColor(getThemedColor(r122));
        this.firstNameField.setDivider(true);
        this.firstNameField.editText.setOnEditorActionListener(new ContactAddActivity$$ExternalSyntheticLambda2(this));
        this.firstNameField.editText.setOnFocusChangeListener(new AnonymousClass3(this));
        this.firstNameField.setText(this.firstNameFromCard);
        EditTextCell r117 = new EditTextCell(r24, LocaleController.getString(R.string.LastName), false, false, -1, this.resourcesProvider);
        this.lastNameField = r117;
        r117.editText.setImeOptions(5);
        this.lastNameField.setBackgroundColor(getThemedColor(r122));
        this.lastNameField.editText.setOnEditorActionListener(new ContactAddActivity$$ExternalSyntheticLambda3(this));
        this.lastNameField.setText(this.lastNameFromCard);
        EditTextCell r118 = new EditTextCell(r24, LocaleController.getString(R.string.AddNotes), true, true, getMessagesController().config.contactNoteLengthLimit.get(), this.resourcesProvider);
        this.noteField = r118;
        r118.editText.setLinkTextColor(getThemedColor(Theme.key_chat_messageLinkIn));
        this.noteField.editText.setImeOptions(6);
        this.noteField.setBackgroundColor(getThemedColor(r122));
        this.noteField.editText.setOnEditorActionListener(new ContactAddActivity$$ExternalSyntheticLambda4(this));
        if (this.addContact == true) goto L58;
        TLRPC.User r119 = getMessagesController().getUser(Long.valueOf(this.user_id));
        TextCell r38 = new TextCell(r24, this.resourcesProvider);
        this.suggestPhoto = r38;
        r38.setTextAndIcon(LocaleController.formatString(R.string.SuggestUserPhoto, new Object[]{r119.first_name}), R.drawable.msg_addphoto, true);
        this.suggestPhoto.setBackground(Theme.getSelectorDrawable(true, this.resourcesProvider));
        TextCell r39 = this.suggestPhoto;
        int r47 = Theme.key_windowBackgroundWhiteBlueIcon;
        int r52 = Theme.key_windowBackgroundWhiteBlueButton;
        r39.setColors(r47, r52);
        RLottieDrawable r152 = new RLottieDrawable(R.raw.photo_suggest_icon, AndroidUtilities.dp(50.0f), AndroidUtilities.dp(50.0f), false, null);
        this.suggestPhoto.imageView.setTranslationX(-AndroidUtilities.dp(8.0f));
        this.suggestPhoto.imageView.setAnimation(r152);
        this.suggestPhoto.setOnClickListener(new ContactAddActivity$$ExternalSyntheticLambda5(this, r119, r152));
        TextCell r62 = new TextCell(r24, this.resourcesProvider);
        this.setAvatarCell = r62;
        r62.setTextAndIcon(LocaleController.formatString(R.string.UserSetPhoto, new Object[]{r119.first_name}), R.drawable.msg_addphoto, false);
        this.setAvatarCell.setBackground(Theme.getSelectorDrawable(true, this.resourcesProvider));
        this.setAvatarCell.setColors(r47, r52);
        RLottieDrawable r153 = new RLottieDrawable(R.raw.camera_outline, AndroidUtilities.dp(50.0f), AndroidUtilities.dp(50.0f), false, null);
        this.setAvatarCell.imageView.setTranslationX(-AndroidUtilities.dp(8.0f));
        this.setAvatarCell.imageView.setAnimation(r153);
        this.setAvatarCell.setOnClickListener(new ContactAddActivity$$ExternalSyntheticLambda6(this, r119, r153));
        this.oldAvatarView = new BackupImageView(r24);
        this.oldPhotoCell = new AnonymousClass4(this, r24, this.resourcesProvider);
        if (this.avatarDrawable != null) goto L52;
        this.avatarDrawable = new AvatarDrawable(r119);
    L52:
        this.oldAvatarView.setForUserOrChat(r119.photo, this.avatarDrawable);
        this.oldPhotoCell.addView(this.oldAvatarView, LayoutHelper.createFrame(30, 30.0f, 16, 21.0f, 0.0f, 21.0f, 0.0f));
        this.oldPhotoCell.setText(LocaleController.getString(R.string.ResetToOriginalPhoto), false);
        this.oldPhotoCell.getImageView().setVisibility(0);
        this.oldPhotoCell.setBackground(Theme.getSelectorDrawable(true, this.resourcesProvider));
        this.oldPhotoCell.setColors(r47, r52);
        this.oldPhotoCell.setOnClickListener(new ContactAddActivity$$ExternalSyntheticLambda7(this, r24, r119));
        TextCell r310 = new TextCell(r24, this.resourcesProvider);
        this.suggestBirthday = r310;
        r310.setTextAndIcon(LocaleController.formatString(R.string.UserSuggestBirthday, new Object[0]), R.drawable.menu_birthday, false);
        this.suggestBirthday.setBackground(Theme.getSelectorDrawable(true, this.resourcesProvider));
        this.suggestBirthday.setColors(r47, r52);
        this.suggestBirthday.setNeedDivider(true);
        this.suggestBirthday.imageView.setTranslationX(AndroidUtilities.dp(4.0f));
        this.suggestBirthday.setOnClickListener(new ContactAddActivity$$ExternalSyntheticLambda8(this, r119));
        TLRPC.UserFull r120 = getMessagesController().getUserFull(this.user_id);
        if (r120 == null) goto L57;
        TLRPC.Photo r2 = r120.profile_photo;
        this.prevAvatar = r2;
        if (r2 != null) goto L57;
        this.prevAvatar = r120.fallback_photo;
    L57:
        updateCustomPhotoInfo();
    L58:
        UniversalRecyclerView r121 = new UniversalRecyclerView(this, new ContactAddActivity$$ExternalSyntheticLambda9(this), new ContactAddActivity$$ExternalSyntheticLambda10(this), null);
        this.listView = r121;
        r121.setSections();
        this.listView.setOnScrollListener(new AnonymousClass5(this, r10));
        this.listView.setBackgroundColor(getThemedColor(r11));
        r10.addView(this.listView, LayoutHelper.createFrame(-1, -1, 119));
        this.actionBar.setAdaptiveBackground(this.listView);
        if (this.addContact == true) goto L61;
    L63:
        this.listView.adapter.update(false);
        this.fragmentView = r10;
        return r10;
    L61:
        if (this.needAddException == false) goto L63;
        this.checkShare = true;
        goto L63
    L46:
        r202 = 0.0f;
        goto L47
    L43:
        r183 = 94.0f;
        goto L44
    L36:
        r36 = 3;
        goto L37
    L32:
        r20 = 0.0f;
        goto L33
    L29:
        r182 = 94.0f;
        goto L30
    L25:
        r7 = 3;
        goto L26
    L21:
        r44 = 3;
        goto L22
    L17:
        r43 = 3;
        goto L18
    L13:
        r42 = 3;
        goto L14
    L9:
        r4 = 3;
        goto L10
    L5:
        r3.setTitle(LocaleController.getString(R.string.EditContact));
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r2, int r3, Object... r4) {
        if (r2 != NotificationCenter.updateInterfaces) goto L11;
        int r22 = ((Integer) r4[0]).intValue();
        if ((MessagesController.UPDATE_MASK_AVATAR & r22) == 0) goto L7;
    L8:
        updateAvatarLayout();
        return;
    L7:
        if ((r22 & MessagesController.UPDATE_MASK_STATUS) != 0) goto L8;
        return;
    L11:
        if (r2 != NotificationCenter.dialogPhotosUpdate) goto L29;
        MessagesController.DialogPhotos r23 = (MessagesController.DialogPhotos) r4[0];
        if (r23 != this.dialogPhotos) goto L30;
        ArrayList r32 = new ArrayList(r23.photos);
        int r24 = 0;
    L16:
        if (r24 >= r32.size()) goto L22;
        if (r32.get(r24) != null) goto L20;
        r32.remove(r24);
        r24 = r24 - 1;
    L20:
        r24 = r24 + 1;
        goto L16
    L22:
        if (r32.size() <= 0) goto L31;
        this.prevAvatar = (TLRPC.Photo) r32.get(0);
        updateCustomPhotoInfo();
        return;
    L31:
        return;
    L30:
        return;
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void didStartUpload(boolean r1, boolean r2) {
        RadialProgressView r12 = this.avatarProgressView;
        if (r12 != null) goto L5;
        return;
    L5:
        this.photoSelectedTypeFinal = this.photoSelectedType;
        r12.setProgress(0.0f);
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void didUploadFailed() {
        AndroidUtilities.runOnUIThread(new ContactAddActivity$$ExternalSyntheticLambda1(this));
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void didUploadPhoto(TLRPC.InputFile r4, TLRPC.InputFile r5, double r6, String r8, TLRPC.PhotoSize r9, TLRPC.PhotoSize r10, boolean r11, TLRPC.VideoSize r12) {
        AndroidUtilities.runOnUIThread(new ContactAddActivity$$ExternalSyntheticLambda11(this, r10, r4, r5, r9, r12, r6, r11));
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public String getInitialSearchString() {
        return super.getInitialSearchString();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public Theme.ResourcesProvider getResourceProvider() {
        return this.resourcesProvider;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        ContactAddActivity$$ExternalSyntheticLambda0 r8 = new ContactAddActivity$$ExternalSyntheticLambda0(this);
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        TextView r14 = this.nameTextView;
        int r15 = ThemeDescription.FLAG_TEXTCOLOR;
        int r23 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(r14, r15, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.onlineTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteGrayText3));
        r1.add(new ThemeDescription(this.firstNameField, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r23));
        EditTextCell r10 = this.firstNameField;
        int r11 = ThemeDescription.FLAG_HINTTEXTCOLOR;
        int r16 = Theme.key_windowBackgroundWhiteHintText;
        r1.add(new ThemeDescription(r10, r11, null, null, null, null, r16));
        EditTextCell r112 = this.firstNameField;
        int r12 = ThemeDescription.FLAG_BACKGROUNDFILTER;
        int r17 = Theme.key_windowBackgroundWhiteInputField;
        r1.add(new ThemeDescription(r112, r12, null, null, null, null, r17));
        EditTextCell r122 = this.firstNameField;
        int r13 = ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE;
        int r18 = Theme.key_windowBackgroundWhiteInputFieldActivated;
        r1.add(new ThemeDescription(r122, r13, null, null, null, null, r18));
        r1.add(new ThemeDescription(this.lastNameField, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.lastNameField, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.lastNameField, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r17));
        r1.add(new ThemeDescription(this.lastNameField, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, r18));
        r1.add(new ThemeDescription(null, 0, null, null, Theme.avatarDrawables, r8, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.updateInterfaces);
        getNotificationCenter().addObserver(this, NotificationCenter.dialogPhotosUpdate);
        this.user_id = getArguments().getLong("user_id", 0);
        this.phone = getArguments().getString("phone");
        this.firstNameFromCard = getArguments().getString("first_name_card");
        this.lastNameFromCard = getArguments().getString("last_name_card");
        this.addContact = getArguments().getBoolean("addContact", false);
        this.focusNotes = getArguments().getBoolean("focus_notes", false);
        this.needAddException = MessagesController.getNotificationsSettings(this.currentAccount).getBoolean("dialog_bar_exception" + this.user_id, false);
        if (this.user_id == 0) goto L5;
        TLRPC.User r0 = getMessagesController().getUser(Long.valueOf(this.user_id));
    L6:
        ImageUpdater r1 = this.imageUpdater;
        if (r1 == null) goto L9;
        r1.parentFragment = this;
        r1.setDelegate(this);
    L9:
        this.dialogPhotos = MessagesController.getInstance(this.currentAccount).getDialogPhotos(this.user_id);
        if (r0 != null) goto L12;
    L15:
        return false;
    L12:
        if (super.onFragmentCreate() == false) goto L15;
        return true;
    L5:
        r0 = null;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.updateInterfaces);
        getNotificationCenter().removeObserver(this, NotificationCenter.dialogPhotosUpdate);
        ImageUpdater r2 = this.imageUpdater;
        if (r2 == null) goto L6;
        r2.clear();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        this.paused = true;
        this.imageUpdater.onPause();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        updateAvatarLayout();
        this.imageUpdater.onResume();
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void onUploadProgressChanged(float r1) {
        RadialProgressView r0 = this.avatarProgressView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setProgress(r1);
    }

    public void setDelegate(ContactAddActivityDelegate r1) {
        this.delegate = r1;
    }
}
