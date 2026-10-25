.class final synthetic Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlm;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/function/Function;


# static fields
.field static final synthetic zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlm;


# direct methods
.method public static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlm;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlm;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlm;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlm;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final synthetic apply(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmp;

    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmp;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmq;

    move-result-object p0

    return-object p0
.end method
