package androidx.mediarouter.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.mediarouter.R;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import c.f$$ExternalSyntheticBUOutline1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class MediaRouteChooserDialog extends AppCompatDialog {
    private static final int FINDING_DEVICES = 0;
    private static final int MSG_SHOW_NO_ROUTES = 3;
    private static final int MSG_SHOW_WIFI_HINT = 2;
    private static final int MSG_UPDATE_ROUTES = 1;
    private static final int NO_DEVICES_NO_WIFI_HINT = 2;
    private static final int NO_ROUTES = 3;
    private static final int SHOWING_ROUTES = 1;
    private static final int SHOW_NO_ROUTES_DELAY_MS = 15000;
    private static final int SHOW_WIFI_HINT_DELAY_MS = 5000;
    static final String TAG = "MediaRouteChooserDialog";
    private static final long UPDATE_ROUTES_DELAY_MS = 300;
    private RouteAdapter mAdapter;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private final Handler mHandler;
    private long mLastUpdateTime;
    private TextView mLearnMoreTextView;
    private ListView mListView;
    private Button mOkButton;
    private LinearLayout mOkButtonContainer;
    private final MediaRouter mRouter;
    private ArrayList<MediaRouter.RouteInfo> mRoutes;
    private ScreenOnOffReceiver mScreenOnOffReceiver;
    private ProgressBar mSearchingProgressBar;
    private TextView mSearchingRoutesTextView;
    private MediaRouteSelector mSelector;
    private TextView mTitleView;
    private RelativeLayout mWifiWarningContainer;
    private TextView mWifiWarningTextView;

    /* renamed from: androidx.mediarouter.app.MediaRouteChooserDialog$1, reason: invalid class name */
    public class AnonymousClass1 extends Handler {
        final /* synthetic */ MediaRouteChooserDialog this$0;

        public AnonymousClass1(MediaRouteChooserDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message r3) {
            int r0 = r3.what;
            if (r0 != 1) goto L5;
            this.this$0.handleUpdateRoutes((List) r3.obj);
            return;
        L5:
            if (r0 != 2) goto L7;
            this.this$0.handleShowNoWifiWarning();
            return;
        L7:
            if (r0 == 3) goto L9;
            return;
        L9:
            this.this$0.handleShowNoRoutes();
        }
    }

    public final class MediaRouterCallback extends MediaRouter.Callback {
        final /* synthetic */ MediaRouteChooserDialog this$0;

        public MediaRouterCallback(MediaRouteChooserDialog r1) {
            this.this$0 = r1;
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteAdded(MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.this$0.refreshRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteChanged(MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.this$0.refreshRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteRemoved(MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.this$0.refreshRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteSelected(MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.this$0.dismiss();
        }
    }

    public static final class RouteAdapter extends ArrayAdapter<MediaRouter.RouteInfo> implements AdapterView.OnItemClickListener {
        private final Drawable mDefaultIcon;
        private final LayoutInflater mInflater;
        private final Drawable mSpeakerGroupIcon;
        private final Drawable mSpeakerIcon;
        private final Drawable mTvIcon;

        public RouteAdapter(Context r6, List<MediaRouter.RouteInfo> r7) {
            super(r6, 0, r7);
            this.mInflater = LayoutInflater.from(r6);
            TypedArray r72 = getContext().obtainStyledAttributes(new int[]{R.attr.mediaRouteDefaultIconDrawable, R.attr.mediaRouteTvIconDrawable, R.attr.mediaRouteSpeakerIconDrawable, R.attr.mediaRouteSpeakerGroupIconDrawable});
            this.mDefaultIcon = AppCompatResources.getDrawable(r6, r72.getResourceId(0, 0));
            this.mTvIcon = AppCompatResources.getDrawable(r6, r72.getResourceId(1, 0));
            this.mSpeakerIcon = AppCompatResources.getDrawable(r6, r72.getResourceId(2, 0));
            this.mSpeakerGroupIcon = AppCompatResources.getDrawable(r6, r72.getResourceId(3, 0));
            r72.recycle();
        }

        private Drawable getDefaultIconDrawable(MediaRouter.RouteInfo r3) {
            int r0 = r3.getDeviceType();
            if (r0 == 1) goto L15;
            if (r0 == 2) goto L13;
            if (r3.isGroup() == false) goto L11;
            return this.mSpeakerGroupIcon;
        L11:
            return this.mDefaultIcon;
        L13:
            return this.mSpeakerIcon;
        L15:
            return this.mTvIcon;
        }

        private Drawable getIconDrawable(MediaRouter.RouteInfo r5) {
            Uri r0 = r5.getIconUri();
            if (r0 == null) goto L10;
            Drawable r02 = Drawable.createFromStream(getContext().getContentResolver().openInputStream(r0), null);     // Catch: IOException -> L7
            if (r02 == null) goto L10;
            return r02;
        L7:
            e = move-exception;
            Log.w(MediaRouteChooserDialog.TAG, "Failed to load " + r0, e);
        L10:
            return getDefaultIconDrawable(r5);
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int r6, View r7, ViewGroup r8) {
            if (r7 != null) goto L5;
            r7 = this.mInflater.inflate(R.layout.mr_chooser_list_item, r8, false);
        L5:
            MediaRouter.RouteInfo r62 = (MediaRouter.RouteInfo) getItem(r6);
            TextView r82 = (TextView) r7.findViewById(R.id.mr_chooser_route_name);
            TextView r1 = (TextView) r7.findViewById(R.id.mr_chooser_route_desc);
            r82.setText(r62.getName());
            String r2 = r62.getDescription();
            if (r62.getConnectionState() == 2) goto L10;
            if (r62.getConnectionState() == 1) goto L10;
        L12:
            r82.setGravity(16);
            r1.setVisibility(8);
            r1.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        L13:
            r7.setEnabled(r62.isEnabled());
            ImageView r83 = (ImageView) r7.findViewById(R.id.mr_chooser_route_icon);
            if (r83 == null) goto L16;
            r83.setImageDrawable(getIconDrawable(r62));
        L16:
            return r7;
        L10:
            if (TextUtils.isEmpty(r2) == true) goto L12;
            r82.setGravity(80);
            r1.setVisibility(0);
            r1.setText(r2);
            goto L13
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int r1) {
            return ((MediaRouter.RouteInfo) getItem(r1)).isEnabled();
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> r1, View r2, int r3, long r4) {
            MediaRouter.RouteInfo r0 = (MediaRouter.RouteInfo) getItem(r3);
            ImageView r12 = (ImageView) r2.findViewById(R.id.mr_chooser_route_icon);
            ProgressBar r22 = (ProgressBar) r2.findViewById(R.id.mr_chooser_route_progress_bar);
            if (r12 == null) goto L6;
            if (r22 == null) goto L6;
            r12.setVisibility(8);
            r22.setVisibility(0);
        L6:
            r0.select();
        }
    }

    public static final class RouteComparator implements Comparator<MediaRouter.RouteInfo> {
        public static final RouteComparator sInstance = null;

        static {
            sInstance = new RouteComparator();
        }

        public RouteComparator() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(MediaRouter.RouteInfo r1, MediaRouter.RouteInfo r2) {
            return r1.getName().compareToIgnoreCase(r2.getName());
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(MediaRouter.RouteInfo r1, MediaRouter.RouteInfo r2) {
            return compare2(r1, r2);
        }
    }

    public final class ScreenOnOffReceiver extends BroadcastReceiver {
        final /* synthetic */ MediaRouteChooserDialog this$0;

        public ScreenOnOffReceiver(MediaRouteChooserDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r1, Intent r2) {
            if ("android.intent.action.SCREEN_OFF".equals(r2.getAction()) == false) goto L6;
            this.this$0.dismiss();
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$FjlYNvn35wzmUucGiMF1CvFxETU(MediaRouteChooserDialog r0, View r1) {
        r0.dismiss();
    }

    public MediaRouteChooserDialog(Context r2) {
        this(r2, 0);
    }

    public MediaRouteChooserDialog(Context r2, int r3) {
        Context r22 = MediaRouterThemeHelper.createThemedDialogContext(r2, r3, false);
        super(r22, MediaRouterThemeHelper.createThemedDialogStyle(r22));
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mHandler = new AnonymousClass1(this);
        this.mRouter = MediaRouter.getInstance(getContext());
        this.mCallback = new MediaRouterCallback(this);
        this.mScreenOnOffReceiver = new ScreenOnOffReceiver(this);
    }

    private void registerBroadcastReceiver() {
        IntentFilter r0 = new IntentFilter("android.intent.action.SCREEN_OFF");
        getContext().registerReceiver(this.mScreenOnOffReceiver, r0);
    }

    private void unregisterBroadcastReceiver() {
        getContext().unregisterReceiver(this.mScreenOnOffReceiver);     // Catch: IllegalArgumentException -> L4
        return;
    }

    private void updateViewForFindingDevices() {
        setTitle(R.string.mr_chooser_title);
        this.mListView.setVisibility(8);
        this.mSearchingRoutesTextView.setVisibility(0);
        this.mSearchingProgressBar.setVisibility(0);
        this.mOkButtonContainer.setVisibility(8);
        this.mOkButton.setVisibility(8);
        this.mLearnMoreTextView.setVisibility(8);
        this.mWifiWarningContainer.setVisibility(8);
    }

    private void updateViewForNoDevicesNoWifiHint() {
        setTitle(R.string.mr_chooser_title);
        this.mListView.setVisibility(8);
        this.mSearchingRoutesTextView.setVisibility(8);
        this.mSearchingProgressBar.setVisibility(0);
        this.mOkButtonContainer.setVisibility(8);
        this.mOkButton.setVisibility(8);
        this.mLearnMoreTextView.setVisibility(4);
        this.mWifiWarningContainer.setVisibility(0);
    }

    private void updateViewForNoRoutes() {
        setTitle(R.string.mr_chooser_zero_routes_found_title);
        this.mListView.setVisibility(8);
        this.mSearchingRoutesTextView.setVisibility(8);
        this.mSearchingProgressBar.setVisibility(8);
        this.mOkButtonContainer.setVisibility(0);
        this.mOkButton.setVisibility(0);
        this.mLearnMoreTextView.setVisibility(0);
        this.mWifiWarningContainer.setVisibility(0);
    }

    private void updateViewForShowingRoutes() {
        setTitle(R.string.mr_chooser_title);
        this.mListView.setVisibility(0);
        this.mSearchingRoutesTextView.setVisibility(8);
        this.mSearchingProgressBar.setVisibility(8);
        this.mOkButtonContainer.setVisibility(8);
        this.mOkButton.setVisibility(8);
        this.mLearnMoreTextView.setVisibility(8);
        this.mWifiWarningContainer.setVisibility(8);
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        unregisterBroadcastReceiver();
        super.dismiss();
    }

    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }

    public void handleShowNoRoutes() {
        if (this.mRoutes.isEmpty() == false) goto L6;
        updateViewForState(3);
        this.mHandler.removeMessages(2);
        this.mHandler.removeMessages(3);
        this.mHandler.removeMessages(1);
        this.mRouter.removeCallback(this.mCallback);
        return;
    }

    public void handleShowNoWifiWarning() {
        if (this.mRoutes.isEmpty() == false) goto L6;
        updateViewForState(2);
        this.mHandler.removeMessages(2);
        this.mHandler.removeMessages(3);
        Handler r3 = this.mHandler;
        r3.sendMessageDelayed(r3.obtainMessage(3), 15000);
        return;
    }

    public void handleUpdateRoutes(List<MediaRouter.RouteInfo> r3) {
        this.mLastUpdateTime = SystemClock.uptimeMillis();
        this.mRoutes.clear();
        this.mRoutes.addAll(r3);
        this.mAdapter.notifyDataSetChanged();
        this.mHandler.removeMessages(3);
        this.mHandler.removeMessages(2);
        if (r3.isEmpty() == false) goto L6;
        updateViewForState(0);
        Handler r2 = this.mHandler;
        r2.sendMessageDelayed(r2.obtainMessage(2), 5000);
        return;
    L6:
        updateViewForState(1);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.mRouter.addCallback(this.mSelector, this.mCallback, 1);
        refreshRoutes();
        this.mHandler.removeMessages(2);
        this.mHandler.removeMessages(3);
        this.mHandler.removeMessages(1);
        Handler r4 = this.mHandler;
        r4.sendMessageDelayed(r4.obtainMessage(2), 5000);
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(Bundle r3) {
        super.onCreate(r3);
        setContentView(R.layout.mr_chooser_dialog);
        this.mRoutes = new ArrayList();
        this.mAdapter = new RouteAdapter(getContext(), this.mRoutes);
        this.mTitleView = (TextView) findViewById(R.id.mr_chooser_title);
        this.mSearchingRoutesTextView = (TextView) findViewById(R.id.mr_chooser_searching);
        this.mWifiWarningContainer = (RelativeLayout) findViewById(R.id.mr_chooser_wifi_warning_container);
        this.mWifiWarningTextView = (TextView) findViewById(R.id.mr_chooser_wifi_warning_description);
        this.mLearnMoreTextView = (TextView) findViewById(R.id.mr_chooser_wifi_learn_more);
        this.mOkButtonContainer = (LinearLayout) findViewById(R.id.mr_chooser_ok_button_container);
        this.mOkButton = (Button) findViewById(R.id.mr_chooser_ok_button);
        this.mSearchingProgressBar = (ProgressBar) findViewById(R.id.mr_chooser_search_progress_bar);
        String r32 = DeviceUtils.getDialogChooserWifiWarningDescription(getContext());
        this.mWifiWarningTextView.setText(r32);
        this.mLearnMoreTextView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mOkButton.setOnClickListener(new MediaRouteChooserDialog$$ExternalSyntheticLambda0(this));
        ListView r33 = (ListView) findViewById(R.id.mr_chooser_list);
        this.mListView = r33;
        r33.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(this.mAdapter);
        this.mListView.setEmptyView(findViewById(android.R.id.empty));
        updateLayout();
        registerBroadcastReceiver();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.mAttachedToWindow = false;
        this.mRouter.removeCallback(this.mCallback);
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
        this.mHandler.removeMessages(3);
        super.onDetachedFromWindow();
    }

    public boolean onFilterRoute(MediaRouter.RouteInfo r2) {
        if (r2.isDefaultOrBluetooth() == false) goto L5;
        return false;
    L5:
        if (r2.isEnabled() == true) goto L7;
        return false;
    L7:
        if (r2.matchesSelector(this.mSelector) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public void onFilterRoutes(List<MediaRouter.RouteInfo> r3) {
        int r0 = r3.size();
    L3:
        int r1 = r0 - 1;
        if (r0 <= 0) goto L9;
        if (onFilterRoute(r3.get(r1)) == true) goto L8;
        r3.remove(r1);
    L8:
        r0 = r1;
        goto L3
    }

    public void refreshRoutes() {
        if (this.mAttachedToWindow == false) goto L10;
        ArrayList r0 = new ArrayList(this.mRouter.getRoutes());
        onFilterRoutes(r0);
        Collections.sort(r0, RouteComparator.sInstance);
        if ((SystemClock.uptimeMillis() - this.mLastUpdateTime) < UPDATE_ROUTES_DELAY_MS) goto L8;
        handleUpdateRoutes(r0);
        return;
    L8:
        this.mHandler.removeMessages(1);
        Handler r1 = this.mHandler;
        r1.sendMessageAtTime(r1.obtainMessage(1, r0), this.mLastUpdateTime + UPDATE_ROUTES_DELAY_MS);
        return;
    }

    public void setRouteSelector(MediaRouteSelector r4) {
        if (r4 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("selector must not be null");
        return;
    L4:
        if (this.mSelector.equals(r4) == true) goto L12;
        this.mSelector = r4;
        if (this.mAttachedToWindow == false) goto L8;
        this.mRouter.removeCallback(this.mCallback);
        this.mRouter.addCallback(r4, this.mCallback, 1);
    L8:
        refreshRoutes();
        return;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setTitle(int r1) {
        this.mTitleView.setText(r1);
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setTitle(CharSequence r1) {
        this.mTitleView.setText(r1);
    }

    public void updateLayout() {
        getWindow().setLayout(MediaRouteDialogHelper.getDialogWidth(getContext()), -2);
    }

    public void updateViewForState(int r2) {
        if (r2 != 0) goto L4;
        updateViewForFindingDevices();
        return;
    L4:
        if (r2 != 1) goto L6;
        updateViewForShowingRoutes();
        return;
    L6:
        if (r2 != 2) goto L8;
        updateViewForNoDevicesNoWifiHint();
        return;
    L8:
        if (r2 == 3) goto L10;
        return;
    L10:
        updateViewForNoRoutes();
    }
}
