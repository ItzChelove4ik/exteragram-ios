package androidx.appcompat.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.R$layout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuPresenter;

/* loaded from: classes3.dex */
class MenuDialogHelper implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, MenuPresenter.Callback {
    private AlertDialog mDialog;
    private MenuBuilder mMenu;
    ListMenuPresenter mPresenter;
    private MenuPresenter.Callback mPresenterCallback;

    public MenuDialogHelper(MenuBuilder r1) {
        this.mMenu = r1;
    }

    public void dismiss() {
        AlertDialog r0 = this.mDialog;
        if (r0 == null) goto L6;
        r0.dismiss();
        return;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface r1, int r2) {
        this.mMenu.performItemAction((MenuItemImpl) this.mPresenter.getAdapter().getItem(r2), 0);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
    public void onCloseMenu(MenuBuilder r2, boolean r3) {
        if (r3 == false) goto L4;
    L5:
        dismiss();
    L6:
        MenuPresenter.Callback r1 = this.mPresenterCallback;
        if (r1 == null) goto L10;
        r1.onCloseMenu(r2, r3);
        return;
    L10:
        return;
    L4:
        if (r2 != this.mMenu) goto L6;
        goto L5
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface r2) {
        this.mPresenter.onCloseMenu(this.mMenu, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface r3, int r4, KeyEvent r5) {
        if (r4 == 82) goto L7;
        if (r4 == 4) goto L7;
    L33:
        return this.mMenu.performShortcut(r4, r5, 0);
    L7:
        if (r5.getAction() != 0) goto L19;
        if (r5.getRepeatCount() != 0) goto L19;
        Window r32 = this.mDialog.getWindow();
        if (r32 == null) goto L33;
        View r33 = r32.getDecorView();
        if (r33 == null) goto L33;
        KeyEvent.DispatcherState r34 = r33.getKeyDispatcherState();
        if (r34 == null) goto L33;
        r34.startTracking(r5, this);
        return true;
    L19:
        if (r5.getAction() != 1) goto L33;
        if (r5.isCanceled() == true) goto L33;
        Window r0 = this.mDialog.getWindow();
        if (r0 == null) goto L33;
        View r02 = r0.getDecorView();
        if (r02 == null) goto L33;
        KeyEvent.DispatcherState r03 = r02.getKeyDispatcherState();
        if (r03 == null) goto L33;
        if (r03.isTracking(r5) == false) goto L33;
        this.mMenu.close(true);
        r3.dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
    public boolean onOpenSubMenu(MenuBuilder r1) {
        MenuPresenter.Callback r0 = this.mPresenterCallback;
        if (r0 != null) goto L5;
        return false;
    L5:
        return r0.onOpenSubMenu(r1);
    }

    public void show(IBinder r6) {
        MenuBuilder r0 = this.mMenu;
        AlertDialog.Builder r1 = new AlertDialog.Builder(r0.getContext());
        ListMenuPresenter r2 = new ListMenuPresenter(r1.getContext(), R$layout.abc_list_menu_item_layout);
        this.mPresenter = r2;
        r2.setCallback(this);
        this.mMenu.addMenuPresenter(this.mPresenter);
        r1.setAdapter(this.mPresenter.getAdapter(), this);
        View r22 = r0.getHeaderView();
        if (r22 == null) goto L5;
        r1.setCustomTitle(r22);
    L6:
        r1.setOnKeyListener(this);
        AlertDialog r02 = r1.create();
        this.mDialog = r02;
        r02.setOnDismissListener(this);
        WindowManager.LayoutParams r03 = this.mDialog.getWindow().getAttributes();
        r03.type = 1003;
        if (r6 == null) goto L9;
        r03.token = r6;
    L9:
        r03.flags |= 131072;
        this.mDialog.show();
        return;
    L5:
        r1.setIcon(r0.getHeaderIcon()).setTitle(r0.getHeaderTitle());
        goto L6
    }
}
