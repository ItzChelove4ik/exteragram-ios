package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlinx.serialization.json.Json;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0080\u0080\u0004¨\u0006\u0006"}, d2 = {"Composer", "Lkotlinx/serialization/json/internal/Composer;", "sb", "Lkotlinx/serialization/json/internal/InternalJsonWriter;", "json", "Lkotlinx/serialization/json/Json;", "kotlinx-serialization-json"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ComposersKt {
    public static final Composer Composer(InternalJsonWriter r1, Json r2) {
        if (r2.getConfiguration().getPrettyPrint() == false) goto L7;
        return new ComposerWithPrettyPrint(r1, r2);
    L7:
        return new Composer(r1);
    }
}
