package org.telegram.ui.Adapters;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LocationController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;

/* loaded from: classes6.dex */
public abstract class BaseLocationAdapter extends AdapterWithDiffUtils {
    public final boolean biz;
    private int currentAccount;
    private int currentRequestNum;
    private BaseLocationAdapterDelegate delegate;
    private long dialogId;
    private String lastFoundQuery;
    private Location lastSearchLocation;
    private String lastSearchQuery;
    protected ArrayList<TLRPC.TL_messageMediaVenue> locations;
    protected ArrayList<TLRPC.TL_messageMediaVenue> places;
    protected boolean searchInProgress;
    private Runnable searchRunnable;
    protected boolean searched;
    protected boolean searching;
    protected boolean searchingLocations;
    private boolean searchingUser;
    public final boolean stories;

    public interface BaseLocationAdapterDelegate {
        void didLoadSearchResult(ArrayList<TLRPC.TL_messageMediaVenue> r1);
    }

    public static /* synthetic */ void $r8$lambda$CF0c4mGfzI1CXkgoys9vnPpuHZ0(BaseLocationAdapter r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$searchBotUser$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FzuQb0P1QS76XIwMzky3lRNN7aQ(BaseLocationAdapter r0, TLRPC.TL_error r1, String r2, TLObject r3) {
        r0.lambda$searchPlacesWithQuery$6(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$P4uVBE_ypH02XO1GnWQNKYfbG28(BaseLocationAdapter r0, Locale r1, String r2, Locale r3, Location r4, String r5) {
        r0.lambda$searchPlacesWithQuery$5(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$Wn05OMmwiuJ2PFx8d1ojEg_SK7Q(BaseLocationAdapter r0, String r1, Location r2) {
        r0.lambda$searchDelayed$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$glF3cjSO1PWYapD_tol7O_MwpEk(BaseLocationAdapter r0, String r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$searchPlacesWithQuery$7(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$hRGTyB_XQRQ8oGrgVhhVW0SK48w(BaseLocationAdapter r0, String r1, Location r2) {
        r0.lambda$searchDelayed$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$oFwL5YCJAjqXcBmp3tNh6X_LpSY(BaseLocationAdapter r0, Location r1, String r2, ArrayList r3) {
        r0.lambda$searchPlacesWithQuery$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$pDm63QZaiU1ffuSDB66H09UzcDU(BaseLocationAdapter r0, TLObject r1) {
        r0.lambda$searchBotUser$2(r1);
    }

    public BaseLocationAdapter(boolean r2, boolean r3) {
        this.searched = false;
        this.locations = new ArrayList();
        this.places = new ArrayList();
        this.currentAccount = UserConfig.selectedAccount;
        this.stories = r2;
        this.biz = r3;
    }

    private /* synthetic */ void lambda$searchBotUser$2(TLObject r5) {
        TLRPC.TL_contacts_resolvedPeer r52 = (TLRPC.TL_contacts_resolvedPeer) r5;
        MessagesController.getInstance(this.currentAccount).putUsers(r52.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r52.chats, false);
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r52.users, r52.chats, true, true);
        Location r53 = this.lastSearchLocation;
        this.lastSearchLocation = null;
        searchPlacesWithQuery(this.lastSearchQuery, r53, false);
    }

    private /* synthetic */ void lambda$searchBotUser$3(TLObject r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L5;
        AndroidUtilities.runOnUIThread(new BaseLocationAdapter$$ExternalSyntheticLambda6(this, r1));
        return;
    }

    private /* synthetic */ void lambda$searchDelayed$0(String r2, Location r3) {
        this.searchRunnable = null;
        this.lastSearchLocation = null;
        searchPlacesWithQuery(r2, r3, true);
    }

    private /* synthetic */ void lambda$searchDelayed$1(String r2, Location r3) {
        AndroidUtilities.runOnUIThread(new BaseLocationAdapter$$ExternalSyntheticLambda7(this, r2, r3));
    }

    private /* synthetic */ void lambda$searchPlacesWithQuery$4(Location r2, String r3, ArrayList r4) {
        this.searchingLocations = false;
        if (r2 != null) goto L5;
        this.currentRequestNum = 0;
        this.searching = false;
        this.places.clear();
        this.searchInProgress = false;
        this.lastFoundQuery = r3;
    L5:
        this.locations.clear();
        this.locations.addAll(r4);
        update(true);
    }

    private /* synthetic */ void lambda$searchPlacesWithQuery$5(Locale r30, String r31, Locale r32, Location r33, String r34) {
        BaseLocationAdapter r0 = this;
        ArrayList r2 = new ArrayList();
        if (r0.biz == false) goto L6;
        int r3 = 10;
    L7:
        List<Address> r5 = new Geocoder(ApplicationLoader.applicationContext, r30).getFromLocationName(r31, 5);     // Catch: Exception -> L235
        if (r0.stories == false) goto L10;
        List<Address> r1 = new Geocoder(ApplicationLoader.applicationContext, r32).getFromLocationName(r31, 5);     // Catch: Exception -> L235
    L11:
        HashSet r4 = new HashSet();     // Catch: Exception -> L235
        HashSet r6 = new HashSet();     // Catch: Exception -> L235
        int r10 = 0;
    L12:
        if (r10 >= r5.size()) goto L232;
        Address r11 = r5.get(r10);     // Catch: Exception -> L235
        if (r1 != null) goto L17;
    L19:
        Address r12 = null;
    L21:
        if (r11.hasLatitude() == true) goto L23;
    L24:
        List<Address> r16 = r1;
        List<Address> r17 = r5;
        HashSet r13 = r6;
        int r18 = r10;
    L231:
        r10 = r18 + 1;
        r0 = this;
        r6 = r13;
        r1 = r16;
        r5 = r17;
        goto L12
    L23:
        if (r11.hasLongitude() == false) goto L24;
        double r132 = r11.getLatitude();     // Catch: Exception -> L235
        double r8 = r11.getLongitude();     // Catch: Exception -> L235
        StringBuilder r15 = new StringBuilder();     // Catch: Exception -> L235
        r16 = r1;
        StringBuilder r14 = new StringBuilder();     // Catch: Exception -> L235
        r17 = r5;
        StringBuilder r52 = new StringBuilder();     // Catch: Exception -> L235
        String r182 = r11.getLocality();     // Catch: Exception -> L235
        if (TextUtils.isEmpty(r182) == false) goto L28;
        r182 = r11.getAdminArea();     // Catch: Exception -> L235
    L28:
        String r7 = r182;
        if (r12 != null) goto L31;
    L33:
        r18 = r10;
        String r102 = r11.getThoroughfare();     // Catch: Exception -> L235
        Address r20 = r12;
        if (TextUtils.isEmpty(r102) == true) goto L44;
        HashSet r19 = r4;
        if (TextUtils.equals(r102, r11.getAdminArea()) == false) goto L40;
    L45:
        String r42 = r11.getSubLocality();     // Catch: Exception -> L235
        if (TextUtils.isEmpty(r42) == false) goto L48;
        String r43 = r11.getLocality();     // Catch: Exception -> L235
        if (TextUtils.isEmpty(r43) == false) goto L54;
    L59:
        r52 = null;
        boolean r44 = true;
    L61:
        if (TextUtils.isEmpty(r7) == false) goto L63;
        boolean r103 = true;
    L72:
        boolean r22 = r44;
        String r45 = r11.getCountryName();     // Catch: Exception -> L235
        if (TextUtils.isEmpty(r45) == true) goto L100;
        StringBuilder r23 = r52;
        boolean r24 = r103;
        if ("US".equals(r11.getCountryCode()) == false) goto L79;
    L86:
        String[] r104 = r45.split(" ");     // Catch: Exception -> L235
        int r53 = r104.length;     // Catch: Exception -> L235
        String r105 = _UrlKt.FRAGMENT_ENCODE_SET;
        HashSet r25 = r6;
        int r62 = 0;
    L87:
        if (r62 >= r53) goto L94;
        int r27 = r53;
        String r54 = r104[r62];     // Catch: Exception -> L235
        if (r54.length() <= 0) goto L91;
        int r28 = r62;
        r105 = r105 + r54.charAt(0);     // Catch: Exception -> L235
    L92:
        r62 = r28 + 1;     // Catch: Exception -> L235
        r53 = r27;
        goto L87
    L91:
        r28 = r62;
    L94:
        if (r14.length() <= 0) goto L96;
        r14.append(", ");     // Catch: Exception -> L235
    L96:
        r14.append(r105);     // Catch: Exception -> L235
        if (r15.length() <= 0) goto L99;
        r15.append(", ");     // Catch: Exception -> L235
    L99:
        r15.append(r45);     // Catch: Exception -> L235
    L103:
        if (r0.biz == false) goto L114;
        StringBuilder r46 = new StringBuilder();     // Catch: Exception -> L235
        String r63 = r11.getAddressLine(0);     // Catch: Exception -> L234
        if (TextUtils.isEmpty(r63) == true) goto L110;
        r46.append(r63);     // Catch: Exception -> L234
    L110:
        if (r46.length() <= 0) goto L112;
        TLRPC.TL_messageMediaVenue r64 = new TLRPC.TL_messageMediaVenue();     // Catch: Exception -> L235
        TLRPC.TL_geoPoint r72 = new TLRPC.TL_geoPoint();     // Catch: Exception -> L235
        r64.geo = r72;     // Catch: Exception -> L235
        r72.lat = r132;     // Catch: Exception -> L235
        r72._long = r8;     // Catch: Exception -> L235
        r64.query_id = -1;     // Catch: Exception -> L235
        r64.title = r46.toString();     // Catch: Exception -> L235
        r64.icon = "pin";     // Catch: Exception -> L235
        r64.address = LocaleController.getString(R.string.PassportAddress);     // Catch: Exception -> L235
        r2.add(r64);     // Catch: Exception -> L235
    L112:
        r4 = r19;
        r13 = r25;
        goto L231
    L114:
        if (r23 != null) goto L116;
    L194:
        StringBuilder r232 = r14;
    L195:
        if (r24 == true) goto L219;
        r13 = r25;
        if (r13.contains(r232.toString()) == true) goto L221;
        TLRPC.TL_messageMediaVenue r02 = new TLRPC.TL_messageMediaVenue();     // Catch: Exception -> L235
        TLRPC.TL_geoPoint r47 = new TLRPC.TL_geoPoint();     // Catch: Exception -> L235
        r02.geo = r47;     // Catch: Exception -> L235
        r47.lat = r132;     // Catch: Exception -> L235
        r47._long = r8;     // Catch: Exception -> L235
        r02.query_id = -1;     // Catch: Exception -> L235
        r02.title = r232.toString();     // Catch: Exception -> L235
        r02.icon = "https://ss3.4sqi.net/img/categories_v2/travel/hotel_64.png";     // Catch: Exception -> L235
        r02.emoji = LocationController.countryCodeToEmoji(r11.getCountryCode());     // Catch: Exception -> L235
        r13.add(r02.title);     // Catch: Exception -> L235
        r02.address = LocaleController.getString(R.string.PassportCity);     // Catch: Exception -> L235
        if (r20 == null) goto L216;
        TL_stories.TL_geoPointAddress r48 = new TL_stories.TL_geoPointAddress();     // Catch: Exception -> L235
        r02.geoAddress = r48;     // Catch: Exception -> L235
        r48.country_iso2 = r20.getCountryCode();     // Catch: Exception -> L235
        if (TextUtils.isEmpty(null) == false) goto L203;
        String r49 = r20.getLocality();     // Catch: Exception -> L235
    L205:
        if (TextUtils.isEmpty(r49) == false) goto L208;
        r49 = r20.getAdminArea();     // Catch: Exception -> L235
    L208:
        if (TextUtils.isEmpty(r49) == false) goto L210;
        r49 = r20.getSubAdminArea();     // Catch: Exception -> L235
    L210:
        String r55 = r20.getAdminArea();     // Catch: Exception -> L235
        if (TextUtils.isEmpty(r55) == true) goto L214;
        TL_stories.TL_geoPointAddress r65 = r02.geoAddress;     // Catch: Exception -> L235
        r65.state = r55;     // Catch: Exception -> L235
        r65.flags |= 1;
    L214:
        if (TextUtils.isEmpty(r49) == true) goto L216;
        TL_stories.TL_geoPointAddress r56 = r02.geoAddress;     // Catch: Exception -> L235
        r56.city = r49;     // Catch: Exception -> L235
        r56.flags |= 2;
        goto L216
    L203:
        r49 = null;
    L216:
        r2.add(r02);     // Catch: Exception -> L235
        if (r2.size() >= r3) goto L232;
    L221:
        if (r15.length() <= 0) goto L230;
        r4 = r19;
        if (r4.contains(r15.toString()) == true) goto L231;
        TLRPC.TL_messageMediaVenue r03 = new TLRPC.TL_messageMediaVenue();     // Catch: Exception -> L235
        TLRPC.TL_geoPoint r57 = new TLRPC.TL_geoPoint();     // Catch: Exception -> L235
        r03.geo = r57;     // Catch: Exception -> L235
        r57.lat = r132;     // Catch: Exception -> L235
        r57._long = r8;     // Catch: Exception -> L235
        r03.query_id = -1;     // Catch: Exception -> L235
        r03.title = r15.toString();     // Catch: Exception -> L235
        r03.icon = "https://ss3.4sqi.net/img/categories_v2/building/government_capitolbuilding_64.png";     // Catch: Exception -> L235
        r03.emoji = LocationController.countryCodeToEmoji(r11.getCountryCode());     // Catch: Exception -> L235
        r4.add(r03.title);     // Catch: Exception -> L235
        r03.address = LocaleController.getString(R.string.Country);     // Catch: Exception -> L235
        if (r20 == null) goto L227;
        TL_stories.TL_geoPointAddress r58 = new TL_stories.TL_geoPointAddress();     // Catch: Exception -> L235
        r03.geoAddress = r58;     // Catch: Exception -> L235
        r58.country_iso2 = r20.getCountryCode();     // Catch: Exception -> L235
    L227:
        r2.add(r03);     // Catch: Exception -> L235
        if (r2.size() < r3) goto L231;
    L230:
        r4 = r19;
        goto L231
    L219:
        r13 = r25;
        goto L221
    L116:
        if (r23.length() <= 0) goto L194;
        TLRPC.TL_messageMediaVenue r410 = new TLRPC.TL_messageMediaVenue();     // Catch: Exception -> L235
        TLRPC.TL_geoPoint r106 = new TLRPC.TL_geoPoint();     // Catch: Exception -> L235
        r410.geo = r106;     // Catch: Exception -> L235
        r106.lat = r132;     // Catch: Exception -> L235
        r106._long = r8;     // Catch: Exception -> L235
        r410.query_id = -1;     // Catch: Exception -> L235
        r410.title = r23.toString();     // Catch: Exception -> L235
        r410.icon = "pin";     // Catch: Exception -> L235
        if (r22 == false) goto L121;
        int r59 = R.string.PassportCity;     // Catch: Exception -> L235
    L122:
        r410.address = LocaleController.getString(r59);     // Catch: Exception -> L235
        if (r20 == null) goto L188;
        TL_stories.TL_geoPointAddress r510 = new TL_stories.TL_geoPointAddress();     // Catch: Exception -> L235
        r410.geoAddress = r510;     // Catch: Exception -> L235
        r510.country_iso2 = r20.getCountryCode();     // Catch: Exception -> L235
        if (TextUtils.isEmpty(null) == false) goto L127;
        String r511 = r20.getLocality();     // Catch: Exception -> L235
    L129:
        if (TextUtils.isEmpty(r511) == false) goto L132;
        r511 = r20.getAdminArea();     // Catch: Exception -> L235
    L132:
        if (TextUtils.isEmpty(r511) == false) goto L134;
        r511 = r20.getSubAdminArea();     // Catch: Exception -> L235
    L134:
        String r107 = r20.getAdminArea();     // Catch: Exception -> L235
        StringBuilder r04 = new StringBuilder();     // Catch: Exception -> L235
        if (TextUtils.isEmpty(r107) == true) goto L137;
        TL_stories.TL_geoPointAddress r110 = r410.geoAddress;     // Catch: Exception -> L235
        r110.state = r107;     // Catch: Exception -> L235
        r232 = r14;
        r110.flags |= 1;
    L139:
        if (TextUtils.isEmpty(r511) == true) goto L141;
        TL_stories.TL_geoPointAddress r111 = r410.geoAddress;     // Catch: Exception -> L235
        r111.city = r511;     // Catch: Exception -> L235
        r111.flags |= 2;
    L141:
        if (r22 == false) goto L143;
    L189:
        boolean r108 = false;
    L190:
        if (r108 == true) goto L195;
        r2.add(r410);     // Catch: Exception -> L235
        if (r2.size() < r3) goto L195;
    L143:
        if (TextUtils.isEmpty(null) == true) goto L145;
    L149:
        String r112 = null;
    L151:
        if (TextUtils.isEmpty(r112) == false) goto L158;
        if (TextUtils.equals(r20.getSubLocality(), r7) == true) goto L158;
        if (TextUtils.equals(r20.getSubLocality(), r20.getCountryName()) == true) goto L158;
        r112 = r20.getSubLocality();     // Catch: Exception -> L235
    L158:
        if (TextUtils.isEmpty(r112) == false) goto L165;
        if (TextUtils.equals(r20.getLocality(), r7) == true) goto L165;
        if (TextUtils.equals(r20.getLocality(), r20.getCountryName()) == true) goto L165;
        r112 = r20.getLocality();     // Catch: Exception -> L235
    L165:
        if (TextUtils.isEmpty(r112) == false) goto L167;
    L174:
        r04 = null;
    L176:
        if (TextUtils.isEmpty(r04) == true) goto L184;
        int r109 = 0;
    L178:
        String[] r113 = LocationController.unnamedRoads;     // Catch: Exception -> L235
        if (r109 >= r113.length) goto L184;
        if (r113[r109].equalsIgnoreCase(r04.toString()) == true) goto L182;
        r109 = r109 + 1;     // Catch: Exception -> L235
        goto L178
    L182:
        r108 = true;
    L186:
        if (TextUtils.isEmpty(r04) == true) goto L190;
        TL_stories.TL_geoPointAddress r114 = r410.geoAddress;     // Catch: Exception -> L235
        r114.flags |= 4;
        r114.street = r04.toString();     // Catch: Exception -> L235
    L184:
        r108 = false;
        goto L186
    L167:
        if (TextUtils.equals(r112, r107) == true) goto L174;
        if (TextUtils.equals(r112, r20.getCountryName()) == true) goto L174;
        if (r04.length() <= 0) goto L173;
        r04.append(", ");     // Catch: Exception -> L235
    L173:
        r04.append(r112);     // Catch: Exception -> L235
        goto L176
    L145:
        if (TextUtils.equals(r20.getThoroughfare(), r7) == true) goto L149;
        if (TextUtils.equals(r20.getThoroughfare(), r20.getCountryName()) == true) goto L149;
        r112 = r20.getThoroughfare();     // Catch: Exception -> L235
        goto L151
    L137:
        r232 = r14;
        goto L139
    L127:
        r511 = null;
        goto L129
    L188:
        r232 = r14;
        goto L189
    L121:
        r59 = R.string.PassportStreet1;     // Catch: Exception -> L235
        goto L122
    L79:
        if ("AE".equals(r11.getCountryCode()) == true) goto L86;
        if ("GB".equals(r11.getCountryCode()) == true) goto L83;
    L85:
        r105 = r45;
        r25 = r6;
        goto L94
    L83:
        if ("en".equals(r30.getLanguage()) == false) goto L85;
    L100:
        r23 = r52;
        r25 = r6;
        r24 = r103;
        goto L103
    L63:
        if (r14.length() <= 0) goto L65;
        r14.append(", ");     // Catch: Exception -> L235
    L65:
        r14.append(r7);     // Catch: Exception -> L235
        if (r52 != null) goto L68;
    L71:
        r103 = false;
        goto L72
    L68:
        if (r52.length() <= 0) goto L70;
        r52.append(", ");     // Catch: Exception -> L235
    L70:
        r52.append(r7);     // Catch: Exception -> L235
        goto L71
    L54:
        if (TextUtils.equals(r43, r7) == true) goto L59;
        if (r52.length() <= 0) goto L58;
        r52.append(", ");     // Catch: Exception -> L235
    L58:
        r52.append(r43);     // Catch: Exception -> L235
    L43:
        r44 = false;
        goto L61
    L48:
        if (r52.length() <= 0) goto L50;
        r52.append(", ");     // Catch: Exception -> L235
    L50:
        r52.append(r42);     // Catch: Exception -> L235
        goto L43
    L40:
        if (r52.length() <= 0) goto L42;
        r52.append(", ");     // Catch: Exception -> L235
    L42:
        r52.append(r102);     // Catch: Exception -> L235
        goto L43
    L44:
        r19 = r4;
        goto L45
    L31:
        if (TextUtils.isEmpty(r12.getLocality()) == false) goto L33;
        r12.getAdminArea();     // Catch: Exception -> L235
        goto L33
    L17:
        if (r10 >= r1.size()) goto L19;
        r12 = r1.get(r10);     // Catch: Exception -> L235
        goto L21
    L10:
        r1 = null;
        goto L11
    L6:
        r3 = 5;
    L232:
        AndroidUtilities.runOnUIThread(new BaseLocationAdapter$$ExternalSyntheticLambda4(this, r33, r34, r2));
    }

    private /* synthetic */ void lambda$searchPlacesWithQuery$6(TLRPC.TL_error r6, String r7, TLObject r8) {
        if (r6 != null) goto L12;
        int r62 = 0;
        this.currentRequestNum = 0;
        this.searching = false;
        this.places.clear();
        this.searchInProgress = false;
        this.lastFoundQuery = r7;
        TLRPC.messages_BotResults r82 = (TLRPC.messages_BotResults) r8;
        int r72 = r82.results.size();
    L4:
        if (r62 >= r72) goto L12;
        TLRPC.BotInlineResult r0 = r82.results.get(r62);
        if ("venue".equals(r0.type) == false) goto L11;
        TLRPC.BotInlineMessage r1 = r0.send_message;
        if ((r1 instanceof TLRPC.TL_botInlineMessageMediaVenue) == false) goto L11;
        TLRPC.TL_botInlineMessageMediaVenue r12 = (TLRPC.TL_botInlineMessageMediaVenue) r1;
        TLRPC.TL_messageMediaVenue r2 = new TLRPC.TL_messageMediaVenue();
        r2.geo = r12.geo;
        r2.address = r12.address;
        r2.title = r12.title;
        r2.icon = "https://ss3.4sqi.net/img/categories_v2/" + r12.venue_type + "_64.png";
        r2.venue_type = r12.venue_type;
        r2.venue_id = r12.venue_id;
        r2.provider = r12.provider;
        r2.query_id = r82.query_id;
        r2.result_id = r0.id;
        this.places.add(r2);
    L11:
        r62 = r62 + 1;
    L12:
        BaseLocationAdapterDelegate r63 = this.delegate;
        if (r63 == null) goto L15;
        r63.didLoadSearchResult(this.places);
    L15:
        update(true);
    }

    private /* synthetic */ void lambda$searchPlacesWithQuery$7(String r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new BaseLocationAdapter$$ExternalSyntheticLambda5(this, r4, r2, r3));
    }

    private void searchBotUser() {
        if (this.searchingUser == false) goto L5;
        return;
    L5:
        this.searchingUser = true;
        TLRPC.TL_contacts_resolveUsername r0 = new TLRPC.TL_contacts_resolveUsername();
        boolean r1 = this.stories;
        int r2 = this.currentAccount;
        if (r1 == false) goto L8;
        String r12 = MessagesController.getInstance(r2).storyVenueSearchBot;
    L9:
        r0.username = r12;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new BaseLocationAdapter$$ExternalSyntheticLambda2(this));
        return;
    L8:
        r12 = MessagesController.getInstance(r2).venueSearchBot;
        goto L9
    }

    public void destroy() {
        if (this.currentRequestNum == 0) goto L6;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.currentRequestNum, true);
        this.currentRequestNum = 0;
        return;
    }

    public String getLastSearchString() {
        return this.lastFoundQuery;
    }

    public boolean isSearching() {
        return this.searchInProgress;
    }

    public void searchDelayed(String r4, Location r5) {
        if (r4 != null) goto L5;
    L12:
        this.places.clear();
        this.locations.clear();
        this.searchInProgress = false;
        update(true);
        return;
    L5:
        if (r4.length() == 0) goto L12;
        if (this.searchRunnable == null) goto L10;
        Utilities.searchQueue.cancelRunnable(this.searchRunnable);
        this.searchRunnable = null;
    L10:
        this.searchInProgress = true;
        DispatchQueue r0 = Utilities.searchQueue;
        BaseLocationAdapter$$ExternalSyntheticLambda3 r1 = new BaseLocationAdapter$$ExternalSyntheticLambda3(this, r4, r5);
        this.searchRunnable = r1;
        r0.postRunnable(r1, 400);
    }

    public void searchPlacesWithQuery(String r2, Location r3, boolean r4) {
        searchPlacesWithQuery(r2, r3, r4, false);
    }

    public void searchPlacesWithQuery(String r11, Location r12, boolean r13, boolean r14) {
        if (r12 == null) goto L4;
    L5:
        Location r142 = this.lastSearchLocation;
        if (r142 == null) goto L11;
        if (r12 == null) goto L11;
        if (r12.distanceTo(r142) >= 200.0f) goto L11;
        return;
    L11:
        Locale r143 = null;
        if (r12 != null) goto L14;
        Location r0 = null;
    L15:
        this.lastSearchLocation = r0;
        this.lastSearchQuery = r11;
        if (this.searching == false) goto L20;
        this.searching = false;
        if (this.currentRequestNum == 0) goto L20;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.currentRequestNum, true);
        this.currentRequestNum = 0;
    L20:
        getItemCount();
        this.searching = true;
        this.searched = true;
        MessagesController r02 = MessagesController.getInstance(this.currentAccount);
        boolean r3 = this.stories;
        int r4 = this.currentAccount;
        if (r3 == false) goto L23;
        String r32 = MessagesController.getInstance(r4).storyVenueSearchBot;
    L24:
        TLObject r03 = r02.getUserOrChat(r32);
        if ((r03 instanceof TLRPC.User) == true) goto L29;
        if (r13 == false) goto L61;
        searchBotUser();
        return;
    L61:
        return;
    L29:
        TLRPC.User r04 = (TLRPC.User) r03;
        TLRPC.TL_messages_getInlineBotResults r132 = new TLRPC.TL_messages_getInlineBotResults();
        if (r11 != null) goto L32;
        String r42 = _UrlKt.FRAGMENT_ENCODE_SET;
    L33:
        r132.query = r42;
        r132.bot = MessagesController.getInstance(this.currentAccount).getInputUser(r04);
        r132.offset = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r12 == null) goto L37;
        TLRPC.TL_inputGeoPoint r05 = new TLRPC.TL_inputGeoPoint();
        r132.geo_point = r05;
        r05.lat = AndroidUtilities.fixLocationCoord(r12.getLatitude());
        r132.geo_point._long = AndroidUtilities.fixLocationCoord(r12.getLongitude());
        r132.flags |= 1;
    L37:
        if (DialogObject.isEncryptedDialog(this.dialogId) == false) goto L39;
        r132.peer = new TLRPC.TL_inputPeerEmpty();
    L41:
        if (TextUtils.isEmpty(r11) == false) goto L43;
    L47:
        BaseLocationAdapter r43 = this;
        String r6 = r11;
        Location r8 = r12;
        r43.searchingLocations = false;
    L57:
        if (r8 == null) goto L62;
        r43.currentRequestNum = ConnectionsManager.getInstance(r43.currentAccount).sendRequest(r132, new BaseLocationAdapter$$ExternalSyntheticLambda1(r43, r6));
        r43.update(true);
        return;
    L62:
        return;
    L43:
        if (this.stories == false) goto L45;
    L48:
        this.searchingLocations = true;
        Locale r5 = LocaleController.getInstance().getCurrentLocale();
        if (this.stories == true) goto L51;
    L54:
        Locale r7 = r143;
    L55:
        r43 = this;
        r6 = r11;
        r8 = r12;
        Utilities.globalQueue.postRunnable(new BaseLocationAdapter$$ExternalSyntheticLambda0(r43, r5, r6, r7, r8, r11));
        goto L57
    L51:
        if (r5.getLanguage().contains("en") == false) goto L53;
        r7 = r5;
        goto L55
    L53:
        r143 = Locale.US;
        goto L54
    L45:
        if (this.biz == false) goto L47;
    L39:
        r132.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        goto L41
    L32:
        r42 = r11;
        goto L33
    L23:
        r32 = MessagesController.getInstance(r4).venueSearchBot;
        goto L24
    L14:
        r0 = new Location(r12);
        goto L15
    L4:
        if (this.stories == true) goto L5;
    }

    public void setDelegate(long r1, BaseLocationAdapterDelegate r3) {
        this.dialogId = r1;
        this.delegate = r3;
    }

    public void update(boolean r1) {
        notifyDataSetChanged();
    }
}
