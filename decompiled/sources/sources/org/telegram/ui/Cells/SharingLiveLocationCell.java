package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.text.Layout;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.IMapsProvider;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LocationController;
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
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LoadingSpan;
import org.telegram.ui.LocationActivity;

/* loaded from: classes6.dex */
public class SharingLiveLocationCell extends FrameLayout {
    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImageView;
    private int currentAccount;
    private LocationController.SharingLocationInfo currentInfo;
    private TextView distanceTextView;
    private int distanceTextViewHeight;
    private boolean distanceTextViewSingle;
    private Drawable foreverDrawable;
    private int foreverDrawableColor;
    private Runnable invalidateRunnable;
    private double lastLat;
    private double lastLong;
    private CharSequence lastName;
    private LocationActivity.LiveLocation liveLocation;
    private boolean loading;
    private SpannableString loadingString;
    private Location location;
    private SimpleTextView nameTextView;
    private int padding;
    private RectF rect;
    private final Theme.ResourcesProvider resourcesProvider;

    /* renamed from: org.telegram.ui.Cells.SharingLiveLocationCell$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ SharingLiveLocationCell this$0;

        public AnonymousClass1(SharingLiveLocationCell r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.invalidate(((int) SharingLiveLocationCell.m7034$$Nest$fgetrect(r0).left) - 5, ((int) SharingLiveLocationCell.m7034$$Nest$fgetrect(this.this$0).top) - 5, ((int) SharingLiveLocationCell.m7034$$Nest$fgetrect(this.this$0).right) + 5, ((int) SharingLiveLocationCell.m7034$$Nest$fgetrect(this.this$0).bottom) + 5);
            AndroidUtilities.runOnUIThread(SharingLiveLocationCell.m7033$$Nest$fgetinvalidateRunnable(this.this$0), 1000);
        }
    }

    public static /* synthetic */ void $r8$lambda$EZQ2seE6TgJSDV_OFxguFWqhPgk(SharingLiveLocationCell r0, double r1, double r3) {
        r0.lambda$getName$1(r1, r3);
    }

    /* renamed from: $r8$lambda$MR-99HZHUULOO5LtbTjgEi_Gq7Y, reason: not valid java name */
    public static /* synthetic */ void m7032$r8$lambda$MR99HZHUULOO5LtbTjgEi_Gq7Y(SharingLiveLocationCell r0, double r1, double r3) {
        r0.lambda$getName$0(r1, r3);
    }

