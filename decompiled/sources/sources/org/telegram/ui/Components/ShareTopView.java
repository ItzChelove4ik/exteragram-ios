package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class ShareTopView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private int currentAccount;
    private int currentMode;
    private String dismissedMessage;
    private final ArrayList<CharSequence> foundUrls;
    private Runnable hintRunnable;
    private View.OnClickListener layoutClickListener;
    public final Layout[] layouts;
    private final HashMap<String, TLRPC.WebPage> linkPreviewCache;
    private int linkRequestId;
    private int linkRequestSerial;
    private boolean linkSearchEnabled;
    private TLRPC.WebPage loadedWebPage;
    private ArrayList<MediaController.PhotoEntry> mediaEntries;
    private OnModeChangeListener modeChangeListener;
    private CharSequence pendingHintText;
    private boolean previewEnabled;
    private final ArrayList<Long> recipients;
    private final Theme.ResourcesProvider resourcesProvider;
    private long selfId;
    private boolean showingHint;

    /* loaded from: classes7.dex */
    public class Layout extends FrameLayout {
        public boolean active;
        public final ImageView closeButton;
        public final LinearLayout container;
        public final ImageView icon;
        public final BackupImageView[] images;
        public final FrameLayout imagesContainer;
        public final BackupImageView linkImage;
        public final SimpleTextView name;
        public final SimpleTextView obj;
        public final SimpleTextView objHint;
        public final FrameLayout textLayout;
        final /* synthetic */ ShareTopView this$0;

        public static /* synthetic */ void $r8$lambda$uuGWeCM4WBFFdQMtMOks48DRLSk(Layout r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public Layout(ShareTopView r18, Context r19, Theme.ResourcesProvider r20) {
            this.this$0 = r18;
            super(r19);
            this.active = true;
            LinearLayout r4 = new LinearLayout(r19);
            this.container = r4;
            r4.setOrientation(0);
            r4.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, r20), 20, 20, 6, 6));
            ScaleStateListAnimator.apply(r4, 0.02f, 1.2f);
            addView(r4, LayoutHelper.createFrame(-1, -1.0f, 119, 4.0f, 4.0f, 4.0f, 4.0f));
            ImageView r6 = new ImageView(r19);
            this.icon = r6;
            r6.setScaleType(ImageView.ScaleType.CENTER);
            r6.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_replyPanelIcons, r20), PorterDuff.Mode.MULTIPLY));
            r4.addView(r6, LayoutHelper.createLinear(40, 38, 51));
            FrameLayout r62 = new FrameLayout(r19);
            this.imagesContainer = r62;
            r4.addView(r62, LayoutHelper.createLinear(-2, -1, 115, 6, 0, 0, 0));
            BackupImageView[] r42 = new BackupImageView[3];
            this.images = r42;
            int r43 = r42.length - 1;
        L4:
            if (r43 < 0) goto L6;
            this.images[r43] = new BackupImageView(r19);
            this.images[r43].setRoundRadius(AndroidUtilities.dp(6.0f));
            this.images[r43].setVisibility(8);
            int r9 = 32 - (r43 * 4);
            this.imagesContainer.addView(this.images[r43], LayoutHelper.createFrame(r9, r9, 19, r43 * 12, 0.0f, 0.0f, 0.0f));
            r43 = r43 - 1;
            goto L4
        L6:
            BackupImageView r44 = new BackupImageView(r19);
            this.linkImage = r44;
            r44.setRoundRadius(AndroidUtilities.dp(4.0f));
            r44.setVisibility(8);
            this.container.addView(r44, LayoutHelper.createLinear(34, 34, 19, 6, 0, 0, 0));
            FrameLayout r45 = new FrameLayout(r19);
            this.textLayout = r45;
            this.container.addView(r45, LayoutHelper.createLinear(0, -1, 1.0f, 119));
            SimpleTextView r5 = new SimpleTextView(r19);
            this.name = r5;
            r5.setTextSize(14);
            r5.setTypeface(AndroidUtilities.bold());
            r5.setTextColor(Theme.getColor(Theme.key_chat_replyPanelName, r20));
            r45.addView(r5, LayoutHelper.createFrame(-1, 18.0f, 51, 8.0f, 2.0f, 8.0f, 0.0f));
            SimpleTextView r52 = new SimpleTextView(r19);
            this.obj = r52;
            r52.setTextSize(14);
            int r8 = Theme.key_glass_defaultText;
            r52.setTextColor(Theme.getColor(r8, r20));
            r45.addView(r52, LayoutHelper.createFrame(-1, 18.0f, 51, 8.0f, 20.0f, 8.0f, 0.0f));
            SimpleTextView r53 = new SimpleTextView(r19);
            this.objHint = r53;
            r53.setTextSize(14);
            r53.setTextColor(Theme.getColor(r8, r20));
            r53.setAlpha(0.0f);
            r45.addView(r53, LayoutHelper.createFrame(-1, 18.0f, 51, 8.0f, 20.0f, 8.0f, 0.0f));
            ImageView r46 = new ImageView(r19);
            this.closeButton = r46;
            r46.setScaleType(ImageView.ScaleType.CENTER);
            r46.setImageResource(R.drawable.input_clear);
            r46.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_glass_defaultIcon, r20), PorterDuff.Mode.MULTIPLY));
            r46.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector, r20), 1, AndroidUtilities.dp(18.0f)));
            r46.setVisibility(8);
            r46.setOnClickListener(new ShareTopView$Layout$$ExternalSyntheticLambda0(this));
            this.container.addView(r46, LayoutHelper.createLinear(36, 36, 21, 0, 0, 4, 0));
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            this.this$0.dismissWebPagePreview();
        }
    }

    /* loaded from: classes7.dex */
    public interface OnModeChangeListener {
        void onModeChanged(int r1, int r2);
    }

    /* renamed from: $r8$lambda$6Nlf2cjol-otbU7_FgxKU3i3ieI, reason: not valid java name */
    public static /* synthetic */ void m12760$r8$lambda$6Nlf2cjolotbU7_FgxKU3i3ieI(ShareTopView r0) {
        r0.lambda$startHintRotation$3();
    }

    public static /* synthetic */ void $r8$lambda$gNmDVhinTY1oN0PGxOCJ9qmhbuE(Layout r1) {
        r1.setVisibility(8);
    }

    public static /* synthetic */ void $r8$lambda$nyh6VGAi4N6UUskAwNuoSc9xuuM(ShareTopView r0, int r1, TLObject r2, String r3) {
        r0.lambda$requestLinkPreview$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$zY9mcGV5kM599tjfwGXtmI_rAaw(ShareTopView r0, int r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestLinkPreview$2(r1, r2, r3, r4);
    }

    public ShareTopView(Context r7, Theme.ResourcesProvider r8) {
        super(r7);
        this.layouts = new Layout[2];
        this.currentMode = 0;
        this.recipients = new ArrayList();
        this.previewEnabled = true;
        this.foundUrls = new ArrayList();
        this.linkPreviewCache = new HashMap();
        this.resourcesProvider = r8;
        int r2 = 0;
    L3:
        Layout[] r3 = this.layouts;
        if (r2 >= r3.length) goto L6;
        r3[r2] = new Layout(this, r7, r8);
        addView(this.layouts[r2], LayoutHelper.createFrame(-1, -1.0f));
        r2 = r2 + 1;
        goto L3
    L6:
        r3[0].setVisibility(0);
        this.layouts[1].setVisibility(8);
    }

    private void applyHintText(Layout r1) {
        CharSequence r0 = this.pendingHintText;
        if (r0 == null) goto L6;
        r1.objHint.setText(r0);
        return;
    }

    private void bindLinkLoaded(Layout r13, TLRPC.WebPage r14, String r15) {
        r13.icon.setImageResource(R.drawable.msg_link2);
        r13.icon.setVisibility(0);
        r13.imagesContainer.setVisibility(8);
        r13.closeButton.setVisibility(0);
        String r1 = r14.site_name;
        if (r1 != null) goto L5;
        r1 = r14.title;
    L5:
        if (r1 != null) goto L7;
        r1 = r15;
    L7:
        r13.name.setText(r1);
        String r12 = r14.title;
        if (r12 != null) goto L10;
    L12:
        r12 = r14.description;
    L13:
        if (r12 != null) goto L18;
        r12 = r14.display_url;
        if (r12 != null) goto L18;
        r12 = r15;
    L18:
        r13.obj.setText(r12);
        TLRPC.Photo r16 = r14.photo;
        if (r16 == null) goto L24;
        TLRPC.PhotoSize r17 = FileLoader.getClosestPhotoSizeWithSize(r16.sizes, 320);
        TLRPC.PhotoSize r3 = FileLoader.getClosestPhotoSizeWithSize(r14.photo.sizes, AndroidUtilities.dp(40.0f));
        BackupImageView r4 = r13.linkImage;
        if (r17 == null) goto L23;
        r4.setRoundRadius(AndroidUtilities.dp(4.0f));
        r13.linkImage.setImage(ImageLocation.getForObject(r17, r14.photo), "50_50", ImageLocation.getForObject(r3, r14.photo), "50_50_b", null, 0, 1, r14);
        r13.linkImage.setVisibility(0);
    L25:
        r13.container.setClickable(false);
        return;
    L23:
        r4.setVisibility(8);
        goto L25
    L24:
        r13.linkImage.setVisibility(8);
        goto L25
    L10:
        if (r14.site_name == null) goto L12;
        goto L12
    }

    private void bindLinkLoading(Layout r3, String r4) {
        r3.icon.setImageResource(R.drawable.msg_link2);
        r3.icon.setVisibility(0);
        r3.imagesContainer.setVisibility(8);
        r3.linkImage.setVisibility(8);
        r3.closeButton.setVisibility(0);
        r3.name.setText(LocaleController.getString(R.string.GettingLinkInfo));
        SimpleTextView r2 = r3.obj;
        if (r4 != null) goto L5;
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        r2.setText(r4);
        r3.container.setClickable(false);
    }

    private void bindMedia(Layout r9) {
        r9.icon.setImageResource(R.drawable.filled_forward);
        int r1 = 0;
        r9.icon.setVisibility(0);
        r9.linkImage.setVisibility(8);
        r9.imagesContainer.setVisibility(0);
        r9.closeButton.setVisibility(8);
        r9.container.setClickable(true);
        ArrayList<MediaController.PhotoEntry> r0 = this.mediaEntries;
        if (r0 != null) goto L5;
    L39:
        r9.name.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        r9.obj.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        BackupImageView[] r8 = r9.images;
        int r92 = r8.length;
    L40:
        if (r1 >= r92) goto L42;
        r8[r1].setVisibility(8);
        r1 = r1 + 1;
        goto L40
    L42:
        return;
    L5:
        if (r0.isEmpty() == true) goto L39;
        int r2 = r0.size();
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
    L8:
        if (r6 >= r2) goto L13;
        MediaController.PhotoEntry r7 = r0.get(r6);
        r6 = r6 + 1;
        if (r7.isVideo == true) goto L11;
        r5 = r5 + 1;
        goto L8
    L11:
        r4 = r4 + 1;
        goto L8
    L13:
        int r22 = r0.size();
        if (r22 != 1) goto L20;
        SimpleTextView r23 = r9.name;
        if (r0.get(0).isVideo == false) goto L18;
        int r42 = R.string.ShareSendVideo;
    L19:
        r23.setText(LocaleController.getString(r42));
    L26:
        r9.obj.setText(buildRecipientText(r9));
        BackupImageView r24 = r9.images[0];
        MediaController.PhotoEntry r52 = null;
        if (r0.size() <= 0) goto L29;
        MediaController.PhotoEntry r12 = r0.get(0);
    L30:
        bindThumb(r24, r12);
        BackupImageView r13 = r9.images[1];
        if (r0.size() <= 1) goto L33;
        MediaController.PhotoEntry r25 = r0.get(1);
    L34:
        bindThumb(r13, r25);
        BackupImageView r93 = r9.images[2];
        if (r0.size() <= 2) goto L37;
        r52 = r0.get(2);
    L37:
        bindThumb(r93, r52);
        return;
    L33:
        r25 = null;
        goto L34
    L29:
        r12 = null;
        goto L30
    L18:
        r42 = R.string.ShareSendPhoto;
        goto L19
    L20:
        if (r4 != 0) goto L22;
        r9.name.setText(LocaleController.formatPluralString("ShareSendPhotos", r22, new Object[0]));
        goto L26
    L22:
        SimpleTextView r43 = r9.name;
        if (r5 != 0) goto L25;
        r43.setText(LocaleController.formatPluralString("ShareSendVideos", r22, new Object[0]));
        goto L26
    L25:
        r43.setText(LocaleController.formatPluralString("ShareSendItems", r22, new Object[0]));
        goto L26
    }

    private void bindThumb(BackupImageView r5, MediaController.PhotoEntry r6) {
        if (r6 != null) goto L5;
        r5.setVisibility(8);
        return;
    L5:
        r5.setVisibility(0);
        r5.setOrientation(0, true);
        String r4 = r6.thumbPath;
        if (r4 == null) goto L10;
        r5.setImage(r4, null, null);
        return;
    L10:
        if (r6.path != null) goto L12;
        r5.setImageDrawable(null);
        return;
    L12:
        if (r6.isVideo == false) goto L15;
        r5.setImage("vthumb://" + r6.imageId + ":" + r6.path, null, null);
        return;
    L15:
        r5.setOrientation(r6.orientation, r6.invert, true);
        r5.setImage("thumb://" + r6.imageId + ":" + r6.path, null, null);
    }

    private CharSequence buildRecipientText(Layout r11) {
        if (this.recipients.isEmpty() == false) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        StringBuilder r0 = new StringBuilder();
        ArrayList<Long> r1 = this.recipients;
        int r2 = r1.size();
        int r4 = 0;
    L7:
        if (r4 >= r2) goto L19;
        Long r5 = r1.get(r4);
        r4 = r4 + 1;
        long r52 = r5.longValue();
        if (r0.length() <= 0) goto L12;
        r0.append(", ");
    L12:
        if (r52 == this.selfId) goto L13;
        int r7 = this.recipients.size();
        int r8 = this.currentAccount;
        if (r7 != 1) goto L17;
        String r53 = DialogObject.getName(r8, r52);
    L18:
        r0.append(r53);
        goto L7
    L17:
        r53 = DialogObject.getShortName(r8, r52);
        goto L18
    L13:
        r0.append(LocaleController.getString(R.string.SavedMessages));
        goto L7
    L19:
        String r02 = LocaleController.formatString(R.string.ShareSendToChats, new Object[]{r0.toString()});
        if (r11.obj.getMeasuredWidth() > 0) goto L23;
        int r12 = AndroidUtilities.displaySize.x - AndroidUtilities.dp(140.0f);
    L22:
        float r13 = r12;
        if (this.recipients.size() > 2) goto L31;
        if (r11.obj.getPaint().measureText(r02) > r13) goto L31;
        return r02;
    L31:
        return LocaleController.formatPluralString("ShareSendToMany", this.recipients.size(), new Object[0]);
    L23:
        r12 = r11.obj.getMeasuredWidth();
        goto L22
    }

    private void cancelLinkRequest() {
        if (this.linkRequestId == 0) goto L5;
        AccountInstance.getInstance(this.currentAccount).getConnectionsManager().cancelRequest(this.linkRequestId, true);
        this.linkRequestId = 0;
    L5:
        this.linkRequestSerial++;
    }

    private void doLinkSearch(CharSequence r4, boolean r5) {
        ArrayList<CharSequence> r42 = extractUrls(r4);
        if (r5 == false) goto L5;
    L7:
        this.foundUrls.clear();
        if (r42 == null) goto L10;
        this.foundUrls.addAll(r42);
    L10:
        boolean r0 = false;
        if (r42 != null) goto L13;
    L41:
        cancelLinkRequest();
        this.loadedWebPage = null;
        int r43 = this.currentMode;
        if (r43 == 0) goto L49;
        this.currentMode = 0;
        OnModeChangeListener r3 = this.modeChangeListener;
        if (r3 == null) goto L50;
        r3.onModeChanged(r43, 0);
        return;
    L50:
        return;
    L49:
        return;
    L13:
        if (r42.isEmpty() == true) goto L41;
        String r44 = TextUtils.join(" ", r42).toString();
        if (this.previewEnabled == true) goto L23;
        String r1 = this.dismissedMessage;
        if (r1 != null) goto L20;
    L22:
        this.previewEnabled = true;
        this.dismissedMessage = null;
        goto L23
    L20:
        if (r1.equals(r44) == false) goto L22;
        return;
    L23:
        int r52 = this.currentMode;
        if (r52 == 2) goto L27;
        if (r52 == 0) goto L27;
        r0 = true;
    L27:
        if (r52 == 2) goto L29;
        this.currentMode = 2;
    L29:
        if (r0 == false) goto L31;
        switchLayouts(true);
    L31:
        TLRPC.WebPage r02 = this.linkPreviewCache.get(r44);
        if (r02 == null) goto L34;
        this.loadedWebPage = r02;
        bindLinkLoaded(current(), r02, r44);
    L35:
        applyHintText(current());
        int r45 = this.currentMode;
        if (r52 == r45) goto L47;
        OnModeChangeListener r32 = this.modeChangeListener;
        if (r32 == null) goto L48;
        r32.onModeChanged(r52, r45);
        return;
    L48:
        return;
    L47:
        return;
    L34:
        bindLinkLoading(current(), r44);
        requestLinkPreview(r44);
        goto L35
    L5:
        if (sameUrls(r42) == false) goto L7;
    }

    public static String extractFirstUrl(CharSequence r3) {
        if (r3 != null) goto L5;
    L11:
        return null;
    L5:
        if (r3.length() == 0) goto L11;
        Matcher r1 = AndroidUtilities.WEB_URL.matcher(r3);     // Catch: Exception -> L12
        if (r1.find() == false) goto L11;
        return r3.subSequence(r1.start(), r1.end()).toString();
    }

    private static ArrayList<CharSequence> extractUrls(CharSequence r4) {
        ArrayList<CharSequence> r0 = null;
        if (r4 != null) goto L5;
    L19:
        return r0;
    L5:
        if (r4.length() == 0) goto L19;
        Matcher r1 = AndroidUtilities.WEB_URL.matcher(r4);     // Catch: Exception -> L20
    L8:
        if (r1.find() == false) goto L19;
        if (r1.start() <= 0) goto L15;
        if (r4.charAt(r1.start() - 1) == '@') goto L8;
    L15:
        if (r0 != null) goto L17;
        r0 = new ArrayList();     // Catch: Exception -> L20
    L17:
        r0.add(r4.subSequence(r1.start(), r1.end()));     // Catch: Exception -> L20
        goto L8
    }

    private /* synthetic */ void lambda$requestLinkPreview$1(int r4, TLObject r5, String r6) {
        if (r4 != this.linkRequestSerial) goto L40;
        this.linkRequestId = 0;
        if ((r5 instanceof TL_account.webPagePreview) == false) goto L10;
        TL_account.webPagePreview r52 = (TL_account.webPagePreview) r5;
        MessagesController.getInstance(this.currentAccount).putUsers(r52.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r52.chats, false);
        TLRPC.MessageMedia r53 = r52.media;
        if ((r53 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L10;
        TLRPC.WebPage r54 = ((TLRPC.TL_messageMediaWebPage) r53).webpage;
    L12:
        if ((r54 instanceof TLRPC.TL_webPage) == false) goto L24;
        if (this.linkPreviewCache.size() <= 5) goto L21;
        Iterator<String> r42 = this.linkPreviewCache.keySet().iterator();
    L17:
        if (r42.hasNext() == false) goto L21;
        if (this.linkPreviewCache.size() <= 5) goto L21;
        r42.next();
        r42.remove();
    L21:
        this.linkPreviewCache.put(r6, r54);
        this.loadedWebPage = r54;
        bindLinkLoaded(current(), r54, r6);
        return;
    L24:
        if ((r54 instanceof TLRPC.TL_webPagePending) == false) goto L28;
        this.loadedWebPage = r54;
        return;
    L28:
        if ((r54 instanceof TLRPC.TL_webPageEmpty) == false) goto L37;
        this.loadedWebPage = null;
        int r55 = this.currentMode;
        if (r55 == 0) goto L38;
        this.currentMode = 0;
        OnModeChangeListener r3 = this.modeChangeListener;
        if (r3 == null) goto L39;
        r3.onModeChanged(r55, 0);
        return;
    L39:
        return;
    L38:
        return;
    L37:
        return;
    L10:
        r54 = null;
        goto L12
    }

    private /* synthetic */ void lambda$requestLinkPreview$2(int r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new ShareTopView$$ExternalSyntheticLambda3(this, r1, r3, r2));
    }

    private /* synthetic */ void lambda$startHintRotation$3() {
        int r3 = 0;
        if (this.currentMode == 1) goto L9;
        Layout[] r0 = this.layouts;
        int r6 = r0.length;
        int r7 = 0;
    L5:
        if (r7 >= r6) goto L7;
        Layout r8 = r0[r7];
        r8.obj.setAlpha(1.0f);
        r8.obj.setScaleX(1.0f);
        r8.obj.setScaleY(1.0f);
        r8.objHint.setAlpha(0.0f);
        r7 = r7 + 1;
        goto L5
    L7:
        this.showingHint = false;
        AndroidUtilities.runOnUIThread(this.hintRunnable, 4000);
        return;
    L9:
        this.showingHint = !this.showingHint;
        Layout[] r02 = this.layouts;
        int r62 = r02.length;
    L10:
        if (r3 >= r62) goto L16;
        Layout r72 = r02[r3];
        r72.obj.setPivotX(0.0f);
        r72.objHint.setPivotX(0.0f);
        boolean r82 = this.showingHint;
        SimpleTextView r9 = r72.obj;
        if (r82 == false) goto L14;
        r9.animate().alpha(0.0f).scaleX(0.98f).scaleY(0.98f).setDuration(150).start();
        r72.objHint.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(150).start();
    L15:
        r3 = r3 + 1;
        goto L10
    L14:
        r9.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(150).start();
        r72.objHint.animate().alpha(0.0f).scaleX(0.98f).scaleY(0.98f).setDuration(150).start();
        goto L15
    L16:
        AndroidUtilities.runOnUIThread(this.hintRunnable, 4000);
    }

    private void prepareForLinkSearch(int r3) {
        rebindObserver(r3);
        this.selfId = AccountInstance.getInstance(r3).getUserConfig().getClientUserId();
        this.mediaEntries = null;
        this.linkSearchEnabled = true;
        this.previewEnabled = true;
        this.dismissedMessage = null;
        this.loadedWebPage = null;
        cancelLinkRequest();
        this.foundUrls.clear();
    }

    private void rebindObserver(int r3) {
        if (this.currentAccount != r3) goto L9;
        this.currentAccount = r3;
        if (isAttachedToWindow() == false) goto L15;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.didReceivedWebpagesInUpdates);
        return;
    L15:
        return;
    L9:
        if (isAttachedToWindow() == false) goto L11;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.didReceivedWebpagesInUpdates);
    L11:
        this.currentAccount = r3;
        if (isAttachedToWindow() == false) goto L16;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.didReceivedWebpagesInUpdates);
        return;
    }

    private void requestLinkPreview(String r5) {
        cancelLinkRequest();
        if (r5 != null) goto L5;
        return;
    L5:
        if (r5.isEmpty() == true) goto L10;
        TL_account.getWebPagePreview r0 = new TL_account.getWebPagePreview();
        r0.message = r5;
        int r1 = this.linkRequestSerial + 1;
        this.linkRequestSerial = r1;
        this.linkRequestId = AccountInstance.getInstance(this.currentAccount).getConnectionsManager().sendRequest(r0, new ShareTopView$$ExternalSyntheticLambda0(this, r1, r5));
        return;
    }

    private boolean sameUrls(ArrayList<CharSequence> r5) {
        if (r5 != null) goto L6;
        return this.foundUrls.isEmpty();
    L6:
        if (r5.size() == this.foundUrls.size()) goto L8;
        return false;
    L8:
        int r0 = 0;
    L10:
        if (r0 >= r5.size()) goto L15;
        if (TextUtils.equals(r5.get(r0), this.foundUrls.get(r0)) == false) goto L13;
        r0 = r0 + 1;
        goto L10
    L13:
        return false;
    L15:
        return true;
    }

    private void switchLayouts(boolean r9) {
        Layout[] r0 = this.layouts;
        Layout r2 = r0[0];
        Layout r4 = r0[1];
        r0[0] = r4;
        r0[1] = r2;
        if (r9 == false) goto L6;
        r4.active = true;
        r4.setVisibility(0);
        this.layouts[0].setScaleX(0.8f);
        this.layouts[0].setScaleY(0.8f);
        this.layouts[0].setAlpha(0.0f);
        this.layouts[0].setTranslationY(AndroidUtilities.dp(20.0f));
        ViewPropertyAnimator r92 = this.layouts[0].animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).translationY(0.0f);
        CubicBezierInterpolator r5 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r92.setInterpolator(r5).setDuration(320).start();
        Layout r8 = this.layouts[1];
        r8.active = false;
        r8.animate().scaleX(0.8f).scaleY(0.8f).alpha(0.0f).translationY(-AndroidUtilities.dp(20.0f)).setInterpolator(r5).setDuration(320).withEndAction(new ShareTopView$$ExternalSyntheticLambda1(r8)).start();
        return;
    L6:
        r2.setVisibility(8);
        Layout[] r93 = this.layouts;
        r93[1].active = false;
        r93[0].setVisibility(0);
        this.layouts[0].setScaleX(1.0f);
        this.layouts[0].setScaleY(1.0f);
        this.layouts[0].setAlpha(1.0f);
        this.layouts[0].setTranslationY(0.0f);
        this.layouts[0].active = true;
    }

    public Layout current() {
        return this.layouts[0];
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r6, int r7, Object... r8) {
        if (r6 == NotificationCenter.didReceivedWebpagesInUpdates) goto L6;
        return;
    L6:
        if (this.loadedWebPage != null) goto L8;
        return;
    L8:
        if (r7 != this.currentAccount) goto L47;
        LongSparseArray r72 = (LongSparseArray) r8[0];
        int r82 = 0;
    L12:
        if (r82 >= r72.size()) goto L48;
        TLRPC.WebPage r0 = (TLRPC.WebPage) r72.valueAt(r82);
        if (r0 == null) goto L37;
        if (r0.id != this.loadedWebPage.id) goto L37;
        if ((r0 instanceof TLRPC.TL_webPageEmpty) == false) goto L27;
        int r73 = this.currentMode;
        this.loadedWebPage = null;
        cancelLinkRequest();
        if (this.currentMode == 0) goto L43;
        this.currentMode = 0;
        OnModeChangeListener r5 = this.modeChangeListener;
        if (r5 == null) goto L44;
        r5.onModeChanged(r73, 0);
        return;
    L44:
        return;
    L43:
        return;
    L27:
        if ((r0 instanceof TLRPC.TL_webPage) == false) goto L45;
        this.loadedWebPage = r0;
        if (this.foundUrls.isEmpty() == false) goto L31;
        String r62 = _UrlKt.FRAGMENT_ENCODE_SET;
    L33:
        if (this.linkPreviewCache.containsKey(r62) == true) goto L35;
        this.linkPreviewCache.put(r62, r0);
    L35:
        bindLinkLoaded(current(), r0, r62);
        return;
    L31:
        r62 = TextUtils.join(" ", this.foundUrls).toString();
        goto L33
    L45:
        return;
    L37:
        r82 = r82 + 1;
        goto L12
    L48:
        return;
    }

    public void dismissWebPagePreview() {
        if (this.foundUrls.isEmpty() == true) goto L13;
        this.dismissedMessage = TextUtils.join(" ", this.foundUrls).toString();
        this.previewEnabled = false;
        cancelLinkRequest();
        this.loadedWebPage = null;
        int r1 = this.currentMode;
        if (r1 == 0) goto L11;
        this.currentMode = 0;
        OnModeChangeListener r2 = this.modeChangeListener;
        if (r2 == null) goto L12;
        r2.onModeChanged(r1, 0);
        return;
    L12:
        return;
    L11:
        return;
    }

    public TLRPC.WebPage getLoadedWebPage() {
        return this.loadedWebPage;
    }

    public int getMode() {
        return this.currentMode;
    }

    public BackupImageView getThumbView(int r4) {
        if (this.currentMode == 1) goto L5;
        return null;
    L5:
        BackupImageView[] r3 = current().images;
        if (r3 == null) goto L15;
        if (r4 < 0) goto L15;
        if (r4 >= r3.length) goto L15;
        if (r3[r4].getVisibility() != 0) goto L15;
        return r3[r4];
    L15:
        return null;
    }

    public boolean isPreviewEnabled() {
        return this.previewEnabled;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.didReceivedWebpagesInUpdates);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.didReceivedWebpagesInUpdates);
        cancelLinkRequest();
    }

    public void onTextChanged(CharSequence r2, boolean r3) {
        if (this.linkSearchEnabled == true) goto L5;
        return;
    L5:
        doLinkSearch(r2, r3);
    }

    public void setLayoutClickListener(View.OnClickListener r4) {
        this.layoutClickListener = r4;
        Layout[] r3 = this.layouts;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].container.setOnClickListener(r4);
        r1 = r1 + 1;
        goto L3
    }

    public void setOnModeChangeListener(OnModeChangeListener r1) {
        this.modeChangeListener = r1;
    }

    public void setRecipients(int r3, ArrayList<Long> r4) {
        rebindObserver(r3);
        this.selfId = AccountInstance.getInstance(r3).getUserConfig().getClientUserId();
        this.recipients.clear();
        if (r4 == null) goto L5;
        this.recipients.addAll(r4);
    L5:
        Layout r32 = current();
        if (this.currentMode != 1) goto L9;
        r32.obj.setText(buildRecipientText(r32));
        return;
    }

    public void setSharedLink(int r1, String r2) {
        prepareForLinkSearch(r1);
        if (r2 != null) goto L5;
        return;
    L5:
        if (r2.isEmpty() == true) goto L9;
        doLinkSearch(r2, true);
        return;
    }

    public void setSharedMedia(int r3, ArrayList<MediaController.PhotoEntry> r4) {
        rebindObserver(r3);
        this.selfId = AccountInstance.getInstance(r3).getUserConfig().getClientUserId();
        this.mediaEntries = r4;
        boolean r32 = false;
        this.linkSearchEnabled = false;
        this.loadedWebPage = null;
        cancelLinkRequest();
        this.foundUrls.clear();
        int r42 = this.currentMode;
        if (r42 == 1) goto L6;
        if (r42 == 0) goto L6;
        r32 = true;
    L6:
        this.currentMode = 1;
        if (r32 == false) goto L9;
        switchLayouts(true);
    L9:
        bindMedia(current());
        applyHintText(current());
        int r33 = this.currentMode;
        if (r42 == r33) goto L15;
        OnModeChangeListener r2 = this.modeChangeListener;
        if (r2 == null) goto L16;
        r2.onModeChanged(r42, r33);
        return;
    L16:
        return;
    }

    public void setSharedText(int r1, CharSequence r2) {
        prepareForLinkSearch(r1);
        if (r2 != null) goto L5;
        return;
    L5:
        if (r2.length() <= 0) goto L9;
        doLinkSearch(r2, true);
        return;
    }

    public void startHintRotation(CharSequence r3) {
        stopHintRotation();
        this.pendingHintText = r3;
        applyHintText(current());
        ShareTopView$$ExternalSyntheticLambda2 r32 = new ShareTopView$$ExternalSyntheticLambda2(this);
        this.hintRunnable = r32;
        AndroidUtilities.runOnUIThread(r32, 1000);
    }

    public void stopHintRotation() {
        Runnable r0 = this.hintRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.hintRunnable = null;
    L5:
        int r02 = 0;
        this.showingHint = false;
        Layout[] r5 = this.layouts;
        int r1 = r5.length;
    L6:
        if (r02 >= r1) goto L8;
        Layout r2 = r5[r02];
        r2.obj.setAlpha(1.0f);
        r2.obj.setScaleX(1.0f);
        r2.obj.setScaleY(1.0f);
        r2.objHint.setAlpha(0.0f);
        r02 = r02 + 1;
        goto L6
    }
}
