package org.telegram.ui.Adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LocationController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.LocationCell;
import org.telegram.ui.Cells.LocationDirectionCell;
import org.telegram.ui.Cells.LocationLoadingCell;
import org.telegram.ui.Cells.LocationPoweredCell;
import org.telegram.ui.Cells.SendLocationCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.SharingLiveLocationCell;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SharedMediaLayout;
import org.telegram.ui.LocationActivity;

/* loaded from: classes6.dex */
public class LocationActivityAdapter extends BaseLocationAdapter implements LocationController.LocationFetchCallback {
    private String addressName;
    public boolean animated;
    private boolean askingForMyLocation;
    private TLRPC.TL_channelLocation chatLocation;
    public TLRPC.TL_messageMediaVenue city;
    private int currentAccount;
    private ArrayList<LocationActivity.LiveLocation> currentLiveLocations;
    private MessageObject currentMessageObject;
    private Location customLocation;
    private long dialogId;
    private FrameLayout emptyCell;
    private boolean fetchingLocation;
    private boolean fromStories;
    private Location gpsLocation;
    public boolean isPollAttach;
    private int locationType;
    private Context mContext;
    private boolean myLocationDenied;
    private boolean needEmptyView;
    private int overScrollHeight;
    private String overrideAddressName;
    private Location previousFetchedLocation;
    private final Theme.ResourcesProvider resourcesProvider;
    private SendLocationCell sendLocationCell;
    private int shareLiveLocationPotistion;
    private SharedMediaLayout sharedMediaLayout;
    private boolean sharedMediaLayoutVisible;
    public TLRPC.TL_messageMediaVenue street;
    private Runnable updateRunnable;

    /* renamed from: $r8$lambda$9P_6-Sikqqz_XxzhzNExwQbzd7o, reason: not valid java name */
    public static /* synthetic */ void m5902$r8$lambda$9P_6Sikqqz_XxzhzNExwQbzd7o(LocationActivityAdapter r0, View r1) {
        r0.lambda$onCreateViewHolder$0(r1);
    }

