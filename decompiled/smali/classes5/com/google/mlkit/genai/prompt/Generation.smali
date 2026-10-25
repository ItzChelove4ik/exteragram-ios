.class public final Lcom/google/mlkit/genai/prompt/Generation;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\u0008\u00c6\u0002\u0018\u00002\u00020\u0001J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\u0008\u0005\u0010\u0006\u00a8\u0006\u0007"
    }
    d2 = {
        "Lcom/google/mlkit/genai/prompt/Generation;",
        "",
        "Lcom/google/mlkit/genai/prompt/GenerationConfig;",
        "options",
        "Lcom/google/mlkit/genai/prompt/GenerativeModel;",
        "getClient",
        "(Lcom/google/mlkit/genai/prompt/GenerationConfig;)Lcom/google/mlkit/genai/prompt/GenerativeModel;",
        "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"
    }
    k = 0x1
    mv = {
        0x2,
        0x3,
        0x0
    }
    xi = 0x30
.end annotation


# static fields
.field public static final INSTANCE:Lcom/google/mlkit/genai/prompt/Generation;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/mlkit/genai/prompt/Generation;

    invoke-direct {v0}, Lcom/google/mlkit/genai/prompt/Generation;-><init>()V

    sput-object v0, Lcom/google/mlkit/genai/prompt/Generation;->INSTANCE:Lcom/google/mlkit/genai/prompt/Generation;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final getClient(Lcom/google/mlkit/genai/prompt/GenerationConfig;)Lcom/google/mlkit/genai/prompt/GenerativeModel;
    .locals 9

    .line 2
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;

    .line 3
    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    .line 4
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;

    invoke-static {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;->zzb(Lcom/google/mlkit/genai/prompt/GenerationConfig;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;)I

    move-result p0

    .line 5
    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/GenerationConfig;->getWorkerExecutor()Ljava/util/concurrent/ExecutorService;

    move-result-object v1

    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;

    .line 6
    sget-object v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    invoke-direct {v3, p1, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;-><init>(Lcom/google/mlkit/genai/prompt/GenerationConfig;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;)V

    .line 7
    invoke-direct {v2, p0, v1, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;-><init>(ILjava/util/concurrent/ExecutorService;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;)V

    new-instance v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;

    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;

    .line 8
    invoke-static {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;->zzb(Lcom/google/mlkit/genai/prompt/GenerationConfig;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;)I

    move-result p0

    .line 9
    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/GenerationConfig;->getWorkerExecutor()Ljava/util/concurrent/ExecutorService;

    move-result-object v1

    new-instance v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwf;

    .line 10
    invoke-direct {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwf;-><init>()V

    .line 11
    invoke-direct {v3, p0, v1, v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;-><init>(ILjava/util/concurrent/ExecutorService;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;)V

    move-object p0, v4

    new-instance v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;

    .line 12
    invoke-static {p1, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;->zzb(Lcom/google/mlkit/genai/prompt/GenerationConfig;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;)I

    move-result v1

    .line 13
    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/GenerationConfig;->getWorkerExecutor()Ljava/util/concurrent/ExecutorService;

    move-result-object v5

    new-instance v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;

    sget-object v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;

    .line 14
    invoke-direct {v6, p1, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;-><init>(Lcom/google/mlkit/genai/prompt/GenerationConfig;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;)V

    .line 15
    invoke-direct {v4, v1, v5, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;-><init>(ILjava/util/concurrent/ExecutorService;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;)V

    new-instance v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;

    .line 16
    invoke-static {p1, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;->zzb(Lcom/google/mlkit/genai/prompt/GenerationConfig;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;)I

    move-result v1

    .line 17
    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/GenerationConfig;->getWorkerExecutor()Ljava/util/concurrent/ExecutorService;

    move-result-object v6

    new-instance v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;

    .line 18
    invoke-direct {v8, p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;-><init>(Lcom/google/mlkit/genai/prompt/GenerationConfig;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;)V

    .line 19
    invoke-direct {v5, v1, v6, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;-><init>(ILjava/util/concurrent/ExecutorService;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;)V

    new-instance v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;

    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;

    .line 20
    invoke-static {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyt;->zzb(Lcom/google/mlkit/genai/prompt/GenerationConfig;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzye;)I

    move-result p0

    .line 21
    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/GenerationConfig;->getWorkerExecutor()Ljava/util/concurrent/ExecutorService;

    move-result-object v1

    new-instance v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;

    .line 22
    invoke-direct {v8, p1, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzyj;-><init>(Lcom/google/mlkit/genai/prompt/GenerationConfig;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaam;)V

    .line 23
    invoke-direct {v6, p0, v1, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;-><init>(ILjava/util/concurrent/ExecutorService;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaty;)V

    move-object v1, p1

    .line 24
    invoke-direct/range {v0 .. v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzzy;-><init>(Lcom/google/mlkit/genai/prompt/GenerationConfig;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzwl;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaah;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaak;)V

    return-object v0
.end method