    /* renamed from: -$$Nest$fgetinvalidateRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m7033$$Nest$fgetinvalidateRunnable(SharingLiveLocationCell r0) {
        return r0.invalidateRunnable;
    }

    /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m7034$$Nest$fgetrect(SharingLiveLocationCell r0) {
        return r0.rect;
    }

    public SharingLiveLocationCell(Context r19, boolean r20, int r21, Theme.ResourcesProvider r22) {
        super(r19);
        this.rect = new RectF();
        this.location = new Location("network");
        this.currentAccount = UserConfig.selectedAccount;
        this.invalidateRunnable = new AnonymousClass1(this);
        this.lastName = _UrlKt.FRAGMENT_ENCODE_SET;
        this.resourcesProvider = r22;
        this.padding = r21;
        BackupImageView r3 = new BackupImageView(r19);
        this.avatarImageView = r3;
        r3.setRoundRadius(ExteraConfig.getAvatarCorners(42.0f));
        this.avatarDrawable = new AvatarDrawable();
        SimpleTextView r32 = new SimpleTextView(r19);
        this.nameTextView = r32;
        NotificationCenter.listenEmojiLoading(r32);
        this.nameTextView.setTextSize(16);
        this.nameTextView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        this.nameTextView.setTypeface(AndroidUtilities.bold());
        SimpleTextView r33 = this.nameTextView;
        int r5 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r4 = 5;
    L6:
        r33.setGravity(r4);
        this.nameTextView.setScrollNonFitText(true);
        BackupImageView r34 = this.avatarImageView;
        if (r20 == false) goto L45;
        boolean r9 = LocaleController.isRTL;
        if (r9 == false) goto L11;
        int r10 = 5;
    L12:
        int r13 = r10 | 48;
        if (r9 == false) goto L15;
        float r14 = 0.0f;
    L16:
        if (r9 == false) goto L18;
        float r16 = 15.0f;
    L19:
        addView(r34, LayoutHelper.createFrame(42, 42.0f, r13, r14, 12.0f, r16, 0.0f));
        SimpleTextView r35 = this.nameTextView;
        boolean r7 = LocaleController.isRTL;
        if (r7 == false) goto L22;
        int r8 = 5;
    L23:
        int r11 = r8 | 48;
        float r82 = 73.0f;
        if (r7 == false) goto L26;
        float r12 = r21;
    L27:
        if (r7 == false) goto L29;
        float r142 = 73.0f;
    L30:
        addView(r35, LayoutHelper.createFrame(-1, 20.0f, r11, r12, 12.0f, r142, 0.0f));
        TextView r36 = new TextView(r19);
        this.distanceTextView = r36;
        r36.setSingleLine();
        this.distanceTextViewSingle = true;
        this.distanceTextView.setTextSize(1, 14.0f);
        this.distanceTextView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText3));
        TextView r1 = this.distanceTextView;
        if (LocaleController.isRTL == false) goto L33;
        int r37 = 5;
    L34:
        r1.setGravity(r37);
        TextView r15 = this.distanceTextView;
        boolean r38 = LocaleController.isRTL;
        if (r38 == false) goto L37;
        r5 = 5;
    L37:
        int r112 = r5 | 48;
        if (r38 == false) goto L40;
        float r122 = r21;
    L41:
        if (r38 == true) goto L44;
        r82 = r21;
    L44:
        addView(r15, LayoutHelper.createFrame(-1, -2.0f, r112, r122, 33.0f, r82, 0.0f));
    L67:
        setWillNotDraw(false);
        return;
    L40:
        r122 = 73.0f;
        goto L41
    L33:
        r37 = 3;
        goto L34
    L29:
        r142 = 16.0f;
        goto L30
    L26:
        r12 = 73.0f;
        goto L27
    L22:
        r8 = 3;
        goto L23
    L18:
        r16 = 0.0f;
        goto L19
    L15:
        r14 = 15.0f;
        goto L16
    L11:
        r10 = 3;
        goto L12
    L45:
        boolean r17 = LocaleController.isRTL;
        if (r17 == false) goto L48;
        int r42 = 5;
    L49:
        int r113 = r42 | 48;
        if (r17 == false) goto L52;
        float r123 = 0.0f;
    L53:
        if (r17 == false) goto L55;
        float r143 = 15.0f;
    L56:
        addView(r34, LayoutHelper.createFrame(42, 42.0f, r113, r123, 6.0f, r143, 0.0f));
        SimpleTextView r18 = this.nameTextView;
        boolean r39 = LocaleController.isRTL;
        if (r39 == false) goto L59;
        r5 = 5;
    L59:
        int r83 = r5 | 48;
        float r43 = 74.0f;
        if (r39 == false) goto L62;
        float r92 = r21;
    L63:
        if (r39 == true) goto L66;
        r43 = r21;
    L66:
        addView(r18, LayoutHelper.createFrame(-2, -2.0f, r83, r92, 17.0f, r43, 0.0f));
        goto L67
    L62:
        r92 = 74.0f;
        goto L63
    L55:
        r143 = 0.0f;
        goto L56
    L52:
        r123 = 15.0f;
        goto L53
    L48:
        r42 = 3;
        goto L49
    L5:
        r4 = 3;
        goto L6
    }

    private CharSequence getName(double r8, double r10) {
        if (this.loading == false) goto L7;
        return this.lastName;
    L7:
        if (Math.abs(this.lastLat - r8) <= 1.0E-6d) goto L9;
    L14:
        this.loading = true;
        SharingLiveLocationCell r2 = this;
        Utilities.globalQueue.postRunnable(new SharingLiveLocationCell$$ExternalSyntheticLambda0(r2, r8, r10));
    L16:
        return r2.lastName;
    L9:
        if (Math.abs(this.lastLong - r10) > 1.0E-6d) goto L14;
        if (TextUtils.isEmpty(this.lastName) == true) goto L14;
        r2 = this;
        goto L16
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$getName$0(double r1, double r3) {
        this.lastLat = r1;
        this.lastLong = r3;
        this.loading = false;
        CharSequence r12 = Emoji.replaceEmoji(this.lastName, this.nameTextView.getPaint().getFontMetricsInt(), false);
        this.lastName = r12;
        this.nameTextView.setText(r12);
    }

    private /* synthetic */ void lambda$getName$1(double r8, double r10) {
        double r2 = r8;
        double r4 = r10;
        List<Address> r82 = new Geocoder(ApplicationLoader.applicationContext, LocaleController.getInstance().getCurrentLocale()).getFromLocation(r2, r4, 1);     // Catch: Exception -> L30
        if (r82.isEmpty() == false) goto L11;
        String r83 = LocationController.detectOcean(r4, r2);     // Catch: Exception -> L30
        this.lastName = r83;     // Catch: Exception -> L30
        if (r83 != null) goto L10;
        this.lastName = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Exception -> L30
        goto L28
    L10:
        this.lastName = "🌊 " + this.lastName;     // Catch: Exception -> L30
        goto L28
    L11:
        Address r84 = r82.get(0);     // Catch: Exception -> L30
        StringBuilder r9 = new StringBuilder();     // Catch: Exception -> L30
        HashSet r102 = new HashSet();     // Catch: Exception -> L30
        r102.add(r84.getSubAdminArea());     // Catch: Exception -> L30
        r102.add(r84.getAdminArea());     // Catch: Exception -> L30
        r102.add(r84.getLocality());     // Catch: Exception -> L30
        r102.add(r84.getCountryName());     // Catch: Exception -> L30
        Iterator r103 = r102.iterator();     // Catch: Exception -> L30
    L13:
        if (r103.hasNext() == false) goto L21;
        String r11 = (String) r103.next();     // Catch: Exception -> L30
        if (TextUtils.isEmpty(r11) == true) goto L13;
        if (r9.length() <= 0) goto L20;
        r9.append(", ");     // Catch: Exception -> L30
    L20:
        r9.append(r11);     // Catch: Exception -> L30
        goto L13
    L21:
        this.lastName = r9.toString();     // Catch: Exception -> L30
        String r85 = LocationController.countryCodeToEmoji(r84.getCountryCode());     // Catch: Exception -> L30
        if (r85 == null) goto L28;
        if (Emoji.getEmojiDrawable(r85) == null) goto L28;
        this.lastName = r85 + " " + this.lastName;     // Catch: Exception -> L30
    L28:
        AndroidUtilities.runOnUIThread(new SharingLiveLocationCell$$ExternalSyntheticLambda1(this, r2, r4));
        return;
    L27:
        r2 = r8;
        r4 = r10;
        goto L28
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        AndroidUtilities.runOnUIThread(this.invalidateRunnable);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AndroidUtilities.cancelRunOnUIThread(this.invalidateRunnable);
    }

