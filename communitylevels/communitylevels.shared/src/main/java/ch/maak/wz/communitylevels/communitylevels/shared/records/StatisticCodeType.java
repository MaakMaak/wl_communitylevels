package ch.maak.wz.communitylevels.communitylevels.shared.records;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

public class StatisticCodeType extends AbstractCodeType<String, String> {
  private static final long serialVersionUID = 1L;

  public static final String ID = "5a4e4819-628f-4a78-8809-6d380c3d1b46";

  @Override
  public String getId() {
    return ID;
  }

  @Order(0)
  public static class TotalLevelsCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;

    public static final String ID = "efd5d920-9484-405f-8a79-45ad93c3c698";

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(1000)
  public static class TotalAttemptsCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;

    public static final String ID = "e4bb453d-fb61-4dfe-b0f1-5b7abf4b54cd";

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(1000)
  public static class TotalWinsCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;

    public static final String ID = "59815c56-fb41-41b4-938d-13ce1dd6db89";

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(2000)
  public static class TotalLikesCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;

    public static final String ID = "70a1da65-08ac-43de-bdba-a936eddebb6b";

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(3000)
  public static class TotalCreatorsCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;

    public static final String ID = "ff5b63d4-0a0c-4e92-939f-d700b5c0392a";

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(4000)
  public static class TotalRecordHoldersCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;

    public static final String ID = "aaa973ad-c82c-4999-bb5a-4893ce81a5fa";

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(5000)
  public static class TotalUnbeatenLevelsCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;

    public static final String ID = "459872b6-bce3-417d-8316-85c63d6cc7a4";

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(6000)
  public static class LongestLevelCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;

    public static final String ID = "a26d1bc0-488f-41f4-8b40-8bd27f79440e";

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(7000)
  public static class LongestLevelIdCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;

    public static final String ID = "b1839a9a-42bd-4f50-a511-f15c11986997";

    @Override
    public String getId() {
      return ID;
    }
  }
}
