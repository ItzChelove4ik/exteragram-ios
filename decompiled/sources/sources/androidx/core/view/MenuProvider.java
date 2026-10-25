package androidx.core.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/* loaded from: classes.dex */
public interface MenuProvider {
    void onCreateMenu(Menu r1, MenuInflater r2);

    void onMenuClosed(Menu r1);

    boolean onMenuItemSelected(MenuItem r1);

    void onPrepareMenu(Menu r1);
}
