package com.google.mlkit.genai.prompt;

import com.google.mlkit.genai.prompt.CreateCachedContextRequest;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"createCachedContextRequest", "Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "promptPrefix", "Lcom/google/mlkit/genai/prompt/PromptPrefix;", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class CreateCachedContextRequestKt {
    public static final CreateCachedContextRequest createCachedContextRequest(String r1, PromptPrefix r2) {
        return new CreateCachedContextRequest.Builder(r1, r2).build();
    }
}
