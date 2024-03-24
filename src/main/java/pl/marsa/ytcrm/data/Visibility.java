package pl.marsa.ytcrm.data;

public enum Visibility {
    PUBLIC("public"),
    NOT_PUBLIC("not_public"),
    PRIVATE("private");

    Visibility(String value) {
        this.value = value;
    }

    String value;
}
