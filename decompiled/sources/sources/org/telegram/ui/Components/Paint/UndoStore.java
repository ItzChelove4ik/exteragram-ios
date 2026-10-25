package org.telegram.ui.Components.Paint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class UndoStore {
    private UndoStoreDelegate delegate;
    private List<UUID> operations;
    private Map<UUID, Runnable> uuidToOperationMap;

    public interface UndoStoreDelegate {
        void historyChanged();
    }

    public static /* synthetic */ void $r8$lambda$WlytUNSZihd6meEcBDUtMIkFh0s(UndoStore r0) {
        r0.lambda$notifyOfHistoryChanges$0();
    }

    public UndoStore() {
        this.uuidToOperationMap = new HashMap();
        this.operations = new ArrayList();
    }

    private /* synthetic */ void lambda$notifyOfHistoryChanges$0() {
        UndoStoreDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.historyChanged();
        return;
    }

    private void notifyOfHistoryChanges() {
        AndroidUtilities.runOnUIThread(new UndoStore$$ExternalSyntheticLambda0(this));
    }

    public boolean canUndo() {
        return !this.operations.isEmpty();
    }

    public UndoStoreDelegate getDelegate() {
        return this.delegate;
    }

    public void registerUndo(UUID r2, Runnable r3) {
        this.uuidToOperationMap.put(r2, r3);
        this.operations.add(r2);
        notifyOfHistoryChanges();
    }

    public void reset() {
        this.operations.clear();
        this.uuidToOperationMap.clear();
        notifyOfHistoryChanges();
    }

    public void setDelegate(UndoStoreDelegate r1) {
        this.delegate = r1;
    }

    public void undo() {
        if (this.operations.size() != 0) goto L5;
        return;
    L5:
        int r0 = this.operations.size() - 1;
        UUID r1 = this.operations.get(r0);
        Runnable r2 = this.uuidToOperationMap.get(r1);
        this.uuidToOperationMap.remove(r1);
        this.operations.remove(r0);
        r2.run();
        notifyOfHistoryChanges();
    }

    public void unregisterUndo(UUID r2) {
        this.uuidToOperationMap.remove(r2);
        this.operations.remove(r2);
        notifyOfHistoryChanges();
    }
}
