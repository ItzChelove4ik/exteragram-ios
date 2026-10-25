package com.google.android.material.navigation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.SubMenuBuilder;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.internal.ParcelableSparseArray;

/* loaded from: classes5.dex */
public class NavigationBarPresenter implements MenuPresenter {
    private int id;
    private NavigationBarMenuView menuView;
    private boolean updateSuspended;

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        ParcelableSparseArray badgeSavedStates;
        int selectedItemId;

        /* renamed from: com.google.android.material.navigation.NavigationBarPresenter$SavedState$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.Creator<SavedState> {
            public AnonymousClass1() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel r1) {
                return new SavedState(r1);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SavedState createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int r1) {
                return new SavedState[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SavedState[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public SavedState() {
        }

        public SavedState(Parcel r2) {
            this.selectedItemId = r2.readInt();
            this.badgeSavedStates = (ParcelableSparseArray) r2.readParcelable(getClass().getClassLoader());
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            r1.writeInt(this.selectedItemId);
            r1.writeParcelable(this.badgeSavedStates, 0);
        }
    }

    public NavigationBarPresenter() {
        this.updateSuspended = false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder r1, MenuItemImpl r2) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder r1, MenuItemImpl r2) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return this.id;
    }

    public MenuView getMenuView(ViewGroup r1) {
        return this.menuView;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(Context r1, MenuBuilder r2) {
        this.menuView.initialize(r2);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder r1, boolean r2) {
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable r3) {
        if ((r3 instanceof SavedState) == false) goto L6;
        SavedState r32 = (SavedState) r3;
        this.menuView.tryRestoreSelectedItemId(r32.selectedItemId);
        SparseArray<BadgeDrawable> r33 = BadgeUtils.createBadgeDrawablesFromSavedStates(this.menuView.getContext(), r32.badgeSavedStates);
        this.menuView.restoreBadgeDrawables(r33);
        return;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        SavedState r0 = new SavedState();
        r0.selectedItemId = this.menuView.getSelectedItemId();
        r0.badgeSavedStates = BadgeUtils.createParcelableBadgeStates(this.menuView.getBadgeDrawables());
        return r0;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder r1) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback r1) {
    }

    public void setId(int r1) {
        this.id = r1;
    }

    public void setMenuView(NavigationBarMenuView r1) {
        this.menuView = r1;
    }

    public void setUpdateSuspended(boolean r1) {
        this.updateSuspended = r1;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean r2) {
        if (this.updateSuspended == false) goto L5;
        return;
    L5:
        NavigationBarMenuView r1 = this.menuView;
        if (r2 == false) goto L9;
        r1.buildMenuView();
        return;
    L9:
        r1.updateMenuView();
    }
}