    @Override // android.view.View
    public void onDraw(Canvas r14) {
        LocationController.SharingLocationInfo r1 = this.currentInfo;
        if (r1 == null) goto L5;
    L7:
        if (r1 == null) goto L10;
        int r2 = r1.stopTime;
        int r12 = r1.period;
    L9:
        int r7 = r2;
        if (r12 != Integer.MAX_VALUE) goto L15;
        boolean r22 = true;
    L14:
        boolean r8 = r22;
        int r9 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        if (r7 >= r9) goto L20;
        if (r8 == true) goto L20;
        return;
    L20:
        if (r8 == false) goto L23;
        float r13 = 1.0f;
    L22:
        float r10 = r13;
        boolean r15 = LocaleController.isRTL;
        RectF r23 = this.rect;
        float r3 = 42.0f;
        float r6 = 12.0f;
        if (r15 == false) goto L33;
        float r16 = AndroidUtilities.dp(13.0f);
        if (this.distanceTextView == null) goto L29;
        r6 = 18.0f;
    L29:
        float r62 = AndroidUtilities.dp(r6);
        float r5 = AndroidUtilities.dp(43.0f);
        if (this.distanceTextView == null) goto L32;
        r3 = 48.0f;
    L32:
        r23.set(r16, r62, r5, AndroidUtilities.dp(r3));
    L41:
        if (this.distanceTextView != null) goto L43;
        int r17 = getThemedColor(Theme.key_dialog_liveLocationProgress);
    L44:
        Theme.chat_radialProgress2Paint.setColor(r17);
        Theme.chat_livePaint.setColor(r17);
        int r11 = Theme.chat_radialProgress2Paint.getAlpha();
        Theme.chat_radialProgress2Paint.setAlpha((int) (r11 * 0.2f));
        r14.drawArc(this.rect, -90.0f, 360.0f, false, Theme.chat_radialProgress2Paint);
        Theme.chat_radialProgress2Paint.setAlpha(r11);
        r14.drawArc(this.rect, -90.0f, r10 * (-360.0f), false, Theme.chat_radialProgress2Paint);
        Theme.chat_radialProgress2Paint.setAlpha(r11);
        if (r8 == true) goto L47;
        String r24 = LocaleController.formatLocationLeftTime(r7 - r9);
        float r4 = this.rect.centerX() - (Theme.chat_livePaint.measureText(r24) / 2.0f);
        if (this.distanceTextView == null) goto L57;
        float r0 = 37.0f;
    L58:
        r14.drawText(r24, r4, AndroidUtilities.dp(r0), Theme.chat_livePaint);
        return;
    L57:
        r0 = 31.0f;
        goto L58
    L47:
        if (this.foreverDrawable != null) goto L50;
        this.foreverDrawable = getContext().getResources().getDrawable(R.drawable.filled_location_forever).mutate();
    L50:
        if (Theme.chat_livePaint.getColor() == this.foreverDrawableColor) goto L52;
        Drawable r25 = this.foreverDrawable;
        int r42 = Theme.chat_livePaint.getColor();
        this.foreverDrawableColor = r42;
        r25.setColorFilter(new PorterDuffColorFilter(r42, PorterDuff.Mode.SRC_IN));
    L52:
        this.foreverDrawable.setBounds(((int) this.rect.centerX()) - (this.foreverDrawable.getIntrinsicWidth() / 2), ((int) this.rect.centerY()) - (this.foreverDrawable.getIntrinsicHeight() / 2), ((int) this.rect.centerX()) + (this.foreverDrawable.getIntrinsicWidth() / 2), ((int) this.rect.centerY()) + (this.foreverDrawable.getIntrinsicHeight() / 2));
        this.foreverDrawable.draw(r14);
        return;
    L43:
        r17 = getThemedColor(Theme.key_location_liveLocationProgress);
        goto L44
    L33:
        float r18 = getMeasuredWidth() - AndroidUtilities.dp(43.0f);
        if (this.distanceTextView == null) goto L36;
        r6 = 18.0f;
    L36:
        float r52 = AndroidUtilities.dp(r6);
        float r63 = getMeasuredWidth() - AndroidUtilities.dp(13.0f);
        if (this.distanceTextView == null) goto L39;
        r3 = 48.0f;
    L39:
        r23.set(r18, r52, r63, AndroidUtilities.dp(r3));
        goto L41
    L23:
        r13 = Math.abs(r7 - r9) / r12;
        goto L22
    L15:
        r22 = false;
        goto L14
    L10:
        TLRPC.Message r19 = this.liveLocation.object;
        int r26 = r19.date;
        r12 = r19.media.period;
        r2 = r26 + r12;
        goto L9
    L5:
        if (this.liveLocation != null) goto L7;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        int r42 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r4), TLObject.FLAG_30);
        if (this.distanceTextView == null) goto L5;
        float r0 = 66.0f;
    L6:
        int r02 = AndroidUtilities.dp(r0);
        if (this.distanceTextView != null) goto L9;
    L11:
        int r1 = 0;
    L12:
        super.onMeasure(r42, View.MeasureSpec.makeMeasureSpec(r02 + r1, TLObject.FLAG_30));
        return;
    L9:
        if (this.distanceTextViewSingle == true) goto L11;
        r1 = (-AndroidUtilities.dp(20.0f)) + this.distanceTextViewHeight;
        goto L12
    L5:
        r0 = 54.0f;
        goto L6
    }

    public void setDialog(long r5, TLRPC.TL_channelLocation r7) {
        this.currentAccount = UserConfig.selectedAccount;
        String r0 = r7.address;
        this.avatarDrawable = null;
        boolean r1 = DialogObject.isUserDialog(r5);
        int r2 = this.currentAccount;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 == false) goto L7;
        TLRPC.User r52 = MessagesController.getInstance(r2).getUser(Long.valueOf(r5));
        if (r52 == null) goto L10;
        this.avatarDrawable = new AvatarDrawable(r52);
        r3 = UserObject.getUserName(r52);
        this.avatarImageView.setForUserOrChat(r52, this.avatarDrawable);
    L10:
        this.nameTextView.setText(r3);
        this.location.setLatitude(r7.geo_point.lat);
        this.location.setLongitude(r7.geo_point._long);
        TextView r53 = this.distanceTextView;
        this.distanceTextViewSingle = true;
        r53.setSingleLine(true);
        this.distanceTextView.setText(r0);
        return;
    L7:
        TLRPC.Chat r54 = MessagesController.getInstance(r2).getChat(Long.valueOf(-r5));
        if (r54 == null) goto L10;
        AvatarDrawable r6 = new AvatarDrawable(r54);
        this.avatarDrawable = r6;
        r3 = r54.title;
        this.avatarImageView.setForUserOrChat(r54, r6);
        goto L10
    }

    public void setDialog(LocationController.SharingLocationInfo r4) {
        this.currentInfo = r4;
        this.currentAccount = r4.account;
        this.avatarImageView.getImageReceiver().setCurrentAccount(this.currentAccount);
        boolean r0 = DialogObject.isUserDialog(r4.did);
        int r1 = this.currentAccount;
        if (r0 == false) goto L8;
        TLRPC.User r42 = MessagesController.getInstance(r1).getUser(Long.valueOf(r4.did));
        if (r42 == null) goto L12;
        this.avatarDrawable.setInfo(this.currentAccount, r42);
        this.nameTextView.setText(ContactsController.formatName(r42.first_name, r42.last_name));
        this.avatarImageView.setForUserOrChat(r42, this.avatarDrawable);
        return;
    L12:
        return;
    L8:
        TLRPC.Chat r43 = MessagesController.getInstance(r1).getChat(Long.valueOf(-r4.did));
        if (r43 == null) goto L13;
        this.avatarDrawable.setInfo(this.currentAccount, r43);
        this.nameTextView.setText(r43.title);
        this.avatarImageView.setForUserOrChat(r43, this.avatarDrawable);
        return;
    }

    public void setDialog(MessageObject r12, Location r13, boolean r14) {
        if (r12 == null) goto L10;
        TLRPC.Message r3 = r12.messageOwner;
        if (r3 == null) goto L10;
        if (r3.local_id != (-1)) goto L10;
        Drawable r132 = getResources().getDrawable(R.drawable.pin);
        r132.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_location_sendLocationIcon), PorterDuff.Mode.MULTIPLY));
        int r142 = getThemedColor(Theme.key_location_placeLocationBackground);
        CombinedDrawable r32 = new CombinedDrawable(Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(42.0f), r142, r142), r132);
        r32.setCustomSize(AndroidUtilities.dp(42.0f), AndroidUtilities.dp(42.0f));
        r32.setIconSize(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
        this.avatarImageView.setImageDrawable(r32);
        this.nameTextView.setText(Emoji.replaceEmoji(MessagesController.getInstance(this.currentAccount).getPeerName(DialogObject.getPeerDialogId(r12.messageOwner.peer_id)), this.nameTextView.getPaint().getFontMetricsInt(), false));
        TextView r133 = this.distanceTextView;
        this.distanceTextViewSingle = false;
        r133.setSingleLine(false);
        String r1 = r12.messageOwner.media.address;
        this.distanceTextViewHeight = new StaticLayout(r1, this.distanceTextView.getPaint(), AndroidUtilities.displaySize.x - AndroidUtilities.dp(this.padding + 73), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false).getHeight();
        this.distanceTextView.setText(r1);
        requestLayout();
        return;
    L10:
        TextView r33 = this.distanceTextView;
        this.distanceTextViewSingle = true;
        r33.setSingleLine(true);
        long r34 = r12.getFromChatId();
        if (r12.isForwarded() == false) goto L13;
        r34 = MessageObject.getPeerId(r12.messageOwner.fwd_from.from_id);
    L13:
        this.currentAccount = r12.currentAccount;
        if (TextUtils.isEmpty(r12.messageOwner.media.address) == true) goto L16;
        String r5 = r12.messageOwner.media.address;
    L17:
        boolean r7 = TextUtils.isEmpty(r12.messageOwner.media.title);
        if (r7 == false) goto L29;
        this.avatarDrawable = null;
        int r9 = this.currentAccount;
        if (r34 <= 0) goto L25;
        TLRPC.User r35 = MessagesController.getInstance(r9).getUser(Long.valueOf(r34));
        if (r35 == null) goto L24;
        this.avatarDrawable = new AvatarDrawable(r35);
        CharSequence r4 = UserObject.getUserName(r35);
        this.avatarImageView.setForUserOrChat(r35, this.avatarDrawable);
    L31:
        if (TextUtils.isEmpty(r4) == true) goto L33;
    L36:
        if (r7 == false) goto L38;
    L41:
        this.nameTextView.setText(r4);
        this.location.setLatitude(r12.messageOwner.media.geo.lat);
        this.location.setLongitude(r12.messageOwner.media.geo._long);
        if (r13 == null) goto L49;
        float r122 = this.location.distanceTo(r13);
        TextView r11 = this.distanceTextView;
        if (r5 == null) goto L47;
        r11.setText(String.format("%s - %s", new Object[]{r5, LocaleController.formatDistance(r122, 0)}));
        return;
    L47:
        r11.setText(LocaleController.formatDistance(r122, 0));
        return;
    L49:
        if (r5 == null) goto L52;
        this.distanceTextView.setText(r5);
        return;
    L52:
        TextView r112 = this.distanceTextView;
        if (r14 == true) goto L56;
        r112.setText(LocaleController.getString(R.string.Loading));
        return;
    L56:
        r112.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L38:
        if (TextUtils.isEmpty(r12.messageOwner.media.title) == true) goto L40;
        r4 = r12.messageOwner.media.title;
    L40:
        Drawable r36 = getResources().getDrawable(R.drawable.pin);
        r36.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_location_sendLocationIcon), PorterDuff.Mode.MULTIPLY));
        int r6 = getThemedColor(Theme.key_location_placeLocationBackground);
        CombinedDrawable r72 = new CombinedDrawable(Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(42.0f), r6, r6), r36);
        r72.setCustomSize(AndroidUtilities.dp(42.0f), AndroidUtilities.dp(42.0f));
        r72.setIconSize(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
        this.avatarImageView.setImageDrawable(r72);
        goto L41
    L33:
        if (this.loadingString != null) goto L35;
        SpannableString r37 = new SpannableString("dkaraush has been here");
        this.loadingString = r37;
        r37.setSpan(new LoadingSpan(this.nameTextView, AndroidUtilities.dp(100.0f), 0, this.resourcesProvider), 0, this.loadingString.length(), 33);
    L35:
        r4 = this.loadingString;
        goto L36
    L24:
        TLRPC.GeoPoint r38 = r12.messageOwner.media.geo;
        r4 = getName(r38.lat, r38._long);
        r7 = false;
        goto L31
    L25:
        TLRPC.Chat r39 = MessagesController.getInstance(r9).getChat(Long.valueOf(-r34));
        if (r39 == null) goto L28;
        AvatarDrawable r42 = new AvatarDrawable(r39);
        this.avatarDrawable = r42;
        String r62 = r39.title;
        this.avatarImageView.setForUserOrChat(r39, r42);
        r4 = r62;
        goto L31
    L28:
        TLRPC.GeoPoint r310 = r12.messageOwner.media.geo;
        r7 = false;
        r4 = getName(r310.lat, r310._long);
        goto L31
    L29:
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L31
    L16:
        r5 = null;
        goto L17
    }

    public void setDialog(LocationActivity.LiveLocation r5, Location r6) {
        this.liveLocation = r5;
        boolean r0 = DialogObject.isUserDialog(r5.id);
        int r1 = this.currentAccount;
        if (r0 == false) goto L7;
        TLRPC.User r02 = MessagesController.getInstance(r1).getUser(Long.valueOf(r5.id));
        if (r02 == null) goto L10;
        this.avatarDrawable.setInfo(this.currentAccount, r02);
        this.nameTextView.setText(ContactsController.formatName(r02.first_name, r02.last_name));
        this.avatarImageView.setForUserOrChat(r02, this.avatarDrawable);
    L10:
        IMapsProvider.LatLng r03 = r5.marker.getPosition();
        this.location.setLatitude(r03.latitude);
        this.location.setLongitude(r03.longitude);
        TLRPC.Message r52 = r5.object;
        int r04 = r52.edit_date;
        if (r04 == 0) goto L13;
        long r05 = r04;
    L14:
        String r53 = LocaleController.formatLocationUpdateDate(r05);
        TextView r06 = this.distanceTextView;
        if (r6 == null) goto L18;
        r06.setText(String.format("%s - %s", new Object[]{r53, LocaleController.formatDistance(this.location.distanceTo(r6), 0)}));
        return;
    L18:
        r06.setText(r53);
        return;
    L13:
        r05 = r52.date;
        goto L14
    L7:
        TLRPC.Chat r07 = MessagesController.getInstance(r1).getChat(Long.valueOf(-r5.id));
        if (r07 == null) goto L10;
        this.avatarDrawable.setInfo(this.currentAccount, r07);
        this.nameTextView.setText(r07.title);
        this.avatarImageView.setForUserOrChat(r07, this.avatarDrawable);
        goto L10
    }
}