    public LocationActivityAdapter(Context r1, int r2, long r3, boolean r5, Theme.ResourcesProvider r6, boolean r7, boolean r8, boolean r9) {
        super(r7, r9);
        this.currentAccount = UserConfig.selectedAccount;
        this.shareLiveLocationPotistion = -1;
        this.currentLiveLocations = new ArrayList();
        this.animated = true;
        this.myLocationDenied = false;
        this.askingForMyLocation = false;
        this.fromStories = r8;
        this.mContext = r1;
        this.locationType = r2;
        this.dialogId = r3;
        this.needEmptyView = r5;
        this.resourcesProvider = r6;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$onCreateViewHolder$0(View r1) {
        onDirectionClick();
    }

    private void updateCell() {
        SendLocationCell r0 = this.sendLocationCell;
        if (r0 == null) goto L64;
        int r1 = this.locationType;
        if (r1 == 8) goto L7;
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 == 4) goto L34;
        if (this.customLocation != null) goto L34;
        if (this.gpsLocation == null) goto L27;
        r0.setText(LocaleController.getString(R.string.SendLocation), LocaleController.formatString(R.string.AccurateTo, new Object[]{LocaleController.formatPluralString("Meters", (int) this.gpsLocation.getAccuracy(), new Object[0])}));
        this.sendLocationCell.setHasLocation(true);
        return;
    L27:
        String r12 = LocaleController.getString(R.string.SendLocation);
        if (this.myLocationDenied == true) goto L31;
        r2 = LocaleController.getString(R.string.Loading);
    L31:
        r0.setText(r12, r2);
        this.sendLocationCell.setHasLocation(!this.myLocationDenied);
        return;
    L34:
        if (TextUtils.isEmpty(this.overrideAddressName) == true) goto L37;
        r2 = this.overrideAddressName;
    L56:
        if (this.isPollAttach == false) goto L58;
        this.sendLocationCell.setText(LocaleController.getString(R.string.AttachSelectedLocation), r2);
    L62:
        this.sendLocationCell.setHasLocation(true);
        return;
    L58:
        int r02 = this.locationType;
        SendLocationCell r13 = this.sendLocationCell;
        if (r02 != 4) goto L61;
        r13.setText(LocaleController.getString(R.string.ChatSetThisLocation), r2);
        goto L62
    L61:
        r13.setText(LocaleController.getString(R.string.SendSelectedLocation), r2);
        goto L62
    L37:
        if (TextUtils.isEmpty(this.addressName) == true) goto L39;
        r2 = this.addressName;
        goto L56
    L39:
        Location r03 = this.customLocation;
        if (r03 != null) goto L44;
        if (this.gpsLocation != null) goto L44;
    L45:
        r2 = LocaleController.getString(R.string.Loading);
    L44:
        if (this.fetchingLocation == true) goto L45;
        if (r03 == null) goto L49;
        r2 = String.format(Locale.US, "(%f,%f)", new Object[]{Double.valueOf(r03.getLatitude()), Double.valueOf(this.customLocation.getLongitude())});
        goto L56
    L49:
        Location r04 = this.gpsLocation;
        if (r04 == null) goto L53;
        r2 = String.format(Locale.US, "(%f,%f)", new Object[]{Double.valueOf(r04.getLatitude()), Double.valueOf(this.gpsLocation.getLongitude())});
        goto L56
    L53:
        if (this.myLocationDenied == true) goto L56;
        r2 = LocaleController.getString(R.string.Loading);
        goto L56
    L7:
        if (TextUtils.isEmpty(this.overrideAddressName) == true) goto L10;
        String r05 = this.overrideAddressName;
    L16:
        this.sendLocationCell.setText(LocaleController.getString(R.string.SetThisLocation), r05);
        this.sendLocationCell.setHasLocation(true);
        return;
    L10:
        if (TextUtils.isEmpty(this.addressName) == true) goto L13;
        r05 = this.addressName;
        goto L16
    L13:
        if (this.fetchingLocation == false) goto L15;
        r05 = LocaleController.getString(R.string.Loading);
        goto L16
    L15:
        r05 = LocaleController.getString(R.string.UnknownLocation);
        goto L16
    }

    public void fetchLocationAddress() {
        int r0 = this.locationType;
        if (r0 != 8) goto L11;
        Location r02 = this.customLocation;
        if (r02 != null) goto L9;
        r02 = this.gpsLocation;
        if (r02 != null) goto L9;
        return;
    L9:
        this.fetchingLocation = true;
        updateCell();
        LocationController.fetchLocationAddress(r02, this.biz ? 1 : 0, this);
        return;
    L11:
        Location r1 = this.customLocation;
        if (r0 != 4) goto L24;
        if (r1 != null) goto L17;
        r1 = this.gpsLocation;
        if (r1 != null) goto L17;
        return;
    L17:
        Location r03 = this.previousFetchedLocation;
        if (r03 != null) goto L20;
    L21:
        this.addressName = null;
    L22:
        this.fetchingLocation = true;
        updateCell();
        LocationController.fetchLocationAddress(r1, this);
        return;
    L20:
        if (r03.distanceTo(r1) <= 100.0f) goto L22;
    L24:
        if (r1 == null) goto L38;
        Location r04 = this.previousFetchedLocation;
        if (r04 != null) goto L28;
    L29:
        this.addressName = null;
    L30:
        this.fetchingLocation = true;
        updateCell();
        if (this.stories == false) goto L33;
        int r05 = 2;
    L34:
        LocationController.fetchLocationAddress(r1, r05, this);
        return;
    L33:
        r05 = 0;
        goto L34
    L28:
        if (r04.distanceTo(r1) <= 20.0f) goto L30;
    }

    public String getAddressName() {
        return this.addressName;
    }

