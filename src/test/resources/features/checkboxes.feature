Feature: The Internet - Checkboxes

  Background:
    Given kullanıcı checkboxes sayfasındadır

  Scenario: Sayfada iki checkbox bulunur
    Then toplam checkbox sayısı 2 olmalıdır

  Scenario: İlk checkbox başlangıçta işaretsizdir
    Then 0. index'teki checkbox işaretli değildir

  Scenario: İkinci checkbox başlangıçta işaretlidir
    Then 1. index'teki checkbox işaretlidir

  Scenario: İlk checkbox işaretlenebilir
    When 0. index'teki checkbox işaretlenir
    Then 0. index'teki checkbox işaretlidir

  Scenario: İkinci checkbox'ın işareti kaldırılabilir
    When 1. index'teki checkbox işareti kaldırılır
    Then 1. index'teki checkbox işaretli değildir