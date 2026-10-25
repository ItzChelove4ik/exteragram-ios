package org.telegram.messenger;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class ContactsController$$ExternalSyntheticLambda11 implements RequestDelegate {
    public final /* synthetic */ ContactsController f$0;
    public final /* synthetic */ HashMap f$1;
    public final /* synthetic */ ArrayList f$10;
    public final /* synthetic */ HashMap f$11;
    public final /* synthetic */ SparseArray f$2;
    public final /* synthetic */ boolean[] f$3;
    public final /* synthetic */ HashMap f$4;
    public final /* synthetic */ TLRPC.TL_contacts_importContacts f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ HashMap f$7;
    public final /* synthetic */ boolean f$8;
    public final /* synthetic */ HashMap f$9;

    public /* synthetic */ ContactsController$$ExternalSyntheticLambda11(ContactsController r1, HashMap r2, SparseArray r3, boolean[] r4, HashMap r5, TLRPC.TL_contacts_importContacts r6, int r7, HashMap r8, boolean r9, HashMap r10, ArrayList r11, HashMap r12) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
        this.f$9 = r10;
        this.f$10 = r11;
        this.f$11 = r12;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r15, TLRPC.TL_error r16) {
        ContactsController.$r8$lambda$hGKiWaKZlL1gl1pfkB1fCc9fP7Y(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, r15, r16);
    }
}