    public Object getItem(int r9) {
        int r0 = this.locationType;
        int r2 = 4;
        if (r0 == 4) goto L5;
        MessageObject r3 = this.currentMessageObject;
        if (r3 == null) goto L24;
        if (r9 != 1) goto L19;
        return r3;
    L19:
        if (r9 > 4) goto L21;
    L62:
        return null;
    L21:
        if (r9 >= (this.places.size() + 4)) goto L62;
        return this.currentLiveLocations.get(r9 - 5);
    L24:
        int r32 = 2;
        if (r0 != 2) goto L35;
        LocationController.SharingLocationInfo r02 = LocationController.getInstance(this.currentAccount).getSharingLocationInfo(this.dialogId);
        if (r02 != null) goto L29;
    L31:
        if (r9 >= r32) goto L33;
        return null;
    L33:
        return this.currentLiveLocations.get(r9 - r32);
    L29:
        if (r02.period == Integer.MAX_VALUE) goto L31;
        r32 = 3;
        goto L31
    L35:
        if (r0 != 1) goto L42;
        if (r9 <= 4) goto L62;
        if (r9 >= (this.places.size() + 5)) goto L62;
        return this.places.get(r9 - 5);
    L42:
        if (r0 == 7) goto L44;
        if (r9 <= 3) goto L62;
        if (r9 >= (this.places.size() + 4)) goto L62;
        return this.places.get(r9 - 4);
    L44:
        if (this.street != null) goto L46;
        r2 = 3;
    L46:
        if (r9 <= r2) goto L51;
        int r33 = r2 + 1;
        if (r9 >= (this.locations.size() + r33)) goto L51;
        return this.locations.get(r9 - r33);
    L51:
        int r22 = r2 + this.locations.size();
        if (r9 <= r22) goto L62;
        int r23 = r22 + 1;
        if (r9 >= (this.places.size() + r23)) goto L62;
        return this.places.get(r9 - r23);
    L5:
        if (this.addressName != null) goto L7;
        return null;
    L7:
        TLRPC.TL_messageMediaVenue r92 = new TLRPC.TL_messageMediaVenue();
        r92.address = this.addressName;
        TLRPC.TL_geoPoint r03 = new TLRPC.TL_geoPoint();
        r92.geo = r03;
        Location r1 = this.customLocation;
        if (r1 == null) goto L11;
        r03.lat = r1.getLatitude();
        r92.geo._long = this.customLocation.getLongitude();
        return r92;
    L11:
        Location r12 = this.gpsLocation;
        if (r12 == null) goto L14;
        r03.lat = r12.getLatitude();
        r92.geo._long = this.gpsLocation.getLongitude();
    L14:
        return r92;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int r0 = this.locationType;
        int r2 = 2;
        int r3 = 6;
        if (r0 == 6) goto L67;
        if (r0 == 5) goto L67;
        if (r0 == 4) goto L67;
        if (this.biz == true) goto L67;
        int r6 = 0;
        if (this.currentMessageObject != null) goto L17;
        if (r0 != 2) goto L32;
        LocationController.SharingLocationInfo r02 = LocationController.getInstance(this.currentAccount).getSharingLocationInfo(this.dialogId);
        int r32 = this.currentLiveLocations.size() + 2;
        if (r02 != null) goto L28;
    L30:
        r2 = r32 + r6;
        goto L67
    L28:
        if (r02.period == Integer.MAX_VALUE) goto L30;
        r6 = 1;
        goto L30
    L32:
        if (this.searching == false) goto L34;
    L43:
        int r03 = this.locationType;
        if (r03 != 0) goto L47;
        r3 = 5;
    L53:
        boolean r04 = this.myLocationDenied;
        if (r04 == false) goto L56;
    L60:
        int r4 = 0;
    L61:
        int r42 = r4 + (this.needEmptyView ? 1 : 0);
        if (r04 == true) goto L65;
        r2 = 0;
    L65:
        r2 = r3 + (r42 - r2);
        goto L67
    L56:
        if (this.searching == false) goto L58;
    L59:
        r4 = 2;
        goto L61
    L58:
        if (this.searched == true) goto L60;
    L47:
        if (r03 != 7) goto L53;
        if (this.street == null) goto L51;
        int r05 = 1;
    L52:
        r3 = r05 + 5;
        goto L53
    L51:
        r05 = 0;
        goto L52
    L34:
        if (this.searched == false) goto L43;
        if (this.places.isEmpty() == true) goto L43;
        if (this.locationType == 1) goto L42;
        r3 = 5;
    L42:
        r2 = r3 + ((this.locations.size() + this.places.size()) + (this.needEmptyView ? 1 : 0));
        goto L67
    L17:
        if (this.currentLiveLocations.isEmpty() == true) goto L19;
        r6 = this.currentLiveLocations.size() + 3;
    L23:
        r2 = 2 + r6;
        goto L67
    L19:
        if (this.fromStories == true) goto L23;
        r6 = 1;
    L67:
        if (this.sharedMediaLayout != null) goto L69;
        return r2;
    L69:
        if (this.sharedMediaLayoutVisible == true) goto L71;
        return r2;
    L71:
        return r2 + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int r12) {
        if (r12 == (getItemCount() - 1)) goto L5;
    L10:
        if (r12 != 0) goto L14;
        return 0;
    L14:
        if (this.locationType != 6) goto L17;
        return 8;
    L17:
        if (this.needEmptyView == true) goto L19;
    L22:
        int r0 = this.locationType;
        if (r0 != 5) goto L26;
        return 8;
    L26:
        if (r0 != 4) goto L29;
        return 1;
    L29:
        if (r0 == 2) goto L33;
        if (r0 == 1) goto L33;
        LocationController.SharingLocationInfo r02 = null;
    L35:
        if (this.currentMessageObject != null) goto L37;
        int r7 = this.locationType;
        if (r7 == 2) goto L52;
        if (r7 != 1) goto L97;
        if (r12 != 1) goto L68;
        return 1;
    L68:
        if (r12 != 2) goto L74;
        if (r02 == null) goto L72;
        this.shareLiveLocationPotistion = -1;
        return 7;
    L72:
        this.shareLiveLocationPotistion = r12;
        return 6;
    L74:
        if (r12 != 3) goto L76;
        return 10;
    L76:
        if (r12 != 4) goto L79;
        return 2;
    L79:
        if (this.searching == false) goto L81;
    L88:
        if (r12 <= 7) goto L90;
    L96:
        return 4;
    L90:
        if (this.searching == true) goto L94;
        if (this.searched == true) goto L96;
    L94:
        if (this.myLocationDenied == true) goto L96;
        return 3;
    L81:
        if (this.places.isEmpty() == true) goto L88;
        if (this.searched == false) goto L88;
        if (r12 != (this.places.size() + 5)) goto L124;
        return 5;
    L124:
        return 3;
    L97:
        int r03 = this.places.size() + this.locations.size();
        if (this.locationType == 7) goto L100;
    L107:
        int r2 = 4;
    L108:
        if (r12 != 1) goto L110;
        return 1;
    L110:
        if (r12 != 2) goto L112;
        return 10;
    L112:
        if (r12 != 3) goto L115;
        return 2;
    L115:
        if (this.searching == false) goto L117;
    L125:
        if (r12 <= 6) goto L127;
    L133:
        return 4;
    L127:
        if (this.searching == true) goto L131;
        if (this.searched == true) goto L133;
    L131:
        if (this.myLocationDenied == true) goto L133;
        return 3;
    L117:
        if (this.places.isEmpty() == false) goto L122;
        if (this.locations.isEmpty() == true) goto L125;
    L122:
        if (r12 != (r03 + r2)) goto L124;
        return 5;
    L100:
        if (r12 != 1) goto L103;
        return 12;
    L103:
        if (this.street == null) goto L107;
        if (r12 != 2) goto L106;
        return 12;
    L106:
        r12 = r12 - 1;
        r2 = 3;
        goto L108
    L52:
        if (r12 != 2) goto L57;
        if (r02 == null) goto L57;
        if (r02.period == Integer.MAX_VALUE) goto L57;
        return 7;
    L57:
        if (r12 != 1) goto L64;
        if (r02 != null) goto L60;
    L62:
        this.shareLiveLocationPotistion = r12;
        return 6;
    L60:
        if (r02.period != Integer.MAX_VALUE) goto L62;
        return 7;
    L64:
        return 8;
    L37:
        if (this.currentLiveLocations.isEmpty() == false) goto L41;
        if (r12 != 2) goto L48;
        return 9;
    L48:
        return 8;
    L41:
        if (r12 != 2) goto L43;
        return 10;
    L43:
        if (r12 != 3) goto L45;
        return 2;
    L45:
        if (r12 != 4) goto L48;
        this.shareLiveLocationPotistion = r12;
        return 6;
    L33:
        r02 = LocationController.getInstance(this.currentAccount).getSharingLocationInfo(this.dialogId);
        goto L35
    L19:
        if (r12 != (getItemCount() - 1)) goto L22;
        return 11;
    L5:
        if (this.sharedMediaLayout == null) goto L10;
        if (this.sharedMediaLayoutVisible == false) goto L10;
        return 13;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r6) {
        int r62 = r6.getItemViewType();
        if (r62 == 6) goto L5;
        if (r62 != 1) goto L13;
    L22:
        return true;
    L13:
        if (r62 == 3) goto L22;
        if (r62 == 8) goto L22;
        if (r62 == 12) goto L22;
        if (r62 == 7) goto L22;
        return false;
    L5:
        if (LocationController.getInstance(this.currentAccount).getSharingLocationInfo(this.dialogId) == null) goto L7;
    L10:
        return true;
    L7:
        if (this.gpsLocation != null) goto L10;
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r13, int r14) {
        TLRPC.TL_messageMediaVenue r1 = null;
        int r3 = 2;
        boolean r4 = false;
        switch(r13.getItemViewType()) {
            case 0: goto L111;
            case 1: goto L109;
            case 2: goto L103;
            case 3: goto L72;
            case 4: goto L70;
            case 5: goto L118;
            case 6: goto L59;
            case 7: goto L54;
            case 8: goto L28;
            case 9: goto L118;
            case 10: goto L118;
            case 11: goto L22;
            case 12: goto L5;
            default: goto L118;
        };
    L5:
        LocationCell r6 = (LocationCell) r13.itemView;
        if (this.askingForMyLocation == false) goto L13;
        if (r14 == 1) goto L9;
    L11:
        r6.setLocation(null, 2, r4);
        return;
    L9:
        if (this.street == null) goto L11;
        r4 = true;
        goto L11
    L13:
        if (r14 != 1) goto L20;
        TLRPC.TL_messageMediaVenue r7 = this.city;
        if (this.street == null) goto L17;
        boolean r10 = true;
    L18:
        r6.setLocation(r7, null, 2, r10, this.animated);
        return;
    L17:
        r10 = false;
        goto L18
    L20:
        r6.setLocation(this.street, null, 2, false, this.animated);
        return;
    L22:
        View r132 = r13.itemView;
        if (this.myLocationDenied == false) goto L25;
        int r142 = Theme.key_dialogBackgroundGray;
    L26:
        r132.setBackgroundColor(Theme.getColor(r142, this.resourcesProvider));
        return;
    L25:
        r142 = Theme.key_dialogBackground;
        goto L26
    L28:
        SharingLiveLocationCell r133 = (SharingLiveLocationCell) r13.itemView;
        if (this.locationType != 6) goto L32;
        r133.setDialog(this.currentMessageObject, this.gpsLocation, this.myLocationDenied);
        return;
    L32:
        TLRPC.TL_channelLocation r0 = this.chatLocation;
        if (r0 == null) goto L36;
        r133.setDialog(this.dialogId, r0);
        return;
    L36:
        MessageObject r02 = this.currentMessageObject;
        if (r02 == null) goto L41;
        if (r14 != 1) goto L41;
        r133.setDialog(r02, this.gpsLocation, this.myLocationDenied);
        return;
    L41:
        if (r02 == null) goto L43;
        r3 = 5;
    L43:
        int r143 = r14 - r3;
        LocationController.SharingLocationInfo r03 = LocationController.getInstance(this.currentAccount).getSharingLocationInfo(this.dialogId);
        if (r03 != null) goto L46;
    L48:
        if (r143 >= 0) goto L50;
        return;
    L50:
        if (r143 >= this.currentLiveLocations.size()) goto L119;
        r133.setDialog(this.currentLiveLocations.get(r143), this.gpsLocation);
        return;
    L119:
        return;
    L46:
        if (r03.period == Integer.MAX_VALUE) goto L48;
        r143 = r143 - 1;
        goto L48
    L54:
        SendLocationCell r134 = (SendLocationCell) r13.itemView;
        if (this.gpsLocation == null) goto L57;
        r4 = true;
    L57:
        r134.setHasLocation(r4);
        return;
    L59:
        SendLocationCell r135 = (SendLocationCell) r13.itemView;
        if (this.gpsLocation == null) goto L62;
        boolean r04 = true;
    L63:
        r135.setHasLocation(r04);
        int r144 = r14 + 1;
        if (r144 < getItemCount()) goto L66;
    L68:
        r135.useDivider = r4;
        r135.invalidate();
        return;
    L66:
        if (getItemViewType(r144) != 7) goto L68;
        r4 = true;
        goto L68
    L62:
        r04 = false;
        goto L63
    L70:
        ((LocationLoadingCell) r13.itemView).setLoading(this.searching);
        return;
    L72:
        LocationCell r136 = (LocationCell) r13.itemView;
        int r05 = this.locationType;
        if (r05 != 0) goto L75;
        int r145 = r14 - 4;
    L85:
        if (this.searched == false) goto L90;
        if (r05 == 7) goto L88;
    L89:
        r4 = true;
        goto L90
    L88:
        if (this.searching == false) goto L89;
    L90:
        if (r4 == false) goto L100;
        if (r145 >= 0) goto L93;
    L95:
        int r06 = r145 - this.locations.size();
        if (r06 < 0) goto L100;
        if (r06 >= this.places.size()) goto L100;
        r1 = this.places.get(r06);
        goto L100
    L93:
        if (r145 >= this.locations.size()) goto L95;
        r1 = this.locations.get(r145);
    L101:
        r136.setLocation(r1, r3, true);
        return;
    L100:
        r3 = r145;
        goto L101
    L75:
        if (r05 != 7) goto L77;
    L80:
        int r62 = r14 - 4;
        if (this.street != null) goto L79;
        r145 = r62;
    L79:
        r145 = r14 - 5;
        goto L85
    L77:
        if (r05 != 8) goto L79;
    L103:
        HeaderCell r137 = (HeaderCell) r13.itemView;
        if (this.currentMessageObject == null) goto L107;
        r137.setText(LocaleController.getString(R.string.LiveLocations));
        return;
    L107:
        r137.setText(LocaleController.getString(R.string.NearbyVenue));
        return;
    L109:
        this.sendLocationCell = (SendLocationCell) r13.itemView;
        updateCell();
        return;
    L111:
        RecyclerView.LayoutParams r146 = (RecyclerView.LayoutParams) r13.itemView.getLayoutParams();
        if (r146 != null) goto L114;
        r146 = new RecyclerView.LayoutParams(-1, this.overScrollHeight);
    L115:
        r13.itemView.setLayoutParams(r146);
        return;
    L114:
        ((ViewGroup.MarginLayoutParams) r146).height = this.overScrollHeight;
        goto L115
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r5, int r6) {
        switch(r6) {
            case 0: goto L27;
            case 1: goto L26;
            case 2: goto L25;
            case 3: goto L24;
            case 4: goto L23;
            case 5: goto L22;
            case 6: goto L21;
            case 7: goto L20;
            case 8: goto L10;
            case 9: goto L9;
            case 10: goto L8;
            case 11: goto L4;
            case 12: goto L6;
            case 13: goto L5;
            default: goto L4;
        };
    L4:
        View r52 = new View(this.mContext);
    L29:
        return new RecyclerListView.Holder(r52);
    L5:
        r52 = this.sharedMediaLayout;
        goto L29
    L6:
        LocationCell r62 = new LocationCell(this.mContext, false, this.resourcesProvider);
        r62.setAllowTextAnimation(true);
        View r63 = r62;
    L7:
        r52 = r63;
        goto L29
    L8:
        View r53 = new ShadowSectionCell(this.mContext);
        Drawable r64 = Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow);
        new CombinedDrawable(new ColorDrawable(getThemedColor(Theme.key_windowBackgroundGray)), r64).setFullsize(true);
        r52 = r53;
        goto L29
    L9:
        LocationDirectionCell r54 = new LocationDirectionCell(this.mContext, this.resourcesProvider);
        r54.setOnButtonClick(new LocationActivityAdapter$$ExternalSyntheticLambda0(this));
        r52 = r54;
        goto L29
    L10:
        Context r65 = this.mContext;
        int r1 = this.locationType;
        if (r1 != 4) goto L13;
    L18:
        int r12 = 16;
    L19:
        r52 = new SharingLiveLocationCell(r65, true, r12, this.resourcesProvider);
        goto L29
    L13:
        if (r1 == 5) goto L18;
        if (r1 == 3) goto L18;
        r12 = 54;
        goto L19
    L20:
        SendLocationCell r55 = new SendLocationCell(this.mContext, true, true, this.resourcesProvider);
        r55.setDialogId(this.dialogId);
        r52 = r55;
        goto L29
    L21:
        SendLocationCell r66 = new SendLocationCell(this.mContext, true, false, this.resourcesProvider);
        r66.setDialogId(this.dialogId);
        r63 = r66;
        goto L7
    L22:
        r52 = new LocationPoweredCell(this.mContext, this.resourcesProvider);
        goto L29
    L23:
        r52 = new LocationLoadingCell(this.mContext, this.resourcesProvider);
        goto L29
    L24:
        r63 = new LocationCell(this.mContext, false, this.resourcesProvider);
        goto L7
    L25:
        r52 = new HeaderCell(this.mContext, this.resourcesProvider);
        goto L29
    L26:
        r63 = new SendLocationCell(this.mContext, false, false, this.resourcesProvider);
        goto L7
    L27:
        FrameLayout r56 = new FrameLayout(this.mContext);
        this.emptyCell = r56;
        r56.setTag(-33024);
        this.emptyCell.setLayoutParams(new RecyclerView.LayoutParams(-1, this.overScrollHeight));
        r52 = r56;
        goto L29
    }

    public void onDirectionClick() {
    }

    @Override // org.telegram.messenger.LocationController.LocationFetchCallback
    public void onLocationAddressAvailable(String r3, String r4, TLRPC.TL_messageMediaVenue r5, TLRPC.TL_messageMediaVenue r6, Location r7) {
        boolean r0 = false;
        this.fetchingLocation = false;
        this.previousFetchedLocation = r7;
        int r72 = this.locationType;
        if (r72 != 8) goto L5;
        this.addressName = r4;
    L7:
        if (r72 != 7) goto L12;
        if (this.askingForMyLocation == false) goto L12;
        this.city = null;
        this.street = null;
    L12:
        if (this.street == null) goto L14;
        boolean r42 = true;
    L15:
        if (r72 != 7) goto L29;
        this.city = r5;
        this.street = r6;
        if (r6 != null) goto L20;
        r0 = true;
    L20:
        if (r42 != r0) goto L27;
        notifyItemChanged(1);
        if (this.street != null) goto L25;
        notifyItemRemoved(2);
        return;
    L25:
        notifyItemInserted(2);
        return;
    L27:
        notifyItemRangeChanged(1, 2);
        return;
    L29:
        updateCell();
        return;
    L14:
        r42 = false;
        goto L15
    L5:
        this.addressName = r3;
        goto L7
    }

    public void setAddressNameOverride(String r1) {
        this.overrideAddressName = r1;
        updateCell();
    }

    public void setChatLocation(TLRPC.TL_channelLocation r1) {
        this.chatLocation = r1;
    }

    public void setCustomLocation(Location r1) {
        this.customLocation = r1;
        fetchLocationAddress();
        updateCell();
    }

    public void setGpsLocation(Location r3) {
        if (this.gpsLocation != null) goto L5;
        boolean r0 = true;
    L6:
        this.gpsLocation = r3;
        if (this.customLocation != null) goto L9;
        fetchLocationAddress();
    L9:
        if (r0 == false) goto L14;
        int r32 = this.shareLiveLocationPotistion;
        if (r32 <= 0) goto L14;
        notifyItemChanged(r32);
    L14:
        if (this.currentMessageObject == null) goto L18;
        notifyItemChanged(1, new Object());
        updateLiveLocations();
        return;
    L18:
        if (this.locationType == 2) goto L21;
        updateCell();
        return;
    L21:
        updateLiveLocations();
        return;
    L5:
        r0 = false;
        goto L6
    }

    public void setLiveLocations(ArrayList<LocationActivity.LiveLocation> r5) {
        this.currentLiveLocations = new ArrayList(r5);
        long r0 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        int r52 = 0;
    L4:
        if (r52 >= this.currentLiveLocations.size()) goto L12;
        if (this.currentLiveLocations.get(r52).id == r0) goto L11;
        if (this.currentLiveLocations.get(r52).object.out == true) goto L11;
        r52 = r52 + 1;
    L11:
        this.currentLiveLocations.remove(r52);
    L12:
        notifyDataSetChanged();
    }

    public void setMessageObject(MessageObject r1) {
        this.currentMessageObject = r1;
        notifyDataSetChanged();
    }

    public void setMyLocationDenied(boolean r2, boolean r3) {
        if (this.myLocationDenied == r2) goto L5;
    L7:
        this.myLocationDenied = r2;
        this.askingForMyLocation = r3;
        if (r3 == false) goto L10;
        this.city = null;
        this.street = null;
    L10:
        notifyDataSetChanged();
        return;
    L5:
        if (this.askingForMyLocation != r3) goto L7;
    }

    public void setOverScrollHeight(int r3) {
        this.overScrollHeight = r3;
        FrameLayout r32 = this.emptyCell;
        if (r32 == null) goto L10;
        RecyclerView.LayoutParams r33 = (RecyclerView.LayoutParams) r32.getLayoutParams();
        if (r33 != null) goto L7;
        r33 = new RecyclerView.LayoutParams(-1, this.overScrollHeight);
    L8:
        this.emptyCell.setLayoutParams(r33);
        this.emptyCell.forceLayout();
        return;
    L7:
        ((ViewGroup.MarginLayoutParams) r33).height = this.overScrollHeight;
        goto L8
    }

    public void setSharedMediaLayout(SharedMediaLayout r1) {
        this.sharedMediaLayout = r1;
    }

    public boolean setSharedMediaLayoutVisible(boolean r2) {
        if (this.sharedMediaLayoutVisible == r2) goto L6;
        this.sharedMediaLayoutVisible = r2;
        notifyDataSetChanged();
        return true;
    L6:
        return false;
    }

    public void setUpdateRunnable(Runnable r1) {
        this.updateRunnable = r1;
    }

    public void updateLiveLocations() {
        if (this.currentLiveLocations.isEmpty() == true) goto L6;
        notifyItemRangeChanged(2, this.currentLiveLocations.size(), new Object());
        return;
    }
}
