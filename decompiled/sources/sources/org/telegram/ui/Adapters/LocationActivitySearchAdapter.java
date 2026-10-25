package org.telegram.ui.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.LocationCell;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public abstract class LocationActivitySearchAdapter extends BaseLocationAdapter {
    private FlickerLoadingView globalGradientView;
    private Context mContext;
    private boolean myLocationDenied;
    private Theme.ResourcesProvider resourcesProvider;

    public LocationActivitySearchAdapter(Context r1, Theme.ResourcesProvider r2, boolean r3, boolean r4) {
        super(r3, r4);
        this.myLocationDenied = false;
        this.mContext = r1;
        this.resourcesProvider = r2;
        FlickerLoadingView r22 = new FlickerLoadingView(r1);
        this.globalGradientView = r22;
        r22.setIsSingleCell(true);
    }

    public TLRPC.TL_messageMediaVenue getItem(int r2) {
        if (this.locations.isEmpty() == true) goto L5;
        r2 = r2 - 1;
    L5:
        if (r2 < 0) goto L11;
        if (r2 >= this.locations.size()) goto L11;
        return this.locations.get(r2);
    L11:
        if (isSearching() == true) goto L20;
        int r22 = r2 - this.locations.size();
        if (this.locations.isEmpty() == true) goto L15;
        r22 = r22 - 1;
    L15:
        if (r22 >= 0) goto L17;
        return null;
    L17:
        if (r22 < this.places.size()) goto L19;
        return null;
    L19:
        return this.places.get(r22);
    L20:
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.locations.isEmpty() == true) goto L5;
        int r0 = this.locations.size() + 1;
    L7:
        if (this.myLocationDenied == false) goto L9;
        return r0;
    L9:
        if (isSearching() == false) goto L13;
        return r0 + 3;
    L13:
        if (this.locations.isEmpty() == true) goto L18;
        if (this.places.isEmpty() == true) goto L18;
        r0 = r0 + 1;
    L18:
        return r0 + this.places.size();
    L5:
        r0 = 0;
        goto L7
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int r3) {
        if (r3 == 0) goto L7;
        if (r3 == (this.locations.size() + 1)) goto L7;
        return 0;
    L7:
        if (this.locations.isEmpty() == true) goto L11;
        return 1;
    L11:
        return 0;
    }

    public boolean isEmpty() {
        if (this.places.size() == 0) goto L5;
        return false;
    L5:
        if (this.locations.size() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r1) {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
        boolean r1 = true;
        if (r5.getItemViewType() != 0) goto L38;
        if (this.locations.isEmpty() == true) goto L7;
        int r0 = r6 - 1;
    L8:
        if (r0 < 0) goto L13;
        if (r0 >= this.locations.size()) goto L13;
        TLRPC.TL_messageMediaVenue r02 = this.locations.get(r0);
        int r2 = 2;
    L25:
        LocationCell r52 = (LocationCell) r5.itemView;
        if (r6 != (getItemCount() - 1)) goto L28;
    L34:
        r1 = false;
    L35:
        r52.setLocation(r02, r2, r1);
        return;
    L28:
        if (this.searchingLocations == true) goto L35;
        if (this.locations.isEmpty() == true) goto L35;
        if (r6 == this.locations.size()) goto L34;
    L13:
        if (isSearching() == true) goto L24;
        int r03 = r0 - this.locations.size();
        if (this.searchingLocations == false) goto L17;
    L19:
        r2 = r03;
        if (r2 < 0) goto L24;
        if (r2 >= this.places.size()) goto L24;
        r02 = this.places.get(r2);
        goto L25
    L17:
        if (this.locations.isEmpty() == true) goto L19;
        r03 = r03 - 1;
    L24:
        r02 = null;
        r2 = r6;
        goto L25
    L7:
        r0 = r6;
        goto L8
    L38:
        if (r5.getItemViewType() != 1) goto L46;
        if (r6 == 0) goto L41;
    L44:
        ((GraySectionCell) r5.itemView).setText(LocaleController.getString(R.string.NearbyVenue));
        return;
    L41:
        if (this.locations.isEmpty() == true) goto L44;
        ((GraySectionCell) r5.itemView).setText(LocaleController.getString(R.string.LocationOnMap));
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
        if (r3 != 0) goto L4;
        View r22 = new LocationCell(this.mContext, false, this.resourcesProvider);
    L6:
        return new RecyclerListView.Holder(r22);
    L4:
        r22 = new GraySectionCell(this.mContext, this.resourcesProvider);
        goto L6
    }

    public void setMyLocationDenied(boolean r2) {
        if (this.myLocationDenied != r2) goto L5;
        return;
    L5:
        this.myLocationDenied = r2;
    }
}
