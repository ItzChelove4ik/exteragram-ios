package com.exteragram.messenger.pillstack.ui.pills.weather;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.pillstack.core.PillStackConfig;
import com.exteragram.messenger.pillstack.core.PillType;
import com.exteragram.messenger.pillstack.ui.PillStackPreferencesActivity;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.WebFile;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ClipRoundedDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.LocationActivity;
import org.telegram.ui.Stories.recorder.Weather;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\u0018\u0000 +2\u00020\u0001:\u0001+B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u001e\u0010\u001a\u001a\u00020\u00192\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH\u0014J0\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0014J\u0010\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u0014H\u0002J\b\u0010*\u001a\u00020\u0019H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "<init>", "()V", "mapPreviewContainer", "Landroid/widget/FrameLayout;", "mapMarker", "Landroid/view/View;", "mapLoadingDrawable", "Lorg/telegram/ui/Components/ClipRoundedDrawable;", "mapPreview", "Lorg/telegram/ui/Components/BackupImageView;", "currentGeo", "Lorg/telegram/tgnet/TLRPC$GeoPoint;", "addressContainer", "addressText", "Landroid/widget/TextView;", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "needHideTitle", _UrlKt.FRAGMENT_ENCODE_SET, "createView", "context", "Landroid/content/Context;", "updateMapPreview", _UrlKt.FRAGMENT_ENCODE_SET, "fillItems", PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", _UrlKt.FRAGMENT_ENCODE_SET, "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "updateLocationSetting", "useCurrent", "openMapPicker", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WeatherSettingsActivity extends BasePreferencesActivity {
    public static final Companion Companion = null;
    private FrameLayout addressContainer;
    private TextView addressText;
    private TLRPC.GeoPoint currentGeo;
    private ClipRoundedDrawable mapLoadingDrawable;
    private View mapMarker;
    private BackupImageView mapPreview;
    private FrameLayout mapPreviewContainer;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "ITEM_SHOW_WEATHER", _UrlKt.FRAGMENT_ENCODE_SET, "ITEM_USE_CURRENT_LOCATION", "ITEM_USE_CUSTOM_LOCATION", "ITEM_ENABLE_LOCATION", "ITEM_MAP_PREVIEW", "ITEM_ADDRESS", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0014J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0014J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"}, d2 = {"com/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1", "Lorg/telegram/ui/Components/BackupImageView;", "createImageReciever", "Lorg/telegram/messenger/ImageReceiver;", "onMeasure", _UrlKt.FRAGMENT_ENCODE_SET, "widthMeasureSpec", _UrlKt.FRAGMENT_ENCODE_SET, "heightMeasureSpec", "verifyDrawable", _UrlKt.FRAGMENT_ENCODE_SET, "who", "Landroid/graphics/drawable/Drawable;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.pillstack.ui.pills.weather.WeatherSettingsActivity$createView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends BackupImageView {
        final /* synthetic */ WeatherSettingsActivity this$0;

        public AnonymousClass1(Context r1, WeatherSettingsActivity r2) {
            this.this$0 = r2;
            super(r1);
        }

        @Override // org.telegram.ui.Components.BackupImageView
        public ImageReceiver createImageReciever() {
            return new WeatherSettingsActivity$createView$1$createImageReciever$1(this, this.this$0);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(240.0f), TLObject.FLAG_30));
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != WeatherSettingsActivity.access$getMapLoadingDrawable$p(this.this$0)) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    @Metadata(d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0014J\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"com/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$2", "Landroid/view/View;", "pin", "Landroid/graphics/drawable/Drawable;", "avatarDrawable", "Lorg/telegram/ui/Components/AvatarDrawable;", "avatarImage", "Lorg/telegram/messenger/ImageReceiver;", "dispatchDraw", _UrlKt.FRAGMENT_ENCODE_SET, "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", _UrlKt.FRAGMENT_ENCODE_SET, "heightMeasureSpec", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.pillstack.ui.pills.weather.WeatherSettingsActivity$createView$2, reason: invalid class name */
    public static final class AnonymousClass2 extends View {
        private final AvatarDrawable avatarDrawable;
        private final ImageReceiver avatarImage;
        private final Drawable pin;

        public AnonymousClass2(Context r2, WeatherSettingsActivity r3) {
            super(r2);
            this.pin = r2.getResources().getDrawable(R.drawable.map_pin_photo).mutate();
            AvatarDrawable r22 = new AvatarDrawable();
            this.avatarDrawable = r22;
            ImageReceiver r0 = new ImageReceiver(this);
            this.avatarImage = r0;
            r22.setInfo(r3.getUserConfig().getCurrentUser());
            r0.setForUserOrChat(r3.getUserConfig().getCurrentUser(), r22);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r6) {
            this.pin.setBounds(0, 0, AndroidUtilities.dp(62.0f), AndroidUtilities.dp(85.0f));
            this.pin.draw(r6);
            this.avatarImage.setRoundRadius(AndroidUtilities.dp(62.0f));
            this.avatarImage.setImageCoords(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(50.0f), AndroidUtilities.dp(50.0f));
            this.avatarImage.draw(r6);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(62.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(85.0f), TLObject.FLAG_30));
        }
    }

    public static void $r8$lambda$K18aKbEB02VwdgSk6OgSRlmQkY4(WeatherSettingsActivity r0, Location r1) {
        if (r1 == null) goto L5;
        r0.updateLocationSetting(true);
        return;
    }

    public static void $r8$lambda$dyTW6MAr64ZRqyJ5JhTAxzD0hK4(WeatherSettingsActivity r0, Location r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (r0.fragmentView == null) goto L10;
        UniversalAdapter r02 = r0.getListView$TMessagesProj().adapter;
        if (r02 == null) goto L11;
        r02.update(true);
        return;
    L11:
        return;
    }

    /* renamed from: $r8$lambda$kM8epmpckYHOpxod_f3EFFqG-34, reason: not valid java name */
    public static void m1541$r8$lambda$kM8epmpckYHOpxod_f3EFFqG34(WeatherSettingsActivity r0, View r1) {
        r0.openMapPicker();
    }

    public static void $r8$lambda$r1F3JTTSwGevkR3rdkI74dTACBQ(WeatherSettingsActivity r0, LocationActivity r1, TLRPC.MessageMedia r2, int r3, boolean r4, int r5, long r6) {
        r0.currentGeo = r2.geo;
        String r12 = r1.getAddressName();
        if (r12 != null) goto L5;
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        PillStackConfig.setCustomWeatherLocation(ExteraConfig.getGSON().toJson(r0.currentGeo));
        PillStackConfig.setCustomWeatherAddress(r12);
        r0.updateLocationSetting(false);
    }

    static {
        Companion = new Companion(null);
    }

    public WeatherSettingsActivity() {
    }

    public static final /* synthetic */ ClipRoundedDrawable access$getMapLoadingDrawable$p(WeatherSettingsActivity r0) {
        return r0.mapLoadingDrawable;
    }

    public static final /* synthetic */ View access$getMapMarker$p(WeatherSettingsActivity r0) {
        return r0.mapMarker;
    }

    private final void openMapPicker() {
        LocationActivity r0 = new LocationActivity(8);
        TLRPC.GeoPoint r1 = this.currentGeo;
        if (r1 == null) goto L5;
        TLRPC.TL_channelLocation r2 = new TLRPC.TL_channelLocation();
        r2.geo_point = r1;
        r2.address = PillStackConfig.getCustomWeatherAddress();
        r0.setInitialLocation(r2);
    L5:
        r0.setDelegate(new WeatherSettingsActivity$$ExternalSyntheticLambda1(this, r0));
        presentFragment(r0);
    }

    private final void updateLocationSetting(boolean r1) {
        PillStackConfig.setUseCurrentLocation(r1);
        PillStackConfig.notifySettingsChanged(new int[]{PillType.WEATHER.getId()});
        if (this.fragmentView == null) goto L8;
        updateMapPreview();
        UniversalAdapter r0 = getListView$TMessagesProj().adapter;
        if (r0 == null) goto L9;
        r0.update(true);
        return;
    L9:
        return;
    }

    private final void updateMapPreview() {
        TLRPC.GeoPoint r0 = this.currentGeo;
        TextView r1 = null;
        if (r0 != null) goto L9;
        BackupImageView r15 = this.mapPreview;
        if (r15 != null) goto L7;
        r15 = null;
    L7:
        r15.setImageBitmap(null);
        return;
    L9:
        View r2 = this.mapMarker;
        if (r2 != null) goto L12;
        r2 = null;
    L12:
        r2.setAlpha(0.0f);
        View r22 = this.mapMarker;
        if (r22 != null) goto L15;
        r22 = null;
    L15:
        r22.setTranslationY(-AndroidUtilities.dp(12.0f));
        BackupImageView r23 = this.mapPreview;
        if (r23 != null) goto L19;
        r23 = null;
    L19:
        if (r23.getMeasuredWidth() > 0) goto L21;
        int r24 = AndroidUtilities.displaySize.x;
    L25:
        int r25 = (int) (r24 / AndroidUtilities.density);
        int r14 = Math.min(2, (int) Math.ceil(r3));
        BackupImageView r3 = this.mapPreview;
        if (r3 != null) goto L28;
        BackupImageView r4 = null;
    L29:
        r4.setImage(ImageLocation.getForWebFile(WebFile.createWithGeoPoint(r0.lat, r0._long, 0, r14 * r25, r14 * 240, 15, r14)), r25 + "_240", this.mapLoadingDrawable, 0, null);
        TextView r152 = this.addressText;
        if (r152 == null) goto L33;
        r1 = r152;
    L33:
        r1.setText(PillStackConfig.getCustomWeatherAddress());
        return;
    L28:
        r4 = r3;
        goto L29
    L21:
        BackupImageView r26 = this.mapPreview;
        if (r26 != null) goto L24;
        r26 = null;
    L24:
        r24 = r26.getMeasuredWidth();
        goto L25
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r12) {
        TextView r0 = new TextView(r12);
        this.addressText = r0;
        r0.setTextSize(1, 14.0f);
        TextView r02 = this.addressText;
        FrameLayout r1 = null;
        if (r02 != null) goto L5;
        r02 = null;
    L5:
        r02.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        TextView r03 = this.addressText;
        if (r03 != null) goto L8;
        r03 = null;
    L8:
        r03.setGravity(17);
        TextView r04 = this.addressText;
        if (r04 != null) goto L11;
        r04 = null;
    L11:
        r04.setPadding(0, 0, 0, 0);
        FrameLayout r05 = new FrameLayout(r12);
        this.addressContainer = r05;
        TextView r3 = this.addressText;
        if (r3 != null) goto L14;
        r3 = null;
    L14:
        r05.addView(r3, LayoutHelper.createFrame(-1, -2.0f, 48, 21.0f, 15.0f, 21.0f, 15.0f));
        FrameLayout r06 = this.addressContainer;
        if (r06 != null) goto L17;
        r06 = null;
    L17:
        int r32 = Theme.key_windowBackgroundWhite;
        r06.setBackgroundColor(getThemedColor(r32));
        this.mapPreview = new AnonymousClass1(r12, this);
        SvgHelper.SvgDrawable r07 = DocumentObject.getSvgThumb(R.raw.map_placeholder, Theme.key_chat_outLocationIcon, 0.2f);
        r07.setColorKey(Theme.key_windowBackgroundWhiteBlackText, this.resourceProvider);
        r07.setAspectCenter(true);
        BackupImageView r2 = this.mapPreview;
        if (r2 != null) goto L20;
        r2 = null;
    L20:
        r07.setParent(r2.getImageReceiver());
        ClipRoundedDrawable r22 = new ClipRoundedDrawable(r07);
        this.mapLoadingDrawable = r22;
        BackupImageView r08 = this.mapPreview;
        if (r08 != null) goto L23;
        r08 = null;
    L23:
        r22.setCallback(r08);
        BackupImageView r09 = this.mapPreview;
        if (r09 != null) goto L26;
        r09 = null;
    L26:
        r09.setBackgroundColor(getThemedColor(r32));
        this.mapMarker = new AnonymousClass2(r12, this);
        FrameLayout r010 = new FrameLayout(r12);
        this.mapPreviewContainer = r010;
        BackupImageView r23 = this.mapPreview;
        if (r23 != null) goto L29;
        r23 = null;
    L29:
        r010.addView(r23, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r011 = this.mapPreviewContainer;
        if (r011 != null) goto L32;
        r011 = null;
    L32:
        View r24 = this.mapMarker;
        if (r24 != null) goto L35;
        r24 = null;
    L35:
        r011.addView(r24, LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, -31.0f, 0.0f, 0.0f));
        FrameLayout r012 = this.mapPreviewContainer;
        if (r012 == null) goto L39;
        r1 = r012;
    L39:
        r1.setOnClickListener(new WeatherSettingsActivity$$ExternalSyntheticLambda0(this));
        String r013 = PillStackConfig.getCustomWeatherLocation();
        if (r013 != null) goto L45;
    L42:
        updateMapPreview();
        return super.createView(r12);
    L45:
        this.currentGeo = (TLRPC.GeoPoint) ExteraConfig.getGSON().fromJson(r013, TLRPC.TL_geoPoint.class);     // Catch: Exception -> L44
        goto L42
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r4, UniversalAdapter r5) {
        r4.add(UItem.asTopView(getTitle(), LocaleController.getString(R.string.WeatherPillTopInfo), "RestrictedEmoji", "🌤"));
        r4.add(UItem.asButton(1, R.drawable.msg_settings_old, LocaleController.getString(R.string.PillStackPills)));
        r4.add(UItem.asShadow());
        if (PillStackConfig.getActivePills().contains(Integer.valueOf(PillType.WEATHER.getId())) == false) goto L31;
        r4.add(UItem.asHeader(LocaleController.getString(R.string.WeatherLocation)));
        r4.add(UItem.asRadio(2, LocaleController.getString(R.string.CurrentLocation)).setChecked(PillStackConfig.getUseCurrentLocation()));
        r4.add(UItem.asRadio(3, LocaleController.getString(R.string.SelectLocation)).setChecked(true ^ PillStackConfig.getUseCurrentLocation()));
        if (PillStackConfig.getUseCurrentLocation() == false) goto L15;
        r4.add(UItem.asShadow(LocaleController.getString(R.string.WeatherSettingsInfo)));
        if (Weather.isLocationPermissionGranted() == true) goto L12;
        r4.add(UItem.asButton(2, R.drawable.report, LocaleController.getString(R.string.WeatherLocationPermissionGrant)).red());
        r4.add(UItem.asShadow(LocaleController.getString(R.string.WeatherLocationPermissionNo)));
        return;
    L12:
        if (Weather.isLocationEnabled() == true) goto L30;
        r4.add(UItem.asButton(4, R.drawable.filled_location, LocaleController.getString(R.string.WeatherLocationServicesEnable)).accent());
        r4.add(UItem.asShadow(LocaleController.getString(R.string.GpsDisabledAlertText)));
        return;
    L30:
        return;
    L15:
        FrameLayout r52 = this.mapPreviewContainer;
        FrameLayout r0 = null;
        if (r52 != null) goto L18;
        r52 = null;
    L18:
        r4.add(UItem.asCustom(5, r52));
        String r53 = PillStackConfig.getCustomWeatherAddress();
        if (r53 != null) goto L21;
    L28:
        r4.add(UItem.asShadow(LocaleController.getString(R.string.WeatherSettingsInfo)));
        return;
    L21:
        if (r53.length() == 0) goto L28;
        FrameLayout r3 = this.addressContainer;
        if (r3 == null) goto L27;
        r0 = r3;
    L27:
        r4.add(UItem.asCustom(6, r0));
        goto L28
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.WeatherPill);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public boolean needHideTitle() {
        return true;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        switch(r1.id) {
            case 1: goto L25;
            case 2: goto L18;
            case 3: goto L10;
            case 4: goto L7;
            case 5: goto L5;
            case 6: goto L5;
            default: goto L27;
        };
    L5:
        openMapPicker();
        return;
    L7:
        Weather.getUserLocation(true, new WeatherSettingsActivity$$ExternalSyntheticLambda3(this));
        return;
    L25:
        presentFragment(new PillStackPreferencesActivity());
        return;
    L27:
        return;
    L10:
        if (PillStackConfig.getUseCurrentLocation() == true) goto L12;
    L15:
        openMapPicker();
        return;
    L12:
        if (this.currentGeo != null) goto L14;
        TLRPC.TL_geoPoint r12 = new TLRPC.TL_geoPoint();
        r12.lat = 55.7558d;
        r12._long = 37.6173d;
        this.currentGeo = r12;
        PillStackConfig.setCustomWeatherLocation(ExteraConfig.getGSON().toJson(this.currentGeo));
    L14:
        updateLocationSetting(false);
        goto L15
    L18:
        if (PillStackConfig.getUseCurrentLocation() == true) goto L20;
    L23:
        Weather.getUserLocation(true, new WeatherSettingsActivity$$ExternalSyntheticLambda2(this));
        return;
    L20:
        if (Weather.isLocationPermissionGranted() == false) goto L23;
    }
}
