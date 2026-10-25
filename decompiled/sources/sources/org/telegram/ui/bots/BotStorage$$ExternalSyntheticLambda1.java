package org.telegram.ui.bots;

import java.util.HashSet;
import java.util.function.Predicate;
import org.telegram.ui.bots.BotStorage;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotStorage$$ExternalSyntheticLambda1 implements Predicate {
    public final /* synthetic */ HashSet f$0;

    public /* synthetic */ BotStorage$$ExternalSyntheticLambda1(HashSet r1) {
        this.f$0 = r1;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object r1) {
        return BotStorage.m21345$r8$lambda$WR9erIHrU4gsykerJmxzH3I8b0(this.f$0, (BotStorage.StorageConfig) r1);
    }
}
