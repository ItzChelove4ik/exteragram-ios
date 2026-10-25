.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfg;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/concurrent/futures/CallbackToFutureAdapter$Resolver;


# instance fields
.field private final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;

.field private final synthetic zzb:Ljava/lang/Object;

.field private final synthetic zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfu;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;Ljava/lang/Object;ZLcom/google/android/gms/internal/mlkit_genai_prompt/zzfu;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfg;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfg;->zzb:Ljava/lang/Object;

    iput-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfg;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfu;

    return-void
.end method


# virtual methods
.method public final synthetic attachCompleter(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;
    .locals 3

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfg;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfu;

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfg;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfg;->zzb:Ljava/lang/Object;

    const/4 v2, 0x1

    invoke-virtual {v1, p0, v2, v0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzfv;->zzs(Ljava/lang/Object;ZLcom/google/android/gms/internal/mlkit_genai_prompt/zzfu;Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
