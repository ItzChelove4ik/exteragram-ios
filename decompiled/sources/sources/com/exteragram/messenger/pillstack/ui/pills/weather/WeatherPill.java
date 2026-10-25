package com.exteragram.messenger.pillstack.ui.pills.weather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.location.Location;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.exteragram.messenger.pillstack.core.PillStackConfig;
import com.exteragram.messenger.pillstack.core.PillType;
import com.exteragram.messenger.pillstack.ui.pills.BasePill;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.recorder.Weather;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class WeatherPill extends BasePill implements NotificationCenter.NotificationCenterDelegate {
    private final ImageView iconView;
    private final LinearLayout layout;
    private boolean showingWeather;
    private final AnimatedTextView textView;

    public static /* synthetic */ void $r8$lambda$1CTX_OyKX_UWS8yWqpD6L19h2lM(WeatherPill r0) {
        r0.lambda$onPillLongClicked$0();
    }

    public static /* synthetic */ void $r8$lambda$EpjcOaSGvjehzctNosNGVWFT7IM(WeatherPill r0) {
        r0.lambda$onUpdateData$3();
    }

    public static /* synthetic */ void $r8$lambda$QrHj7fIke27WShAPmUPwFh1i0Ns(WeatherPill r0, Location r1) {
        r0.lambda$requestLocationAndUpdate$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$gq51BIjpNeerpJGnrv9pZF8QkKo(BaseFragment r1) {
        r1.presentFragment(new WeatherSettingsActivity());
    }

    public static /* synthetic */ void $r8$lambda$lkSwwUy3i43jI6zZhgViaMCCqb0(WeatherPill r0, Weather.State r1) {
        r0.lambda$onUpdateData$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$xbxBWjRvhVdtMplCMwanqx4EhxY(WeatherPill r0, Weather.State r1) {
        r0.lambda$onUpdateData$2(r1);
    }

    public WeatherPill(Context r13, Theme.ResourcesProvider r14) {
        super(r13, r14);
        LinearLayout r142 = new LinearLayout(r13);
        this.layout = r142;
        r142.setOrientation(0);
        r142.setGravity(17);
        r142.setMinimumWidth(AndroidUtilities.dp(48.0f));
        r142.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        if (LocaleController.isRTL == false) goto L5;
        int r1 = 3;
    L6:
        addView(r142, LayoutHelper.createFrame(-2, 28, r1 | 16));
        ImageView r12 = new ImageView(r13);
        this.iconView = r12;
        r12.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        r142.addView(r12, LayoutHelper.createLinear(16, 16, 16, 0, 0, 4, 0));
        AnimatedTextView r15 = new AnimatedTextView(r13, true, true, true);
        this.textView = r15;
        r15.setTextSize(AndroidUtilities.dp(13.0f));
        r15.setTypeface(AndroidUtilities.bold());
        r15.setIncludeFontPadding(false);
        r15.adaptWidth = true;
        NotificationCenter.listenEmojiLoading(r15);
        r142.addView(r15, LayoutHelper.createLinear(-2, -2, 16));
        setLoadingTargetView(r142);
        updateColors();
        ScaleStateListAnimator.apply(r142);
        Weather.State r132 = Weather.getCached();
        if (r132 == null) goto L10;
        setData(r132, false);
        return;
    L10:
        return;
    L5:
        r1 = 5;
        goto L6
    }

    private int getWeatherIconRes(String r3) {
        if (r3 != null) goto L5;
        return 0;
    L5:
        char r1 = 65535;
        switch(r3.hashCode()) {
            case 9728: goto L81;
            case 9729: goto L77;
            case 9889: goto L73;
            case 9925: goto L69;
            case 9928: goto L65;
            case 10052: goto L61;
            case 1773143: goto L57;
            case 1773144: goto L53;
            case 1773146: goto L49;
            case 1773147: goto L45;
            case 1773150: goto L41;
            case 1773151: goto L37;
            case 1773152: goto L33;
            case 1773153: goto L29;
            case 1773160: goto L25;
            case 1773162: goto L21;
            case 1773163: goto L17;
            case 1773164: goto L13;
            case 1288093443: goto L9;
            default: goto L84;
        };
    L84:
        switch(r1) {
            case 0: goto L101;
            case 1: goto L99;
            case 2: goto L97;
            case 3: goto L95;
            case 4: goto L97;
            case 5: goto L93;
            case 6: goto L91;
            case 7: goto L91;
            case 8: goto L91;
            case 9: goto L91;
            case 10: goto L91;
            case 11: goto L91;
            case 12: goto L91;
            case 13: goto L91;
            case 14: goto L95;
            case 15: goto L89;
            case 16: goto L89;
            case 17: goto L93;
            case 18: goto L87;
            default: goto L85;
        };
    L85:
        return 0;
    L87:
        return R.drawable.weather_foggy;
    L89:
        return R.drawable.weather_rainy;
    L91:
        return R.drawable.weather_night;
    L93:
        return R.drawable.weather_snowy;
    L95:
        return R.drawable.weather_partly_cloudy;
    L97:
        return R.drawable.weather_thunderstorm;
    L99:
        return R.drawable.weather_cloudy;
    L101:
        return R.drawable.weather_sunny;
    L9:
        if (r3.equals("😶\u200d🌫") == false) goto L84;
        r1 = 18;
        goto L84
    L13:
        if (r3.equals("🌨") == false) goto L84;
        r1 = 17;
        goto L84
    L17:
        if (r3.equals("🌧") == false) goto L84;
        r1 = 16;
        goto L84
    L21:
        if (r3.equals("🌦") == false) goto L84;
        r1 = 15;
        goto L84
    L25:
        if (r3.equals("🌤") == false) goto L84;
        r1 = 14;
        goto L84
    L29:
        if (r3.equals("🌝") == false) goto L84;
        r1 = '\r';
        goto L84
    L33:
        if (r3.equals("🌜") == false) goto L84;
        r1 = '\f';
        goto L84
    L37:
        if (r3.equals("🌛") == false) goto L84;
        r1 = 11;
        goto L84
    L41:
        if (r3.equals("🌚") == false) goto L84;
        r1 = '\n';
        goto L84
    L45:
        if (r3.equals("🌗") == false) goto L84;
        r1 = '\t';
        goto L84
    L49:
        if (r3.equals("🌖") == false) goto L84;
        r1 = '\b';
        goto L84
    L53:
        if (r3.equals("🌔") == false) goto L84;
        r1 = 7;
        goto L84
    L57:
        if (r3.equals("🌓") == false) goto L84;
        r1 = 6;
        goto L84
    L61:
        if (r3.equals("❄") == false) goto L84;
        r1 = 5;
        goto L84
    L65:
        if (r3.equals("⛈") == false) goto L84;
        r1 = 4;
        goto L84
    L69:
        if (r3.equals("⛅") == false) goto L84;
        r1 = 3;
        goto L84
    L73:
        if (r3.equals("⚡") == false) goto L84;
        r1 = 2;
        goto L84
    L77:
        if (r3.equals("☁") == false) goto L84;
        r1 = 1;
        goto L84
    L81:
        if (r3.equals("☀") == false) goto L84;
        r1 = 0;
        goto L84
    }

    private /* synthetic */ void lambda$onPillLongClicked$0() {
        onUpdateData(true);
    }

    private /* synthetic */ void lambda$onUpdateData$2(Weather.State r2) {
        setData(r2, true);
    }

    private /* synthetic */ void lambda$onUpdateData$3() {
        setErrorState(true);
    }

    private /* synthetic */ void lambda$onUpdateData$4(Weather.State r4) {
        if (r4 == null) goto L6;
        markDataUpdated();
        postDelayed(new WeatherPill$$ExternalSyntheticLambda4(this, r4), 300);
        return;
    L6:
        postDelayed(new WeatherPill$$ExternalSyntheticLambda5(this), 300);
    }

    private /* synthetic */ void lambda$requestLocationAndUpdate$5(Location r1) {
        if (r1 == null) goto L5;
        onUpdateData(true);
        return;
    }

    private void requestLocationAndUpdate() {
        Weather.getUserLocation(true, new WeatherPill$$ExternalSyntheticLambda2(this));
    }

    private void setErrorState(boolean r4) {
        stopLoading();
        if (r4 == false) goto L5;
        animateSizeChange();
    L5:
        this.iconView.setImageResource(R.drawable.msg_retry);
        this.iconView.setVisibility(0);
        this.textView.setText(LocaleController.getString(R.string.Retry), r4);
        this.showingWeather = false;
    }

    private void setLocationState(int r3, boolean r4) {
        stopLoading();
        if (r4 == false) goto L5;
        animateSizeChange();
    L5:
        this.iconView.setImageResource(R.drawable.filled_location);
        this.iconView.setVisibility(0);
        this.textView.setText(LocaleController.getString(r3), r4);
        this.showingWeather = false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.pillStackSettingsChanged) goto L5;
        return;
    L5:
        if (PillStackConfig.shouldUpdatePill(r3, new int[]{getPillId()}) == false) goto L10;
        PillStackConfig.checkAndClearPendingUpdate(getPillId());
        onUpdateData(true);
        return;
    }

    @Override // android.view.View
    public void drawableHotspotChanged(float r3, float r4) {
        if (this.loading == false) goto L5;
        return;
    L5:
        super.drawableHotspotChanged(r3, r4);
        this.layout.drawableHotspotChanged(r3 - r0.getLeft(), r4 - this.layout.getTop());
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public int getPillId() {
        return PillType.WEATHER.getId();
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public long getRefreshInterval() {
        return 1200000;
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.pillStackSettingsChanged);
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.pillStackSettingsChanged);
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public void onPillClicked() {
        if (PillStackConfig.getUseCurrentLocation() == true) goto L5;
    L12:
        onPillLongClicked();
        return;
    L5:
        if (this.showingWeather == true) goto L12;
        if (Weather.isLocationPermissionGranted() == true) goto L9;
    L10:
        requestLocationAndUpdate();
        return;
    L9:
        if (Weather.isLocationEnabled() == true) goto L12;
        goto L10
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public boolean onPillLongClicked() {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L6;
        ItemOptions.makeOptions(r0, this).add(R.drawable.msg_retry, LocaleController.getString(R.string.Refresh), new WeatherPill$$ExternalSyntheticLambda0(this)).add(R.drawable.msg_settings, LocaleController.getString(R.string.Settings), new WeatherPill$$ExternalSyntheticLambda1(r0)).setDrawScrim(false).setDimAlpha(0).show();
        return true;
    L6:
        return false;
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public void onUpdateData(boolean r2) {
        if (PillStackConfig.getUseCurrentLocation() == true) goto L5;
    L12:
        if (r2 == false) goto L14;
        Weather.clearCache();
    L14:
        startLoading();
        Weather.fetchExtera(new WeatherPill$$ExternalSyntheticLambda3(this));
        return;
    L5:
        if (Weather.isLocationPermissionGranted() == true) goto L9;
        setLocationState(R.string.WeatherLocationPermissionGrant, this.showingWeather);
        return;
    L9:
        if (Weather.isLocationEnabled() == true) goto L12;
        setLocationState(R.string.WeatherLocationServicesEnable, this.showingWeather);
    }

    public void setData(Weather.State r4, boolean r5) {
        stopLoading();
        if (r4 != null) goto L5;
        return;
    L5:
        if (r5 == false) goto L7;
        animateSizeChange();
    L7:
        int r0 = getWeatherIconRes(r4.getEmoji());
        ImageView r1 = this.iconView;
        if (r0 == 0) goto L10;
        r1.setImageResource(r0);
        this.iconView.setVisibility(0);
        this.textView.setText(r4.getTemperature(), r5);
    L11:
        this.showingWeather = true;
        return;
    L10:
        r1.setVisibility(8);
        this.textView.setText(Emoji.replaceEmoji(String.format("%s %s", new Object[]{r4.getEmoji(), r4.getTemperature()}), this.textView.getPaint().getFontMetricsInt(), true), r5);
        goto L11
    }

    @Override // android.view.View
    public void setPressed(boolean r2) {
        if (this.loading == false) goto L5;
        r2 = false;
    L5:
        super.setPressed(r2);
        this.layout.setPressed(r2);
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public void updateColors() {
        int r0 = getThemedColor(Theme.key_windowBackgroundWhiteBlackText, 0.75f);
        if (Theme.isCurrentThemeDark() == false) goto L5;
        int r1 = getThemedColor(Theme.key_windowBackgroundWhite);
    L6:
        this.layout.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(14.0f), r1, Theme.multAlpha(r0, 0.1f)));
        this.textView.setTextColor(r0);
        this.iconView.setColorFilter(new PorterDuffColorFilter(r0, PorterDuff.Mode.MULTIPLY));
        updateLoadingColors();
        return;
    L5:
        r1 = Theme.multAlpha(r0, 0.09f);
        goto L6
    }
}
