package org.telegram.ui;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes6.dex */
public class ContactsWidgetConfigActivity extends ExternalActionActivity {
    private int creatingAppWidgetId;

    public static /* synthetic */ void $r8$lambda$8syqG0iEK5OvGMbYu2y2gS0jnzQ(ContactsWidgetConfigActivity r0, ArrayList r1) {
        r0.lambda$handleIntent$0(r1);
    }

    public ContactsWidgetConfigActivity() {
        this.creatingAppWidgetId = 0;
    }

    private /* synthetic */ void lambda$handleIntent$0(ArrayList r3) {
        Intent r32 = new Intent();
        r32.putExtra("appWidgetId", this.creatingAppWidgetId);
        setResult(-1, r32);
        finish();
    }

    @Override // org.telegram.ui.ExternalActionActivity
    public boolean handleIntent(Intent r1, boolean r2, boolean r3, boolean r4, int r5, int r6) {
        if (checkPasscode(r1, r2, r3, r4, r5, r6) == true) goto L5;
        return false;
    L5:
        Bundle r22 = r1.getExtras();
        if (r22 == null) goto L9;
        this.creatingAppWidgetId = r22.getInt("appWidgetId", 0);
    L9:
        if (this.creatingAppWidgetId == 0) goto L25;
        Bundle r23 = new Bundle();
        r23.putBoolean("onlySelect", true);
        r23.putInt("dialogsType", 10);
        r23.putBoolean("allowSwitchAccount", true);
        EditWidgetActivity r24 = new EditWidgetActivity(1, this.creatingAppWidgetId);
        r24.setDelegate(new ContactsWidgetConfigActivity$$ExternalSyntheticLambda0(this));
        if (AndroidUtilities.isTablet() == false) goto L16;
        if (this.layersActionBarLayout.getFragmentStack().isEmpty() == false) goto L19;
        this.layersActionBarLayout.addFragmentToStack(r24);
    L19:
        if (AndroidUtilities.isTablet() == true) goto L21;
        this.backgroundTablet.setVisibility(8);
    L21:
        this.actionBarLayout.showLastFragment();
        if (AndroidUtilities.isTablet() == false) goto L24;
        this.layersActionBarLayout.showLastFragment();
    L24:
        r1.setAction(null);
    L26:
        return true;
    L16:
        if (this.actionBarLayout.getFragmentStack().isEmpty() == false) goto L19;
        this.actionBarLayout.addFragmentToStack(r24);
        goto L19
    L25:
        finish();
        goto L26
    }
}
