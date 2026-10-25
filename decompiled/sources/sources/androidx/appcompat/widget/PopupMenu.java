package androidx.appcompat.widget;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;

/* loaded from: classes3.dex */
public class PopupMenu {
    private final View mAnchor;
    private final Context mContext;
    private final MenuBuilder mMenu;
    final MenuPopupHelper mPopup;

    /* renamed from: androidx.appcompat.widget.PopupMenu$1, reason: invalid class name */
    public class AnonymousClass1 implements MenuBuilder.Callback {
        final /* synthetic */ PopupMenu this$0;

        public AnonymousClass1(PopupMenu r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder r1, MenuItem r2) {
            this.this$0.getClass();
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder r1) {
        }
    }

    /* renamed from: androidx.appcompat.widget.PopupMenu$2, reason: invalid class name */
    public class AnonymousClass2 implements PopupWindow.OnDismissListener {
        final /* synthetic */ PopupMenu this$0;

        public AnonymousClass2(PopupMenu r1) {
            this.this$0 = r1;
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            this.this$0.getClass();
        }
    }

    public PopupMenu(Context r8, View r9, int r10, int r11, int r12) {
        this.mContext = r8;
        this.mAnchor = r9;
        MenuBuilder r2 = new MenuBuilder(r8);
        this.mMenu = r2;
        r2.setCallback(new AnonymousClass1(this));
        MenuPopupHelper r0 = new MenuPopupHelper(r8, r2, r9, false, r11, r12);
        this.mPopup = r0;
        r0.setGravity(r10);
        r0.setOnDismissListener(new AnonymousClass2(this));
    }

    public Menu getMenu() {
        return this.mMenu;
    }

    public void setForceShowIcon(boolean r1) {
        this.mPopup.setForceShowIcon(r1);
    }

    public void show() {
        this.mPopup.show();
    }
}
