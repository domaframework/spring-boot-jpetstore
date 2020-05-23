package sample.web;

import java.util.List;

public final class Constants {

  public static final List<String> CARD_TYPE_LIST;

  static {
    CARD_TYPE_LIST = List.of("Visa", "MasterCard", "American Express");
  }

  public static final List<String> LANGUAGE_LIST;

  static {
    LANGUAGE_LIST = List.of("english", "japanese");
  }

  public static final List<String> CATEGORY_LIST;

  static {
    CATEGORY_LIST = List.of("FISH", "DOGS", "REPTILES", "CATS", "BIRDS");
  }
}
