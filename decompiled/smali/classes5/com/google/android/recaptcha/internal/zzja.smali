.class public abstract Lcom/google/android/recaptcha/internal/zzja;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zzb:Lkotlin/text/Regex;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lkotlin/text/Regex;

    const-string v1, "^[a-zA-Z0-9/_]{1,100}$"

    invoke-direct {v0, v1}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzja;->zzb:Lkotlin/text/Regex;

    return-void
.end method

.method public static final zza()Lkotlin/text/Regex;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzja;->zzb:Lkotlin/text/Regex;

    return-object v0
.end method
