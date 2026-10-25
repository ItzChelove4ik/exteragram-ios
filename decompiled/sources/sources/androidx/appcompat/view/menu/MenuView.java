package androidx.appcompat.view.menu;

/* loaded from: classes3.dex */
public interface MenuView {

    public interface ItemView {
        MenuItemImpl getItemData();

        void initialize(MenuItemImpl r1, int r2);

        boolean prefersCondensedTitle();
    }

    void initialize(MenuBuilder r1);
}
