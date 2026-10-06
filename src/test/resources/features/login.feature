Feature: The Internet - Login

  Background:
    Given kullanıcı login sayfasındadır

  @smoke
  Scenario: Doğru bilgilerle giriş başarılı
    When kullanıcı "tomsmith" ve "SuperSecretPassword!" ile giriş yapar
    Then "You logged into a secure area!" mesajı görüntülenir
    And secure area sayfası açılır

  Scenario Outline: Hatalı bilgilerle giriş başarısız
    When kullanıcı "<kullanici>" ve "<sifre>" ile giriş yapar
    Then "<beklenenMesaj>" mesajı görüntülenir

    Examples:
      | kullanici     | sifre                 | beklenenMesaj              |
      | yanlisKulanci | SuperSecretPassword!  | Your username is invalid!  |
      | tomsmith      | yanlisSifre            | Your password is invalid!  |

  Scenario: Başarılı giriş sonrası çıkış yapılabilir
    When kullanıcı "tomsmith" ve "SuperSecretPassword!" ile giriş yapar
    And kullanıcı çıkış yapar
    Then "You logged out of the secure area!" mesajı görüntülenir