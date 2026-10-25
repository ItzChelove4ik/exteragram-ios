package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.mediarouter.R;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c.f$$ExternalSyntheticBUOutline1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class MediaRouteDynamicChooserDialog extends AppCompatDialog {
    private static final int ITEM_TYPE_HEADER = 1;
    private static final int ITEM_TYPE_ROUTE = 2;
    private static final int MSG_UPDATE_ROUTES = 1;
    private RecyclerAdapter mAdapter;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private ImageButton mCloseButton;
    Context mContext;
    private final Handler mHandler;
    private long mLastUpdateTime;
    private RecyclerView mRecyclerView;
    final MediaRouter mRouter;
    List<MediaRouter.RouteInfo> mRoutes;
    MediaRouter.RouteInfo mSelectingRoute;
    private MediaRouteSelector mSelector;
    private long mUpdateRoutesDelayMs;

    /* renamed from: androidx.mediarouter.app.MediaRouteDynamicChooserDialog$1, reason: invalid class name */
    public class AnonymousClass1 extends Handler {
        final /* synthetic */ MediaRouteDynamicChooserDialog this$0;

        public AnonymousClass1(MediaRouteDynamicChooserDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message r3) {
            if (r3.what == 1) goto L5;
            return;
        L5:
            this.this$0.updateRoutes((List) r3.obj);
        }
    }

    /* renamed from: androidx.mediarouter.app.MediaRouteDynamicChooserDialog$2, reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ MediaRouteDynamicChooserDialog this$0;

        public AnonymousClass2(MediaRouteDynamicChooserDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r1) {
            this.this$0.dismiss();
        }
    }

    public final class MediaRouterCallback extends MediaRouter.Callback {
        final /* synthetic */ MediaRouteDynamicChooserDialog this$0;

        public MediaRouterCallback(MediaRouteDynamicChooserDialog r1) {
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

    public final class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final String TAG = "RecyclerAdapter";
        private final Drawable mDefaultIcon;
        private final LayoutInflater mInflater;
        private final ArrayList<Item> mItems;
        private final Drawable mSpeakerGroupIcon;
        private final Drawable mSpeakerIcon;
        private final Drawable mTvIcon;
        final /* synthetic */ MediaRouteDynamicChooserDialog this$0;

        public class HeaderViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;
            final /* synthetic */ RecyclerAdapter this$1;

            public HeaderViewHolder(RecyclerAdapter r1, View r2) {
                this.this$1 = r1;
                super(r2);
                this.mTextView = (TextView) r2.findViewById(R.id.mr_picker_header_name);
            }

            public void bindHeaderView(Item r1) {
                String r12 = r1.getData().toString();
                this.mTextView.setText(r12);
            }
        }

        public class Item {
            private final Object mData;
            private final int mType;
            final /* synthetic */ RecyclerAdapter this$1;

            public Item(RecyclerAdapter r1, Object r2) {
                this.this$1 = r1;
                this.mData = r2;
                if ((r2 instanceof String) == false) goto L7;
                this.mType = 1;
                return;
            L7:
                if ((r2 instanceof MediaRouter.RouteInfo) == false) goto L10;
                this.mType = 2;
                return;
            L10:
                Segment$$ExternalSyntheticBUOutline0.m();
                throw null;
            }

            public Object getData() {
                return this.mData;
            }

            public int getType() {
                return this.mType;
            }
        }

        public class RouteViewHolder extends RecyclerView.ViewHolder {
            final ImageView mImageView;
            final View mItemView;
            final ProgressBar mProgressBar;
            final TextView mTextView;
            final /* synthetic */ RecyclerAdapter this$1;

            /* renamed from: androidx.mediarouter.app.MediaRouteDynamicChooserDialog$RecyclerAdapter$RouteViewHolder$1, reason: invalid class name */
            public class AnonymousClass1 implements View.OnClickListener {
                final /* synthetic */ RouteViewHolder this$2;
                final /* synthetic */ MediaRouter.RouteInfo val$route;

                public AnonymousClass1(RouteViewHolder r1, MediaRouter.RouteInfo r2) {
                    this.this$2 = r1;
                    this.val$route = r2;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View r2) {
                    MediaRouteDynamicChooserDialog r22 = this.this$2.this$1.this$0;
                    MediaRouter.RouteInfo r0 = this.val$route;
                    r22.mSelectingRoute = r0;
                    r0.select();
                    this.this$2.mImageView.setVisibility(4);
                    this.this$2.mProgressBar.setVisibility(0);
                }
            }

            public RouteViewHolder(RecyclerAdapter r3, View r4) {
                this.this$1 = r3;
                super(r4);
                this.mItemView = r4;
                this.mImageView = (ImageView) r4.findViewById(R.id.mr_picker_route_icon);
                ProgressBar r0 = (ProgressBar) r4.findViewById(R.id.mr_picker_route_progress_bar);
                this.mProgressBar = r0;
                this.mTextView = (TextView) r4.findViewById(R.id.mr_picker_route_name);
                MediaRouterThemeHelper.setIndeterminateProgressBarColor(r3.this$0.mContext, r0);
            }

            public void bindRouteView(Item r3) {
                MediaRouter.RouteInfo r32 = (MediaRouter.RouteInfo) r3.getData();
                this.mItemView.setVisibility(0);
                this.mProgressBar.setVisibility(4);
                this.mItemView.setOnClickListener(new AnonymousClass1(this, r32));
                this.mTextView.setText(r32.getName());
                this.mImageView.setImageDrawable(this.this$1.getIconDrawable(r32));
            }
        }

        public RecyclerAdapter(MediaRouteDynamicChooserDialog r2) {
            this.this$0 = r2;
            this.mItems = new ArrayList();
            this.mInflater = LayoutInflater.from(r2.mContext);
            this.mDefaultIcon = MediaRouterThemeHelper.getDefaultDrawableIcon(r2.mContext);
            this.mTvIcon = MediaRouterThemeHelper.getTvDrawableIcon(r2.mContext);
            this.mSpeakerIcon = MediaRouterThemeHelper.getSpeakerDrawableIcon(r2.mContext);
            this.mSpeakerGroupIcon = MediaRouterThemeHelper.getSpeakerGroupDrawableIcon(r2.mContext);
            rebuildItems();
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

        public Drawable getIconDrawable(MediaRouter.RouteInfo r5) {
            Uri r0 = r5.getIconUri();
            if (r0 == null) goto L10;
            Drawable r02 = Drawable.createFromStream(this.this$0.mContext.getContentResolver().openInputStream(r0), null);     // Catch: IOException -> L7
            if (r02 == null) goto L10;
            return r02;
        L7:
            e = move-exception;
            Log.w(TAG, "Failed to load " + r0, e);
        L10:
            return getDefaultIconDrawable(r5);
        }

        public Item getItem(int r1) {
            return this.mItems.get(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mItems.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return this.mItems.get(r1).getType();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r2, int r3) {
            int r0 = getItemViewType(r3);
            Item r1 = getItem(r3);
            if (r0 != 1) goto L5;
            ((HeaderViewHolder) r2).bindHeaderView(r1);
            return;
        L5:
            if (r0 == 2) goto L8;
            Log.w(TAG, "Cannot bind item to ViewHolder because of wrong view type");
            return;
        L8:
            ((RouteViewHolder) r2).bindRouteView(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 == 1) goto L11;
            if (r4 == 2) goto L7;
            MethodWriter$$ExternalSyntheticBUOutline0.m();
            return null;
        L7:
            return new RouteViewHolder(this, this.mInflater.inflate(R.layout.mr_picker_route_item, r3, false));
        L11:
            return new HeaderViewHolder(this, this.mInflater.inflate(R.layout.mr_picker_header_item, r3, false));
        }

        public void rebuildItems() {
            this.mItems.clear();
            this.mItems.add(new Item(this, this.this$0.mContext.getString(R.string.mr_chooser_title)));
            Iterator<MediaRouter.RouteInfo> r0 = this.this$0.mRoutes.iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            MediaRouter.RouteInfo r1 = r0.next();
            this.mItems.add(new Item(this, r1));
            goto L4
        L6:
            notifyDataSetChanged();
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

    public MediaRouteDynamicChooserDialog(Context r2) {
        this(r2, 0);
    }

    public MediaRouteDynamicChooserDialog(Context r2, int r3) {
        Context r22 = MediaRouterThemeHelper.createThemedDialogContext(r2, r3, false);
        super(r22, MediaRouterThemeHelper.createThemedDialogStyle(r22));
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mHandler = new AnonymousClass1(this);
        Context r23 = getContext();
        this.mRouter = MediaRouter.getInstance(r23);
        this.mCallback = new MediaRouterCallback(this);
        this.mContext = r23;
        this.mUpdateRoutesDelayMs = r23.getResources().getInteger(R.integer.mr_update_routes_delay_ms);
    }

    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.mRouter.addCallback(this.mSelector, this.mCallback, 1);
        refreshRoutes();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(Bundle r3) {
        super.onCreate(r3);
        setContentView(R.layout.mr_picker_dialog);
        MediaRouterThemeHelper.setDialogBackgroundColor(this.mContext, this);
        this.mRoutes = new ArrayList();
        ImageButton r32 = (ImageButton) findViewById(R.id.mr_picker_close_button);
        this.mCloseButton = r32;
        r32.setOnClickListener(new AnonymousClass2(this));
        this.mAdapter = new RecyclerAdapter(this);
        RecyclerView r33 = (RecyclerView) findViewById(R.id.mr_picker_list);
        this.mRecyclerView = r33;
        r33.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        updateLayout();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachedToWindow = false;
        this.mRouter.removeCallback(this.mCallback);
        this.mHandler.removeMessages(1);
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
        if (this.mSelectingRoute == null) goto L6;
        return;
    L6:
        if (this.mAttachedToWindow == false) goto L13;
        ArrayList r0 = new ArrayList(this.mRouter.getRoutes());
        onFilterRoutes(r0);
        Collections.sort(r0, RouteComparator.sInstance);
        if ((SystemClock.uptimeMillis() - this.mLastUpdateTime) < this.mUpdateRoutesDelayMs) goto L11;
        updateRoutes(r0);
        return;
    L11:
        this.mHandler.removeMessages(1);
        Handler r1 = this.mHandler;
        r1.sendMessageAtTime(r1.obtainMessage(1, r0), this.mLastUpdateTime + this.mUpdateRoutesDelayMs);
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

    public void updateLayout() {
        int r0 = MediaRouteDialogHelper.getDialogWidthForDynamicGroup(this.mContext);
        int r1 = MediaRouteDialogHelper.getDialogHeight(this.mContext);
        getWindow().setLayout(r0, r1);
    }

    public void updateRoutes(List<MediaRouter.RouteInfo> r3) {
        this.mLastUpdateTime = SystemClock.uptimeMillis();
        this.mRoutes.clear();
        this.mRoutes.addAll(r3);
        this.mAdapter.rebuildItems();
    }
}
