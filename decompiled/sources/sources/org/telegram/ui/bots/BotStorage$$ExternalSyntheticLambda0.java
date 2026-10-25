package org.telegram.ui.bots;

import java.util.HashSet;
import java.util.function.Predicate;
import org.telegram.ui.bots.BotStorage;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotStorage$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ HashSet f$0;

    public /* synthetic */ BotStorage$$ExternalSyntheticLambda0(HashSet r1) {
        this.f$0 = r1;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object r1) {
        return BotStorage.m21344$r8$lambda$2TrejV4DZFTKINv8zN0Fm9UNc(this.f$0, (BotStorage.StorageConfig) r1);
    }
}
