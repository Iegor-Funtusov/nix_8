package ua.com.alevel.sealedclass;

public sealed class CustomSealedClass permits ExtendsSealedClass1, ExtendsSealedClass2 {

    sealed class AA permits BB { }

    private final class BB extends AA {}
}
